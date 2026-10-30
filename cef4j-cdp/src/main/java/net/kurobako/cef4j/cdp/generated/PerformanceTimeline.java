// GENERATED - do not edit. Regenerate via: ./mvnw generate-sources -pl cef4j-cdp -Dcef.version=150.0.18+gdb11278+chromium-150.0.7871.213
package net.kurobako.cef4j.cdp.generated;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Optional;
import java.util.OptionalDouble;
import java.util.concurrent.CompletionStage;
import java.util.function.Consumer;
import javax.annotation.processing.Generated;
import net.kurobako.cef4j.cdp.CdpClient;
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
            return new Network.TimeSinceEpoch(((Number) require("renderTime")).doubleValue());
        }
        /**
         * Returns the loadTime field.
         * @return the protocol field value
         */
        public Network.TimeSinceEpoch loadTime() {
            return new Network.TimeSinceEpoch(((Number) require("loadTime")).doubleValue());
        }
        /**
         * The number of pixels being painted.
         * @return the protocol field value
         */
        public double size() {
            return ((Number) require("size")).doubleValue();
        }
        /**
         * The id attribute of the element, if available.
         * @return the protocol field value, empty when absent
         */
        public Optional<String> elementId() {
            return raw("elementId").map(value -> (String) value);
        }
        /**
         * The URL of the image (may be trimmed).
         * @return the protocol field value, empty when absent
         */
        public Optional<String> url() {
            return raw("url").map(value -> (String) value);
        }
        /**
         * Returns the nodeId field.
         * @return the protocol field value, empty when absent
         */
        public Optional<DOM.BackendNodeId> nodeId() {
            return raw("nodeId").map(value -> new DOM.BackendNodeId(((Number) value).longValue()));
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
            return java.util.Objects.requireNonNull(DOM.Rect.fromMap(CdpObject.requireObjectMap(require("previousRect"))));
        }
        /**
         * Returns the currentRect field.
         * @return the protocol field value
         */
        public DOM.Rect currentRect() {
            return java.util.Objects.requireNonNull(DOM.Rect.fromMap(CdpObject.requireObjectMap(require("currentRect"))));
        }
        /**
         * Returns the nodeId field.
         * @return the protocol field value, empty when absent
         */
        public Optional<DOM.BackendNodeId> nodeId() {
            return raw("nodeId").map(value -> new DOM.BackendNodeId(((Number) value).longValue()));
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
            return ((Number) require("value")).doubleValue();
        }
        /**
         * Returns the hadRecentInput field.
         * @return the protocol field value
         */
        public boolean hadRecentInput() {
            return (Boolean) require("hadRecentInput");
        }
        /**
         * Returns the lastInputTime field.
         * @return the protocol field value
         */
        public Network.TimeSinceEpoch lastInputTime() {
            return new Network.TimeSinceEpoch(((Number) require("lastInputTime")).doubleValue());
        }
        /**
         * Returns the sources field.
         * @return the protocol field value
         */
        public java.util.List<PerformanceTimeline.LayoutShiftAttribution> sources() {
            return CdpObject.requireList(require("sources"), element0 -> java.util.Objects.requireNonNull(PerformanceTimeline.LayoutShiftAttribution.fromMap(CdpObject.requireObjectMap(element0))));
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
            return new Page.FrameId((String) require("frameId"));
        }
        /**
         * The event type, as specified in https://w3c.github.io/performance-timeline/#dom-performanceentry-entrytype This determines which of the optional &quot;details&quot; fields is present.
         * @return the protocol field value
         */
        public String type() {
            return (String) require("type");
        }
        /**
         * Name may be empty depending on the type.
         * @return the protocol field value
         */
        public String name() {
            return (String) require("name");
        }
        /**
         * Time in seconds since Epoch, monotonically increasing within document lifetime.
         * @return the protocol field value
         */
        public Network.TimeSinceEpoch time() {
            return new Network.TimeSinceEpoch(((Number) require("time")).doubleValue());
        }
        /**
         * Event duration, if applicable.
         * @return the protocol field value, empty when absent
         */
        public OptionalDouble duration() {
            return raw("duration").flatMap(CdpObject::numberAsDouble).stream().mapToDouble(Double::doubleValue).findFirst();
        }
        /**
         * Returns the lcpDetails field.
         * @return the protocol field value, empty when absent
         */
        public Optional<PerformanceTimeline.LargestContentfulPaint> lcpDetails() {
            return raw("lcpDetails").flatMap(CdpObject::objectMap).map(PerformanceTimeline.LargestContentfulPaint::fromMap);
        }
        /**
         * Returns the layoutShiftDetails field.
         * @return the protocol field value, empty when absent
         */
        public Optional<PerformanceTimeline.LayoutShift> layoutShiftDetails() {
            return raw("layoutShiftDetails").flatMap(CdpObject::objectMap).map(PerformanceTimeline.LayoutShift::fromMap);
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
        /**
         * Previously buffered events would be reported before method returns. See also: timelineEventAdded
         * @param eventTypes protocol value
         */
        public EnableRequest(java.util.List<String> eventTypes) {
            eventTypes(eventTypes);
        }
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
            return CdpObject.requireList(require("eventTypes"), element0 -> (String) element0);
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
            return java.util.Objects.requireNonNull(PerformanceTimeline.TimelineEvent.fromMap(CdpObject.requireObjectMap(require("event"))));
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
    public static final class Client {
        private final CdpClient client;
        public Client(CdpClient client) { this.client = client; }
        /**
         * Previously buffered events would be reported before method returns. See also: timelineEventAdded
         * @param eventTypes protocol value
         * @return a stage completing when the command completes
         */
        public CompletionStage<Void> enable(java.util.List<String> eventTypes) {
            final Map<String, Object> params = new LinkedHashMap<>();
            params.put("eventTypes", CdpObject.json(eventTypes));
            return client.call("PerformanceTimeline.enable", params);
        }
        /**
         * Previously buffered events would be reported before method returns. See also: timelineEventAdded
         * @param request request parameters
         * @return a stage completing when the command completes
         */
        public CompletionStage<Void> enable(EnableRequest request) {
            return client.call("PerformanceTimeline.enable", request == null ? null : request.toMap());
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
