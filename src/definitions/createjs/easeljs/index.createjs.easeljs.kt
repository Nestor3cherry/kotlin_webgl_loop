@file:Suppress("INTERFACE_WITH_SUPERCLASS", "OVERRIDING_FINAL_MEMBER", "RETURN_TYPE_MISMATCH_ON_OVERRIDE", "CONFLICTING_OVERLOADS")
@file:JsQualifier("createjs")
package createjs


import org.khronos.webgl.WebGLRenderingContext
import org.khronos.webgl.WebGLTexture
import org.w3c.dom.*

external open class AlphaMapFilter : Filter {
    constructor(alphaMap: HTMLImageElement)
    constructor(alphaMap: HTMLCanvasElement)
    open var alphaMap: dynamic /* HTMLImageElement | HTMLCanvasElement */ = definedExternally
    override fun clone(): AlphaMapFilter = definedExternally
}
external open class AlphaMaskFilter : Filter {
    //constructor(mask: HTMLImageElement)
    constructor(mask: HTMLCanvasElement)
    open var mask: dynamic /* HTMLImageElement | HTMLCanvasElement */ = definedExternally
    override fun clone(): AlphaMaskFilter = definedExternally
}
external open class Bitmap : DisplayObject {
    constructor(imageOrUrl: HTMLImageElement)
    constructor(imageOrUrl: HTMLCanvasElement)
    constructor(imageOrUrl: HTMLVideoElement)
    constructor(any: dynamic)
    constructor(imageOrUrl: String)
    open var image: HTMLImageElement /* HTMLImageElement | HTMLCanvasElement | HTMLVideoElement */ = definedExternally
    open var sourceRect: Rectangle = definedExternally
    override fun clone(): Bitmap = definedExternally
}
external open class ScaleBitmap : DisplayObject {
    constructor(imageOrUrl: HTMLImageElement, scale9Grid: Rectangle)
    constructor(imageOrUrl: HTMLCanvasElement, scale9Grid: Rectangle)
    constructor(imageOrUrl: HTMLVideoElement, scale9Grid: Rectangle)
    constructor(imageOrUrl: Any, scale9Grid: Rectangle)
    constructor(imageOrUrl: String, scale9Grid: Rectangle)
    open var image: dynamic /* HTMLImageElement | HTMLCanvasElement | HTMLVideoElement */ = definedExternally
    open var sourceRect: Rectangle = definedExternally
    open var drawWidth: Float = definedExternally
    open var drawHeight: Float = definedExternally
    open var scale9Grid: Rectangle = definedExternally
    override var snapToPixel: Boolean = definedExternally
    open fun setDrawSize(newWidth: Number, newHeight: Number): Unit = definedExternally
    override fun clone(): ScaleBitmap = definedExternally
}

