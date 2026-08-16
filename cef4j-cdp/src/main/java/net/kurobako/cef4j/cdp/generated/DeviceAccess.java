// GENERATED - do not edit. Run scripts/update-cdp-schema.sh.
package net.kurobako.cef4j.cdp.generated;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.concurrent.CompletionStage;
import java.util.function.Consumer;
import javax.annotation.Nullable;
import net.kurobako.cef4j.cdp.CdpClient;
import net.kurobako.cef4j.cdp.CdpObject;
import net.kurobako.cef4j.cdp.CdpSubscription;

/**
 * Chrome DevTools Protocol DeviceAccess domain.
 * <p><b>Experimental:</b> this part of CDP may change without notice.
 * @see <a href="https://chromium.googlesource.com/chromium/src/+/refs/tags/146.0.7680.165/third_party/blink/public/devtools_protocol/domains/DeviceAccess.pdl">Pinned protocol source</a>
 */
@SuppressWarnings({"EscapedEntity", "JavaLangClash", "MissingSummary", "UnusedMethod"})
public final class DeviceAccess {
    private DeviceAccess() {}
    @Nullable private static Long numberAsLong(@Nullable Object value) { return value == null ? null : ((Number) value).longValue(); }
    @Nullable private static Double numberAsDouble(@Nullable Object value) { return value == null ? null : ((Number) value).doubleValue(); }
    /**
     * Device information displayed in a user prompt to select a device.
     */
    public static final class PromptDevice extends CdpObject {
        private PromptDevice(Map<String, Object> values) { super(values); }
        @Nullable public static PromptDevice fromMap(@Nullable Map<String, Object> values) {
            return values == null ? null : new PromptDevice(values);
        }
        public static Builder builder() { return new Builder(); }
        /**
         * Returns the id field.
         * @return the protocol field value
         */
        @Nullable public String id() {
            return (String) value("id");
        }
        /**
         * Display name as it appears in a device request user prompt.
         * @return the protocol field value
         */
        @Nullable public String name() {
            return (String) value("name");
        }
        public static final class Builder {
            private final Map<String, Object> values = new LinkedHashMap<>();
            /**
             * Sets the id field.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder id(@Nullable String value) {
                if (value == null) values.remove("id");
                else values.put("id", jsonValue(value));
                return this;
            }
            /**
             * Display name as it appears in a device request user prompt.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder name(@Nullable String value) {
                if (value == null) values.remove("name");
                else values.put("name", jsonValue(value));
                return this;
            }
            public PromptDevice build() {
                if (!values.containsKey("id")) throw new IllegalStateException("Missing required CDP field: id");
                if (!values.containsKey("name")) throw new IllegalStateException("Missing required CDP field: name");
                return new PromptDevice(values);
            }
        }
    }
    /**
     * Enable events in this domain.
     */
    public static final class EnableParams extends CdpObject {
        private EnableParams(Map<String, Object> values) { super(values); }
        @Nullable public static EnableParams fromMap(@Nullable Map<String, Object> values) {
            return values == null ? null : new EnableParams(values);
        }
        public static Builder builder() { return new Builder(); }
        public static final class Builder {
            private final Map<String, Object> values = new LinkedHashMap<>();
            public EnableParams build() {
                return new EnableParams(values);
            }
        }
    }
    /**
     * Enable events in this domain.
     */
    public static final class EnableResult extends CdpObject {
        private EnableResult(Map<String, Object> values) { super(values); }
        @Nullable public static EnableResult fromMap(@Nullable Map<String, Object> values) {
            return values == null ? null : new EnableResult(values);
        }
        public static Builder builder() { return new Builder(); }
        public static final class Builder {
            private final Map<String, Object> values = new LinkedHashMap<>();
            public EnableResult build() {
                return new EnableResult(values);
            }
        }
    }
    /**
     * Disable events in this domain.
     */
    public static final class DisableParams extends CdpObject {
        private DisableParams(Map<String, Object> values) { super(values); }
        @Nullable public static DisableParams fromMap(@Nullable Map<String, Object> values) {
            return values == null ? null : new DisableParams(values);
        }
        public static Builder builder() { return new Builder(); }
        public static final class Builder {
            private final Map<String, Object> values = new LinkedHashMap<>();
            public DisableParams build() {
                return new DisableParams(values);
            }
        }
    }
    /**
     * Disable events in this domain.
     */
    public static final class DisableResult extends CdpObject {
        private DisableResult(Map<String, Object> values) { super(values); }
        @Nullable public static DisableResult fromMap(@Nullable Map<String, Object> values) {
            return values == null ? null : new DisableResult(values);
        }
        public static Builder builder() { return new Builder(); }
        public static final class Builder {
            private final Map<String, Object> values = new LinkedHashMap<>();
            public DisableResult build() {
                return new DisableResult(values);
            }
        }
    }
    /**
     * Select a device in response to a DeviceAccess.deviceRequestPrompted event.
     */
    public static final class SelectPromptParams extends CdpObject {
        private SelectPromptParams(Map<String, Object> values) { super(values); }
        @Nullable public static SelectPromptParams fromMap(@Nullable Map<String, Object> values) {
            return values == null ? null : new SelectPromptParams(values);
        }
        public static Builder builder() { return new Builder(); }
        /**
         * Returns the id field.
         * @return the protocol field value
         */
        @Nullable public String id() {
            return (String) value("id");
        }
        /**
         * Returns the deviceId field.
         * @return the protocol field value
         */
        @Nullable public String deviceId() {
            return (String) value("deviceId");
        }
        public static final class Builder {
            private final Map<String, Object> values = new LinkedHashMap<>();
            /**
             * Sets the id field.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder id(@Nullable String value) {
                if (value == null) values.remove("id");
                else values.put("id", jsonValue(value));
                return this;
            }
            /**
             * Sets the deviceId field.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder deviceId(@Nullable String value) {
                if (value == null) values.remove("deviceId");
                else values.put("deviceId", jsonValue(value));
                return this;
            }
            public SelectPromptParams build() {
                if (!values.containsKey("id")) throw new IllegalStateException("Missing required CDP field: id");
                if (!values.containsKey("deviceId")) throw new IllegalStateException("Missing required CDP field: deviceId");
                return new SelectPromptParams(values);
            }
        }
    }
    /**
     * Select a device in response to a DeviceAccess.deviceRequestPrompted event.
     */
    public static final class SelectPromptResult extends CdpObject {
        private SelectPromptResult(Map<String, Object> values) { super(values); }
        @Nullable public static SelectPromptResult fromMap(@Nullable Map<String, Object> values) {
            return values == null ? null : new SelectPromptResult(values);
        }
        public static Builder builder() { return new Builder(); }
        public static final class Builder {
            private final Map<String, Object> values = new LinkedHashMap<>();
            public SelectPromptResult build() {
                return new SelectPromptResult(values);
            }
        }
    }
    /**
     * Cancel a prompt in response to a DeviceAccess.deviceRequestPrompted event.
     */
    public static final class CancelPromptParams extends CdpObject {
        private CancelPromptParams(Map<String, Object> values) { super(values); }
        @Nullable public static CancelPromptParams fromMap(@Nullable Map<String, Object> values) {
            return values == null ? null : new CancelPromptParams(values);
        }
        public static Builder builder() { return new Builder(); }
        /**
         * Returns the id field.
         * @return the protocol field value
         */
        @Nullable public String id() {
            return (String) value("id");
        }
        public static final class Builder {
            private final Map<String, Object> values = new LinkedHashMap<>();
            /**
             * Sets the id field.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder id(@Nullable String value) {
                if (value == null) values.remove("id");
                else values.put("id", jsonValue(value));
                return this;
            }
            public CancelPromptParams build() {
                if (!values.containsKey("id")) throw new IllegalStateException("Missing required CDP field: id");
                return new CancelPromptParams(values);
            }
        }
    }
    /**
     * Cancel a prompt in response to a DeviceAccess.deviceRequestPrompted event.
     */
    public static final class CancelPromptResult extends CdpObject {
        private CancelPromptResult(Map<String, Object> values) { super(values); }
        @Nullable public static CancelPromptResult fromMap(@Nullable Map<String, Object> values) {
            return values == null ? null : new CancelPromptResult(values);
        }
        public static Builder builder() { return new Builder(); }
        public static final class Builder {
            private final Map<String, Object> values = new LinkedHashMap<>();
            public CancelPromptResult build() {
                return new CancelPromptResult(values);
            }
        }
    }
    /**
     * A device request opened a user prompt to select a device. Respond with the selectPrompt or cancelPrompt command.
     */
    public static final class DeviceRequestPromptedEvent extends CdpObject {
        private DeviceRequestPromptedEvent(Map<String, Object> values) { super(values); }
        @Nullable public static DeviceRequestPromptedEvent fromMap(@Nullable Map<String, Object> values) {
            return values == null ? null : new DeviceRequestPromptedEvent(values);
        }
        public static Builder builder() { return new Builder(); }
        /**
         * Returns the id field.
         * @return the protocol field value
         */
        @Nullable public String id() {
            return (String) value("id");
        }
        /**
         * Returns the devices field.
         * @return the protocol field value
         */
        @Nullable public java.util.List<DeviceAccess.PromptDevice> devices() {
            return list(value("devices"), element0 -> DeviceAccess.PromptDevice.fromMap(objectMap(element0)));
        }
        public static final class Builder {
            private final Map<String, Object> values = new LinkedHashMap<>();
            /**
             * Sets the id field.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder id(@Nullable String value) {
                if (value == null) values.remove("id");
                else values.put("id", jsonValue(value));
                return this;
            }
            /**
             * Sets the devices field.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder devices(@Nullable java.util.List<DeviceAccess.PromptDevice> value) {
                if (value == null) values.remove("devices");
                else values.put("devices", jsonValue(value));
                return this;
            }
            public DeviceRequestPromptedEvent build() {
                if (!values.containsKey("id")) throw new IllegalStateException("Missing required CDP field: id");
                if (!values.containsKey("devices")) throw new IllegalStateException("Missing required CDP field: devices");
                return new DeviceRequestPromptedEvent(values);
            }
        }
    }
    public static final class Client {
        private final CdpClient client;
        public Client(CdpClient client) { this.client = client; }
        /**
         * Enable events in this domain.
         * @return a stage completing with the command result
         */
        public CompletionStage<EnableResult> enable() {
            return client.call("DeviceAccess.enable", null, EnableResult::fromMap);
        }
        /**
         * Disable events in this domain.
         * @return a stage completing with the command result
         */
        public CompletionStage<DisableResult> disable() {
            return client.call("DeviceAccess.disable", null, DisableResult::fromMap);
        }
        /**
         * Select a device in response to a DeviceAccess.deviceRequestPrompted event.
         * @param params command parameters
         * @return a stage completing with the command result
         */
        public CompletionStage<SelectPromptResult> selectPrompt(SelectPromptParams params) {
            return client.call("DeviceAccess.selectPrompt", params, SelectPromptResult::fromMap);
        }
        /**
         * Cancel a prompt in response to a DeviceAccess.deviceRequestPrompted event.
         * @param params command parameters
         * @return a stage completing with the command result
         */
        public CompletionStage<CancelPromptResult> cancelPrompt(CancelPromptParams params) {
            return client.call("DeviceAccess.cancelPrompt", params, CancelPromptResult::fromMap);
        }
        /**
         * A device request opened a user prompt to select a device. Respond with the selectPrompt or cancelPrompt command.
         * @param handler event callback
         * @return a removable subscription
         */
        public CdpSubscription onDeviceRequestPrompted(Consumer<DeviceRequestPromptedEvent> handler) {
            return client.on("DeviceAccess.deviceRequestPrompted", DeviceRequestPromptedEvent::fromMap, handler);
        }
    }
}
