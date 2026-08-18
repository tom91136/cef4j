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
 * DOM debugging allows setting breakpoints on particular DOM operations and events. JavaScript execution will stop on these operations as if there was a regular breakpoint set.
 * @see <a href="https://chromium.googlesource.com/chromium/src/+/refs/tags/150.0.7871.213/third_party/blink/public/devtools_protocol/domains/DOMDebugger.pdl">Pinned protocol source</a>
 */
@Generated("mvn generate-sources -pl cef4j-platform -Dcef.version=150.0.18+gdb11278+chromium-150.0.7871.213")
@SuppressWarnings({"EscapedEntity", "InvalidParam", "JavaLangClash", "MissingSummary", "Unchecked", "UnusedMethod"})
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
        @Nonnull public String value() { return value; }
        public static DOMBreakpointType of(@Nonnull String value) {
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
        @Nonnull public String value() { return value; }
        public static CSPViolationType of(@Nonnull String value) {
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
            return Optional.ofNullable(raw("handler") == null ? null : Runtime.RemoteObject.fromMap(java.util.Objects.requireNonNull(objectMap(raw("handler")))));
        }
        /**
         * Event original handler function value.
         * @return the protocol field value, empty when absent
         */
        public Optional<Runtime.RemoteObject> originalHandler() {
            return Optional.ofNullable(raw("originalHandler") == null ? null : Runtime.RemoteObject.fromMap(java.util.Objects.requireNonNull(objectMap(raw("originalHandler")))));
        }
        /**
         * Node the listener is added to (if any).
         * @return the protocol field value, empty when absent
         */
        public Optional<DOM.BackendNodeId> backendNodeId() {
            return Optional.ofNullable(raw("backendNodeId") == null ? null : new DOM.BackendNodeId(((Number) raw("backendNodeId")).longValue()));
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
         * @param handler field value; empty omits the value
         * @return this model
         */
        public EventListener handler(Optional<Runtime.RemoteObject> handler) {
            set("handler", handler.orElse(null));
            return this;
        }
        /**
         * Event handler function value.
         * @param handler field value; null removes the value
         * @return this model
         */
        public EventListener handler(Runtime.RemoteObject handler) {
            set("handler", handler);
            return this;
        }
        /**
         * Event original handler function value.
         * @param originalHandler field value; empty omits the value
         * @return this model
         */
        public EventListener originalHandler(Optional<Runtime.RemoteObject> originalHandler) {
            set("originalHandler", originalHandler.orElse(null));
            return this;
        }
        /**
         * Event original handler function value.
         * @param originalHandler field value; null removes the value
         * @return this model
         */
        public EventListener originalHandler(Runtime.RemoteObject originalHandler) {
            set("originalHandler", originalHandler);
            return this;
        }
        /**
         * Node the listener is added to (if any).
         * @param backendNodeId field value; empty omits the value
         * @return this model
         */
        public EventListener backendNodeId(Optional<DOM.BackendNodeId> backendNodeId) {
            set("backendNodeId", backendNodeId.orElse(null));
            return this;
        }
        /**
         * Node the listener is added to (if any).
         * @param backendNodeId field value; null removes the value
         * @return this model
         */
        public EventListener backendNodeId(DOM.BackendNodeId backendNodeId) {
            set("backendNodeId", backendNodeId);
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
        public CompletionStage<java.util.List<DOMDebugger.EventListener>> getEventListeners(Runtime.RemoteObjectId objectId, OptionalLong depth, Optional<Boolean> pierce) {
            Map<String, Object> params = new LinkedHashMap<>();
            params.put("objectId", CdpObject.json(objectId));
            depth.ifPresent(value_ -> params.put("depth", value_));
            pierce.ifPresent(value_ -> params.put("pierce", value_));
            return client.call("DOMDebugger.getEventListeners", params, result_ -> CdpObject.requireList(java.util.Objects.requireNonNull(result_.get("listeners")), element0 -> java.util.Objects.requireNonNull(DOMDebugger.EventListener.fromMap(java.util.Objects.requireNonNull(CdpObject.objectMap(element0))))));
        }
        /**
         * Returns event listeners of the given object.
         * @param objectId protocol value
         * @return a stage completing with the command result
         */
        public CompletionStage<java.util.List<DOMDebugger.EventListener>> getEventListeners(Runtime.RemoteObjectId objectId) {
            return getEventListeners(objectId, OptionalLong.empty(), Optional.empty());
        }
        /**
         * Removes DOM breakpoint that was set using {@code setDOMBreakpoint}.
         * @param nodeId protocol value
         * @param type protocol value
         * @return a stage completing when the command completes
         */
        public CompletionStage<Void> removeDOMBreakpoint(DOM.NodeId nodeId, DOMDebugger.DOMBreakpointType type) {
            Map<String, Object> params = new LinkedHashMap<>();
            params.put("nodeId", CdpObject.json(nodeId));
            params.put("type", CdpObject.json(type));
            return client.call("DOMDebugger.removeDOMBreakpoint", params, result_ -> null);
        }
        /**
         * Removes breakpoint on particular DOM event.
         * @param eventName protocol value
         * @param targetName protocol value
         * @return a stage completing when the command completes
         */
        public CompletionStage<Void> removeEventListenerBreakpoint(String eventName, Optional<String> targetName) {
            Map<String, Object> params = new LinkedHashMap<>();
            params.put("eventName", CdpObject.json(eventName));
            targetName.ifPresent(value_ -> params.put("targetName", CdpObject.json(value_)));
            return client.call("DOMDebugger.removeEventListenerBreakpoint", params, result_ -> null);
        }
        /**
         * Removes breakpoint on particular DOM event.
         * @param eventName protocol value
         * @return a stage completing when the command completes
         */
        public CompletionStage<Void> removeEventListenerBreakpoint(String eventName) {
            return removeEventListenerBreakpoint(eventName, Optional.empty());
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
            Map<String, Object> params = new LinkedHashMap<>();
            params.put("eventName", CdpObject.json(eventName));
            return client.call("DOMDebugger.removeInstrumentationBreakpoint", params, result_ -> null);
        }
        /**
         * Removes breakpoint from XMLHttpRequest.
         * @param url protocol value
         * @return a stage completing when the command completes
         */
        public CompletionStage<Void> removeXHRBreakpoint(String url) {
            Map<String, Object> params = new LinkedHashMap<>();
            params.put("url", CdpObject.json(url));
            return client.call("DOMDebugger.removeXHRBreakpoint", params, result_ -> null);
        }
        /**
         * Sets breakpoint on particular CSP violations.
         * <p><b>Experimental:</b> this part of CDP may change without notice.
         * @param violationTypes protocol value
         * @return a stage completing when the command completes
         */
        public CompletionStage<Void> setBreakOnCSPViolation(java.util.List<DOMDebugger.CSPViolationType> violationTypes) {
            Map<String, Object> params = new LinkedHashMap<>();
            params.put("violationTypes", CdpObject.json(violationTypes));
            return client.call("DOMDebugger.setBreakOnCSPViolation", params, result_ -> null);
        }
        /**
         * Sets breakpoint on particular operation with DOM.
         * @param nodeId protocol value
         * @param type protocol value
         * @return a stage completing when the command completes
         */
        public CompletionStage<Void> setDOMBreakpoint(DOM.NodeId nodeId, DOMDebugger.DOMBreakpointType type) {
            Map<String, Object> params = new LinkedHashMap<>();
            params.put("nodeId", CdpObject.json(nodeId));
            params.put("type", CdpObject.json(type));
            return client.call("DOMDebugger.setDOMBreakpoint", params, result_ -> null);
        }
        /**
         * Sets breakpoint on particular DOM event.
         * @param eventName protocol value
         * @param targetName protocol value
         * @return a stage completing when the command completes
         */
        public CompletionStage<Void> setEventListenerBreakpoint(String eventName, Optional<String> targetName) {
            Map<String, Object> params = new LinkedHashMap<>();
            params.put("eventName", CdpObject.json(eventName));
            targetName.ifPresent(value_ -> params.put("targetName", CdpObject.json(value_)));
            return client.call("DOMDebugger.setEventListenerBreakpoint", params, result_ -> null);
        }
        /**
         * Sets breakpoint on particular DOM event.
         * @param eventName protocol value
         * @return a stage completing when the command completes
         */
        public CompletionStage<Void> setEventListenerBreakpoint(String eventName) {
            return setEventListenerBreakpoint(eventName, Optional.empty());
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
            Map<String, Object> params = new LinkedHashMap<>();
            params.put("eventName", CdpObject.json(eventName));
            return client.call("DOMDebugger.setInstrumentationBreakpoint", params, result_ -> null);
        }
        /**
         * Sets breakpoint on XMLHttpRequest.
         * @param url protocol value
         * @return a stage completing when the command completes
         */
        public CompletionStage<Void> setXHRBreakpoint(String url) {
            Map<String, Object> params = new LinkedHashMap<>();
            params.put("url", CdpObject.json(url));
            return client.call("DOMDebugger.setXHRBreakpoint", params, result_ -> null);
        }
    }
}
