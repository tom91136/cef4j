// GENERATED - do not edit. Regenerate via: mvn generate-sources -pl cef4j-native
package net.kurobako.cef4j.gen;

/**
 * Structure representing a size.
 *
 * <p>Definition generated from cef_types_geometry.h
 *
 * <pre>typedef struct _cef_size_t {
 *   int width;
 *   int height;
 * } cef_size_t;</pre>
 *
 * @see <a href="https://cef-builds.spotifycdn.com/docs/146.0/cef__types__geometry_8h.html">cef_types_geometry.h:56</a>
 */
public final class CefSize {

    public final int width;
    public final int height;

    public CefSize(int width, int height) {
        this.width = width;
        this.height = height;
    }

    /** Create a mutable copy of this instance. */
    public CefMutableSize toMutable() {
        return new CefMutableSize(this.width, this.height);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (!(obj instanceof CefSize)) return false;
        CefSize other = (CefSize) obj;
        return this.width == other.width && this.height == other.height;
    }

    @Override
    public int hashCode() {
        return java.util.Objects.hash(width, height);
    }

    @Override
    public String toString() {
        return "CefSize{" + "width=" + width + ", " + "height=" + height + "}";
    }
}
