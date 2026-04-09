// GENERATED - do not edit. Regenerate via: mvn generate-sources -pl cef4j-native -Dcef.version=146.0.9+g3ca6a87+chromium-146.0.7680.165
package net.kurobako.cef4j.gen;

import javax.annotation.processing.Generated;

/**
 * Class representing window information.
 * <p>Definition generated from internal/cef_types_linux.h
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
 * @see <a href="https://cef-builds.spotifycdn.com/docs/146.0/cef__types__linux_8h.html">internal/cef_types_linux.h:85</a>
 */
@Generated("mvn generate-sources -pl cef4j-native -Dcef.version=146.0.9+g3ca6a87+chromium-146.0.7680.165")
@SuppressWarnings({"SameReturnValue", "EmptyMethod", "UnusedReturnValue", "unused"})
public final class CefWindowInfo {

    // Native struct size, populated by the JNI layer with sizeof(struct) as required by CEF. Not user-modifiable.
    @SuppressWarnings({"FieldMayBeFinal", "CanBeFinal"})
    private volatile long size = -1;

        /**
         * The initial title of the window, to be set when the window is created. Some layout managers (e.g., Compiz) can look at the window title in order to decide where to place the window when it is created. When this attribute is not empty, the window title will be set before the window is mapped to the dispay. Otherwise the title will be initially empty. 
         */    public final String windowName;
        /**
         * Initial window bounds. 
         */    public final CefRect bounds;
        /**
         * Pointer for the parent window. 
         */    public final long parentWindow;
        /**
         * Set to {@code true} (1) to create the browser using windowless (off-screen) rendering. No window will be created for the browser and all rendering will occur via the CefRenderHandler interface. The {@code parent_window} value will be used to identify monitor info and to act as the parent window for dialogs, context menus, etc. If {@code parent_window} is not provided then the main screen monitor will be used and some functionality that requires a parent window may not function correctly. In order to create windowless browsers the CefSettings.windowless_rendering_enabled value must be set to {@code true}. Transparent painting is enabled by default but can be disabled by setting CefBrowserSettings.background_color to an opaque value. 
         */    public final int windowlessRenderingEnabled;
        /**
         * Set to {@code true} (1) to enable shared textures for windowless rendering. Only valid if windowless_rendering_enabled above is also set to {@code true}. Currently only supported on Windows (D3D11). 
         */    public final int sharedTextureEnabled;
        /**
         * Set to {@code true} (1) to enable the ability to issue BeginFrame requests from the client application by calling {@link net.kurobako.cef4j.gen.CefBrowserHost#sendExternalBeginFrame()}. 
         */    public final int externalBeginFrameEnabled;
        /**
         * Pointer for the new browser window. Only used with windowed rendering. 
         */    public final long window;
        /**
         * Optionally change the runtime style. Alloy style will always be used if {@code windowless_rendering_enabled} is {@code true}. See cef_runtime_style_t documentation for details. 
         */    public final CefRuntimeStyle runtimeStyle;

