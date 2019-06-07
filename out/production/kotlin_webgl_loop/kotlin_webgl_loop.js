if (typeof kotlin === 'undefined') {
  throw new Error("Error loading module 'kotlin_webgl_loop'. Its dependency 'kotlin' was not found. Please, check whether 'kotlin' is loaded prior to 'kotlin_webgl_loop'.");
}
var kotlin_webgl_loop = function (_, Kotlin) {
  'use strict';
  var Unit = Kotlin.kotlin.Unit;
  var ensureNotNull = Kotlin.ensureNotNull;
  var throwCCE = Kotlin.throwCCE;
  var StageGL_init = createjs.StageGL;
  var Ticker$Companion = createjs.Ticker;
  var IntRange = Kotlin.kotlin.ranges.IntRange;
  var Container = createjs.Container;
  var Shape = createjs.Shape;
  var Kind_CLASS = Kotlin.Kind.CLASS;
  var Exception_init = Kotlin.kotlin.Exception_init_pdl1vj$;
  var equals = Kotlin.equals;
  var Rectangle = createjs.Rectangle;
  var Matrix2D = createjs.Matrix2D;
  function main$lambda(it) {
    onLoaded();
    return Unit;
  }
  function main(args) {
    window.addEventListener('load', main$lambda);
  }
  function onLoaded$lambda(closure$stage) {
    return function (eventObj) {
      closure$stage.update(eventObj);
      return Unit;
    };
  }
  function onLoaded() {
    var tmp$;
    var canvas = Kotlin.isType(tmp$ = ensureNotNull(document.getElementById('canvas')), HTMLCanvasElement) ? tmp$ : throwCCE();
    var stage = new StageGL_init(canvas);
    canvas.width = window.innerWidth;
    canvas.height = window.innerHeight;
    stage.updateViewport(window.innerWidth, window.innerHeight);
    overrideRenderLoop(stage);
    Ticker$Companion.on('tick', onLoaded$lambda(stage));
    var tmp$_0;
    tmp$_0 = (new IntRange(1, 10)).iterator();
    while (tmp$_0.hasNext()) {
      var element = tmp$_0.next();
      var tmp$_1;
      tmp$_1 = (new IntRange(1, 10)).iterator();
      while (tmp$_1.hasNext()) {
        var element_0 = tmp$_1.next();
        var circle = createCircle(element * 40.0, element_0 * 40.0, (element + element_0 | 0) % 2 !== 0);
        stage.addChild(circle);
      }
    }
    var tmp$_2;
    tmp$_2 = (new IntRange(1, 10)).iterator();
    while (tmp$_2.hasNext()) {
      var element_1 = tmp$_2.next();
      var tmp$_3;
      tmp$_3 = (new IntRange(1, 10)).iterator();
      while (tmp$_3.hasNext()) {
        var element_2 = tmp$_3.next();
        var circle_0 = createCircle(500 + element_1 * 40.0, element_2 * 40.0, (element_1 + element_2 | 0) % 2 !== 0);
        circle_0.flatten = 'circle';
        stage.addChild(circle_0);
      }
    }
  }
  function createCircle(x, y, additive) {
    var parent = new Container();
    var circle = new Shape();
    parent.addChild(circle);
    circle.graphics.beginFill('#aa0000').drawCircle(0, 0, 25);
    circle.x = x;
    circle.y = y;
    circle.cache(-25, -25, 50, 50);
    if (additive)
      parent.compositeOperation = 'lighter';
    return parent;
  }
  function CommandBuffer() {
    this.commands_0 = [];
    this.item_0 = [];
    this.totalCommands_0 = 0;
    this.counter_0 = 0;
  }
  CommandBuffer.prototype.clear = function () {
    this.totalCommands_0 = 0;
    this.counter_0 = 0;
  };
  CommandBuffer.prototype.consume = function () {
    this.counter_0 = this.counter_0 + 1 | 0;
  };
  CommandBuffer.prototype.consume_70ax90$ = function (action) {
    action(this.commands_0[this.counter_0], this.item_0[this.counter_0]);
    this.counter_0 = this.counter_0 + 1 | 0;
  };
  CommandBuffer.prototype.consumeAll_70ax90$ = function (action) {
    var tmp$, tmp$_0;
    tmp$ = this.counter_0;
    tmp$_0 = this.totalCommands_0;
    for (var i = tmp$; i < tmp$_0; i++)
      action(this.commands_0[i], this.item_0[i]);
    this.counter_0 = this.totalCommands_0;
  };
  Object.defineProperty(CommandBuffer.prototype, 'nextCommand', {
    get: function () {
      return this.commands_0[this.counter_0];
    }
  });
  Object.defineProperty(CommandBuffer.prototype, 'nextItem', {
    get: function () {
      return this.item_0[this.counter_0];
    }
  });
  CommandBuffer.prototype.add_vux3hl$ = function (command, _item) {
    this.commands_0[this.totalCommands_0] = command;
    this.item_0[this.totalCommands_0] = _item;
    this.totalCommands_0 = this.totalCommands_0 + 1 | 0;
  };
  Object.defineProperty(CommandBuffer.prototype, 'size', {
    get: function () {
      return this.totalCommands_0 - this.counter_0 | 0;
    }
  });
  CommandBuffer.$metadata$ = {
    kind: Kind_CLASS,
    simpleName: 'CommandBuffer',
    interfaces: []
  };
  function CommandBufferGroup() {
    this.commandGroups = [];
    this.totalGroups_0 = 0;
  }
  CommandBufferGroup.prototype.clear = function () {
    this.totalGroups_0 = 0;
  };
  CommandBufferGroup.prototype.add_har48a$ = function (buffer) {
    this.commandGroups[this.totalGroups_0] = buffer;
    this.totalGroups_0 = this.totalGroups_0 + 1 | 0;
  };
  Object.defineProperty(CommandBufferGroup.prototype, 'size', {
    get: function () {
      return this.totalGroups_0;
    }
  });
  CommandBufferGroup.$metadata$ = {
    kind: Kind_CLASS,
    simpleName: 'CommandBufferGroup',
    interfaces: []
  };
  function CommandBufferPool() {
    this.bufferGroups_0 = {};
    this.buffers_0 = [];
    this.totalBuffers_0 = 0;
    this.counter_0 = 0;
  }
  CommandBufferPool.prototype.clear = function () {
    this.bufferGroups_0 = {};
    this.counter_0 = 0;
  };
  CommandBufferPool.prototype.hasGroup_61zpoe$ = function (groupId) {
    return this.bufferGroups_0[groupId] != undefined;
  };
  CommandBufferPool.prototype.getBufferGroup_61zpoe$ = function (groupId) {
    return this.bufferGroups_0[groupId];
  };
  CommandBufferPool.prototype.get_61zpoe$ = function (groupId) {
    if (groupId === void 0)
      groupId = '';
    var tmp$;
    if (this.counter_0 === this.totalBuffers_0) {
      var newBuffer = new CommandBuffer();
      this.buffers_0[this.counter_0] = newBuffer;
      this.counter_0 = this.counter_0 + 1 | 0;
      this.totalBuffers_0 = this.totalBuffers_0 + 1 | 0;
      tmp$ = newBuffer;
    }
     else {
      var buffer = this.buffers_0[this.counter_0];
      buffer.clear();
      this.counter_0 = this.counter_0 + 1 | 0;
      tmp$ = buffer;
    }
    var newBuffer_0 = tmp$;
    if (groupId.length > 0) {
      if (this.bufferGroups_0[groupId] == undefined)
        this.bufferGroups_0[groupId] = new CommandBufferGroup();
      var asd = this.bufferGroups_0[groupId];
      asd.add_har48a$(newBuffer_0);
    }
    return newBuffer_0;
  };
  CommandBufferPool.$metadata$ = {
    kind: Kind_CLASS,
    simpleName: 'CommandBufferPool',
    interfaces: []
  };
  var draw;
  var set_mask;
  var rem_mask;
  var set_composite;
  var do_flatten;
  var bufferPool;
  function overrideRenderLoop$lambda(this$overrideRenderLoop) {
    return function (container, concatMtx, concatAlpha, ignoreCache) {
      if (!this$overrideRenderLoop._directDraw)
        throw Exception_init('this render loop works only on directdraw=true configuration ');
      var mainBuffer = bufferPool.get_61zpoe$();
      getTransformMatrix(container, concatMtx);
      _searchInRenderTree(this$overrideRenderLoop, container, concatAlpha, mainBuffer);
      this$overrideRenderLoop._renderMode = 'source-over';
      this$overrideRenderLoop._renderModeLazy = 'source-over';
      _executeFrameActions(this$overrideRenderLoop, mainBuffer);
      bufferPool.clear();
      return Unit;
    };
  }
  function overrideRenderLoop($receiver) {
    var old = $receiver._appendToBatch;
    var new_0 = overrideRenderLoop$lambda($receiver);
    $receiver._appendToBatch = new_0;
  }
  function _executeFrameActions$lambda(this$_executeFrameActions, closure$gl) {
    return function (command, item) {
      var tmp$;
      switch (command) {
        case 0:
          var useCache = item.cacheCanvas != undefined;
          if ((this$_executeFrameActions._batchVertexCount + StageGL_init.INDICIES_PER_CARD | 0) > this$_executeFrameActions._maxBatchVertexCount) {
            this$_executeFrameActions.batchReason = 'vertexOverflow';
            this$_executeFrameActions._renderBatch();
          }

          var frame = item._webGLRenderStyle == 1 ? item.spriteSheet.getFrame(item.currentFrame) : null;
          if (item._webGLRenderStyle == 2 || useCache)
            tmp$ = !useCache ? item.image : item.cacheCanvas;
          else if (item._webGLRenderStyle == 1)
            tmp$ = frame != null ? frame.image : null;
          else
            tmp$ = null;
          var image = tmp$;
          if (image != null) {
            var texture = getTexture(this$_executeFrameActions, image, closure$gl);
            if (!equals(texture, undefined)) {
              image._drawID = this$_executeFrameActions._drawID;
              appendGeometry(this$_executeFrameActions, item, useCache, image, frame, item._myGlMtx, texture._activeIndex, item.alpha, item.concatAlpha);
              this$_executeFrameActions._batchVertexCount = this$_executeFrameActions._batchVertexCount + StageGL_init.INDICIES_PER_CARD | 0;
            }
          }

          break;
        case 1:
          this$_executeFrameActions.batchReason = 'setMask';
          this$_executeFrameActions._renderBatch();
          closure$gl.enable(WebGLRenderingContext.SCISSOR_TEST);
          closure$gl.scissor(item.x, item.y, item.width, item.height);
          break;
        case 2:
          this$_executeFrameActions.batchReason = 'removeMask';
          this$_executeFrameActions._renderBatch();
          closure$gl.disable(WebGLRenderingContext.SCISSOR_TEST);
          break;
        case 3:
          this$_executeFrameActions._renderModeLazy = item;
          this$_executeFrameActions._applyRenderMode();
          break;
        case 4:
          _executeFrameActions(this$_executeFrameActions, regroupCommands(this$_executeFrameActions, item));
          break;
      }
      return Unit;
    };
  }
  function _executeFrameActions($receiver, mainGroup) {
    var gl = $receiver._webGLContext;
    mainGroup.consumeAll_70ax90$(_executeFrameActions$lambda($receiver, gl));
  }
  function regroupCommands$lambda(closure$out) {
    return function (com, item) {
      closure$out.add_vux3hl$(com, item);
      return Unit;
    };
  }
  function regroupCommands($receiver, allGroups) {
    var out = new CommandBuffer();
    var remaining = allGroups.commandGroups;
    var todo = allGroups.size;
    var done = 0;
    var i;
    var entryComposite = $receiver._renderMode;
    var currentComposite = $receiver._renderMode;
    while (done < todo) {
      i = 0;
      while (i < (todo - done | 0)) {
        var it = remaining[i];
        while (true) {
          var command = it.nextCommand;
          if (command === set_composite) {
            currentComposite = it.nextItem;
            i = i + 1 | 0;
            break;
          }
          if (command !== draw)
            throw Exception_init('only draw/mask commands inside a flatten section');
          it.consume_70ax90$(regroupCommands$lambda(out));
          if (it.size === 0) {
            done = done + 1 | 0;
            remaining[i] = remaining[todo - done | 0];
            break;
          }
        }
      }
      if (done === todo)
        break;
      out.add_vux3hl$(set_composite, currentComposite);
      i = 0;
      while (i < (todo - done | 0)) {
        var it_0 = remaining[i];
        if (equals(currentComposite, it_0.nextItem)) {
          it_0.consume();
          if (it_0.size === 0) {
            done = done + 1 | 0;
            remaining[i] = remaining[todo - done | 0];
            continue;
          }
        }
        i = i + 1 | 0;
      }
    }
    if (!equals(currentComposite, entryComposite))
      out.add_vux3hl$(set_composite, entryComposite);
    return out;
  }
  function _searchInRenderTree($receiver, container, concatAlpha, commandBuffer) {
    var cMtx = container._myGlMtx;
    var applyMask = applyScissorsMask($receiver, container, cMtx, commandBuffer);
    var previousRenderMode = $receiver._renderModeLazy;
    if (container.compositeOperation != null) {
      $receiver._renderModeLazy = container.compositeOperation;
    }
    var $receiver_0 = container.children;
    var tmp$;
    for (tmp$ = 0; tmp$ !== $receiver_0.length; ++tmp$) {
      var element = $receiver_0[tmp$];
      if (element.visible && concatAlpha > 0.0035) {
        var useCache = element.cacheCanvas != undefined;
        if (!useCache && element.children != undefined) {
          if (element.children.length > 0) {
            if (element._updateState)
              element._updateState();
            getTransformMatrix(element, cMtx);
            var groupForChild = processFlatten(element, commandBuffer);
            var previousLazy = $receiver._renderModeLazy;
            var previousMode = $receiver._renderMode;
            _searchInRenderTree($receiver, element, element.alpha * concatAlpha, groupForChild);
            if (groupForChild !== commandBuffer) {
              $receiver._renderModeLazy = previousLazy;
              $receiver._renderMode = previousMode;
            }
          }
        }
         else {
          getTransformMatrix(element, cMtx);
          var containerRenderMode = $receiver._renderModeLazy;
          if (element.compositeOperation != null) {
            $receiver._renderModeLazy = element.compositeOperation;
          }
          if (!equals($receiver._renderMode, $receiver._renderModeLazy)) {
            $receiver._renderMode = $receiver._renderModeLazy;
            commandBuffer.add_vux3hl$(set_composite, $receiver._renderModeLazy);
          }
          element.concatAlpha = concatAlpha;
          commandBuffer.add_vux3hl$(draw, element);
          $receiver._renderModeLazy = containerRenderMode;
        }
      }
    }
    $receiver._renderModeLazy = previousRenderMode;
    if (applyMask) {
      removeScissorMask($receiver, commandBuffer);
    }
  }
  function processFlatten(item, commandGroup) {
    var tmp$;
    var flatten = item.flatten;
    if (flatten == undefined)
      tmp$ = commandGroup;
    else {
      var new_0 = !bufferPool.hasGroup_61zpoe$(flatten);
      var newGroup = bufferPool.get_61zpoe$(flatten);
      if (new_0)
        commandGroup.add_vux3hl$(do_flatten, bufferPool.getBufferGroup_61zpoe$(flatten));
      tmp$ = newGroup;
    }
    return tmp$;
  }
  function getTexture($receiver, image, gl) {
    var tmp$;
    if (image._storeID === undefined) {
      tmp$ = $receiver._loadTextureImage(gl, image);
    }
     else {
      var temp = $receiver._textureDictionary[image._storeID];
      if (temp == undefined) {
        if ($receiver.vocalDebug) {
          console.log('Image source should not be lookup a non existent texture, please report a bug.');
        }
      }
       else {
        if (temp._batchID !== $receiver._batchID) {
          $receiver._insertTextureInBatch(gl, temp);
        }
      }
      tmp$ = temp;
    }
    var texture = tmp$;
    return texture;
  }
  function removeScissorMask($receiver, mainGroup) {
    $receiver._masks.pop();
    if ($receiver._masks.length === 0) {
      mainGroup.add_vux3hl$(rem_mask, null);
    }
     else {
      var combined = $receiver._getCombinedMasks();
      mainGroup.add_vux3hl$(set_mask, new Rectangle(combined.x, combined.y, combined.width, combined.height));
    }
  }
  function appendGeometry($receiver, item, useCache, image, frame, iMtx, texIndex, itemAlpha, concatAlpha) {
    var tmp$, tmp$_0, tmp$_1, tmp$_2, tmp$_3, tmp$_4, tmp$_5, tmp$_6, tmp$_7, tmp$_8, tmp$_9, tmp$_10, tmp$_11;
    var subL = 0.0;
    var subT = 0.0;
    var subR = 0.0;
    var subB = 0.0;
    var src;
    var uvRect = null;
    if (item._webGLRenderStyle === 2 || useCache) {
      if (!useCache && item.sourceRect != null && item.sourceRect != undefined) {
        if (item._uvRect == undefined) {
          item._uvRect = {};
        }
        src = item.sourceRect;
        var imgWidth = image.width;
        var imgHeight = image.height;
        uvRect = item._uvRect;
        uvRect.t = 1 - (typeof (tmp$ = src.y / imgHeight) === 'number' ? tmp$ : throwCCE());
        uvRect.l = src.x / imgWidth;
        uvRect.b = 1 - (typeof (tmp$_0 = (src.y + src.height) / imgHeight) === 'number' ? tmp$_0 : throwCCE());
        uvRect.r = (src.x + src.width) / imgWidth;
        subL = 0.0;
        subT = 0.0;
        subR = src.width + subL;
        subB = src.height + subT;
      }
       else {
        uvRect = StageGL_init.UV_RECT;
        if (!useCache) {
          subL = 0.0;
          subT = 0.0;
          subR = image.width + subL;
          subB = image.height + subT;
        }
         else {
          src = item.bitmapCache;
          subL = src.x + src._filterOffX / src.scale;
          subT = src.y + src._filterOffY / src.scale;
          subR = src._drawWidth / src.scale + subL;
          subB = src._drawHeight / src.scale + subT;
        }
      }
    }
     else if (item._webGLRenderStyle === 1) {
      var rect = Kotlin.isType(tmp$_1 = frame.rect, Rectangle) ? tmp$_1 : throwCCE();
      uvRect = frame.uvRect;
      if (uvRect == null || uvRect == undefined) {
        uvRect = StageGL_init.buildUVRects(item.spriteSheet, item.currentFrame, false);
      }
      subL = -frame.regX;
      subT = -frame.regY;
      subR = rect.width - (typeof (tmp$_2 = frame.regX) === 'number' ? tmp$_2 : throwCCE());
      subB = rect.height - (typeof (tmp$_3 = frame.regY) === 'number' ? tmp$_3 : throwCCE());
    }
    var cfg = $receiver._activeConfig;
    var vpos = cfg.position.array;
    var uvs = cfg.uv.array;
    var texI = cfg.texture.array;
    var alphas = cfg.alpha.array;
    var spacing = typeof (tmp$_4 = cfg.position.spacing) === 'number' ? tmp$_4 : throwCCE();
    var vtxOff = Kotlin.imul($receiver._batchVertexCount, spacing) + (typeof (tmp$_5 = cfg.position.offset) === 'number' ? tmp$_5 : throwCCE()) | 0;
    vpos[vtxOff] = subL * iMtx.a + subT * iMtx.c + iMtx.tx;
    vpos[vtxOff + 1 | 0] = subL * iMtx.b + subT * iMtx.d + iMtx.ty;
    vtxOff = vtxOff + spacing | 0;
    vpos[vtxOff] = subL * iMtx.a + subB * iMtx.c + iMtx.tx;
    vpos[vtxOff + 1 | 0] = subL * iMtx.b + subB * iMtx.d + iMtx.ty;
    vtxOff = vtxOff + spacing | 0;
    vpos[vtxOff] = subR * iMtx.a + subT * iMtx.c + iMtx.tx;
    vpos[vtxOff + 1 | 0] = subR * iMtx.b + subT * iMtx.d + iMtx.ty;
    vtxOff = vtxOff + spacing | 0;
    vpos[vtxOff] = subL * iMtx.a + subB * iMtx.c + iMtx.tx;
    vpos[vtxOff + 1 | 0] = subL * iMtx.b + subB * iMtx.d + iMtx.ty;
    vtxOff = vtxOff + spacing | 0;
    vpos[vtxOff] = subR * iMtx.a + subT * iMtx.c + iMtx.tx;
    vpos[vtxOff + 1 | 0] = subR * iMtx.b + subT * iMtx.d + iMtx.ty;
    vtxOff = vtxOff + spacing | 0;
    vpos[vtxOff] = subR * iMtx.a + subB * iMtx.c + iMtx.tx;
    vpos[vtxOff + 1 | 0] = subR * iMtx.b + subB * iMtx.d + iMtx.ty;
    spacing = typeof (tmp$_6 = cfg.uv.spacing) === 'number' ? tmp$_6 : throwCCE();
    var uvOff = Kotlin.imul($receiver._batchVertexCount, spacing) + (typeof (tmp$_7 = cfg.uv.offset) === 'number' ? tmp$_7 : throwCCE()) | 0;
    uvs[uvOff] = uvRect.l;
    uvs[uvOff + 1 | 0] = uvRect.t;
    uvOff = uvOff + spacing | 0;
    uvs[uvOff] = uvRect.l;
    uvs[uvOff + 1 | 0] = uvRect.b;
    uvOff = uvOff + spacing | 0;
    uvs[uvOff] = uvRect.r;
    uvs[uvOff + 1 | 0] = uvRect.t;
    uvOff = uvOff + spacing | 0;
    uvs[uvOff] = uvRect.l;
    uvs[uvOff + 1 | 0] = uvRect.b;
    uvOff = uvOff + spacing | 0;
    uvs[uvOff] = uvRect.r;
    uvs[uvOff + 1 | 0] = uvRect.t;
    uvOff = uvOff + spacing | 0;
    uvs[uvOff] = uvRect.r;
    uvs[uvOff + 1 | 0] = uvRect.b;
    spacing = typeof (tmp$_8 = cfg.texture.spacing) === 'number' ? tmp$_8 : throwCCE();
    var texOff = Kotlin.imul($receiver._batchVertexCount, spacing) + (typeof (tmp$_9 = cfg.texture.offset) === 'number' ? tmp$_9 : throwCCE()) | 0;
    texI[texOff] = texIndex;
    texOff = texOff + spacing | 0;
    texI[texOff] = texIndex;
    texOff = texOff + spacing | 0;
    texI[texOff] = texIndex;
    texOff = texOff + spacing | 0;
    texI[texOff] = texIndex;
    texOff = texOff + spacing | 0;
    texI[texOff] = texIndex;
    texOff = texOff + spacing | 0;
    texI[texOff] = texIndex;
    spacing = typeof (tmp$_10 = cfg.alpha.spacing) === 'number' ? tmp$_10 : throwCCE();
    var aOff = Kotlin.imul($receiver._batchVertexCount, spacing) + (typeof (tmp$_11 = cfg.alpha.offset) === 'number' ? tmp$_11 : throwCCE()) | 0;
    alphas[aOff] = itemAlpha * concatAlpha;
    aOff = aOff + spacing | 0;
    alphas[aOff] = itemAlpha * concatAlpha;
    aOff = aOff + spacing | 0;
    alphas[aOff] = itemAlpha * concatAlpha;
    aOff = aOff + spacing | 0;
    alphas[aOff] = itemAlpha * concatAlpha;
    aOff = aOff + spacing | 0;
    alphas[aOff] = itemAlpha * concatAlpha;
    aOff = aOff + spacing | 0;
    alphas[aOff] = itemAlpha * concatAlpha;
  }
  function applyScissorsMask($receiver, container, cMtx, mainGroup) {
    var applyMask = false;
    if (container.mask !== null) {
      var isRotated = cMtx.c !== 0.0 || cMtx.b !== 0.0;
      if (isRotated) {
        console.warn('mask cannot be added to rotated objects');
      }
      applyMask = !isRotated;
      if (applyMask) {
        var maskBounds = ensureNotNull(container.mask).getBounds();
        var point1 = cMtx.transformPoint(maskBounds.x, maskBounds.y);
        var point2 = cMtx.transformPoint(maskBounds.x + maskBounds.width, maskBounds.y + maskBounds.height);
        var width = point2.x - point1.x;
        var height = point2.y - point1.y;
        var x = point1.x;
        var y = $receiver.canvas.height - point1.y - height;
        if (x < 0) {
          width += x;
          x = 0.0;
        }
        if (width < 0)
          width = 0.0;
        if (y < 0) {
          height += y;
          y = 0.0;
        }
        if (height < 0)
          height = 0.0;
        var rectangle = new Rectangle(x, y, width, height);
        $receiver._masks.push(rectangle);
        var combined = $receiver._getCombinedMasks();
        mainGroup.add_vux3hl$(set_mask, new Rectangle(combined.x, combined.y, combined.width, combined.height));
      }
    }
    return applyMask;
  }
  function getTransformMatrix(item, concatMtx) {
    if (equals(item._myGlMtx, undefined))
      item._myGlMtx = new Matrix2D();
    var cMtx = item._myGlMtx;
    cMtx.copy(concatMtx);
    if (item.transformMatrix != null) {
      cMtx.appendMatrix(ensureNotNull(item.transformMatrix));
    }
     else {
      cMtx.appendTransform(item.x, item.y, item.scaleX, item.scaleY, item.rotation, item.skewX, item.skewY, item.regX, item.regY);
    }
    return cMtx;
  }
  _.main_kand9s$ = main;
  _.onLoaded = onLoaded;
  var package$mywebgl = _.mywebgl || (_.mywebgl = {});
  package$mywebgl.CommandBuffer = CommandBuffer;
  package$mywebgl.CommandBufferGroup = CommandBufferGroup;
  package$mywebgl.CommandBufferPool = CommandBufferPool;
  Object.defineProperty(package$mywebgl, 'draw', {
    get: function () {
      return draw;
    }
  });
  Object.defineProperty(package$mywebgl, 'set_mask', {
    get: function () {
      return set_mask;
    }
  });
  Object.defineProperty(package$mywebgl, 'rem_mask', {
    get: function () {
      return rem_mask;
    }
  });
  Object.defineProperty(package$mywebgl, 'set_composite', {
    get: function () {
      return set_composite;
    }
  });
  Object.defineProperty(package$mywebgl, 'do_flatten', {
    get: function () {
      return do_flatten;
    }
  });
  Object.defineProperty(package$mywebgl, 'bufferPool', {
    get: function () {
      return bufferPool;
    }
  });
  package$mywebgl.overrideRenderLoop_9tkvdh$ = overrideRenderLoop;
  draw = 0;
  set_mask = 1;
  rem_mask = 2;
  set_composite = 3;
  do_flatten = 4;
  bufferPool = new CommandBufferPool();
  main([]);
  Kotlin.defineModule('kotlin_webgl_loop', _);
  return _;
}(typeof kotlin_webgl_loop === 'undefined' ? {} : kotlin_webgl_loop, kotlin);
