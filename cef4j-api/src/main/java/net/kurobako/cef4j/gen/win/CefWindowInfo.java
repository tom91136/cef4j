// GENERATED - do not edit. Regenerate via: ./mvnw generate-sources -pl cef4j-api
// -Dcef.version=150.0.18+gdb11278+chromium-150.0.7871.213
package net.kurobako.cef4j.gen.win;

import javax.annotation.Nullable;
import javax.annotation.processing.Generated;
import net.kurobako.cef4j.gen.CefRect;
import net.kurobako.cef4j.gen.CefRuntimeStyle;
import net.kurobako.cef4j.policy.NullableBoundary;

/**
 * Structure representing window information.
 *
 * <p>Definition generated from internal/cef_types_win.h
 *
 * <pre>typedef struct _cef_window_info_t {
 *   size_t size;
 *   int ex_style;
 *   cef_string_t* window_name;
 *   int style;
 *   cef_rect_t* bounds;
 *   int64_t parent_window;
 *   int64_t menu;
 *   int windowless_rendering_enabled;
 *   int shared_texture_enabled;
 *   int external_begin_frame_enabled;
 *   int64_t window;
 *   cef_runtime_style_t runtime_style;
 * } cef_window_info_t;</pre>
 *
 * @see <a href="https://cef-builds.spotifycdn.com/docs/150.0/cef__types__win_8h.html">internal/cef_types_win.h:71</a>
 */
@Generated("./mvnw generate-sources -pl cef4j-api -Dcef.version=150.0.18+gdb11278+chromium-150.0.7871.213")
@SuppressWarnings({"SameReturnValue", "EmptyMethod", "UnusedReturnValue", "unused"})
@NullableBoundary("generated external ABI")
public final class CefWindowInfo implements net.kurobako.cef4j.gen.CefWindowInfo {

    // Native struct size, populated by the JNI layer with sizeof(struct) as required by CEF. Not user-modifiable.
    @SuppressWarnings({"FieldMayBeFinal", "CanBeFinal"})
    private volatile long size = -1;

    public final int exStyle;
    public final @Nullable String windowName;
    public final int style;
    public final @Nullable CefRect bounds;
    public final long parentWindow;
    public final long menu;
    /**
     * Set to {@code true} (1) to create the browser using windowless (off-screen) rendering. No window will be created
     * for the browser and all rendering will occur via the CefRenderHandler interface. The {@code parent_window} value
     * will be used to identify monitor info and to act as the parent window for dialogs, context menus, etc. If
     * {@code parent_window} is not provided then the main screen monitor will be used and some functionality that
     * requires a parent window may not function correctly. In order to create windowless browsers the
     * CefSettings.windowless_rendering_enabled value must be set to {@code true}. Transparent painting is enabled by
     * default but can be disabled by setting CefBrowserSettings.background_color to an opaque value.
     */
    public final int windowlessRenderingEnabled;
    /**
     * Set to {@code true} (1) to enable shared textures for windowless rendering. Only valid if
     * windowless_rendering_enabled above is also set to {@code true}. Currently only supported on Windows (D3D11).
     */
    public final int sharedTextureEnabled;
    /**
     * Set to {@code true} (1) to enable the ability to issue BeginFrame requests from the client application by calling
     * {@link net.kurobako.cef4j.gen.CefBrowserHost#sendExternalBeginFrame()}.
     */
    public final int externalBeginFrameEnabled;
    /** Handle for the new browser window. Only used with windowed rendering. */
    public final long window;
    /**
     * Optionally change the runtime style. Alloy style will always be used if {@code windowless_rendering_enabled} is
     * {@code true}. See cef_runtime_style_t documentation for details.
     */
    public final @Nullable CefRuntimeStyle runtimeStyle;

