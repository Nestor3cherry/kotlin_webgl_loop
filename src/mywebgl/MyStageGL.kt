package mywebgl


import createjs.*
import org.khronos.webgl.WebGLRenderingContext
import org.khronos.webgl.WebGLTexture

/**
 *  Defined render actions
 */

val draw = 0
val set_mask = 1
val rem_mask = 2
val set_composite = 3
val do_flatten = 4


/**
 *  Pool that servers buffers that can store render commands (called CommandBuffers)
 */

val bufferPool = CommandBufferPool()


/**
 *   function that overrides default webGL render loop
 */

fun StageGL.overrideRenderLoop() {

    val old = asDynamic()._appendToBatch

    val new =   { container: dynamic, concatMtx: dynamic, concatAlpha: dynamic, ignoreCache: dynamic ->
                if (!_directDraw) throw Exception("this render loop works only on directdraw=true configuration ")
                val mainBuffer = bufferPool.get()
                getTransformMatrix(container, concatMtx)
                _searchInRenderTree(container, concatAlpha, mainBuffer)
                _renderMode =  "source-over"
                _renderModeLazy = "source-over"
                _executeFrameActions(mainBuffer)
                bufferPool.clear()
            }

    asDynamic()._appendToBatch = new

}




/**
 * Execute render commands
 * draw: appends data to geometry buffers
 * set_mask: activates scissor-based mask
 * rem_mask: disables scissor-based mask
 * set_composite: change composite
 * do_flatten: regroups different buffers into a single command buffer, minimizing composite changes
 */

private fun StageGL._executeFrameActions(mainGroup: CommandBuffer) {
    val gl = _webGLContext

    mainGroup.consumeAll { command, item ->
        when (command) {
            draw -> {

                val useCache = item.cacheCanvas != undefined

                // check for overflowing batch, if yes then force a render
                if (_batchVertexCount + StageGL.INDICIES_PER_CARD > _maxBatchVertexCount) {
                    batchReason = "vertexOverflow"
                    _renderBatch()                   // <------------------------------------------------------------
                }

                val frame: dynamic =
                    if (item._webGLRenderStyle == 1) item.spriteSheet.getFrame(item.currentFrame) else null
                val image = when {
                    (item._webGLRenderStyle == 2 || useCache) -> if (!useCache) item.image else item.cacheCanvas
                    (item._webGLRenderStyle == 1) -> {
                        frame?.image
                    }
                    else -> null
                }

                if (image != null) {
                    val texture = getTexture(image, gl)
                    if (texture != undefined) {
                        image._drawID = _drawID

                        // sub components of figuring out the position an object holds
                        appendGeometry(
                            item,
                            useCache,
                            image,
                            frame,
                            item._myGlMtx,
                            texture.asDynamic()._activeIndex,
                            item.alpha,
                            item.concatAlpha
                        )
                        _batchVertexCount += StageGL.INDICIES_PER_CARD
                    }
                }
            }
            set_mask -> {
                batchReason = "setMask"
                _renderBatch()
                gl.enable(WebGLRenderingContext.SCISSOR_TEST)
                gl.scissor(item.x, item.y, item.width, item.height)
            }
            rem_mask -> {
                batchReason = "removeMask"
                _renderBatch()
                gl.disable(WebGLRenderingContext.SCISSOR_TEST)
            }
            set_composite -> {
                _renderModeLazy = item
                _applyRenderMode()
            }
            do_flatten -> {
                _executeFrameActions( regroupCommands(item))
            }
        }
    }
}


/**
 *
 * Regroups independent render command list to minimize composite change.
 * It assumes that render order between different command groups are not important
 */


