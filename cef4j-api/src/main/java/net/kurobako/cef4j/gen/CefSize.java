// GENERATED - do not edit. Regenerate via: mvn generate-sources -pl cef4j-native
package net.kurobako.cef4j.gen;

/** Structure representing a size. */
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
