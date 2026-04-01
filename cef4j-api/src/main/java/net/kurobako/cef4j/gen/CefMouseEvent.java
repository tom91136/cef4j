// GENERATED - do not edit. Regenerate via: mvn generate-sources -pl cef4j-native
package net.kurobako.cef4j.gen;

/**
 * Structure representing mouse event information.
 *
 * <p>Definition generated from cef_types.h
 *
 * <pre>typedef struct _cef_mouse_event_t {
 *   int x;
 *   int y;
 *   unsigned int modifiers;
 * } cef_mouse_event_t;</pre>
 *
 * @see <a href="https://cef-builds.spotifycdn.com/docs/146.0/cef__types_8h.html">cef_types.h:2054</a>
 */
public final class CefMouseEvent {

    public final int x;
    public final int y;
    public final int modifiers;

    public CefMouseEvent(int x, int y, int modifiers) {
        this.x = x;
        this.y = y;
        this.modifiers = modifiers;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (!(obj instanceof CefMouseEvent)) return false;
        CefMouseEvent other = (CefMouseEvent) obj;
        return this.x == other.x && this.y == other.y && this.modifiers == other.modifiers;
    }

    @Override
    public int hashCode() {
        return java.util.Objects.hash(x, y, modifiers);
    }

    @Override
    public String toString() {
        return "CefMouseEvent{" + "x=" + x + ", " + "y=" + y + ", " + "modifiers=" + modifiers + "}";
    }
}
