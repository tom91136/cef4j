// GENERATED - do not edit. Regenerate via: mvn generate-sources -pl cef4j-native
package net.kurobako.cef4j.gen;

/** Mutable variant of {@link CefRange}. Structure representing a range. */
public final class CefMutableRange {

    public int from;
    public int to;

    public CefMutableRange() {}

    public CefMutableRange(int from, int to) {
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
        if (!(obj instanceof CefMutableRange)) return false;
        CefMutableRange other = (CefMutableRange) obj;
        return this.from == other.from && this.to == other.to;
    }

    @Override
    public int hashCode() {
        return java.util.Objects.hash(from, to);
    }

    @Override
    public String toString() {
        return "CefMutableRange{" + "from=" + from + ", " + "to=" + to + "}";
    }
}
