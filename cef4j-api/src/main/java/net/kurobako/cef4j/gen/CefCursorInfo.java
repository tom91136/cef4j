// GENERATED - do not edit. Regenerate via: mvn generate-sources -pl cef4j-native
// -Dcef.version=146.0.9+g3ca6a87+chromium-146.0.7680.165
package net.kurobako.cef4j.gen;

import javax.annotation.processing.Generated;

/**
 * Structure representing cursor information. {@code buffer} will be {@code size.width}*{@code size.height}*4 bytes in
 * size and represents a BGRA image with an upper-left origin.
 *
 * <p>Definition generated from cef_types.h
 *
 * <pre>typedef struct _cef_cursor_info_t {
 *   cef_point_t* hotspot;
 *   float image_scale_factor;
 *   void* buffer;
 *   cef_size_t* size;
 * } cef_cursor_info_t;</pre>
 *
 * @see <a href="https://cef-builds.spotifycdn.com/docs/146.0/cef__types_8h.html">cef_types.h:2724</a>
 */
@Generated("mvn generate-sources -pl cef4j-native -Dcef.version=146.0.9+g3ca6a87+chromium-146.0.7680.165")
public final class CefCursorInfo {

    public final CefPoint hotspot;
    public final float imageScaleFactor;
    public final NativePointer buffer;
    public final CefSize size;

    public CefCursorInfo(CefPoint hotspot, float imageScaleFactor, NativePointer buffer, CefSize size) {
        this.hotspot = hotspot;
        this.imageScaleFactor = imageScaleFactor;
        this.buffer = buffer;
        this.size = size;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (!(obj instanceof CefCursorInfo)) return false;
        CefCursorInfo other = (CefCursorInfo) obj;
        return java.util.Objects.equals(this.hotspot, other.hotspot)
                && this.imageScaleFactor == other.imageScaleFactor
                && java.util.Objects.equals(this.buffer, other.buffer)
                && java.util.Objects.equals(this.size, other.size);
    }

    @Override
    public int hashCode() {
        return java.util.Objects.hash(hotspot, imageScaleFactor, buffer, size);
    }

    @Override
    public String toString() {
        return "CefCursorInfo{" + "hotspot=" + hotspot + ", " + "imageScaleFactor=" + imageScaleFactor + ", "
                + "buffer=" + buffer + ", " + "size=" + size + "}";
    }
}
