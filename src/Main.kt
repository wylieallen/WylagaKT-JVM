import wylaga.Wylaga
import wylaga.external.KeyboardAdapter
import wylaga.view.display.DisplayPanel
import wylaga.view.display.image.decodeToBufferedImage
import java.awt.event.KeyEvent
import java.awt.event.KeyListener
import javax.swing.JFrame
import javax.swing.SwingUtilities
import javax.swing.Timer
import javax.swing.WindowConstants

fun main() = SwingUtilities.invokeLater { createAndShowGui() }

fun createAndShowGui() {
    val frame = JFrame()
    val wylaga = Wylaga(decodeToBufferedImage)
    val panel = DisplayPanel(wylaga)

    panel.setSize(1600, 900)
    frame.setSize(1600 + 16, 900 + 39)

    frame.title = "WylagaKT for the JVM"
    frame.contentPane = panel
    frame.validate()
    frame.isVisible = true
    frame.isResizable = false
    frame.defaultCloseOperation = WindowConstants.EXIT_ON_CLOSE

    panel.background = java.awt.Color.BLACK

    frame.addKeyListener(KeyController(wylaga))

    Timing.onUpdate = {
        wylaga.tick()
        panel.repaint()
    }

    Timing.start()
}

object Timing {
    private val timer = Timer(16) { tick() }

    private fun tick() {
        timer.stop()
        onUpdate()
        timer.restart()
    }

    var onUpdate = {}

    fun start() = timer.start()
}

class KeyController(wylaga: Wylaga) : KeyListener {
    private val controller = KeyboardAdapter(wylaga)

    override fun keyPressed(e: KeyEvent) = controller.keyDown(e.keyCode)
    override fun keyReleased(e: KeyEvent) = controller.keyUp(e.keyCode)
    override fun keyTyped(e: KeyEvent) {}
}