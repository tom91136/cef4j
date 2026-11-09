// GENERATED - do not edit. Regenerate via: ./mvnw generate-sources -pl cef4j-cdp -Dcef.version=150.0.18+gdb11278+chromium-150.0.7871.213
package net.kurobako.cef4j.cdp.generated;

import java.util.Map;
import java.util.concurrent.CompletionStage;
import javax.annotation.processing.Generated;
import net.kurobako.cef4j.cdp.CdpClient;
import net.kurobako.cef4j.cdp.CdpDomainClient;
import net.kurobako.cef4j.cdp.CdpField;
import net.kurobako.cef4j.cdp.CdpObject;
import net.kurobako.cef4j.policy.NullableBoundary;

/**
 * This domain is deprecated.
 * @deprecated Deprecated by the Chromium DevTools Protocol.
 * @see <a href="https://chromium.googlesource.com/v8/v8/+/3063ea3a0737a3fc4d4ed3babd595f1cace1e6ac/include/js_protocol.pdl">Pinned protocol source</a>
 */
@Deprecated
@Generated("./mvnw generate-sources -pl cef4j-cdp -Dcef.version=150.0.18+gdb11278+chromium-150.0.7871.213")
@SuppressWarnings({"EscapedEntity", "InvalidParam", "JavaLangClash", "MissingSummary", "Unchecked", "UnusedMethod"})
@NullableBoundary("CDP wire objects use null for absent optional fields")
public final class Schema {
    private Schema() {}
    private static final CdpField<java.util.List<Schema.Domain>> DOMAINS = CdpField.of("domains", value -> CdpObject.requireList(value, element0 -> java.util.Objects.requireNonNull(Schema.Domain.fromMap(CdpObject.requireObjectMap(element0)))));
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
            return requireString("name");
        }
        /**
         * Domain version.
         * @return the protocol field value
         */
        public String version() {
            return requireString("version");
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
    public static final class Client extends CdpDomainClient {
        public Client(CdpClient client) { super(client); }
        /**
         * Returns supported domains.
         * @return a stage completing with the command result
         */
        public CompletionStage<java.util.List<Schema.Domain>> getDomains() {
            return call("Schema.getDomains", DOMAINS::require);
        }
    }
}