private fun StageGL.regroupCommands(allGroups: CommandBufferGroup): CommandBuffer {

    val out = CommandBuffer()

    val remaining = allGroups.commandGroups
    val todo = allGroups.size
    var done = 0
    var i: Int
    val entryComposite = _renderMode
    var currentComposite = _renderMode


    while (done < todo) {
        //step 1 get all draws for current composite
        i = 0
        while (i < todo - done) {
            val it = remaining[i]
            while (true) {
                if (it.size > 0) {
                    val command = it.nextCommand
                    if (command == set_composite) {
                        currentComposite = it.nextItem
                        i++
                        break
                    }
                    if (command != draw) throw Exception("only draw/mask commands inside a flatten section")
                    it.consume { com, item -> out.add(com, item) }
                } else {
                    done++
                    remaining[i] = remaining[todo - done]
                    break
                }
            }
        }

        if (done == todo) break

        //step 2, choose new composite
        out.add(set_composite, currentComposite)

        //unlock all items that next command is the selected composite

        i = 0
        while (i < todo - done) {
            val it = remaining[i]
            if (currentComposite == it.nextItem) {
                it.consume()
                if (it.size == 0) {
                    done++
                    remaining[i] = remaining[todo - done]
                    continue
                }
            }
            i++
        }
    }
    if (currentComposite != entryComposite) out.add(set_composite, entryComposite)
    return out
}


/**
 * Search in graphics tree structure and composes a CommandBuffer with all the actions needed
 * to render it
 */

private fun StageGL._searchInRenderTree(
    container: Container,
    concatAlpha: Float,
    commandBuffer: CommandBuffer
) {
    val cMtx = container._myGlMtx
    val applyMask = applyScissorsMask(container, cMtx, commandBuffer)
    val previousRenderMode = _renderModeLazy

    if (container.compositeOperation != null) {
        _renderModeLazy = container.compositeOperation
    }

    container.children.forEach { item ->
        if (item.visible && concatAlpha > 0.0035) {

            val useCache = item.cacheCanvas != undefined

            if (!useCache && item.asDynamic().children != undefined) {
                if (item.asDynamic().children.length > 0) {
                    if (item.asDynamic()._updateState)  item.asDynamic()._updateState()
                    getTransformMatrix(item, cMtx)
                    val groupForChild = processFlatten(item, commandBuffer)
                    val previousLazy = _renderModeLazy
                    val previousMode = _renderMode
                    _searchInRenderTree(item.asDynamic(), item.alpha * concatAlpha, groupForChild)
                    if (groupForChild !== commandBuffer) {
                        _renderModeLazy = previousLazy
                        _renderMode = previousMode
                    }
                }

            } else {
                getTransformMatrix(item, cMtx)
                val containerRenderMode = _renderModeLazy
                if (item.compositeOperation != null) {
                    _renderModeLazy = item.compositeOperation
                }

                if (_renderMode != _renderModeLazy) {
                    _renderMode = _renderModeLazy
                    commandBuffer.add(set_composite, _renderModeLazy)
                }
                item.asDynamic().concatAlpha = concatAlpha
                commandBuffer.add(draw, item)
                _renderModeLazy = containerRenderMode
            }
        }
    }
    _renderModeLazy = previousRenderMode


    if (applyMask) {
        removeScissorMask(commandBuffer)
    }
}


/**
 * Evaluates the need of a independent commandbuffer, in order to later combine it with another groups
 * tree nodes with the same value in 'flatten' field will be stored in different buffers and later combined
 */

private fun processFlatten(
    item: DisplayObject,
    commandGroup: CommandBuffer
): CommandBuffer {
    val flatten = item.asDynamic().flatten
    return if (flatten == undefined) commandGroup else {
        val new =!bufferPool.hasGroup(flatten)
        val newGroup = bufferPool.get(flatten)
        if (new) commandGroup.add(do_flatten, bufferPool.getBufferGroup(flatten))
        newGroup
    }
}

private fun StageGL.getTexture(image: dynamic, gl: WebGLRenderingContext): WebGLTexture {
    val texture = if (image._storeID === undefined) {
        // this texture is new to us so load it and add it to the batch
        _loadTextureImage(gl, image)
    } else {
        // fetch the texture (render textures know how to look themselves up to simplify this logic)
        val temp = _textureDictionary[image._storeID].asDynamic()

        if (temp == undefined) { //TODO: this should really not occur but has due to bugs, hopefully this can be removed eventually
            if (vocalDebug) {
                console.log("Image source should not be lookup a non existent texture, please report a bug."); }
        } else {
            // put it in the batch if needed
            if (temp._batchID !== _batchID) {
                _insertTextureInBatch(gl, temp)
            }
        }
        temp
    }
    return texture
}

