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
 * The Tethering domain defines methods and events for browser port binding.
 * <p><b>Experimental:</b> this part of CDP may change without notice.
 * @see <a href="https://chromium.googlesource.com/chromium/src/+/refs/tags/150.0.7871.213/third_party/blink/public/devtools_protocol/domains/Tethering.pdl">Pinned protocol source</a>
 */
@Generated("mvn generate-sources -pl cef4j-platform -Dcef.version=150.0.18+gdb11278+chromium-150.0.7871.213")
@SuppressWarnings({"EscapedEntity", "InvalidParam", "JavaLangClash", "MissingSummary", "Unchecked", "UnusedMethod"})
public final class Tethering {
    private Tethering() {}
    /**
     * Informs that port was successfully bound and got a specified connection id.
     */
    public static final class AcceptedEvent extends CdpObject {
        public AcceptedEvent() {}
        private AcceptedEvent(Map<String, Object> values) { super(values); }
        public static AcceptedEvent fromMap(Map<String, Object> values) {
            return new AcceptedEvent(values);
        }
        /**
         * Port number that was successfully bound.
         * @return the protocol field value
         */
        public long port() {
            return ((Number) require("port")).longValue();
        }
        /**
         * Connection id to be used.
         * @return the protocol field value
         */
        public String connectionId() {
            return (String) require("connectionId");
        }
        /**
         * Port number that was successfully bound.
         * @param port field value
         * @return this model
         */
        public AcceptedEvent port(long port) {
            set("port", port);
            return this;
        }
        /**
         * Connection id to be used.
         * @param connectionId field value
         * @return this model
         */
        public AcceptedEvent connectionId(String connectionId) {
            set("connectionId", connectionId);
            return this;
        }
    }
    public static final class Client {
        private final CdpClient client;
        public Client(CdpClient client) { this.client = client; }
        /**
         * Request browser port binding.
         * @param port protocol value
         * @return a stage completing when the command completes
         */
        public CompletionStage<Void> bind(long port) {
            Map<String, Object> params = new LinkedHashMap<>();
            params.put("port", CdpObject.json(port));
            return client.call("Tethering.bind", params, result_ -> null);
        }
        /**
         * Request browser port unbinding.
         * @param port protocol value
         * @return a stage completing when the command completes
         */
        public CompletionStage<Void> unbind(long port) {
            Map<String, Object> params = new LinkedHashMap<>();
            params.put("port", CdpObject.json(port));
            return client.call("Tethering.unbind", params, result_ -> null);
        }
        /**
         * Informs that port was successfully bound and got a specified connection id.
         * @param handler event callback
         * @return a removable subscription
         */
        public CdpSubscription onAccepted(Consumer<AcceptedEvent> handler) {
            return client.on("Tethering.accepted", AcceptedEvent::fromMap, handler);
        }
    }
}
