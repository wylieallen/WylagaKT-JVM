package wylaga.view.display.image

import sun.misc.BASE64Decoder
import wylaga.view.display.displayables.BufferedImageDisplayable
import java.awt.image.BufferedImage
import java.io.ByteArrayInputStream
import javax.imageio.ImageIO

val decoder = BASE64Decoder()

val decodeToBufferedImage = {encoding: Base64Encoding ->
    try {
        val instream = ByteArrayInputStream(decoder.decodeBuffer(encoding.data))
        val image = ImageIO.read(instream)
        instream.close()
        BufferedImageDisplayable(image)

    } catch (e: Exception) {
        BufferedImageDisplayable(BufferedImage(1, 1, BufferedImage.TYPE_INT_ARGB))
    }
}