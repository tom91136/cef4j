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
 * Chrome DevTools Protocol DeviceOrientation domain.
 * <p><b>Experimental:</b> this part of CDP may change without notice.
 * @see <a href="https://chromium.googlesource.com/chromium/src/+/refs/tags/150.0.7871.213/third_party/blink/public/devtools_protocol/domains/DeviceOrientation.pdl">Pinned protocol source</a>
 */
@Generated("./mvnw generate-sources -pl cef4j-cdp -Dcef.version=150.0.18+gdb11278+chromium-150.0.7871.213")
@SuppressWarnings({"EscapedEntity", "InvalidParam", "JavaLangClash", "MissingSummary", "Unchecked", "UnusedMethod"})
public final class DeviceOrientation {
    private DeviceOrientation() {}
    /**
     * Overrides the Device Orientation.
     */
    public static final class SetDeviceOrientationOverrideRequest extends CdpObject {
        public SetDeviceOrientationOverrideRequest() {}
        /**
         * Overrides the Device Orientation.
         * @param alpha protocol value
         * @param beta protocol value
         * @param gamma protocol value
         */
        public SetDeviceOrientationOverrideRequest(double alpha, double beta, double gamma) {
            set("alpha", alpha);
            set("beta", beta);
            set("gamma", gamma);
        }
        public static SetDeviceOrientationOverrideRequest fromMap(Map<String, Object> values) {
            SetDeviceOrientationOverrideRequest instance_ = new SetDeviceOrientationOverrideRequest();
            if (values != null) instance_.values.putAll(values);
            return instance_;
        }
        /**
         * Mock alpha
         * @return the protocol field value
         */
        public double alpha() {
            return ((Number) require("alpha")).doubleValue();
        }
        /**
         * Mock beta
         * @return the protocol field value
         */
        public double beta() {
            return ((Number) require("beta")).doubleValue();
        }
        /**
         * Mock gamma
         * @return the protocol field value
         */
        public double gamma() {
            return ((Number) require("gamma")).doubleValue();
        }
        /**
         * Mock alpha
         * @param alpha field value
         * @return this model
         */
        public SetDeviceOrientationOverrideRequest alpha(double alpha) {
            set("alpha", alpha);
            return this;
        }
        /**
         * Mock beta
         * @param beta field value
         * @return this model
         */
        public SetDeviceOrientationOverrideRequest beta(double beta) {
            set("beta", beta);
            return this;
        }
        /**
         * Mock gamma
         * @param gamma field value
         * @return this model
         */
        public SetDeviceOrientationOverrideRequest gamma(double gamma) {
            set("gamma", gamma);
            return this;
        }
    }
    public static final class Client {
        private final CdpClient client;
        public Client(CdpClient client) { this.client = client; }
        /**
         * Clears the overridden Device Orientation.
         * @return a stage completing when the command completes
         */
        public CompletionStage<Void> clearDeviceOrientationOverride() {
            return client.call("DeviceOrientation.clearDeviceOrientationOverride", null, result_ -> null);
        }
        /**
         * Overrides the Device Orientation.
         * @param alpha protocol value
         * @param beta protocol value
         * @param gamma protocol value
         * @return a stage completing when the command completes
         */
        public CompletionStage<Void> setDeviceOrientationOverride(double alpha, double beta, double gamma) {
            Map<String, Object> params = new LinkedHashMap<>();
            params.put("alpha", CdpObject.json(alpha));
            params.put("beta", CdpObject.json(beta));
            params.put("gamma", CdpObject.json(gamma));
            return client.call("DeviceOrientation.setDeviceOrientationOverride", params, result_ -> null);
        }
        /**
         * Overrides the Device Orientation.
         * @param request request parameters
         * @return a stage completing when the command completes
         */
        public CompletionStage<Void> setDeviceOrientationOverride(SetDeviceOrientationOverrideRequest request) {
            return client.call("DeviceOrientation.setDeviceOrientationOverride", request == null ? null : request.toMap(), result_ -> null);
        }
    }
}
