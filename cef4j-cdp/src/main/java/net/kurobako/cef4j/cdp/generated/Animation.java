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
 * Chrome DevTools Protocol Animation domain.
 * <p><b>Experimental:</b> this part of CDP may change without notice.
 * @see <a href="https://chromium.googlesource.com/chromium/src/+/refs/tags/150.0.7871.213/third_party/blink/public/devtools_protocol/domains/Animation.pdl">Pinned protocol source</a>
 */
@SuppressWarnings({"EscapedEntity", "JavaLangClash", "MissingSummary", "UnusedMethod"})
public final class Animation {
    private Animation() {}
    @Nullable private static Long numberAsLong(@Nullable Object value) { return value == null ? null : ((Number) value).longValue(); }
    @Nullable private static Double numberAsDouble(@Nullable Object value) { return value == null ? null : ((Number) value).doubleValue(); }
    /**
     * Animation instance.
     */
    public static final class AnimationValue extends CdpObject {
        private AnimationValue(Map<String, Object> values) { super(values); }
        @Nullable public static AnimationValue fromMap(@Nullable Map<String, Object> values) {
            return values == null ? null : new AnimationValue(values);
        }
        public static Builder builder() { return new Builder(); }
        /**
         * {@code Animation}&#x27;s id.
         * @return the protocol field value
         */
        @Nullable public String id() {
            return (String) value("id");
        }
        /**
         * {@code Animation}&#x27;s name.
         * @return the protocol field value
         */
        @Nullable public String name() {
            return (String) value("name");
        }
        /**
         * {@code Animation}&#x27;s internal paused state.
         * @return the protocol field value
         */
        @Nullable public Boolean pausedState() {
            return (Boolean) value("pausedState");
        }
        /**
         * {@code Animation}&#x27;s play state.
         * @return the protocol field value
         */
        @Nullable public String playState() {
            return (String) value("playState");
        }
        /**
         * {@code Animation}&#x27;s playback rate.
         * @return the protocol field value
         */
        @Nullable public Double playbackRate() {
            return numberAsDouble(value("playbackRate"));
        }
        /**
         * {@code Animation}&#x27;s start time. Milliseconds for time based animations and percentage [0 - 100] for scroll driven animations (i.e. when viewOrScrollTimeline exists).
         * @return the protocol field value
         */
        @Nullable public Double startTime() {
            return numberAsDouble(value("startTime"));
        }
        /**
         * {@code Animation}&#x27;s current time.
         * @return the protocol field value
         */
        @Nullable public Double currentTime() {
            return numberAsDouble(value("currentTime"));
        }
        /**
         * Animation type of {@code Animation}.
         * @return the protocol field value
         */
        @Nullable public String type() {
            return (String) value("type");
        }
        /**
         * Animation type of {@code Animation}.
         */
        public static final class TypeValues {
            private TypeValues() {}
            public static final String CSSTRANSITION = "CSSTransition";
            public static final String CSSANIMATION = "CSSAnimation";
            public static final String WEBANIMATION = "WebAnimation";
        }
        /**
         * {@code Animation}&#x27;s source animation node.
         * @return the protocol field value
         */
        @Nullable public Animation.AnimationEffect source() {
            return Animation.AnimationEffect.fromMap(objectMap(value("source")));
        }
        /**
         * A unique ID for {@code Animation} representing the sources that triggered this CSS animation/transition.
         * @return the protocol field value
         */
        @Nullable public String cssId() {
            return (String) value("cssId");
        }
        /**
         * View or scroll timeline
         * @return the protocol field value
         */
        @Nullable public Animation.ViewOrScrollTimeline viewOrScrollTimeline() {
            return Animation.ViewOrScrollTimeline.fromMap(objectMap(value("viewOrScrollTimeline")));
        }
        public static final class Builder {
            private final Map<String, Object> values = new LinkedHashMap<>();
            /**
             * {@code Animation}&#x27;s id.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder id(@Nullable String value) {
                if (value == null) values.remove("id");
                else values.put("id", jsonValue(value));
                return this;
            }
            /**
             * {@code Animation}&#x27;s name.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder name(@Nullable String value) {
                if (value == null) values.remove("name");
                else values.put("name", jsonValue(value));
                return this;
            }
            /**
             * {@code Animation}&#x27;s internal paused state.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder pausedState(@Nullable Boolean value) {
                if (value == null) values.remove("pausedState");
                else values.put("pausedState", jsonValue(value));
                return this;
            }
            /**
             * {@code Animation}&#x27;s play state.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder playState(@Nullable String value) {
                if (value == null) values.remove("playState");
                else values.put("playState", jsonValue(value));
                return this;
            }
            /**
             * {@code Animation}&#x27;s playback rate.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder playbackRate(@Nullable Double value) {
                if (value == null) values.remove("playbackRate");
                else values.put("playbackRate", jsonValue(value));
                return this;
            }
            /**
             * {@code Animation}&#x27;s start time. Milliseconds for time based animations and percentage [0 - 100] for scroll driven animations (i.e. when viewOrScrollTimeline exists).
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder startTime(@Nullable Double value) {
                if (value == null) values.remove("startTime");
                else values.put("startTime", jsonValue(value));
                return this;
            }
            /**
             * {@code Animation}&#x27;s current time.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder currentTime(@Nullable Double value) {
                if (value == null) values.remove("currentTime");
                else values.put("currentTime", jsonValue(value));
                return this;
            }
            /**
             * Animation type of {@code Animation}.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder type(@Nullable String value) {
                if (value == null) values.remove("type");
                else values.put("type", jsonValue(value));
                return this;
            }
            /**
             * {@code Animation}&#x27;s source animation node.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder source(@Nullable Animation.AnimationEffect value) {
                if (value == null) values.remove("source");
                else values.put("source", jsonValue(value));
                return this;
            }
            /**
             * A unique ID for {@code Animation} representing the sources that triggered this CSS animation/transition.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder cssId(@Nullable String value) {
                if (value == null) values.remove("cssId");
                else values.put("cssId", jsonValue(value));
                return this;
            }
            /**
             * View or scroll timeline
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder viewOrScrollTimeline(@Nullable Animation.ViewOrScrollTimeline value) {
                if (value == null) values.remove("viewOrScrollTimeline");
                else values.put("viewOrScrollTimeline", jsonValue(value));
                return this;
            }
            public AnimationValue build() {
                if (!values.containsKey("id")) throw new IllegalStateException("Missing required CDP field: id");
                if (!values.containsKey("name")) throw new IllegalStateException("Missing required CDP field: name");
                if (!values.containsKey("pausedState")) throw new IllegalStateException("Missing required CDP field: pausedState");
                if (!values.containsKey("playState")) throw new IllegalStateException("Missing required CDP field: playState");
                if (!values.containsKey("playbackRate")) throw new IllegalStateException("Missing required CDP field: playbackRate");
                if (!values.containsKey("startTime")) throw new IllegalStateException("Missing required CDP field: startTime");
                if (!values.containsKey("currentTime")) throw new IllegalStateException("Missing required CDP field: currentTime");
                if (!values.containsKey("type")) throw new IllegalStateException("Missing required CDP field: type");
                return new AnimationValue(values);
            }
        }
    }
    /**
     * Timeline instance
     */
    public static final class ViewOrScrollTimeline extends CdpObject {
        private ViewOrScrollTimeline(Map<String, Object> values) { super(values); }
        @Nullable public static ViewOrScrollTimeline fromMap(@Nullable Map<String, Object> values) {
            return values == null ? null : new ViewOrScrollTimeline(values);
        }
        public static Builder builder() { return new Builder(); }
        /**
         * Scroll container node
         * @return the protocol field value
         */
        @Nullable public Long sourceNodeId() {
            return numberAsLong(value("sourceNodeId"));
        }
        /**
         * Represents the starting scroll position of the timeline as a length offset in pixels from scroll origin.
         * @return the protocol field value
         */
        @Nullable public Double startOffset() {
            return numberAsDouble(value("startOffset"));
        }
        /**
         * Represents the ending scroll position of the timeline as a length offset in pixels from scroll origin.
         * @return the protocol field value
         */
        @Nullable public Double endOffset() {
            return numberAsDouble(value("endOffset"));
        }
        /**
         * The element whose principal box&#x27;s visibility in the scrollport defined the progress of the timeline. Does not exist for animations with ScrollTimeline
         * @return the protocol field value
         */
        @Nullable public Long subjectNodeId() {
            return numberAsLong(value("subjectNodeId"));
        }
        /**
         * Orientation of the scroll
         * @return the protocol field value
         */
        @Nullable public String axis() {
            return (String) value("axis");
        }
        public static final class Builder {
            private final Map<String, Object> values = new LinkedHashMap<>();
            /**
             * Scroll container node
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder sourceNodeId(@Nullable Long value) {
                if (value == null) values.remove("sourceNodeId");
                else values.put("sourceNodeId", jsonValue(value));
                return this;
            }
            /**
             * Represents the starting scroll position of the timeline as a length offset in pixels from scroll origin.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder startOffset(@Nullable Double value) {
                if (value == null) values.remove("startOffset");
                else values.put("startOffset", jsonValue(value));
                return this;
            }
            /**
             * Represents the ending scroll position of the timeline as a length offset in pixels from scroll origin.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder endOffset(@Nullable Double value) {
                if (value == null) values.remove("endOffset");
                else values.put("endOffset", jsonValue(value));
                return this;
            }
            /**
             * The element whose principal box&#x27;s visibility in the scrollport defined the progress of the timeline. Does not exist for animations with ScrollTimeline
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder subjectNodeId(@Nullable Long value) {
                if (value == null) values.remove("subjectNodeId");
                else values.put("subjectNodeId", jsonValue(value));
                return this;
            }
            /**
             * Orientation of the scroll
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder axis(@Nullable String value) {
                if (value == null) values.remove("axis");
                else values.put("axis", jsonValue(value));
                return this;
            }
            public ViewOrScrollTimeline build() {
                if (!values.containsKey("axis")) throw new IllegalStateException("Missing required CDP field: axis");
                return new ViewOrScrollTimeline(values);
            }
        }
    }
    /**
     * AnimationEffect instance
     */
    public static final class AnimationEffect extends CdpObject {
        private AnimationEffect(Map<String, Object> values) { super(values); }
        @Nullable public static AnimationEffect fromMap(@Nullable Map<String, Object> values) {
            return values == null ? null : new AnimationEffect(values);
        }
        public static Builder builder() { return new Builder(); }
        /**
         * {@code AnimationEffect}&#x27;s delay.
         * @return the protocol field value
         */
        @Nullable public Double delay() {
            return numberAsDouble(value("delay"));
        }
        /**
         * {@code AnimationEffect}&#x27;s end delay.
         * @return the protocol field value
         */
        @Nullable public Double endDelay() {
            return numberAsDouble(value("endDelay"));
        }
        /**
         * {@code AnimationEffect}&#x27;s iteration start.
         * @return the protocol field value
         */
        @Nullable public Double iterationStart() {
            return numberAsDouble(value("iterationStart"));
        }
        /**
         * {@code AnimationEffect}&#x27;s iterations. Omitted if the value is infinite.
         * @return the protocol field value
         */
        @Nullable public Double iterations() {
            return numberAsDouble(value("iterations"));
        }
        /**
         * {@code AnimationEffect}&#x27;s iteration duration. Milliseconds for time based animations and percentage [0 - 100] for scroll driven animations (i.e. when viewOrScrollTimeline exists).
         * @return the protocol field value
         */
        @Nullable public Double duration() {
            return numberAsDouble(value("duration"));
        }
        /**
         * {@code AnimationEffect}&#x27;s playback direction.
         * @return the protocol field value
         */
        @Nullable public String direction() {
            return (String) value("direction");
        }
        /**
         * {@code AnimationEffect}&#x27;s fill mode.
         * @return the protocol field value
         */
        @Nullable public String fill() {
            return (String) value("fill");
        }
        /**
         * {@code AnimationEffect}&#x27;s target node.
         * @return the protocol field value
         */
        @Nullable public Long backendNodeId() {
            return numberAsLong(value("backendNodeId"));
        }
        /**
         * {@code AnimationEffect}&#x27;s keyframes.
         * @return the protocol field value
         */
        @Nullable public Animation.KeyframesRule keyframesRule() {
            return Animation.KeyframesRule.fromMap(objectMap(value("keyframesRule")));
        }
        /**
         * {@code AnimationEffect}&#x27;s timing function.
         * @return the protocol field value
         */
        @Nullable public String easing() {
            return (String) value("easing");
        }
        public static final class Builder {
            private final Map<String, Object> values = new LinkedHashMap<>();
            /**
             * {@code AnimationEffect}&#x27;s delay.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder delay(@Nullable Double value) {
                if (value == null) values.remove("delay");
                else values.put("delay", jsonValue(value));
                return this;
            }
            /**
             * {@code AnimationEffect}&#x27;s end delay.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder endDelay(@Nullable Double value) {
                if (value == null) values.remove("endDelay");
                else values.put("endDelay", jsonValue(value));
                return this;
            }
            /**
             * {@code AnimationEffect}&#x27;s iteration start.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder iterationStart(@Nullable Double value) {
                if (value == null) values.remove("iterationStart");
                else values.put("iterationStart", jsonValue(value));
                return this;
            }
            /**
             * {@code AnimationEffect}&#x27;s iterations. Omitted if the value is infinite.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder iterations(@Nullable Double value) {
                if (value == null) values.remove("iterations");
                else values.put("iterations", jsonValue(value));
                return this;
            }
            /**
             * {@code AnimationEffect}&#x27;s iteration duration. Milliseconds for time based animations and percentage [0 - 100] for scroll driven animations (i.e. when viewOrScrollTimeline exists).
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder duration(@Nullable Double value) {
                if (value == null) values.remove("duration");
                else values.put("duration", jsonValue(value));
                return this;
            }
            /**
             * {@code AnimationEffect}&#x27;s playback direction.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder direction(@Nullable String value) {
                if (value == null) values.remove("direction");
                else values.put("direction", jsonValue(value));
                return this;
            }
            /**
             * {@code AnimationEffect}&#x27;s fill mode.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder fill(@Nullable String value) {
                if (value == null) values.remove("fill");
                else values.put("fill", jsonValue(value));
                return this;
            }
            /**
             * {@code AnimationEffect}&#x27;s target node.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder backendNodeId(@Nullable Long value) {
                if (value == null) values.remove("backendNodeId");
                else values.put("backendNodeId", jsonValue(value));
                return this;
            }
            /**
             * {@code AnimationEffect}&#x27;s keyframes.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder keyframesRule(@Nullable Animation.KeyframesRule value) {
                if (value == null) values.remove("keyframesRule");
                else values.put("keyframesRule", jsonValue(value));
                return this;
            }
            /**
             * {@code AnimationEffect}&#x27;s timing function.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder easing(@Nullable String value) {
                if (value == null) values.remove("easing");
                else values.put("easing", jsonValue(value));
                return this;
            }
            public AnimationEffect build() {
                if (!values.containsKey("delay")) throw new IllegalStateException("Missing required CDP field: delay");
                if (!values.containsKey("endDelay")) throw new IllegalStateException("Missing required CDP field: endDelay");
                if (!values.containsKey("iterationStart")) throw new IllegalStateException("Missing required CDP field: iterationStart");
                if (!values.containsKey("duration")) throw new IllegalStateException("Missing required CDP field: duration");
                if (!values.containsKey("direction")) throw new IllegalStateException("Missing required CDP field: direction");
                if (!values.containsKey("fill")) throw new IllegalStateException("Missing required CDP field: fill");
                if (!values.containsKey("easing")) throw new IllegalStateException("Missing required CDP field: easing");
                return new AnimationEffect(values);
            }
        }
    }
    /**
     * Keyframes Rule
     */
    public static final class KeyframesRule extends CdpObject {
        private KeyframesRule(Map<String, Object> values) { super(values); }
        @Nullable public static KeyframesRule fromMap(@Nullable Map<String, Object> values) {
            return values == null ? null : new KeyframesRule(values);
        }
        public static Builder builder() { return new Builder(); }
        /**
         * CSS keyframed animation&#x27;s name.
         * @return the protocol field value
         */
        @Nullable public String name() {
            return (String) value("name");
        }
        /**
         * List of animation keyframes.
         * @return the protocol field value
         */
        @Nullable public java.util.List<Animation.KeyframeStyle> keyframes() {
            return list(value("keyframes"), element0 -> Animation.KeyframeStyle.fromMap(objectMap(element0)));
        }
        public static final class Builder {
            private final Map<String, Object> values = new LinkedHashMap<>();
            /**
             * CSS keyframed animation&#x27;s name.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder name(@Nullable String value) {
                if (value == null) values.remove("name");
                else values.put("name", jsonValue(value));
                return this;
            }
            /**
             * List of animation keyframes.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder keyframes(@Nullable java.util.List<Animation.KeyframeStyle> value) {
                if (value == null) values.remove("keyframes");
                else values.put("keyframes", jsonValue(value));
                return this;
            }
            public KeyframesRule build() {
                if (!values.containsKey("keyframes")) throw new IllegalStateException("Missing required CDP field: keyframes");
                return new KeyframesRule(values);
            }
        }
    }
    /**
     * Keyframe Style
     */
    public static final class KeyframeStyle extends CdpObject {
        private KeyframeStyle(Map<String, Object> values) { super(values); }
        @Nullable public static KeyframeStyle fromMap(@Nullable Map<String, Object> values) {
            return values == null ? null : new KeyframeStyle(values);
        }
        public static Builder builder() { return new Builder(); }
        /**
         * Keyframe&#x27;s time offset.
         * @return the protocol field value
         */
        @Nullable public String offset() {
            return (String) value("offset");
        }
        /**
         * {@code AnimationEffect}&#x27;s timing function.
         * @return the protocol field value
         */
        @Nullable public String easing() {
            return (String) value("easing");
        }
        public static final class Builder {
            private final Map<String, Object> values = new LinkedHashMap<>();
            /**
             * Keyframe&#x27;s time offset.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder offset(@Nullable String value) {
                if (value == null) values.remove("offset");
                else values.put("offset", jsonValue(value));
                return this;
            }
            /**
             * {@code AnimationEffect}&#x27;s timing function.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder easing(@Nullable String value) {
                if (value == null) values.remove("easing");
                else values.put("easing", jsonValue(value));
                return this;
            }
            public KeyframeStyle build() {
                if (!values.containsKey("offset")) throw new IllegalStateException("Missing required CDP field: offset");
                if (!values.containsKey("easing")) throw new IllegalStateException("Missing required CDP field: easing");
                return new KeyframeStyle(values);
            }
        }
    }
    /**
     * Disables animation domain notifications.
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
     * Disables animation domain notifications.
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
     * Enables animation domain notifications.
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
     * Enables animation domain notifications.
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
     * Returns the current time of the an animation.
     */
    public static final class GetCurrentTimeParams extends CdpObject {
        private GetCurrentTimeParams(Map<String, Object> values) { super(values); }
        @Nullable public static GetCurrentTimeParams fromMap(@Nullable Map<String, Object> values) {
            return values == null ? null : new GetCurrentTimeParams(values);
        }
        public static Builder builder() { return new Builder(); }
        /**
         * Id of animation.
         * @return the protocol field value
         */
        @Nullable public String id() {
            return (String) value("id");
        }
        public static final class Builder {
            private final Map<String, Object> values = new LinkedHashMap<>();
            /**
             * Id of animation.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder id(@Nullable String value) {
                if (value == null) values.remove("id");
                else values.put("id", jsonValue(value));
                return this;
            }
            public GetCurrentTimeParams build() {
                if (!values.containsKey("id")) throw new IllegalStateException("Missing required CDP field: id");
                return new GetCurrentTimeParams(values);
            }
        }
    }
    /**
     * Returns the current time of the an animation.
     */
    public static final class GetCurrentTimeResult extends CdpObject {
        private GetCurrentTimeResult(Map<String, Object> values) { super(values); }
        @Nullable public static GetCurrentTimeResult fromMap(@Nullable Map<String, Object> values) {
            return values == null ? null : new GetCurrentTimeResult(values);
        }
        public static Builder builder() { return new Builder(); }
        /**
         * Current time of the page.
         * @return the protocol field value
         */
        @Nullable public Double currentTime() {
            return numberAsDouble(value("currentTime"));
        }
        public static final class Builder {
            private final Map<String, Object> values = new LinkedHashMap<>();
            /**
             * Current time of the page.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder currentTime(@Nullable Double value) {
                if (value == null) values.remove("currentTime");
                else values.put("currentTime", jsonValue(value));
                return this;
            }
            public GetCurrentTimeResult build() {
                if (!values.containsKey("currentTime")) throw new IllegalStateException("Missing required CDP field: currentTime");
                return new GetCurrentTimeResult(values);
            }
        }
    }
    /**
     * Gets the playback rate of the document timeline.
     */
    public static final class GetPlaybackRateParams extends CdpObject {
        private GetPlaybackRateParams(Map<String, Object> values) { super(values); }
        @Nullable public static GetPlaybackRateParams fromMap(@Nullable Map<String, Object> values) {
            return values == null ? null : new GetPlaybackRateParams(values);
        }
        public static Builder builder() { return new Builder(); }
        public static final class Builder {
            private final Map<String, Object> values = new LinkedHashMap<>();
            public GetPlaybackRateParams build() {
                return new GetPlaybackRateParams(values);
            }
        }
    }
    /**
     * Gets the playback rate of the document timeline.
     */
    public static final class GetPlaybackRateResult extends CdpObject {
        private GetPlaybackRateResult(Map<String, Object> values) { super(values); }
        @Nullable public static GetPlaybackRateResult fromMap(@Nullable Map<String, Object> values) {
            return values == null ? null : new GetPlaybackRateResult(values);
        }
        public static Builder builder() { return new Builder(); }
        /**
         * Playback rate for animations on page.
         * @return the protocol field value
         */
        @Nullable public Double playbackRate() {
            return numberAsDouble(value("playbackRate"));
        }
        public static final class Builder {
            private final Map<String, Object> values = new LinkedHashMap<>();
            /**
             * Playback rate for animations on page.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder playbackRate(@Nullable Double value) {
                if (value == null) values.remove("playbackRate");
                else values.put("playbackRate", jsonValue(value));
                return this;
            }
            public GetPlaybackRateResult build() {
                if (!values.containsKey("playbackRate")) throw new IllegalStateException("Missing required CDP field: playbackRate");
                return new GetPlaybackRateResult(values);
            }
        }
    }
    /**
     * Releases a set of animations to no longer be manipulated.
     */
    public static final class ReleaseAnimationsParams extends CdpObject {
        private ReleaseAnimationsParams(Map<String, Object> values) { super(values); }
        @Nullable public static ReleaseAnimationsParams fromMap(@Nullable Map<String, Object> values) {
            return values == null ? null : new ReleaseAnimationsParams(values);
        }
        public static Builder builder() { return new Builder(); }
        /**
         * List of animation ids to seek.
         * @return the protocol field value
         */
        @Nullable public java.util.List<String> animations() {
            return list(value("animations"), element0 -> (String) element0);
        }
        public static final class Builder {
            private final Map<String, Object> values = new LinkedHashMap<>();
            /**
             * List of animation ids to seek.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder animations(@Nullable java.util.List<String> value) {
                if (value == null) values.remove("animations");
                else values.put("animations", jsonValue(value));
                return this;
            }
            public ReleaseAnimationsParams build() {
                if (!values.containsKey("animations")) throw new IllegalStateException("Missing required CDP field: animations");
                return new ReleaseAnimationsParams(values);
            }
        }
    }
    /**
     * Releases a set of animations to no longer be manipulated.
     */
    public static final class ReleaseAnimationsResult extends CdpObject {
        private ReleaseAnimationsResult(Map<String, Object> values) { super(values); }
        @Nullable public static ReleaseAnimationsResult fromMap(@Nullable Map<String, Object> values) {
            return values == null ? null : new ReleaseAnimationsResult(values);
        }
        public static Builder builder() { return new Builder(); }
        public static final class Builder {
            private final Map<String, Object> values = new LinkedHashMap<>();
            public ReleaseAnimationsResult build() {
                return new ReleaseAnimationsResult(values);
            }
        }
    }
    /**
     * Gets the remote object of the Animation.
     */
    public static final class ResolveAnimationParams extends CdpObject {
        private ResolveAnimationParams(Map<String, Object> values) { super(values); }
        @Nullable public static ResolveAnimationParams fromMap(@Nullable Map<String, Object> values) {
            return values == null ? null : new ResolveAnimationParams(values);
        }
        public static Builder builder() { return new Builder(); }
        /**
         * Animation id.
         * @return the protocol field value
         */
        @Nullable public String animationId() {
            return (String) value("animationId");
        }
        public static final class Builder {
            private final Map<String, Object> values = new LinkedHashMap<>();
            /**
             * Animation id.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder animationId(@Nullable String value) {
                if (value == null) values.remove("animationId");
                else values.put("animationId", jsonValue(value));
                return this;
            }
            public ResolveAnimationParams build() {
                if (!values.containsKey("animationId")) throw new IllegalStateException("Missing required CDP field: animationId");
                return new ResolveAnimationParams(values);
            }
        }
    }
    /**
     * Gets the remote object of the Animation.
     */
    public static final class ResolveAnimationResult extends CdpObject {
        private ResolveAnimationResult(Map<String, Object> values) { super(values); }
        @Nullable public static ResolveAnimationResult fromMap(@Nullable Map<String, Object> values) {
            return values == null ? null : new ResolveAnimationResult(values);
        }
        public static Builder builder() { return new Builder(); }
        /**
         * Corresponding remote object.
         * @return the protocol field value
         */
        @Nullable public Runtime.RemoteObject remoteObject() {
            return Runtime.RemoteObject.fromMap(objectMap(value("remoteObject")));
        }
        public static final class Builder {
            private final Map<String, Object> values = new LinkedHashMap<>();
            /**
             * Corresponding remote object.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder remoteObject(@Nullable Runtime.RemoteObject value) {
                if (value == null) values.remove("remoteObject");
                else values.put("remoteObject", jsonValue(value));
                return this;
            }
            public ResolveAnimationResult build() {
                if (!values.containsKey("remoteObject")) throw new IllegalStateException("Missing required CDP field: remoteObject");
                return new ResolveAnimationResult(values);
            }
        }
    }
    /**
     * Seek a set of animations to a particular time within each animation.
     */
    public static final class SeekAnimationsParams extends CdpObject {
        private SeekAnimationsParams(Map<String, Object> values) { super(values); }
        @Nullable public static SeekAnimationsParams fromMap(@Nullable Map<String, Object> values) {
            return values == null ? null : new SeekAnimationsParams(values);
        }
        public static Builder builder() { return new Builder(); }
        /**
         * List of animation ids to seek.
         * @return the protocol field value
         */
        @Nullable public java.util.List<String> animations() {
            return list(value("animations"), element0 -> (String) element0);
        }
        /**
         * Set the current time of each animation.
         * @return the protocol field value
         */
        @Nullable public Double currentTime() {
            return numberAsDouble(value("currentTime"));
        }
        public static final class Builder {
            private final Map<String, Object> values = new LinkedHashMap<>();
            /**
             * List of animation ids to seek.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder animations(@Nullable java.util.List<String> value) {
                if (value == null) values.remove("animations");
                else values.put("animations", jsonValue(value));
                return this;
            }
            /**
             * Set the current time of each animation.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder currentTime(@Nullable Double value) {
                if (value == null) values.remove("currentTime");
                else values.put("currentTime", jsonValue(value));
                return this;
            }
            public SeekAnimationsParams build() {
                if (!values.containsKey("animations")) throw new IllegalStateException("Missing required CDP field: animations");
                if (!values.containsKey("currentTime")) throw new IllegalStateException("Missing required CDP field: currentTime");
                return new SeekAnimationsParams(values);
            }
        }
    }
    /**
     * Seek a set of animations to a particular time within each animation.
     */
    public static final class SeekAnimationsResult extends CdpObject {
        private SeekAnimationsResult(Map<String, Object> values) { super(values); }
        @Nullable public static SeekAnimationsResult fromMap(@Nullable Map<String, Object> values) {
            return values == null ? null : new SeekAnimationsResult(values);
        }
        public static Builder builder() { return new Builder(); }
        public static final class Builder {
            private final Map<String, Object> values = new LinkedHashMap<>();
            public SeekAnimationsResult build() {
                return new SeekAnimationsResult(values);
            }
        }
    }
    /**
     * Sets the paused state of a set of animations.
     */
    public static final class SetPausedParams extends CdpObject {
        private SetPausedParams(Map<String, Object> values) { super(values); }
        @Nullable public static SetPausedParams fromMap(@Nullable Map<String, Object> values) {
            return values == null ? null : new SetPausedParams(values);
        }
        public static Builder builder() { return new Builder(); }
        /**
         * Animations to set the pause state of.
         * @return the protocol field value
         */
        @Nullable public java.util.List<String> animations() {
            return list(value("animations"), element0 -> (String) element0);
        }
        /**
         * Paused state to set to.
         * @return the protocol field value
         */
        @Nullable public Boolean paused() {
            return (Boolean) value("paused");
        }
        public static final class Builder {
            private final Map<String, Object> values = new LinkedHashMap<>();
            /**
             * Animations to set the pause state of.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder animations(@Nullable java.util.List<String> value) {
                if (value == null) values.remove("animations");
                else values.put("animations", jsonValue(value));
                return this;
            }
            /**
             * Paused state to set to.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder paused(@Nullable Boolean value) {
                if (value == null) values.remove("paused");
                else values.put("paused", jsonValue(value));
                return this;
            }
            public SetPausedParams build() {
                if (!values.containsKey("animations")) throw new IllegalStateException("Missing required CDP field: animations");
                if (!values.containsKey("paused")) throw new IllegalStateException("Missing required CDP field: paused");
                return new SetPausedParams(values);
            }
        }
    }
    /**
     * Sets the paused state of a set of animations.
     */
    public static final class SetPausedResult extends CdpObject {
        private SetPausedResult(Map<String, Object> values) { super(values); }
        @Nullable public static SetPausedResult fromMap(@Nullable Map<String, Object> values) {
            return values == null ? null : new SetPausedResult(values);
        }
        public static Builder builder() { return new Builder(); }
        public static final class Builder {
            private final Map<String, Object> values = new LinkedHashMap<>();
            public SetPausedResult build() {
                return new SetPausedResult(values);
            }
        }
    }
    /**
     * Sets the playback rate of the document timeline.
     */
    public static final class SetPlaybackRateParams extends CdpObject {
        private SetPlaybackRateParams(Map<String, Object> values) { super(values); }
        @Nullable public static SetPlaybackRateParams fromMap(@Nullable Map<String, Object> values) {
            return values == null ? null : new SetPlaybackRateParams(values);
        }
        public static Builder builder() { return new Builder(); }
        /**
         * Playback rate for animations on page
         * @return the protocol field value
         */
        @Nullable public Double playbackRate() {
            return numberAsDouble(value("playbackRate"));
        }
        public static final class Builder {
            private final Map<String, Object> values = new LinkedHashMap<>();
            /**
             * Playback rate for animations on page
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder playbackRate(@Nullable Double value) {
                if (value == null) values.remove("playbackRate");
                else values.put("playbackRate", jsonValue(value));
                return this;
            }
            public SetPlaybackRateParams build() {
                if (!values.containsKey("playbackRate")) throw new IllegalStateException("Missing required CDP field: playbackRate");
                return new SetPlaybackRateParams(values);
            }
        }
    }
    /**
     * Sets the playback rate of the document timeline.
     */
    public static final class SetPlaybackRateResult extends CdpObject {
        private SetPlaybackRateResult(Map<String, Object> values) { super(values); }
        @Nullable public static SetPlaybackRateResult fromMap(@Nullable Map<String, Object> values) {
            return values == null ? null : new SetPlaybackRateResult(values);
        }
        public static Builder builder() { return new Builder(); }
        public static final class Builder {
            private final Map<String, Object> values = new LinkedHashMap<>();
            public SetPlaybackRateResult build() {
                return new SetPlaybackRateResult(values);
            }
        }
    }
    /**
     * Sets the timing of an animation node.
     */
    public static final class SetTimingParams extends CdpObject {
        private SetTimingParams(Map<String, Object> values) { super(values); }
        @Nullable public static SetTimingParams fromMap(@Nullable Map<String, Object> values) {
            return values == null ? null : new SetTimingParams(values);
        }
        public static Builder builder() { return new Builder(); }
        /**
         * Animation id.
         * @return the protocol field value
         */
        @Nullable public String animationId() {
            return (String) value("animationId");
        }
        /**
         * Duration of the animation.
         * @return the protocol field value
         */
        @Nullable public Double duration() {
            return numberAsDouble(value("duration"));
        }
        /**
         * Delay of the animation.
         * @return the protocol field value
         */
        @Nullable public Double delay() {
            return numberAsDouble(value("delay"));
        }
        public static final class Builder {
            private final Map<String, Object> values = new LinkedHashMap<>();
            /**
             * Animation id.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder animationId(@Nullable String value) {
                if (value == null) values.remove("animationId");
                else values.put("animationId", jsonValue(value));
                return this;
            }
            /**
             * Duration of the animation.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder duration(@Nullable Double value) {
                if (value == null) values.remove("duration");
                else values.put("duration", jsonValue(value));
                return this;
            }
            /**
             * Delay of the animation.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder delay(@Nullable Double value) {
                if (value == null) values.remove("delay");
                else values.put("delay", jsonValue(value));
                return this;
            }
            public SetTimingParams build() {
                if (!values.containsKey("animationId")) throw new IllegalStateException("Missing required CDP field: animationId");
                if (!values.containsKey("duration")) throw new IllegalStateException("Missing required CDP field: duration");
                if (!values.containsKey("delay")) throw new IllegalStateException("Missing required CDP field: delay");
                return new SetTimingParams(values);
            }
        }
    }
    /**
     * Sets the timing of an animation node.
     */
    public static final class SetTimingResult extends CdpObject {
        private SetTimingResult(Map<String, Object> values) { super(values); }
        @Nullable public static SetTimingResult fromMap(@Nullable Map<String, Object> values) {
            return values == null ? null : new SetTimingResult(values);
        }
        public static Builder builder() { return new Builder(); }
        public static final class Builder {
            private final Map<String, Object> values = new LinkedHashMap<>();
            public SetTimingResult build() {
                return new SetTimingResult(values);
            }
        }
    }
    /**
     * Event for when an animation has been cancelled.
     */
    public static final class AnimationCanceledEvent extends CdpObject {
        private AnimationCanceledEvent(Map<String, Object> values) { super(values); }
        @Nullable public static AnimationCanceledEvent fromMap(@Nullable Map<String, Object> values) {
            return values == null ? null : new AnimationCanceledEvent(values);
        }
        public static Builder builder() { return new Builder(); }
        /**
         * Id of the animation that was cancelled.
         * @return the protocol field value
         */
        @Nullable public String id() {
            return (String) value("id");
        }
        public static final class Builder {
            private final Map<String, Object> values = new LinkedHashMap<>();
            /**
             * Id of the animation that was cancelled.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder id(@Nullable String value) {
                if (value == null) values.remove("id");
                else values.put("id", jsonValue(value));
                return this;
            }
            public AnimationCanceledEvent build() {
                if (!values.containsKey("id")) throw new IllegalStateException("Missing required CDP field: id");
                return new AnimationCanceledEvent(values);
            }
        }
    }
    /**
     * Event for each animation that has been created.
     */
    public static final class AnimationCreatedEvent extends CdpObject {
        private AnimationCreatedEvent(Map<String, Object> values) { super(values); }
        @Nullable public static AnimationCreatedEvent fromMap(@Nullable Map<String, Object> values) {
            return values == null ? null : new AnimationCreatedEvent(values);
        }
        public static Builder builder() { return new Builder(); }
        /**
         * Id of the animation that was created.
         * @return the protocol field value
         */
        @Nullable public String id() {
            return (String) value("id");
        }
        public static final class Builder {
            private final Map<String, Object> values = new LinkedHashMap<>();
            /**
             * Id of the animation that was created.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder id(@Nullable String value) {
                if (value == null) values.remove("id");
                else values.put("id", jsonValue(value));
                return this;
            }
            public AnimationCreatedEvent build() {
                if (!values.containsKey("id")) throw new IllegalStateException("Missing required CDP field: id");
                return new AnimationCreatedEvent(values);
            }
        }
    }
    /**
     * Event for animation that has been started.
     */
    public static final class AnimationStartedEvent extends CdpObject {
        private AnimationStartedEvent(Map<String, Object> values) { super(values); }
        @Nullable public static AnimationStartedEvent fromMap(@Nullable Map<String, Object> values) {
            return values == null ? null : new AnimationStartedEvent(values);
        }
        public static Builder builder() { return new Builder(); }
        /**
         * Animation that was started.
         * @return the protocol field value
         */
        @Nullable public Animation.AnimationValue animation() {
            return Animation.AnimationValue.fromMap(objectMap(value("animation")));
        }
        public static final class Builder {
            private final Map<String, Object> values = new LinkedHashMap<>();
            /**
             * Animation that was started.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder animation(@Nullable Animation.AnimationValue value) {
                if (value == null) values.remove("animation");
                else values.put("animation", jsonValue(value));
                return this;
            }
            public AnimationStartedEvent build() {
                if (!values.containsKey("animation")) throw new IllegalStateException("Missing required CDP field: animation");
                return new AnimationStartedEvent(values);
            }
        }
    }
    /**
     * Event for animation that has been updated.
     */
    public static final class AnimationUpdatedEvent extends CdpObject {
        private AnimationUpdatedEvent(Map<String, Object> values) { super(values); }
        @Nullable public static AnimationUpdatedEvent fromMap(@Nullable Map<String, Object> values) {
            return values == null ? null : new AnimationUpdatedEvent(values);
        }
        public static Builder builder() { return new Builder(); }
        /**
         * Animation that was updated.
         * @return the protocol field value
         */
        @Nullable public Animation.AnimationValue animation() {
            return Animation.AnimationValue.fromMap(objectMap(value("animation")));
        }
        public static final class Builder {
            private final Map<String, Object> values = new LinkedHashMap<>();
            /**
             * Animation that was updated.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder animation(@Nullable Animation.AnimationValue value) {
                if (value == null) values.remove("animation");
                else values.put("animation", jsonValue(value));
                return this;
            }
            public AnimationUpdatedEvent build() {
                if (!values.containsKey("animation")) throw new IllegalStateException("Missing required CDP field: animation");
                return new AnimationUpdatedEvent(values);
            }
        }
    }
    public static final class Client {
        private final CdpClient client;
        public Client(CdpClient client) { this.client = client; }
        /**
         * Disables animation domain notifications.
         * @return a stage completing with the command result
         */
        public CompletionStage<DisableResult> disable() {
            return client.call("Animation.disable", null, DisableResult::fromMap);
        }
        /**
         * Enables animation domain notifications.
         * @return a stage completing with the command result
         */
        public CompletionStage<EnableResult> enable() {
            return client.call("Animation.enable", null, EnableResult::fromMap);
        }
        /**
         * Returns the current time of the an animation.
         * @param params command parameters
         * @return a stage completing with the command result
         */
        public CompletionStage<GetCurrentTimeResult> getCurrentTime(GetCurrentTimeParams params) {
            return client.call("Animation.getCurrentTime", params, GetCurrentTimeResult::fromMap);
        }
        /**
         * Gets the playback rate of the document timeline.
         * @return a stage completing with the command result
         */
        public CompletionStage<GetPlaybackRateResult> getPlaybackRate() {
            return client.call("Animation.getPlaybackRate", null, GetPlaybackRateResult::fromMap);
        }
        /**
         * Releases a set of animations to no longer be manipulated.
         * @param params command parameters
         * @return a stage completing with the command result
         */
        public CompletionStage<ReleaseAnimationsResult> releaseAnimations(ReleaseAnimationsParams params) {
            return client.call("Animation.releaseAnimations", params, ReleaseAnimationsResult::fromMap);
        }
        /**
         * Gets the remote object of the Animation.
         * @param params command parameters
         * @return a stage completing with the command result
         */
        public CompletionStage<ResolveAnimationResult> resolveAnimation(ResolveAnimationParams params) {
            return client.call("Animation.resolveAnimation", params, ResolveAnimationResult::fromMap);
        }
        /**
         * Seek a set of animations to a particular time within each animation.
         * @param params command parameters
         * @return a stage completing with the command result
         */
        public CompletionStage<SeekAnimationsResult> seekAnimations(SeekAnimationsParams params) {
            return client.call("Animation.seekAnimations", params, SeekAnimationsResult::fromMap);
        }
        /**
         * Sets the paused state of a set of animations.
         * @param params command parameters
         * @return a stage completing with the command result
         */
        public CompletionStage<SetPausedResult> setPaused(SetPausedParams params) {
            return client.call("Animation.setPaused", params, SetPausedResult::fromMap);
        }
        /**
         * Sets the playback rate of the document timeline.
         * @param params command parameters
         * @return a stage completing with the command result
         */
        public CompletionStage<SetPlaybackRateResult> setPlaybackRate(SetPlaybackRateParams params) {
            return client.call("Animation.setPlaybackRate", params, SetPlaybackRateResult::fromMap);
        }
        /**
         * Sets the timing of an animation node.
         * @param params command parameters
         * @return a stage completing with the command result
         */
        public CompletionStage<SetTimingResult> setTiming(SetTimingParams params) {
            return client.call("Animation.setTiming", params, SetTimingResult::fromMap);
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
