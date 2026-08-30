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
 * Chrome DevTools Protocol Performance domain.
 * @see <a href="https://chromium.googlesource.com/chromium/src/+/refs/tags/150.0.7871.213/third_party/blink/public/devtools_protocol/domains/Performance.pdl">Pinned protocol source</a>
 */
@Generated("./mvnw generate-sources -pl cef4j-cdp -Dcef.version=150.0.18+gdb11278+chromium-150.0.7871.213")
@SuppressWarnings({"EscapedEntity", "InvalidParam", "JavaLangClash", "MissingSummary", "Unchecked", "UnusedMethod"})
public final class Performance {
    private Performance() {}
    /**
     * Run-time execution metric.
     */
    public static final class Metric extends CdpObject {
        public Metric() {}
        private Metric(Map<String, Object> values) { super(values); }
        public static Metric fromMap(Map<String, Object> values) {
            return new Metric(values);
        }
        /**
         * Metric name.
         * @return the protocol field value
         */
        public String name() {
            return (String) require("name");
        }
        /**
         * Metric value.
         * @return the protocol field value
         */
        public double value() {
            return ((Number) require("value")).doubleValue();
        }
        /**
         * Metric name.
         * @param name field value
         * @return this model
         */
        public Metric name(String name) {
            set("name", name);
            return this;
        }
        /**
         * Metric value.
         * @param value field value
         * @return this model
         */
        public Metric value(double value) {
            set("value", value);
            return this;
        }
    }
    /**
     * Enable collecting and reporting metrics.
     */
    public static final class EnableRequest extends CdpObject {
        public EnableRequest() {}
        public static EnableRequest fromMap(Map<String, Object> values) {
            EnableRequest instance_ = new EnableRequest();
            if (values != null) instance_.values.putAll(values);
            return instance_;
        }
        /**
         * Time domain to use for collecting and reporting duration metrics.
         * @return the protocol field value, empty when absent
         */
        public Optional<EnableTimeDomainValues> timeDomain() {
            return Optional.ofNullable(raw("timeDomain") == null ? null : EnableTimeDomainValues.of((String) raw("timeDomain")));
        }
        /**
         * Time domain to use for collecting and reporting duration metrics.
         * @param timeDomain field value; empty omits the value
         * @return this model
         */
        public EnableRequest timeDomain(Optional<EnableTimeDomainValues> timeDomain) {
            set("timeDomain", timeDomain.orElse(null));
            return this;
        }
        /**
         * Time domain to use for collecting and reporting duration metrics.
         * @param timeDomain field value; null removes the value
         * @return this model
         */
        public EnableRequest timeDomain(EnableTimeDomainValues timeDomain) {
            set("timeDomain", timeDomain);
            return this;
        }
    }
    /**
     * Sets time domain to use for collecting and reporting duration metrics. Note that this must be called before enabling metrics collection. Calling this method while metrics collection is enabled returns an error.
     * <p><b>Experimental:</b> this part of CDP may change without notice.
     * @deprecated Deprecated by the Chromium DevTools Protocol.
     */
    @Deprecated
    public static final class SetTimeDomainRequest extends CdpObject {
        public SetTimeDomainRequest() {}
        /**
         * Sets time domain to use for collecting and reporting duration metrics. Note that this must be called before enabling metrics collection. Calling this method while metrics collection is enabled returns an error.
         * <p><b>Experimental:</b> this part of CDP may change without notice.
         * @param timeDomain protocol value
         * @deprecated Deprecated by the Chromium DevTools Protocol.
         */
        @Deprecated
        public SetTimeDomainRequest(SetTimeDomainTimeDomainValues timeDomain) {
            set("timeDomain", timeDomain);
        }
        public static SetTimeDomainRequest fromMap(Map<String, Object> values) {
            SetTimeDomainRequest instance_ = new SetTimeDomainRequest();
            if (values != null) instance_.values.putAll(values);
            return instance_;
        }
        /**
         * Time domain
         * @return the protocol field value
         */
        public SetTimeDomainTimeDomainValues timeDomain() {
            return SetTimeDomainTimeDomainValues.of((String) require("timeDomain"));
        }
        /**
         * Time domain
         * @param timeDomain field value
         * @return this model
         */
        public SetTimeDomainRequest timeDomain(SetTimeDomainTimeDomainValues timeDomain) {
            set("timeDomain", timeDomain);
            return this;
        }
    }
    /**
     * Current values of the metrics.
     */
    public static final class MetricsEvent extends CdpObject {
        public MetricsEvent() {}
        private MetricsEvent(Map<String, Object> values) { super(values); }
        public static MetricsEvent fromMap(Map<String, Object> values) {
            return new MetricsEvent(values);
        }
        /**
         * Current values of the metrics.
         * @return the protocol field value
         */
        public java.util.List<Performance.Metric> metrics() {
            return CdpObject.requireList(require("metrics"), element0 -> java.util.Objects.requireNonNull(Performance.Metric.fromMap(java.util.Objects.requireNonNull(CdpObject.objectMap(element0)))));
        }
        /**
         * Timestamp title.
         * @return the protocol field value
         */
        public String title() {
            return (String) require("title");
        }
        /**
         * Current values of the metrics.
         * @param metrics field value
         * @return this model
         */
        public MetricsEvent metrics(java.util.List<Performance.Metric> metrics) {
            set("metrics", metrics);
            return this;
        }
        /**
         * Timestamp title.
         * @param title field value
         * @return this model
         */
        public MetricsEvent title(String title) {
            set("title", title);
            return this;
        }
    }
    /**
     * Time domain to use for collecting and reporting duration metrics.
     */
    public enum EnableTimeDomainValues implements CdpValue<String> {
        TIMETICKS("timeTicks"),
        THREADTICKS("threadTicks");
        public final String value;
        EnableTimeDomainValues(String value) { this.value = value; }
        @Nonnull public String value() { return value; }
        public static EnableTimeDomainValues of(@Nonnull String value) {
            for (EnableTimeDomainValues constant : values()) {
                if (constant.value.equals(value)) return constant;
            }
            throw new IllegalArgumentException("Unknown EnableTimeDomainValues value: " + value);
        }
    }
    /**
     * Time domain
     */
    public enum SetTimeDomainTimeDomainValues implements CdpValue<String> {
        TIMETICKS("timeTicks"),
        THREADTICKS("threadTicks");
        public final String value;
        SetTimeDomainTimeDomainValues(String value) { this.value = value; }
        @Nonnull public String value() { return value; }
        public static SetTimeDomainTimeDomainValues of(@Nonnull String value) {
            for (SetTimeDomainTimeDomainValues constant : values()) {
                if (constant.value.equals(value)) return constant;
            }
            throw new IllegalArgumentException("Unknown SetTimeDomainTimeDomainValues value: " + value);
        }
    }
    public static final class Client {
        private final CdpClient client;
        public Client(CdpClient client) { this.client = client; }
        /**
         * Disable collecting and reporting metrics.
         * @return a stage completing when the command completes
         */
        public CompletionStage<Void> disable() {
            return client.call("Performance.disable", null, result_ -> null);
        }
        /**
         * Enable collecting and reporting metrics.
         * @param timeDomain protocol value
         * @return a stage completing when the command completes
         */
        public CompletionStage<Void> enable(Optional<EnableTimeDomainValues> timeDomain) {
            Map<String, Object> params = new LinkedHashMap<>();
            timeDomain.ifPresent(value_ -> params.put("timeDomain", CdpObject.json(value_)));
            return client.call("Performance.enable", params, result_ -> null);
        }
        /**
         * Enable collecting and reporting metrics.
         * @return a stage completing when the command completes
         */
        public CompletionStage<Void> enable() {
            return enable(Optional.empty());
        }
        /**
         * Enable collecting and reporting metrics.
         * @param request request parameters
         * @return a stage completing when the command completes
         */
        public CompletionStage<Void> enable(EnableRequest request) {
            return client.call("Performance.enable", request == null ? null : request.toMap(), result_ -> null);
        }
        /**
         * Sets time domain to use for collecting and reporting duration metrics. Note that this must be called before enabling metrics collection. Calling this method while metrics collection is enabled returns an error.
         * <p><b>Experimental:</b> this part of CDP may change without notice.
         * @param timeDomain protocol value
         * @return a stage completing when the command completes
         * @deprecated Deprecated by the Chromium DevTools Protocol.
         */
        @Deprecated
        public CompletionStage<Void> setTimeDomain(SetTimeDomainTimeDomainValues timeDomain) {
            Map<String, Object> params = new LinkedHashMap<>();
            params.put("timeDomain", CdpObject.json(timeDomain));
            return client.call("Performance.setTimeDomain", params, result_ -> null);
        }
        /**
         * Sets time domain to use for collecting and reporting duration metrics. Note that this must be called before enabling metrics collection. Calling this method while metrics collection is enabled returns an error.
         * <p><b>Experimental:</b> this part of CDP may change without notice.
         * @param request request parameters
         * @return a stage completing when the command completes
         * @deprecated Deprecated by the Chromium DevTools Protocol.
         */
        @Deprecated
        public CompletionStage<Void> setTimeDomain(SetTimeDomainRequest request) {
            return client.call("Performance.setTimeDomain", request == null ? null : request.toMap(), result_ -> null);
        }
        /**
         * Retrieve current values of run-time metrics.
         * @return a stage completing with the command result
         */
        public CompletionStage<java.util.List<Performance.Metric>> getMetrics() {
            return client.call("Performance.getMetrics", null, result_ -> CdpObject.requireList(java.util.Objects.requireNonNull(result_.get("metrics")), element0 -> java.util.Objects.requireNonNull(Performance.Metric.fromMap(java.util.Objects.requireNonNull(CdpObject.objectMap(element0))))));
        }
        /**
         * Current values of the metrics.
         * @param handler event callback
         * @return a removable subscription
         */
        public CdpSubscription onMetrics(Consumer<MetricsEvent> handler) {
            return client.on("Performance.metrics", MetricsEvent::fromMap, handler);
        }
    }
}
