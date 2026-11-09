// GENERATED - do not edit. Regenerate via: ./mvnw generate-sources -pl cef4j-cdp -Dcef.version=150.0.18+gdb11278+chromium-150.0.7871.213
package net.kurobako.cef4j.cdp.generated;

import java.util.Map;
import java.util.Optional;
import java.util.OptionalLong;
import java.util.concurrent.CompletionStage;
import java.util.function.Consumer;
import javax.annotation.processing.Generated;
import net.kurobako.cef4j.cdp.CdpClient;
import net.kurobako.cef4j.cdp.CdpDomainClient;
import net.kurobako.cef4j.cdp.CdpField;
import net.kurobako.cef4j.cdp.CdpObject;
import net.kurobako.cef4j.cdp.CdpSubscription;
import net.kurobako.cef4j.policy.NullableBoundary;
import org.jspecify.annotations.Nullable;

/**
 * Chrome DevTools Protocol Profiler domain.
 * @see <a href="https://chromium.googlesource.com/v8/v8/+/3063ea3a0737a3fc4d4ed3babd595f1cace1e6ac/include/js_protocol.pdl">Pinned protocol source</a>
 */
@Generated("./mvnw generate-sources -pl cef4j-cdp -Dcef.version=150.0.18+gdb11278+chromium-150.0.7871.213")
@SuppressWarnings({"EscapedEntity", "InvalidParam", "JavaLangClash", "MissingSummary", "Unchecked", "UnusedMethod"})
@NullableBoundary("CDP wire objects use null for absent optional fields")
public final class Profiler {
    private Profiler() {}
    private static final CdpField<Runtime.CallFrame> CALL_FRAME = CdpField.of("callFrame", value -> java.util.Objects.requireNonNull(Runtime.CallFrame.fromMap(CdpObject.requireObjectMap(value))));
    private static final CdpField<java.util.List<Long>> CHILDREN = CdpField.of("children", value -> CdpObject.requireList(value, element0 -> ((Number) element0).longValue()));
    private static final CdpField<java.util.List<Profiler.FunctionCoverage>> FUNCTIONS = CdpField.of("functions", value -> CdpObject.requireList(value, element0 -> java.util.Objects.requireNonNull(Profiler.FunctionCoverage.fromMap(CdpObject.requireObjectMap(element0)))));
    private static final CdpField<Debugger.Location> LOCATION = CdpField.of("location", value -> java.util.Objects.requireNonNull(Debugger.Location.fromMap(CdpObject.requireObjectMap(value))));
    private static final CdpField<java.util.List<Profiler.ProfileNode>> NODES = CdpField.of("nodes", value -> CdpObject.requireList(value, element0 -> java.util.Objects.requireNonNull(Profiler.ProfileNode.fromMap(CdpObject.requireObjectMap(element0)))));
    private static final CdpField<java.util.List<Profiler.PositionTickInfo>> POSITION_TICKS = CdpField.of("positionTicks", value -> CdpObject.requireList(value, element0 -> java.util.Objects.requireNonNull(Profiler.PositionTickInfo.fromMap(CdpObject.requireObjectMap(element0)))));
    private static final CdpField<Profiler.Profile> PROFILE = CdpField.of("profile", value -> java.util.Objects.requireNonNull(Profiler.Profile.fromMap(CdpObject.requireObjectMap(value))));
    private static final CdpField<java.util.List<Profiler.CoverageRange>> RANGES = CdpField.of("ranges", value -> CdpObject.requireList(value, element0 -> java.util.Objects.requireNonNull(Profiler.CoverageRange.fromMap(CdpObject.requireObjectMap(element0)))));
    private static final CdpField<java.util.List<Profiler.ScriptCoverage>> RESULT = CdpField.of("result", value -> CdpObject.requireList(value, element0 -> java.util.Objects.requireNonNull(Profiler.ScriptCoverage.fromMap(CdpObject.requireObjectMap(element0)))));
    private static final CdpField<java.util.List<Long>> SAMPLES = CdpField.of("samples", value -> CdpObject.requireList(value, element0 -> ((Number) element0).longValue()));
    private static final CdpField<Runtime.ScriptId> SCRIPT_ID = CdpField.of("scriptId", value -> new Runtime.ScriptId((String) value));
    private static final CdpField<Double> TIMESTAMP = CdpField.of("timestamp", value -> ((Number) value).doubleValue());
    private static final CdpField<java.util.List<Long>> TIME_DELTAS = CdpField.of("timeDeltas", value -> CdpObject.requireList(value, element0 -> ((Number) element0).longValue()));
    /**
     * Profile node. Holds callsite information, execution statistics and child nodes.
     */
    public static final class ProfileNode extends CdpObject {
        public ProfileNode() {}
        private ProfileNode(Map<String, Object> values) { super(values); }
        public static ProfileNode fromMap(Map<String, Object> values) {
            return new ProfileNode(values);
        }
        /**
         * Unique id of the node.
         * @return the protocol field value
         */
        public long id() {
            return requireLong("id");
        }
        /**
         * Function location.
         * @return the protocol field value
         */
        public Runtime.CallFrame callFrame() {
            return require(CALL_FRAME);
        }
        /**
         * Number of samples where this node was on top of the call stack.
         * @return the protocol field value, empty when absent
         */
        public OptionalLong hitCount() {
            return optionalLong("hitCount");
        }
        /**
         * Child node ids.
         * @return the protocol field value, empty when absent
         */
        public Optional<java.util.List<Long>> children() {
            return optional(CHILDREN);
        }
        /**
         * The reason of being not optimized. The function may be deoptimized or marked as don&#x27;t optimize.
         * @return the protocol field value, empty when absent
         */
        public Optional<String> deoptReason() {
            return optionalString("deoptReason");
        }
        /**
         * An array of source position ticks.
         * @return the protocol field value, empty when absent
         */
        public Optional<java.util.List<Profiler.PositionTickInfo>> positionTicks() {
            return optional(POSITION_TICKS);
        }
        /**
         * Unique id of the node.
         * @param id field value
         * @return this model
         */
        public ProfileNode id(long id) {
            set("id", id);
            return this;
        }
        /**
         * Function location.
         * @param callFrame field value
         * @return this model
         */
        public ProfileNode callFrame(Runtime.CallFrame callFrame) {
            set("callFrame", callFrame);
            return this;
        }
        /**
         * Number of samples where this node was on top of the call stack.
         * @param hitCount field value
         * @return this model
         */
        public ProfileNode hitCount(long hitCount) {
            set("hitCount", hitCount);
            return this;
        }
        /**
         * Child node ids.
         * @param children field value
         * @return this model
         */
        public ProfileNode children(java.util.List<Long> children) {
            set("children", children);
            return this;
        }
        /**
         * The reason of being not optimized. The function may be deoptimized or marked as don&#x27;t optimize.
         * @param deoptReason field value
         * @return this model
         */
        public ProfileNode deoptReason(String deoptReason) {
            set("deoptReason", deoptReason);
            return this;
        }
        /**
         * An array of source position ticks.
         * @param positionTicks field value
         * @return this model
         */
        public ProfileNode positionTicks(java.util.List<Profiler.PositionTickInfo> positionTicks) {
            set("positionTicks", positionTicks);
            return this;
        }
    }
    /**
     * Profile.
     */
    public static final class Profile extends CdpObject {
        public Profile() {}
        private Profile(Map<String, Object> values) { super(values); }
        public static Profile fromMap(Map<String, Object> values) {
            return new Profile(values);
        }
        /**
         * The list of profile nodes. First item is the root node.
         * @return the protocol field value
         */
        public java.util.List<Profiler.ProfileNode> nodes() {
            return require(NODES);
        }
        /**
         * Profiling start timestamp in microseconds.
         * @return the protocol field value
         */
        public double startTime() {
            return requireDouble("startTime");
        }
        /**
         * Profiling end timestamp in microseconds.
         * @return the protocol field value
         */
        public double endTime() {
            return requireDouble("endTime");
        }
        /**
         * Ids of samples top nodes.
         * @return the protocol field value, empty when absent
         */
        public Optional<java.util.List<Long>> samples() {
            return optional(SAMPLES);
        }
        /**
         * Time intervals between adjacent samples in microseconds. The first delta is relative to the profile startTime.
         * @return the protocol field value, empty when absent
         */
        public Optional<java.util.List<Long>> timeDeltas() {
            return optional(TIME_DELTAS);
        }
        /**
         * The list of profile nodes. First item is the root node.
         * @param nodes field value
         * @return this model
         */
        public Profile nodes(java.util.List<Profiler.ProfileNode> nodes) {
            set("nodes", nodes);
            return this;
        }
        /**
         * Profiling start timestamp in microseconds.
         * @param startTime field value
         * @return this model
         */
        public Profile startTime(double startTime) {
            set("startTime", startTime);
            return this;
        }
        /**
         * Profiling end timestamp in microseconds.
         * @param endTime field value
         * @return this model
         */
        public Profile endTime(double endTime) {
            set("endTime", endTime);
            return this;
        }
        /**
         * Ids of samples top nodes.
         * @param samples field value
         * @return this model
         */
        public Profile samples(java.util.List<Long> samples) {
            set("samples", samples);
            return this;
        }
        /**
         * Time intervals between adjacent samples in microseconds. The first delta is relative to the profile startTime.
         * @param timeDeltas field value
         * @return this model
         */
        public Profile timeDeltas(java.util.List<Long> timeDeltas) {
            set("timeDeltas", timeDeltas);
            return this;
        }
    }
    /**
     * Specifies a number of samples attributed to a certain source position.
     */
    public static final class PositionTickInfo extends CdpObject {
        public PositionTickInfo() {}
        private PositionTickInfo(Map<String, Object> values) { super(values); }
        public static PositionTickInfo fromMap(Map<String, Object> values) {
            return new PositionTickInfo(values);
        }
        /**
         * Source line number (1-based).
         * @return the protocol field value
         */
        public long line() {
            return requireLong("line");
        }
        /**
         * Number of samples attributed to the source line.
         * @return the protocol field value
         */
        public long ticks() {
            return requireLong("ticks");
        }
        /**
         * Source line number (1-based).
         * @param line field value
         * @return this model
         */
        public PositionTickInfo line(long line) {
            set("line", line);
            return this;
        }
        /**
         * Number of samples attributed to the source line.
         * @param ticks field value
         * @return this model
         */
        public PositionTickInfo ticks(long ticks) {
            set("ticks", ticks);
            return this;
        }
    }
    /**
     * Coverage data for a source range.
     */
    public static final class CoverageRange extends CdpObject {
        public CoverageRange() {}
        private CoverageRange(Map<String, Object> values) { super(values); }
        public static CoverageRange fromMap(Map<String, Object> values) {
            return new CoverageRange(values);
        }
        /**
         * JavaScript script source offset for the range start.
         * @return the protocol field value
         */
        public long startOffset() {
            return requireLong("startOffset");
        }
        /**
         * JavaScript script source offset for the range end.
         * @return the protocol field value
         */
        public long endOffset() {
            return requireLong("endOffset");
        }
        /**
         * Collected execution count of the source range.
         * @return the protocol field value
         */
        public long count() {
            return requireLong("count");
        }
        /**
         * JavaScript script source offset for the range start.
         * @param startOffset field value
         * @return this model
         */
        public CoverageRange startOffset(long startOffset) {
            set("startOffset", startOffset);
            return this;
        }
        /**
         * JavaScript script source offset for the range end.
         * @param endOffset field value
         * @return this model
         */
        public CoverageRange endOffset(long endOffset) {
            set("endOffset", endOffset);
            return this;
        }
        /**
         * Collected execution count of the source range.
         * @param count field value
         * @return this model
         */
        public CoverageRange count(long count) {
            set("count", count);
            return this;
        }
    }
    /**
     * Coverage data for a JavaScript function.
     */
    public static final class FunctionCoverage extends CdpObject {
        public FunctionCoverage() {}
        private FunctionCoverage(Map<String, Object> values) { super(values); }
        public static FunctionCoverage fromMap(Map<String, Object> values) {
            return new FunctionCoverage(values);
        }
        /**
         * JavaScript function name.
         * @return the protocol field value
         */
        public String functionName() {
            return requireString("functionName");
        }
        /**
         * Source ranges inside the function with coverage data.
         * @return the protocol field value
         */
        public java.util.List<Profiler.CoverageRange> ranges() {
            return require(RANGES);
        }
        /**
         * Whether coverage data for this function has block granularity.
         * @return the protocol field value
         */
        public boolean isBlockCoverage() {
            return requireBoolean("isBlockCoverage");
        }
        /**
         * JavaScript function name.
         * @param functionName field value
         * @return this model
         */
        public FunctionCoverage functionName(String functionName) {
            set("functionName", functionName);
            return this;
        }
        /**
         * Source ranges inside the function with coverage data.
         * @param ranges field value
         * @return this model
         */
        public FunctionCoverage ranges(java.util.List<Profiler.CoverageRange> ranges) {
            set("ranges", ranges);
            return this;
        }
        /**
         * Whether coverage data for this function has block granularity.
         * @param isBlockCoverage field value
         * @return this model
         */
        public FunctionCoverage isBlockCoverage(boolean isBlockCoverage) {
            set("isBlockCoverage", isBlockCoverage);
            return this;
        }
    }
    /**
     * Coverage data for a JavaScript script.
     */
    public static final class ScriptCoverage extends CdpObject {
        public ScriptCoverage() {}
        private ScriptCoverage(Map<String, Object> values) { super(values); }
        public static ScriptCoverage fromMap(Map<String, Object> values) {
            return new ScriptCoverage(values);
        }
        /**
         * JavaScript script id.
         * @return the protocol field value
         */
        public Runtime.ScriptId scriptId() {
            return require(SCRIPT_ID);
        }
        /**
         * JavaScript script name or url.
         * @return the protocol field value
         */
        public String url() {
            return requireString("url");
        }
        /**
         * Functions contained in the script that has coverage data.
         * @return the protocol field value
         */
        public java.util.List<Profiler.FunctionCoverage> functions() {
            return require(FUNCTIONS);
        }
        /**
         * JavaScript script id.
         * @param scriptId field value
         * @return this model
         */
        public ScriptCoverage scriptId(Runtime.ScriptId scriptId) {
            set("scriptId", scriptId);
            return this;
        }
        /**
         * JavaScript script name or url.
         * @param url field value
         * @return this model
         */
        public ScriptCoverage url(String url) {
            set("url", url);
            return this;
        }
        /**
         * Functions contained in the script that has coverage data.
         * @param functions field value
         * @return this model
         */
        public ScriptCoverage functions(java.util.List<Profiler.FunctionCoverage> functions) {
            set("functions", functions);
            return this;
        }
    }
    /**
     * Changes CPU profiler sampling interval. Must be called before CPU profiles recording started.
     */
    public static final class SetSamplingIntervalRequest extends CdpObject {
        public SetSamplingIntervalRequest() {}
        public static SetSamplingIntervalRequest fromMap(Map<String, Object> values) {
            final SetSamplingIntervalRequest instance_ = new SetSamplingIntervalRequest();
            if (values != null) instance_.values.putAll(values);
            return instance_;
        }
        /**
         * New sampling interval in microseconds.
         * @return the protocol field value
         */
        public long interval() {
            return requireLong("interval");
        }
        /**
         * New sampling interval in microseconds.
         * @param interval field value
         * @return this model
         */
        public SetSamplingIntervalRequest interval(long interval) {
            set("interval", interval);
            return this;
        }
    }
    /**
     * Enable precise code coverage. Coverage data for JavaScript executed before enabling precise code coverage may be incomplete. Enabling prevents running optimized code and resets execution counters.
     */
    public static final class StartPreciseCoverageRequest extends CdpObject {
        public StartPreciseCoverageRequest() {}
        public static StartPreciseCoverageRequest fromMap(Map<String, Object> values) {
            final StartPreciseCoverageRequest instance_ = new StartPreciseCoverageRequest();
            if (values != null) instance_.values.putAll(values);
            return instance_;
        }
        /**
         * Collect accurate call counts beyond simple &#x27;covered&#x27; or &#x27;not covered&#x27;.
         * @return the protocol field value, empty when absent
         */
        public Optional<Boolean> callCount() {
            return optionalBoolean("callCount");
        }
        /**
         * Collect block-based coverage.
         * @return the protocol field value, empty when absent
         */
        public Optional<Boolean> detailed() {
            return optionalBoolean("detailed");
        }
        /**
         * Allow the backend to send updates on its own initiative
         * @return the protocol field value, empty when absent
         */
        public Optional<Boolean> allowTriggeredUpdates() {
            return optionalBoolean("allowTriggeredUpdates");
        }
        /**
         * Collect accurate call counts beyond simple &#x27;covered&#x27; or &#x27;not covered&#x27;.
         * @param callCount field value
         * @return this model
         */
        public StartPreciseCoverageRequest callCount(boolean callCount) {
            set("callCount", callCount);
            return this;
        }
        /**
         * Collect block-based coverage.
         * @param detailed field value
         * @return this model
         */
        public StartPreciseCoverageRequest detailed(boolean detailed) {
            set("detailed", detailed);
            return this;
        }
        /**
         * Allow the backend to send updates on its own initiative
         * @param allowTriggeredUpdates field value
         * @return this model
         */
        public StartPreciseCoverageRequest allowTriggeredUpdates(boolean allowTriggeredUpdates) {
            set("allowTriggeredUpdates", allowTriggeredUpdates);
            return this;
        }
    }
    /**
     * Collect coverage data for the current isolate, and resets execution counters. Precise code coverage needs to have started.
     */
    public static final class TakePreciseCoverageResult extends CdpObject {
        public TakePreciseCoverageResult() {}
        private TakePreciseCoverageResult(Map<String, Object> values) { super(values); }
        public static TakePreciseCoverageResult fromMap(Map<String, Object> values) {
            return new TakePreciseCoverageResult(values);
        }
        /**
         * Coverage data for the current isolate.
         * @return the protocol field value
         */
        public java.util.List<Profiler.ScriptCoverage> result() {
            return require(RESULT);
        }
        /**
         * Monotonically increasing time (in seconds) when the coverage update was taken in the backend.
         * @return the protocol field value
         */
        public double timestamp() {
            return requireDouble("timestamp");
        }
        /**
         * Coverage data for the current isolate.
         * @param result field value
         * @return this model
         */
        public TakePreciseCoverageResult result(java.util.List<Profiler.ScriptCoverage> result) {
            set("result", result);
            return this;
        }
        /**
         * Monotonically increasing time (in seconds) when the coverage update was taken in the backend.
         * @param timestamp field value
         * @return this model
         */
        public TakePreciseCoverageResult timestamp(double timestamp) {
            set("timestamp", timestamp);
            return this;
        }
    }
    /**
     * Payload of the Profiler.consoleProfileFinished event.
     */
    public static final class ConsoleProfileFinishedEvent extends CdpObject {
        public ConsoleProfileFinishedEvent() {}
        private ConsoleProfileFinishedEvent(Map<String, Object> values) { super(values); }
        public static ConsoleProfileFinishedEvent fromMap(Map<String, Object> values) {
            return new ConsoleProfileFinishedEvent(values);
        }
        /**
         * Returns the id field.
         * @return the protocol field value
         */
        public String id() {
            return requireString("id");
        }
        /**
         * Location of console.profileEnd().
         * @return the protocol field value
         */
        public Debugger.Location location() {
            return require(LOCATION);
        }
        /**
         * Returns the profile field.
         * @return the protocol field value
         */
        public Profiler.Profile profile() {
            return require(PROFILE);
        }
        /**
         * Profile title passed as an argument to console.profile().
         * @return the protocol field value, empty when absent
         */
        public Optional<String> title() {
            return optionalString("title");
        }
        /**
         * Sets the id field.
         * @param id field value
         * @return this model
         */
        public ConsoleProfileFinishedEvent id(String id) {
            set("id", id);
            return this;
        }
        /**
         * Location of console.profileEnd().
         * @param location field value
         * @return this model
         */
        public ConsoleProfileFinishedEvent location(Debugger.Location location) {
            set("location", location);
            return this;
        }
        /**
         * Sets the profile field.
         * @param profile field value
         * @return this model
         */
        public ConsoleProfileFinishedEvent profile(Profiler.Profile profile) {
            set("profile", profile);
            return this;
        }
        /**
         * Profile title passed as an argument to console.profile().
         * @param title field value
         * @return this model
         */
        public ConsoleProfileFinishedEvent title(String title) {
            set("title", title);
            return this;
        }
    }
    /**
     * Sent when new profile recording is started using console.profile() call.
     */
    public static final class ConsoleProfileStartedEvent extends CdpObject {
        public ConsoleProfileStartedEvent() {}
        private ConsoleProfileStartedEvent(Map<String, Object> values) { super(values); }
        public static ConsoleProfileStartedEvent fromMap(Map<String, Object> values) {
            return new ConsoleProfileStartedEvent(values);
        }
        /**
         * Returns the id field.
         * @return the protocol field value
         */
        public String id() {
            return requireString("id");
        }
        /**
         * Location of console.profile().
         * @return the protocol field value
         */
        public Debugger.Location location() {
            return require(LOCATION);
        }
        /**
         * Profile title passed as an argument to console.profile().
         * @return the protocol field value, empty when absent
         */
        public Optional<String> title() {
            return optionalString("title");
        }
        /**
         * Sets the id field.
         * @param id field value
         * @return this model
         */
        public ConsoleProfileStartedEvent id(String id) {
            set("id", id);
            return this;
        }
        /**
         * Location of console.profile().
         * @param location field value
         * @return this model
         */
        public ConsoleProfileStartedEvent location(Debugger.Location location) {
            set("location", location);
            return this;
        }
        /**
         * Profile title passed as an argument to console.profile().
         * @param title field value
         * @return this model
         */
        public ConsoleProfileStartedEvent title(String title) {
            set("title", title);
            return this;
        }
    }
    /**
     * Reports coverage delta since the last poll (either from an event like this, or from {@code takePreciseCoverage} for the current isolate. May only be sent if precise code coverage has been started. This event can be trigged by the embedder to, for example, trigger collection of coverage data immediately at a certain point in time.
     * <p><b>Experimental:</b> this part of CDP may change without notice.
     */
    public static final class PreciseCoverageDeltaUpdateEvent extends CdpObject {
        public PreciseCoverageDeltaUpdateEvent() {}
        private PreciseCoverageDeltaUpdateEvent(Map<String, Object> values) { super(values); }
        public static PreciseCoverageDeltaUpdateEvent fromMap(Map<String, Object> values) {
            return new PreciseCoverageDeltaUpdateEvent(values);
        }
        /**
         * Monotonically increasing time (in seconds) when the coverage update was taken in the backend.
         * @return the protocol field value
         */
        public double timestamp() {
            return requireDouble("timestamp");
        }
        /**
         * Identifier for distinguishing coverage events.
         * @return the protocol field value
         */
        public String occasion() {
            return requireString("occasion");
        }
        /**
         * Coverage data for the current isolate.
         * @return the protocol field value
         */
        public java.util.List<Profiler.ScriptCoverage> result() {
            return require(RESULT);
        }
        /**
         * Monotonically increasing time (in seconds) when the coverage update was taken in the backend.
         * @param timestamp field value
         * @return this model
         */
        public PreciseCoverageDeltaUpdateEvent timestamp(double timestamp) {
            set("timestamp", timestamp);
            return this;
        }
        /**
         * Identifier for distinguishing coverage events.
         * @param occasion field value
         * @return this model
         */
        public PreciseCoverageDeltaUpdateEvent occasion(String occasion) {
            set("occasion", occasion);
            return this;
        }
        /**
         * Coverage data for the current isolate.
         * @param result field value
         * @return this model
         */
        public PreciseCoverageDeltaUpdateEvent result(java.util.List<Profiler.ScriptCoverage> result) {
            set("result", result);
            return this;
        }
    }
    public static final class Client extends CdpDomainClient {
        public Client(CdpClient client) { super(client); }
        /**
         * Invokes Profiler.disable.
         * @return a stage completing when the command completes
         */
        public CompletionStage<Void> disable() {
            return call("Profiler.disable");
        }
        /**
         * Invokes Profiler.enable.
         * @return a stage completing when the command completes
         */
        public CompletionStage<Void> enable() {
            return call("Profiler.enable");
        }
        /**
         * Collect coverage data for the current isolate. The coverage data may be incomplete due to garbage collection.
         * @return a stage completing with the command result
         */
        public CompletionStage<java.util.List<Profiler.ScriptCoverage>> getBestEffortCoverage() {
            return call("Profiler.getBestEffortCoverage", RESULT::require);
        }
        /**
         * Changes CPU profiler sampling interval. Must be called before CPU profiles recording started.
         * @param interval protocol value
         * @return a stage completing when the command completes
         */
        public CompletionStage<Void> setSamplingInterval(long interval) {
            return call("Profiler.setSamplingInterval", params("interval", interval));
        }
        /**
         * Changes CPU profiler sampling interval. Must be called before CPU profiles recording started.
         * @param request request parameters
         * @return a stage completing when the command completes
         */
        public CompletionStage<Void> setSamplingInterval(SetSamplingIntervalRequest request) {
            return call("Profiler.setSamplingInterval", request);
        }
        /**
         * Invokes Profiler.start.
         * @return a stage completing when the command completes
         */
        public CompletionStage<Void> start() {
            return call("Profiler.start");
        }
        /**
         * Enable precise code coverage. Coverage data for JavaScript executed before enabling precise code coverage may be incomplete. Enabling prevents running optimized code and resets execution counters.
         * @param callCount protocol value
         * @param detailed protocol value
         * @param allowTriggeredUpdates protocol value
         * @return a stage completing with the command result
         */
        public CompletionStage<Double> startPreciseCoverage(@Nullable Boolean callCount, @Nullable Boolean detailed, @Nullable Boolean allowTriggeredUpdates) {
            return call("Profiler.startPreciseCoverage", params("callCount", callCount, "detailed", detailed, "allowTriggeredUpdates", allowTriggeredUpdates), TIMESTAMP::require);
        }
        /**
         * Enable precise code coverage. Coverage data for JavaScript executed before enabling precise code coverage may be incomplete. Enabling prevents running optimized code and resets execution counters.
         * @return a stage completing with the command result
         */
        public CompletionStage<Double> startPreciseCoverage() {
            return call("Profiler.startPreciseCoverage", TIMESTAMP::require);
        }
        /**
         * Enable precise code coverage. Coverage data for JavaScript executed before enabling precise code coverage may be incomplete. Enabling prevents running optimized code and resets execution counters.
         * @param request request parameters
         * @return a stage completing with the command result
         */
        public CompletionStage<Double> startPreciseCoverage(StartPreciseCoverageRequest request) {
            return call("Profiler.startPreciseCoverage", request, TIMESTAMP::require);
        }
        /**
         * Invokes Profiler.stop.
         * @return a stage completing with the command result
         */
        public CompletionStage<Profiler.Profile> stop() {
            return call("Profiler.stop", PROFILE::require);
        }
        /**
         * Disable precise code coverage. Disabling releases unnecessary execution count records and allows executing optimized code.
         * @return a stage completing when the command completes
         */
        public CompletionStage<Void> stopPreciseCoverage() {
            return call("Profiler.stopPreciseCoverage");
        }
        /**
         * Collect coverage data for the current isolate, and resets execution counters. Precise code coverage needs to have started.
         * @return a stage completing with the command result
         */
        public CompletionStage<TakePreciseCoverageResult> takePreciseCoverage() {
            return call("Profiler.takePreciseCoverage", TakePreciseCoverageResult::fromMap);
        }
        /**
         * Subscribes to Profiler.consoleProfileFinished.
         * @param handler event callback
         * @return a removable subscription
         */
        public CdpSubscription onConsoleProfileFinished(Consumer<ConsoleProfileFinishedEvent> handler) {
            return on("Profiler.consoleProfileFinished", ConsoleProfileFinishedEvent::fromMap, handler);
        }
        /**
         * Sent when new profile recording is started using console.profile() call.
         * @param handler event callback
         * @return a removable subscription
         */
        public CdpSubscription onConsoleProfileStarted(Consumer<ConsoleProfileStartedEvent> handler) {
            return on("Profiler.consoleProfileStarted", ConsoleProfileStartedEvent::fromMap, handler);
        }
        /**
         * Reports coverage delta since the last poll (either from an event like this, or from {@code takePreciseCoverage} for the current isolate. May only be sent if precise code coverage has been started. This event can be trigged by the embedder to, for example, trigger collection of coverage data immediately at a certain point in time.
         * <p><b>Experimental:</b> this part of CDP may change without notice.
         * @param handler event callback
         * @return a removable subscription
         */
        public CdpSubscription onPreciseCoverageDeltaUpdate(Consumer<PreciseCoverageDeltaUpdateEvent> handler) {
            return on("Profiler.preciseCoverageDeltaUpdate", PreciseCoverageDeltaUpdateEvent::fromMap, handler);
        }
    }
}
