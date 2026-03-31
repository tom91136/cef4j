// GENERATED - do not edit. Regenerate via: mvn generate-sources -pl cef4j-native
package net.kurobako.cef4j.gen;

/** Structure representing a draggable region. */
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
