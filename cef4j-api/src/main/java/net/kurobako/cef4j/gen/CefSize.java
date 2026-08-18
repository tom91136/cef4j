// GENERATED - do not edit. Regenerate via: mvn generate-sources -pl cef4j-platform -Dcef.version=150.0.18+gdb11278+chromium-150.0.7871.213
package net.kurobako.cef4j.gen;

import javax.annotation.processing.Generated;

/**
 * Structure representing a size.
 * <p>Definition generated from internal/cef_types_geometry.h
 * <pre>typedef struct _cef_size_t {
 *   int width;
 *   int height;
 * } cef_size_t;</pre>
 *
 * @see <a href="https://cef-builds.spotifycdn.com/docs/150.0/cef__types__geometry_8h.html">internal/cef_types_geometry.h:56</a>
 */
@Generated("mvn generate-sources -pl cef4j-platform -Dcef.version=150.0.18+gdb11278+chromium-150.0.7871.213")
@SuppressWarnings({"SameReturnValue", "EmptyMethod", "UnusedReturnValue", "unused", "NullableForbidden"})
public final class CefSize {

    public final int width;
    public final int height;

    public CefSize(int width, int height) {
        this.width = width;
        this.height = height;
    }

    /** Create a mutable copy of this instance. */
    public Mutable toMutable() {
        return new Mutable(this.width, this.height);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (!(obj instanceof CefSize)) return false;
        CefSize other = (CefSize) obj;
        return this.width == other.width
                    && this.height == other.height;
    }

    @Override
    public int hashCode() {
        return java.util.Objects.hash(width, height);
    }

    @Override
    public String toString() {
        return "CefSize{" + "width=" + width + ", " + "height=" + height + "}";
    }

    /**
     * Mutable variant of {@link CefSize}. Structure representing a size.
     * <p>Definition generated from internal/cef_types_geometry.h
     *
     * @see <a href="https://cef-builds.spotifycdn.com/docs/150.0/cef__types__geometry_8h.html">internal/cef_types_geometry.h:56</a>
     */
    public static final class Mutable {

        public int width;
        public int height;

        public Mutable() {}

        public Mutable(int width, int height) {
            this.width = width;
            this.height = height;
        }

        /** Create an immutable snapshot of this instance. */
        public CefSize toImmutable() {
            return new CefSize(this.width, this.height);
        }

        @Override
        public boolean equals(Object obj) {
            if (this == obj) return true;
            if (!(obj instanceof Mutable)) return false;
            Mutable other = (Mutable) obj;
            return this.width == other.width
                        && this.height == other.height;
        }

        @Override
        public int hashCode() {
            return java.util.Objects.hash(width, height);
        }

        @Override
        public String toString() {
            return "CefSize.Mutable{" + "width=" + width + ", " + "height=" + height + "}";
        }
    }
}
