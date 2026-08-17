// GENERATED - do not edit. Regenerate via: mvn generate-sources -pl cef4j-platform -Dcef.version=150.0.18+gdb11278+chromium-150.0.7871.213
package net.kurobako.cef4j.cdp.generated;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.concurrent.CompletionStage;
import java.util.function.Consumer;
import javax.annotation.Nullable;
import javax.annotation.processing.Generated;
import net.kurobako.cef4j.cdp.CdpClient;
import net.kurobako.cef4j.cdp.CdpObject;
import net.kurobako.cef4j.cdp.CdpSubscription;

/**
 * Chrome DevTools Protocol Input domain.
 * @see <a href="https://chromium.googlesource.com/chromium/src/+/refs/tags/150.0.7871.213/third_party/blink/public/devtools_protocol/domains/Input.pdl">Pinned protocol source</a>
 */
@Generated("mvn generate-sources -pl cef4j-platform -Dcef.version=150.0.18+gdb11278+chromium-150.0.7871.213")
@SuppressWarnings({"EscapedEntity", "JavaLangClash", "MissingSummary", "UnusedMethod"})
public final class Input {
    private Input() {}
    @Nullable private static Long numberAsLong(@Nullable Object value) { return value == null ? null : ((Number) value).longValue(); }
    @Nullable private static Double numberAsDouble(@Nullable Object value) { return value == null ? null : ((Number) value).doubleValue(); }
    /**
     */
    public static final class TouchPoint extends CdpObject {
        private TouchPoint(Map<String, Object> values) { super(values); }
        @Nullable public static TouchPoint fromMap(@Nullable Map<String, Object> values) {
            return values == null ? null : new TouchPoint(values);
        }
        public static Builder builder() { return new Builder(); }
        /**
         * X coordinate of the event relative to the main frame&#x27;s viewport in CSS pixels.
         * @return the protocol field value
         */
        @Nullable public Double x() {
            return numberAsDouble(value("x"));
        }
        /**
         * Y coordinate of the event relative to the main frame&#x27;s viewport in CSS pixels. 0 refers to the top of the viewport and Y increases as it proceeds towards the bottom of the viewport.
         * @return the protocol field value
         */
        @Nullable public Double y() {
            return numberAsDouble(value("y"));
        }
        /**
         * X radius of the touch area (default: 1.0).
         * @return the protocol field value
         */
        @Nullable public Double radiusX() {
            return numberAsDouble(value("radiusX"));
        }
        /**
         * Y radius of the touch area (default: 1.0).
         * @return the protocol field value
         */
        @Nullable public Double radiusY() {
            return numberAsDouble(value("radiusY"));
        }
        /**
         * Rotation angle (default: 0.0).
         * @return the protocol field value
         */
        @Nullable public Double rotationAngle() {
            return numberAsDouble(value("rotationAngle"));
        }
        /**
         * Force (default: 1.0).
         * @return the protocol field value
         */
        @Nullable public Double force() {
            return numberAsDouble(value("force"));
        }
        /**
         * The normalized tangential pressure, which has a range of [-1,1] (default: 0).
         * <p><b>Experimental:</b> this part of CDP may change without notice.
         * @return the protocol field value
         */
        @Nullable public Double tangentialPressure() {
            return numberAsDouble(value("tangentialPressure"));
        }
        /**
         * The plane angle between the Y-Z plane and the plane containing both the stylus axis and the Y axis, in degrees of the range [-90,90], a positive tiltX is to the right (default: 0)
         * @return the protocol field value
         */
        @Nullable public Double tiltX() {
            return numberAsDouble(value("tiltX"));
        }
        /**
         * The plane angle between the X-Z plane and the plane containing both the stylus axis and the X axis, in degrees of the range [-90,90], a positive tiltY is towards the user (default: 0).
         * @return the protocol field value
         */
        @Nullable public Double tiltY() {
            return numberAsDouble(value("tiltY"));
        }
        /**
         * The clockwise rotation of a pen stylus around its own major axis, in degrees in the range [0,359] (default: 0).
         * <p><b>Experimental:</b> this part of CDP may change without notice.
         * @return the protocol field value
         */
        @Nullable public Long twist() {
            return numberAsLong(value("twist"));
        }
        /**
         * Identifier used to track touch sources between events, must be unique within an event.
         * @return the protocol field value
         */
        @Nullable public Double id() {
            return numberAsDouble(value("id"));
        }
        public static final class Builder {
            private final Map<String, Object> values = new LinkedHashMap<>();
            /**
             * X coordinate of the event relative to the main frame&#x27;s viewport in CSS pixels.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder x(@Nullable Double value) {
                if (value == null) values.remove("x");
                else values.put("x", jsonValue(value));
                return this;
            }
            /**
             * Y coordinate of the event relative to the main frame&#x27;s viewport in CSS pixels. 0 refers to the top of the viewport and Y increases as it proceeds towards the bottom of the viewport.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder y(@Nullable Double value) {
                if (value == null) values.remove("y");
                else values.put("y", jsonValue(value));
                return this;
            }
            /**
             * X radius of the touch area (default: 1.0).
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder radiusX(@Nullable Double value) {
                if (value == null) values.remove("radiusX");
                else values.put("radiusX", jsonValue(value));
                return this;
            }
            /**
             * Y radius of the touch area (default: 1.0).
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder radiusY(@Nullable Double value) {
                if (value == null) values.remove("radiusY");
                else values.put("radiusY", jsonValue(value));
                return this;
            }
            /**
             * Rotation angle (default: 0.0).
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder rotationAngle(@Nullable Double value) {
                if (value == null) values.remove("rotationAngle");
                else values.put("rotationAngle", jsonValue(value));
                return this;
            }
            /**
             * Force (default: 1.0).
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder force(@Nullable Double value) {
                if (value == null) values.remove("force");
                else values.put("force", jsonValue(value));
                return this;
            }
            /**
             * The normalized tangential pressure, which has a range of [-1,1] (default: 0).
             * <p><b>Experimental:</b> this part of CDP may change without notice.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder tangentialPressure(@Nullable Double value) {
                if (value == null) values.remove("tangentialPressure");
                else values.put("tangentialPressure", jsonValue(value));
                return this;
            }
            /**
             * The plane angle between the Y-Z plane and the plane containing both the stylus axis and the Y axis, in degrees of the range [-90,90], a positive tiltX is to the right (default: 0)
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder tiltX(@Nullable Double value) {
                if (value == null) values.remove("tiltX");
                else values.put("tiltX", jsonValue(value));
                return this;
            }
            /**
             * The plane angle between the X-Z plane and the plane containing both the stylus axis and the X axis, in degrees of the range [-90,90], a positive tiltY is towards the user (default: 0).
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder tiltY(@Nullable Double value) {
                if (value == null) values.remove("tiltY");
                else values.put("tiltY", jsonValue(value));
                return this;
            }
            /**
             * The clockwise rotation of a pen stylus around its own major axis, in degrees in the range [0,359] (default: 0).
             * <p><b>Experimental:</b> this part of CDP may change without notice.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder twist(@Nullable Long value) {
                if (value == null) values.remove("twist");
                else values.put("twist", jsonValue(value));
                return this;
            }
            /**
             * Identifier used to track touch sources between events, must be unique within an event.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder id(@Nullable Double value) {
                if (value == null) values.remove("id");
                else values.put("id", jsonValue(value));
                return this;
            }
            public TouchPoint build() {
                if (!values.containsKey("x")) throw new IllegalStateException("Missing required CDP field: x");
                if (!values.containsKey("y")) throw new IllegalStateException("Missing required CDP field: y");
                return new TouchPoint(values);
            }
        }
    }
    /**
     * Wire values for GestureSourceType.
     * <p><b>Experimental:</b> this part of CDP may change without notice.
     */
    public static final class GestureSourceType {
        private GestureSourceType() {}
        public static final String DEFAULT = "default";
        public static final String TOUCH = "touch";
        public static final String MOUSE = "mouse";
    }
    /**
     * Wire values for MouseButton.
     */
    public static final class MouseButton {
        private MouseButton() {}
        public static final String NONE = "none";
        public static final String LEFT = "left";
        public static final String MIDDLE = "middle";
        public static final String RIGHT = "right";
        public static final String BACK = "back";
        public static final String FORWARD = "forward";
    }
    /**
     * <p><b>Experimental:</b> this part of CDP may change without notice.
     */
    public static final class DragDataItem extends CdpObject {
        private DragDataItem(Map<String, Object> values) { super(values); }
        @Nullable public static DragDataItem fromMap(@Nullable Map<String, Object> values) {
            return values == null ? null : new DragDataItem(values);
        }
        public static Builder builder() { return new Builder(); }
        /**
         * Mime type of the dragged data.
         * @return the protocol field value
         */
        @Nullable public String mimeType() {
            return (String) value("mimeType");
        }
        /**
         * Depending of the value of {@code mimeType}, it contains the dragged link, text, HTML markup or any other data.
         * @return the protocol field value
         */
        @Nullable public String data() {
            return (String) value("data");
        }
        /**
         * Title associated with a link. Only valid when {@code mimeType} == &quot;text/uri-list&quot;.
         * @return the protocol field value
         */
        @Nullable public String title() {
            return (String) value("title");
        }
        /**
         * Stores the base URL for the contained markup. Only valid when {@code mimeType} == &quot;text/html&quot;.
         * @return the protocol field value
         */
        @Nullable public String baseURL() {
            return (String) value("baseURL");
        }
        public static final class Builder {
            private final Map<String, Object> values = new LinkedHashMap<>();
            /**
             * Mime type of the dragged data.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder mimeType(@Nullable String value) {
                if (value == null) values.remove("mimeType");
                else values.put("mimeType", jsonValue(value));
                return this;
            }
            /**
             * Depending of the value of {@code mimeType}, it contains the dragged link, text, HTML markup or any other data.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder data(@Nullable String value) {
                if (value == null) values.remove("data");
                else values.put("data", jsonValue(value));
                return this;
            }
            /**
             * Title associated with a link. Only valid when {@code mimeType} == &quot;text/uri-list&quot;.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder title(@Nullable String value) {
                if (value == null) values.remove("title");
                else values.put("title", jsonValue(value));
                return this;
            }
            /**
             * Stores the base URL for the contained markup. Only valid when {@code mimeType} == &quot;text/html&quot;.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder baseURL(@Nullable String value) {
                if (value == null) values.remove("baseURL");
                else values.put("baseURL", jsonValue(value));
                return this;
            }
            public DragDataItem build() {
                if (!values.containsKey("mimeType")) throw new IllegalStateException("Missing required CDP field: mimeType");
                if (!values.containsKey("data")) throw new IllegalStateException("Missing required CDP field: data");
                return new DragDataItem(values);
            }
        }
    }
    /**
     * <p><b>Experimental:</b> this part of CDP may change without notice.
     */
    public static final class DragData extends CdpObject {
        private DragData(Map<String, Object> values) { super(values); }
        @Nullable public static DragData fromMap(@Nullable Map<String, Object> values) {
            return values == null ? null : new DragData(values);
        }
        public static Builder builder() { return new Builder(); }
        /**
         * Returns the items field.
         * @return the protocol field value
         */
        @Nullable public java.util.List<Input.DragDataItem> items() {
            return list(value("items"), element0 -> Input.DragDataItem.fromMap(objectMap(element0)));
        }
        /**
         * List of filenames that should be included when dropping
         * @return the protocol field value
         */
        @Nullable public java.util.List<String> files() {
            return list(value("files"), element0 -> (String) element0);
        }
        /**
         * Bit field representing allowed drag operations. Copy = 1, Link = 2, Move = 16
         * @return the protocol field value
         */
        @Nullable public Long dragOperationsMask() {
            return numberAsLong(value("dragOperationsMask"));
        }
        public static final class Builder {
            private final Map<String, Object> values = new LinkedHashMap<>();
            /**
             * Sets the items field.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder items(@Nullable java.util.List<Input.DragDataItem> value) {
                if (value == null) values.remove("items");
                else values.put("items", jsonValue(value));
                return this;
            }
            /**
             * List of filenames that should be included when dropping
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder files(@Nullable java.util.List<String> value) {
                if (value == null) values.remove("files");
                else values.put("files", jsonValue(value));
                return this;
            }
            /**
             * Bit field representing allowed drag operations. Copy = 1, Link = 2, Move = 16
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder dragOperationsMask(@Nullable Long value) {
                if (value == null) values.remove("dragOperationsMask");
                else values.put("dragOperationsMask", jsonValue(value));
                return this;
            }
            public DragData build() {
                if (!values.containsKey("items")) throw new IllegalStateException("Missing required CDP field: items");
                if (!values.containsKey("dragOperationsMask")) throw new IllegalStateException("Missing required CDP field: dragOperationsMask");
                return new DragData(values);
            }
        }
    }
    /**
     * Dispatches a drag event into the page.
     * <p><b>Experimental:</b> this part of CDP may change without notice.
     */
    public static final class DispatchDragEventParams extends CdpObject {
        private DispatchDragEventParams(Map<String, Object> values) { super(values); }
        @Nullable public static DispatchDragEventParams fromMap(@Nullable Map<String, Object> values) {
            return values == null ? null : new DispatchDragEventParams(values);
        }
        public static Builder builder() { return new Builder(); }
        /**
         * Type of the drag event.
         * @return the protocol field value
         */
        @Nullable public String type() {
            return (String) value("type");
        }
        /**
         * Type of the drag event.
         */
        public static final class TypeValues {
            private TypeValues() {}
            public static final String DRAGENTER = "dragEnter";
            public static final String DRAGOVER = "dragOver";
            public static final String DROP = "drop";
            public static final String DRAGCANCEL = "dragCancel";
        }
        /**
         * X coordinate of the event relative to the main frame&#x27;s viewport in CSS pixels.
         * @return the protocol field value
         */
        @Nullable public Double x() {
            return numberAsDouble(value("x"));
        }
        /**
         * Y coordinate of the event relative to the main frame&#x27;s viewport in CSS pixels. 0 refers to the top of the viewport and Y increases as it proceeds towards the bottom of the viewport.
         * @return the protocol field value
         */
        @Nullable public Double y() {
            return numberAsDouble(value("y"));
        }
        /**
         * Returns the data field.
         * @return the protocol field value
         */
        @Nullable public Input.DragData data() {
            return Input.DragData.fromMap(objectMap(value("data")));
        }
        /**
         * Bit field representing pressed modifier keys. Alt=1, Ctrl=2, Meta/Command=4, Shift=8 (default: 0).
         * @return the protocol field value
         */
        @Nullable public Long modifiers() {
            return numberAsLong(value("modifiers"));
        }
        public static final class Builder {
            private final Map<String, Object> values = new LinkedHashMap<>();
            /**
             * Type of the drag event.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder type(@Nullable String value) {
                if (value == null) values.remove("type");
                else values.put("type", jsonValue(value));
                return this;
            }
            /**
             * X coordinate of the event relative to the main frame&#x27;s viewport in CSS pixels.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder x(@Nullable Double value) {
                if (value == null) values.remove("x");
                else values.put("x", jsonValue(value));
                return this;
            }
            /**
             * Y coordinate of the event relative to the main frame&#x27;s viewport in CSS pixels. 0 refers to the top of the viewport and Y increases as it proceeds towards the bottom of the viewport.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder y(@Nullable Double value) {
                if (value == null) values.remove("y");
                else values.put("y", jsonValue(value));
                return this;
            }
            /**
             * Sets the data field.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder data(@Nullable Input.DragData value) {
                if (value == null) values.remove("data");
                else values.put("data", jsonValue(value));
                return this;
            }
            /**
             * Bit field representing pressed modifier keys. Alt=1, Ctrl=2, Meta/Command=4, Shift=8 (default: 0).
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder modifiers(@Nullable Long value) {
                if (value == null) values.remove("modifiers");
                else values.put("modifiers", jsonValue(value));
                return this;
            }
            public DispatchDragEventParams build() {
                if (!values.containsKey("type")) throw new IllegalStateException("Missing required CDP field: type");
                if (!values.containsKey("x")) throw new IllegalStateException("Missing required CDP field: x");
                if (!values.containsKey("y")) throw new IllegalStateException("Missing required CDP field: y");
                if (!values.containsKey("data")) throw new IllegalStateException("Missing required CDP field: data");
                return new DispatchDragEventParams(values);
            }
        }
    }
    /**
     * Dispatches a drag event into the page.
     * <p><b>Experimental:</b> this part of CDP may change without notice.
     */
    public static final class DispatchDragEventResult extends CdpObject {
        private DispatchDragEventResult(Map<String, Object> values) { super(values); }
        @Nullable public static DispatchDragEventResult fromMap(@Nullable Map<String, Object> values) {
            return values == null ? null : new DispatchDragEventResult(values);
        }
        public static Builder builder() { return new Builder(); }
        public static final class Builder {
            private final Map<String, Object> values = new LinkedHashMap<>();
            public DispatchDragEventResult build() {
                return new DispatchDragEventResult(values);
            }
        }
    }
    /**
     * Dispatches a key event to the page.
     */
    public static final class DispatchKeyEventParams extends CdpObject {
        private DispatchKeyEventParams(Map<String, Object> values) { super(values); }
        @Nullable public static DispatchKeyEventParams fromMap(@Nullable Map<String, Object> values) {
            return values == null ? null : new DispatchKeyEventParams(values);
        }
        public static Builder builder() { return new Builder(); }
        /**
         * Type of the key event.
         * @return the protocol field value
         */
        @Nullable public String type() {
            return (String) value("type");
        }
        /**
         * Type of the key event.
         */
        public static final class TypeValues {
            private TypeValues() {}
            public static final String KEYDOWN = "keyDown";
            public static final String KEYUP = "keyUp";
            public static final String RAWKEYDOWN = "rawKeyDown";
            public static final String CHAR = "char";
        }
        /**
         * Bit field representing pressed modifier keys. Alt=1, Ctrl=2, Meta/Command=4, Shift=8 (default: 0).
         * @return the protocol field value
         */
        @Nullable public Long modifiers() {
            return numberAsLong(value("modifiers"));
        }
        /**
         * Time at which the event occurred.
         * @return the protocol field value
         */
        @Nullable public Double timestamp() {
            return numberAsDouble(value("timestamp"));
        }
        /**
         * Text as generated by processing a virtual key code with a keyboard layout. Not needed for for {@code keyUp} and {@code rawKeyDown} events (default: &quot;&quot;)
         * @return the protocol field value
         */
        @Nullable public String text() {
            return (String) value("text");
        }
        /**
         * Text that would have been generated by the keyboard if no modifiers were pressed (except for shift). Useful for shortcut (accelerator) key handling (default: &quot;&quot;).
         * @return the protocol field value
         */
        @Nullable public String unmodifiedText() {
            return (String) value("unmodifiedText");
        }
        /**
         * Unique key identifier (e.g., &#x27;U+0041&#x27;) (default: &quot;&quot;).
         * @return the protocol field value
         */
        @Nullable public String keyIdentifier() {
            return (String) value("keyIdentifier");
        }
        /**
         * Unique DOM defined string value for each physical key (e.g., &#x27;KeyA&#x27;) (default: &quot;&quot;).
         * @return the protocol field value
         */
        @Nullable public String code() {
            return (String) value("code");
        }
        /**
         * Unique DOM defined string value describing the meaning of the key in the context of active modifiers, keyboard layout, etc (e.g., &#x27;AltGr&#x27;) (default: &quot;&quot;).
         * @return the protocol field value
         */
        @Nullable public String key() {
            return (String) value("key");
        }
        /**
         * Windows virtual key code (default: 0).
         * @return the protocol field value
         */
        @Nullable public Long windowsVirtualKeyCode() {
            return numberAsLong(value("windowsVirtualKeyCode"));
        }
        /**
         * Native virtual key code (default: 0).
         * @return the protocol field value
         */
        @Nullable public Long nativeVirtualKeyCode() {
            return numberAsLong(value("nativeVirtualKeyCode"));
        }
        /**
         * Whether the event was generated from auto repeat (default: false).
         * @return the protocol field value
         */
        @Nullable public Boolean autoRepeat() {
            return (Boolean) value("autoRepeat");
        }
        /**
         * Whether the event was generated from the keypad (default: false).
         * @return the protocol field value
         */
        @Nullable public Boolean isKeypad() {
            return (Boolean) value("isKeypad");
        }
        /**
         * Whether the event was a system key event (default: false).
         * @return the protocol field value
         */
        @Nullable public Boolean isSystemKey() {
            return (Boolean) value("isSystemKey");
        }
        /**
         * Whether the event was from the left or right side of the keyboard. 1=Left, 2=Right (default: 0).
         * @return the protocol field value
         */
        @Nullable public Long location() {
            return numberAsLong(value("location"));
        }
        /**
         * Editing commands to send with the key event (e.g., &#x27;selectAll&#x27;) (default: []). These are related to but not equal the command names used in {@code document.execCommand} and NSStandardKeyBindingResponding. See https://source.chromium.org/chromium/chromium/src/+/main:third_party/blink/renderer/core/editing/commands/editor_command_names.h for valid command names.
         * <p><b>Experimental:</b> this part of CDP may change without notice.
         * @return the protocol field value
         */
        @Nullable public java.util.List<String> commands() {
            return list(value("commands"), element0 -> (String) element0);
        }
        public static final class Builder {
            private final Map<String, Object> values = new LinkedHashMap<>();
            /**
             * Type of the key event.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder type(@Nullable String value) {
                if (value == null) values.remove("type");
                else values.put("type", jsonValue(value));
                return this;
            }
            /**
             * Bit field representing pressed modifier keys. Alt=1, Ctrl=2, Meta/Command=4, Shift=8 (default: 0).
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder modifiers(@Nullable Long value) {
                if (value == null) values.remove("modifiers");
                else values.put("modifiers", jsonValue(value));
                return this;
            }
            /**
             * Time at which the event occurred.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder timestamp(@Nullable Double value) {
                if (value == null) values.remove("timestamp");
                else values.put("timestamp", jsonValue(value));
                return this;
            }
            /**
             * Text as generated by processing a virtual key code with a keyboard layout. Not needed for for {@code keyUp} and {@code rawKeyDown} events (default: &quot;&quot;)
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder text(@Nullable String value) {
                if (value == null) values.remove("text");
                else values.put("text", jsonValue(value));
                return this;
            }
            /**
             * Text that would have been generated by the keyboard if no modifiers were pressed (except for shift). Useful for shortcut (accelerator) key handling (default: &quot;&quot;).
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder unmodifiedText(@Nullable String value) {
                if (value == null) values.remove("unmodifiedText");
                else values.put("unmodifiedText", jsonValue(value));
                return this;
            }
            /**
             * Unique key identifier (e.g., &#x27;U+0041&#x27;) (default: &quot;&quot;).
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder keyIdentifier(@Nullable String value) {
                if (value == null) values.remove("keyIdentifier");
                else values.put("keyIdentifier", jsonValue(value));
                return this;
            }
            /**
             * Unique DOM defined string value for each physical key (e.g., &#x27;KeyA&#x27;) (default: &quot;&quot;).
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder code(@Nullable String value) {
                if (value == null) values.remove("code");
                else values.put("code", jsonValue(value));
                return this;
            }
            /**
             * Unique DOM defined string value describing the meaning of the key in the context of active modifiers, keyboard layout, etc (e.g., &#x27;AltGr&#x27;) (default: &quot;&quot;).
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder key(@Nullable String value) {
                if (value == null) values.remove("key");
                else values.put("key", jsonValue(value));
                return this;
            }
            /**
             * Windows virtual key code (default: 0).
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder windowsVirtualKeyCode(@Nullable Long value) {
                if (value == null) values.remove("windowsVirtualKeyCode");
                else values.put("windowsVirtualKeyCode", jsonValue(value));
                return this;
            }
            /**
             * Native virtual key code (default: 0).
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder nativeVirtualKeyCode(@Nullable Long value) {
                if (value == null) values.remove("nativeVirtualKeyCode");
                else values.put("nativeVirtualKeyCode", jsonValue(value));
                return this;
            }
            /**
             * Whether the event was generated from auto repeat (default: false).
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder autoRepeat(@Nullable Boolean value) {
                if (value == null) values.remove("autoRepeat");
                else values.put("autoRepeat", jsonValue(value));
                return this;
            }
            /**
             * Whether the event was generated from the keypad (default: false).
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder isKeypad(@Nullable Boolean value) {
                if (value == null) values.remove("isKeypad");
                else values.put("isKeypad", jsonValue(value));
                return this;
            }
            /**
             * Whether the event was a system key event (default: false).
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder isSystemKey(@Nullable Boolean value) {
                if (value == null) values.remove("isSystemKey");
                else values.put("isSystemKey", jsonValue(value));
                return this;
            }
            /**
             * Whether the event was from the left or right side of the keyboard. 1=Left, 2=Right (default: 0).
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder location(@Nullable Long value) {
                if (value == null) values.remove("location");
                else values.put("location", jsonValue(value));
                return this;
            }
            /**
             * Editing commands to send with the key event (e.g., &#x27;selectAll&#x27;) (default: []). These are related to but not equal the command names used in {@code document.execCommand} and NSStandardKeyBindingResponding. See https://source.chromium.org/chromium/chromium/src/+/main:third_party/blink/renderer/core/editing/commands/editor_command_names.h for valid command names.
             * <p><b>Experimental:</b> this part of CDP may change without notice.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder commands(@Nullable java.util.List<String> value) {
                if (value == null) values.remove("commands");
                else values.put("commands", jsonValue(value));
                return this;
            }
            public DispatchKeyEventParams build() {
                if (!values.containsKey("type")) throw new IllegalStateException("Missing required CDP field: type");
                return new DispatchKeyEventParams(values);
            }
        }
    }
    /**
     * Dispatches a key event to the page.
     */
    public static final class DispatchKeyEventResult extends CdpObject {
        private DispatchKeyEventResult(Map<String, Object> values) { super(values); }
        @Nullable public static DispatchKeyEventResult fromMap(@Nullable Map<String, Object> values) {
            return values == null ? null : new DispatchKeyEventResult(values);
        }
        public static Builder builder() { return new Builder(); }
        public static final class Builder {
            private final Map<String, Object> values = new LinkedHashMap<>();
            public DispatchKeyEventResult build() {
                return new DispatchKeyEventResult(values);
            }
        }
    }
    /**
     * This method emulates inserting text that doesn&#x27;t come from a key press, for example an emoji keyboard or an IME.
     * <p><b>Experimental:</b> this part of CDP may change without notice.
     */
    public static final class InsertTextParams extends CdpObject {
        private InsertTextParams(Map<String, Object> values) { super(values); }
        @Nullable public static InsertTextParams fromMap(@Nullable Map<String, Object> values) {
            return values == null ? null : new InsertTextParams(values);
        }
        public static Builder builder() { return new Builder(); }
        /**
         * The text to insert.
         * @return the protocol field value
         */
        @Nullable public String text() {
            return (String) value("text");
        }
        public static final class Builder {
            private final Map<String, Object> values = new LinkedHashMap<>();
            /**
             * The text to insert.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder text(@Nullable String value) {
                if (value == null) values.remove("text");
                else values.put("text", jsonValue(value));
                return this;
            }
            public InsertTextParams build() {
                if (!values.containsKey("text")) throw new IllegalStateException("Missing required CDP field: text");
                return new InsertTextParams(values);
            }
        }
    }
    /**
     * This method emulates inserting text that doesn&#x27;t come from a key press, for example an emoji keyboard or an IME.
     * <p><b>Experimental:</b> this part of CDP may change without notice.
     */
    public static final class InsertTextResult extends CdpObject {
        private InsertTextResult(Map<String, Object> values) { super(values); }
        @Nullable public static InsertTextResult fromMap(@Nullable Map<String, Object> values) {
            return values == null ? null : new InsertTextResult(values);
        }
        public static Builder builder() { return new Builder(); }
        public static final class Builder {
            private final Map<String, Object> values = new LinkedHashMap<>();
            public InsertTextResult build() {
                return new InsertTextResult(values);
            }
        }
    }
    /**
     * This method sets the current candidate text for IME. Use imeCommitComposition to commit the final text. Use imeSetComposition with empty string as text to cancel composition.
     * <p><b>Experimental:</b> this part of CDP may change without notice.
     */
    public static final class ImeSetCompositionParams extends CdpObject {
        private ImeSetCompositionParams(Map<String, Object> values) { super(values); }
        @Nullable public static ImeSetCompositionParams fromMap(@Nullable Map<String, Object> values) {
            return values == null ? null : new ImeSetCompositionParams(values);
        }
        public static Builder builder() { return new Builder(); }
        /**
         * The text to insert
         * @return the protocol field value
         */
        @Nullable public String text() {
            return (String) value("text");
        }
        /**
         * selection start
         * @return the protocol field value
         */
        @Nullable public Long selectionStart() {
            return numberAsLong(value("selectionStart"));
        }
        /**
         * selection end
         * @return the protocol field value
         */
        @Nullable public Long selectionEnd() {
            return numberAsLong(value("selectionEnd"));
        }
        /**
         * replacement start
         * @return the protocol field value
         */
        @Nullable public Long replacementStart() {
            return numberAsLong(value("replacementStart"));
        }
        /**
         * replacement end
         * @return the protocol field value
         */
        @Nullable public Long replacementEnd() {
            return numberAsLong(value("replacementEnd"));
        }
        public static final class Builder {
            private final Map<String, Object> values = new LinkedHashMap<>();
            /**
             * The text to insert
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder text(@Nullable String value) {
                if (value == null) values.remove("text");
                else values.put("text", jsonValue(value));
                return this;
            }
            /**
             * selection start
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder selectionStart(@Nullable Long value) {
                if (value == null) values.remove("selectionStart");
                else values.put("selectionStart", jsonValue(value));
                return this;
            }
            /**
             * selection end
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder selectionEnd(@Nullable Long value) {
                if (value == null) values.remove("selectionEnd");
                else values.put("selectionEnd", jsonValue(value));
                return this;
            }
            /**
             * replacement start
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder replacementStart(@Nullable Long value) {
                if (value == null) values.remove("replacementStart");
                else values.put("replacementStart", jsonValue(value));
                return this;
            }
            /**
             * replacement end
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder replacementEnd(@Nullable Long value) {
                if (value == null) values.remove("replacementEnd");
                else values.put("replacementEnd", jsonValue(value));
                return this;
            }
            public ImeSetCompositionParams build() {
                if (!values.containsKey("text")) throw new IllegalStateException("Missing required CDP field: text");
                if (!values.containsKey("selectionStart")) throw new IllegalStateException("Missing required CDP field: selectionStart");
                if (!values.containsKey("selectionEnd")) throw new IllegalStateException("Missing required CDP field: selectionEnd");
                return new ImeSetCompositionParams(values);
            }
        }
    }
    /**
     * This method sets the current candidate text for IME. Use imeCommitComposition to commit the final text. Use imeSetComposition with empty string as text to cancel composition.
     * <p><b>Experimental:</b> this part of CDP may change without notice.
     */
    public static final class ImeSetCompositionResult extends CdpObject {
        private ImeSetCompositionResult(Map<String, Object> values) { super(values); }
        @Nullable public static ImeSetCompositionResult fromMap(@Nullable Map<String, Object> values) {
            return values == null ? null : new ImeSetCompositionResult(values);
        }
        public static Builder builder() { return new Builder(); }
        public static final class Builder {
            private final Map<String, Object> values = new LinkedHashMap<>();
            public ImeSetCompositionResult build() {
                return new ImeSetCompositionResult(values);
            }
        }
    }
    /**
     * Dispatches a mouse event to the page.
     */
    public static final class DispatchMouseEventParams extends CdpObject {
        private DispatchMouseEventParams(Map<String, Object> values) { super(values); }
        @Nullable public static DispatchMouseEventParams fromMap(@Nullable Map<String, Object> values) {
            return values == null ? null : new DispatchMouseEventParams(values);
        }
        public static Builder builder() { return new Builder(); }
        /**
         * Type of the mouse event.
         * @return the protocol field value
         */
        @Nullable public String type() {
            return (String) value("type");
        }
        /**
         * Type of the mouse event.
         */
        public static final class TypeValues {
            private TypeValues() {}
            public static final String MOUSEPRESSED = "mousePressed";
            public static final String MOUSERELEASED = "mouseReleased";
            public static final String MOUSEMOVED = "mouseMoved";
            public static final String MOUSEWHEEL = "mouseWheel";
        }
        /**
         * X coordinate of the event relative to the main frame&#x27;s viewport in CSS pixels.
         * @return the protocol field value
         */
        @Nullable public Double x() {
            return numberAsDouble(value("x"));
        }
        /**
         * Y coordinate of the event relative to the main frame&#x27;s viewport in CSS pixels. 0 refers to the top of the viewport and Y increases as it proceeds towards the bottom of the viewport.
         * @return the protocol field value
         */
        @Nullable public Double y() {
            return numberAsDouble(value("y"));
        }
        /**
         * Bit field representing pressed modifier keys. Alt=1, Ctrl=2, Meta/Command=4, Shift=8 (default: 0).
         * @return the protocol field value
         */
        @Nullable public Long modifiers() {
            return numberAsLong(value("modifiers"));
        }
        /**
         * Time at which the event occurred.
         * @return the protocol field value
         */
        @Nullable public Double timestamp() {
            return numberAsDouble(value("timestamp"));
        }
        /**
         * Mouse button (default: &quot;none&quot;).
         * @return the protocol field value
         */
        @Nullable public String button() {
            return (String) value("button");
        }
        /**
         * A number indicating which buttons are pressed on the mouse when a mouse event is triggered. Left=1, Right=2, Middle=4, Back=8, Forward=16, None=0.
         * @return the protocol field value
         */
        @Nullable public Long buttons() {
            return numberAsLong(value("buttons"));
        }
        /**
         * Number of times the mouse button was clicked (default: 0).
         * @return the protocol field value
         */
        @Nullable public Long clickCount() {
            return numberAsLong(value("clickCount"));
        }
        /**
         * The normalized pressure, which has a range of [0,1] (default: 0).
         * <p><b>Experimental:</b> this part of CDP may change without notice.
         * @return the protocol field value
         */
        @Nullable public Double force() {
            return numberAsDouble(value("force"));
        }
        /**
         * The normalized tangential pressure, which has a range of [-1,1] (default: 0).
         * <p><b>Experimental:</b> this part of CDP may change without notice.
         * @return the protocol field value
         */
        @Nullable public Double tangentialPressure() {
            return numberAsDouble(value("tangentialPressure"));
        }
        /**
         * The plane angle between the Y-Z plane and the plane containing both the stylus axis and the Y axis, in degrees of the range [-90,90], a positive tiltX is to the right (default: 0).
         * @return the protocol field value
         */
        @Nullable public Double tiltX() {
            return numberAsDouble(value("tiltX"));
        }
        /**
         * The plane angle between the X-Z plane and the plane containing both the stylus axis and the X axis, in degrees of the range [-90,90], a positive tiltY is towards the user (default: 0).
         * @return the protocol field value
         */
        @Nullable public Double tiltY() {
            return numberAsDouble(value("tiltY"));
        }
        /**
         * The clockwise rotation of a pen stylus around its own major axis, in degrees in the range [0,359] (default: 0).
         * <p><b>Experimental:</b> this part of CDP may change without notice.
         * @return the protocol field value
         */
        @Nullable public Long twist() {
            return numberAsLong(value("twist"));
        }
        /**
         * X delta in CSS pixels for mouse wheel event (default: 0).
         * @return the protocol field value
         */
        @Nullable public Double deltaX() {
            return numberAsDouble(value("deltaX"));
        }
        /**
         * Y delta in CSS pixels for mouse wheel event (default: 0).
         * @return the protocol field value
         */
        @Nullable public Double deltaY() {
            return numberAsDouble(value("deltaY"));
        }
        /**
         * Pointer type (default: &quot;mouse&quot;).
         * @return the protocol field value
         */
        @Nullable public String pointerType() {
            return (String) value("pointerType");
        }
        /**
         * Pointer type (default: &quot;mouse&quot;).
         */
        public static final class PointerTypeValues {
            private PointerTypeValues() {}
            public static final String MOUSE = "mouse";
            public static final String PEN = "pen";
        }
        public static final class Builder {
            private final Map<String, Object> values = new LinkedHashMap<>();
            /**
             * Type of the mouse event.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder type(@Nullable String value) {
                if (value == null) values.remove("type");
                else values.put("type", jsonValue(value));
                return this;
            }
            /**
             * X coordinate of the event relative to the main frame&#x27;s viewport in CSS pixels.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder x(@Nullable Double value) {
                if (value == null) values.remove("x");
                else values.put("x", jsonValue(value));
                return this;
            }
            /**
             * Y coordinate of the event relative to the main frame&#x27;s viewport in CSS pixels. 0 refers to the top of the viewport and Y increases as it proceeds towards the bottom of the viewport.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder y(@Nullable Double value) {
                if (value == null) values.remove("y");
                else values.put("y", jsonValue(value));
                return this;
            }
            /**
             * Bit field representing pressed modifier keys. Alt=1, Ctrl=2, Meta/Command=4, Shift=8 (default: 0).
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder modifiers(@Nullable Long value) {
                if (value == null) values.remove("modifiers");
                else values.put("modifiers", jsonValue(value));
                return this;
            }
            /**
             * Time at which the event occurred.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder timestamp(@Nullable Double value) {
                if (value == null) values.remove("timestamp");
                else values.put("timestamp", jsonValue(value));
                return this;
            }
            /**
             * Mouse button (default: &quot;none&quot;).
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder button(@Nullable String value) {
                if (value == null) values.remove("button");
                else values.put("button", jsonValue(value));
                return this;
            }
            /**
             * A number indicating which buttons are pressed on the mouse when a mouse event is triggered. Left=1, Right=2, Middle=4, Back=8, Forward=16, None=0.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder buttons(@Nullable Long value) {
                if (value == null) values.remove("buttons");
                else values.put("buttons", jsonValue(value));
                return this;
            }
            /**
             * Number of times the mouse button was clicked (default: 0).
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder clickCount(@Nullable Long value) {
                if (value == null) values.remove("clickCount");
                else values.put("clickCount", jsonValue(value));
                return this;
            }
            /**
             * The normalized pressure, which has a range of [0,1] (default: 0).
             * <p><b>Experimental:</b> this part of CDP may change without notice.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder force(@Nullable Double value) {
                if (value == null) values.remove("force");
                else values.put("force", jsonValue(value));
                return this;
            }
            /**
             * The normalized tangential pressure, which has a range of [-1,1] (default: 0).
             * <p><b>Experimental:</b> this part of CDP may change without notice.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder tangentialPressure(@Nullable Double value) {
                if (value == null) values.remove("tangentialPressure");
                else values.put("tangentialPressure", jsonValue(value));
                return this;
            }
            /**
             * The plane angle between the Y-Z plane and the plane containing both the stylus axis and the Y axis, in degrees of the range [-90,90], a positive tiltX is to the right (default: 0).
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder tiltX(@Nullable Double value) {
                if (value == null) values.remove("tiltX");
                else values.put("tiltX", jsonValue(value));
                return this;
            }
            /**
             * The plane angle between the X-Z plane and the plane containing both the stylus axis and the X axis, in degrees of the range [-90,90], a positive tiltY is towards the user (default: 0).
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder tiltY(@Nullable Double value) {
                if (value == null) values.remove("tiltY");
                else values.put("tiltY", jsonValue(value));
                return this;
            }
            /**
             * The clockwise rotation of a pen stylus around its own major axis, in degrees in the range [0,359] (default: 0).
             * <p><b>Experimental:</b> this part of CDP may change without notice.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder twist(@Nullable Long value) {
                if (value == null) values.remove("twist");
                else values.put("twist", jsonValue(value));
                return this;
            }
            /**
             * X delta in CSS pixels for mouse wheel event (default: 0).
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder deltaX(@Nullable Double value) {
                if (value == null) values.remove("deltaX");
                else values.put("deltaX", jsonValue(value));
                return this;
            }
            /**
             * Y delta in CSS pixels for mouse wheel event (default: 0).
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder deltaY(@Nullable Double value) {
                if (value == null) values.remove("deltaY");
                else values.put("deltaY", jsonValue(value));
                return this;
            }
            /**
             * Pointer type (default: &quot;mouse&quot;).
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder pointerType(@Nullable String value) {
                if (value == null) values.remove("pointerType");
                else values.put("pointerType", jsonValue(value));
                return this;
            }
            public DispatchMouseEventParams build() {
                if (!values.containsKey("type")) throw new IllegalStateException("Missing required CDP field: type");
                if (!values.containsKey("x")) throw new IllegalStateException("Missing required CDP field: x");
                if (!values.containsKey("y")) throw new IllegalStateException("Missing required CDP field: y");
                return new DispatchMouseEventParams(values);
            }
        }
    }
    /**
     * Dispatches a mouse event to the page.
     */
    public static final class DispatchMouseEventResult extends CdpObject {
        private DispatchMouseEventResult(Map<String, Object> values) { super(values); }
        @Nullable public static DispatchMouseEventResult fromMap(@Nullable Map<String, Object> values) {
            return values == null ? null : new DispatchMouseEventResult(values);
        }
        public static Builder builder() { return new Builder(); }
        public static final class Builder {
            private final Map<String, Object> values = new LinkedHashMap<>();
            public DispatchMouseEventResult build() {
                return new DispatchMouseEventResult(values);
            }
        }
    }
    /**
     * Dispatches a touch event to the page.
     */
    public static final class DispatchTouchEventParams extends CdpObject {
        private DispatchTouchEventParams(Map<String, Object> values) { super(values); }
        @Nullable public static DispatchTouchEventParams fromMap(@Nullable Map<String, Object> values) {
            return values == null ? null : new DispatchTouchEventParams(values);
        }
        public static Builder builder() { return new Builder(); }
        /**
         * Type of the touch event. TouchEnd and TouchCancel must not contain any touch points, while TouchStart and TouchMove must contains at least one.
         * @return the protocol field value
         */
        @Nullable public String type() {
            return (String) value("type");
        }
        /**
         * Type of the touch event. TouchEnd and TouchCancel must not contain any touch points, while TouchStart and TouchMove must contains at least one.
         */
        public static final class TypeValues {
            private TypeValues() {}
            public static final String TOUCHSTART = "touchStart";
            public static final String TOUCHEND = "touchEnd";
            public static final String TOUCHMOVE = "touchMove";
            public static final String TOUCHCANCEL = "touchCancel";
        }
        /**
         * Active touch points on the touch device. One event per any changed point (compared to previous touch event in a sequence) is generated, emulating pressing/moving/releasing points one by one.
         * @return the protocol field value
         */
        @Nullable public java.util.List<Input.TouchPoint> touchPoints() {
            return list(value("touchPoints"), element0 -> Input.TouchPoint.fromMap(objectMap(element0)));
        }
        /**
         * Bit field representing pressed modifier keys. Alt=1, Ctrl=2, Meta/Command=4, Shift=8 (default: 0).
         * @return the protocol field value
         */
        @Nullable public Long modifiers() {
            return numberAsLong(value("modifiers"));
        }
        /**
         * Time at which the event occurred.
         * @return the protocol field value
         */
        @Nullable public Double timestamp() {
            return numberAsDouble(value("timestamp"));
        }
        public static final class Builder {
            private final Map<String, Object> values = new LinkedHashMap<>();
            /**
             * Type of the touch event. TouchEnd and TouchCancel must not contain any touch points, while TouchStart and TouchMove must contains at least one.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder type(@Nullable String value) {
                if (value == null) values.remove("type");
                else values.put("type", jsonValue(value));
                return this;
            }
            /**
             * Active touch points on the touch device. One event per any changed point (compared to previous touch event in a sequence) is generated, emulating pressing/moving/releasing points one by one.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder touchPoints(@Nullable java.util.List<Input.TouchPoint> value) {
                if (value == null) values.remove("touchPoints");
                else values.put("touchPoints", jsonValue(value));
                return this;
            }
            /**
             * Bit field representing pressed modifier keys. Alt=1, Ctrl=2, Meta/Command=4, Shift=8 (default: 0).
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder modifiers(@Nullable Long value) {
                if (value == null) values.remove("modifiers");
                else values.put("modifiers", jsonValue(value));
                return this;
            }
            /**
             * Time at which the event occurred.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder timestamp(@Nullable Double value) {
                if (value == null) values.remove("timestamp");
                else values.put("timestamp", jsonValue(value));
                return this;
            }
            public DispatchTouchEventParams build() {
                if (!values.containsKey("type")) throw new IllegalStateException("Missing required CDP field: type");
                if (!values.containsKey("touchPoints")) throw new IllegalStateException("Missing required CDP field: touchPoints");
                return new DispatchTouchEventParams(values);
            }
        }
    }
    /**
     * Dispatches a touch event to the page.
     */
    public static final class DispatchTouchEventResult extends CdpObject {
        private DispatchTouchEventResult(Map<String, Object> values) { super(values); }
        @Nullable public static DispatchTouchEventResult fromMap(@Nullable Map<String, Object> values) {
            return values == null ? null : new DispatchTouchEventResult(values);
        }
        public static Builder builder() { return new Builder(); }
        public static final class Builder {
            private final Map<String, Object> values = new LinkedHashMap<>();
            public DispatchTouchEventResult build() {
                return new DispatchTouchEventResult(values);
            }
        }
    }
    /**
     * Cancels any active dragging in the page.
     */
    public static final class CancelDraggingParams extends CdpObject {
        private CancelDraggingParams(Map<String, Object> values) { super(values); }
        @Nullable public static CancelDraggingParams fromMap(@Nullable Map<String, Object> values) {
            return values == null ? null : new CancelDraggingParams(values);
        }
        public static Builder builder() { return new Builder(); }
        public static final class Builder {
            private final Map<String, Object> values = new LinkedHashMap<>();
            public CancelDraggingParams build() {
                return new CancelDraggingParams(values);
            }
        }
    }
    /**
     * Cancels any active dragging in the page.
     */
    public static final class CancelDraggingResult extends CdpObject {
        private CancelDraggingResult(Map<String, Object> values) { super(values); }
        @Nullable public static CancelDraggingResult fromMap(@Nullable Map<String, Object> values) {
            return values == null ? null : new CancelDraggingResult(values);
        }
        public static Builder builder() { return new Builder(); }
        public static final class Builder {
            private final Map<String, Object> values = new LinkedHashMap<>();
            public CancelDraggingResult build() {
                return new CancelDraggingResult(values);
            }
        }
    }
    /**
     * Emulates touch event from the mouse event parameters.
     * <p><b>Experimental:</b> this part of CDP may change without notice.
     */
    public static final class EmulateTouchFromMouseEventParams extends CdpObject {
        private EmulateTouchFromMouseEventParams(Map<String, Object> values) { super(values); }
        @Nullable public static EmulateTouchFromMouseEventParams fromMap(@Nullable Map<String, Object> values) {
            return values == null ? null : new EmulateTouchFromMouseEventParams(values);
        }
        public static Builder builder() { return new Builder(); }
        /**
         * Type of the mouse event.
         * @return the protocol field value
         */
        @Nullable public String type() {
            return (String) value("type");
        }
        /**
         * Type of the mouse event.
         */
        public static final class TypeValues {
            private TypeValues() {}
            public static final String MOUSEPRESSED = "mousePressed";
            public static final String MOUSERELEASED = "mouseReleased";
            public static final String MOUSEMOVED = "mouseMoved";
            public static final String MOUSEWHEEL = "mouseWheel";
        }
        /**
         * X coordinate of the mouse pointer in DIP.
         * @return the protocol field value
         */
        @Nullable public Long x() {
            return numberAsLong(value("x"));
        }
        /**
         * Y coordinate of the mouse pointer in DIP.
         * @return the protocol field value
         */
        @Nullable public Long y() {
            return numberAsLong(value("y"));
        }
        /**
         * Mouse button. Only &quot;none&quot;, &quot;left&quot;, &quot;right&quot; are supported.
         * @return the protocol field value
         */
        @Nullable public String button() {
            return (String) value("button");
        }
        /**
         * Time at which the event occurred (default: current time).
         * @return the protocol field value
         */
        @Nullable public Double timestamp() {
            return numberAsDouble(value("timestamp"));
        }
        /**
         * X delta in DIP for mouse wheel event (default: 0).
         * @return the protocol field value
         */
        @Nullable public Double deltaX() {
            return numberAsDouble(value("deltaX"));
        }
        /**
         * Y delta in DIP for mouse wheel event (default: 0).
         * @return the protocol field value
         */
        @Nullable public Double deltaY() {
            return numberAsDouble(value("deltaY"));
        }
        /**
         * Bit field representing pressed modifier keys. Alt=1, Ctrl=2, Meta/Command=4, Shift=8 (default: 0).
         * @return the protocol field value
         */
        @Nullable public Long modifiers() {
            return numberAsLong(value("modifiers"));
        }
        /**
         * Number of times the mouse button was clicked (default: 0).
         * @return the protocol field value
         */
        @Nullable public Long clickCount() {
            return numberAsLong(value("clickCount"));
        }
        public static final class Builder {
            private final Map<String, Object> values = new LinkedHashMap<>();
            /**
             * Type of the mouse event.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder type(@Nullable String value) {
                if (value == null) values.remove("type");
                else values.put("type", jsonValue(value));
                return this;
            }
            /**
             * X coordinate of the mouse pointer in DIP.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder x(@Nullable Long value) {
                if (value == null) values.remove("x");
                else values.put("x", jsonValue(value));
                return this;
            }
            /**
             * Y coordinate of the mouse pointer in DIP.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder y(@Nullable Long value) {
                if (value == null) values.remove("y");
                else values.put("y", jsonValue(value));
                return this;
            }
            /**
             * Mouse button. Only &quot;none&quot;, &quot;left&quot;, &quot;right&quot; are supported.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder button(@Nullable String value) {
                if (value == null) values.remove("button");
                else values.put("button", jsonValue(value));
                return this;
            }
            /**
             * Time at which the event occurred (default: current time).
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder timestamp(@Nullable Double value) {
                if (value == null) values.remove("timestamp");
                else values.put("timestamp", jsonValue(value));
                return this;
            }
            /**
             * X delta in DIP for mouse wheel event (default: 0).
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder deltaX(@Nullable Double value) {
                if (value == null) values.remove("deltaX");
                else values.put("deltaX", jsonValue(value));
                return this;
            }
            /**
             * Y delta in DIP for mouse wheel event (default: 0).
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder deltaY(@Nullable Double value) {
                if (value == null) values.remove("deltaY");
                else values.put("deltaY", jsonValue(value));
                return this;
            }
            /**
             * Bit field representing pressed modifier keys. Alt=1, Ctrl=2, Meta/Command=4, Shift=8 (default: 0).
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder modifiers(@Nullable Long value) {
                if (value == null) values.remove("modifiers");
                else values.put("modifiers", jsonValue(value));
                return this;
            }
            /**
             * Number of times the mouse button was clicked (default: 0).
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder clickCount(@Nullable Long value) {
                if (value == null) values.remove("clickCount");
                else values.put("clickCount", jsonValue(value));
                return this;
            }
            public EmulateTouchFromMouseEventParams build() {
                if (!values.containsKey("type")) throw new IllegalStateException("Missing required CDP field: type");
                if (!values.containsKey("x")) throw new IllegalStateException("Missing required CDP field: x");
                if (!values.containsKey("y")) throw new IllegalStateException("Missing required CDP field: y");
                if (!values.containsKey("button")) throw new IllegalStateException("Missing required CDP field: button");
                return new EmulateTouchFromMouseEventParams(values);
            }
        }
    }
    /**
     * Emulates touch event from the mouse event parameters.
     * <p><b>Experimental:</b> this part of CDP may change without notice.
     */
    public static final class EmulateTouchFromMouseEventResult extends CdpObject {
        private EmulateTouchFromMouseEventResult(Map<String, Object> values) { super(values); }
        @Nullable public static EmulateTouchFromMouseEventResult fromMap(@Nullable Map<String, Object> values) {
            return values == null ? null : new EmulateTouchFromMouseEventResult(values);
        }
        public static Builder builder() { return new Builder(); }
        public static final class Builder {
            private final Map<String, Object> values = new LinkedHashMap<>();
            public EmulateTouchFromMouseEventResult build() {
                return new EmulateTouchFromMouseEventResult(values);
            }
        }
    }
    /**
     * Ignores input events (useful while auditing page).
     */
    public static final class SetIgnoreInputEventsParams extends CdpObject {
        private SetIgnoreInputEventsParams(Map<String, Object> values) { super(values); }
        @Nullable public static SetIgnoreInputEventsParams fromMap(@Nullable Map<String, Object> values) {
            return values == null ? null : new SetIgnoreInputEventsParams(values);
        }
        public static Builder builder() { return new Builder(); }
        /**
         * Ignores input events processing when set to true.
         * @return the protocol field value
         */
        @Nullable public Boolean ignore() {
            return (Boolean) value("ignore");
        }
        public static final class Builder {
            private final Map<String, Object> values = new LinkedHashMap<>();
            /**
             * Ignores input events processing when set to true.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder ignore(@Nullable Boolean value) {
                if (value == null) values.remove("ignore");
                else values.put("ignore", jsonValue(value));
                return this;
            }
            public SetIgnoreInputEventsParams build() {
                if (!values.containsKey("ignore")) throw new IllegalStateException("Missing required CDP field: ignore");
                return new SetIgnoreInputEventsParams(values);
            }
        }
    }
    /**
     * Ignores input events (useful while auditing page).
     */
    public static final class SetIgnoreInputEventsResult extends CdpObject {
        private SetIgnoreInputEventsResult(Map<String, Object> values) { super(values); }
        @Nullable public static SetIgnoreInputEventsResult fromMap(@Nullable Map<String, Object> values) {
            return values == null ? null : new SetIgnoreInputEventsResult(values);
        }
        public static Builder builder() { return new Builder(); }
        public static final class Builder {
            private final Map<String, Object> values = new LinkedHashMap<>();
            public SetIgnoreInputEventsResult build() {
                return new SetIgnoreInputEventsResult(values);
            }
        }
    }
    /**
     * Prevents default drag and drop behavior and instead emits {@code Input.dragIntercepted} events. Drag and drop behavior can be directly controlled via {@code Input.dispatchDragEvent}.
     * <p><b>Experimental:</b> this part of CDP may change without notice.
     */
    public static final class SetInterceptDragsParams extends CdpObject {
        private SetInterceptDragsParams(Map<String, Object> values) { super(values); }
        @Nullable public static SetInterceptDragsParams fromMap(@Nullable Map<String, Object> values) {
            return values == null ? null : new SetInterceptDragsParams(values);
        }
        public static Builder builder() { return new Builder(); }
        /**
         * Returns the enabled field.
         * @return the protocol field value
         */
        @Nullable public Boolean enabled() {
            return (Boolean) value("enabled");
        }
        public static final class Builder {
            private final Map<String, Object> values = new LinkedHashMap<>();
            /**
             * Sets the enabled field.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder enabled(@Nullable Boolean value) {
                if (value == null) values.remove("enabled");
                else values.put("enabled", jsonValue(value));
                return this;
            }
            public SetInterceptDragsParams build() {
                if (!values.containsKey("enabled")) throw new IllegalStateException("Missing required CDP field: enabled");
                return new SetInterceptDragsParams(values);
            }
        }
    }
    /**
     * Prevents default drag and drop behavior and instead emits {@code Input.dragIntercepted} events. Drag and drop behavior can be directly controlled via {@code Input.dispatchDragEvent}.
     * <p><b>Experimental:</b> this part of CDP may change without notice.
     */
    public static final class SetInterceptDragsResult extends CdpObject {
        private SetInterceptDragsResult(Map<String, Object> values) { super(values); }
        @Nullable public static SetInterceptDragsResult fromMap(@Nullable Map<String, Object> values) {
            return values == null ? null : new SetInterceptDragsResult(values);
        }
        public static Builder builder() { return new Builder(); }
        public static final class Builder {
            private final Map<String, Object> values = new LinkedHashMap<>();
            public SetInterceptDragsResult build() {
                return new SetInterceptDragsResult(values);
            }
        }
    }
    /**
     * Synthesizes a pinch gesture over a time period by issuing appropriate touch events.
     * <p><b>Experimental:</b> this part of CDP may change without notice.
     */
    public static final class SynthesizePinchGestureParams extends CdpObject {
        private SynthesizePinchGestureParams(Map<String, Object> values) { super(values); }
        @Nullable public static SynthesizePinchGestureParams fromMap(@Nullable Map<String, Object> values) {
            return values == null ? null : new SynthesizePinchGestureParams(values);
        }
        public static Builder builder() { return new Builder(); }
        /**
         * X coordinate of the start of the gesture in CSS pixels.
         * @return the protocol field value
         */
        @Nullable public Double x() {
            return numberAsDouble(value("x"));
        }
        /**
         * Y coordinate of the start of the gesture in CSS pixels.
         * @return the protocol field value
         */
        @Nullable public Double y() {
            return numberAsDouble(value("y"));
        }
        /**
         * Relative scale factor after zooming (&gt;1.0 zooms in, &lt;1.0 zooms out).
         * @return the protocol field value
         */
        @Nullable public Double scaleFactor() {
            return numberAsDouble(value("scaleFactor"));
        }
        /**
         * Relative pointer speed in pixels per second (default: 800).
         * @return the protocol field value
         */
        @Nullable public Long relativeSpeed() {
            return numberAsLong(value("relativeSpeed"));
        }
        /**
         * Which type of input events to be generated (default: &#x27;default&#x27;, which queries the platform for the preferred input type).
         * @return the protocol field value
         */
        @Nullable public String gestureSourceType() {
            return (String) value("gestureSourceType");
        }
        public static final class Builder {
            private final Map<String, Object> values = new LinkedHashMap<>();
            /**
             * X coordinate of the start of the gesture in CSS pixels.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder x(@Nullable Double value) {
                if (value == null) values.remove("x");
                else values.put("x", jsonValue(value));
                return this;
            }
            /**
             * Y coordinate of the start of the gesture in CSS pixels.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder y(@Nullable Double value) {
                if (value == null) values.remove("y");
                else values.put("y", jsonValue(value));
                return this;
            }
            /**
             * Relative scale factor after zooming (&gt;1.0 zooms in, &lt;1.0 zooms out).
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder scaleFactor(@Nullable Double value) {
                if (value == null) values.remove("scaleFactor");
                else values.put("scaleFactor", jsonValue(value));
                return this;
            }
            /**
             * Relative pointer speed in pixels per second (default: 800).
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder relativeSpeed(@Nullable Long value) {
                if (value == null) values.remove("relativeSpeed");
                else values.put("relativeSpeed", jsonValue(value));
                return this;
            }
            /**
             * Which type of input events to be generated (default: &#x27;default&#x27;, which queries the platform for the preferred input type).
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder gestureSourceType(@Nullable String value) {
                if (value == null) values.remove("gestureSourceType");
                else values.put("gestureSourceType", jsonValue(value));
                return this;
            }
            public SynthesizePinchGestureParams build() {
                if (!values.containsKey("x")) throw new IllegalStateException("Missing required CDP field: x");
                if (!values.containsKey("y")) throw new IllegalStateException("Missing required CDP field: y");
                if (!values.containsKey("scaleFactor")) throw new IllegalStateException("Missing required CDP field: scaleFactor");
                return new SynthesizePinchGestureParams(values);
            }
        }
    }
    /**
     * Synthesizes a pinch gesture over a time period by issuing appropriate touch events.
     * <p><b>Experimental:</b> this part of CDP may change without notice.
     */
    public static final class SynthesizePinchGestureResult extends CdpObject {
        private SynthesizePinchGestureResult(Map<String, Object> values) { super(values); }
        @Nullable public static SynthesizePinchGestureResult fromMap(@Nullable Map<String, Object> values) {
            return values == null ? null : new SynthesizePinchGestureResult(values);
        }
        public static Builder builder() { return new Builder(); }
        public static final class Builder {
            private final Map<String, Object> values = new LinkedHashMap<>();
            public SynthesizePinchGestureResult build() {
                return new SynthesizePinchGestureResult(values);
            }
        }
    }
    /**
     * Synthesizes a scroll gesture over a time period by issuing appropriate touch events.
     * <p><b>Experimental:</b> this part of CDP may change without notice.
     */
    public static final class SynthesizeScrollGestureParams extends CdpObject {
        private SynthesizeScrollGestureParams(Map<String, Object> values) { super(values); }
        @Nullable public static SynthesizeScrollGestureParams fromMap(@Nullable Map<String, Object> values) {
            return values == null ? null : new SynthesizeScrollGestureParams(values);
        }
        public static Builder builder() { return new Builder(); }
        /**
         * X coordinate of the start of the gesture in CSS pixels.
         * @return the protocol field value
         */
        @Nullable public Double x() {
            return numberAsDouble(value("x"));
        }
        /**
         * Y coordinate of the start of the gesture in CSS pixels.
         * @return the protocol field value
         */
        @Nullable public Double y() {
            return numberAsDouble(value("y"));
        }
        /**
         * The distance to scroll along the X axis (positive to scroll left).
         * @return the protocol field value
         */
        @Nullable public Double xDistance() {
            return numberAsDouble(value("xDistance"));
        }
        /**
         * The distance to scroll along the Y axis (positive to scroll up).
         * @return the protocol field value
         */
        @Nullable public Double yDistance() {
            return numberAsDouble(value("yDistance"));
        }
        /**
         * The number of additional pixels to scroll back along the X axis, in addition to the given distance.
         * @return the protocol field value
         */
        @Nullable public Double xOverscroll() {
            return numberAsDouble(value("xOverscroll"));
        }
        /**
         * The number of additional pixels to scroll back along the Y axis, in addition to the given distance.
         * @return the protocol field value
         */
        @Nullable public Double yOverscroll() {
            return numberAsDouble(value("yOverscroll"));
        }
        /**
         * Prevent fling (default: true).
         * @return the protocol field value
         */
        @Nullable public Boolean preventFling() {
            return (Boolean) value("preventFling");
        }
        /**
         * Swipe speed in pixels per second (default: 800).
         * @return the protocol field value
         */
        @Nullable public Long speed() {
            return numberAsLong(value("speed"));
        }
        /**
         * Which type of input events to be generated (default: &#x27;default&#x27;, which queries the platform for the preferred input type).
         * @return the protocol field value
         */
        @Nullable public String gestureSourceType() {
            return (String) value("gestureSourceType");
        }
        /**
         * The number of times to repeat the gesture (default: 0).
         * @return the protocol field value
         */
        @Nullable public Long repeatCount() {
            return numberAsLong(value("repeatCount"));
        }
        /**
         * The number of milliseconds delay between each repeat. (default: 250).
         * @return the protocol field value
         */
        @Nullable public Long repeatDelayMs() {
            return numberAsLong(value("repeatDelayMs"));
        }
        /**
         * The name of the interaction markers to generate, if not empty (default: &quot;&quot;).
         * @return the protocol field value
         */
        @Nullable public String interactionMarkerName() {
            return (String) value("interactionMarkerName");
        }
        public static final class Builder {
            private final Map<String, Object> values = new LinkedHashMap<>();
            /**
             * X coordinate of the start of the gesture in CSS pixels.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder x(@Nullable Double value) {
                if (value == null) values.remove("x");
                else values.put("x", jsonValue(value));
                return this;
            }
            /**
             * Y coordinate of the start of the gesture in CSS pixels.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder y(@Nullable Double value) {
                if (value == null) values.remove("y");
                else values.put("y", jsonValue(value));
                return this;
            }
            /**
             * The distance to scroll along the X axis (positive to scroll left).
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder xDistance(@Nullable Double value) {
                if (value == null) values.remove("xDistance");
                else values.put("xDistance", jsonValue(value));
                return this;
            }
            /**
             * The distance to scroll along the Y axis (positive to scroll up).
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder yDistance(@Nullable Double value) {
                if (value == null) values.remove("yDistance");
                else values.put("yDistance", jsonValue(value));
                return this;
            }
            /**
             * The number of additional pixels to scroll back along the X axis, in addition to the given distance.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder xOverscroll(@Nullable Double value) {
                if (value == null) values.remove("xOverscroll");
                else values.put("xOverscroll", jsonValue(value));
                return this;
            }
            /**
             * The number of additional pixels to scroll back along the Y axis, in addition to the given distance.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder yOverscroll(@Nullable Double value) {
                if (value == null) values.remove("yOverscroll");
                else values.put("yOverscroll", jsonValue(value));
                return this;
            }
            /**
             * Prevent fling (default: true).
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder preventFling(@Nullable Boolean value) {
                if (value == null) values.remove("preventFling");
                else values.put("preventFling", jsonValue(value));
                return this;
            }
            /**
             * Swipe speed in pixels per second (default: 800).
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder speed(@Nullable Long value) {
                if (value == null) values.remove("speed");
                else values.put("speed", jsonValue(value));
                return this;
            }
            /**
             * Which type of input events to be generated (default: &#x27;default&#x27;, which queries the platform for the preferred input type).
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder gestureSourceType(@Nullable String value) {
                if (value == null) values.remove("gestureSourceType");
                else values.put("gestureSourceType", jsonValue(value));
                return this;
            }
            /**
             * The number of times to repeat the gesture (default: 0).
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder repeatCount(@Nullable Long value) {
                if (value == null) values.remove("repeatCount");
                else values.put("repeatCount", jsonValue(value));
                return this;
            }
            /**
             * The number of milliseconds delay between each repeat. (default: 250).
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder repeatDelayMs(@Nullable Long value) {
                if (value == null) values.remove("repeatDelayMs");
                else values.put("repeatDelayMs", jsonValue(value));
                return this;
            }
            /**
             * The name of the interaction markers to generate, if not empty (default: &quot;&quot;).
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder interactionMarkerName(@Nullable String value) {
                if (value == null) values.remove("interactionMarkerName");
                else values.put("interactionMarkerName", jsonValue(value));
                return this;
            }
            public SynthesizeScrollGestureParams build() {
                if (!values.containsKey("x")) throw new IllegalStateException("Missing required CDP field: x");
                if (!values.containsKey("y")) throw new IllegalStateException("Missing required CDP field: y");
                return new SynthesizeScrollGestureParams(values);
            }
        }
    }
    /**
     * Synthesizes a scroll gesture over a time period by issuing appropriate touch events.
     * <p><b>Experimental:</b> this part of CDP may change without notice.
     */
    public static final class SynthesizeScrollGestureResult extends CdpObject {
        private SynthesizeScrollGestureResult(Map<String, Object> values) { super(values); }
        @Nullable public static SynthesizeScrollGestureResult fromMap(@Nullable Map<String, Object> values) {
            return values == null ? null : new SynthesizeScrollGestureResult(values);
        }
        public static Builder builder() { return new Builder(); }
        public static final class Builder {
            private final Map<String, Object> values = new LinkedHashMap<>();
            public SynthesizeScrollGestureResult build() {
                return new SynthesizeScrollGestureResult(values);
            }
        }
    }
    /**
     * Synthesizes a tap gesture over a time period by issuing appropriate touch events.
     * <p><b>Experimental:</b> this part of CDP may change without notice.
     */
    public static final class SynthesizeTapGestureParams extends CdpObject {
        private SynthesizeTapGestureParams(Map<String, Object> values) { super(values); }
        @Nullable public static SynthesizeTapGestureParams fromMap(@Nullable Map<String, Object> values) {
            return values == null ? null : new SynthesizeTapGestureParams(values);
        }
        public static Builder builder() { return new Builder(); }
        /**
         * X coordinate of the start of the gesture in CSS pixels.
         * @return the protocol field value
         */
        @Nullable public Double x() {
            return numberAsDouble(value("x"));
        }
        /**
         * Y coordinate of the start of the gesture in CSS pixels.
         * @return the protocol field value
         */
        @Nullable public Double y() {
            return numberAsDouble(value("y"));
        }
        /**
         * Duration between touchdown and touchup events in ms (default: 50).
         * @return the protocol field value
         */
        @Nullable public Long duration() {
            return numberAsLong(value("duration"));
        }
        /**
         * Number of times to perform the tap (e.g. 2 for double tap, default: 1).
         * @return the protocol field value
         */
        @Nullable public Long tapCount() {
            return numberAsLong(value("tapCount"));
        }
        /**
         * Which type of input events to be generated (default: &#x27;default&#x27;, which queries the platform for the preferred input type).
         * @return the protocol field value
         */
        @Nullable public String gestureSourceType() {
            return (String) value("gestureSourceType");
        }
        public static final class Builder {
            private final Map<String, Object> values = new LinkedHashMap<>();
            /**
             * X coordinate of the start of the gesture in CSS pixels.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder x(@Nullable Double value) {
                if (value == null) values.remove("x");
                else values.put("x", jsonValue(value));
                return this;
            }
            /**
             * Y coordinate of the start of the gesture in CSS pixels.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder y(@Nullable Double value) {
                if (value == null) values.remove("y");
                else values.put("y", jsonValue(value));
                return this;
            }
            /**
             * Duration between touchdown and touchup events in ms (default: 50).
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder duration(@Nullable Long value) {
                if (value == null) values.remove("duration");
                else values.put("duration", jsonValue(value));
                return this;
            }
            /**
             * Number of times to perform the tap (e.g. 2 for double tap, default: 1).
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder tapCount(@Nullable Long value) {
                if (value == null) values.remove("tapCount");
                else values.put("tapCount", jsonValue(value));
                return this;
            }
            /**
             * Which type of input events to be generated (default: &#x27;default&#x27;, which queries the platform for the preferred input type).
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder gestureSourceType(@Nullable String value) {
                if (value == null) values.remove("gestureSourceType");
                else values.put("gestureSourceType", jsonValue(value));
                return this;
            }
            public SynthesizeTapGestureParams build() {
                if (!values.containsKey("x")) throw new IllegalStateException("Missing required CDP field: x");
                if (!values.containsKey("y")) throw new IllegalStateException("Missing required CDP field: y");
                return new SynthesizeTapGestureParams(values);
            }
        }
    }
    /**
     * Synthesizes a tap gesture over a time period by issuing appropriate touch events.
     * <p><b>Experimental:</b> this part of CDP may change without notice.
     */
    public static final class SynthesizeTapGestureResult extends CdpObject {
        private SynthesizeTapGestureResult(Map<String, Object> values) { super(values); }
        @Nullable public static SynthesizeTapGestureResult fromMap(@Nullable Map<String, Object> values) {
            return values == null ? null : new SynthesizeTapGestureResult(values);
        }
        public static Builder builder() { return new Builder(); }
        public static final class Builder {
            private final Map<String, Object> values = new LinkedHashMap<>();
            public SynthesizeTapGestureResult build() {
                return new SynthesizeTapGestureResult(values);
            }
        }
    }
    /**
     * Emitted only when {@code Input.setInterceptDrags} is enabled. Use this data with {@code Input.dispatchDragEvent} to restore normal drag and drop behavior.
     * <p><b>Experimental:</b> this part of CDP may change without notice.
     */
    public static final class DragInterceptedEvent extends CdpObject {
        private DragInterceptedEvent(Map<String, Object> values) { super(values); }
        @Nullable public static DragInterceptedEvent fromMap(@Nullable Map<String, Object> values) {
            return values == null ? null : new DragInterceptedEvent(values);
        }
        public static Builder builder() { return new Builder(); }
        /**
         * Returns the data field.
         * @return the protocol field value
         */
        @Nullable public Input.DragData data() {
            return Input.DragData.fromMap(objectMap(value("data")));
        }
        public static final class Builder {
            private final Map<String, Object> values = new LinkedHashMap<>();
            /**
             * Sets the data field.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder data(@Nullable Input.DragData value) {
                if (value == null) values.remove("data");
                else values.put("data", jsonValue(value));
                return this;
            }
            public DragInterceptedEvent build() {
                if (!values.containsKey("data")) throw new IllegalStateException("Missing required CDP field: data");
                return new DragInterceptedEvent(values);
            }
        }
    }
    public static final class Client {
        private final CdpClient client;
        public Client(CdpClient client) { this.client = client; }
        /**
         * Dispatches a drag event into the page.
         * <p><b>Experimental:</b> this part of CDP may change without notice.
         * @param params command parameters
         * @return a stage completing with the command result
         */
        public CompletionStage<DispatchDragEventResult> dispatchDragEvent(DispatchDragEventParams params) {
            return client.call("Input.dispatchDragEvent", params, DispatchDragEventResult::fromMap);
        }
        /**
         * Dispatches a key event to the page.
         * @param params command parameters
         * @return a stage completing with the command result
         */
        public CompletionStage<DispatchKeyEventResult> dispatchKeyEvent(DispatchKeyEventParams params) {
            return client.call("Input.dispatchKeyEvent", params, DispatchKeyEventResult::fromMap);
        }
        /**
         * This method emulates inserting text that doesn&#x27;t come from a key press, for example an emoji keyboard or an IME.
         * <p><b>Experimental:</b> this part of CDP may change without notice.
         * @param params command parameters
         * @return a stage completing with the command result
         */
        public CompletionStage<InsertTextResult> insertText(InsertTextParams params) {
            return client.call("Input.insertText", params, InsertTextResult::fromMap);
        }
        /**
         * This method sets the current candidate text for IME. Use imeCommitComposition to commit the final text. Use imeSetComposition with empty string as text to cancel composition.
         * <p><b>Experimental:</b> this part of CDP may change without notice.
         * @param params command parameters
         * @return a stage completing with the command result
         */
        public CompletionStage<ImeSetCompositionResult> imeSetComposition(ImeSetCompositionParams params) {
            return client.call("Input.imeSetComposition", params, ImeSetCompositionResult::fromMap);
        }
        /**
         * Dispatches a mouse event to the page.
         * @param params command parameters
         * @return a stage completing with the command result
         */
        public CompletionStage<DispatchMouseEventResult> dispatchMouseEvent(DispatchMouseEventParams params) {
            return client.call("Input.dispatchMouseEvent", params, DispatchMouseEventResult::fromMap);
        }
        /**
         * Dispatches a touch event to the page.
         * @param params command parameters
         * @return a stage completing with the command result
         */
        public CompletionStage<DispatchTouchEventResult> dispatchTouchEvent(DispatchTouchEventParams params) {
            return client.call("Input.dispatchTouchEvent", params, DispatchTouchEventResult::fromMap);
        }
        /**
         * Cancels any active dragging in the page.
         * @return a stage completing with the command result
         */
        public CompletionStage<CancelDraggingResult> cancelDragging() {
            return client.call("Input.cancelDragging", null, CancelDraggingResult::fromMap);
        }
        /**
         * Emulates touch event from the mouse event parameters.
         * <p><b>Experimental:</b> this part of CDP may change without notice.
         * @param params command parameters
         * @return a stage completing with the command result
         */
        public CompletionStage<EmulateTouchFromMouseEventResult> emulateTouchFromMouseEvent(EmulateTouchFromMouseEventParams params) {
            return client.call("Input.emulateTouchFromMouseEvent", params, EmulateTouchFromMouseEventResult::fromMap);
        }
        /**
         * Ignores input events (useful while auditing page).
         * @param params command parameters
         * @return a stage completing with the command result
         */
        public CompletionStage<SetIgnoreInputEventsResult> setIgnoreInputEvents(SetIgnoreInputEventsParams params) {
            return client.call("Input.setIgnoreInputEvents", params, SetIgnoreInputEventsResult::fromMap);
        }
        /**
         * Prevents default drag and drop behavior and instead emits {@code Input.dragIntercepted} events. Drag and drop behavior can be directly controlled via {@code Input.dispatchDragEvent}.
         * <p><b>Experimental:</b> this part of CDP may change without notice.
         * @param params command parameters
         * @return a stage completing with the command result
         */
        public CompletionStage<SetInterceptDragsResult> setInterceptDrags(SetInterceptDragsParams params) {
            return client.call("Input.setInterceptDrags", params, SetInterceptDragsResult::fromMap);
        }
        /**
         * Synthesizes a pinch gesture over a time period by issuing appropriate touch events.
         * <p><b>Experimental:</b> this part of CDP may change without notice.
         * @param params command parameters
         * @return a stage completing with the command result
         */
        public CompletionStage<SynthesizePinchGestureResult> synthesizePinchGesture(SynthesizePinchGestureParams params) {
            return client.call("Input.synthesizePinchGesture", params, SynthesizePinchGestureResult::fromMap);
        }
        /**
         * Synthesizes a scroll gesture over a time period by issuing appropriate touch events.
         * <p><b>Experimental:</b> this part of CDP may change without notice.
         * @param params command parameters
         * @return a stage completing with the command result
         */
        public CompletionStage<SynthesizeScrollGestureResult> synthesizeScrollGesture(SynthesizeScrollGestureParams params) {
            return client.call("Input.synthesizeScrollGesture", params, SynthesizeScrollGestureResult::fromMap);
        }
        /**
         * Synthesizes a tap gesture over a time period by issuing appropriate touch events.
         * <p><b>Experimental:</b> this part of CDP may change without notice.
         * @param params command parameters
         * @return a stage completing with the command result
         */
        public CompletionStage<SynthesizeTapGestureResult> synthesizeTapGesture(SynthesizeTapGestureParams params) {
            return client.call("Input.synthesizeTapGesture", params, SynthesizeTapGestureResult::fromMap);
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
