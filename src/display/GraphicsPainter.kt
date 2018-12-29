package wylaga.view.display

import java.awt.Graphics2D
import java.awt.image.BufferedImage
import java.util.*

class GraphicsPainter(private var g2d: Graphics2D) : Painter {
    private val stack = Stack<Graphics2D>()

    override fun push() {
        stack.push(g2d)
        g2d = g2d.create() as Graphics2D
    }

    override fun pop() {
        g2d = stack.pop()
    }

    override fun translate(x: Double, y: Double) {
        g2d.translate(x, y)
    }

    override fun rotate(theta: Double) {
        g2d.rotate(theta)
    }

    override fun setFillColor(color: Color) {
        g2d.color = ktColorToAwt(color)
    }

    private fun ktColorToAwt(color: Color) : java.awt.Color {
        return java.awt.Color(color.red, color.green, color.blue, color.alpha)
    }

    override fun fillRect(x: Double, y: Double, width: Double, height: Double) {
        g2d.fillRect(x.toInt(), y.toInt(), width.toInt(), height.toInt())
    }

    fun drawBufferedImage(image: BufferedImage) {
        g2d.drawImage(image, 0, 0, null)
    }
}