private fun StageGL.removeScissorMask(mainGroup: CommandBuffer) {

    _masks.asDynamic().pop()
    if (_masks.isEmpty()) {
        //disable masks
        mainGroup.add(rem_mask, null)
    } else {
        //restore previous mask combinations
        val combined = _getCombinedMasks()
        mainGroup.add(set_mask, Rectangle(combined.x, combined.y, combined.width, combined.height))
    }
}

private fun StageGL.appendGeometry(
    item: DisplayObject,
    useCache: Boolean,
    image: dynamic,
    frame: dynamic,
    iMtx: Matrix2D,
    texIndex: Int,
    itemAlpha: Float,
    concatAlpha: Float
) {
    var subL = 0f
    var subT = 0f
    var subR = 0f
    var subB = 0f
    var src: dynamic
    var uvRect: dynamic = null

    // BITMAP / Cached Canvas
    if (item._webGLRenderStyle == 2 || useCache) {
        if (!useCache && item.asDynamic().sourceRect != null && item.asDynamic().sourceRect != undefined) {
            // calculate uvs
            if (item.asDynamic()._uvRect == undefined) {
                item.asDynamic()._uvRect = js("{}")
            }
            src = item.asDynamic().sourceRect
            val imgWidth = image.width
            val imgHeight = image.height
            uvRect = item.asDynamic()._uvRect
            uvRect.t = 1 - ((src.y) / imgHeight) as Float
            uvRect.l = (src.x) / imgWidth
            uvRect.b = 1 - ((src.y + src.height) / imgHeight) as Float
            uvRect.r = (src.x + src.width) / imgWidth

            // calculate vertices
            subL = 0f
            subT = 0f
            subR = src.width + subL
            subB = src.height + subT
        } else {
            // calculate uvs
            uvRect = StageGL.UV_RECT
            // calculate vertices
            if (!useCache) {
                subL = 0f
                subT = 0f
                subR = image.width + subL;
                subB = image.height + subT;
            } else {
                src = item.asDynamic().bitmapCache
                subL = src.x + (src._filterOffX / src.scale)
                subT = src.y + (src._filterOffY / src.scale)
                subR = (src._drawWidth / src.scale) + subL
                subB = (src._drawHeight / src.scale) + subT
            }
        }

        // SPRITE
    } else if (item._webGLRenderStyle == 1) {
        val rect = frame.rect as Rectangle

        // calculate uvs
        uvRect = frame.uvRect
        if (uvRect == null || uvRect == undefined) {
            uvRect = StageGL.buildUVRects(item.asDynamic().spriteSheet, item.asDynamic().currentFrame, false)
        }

        // calculate vertices
        subL = -frame.regX; subT = -frame.regY
        subR = rect.width - frame.regX as Float; subB = rect.height - frame.regY as Float
    }


    val cfg = _activeConfig
    val vpos = cfg.position.array
    val uvs = cfg.uv.array
    val texI = cfg.texture.array
    val alphas = cfg.alpha.array

    // apply vertices
    var spacing = cfg.position.spacing as Int
    var vtxOff: Int = _batchVertexCount * spacing + cfg.position.offset as Int
    vpos[vtxOff] = subL * iMtx.a + subT * iMtx.c + iMtx.tx; vpos[vtxOff + 1] = subL * iMtx.b + subT *
            iMtx.d + iMtx.ty
    vtxOff += spacing
    vpos[vtxOff] = subL * iMtx.a + subB * iMtx.c + iMtx.tx; vpos[vtxOff + 1] = subL * iMtx.b + subB *
            iMtx.d + iMtx.ty
    vtxOff += spacing
    vpos[vtxOff] = subR * iMtx.a + subT * iMtx.c + iMtx.tx; vpos[vtxOff + 1] = subR * iMtx.b + subT *
            iMtx.d + iMtx.ty
    vtxOff += spacing
    vpos[vtxOff] = subL * iMtx.a + subB * iMtx.c + iMtx.tx; vpos[vtxOff + 1] = subL * iMtx.b + subB *
            iMtx.d + iMtx.ty
    vtxOff += spacing
    vpos[vtxOff] = subR * iMtx.a + subT * iMtx.c + iMtx.tx; vpos[vtxOff + 1] = subR * iMtx.b + subT *
            iMtx.d + iMtx.ty
    vtxOff += spacing
    vpos[vtxOff] = subR * iMtx.a + subB * iMtx.c + iMtx.tx; vpos[vtxOff + 1] = subR * iMtx.b + subB *
            iMtx.d + iMtx.ty

    // apply uvs
    spacing = cfg.uv.spacing as Int
    var uvOff: Int = _batchVertexCount * spacing + cfg.uv.offset as Int
    uvs[uvOff] = uvRect.l; uvs[uvOff + 1] = uvRect.t
    uvOff += spacing
    uvs[uvOff] = uvRect.l; uvs[uvOff + 1] = uvRect.b
    uvOff += spacing
    uvs[uvOff] = uvRect.r; uvs[uvOff + 1] = uvRect.t
    uvOff += spacing
    uvs[uvOff] = uvRect.l; uvs[uvOff + 1] = uvRect.b
    uvOff += spacing
    uvs[uvOff] = uvRect.r; uvs[uvOff + 1] = uvRect.t
    uvOff += spacing
    uvs[uvOff] = uvRect.r; uvs[uvOff + 1] = uvRect.b

    // apply texture
    spacing = cfg.texture.spacing as Int
    var texOff: Int = _batchVertexCount * spacing + cfg.texture.offset as Int
    texI[texOff] = texIndex
    texOff += spacing
    texI[texOff] = texIndex
    texOff += spacing
    texI[texOff] = texIndex
    texOff += spacing
    texI[texOff] = texIndex
    texOff += spacing
    texI[texOff] = texIndex
    texOff += spacing
    texI[texOff] = texIndex

    // apply alpha
    spacing = cfg.alpha.spacing as Int
    var aOff: Int = _batchVertexCount * spacing + cfg.alpha.offset as Int
    alphas[aOff] = itemAlpha * concatAlpha
    aOff += spacing
    alphas[aOff] = itemAlpha * concatAlpha
    aOff += spacing
    alphas[aOff] = itemAlpha * concatAlpha
    aOff += spacing
    alphas[aOff] = itemAlpha * concatAlpha
    aOff += spacing
    alphas[aOff] = itemAlpha * concatAlpha
    aOff += spacing
    alphas[aOff] = itemAlpha * concatAlpha
}

