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
 * This domain allows inspection of Web Audio API. https://webaudio.github.io/web-audio-api/
 * <p><b>Experimental:</b> this part of CDP may change without notice.
 * @see <a href="https://chromium.googlesource.com/chromium/src/+/refs/tags/150.0.7871.213/third_party/blink/public/devtools_protocol/domains/WebAudio.pdl">Pinned protocol source</a>
 */
@Generated("mvn generate-sources -pl cef4j-platform -Dcef.version=150.0.18+gdb11278+chromium-150.0.7871.213")
@SuppressWarnings({"EscapedEntity", "InvalidParam", "JavaLangClash", "MissingSummary", "Unchecked", "UnusedMethod"})
public final class WebAudio {
    private WebAudio() {}
    /**
     * An unique ID for a graph object (AudioContext, AudioNode, AudioParam) in Web Audio API
     */
    public static final class GraphObjectId implements CdpValue<String> {
        public final String value;
        public GraphObjectId(@Nonnull String value) { this.value = java.util.Objects.requireNonNull(value); }
        @Nonnull public String value() { return value; }
        @Override public boolean equals(Object other) {
            if (this == other) return true;
            if (!(other instanceof GraphObjectId)) return false;
            return value.equals(((GraphObjectId) other).value);
        }
        @Override public int hashCode() { return value.hashCode(); }
        @Override public String toString() { return "GraphObjectId(" + value + ")"; }
    }
    /**
     * Enum of BaseAudioContext types
     */
    public enum ContextType implements CdpValue<String> {
        REALTIME("realtime"),
        OFFLINE("offline");
        public final String value;
        ContextType(String value) { this.value = value; }
        @Nonnull public String value() { return value; }
        public static ContextType of(@Nonnull String value) {
            for (ContextType constant : values()) {
                if (constant.value.equals(value)) return constant;
            }
            throw new IllegalArgumentException("Unknown ContextType value: " + value);
        }
    }
    /**
     * Enum of AudioContextState from the spec
     */
    public enum ContextState implements CdpValue<String> {
        SUSPENDED("suspended"),
        RUNNING("running"),
        CLOSED("closed"),
        INTERRUPTED("interrupted");
        public final String value;
        ContextState(String value) { this.value = value; }
        @Nonnull public String value() { return value; }
        public static ContextState of(@Nonnull String value) {
            for (ContextState constant : values()) {
                if (constant.value.equals(value)) return constant;
            }
            throw new IllegalArgumentException("Unknown ContextState value: " + value);
        }
    }
    /**
     * Enum of AudioNode types
     */
    public static final class NodeType implements CdpValue<String> {
        public final String value;
        public NodeType(@Nonnull String value) { this.value = java.util.Objects.requireNonNull(value); }
        @Nonnull public String value() { return value; }
        @Override public boolean equals(Object other) {
            if (this == other) return true;
            if (!(other instanceof NodeType)) return false;
            return value.equals(((NodeType) other).value);
        }
        @Override public int hashCode() { return value.hashCode(); }
        @Override public String toString() { return "NodeType(" + value + ")"; }
    }
    /**
     * Enum of AudioNode::ChannelCountMode from the spec
     */
    public enum ChannelCountMode implements CdpValue<String> {
        CLAMPED_MAX("clamped-max"),
        EXPLICIT("explicit"),
        MAX("max");
        public final String value;
        ChannelCountMode(String value) { this.value = value; }
        @Nonnull public String value() { return value; }
        public static ChannelCountMode of(@Nonnull String value) {
            for (ChannelCountMode constant : values()) {
                if (constant.value.equals(value)) return constant;
            }
            throw new IllegalArgumentException("Unknown ChannelCountMode value: " + value);
        }
    }
    /**
     * Enum of AudioNode::ChannelInterpretation from the spec
     */
    public enum ChannelInterpretation implements CdpValue<String> {
        DISCRETE("discrete"),
        SPEAKERS("speakers");
        public final String value;
        ChannelInterpretation(String value) { this.value = value; }
        @Nonnull public String value() { return value; }
        public static ChannelInterpretation of(@Nonnull String value) {
            for (ChannelInterpretation constant : values()) {
                if (constant.value.equals(value)) return constant;
            }
            throw new IllegalArgumentException("Unknown ChannelInterpretation value: " + value);
        }
    }
    /**
     * Enum of AudioParam types
     */
    public static final class ParamType implements CdpValue<String> {
        public final String value;
        public ParamType(@Nonnull String value) { this.value = java.util.Objects.requireNonNull(value); }
        @Nonnull public String value() { return value; }
        @Override public boolean equals(Object other) {
            if (this == other) return true;
            if (!(other instanceof ParamType)) return false;
            return value.equals(((ParamType) other).value);
        }
        @Override public int hashCode() { return value.hashCode(); }
        @Override public String toString() { return "ParamType(" + value + ")"; }
    }
    /**
     * Enum of AudioParam::AutomationRate from the spec
     */
    public enum AutomationRate implements CdpValue<String> {
        A_RATE("a-rate"),
        K_RATE("k-rate");
        public final String value;
        AutomationRate(String value) { this.value = value; }
        @Nonnull public String value() { return value; }
        public static AutomationRate of(@Nonnull String value) {
            for (AutomationRate constant : values()) {
                if (constant.value.equals(value)) return constant;
            }
            throw new IllegalArgumentException("Unknown AutomationRate value: " + value);
        }
    }
    /**
     * Fields in AudioContext that change in real-time.
     */
    public static final class ContextRealtimeData extends CdpObject {
        public ContextRealtimeData() {}
        private ContextRealtimeData(Map<String, Object> values) { super(values); }
        public static ContextRealtimeData fromMap(Map<String, Object> values) {
            return new ContextRealtimeData(values);
        }
        /**
         * The current context time in second in BaseAudioContext.
         * @return the protocol field value
         */
        public double currentTime() {
            return ((Number) require("currentTime")).doubleValue();
        }
        /**
         * The time spent on rendering graph divided by render quantum duration, and multiplied by 100. 100 means the audio renderer reached the full capacity and glitch may occur.
         * @return the protocol field value
         */
        public double renderCapacity() {
            return ((Number) require("renderCapacity")).doubleValue();
        }
        /**
         * A running mean of callback interval.
         * @return the protocol field value
         */
        public double callbackIntervalMean() {
            return ((Number) require("callbackIntervalMean")).doubleValue();
        }
        /**
         * A running variance of callback interval.
         * @return the protocol field value
         */
        public double callbackIntervalVariance() {
            return ((Number) require("callbackIntervalVariance")).doubleValue();
        }
        /**
         * The current context time in second in BaseAudioContext.
         * @param currentTime field value
         * @return this model
         */
        public ContextRealtimeData currentTime(double currentTime) {
            set("currentTime", currentTime);
            return this;
        }
        /**
         * The time spent on rendering graph divided by render quantum duration, and multiplied by 100. 100 means the audio renderer reached the full capacity and glitch may occur.
         * @param renderCapacity field value
         * @return this model
         */
        public ContextRealtimeData renderCapacity(double renderCapacity) {
            set("renderCapacity", renderCapacity);
            return this;
        }
        /**
         * A running mean of callback interval.
         * @param callbackIntervalMean field value
         * @return this model
         */
        public ContextRealtimeData callbackIntervalMean(double callbackIntervalMean) {
            set("callbackIntervalMean", callbackIntervalMean);
            return this;
        }
        /**
         * A running variance of callback interval.
         * @param callbackIntervalVariance field value
         * @return this model
         */
        public ContextRealtimeData callbackIntervalVariance(double callbackIntervalVariance) {
            set("callbackIntervalVariance", callbackIntervalVariance);
            return this;
        }
    }
    /**
     * Protocol object for BaseAudioContext
     */
    public static final class BaseAudioContext extends CdpObject {
        public BaseAudioContext() {}
        private BaseAudioContext(Map<String, Object> values) { super(values); }
        public static BaseAudioContext fromMap(Map<String, Object> values) {
            return new BaseAudioContext(values);
        }
        /**
         * Returns the contextId field.
         * @return the protocol field value
         */
        public WebAudio.GraphObjectId contextId() {
            return new WebAudio.GraphObjectId((String) require("contextId"));
        }
        /**
         * Returns the contextType field.
         * @return the protocol field value
         */
        public WebAudio.ContextType contextType() {
            return WebAudio.ContextType.of((String) require("contextType"));
        }
        /**
         * Returns the contextState field.
         * @return the protocol field value
         */
        public WebAudio.ContextState contextState() {
            return WebAudio.ContextState.of((String) require("contextState"));
        }
        /**
         * Returns the realtimeData field.
         * @return the protocol field value, empty when absent
         */
        public Optional<WebAudio.ContextRealtimeData> realtimeData() {
            return Optional.ofNullable(raw("realtimeData") == null ? null : WebAudio.ContextRealtimeData.fromMap(java.util.Objects.requireNonNull(objectMap(raw("realtimeData")))));
        }
        /**
         * Platform-dependent callback buffer size.
         * @return the protocol field value
         */
        public double callbackBufferSize() {
            return ((Number) require("callbackBufferSize")).doubleValue();
        }
        /**
         * Number of output channels supported by audio hardware in use.
         * @return the protocol field value
         */
        public double maxOutputChannelCount() {
            return ((Number) require("maxOutputChannelCount")).doubleValue();
        }
        /**
         * Context sample rate.
         * @return the protocol field value
         */
        public double sampleRate() {
            return ((Number) require("sampleRate")).doubleValue();
        }
        /**
         * Sets the contextId field.
         * @param contextId field value
         * @return this model
         */
        public BaseAudioContext contextId(WebAudio.GraphObjectId contextId) {
            set("contextId", contextId);
            return this;
        }
        /**
         * Sets the contextType field.
         * @param contextType field value
         * @return this model
         */
        public BaseAudioContext contextType(WebAudio.ContextType contextType) {
            set("contextType", contextType);
            return this;
        }
        /**
         * Sets the contextState field.
         * @param contextState field value
         * @return this model
         */
        public BaseAudioContext contextState(WebAudio.ContextState contextState) {
            set("contextState", contextState);
            return this;
        }
        /**
         * Sets the realtimeData field.
         * @param realtimeData field value; empty omits the value
         * @return this model
         */
        public BaseAudioContext realtimeData(Optional<WebAudio.ContextRealtimeData> realtimeData) {
            set("realtimeData", realtimeData.orElse(null));
            return this;
        }
        /**
         * Sets the realtimeData field.
         * @param realtimeData field value; null removes the value
         * @return this model
         */
        public BaseAudioContext realtimeData(WebAudio.ContextRealtimeData realtimeData) {
            set("realtimeData", realtimeData);
            return this;
        }
        /**
         * Platform-dependent callback buffer size.
         * @param callbackBufferSize field value
         * @return this model
         */
        public BaseAudioContext callbackBufferSize(double callbackBufferSize) {
            set("callbackBufferSize", callbackBufferSize);
            return this;
        }
        /**
         * Number of output channels supported by audio hardware in use.
         * @param maxOutputChannelCount field value
         * @return this model
         */
        public BaseAudioContext maxOutputChannelCount(double maxOutputChannelCount) {
            set("maxOutputChannelCount", maxOutputChannelCount);
            return this;
        }
        /**
         * Context sample rate.
         * @param sampleRate field value
         * @return this model
         */
        public BaseAudioContext sampleRate(double sampleRate) {
            set("sampleRate", sampleRate);
            return this;
        }
    }
    /**
     * Protocol object for AudioListener
     */
    public static final class AudioListener extends CdpObject {
        public AudioListener() {}
        private AudioListener(Map<String, Object> values) { super(values); }
        public static AudioListener fromMap(Map<String, Object> values) {
            return new AudioListener(values);
        }
        /**
         * Returns the listenerId field.
         * @return the protocol field value
         */
        public WebAudio.GraphObjectId listenerId() {
            return new WebAudio.GraphObjectId((String) require("listenerId"));
        }
        /**
         * Returns the contextId field.
         * @return the protocol field value
         */
        public WebAudio.GraphObjectId contextId() {
            return new WebAudio.GraphObjectId((String) require("contextId"));
        }
        /**
         * Sets the listenerId field.
         * @param listenerId field value
         * @return this model
         */
        public AudioListener listenerId(WebAudio.GraphObjectId listenerId) {
            set("listenerId", listenerId);
            return this;
        }
        /**
         * Sets the contextId field.
         * @param contextId field value
         * @return this model
         */
        public AudioListener contextId(WebAudio.GraphObjectId contextId) {
            set("contextId", contextId);
            return this;
        }
    }
    /**
     * Protocol object for AudioNode
     */
    public static final class AudioNode extends CdpObject {
        public AudioNode() {}
        private AudioNode(Map<String, Object> values) { super(values); }
        public static AudioNode fromMap(Map<String, Object> values) {
            return new AudioNode(values);
        }
        /**
         * Returns the nodeId field.
         * @return the protocol field value
         */
        public WebAudio.GraphObjectId nodeId() {
            return new WebAudio.GraphObjectId((String) require("nodeId"));
        }
        /**
         * Returns the contextId field.
         * @return the protocol field value
         */
        public WebAudio.GraphObjectId contextId() {
            return new WebAudio.GraphObjectId((String) require("contextId"));
        }
        /**
         * Returns the nodeType field.
         * @return the protocol field value
         */
        public WebAudio.NodeType nodeType() {
            return new WebAudio.NodeType((String) require("nodeType"));
        }
        /**
         * Returns the numberOfInputs field.
         * @return the protocol field value
         */
        public double numberOfInputs() {
            return ((Number) require("numberOfInputs")).doubleValue();
        }
        /**
         * Returns the numberOfOutputs field.
         * @return the protocol field value
         */
        public double numberOfOutputs() {
            return ((Number) require("numberOfOutputs")).doubleValue();
        }
        /**
         * Returns the channelCount field.
         * @return the protocol field value
         */
        public double channelCount() {
            return ((Number) require("channelCount")).doubleValue();
        }
        /**
         * Returns the channelCountMode field.
         * @return the protocol field value
         */
        public WebAudio.ChannelCountMode channelCountMode() {
            return WebAudio.ChannelCountMode.of((String) require("channelCountMode"));
        }
        /**
         * Returns the channelInterpretation field.
         * @return the protocol field value
         */
        public WebAudio.ChannelInterpretation channelInterpretation() {
            return WebAudio.ChannelInterpretation.of((String) require("channelInterpretation"));
        }
        /**
         * Sets the nodeId field.
         * @param nodeId field value
         * @return this model
         */
        public AudioNode nodeId(WebAudio.GraphObjectId nodeId) {
            set("nodeId", nodeId);
            return this;
        }
        /**
         * Sets the contextId field.
         * @param contextId field value
         * @return this model
         */
        public AudioNode contextId(WebAudio.GraphObjectId contextId) {
            set("contextId", contextId);
            return this;
        }
        /**
         * Sets the nodeType field.
         * @param nodeType field value
         * @return this model
         */
        public AudioNode nodeType(WebAudio.NodeType nodeType) {
            set("nodeType", nodeType);
            return this;
        }
        /**
         * Sets the numberOfInputs field.
         * @param numberOfInputs field value
         * @return this model
         */
        public AudioNode numberOfInputs(double numberOfInputs) {
            set("numberOfInputs", numberOfInputs);
            return this;
        }
        /**
         * Sets the numberOfOutputs field.
         * @param numberOfOutputs field value
         * @return this model
         */
        public AudioNode numberOfOutputs(double numberOfOutputs) {
            set("numberOfOutputs", numberOfOutputs);
            return this;
        }
        /**
         * Sets the channelCount field.
         * @param channelCount field value
         * @return this model
         */
        public AudioNode channelCount(double channelCount) {
            set("channelCount", channelCount);
            return this;
        }
        /**
         * Sets the channelCountMode field.
         * @param channelCountMode field value
         * @return this model
         */
        public AudioNode channelCountMode(WebAudio.ChannelCountMode channelCountMode) {
            set("channelCountMode", channelCountMode);
            return this;
        }
        /**
         * Sets the channelInterpretation field.
         * @param channelInterpretation field value
         * @return this model
         */
        public AudioNode channelInterpretation(WebAudio.ChannelInterpretation channelInterpretation) {
            set("channelInterpretation", channelInterpretation);
            return this;
        }
    }
    /**
     * Protocol object for AudioParam
     */
    public static final class AudioParam extends CdpObject {
        public AudioParam() {}
        private AudioParam(Map<String, Object> values) { super(values); }
        public static AudioParam fromMap(Map<String, Object> values) {
            return new AudioParam(values);
        }
        /**
         * Returns the paramId field.
         * @return the protocol field value
         */
        public WebAudio.GraphObjectId paramId() {
            return new WebAudio.GraphObjectId((String) require("paramId"));
        }
        /**
         * Returns the nodeId field.
         * @return the protocol field value
         */
        public WebAudio.GraphObjectId nodeId() {
            return new WebAudio.GraphObjectId((String) require("nodeId"));
        }
        /**
         * Returns the contextId field.
         * @return the protocol field value
         */
        public WebAudio.GraphObjectId contextId() {
            return new WebAudio.GraphObjectId((String) require("contextId"));
        }
        /**
         * Returns the paramType field.
         * @return the protocol field value
         */
        public WebAudio.ParamType paramType() {
            return new WebAudio.ParamType((String) require("paramType"));
        }
        /**
         * Returns the rate field.
         * @return the protocol field value
         */
        public WebAudio.AutomationRate rate() {
            return WebAudio.AutomationRate.of((String) require("rate"));
        }
        /**
         * Returns the defaultValue field.
         * @return the protocol field value
         */
        public double defaultValue() {
            return ((Number) require("defaultValue")).doubleValue();
        }
        /**
         * Returns the minValue field.
         * @return the protocol field value
         */
        public double minValue() {
            return ((Number) require("minValue")).doubleValue();
        }
        /**
         * Returns the maxValue field.
         * @return the protocol field value
         */
        public double maxValue() {
            return ((Number) require("maxValue")).doubleValue();
        }
        /**
         * Sets the paramId field.
         * @param paramId field value
         * @return this model
         */
        public AudioParam paramId(WebAudio.GraphObjectId paramId) {
            set("paramId", paramId);
            return this;
        }
        /**
         * Sets the nodeId field.
         * @param nodeId field value
         * @return this model
         */
        public AudioParam nodeId(WebAudio.GraphObjectId nodeId) {
            set("nodeId", nodeId);
            return this;
        }
        /**
         * Sets the contextId field.
         * @param contextId field value
         * @return this model
         */
        public AudioParam contextId(WebAudio.GraphObjectId contextId) {
            set("contextId", contextId);
            return this;
        }
        /**
         * Sets the paramType field.
         * @param paramType field value
         * @return this model
         */
        public AudioParam paramType(WebAudio.ParamType paramType) {
            set("paramType", paramType);
            return this;
        }
        /**
         * Sets the rate field.
         * @param rate field value
         * @return this model
         */
        public AudioParam rate(WebAudio.AutomationRate rate) {
            set("rate", rate);
            return this;
        }
        /**
         * Sets the defaultValue field.
         * @param defaultValue field value
         * @return this model
         */
        public AudioParam defaultValue(double defaultValue) {
            set("defaultValue", defaultValue);
            return this;
        }
        /**
         * Sets the minValue field.
         * @param minValue field value
         * @return this model
         */
        public AudioParam minValue(double minValue) {
            set("minValue", minValue);
            return this;
        }
        /**
         * Sets the maxValue field.
         * @param maxValue field value
         * @return this model
         */
        public AudioParam maxValue(double maxValue) {
            set("maxValue", maxValue);
            return this;
        }
    }
    /**
     * Fetch the realtime data from the registered contexts.
     */
    public static final class GetRealtimeDataRequest extends CdpObject {
        public GetRealtimeDataRequest() {}
        /**
         * Fetch the realtime data from the registered contexts.
         * @param contextId protocol value
         */
        public GetRealtimeDataRequest(WebAudio.GraphObjectId contextId) {
            set("contextId", contextId);
        }
        public static GetRealtimeDataRequest fromMap(Map<String, Object> values) {
            GetRealtimeDataRequest instance_ = new GetRealtimeDataRequest();
            if (values != null) instance_.values.putAll(values);
            return instance_;
        }
        /**
         * Returns the contextId field.
         * @return the protocol field value
         */
        public WebAudio.GraphObjectId contextId() {
            return new WebAudio.GraphObjectId((String) require("contextId"));
        }
        /**
         * Sets the contextId field.
         * @param contextId field value
         * @return this model
         */
        public GetRealtimeDataRequest contextId(WebAudio.GraphObjectId contextId) {
            set("contextId", contextId);
            return this;
        }
    }
    /**
     * Notifies that a new BaseAudioContext has been created.
     */
    public static final class ContextCreatedEvent extends CdpObject {
        public ContextCreatedEvent() {}
        private ContextCreatedEvent(Map<String, Object> values) { super(values); }
        public static ContextCreatedEvent fromMap(Map<String, Object> values) {
            return new ContextCreatedEvent(values);
        }
        /**
         * Returns the context field.
         * @return the protocol field value
         */
        public WebAudio.BaseAudioContext context() {
            return java.util.Objects.requireNonNull(WebAudio.BaseAudioContext.fromMap(java.util.Objects.requireNonNull(CdpObject.objectMap(require("context")))));
        }
        /**
         * Sets the context field.
         * @param context field value
         * @return this model
         */
        public ContextCreatedEvent context(WebAudio.BaseAudioContext context) {
            set("context", context);
            return this;
        }
    }
    /**
     * Notifies that an existing BaseAudioContext will be destroyed.
     */
    public static final class ContextWillBeDestroyedEvent extends CdpObject {
        public ContextWillBeDestroyedEvent() {}
        private ContextWillBeDestroyedEvent(Map<String, Object> values) { super(values); }
        public static ContextWillBeDestroyedEvent fromMap(Map<String, Object> values) {
            return new ContextWillBeDestroyedEvent(values);
        }
        /**
         * Returns the contextId field.
         * @return the protocol field value
         */
        public WebAudio.GraphObjectId contextId() {
            return new WebAudio.GraphObjectId((String) require("contextId"));
        }
        /**
         * Sets the contextId field.
         * @param contextId field value
         * @return this model
         */
        public ContextWillBeDestroyedEvent contextId(WebAudio.GraphObjectId contextId) {
            set("contextId", contextId);
            return this;
        }
    }
    /**
     * Notifies that existing BaseAudioContext has changed some properties (id stays the same)..
     */
    public static final class ContextChangedEvent extends CdpObject {
        public ContextChangedEvent() {}
        private ContextChangedEvent(Map<String, Object> values) { super(values); }
        public static ContextChangedEvent fromMap(Map<String, Object> values) {
            return new ContextChangedEvent(values);
        }
        /**
         * Returns the context field.
         * @return the protocol field value
         */
        public WebAudio.BaseAudioContext context() {
            return java.util.Objects.requireNonNull(WebAudio.BaseAudioContext.fromMap(java.util.Objects.requireNonNull(CdpObject.objectMap(require("context")))));
        }
        /**
         * Sets the context field.
         * @param context field value
         * @return this model
         */
        public ContextChangedEvent context(WebAudio.BaseAudioContext context) {
            set("context", context);
            return this;
        }
    }
    /**
     * Notifies that the construction of an AudioListener has finished.
     */
    public static final class AudioListenerCreatedEvent extends CdpObject {
        public AudioListenerCreatedEvent() {}
        private AudioListenerCreatedEvent(Map<String, Object> values) { super(values); }
        public static AudioListenerCreatedEvent fromMap(Map<String, Object> values) {
            return new AudioListenerCreatedEvent(values);
        }
        /**
         * Returns the listener field.
         * @return the protocol field value
         */
        public WebAudio.AudioListener listener() {
            return java.util.Objects.requireNonNull(WebAudio.AudioListener.fromMap(java.util.Objects.requireNonNull(CdpObject.objectMap(require("listener")))));
        }
        /**
         * Sets the listener field.
         * @param listener field value
         * @return this model
         */
        public AudioListenerCreatedEvent listener(WebAudio.AudioListener listener) {
            set("listener", listener);
            return this;
        }
    }
    /**
     * Notifies that a new AudioListener has been created.
     */
    public static final class AudioListenerWillBeDestroyedEvent extends CdpObject {
        public AudioListenerWillBeDestroyedEvent() {}
        private AudioListenerWillBeDestroyedEvent(Map<String, Object> values) { super(values); }
        public static AudioListenerWillBeDestroyedEvent fromMap(Map<String, Object> values) {
            return new AudioListenerWillBeDestroyedEvent(values);
        }
        /**
         * Returns the contextId field.
         * @return the protocol field value
         */
        public WebAudio.GraphObjectId contextId() {
            return new WebAudio.GraphObjectId((String) require("contextId"));
        }
        /**
         * Returns the listenerId field.
         * @return the protocol field value
         */
        public WebAudio.GraphObjectId listenerId() {
            return new WebAudio.GraphObjectId((String) require("listenerId"));
        }
        /**
         * Sets the contextId field.
         * @param contextId field value
         * @return this model
         */
        public AudioListenerWillBeDestroyedEvent contextId(WebAudio.GraphObjectId contextId) {
            set("contextId", contextId);
            return this;
        }
        /**
         * Sets the listenerId field.
         * @param listenerId field value
         * @return this model
         */
        public AudioListenerWillBeDestroyedEvent listenerId(WebAudio.GraphObjectId listenerId) {
            set("listenerId", listenerId);
            return this;
        }
    }
    /**
     * Notifies that a new AudioNode has been created.
     */
    public static final class AudioNodeCreatedEvent extends CdpObject {
        public AudioNodeCreatedEvent() {}
        private AudioNodeCreatedEvent(Map<String, Object> values) { super(values); }
        public static AudioNodeCreatedEvent fromMap(Map<String, Object> values) {
            return new AudioNodeCreatedEvent(values);
        }
        /**
         * Returns the node field.
         * @return the protocol field value
         */
        public WebAudio.AudioNode node() {
            return java.util.Objects.requireNonNull(WebAudio.AudioNode.fromMap(java.util.Objects.requireNonNull(CdpObject.objectMap(require("node")))));
        }
        /**
         * Sets the node field.
         * @param node field value
         * @return this model
         */
        public AudioNodeCreatedEvent node(WebAudio.AudioNode node) {
            set("node", node);
            return this;
        }
    }
    /**
     * Notifies that an existing AudioNode has been destroyed.
     */
    public static final class AudioNodeWillBeDestroyedEvent extends CdpObject {
        public AudioNodeWillBeDestroyedEvent() {}
        private AudioNodeWillBeDestroyedEvent(Map<String, Object> values) { super(values); }
        public static AudioNodeWillBeDestroyedEvent fromMap(Map<String, Object> values) {
            return new AudioNodeWillBeDestroyedEvent(values);
        }
        /**
         * Returns the contextId field.
         * @return the protocol field value
         */
        public WebAudio.GraphObjectId contextId() {
            return new WebAudio.GraphObjectId((String) require("contextId"));
        }
        /**
         * Returns the nodeId field.
         * @return the protocol field value
         */
        public WebAudio.GraphObjectId nodeId() {
            return new WebAudio.GraphObjectId((String) require("nodeId"));
        }
        /**
         * Sets the contextId field.
         * @param contextId field value
         * @return this model
         */
        public AudioNodeWillBeDestroyedEvent contextId(WebAudio.GraphObjectId contextId) {
            set("contextId", contextId);
            return this;
        }
        /**
         * Sets the nodeId field.
         * @param nodeId field value
         * @return this model
         */
        public AudioNodeWillBeDestroyedEvent nodeId(WebAudio.GraphObjectId nodeId) {
            set("nodeId", nodeId);
            return this;
        }
    }
    /**
     * Notifies that a new AudioParam has been created.
     */
    public static final class AudioParamCreatedEvent extends CdpObject {
        public AudioParamCreatedEvent() {}
        private AudioParamCreatedEvent(Map<String, Object> values) { super(values); }
        public static AudioParamCreatedEvent fromMap(Map<String, Object> values) {
            return new AudioParamCreatedEvent(values);
        }
        /**
         * Returns the param field.
         * @return the protocol field value
         */
        public WebAudio.AudioParam param() {
            return java.util.Objects.requireNonNull(WebAudio.AudioParam.fromMap(java.util.Objects.requireNonNull(CdpObject.objectMap(require("param")))));
        }
        /**
         * Sets the param field.
         * @param param field value
         * @return this model
         */
        public AudioParamCreatedEvent param(WebAudio.AudioParam param) {
            set("param", param);
            return this;
        }
    }
    /**
     * Notifies that an existing AudioParam has been destroyed.
     */
    public static final class AudioParamWillBeDestroyedEvent extends CdpObject {
        public AudioParamWillBeDestroyedEvent() {}
        private AudioParamWillBeDestroyedEvent(Map<String, Object> values) { super(values); }
        public static AudioParamWillBeDestroyedEvent fromMap(Map<String, Object> values) {
            return new AudioParamWillBeDestroyedEvent(values);
        }
        /**
         * Returns the contextId field.
         * @return the protocol field value
         */
        public WebAudio.GraphObjectId contextId() {
            return new WebAudio.GraphObjectId((String) require("contextId"));
        }
        /**
         * Returns the nodeId field.
         * @return the protocol field value
         */
        public WebAudio.GraphObjectId nodeId() {
            return new WebAudio.GraphObjectId((String) require("nodeId"));
        }
        /**
         * Returns the paramId field.
         * @return the protocol field value
         */
        public WebAudio.GraphObjectId paramId() {
            return new WebAudio.GraphObjectId((String) require("paramId"));
        }
        /**
         * Sets the contextId field.
         * @param contextId field value
         * @return this model
         */
        public AudioParamWillBeDestroyedEvent contextId(WebAudio.GraphObjectId contextId) {
            set("contextId", contextId);
            return this;
        }
        /**
         * Sets the nodeId field.
         * @param nodeId field value
         * @return this model
         */
        public AudioParamWillBeDestroyedEvent nodeId(WebAudio.GraphObjectId nodeId) {
            set("nodeId", nodeId);
            return this;
        }
        /**
         * Sets the paramId field.
         * @param paramId field value
         * @return this model
         */
        public AudioParamWillBeDestroyedEvent paramId(WebAudio.GraphObjectId paramId) {
            set("paramId", paramId);
            return this;
        }
    }
    /**
     * Notifies that two AudioNodes are connected.
     */
    public static final class NodesConnectedEvent extends CdpObject {
        public NodesConnectedEvent() {}
        private NodesConnectedEvent(Map<String, Object> values) { super(values); }
        public static NodesConnectedEvent fromMap(Map<String, Object> values) {
            return new NodesConnectedEvent(values);
        }
        /**
         * Returns the contextId field.
         * @return the protocol field value
         */
        public WebAudio.GraphObjectId contextId() {
            return new WebAudio.GraphObjectId((String) require("contextId"));
        }
        /**
         * Returns the sourceId field.
         * @return the protocol field value
         */
        public WebAudio.GraphObjectId sourceId() {
            return new WebAudio.GraphObjectId((String) require("sourceId"));
        }
        /**
         * Returns the destinationId field.
         * @return the protocol field value
         */
        public WebAudio.GraphObjectId destinationId() {
            return new WebAudio.GraphObjectId((String) require("destinationId"));
        }
        /**
         * Returns the sourceOutputIndex field.
         * @return the protocol field value, empty when absent
         */
        public OptionalDouble sourceOutputIndex() {
            Double value = CdpObject.numberAsDouble(raw("sourceOutputIndex"));
            return value == null ? OptionalDouble.empty() : OptionalDouble.of(value);
        }
        /**
         * Returns the destinationInputIndex field.
         * @return the protocol field value, empty when absent
         */
        public OptionalDouble destinationInputIndex() {
            Double value = CdpObject.numberAsDouble(raw("destinationInputIndex"));
            return value == null ? OptionalDouble.empty() : OptionalDouble.of(value);
        }
        /**
         * Sets the contextId field.
         * @param contextId field value
         * @return this model
         */
        public NodesConnectedEvent contextId(WebAudio.GraphObjectId contextId) {
            set("contextId", contextId);
            return this;
        }
        /**
         * Sets the sourceId field.
         * @param sourceId field value
         * @return this model
         */
        public NodesConnectedEvent sourceId(WebAudio.GraphObjectId sourceId) {
            set("sourceId", sourceId);
            return this;
        }
        /**
         * Sets the destinationId field.
         * @param destinationId field value
         * @return this model
         */
        public NodesConnectedEvent destinationId(WebAudio.GraphObjectId destinationId) {
            set("destinationId", destinationId);
            return this;
        }
        /**
         * Sets the sourceOutputIndex field.
         * @param sourceOutputIndex field value; empty omits the value
         * @return this model
         */
        public NodesConnectedEvent sourceOutputIndex(OptionalDouble sourceOutputIndex) {
            set("sourceOutputIndex", sourceOutputIndex.isPresent() ? sourceOutputIndex.getAsDouble() : null);
            return this;
        }
        /**
         * Sets the sourceOutputIndex field.
         * @param sourceOutputIndex field value; null removes the value
         * @return this model
         */
        public NodesConnectedEvent sourceOutputIndex(Double sourceOutputIndex) {
            set("sourceOutputIndex", sourceOutputIndex);
            return this;
        }
        /**
         * Sets the destinationInputIndex field.
         * @param destinationInputIndex field value; empty omits the value
         * @return this model
         */
        public NodesConnectedEvent destinationInputIndex(OptionalDouble destinationInputIndex) {
            set("destinationInputIndex", destinationInputIndex.isPresent() ? destinationInputIndex.getAsDouble() : null);
            return this;
        }
        /**
         * Sets the destinationInputIndex field.
         * @param destinationInputIndex field value; null removes the value
         * @return this model
         */
        public NodesConnectedEvent destinationInputIndex(Double destinationInputIndex) {
            set("destinationInputIndex", destinationInputIndex);
            return this;
        }
    }
    /**
     * Notifies that AudioNodes are disconnected. The destination can be null, and it means all the outgoing connections from the source are disconnected.
     */
    public static final class NodesDisconnectedEvent extends CdpObject {
        public NodesDisconnectedEvent() {}
        private NodesDisconnectedEvent(Map<String, Object> values) { super(values); }
        public static NodesDisconnectedEvent fromMap(Map<String, Object> values) {
            return new NodesDisconnectedEvent(values);
        }
        /**
         * Returns the contextId field.
         * @return the protocol field value
         */
        public WebAudio.GraphObjectId contextId() {
            return new WebAudio.GraphObjectId((String) require("contextId"));
        }
        /**
         * Returns the sourceId field.
         * @return the protocol field value
         */
        public WebAudio.GraphObjectId sourceId() {
            return new WebAudio.GraphObjectId((String) require("sourceId"));
        }
        /**
         * Returns the destinationId field.
         * @return the protocol field value
         */
        public WebAudio.GraphObjectId destinationId() {
            return new WebAudio.GraphObjectId((String) require("destinationId"));
        }
        /**
         * Returns the sourceOutputIndex field.
         * @return the protocol field value, empty when absent
         */
        public OptionalDouble sourceOutputIndex() {
            Double value = CdpObject.numberAsDouble(raw("sourceOutputIndex"));
            return value == null ? OptionalDouble.empty() : OptionalDouble.of(value);
        }
        /**
         * Returns the destinationInputIndex field.
         * @return the protocol field value, empty when absent
         */
        public OptionalDouble destinationInputIndex() {
            Double value = CdpObject.numberAsDouble(raw("destinationInputIndex"));
            return value == null ? OptionalDouble.empty() : OptionalDouble.of(value);
        }
        /**
         * Sets the contextId field.
         * @param contextId field value
         * @return this model
         */
        public NodesDisconnectedEvent contextId(WebAudio.GraphObjectId contextId) {
            set("contextId", contextId);
            return this;
        }
        /**
         * Sets the sourceId field.
         * @param sourceId field value
         * @return this model
         */
        public NodesDisconnectedEvent sourceId(WebAudio.GraphObjectId sourceId) {
            set("sourceId", sourceId);
            return this;
        }
        /**
         * Sets the destinationId field.
         * @param destinationId field value
         * @return this model
         */
        public NodesDisconnectedEvent destinationId(WebAudio.GraphObjectId destinationId) {
            set("destinationId", destinationId);
            return this;
        }
        /**
         * Sets the sourceOutputIndex field.
         * @param sourceOutputIndex field value; empty omits the value
         * @return this model
         */
        public NodesDisconnectedEvent sourceOutputIndex(OptionalDouble sourceOutputIndex) {
            set("sourceOutputIndex", sourceOutputIndex.isPresent() ? sourceOutputIndex.getAsDouble() : null);
            return this;
        }
        /**
         * Sets the sourceOutputIndex field.
         * @param sourceOutputIndex field value; null removes the value
         * @return this model
         */
        public NodesDisconnectedEvent sourceOutputIndex(Double sourceOutputIndex) {
            set("sourceOutputIndex", sourceOutputIndex);
            return this;
        }
        /**
         * Sets the destinationInputIndex field.
         * @param destinationInputIndex field value; empty omits the value
         * @return this model
         */
        public NodesDisconnectedEvent destinationInputIndex(OptionalDouble destinationInputIndex) {
            set("destinationInputIndex", destinationInputIndex.isPresent() ? destinationInputIndex.getAsDouble() : null);
            return this;
        }
        /**
         * Sets the destinationInputIndex field.
         * @param destinationInputIndex field value; null removes the value
         * @return this model
         */
        public NodesDisconnectedEvent destinationInputIndex(Double destinationInputIndex) {
            set("destinationInputIndex", destinationInputIndex);
            return this;
        }
    }
    /**
     * Notifies that an AudioNode is connected to an AudioParam.
     */
    public static final class NodeParamConnectedEvent extends CdpObject {
        public NodeParamConnectedEvent() {}
        private NodeParamConnectedEvent(Map<String, Object> values) { super(values); }
        public static NodeParamConnectedEvent fromMap(Map<String, Object> values) {
            return new NodeParamConnectedEvent(values);
        }
        /**
         * Returns the contextId field.
         * @return the protocol field value
         */
        public WebAudio.GraphObjectId contextId() {
            return new WebAudio.GraphObjectId((String) require("contextId"));
        }
        /**
         * Returns the sourceId field.
         * @return the protocol field value
         */
        public WebAudio.GraphObjectId sourceId() {
            return new WebAudio.GraphObjectId((String) require("sourceId"));
        }
        /**
         * Returns the destinationId field.
         * @return the protocol field value
         */
        public WebAudio.GraphObjectId destinationId() {
            return new WebAudio.GraphObjectId((String) require("destinationId"));
        }
        /**
         * Returns the sourceOutputIndex field.
         * @return the protocol field value, empty when absent
         */
        public OptionalDouble sourceOutputIndex() {
            Double value = CdpObject.numberAsDouble(raw("sourceOutputIndex"));
            return value == null ? OptionalDouble.empty() : OptionalDouble.of(value);
        }
        /**
         * Sets the contextId field.
         * @param contextId field value
         * @return this model
         */
        public NodeParamConnectedEvent contextId(WebAudio.GraphObjectId contextId) {
            set("contextId", contextId);
            return this;
        }
        /**
         * Sets the sourceId field.
         * @param sourceId field value
         * @return this model
         */
        public NodeParamConnectedEvent sourceId(WebAudio.GraphObjectId sourceId) {
            set("sourceId", sourceId);
            return this;
        }
        /**
         * Sets the destinationId field.
         * @param destinationId field value
         * @return this model
         */
        public NodeParamConnectedEvent destinationId(WebAudio.GraphObjectId destinationId) {
            set("destinationId", destinationId);
            return this;
        }
        /**
         * Sets the sourceOutputIndex field.
         * @param sourceOutputIndex field value; empty omits the value
         * @return this model
         */
        public NodeParamConnectedEvent sourceOutputIndex(OptionalDouble sourceOutputIndex) {
            set("sourceOutputIndex", sourceOutputIndex.isPresent() ? sourceOutputIndex.getAsDouble() : null);
            return this;
        }
        /**
         * Sets the sourceOutputIndex field.
         * @param sourceOutputIndex field value; null removes the value
         * @return this model
         */
        public NodeParamConnectedEvent sourceOutputIndex(Double sourceOutputIndex) {
            set("sourceOutputIndex", sourceOutputIndex);
            return this;
        }
    }
    /**
     * Notifies that an AudioNode is disconnected to an AudioParam.
     */
    public static final class NodeParamDisconnectedEvent extends CdpObject {
        public NodeParamDisconnectedEvent() {}
        private NodeParamDisconnectedEvent(Map<String, Object> values) { super(values); }
        public static NodeParamDisconnectedEvent fromMap(Map<String, Object> values) {
            return new NodeParamDisconnectedEvent(values);
        }
        /**
         * Returns the contextId field.
         * @return the protocol field value
         */
        public WebAudio.GraphObjectId contextId() {
            return new WebAudio.GraphObjectId((String) require("contextId"));
        }
        /**
         * Returns the sourceId field.
         * @return the protocol field value
         */
        public WebAudio.GraphObjectId sourceId() {
            return new WebAudio.GraphObjectId((String) require("sourceId"));
        }
        /**
         * Returns the destinationId field.
         * @return the protocol field value
         */
        public WebAudio.GraphObjectId destinationId() {
            return new WebAudio.GraphObjectId((String) require("destinationId"));
        }
        /**
         * Returns the sourceOutputIndex field.
         * @return the protocol field value, empty when absent
         */
        public OptionalDouble sourceOutputIndex() {
            Double value = CdpObject.numberAsDouble(raw("sourceOutputIndex"));
            return value == null ? OptionalDouble.empty() : OptionalDouble.of(value);
        }
        /**
         * Sets the contextId field.
         * @param contextId field value
         * @return this model
         */
        public NodeParamDisconnectedEvent contextId(WebAudio.GraphObjectId contextId) {
            set("contextId", contextId);
            return this;
        }
        /**
         * Sets the sourceId field.
         * @param sourceId field value
         * @return this model
         */
        public NodeParamDisconnectedEvent sourceId(WebAudio.GraphObjectId sourceId) {
            set("sourceId", sourceId);
            return this;
        }
        /**
         * Sets the destinationId field.
         * @param destinationId field value
         * @return this model
         */
        public NodeParamDisconnectedEvent destinationId(WebAudio.GraphObjectId destinationId) {
            set("destinationId", destinationId);
            return this;
        }
        /**
         * Sets the sourceOutputIndex field.
         * @param sourceOutputIndex field value; empty omits the value
         * @return this model
         */
        public NodeParamDisconnectedEvent sourceOutputIndex(OptionalDouble sourceOutputIndex) {
            set("sourceOutputIndex", sourceOutputIndex.isPresent() ? sourceOutputIndex.getAsDouble() : null);
            return this;
        }
        /**
         * Sets the sourceOutputIndex field.
         * @param sourceOutputIndex field value; null removes the value
         * @return this model
         */
        public NodeParamDisconnectedEvent sourceOutputIndex(Double sourceOutputIndex) {
            set("sourceOutputIndex", sourceOutputIndex);
            return this;
        }
    }
    public static final class Client {
        private final CdpClient client;
        public Client(CdpClient client) { this.client = client; }
        /**
         * Enables the WebAudio domain and starts sending context lifetime events.
         * @return a stage completing when the command completes
         */
        public CompletionStage<Void> enable() {
            return client.call("WebAudio.enable", null, result_ -> null);
        }
        /**
         * Disables the WebAudio domain.
         * @return a stage completing when the command completes
         */
        public CompletionStage<Void> disable() {
            return client.call("WebAudio.disable", null, result_ -> null);
        }
        /**
         * Fetch the realtime data from the registered contexts.
         * @param contextId protocol value
         * @return a stage completing with the command result
         */
        public CompletionStage<WebAudio.ContextRealtimeData> getRealtimeData(WebAudio.GraphObjectId contextId) {
            Map<String, Object> params = new LinkedHashMap<>();
            params.put("contextId", CdpObject.json(contextId));
            return client.call("WebAudio.getRealtimeData", params, result_ -> java.util.Objects.requireNonNull(WebAudio.ContextRealtimeData.fromMap(java.util.Objects.requireNonNull(CdpObject.objectMap(java.util.Objects.requireNonNull(result_.get("realtimeData")))))));
        }
        /**
         * Fetch the realtime data from the registered contexts.
         * @param request request parameters
         * @return a stage completing with the command result
         */
        public CompletionStage<WebAudio.ContextRealtimeData> getRealtimeData(GetRealtimeDataRequest request) {
            return client.call("WebAudio.getRealtimeData", request == null ? null : request.toMap(), result_ -> java.util.Objects.requireNonNull(WebAudio.ContextRealtimeData.fromMap(java.util.Objects.requireNonNull(CdpObject.objectMap(java.util.Objects.requireNonNull(result_.get("realtimeData")))))));
        }
        /**
         * Notifies that a new BaseAudioContext has been created.
         * @param handler event callback
         * @return a removable subscription
         */
        public CdpSubscription onContextCreated(Consumer<ContextCreatedEvent> handler) {
            return client.on("WebAudio.contextCreated", ContextCreatedEvent::fromMap, handler);
        }
        /**
         * Notifies that an existing BaseAudioContext will be destroyed.
         * @param handler event callback
         * @return a removable subscription
         */
        public CdpSubscription onContextWillBeDestroyed(Consumer<ContextWillBeDestroyedEvent> handler) {
            return client.on("WebAudio.contextWillBeDestroyed", ContextWillBeDestroyedEvent::fromMap, handler);
        }
        /**
         * Notifies that existing BaseAudioContext has changed some properties (id stays the same)..
         * @param handler event callback
         * @return a removable subscription
         */
        public CdpSubscription onContextChanged(Consumer<ContextChangedEvent> handler) {
            return client.on("WebAudio.contextChanged", ContextChangedEvent::fromMap, handler);
        }
        /**
         * Notifies that the construction of an AudioListener has finished.
         * @param handler event callback
         * @return a removable subscription
         */
        public CdpSubscription onAudioListenerCreated(Consumer<AudioListenerCreatedEvent> handler) {
            return client.on("WebAudio.audioListenerCreated", AudioListenerCreatedEvent::fromMap, handler);
        }
        /**
         * Notifies that a new AudioListener has been created.
         * @param handler event callback
         * @return a removable subscription
         */
        public CdpSubscription onAudioListenerWillBeDestroyed(Consumer<AudioListenerWillBeDestroyedEvent> handler) {
            return client.on("WebAudio.audioListenerWillBeDestroyed", AudioListenerWillBeDestroyedEvent::fromMap, handler);
        }
        /**
         * Notifies that a new AudioNode has been created.
         * @param handler event callback
         * @return a removable subscription
         */
        public CdpSubscription onAudioNodeCreated(Consumer<AudioNodeCreatedEvent> handler) {
            return client.on("WebAudio.audioNodeCreated", AudioNodeCreatedEvent::fromMap, handler);
        }
        /**
         * Notifies that an existing AudioNode has been destroyed.
         * @param handler event callback
         * @return a removable subscription
         */
        public CdpSubscription onAudioNodeWillBeDestroyed(Consumer<AudioNodeWillBeDestroyedEvent> handler) {
            return client.on("WebAudio.audioNodeWillBeDestroyed", AudioNodeWillBeDestroyedEvent::fromMap, handler);
        }
        /**
         * Notifies that a new AudioParam has been created.
         * @param handler event callback
         * @return a removable subscription
         */
        public CdpSubscription onAudioParamCreated(Consumer<AudioParamCreatedEvent> handler) {
            return client.on("WebAudio.audioParamCreated", AudioParamCreatedEvent::fromMap, handler);
        }
        /**
         * Notifies that an existing AudioParam has been destroyed.
         * @param handler event callback
         * @return a removable subscription
         */
        public CdpSubscription onAudioParamWillBeDestroyed(Consumer<AudioParamWillBeDestroyedEvent> handler) {
            return client.on("WebAudio.audioParamWillBeDestroyed", AudioParamWillBeDestroyedEvent::fromMap, handler);
        }
        /**
         * Notifies that two AudioNodes are connected.
         * @param handler event callback
         * @return a removable subscription
         */
        public CdpSubscription onNodesConnected(Consumer<NodesConnectedEvent> handler) {
            return client.on("WebAudio.nodesConnected", NodesConnectedEvent::fromMap, handler);
        }
        /**
         * Notifies that AudioNodes are disconnected. The destination can be null, and it means all the outgoing connections from the source are disconnected.
         * @param handler event callback
         * @return a removable subscription
         */
        public CdpSubscription onNodesDisconnected(Consumer<NodesDisconnectedEvent> handler) {
            return client.on("WebAudio.nodesDisconnected", NodesDisconnectedEvent::fromMap, handler);
        }
        /**
         * Notifies that an AudioNode is connected to an AudioParam.
         * @param handler event callback
         * @return a removable subscription
         */
        public CdpSubscription onNodeParamConnected(Consumer<NodeParamConnectedEvent> handler) {
            return client.on("WebAudio.nodeParamConnected", NodeParamConnectedEvent::fromMap, handler);
        }
        /**
         * Notifies that an AudioNode is disconnected to an AudioParam.
         * @param handler event callback
         * @return a removable subscription
         */
        public CdpSubscription onNodeParamDisconnected(Consumer<NodeParamDisconnectedEvent> handler) {
            return client.on("WebAudio.nodeParamDisconnected", NodeParamDisconnectedEvent::fromMap, handler);
        }
    }
}
