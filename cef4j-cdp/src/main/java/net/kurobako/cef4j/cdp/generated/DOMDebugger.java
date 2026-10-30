// GENERATED - do not edit. Regenerate via: ./mvnw generate-sources -pl cef4j-cdp -Dcef.version=150.0.18+gdb11278+chromium-150.0.7871.213
package net.kurobako.cef4j.cdp.generated;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Optional;
import java.util.OptionalLong;
import java.util.concurrent.CompletionStage;
import javax.annotation.processing.Generated;
import net.kurobako.cef4j.cdp.CdpClient;
import net.kurobako.cef4j.cdp.CdpObject;
import net.kurobako.cef4j.cdp.CdpValue;
import net.kurobako.cef4j.policy.NullableBoundary;
import org.jspecify.annotations.Nullable;

/**
 * DOM debugging allows setting breakpoints on particular DOM operations and events. JavaScript execution will stop on these operations as if there was a regular breakpoint set.
 * @see <a href="https://chromium.googlesource.com/chromium/src/+/refs/tags/150.0.7871.213/third_party/blink/public/devtools_protocol/domains/DOMDebugger.pdl">Pinned protocol source</a>
 */
@Generated("./mvnw generate-sources -pl cef4j-cdp -Dcef.version=150.0.18+gdb11278+chromium-150.0.7871.213")
@SuppressWarnings({"EscapedEntity", "InvalidParam", "JavaLangClash", "MissingSummary", "Unchecked", "UnusedMethod"})
@NullableBoundary("CDP wire objects use null for absent optional fields")
public final class DOMDebugger {
    private DOMDebugger() {}
    /**
     * DOM breakpoint type.
     */
    public enum DOMBreakpointType implements CdpValue<String> {
        SUBTREE_MODIFIED("subtree-modified"),
        ATTRIBUTE_MODIFIED("attribute-modified"),
        NODE_REMOVED("node-removed");
        public final String value;
        DOMBreakpointType(String value) { this.value = value; }
        @Override public String value() { return value; }
        public static DOMBreakpointType of(String value) {
            for (DOMBreakpointType constant : values()) {
                if (constant.value.equals(value)) return constant;
            }
            throw new IllegalArgumentException("Unknown DOMBreakpointType value: " + value);
        }
    }
    /**
     * CSP Violation type.
     * <p><b>Experimental:</b> this part of CDP may change without notice.
     */
    public enum CSPViolationType implements CdpValue<String> {
        TRUSTEDTYPE_SINK_VIOLATION("trustedtype-sink-violation"),
        TRUSTEDTYPE_POLICY_VIOLATION("trustedtype-policy-violation");
        public final String value;
        CSPViolationType(String value) { this.value = value; }
        @Override public String value() { return value; }
        public static CSPViolationType of(String value) {
            for (CSPViolationType constant : values()) {
                if (constant.value.equals(value)) return constant;
            }
            throw new IllegalArgumentException("Unknown CSPViolationType value: " + value);
        }
    }
    /**
     * Object event listener.
     */
    public static final class EventListener extends CdpObject {
        public EventListener() {}
        private EventListener(Map<String, Object> values) { super(values); }
        public static EventListener fromMap(Map<String, Object> values) {
            return new EventListener(values);
        }
        /**
         * {@code EventListener}&#x27;s type.
         * @return the protocol field value
         */
        public String type() {
            return (String) require("type");
        }
        /**
         * {@code EventListener}&#x27;s useCapture.
         * @return the protocol field value
         */
        public boolean useCapture() {
            return (Boolean) require("useCapture");
        }
        /**
         * {@code EventListener}&#x27;s passive flag.
         * @return the protocol field value
         */
        public boolean passive() {
            return (Boolean) require("passive");
        }
        /**
         * {@code EventListener}&#x27;s once flag.
         * @return the protocol field value
         */
        public boolean once() {
            return (Boolean) require("once");
        }
        /**
         * Script id of the handler code.
         * @return the protocol field value
         */
        public Runtime.ScriptId scriptId() {
            return new Runtime.ScriptId((String) require("scriptId"));
        }
        /**
         * Line number in the script (0-based).
         * @return the protocol field value
         */
        public long lineNumber() {
            return ((Number) require("lineNumber")).longValue();
        }
        /**
         * Column number in the script (0-based).
         * @return the protocol field value
         */
        public long columnNumber() {
            return ((Number) require("columnNumber")).longValue();
        }
        /**
         * Event handler function value.
         * @return the protocol field value, empty when absent
         */
        public Optional<Runtime.RemoteObject> handler() {
            return raw("handler").flatMap(CdpObject::objectMap).map(Runtime.RemoteObject::fromMap);
        }
        /**
         * Event original handler function value.
         * @return the protocol field value, empty when absent
         */
        public Optional<Runtime.RemoteObject> originalHandler() {
            return raw("originalHandler").flatMap(CdpObject::objectMap).map(Runtime.RemoteObject::fromMap);
        }
        /**
         * Node the listener is added to (if any).
         * @return the protocol field value, empty when absent
         */
        public Optional<DOM.BackendNodeId> backendNodeId() {
            return raw("backendNodeId").map(value -> new DOM.BackendNodeId(((Number) value).longValue()));
        }
        /**
         * {@code EventListener}&#x27;s type.
         * @param type field value
         * @return this model
         */
        public EventListener type(String type) {
            set("type", type);
            return this;
        }
        /**
         * {@code EventListener}&#x27;s useCapture.
         * @param useCapture field value
         * @return this model
         */
        public EventListener useCapture(boolean useCapture) {
            set("useCapture", useCapture);
            return this;
        }
        /**
         * {@code EventListener}&#x27;s passive flag.
         * @param passive field value
         * @return this model
         */
        public EventListener passive(boolean passive) {
            set("passive", passive);
            return this;
        }
        /**
         * {@code EventListener}&#x27;s once flag.
         * @param once field value
         * @return this model
         */
        public EventListener once(boolean once) {
            set("once", once);
            return this;
        }
        /**
         * Script id of the handler code.
         * @param scriptId field value
         * @return this model
         */
        public EventListener scriptId(Runtime.ScriptId scriptId) {
            set("scriptId", scriptId);
            return this;
        }
        /**
         * Line number in the script (0-based).
         * @param lineNumber field value
         * @return this model
         */
        public EventListener lineNumber(long lineNumber) {
            set("lineNumber", lineNumber);
            return this;
        }
        /**
         * Column number in the script (0-based).
         * @param columnNumber field value
         * @return this model
         */
        public EventListener columnNumber(long columnNumber) {
            set("columnNumber", columnNumber);
            return this;
        }
        /**
         * Event handler function value.
         * @param handler field value
         * @return this model
         */
        public EventListener handler(Runtime.RemoteObject handler) {
            set("handler", handler);
            return this;
        }
        /**
         * Event original handler function value.
         * @param originalHandler field value
         * @return this model
         */
        public EventListener originalHandler(Runtime.RemoteObject originalHandler) {
            set("originalHandler", originalHandler);
            return this;
        }
        /**
         * Node the listener is added to (if any).
         * @param backendNodeId field value
         * @return this model
         */
        public EventListener backendNodeId(DOM.BackendNodeId backendNodeId) {
            set("backendNodeId", backendNodeId);
            return this;
        }
    }
    /**
     * Returns event listeners of the given object.
     */
    public static final class GetEventListenersRequest extends CdpObject {
        public GetEventListenersRequest() {}
        /**
         * Returns event listeners of the given object.
         * @param objectId protocol value
         */
        public GetEventListenersRequest(Runtime.RemoteObjectId objectId) {
            objectId(objectId);
        }
        public static GetEventListenersRequest fromMap(Map<String, Object> values) {
            final GetEventListenersRequest instance_ = new GetEventListenersRequest();
            if (values != null) instance_.values.putAll(values);
            return instance_;
        }
        /**
         * Identifier of the object to return listeners for.
         * @return the protocol field value
         */
        public Runtime.RemoteObjectId objectId() {
            return new Runtime.RemoteObjectId((String) require("objectId"));
        }
        /**
         * The maximum depth at which Node children should be retrieved, defaults to 1. Use -1 for the entire subtree or provide an integer larger than 0.
         * @return the protocol field value, empty when absent
         */
        public OptionalLong depth() {
            return raw("depth").flatMap(CdpObject::numberAsLong).stream().mapToLong(Long::longValue).findFirst();
        }
        /**
         * Whether or not iframes and shadow roots should be traversed when returning the subtree (default is false). Reports listeners for all contexts if pierce is enabled.
         * @return the protocol field value, empty when absent
         */
        public Optional<Boolean> pierce() {
            return raw("pierce").map(value -> (Boolean) value);
        }
        /**
         * Identifier of the object to return listeners for.
         * @param objectId field value
         * @return this model
         */
        public GetEventListenersRequest objectId(Runtime.RemoteObjectId objectId) {
            set("objectId", objectId);
            return this;
        }
        /**
         * The maximum depth at which Node children should be retrieved, defaults to 1. Use -1 for the entire subtree or provide an integer larger than 0.
         * @param depth field value
         * @return this model
         */
        public GetEventListenersRequest depth(long depth) {
            set("depth", depth);
            return this;
        }
        /**
         * Whether or not iframes and shadow roots should be traversed when returning the subtree (default is false). Reports listeners for all contexts if pierce is enabled.
         * @param pierce field value
         * @return this model
         */
        public GetEventListenersRequest pierce(boolean pierce) {
            set("pierce", pierce);
            return this;
        }
    }
    /**
     * Removes DOM breakpoint that was set using {@code setDOMBreakpoint}.
     */
    public static final class RemoveDOMBreakpointRequest extends CdpObject {
        public RemoveDOMBreakpointRequest() {}
        /**
         * Removes DOM breakpoint that was set using {@code setDOMBreakpoint}.
         * @param nodeId protocol value
         * @param type protocol value
         */
        public RemoveDOMBreakpointRequest(DOM.NodeId nodeId, DOMDebugger.DOMBreakpointType type) {
            nodeId(nodeId).type(type);
        }
        public static RemoveDOMBreakpointRequest fromMap(Map<String, Object> values) {
            final RemoveDOMBreakpointRequest instance_ = new RemoveDOMBreakpointRequest();
            if (values != null) instance_.values.putAll(values);
            return instance_;
        }
        /**
         * Identifier of the node to remove breakpoint from.
         * @return the protocol field value
         */
        public DOM.NodeId nodeId() {
            return new DOM.NodeId(((Number) require("nodeId")).longValue());
        }
        /**
         * Type of the breakpoint to remove.
         * @return the protocol field value
         */
        public DOMDebugger.DOMBreakpointType type() {
            return DOMDebugger.DOMBreakpointType.of((String) require("type"));
        }
        /**
         * Identifier of the node to remove breakpoint from.
         * @param nodeId field value
         * @return this model
         */
        public RemoveDOMBreakpointRequest nodeId(DOM.NodeId nodeId) {
            set("nodeId", nodeId);
            return this;
        }
        /**
         * Type of the breakpoint to remove.
         * @param type field value
         * @return this model
         */
        public RemoveDOMBreakpointRequest type(DOMDebugger.DOMBreakpointType type) {
            set("type", type);
            return this;
        }
    }
    /**
     * Removes breakpoint on particular DOM event.
     */
    public static final class RemoveEventListenerBreakpointRequest extends CdpObject {
        public RemoveEventListenerBreakpointRequest() {}
        /**
         * Removes breakpoint on particular DOM event.
         * @param eventName protocol value
         */
        public RemoveEventListenerBreakpointRequest(String eventName) {
            eventName(eventName);
        }
        public static RemoveEventListenerBreakpointRequest fromMap(Map<String, Object> values) {
            final RemoveEventListenerBreakpointRequest instance_ = new RemoveEventListenerBreakpointRequest();
            if (values != null) instance_.values.putAll(values);
            return instance_;
        }
        /**
         * Event name.
         * @return the protocol field value
         */
        public String eventName() {
            return (String) require("eventName");
        }
        /**
         * EventTarget interface name.
         * <p><b>Experimental:</b> this part of CDP may change without notice.
         * @return the protocol field value, empty when absent
         */
        public Optional<String> targetName() {
            return raw("targetName").map(value -> (String) value);
        }
        /**
         * Event name.
         * @param eventName field value
         * @return this model
         */
        public RemoveEventListenerBreakpointRequest eventName(String eventName) {
            set("eventName", eventName);
            return this;
        }
        /**
         * EventTarget interface name.
         * <p><b>Experimental:</b> this part of CDP may change without notice.
         * @param targetName field value
         * @return this model
         */
        public RemoveEventListenerBreakpointRequest targetName(String targetName) {
            set("targetName", targetName);
            return this;
        }
    }
    /**
     * Removes breakpoint on particular native event.
     * <p><b>Experimental:</b> this part of CDP may change without notice.
     * @deprecated Deprecated by the Chromium DevTools Protocol.
     */
    @Deprecated
    public static final class RemoveInstrumentationBreakpointRequest extends CdpObject {
        public RemoveInstrumentationBreakpointRequest() {}
        /**
         * Removes breakpoint on particular native event.
         * <p><b>Experimental:</b> this part of CDP may change without notice.
         * @param eventName protocol value
         * @deprecated Deprecated by the Chromium DevTools Protocol.
         */
        @Deprecated
        public RemoveInstrumentationBreakpointRequest(String eventName) {
            eventName(eventName);
        }
        public static RemoveInstrumentationBreakpointRequest fromMap(Map<String, Object> values) {
            final RemoveInstrumentationBreakpointRequest instance_ = new RemoveInstrumentationBreakpointRequest();
            if (values != null) instance_.values.putAll(values);
            return instance_;
        }
        /**
         * Instrumentation name to stop on.
         * @return the protocol field value
         */
        public String eventName() {
            return (String) require("eventName");
        }
        /**
         * Instrumentation name to stop on.
         * @param eventName field value
         * @return this model
         */
        public RemoveInstrumentationBreakpointRequest eventName(String eventName) {
            set("eventName", eventName);
            return this;
        }
    }
    /**
     * Removes breakpoint from XMLHttpRequest.
     */
    public static final class RemoveXHRBreakpointRequest extends CdpObject {
        public RemoveXHRBreakpointRequest() {}
        /**
         * Removes breakpoint from XMLHttpRequest.
         * @param url protocol value
         */
        public RemoveXHRBreakpointRequest(String url) {
            url(url);
        }
        public static RemoveXHRBreakpointRequest fromMap(Map<String, Object> values) {
            final RemoveXHRBreakpointRequest instance_ = new RemoveXHRBreakpointRequest();
            if (values != null) instance_.values.putAll(values);
            return instance_;
        }
        /**
         * Resource URL substring.
         * @return the protocol field value
         */
        public String url() {
            return (String) require("url");
        }
        /**
         * Resource URL substring.
         * @param url field value
         * @return this model
         */
        public RemoveXHRBreakpointRequest url(String url) {
            set("url", url);
            return this;
        }
    }
    /**
     * Sets breakpoint on particular CSP violations.
     * <p><b>Experimental:</b> this part of CDP may change without notice.
     */
    public static final class SetBreakOnCSPViolationRequest extends CdpObject {
        public SetBreakOnCSPViolationRequest() {}
        /**
         * Sets breakpoint on particular CSP violations.
         * <p><b>Experimental:</b> this part of CDP may change without notice.
         * @param violationTypes protocol value
         */
        public SetBreakOnCSPViolationRequest(java.util.List<DOMDebugger.CSPViolationType> violationTypes) {
            violationTypes(violationTypes);
        }
        public static SetBreakOnCSPViolationRequest fromMap(Map<String, Object> values) {
            final SetBreakOnCSPViolationRequest instance_ = new SetBreakOnCSPViolationRequest();
            if (values != null) instance_.values.putAll(values);
            return instance_;
        }
        /**
         * CSP Violations to stop upon.
         * @return the protocol field value
         */
        public java.util.List<DOMDebugger.CSPViolationType> violationTypes() {
            return CdpObject.requireList(require("violationTypes"), element0 -> DOMDebugger.CSPViolationType.of((String) element0));
        }
        /**
         * CSP Violations to stop upon.
         * @param violationTypes field value
         * @return this model
         */
        public SetBreakOnCSPViolationRequest violationTypes(java.util.List<DOMDebugger.CSPViolationType> violationTypes) {
            set("violationTypes", violationTypes);
            return this;
        }
    }
    /**
     * Sets breakpoint on particular operation with DOM.
     */
    public static final class SetDOMBreakpointRequest extends CdpObject {
        public SetDOMBreakpointRequest() {}
        /**
         * Sets breakpoint on particular operation with DOM.
         * @param nodeId protocol value
         * @param type protocol value
         */
        public SetDOMBreakpointRequest(DOM.NodeId nodeId, DOMDebugger.DOMBreakpointType type) {
            nodeId(nodeId).type(type);
        }
        public static SetDOMBreakpointRequest fromMap(Map<String, Object> values) {
            final SetDOMBreakpointRequest instance_ = new SetDOMBreakpointRequest();
            if (values != null) instance_.values.putAll(values);
            return instance_;
        }
        /**
         * Identifier of the node to set breakpoint on.
         * @return the protocol field value
         */
        public DOM.NodeId nodeId() {
            return new DOM.NodeId(((Number) require("nodeId")).longValue());
        }
        /**
         * Type of the operation to stop upon.
         * @return the protocol field value
         */
        public DOMDebugger.DOMBreakpointType type() {
            return DOMDebugger.DOMBreakpointType.of((String) require("type"));
        }
        /**
         * Identifier of the node to set breakpoint on.
         * @param nodeId field value
         * @return this model
         */
        public SetDOMBreakpointRequest nodeId(DOM.NodeId nodeId) {
            set("nodeId", nodeId);
            return this;
        }
        /**
         * Type of the operation to stop upon.
         * @param type field value
         * @return this model
         */
        public SetDOMBreakpointRequest type(DOMDebugger.DOMBreakpointType type) {
            set("type", type);
            return this;
        }
    }
    /**
     * Sets breakpoint on particular DOM event.
     */
    public static final class SetEventListenerBreakpointRequest extends CdpObject {
        public SetEventListenerBreakpointRequest() {}
        /**
         * Sets breakpoint on particular DOM event.
         * @param eventName protocol value
         */
        public SetEventListenerBreakpointRequest(String eventName) {
            eventName(eventName);
        }
        public static SetEventListenerBreakpointRequest fromMap(Map<String, Object> values) {
            final SetEventListenerBreakpointRequest instance_ = new SetEventListenerBreakpointRequest();
            if (values != null) instance_.values.putAll(values);
            return instance_;
        }
        /**
         * DOM Event name to stop on (any DOM event will do).
         * @return the protocol field value
         */
        public String eventName() {
            return (String) require("eventName");
        }
        /**
         * EventTarget interface name to stop on. If equal to {@code &quot;*&quot;} or not provided, will stop on any EventTarget.
         * <p><b>Experimental:</b> this part of CDP may change without notice.
         * @return the protocol field value, empty when absent
         */
        public Optional<String> targetName() {
            return raw("targetName").map(value -> (String) value);
        }
        /**
         * DOM Event name to stop on (any DOM event will do).
         * @param eventName field value
         * @return this model
         */
        public SetEventListenerBreakpointRequest eventName(String eventName) {
            set("eventName", eventName);
            return this;
        }
        /**
         * EventTarget interface name to stop on. If equal to {@code &quot;*&quot;} or not provided, will stop on any EventTarget.
         * <p><b>Experimental:</b> this part of CDP may change without notice.
         * @param targetName field value
         * @return this model
         */
        public SetEventListenerBreakpointRequest targetName(String targetName) {
            set("targetName", targetName);
            return this;
        }
    }
    /**
     * Sets breakpoint on particular native event.
     * <p><b>Experimental:</b> this part of CDP may change without notice.
     * @deprecated Deprecated by the Chromium DevTools Protocol.
     */
    @Deprecated
    public static final class SetInstrumentationBreakpointRequest extends CdpObject {
        public SetInstrumentationBreakpointRequest() {}
        /**
         * Sets breakpoint on particular native event.
         * <p><b>Experimental:</b> this part of CDP may change without notice.
         * @param eventName protocol value
         * @deprecated Deprecated by the Chromium DevTools Protocol.
         */
        @Deprecated
        public SetInstrumentationBreakpointRequest(String eventName) {
            eventName(eventName);
        }
        public static SetInstrumentationBreakpointRequest fromMap(Map<String, Object> values) {
            final SetInstrumentationBreakpointRequest instance_ = new SetInstrumentationBreakpointRequest();
            if (values != null) instance_.values.putAll(values);
            return instance_;
        }
        /**
         * Instrumentation name to stop on.
         * @return the protocol field value
         */
        public String eventName() {
            return (String) require("eventName");
        }
        /**
         * Instrumentation name to stop on.
         * @param eventName field value
         * @return this model
         */
        public SetInstrumentationBreakpointRequest eventName(String eventName) {
            set("eventName", eventName);
            return this;
        }
    }
    /**
     * Sets breakpoint on XMLHttpRequest.
     */
    public static final class SetXHRBreakpointRequest extends CdpObject {
        public SetXHRBreakpointRequest() {}
        /**
         * Sets breakpoint on XMLHttpRequest.
         * @param url protocol value
         */
        public SetXHRBreakpointRequest(String url) {
            url(url);
        }
        public static SetXHRBreakpointRequest fromMap(Map<String, Object> values) {
            final SetXHRBreakpointRequest instance_ = new SetXHRBreakpointRequest();
            if (values != null) instance_.values.putAll(values);
            return instance_;
        }
        /**
         * Resource URL substring. All XHRs having this substring in the URL will get stopped upon.
         * @return the protocol field value
         */
        public String url() {
            return (String) require("url");
        }
        /**
         * Resource URL substring. All XHRs having this substring in the URL will get stopped upon.
         * @param url field value
         * @return this model
         */
        public SetXHRBreakpointRequest url(String url) {
            set("url", url);
            return this;
        }
    }
    public static final class Client {
        private final CdpClient client;
        public Client(CdpClient client) { this.client = client; }
        /**
         * Returns event listeners of the given object.
         * @param objectId protocol value
         * @param depth protocol value
         * @param pierce protocol value
         * @return a stage completing with the command result
         */
        public CompletionStage<java.util.List<DOMDebugger.EventListener>> getEventListeners(Runtime.RemoteObjectId objectId, @Nullable Long depth, @Nullable Boolean pierce) {
            final Map<String, Object> params = new LinkedHashMap<>();
            params.put("objectId", CdpObject.json(objectId));
            if (depth != null) params.put("depth", CdpObject.json(depth));
            if (pierce != null) params.put("pierce", CdpObject.json(pierce));
            return client.call("DOMDebugger.getEventListeners", params, result_ -> CdpObject.requireList(java.util.Objects.requireNonNull(result_.get("listeners")), element0 -> java.util.Objects.requireNonNull(DOMDebugger.EventListener.fromMap(CdpObject.requireObjectMap(element0)))));
        }
        /**
         * Returns event listeners of the given object.
         * @param objectId protocol value
         * @return a stage completing with the command result
         */
        public CompletionStage<java.util.List<DOMDebugger.EventListener>> getEventListeners(Runtime.RemoteObjectId objectId) {
            final Map<String, Object> params = new LinkedHashMap<>();
            params.put("objectId", CdpObject.json(objectId));
            return client.call("DOMDebugger.getEventListeners", params, result_ -> CdpObject.requireList(java.util.Objects.requireNonNull(result_.get("listeners")), element0 -> java.util.Objects.requireNonNull(DOMDebugger.EventListener.fromMap(CdpObject.requireObjectMap(element0)))));
        }
        /**
         * Returns event listeners of the given object.
         * @param request request parameters
         * @return a stage completing with the command result
         */
        public CompletionStage<java.util.List<DOMDebugger.EventListener>> getEventListeners(GetEventListenersRequest request) {
            return client.call("DOMDebugger.getEventListeners", request == null ? null : request.toMap(), result_ -> CdpObject.requireList(java.util.Objects.requireNonNull(result_.get("listeners")), element0 -> java.util.Objects.requireNonNull(DOMDebugger.EventListener.fromMap(CdpObject.requireObjectMap(element0)))));
        }
        /**
         * Removes DOM breakpoint that was set using {@code setDOMBreakpoint}.
         * @param nodeId protocol value
         * @param type protocol value
         * @return a stage completing when the command completes
         */
        public CompletionStage<Void> removeDOMBreakpoint(DOM.NodeId nodeId, DOMDebugger.DOMBreakpointType type) {
            final Map<String, Object> params = new LinkedHashMap<>();
            params.put("nodeId", CdpObject.json(nodeId));
            params.put("type", CdpObject.json(type));
            return client.call("DOMDebugger.removeDOMBreakpoint", params);
        }
        /**
         * Removes DOM breakpoint that was set using {@code setDOMBreakpoint}.
         * @param request request parameters
         * @return a stage completing when the command completes
         */
        public CompletionStage<Void> removeDOMBreakpoint(RemoveDOMBreakpointRequest request) {
            return client.call("DOMDebugger.removeDOMBreakpoint", request == null ? null : request.toMap());
        }
        /**
         * Removes breakpoint on particular DOM event.
         * @param eventName protocol value
         * @param targetName protocol value
         * @return a stage completing when the command completes
         */
        public CompletionStage<Void> removeEventListenerBreakpoint(String eventName, String targetName) {
            final Map<String, Object> params = new LinkedHashMap<>();
            params.put("eventName", CdpObject.json(eventName));
            params.put("targetName", CdpObject.json(targetName));
            return client.call("DOMDebugger.removeEventListenerBreakpoint", params);
        }
        /**
         * Removes breakpoint on particular DOM event.
         * @param eventName protocol value
         * @return a stage completing when the command completes
         */
        public CompletionStage<Void> removeEventListenerBreakpoint(String eventName) {
            final Map<String, Object> params = new LinkedHashMap<>();
            params.put("eventName", CdpObject.json(eventName));
            return client.call("DOMDebugger.removeEventListenerBreakpoint", params);
        }
        /**
         * Removes breakpoint on particular DOM event.
         * @param request request parameters
         * @return a stage completing when the command completes
         */
        public CompletionStage<Void> removeEventListenerBreakpoint(RemoveEventListenerBreakpointRequest request) {
            return client.call("DOMDebugger.removeEventListenerBreakpoint", request == null ? null : request.toMap());
        }
        /**
         * Removes breakpoint on particular native event.
         * <p><b>Experimental:</b> this part of CDP may change without notice.
         * @param eventName protocol value
         * @return a stage completing when the command completes
         * @deprecated Deprecated by the Chromium DevTools Protocol.
         */
        @Deprecated
        public CompletionStage<Void> removeInstrumentationBreakpoint(String eventName) {
            final Map<String, Object> params = new LinkedHashMap<>();
            params.put("eventName", CdpObject.json(eventName));
            return client.call("DOMDebugger.removeInstrumentationBreakpoint", params);
        }
        /**
         * Removes breakpoint on particular native event.
         * <p><b>Experimental:</b> this part of CDP may change without notice.
         * @param request request parameters
         * @return a stage completing when the command completes
         * @deprecated Deprecated by the Chromium DevTools Protocol.
         */
        @Deprecated
        public CompletionStage<Void> removeInstrumentationBreakpoint(RemoveInstrumentationBreakpointRequest request) {
            return client.call("DOMDebugger.removeInstrumentationBreakpoint", request == null ? null : request.toMap());
        }
        /**
         * Removes breakpoint from XMLHttpRequest.
         * @param url protocol value
         * @return a stage completing when the command completes
         */
        public CompletionStage<Void> removeXHRBreakpoint(String url) {
            final Map<String, Object> params = new LinkedHashMap<>();
            params.put("url", CdpObject.json(url));
            return client.call("DOMDebugger.removeXHRBreakpoint", params);
        }
        /**
         * Removes breakpoint from XMLHttpRequest.
         * @param request request parameters
         * @return a stage completing when the command completes
         */
        public CompletionStage<Void> removeXHRBreakpoint(RemoveXHRBreakpointRequest request) {
            return client.call("DOMDebugger.removeXHRBreakpoint", request == null ? null : request.toMap());
        }
        /**
         * Sets breakpoint on particular CSP violations.
         * <p><b>Experimental:</b> this part of CDP may change without notice.
         * @param violationTypes protocol value
         * @return a stage completing when the command completes
         */
        public CompletionStage<Void> setBreakOnCSPViolation(java.util.List<DOMDebugger.CSPViolationType> violationTypes) {
            final Map<String, Object> params = new LinkedHashMap<>();
            params.put("violationTypes", CdpObject.json(violationTypes));
            return client.call("DOMDebugger.setBreakOnCSPViolation", params);
        }
        /**
         * Sets breakpoint on particular CSP violations.
         * <p><b>Experimental:</b> this part of CDP may change without notice.
         * @param request request parameters
         * @return a stage completing when the command completes
         */
        public CompletionStage<Void> setBreakOnCSPViolation(SetBreakOnCSPViolationRequest request) {
            return client.call("DOMDebugger.setBreakOnCSPViolation", request == null ? null : request.toMap());
        }
        /**
         * Sets breakpoint on particular operation with DOM.
         * @param nodeId protocol value
         * @param type protocol value
         * @return a stage completing when the command completes
         */
        public CompletionStage<Void> setDOMBreakpoint(DOM.NodeId nodeId, DOMDebugger.DOMBreakpointType type) {
            final Map<String, Object> params = new LinkedHashMap<>();
            params.put("nodeId", CdpObject.json(nodeId));
            params.put("type", CdpObject.json(type));
            return client.call("DOMDebugger.setDOMBreakpoint", params);
        }
        /**
         * Sets breakpoint on particular operation with DOM.
         * @param request request parameters
         * @return a stage completing when the command completes
         */
        public CompletionStage<Void> setDOMBreakpoint(SetDOMBreakpointRequest request) {
            return client.call("DOMDebugger.setDOMBreakpoint", request == null ? null : request.toMap());
        }
        /**
         * Sets breakpoint on particular DOM event.
         * @param eventName protocol value
         * @param targetName protocol value
         * @return a stage completing when the command completes
         */
        public CompletionStage<Void> setEventListenerBreakpoint(String eventName, String targetName) {
            final Map<String, Object> params = new LinkedHashMap<>();
            params.put("eventName", CdpObject.json(eventName));
            params.put("targetName", CdpObject.json(targetName));
            return client.call("DOMDebugger.setEventListenerBreakpoint", params);
        }
        /**
         * Sets breakpoint on particular DOM event.
         * @param eventName protocol value
         * @return a stage completing when the command completes
         */
        public CompletionStage<Void> setEventListenerBreakpoint(String eventName) {
            final Map<String, Object> params = new LinkedHashMap<>();
            params.put("eventName", CdpObject.json(eventName));
            return client.call("DOMDebugger.setEventListenerBreakpoint", params);
        }
        /**
         * Sets breakpoint on particular DOM event.
         * @param request request parameters
         * @return a stage completing when the command completes
         */
        public CompletionStage<Void> setEventListenerBreakpoint(SetEventListenerBreakpointRequest request) {
            return client.call("DOMDebugger.setEventListenerBreakpoint", request == null ? null : request.toMap());
        }
        /**
         * Sets breakpoint on particular native event.
         * <p><b>Experimental:</b> this part of CDP may change without notice.
         * @param eventName protocol value
         * @return a stage completing when the command completes
         * @deprecated Deprecated by the Chromium DevTools Protocol.
         */
        @Deprecated
        public CompletionStage<Void> setInstrumentationBreakpoint(String eventName) {
            final Map<String, Object> params = new LinkedHashMap<>();
            params.put("eventName", CdpObject.json(eventName));
            return client.call("DOMDebugger.setInstrumentationBreakpoint", params);
        }
        /**
         * Sets breakpoint on particular native event.
         * <p><b>Experimental:</b> this part of CDP may change without notice.
         * @param request request parameters
         * @return a stage completing when the command completes
         * @deprecated Deprecated by the Chromium DevTools Protocol.
         */
        @Deprecated
        public CompletionStage<Void> setInstrumentationBreakpoint(SetInstrumentationBreakpointRequest request) {
            return client.call("DOMDebugger.setInstrumentationBreakpoint", request == null ? null : request.toMap());
        }
        /**
         * Sets breakpoint on XMLHttpRequest.
         * @param url protocol value
         * @return a stage completing when the command completes
         */
        public CompletionStage<Void> setXHRBreakpoint(String url) {
            final Map<String, Object> params = new LinkedHashMap<>();
            params.put("url", CdpObject.json(url));
            return client.call("DOMDebugger.setXHRBreakpoint", params);
        }
        /**
         * Sets breakpoint on XMLHttpRequest.
         * @param request request parameters
         * @return a stage completing when the command completes
         */
        public CompletionStage<Void> setXHRBreakpoint(SetXHRBreakpointRequest request) {
            return client.call("DOMDebugger.setXHRBreakpoint", request == null ? null : request.toMap());
        }
    }
}
