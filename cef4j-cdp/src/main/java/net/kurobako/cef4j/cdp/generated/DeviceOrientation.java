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
 * Chrome DevTools Protocol DeviceOrientation domain.
 * <p><b>Experimental:</b> this part of CDP may change without notice.
 * @see <a href="https://chromium.googlesource.com/chromium/src/+/refs/tags/150.0.7871.213/third_party/blink/public/devtools_protocol/domains/DeviceOrientation.pdl">Pinned protocol source</a>
 */
@Generated("mvn generate-sources -pl cef4j-platform -Dcef.version=150.0.18+gdb11278+chromium-150.0.7871.213")
@SuppressWarnings({"EscapedEntity", "JavaLangClash", "MissingSummary", "UnusedMethod"})
public final class DeviceOrientation {
    private DeviceOrientation() {}
    @Nullable private static Long numberAsLong(@Nullable Object value) { return value == null ? null : ((Number) value).longValue(); }
    @Nullable private static Double numberAsDouble(@Nullable Object value) { return value == null ? null : ((Number) value).doubleValue(); }
    /**
     * Clears the overridden Device Orientation.
     */
    public static final class ClearDeviceOrientationOverrideParams extends CdpObject {
        private ClearDeviceOrientationOverrideParams(Map<String, Object> values) { super(values); }
        @Nullable public static ClearDeviceOrientationOverrideParams fromMap(@Nullable Map<String, Object> values) {
            return values == null ? null : new ClearDeviceOrientationOverrideParams(values);
        }
        public static Builder builder() { return new Builder(); }
        public static final class Builder {
            private final Map<String, Object> values = new LinkedHashMap<>();
            public ClearDeviceOrientationOverrideParams build() {
                return new ClearDeviceOrientationOverrideParams(values);
            }
        }
    }
    /**
     * Clears the overridden Device Orientation.
     */
    public static final class ClearDeviceOrientationOverrideResult extends CdpObject {
        private ClearDeviceOrientationOverrideResult(Map<String, Object> values) { super(values); }
        @Nullable public static ClearDeviceOrientationOverrideResult fromMap(@Nullable Map<String, Object> values) {
            return values == null ? null : new ClearDeviceOrientationOverrideResult(values);
        }
        public static Builder builder() { return new Builder(); }
        public static final class Builder {
            private final Map<String, Object> values = new LinkedHashMap<>();
            public ClearDeviceOrientationOverrideResult build() {
                return new ClearDeviceOrientationOverrideResult(values);
            }
        }
    }
    /**
     * Overrides the Device Orientation.
     */
    public static final class SetDeviceOrientationOverrideParams extends CdpObject {
        private SetDeviceOrientationOverrideParams(Map<String, Object> values) { super(values); }
        @Nullable public static SetDeviceOrientationOverrideParams fromMap(@Nullable Map<String, Object> values) {
            return values == null ? null : new SetDeviceOrientationOverrideParams(values);
        }
        public static Builder builder() { return new Builder(); }
        /**
         * Mock alpha
         * @return the protocol field value
         */
        @Nullable public Double alpha() {
            return numberAsDouble(value("alpha"));
        }
        /**
         * Mock beta
         * @return the protocol field value
         */
        @Nullable public Double beta() {
            return numberAsDouble(value("beta"));
        }
        /**
         * Mock gamma
         * @return the protocol field value
         */
        @Nullable public Double gamma() {
            return numberAsDouble(value("gamma"));
        }
        public static final class Builder {
            private final Map<String, Object> values = new LinkedHashMap<>();
            /**
             * Mock alpha
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder alpha(@Nullable Double value) {
                if (value == null) values.remove("alpha");
                else values.put("alpha", jsonValue(value));
                return this;
            }
            /**
             * Mock beta
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder beta(@Nullable Double value) {
                if (value == null) values.remove("beta");
                else values.put("beta", jsonValue(value));
                return this;
            }
            /**
             * Mock gamma
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder gamma(@Nullable Double value) {
                if (value == null) values.remove("gamma");
                else values.put("gamma", jsonValue(value));
                return this;
            }
            public SetDeviceOrientationOverrideParams build() {
                if (!values.containsKey("alpha")) throw new IllegalStateException("Missing required CDP field: alpha");
                if (!values.containsKey("beta")) throw new IllegalStateException("Missing required CDP field: beta");
                if (!values.containsKey("gamma")) throw new IllegalStateException("Missing required CDP field: gamma");
                return new SetDeviceOrientationOverrideParams(values);
            }
        }
    }
    /**
     * Overrides the Device Orientation.
     */
    public static final class SetDeviceOrientationOverrideResult extends CdpObject {
        private SetDeviceOrientationOverrideResult(Map<String, Object> values) { super(values); }
        @Nullable public static SetDeviceOrientationOverrideResult fromMap(@Nullable Map<String, Object> values) {
            return values == null ? null : new SetDeviceOrientationOverrideResult(values);
        }
        public static Builder builder() { return new Builder(); }
        public static final class Builder {
            private final Map<String, Object> values = new LinkedHashMap<>();
            public SetDeviceOrientationOverrideResult build() {
                return new SetDeviceOrientationOverrideResult(values);
            }
        }
    }
    public static final class Client {
        private final CdpClient client;
        public Client(CdpClient client) { this.client = client; }
        /**
         * Clears the overridden Device Orientation.
         * @return a stage completing with the command result
         */
        public CompletionStage<ClearDeviceOrientationOverrideResult> clearDeviceOrientationOverride() {
            return client.call("DeviceOrientation.clearDeviceOrientationOverride", null, ClearDeviceOrientationOverrideResult::fromMap);
        }
        /**
         * Overrides the Device Orientation.
         * @param params command parameters
         * @return a stage completing with the command result
         */
        public CompletionStage<SetDeviceOrientationOverrideResult> setDeviceOrientationOverride(SetDeviceOrientationOverrideParams params) {
            return client.call("DeviceOrientation.setDeviceOrientationOverride", params, SetDeviceOrientationOverrideResult::fromMap);
        }
    }
}
