package wylaga.view.display.displayables

import wylaga.view.display.GraphicsPainter
import wylaga.view.display.Painter
import java.awt.image.BufferedImage

class BufferedImageDisplayable(private val image : BufferedImage) : AbstractDisplayable() {
    override fun doDisplay(p: Painter) { (p as GraphicsPainter).drawBufferedImage(image) }
}