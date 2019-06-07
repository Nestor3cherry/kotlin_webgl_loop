@file:Suppress("INTERFACE_WITH_SUPERCLASS", "OVERRIDING_FINAL_MEMBER", "RETURN_TYPE_MISMATCH_ON_OVERRIDE", "CONFLICTING_OVERLOADS")
@file:JsQualifier("createjs")
package createjs

external open class Event(type: String, bubbles: Boolean, cancelable: Boolean) {
    open var bubbles: Boolean = definedExternally
    open var cancelable: Boolean = definedExternally
    open var currentTarget: Any = definedExternally
    open var defaultPrevented: Boolean = definedExternally
    open var eventPhase: Float = definedExternally
    open var immediatePropagationStopped: Boolean = definedExternally
    open var propagationStopped: Boolean = definedExternally
    open var removed: Boolean = definedExternally
    open var target: Any = definedExternally
    open var timeStamp: Float = definedExternally
    open var type: String = definedExternally
    open var data: Any = definedExternally
    open var delta: Float = definedExternally
    open var error: String = definedExternally
    open var id: String = definedExternally
    open var item: dynamic = definedExternally
    open var loaded: Float = definedExternally
    open var name: String = definedExternally
    open var next: String = definedExternally
    open var params: Any = definedExternally
    open var paused: Boolean = definedExternally
    open var progress: Float = definedExternally
    open var rawResult: Any = definedExternally
    open var result: Any = definedExternally
    open var runTime: Float = definedExternally
    open var src: String = definedExternally
    open var time: Float = definedExternally
    open var total: Float = definedExternally
    open fun clone(): Event = definedExternally
    open fun preventDefault(): Unit = definedExternally
    open fun remove(): Unit = definedExternally
    open fun set(props: Any): Event = definedExternally
    open fun stopImmediatePropagation(): Unit = definedExternally
    open fun stopPropagation(): Unit = definedExternally
    override fun toString(): String = definedExternally
}
external interface `T$00` {
    var handleEvent: (eventObj: Event) -> Boolean
}
external interface `T$01` {
    var handleEvent: (eventObj: Event) -> Unit
}
external interface `T$02` {
    var handleEvent: (eventObj: Event) -> Boolean
}
external interface `T$03` {
    var handleEvent: (eventObj: Event) -> Unit
}
external interface `T$04` {
    var handleEvent: (eventObj: Event) -> Boolean
}
external interface `T$05` {
    var handleEvent: (eventObj: Event) -> Unit
}
external interface `T$06` {
    var handleEvent: (eventObj: Event) -> Boolean
}
external interface `T$07` {
    var handleEvent: (eventObj: Event) -> Unit
}
external open class EventDispatcher {
    //open fun addEventListener(type: String, listener: (eventObj: Event) -> Boolean, useCapture: Boolean? = definedExternally /* null */): Function<*> = definedExternally
    open fun addEventListener(type: String, listener: (eventObj: Event) -> Unit, useCapture: Boolean? = definedExternally /* null */): Function<*> = definedExternally
    //open fun addEventListener(type: String, listener: `T$00`, useCapture: Boolean? = definedExternally /* null */): Any = definedExternally
    //open fun addEventListener(type: String, listener: `T$01`, useCapture: Boolean? = definedExternally /* null */): Any = definedExternally
    open fun dispatchEvent(eventObj: Event, target: Any? = definedExternally /* null */): Boolean = definedExternally
    open fun dispatchEvent(eventObj: String, target: Any? = definedExternally /* null */): Boolean = definedExternally
    //open fun dispatchEvent(eventObj: Event, target: Any? = definedExternally /* null */): Boolean = definedExternally
    open fun hasEventListener(type: String): Boolean = definedExternally
    open fun off(type: String, listener: (eventObj: Event) -> Boolean, useCapture: Boolean? = definedExternally /* null */): Unit = definedExternally
    open fun off(type: String, listener: (eventObj: Event) -> Unit, useCapture: Boolean? = definedExternally /* null */): Unit = definedExternally
    open fun off(type: String, listener: `T$02`, useCapture: Boolean? = definedExternally /* null */): Unit = definedExternally
    open fun off(type: String, listener: `T$03`, useCapture: Boolean? = definedExternally /* null */): Unit = definedExternally
    open fun off(type: String, listener: Function<*>, useCapture: Boolean? = definedExternally /* null */): Unit = definedExternally
    //open fun on(type: String, listener: (eventObj: Event) -> Boolean, scope: Any? = definedExternally /* null */, once: Boolean? = definedExternally /* null */, data: Any? = definedExternally /* null */, useCapture: Boolean? = definedExternally /* null */): Function<*> = definedExternally
    open fun on(type: String, listener: (eventObj: Event) -> Unit, scope: Any? = definedExternally /* null */, once: Boolean? = definedExternally /* null */, data: Any? = definedExternally /* null */, useCapture: Boolean? = definedExternally /* null */): Function<*> = definedExternally
    open fun on(type: String, listener: `T$04`, scope: Any? = definedExternally /* null */, once: Boolean? = definedExternally /* null */, data: Any? = definedExternally /* null */, useCapture: Boolean? = definedExternally /* null */): Any = definedExternally
    open fun on(type: String, listener: `T$05`, scope: Any? = definedExternally /* null */, once: Boolean? = definedExternally /* null */, data: Any? = definedExternally /* null */, useCapture: Boolean? = definedExternally /* null */): Any = definedExternally
    open fun removeAllEventListeners(type: String? = definedExternally /* null */): Unit = definedExternally
    open fun removeEventListener(type: String, listener: (eventObj: Event) -> Boolean, useCapture: Boolean? = definedExternally /* null */): Unit = definedExternally
    open fun removeEventListener(type: String, listener: (eventObj: Event) -> Unit, useCapture: Boolean? = definedExternally /* null */): Unit = definedExternally
    open fun removeEventListener(type: String, listener: `T$06`, useCapture: Boolean? = definedExternally /* null */): Unit = definedExternally
    open fun removeEventListener(type: String, listener: `T$07`, useCapture: Boolean? = definedExternally /* null */): Unit = definedExternally
    open fun removeEventListener(type: String, listener: Function<*>, useCapture: Boolean? = definedExternally /* null */): Unit = definedExternally
    override fun toString(): String = definedExternally
    open fun willTrigger(type: String): Boolean = definedExternally
    companion object {
        fun initialize(target: Any): Unit = definedExternally
    }
}
external fun extend(subclass: () -> Any, superclass: () -> Any): () -> Any = definedExternally
external fun indexOf(array: Array<Any>, searchElement: Any): Float = definedExternally
external fun promote(subclass: () -> Any, prefix: String): () -> Any = definedExternally
external fun proxy(method: (eventObj: Event) -> Boolean, scope: Any, vararg arg: Any): (eventObj: Event) -> Any = definedExternally
external fun proxy(method: (eventObj: Event) -> Unit, scope: Any, vararg arg: Any): (eventObj: Event) -> Any = definedExternally
external interface `T$08` {
    var handleEvent: (eventObj: Event) -> Boolean
}
external fun proxy(method: `T$08`, scope: Any, vararg arg: Any): (eventObj: Event) -> Any = definedExternally
external interface `T$09` {
    var handleEvent: (eventObj: Event) -> Unit
}
external fun proxy(method: `T$09`, scope: Any, vararg arg: Any): (eventObj: Event) -> Any = definedExternally
