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
 * The Tethering domain defines methods and events for browser port binding.
 * <p><b>Experimental:</b> this part of CDP may change without notice.
 * @see <a href="https://chromium.googlesource.com/chromium/src/+/refs/tags/146.0.7680.165/third_party/blink/public/devtools_protocol/domains/Tethering.pdl">Pinned protocol source</a>
 */
@SuppressWarnings({"EscapedEntity", "JavaLangClash", "MissingSummary", "UnusedMethod"})
public final class Tethering {
    private Tethering() {}
    @Nullable private static Long numberAsLong(@Nullable Object value) { return value == null ? null : ((Number) value).longValue(); }
    @Nullable private static Double numberAsDouble(@Nullable Object value) { return value == null ? null : ((Number) value).doubleValue(); }
    /**
     * Request browser port binding.
     */
    public static final class BindParams extends CdpObject {
        private BindParams(Map<String, Object> values) { super(values); }
        @Nullable public static BindParams fromMap(@Nullable Map<String, Object> values) {
            return values == null ? null : new BindParams(values);
        }
        public static Builder builder() { return new Builder(); }
        /**
         * Port number to bind.
         * @return the protocol field value
         */
        @Nullable public Long port() {
            return numberAsLong(value("port"));
        }
        public static final class Builder {
            private final Map<String, Object> values = new LinkedHashMap<>();
            /**
             * Port number to bind.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder port(@Nullable Long value) {
                if (value == null) values.remove("port");
                else values.put("port", jsonValue(value));
                return this;
            }
            public BindParams build() {
                if (!values.containsKey("port")) throw new IllegalStateException("Missing required CDP field: port");
                return new BindParams(values);
            }
        }
    }
    /**
     * Request browser port binding.
     */
    public static final class BindResult extends CdpObject {
        private BindResult(Map<String, Object> values) { super(values); }
        @Nullable public static BindResult fromMap(@Nullable Map<String, Object> values) {
            return values == null ? null : new BindResult(values);
        }
        public static Builder builder() { return new Builder(); }
        public static final class Builder {
            private final Map<String, Object> values = new LinkedHashMap<>();
            public BindResult build() {
                return new BindResult(values);
            }
        }
    }
    /**
     * Request browser port unbinding.
     */
    public static final class UnbindParams extends CdpObject {
        private UnbindParams(Map<String, Object> values) { super(values); }
        @Nullable public static UnbindParams fromMap(@Nullable Map<String, Object> values) {
            return values == null ? null : new UnbindParams(values);
        }
        public static Builder builder() { return new Builder(); }
        /**
         * Port number to unbind.
         * @return the protocol field value
         */
        @Nullable public Long port() {
            return numberAsLong(value("port"));
        }
        public static final class Builder {
            private final Map<String, Object> values = new LinkedHashMap<>();
            /**
             * Port number to unbind.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder port(@Nullable Long value) {
                if (value == null) values.remove("port");
                else values.put("port", jsonValue(value));
                return this;
            }
            public UnbindParams build() {
                if (!values.containsKey("port")) throw new IllegalStateException("Missing required CDP field: port");
                return new UnbindParams(values);
            }
        }
    }
    /**
     * Request browser port unbinding.
     */
    public static final class UnbindResult extends CdpObject {
        private UnbindResult(Map<String, Object> values) { super(values); }
        @Nullable public static UnbindResult fromMap(@Nullable Map<String, Object> values) {
            return values == null ? null : new UnbindResult(values);
        }
        public static Builder builder() { return new Builder(); }
        public static final class Builder {
            private final Map<String, Object> values = new LinkedHashMap<>();
            public UnbindResult build() {
                return new UnbindResult(values);
            }
        }
    }
    /**
     * Informs that port was successfully bound and got a specified connection id.
     */
    public static final class AcceptedEvent extends CdpObject {
        private AcceptedEvent(Map<String, Object> values) { super(values); }
        @Nullable public static AcceptedEvent fromMap(@Nullable Map<String, Object> values) {
            return values == null ? null : new AcceptedEvent(values);
        }
        public static Builder builder() { return new Builder(); }
        /**
         * Port number that was successfully bound.
         * @return the protocol field value
         */
        @Nullable public Long port() {
            return numberAsLong(value("port"));
        }
        /**
         * Connection id to be used.
         * @return the protocol field value
         */
        @Nullable public String connectionId() {
            return (String) value("connectionId");
        }
        public static final class Builder {
            private final Map<String, Object> values = new LinkedHashMap<>();
            /**
             * Port number that was successfully bound.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder port(@Nullable Long value) {
                if (value == null) values.remove("port");
                else values.put("port", jsonValue(value));
                return this;
            }
            /**
             * Connection id to be used.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder connectionId(@Nullable String value) {
                if (value == null) values.remove("connectionId");
                else values.put("connectionId", jsonValue(value));
                return this;
            }
            public AcceptedEvent build() {
                if (!values.containsKey("port")) throw new IllegalStateException("Missing required CDP field: port");
                if (!values.containsKey("connectionId")) throw new IllegalStateException("Missing required CDP field: connectionId");
                return new AcceptedEvent(values);
            }
        }
    }
    public static final class Client {
        private final CdpClient client;
        public Client(CdpClient client) { this.client = client; }
        /**
         * Request browser port binding.
         * @param params command parameters
         * @return a stage completing with the command result
         */
        public CompletionStage<BindResult> bind(BindParams params) {
            return client.call("Tethering.bind", params, BindResult::fromMap);
        }
        /**
         * Request browser port unbinding.
         * @param params command parameters
         * @return a stage completing with the command result
         */
        public CompletionStage<UnbindResult> unbind(UnbindParams params) {
            return client.call("Tethering.unbind", params, UnbindResult::fromMap);
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
