// GENERATED - do not edit. Regenerate via: mvn generate-sources -pl cef4j-native
package net.kurobako.cef4j.gen;

/**
 * Structure representing touch event information.
 *
 * <p>Definition generated from cef_types.h
 *
 * <pre>typedef struct _cef_touch_event_t {
 *   int id;
 *   float x;
 *   float y;
 *   float radius_x;
 *   float radius_y;
 *   float rotation_angle;
 *   float pressure;
 *   cef_touch_event_type_t type;
 *   unsigned int modifiers;
 *   cef_pointer_type_t pointer_type;
 * } cef_touch_event_t;</pre>
 *
 * @see <a href="https://cef-builds.spotifycdn.com/docs/146.0/cef__types_8h.html">cef_types.h:2096</a>
 */
public final class CefTouchEvent {

    /**
     * Id of a touch point. Must be unique per touch, can be any number except -1. Note that a maximum of 16 concurrent
     * touches will be tracked; touches beyond that will be ignored.
     */
    public final int id;
    /** X coordinate relative to the left side of the view. */
    public final float x;
    /** Y coordinate relative to the top side of the view. */
    public final float y;
    /** X radius in pixels. Set to 0 if not applicable. */
    public final float radiusX;
    /** Y radius in pixels. Set to 0 if not applicable. */
    public final float radiusY;
    /** Rotation angle in radians. Set to 0 if not applicable. */
    public final float rotationAngle;
    /** The normalized pressure of the pointer input in the range of [0,1]. Set to 0 if not applicable. */
    public final float pressure;
    /**
     * The state of the touch point. Touches begin with one {@link CefTouchEventType.Kind#PRESSED} event followed by
     * zero or more {@link CefTouchEventType.Kind#MOVED} events and finally one {@link CefTouchEventType.Kind#RELEASED}
     * or {@link CefTouchEventType.Kind#CANCELLED} event. Events not respecting this order will be ignored.
     */
    public final CefTouchEventType type;
    /** Bit flags describing any pressed modifier keys. See cef_event_flags_t for values. */
    public final int modifiers;
    /** The device type that caused the event. */
    public final CefPointerType pointerType;

    public CefTouchEvent(
            int id,
            float x,
            float y,
            float radiusX,
            float radiusY,
            float rotationAngle,
            float pressure,
            CefTouchEventType type,
            int modifiers,
            CefPointerType pointerType) {
        this.id = id;
        this.x = x;
        this.y = y;
        this.radiusX = radiusX;
        this.radiusY = radiusY;
        this.rotationAngle = rotationAngle;
        this.pressure = pressure;
        this.type = type;
        this.modifiers = modifiers;
        this.pointerType = pointerType;
    }

    /** Create a mutable copy of this instance. */
    public Mutable toMutable() {
        return new Mutable(
                this.id,
                this.x,
                this.y,
                this.radiusX,
                this.radiusY,
                this.rotationAngle,
                this.pressure,
                this.type,
                this.modifiers,
                this.pointerType);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (!(obj instanceof CefTouchEvent)) return false;
        CefTouchEvent other = (CefTouchEvent) obj;
        return this.id == other.id
                && this.x == other.x
                && this.y == other.y
                && this.radiusX == other.radiusX
                && this.radiusY == other.radiusY
                && this.rotationAngle == other.rotationAngle
                && this.pressure == other.pressure
                && java.util.Objects.equals(this.type, other.type)
                && this.modifiers == other.modifiers
                && java.util.Objects.equals(this.pointerType, other.pointerType);
    }

    @Override
    public int hashCode() {
        return java.util.Objects.hash(
                id, x, y, radiusX, radiusY, rotationAngle, pressure, type, modifiers, pointerType);
    }

    @Override
    public String toString() {
        return "CefTouchEvent{" + "id=" + id + ", " + "x=" + x + ", " + "y=" + y + ", " + "radiusX=" + radiusX + ", "
                + "radiusY=" + radiusY + ", " + "rotationAngle=" + rotationAngle + ", " + "pressure=" + pressure + ", "
                + "type=" + type + ", " + "modifiers=" + modifiers + ", " + "pointerType=" + pointerType + "}";
    }

