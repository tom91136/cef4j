// GENERATED - do not edit. Regenerate via: ./mvnw generate-sources -pl cef4j-cdp -Dcef.version=150.0.18+gdb11278+chromium-150.0.7871.213
package net.kurobako.cef4j.cdp.generated;

import java.util.Map;
import java.util.Optional;
import java.util.OptionalDouble;
import java.util.OptionalLong;
import java.util.concurrent.CompletionStage;
import java.util.function.Consumer;
import javax.annotation.processing.Generated;
import net.kurobako.cef4j.cdp.CdpClient;
import net.kurobako.cef4j.cdp.CdpDomainClient;
import net.kurobako.cef4j.cdp.CdpField;
import net.kurobako.cef4j.cdp.CdpObject;
import net.kurobako.cef4j.cdp.CdpSubscription;
import net.kurobako.cef4j.cdp.CdpValue;
import net.kurobako.cef4j.policy.NullableBoundary;
import org.jspecify.annotations.Nullable;

/**
 * Chrome DevTools Protocol Input domain.
 * @see <a href="https://chromium.googlesource.com/chromium/src/+/refs/tags/150.0.7871.213/third_party/blink/public/devtools_protocol/domains/Input.pdl">Pinned protocol source</a>
 */
@Generated("./mvnw generate-sources -pl cef4j-cdp -Dcef.version=150.0.18+gdb11278+chromium-150.0.7871.213")
@SuppressWarnings({"EscapedEntity", "InvalidParam", "JavaLangClash", "MissingSummary", "Unchecked", "UnusedMethod"})
@NullableBoundary("CDP wire objects use null for absent optional fields")
public final class Input {
    private Input() {}
    private static final CdpField<Input.MouseButton> BUTTON = CdpField.of("button", value -> Input.MouseButton.of((String) value));
    private static final CdpField<java.util.List<String>> COMMANDS = CdpField.of("commands", value -> CdpObject.requireList(value, element0 -> (String) element0));
    private static final CdpField<Input.DragData> DATA = CdpField.of("data", value -> java.util.Objects.requireNonNull(Input.DragData.fromMap(CdpObject.requireObjectMap(value))));
    private static final CdpField<java.util.List<String>> FILES = CdpField.of("files", value -> CdpObject.requireList(value, element0 -> (String) element0));
    private static final CdpField<Input.GestureSourceType> GESTURE_SOURCE_TYPE = CdpField.of("gestureSourceType", value -> Input.GestureSourceType.of((String) value));
    private static final CdpField<java.util.List<Input.DragDataItem>> ITEMS = CdpField.of("items", value -> CdpObject.requireList(value, element0 -> java.util.Objects.requireNonNull(Input.DragDataItem.fromMap(CdpObject.requireObjectMap(element0)))));
    private static final CdpField<DispatchMouseEventPointerTypeValues> POINTER_TYPE = CdpField.of("pointerType", value -> DispatchMouseEventPointerTypeValues.of((String) value));
    private static final CdpField<Input.TimeSinceEpoch> TIMESTAMP = CdpField.of("timestamp", value -> new Input.TimeSinceEpoch(((Number) value).doubleValue()));
    private static final CdpField<java.util.List<Input.TouchPoint>> TOUCH_POINTS = CdpField.of("touchPoints", value -> CdpObject.requireList(value, element0 -> java.util.Objects.requireNonNull(Input.TouchPoint.fromMap(CdpObject.requireObjectMap(element0)))));
    private static final CdpField<DispatchDragEventTypeValues> TYPE = CdpField.of("type", value -> DispatchDragEventTypeValues.of((String) value));
    private static final CdpField<DispatchKeyEventTypeValues> TYPE_2 = CdpField.of("type", value -> DispatchKeyEventTypeValues.of((String) value));
    private static final CdpField<DispatchMouseEventTypeValues> TYPE_3 = CdpField.of("type", value -> DispatchMouseEventTypeValues.of((String) value));
    private static final CdpField<DispatchTouchEventTypeValues> TYPE_4 = CdpField.of("type", value -> DispatchTouchEventTypeValues.of((String) value));
    private static final CdpField<EmulateTouchFromMouseEventTypeValues> TYPE_5 = CdpField.of("type", value -> EmulateTouchFromMouseEventTypeValues.of((String) value));
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
            return requireDouble("x");
        }
        /**
         * Y coordinate of the event relative to the main frame&#x27;s viewport in CSS pixels. 0 refers to the top of the viewport and Y increases as it proceeds towards the bottom of the viewport.
         * @return the protocol field value
         */
        public double y() {
            return requireDouble("y");
        }
        /**
         * X radius of the touch area (default: 1.0).
         * @return the protocol field value, empty when absent
         */
        public OptionalDouble radiusX() {
            return optionalDouble("radiusX");
        }
        /**
         * Y radius of the touch area (default: 1.0).
         * @return the protocol field value, empty when absent
         */
        public OptionalDouble radiusY() {
            return optionalDouble("radiusY");
        }
        /**
         * Rotation angle (default: 0.0).
         * @return the protocol field value, empty when absent
         */
        public OptionalDouble rotationAngle() {
            return optionalDouble("rotationAngle");
        }
        /**
         * Force (default: 1.0).
         * @return the protocol field value, empty when absent
         */
        public OptionalDouble force() {
            return optionalDouble("force");
        }
        /**
         * The normalized tangential pressure, which has a range of [-1,1] (default: 0).
         * <p><b>Experimental:</b> this part of CDP may change without notice.
         * @return the protocol field value, empty when absent
         */
        public OptionalDouble tangentialPressure() {
            return optionalDouble("tangentialPressure");
        }
        /**
         * The plane angle between the Y-Z plane and the plane containing both the stylus axis and the Y axis, in degrees of the range [-90,90], a positive tiltX is to the right (default: 0)
         * @return the protocol field value, empty when absent
         */
        public OptionalDouble tiltX() {
            return optionalDouble("tiltX");
        }
        /**
         * The plane angle between the X-Z plane and the plane containing both the stylus axis and the X axis, in degrees of the range [-90,90], a positive tiltY is towards the user (default: 0).
         * @return the protocol field value, empty when absent
         */
        public OptionalDouble tiltY() {
            return optionalDouble("tiltY");
        }
        /**
         * The clockwise rotation of a pen stylus around its own major axis, in degrees in the range [0,359] (default: 0).
         * <p><b>Experimental:</b> this part of CDP may change without notice.
         * @return the protocol field value, empty when absent
         */
        public OptionalLong twist() {
            return optionalLong("twist");
        }
        /**
         * Identifier used to track touch sources between events, must be unique within an event.
         * @return the protocol field value, empty when absent
         */
        public OptionalDouble id() {
            return optionalDouble("id");
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
         * @param radiusX field value
         * @return this model
         */
        public TouchPoint radiusX(double radiusX) {
            set("radiusX", radiusX);
            return this;
        }
        /**
         * Y radius of the touch area (default: 1.0).
         * @param radiusY field value
         * @return this model
         */
        public TouchPoint radiusY(double radiusY) {
            set("radiusY", radiusY);
            return this;
        }
        /**
         * Rotation angle (default: 0.0).
         * @param rotationAngle field value
         * @return this model
         */
        public TouchPoint rotationAngle(double rotationAngle) {
            set("rotationAngle", rotationAngle);
            return this;
        }
        /**
         * Force (default: 1.0).
         * @param force field value
         * @return this model
         */
        public TouchPoint force(double force) {
            set("force", force);
            return this;
        }
        /**
         * The normalized tangential pressure, which has a range of [-1,1] (default: 0).
         * <p><b>Experimental:</b> this part of CDP may change without notice.
         * @param tangentialPressure field value
         * @return this model
         */
        public TouchPoint tangentialPressure(double tangentialPressure) {
            set("tangentialPressure", tangentialPressure);
            return this;
        }
        /**
         * The plane angle between the Y-Z plane and the plane containing both the stylus axis and the Y axis, in degrees of the range [-90,90], a positive tiltX is to the right (default: 0)
         * @param tiltX field value
         * @return this model
         */
        public TouchPoint tiltX(double tiltX) {
            set("tiltX", tiltX);
            return this;
        }
        /**
         * The plane angle between the X-Z plane and the plane containing both the stylus axis and the X axis, in degrees of the range [-90,90], a positive tiltY is towards the user (default: 0).
         * @param tiltY field value
         * @return this model
         */
        public TouchPoint tiltY(double tiltY) {
            set("tiltY", tiltY);
            return this;
        }
        /**
         * The clockwise rotation of a pen stylus around its own major axis, in degrees in the range [0,359] (default: 0).
         * <p><b>Experimental:</b> this part of CDP may change without notice.
         * @param twist field value
         * @return this model
         */
        public TouchPoint twist(long twist) {
            set("twist", twist);
            return this;
        }
        /**
         * Identifier used to track touch sources between events, must be unique within an event.
         * @param id field value
         * @return this model
         */
        public TouchPoint id(double id) {
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
        @Override public String value() { return value; }
        public static GestureSourceType of(String value) {
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
        @Override public String value() { return value; }
        public static MouseButton of(String value) {
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
        @Override public Double value() { return value; }
        @Override public boolean equals(@org.jspecify.annotations.Nullable Object other) {
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
            return requireString("mimeType");
        }
        /**
         * Depending of the value of {@code mimeType}, it contains the dragged link, text, HTML markup or any other data.
         * @return the protocol field value
         */
        public String data() {
            return requireString("data");
        }
        /**
         * Title associated with a link. Only valid when {@code mimeType} == &quot;text/uri-list&quot;.
         * @return the protocol field value, empty when absent
         */
        public Optional<String> title() {
            return optionalString("title");
        }
        /**
         * Stores the base URL for the contained markup. Only valid when {@code mimeType} == &quot;text/html&quot;.
         * @return the protocol field value, empty when absent
         */
        public Optional<String> baseURL() {
            return optionalString("baseURL");
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
         * @param title field value
         * @return this model
         */
        public DragDataItem title(String title) {
            set("title", title);
            return this;
        }
        /**
         * Stores the base URL for the contained markup. Only valid when {@code mimeType} == &quot;text/html&quot;.
         * @param baseURL field value
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
            return require(ITEMS);
        }
        /**
         * List of filenames that should be included when dropping
         * @return the protocol field value, empty when absent
         */
        public Optional<java.util.List<String>> files() {
            return optional(FILES);
        }
        /**
         * Bit field representing allowed drag operations. Copy = 1, Link = 2, Move = 16
         * @return the protocol field value
         */
        public long dragOperationsMask() {
            return requireLong("dragOperationsMask");
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
         * @param files field value
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
        public static DispatchDragEventRequest fromMap(Map<String, Object> values) {
            final DispatchDragEventRequest instance_ = new DispatchDragEventRequest();
            if (values != null) instance_.values.putAll(values);
            return instance_;
        }
        /**
         * Type of the drag event.
         * @return the protocol field value
         */
        public DispatchDragEventTypeValues type() {
            return require(TYPE);
        }
        /**
         * X coordinate of the event relative to the main frame&#x27;s viewport in CSS pixels.
         * @return the protocol field value
         */
        public double x() {
            return requireDouble("x");
        }
        /**
         * Y coordinate of the event relative to the main frame&#x27;s viewport in CSS pixels. 0 refers to the top of the viewport and Y increases as it proceeds towards the bottom of the viewport.
         * @return the protocol field value
         */
        public double y() {
            return requireDouble("y");
        }
        /**
         * Returns the data field.
         * @return the protocol field value
         */
        public Input.DragData data() {
            return require(DATA);
        }
        /**
         * Bit field representing pressed modifier keys. Alt=1, Ctrl=2, Meta/Command=4, Shift=8 (default: 0).
         * @return the protocol field value, empty when absent
         */
        public OptionalLong modifiers() {
            return optionalLong("modifiers");
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
         * @param modifiers field value
         * @return this model
         */
        public DispatchDragEventRequest modifiers(long modifiers) {
            set("modifiers", modifiers);
            return this;
        }
    }
    /**
     * Dispatches a key event to the page.
     */
    public static final class DispatchKeyEventRequest extends CdpObject {
        public DispatchKeyEventRequest() {}
        public static DispatchKeyEventRequest fromMap(Map<String, Object> values) {
            final DispatchKeyEventRequest instance_ = new DispatchKeyEventRequest();
            if (values != null) instance_.values.putAll(values);
            return instance_;
        }
        /**
         * Type of the key event.
         * @return the protocol field value
         */
        public DispatchKeyEventTypeValues type() {
            return require(TYPE_2);
        }
        /**
         * Bit field representing pressed modifier keys. Alt=1, Ctrl=2, Meta/Command=4, Shift=8 (default: 0).
         * @return the protocol field value, empty when absent
         */
        public OptionalLong modifiers() {
            return optionalLong("modifiers");
        }
        /**
         * Time at which the event occurred.
         * @return the protocol field value, empty when absent
         */
        public Optional<Input.TimeSinceEpoch> timestamp() {
            return optional(TIMESTAMP);
        }
        /**
         * Text as generated by processing a virtual key code with a keyboard layout. Not needed for for {@code keyUp} and {@code rawKeyDown} events (default: &quot;&quot;)
         * @return the protocol field value, empty when absent
         */
        public Optional<String> text() {
            return optionalString("text");
        }
        /**
         * Text that would have been generated by the keyboard if no modifiers were pressed (except for shift). Useful for shortcut (accelerator) key handling (default: &quot;&quot;).
         * @return the protocol field value, empty when absent
         */
        public Optional<String> unmodifiedText() {
            return optionalString("unmodifiedText");
        }
        /**
         * Unique key identifier (e.g., &#x27;U+0041&#x27;) (default: &quot;&quot;).
         * @return the protocol field value, empty when absent
         */
        public Optional<String> keyIdentifier() {
            return optionalString("keyIdentifier");
        }
        /**
         * Unique DOM defined string value for each physical key (e.g., &#x27;KeyA&#x27;) (default: &quot;&quot;).
         * @return the protocol field value, empty when absent
         */
        public Optional<String> code() {
            return optionalString("code");
        }
        /**
         * Unique DOM defined string value describing the meaning of the key in the context of active modifiers, keyboard layout, etc (e.g., &#x27;AltGr&#x27;) (default: &quot;&quot;).
         * @return the protocol field value, empty when absent
         */
        public Optional<String> key() {
            return optionalString("key");
        }
        /**
         * Windows virtual key code (default: 0).
         * @return the protocol field value, empty when absent
         */
        public OptionalLong windowsVirtualKeyCode() {
            return optionalLong("windowsVirtualKeyCode");
        }
        /**
         * Native virtual key code (default: 0).
         * @return the protocol field value, empty when absent
         */
        public OptionalLong nativeVirtualKeyCode() {
            return optionalLong("nativeVirtualKeyCode");
        }
        /**
         * Whether the event was generated from auto repeat (default: false).
         * @return the protocol field value, empty when absent
         */
        public Optional<Boolean> autoRepeat() {
            return optionalBoolean("autoRepeat");
        }
        /**
         * Whether the event was generated from the keypad (default: false).
         * @return the protocol field value, empty when absent
         */
        public Optional<Boolean> isKeypad() {
            return optionalBoolean("isKeypad");
        }
        /**
         * Whether the event was a system key event (default: false).
         * @return the protocol field value, empty when absent
         */
        public Optional<Boolean> isSystemKey() {
            return optionalBoolean("isSystemKey");
        }
        /**
         * Whether the event was from the left or right side of the keyboard. 1=Left, 2=Right (default: 0).
         * @return the protocol field value, empty when absent
         */
        public OptionalLong location() {
            return optionalLong("location");
        }
        /**
         * Editing commands to send with the key event (e.g., &#x27;selectAll&#x27;) (default: []). These are related to but not equal the command names used in {@code document.execCommand} and NSStandardKeyBindingResponding. See https://source.chromium.org/chromium/chromium/src/+/main:third_party/blink/renderer/core/editing/commands/editor_command_names.h for valid command names.
         * <p><b>Experimental:</b> this part of CDP may change without notice.
         * @return the protocol field value, empty when absent
         */
        public Optional<java.util.List<String>> commands() {
            return optional(COMMANDS);
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
         * @param modifiers field value
         * @return this model
         */
        public DispatchKeyEventRequest modifiers(long modifiers) {
            set("modifiers", modifiers);
            return this;
        }
        /**
         * Time at which the event occurred.
         * @param timestamp field value
         * @return this model
         */
        public DispatchKeyEventRequest timestamp(Input.TimeSinceEpoch timestamp) {
            set("timestamp", timestamp);
            return this;
        }
        /**
         * Text as generated by processing a virtual key code with a keyboard layout. Not needed for for {@code keyUp} and {@code rawKeyDown} events (default: &quot;&quot;)
         * @param text field value
         * @return this model
         */
        public DispatchKeyEventRequest text(String text) {
            set("text", text);
            return this;
        }
        /**
         * Text that would have been generated by the keyboard if no modifiers were pressed (except for shift). Useful for shortcut (accelerator) key handling (default: &quot;&quot;).
         * @param unmodifiedText field value
         * @return this model
         */
        public DispatchKeyEventRequest unmodifiedText(String unmodifiedText) {
            set("unmodifiedText", unmodifiedText);
            return this;
        }
        /**
         * Unique key identifier (e.g., &#x27;U+0041&#x27;) (default: &quot;&quot;).
         * @param keyIdentifier field value
         * @return this model
         */
        public DispatchKeyEventRequest keyIdentifier(String keyIdentifier) {
            set("keyIdentifier", keyIdentifier);
            return this;
        }
        /**
         * Unique DOM defined string value for each physical key (e.g., &#x27;KeyA&#x27;) (default: &quot;&quot;).
         * @param code field value
         * @return this model
         */
        public DispatchKeyEventRequest code(String code) {
            set("code", code);
            return this;
        }
        /**
         * Unique DOM defined string value describing the meaning of the key in the context of active modifiers, keyboard layout, etc (e.g., &#x27;AltGr&#x27;) (default: &quot;&quot;).
         * @param key field value
         * @return this model
         */
        public DispatchKeyEventRequest key(String key) {
            set("key", key);
            return this;
        }
        /**
         * Windows virtual key code (default: 0).
         * @param windowsVirtualKeyCode field value
         * @return this model
         */
        public DispatchKeyEventRequest windowsVirtualKeyCode(long windowsVirtualKeyCode) {
            set("windowsVirtualKeyCode", windowsVirtualKeyCode);
            return this;
        }
        /**
         * Native virtual key code (default: 0).
         * @param nativeVirtualKeyCode field value
         * @return this model
         */
        public DispatchKeyEventRequest nativeVirtualKeyCode(long nativeVirtualKeyCode) {
            set("nativeVirtualKeyCode", nativeVirtualKeyCode);
            return this;
        }
        /**
         * Whether the event was generated from auto repeat (default: false).
         * @param autoRepeat field value
         * @return this model
         */
        public DispatchKeyEventRequest autoRepeat(boolean autoRepeat) {
            set("autoRepeat", autoRepeat);
            return this;
        }
        /**
         * Whether the event was generated from the keypad (default: false).
         * @param isKeypad field value
         * @return this model
         */
        public DispatchKeyEventRequest isKeypad(boolean isKeypad) {
            set("isKeypad", isKeypad);
            return this;
        }
        /**
         * Whether the event was a system key event (default: false).
         * @param isSystemKey field value
         * @return this model
         */
        public DispatchKeyEventRequest isSystemKey(boolean isSystemKey) {
            set("isSystemKey", isSystemKey);
            return this;
        }
        /**
         * Whether the event was from the left or right side of the keyboard. 1=Left, 2=Right (default: 0).
         * @param location field value
         * @return this model
         */
        public DispatchKeyEventRequest location(long location) {
            set("location", location);
            return this;
        }
        /**
         * Editing commands to send with the key event (e.g., &#x27;selectAll&#x27;) (default: []). These are related to but not equal the command names used in {@code document.execCommand} and NSStandardKeyBindingResponding. See https://source.chromium.org/chromium/chromium/src/+/main:third_party/blink/renderer/core/editing/commands/editor_command_names.h for valid command names.
         * <p><b>Experimental:</b> this part of CDP may change without notice.
         * @param commands field value
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
        public static InsertTextRequest fromMap(Map<String, Object> values) {
            final InsertTextRequest instance_ = new InsertTextRequest();
            if (values != null) instance_.values.putAll(values);
            return instance_;
        }
        /**
         * The text to insert.
         * @return the protocol field value
         */
        public String text() {
            return requireString("text");
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
        public static ImeSetCompositionRequest fromMap(Map<String, Object> values) {
            final ImeSetCompositionRequest instance_ = new ImeSetCompositionRequest();
            if (values != null) instance_.values.putAll(values);
            return instance_;
        }
        /**
         * The text to insert
         * @return the protocol field value
         */
        public String text() {
            return requireString("text");
        }
        /**
         * selection start
         * @return the protocol field value
         */
        public long selectionStart() {
            return requireLong("selectionStart");
        }
        /**
         * selection end
         * @return the protocol field value
         */
        public long selectionEnd() {
            return requireLong("selectionEnd");
        }
        /**
         * replacement start
         * @return the protocol field value, empty when absent
         */
        public OptionalLong replacementStart() {
            return optionalLong("replacementStart");
        }
        /**
         * replacement end
         * @return the protocol field value, empty when absent
         */
        public OptionalLong replacementEnd() {
            return optionalLong("replacementEnd");
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
         * @param replacementStart field value
         * @return this model
         */
        public ImeSetCompositionRequest replacementStart(long replacementStart) {
            set("replacementStart", replacementStart);
            return this;
        }
        /**
         * replacement end
         * @param replacementEnd field value
         * @return this model
         */
        public ImeSetCompositionRequest replacementEnd(long replacementEnd) {
            set("replacementEnd", replacementEnd);
            return this;
        }
    }
    /**
     * Dispatches a mouse event to the page.
     */
    public static final class DispatchMouseEventRequest extends CdpObject {
        public DispatchMouseEventRequest() {}
        public static DispatchMouseEventRequest fromMap(Map<String, Object> values) {
            final DispatchMouseEventRequest instance_ = new DispatchMouseEventRequest();
            if (values != null) instance_.values.putAll(values);
            return instance_;
        }
        /**
         * Type of the mouse event.
         * @return the protocol field value
         */
        public DispatchMouseEventTypeValues type() {
            return require(TYPE_3);
        }
        /**
         * X coordinate of the event relative to the main frame&#x27;s viewport in CSS pixels.
         * @return the protocol field value
         */
        public double x() {
            return requireDouble("x");
        }
        /**
         * Y coordinate of the event relative to the main frame&#x27;s viewport in CSS pixels. 0 refers to the top of the viewport and Y increases as it proceeds towards the bottom of the viewport.
         * @return the protocol field value
         */
        public double y() {
            return requireDouble("y");
        }
        /**
         * Bit field representing pressed modifier keys. Alt=1, Ctrl=2, Meta/Command=4, Shift=8 (default: 0).
         * @return the protocol field value, empty when absent
         */
        public OptionalLong modifiers() {
            return optionalLong("modifiers");
        }
        /**
         * Time at which the event occurred.
         * @return the protocol field value, empty when absent
         */
        public Optional<Input.TimeSinceEpoch> timestamp() {
            return optional(TIMESTAMP);
        }
        /**
         * Mouse button (default: &quot;none&quot;).
         * @return the protocol field value, empty when absent
         */
        public Optional<Input.MouseButton> button() {
            return optional(BUTTON);
        }
        /**
         * A number indicating which buttons are pressed on the mouse when a mouse event is triggered. Left=1, Right=2, Middle=4, Back=8, Forward=16, None=0.
         * @return the protocol field value, empty when absent
         */
        public OptionalLong buttons() {
            return optionalLong("buttons");
        }
        /**
         * Number of times the mouse button was clicked (default: 0).
         * @return the protocol field value, empty when absent
         */
        public OptionalLong clickCount() {
            return optionalLong("clickCount");
        }
        /**
         * The normalized pressure, which has a range of [0,1] (default: 0).
         * <p><b>Experimental:</b> this part of CDP may change without notice.
         * @return the protocol field value, empty when absent
         */
        public OptionalDouble force() {
            return optionalDouble("force");
        }
        /**
         * The normalized tangential pressure, which has a range of [-1,1] (default: 0).
         * <p><b>Experimental:</b> this part of CDP may change without notice.
         * @return the protocol field value, empty when absent
         */
        public OptionalDouble tangentialPressure() {
            return optionalDouble("tangentialPressure");
        }
        /**
         * The plane angle between the Y-Z plane and the plane containing both the stylus axis and the Y axis, in degrees of the range [-90,90], a positive tiltX is to the right (default: 0).
         * @return the protocol field value, empty when absent
         */
        public OptionalDouble tiltX() {
            return optionalDouble("tiltX");
        }
        /**
         * The plane angle between the X-Z plane and the plane containing both the stylus axis and the X axis, in degrees of the range [-90,90], a positive tiltY is towards the user (default: 0).
         * @return the protocol field value, empty when absent
         */
        public OptionalDouble tiltY() {
            return optionalDouble("tiltY");
        }
        /**
         * The clockwise rotation of a pen stylus around its own major axis, in degrees in the range [0,359] (default: 0).
         * <p><b>Experimental:</b> this part of CDP may change without notice.
         * @return the protocol field value, empty when absent
         */
        public OptionalLong twist() {
            return optionalLong("twist");
        }
        /**
         * X delta in CSS pixels for mouse wheel event (default: 0).
         * @return the protocol field value, empty when absent
         */
        public OptionalDouble deltaX() {
            return optionalDouble("deltaX");
        }
        /**
         * Y delta in CSS pixels for mouse wheel event (default: 0).
         * @return the protocol field value, empty when absent
         */
        public OptionalDouble deltaY() {
            return optionalDouble("deltaY");
        }
        /**
         * Pointer type (default: &quot;mouse&quot;).
         * @return the protocol field value, empty when absent
         */
        public Optional<DispatchMouseEventPointerTypeValues> pointerType() {
            return optional(POINTER_TYPE);
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
         * @param modifiers field value
         * @return this model
         */
        public DispatchMouseEventRequest modifiers(long modifiers) {
            set("modifiers", modifiers);
            return this;
        }
        /**
         * Time at which the event occurred.
         * @param timestamp field value
         * @return this model
         */
        public DispatchMouseEventRequest timestamp(Input.TimeSinceEpoch timestamp) {
            set("timestamp", timestamp);
            return this;
        }
        /**
         * Mouse button (default: &quot;none&quot;).
         * @param button field value
         * @return this model
         */
        public DispatchMouseEventRequest button(Input.MouseButton button) {
            set("button", button);
            return this;
        }
        /**
         * A number indicating which buttons are pressed on the mouse when a mouse event is triggered. Left=1, Right=2, Middle=4, Back=8, Forward=16, None=0.
         * @param buttons field value
         * @return this model
         */
        public DispatchMouseEventRequest buttons(long buttons) {
            set("buttons", buttons);
            return this;
        }
        /**
         * Number of times the mouse button was clicked (default: 0).
         * @param clickCount field value
         * @return this model
         */
        public DispatchMouseEventRequest clickCount(long clickCount) {
            set("clickCount", clickCount);
            return this;
        }
        /**
         * The normalized pressure, which has a range of [0,1] (default: 0).
         * <p><b>Experimental:</b> this part of CDP may change without notice.
         * @param force field value
         * @return this model
         */
        public DispatchMouseEventRequest force(double force) {
            set("force", force);
            return this;
        }
        /**
         * The normalized tangential pressure, which has a range of [-1,1] (default: 0).
         * <p><b>Experimental:</b> this part of CDP may change without notice.
         * @param tangentialPressure field value
         * @return this model
         */
        public DispatchMouseEventRequest tangentialPressure(double tangentialPressure) {
            set("tangentialPressure", tangentialPressure);
            return this;
        }
        /**
         * The plane angle between the Y-Z plane and the plane containing both the stylus axis and the Y axis, in degrees of the range [-90,90], a positive tiltX is to the right (default: 0).
         * @param tiltX field value
         * @return this model
         */
        public DispatchMouseEventRequest tiltX(double tiltX) {
            set("tiltX", tiltX);
            return this;
        }
        /**
         * The plane angle between the X-Z plane and the plane containing both the stylus axis and the X axis, in degrees of the range [-90,90], a positive tiltY is towards the user (default: 0).
         * @param tiltY field value
         * @return this model
         */
        public DispatchMouseEventRequest tiltY(double tiltY) {
            set("tiltY", tiltY);
            return this;
        }
        /**
         * The clockwise rotation of a pen stylus around its own major axis, in degrees in the range [0,359] (default: 0).
         * <p><b>Experimental:</b> this part of CDP may change without notice.
         * @param twist field value
         * @return this model
         */
        public DispatchMouseEventRequest twist(long twist) {
            set("twist", twist);
            return this;
        }
        /**
         * X delta in CSS pixels for mouse wheel event (default: 0).
         * @param deltaX field value
         * @return this model
         */
        public DispatchMouseEventRequest deltaX(double deltaX) {
            set("deltaX", deltaX);
            return this;
        }
        /**
         * Y delta in CSS pixels for mouse wheel event (default: 0).
         * @param deltaY field value
         * @return this model
         */
        public DispatchMouseEventRequest deltaY(double deltaY) {
            set("deltaY", deltaY);
            return this;
        }
        /**
         * Pointer type (default: &quot;mouse&quot;).
         * @param pointerType field value
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
        public static DispatchTouchEventRequest fromMap(Map<String, Object> values) {
            final DispatchTouchEventRequest instance_ = new DispatchTouchEventRequest();
            if (values != null) instance_.values.putAll(values);
            return instance_;
        }
        /**
         * Type of the touch event. TouchEnd and TouchCancel must not contain any touch points, while TouchStart and TouchMove must contains at least one.
         * @return the protocol field value
         */
        public DispatchTouchEventTypeValues type() {
            return require(TYPE_4);
        }
        /**
         * Active touch points on the touch device. One event per any changed point (compared to previous touch event in a sequence) is generated, emulating pressing/moving/releasing points one by one.
         * @return the protocol field value
         */
        public java.util.List<Input.TouchPoint> touchPoints() {
            return require(TOUCH_POINTS);
        }
        /**
         * Bit field representing pressed modifier keys. Alt=1, Ctrl=2, Meta/Command=4, Shift=8 (default: 0).
         * @return the protocol field value, empty when absent
         */
        public OptionalLong modifiers() {
            return optionalLong("modifiers");
        }
        /**
         * Time at which the event occurred.
         * @return the protocol field value, empty when absent
         */
        public Optional<Input.TimeSinceEpoch> timestamp() {
            return optional(TIMESTAMP);
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
         * @param modifiers field value
         * @return this model
         */
        public DispatchTouchEventRequest modifiers(long modifiers) {
            set("modifiers", modifiers);
            return this;
        }
        /**
         * Time at which the event occurred.
         * @param timestamp field value
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
        public static EmulateTouchFromMouseEventRequest fromMap(Map<String, Object> values) {
            final EmulateTouchFromMouseEventRequest instance_ = new EmulateTouchFromMouseEventRequest();
            if (values != null) instance_.values.putAll(values);
            return instance_;
        }
        /**
         * Type of the mouse event.
         * @return the protocol field value
         */
        public EmulateTouchFromMouseEventTypeValues type() {
            return require(TYPE_5);
        }
        /**
         * X coordinate of the mouse pointer in DIP.
         * @return the protocol field value
         */
        public long x() {
            return requireLong("x");
        }
        /**
         * Y coordinate of the mouse pointer in DIP.
         * @return the protocol field value
         */
        public long y() {
            return requireLong("y");
        }
        /**
         * Mouse button. Only &quot;none&quot;, &quot;left&quot;, &quot;right&quot; are supported.
         * @return the protocol field value
         */
        public Input.MouseButton button() {
            return require(BUTTON);
        }
        /**
         * Time at which the event occurred (default: current time).
         * @return the protocol field value, empty when absent
         */
        public Optional<Input.TimeSinceEpoch> timestamp() {
            return optional(TIMESTAMP);
        }
        /**
         * X delta in DIP for mouse wheel event (default: 0).
         * @return the protocol field value, empty when absent
         */
        public OptionalDouble deltaX() {
            return optionalDouble("deltaX");
        }
        /**
         * Y delta in DIP for mouse wheel event (default: 0).
         * @return the protocol field value, empty when absent
         */
        public OptionalDouble deltaY() {
            return optionalDouble("deltaY");
        }
        /**
         * Bit field representing pressed modifier keys. Alt=1, Ctrl=2, Meta/Command=4, Shift=8 (default: 0).
         * @return the protocol field value, empty when absent
         */
        public OptionalLong modifiers() {
            return optionalLong("modifiers");
        }
        /**
         * Number of times the mouse button was clicked (default: 0).
         * @return the protocol field value, empty when absent
         */
        public OptionalLong clickCount() {
            return optionalLong("clickCount");
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
         * @param timestamp field value
         * @return this model
         */
        public EmulateTouchFromMouseEventRequest timestamp(Input.TimeSinceEpoch timestamp) {
            set("timestamp", timestamp);
            return this;
        }
        /**
         * X delta in DIP for mouse wheel event (default: 0).
         * @param deltaX field value
         * @return this model
         */
        public EmulateTouchFromMouseEventRequest deltaX(double deltaX) {
            set("deltaX", deltaX);
            return this;
        }
        /**
         * Y delta in DIP for mouse wheel event (default: 0).
         * @param deltaY field value
         * @return this model
         */
        public EmulateTouchFromMouseEventRequest deltaY(double deltaY) {
            set("deltaY", deltaY);
            return this;
        }
        /**
         * Bit field representing pressed modifier keys. Alt=1, Ctrl=2, Meta/Command=4, Shift=8 (default: 0).
         * @param modifiers field value
         * @return this model
         */
        public EmulateTouchFromMouseEventRequest modifiers(long modifiers) {
            set("modifiers", modifiers);
            return this;
        }
        /**
         * Number of times the mouse button was clicked (default: 0).
         * @param clickCount field value
         * @return this model
         */
        public EmulateTouchFromMouseEventRequest clickCount(long clickCount) {
            set("clickCount", clickCount);
            return this;
        }
    }
    /**
     * Ignores input events (useful while auditing page).
     */
    public static final class SetIgnoreInputEventsRequest extends CdpObject {
        public SetIgnoreInputEventsRequest() {}
        public static SetIgnoreInputEventsRequest fromMap(Map<String, Object> values) {
            final SetIgnoreInputEventsRequest instance_ = new SetIgnoreInputEventsRequest();
            if (values != null) instance_.values.putAll(values);
            return instance_;
        }
        /**
         * Ignores input events processing when set to true.
         * @return the protocol field value
         */
        public boolean ignore() {
            return requireBoolean("ignore");
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
        public static SetInterceptDragsRequest fromMap(Map<String, Object> values) {
            final SetInterceptDragsRequest instance_ = new SetInterceptDragsRequest();
            if (values != null) instance_.values.putAll(values);
            return instance_;
        }
        /**
         * Returns the enabled field.
         * @return the protocol field value
         */
        public boolean enabled() {
            return requireBoolean("enabled");
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
        public static SynthesizePinchGestureRequest fromMap(Map<String, Object> values) {
            final SynthesizePinchGestureRequest instance_ = new SynthesizePinchGestureRequest();
            if (values != null) instance_.values.putAll(values);
            return instance_;
        }
        /**
         * X coordinate of the start of the gesture in CSS pixels.
         * @return the protocol field value
         */
        public double x() {
            return requireDouble("x");
        }
        /**
         * Y coordinate of the start of the gesture in CSS pixels.
         * @return the protocol field value
         */
        public double y() {
            return requireDouble("y");
        }
        /**
         * Relative scale factor after zooming (&gt;1.0 zooms in, &lt;1.0 zooms out).
         * @return the protocol field value
         */
        public double scaleFactor() {
            return requireDouble("scaleFactor");
        }
        /**
         * Relative pointer speed in pixels per second (default: 800).
         * @return the protocol field value, empty when absent
         */
        public OptionalLong relativeSpeed() {
            return optionalLong("relativeSpeed");
        }
        /**
         * Which type of input events to be generated (default: &#x27;default&#x27;, which queries the platform for the preferred input type).
         * @return the protocol field value, empty when absent
         */
        public Optional<Input.GestureSourceType> gestureSourceType() {
            return optional(GESTURE_SOURCE_TYPE);
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
         * @param relativeSpeed field value
         * @return this model
         */
        public SynthesizePinchGestureRequest relativeSpeed(long relativeSpeed) {
            set("relativeSpeed", relativeSpeed);
            return this;
        }
        /**
         * Which type of input events to be generated (default: &#x27;default&#x27;, which queries the platform for the preferred input type).
         * @param gestureSourceType field value
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
        public static SynthesizeScrollGestureRequest fromMap(Map<String, Object> values) {
            final SynthesizeScrollGestureRequest instance_ = new SynthesizeScrollGestureRequest();
            if (values != null) instance_.values.putAll(values);
            return instance_;
        }
        /**
         * X coordinate of the start of the gesture in CSS pixels.
         * @return the protocol field value
         */
        public double x() {
            return requireDouble("x");
        }
        /**
         * Y coordinate of the start of the gesture in CSS pixels.
         * @return the protocol field value
         */
        public double y() {
            return requireDouble("y");
        }
        /**
         * The distance to scroll along the X axis (positive to scroll left).
         * @return the protocol field value, empty when absent
         */
        public OptionalDouble xDistance() {
            return optionalDouble("xDistance");
        }
        /**
         * The distance to scroll along the Y axis (positive to scroll up).
         * @return the protocol field value, empty when absent
         */
        public OptionalDouble yDistance() {
            return optionalDouble("yDistance");
        }
        /**
         * The number of additional pixels to scroll back along the X axis, in addition to the given distance.
         * @return the protocol field value, empty when absent
         */
        public OptionalDouble xOverscroll() {
            return optionalDouble("xOverscroll");
        }
        /**
         * The number of additional pixels to scroll back along the Y axis, in addition to the given distance.
         * @return the protocol field value, empty when absent
         */
        public OptionalDouble yOverscroll() {
            return optionalDouble("yOverscroll");
        }
        /**
         * Prevent fling (default: true).
         * @return the protocol field value, empty when absent
         */
        public Optional<Boolean> preventFling() {
            return optionalBoolean("preventFling");
        }
        /**
         * Swipe speed in pixels per second (default: 800).
         * @return the protocol field value, empty when absent
         */
        public OptionalLong speed() {
            return optionalLong("speed");
        }
        /**
         * Which type of input events to be generated (default: &#x27;default&#x27;, which queries the platform for the preferred input type).
         * @return the protocol field value, empty when absent
         */
        public Optional<Input.GestureSourceType> gestureSourceType() {
            return optional(GESTURE_SOURCE_TYPE);
        }
        /**
         * The number of times to repeat the gesture (default: 0).
         * @return the protocol field value, empty when absent
         */
        public OptionalLong repeatCount() {
            return optionalLong("repeatCount");
        }
        /**
         * The number of milliseconds delay between each repeat. (default: 250).
         * @return the protocol field value, empty when absent
         */
        public OptionalLong repeatDelayMs() {
            return optionalLong("repeatDelayMs");
        }
        /**
         * The name of the interaction markers to generate, if not empty (default: &quot;&quot;).
         * @return the protocol field value, empty when absent
         */
        public Optional<String> interactionMarkerName() {
            return optionalString("interactionMarkerName");
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
         * @param xDistance field value
         * @return this model
         */
        public SynthesizeScrollGestureRequest xDistance(double xDistance) {
            set("xDistance", xDistance);
            return this;
        }
        /**
         * The distance to scroll along the Y axis (positive to scroll up).
         * @param yDistance field value
         * @return this model
         */
        public SynthesizeScrollGestureRequest yDistance(double yDistance) {
            set("yDistance", yDistance);
            return this;
        }
        /**
         * The number of additional pixels to scroll back along the X axis, in addition to the given distance.
         * @param xOverscroll field value
         * @return this model
         */
        public SynthesizeScrollGestureRequest xOverscroll(double xOverscroll) {
            set("xOverscroll", xOverscroll);
            return this;
        }
        /**
         * The number of additional pixels to scroll back along the Y axis, in addition to the given distance.
         * @param yOverscroll field value
         * @return this model
         */
        public SynthesizeScrollGestureRequest yOverscroll(double yOverscroll) {
            set("yOverscroll", yOverscroll);
            return this;
        }
        /**
         * Prevent fling (default: true).
         * @param preventFling field value
         * @return this model
         */
        public SynthesizeScrollGestureRequest preventFling(boolean preventFling) {
            set("preventFling", preventFling);
            return this;
        }
        /**
         * Swipe speed in pixels per second (default: 800).
         * @param speed field value
         * @return this model
         */
        public SynthesizeScrollGestureRequest speed(long speed) {
            set("speed", speed);
            return this;
        }
        /**
         * Which type of input events to be generated (default: &#x27;default&#x27;, which queries the platform for the preferred input type).
         * @param gestureSourceType field value
         * @return this model
         */
        public SynthesizeScrollGestureRequest gestureSourceType(Input.GestureSourceType gestureSourceType) {
            set("gestureSourceType", gestureSourceType);
            return this;
        }
        /**
         * The number of times to repeat the gesture (default: 0).
         * @param repeatCount field value
         * @return this model
         */
        public SynthesizeScrollGestureRequest repeatCount(long repeatCount) {
            set("repeatCount", repeatCount);
            return this;
        }
        /**
         * The number of milliseconds delay between each repeat. (default: 250).
         * @param repeatDelayMs field value
         * @return this model
         */
        public SynthesizeScrollGestureRequest repeatDelayMs(long repeatDelayMs) {
            set("repeatDelayMs", repeatDelayMs);
            return this;
        }
        /**
         * The name of the interaction markers to generate, if not empty (default: &quot;&quot;).
         * @param interactionMarkerName field value
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
        public static SynthesizeTapGestureRequest fromMap(Map<String, Object> values) {
            final SynthesizeTapGestureRequest instance_ = new SynthesizeTapGestureRequest();
            if (values != null) instance_.values.putAll(values);
            return instance_;
        }
        /**
         * X coordinate of the start of the gesture in CSS pixels.
         * @return the protocol field value
         */
        public double x() {
            return requireDouble("x");
        }
        /**
         * Y coordinate of the start of the gesture in CSS pixels.
         * @return the protocol field value
         */
        public double y() {
            return requireDouble("y");
        }
        /**
         * Duration between touchdown and touchup events in ms (default: 50).
         * @return the protocol field value, empty when absent
         */
        public OptionalLong duration() {
            return optionalLong("duration");
        }
        /**
         * Number of times to perform the tap (e.g. 2 for double tap, default: 1).
         * @return the protocol field value, empty when absent
         */
        public OptionalLong tapCount() {
            return optionalLong("tapCount");
        }
        /**
         * Which type of input events to be generated (default: &#x27;default&#x27;, which queries the platform for the preferred input type).
         * @return the protocol field value, empty when absent
         */
        public Optional<Input.GestureSourceType> gestureSourceType() {
            return optional(GESTURE_SOURCE_TYPE);
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
         * @param duration field value
         * @return this model
         */
        public SynthesizeTapGestureRequest duration(long duration) {
            set("duration", duration);
            return this;
        }
        /**
         * Number of times to perform the tap (e.g. 2 for double tap, default: 1).
         * @param tapCount field value
         * @return this model
         */
        public SynthesizeTapGestureRequest tapCount(long tapCount) {
            set("tapCount", tapCount);
            return this;
        }
        /**
         * Which type of input events to be generated (default: &#x27;default&#x27;, which queries the platform for the preferred input type).
         * @param gestureSourceType field value
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
            return require(DATA);
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
        @Override public String value() { return value; }
        public static DispatchDragEventTypeValues of(String value) {
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
        @Override public String value() { return value; }
        public static DispatchKeyEventTypeValues of(String value) {
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
        @Override public String value() { return value; }
        public static DispatchMouseEventTypeValues of(String value) {
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
        @Override public String value() { return value; }
        public static DispatchMouseEventPointerTypeValues of(String value) {
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
        @Override public String value() { return value; }
        public static DispatchTouchEventTypeValues of(String value) {
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
        @Override public String value() { return value; }
        public static EmulateTouchFromMouseEventTypeValues of(String value) {
            for (EmulateTouchFromMouseEventTypeValues constant : values()) {
                if (constant.value.equals(value)) return constant;
            }
            throw new IllegalArgumentException("Unknown EmulateTouchFromMouseEventTypeValues value: " + value);
        }
    }
    public static final class Client extends CdpDomainClient {
        public Client(CdpClient client) { super(client); }
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
        public CompletionStage<Void> dispatchDragEvent(DispatchDragEventTypeValues type, double x, double y, Input.DragData data, long modifiers) {
            return call("Input.dispatchDragEvent", params("type", type, "x", x, "y", y, "data", data, "modifiers", modifiers));
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
            return call("Input.dispatchDragEvent", params("type", type, "x", x, "y", y, "data", data));
        }
        /**
         * Dispatches a drag event into the page.
         * <p><b>Experimental:</b> this part of CDP may change without notice.
         * @param request request parameters
         * @return a stage completing when the command completes
         */
        public CompletionStage<Void> dispatchDragEvent(DispatchDragEventRequest request) {
            return call("Input.dispatchDragEvent", request);
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
        public CompletionStage<Void> dispatchKeyEvent(DispatchKeyEventTypeValues type, @Nullable Long modifiers, Input.@Nullable TimeSinceEpoch timestamp, @Nullable String text, @Nullable String unmodifiedText, @Nullable String keyIdentifier, @Nullable String code, @Nullable String key, @Nullable Long windowsVirtualKeyCode, @Nullable Long nativeVirtualKeyCode, @Nullable Boolean autoRepeat, @Nullable Boolean isKeypad, @Nullable Boolean isSystemKey, @Nullable Long location, java.util.@Nullable List<String> commands) {
            return call("Input.dispatchKeyEvent", params("type", type, "modifiers", modifiers, "timestamp", timestamp, "text", text, "unmodifiedText", unmodifiedText, "keyIdentifier", keyIdentifier, "code", code, "key", key, "windowsVirtualKeyCode", windowsVirtualKeyCode, "nativeVirtualKeyCode", nativeVirtualKeyCode, "autoRepeat", autoRepeat, "isKeypad", isKeypad, "isSystemKey", isSystemKey, "location", location, "commands", commands));
        }
        /**
         * Dispatches a key event to the page.
         * @param type protocol value
         * @return a stage completing when the command completes
         */
        public CompletionStage<Void> dispatchKeyEvent(DispatchKeyEventTypeValues type) {
            return call("Input.dispatchKeyEvent", params("type", type));
        }
        /**
         * Dispatches a key event to the page.
         * @param request request parameters
         * @return a stage completing when the command completes
         */
        public CompletionStage<Void> dispatchKeyEvent(DispatchKeyEventRequest request) {
            return call("Input.dispatchKeyEvent", request);
        }
        /**
         * This method emulates inserting text that doesn&#x27;t come from a key press, for example an emoji keyboard or an IME.
         * <p><b>Experimental:</b> this part of CDP may change without notice.
         * @param text protocol value
         * @return a stage completing when the command completes
         */
        public CompletionStage<Void> insertText(String text) {
            return call("Input.insertText", params("text", text));
        }
        /**
         * This method emulates inserting text that doesn&#x27;t come from a key press, for example an emoji keyboard or an IME.
         * <p><b>Experimental:</b> this part of CDP may change without notice.
         * @param request request parameters
         * @return a stage completing when the command completes
         */
        public CompletionStage<Void> insertText(InsertTextRequest request) {
            return call("Input.insertText", request);
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
        public CompletionStage<Void> imeSetComposition(String text, long selectionStart, long selectionEnd, @Nullable Long replacementStart, @Nullable Long replacementEnd) {
            return call("Input.imeSetComposition", params("text", text, "selectionStart", selectionStart, "selectionEnd", selectionEnd, "replacementStart", replacementStart, "replacementEnd", replacementEnd));
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
            return call("Input.imeSetComposition", params("text", text, "selectionStart", selectionStart, "selectionEnd", selectionEnd));
        }
        /**
         * This method sets the current candidate text for IME. Use imeCommitComposition to commit the final text. Use imeSetComposition with empty string as text to cancel composition.
         * <p><b>Experimental:</b> this part of CDP may change without notice.
         * @param request request parameters
         * @return a stage completing when the command completes
         */
        public CompletionStage<Void> imeSetComposition(ImeSetCompositionRequest request) {
            return call("Input.imeSetComposition", request);
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
        public CompletionStage<Void> dispatchMouseEvent(DispatchMouseEventTypeValues type, double x, double y, @Nullable Long modifiers, Input.@Nullable TimeSinceEpoch timestamp, Input.@Nullable MouseButton button, @Nullable Long buttons, @Nullable Long clickCount, @Nullable Double force, @Nullable Double tangentialPressure, @Nullable Double tiltX, @Nullable Double tiltY, @Nullable Long twist, @Nullable Double deltaX, @Nullable Double deltaY, @Nullable DispatchMouseEventPointerTypeValues pointerType) {
            return call("Input.dispatchMouseEvent", params("type", type, "x", x, "y", y, "modifiers", modifiers, "timestamp", timestamp, "button", button, "buttons", buttons, "clickCount", clickCount, "force", force, "tangentialPressure", tangentialPressure, "tiltX", tiltX, "tiltY", tiltY, "twist", twist, "deltaX", deltaX, "deltaY", deltaY, "pointerType", pointerType));
        }
        /**
         * Dispatches a mouse event to the page.
         * @param type protocol value
         * @param x protocol value
         * @param y protocol value
         * @return a stage completing when the command completes
         */
        public CompletionStage<Void> dispatchMouseEvent(DispatchMouseEventTypeValues type, double x, double y) {
            return call("Input.dispatchMouseEvent", params("type", type, "x", x, "y", y));
        }
        /**
         * Dispatches a mouse event to the page.
         * @param request request parameters
         * @return a stage completing when the command completes
         */
        public CompletionStage<Void> dispatchMouseEvent(DispatchMouseEventRequest request) {
            return call("Input.dispatchMouseEvent", request);
        }
        /**
         * Dispatches a touch event to the page.
         * @param type protocol value
         * @param touchPoints protocol value
         * @param modifiers protocol value
         * @param timestamp protocol value
         * @return a stage completing when the command completes
         */
        public CompletionStage<Void> dispatchTouchEvent(DispatchTouchEventTypeValues type, java.util.List<Input.TouchPoint> touchPoints, @Nullable Long modifiers, Input.@Nullable TimeSinceEpoch timestamp) {
            return call("Input.dispatchTouchEvent", params("type", type, "touchPoints", touchPoints, "modifiers", modifiers, "timestamp", timestamp));
        }
        /**
         * Dispatches a touch event to the page.
         * @param type protocol value
         * @param touchPoints protocol value
         * @return a stage completing when the command completes
         */
        public CompletionStage<Void> dispatchTouchEvent(DispatchTouchEventTypeValues type, java.util.List<Input.TouchPoint> touchPoints) {
            return call("Input.dispatchTouchEvent", params("type", type, "touchPoints", touchPoints));
        }
        /**
         * Dispatches a touch event to the page.
         * @param request request parameters
         * @return a stage completing when the command completes
         */
        public CompletionStage<Void> dispatchTouchEvent(DispatchTouchEventRequest request) {
            return call("Input.dispatchTouchEvent", request);
        }
        /**
         * Cancels any active dragging in the page.
         * @return a stage completing when the command completes
         */
        public CompletionStage<Void> cancelDragging() {
            return call("Input.cancelDragging");
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
        public CompletionStage<Void> emulateTouchFromMouseEvent(EmulateTouchFromMouseEventTypeValues type, long x, long y, Input.MouseButton button, Input.@Nullable TimeSinceEpoch timestamp, @Nullable Double deltaX, @Nullable Double deltaY, @Nullable Long modifiers, @Nullable Long clickCount) {
            return call("Input.emulateTouchFromMouseEvent", params("type", type, "x", x, "y", y, "button", button, "timestamp", timestamp, "deltaX", deltaX, "deltaY", deltaY, "modifiers", modifiers, "clickCount", clickCount));
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
            return call("Input.emulateTouchFromMouseEvent", params("type", type, "x", x, "y", y, "button", button));
        }
        /**
         * Emulates touch event from the mouse event parameters.
         * <p><b>Experimental:</b> this part of CDP may change without notice.
         * @param request request parameters
         * @return a stage completing when the command completes
         */
        public CompletionStage<Void> emulateTouchFromMouseEvent(EmulateTouchFromMouseEventRequest request) {
            return call("Input.emulateTouchFromMouseEvent", request);
        }
        /**
         * Ignores input events (useful while auditing page).
         * @param ignore protocol value
         * @return a stage completing when the command completes
         */
        public CompletionStage<Void> setIgnoreInputEvents(boolean ignore) {
            return call("Input.setIgnoreInputEvents", params("ignore", ignore));
        }
        /**
         * Ignores input events (useful while auditing page).
         * @param request request parameters
         * @return a stage completing when the command completes
         */
        public CompletionStage<Void> setIgnoreInputEvents(SetIgnoreInputEventsRequest request) {
            return call("Input.setIgnoreInputEvents", request);
        }
        /**
         * Prevents default drag and drop behavior and instead emits {@code Input.dragIntercepted} events. Drag and drop behavior can be directly controlled via {@code Input.dispatchDragEvent}.
         * <p><b>Experimental:</b> this part of CDP may change without notice.
         * @param enabled protocol value
         * @return a stage completing when the command completes
         */
        public CompletionStage<Void> setInterceptDrags(boolean enabled) {
            return call("Input.setInterceptDrags", params("enabled", enabled));
        }
        /**
         * Prevents default drag and drop behavior and instead emits {@code Input.dragIntercepted} events. Drag and drop behavior can be directly controlled via {@code Input.dispatchDragEvent}.
         * <p><b>Experimental:</b> this part of CDP may change without notice.
         * @param request request parameters
         * @return a stage completing when the command completes
         */
        public CompletionStage<Void> setInterceptDrags(SetInterceptDragsRequest request) {
            return call("Input.setInterceptDrags", request);
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
        public CompletionStage<Void> synthesizePinchGesture(double x, double y, double scaleFactor, @Nullable Long relativeSpeed, Input.@Nullable GestureSourceType gestureSourceType) {
            return call("Input.synthesizePinchGesture", params("x", x, "y", y, "scaleFactor", scaleFactor, "relativeSpeed", relativeSpeed, "gestureSourceType", gestureSourceType));
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
            return call("Input.synthesizePinchGesture", params("x", x, "y", y, "scaleFactor", scaleFactor));
        }
        /**
         * Synthesizes a pinch gesture over a time period by issuing appropriate touch events.
         * <p><b>Experimental:</b> this part of CDP may change without notice.
         * @param request request parameters
         * @return a stage completing when the command completes
         */
        public CompletionStage<Void> synthesizePinchGesture(SynthesizePinchGestureRequest request) {
            return call("Input.synthesizePinchGesture", request);
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
        public CompletionStage<Void> synthesizeScrollGesture(double x, double y, @Nullable Double xDistance, @Nullable Double yDistance, @Nullable Double xOverscroll, @Nullable Double yOverscroll, @Nullable Boolean preventFling, @Nullable Long speed, Input.@Nullable GestureSourceType gestureSourceType, @Nullable Long repeatCount, @Nullable Long repeatDelayMs, @Nullable String interactionMarkerName) {
            return call("Input.synthesizeScrollGesture", params("x", x, "y", y, "xDistance", xDistance, "yDistance", yDistance, "xOverscroll", xOverscroll, "yOverscroll", yOverscroll, "preventFling", preventFling, "speed", speed, "gestureSourceType", gestureSourceType, "repeatCount", repeatCount, "repeatDelayMs", repeatDelayMs, "interactionMarkerName", interactionMarkerName));
        }
        /**
         * Synthesizes a scroll gesture over a time period by issuing appropriate touch events.
         * <p><b>Experimental:</b> this part of CDP may change without notice.
         * @param x protocol value
         * @param y protocol value
         * @return a stage completing when the command completes
         */
        public CompletionStage<Void> synthesizeScrollGesture(double x, double y) {
            return call("Input.synthesizeScrollGesture", params("x", x, "y", y));
        }
        /**
         * Synthesizes a scroll gesture over a time period by issuing appropriate touch events.
         * <p><b>Experimental:</b> this part of CDP may change without notice.
         * @param request request parameters
         * @return a stage completing when the command completes
         */
        public CompletionStage<Void> synthesizeScrollGesture(SynthesizeScrollGestureRequest request) {
            return call("Input.synthesizeScrollGesture", request);
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
        public CompletionStage<Void> synthesizeTapGesture(double x, double y, @Nullable Long duration, @Nullable Long tapCount, Input.@Nullable GestureSourceType gestureSourceType) {
            return call("Input.synthesizeTapGesture", params("x", x, "y", y, "duration", duration, "tapCount", tapCount, "gestureSourceType", gestureSourceType));
        }
        /**
         * Synthesizes a tap gesture over a time period by issuing appropriate touch events.
         * <p><b>Experimental:</b> this part of CDP may change without notice.
         * @param x protocol value
         * @param y protocol value
         * @return a stage completing when the command completes
         */
        public CompletionStage<Void> synthesizeTapGesture(double x, double y) {
            return call("Input.synthesizeTapGesture", params("x", x, "y", y));
        }
        /**
         * Synthesizes a tap gesture over a time period by issuing appropriate touch events.
         * <p><b>Experimental:</b> this part of CDP may change without notice.
         * @param request request parameters
         * @return a stage completing when the command completes
         */
        public CompletionStage<Void> synthesizeTapGesture(SynthesizeTapGestureRequest request) {
            return call("Input.synthesizeTapGesture", request);
        }
        /**
         * Emitted only when {@code Input.setInterceptDrags} is enabled. Use this data with {@code Input.dispatchDragEvent} to restore normal drag and drop behavior.
         * <p><b>Experimental:</b> this part of CDP may change without notice.
         * @param handler event callback
         * @return a removable subscription
         */
        public CdpSubscription onDragIntercepted(Consumer<DragInterceptedEvent> handler) {
            return on("Input.dragIntercepted", DragInterceptedEvent::fromMap, handler);
        }
    }
}
