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

    public final int id;
    public final float x;
    public final float y;
    public final float radiusX;
    public final float radiusY;
    public final float rotationAngle;
    public final float pressure;
    public final CefTouchEventType type;
    public final int modifiers;
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
}
