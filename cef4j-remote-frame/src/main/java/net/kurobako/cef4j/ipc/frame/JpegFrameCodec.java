package net.kurobako.cef4j.ipc.frame;

import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.util.Iterator;
import javax.annotation.Nonnull;
import javax.imageio.IIOImage;
import javax.imageio.ImageIO;
import javax.imageio.ImageWriteParam;
import javax.imageio.ImageWriter;
import javax.imageio.stream.ImageOutputStream;

/** Built-in independently-decodable JPEG codec, also used by the MJPEG HTTP representation. */
public final class JpegFrameCodec implements FrameCodec {
    private static final CodecDescriptor DESCRIPTOR = new CodecDescriptor("jpeg", "image/jpeg", false);

    private final float quality;

    /** @param quality JPEG compression quality in {@code (0, 1]} */
    public JpegFrameCodec(float quality) {
        if (!(quality > 0.0f && quality <= 1.0f)) throw new IllegalArgumentException("quality must be in (0, 1]");
        this.quality = quality;
    }

    @Override
    @Nonnull
    public CodecDescriptor descriptor() {
        return DESCRIPTOR;
    }

    @Override
    @Nonnull
    public EncodedFrame encode(@Nonnull RawFrame frame) throws IOException {
        BufferedImage image = new BufferedImage(frame.width(), frame.height(), BufferedImage.TYPE_INT_RGB);
        int[] row = new int[frame.width()];
        ByteBuffer pixels = frame.pixels();
        for (int y = 0; y < frame.height(); y++) {
            int offset = y * frame.stride();
            for (int x = 0; x < frame.width(); x++) {
                int p = offset + x * 4;
                int b = pixels.get(p) & 0xff;
                int g = pixels.get(p + 1) & 0xff;
                int r = pixels.get(p + 2) & 0xff;
                row[x] = (r << 16) | (g << 8) | b;
            }
            image.setRGB(0, y, frame.width(), 1, row, 0, frame.width());
        }

        Iterator<ImageWriter> writers = ImageIO.getImageWritersByFormatName("jpeg");
        if (!writers.hasNext()) throw new IOException("no JPEG ImageIO writer is installed");
        ImageWriter writer = writers.next();
        ByteArrayOutputStream bytes = new ByteArrayOutputStream();
        try (ImageOutputStream output = ImageIO.createImageOutputStream(bytes)) {
            writer.setOutput(output);
            ImageWriteParam params = writer.getDefaultWriteParam();
            if (params.canWriteCompressed()) {
                params.setCompressionMode(ImageWriteParam.MODE_EXPLICIT);
                params.setCompressionQuality(quality);
            }
            writer.write(null, new IIOImage(image, null, null), params);
        } finally {
            writer.dispose();
        }
        return new EncodedFrame(
                DESCRIPTOR,
                frame.metadata().sourceSequence(),
                EncodedFrame.NO_BASE_SEQUENCE,
                true,
                frame.width(),
                frame.height(),
                ByteBuffer.wrap(bytes.toByteArray()));
    }
}
