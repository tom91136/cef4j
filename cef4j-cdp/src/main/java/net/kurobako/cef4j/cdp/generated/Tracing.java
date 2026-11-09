// GENERATED - do not edit. Regenerate via: ./mvnw generate-sources -pl cef4j-cdp -Dcef.version=150.0.18+gdb11278+chromium-150.0.7871.213
package net.kurobako.cef4j.cdp.generated;

import java.util.Map;
import java.util.Optional;
import java.util.OptionalDouble;
import java.util.concurrent.CompletionStage;
import java.util.function.Consumer;
import javax.annotation.processing.Generated;
import net.kurobako.cef4j.cdp.CdpClient;
import net.kurobako.cef4j.cdp.CdpDomainClient;
import net.kurobako.cef4j.cdp.CdpField;
import net.kurobako.cef4j.cdp.CdpObject;
import net.kurobako.cef4j.cdp.CdpSubscription;
import net.kurobako.cef4j.cdp.CdpValue;
import net.kurobako.cef4j.policy.NullableBoundary;
import org.jspecify.annotations.Nullable;

/**
 * Chrome DevTools Protocol Tracing domain.
 * @see <a href="https://chromium.googlesource.com/chromium/src/+/refs/tags/150.0.7871.213/third_party/blink/public/devtools_protocol/domains/Tracing.pdl">Pinned protocol source</a>
 */
