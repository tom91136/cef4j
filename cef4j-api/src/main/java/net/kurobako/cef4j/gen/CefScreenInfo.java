// GENERATED - do not edit. Regenerate via: mvn generate-sources -pl cef4j-platform -Dcef.version=150.0.18+gdb11278+chromium-150.0.7871.213
package net.kurobako.cef4j.gen;

import javax.annotation.processing.Generated;
import javax.annotation.Nullable;

/**
 * Screen information used when window rendering is disabled. This structure is passed as a parameter to {@link net.kurobako.cef4j.gen.CefRenderHandler#getScreenInfo(CefBrowser, CefScreenInfo.Mutable)} and should be filled in by the client.
 * <p>Definition generated from internal/cef_types.h
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
 * @see <a href="https://cef-builds.spotifycdn.com/docs/150.0/cef__types_8h.html">internal/cef_types.h:1918</a>
 */
@Generated("mvn generate-sources -pl cef4j-platform -Dcef.version=150.0.18+gdb11278+chromium-150.0.7871.213")
@SuppressWarnings({"SameReturnValue", "EmptyMethod", "UnusedReturnValue", "unused", "NullableForbidden"})
public final class CefScreenInfo {

    // Native struct size, populated by the JNI layer with sizeof(struct) as required by CEF. Not user-modifiable.
    @SuppressWarnings({"FieldMayBeFinal", "CanBeFinal"})
    private volatile long size = -1;

        /**
         * Device scale factor. Specifies the ratio between physical and logical pixels. 
         */    public final float deviceScaleFactor;
        /**
         * The screen depth in bits per pixel. 
         */    public final int depth;
        /**
         * The bits per color component. This assumes that the colors are balanced equally. 
         */    public final int depthPerComponent;
        /**
         * This can be {@code true} for black and white printers. 
         */    public final int isMonochrome;
        /**
         * This is set from the rcMonitor member of MONITORINFOEX, to whit: "A RECT structure that specifies the display monitor rectangle, expressed in virtual-screen coordinates. Note that if the monitor is not the primary display monitor, some of the rectangle's coordinates may be negative values." The {@code rect} and {@code available_rect} properties are used to determine the available surface for rendering popup views. 
         */    public final @Nullable CefRect rect;
        /**
         * This is set from the rcWork member of MONITORINFOEX, to whit: "A RECT structure that specifies the work area rectangle of the display monitor that can be used by applications, expressed in virtual-screen coordinates. Windows uses this rectangle to maximize an application on the monitor. The rest of the area in rcMonitor contains system windows such as the task bar and side bars. Note that if the monitor is not the primary display monitor, some of the rectangle's coordinates may be negative values". The {@code rect} and {@code available_rect} properties are used to determine the available surface for rendering popup views. 
         */    public final @Nullable CefRect availableRect;

    public CefScreenInfo(float deviceScaleFactor, int depth, int depthPerComponent, int isMonochrome, @Nullable CefRect rect, @Nullable CefRect availableRect) {
        this.deviceScaleFactor = deviceScaleFactor;
        this.depth = depth;
        this.depthPerComponent = depthPerComponent;
        this.isMonochrome = isMonochrome;
        this.rect = rect;
        this.availableRect = availableRect;
    }

    /** Create a mutable copy of this instance. */
    public Mutable toMutable() {
        return new Mutable(this.deviceScaleFactor, this.depth, this.depthPerComponent, this.isMonochrome, this.rect, this.availableRect);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (!(obj instanceof CefScreenInfo)) return false;
        CefScreenInfo other = (CefScreenInfo) obj;
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
        return "CefScreenInfo{" + "size=" + (size == -1 ? "pending" : Long.toString(size)) + ", " + "deviceScaleFactor=" + deviceScaleFactor + ", " + "depth=" + depth + ", " + "depthPerComponent=" + depthPerComponent + ", " + "isMonochrome=" + isMonochrome + ", " + "rect=" + rect + ", " + "availableRect=" + availableRect + "}";
    }

    /**
     * Mutable variant of {@link CefScreenInfo}. Screen information used when window rendering is disabled. This structure is passed as a parameter to {@link net.kurobako.cef4j.gen.CefRenderHandler#getScreenInfo(CefBrowser, CefScreenInfo.Mutable)} and should be filled in by the client.
     * <p>Definition generated from internal/cef_types.h
     *
     * @see <a href="https://cef-builds.spotifycdn.com/docs/150.0/cef__types_8h.html">internal/cef_types.h:1918</a>
     */
    public static final class Mutable {

    // Native struct size, populated by the JNI layer with sizeof(struct) as required by CEF. Not user-modifiable.
    @SuppressWarnings({"FieldMayBeFinal", "CanBeFinal"})
    private volatile long size = -1;

            /**
             * Device scale factor. Specifies the ratio between physical and logical pixels. 
             */        public float deviceScaleFactor;
            /**
             * The screen depth in bits per pixel. 
             */        public int depth;
            /**
             * The bits per color component. This assumes that the colors are balanced equally. 
             */        public int depthPerComponent;
            /**
             * This can be {@code true} for black and white printers. 
             */        public int isMonochrome;
            /**
             * This is set from the rcMonitor member of MONITORINFOEX, to whit: "A RECT structure that specifies the display monitor rectangle, expressed in virtual-screen coordinates. Note that if the monitor is not the primary display monitor, some of the rectangle's coordinates may be negative values." The {@code rect} and {@code available_rect} properties are used to determine the available surface for rendering popup views. 
             */        public @Nullable CefRect rect;
            /**
             * This is set from the rcWork member of MONITORINFOEX, to whit: "A RECT structure that specifies the work area rectangle of the display monitor that can be used by applications, expressed in virtual-screen coordinates. Windows uses this rectangle to maximize an application on the monitor. The rest of the area in rcMonitor contains system windows such as the task bar and side bars. Note that if the monitor is not the primary display monitor, some of the rectangle's coordinates may be negative values". The {@code rect} and {@code available_rect} properties are used to determine the available surface for rendering popup views. 
             */        public @Nullable CefRect availableRect;

        public Mutable() {}

        public Mutable(float deviceScaleFactor, int depth, int depthPerComponent, int isMonochrome, @Nullable CefRect rect, @Nullable CefRect availableRect) {
            this.deviceScaleFactor = deviceScaleFactor;
            this.depth = depth;
            this.depthPerComponent = depthPerComponent;
            this.isMonochrome = isMonochrome;
            this.rect = rect;
            this.availableRect = availableRect;
        }

        /** Create an immutable snapshot of this instance. */
        public CefScreenInfo toImmutable() {
            return new CefScreenInfo(this.deviceScaleFactor, this.depth, this.depthPerComponent, this.isMonochrome, this.rect, this.availableRect);
        }

        @Override
        public boolean equals(Object obj) {
            if (this == obj) return true;
            if (!(obj instanceof Mutable)) return false;
            Mutable other = (Mutable) obj;
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
            return "CefScreenInfo.Mutable{" + "size=" + (size == -1 ? "pending" : Long.toString(size)) + ", " + "deviceScaleFactor=" + deviceScaleFactor + ", " + "depth=" + depth + ", " + "depthPerComponent=" + depthPerComponent + ", " + "isMonochrome=" + isMonochrome + ", " + "rect=" + rect + ", " + "availableRect=" + availableRect + "}";
        }
    }
}
