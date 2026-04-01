// GENERATED - do not edit. Regenerate via: mvn generate-sources -pl cef4j-native
package net.kurobako.cef4j.gen;

/**
 * Mutable variant of {@link CefRect}. Structure representing a rectangle.
 *
 * <p>Definition generated from cef_types_geometry.h
 *
 * <pre>typedef struct _cef_rect_t {
 *   int x;
 *   int y;
 *   int width;
 *   int height;
 * } cef_rect_t;</pre>
 *
 * @see <a href="https://cef-builds.spotifycdn.com/docs/146.0/cef__types__geometry_8h.html">cef_types_geometry.h:46</a>
 */
public final class CefMutableRect {

    public int x;
    public int y;
    public int width;
    public int height;

    public CefMutableRect() {}

    public CefMutableRect(int x, int y, int width, int height) {
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
    }

    /** Create an immutable snapshot of this instance. */
    public CefRect toImmutable() {
        return new CefRect(this.x, this.y, this.width, this.height);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (!(obj instanceof CefMutableRect)) return false;
        CefMutableRect other = (CefMutableRect) obj;
        return this.x == other.x && this.y == other.y && this.width == other.width && this.height == other.height;
    }

    @Override
    public int hashCode() {
        return java.util.Objects.hash(x, y, width, height);
    }

    @Override
    public String toString() {
        return "CefMutableRect{" + "x=" + x + ", " + "y=" + y + ", " + "width=" + width + ", " + "height=" + height
                + "}";
    }
}