@Generated("./mvnw generate-sources -pl cef4j-cdp -Dcef.version=150.0.18+gdb11278+chromium-150.0.7871.213")
@SuppressWarnings({"EscapedEntity", "InvalidParam", "JavaLangClash", "MissingSummary", "Unchecked", "UnusedMethod"})
@NullableBoundary("CDP wire objects use null for absent optional fields")
public final class Tracing {
    private Tracing() {}
    private static final CdpField<java.util.List<String>> CATEGORIES = CdpField.of("categories", value -> CdpObject.requireList(value, element0 -> (String) element0));
    private static final CdpField<String> DESCRIPTOR = CdpField.of("descriptor", value -> (String) value);
    private static final CdpField<java.util.List<String>> EXCLUDED_CATEGORIES = CdpField.of("excludedCategories", value -> CdpObject.requireList(value, element0 -> (String) element0));
    private static final CdpField<java.util.List<String>> INCLUDED_CATEGORIES = CdpField.of("includedCategories", value -> CdpObject.requireList(value, element0 -> (String) element0));
    private static final CdpField<Tracing.MemoryDumpLevelOfDetail> LEVEL_OF_DETAIL = CdpField.of("levelOfDetail", value -> Tracing.MemoryDumpLevelOfDetail.of((String) value));
    private static final CdpField<TraceConfig.RecordModeValues> RECORD_MODE = CdpField.of("recordMode", value -> TraceConfig.RecordModeValues.of((String) value));
    private static final CdpField<IO.StreamHandle> STREAM = CdpField.of("stream", value -> new IO.StreamHandle((String) value));
    private static final CdpField<Tracing.StreamCompression> STREAM_COMPRESSION = CdpField.of("streamCompression", value -> Tracing.StreamCompression.of((String) value));
    private static final CdpField<Tracing.StreamFormat> STREAM_FORMAT = CdpField.of("streamFormat", value -> Tracing.StreamFormat.of((String) value));
    private static final CdpField<java.util.List<String>> SYNTHETIC_DELAYS = CdpField.of("syntheticDelays", value -> CdpObject.requireList(value, element0 -> (String) element0));
    private static final CdpField<Tracing.TraceConfig> TRACE_CONFIG = CdpField.of("traceConfig", value -> java.util.Objects.requireNonNull(Tracing.TraceConfig.fromMap(CdpObject.requireObjectMap(value))));
    private static final CdpField<Tracing.StreamFormat> TRACE_FORMAT = CdpField.of("traceFormat", value -> Tracing.StreamFormat.of((String) value));
    private static final CdpField<Tracing.TracingBackend> TRACING_BACKEND = CdpField.of("tracingBackend", value -> Tracing.TracingBackend.of((String) value));
    private static final CdpField<StartTransferModeValues> TRANSFER_MODE = CdpField.of("transferMode", value -> StartTransferModeValues.of((String) value));
    private static final CdpField<java.util.List<java.util.Map<String, Object>>> VALUE = CdpField.of("value", value -> CdpObject.requireList(value, element0 -> CdpObject.requireObjectMap(element0)));
    /**
     */
    public static final class TraceConfig extends CdpObject {
        public TraceConfig() {}
        private TraceConfig(Map<String, Object> values) { super(values); }
        public static TraceConfig fromMap(Map<String, Object> values) {
            return new TraceConfig(values);
        }
        /**
         * Controls how the trace buffer stores data. The default is {@code recordUntilFull}.
         * <p><b>Experimental:</b> this part of CDP may change without notice.
         */
        public enum RecordModeValues implements CdpValue<String> {
            RECORDUNTILFULL("recordUntilFull"),
            RECORDCONTINUOUSLY("recordContinuously"),
            RECORDASMUCHASPOSSIBLE("recordAsMuchAsPossible"),
            ECHOTOCONSOLE("echoToConsole");
            public final String value;
            RecordModeValues(String value) { this.value = value; }
            @Override public String value() { return value; }
            public static RecordModeValues of(String value) {
                for (RecordModeValues constant : values()) {
                    if (constant.value.equals(value)) return constant;
                }
                throw new IllegalArgumentException("Unknown RecordModeValues value: " + value);
            }
        }
        /**
         * Controls how the trace buffer stores data. The default is {@code recordUntilFull}.
         * <p><b>Experimental:</b> this part of CDP may change without notice.
         * @return the protocol field value, empty when absent
         */
        public Optional<TraceConfig.RecordModeValues> recordMode() {
            return optional(RECORD_MODE);
        }
        /**
         * Size of the trace buffer in kilobytes. If not specified or zero is passed, a default value of 200 MB would be used.
         * <p><b>Experimental:</b> this part of CDP may change without notice.
         * @return the protocol field value, empty when absent
         */
        public OptionalDouble traceBufferSizeInKb() {
            return optionalDouble("traceBufferSizeInKb");
        }
        /**
         * Turns on JavaScript stack sampling.
         * <p><b>Experimental:</b> this part of CDP may change without notice.
         * @return the protocol field value, empty when absent
         */
        public Optional<Boolean> enableSampling() {
            return optionalBoolean("enableSampling");
        }
        /**
         * Turns on system tracing.
         * <p><b>Experimental:</b> this part of CDP may change without notice.
         * @return the protocol field value, empty when absent
         */
        public Optional<Boolean> enableSystrace() {
            return optionalBoolean("enableSystrace");
        }
        /**
         * Turns on argument filter.
         * <p><b>Experimental:</b> this part of CDP may change without notice.
         * @return the protocol field value, empty when absent
         */
        public Optional<Boolean> enableArgumentFilter() {
            return optionalBoolean("enableArgumentFilter");
        }
        /**
         * Included category filters.
         * @return the protocol field value, empty when absent
         */
        public Optional<java.util.List<String>> includedCategories() {
            return optional(INCLUDED_CATEGORIES);
        }
        /**
         * Excluded category filters.
         * @return the protocol field value, empty when absent
         */
        public Optional<java.util.List<String>> excludedCategories() {
            return optional(EXCLUDED_CATEGORIES);
        }
        /**
         * Configuration to synthesize the delays in tracing.
         * <p><b>Experimental:</b> this part of CDP may change without notice.
         * @return the protocol field value, empty when absent
         */
        public Optional<java.util.List<String>> syntheticDelays() {
            return optional(SYNTHETIC_DELAYS);
        }
        /**
         * Configuration for memory dump triggers. Used only when &quot;memory-infra&quot; category is enabled.
         * <p><b>Experimental:</b> this part of CDP may change without notice.
         * @return the protocol field value, empty when absent
         */
        public Optional<java.util.Map<String, Object>> memoryDumpConfig() {
            return optionalObject("memoryDumpConfig");
        }
        /**
         * Controls how the trace buffer stores data. The default is {@code recordUntilFull}.
         * <p><b>Experimental:</b> this part of CDP may change without notice.
         * @param recordMode field value
         * @return this model
         */
        public TraceConfig recordMode(TraceConfig.RecordModeValues recordMode) {
            set("recordMode", recordMode);
            return this;
        }
        /**
         * Size of the trace buffer in kilobytes. If not specified or zero is passed, a default value of 200 MB would be used.
         * <p><b>Experimental:</b> this part of CDP may change without notice.
         * @param traceBufferSizeInKb field value
         * @return this model
         */
        public TraceConfig traceBufferSizeInKb(double traceBufferSizeInKb) {
            set("traceBufferSizeInKb", traceBufferSizeInKb);
            return this;
        }
        /**
         * Turns on JavaScript stack sampling.
         * <p><b>Experimental:</b> this part of CDP may change without notice.
         * @param enableSampling field value
         * @return this model
         */
        public TraceConfig enableSampling(boolean enableSampling) {
            set("enableSampling", enableSampling);
            return this;
        }
        /**
         * Turns on system tracing.
         * <p><b>Experimental:</b> this part of CDP may change without notice.
         * @param enableSystrace field value
         * @return this model
         */
        public TraceConfig enableSystrace(boolean enableSystrace) {
            set("enableSystrace", enableSystrace);
            return this;
        }
        /**
         * Turns on argument filter.
         * <p><b>Experimental:</b> this part of CDP may change without notice.
         * @param enableArgumentFilter field value
         * @return this model
         */
        public TraceConfig enableArgumentFilter(boolean enableArgumentFilter) {
            set("enableArgumentFilter", enableArgumentFilter);
            return this;
        }
        /**
         * Included category filters.
         * @param includedCategories field value
         * @return this model
         */
        public TraceConfig includedCategories(java.util.List<String> includedCategories) {
            set("includedCategories", includedCategories);
            return this;
        }
        /**
         * Excluded category filters.
         * @param excludedCategories field value
         * @return this model
         */
        public TraceConfig excludedCategories(java.util.List<String> excludedCategories) {
            set("excludedCategories", excludedCategories);
            return this;
        }
        /**
         * Configuration to synthesize the delays in tracing.
         * <p><b>Experimental:</b> this part of CDP may change without notice.
         * @param syntheticDelays field value
         * @return this model
         */
        public TraceConfig syntheticDelays(java.util.List<String> syntheticDelays) {
            set("syntheticDelays", syntheticDelays);
            return this;
        }
        /**
         * Configuration for memory dump triggers. Used only when &quot;memory-infra&quot; category is enabled.
         * <p><b>Experimental:</b> this part of CDP may change without notice.
         * @param memoryDumpConfig field value
         * @return this model
         */
        public TraceConfig memoryDumpConfig(java.util.Map<String, Object> memoryDumpConfig) {
            set("memoryDumpConfig", memoryDumpConfig);
            return this;
        }
    }
    /**
     * Data format of a trace. Can be either the legacy JSON format or the protocol buffer format. Note that the JSON format will be deprecated soon.
     * <p><b>Experimental:</b> this part of CDP may change without notice.
     */
    public enum StreamFormat implements CdpValue<String> {
        JSON("json"),
        PROTO("proto");
        public final String value;
        StreamFormat(String value) { this.value = value; }
        @Override public String value() { return value; }
        public static StreamFormat of(String value) {
            for (StreamFormat constant : values()) {
                if (constant.value.equals(value)) return constant;
            }
            throw new IllegalArgumentException("Unknown StreamFormat value: " + value);
        }
    }
    /**
     * Compression type to use for traces returned via streams.
     * <p><b>Experimental:</b> this part of CDP may change without notice.
     */
    public enum StreamCompression implements CdpValue<String> {
        NONE("none"),
        GZIP("gzip");
        public final String value;
        StreamCompression(String value) { this.value = value; }
        @Override public String value() { return value; }
        public static StreamCompression of(String value) {
            for (StreamCompression constant : values()) {
                if (constant.value.equals(value)) return constant;
            }
            throw new IllegalArgumentException("Unknown StreamCompression value: " + value);
        }
    }
    /**
     * Details exposed when memory request explicitly declared. Keep consistent with memory_dump_request_args.h and memory_instrumentation.mojom
     * <p><b>Experimental:</b> this part of CDP may change without notice.
     */
    public enum MemoryDumpLevelOfDetail implements CdpValue<String> {
        BACKGROUND("background"),
        LIGHT("light"),
        DETAILED("detailed");
        public final String value;
        MemoryDumpLevelOfDetail(String value) { this.value = value; }
        @Override public String value() { return value; }
        public static MemoryDumpLevelOfDetail of(String value) {
            for (MemoryDumpLevelOfDetail constant : values()) {
                if (constant.value.equals(value)) return constant;
            }
            throw new IllegalArgumentException("Unknown MemoryDumpLevelOfDetail value: " + value);
        }
    }
    /**
     * Backend type to use for tracing. {@code chrome} uses the Chrome-integrated tracing service and is supported on all platforms. {@code system} is only supported on Chrome OS and uses the Perfetto system tracing service. {@code auto} chooses {@code system} when the perfettoConfig provided to Tracing.start specifies at least one non-Chrome data source; otherwise uses {@code chrome}.
     * <p><b>Experimental:</b> this part of CDP may change without notice.
     */
    public enum TracingBackend implements CdpValue<String> {
        AUTO("auto"),
        CHROME("chrome"),
        SYSTEM("system");
        public final String value;
        TracingBackend(String value) { this.value = value; }
        @Override public String value() { return value; }
        public static TracingBackend of(String value) {
            for (TracingBackend constant : values()) {
                if (constant.value.equals(value)) return constant;
            }
            throw new IllegalArgumentException("Unknown TracingBackend value: " + value);
        }
    }
    /**
     * Record a clock sync marker in the trace.
     * <p><b>Experimental:</b> this part of CDP may change without notice.
     */
    public static final class RecordClockSyncMarkerRequest extends CdpObject {
        public RecordClockSyncMarkerRequest() {}
        public static RecordClockSyncMarkerRequest fromMap(Map<String, Object> values) {
            final RecordClockSyncMarkerRequest instance_ = new RecordClockSyncMarkerRequest();
            if (values != null) instance_.values.putAll(values);
            return instance_;
        }
        /**
         * The ID of this clock sync marker
         * @return the protocol field value
         */
        public String syncId() {
            return requireString("syncId");
        }
        /**
         * The ID of this clock sync marker
         * @param syncId field value
         * @return this model
         */
        public RecordClockSyncMarkerRequest syncId(String syncId) {
            set("syncId", syncId);
            return this;
        }
    }
    /**
     * Request a global memory dump.
     * <p><b>Experimental:</b> this part of CDP may change without notice.
     */
    public static final class RequestMemoryDumpRequest extends CdpObject {
        public RequestMemoryDumpRequest() {}
        public static RequestMemoryDumpRequest fromMap(Map<String, Object> values) {
            final RequestMemoryDumpRequest instance_ = new RequestMemoryDumpRequest();
            if (values != null) instance_.values.putAll(values);
            return instance_;
        }
        /**
         * Enables more deterministic results by forcing garbage collection
         * @return the protocol field value, empty when absent
         */
        public Optional<Boolean> deterministic() {
            return optionalBoolean("deterministic");
        }
        /**
         * Specifies level of details in memory dump. Defaults to &quot;detailed&quot;.
         * @return the protocol field value, empty when absent
         */
        public Optional<Tracing.MemoryDumpLevelOfDetail> levelOfDetail() {
            return optional(LEVEL_OF_DETAIL);
        }
        /**
         * Enables more deterministic results by forcing garbage collection
         * @param deterministic field value
         * @return this model
         */
        public RequestMemoryDumpRequest deterministic(boolean deterministic) {
            set("deterministic", deterministic);
            return this;
        }
        /**
         * Specifies level of details in memory dump. Defaults to &quot;detailed&quot;.
         * @param levelOfDetail field value
         * @return this model
         */
        public RequestMemoryDumpRequest levelOfDetail(Tracing.MemoryDumpLevelOfDetail levelOfDetail) {
            set("levelOfDetail", levelOfDetail);
            return this;
        }
    }
    /**
     * Start trace events collection.
     */
    public static final class StartRequest extends CdpObject {
        public StartRequest() {}
        public static StartRequest fromMap(Map<String, Object> values) {
            final StartRequest instance_ = new StartRequest();
            if (values != null) instance_.values.putAll(values);
            return instance_;
        }
        /**
         * Category/tag filter
         * <p><b>Experimental:</b> this part of CDP may change without notice.
         * @return the protocol field value, empty when absent
         * @deprecated Deprecated by the Chromium DevTools Protocol.
         */
        @Deprecated
        public Optional<String> categories() {
            return optionalString("categories");
        }
        /**
         * Tracing options
         * <p><b>Experimental:</b> this part of CDP may change without notice.
         * @return the protocol field value, empty when absent
         * @deprecated Deprecated by the Chromium DevTools Protocol.
         */
        @Deprecated
        public Optional<String> options() {
            return optionalString("options");
        }
        /**
         * If set, the agent will issue bufferUsage events at this interval, specified in milliseconds
         * <p><b>Experimental:</b> this part of CDP may change without notice.
         * @return the protocol field value, empty when absent
         */
        public OptionalDouble bufferUsageReportingInterval() {
            return optionalDouble("bufferUsageReportingInterval");
        }
        /**
         * Whether to report trace events as series of dataCollected events or to save trace to a stream (defaults to {@code ReportEvents}).
         * @return the protocol field value, empty when absent
         */
        public Optional<StartTransferModeValues> transferMode() {
            return optional(TRANSFER_MODE);
        }
        /**
         * Trace data format to use. This only applies when using {@code ReturnAsStream} transfer mode (defaults to {@code json}).
         * @return the protocol field value, empty when absent
         */
        public Optional<Tracing.StreamFormat> streamFormat() {
            return optional(STREAM_FORMAT);
        }
        /**
         * Compression format to use. This only applies when using {@code ReturnAsStream} transfer mode (defaults to {@code none})
         * <p><b>Experimental:</b> this part of CDP may change without notice.
         * @return the protocol field value, empty when absent
         */
        public Optional<Tracing.StreamCompression> streamCompression() {
            return optional(STREAM_COMPRESSION);
        }
        /**
         * Returns the traceConfig field.
         * @return the protocol field value, empty when absent
         */
        public Optional<Tracing.TraceConfig> traceConfig() {
            return optional(TRACE_CONFIG);
        }
        /**
         * Base64-encoded serialized perfetto.protos.TraceConfig protobuf message When specified, the parameters {@code categories}, {@code options}, {@code traceConfig} are ignored. (Encoded as a base64 string when passed over JSON)
         * <p><b>Experimental:</b> this part of CDP may change without notice.
         * @return the protocol field value, empty when absent
         */
        public Optional<String> perfettoConfig() {
            return optionalString("perfettoConfig");
        }
        /**
         * Backend type (defaults to {@code auto})
         * <p><b>Experimental:</b> this part of CDP may change without notice.
         * @return the protocol field value, empty when absent
         */
        public Optional<Tracing.TracingBackend> tracingBackend() {
            return optional(TRACING_BACKEND);
        }
        /**
         * Category/tag filter
         * <p><b>Experimental:</b> this part of CDP may change without notice.
         * @param categories field value
         * @return this model
         * @deprecated Deprecated by the Chromium DevTools Protocol.
         */
        @Deprecated
        public StartRequest categories(String categories) {
            set("categories", categories);
            return this;
        }
        /**
         * Tracing options
         * <p><b>Experimental:</b> this part of CDP may change without notice.
         * @param options field value
         * @return this model
         * @deprecated Deprecated by the Chromium DevTools Protocol.
         */
        @Deprecated
        public StartRequest options(String options) {
            set("options", options);
            return this;
        }
        /**
         * If set, the agent will issue bufferUsage events at this interval, specified in milliseconds
         * <p><b>Experimental:</b> this part of CDP may change without notice.
         * @param bufferUsageReportingInterval field value
         * @return this model
         */
        public StartRequest bufferUsageReportingInterval(double bufferUsageReportingInterval) {
            set("bufferUsageReportingInterval", bufferUsageReportingInterval);
            return this;
        }
        /**
         * Whether to report trace events as series of dataCollected events or to save trace to a stream (defaults to {@code ReportEvents}).
         * @param transferMode field value
         * @return this model
         */
        public StartRequest transferMode(StartTransferModeValues transferMode) {
            set("transferMode", transferMode);
            return this;
        }
        /**
         * Trace data format to use. This only applies when using {@code ReturnAsStream} transfer mode (defaults to {@code json}).
         * @param streamFormat field value
         * @return this model
         */
        public StartRequest streamFormat(Tracing.StreamFormat streamFormat) {
            set("streamFormat", streamFormat);
            return this;
        }
        /**
         * Compression format to use. This only applies when using {@code ReturnAsStream} transfer mode (defaults to {@code none})
         * <p><b>Experimental:</b> this part of CDP may change without notice.
         * @param streamCompression field value
         * @return this model
         */
        public StartRequest streamCompression(Tracing.StreamCompression streamCompression) {
            set("streamCompression", streamCompression);
            return this;
        }
        /**
         * Sets the traceConfig field.
         * @param traceConfig field value
         * @return this model
         */
        public StartRequest traceConfig(Tracing.TraceConfig traceConfig) {
            set("traceConfig", traceConfig);
            return this;
        }
        /**
         * Base64-encoded serialized perfetto.protos.TraceConfig protobuf message When specified, the parameters {@code categories}, {@code options}, {@code traceConfig} are ignored. (Encoded as a base64 string when passed over JSON)
         * <p><b>Experimental:</b> this part of CDP may change without notice.
         * @param perfettoConfig field value
         * @return this model
         */
        public StartRequest perfettoConfig(String perfettoConfig) {
            set("perfettoConfig", perfettoConfig);
            return this;
        }
        /**
         * Backend type (defaults to {@code auto})
         * <p><b>Experimental:</b> this part of CDP may change without notice.
         * @param tracingBackend field value
         * @return this model
         */
        public StartRequest tracingBackend(Tracing.TracingBackend tracingBackend) {
            set("tracingBackend", tracingBackend);
            return this;
        }
    }
    /**
     * Request a global memory dump.
     * <p><b>Experimental:</b> this part of CDP may change without notice.
     */
    public static final class RequestMemoryDumpResult extends CdpObject {
        public RequestMemoryDumpResult() {}
        private RequestMemoryDumpResult(Map<String, Object> values) { super(values); }
        public static RequestMemoryDumpResult fromMap(Map<String, Object> values) {
            return new RequestMemoryDumpResult(values);
        }
        /**
         * GUID of the resulting global memory dump.
         * @return the protocol field value
         */
        public String dumpGuid() {
            return requireString("dumpGuid");
        }
        /**
         * True iff the global memory dump succeeded.
         * @return the protocol field value
         */
        public boolean success() {
            return requireBoolean("success");
        }
        /**
         * GUID of the resulting global memory dump.
         * @param dumpGuid field value
         * @return this model
         */
        public RequestMemoryDumpResult dumpGuid(String dumpGuid) {
            set("dumpGuid", dumpGuid);
            return this;
        }
        /**
         * True iff the global memory dump succeeded.
         * @param success field value
         * @return this model
         */
        public RequestMemoryDumpResult success(boolean success) {
            set("success", success);
            return this;
        }
    }
    /**
     * Payload of the Tracing.bufferUsage event.
     * <p><b>Experimental:</b> this part of CDP may change without notice.
     */
    public static final class BufferUsageEvent extends CdpObject {
        public BufferUsageEvent() {}
        private BufferUsageEvent(Map<String, Object> values) { super(values); }
        public static BufferUsageEvent fromMap(Map<String, Object> values) {
            return new BufferUsageEvent(values);
        }
        /**
         * A number in range [0..1] that indicates the used size of event buffer as a fraction of its total size.
         * @return the protocol field value, empty when absent
         */
        public OptionalDouble percentFull() {
            return optionalDouble("percentFull");
        }
        /**
         * An approximate number of events in the trace log.
         * @return the protocol field value, empty when absent
         */
        public OptionalDouble eventCount() {
            return optionalDouble("eventCount");
        }
        /**
         * A number in range [0..1] that indicates the used size of event buffer as a fraction of its total size.
         * @return the protocol field value, empty when absent
         */
        public OptionalDouble value() {
            return optionalDouble("value");
        }
        /**
         * A number in range [0..1] that indicates the used size of event buffer as a fraction of its total size.
         * @param percentFull field value
         * @return this model
         */
        public BufferUsageEvent percentFull(double percentFull) {
            set("percentFull", percentFull);
            return this;
        }
        /**
         * An approximate number of events in the trace log.
         * @param eventCount field value
         * @return this model
         */
        public BufferUsageEvent eventCount(double eventCount) {
            set("eventCount", eventCount);
            return this;
        }
        /**
         * A number in range [0..1] that indicates the used size of event buffer as a fraction of its total size.
         * @param value field value
         * @return this model
         */
        public BufferUsageEvent value(double value) {
            set("value", value);
            return this;
        }
    }
    /**
     * Contains a bucket of collected trace events. When tracing is stopped collected events will be sent as a sequence of dataCollected events followed by tracingComplete event.
     * <p><b>Experimental:</b> this part of CDP may change without notice.
     */
    public static final class DataCollectedEvent extends CdpObject {
        public DataCollectedEvent() {}
        private DataCollectedEvent(Map<String, Object> values) { super(values); }
        public static DataCollectedEvent fromMap(Map<String, Object> values) {
            return new DataCollectedEvent(values);
        }
        /**
         * Returns the value field.
         * @return the protocol field value
         */
        public java.util.List<java.util.Map<String, Object>> value() {
            return require(VALUE);
        }
        /**
         * Sets the value field.
         * @param value field value
         * @return this model
         */
        public DataCollectedEvent value(java.util.List<java.util.Map<String, Object>> value) {
            set("value", value);
            return this;
        }
    }
    /**
     * Signals that tracing is stopped and there is no trace buffers pending flush, all data were delivered via dataCollected events.
     */
    public static final class TracingCompleteEvent extends CdpObject {
        public TracingCompleteEvent() {}
        private TracingCompleteEvent(Map<String, Object> values) { super(values); }
        public static TracingCompleteEvent fromMap(Map<String, Object> values) {
            return new TracingCompleteEvent(values);
        }
        /**
         * Indicates whether some trace data is known to have been lost, e.g. because the trace ring buffer wrapped around.
         * @return the protocol field value
         */
        public boolean dataLossOccurred() {
            return requireBoolean("dataLossOccurred");
        }
        /**
         * A handle of the stream that holds resulting trace data.
         * @return the protocol field value, empty when absent
         */
        public Optional<IO.StreamHandle> stream() {
            return optional(STREAM);
        }
        /**
         * Trace data format of returned stream.
         * @return the protocol field value, empty when absent
         */
        public Optional<Tracing.StreamFormat> traceFormat() {
            return optional(TRACE_FORMAT);
        }
        /**
         * Compression format of returned stream.
         * @return the protocol field value, empty when absent
         */
        public Optional<Tracing.StreamCompression> streamCompression() {
            return optional(STREAM_COMPRESSION);
        }
        /**
         * Indicates whether some trace data is known to have been lost, e.g. because the trace ring buffer wrapped around.
         * @param dataLossOccurred field value
         * @return this model
         */
        public TracingCompleteEvent dataLossOccurred(boolean dataLossOccurred) {
            set("dataLossOccurred", dataLossOccurred);
            return this;
        }
        /**
         * A handle of the stream that holds resulting trace data.
         * @param stream field value
         * @return this model
         */
        public TracingCompleteEvent stream(IO.StreamHandle stream) {
            set("stream", stream);
            return this;
        }
        /**
         * Trace data format of returned stream.
         * @param traceFormat field value
         * @return this model
         */
        public TracingCompleteEvent traceFormat(Tracing.StreamFormat traceFormat) {
            set("traceFormat", traceFormat);
            return this;
        }
        /**
         * Compression format of returned stream.
         * @param streamCompression field value
         * @return this model
         */
        public TracingCompleteEvent streamCompression(Tracing.StreamCompression streamCompression) {
            set("streamCompression", streamCompression);
            return this;
        }
    }
    /**
     * Whether to report trace events as series of dataCollected events or to save trace to a stream (defaults to {@code ReportEvents}).
     */
    public enum StartTransferModeValues implements CdpValue<String> {
        REPORTEVENTS("ReportEvents"),
        RETURNASSTREAM("ReturnAsStream");
        public final String value;
        StartTransferModeValues(String value) { this.value = value; }
        @Override public String value() { return value; }
        public static StartTransferModeValues of(String value) {
            for (StartTransferModeValues constant : values()) {
                if (constant.value.equals(value)) return constant;
            }
            throw new IllegalArgumentException("Unknown StartTransferModeValues value: " + value);
        }
    }
    public static final class Client extends CdpDomainClient {
        public Client(CdpClient client) { super(client); }
        /**
         * Stop trace events collection.
         * @return a stage completing when the command completes
         */
        public CompletionStage<Void> end() {
            return call("Tracing.end");
        }
        /**
         * Gets supported tracing categories.
         * <p><b>Experimental:</b> this part of CDP may change without notice.
         * @return a stage completing with the command result
         */
        public CompletionStage<java.util.List<String>> getCategories() {
            return call("Tracing.getCategories", CATEGORIES::require);
        }
        /**
         * Return a descriptor for all available tracing categories.
         * <p><b>Experimental:</b> this part of CDP may change without notice.
         * @return a stage completing with the command result
         */
        public CompletionStage<String> getTrackEventDescriptor() {
            return call("Tracing.getTrackEventDescriptor", DESCRIPTOR::require);
        }
        /**
         * Record a clock sync marker in the trace.
         * <p><b>Experimental:</b> this part of CDP may change without notice.
         * @param syncId protocol value
         * @return a stage completing when the command completes
         */
        public CompletionStage<Void> recordClockSyncMarker(String syncId) {
            return call("Tracing.recordClockSyncMarker", params("syncId", syncId));
        }
        /**
         * Record a clock sync marker in the trace.
         * <p><b>Experimental:</b> this part of CDP may change without notice.
         * @param request request parameters
         * @return a stage completing when the command completes
         */
        public CompletionStage<Void> recordClockSyncMarker(RecordClockSyncMarkerRequest request) {
            return call("Tracing.recordClockSyncMarker", request);
        }
        /**
         * Request a global memory dump.
         * <p><b>Experimental:</b> this part of CDP may change without notice.
         * @param deterministic protocol value
         * @param levelOfDetail protocol value
         * @return a stage completing with the command result
         */
        public CompletionStage<RequestMemoryDumpResult> requestMemoryDump(@Nullable Boolean deterministic, Tracing.@Nullable MemoryDumpLevelOfDetail levelOfDetail) {
            return call("Tracing.requestMemoryDump", params("deterministic", deterministic, "levelOfDetail", levelOfDetail), RequestMemoryDumpResult::fromMap);
        }
        /**
         * Request a global memory dump.
         * <p><b>Experimental:</b> this part of CDP may change without notice.
         * @return a stage completing with the command result
         */
        public CompletionStage<RequestMemoryDumpResult> requestMemoryDump() {
            return call("Tracing.requestMemoryDump", RequestMemoryDumpResult::fromMap);
        }
        /**
         * Request a global memory dump.
         * <p><b>Experimental:</b> this part of CDP may change without notice.
         * @param request request parameters
         * @return a stage completing with the command result
         */
        public CompletionStage<RequestMemoryDumpResult> requestMemoryDump(RequestMemoryDumpRequest request) {
            return call("Tracing.requestMemoryDump", request, RequestMemoryDumpResult::fromMap);
        }
        /**
         * Start trace events collection.
         * @param categories protocol value
         * @param options protocol value
         * @param bufferUsageReportingInterval protocol value
         * @param transferMode protocol value
         * @param streamFormat protocol value
         * @param streamCompression protocol value
         * @param traceConfig protocol value
         * @param perfettoConfig protocol value
         * @param tracingBackend protocol value
         * @return a stage completing when the command completes
         */
        public CompletionStage<Void> start(@Nullable String categories, @Nullable String options, @Nullable Double bufferUsageReportingInterval, @Nullable StartTransferModeValues transferMode, Tracing.@Nullable StreamFormat streamFormat, Tracing.@Nullable StreamCompression streamCompression, Tracing.@Nullable TraceConfig traceConfig, @Nullable String perfettoConfig, Tracing.@Nullable TracingBackend tracingBackend) {
            return call("Tracing.start", params("categories", categories, "options", options, "bufferUsageReportingInterval", bufferUsageReportingInterval, "transferMode", transferMode, "streamFormat", streamFormat, "streamCompression", streamCompression, "traceConfig", traceConfig, "perfettoConfig", perfettoConfig, "tracingBackend", tracingBackend));
        }
        /**
         * Start trace events collection.
         * @return a stage completing when the command completes
         */
        public CompletionStage<Void> start() {
            return call("Tracing.start");
        }
        /**
         * Start trace events collection.
         * @param request request parameters
         * @return a stage completing when the command completes
         */
        public CompletionStage<Void> start(StartRequest request) {
            return call("Tracing.start", request);
        }
        /**
         * Subscribes to Tracing.bufferUsage.
         * <p><b>Experimental:</b> this part of CDP may change without notice.
         * @param handler event callback
         * @return a removable subscription
         */
        public CdpSubscription onBufferUsage(Consumer<BufferUsageEvent> handler) {
            return on("Tracing.bufferUsage", BufferUsageEvent::fromMap, handler);
        }
        /**
         * Contains a bucket of collected trace events. When tracing is stopped collected events will be sent as a sequence of dataCollected events followed by tracingComplete event.
         * <p><b>Experimental:</b> this part of CDP may change without notice.
         * @param handler event callback
         * @return a removable subscription
         */
        public CdpSubscription onDataCollected(Consumer<DataCollectedEvent> handler) {
            return on("Tracing.dataCollected", DataCollectedEvent::fromMap, handler);
        }
        /**
         * Signals that tracing is stopped and there is no trace buffers pending flush, all data were delivered via dataCollected events.
         * @param handler event callback
         * @return a removable subscription
         */
        public CdpSubscription onTracingComplete(Consumer<TracingCompleteEvent> handler) {
            return on("Tracing.tracingComplete", TracingCompleteEvent::fromMap, handler);
        }
    }
}
