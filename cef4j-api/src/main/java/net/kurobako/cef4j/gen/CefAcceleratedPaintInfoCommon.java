// GENERATED - do not edit. Regenerate via: mvn generate-sources -pl cef4j-native
package net.kurobako.cef4j.gen;

/**
 * Structure containing shared texture common metadata. For documentation on each field, please refer to
 * src/media/base/video_frame_metadata.h for actual details.
 *
 * <p>Definition generated from cef_types_osr.h
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
 * @see <a href="https://cef-builds.spotifycdn.com/docs/146.0/cef__types__osr_8h.html">cef_types_osr.h:43</a>
 */
public final class CefAcceleratedPaintInfoCommon {

    // Native struct size — set by JNI, not user-modifiable.
    @SuppressWarnings("FieldMayBeFinal")
    private volatile long size = -1;

    public final long timestamp;
    public final CefSize codedSize;
    public final CefRect visibleRect;
    public final CefRect contentRect;
    public final CefSize sourceSize;
    public final CefRect captureUpdateRect;
    public final CefRect regionCaptureRect;
    public final long captureCounter;
    public final int hasCaptureUpdateRect;
    public final int hasRegionCaptureRect;
    public final int hasSourceSize;
    public final int hasCaptureCounter;

    public CefAcceleratedPaintInfoCommon(
            long timestamp,
            CefSize codedSize,
            CefRect visibleRect,
            CefRect contentRect,
            CefSize sourceSize,
            CefRect captureUpdateRect,
            CefRect regionCaptureRect,
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
