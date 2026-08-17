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
 * This domain allows inspection of Web Audio API. https://webaudio.github.io/web-audio-api/
 * <p><b>Experimental:</b> this part of CDP may change without notice.
 * @see <a href="https://chromium.googlesource.com/chromium/src/+/refs/tags/150.0.7871.213/third_party/blink/public/devtools_protocol/domains/WebAudio.pdl">Pinned protocol source</a>
 */
@SuppressWarnings({"EscapedEntity", "JavaLangClash", "MissingSummary", "UnusedMethod"})
public final class WebAudio {
    private WebAudio() {}
    @Nullable private static Long numberAsLong(@Nullable Object value) { return value == null ? null : ((Number) value).longValue(); }
    @Nullable private static Double numberAsDouble(@Nullable Object value) { return value == null ? null : ((Number) value).doubleValue(); }
    /**
     * Enum of BaseAudioContext types
     */
    public static final class ContextType {
        private ContextType() {}
        public static final String REALTIME = "realtime";
        public static final String OFFLINE = "offline";
    }
    /**
     * Enum of AudioContextState from the spec
     */
    public static final class ContextState {
        private ContextState() {}
        public static final String SUSPENDED = "suspended";
        public static final String RUNNING = "running";
        public static final String CLOSED = "closed";
        public static final String INTERRUPTED = "interrupted";
    }
    /**
     * Enum of AudioNode::ChannelCountMode from the spec
     */
    public static final class ChannelCountMode {
        private ChannelCountMode() {}
        public static final String CLAMPED_MAX = "clamped-max";
        public static final String EXPLICIT = "explicit";
        public static final String MAX = "max";
    }
    /**
     * Enum of AudioNode::ChannelInterpretation from the spec
     */
    public static final class ChannelInterpretation {
        private ChannelInterpretation() {}
        public static final String DISCRETE = "discrete";
        public static final String SPEAKERS = "speakers";
    }
    /**
     * Enum of AudioParam::AutomationRate from the spec
     */
    public static final class AutomationRate {
        private AutomationRate() {}
        public static final String A_RATE = "a-rate";
        public static final String K_RATE = "k-rate";
    }
    /**
     * Fields in AudioContext that change in real-time.
     */
    public static final class ContextRealtimeData extends CdpObject {
        private ContextRealtimeData(Map<String, Object> values) { super(values); }
        @Nullable public static ContextRealtimeData fromMap(@Nullable Map<String, Object> values) {
            return values == null ? null : new ContextRealtimeData(values);
        }
        public static Builder builder() { return new Builder(); }
        /**
         * The current context time in second in BaseAudioContext.
         * @return the protocol field value
         */
        @Nullable public Double currentTime() {
            return numberAsDouble(value("currentTime"));
        }
        /**
         * The time spent on rendering graph divided by render quantum duration, and multiplied by 100. 100 means the audio renderer reached the full capacity and glitch may occur.
         * @return the protocol field value
         */
        @Nullable public Double renderCapacity() {
            return numberAsDouble(value("renderCapacity"));
        }
        /**
         * A running mean of callback interval.
         * @return the protocol field value
         */
        @Nullable public Double callbackIntervalMean() {
            return numberAsDouble(value("callbackIntervalMean"));
        }
        /**
         * A running variance of callback interval.
         * @return the protocol field value
         */
        @Nullable public Double callbackIntervalVariance() {
            return numberAsDouble(value("callbackIntervalVariance"));
        }
        public static final class Builder {
            private final Map<String, Object> values = new LinkedHashMap<>();
            /**
             * The current context time in second in BaseAudioContext.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder currentTime(@Nullable Double value) {
                if (value == null) values.remove("currentTime");
                else values.put("currentTime", jsonValue(value));
                return this;
            }
            /**
             * The time spent on rendering graph divided by render quantum duration, and multiplied by 100. 100 means the audio renderer reached the full capacity and glitch may occur.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder renderCapacity(@Nullable Double value) {
                if (value == null) values.remove("renderCapacity");
                else values.put("renderCapacity", jsonValue(value));
                return this;
            }
            /**
             * A running mean of callback interval.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder callbackIntervalMean(@Nullable Double value) {
                if (value == null) values.remove("callbackIntervalMean");
                else values.put("callbackIntervalMean", jsonValue(value));
                return this;
            }
            /**
             * A running variance of callback interval.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder callbackIntervalVariance(@Nullable Double value) {
                if (value == null) values.remove("callbackIntervalVariance");
                else values.put("callbackIntervalVariance", jsonValue(value));
                return this;
            }
            public ContextRealtimeData build() {
                if (!values.containsKey("currentTime")) throw new IllegalStateException("Missing required CDP field: currentTime");
                if (!values.containsKey("renderCapacity")) throw new IllegalStateException("Missing required CDP field: renderCapacity");
                if (!values.containsKey("callbackIntervalMean")) throw new IllegalStateException("Missing required CDP field: callbackIntervalMean");
                if (!values.containsKey("callbackIntervalVariance")) throw new IllegalStateException("Missing required CDP field: callbackIntervalVariance");
                return new ContextRealtimeData(values);
            }
        }
    }
    /**
     * Protocol object for BaseAudioContext
     */
    public static final class BaseAudioContext extends CdpObject {
        private BaseAudioContext(Map<String, Object> values) { super(values); }
        @Nullable public static BaseAudioContext fromMap(@Nullable Map<String, Object> values) {
            return values == null ? null : new BaseAudioContext(values);
        }
        public static Builder builder() { return new Builder(); }
        /**
         * Returns the contextId field.
         * @return the protocol field value
         */
        @Nullable public String contextId() {
            return (String) value("contextId");
        }
        /**
         * Returns the contextType field.
         * @return the protocol field value
         */
        @Nullable public String contextType() {
            return (String) value("contextType");
        }
        /**
         * Returns the contextState field.
         * @return the protocol field value
         */
        @Nullable public String contextState() {
            return (String) value("contextState");
        }
        /**
         * Returns the realtimeData field.
         * @return the protocol field value
         */
        @Nullable public WebAudio.ContextRealtimeData realtimeData() {
            return WebAudio.ContextRealtimeData.fromMap(objectMap(value("realtimeData")));
        }
        /**
         * Platform-dependent callback buffer size.
         * @return the protocol field value
         */
        @Nullable public Double callbackBufferSize() {
            return numberAsDouble(value("callbackBufferSize"));
        }
        /**
         * Number of output channels supported by audio hardware in use.
         * @return the protocol field value
         */
        @Nullable public Double maxOutputChannelCount() {
            return numberAsDouble(value("maxOutputChannelCount"));
        }
        /**
         * Context sample rate.
         * @return the protocol field value
         */
        @Nullable public Double sampleRate() {
            return numberAsDouble(value("sampleRate"));
        }
        public static final class Builder {
            private final Map<String, Object> values = new LinkedHashMap<>();
            /**
             * Sets the contextId field.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder contextId(@Nullable String value) {
                if (value == null) values.remove("contextId");
                else values.put("contextId", jsonValue(value));
                return this;
            }
            /**
             * Sets the contextType field.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder contextType(@Nullable String value) {
                if (value == null) values.remove("contextType");
                else values.put("contextType", jsonValue(value));
                return this;
            }
            /**
             * Sets the contextState field.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder contextState(@Nullable String value) {
                if (value == null) values.remove("contextState");
                else values.put("contextState", jsonValue(value));
                return this;
            }
            /**
             * Sets the realtimeData field.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder realtimeData(@Nullable WebAudio.ContextRealtimeData value) {
                if (value == null) values.remove("realtimeData");
                else values.put("realtimeData", jsonValue(value));
                return this;
            }
            /**
             * Platform-dependent callback buffer size.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder callbackBufferSize(@Nullable Double value) {
                if (value == null) values.remove("callbackBufferSize");
                else values.put("callbackBufferSize", jsonValue(value));
                return this;
            }
            /**
             * Number of output channels supported by audio hardware in use.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder maxOutputChannelCount(@Nullable Double value) {
                if (value == null) values.remove("maxOutputChannelCount");
                else values.put("maxOutputChannelCount", jsonValue(value));
                return this;
            }
            /**
             * Context sample rate.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder sampleRate(@Nullable Double value) {
                if (value == null) values.remove("sampleRate");
                else values.put("sampleRate", jsonValue(value));
                return this;
            }
            public BaseAudioContext build() {
                if (!values.containsKey("contextId")) throw new IllegalStateException("Missing required CDP field: contextId");
                if (!values.containsKey("contextType")) throw new IllegalStateException("Missing required CDP field: contextType");
                if (!values.containsKey("contextState")) throw new IllegalStateException("Missing required CDP field: contextState");
                if (!values.containsKey("callbackBufferSize")) throw new IllegalStateException("Missing required CDP field: callbackBufferSize");
                if (!values.containsKey("maxOutputChannelCount")) throw new IllegalStateException("Missing required CDP field: maxOutputChannelCount");
                if (!values.containsKey("sampleRate")) throw new IllegalStateException("Missing required CDP field: sampleRate");
                return new BaseAudioContext(values);
            }
        }
    }
    /**
     * Protocol object for AudioListener
     */
    public static final class AudioListener extends CdpObject {
        private AudioListener(Map<String, Object> values) { super(values); }
        @Nullable public static AudioListener fromMap(@Nullable Map<String, Object> values) {
            return values == null ? null : new AudioListener(values);
        }
        public static Builder builder() { return new Builder(); }
        /**
         * Returns the listenerId field.
         * @return the protocol field value
         */
        @Nullable public String listenerId() {
            return (String) value("listenerId");
        }
        /**
         * Returns the contextId field.
         * @return the protocol field value
         */
        @Nullable public String contextId() {
            return (String) value("contextId");
        }
        public static final class Builder {
            private final Map<String, Object> values = new LinkedHashMap<>();
            /**
             * Sets the listenerId field.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder listenerId(@Nullable String value) {
                if (value == null) values.remove("listenerId");
                else values.put("listenerId", jsonValue(value));
                return this;
            }
            /**
             * Sets the contextId field.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder contextId(@Nullable String value) {
                if (value == null) values.remove("contextId");
                else values.put("contextId", jsonValue(value));
                return this;
            }
            public AudioListener build() {
                if (!values.containsKey("listenerId")) throw new IllegalStateException("Missing required CDP field: listenerId");
                if (!values.containsKey("contextId")) throw new IllegalStateException("Missing required CDP field: contextId");
                return new AudioListener(values);
            }
        }
    }
    /**
     * Protocol object for AudioNode
     */
    public static final class AudioNode extends CdpObject {
        private AudioNode(Map<String, Object> values) { super(values); }
        @Nullable public static AudioNode fromMap(@Nullable Map<String, Object> values) {
            return values == null ? null : new AudioNode(values);
        }
        public static Builder builder() { return new Builder(); }
        /**
         * Returns the nodeId field.
         * @return the protocol field value
         */
        @Nullable public String nodeId() {
            return (String) value("nodeId");
        }
        /**
         * Returns the contextId field.
         * @return the protocol field value
         */
        @Nullable public String contextId() {
            return (String) value("contextId");
        }
        /**
         * Returns the nodeType field.
         * @return the protocol field value
         */
        @Nullable public String nodeType() {
            return (String) value("nodeType");
        }
        /**
         * Returns the numberOfInputs field.
         * @return the protocol field value
         */
        @Nullable public Double numberOfInputs() {
            return numberAsDouble(value("numberOfInputs"));
        }
        /**
         * Returns the numberOfOutputs field.
         * @return the protocol field value
         */
        @Nullable public Double numberOfOutputs() {
            return numberAsDouble(value("numberOfOutputs"));
        }
        /**
         * Returns the channelCount field.
         * @return the protocol field value
         */
        @Nullable public Double channelCount() {
            return numberAsDouble(value("channelCount"));
        }
        /**
         * Returns the channelCountMode field.
         * @return the protocol field value
         */
        @Nullable public String channelCountMode() {
            return (String) value("channelCountMode");
        }
        /**
         * Returns the channelInterpretation field.
         * @return the protocol field value
         */
        @Nullable public String channelInterpretation() {
            return (String) value("channelInterpretation");
        }
        public static final class Builder {
            private final Map<String, Object> values = new LinkedHashMap<>();
            /**
             * Sets the nodeId field.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder nodeId(@Nullable String value) {
                if (value == null) values.remove("nodeId");
                else values.put("nodeId", jsonValue(value));
                return this;
            }
            /**
             * Sets the contextId field.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder contextId(@Nullable String value) {
                if (value == null) values.remove("contextId");
                else values.put("contextId", jsonValue(value));
                return this;
            }
            /**
             * Sets the nodeType field.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder nodeType(@Nullable String value) {
                if (value == null) values.remove("nodeType");
                else values.put("nodeType", jsonValue(value));
                return this;
            }
            /**
             * Sets the numberOfInputs field.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder numberOfInputs(@Nullable Double value) {
                if (value == null) values.remove("numberOfInputs");
                else values.put("numberOfInputs", jsonValue(value));
                return this;
            }
            /**
             * Sets the numberOfOutputs field.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder numberOfOutputs(@Nullable Double value) {
                if (value == null) values.remove("numberOfOutputs");
                else values.put("numberOfOutputs", jsonValue(value));
                return this;
            }
            /**
             * Sets the channelCount field.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder channelCount(@Nullable Double value) {
                if (value == null) values.remove("channelCount");
                else values.put("channelCount", jsonValue(value));
                return this;
            }
            /**
             * Sets the channelCountMode field.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder channelCountMode(@Nullable String value) {
                if (value == null) values.remove("channelCountMode");
                else values.put("channelCountMode", jsonValue(value));
                return this;
            }
            /**
             * Sets the channelInterpretation field.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder channelInterpretation(@Nullable String value) {
                if (value == null) values.remove("channelInterpretation");
                else values.put("channelInterpretation", jsonValue(value));
                return this;
            }
            public AudioNode build() {
                if (!values.containsKey("nodeId")) throw new IllegalStateException("Missing required CDP field: nodeId");
                if (!values.containsKey("contextId")) throw new IllegalStateException("Missing required CDP field: contextId");
                if (!values.containsKey("nodeType")) throw new IllegalStateException("Missing required CDP field: nodeType");
                if (!values.containsKey("numberOfInputs")) throw new IllegalStateException("Missing required CDP field: numberOfInputs");
                if (!values.containsKey("numberOfOutputs")) throw new IllegalStateException("Missing required CDP field: numberOfOutputs");
                if (!values.containsKey("channelCount")) throw new IllegalStateException("Missing required CDP field: channelCount");
                if (!values.containsKey("channelCountMode")) throw new IllegalStateException("Missing required CDP field: channelCountMode");
                if (!values.containsKey("channelInterpretation")) throw new IllegalStateException("Missing required CDP field: channelInterpretation");
                return new AudioNode(values);
            }
        }
    }
    /**
     * Protocol object for AudioParam
     */
    public static final class AudioParam extends CdpObject {
        private AudioParam(Map<String, Object> values) { super(values); }
        @Nullable public static AudioParam fromMap(@Nullable Map<String, Object> values) {
            return values == null ? null : new AudioParam(values);
        }
        public static Builder builder() { return new Builder(); }
        /**
         * Returns the paramId field.
         * @return the protocol field value
         */
        @Nullable public String paramId() {
            return (String) value("paramId");
        }
        /**
         * Returns the nodeId field.
         * @return the protocol field value
         */
        @Nullable public String nodeId() {
            return (String) value("nodeId");
        }
        /**
         * Returns the contextId field.
         * @return the protocol field value
         */
        @Nullable public String contextId() {
            return (String) value("contextId");
        }
        /**
         * Returns the paramType field.
         * @return the protocol field value
         */
        @Nullable public String paramType() {
            return (String) value("paramType");
        }
        /**
         * Returns the rate field.
         * @return the protocol field value
         */
        @Nullable public String rate() {
            return (String) value("rate");
        }
        /**
         * Returns the defaultValue field.
         * @return the protocol field value
         */
        @Nullable public Double defaultValue() {
            return numberAsDouble(value("defaultValue"));
        }
        /**
         * Returns the minValue field.
         * @return the protocol field value
         */
        @Nullable public Double minValue() {
            return numberAsDouble(value("minValue"));
        }
        /**
         * Returns the maxValue field.
         * @return the protocol field value
         */
        @Nullable public Double maxValue() {
            return numberAsDouble(value("maxValue"));
        }
        public static final class Builder {
            private final Map<String, Object> values = new LinkedHashMap<>();
            /**
             * Sets the paramId field.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder paramId(@Nullable String value) {
                if (value == null) values.remove("paramId");
                else values.put("paramId", jsonValue(value));
                return this;
            }
            /**
             * Sets the nodeId field.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder nodeId(@Nullable String value) {
                if (value == null) values.remove("nodeId");
                else values.put("nodeId", jsonValue(value));
                return this;
            }
            /**
             * Sets the contextId field.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder contextId(@Nullable String value) {
                if (value == null) values.remove("contextId");
                else values.put("contextId", jsonValue(value));
                return this;
            }
            /**
             * Sets the paramType field.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder paramType(@Nullable String value) {
                if (value == null) values.remove("paramType");
                else values.put("paramType", jsonValue(value));
                return this;
            }
            /**
             * Sets the rate field.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder rate(@Nullable String value) {
                if (value == null) values.remove("rate");
                else values.put("rate", jsonValue(value));
                return this;
            }
            /**
             * Sets the defaultValue field.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder defaultValue(@Nullable Double value) {
                if (value == null) values.remove("defaultValue");
                else values.put("defaultValue", jsonValue(value));
                return this;
            }
            /**
             * Sets the minValue field.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder minValue(@Nullable Double value) {
                if (value == null) values.remove("minValue");
                else values.put("minValue", jsonValue(value));
                return this;
            }
            /**
             * Sets the maxValue field.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder maxValue(@Nullable Double value) {
                if (value == null) values.remove("maxValue");
                else values.put("maxValue", jsonValue(value));
                return this;
            }
            public AudioParam build() {
                if (!values.containsKey("paramId")) throw new IllegalStateException("Missing required CDP field: paramId");
                if (!values.containsKey("nodeId")) throw new IllegalStateException("Missing required CDP field: nodeId");
                if (!values.containsKey("contextId")) throw new IllegalStateException("Missing required CDP field: contextId");
                if (!values.containsKey("paramType")) throw new IllegalStateException("Missing required CDP field: paramType");
                if (!values.containsKey("rate")) throw new IllegalStateException("Missing required CDP field: rate");
                if (!values.containsKey("defaultValue")) throw new IllegalStateException("Missing required CDP field: defaultValue");
                if (!values.containsKey("minValue")) throw new IllegalStateException("Missing required CDP field: minValue");
                if (!values.containsKey("maxValue")) throw new IllegalStateException("Missing required CDP field: maxValue");
                return new AudioParam(values);
            }
        }
    }
    /**
     * Enables the WebAudio domain and starts sending context lifetime events.
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
     * Enables the WebAudio domain and starts sending context lifetime events.
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
     * Disables the WebAudio domain.
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
     * Disables the WebAudio domain.
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
     * Fetch the realtime data from the registered contexts.
     */
    public static final class GetRealtimeDataParams extends CdpObject {
        private GetRealtimeDataParams(Map<String, Object> values) { super(values); }
        @Nullable public static GetRealtimeDataParams fromMap(@Nullable Map<String, Object> values) {
            return values == null ? null : new GetRealtimeDataParams(values);
        }
        public static Builder builder() { return new Builder(); }
        /**
         * Returns the contextId field.
         * @return the protocol field value
         */
        @Nullable public String contextId() {
            return (String) value("contextId");
        }
        public static final class Builder {
            private final Map<String, Object> values = new LinkedHashMap<>();
            /**
             * Sets the contextId field.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder contextId(@Nullable String value) {
                if (value == null) values.remove("contextId");
                else values.put("contextId", jsonValue(value));
                return this;
            }
            public GetRealtimeDataParams build() {
                if (!values.containsKey("contextId")) throw new IllegalStateException("Missing required CDP field: contextId");
                return new GetRealtimeDataParams(values);
            }
        }
    }
    /**
     * Fetch the realtime data from the registered contexts.
     */
    public static final class GetRealtimeDataResult extends CdpObject {
        private GetRealtimeDataResult(Map<String, Object> values) { super(values); }
        @Nullable public static GetRealtimeDataResult fromMap(@Nullable Map<String, Object> values) {
            return values == null ? null : new GetRealtimeDataResult(values);
        }
        public static Builder builder() { return new Builder(); }
        /**
         * Returns the realtimeData field.
         * @return the protocol field value
         */
        @Nullable public WebAudio.ContextRealtimeData realtimeData() {
            return WebAudio.ContextRealtimeData.fromMap(objectMap(value("realtimeData")));
        }
        public static final class Builder {
            private final Map<String, Object> values = new LinkedHashMap<>();
            /**
             * Sets the realtimeData field.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder realtimeData(@Nullable WebAudio.ContextRealtimeData value) {
                if (value == null) values.remove("realtimeData");
                else values.put("realtimeData", jsonValue(value));
                return this;
            }
            public GetRealtimeDataResult build() {
                if (!values.containsKey("realtimeData")) throw new IllegalStateException("Missing required CDP field: realtimeData");
                return new GetRealtimeDataResult(values);
            }
        }
    }
    /**
     * Notifies that a new BaseAudioContext has been created.
     */
    public static final class ContextCreatedEvent extends CdpObject {
        private ContextCreatedEvent(Map<String, Object> values) { super(values); }
        @Nullable public static ContextCreatedEvent fromMap(@Nullable Map<String, Object> values) {
            return values == null ? null : new ContextCreatedEvent(values);
        }
        public static Builder builder() { return new Builder(); }
        /**
         * Returns the context field.
         * @return the protocol field value
         */
        @Nullable public WebAudio.BaseAudioContext context() {
            return WebAudio.BaseAudioContext.fromMap(objectMap(value("context")));
        }
        public static final class Builder {
            private final Map<String, Object> values = new LinkedHashMap<>();
            /**
             * Sets the context field.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder context(@Nullable WebAudio.BaseAudioContext value) {
                if (value == null) values.remove("context");
                else values.put("context", jsonValue(value));
                return this;
            }
            public ContextCreatedEvent build() {
                if (!values.containsKey("context")) throw new IllegalStateException("Missing required CDP field: context");
                return new ContextCreatedEvent(values);
            }
        }
    }
    /**
     * Notifies that an existing BaseAudioContext will be destroyed.
     */
    public static final class ContextWillBeDestroyedEvent extends CdpObject {
        private ContextWillBeDestroyedEvent(Map<String, Object> values) { super(values); }
        @Nullable public static ContextWillBeDestroyedEvent fromMap(@Nullable Map<String, Object> values) {
            return values == null ? null : new ContextWillBeDestroyedEvent(values);
        }
        public static Builder builder() { return new Builder(); }
        /**
         * Returns the contextId field.
         * @return the protocol field value
         */
        @Nullable public String contextId() {
            return (String) value("contextId");
        }
        public static final class Builder {
            private final Map<String, Object> values = new LinkedHashMap<>();
            /**
             * Sets the contextId field.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder contextId(@Nullable String value) {
                if (value == null) values.remove("contextId");
                else values.put("contextId", jsonValue(value));
                return this;
            }
            public ContextWillBeDestroyedEvent build() {
                if (!values.containsKey("contextId")) throw new IllegalStateException("Missing required CDP field: contextId");
                return new ContextWillBeDestroyedEvent(values);
            }
        }
    }
    /**
     * Notifies that existing BaseAudioContext has changed some properties (id stays the same)..
     */
    public static final class ContextChangedEvent extends CdpObject {
        private ContextChangedEvent(Map<String, Object> values) { super(values); }
        @Nullable public static ContextChangedEvent fromMap(@Nullable Map<String, Object> values) {
            return values == null ? null : new ContextChangedEvent(values);
        }
        public static Builder builder() { return new Builder(); }
        /**
         * Returns the context field.
         * @return the protocol field value
         */
        @Nullable public WebAudio.BaseAudioContext context() {
            return WebAudio.BaseAudioContext.fromMap(objectMap(value("context")));
        }
        public static final class Builder {
            private final Map<String, Object> values = new LinkedHashMap<>();
            /**
             * Sets the context field.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder context(@Nullable WebAudio.BaseAudioContext value) {
                if (value == null) values.remove("context");
                else values.put("context", jsonValue(value));
                return this;
            }
            public ContextChangedEvent build() {
                if (!values.containsKey("context")) throw new IllegalStateException("Missing required CDP field: context");
                return new ContextChangedEvent(values);
            }
        }
    }
    /**
     * Notifies that the construction of an AudioListener has finished.
     */
    public static final class AudioListenerCreatedEvent extends CdpObject {
        private AudioListenerCreatedEvent(Map<String, Object> values) { super(values); }
        @Nullable public static AudioListenerCreatedEvent fromMap(@Nullable Map<String, Object> values) {
            return values == null ? null : new AudioListenerCreatedEvent(values);
        }
        public static Builder builder() { return new Builder(); }
        /**
         * Returns the listener field.
         * @return the protocol field value
         */
        @Nullable public WebAudio.AudioListener listener() {
            return WebAudio.AudioListener.fromMap(objectMap(value("listener")));
        }
        public static final class Builder {
            private final Map<String, Object> values = new LinkedHashMap<>();
            /**
             * Sets the listener field.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder listener(@Nullable WebAudio.AudioListener value) {
                if (value == null) values.remove("listener");
                else values.put("listener", jsonValue(value));
                return this;
            }
            public AudioListenerCreatedEvent build() {
                if (!values.containsKey("listener")) throw new IllegalStateException("Missing required CDP field: listener");
                return new AudioListenerCreatedEvent(values);
            }
        }
    }
    /**
     * Notifies that a new AudioListener has been created.
     */
    public static final class AudioListenerWillBeDestroyedEvent extends CdpObject {
        private AudioListenerWillBeDestroyedEvent(Map<String, Object> values) { super(values); }
        @Nullable public static AudioListenerWillBeDestroyedEvent fromMap(@Nullable Map<String, Object> values) {
            return values == null ? null : new AudioListenerWillBeDestroyedEvent(values);
        }
        public static Builder builder() { return new Builder(); }
        /**
         * Returns the contextId field.
         * @return the protocol field value
         */
        @Nullable public String contextId() {
            return (String) value("contextId");
        }
        /**
         * Returns the listenerId field.
         * @return the protocol field value
         */
        @Nullable public String listenerId() {
            return (String) value("listenerId");
        }
        public static final class Builder {
            private final Map<String, Object> values = new LinkedHashMap<>();
            /**
             * Sets the contextId field.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder contextId(@Nullable String value) {
                if (value == null) values.remove("contextId");
                else values.put("contextId", jsonValue(value));
                return this;
            }
            /**
             * Sets the listenerId field.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder listenerId(@Nullable String value) {
                if (value == null) values.remove("listenerId");
                else values.put("listenerId", jsonValue(value));
                return this;
            }
            public AudioListenerWillBeDestroyedEvent build() {
                if (!values.containsKey("contextId")) throw new IllegalStateException("Missing required CDP field: contextId");
                if (!values.containsKey("listenerId")) throw new IllegalStateException("Missing required CDP field: listenerId");
                return new AudioListenerWillBeDestroyedEvent(values);
            }
        }
    }
    /**
     * Notifies that a new AudioNode has been created.
     */
    public static final class AudioNodeCreatedEvent extends CdpObject {
        private AudioNodeCreatedEvent(Map<String, Object> values) { super(values); }
        @Nullable public static AudioNodeCreatedEvent fromMap(@Nullable Map<String, Object> values) {
            return values == null ? null : new AudioNodeCreatedEvent(values);
        }
        public static Builder builder() { return new Builder(); }
        /**
         * Returns the node field.
         * @return the protocol field value
         */
        @Nullable public WebAudio.AudioNode node() {
            return WebAudio.AudioNode.fromMap(objectMap(value("node")));
        }
        public static final class Builder {
            private final Map<String, Object> values = new LinkedHashMap<>();
            /**
             * Sets the node field.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder node(@Nullable WebAudio.AudioNode value) {
                if (value == null) values.remove("node");
                else values.put("node", jsonValue(value));
                return this;
            }
            public AudioNodeCreatedEvent build() {
                if (!values.containsKey("node")) throw new IllegalStateException("Missing required CDP field: node");
                return new AudioNodeCreatedEvent(values);
            }
        }
    }
    /**
     * Notifies that an existing AudioNode has been destroyed.
     */
    public static final class AudioNodeWillBeDestroyedEvent extends CdpObject {
        private AudioNodeWillBeDestroyedEvent(Map<String, Object> values) { super(values); }
        @Nullable public static AudioNodeWillBeDestroyedEvent fromMap(@Nullable Map<String, Object> values) {
            return values == null ? null : new AudioNodeWillBeDestroyedEvent(values);
        }
        public static Builder builder() { return new Builder(); }
        /**
         * Returns the contextId field.
         * @return the protocol field value
         */
        @Nullable public String contextId() {
            return (String) value("contextId");
        }
        /**
         * Returns the nodeId field.
         * @return the protocol field value
         */
        @Nullable public String nodeId() {
            return (String) value("nodeId");
        }
        public static final class Builder {
            private final Map<String, Object> values = new LinkedHashMap<>();
            /**
             * Sets the contextId field.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder contextId(@Nullable String value) {
                if (value == null) values.remove("contextId");
                else values.put("contextId", jsonValue(value));
                return this;
            }
            /**
             * Sets the nodeId field.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder nodeId(@Nullable String value) {
                if (value == null) values.remove("nodeId");
                else values.put("nodeId", jsonValue(value));
                return this;
            }
            public AudioNodeWillBeDestroyedEvent build() {
                if (!values.containsKey("contextId")) throw new IllegalStateException("Missing required CDP field: contextId");
                if (!values.containsKey("nodeId")) throw new IllegalStateException("Missing required CDP field: nodeId");
                return new AudioNodeWillBeDestroyedEvent(values);
            }
        }
    }
    /**
     * Notifies that a new AudioParam has been created.
     */
    public static final class AudioParamCreatedEvent extends CdpObject {
        private AudioParamCreatedEvent(Map<String, Object> values) { super(values); }
        @Nullable public static AudioParamCreatedEvent fromMap(@Nullable Map<String, Object> values) {
            return values == null ? null : new AudioParamCreatedEvent(values);
        }
        public static Builder builder() { return new Builder(); }
        /**
         * Returns the param field.
         * @return the protocol field value
         */
        @Nullable public WebAudio.AudioParam param() {
            return WebAudio.AudioParam.fromMap(objectMap(value("param")));
        }
        public static final class Builder {
            private final Map<String, Object> values = new LinkedHashMap<>();
            /**
             * Sets the param field.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder param(@Nullable WebAudio.AudioParam value) {
                if (value == null) values.remove("param");
                else values.put("param", jsonValue(value));
                return this;
            }
            public AudioParamCreatedEvent build() {
                if (!values.containsKey("param")) throw new IllegalStateException("Missing required CDP field: param");
                return new AudioParamCreatedEvent(values);
            }
        }
    }
    /**
     * Notifies that an existing AudioParam has been destroyed.
     */
    public static final class AudioParamWillBeDestroyedEvent extends CdpObject {
        private AudioParamWillBeDestroyedEvent(Map<String, Object> values) { super(values); }
        @Nullable public static AudioParamWillBeDestroyedEvent fromMap(@Nullable Map<String, Object> values) {
            return values == null ? null : new AudioParamWillBeDestroyedEvent(values);
        }
        public static Builder builder() { return new Builder(); }
        /**
         * Returns the contextId field.
         * @return the protocol field value
         */
        @Nullable public String contextId() {
            return (String) value("contextId");
        }
        /**
         * Returns the nodeId field.
         * @return the protocol field value
         */
        @Nullable public String nodeId() {
            return (String) value("nodeId");
        }
        /**
         * Returns the paramId field.
         * @return the protocol field value
         */
        @Nullable public String paramId() {
            return (String) value("paramId");
        }
        public static final class Builder {
            private final Map<String, Object> values = new LinkedHashMap<>();
            /**
             * Sets the contextId field.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder contextId(@Nullable String value) {
                if (value == null) values.remove("contextId");
                else values.put("contextId", jsonValue(value));
                return this;
            }
            /**
             * Sets the nodeId field.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder nodeId(@Nullable String value) {
                if (value == null) values.remove("nodeId");
                else values.put("nodeId", jsonValue(value));
                return this;
            }
            /**
             * Sets the paramId field.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder paramId(@Nullable String value) {
                if (value == null) values.remove("paramId");
                else values.put("paramId", jsonValue(value));
                return this;
            }
            public AudioParamWillBeDestroyedEvent build() {
                if (!values.containsKey("contextId")) throw new IllegalStateException("Missing required CDP field: contextId");
                if (!values.containsKey("nodeId")) throw new IllegalStateException("Missing required CDP field: nodeId");
                if (!values.containsKey("paramId")) throw new IllegalStateException("Missing required CDP field: paramId");
                return new AudioParamWillBeDestroyedEvent(values);
            }
        }
    }
    /**
     * Notifies that two AudioNodes are connected.
     */
    public static final class NodesConnectedEvent extends CdpObject {
        private NodesConnectedEvent(Map<String, Object> values) { super(values); }
        @Nullable public static NodesConnectedEvent fromMap(@Nullable Map<String, Object> values) {
            return values == null ? null : new NodesConnectedEvent(values);
        }
        public static Builder builder() { return new Builder(); }
        /**
         * Returns the contextId field.
         * @return the protocol field value
         */
        @Nullable public String contextId() {
            return (String) value("contextId");
        }
        /**
         * Returns the sourceId field.
         * @return the protocol field value
         */
        @Nullable public String sourceId() {
            return (String) value("sourceId");
        }
        /**
         * Returns the destinationId field.
         * @return the protocol field value
         */
        @Nullable public String destinationId() {
            return (String) value("destinationId");
        }
        /**
         * Returns the sourceOutputIndex field.
         * @return the protocol field value
         */
        @Nullable public Double sourceOutputIndex() {
            return numberAsDouble(value("sourceOutputIndex"));
        }
        /**
         * Returns the destinationInputIndex field.
         * @return the protocol field value
         */
        @Nullable public Double destinationInputIndex() {
            return numberAsDouble(value("destinationInputIndex"));
        }
        public static final class Builder {
            private final Map<String, Object> values = new LinkedHashMap<>();
            /**
             * Sets the contextId field.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder contextId(@Nullable String value) {
                if (value == null) values.remove("contextId");
                else values.put("contextId", jsonValue(value));
                return this;
            }
            /**
             * Sets the sourceId field.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder sourceId(@Nullable String value) {
                if (value == null) values.remove("sourceId");
                else values.put("sourceId", jsonValue(value));
                return this;
            }
            /**
             * Sets the destinationId field.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder destinationId(@Nullable String value) {
                if (value == null) values.remove("destinationId");
                else values.put("destinationId", jsonValue(value));
                return this;
            }
            /**
             * Sets the sourceOutputIndex field.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder sourceOutputIndex(@Nullable Double value) {
                if (value == null) values.remove("sourceOutputIndex");
                else values.put("sourceOutputIndex", jsonValue(value));
                return this;
            }
            /**
             * Sets the destinationInputIndex field.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder destinationInputIndex(@Nullable Double value) {
                if (value == null) values.remove("destinationInputIndex");
                else values.put("destinationInputIndex", jsonValue(value));
                return this;
            }
            public NodesConnectedEvent build() {
                if (!values.containsKey("contextId")) throw new IllegalStateException("Missing required CDP field: contextId");
                if (!values.containsKey("sourceId")) throw new IllegalStateException("Missing required CDP field: sourceId");
                if (!values.containsKey("destinationId")) throw new IllegalStateException("Missing required CDP field: destinationId");
                return new NodesConnectedEvent(values);
            }
        }
    }
    /**
     * Notifies that AudioNodes are disconnected. The destination can be null, and it means all the outgoing connections from the source are disconnected.
     */
    public static final class NodesDisconnectedEvent extends CdpObject {
        private NodesDisconnectedEvent(Map<String, Object> values) { super(values); }
        @Nullable public static NodesDisconnectedEvent fromMap(@Nullable Map<String, Object> values) {
            return values == null ? null : new NodesDisconnectedEvent(values);
        }
        public static Builder builder() { return new Builder(); }
        /**
         * Returns the contextId field.
         * @return the protocol field value
         */
        @Nullable public String contextId() {
            return (String) value("contextId");
        }
        /**
         * Returns the sourceId field.
         * @return the protocol field value
         */
        @Nullable public String sourceId() {
            return (String) value("sourceId");
        }
        /**
         * Returns the destinationId field.
         * @return the protocol field value
         */
        @Nullable public String destinationId() {
            return (String) value("destinationId");
        }
        /**
         * Returns the sourceOutputIndex field.
         * @return the protocol field value
         */
        @Nullable public Double sourceOutputIndex() {
            return numberAsDouble(value("sourceOutputIndex"));
        }
        /**
         * Returns the destinationInputIndex field.
         * @return the protocol field value
         */
        @Nullable public Double destinationInputIndex() {
            return numberAsDouble(value("destinationInputIndex"));
        }
        public static final class Builder {
            private final Map<String, Object> values = new LinkedHashMap<>();
            /**
             * Sets the contextId field.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder contextId(@Nullable String value) {
                if (value == null) values.remove("contextId");
                else values.put("contextId", jsonValue(value));
                return this;
            }
            /**
             * Sets the sourceId field.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder sourceId(@Nullable String value) {
                if (value == null) values.remove("sourceId");
                else values.put("sourceId", jsonValue(value));
                return this;
            }
            /**
             * Sets the destinationId field.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder destinationId(@Nullable String value) {
                if (value == null) values.remove("destinationId");
                else values.put("destinationId", jsonValue(value));
                return this;
            }
            /**
             * Sets the sourceOutputIndex field.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder sourceOutputIndex(@Nullable Double value) {
                if (value == null) values.remove("sourceOutputIndex");
                else values.put("sourceOutputIndex", jsonValue(value));
                return this;
            }
            /**
             * Sets the destinationInputIndex field.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder destinationInputIndex(@Nullable Double value) {
                if (value == null) values.remove("destinationInputIndex");
                else values.put("destinationInputIndex", jsonValue(value));
                return this;
            }
            public NodesDisconnectedEvent build() {
                if (!values.containsKey("contextId")) throw new IllegalStateException("Missing required CDP field: contextId");
                if (!values.containsKey("sourceId")) throw new IllegalStateException("Missing required CDP field: sourceId");
                if (!values.containsKey("destinationId")) throw new IllegalStateException("Missing required CDP field: destinationId");
                return new NodesDisconnectedEvent(values);
            }
        }
    }
    /**
     * Notifies that an AudioNode is connected to an AudioParam.
     */
    public static final class NodeParamConnectedEvent extends CdpObject {
        private NodeParamConnectedEvent(Map<String, Object> values) { super(values); }
        @Nullable public static NodeParamConnectedEvent fromMap(@Nullable Map<String, Object> values) {
            return values == null ? null : new NodeParamConnectedEvent(values);
        }
        public static Builder builder() { return new Builder(); }
        /**
         * Returns the contextId field.
         * @return the protocol field value
         */
        @Nullable public String contextId() {
            return (String) value("contextId");
        }
        /**
         * Returns the sourceId field.
         * @return the protocol field value
         */
        @Nullable public String sourceId() {
            return (String) value("sourceId");
        }
        /**
         * Returns the destinationId field.
         * @return the protocol field value
         */
        @Nullable public String destinationId() {
            return (String) value("destinationId");
        }
        /**
         * Returns the sourceOutputIndex field.
         * @return the protocol field value
         */
        @Nullable public Double sourceOutputIndex() {
            return numberAsDouble(value("sourceOutputIndex"));
        }
        public static final class Builder {
            private final Map<String, Object> values = new LinkedHashMap<>();
            /**
             * Sets the contextId field.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder contextId(@Nullable String value) {
                if (value == null) values.remove("contextId");
                else values.put("contextId", jsonValue(value));
                return this;
            }
            /**
             * Sets the sourceId field.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder sourceId(@Nullable String value) {
                if (value == null) values.remove("sourceId");
                else values.put("sourceId", jsonValue(value));
                return this;
            }
            /**
             * Sets the destinationId field.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder destinationId(@Nullable String value) {
                if (value == null) values.remove("destinationId");
                else values.put("destinationId", jsonValue(value));
                return this;
            }
            /**
             * Sets the sourceOutputIndex field.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder sourceOutputIndex(@Nullable Double value) {
                if (value == null) values.remove("sourceOutputIndex");
                else values.put("sourceOutputIndex", jsonValue(value));
                return this;
            }
            public NodeParamConnectedEvent build() {
                if (!values.containsKey("contextId")) throw new IllegalStateException("Missing required CDP field: contextId");
                if (!values.containsKey("sourceId")) throw new IllegalStateException("Missing required CDP field: sourceId");
                if (!values.containsKey("destinationId")) throw new IllegalStateException("Missing required CDP field: destinationId");
                return new NodeParamConnectedEvent(values);
            }
        }
    }
    /**
     * Notifies that an AudioNode is disconnected to an AudioParam.
     */
    public static final class NodeParamDisconnectedEvent extends CdpObject {
        private NodeParamDisconnectedEvent(Map<String, Object> values) { super(values); }
        @Nullable public static NodeParamDisconnectedEvent fromMap(@Nullable Map<String, Object> values) {
            return values == null ? null : new NodeParamDisconnectedEvent(values);
        }
        public static Builder builder() { return new Builder(); }
        /**
         * Returns the contextId field.
         * @return the protocol field value
         */
        @Nullable public String contextId() {
            return (String) value("contextId");
        }
        /**
         * Returns the sourceId field.
         * @return the protocol field value
         */
        @Nullable public String sourceId() {
            return (String) value("sourceId");
        }
        /**
         * Returns the destinationId field.
         * @return the protocol field value
         */
        @Nullable public String destinationId() {
            return (String) value("destinationId");
        }
        /**
         * Returns the sourceOutputIndex field.
         * @return the protocol field value
         */
        @Nullable public Double sourceOutputIndex() {
            return numberAsDouble(value("sourceOutputIndex"));
        }
        public static final class Builder {
            private final Map<String, Object> values = new LinkedHashMap<>();
            /**
             * Sets the contextId field.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder contextId(@Nullable String value) {
                if (value == null) values.remove("contextId");
                else values.put("contextId", jsonValue(value));
                return this;
            }
            /**
             * Sets the sourceId field.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder sourceId(@Nullable String value) {
                if (value == null) values.remove("sourceId");
                else values.put("sourceId", jsonValue(value));
                return this;
            }
            /**
             * Sets the destinationId field.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder destinationId(@Nullable String value) {
                if (value == null) values.remove("destinationId");
                else values.put("destinationId", jsonValue(value));
                return this;
            }
            /**
             * Sets the sourceOutputIndex field.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder sourceOutputIndex(@Nullable Double value) {
                if (value == null) values.remove("sourceOutputIndex");
                else values.put("sourceOutputIndex", jsonValue(value));
                return this;
            }
            public NodeParamDisconnectedEvent build() {
                if (!values.containsKey("contextId")) throw new IllegalStateException("Missing required CDP field: contextId");
                if (!values.containsKey("sourceId")) throw new IllegalStateException("Missing required CDP field: sourceId");
                if (!values.containsKey("destinationId")) throw new IllegalStateException("Missing required CDP field: destinationId");
                return new NodeParamDisconnectedEvent(values);
            }
        }
    }
    public static final class Client {
        private final CdpClient client;
        public Client(CdpClient client) { this.client = client; }
        /**
         * Enables the WebAudio domain and starts sending context lifetime events.
         * @return a stage completing with the command result
         */
        public CompletionStage<EnableResult> enable() {
            return client.call("WebAudio.enable", null, EnableResult::fromMap);
        }
        /**
         * Disables the WebAudio domain.
         * @return a stage completing with the command result
         */
        public CompletionStage<DisableResult> disable() {
            return client.call("WebAudio.disable", null, DisableResult::fromMap);
        }
        /**
         * Fetch the realtime data from the registered contexts.
         * @param params command parameters
         * @return a stage completing with the command result
         */
        public CompletionStage<GetRealtimeDataResult> getRealtimeData(GetRealtimeDataParams params) {
            return client.call("WebAudio.getRealtimeData", params, GetRealtimeDataResult::fromMap);
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
