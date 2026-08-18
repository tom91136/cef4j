// GENERATED - do not edit. Regenerate via: mvn generate-sources -pl cef4j-platform -Dcef.version=150.0.18+gdb11278+chromium-150.0.7871.213
package net.kurobako.cef4j.gen;

import javax.annotation.processing.Generated;

/**
 * Structure representing a point.
 * <p>Definition generated from internal/cef_types_geometry.h
 * <pre>typedef struct _cef_point_t {
 *   int x;
 *   int y;
 * } cef_point_t;</pre>
 *
 * @see <a href="https://cef-builds.spotifycdn.com/docs/150.0/cef__types__geometry_8h.html">internal/cef_types_geometry.h:38</a>
 */
@Generated("mvn generate-sources -pl cef4j-platform -Dcef.version=150.0.18+gdb11278+chromium-150.0.7871.213")
@SuppressWarnings({"SameReturnValue", "EmptyMethod", "UnusedReturnValue", "unused", "NullableForbidden"})
public final class CefPoint {

    public final int x;
    public final int y;

    public CefPoint(int x, int y) {
        this.x = x;
        this.y = y;
    }

    /** Create a mutable copy of this instance. */
    public Mutable toMutable() {
        return new Mutable(this.x, this.y);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (!(obj instanceof CefPoint)) return false;
        CefPoint other = (CefPoint) obj;
        return this.x == other.x
                    && this.y == other.y;
    }

    @Override
    public int hashCode() {
        return java.util.Objects.hash(x, y);
    }

    @Override
    public String toString() {
        return "CefPoint{" + "x=" + x + ", " + "y=" + y + "}";
    }

    /**
     * Mutable variant of {@link CefPoint}. Structure representing a point.
     * <p>Definition generated from internal/cef_types_geometry.h
     *
     * @see <a href="https://cef-builds.spotifycdn.com/docs/150.0/cef__types__geometry_8h.html">internal/cef_types_geometry.h:38</a>
     */
    public static final class Mutable {

        public int x;
        public int y;

        public Mutable() {}

        public Mutable(int x, int y) {
            this.x = x;
            this.y = y;
        }

        /** Create an immutable snapshot of this instance. */
        public CefPoint toImmutable() {
            return new CefPoint(this.x, this.y);
        }

        @Override
        public boolean equals(Object obj) {
            if (this == obj) return true;
            if (!(obj instanceof Mutable)) return false;
            Mutable other = (Mutable) obj;
            return this.x == other.x
                        && this.y == other.y;
        }

        @Override
        public int hashCode() {
            return java.util.Objects.hash(x, y);
        }

        @Override
        public String toString() {
            return "CefPoint.Mutable{" + "x=" + x + ", " + "y=" + y + "}";
        }
    }
}
