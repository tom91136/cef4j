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
 * This domain is deprecated.
 * @deprecated Deprecated by the Chromium DevTools Protocol.
 * @see <a href="https://chromium.googlesource.com/v8/v8/+/3063ea3a0737a3fc4d4ed3babd595f1cace1e6ac/include/js_protocol.pdl">Pinned protocol source</a>
 */
@Deprecated
@Generated("mvn generate-sources -pl cef4j-platform -Dcef.version=150.0.18+gdb11278+chromium-150.0.7871.213")
@SuppressWarnings({"EscapedEntity", "InvalidParam", "JavaLangClash", "MissingSummary", "Unchecked", "UnusedMethod"})
public final class Schema {
    private Schema() {}
    /**
     * Description of the protocol domain.
     */
    public static final class Domain extends CdpObject {
        public Domain() {}
        private Domain(Map<String, Object> values) { super(values); }
        public static Domain fromMap(Map<String, Object> values) {
            return new Domain(values);
        }
        /**
         * Domain name.
         * @return the protocol field value
         */
        public String name() {
            return (String) require("name");
        }
        /**
         * Domain version.
         * @return the protocol field value
         */
        public String version() {
            return (String) require("version");
        }
        /**
         * Domain name.
         * @param name field value
         * @return this model
         */
        public Domain name(String name) {
            set("name", name);
            return this;
        }
        /**
         * Domain version.
         * @param version field value
         * @return this model
         */
        public Domain version(String version) {
            set("version", version);
            return this;
        }
    }
    public static final class Client {
        private final CdpClient client;
        public Client(CdpClient client) { this.client = client; }
        /**
         * Returns supported domains.
         * @return a stage completing with the command result
         */
        public CompletionStage<java.util.List<Schema.Domain>> getDomains() {
            return client.call("Schema.getDomains", null, result_ -> CdpObject.requireList(java.util.Objects.requireNonNull(result_.get("domains")), element0 -> java.util.Objects.requireNonNull(Schema.Domain.fromMap(java.util.Objects.requireNonNull(CdpObject.objectMap(element0))))));
        }
    }
}
