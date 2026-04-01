// GENERATED - do not edit. Regenerate via: mvn generate-sources -pl cef4j-native
package net.kurobako.cef4j.gen;

/**
 * Structure representing a point.
 *
 * <p>Definition generated from cef_types_geometry.h
 *
 * <pre>typedef struct _cef_point_t {
 *   int x;
 *   int y;
 * } cef_point_t;</pre>
 *
 * @see <a href="https://cef-builds.spotifycdn.com/docs/146.0/cef__types__geometry_8h.html">cef_types_geometry.h:38</a>
 */
public final class CefPoint {

    public final int x;
    public final int y;

    public CefPoint(int x, int y) {
        this.x = x;
        this.y = y;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (!(obj instanceof CefPoint)) return false;
        CefPoint other = (CefPoint) obj;
        return this.x == other.x && this.y == other.y;
    }

    @Override
    public int hashCode() {
        return java.util.Objects.hash(x, y);
    }

    @Override
    public String toString() {
        return "CefPoint{" + "x=" + x + ", " + "y=" + y + "}";
    }
}
