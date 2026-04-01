// GENERATED - do not edit. Regenerate via: mvn generate-sources -pl cef4j-native
package net.kurobako.cef4j.gen;

/**
 * Structure representing a range.
 *
 * <p>Definition generated from cef_types.h
 *
 * <pre>typedef struct _cef_range_t {
 *   unsigned int from;
 *   unsigned int to;
 * } cef_range_t;</pre>
 *
 * @see <a href="https://cef-builds.spotifycdn.com/docs/146.0/cef__types_8h.html">cef_types.h:3343</a>
 */
public final class CefRange {

    public final int from;
    public final int to;

    public CefRange(int from, int to) {
        this.from = from;
        this.to = to;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (!(obj instanceof CefRange)) return false;
        CefRange other = (CefRange) obj;
        return this.from == other.from && this.to == other.to;
    }

    @Override
    public int hashCode() {
        return java.util.Objects.hash(from, to);
    }

    @Override
    public String toString() {
        return "CefRange{" + "from=" + from + ", " + "to=" + to + "}";
    }
}