    public CefWindowInfo(String windowName, CefRect bounds, long parentWindow, int windowlessRenderingEnabled, int sharedTextureEnabled, int externalBeginFrameEnabled, long window, CefRuntimeStyle runtimeStyle) {
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
    public Mutable toMutable() {
        return new Mutable(this.windowName, this.bounds, this.parentWindow, this.windowlessRenderingEnabled, this.sharedTextureEnabled, this.externalBeginFrameEnabled, this.window, this.runtimeStyle);
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
        return java.util.Objects.hash(windowName, bounds, parentWindow, windowlessRenderingEnabled, sharedTextureEnabled, externalBeginFrameEnabled, window, runtimeStyle);
    }

    @Override
    public String toString() {
        return "CefWindowInfo{" + "size=" + (size == -1 ? "pending" : Long.toString(size)) + ", " + "windowName=" + windowName + ", " + "bounds=" + bounds + ", " + "parentWindow=" + parentWindow + ", " + "windowlessRenderingEnabled=" + windowlessRenderingEnabled + ", " + "sharedTextureEnabled=" + sharedTextureEnabled + ", " + "externalBeginFrameEnabled=" + externalBeginFrameEnabled + ", " + "window=" + window + ", " + "runtimeStyle=" + runtimeStyle + "}";
    }

    /**
     * Mutable variant of {@link CefWindowInfo}. Class representing window information.
     * <p>Definition generated from internal/cef_types_linux.h
     *
     * @see <a href="https://cef-builds.spotifycdn.com/docs/146.0/cef__types__linux_8h.html">internal/cef_types_linux.h:85</a>
     */
    public static final class Mutable {

    // Native struct size, populated by the JNI layer with sizeof(struct) as required by CEF. Not user-modifiable.
    @SuppressWarnings({"FieldMayBeFinal", "CanBeFinal"})
    private volatile long size = -1;

            /**
             * The initial title of the window, to be set when the window is created. Some layout managers (e.g., Compiz) can look at the window title in order to decide where to place the window when it is created. When this attribute is not empty, the window title will be set before the window is mapped to the dispay. Otherwise the title will be initially empty. 
             */        public String windowName;
            /**
             * Initial window bounds. 
             */        public CefRect bounds;
            /**
             * Pointer for the parent window. 
             */        public long parentWindow;
            /**
             * Set to {@code true} (1) to create the browser using windowless (off-screen) rendering. No window will be created for the browser and all rendering will occur via the CefRenderHandler interface. The {@code parent_window} value will be used to identify monitor info and to act as the parent window for dialogs, context menus, etc. If {@code parent_window} is not provided then the main screen monitor will be used and some functionality that requires a parent window may not function correctly. In order to create windowless browsers the CefSettings.windowless_rendering_enabled value must be set to {@code true}. Transparent painting is enabled by default but can be disabled by setting CefBrowserSettings.background_color to an opaque value. 
             */        public int windowlessRenderingEnabled;
            /**
             * Set to {@code true} (1) to enable shared textures for windowless rendering. Only valid if windowless_rendering_enabled above is also set to {@code true}. Currently only supported on Windows (D3D11). 
             */        public int sharedTextureEnabled;
            /**
             * Set to {@code true} (1) to enable the ability to issue BeginFrame requests from the client application by calling {@link net.kurobako.cef4j.gen.CefBrowserHost#sendExternalBeginFrame()}. 
             */        public int externalBeginFrameEnabled;
            /**
             * Pointer for the new browser window. Only used with windowed rendering. 
             */        public long window;
            /**
             * Optionally change the runtime style. Alloy style will always be used if {@code windowless_rendering_enabled} is {@code true}. See cef_runtime_style_t documentation for details. 
             */        public CefRuntimeStyle runtimeStyle;

        public Mutable() {}

        public Mutable(String windowName, CefRect bounds, long parentWindow, int windowlessRenderingEnabled, int sharedTextureEnabled, int externalBeginFrameEnabled, long window, CefRuntimeStyle runtimeStyle) {
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
            return new CefWindowInfo(this.windowName, this.bounds, this.parentWindow, this.windowlessRenderingEnabled, this.sharedTextureEnabled, this.externalBeginFrameEnabled, this.window, this.runtimeStyle);
        }

        @Override
        public boolean equals(Object obj) {
            if (this == obj) return true;
            if (!(obj instanceof Mutable)) return false;
            Mutable other = (Mutable) obj;
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
            return java.util.Objects.hash(windowName, bounds, parentWindow, windowlessRenderingEnabled, sharedTextureEnabled, externalBeginFrameEnabled, window, runtimeStyle);
        }

        @Override
        public String toString() {
            return "CefWindowInfo.Mutable{" + "size=" + (size == -1 ? "pending" : Long.toString(size)) + ", " + "windowName=" + windowName + ", " + "bounds=" + bounds + ", " + "parentWindow=" + parentWindow + ", " + "windowlessRenderingEnabled=" + windowlessRenderingEnabled + ", " + "sharedTextureEnabled=" + sharedTextureEnabled + ", " + "externalBeginFrameEnabled=" + externalBeginFrameEnabled + ", " + "window=" + window + ", " + "runtimeStyle=" + runtimeStyle + "}";
        }
    }
}
