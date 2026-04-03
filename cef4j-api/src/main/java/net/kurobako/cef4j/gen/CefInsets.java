// GENERATED - do not edit. Regenerate via: mvn generate-sources -pl cef4j-native
// -Dcef.version=146.0.9+g3ca6a87+chromium-146.0.7680.165
package net.kurobako.cef4j.gen;

import javax.annotation.processing.Generated;

/**
 * Structure representing insets.
 *
 * <p>Definition generated from cef_types_geometry.h
 *
 * <pre>typedef struct _cef_insets_t {
 *   int top;
 *   int left;
 *   int bottom;
 *   int right;
 * } cef_insets_t;</pre>
 *
 * @see <a href="https://cef-builds.spotifycdn.com/docs/146.0/cef__types__geometry_8h.html">cef_types_geometry.h:64</a>
 */
@Generated("mvn generate-sources -pl cef4j-native -Dcef.version=146.0.9+g3ca6a87+chromium-146.0.7680.165")
public final class CefInsets {

    public final int top;
    public final int left;
    public final int bottom;
    public final int right;

    public CefInsets(int top, int left, int bottom, int right) {
        this.top = top;
        this.left = left;
        this.bottom = bottom;
        this.right = right;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (!(obj instanceof CefInsets)) return false;
        CefInsets other = (CefInsets) obj;
        return this.top == other.top
                && this.left == other.left
                && this.bottom == other.bottom
                && this.right == other.right;
    }

    @Override
    public int hashCode() {
        return java.util.Objects.hash(top, left, bottom, right);
    }

    @Override
    public String toString() {
        return "CefInsets{" + "top=" + top + ", " + "left=" + left + ", " + "bottom=" + bottom + ", " + "right=" + right
                + "}";
    }
}
