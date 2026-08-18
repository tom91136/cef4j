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
 * Chrome DevTools Protocol Memory domain.
 * <p><b>Experimental:</b> this part of CDP may change without notice.
 * @see <a href="https://chromium.googlesource.com/chromium/src/+/refs/tags/150.0.7871.213/third_party/blink/public/devtools_protocol/domains/Memory.pdl">Pinned protocol source</a>
 */
@Generated("mvn generate-sources -pl cef4j-platform -Dcef.version=150.0.18+gdb11278+chromium-150.0.7871.213")
@SuppressWarnings({"EscapedEntity", "InvalidParam", "JavaLangClash", "MissingSummary", "Unchecked", "UnusedMethod"})
public final class Memory {
    private Memory() {}
    /**
     * Memory pressure level.
     */
    public enum PressureLevel implements CdpValue<String> {
        MODERATE("moderate"),
        CRITICAL("critical");
        public final String value;
        PressureLevel(String value) { this.value = value; }
        @Nonnull public String value() { return value; }
        public static PressureLevel of(@Nonnull String value) {
            for (PressureLevel constant : values()) {
                if (constant.value.equals(value)) return constant;
            }
            throw new IllegalArgumentException("Unknown PressureLevel value: " + value);
        }
    }
    /**
     * Heap profile sample.
     */
    public static final class SamplingProfileNode extends CdpObject {
        public SamplingProfileNode() {}
        private SamplingProfileNode(Map<String, Object> values) { super(values); }
        public static SamplingProfileNode fromMap(Map<String, Object> values) {
            return new SamplingProfileNode(values);
        }
        /**
         * Size of the sampled allocation.
         * @return the protocol field value
         */
        public double size() {
            return ((Number) require("size")).doubleValue();
        }
        /**
         * Total bytes attributed to this sample.
         * @return the protocol field value
         */
        public double total() {
            return ((Number) require("total")).doubleValue();
        }
        /**
         * Execution stack at the point of allocation.
         * @return the protocol field value
         */
        public java.util.List<String> stack() {
            return CdpObject.requireList(require("stack"), element0 -> (String) element0);
        }
        /**
         * Size of the sampled allocation.
         * @param size field value
         * @return this model
         */
        public SamplingProfileNode size(double size) {
            set("size", size);
            return this;
        }
        /**
         * Total bytes attributed to this sample.
         * @param total field value
         * @return this model
         */
        public SamplingProfileNode total(double total) {
            set("total", total);
            return this;
        }
        /**
         * Execution stack at the point of allocation.
         * @param stack field value
         * @return this model
         */
        public SamplingProfileNode stack(java.util.List<String> stack) {
            set("stack", stack);
            return this;
        }
    }
    /**
     * Array of heap profile samples.
     */
    public static final class SamplingProfile extends CdpObject {
        public SamplingProfile() {}
        private SamplingProfile(Map<String, Object> values) { super(values); }
        public static SamplingProfile fromMap(Map<String, Object> values) {
            return new SamplingProfile(values);
        }
        /**
         * Returns the samples field.
         * @return the protocol field value
         */
        public java.util.List<Memory.SamplingProfileNode> samples() {
            return CdpObject.requireList(require("samples"), element0 -> java.util.Objects.requireNonNull(Memory.SamplingProfileNode.fromMap(java.util.Objects.requireNonNull(CdpObject.objectMap(element0)))));
        }
        /**
         * Returns the modules field.
         * @return the protocol field value
         */
        public java.util.List<Memory.Module> modules() {
            return CdpObject.requireList(require("modules"), element0 -> java.util.Objects.requireNonNull(Memory.Module.fromMap(java.util.Objects.requireNonNull(CdpObject.objectMap(element0)))));
        }
        /**
         * Sets the samples field.
         * @param samples field value
         * @return this model
         */
        public SamplingProfile samples(java.util.List<Memory.SamplingProfileNode> samples) {
            set("samples", samples);
            return this;
        }
        /**
         * Sets the modules field.
         * @param modules field value
         * @return this model
         */
        public SamplingProfile modules(java.util.List<Memory.Module> modules) {
            set("modules", modules);
            return this;
        }
    }
    /**
     * Executable module information
     */
    public static final class Module extends CdpObject {
        public Module() {}
        private Module(Map<String, Object> values) { super(values); }
        public static Module fromMap(Map<String, Object> values) {
            return new Module(values);
        }
        /**
         * Name of the module.
         * @return the protocol field value
         */
        public String name() {
            return (String) require("name");
        }
        /**
         * UUID of the module.
         * @return the protocol field value
         */
        public String uuid() {
            return (String) require("uuid");
        }
        /**
         * Base address where the module is loaded into memory. Encoded as a decimal or hexadecimal (0x prefixed) string.
         * @return the protocol field value
         */
        public String baseAddress() {
            return (String) require("baseAddress");
        }
        /**
         * Size of the module in bytes.
         * @return the protocol field value
         */
        public double size() {
            return ((Number) require("size")).doubleValue();
        }
        /**
         * Name of the module.
         * @param name field value
         * @return this model
         */
        public Module name(String name) {
            set("name", name);
            return this;
        }
        /**
         * UUID of the module.
         * @param uuid field value
         * @return this model
         */
        public Module uuid(String uuid) {
            set("uuid", uuid);
            return this;
        }
        /**
         * Base address where the module is loaded into memory. Encoded as a decimal or hexadecimal (0x prefixed) string.
         * @param baseAddress field value
         * @return this model
         */
        public Module baseAddress(String baseAddress) {
            set("baseAddress", baseAddress);
            return this;
        }
        /**
         * Size of the module in bytes.
         * @param size field value
         * @return this model
         */
        public Module size(double size) {
            set("size", size);
            return this;
        }
    }
    /**
     * DOM object counter data.
     */
    public static final class DOMCounter extends CdpObject {
        public DOMCounter() {}
        private DOMCounter(Map<String, Object> values) { super(values); }
        public static DOMCounter fromMap(Map<String, Object> values) {
            return new DOMCounter(values);
        }
        /**
         * Object name. Note: object names should be presumed volatile and clients should not expect the returned names to be consistent across runs.
         * @return the protocol field value
         */
        public String name() {
            return (String) require("name");
        }
        /**
         * Object count.
         * @return the protocol field value
         */
        public long count() {
            return ((Number) require("count")).longValue();
        }
        /**
         * Object name. Note: object names should be presumed volatile and clients should not expect the returned names to be consistent across runs.
         * @param name field value
         * @return this model
         */
        public DOMCounter name(String name) {
            set("name", name);
            return this;
        }
        /**
         * Object count.
         * @param count field value
         * @return this model
         */
        public DOMCounter count(long count) {
            set("count", count);
            return this;
        }
    }
    /**
     * Retruns current DOM object counters.
     */
    public static final class GetDOMCountersResult extends CdpObject {
        public GetDOMCountersResult() {}
        private GetDOMCountersResult(Map<String, Object> values) { super(values); }
        public static GetDOMCountersResult fromMap(Map<String, Object> values) {
            return new GetDOMCountersResult(values);
        }
        /**
         * Returns the documents field.
         * @return the protocol field value
         */
        public long documents() {
            return ((Number) require("documents")).longValue();
        }
        /**
         * Returns the nodes field.
         * @return the protocol field value
         */
        public long nodes() {
            return ((Number) require("nodes")).longValue();
        }
        /**
         * Returns the jsEventListeners field.
         * @return the protocol field value
         */
        public long jsEventListeners() {
            return ((Number) require("jsEventListeners")).longValue();
        }
        /**
         * Sets the documents field.
         * @param documents field value
         * @return this model
         */
        public GetDOMCountersResult documents(long documents) {
            set("documents", documents);
            return this;
        }
        /**
         * Sets the nodes field.
         * @param nodes field value
         * @return this model
         */
        public GetDOMCountersResult nodes(long nodes) {
            set("nodes", nodes);
            return this;
        }
        /**
         * Sets the jsEventListeners field.
         * @param jsEventListeners field value
         * @return this model
         */
        public GetDOMCountersResult jsEventListeners(long jsEventListeners) {
            set("jsEventListeners", jsEventListeners);
            return this;
        }
    }
    public static final class Client {
        private final CdpClient client;
        public Client(CdpClient client) { this.client = client; }
        /**
         * Retruns current DOM object counters.
         * @return a stage completing with the command result
         */
        public CompletionStage<GetDOMCountersResult> getDOMCounters() {
            return client.call("Memory.getDOMCounters", null, result_ -> new GetDOMCountersResult(result_));
        }
        /**
         * Retruns DOM object counters after preparing renderer for leak detection.
         * @return a stage completing with the command result
         */
        public CompletionStage<java.util.List<Memory.DOMCounter>> getDOMCountersForLeakDetection() {
            return client.call("Memory.getDOMCountersForLeakDetection", null, result_ -> CdpObject.requireList(java.util.Objects.requireNonNull(result_.get("counters")), element0 -> java.util.Objects.requireNonNull(Memory.DOMCounter.fromMap(java.util.Objects.requireNonNull(CdpObject.objectMap(element0))))));
        }
        /**
         * Prepares for leak detection by terminating workers, stopping spellcheckers, dropping non-essential internal caches, running garbage collections, etc.
         * @return a stage completing when the command completes
         */
        public CompletionStage<Void> prepareForLeakDetection() {
            return client.call("Memory.prepareForLeakDetection", null, result_ -> null);
        }
        /**
         * Simulate OomIntervention by purging V8 memory.
         * @return a stage completing when the command completes
         */
        public CompletionStage<Void> forciblyPurgeJavaScriptMemory() {
            return client.call("Memory.forciblyPurgeJavaScriptMemory", null, result_ -> null);
        }
        /**
         * Enable/disable suppressing memory pressure notifications in all processes.
         * @param suppressed protocol value
         * @return a stage completing when the command completes
         */
        public CompletionStage<Void> setPressureNotificationsSuppressed(boolean suppressed) {
            Map<String, Object> params = new LinkedHashMap<>();
            params.put("suppressed", CdpObject.json(suppressed));
            return client.call("Memory.setPressureNotificationsSuppressed", params, result_ -> null);
        }
        /**
         * Simulate a memory pressure notification in all processes.
         * @param level protocol value
         * @return a stage completing when the command completes
         */
        public CompletionStage<Void> simulatePressureNotification(Memory.PressureLevel level) {
            Map<String, Object> params = new LinkedHashMap<>();
            params.put("level", CdpObject.json(level));
            return client.call("Memory.simulatePressureNotification", params, result_ -> null);
        }
        /**
         * Start collecting native memory profile.
         * @param samplingInterval protocol value
         * @param suppressRandomness protocol value
         * @return a stage completing when the command completes
         */
        public CompletionStage<Void> startSampling(OptionalLong samplingInterval, Optional<Boolean> suppressRandomness) {
            Map<String, Object> params = new LinkedHashMap<>();
            samplingInterval.ifPresent(value_ -> params.put("samplingInterval", value_));
            suppressRandomness.ifPresent(value_ -> params.put("suppressRandomness", value_));
            return client.call("Memory.startSampling", params, result_ -> null);
        }
        /**
         * Start collecting native memory profile.
         * @return a stage completing when the command completes
         */
        public CompletionStage<Void> startSampling() {
            return startSampling(OptionalLong.empty(), Optional.empty());
        }
        /**
         * Stop collecting native memory profile.
         * @return a stage completing when the command completes
         */
        public CompletionStage<Void> stopSampling() {
            return client.call("Memory.stopSampling", null, result_ -> null);
        }
        /**
         * Retrieve native memory allocations profile collected since renderer process startup.
         * @return a stage completing with the command result
         */
        public CompletionStage<Memory.SamplingProfile> getAllTimeSamplingProfile() {
            return client.call("Memory.getAllTimeSamplingProfile", null, result_ -> java.util.Objects.requireNonNull(Memory.SamplingProfile.fromMap(java.util.Objects.requireNonNull(CdpObject.objectMap(java.util.Objects.requireNonNull(result_.get("profile")))))));
        }
        /**
         * Retrieve native memory allocations profile collected since browser process startup.
         * @return a stage completing with the command result
         */
        public CompletionStage<Memory.SamplingProfile> getBrowserSamplingProfile() {
            return client.call("Memory.getBrowserSamplingProfile", null, result_ -> java.util.Objects.requireNonNull(Memory.SamplingProfile.fromMap(java.util.Objects.requireNonNull(CdpObject.objectMap(java.util.Objects.requireNonNull(result_.get("profile")))))));
        }
        /**
         * Retrieve native memory allocations profile collected since last {@code startSampling} call.
         * @return a stage completing with the command result
         */
        public CompletionStage<Memory.SamplingProfile> getSamplingProfile() {
            return client.call("Memory.getSamplingProfile", null, result_ -> java.util.Objects.requireNonNull(Memory.SamplingProfile.fromMap(java.util.Objects.requireNonNull(CdpObject.objectMap(java.util.Objects.requireNonNull(result_.get("profile")))))));
        }
    }
}
