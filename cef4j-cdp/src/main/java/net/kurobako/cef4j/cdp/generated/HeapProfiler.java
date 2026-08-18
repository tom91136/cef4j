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
 * Chrome DevTools Protocol HeapProfiler domain.
 * <p><b>Experimental:</b> this part of CDP may change without notice.
 * @see <a href="https://chromium.googlesource.com/v8/v8/+/3063ea3a0737a3fc4d4ed3babd595f1cace1e6ac/include/js_protocol.pdl">Pinned protocol source</a>
 */
@Generated("mvn generate-sources -pl cef4j-platform -Dcef.version=150.0.18+gdb11278+chromium-150.0.7871.213")
@SuppressWarnings({"EscapedEntity", "InvalidParam", "JavaLangClash", "MissingSummary", "Unchecked", "UnusedMethod"})
public final class HeapProfiler {
    private HeapProfiler() {}
    /**
     * Heap snapshot object id.
     */
    public static final class HeapSnapshotObjectId implements CdpValue<String> {
        public final String value;
        public HeapSnapshotObjectId(@Nonnull String value) { this.value = java.util.Objects.requireNonNull(value); }
        @Nonnull public String value() { return value; }
        @Override public boolean equals(Object other) {
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
            return java.util.Objects.requireNonNull(Runtime.CallFrame.fromMap(java.util.Objects.requireNonNull(CdpObject.objectMap(require("callFrame")))));
        }
        /**
         * Allocations size in bytes for the node excluding children.
         * @return the protocol field value
         */
        public double selfSize() {
            return ((Number) require("selfSize")).doubleValue();
        }
        /**
         * Node id. Ids are unique across all profiles collected between startSampling and stopSampling.
         * @return the protocol field value
         */
        public long id() {
            return ((Number) require("id")).longValue();
        }
        /**
         * Child nodes.
         * @return the protocol field value
         */
        public java.util.List<HeapProfiler.SamplingHeapProfileNode> children() {
            return CdpObject.requireList(require("children"), element0 -> java.util.Objects.requireNonNull(HeapProfiler.SamplingHeapProfileNode.fromMap(java.util.Objects.requireNonNull(CdpObject.objectMap(element0)))));
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
            return ((Number) require("size")).doubleValue();
        }
        /**
         * Id of the corresponding profile tree node.
         * @return the protocol field value
         */
        public long nodeId() {
            return ((Number) require("nodeId")).longValue();
        }
        /**
         * Time-ordered sample ordinal number. It is unique across all profiles retrieved between startSampling and stopSampling.
         * @return the protocol field value
         */
        public double ordinal() {
            return ((Number) require("ordinal")).doubleValue();
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
            return java.util.Objects.requireNonNull(HeapProfiler.SamplingHeapProfileNode.fromMap(java.util.Objects.requireNonNull(CdpObject.objectMap(require("head")))));
        }
        /**
         * Returns the samples field.
         * @return the protocol field value
         */
        public java.util.List<HeapProfiler.SamplingHeapProfileSample> samples() {
            return CdpObject.requireList(require("samples"), element0 -> java.util.Objects.requireNonNull(HeapProfiler.SamplingHeapProfileSample.fromMap(java.util.Objects.requireNonNull(CdpObject.objectMap(element0)))));
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
            return (String) require("chunk");
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
            return CdpObject.requireList(require("statsUpdate"), element0 -> ((Number) element0).longValue());
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
            return ((Number) require("lastSeenObjectId")).longValue();
        }
        /**
         * Returns the timestamp field.
         * @return the protocol field value
         */
        public double timestamp() {
            return ((Number) require("timestamp")).doubleValue();
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
            return ((Number) require("done")).longValue();
        }
        /**
         * Returns the total field.
         * @return the protocol field value
         */
        public long total() {
            return ((Number) require("total")).longValue();
        }
        /**
         * Returns the finished field.
         * @return the protocol field value, empty when absent
         */
        public Optional<Boolean> finished() {
            return Optional.ofNullable((Boolean) raw("finished"));
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
         * @param finished field value; empty omits the value
         * @return this model
         */
        public ReportHeapSnapshotProgressEvent finished(Optional<Boolean> finished) {
            set("finished", finished.orElse(null));
            return this;
        }
        /**
         * Sets the finished field.
         * @param finished field value; null removes the value
         * @return this model
         */
        public ReportHeapSnapshotProgressEvent finished(Boolean finished) {
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
    public static final class Client {
        private final CdpClient client;
        public Client(CdpClient client) { this.client = client; }
        /**
         * Enables console to refer to the node with given id via $x (see Command Line API for more details $x functions).
         * @param heapObjectId protocol value
         * @return a stage completing when the command completes
         */
        public CompletionStage<Void> addInspectedHeapObject(HeapProfiler.HeapSnapshotObjectId heapObjectId) {
            Map<String, Object> params = new LinkedHashMap<>();
            params.put("heapObjectId", CdpObject.json(heapObjectId));
            return client.call("HeapProfiler.addInspectedHeapObject", params, result_ -> null);
        }
        /**
         * Invokes HeapProfiler.collectGarbage.
         * @return a stage completing when the command completes
         */
        public CompletionStage<Void> collectGarbage() {
            return client.call("HeapProfiler.collectGarbage", null, result_ -> null);
        }
        /**
         * Invokes HeapProfiler.disable.
         * @return a stage completing when the command completes
         */
        public CompletionStage<Void> disable() {
            return client.call("HeapProfiler.disable", null, result_ -> null);
        }
        /**
         * Invokes HeapProfiler.enable.
         * @return a stage completing when the command completes
         */
        public CompletionStage<Void> enable() {
            return client.call("HeapProfiler.enable", null, result_ -> null);
        }
        /**
         * Invokes HeapProfiler.getHeapObjectId.
         * @param objectId protocol value
         * @return a stage completing with the command result
         */
        public CompletionStage<HeapProfiler.HeapSnapshotObjectId> getHeapObjectId(Runtime.RemoteObjectId objectId) {
            Map<String, Object> params = new LinkedHashMap<>();
            params.put("objectId", CdpObject.json(objectId));
            return client.call("HeapProfiler.getHeapObjectId", params, result_ -> new HeapProfiler.HeapSnapshotObjectId((String) java.util.Objects.requireNonNull(result_.get("heapSnapshotObjectId"))));
        }
        /**
         * Invokes HeapProfiler.getObjectByHeapObjectId.
         * @param objectId protocol value
         * @param objectGroup protocol value
         * @return a stage completing with the command result
         */
        public CompletionStage<Runtime.RemoteObject> getObjectByHeapObjectId(HeapProfiler.HeapSnapshotObjectId objectId, Optional<String> objectGroup) {
            Map<String, Object> params = new LinkedHashMap<>();
            params.put("objectId", CdpObject.json(objectId));
            objectGroup.ifPresent(value_ -> params.put("objectGroup", CdpObject.json(value_)));
            return client.call("HeapProfiler.getObjectByHeapObjectId", params, result_ -> java.util.Objects.requireNonNull(Runtime.RemoteObject.fromMap(java.util.Objects.requireNonNull(CdpObject.objectMap(java.util.Objects.requireNonNull(result_.get("result")))))));
        }
        /**
         * Invokes HeapProfiler.getObjectByHeapObjectId with the required parameters.
         * @param objectId protocol value
         * @return a stage completing with the command result
         */
        public CompletionStage<Runtime.RemoteObject> getObjectByHeapObjectId(HeapProfiler.HeapSnapshotObjectId objectId) {
            return getObjectByHeapObjectId(objectId, Optional.empty());
        }
        /**
         * Invokes HeapProfiler.getSamplingProfile.
         * @return a stage completing with the command result
         */
        public CompletionStage<HeapProfiler.SamplingHeapProfile> getSamplingProfile() {
            return client.call("HeapProfiler.getSamplingProfile", null, result_ -> java.util.Objects.requireNonNull(HeapProfiler.SamplingHeapProfile.fromMap(java.util.Objects.requireNonNull(CdpObject.objectMap(java.util.Objects.requireNonNull(result_.get("profile")))))));
        }
        /**
         * Invokes HeapProfiler.startSampling.
         * @param samplingInterval protocol value
         * @param stackDepth protocol value
         * @param includeObjectsCollectedByMajorGC protocol value
         * @param includeObjectsCollectedByMinorGC protocol value
         * @return a stage completing when the command completes
         */
        public CompletionStage<Void> startSampling(OptionalDouble samplingInterval, OptionalDouble stackDepth, Optional<Boolean> includeObjectsCollectedByMajorGC, Optional<Boolean> includeObjectsCollectedByMinorGC) {
            Map<String, Object> params = new LinkedHashMap<>();
            samplingInterval.ifPresent(value_ -> params.put("samplingInterval", value_));
            stackDepth.ifPresent(value_ -> params.put("stackDepth", value_));
            includeObjectsCollectedByMajorGC.ifPresent(value_ -> params.put("includeObjectsCollectedByMajorGC", value_));
            includeObjectsCollectedByMinorGC.ifPresent(value_ -> params.put("includeObjectsCollectedByMinorGC", value_));
            return client.call("HeapProfiler.startSampling", params, result_ -> null);
        }
        /**
         * Invokes HeapProfiler.startSampling with default parameters.
         * @return a stage completing when the command completes
         */
        public CompletionStage<Void> startSampling() {
            return startSampling(OptionalDouble.empty(), OptionalDouble.empty(), Optional.empty(), Optional.empty());
        }
        /**
         * Invokes HeapProfiler.startTrackingHeapObjects.
         * @param trackAllocations protocol value
         * @return a stage completing when the command completes
         */
        public CompletionStage<Void> startTrackingHeapObjects(Optional<Boolean> trackAllocations) {
            Map<String, Object> params = new LinkedHashMap<>();
            trackAllocations.ifPresent(value_ -> params.put("trackAllocations", value_));
            return client.call("HeapProfiler.startTrackingHeapObjects", params, result_ -> null);
        }
        /**
         * Invokes HeapProfiler.startTrackingHeapObjects with default parameters.
         * @return a stage completing when the command completes
         */
        public CompletionStage<Void> startTrackingHeapObjects() {
            return startTrackingHeapObjects(Optional.empty());
        }
        /**
         * Invokes HeapProfiler.stopSampling.
         * @return a stage completing with the command result
         */
        public CompletionStage<HeapProfiler.SamplingHeapProfile> stopSampling() {
            return client.call("HeapProfiler.stopSampling", null, result_ -> java.util.Objects.requireNonNull(HeapProfiler.SamplingHeapProfile.fromMap(java.util.Objects.requireNonNull(CdpObject.objectMap(java.util.Objects.requireNonNull(result_.get("profile")))))));
        }
        /**
         * Invokes HeapProfiler.stopTrackingHeapObjects.
         * @param reportProgress protocol value
         * @param treatGlobalObjectsAsRoots protocol value
         * @param captureNumericValue protocol value
         * @param exposeInternals protocol value
         * @return a stage completing when the command completes
         */
        public CompletionStage<Void> stopTrackingHeapObjects(Optional<Boolean> reportProgress, Optional<Boolean> treatGlobalObjectsAsRoots, Optional<Boolean> captureNumericValue, Optional<Boolean> exposeInternals) {
            Map<String, Object> params = new LinkedHashMap<>();
            reportProgress.ifPresent(value_ -> params.put("reportProgress", value_));
            treatGlobalObjectsAsRoots.ifPresent(value_ -> params.put("treatGlobalObjectsAsRoots", value_));
            captureNumericValue.ifPresent(value_ -> params.put("captureNumericValue", value_));
            exposeInternals.ifPresent(value_ -> params.put("exposeInternals", value_));
            return client.call("HeapProfiler.stopTrackingHeapObjects", params, result_ -> null);
        }
        /**
         * Invokes HeapProfiler.stopTrackingHeapObjects with default parameters.
         * @return a stage completing when the command completes
         */
        public CompletionStage<Void> stopTrackingHeapObjects() {
            return stopTrackingHeapObjects(Optional.empty(), Optional.empty(), Optional.empty(), Optional.empty());
        }
        /**
         * Invokes HeapProfiler.takeHeapSnapshot.
         * @param reportProgress protocol value
         * @param treatGlobalObjectsAsRoots protocol value
         * @param captureNumericValue protocol value
         * @param exposeInternals protocol value
         * @return a stage completing when the command completes
         */
        public CompletionStage<Void> takeHeapSnapshot(Optional<Boolean> reportProgress, Optional<Boolean> treatGlobalObjectsAsRoots, Optional<Boolean> captureNumericValue, Optional<Boolean> exposeInternals) {
            Map<String, Object> params = new LinkedHashMap<>();
            reportProgress.ifPresent(value_ -> params.put("reportProgress", value_));
            treatGlobalObjectsAsRoots.ifPresent(value_ -> params.put("treatGlobalObjectsAsRoots", value_));
            captureNumericValue.ifPresent(value_ -> params.put("captureNumericValue", value_));
            exposeInternals.ifPresent(value_ -> params.put("exposeInternals", value_));
            return client.call("HeapProfiler.takeHeapSnapshot", params, result_ -> null);
        }
        /**
         * Invokes HeapProfiler.takeHeapSnapshot with default parameters.
         * @return a stage completing when the command completes
         */
        public CompletionStage<Void> takeHeapSnapshot() {
            return takeHeapSnapshot(Optional.empty(), Optional.empty(), Optional.empty(), Optional.empty());
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
