// GENERATED - do not edit. Regenerate via: mvn generate-sources -pl cef4j-native
package net.kurobako.cef4j.gen;

/**
 * Structure representing cursor information. |buffer| will be |size.width|*|size.height|*4 bytes in size and represents
 * a BGRA image with an upper-left origin.
 */
public final class CefCursorInfo {

    public final CefPoint hotspot;
    public final float imageScaleFactor;
    public final long buffer;
    public final CefSize size;

    public CefCursorInfo(CefPoint hotspot, float imageScaleFactor, long buffer, CefSize size) {
        this.hotspot = hotspot;
        this.imageScaleFactor = imageScaleFactor;
        this.buffer = buffer;
        this.size = size;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (!(obj instanceof CefCursorInfo)) return false;
        CefCursorInfo other = (CefCursorInfo) obj;
        return java.util.Objects.equals(this.hotspot, other.hotspot)
                && this.imageScaleFactor == other.imageScaleFactor
                && java.util.Objects.equals(this.buffer, other.buffer)
                && java.util.Objects.equals(this.size, other.size);
    }

    @Override
    public int hashCode() {
        return java.util.Objects.hash(hotspot, imageScaleFactor, buffer, size);
    }

    @Override
    public String toString() {
        return "CefCursorInfo{" + "hotspot=" + hotspot + ", " + "imageScaleFactor=" + imageScaleFactor + ", "
                + "buffer=" + buffer + ", " + "size=" + size + "}";
    }
}
