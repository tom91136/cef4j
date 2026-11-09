// GENERATED - do not edit. Regenerate via: ./mvnw generate-sources -pl cef4j-cdp -Dcef.version=150.0.18+gdb11278+chromium-150.0.7871.213
package net.kurobako.cef4j.cdp.generated;

import java.util.Map;
import java.util.concurrent.CompletionStage;
import javax.annotation.processing.Generated;
import net.kurobako.cef4j.cdp.CdpClient;
import net.kurobako.cef4j.cdp.CdpDomainClient;
import net.kurobako.cef4j.cdp.CdpObject;
import net.kurobako.cef4j.policy.NullableBoundary;

/**
 * Chrome DevTools Protocol DeviceOrientation domain.
 * <p><b>Experimental:</b> this part of CDP may change without notice.
 * @see <a href="https://chromium.googlesource.com/chromium/src/+/refs/tags/150.0.7871.213/third_party/blink/public/devtools_protocol/domains/DeviceOrientation.pdl">Pinned protocol source</a>
 */
@Generated("./mvnw generate-sources -pl cef4j-cdp -Dcef.version=150.0.18+gdb11278+chromium-150.0.7871.213")
@SuppressWarnings({"EscapedEntity", "InvalidParam", "JavaLangClash", "MissingSummary", "Unchecked", "UnusedMethod"})
@NullableBoundary("CDP wire objects use null for absent optional fields")
public final class DeviceOrientation {
    private DeviceOrientation() {}
    /**
     * Overrides the Device Orientation.
     */
    public static final class SetDeviceOrientationOverrideRequest extends CdpObject {
        public SetDeviceOrientationOverrideRequest() {}
        public static SetDeviceOrientationOverrideRequest fromMap(Map<String, Object> values) {
            final SetDeviceOrientationOverrideRequest instance_ = new SetDeviceOrientationOverrideRequest();
            if (values != null) instance_.values.putAll(values);
            return instance_;
        }
        /**
         * Mock alpha
         * @return the protocol field value
         */
        public double alpha() {
            return requireDouble("alpha");
        }
        /**
         * Mock beta
         * @return the protocol field value
         */
        public double beta() {
            return requireDouble("beta");
        }
        /**
         * Mock gamma
         * @return the protocol field value
         */
        public double gamma() {
            return requireDouble("gamma");
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
    public static final class Client extends CdpDomainClient {
        public Client(CdpClient client) { super(client); }
        /**
         * Clears the overridden Device Orientation.
         * @return a stage completing when the command completes
         */
        public CompletionStage<Void> clearDeviceOrientationOverride() {
            return call("DeviceOrientation.clearDeviceOrientationOverride");
        }
        /**
         * Overrides the Device Orientation.
         * @param alpha protocol value
         * @param beta protocol value
         * @param gamma protocol value
         * @return a stage completing when the command completes
         */
        public CompletionStage<Void> setDeviceOrientationOverride(double alpha, double beta, double gamma) {
            return call("DeviceOrientation.setDeviceOrientationOverride", params("alpha", alpha, "beta", beta, "gamma", gamma));
        }
        /**
         * Overrides the Device Orientation.
         * @param request request parameters
         * @return a stage completing when the command completes
         */
        public CompletionStage<Void> setDeviceOrientationOverride(SetDeviceOrientationOverrideRequest request) {
            return call("DeviceOrientation.setDeviceOrientationOverride", request);
        }
    }
}
