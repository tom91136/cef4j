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
 * Reporting of performance timeline events, as specified in https://w3c.github.io/performance-timeline/#dom-performanceobserver.
 * <p><b>Experimental:</b> this part of CDP may change without notice.
 * @see <a href="https://chromium.googlesource.com/chromium/src/+/refs/tags/150.0.7871.213/third_party/blink/public/devtools_protocol/domains/PerformanceTimeline.pdl">Pinned protocol source</a>
 */
@SuppressWarnings({"EscapedEntity", "JavaLangClash", "MissingSummary", "UnusedMethod"})
public final class PerformanceTimeline {
    private PerformanceTimeline() {}
    @Nullable private static Long numberAsLong(@Nullable Object value) { return value == null ? null : ((Number) value).longValue(); }
    @Nullable private static Double numberAsDouble(@Nullable Object value) { return value == null ? null : ((Number) value).doubleValue(); }
    /**
     * See https://github.com/WICG/LargestContentfulPaint and largest_contentful_paint.idl
     */
    public static final class LargestContentfulPaint extends CdpObject {
        private LargestContentfulPaint(Map<String, Object> values) { super(values); }
        @Nullable public static LargestContentfulPaint fromMap(@Nullable Map<String, Object> values) {
            return values == null ? null : new LargestContentfulPaint(values);
        }
        public static Builder builder() { return new Builder(); }
        /**
         * Returns the renderTime field.
         * @return the protocol field value
         */
        @Nullable public Double renderTime() {
            return numberAsDouble(value("renderTime"));
        }
        /**
         * Returns the loadTime field.
         * @return the protocol field value
         */
        @Nullable public Double loadTime() {
            return numberAsDouble(value("loadTime"));
        }
        /**
         * The number of pixels being painted.
         * @return the protocol field value
         */
        @Nullable public Double size() {
            return numberAsDouble(value("size"));
        }
        /**
         * The id attribute of the element, if available.
         * @return the protocol field value
         */
        @Nullable public String elementId() {
            return (String) value("elementId");
        }
        /**
         * The URL of the image (may be trimmed).
         * @return the protocol field value
         */
        @Nullable public String url() {
            return (String) value("url");
        }
        /**
         * Returns the nodeId field.
         * @return the protocol field value
         */
        @Nullable public Long nodeId() {
            return numberAsLong(value("nodeId"));
        }
        public static final class Builder {
            private final Map<String, Object> values = new LinkedHashMap<>();
            /**
             * Sets the renderTime field.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder renderTime(@Nullable Double value) {
                if (value == null) values.remove("renderTime");
                else values.put("renderTime", jsonValue(value));
                return this;
            }
            /**
             * Sets the loadTime field.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder loadTime(@Nullable Double value) {
                if (value == null) values.remove("loadTime");
                else values.put("loadTime", jsonValue(value));
                return this;
            }
            /**
             * The number of pixels being painted.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder size(@Nullable Double value) {
                if (value == null) values.remove("size");
                else values.put("size", jsonValue(value));
                return this;
            }
            /**
             * The id attribute of the element, if available.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder elementId(@Nullable String value) {
                if (value == null) values.remove("elementId");
                else values.put("elementId", jsonValue(value));
                return this;
            }
            /**
             * The URL of the image (may be trimmed).
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder url(@Nullable String value) {
                if (value == null) values.remove("url");
                else values.put("url", jsonValue(value));
                return this;
            }
            /**
             * Sets the nodeId field.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder nodeId(@Nullable Long value) {
                if (value == null) values.remove("nodeId");
                else values.put("nodeId", jsonValue(value));
                return this;
            }
            public LargestContentfulPaint build() {
                if (!values.containsKey("renderTime")) throw new IllegalStateException("Missing required CDP field: renderTime");
                if (!values.containsKey("loadTime")) throw new IllegalStateException("Missing required CDP field: loadTime");
                if (!values.containsKey("size")) throw new IllegalStateException("Missing required CDP field: size");
                return new LargestContentfulPaint(values);
            }
        }
    }
    /**
     */
    public static final class LayoutShiftAttribution extends CdpObject {
        private LayoutShiftAttribution(Map<String, Object> values) { super(values); }
        @Nullable public static LayoutShiftAttribution fromMap(@Nullable Map<String, Object> values) {
            return values == null ? null : new LayoutShiftAttribution(values);
        }
        public static Builder builder() { return new Builder(); }
        /**
         * Returns the previousRect field.
         * @return the protocol field value
         */
        @Nullable public DOM.Rect previousRect() {
            return DOM.Rect.fromMap(objectMap(value("previousRect")));
        }
        /**
         * Returns the currentRect field.
         * @return the protocol field value
         */
        @Nullable public DOM.Rect currentRect() {
            return DOM.Rect.fromMap(objectMap(value("currentRect")));
        }
        /**
         * Returns the nodeId field.
         * @return the protocol field value
         */
        @Nullable public Long nodeId() {
            return numberAsLong(value("nodeId"));
        }
        public static final class Builder {
            private final Map<String, Object> values = new LinkedHashMap<>();
            /**
             * Sets the previousRect field.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder previousRect(@Nullable DOM.Rect value) {
                if (value == null) values.remove("previousRect");
                else values.put("previousRect", jsonValue(value));
                return this;
            }
            /**
             * Sets the currentRect field.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder currentRect(@Nullable DOM.Rect value) {
                if (value == null) values.remove("currentRect");
                else values.put("currentRect", jsonValue(value));
                return this;
            }
            /**
             * Sets the nodeId field.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder nodeId(@Nullable Long value) {
                if (value == null) values.remove("nodeId");
                else values.put("nodeId", jsonValue(value));
                return this;
            }
            public LayoutShiftAttribution build() {
                if (!values.containsKey("previousRect")) throw new IllegalStateException("Missing required CDP field: previousRect");
                if (!values.containsKey("currentRect")) throw new IllegalStateException("Missing required CDP field: currentRect");
                return new LayoutShiftAttribution(values);
            }
        }
    }
    /**
     * See https://wicg.github.io/layout-instability/#sec-layout-shift and layout_shift.idl
     */
    public static final class LayoutShift extends CdpObject {
        private LayoutShift(Map<String, Object> values) { super(values); }
        @Nullable public static LayoutShift fromMap(@Nullable Map<String, Object> values) {
            return values == null ? null : new LayoutShift(values);
        }
        public static Builder builder() { return new Builder(); }
        /**
         * Score increment produced by this event.
         * @return the protocol field value
         */
        @Nullable public Double value() {
            return numberAsDouble(value("value"));
        }
        /**
         * Returns the hadRecentInput field.
         * @return the protocol field value
         */
        @Nullable public Boolean hadRecentInput() {
            return (Boolean) value("hadRecentInput");
        }
        /**
         * Returns the lastInputTime field.
         * @return the protocol field value
         */
        @Nullable public Double lastInputTime() {
            return numberAsDouble(value("lastInputTime"));
        }
        /**
         * Returns the sources field.
         * @return the protocol field value
         */
        @Nullable public java.util.List<PerformanceTimeline.LayoutShiftAttribution> sources() {
            return list(value("sources"), element0 -> PerformanceTimeline.LayoutShiftAttribution.fromMap(objectMap(element0)));
        }
        public static final class Builder {
            private final Map<String, Object> values = new LinkedHashMap<>();
            /**
             * Score increment produced by this event.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder value(@Nullable Double value) {
                if (value == null) values.remove("value");
                else values.put("value", jsonValue(value));
                return this;
            }
            /**
             * Sets the hadRecentInput field.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder hadRecentInput(@Nullable Boolean value) {
                if (value == null) values.remove("hadRecentInput");
                else values.put("hadRecentInput", jsonValue(value));
                return this;
            }
            /**
             * Sets the lastInputTime field.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder lastInputTime(@Nullable Double value) {
                if (value == null) values.remove("lastInputTime");
                else values.put("lastInputTime", jsonValue(value));
                return this;
            }
            /**
             * Sets the sources field.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder sources(@Nullable java.util.List<PerformanceTimeline.LayoutShiftAttribution> value) {
                if (value == null) values.remove("sources");
                else values.put("sources", jsonValue(value));
                return this;
            }
            public LayoutShift build() {
                if (!values.containsKey("value")) throw new IllegalStateException("Missing required CDP field: value");
                if (!values.containsKey("hadRecentInput")) throw new IllegalStateException("Missing required CDP field: hadRecentInput");
                if (!values.containsKey("lastInputTime")) throw new IllegalStateException("Missing required CDP field: lastInputTime");
                if (!values.containsKey("sources")) throw new IllegalStateException("Missing required CDP field: sources");
                return new LayoutShift(values);
            }
        }
    }
    /**
     */
    public static final class TimelineEvent extends CdpObject {
        private TimelineEvent(Map<String, Object> values) { super(values); }
        @Nullable public static TimelineEvent fromMap(@Nullable Map<String, Object> values) {
            return values == null ? null : new TimelineEvent(values);
        }
        public static Builder builder() { return new Builder(); }
        /**
         * Identifies the frame that this event is related to. Empty for non-frame targets.
         * @return the protocol field value
         */
        @Nullable public String frameId() {
            return (String) value("frameId");
        }
        /**
         * The event type, as specified in https://w3c.github.io/performance-timeline/#dom-performanceentry-entrytype This determines which of the optional &quot;details&quot; fields is present.
         * @return the protocol field value
         */
        @Nullable public String type() {
            return (String) value("type");
        }
        /**
         * Name may be empty depending on the type.
         * @return the protocol field value
         */
        @Nullable public String name() {
            return (String) value("name");
        }
        /**
         * Time in seconds since Epoch, monotonically increasing within document lifetime.
         * @return the protocol field value
         */
        @Nullable public Double time() {
            return numberAsDouble(value("time"));
        }
        /**
         * Event duration, if applicable.
         * @return the protocol field value
         */
        @Nullable public Double duration() {
            return numberAsDouble(value("duration"));
        }
        /**
         * Returns the lcpDetails field.
         * @return the protocol field value
         */
        @Nullable public PerformanceTimeline.LargestContentfulPaint lcpDetails() {
            return PerformanceTimeline.LargestContentfulPaint.fromMap(objectMap(value("lcpDetails")));
        }
        /**
         * Returns the layoutShiftDetails field.
         * @return the protocol field value
         */
        @Nullable public PerformanceTimeline.LayoutShift layoutShiftDetails() {
            return PerformanceTimeline.LayoutShift.fromMap(objectMap(value("layoutShiftDetails")));
        }
        public static final class Builder {
            private final Map<String, Object> values = new LinkedHashMap<>();
            /**
             * Identifies the frame that this event is related to. Empty for non-frame targets.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder frameId(@Nullable String value) {
                if (value == null) values.remove("frameId");
                else values.put("frameId", jsonValue(value));
                return this;
            }
            /**
             * The event type, as specified in https://w3c.github.io/performance-timeline/#dom-performanceentry-entrytype This determines which of the optional &quot;details&quot; fields is present.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder type(@Nullable String value) {
                if (value == null) values.remove("type");
                else values.put("type", jsonValue(value));
                return this;
            }
            /**
             * Name may be empty depending on the type.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder name(@Nullable String value) {
                if (value == null) values.remove("name");
                else values.put("name", jsonValue(value));
                return this;
            }
            /**
             * Time in seconds since Epoch, monotonically increasing within document lifetime.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder time(@Nullable Double value) {
                if (value == null) values.remove("time");
                else values.put("time", jsonValue(value));
                return this;
            }
            /**
             * Event duration, if applicable.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder duration(@Nullable Double value) {
                if (value == null) values.remove("duration");
                else values.put("duration", jsonValue(value));
                return this;
            }
            /**
             * Sets the lcpDetails field.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder lcpDetails(@Nullable PerformanceTimeline.LargestContentfulPaint value) {
                if (value == null) values.remove("lcpDetails");
                else values.put("lcpDetails", jsonValue(value));
                return this;
            }
            /**
             * Sets the layoutShiftDetails field.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder layoutShiftDetails(@Nullable PerformanceTimeline.LayoutShift value) {
                if (value == null) values.remove("layoutShiftDetails");
                else values.put("layoutShiftDetails", jsonValue(value));
                return this;
            }
            public TimelineEvent build() {
                if (!values.containsKey("frameId")) throw new IllegalStateException("Missing required CDP field: frameId");
                if (!values.containsKey("type")) throw new IllegalStateException("Missing required CDP field: type");
                if (!values.containsKey("name")) throw new IllegalStateException("Missing required CDP field: name");
                if (!values.containsKey("time")) throw new IllegalStateException("Missing required CDP field: time");
                return new TimelineEvent(values);
            }
        }
    }
    /**
     * Previously buffered events would be reported before method returns. See also: timelineEventAdded
     */
    public static final class EnableParams extends CdpObject {
        private EnableParams(Map<String, Object> values) { super(values); }
        @Nullable public static EnableParams fromMap(@Nullable Map<String, Object> values) {
            return values == null ? null : new EnableParams(values);
        }
        public static Builder builder() { return new Builder(); }
        /**
         * The types of event to report, as specified in https://w3c.github.io/performance-timeline/#dom-performanceentry-entrytype The specified filter overrides any previous filters, passing empty filter disables recording. Note that not all types exposed to the web platform are currently supported.
         * @return the protocol field value
         */
        @Nullable public java.util.List<String> eventTypes() {
            return list(value("eventTypes"), element0 -> (String) element0);
        }
        public static final class Builder {
            private final Map<String, Object> values = new LinkedHashMap<>();
            /**
             * The types of event to report, as specified in https://w3c.github.io/performance-timeline/#dom-performanceentry-entrytype The specified filter overrides any previous filters, passing empty filter disables recording. Note that not all types exposed to the web platform are currently supported.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder eventTypes(@Nullable java.util.List<String> value) {
                if (value == null) values.remove("eventTypes");
                else values.put("eventTypes", jsonValue(value));
                return this;
            }
            public EnableParams build() {
                if (!values.containsKey("eventTypes")) throw new IllegalStateException("Missing required CDP field: eventTypes");
                return new EnableParams(values);
            }
        }
    }
    /**
     * Previously buffered events would be reported before method returns. See also: timelineEventAdded
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
     * Sent when a performance timeline event is added. See reportPerformanceTimeline method.
     */
    public static final class TimelineEventAddedEvent extends CdpObject {
        private TimelineEventAddedEvent(Map<String, Object> values) { super(values); }
        @Nullable public static TimelineEventAddedEvent fromMap(@Nullable Map<String, Object> values) {
            return values == null ? null : new TimelineEventAddedEvent(values);
        }
        public static Builder builder() { return new Builder(); }
        /**
         * Returns the event field.
         * @return the protocol field value
         */
        @Nullable public PerformanceTimeline.TimelineEvent event() {
            return PerformanceTimeline.TimelineEvent.fromMap(objectMap(value("event")));
        }
        public static final class Builder {
            private final Map<String, Object> values = new LinkedHashMap<>();
            /**
             * Sets the event field.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder event(@Nullable PerformanceTimeline.TimelineEvent value) {
                if (value == null) values.remove("event");
                else values.put("event", jsonValue(value));
                return this;
            }
            public TimelineEventAddedEvent build() {
                if (!values.containsKey("event")) throw new IllegalStateException("Missing required CDP field: event");
                return new TimelineEventAddedEvent(values);
            }
        }
    }
    public static final class Client {
        private final CdpClient client;
        public Client(CdpClient client) { this.client = client; }
        /**
         * Previously buffered events would be reported before method returns. See also: timelineEventAdded
         * @param params command parameters
         * @return a stage completing with the command result
         */
        public CompletionStage<EnableResult> enable(EnableParams params) {
            return client.call("PerformanceTimeline.enable", params, EnableResult::fromMap);
        }
        /**
         * Sent when a performance timeline event is added. See reportPerformanceTimeline method.
         * @param handler event callback
         * @return a removable subscription
         */
        public CdpSubscription onTimelineEventAdded(Consumer<TimelineEventAddedEvent> handler) {
            return client.on("PerformanceTimeline.timelineEventAdded", TimelineEventAddedEvent::fromMap, handler);
        }
    }
}
