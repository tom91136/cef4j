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
 * Chrome DevTools Protocol DeviceOrientation domain.
 * <p><b>Experimental:</b> this part of CDP may change without notice.
 * @see <a href="https://chromium.googlesource.com/chromium/src/+/refs/tags/150.0.7871.213/third_party/blink/public/devtools_protocol/domains/DeviceOrientation.pdl">Pinned protocol source</a>
 */
@Generated("mvn generate-sources -pl cef4j-platform -Dcef.version=150.0.18+gdb11278+chromium-150.0.7871.213")
@SuppressWarnings({"EscapedEntity", "InvalidParam", "JavaLangClash", "MissingSummary", "Unchecked", "UnusedMethod"})
public final class DeviceOrientation {
    private DeviceOrientation() {}
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
    }
}
