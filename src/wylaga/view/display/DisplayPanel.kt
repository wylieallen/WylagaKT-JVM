package wylaga.view.display

import wylaga.view.display.displayables.Displayable
import java.awt.Graphics
import java.awt.Graphics2D
import javax.swing.JPanel

class DisplayPanel(var root: Displayable) : JPanel() {
    override fun paintComponent(g: Graphics) {
        super.paintComponent(g)
        root.display(GraphicsPainter(g as Graphics2D))
    }
}