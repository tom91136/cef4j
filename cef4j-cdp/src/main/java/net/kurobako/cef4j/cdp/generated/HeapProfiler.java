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
 * Chrome DevTools Protocol HeapProfiler domain.
 * <p><b>Experimental:</b> this part of CDP may change without notice.
 * @see <a href="https://chromium.googlesource.com/v8/v8/+/0e999a528db40a3ef6fa917adf96370a18b87d70/include/js_protocol.pdl">Pinned protocol source</a>
 */
@SuppressWarnings({"JavaLangClash", "UnusedMethod"})
public final class HeapProfiler {
    private HeapProfiler() {}
    @Nullable private static Long numberAsLong(@Nullable Object value) { return value == null ? null : ((Number) value).longValue(); }
    @Nullable private static Double numberAsDouble(@Nullable Object value) { return value == null ? null : ((Number) value).doubleValue(); }
    /**
     * Sampling Heap Profile node. Holds callsite information, allocation statistics and child nodes.
     */
    public static final class SamplingHeapProfileNode extends CdpObject {
        private SamplingHeapProfileNode(Map<String, Object> values) { super(values); }
        @Nullable public static SamplingHeapProfileNode fromMap(@Nullable Map<String, Object> values) {
            return values == null ? null : new SamplingHeapProfileNode(values);
        }
        public static Builder builder() { return new Builder(); }
        /**
         * Function location.
         * @return the protocol field value
         */
        @Nullable public Runtime.CallFrame callFrame() {
            return Runtime.CallFrame.fromMap(objectMap(value("callFrame")));
        }
        /**
         * Allocations size in bytes for the node excluding children.
         * @return the protocol field value
         */
        @Nullable public Double selfSize() {
            return numberAsDouble(value("selfSize"));
        }
        /**
         * Node id. Ids are unique across all profiles collected between startSampling and stopSampling.
         * @return the protocol field value
         */
        @Nullable public Long id() {
            return numberAsLong(value("id"));
        }
        /**
         * Child nodes.
         * @return the protocol field value
         */
        @Nullable public java.util.List<HeapProfiler.SamplingHeapProfileNode> children() {
            return list(value("children"), element0 -> HeapProfiler.SamplingHeapProfileNode.fromMap(objectMap(element0)));
        }
        public static final class Builder {
            private final Map<String, Object> values = new LinkedHashMap<>();
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
             * Allocations size in bytes for the node excluding children.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder selfSize(@Nullable Double value) {
                if (value == null) values.remove("selfSize");
                else values.put("selfSize", jsonValue(value));
                return this;
            }
            /**
             * Node id. Ids are unique across all profiles collected between startSampling and stopSampling.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder id(@Nullable Long value) {
                if (value == null) values.remove("id");
                else values.put("id", jsonValue(value));
                return this;
            }
            /**
             * Child nodes.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder children(@Nullable java.util.List<HeapProfiler.SamplingHeapProfileNode> value) {
                if (value == null) values.remove("children");
                else values.put("children", jsonValue(value));
                return this;
            }
            public SamplingHeapProfileNode build() {
                if (!values.containsKey("callFrame")) throw new IllegalStateException("Missing required CDP field: callFrame");
                if (!values.containsKey("selfSize")) throw new IllegalStateException("Missing required CDP field: selfSize");
                if (!values.containsKey("id")) throw new IllegalStateException("Missing required CDP field: id");
                if (!values.containsKey("children")) throw new IllegalStateException("Missing required CDP field: children");
                return new SamplingHeapProfileNode(values);
            }
        }
    }
    /**
     * A single sample from a sampling profile.
     */
    public static final class SamplingHeapProfileSample extends CdpObject {
        private SamplingHeapProfileSample(Map<String, Object> values) { super(values); }
        @Nullable public static SamplingHeapProfileSample fromMap(@Nullable Map<String, Object> values) {
            return values == null ? null : new SamplingHeapProfileSample(values);
        }
        public static Builder builder() { return new Builder(); }
        /**
         * Allocation size in bytes attributed to the sample.
         * @return the protocol field value
         */
        @Nullable public Double size() {
            return numberAsDouble(value("size"));
        }
        /**
         * Id of the corresponding profile tree node.
         * @return the protocol field value
         */
        @Nullable public Long nodeId() {
            return numberAsLong(value("nodeId"));
        }
        /**
         * Time-ordered sample ordinal number. It is unique across all profiles retrieved between startSampling and stopSampling.
         * @return the protocol field value
         */
        @Nullable public Double ordinal() {
            return numberAsDouble(value("ordinal"));
        }
        public static final class Builder {
            private final Map<String, Object> values = new LinkedHashMap<>();
            /**
             * Allocation size in bytes attributed to the sample.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder size(@Nullable Double value) {
                if (value == null) values.remove("size");
                else values.put("size", jsonValue(value));
                return this;
            }
            /**
             * Id of the corresponding profile tree node.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder nodeId(@Nullable Long value) {
                if (value == null) values.remove("nodeId");
                else values.put("nodeId", jsonValue(value));
                return this;
            }
            /**
             * Time-ordered sample ordinal number. It is unique across all profiles retrieved between startSampling and stopSampling.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder ordinal(@Nullable Double value) {
                if (value == null) values.remove("ordinal");
                else values.put("ordinal", jsonValue(value));
                return this;
            }
            public SamplingHeapProfileSample build() {
                if (!values.containsKey("size")) throw new IllegalStateException("Missing required CDP field: size");
                if (!values.containsKey("nodeId")) throw new IllegalStateException("Missing required CDP field: nodeId");
                if (!values.containsKey("ordinal")) throw new IllegalStateException("Missing required CDP field: ordinal");
                return new SamplingHeapProfileSample(values);
            }
        }
    }
    /**
     * Sampling profile.
     */
    public static final class SamplingHeapProfile extends CdpObject {
        private SamplingHeapProfile(Map<String, Object> values) { super(values); }
        @Nullable public static SamplingHeapProfile fromMap(@Nullable Map<String, Object> values) {
            return values == null ? null : new SamplingHeapProfile(values);
        }
        public static Builder builder() { return new Builder(); }
        /**
         * Returns the head field.
         * @return the protocol field value
         */
        @Nullable public HeapProfiler.SamplingHeapProfileNode head() {
            return HeapProfiler.SamplingHeapProfileNode.fromMap(objectMap(value("head")));
        }
        /**
         * Returns the samples field.
         * @return the protocol field value
         */
        @Nullable public java.util.List<HeapProfiler.SamplingHeapProfileSample> samples() {
            return list(value("samples"), element0 -> HeapProfiler.SamplingHeapProfileSample.fromMap(objectMap(element0)));
        }
        public static final class Builder {
            private final Map<String, Object> values = new LinkedHashMap<>();
            /**
             * Sets the head field.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder head(@Nullable HeapProfiler.SamplingHeapProfileNode value) {
                if (value == null) values.remove("head");
                else values.put("head", jsonValue(value));
                return this;
            }
            /**
             * Sets the samples field.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder samples(@Nullable java.util.List<HeapProfiler.SamplingHeapProfileSample> value) {
                if (value == null) values.remove("samples");
                else values.put("samples", jsonValue(value));
                return this;
            }
            public SamplingHeapProfile build() {
                if (!values.containsKey("head")) throw new IllegalStateException("Missing required CDP field: head");
                if (!values.containsKey("samples")) throw new IllegalStateException("Missing required CDP field: samples");
                return new SamplingHeapProfile(values);
            }
        }
    }
    /**
     * Enables console to refer to the node with given id via $x (see Command Line API for more details $x functions).
     */
    public static final class AddInspectedHeapObjectParams extends CdpObject {
        private AddInspectedHeapObjectParams(Map<String, Object> values) { super(values); }
        @Nullable public static AddInspectedHeapObjectParams fromMap(@Nullable Map<String, Object> values) {
            return values == null ? null : new AddInspectedHeapObjectParams(values);
        }
        public static Builder builder() { return new Builder(); }
        /**
         * Heap snapshot object id to be accessible by means of $x command line API.
         * @return the protocol field value
         */
        @Nullable public String heapObjectId() {
            return (String) value("heapObjectId");
        }
        public static final class Builder {
            private final Map<String, Object> values = new LinkedHashMap<>();
            /**
             * Heap snapshot object id to be accessible by means of $x command line API.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder heapObjectId(@Nullable String value) {
                if (value == null) values.remove("heapObjectId");
                else values.put("heapObjectId", jsonValue(value));
                return this;
            }
            public AddInspectedHeapObjectParams build() {
                if (!values.containsKey("heapObjectId")) throw new IllegalStateException("Missing required CDP field: heapObjectId");
                return new AddInspectedHeapObjectParams(values);
            }
        }
    }
    /**
     * Enables console to refer to the node with given id via $x (see Command Line API for more details $x functions).
     */
    public static final class AddInspectedHeapObjectResult extends CdpObject {
        private AddInspectedHeapObjectResult(Map<String, Object> values) { super(values); }
        @Nullable public static AddInspectedHeapObjectResult fromMap(@Nullable Map<String, Object> values) {
            return values == null ? null : new AddInspectedHeapObjectResult(values);
        }
        public static Builder builder() { return new Builder(); }
        public static final class Builder {
            private final Map<String, Object> values = new LinkedHashMap<>();
            public AddInspectedHeapObjectResult build() {
                return new AddInspectedHeapObjectResult(values);
            }
        }
    }
    /**
     * Parameters for HeapProfiler.collectGarbage.
     */
    public static final class CollectGarbageParams extends CdpObject {
        private CollectGarbageParams(Map<String, Object> values) { super(values); }
        @Nullable public static CollectGarbageParams fromMap(@Nullable Map<String, Object> values) {
            return values == null ? null : new CollectGarbageParams(values);
        }
        public static Builder builder() { return new Builder(); }
        public static final class Builder {
            private final Map<String, Object> values = new LinkedHashMap<>();
            public CollectGarbageParams build() {
                return new CollectGarbageParams(values);
            }
        }
    }
    /**
     * Result of HeapProfiler.collectGarbage.
     */
    public static final class CollectGarbageResult extends CdpObject {
        private CollectGarbageResult(Map<String, Object> values) { super(values); }
        @Nullable public static CollectGarbageResult fromMap(@Nullable Map<String, Object> values) {
            return values == null ? null : new CollectGarbageResult(values);
        }
        public static Builder builder() { return new Builder(); }
        public static final class Builder {
            private final Map<String, Object> values = new LinkedHashMap<>();
            public CollectGarbageResult build() {
                return new CollectGarbageResult(values);
            }
        }
    }
    /**
     * Parameters for HeapProfiler.disable.
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
     * Result of HeapProfiler.disable.
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
     * Parameters for HeapProfiler.enable.
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
     * Result of HeapProfiler.enable.
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
     * Parameters for HeapProfiler.getHeapObjectId.
     */
    public static final class GetHeapObjectIdParams extends CdpObject {
        private GetHeapObjectIdParams(Map<String, Object> values) { super(values); }
        @Nullable public static GetHeapObjectIdParams fromMap(@Nullable Map<String, Object> values) {
            return values == null ? null : new GetHeapObjectIdParams(values);
        }
        public static Builder builder() { return new Builder(); }
        /**
         * Identifier of the object to get heap object id for.
         * @return the protocol field value
         */
        @Nullable public String objectId() {
            return (String) value("objectId");
        }
        public static final class Builder {
            private final Map<String, Object> values = new LinkedHashMap<>();
            /**
             * Identifier of the object to get heap object id for.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder objectId(@Nullable String value) {
                if (value == null) values.remove("objectId");
                else values.put("objectId", jsonValue(value));
                return this;
            }
            public GetHeapObjectIdParams build() {
                if (!values.containsKey("objectId")) throw new IllegalStateException("Missing required CDP field: objectId");
                return new GetHeapObjectIdParams(values);
            }
        }
    }
    /**
     * Result of HeapProfiler.getHeapObjectId.
     */
    public static final class GetHeapObjectIdResult extends CdpObject {
        private GetHeapObjectIdResult(Map<String, Object> values) { super(values); }
        @Nullable public static GetHeapObjectIdResult fromMap(@Nullable Map<String, Object> values) {
            return values == null ? null : new GetHeapObjectIdResult(values);
        }
        public static Builder builder() { return new Builder(); }
        /**
         * Id of the heap snapshot object corresponding to the passed remote object id.
         * @return the protocol field value
         */
        @Nullable public String heapSnapshotObjectId() {
            return (String) value("heapSnapshotObjectId");
        }
        public static final class Builder {
            private final Map<String, Object> values = new LinkedHashMap<>();
            /**
             * Id of the heap snapshot object corresponding to the passed remote object id.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder heapSnapshotObjectId(@Nullable String value) {
                if (value == null) values.remove("heapSnapshotObjectId");
                else values.put("heapSnapshotObjectId", jsonValue(value));
                return this;
            }
            public GetHeapObjectIdResult build() {
                if (!values.containsKey("heapSnapshotObjectId")) throw new IllegalStateException("Missing required CDP field: heapSnapshotObjectId");
                return new GetHeapObjectIdResult(values);
            }
        }
    }
    /**
     * Parameters for HeapProfiler.getObjectByHeapObjectId.
     */
    public static final class GetObjectByHeapObjectIdParams extends CdpObject {
        private GetObjectByHeapObjectIdParams(Map<String, Object> values) { super(values); }
        @Nullable public static GetObjectByHeapObjectIdParams fromMap(@Nullable Map<String, Object> values) {
            return values == null ? null : new GetObjectByHeapObjectIdParams(values);
        }
        public static Builder builder() { return new Builder(); }
        /**
         * Returns the objectId field.
         * @return the protocol field value
         */
        @Nullable public String objectId() {
            return (String) value("objectId");
        }
        /**
         * Symbolic group name that can be used to release multiple objects.
         * @return the protocol field value
         */
        @Nullable public String objectGroup() {
            return (String) value("objectGroup");
        }
        public static final class Builder {
            private final Map<String, Object> values = new LinkedHashMap<>();
            /**
             * Sets the objectId field.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder objectId(@Nullable String value) {
                if (value == null) values.remove("objectId");
                else values.put("objectId", jsonValue(value));
                return this;
            }
            /**
             * Symbolic group name that can be used to release multiple objects.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder objectGroup(@Nullable String value) {
                if (value == null) values.remove("objectGroup");
                else values.put("objectGroup", jsonValue(value));
                return this;
            }
            public GetObjectByHeapObjectIdParams build() {
                if (!values.containsKey("objectId")) throw new IllegalStateException("Missing required CDP field: objectId");
                return new GetObjectByHeapObjectIdParams(values);
            }
        }
    }
    /**
     * Result of HeapProfiler.getObjectByHeapObjectId.
     */
    public static final class GetObjectByHeapObjectIdResult extends CdpObject {
        private GetObjectByHeapObjectIdResult(Map<String, Object> values) { super(values); }
        @Nullable public static GetObjectByHeapObjectIdResult fromMap(@Nullable Map<String, Object> values) {
            return values == null ? null : new GetObjectByHeapObjectIdResult(values);
        }
        public static Builder builder() { return new Builder(); }
        /**
         * Evaluation result.
         * @return the protocol field value
         */
        @Nullable public Runtime.RemoteObject result() {
            return Runtime.RemoteObject.fromMap(objectMap(value("result")));
        }
        public static final class Builder {
            private final Map<String, Object> values = new LinkedHashMap<>();
            /**
             * Evaluation result.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder result(@Nullable Runtime.RemoteObject value) {
                if (value == null) values.remove("result");
                else values.put("result", jsonValue(value));
                return this;
            }
            public GetObjectByHeapObjectIdResult build() {
                if (!values.containsKey("result")) throw new IllegalStateException("Missing required CDP field: result");
                return new GetObjectByHeapObjectIdResult(values);
            }
        }
    }
    /**
     * Parameters for HeapProfiler.getSamplingProfile.
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
     * Result of HeapProfiler.getSamplingProfile.
     */
    public static final class GetSamplingProfileResult extends CdpObject {
        private GetSamplingProfileResult(Map<String, Object> values) { super(values); }
        @Nullable public static GetSamplingProfileResult fromMap(@Nullable Map<String, Object> values) {
            return values == null ? null : new GetSamplingProfileResult(values);
        }
        public static Builder builder() { return new Builder(); }
        /**
         * Return the sampling profile being collected.
         * @return the protocol field value
         */
        @Nullable public HeapProfiler.SamplingHeapProfile profile() {
            return HeapProfiler.SamplingHeapProfile.fromMap(objectMap(value("profile")));
        }
        public static final class Builder {
            private final Map<String, Object> values = new LinkedHashMap<>();
            /**
             * Return the sampling profile being collected.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder profile(@Nullable HeapProfiler.SamplingHeapProfile value) {
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
    /**
     * Parameters for HeapProfiler.startSampling.
     */
    public static final class StartSamplingParams extends CdpObject {
        private StartSamplingParams(Map<String, Object> values) { super(values); }
        @Nullable public static StartSamplingParams fromMap(@Nullable Map<String, Object> values) {
            return values == null ? null : new StartSamplingParams(values);
        }
        public static Builder builder() { return new Builder(); }
        /**
         * Average sample interval in bytes. Poisson distribution is used for the intervals. The default value is 32768 bytes.
         * @return the protocol field value
         */
        @Nullable public Double samplingInterval() {
            return numberAsDouble(value("samplingInterval"));
        }
        /**
         * Maximum stack depth. The default value is 128.
         * @return the protocol field value
         */
        @Nullable public Double stackDepth() {
            return numberAsDouble(value("stackDepth"));
        }
        /**
         * By default, the sampling heap profiler reports only objects which are still alive when the profile is returned via getSamplingProfile or stopSampling, which is useful for determining what functions contribute the most to steady-state memory usage. This flag instructs the sampling heap profiler to also include information about objects discarded by major GC, which will show which functions cause large temporary memory usage or long GC pauses.
         * @return the protocol field value
         */
        @Nullable public Boolean includeObjectsCollectedByMajorGC() {
            return (Boolean) value("includeObjectsCollectedByMajorGC");
        }
        /**
         * By default, the sampling heap profiler reports only objects which are still alive when the profile is returned via getSamplingProfile or stopSampling, which is useful for determining what functions contribute the most to steady-state memory usage. This flag instructs the sampling heap profiler to also include information about objects discarded by minor GC, which is useful when tuning a latency-sensitive application for minimal GC activity.
         * @return the protocol field value
         */
        @Nullable public Boolean includeObjectsCollectedByMinorGC() {
            return (Boolean) value("includeObjectsCollectedByMinorGC");
        }
        public static final class Builder {
            private final Map<String, Object> values = new LinkedHashMap<>();
            /**
             * Average sample interval in bytes. Poisson distribution is used for the intervals. The default value is 32768 bytes.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder samplingInterval(@Nullable Double value) {
                if (value == null) values.remove("samplingInterval");
                else values.put("samplingInterval", jsonValue(value));
                return this;
            }
            /**
             * Maximum stack depth. The default value is 128.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder stackDepth(@Nullable Double value) {
                if (value == null) values.remove("stackDepth");
                else values.put("stackDepth", jsonValue(value));
                return this;
            }
            /**
             * By default, the sampling heap profiler reports only objects which are still alive when the profile is returned via getSamplingProfile or stopSampling, which is useful for determining what functions contribute the most to steady-state memory usage. This flag instructs the sampling heap profiler to also include information about objects discarded by major GC, which will show which functions cause large temporary memory usage or long GC pauses.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder includeObjectsCollectedByMajorGC(@Nullable Boolean value) {
                if (value == null) values.remove("includeObjectsCollectedByMajorGC");
                else values.put("includeObjectsCollectedByMajorGC", jsonValue(value));
                return this;
            }
            /**
             * By default, the sampling heap profiler reports only objects which are still alive when the profile is returned via getSamplingProfile or stopSampling, which is useful for determining what functions contribute the most to steady-state memory usage. This flag instructs the sampling heap profiler to also include information about objects discarded by minor GC, which is useful when tuning a latency-sensitive application for minimal GC activity.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder includeObjectsCollectedByMinorGC(@Nullable Boolean value) {
                if (value == null) values.remove("includeObjectsCollectedByMinorGC");
                else values.put("includeObjectsCollectedByMinorGC", jsonValue(value));
                return this;
            }
            public StartSamplingParams build() {
                return new StartSamplingParams(values);
            }
        }
    }
    /**
     * Result of HeapProfiler.startSampling.
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
     * Parameters for HeapProfiler.startTrackingHeapObjects.
     */
    public static final class StartTrackingHeapObjectsParams extends CdpObject {
        private StartTrackingHeapObjectsParams(Map<String, Object> values) { super(values); }
        @Nullable public static StartTrackingHeapObjectsParams fromMap(@Nullable Map<String, Object> values) {
            return values == null ? null : new StartTrackingHeapObjectsParams(values);
        }
        public static Builder builder() { return new Builder(); }
        /**
         * Returns the trackAllocations field.
         * @return the protocol field value
         */
        @Nullable public Boolean trackAllocations() {
            return (Boolean) value("trackAllocations");
        }
        public static final class Builder {
            private final Map<String, Object> values = new LinkedHashMap<>();
            /**
             * Sets the trackAllocations field.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder trackAllocations(@Nullable Boolean value) {
                if (value == null) values.remove("trackAllocations");
                else values.put("trackAllocations", jsonValue(value));
                return this;
            }
            public StartTrackingHeapObjectsParams build() {
                return new StartTrackingHeapObjectsParams(values);
            }
        }
    }
    /**
     * Result of HeapProfiler.startTrackingHeapObjects.
     */
    public static final class StartTrackingHeapObjectsResult extends CdpObject {
        private StartTrackingHeapObjectsResult(Map<String, Object> values) { super(values); }
        @Nullable public static StartTrackingHeapObjectsResult fromMap(@Nullable Map<String, Object> values) {
            return values == null ? null : new StartTrackingHeapObjectsResult(values);
        }
        public static Builder builder() { return new Builder(); }
        public static final class Builder {
            private final Map<String, Object> values = new LinkedHashMap<>();
            public StartTrackingHeapObjectsResult build() {
                return new StartTrackingHeapObjectsResult(values);
            }
        }
    }
    /**
     * Parameters for HeapProfiler.stopSampling.
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
     * Result of HeapProfiler.stopSampling.
     */
    public static final class StopSamplingResult extends CdpObject {
        private StopSamplingResult(Map<String, Object> values) { super(values); }
        @Nullable public static StopSamplingResult fromMap(@Nullable Map<String, Object> values) {
            return values == null ? null : new StopSamplingResult(values);
        }
        public static Builder builder() { return new Builder(); }
        /**
         * Recorded sampling heap profile.
         * @return the protocol field value
         */
        @Nullable public HeapProfiler.SamplingHeapProfile profile() {
            return HeapProfiler.SamplingHeapProfile.fromMap(objectMap(value("profile")));
        }
        public static final class Builder {
            private final Map<String, Object> values = new LinkedHashMap<>();
            /**
             * Recorded sampling heap profile.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder profile(@Nullable HeapProfiler.SamplingHeapProfile value) {
                if (value == null) values.remove("profile");
                else values.put("profile", jsonValue(value));
                return this;
            }
            public StopSamplingResult build() {
                if (!values.containsKey("profile")) throw new IllegalStateException("Missing required CDP field: profile");
                return new StopSamplingResult(values);
            }
        }
    }
    /**
     * Parameters for HeapProfiler.stopTrackingHeapObjects.
     */
    public static final class StopTrackingHeapObjectsParams extends CdpObject {
        private StopTrackingHeapObjectsParams(Map<String, Object> values) { super(values); }
        @Nullable public static StopTrackingHeapObjectsParams fromMap(@Nullable Map<String, Object> values) {
            return values == null ? null : new StopTrackingHeapObjectsParams(values);
        }
        public static Builder builder() { return new Builder(); }
        /**
         * If true &#x27;reportHeapSnapshotProgress&#x27; events will be generated while snapshot is being taken when the tracking is stopped.
         * @return the protocol field value
         */
        @Nullable public Boolean reportProgress() {
            return (Boolean) value("reportProgress");
        }
        /**
         * Deprecated in favor of {@code exposeInternals}.
         * @return the protocol field value
         * @deprecated Deprecated by the Chromium DevTools Protocol.
         */
        @Deprecated
        @Nullable public Boolean treatGlobalObjectsAsRoots() {
            return (Boolean) value("treatGlobalObjectsAsRoots");
        }
        /**
         * If true, numerical values are included in the snapshot
         * @return the protocol field value
         */
        @Nullable public Boolean captureNumericValue() {
            return (Boolean) value("captureNumericValue");
        }
        /**
         * If true, exposes internals of the snapshot.
         * <p><b>Experimental:</b> this part of CDP may change without notice.
         * @return the protocol field value
         */
        @Nullable public Boolean exposeInternals() {
            return (Boolean) value("exposeInternals");
        }
        public static final class Builder {
            private final Map<String, Object> values = new LinkedHashMap<>();
            /**
             * If true &#x27;reportHeapSnapshotProgress&#x27; events will be generated while snapshot is being taken when the tracking is stopped.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder reportProgress(@Nullable Boolean value) {
                if (value == null) values.remove("reportProgress");
                else values.put("reportProgress", jsonValue(value));
                return this;
            }
            /**
             * Deprecated in favor of {@code exposeInternals}.
             * @param value field value; null removes an optional value
             * @return this builder
             * @deprecated Deprecated by the Chromium DevTools Protocol.
             */
            @Deprecated
            public Builder treatGlobalObjectsAsRoots(@Nullable Boolean value) {
                if (value == null) values.remove("treatGlobalObjectsAsRoots");
                else values.put("treatGlobalObjectsAsRoots", jsonValue(value));
                return this;
            }
            /**
             * If true, numerical values are included in the snapshot
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder captureNumericValue(@Nullable Boolean value) {
                if (value == null) values.remove("captureNumericValue");
                else values.put("captureNumericValue", jsonValue(value));
                return this;
            }
            /**
             * If true, exposes internals of the snapshot.
             * <p><b>Experimental:</b> this part of CDP may change without notice.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder exposeInternals(@Nullable Boolean value) {
                if (value == null) values.remove("exposeInternals");
                else values.put("exposeInternals", jsonValue(value));
                return this;
            }
            public StopTrackingHeapObjectsParams build() {
                return new StopTrackingHeapObjectsParams(values);
            }
        }
    }
    /**
     * Result of HeapProfiler.stopTrackingHeapObjects.
     */
    public static final class StopTrackingHeapObjectsResult extends CdpObject {
        private StopTrackingHeapObjectsResult(Map<String, Object> values) { super(values); }
        @Nullable public static StopTrackingHeapObjectsResult fromMap(@Nullable Map<String, Object> values) {
            return values == null ? null : new StopTrackingHeapObjectsResult(values);
        }
        public static Builder builder() { return new Builder(); }
        public static final class Builder {
            private final Map<String, Object> values = new LinkedHashMap<>();
            public StopTrackingHeapObjectsResult build() {
                return new StopTrackingHeapObjectsResult(values);
            }
        }
    }
    /**
     * Parameters for HeapProfiler.takeHeapSnapshot.
     */
    public static final class TakeHeapSnapshotParams extends CdpObject {
        private TakeHeapSnapshotParams(Map<String, Object> values) { super(values); }
        @Nullable public static TakeHeapSnapshotParams fromMap(@Nullable Map<String, Object> values) {
            return values == null ? null : new TakeHeapSnapshotParams(values);
        }
        public static Builder builder() { return new Builder(); }
        /**
         * If true &#x27;reportHeapSnapshotProgress&#x27; events will be generated while snapshot is being taken.
         * @return the protocol field value
         */
        @Nullable public Boolean reportProgress() {
            return (Boolean) value("reportProgress");
        }
        /**
         * If true, a raw snapshot without artificial roots will be generated. Deprecated in favor of {@code exposeInternals}.
         * @return the protocol field value
         * @deprecated Deprecated by the Chromium DevTools Protocol.
         */
        @Deprecated
        @Nullable public Boolean treatGlobalObjectsAsRoots() {
            return (Boolean) value("treatGlobalObjectsAsRoots");
        }
        /**
         * If true, numerical values are included in the snapshot
         * @return the protocol field value
         */
        @Nullable public Boolean captureNumericValue() {
            return (Boolean) value("captureNumericValue");
        }
        /**
         * If true, exposes internals of the snapshot.
         * <p><b>Experimental:</b> this part of CDP may change without notice.
         * @return the protocol field value
         */
        @Nullable public Boolean exposeInternals() {
            return (Boolean) value("exposeInternals");
        }
        public static final class Builder {
            private final Map<String, Object> values = new LinkedHashMap<>();
            /**
             * If true &#x27;reportHeapSnapshotProgress&#x27; events will be generated while snapshot is being taken.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder reportProgress(@Nullable Boolean value) {
                if (value == null) values.remove("reportProgress");
                else values.put("reportProgress", jsonValue(value));
                return this;
            }
            /**
             * If true, a raw snapshot without artificial roots will be generated. Deprecated in favor of {@code exposeInternals}.
             * @param value field value; null removes an optional value
             * @return this builder
             * @deprecated Deprecated by the Chromium DevTools Protocol.
             */
            @Deprecated
            public Builder treatGlobalObjectsAsRoots(@Nullable Boolean value) {
                if (value == null) values.remove("treatGlobalObjectsAsRoots");
                else values.put("treatGlobalObjectsAsRoots", jsonValue(value));
                return this;
            }
            /**
             * If true, numerical values are included in the snapshot
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder captureNumericValue(@Nullable Boolean value) {
                if (value == null) values.remove("captureNumericValue");
                else values.put("captureNumericValue", jsonValue(value));
                return this;
            }
            /**
             * If true, exposes internals of the snapshot.
             * <p><b>Experimental:</b> this part of CDP may change without notice.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder exposeInternals(@Nullable Boolean value) {
                if (value == null) values.remove("exposeInternals");
                else values.put("exposeInternals", jsonValue(value));
                return this;
            }
            public TakeHeapSnapshotParams build() {
                return new TakeHeapSnapshotParams(values);
            }
        }
    }
    /**
     * Result of HeapProfiler.takeHeapSnapshot.
     */
    public static final class TakeHeapSnapshotResult extends CdpObject {
        private TakeHeapSnapshotResult(Map<String, Object> values) { super(values); }
        @Nullable public static TakeHeapSnapshotResult fromMap(@Nullable Map<String, Object> values) {
            return values == null ? null : new TakeHeapSnapshotResult(values);
        }
        public static Builder builder() { return new Builder(); }
        public static final class Builder {
            private final Map<String, Object> values = new LinkedHashMap<>();
            public TakeHeapSnapshotResult build() {
                return new TakeHeapSnapshotResult(values);
            }
        }
    }
    /**
     * Payload of the HeapProfiler.addHeapSnapshotChunk event.
     */
    public static final class AddHeapSnapshotChunkEvent extends CdpObject {
        private AddHeapSnapshotChunkEvent(Map<String, Object> values) { super(values); }
        @Nullable public static AddHeapSnapshotChunkEvent fromMap(@Nullable Map<String, Object> values) {
            return values == null ? null : new AddHeapSnapshotChunkEvent(values);
        }
        public static Builder builder() { return new Builder(); }
        /**
         * Returns the chunk field.
         * @return the protocol field value
         */
        @Nullable public String chunk() {
            return (String) value("chunk");
        }
        public static final class Builder {
            private final Map<String, Object> values = new LinkedHashMap<>();
            /**
             * Sets the chunk field.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder chunk(@Nullable String value) {
                if (value == null) values.remove("chunk");
                else values.put("chunk", jsonValue(value));
                return this;
            }
            public AddHeapSnapshotChunkEvent build() {
                if (!values.containsKey("chunk")) throw new IllegalStateException("Missing required CDP field: chunk");
                return new AddHeapSnapshotChunkEvent(values);
            }
        }
    }
    /**
     * If heap objects tracking has been started then backend may send update for one or more fragments
     */
    public static final class HeapStatsUpdateEvent extends CdpObject {
        private HeapStatsUpdateEvent(Map<String, Object> values) { super(values); }
        @Nullable public static HeapStatsUpdateEvent fromMap(@Nullable Map<String, Object> values) {
            return values == null ? null : new HeapStatsUpdateEvent(values);
        }
        public static Builder builder() { return new Builder(); }
        /**
         * An array of triplets. Each triplet describes a fragment. The first integer is the fragment index, the second integer is a total count of objects for the fragment, the third integer is a total size of the objects for the fragment.
         * @return the protocol field value
         */
        @Nullable public java.util.List<Long> statsUpdate() {
            return list(value("statsUpdate"), element0 -> numberAsLong(element0));
        }
        public static final class Builder {
            private final Map<String, Object> values = new LinkedHashMap<>();
            /**
             * An array of triplets. Each triplet describes a fragment. The first integer is the fragment index, the second integer is a total count of objects for the fragment, the third integer is a total size of the objects for the fragment.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder statsUpdate(@Nullable java.util.List<Long> value) {
                if (value == null) values.remove("statsUpdate");
                else values.put("statsUpdate", jsonValue(value));
                return this;
            }
            public HeapStatsUpdateEvent build() {
                if (!values.containsKey("statsUpdate")) throw new IllegalStateException("Missing required CDP field: statsUpdate");
                return new HeapStatsUpdateEvent(values);
            }
        }
    }
    /**
     * If heap objects tracking has been started then backend regularly sends a current value for last seen object id and corresponding timestamp. If the were changes in the heap since last event then one or more heapStatsUpdate events will be sent before a new lastSeenObjectId event.
     */
    public static final class LastSeenObjectIdEvent extends CdpObject {
        private LastSeenObjectIdEvent(Map<String, Object> values) { super(values); }
        @Nullable public static LastSeenObjectIdEvent fromMap(@Nullable Map<String, Object> values) {
            return values == null ? null : new LastSeenObjectIdEvent(values);
        }
        public static Builder builder() { return new Builder(); }
        /**
         * Returns the lastSeenObjectId field.
         * @return the protocol field value
         */
        @Nullable public Long lastSeenObjectId() {
            return numberAsLong(value("lastSeenObjectId"));
        }
        /**
         * Returns the timestamp field.
         * @return the protocol field value
         */
        @Nullable public Double timestamp() {
            return numberAsDouble(value("timestamp"));
        }
        public static final class Builder {
            private final Map<String, Object> values = new LinkedHashMap<>();
            /**
             * Sets the lastSeenObjectId field.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder lastSeenObjectId(@Nullable Long value) {
                if (value == null) values.remove("lastSeenObjectId");
                else values.put("lastSeenObjectId", jsonValue(value));
                return this;
            }
            /**
             * Sets the timestamp field.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder timestamp(@Nullable Double value) {
                if (value == null) values.remove("timestamp");
                else values.put("timestamp", jsonValue(value));
                return this;
            }
            public LastSeenObjectIdEvent build() {
                if (!values.containsKey("lastSeenObjectId")) throw new IllegalStateException("Missing required CDP field: lastSeenObjectId");
                if (!values.containsKey("timestamp")) throw new IllegalStateException("Missing required CDP field: timestamp");
                return new LastSeenObjectIdEvent(values);
            }
        }
    }
    /**
     * Payload of the HeapProfiler.reportHeapSnapshotProgress event.
     */
    public static final class ReportHeapSnapshotProgressEvent extends CdpObject {
        private ReportHeapSnapshotProgressEvent(Map<String, Object> values) { super(values); }
        @Nullable public static ReportHeapSnapshotProgressEvent fromMap(@Nullable Map<String, Object> values) {
            return values == null ? null : new ReportHeapSnapshotProgressEvent(values);
        }
        public static Builder builder() { return new Builder(); }
        /**
         * Returns the done field.
         * @return the protocol field value
         */
        @Nullable public Long done() {
            return numberAsLong(value("done"));
        }
        /**
         * Returns the total field.
         * @return the protocol field value
         */
        @Nullable public Long total() {
            return numberAsLong(value("total"));
        }
        /**
         * Returns the finished field.
         * @return the protocol field value
         */
        @Nullable public Boolean finished() {
            return (Boolean) value("finished");
        }
        public static final class Builder {
            private final Map<String, Object> values = new LinkedHashMap<>();
            /**
             * Sets the done field.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder done(@Nullable Long value) {
                if (value == null) values.remove("done");
                else values.put("done", jsonValue(value));
                return this;
            }
            /**
             * Sets the total field.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder total(@Nullable Long value) {
                if (value == null) values.remove("total");
                else values.put("total", jsonValue(value));
                return this;
            }
            /**
             * Sets the finished field.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder finished(@Nullable Boolean value) {
                if (value == null) values.remove("finished");
                else values.put("finished", jsonValue(value));
                return this;
            }
            public ReportHeapSnapshotProgressEvent build() {
                if (!values.containsKey("done")) throw new IllegalStateException("Missing required CDP field: done");
                if (!values.containsKey("total")) throw new IllegalStateException("Missing required CDP field: total");
                return new ReportHeapSnapshotProgressEvent(values);
            }
        }
    }
    /**
     * Payload of the HeapProfiler.resetProfiles event.
     */
    public static final class ResetProfilesEvent extends CdpObject {
        private ResetProfilesEvent(Map<String, Object> values) { super(values); }
        @Nullable public static ResetProfilesEvent fromMap(@Nullable Map<String, Object> values) {
            return values == null ? null : new ResetProfilesEvent(values);
        }
        public static Builder builder() { return new Builder(); }
        public static final class Builder {
            private final Map<String, Object> values = new LinkedHashMap<>();
            public ResetProfilesEvent build() {
                return new ResetProfilesEvent(values);
            }
        }
    }
    public static final class Client {
        private final CdpClient client;
        public Client(CdpClient client) { this.client = client; }
        /**
         * Enables console to refer to the node with given id via $x (see Command Line API for more details $x functions).
         * @param params command parameters
         * @return a stage completing with the command result
         */
        public CompletionStage<AddInspectedHeapObjectResult> addInspectedHeapObject(AddInspectedHeapObjectParams params) {
            return client.call("HeapProfiler.addInspectedHeapObject", params, AddInspectedHeapObjectResult::fromMap);
        }
        /**
         * Invokes HeapProfiler.collectGarbage.
         * @return a stage completing with the command result
         */
        public CompletionStage<CollectGarbageResult> collectGarbage() {
            return client.call("HeapProfiler.collectGarbage", null, CollectGarbageResult::fromMap);
        }
        /**
         * Invokes HeapProfiler.disable.
         * @return a stage completing with the command result
         */
        public CompletionStage<DisableResult> disable() {
            return client.call("HeapProfiler.disable", null, DisableResult::fromMap);
        }
        /**
         * Invokes HeapProfiler.enable.
         * @return a stage completing with the command result
         */
        public CompletionStage<EnableResult> enable() {
            return client.call("HeapProfiler.enable", null, EnableResult::fromMap);
        }
        /**
         * Invokes HeapProfiler.getHeapObjectId.
         * @param params command parameters
         * @return a stage completing with the command result
         */
        public CompletionStage<GetHeapObjectIdResult> getHeapObjectId(GetHeapObjectIdParams params) {
            return client.call("HeapProfiler.getHeapObjectId", params, GetHeapObjectIdResult::fromMap);
        }
        /**
         * Invokes HeapProfiler.getObjectByHeapObjectId.
         * @param params command parameters
         * @return a stage completing with the command result
         */
        public CompletionStage<GetObjectByHeapObjectIdResult> getObjectByHeapObjectId(GetObjectByHeapObjectIdParams params) {
            return client.call("HeapProfiler.getObjectByHeapObjectId", params, GetObjectByHeapObjectIdResult::fromMap);
        }
        /**
         * Invokes HeapProfiler.getSamplingProfile.
         * @return a stage completing with the command result
         */
        public CompletionStage<GetSamplingProfileResult> getSamplingProfile() {
            return client.call("HeapProfiler.getSamplingProfile", null, GetSamplingProfileResult::fromMap);
        }
        /**
         * Invokes HeapProfiler.startSampling.
         * @param params command parameters
         * @return a stage completing with the command result
         */
        public CompletionStage<StartSamplingResult> startSampling(StartSamplingParams params) {
            return client.call("HeapProfiler.startSampling", params, StartSamplingResult::fromMap);
        }
        /**
         * Invokes HeapProfiler.startTrackingHeapObjects.
         * @param params command parameters
         * @return a stage completing with the command result
         */
        public CompletionStage<StartTrackingHeapObjectsResult> startTrackingHeapObjects(StartTrackingHeapObjectsParams params) {
            return client.call("HeapProfiler.startTrackingHeapObjects", params, StartTrackingHeapObjectsResult::fromMap);
        }
        /**
         * Invokes HeapProfiler.stopSampling.
         * @return a stage completing with the command result
         */
        public CompletionStage<StopSamplingResult> stopSampling() {
            return client.call("HeapProfiler.stopSampling", null, StopSamplingResult::fromMap);
        }
        /**
         * Invokes HeapProfiler.stopTrackingHeapObjects.
         * @param params command parameters
         * @return a stage completing with the command result
         */
        public CompletionStage<StopTrackingHeapObjectsResult> stopTrackingHeapObjects(StopTrackingHeapObjectsParams params) {
            return client.call("HeapProfiler.stopTrackingHeapObjects", params, StopTrackingHeapObjectsResult::fromMap);
        }
        /**
         * Invokes HeapProfiler.takeHeapSnapshot.
         * @param params command parameters
         * @return a stage completing with the command result
         */
        public CompletionStage<TakeHeapSnapshotResult> takeHeapSnapshot(TakeHeapSnapshotParams params) {
            return client.call("HeapProfiler.takeHeapSnapshot", params, TakeHeapSnapshotResult::fromMap);
        }
        /**
         * Subscribes to HeapProfiler.addHeapSnapshotChunk.
         * @param handler event callback
         * @return a removable subscription
         */
        public CdpSubscription onAddHeapSnapshotChunk(Consumer<AddHeapSnapshotChunkEvent> handler) {
            return client.on("HeapProfiler.addHeapSnapshotChunk", AddHeapSnapshotChunkEvent::fromMap, handler);
        }
        /**
         * If heap objects tracking has been started then backend may send update for one or more fragments
         * @param handler event callback
         * @return a removable subscription
         */
        public CdpSubscription onHeapStatsUpdate(Consumer<HeapStatsUpdateEvent> handler) {
            return client.on("HeapProfiler.heapStatsUpdate", HeapStatsUpdateEvent::fromMap, handler);
        }
        /**
         * If heap objects tracking has been started then backend regularly sends a current value for last seen object id and corresponding timestamp. If the were changes in the heap since last event then one or more heapStatsUpdate events will be sent before a new lastSeenObjectId event.
         * @param handler event callback
         * @return a removable subscription
         */
        public CdpSubscription onLastSeenObjectId(Consumer<LastSeenObjectIdEvent> handler) {
            return client.on("HeapProfiler.lastSeenObjectId", LastSeenObjectIdEvent::fromMap, handler);
        }
        /**
         * Subscribes to HeapProfiler.reportHeapSnapshotProgress.
         * @param handler event callback
         * @return a removable subscription
         */
        public CdpSubscription onReportHeapSnapshotProgress(Consumer<ReportHeapSnapshotProgressEvent> handler) {
            return client.on("HeapProfiler.reportHeapSnapshotProgress", ReportHeapSnapshotProgressEvent::fromMap, handler);
        }
        /**
         * Subscribes to HeapProfiler.resetProfiles.
         * @param handler event callback
         * @return a removable subscription
         */
        public CdpSubscription onResetProfiles(Consumer<ResetProfilesEvent> handler) {
            return client.on("HeapProfiler.resetProfiles", ResetProfilesEvent::fromMap, handler);
        }
    }
}
