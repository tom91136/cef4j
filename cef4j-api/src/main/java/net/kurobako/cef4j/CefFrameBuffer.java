package net.kurobako.cef4j;

import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.util.Optional;
import javax.annotation.Nullable;
import net.kurobako.cef4j.gen.CefRect;
import net.kurobako.cef4j.policy.NullableBoundary;

/**
 * Thread-safe frame buffer for CEF off-screen rendering.
 *
 * <p>Encapsulates the pixel-copy and image-handoff pipeline between CEF's {@code onPaint} callback (producer, CEF UI
 * thread) and a toolkit rendering loop (consumer, e.g. Swing EDT or JavaFX application thread).
 *
 * <p>Key properties:
 *
 * <ul>
 *   <li>Pre-allocated {@code int[]} pixel buffer sized to max monitor dimensions - no GC churn on resize.
 *   <li>Back-pressure: the producer skips work when the consumer hasn't consumed the last frame. CEF's {@code onPaint}
 *       is demand-driven (only fires on dirty regions), so a dropped frame means no repaint until the next interaction
 *       unless {@link #resetBackPressure()} is called.
 *   <li>Thread-safe image handoff via volatile reference. The volatile write of the image reference <em>after</em>
 *       pixel stamping creates the happens-before edge.
 *   <li>Generic over image type {@code I} - provide an {@link ImageWriter} for your toolkit.
 * </ul>
 *
 * <p>Typical Swing usage:
 *
 * <pre>{@code
 * CefFrameBuffer<BufferedImage> fb = new CefFrameBuffer<>(maxW, maxH, (prev, pixels, w, h, dirty) -> {
 *     BufferedImage img = (prev != null && prev.getWidth() == w && prev.getHeight() == h)
 *         ? prev : new BufferedImage(w, h, BufferedImage.TYPE_INT_ARGB);
 *     if (dirty == null) {
 *         img.getRaster().setDataElements(0, 0, w, h, pixels);
 *     } else {
 *         for (CefRect r : dirty)
 *             img.getRaster().setDataElements(r.x, r.y, r.width, r.height, pixels);
 *     }
 *     return img;
 * });
 * }</pre>
 *
 * @param <I> the toolkit image type (e.g. {@code BufferedImage}, {@code WritableImage})
 */
@SuppressWarnings("unused")
@NullableBoundary("CEF paint callbacks use null for an unspecified dirty region")
public final class CefFrameBuffer<I> {

    static final int MAX_PIXEL_COUNT = 7_680 * 4_320;

    /**
     * Strategy for creating/reusing a toolkit image and stamping pixel data into it.
     *
     * <p>The implementation may reuse {@code prev} if dimensions match, or create a new image. The {@code pixels} array
     * is pre-populated with ARGB int values (converted from CEF's BGRA byte layout) and contains exactly {@code width *
     * height} valid elements.
     *
     * @param <I> the toolkit image type
     */
    @FunctionalInterface
    public interface ImageWriter<I> {

        /**
         * Stamp pixel data into an image.
         *
         * <p>When {@code dirtyRects} is non-null, only those regions have been updated in the {@code pixels} array -
         * the implementation may choose to blit only those regions into the image for better performance.
         *
         * @param prev the previous image, or empty if this is the first frame
         * @param pixels ARGB pixel data; at least {@code width * height} elements are valid
         * @param width frame width in pixels
         * @param height frame height in pixels
         * @param dirtyRects regions that changed, or {@code null} for a full-frame update
         * @return the image containing the stamped pixels (may be {@code prev} reused, or a new instance)
         */
        I stamp(Optional<I> prev, int[] pixels, int width, int height, @Nullable CefRect[] dirtyRects);
    }

    private int[] pixelBuffer;
    private final ImageWriter<I> writer;

    private int lastWidth;
    private int lastHeight;

    private volatile boolean ready = true;

