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
 * Chrome DevTools Protocol Performance domain.
 * @see <a href="https://chromium.googlesource.com/chromium/src/+/refs/tags/150.0.7871.213/third_party/blink/public/devtools_protocol/domains/Performance.pdl">Pinned protocol source</a>
 */
@SuppressWarnings({"EscapedEntity", "JavaLangClash", "MissingSummary", "UnusedMethod"})
public final class Performance {
    private Performance() {}
    @Nullable private static Long numberAsLong(@Nullable Object value) { return value == null ? null : ((Number) value).longValue(); }
    @Nullable private static Double numberAsDouble(@Nullable Object value) { return value == null ? null : ((Number) value).doubleValue(); }
    /**
     * Run-time execution metric.
     */
    public static final class Metric extends CdpObject {
        private Metric(Map<String, Object> values) { super(values); }
        @Nullable public static Metric fromMap(@Nullable Map<String, Object> values) {
            return values == null ? null : new Metric(values);
        }
        public static Builder builder() { return new Builder(); }
        /**
         * Metric name.
         * @return the protocol field value
         */
        @Nullable public String name() {
            return (String) value("name");
        }
        /**
         * Metric value.
         * @return the protocol field value
         */
        @Nullable public Double value() {
            return numberAsDouble(value("value"));
        }
        public static final class Builder {
            private final Map<String, Object> values = new LinkedHashMap<>();
            /**
             * Metric name.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder name(@Nullable String value) {
                if (value == null) values.remove("name");
                else values.put("name", jsonValue(value));
                return this;
            }
            /**
             * Metric value.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder value(@Nullable Double value) {
                if (value == null) values.remove("value");
                else values.put("value", jsonValue(value));
                return this;
            }
            public Metric build() {
                if (!values.containsKey("name")) throw new IllegalStateException("Missing required CDP field: name");
                if (!values.containsKey("value")) throw new IllegalStateException("Missing required CDP field: value");
                return new Metric(values);
            }
        }
    }
    /**
     * Disable collecting and reporting metrics.
     */
    public static final class DisableParams extends CdpObject {
        private DisableParams(Map<String, Object> values) { super(values); }
        @Nullable public static DisableParams fromMap(@Nullable Map<String, Object> values) {
            return values == null ? null : new DisableParams(values);
        }
        public static Builder builder() { return new Builder(); }
        public static final class Builder {
            private final Map<String, Object> values = new LinkedHashMap<>();
            public DisableParams build() {
                return new DisableParams(values);
            }
        }
    }
    /**
     * Disable collecting and reporting metrics.
     */
    public static final class DisableResult extends CdpObject {
        private DisableResult(Map<String, Object> values) { super(values); }
        @Nullable public static DisableResult fromMap(@Nullable Map<String, Object> values) {
            return values == null ? null : new DisableResult(values);
        }
        public static Builder builder() { return new Builder(); }
        public static final class Builder {
            private final Map<String, Object> values = new LinkedHashMap<>();
            public DisableResult build() {
                return new DisableResult(values);
            }
        }
    }
    /**
     * Enable collecting and reporting metrics.
     */
    public static final class EnableParams extends CdpObject {
        private EnableParams(Map<String, Object> values) { super(values); }
        @Nullable public static EnableParams fromMap(@Nullable Map<String, Object> values) {
            return values == null ? null : new EnableParams(values);
        }
        public static Builder builder() { return new Builder(); }
        /**
         * Time domain to use for collecting and reporting duration metrics.
         * @return the protocol field value
         */
        @Nullable public String timeDomain() {
            return (String) value("timeDomain");
        }
        /**
         * Time domain to use for collecting and reporting duration metrics.
         */
        public static final class TimeDomainValues {
            private TimeDomainValues() {}
            public static final String TIMETICKS = "timeTicks";
            public static final String THREADTICKS = "threadTicks";
        }
        public static final class Builder {
            private final Map<String, Object> values = new LinkedHashMap<>();
            /**
             * Time domain to use for collecting and reporting duration metrics.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder timeDomain(@Nullable String value) {
                if (value == null) values.remove("timeDomain");
                else values.put("timeDomain", jsonValue(value));
                return this;
            }
            public EnableParams build() {
                return new EnableParams(values);
            }
        }
    }
    /**
     * Enable collecting and reporting metrics.
     */
    public static final class EnableResult extends CdpObject {
        private EnableResult(Map<String, Object> values) { super(values); }
        @Nullable public static EnableResult fromMap(@Nullable Map<String, Object> values) {
            return values == null ? null : new EnableResult(values);
        }
        public static Builder builder() { return new Builder(); }
        public static final class Builder {
            private final Map<String, Object> values = new LinkedHashMap<>();
            public EnableResult build() {
                return new EnableResult(values);
            }
        }
    }
    /**
     * Sets time domain to use for collecting and reporting duration metrics. Note that this must be called before enabling metrics collection. Calling this method while metrics collection is enabled returns an error.
     * <p><b>Experimental:</b> this part of CDP may change without notice.
     * @deprecated Deprecated by the Chromium DevTools Protocol.
     */
    @Deprecated
    public static final class SetTimeDomainParams extends CdpObject {
        private SetTimeDomainParams(Map<String, Object> values) { super(values); }
        @Nullable public static SetTimeDomainParams fromMap(@Nullable Map<String, Object> values) {
            return values == null ? null : new SetTimeDomainParams(values);
        }
        public static Builder builder() { return new Builder(); }
        /**
         * Time domain
         * @return the protocol field value
         */
        @Nullable public String timeDomain() {
            return (String) value("timeDomain");
        }
        /**
         * Time domain
         */
        public static final class TimeDomainValues {
            private TimeDomainValues() {}
            public static final String TIMETICKS = "timeTicks";
            public static final String THREADTICKS = "threadTicks";
        }
        public static final class Builder {
            private final Map<String, Object> values = new LinkedHashMap<>();
            /**
             * Time domain
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder timeDomain(@Nullable String value) {
                if (value == null) values.remove("timeDomain");
                else values.put("timeDomain", jsonValue(value));
                return this;
            }
            public SetTimeDomainParams build() {
                if (!values.containsKey("timeDomain")) throw new IllegalStateException("Missing required CDP field: timeDomain");
                return new SetTimeDomainParams(values);
            }
        }
    }
    /**
     * Sets time domain to use for collecting and reporting duration metrics. Note that this must be called before enabling metrics collection. Calling this method while metrics collection is enabled returns an error.
     * <p><b>Experimental:</b> this part of CDP may change without notice.
     * @deprecated Deprecated by the Chromium DevTools Protocol.
     */
    @Deprecated
    public static final class SetTimeDomainResult extends CdpObject {
        private SetTimeDomainResult(Map<String, Object> values) { super(values); }
        @Nullable public static SetTimeDomainResult fromMap(@Nullable Map<String, Object> values) {
            return values == null ? null : new SetTimeDomainResult(values);
        }
        public static Builder builder() { return new Builder(); }
        public static final class Builder {
            private final Map<String, Object> values = new LinkedHashMap<>();
            public SetTimeDomainResult build() {
                return new SetTimeDomainResult(values);
            }
        }
    }
    /**
     * Retrieve current values of run-time metrics.
     */
    public static final class GetMetricsParams extends CdpObject {
        private GetMetricsParams(Map<String, Object> values) { super(values); }
        @Nullable public static GetMetricsParams fromMap(@Nullable Map<String, Object> values) {
            return values == null ? null : new GetMetricsParams(values);
        }
        public static Builder builder() { return new Builder(); }
        public static final class Builder {
            private final Map<String, Object> values = new LinkedHashMap<>();
            public GetMetricsParams build() {
                return new GetMetricsParams(values);
            }
        }
    }
    /**
     * Retrieve current values of run-time metrics.
     */
    public static final class GetMetricsResult extends CdpObject {
        private GetMetricsResult(Map<String, Object> values) { super(values); }
        @Nullable public static GetMetricsResult fromMap(@Nullable Map<String, Object> values) {
            return values == null ? null : new GetMetricsResult(values);
        }
        public static Builder builder() { return new Builder(); }
        /**
         * Current values for run-time metrics.
         * @return the protocol field value
         */
        @Nullable public java.util.List<Performance.Metric> metrics() {
            return list(value("metrics"), element0 -> Performance.Metric.fromMap(objectMap(element0)));
        }
        public static final class Builder {
            private final Map<String, Object> values = new LinkedHashMap<>();
            /**
             * Current values for run-time metrics.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder metrics(@Nullable java.util.List<Performance.Metric> value) {
                if (value == null) values.remove("metrics");
                else values.put("metrics", jsonValue(value));
                return this;
            }
            public GetMetricsResult build() {
                if (!values.containsKey("metrics")) throw new IllegalStateException("Missing required CDP field: metrics");
                return new GetMetricsResult(values);
            }
        }
    }
    /**
     * Current values of the metrics.
     */
    public static final class MetricsEvent extends CdpObject {
        private MetricsEvent(Map<String, Object> values) { super(values); }
        @Nullable public static MetricsEvent fromMap(@Nullable Map<String, Object> values) {
            return values == null ? null : new MetricsEvent(values);
        }
        public static Builder builder() { return new Builder(); }
        /**
         * Current values of the metrics.
         * @return the protocol field value
         */
        @Nullable public java.util.List<Performance.Metric> metrics() {
            return list(value("metrics"), element0 -> Performance.Metric.fromMap(objectMap(element0)));
        }
        /**
         * Timestamp title.
         * @return the protocol field value
         */
        @Nullable public String title() {
            return (String) value("title");
        }
        public static final class Builder {
            private final Map<String, Object> values = new LinkedHashMap<>();
            /**
             * Current values of the metrics.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder metrics(@Nullable java.util.List<Performance.Metric> value) {
                if (value == null) values.remove("metrics");
                else values.put("metrics", jsonValue(value));
                return this;
            }
            /**
             * Timestamp title.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder title(@Nullable String value) {
                if (value == null) values.remove("title");
                else values.put("title", jsonValue(value));
                return this;
            }
            public MetricsEvent build() {
                if (!values.containsKey("metrics")) throw new IllegalStateException("Missing required CDP field: metrics");
                if (!values.containsKey("title")) throw new IllegalStateException("Missing required CDP field: title");
                return new MetricsEvent(values);
            }
        }
    }
    public static final class Client {
        private final CdpClient client;
        public Client(CdpClient client) { this.client = client; }
        /**
         * Disable collecting and reporting metrics.
         * @return a stage completing with the command result
         */
        public CompletionStage<DisableResult> disable() {
            return client.call("Performance.disable", null, DisableResult::fromMap);
        }
        /**
         * Enable collecting and reporting metrics.
         * @param params command parameters
         * @return a stage completing with the command result
         */
        public CompletionStage<EnableResult> enable(EnableParams params) {
            return client.call("Performance.enable", params, EnableResult::fromMap);
        }
        /**
         * Sets time domain to use for collecting and reporting duration metrics. Note that this must be called before enabling metrics collection. Calling this method while metrics collection is enabled returns an error.
         * <p><b>Experimental:</b> this part of CDP may change without notice.
         * @param params command parameters
         * @return a stage completing with the command result
         * @deprecated Deprecated by the Chromium DevTools Protocol.
         */
        @Deprecated
        public CompletionStage<SetTimeDomainResult> setTimeDomain(SetTimeDomainParams params) {
            return client.call("Performance.setTimeDomain", params, SetTimeDomainResult::fromMap);
        }
        /**
         * Retrieve current values of run-time metrics.
         * @return a stage completing with the command result
         */
        public CompletionStage<GetMetricsResult> getMetrics() {
            return client.call("Performance.getMetrics", null, GetMetricsResult::fromMap);
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