/*
external open class BitmapText(text: String? = definedExternally /* null */, spriteSheet: SpriteSheet? = definedExternally /* null */) : DisplayObject {
    open var letterSpacing: Float = definedExternally
    open var lineHeight: Float = definedExternally
    open var spaceWidth: Float = definedExternally
    open var spriteSheet: SpriteSheet = definedExternally
    open var text: String = definedExternally
    companion object {
        var maxPoolSize: Float = definedExternally
    }
}
*/
external open class BlurFilter(blurX: Number? = definedExternally /* null */, blurY: Number? = definedExternally /* null */, quality: Number? = definedExternally /* null */) : Filter {
    open var blurX: Float = definedExternally
    open var blurY: Float = definedExternally
    open var quality: Float = definedExternally
    override fun clone(): BlurFilter = definedExternally
}
external open class ButtonHelper {
    constructor(target: Sprite, outLabel: String? = definedExternally /* null */, overLabel: String? = definedExternally /* null */, downLabel: String? = definedExternally /* null */, play: Boolean? = definedExternally /* null */, hitArea: DisplayObject? = definedExternally /* null */, hitLabel: String? = definedExternally /* null */)
    constructor(target: MovieClip, outLabel: String? = definedExternally /* null */, overLabel: String? = definedExternally /* null */, downLabel: String? = definedExternally /* null */, play: Boolean? = definedExternally /* null */, hitArea: DisplayObject? = definedExternally /* null */, hitLabel: String? = definedExternally /* null */)
    open var downLabel: dynamic /* String | Number */ = definedExternally
    open var outLabel: dynamic /* String | Number */ = definedExternally
    open var overLabel: dynamic /* String | Number */ = definedExternally
    open var play: Boolean = definedExternally
    open var target: dynamic /* MovieClip | Sprite */ = definedExternally
    open var enabled: Boolean = definedExternally
    open fun setEnabled(value: Boolean): Unit = definedExternally
    open fun getEnabled(): Boolean = definedExternally
    override fun toString(): String = definedExternally
}
external open class ColorFilter(redMultiplier: Number? = definedExternally /* null */, greenMultiplier: Number? = definedExternally /* null */, blueMultiplier: Number? = definedExternally /* null */, alphaMultiplier: Number? = definedExternally /* null */, redOffset: Number? = definedExternally /* null */, greenOffset: Number? = definedExternally /* null */, blueOffset: Number? = definedExternally /* null */, alphaOffset: Number? = definedExternally /* null */) : Filter {
    open var alphaMultiplier: Float = definedExternally
    open var alphaOffset: Float = definedExternally
    open var blueMultiplier: Float = definedExternally
    open var blueOffset: Float = definedExternally
    open var greenMultiplier: Float = definedExternally
    open var greenOffset: Float = definedExternally
    open var redMultiplier: Float = definedExternally
    open var redOffset: Float = definedExternally
    override fun clone(): ColorFilter = definedExternally
}
external open class ColorMatrix(brightness: Number? = definedExternally /* null */, contrast: Number? = definedExternally /* null */, saturation: Number? = definedExternally /* null */, hue: Number? = definedExternally /* null */) {
    open fun adjustBrightness(value: Number): ColorMatrix = definedExternally
    open fun adjustColor(brightness: Number, contrast: Number, saturation: Number, hue: Number): ColorMatrix = definedExternally
    open fun adjustContrast(value: Number): ColorMatrix = definedExternally
    open fun adjustHue(value: Number): ColorMatrix = definedExternally
    open fun adjustSaturation(value: Number): ColorMatrix = definedExternally
    open fun clone(): ColorMatrix = definedExternally
    open fun concat(vararg matrix: Number): ColorMatrix = definedExternally
    open fun concat(matrix: ColorMatrix): ColorMatrix = definedExternally
    open fun copy(vararg matrix: Number): ColorMatrix = definedExternally
    open fun copy(matrix: ColorMatrix): ColorMatrix = definedExternally
    open fun reset(): ColorMatrix = definedExternally
    open fun setColor(brightness: Number, contrast: Number, saturation: Number, hue: Number): ColorMatrix = definedExternally
    open fun toArray(): Array<Number> = definedExternally
    override fun toString(): String = definedExternally
}
external open class ColorMatrixFilter : Filter {
    constructor(matrix: Array<Number>)
    constructor(matrix: ColorMatrix)
    open var matrix: dynamic /* Array<Number> | ColorMatrix */ = definedExternally
    override fun clone(): ColorMatrixFilter = definedExternally
}
external open class Container : DisplayObject {
    open var children: Array<DisplayObject> = definedExternally
    open var mouseChildren: Boolean = definedExternally
    open var numChildren: Int = definedExternally
    open var tickChildren: Boolean = definedExternally

    //open fun <T : DisplayObject> _addChild(child: T): T = definedExternally
    //open fun <T : DisplayObject> addChild(child0: DisplayObject, lastChild: T): T = definedExternally
    //open fun <T : DisplayObject> addChild(child0: DisplayObject, child1: DisplayObject, lastChild: T): T = definedExternally
    //open fun <T : DisplayObject> addChild(child0: DisplayObject, child1: DisplayObject, child2: DisplayObject, lastChild: T): T = definedExternally
    open fun addChild(vararg children: DisplayObject): DisplayObject = definedExternally
    open fun <T : DisplayObject> addChildAt(child: T, index: Number): T = definedExternally
    open fun <T : DisplayObject> addChildAt(child0: DisplayObject, lastChild: T, index: Number): T = definedExternally
    open fun <T : DisplayObject> addChildAt(child0: DisplayObject, child1: DisplayObject, lastChild: T, index: Number): T = definedExternally
    open fun addChildAt(vararg childOrIndex: dynamic /* DisplayObject | Number */): DisplayObject = definedExternally
    open fun clone(recursive: Boolean? = definedExternally /* null */): Container = definedExternally
    open fun contains(child: DisplayObject): Boolean = definedExternally
    open fun getChildAt(index: Number): DisplayObject = definedExternally
    open fun getChildByName(name: String): DisplayObject = definedExternally
    open fun getChildIndex(child: DisplayObject): Float = definedExternally
    //Deprecated, will be removed on CreateJS 1.1
    //open fun getNumChildren(): Int = definedExternally
    open fun getObjectsUnderPoint(x: Number, y: Number, mode: Number): Array<DisplayObject> = definedExternally
    open fun getObjectUnderPoint(x: Number, y: Number, mode: Number): DisplayObject = definedExternally
    open fun removeAllChildren(): Unit = definedExternally
    open fun removeChild(vararg child: DisplayObject): Boolean = definedExternally
    open fun removeChildAt(vararg index: Number): Boolean = definedExternally
    open fun setChildIndex(child: DisplayObject, index: Number): Unit = definedExternally
    open fun sortChildren(sortFunction: (a: DisplayObject, b: DisplayObject) -> Number): Unit = definedExternally
    open fun swapChildren(child1: DisplayObject, child2: DisplayObject): Unit = definedExternally
    open fun swapChildrenAt(index1: Number, index2: Number): Unit = definedExternally
}
external open class DisplayObject : EventDispatcher {
    open var _myGlMtx: Matrix2D = definedExternally
    open var alpha: Float = definedExternally
    open var cacheCanvas: dynamic /* HTMLCanvasElement | Any */ = definedExternally
    open var cacheID: Float = definedExternally
    open var compositeOperation: String? = definedExternally
    open var cursor: String = definedExternally
    open var filters: Array<Filter>? = definedExternally
    open var hitArea: DisplayObject = definedExternally
    open var id: Float = definedExternally
    open var mask: Shape? = definedExternally
    open var mouseEnabled: Boolean = definedExternally
    open var name: String = definedExternally
    open var parent: Container = definedExternally
    open var regX: Float = definedExternally
    open var regY: Float = definedExternally
    open var rotation: Float = definedExternally
    open var scaleX: Float = definedExternally
    open var scaleY: Float = definedExternally
    //------------
    //commented because Animate override this funcion, causing a kotlin error (missing override), anyways we don't need this function
    //open var shadow: Shadow = definedExternally
    //------------
    open var skewX: Float = definedExternally
    open var skewY: Float = definedExternally
    open var snapToPixel: Boolean = definedExternally
    open var stage: Stage? = definedExternally
    open var tickEnabled: Boolean = definedExternally
    open var transformMatrix: Matrix2D? = definedExternally
    open var _webGLRenderStyle: Int = definedExternally
    open var visible: Boolean = definedExternally
    open var x: Float = definedExternally
    open var y: Float = definedExternally
    open fun cache(x: Number, y: Number, width: Number, height: Number, scale: Number? = definedExternally /* null */, options: Any = definedExternally): Unit = definedExternally
    open fun clone(): DisplayObject = definedExternally
    open fun draw(ctx: CanvasRenderingContext2D, ignoreCache: Boolean? = definedExternally /* null */): Boolean = definedExternally
    open fun getBounds(): Rectangle = definedExternally
    open fun getCacheDataURL(): String = definedExternally
    open fun getConcatenatedDisplayProps(props: DisplayProps? = definedExternally /* null */): DisplayProps = definedExternally
    open fun getConcatenatedMatrix(mtx: Matrix2D? = definedExternally /* null */): Matrix2D = definedExternally
    open fun getMatrix(matrix: Matrix2D? = definedExternally /* null */): Matrix2D = definedExternally
    open fun getStage(): Stage? = definedExternally
    open fun getTransformedBounds(): Rectangle = definedExternally
    open fun globalToLocal(x: Number, y: Number, pt: Point? = definedExternally /* null */): Point = definedExternally
    //open fun globalToLocal(x: Number, y: Number, pt: Any? = definedExternally /* null */): Point = definedExternally
    open fun hitTest(x: Number, y: Number): Boolean = definedExternally
    open fun isVisible(): Boolean = definedExternally
    open fun localToGlobal(x: Number, y: Number, pt: Point? = definedExternally /* null */): Point = definedExternally
    //open fun localToGlobal(x: Number, y: Number, pt: Any? = definedExternally /* null */): Point = definedExternally
    open fun localToLocal(x: Number, y: Number, target: DisplayObject, pt: Point? = definedExternally /* null */): Point = definedExternally
    open fun localToLocal(x: Number, y: Number, target: DisplayObject, pt: Any? = definedExternally /* null */): Point = definedExternally
    open fun set(props: Any): DisplayObject = definedExternally
    open fun setBounds(x: Number, y: Number, width: Number, height: Number): Unit = definedExternally
    open fun setTransform(x: Number? = definedExternally /* null */, y: Number? = definedExternally /* null */, scaleX: Number? = definedExternally /* null */, scaleY: Number? = definedExternally /* null */, rotation: Number? = definedExternally /* null */, skewX: Number? = definedExternally /* null */, skewY: Number? = definedExternally /* null */, regX: Number? = definedExternally /* null */, regY: Number? = definedExternally /* null */): DisplayObject = definedExternally
    open fun uncache(): Unit = definedExternally
    open fun updateCache(compositeOperation: String? = definedExternally /* null */): Unit = definedExternally
    open fun updateContext(ctx: CanvasRenderingContext2D): Unit = definedExternally
    companion object {
        var suppressCrossDomainErrors: Boolean = definedExternally
    }
}
external open class DisplayProps(visible: Number? = definedExternally /* null */, alpha: Number? = definedExternally /* null */, shadow: Number? = definedExternally /* null */, compositeOperation: Number? = definedExternally /* null */, matrix: Number? = definedExternally /* null */) {
    open var alpha: Float = definedExternally
    open var compositeOperation: String = definedExternally
    open var matrix: Matrix2D = definedExternally
    open var shadow: Shadow = definedExternally
    open var visible: Boolean = definedExternally
    open fun append(visible: Boolean, alpha: Number, shadow: Shadow, compositeOperation: String, matrix: Matrix2D? = definedExternally /* null */): DisplayProps = definedExternally
    open fun clone(): DisplayProps = definedExternally
    open fun identity(): DisplayProps = definedExternally
    open fun prepend(visible: Boolean, alpha: Number, shadow: Shadow, compositeOperation: String, matrix: Matrix2D? = definedExternally /* null */): DisplayProps = definedExternally
    open fun setValues(visible: Boolean? = definedExternally /* null */, alpha: Number? = definedExternally /* null */, shadow: Number? = definedExternally /* null */, compositeOperation: Number? = definedExternally /* null */, matrix: Number? = definedExternally /* null */): DisplayProps = definedExternally
}
external open class DOMElement(htmlElement: HTMLElement) : DisplayObject {
    open var htmlElement: HTMLElement = definedExternally
    override fun clone(): DisplayObject = definedExternally
    override fun set(props: Any): DOMElement = definedExternally
    override fun setTransform(x: Number?, y: Number?, scaleX: Number?, scaleY: Number?, rotation: Number?, skewX: Number?, skewY: Number?, regX: Number?, regY: Number?): DOMElement = definedExternally
}
external open class EaselJS {
    companion object {
        var buildDate: String = definedExternally
        var version: String = definedExternally
    }
}
external open class Filter {
    open fun applyFilter(ctx: CanvasRenderingContext2D, x: Number, y: Number, width: Number, height: Number, targetCtx: CanvasRenderingContext2D? = definedExternally /* null */, targetX: Number? = definedExternally /* null */, targetY: Number? = definedExternally /* null */): Boolean = definedExternally
    open fun clone(): Filter = definedExternally
    open fun getBounds(): Rectangle = definedExternally
    override fun toString(): String = definedExternally
}
external open class Graphics {
    open var command: Any = definedExternally
    open var instructions: Array<Any> = definedExternally
    open fun append(command: Any, clean: Boolean? = definedExternally /* null */): Graphics = definedExternally
    open fun arc(x: Number, y: Number, radius: Number, startAngle: Number, endAngle: Number, anticlockwise: Boolean): Graphics = definedExternally
    open fun arcTo(x1: Number, y1: Number, x2: Number, y2: Number, radius: Number): Graphics = definedExternally
    open fun beginBitmapFill(image: Any, repetition: String? = definedExternally /* null */, matrix: Matrix2D? = definedExternally /* null */): Graphics = definedExternally
    open fun beginBitmapStroke(image: Any, repetition: String? = definedExternally /* null */): Graphics = definedExternally
    open fun beginFill(color: String): Graphics = definedExternally
    open fun beginLinearGradientFill(colors: Array<String>, ratios: Array<Number>, x0: Number, y0: Number, x1: Number, y1: Number): Graphics = definedExternally
    open fun beginLinearGradientStroke(colors: Array<String>, ratios: Array<Number>, x0: Number, y0: Number, x1: Number, y1: Number): Graphics = definedExternally
    open fun beginRadialGradientFill(colors: Array<String>, ratios: Array<Number>, x0: Number, y0: Number, r0: Number, x1: Number, y1: Number, r1: Number): Graphics = definedExternally
    open fun beginRadialGradientStroke(colors: Array<String>, ratios: Array<Number>, x0: Number, y0: Number, r0: Number, x1: Number, y1: Number, r1: Number): Graphics = definedExternally
    open fun beginStroke(color: String): Graphics = definedExternally
    open fun bezierCurveTo(cp1x: Number, cp1y: Number, cp2x: Number, cp2y: Number, x: Number, y: Number): Graphics = definedExternally
    open fun clear(): Graphics = definedExternally
    open fun clone(): Graphics = definedExternally
    open fun closePath(): Graphics = definedExternally
    open fun curveTo(cpx: Number, cpy: Number, x: Number, y: Number): Graphics = definedExternally
    open fun decodePath(str: String): Graphics = definedExternally
    open fun draw(ctx: CanvasRenderingContext2D): Unit = definedExternally
    open fun drawAsPath(ctx: CanvasRenderingContext2D): Unit = definedExternally
    open fun drawCircle(x: Number, y: Number, radius: Number): Graphics = definedExternally
    open fun drawEllipse(x: Number, y: Number, w: Number, h: Number): Graphics = definedExternally
    open fun drawPolyStar(x: Number, y: Number, radius: Number, sides: Number, pointSize: Number, angle: Number): Graphics = definedExternally
    open fun drawRect(x: Number, y: Number, w: Number, h: Number): Graphics = definedExternally
    open fun drawRoundRect(x: Number, y: Number, w: Number, h: Number, radius: Number): Graphics = definedExternally
    open fun drawRoundRectComplex(x: Number, y: Number, w: Number, h: Number, radiusTL: Number, radiusTR: Number, radiusBR: Number, radisBL: Number): Graphics = definedExternally
    open fun endFill(): Graphics = definedExternally
    open fun endStroke(): Graphics = definedExternally
    open fun getInstructions(): Array<Any> = definedExternally
    open fun inject(callback: (data: Any) -> Any, data: Any): Graphics = definedExternally
    open fun isEmpty(): Boolean = definedExternally
    open fun lineTo(x: Number, y: Number): Graphics = definedExternally
    open fun moveTo(x: Number, y: Number): Graphics = definedExternally
    open fun quadraticCurveTo(cpx: Number, cpy: Number, x: Number, y: Number): Graphics = definedExternally
    open fun rect(x: Number, y: Number, w: Number, h: Number): Graphics = definedExternally
    open fun setStrokeStyle(thickness: Number, caps: String? = definedExternally /* null */, joints: String? = definedExternally /* null */, miterLimit: Number? = definedExternally /* null */, ignoreScale: Boolean? = definedExternally /* null */): Graphics = definedExternally
    open fun setStrokeStyle(thickness: Number, caps: String? = definedExternally /* null */, joints: Number? = definedExternally /* null */, miterLimit: Number? = definedExternally /* null */, ignoreScale: Boolean? = definedExternally /* null */): Graphics = definedExternally
    open fun setStrokeStyle(thickness: Number, caps: Number? = definedExternally /* null */, joints: String? = definedExternally /* null */, miterLimit: Number? = definedExternally /* null */, ignoreScale: Boolean? = definedExternally /* null */): Graphics = definedExternally
    open fun setStrokeStyle(thickness: Number, caps: Number? = definedExternally /* null */, joints: Number? = definedExternally /* null */, miterLimit: Number? = definedExternally /* null */, ignoreScale: Boolean? = definedExternally /* null */): Graphics = definedExternally
    open fun setStrokeDash(segments: Array<Number>? = definedExternally /* null */, offset: Number? = definedExternally /* null */): Graphics = definedExternally
    open fun store(): Graphics = definedExternally
    override fun toString(): String = definedExternally
    open fun unstore(): Graphics = definedExternally
    open fun a(x: Number, y: Number, radius: Number, startAngle: Number, endAngle: Number, anticlockwise: Boolean): Graphics = definedExternally
    open fun at(x1: Number, y1: Number, x2: Number, y2: Number, radius: Number): Graphics = definedExternally
    open fun bf(image: Any, repetition: String? = definedExternally /* null */, matrix: Matrix2D? = definedExternally /* null */): Graphics = definedExternally
    open fun bs(image: Any, repetition: String? = definedExternally /* null */): Graphics = definedExternally
    open fun f(color: String): Graphics = definedExternally
    open fun lf(colors: Array<String>, ratios: Array<Number>, x0: Number, y0: Number, x1: Number, y1: Number): Graphics = definedExternally
    open fun ls(colors: Array<String>, ratios: Array<Number>, x0: Number, y0: Number, x1: Number, y1: Number): Graphics = definedExternally
    open fun rf(colors: Array<String>, ratios: Array<Number>, x0: Number, y0: Number, r0: Number, x1: Number, y1: Number, r1: Number): Graphics = definedExternally
    open fun rs(colors: Array<String>, ratios: Array<Number>, x0: Number, y0: Number, r0: Number, x1: Number, y1: Number, r1: Number): Graphics = definedExternally
    open fun s(color: String): Graphics = definedExternally
    open fun bt(cp1x: Number, cp1y: Number, cp2x: Number, cp2y: Number, x: Number, y: Number): Graphics = definedExternally
    open fun c(): Graphics = definedExternally
    open fun cp(): Graphics = definedExternally
    open fun p(str: String): Graphics = definedExternally
    open fun dc(x: Number, y: Number, radius: Number): Graphics = definedExternally
    open fun de(x: Number, y: Number, w: Number, h: Number): Graphics = definedExternally
    open fun dp(x: Number, y: Number, radius: Number, sides: Number, pointSize: Number, angle: Number): Graphics = definedExternally
    open fun dr(x: Number, y: Number, w: Number, h: Number): Graphics = definedExternally
    open fun rr(x: Number, y: Number, w: Number, h: Number, radius: Number): Graphics = definedExternally
    open fun rc(x: Number, y: Number, w: Number, h: Number, radiusTL: Number, radiusTR: Number, radiusBR: Number, radisBL: Number): Graphics = definedExternally
    open fun ef(): Graphics = definedExternally
    open fun es(): Graphics = definedExternally
    open fun lt(x: Number, y: Number): Graphics = definedExternally
    open fun mt(x: Number, y: Number): Graphics = definedExternally
    open fun qt(cpx: Number, cpy: Number, x: Number, y: Number): Graphics = definedExternally
    open fun r(x: Number, y: Number, w: Number, h: Number): Graphics = definedExternally
    open fun ss(thickness: Number, caps: String? = definedExternally /* null */, joints: String? = definedExternally /* null */, miterLimit: Number? = definedExternally /* null */, ignoreScale: Boolean? = definedExternally /* null */): Graphics = definedExternally
    open fun ss(thickness: Number, caps: String? = definedExternally /* null */, joints: Number? = definedExternally /* null */, miterLimit: Number? = definedExternally /* null */, ignoreScale: Boolean? = definedExternally /* null */): Graphics = definedExternally
    open fun ss(thickness: Number, caps: Number? = definedExternally /* null */, joints: String? = definedExternally /* null */, miterLimit: Number? = definedExternally /* null */, ignoreScale: Boolean? = definedExternally /* null */): Graphics = definedExternally
    open fun ss(thickness: Number, caps: Number? = definedExternally /* null */, joints: Number? = definedExternally /* null */, miterLimit: Number? = definedExternally /* null */, ignoreScale: Boolean? = definedExternally /* null */): Graphics = definedExternally
    open fun sd(segments: Array<Number>? = definedExternally /* null */, offset: Number? = definedExternally /* null */): Graphics = definedExternally
    companion object {
        var BASE_64: Any = definedExternally
        var STROKE_CAPS_MAP: Array<String> = definedExternally
        var STROKE_JOINTS_MAP: Array<String> = definedExternally
        fun getHSL(hue: Number, saturation: Number, lightness: Number, alpha: Number? = definedExternally /* null */): String = definedExternally
        fun getRGB(r: Number, g: Number, b: Number, alpha: Number? = definedExternally /* null */): String = definedExternally
    }
}
external interface `T$0` {
    var x: Number
    var y: Number
    var scaleX: Number
    var scaleY: Number
    var rotation: Number
    var skewX: Number
    var skewY: Number
}
external open class Matrix2D(a: Number? = definedExternally /* null */, b: Number? = definedExternally /* null */, c: Number? = definedExternally /* null */, d: Number? = definedExternally /* null */, tx: Number? = definedExternally /* null */, ty: Number? = definedExternally /* null */) {
    open var a: Float = definedExternally
    open var b: Float = definedExternally
    open var c: Float = definedExternally
    open var d: Float = definedExternally
    open var tx: Float = definedExternally
    open var ty: Float = definedExternally
    open fun append(a: Number, b: Number, c: Number, d: Number, tx: Number, ty: Number): Matrix2D = definedExternally
    open fun appendMatrix(matrix: Matrix2D): Matrix2D = definedExternally
    open fun appendTransform(x: Number, y: Number, scaleX: Number, scaleY: Number, rotation: Number, skewX: Number, skewY: Number, regX: Number? = definedExternally /* null */, regY: Number? = definedExternally /* null */): Matrix2D = definedExternally
    open fun clone(): Matrix2D = definedExternally
    open fun copy(matrix: Matrix2D): Matrix2D = definedExternally
    open fun decompose(): `T$0` = definedExternally
    open fun decompose(target: Any): Matrix2D = definedExternally
    open fun equals(matrix: Matrix2D): Boolean = definedExternally
    open fun identity(): Matrix2D = definedExternally
    open fun invert(): Matrix2D = definedExternally
    open fun isIdentity(): Boolean = definedExternally
    open fun prepend(a: Number, b: Number, c: Number, d: Number, tx: Number, ty: Number): Matrix2D = definedExternally
    open fun prependMatrix(matrix: Matrix2D): Matrix2D = definedExternally
    open fun prependTransform(x: Number, y: Number, scaleX: Number, scaleY: Number, rotation: Number, skewX: Number, skewY: Number, regX: Number? = definedExternally /* null */, regY: Number? = definedExternally /* null */): Matrix2D = definedExternally
    open fun rotate(angle: Number): Matrix2D = definedExternally
    open fun scale(x: Number, y: Number): Matrix2D = definedExternally
    open fun setValues(a: Number? = definedExternally /* null */, b: Number? = definedExternally /* null */, c: Number? = definedExternally /* null */, d: Number? = definedExternally /* null */, tx: Number? = definedExternally /* null */, ty: Number? = definedExternally /* null */): Matrix2D = definedExternally
    open fun skew(skewX: Number, skewY: Number): Matrix2D = definedExternally
    override fun toString(): String = definedExternally
    //open fun transformPoint(x: Float, y: Float, pt: Point? = definedExternally /* null */): Point = definedExternally
    open fun transformPoint(x: Float, y: Float, pt: Any? = definedExternally /* null */): Point = definedExternally
    open fun translate(x: Number, y: Number): Matrix2D = definedExternally
    companion object {
        var DEG_TO_RAD: Float = definedExternally
        var identity: Matrix2D = definedExternally
    }
}
external interface `T$1` {
    var handleEvent: (eventObj: Any) -> Boolean
}
external interface `T$2` {
    var handleEvent: (eventObj: Any) -> Unit
}
external interface `T$3` {
    var handleEvent: (eventObj: Any) -> Boolean
}
external interface `T$4` {
    var handleEvent: (eventObj: Any) -> Unit
}
external interface `T$5` {
    var handleEvent: (eventObj: Any) -> Boolean
}
external interface `T$6` {
    var handleEvent: (eventObj: Any) -> Unit
}
external interface `T$7` {
    var handleEvent: (eventObj: Any) -> Boolean
}
external interface `T$8` {
    var handleEvent: (eventObj: Any) -> Unit
}

