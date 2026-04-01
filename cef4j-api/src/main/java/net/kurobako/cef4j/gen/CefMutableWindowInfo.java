// GENERATED - do not edit. Regenerate via: mvn generate-sources -pl cef4j-native
package net.kurobako.cef4j.gen;

/**
 * Mutable variant of {@link CefWindowInfo}. Class representing window information.
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
public final class CefMutableWindowInfo {

    // Native struct size — set by JNI, not user-modifiable.
    @SuppressWarnings("FieldMayBeFinal")
    private volatile long size = -1;

    public String windowName;
    public CefRect bounds;
    public long parentWindow;
    public int windowlessRenderingEnabled;
    public int sharedTextureEnabled;
    public int externalBeginFrameEnabled;
    public long window;
    public CefRuntimeStyle runtimeStyle;

    public CefMutableWindowInfo() {}

    public CefMutableWindowInfo(
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

    /** Create an immutable snapshot of this instance. */
    public CefWindowInfo toImmutable() {
        return new CefWindowInfo(
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
        if (!(obj instanceof CefMutableWindowInfo)) return false;
        CefMutableWindowInfo other = (CefMutableWindowInfo) obj;
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
        return "CefMutableWindowInfo{" + "size=" + (size == -1 ? "pending" : Long.toString(size)) + ", " + "windowName="
                + windowName + ", " + "bounds=" + bounds + ", " + "parentWindow=" + parentWindow + ", "
                + "windowlessRenderingEnabled=" + windowlessRenderingEnabled + ", " + "sharedTextureEnabled="
                + sharedTextureEnabled + ", " + "externalBeginFrameEnabled=" + externalBeginFrameEnabled + ", "
                + "window=" + window + ", " + "runtimeStyle=" + runtimeStyle + "}";
    }
}
