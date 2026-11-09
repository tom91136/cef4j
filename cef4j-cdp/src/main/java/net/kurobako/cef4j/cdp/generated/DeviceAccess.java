// GENERATED - do not edit. Regenerate via: ./mvnw generate-sources -pl cef4j-cdp -Dcef.version=150.0.18+gdb11278+chromium-150.0.7871.213
package net.kurobako.cef4j.cdp.generated;

import java.util.Map;
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

/**
 * Chrome DevTools Protocol DeviceAccess domain.
 * <p><b>Experimental:</b> this part of CDP may change without notice.
 * @see <a href="https://chromium.googlesource.com/chromium/src/+/refs/tags/150.0.7871.213/third_party/blink/public/devtools_protocol/domains/DeviceAccess.pdl">Pinned protocol source</a>
 */
@Generated("./mvnw generate-sources -pl cef4j-cdp -Dcef.version=150.0.18+gdb11278+chromium-150.0.7871.213")
@SuppressWarnings({"EscapedEntity", "InvalidParam", "JavaLangClash", "MissingSummary", "Unchecked", "UnusedMethod"})
@NullableBoundary("CDP wire objects use null for absent optional fields")
public final class DeviceAccess {
    private DeviceAccess() {}
    private static final CdpField<java.util.List<DeviceAccess.PromptDevice>> DEVICES = CdpField.of("devices", value -> CdpObject.requireList(value, element0 -> java.util.Objects.requireNonNull(DeviceAccess.PromptDevice.fromMap(CdpObject.requireObjectMap(element0)))));
    private static final CdpField<DeviceAccess.DeviceId> DEVICE_ID = CdpField.of("deviceId", value -> new DeviceAccess.DeviceId((String) value));
    private static final CdpField<DeviceAccess.DeviceId> ID = CdpField.of("id", value -> new DeviceAccess.DeviceId((String) value));
    private static final CdpField<DeviceAccess.RequestId> ID_2 = CdpField.of("id", value -> new DeviceAccess.RequestId((String) value));
    /**
     * Device request id.
     */
    public static final class RequestId implements CdpValue<String> {
        public final String value;
        public RequestId(String value) { this.value = java.util.Objects.requireNonNull(value); }
        @Override public String value() { return value; }
        @Override public boolean equals(@org.jspecify.annotations.Nullable Object other) {
            if (this == other) return true;
            if (!(other instanceof RequestId)) return false;
            return value.equals(((RequestId) other).value);
        }
        @Override public int hashCode() { return value.hashCode(); }
        @Override public String toString() { return "RequestId(" + value + ")"; }
    }
    /**
     * A device id.
     */
    public static final class DeviceId implements CdpValue<String> {
        public final String value;
        public DeviceId(String value) { this.value = java.util.Objects.requireNonNull(value); }
        @Override public String value() { return value; }
        @Override public boolean equals(@org.jspecify.annotations.Nullable Object other) {
            if (this == other) return true;
            if (!(other instanceof DeviceId)) return false;
            return value.equals(((DeviceId) other).value);
        }
        @Override public int hashCode() { return value.hashCode(); }
        @Override public String toString() { return "DeviceId(" + value + ")"; }
    }
    /**
     * Device information displayed in a user prompt to select a device.
     */
    public static final class PromptDevice extends CdpObject {
        public PromptDevice() {}
        private PromptDevice(Map<String, Object> values) { super(values); }
        public static PromptDevice fromMap(Map<String, Object> values) {
            return new PromptDevice(values);
        }
        /**
         * Returns the id field.
         * @return the protocol field value
         */
        public DeviceAccess.DeviceId id() {
            return require(ID);
        }
        /**
         * Display name as it appears in a device request user prompt.
         * @return the protocol field value
         */
        public String name() {
            return requireString("name");
        }
        /**
         * Sets the id field.
         * @param id field value
         * @return this model
         */
        public PromptDevice id(DeviceAccess.DeviceId id) {
            set("id", id);
            return this;
        }
        /**
         * Display name as it appears in a device request user prompt.
         * @param name field value
         * @return this model
         */
        public PromptDevice name(String name) {
            set("name", name);
            return this;
        }
    }
    /**
     * Select a device in response to a DeviceAccess.deviceRequestPrompted event.
     */
    public static final class SelectPromptRequest extends CdpObject {
        public SelectPromptRequest() {}
        public static SelectPromptRequest fromMap(Map<String, Object> values) {
            final SelectPromptRequest instance_ = new SelectPromptRequest();
            if (values != null) instance_.values.putAll(values);
            return instance_;
        }
        /**
         * Returns the id field.
         * @return the protocol field value
         */
        public DeviceAccess.RequestId id() {
            return require(ID_2);
        }
        /**
         * Returns the deviceId field.
         * @return the protocol field value
         */
        public DeviceAccess.DeviceId deviceId() {
            return require(DEVICE_ID);
        }
        /**
         * Sets the id field.
         * @param id field value
         * @return this model
         */
        public SelectPromptRequest id(DeviceAccess.RequestId id) {
            set("id", id);
            return this;
        }
        /**
         * Sets the deviceId field.
         * @param deviceId field value
         * @return this model
         */
        public SelectPromptRequest deviceId(DeviceAccess.DeviceId deviceId) {
            set("deviceId", deviceId);
            return this;
        }
    }
    /**
     * Cancel a prompt in response to a DeviceAccess.deviceRequestPrompted event.
     */
    public static final class CancelPromptRequest extends CdpObject {
        public CancelPromptRequest() {}
        public static CancelPromptRequest fromMap(Map<String, Object> values) {
            final CancelPromptRequest instance_ = new CancelPromptRequest();
            if (values != null) instance_.values.putAll(values);
            return instance_;
        }
        /**
         * Returns the id field.
         * @return the protocol field value
         */
        public DeviceAccess.RequestId id() {
            return require(ID_2);
        }
        /**
         * Sets the id field.
         * @param id field value
         * @return this model
         */
        public CancelPromptRequest id(DeviceAccess.RequestId id) {
            set("id", id);
            return this;
        }
    }
    /**
     * A device request opened a user prompt to select a device. Respond with the selectPrompt or cancelPrompt command.
     */
    public static final class DeviceRequestPromptedEvent extends CdpObject {
        public DeviceRequestPromptedEvent() {}
        private DeviceRequestPromptedEvent(Map<String, Object> values) { super(values); }
        public static DeviceRequestPromptedEvent fromMap(Map<String, Object> values) {
            return new DeviceRequestPromptedEvent(values);
        }
        /**
         * Returns the id field.
         * @return the protocol field value
         */
        public DeviceAccess.RequestId id() {
            return require(ID_2);
        }
        /**
         * Returns the devices field.
         * @return the protocol field value
         */
        public java.util.List<DeviceAccess.PromptDevice> devices() {
            return require(DEVICES);
        }
        /**
         * Sets the id field.
         * @param id field value
         * @return this model
         */
        public DeviceRequestPromptedEvent id(DeviceAccess.RequestId id) {
            set("id", id);
            return this;
        }
        /**
         * Sets the devices field.
         * @param devices field value
         * @return this model
         */
        public DeviceRequestPromptedEvent devices(java.util.List<DeviceAccess.PromptDevice> devices) {
            set("devices", devices);
            return this;
        }
    }
    public static final class Client extends CdpDomainClient {
        public Client(CdpClient client) { super(client); }
        /**
         * Enable events in this domain.
         * @return a stage completing when the command completes
         */
        public CompletionStage<Void> enable() {
            return call("DeviceAccess.enable");
        }
        /**
         * Disable events in this domain.
         * @return a stage completing when the command completes
         */
        public CompletionStage<Void> disable() {
            return call("DeviceAccess.disable");
        }
        /**
         * Select a device in response to a DeviceAccess.deviceRequestPrompted event.
         * @param id protocol value
         * @param deviceId protocol value
         * @return a stage completing when the command completes
         */
        public CompletionStage<Void> selectPrompt(DeviceAccess.RequestId id, DeviceAccess.DeviceId deviceId) {
            return call("DeviceAccess.selectPrompt", params("id", id, "deviceId", deviceId));
        }
        /**
         * Select a device in response to a DeviceAccess.deviceRequestPrompted event.
         * @param request request parameters
         * @return a stage completing when the command completes
         */
        public CompletionStage<Void> selectPrompt(SelectPromptRequest request) {
            return call("DeviceAccess.selectPrompt", request);
        }
        /**
         * Cancel a prompt in response to a DeviceAccess.deviceRequestPrompted event.
         * @param id protocol value
         * @return a stage completing when the command completes
         */
        public CompletionStage<Void> cancelPrompt(DeviceAccess.RequestId id) {
            return call("DeviceAccess.cancelPrompt", params("id", id));
        }
        /**
         * Cancel a prompt in response to a DeviceAccess.deviceRequestPrompted event.
         * @param request request parameters
         * @return a stage completing when the command completes
         */
        public CompletionStage<Void> cancelPrompt(CancelPromptRequest request) {
            return call("DeviceAccess.cancelPrompt", request);
        }
        /**
         * A device request opened a user prompt to select a device. Respond with the selectPrompt or cancelPrompt command.
         * @param handler event callback
         * @return a removable subscription
         */
        public CdpSubscription onDeviceRequestPrompted(Consumer<DeviceRequestPromptedEvent> handler) {
            return on("DeviceAccess.deviceRequestPrompted", DeviceRequestPromptedEvent::fromMap, handler);
        }
    }
}
