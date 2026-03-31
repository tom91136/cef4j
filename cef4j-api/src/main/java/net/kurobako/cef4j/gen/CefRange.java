// GENERATED - do not edit. Regenerate via: mvn generate-sources -pl cef4j-native
package net.kurobako.cef4j.gen;

/** Structure representing a range. */
public final class CefRange {

    public final int from;
    public final int to;

    public CefRange(int from, int to) {
        this.from = from;
        this.to = to;
    }

    /** Create a mutable copy of this instance. */
    public CefMutableRange toMutable() {
        return new CefMutableRange(this.from, this.to);
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
