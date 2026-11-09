// GENERATED - do not edit. Regenerate via: ./mvnw generate-sources -pl cef4j-cdp -Dcef.version=150.0.18+gdb11278+chromium-150.0.7871.213
package net.kurobako.cef4j.cdp.generated;

import java.util.Map;
import java.util.Optional;
import java.util.OptionalLong;
import java.util.concurrent.CompletionStage;
import javax.annotation.processing.Generated;
import net.kurobako.cef4j.cdp.CdpClient;
import net.kurobako.cef4j.cdp.CdpDomainClient;
import net.kurobako.cef4j.cdp.CdpField;
import net.kurobako.cef4j.cdp.CdpObject;
import net.kurobako.cef4j.cdp.CdpValue;
import net.kurobako.cef4j.policy.NullableBoundary;
import org.jspecify.annotations.Nullable;

/**
 * Chrome DevTools Protocol Memory domain.
 * <p><b>Experimental:</b> this part of CDP may change without notice.
 * @see <a href="https://chromium.googlesource.com/chromium/src/+/refs/tags/150.0.7871.213/third_party/blink/public/devtools_protocol/domains/Memory.pdl">Pinned protocol source</a>
 */
@Generated("./mvnw generate-sources -pl cef4j-cdp -Dcef.version=150.0.18+gdb11278+chromium-150.0.7871.213")
@SuppressWarnings({"EscapedEntity", "InvalidParam", "JavaLangClash", "MissingSummary", "Unchecked", "UnusedMethod"})
@NullableBoundary("CDP wire objects use null for absent optional fields")
public final class Memory {
    private Memory() {}
    private static final CdpField<java.util.List<Memory.DOMCounter>> COUNTERS = CdpField.of("counters", value -> CdpObject.requireList(value, element0 -> java.util.Objects.requireNonNull(Memory.DOMCounter.fromMap(CdpObject.requireObjectMap(element0)))));
    private static final CdpField<Memory.PressureLevel> LEVEL = CdpField.of("level", value -> Memory.PressureLevel.of((String) value));
    private static final CdpField<java.util.List<Memory.Module>> MODULES = CdpField.of("modules", value -> CdpObject.requireList(value, element0 -> java.util.Objects.requireNonNull(Memory.Module.fromMap(CdpObject.requireObjectMap(element0)))));
    private static final CdpField<Memory.SamplingProfile> PROFILE = CdpField.of("profile", value -> java.util.Objects.requireNonNull(Memory.SamplingProfile.fromMap(CdpObject.requireObjectMap(value))));
    private static final CdpField<java.util.List<Memory.SamplingProfileNode>> SAMPLES = CdpField.of("samples", value -> CdpObject.requireList(value, element0 -> java.util.Objects.requireNonNull(Memory.SamplingProfileNode.fromMap(CdpObject.requireObjectMap(element0)))));
    private static final CdpField<java.util.List<String>> STACK = CdpField.of("stack", value -> CdpObject.requireList(value, element0 -> (String) element0));
    /**
     * Memory pressure level.
     */
    public enum PressureLevel implements CdpValue<String> {
        MODERATE("moderate"),
        CRITICAL("critical");
        public final String value;
        PressureLevel(String value) { this.value = value; }
        @Override public String value() { return value; }
        public static PressureLevel of(String value) {
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
            return requireDouble("size");
        }
        /**
         * Total bytes attributed to this sample.
         * @return the protocol field value
         */
        public double total() {
            return requireDouble("total");
        }
        /**
         * Execution stack at the point of allocation.
         * @return the protocol field value
         */
        public java.util.List<String> stack() {
            return require(STACK);
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
            return require(SAMPLES);
        }
        /**
         * Returns the modules field.
         * @return the protocol field value
         */
        public java.util.List<Memory.Module> modules() {
            return require(MODULES);
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
            return requireString("name");
        }
        /**
         * UUID of the module.
         * @return the protocol field value
         */
        public String uuid() {
            return requireString("uuid");
        }
        /**
         * Base address where the module is loaded into memory. Encoded as a decimal or hexadecimal (0x prefixed) string.
         * @return the protocol field value
         */
        public String baseAddress() {
            return requireString("baseAddress");
        }
        /**
         * Size of the module in bytes.
         * @return the protocol field value
         */
        public double size() {
            return requireDouble("size");
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
            return requireString("name");
        }
        /**
         * Object count.
         * @return the protocol field value
         */
        public long count() {
            return requireLong("count");
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
     * Enable/disable suppressing memory pressure notifications in all processes.
     */
    public static final class SetPressureNotificationsSuppressedRequest extends CdpObject {
        public SetPressureNotificationsSuppressedRequest() {}
        public static SetPressureNotificationsSuppressedRequest fromMap(Map<String, Object> values) {
            final SetPressureNotificationsSuppressedRequest instance_ = new SetPressureNotificationsSuppressedRequest();
            if (values != null) instance_.values.putAll(values);
            return instance_;
        }
        /**
         * If true, memory pressure notifications will be suppressed.
         * @return the protocol field value
         */
        public boolean suppressed() {
            return requireBoolean("suppressed");
        }
        /**
         * If true, memory pressure notifications will be suppressed.
         * @param suppressed field value
         * @return this model
         */
        public SetPressureNotificationsSuppressedRequest suppressed(boolean suppressed) {
            set("suppressed", suppressed);
            return this;
        }
    }
    /**
     * Simulate a memory pressure notification in all processes.
     */
    public static final class SimulatePressureNotificationRequest extends CdpObject {
        public SimulatePressureNotificationRequest() {}
        public static SimulatePressureNotificationRequest fromMap(Map<String, Object> values) {
            final SimulatePressureNotificationRequest instance_ = new SimulatePressureNotificationRequest();
            if (values != null) instance_.values.putAll(values);
            return instance_;
        }
        /**
         * Memory pressure level of the notification.
         * @return the protocol field value
         */
        public Memory.PressureLevel level() {
            return require(LEVEL);
        }
        /**
         * Memory pressure level of the notification.
         * @param level field value
         * @return this model
         */
        public SimulatePressureNotificationRequest level(Memory.PressureLevel level) {
            set("level", level);
            return this;
        }
    }
    /**
     * Start collecting native memory profile.
     */
    public static final class StartSamplingRequest extends CdpObject {
        public StartSamplingRequest() {}
        public static StartSamplingRequest fromMap(Map<String, Object> values) {
            final StartSamplingRequest instance_ = new StartSamplingRequest();
            if (values != null) instance_.values.putAll(values);
            return instance_;
        }
        /**
         * Average number of bytes between samples.
         * @return the protocol field value, empty when absent
         */
        public OptionalLong samplingInterval() {
            return optionalLong("samplingInterval");
        }
        /**
         * Do not randomize intervals between samples.
         * @return the protocol field value, empty when absent
         */
        public Optional<Boolean> suppressRandomness() {
            return optionalBoolean("suppressRandomness");
        }
        /**
         * Average number of bytes between samples.
         * @param samplingInterval field value
         * @return this model
         */
        public StartSamplingRequest samplingInterval(long samplingInterval) {
            set("samplingInterval", samplingInterval);
            return this;
        }
        /**
         * Do not randomize intervals between samples.
         * @param suppressRandomness field value
         * @return this model
         */
        public StartSamplingRequest suppressRandomness(boolean suppressRandomness) {
            set("suppressRandomness", suppressRandomness);
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
            return requireLong("documents");
        }
        /**
         * Returns the nodes field.
         * @return the protocol field value
         */
        public long nodes() {
            return requireLong("nodes");
        }
        /**
         * Returns the jsEventListeners field.
         * @return the protocol field value
         */
        public long jsEventListeners() {
            return requireLong("jsEventListeners");
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
    public static final class Client extends CdpDomainClient {
        public Client(CdpClient client) { super(client); }
        /**
         * Retruns current DOM object counters.
         * @return a stage completing with the command result
         */
        public CompletionStage<GetDOMCountersResult> getDOMCounters() {
            return call("Memory.getDOMCounters", GetDOMCountersResult::fromMap);
        }
        /**
         * Retruns DOM object counters after preparing renderer for leak detection.
         * @return a stage completing with the command result
         */
        public CompletionStage<java.util.List<Memory.DOMCounter>> getDOMCountersForLeakDetection() {
            return call("Memory.getDOMCountersForLeakDetection", COUNTERS::require);
        }
        /**
         * Prepares for leak detection by terminating workers, stopping spellcheckers, dropping non-essential internal caches, running garbage collections, etc.
         * @return a stage completing when the command completes
         */
        public CompletionStage<Void> prepareForLeakDetection() {
            return call("Memory.prepareForLeakDetection");
        }
        /**
         * Simulate OomIntervention by purging V8 memory.
         * @return a stage completing when the command completes
         */
        public CompletionStage<Void> forciblyPurgeJavaScriptMemory() {
            return call("Memory.forciblyPurgeJavaScriptMemory");
        }
        /**
         * Enable/disable suppressing memory pressure notifications in all processes.
         * @param suppressed protocol value
         * @return a stage completing when the command completes
         */
        public CompletionStage<Void> setPressureNotificationsSuppressed(boolean suppressed) {
            return call("Memory.setPressureNotificationsSuppressed", params("suppressed", suppressed));
        }
        /**
         * Enable/disable suppressing memory pressure notifications in all processes.
         * @param request request parameters
         * @return a stage completing when the command completes
         */
        public CompletionStage<Void> setPressureNotificationsSuppressed(SetPressureNotificationsSuppressedRequest request) {
            return call("Memory.setPressureNotificationsSuppressed", request);
        }
        /**
         * Simulate a memory pressure notification in all processes.
         * @param level protocol value
         * @return a stage completing when the command completes
         */
        public CompletionStage<Void> simulatePressureNotification(Memory.PressureLevel level) {
            return call("Memory.simulatePressureNotification", params("level", level));
        }
        /**
         * Simulate a memory pressure notification in all processes.
         * @param request request parameters
         * @return a stage completing when the command completes
         */
        public CompletionStage<Void> simulatePressureNotification(SimulatePressureNotificationRequest request) {
            return call("Memory.simulatePressureNotification", request);
        }
        /**
         * Start collecting native memory profile.
         * @param samplingInterval protocol value
         * @param suppressRandomness protocol value
         * @return a stage completing when the command completes
         */
        public CompletionStage<Void> startSampling(@Nullable Long samplingInterval, @Nullable Boolean suppressRandomness) {
            return call("Memory.startSampling", params("samplingInterval", samplingInterval, "suppressRandomness", suppressRandomness));
        }
        /**
         * Start collecting native memory profile.
         * @return a stage completing when the command completes
         */
        public CompletionStage<Void> startSampling() {
            return call("Memory.startSampling");
        }
        /**
         * Start collecting native memory profile.
         * @param request request parameters
         * @return a stage completing when the command completes
         */
        public CompletionStage<Void> startSampling(StartSamplingRequest request) {
            return call("Memory.startSampling", request);
        }
        /**
         * Stop collecting native memory profile.
         * @return a stage completing when the command completes
         */
        public CompletionStage<Void> stopSampling() {
            return call("Memory.stopSampling");
        }
        /**
         * Retrieve native memory allocations profile collected since renderer process startup.
         * @return a stage completing with the command result
         */
        public CompletionStage<Memory.SamplingProfile> getAllTimeSamplingProfile() {
            return call("Memory.getAllTimeSamplingProfile", PROFILE::require);
        }
        /**
         * Retrieve native memory allocations profile collected since browser process startup.
         * @return a stage completing with the command result
         */
        public CompletionStage<Memory.SamplingProfile> getBrowserSamplingProfile() {
            return call("Memory.getBrowserSamplingProfile", PROFILE::require);
        }
        /**
         * Retrieve native memory allocations profile collected since last {@code startSampling} call.
         * @return a stage completing with the command result
         */
        public CompletionStage<Memory.SamplingProfile> getSamplingProfile() {
            return call("Memory.getSamplingProfile", PROFILE::require);
        }
    }
}
