// GENERATED - do not edit. Regenerate via: mvn generate-sources -pl cef4j-platform -Dcef.version=150.0.18+gdb11278+chromium-150.0.7871.213
package net.kurobako.cef4j.gen;

import javax.annotation.processing.Generated;

/**
 * Structure representing a range.
 * <p>Definition generated from internal/cef_types.h
 * <pre>typedef struct _cef_range_t {
 *   unsigned int from;
 *   unsigned int to;
 * } cef_range_t;</pre>
 *
 * @see <a href="https://cef-builds.spotifycdn.com/docs/150.0/cef__types_8h.html">internal/cef_types.h:3358</a>
 */
@Generated("mvn generate-sources -pl cef4j-platform -Dcef.version=150.0.18+gdb11278+chromium-150.0.7871.213")
@SuppressWarnings({"SameReturnValue", "EmptyMethod", "UnusedReturnValue", "unused"})
public final class CefRange {

    public final int from;
    public final int to;

    public CefRange(int from, int to) {
        this.from = from;
        this.to = to;
    }

    /** Create a mutable copy of this instance. */
    public Mutable toMutable() {
        return new Mutable(this.from, this.to);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (!(obj instanceof CefRange)) return false;
        CefRange other = (CefRange) obj;
        return this.from == other.from
                    && this.to == other.to;
    }

    @Override
    public int hashCode() {
        return java.util.Objects.hash(from, to);
    }

    @Override
    public String toString() {
        return "CefRange{" + "from=" + from + ", " + "to=" + to + "}";
    }

    /**
     * Mutable variant of {@link CefRange}. Structure representing a range.
     * <p>Definition generated from internal/cef_types.h
     *
     * @see <a href="https://cef-builds.spotifycdn.com/docs/150.0/cef__types_8h.html">internal/cef_types.h:3358</a>
     */
    public static final class Mutable {

        public int from;
        public int to;

        public Mutable() {}

        public Mutable(int from, int to) {
            this.from = from;
            this.to = to;
        }

        /** Create an immutable snapshot of this instance. */
        public CefRange toImmutable() {
            return new CefRange(this.from, this.to);
        }

        @Override
        public boolean equals(Object obj) {
            if (this == obj) return true;
            if (!(obj instanceof Mutable)) return false;
            Mutable other = (Mutable) obj;
            return this.from == other.from
                        && this.to == other.to;
        }

        @Override
        public int hashCode() {
            return java.util.Objects.hash(from, to);
        }

        @Override
        public String toString() {
            return "CefRange.Mutable{" + "from=" + from + ", " + "to=" + to + "}";
        }
    }
}