external open class MovieClip(mode: String? = definedExternally /* null */, startPosition: Number? = definedExternally /* null */, loop: Boolean? = definedExternally /* null */, labels: Any? = definedExternally /* null */) : Container {
    open var actionsEnabled: Boolean = definedExternally
    open var autoReset: Boolean = definedExternally
    open var currentFrame: Float = definedExternally
    open var totalFrames: Float = definedExternally
    open var currentLabel: String = definedExternally
    open var frameBounds: Array<Rectangle> = definedExternally
    open var framerate: Float = definedExternally
    open var labels: Array<Any> = definedExternally
    open var loop: Boolean = definedExternally
    open var mode: String = definedExternally
    open var paused: Boolean = definedExternally
    open var startPosition: Float = definedExternally
    open var duration: Float = definedExternally
    open fun advance(time: Number? = definedExternally /* null */): Unit = definedExternally
    override fun clone(): MovieClip = definedExternally
    open fun getCurrentLabel(): String = definedExternally
    open fun getLabels(): Array<Any> = definedExternally


    open fun gotoAndStop(positionOrLabel: String): Unit = definedExternally
    open fun gotoAndStop(positionOrLabel: Number): Unit = definedExternally
    open fun play(): Unit = definedExternally
    open fun stop(): Unit = definedExternally

    companion object {
        var buildDate: String = definedExternally
        var INDEPENDENT: String = definedExternally
        var SINGLE_FRAME: String = definedExternally
        var SYNCHED: String = definedExternally
        var version: String = definedExternally
    }
}

