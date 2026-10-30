package net.kurobako.cef4j;

import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.util.Objects;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicBoolean;
import net.kurobako.cef4j.gen.CefRect;
import net.kurobako.cef4j.policy.NullableBoundary;
import org.jspecify.annotations.Nullable;

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
 *       is demand-driven (only fires on dirty regions), so the first frame dropped after an accepted one runs the
 *       refresh request, which should ask CEF for a new frame once the consumer has caught up.
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
 * }, () -> SwingUtilities.invokeLater(this::refreshView));
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
         * Stamp pixel data into a new image when there is no previous frame.
         *
         * @param pixels ARGB pixel data; at least {@code width * height} elements are valid
         * @param width frame width in pixels
         * @param height frame height in pixels
         * @param dirtyRects regions that changed, or {@code null} for a full-frame update
         * @return the newly created image containing the stamped pixels
         */
        default I stamp(int[] pixels, int width, int height, CefRect @Nullable [] dirtyRects) {
            return stamp(null, pixels, width, height, dirtyRects);
        }

        /**
         * Stamp pixel data into an image.
         *
         * <p>When {@code dirtyRects} is non-null, only those regions have been updated in the {@code pixels} array -
         * the implementation may choose to blit only those regions into the image for better performance.
         *
         * @param prev the previous image, or {@code null} if this is the first frame
         * @param pixels ARGB pixel data; at least {@code width * height} elements are valid
         * @param width frame width in pixels
         * @param height frame height in pixels
         * @param dirtyRects regions that changed, or {@code null} for a full-frame update
         * @return the image containing the stamped pixels (may be {@code prev} reused, or a new instance)
         */
        I stamp(@Nullable I prev, int[] pixels, int width, int height, CefRect @Nullable [] dirtyRects);
    }

    private int[] pixelBuffer;
    private final ImageWriter<I> writer;
    private final Runnable refreshRequest;
    private final AtomicBoolean refreshRequested = new AtomicBoolean();

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
     * @param refreshRequest runs on the producer thread when a frame is dropped by back-pressure, at most once until
     *     the next accepted frame; it should hand off to the consumer thread, then call {@link #resetBackPressure()}
     *     and invalidate the CEF view so the dropped content is painted again
     */
    public CefFrameBuffer(int maxWidth, int maxHeight, ImageWriter<I> writer, Runnable refreshRequest) {
        if (maxWidth <= 0 || maxHeight <= 0) {
            throw new IllegalArgumentException("maxWidth and maxHeight must be positive");
        }
        Objects.requireNonNull(writer, "writer");
        Objects.requireNonNull(refreshRequest, "refreshRequest");
        this.pixelBuffer = new int[checkedPixelCount(maxWidth, maxHeight)];
        this.writer = writer;
        this.refreshRequest = refreshRequest;
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
    public Optional<I> onPaint(@Nullable ByteBuffer buffer, int width, int height) {
        return onPaint(buffer, width, height, null);
    }

    /** Copies a complete validated CEF BGRA frame, or returns empty for invalid dimensions or truncated input. */
    public static Optional<int[]> copyBgraPixels(@Nullable ByteBuffer buffer, int width, int height) {
        if (buffer == null || width <= 0 || height <= 0) return Optional.empty();
        int pixelCount;
        try {
            pixelCount = checkedPixelCount(width, height);
        } catch (IllegalArgumentException ignored) {
            return Optional.empty();
        }
        if (buffer.remaining() < (long) pixelCount * Integer.BYTES) return Optional.empty();
        final int[] pixels = new int[pixelCount];
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
    public Optional<I> onPaint(@Nullable ByteBuffer buffer, int width, int height, CefRect @Nullable [] dirtyRects) {
        if (width <= 0 || height <= 0 || buffer == null) return Optional.empty();

        int pixelCount;
        try {
            pixelCount = checkedPixelCount(width, height);
        } catch (IllegalArgumentException ignored) {
            return Optional.empty();
        }
        final long requiredBytes = (long) pixelCount * Integer.BYTES;
        if (buffer.remaining() < requiredBytes) return Optional.empty();

        if (!ready) {
            accumulateRects(dirtyRects);
            if (refreshRequested.compareAndSet(false, true)) refreshRequest.run();
            return Optional.empty();
        }
        ready = false;

        boolean stamped = false;
        try {
            if (pixelCount > pixelBuffer.length) pixelBuffer = new int[pixelCount];
            final java.nio.IntBuffer src = buffer.order(ByteOrder.LITTLE_ENDIAN).asIntBuffer();
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
                    final CefRect pending =
                            new CefRect(pendingX1, pendingY1, pendingX2 - pendingX1, pendingY2 - pendingY1);
                    hasPending = false;
                    copyRect(src, pending, width, height);
                    final CefRect[] merged = new CefRect[dirtyRects.length + 1];
                    System.arraycopy(dirtyRects, 0, merged, 0, dirtyRects.length);
                    merged[dirtyRects.length] = pending;
                    stampRects = merged;
                } else {
                    stampRects = dirtyRects;
                }
            }

            lastWidth = width;
            lastHeight = height;

            final I image = backImage == null
                    ? writer.stamp(pixelBuffer, width, height, stampRects)
                    : writer.stamp(backImage, pixelBuffer, width, height, stampRects);
            backImage = frontImage;
            frontImage = image;
            refreshRequested.set(false);
            stamped = true;
            return Optional.of(image);
        } finally {
            // The back-pressure is released when the stamp fails, so that the next frame is painted again.
            if (!stamped) ready = true;
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
        final long count = (long) width * height;
        if (count <= 0 || count > MAX_PIXEL_COUNT) {
            throw new IllegalArgumentException("pixel count exceeds supported maximum: " + count);
        }
        return (int) count;
    }

    private void accumulateRects(CefRect @Nullable [] rects) {
        if (rects == null) return;
        for (CefRect r : rects) {
            final int rx2 = r.x + r.width;
            final int ry2 = r.y + r.height;
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
        final int rx = Math.max(0, x);
        final int ry = Math.max(0, y);
        final int rw = Math.min(w, frameW - rx);
        final int rh = Math.min(h, frameH - ry);
        if (rw <= 0 || rh <= 0) return;

        for (int row = ry; row < ry + rh; row++) {
            final int offset = row * frameW + rx;
            src.position(offset);
            src.get(pixelBuffer, offset, rw);
        }
    }
}