    private int pendingX1, pendingY1, pendingX2, pendingY2;
    private boolean hasPending;

    private volatile @Nullable I frontImage;
    private @Nullable I backImage;

    /**
     * Create a frame buffer with a pre-allocated pixel buffer.
     *
     * @param maxWidth maximum expected frame width (typically max monitor width)
     * @param maxHeight maximum expected frame height (typically max monitor height)
     * @param writer strategy for creating/reusing images and stamping pixels
     */
    public CefFrameBuffer(int maxWidth, int maxHeight, ImageWriter<I> writer) {
        if (maxWidth <= 0 || maxHeight <= 0) {
            throw new IllegalArgumentException("maxWidth and maxHeight must be positive");
        }
        if (writer == null) {
            throw new IllegalArgumentException("writer must not be null");
        }
        this.pixelBuffer = new int[checkedPixelCount(maxWidth, maxHeight)];
        this.writer = writer;
    }

    /**
     * Called from CEF's {@code onPaint} callback (CEF UI thread).
     *
     * <p>Performs a bulk BGRA-to-int copy from the direct byte buffer into the pre-allocated pixel buffer, then
     * delegates to the {@link ImageWriter} to stamp the pixels into a toolkit image. The {@code ByteBuffer} is a
     * zero-copy view of native memory and is only valid for the duration of this call.
     *
     * <p>Returns empty if back-pressure suppressed this frame (the consumer hasn't called {@link #consume()} since the
     * last successful {@code onPaint}).
     *
     * @param buffer direct ByteBuffer wrapping CEF's BGRA pixel data ({@code width * height * 4} bytes)
     * @param width frame width in pixels
     * @param height frame height in pixels
     * @return the stamped image, or empty if the frame was skipped
     */
    public Optional<I> onPaint(ByteBuffer buffer, int width, int height) {
        return onPaint(buffer, width, height, null);
    }

    /** Copies a complete validated CEF BGRA frame, or returns empty for invalid dimensions or truncated input. */
    public static Optional<int[]> copyBgraPixels(ByteBuffer buffer, int width, int height) {
        if (buffer == null || width <= 0 || height <= 0) return Optional.empty();
        int pixelCount;
        try {
            pixelCount = checkedPixelCount(width, height);
        } catch (IllegalArgumentException ignored) {
            return Optional.empty();
        }
        if (buffer.remaining() < (long) pixelCount * Integer.BYTES) return Optional.empty();
        int[] pixels = new int[pixelCount];
        buffer.duplicate().order(ByteOrder.LITTLE_ENDIAN).asIntBuffer().get(pixels);
        return Optional.of(pixels);
    }

