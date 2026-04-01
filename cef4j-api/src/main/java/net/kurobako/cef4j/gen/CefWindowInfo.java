// GENERATED - do not edit. Regenerate via: mvn generate-sources -pl cef4j-native
package net.kurobako.cef4j.gen;

/**
 * Class representing window information.
 *
 * <p>Definition generated from cef_types_linux.h
 *
 * <pre>typedef struct _cef_window_info_t {
 *   size_t size;
 *   cef_string_t* window_name;
 *   cef_rect_t* bounds;
 *   int64_t parent_window;
 *   int windowless_rendering_enabled;
 *   int shared_texture_enabled;
 *   int external_begin_frame_enabled;
 *   int64_t window;
 *   cef_runtime_style_t runtime_style;
 * } cef_window_info_t;</pre>
 *
 * @see <a href="https://cef-builds.spotifycdn.com/docs/146.0/cef__types__linux_8h.html">cef_types_linux.h:85</a>
 */
public final class CefWindowInfo {

    // Native struct size — set by JNI, not user-modifiable.
    @SuppressWarnings("FieldMayBeFinal")
    private volatile long size = -1;

    public final String windowName;
    public final CefRect bounds;
    public final long parentWindow;
    public final int windowlessRenderingEnabled;
    public final int sharedTextureEnabled;
    public final int externalBeginFrameEnabled;
    public final long window;
    public final CefRuntimeStyle runtimeStyle;

    public CefWindowInfo(
            String windowName,
            CefRect bounds,
            long parentWindow,
            int windowlessRenderingEnabled,
            int sharedTextureEnabled,
            int externalBeginFrameEnabled,
            long window,
            CefRuntimeStyle runtimeStyle) {
        this.windowName = windowName;
        this.bounds = bounds;
        this.parentWindow = parentWindow;
        this.windowlessRenderingEnabled = windowlessRenderingEnabled;
        this.sharedTextureEnabled = sharedTextureEnabled;
        this.externalBeginFrameEnabled = externalBeginFrameEnabled;
        this.window = window;
        this.runtimeStyle = runtimeStyle;
    }

    /** Create a mutable copy of this instance. */
    public CefMutableWindowInfo toMutable() {
        return new CefMutableWindowInfo(
                this.windowName,
                this.bounds,
                this.parentWindow,
                this.windowlessRenderingEnabled,
                this.sharedTextureEnabled,
                this.externalBeginFrameEnabled,
                this.window,
                this.runtimeStyle);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (!(obj instanceof CefWindowInfo)) return false;
        CefWindowInfo other = (CefWindowInfo) obj;
        return java.util.Objects.equals(this.windowName, other.windowName)
                && java.util.Objects.equals(this.bounds, other.bounds)
                && this.parentWindow == other.parentWindow
                && this.windowlessRenderingEnabled == other.windowlessRenderingEnabled
                && this.sharedTextureEnabled == other.sharedTextureEnabled
                && this.externalBeginFrameEnabled == other.externalBeginFrameEnabled
                && this.window == other.window
                && java.util.Objects.equals(this.runtimeStyle, other.runtimeStyle);
    }

    @Override
    public int hashCode() {
        return java.util.Objects.hash(
                windowName,
                bounds,
                parentWindow,
                windowlessRenderingEnabled,
                sharedTextureEnabled,
                externalBeginFrameEnabled,
                window,
                runtimeStyle);
    }

    @Override
    public String toString() {
        return "CefWindowInfo{" + "size=" + (size == -1 ? "pending" : Long.toString(size)) + ", " + "windowName="
                + windowName + ", " + "bounds=" + bounds + ", " + "parentWindow=" + parentWindow + ", "
                + "windowlessRenderingEnabled=" + windowlessRenderingEnabled + ", " + "sharedTextureEnabled="
                + sharedTextureEnabled + ", " + "externalBeginFrameEnabled=" + externalBeginFrameEnabled + ", "
                + "window=" + window + ", " + "runtimeStyle=" + runtimeStyle + "}";
    }
}
