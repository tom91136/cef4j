// GENERATED - do not edit. Regenerate via: mvn generate-sources -pl cef4j-platform -Dcef.version=150.0.18+gdb11278+chromium-150.0.7871.213
package net.kurobako.cef4j.gen.mac;

import javax.annotation.processing.Generated;
import javax.annotation.Nullable;
import net.kurobako.cef4j.gen.CefRect;
import net.kurobako.cef4j.gen.CefRuntimeStyle;

/**
 * Class representing window information.
 * <p>Definition generated from internal/cef_types_mac.h
 * <pre>typedef struct _cef_window_info_t {
 *   size_t size;
 *   cef_string_t* window_name;
 *   cef_rect_t* bounds;
 *   int hidden;
 *   int64_t parent_view;
 *   int windowless_rendering_enabled;
 *   int shared_texture_enabled;
 *   int external_begin_frame_enabled;
 *   int64_t view;
 *   cef_runtime_style_t runtime_style;
 * } cef_window_info_t;</pre>
 *
 * @see <a href="https://cef-builds.spotifycdn.com/docs/150.0/cef__types__mac_8h.html">internal/cef_types_mac.h:91</a>
 */
@Generated("mvn generate-sources -pl cef4j-platform -Dcef.version=150.0.18+gdb11278+chromium-150.0.7871.213")
@SuppressWarnings({"SameReturnValue", "EmptyMethod", "UnusedReturnValue", "unused", "NullableForbidden"})
public final class CefWindowInfo implements net.kurobako.cef4j.gen.CefWindowInfo {

    // Native struct size, populated by the JNI layer with sizeof(struct) as required by CEF. Not user-modifiable.
    @SuppressWarnings({"FieldMayBeFinal", "CanBeFinal"})
    private volatile long size = -1;

    public final @Nullable String windowName;
        /**
         * Initial window bounds. 
         */    public final @Nullable CefRect bounds;
        /**
         * Set to {@code true} (1) to create the view initially hidden. 
         */    public final int hidden;
        /**
         * NSView pointer for the parent view. 
         */    public final long parentView;
        /**
         * Set to {@code true} (1) to create the browser using windowless (off-screen) rendering. No view will be created for the browser and all rendering will occur via the CefRenderHandler interface. The {@code parent_view} value will be used to identify monitor info and to act as the parent view for dialogs, context menus, etc. If {@code parent_view} is not provided then the main screen monitor will be used and some functionality that requires a parent view may not function correctly. In order to create windowless browsers the CefSettings.windowless_rendering_enabled value must be set to {@code true}. Transparent painting is enabled by default but can be disabled by setting CefBrowserSettings.background_color to an opaque value. 
         */    public final int windowlessRenderingEnabled;
        /**
         * Set to {@code true} (1) to enable shared textures for windowless rendering. Only valid if windowless_rendering_enabled above is also set to {@code true}. Currently only supported on Windows (D3D11). 
         */    public final int sharedTextureEnabled;
        /**
         * Set to {@code true} (1) to enable the ability to issue BeginFrame from the client application. 
         */    public final int externalBeginFrameEnabled;
        /**
         * NSView pointer for the new browser view. Only used with windowed rendering. 
         */    public final long view;
        /**
         * Optionally change the runtime style. Alloy style will always be used if {@code windowless_rendering_enabled} is {@code true} or if {@code parent_view} is provided. See cef_runtime_style_t documentation for details. 
         */    public final @Nullable CefRuntimeStyle runtimeStyle;

    public CefWindowInfo(@Nullable String windowName, @Nullable CefRect bounds, int hidden, long parentView, int windowlessRenderingEnabled, int sharedTextureEnabled, int externalBeginFrameEnabled, long view, @Nullable CefRuntimeStyle runtimeStyle) {
        this.windowName = windowName;
        this.bounds = bounds;
        this.hidden = hidden;
        this.parentView = parentView;
        this.windowlessRenderingEnabled = windowlessRenderingEnabled;
        this.sharedTextureEnabled = sharedTextureEnabled;
        this.externalBeginFrameEnabled = externalBeginFrameEnabled;
        this.view = view;
        this.runtimeStyle = runtimeStyle;
    }

    /** Create a mutable copy of this instance. */
    public Mutable toMutable() {
        return new Mutable(this.windowName, this.bounds, this.hidden, this.parentView, this.windowlessRenderingEnabled, this.sharedTextureEnabled, this.externalBeginFrameEnabled, this.view, this.runtimeStyle);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (!(obj instanceof CefWindowInfo)) return false;
        CefWindowInfo other = (CefWindowInfo) obj;
        return java.util.Objects.equals(this.windowName, other.windowName)
                    && java.util.Objects.equals(this.bounds, other.bounds)
                    && this.hidden == other.hidden
                    && this.parentView == other.parentView
                    && this.windowlessRenderingEnabled == other.windowlessRenderingEnabled
                    && this.sharedTextureEnabled == other.sharedTextureEnabled
                    && this.externalBeginFrameEnabled == other.externalBeginFrameEnabled
                    && this.view == other.view
                    && java.util.Objects.equals(this.runtimeStyle, other.runtimeStyle);
    }

    @Override
    public int hashCode() {
        return java.util.Objects.hash(windowName, bounds, hidden, parentView, windowlessRenderingEnabled, sharedTextureEnabled, externalBeginFrameEnabled, view, runtimeStyle);
    }

