// GENERATED - do not edit. Regenerate via: mvn generate-sources -pl cef4j-native
package net.kurobako.cef4j.gen;

/**
 * Screen information used when window rendering is disabled. This structure is passed as a parameter to
 * CefRenderHandler::GetScreenInfo and should be filled in by the client.
 */
public final class CefScreenInfo {

    public final long size;
    public final float deviceScaleFactor;
    public final int depth;
    public final int depthPerComponent;
    public final int isMonochrome;
    public final CefRect rect;
    public final CefRect availableRect;

    public CefScreenInfo(
            long size,
            float deviceScaleFactor,
            int depth,
            int depthPerComponent,
            int isMonochrome,
            CefRect rect,
            CefRect availableRect) {
        this.size = size;
        this.deviceScaleFactor = deviceScaleFactor;
        this.depth = depth;
        this.depthPerComponent = depthPerComponent;
        this.isMonochrome = isMonochrome;
        this.rect = rect;
        this.availableRect = availableRect;
    }

    /** Create a mutable copy of this instance. */
    public CefMutableScreenInfo toMutable() {
        return new CefMutableScreenInfo(
                this.size,
                this.deviceScaleFactor,
                this.depth,
                this.depthPerComponent,
                this.isMonochrome,
                this.rect,
                this.availableRect);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (!(obj instanceof CefScreenInfo)) return false;
        CefScreenInfo other = (CefScreenInfo) obj;
        return this.size == other.size
                && this.deviceScaleFactor == other.deviceScaleFactor
                && this.depth == other.depth
                && this.depthPerComponent == other.depthPerComponent
                && this.isMonochrome == other.isMonochrome
                && java.util.Objects.equals(this.rect, other.rect)
                && java.util.Objects.equals(this.availableRect, other.availableRect);
    }

    @Override
    public int hashCode() {
        return java.util.Objects.hash(
                size, deviceScaleFactor, depth, depthPerComponent, isMonochrome, rect, availableRect);
    }

    @Override
    public String toString() {
        return "CefScreenInfo{" + "size=" + size + ", " + "deviceScaleFactor=" + deviceScaleFactor + ", " + "depth="
                + depth + ", " + "depthPerComponent=" + depthPerComponent + ", " + "isMonochrome=" + isMonochrome + ", "
                + "rect=" + rect + ", " + "availableRect=" + availableRect + "}";
    }
}
