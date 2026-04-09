// GENERATED - do not edit. Regenerate via: mvn generate-sources -pl cef4j-native -Dcef.version=146.0.9+g3ca6a87+chromium-146.0.7680.165
package net.kurobako.cef4j.gen;

import javax.annotation.processing.Generated;

/**
 * Structure representing a rectangle.
 * <p>Definition generated from internal/cef_types_geometry.h
 * <pre>typedef struct _cef_rect_t {
 *   int x;
 *   int y;
 *   int width;
 *   int height;
 * } cef_rect_t;</pre>
 *
 * @see <a href="https://cef-builds.spotifycdn.com/docs/146.0/cef__types__geometry_8h.html">internal/cef_types_geometry.h:46</a>
 */
@Generated("mvn generate-sources -pl cef4j-native -Dcef.version=146.0.9+g3ca6a87+chromium-146.0.7680.165")
@SuppressWarnings({"SameReturnValue", "EmptyMethod", "UnusedReturnValue", "unused"})
public final class CefRect {

    public final int x;
    public final int y;
    public final int width;
    public final int height;

    public CefRect(int x, int y, int width, int height) {
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
    }

    /** Create a mutable copy of this instance. */
    public Mutable toMutable() {
        return new Mutable(this.x, this.y, this.width, this.height);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (!(obj instanceof CefRect)) return false;
        CefRect other = (CefRect) obj;
        return this.x == other.x
                    && this.y == other.y
                    && this.width == other.width
                    && this.height == other.height;
    }

    @Override
    public int hashCode() {
        return java.util.Objects.hash(x, y, width, height);
    }

    @Override
    public String toString() {
        return "CefRect{" + "x=" + x + ", " + "y=" + y + ", " + "width=" + width + ", " + "height=" + height + "}";
    }

    /**
     * Mutable variant of {@link CefRect}. Structure representing a rectangle.
     * <p>Definition generated from internal/cef_types_geometry.h
     *
     * @see <a href="https://cef-builds.spotifycdn.com/docs/146.0/cef__types__geometry_8h.html">internal/cef_types_geometry.h:46</a>
     */
    public static final class Mutable {

        public int x;
        public int y;
        public int width;
        public int height;

        public Mutable() {}

        public Mutable(int x, int y, int width, int height) {
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
            if (!(obj instanceof Mutable)) return false;
            Mutable other = (Mutable) obj;
            return this.x == other.x
                        && this.y == other.y
                        && this.width == other.width
                        && this.height == other.height;
        }

        @Override
        public int hashCode() {
            return java.util.Objects.hash(x, y, width, height);
        }

        @Override
        public String toString() {
            return "CefRect.Mutable{" + "x=" + x + ", " + "y=" + y + ", " + "width=" + width + ", " + "height=" + height + "}";
        }
    }
}
