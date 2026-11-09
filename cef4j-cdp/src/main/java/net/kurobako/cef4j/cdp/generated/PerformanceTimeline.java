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
import net.kurobako.cef4j.policy.NullableBoundary;

/**
 * Reporting of performance timeline events, as specified in https://w3c.github.io/performance-timeline/#dom-performanceobserver.
 * <p><b>Experimental:</b> this part of CDP may change without notice.
 * @see <a href="https://chromium.googlesource.com/chromium/src/+/refs/tags/150.0.7871.213/third_party/blink/public/devtools_protocol/domains/PerformanceTimeline.pdl">Pinned protocol source</a>
 */
@Generated("./mvnw generate-sources -pl cef4j-cdp -Dcef.version=150.0.18+gdb11278+chromium-150.0.7871.213")
@SuppressWarnings({"EscapedEntity", "InvalidParam", "JavaLangClash", "MissingSummary", "Unchecked", "UnusedMethod"})
@NullableBoundary("CDP wire objects use null for absent optional fields")
public final class PerformanceTimeline {
    private PerformanceTimeline() {}
    private static final CdpField<DOM.Rect> CURRENT_RECT = CdpField.of("currentRect", value -> java.util.Objects.requireNonNull(DOM.Rect.fromMap(CdpObject.requireObjectMap(value))));
    private static final CdpField<PerformanceTimeline.TimelineEvent> EVENT = CdpField.of("event", value -> java.util.Objects.requireNonNull(PerformanceTimeline.TimelineEvent.fromMap(CdpObject.requireObjectMap(value))));
    private static final CdpField<java.util.List<String>> EVENT_TYPES = CdpField.of("eventTypes", value -> CdpObject.requireList(value, element0 -> (String) element0));
    private static final CdpField<Page.FrameId> FRAME_ID = CdpField.of("frameId", value -> new Page.FrameId((String) value));
    private static final CdpField<Network.TimeSinceEpoch> LAST_INPUT_TIME = CdpField.of("lastInputTime", value -> new Network.TimeSinceEpoch(((Number) value).doubleValue()));
    private static final CdpField<PerformanceTimeline.LayoutShift> LAYOUT_SHIFT_DETAILS = CdpField.of("layoutShiftDetails", value -> java.util.Objects.requireNonNull(PerformanceTimeline.LayoutShift.fromMap(CdpObject.requireObjectMap(value))));
    private static final CdpField<PerformanceTimeline.LargestContentfulPaint> LCP_DETAILS = CdpField.of("lcpDetails", value -> java.util.Objects.requireNonNull(PerformanceTimeline.LargestContentfulPaint.fromMap(CdpObject.requireObjectMap(value))));
    private static final CdpField<Network.TimeSinceEpoch> LOAD_TIME = CdpField.of("loadTime", value -> new Network.TimeSinceEpoch(((Number) value).doubleValue()));
    private static final CdpField<DOM.BackendNodeId> NODE_ID = CdpField.of("nodeId", value -> new DOM.BackendNodeId(((Number) value).longValue()));
    private static final CdpField<DOM.Rect> PREVIOUS_RECT = CdpField.of("previousRect", value -> java.util.Objects.requireNonNull(DOM.Rect.fromMap(CdpObject.requireObjectMap(value))));
    private static final CdpField<Network.TimeSinceEpoch> RENDER_TIME = CdpField.of("renderTime", value -> new Network.TimeSinceEpoch(((Number) value).doubleValue()));
    private static final CdpField<java.util.List<PerformanceTimeline.LayoutShiftAttribution>> SOURCES = CdpField.of("sources", value -> CdpObject.requireList(value, element0 -> java.util.Objects.requireNonNull(PerformanceTimeline.LayoutShiftAttribution.fromMap(CdpObject.requireObjectMap(element0)))));
    private static final CdpField<Network.TimeSinceEpoch> TIME = CdpField.of("time", value -> new Network.TimeSinceEpoch(((Number) value).doubleValue()));
    /**
     * See https://github.com/WICG/LargestContentfulPaint and largest_contentful_paint.idl
     */
    public static final class LargestContentfulPaint extends CdpObject {
        public LargestContentfulPaint() {}
        private LargestContentfulPaint(Map<String, Object> values) { super(values); }
        public static LargestContentfulPaint fromMap(Map<String, Object> values) {
            return new LargestContentfulPaint(values);
        }
        /**
         * Returns the renderTime field.
         * @return the protocol field value
         */
        public Network.TimeSinceEpoch renderTime() {
            return require(RENDER_TIME);
        }
        /**
         * Returns the loadTime field.
         * @return the protocol field value
         */
        public Network.TimeSinceEpoch loadTime() {
            return require(LOAD_TIME);
        }
        /**
         * The number of pixels being painted.
         * @return the protocol field value
         */
        public double size() {
            return requireDouble("size");
        }
        /**
         * The id attribute of the element, if available.
         * @return the protocol field value, empty when absent
         */
        public Optional<String> elementId() {
            return optionalString("elementId");
        }
        /**
         * The URL of the image (may be trimmed).
         * @return the protocol field value, empty when absent
         */
        public Optional<String> url() {
            return optionalString("url");
        }
        /**
         * Returns the nodeId field.
         * @return the protocol field value, empty when absent
         */
        public Optional<DOM.BackendNodeId> nodeId() {
            return optional(NODE_ID);
        }
        /**
         * Sets the renderTime field.
         * @param renderTime field value
         * @return this model
         */
        public LargestContentfulPaint renderTime(Network.TimeSinceEpoch renderTime) {
            set("renderTime", renderTime);
            return this;
        }
        /**
         * Sets the loadTime field.
         * @param loadTime field value
         * @return this model
         */
        public LargestContentfulPaint loadTime(Network.TimeSinceEpoch loadTime) {
            set("loadTime", loadTime);
            return this;
        }
        /**
         * The number of pixels being painted.
         * @param size field value
         * @return this model
         */
        public LargestContentfulPaint size(double size) {
            set("size", size);
            return this;
        }
        /**
         * The id attribute of the element, if available.
         * @param elementId field value
         * @return this model
         */
        public LargestContentfulPaint elementId(String elementId) {
            set("elementId", elementId);
            return this;
        }
        /**
         * The URL of the image (may be trimmed).
         * @param url field value
         * @return this model
         */
        public LargestContentfulPaint url(String url) {
            set("url", url);
            return this;
        }
        /**
         * Sets the nodeId field.
         * @param nodeId field value
         * @return this model
         */
        public LargestContentfulPaint nodeId(DOM.BackendNodeId nodeId) {
            set("nodeId", nodeId);
            return this;
        }
    }
    /**
     */
    public static final class LayoutShiftAttribution extends CdpObject {
        public LayoutShiftAttribution() {}
        private LayoutShiftAttribution(Map<String, Object> values) { super(values); }
        public static LayoutShiftAttribution fromMap(Map<String, Object> values) {
            return new LayoutShiftAttribution(values);
        }
        /**
         * Returns the previousRect field.
         * @return the protocol field value
         */
        public DOM.Rect previousRect() {
            return require(PREVIOUS_RECT);
        }
        /**
         * Returns the currentRect field.
         * @return the protocol field value
         */
        public DOM.Rect currentRect() {
            return require(CURRENT_RECT);
        }
        /**
         * Returns the nodeId field.
         * @return the protocol field value, empty when absent
         */
        public Optional<DOM.BackendNodeId> nodeId() {
            return optional(NODE_ID);
        }
        /**
         * Sets the previousRect field.
         * @param previousRect field value
         * @return this model
         */
        public LayoutShiftAttribution previousRect(DOM.Rect previousRect) {
            set("previousRect", previousRect);
            return this;
        }
        /**
         * Sets the currentRect field.
         * @param currentRect field value
         * @return this model
         */
        public LayoutShiftAttribution currentRect(DOM.Rect currentRect) {
            set("currentRect", currentRect);
            return this;
        }
        /**
         * Sets the nodeId field.
         * @param nodeId field value
         * @return this model
         */
        public LayoutShiftAttribution nodeId(DOM.BackendNodeId nodeId) {
            set("nodeId", nodeId);
            return this;
        }
    }
    /**
     * See https://wicg.github.io/layout-instability/#sec-layout-shift and layout_shift.idl
     */
    public static final class LayoutShift extends CdpObject {
        public LayoutShift() {}
        private LayoutShift(Map<String, Object> values) { super(values); }
        public static LayoutShift fromMap(Map<String, Object> values) {
            return new LayoutShift(values);
        }
        /**
         * Score increment produced by this event.
         * @return the protocol field value
         */
        public double value() {
            return requireDouble("value");
        }
        /**
         * Returns the hadRecentInput field.
         * @return the protocol field value
         */
        public boolean hadRecentInput() {
            return requireBoolean("hadRecentInput");
        }
        /**
         * Returns the lastInputTime field.
         * @return the protocol field value
         */
        public Network.TimeSinceEpoch lastInputTime() {
            return require(LAST_INPUT_TIME);
        }
        /**
         * Returns the sources field.
         * @return the protocol field value
         */
        public java.util.List<PerformanceTimeline.LayoutShiftAttribution> sources() {
            return require(SOURCES);
        }
        /**
         * Score increment produced by this event.
         * @param value field value
         * @return this model
         */
        public LayoutShift value(double value) {
            set("value", value);
            return this;
        }
        /**
         * Sets the hadRecentInput field.
         * @param hadRecentInput field value
         * @return this model
         */
        public LayoutShift hadRecentInput(boolean hadRecentInput) {
            set("hadRecentInput", hadRecentInput);
            return this;
        }
        /**
         * Sets the lastInputTime field.
         * @param lastInputTime field value
         * @return this model
         */
        public LayoutShift lastInputTime(Network.TimeSinceEpoch lastInputTime) {
            set("lastInputTime", lastInputTime);
            return this;
        }
        /**
         * Sets the sources field.
         * @param sources field value
         * @return this model
         */
        public LayoutShift sources(java.util.List<PerformanceTimeline.LayoutShiftAttribution> sources) {
            set("sources", sources);
            return this;
        }
    }
    /**
     */
    public static final class TimelineEvent extends CdpObject {
        public TimelineEvent() {}
        private TimelineEvent(Map<String, Object> values) { super(values); }
        public static TimelineEvent fromMap(Map<String, Object> values) {
            return new TimelineEvent(values);
        }
        /**
         * Identifies the frame that this event is related to. Empty for non-frame targets.
         * @return the protocol field value
         */
        public Page.FrameId frameId() {
            return require(FRAME_ID);
        }
        /**
         * The event type, as specified in https://w3c.github.io/performance-timeline/#dom-performanceentry-entrytype This determines which of the optional &quot;details&quot; fields is present.
         * @return the protocol field value
         */
        public String type() {
            return requireString("type");
        }
        /**
         * Name may be empty depending on the type.
         * @return the protocol field value
         */
        public String name() {
            return requireString("name");
        }
        /**
         * Time in seconds since Epoch, monotonically increasing within document lifetime.
         * @return the protocol field value
         */
        public Network.TimeSinceEpoch time() {
            return require(TIME);
        }
        /**
         * Event duration, if applicable.
         * @return the protocol field value, empty when absent
         */
        public OptionalDouble duration() {
            return optionalDouble("duration");
        }
        /**
         * Returns the lcpDetails field.
         * @return the protocol field value, empty when absent
         */
        public Optional<PerformanceTimeline.LargestContentfulPaint> lcpDetails() {
            return optional(LCP_DETAILS);
        }
        /**
         * Returns the layoutShiftDetails field.
         * @return the protocol field value, empty when absent
         */
        public Optional<PerformanceTimeline.LayoutShift> layoutShiftDetails() {
            return optional(LAYOUT_SHIFT_DETAILS);
        }
        /**
         * Identifies the frame that this event is related to. Empty for non-frame targets.
         * @param frameId field value
         * @return this model
         */
        public TimelineEvent frameId(Page.FrameId frameId) {
            set("frameId", frameId);
            return this;
        }
        /**
         * The event type, as specified in https://w3c.github.io/performance-timeline/#dom-performanceentry-entrytype This determines which of the optional &quot;details&quot; fields is present.
         * @param type field value
         * @return this model
         */
        public TimelineEvent type(String type) {
            set("type", type);
            return this;
        }
        /**
         * Name may be empty depending on the type.
         * @param name field value
         * @return this model
         */
        public TimelineEvent name(String name) {
            set("name", name);
            return this;
        }
        /**
         * Time in seconds since Epoch, monotonically increasing within document lifetime.
         * @param time field value
         * @return this model
         */
        public TimelineEvent time(Network.TimeSinceEpoch time) {
            set("time", time);
            return this;
        }
        /**
         * Event duration, if applicable.
         * @param duration field value
         * @return this model
         */
        public TimelineEvent duration(double duration) {
            set("duration", duration);
            return this;
        }
        /**
         * Sets the lcpDetails field.
         * @param lcpDetails field value
         * @return this model
         */
        public TimelineEvent lcpDetails(PerformanceTimeline.LargestContentfulPaint lcpDetails) {
            set("lcpDetails", lcpDetails);
            return this;
        }
        /**
         * Sets the layoutShiftDetails field.
         * @param layoutShiftDetails field value
         * @return this model
         */
        public TimelineEvent layoutShiftDetails(PerformanceTimeline.LayoutShift layoutShiftDetails) {
            set("layoutShiftDetails", layoutShiftDetails);
            return this;
        }
    }
    /**
     * Previously buffered events would be reported before method returns. See also: timelineEventAdded
     */
    public static final class EnableRequest extends CdpObject {
        public EnableRequest() {}
        public static EnableRequest fromMap(Map<String, Object> values) {
            final EnableRequest instance_ = new EnableRequest();
            if (values != null) instance_.values.putAll(values);
            return instance_;
        }
        /**
         * The types of event to report, as specified in https://w3c.github.io/performance-timeline/#dom-performanceentry-entrytype The specified filter overrides any previous filters, passing empty filter disables recording. Note that not all types exposed to the web platform are currently supported.
         * @return the protocol field value
         */
        public java.util.List<String> eventTypes() {
            return require(EVENT_TYPES);
        }
        /**
         * The types of event to report, as specified in https://w3c.github.io/performance-timeline/#dom-performanceentry-entrytype The specified filter overrides any previous filters, passing empty filter disables recording. Note that not all types exposed to the web platform are currently supported.
         * @param eventTypes field value
         * @return this model
         */
        public EnableRequest eventTypes(java.util.List<String> eventTypes) {
            set("eventTypes", eventTypes);
            return this;
        }
    }
    /**
     * Sent when a performance timeline event is added. See reportPerformanceTimeline method.
     */
    public static final class TimelineEventAddedEvent extends CdpObject {
        public TimelineEventAddedEvent() {}
        private TimelineEventAddedEvent(Map<String, Object> values) { super(values); }
        public static TimelineEventAddedEvent fromMap(Map<String, Object> values) {
            return new TimelineEventAddedEvent(values);
        }
        /**
         * Returns the event field.
         * @return the protocol field value
         */
        public PerformanceTimeline.TimelineEvent event() {
            return require(EVENT);
        }
        /**
         * Sets the event field.
         * @param event field value
         * @return this model
         */
        public TimelineEventAddedEvent event(PerformanceTimeline.TimelineEvent event) {
            set("event", event);
            return this;
        }
    }
    public static final class Client extends CdpDomainClient {
        public Client(CdpClient client) { super(client); }
        /**
         * Previously buffered events would be reported before method returns. See also: timelineEventAdded
         * @param eventTypes protocol value
         * @return a stage completing when the command completes
         */
        public CompletionStage<Void> enable(java.util.List<String> eventTypes) {
            return call("PerformanceTimeline.enable", params("eventTypes", eventTypes));
        }
        /**
         * Previously buffered events would be reported before method returns. See also: timelineEventAdded
         * @param request request parameters
         * @return a stage completing when the command completes
         */
        public CompletionStage<Void> enable(EnableRequest request) {
            return call("PerformanceTimeline.enable", request);
        }
        /**
         * Sent when a performance timeline event is added. See reportPerformanceTimeline method.
         * @param handler event callback
         * @return a removable subscription
         */
        public CdpSubscription onTimelineEventAdded(Consumer<TimelineEventAddedEvent> handler) {
            return on("PerformanceTimeline.timelineEventAdded", TimelineEventAddedEvent::fromMap, handler);
        }
    }
}
