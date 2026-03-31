// GENERATED - do not edit. Regenerate via: mvn generate-sources -pl cef4j-native
package net.kurobako.cef4j.gen;

/**
 * Structure containing shared texture common metadata. For documentation on each field, please refer to
 * src/media/base/video_frame_metadata.h for actual details.
 */
public final class CefAcceleratedPaintInfoCommon {

    public final long size;
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
            long size,
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
        this.size = size;
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
        return this.size == other.size
                && this.timestamp == other.timestamp
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
                size,
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
        return "CefAcceleratedPaintInfoCommon{" + "size=" + size + ", " + "timestamp=" + timestamp + ", " + "codedSize="
                + codedSize + ", " + "visibleRect=" + visibleRect + ", " + "contentRect=" + contentRect + ", "
                + "sourceSize=" + sourceSize + ", " + "captureUpdateRect=" + captureUpdateRect + ", "
                + "regionCaptureRect=" + regionCaptureRect + ", " + "captureCounter=" + captureCounter + ", "
                + "hasCaptureUpdateRect=" + hasCaptureUpdateRect + ", " + "hasRegionCaptureRect=" + hasRegionCaptureRect
                + ", " + "hasSourceSize=" + hasSourceSize + ", " + "hasCaptureCounter=" + hasCaptureCounter + "}";
    }
}