    /**
     * Mutable variant of {@link CefTouchEvent}. Structure representing touch event information.
     *
     * <p>Definition generated from cef_types.h
     *
     * @see <a href="https://cef-builds.spotifycdn.com/docs/146.0/cef__types_8h.html">cef_types.h:2096</a>
     */
    public static final class Mutable {

        /**
         * Id of a touch point. Must be unique per touch, can be any number except -1. Note that a maximum of 16
         * concurrent touches will be tracked; touches beyond that will be ignored.
         */
        public int id;
        /** X coordinate relative to the left side of the view. */
        public float x;
        /** Y coordinate relative to the top side of the view. */
        public float y;
        /** X radius in pixels. Set to 0 if not applicable. */
        public float radiusX;
        /** Y radius in pixels. Set to 0 if not applicable. */
        public float radiusY;
        /** Rotation angle in radians. Set to 0 if not applicable. */
        public float rotationAngle;
        /** The normalized pressure of the pointer input in the range of [0,1]. Set to 0 if not applicable. */
        public float pressure;
        /**
         * The state of the touch point. Touches begin with one {@link CefTouchEventType.Kind#PRESSED} event followed by
         * zero or more {@link CefTouchEventType.Kind#MOVED} events and finally one
         * {@link CefTouchEventType.Kind#RELEASED} or {@link CefTouchEventType.Kind#CANCELLED} event. Events not
         * respecting this order will be ignored.
         */
        public CefTouchEventType type;
        /** Bit flags describing any pressed modifier keys. See cef_event_flags_t for values. */
        public int modifiers;
        /** The device type that caused the event. */
        public CefPointerType pointerType;

        public Mutable() {}

        public Mutable(
                int id,
                float x,
                float y,
                float radiusX,
                float radiusY,
                float rotationAngle,
                float pressure,
                CefTouchEventType type,
                int modifiers,
                CefPointerType pointerType) {
            this.id = id;
            this.x = x;
            this.y = y;
            this.radiusX = radiusX;
            this.radiusY = radiusY;
            this.rotationAngle = rotationAngle;
            this.pressure = pressure;
            this.type = type;
            this.modifiers = modifiers;
            this.pointerType = pointerType;
        }

        /** Create an immutable snapshot of this instance. */
        public CefTouchEvent toImmutable() {
            return new CefTouchEvent(
                    this.id,
                    this.x,
                    this.y,
                    this.radiusX,
                    this.radiusY,
                    this.rotationAngle,
                    this.pressure,
                    this.type,
                    this.modifiers,
                    this.pointerType);
        }

        @Override
        public boolean equals(Object obj) {
            if (this == obj) return true;
            if (!(obj instanceof Mutable)) return false;
            Mutable other = (Mutable) obj;
            return this.id == other.id
                    && this.x == other.x
                    && this.y == other.y
                    && this.radiusX == other.radiusX
                    && this.radiusY == other.radiusY
                    && this.rotationAngle == other.rotationAngle
                    && this.pressure == other.pressure
                    && java.util.Objects.equals(this.type, other.type)
                    && this.modifiers == other.modifiers
                    && java.util.Objects.equals(this.pointerType, other.pointerType);
        }

        @Override
        public int hashCode() {
            return java.util.Objects.hash(
                    id, x, y, radiusX, radiusY, rotationAngle, pressure, type, modifiers, pointerType);
        }

        @Override
        public String toString() {
            return "CefTouchEvent.Mutable{" + "id=" + id + ", " + "x=" + x + ", " + "y=" + y + ", " + "radiusX="
                    + radiusX + ", " + "radiusY=" + radiusY + ", " + "rotationAngle=" + rotationAngle + ", "
                    + "pressure=" + pressure + ", " + "type=" + type + ", " + "modifiers=" + modifiers + ", "
                    + "pointerType=" + pointerType + "}";
        }
    }
}