external open class Point(x: Number? = definedExternally /* null */, y: Number? = definedExternally /* null */) {
    open var x: Float = definedExternally
    open var y: Float = definedExternally
    open fun clone(): Point = definedExternally
    open fun copy(point: Point): Point = definedExternally
    open fun setValues(x: Number? = definedExternally /* null */, y: Number? = definedExternally /* null */): Point = definedExternally
    override fun toString(): String = definedExternally
    companion object{
    }
}
external open class Rectangle(x: Number? = definedExternally /* null */, y: Number? = definedExternally /* null */, width: Number? = definedExternally /* null */, height: Number? = definedExternally /* null */) {
    open var height: Float = definedExternally
    open var width: Float = definedExternally
    open var x: Float = definedExternally
    open var y: Float = definedExternally
    open fun clone(): Rectangle = definedExternally
    open fun contains(x: Number, y: Number, width: Number? = definedExternally /* null */, height: Number? = definedExternally /* null */): Boolean = definedExternally
    open fun copy(rectangle: Rectangle): Rectangle = definedExternally
    open fun extend(x: Number, y: Number, width: Number? = definedExternally /* null */, height: Number? = definedExternally /* null */): Rectangle = definedExternally
    open fun intersection(rect: Rectangle): Rectangle = definedExternally
    open fun intersects(rect: Rectangle): Boolean = definedExternally
    open fun isEmpty(): Boolean = definedExternally
    open fun pad(top: Number, left: Number, bottom: Number, right: Number): Rectangle = definedExternally
    open fun setValues(x: Number? = definedExternally /* null */, y: Number? = definedExternally /* null */, width: Number? = definedExternally /* null */, height: Number? = definedExternally /* null */): Rectangle = definedExternally
    override fun toString(): String = definedExternally
    open fun union(rect: Rectangle): Rectangle = definedExternally
}
external open class Shadow(color: String, offsetX: Number, offsetY: Number, blur: Number) {
    open var blur: Float = definedExternally
    open var color: String = definedExternally
    open var offsetX: Float = definedExternally
    open var offsetY: Float = definedExternally
    open fun clone(): Shadow = definedExternally
    override fun toString(): String = definedExternally
    companion object {
        var identity: Shadow = definedExternally
    }
}
external open class Shape(graphics: Graphics? = definedExternally /* null */) : DisplayObject {
    open var graphics: Graphics = definedExternally
    open fun clone(recursive: Boolean? = definedExternally /* null */): Shape = definedExternally
    override fun set(props: Any): Shape = definedExternally
    override fun setTransform(x: Number?, y: Number?, scaleX: Number?, scaleY: Number?, rotation: Number?, skewX: Number?, skewY: Number?, regX: Number?, regY: Number?): Shape = definedExternally
}
external open class Sprite : DisplayObject {
    constructor(spriteSheet: SpriteSheet, frameOrAnimation: String? = definedExternally /* null */)
    constructor(spriteSheet: SpriteSheet, frameOrAnimation: Number? = definedExternally /* null */)
    open var currentAnimation: String = definedExternally
    open var currentAnimationFrame: Float = definedExternally
    open var currentFrame: Float = definedExternally
    open var framerate: Float = definedExternally
    open var offset: Float = definedExternally
    open var paused: Boolean = definedExternally
    open var spriteSheet: SpriteSheet = definedExternally
    open fun advance(time: Number? = definedExternally /* null */): Unit = definedExternally
    override fun clone(): Sprite = definedExternally
    override fun getBounds(): Rectangle = definedExternally
    open fun gotoAndPlay(frameOrAnimation: String): Unit = definedExternally
    open fun gotoAndPlay(frameOrAnimation: Number): Unit = definedExternally
    open fun gotoAndStop(frameOrAnimation: String): Unit = definedExternally
    open fun gotoAndStop(frameOrAnimation: Number): Unit = definedExternally
    open fun play(): Unit = definedExternally
    override fun set(props: Any): Sprite = definedExternally
    override fun setTransform(x: Number?, y: Number?, scaleX: Number?, scaleY: Number?, rotation: Number?, skewX: Number?, skewY: Number?, regX: Number?, regY: Number?): Sprite = definedExternally
    open fun stop(): Unit = definedExternally
}
external open class SpriteContainer(spriteSheet: SpriteSheet? = definedExternally /* null */) : Container {
    open var spriteSheet: SpriteSheet = definedExternally
}
external interface SpriteSheetAnimation {
    var frames: Array<Number>
    var speed: Number
    var name: String
    var next: String
}
external interface SpriteSheetFrame {
    var image: HTMLImageElement
    var rect: Rectangle
}
external open class SpriteSheet(data: dynamic) : EventDispatcher {
    open var animations: Array<String> = definedExternally
    open var complete: Boolean = definedExternally
    open var framerate: Float = definedExternally
    open var halfSize : Boolean = definedExternally
    open fun clone(): SpriteSheet = definedExternally
    open fun getAnimation(name: String): SpriteSheetAnimation = definedExternally
    open fun getAnimations(): Array<String> = definedExternally
    open fun getFrame(frameIndex: Number): SpriteSheetFrame = definedExternally
    open fun getFrameBounds(frameIndex: Number, rectangle: Rectangle? = definedExternally /* null */): Rectangle = definedExternally
    open fun getNumFrames(animation: String): Float = definedExternally
}
external open class SpriteSheetBuilder : EventDispatcher {
    open var maxHeight: Float = definedExternally
    open var maxWidth: Float = definedExternally
    open var padding: Float = definedExternally
    open var progress: Float = definedExternally
    open var scale: Float = definedExternally
    open var spriteSheet: SpriteSheet = definedExternally
    open var timeSlice: Float = definedExternally
    open fun addAnimation(name: String, frames: Array<Number>, next: String? = definedExternally /* null */, frequency: Number? = definedExternally /* null */): Unit = definedExternally
    open fun addAnimation(name: String, frames: Array<Number>, next: Boolean? = definedExternally /* null */, frequency: Number? = definedExternally /* null */): Unit = definedExternally
    open fun addFrame(source: DisplayObject, sourceRect: Rectangle? = definedExternally /* null */, scale: Number? = definedExternally /* null */, setupFunction: (() -> Any)? = definedExternally /* null */, setupData: Any? = definedExternally /* null */): Float = definedExternally
    open fun addMovieClip(source: MovieClip, sourceRect: Rectangle? = definedExternally /* null */, scale: Number? = definedExternally /* null */, setupFunction: (() -> Any)? = definedExternally /* null */, setupData: Any? = definedExternally /* null */, labelFunction: (() -> Any)? = definedExternally /* null */): Unit = definedExternally
    open fun build(): SpriteSheet = definedExternally
    open fun buildAsync(timeSlice: Number? = definedExternally /* null */): Unit = definedExternally
    open fun clone(): Unit = definedExternally
    open fun stopAsync(): Unit = definedExternally
}
external open class SpriteSheetUtils {
    companion object {
        fun addFlippedFrames(spriteSheet: SpriteSheet, horizontal: Boolean? = definedExternally /* null */, vertical: Boolean? = definedExternally /* null */, both: Boolean? = definedExternally /* null */): Unit = definedExternally
        fun extractFrame(spriteSheet: SpriteSheet, frameOrAnimation: Number): HTMLImageElement = definedExternally
        fun extractFrame(spriteSheet: SpriteSheet, frameOrAnimation: String): HTMLImageElement = definedExternally
        fun mergeAlpha(rgbImage: HTMLImageElement, alphaImage: HTMLImageElement, canvas: HTMLCanvasElement? = definedExternally /* null */): HTMLCanvasElement = definedExternally
    }
}
external open class SpriteStage : Stage {
    constructor(canvas: HTMLCanvasElement, preserveDrawingBuffer: Boolean? = definedExternally /* null */, antialias: Boolean? = definedExternally /* null */)
    constructor(canvas: String, preserveDrawingBuffer: Boolean? = definedExternally /* null */, antialias: Boolean? = definedExternally /* null */)
    open var isWebGL: Boolean = definedExternally
    open fun clearImageTexture(image: Any): Unit = definedExternally
    open fun updateViewport(width: Number, height: Number): Unit = definedExternally
    companion object {
        var INDICES_PER_BOX: Float = definedExternally
        var MAX_BOXES_POINTS_INCREMENT: Float = definedExternally
        var MAX_INDEX_SIZE: Float = definedExternally
        var NUM_VERTEX_PROPERTIES: Float = definedExternally
        var NUM_VERTEX_PROPERTIES_PER_BOX: Float = definedExternally
        var POINTS_PER_BOX: Float = definedExternally
    }
}

