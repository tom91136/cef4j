// GENERATED - do not edit. Regenerate via: mvn generate-sources -pl cef4j-native
package net.kurobako.cef4j.gen;

/**
 * Structure containing shared texture information for the OnAcceleratedPaint callback. Resources will be released to
 * the underlying pool for reuse when the callback returns from client code.
 *
 * <p>Definition generated from cef_types_linux.h
 *
 * <pre>typedef struct _cef_accelerated_paint_info_t {
 *   size_t size;
 *   int plane_count;
 *   int64_t modifier;
 *   cef_color_type_t format;
 *   cef_accelerated_paint_info_common_t* extra;
 * } cef_accelerated_paint_info_t;</pre>
 *
 * @see <a href="https://cef-builds.spotifycdn.com/docs/146.0/cef__types__linux_8h.html">cef_types_linux.h:176</a>
 */
public final class CefAcceleratedPaintInfo {

    // Native struct size, populated by the JNI layer with sizeof(struct) as required by CEF. Not user-modifiable.
    @SuppressWarnings("FieldMayBeFinal")
    private volatile long size = -1;

    /** Plane count. */
    public final int planeCount;
    /** Modifier could be used with EGL driver. */
    public final long modifier;
    /** The pixel format of the texture. */
    public final CefColorType format;
    /** The extra common info. */
    public final CefAcceleratedPaintInfoCommon extra;

    public CefAcceleratedPaintInfo(
            int planeCount, long modifier, CefColorType format, CefAcceleratedPaintInfoCommon extra) {
        this.planeCount = planeCount;
        this.modifier = modifier;
        this.format = format;
        this.extra = extra;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (!(obj instanceof CefAcceleratedPaintInfo)) return false;
        CefAcceleratedPaintInfo other = (CefAcceleratedPaintInfo) obj;
        return this.planeCount == other.planeCount
                && this.modifier == other.modifier
                && java.util.Objects.equals(this.format, other.format)
                && java.util.Objects.equals(this.extra, other.extra);
    }

    @Override
    public int hashCode() {
        return java.util.Objects.hash(planeCount, modifier, format, extra);
    }

    @Override
    public String toString() {
        return "CefAcceleratedPaintInfo{" + "size=" + (size == -1 ? "pending" : Long.toString(size)) + ", "
                + "planeCount=" + planeCount + ", " + "modifier=" + modifier + ", " + "format=" + format + ", "
                + "extra=" + extra + "}";
    }
}
