// GENERATED - do not edit. Regenerate via: mvn generate-sources -pl cef4j-native
package net.kurobako.cef4j.gen;

/** Mutable variant of {@link CefRect}. Structure representing a rectangle. */
public final class CefMutableRect {

    public int x;
    public int y;
    public int width;
    public int height;

    public CefMutableRect() {}

    public CefMutableRect(int x, int y, int width, int height) {
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
        if (!(obj instanceof CefMutableRect)) return false;
        CefMutableRect other = (CefMutableRect) obj;
        return this.x == other.x && this.y == other.y && this.width == other.width && this.height == other.height;
    }

    @Override
    public int hashCode() {
        return java.util.Objects.hash(x, y, width, height);
    }

    @Override
    public String toString() {
        return "CefMutableRect{" + "x=" + x + ", " + "y=" + y + ", " + "width=" + width + ", " + "height=" + height
                + "}";
    }
}