external open class StageGL : Stage{
    constructor(canvas: HTMLCanvasElement)
    constructor(canvas: String)
    constructor(canvas: Any)
    constructor(canvas: Any, options: Any)

    protected open fun _appendToBatch(container: Container, concatMtx: Matrix2D, concatAlpha: Float, ignoreCache: Boolean): Unit = definedExternally
     var vocalDebug: Boolean = definedExternally
     var batchReason: String = definedExternally
     var _batchID: Int = definedExternally
     var _drawID: Int = definedExternally
     var _webGLContext : WebGLRenderingContext = definedExternally
     var _textureDictionary: Array<WebGLTexture> = definedExternally
     fun _renderBatch(): Unit = definedExternally
     var _masks: Array<Rectangle> = definedExternally
     fun _getCombinedMasks() : Rectangle = definedExternally
     var _batchVertexCount: Int = definedExternally
     var _renderMode: String? = definedExternally
     var _renderModeLazy: String? = definedExternally
     var _activeConfig: dynamic = definedExternally
     fun _updateRenderMode(composite:String): Unit = definedExternally
     fun _applyRenderMode(): Unit = definedExternally
     fun _loadTextureImage(gl : WebGLRenderingContext, image: dynamic): WebGLTexture = definedExternally
     fun _insertTextureInBatch(gl: WebGLRenderingContext, texture: dynamic) : Unit = definedExternally
     val _directDraw: Boolean = definedExternally
     val _maxBatchVertexCount: Int = definedExternally
     var _immediateRender: Boolean = definedExternally
     fun _immediateBatchRender() : Unit = definedExternally
     var _attributeConfig: dynamic = definedExternally
companion object {
    val INDICIES_PER_CARD:Int = definedExternally
    var UV_RECT: dynamic = definedExternally
    fun buildUVRects(spritesheet: SpriteSheet, target: Int,onlyTarget: Boolean ): dynamic = definedExternally
}

}

