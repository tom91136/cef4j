// GENERATED - do not edit. Regenerate via: ./mvnw generate-sources -pl cef4j-api
// -Dcef.version=150.0.18+gdb11278+chromium-150.0.7871.213
package net.kurobako.cef4j.gen;

import javax.annotation.Nullable;
import javax.annotation.processing.Generated;
import net.kurobako.cef4j.policy.NullableBoundary;

/**
 * Structure containing shared texture common metadata. For documentation on each field, please refer to
 * src/media/base/video_frame_metadata.h for actual details.
 *
 * <p>Definition generated from internal/cef_types_osr.h
 *
 * <pre>typedef struct _cef_accelerated_paint_info_common_t {
 *   size_t size;
 *   int64_t timestamp;
 *   cef_size_t* coded_size;
 *   cef_rect_t* visible_rect;
 *   cef_rect_t* content_rect;
 *   cef_size_t* source_size;
 *   cef_rect_t* capture_update_rect;
 *   cef_rect_t* region_capture_rect;
 *   int64_t capture_counter;
 *   int has_capture_update_rect;
 *   int has_region_capture_rect;
 *   int has_source_size;
 *   int has_capture_counter;
 * } cef_accelerated_paint_info_common_t;</pre>
 *
 * @see <a href="https://cef-builds.spotifycdn.com/docs/150.0/cef__types__osr_8h.html">internal/cef_types_osr.h:43</a>
 */
@Generated("./mvnw generate-sources -pl cef4j-api -Dcef.version=150.0.18+gdb11278+chromium-150.0.7871.213")
@SuppressWarnings({"SameReturnValue", "EmptyMethod", "UnusedReturnValue", "unused"})
@NullableBoundary("generated external ABI")
public final class CefAcceleratedPaintInfoCommon {

    // Native struct size, populated by the JNI layer with sizeof(struct) as required by CEF. Not user-modifiable.
    @SuppressWarnings({"FieldMayBeFinal", "CanBeFinal"})
    private volatile long size = -1;

    /** Timestamp of the frame in microseconds since capture start. */
    public final long timestamp;
    /** The full dimensions of the video frame. */
    public final @Nullable CefSize codedSize;
    /** The visible area of the video frame. */
    public final @Nullable CefRect visibleRect;
    /** The region of the video frame that capturer would like to populate. */
    public final @Nullable CefRect contentRect;
    /** Full size of the source frame. */
    public final @Nullable CefSize sourceSize;
    /** Updated area of frame, can be considered as the `dirty` area. */
    public final @Nullable CefRect captureUpdateRect;
    /** May reflects where the frame's contents originate from if region capture is used internally. */
    public final @Nullable CefRect regionCaptureRect;
    /** The increamental counter of the frame. */
    public final long captureCounter;
    /** Optional flag of capture_update_rect */
    public final int hasCaptureUpdateRect;
    /** Optional flag of region_capture_rect */
    public final int hasRegionCaptureRect;
    /** Optional flag of source_size */
    public final int hasSourceSize;
    /** Optional flag of capture_counter */
    public final int hasCaptureCounter;

    public CefAcceleratedPaintInfoCommon(
            long timestamp,
            @Nullable CefSize codedSize,
            @Nullable CefRect visibleRect,
            @Nullable CefRect contentRect,
            @Nullable CefSize sourceSize,
            @Nullable CefRect captureUpdateRect,
            @Nullable CefRect regionCaptureRect,
            long captureCounter,
            int hasCaptureUpdateRect,
            int hasRegionCaptureRect,
            int hasSourceSize,
            int hasCaptureCounter) {
        this.timestamp = timestamp;
        this.codedSize = codedSize;
        this.visibleRect = visibleRect;
        this.contentRect = contentRect;
        this.sourceSize = sourceSize;
        this.captureUpdateRect = captureUpdateRect;
        this.regionCaptureRect = regionCaptureRect;
        this.captureCounter = captureCounter;
        this.hasCaptureUpdateRect = hasCaptureUpdateRect;
        this.hasRegionCaptureRect = hasRegionCaptureRect;
        this.hasSourceSize = hasSourceSize;
        this.hasCaptureCounter = hasCaptureCounter;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (!(obj instanceof CefAcceleratedPaintInfoCommon)) return false;
        CefAcceleratedPaintInfoCommon other = (CefAcceleratedPaintInfoCommon) obj;
        return this.timestamp == other.timestamp
                && java.util.Objects.equals(this.codedSize, other.codedSize)
                && java.util.Objects.equals(this.visibleRect, other.visibleRect)
                && java.util.Objects.equals(this.contentRect, other.contentRect)
                && java.util.Objects.equals(this.sourceSize, other.sourceSize)
                && java.util.Objects.equals(this.captureUpdateRect, other.captureUpdateRect)
                && java.util.Objects.equals(this.regionCaptureRect, other.regionCaptureRect)
                && this.captureCounter == other.captureCounter
                && this.hasCaptureUpdateRect == other.hasCaptureUpdateRect
                && this.hasRegionCaptureRect == other.hasRegionCaptureRect
                && this.hasSourceSize == other.hasSourceSize
                && this.hasCaptureCounter == other.hasCaptureCounter;
    }

    @Override
    public int hashCode() {
        return java.util.Objects.hash(
                timestamp,
                codedSize,
                visibleRect,
                contentRect,
                sourceSize,
                captureUpdateRect,
                regionCaptureRect,
                captureCounter,
                hasCaptureUpdateRect,
                hasRegionCaptureRect,
                hasSourceSize,
                hasCaptureCounter);
    }

    @Override
    public String toString() {
        return "CefAcceleratedPaintInfoCommon{" + "size=" + (size == -1 ? "pending" : Long.toString(size)) + ", "
                + "timestamp=" + timestamp + ", " + "codedSize=" + codedSize + ", " + "visibleRect=" + visibleRect
                + ", " + "contentRect=" + contentRect + ", " + "sourceSize=" + sourceSize + ", " + "captureUpdateRect="
                + captureUpdateRect + ", " + "regionCaptureRect=" + regionCaptureRect + ", " + "captureCounter="
                + captureCounter + ", " + "hasCaptureUpdateRect=" + hasCaptureUpdateRect + ", "
                + "hasRegionCaptureRect=" + hasRegionCaptureRect + ", " + "hasSourceSize=" + hasSourceSize + ", "
                + "hasCaptureCounter=" + hasCaptureCounter + "}";
    }
}
