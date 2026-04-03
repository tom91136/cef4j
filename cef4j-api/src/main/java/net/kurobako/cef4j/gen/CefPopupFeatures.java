// GENERATED - do not edit. Regenerate via: mvn generate-sources -pl cef4j-native
// -Dcef.version=146.0.9+g3ca6a87+chromium-146.0.7680.165
package net.kurobako.cef4j.gen;

import javax.annotation.processing.Generated;

/**
 * Popup window features.
 *
 * <p>Definition generated from internal/cef_types.h
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
 * @see <a href="https://cef-builds.spotifycdn.com/docs/146.0/cef__types_8h.html">internal/cef_types.h:2474</a>
 */
@Generated("mvn generate-sources -pl cef4j-native -Dcef.version=146.0.9+g3ca6a87+chromium-146.0.7680.165")
public final class CefPopupFeatures {

    // Native struct size, populated by the JNI layer with sizeof(struct) as required by CEF. Not user-modifiable.
    @SuppressWarnings("FieldMayBeFinal")
    private volatile long size = -1;

    public final int x;
    public final int xSet;
    public final int y;
    public final int ySet;
    public final int width;
    public final int widthSet;
    public final int height;
    public final int heightSet;
    /** True (1) if browser interface elements should be hidden. */
    public final int isPopup;

    public CefPopupFeatures(
            int x, int xSet, int y, int ySet, int width, int widthSet, int height, int heightSet, int isPopup) {
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
        return this.x == other.x
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
        return java.util.Objects.hash(x, xSet, y, ySet, width, widthSet, height, heightSet, isPopup);
    }

    @Override
    public String toString() {
        return "CefPopupFeatures{" + "size=" + (size == -1 ? "pending" : Long.toString(size)) + ", " + "x=" + x + ", "
                + "xSet=" + xSet + ", " + "y=" + y + ", " + "ySet=" + ySet + ", " + "width=" + width + ", "
                + "widthSet=" + widthSet + ", " + "height=" + height + ", " + "heightSet=" + heightSet + ", "
                + "isPopup=" + isPopup + "}";
    }
}
