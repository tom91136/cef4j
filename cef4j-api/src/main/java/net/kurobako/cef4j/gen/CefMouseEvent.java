// GENERATED - do not edit. Regenerate via: mvn generate-sources -pl cef4j-native
// -Dcef.version=146.0.9+g3ca6a87+chromium-146.0.7680.165
package net.kurobako.cef4j.gen;

import javax.annotation.processing.Generated;

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
@Generated("mvn generate-sources -pl cef4j-native -Dcef.version=146.0.9+g3ca6a87+chromium-146.0.7680.165")
public final class CefMouseEvent {

    /** X coordinate relative to the left side of the view. */
    public final int x;
    /** Y coordinate relative to the top side of the view. */
    public final int y;
    /** Bit flags describing any pressed modifier keys. See cef_event_flags_t for values. */
    public final int modifiers;

    public CefMouseEvent(int x, int y, int modifiers) {
        this.x = x;
        this.y = y;
        this.modifiers = modifiers;
    }

    /** Create a mutable copy of this instance. */
    public Mutable toMutable() {
        return new Mutable(this.x, this.y, this.modifiers);
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

    /**
     * Mutable variant of {@link CefMouseEvent}. Structure representing mouse event information.
     *
     * <p>Definition generated from cef_types.h
     *
     * @see <a href="https://cef-builds.spotifycdn.com/docs/146.0/cef__types_8h.html">cef_types.h:2054</a>
     */
    public static final class Mutable {

        /** X coordinate relative to the left side of the view. */
        public int x;
        /** Y coordinate relative to the top side of the view. */
        public int y;
        /** Bit flags describing any pressed modifier keys. See cef_event_flags_t for values. */
        public int modifiers;

        public Mutable() {}

        public Mutable(int x, int y, int modifiers) {
            this.x = x;
            this.y = y;
            this.modifiers = modifiers;
        }

        /** Create an immutable snapshot of this instance. */
        public CefMouseEvent toImmutable() {
            return new CefMouseEvent(this.x, this.y, this.modifiers);
        }

        @Override
        public boolean equals(Object obj) {
            if (this == obj) return true;
            if (!(obj instanceof Mutable)) return false;
            Mutable other = (Mutable) obj;
            return this.x == other.x && this.y == other.y && this.modifiers == other.modifiers;
        }

        @Override
        public int hashCode() {
            return java.util.Objects.hash(x, y, modifiers);
        }

        @Override
        public String toString() {
            return "CefMouseEvent.Mutable{" + "x=" + x + ", " + "y=" + y + ", " + "modifiers=" + modifiers + "}";
        }
    }
}