    public CefWindowInfo(
            int exStyle,
            @Nullable String windowName,
            int style,
            @Nullable CefRect bounds,
            long parentWindow,
            long menu,
            int windowlessRenderingEnabled,
            int sharedTextureEnabled,
            int externalBeginFrameEnabled,
            long window,
            @Nullable CefRuntimeStyle runtimeStyle) {
        this.exStyle = exStyle;
        this.windowName = windowName;
        this.style = style;
        this.bounds = bounds;
        this.parentWindow = parentWindow;
        this.menu = menu;
        this.windowlessRenderingEnabled = windowlessRenderingEnabled;
        this.sharedTextureEnabled = sharedTextureEnabled;
        this.externalBeginFrameEnabled = externalBeginFrameEnabled;
        this.window = window;
        this.runtimeStyle = runtimeStyle;
    }

    /** Create a mutable copy of this instance. */
    public Mutable toMutable() {
        return new Mutable(
                this.exStyle,
                this.windowName,
                this.style,
                this.bounds,
                this.parentWindow,
                this.menu,
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
        return this.exStyle == other.exStyle
                && java.util.Objects.equals(this.windowName, other.windowName)
                && this.style == other.style
                && java.util.Objects.equals(this.bounds, other.bounds)
                && this.parentWindow == other.parentWindow
                && this.menu == other.menu
                && this.windowlessRenderingEnabled == other.windowlessRenderingEnabled
                && this.sharedTextureEnabled == other.sharedTextureEnabled
                && this.externalBeginFrameEnabled == other.externalBeginFrameEnabled
                && this.window == other.window
                && java.util.Objects.equals(this.runtimeStyle, other.runtimeStyle);
    }

    @Override
    public int hashCode() {
        return java.util.Objects.hash(
                exStyle,
                windowName,
                style,
                bounds,
                parentWindow,
                menu,
                windowlessRenderingEnabled,
                sharedTextureEnabled,
                externalBeginFrameEnabled,
                window,
                runtimeStyle);
    }

    @Override
    public String toString() {
        return "CefWindowInfo{" + "size=" + (size == -1 ? "pending" : Long.toString(size)) + ", " + "exStyle=" + exStyle
                + ", " + "windowName=" + windowName + ", " + "style=" + style + ", " + "bounds=" + bounds + ", "
                + "parentWindow=" + parentWindow + ", " + "menu=" + menu + ", " + "windowlessRenderingEnabled="
                + windowlessRenderingEnabled + ", " + "sharedTextureEnabled=" + sharedTextureEnabled + ", "
                + "externalBeginFrameEnabled=" + externalBeginFrameEnabled + ", " + "window=" + window + ", "
                + "runtimeStyle=" + runtimeStyle + "}";
    }

    /**
     * Mutable variant of {@link CefWindowInfo}. Structure representing window information.
     *
     * <p>Definition generated from internal/cef_types_win.h
     *
     * @see <a
     *     href="https://cef-builds.spotifycdn.com/docs/150.0/cef__types__win_8h.html">internal/cef_types_win.h:71</a>
     */
    public static final class Mutable implements net.kurobako.cef4j.gen.CefWindowInfo.Mutable {

        // Native struct size, populated by the JNI layer with sizeof(struct) as required by CEF. Not user-modifiable.
        @SuppressWarnings({"FieldMayBeFinal", "CanBeFinal"})
        private volatile long size = -1;

        public int exStyle;
        public @Nullable String windowName;
        public int style;
        public @Nullable CefRect bounds;
        public long parentWindow;
        public long menu;
        /**
         * Set to {@code true} (1) to create the browser using windowless (off-screen) rendering. No window will be
         * created for the browser and all rendering will occur via the CefRenderHandler interface. The
         * {@code parent_window} value will be used to identify monitor info and to act as the parent window for
         * dialogs, context menus, etc. If {@code parent_window} is not provided then the main screen monitor will be
         * used and some functionality that requires a parent window may not function correctly. In order to create
         * windowless browsers the CefSettings.windowless_rendering_enabled value must be set to {@code true}.
         * Transparent painting is enabled by default but can be disabled by setting CefBrowserSettings.background_color
         * to an opaque value.
         */
        public int windowlessRenderingEnabled;
        /**
         * Set to {@code true} (1) to enable shared textures for windowless rendering. Only valid if
         * windowless_rendering_enabled above is also set to {@code true}. Currently only supported on Windows (D3D11).
         */
        public int sharedTextureEnabled;
        /**
         * Set to {@code true} (1) to enable the ability to issue BeginFrame requests from the client application by
         * calling {@link net.kurobako.cef4j.gen.CefBrowserHost#sendExternalBeginFrame()}.
         */
        public int externalBeginFrameEnabled;
        /** Handle for the new browser window. Only used with windowed rendering. */
        public long window;
        /**
         * Optionally change the runtime style. Alloy style will always be used if {@code windowless_rendering_enabled}
         * is {@code true}. See cef_runtime_style_t documentation for details.
         */
        public @Nullable CefRuntimeStyle runtimeStyle;

        public Mutable() {}

        public Mutable(
                int exStyle,
                @Nullable String windowName,
                int style,
                @Nullable CefRect bounds,
                long parentWindow,
                long menu,
                int windowlessRenderingEnabled,
                int sharedTextureEnabled,
                int externalBeginFrameEnabled,
                long window,
                @Nullable CefRuntimeStyle runtimeStyle) {
            this.exStyle = exStyle;
            this.windowName = windowName;
            this.style = style;
            this.bounds = bounds;
            this.parentWindow = parentWindow;
            this.menu = menu;
            this.windowlessRenderingEnabled = windowlessRenderingEnabled;
            this.sharedTextureEnabled = sharedTextureEnabled;
            this.externalBeginFrameEnabled = externalBeginFrameEnabled;
            this.window = window;
            this.runtimeStyle = runtimeStyle;
        }

        /** Create an immutable snapshot of this instance. */
        public CefWindowInfo toImmutable() {
            return new CefWindowInfo(
                    this.exStyle,
                    this.windowName,
                    this.style,
                    this.bounds,
                    this.parentWindow,
                    this.menu,
                    this.windowlessRenderingEnabled,
                    this.sharedTextureEnabled,
                    this.externalBeginFrameEnabled,
                    this.window,
                    this.runtimeStyle);
        }

        @Override
        public boolean equals(Object obj) {
            if (this == obj) return true;
            if (!(obj instanceof Mutable)) return false;
            Mutable other = (Mutable) obj;
            return this.exStyle == other.exStyle
                    && java.util.Objects.equals(this.windowName, other.windowName)
                    && this.style == other.style
                    && java.util.Objects.equals(this.bounds, other.bounds)
                    && this.parentWindow == other.parentWindow
                    && this.menu == other.menu
                    && this.windowlessRenderingEnabled == other.windowlessRenderingEnabled
                    && this.sharedTextureEnabled == other.sharedTextureEnabled
                    && this.externalBeginFrameEnabled == other.externalBeginFrameEnabled
                    && this.window == other.window
                    && java.util.Objects.equals(this.runtimeStyle, other.runtimeStyle);
        }

        @Override
        public int hashCode() {
            return java.util.Objects.hash(
                    exStyle,
                    windowName,
                    style,
                    bounds,
                    parentWindow,
                    menu,
                    windowlessRenderingEnabled,
                    sharedTextureEnabled,
                    externalBeginFrameEnabled,
                    window,
                    runtimeStyle);
        }

        @Override
        public String toString() {
            return "CefWindowInfo.Mutable{" + "size=" + (size == -1 ? "pending" : Long.toString(size)) + ", "
                    + "exStyle=" + exStyle + ", " + "windowName=" + windowName + ", " + "style=" + style + ", "
                    + "bounds=" + bounds + ", " + "parentWindow=" + parentWindow + ", " + "menu=" + menu + ", "
                    + "windowlessRenderingEnabled=" + windowlessRenderingEnabled + ", " + "sharedTextureEnabled="
                    + sharedTextureEnabled + ", " + "externalBeginFrameEnabled=" + externalBeginFrameEnabled + ", "
                    + "window=" + window + ", " + "runtimeStyle=" + runtimeStyle + "}";
        }
    }
}
