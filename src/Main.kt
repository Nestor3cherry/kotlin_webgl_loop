import createjs.*
import mywebgl.overrideRenderLoop
import org.w3c.dom.HTMLCanvasElement
import org.w3c.dom.events.Event
import kotlin.browser.document
import kotlin.browser.window


fun main(args: Array<String>) {
    window.addEventListener("load", { it: Event -> onLoaded() })
}

fun onLoaded() {
    val canvas = document.getElementById("canvas")!! as HTMLCanvasElement
    val stage = StageGL(canvas)

    canvas.width = window.innerWidth
    canvas.height = window.innerHeight
    stage.asDynamic().updateViewport(window.innerWidth, window.innerHeight)

    //my own render loop! :-D
    stage.overrideRenderLoop()

    Ticker.on("tick", { eventObj -> stage.update(eventObj) })

    //circles renderer in order
    (1..10).forEach { x ->
        (1..10).forEach { y ->
            val circle = createCircle(x*40f, y*40f,(x+y)%2!=0 )
            stage.addChild(circle)
        }
    }

    //circles reordered by render loop to minimize composite changes
    (1..10).forEach { x ->
        (1..10).forEach { y ->
            val circle = createCircle(500+x*40f, y*40f,(x+y)%2!=0 )
            circle.asDynamic().flatten = "circle"
            stage.addChild(circle)
        }
    }
}

private fun createCircle(x: Float, y: Float, additive: Boolean): DisplayObject {
    val parent = Container()
    val circle = Shape()
    parent.addChild(circle)
    circle.graphics.beginFill("#aa0000").drawCircle(0, 0, 25)
    circle.x = x
    circle.y = y
    circle.cache(-25, -25, 50, 50)
    if (additive) parent.compositeOperation = "lighter"
    return parent
}
