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

    val title = Text("Kotlin-made WebGL render loop! :D", "24px Arial")
    title.cache(0, 0, 512, 256)
    title.y = 16f
    stage.addChild(title)

    val text1 = Text("Circles rendered in order", "18px Arial", "#ffffff")
    text1.cache(0, 0, 512, 256)
    text1.y = 100f
    text1.x = 20f
    stage.addChild(text1)

    //circles renderer in order
    (1..10).forEach { x ->
        (5..15).forEach { y ->
            val circle = createCircle(x * 40f, y * 40f, (x + y) % 2 != 0)
            stage.addChild(circle)
        }
    }


    val text2 = Text("Circles automatically reordered to minimize blending changes", "18px Arial", "#ffffff")
    text2.cache(0, 0, 512, 256)
    text2.y = 100f
    text2.x = 500f
    stage.addChild(text2)

    //circles reordered by render loop to minimize composite changes
    (1..10).forEach { x ->
        (5..15).forEach { y ->
            val circle = createCircle(500 + x * 40f, y * 40f, (x + y) % 2 != 0)
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
