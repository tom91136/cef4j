// GENERATED - do not edit. Regenerate via: mvn generate-sources -pl cef4j-native
package net.kurobako.cef4j.gen;

/**
 * Structure representing a draggable region.
 *
 * <p>Definition generated from cef_types.h
 *
 * <pre>typedef struct _cef_draggable_region_t {
 *   cef_rect_t* bounds;
 *   int draggable;
 * } cef_draggable_region_t;</pre>
 *
 * @see <a href="https://cef-builds.spotifycdn.com/docs/146.0/cef__types_8h.html">cef_types.h:1703</a>
 */
public final class CefDraggableRegion {

    public final CefRect bounds;
    public final int draggable;

    public CefDraggableRegion(CefRect bounds, int draggable) {
        this.bounds = bounds;
        this.draggable = draggable;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (!(obj instanceof CefDraggableRegion)) return false;
        CefDraggableRegion other = (CefDraggableRegion) obj;
        return java.util.Objects.equals(this.bounds, other.bounds) && this.draggable == other.draggable;
    }

    @Override
    public int hashCode() {
        return java.util.Objects.hash(bounds, draggable);
    }

    @Override
    public String toString() {
        return "CefDraggableRegion{" + "bounds=" + bounds + ", " + "draggable=" + draggable + "}";
    }
}
