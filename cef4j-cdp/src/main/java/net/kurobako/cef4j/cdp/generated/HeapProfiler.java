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
 * Chrome DevTools Protocol HeapProfiler domain.
 * <p><b>Experimental:</b> this part of CDP may change without notice.
 * @see <a href="https://chromium.googlesource.com/v8/v8/+/3063ea3a0737a3fc4d4ed3babd595f1cace1e6ac/include/js_protocol.pdl">Pinned protocol source</a>
 */
@Generated("./mvnw generate-sources -pl cef4j-cdp -Dcef.version=150.0.18+gdb11278+chromium-150.0.7871.213")
@SuppressWarnings({"EscapedEntity", "InvalidParam", "JavaLangClash", "MissingSummary", "Unchecked", "UnusedMethod"})
@NullableBoundary("CDP wire objects use null for absent optional fields")
public final class HeapProfiler {
    private HeapProfiler() {}
    private static final CdpField<Runtime.CallFrame> CALL_FRAME = CdpField.of("callFrame", value -> java.util.Objects.requireNonNull(Runtime.CallFrame.fromMap(CdpObject.requireObjectMap(value))));
    private static final CdpField<java.util.List<HeapProfiler.SamplingHeapProfileNode>> CHILDREN = CdpField.of("children", value -> CdpObject.requireList(value, element0 -> java.util.Objects.requireNonNull(HeapProfiler.SamplingHeapProfileNode.fromMap(CdpObject.requireObjectMap(element0)))));
    private static final CdpField<HeapProfiler.SamplingHeapProfileNode> HEAD = CdpField.of("head", value -> java.util.Objects.requireNonNull(HeapProfiler.SamplingHeapProfileNode.fromMap(CdpObject.requireObjectMap(value))));
    private static final CdpField<HeapProfiler.HeapSnapshotObjectId> HEAP_OBJECT_ID = CdpField.of("heapObjectId", value -> new HeapProfiler.HeapSnapshotObjectId((String) value));
    private static final CdpField<HeapProfiler.HeapSnapshotObjectId> HEAP_SNAPSHOT_OBJECT_ID = CdpField.of("heapSnapshotObjectId", value -> new HeapProfiler.HeapSnapshotObjectId((String) value));
    private static final CdpField<Runtime.RemoteObjectId> OBJECT_ID = CdpField.of("objectId", value -> new Runtime.RemoteObjectId((String) value));
    private static final CdpField<HeapProfiler.HeapSnapshotObjectId> OBJECT_ID_2 = CdpField.of("objectId", value -> new HeapProfiler.HeapSnapshotObjectId((String) value));
    private static final CdpField<HeapProfiler.SamplingHeapProfile> PROFILE = CdpField.of("profile", value -> java.util.Objects.requireNonNull(HeapProfiler.SamplingHeapProfile.fromMap(CdpObject.requireObjectMap(value))));
    private static final CdpField<Runtime.RemoteObject> RESULT = CdpField.of("result", value -> java.util.Objects.requireNonNull(Runtime.RemoteObject.fromMap(CdpObject.requireObjectMap(value))));
    private static final CdpField<java.util.List<HeapProfiler.SamplingHeapProfileSample>> SAMPLES = CdpField.of("samples", value -> CdpObject.requireList(value, element0 -> java.util.Objects.requireNonNull(HeapProfiler.SamplingHeapProfileSample.fromMap(CdpObject.requireObjectMap(element0)))));
    private static final CdpField<java.util.List<Long>> STATS_UPDATE = CdpField.of("statsUpdate", value -> CdpObject.requireList(value, element0 -> ((Number) element0).longValue()));
    /**
     * Heap snapshot object id.
     */
    public static final class HeapSnapshotObjectId implements CdpValue<String> {
        public final String value;
        public HeapSnapshotObjectId(String value) { this.value = java.util.Objects.requireNonNull(value); }
        @Override public String value() { return value; }
        @Override public boolean equals(@org.jspecify.annotations.Nullable Object other) {
            if (this == other) return true;
            if (!(other instanceof HeapSnapshotObjectId)) return false;
            return value.equals(((HeapSnapshotObjectId) other).value);
        }
        @Override public int hashCode() { return value.hashCode(); }
        @Override public String toString() { return "HeapSnapshotObjectId(" + value + ")"; }
    }
    /**
     * Sampling Heap Profile node. Holds callsite information, allocation statistics and child nodes.
     */
    public static final class SamplingHeapProfileNode extends CdpObject {
        public SamplingHeapProfileNode() {}
        private SamplingHeapProfileNode(Map<String, Object> values) { super(values); }
        public static SamplingHeapProfileNode fromMap(Map<String, Object> values) {
            return new SamplingHeapProfileNode(values);
        }
        /**
         * Function location.
         * @return the protocol field value
         */
        public Runtime.CallFrame callFrame() {
            return require(CALL_FRAME);
        }
        /**
         * Allocations size in bytes for the node excluding children.
         * @return the protocol field value
         */
        public double selfSize() {
            return requireDouble("selfSize");
        }
        /**
         * Node id. Ids are unique across all profiles collected between startSampling and stopSampling.
         * @return the protocol field value
         */
        public long id() {
            return requireLong("id");
        }
        /**
         * Child nodes.
         * @return the protocol field value
         */
        public java.util.List<HeapProfiler.SamplingHeapProfileNode> children() {
            return require(CHILDREN);
        }
        /**
         * Function location.
         * @param callFrame field value
         * @return this model
         */
        public SamplingHeapProfileNode callFrame(Runtime.CallFrame callFrame) {
            set("callFrame", callFrame);
            return this;
        }
        /**
         * Allocations size in bytes for the node excluding children.
         * @param selfSize field value
         * @return this model
         */
        public SamplingHeapProfileNode selfSize(double selfSize) {
            set("selfSize", selfSize);
            return this;
        }
        /**
         * Node id. Ids are unique across all profiles collected between startSampling and stopSampling.
         * @param id field value
         * @return this model
         */
        public SamplingHeapProfileNode id(long id) {
            set("id", id);
            return this;
        }
        /**
         * Child nodes.
         * @param children field value
         * @return this model
         */
        public SamplingHeapProfileNode children(java.util.List<HeapProfiler.SamplingHeapProfileNode> children) {
            set("children", children);
            return this;
        }
    }
    /**
     * A single sample from a sampling profile.
     */
    public static final class SamplingHeapProfileSample extends CdpObject {
        public SamplingHeapProfileSample() {}
        private SamplingHeapProfileSample(Map<String, Object> values) { super(values); }
        public static SamplingHeapProfileSample fromMap(Map<String, Object> values) {
            return new SamplingHeapProfileSample(values);
        }
        /**
         * Allocation size in bytes attributed to the sample.
         * @return the protocol field value
         */
        public double size() {
            return requireDouble("size");
        }
        /**
         * Id of the corresponding profile tree node.
         * @return the protocol field value
         */
        public long nodeId() {
            return requireLong("nodeId");
        }
        /**
         * Time-ordered sample ordinal number. It is unique across all profiles retrieved between startSampling and stopSampling.
         * @return the protocol field value
         */
        public double ordinal() {
            return requireDouble("ordinal");
        }
        /**
         * Allocation size in bytes attributed to the sample.
         * @param size field value
         * @return this model
         */
        public SamplingHeapProfileSample size(double size) {
            set("size", size);
            return this;
        }
        /**
         * Id of the corresponding profile tree node.
         * @param nodeId field value
         * @return this model
         */
        public SamplingHeapProfileSample nodeId(long nodeId) {
            set("nodeId", nodeId);
            return this;
        }
        /**
         * Time-ordered sample ordinal number. It is unique across all profiles retrieved between startSampling and stopSampling.
         * @param ordinal field value
         * @return this model
         */
        public SamplingHeapProfileSample ordinal(double ordinal) {
            set("ordinal", ordinal);
            return this;
        }
    }
    /**
     * Sampling profile.
     */
    public static final class SamplingHeapProfile extends CdpObject {
        public SamplingHeapProfile() {}
        private SamplingHeapProfile(Map<String, Object> values) { super(values); }
        public static SamplingHeapProfile fromMap(Map<String, Object> values) {
            return new SamplingHeapProfile(values);
        }
        /**
         * Returns the head field.
         * @return the protocol field value
         */
        public HeapProfiler.SamplingHeapProfileNode head() {
            return require(HEAD);
        }
        /**
         * Returns the samples field.
         * @return the protocol field value
         */
        public java.util.List<HeapProfiler.SamplingHeapProfileSample> samples() {
            return require(SAMPLES);
        }
        /**
         * Sets the head field.
         * @param head field value
         * @return this model
         */
        public SamplingHeapProfile head(HeapProfiler.SamplingHeapProfileNode head) {
            set("head", head);
            return this;
        }
        /**
         * Sets the samples field.
         * @param samples field value
         * @return this model
         */
        public SamplingHeapProfile samples(java.util.List<HeapProfiler.SamplingHeapProfileSample> samples) {
            set("samples", samples);
            return this;
        }
    }
    /**
     * Enables console to refer to the node with given id via $x (see Command Line API for more details $x functions).
     */
    public static final class AddInspectedHeapObjectRequest extends CdpObject {
        public AddInspectedHeapObjectRequest() {}
        public static AddInspectedHeapObjectRequest fromMap(Map<String, Object> values) {
            final AddInspectedHeapObjectRequest instance_ = new AddInspectedHeapObjectRequest();
            if (values != null) instance_.values.putAll(values);
            return instance_;
        }
        /**
         * Heap snapshot object id to be accessible by means of $x command line API.
         * @return the protocol field value
         */
        public HeapProfiler.HeapSnapshotObjectId heapObjectId() {
            return require(HEAP_OBJECT_ID);
        }
        /**
         * Heap snapshot object id to be accessible by means of $x command line API.
         * @param heapObjectId field value
         * @return this model
         */
        public AddInspectedHeapObjectRequest heapObjectId(HeapProfiler.HeapSnapshotObjectId heapObjectId) {
            set("heapObjectId", heapObjectId);
            return this;
        }
    }
    /**
     * Request parameters for HeapProfiler.getHeapObjectId.
     */
    public static final class GetHeapObjectIdRequest extends CdpObject {
        public GetHeapObjectIdRequest() {}
        public static GetHeapObjectIdRequest fromMap(Map<String, Object> values) {
            final GetHeapObjectIdRequest instance_ = new GetHeapObjectIdRequest();
            if (values != null) instance_.values.putAll(values);
            return instance_;
        }
        /**
         * Identifier of the object to get heap object id for.
         * @return the protocol field value
         */
        public Runtime.RemoteObjectId objectId() {
            return require(OBJECT_ID);
        }
        /**
         * Identifier of the object to get heap object id for.
         * @param objectId field value
         * @return this model
         */
        public GetHeapObjectIdRequest objectId(Runtime.RemoteObjectId objectId) {
            set("objectId", objectId);
            return this;
        }
    }
    /**
     * Request parameters for HeapProfiler.getObjectByHeapObjectId.
     */
    public static final class GetObjectByHeapObjectIdRequest extends CdpObject {
        public GetObjectByHeapObjectIdRequest() {}
        public static GetObjectByHeapObjectIdRequest fromMap(Map<String, Object> values) {
            final GetObjectByHeapObjectIdRequest instance_ = new GetObjectByHeapObjectIdRequest();
            if (values != null) instance_.values.putAll(values);
            return instance_;
        }
        /**
         * Returns the objectId field.
         * @return the protocol field value
         */
        public HeapProfiler.HeapSnapshotObjectId objectId() {
            return require(OBJECT_ID_2);
        }
        /**
         * Symbolic group name that can be used to release multiple objects.
         * @return the protocol field value, empty when absent
         */
        public Optional<String> objectGroup() {
            return optionalString("objectGroup");
        }
        /**
         * Sets the objectId field.
         * @param objectId field value
         * @return this model
         */
        public GetObjectByHeapObjectIdRequest objectId(HeapProfiler.HeapSnapshotObjectId objectId) {
            set("objectId", objectId);
            return this;
        }
        /**
         * Symbolic group name that can be used to release multiple objects.
         * @param objectGroup field value
         * @return this model
         */
        public GetObjectByHeapObjectIdRequest objectGroup(String objectGroup) {
            set("objectGroup", objectGroup);
            return this;
        }
    }
    /**
     * Request parameters for HeapProfiler.startSampling.
     */
    public static final class StartSamplingRequest extends CdpObject {
        public StartSamplingRequest() {}
        public static StartSamplingRequest fromMap(Map<String, Object> values) {
            final StartSamplingRequest instance_ = new StartSamplingRequest();
            if (values != null) instance_.values.putAll(values);
            return instance_;
        }
        /**
         * Average sample interval in bytes. Poisson distribution is used for the intervals. The default value is 32768 bytes.
         * @return the protocol field value, empty when absent
         */
        public OptionalDouble samplingInterval() {
            return optionalDouble("samplingInterval");
        }
        /**
         * Maximum stack depth. The default value is 128.
         * @return the protocol field value, empty when absent
         */
        public OptionalDouble stackDepth() {
            return optionalDouble("stackDepth");
        }
        /**
         * By default, the sampling heap profiler reports only objects which are still alive when the profile is returned via getSamplingProfile or stopSampling, which is useful for determining what functions contribute the most to steady-state memory usage. This flag instructs the sampling heap profiler to also include information about objects discarded by major GC, which will show which functions cause large temporary memory usage or long GC pauses.
         * @return the protocol field value, empty when absent
         */
        public Optional<Boolean> includeObjectsCollectedByMajorGC() {
            return optionalBoolean("includeObjectsCollectedByMajorGC");
        }
        /**
         * By default, the sampling heap profiler reports only objects which are still alive when the profile is returned via getSamplingProfile or stopSampling, which is useful for determining what functions contribute the most to steady-state memory usage. This flag instructs the sampling heap profiler to also include information about objects discarded by minor GC, which is useful when tuning a latency-sensitive application for minimal GC activity.
         * @return the protocol field value, empty when absent
         */
        public Optional<Boolean> includeObjectsCollectedByMinorGC() {
            return optionalBoolean("includeObjectsCollectedByMinorGC");
        }
        /**
         * Average sample interval in bytes. Poisson distribution is used for the intervals. The default value is 32768 bytes.
         * @param samplingInterval field value
         * @return this model
         */
        public StartSamplingRequest samplingInterval(double samplingInterval) {
            set("samplingInterval", samplingInterval);
            return this;
        }
        /**
         * Maximum stack depth. The default value is 128.
         * @param stackDepth field value
         * @return this model
         */
        public StartSamplingRequest stackDepth(double stackDepth) {
            set("stackDepth", stackDepth);
            return this;
        }
        /**
         * By default, the sampling heap profiler reports only objects which are still alive when the profile is returned via getSamplingProfile or stopSampling, which is useful for determining what functions contribute the most to steady-state memory usage. This flag instructs the sampling heap profiler to also include information about objects discarded by major GC, which will show which functions cause large temporary memory usage or long GC pauses.
         * @param includeObjectsCollectedByMajorGC field value
         * @return this model
         */
        public StartSamplingRequest includeObjectsCollectedByMajorGC(boolean includeObjectsCollectedByMajorGC) {
            set("includeObjectsCollectedByMajorGC", includeObjectsCollectedByMajorGC);
            return this;
        }
        /**
         * By default, the sampling heap profiler reports only objects which are still alive when the profile is returned via getSamplingProfile or stopSampling, which is useful for determining what functions contribute the most to steady-state memory usage. This flag instructs the sampling heap profiler to also include information about objects discarded by minor GC, which is useful when tuning a latency-sensitive application for minimal GC activity.
         * @param includeObjectsCollectedByMinorGC field value
         * @return this model
         */
        public StartSamplingRequest includeObjectsCollectedByMinorGC(boolean includeObjectsCollectedByMinorGC) {
            set("includeObjectsCollectedByMinorGC", includeObjectsCollectedByMinorGC);
            return this;
        }
    }
    /**
     * Request parameters for HeapProfiler.startTrackingHeapObjects.
     */
    public static final class StartTrackingHeapObjectsRequest extends CdpObject {
        public StartTrackingHeapObjectsRequest() {}
        public static StartTrackingHeapObjectsRequest fromMap(Map<String, Object> values) {
            final StartTrackingHeapObjectsRequest instance_ = new StartTrackingHeapObjectsRequest();
            if (values != null) instance_.values.putAll(values);
            return instance_;
        }
        /**
         * Returns the trackAllocations field.
         * @return the protocol field value, empty when absent
         */
        public Optional<Boolean> trackAllocations() {
            return optionalBoolean("trackAllocations");
        }
        /**
         * Sets the trackAllocations field.
         * @param trackAllocations field value
         * @return this model
         */
        public StartTrackingHeapObjectsRequest trackAllocations(boolean trackAllocations) {
            set("trackAllocations", trackAllocations);
            return this;
        }
    }
    /**
     * Request parameters for HeapProfiler.stopTrackingHeapObjects.
     */
    public static final class StopTrackingHeapObjectsRequest extends CdpObject {
        public StopTrackingHeapObjectsRequest() {}
        public static StopTrackingHeapObjectsRequest fromMap(Map<String, Object> values) {
            final StopTrackingHeapObjectsRequest instance_ = new StopTrackingHeapObjectsRequest();
            if (values != null) instance_.values.putAll(values);
            return instance_;
        }
        /**
         * If true &#x27;reportHeapSnapshotProgress&#x27; events will be generated while snapshot is being taken when the tracking is stopped.
         * @return the protocol field value, empty when absent
         */
        public Optional<Boolean> reportProgress() {
            return optionalBoolean("reportProgress");
        }
        /**
         * Deprecated in favor of {@code exposeInternals}.
         * @return the protocol field value, empty when absent
         * @deprecated Deprecated by the Chromium DevTools Protocol.
         */
        @Deprecated
        public Optional<Boolean> treatGlobalObjectsAsRoots() {
            return optionalBoolean("treatGlobalObjectsAsRoots");
        }
        /**
         * If true, numerical values are included in the snapshot
         * @return the protocol field value, empty when absent
         */
        public Optional<Boolean> captureNumericValue() {
            return optionalBoolean("captureNumericValue");
        }
        /**
         * If true, exposes internals of the snapshot.
         * <p><b>Experimental:</b> this part of CDP may change without notice.
         * @return the protocol field value, empty when absent
         */
        public Optional<Boolean> exposeInternals() {
            return optionalBoolean("exposeInternals");
        }
        /**
         * If true &#x27;reportHeapSnapshotProgress&#x27; events will be generated while snapshot is being taken when the tracking is stopped.
         * @param reportProgress field value
         * @return this model
         */
        public StopTrackingHeapObjectsRequest reportProgress(boolean reportProgress) {
            set("reportProgress", reportProgress);
            return this;
        }
        /**
         * Deprecated in favor of {@code exposeInternals}.
         * @param treatGlobalObjectsAsRoots field value
         * @return this model
         * @deprecated Deprecated by the Chromium DevTools Protocol.
         */
        @Deprecated
        public StopTrackingHeapObjectsRequest treatGlobalObjectsAsRoots(boolean treatGlobalObjectsAsRoots) {
            set("treatGlobalObjectsAsRoots", treatGlobalObjectsAsRoots);
            return this;
        }
        /**
         * If true, numerical values are included in the snapshot
         * @param captureNumericValue field value
         * @return this model
         */
        public StopTrackingHeapObjectsRequest captureNumericValue(boolean captureNumericValue) {
            set("captureNumericValue", captureNumericValue);
            return this;
        }
        /**
         * If true, exposes internals of the snapshot.
         * <p><b>Experimental:</b> this part of CDP may change without notice.
         * @param exposeInternals field value
         * @return this model
         */
        public StopTrackingHeapObjectsRequest exposeInternals(boolean exposeInternals) {
            set("exposeInternals", exposeInternals);
            return this;
        }
    }
    /**
     * Request parameters for HeapProfiler.takeHeapSnapshot.
     */
    public static final class TakeHeapSnapshotRequest extends CdpObject {
        public TakeHeapSnapshotRequest() {}
        public static TakeHeapSnapshotRequest fromMap(Map<String, Object> values) {
            final TakeHeapSnapshotRequest instance_ = new TakeHeapSnapshotRequest();
            if (values != null) instance_.values.putAll(values);
            return instance_;
        }
        /**
         * If true &#x27;reportHeapSnapshotProgress&#x27; events will be generated while snapshot is being taken.
         * @return the protocol field value, empty when absent
         */
        public Optional<Boolean> reportProgress() {
            return optionalBoolean("reportProgress");
        }
        /**
         * If true, a raw snapshot without artificial roots will be generated. Deprecated in favor of {@code exposeInternals}.
         * @return the protocol field value, empty when absent
         * @deprecated Deprecated by the Chromium DevTools Protocol.
         */
        @Deprecated
        public Optional<Boolean> treatGlobalObjectsAsRoots() {
            return optionalBoolean("treatGlobalObjectsAsRoots");
        }
        /**
         * If true, numerical values are included in the snapshot
         * @return the protocol field value, empty when absent
         */
        public Optional<Boolean> captureNumericValue() {
            return optionalBoolean("captureNumericValue");
        }
        /**
         * If true, exposes internals of the snapshot.
         * <p><b>Experimental:</b> this part of CDP may change without notice.
         * @return the protocol field value, empty when absent
         */
        public Optional<Boolean> exposeInternals() {
            return optionalBoolean("exposeInternals");
        }
        /**
         * If true &#x27;reportHeapSnapshotProgress&#x27; events will be generated while snapshot is being taken.
         * @param reportProgress field value
         * @return this model
         */
        public TakeHeapSnapshotRequest reportProgress(boolean reportProgress) {
            set("reportProgress", reportProgress);
            return this;
        }
        /**
         * If true, a raw snapshot without artificial roots will be generated. Deprecated in favor of {@code exposeInternals}.
         * @param treatGlobalObjectsAsRoots field value
         * @return this model
         * @deprecated Deprecated by the Chromium DevTools Protocol.
         */
        @Deprecated
        public TakeHeapSnapshotRequest treatGlobalObjectsAsRoots(boolean treatGlobalObjectsAsRoots) {
            set("treatGlobalObjectsAsRoots", treatGlobalObjectsAsRoots);
            return this;
        }
        /**
         * If true, numerical values are included in the snapshot
         * @param captureNumericValue field value
         * @return this model
         */
        public TakeHeapSnapshotRequest captureNumericValue(boolean captureNumericValue) {
            set("captureNumericValue", captureNumericValue);
            return this;
        }
        /**
         * If true, exposes internals of the snapshot.
         * <p><b>Experimental:</b> this part of CDP may change without notice.
         * @param exposeInternals field value
         * @return this model
         */
        public TakeHeapSnapshotRequest exposeInternals(boolean exposeInternals) {
            set("exposeInternals", exposeInternals);
            return this;
        }
    }
    /**
     * Payload of the HeapProfiler.addHeapSnapshotChunk event.
     */
    public static final class AddHeapSnapshotChunkEvent extends CdpObject {
        public AddHeapSnapshotChunkEvent() {}
        private AddHeapSnapshotChunkEvent(Map<String, Object> values) { super(values); }
        public static AddHeapSnapshotChunkEvent fromMap(Map<String, Object> values) {
            return new AddHeapSnapshotChunkEvent(values);
        }
        /**
         * Returns the chunk field.
         * @return the protocol field value
         */
        public String chunk() {
            return requireString("chunk");
        }
        /**
         * Sets the chunk field.
         * @param chunk field value
         * @return this model
         */
        public AddHeapSnapshotChunkEvent chunk(String chunk) {
            set("chunk", chunk);
            return this;
        }
    }
    /**
     * If heap objects tracking has been started then backend may send update for one or more fragments
     */
    public static final class HeapStatsUpdateEvent extends CdpObject {
        public HeapStatsUpdateEvent() {}
        private HeapStatsUpdateEvent(Map<String, Object> values) { super(values); }
        public static HeapStatsUpdateEvent fromMap(Map<String, Object> values) {
            return new HeapStatsUpdateEvent(values);
        }
        /**
         * An array of triplets. Each triplet describes a fragment. The first integer is the fragment index, the second integer is a total count of objects for the fragment, the third integer is a total size of the objects for the fragment.
         * @return the protocol field value
         */
        public java.util.List<Long> statsUpdate() {
            return require(STATS_UPDATE);
        }
        /**
         * An array of triplets. Each triplet describes a fragment. The first integer is the fragment index, the second integer is a total count of objects for the fragment, the third integer is a total size of the objects for the fragment.
         * @param statsUpdate field value
         * @return this model
         */
        public HeapStatsUpdateEvent statsUpdate(java.util.List<Long> statsUpdate) {
            set("statsUpdate", statsUpdate);
            return this;
        }
    }
    /**
     * If heap objects tracking has been started then backend regularly sends a current value for last seen object id and corresponding timestamp. If the were changes in the heap since last event then one or more heapStatsUpdate events will be sent before a new lastSeenObjectId event.
     */
    public static final class LastSeenObjectIdEvent extends CdpObject {
        public LastSeenObjectIdEvent() {}
        private LastSeenObjectIdEvent(Map<String, Object> values) { super(values); }
        public static LastSeenObjectIdEvent fromMap(Map<String, Object> values) {
            return new LastSeenObjectIdEvent(values);
        }
        /**
         * Returns the lastSeenObjectId field.
         * @return the protocol field value
         */
        public long lastSeenObjectId() {
            return requireLong("lastSeenObjectId");
        }
        /**
         * Returns the timestamp field.
         * @return the protocol field value
         */
        public double timestamp() {
            return requireDouble("timestamp");
        }
        /**
         * Sets the lastSeenObjectId field.
         * @param lastSeenObjectId field value
         * @return this model
         */
        public LastSeenObjectIdEvent lastSeenObjectId(long lastSeenObjectId) {
            set("lastSeenObjectId", lastSeenObjectId);
            return this;
        }
        /**
         * Sets the timestamp field.
         * @param timestamp field value
         * @return this model
         */
        public LastSeenObjectIdEvent timestamp(double timestamp) {
            set("timestamp", timestamp);
            return this;
        }
    }
    /**
     * Payload of the HeapProfiler.reportHeapSnapshotProgress event.
     */
    public static final class ReportHeapSnapshotProgressEvent extends CdpObject {
        public ReportHeapSnapshotProgressEvent() {}
        private ReportHeapSnapshotProgressEvent(Map<String, Object> values) { super(values); }
        public static ReportHeapSnapshotProgressEvent fromMap(Map<String, Object> values) {
            return new ReportHeapSnapshotProgressEvent(values);
        }
        /**
         * Returns the done field.
         * @return the protocol field value
         */
        public long done() {
            return requireLong("done");
        }
        /**
         * Returns the total field.
         * @return the protocol field value
         */
        public long total() {
            return requireLong("total");
        }
        /**
         * Returns the finished field.
         * @return the protocol field value, empty when absent
         */
        public Optional<Boolean> finished() {
            return optionalBoolean("finished");
        }
        /**
         * Sets the done field.
         * @param done field value
         * @return this model
         */
        public ReportHeapSnapshotProgressEvent done(long done) {
            set("done", done);
            return this;
        }
        /**
         * Sets the total field.
         * @param total field value
         * @return this model
         */
        public ReportHeapSnapshotProgressEvent total(long total) {
            set("total", total);
            return this;
        }
        /**
         * Sets the finished field.
         * @param finished field value
         * @return this model
         */
        public ReportHeapSnapshotProgressEvent finished(boolean finished) {
            set("finished", finished);
            return this;
        }
    }
    /**
     * Payload of the HeapProfiler.resetProfiles event.
     */
    public static final class ResetProfilesEvent extends CdpObject {
        public ResetProfilesEvent() {}
        private ResetProfilesEvent(Map<String, Object> values) { super(values); }
        public static ResetProfilesEvent fromMap(Map<String, Object> values) {
            return new ResetProfilesEvent(values);
        }
    }
    public static final class Client extends CdpDomainClient {
        public Client(CdpClient client) { super(client); }
        /**
         * Enables console to refer to the node with given id via $x (see Command Line API for more details $x functions).
         * @param heapObjectId protocol value
         * @return a stage completing when the command completes
         */
        public CompletionStage<Void> addInspectedHeapObject(HeapProfiler.HeapSnapshotObjectId heapObjectId) {
            return call("HeapProfiler.addInspectedHeapObject", params("heapObjectId", heapObjectId));
        }
        /**
         * Enables console to refer to the node with given id via $x (see Command Line API for more details $x functions).
         * @param request request parameters
         * @return a stage completing when the command completes
         */
        public CompletionStage<Void> addInspectedHeapObject(AddInspectedHeapObjectRequest request) {
            return call("HeapProfiler.addInspectedHeapObject", request);
        }
        /**
         * Invokes HeapProfiler.collectGarbage.
         * @return a stage completing when the command completes
         */
        public CompletionStage<Void> collectGarbage() {
            return call("HeapProfiler.collectGarbage");
        }
        /**
         * Invokes HeapProfiler.disable.
         * @return a stage completing when the command completes
         */
        public CompletionStage<Void> disable() {
            return call("HeapProfiler.disable");
        }
        /**
         * Invokes HeapProfiler.enable.
         * @return a stage completing when the command completes
         */
        public CompletionStage<Void> enable() {
            return call("HeapProfiler.enable");
        }
        /**
         * Invokes HeapProfiler.getHeapObjectId.
         * @param objectId protocol value
         * @return a stage completing with the command result
         */
        public CompletionStage<HeapProfiler.HeapSnapshotObjectId> getHeapObjectId(Runtime.RemoteObjectId objectId) {
            return call("HeapProfiler.getHeapObjectId", params("objectId", objectId), HEAP_SNAPSHOT_OBJECT_ID::require);
        }
        /**
         * Invokes HeapProfiler.getHeapObjectId with a request object.
         * @param request request parameters
         * @return a stage completing with the command result
         */
        public CompletionStage<HeapProfiler.HeapSnapshotObjectId> getHeapObjectId(GetHeapObjectIdRequest request) {
            return call("HeapProfiler.getHeapObjectId", request, HEAP_SNAPSHOT_OBJECT_ID::require);
        }
        /**
         * Invokes HeapProfiler.getObjectByHeapObjectId.
         * @param objectId protocol value
         * @param objectGroup protocol value
         * @return a stage completing with the command result
         */
        public CompletionStage<Runtime.RemoteObject> getObjectByHeapObjectId(HeapProfiler.HeapSnapshotObjectId objectId, String objectGroup) {
            return call("HeapProfiler.getObjectByHeapObjectId", params("objectId", objectId, "objectGroup", objectGroup), RESULT::require);
        }
        /**
         * Invokes HeapProfiler.getObjectByHeapObjectId with the required parameters.
         * @param objectId protocol value
         * @return a stage completing with the command result
         */
        public CompletionStage<Runtime.RemoteObject> getObjectByHeapObjectId(HeapProfiler.HeapSnapshotObjectId objectId) {
            return call("HeapProfiler.getObjectByHeapObjectId", params("objectId", objectId), RESULT::require);
        }
        /**
         * Invokes HeapProfiler.getObjectByHeapObjectId with a request object.
         * @param request request parameters
         * @return a stage completing with the command result
         */
        public CompletionStage<Runtime.RemoteObject> getObjectByHeapObjectId(GetObjectByHeapObjectIdRequest request) {
            return call("HeapProfiler.getObjectByHeapObjectId", request, RESULT::require);
        }
        /**
         * Invokes HeapProfiler.getSamplingProfile.
         * @return a stage completing with the command result
         */
        public CompletionStage<HeapProfiler.SamplingHeapProfile> getSamplingProfile() {
            return call("HeapProfiler.getSamplingProfile", PROFILE::require);
        }
        /**
         * Invokes HeapProfiler.startSampling.
         * @param samplingInterval protocol value
         * @param stackDepth protocol value
         * @param includeObjectsCollectedByMajorGC protocol value
         * @param includeObjectsCollectedByMinorGC protocol value
         * @return a stage completing when the command completes
         */
        public CompletionStage<Void> startSampling(@Nullable Double samplingInterval, @Nullable Double stackDepth, @Nullable Boolean includeObjectsCollectedByMajorGC, @Nullable Boolean includeObjectsCollectedByMinorGC) {
            return call("HeapProfiler.startSampling", params("samplingInterval", samplingInterval, "stackDepth", stackDepth, "includeObjectsCollectedByMajorGC", includeObjectsCollectedByMajorGC, "includeObjectsCollectedByMinorGC", includeObjectsCollectedByMinorGC));
        }
        /**
         * Invokes HeapProfiler.startSampling with default parameters.
         * @return a stage completing when the command completes
         */
        public CompletionStage<Void> startSampling() {
            return call("HeapProfiler.startSampling");
        }
        /**
         * Invokes HeapProfiler.startSampling with a request object.
         * @param request request parameters
         * @return a stage completing when the command completes
         */
        public CompletionStage<Void> startSampling(StartSamplingRequest request) {
            return call("HeapProfiler.startSampling", request);
        }
        /**
         * Invokes HeapProfiler.startTrackingHeapObjects.
         * @param trackAllocations protocol value
         * @return a stage completing when the command completes
         */
        public CompletionStage<Void> startTrackingHeapObjects(boolean trackAllocations) {
            return call("HeapProfiler.startTrackingHeapObjects", params("trackAllocations", trackAllocations));
        }
        /**
         * Invokes HeapProfiler.startTrackingHeapObjects with default parameters.
         * @return a stage completing when the command completes
         */
        public CompletionStage<Void> startTrackingHeapObjects() {
            return call("HeapProfiler.startTrackingHeapObjects");
        }
        /**
         * Invokes HeapProfiler.startTrackingHeapObjects with a request object.
         * @param request request parameters
         * @return a stage completing when the command completes
         */
        public CompletionStage<Void> startTrackingHeapObjects(StartTrackingHeapObjectsRequest request) {
            return call("HeapProfiler.startTrackingHeapObjects", request);
        }
        /**
         * Invokes HeapProfiler.stopSampling.
         * @return a stage completing with the command result
         */
        public CompletionStage<HeapProfiler.SamplingHeapProfile> stopSampling() {
            return call("HeapProfiler.stopSampling", PROFILE::require);
        }
        /**
         * Invokes HeapProfiler.stopTrackingHeapObjects.
         * @param reportProgress protocol value
         * @param treatGlobalObjectsAsRoots protocol value
         * @param captureNumericValue protocol value
         * @param exposeInternals protocol value
         * @return a stage completing when the command completes
         */
        public CompletionStage<Void> stopTrackingHeapObjects(@Nullable Boolean reportProgress, @Nullable Boolean treatGlobalObjectsAsRoots, @Nullable Boolean captureNumericValue, @Nullable Boolean exposeInternals) {
            return call("HeapProfiler.stopTrackingHeapObjects", params("reportProgress", reportProgress, "treatGlobalObjectsAsRoots", treatGlobalObjectsAsRoots, "captureNumericValue", captureNumericValue, "exposeInternals", exposeInternals));
        }
        /**
         * Invokes HeapProfiler.stopTrackingHeapObjects with default parameters.
         * @return a stage completing when the command completes
         */
        public CompletionStage<Void> stopTrackingHeapObjects() {
            return call("HeapProfiler.stopTrackingHeapObjects");
        }
        /**
         * Invokes HeapProfiler.stopTrackingHeapObjects with a request object.
         * @param request request parameters
         * @return a stage completing when the command completes
         */
        public CompletionStage<Void> stopTrackingHeapObjects(StopTrackingHeapObjectsRequest request) {
            return call("HeapProfiler.stopTrackingHeapObjects", request);
        }
        /**
         * Invokes HeapProfiler.takeHeapSnapshot.
         * @param reportProgress protocol value
         * @param treatGlobalObjectsAsRoots protocol value
         * @param captureNumericValue protocol value
         * @param exposeInternals protocol value
         * @return a stage completing when the command completes
         */
        public CompletionStage<Void> takeHeapSnapshot(@Nullable Boolean reportProgress, @Nullable Boolean treatGlobalObjectsAsRoots, @Nullable Boolean captureNumericValue, @Nullable Boolean exposeInternals) {
            return call("HeapProfiler.takeHeapSnapshot", params("reportProgress", reportProgress, "treatGlobalObjectsAsRoots", treatGlobalObjectsAsRoots, "captureNumericValue", captureNumericValue, "exposeInternals", exposeInternals));
        }
        /**
         * Invokes HeapProfiler.takeHeapSnapshot with default parameters.
         * @return a stage completing when the command completes
         */
        public CompletionStage<Void> takeHeapSnapshot() {
            return call("HeapProfiler.takeHeapSnapshot");
        }
        /**
         * Invokes HeapProfiler.takeHeapSnapshot with a request object.
         * @param request request parameters
         * @return a stage completing when the command completes
         */
        public CompletionStage<Void> takeHeapSnapshot(TakeHeapSnapshotRequest request) {
            return call("HeapProfiler.takeHeapSnapshot", request);
        }
        /**
         * Subscribes to HeapProfiler.addHeapSnapshotChunk.
         * @param handler event callback
         * @return a removable subscription
         */
        public CdpSubscription onAddHeapSnapshotChunk(Consumer<AddHeapSnapshotChunkEvent> handler) {
            return on("HeapProfiler.addHeapSnapshotChunk", AddHeapSnapshotChunkEvent::fromMap, handler);
        }
        /**
         * If heap objects tracking has been started then backend may send update for one or more fragments
         * @param handler event callback
         * @return a removable subscription
         */
        public CdpSubscription onHeapStatsUpdate(Consumer<HeapStatsUpdateEvent> handler) {
            return on("HeapProfiler.heapStatsUpdate", HeapStatsUpdateEvent::fromMap, handler);
        }
        /**
         * If heap objects tracking has been started then backend regularly sends a current value for last seen object id and corresponding timestamp. If the were changes in the heap since last event then one or more heapStatsUpdate events will be sent before a new lastSeenObjectId event.
         * @param handler event callback
         * @return a removable subscription
         */
        public CdpSubscription onLastSeenObjectId(Consumer<LastSeenObjectIdEvent> handler) {
            return on("HeapProfiler.lastSeenObjectId", LastSeenObjectIdEvent::fromMap, handler);
        }
        /**
         * Subscribes to HeapProfiler.reportHeapSnapshotProgress.
         * @param handler event callback
         * @return a removable subscription
         */
        public CdpSubscription onReportHeapSnapshotProgress(Consumer<ReportHeapSnapshotProgressEvent> handler) {
            return on("HeapProfiler.reportHeapSnapshotProgress", ReportHeapSnapshotProgressEvent::fromMap, handler);
        }
        /**
         * Subscribes to HeapProfiler.resetProfiles.
         * @param handler event callback
         * @return a removable subscription
         */
        public CdpSubscription onResetProfiles(Consumer<ResetProfilesEvent> handler) {
            return on("HeapProfiler.resetProfiles", ResetProfilesEvent::fromMap, handler);
        }
    }
}