    @Override
    public String toString() {
        return "CefWindowInfo{" + "size=" + (size == -1 ? "pending" : Long.toString(size)) + ", " + "windowName=" + windowName + ", " + "bounds=" + bounds + ", " + "hidden=" + hidden + ", " + "parentView=" + parentView + ", " + "windowlessRenderingEnabled=" + windowlessRenderingEnabled + ", " + "sharedTextureEnabled=" + sharedTextureEnabled + ", " + "externalBeginFrameEnabled=" + externalBeginFrameEnabled + ", " + "view=" + view + ", " + "runtimeStyle=" + runtimeStyle + "}";
    }

    /**
     * Mutable variant of {@link CefWindowInfo}. Class representing window information.
     * <p>Definition generated from internal/cef_types_mac.h
     *
     * @see <a href="https://cef-builds.spotifycdn.com/docs/150.0/cef__types__mac_8h.html">internal/cef_types_mac.h:91</a>
     */
    public static final class Mutable implements net.kurobako.cef4j.gen.CefWindowInfo.Mutable {

    // Native struct size, populated by the JNI layer with sizeof(struct) as required by CEF. Not user-modifiable.
    @SuppressWarnings({"FieldMayBeFinal", "CanBeFinal"})
    private volatile long size = -1;

        public @Nullable String windowName;
            /**
             * Initial window bounds. 
             */        public @Nullable CefRect bounds;
            /**
             * Set to {@code true} (1) to create the view initially hidden. 
             */        public int hidden;
            /**
             * NSView pointer for the parent view. 
             */        public long parentView;
            /**
             * Set to {@code true} (1) to create the browser using windowless (off-screen) rendering. No view will be created for the browser and all rendering will occur via the CefRenderHandler interface. The {@code parent_view} value will be used to identify monitor info and to act as the parent view for dialogs, context menus, etc. If {@code parent_view} is not provided then the main screen monitor will be used and some functionality that requires a parent view may not function correctly. In order to create windowless browsers the CefSettings.windowless_rendering_enabled value must be set to {@code true}. Transparent painting is enabled by default but can be disabled by setting CefBrowserSettings.background_color to an opaque value. 
             */        public int windowlessRenderingEnabled;
            /**
             * Set to {@code true} (1) to enable shared textures for windowless rendering. Only valid if windowless_rendering_enabled above is also set to {@code true}. Currently only supported on Windows (D3D11). 
             */        public int sharedTextureEnabled;
            /**
             * Set to {@code true} (1) to enable the ability to issue BeginFrame from the client application. 
             */        public int externalBeginFrameEnabled;
            /**
             * NSView pointer for the new browser view. Only used with windowed rendering. 
             */        public long view;
            /**
             * Optionally change the runtime style. Alloy style will always be used if {@code windowless_rendering_enabled} is {@code true} or if {@code parent_view} is provided. See cef_runtime_style_t documentation for details. 
             */        public @Nullable CefRuntimeStyle runtimeStyle;

        public Mutable() {}

        public Mutable(@Nullable String windowName, @Nullable CefRect bounds, int hidden, long parentView, int windowlessRenderingEnabled, int sharedTextureEnabled, int externalBeginFrameEnabled, long view, @Nullable CefRuntimeStyle runtimeStyle) {
            this.windowName = windowName;
            this.bounds = bounds;
            this.hidden = hidden;
            this.parentView = parentView;
            this.windowlessRenderingEnabled = windowlessRenderingEnabled;
            this.sharedTextureEnabled = sharedTextureEnabled;
            this.externalBeginFrameEnabled = externalBeginFrameEnabled;
            this.view = view;
            this.runtimeStyle = runtimeStyle;
        }

        /** Create an immutable snapshot of this instance. */
        public CefWindowInfo toImmutable() {
            return new CefWindowInfo(this.windowName, this.bounds, this.hidden, this.parentView, this.windowlessRenderingEnabled, this.sharedTextureEnabled, this.externalBeginFrameEnabled, this.view, this.runtimeStyle);
        }

        @Override
        public boolean equals(Object obj) {
            if (this == obj) return true;
            if (!(obj instanceof Mutable)) return false;
            Mutable other = (Mutable) obj;
            return java.util.Objects.equals(this.windowName, other.windowName)
                        && java.util.Objects.equals(this.bounds, other.bounds)
                        && this.hidden == other.hidden
                        && this.parentView == other.parentView
                        && this.windowlessRenderingEnabled == other.windowlessRenderingEnabled
                        && this.sharedTextureEnabled == other.sharedTextureEnabled
                        && this.externalBeginFrameEnabled == other.externalBeginFrameEnabled
                        && this.view == other.view
                        && java.util.Objects.equals(this.runtimeStyle, other.runtimeStyle);
        }

        @Override
        public int hashCode() {
            return java.util.Objects.hash(windowName, bounds, hidden, parentView, windowlessRenderingEnabled, sharedTextureEnabled, externalBeginFrameEnabled, view, runtimeStyle);
        }

        @Override
        public String toString() {
            return "CefWindowInfo.Mutable{" + "size=" + (size == -1 ? "pending" : Long.toString(size)) + ", " + "windowName=" + windowName + ", " + "bounds=" + bounds + ", " + "hidden=" + hidden + ", " + "parentView=" + parentView + ", " + "windowlessRenderingEnabled=" + windowlessRenderingEnabled + ", " + "sharedTextureEnabled=" + sharedTextureEnabled + ", " + "externalBeginFrameEnabled=" + externalBeginFrameEnabled + ", " + "view=" + view + ", " + "runtimeStyle=" + runtimeStyle + "}";
        }
    }
}
