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
 * This domain is deprecated.
 * @deprecated Deprecated by the Chromium DevTools Protocol.
 * @see <a href="https://chromium.googlesource.com/v8/v8/+/0e999a528db40a3ef6fa917adf96370a18b87d70/include/js_protocol.pdl">Pinned protocol source</a>
 */
@Deprecated
@SuppressWarnings({"JavaLangClash", "UnusedMethod"})
public final class Schema {
    private Schema() {}
    @Nullable private static Long numberAsLong(@Nullable Object value) { return value == null ? null : ((Number) value).longValue(); }
    @Nullable private static Double numberAsDouble(@Nullable Object value) { return value == null ? null : ((Number) value).doubleValue(); }
    /**
     * Description of the protocol domain.
     */
    public static final class Domain extends CdpObject {
        private Domain(Map<String, Object> values) { super(values); }
        @Nullable public static Domain fromMap(@Nullable Map<String, Object> values) {
            return values == null ? null : new Domain(values);
        }
        public static Builder builder() { return new Builder(); }
        /**
         * Domain name.
         * @return the protocol field value
         */
        @Nullable public String name() {
            return (String) value("name");
        }
        /**
         * Domain version.
         * @return the protocol field value
         */
        @Nullable public String version() {
            return (String) value("version");
        }
        public static final class Builder {
            private final Map<String, Object> values = new LinkedHashMap<>();
            /**
             * Domain name.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder name(@Nullable String value) {
                if (value == null) values.remove("name");
                else values.put("name", jsonValue(value));
                return this;
            }
            /**
             * Domain version.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder version(@Nullable String value) {
                if (value == null) values.remove("version");
                else values.put("version", jsonValue(value));
                return this;
            }
            public Domain build() {
                if (!values.containsKey("name")) throw new IllegalStateException("Missing required CDP field: name");
                if (!values.containsKey("version")) throw new IllegalStateException("Missing required CDP field: version");
                return new Domain(values);
            }
        }
    }
    /**
     * Returns supported domains.
     */
    public static final class GetDomainsParams extends CdpObject {
        private GetDomainsParams(Map<String, Object> values) { super(values); }
        @Nullable public static GetDomainsParams fromMap(@Nullable Map<String, Object> values) {
            return values == null ? null : new GetDomainsParams(values);
        }
        public static Builder builder() { return new Builder(); }
        public static final class Builder {
            private final Map<String, Object> values = new LinkedHashMap<>();
            public GetDomainsParams build() {
                return new GetDomainsParams(values);
            }
        }
    }
    /**
     * Returns supported domains.
     */
    public static final class GetDomainsResult extends CdpObject {
        private GetDomainsResult(Map<String, Object> values) { super(values); }
        @Nullable public static GetDomainsResult fromMap(@Nullable Map<String, Object> values) {
            return values == null ? null : new GetDomainsResult(values);
        }
        public static Builder builder() { return new Builder(); }
        /**
         * List of supported domains.
         * @return the protocol field value
         */
        @Nullable public java.util.List<Schema.Domain> domains() {
            return list(value("domains"), element0 -> Schema.Domain.fromMap(objectMap(element0)));
        }
        public static final class Builder {
            private final Map<String, Object> values = new LinkedHashMap<>();
            /**
             * List of supported domains.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder domains(@Nullable java.util.List<Schema.Domain> value) {
                if (value == null) values.remove("domains");
                else values.put("domains", jsonValue(value));
                return this;
            }
            public GetDomainsResult build() {
                if (!values.containsKey("domains")) throw new IllegalStateException("Missing required CDP field: domains");
                return new GetDomainsResult(values);
            }
        }
    }
    public static final class Client {
        private final CdpClient client;
        public Client(CdpClient client) { this.client = client; }
        /**
         * Returns supported domains.
         * @return a stage completing with the command result
         */
        public CompletionStage<GetDomainsResult> getDomains() {
            return client.call("Schema.getDomains", null, GetDomainsResult::fromMap);
        }
    }
}
