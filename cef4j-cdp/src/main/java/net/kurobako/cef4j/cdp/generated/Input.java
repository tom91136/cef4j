// GENERATED - do not edit. Regenerate via: ./mvnw generate-sources -pl cef4j-cdp -Dcef.version=150.0.18+gdb11278+chromium-150.0.7871.213
package net.kurobako.cef4j.cdp.generated;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Optional;
import java.util.OptionalDouble;
import java.util.OptionalLong;
import java.util.concurrent.CompletionStage;
import java.util.function.Consumer;
import javax.annotation.Nonnull;
import javax.annotation.processing.Generated;
import net.kurobako.cef4j.cdp.CdpClient;
import net.kurobako.cef4j.cdp.CdpObject;
import net.kurobako.cef4j.cdp.CdpSubscription;
import net.kurobako.cef4j.cdp.CdpValue;

/**
 * Chrome DevTools Protocol Input domain.
 * @see <a href="https://chromium.googlesource.com/chromium/src/+/refs/tags/150.0.7871.213/third_party/blink/public/devtools_protocol/domains/Input.pdl">Pinned protocol source</a>
 */
@Generated("./mvnw generate-sources -pl cef4j-cdp -Dcef.version=150.0.18+gdb11278+chromium-150.0.7871.213")
@SuppressWarnings({"EscapedEntity", "InvalidParam", "JavaLangClash", "MissingSummary", "Unchecked", "UnusedMethod"})
public final class Input {
    private Input() {}
    /**
     */
    public static final class TouchPoint extends CdpObject {
        public TouchPoint() {}
        private TouchPoint(Map<String, Object> values) { super(values); }
        public static TouchPoint fromMap(Map<String, Object> values) {
            return new TouchPoint(values);
        }
        /**
         * X coordinate of the event relative to the main frame&#x27;s viewport in CSS pixels.
         * @return the protocol field value
         */
        public double x() {
            return ((Number) require("x")).doubleValue();
        }
        /**
         * Y coordinate of the event relative to the main frame&#x27;s viewport in CSS pixels. 0 refers to the top of the viewport and Y increases as it proceeds towards the bottom of the viewport.
         * @return the protocol field value
         */
        public double y() {
            return ((Number) require("y")).doubleValue();
        }
        /**
         * X radius of the touch area (default: 1.0).
         * @return the protocol field value, empty when absent
         */
        public OptionalDouble radiusX() {
            Double value = CdpObject.numberAsDouble(raw("radiusX"));
            return value == null ? OptionalDouble.empty() : OptionalDouble.of(value);
        }
        /**
         * Y radius of the touch area (default: 1.0).
         * @return the protocol field value, empty when absent
         */
        public OptionalDouble radiusY() {
            Double value = CdpObject.numberAsDouble(raw("radiusY"));
            return value == null ? OptionalDouble.empty() : OptionalDouble.of(value);
        }
        /**
         * Rotation angle (default: 0.0).
         * @return the protocol field value, empty when absent
         */
        public OptionalDouble rotationAngle() {
            Double value = CdpObject.numberAsDouble(raw("rotationAngle"));
            return value == null ? OptionalDouble.empty() : OptionalDouble.of(value);
        }
        /**
         * Force (default: 1.0).
         * @return the protocol field value, empty when absent
         */
        public OptionalDouble force() {
            Double value = CdpObject.numberAsDouble(raw("force"));
            return value == null ? OptionalDouble.empty() : OptionalDouble.of(value);
        }
        /**
         * The normalized tangential pressure, which has a range of [-1,1] (default: 0).
         * <p><b>Experimental:</b> this part of CDP may change without notice.
         * @return the protocol field value, empty when absent
         */
        public OptionalDouble tangentialPressure() {
            Double value = CdpObject.numberAsDouble(raw("tangentialPressure"));
            return value == null ? OptionalDouble.empty() : OptionalDouble.of(value);
        }
        /**
         * The plane angle between the Y-Z plane and the plane containing both the stylus axis and the Y axis, in degrees of the range [-90,90], a positive tiltX is to the right (default: 0)
         * @return the protocol field value, empty when absent
         */
        public OptionalDouble tiltX() {
            Double value = CdpObject.numberAsDouble(raw("tiltX"));
            return value == null ? OptionalDouble.empty() : OptionalDouble.of(value);
        }
        /**
         * The plane angle between the X-Z plane and the plane containing both the stylus axis and the X axis, in degrees of the range [-90,90], a positive tiltY is towards the user (default: 0).
         * @return the protocol field value, empty when absent
         */
        public OptionalDouble tiltY() {
            Double value = CdpObject.numberAsDouble(raw("tiltY"));
            return value == null ? OptionalDouble.empty() : OptionalDouble.of(value);
        }
        /**
         * The clockwise rotation of a pen stylus around its own major axis, in degrees in the range [0,359] (default: 0).
         * <p><b>Experimental:</b> this part of CDP may change without notice.
         * @return the protocol field value, empty when absent
         */
        public OptionalLong twist() {
            Long value = CdpObject.numberAsLong(raw("twist"));
            return value == null ? OptionalLong.empty() : OptionalLong.of(value);
        }
        /**
         * Identifier used to track touch sources between events, must be unique within an event.
         * @return the protocol field value, empty when absent
         */
        public OptionalDouble id() {
            Double value = CdpObject.numberAsDouble(raw("id"));
            return value == null ? OptionalDouble.empty() : OptionalDouble.of(value);
        }
        /**
         * X coordinate of the event relative to the main frame&#x27;s viewport in CSS pixels.
         * @param x field value
         * @return this model
         */
        public TouchPoint x(double x) {
            set("x", x);
            return this;
        }
        /**
         * Y coordinate of the event relative to the main frame&#x27;s viewport in CSS pixels. 0 refers to the top of the viewport and Y increases as it proceeds towards the bottom of the viewport.
         * @param y field value
         * @return this model
         */
        public TouchPoint y(double y) {
            set("y", y);
            return this;
        }
        /**
         * X radius of the touch area (default: 1.0).
         * @param radiusX field value; empty omits the value
         * @return this model
         */
        public TouchPoint radiusX(OptionalDouble radiusX) {
            set("radiusX", radiusX.isPresent() ? radiusX.getAsDouble() : null);
            return this;
        }
        /**
         * X radius of the touch area (default: 1.0).
         * @param radiusX field value; null removes the value
         * @return this model
         */
        public TouchPoint radiusX(Double radiusX) {
            set("radiusX", radiusX);
            return this;
        }
        /**
         * Y radius of the touch area (default: 1.0).
         * @param radiusY field value; empty omits the value
         * @return this model
         */
        public TouchPoint radiusY(OptionalDouble radiusY) {
            set("radiusY", radiusY.isPresent() ? radiusY.getAsDouble() : null);
            return this;
        }
        /**
         * Y radius of the touch area (default: 1.0).
         * @param radiusY field value; null removes the value
         * @return this model
         */
        public TouchPoint radiusY(Double radiusY) {
            set("radiusY", radiusY);
            return this;
        }
        /**
         * Rotation angle (default: 0.0).
         * @param rotationAngle field value; empty omits the value
         * @return this model
         */
        public TouchPoint rotationAngle(OptionalDouble rotationAngle) {
            set("rotationAngle", rotationAngle.isPresent() ? rotationAngle.getAsDouble() : null);
            return this;
        }
        /**
         * Rotation angle (default: 0.0).
         * @param rotationAngle field value; null removes the value
         * @return this model
         */
        public TouchPoint rotationAngle(Double rotationAngle) {
            set("rotationAngle", rotationAngle);
            return this;
        }
        /**
         * Force (default: 1.0).
         * @param force field value; empty omits the value
         * @return this model
         */
        public TouchPoint force(OptionalDouble force) {
            set("force", force.isPresent() ? force.getAsDouble() : null);
            return this;
        }
        /**
         * Force (default: 1.0).
         * @param force field value; null removes the value
         * @return this model
         */
        public TouchPoint force(Double force) {
            set("force", force);
            return this;
        }
        /**
         * The normalized tangential pressure, which has a range of [-1,1] (default: 0).
         * <p><b>Experimental:</b> this part of CDP may change without notice.
         * @param tangentialPressure field value; empty omits the value
         * @return this model
         */
        public TouchPoint tangentialPressure(OptionalDouble tangentialPressure) {
            set("tangentialPressure", tangentialPressure.isPresent() ? tangentialPressure.getAsDouble() : null);
            return this;
        }
        /**
         * The normalized tangential pressure, which has a range of [-1,1] (default: 0).
         * <p><b>Experimental:</b> this part of CDP may change without notice.
         * @param tangentialPressure field value; null removes the value
         * @return this model
         */
        public TouchPoint tangentialPressure(Double tangentialPressure) {
            set("tangentialPressure", tangentialPressure);
            return this;
        }
        /**
         * The plane angle between the Y-Z plane and the plane containing both the stylus axis and the Y axis, in degrees of the range [-90,90], a positive tiltX is to the right (default: 0)
         * @param tiltX field value; empty omits the value
         * @return this model
         */
        public TouchPoint tiltX(OptionalDouble tiltX) {
            set("tiltX", tiltX.isPresent() ? tiltX.getAsDouble() : null);
            return this;
        }
        /**
         * The plane angle between the Y-Z plane and the plane containing both the stylus axis and the Y axis, in degrees of the range [-90,90], a positive tiltX is to the right (default: 0)
         * @param tiltX field value; null removes the value
         * @return this model
         */
        public TouchPoint tiltX(Double tiltX) {
            set("tiltX", tiltX);
            return this;
        }
        /**
         * The plane angle between the X-Z plane and the plane containing both the stylus axis and the X axis, in degrees of the range [-90,90], a positive tiltY is towards the user (default: 0).
         * @param tiltY field value; empty omits the value
         * @return this model
         */
        public TouchPoint tiltY(OptionalDouble tiltY) {
            set("tiltY", tiltY.isPresent() ? tiltY.getAsDouble() : null);
            return this;
        }
        /**
         * The plane angle between the X-Z plane and the plane containing both the stylus axis and the X axis, in degrees of the range [-90,90], a positive tiltY is towards the user (default: 0).
         * @param tiltY field value; null removes the value
         * @return this model
         */
        public TouchPoint tiltY(Double tiltY) {
            set("tiltY", tiltY);
            return this;
        }
        /**
         * The clockwise rotation of a pen stylus around its own major axis, in degrees in the range [0,359] (default: 0).
         * <p><b>Experimental:</b> this part of CDP may change without notice.
         * @param twist field value; empty omits the value
         * @return this model
         */
        public TouchPoint twist(OptionalLong twist) {
            set("twist", twist.isPresent() ? twist.getAsLong() : null);
            return this;
        }
        /**
         * The clockwise rotation of a pen stylus around its own major axis, in degrees in the range [0,359] (default: 0).
         * <p><b>Experimental:</b> this part of CDP may change without notice.
         * @param twist field value; null removes the value
         * @return this model
         */
        public TouchPoint twist(Long twist) {
            set("twist", twist);
            return this;
        }
        /**
         * Identifier used to track touch sources between events, must be unique within an event.
         * @param id field value; empty omits the value
         * @return this model
         */
        public TouchPoint id(OptionalDouble id) {
            set("id", id.isPresent() ? id.getAsDouble() : null);
            return this;
        }
        /**
         * Identifier used to track touch sources between events, must be unique within an event.
         * @param id field value; null removes the value
         * @return this model
         */
        public TouchPoint id(Double id) {
            set("id", id);
            return this;
        }
    }
    /**
     * Wire values for GestureSourceType.
     * <p><b>Experimental:</b> this part of CDP may change without notice.
     */
    public enum GestureSourceType implements CdpValue<String> {
        DEFAULT("default"),
        TOUCH("touch"),
        MOUSE("mouse");
        public final String value;
        GestureSourceType(String value) { this.value = value; }
        @Nonnull public String value() { return value; }
        public static GestureSourceType of(@Nonnull String value) {
            for (GestureSourceType constant : values()) {
                if (constant.value.equals(value)) return constant;
            }
            throw new IllegalArgumentException("Unknown GestureSourceType value: " + value);
        }
    }
    /**
     * Wire values for MouseButton.
     */
    public enum MouseButton implements CdpValue<String> {
        NONE("none"),
        LEFT("left"),
        MIDDLE("middle"),
        RIGHT("right"),
        BACK("back"),
        FORWARD("forward");
        public final String value;
        MouseButton(String value) { this.value = value; }
        @Nonnull public String value() { return value; }
        public static MouseButton of(@Nonnull String value) {
            for (MouseButton constant : values()) {
                if (constant.value.equals(value)) return constant;
            }
            throw new IllegalArgumentException("Unknown MouseButton value: " + value);
        }
    }
    /**
     * UTC time in seconds, counted from January 1, 1970.
     */
    public static final class TimeSinceEpoch implements CdpValue<Double> {
        public final double value;
        public TimeSinceEpoch(double value) { this.value = value; }
        @Nonnull public Double value() { return value; }
        @Override public boolean equals(Object other) {
            if (this == other) return true;
            if (!(other instanceof TimeSinceEpoch)) return false;
            return Double.compare(value, ((TimeSinceEpoch) other).value) == 0;
        }
        @Override public int hashCode() { return Double.hashCode(value); }
        @Override public String toString() { return "TimeSinceEpoch(" + value + ")"; }
    }
    /**
     * <p><b>Experimental:</b> this part of CDP may change without notice.
     */
    public static final class DragDataItem extends CdpObject {
        public DragDataItem() {}
        private DragDataItem(Map<String, Object> values) { super(values); }
        public static DragDataItem fromMap(Map<String, Object> values) {
            return new DragDataItem(values);
        }
        /**
         * Mime type of the dragged data.
         * @return the protocol field value
         */
        public String mimeType() {
            return (String) require("mimeType");
        }
        /**
         * Depending of the value of {@code mimeType}, it contains the dragged link, text, HTML markup or any other data.
         * @return the protocol field value
         */
        public String data() {
            return (String) require("data");
        }
        /**
         * Title associated with a link. Only valid when {@code mimeType} == &quot;text/uri-list&quot;.
         * @return the protocol field value, empty when absent
         */
        public Optional<String> title() {
            return Optional.ofNullable((String) raw("title"));
        }
        /**
         * Stores the base URL for the contained markup. Only valid when {@code mimeType} == &quot;text/html&quot;.
         * @return the protocol field value, empty when absent
         */
        public Optional<String> baseURL() {
            return Optional.ofNullable((String) raw("baseURL"));
        }
        /**
         * Mime type of the dragged data.
         * @param mimeType field value
         * @return this model
         */
        public DragDataItem mimeType(String mimeType) {
            set("mimeType", mimeType);
            return this;
        }
        /**
         * Depending of the value of {@code mimeType}, it contains the dragged link, text, HTML markup or any other data.
         * @param data field value
         * @return this model
         */
        public DragDataItem data(String data) {
            set("data", data);
            return this;
        }
        /**
         * Title associated with a link. Only valid when {@code mimeType} == &quot;text/uri-list&quot;.
         * @param title field value; empty omits the value
         * @return this model
         */
        public DragDataItem title(Optional<String> title) {
            set("title", title.orElse(null));
            return this;
        }
        /**
         * Title associated with a link. Only valid when {@code mimeType} == &quot;text/uri-list&quot;.
         * @param title field value; null removes the value
         * @return this model
         */
        public DragDataItem title(String title) {
            set("title", title);
            return this;
        }
        /**
         * Stores the base URL for the contained markup. Only valid when {@code mimeType} == &quot;text/html&quot;.
         * @param baseURL field value; empty omits the value
         * @return this model
         */
        public DragDataItem baseURL(Optional<String> baseURL) {
            set("baseURL", baseURL.orElse(null));
            return this;
        }
        /**
         * Stores the base URL for the contained markup. Only valid when {@code mimeType} == &quot;text/html&quot;.
         * @param baseURL field value; null removes the value
         * @return this model
         */
        public DragDataItem baseURL(String baseURL) {
            set("baseURL", baseURL);
            return this;
        }
    }
    /**
     * <p><b>Experimental:</b> this part of CDP may change without notice.
     */
    public static final class DragData extends CdpObject {
        public DragData() {}
        private DragData(Map<String, Object> values) { super(values); }
        public static DragData fromMap(Map<String, Object> values) {
            return new DragData(values);
        }
        /**
         * Returns the items field.
         * @return the protocol field value
         */
        public java.util.List<Input.DragDataItem> items() {
            return CdpObject.requireList(require("items"), element0 -> java.util.Objects.requireNonNull(Input.DragDataItem.fromMap(java.util.Objects.requireNonNull(CdpObject.objectMap(element0)))));
        }
        /**
         * List of filenames that should be included when dropping
         * @return the protocol field value, empty when absent
         */
        public Optional<java.util.List<String>> files() {
            return Optional.ofNullable(list(raw("files"), element0 -> (String) element0));
        }
        /**
         * Bit field representing allowed drag operations. Copy = 1, Link = 2, Move = 16
         * @return the protocol field value
         */
        public long dragOperationsMask() {
            return ((Number) require("dragOperationsMask")).longValue();
        }
        /**
         * Sets the items field.
         * @param items field value
         * @return this model
         */
        public DragData items(java.util.List<Input.DragDataItem> items) {
            set("items", items);
            return this;
        }
        /**
         * List of filenames that should be included when dropping
         * @param files field value; empty omits the value
         * @return this model
         */
        public DragData files(Optional<java.util.List<String>> files) {
            set("files", files.orElse(null));
            return this;
        }
        /**
         * List of filenames that should be included when dropping
         * @param files field value; null removes the value
         * @return this model
         */
        public DragData files(java.util.List<String> files) {
            set("files", files);
            return this;
        }
        /**
         * Bit field representing allowed drag operations. Copy = 1, Link = 2, Move = 16
         * @param dragOperationsMask field value
         * @return this model
         */
        public DragData dragOperationsMask(long dragOperationsMask) {
            set("dragOperationsMask", dragOperationsMask);
            return this;
        }
    }
    /**
     * Dispatches a drag event into the page.
     * <p><b>Experimental:</b> this part of CDP may change without notice.
     */
    public static final class DispatchDragEventRequest extends CdpObject {
        public DispatchDragEventRequest() {}
        /**
         * Dispatches a drag event into the page.
         * <p><b>Experimental:</b> this part of CDP may change without notice.
         * @param type protocol value
         * @param x protocol value
         * @param y protocol value
         * @param data protocol value
         */
        public DispatchDragEventRequest(DispatchDragEventTypeValues type, double x, double y, Input.DragData data) {
            set("type", type);
            set("x", x);
            set("y", y);
            set("data", data);
        }
        public static DispatchDragEventRequest fromMap(Map<String, Object> values) {
            DispatchDragEventRequest instance_ = new DispatchDragEventRequest();
            if (values != null) instance_.values.putAll(values);
            return instance_;
        }
        /**
         * Type of the drag event.
         * @return the protocol field value
         */
        public DispatchDragEventTypeValues type() {
            return DispatchDragEventTypeValues.of((String) require("type"));
        }
        /**
         * X coordinate of the event relative to the main frame&#x27;s viewport in CSS pixels.
         * @return the protocol field value
         */
        public double x() {
            return ((Number) require("x")).doubleValue();
        }
        /**
         * Y coordinate of the event relative to the main frame&#x27;s viewport in CSS pixels. 0 refers to the top of the viewport and Y increases as it proceeds towards the bottom of the viewport.
         * @return the protocol field value
         */
        public double y() {
            return ((Number) require("y")).doubleValue();
        }
        /**
         * Returns the data field.
         * @return the protocol field value
         */
        public Input.DragData data() {
            return java.util.Objects.requireNonNull(Input.DragData.fromMap(java.util.Objects.requireNonNull(CdpObject.objectMap(require("data")))));
        }
        /**
         * Bit field representing pressed modifier keys. Alt=1, Ctrl=2, Meta/Command=4, Shift=8 (default: 0).
         * @return the protocol field value, empty when absent
         */
        public OptionalLong modifiers() {
            Long value = CdpObject.numberAsLong(raw("modifiers"));
            return value == null ? OptionalLong.empty() : OptionalLong.of(value);
        }
        /**
         * Type of the drag event.
         * @param type field value
         * @return this model
         */
        public DispatchDragEventRequest type(DispatchDragEventTypeValues type) {
            set("type", type);
            return this;
        }
        /**
         * X coordinate of the event relative to the main frame&#x27;s viewport in CSS pixels.
         * @param x field value
         * @return this model
         */
        public DispatchDragEventRequest x(double x) {
            set("x", x);
            return this;
        }
        /**
         * Y coordinate of the event relative to the main frame&#x27;s viewport in CSS pixels. 0 refers to the top of the viewport and Y increases as it proceeds towards the bottom of the viewport.
         * @param y field value
         * @return this model
         */
        public DispatchDragEventRequest y(double y) {
            set("y", y);
            return this;
        }
        /**
         * Sets the data field.
         * @param data field value
         * @return this model
         */
        public DispatchDragEventRequest data(Input.DragData data) {
            set("data", data);
            return this;
        }
        /**
         * Bit field representing pressed modifier keys. Alt=1, Ctrl=2, Meta/Command=4, Shift=8 (default: 0).
         * @param modifiers field value; empty omits the value
         * @return this model
         */
        public DispatchDragEventRequest modifiers(OptionalLong modifiers) {
            set("modifiers", modifiers.isPresent() ? modifiers.getAsLong() : null);
            return this;
        }
        /**
         * Bit field representing pressed modifier keys. Alt=1, Ctrl=2, Meta/Command=4, Shift=8 (default: 0).
         * @param modifiers field value; null removes the value
         * @return this model
         */
        public DispatchDragEventRequest modifiers(Long modifiers) {
            set("modifiers", modifiers);
            return this;
        }
    }
    /**
     * Dispatches a key event to the page.
     */
    public static final class DispatchKeyEventRequest extends CdpObject {
        public DispatchKeyEventRequest() {}
        /**
         * Dispatches a key event to the page.
         * @param type protocol value
         */
        public DispatchKeyEventRequest(DispatchKeyEventTypeValues type) {
            set("type", type);
        }
        public static DispatchKeyEventRequest fromMap(Map<String, Object> values) {
            DispatchKeyEventRequest instance_ = new DispatchKeyEventRequest();
            if (values != null) instance_.values.putAll(values);
            return instance_;
        }
        /**
         * Type of the key event.
         * @return the protocol field value
         */
        public DispatchKeyEventTypeValues type() {
            return DispatchKeyEventTypeValues.of((String) require("type"));
        }
        /**
         * Bit field representing pressed modifier keys. Alt=1, Ctrl=2, Meta/Command=4, Shift=8 (default: 0).
         * @return the protocol field value, empty when absent
         */
        public OptionalLong modifiers() {
            Long value = CdpObject.numberAsLong(raw("modifiers"));
            return value == null ? OptionalLong.empty() : OptionalLong.of(value);
        }
        /**
         * Time at which the event occurred.
         * @return the protocol field value, empty when absent
         */
        public Optional<Input.TimeSinceEpoch> timestamp() {
            return Optional.ofNullable(raw("timestamp") == null ? null : new Input.TimeSinceEpoch(((Number) raw("timestamp")).doubleValue()));
        }
        /**
         * Text as generated by processing a virtual key code with a keyboard layout. Not needed for for {@code keyUp} and {@code rawKeyDown} events (default: &quot;&quot;)
         * @return the protocol field value, empty when absent
         */
        public Optional<String> text() {
            return Optional.ofNullable((String) raw("text"));
        }
        /**
         * Text that would have been generated by the keyboard if no modifiers were pressed (except for shift). Useful for shortcut (accelerator) key handling (default: &quot;&quot;).
         * @return the protocol field value, empty when absent
         */
        public Optional<String> unmodifiedText() {
            return Optional.ofNullable((String) raw("unmodifiedText"));
        }
        /**
         * Unique key identifier (e.g., &#x27;U+0041&#x27;) (default: &quot;&quot;).
         * @return the protocol field value, empty when absent
         */
        public Optional<String> keyIdentifier() {
            return Optional.ofNullable((String) raw("keyIdentifier"));
        }
        /**
         * Unique DOM defined string value for each physical key (e.g., &#x27;KeyA&#x27;) (default: &quot;&quot;).
         * @return the protocol field value, empty when absent
         */
        public Optional<String> code() {
            return Optional.ofNullable((String) raw("code"));
        }
        /**
         * Unique DOM defined string value describing the meaning of the key in the context of active modifiers, keyboard layout, etc (e.g., &#x27;AltGr&#x27;) (default: &quot;&quot;).
         * @return the protocol field value, empty when absent
         */
        public Optional<String> key() {
            return Optional.ofNullable((String) raw("key"));
        }
        /**
         * Windows virtual key code (default: 0).
         * @return the protocol field value, empty when absent
         */
        public OptionalLong windowsVirtualKeyCode() {
            Long value = CdpObject.numberAsLong(raw("windowsVirtualKeyCode"));
            return value == null ? OptionalLong.empty() : OptionalLong.of(value);
        }
        /**
         * Native virtual key code (default: 0).
         * @return the protocol field value, empty when absent
         */
        public OptionalLong nativeVirtualKeyCode() {
            Long value = CdpObject.numberAsLong(raw("nativeVirtualKeyCode"));
            return value == null ? OptionalLong.empty() : OptionalLong.of(value);
        }
        /**
         * Whether the event was generated from auto repeat (default: false).
         * @return the protocol field value, empty when absent
         */
        public Optional<Boolean> autoRepeat() {
            return Optional.ofNullable((Boolean) raw("autoRepeat"));
        }
        /**
         * Whether the event was generated from the keypad (default: false).
         * @return the protocol field value, empty when absent
         */
        public Optional<Boolean> isKeypad() {
            return Optional.ofNullable((Boolean) raw("isKeypad"));
        }
        /**
         * Whether the event was a system key event (default: false).
         * @return the protocol field value, empty when absent
         */
        public Optional<Boolean> isSystemKey() {
            return Optional.ofNullable((Boolean) raw("isSystemKey"));
        }
        /**
         * Whether the event was from the left or right side of the keyboard. 1=Left, 2=Right (default: 0).
         * @return the protocol field value, empty when absent
         */
        public OptionalLong location() {
            Long value = CdpObject.numberAsLong(raw("location"));
            return value == null ? OptionalLong.empty() : OptionalLong.of(value);
        }
        /**
         * Editing commands to send with the key event (e.g., &#x27;selectAll&#x27;) (default: []). These are related to but not equal the command names used in {@code document.execCommand} and NSStandardKeyBindingResponding. See https://source.chromium.org/chromium/chromium/src/+/main:third_party/blink/renderer/core/editing/commands/editor_command_names.h for valid command names.
         * <p><b>Experimental:</b> this part of CDP may change without notice.
         * @return the protocol field value, empty when absent
         */
        public Optional<java.util.List<String>> commands() {
            return Optional.ofNullable(list(raw("commands"), element0 -> (String) element0));
        }
        /**
         * Type of the key event.
         * @param type field value
         * @return this model
         */
        public DispatchKeyEventRequest type(DispatchKeyEventTypeValues type) {
            set("type", type);
            return this;
        }
        /**
         * Bit field representing pressed modifier keys. Alt=1, Ctrl=2, Meta/Command=4, Shift=8 (default: 0).
         * @param modifiers field value; empty omits the value
         * @return this model
         */
        public DispatchKeyEventRequest modifiers(OptionalLong modifiers) {
            set("modifiers", modifiers.isPresent() ? modifiers.getAsLong() : null);
            return this;
        }
        /**
         * Bit field representing pressed modifier keys. Alt=1, Ctrl=2, Meta/Command=4, Shift=8 (default: 0).
         * @param modifiers field value; null removes the value
         * @return this model
         */
        public DispatchKeyEventRequest modifiers(Long modifiers) {
            set("modifiers", modifiers);
            return this;
        }
        /**
         * Time at which the event occurred.
         * @param timestamp field value; empty omits the value
         * @return this model
         */
        public DispatchKeyEventRequest timestamp(Optional<Input.TimeSinceEpoch> timestamp) {
            set("timestamp", timestamp.orElse(null));
            return this;
        }
        /**
         * Time at which the event occurred.
         * @param timestamp field value; null removes the value
         * @return this model
         */
        public DispatchKeyEventRequest timestamp(Input.TimeSinceEpoch timestamp) {
            set("timestamp", timestamp);
            return this;
        }
        /**
         * Text as generated by processing a virtual key code with a keyboard layout. Not needed for for {@code keyUp} and {@code rawKeyDown} events (default: &quot;&quot;)
         * @param text field value; empty omits the value
         * @return this model
         */
        public DispatchKeyEventRequest text(Optional<String> text) {
            set("text", text.orElse(null));
            return this;
        }
        /**
         * Text as generated by processing a virtual key code with a keyboard layout. Not needed for for {@code keyUp} and {@code rawKeyDown} events (default: &quot;&quot;)
         * @param text field value; null removes the value
         * @return this model
         */
        public DispatchKeyEventRequest text(String text) {
            set("text", text);
            return this;
        }
        /**
         * Text that would have been generated by the keyboard if no modifiers were pressed (except for shift). Useful for shortcut (accelerator) key handling (default: &quot;&quot;).
         * @param unmodifiedText field value; empty omits the value
         * @return this model
         */
        public DispatchKeyEventRequest unmodifiedText(Optional<String> unmodifiedText) {
            set("unmodifiedText", unmodifiedText.orElse(null));
            return this;
        }
        /**
         * Text that would have been generated by the keyboard if no modifiers were pressed (except for shift). Useful for shortcut (accelerator) key handling (default: &quot;&quot;).
         * @param unmodifiedText field value; null removes the value
         * @return this model
         */
        public DispatchKeyEventRequest unmodifiedText(String unmodifiedText) {
            set("unmodifiedText", unmodifiedText);
            return this;
        }
        /**
         * Unique key identifier (e.g., &#x27;U+0041&#x27;) (default: &quot;&quot;).
         * @param keyIdentifier field value; empty omits the value
         * @return this model
         */
        public DispatchKeyEventRequest keyIdentifier(Optional<String> keyIdentifier) {
            set("keyIdentifier", keyIdentifier.orElse(null));
            return this;
        }
        /**
         * Unique key identifier (e.g., &#x27;U+0041&#x27;) (default: &quot;&quot;).
         * @param keyIdentifier field value; null removes the value
         * @return this model
         */
        public DispatchKeyEventRequest keyIdentifier(String keyIdentifier) {
            set("keyIdentifier", keyIdentifier);
            return this;
        }
        /**
         * Unique DOM defined string value for each physical key (e.g., &#x27;KeyA&#x27;) (default: &quot;&quot;).
         * @param code field value; empty omits the value
         * @return this model
         */
        public DispatchKeyEventRequest code(Optional<String> code) {
            set("code", code.orElse(null));
            return this;
        }
        /**
         * Unique DOM defined string value for each physical key (e.g., &#x27;KeyA&#x27;) (default: &quot;&quot;).
         * @param code field value; null removes the value
         * @return this model
         */
        public DispatchKeyEventRequest code(String code) {
            set("code", code);
            return this;
        }
        /**
         * Unique DOM defined string value describing the meaning of the key in the context of active modifiers, keyboard layout, etc (e.g., &#x27;AltGr&#x27;) (default: &quot;&quot;).
         * @param key field value; empty omits the value
         * @return this model
         */
        public DispatchKeyEventRequest key(Optional<String> key) {
            set("key", key.orElse(null));
            return this;
        }
        /**
         * Unique DOM defined string value describing the meaning of the key in the context of active modifiers, keyboard layout, etc (e.g., &#x27;AltGr&#x27;) (default: &quot;&quot;).
         * @param key field value; null removes the value
         * @return this model
         */
        public DispatchKeyEventRequest key(String key) {
            set("key", key);
            return this;
        }
        /**
         * Windows virtual key code (default: 0).
         * @param windowsVirtualKeyCode field value; empty omits the value
         * @return this model
         */
        public DispatchKeyEventRequest windowsVirtualKeyCode(OptionalLong windowsVirtualKeyCode) {
            set("windowsVirtualKeyCode", windowsVirtualKeyCode.isPresent() ? windowsVirtualKeyCode.getAsLong() : null);
            return this;
        }
        /**
         * Windows virtual key code (default: 0).
         * @param windowsVirtualKeyCode field value; null removes the value
         * @return this model
         */
        public DispatchKeyEventRequest windowsVirtualKeyCode(Long windowsVirtualKeyCode) {
            set("windowsVirtualKeyCode", windowsVirtualKeyCode);
            return this;
        }
        /**
         * Native virtual key code (default: 0).
         * @param nativeVirtualKeyCode field value; empty omits the value
         * @return this model
         */
        public DispatchKeyEventRequest nativeVirtualKeyCode(OptionalLong nativeVirtualKeyCode) {
            set("nativeVirtualKeyCode", nativeVirtualKeyCode.isPresent() ? nativeVirtualKeyCode.getAsLong() : null);
            return this;
        }
        /**
         * Native virtual key code (default: 0).
         * @param nativeVirtualKeyCode field value; null removes the value
         * @return this model
         */
        public DispatchKeyEventRequest nativeVirtualKeyCode(Long nativeVirtualKeyCode) {
            set("nativeVirtualKeyCode", nativeVirtualKeyCode);
            return this;
        }
        /**
         * Whether the event was generated from auto repeat (default: false).
         * @param autoRepeat field value; empty omits the value
         * @return this model
         */
        public DispatchKeyEventRequest autoRepeat(Optional<Boolean> autoRepeat) {
            set("autoRepeat", autoRepeat.orElse(null));
            return this;
        }
        /**
         * Whether the event was generated from auto repeat (default: false).
         * @param autoRepeat field value; null removes the value
         * @return this model
         */
        public DispatchKeyEventRequest autoRepeat(Boolean autoRepeat) {
            set("autoRepeat", autoRepeat);
            return this;
        }
        /**
         * Whether the event was generated from the keypad (default: false).
         * @param isKeypad field value; empty omits the value
         * @return this model
         */
        public DispatchKeyEventRequest isKeypad(Optional<Boolean> isKeypad) {
            set("isKeypad", isKeypad.orElse(null));
            return this;
        }
        /**
         * Whether the event was generated from the keypad (default: false).
         * @param isKeypad field value; null removes the value
         * @return this model
         */
        public DispatchKeyEventRequest isKeypad(Boolean isKeypad) {
            set("isKeypad", isKeypad);
            return this;
        }
        /**
         * Whether the event was a system key event (default: false).
         * @param isSystemKey field value; empty omits the value
         * @return this model
         */
        public DispatchKeyEventRequest isSystemKey(Optional<Boolean> isSystemKey) {
            set("isSystemKey", isSystemKey.orElse(null));
            return this;
        }
        /**
         * Whether the event was a system key event (default: false).
         * @param isSystemKey field value; null removes the value
         * @return this model
         */
        public DispatchKeyEventRequest isSystemKey(Boolean isSystemKey) {
            set("isSystemKey", isSystemKey);
            return this;
        }
        /**
         * Whether the event was from the left or right side of the keyboard. 1=Left, 2=Right (default: 0).
         * @param location field value; empty omits the value
         * @return this model
         */
        public DispatchKeyEventRequest location(OptionalLong location) {
            set("location", location.isPresent() ? location.getAsLong() : null);
            return this;
        }
        /**
         * Whether the event was from the left or right side of the keyboard. 1=Left, 2=Right (default: 0).
         * @param location field value; null removes the value
         * @return this model
         */
        public DispatchKeyEventRequest location(Long location) {
            set("location", location);
            return this;
        }
        /**
         * Editing commands to send with the key event (e.g., &#x27;selectAll&#x27;) (default: []). These are related to but not equal the command names used in {@code document.execCommand} and NSStandardKeyBindingResponding. See https://source.chromium.org/chromium/chromium/src/+/main:third_party/blink/renderer/core/editing/commands/editor_command_names.h for valid command names.
         * <p><b>Experimental:</b> this part of CDP may change without notice.
         * @param commands field value; empty omits the value
         * @return this model
         */
        public DispatchKeyEventRequest commands(Optional<java.util.List<String>> commands) {
            set("commands", commands.orElse(null));
            return this;
        }
        /**
         * Editing commands to send with the key event (e.g., &#x27;selectAll&#x27;) (default: []). These are related to but not equal the command names used in {@code document.execCommand} and NSStandardKeyBindingResponding. See https://source.chromium.org/chromium/chromium/src/+/main:third_party/blink/renderer/core/editing/commands/editor_command_names.h for valid command names.
         * <p><b>Experimental:</b> this part of CDP may change without notice.
         * @param commands field value; null removes the value
         * @return this model
         */
        public DispatchKeyEventRequest commands(java.util.List<String> commands) {
            set("commands", commands);
            return this;
        }
    }
    /**
     * This method emulates inserting text that doesn&#x27;t come from a key press, for example an emoji keyboard or an IME.
     * <p><b>Experimental:</b> this part of CDP may change without notice.
     */
    public static final class InsertTextRequest extends CdpObject {
        public InsertTextRequest() {}
        /**
         * This method emulates inserting text that doesn&#x27;t come from a key press, for example an emoji keyboard or an IME.
         * <p><b>Experimental:</b> this part of CDP may change without notice.
         * @param text protocol value
         */
        public InsertTextRequest(String text) {
            set("text", text);
        }
        public static InsertTextRequest fromMap(Map<String, Object> values) {
            InsertTextRequest instance_ = new InsertTextRequest();
            if (values != null) instance_.values.putAll(values);
            return instance_;
        }
        /**
         * The text to insert.
         * @return the protocol field value
         */
        public String text() {
            return (String) require("text");
        }
        /**
         * The text to insert.
         * @param text field value
         * @return this model
         */
        public InsertTextRequest text(String text) {
            set("text", text);
            return this;
        }
    }
    /**
     * This method sets the current candidate text for IME. Use imeCommitComposition to commit the final text. Use imeSetComposition with empty string as text to cancel composition.
     * <p><b>Experimental:</b> this part of CDP may change without notice.
     */
    public static final class ImeSetCompositionRequest extends CdpObject {
        public ImeSetCompositionRequest() {}
        /**
         * This method sets the current candidate text for IME. Use imeCommitComposition to commit the final text. Use imeSetComposition with empty string as text to cancel composition.
         * <p><b>Experimental:</b> this part of CDP may change without notice.
         * @param text protocol value
         * @param selectionStart protocol value
         * @param selectionEnd protocol value
         */
        public ImeSetCompositionRequest(String text, long selectionStart, long selectionEnd) {
            set("text", text);
            set("selectionStart", selectionStart);
            set("selectionEnd", selectionEnd);
        }
        public static ImeSetCompositionRequest fromMap(Map<String, Object> values) {
            ImeSetCompositionRequest instance_ = new ImeSetCompositionRequest();
            if (values != null) instance_.values.putAll(values);
            return instance_;
        }
        /**
         * The text to insert
         * @return the protocol field value
         */
        public String text() {
            return (String) require("text");
        }
        /**
         * selection start
         * @return the protocol field value
         */
        public long selectionStart() {
            return ((Number) require("selectionStart")).longValue();
        }
        /**
         * selection end
         * @return the protocol field value
         */
        public long selectionEnd() {
            return ((Number) require("selectionEnd")).longValue();
        }
        /**
         * replacement start
         * @return the protocol field value, empty when absent
         */
        public OptionalLong replacementStart() {
            Long value = CdpObject.numberAsLong(raw("replacementStart"));
            return value == null ? OptionalLong.empty() : OptionalLong.of(value);
        }
        /**
         * replacement end
         * @return the protocol field value, empty when absent
         */
        public OptionalLong replacementEnd() {
            Long value = CdpObject.numberAsLong(raw("replacementEnd"));
            return value == null ? OptionalLong.empty() : OptionalLong.of(value);
        }
        /**
         * The text to insert
         * @param text field value
         * @return this model
         */
        public ImeSetCompositionRequest text(String text) {
            set("text", text);
            return this;
        }
        /**
         * selection start
         * @param selectionStart field value
         * @return this model
         */
        public ImeSetCompositionRequest selectionStart(long selectionStart) {
            set("selectionStart", selectionStart);
            return this;
        }
        /**
         * selection end
         * @param selectionEnd field value
         * @return this model
         */
        public ImeSetCompositionRequest selectionEnd(long selectionEnd) {
            set("selectionEnd", selectionEnd);
            return this;
        }
        /**
         * replacement start
         * @param replacementStart field value; empty omits the value
         * @return this model
         */
        public ImeSetCompositionRequest replacementStart(OptionalLong replacementStart) {
            set("replacementStart", replacementStart.isPresent() ? replacementStart.getAsLong() : null);
            return this;
        }
        /**
         * replacement start
         * @param replacementStart field value; null removes the value
         * @return this model
         */
        public ImeSetCompositionRequest replacementStart(Long replacementStart) {
            set("replacementStart", replacementStart);
            return this;
        }
        /**
         * replacement end
         * @param replacementEnd field value; empty omits the value
         * @return this model
         */
        public ImeSetCompositionRequest replacementEnd(OptionalLong replacementEnd) {
            set("replacementEnd", replacementEnd.isPresent() ? replacementEnd.getAsLong() : null);
            return this;
        }
        /**
         * replacement end
         * @param replacementEnd field value; null removes the value
         * @return this model
         */
        public ImeSetCompositionRequest replacementEnd(Long replacementEnd) {
            set("replacementEnd", replacementEnd);
            return this;
        }
    }
    /**
     * Dispatches a mouse event to the page.
     */
    public static final class DispatchMouseEventRequest extends CdpObject {
        public DispatchMouseEventRequest() {}
        /**
         * Dispatches a mouse event to the page.
         * @param type protocol value
         * @param x protocol value
         * @param y protocol value
         */
        public DispatchMouseEventRequest(DispatchMouseEventTypeValues type, double x, double y) {
            set("type", type);
            set("x", x);
            set("y", y);
        }
        public static DispatchMouseEventRequest fromMap(Map<String, Object> values) {
            DispatchMouseEventRequest instance_ = new DispatchMouseEventRequest();
            if (values != null) instance_.values.putAll(values);
            return instance_;
        }
        /**
         * Type of the mouse event.
         * @return the protocol field value
         */
        public DispatchMouseEventTypeValues type() {
            return DispatchMouseEventTypeValues.of((String) require("type"));
        }
        /**
         * X coordinate of the event relative to the main frame&#x27;s viewport in CSS pixels.
         * @return the protocol field value
         */
        public double x() {
            return ((Number) require("x")).doubleValue();
        }
        /**
         * Y coordinate of the event relative to the main frame&#x27;s viewport in CSS pixels. 0 refers to the top of the viewport and Y increases as it proceeds towards the bottom of the viewport.
         * @return the protocol field value
         */
        public double y() {
            return ((Number) require("y")).doubleValue();
        }
        /**
         * Bit field representing pressed modifier keys. Alt=1, Ctrl=2, Meta/Command=4, Shift=8 (default: 0).
         * @return the protocol field value, empty when absent
         */
        public OptionalLong modifiers() {
            Long value = CdpObject.numberAsLong(raw("modifiers"));
            return value == null ? OptionalLong.empty() : OptionalLong.of(value);
        }
        /**
         * Time at which the event occurred.
         * @return the protocol field value, empty when absent
         */
        public Optional<Input.TimeSinceEpoch> timestamp() {
            return Optional.ofNullable(raw("timestamp") == null ? null : new Input.TimeSinceEpoch(((Number) raw("timestamp")).doubleValue()));
        }
        /**
         * Mouse button (default: &quot;none&quot;).
         * @return the protocol field value, empty when absent
         */
        public Optional<Input.MouseButton> button() {
            return Optional.ofNullable(raw("button") == null ? null : Input.MouseButton.of((String) raw("button")));
        }
        /**
         * A number indicating which buttons are pressed on the mouse when a mouse event is triggered. Left=1, Right=2, Middle=4, Back=8, Forward=16, None=0.
         * @return the protocol field value, empty when absent
         */
        public OptionalLong buttons() {
            Long value = CdpObject.numberAsLong(raw("buttons"));
            return value == null ? OptionalLong.empty() : OptionalLong.of(value);
        }
        /**
         * Number of times the mouse button was clicked (default: 0).
         * @return the protocol field value, empty when absent
         */
        public OptionalLong clickCount() {
            Long value = CdpObject.numberAsLong(raw("clickCount"));
            return value == null ? OptionalLong.empty() : OptionalLong.of(value);
        }
        /**
         * The normalized pressure, which has a range of [0,1] (default: 0).
         * <p><b>Experimental:</b> this part of CDP may change without notice.
         * @return the protocol field value, empty when absent
         */
        public OptionalDouble force() {
            Double value = CdpObject.numberAsDouble(raw("force"));
            return value == null ? OptionalDouble.empty() : OptionalDouble.of(value);
        }
        /**
         * The normalized tangential pressure, which has a range of [-1,1] (default: 0).
         * <p><b>Experimental:</b> this part of CDP may change without notice.
         * @return the protocol field value, empty when absent
         */
        public OptionalDouble tangentialPressure() {
            Double value = CdpObject.numberAsDouble(raw("tangentialPressure"));
            return value == null ? OptionalDouble.empty() : OptionalDouble.of(value);
        }
        /**
         * The plane angle between the Y-Z plane and the plane containing both the stylus axis and the Y axis, in degrees of the range [-90,90], a positive tiltX is to the right (default: 0).
         * @return the protocol field value, empty when absent
         */
        public OptionalDouble tiltX() {
            Double value = CdpObject.numberAsDouble(raw("tiltX"));
            return value == null ? OptionalDouble.empty() : OptionalDouble.of(value);
        }
        /**
         * The plane angle between the X-Z plane and the plane containing both the stylus axis and the X axis, in degrees of the range [-90,90], a positive tiltY is towards the user (default: 0).
         * @return the protocol field value, empty when absent
         */
        public OptionalDouble tiltY() {
            Double value = CdpObject.numberAsDouble(raw("tiltY"));
            return value == null ? OptionalDouble.empty() : OptionalDouble.of(value);
        }
        /**
         * The clockwise rotation of a pen stylus around its own major axis, in degrees in the range [0,359] (default: 0).
         * <p><b>Experimental:</b> this part of CDP may change without notice.
         * @return the protocol field value, empty when absent
         */
        public OptionalLong twist() {
            Long value = CdpObject.numberAsLong(raw("twist"));
            return value == null ? OptionalLong.empty() : OptionalLong.of(value);
        }
        /**
         * X delta in CSS pixels for mouse wheel event (default: 0).
         * @return the protocol field value, empty when absent
         */
        public OptionalDouble deltaX() {
            Double value = CdpObject.numberAsDouble(raw("deltaX"));
            return value == null ? OptionalDouble.empty() : OptionalDouble.of(value);
        }
        /**
         * Y delta in CSS pixels for mouse wheel event (default: 0).
         * @return the protocol field value, empty when absent
         */
        public OptionalDouble deltaY() {
            Double value = CdpObject.numberAsDouble(raw("deltaY"));
            return value == null ? OptionalDouble.empty() : OptionalDouble.of(value);
        }
        /**
         * Pointer type (default: &quot;mouse&quot;).
         * @return the protocol field value, empty when absent
         */
        public Optional<DispatchMouseEventPointerTypeValues> pointerType() {
            return Optional.ofNullable(raw("pointerType") == null ? null : DispatchMouseEventPointerTypeValues.of((String) raw("pointerType")));
        }
        /**
         * Type of the mouse event.
         * @param type field value
         * @return this model
         */
        public DispatchMouseEventRequest type(DispatchMouseEventTypeValues type) {
            set("type", type);
            return this;
        }
        /**
         * X coordinate of the event relative to the main frame&#x27;s viewport in CSS pixels.
         * @param x field value
         * @return this model
         */
        public DispatchMouseEventRequest x(double x) {
            set("x", x);
            return this;
        }
        /**
         * Y coordinate of the event relative to the main frame&#x27;s viewport in CSS pixels. 0 refers to the top of the viewport and Y increases as it proceeds towards the bottom of the viewport.
         * @param y field value
         * @return this model
         */
        public DispatchMouseEventRequest y(double y) {
            set("y", y);
            return this;
        }
        /**
         * Bit field representing pressed modifier keys. Alt=1, Ctrl=2, Meta/Command=4, Shift=8 (default: 0).
         * @param modifiers field value; empty omits the value
         * @return this model
         */
        public DispatchMouseEventRequest modifiers(OptionalLong modifiers) {
            set("modifiers", modifiers.isPresent() ? modifiers.getAsLong() : null);
            return this;
        }
        /**
         * Bit field representing pressed modifier keys. Alt=1, Ctrl=2, Meta/Command=4, Shift=8 (default: 0).
         * @param modifiers field value; null removes the value
         * @return this model
         */
        public DispatchMouseEventRequest modifiers(Long modifiers) {
            set("modifiers", modifiers);
            return this;
        }
        /**
         * Time at which the event occurred.
         * @param timestamp field value; empty omits the value
         * @return this model
         */
        public DispatchMouseEventRequest timestamp(Optional<Input.TimeSinceEpoch> timestamp) {
            set("timestamp", timestamp.orElse(null));
            return this;
        }
        /**
         * Time at which the event occurred.
         * @param timestamp field value; null removes the value
         * @return this model
         */
        public DispatchMouseEventRequest timestamp(Input.TimeSinceEpoch timestamp) {
            set("timestamp", timestamp);
            return this;
        }
        /**
         * Mouse button (default: &quot;none&quot;).
         * @param button field value; empty omits the value
         * @return this model
         */
        public DispatchMouseEventRequest button(Optional<Input.MouseButton> button) {
            set("button", button.orElse(null));
            return this;
        }
        /**
         * Mouse button (default: &quot;none&quot;).
         * @param button field value; null removes the value
         * @return this model
         */
        public DispatchMouseEventRequest button(Input.MouseButton button) {
            set("button", button);
            return this;
        }
        /**
         * A number indicating which buttons are pressed on the mouse when a mouse event is triggered. Left=1, Right=2, Middle=4, Back=8, Forward=16, None=0.
         * @param buttons field value; empty omits the value
         * @return this model
         */
        public DispatchMouseEventRequest buttons(OptionalLong buttons) {
            set("buttons", buttons.isPresent() ? buttons.getAsLong() : null);
            return this;
        }
        /**
         * A number indicating which buttons are pressed on the mouse when a mouse event is triggered. Left=1, Right=2, Middle=4, Back=8, Forward=16, None=0.
         * @param buttons field value; null removes the value
         * @return this model
         */
        public DispatchMouseEventRequest buttons(Long buttons) {
            set("buttons", buttons);
            return this;
        }
        /**
         * Number of times the mouse button was clicked (default: 0).
         * @param clickCount field value; empty omits the value
         * @return this model
         */
        public DispatchMouseEventRequest clickCount(OptionalLong clickCount) {
            set("clickCount", clickCount.isPresent() ? clickCount.getAsLong() : null);
            return this;
        }
        /**
         * Number of times the mouse button was clicked (default: 0).
         * @param clickCount field value; null removes the value
         * @return this model
         */
        public DispatchMouseEventRequest clickCount(Long clickCount) {
            set("clickCount", clickCount);
            return this;
        }
        /**
         * The normalized pressure, which has a range of [0,1] (default: 0).
         * <p><b>Experimental:</b> this part of CDP may change without notice.
         * @param force field value; empty omits the value
         * @return this model
         */
        public DispatchMouseEventRequest force(OptionalDouble force) {
            set("force", force.isPresent() ? force.getAsDouble() : null);
            return this;
        }
        /**
         * The normalized pressure, which has a range of [0,1] (default: 0).
         * <p><b>Experimental:</b> this part of CDP may change without notice.
         * @param force field value; null removes the value
         * @return this model
         */
        public DispatchMouseEventRequest force(Double force) {
            set("force", force);
            return this;
        }
        /**
         * The normalized tangential pressure, which has a range of [-1,1] (default: 0).
         * <p><b>Experimental:</b> this part of CDP may change without notice.
         * @param tangentialPressure field value; empty omits the value
         * @return this model
         */
        public DispatchMouseEventRequest tangentialPressure(OptionalDouble tangentialPressure) {
            set("tangentialPressure", tangentialPressure.isPresent() ? tangentialPressure.getAsDouble() : null);
            return this;
        }
        /**
         * The normalized tangential pressure, which has a range of [-1,1] (default: 0).
         * <p><b>Experimental:</b> this part of CDP may change without notice.
         * @param tangentialPressure field value; null removes the value
         * @return this model
         */
        public DispatchMouseEventRequest tangentialPressure(Double tangentialPressure) {
            set("tangentialPressure", tangentialPressure);
            return this;
        }
        /**
         * The plane angle between the Y-Z plane and the plane containing both the stylus axis and the Y axis, in degrees of the range [-90,90], a positive tiltX is to the right (default: 0).
         * @param tiltX field value; empty omits the value
         * @return this model
         */
        public DispatchMouseEventRequest tiltX(OptionalDouble tiltX) {
            set("tiltX", tiltX.isPresent() ? tiltX.getAsDouble() : null);
            return this;
        }
        /**
         * The plane angle between the Y-Z plane and the plane containing both the stylus axis and the Y axis, in degrees of the range [-90,90], a positive tiltX is to the right (default: 0).
         * @param tiltX field value; null removes the value
         * @return this model
         */
        public DispatchMouseEventRequest tiltX(Double tiltX) {
            set("tiltX", tiltX);
            return this;
        }
        /**
         * The plane angle between the X-Z plane and the plane containing both the stylus axis and the X axis, in degrees of the range [-90,90], a positive tiltY is towards the user (default: 0).
         * @param tiltY field value; empty omits the value
         * @return this model
         */
        public DispatchMouseEventRequest tiltY(OptionalDouble tiltY) {
            set("tiltY", tiltY.isPresent() ? tiltY.getAsDouble() : null);
            return this;
        }
        /**
         * The plane angle between the X-Z plane and the plane containing both the stylus axis and the X axis, in degrees of the range [-90,90], a positive tiltY is towards the user (default: 0).
         * @param tiltY field value; null removes the value
         * @return this model
         */
        public DispatchMouseEventRequest tiltY(Double tiltY) {
            set("tiltY", tiltY);
            return this;
        }
        /**
         * The clockwise rotation of a pen stylus around its own major axis, in degrees in the range [0,359] (default: 0).
         * <p><b>Experimental:</b> this part of CDP may change without notice.
         * @param twist field value; empty omits the value
         * @return this model
         */
        public DispatchMouseEventRequest twist(OptionalLong twist) {
            set("twist", twist.isPresent() ? twist.getAsLong() : null);
            return this;
        }
        /**
         * The clockwise rotation of a pen stylus around its own major axis, in degrees in the range [0,359] (default: 0).
         * <p><b>Experimental:</b> this part of CDP may change without notice.
         * @param twist field value; null removes the value
         * @return this model
         */
        public DispatchMouseEventRequest twist(Long twist) {
            set("twist", twist);
            return this;
        }
        /**
         * X delta in CSS pixels for mouse wheel event (default: 0).
         * @param deltaX field value; empty omits the value
         * @return this model
         */
        public DispatchMouseEventRequest deltaX(OptionalDouble deltaX) {
            set("deltaX", deltaX.isPresent() ? deltaX.getAsDouble() : null);
            return this;
        }
        /**
         * X delta in CSS pixels for mouse wheel event (default: 0).
         * @param deltaX field value; null removes the value
         * @return this model
         */
        public DispatchMouseEventRequest deltaX(Double deltaX) {
            set("deltaX", deltaX);
            return this;
        }
        /**
         * Y delta in CSS pixels for mouse wheel event (default: 0).
         * @param deltaY field value; empty omits the value
         * @return this model
         */
        public DispatchMouseEventRequest deltaY(OptionalDouble deltaY) {
            set("deltaY", deltaY.isPresent() ? deltaY.getAsDouble() : null);
            return this;
        }
        /**
         * Y delta in CSS pixels for mouse wheel event (default: 0).
         * @param deltaY field value; null removes the value
         * @return this model
         */
        public DispatchMouseEventRequest deltaY(Double deltaY) {
            set("deltaY", deltaY);
            return this;
        }
        /**
         * Pointer type (default: &quot;mouse&quot;).
         * @param pointerType field value; empty omits the value
         * @return this model
         */
        public DispatchMouseEventRequest pointerType(Optional<DispatchMouseEventPointerTypeValues> pointerType) {
            set("pointerType", pointerType.orElse(null));
            return this;
        }
        /**
         * Pointer type (default: &quot;mouse&quot;).
         * @param pointerType field value; null removes the value
         * @return this model
         */
        public DispatchMouseEventRequest pointerType(DispatchMouseEventPointerTypeValues pointerType) {
            set("pointerType", pointerType);
            return this;
        }
    }
    /**
     * Dispatches a touch event to the page.
     */
    public static final class DispatchTouchEventRequest extends CdpObject {
        public DispatchTouchEventRequest() {}
        /**
         * Dispatches a touch event to the page.
         * @param type protocol value
         * @param touchPoints protocol value
         */
        public DispatchTouchEventRequest(DispatchTouchEventTypeValues type, java.util.List<Input.TouchPoint> touchPoints) {
            set("type", type);
            set("touchPoints", touchPoints);
        }
        public static DispatchTouchEventRequest fromMap(Map<String, Object> values) {
            DispatchTouchEventRequest instance_ = new DispatchTouchEventRequest();
            if (values != null) instance_.values.putAll(values);
            return instance_;
        }
        /**
         * Type of the touch event. TouchEnd and TouchCancel must not contain any touch points, while TouchStart and TouchMove must contains at least one.
         * @return the protocol field value
         */
        public DispatchTouchEventTypeValues type() {
            return DispatchTouchEventTypeValues.of((String) require("type"));
        }
        /**
         * Active touch points on the touch device. One event per any changed point (compared to previous touch event in a sequence) is generated, emulating pressing/moving/releasing points one by one.
         * @return the protocol field value
         */
        public java.util.List<Input.TouchPoint> touchPoints() {
            return CdpObject.requireList(require("touchPoints"), element0 -> java.util.Objects.requireNonNull(Input.TouchPoint.fromMap(java.util.Objects.requireNonNull(CdpObject.objectMap(element0)))));
        }
        /**
         * Bit field representing pressed modifier keys. Alt=1, Ctrl=2, Meta/Command=4, Shift=8 (default: 0).
         * @return the protocol field value, empty when absent
         */
        public OptionalLong modifiers() {
            Long value = CdpObject.numberAsLong(raw("modifiers"));
            return value == null ? OptionalLong.empty() : OptionalLong.of(value);
        }
        /**
         * Time at which the event occurred.
         * @return the protocol field value, empty when absent
         */
        public Optional<Input.TimeSinceEpoch> timestamp() {
            return Optional.ofNullable(raw("timestamp") == null ? null : new Input.TimeSinceEpoch(((Number) raw("timestamp")).doubleValue()));
        }
        /**
         * Type of the touch event. TouchEnd and TouchCancel must not contain any touch points, while TouchStart and TouchMove must contains at least one.
         * @param type field value
         * @return this model
         */
        public DispatchTouchEventRequest type(DispatchTouchEventTypeValues type) {
            set("type", type);
            return this;
        }
        /**
         * Active touch points on the touch device. One event per any changed point (compared to previous touch event in a sequence) is generated, emulating pressing/moving/releasing points one by one.
         * @param touchPoints field value
         * @return this model
         */
        public DispatchTouchEventRequest touchPoints(java.util.List<Input.TouchPoint> touchPoints) {
            set("touchPoints", touchPoints);
            return this;
        }
        /**
         * Bit field representing pressed modifier keys. Alt=1, Ctrl=2, Meta/Command=4, Shift=8 (default: 0).
         * @param modifiers field value; empty omits the value
         * @return this model
         */
        public DispatchTouchEventRequest modifiers(OptionalLong modifiers) {
            set("modifiers", modifiers.isPresent() ? modifiers.getAsLong() : null);
            return this;
        }
        /**
         * Bit field representing pressed modifier keys. Alt=1, Ctrl=2, Meta/Command=4, Shift=8 (default: 0).
         * @param modifiers field value; null removes the value
         * @return this model
         */
        public DispatchTouchEventRequest modifiers(Long modifiers) {
            set("modifiers", modifiers);
            return this;
        }
        /**
         * Time at which the event occurred.
         * @param timestamp field value; empty omits the value
         * @return this model
         */
        public DispatchTouchEventRequest timestamp(Optional<Input.TimeSinceEpoch> timestamp) {
            set("timestamp", timestamp.orElse(null));
            return this;
        }
        /**
         * Time at which the event occurred.
         * @param timestamp field value; null removes the value
         * @return this model
         */
        public DispatchTouchEventRequest timestamp(Input.TimeSinceEpoch timestamp) {
            set("timestamp", timestamp);
            return this;
        }
    }
    /**
     * Emulates touch event from the mouse event parameters.
     * <p><b>Experimental:</b> this part of CDP may change without notice.
     */
    public static final class EmulateTouchFromMouseEventRequest extends CdpObject {
        public EmulateTouchFromMouseEventRequest() {}
        /**
         * Emulates touch event from the mouse event parameters.
         * <p><b>Experimental:</b> this part of CDP may change without notice.
         * @param type protocol value
         * @param x protocol value
         * @param y protocol value
         * @param button protocol value
         */
        public EmulateTouchFromMouseEventRequest(EmulateTouchFromMouseEventTypeValues type, long x, long y, Input.MouseButton button) {
            set("type", type);
            set("x", x);
            set("y", y);
            set("button", button);
        }
        public static EmulateTouchFromMouseEventRequest fromMap(Map<String, Object> values) {
            EmulateTouchFromMouseEventRequest instance_ = new EmulateTouchFromMouseEventRequest();
            if (values != null) instance_.values.putAll(values);
            return instance_;
        }
        /**
         * Type of the mouse event.
         * @return the protocol field value
         */
        public EmulateTouchFromMouseEventTypeValues type() {
            return EmulateTouchFromMouseEventTypeValues.of((String) require("type"));
        }
        /**
         * X coordinate of the mouse pointer in DIP.
         * @return the protocol field value
         */
        public long x() {
            return ((Number) require("x")).longValue();
        }
        /**
         * Y coordinate of the mouse pointer in DIP.
         * @return the protocol field value
         */
        public long y() {
            return ((Number) require("y")).longValue();
        }
        /**
         * Mouse button. Only &quot;none&quot;, &quot;left&quot;, &quot;right&quot; are supported.
         * @return the protocol field value
         */
        public Input.MouseButton button() {
            return Input.MouseButton.of((String) require("button"));
        }
        /**
         * Time at which the event occurred (default: current time).
         * @return the protocol field value, empty when absent
         */
        public Optional<Input.TimeSinceEpoch> timestamp() {
            return Optional.ofNullable(raw("timestamp") == null ? null : new Input.TimeSinceEpoch(((Number) raw("timestamp")).doubleValue()));
        }
        /**
         * X delta in DIP for mouse wheel event (default: 0).
         * @return the protocol field value, empty when absent
         */
        public OptionalDouble deltaX() {
            Double value = CdpObject.numberAsDouble(raw("deltaX"));
            return value == null ? OptionalDouble.empty() : OptionalDouble.of(value);
        }
        /**
         * Y delta in DIP for mouse wheel event (default: 0).
         * @return the protocol field value, empty when absent
         */
        public OptionalDouble deltaY() {
            Double value = CdpObject.numberAsDouble(raw("deltaY"));
            return value == null ? OptionalDouble.empty() : OptionalDouble.of(value);
        }
        /**
         * Bit field representing pressed modifier keys. Alt=1, Ctrl=2, Meta/Command=4, Shift=8 (default: 0).
         * @return the protocol field value, empty when absent
         */
        public OptionalLong modifiers() {
            Long value = CdpObject.numberAsLong(raw("modifiers"));
            return value == null ? OptionalLong.empty() : OptionalLong.of(value);
        }
        /**
         * Number of times the mouse button was clicked (default: 0).
         * @return the protocol field value, empty when absent
         */
        public OptionalLong clickCount() {
            Long value = CdpObject.numberAsLong(raw("clickCount"));
            return value == null ? OptionalLong.empty() : OptionalLong.of(value);
        }
        /**
         * Type of the mouse event.
         * @param type field value
         * @return this model
         */
        public EmulateTouchFromMouseEventRequest type(EmulateTouchFromMouseEventTypeValues type) {
            set("type", type);
            return this;
        }
        /**
         * X coordinate of the mouse pointer in DIP.
         * @param x field value
         * @return this model
         */
        public EmulateTouchFromMouseEventRequest x(long x) {
            set("x", x);
            return this;
        }
        /**
         * Y coordinate of the mouse pointer in DIP.
         * @param y field value
         * @return this model
         */
        public EmulateTouchFromMouseEventRequest y(long y) {
            set("y", y);
            return this;
        }
        /**
         * Mouse button. Only &quot;none&quot;, &quot;left&quot;, &quot;right&quot; are supported.
         * @param button field value
         * @return this model
         */
        public EmulateTouchFromMouseEventRequest button(Input.MouseButton button) {
            set("button", button);
            return this;
        }
        /**
         * Time at which the event occurred (default: current time).
         * @param timestamp field value; empty omits the value
         * @return this model
         */
        public EmulateTouchFromMouseEventRequest timestamp(Optional<Input.TimeSinceEpoch> timestamp) {
            set("timestamp", timestamp.orElse(null));
            return this;
        }
        /**
         * Time at which the event occurred (default: current time).
         * @param timestamp field value; null removes the value
         * @return this model
         */
        public EmulateTouchFromMouseEventRequest timestamp(Input.TimeSinceEpoch timestamp) {
            set("timestamp", timestamp);
            return this;
        }
        /**
         * X delta in DIP for mouse wheel event (default: 0).
         * @param deltaX field value; empty omits the value
         * @return this model
         */
        public EmulateTouchFromMouseEventRequest deltaX(OptionalDouble deltaX) {
            set("deltaX", deltaX.isPresent() ? deltaX.getAsDouble() : null);
            return this;
        }
        /**
         * X delta in DIP for mouse wheel event (default: 0).
         * @param deltaX field value; null removes the value
         * @return this model
         */
        public EmulateTouchFromMouseEventRequest deltaX(Double deltaX) {
            set("deltaX", deltaX);
            return this;
        }
        /**
         * Y delta in DIP for mouse wheel event (default: 0).
         * @param deltaY field value; empty omits the value
         * @return this model
         */
        public EmulateTouchFromMouseEventRequest deltaY(OptionalDouble deltaY) {
            set("deltaY", deltaY.isPresent() ? deltaY.getAsDouble() : null);
            return this;
        }
        /**
         * Y delta in DIP for mouse wheel event (default: 0).
         * @param deltaY field value; null removes the value
         * @return this model
         */
        public EmulateTouchFromMouseEventRequest deltaY(Double deltaY) {
            set("deltaY", deltaY);
            return this;
        }
        /**
         * Bit field representing pressed modifier keys. Alt=1, Ctrl=2, Meta/Command=4, Shift=8 (default: 0).
         * @param modifiers field value; empty omits the value
         * @return this model
         */
        public EmulateTouchFromMouseEventRequest modifiers(OptionalLong modifiers) {
            set("modifiers", modifiers.isPresent() ? modifiers.getAsLong() : null);
            return this;
        }
        /**
         * Bit field representing pressed modifier keys. Alt=1, Ctrl=2, Meta/Command=4, Shift=8 (default: 0).
         * @param modifiers field value; null removes the value
         * @return this model
         */
        public EmulateTouchFromMouseEventRequest modifiers(Long modifiers) {
            set("modifiers", modifiers);
            return this;
        }
        /**
         * Number of times the mouse button was clicked (default: 0).
         * @param clickCount field value; empty omits the value
         * @return this model
         */
        public EmulateTouchFromMouseEventRequest clickCount(OptionalLong clickCount) {
            set("clickCount", clickCount.isPresent() ? clickCount.getAsLong() : null);
            return this;
        }
        /**
         * Number of times the mouse button was clicked (default: 0).
         * @param clickCount field value; null removes the value
         * @return this model
         */
        public EmulateTouchFromMouseEventRequest clickCount(Long clickCount) {
            set("clickCount", clickCount);
            return this;
        }
    }
    /**
     * Ignores input events (useful while auditing page).
     */
    public static final class SetIgnoreInputEventsRequest extends CdpObject {
        public SetIgnoreInputEventsRequest() {}
        /**
         * Ignores input events (useful while auditing page).
         * @param ignore protocol value
         */
        public SetIgnoreInputEventsRequest(boolean ignore) {
            set("ignore", ignore);
        }
        public static SetIgnoreInputEventsRequest fromMap(Map<String, Object> values) {
            SetIgnoreInputEventsRequest instance_ = new SetIgnoreInputEventsRequest();
            if (values != null) instance_.values.putAll(values);
            return instance_;
        }
        /**
         * Ignores input events processing when set to true.
         * @return the protocol field value
         */
        public boolean ignore() {
            return (Boolean) require("ignore");
        }
        /**
         * Ignores input events processing when set to true.
         * @param ignore field value
         * @return this model
         */
        public SetIgnoreInputEventsRequest ignore(boolean ignore) {
            set("ignore", ignore);
            return this;
        }
    }
    /**
     * Prevents default drag and drop behavior and instead emits {@code Input.dragIntercepted} events. Drag and drop behavior can be directly controlled via {@code Input.dispatchDragEvent}.
     * <p><b>Experimental:</b> this part of CDP may change without notice.
     */
    public static final class SetInterceptDragsRequest extends CdpObject {
        public SetInterceptDragsRequest() {}
        /**
         * Prevents default drag and drop behavior and instead emits {@code Input.dragIntercepted} events. Drag and drop behavior can be directly controlled via {@code Input.dispatchDragEvent}.
         * <p><b>Experimental:</b> this part of CDP may change without notice.
         * @param enabled protocol value
         */
        public SetInterceptDragsRequest(boolean enabled) {
            set("enabled", enabled);
        }
        public static SetInterceptDragsRequest fromMap(Map<String, Object> values) {
            SetInterceptDragsRequest instance_ = new SetInterceptDragsRequest();
            if (values != null) instance_.values.putAll(values);
            return instance_;
        }
        /**
         * Returns the enabled field.
         * @return the protocol field value
         */
        public boolean enabled() {
            return (Boolean) require("enabled");
        }
        /**
         * Sets the enabled field.
         * @param enabled field value
         * @return this model
         */
        public SetInterceptDragsRequest enabled(boolean enabled) {
            set("enabled", enabled);
            return this;
        }
    }
    /**
     * Synthesizes a pinch gesture over a time period by issuing appropriate touch events.
     * <p><b>Experimental:</b> this part of CDP may change without notice.
     */
    public static final class SynthesizePinchGestureRequest extends CdpObject {
        public SynthesizePinchGestureRequest() {}
        /**
         * Synthesizes a pinch gesture over a time period by issuing appropriate touch events.
         * <p><b>Experimental:</b> this part of CDP may change without notice.
         * @param x protocol value
         * @param y protocol value
         * @param scaleFactor protocol value
         */
        public SynthesizePinchGestureRequest(double x, double y, double scaleFactor) {
            set("x", x);
            set("y", y);
            set("scaleFactor", scaleFactor);
        }
        public static SynthesizePinchGestureRequest fromMap(Map<String, Object> values) {
            SynthesizePinchGestureRequest instance_ = new SynthesizePinchGestureRequest();
            if (values != null) instance_.values.putAll(values);
            return instance_;
        }
        /**
         * X coordinate of the start of the gesture in CSS pixels.
         * @return the protocol field value
         */
        public double x() {
            return ((Number) require("x")).doubleValue();
        }
        /**
         * Y coordinate of the start of the gesture in CSS pixels.
         * @return the protocol field value
         */
        public double y() {
            return ((Number) require("y")).doubleValue();
        }
        /**
         * Relative scale factor after zooming (&gt;1.0 zooms in, &lt;1.0 zooms out).
         * @return the protocol field value
         */
        public double scaleFactor() {
            return ((Number) require("scaleFactor")).doubleValue();
        }
        /**
         * Relative pointer speed in pixels per second (default: 800).
         * @return the protocol field value, empty when absent
         */
        public OptionalLong relativeSpeed() {
            Long value = CdpObject.numberAsLong(raw("relativeSpeed"));
            return value == null ? OptionalLong.empty() : OptionalLong.of(value);
        }
        /**
         * Which type of input events to be generated (default: &#x27;default&#x27;, which queries the platform for the preferred input type).
         * @return the protocol field value, empty when absent
         */
        public Optional<Input.GestureSourceType> gestureSourceType() {
            return Optional.ofNullable(raw("gestureSourceType") == null ? null : Input.GestureSourceType.of((String) raw("gestureSourceType")));
        }
        /**
         * X coordinate of the start of the gesture in CSS pixels.
         * @param x field value
         * @return this model
         */
        public SynthesizePinchGestureRequest x(double x) {
            set("x", x);
            return this;
        }
        /**
         * Y coordinate of the start of the gesture in CSS pixels.
         * @param y field value
         * @return this model
         */
        public SynthesizePinchGestureRequest y(double y) {
            set("y", y);
            return this;
        }
        /**
         * Relative scale factor after zooming (&gt;1.0 zooms in, &lt;1.0 zooms out).
         * @param scaleFactor field value
         * @return this model
         */
        public SynthesizePinchGestureRequest scaleFactor(double scaleFactor) {
            set("scaleFactor", scaleFactor);
            return this;
        }
        /**
         * Relative pointer speed in pixels per second (default: 800).
         * @param relativeSpeed field value; empty omits the value
         * @return this model
         */
        public SynthesizePinchGestureRequest relativeSpeed(OptionalLong relativeSpeed) {
            set("relativeSpeed", relativeSpeed.isPresent() ? relativeSpeed.getAsLong() : null);
            return this;
        }
        /**
         * Relative pointer speed in pixels per second (default: 800).
         * @param relativeSpeed field value; null removes the value
         * @return this model
         */
        public SynthesizePinchGestureRequest relativeSpeed(Long relativeSpeed) {
            set("relativeSpeed", relativeSpeed);
            return this;
        }
        /**
         * Which type of input events to be generated (default: &#x27;default&#x27;, which queries the platform for the preferred input type).
         * @param gestureSourceType field value; empty omits the value
         * @return this model
         */
        public SynthesizePinchGestureRequest gestureSourceType(Optional<Input.GestureSourceType> gestureSourceType) {
            set("gestureSourceType", gestureSourceType.orElse(null));
            return this;
        }
        /**
         * Which type of input events to be generated (default: &#x27;default&#x27;, which queries the platform for the preferred input type).
         * @param gestureSourceType field value; null removes the value
         * @return this model
         */
        public SynthesizePinchGestureRequest gestureSourceType(Input.GestureSourceType gestureSourceType) {
            set("gestureSourceType", gestureSourceType);
            return this;
        }
    }
    /**
     * Synthesizes a scroll gesture over a time period by issuing appropriate touch events.
     * <p><b>Experimental:</b> this part of CDP may change without notice.
     */
    public static final class SynthesizeScrollGestureRequest extends CdpObject {
        public SynthesizeScrollGestureRequest() {}
        /**
         * Synthesizes a scroll gesture over a time period by issuing appropriate touch events.
         * <p><b>Experimental:</b> this part of CDP may change without notice.
         * @param x protocol value
         * @param y protocol value
         */
        public SynthesizeScrollGestureRequest(double x, double y) {
            set("x", x);
            set("y", y);
        }
        public static SynthesizeScrollGestureRequest fromMap(Map<String, Object> values) {
            SynthesizeScrollGestureRequest instance_ = new SynthesizeScrollGestureRequest();
            if (values != null) instance_.values.putAll(values);
            return instance_;
        }
        /**
         * X coordinate of the start of the gesture in CSS pixels.
         * @return the protocol field value
         */
        public double x() {
            return ((Number) require("x")).doubleValue();
        }
        /**
         * Y coordinate of the start of the gesture in CSS pixels.
         * @return the protocol field value
         */
        public double y() {
            return ((Number) require("y")).doubleValue();
        }
        /**
         * The distance to scroll along the X axis (positive to scroll left).
         * @return the protocol field value, empty when absent
         */
        public OptionalDouble xDistance() {
            Double value = CdpObject.numberAsDouble(raw("xDistance"));
            return value == null ? OptionalDouble.empty() : OptionalDouble.of(value);
        }
        /**
         * The distance to scroll along the Y axis (positive to scroll up).
         * @return the protocol field value, empty when absent
         */
        public OptionalDouble yDistance() {
            Double value = CdpObject.numberAsDouble(raw("yDistance"));
            return value == null ? OptionalDouble.empty() : OptionalDouble.of(value);
        }
        /**
         * The number of additional pixels to scroll back along the X axis, in addition to the given distance.
         * @return the protocol field value, empty when absent
         */
        public OptionalDouble xOverscroll() {
            Double value = CdpObject.numberAsDouble(raw("xOverscroll"));
            return value == null ? OptionalDouble.empty() : OptionalDouble.of(value);
        }
        /**
         * The number of additional pixels to scroll back along the Y axis, in addition to the given distance.
         * @return the protocol field value, empty when absent
         */
        public OptionalDouble yOverscroll() {
            Double value = CdpObject.numberAsDouble(raw("yOverscroll"));
            return value == null ? OptionalDouble.empty() : OptionalDouble.of(value);
        }
        /**
         * Prevent fling (default: true).
         * @return the protocol field value, empty when absent
         */
        public Optional<Boolean> preventFling() {
            return Optional.ofNullable((Boolean) raw("preventFling"));
        }
        /**
         * Swipe speed in pixels per second (default: 800).
         * @return the protocol field value, empty when absent
         */
        public OptionalLong speed() {
            Long value = CdpObject.numberAsLong(raw("speed"));
            return value == null ? OptionalLong.empty() : OptionalLong.of(value);
        }
        /**
         * Which type of input events to be generated (default: &#x27;default&#x27;, which queries the platform for the preferred input type).
         * @return the protocol field value, empty when absent
         */
        public Optional<Input.GestureSourceType> gestureSourceType() {
            return Optional.ofNullable(raw("gestureSourceType") == null ? null : Input.GestureSourceType.of((String) raw("gestureSourceType")));
        }
        /**
         * The number of times to repeat the gesture (default: 0).
         * @return the protocol field value, empty when absent
         */
        public OptionalLong repeatCount() {
            Long value = CdpObject.numberAsLong(raw("repeatCount"));
            return value == null ? OptionalLong.empty() : OptionalLong.of(value);
        }
        /**
         * The number of milliseconds delay between each repeat. (default: 250).
         * @return the protocol field value, empty when absent
         */
        public OptionalLong repeatDelayMs() {
            Long value = CdpObject.numberAsLong(raw("repeatDelayMs"));
            return value == null ? OptionalLong.empty() : OptionalLong.of(value);
        }
        /**
         * The name of the interaction markers to generate, if not empty (default: &quot;&quot;).
         * @return the protocol field value, empty when absent
         */
        public Optional<String> interactionMarkerName() {
            return Optional.ofNullable((String) raw("interactionMarkerName"));
        }
        /**
         * X coordinate of the start of the gesture in CSS pixels.
         * @param x field value
         * @return this model
         */
        public SynthesizeScrollGestureRequest x(double x) {
            set("x", x);
            return this;
        }
        /**
         * Y coordinate of the start of the gesture in CSS pixels.
         * @param y field value
         * @return this model
         */
        public SynthesizeScrollGestureRequest y(double y) {
            set("y", y);
            return this;
        }
        /**
         * The distance to scroll along the X axis (positive to scroll left).
         * @param xDistance field value; empty omits the value
         * @return this model
         */
        public SynthesizeScrollGestureRequest xDistance(OptionalDouble xDistance) {
            set("xDistance", xDistance.isPresent() ? xDistance.getAsDouble() : null);
            return this;
        }
        /**
         * The distance to scroll along the X axis (positive to scroll left).
         * @param xDistance field value; null removes the value
         * @return this model
         */
        public SynthesizeScrollGestureRequest xDistance(Double xDistance) {
            set("xDistance", xDistance);
            return this;
        }
        /**
         * The distance to scroll along the Y axis (positive to scroll up).
         * @param yDistance field value; empty omits the value
         * @return this model
         */
        public SynthesizeScrollGestureRequest yDistance(OptionalDouble yDistance) {
            set("yDistance", yDistance.isPresent() ? yDistance.getAsDouble() : null);
            return this;
        }
        /**
         * The distance to scroll along the Y axis (positive to scroll up).
         * @param yDistance field value; null removes the value
         * @return this model
         */
        public SynthesizeScrollGestureRequest yDistance(Double yDistance) {
            set("yDistance", yDistance);
            return this;
        }
        /**
         * The number of additional pixels to scroll back along the X axis, in addition to the given distance.
         * @param xOverscroll field value; empty omits the value
         * @return this model
         */
        public SynthesizeScrollGestureRequest xOverscroll(OptionalDouble xOverscroll) {
            set("xOverscroll", xOverscroll.isPresent() ? xOverscroll.getAsDouble() : null);
            return this;
        }
        /**
         * The number of additional pixels to scroll back along the X axis, in addition to the given distance.
         * @param xOverscroll field value; null removes the value
         * @return this model
         */
        public SynthesizeScrollGestureRequest xOverscroll(Double xOverscroll) {
            set("xOverscroll", xOverscroll);
            return this;
        }
        /**
         * The number of additional pixels to scroll back along the Y axis, in addition to the given distance.
         * @param yOverscroll field value; empty omits the value
         * @return this model
         */
        public SynthesizeScrollGestureRequest yOverscroll(OptionalDouble yOverscroll) {
            set("yOverscroll", yOverscroll.isPresent() ? yOverscroll.getAsDouble() : null);
            return this;
        }
        /**
         * The number of additional pixels to scroll back along the Y axis, in addition to the given distance.
         * @param yOverscroll field value; null removes the value
         * @return this model
         */
        public SynthesizeScrollGestureRequest yOverscroll(Double yOverscroll) {
            set("yOverscroll", yOverscroll);
            return this;
        }
        /**
         * Prevent fling (default: true).
         * @param preventFling field value; empty omits the value
         * @return this model
         */
        public SynthesizeScrollGestureRequest preventFling(Optional<Boolean> preventFling) {
            set("preventFling", preventFling.orElse(null));
            return this;
        }
        /**
         * Prevent fling (default: true).
         * @param preventFling field value; null removes the value
         * @return this model
         */
        public SynthesizeScrollGestureRequest preventFling(Boolean preventFling) {
            set("preventFling", preventFling);
            return this;
        }
        /**
         * Swipe speed in pixels per second (default: 800).
         * @param speed field value; empty omits the value
         * @return this model
         */
        public SynthesizeScrollGestureRequest speed(OptionalLong speed) {
            set("speed", speed.isPresent() ? speed.getAsLong() : null);
            return this;
        }
        /**
         * Swipe speed in pixels per second (default: 800).
         * @param speed field value; null removes the value
         * @return this model
         */
        public SynthesizeScrollGestureRequest speed(Long speed) {
            set("speed", speed);
            return this;
        }
        /**
         * Which type of input events to be generated (default: &#x27;default&#x27;, which queries the platform for the preferred input type).
         * @param gestureSourceType field value; empty omits the value
         * @return this model
         */
        public SynthesizeScrollGestureRequest gestureSourceType(Optional<Input.GestureSourceType> gestureSourceType) {
            set("gestureSourceType", gestureSourceType.orElse(null));
            return this;
        }
        /**
         * Which type of input events to be generated (default: &#x27;default&#x27;, which queries the platform for the preferred input type).
         * @param gestureSourceType field value; null removes the value
         * @return this model
         */
        public SynthesizeScrollGestureRequest gestureSourceType(Input.GestureSourceType gestureSourceType) {
            set("gestureSourceType", gestureSourceType);
            return this;
        }
        /**
         * The number of times to repeat the gesture (default: 0).
         * @param repeatCount field value; empty omits the value
         * @return this model
         */
        public SynthesizeScrollGestureRequest repeatCount(OptionalLong repeatCount) {
            set("repeatCount", repeatCount.isPresent() ? repeatCount.getAsLong() : null);
            return this;
        }
        /**
         * The number of times to repeat the gesture (default: 0).
         * @param repeatCount field value; null removes the value
         * @return this model
         */
        public SynthesizeScrollGestureRequest repeatCount(Long repeatCount) {
            set("repeatCount", repeatCount);
            return this;
        }
        /**
         * The number of milliseconds delay between each repeat. (default: 250).
         * @param repeatDelayMs field value; empty omits the value
         * @return this model
         */
        public SynthesizeScrollGestureRequest repeatDelayMs(OptionalLong repeatDelayMs) {
            set("repeatDelayMs", repeatDelayMs.isPresent() ? repeatDelayMs.getAsLong() : null);
            return this;
        }
        /**
         * The number of milliseconds delay between each repeat. (default: 250).
         * @param repeatDelayMs field value; null removes the value
         * @return this model
         */
        public SynthesizeScrollGestureRequest repeatDelayMs(Long repeatDelayMs) {
            set("repeatDelayMs", repeatDelayMs);
            return this;
        }
        /**
         * The name of the interaction markers to generate, if not empty (default: &quot;&quot;).
         * @param interactionMarkerName field value; empty omits the value
         * @return this model
         */
        public SynthesizeScrollGestureRequest interactionMarkerName(Optional<String> interactionMarkerName) {
            set("interactionMarkerName", interactionMarkerName.orElse(null));
            return this;
        }
        /**
         * The name of the interaction markers to generate, if not empty (default: &quot;&quot;).
         * @param interactionMarkerName field value; null removes the value
         * @return this model
         */
        public SynthesizeScrollGestureRequest interactionMarkerName(String interactionMarkerName) {
            set("interactionMarkerName", interactionMarkerName);
            return this;
        }
    }
    /**
     * Synthesizes a tap gesture over a time period by issuing appropriate touch events.
     * <p><b>Experimental:</b> this part of CDP may change without notice.
     */
    public static final class SynthesizeTapGestureRequest extends CdpObject {
        public SynthesizeTapGestureRequest() {}
        /**
         * Synthesizes a tap gesture over a time period by issuing appropriate touch events.
         * <p><b>Experimental:</b> this part of CDP may change without notice.
         * @param x protocol value
         * @param y protocol value
         */
        public SynthesizeTapGestureRequest(double x, double y) {
            set("x", x);
            set("y", y);
        }
        public static SynthesizeTapGestureRequest fromMap(Map<String, Object> values) {
            SynthesizeTapGestureRequest instance_ = new SynthesizeTapGestureRequest();
            if (values != null) instance_.values.putAll(values);
            return instance_;
        }
        /**
         * X coordinate of the start of the gesture in CSS pixels.
         * @return the protocol field value
         */
        public double x() {
            return ((Number) require("x")).doubleValue();
        }
        /**
         * Y coordinate of the start of the gesture in CSS pixels.
         * @return the protocol field value
         */
        public double y() {
            return ((Number) require("y")).doubleValue();
        }
        /**
         * Duration between touchdown and touchup events in ms (default: 50).
         * @return the protocol field value, empty when absent
         */
        public OptionalLong duration() {
            Long value = CdpObject.numberAsLong(raw("duration"));
            return value == null ? OptionalLong.empty() : OptionalLong.of(value);
        }
        /**
         * Number of times to perform the tap (e.g. 2 for double tap, default: 1).
         * @return the protocol field value, empty when absent
         */
        public OptionalLong tapCount() {
            Long value = CdpObject.numberAsLong(raw("tapCount"));
            return value == null ? OptionalLong.empty() : OptionalLong.of(value);
        }
        /**
         * Which type of input events to be generated (default: &#x27;default&#x27;, which queries the platform for the preferred input type).
         * @return the protocol field value, empty when absent
         */
        public Optional<Input.GestureSourceType> gestureSourceType() {
            return Optional.ofNullable(raw("gestureSourceType") == null ? null : Input.GestureSourceType.of((String) raw("gestureSourceType")));
        }
        /**
         * X coordinate of the start of the gesture in CSS pixels.
         * @param x field value
         * @return this model
         */
        public SynthesizeTapGestureRequest x(double x) {
            set("x", x);
            return this;
        }
        /**
         * Y coordinate of the start of the gesture in CSS pixels.
         * @param y field value
         * @return this model
         */
        public SynthesizeTapGestureRequest y(double y) {
            set("y", y);
            return this;
        }
        /**
         * Duration between touchdown and touchup events in ms (default: 50).
         * @param duration field value; empty omits the value
         * @return this model
         */
        public SynthesizeTapGestureRequest duration(OptionalLong duration) {
            set("duration", duration.isPresent() ? duration.getAsLong() : null);
            return this;
        }
        /**
         * Duration between touchdown and touchup events in ms (default: 50).
         * @param duration field value; null removes the value
         * @return this model
         */
        public SynthesizeTapGestureRequest duration(Long duration) {
            set("duration", duration);
            return this;
        }
        /**
         * Number of times to perform the tap (e.g. 2 for double tap, default: 1).
         * @param tapCount field value; empty omits the value
         * @return this model
         */
        public SynthesizeTapGestureRequest tapCount(OptionalLong tapCount) {
            set("tapCount", tapCount.isPresent() ? tapCount.getAsLong() : null);
            return this;
        }
        /**
         * Number of times to perform the tap (e.g. 2 for double tap, default: 1).
         * @param tapCount field value; null removes the value
         * @return this model
         */
        public SynthesizeTapGestureRequest tapCount(Long tapCount) {
            set("tapCount", tapCount);
            return this;
        }
        /**
         * Which type of input events to be generated (default: &#x27;default&#x27;, which queries the platform for the preferred input type).
         * @param gestureSourceType field value; empty omits the value
         * @return this model
         */
        public SynthesizeTapGestureRequest gestureSourceType(Optional<Input.GestureSourceType> gestureSourceType) {
            set("gestureSourceType", gestureSourceType.orElse(null));
            return this;
        }
        /**
         * Which type of input events to be generated (default: &#x27;default&#x27;, which queries the platform for the preferred input type).
         * @param gestureSourceType field value; null removes the value
         * @return this model
         */
        public SynthesizeTapGestureRequest gestureSourceType(Input.GestureSourceType gestureSourceType) {
            set("gestureSourceType", gestureSourceType);
            return this;
        }
    }
    /**
     * Emitted only when {@code Input.setInterceptDrags} is enabled. Use this data with {@code Input.dispatchDragEvent} to restore normal drag and drop behavior.
     * <p><b>Experimental:</b> this part of CDP may change without notice.
     */
    public static final class DragInterceptedEvent extends CdpObject {
        public DragInterceptedEvent() {}
        private DragInterceptedEvent(Map<String, Object> values) { super(values); }
        public static DragInterceptedEvent fromMap(Map<String, Object> values) {
            return new DragInterceptedEvent(values);
        }
        /**
         * Returns the data field.
         * @return the protocol field value
         */
        public Input.DragData data() {
            return java.util.Objects.requireNonNull(Input.DragData.fromMap(java.util.Objects.requireNonNull(CdpObject.objectMap(require("data")))));
        }
        /**
         * Sets the data field.
         * @param data field value
         * @return this model
         */
        public DragInterceptedEvent data(Input.DragData data) {
            set("data", data);
            return this;
        }
    }
    /**
     * Type of the drag event.
     */
    public enum DispatchDragEventTypeValues implements CdpValue<String> {
        DRAGENTER("dragEnter"),
        DRAGOVER("dragOver"),
        DROP("drop"),
        DRAGCANCEL("dragCancel");
        public final String value;
        DispatchDragEventTypeValues(String value) { this.value = value; }
        @Nonnull public String value() { return value; }
        public static DispatchDragEventTypeValues of(@Nonnull String value) {
            for (DispatchDragEventTypeValues constant : values()) {
                if (constant.value.equals(value)) return constant;
            }
            throw new IllegalArgumentException("Unknown DispatchDragEventTypeValues value: " + value);
        }
    }
    /**
     * Type of the key event.
     */
    public enum DispatchKeyEventTypeValues implements CdpValue<String> {
        KEYDOWN("keyDown"),
        KEYUP("keyUp"),
        RAWKEYDOWN("rawKeyDown"),
        CHAR("char");
        public final String value;
        DispatchKeyEventTypeValues(String value) { this.value = value; }
        @Nonnull public String value() { return value; }
        public static DispatchKeyEventTypeValues of(@Nonnull String value) {
            for (DispatchKeyEventTypeValues constant : values()) {
                if (constant.value.equals(value)) return constant;
            }
            throw new IllegalArgumentException("Unknown DispatchKeyEventTypeValues value: " + value);
        }
    }
    /**
     * Type of the mouse event.
     */
    public enum DispatchMouseEventTypeValues implements CdpValue<String> {
        MOUSEPRESSED("mousePressed"),
        MOUSERELEASED("mouseReleased"),
        MOUSEMOVED("mouseMoved"),
        MOUSEWHEEL("mouseWheel");
        public final String value;
        DispatchMouseEventTypeValues(String value) { this.value = value; }
        @Nonnull public String value() { return value; }
        public static DispatchMouseEventTypeValues of(@Nonnull String value) {
            for (DispatchMouseEventTypeValues constant : values()) {
                if (constant.value.equals(value)) return constant;
            }
            throw new IllegalArgumentException("Unknown DispatchMouseEventTypeValues value: " + value);
        }
    }
    /**
     * Pointer type (default: &quot;mouse&quot;).
     */
    public enum DispatchMouseEventPointerTypeValues implements CdpValue<String> {
        MOUSE("mouse"),
        PEN("pen");
        public final String value;
        DispatchMouseEventPointerTypeValues(String value) { this.value = value; }
        @Nonnull public String value() { return value; }
        public static DispatchMouseEventPointerTypeValues of(@Nonnull String value) {
            for (DispatchMouseEventPointerTypeValues constant : values()) {
                if (constant.value.equals(value)) return constant;
            }
            throw new IllegalArgumentException("Unknown DispatchMouseEventPointerTypeValues value: " + value);
        }
    }
    /**
     * Type of the touch event. TouchEnd and TouchCancel must not contain any touch points, while TouchStart and TouchMove must contains at least one.
     */
    public enum DispatchTouchEventTypeValues implements CdpValue<String> {
        TOUCHSTART("touchStart"),
        TOUCHEND("touchEnd"),
        TOUCHMOVE("touchMove"),
        TOUCHCANCEL("touchCancel");
        public final String value;
        DispatchTouchEventTypeValues(String value) { this.value = value; }
        @Nonnull public String value() { return value; }
        public static DispatchTouchEventTypeValues of(@Nonnull String value) {
            for (DispatchTouchEventTypeValues constant : values()) {
                if (constant.value.equals(value)) return constant;
            }
            throw new IllegalArgumentException("Unknown DispatchTouchEventTypeValues value: " + value);
        }
    }
    /**
     * Type of the mouse event.
     */
    public enum EmulateTouchFromMouseEventTypeValues implements CdpValue<String> {
        MOUSEPRESSED("mousePressed"),
        MOUSERELEASED("mouseReleased"),
        MOUSEMOVED("mouseMoved"),
        MOUSEWHEEL("mouseWheel");
        public final String value;
        EmulateTouchFromMouseEventTypeValues(String value) { this.value = value; }
        @Nonnull public String value() { return value; }
        public static EmulateTouchFromMouseEventTypeValues of(@Nonnull String value) {
            for (EmulateTouchFromMouseEventTypeValues constant : values()) {
                if (constant.value.equals(value)) return constant;
            }
            throw new IllegalArgumentException("Unknown EmulateTouchFromMouseEventTypeValues value: " + value);
        }
    }
    public static final class Client {
        private final CdpClient client;
        public Client(CdpClient client) { this.client = client; }
        /**
         * Dispatches a drag event into the page.
         * <p><b>Experimental:</b> this part of CDP may change without notice.
         * @param type protocol value
         * @param x protocol value
         * @param y protocol value
         * @param data protocol value
         * @param modifiers protocol value
         * @return a stage completing when the command completes
         */
        public CompletionStage<Void> dispatchDragEvent(DispatchDragEventTypeValues type, double x, double y, Input.DragData data, OptionalLong modifiers) {
            Map<String, Object> params = new LinkedHashMap<>();
            params.put("type", CdpObject.json(type));
            params.put("x", CdpObject.json(x));
            params.put("y", CdpObject.json(y));
            params.put("data", CdpObject.json(data));
            modifiers.ifPresent(value_ -> params.put("modifiers", value_));
            return client.call("Input.dispatchDragEvent", params, result_ -> null);
        }
        /**
         * Dispatches a drag event into the page.
         * <p><b>Experimental:</b> this part of CDP may change without notice.
         * @param type protocol value
         * @param x protocol value
         * @param y protocol value
         * @param data protocol value
         * @return a stage completing when the command completes
         */
        public CompletionStage<Void> dispatchDragEvent(DispatchDragEventTypeValues type, double x, double y, Input.DragData data) {
            return dispatchDragEvent(type, x, y, data, OptionalLong.empty());
        }
        /**
         * Dispatches a drag event into the page.
         * <p><b>Experimental:</b> this part of CDP may change without notice.
         * @param request request parameters
         * @return a stage completing when the command completes
         */
        public CompletionStage<Void> dispatchDragEvent(DispatchDragEventRequest request) {
            return client.call("Input.dispatchDragEvent", request == null ? null : request.toMap(), result_ -> null);
        }
        /**
         * Dispatches a key event to the page.
         * @param type protocol value
         * @param modifiers protocol value
         * @param timestamp protocol value
         * @param text protocol value
         * @param unmodifiedText protocol value
         * @param keyIdentifier protocol value
         * @param code protocol value
         * @param key protocol value
         * @param windowsVirtualKeyCode protocol value
         * @param nativeVirtualKeyCode protocol value
         * @param autoRepeat protocol value
         * @param isKeypad protocol value
         * @param isSystemKey protocol value
         * @param location protocol value
         * @param commands protocol value
         * @return a stage completing when the command completes
         */
        public CompletionStage<Void> dispatchKeyEvent(DispatchKeyEventTypeValues type, OptionalLong modifiers, Optional<Input.TimeSinceEpoch> timestamp, Optional<String> text, Optional<String> unmodifiedText, Optional<String> keyIdentifier, Optional<String> code, Optional<String> key, OptionalLong windowsVirtualKeyCode, OptionalLong nativeVirtualKeyCode, Optional<Boolean> autoRepeat, Optional<Boolean> isKeypad, Optional<Boolean> isSystemKey, OptionalLong location, Optional<java.util.List<String>> commands) {
            Map<String, Object> params = new LinkedHashMap<>();
            params.put("type", CdpObject.json(type));
            modifiers.ifPresent(value_ -> params.put("modifiers", value_));
            timestamp.ifPresent(value_ -> params.put("timestamp", CdpObject.json(value_)));
            text.ifPresent(value_ -> params.put("text", CdpObject.json(value_)));
            unmodifiedText.ifPresent(value_ -> params.put("unmodifiedText", CdpObject.json(value_)));
            keyIdentifier.ifPresent(value_ -> params.put("keyIdentifier", CdpObject.json(value_)));
            code.ifPresent(value_ -> params.put("code", CdpObject.json(value_)));
            key.ifPresent(value_ -> params.put("key", CdpObject.json(value_)));
            windowsVirtualKeyCode.ifPresent(value_ -> params.put("windowsVirtualKeyCode", value_));
            nativeVirtualKeyCode.ifPresent(value_ -> params.put("nativeVirtualKeyCode", value_));
            autoRepeat.ifPresent(value_ -> params.put("autoRepeat", value_));
            isKeypad.ifPresent(value_ -> params.put("isKeypad", value_));
            isSystemKey.ifPresent(value_ -> params.put("isSystemKey", value_));
            location.ifPresent(value_ -> params.put("location", value_));
            commands.ifPresent(value_ -> params.put("commands", CdpObject.json(value_)));
            return client.call("Input.dispatchKeyEvent", params, result_ -> null);
        }
        /**
         * Dispatches a key event to the page.
         * @param type protocol value
         * @return a stage completing when the command completes
         */
        public CompletionStage<Void> dispatchKeyEvent(DispatchKeyEventTypeValues type) {
            return dispatchKeyEvent(type, OptionalLong.empty(), Optional.empty(), Optional.empty(), Optional.empty(), Optional.empty(), Optional.empty(), Optional.empty(), OptionalLong.empty(), OptionalLong.empty(), Optional.empty(), Optional.empty(), Optional.empty(), OptionalLong.empty(), Optional.empty());
        }
        /**
         * Dispatches a key event to the page.
         * @param request request parameters
         * @return a stage completing when the command completes
         */
        public CompletionStage<Void> dispatchKeyEvent(DispatchKeyEventRequest request) {
            return client.call("Input.dispatchKeyEvent", request == null ? null : request.toMap(), result_ -> null);
        }
        /**
         * This method emulates inserting text that doesn&#x27;t come from a key press, for example an emoji keyboard or an IME.
         * <p><b>Experimental:</b> this part of CDP may change without notice.
         * @param text protocol value
         * @return a stage completing when the command completes
         */
        public CompletionStage<Void> insertText(String text) {
            Map<String, Object> params = new LinkedHashMap<>();
            params.put("text", CdpObject.json(text));
            return client.call("Input.insertText", params, result_ -> null);
        }
        /**
         * This method emulates inserting text that doesn&#x27;t come from a key press, for example an emoji keyboard or an IME.
         * <p><b>Experimental:</b> this part of CDP may change without notice.
         * @param request request parameters
         * @return a stage completing when the command completes
         */
        public CompletionStage<Void> insertText(InsertTextRequest request) {
            return client.call("Input.insertText", request == null ? null : request.toMap(), result_ -> null);
        }
        /**
         * This method sets the current candidate text for IME. Use imeCommitComposition to commit the final text. Use imeSetComposition with empty string as text to cancel composition.
         * <p><b>Experimental:</b> this part of CDP may change without notice.
         * @param text protocol value
         * @param selectionStart protocol value
         * @param selectionEnd protocol value
         * @param replacementStart protocol value
         * @param replacementEnd protocol value
         * @return a stage completing when the command completes
         */
        public CompletionStage<Void> imeSetComposition(String text, long selectionStart, long selectionEnd, OptionalLong replacementStart, OptionalLong replacementEnd) {
            Map<String, Object> params = new LinkedHashMap<>();
            params.put("text", CdpObject.json(text));
            params.put("selectionStart", CdpObject.json(selectionStart));
            params.put("selectionEnd", CdpObject.json(selectionEnd));
            replacementStart.ifPresent(value_ -> params.put("replacementStart", value_));
            replacementEnd.ifPresent(value_ -> params.put("replacementEnd", value_));
            return client.call("Input.imeSetComposition", params, result_ -> null);
        }
        /**
         * This method sets the current candidate text for IME. Use imeCommitComposition to commit the final text. Use imeSetComposition with empty string as text to cancel composition.
         * <p><b>Experimental:</b> this part of CDP may change without notice.
         * @param text protocol value
         * @param selectionStart protocol value
         * @param selectionEnd protocol value
         * @return a stage completing when the command completes
         */
        public CompletionStage<Void> imeSetComposition(String text, long selectionStart, long selectionEnd) {
            return imeSetComposition(text, selectionStart, selectionEnd, OptionalLong.empty(), OptionalLong.empty());
        }
        /**
         * This method sets the current candidate text for IME. Use imeCommitComposition to commit the final text. Use imeSetComposition with empty string as text to cancel composition.
         * <p><b>Experimental:</b> this part of CDP may change without notice.
         * @param request request parameters
         * @return a stage completing when the command completes
         */
        public CompletionStage<Void> imeSetComposition(ImeSetCompositionRequest request) {
            return client.call("Input.imeSetComposition", request == null ? null : request.toMap(), result_ -> null);
        }
        /**
         * Dispatches a mouse event to the page.
         * @param type protocol value
         * @param x protocol value
         * @param y protocol value
         * @param modifiers protocol value
         * @param timestamp protocol value
         * @param button protocol value
         * @param buttons protocol value
         * @param clickCount protocol value
         * @param force protocol value
         * @param tangentialPressure protocol value
         * @param tiltX protocol value
         * @param tiltY protocol value
         * @param twist protocol value
         * @param deltaX protocol value
         * @param deltaY protocol value
         * @param pointerType protocol value
         * @return a stage completing when the command completes
         */
        public CompletionStage<Void> dispatchMouseEvent(DispatchMouseEventTypeValues type, double x, double y, OptionalLong modifiers, Optional<Input.TimeSinceEpoch> timestamp, Optional<Input.MouseButton> button, OptionalLong buttons, OptionalLong clickCount, OptionalDouble force, OptionalDouble tangentialPressure, OptionalDouble tiltX, OptionalDouble tiltY, OptionalLong twist, OptionalDouble deltaX, OptionalDouble deltaY, Optional<DispatchMouseEventPointerTypeValues> pointerType) {
            Map<String, Object> params = new LinkedHashMap<>();
            params.put("type", CdpObject.json(type));
            params.put("x", CdpObject.json(x));
            params.put("y", CdpObject.json(y));
            modifiers.ifPresent(value_ -> params.put("modifiers", value_));
            timestamp.ifPresent(value_ -> params.put("timestamp", CdpObject.json(value_)));
            button.ifPresent(value_ -> params.put("button", CdpObject.json(value_)));
            buttons.ifPresent(value_ -> params.put("buttons", value_));
            clickCount.ifPresent(value_ -> params.put("clickCount", value_));
            force.ifPresent(value_ -> params.put("force", value_));
            tangentialPressure.ifPresent(value_ -> params.put("tangentialPressure", value_));
            tiltX.ifPresent(value_ -> params.put("tiltX", value_));
            tiltY.ifPresent(value_ -> params.put("tiltY", value_));
            twist.ifPresent(value_ -> params.put("twist", value_));
            deltaX.ifPresent(value_ -> params.put("deltaX", value_));
            deltaY.ifPresent(value_ -> params.put("deltaY", value_));
            pointerType.ifPresent(value_ -> params.put("pointerType", CdpObject.json(value_)));
            return client.call("Input.dispatchMouseEvent", params, result_ -> null);
        }
        /**
         * Dispatches a mouse event to the page.
         * @param type protocol value
         * @param x protocol value
         * @param y protocol value
         * @return a stage completing when the command completes
         */
        public CompletionStage<Void> dispatchMouseEvent(DispatchMouseEventTypeValues type, double x, double y) {
            return dispatchMouseEvent(type, x, y, OptionalLong.empty(), Optional.empty(), Optional.empty(), OptionalLong.empty(), OptionalLong.empty(), OptionalDouble.empty(), OptionalDouble.empty(), OptionalDouble.empty(), OptionalDouble.empty(), OptionalLong.empty(), OptionalDouble.empty(), OptionalDouble.empty(), Optional.empty());
        }
        /**
         * Dispatches a mouse event to the page.
         * @param request request parameters
         * @return a stage completing when the command completes
         */
        public CompletionStage<Void> dispatchMouseEvent(DispatchMouseEventRequest request) {
            return client.call("Input.dispatchMouseEvent", request == null ? null : request.toMap(), result_ -> null);
        }
        /**
         * Dispatches a touch event to the page.
         * @param type protocol value
         * @param touchPoints protocol value
         * @param modifiers protocol value
         * @param timestamp protocol value
         * @return a stage completing when the command completes
         */
        public CompletionStage<Void> dispatchTouchEvent(DispatchTouchEventTypeValues type, java.util.List<Input.TouchPoint> touchPoints, OptionalLong modifiers, Optional<Input.TimeSinceEpoch> timestamp) {
            Map<String, Object> params = new LinkedHashMap<>();
            params.put("type", CdpObject.json(type));
            params.put("touchPoints", CdpObject.json(touchPoints));
            modifiers.ifPresent(value_ -> params.put("modifiers", value_));
            timestamp.ifPresent(value_ -> params.put("timestamp", CdpObject.json(value_)));
            return client.call("Input.dispatchTouchEvent", params, result_ -> null);
        }
        /**
         * Dispatches a touch event to the page.
         * @param type protocol value
         * @param touchPoints protocol value
         * @return a stage completing when the command completes
         */
        public CompletionStage<Void> dispatchTouchEvent(DispatchTouchEventTypeValues type, java.util.List<Input.TouchPoint> touchPoints) {
            return dispatchTouchEvent(type, touchPoints, OptionalLong.empty(), Optional.empty());
        }
        /**
         * Dispatches a touch event to the page.
         * @param request request parameters
         * @return a stage completing when the command completes
         */
        public CompletionStage<Void> dispatchTouchEvent(DispatchTouchEventRequest request) {
            return client.call("Input.dispatchTouchEvent", request == null ? null : request.toMap(), result_ -> null);
        }
        /**
         * Cancels any active dragging in the page.
         * @return a stage completing when the command completes
         */
        public CompletionStage<Void> cancelDragging() {
            return client.call("Input.cancelDragging", null, result_ -> null);
        }
        /**
         * Emulates touch event from the mouse event parameters.
         * <p><b>Experimental:</b> this part of CDP may change without notice.
         * @param type protocol value
         * @param x protocol value
         * @param y protocol value
         * @param button protocol value
         * @param timestamp protocol value
         * @param deltaX protocol value
         * @param deltaY protocol value
         * @param modifiers protocol value
         * @param clickCount protocol value
         * @return a stage completing when the command completes
         */
        public CompletionStage<Void> emulateTouchFromMouseEvent(EmulateTouchFromMouseEventTypeValues type, long x, long y, Input.MouseButton button, Optional<Input.TimeSinceEpoch> timestamp, OptionalDouble deltaX, OptionalDouble deltaY, OptionalLong modifiers, OptionalLong clickCount) {
            Map<String, Object> params = new LinkedHashMap<>();
            params.put("type", CdpObject.json(type));
            params.put("x", CdpObject.json(x));
            params.put("y", CdpObject.json(y));
            params.put("button", CdpObject.json(button));
            timestamp.ifPresent(value_ -> params.put("timestamp", CdpObject.json(value_)));
            deltaX.ifPresent(value_ -> params.put("deltaX", value_));
            deltaY.ifPresent(value_ -> params.put("deltaY", value_));
            modifiers.ifPresent(value_ -> params.put("modifiers", value_));
            clickCount.ifPresent(value_ -> params.put("clickCount", value_));
            return client.call("Input.emulateTouchFromMouseEvent", params, result_ -> null);
        }
        /**
         * Emulates touch event from the mouse event parameters.
         * <p><b>Experimental:</b> this part of CDP may change without notice.
         * @param type protocol value
         * @param x protocol value
         * @param y protocol value
         * @param button protocol value
         * @return a stage completing when the command completes
         */
        public CompletionStage<Void> emulateTouchFromMouseEvent(EmulateTouchFromMouseEventTypeValues type, long x, long y, Input.MouseButton button) {
            return emulateTouchFromMouseEvent(type, x, y, button, Optional.empty(), OptionalDouble.empty(), OptionalDouble.empty(), OptionalLong.empty(), OptionalLong.empty());
        }
        /**
         * Emulates touch event from the mouse event parameters.
         * <p><b>Experimental:</b> this part of CDP may change without notice.
         * @param request request parameters
         * @return a stage completing when the command completes
         */
        public CompletionStage<Void> emulateTouchFromMouseEvent(EmulateTouchFromMouseEventRequest request) {
            return client.call("Input.emulateTouchFromMouseEvent", request == null ? null : request.toMap(), result_ -> null);
        }
        /**
         * Ignores input events (useful while auditing page).
         * @param ignore protocol value
         * @return a stage completing when the command completes
         */
        public CompletionStage<Void> setIgnoreInputEvents(boolean ignore) {
            Map<String, Object> params = new LinkedHashMap<>();
            params.put("ignore", CdpObject.json(ignore));
            return client.call("Input.setIgnoreInputEvents", params, result_ -> null);
        }
        /**
         * Ignores input events (useful while auditing page).
         * @param request request parameters
         * @return a stage completing when the command completes
         */
        public CompletionStage<Void> setIgnoreInputEvents(SetIgnoreInputEventsRequest request) {
            return client.call("Input.setIgnoreInputEvents", request == null ? null : request.toMap(), result_ -> null);
        }
        /**
         * Prevents default drag and drop behavior and instead emits {@code Input.dragIntercepted} events. Drag and drop behavior can be directly controlled via {@code Input.dispatchDragEvent}.
         * <p><b>Experimental:</b> this part of CDP may change without notice.
         * @param enabled protocol value
         * @return a stage completing when the command completes
         */
        public CompletionStage<Void> setInterceptDrags(boolean enabled) {
            Map<String, Object> params = new LinkedHashMap<>();
            params.put("enabled", CdpObject.json(enabled));
            return client.call("Input.setInterceptDrags", params, result_ -> null);
        }
        /**
         * Prevents default drag and drop behavior and instead emits {@code Input.dragIntercepted} events. Drag and drop behavior can be directly controlled via {@code Input.dispatchDragEvent}.
         * <p><b>Experimental:</b> this part of CDP may change without notice.
         * @param request request parameters
         * @return a stage completing when the command completes
         */
        public CompletionStage<Void> setInterceptDrags(SetInterceptDragsRequest request) {
            return client.call("Input.setInterceptDrags", request == null ? null : request.toMap(), result_ -> null);
        }
        /**
         * Synthesizes a pinch gesture over a time period by issuing appropriate touch events.
         * <p><b>Experimental:</b> this part of CDP may change without notice.
         * @param x protocol value
         * @param y protocol value
         * @param scaleFactor protocol value
         * @param relativeSpeed protocol value
         * @param gestureSourceType protocol value
         * @return a stage completing when the command completes
         */
        public CompletionStage<Void> synthesizePinchGesture(double x, double y, double scaleFactor, OptionalLong relativeSpeed, Optional<Input.GestureSourceType> gestureSourceType) {
            Map<String, Object> params = new LinkedHashMap<>();
            params.put("x", CdpObject.json(x));
            params.put("y", CdpObject.json(y));
            params.put("scaleFactor", CdpObject.json(scaleFactor));
            relativeSpeed.ifPresent(value_ -> params.put("relativeSpeed", value_));
            gestureSourceType.ifPresent(value_ -> params.put("gestureSourceType", CdpObject.json(value_)));
            return client.call("Input.synthesizePinchGesture", params, result_ -> null);
        }
        /**
         * Synthesizes a pinch gesture over a time period by issuing appropriate touch events.
         * <p><b>Experimental:</b> this part of CDP may change without notice.
         * @param x protocol value
         * @param y protocol value
         * @param scaleFactor protocol value
         * @return a stage completing when the command completes
         */
        public CompletionStage<Void> synthesizePinchGesture(double x, double y, double scaleFactor) {
            return synthesizePinchGesture(x, y, scaleFactor, OptionalLong.empty(), Optional.empty());
        }
        /**
         * Synthesizes a pinch gesture over a time period by issuing appropriate touch events.
         * <p><b>Experimental:</b> this part of CDP may change without notice.
         * @param request request parameters
         * @return a stage completing when the command completes
         */
        public CompletionStage<Void> synthesizePinchGesture(SynthesizePinchGestureRequest request) {
            return client.call("Input.synthesizePinchGesture", request == null ? null : request.toMap(), result_ -> null);
        }
        /**
         * Synthesizes a scroll gesture over a time period by issuing appropriate touch events.
         * <p><b>Experimental:</b> this part of CDP may change without notice.
         * @param x protocol value
         * @param y protocol value
         * @param xDistance protocol value
         * @param yDistance protocol value
         * @param xOverscroll protocol value
         * @param yOverscroll protocol value
         * @param preventFling protocol value
         * @param speed protocol value
         * @param gestureSourceType protocol value
         * @param repeatCount protocol value
         * @param repeatDelayMs protocol value
         * @param interactionMarkerName protocol value
         * @return a stage completing when the command completes
         */
        public CompletionStage<Void> synthesizeScrollGesture(double x, double y, OptionalDouble xDistance, OptionalDouble yDistance, OptionalDouble xOverscroll, OptionalDouble yOverscroll, Optional<Boolean> preventFling, OptionalLong speed, Optional<Input.GestureSourceType> gestureSourceType, OptionalLong repeatCount, OptionalLong repeatDelayMs, Optional<String> interactionMarkerName) {
            Map<String, Object> params = new LinkedHashMap<>();
            params.put("x", CdpObject.json(x));
            params.put("y", CdpObject.json(y));
            xDistance.ifPresent(value_ -> params.put("xDistance", value_));
            yDistance.ifPresent(value_ -> params.put("yDistance", value_));
            xOverscroll.ifPresent(value_ -> params.put("xOverscroll", value_));
            yOverscroll.ifPresent(value_ -> params.put("yOverscroll", value_));
            preventFling.ifPresent(value_ -> params.put("preventFling", value_));
            speed.ifPresent(value_ -> params.put("speed", value_));
            gestureSourceType.ifPresent(value_ -> params.put("gestureSourceType", CdpObject.json(value_)));
            repeatCount.ifPresent(value_ -> params.put("repeatCount", value_));
            repeatDelayMs.ifPresent(value_ -> params.put("repeatDelayMs", value_));
            interactionMarkerName.ifPresent(value_ -> params.put("interactionMarkerName", CdpObject.json(value_)));
            return client.call("Input.synthesizeScrollGesture", params, result_ -> null);
        }
        /**
         * Synthesizes a scroll gesture over a time period by issuing appropriate touch events.
         * <p><b>Experimental:</b> this part of CDP may change without notice.
         * @param x protocol value
         * @param y protocol value
         * @return a stage completing when the command completes
         */
        public CompletionStage<Void> synthesizeScrollGesture(double x, double y) {
            return synthesizeScrollGesture(x, y, OptionalDouble.empty(), OptionalDouble.empty(), OptionalDouble.empty(), OptionalDouble.empty(), Optional.empty(), OptionalLong.empty(), Optional.empty(), OptionalLong.empty(), OptionalLong.empty(), Optional.empty());
        }
        /**
         * Synthesizes a scroll gesture over a time period by issuing appropriate touch events.
         * <p><b>Experimental:</b> this part of CDP may change without notice.
         * @param request request parameters
         * @return a stage completing when the command completes
         */
        public CompletionStage<Void> synthesizeScrollGesture(SynthesizeScrollGestureRequest request) {
            return client.call("Input.synthesizeScrollGesture", request == null ? null : request.toMap(), result_ -> null);
        }
        /**
         * Synthesizes a tap gesture over a time period by issuing appropriate touch events.
         * <p><b>Experimental:</b> this part of CDP may change without notice.
         * @param x protocol value
         * @param y protocol value
         * @param duration protocol value
         * @param tapCount protocol value
         * @param gestureSourceType protocol value
         * @return a stage completing when the command completes
         */
        public CompletionStage<Void> synthesizeTapGesture(double x, double y, OptionalLong duration, OptionalLong tapCount, Optional<Input.GestureSourceType> gestureSourceType) {
            Map<String, Object> params = new LinkedHashMap<>();
            params.put("x", CdpObject.json(x));
            params.put("y", CdpObject.json(y));
            duration.ifPresent(value_ -> params.put("duration", value_));
            tapCount.ifPresent(value_ -> params.put("tapCount", value_));
            gestureSourceType.ifPresent(value_ -> params.put("gestureSourceType", CdpObject.json(value_)));
            return client.call("Input.synthesizeTapGesture", params, result_ -> null);
        }
        /**
         * Synthesizes a tap gesture over a time period by issuing appropriate touch events.
         * <p><b>Experimental:</b> this part of CDP may change without notice.
         * @param x protocol value
         * @param y protocol value
         * @return a stage completing when the command completes
         */
        public CompletionStage<Void> synthesizeTapGesture(double x, double y) {
            return synthesizeTapGesture(x, y, OptionalLong.empty(), OptionalLong.empty(), Optional.empty());
        }
        /**
         * Synthesizes a tap gesture over a time period by issuing appropriate touch events.
         * <p><b>Experimental:</b> this part of CDP may change without notice.
         * @param request request parameters
         * @return a stage completing when the command completes
         */
        public CompletionStage<Void> synthesizeTapGesture(SynthesizeTapGestureRequest request) {
            return client.call("Input.synthesizeTapGesture", request == null ? null : request.toMap(), result_ -> null);
        }
        /**
         * Emitted only when {@code Input.setInterceptDrags} is enabled. Use this data with {@code Input.dispatchDragEvent} to restore normal drag and drop behavior.
         * <p><b>Experimental:</b> this part of CDP may change without notice.
         * @param handler event callback
         * @return a removable subscription
         */
        public CdpSubscription onDragIntercepted(Consumer<DragInterceptedEvent> handler) {
            return client.on("Input.dragIntercepted", DragInterceptedEvent::fromMap, handler);
        }
    }
}
