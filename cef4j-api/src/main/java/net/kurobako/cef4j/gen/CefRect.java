// GENERATED - do not edit. Regenerate via: mvn generate-sources -pl cef4j-native
package net.kurobako.cef4j.gen;

/** Structure representing a rectangle. */
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
    public CefMutableRect toMutable() {
        return new CefMutableRect(this.x, this.y, this.width, this.height);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (!(obj instanceof CefRect)) return false;
        CefRect other = (CefRect) obj;
        return this.x == other.x && this.y == other.y && this.width == other.width && this.height == other.height;
    }

    @Override
    public int hashCode() {
        return java.util.Objects.hash(x, y, width, height);
    }

    @Override
    public String toString() {
        return "CefRect{" + "x=" + x + ", " + "y=" + y + ", " + "width=" + width + ", " + "height=" + height + "}";
    }
}