    /**
     * Called from CEF's {@code onPaint} callback (CEF UI thread).
     *
     * <p>When {@code dirtyRects} is provided, only the dirty regions are copied from the source buffer into the
     * pre-allocated pixel buffer, avoiding a full-frame copy. The first frame (or after a resize) always does a full
     * copy since the pixel buffer may not contain valid data yet.
     *
     * @param buffer direct ByteBuffer wrapping CEF's BGRA pixel data ({@code width * height * 4} bytes)
     * @param width frame width in pixels
     * @param height frame height in pixels
     * @param dirtyRects array of dirty rectangles, or {@code null} for a full-frame copy
     * @return the stamped image, or empty if the frame was skipped
     */
    public Optional<I> onPaint(ByteBuffer buffer, int width, int height, @Nullable CefRect[] dirtyRects) {
        if (width <= 0 || height <= 0 || buffer == null) return Optional.empty();

        int pixelCount;
        try {
            pixelCount = checkedPixelCount(width, height);
        } catch (IllegalArgumentException ignored) {
            return Optional.empty();
        }
        long requiredBytes = (long) pixelCount * Integer.BYTES;
        if (buffer.remaining() < requiredBytes) return Optional.empty();

        if (!ready) {
            accumulateRects(dirtyRects);
            return Optional.empty();
        }
        ready = false;

        try {
            if (pixelCount > pixelBuffer.length) pixelBuffer = new int[pixelCount];
            java.nio.IntBuffer src = buffer.order(ByteOrder.LITTLE_ENDIAN).asIntBuffer();
            CefRect[] stampRects = null;

            if (dirtyRects == null
                    || dirtyRects.length == 0
                    || backImage == null
                    || lastWidth != width
                    || lastHeight != height) {
                hasPending = false;
                src.get(pixelBuffer, 0, pixelCount);
            } else {
                for (CefRect rect : dirtyRects) copyRect(src, rect, width, height);
                if (hasPending) {
                    CefRect pending = new CefRect(pendingX1, pendingY1, pendingX2 - pendingX1, pendingY2 - pendingY1);
                    hasPending = false;
                    copyRect(src, pending, width, height);
                    CefRect[] merged = new CefRect[dirtyRects.length + 1];
                    System.arraycopy(dirtyRects, 0, merged, 0, dirtyRects.length);
                    merged[dirtyRects.length] = pending;
                    stampRects = merged;
                } else {
                    stampRects = dirtyRects;
                }
            }

            lastWidth = width;
            lastHeight = height;

            I image = writer.stamp(Optional.ofNullable(backImage), pixelBuffer, width, height, stampRects);
            backImage = frontImage;
            frontImage = image;
            return Optional.of(image);
        } catch (RuntimeException | Error failure) {
            ready = true;
            throw failure;
        }
    }

    /**
     * Called from the consumer thread (e.g. Swing EDT {@code paintComponent}, JavaFX pulse).
     *
     * <p>Returns the current image and resets back-pressure so the next {@code onPaint} will produce a new frame.
     *
     * @return the current image, or empty if no frame has been produced yet
     */
    public Optional<I> consume() {
        ready = true;
        return Optional.ofNullable(frontImage);
    }

    /**
     * Reset back-pressure without consuming the image.
     *
     * <p>Call this on resize so the post-resize frame from CEF is not dropped. Without this, the first frame after a
     * resize would be suppressed if the consumer hasn't painted since the last pre-resize frame.
     */
    public void resetBackPressure() {
        ready = true;
    }

    private static int checkedPixelCount(int width, int height) {
        long count = (long) width * height;
        if (count <= 0 || count > MAX_PIXEL_COUNT) {
            throw new IllegalArgumentException("pixel count exceeds supported maximum: " + count);
        }
        return (int) count;
    }

    private void accumulateRects(@Nullable CefRect[] rects) {
        if (rects == null) return;
        for (CefRect r : rects) {
            int rx2 = r.x + r.width;
            int ry2 = r.y + r.height;
            if (!hasPending) {
                pendingX1 = r.x;
                pendingY1 = r.y;
                pendingX2 = rx2;
                pendingY2 = ry2;
                hasPending = true;
            } else {
                if (r.x < pendingX1) pendingX1 = r.x;
                if (r.y < pendingY1) pendingY1 = r.y;
                if (rx2 > pendingX2) pendingX2 = rx2;
                if (ry2 > pendingY2) pendingY2 = ry2;
            }
        }
    }

    private void copyRect(java.nio.IntBuffer src, CefRect r, int frameW, int frameH) {
        copyRect(src, r.x, r.y, r.width, r.height, frameW, frameH);
    }

    private void copyRect(java.nio.IntBuffer src, int x, int y, int w, int h, int frameW, int frameH) {
        int rx = Math.max(0, x);
        int ry = Math.max(0, y);
        int rw = Math.min(w, frameW - rx);
        int rh = Math.min(h, frameH - ry);
        if (rw <= 0 || rh <= 0) return;

        for (int row = ry; row < ry + rh; row++) {
            int offset = row * frameW + rx;
            src.position(offset);
            src.get(pixelBuffer, offset, rw);
        }
    }
}
