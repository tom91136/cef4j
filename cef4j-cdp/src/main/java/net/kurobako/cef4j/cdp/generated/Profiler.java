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
 * Chrome DevTools Protocol Profiler domain.
 * @see <a href="https://chromium.googlesource.com/v8/v8/+/3063ea3a0737a3fc4d4ed3babd595f1cace1e6ac/include/js_protocol.pdl">Pinned protocol source</a>
 */
@SuppressWarnings({"EscapedEntity", "JavaLangClash", "MissingSummary", "UnusedMethod"})
public final class Profiler {
    private Profiler() {}
    @Nullable private static Long numberAsLong(@Nullable Object value) { return value == null ? null : ((Number) value).longValue(); }
    @Nullable private static Double numberAsDouble(@Nullable Object value) { return value == null ? null : ((Number) value).doubleValue(); }
    /**
     * Profile node. Holds callsite information, execution statistics and child nodes.
     */
    public static final class ProfileNode extends CdpObject {
        private ProfileNode(Map<String, Object> values) { super(values); }
        @Nullable public static ProfileNode fromMap(@Nullable Map<String, Object> values) {
            return values == null ? null : new ProfileNode(values);
        }
        public static Builder builder() { return new Builder(); }
        /**
         * Unique id of the node.
         * @return the protocol field value
         */
        @Nullable public Long id() {
            return numberAsLong(value("id"));
        }
        /**
         * Function location.
         * @return the protocol field value
         */
        @Nullable public Runtime.CallFrame callFrame() {
            return Runtime.CallFrame.fromMap(objectMap(value("callFrame")));
        }
        /**
         * Number of samples where this node was on top of the call stack.
         * @return the protocol field value
         */
        @Nullable public Long hitCount() {
            return numberAsLong(value("hitCount"));
        }
        /**
         * Child node ids.
         * @return the protocol field value
         */
        @Nullable public java.util.List<Long> children() {
            return list(value("children"), element0 -> numberAsLong(element0));
        }
        /**
         * The reason of being not optimized. The function may be deoptimized or marked as don&#x27;t optimize.
         * @return the protocol field value
         */
        @Nullable public String deoptReason() {
            return (String) value("deoptReason");
        }
        /**
         * An array of source position ticks.
         * @return the protocol field value
         */
        @Nullable public java.util.List<Profiler.PositionTickInfo> positionTicks() {
            return list(value("positionTicks"), element0 -> Profiler.PositionTickInfo.fromMap(objectMap(element0)));
        }
        public static final class Builder {
            private final Map<String, Object> values = new LinkedHashMap<>();
            /**
             * Unique id of the node.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder id(@Nullable Long value) {
                if (value == null) values.remove("id");
                else values.put("id", jsonValue(value));
                return this;
            }
            /**
             * Function location.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder callFrame(@Nullable Runtime.CallFrame value) {
                if (value == null) values.remove("callFrame");
                else values.put("callFrame", jsonValue(value));
                return this;
            }
            /**
             * Number of samples where this node was on top of the call stack.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder hitCount(@Nullable Long value) {
                if (value == null) values.remove("hitCount");
                else values.put("hitCount", jsonValue(value));
                return this;
            }
            /**
             * Child node ids.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder children(@Nullable java.util.List<Long> value) {
                if (value == null) values.remove("children");
                else values.put("children", jsonValue(value));
                return this;
            }
            /**
             * The reason of being not optimized. The function may be deoptimized or marked as don&#x27;t optimize.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder deoptReason(@Nullable String value) {
                if (value == null) values.remove("deoptReason");
                else values.put("deoptReason", jsonValue(value));
                return this;
            }
            /**
             * An array of source position ticks.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder positionTicks(@Nullable java.util.List<Profiler.PositionTickInfo> value) {
                if (value == null) values.remove("positionTicks");
                else values.put("positionTicks", jsonValue(value));
                return this;
            }
            public ProfileNode build() {
                if (!values.containsKey("id")) throw new IllegalStateException("Missing required CDP field: id");
                if (!values.containsKey("callFrame")) throw new IllegalStateException("Missing required CDP field: callFrame");
                return new ProfileNode(values);
            }
        }
    }
    /**
     * Profile.
     */
    public static final class Profile extends CdpObject {
        private Profile(Map<String, Object> values) { super(values); }
        @Nullable public static Profile fromMap(@Nullable Map<String, Object> values) {
            return values == null ? null : new Profile(values);
        }
        public static Builder builder() { return new Builder(); }
        /**
         * The list of profile nodes. First item is the root node.
         * @return the protocol field value
         */
        @Nullable public java.util.List<Profiler.ProfileNode> nodes() {
            return list(value("nodes"), element0 -> Profiler.ProfileNode.fromMap(objectMap(element0)));
        }
        /**
         * Profiling start timestamp in microseconds.
         * @return the protocol field value
         */
        @Nullable public Double startTime() {
            return numberAsDouble(value("startTime"));
        }
        /**
         * Profiling end timestamp in microseconds.
         * @return the protocol field value
         */
        @Nullable public Double endTime() {
            return numberAsDouble(value("endTime"));
        }
        /**
         * Ids of samples top nodes.
         * @return the protocol field value
         */
        @Nullable public java.util.List<Long> samples() {
            return list(value("samples"), element0 -> numberAsLong(element0));
        }
        /**
         * Time intervals between adjacent samples in microseconds. The first delta is relative to the profile startTime.
         * @return the protocol field value
         */
        @Nullable public java.util.List<Long> timeDeltas() {
            return list(value("timeDeltas"), element0 -> numberAsLong(element0));
        }
        public static final class Builder {
            private final Map<String, Object> values = new LinkedHashMap<>();
            /**
             * The list of profile nodes. First item is the root node.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder nodes(@Nullable java.util.List<Profiler.ProfileNode> value) {
                if (value == null) values.remove("nodes");
                else values.put("nodes", jsonValue(value));
                return this;
            }
            /**
             * Profiling start timestamp in microseconds.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder startTime(@Nullable Double value) {
                if (value == null) values.remove("startTime");
                else values.put("startTime", jsonValue(value));
                return this;
            }
            /**
             * Profiling end timestamp in microseconds.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder endTime(@Nullable Double value) {
                if (value == null) values.remove("endTime");
                else values.put("endTime", jsonValue(value));
                return this;
            }
            /**
             * Ids of samples top nodes.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder samples(@Nullable java.util.List<Long> value) {
                if (value == null) values.remove("samples");
                else values.put("samples", jsonValue(value));
                return this;
            }
            /**
             * Time intervals between adjacent samples in microseconds. The first delta is relative to the profile startTime.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder timeDeltas(@Nullable java.util.List<Long> value) {
                if (value == null) values.remove("timeDeltas");
                else values.put("timeDeltas", jsonValue(value));
                return this;
            }
            public Profile build() {
                if (!values.containsKey("nodes")) throw new IllegalStateException("Missing required CDP field: nodes");
                if (!values.containsKey("startTime")) throw new IllegalStateException("Missing required CDP field: startTime");
                if (!values.containsKey("endTime")) throw new IllegalStateException("Missing required CDP field: endTime");
                return new Profile(values);
            }
        }
    }
    /**
     * Specifies a number of samples attributed to a certain source position.
     */
    public static final class PositionTickInfo extends CdpObject {
        private PositionTickInfo(Map<String, Object> values) { super(values); }
        @Nullable public static PositionTickInfo fromMap(@Nullable Map<String, Object> values) {
            return values == null ? null : new PositionTickInfo(values);
        }
        public static Builder builder() { return new Builder(); }
        /**
         * Source line number (1-based).
         * @return the protocol field value
         */
        @Nullable public Long line() {
            return numberAsLong(value("line"));
        }
        /**
         * Number of samples attributed to the source line.
         * @return the protocol field value
         */
        @Nullable public Long ticks() {
            return numberAsLong(value("ticks"));
        }
        public static final class Builder {
            private final Map<String, Object> values = new LinkedHashMap<>();
            /**
             * Source line number (1-based).
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder line(@Nullable Long value) {
                if (value == null) values.remove("line");
                else values.put("line", jsonValue(value));
                return this;
            }
            /**
             * Number of samples attributed to the source line.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder ticks(@Nullable Long value) {
                if (value == null) values.remove("ticks");
                else values.put("ticks", jsonValue(value));
                return this;
            }
            public PositionTickInfo build() {
                if (!values.containsKey("line")) throw new IllegalStateException("Missing required CDP field: line");
                if (!values.containsKey("ticks")) throw new IllegalStateException("Missing required CDP field: ticks");
                return new PositionTickInfo(values);
            }
        }
    }
    /**
     * Coverage data for a source range.
     */
    public static final class CoverageRange extends CdpObject {
        private CoverageRange(Map<String, Object> values) { super(values); }
        @Nullable public static CoverageRange fromMap(@Nullable Map<String, Object> values) {
            return values == null ? null : new CoverageRange(values);
        }
        public static Builder builder() { return new Builder(); }
        /**
         * JavaScript script source offset for the range start.
         * @return the protocol field value
         */
        @Nullable public Long startOffset() {
            return numberAsLong(value("startOffset"));
        }
        /**
         * JavaScript script source offset for the range end.
         * @return the protocol field value
         */
        @Nullable public Long endOffset() {
            return numberAsLong(value("endOffset"));
        }
        /**
         * Collected execution count of the source range.
         * @return the protocol field value
         */
        @Nullable public Long count() {
            return numberAsLong(value("count"));
        }
        public static final class Builder {
            private final Map<String, Object> values = new LinkedHashMap<>();
            /**
             * JavaScript script source offset for the range start.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder startOffset(@Nullable Long value) {
                if (value == null) values.remove("startOffset");
                else values.put("startOffset", jsonValue(value));
                return this;
            }
            /**
             * JavaScript script source offset for the range end.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder endOffset(@Nullable Long value) {
                if (value == null) values.remove("endOffset");
                else values.put("endOffset", jsonValue(value));
                return this;
            }
            /**
             * Collected execution count of the source range.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder count(@Nullable Long value) {
                if (value == null) values.remove("count");
                else values.put("count", jsonValue(value));
                return this;
            }
            public CoverageRange build() {
                if (!values.containsKey("startOffset")) throw new IllegalStateException("Missing required CDP field: startOffset");
                if (!values.containsKey("endOffset")) throw new IllegalStateException("Missing required CDP field: endOffset");
                if (!values.containsKey("count")) throw new IllegalStateException("Missing required CDP field: count");
                return new CoverageRange(values);
            }
        }
    }
    /**
     * Coverage data for a JavaScript function.
     */
    public static final class FunctionCoverage extends CdpObject {
        private FunctionCoverage(Map<String, Object> values) { super(values); }
        @Nullable public static FunctionCoverage fromMap(@Nullable Map<String, Object> values) {
            return values == null ? null : new FunctionCoverage(values);
        }
        public static Builder builder() { return new Builder(); }
        /**
         * JavaScript function name.
         * @return the protocol field value
         */
        @Nullable public String functionName() {
            return (String) value("functionName");
        }
        /**
         * Source ranges inside the function with coverage data.
         * @return the protocol field value
         */
        @Nullable public java.util.List<Profiler.CoverageRange> ranges() {
            return list(value("ranges"), element0 -> Profiler.CoverageRange.fromMap(objectMap(element0)));
        }
        /**
         * Whether coverage data for this function has block granularity.
         * @return the protocol field value
         */
        @Nullable public Boolean isBlockCoverage() {
            return (Boolean) value("isBlockCoverage");
        }
        public static final class Builder {
            private final Map<String, Object> values = new LinkedHashMap<>();
            /**
             * JavaScript function name.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder functionName(@Nullable String value) {
                if (value == null) values.remove("functionName");
                else values.put("functionName", jsonValue(value));
                return this;
            }
            /**
             * Source ranges inside the function with coverage data.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder ranges(@Nullable java.util.List<Profiler.CoverageRange> value) {
                if (value == null) values.remove("ranges");
                else values.put("ranges", jsonValue(value));
                return this;
            }
            /**
             * Whether coverage data for this function has block granularity.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder isBlockCoverage(@Nullable Boolean value) {
                if (value == null) values.remove("isBlockCoverage");
                else values.put("isBlockCoverage", jsonValue(value));
                return this;
            }
            public FunctionCoverage build() {
                if (!values.containsKey("functionName")) throw new IllegalStateException("Missing required CDP field: functionName");
                if (!values.containsKey("ranges")) throw new IllegalStateException("Missing required CDP field: ranges");
                if (!values.containsKey("isBlockCoverage")) throw new IllegalStateException("Missing required CDP field: isBlockCoverage");
                return new FunctionCoverage(values);
            }
        }
    }
    /**
     * Coverage data for a JavaScript script.
     */
    public static final class ScriptCoverage extends CdpObject {
        private ScriptCoverage(Map<String, Object> values) { super(values); }
        @Nullable public static ScriptCoverage fromMap(@Nullable Map<String, Object> values) {
            return values == null ? null : new ScriptCoverage(values);
        }
        public static Builder builder() { return new Builder(); }
        /**
         * JavaScript script id.
         * @return the protocol field value
         */
        @Nullable public String scriptId() {
            return (String) value("scriptId");
        }
        /**
         * JavaScript script name or url.
         * @return the protocol field value
         */
        @Nullable public String url() {
            return (String) value("url");
        }
        /**
         * Functions contained in the script that has coverage data.
         * @return the protocol field value
         */
        @Nullable public java.util.List<Profiler.FunctionCoverage> functions() {
            return list(value("functions"), element0 -> Profiler.FunctionCoverage.fromMap(objectMap(element0)));
        }
        public static final class Builder {
            private final Map<String, Object> values = new LinkedHashMap<>();
            /**
             * JavaScript script id.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder scriptId(@Nullable String value) {
                if (value == null) values.remove("scriptId");
                else values.put("scriptId", jsonValue(value));
                return this;
            }
            /**
             * JavaScript script name or url.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder url(@Nullable String value) {
                if (value == null) values.remove("url");
                else values.put("url", jsonValue(value));
                return this;
            }
            /**
             * Functions contained in the script that has coverage data.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder functions(@Nullable java.util.List<Profiler.FunctionCoverage> value) {
                if (value == null) values.remove("functions");
                else values.put("functions", jsonValue(value));
                return this;
            }
            public ScriptCoverage build() {
                if (!values.containsKey("scriptId")) throw new IllegalStateException("Missing required CDP field: scriptId");
                if (!values.containsKey("url")) throw new IllegalStateException("Missing required CDP field: url");
                if (!values.containsKey("functions")) throw new IllegalStateException("Missing required CDP field: functions");
                return new ScriptCoverage(values);
            }
        }
    }
    /**
     * Parameters for Profiler.disable.
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
     * Result of Profiler.disable.
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
     * Parameters for Profiler.enable.
     */
    public static final class EnableParams extends CdpObject {
        private EnableParams(Map<String, Object> values) { super(values); }
        @Nullable public static EnableParams fromMap(@Nullable Map<String, Object> values) {
            return values == null ? null : new EnableParams(values);
        }
        public static Builder builder() { return new Builder(); }
        public static final class Builder {
            private final Map<String, Object> values = new LinkedHashMap<>();
            public EnableParams build() {
                return new EnableParams(values);
            }
        }
    }
    /**
     * Result of Profiler.enable.
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
     * Collect coverage data for the current isolate. The coverage data may be incomplete due to garbage collection.
     */
    public static final class GetBestEffortCoverageParams extends CdpObject {
        private GetBestEffortCoverageParams(Map<String, Object> values) { super(values); }
        @Nullable public static GetBestEffortCoverageParams fromMap(@Nullable Map<String, Object> values) {
            return values == null ? null : new GetBestEffortCoverageParams(values);
        }
        public static Builder builder() { return new Builder(); }
        public static final class Builder {
            private final Map<String, Object> values = new LinkedHashMap<>();
            public GetBestEffortCoverageParams build() {
                return new GetBestEffortCoverageParams(values);
            }
        }
    }
    /**
     * Collect coverage data for the current isolate. The coverage data may be incomplete due to garbage collection.
     */
    public static final class GetBestEffortCoverageResult extends CdpObject {
        private GetBestEffortCoverageResult(Map<String, Object> values) { super(values); }
        @Nullable public static GetBestEffortCoverageResult fromMap(@Nullable Map<String, Object> values) {
            return values == null ? null : new GetBestEffortCoverageResult(values);
        }
        public static Builder builder() { return new Builder(); }
        /**
         * Coverage data for the current isolate.
         * @return the protocol field value
         */
        @Nullable public java.util.List<Profiler.ScriptCoverage> result() {
            return list(value("result"), element0 -> Profiler.ScriptCoverage.fromMap(objectMap(element0)));
        }
        public static final class Builder {
            private final Map<String, Object> values = new LinkedHashMap<>();
            /**
             * Coverage data for the current isolate.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder result(@Nullable java.util.List<Profiler.ScriptCoverage> value) {
                if (value == null) values.remove("result");
                else values.put("result", jsonValue(value));
                return this;
            }
            public GetBestEffortCoverageResult build() {
                if (!values.containsKey("result")) throw new IllegalStateException("Missing required CDP field: result");
                return new GetBestEffortCoverageResult(values);
            }
        }
    }
    /**
     * Changes CPU profiler sampling interval. Must be called before CPU profiles recording started.
     */
    public static final class SetSamplingIntervalParams extends CdpObject {
        private SetSamplingIntervalParams(Map<String, Object> values) { super(values); }
        @Nullable public static SetSamplingIntervalParams fromMap(@Nullable Map<String, Object> values) {
            return values == null ? null : new SetSamplingIntervalParams(values);
        }
        public static Builder builder() { return new Builder(); }
        /**
         * New sampling interval in microseconds.
         * @return the protocol field value
         */
        @Nullable public Long interval() {
            return numberAsLong(value("interval"));
        }
        public static final class Builder {
            private final Map<String, Object> values = new LinkedHashMap<>();
            /**
             * New sampling interval in microseconds.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder interval(@Nullable Long value) {
                if (value == null) values.remove("interval");
                else values.put("interval", jsonValue(value));
                return this;
            }
            public SetSamplingIntervalParams build() {
                if (!values.containsKey("interval")) throw new IllegalStateException("Missing required CDP field: interval");
                return new SetSamplingIntervalParams(values);
            }
        }
    }
    /**
     * Changes CPU profiler sampling interval. Must be called before CPU profiles recording started.
     */
    public static final class SetSamplingIntervalResult extends CdpObject {
        private SetSamplingIntervalResult(Map<String, Object> values) { super(values); }
        @Nullable public static SetSamplingIntervalResult fromMap(@Nullable Map<String, Object> values) {
            return values == null ? null : new SetSamplingIntervalResult(values);
        }
        public static Builder builder() { return new Builder(); }
        public static final class Builder {
            private final Map<String, Object> values = new LinkedHashMap<>();
            public SetSamplingIntervalResult build() {
                return new SetSamplingIntervalResult(values);
            }
        }
    }
    /**
     * Parameters for Profiler.start.
     */
    public static final class StartParams extends CdpObject {
        private StartParams(Map<String, Object> values) { super(values); }
        @Nullable public static StartParams fromMap(@Nullable Map<String, Object> values) {
            return values == null ? null : new StartParams(values);
        }
        public static Builder builder() { return new Builder(); }
        public static final class Builder {
            private final Map<String, Object> values = new LinkedHashMap<>();
            public StartParams build() {
                return new StartParams(values);
            }
        }
    }
    /**
     * Result of Profiler.start.
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
     * Enable precise code coverage. Coverage data for JavaScript executed before enabling precise code coverage may be incomplete. Enabling prevents running optimized code and resets execution counters.
     */
    public static final class StartPreciseCoverageParams extends CdpObject {
        private StartPreciseCoverageParams(Map<String, Object> values) { super(values); }
        @Nullable public static StartPreciseCoverageParams fromMap(@Nullable Map<String, Object> values) {
            return values == null ? null : new StartPreciseCoverageParams(values);
        }
        public static Builder builder() { return new Builder(); }
        /**
         * Collect accurate call counts beyond simple &#x27;covered&#x27; or &#x27;not covered&#x27;.
         * @return the protocol field value
         */
        @Nullable public Boolean callCount() {
            return (Boolean) value("callCount");
        }
        /**
         * Collect block-based coverage.
         * @return the protocol field value
         */
        @Nullable public Boolean detailed() {
            return (Boolean) value("detailed");
        }
        /**
         * Allow the backend to send updates on its own initiative
         * @return the protocol field value
         */
        @Nullable public Boolean allowTriggeredUpdates() {
            return (Boolean) value("allowTriggeredUpdates");
        }
        public static final class Builder {
            private final Map<String, Object> values = new LinkedHashMap<>();
            /**
             * Collect accurate call counts beyond simple &#x27;covered&#x27; or &#x27;not covered&#x27;.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder callCount(@Nullable Boolean value) {
                if (value == null) values.remove("callCount");
                else values.put("callCount", jsonValue(value));
                return this;
            }
            /**
             * Collect block-based coverage.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder detailed(@Nullable Boolean value) {
                if (value == null) values.remove("detailed");
                else values.put("detailed", jsonValue(value));
                return this;
            }
            /**
             * Allow the backend to send updates on its own initiative
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder allowTriggeredUpdates(@Nullable Boolean value) {
                if (value == null) values.remove("allowTriggeredUpdates");
                else values.put("allowTriggeredUpdates", jsonValue(value));
                return this;
            }
            public StartPreciseCoverageParams build() {
                return new StartPreciseCoverageParams(values);
            }
        }
    }
    /**
     * Enable precise code coverage. Coverage data for JavaScript executed before enabling precise code coverage may be incomplete. Enabling prevents running optimized code and resets execution counters.
     */
    public static final class StartPreciseCoverageResult extends CdpObject {
        private StartPreciseCoverageResult(Map<String, Object> values) { super(values); }
        @Nullable public static StartPreciseCoverageResult fromMap(@Nullable Map<String, Object> values) {
            return values == null ? null : new StartPreciseCoverageResult(values);
        }
        public static Builder builder() { return new Builder(); }
        /**
         * Monotonically increasing time (in seconds) when the coverage update was taken in the backend.
         * @return the protocol field value
         */
        @Nullable public Double timestamp() {
            return numberAsDouble(value("timestamp"));
        }
        public static final class Builder {
            private final Map<String, Object> values = new LinkedHashMap<>();
            /**
             * Monotonically increasing time (in seconds) when the coverage update was taken in the backend.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder timestamp(@Nullable Double value) {
                if (value == null) values.remove("timestamp");
                else values.put("timestamp", jsonValue(value));
                return this;
            }
            public StartPreciseCoverageResult build() {
                if (!values.containsKey("timestamp")) throw new IllegalStateException("Missing required CDP field: timestamp");
                return new StartPreciseCoverageResult(values);
            }
        }
    }
    /**
     * Parameters for Profiler.stop.
     */
    public static final class StopParams extends CdpObject {
        private StopParams(Map<String, Object> values) { super(values); }
        @Nullable public static StopParams fromMap(@Nullable Map<String, Object> values) {
            return values == null ? null : new StopParams(values);
        }
        public static Builder builder() { return new Builder(); }
        public static final class Builder {
            private final Map<String, Object> values = new LinkedHashMap<>();
            public StopParams build() {
                return new StopParams(values);
            }
        }
    }
    /**
     * Result of Profiler.stop.
     */
    public static final class StopResult extends CdpObject {
        private StopResult(Map<String, Object> values) { super(values); }
        @Nullable public static StopResult fromMap(@Nullable Map<String, Object> values) {
            return values == null ? null : new StopResult(values);
        }
        public static Builder builder() { return new Builder(); }
        /**
         * Recorded profile.
         * @return the protocol field value
         */
        @Nullable public Profiler.Profile profile() {
            return Profiler.Profile.fromMap(objectMap(value("profile")));
        }
        public static final class Builder {
            private final Map<String, Object> values = new LinkedHashMap<>();
            /**
             * Recorded profile.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder profile(@Nullable Profiler.Profile value) {
                if (value == null) values.remove("profile");
                else values.put("profile", jsonValue(value));
                return this;
            }
            public StopResult build() {
                if (!values.containsKey("profile")) throw new IllegalStateException("Missing required CDP field: profile");
                return new StopResult(values);
            }
        }
    }
    /**
     * Disable precise code coverage. Disabling releases unnecessary execution count records and allows executing optimized code.
     */
    public static final class StopPreciseCoverageParams extends CdpObject {
        private StopPreciseCoverageParams(Map<String, Object> values) { super(values); }
        @Nullable public static StopPreciseCoverageParams fromMap(@Nullable Map<String, Object> values) {
            return values == null ? null : new StopPreciseCoverageParams(values);
        }
        public static Builder builder() { return new Builder(); }
        public static final class Builder {
            private final Map<String, Object> values = new LinkedHashMap<>();
            public StopPreciseCoverageParams build() {
                return new StopPreciseCoverageParams(values);
            }
        }
    }
    /**
     * Disable precise code coverage. Disabling releases unnecessary execution count records and allows executing optimized code.
     */
    public static final class StopPreciseCoverageResult extends CdpObject {
        private StopPreciseCoverageResult(Map<String, Object> values) { super(values); }
        @Nullable public static StopPreciseCoverageResult fromMap(@Nullable Map<String, Object> values) {
            return values == null ? null : new StopPreciseCoverageResult(values);
        }
        public static Builder builder() { return new Builder(); }
        public static final class Builder {
            private final Map<String, Object> values = new LinkedHashMap<>();
            public StopPreciseCoverageResult build() {
                return new StopPreciseCoverageResult(values);
            }
        }
    }
    /**
     * Collect coverage data for the current isolate, and resets execution counters. Precise code coverage needs to have started.
     */
    public static final class TakePreciseCoverageParams extends CdpObject {
        private TakePreciseCoverageParams(Map<String, Object> values) { super(values); }
        @Nullable public static TakePreciseCoverageParams fromMap(@Nullable Map<String, Object> values) {
            return values == null ? null : new TakePreciseCoverageParams(values);
        }
        public static Builder builder() { return new Builder(); }
        public static final class Builder {
            private final Map<String, Object> values = new LinkedHashMap<>();
            public TakePreciseCoverageParams build() {
                return new TakePreciseCoverageParams(values);
            }
        }
    }
    /**
     * Collect coverage data for the current isolate, and resets execution counters. Precise code coverage needs to have started.
     */
    public static final class TakePreciseCoverageResult extends CdpObject {
        private TakePreciseCoverageResult(Map<String, Object> values) { super(values); }
        @Nullable public static TakePreciseCoverageResult fromMap(@Nullable Map<String, Object> values) {
            return values == null ? null : new TakePreciseCoverageResult(values);
        }
        public static Builder builder() { return new Builder(); }
        /**
         * Coverage data for the current isolate.
         * @return the protocol field value
         */
        @Nullable public java.util.List<Profiler.ScriptCoverage> result() {
            return list(value("result"), element0 -> Profiler.ScriptCoverage.fromMap(objectMap(element0)));
        }
        /**
         * Monotonically increasing time (in seconds) when the coverage update was taken in the backend.
         * @return the protocol field value
         */
        @Nullable public Double timestamp() {
            return numberAsDouble(value("timestamp"));
        }
        public static final class Builder {
            private final Map<String, Object> values = new LinkedHashMap<>();
            /**
             * Coverage data for the current isolate.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder result(@Nullable java.util.List<Profiler.ScriptCoverage> value) {
                if (value == null) values.remove("result");
                else values.put("result", jsonValue(value));
                return this;
            }
            /**
             * Monotonically increasing time (in seconds) when the coverage update was taken in the backend.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder timestamp(@Nullable Double value) {
                if (value == null) values.remove("timestamp");
                else values.put("timestamp", jsonValue(value));
                return this;
            }
            public TakePreciseCoverageResult build() {
                if (!values.containsKey("result")) throw new IllegalStateException("Missing required CDP field: result");
                if (!values.containsKey("timestamp")) throw new IllegalStateException("Missing required CDP field: timestamp");
                return new TakePreciseCoverageResult(values);
            }
        }
    }
    /**
     * Payload of the Profiler.consoleProfileFinished event.
     */
    public static final class ConsoleProfileFinishedEvent extends CdpObject {
        private ConsoleProfileFinishedEvent(Map<String, Object> values) { super(values); }
        @Nullable public static ConsoleProfileFinishedEvent fromMap(@Nullable Map<String, Object> values) {
            return values == null ? null : new ConsoleProfileFinishedEvent(values);
        }
        public static Builder builder() { return new Builder(); }
        /**
         * Returns the id field.
         * @return the protocol field value
         */
        @Nullable public String id() {
            return (String) value("id");
        }
        /**
         * Location of console.profileEnd().
         * @return the protocol field value
         */
        @Nullable public Debugger.Location location() {
            return Debugger.Location.fromMap(objectMap(value("location")));
        }
        /**
         * Returns the profile field.
         * @return the protocol field value
         */
        @Nullable public Profiler.Profile profile() {
            return Profiler.Profile.fromMap(objectMap(value("profile")));
        }
        /**
         * Profile title passed as an argument to console.profile().
         * @return the protocol field value
         */
        @Nullable public String title() {
            return (String) value("title");
        }
        public static final class Builder {
            private final Map<String, Object> values = new LinkedHashMap<>();
            /**
             * Sets the id field.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder id(@Nullable String value) {
                if (value == null) values.remove("id");
                else values.put("id", jsonValue(value));
                return this;
            }
            /**
             * Location of console.profileEnd().
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder location(@Nullable Debugger.Location value) {
                if (value == null) values.remove("location");
                else values.put("location", jsonValue(value));
                return this;
            }
            /**
             * Sets the profile field.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder profile(@Nullable Profiler.Profile value) {
                if (value == null) values.remove("profile");
                else values.put("profile", jsonValue(value));
                return this;
            }
            /**
             * Profile title passed as an argument to console.profile().
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder title(@Nullable String value) {
                if (value == null) values.remove("title");
                else values.put("title", jsonValue(value));
                return this;
            }
            public ConsoleProfileFinishedEvent build() {
                if (!values.containsKey("id")) throw new IllegalStateException("Missing required CDP field: id");
                if (!values.containsKey("location")) throw new IllegalStateException("Missing required CDP field: location");
                if (!values.containsKey("profile")) throw new IllegalStateException("Missing required CDP field: profile");
                return new ConsoleProfileFinishedEvent(values);
            }
        }
    }
    /**
     * Sent when new profile recording is started using console.profile() call.
     */
    public static final class ConsoleProfileStartedEvent extends CdpObject {
        private ConsoleProfileStartedEvent(Map<String, Object> values) { super(values); }
        @Nullable public static ConsoleProfileStartedEvent fromMap(@Nullable Map<String, Object> values) {
            return values == null ? null : new ConsoleProfileStartedEvent(values);
        }
        public static Builder builder() { return new Builder(); }
        /**
         * Returns the id field.
         * @return the protocol field value
         */
        @Nullable public String id() {
            return (String) value("id");
        }
        /**
         * Location of console.profile().
         * @return the protocol field value
         */
        @Nullable public Debugger.Location location() {
            return Debugger.Location.fromMap(objectMap(value("location")));
        }
        /**
         * Profile title passed as an argument to console.profile().
         * @return the protocol field value
         */
        @Nullable public String title() {
            return (String) value("title");
        }
        public static final class Builder {
            private final Map<String, Object> values = new LinkedHashMap<>();
            /**
             * Sets the id field.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder id(@Nullable String value) {
                if (value == null) values.remove("id");
                else values.put("id", jsonValue(value));
                return this;
            }
            /**
             * Location of console.profile().
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder location(@Nullable Debugger.Location value) {
                if (value == null) values.remove("location");
                else values.put("location", jsonValue(value));
                return this;
            }
            /**
             * Profile title passed as an argument to console.profile().
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder title(@Nullable String value) {
                if (value == null) values.remove("title");
                else values.put("title", jsonValue(value));
                return this;
            }
            public ConsoleProfileStartedEvent build() {
                if (!values.containsKey("id")) throw new IllegalStateException("Missing required CDP field: id");
                if (!values.containsKey("location")) throw new IllegalStateException("Missing required CDP field: location");
                return new ConsoleProfileStartedEvent(values);
            }
        }
    }
    /**
     * Reports coverage delta since the last poll (either from an event like this, or from {@code takePreciseCoverage} for the current isolate. May only be sent if precise code coverage has been started. This event can be trigged by the embedder to, for example, trigger collection of coverage data immediately at a certain point in time.
     * <p><b>Experimental:</b> this part of CDP may change without notice.
     */
    public static final class PreciseCoverageDeltaUpdateEvent extends CdpObject {
        private PreciseCoverageDeltaUpdateEvent(Map<String, Object> values) { super(values); }
        @Nullable public static PreciseCoverageDeltaUpdateEvent fromMap(@Nullable Map<String, Object> values) {
            return values == null ? null : new PreciseCoverageDeltaUpdateEvent(values);
        }
        public static Builder builder() { return new Builder(); }
        /**
         * Monotonically increasing time (in seconds) when the coverage update was taken in the backend.
         * @return the protocol field value
         */
        @Nullable public Double timestamp() {
            return numberAsDouble(value("timestamp"));
        }
        /**
         * Identifier for distinguishing coverage events.
         * @return the protocol field value
         */
        @Nullable public String occasion() {
            return (String) value("occasion");
        }
        /**
         * Coverage data for the current isolate.
         * @return the protocol field value
         */
        @Nullable public java.util.List<Profiler.ScriptCoverage> result() {
            return list(value("result"), element0 -> Profiler.ScriptCoverage.fromMap(objectMap(element0)));
        }
        public static final class Builder {
            private final Map<String, Object> values = new LinkedHashMap<>();
            /**
             * Monotonically increasing time (in seconds) when the coverage update was taken in the backend.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder timestamp(@Nullable Double value) {
                if (value == null) values.remove("timestamp");
                else values.put("timestamp", jsonValue(value));
                return this;
            }
            /**
             * Identifier for distinguishing coverage events.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder occasion(@Nullable String value) {
                if (value == null) values.remove("occasion");
                else values.put("occasion", jsonValue(value));
                return this;
            }
            /**
             * Coverage data for the current isolate.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder result(@Nullable java.util.List<Profiler.ScriptCoverage> value) {
                if (value == null) values.remove("result");
                else values.put("result", jsonValue(value));
                return this;
            }
            public PreciseCoverageDeltaUpdateEvent build() {
                if (!values.containsKey("timestamp")) throw new IllegalStateException("Missing required CDP field: timestamp");
                if (!values.containsKey("occasion")) throw new IllegalStateException("Missing required CDP field: occasion");
                if (!values.containsKey("result")) throw new IllegalStateException("Missing required CDP field: result");
                return new PreciseCoverageDeltaUpdateEvent(values);
            }
        }
    }
    public static final class Client {
        private final CdpClient client;
        public Client(CdpClient client) { this.client = client; }
        /**
         * Invokes Profiler.disable.
         * @return a stage completing with the command result
         */
        public CompletionStage<DisableResult> disable() {
            return client.call("Profiler.disable", null, DisableResult::fromMap);
        }
        /**
         * Invokes Profiler.enable.
         * @return a stage completing with the command result
         */
        public CompletionStage<EnableResult> enable() {
            return client.call("Profiler.enable", null, EnableResult::fromMap);
        }
        /**
         * Collect coverage data for the current isolate. The coverage data may be incomplete due to garbage collection.
         * @return a stage completing with the command result
         */
        public CompletionStage<GetBestEffortCoverageResult> getBestEffortCoverage() {
            return client.call("Profiler.getBestEffortCoverage", null, GetBestEffortCoverageResult::fromMap);
        }
        /**
         * Changes CPU profiler sampling interval. Must be called before CPU profiles recording started.
         * @param params command parameters
         * @return a stage completing with the command result
         */
        public CompletionStage<SetSamplingIntervalResult> setSamplingInterval(SetSamplingIntervalParams params) {
            return client.call("Profiler.setSamplingInterval", params, SetSamplingIntervalResult::fromMap);
        }
        /**
         * Invokes Profiler.start.
         * @return a stage completing with the command result
         */
        public CompletionStage<StartResult> start() {
            return client.call("Profiler.start", null, StartResult::fromMap);
        }
        /**
         * Enable precise code coverage. Coverage data for JavaScript executed before enabling precise code coverage may be incomplete. Enabling prevents running optimized code and resets execution counters.
         * @param params command parameters
         * @return a stage completing with the command result
         */
        public CompletionStage<StartPreciseCoverageResult> startPreciseCoverage(StartPreciseCoverageParams params) {
            return client.call("Profiler.startPreciseCoverage", params, StartPreciseCoverageResult::fromMap);
        }
        /**
         * Invokes Profiler.stop.
         * @return a stage completing with the command result
         */
        public CompletionStage<StopResult> stop() {
            return client.call("Profiler.stop", null, StopResult::fromMap);
        }
        /**
         * Disable precise code coverage. Disabling releases unnecessary execution count records and allows executing optimized code.
         * @return a stage completing with the command result
         */
        public CompletionStage<StopPreciseCoverageResult> stopPreciseCoverage() {
            return client.call("Profiler.stopPreciseCoverage", null, StopPreciseCoverageResult::fromMap);
        }
        /**
         * Collect coverage data for the current isolate, and resets execution counters. Precise code coverage needs to have started.
         * @return a stage completing with the command result
         */
        public CompletionStage<TakePreciseCoverageResult> takePreciseCoverage() {
            return client.call("Profiler.takePreciseCoverage", null, TakePreciseCoverageResult::fromMap);
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
