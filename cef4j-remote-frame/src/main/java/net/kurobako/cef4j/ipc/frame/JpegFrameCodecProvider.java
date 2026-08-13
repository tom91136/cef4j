package net.kurobako.cef4j.ipc.frame;

import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.util.Collections;
import java.util.Iterator;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;
import javax.annotation.Nonnull;
import javax.imageio.IIOImage;
import javax.imageio.ImageIO;
import javax.imageio.ImageWriteParam;
import javax.imageio.ImageWriter;
import javax.imageio.stream.ImageOutputStream;

/** Built-in independently-decodable JPEG codec, also used by the MJPEG HTTP representation. */
public final class JpegFrameCodecProvider implements FrameCodecProvider {
    private static final CodecDescriptor DESCRIPTOR = new CodecDescriptor("jpeg", "image/jpeg", false);

    @Override
    @Nonnull
    public CodecDescriptor descriptor() {
        return DESCRIPTOR;
    }

    @Override
    @Nonnull
    public FrameCodec newEncoder(@Nonnull Map<String, String> configuration) {
        float quality = Float.parseFloat(configuration.getOrDefault("quality", "0.80"));
        if (!(quality > 0.0f && quality <= 1.0f)) throw new IllegalArgumentException("quality must be in (0, 1]");
        return new Encoder(quality);
    }

    @Override
    @Nonnull
    public FrameDecoder newDecoder(@Nonnull Map<String, String> configuration) {
        return new Decoder();
    }

    private static final class Encoder implements FrameCodec {
        private final float quality;

        private Encoder(float quality) {
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

    private static final class Decoder implements FrameDecoder {
        private final AtomicInteger delivered = new AtomicInteger();

        @Override
        @Nonnull
        public CodecDescriptor descriptor() {
            return DESCRIPTOR;
        }

        @Override
        @Nonnull
        public RawFrame decode(@Nonnull EncodedFrame frame) throws IOException {
            if (!DESCRIPTOR.id().equals(frame.codec().id())) throw new IOException("expected jpeg frame");
            ByteBuffer payload = frame.payload();
            byte[] encoded = new byte[payload.remaining()];
            payload.get(encoded);
            BufferedImage image = ImageIO.read(new ByteArrayInputStream(encoded));
            if (image == null) throw new IOException("invalid JPEG payload");
            if (image.getWidth() != frame.width() || image.getHeight() != frame.height()) {
                throw new IOException("JPEG dimensions do not match envelope");
            }
            ByteBuffer bgra = ByteBuffer.allocateDirect(frame.width() * frame.height() * 4);
            int[] row = new int[frame.width()];
            for (int y = 0; y < frame.height(); y++) {
                image.getRGB(0, y, frame.width(), 1, row, 0, frame.width());
                for (int pixel : row) {
                    bgra.put((byte) pixel);
                    bgra.put((byte) (pixel >>> 8));
                    bgra.put((byte) (pixel >>> 16));
                    bgra.put((byte) 0xff);
                }
            }
            bgra.flip();
            FrameMetadata metadata = new FrameMetadata(
                    delivered.incrementAndGet(),
                    frame.sequence(),
                    System.nanoTime(),
                    PixelFormat.BGRA,
                    Collections.singletonList(new Rect(0, 0, frame.width(), frame.height())));
            return new RawFrame(frame.width(), frame.height(), frame.width() * 4, bgra, metadata);
        }
    }
}
