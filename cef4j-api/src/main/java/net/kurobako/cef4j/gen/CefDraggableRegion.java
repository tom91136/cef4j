// GENERATED - do not edit. Regenerate via: ./mvnw generate-sources -pl cef4j-api
// -Dcef.version=150.0.18+gdb11278+chromium-150.0.7871.213
package net.kurobako.cef4j.gen;

import javax.annotation.Nullable;
import javax.annotation.processing.Generated;
import net.kurobako.cef4j.policy.NullableBoundary;

/**
 * Structure representing a draggable region.
 *
 * <p>Definition generated from internal/cef_types.h
 *
 * <pre>typedef struct _cef_draggable_region_t {
 *   cef_rect_t* bounds;
 *   int draggable;
 * } cef_draggable_region_t;</pre>
 *
 * @see <a href="https://cef-builds.spotifycdn.com/docs/150.0/cef__types_8h.html">internal/cef_types.h:1718</a>
 */
@Generated("./mvnw generate-sources -pl cef4j-api -Dcef.version=150.0.18+gdb11278+chromium-150.0.7871.213")
@SuppressWarnings({"SameReturnValue", "EmptyMethod", "UnusedReturnValue", "unused"})
@NullableBoundary("generated external ABI")
public final class CefDraggableRegion {

    /** Bounds of the region. */
    public final @Nullable CefRect bounds;
    /** True (1) this this region is draggable and {@code false} (0) otherwise. */
    public final int draggable;

    public CefDraggableRegion(@Nullable CefRect bounds, int draggable) {
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
