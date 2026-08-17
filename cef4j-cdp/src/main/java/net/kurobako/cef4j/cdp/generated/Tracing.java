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
 * Chrome DevTools Protocol Tracing domain.
 * @see <a href="https://chromium.googlesource.com/chromium/src/+/refs/tags/150.0.7871.213/third_party/blink/public/devtools_protocol/domains/Tracing.pdl">Pinned protocol source</a>
 */
@SuppressWarnings({"EscapedEntity", "JavaLangClash", "MissingSummary", "UnusedMethod"})
public final class Tracing {
    private Tracing() {}
    @Nullable private static Long numberAsLong(@Nullable Object value) { return value == null ? null : ((Number) value).longValue(); }
    @Nullable private static Double numberAsDouble(@Nullable Object value) { return value == null ? null : ((Number) value).doubleValue(); }
    /**
     */
    public static final class TraceConfig extends CdpObject {
        private TraceConfig(Map<String, Object> values) { super(values); }
        @Nullable public static TraceConfig fromMap(@Nullable Map<String, Object> values) {
            return values == null ? null : new TraceConfig(values);
        }
        public static Builder builder() { return new Builder(); }
        /**
         * Controls how the trace buffer stores data. The default is {@code recordUntilFull}.
         * <p><b>Experimental:</b> this part of CDP may change without notice.
         * @return the protocol field value
         */
        @Nullable public String recordMode() {
            return (String) value("recordMode");
        }
        /**
         * Controls how the trace buffer stores data. The default is {@code recordUntilFull}.
         * <p><b>Experimental:</b> this part of CDP may change without notice.
         */
        public static final class RecordModeValues {
            private RecordModeValues() {}
            public static final String RECORDUNTILFULL = "recordUntilFull";
            public static final String RECORDCONTINUOUSLY = "recordContinuously";
            public static final String RECORDASMUCHASPOSSIBLE = "recordAsMuchAsPossible";
            public static final String ECHOTOCONSOLE = "echoToConsole";
        }
        /**
         * Size of the trace buffer in kilobytes. If not specified or zero is passed, a default value of 200 MB would be used.
         * <p><b>Experimental:</b> this part of CDP may change without notice.
         * @return the protocol field value
         */
        @Nullable public Double traceBufferSizeInKb() {
            return numberAsDouble(value("traceBufferSizeInKb"));
        }
        /**
         * Turns on JavaScript stack sampling.
         * <p><b>Experimental:</b> this part of CDP may change without notice.
         * @return the protocol field value
         */
        @Nullable public Boolean enableSampling() {
            return (Boolean) value("enableSampling");
        }
        /**
         * Turns on system tracing.
         * <p><b>Experimental:</b> this part of CDP may change without notice.
         * @return the protocol field value
         */
        @Nullable public Boolean enableSystrace() {
            return (Boolean) value("enableSystrace");
        }
        /**
         * Turns on argument filter.
         * <p><b>Experimental:</b> this part of CDP may change without notice.
         * @return the protocol field value
         */
        @Nullable public Boolean enableArgumentFilter() {
            return (Boolean) value("enableArgumentFilter");
        }
        /**
         * Included category filters.
         * @return the protocol field value
         */
        @Nullable public java.util.List<String> includedCategories() {
            return list(value("includedCategories"), element0 -> (String) element0);
        }
        /**
         * Excluded category filters.
         * @return the protocol field value
         */
        @Nullable public java.util.List<String> excludedCategories() {
            return list(value("excludedCategories"), element0 -> (String) element0);
        }
        /**
         * Configuration to synthesize the delays in tracing.
         * <p><b>Experimental:</b> this part of CDP may change without notice.
         * @return the protocol field value
         */
        @Nullable public java.util.List<String> syntheticDelays() {
            return list(value("syntheticDelays"), element0 -> (String) element0);
        }
        /**
         * Configuration for memory dump triggers. Used only when &quot;memory-infra&quot; category is enabled.
         * <p><b>Experimental:</b> this part of CDP may change without notice.
         * @return the protocol field value
         */
        @Nullable public java.util.Map<String, Object> memoryDumpConfig() {
            return objectMap(value("memoryDumpConfig"));
        }
        public static final class Builder {
            private final Map<String, Object> values = new LinkedHashMap<>();
            /**
             * Controls how the trace buffer stores data. The default is {@code recordUntilFull}.
             * <p><b>Experimental:</b> this part of CDP may change without notice.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder recordMode(@Nullable String value) {
                if (value == null) values.remove("recordMode");
                else values.put("recordMode", jsonValue(value));
                return this;
            }
            /**
             * Size of the trace buffer in kilobytes. If not specified or zero is passed, a default value of 200 MB would be used.
             * <p><b>Experimental:</b> this part of CDP may change without notice.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder traceBufferSizeInKb(@Nullable Double value) {
                if (value == null) values.remove("traceBufferSizeInKb");
                else values.put("traceBufferSizeInKb", jsonValue(value));
                return this;
            }
            /**
             * Turns on JavaScript stack sampling.
             * <p><b>Experimental:</b> this part of CDP may change without notice.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder enableSampling(@Nullable Boolean value) {
                if (value == null) values.remove("enableSampling");
                else values.put("enableSampling", jsonValue(value));
                return this;
            }
            /**
             * Turns on system tracing.
             * <p><b>Experimental:</b> this part of CDP may change without notice.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder enableSystrace(@Nullable Boolean value) {
                if (value == null) values.remove("enableSystrace");
                else values.put("enableSystrace", jsonValue(value));
                return this;
            }
            /**
             * Turns on argument filter.
             * <p><b>Experimental:</b> this part of CDP may change without notice.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder enableArgumentFilter(@Nullable Boolean value) {
                if (value == null) values.remove("enableArgumentFilter");
                else values.put("enableArgumentFilter", jsonValue(value));
                return this;
            }
            /**
             * Included category filters.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder includedCategories(@Nullable java.util.List<String> value) {
                if (value == null) values.remove("includedCategories");
                else values.put("includedCategories", jsonValue(value));
                return this;
            }
            /**
             * Excluded category filters.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder excludedCategories(@Nullable java.util.List<String> value) {
                if (value == null) values.remove("excludedCategories");
                else values.put("excludedCategories", jsonValue(value));
                return this;
            }
            /**
             * Configuration to synthesize the delays in tracing.
             * <p><b>Experimental:</b> this part of CDP may change without notice.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder syntheticDelays(@Nullable java.util.List<String> value) {
                if (value == null) values.remove("syntheticDelays");
                else values.put("syntheticDelays", jsonValue(value));
                return this;
            }
            /**
             * Configuration for memory dump triggers. Used only when &quot;memory-infra&quot; category is enabled.
             * <p><b>Experimental:</b> this part of CDP may change without notice.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder memoryDumpConfig(@Nullable java.util.Map<String, Object> value) {
                if (value == null) values.remove("memoryDumpConfig");
                else values.put("memoryDumpConfig", jsonValue(value));
                return this;
            }
            public TraceConfig build() {
                return new TraceConfig(values);
            }
        }
    }
    /**
     * Data format of a trace. Can be either the legacy JSON format or the protocol buffer format. Note that the JSON format will be deprecated soon.
     * <p><b>Experimental:</b> this part of CDP may change without notice.
     */
    public static final class StreamFormat {
        private StreamFormat() {}
        public static final String JSON = "json";
        public static final String PROTO = "proto";
    }
    /**
     * Compression type to use for traces returned via streams.
     * <p><b>Experimental:</b> this part of CDP may change without notice.
     */
    public static final class StreamCompression {
        private StreamCompression() {}
        public static final String NONE = "none";
        public static final String GZIP = "gzip";
    }
    /**
     * Details exposed when memory request explicitly declared. Keep consistent with memory_dump_request_args.h and memory_instrumentation.mojom
     * <p><b>Experimental:</b> this part of CDP may change without notice.
     */
    public static final class MemoryDumpLevelOfDetail {
        private MemoryDumpLevelOfDetail() {}
        public static final String BACKGROUND = "background";
        public static final String LIGHT = "light";
        public static final String DETAILED = "detailed";
    }
    /**
     * Backend type to use for tracing. {@code chrome} uses the Chrome-integrated tracing service and is supported on all platforms. {@code system} is only supported on Chrome OS and uses the Perfetto system tracing service. {@code auto} chooses {@code system} when the perfettoConfig provided to Tracing.start specifies at least one non-Chrome data source; otherwise uses {@code chrome}.
     * <p><b>Experimental:</b> this part of CDP may change without notice.
     */
    public static final class TracingBackend {
        private TracingBackend() {}
        public static final String AUTO = "auto";
        public static final String CHROME = "chrome";
        public static final String SYSTEM = "system";
    }
    /**
     * Stop trace events collection.
     */
    public static final class EndParams extends CdpObject {
        private EndParams(Map<String, Object> values) { super(values); }
        @Nullable public static EndParams fromMap(@Nullable Map<String, Object> values) {
            return values == null ? null : new EndParams(values);
        }
        public static Builder builder() { return new Builder(); }
        public static final class Builder {
            private final Map<String, Object> values = new LinkedHashMap<>();
            public EndParams build() {
                return new EndParams(values);
            }
        }
    }
    /**
     * Stop trace events collection.
     */
    public static final class EndResult extends CdpObject {
        private EndResult(Map<String, Object> values) { super(values); }
        @Nullable public static EndResult fromMap(@Nullable Map<String, Object> values) {
            return values == null ? null : new EndResult(values);
        }
        public static Builder builder() { return new Builder(); }
        public static final class Builder {
            private final Map<String, Object> values = new LinkedHashMap<>();
            public EndResult build() {
                return new EndResult(values);
            }
        }
    }
    /**
     * Gets supported tracing categories.
     * <p><b>Experimental:</b> this part of CDP may change without notice.
     */
    public static final class GetCategoriesParams extends CdpObject {
        private GetCategoriesParams(Map<String, Object> values) { super(values); }
        @Nullable public static GetCategoriesParams fromMap(@Nullable Map<String, Object> values) {
            return values == null ? null : new GetCategoriesParams(values);
        }
        public static Builder builder() { return new Builder(); }
        public static final class Builder {
            private final Map<String, Object> values = new LinkedHashMap<>();
            public GetCategoriesParams build() {
                return new GetCategoriesParams(values);
            }
        }
    }
    /**
     * Gets supported tracing categories.
     * <p><b>Experimental:</b> this part of CDP may change without notice.
     */
    public static final class GetCategoriesResult extends CdpObject {
        private GetCategoriesResult(Map<String, Object> values) { super(values); }
        @Nullable public static GetCategoriesResult fromMap(@Nullable Map<String, Object> values) {
            return values == null ? null : new GetCategoriesResult(values);
        }
        public static Builder builder() { return new Builder(); }
        /**
         * A list of supported tracing categories.
         * @return the protocol field value
         */
        @Nullable public java.util.List<String> categories() {
            return list(value("categories"), element0 -> (String) element0);
        }
        public static final class Builder {
            private final Map<String, Object> values = new LinkedHashMap<>();
            /**
             * A list of supported tracing categories.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder categories(@Nullable java.util.List<String> value) {
                if (value == null) values.remove("categories");
                else values.put("categories", jsonValue(value));
                return this;
            }
            public GetCategoriesResult build() {
                if (!values.containsKey("categories")) throw new IllegalStateException("Missing required CDP field: categories");
                return new GetCategoriesResult(values);
            }
        }
    }
    /**
     * Return a descriptor for all available tracing categories.
     * <p><b>Experimental:</b> this part of CDP may change without notice.
     */
    public static final class GetTrackEventDescriptorParams extends CdpObject {
        private GetTrackEventDescriptorParams(Map<String, Object> values) { super(values); }
        @Nullable public static GetTrackEventDescriptorParams fromMap(@Nullable Map<String, Object> values) {
            return values == null ? null : new GetTrackEventDescriptorParams(values);
        }
        public static Builder builder() { return new Builder(); }
        public static final class Builder {
            private final Map<String, Object> values = new LinkedHashMap<>();
            public GetTrackEventDescriptorParams build() {
                return new GetTrackEventDescriptorParams(values);
            }
        }
    }
    /**
     * Return a descriptor for all available tracing categories.
     * <p><b>Experimental:</b> this part of CDP may change without notice.
     */
    public static final class GetTrackEventDescriptorResult extends CdpObject {
        private GetTrackEventDescriptorResult(Map<String, Object> values) { super(values); }
        @Nullable public static GetTrackEventDescriptorResult fromMap(@Nullable Map<String, Object> values) {
            return values == null ? null : new GetTrackEventDescriptorResult(values);
        }
        public static Builder builder() { return new Builder(); }
        /**
         * Base64-encoded serialized perfetto.protos.TrackEventDescriptor protobuf message. (Encoded as a base64 string when passed over JSON)
         * @return the protocol field value
         */
        @Nullable public String descriptor() {
            return (String) value("descriptor");
        }
        public static final class Builder {
            private final Map<String, Object> values = new LinkedHashMap<>();
            /**
             * Base64-encoded serialized perfetto.protos.TrackEventDescriptor protobuf message. (Encoded as a base64 string when passed over JSON)
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder descriptor(@Nullable String value) {
                if (value == null) values.remove("descriptor");
                else values.put("descriptor", jsonValue(value));
                return this;
            }
            public GetTrackEventDescriptorResult build() {
                if (!values.containsKey("descriptor")) throw new IllegalStateException("Missing required CDP field: descriptor");
                return new GetTrackEventDescriptorResult(values);
            }
        }
    }
    /**
     * Record a clock sync marker in the trace.
     * <p><b>Experimental:</b> this part of CDP may change without notice.
     */
    public static final class RecordClockSyncMarkerParams extends CdpObject {
        private RecordClockSyncMarkerParams(Map<String, Object> values) { super(values); }
        @Nullable public static RecordClockSyncMarkerParams fromMap(@Nullable Map<String, Object> values) {
            return values == null ? null : new RecordClockSyncMarkerParams(values);
        }
        public static Builder builder() { return new Builder(); }
        /**
         * The ID of this clock sync marker
         * @return the protocol field value
         */
        @Nullable public String syncId() {
            return (String) value("syncId");
        }
        public static final class Builder {
            private final Map<String, Object> values = new LinkedHashMap<>();
            /**
             * The ID of this clock sync marker
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder syncId(@Nullable String value) {
                if (value == null) values.remove("syncId");
                else values.put("syncId", jsonValue(value));
                return this;
            }
            public RecordClockSyncMarkerParams build() {
                if (!values.containsKey("syncId")) throw new IllegalStateException("Missing required CDP field: syncId");
                return new RecordClockSyncMarkerParams(values);
            }
        }
    }
    /**
     * Record a clock sync marker in the trace.
     * <p><b>Experimental:</b> this part of CDP may change without notice.
     */
    public static final class RecordClockSyncMarkerResult extends CdpObject {
        private RecordClockSyncMarkerResult(Map<String, Object> values) { super(values); }
        @Nullable public static RecordClockSyncMarkerResult fromMap(@Nullable Map<String, Object> values) {
            return values == null ? null : new RecordClockSyncMarkerResult(values);
        }
        public static Builder builder() { return new Builder(); }
        public static final class Builder {
            private final Map<String, Object> values = new LinkedHashMap<>();
            public RecordClockSyncMarkerResult build() {
                return new RecordClockSyncMarkerResult(values);
            }
        }
    }
    /**
     * Request a global memory dump.
     * <p><b>Experimental:</b> this part of CDP may change without notice.
     */
    public static final class RequestMemoryDumpParams extends CdpObject {
        private RequestMemoryDumpParams(Map<String, Object> values) { super(values); }
        @Nullable public static RequestMemoryDumpParams fromMap(@Nullable Map<String, Object> values) {
            return values == null ? null : new RequestMemoryDumpParams(values);
        }
        public static Builder builder() { return new Builder(); }
        /**
         * Enables more deterministic results by forcing garbage collection
         * @return the protocol field value
         */
        @Nullable public Boolean deterministic() {
            return (Boolean) value("deterministic");
        }
        /**
         * Specifies level of details in memory dump. Defaults to &quot;detailed&quot;.
         * @return the protocol field value
         */
        @Nullable public String levelOfDetail() {
            return (String) value("levelOfDetail");
        }
        public static final class Builder {
            private final Map<String, Object> values = new LinkedHashMap<>();
            /**
             * Enables more deterministic results by forcing garbage collection
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder deterministic(@Nullable Boolean value) {
                if (value == null) values.remove("deterministic");
                else values.put("deterministic", jsonValue(value));
                return this;
            }
            /**
             * Specifies level of details in memory dump. Defaults to &quot;detailed&quot;.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder levelOfDetail(@Nullable String value) {
                if (value == null) values.remove("levelOfDetail");
                else values.put("levelOfDetail", jsonValue(value));
                return this;
            }
            public RequestMemoryDumpParams build() {
                return new RequestMemoryDumpParams(values);
            }
        }
    }
    /**
     * Request a global memory dump.
     * <p><b>Experimental:</b> this part of CDP may change without notice.
     */
    public static final class RequestMemoryDumpResult extends CdpObject {
        private RequestMemoryDumpResult(Map<String, Object> values) { super(values); }
        @Nullable public static RequestMemoryDumpResult fromMap(@Nullable Map<String, Object> values) {
            return values == null ? null : new RequestMemoryDumpResult(values);
        }
        public static Builder builder() { return new Builder(); }
        /**
         * GUID of the resulting global memory dump.
         * @return the protocol field value
         */
        @Nullable public String dumpGuid() {
            return (String) value("dumpGuid");
        }
        /**
         * True iff the global memory dump succeeded.
         * @return the protocol field value
         */
        @Nullable public Boolean success() {
            return (Boolean) value("success");
        }
        public static final class Builder {
            private final Map<String, Object> values = new LinkedHashMap<>();
            /**
             * GUID of the resulting global memory dump.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder dumpGuid(@Nullable String value) {
                if (value == null) values.remove("dumpGuid");
                else values.put("dumpGuid", jsonValue(value));
                return this;
            }
            /**
             * True iff the global memory dump succeeded.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder success(@Nullable Boolean value) {
                if (value == null) values.remove("success");
                else values.put("success", jsonValue(value));
                return this;
            }
            public RequestMemoryDumpResult build() {
                if (!values.containsKey("dumpGuid")) throw new IllegalStateException("Missing required CDP field: dumpGuid");
                if (!values.containsKey("success")) throw new IllegalStateException("Missing required CDP field: success");
                return new RequestMemoryDumpResult(values);
            }
        }
    }
    /**
     * Start trace events collection.
     */
    public static final class StartParams extends CdpObject {
        private StartParams(Map<String, Object> values) { super(values); }
        @Nullable public static StartParams fromMap(@Nullable Map<String, Object> values) {
            return values == null ? null : new StartParams(values);
        }
        public static Builder builder() { return new Builder(); }
        /**
         * Category/tag filter
         * <p><b>Experimental:</b> this part of CDP may change without notice.
         * @return the protocol field value
         * @deprecated Deprecated by the Chromium DevTools Protocol.
         */
        @Deprecated
        @Nullable public String categories() {
            return (String) value("categories");
        }
        /**
         * Tracing options
         * <p><b>Experimental:</b> this part of CDP may change without notice.
         * @return the protocol field value
         * @deprecated Deprecated by the Chromium DevTools Protocol.
         */
        @Deprecated
        @Nullable public String options() {
            return (String) value("options");
        }
        /**
         * If set, the agent will issue bufferUsage events at this interval, specified in milliseconds
         * <p><b>Experimental:</b> this part of CDP may change without notice.
         * @return the protocol field value
         */
        @Nullable public Double bufferUsageReportingInterval() {
            return numberAsDouble(value("bufferUsageReportingInterval"));
        }
        /**
         * Whether to report trace events as series of dataCollected events or to save trace to a stream (defaults to {@code ReportEvents}).
         * @return the protocol field value
         */
        @Nullable public String transferMode() {
            return (String) value("transferMode");
        }
        /**
         * Whether to report trace events as series of dataCollected events or to save trace to a stream (defaults to {@code ReportEvents}).
         */
        public static final class TransferModeValues {
            private TransferModeValues() {}
            public static final String REPORTEVENTS = "ReportEvents";
            public static final String RETURNASSTREAM = "ReturnAsStream";
        }
        /**
         * Trace data format to use. This only applies when using {@code ReturnAsStream} transfer mode (defaults to {@code json}).
         * @return the protocol field value
         */
        @Nullable public String streamFormat() {
            return (String) value("streamFormat");
        }
        /**
         * Compression format to use. This only applies when using {@code ReturnAsStream} transfer mode (defaults to {@code none})
         * <p><b>Experimental:</b> this part of CDP may change without notice.
         * @return the protocol field value
         */
        @Nullable public String streamCompression() {
            return (String) value("streamCompression");
        }
        /**
         * Returns the traceConfig field.
         * @return the protocol field value
         */
        @Nullable public Tracing.TraceConfig traceConfig() {
            return Tracing.TraceConfig.fromMap(objectMap(value("traceConfig")));
        }
        /**
         * Base64-encoded serialized perfetto.protos.TraceConfig protobuf message When specified, the parameters {@code categories}, {@code options}, {@code traceConfig} are ignored. (Encoded as a base64 string when passed over JSON)
         * <p><b>Experimental:</b> this part of CDP may change without notice.
         * @return the protocol field value
         */
        @Nullable public String perfettoConfig() {
            return (String) value("perfettoConfig");
        }
        /**
         * Backend type (defaults to {@code auto})
         * <p><b>Experimental:</b> this part of CDP may change without notice.
         * @return the protocol field value
         */
        @Nullable public String tracingBackend() {
            return (String) value("tracingBackend");
        }
        public static final class Builder {
            private final Map<String, Object> values = new LinkedHashMap<>();
            /**
             * Category/tag filter
             * <p><b>Experimental:</b> this part of CDP may change without notice.
             * @param value field value; null removes an optional value
             * @return this builder
             * @deprecated Deprecated by the Chromium DevTools Protocol.
             */
            @Deprecated
            public Builder categories(@Nullable String value) {
                if (value == null) values.remove("categories");
                else values.put("categories", jsonValue(value));
                return this;
            }
            /**
             * Tracing options
             * <p><b>Experimental:</b> this part of CDP may change without notice.
             * @param value field value; null removes an optional value
             * @return this builder
             * @deprecated Deprecated by the Chromium DevTools Protocol.
             */
            @Deprecated
            public Builder options(@Nullable String value) {
                if (value == null) values.remove("options");
                else values.put("options", jsonValue(value));
                return this;
            }
            /**
             * If set, the agent will issue bufferUsage events at this interval, specified in milliseconds
             * <p><b>Experimental:</b> this part of CDP may change without notice.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder bufferUsageReportingInterval(@Nullable Double value) {
                if (value == null) values.remove("bufferUsageReportingInterval");
                else values.put("bufferUsageReportingInterval", jsonValue(value));
                return this;
            }
            /**
             * Whether to report trace events as series of dataCollected events or to save trace to a stream (defaults to {@code ReportEvents}).
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder transferMode(@Nullable String value) {
                if (value == null) values.remove("transferMode");
                else values.put("transferMode", jsonValue(value));
                return this;
            }
            /**
             * Trace data format to use. This only applies when using {@code ReturnAsStream} transfer mode (defaults to {@code json}).
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder streamFormat(@Nullable String value) {
                if (value == null) values.remove("streamFormat");
                else values.put("streamFormat", jsonValue(value));
                return this;
            }
            /**
             * Compression format to use. This only applies when using {@code ReturnAsStream} transfer mode (defaults to {@code none})
             * <p><b>Experimental:</b> this part of CDP may change without notice.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder streamCompression(@Nullable String value) {
                if (value == null) values.remove("streamCompression");
                else values.put("streamCompression", jsonValue(value));
                return this;
            }
            /**
             * Sets the traceConfig field.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder traceConfig(@Nullable Tracing.TraceConfig value) {
                if (value == null) values.remove("traceConfig");
                else values.put("traceConfig", jsonValue(value));
                return this;
            }
            /**
             * Base64-encoded serialized perfetto.protos.TraceConfig protobuf message When specified, the parameters {@code categories}, {@code options}, {@code traceConfig} are ignored. (Encoded as a base64 string when passed over JSON)
             * <p><b>Experimental:</b> this part of CDP may change without notice.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder perfettoConfig(@Nullable String value) {
                if (value == null) values.remove("perfettoConfig");
                else values.put("perfettoConfig", jsonValue(value));
                return this;
            }
            /**
             * Backend type (defaults to {@code auto})
             * <p><b>Experimental:</b> this part of CDP may change without notice.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder tracingBackend(@Nullable String value) {
                if (value == null) values.remove("tracingBackend");
                else values.put("tracingBackend", jsonValue(value));
                return this;
            }
            public StartParams build() {
                return new StartParams(values);
            }
        }
    }
    /**
     * Start trace events collection.
     */
    public static final class StartResult extends CdpObject {
        private StartResult(Map<String, Object> values) { super(values); }
        @Nullable public static StartResult fromMap(@Nullable Map<String, Object> values) {
            return values == null ? null : new StartResult(values);
        }
        public static Builder builder() { return new Builder(); }
        public static final class Builder {
            private final Map<String, Object> values = new LinkedHashMap<>();
            public StartResult build() {
                return new StartResult(values);
            }
        }
    }
    /**
     * Payload of the Tracing.bufferUsage event.
     * <p><b>Experimental:</b> this part of CDP may change without notice.
     */
    public static final class BufferUsageEvent extends CdpObject {
        private BufferUsageEvent(Map<String, Object> values) { super(values); }
        @Nullable public static BufferUsageEvent fromMap(@Nullable Map<String, Object> values) {
            return values == null ? null : new BufferUsageEvent(values);
        }
        public static Builder builder() { return new Builder(); }
        /**
         * A number in range [0..1] that indicates the used size of event buffer as a fraction of its total size.
         * @return the protocol field value
         */
        @Nullable public Double percentFull() {
            return numberAsDouble(value("percentFull"));
        }
        /**
         * An approximate number of events in the trace log.
         * @return the protocol field value
         */
        @Nullable public Double eventCount() {
            return numberAsDouble(value("eventCount"));
        }
        /**
         * A number in range [0..1] that indicates the used size of event buffer as a fraction of its total size.
         * @return the protocol field value
         */
        @Nullable public Double value() {
            return numberAsDouble(value("value"));
        }
        public static final class Builder {
            private final Map<String, Object> values = new LinkedHashMap<>();
            /**
             * A number in range [0..1] that indicates the used size of event buffer as a fraction of its total size.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder percentFull(@Nullable Double value) {
                if (value == null) values.remove("percentFull");
                else values.put("percentFull", jsonValue(value));
                return this;
            }
            /**
             * An approximate number of events in the trace log.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder eventCount(@Nullable Double value) {
                if (value == null) values.remove("eventCount");
                else values.put("eventCount", jsonValue(value));
                return this;
            }
            /**
             * A number in range [0..1] that indicates the used size of event buffer as a fraction of its total size.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder value(@Nullable Double value) {
                if (value == null) values.remove("value");
                else values.put("value", jsonValue(value));
                return this;
            }
            public BufferUsageEvent build() {
                return new BufferUsageEvent(values);
            }
        }
    }
    /**
     * Contains a bucket of collected trace events. When tracing is stopped collected events will be sent as a sequence of dataCollected events followed by tracingComplete event.
     * <p><b>Experimental:</b> this part of CDP may change without notice.
     */
    public static final class DataCollectedEvent extends CdpObject {
        private DataCollectedEvent(Map<String, Object> values) { super(values); }
        @Nullable public static DataCollectedEvent fromMap(@Nullable Map<String, Object> values) {
            return values == null ? null : new DataCollectedEvent(values);
        }
        public static Builder builder() { return new Builder(); }
        /**
         * Returns the value field.
         * @return the protocol field value
         */
        @Nullable public java.util.List<java.util.Map<String, Object>> value() {
            return list(value("value"), element0 -> objectMap(element0));
        }
        public static final class Builder {
            private final Map<String, Object> values = new LinkedHashMap<>();
            /**
             * Sets the value field.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder value(@Nullable java.util.List<java.util.Map<String, Object>> value) {
                if (value == null) values.remove("value");
                else values.put("value", jsonValue(value));
                return this;
            }
            public DataCollectedEvent build() {
                if (!values.containsKey("value")) throw new IllegalStateException("Missing required CDP field: value");
                return new DataCollectedEvent(values);
            }
        }
    }
    /**
     * Signals that tracing is stopped and there is no trace buffers pending flush, all data were delivered via dataCollected events.
     */
    public static final class TracingCompleteEvent extends CdpObject {
        private TracingCompleteEvent(Map<String, Object> values) { super(values); }
        @Nullable public static TracingCompleteEvent fromMap(@Nullable Map<String, Object> values) {
            return values == null ? null : new TracingCompleteEvent(values);
        }
        public static Builder builder() { return new Builder(); }
        /**
         * Indicates whether some trace data is known to have been lost, e.g. because the trace ring buffer wrapped around.
         * @return the protocol field value
         */
        @Nullable public Boolean dataLossOccurred() {
            return (Boolean) value("dataLossOccurred");
        }
        /**
         * A handle of the stream that holds resulting trace data.
         * @return the protocol field value
         */
        @Nullable public String stream() {
            return (String) value("stream");
        }
        /**
         * Trace data format of returned stream.
         * @return the protocol field value
         */
        @Nullable public String traceFormat() {
            return (String) value("traceFormat");
        }
        /**
         * Compression format of returned stream.
         * @return the protocol field value
         */
        @Nullable public String streamCompression() {
            return (String) value("streamCompression");
        }
        public static final class Builder {
            private final Map<String, Object> values = new LinkedHashMap<>();
            /**
             * Indicates whether some trace data is known to have been lost, e.g. because the trace ring buffer wrapped around.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder dataLossOccurred(@Nullable Boolean value) {
                if (value == null) values.remove("dataLossOccurred");
                else values.put("dataLossOccurred", jsonValue(value));
                return this;
            }
            /**
             * A handle of the stream that holds resulting trace data.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder stream(@Nullable String value) {
                if (value == null) values.remove("stream");
                else values.put("stream", jsonValue(value));
                return this;
            }
            /**
             * Trace data format of returned stream.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder traceFormat(@Nullable String value) {
                if (value == null) values.remove("traceFormat");
                else values.put("traceFormat", jsonValue(value));
                return this;
            }
            /**
             * Compression format of returned stream.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder streamCompression(@Nullable String value) {
                if (value == null) values.remove("streamCompression");
                else values.put("streamCompression", jsonValue(value));
                return this;
            }
            public TracingCompleteEvent build() {
                if (!values.containsKey("dataLossOccurred")) throw new IllegalStateException("Missing required CDP field: dataLossOccurred");
                return new TracingCompleteEvent(values);
            }
        }
    }
    public static final class Client {
        private final CdpClient client;
        public Client(CdpClient client) { this.client = client; }
        /**
         * Stop trace events collection.
         * @return a stage completing with the command result
         */
        public CompletionStage<EndResult> end() {
            return client.call("Tracing.end", null, EndResult::fromMap);
        }
        /**
         * Gets supported tracing categories.
         * <p><b>Experimental:</b> this part of CDP may change without notice.
         * @return a stage completing with the command result
         */
        public CompletionStage<GetCategoriesResult> getCategories() {
            return client.call("Tracing.getCategories", null, GetCategoriesResult::fromMap);
        }
        /**
         * Return a descriptor for all available tracing categories.
         * <p><b>Experimental:</b> this part of CDP may change without notice.
         * @return a stage completing with the command result
         */
        public CompletionStage<GetTrackEventDescriptorResult> getTrackEventDescriptor() {
            return client.call("Tracing.getTrackEventDescriptor", null, GetTrackEventDescriptorResult::fromMap);
        }
        /**
         * Record a clock sync marker in the trace.
         * <p><b>Experimental:</b> this part of CDP may change without notice.
         * @param params command parameters
         * @return a stage completing with the command result
         */
        public CompletionStage<RecordClockSyncMarkerResult> recordClockSyncMarker(RecordClockSyncMarkerParams params) {
            return client.call("Tracing.recordClockSyncMarker", params, RecordClockSyncMarkerResult::fromMap);
        }
        /**
         * Request a global memory dump.
         * <p><b>Experimental:</b> this part of CDP may change without notice.
         * @param params command parameters
         * @return a stage completing with the command result
         */
        public CompletionStage<RequestMemoryDumpResult> requestMemoryDump(RequestMemoryDumpParams params) {
            return client.call("Tracing.requestMemoryDump", params, RequestMemoryDumpResult::fromMap);
        }
        /**
         * Start trace events collection.
         * @param params command parameters
         * @return a stage completing with the command result
         */
        public CompletionStage<StartResult> start(StartParams params) {
            return client.call("Tracing.start", params, StartResult::fromMap);
        }
        /**
         * Subscribes to Tracing.bufferUsage.
         * <p><b>Experimental:</b> this part of CDP may change without notice.
         * @param handler event callback
         * @return a removable subscription
         */
        public CdpSubscription onBufferUsage(Consumer<BufferUsageEvent> handler) {
            return client.on("Tracing.bufferUsage", BufferUsageEvent::fromMap, handler);
        }
        /**
         * Contains a bucket of collected trace events. When tracing is stopped collected events will be sent as a sequence of dataCollected events followed by tracingComplete event.
         * <p><b>Experimental:</b> this part of CDP may change without notice.
         * @param handler event callback
         * @return a removable subscription
         */
        public CdpSubscription onDataCollected(Consumer<DataCollectedEvent> handler) {
            return client.on("Tracing.dataCollected", DataCollectedEvent::fromMap, handler);
        }
        /**
         * Signals that tracing is stopped and there is no trace buffers pending flush, all data were delivered via dataCollected events.
         * @param handler event callback
         * @return a removable subscription
         */
        public CdpSubscription onTracingComplete(Consumer<TracingCompleteEvent> handler) {
            return client.on("Tracing.tracingComplete", TracingCompleteEvent::fromMap, handler);
        }
    }
}
