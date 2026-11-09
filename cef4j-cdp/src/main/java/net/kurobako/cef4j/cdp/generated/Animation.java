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

/**
 * Chrome DevTools Protocol Animation domain.
 * <p><b>Experimental:</b> this part of CDP may change without notice.
 * @see <a href="https://chromium.googlesource.com/chromium/src/+/refs/tags/150.0.7871.213/third_party/blink/public/devtools_protocol/domains/Animation.pdl">Pinned protocol source</a>
 */
@Generated("./mvnw generate-sources -pl cef4j-cdp -Dcef.version=150.0.18+gdb11278+chromium-150.0.7871.213")
@SuppressWarnings({"EscapedEntity", "InvalidParam", "JavaLangClash", "MissingSummary", "Unchecked", "UnusedMethod"})
@NullableBoundary("CDP wire objects use null for absent optional fields")
public final class Animation {
    private Animation() {}
    private static final CdpField<Animation.AnimationValue> ANIMATION = CdpField.of("animation", value -> java.util.Objects.requireNonNull(Animation.AnimationValue.fromMap(CdpObject.requireObjectMap(value))));
    private static final CdpField<java.util.List<String>> ANIMATIONS = CdpField.of("animations", value -> CdpObject.requireList(value, element0 -> (String) element0));
    private static final CdpField<DOM.ScrollOrientation> AXIS = CdpField.of("axis", value -> DOM.ScrollOrientation.of((String) value));
    private static final CdpField<DOM.BackendNodeId> BACKEND_NODE_ID = CdpField.of("backendNodeId", value -> new DOM.BackendNodeId(((Number) value).longValue()));
    private static final CdpField<Double> CURRENT_TIME = CdpField.of("currentTime", value -> ((Number) value).doubleValue());
    private static final CdpField<java.util.List<Animation.KeyframeStyle>> KEYFRAMES = CdpField.of("keyframes", value -> CdpObject.requireList(value, element0 -> java.util.Objects.requireNonNull(Animation.KeyframeStyle.fromMap(CdpObject.requireObjectMap(element0)))));
    private static final CdpField<Animation.KeyframesRule> KEYFRAMES_RULE = CdpField.of("keyframesRule", value -> java.util.Objects.requireNonNull(Animation.KeyframesRule.fromMap(CdpObject.requireObjectMap(value))));
    private static final CdpField<Double> PLAYBACK_RATE = CdpField.of("playbackRate", value -> ((Number) value).doubleValue());
    private static final CdpField<Runtime.RemoteObject> REMOTE_OBJECT = CdpField.of("remoteObject", value -> java.util.Objects.requireNonNull(Runtime.RemoteObject.fromMap(CdpObject.requireObjectMap(value))));
    private static final CdpField<Animation.AnimationEffect> SOURCE = CdpField.of("source", value -> java.util.Objects.requireNonNull(Animation.AnimationEffect.fromMap(CdpObject.requireObjectMap(value))));
    private static final CdpField<DOM.BackendNodeId> SOURCE_NODE_ID = CdpField.of("sourceNodeId", value -> new DOM.BackendNodeId(((Number) value).longValue()));
    private static final CdpField<DOM.BackendNodeId> SUBJECT_NODE_ID = CdpField.of("subjectNodeId", value -> new DOM.BackendNodeId(((Number) value).longValue()));
    private static final CdpField<AnimationValue.TypeValues> TYPE = CdpField.of("type", value -> AnimationValue.TypeValues.of((String) value));
    private static final CdpField<Animation.ViewOrScrollTimeline> VIEW_OR_SCROLL_TIMELINE = CdpField.of("viewOrScrollTimeline", value -> java.util.Objects.requireNonNull(Animation.ViewOrScrollTimeline.fromMap(CdpObject.requireObjectMap(value))));
    /**
     * Animation instance.
     */
    public static final class AnimationValue extends CdpObject {
        public AnimationValue() {}
        private AnimationValue(Map<String, Object> values) { super(values); }
        public static AnimationValue fromMap(Map<String, Object> values) {
            return new AnimationValue(values);
        }
        /**
         * Animation type of {@code Animation}.
         */
        public enum TypeValues implements CdpValue<String> {
            CSSTRANSITION("CSSTransition"),
            CSSANIMATION("CSSAnimation"),
            WEBANIMATION("WebAnimation");
            public final String value;
            TypeValues(String value) { this.value = value; }
            @Override public String value() { return value; }
            public static TypeValues of(String value) {
                for (TypeValues constant : values()) {
                    if (constant.value.equals(value)) return constant;
                }
                throw new IllegalArgumentException("Unknown TypeValues value: " + value);
            }
        }
        /**
         * {@code Animation}&#x27;s id.
         * @return the protocol field value
         */
        public String id() {
            return requireString("id");
        }
        /**
         * {@code Animation}&#x27;s name.
         * @return the protocol field value
         */
        public String name() {
            return requireString("name");
        }
        /**
         * {@code Animation}&#x27;s internal paused state.
         * @return the protocol field value
         */
        public boolean pausedState() {
            return requireBoolean("pausedState");
        }
        /**
         * {@code Animation}&#x27;s play state.
         * @return the protocol field value
         */
        public String playState() {
            return requireString("playState");
        }
        /**
         * {@code Animation}&#x27;s playback rate.
         * @return the protocol field value
         */
        public double playbackRate() {
            return requireDouble("playbackRate");
        }
        /**
         * {@code Animation}&#x27;s start time. Milliseconds for time based animations and percentage [0 - 100] for scroll driven animations (i.e. when viewOrScrollTimeline exists).
         * @return the protocol field value
         */
        public double startTime() {
            return requireDouble("startTime");
        }
        /**
         * {@code Animation}&#x27;s current time.
         * @return the protocol field value
         */
        public double currentTime() {
            return requireDouble("currentTime");
        }
        /**
         * Animation type of {@code Animation}.
         * @return the protocol field value
         */
        public AnimationValue.TypeValues type() {
            return require(TYPE);
        }
        /**
         * {@code Animation}&#x27;s source animation node.
         * @return the protocol field value, empty when absent
         */
        public Optional<Animation.AnimationEffect> source() {
            return optional(SOURCE);
        }
        /**
         * A unique ID for {@code Animation} representing the sources that triggered this CSS animation/transition.
         * @return the protocol field value, empty when absent
         */
        public Optional<String> cssId() {
            return optionalString("cssId");
        }
        /**
         * View or scroll timeline
         * @return the protocol field value, empty when absent
         */
        public Optional<Animation.ViewOrScrollTimeline> viewOrScrollTimeline() {
            return optional(VIEW_OR_SCROLL_TIMELINE);
        }
        /**
         * {@code Animation}&#x27;s id.
         * @param id field value
         * @return this model
         */
        public AnimationValue id(String id) {
            set("id", id);
            return this;
        }
        /**
         * {@code Animation}&#x27;s name.
         * @param name field value
         * @return this model
         */
        public AnimationValue name(String name) {
            set("name", name);
            return this;
        }
        /**
         * {@code Animation}&#x27;s internal paused state.
         * @param pausedState field value
         * @return this model
         */
        public AnimationValue pausedState(boolean pausedState) {
            set("pausedState", pausedState);
            return this;
        }
        /**
         * {@code Animation}&#x27;s play state.
         * @param playState field value
         * @return this model
         */
        public AnimationValue playState(String playState) {
            set("playState", playState);
            return this;
        }
        /**
         * {@code Animation}&#x27;s playback rate.
         * @param playbackRate field value
         * @return this model
         */
        public AnimationValue playbackRate(double playbackRate) {
            set("playbackRate", playbackRate);
            return this;
        }
        /**
         * {@code Animation}&#x27;s start time. Milliseconds for time based animations and percentage [0 - 100] for scroll driven animations (i.e. when viewOrScrollTimeline exists).
         * @param startTime field value
         * @return this model
         */
        public AnimationValue startTime(double startTime) {
            set("startTime", startTime);
            return this;
        }
        /**
         * {@code Animation}&#x27;s current time.
         * @param currentTime field value
         * @return this model
         */
        public AnimationValue currentTime(double currentTime) {
            set("currentTime", currentTime);
            return this;
        }
        /**
         * Animation type of {@code Animation}.
         * @param type field value
         * @return this model
         */
        public AnimationValue type(AnimationValue.TypeValues type) {
            set("type", type);
            return this;
        }
        /**
         * {@code Animation}&#x27;s source animation node.
         * @param source field value
         * @return this model
         */
        public AnimationValue source(Animation.AnimationEffect source) {
            set("source", source);
            return this;
        }
        /**
         * A unique ID for {@code Animation} representing the sources that triggered this CSS animation/transition.
         * @param cssId field value
         * @return this model
         */
        public AnimationValue cssId(String cssId) {
            set("cssId", cssId);
            return this;
        }
        /**
         * View or scroll timeline
         * @param viewOrScrollTimeline field value
         * @return this model
         */
        public AnimationValue viewOrScrollTimeline(Animation.ViewOrScrollTimeline viewOrScrollTimeline) {
            set("viewOrScrollTimeline", viewOrScrollTimeline);
            return this;
        }
    }
    /**
     * Timeline instance
     */
    public static final class ViewOrScrollTimeline extends CdpObject {
        public ViewOrScrollTimeline() {}
        private ViewOrScrollTimeline(Map<String, Object> values) { super(values); }
        public static ViewOrScrollTimeline fromMap(Map<String, Object> values) {
            return new ViewOrScrollTimeline(values);
        }
        /**
         * Scroll container node
         * @return the protocol field value, empty when absent
         */
        public Optional<DOM.BackendNodeId> sourceNodeId() {
            return optional(SOURCE_NODE_ID);
        }
        /**
         * Represents the starting scroll position of the timeline as a length offset in pixels from scroll origin.
         * @return the protocol field value, empty when absent
         */
        public OptionalDouble startOffset() {
            return optionalDouble("startOffset");
        }
        /**
         * Represents the ending scroll position of the timeline as a length offset in pixels from scroll origin.
         * @return the protocol field value, empty when absent
         */
        public OptionalDouble endOffset() {
            return optionalDouble("endOffset");
        }
        /**
         * The element whose principal box&#x27;s visibility in the scrollport defined the progress of the timeline. Does not exist for animations with ScrollTimeline
         * @return the protocol field value, empty when absent
         */
        public Optional<DOM.BackendNodeId> subjectNodeId() {
            return optional(SUBJECT_NODE_ID);
        }
        /**
         * Orientation of the scroll
         * @return the protocol field value
         */
        public DOM.ScrollOrientation axis() {
            return require(AXIS);
        }
        /**
         * Scroll container node
         * @param sourceNodeId field value
         * @return this model
         */
        public ViewOrScrollTimeline sourceNodeId(DOM.BackendNodeId sourceNodeId) {
            set("sourceNodeId", sourceNodeId);
            return this;
        }
        /**
         * Represents the starting scroll position of the timeline as a length offset in pixels from scroll origin.
         * @param startOffset field value
         * @return this model
         */
        public ViewOrScrollTimeline startOffset(double startOffset) {
            set("startOffset", startOffset);
            return this;
        }
        /**
         * Represents the ending scroll position of the timeline as a length offset in pixels from scroll origin.
         * @param endOffset field value
         * @return this model
         */
        public ViewOrScrollTimeline endOffset(double endOffset) {
            set("endOffset", endOffset);
            return this;
        }
        /**
         * The element whose principal box&#x27;s visibility in the scrollport defined the progress of the timeline. Does not exist for animations with ScrollTimeline
         * @param subjectNodeId field value
         * @return this model
         */
        public ViewOrScrollTimeline subjectNodeId(DOM.BackendNodeId subjectNodeId) {
            set("subjectNodeId", subjectNodeId);
            return this;
        }
        /**
         * Orientation of the scroll
         * @param axis field value
         * @return this model
         */
        public ViewOrScrollTimeline axis(DOM.ScrollOrientation axis) {
            set("axis", axis);
            return this;
        }
    }
    /**
     * AnimationEffect instance
     */
    public static final class AnimationEffect extends CdpObject {
        public AnimationEffect() {}
        private AnimationEffect(Map<String, Object> values) { super(values); }
        public static AnimationEffect fromMap(Map<String, Object> values) {
            return new AnimationEffect(values);
        }
        /**
         * {@code AnimationEffect}&#x27;s delay.
         * @return the protocol field value
         */
        public double delay() {
            return requireDouble("delay");
        }
        /**
         * {@code AnimationEffect}&#x27;s end delay.
         * @return the protocol field value
         */
        public double endDelay() {
            return requireDouble("endDelay");
        }
        /**
         * {@code AnimationEffect}&#x27;s iteration start.
         * @return the protocol field value
         */
        public double iterationStart() {
            return requireDouble("iterationStart");
        }
        /**
         * {@code AnimationEffect}&#x27;s iterations. Omitted if the value is infinite.
         * @return the protocol field value, empty when absent
         */
        public OptionalDouble iterations() {
            return optionalDouble("iterations");
        }
        /**
         * {@code AnimationEffect}&#x27;s iteration duration. Milliseconds for time based animations and percentage [0 - 100] for scroll driven animations (i.e. when viewOrScrollTimeline exists).
         * @return the protocol field value
         */
        public double duration() {
            return requireDouble("duration");
        }
        /**
         * {@code AnimationEffect}&#x27;s playback direction.
         * @return the protocol field value
         */
        public String direction() {
            return requireString("direction");
        }
        /**
         * {@code AnimationEffect}&#x27;s fill mode.
         * @return the protocol field value
         */
        public String fill() {
            return requireString("fill");
        }
        /**
         * {@code AnimationEffect}&#x27;s target node.
         * @return the protocol field value, empty when absent
         */
        public Optional<DOM.BackendNodeId> backendNodeId() {
            return optional(BACKEND_NODE_ID);
        }
        /**
         * {@code AnimationEffect}&#x27;s keyframes.
         * @return the protocol field value, empty when absent
         */
        public Optional<Animation.KeyframesRule> keyframesRule() {
            return optional(KEYFRAMES_RULE);
        }
        /**
         * {@code AnimationEffect}&#x27;s timing function.
         * @return the protocol field value
         */
        public String easing() {
            return requireString("easing");
        }
        /**
         * {@code AnimationEffect}&#x27;s delay.
         * @param delay field value
         * @return this model
         */
        public AnimationEffect delay(double delay) {
            set("delay", delay);
            return this;
        }
        /**
         * {@code AnimationEffect}&#x27;s end delay.
         * @param endDelay field value
         * @return this model
         */
        public AnimationEffect endDelay(double endDelay) {
            set("endDelay", endDelay);
            return this;
        }
        /**
         * {@code AnimationEffect}&#x27;s iteration start.
         * @param iterationStart field value
         * @return this model
         */
        public AnimationEffect iterationStart(double iterationStart) {
            set("iterationStart", iterationStart);
            return this;
        }
        /**
         * {@code AnimationEffect}&#x27;s iterations. Omitted if the value is infinite.
         * @param iterations field value
         * @return this model
         */
        public AnimationEffect iterations(double iterations) {
            set("iterations", iterations);
            return this;
        }
        /**
         * {@code AnimationEffect}&#x27;s iteration duration. Milliseconds for time based animations and percentage [0 - 100] for scroll driven animations (i.e. when viewOrScrollTimeline exists).
         * @param duration field value
         * @return this model
         */
        public AnimationEffect duration(double duration) {
            set("duration", duration);
            return this;
        }
        /**
         * {@code AnimationEffect}&#x27;s playback direction.
         * @param direction field value
         * @return this model
         */
        public AnimationEffect direction(String direction) {
            set("direction", direction);
            return this;
        }
        /**
         * {@code AnimationEffect}&#x27;s fill mode.
         * @param fill field value
         * @return this model
         */
        public AnimationEffect fill(String fill) {
            set("fill", fill);
            return this;
        }
        /**
         * {@code AnimationEffect}&#x27;s target node.
         * @param backendNodeId field value
         * @return this model
         */
        public AnimationEffect backendNodeId(DOM.BackendNodeId backendNodeId) {
            set("backendNodeId", backendNodeId);
            return this;
        }
        /**
         * {@code AnimationEffect}&#x27;s keyframes.
         * @param keyframesRule field value
         * @return this model
         */
        public AnimationEffect keyframesRule(Animation.KeyframesRule keyframesRule) {
            set("keyframesRule", keyframesRule);
            return this;
        }
        /**
         * {@code AnimationEffect}&#x27;s timing function.
         * @param easing field value
         * @return this model
         */
        public AnimationEffect easing(String easing) {
            set("easing", easing);
            return this;
        }
    }
    /**
     * Keyframes Rule
     */
    public static final class KeyframesRule extends CdpObject {
        public KeyframesRule() {}
        private KeyframesRule(Map<String, Object> values) { super(values); }
        public static KeyframesRule fromMap(Map<String, Object> values) {
            return new KeyframesRule(values);
        }
        /**
         * CSS keyframed animation&#x27;s name.
         * @return the protocol field value, empty when absent
         */
        public Optional<String> name() {
            return optionalString("name");
        }
        /**
         * List of animation keyframes.
         * @return the protocol field value
         */
        public java.util.List<Animation.KeyframeStyle> keyframes() {
            return require(KEYFRAMES);
        }
        /**
         * CSS keyframed animation&#x27;s name.
         * @param name field value
         * @return this model
         */
        public KeyframesRule name(String name) {
            set("name", name);
            return this;
        }
        /**
         * List of animation keyframes.
         * @param keyframes field value
         * @return this model
         */
        public KeyframesRule keyframes(java.util.List<Animation.KeyframeStyle> keyframes) {
            set("keyframes", keyframes);
            return this;
        }
    }
    /**
     * Keyframe Style
     */
    public static final class KeyframeStyle extends CdpObject {
        public KeyframeStyle() {}
        private KeyframeStyle(Map<String, Object> values) { super(values); }
        public static KeyframeStyle fromMap(Map<String, Object> values) {
            return new KeyframeStyle(values);
        }
        /**
         * Keyframe&#x27;s time offset.
         * @return the protocol field value
         */
        public String offset() {
            return requireString("offset");
        }
        /**
         * {@code AnimationEffect}&#x27;s timing function.
         * @return the protocol field value
         */
        public String easing() {
            return requireString("easing");
        }
        /**
         * Keyframe&#x27;s time offset.
         * @param offset field value
         * @return this model
         */
        public KeyframeStyle offset(String offset) {
            set("offset", offset);
            return this;
        }
        /**
         * {@code AnimationEffect}&#x27;s timing function.
         * @param easing field value
         * @return this model
         */
        public KeyframeStyle easing(String easing) {
            set("easing", easing);
            return this;
        }
    }
    /**
     * Returns the current time of the an animation.
     */
    public static final class GetCurrentTimeRequest extends CdpObject {
        public GetCurrentTimeRequest() {}
        public static GetCurrentTimeRequest fromMap(Map<String, Object> values) {
            final GetCurrentTimeRequest instance_ = new GetCurrentTimeRequest();
            if (values != null) instance_.values.putAll(values);
            return instance_;
        }
        /**
         * Id of animation.
         * @return the protocol field value
         */
        public String id() {
            return requireString("id");
        }
        /**
         * Id of animation.
         * @param id field value
         * @return this model
         */
        public GetCurrentTimeRequest id(String id) {
            set("id", id);
            return this;
        }
    }
    /**
     * Releases a set of animations to no longer be manipulated.
     */
    public static final class ReleaseAnimationsRequest extends CdpObject {
        public ReleaseAnimationsRequest() {}
        public static ReleaseAnimationsRequest fromMap(Map<String, Object> values) {
            final ReleaseAnimationsRequest instance_ = new ReleaseAnimationsRequest();
            if (values != null) instance_.values.putAll(values);
            return instance_;
        }
        /**
         * List of animation ids to seek.
         * @return the protocol field value
         */
        public java.util.List<String> animations() {
            return require(ANIMATIONS);
        }
        /**
         * List of animation ids to seek.
         * @param animations field value
         * @return this model
         */
        public ReleaseAnimationsRequest animations(java.util.List<String> animations) {
            set("animations", animations);
            return this;
        }
    }
    /**
     * Gets the remote object of the Animation.
     */
    public static final class ResolveAnimationRequest extends CdpObject {
        public ResolveAnimationRequest() {}
        public static ResolveAnimationRequest fromMap(Map<String, Object> values) {
            final ResolveAnimationRequest instance_ = new ResolveAnimationRequest();
            if (values != null) instance_.values.putAll(values);
            return instance_;
        }
        /**
         * Animation id.
         * @return the protocol field value
         */
        public String animationId() {
            return requireString("animationId");
        }
        /**
         * Animation id.
         * @param animationId field value
         * @return this model
         */
        public ResolveAnimationRequest animationId(String animationId) {
            set("animationId", animationId);
            return this;
        }
    }
    /**
     * Seek a set of animations to a particular time within each animation.
     */
    public static final class SeekAnimationsRequest extends CdpObject {
        public SeekAnimationsRequest() {}
        public static SeekAnimationsRequest fromMap(Map<String, Object> values) {
            final SeekAnimationsRequest instance_ = new SeekAnimationsRequest();
            if (values != null) instance_.values.putAll(values);
            return instance_;
        }
        /**
         * List of animation ids to seek.
         * @return the protocol field value
         */
        public java.util.List<String> animations() {
            return require(ANIMATIONS);
        }
        /**
         * Set the current time of each animation.
         * @return the protocol field value
         */
        public double currentTime() {
            return requireDouble("currentTime");
        }
        /**
         * List of animation ids to seek.
         * @param animations field value
         * @return this model
         */
        public SeekAnimationsRequest animations(java.util.List<String> animations) {
            set("animations", animations);
            return this;
        }
        /**
         * Set the current time of each animation.
         * @param currentTime field value
         * @return this model
         */
        public SeekAnimationsRequest currentTime(double currentTime) {
            set("currentTime", currentTime);
            return this;
        }
    }
    /**
     * Sets the paused state of a set of animations.
     */
    public static final class SetPausedRequest extends CdpObject {
        public SetPausedRequest() {}
        public static SetPausedRequest fromMap(Map<String, Object> values) {
            final SetPausedRequest instance_ = new SetPausedRequest();
            if (values != null) instance_.values.putAll(values);
            return instance_;
        }
        /**
         * Animations to set the pause state of.
         * @return the protocol field value
         */
        public java.util.List<String> animations() {
            return require(ANIMATIONS);
        }
        /**
         * Paused state to set to.
         * @return the protocol field value
         */
        public boolean paused() {
            return requireBoolean("paused");
        }
        /**
         * Animations to set the pause state of.
         * @param animations field value
         * @return this model
         */
        public SetPausedRequest animations(java.util.List<String> animations) {
            set("animations", animations);
            return this;
        }
        /**
         * Paused state to set to.
         * @param paused field value
         * @return this model
         */
        public SetPausedRequest paused(boolean paused) {
            set("paused", paused);
            return this;
        }
    }
    /**
     * Sets the playback rate of the document timeline.
     */
    public static final class SetPlaybackRateRequest extends CdpObject {
        public SetPlaybackRateRequest() {}
        public static SetPlaybackRateRequest fromMap(Map<String, Object> values) {
            final SetPlaybackRateRequest instance_ = new SetPlaybackRateRequest();
            if (values != null) instance_.values.putAll(values);
            return instance_;
        }
        /**
         * Playback rate for animations on page
         * @return the protocol field value
         */
        public double playbackRate() {
            return requireDouble("playbackRate");
        }
        /**
         * Playback rate for animations on page
         * @param playbackRate field value
         * @return this model
         */
        public SetPlaybackRateRequest playbackRate(double playbackRate) {
            set("playbackRate", playbackRate);
            return this;
        }
    }
    /**
     * Sets the timing of an animation node.
     */
    public static final class SetTimingRequest extends CdpObject {
        public SetTimingRequest() {}
        public static SetTimingRequest fromMap(Map<String, Object> values) {
            final SetTimingRequest instance_ = new SetTimingRequest();
            if (values != null) instance_.values.putAll(values);
            return instance_;
        }
        /**
         * Animation id.
         * @return the protocol field value
         */
        public String animationId() {
            return requireString("animationId");
        }
        /**
         * Duration of the animation.
         * @return the protocol field value
         */
        public double duration() {
            return requireDouble("duration");
        }
        /**
         * Delay of the animation.
         * @return the protocol field value
         */
        public double delay() {
            return requireDouble("delay");
        }
        /**
         * Animation id.
         * @param animationId field value
         * @return this model
         */
        public SetTimingRequest animationId(String animationId) {
            set("animationId", animationId);
            return this;
        }
        /**
         * Duration of the animation.
         * @param duration field value
         * @return this model
         */
        public SetTimingRequest duration(double duration) {
            set("duration", duration);
            return this;
        }
        /**
         * Delay of the animation.
         * @param delay field value
         * @return this model
         */
        public SetTimingRequest delay(double delay) {
            set("delay", delay);
            return this;
        }
    }
    /**
     * Event for when an animation has been cancelled.
     */
    public static final class AnimationCanceledEvent extends CdpObject {
        public AnimationCanceledEvent() {}
        private AnimationCanceledEvent(Map<String, Object> values) { super(values); }
        public static AnimationCanceledEvent fromMap(Map<String, Object> values) {
            return new AnimationCanceledEvent(values);
        }
        /**
         * Id of the animation that was cancelled.
         * @return the protocol field value
         */
        public String id() {
            return requireString("id");
        }
        /**
         * Id of the animation that was cancelled.
         * @param id field value
         * @return this model
         */
        public AnimationCanceledEvent id(String id) {
            set("id", id);
            return this;
        }
    }
    /**
     * Event for each animation that has been created.
     */
    public static final class AnimationCreatedEvent extends CdpObject {
        public AnimationCreatedEvent() {}
        private AnimationCreatedEvent(Map<String, Object> values) { super(values); }
        public static AnimationCreatedEvent fromMap(Map<String, Object> values) {
            return new AnimationCreatedEvent(values);
        }
        /**
         * Id of the animation that was created.
         * @return the protocol field value
         */
        public String id() {
            return requireString("id");
        }
        /**
         * Id of the animation that was created.
         * @param id field value
         * @return this model
         */
        public AnimationCreatedEvent id(String id) {
            set("id", id);
            return this;
        }
    }
    /**
     * Event for animation that has been started.
     */
    public static final class AnimationStartedEvent extends CdpObject {
        public AnimationStartedEvent() {}
        private AnimationStartedEvent(Map<String, Object> values) { super(values); }
        public static AnimationStartedEvent fromMap(Map<String, Object> values) {
            return new AnimationStartedEvent(values);
        }
        /**
         * Animation that was started.
         * @return the protocol field value
         */
        public Animation.AnimationValue animation() {
            return require(ANIMATION);
        }
        /**
         * Animation that was started.
         * @param animation field value
         * @return this model
         */
        public AnimationStartedEvent animation(Animation.AnimationValue animation) {
            set("animation", animation);
            return this;
        }
    }
    /**
     * Event for animation that has been updated.
     */
    public static final class AnimationUpdatedEvent extends CdpObject {
        public AnimationUpdatedEvent() {}
        private AnimationUpdatedEvent(Map<String, Object> values) { super(values); }
        public static AnimationUpdatedEvent fromMap(Map<String, Object> values) {
            return new AnimationUpdatedEvent(values);
        }
        /**
         * Animation that was updated.
         * @return the protocol field value
         */
        public Animation.AnimationValue animation() {
            return require(ANIMATION);
        }
        /**
         * Animation that was updated.
         * @param animation field value
         * @return this model
         */
        public AnimationUpdatedEvent animation(Animation.AnimationValue animation) {
            set("animation", animation);
            return this;
        }
    }
    public static final class Client extends CdpDomainClient {
        public Client(CdpClient client) { super(client); }
        /**
         * Disables animation domain notifications.
         * @return a stage completing when the command completes
         */
        public CompletionStage<Void> disable() {
            return call("Animation.disable");
        }
        /**
         * Enables animation domain notifications.
         * @return a stage completing when the command completes
         */
        public CompletionStage<Void> enable() {
            return call("Animation.enable");
        }
        /**
         * Returns the current time of the an animation.
         * @param id protocol value
         * @return a stage completing with the command result
         */
        public CompletionStage<Double> getCurrentTime(String id) {
            return call("Animation.getCurrentTime", params("id", id), CURRENT_TIME::require);
        }
        /**
         * Returns the current time of the an animation.
         * @param request request parameters
         * @return a stage completing with the command result
         */
        public CompletionStage<Double> getCurrentTime(GetCurrentTimeRequest request) {
            return call("Animation.getCurrentTime", request, CURRENT_TIME::require);
        }
        /**
         * Gets the playback rate of the document timeline.
         * @return a stage completing with the command result
         */
        public CompletionStage<Double> getPlaybackRate() {
            return call("Animation.getPlaybackRate", PLAYBACK_RATE::require);
        }
        /**
         * Releases a set of animations to no longer be manipulated.
         * @param animations protocol value
         * @return a stage completing when the command completes
         */
        public CompletionStage<Void> releaseAnimations(java.util.List<String> animations) {
            return call("Animation.releaseAnimations", params("animations", animations));
        }
        /**
         * Releases a set of animations to no longer be manipulated.
         * @param request request parameters
         * @return a stage completing when the command completes
         */
        public CompletionStage<Void> releaseAnimations(ReleaseAnimationsRequest request) {
            return call("Animation.releaseAnimations", request);
        }
        /**
         * Gets the remote object of the Animation.
         * @param animationId protocol value
         * @return a stage completing with the command result
         */
        public CompletionStage<Runtime.RemoteObject> resolveAnimation(String animationId) {
            return call("Animation.resolveAnimation", params("animationId", animationId), REMOTE_OBJECT::require);
        }
        /**
         * Gets the remote object of the Animation.
         * @param request request parameters
         * @return a stage completing with the command result
         */
        public CompletionStage<Runtime.RemoteObject> resolveAnimation(ResolveAnimationRequest request) {
            return call("Animation.resolveAnimation", request, REMOTE_OBJECT::require);
        }
        /**
         * Seek a set of animations to a particular time within each animation.
         * @param animations protocol value
         * @param currentTime protocol value
         * @return a stage completing when the command completes
         */
        public CompletionStage<Void> seekAnimations(java.util.List<String> animations, double currentTime) {
            return call("Animation.seekAnimations", params("animations", animations, "currentTime", currentTime));
        }
        /**
         * Seek a set of animations to a particular time within each animation.
         * @param request request parameters
         * @return a stage completing when the command completes
         */
        public CompletionStage<Void> seekAnimations(SeekAnimationsRequest request) {
            return call("Animation.seekAnimations", request);
        }
        /**
         * Sets the paused state of a set of animations.
         * @param animations protocol value
         * @param paused protocol value
         * @return a stage completing when the command completes
         */
        public CompletionStage<Void> setPaused(java.util.List<String> animations, boolean paused) {
            return call("Animation.setPaused", params("animations", animations, "paused", paused));
        }
        /**
         * Sets the paused state of a set of animations.
         * @param request request parameters
         * @return a stage completing when the command completes
         */
        public CompletionStage<Void> setPaused(SetPausedRequest request) {
            return call("Animation.setPaused", request);
        }
        /**
         * Sets the playback rate of the document timeline.
         * @param playbackRate protocol value
         * @return a stage completing when the command completes
         */
        public CompletionStage<Void> setPlaybackRate(double playbackRate) {
            return call("Animation.setPlaybackRate", params("playbackRate", playbackRate));
        }
        /**
         * Sets the playback rate of the document timeline.
         * @param request request parameters
         * @return a stage completing when the command completes
         */
        public CompletionStage<Void> setPlaybackRate(SetPlaybackRateRequest request) {
            return call("Animation.setPlaybackRate", request);
        }
        /**
         * Sets the timing of an animation node.
         * @param animationId protocol value
         * @param duration protocol value
         * @param delay protocol value
         * @return a stage completing when the command completes
         */
        public CompletionStage<Void> setTiming(String animationId, double duration, double delay) {
            return call("Animation.setTiming", params("animationId", animationId, "duration", duration, "delay", delay));
        }
        /**
         * Sets the timing of an animation node.
         * @param request request parameters
         * @return a stage completing when the command completes
         */
        public CompletionStage<Void> setTiming(SetTimingRequest request) {
            return call("Animation.setTiming", request);
        }
        /**
         * Event for when an animation has been cancelled.
         * @param handler event callback
         * @return a removable subscription
         */
        public CdpSubscription onAnimationCanceled(Consumer<AnimationCanceledEvent> handler) {
            return on("Animation.animationCanceled", AnimationCanceledEvent::fromMap, handler);
        }
        /**
         * Event for each animation that has been created.
         * @param handler event callback
         * @return a removable subscription
         */
        public CdpSubscription onAnimationCreated(Consumer<AnimationCreatedEvent> handler) {
            return on("Animation.animationCreated", AnimationCreatedEvent::fromMap, handler);
        }
        /**
         * Event for animation that has been started.
         * @param handler event callback
         * @return a removable subscription
         */
        public CdpSubscription onAnimationStarted(Consumer<AnimationStartedEvent> handler) {
            return on("Animation.animationStarted", AnimationStartedEvent::fromMap, handler);
        }
        /**
         * Event for animation that has been updated.
         * @param handler event callback
         * @return a removable subscription
         */
        public CdpSubscription onAnimationUpdated(Consumer<AnimationUpdatedEvent> handler) {
            return on("Animation.animationUpdated", AnimationUpdatedEvent::fromMap, handler);
        }
    }
}
