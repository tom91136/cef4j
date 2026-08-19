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
 * Chrome DevTools Protocol Animation domain.
 * <p><b>Experimental:</b> this part of CDP may change without notice.
 * @see <a href="https://chromium.googlesource.com/chromium/src/+/refs/tags/150.0.7871.213/third_party/blink/public/devtools_protocol/domains/Animation.pdl">Pinned protocol source</a>
 */
@Generated("mvn generate-sources -pl cef4j-platform -Dcef.version=150.0.18+gdb11278+chromium-150.0.7871.213")
@SuppressWarnings({"EscapedEntity", "InvalidParam", "JavaLangClash", "MissingSummary", "Unchecked", "UnusedMethod"})
public final class Animation {
    private Animation() {}
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
            @Nonnull public String value() { return value; }
            public static TypeValues of(@Nonnull String value) {
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
            return (String) require("id");
        }
        /**
         * {@code Animation}&#x27;s name.
         * @return the protocol field value
         */
        public String name() {
            return (String) require("name");
        }
        /**
         * {@code Animation}&#x27;s internal paused state.
         * @return the protocol field value
         */
        public boolean pausedState() {
            return (Boolean) require("pausedState");
        }
        /**
         * {@code Animation}&#x27;s play state.
         * @return the protocol field value
         */
        public String playState() {
            return (String) require("playState");
        }
        /**
         * {@code Animation}&#x27;s playback rate.
         * @return the protocol field value
         */
        public double playbackRate() {
            return ((Number) require("playbackRate")).doubleValue();
        }
        /**
         * {@code Animation}&#x27;s start time. Milliseconds for time based animations and percentage [0 - 100] for scroll driven animations (i.e. when viewOrScrollTimeline exists).
         * @return the protocol field value
         */
        public double startTime() {
            return ((Number) require("startTime")).doubleValue();
        }
        /**
         * {@code Animation}&#x27;s current time.
         * @return the protocol field value
         */
        public double currentTime() {
            return ((Number) require("currentTime")).doubleValue();
        }
        /**
         * Animation type of {@code Animation}.
         * @return the protocol field value
         */
        public AnimationValue.TypeValues type() {
            return AnimationValue.TypeValues.of((String) require("type"));
        }
        /**
         * {@code Animation}&#x27;s source animation node.
         * @return the protocol field value, empty when absent
         */
        public Optional<Animation.AnimationEffect> source() {
            return Optional.ofNullable(raw("source") == null ? null : Animation.AnimationEffect.fromMap(java.util.Objects.requireNonNull(objectMap(raw("source")))));
        }
        /**
         * A unique ID for {@code Animation} representing the sources that triggered this CSS animation/transition.
         * @return the protocol field value, empty when absent
         */
        public Optional<String> cssId() {
            return Optional.ofNullable((String) raw("cssId"));
        }
        /**
         * View or scroll timeline
         * @return the protocol field value, empty when absent
         */
        public Optional<Animation.ViewOrScrollTimeline> viewOrScrollTimeline() {
            return Optional.ofNullable(raw("viewOrScrollTimeline") == null ? null : Animation.ViewOrScrollTimeline.fromMap(java.util.Objects.requireNonNull(objectMap(raw("viewOrScrollTimeline")))));
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
         * @param source field value; empty omits the value
         * @return this model
         */
        public AnimationValue source(Optional<Animation.AnimationEffect> source) {
            set("source", source.orElse(null));
            return this;
        }
        /**
         * {@code Animation}&#x27;s source animation node.
         * @param source field value; null removes the value
         * @return this model
         */
        public AnimationValue source(Animation.AnimationEffect source) {
            set("source", source);
            return this;
        }
        /**
         * A unique ID for {@code Animation} representing the sources that triggered this CSS animation/transition.
         * @param cssId field value; empty omits the value
         * @return this model
         */
        public AnimationValue cssId(Optional<String> cssId) {
            set("cssId", cssId.orElse(null));
            return this;
        }
        /**
         * A unique ID for {@code Animation} representing the sources that triggered this CSS animation/transition.
         * @param cssId field value; null removes the value
         * @return this model
         */
        public AnimationValue cssId(String cssId) {
            set("cssId", cssId);
            return this;
        }
        /**
         * View or scroll timeline
         * @param viewOrScrollTimeline field value; empty omits the value
         * @return this model
         */
        public AnimationValue viewOrScrollTimeline(Optional<Animation.ViewOrScrollTimeline> viewOrScrollTimeline) {
            set("viewOrScrollTimeline", viewOrScrollTimeline.orElse(null));
            return this;
        }
        /**
         * View or scroll timeline
         * @param viewOrScrollTimeline field value; null removes the value
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
            return Optional.ofNullable(raw("sourceNodeId") == null ? null : new DOM.BackendNodeId(((Number) raw("sourceNodeId")).longValue()));
        }
        /**
         * Represents the starting scroll position of the timeline as a length offset in pixels from scroll origin.
         * @return the protocol field value, empty when absent
         */
        public OptionalDouble startOffset() {
            Double value = CdpObject.numberAsDouble(raw("startOffset"));
            return value == null ? OptionalDouble.empty() : OptionalDouble.of(value);
        }
        /**
         * Represents the ending scroll position of the timeline as a length offset in pixels from scroll origin.
         * @return the protocol field value, empty when absent
         */
        public OptionalDouble endOffset() {
            Double value = CdpObject.numberAsDouble(raw("endOffset"));
            return value == null ? OptionalDouble.empty() : OptionalDouble.of(value);
        }
        /**
         * The element whose principal box&#x27;s visibility in the scrollport defined the progress of the timeline. Does not exist for animations with ScrollTimeline
         * @return the protocol field value, empty when absent
         */
        public Optional<DOM.BackendNodeId> subjectNodeId() {
            return Optional.ofNullable(raw("subjectNodeId") == null ? null : new DOM.BackendNodeId(((Number) raw("subjectNodeId")).longValue()));
        }
        /**
         * Orientation of the scroll
         * @return the protocol field value
         */
        public DOM.ScrollOrientation axis() {
            return DOM.ScrollOrientation.of((String) require("axis"));
        }
        /**
         * Scroll container node
         * @param sourceNodeId field value; empty omits the value
         * @return this model
         */
        public ViewOrScrollTimeline sourceNodeId(Optional<DOM.BackendNodeId> sourceNodeId) {
            set("sourceNodeId", sourceNodeId.orElse(null));
            return this;
        }
        /**
         * Scroll container node
         * @param sourceNodeId field value; null removes the value
         * @return this model
         */
        public ViewOrScrollTimeline sourceNodeId(DOM.BackendNodeId sourceNodeId) {
            set("sourceNodeId", sourceNodeId);
            return this;
        }
        /**
         * Represents the starting scroll position of the timeline as a length offset in pixels from scroll origin.
         * @param startOffset field value; empty omits the value
         * @return this model
         */
        public ViewOrScrollTimeline startOffset(OptionalDouble startOffset) {
            set("startOffset", startOffset.isPresent() ? startOffset.getAsDouble() : null);
            return this;
        }
        /**
         * Represents the starting scroll position of the timeline as a length offset in pixels from scroll origin.
         * @param startOffset field value; null removes the value
         * @return this model
         */
        public ViewOrScrollTimeline startOffset(Double startOffset) {
            set("startOffset", startOffset);
            return this;
        }
        /**
         * Represents the ending scroll position of the timeline as a length offset in pixels from scroll origin.
         * @param endOffset field value; empty omits the value
         * @return this model
         */
        public ViewOrScrollTimeline endOffset(OptionalDouble endOffset) {
            set("endOffset", endOffset.isPresent() ? endOffset.getAsDouble() : null);
            return this;
        }
        /**
         * Represents the ending scroll position of the timeline as a length offset in pixels from scroll origin.
         * @param endOffset field value; null removes the value
         * @return this model
         */
        public ViewOrScrollTimeline endOffset(Double endOffset) {
            set("endOffset", endOffset);
            return this;
        }
        /**
         * The element whose principal box&#x27;s visibility in the scrollport defined the progress of the timeline. Does not exist for animations with ScrollTimeline
         * @param subjectNodeId field value; empty omits the value
         * @return this model
         */
        public ViewOrScrollTimeline subjectNodeId(Optional<DOM.BackendNodeId> subjectNodeId) {
            set("subjectNodeId", subjectNodeId.orElse(null));
            return this;
        }
        /**
         * The element whose principal box&#x27;s visibility in the scrollport defined the progress of the timeline. Does not exist for animations with ScrollTimeline
         * @param subjectNodeId field value; null removes the value
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
            return ((Number) require("delay")).doubleValue();
        }
        /**
         * {@code AnimationEffect}&#x27;s end delay.
         * @return the protocol field value
         */
        public double endDelay() {
            return ((Number) require("endDelay")).doubleValue();
        }
        /**
         * {@code AnimationEffect}&#x27;s iteration start.
         * @return the protocol field value
         */
        public double iterationStart() {
            return ((Number) require("iterationStart")).doubleValue();
        }
        /**
         * {@code AnimationEffect}&#x27;s iterations. Omitted if the value is infinite.
         * @return the protocol field value, empty when absent
         */
        public OptionalDouble iterations() {
            Double value = CdpObject.numberAsDouble(raw("iterations"));
            return value == null ? OptionalDouble.empty() : OptionalDouble.of(value);
        }
        /**
         * {@code AnimationEffect}&#x27;s iteration duration. Milliseconds for time based animations and percentage [0 - 100] for scroll driven animations (i.e. when viewOrScrollTimeline exists).
         * @return the protocol field value
         */
        public double duration() {
            return ((Number) require("duration")).doubleValue();
        }
        /**
         * {@code AnimationEffect}&#x27;s playback direction.
         * @return the protocol field value
         */
        public String direction() {
            return (String) require("direction");
        }
        /**
         * {@code AnimationEffect}&#x27;s fill mode.
         * @return the protocol field value
         */
        public String fill() {
            return (String) require("fill");
        }
        /**
         * {@code AnimationEffect}&#x27;s target node.
         * @return the protocol field value, empty when absent
         */
        public Optional<DOM.BackendNodeId> backendNodeId() {
            return Optional.ofNullable(raw("backendNodeId") == null ? null : new DOM.BackendNodeId(((Number) raw("backendNodeId")).longValue()));
        }
        /**
         * {@code AnimationEffect}&#x27;s keyframes.
         * @return the protocol field value, empty when absent
         */
        public Optional<Animation.KeyframesRule> keyframesRule() {
            return Optional.ofNullable(raw("keyframesRule") == null ? null : Animation.KeyframesRule.fromMap(java.util.Objects.requireNonNull(objectMap(raw("keyframesRule")))));
        }
        /**
         * {@code AnimationEffect}&#x27;s timing function.
         * @return the protocol field value
         */
        public String easing() {
            return (String) require("easing");
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
         * @param iterations field value; empty omits the value
         * @return this model
         */
        public AnimationEffect iterations(OptionalDouble iterations) {
            set("iterations", iterations.isPresent() ? iterations.getAsDouble() : null);
            return this;
        }
        /**
         * {@code AnimationEffect}&#x27;s iterations. Omitted if the value is infinite.
         * @param iterations field value; null removes the value
         * @return this model
         */
        public AnimationEffect iterations(Double iterations) {
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
         * @param backendNodeId field value; empty omits the value
         * @return this model
         */
        public AnimationEffect backendNodeId(Optional<DOM.BackendNodeId> backendNodeId) {
            set("backendNodeId", backendNodeId.orElse(null));
            return this;
        }
        /**
         * {@code AnimationEffect}&#x27;s target node.
         * @param backendNodeId field value; null removes the value
         * @return this model
         */
        public AnimationEffect backendNodeId(DOM.BackendNodeId backendNodeId) {
            set("backendNodeId", backendNodeId);
            return this;
        }
        /**
         * {@code AnimationEffect}&#x27;s keyframes.
         * @param keyframesRule field value; empty omits the value
         * @return this model
         */
        public AnimationEffect keyframesRule(Optional<Animation.KeyframesRule> keyframesRule) {
            set("keyframesRule", keyframesRule.orElse(null));
            return this;
        }
        /**
         * {@code AnimationEffect}&#x27;s keyframes.
         * @param keyframesRule field value; null removes the value
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
            return Optional.ofNullable((String) raw("name"));
        }
        /**
         * List of animation keyframes.
         * @return the protocol field value
         */
        public java.util.List<Animation.KeyframeStyle> keyframes() {
            return CdpObject.requireList(require("keyframes"), element0 -> java.util.Objects.requireNonNull(Animation.KeyframeStyle.fromMap(java.util.Objects.requireNonNull(CdpObject.objectMap(element0)))));
        }
        /**
         * CSS keyframed animation&#x27;s name.
         * @param name field value; empty omits the value
         * @return this model
         */
        public KeyframesRule name(Optional<String> name) {
            set("name", name.orElse(null));
            return this;
        }
        /**
         * CSS keyframed animation&#x27;s name.
         * @param name field value; null removes the value
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
            return (String) require("offset");
        }
        /**
         * {@code AnimationEffect}&#x27;s timing function.
         * @return the protocol field value
         */
        public String easing() {
            return (String) require("easing");
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
        /**
         * Returns the current time of the an animation.
         * @param id protocol value
         */
        public GetCurrentTimeRequest(String id) {
            set("id", id);
        }
        public static GetCurrentTimeRequest fromMap(Map<String, Object> values) {
            GetCurrentTimeRequest instance_ = new GetCurrentTimeRequest();
            if (values != null) instance_.values.putAll(values);
            return instance_;
        }
        /**
         * Id of animation.
         * @return the protocol field value
         */
        public String id() {
            return (String) require("id");
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
        /**
         * Releases a set of animations to no longer be manipulated.
         * @param animations protocol value
         */
        public ReleaseAnimationsRequest(java.util.List<String> animations) {
            set("animations", animations);
        }
        public static ReleaseAnimationsRequest fromMap(Map<String, Object> values) {
            ReleaseAnimationsRequest instance_ = new ReleaseAnimationsRequest();
            if (values != null) instance_.values.putAll(values);
            return instance_;
        }
        /**
         * List of animation ids to seek.
         * @return the protocol field value
         */
        public java.util.List<String> animations() {
            return CdpObject.requireList(require("animations"), element0 -> (String) element0);
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
        /**
         * Gets the remote object of the Animation.
         * @param animationId protocol value
         */
        public ResolveAnimationRequest(String animationId) {
            set("animationId", animationId);
        }
        public static ResolveAnimationRequest fromMap(Map<String, Object> values) {
            ResolveAnimationRequest instance_ = new ResolveAnimationRequest();
            if (values != null) instance_.values.putAll(values);
            return instance_;
        }
        /**
         * Animation id.
         * @return the protocol field value
         */
        public String animationId() {
            return (String) require("animationId");
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
        /**
         * Seek a set of animations to a particular time within each animation.
         * @param animations protocol value
         * @param currentTime protocol value
         */
        public SeekAnimationsRequest(java.util.List<String> animations, double currentTime) {
            set("animations", animations);
            set("currentTime", currentTime);
        }
        public static SeekAnimationsRequest fromMap(Map<String, Object> values) {
            SeekAnimationsRequest instance_ = new SeekAnimationsRequest();
            if (values != null) instance_.values.putAll(values);
            return instance_;
        }
        /**
         * List of animation ids to seek.
         * @return the protocol field value
         */
        public java.util.List<String> animations() {
            return CdpObject.requireList(require("animations"), element0 -> (String) element0);
        }
        /**
         * Set the current time of each animation.
         * @return the protocol field value
         */
        public double currentTime() {
            return ((Number) require("currentTime")).doubleValue();
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
        /**
         * Sets the paused state of a set of animations.
         * @param animations protocol value
         * @param paused protocol value
         */
        public SetPausedRequest(java.util.List<String> animations, boolean paused) {
            set("animations", animations);
            set("paused", paused);
        }
        public static SetPausedRequest fromMap(Map<String, Object> values) {
            SetPausedRequest instance_ = new SetPausedRequest();
            if (values != null) instance_.values.putAll(values);
            return instance_;
        }
        /**
         * Animations to set the pause state of.
         * @return the protocol field value
         */
        public java.util.List<String> animations() {
            return CdpObject.requireList(require("animations"), element0 -> (String) element0);
        }
        /**
         * Paused state to set to.
         * @return the protocol field value
         */
        public boolean paused() {
            return (Boolean) require("paused");
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
        /**
         * Sets the playback rate of the document timeline.
         * @param playbackRate protocol value
         */
        public SetPlaybackRateRequest(double playbackRate) {
            set("playbackRate", playbackRate);
        }
        public static SetPlaybackRateRequest fromMap(Map<String, Object> values) {
            SetPlaybackRateRequest instance_ = new SetPlaybackRateRequest();
            if (values != null) instance_.values.putAll(values);
            return instance_;
        }
        /**
         * Playback rate for animations on page
         * @return the protocol field value
         */
        public double playbackRate() {
            return ((Number) require("playbackRate")).doubleValue();
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
        /**
         * Sets the timing of an animation node.
         * @param animationId protocol value
         * @param duration protocol value
         * @param delay protocol value
         */
        public SetTimingRequest(String animationId, double duration, double delay) {
            set("animationId", animationId);
            set("duration", duration);
            set("delay", delay);
        }
        public static SetTimingRequest fromMap(Map<String, Object> values) {
            SetTimingRequest instance_ = new SetTimingRequest();
            if (values != null) instance_.values.putAll(values);
            return instance_;
        }
        /**
         * Animation id.
         * @return the protocol field value
         */
        public String animationId() {
            return (String) require("animationId");
        }
        /**
         * Duration of the animation.
         * @return the protocol field value
         */
        public double duration() {
            return ((Number) require("duration")).doubleValue();
        }
        /**
         * Delay of the animation.
         * @return the protocol field value
         */
        public double delay() {
            return ((Number) require("delay")).doubleValue();
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
            return (String) require("id");
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
            return (String) require("id");
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
            return java.util.Objects.requireNonNull(Animation.AnimationValue.fromMap(java.util.Objects.requireNonNull(CdpObject.objectMap(require("animation")))));
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
            return java.util.Objects.requireNonNull(Animation.AnimationValue.fromMap(java.util.Objects.requireNonNull(CdpObject.objectMap(require("animation")))));
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
    public static final class Client {
        private final CdpClient client;
        public Client(CdpClient client) { this.client = client; }
        /**
         * Disables animation domain notifications.
         * @return a stage completing when the command completes
         */
        public CompletionStage<Void> disable() {
            return client.call("Animation.disable", null, result_ -> null);
        }
        /**
         * Enables animation domain notifications.
         * @return a stage completing when the command completes
         */
        public CompletionStage<Void> enable() {
            return client.call("Animation.enable", null, result_ -> null);
        }
        /**
         * Returns the current time of the an animation.
         * @param id protocol value
         * @return a stage completing with the command result
         */
        public CompletionStage<Double> getCurrentTime(String id) {
            Map<String, Object> params = new LinkedHashMap<>();
            params.put("id", CdpObject.json(id));
            return client.call("Animation.getCurrentTime", params, result_ -> ((Number) java.util.Objects.requireNonNull(result_.get("currentTime"))).doubleValue());
        }
        /**
         * Returns the current time of the an animation.
         * @param request request parameters
         * @return a stage completing with the command result
         */
        public CompletionStage<Double> getCurrentTime(GetCurrentTimeRequest request) {
            return client.call("Animation.getCurrentTime", request == null ? null : request.toMap(), result_ -> ((Number) java.util.Objects.requireNonNull(result_.get("currentTime"))).doubleValue());
        }
        /**
         * Gets the playback rate of the document timeline.
         * @return a stage completing with the command result
         */
        public CompletionStage<Double> getPlaybackRate() {
            return client.call("Animation.getPlaybackRate", null, result_ -> ((Number) java.util.Objects.requireNonNull(result_.get("playbackRate"))).doubleValue());
        }
        /**
         * Releases a set of animations to no longer be manipulated.
         * @param animations protocol value
         * @return a stage completing when the command completes
         */
        public CompletionStage<Void> releaseAnimations(java.util.List<String> animations) {
            Map<String, Object> params = new LinkedHashMap<>();
            params.put("animations", CdpObject.json(animations));
            return client.call("Animation.releaseAnimations", params, result_ -> null);
        }
        /**
         * Releases a set of animations to no longer be manipulated.
         * @param request request parameters
         * @return a stage completing when the command completes
         */
        public CompletionStage<Void> releaseAnimations(ReleaseAnimationsRequest request) {
            return client.call("Animation.releaseAnimations", request == null ? null : request.toMap(), result_ -> null);
        }
        /**
         * Gets the remote object of the Animation.
         * @param animationId protocol value
         * @return a stage completing with the command result
         */
        public CompletionStage<Runtime.RemoteObject> resolveAnimation(String animationId) {
            Map<String, Object> params = new LinkedHashMap<>();
            params.put("animationId", CdpObject.json(animationId));
            return client.call("Animation.resolveAnimation", params, result_ -> java.util.Objects.requireNonNull(Runtime.RemoteObject.fromMap(java.util.Objects.requireNonNull(CdpObject.objectMap(java.util.Objects.requireNonNull(result_.get("remoteObject")))))));
        }
        /**
         * Gets the remote object of the Animation.
         * @param request request parameters
         * @return a stage completing with the command result
         */
        public CompletionStage<Runtime.RemoteObject> resolveAnimation(ResolveAnimationRequest request) {
            return client.call("Animation.resolveAnimation", request == null ? null : request.toMap(), result_ -> java.util.Objects.requireNonNull(Runtime.RemoteObject.fromMap(java.util.Objects.requireNonNull(CdpObject.objectMap(java.util.Objects.requireNonNull(result_.get("remoteObject")))))));
        }
        /**
         * Seek a set of animations to a particular time within each animation.
         * @param animations protocol value
         * @param currentTime protocol value
         * @return a stage completing when the command completes
         */
        public CompletionStage<Void> seekAnimations(java.util.List<String> animations, double currentTime) {
            Map<String, Object> params = new LinkedHashMap<>();
            params.put("animations", CdpObject.json(animations));
            params.put("currentTime", CdpObject.json(currentTime));
            return client.call("Animation.seekAnimations", params, result_ -> null);
        }
        /**
         * Seek a set of animations to a particular time within each animation.
         * @param request request parameters
         * @return a stage completing when the command completes
         */
        public CompletionStage<Void> seekAnimations(SeekAnimationsRequest request) {
            return client.call("Animation.seekAnimations", request == null ? null : request.toMap(), result_ -> null);
        }
        /**
         * Sets the paused state of a set of animations.
         * @param animations protocol value
         * @param paused protocol value
         * @return a stage completing when the command completes
         */
        public CompletionStage<Void> setPaused(java.util.List<String> animations, boolean paused) {
            Map<String, Object> params = new LinkedHashMap<>();
            params.put("animations", CdpObject.json(animations));
            params.put("paused", CdpObject.json(paused));
            return client.call("Animation.setPaused", params, result_ -> null);
        }
        /**
         * Sets the paused state of a set of animations.
         * @param request request parameters
         * @return a stage completing when the command completes
         */
        public CompletionStage<Void> setPaused(SetPausedRequest request) {
            return client.call("Animation.setPaused", request == null ? null : request.toMap(), result_ -> null);
        }
        /**
         * Sets the playback rate of the document timeline.
         * @param playbackRate protocol value
         * @return a stage completing when the command completes
         */
        public CompletionStage<Void> setPlaybackRate(double playbackRate) {
            Map<String, Object> params = new LinkedHashMap<>();
            params.put("playbackRate", CdpObject.json(playbackRate));
            return client.call("Animation.setPlaybackRate", params, result_ -> null);
        }
        /**
         * Sets the playback rate of the document timeline.
         * @param request request parameters
         * @return a stage completing when the command completes
         */
        public CompletionStage<Void> setPlaybackRate(SetPlaybackRateRequest request) {
            return client.call("Animation.setPlaybackRate", request == null ? null : request.toMap(), result_ -> null);
        }
        /**
         * Sets the timing of an animation node.
         * @param animationId protocol value
         * @param duration protocol value
         * @param delay protocol value
         * @return a stage completing when the command completes
         */
        public CompletionStage<Void> setTiming(String animationId, double duration, double delay) {
            Map<String, Object> params = new LinkedHashMap<>();
            params.put("animationId", CdpObject.json(animationId));
            params.put("duration", CdpObject.json(duration));
            params.put("delay", CdpObject.json(delay));
            return client.call("Animation.setTiming", params, result_ -> null);
        }
        /**
         * Sets the timing of an animation node.
         * @param request request parameters
         * @return a stage completing when the command completes
         */
        public CompletionStage<Void> setTiming(SetTimingRequest request) {
            return client.call("Animation.setTiming", request == null ? null : request.toMap(), result_ -> null);
        }
        /**
         * Event for when an animation has been cancelled.
         * @param handler event callback
         * @return a removable subscription
         */
        public CdpSubscription onAnimationCanceled(Consumer<AnimationCanceledEvent> handler) {
            return client.on("Animation.animationCanceled", AnimationCanceledEvent::fromMap, handler);
        }
        /**
         * Event for each animation that has been created.
         * @param handler event callback
         * @return a removable subscription
         */
        public CdpSubscription onAnimationCreated(Consumer<AnimationCreatedEvent> handler) {
            return client.on("Animation.animationCreated", AnimationCreatedEvent::fromMap, handler);
        }
        /**
         * Event for animation that has been started.
         * @param handler event callback
         * @return a removable subscription
         */
        public CdpSubscription onAnimationStarted(Consumer<AnimationStartedEvent> handler) {
            return client.on("Animation.animationStarted", AnimationStartedEvent::fromMap, handler);
        }
        /**
         * Event for animation that has been updated.
         * @param handler event callback
         * @return a removable subscription
         */
        public CdpSubscription onAnimationUpdated(Consumer<AnimationUpdatedEvent> handler) {
            return client.on("Animation.animationUpdated", AnimationUpdatedEvent::fromMap, handler);
        }
    }
}