external open class Stage : Container {
    constructor(canvas: HTMLCanvasElement)
    constructor(canvas: String)
    constructor(canvas: Any)
    open var autoClear: Boolean = definedExternally
    open var canvas: HTMLCanvasElement /* HTMLCanvasElement | Any */ = definedExternally
    open var drawRect: Rectangle = definedExternally
    open var handleEvent: Function<*> = definedExternally
    open var mouseInBounds: Boolean = definedExternally
    open var mouseMoveOutside: Boolean = definedExternally
    open var mouseX: Float = definedExternally
    open var mouseY: Float = definedExternally
    open var nextStage: Stage = definedExternally
    open var preventSelection: Boolean = definedExternally
    open var snapToPixelEnabled: Boolean = definedExternally
    open var tickOnUpdate: Boolean = definedExternally
    open fun clear(): Unit = definedExternally
    override fun clone(): Stage = definedExternally
    open fun enableDOMEvents(enable: Boolean? = definedExternally /* null */): Unit = definedExternally
    open fun enableMouseOver(frequency: Number? = definedExternally /* null */): Unit = definedExternally
    open fun tick(props: Any? = definedExternally /* null */): Unit = definedExternally
    open fun toDataURL(backgroundColor: String, mimeType: String): String = definedExternally
    open fun update(vararg arg: Any): Unit = definedExternally
}
external open class Text(text: String? = definedExternally /* null */, font: String? = definedExternally /* null */, color: String? = definedExternally /* null */) : DisplayObject {
    open var originalText:Text = definedExternally
    open var isHDText:Boolean = definedExternally
    open var color: String = definedExternally
    open var font: String = definedExternally
    open var lineHeight: Float = definedExternally
    open var lineWidth: Float = definedExternally
    open var maxWidth: Float = definedExternally
    open var outline: Float = definedExternally
    open var text: String = definedExternally
    open var textAlign: String = definedExternally
    open var textBaseline: String = definedExternally
    override fun clone(): Text = definedExternally
    open fun getMeasuredHeight(): Float = definedExternally
    open fun getMeasuredLineHeight(): Float = definedExternally
    open fun getMeasuredWidth(): Float = definedExternally
    open fun getMetrics(): Any = definedExternally
    override fun set(props: Any): Text = definedExternally
    override fun setTransform(x: Number?, y: Number?, scaleX: Number?, scaleY: Number?, rotation: Number?, skewX: Number?, skewY: Number?, regX: Number?, regY: Number?): Text = definedExternally
}
external interface `T$9` {
    var handleEvent: (eventObj: Any) -> Boolean
}
external interface `T$10` {
    var handleEvent: (eventObj: Any) -> Unit
}
external interface `T$11` {
    var handleEvent: (eventObj: Any) -> Boolean
}
external interface `T$12` {
    var handleEvent: (eventObj: Any) -> Unit
}
external interface `T$13` {
    var handleEvent: (eventObj: Any) -> Boolean
}
external interface `T$14` {
    var handleEvent: (eventObj: Any) -> Unit
}
external interface `T$15` {
    var handleEvent: (eventObj: Any) -> Boolean
}
external interface `T$16` {
    var handleEvent: (eventObj: Any) -> Unit
}
external open class Ticker {
    companion object {
        var framerate: Float = definedExternally
        var interval: Float = definedExternally
        var maxDelta: Float = definedExternally
        var paused: Boolean = definedExternally
        var RAF: String = definedExternally
        var RAF_SYNCHED: String = definedExternally
        var TIMEOUT: String = definedExternally
        var timingMode: String = definedExternally
        var useRAF: Boolean = definedExternally
        fun getEventTime(runTime: Boolean? = definedExternally /* null */): Float = definedExternally
        fun getFPS(): Float = definedExternally
        fun getInterval(): Float = definedExternally
        fun getMeasuredFPS(ticks: Number? = definedExternally /* null */): Float = definedExternally
        fun getMeasuredTickTime(ticks: Number? = definedExternally /* null */): Float = definedExternally
        fun getPaused(): Boolean = definedExternally
        fun getTicks(pauseable: Boolean? = definedExternally /* null */): Float = definedExternally
        fun getTime(runTime: Boolean? = definedExternally /* null */): Float = definedExternally
        fun init(): Unit = definedExternally
        fun reset(): Unit = definedExternally
        fun setFPS(value: Number): Unit = definedExternally
        fun setInterval(interval: Number): Unit = definedExternally
        fun setPaused(value: Boolean): Unit = definedExternally
        fun addEventListener(type: String, listener: Stage, useCapture: Boolean? = definedExternally /* null */): Stage = definedExternally
        fun addEventListener(type: String, listener: (eventObj: Any) -> Boolean, useCapture: Boolean? = definedExternally /* null */): Function<*> = definedExternally
        fun addEventListener(type: String, listener: (eventObj: Any) -> Unit, useCapture: Boolean? = definedExternally /* null */): Function<*> = definedExternally
        fun addEventListener(type: String, listener: `T$9`, useCapture: Boolean? = definedExternally /* null */): Any = definedExternally
        fun addEventListener(type: String, listener: `T$10`, useCapture: Boolean? = definedExternally /* null */): Any = definedExternally
        fun dispatchEvent(eventObj: Any, target: Any? = definedExternally /* null */): Boolean = definedExternally
        fun dispatchEvent(eventObj: String, target: Any? = definedExternally /* null */): Boolean = definedExternally
        fun dispatchEvent(eventObj: Event, target: Any? = definedExternally /* null */): Boolean = definedExternally
        fun hasEventListener(type: String): Boolean = definedExternally
        fun off(type: String, listener: (eventObj: Any) -> Boolean, useCapture: Boolean? = definedExternally /* null */): Unit = definedExternally
        fun off(type: String, listener: (eventObj: Any) -> Unit, useCapture: Boolean? = definedExternally /* null */): Unit = definedExternally
        fun off(type: String, listener: `T$11`, useCapture: Boolean? = definedExternally /* null */): Unit = definedExternally
        fun off(type: String, listener: `T$12`, useCapture: Boolean? = definedExternally /* null */): Unit = definedExternally
        fun off(type: String, listener: Function<*>, useCapture: Boolean? = definedExternally /* null */): Unit = definedExternally
        //fun on(type: String, listener: (eventObj: Any) -> Boolean, scope: Any? = definedExternally /* null */, once: Boolean? = definedExternally /* null */, data: Any? = definedExternally /* null */, useCapture: Boolean? = definedExternally /* null */): Function<*> = definedExternally
        fun on(type: String, listener: (eventObj: Any) -> Unit, scope: Any? = definedExternally /* null */, once: Boolean? = definedExternally /* null */, data: Any? = definedExternally /* null */, useCapture: Boolean? = definedExternally /* null */): Function<*> = definedExternally
        //fun on(type: String, listener: `T$13`, scope: Any? = definedExternally /* null */, once: Boolean? = definedExternally /* null */, data: Any? = definedExternally /* null */, useCapture: Boolean? = definedExternally /* null */): Any = definedExternally
        //fun on(type: String, listener: `T$14`, scope: Any? = definedExternally /* null */, once: Boolean? = definedExternally /* null */, data: Any? = definedExternally /* null */, useCapture: Boolean? = definedExternally /* null */): Any = definedExternally
        fun removeAllEventListeners(type: String? = definedExternally /* null */): Unit = definedExternally
        fun removeEventListener(type: String, listener: (eventObj: Any) -> Boolean, useCapture: Boolean? = definedExternally /* null */): Unit = definedExternally
        fun removeEventListener(type: String, listener: (eventObj: Any) -> Unit, useCapture: Boolean? = definedExternally /* null */): Unit = definedExternally
        fun removeEventListener(type: String, listener: `T$15`, useCapture: Boolean? = definedExternally /* null */): Unit = definedExternally
        fun removeEventListener(type: String, listener: `T$16`, useCapture: Boolean? = definedExternally /* null */): Unit = definedExternally
        fun removeEventListener(type: String, listener: Function<*>, useCapture: Boolean? = definedExternally /* null */): Unit = definedExternally
        override fun toString(): String = definedExternally
        fun willTrigger(type: String): Boolean = definedExternally
    }
}
external open class TickerEvent {
    open var target: Any = definedExternally
    open var type: String = definedExternally
    open var paused: Boolean = definedExternally
    open var delta: Float = definedExternally
    open var time: Float = definedExternally
    open var runTime: Float = definedExternally
}
external open class Touch {
    companion object {
        fun disable(stage: Stage): Unit = definedExternally
        fun enable(stage: Stage, singleTouch: Boolean? = definedExternally /* null */, allowDefault: Boolean? = definedExternally /* null */): Boolean = definedExternally
        fun isSupported(): Boolean = definedExternally
    }
}
external open class UID {
    companion object {
        fun get(): Float = definedExternally
    }
}
