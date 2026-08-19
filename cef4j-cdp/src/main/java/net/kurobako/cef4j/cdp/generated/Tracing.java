// GENERATED - do not edit. Regenerate via: mvn generate-sources -pl cef4j-platform -Dcef.version=150.0.18+gdb11278+chromium-150.0.7871.213
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
 * Chrome DevTools Protocol Tracing domain.
 * @see <a href="https://chromium.googlesource.com/chromium/src/+/refs/tags/150.0.7871.213/third_party/blink/public/devtools_protocol/domains/Tracing.pdl">Pinned protocol source</a>
 */
@Generated("mvn generate-sources -pl cef4j-platform -Dcef.version=150.0.18+gdb11278+chromium-150.0.7871.213")
@SuppressWarnings({"EscapedEntity", "InvalidParam", "JavaLangClash", "MissingSummary", "Unchecked", "UnusedMethod"})
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
            @Nonnull public String value() { return value; }
            public static RecordModeValues of(@Nonnull String value) {
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
            return Optional.ofNullable(raw("recordMode") == null ? null : TraceConfig.RecordModeValues.of((String) raw("recordMode")));
        }
        /**
         * Size of the trace buffer in kilobytes. If not specified or zero is passed, a default value of 200 MB would be used.
         * <p><b>Experimental:</b> this part of CDP may change without notice.
         * @return the protocol field value, empty when absent
         */
        public OptionalDouble traceBufferSizeInKb() {
            Double value = CdpObject.numberAsDouble(raw("traceBufferSizeInKb"));
            return value == null ? OptionalDouble.empty() : OptionalDouble.of(value);
        }
        /**
         * Turns on JavaScript stack sampling.
         * <p><b>Experimental:</b> this part of CDP may change without notice.
         * @return the protocol field value, empty when absent
         */
        public Optional<Boolean> enableSampling() {
            return Optional.ofNullable((Boolean) raw("enableSampling"));
        }
        /**
         * Turns on system tracing.
         * <p><b>Experimental:</b> this part of CDP may change without notice.
         * @return the protocol field value, empty when absent
         */
        public Optional<Boolean> enableSystrace() {
            return Optional.ofNullable((Boolean) raw("enableSystrace"));
        }
        /**
         * Turns on argument filter.
         * <p><b>Experimental:</b> this part of CDP may change without notice.
         * @return the protocol field value, empty when absent
         */
        public Optional<Boolean> enableArgumentFilter() {
            return Optional.ofNullable((Boolean) raw("enableArgumentFilter"));
        }
        /**
         * Included category filters.
         * @return the protocol field value, empty when absent
         */
        public Optional<java.util.List<String>> includedCategories() {
            return Optional.ofNullable(list(raw("includedCategories"), element0 -> (String) element0));
        }
        /**
         * Excluded category filters.
         * @return the protocol field value, empty when absent
         */
        public Optional<java.util.List<String>> excludedCategories() {
            return Optional.ofNullable(list(raw("excludedCategories"), element0 -> (String) element0));
        }
        /**
         * Configuration to synthesize the delays in tracing.
         * <p><b>Experimental:</b> this part of CDP may change without notice.
         * @return the protocol field value, empty when absent
         */
        public Optional<java.util.List<String>> syntheticDelays() {
            return Optional.ofNullable(list(raw("syntheticDelays"), element0 -> (String) element0));
        }
        /**
         * Configuration for memory dump triggers. Used only when &quot;memory-infra&quot; category is enabled.
         * <p><b>Experimental:</b> this part of CDP may change without notice.
         * @return the protocol field value, empty when absent
         */
        public Optional<java.util.Map<String, Object>> memoryDumpConfig() {
            return Optional.ofNullable(objectMap(raw("memoryDumpConfig")));
        }
        /**
         * Controls how the trace buffer stores data. The default is {@code recordUntilFull}.
         * <p><b>Experimental:</b> this part of CDP may change without notice.
         * @param recordMode field value; empty omits the value
         * @return this model
         */
        public TraceConfig recordMode(Optional<TraceConfig.RecordModeValues> recordMode) {
            set("recordMode", recordMode.orElse(null));
            return this;
        }
        /**
         * Controls how the trace buffer stores data. The default is {@code recordUntilFull}.
         * <p><b>Experimental:</b> this part of CDP may change without notice.
         * @param recordMode field value; null removes the value
         * @return this model
         */
        public TraceConfig recordMode(TraceConfig.RecordModeValues recordMode) {
            set("recordMode", recordMode);
            return this;
        }
        /**
         * Size of the trace buffer in kilobytes. If not specified or zero is passed, a default value of 200 MB would be used.
         * <p><b>Experimental:</b> this part of CDP may change without notice.
         * @param traceBufferSizeInKb field value; empty omits the value
         * @return this model
         */
        public TraceConfig traceBufferSizeInKb(OptionalDouble traceBufferSizeInKb) {
            set("traceBufferSizeInKb", traceBufferSizeInKb.isPresent() ? traceBufferSizeInKb.getAsDouble() : null);
            return this;
        }
        /**
         * Size of the trace buffer in kilobytes. If not specified or zero is passed, a default value of 200 MB would be used.
         * <p><b>Experimental:</b> this part of CDP may change without notice.
         * @param traceBufferSizeInKb field value; null removes the value
         * @return this model
         */
        public TraceConfig traceBufferSizeInKb(Double traceBufferSizeInKb) {
            set("traceBufferSizeInKb", traceBufferSizeInKb);
            return this;
        }
        /**
         * Turns on JavaScript stack sampling.
         * <p><b>Experimental:</b> this part of CDP may change without notice.
         * @param enableSampling field value; empty omits the value
         * @return this model
         */
        public TraceConfig enableSampling(Optional<Boolean> enableSampling) {
            set("enableSampling", enableSampling.orElse(null));
            return this;
        }
        /**
         * Turns on JavaScript stack sampling.
         * <p><b>Experimental:</b> this part of CDP may change without notice.
         * @param enableSampling field value; null removes the value
         * @return this model
         */
        public TraceConfig enableSampling(Boolean enableSampling) {
            set("enableSampling", enableSampling);
            return this;
        }
        /**
         * Turns on system tracing.
         * <p><b>Experimental:</b> this part of CDP may change without notice.
         * @param enableSystrace field value; empty omits the value
         * @return this model
         */
        public TraceConfig enableSystrace(Optional<Boolean> enableSystrace) {
            set("enableSystrace", enableSystrace.orElse(null));
            return this;
        }
        /**
         * Turns on system tracing.
         * <p><b>Experimental:</b> this part of CDP may change without notice.
         * @param enableSystrace field value; null removes the value
         * @return this model
         */
        public TraceConfig enableSystrace(Boolean enableSystrace) {
            set("enableSystrace", enableSystrace);
            return this;
        }
        /**
         * Turns on argument filter.
         * <p><b>Experimental:</b> this part of CDP may change without notice.
         * @param enableArgumentFilter field value; empty omits the value
         * @return this model
         */
        public TraceConfig enableArgumentFilter(Optional<Boolean> enableArgumentFilter) {
            set("enableArgumentFilter", enableArgumentFilter.orElse(null));
            return this;
        }
        /**
         * Turns on argument filter.
         * <p><b>Experimental:</b> this part of CDP may change without notice.
         * @param enableArgumentFilter field value; null removes the value
         * @return this model
         */
        public TraceConfig enableArgumentFilter(Boolean enableArgumentFilter) {
            set("enableArgumentFilter", enableArgumentFilter);
            return this;
        }
        /**
         * Included category filters.
         * @param includedCategories field value; empty omits the value
         * @return this model
         */
        public TraceConfig includedCategories(Optional<java.util.List<String>> includedCategories) {
            set("includedCategories", includedCategories.orElse(null));
            return this;
        }
        /**
         * Included category filters.
         * @param includedCategories field value; null removes the value
         * @return this model
         */
        public TraceConfig includedCategories(java.util.List<String> includedCategories) {
            set("includedCategories", includedCategories);
            return this;
        }
        /**
         * Excluded category filters.
         * @param excludedCategories field value; empty omits the value
         * @return this model
         */
        public TraceConfig excludedCategories(Optional<java.util.List<String>> excludedCategories) {
            set("excludedCategories", excludedCategories.orElse(null));
            return this;
        }
        /**
         * Excluded category filters.
         * @param excludedCategories field value; null removes the value
         * @return this model
         */
        public TraceConfig excludedCategories(java.util.List<String> excludedCategories) {
            set("excludedCategories", excludedCategories);
            return this;
        }
        /**
         * Configuration to synthesize the delays in tracing.
         * <p><b>Experimental:</b> this part of CDP may change without notice.
         * @param syntheticDelays field value; empty omits the value
         * @return this model
         */
        public TraceConfig syntheticDelays(Optional<java.util.List<String>> syntheticDelays) {
            set("syntheticDelays", syntheticDelays.orElse(null));
            return this;
        }
        /**
         * Configuration to synthesize the delays in tracing.
         * <p><b>Experimental:</b> this part of CDP may change without notice.
         * @param syntheticDelays field value; null removes the value
         * @return this model
         */
        public TraceConfig syntheticDelays(java.util.List<String> syntheticDelays) {
            set("syntheticDelays", syntheticDelays);
            return this;
        }
        /**
         * Configuration for memory dump triggers. Used only when &quot;memory-infra&quot; category is enabled.
         * <p><b>Experimental:</b> this part of CDP may change without notice.
         * @param memoryDumpConfig field value; empty omits the value
         * @return this model
         */
        public TraceConfig memoryDumpConfig(Optional<java.util.Map<String, Object>> memoryDumpConfig) {
            set("memoryDumpConfig", memoryDumpConfig.orElse(null));
            return this;
        }
        /**
         * Configuration for memory dump triggers. Used only when &quot;memory-infra&quot; category is enabled.
         * <p><b>Experimental:</b> this part of CDP may change without notice.
         * @param memoryDumpConfig field value; null removes the value
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
        @Nonnull public String value() { return value; }
        public static StreamFormat of(@Nonnull String value) {
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
        @Nonnull public String value() { return value; }
        public static StreamCompression of(@Nonnull String value) {
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
        @Nonnull public String value() { return value; }
        public static MemoryDumpLevelOfDetail of(@Nonnull String value) {
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
        @Nonnull public String value() { return value; }
        public static TracingBackend of(@Nonnull String value) {
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
            set("syncId", syncId);
        }
        public static RecordClockSyncMarkerRequest fromMap(Map<String, Object> values) {
            RecordClockSyncMarkerRequest instance_ = new RecordClockSyncMarkerRequest();
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
            RequestMemoryDumpRequest instance_ = new RequestMemoryDumpRequest();
            if (values != null) instance_.values.putAll(values);
            return instance_;
        }
        /**
         * Enables more deterministic results by forcing garbage collection
         * @return the protocol field value, empty when absent
         */
        public Optional<Boolean> deterministic() {
            return Optional.ofNullable((Boolean) raw("deterministic"));
        }
        /**
         * Specifies level of details in memory dump. Defaults to &quot;detailed&quot;.
         * @return the protocol field value, empty when absent
         */
        public Optional<Tracing.MemoryDumpLevelOfDetail> levelOfDetail() {
            return Optional.ofNullable(raw("levelOfDetail") == null ? null : Tracing.MemoryDumpLevelOfDetail.of((String) raw("levelOfDetail")));
        }
        /**
         * Enables more deterministic results by forcing garbage collection
         * @param deterministic field value; empty omits the value
         * @return this model
         */
        public RequestMemoryDumpRequest deterministic(Optional<Boolean> deterministic) {
            set("deterministic", deterministic.orElse(null));
            return this;
        }
        /**
         * Enables more deterministic results by forcing garbage collection
         * @param deterministic field value; null removes the value
         * @return this model
         */
        public RequestMemoryDumpRequest deterministic(Boolean deterministic) {
            set("deterministic", deterministic);
            return this;
        }
        /**
         * Specifies level of details in memory dump. Defaults to &quot;detailed&quot;.
         * @param levelOfDetail field value; empty omits the value
         * @return this model
         */
        public RequestMemoryDumpRequest levelOfDetail(Optional<Tracing.MemoryDumpLevelOfDetail> levelOfDetail) {
            set("levelOfDetail", levelOfDetail.orElse(null));
            return this;
        }
        /**
         * Specifies level of details in memory dump. Defaults to &quot;detailed&quot;.
         * @param levelOfDetail field value; null removes the value
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
            StartRequest instance_ = new StartRequest();
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
            return Optional.ofNullable((String) raw("categories"));
        }
        /**
         * Tracing options
         * <p><b>Experimental:</b> this part of CDP may change without notice.
         * @return the protocol field value, empty when absent
         * @deprecated Deprecated by the Chromium DevTools Protocol.
         */
        @Deprecated
        public Optional<String> options() {
            return Optional.ofNullable((String) raw("options"));
        }
        /**
         * If set, the agent will issue bufferUsage events at this interval, specified in milliseconds
         * <p><b>Experimental:</b> this part of CDP may change without notice.
         * @return the protocol field value, empty when absent
         */
        public OptionalDouble bufferUsageReportingInterval() {
            Double value = CdpObject.numberAsDouble(raw("bufferUsageReportingInterval"));
            return value == null ? OptionalDouble.empty() : OptionalDouble.of(value);
        }
        /**
         * Whether to report trace events as series of dataCollected events or to save trace to a stream (defaults to {@code ReportEvents}).
         * @return the protocol field value, empty when absent
         */
        public Optional<StartTransferModeValues> transferMode() {
            return Optional.ofNullable(raw("transferMode") == null ? null : StartTransferModeValues.of((String) raw("transferMode")));
        }
        /**
         * Trace data format to use. This only applies when using {@code ReturnAsStream} transfer mode (defaults to {@code json}).
         * @return the protocol field value, empty when absent
         */
        public Optional<Tracing.StreamFormat> streamFormat() {
            return Optional.ofNullable(raw("streamFormat") == null ? null : Tracing.StreamFormat.of((String) raw("streamFormat")));
        }
        /**
         * Compression format to use. This only applies when using {@code ReturnAsStream} transfer mode (defaults to {@code none})
         * <p><b>Experimental:</b> this part of CDP may change without notice.
         * @return the protocol field value, empty when absent
         */
        public Optional<Tracing.StreamCompression> streamCompression() {
            return Optional.ofNullable(raw("streamCompression") == null ? null : Tracing.StreamCompression.of((String) raw("streamCompression")));
        }
        /**
         * Returns the traceConfig field.
         * @return the protocol field value, empty when absent
         */
        public Optional<Tracing.TraceConfig> traceConfig() {
            return Optional.ofNullable(raw("traceConfig") == null ? null : Tracing.TraceConfig.fromMap(java.util.Objects.requireNonNull(objectMap(raw("traceConfig")))));
        }
        /**
         * Base64-encoded serialized perfetto.protos.TraceConfig protobuf message When specified, the parameters {@code categories}, {@code options}, {@code traceConfig} are ignored. (Encoded as a base64 string when passed over JSON)
         * <p><b>Experimental:</b> this part of CDP may change without notice.
         * @return the protocol field value, empty when absent
         */
        public Optional<String> perfettoConfig() {
            return Optional.ofNullable((String) raw("perfettoConfig"));
        }
        /**
         * Backend type (defaults to {@code auto})
         * <p><b>Experimental:</b> this part of CDP may change without notice.
         * @return the protocol field value, empty when absent
         */
        public Optional<Tracing.TracingBackend> tracingBackend() {
            return Optional.ofNullable(raw("tracingBackend") == null ? null : Tracing.TracingBackend.of((String) raw("tracingBackend")));
        }
        /**
         * Category/tag filter
         * <p><b>Experimental:</b> this part of CDP may change without notice.
         * @param categories field value; empty omits the value
         * @return this model
         * @deprecated Deprecated by the Chromium DevTools Protocol.
         */
        @Deprecated
        public StartRequest categories(Optional<String> categories) {
            set("categories", categories.orElse(null));
            return this;
        }
        /**
         * Category/tag filter
         * <p><b>Experimental:</b> this part of CDP may change without notice.
         * @param categories field value; null removes the value
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
         * @param options field value; empty omits the value
         * @return this model
         * @deprecated Deprecated by the Chromium DevTools Protocol.
         */
        @Deprecated
        public StartRequest options(Optional<String> options) {
            set("options", options.orElse(null));
            return this;
        }
        /**
         * Tracing options
         * <p><b>Experimental:</b> this part of CDP may change without notice.
         * @param options field value; null removes the value
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
         * @param bufferUsageReportingInterval field value; empty omits the value
         * @return this model
         */
        public StartRequest bufferUsageReportingInterval(OptionalDouble bufferUsageReportingInterval) {
            set("bufferUsageReportingInterval", bufferUsageReportingInterval.isPresent() ? bufferUsageReportingInterval.getAsDouble() : null);
            return this;
        }
        /**
         * If set, the agent will issue bufferUsage events at this interval, specified in milliseconds
         * <p><b>Experimental:</b> this part of CDP may change without notice.
         * @param bufferUsageReportingInterval field value; null removes the value
         * @return this model
         */
        public StartRequest bufferUsageReportingInterval(Double bufferUsageReportingInterval) {
            set("bufferUsageReportingInterval", bufferUsageReportingInterval);
            return this;
        }
        /**
         * Whether to report trace events as series of dataCollected events or to save trace to a stream (defaults to {@code ReportEvents}).
         * @param transferMode field value; empty omits the value
         * @return this model
         */
        public StartRequest transferMode(Optional<StartTransferModeValues> transferMode) {
            set("transferMode", transferMode.orElse(null));
            return this;
        }
        /**
         * Whether to report trace events as series of dataCollected events or to save trace to a stream (defaults to {@code ReportEvents}).
         * @param transferMode field value; null removes the value
         * @return this model
         */
        public StartRequest transferMode(StartTransferModeValues transferMode) {
            set("transferMode", transferMode);
            return this;
        }
        /**
         * Trace data format to use. This only applies when using {@code ReturnAsStream} transfer mode (defaults to {@code json}).
         * @param streamFormat field value; empty omits the value
         * @return this model
         */
        public StartRequest streamFormat(Optional<Tracing.StreamFormat> streamFormat) {
            set("streamFormat", streamFormat.orElse(null));
            return this;
        }
        /**
         * Trace data format to use. This only applies when using {@code ReturnAsStream} transfer mode (defaults to {@code json}).
         * @param streamFormat field value; null removes the value
         * @return this model
         */
        public StartRequest streamFormat(Tracing.StreamFormat streamFormat) {
            set("streamFormat", streamFormat);
            return this;
        }
        /**
         * Compression format to use. This only applies when using {@code ReturnAsStream} transfer mode (defaults to {@code none})
         * <p><b>Experimental:</b> this part of CDP may change without notice.
         * @param streamCompression field value; empty omits the value
         * @return this model
         */
        public StartRequest streamCompression(Optional<Tracing.StreamCompression> streamCompression) {
            set("streamCompression", streamCompression.orElse(null));
            return this;
        }
        /**
         * Compression format to use. This only applies when using {@code ReturnAsStream} transfer mode (defaults to {@code none})
         * <p><b>Experimental:</b> this part of CDP may change without notice.
         * @param streamCompression field value; null removes the value
         * @return this model
         */
        public StartRequest streamCompression(Tracing.StreamCompression streamCompression) {
            set("streamCompression", streamCompression);
            return this;
        }
        /**
         * Sets the traceConfig field.
         * @param traceConfig field value; empty omits the value
         * @return this model
         */
        public StartRequest traceConfig(Optional<Tracing.TraceConfig> traceConfig) {
            set("traceConfig", traceConfig.orElse(null));
            return this;
        }
        /**
         * Sets the traceConfig field.
         * @param traceConfig field value; null removes the value
         * @return this model
         */
        public StartRequest traceConfig(Tracing.TraceConfig traceConfig) {
            set("traceConfig", traceConfig);
            return this;
        }
        /**
         * Base64-encoded serialized perfetto.protos.TraceConfig protobuf message When specified, the parameters {@code categories}, {@code options}, {@code traceConfig} are ignored. (Encoded as a base64 string when passed over JSON)
         * <p><b>Experimental:</b> this part of CDP may change without notice.
         * @param perfettoConfig field value; empty omits the value
         * @return this model
         */
        public StartRequest perfettoConfig(Optional<String> perfettoConfig) {
            set("perfettoConfig", perfettoConfig.orElse(null));
            return this;
        }
        /**
         * Base64-encoded serialized perfetto.protos.TraceConfig protobuf message When specified, the parameters {@code categories}, {@code options}, {@code traceConfig} are ignored. (Encoded as a base64 string when passed over JSON)
         * <p><b>Experimental:</b> this part of CDP may change without notice.
         * @param perfettoConfig field value; null removes the value
         * @return this model
         */
        public StartRequest perfettoConfig(String perfettoConfig) {
            set("perfettoConfig", perfettoConfig);
            return this;
        }
        /**
         * Backend type (defaults to {@code auto})
         * <p><b>Experimental:</b> this part of CDP may change without notice.
         * @param tracingBackend field value; empty omits the value
         * @return this model
         */
        public StartRequest tracingBackend(Optional<Tracing.TracingBackend> tracingBackend) {
            set("tracingBackend", tracingBackend.orElse(null));
            return this;
        }
        /**
         * Backend type (defaults to {@code auto})
         * <p><b>Experimental:</b> this part of CDP may change without notice.
         * @param tracingBackend field value; null removes the value
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
            Double value = CdpObject.numberAsDouble(raw("percentFull"));
            return value == null ? OptionalDouble.empty() : OptionalDouble.of(value);
        }
        /**
         * An approximate number of events in the trace log.
         * @return the protocol field value, empty when absent
         */
        public OptionalDouble eventCount() {
            Double value = CdpObject.numberAsDouble(raw("eventCount"));
            return value == null ? OptionalDouble.empty() : OptionalDouble.of(value);
        }
        /**
         * A number in range [0..1] that indicates the used size of event buffer as a fraction of its total size.
         * @return the protocol field value, empty when absent
         */
        public OptionalDouble value() {
            Double value = CdpObject.numberAsDouble(raw("value"));
            return value == null ? OptionalDouble.empty() : OptionalDouble.of(value);
        }
        /**
         * A number in range [0..1] that indicates the used size of event buffer as a fraction of its total size.
         * @param percentFull field value; empty omits the value
         * @return this model
         */
        public BufferUsageEvent percentFull(OptionalDouble percentFull) {
            set("percentFull", percentFull.isPresent() ? percentFull.getAsDouble() : null);
            return this;
        }
        /**
         * A number in range [0..1] that indicates the used size of event buffer as a fraction of its total size.
         * @param percentFull field value; null removes the value
         * @return this model
         */
        public BufferUsageEvent percentFull(Double percentFull) {
            set("percentFull", percentFull);
            return this;
        }
        /**
         * An approximate number of events in the trace log.
         * @param eventCount field value; empty omits the value
         * @return this model
         */
        public BufferUsageEvent eventCount(OptionalDouble eventCount) {
            set("eventCount", eventCount.isPresent() ? eventCount.getAsDouble() : null);
            return this;
        }
        /**
         * An approximate number of events in the trace log.
         * @param eventCount field value; null removes the value
         * @return this model
         */
        public BufferUsageEvent eventCount(Double eventCount) {
            set("eventCount", eventCount);
            return this;
        }
        /**
         * A number in range [0..1] that indicates the used size of event buffer as a fraction of its total size.
         * @param value field value; empty omits the value
         * @return this model
         */
        public BufferUsageEvent value(OptionalDouble value) {
            set("value", value.isPresent() ? value.getAsDouble() : null);
            return this;
        }
        /**
         * A number in range [0..1] that indicates the used size of event buffer as a fraction of its total size.
         * @param value field value; null removes the value
         * @return this model
         */
        public BufferUsageEvent value(Double value) {
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
            return CdpObject.requireList(require("value"), element0 -> java.util.Objects.requireNonNull(CdpObject.objectMap(element0)));
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
            return Optional.ofNullable(raw("stream") == null ? null : new IO.StreamHandle((String) raw("stream")));
        }
        /**
         * Trace data format of returned stream.
         * @return the protocol field value, empty when absent
         */
        public Optional<Tracing.StreamFormat> traceFormat() {
            return Optional.ofNullable(raw("traceFormat") == null ? null : Tracing.StreamFormat.of((String) raw("traceFormat")));
        }
        /**
         * Compression format of returned stream.
         * @return the protocol field value, empty when absent
         */
        public Optional<Tracing.StreamCompression> streamCompression() {
            return Optional.ofNullable(raw("streamCompression") == null ? null : Tracing.StreamCompression.of((String) raw("streamCompression")));
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
         * @param stream field value; empty omits the value
         * @return this model
         */
        public TracingCompleteEvent stream(Optional<IO.StreamHandle> stream) {
            set("stream", stream.orElse(null));
            return this;
        }
        /**
         * A handle of the stream that holds resulting trace data.
         * @param stream field value; null removes the value
         * @return this model
         */
        public TracingCompleteEvent stream(IO.StreamHandle stream) {
            set("stream", stream);
            return this;
        }
        /**
         * Trace data format of returned stream.
         * @param traceFormat field value; empty omits the value
         * @return this model
         */
        public TracingCompleteEvent traceFormat(Optional<Tracing.StreamFormat> traceFormat) {
            set("traceFormat", traceFormat.orElse(null));
            return this;
        }
        /**
         * Trace data format of returned stream.
         * @param traceFormat field value; null removes the value
         * @return this model
         */
        public TracingCompleteEvent traceFormat(Tracing.StreamFormat traceFormat) {
            set("traceFormat", traceFormat);
            return this;
        }
        /**
         * Compression format of returned stream.
         * @param streamCompression field value; empty omits the value
         * @return this model
         */
        public TracingCompleteEvent streamCompression(Optional<Tracing.StreamCompression> streamCompression) {
            set("streamCompression", streamCompression.orElse(null));
            return this;
        }
        /**
         * Compression format of returned stream.
         * @param streamCompression field value; null removes the value
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
        @Nonnull public String value() { return value; }
        public static StartTransferModeValues of(@Nonnull String value) {
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
            return client.call("Tracing.end", null, result_ -> null);
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
            Map<String, Object> params = new LinkedHashMap<>();
            params.put("syncId", CdpObject.json(syncId));
            return client.call("Tracing.recordClockSyncMarker", params, result_ -> null);
        }
        /**
         * Record a clock sync marker in the trace.
         * <p><b>Experimental:</b> this part of CDP may change without notice.
         * @param request request parameters
         * @return a stage completing when the command completes
         */
        public CompletionStage<Void> recordClockSyncMarker(RecordClockSyncMarkerRequest request) {
            return client.call("Tracing.recordClockSyncMarker", request == null ? null : request.toMap(), result_ -> null);
        }
        /**
         * Request a global memory dump.
         * <p><b>Experimental:</b> this part of CDP may change without notice.
         * @param deterministic protocol value
         * @param levelOfDetail protocol value
         * @return a stage completing with the command result
         */
        public CompletionStage<RequestMemoryDumpResult> requestMemoryDump(Optional<Boolean> deterministic, Optional<Tracing.MemoryDumpLevelOfDetail> levelOfDetail) {
            Map<String, Object> params = new LinkedHashMap<>();
            deterministic.ifPresent(value_ -> params.put("deterministic", value_));
            levelOfDetail.ifPresent(value_ -> params.put("levelOfDetail", CdpObject.json(value_)));
            return client.call("Tracing.requestMemoryDump", params, result_ -> new RequestMemoryDumpResult(result_));
        }
        /**
         * Request a global memory dump.
         * <p><b>Experimental:</b> this part of CDP may change without notice.
         * @return a stage completing with the command result
         */
        public CompletionStage<RequestMemoryDumpResult> requestMemoryDump() {
            return requestMemoryDump(Optional.empty(), Optional.empty());
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
        public CompletionStage<Void> start(Optional<String> categories, Optional<String> options, OptionalDouble bufferUsageReportingInterval, Optional<StartTransferModeValues> transferMode, Optional<Tracing.StreamFormat> streamFormat, Optional<Tracing.StreamCompression> streamCompression, Optional<Tracing.TraceConfig> traceConfig, Optional<String> perfettoConfig, Optional<Tracing.TracingBackend> tracingBackend) {
            Map<String, Object> params = new LinkedHashMap<>();
            categories.ifPresent(value_ -> params.put("categories", CdpObject.json(value_)));
            options.ifPresent(value_ -> params.put("options", CdpObject.json(value_)));
            bufferUsageReportingInterval.ifPresent(value_ -> params.put("bufferUsageReportingInterval", value_));
            transferMode.ifPresent(value_ -> params.put("transferMode", CdpObject.json(value_)));
            streamFormat.ifPresent(value_ -> params.put("streamFormat", CdpObject.json(value_)));
            streamCompression.ifPresent(value_ -> params.put("streamCompression", CdpObject.json(value_)));
            traceConfig.ifPresent(value_ -> params.put("traceConfig", CdpObject.json(value_)));
            perfettoConfig.ifPresent(value_ -> params.put("perfettoConfig", CdpObject.json(value_)));
            tracingBackend.ifPresent(value_ -> params.put("tracingBackend", CdpObject.json(value_)));
            return client.call("Tracing.start", params, result_ -> null);
        }
        /**
         * Start trace events collection.
         * @return a stage completing when the command completes
         */
        public CompletionStage<Void> start() {
            return start(Optional.empty(), Optional.empty(), OptionalDouble.empty(), Optional.empty(), Optional.empty(), Optional.empty(), Optional.empty(), Optional.empty(), Optional.empty());
        }
        /**
         * Start trace events collection.
         * @param request request parameters
         * @return a stage completing when the command completes
         */
        public CompletionStage<Void> start(StartRequest request) {
            return client.call("Tracing.start", request == null ? null : request.toMap(), result_ -> null);
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
