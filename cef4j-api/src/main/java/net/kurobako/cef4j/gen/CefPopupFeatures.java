// GENERATED - do not edit. Regenerate via: mvn generate-sources -pl cef4j-native
package net.kurobako.cef4j.gen;

/** Popup window features. */
public final class CefPopupFeatures {

    public final long size;
    public final int x;
    public final int xSet;
    public final int y;
    public final int ySet;
    public final int width;
    public final int widthSet;
    public final int height;
    public final int heightSet;
    public final int isPopup;

    public CefPopupFeatures(
            long size,
            int x,
            int xSet,
            int y,
            int ySet,
            int width,
            int widthSet,
            int height,
            int heightSet,
            int isPopup) {
        this.size = size;
        this.x = x;
        this.xSet = xSet;
        this.y = y;
        this.ySet = ySet;
        this.width = width;
        this.widthSet = widthSet;
        this.height = height;
        this.heightSet = heightSet;
        this.isPopup = isPopup;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (!(obj instanceof CefPopupFeatures)) return false;
        CefPopupFeatures other = (CefPopupFeatures) obj;
        return this.size == other.size
                && this.x == other.x
                && this.xSet == other.xSet
                && this.y == other.y
                && this.ySet == other.ySet
                && this.width == other.width
                && this.widthSet == other.widthSet
                && this.height == other.height
                && this.heightSet == other.heightSet
                && this.isPopup == other.isPopup;
    }

    @Override
    public int hashCode() {
        return java.util.Objects.hash(size, x, xSet, y, ySet, width, widthSet, height, heightSet, isPopup);
    }

    @Override
    public String toString() {
        return "CefPopupFeatures{" + "size=" + size + ", " + "x=" + x + ", " + "xSet=" + xSet + ", " + "y=" + y + ", "
                + "ySet=" + ySet + ", " + "width=" + width + ", " + "widthSet=" + widthSet + ", " + "height=" + height
                + ", " + "heightSet=" + heightSet + ", " + "isPopup=" + isPopup + "}";
    }
}
