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
 * A domain for ad-related metrics and data.
 * <p><b>Experimental:</b> this part of CDP may change without notice.
 * @see <a href="https://chromium.googlesource.com/chromium/src/+/refs/tags/150.0.7871.213/third_party/blink/public/devtools_protocol/domains/Ads.pdl">Pinned protocol source</a>
 */
@Generated("./mvnw generate-sources -pl cef4j-cdp -Dcef.version=150.0.18+gdb11278+chromium-150.0.7871.213")
@SuppressWarnings({"EscapedEntity", "InvalidParam", "JavaLangClash", "MissingSummary", "Unchecked", "UnusedMethod"})
public final class Ads {
    private Ads() {}
    /**
     * Ad metrics for a page.
     */
    public static final class AdMetrics extends CdpObject {
        public AdMetrics() {}
        private AdMetrics(Map<String, Object> values) { super(values); }
        public static AdMetrics fromMap(Map<String, Object> values) {
            return new AdMetrics(values);
        }
        /**
         * The viewport ad density by area, represented as a percentage (an integer between 0 and 100).
         * @return the protocol field value
         */
        public long viewportAdDensityByArea() {
            return ((Number) require("viewportAdDensityByArea")).longValue();
        }
        /**
         * The time-weighted average of the viewport ad density by area, measured across the duration of the page.
         * @return the protocol field value
         */
        public double averageViewportAdDensityByArea() {
            return ((Number) require("averageViewportAdDensityByArea")).doubleValue();
        }
        /**
         * The number of ads currently visible within the viewport.
         * @return the protocol field value
         */
        public long viewportAdCount() {
            return ((Number) require("viewportAdCount")).longValue();
        }
        /**
         * The time-weighted average of the viewport ad count, measured across the duration of the page.
         * @return the protocol field value
         */
        public double averageViewportAdCount() {
            return ((Number) require("averageViewportAdCount")).doubleValue();
        }
        /**
         * The total ad CPU usage, in milliseconds.
         * @return the protocol field value
         */
        public double totalAdCpuTime() {
            return ((Number) require("totalAdCpuTime")).doubleValue();
        }
        /**
         * The total ad network bytes.
         * @return the protocol field value
         */
        public double totalAdNetworkBytes() {
            return ((Number) require("totalAdNetworkBytes")).doubleValue();
        }
        /**
         * The viewport ad density by area, represented as a percentage (an integer between 0 and 100).
         * @param viewportAdDensityByArea field value
         * @return this model
         */
        public AdMetrics viewportAdDensityByArea(long viewportAdDensityByArea) {
            set("viewportAdDensityByArea", viewportAdDensityByArea);
            return this;
        }
        /**
         * The time-weighted average of the viewport ad density by area, measured across the duration of the page.
         * @param averageViewportAdDensityByArea field value
         * @return this model
         */
        public AdMetrics averageViewportAdDensityByArea(double averageViewportAdDensityByArea) {
            set("averageViewportAdDensityByArea", averageViewportAdDensityByArea);
            return this;
        }
        /**
         * The number of ads currently visible within the viewport.
         * @param viewportAdCount field value
         * @return this model
         */
        public AdMetrics viewportAdCount(long viewportAdCount) {
            set("viewportAdCount", viewportAdCount);
            return this;
        }
        /**
         * The time-weighted average of the viewport ad count, measured across the duration of the page.
         * @param averageViewportAdCount field value
         * @return this model
         */
        public AdMetrics averageViewportAdCount(double averageViewportAdCount) {
            set("averageViewportAdCount", averageViewportAdCount);
            return this;
        }
        /**
         * The total ad CPU usage, in milliseconds.
         * @param totalAdCpuTime field value
         * @return this model
         */
        public AdMetrics totalAdCpuTime(double totalAdCpuTime) {
            set("totalAdCpuTime", totalAdCpuTime);
            return this;
        }
        /**
         * The total ad network bytes.
         * @param totalAdNetworkBytes field value
         * @return this model
         */
        public AdMetrics totalAdNetworkBytes(double totalAdNetworkBytes) {
            set("totalAdNetworkBytes", totalAdNetworkBytes);
            return this;
        }
    }
    public static final class Client {
        private final CdpClient client;
        public Client(CdpClient client) { this.client = client; }
        /**
         * Retrieves ad metrics for the current page.
         * @return a stage completing with the command result
         */
        public CompletionStage<Ads.AdMetrics> getAdMetrics() {
            return client.call("Ads.getAdMetrics", null, result_ -> java.util.Objects.requireNonNull(Ads.AdMetrics.fromMap(java.util.Objects.requireNonNull(CdpObject.objectMap(java.util.Objects.requireNonNull(result_.get("metrics")))))));
        }
    }
}
