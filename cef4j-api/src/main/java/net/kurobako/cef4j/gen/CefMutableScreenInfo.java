// GENERATED - do not edit. Regenerate via: mvn generate-sources -pl cef4j-native
package net.kurobako.cef4j.gen;

/**
 * Mutable variant of {@link CefScreenInfo}. Screen information used when window rendering is disabled. This structure
 * is passed as a parameter to {@link CefRenderHandler#getScreenInfo(CefBrowser, CefMutableScreenInfo)} and should be
 * filled in by the client.
 *
 * <p>Definition generated from cef_types.h
 *
 * <pre>typedef struct _cef_screen_info_t {
 *   size_t size;
 *   float device_scale_factor;
 *   int depth;
 *   int depth_per_component;
 *   int is_monochrome;
 *   cef_rect_t* rect;
 *   cef_rect_t* available_rect;
 * } cef_screen_info_t;</pre>
 *
 * @see <a href="https://cef-builds.spotifycdn.com/docs/146.0/cef__types_8h.html">cef_types.h:1903</a>
 */
public final class CefMutableScreenInfo {

    // Native struct size — set by JNI, not user-modifiable.
    @SuppressWarnings("FieldMayBeFinal")
    private volatile long size = -1;

    public float deviceScaleFactor;
    public int depth;
    public int depthPerComponent;
    public int isMonochrome;
    public CefRect rect;
    public CefRect availableRect;

    public CefMutableScreenInfo() {}

    public CefMutableScreenInfo(
            float deviceScaleFactor,
            int depth,
            int depthPerComponent,
            int isMonochrome,
            CefRect rect,
            CefRect availableRect) {
        this.deviceScaleFactor = deviceScaleFactor;
        this.depth = depth;
        this.depthPerComponent = depthPerComponent;
        this.isMonochrome = isMonochrome;
        this.rect = rect;
        this.availableRect = availableRect;
    }

    /** Create an immutable snapshot of this instance. */
    public CefScreenInfo toImmutable() {
        return new CefScreenInfo(
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
        if (!(obj instanceof CefMutableScreenInfo)) return false;
        CefMutableScreenInfo other = (CefMutableScreenInfo) obj;
        return this.deviceScaleFactor == other.deviceScaleFactor
                && this.depth == other.depth
                && this.depthPerComponent == other.depthPerComponent
                && this.isMonochrome == other.isMonochrome
                && java.util.Objects.equals(this.rect, other.rect)
                && java.util.Objects.equals(this.availableRect, other.availableRect);
    }

    @Override
    public int hashCode() {
        return java.util.Objects.hash(deviceScaleFactor, depth, depthPerComponent, isMonochrome, rect, availableRect);
    }

    @Override
    public String toString() {
        return "CefMutableScreenInfo{" + "size=" + (size == -1 ? "pending" : Long.toString(size)) + ", "
                + "deviceScaleFactor=" + deviceScaleFactor + ", " + "depth=" + depth + ", " + "depthPerComponent="
                + depthPerComponent + ", " + "isMonochrome=" + isMonochrome + ", " + "rect=" + rect + ", "
                + "availableRect=" + availableRect + "}";
    }
}