private fun StageGL.applyScissorsMask(
    container: Container,
    cMtx: Matrix2D,
    mainGroup: CommandBuffer
): Boolean {
    var applyMask = false
    if (container.mask !== null) {
        var isRotated = (cMtx.c != 0f || cMtx.b != 0f)
        if (isRotated) {
            console.warn("mask cannot be added to rotated objects")
        }
        applyMask = !isRotated
        if (applyMask) {
            val maskBounds = container.mask!!.getBounds()
            val point1 = cMtx.transformPoint(maskBounds.x, maskBounds.y)
            val point2 = cMtx.transformPoint(maskBounds.x + maskBounds.width, maskBounds.y + maskBounds.height)
            var width = point2.x - point1.x
            var height = point2.y - point1.y
            var x = point1.x
            var y = canvas.height - point1.y - height
            if (x < 0) {
                width += x;x = 0f
            }
            if (width < 0) width = 0f
            if (y < 0) {
                height += y;y = 0f
            }
            if (height < 0) height = 0f
            val rectangle = Rectangle(x, y, width, height)
            _masks.asDynamic().push(rectangle)
            val combined = _getCombinedMasks()
            mainGroup.add(set_mask, Rectangle(combined.x, combined.y, combined.width, combined.height))
        }
    }
    return applyMask
}

private fun getTransformMatrix(item: DisplayObject, concatMtx: Matrix2D): Matrix2D {

    if (item._myGlMtx == undefined) item._myGlMtx = Matrix2D()

    val cMtx = item._myGlMtx
    cMtx.copy(concatMtx)
    if (item.transformMatrix != null) {
        cMtx.appendMatrix(item.transformMatrix!!)
    } else {

        cMtx.appendTransform(
            item.x, item.y,
            item.scaleX, item.scaleY,
            item.rotation, item.skewX, item.skewY,
            item.regX, item.regY
        )
    }
    return cMtx
}



