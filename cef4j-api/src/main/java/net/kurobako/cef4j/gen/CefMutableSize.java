// GENERATED - do not edit. Regenerate via: mvn generate-sources -pl cef4j-native
package net.kurobako.cef4j.gen;

/** Mutable variant of {@link CefSize}. Structure representing a size. */
public final class CefMutableSize {

    public int width;
    public int height;

    public CefMutableSize() {}

    public CefMutableSize(int width, int height) {
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
        if (!(obj instanceof CefMutableSize)) return false;
        CefMutableSize other = (CefMutableSize) obj;
        return this.width == other.width && this.height == other.height;
    }

    @Override
    public int hashCode() {
        return java.util.Objects.hash(width, height);
    }

    @Override
    public String toString() {
        return "CefMutableSize{" + "width=" + width + ", " + "height=" + height + "}";
    }
}
