// GENERATED - do not edit. Regenerate via: ./mvnw generate-sources -pl cef4j-cdp -Dcef.version=150.0.18+gdb11278+chromium-150.0.7871.213
package net.kurobako.cef4j.cdp.generated;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Optional;
import java.util.OptionalDouble;
import java.util.concurrent.CompletionStage;
import java.util.function.Consumer;
import javax.annotation.processing.Generated;
import net.kurobako.cef4j.cdp.CdpClient;
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
            return raw("recordMode").map(value -> TraceConfig.RecordModeValues.of((String) value));
        }
        /**
         * Size of the trace buffer in kilobytes. If not specified or zero is passed, a default value of 200 MB would be used.
         * <p><b>Experimental:</b> this part of CDP may change without notice.
         * @return the protocol field value, empty when absent
         */
        public OptionalDouble traceBufferSizeInKb() {
            return raw("traceBufferSizeInKb").flatMap(CdpObject::numberAsDouble).stream().mapToDouble(Double::doubleValue).findFirst();
        }
        /**
         * Turns on JavaScript stack sampling.
         * <p><b>Experimental:</b> this part of CDP may change without notice.
         * @return the protocol field value, empty when absent
         */
        public Optional<Boolean> enableSampling() {
            return raw("enableSampling").map(value -> (Boolean) value);
        }
        /**
         * Turns on system tracing.
         * <p><b>Experimental:</b> this part of CDP may change without notice.
         * @return the protocol field value, empty when absent
         */
        public Optional<Boolean> enableSystrace() {
            return raw("enableSystrace").map(value -> (Boolean) value);
        }
        /**
         * Turns on argument filter.
         * <p><b>Experimental:</b> this part of CDP may change without notice.
         * @return the protocol field value, empty when absent
         */
        public Optional<Boolean> enableArgumentFilter() {
            return raw("enableArgumentFilter").map(value -> (Boolean) value);
        }
        /**
         * Included category filters.
         * @return the protocol field value, empty when absent
         */
        public Optional<java.util.List<String>> includedCategories() {
            return raw("includedCategories").flatMap(value -> list(value, element0 -> (String) element0));
        }
        /**
         * Excluded category filters.
         * @return the protocol field value, empty when absent
         */
        public Optional<java.util.List<String>> excludedCategories() {
            return raw("excludedCategories").flatMap(value -> list(value, element0 -> (String) element0));
        }
        /**
         * Configuration to synthesize the delays in tracing.
         * <p><b>Experimental:</b> this part of CDP may change without notice.
         * @return the protocol field value, empty when absent
         */
        public Optional<java.util.List<String>> syntheticDelays() {
            return raw("syntheticDelays").flatMap(value -> list(value, element0 -> (String) element0));
        }
        /**
         * Configuration for memory dump triggers. Used only when &quot;memory-infra&quot; category is enabled.
         * <p><b>Experimental:</b> this part of CDP may change without notice.
         * @return the protocol field value, empty when absent
         */
        public Optional<java.util.Map<String, Object>> memoryDumpConfig() {
            return raw("memoryDumpConfig").flatMap(CdpObject::objectMap);
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
        /**
         * Record a clock sync marker in the trace.
         * <p><b>Experimental:</b> this part of CDP may change without notice.
         * @param syncId protocol value
         */
        public RecordClockSyncMarkerRequest(String syncId) {
            syncId(syncId);
        }
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
            return (String) require("syncId");
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
            return raw("deterministic").map(value -> (Boolean) value);
        }
        /**
         * Specifies level of details in memory dump. Defaults to &quot;detailed&quot;.
         * @return the protocol field value, empty when absent
         */
        public Optional<Tracing.MemoryDumpLevelOfDetail> levelOfDetail() {
            return raw("levelOfDetail").map(value -> Tracing.MemoryDumpLevelOfDetail.of((String) value));
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
            return raw("categories").map(value -> (String) value);
        }
        /**
         * Tracing options
         * <p><b>Experimental:</b> this part of CDP may change without notice.
         * @return the protocol field value, empty when absent
         * @deprecated Deprecated by the Chromium DevTools Protocol.
         */
        @Deprecated
        public Optional<String> options() {
            return raw("options").map(value -> (String) value);
        }
        /**
         * If set, the agent will issue bufferUsage events at this interval, specified in milliseconds
         * <p><b>Experimental:</b> this part of CDP may change without notice.
         * @return the protocol field value, empty when absent
         */
        public OptionalDouble bufferUsageReportingInterval() {
            return raw("bufferUsageReportingInterval").flatMap(CdpObject::numberAsDouble).stream().mapToDouble(Double::doubleValue).findFirst();
        }
        /**
         * Whether to report trace events as series of dataCollected events or to save trace to a stream (defaults to {@code ReportEvents}).
         * @return the protocol field value, empty when absent
         */
        public Optional<StartTransferModeValues> transferMode() {
            return raw("transferMode").map(value -> StartTransferModeValues.of((String) value));
        }
        /**
         * Trace data format to use. This only applies when using {@code ReturnAsStream} transfer mode (defaults to {@code json}).
         * @return the protocol field value, empty when absent
         */
        public Optional<Tracing.StreamFormat> streamFormat() {
            return raw("streamFormat").map(value -> Tracing.StreamFormat.of((String) value));
        }
        /**
         * Compression format to use. This only applies when using {@code ReturnAsStream} transfer mode (defaults to {@code none})
         * <p><b>Experimental:</b> this part of CDP may change without notice.
         * @return the protocol field value, empty when absent
         */
        public Optional<Tracing.StreamCompression> streamCompression() {
            return raw("streamCompression").map(value -> Tracing.StreamCompression.of((String) value));
        }
        /**
         * Returns the traceConfig field.
         * @return the protocol field value, empty when absent
         */
        public Optional<Tracing.TraceConfig> traceConfig() {
            return raw("traceConfig").flatMap(CdpObject::objectMap).map(Tracing.TraceConfig::fromMap);
        }
        /**
         * Base64-encoded serialized perfetto.protos.TraceConfig protobuf message When specified, the parameters {@code categories}, {@code options}, {@code traceConfig} are ignored. (Encoded as a base64 string when passed over JSON)
         * <p><b>Experimental:</b> this part of CDP may change without notice.
         * @return the protocol field value, empty when absent
         */
        public Optional<String> perfettoConfig() {
            return raw("perfettoConfig").map(value -> (String) value);
        }
        /**
         * Backend type (defaults to {@code auto})
         * <p><b>Experimental:</b> this part of CDP may change without notice.
         * @return the protocol field value, empty when absent
         */
        public Optional<Tracing.TracingBackend> tracingBackend() {
            return raw("tracingBackend").map(value -> Tracing.TracingBackend.of((String) value));
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
            return (String) require("dumpGuid");
        }
        /**
         * True iff the global memory dump succeeded.
         * @return the protocol field value
         */
        public boolean success() {
            return (Boolean) require("success");
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
            return raw("percentFull").flatMap(CdpObject::numberAsDouble).stream().mapToDouble(Double::doubleValue).findFirst();
        }
        /**
         * An approximate number of events in the trace log.
         * @return the protocol field value, empty when absent
         */
        public OptionalDouble eventCount() {
            return raw("eventCount").flatMap(CdpObject::numberAsDouble).stream().mapToDouble(Double::doubleValue).findFirst();
        }
        /**
         * A number in range [0..1] that indicates the used size of event buffer as a fraction of its total size.
         * @return the protocol field value, empty when absent
         */
        public OptionalDouble value() {
            return raw("value").flatMap(CdpObject::numberAsDouble).stream().mapToDouble(Double::doubleValue).findFirst();
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
            return CdpObject.requireList(require("value"), element0 -> CdpObject.requireObjectMap(element0));
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
            return (Boolean) require("dataLossOccurred");
        }
        /**
         * A handle of the stream that holds resulting trace data.
         * @return the protocol field value, empty when absent
         */
        public Optional<IO.StreamHandle> stream() {
            return raw("stream").map(value -> new IO.StreamHandle((String) value));
        }
        /**
         * Trace data format of returned stream.
         * @return the protocol field value, empty when absent
         */
        public Optional<Tracing.StreamFormat> traceFormat() {
            return raw("traceFormat").map(value -> Tracing.StreamFormat.of((String) value));
        }
        /**
         * Compression format of returned stream.
         * @return the protocol field value, empty when absent
         */
        public Optional<Tracing.StreamCompression> streamCompression() {
            return raw("streamCompression").map(value -> Tracing.StreamCompression.of((String) value));
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
    public static final class Client {
        private final CdpClient client;
        public Client(CdpClient client) { this.client = client; }
        /**
         * Stop trace events collection.
         * @return a stage completing when the command completes
         */
        public CompletionStage<Void> end() {
            return client.call("Tracing.end", null);
        }
        /**
         * Gets supported tracing categories.
         * <p><b>Experimental:</b> this part of CDP may change without notice.
         * @return a stage completing with the command result
         */
        public CompletionStage<java.util.List<String>> getCategories() {
            return client.call("Tracing.getCategories", null, result_ -> CdpObject.requireList(java.util.Objects.requireNonNull(result_.get("categories")), element0 -> (String) element0));
        }
        /**
         * Return a descriptor for all available tracing categories.
         * <p><b>Experimental:</b> this part of CDP may change without notice.
         * @return a stage completing with the command result
         */
        public CompletionStage<String> getTrackEventDescriptor() {
            return client.call("Tracing.getTrackEventDescriptor", null, result_ -> (String) java.util.Objects.requireNonNull(result_.get("descriptor")));
        }
        /**
         * Record a clock sync marker in the trace.
         * <p><b>Experimental:</b> this part of CDP may change without notice.
         * @param syncId protocol value
         * @return a stage completing when the command completes
         */
        public CompletionStage<Void> recordClockSyncMarker(String syncId) {
            final Map<String, Object> params = new LinkedHashMap<>();
            params.put("syncId", CdpObject.json(syncId));
            return client.call("Tracing.recordClockSyncMarker", params);
        }
        /**
         * Record a clock sync marker in the trace.
         * <p><b>Experimental:</b> this part of CDP may change without notice.
         * @param request request parameters
         * @return a stage completing when the command completes
         */
        public CompletionStage<Void> recordClockSyncMarker(RecordClockSyncMarkerRequest request) {
            return client.call("Tracing.recordClockSyncMarker", request == null ? null : request.toMap());
        }
        /**
         * Request a global memory dump.
         * <p><b>Experimental:</b> this part of CDP may change without notice.
         * @param deterministic protocol value
         * @param levelOfDetail protocol value
         * @return a stage completing with the command result
         */
        public CompletionStage<RequestMemoryDumpResult> requestMemoryDump(@Nullable Boolean deterministic, Tracing.@Nullable MemoryDumpLevelOfDetail levelOfDetail) {
            final Map<String, Object> params = new LinkedHashMap<>();
            if (deterministic != null) params.put("deterministic", CdpObject.json(deterministic));
            if (levelOfDetail != null) params.put("levelOfDetail", CdpObject.json(levelOfDetail));
            return client.call("Tracing.requestMemoryDump", params, result_ -> new RequestMemoryDumpResult(result_));
        }
        /**
         * Request a global memory dump.
         * <p><b>Experimental:</b> this part of CDP may change without notice.
         * @return a stage completing with the command result
         */
        public CompletionStage<RequestMemoryDumpResult> requestMemoryDump() {
            return client.call("Tracing.requestMemoryDump", null, result_ -> new RequestMemoryDumpResult(result_));
        }
        /**
         * Request a global memory dump.
         * <p><b>Experimental:</b> this part of CDP may change without notice.
         * @param request request parameters
         * @return a stage completing with the command result
         */
        public CompletionStage<RequestMemoryDumpResult> requestMemoryDump(RequestMemoryDumpRequest request) {
            return client.call("Tracing.requestMemoryDump", request == null ? null : request.toMap(), result_ -> new RequestMemoryDumpResult(result_));
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
            final Map<String, Object> params = new LinkedHashMap<>();
            if (categories != null) params.put("categories", CdpObject.json(categories));
            if (options != null) params.put("options", CdpObject.json(options));
            if (bufferUsageReportingInterval != null) params.put("bufferUsageReportingInterval", CdpObject.json(bufferUsageReportingInterval));
            if (transferMode != null) params.put("transferMode", CdpObject.json(transferMode));
            if (streamFormat != null) params.put("streamFormat", CdpObject.json(streamFormat));
            if (streamCompression != null) params.put("streamCompression", CdpObject.json(streamCompression));
            if (traceConfig != null) params.put("traceConfig", CdpObject.json(traceConfig));
            if (perfettoConfig != null) params.put("perfettoConfig", CdpObject.json(perfettoConfig));
            if (tracingBackend != null) params.put("tracingBackend", CdpObject.json(tracingBackend));
            return client.call("Tracing.start", params);
        }
        /**
         * Start trace events collection.
         * @return a stage completing when the command completes
         */
        public CompletionStage<Void> start() {
            return client.call("Tracing.start", null);
        }
        /**
         * Start trace events collection.
         * @param request request parameters
         * @return a stage completing when the command completes
         */
        public CompletionStage<Void> start(StartRequest request) {
            return client.call("Tracing.start", request == null ? null : request.toMap());
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
