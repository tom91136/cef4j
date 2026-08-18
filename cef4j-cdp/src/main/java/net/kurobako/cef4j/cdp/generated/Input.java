// GENERATED - do not edit. Regenerate via: mvn generate-sources -pl cef4j-platform -Dcef.version=150.0.18+gdb11278+chromium-150.0.7871.213
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
@Generated("mvn generate-sources -pl cef4j-platform -Dcef.version=150.0.18+gdb11278+chromium-150.0.7871.213")
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
