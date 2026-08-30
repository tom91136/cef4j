// GENERATED - do not edit. Regenerate via: ./mvnw generate-sources -pl cef4j-api
// -Dcef.version=150.0.18+gdb11278+chromium-150.0.7871.213
package net.kurobako.cef4j.gen.mac;

import javax.annotation.Nullable;
import javax.annotation.processing.Generated;
import net.kurobako.cef4j.gen.CefAcceleratedPaintInfoCommon;
import net.kurobako.cef4j.gen.CefColorType;
import net.kurobako.cef4j.policy.NullableBoundary;

/**
 * Structure containing shared texture information for the OnAcceleratedPaint callback. Resources will be released to
 * the underlying pool for reuse when the callback returns from client code.
 *
 * <p>Definition generated from internal/cef_types_mac.h
 *
 * <pre>typedef struct _cef_accelerated_paint_info_t {
 *   size_t size;
 *   int64_t shared_texture_io_surface;
 *   cef_color_type_t format;
 *   cef_accelerated_paint_info_common_t* extra;
 * } cef_accelerated_paint_info_t;</pre>
 *
 * @see <a href="https://cef-builds.spotifycdn.com/docs/150.0/cef__types__mac_8h.html">internal/cef_types_mac.h:158</a>
 */
@Generated("./mvnw generate-sources -pl cef4j-api -Dcef.version=150.0.18+gdb11278+chromium-150.0.7871.213")
@SuppressWarnings({"SameReturnValue", "EmptyMethod", "UnusedReturnValue", "unused"})
@NullableBoundary("generated external ABI")
public final class CefAcceleratedPaintInfo implements net.kurobako.cef4j.gen.CefAcceleratedPaintInfo {

    // Native struct size, populated by the JNI layer with sizeof(struct) as required by CEF. Not user-modifiable.
    @SuppressWarnings({"FieldMayBeFinal", "CanBeFinal"})
    private volatile long size = -1;

    /** Handle for the shared texture IOSurface. */
    public final long sharedTextureIoSurface;
    /** The pixel format of the texture. */
    public final @Nullable CefColorType format;
    /** The extra common info. */
    public final @Nullable CefAcceleratedPaintInfoCommon extra;

    public CefAcceleratedPaintInfo(
            long sharedTextureIoSurface, @Nullable CefColorType format, @Nullable CefAcceleratedPaintInfoCommon extra) {
        this.sharedTextureIoSurface = sharedTextureIoSurface;
        this.format = format;
        this.extra = extra;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (!(obj instanceof CefAcceleratedPaintInfo)) return false;
        CefAcceleratedPaintInfo other = (CefAcceleratedPaintInfo) obj;
        return this.sharedTextureIoSurface == other.sharedTextureIoSurface
                && java.util.Objects.equals(this.format, other.format)
                && java.util.Objects.equals(this.extra, other.extra);
    }

    @Override
    public int hashCode() {
        return java.util.Objects.hash(sharedTextureIoSurface, format, extra);
    }

    @Override
    public String toString() {
        return "CefAcceleratedPaintInfo{" + "size=" + (size == -1 ? "pending" : Long.toString(size)) + ", "
                + "sharedTextureIoSurface=" + sharedTextureIoSurface + ", " + "format=" + format + ", " + "extra="
                + extra + "}";
    }
}
