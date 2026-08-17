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
 * Chrome DevTools Protocol Memory domain.
 * <p><b>Experimental:</b> this part of CDP may change without notice.
 * @see <a href="https://chromium.googlesource.com/chromium/src/+/refs/tags/150.0.7871.213/third_party/blink/public/devtools_protocol/domains/Memory.pdl">Pinned protocol source</a>
 */
@SuppressWarnings({"EscapedEntity", "JavaLangClash", "MissingSummary", "UnusedMethod"})
public final class Memory {
    private Memory() {}
    @Nullable private static Long numberAsLong(@Nullable Object value) { return value == null ? null : ((Number) value).longValue(); }
    @Nullable private static Double numberAsDouble(@Nullable Object value) { return value == null ? null : ((Number) value).doubleValue(); }
    /**
     * Memory pressure level.
     */
    public static final class PressureLevel {
        private PressureLevel() {}
        public static final String MODERATE = "moderate";
        public static final String CRITICAL = "critical";
    }
    /**
     * Heap profile sample.
     */
    public static final class SamplingProfileNode extends CdpObject {
        private SamplingProfileNode(Map<String, Object> values) { super(values); }
        @Nullable public static SamplingProfileNode fromMap(@Nullable Map<String, Object> values) {
            return values == null ? null : new SamplingProfileNode(values);
        }
        public static Builder builder() { return new Builder(); }
        /**
         * Size of the sampled allocation.
         * @return the protocol field value
         */
        @Nullable public Double size() {
            return numberAsDouble(value("size"));
        }
        /**
         * Total bytes attributed to this sample.
         * @return the protocol field value
         */
        @Nullable public Double total() {
            return numberAsDouble(value("total"));
        }
        /**
         * Execution stack at the point of allocation.
         * @return the protocol field value
         */
        @Nullable public java.util.List<String> stack() {
            return list(value("stack"), element0 -> (String) element0);
        }
        public static final class Builder {
            private final Map<String, Object> values = new LinkedHashMap<>();
            /**
             * Size of the sampled allocation.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder size(@Nullable Double value) {
                if (value == null) values.remove("size");
                else values.put("size", jsonValue(value));
                return this;
            }
            /**
             * Total bytes attributed to this sample.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder total(@Nullable Double value) {
                if (value == null) values.remove("total");
                else values.put("total", jsonValue(value));
                return this;
            }
            /**
             * Execution stack at the point of allocation.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder stack(@Nullable java.util.List<String> value) {
                if (value == null) values.remove("stack");
                else values.put("stack", jsonValue(value));
                return this;
            }
            public SamplingProfileNode build() {
                if (!values.containsKey("size")) throw new IllegalStateException("Missing required CDP field: size");
                if (!values.containsKey("total")) throw new IllegalStateException("Missing required CDP field: total");
                if (!values.containsKey("stack")) throw new IllegalStateException("Missing required CDP field: stack");
                return new SamplingProfileNode(values);
            }
        }
    }
    /**
     * Array of heap profile samples.
     */
    public static final class SamplingProfile extends CdpObject {
        private SamplingProfile(Map<String, Object> values) { super(values); }
        @Nullable public static SamplingProfile fromMap(@Nullable Map<String, Object> values) {
            return values == null ? null : new SamplingProfile(values);
        }
        public static Builder builder() { return new Builder(); }
        /**
         * Returns the samples field.
         * @return the protocol field value
         */
        @Nullable public java.util.List<Memory.SamplingProfileNode> samples() {
            return list(value("samples"), element0 -> Memory.SamplingProfileNode.fromMap(objectMap(element0)));
        }
        /**
         * Returns the modules field.
         * @return the protocol field value
         */
        @Nullable public java.util.List<Memory.Module> modules() {
            return list(value("modules"), element0 -> Memory.Module.fromMap(objectMap(element0)));
        }
        public static final class Builder {
            private final Map<String, Object> values = new LinkedHashMap<>();
            /**
             * Sets the samples field.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder samples(@Nullable java.util.List<Memory.SamplingProfileNode> value) {
                if (value == null) values.remove("samples");
                else values.put("samples", jsonValue(value));
                return this;
            }
            /**
             * Sets the modules field.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder modules(@Nullable java.util.List<Memory.Module> value) {
                if (value == null) values.remove("modules");
                else values.put("modules", jsonValue(value));
                return this;
            }
            public SamplingProfile build() {
                if (!values.containsKey("samples")) throw new IllegalStateException("Missing required CDP field: samples");
                if (!values.containsKey("modules")) throw new IllegalStateException("Missing required CDP field: modules");
                return new SamplingProfile(values);
            }
        }
    }
    /**
     * Executable module information
     */
    public static final class Module extends CdpObject {
        private Module(Map<String, Object> values) { super(values); }
        @Nullable public static Module fromMap(@Nullable Map<String, Object> values) {
            return values == null ? null : new Module(values);
        }
        public static Builder builder() { return new Builder(); }
        /**
         * Name of the module.
         * @return the protocol field value
         */
        @Nullable public String name() {
            return (String) value("name");
        }
        /**
         * UUID of the module.
         * @return the protocol field value
         */
        @Nullable public String uuid() {
            return (String) value("uuid");
        }
        /**
         * Base address where the module is loaded into memory. Encoded as a decimal or hexadecimal (0x prefixed) string.
         * @return the protocol field value
         */
        @Nullable public String baseAddress() {
            return (String) value("baseAddress");
        }
        /**
         * Size of the module in bytes.
         * @return the protocol field value
         */
        @Nullable public Double size() {
            return numberAsDouble(value("size"));
        }
        public static final class Builder {
            private final Map<String, Object> values = new LinkedHashMap<>();
            /**
             * Name of the module.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder name(@Nullable String value) {
                if (value == null) values.remove("name");
                else values.put("name", jsonValue(value));
                return this;
            }
            /**
             * UUID of the module.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder uuid(@Nullable String value) {
                if (value == null) values.remove("uuid");
                else values.put("uuid", jsonValue(value));
                return this;
            }
            /**
             * Base address where the module is loaded into memory. Encoded as a decimal or hexadecimal (0x prefixed) string.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder baseAddress(@Nullable String value) {
                if (value == null) values.remove("baseAddress");
                else values.put("baseAddress", jsonValue(value));
                return this;
            }
            /**
             * Size of the module in bytes.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder size(@Nullable Double value) {
                if (value == null) values.remove("size");
                else values.put("size", jsonValue(value));
                return this;
            }
            public Module build() {
                if (!values.containsKey("name")) throw new IllegalStateException("Missing required CDP field: name");
                if (!values.containsKey("uuid")) throw new IllegalStateException("Missing required CDP field: uuid");
                if (!values.containsKey("baseAddress")) throw new IllegalStateException("Missing required CDP field: baseAddress");
                if (!values.containsKey("size")) throw new IllegalStateException("Missing required CDP field: size");
                return new Module(values);
            }
        }
    }
    /**
     * DOM object counter data.
     */
    public static final class DOMCounter extends CdpObject {
        private DOMCounter(Map<String, Object> values) { super(values); }
        @Nullable public static DOMCounter fromMap(@Nullable Map<String, Object> values) {
            return values == null ? null : new DOMCounter(values);
        }
        public static Builder builder() { return new Builder(); }
        /**
         * Object name. Note: object names should be presumed volatile and clients should not expect the returned names to be consistent across runs.
         * @return the protocol field value
         */
        @Nullable public String name() {
            return (String) value("name");
        }
        /**
         * Object count.
         * @return the protocol field value
         */
        @Nullable public Long count() {
            return numberAsLong(value("count"));
        }
        public static final class Builder {
            private final Map<String, Object> values = new LinkedHashMap<>();
            /**
             * Object name. Note: object names should be presumed volatile and clients should not expect the returned names to be consistent across runs.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder name(@Nullable String value) {
                if (value == null) values.remove("name");
                else values.put("name", jsonValue(value));
                return this;
            }
            /**
             * Object count.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder count(@Nullable Long value) {
                if (value == null) values.remove("count");
                else values.put("count", jsonValue(value));
                return this;
            }
            public DOMCounter build() {
                if (!values.containsKey("name")) throw new IllegalStateException("Missing required CDP field: name");
                if (!values.containsKey("count")) throw new IllegalStateException("Missing required CDP field: count");
                return new DOMCounter(values);
            }
        }
    }
    /**
     * Retruns current DOM object counters.
     */
    public static final class GetDOMCountersParams extends CdpObject {
        private GetDOMCountersParams(Map<String, Object> values) { super(values); }
        @Nullable public static GetDOMCountersParams fromMap(@Nullable Map<String, Object> values) {
            return values == null ? null : new GetDOMCountersParams(values);
        }
        public static Builder builder() { return new Builder(); }
        public static final class Builder {
            private final Map<String, Object> values = new LinkedHashMap<>();
            public GetDOMCountersParams build() {
                return new GetDOMCountersParams(values);
            }
        }
    }
    /**
     * Retruns current DOM object counters.
     */
    public static final class GetDOMCountersResult extends CdpObject {
        private GetDOMCountersResult(Map<String, Object> values) { super(values); }
        @Nullable public static GetDOMCountersResult fromMap(@Nullable Map<String, Object> values) {
            return values == null ? null : new GetDOMCountersResult(values);
        }
        public static Builder builder() { return new Builder(); }
        /**
         * Returns the documents field.
         * @return the protocol field value
         */
        @Nullable public Long documents() {
            return numberAsLong(value("documents"));
        }
        /**
         * Returns the nodes field.
         * @return the protocol field value
         */
        @Nullable public Long nodes() {
            return numberAsLong(value("nodes"));
        }
        /**
         * Returns the jsEventListeners field.
         * @return the protocol field value
         */
        @Nullable public Long jsEventListeners() {
            return numberAsLong(value("jsEventListeners"));
        }
        public static final class Builder {
            private final Map<String, Object> values = new LinkedHashMap<>();
            /**
             * Sets the documents field.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder documents(@Nullable Long value) {
                if (value == null) values.remove("documents");
                else values.put("documents", jsonValue(value));
                return this;
            }
            /**
             * Sets the nodes field.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder nodes(@Nullable Long value) {
                if (value == null) values.remove("nodes");
                else values.put("nodes", jsonValue(value));
                return this;
            }
            /**
             * Sets the jsEventListeners field.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder jsEventListeners(@Nullable Long value) {
                if (value == null) values.remove("jsEventListeners");
                else values.put("jsEventListeners", jsonValue(value));
                return this;
            }
            public GetDOMCountersResult build() {
                if (!values.containsKey("documents")) throw new IllegalStateException("Missing required CDP field: documents");
                if (!values.containsKey("nodes")) throw new IllegalStateException("Missing required CDP field: nodes");
                if (!values.containsKey("jsEventListeners")) throw new IllegalStateException("Missing required CDP field: jsEventListeners");
                return new GetDOMCountersResult(values);
            }
        }
    }
    /**
     * Retruns DOM object counters after preparing renderer for leak detection.
     */
    public static final class GetDOMCountersForLeakDetectionParams extends CdpObject {
        private GetDOMCountersForLeakDetectionParams(Map<String, Object> values) { super(values); }
        @Nullable public static GetDOMCountersForLeakDetectionParams fromMap(@Nullable Map<String, Object> values) {
            return values == null ? null : new GetDOMCountersForLeakDetectionParams(values);
        }
        public static Builder builder() { return new Builder(); }
        public static final class Builder {
            private final Map<String, Object> values = new LinkedHashMap<>();
            public GetDOMCountersForLeakDetectionParams build() {
                return new GetDOMCountersForLeakDetectionParams(values);
            }
        }
    }
    /**
     * Retruns DOM object counters after preparing renderer for leak detection.
     */
    public static final class GetDOMCountersForLeakDetectionResult extends CdpObject {
        private GetDOMCountersForLeakDetectionResult(Map<String, Object> values) { super(values); }
        @Nullable public static GetDOMCountersForLeakDetectionResult fromMap(@Nullable Map<String, Object> values) {
            return values == null ? null : new GetDOMCountersForLeakDetectionResult(values);
        }
        public static Builder builder() { return new Builder(); }
        /**
         * DOM object counters.
         * @return the protocol field value
         */
        @Nullable public java.util.List<Memory.DOMCounter> counters() {
            return list(value("counters"), element0 -> Memory.DOMCounter.fromMap(objectMap(element0)));
        }
        public static final class Builder {
            private final Map<String, Object> values = new LinkedHashMap<>();
            /**
             * DOM object counters.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder counters(@Nullable java.util.List<Memory.DOMCounter> value) {
                if (value == null) values.remove("counters");
                else values.put("counters", jsonValue(value));
                return this;
            }
            public GetDOMCountersForLeakDetectionResult build() {
                if (!values.containsKey("counters")) throw new IllegalStateException("Missing required CDP field: counters");
                return new GetDOMCountersForLeakDetectionResult(values);
            }
        }
    }
    /**
     * Prepares for leak detection by terminating workers, stopping spellcheckers, dropping non-essential internal caches, running garbage collections, etc.
     */
    public static final class PrepareForLeakDetectionParams extends CdpObject {
        private PrepareForLeakDetectionParams(Map<String, Object> values) { super(values); }
        @Nullable public static PrepareForLeakDetectionParams fromMap(@Nullable Map<String, Object> values) {
            return values == null ? null : new PrepareForLeakDetectionParams(values);
        }
        public static Builder builder() { return new Builder(); }
        public static final class Builder {
            private final Map<String, Object> values = new LinkedHashMap<>();
            public PrepareForLeakDetectionParams build() {
                return new PrepareForLeakDetectionParams(values);
            }
        }
    }
    /**
     * Prepares for leak detection by terminating workers, stopping spellcheckers, dropping non-essential internal caches, running garbage collections, etc.
     */
    public static final class PrepareForLeakDetectionResult extends CdpObject {
        private PrepareForLeakDetectionResult(Map<String, Object> values) { super(values); }
        @Nullable public static PrepareForLeakDetectionResult fromMap(@Nullable Map<String, Object> values) {
            return values == null ? null : new PrepareForLeakDetectionResult(values);
        }
        public static Builder builder() { return new Builder(); }
        public static final class Builder {
            private final Map<String, Object> values = new LinkedHashMap<>();
            public PrepareForLeakDetectionResult build() {
                return new PrepareForLeakDetectionResult(values);
            }
        }
    }
    /**
     * Simulate OomIntervention by purging V8 memory.
     */
    public static final class ForciblyPurgeJavaScriptMemoryParams extends CdpObject {
        private ForciblyPurgeJavaScriptMemoryParams(Map<String, Object> values) { super(values); }
        @Nullable public static ForciblyPurgeJavaScriptMemoryParams fromMap(@Nullable Map<String, Object> values) {
            return values == null ? null : new ForciblyPurgeJavaScriptMemoryParams(values);
        }
        public static Builder builder() { return new Builder(); }
        public static final class Builder {
            private final Map<String, Object> values = new LinkedHashMap<>();
            public ForciblyPurgeJavaScriptMemoryParams build() {
                return new ForciblyPurgeJavaScriptMemoryParams(values);
            }
        }
    }
    /**
     * Simulate OomIntervention by purging V8 memory.
     */
    public static final class ForciblyPurgeJavaScriptMemoryResult extends CdpObject {
        private ForciblyPurgeJavaScriptMemoryResult(Map<String, Object> values) { super(values); }
        @Nullable public static ForciblyPurgeJavaScriptMemoryResult fromMap(@Nullable Map<String, Object> values) {
            return values == null ? null : new ForciblyPurgeJavaScriptMemoryResult(values);
        }
        public static Builder builder() { return new Builder(); }
        public static final class Builder {
            private final Map<String, Object> values = new LinkedHashMap<>();
            public ForciblyPurgeJavaScriptMemoryResult build() {
                return new ForciblyPurgeJavaScriptMemoryResult(values);
            }
        }
    }
    /**
     * Enable/disable suppressing memory pressure notifications in all processes.
     */
    public static final class SetPressureNotificationsSuppressedParams extends CdpObject {
        private SetPressureNotificationsSuppressedParams(Map<String, Object> values) { super(values); }
        @Nullable public static SetPressureNotificationsSuppressedParams fromMap(@Nullable Map<String, Object> values) {
            return values == null ? null : new SetPressureNotificationsSuppressedParams(values);
        }
        public static Builder builder() { return new Builder(); }
        /**
         * If true, memory pressure notifications will be suppressed.
         * @return the protocol field value
         */
        @Nullable public Boolean suppressed() {
            return (Boolean) value("suppressed");
        }
        public static final class Builder {
            private final Map<String, Object> values = new LinkedHashMap<>();
            /**
             * If true, memory pressure notifications will be suppressed.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder suppressed(@Nullable Boolean value) {
                if (value == null) values.remove("suppressed");
                else values.put("suppressed", jsonValue(value));
                return this;
            }
            public SetPressureNotificationsSuppressedParams build() {
                if (!values.containsKey("suppressed")) throw new IllegalStateException("Missing required CDP field: suppressed");
                return new SetPressureNotificationsSuppressedParams(values);
            }
        }
    }
    /**
     * Enable/disable suppressing memory pressure notifications in all processes.
     */
    public static final class SetPressureNotificationsSuppressedResult extends CdpObject {
        private SetPressureNotificationsSuppressedResult(Map<String, Object> values) { super(values); }
        @Nullable public static SetPressureNotificationsSuppressedResult fromMap(@Nullable Map<String, Object> values) {
            return values == null ? null : new SetPressureNotificationsSuppressedResult(values);
        }
        public static Builder builder() { return new Builder(); }
        public static final class Builder {
            private final Map<String, Object> values = new LinkedHashMap<>();
            public SetPressureNotificationsSuppressedResult build() {
                return new SetPressureNotificationsSuppressedResult(values);
            }
        }
    }
    /**
     * Simulate a memory pressure notification in all processes.
     */
    public static final class SimulatePressureNotificationParams extends CdpObject {
        private SimulatePressureNotificationParams(Map<String, Object> values) { super(values); }
        @Nullable public static SimulatePressureNotificationParams fromMap(@Nullable Map<String, Object> values) {
            return values == null ? null : new SimulatePressureNotificationParams(values);
        }
        public static Builder builder() { return new Builder(); }
        /**
         * Memory pressure level of the notification.
         * @return the protocol field value
         */
        @Nullable public String level() {
            return (String) value("level");
        }
        public static final class Builder {
            private final Map<String, Object> values = new LinkedHashMap<>();
            /**
             * Memory pressure level of the notification.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder level(@Nullable String value) {
                if (value == null) values.remove("level");
                else values.put("level", jsonValue(value));
                return this;
            }
            public SimulatePressureNotificationParams build() {
                if (!values.containsKey("level")) throw new IllegalStateException("Missing required CDP field: level");
                return new SimulatePressureNotificationParams(values);
            }
        }
    }
    /**
     * Simulate a memory pressure notification in all processes.
     */
    public static final class SimulatePressureNotificationResult extends CdpObject {
        private SimulatePressureNotificationResult(Map<String, Object> values) { super(values); }
        @Nullable public static SimulatePressureNotificationResult fromMap(@Nullable Map<String, Object> values) {
            return values == null ? null : new SimulatePressureNotificationResult(values);
        }
        public static Builder builder() { return new Builder(); }
        public static final class Builder {
            private final Map<String, Object> values = new LinkedHashMap<>();
            public SimulatePressureNotificationResult build() {
                return new SimulatePressureNotificationResult(values);
            }
        }
    }
    /**
     * Start collecting native memory profile.
     */
    public static final class StartSamplingParams extends CdpObject {
        private StartSamplingParams(Map<String, Object> values) { super(values); }
        @Nullable public static StartSamplingParams fromMap(@Nullable Map<String, Object> values) {
            return values == null ? null : new StartSamplingParams(values);
        }
        public static Builder builder() { return new Builder(); }
        /**
         * Average number of bytes between samples.
         * @return the protocol field value
         */
        @Nullable public Long samplingInterval() {
            return numberAsLong(value("samplingInterval"));
        }
        /**
         * Do not randomize intervals between samples.
         * @return the protocol field value
         */
        @Nullable public Boolean suppressRandomness() {
            return (Boolean) value("suppressRandomness");
        }
        public static final class Builder {
            private final Map<String, Object> values = new LinkedHashMap<>();
            /**
             * Average number of bytes between samples.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder samplingInterval(@Nullable Long value) {
                if (value == null) values.remove("samplingInterval");
                else values.put("samplingInterval", jsonValue(value));
                return this;
            }
            /**
             * Do not randomize intervals between samples.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder suppressRandomness(@Nullable Boolean value) {
                if (value == null) values.remove("suppressRandomness");
                else values.put("suppressRandomness", jsonValue(value));
                return this;
            }
            public StartSamplingParams build() {
                return new StartSamplingParams(values);
            }
        }
    }
    /**
     * Start collecting native memory profile.
     */
    public static final class StartSamplingResult extends CdpObject {
        private StartSamplingResult(Map<String, Object> values) { super(values); }
        @Nullable public static StartSamplingResult fromMap(@Nullable Map<String, Object> values) {
            return values == null ? null : new StartSamplingResult(values);
        }
        public static Builder builder() { return new Builder(); }
        public static final class Builder {
            private final Map<String, Object> values = new LinkedHashMap<>();
            public StartSamplingResult build() {
                return new StartSamplingResult(values);
            }
        }
    }
    /**
     * Stop collecting native memory profile.
     */
    public static final class StopSamplingParams extends CdpObject {
        private StopSamplingParams(Map<String, Object> values) { super(values); }
        @Nullable public static StopSamplingParams fromMap(@Nullable Map<String, Object> values) {
            return values == null ? null : new StopSamplingParams(values);
        }
        public static Builder builder() { return new Builder(); }
        public static final class Builder {
            private final Map<String, Object> values = new LinkedHashMap<>();
            public StopSamplingParams build() {
                return new StopSamplingParams(values);
            }
        }
    }
    /**
     * Stop collecting native memory profile.
     */
    public static final class StopSamplingResult extends CdpObject {
        private StopSamplingResult(Map<String, Object> values) { super(values); }
        @Nullable public static StopSamplingResult fromMap(@Nullable Map<String, Object> values) {
            return values == null ? null : new StopSamplingResult(values);
        }
        public static Builder builder() { return new Builder(); }
        public static final class Builder {
            private final Map<String, Object> values = new LinkedHashMap<>();
            public StopSamplingResult build() {
                return new StopSamplingResult(values);
            }
        }
    }
    /**
     * Retrieve native memory allocations profile collected since renderer process startup.
     */
    public static final class GetAllTimeSamplingProfileParams extends CdpObject {
        private GetAllTimeSamplingProfileParams(Map<String, Object> values) { super(values); }
        @Nullable public static GetAllTimeSamplingProfileParams fromMap(@Nullable Map<String, Object> values) {
            return values == null ? null : new GetAllTimeSamplingProfileParams(values);
        }
        public static Builder builder() { return new Builder(); }
        public static final class Builder {
            private final Map<String, Object> values = new LinkedHashMap<>();
            public GetAllTimeSamplingProfileParams build() {
                return new GetAllTimeSamplingProfileParams(values);
            }
        }
    }
    /**
     * Retrieve native memory allocations profile collected since renderer process startup.
     */
    public static final class GetAllTimeSamplingProfileResult extends CdpObject {
        private GetAllTimeSamplingProfileResult(Map<String, Object> values) { super(values); }
        @Nullable public static GetAllTimeSamplingProfileResult fromMap(@Nullable Map<String, Object> values) {
            return values == null ? null : new GetAllTimeSamplingProfileResult(values);
        }
        public static Builder builder() { return new Builder(); }
        /**
         * Returns the profile field.
         * @return the protocol field value
         */
        @Nullable public Memory.SamplingProfile profile() {
            return Memory.SamplingProfile.fromMap(objectMap(value("profile")));
        }
        public static final class Builder {
            private final Map<String, Object> values = new LinkedHashMap<>();
            /**
             * Sets the profile field.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder profile(@Nullable Memory.SamplingProfile value) {
                if (value == null) values.remove("profile");
                else values.put("profile", jsonValue(value));
                return this;
            }
            public GetAllTimeSamplingProfileResult build() {
                if (!values.containsKey("profile")) throw new IllegalStateException("Missing required CDP field: profile");
                return new GetAllTimeSamplingProfileResult(values);
            }
        }
    }
    /**
     * Retrieve native memory allocations profile collected since browser process startup.
     */
    public static final class GetBrowserSamplingProfileParams extends CdpObject {
        private GetBrowserSamplingProfileParams(Map<String, Object> values) { super(values); }
        @Nullable public static GetBrowserSamplingProfileParams fromMap(@Nullable Map<String, Object> values) {
            return values == null ? null : new GetBrowserSamplingProfileParams(values);
        }
        public static Builder builder() { return new Builder(); }
        public static final class Builder {
            private final Map<String, Object> values = new LinkedHashMap<>();
            public GetBrowserSamplingProfileParams build() {
                return new GetBrowserSamplingProfileParams(values);
            }
        }
    }
    /**
     * Retrieve native memory allocations profile collected since browser process startup.
     */
    public static final class GetBrowserSamplingProfileResult extends CdpObject {
        private GetBrowserSamplingProfileResult(Map<String, Object> values) { super(values); }
        @Nullable public static GetBrowserSamplingProfileResult fromMap(@Nullable Map<String, Object> values) {
            return values == null ? null : new GetBrowserSamplingProfileResult(values);
        }
        public static Builder builder() { return new Builder(); }
        /**
         * Returns the profile field.
         * @return the protocol field value
         */
        @Nullable public Memory.SamplingProfile profile() {
            return Memory.SamplingProfile.fromMap(objectMap(value("profile")));
        }
        public static final class Builder {
            private final Map<String, Object> values = new LinkedHashMap<>();
            /**
             * Sets the profile field.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder profile(@Nullable Memory.SamplingProfile value) {
                if (value == null) values.remove("profile");
                else values.put("profile", jsonValue(value));
                return this;
            }
            public GetBrowserSamplingProfileResult build() {
                if (!values.containsKey("profile")) throw new IllegalStateException("Missing required CDP field: profile");
                return new GetBrowserSamplingProfileResult(values);
            }
        }
    }
    /**
     * Retrieve native memory allocations profile collected since last {@code startSampling} call.
     */
    public static final class GetSamplingProfileParams extends CdpObject {
        private GetSamplingProfileParams(Map<String, Object> values) { super(values); }
        @Nullable public static GetSamplingProfileParams fromMap(@Nullable Map<String, Object> values) {
            return values == null ? null : new GetSamplingProfileParams(values);
        }
        public static Builder builder() { return new Builder(); }
        public static final class Builder {
            private final Map<String, Object> values = new LinkedHashMap<>();
            public GetSamplingProfileParams build() {
                return new GetSamplingProfileParams(values);
            }
        }
    }
    /**
     * Retrieve native memory allocations profile collected since last {@code startSampling} call.
     */
    public static final class GetSamplingProfileResult extends CdpObject {
        private GetSamplingProfileResult(Map<String, Object> values) { super(values); }
        @Nullable public static GetSamplingProfileResult fromMap(@Nullable Map<String, Object> values) {
            return values == null ? null : new GetSamplingProfileResult(values);
        }
        public static Builder builder() { return new Builder(); }
        /**
         * Returns the profile field.
         * @return the protocol field value
         */
        @Nullable public Memory.SamplingProfile profile() {
            return Memory.SamplingProfile.fromMap(objectMap(value("profile")));
        }
        public static final class Builder {
            private final Map<String, Object> values = new LinkedHashMap<>();
            /**
             * Sets the profile field.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder profile(@Nullable Memory.SamplingProfile value) {
                if (value == null) values.remove("profile");
                else values.put("profile", jsonValue(value));
                return this;
            }
            public GetSamplingProfileResult build() {
                if (!values.containsKey("profile")) throw new IllegalStateException("Missing required CDP field: profile");
                return new GetSamplingProfileResult(values);
            }
        }
    }
    public static final class Client {
        private final CdpClient client;
        public Client(CdpClient client) { this.client = client; }
        /**
         * Retruns current DOM object counters.
         * @return a stage completing with the command result
         */
        public CompletionStage<GetDOMCountersResult> getDOMCounters() {
            return client.call("Memory.getDOMCounters", null, GetDOMCountersResult::fromMap);
        }
        /**
         * Retruns DOM object counters after preparing renderer for leak detection.
         * @return a stage completing with the command result
         */
        public CompletionStage<GetDOMCountersForLeakDetectionResult> getDOMCountersForLeakDetection() {
            return client.call("Memory.getDOMCountersForLeakDetection", null, GetDOMCountersForLeakDetectionResult::fromMap);
        }
        /**
         * Prepares for leak detection by terminating workers, stopping spellcheckers, dropping non-essential internal caches, running garbage collections, etc.
         * @return a stage completing with the command result
         */
        public CompletionStage<PrepareForLeakDetectionResult> prepareForLeakDetection() {
            return client.call("Memory.prepareForLeakDetection", null, PrepareForLeakDetectionResult::fromMap);
        }
        /**
         * Simulate OomIntervention by purging V8 memory.
         * @return a stage completing with the command result
         */
        public CompletionStage<ForciblyPurgeJavaScriptMemoryResult> forciblyPurgeJavaScriptMemory() {
            return client.call("Memory.forciblyPurgeJavaScriptMemory", null, ForciblyPurgeJavaScriptMemoryResult::fromMap);
        }
        /**
         * Enable/disable suppressing memory pressure notifications in all processes.
         * @param params command parameters
         * @return a stage completing with the command result
         */
        public CompletionStage<SetPressureNotificationsSuppressedResult> setPressureNotificationsSuppressed(SetPressureNotificationsSuppressedParams params) {
            return client.call("Memory.setPressureNotificationsSuppressed", params, SetPressureNotificationsSuppressedResult::fromMap);
        }
        /**
         * Simulate a memory pressure notification in all processes.
         * @param params command parameters
         * @return a stage completing with the command result
         */
        public CompletionStage<SimulatePressureNotificationResult> simulatePressureNotification(SimulatePressureNotificationParams params) {
            return client.call("Memory.simulatePressureNotification", params, SimulatePressureNotificationResult::fromMap);
        }
        /**
         * Start collecting native memory profile.
         * @param params command parameters
         * @return a stage completing with the command result
         */
        public CompletionStage<StartSamplingResult> startSampling(StartSamplingParams params) {
            return client.call("Memory.startSampling", params, StartSamplingResult::fromMap);
        }
        /**
         * Stop collecting native memory profile.
         * @return a stage completing with the command result
         */
        public CompletionStage<StopSamplingResult> stopSampling() {
            return client.call("Memory.stopSampling", null, StopSamplingResult::fromMap);
        }
        /**
         * Retrieve native memory allocations profile collected since renderer process startup.
         * @return a stage completing with the command result
         */
        public CompletionStage<GetAllTimeSamplingProfileResult> getAllTimeSamplingProfile() {
            return client.call("Memory.getAllTimeSamplingProfile", null, GetAllTimeSamplingProfileResult::fromMap);
        }
        /**
         * Retrieve native memory allocations profile collected since browser process startup.
         * @return a stage completing with the command result
         */
        public CompletionStage<GetBrowserSamplingProfileResult> getBrowserSamplingProfile() {
            return client.call("Memory.getBrowserSamplingProfile", null, GetBrowserSamplingProfileResult::fromMap);
        }
        /**
         * Retrieve native memory allocations profile collected since last {@code startSampling} call.
         * @return a stage completing with the command result
         */
        public CompletionStage<GetSamplingProfileResult> getSamplingProfile() {
            return client.call("Memory.getSamplingProfile", null, GetSamplingProfileResult::fromMap);
        }
    }
}
