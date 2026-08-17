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
 * A domain for ad-related metrics and data.
 * <p><b>Experimental:</b> this part of CDP may change without notice.
 * @see <a href="https://chromium.googlesource.com/chromium/src/+/refs/tags/150.0.7871.213/third_party/blink/public/devtools_protocol/domains/Ads.pdl">Pinned protocol source</a>
 */
@SuppressWarnings({"EscapedEntity", "JavaLangClash", "MissingSummary", "UnusedMethod"})
public final class Ads {
    private Ads() {}
    @Nullable private static Long numberAsLong(@Nullable Object value) { return value == null ? null : ((Number) value).longValue(); }
    @Nullable private static Double numberAsDouble(@Nullable Object value) { return value == null ? null : ((Number) value).doubleValue(); }
    /**
     * Ad metrics for a page.
     */
    public static final class AdMetrics extends CdpObject {
        private AdMetrics(Map<String, Object> values) { super(values); }
        @Nullable public static AdMetrics fromMap(@Nullable Map<String, Object> values) {
            return values == null ? null : new AdMetrics(values);
        }
        public static Builder builder() { return new Builder(); }
        /**
         * The viewport ad density by area, represented as a percentage (an integer between 0 and 100).
         * @return the protocol field value
         */
        @Nullable public Long viewportAdDensityByArea() {
            return numberAsLong(value("viewportAdDensityByArea"));
        }
        /**
         * The time-weighted average of the viewport ad density by area, measured across the duration of the page.
         * @return the protocol field value
         */
        @Nullable public Double averageViewportAdDensityByArea() {
            return numberAsDouble(value("averageViewportAdDensityByArea"));
        }
        /**
         * The number of ads currently visible within the viewport.
         * @return the protocol field value
         */
        @Nullable public Long viewportAdCount() {
            return numberAsLong(value("viewportAdCount"));
        }
        /**
         * The time-weighted average of the viewport ad count, measured across the duration of the page.
         * @return the protocol field value
         */
        @Nullable public Double averageViewportAdCount() {
            return numberAsDouble(value("averageViewportAdCount"));
        }
        /**
         * The total ad CPU usage, in milliseconds.
         * @return the protocol field value
         */
        @Nullable public Double totalAdCpuTime() {
            return numberAsDouble(value("totalAdCpuTime"));
        }
        /**
         * The total ad network bytes.
         * @return the protocol field value
         */
        @Nullable public Double totalAdNetworkBytes() {
            return numberAsDouble(value("totalAdNetworkBytes"));
        }
        public static final class Builder {
            private final Map<String, Object> values = new LinkedHashMap<>();
            /**
             * The viewport ad density by area, represented as a percentage (an integer between 0 and 100).
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder viewportAdDensityByArea(@Nullable Long value) {
                if (value == null) values.remove("viewportAdDensityByArea");
                else values.put("viewportAdDensityByArea", jsonValue(value));
                return this;
            }
            /**
             * The time-weighted average of the viewport ad density by area, measured across the duration of the page.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder averageViewportAdDensityByArea(@Nullable Double value) {
                if (value == null) values.remove("averageViewportAdDensityByArea");
                else values.put("averageViewportAdDensityByArea", jsonValue(value));
                return this;
            }
            /**
             * The number of ads currently visible within the viewport.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder viewportAdCount(@Nullable Long value) {
                if (value == null) values.remove("viewportAdCount");
                else values.put("viewportAdCount", jsonValue(value));
                return this;
            }
            /**
             * The time-weighted average of the viewport ad count, measured across the duration of the page.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder averageViewportAdCount(@Nullable Double value) {
                if (value == null) values.remove("averageViewportAdCount");
                else values.put("averageViewportAdCount", jsonValue(value));
                return this;
            }
            /**
             * The total ad CPU usage, in milliseconds.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder totalAdCpuTime(@Nullable Double value) {
                if (value == null) values.remove("totalAdCpuTime");
                else values.put("totalAdCpuTime", jsonValue(value));
                return this;
            }
            /**
             * The total ad network bytes.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder totalAdNetworkBytes(@Nullable Double value) {
                if (value == null) values.remove("totalAdNetworkBytes");
                else values.put("totalAdNetworkBytes", jsonValue(value));
                return this;
            }
            public AdMetrics build() {
                if (!values.containsKey("viewportAdDensityByArea")) throw new IllegalStateException("Missing required CDP field: viewportAdDensityByArea");
                if (!values.containsKey("averageViewportAdDensityByArea")) throw new IllegalStateException("Missing required CDP field: averageViewportAdDensityByArea");
                if (!values.containsKey("viewportAdCount")) throw new IllegalStateException("Missing required CDP field: viewportAdCount");
                if (!values.containsKey("averageViewportAdCount")) throw new IllegalStateException("Missing required CDP field: averageViewportAdCount");
                if (!values.containsKey("totalAdCpuTime")) throw new IllegalStateException("Missing required CDP field: totalAdCpuTime");
                if (!values.containsKey("totalAdNetworkBytes")) throw new IllegalStateException("Missing required CDP field: totalAdNetworkBytes");
                return new AdMetrics(values);
            }
        }
    }
    /**
     * Retrieves ad metrics for the current page.
     */
    public static final class GetAdMetricsParams extends CdpObject {
        private GetAdMetricsParams(Map<String, Object> values) { super(values); }
        @Nullable public static GetAdMetricsParams fromMap(@Nullable Map<String, Object> values) {
            return values == null ? null : new GetAdMetricsParams(values);
        }
        public static Builder builder() { return new Builder(); }
        public static final class Builder {
            private final Map<String, Object> values = new LinkedHashMap<>();
            public GetAdMetricsParams build() {
                return new GetAdMetricsParams(values);
            }
        }
    }
    /**
     * Retrieves ad metrics for the current page.
     */
    public static final class GetAdMetricsResult extends CdpObject {
        private GetAdMetricsResult(Map<String, Object> values) { super(values); }
        @Nullable public static GetAdMetricsResult fromMap(@Nullable Map<String, Object> values) {
            return values == null ? null : new GetAdMetricsResult(values);
        }
        public static Builder builder() { return new Builder(); }
        /**
         * Returns the metrics field.
         * @return the protocol field value
         */
        @Nullable public Ads.AdMetrics metrics() {
            return Ads.AdMetrics.fromMap(objectMap(value("metrics")));
        }
        public static final class Builder {
            private final Map<String, Object> values = new LinkedHashMap<>();
            /**
             * Sets the metrics field.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder metrics(@Nullable Ads.AdMetrics value) {
                if (value == null) values.remove("metrics");
                else values.put("metrics", jsonValue(value));
                return this;
            }
            public GetAdMetricsResult build() {
                if (!values.containsKey("metrics")) throw new IllegalStateException("Missing required CDP field: metrics");
                return new GetAdMetricsResult(values);
            }
        }
    }
    public static final class Client {
        private final CdpClient client;
        public Client(CdpClient client) { this.client = client; }
        /**
         * Retrieves ad metrics for the current page.
         * @return a stage completing with the command result
         */
        public CompletionStage<GetAdMetricsResult> getAdMetrics() {
            return client.call("Ads.getAdMetrics", null, GetAdMetricsResult::fromMap);
        }
    }
}
