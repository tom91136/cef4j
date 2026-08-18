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
 * Chrome DevTools Protocol Profiler domain.
 * @see <a href="https://chromium.googlesource.com/v8/v8/+/3063ea3a0737a3fc4d4ed3babd595f1cace1e6ac/include/js_protocol.pdl">Pinned protocol source</a>
 */
@Generated("mvn generate-sources -pl cef4j-platform -Dcef.version=150.0.18+gdb11278+chromium-150.0.7871.213")
@SuppressWarnings({"EscapedEntity", "InvalidParam", "JavaLangClash", "MissingSummary", "Unchecked", "UnusedMethod"})
public final class Profiler {
    private Profiler() {}
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
            return ((Number) require("id")).longValue();
        }
        /**
         * Function location.
         * @return the protocol field value
         */
        public Runtime.CallFrame callFrame() {
            return java.util.Objects.requireNonNull(Runtime.CallFrame.fromMap(java.util.Objects.requireNonNull(CdpObject.objectMap(require("callFrame")))));
        }
        /**
         * Number of samples where this node was on top of the call stack.
         * @return the protocol field value, empty when absent
         */
        public OptionalLong hitCount() {
            Long value = CdpObject.numberAsLong(raw("hitCount"));
            return value == null ? OptionalLong.empty() : OptionalLong.of(value);
        }
        /**
         * Child node ids.
         * @return the protocol field value, empty when absent
         */
        public Optional<java.util.List<Long>> children() {
            return Optional.ofNullable(list(raw("children"), element0 -> ((Number) element0).longValue()));
        }
        /**
         * The reason of being not optimized. The function may be deoptimized or marked as don&#x27;t optimize.
         * @return the protocol field value, empty when absent
         */
        public Optional<String> deoptReason() {
            return Optional.ofNullable((String) raw("deoptReason"));
        }
        /**
         * An array of source position ticks.
         * @return the protocol field value, empty when absent
         */
        public Optional<java.util.List<Profiler.PositionTickInfo>> positionTicks() {
            return Optional.ofNullable(list(raw("positionTicks"), element0 -> java.util.Objects.requireNonNull(Profiler.PositionTickInfo.fromMap(java.util.Objects.requireNonNull(CdpObject.objectMap(element0))))));
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
         * @param hitCount field value; empty omits the value
         * @return this model
         */
        public ProfileNode hitCount(OptionalLong hitCount) {
            set("hitCount", hitCount.isPresent() ? hitCount.getAsLong() : null);
            return this;
        }
        /**
         * Number of samples where this node was on top of the call stack.
         * @param hitCount field value; null removes the value
         * @return this model
         */
        public ProfileNode hitCount(Long hitCount) {
            set("hitCount", hitCount);
            return this;
        }
        /**
         * Child node ids.
         * @param children field value; empty omits the value
         * @return this model
         */
        public ProfileNode children(Optional<java.util.List<Long>> children) {
            set("children", children.orElse(null));
            return this;
        }
        /**
         * Child node ids.
         * @param children field value; null removes the value
         * @return this model
         */
        public ProfileNode children(java.util.List<Long> children) {
            set("children", children);
            return this;
        }
        /**
         * The reason of being not optimized. The function may be deoptimized or marked as don&#x27;t optimize.
         * @param deoptReason field value; empty omits the value
         * @return this model
         */
        public ProfileNode deoptReason(Optional<String> deoptReason) {
            set("deoptReason", deoptReason.orElse(null));
            return this;
        }
        /**
         * The reason of being not optimized. The function may be deoptimized or marked as don&#x27;t optimize.
         * @param deoptReason field value; null removes the value
         * @return this model
         */
        public ProfileNode deoptReason(String deoptReason) {
            set("deoptReason", deoptReason);
            return this;
        }
        /**
         * An array of source position ticks.
         * @param positionTicks field value; empty omits the value
         * @return this model
         */
        public ProfileNode positionTicks(Optional<java.util.List<Profiler.PositionTickInfo>> positionTicks) {
            set("positionTicks", positionTicks.orElse(null));
            return this;
        }
        /**
         * An array of source position ticks.
         * @param positionTicks field value; null removes the value
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
            return CdpObject.requireList(require("nodes"), element0 -> java.util.Objects.requireNonNull(Profiler.ProfileNode.fromMap(java.util.Objects.requireNonNull(CdpObject.objectMap(element0)))));
        }
        /**
         * Profiling start timestamp in microseconds.
         * @return the protocol field value
         */
        public double startTime() {
            return ((Number) require("startTime")).doubleValue();
        }
        /**
         * Profiling end timestamp in microseconds.
         * @return the protocol field value
         */
        public double endTime() {
            return ((Number) require("endTime")).doubleValue();
        }
        /**
         * Ids of samples top nodes.
         * @return the protocol field value, empty when absent
         */
        public Optional<java.util.List<Long>> samples() {
            return Optional.ofNullable(list(raw("samples"), element0 -> ((Number) element0).longValue()));
        }
        /**
         * Time intervals between adjacent samples in microseconds. The first delta is relative to the profile startTime.
         * @return the protocol field value, empty when absent
         */
        public Optional<java.util.List<Long>> timeDeltas() {
            return Optional.ofNullable(list(raw("timeDeltas"), element0 -> ((Number) element0).longValue()));
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
         * @param samples field value; empty omits the value
         * @return this model
         */
        public Profile samples(Optional<java.util.List<Long>> samples) {
            set("samples", samples.orElse(null));
            return this;
        }
        /**
         * Ids of samples top nodes.
         * @param samples field value; null removes the value
         * @return this model
         */
        public Profile samples(java.util.List<Long> samples) {
            set("samples", samples);
            return this;
        }
        /**
         * Time intervals between adjacent samples in microseconds. The first delta is relative to the profile startTime.
         * @param timeDeltas field value; empty omits the value
         * @return this model
         */
        public Profile timeDeltas(Optional<java.util.List<Long>> timeDeltas) {
            set("timeDeltas", timeDeltas.orElse(null));
            return this;
        }
        /**
         * Time intervals between adjacent samples in microseconds. The first delta is relative to the profile startTime.
         * @param timeDeltas field value; null removes the value
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
            return ((Number) require("line")).longValue();
        }
        /**
         * Number of samples attributed to the source line.
         * @return the protocol field value
         */
        public long ticks() {
            return ((Number) require("ticks")).longValue();
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
            return ((Number) require("startOffset")).longValue();
        }
        /**
         * JavaScript script source offset for the range end.
         * @return the protocol field value
         */
        public long endOffset() {
            return ((Number) require("endOffset")).longValue();
        }
        /**
         * Collected execution count of the source range.
         * @return the protocol field value
         */
        public long count() {
            return ((Number) require("count")).longValue();
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
            return (String) require("functionName");
        }
        /**
         * Source ranges inside the function with coverage data.
         * @return the protocol field value
         */
        public java.util.List<Profiler.CoverageRange> ranges() {
            return CdpObject.requireList(require("ranges"), element0 -> java.util.Objects.requireNonNull(Profiler.CoverageRange.fromMap(java.util.Objects.requireNonNull(CdpObject.objectMap(element0)))));
        }
        /**
         * Whether coverage data for this function has block granularity.
         * @return the protocol field value
         */
        public boolean isBlockCoverage() {
            return (Boolean) require("isBlockCoverage");
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
            return new Runtime.ScriptId((String) require("scriptId"));
        }
        /**
         * JavaScript script name or url.
         * @return the protocol field value
         */
        public String url() {
            return (String) require("url");
        }
        /**
         * Functions contained in the script that has coverage data.
         * @return the protocol field value
         */
        public java.util.List<Profiler.FunctionCoverage> functions() {
            return CdpObject.requireList(require("functions"), element0 -> java.util.Objects.requireNonNull(Profiler.FunctionCoverage.fromMap(java.util.Objects.requireNonNull(CdpObject.objectMap(element0)))));
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
            return CdpObject.requireList(require("result"), element0 -> java.util.Objects.requireNonNull(Profiler.ScriptCoverage.fromMap(java.util.Objects.requireNonNull(CdpObject.objectMap(element0)))));
        }
        /**
         * Monotonically increasing time (in seconds) when the coverage update was taken in the backend.
         * @return the protocol field value
         */
        public double timestamp() {
            return ((Number) require("timestamp")).doubleValue();
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
            return (String) require("id");
        }
        /**
         * Location of console.profileEnd().
         * @return the protocol field value
         */
        public Debugger.Location location() {
            return java.util.Objects.requireNonNull(Debugger.Location.fromMap(java.util.Objects.requireNonNull(CdpObject.objectMap(require("location")))));
        }
        /**
         * Returns the profile field.
         * @return the protocol field value
         */
        public Profiler.Profile profile() {
            return java.util.Objects.requireNonNull(Profiler.Profile.fromMap(java.util.Objects.requireNonNull(CdpObject.objectMap(require("profile")))));
        }
        /**
         * Profile title passed as an argument to console.profile().
         * @return the protocol field value, empty when absent
         */
        public Optional<String> title() {
            return Optional.ofNullable((String) raw("title"));
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
         * @param title field value; empty omits the value
         * @return this model
         */
        public ConsoleProfileFinishedEvent title(Optional<String> title) {
            set("title", title.orElse(null));
            return this;
        }
        /**
         * Profile title passed as an argument to console.profile().
         * @param title field value; null removes the value
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
            return (String) require("id");
        }
        /**
         * Location of console.profile().
         * @return the protocol field value
         */
        public Debugger.Location location() {
            return java.util.Objects.requireNonNull(Debugger.Location.fromMap(java.util.Objects.requireNonNull(CdpObject.objectMap(require("location")))));
        }
        /**
         * Profile title passed as an argument to console.profile().
         * @return the protocol field value, empty when absent
         */
        public Optional<String> title() {
            return Optional.ofNullable((String) raw("title"));
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
         * @param title field value; empty omits the value
         * @return this model
         */
        public ConsoleProfileStartedEvent title(Optional<String> title) {
            set("title", title.orElse(null));
            return this;
        }
        /**
         * Profile title passed as an argument to console.profile().
         * @param title field value; null removes the value
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
            return ((Number) require("timestamp")).doubleValue();
        }
        /**
         * Identifier for distinguishing coverage events.
         * @return the protocol field value
         */
        public String occasion() {
            return (String) require("occasion");
        }
        /**
         * Coverage data for the current isolate.
         * @return the protocol field value
         */
        public java.util.List<Profiler.ScriptCoverage> result() {
            return CdpObject.requireList(require("result"), element0 -> java.util.Objects.requireNonNull(Profiler.ScriptCoverage.fromMap(java.util.Objects.requireNonNull(CdpObject.objectMap(element0)))));
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
    public static final class Client {
        private final CdpClient client;
        public Client(CdpClient client) { this.client = client; }
        /**
         * Invokes Profiler.disable.
         * @return a stage completing when the command completes
         */
        public CompletionStage<Void> disable() {
            return client.call("Profiler.disable", null, result_ -> null);
        }
        /**
         * Invokes Profiler.enable.
         * @return a stage completing when the command completes
         */
        public CompletionStage<Void> enable() {
            return client.call("Profiler.enable", null, result_ -> null);
        }
        /**
         * Collect coverage data for the current isolate. The coverage data may be incomplete due to garbage collection.
         * @return a stage completing with the command result
         */
        public CompletionStage<java.util.List<Profiler.ScriptCoverage>> getBestEffortCoverage() {
            return client.call("Profiler.getBestEffortCoverage", null, result_ -> CdpObject.requireList(java.util.Objects.requireNonNull(result_.get("result")), element0 -> java.util.Objects.requireNonNull(Profiler.ScriptCoverage.fromMap(java.util.Objects.requireNonNull(CdpObject.objectMap(element0))))));
        }
        /**
         * Changes CPU profiler sampling interval. Must be called before CPU profiles recording started.
         * @param interval protocol value
         * @return a stage completing when the command completes
         */
        public CompletionStage<Void> setSamplingInterval(long interval) {
            Map<String, Object> params = new LinkedHashMap<>();
            params.put("interval", CdpObject.json(interval));
            return client.call("Profiler.setSamplingInterval", params, result_ -> null);
        }
        /**
         * Invokes Profiler.start.
         * @return a stage completing when the command completes
         */
        public CompletionStage<Void> start() {
            return client.call("Profiler.start", null, result_ -> null);
        }
        /**
         * Enable precise code coverage. Coverage data for JavaScript executed before enabling precise code coverage may be incomplete. Enabling prevents running optimized code and resets execution counters.
         * @param callCount protocol value
         * @param detailed protocol value
         * @param allowTriggeredUpdates protocol value
         * @return a stage completing with the command result
         */
        public CompletionStage<Double> startPreciseCoverage(Optional<Boolean> callCount, Optional<Boolean> detailed, Optional<Boolean> allowTriggeredUpdates) {
            Map<String, Object> params = new LinkedHashMap<>();
            callCount.ifPresent(value_ -> params.put("callCount", value_));
            detailed.ifPresent(value_ -> params.put("detailed", value_));
            allowTriggeredUpdates.ifPresent(value_ -> params.put("allowTriggeredUpdates", value_));
            return client.call("Profiler.startPreciseCoverage", params, result_ -> ((Number) java.util.Objects.requireNonNull(result_.get("timestamp"))).doubleValue());
        }
        /**
         * Enable precise code coverage. Coverage data for JavaScript executed before enabling precise code coverage may be incomplete. Enabling prevents running optimized code and resets execution counters.
         * @return a stage completing with the command result
         */
        public CompletionStage<Double> startPreciseCoverage() {
            return startPreciseCoverage(Optional.empty(), Optional.empty(), Optional.empty());
        }
        /**
         * Invokes Profiler.stop.
         * @return a stage completing with the command result
         */
        public CompletionStage<Profiler.Profile> stop() {
            return client.call("Profiler.stop", null, result_ -> java.util.Objects.requireNonNull(Profiler.Profile.fromMap(java.util.Objects.requireNonNull(CdpObject.objectMap(java.util.Objects.requireNonNull(result_.get("profile")))))));
        }
        /**
         * Disable precise code coverage. Disabling releases unnecessary execution count records and allows executing optimized code.
         * @return a stage completing when the command completes
         */
        public CompletionStage<Void> stopPreciseCoverage() {
            return client.call("Profiler.stopPreciseCoverage", null, result_ -> null);
        }
        /**
         * Collect coverage data for the current isolate, and resets execution counters. Precise code coverage needs to have started.
         * @return a stage completing with the command result
         */
        public CompletionStage<TakePreciseCoverageResult> takePreciseCoverage() {
            return client.call("Profiler.takePreciseCoverage", null, result_ -> new TakePreciseCoverageResult(result_));
        }
        /**
         * Subscribes to Profiler.consoleProfileFinished.
         * @param handler event callback
         * @return a removable subscription
         */
        public CdpSubscription onConsoleProfileFinished(Consumer<ConsoleProfileFinishedEvent> handler) {
            return client.on("Profiler.consoleProfileFinished", ConsoleProfileFinishedEvent::fromMap, handler);
        }
        /**
         * Sent when new profile recording is started using console.profile() call.
         * @param handler event callback
         * @return a removable subscription
         */
        public CdpSubscription onConsoleProfileStarted(Consumer<ConsoleProfileStartedEvent> handler) {
            return client.on("Profiler.consoleProfileStarted", ConsoleProfileStartedEvent::fromMap, handler);
        }
        /**
         * Reports coverage delta since the last poll (either from an event like this, or from {@code takePreciseCoverage} for the current isolate. May only be sent if precise code coverage has been started. This event can be trigged by the embedder to, for example, trigger collection of coverage data immediately at a certain point in time.
         * <p><b>Experimental:</b> this part of CDP may change without notice.
         * @param handler event callback
         * @return a removable subscription
         */
        public CdpSubscription onPreciseCoverageDeltaUpdate(Consumer<PreciseCoverageDeltaUpdateEvent> handler) {
            return client.on("Profiler.preciseCoverageDeltaUpdate", PreciseCoverageDeltaUpdateEvent::fromMap, handler);
        }
    }
}
