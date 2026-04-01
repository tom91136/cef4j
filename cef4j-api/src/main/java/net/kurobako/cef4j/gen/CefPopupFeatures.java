// GENERATED - do not edit. Regenerate via: mvn generate-sources -pl cef4j-native
package net.kurobako.cef4j.gen;

/**
 * Popup window features.
 *
 * <p>Definition generated from cef_types.h
 *
 * <pre>typedef struct _cef_popup_features_t {
 *   size_t size;
 *   int x;
 *   int xSet;
 *   int y;
 *   int ySet;
 *   int width;
 *   int widthSet;
 *   int height;
 *   int heightSet;
 *   int isPopup;
 * } cef_popup_features_t;</pre>
 *
 * @see <a href="https://cef-builds.spotifycdn.com/docs/146.0/cef__types_8h.html">cef_types.h:2474</a>
 */
public final class CefPopupFeatures {

    // Native struct size — set by JNI, not user-modifiable.
    @SuppressWarnings("FieldMayBeFinal")
    private volatile long size = -1;

    public final int x;
    public final int xset;
    public final int y;
    public final int yset;
    public final int width;
    public final int widthset;
    public final int height;
    public final int heightset;
    public final int ispopup;

    public CefPopupFeatures(
            int x, int xset, int y, int yset, int width, int widthset, int height, int heightset, int ispopup) {
        this.x = x;
        this.xset = xset;
        this.y = y;
        this.yset = yset;
        this.width = width;
        this.widthset = widthset;
        this.height = height;
        this.heightset = heightset;
        this.ispopup = ispopup;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (!(obj instanceof CefPopupFeatures)) return false;
        CefPopupFeatures other = (CefPopupFeatures) obj;
        return this.x == other.x
                && this.xset == other.xset
                && this.y == other.y
                && this.yset == other.yset
                && this.width == other.width
                && this.widthset == other.widthset
                && this.height == other.height
                && this.heightset == other.heightset
                && this.ispopup == other.ispopup;
    }

    @Override
    public int hashCode() {
        return java.util.Objects.hash(x, xset, y, yset, width, widthset, height, heightset, ispopup);
    }

    @Override
    public String toString() {
        return "CefPopupFeatures{" + "size=" + (size == -1 ? "pending" : Long.toString(size)) + ", " + "x=" + x + ", "
                + "xset=" + xset + ", " + "y=" + y + ", " + "yset=" + yset + ", " + "width=" + width + ", "
                + "widthset=" + widthset + ", " + "height=" + height + ", " + "heightset=" + heightset + ", "
                + "ispopup=" + ispopup + "}";
    }
}
