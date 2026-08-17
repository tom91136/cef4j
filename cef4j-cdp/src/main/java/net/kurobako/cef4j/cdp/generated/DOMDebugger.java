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
 * DOM debugging allows setting breakpoints on particular DOM operations and events. JavaScript execution will stop on these operations as if there was a regular breakpoint set.
 * @see <a href="https://chromium.googlesource.com/chromium/src/+/refs/tags/150.0.7871.213/third_party/blink/public/devtools_protocol/domains/DOMDebugger.pdl">Pinned protocol source</a>
 */
@SuppressWarnings({"EscapedEntity", "JavaLangClash", "MissingSummary", "UnusedMethod"})
public final class DOMDebugger {
    private DOMDebugger() {}
    @Nullable private static Long numberAsLong(@Nullable Object value) { return value == null ? null : ((Number) value).longValue(); }
    @Nullable private static Double numberAsDouble(@Nullable Object value) { return value == null ? null : ((Number) value).doubleValue(); }
    /**
     * DOM breakpoint type.
     */
    public static final class DOMBreakpointType {
        private DOMBreakpointType() {}
        public static final String SUBTREE_MODIFIED = "subtree-modified";
        public static final String ATTRIBUTE_MODIFIED = "attribute-modified";
        public static final String NODE_REMOVED = "node-removed";
    }
    /**
     * CSP Violation type.
     * <p><b>Experimental:</b> this part of CDP may change without notice.
     */
    public static final class CSPViolationType {
        private CSPViolationType() {}
        public static final String TRUSTEDTYPE_SINK_VIOLATION = "trustedtype-sink-violation";
        public static final String TRUSTEDTYPE_POLICY_VIOLATION = "trustedtype-policy-violation";
    }
    /**
     * Object event listener.
     */
    public static final class EventListener extends CdpObject {
        private EventListener(Map<String, Object> values) { super(values); }
        @Nullable public static EventListener fromMap(@Nullable Map<String, Object> values) {
            return values == null ? null : new EventListener(values);
        }
        public static Builder builder() { return new Builder(); }
        /**
         * {@code EventListener}&#x27;s type.
         * @return the protocol field value
         */
        @Nullable public String type() {
            return (String) value("type");
        }
        /**
         * {@code EventListener}&#x27;s useCapture.
         * @return the protocol field value
         */
        @Nullable public Boolean useCapture() {
            return (Boolean) value("useCapture");
        }
        /**
         * {@code EventListener}&#x27;s passive flag.
         * @return the protocol field value
         */
        @Nullable public Boolean passive() {
            return (Boolean) value("passive");
        }
        /**
         * {@code EventListener}&#x27;s once flag.
         * @return the protocol field value
         */
        @Nullable public Boolean once() {
            return (Boolean) value("once");
        }
        /**
         * Script id of the handler code.
         * @return the protocol field value
         */
        @Nullable public String scriptId() {
            return (String) value("scriptId");
        }
        /**
         * Line number in the script (0-based).
         * @return the protocol field value
         */
        @Nullable public Long lineNumber() {
            return numberAsLong(value("lineNumber"));
        }
        /**
         * Column number in the script (0-based).
         * @return the protocol field value
         */
        @Nullable public Long columnNumber() {
            return numberAsLong(value("columnNumber"));
        }
        /**
         * Event handler function value.
         * @return the protocol field value
         */
        @Nullable public Runtime.RemoteObject handler() {
            return Runtime.RemoteObject.fromMap(objectMap(value("handler")));
        }
        /**
         * Event original handler function value.
         * @return the protocol field value
         */
        @Nullable public Runtime.RemoteObject originalHandler() {
            return Runtime.RemoteObject.fromMap(objectMap(value("originalHandler")));
        }
        /**
         * Node the listener is added to (if any).
         * @return the protocol field value
         */
        @Nullable public Long backendNodeId() {
            return numberAsLong(value("backendNodeId"));
        }
        public static final class Builder {
            private final Map<String, Object> values = new LinkedHashMap<>();
            /**
             * {@code EventListener}&#x27;s type.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder type(@Nullable String value) {
                if (value == null) values.remove("type");
                else values.put("type", jsonValue(value));
                return this;
            }
            /**
             * {@code EventListener}&#x27;s useCapture.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder useCapture(@Nullable Boolean value) {
                if (value == null) values.remove("useCapture");
                else values.put("useCapture", jsonValue(value));
                return this;
            }
            /**
             * {@code EventListener}&#x27;s passive flag.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder passive(@Nullable Boolean value) {
                if (value == null) values.remove("passive");
                else values.put("passive", jsonValue(value));
                return this;
            }
            /**
             * {@code EventListener}&#x27;s once flag.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder once(@Nullable Boolean value) {
                if (value == null) values.remove("once");
                else values.put("once", jsonValue(value));
                return this;
            }
            /**
             * Script id of the handler code.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder scriptId(@Nullable String value) {
                if (value == null) values.remove("scriptId");
                else values.put("scriptId", jsonValue(value));
                return this;
            }
            /**
             * Line number in the script (0-based).
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder lineNumber(@Nullable Long value) {
                if (value == null) values.remove("lineNumber");
                else values.put("lineNumber", jsonValue(value));
                return this;
            }
            /**
             * Column number in the script (0-based).
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder columnNumber(@Nullable Long value) {
                if (value == null) values.remove("columnNumber");
                else values.put("columnNumber", jsonValue(value));
                return this;
            }
            /**
             * Event handler function value.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder handler(@Nullable Runtime.RemoteObject value) {
                if (value == null) values.remove("handler");
                else values.put("handler", jsonValue(value));
                return this;
            }
            /**
             * Event original handler function value.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder originalHandler(@Nullable Runtime.RemoteObject value) {
                if (value == null) values.remove("originalHandler");
                else values.put("originalHandler", jsonValue(value));
                return this;
            }
            /**
             * Node the listener is added to (if any).
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder backendNodeId(@Nullable Long value) {
                if (value == null) values.remove("backendNodeId");
                else values.put("backendNodeId", jsonValue(value));
                return this;
            }
            public EventListener build() {
                if (!values.containsKey("type")) throw new IllegalStateException("Missing required CDP field: type");
                if (!values.containsKey("useCapture")) throw new IllegalStateException("Missing required CDP field: useCapture");
                if (!values.containsKey("passive")) throw new IllegalStateException("Missing required CDP field: passive");
                if (!values.containsKey("once")) throw new IllegalStateException("Missing required CDP field: once");
                if (!values.containsKey("scriptId")) throw new IllegalStateException("Missing required CDP field: scriptId");
                if (!values.containsKey("lineNumber")) throw new IllegalStateException("Missing required CDP field: lineNumber");
                if (!values.containsKey("columnNumber")) throw new IllegalStateException("Missing required CDP field: columnNumber");
                return new EventListener(values);
            }
        }
    }
    /**
     * Returns event listeners of the given object.
     */
    public static final class GetEventListenersParams extends CdpObject {
        private GetEventListenersParams(Map<String, Object> values) { super(values); }
        @Nullable public static GetEventListenersParams fromMap(@Nullable Map<String, Object> values) {
            return values == null ? null : new GetEventListenersParams(values);
        }
        public static Builder builder() { return new Builder(); }
        /**
         * Identifier of the object to return listeners for.
         * @return the protocol field value
         */
        @Nullable public String objectId() {
            return (String) value("objectId");
        }
        /**
         * The maximum depth at which Node children should be retrieved, defaults to 1. Use -1 for the entire subtree or provide an integer larger than 0.
         * @return the protocol field value
         */
        @Nullable public Long depth() {
            return numberAsLong(value("depth"));
        }
        /**
         * Whether or not iframes and shadow roots should be traversed when returning the subtree (default is false). Reports listeners for all contexts if pierce is enabled.
         * @return the protocol field value
         */
        @Nullable public Boolean pierce() {
            return (Boolean) value("pierce");
        }
        public static final class Builder {
            private final Map<String, Object> values = new LinkedHashMap<>();
            /**
             * Identifier of the object to return listeners for.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder objectId(@Nullable String value) {
                if (value == null) values.remove("objectId");
                else values.put("objectId", jsonValue(value));
                return this;
            }
            /**
             * The maximum depth at which Node children should be retrieved, defaults to 1. Use -1 for the entire subtree or provide an integer larger than 0.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder depth(@Nullable Long value) {
                if (value == null) values.remove("depth");
                else values.put("depth", jsonValue(value));
                return this;
            }
            /**
             * Whether or not iframes and shadow roots should be traversed when returning the subtree (default is false). Reports listeners for all contexts if pierce is enabled.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder pierce(@Nullable Boolean value) {
                if (value == null) values.remove("pierce");
                else values.put("pierce", jsonValue(value));
                return this;
            }
            public GetEventListenersParams build() {
                if (!values.containsKey("objectId")) throw new IllegalStateException("Missing required CDP field: objectId");
                return new GetEventListenersParams(values);
            }
        }
    }
    /**
     * Returns event listeners of the given object.
     */
    public static final class GetEventListenersResult extends CdpObject {
        private GetEventListenersResult(Map<String, Object> values) { super(values); }
        @Nullable public static GetEventListenersResult fromMap(@Nullable Map<String, Object> values) {
            return values == null ? null : new GetEventListenersResult(values);
        }
        public static Builder builder() { return new Builder(); }
        /**
         * Array of relevant listeners.
         * @return the protocol field value
         */
        @Nullable public java.util.List<DOMDebugger.EventListener> listeners() {
            return list(value("listeners"), element0 -> DOMDebugger.EventListener.fromMap(objectMap(element0)));
        }
        public static final class Builder {
            private final Map<String, Object> values = new LinkedHashMap<>();
            /**
             * Array of relevant listeners.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder listeners(@Nullable java.util.List<DOMDebugger.EventListener> value) {
                if (value == null) values.remove("listeners");
                else values.put("listeners", jsonValue(value));
                return this;
            }
            public GetEventListenersResult build() {
                if (!values.containsKey("listeners")) throw new IllegalStateException("Missing required CDP field: listeners");
                return new GetEventListenersResult(values);
            }
        }
    }
    /**
     * Removes DOM breakpoint that was set using {@code setDOMBreakpoint}.
     */
    public static final class RemoveDOMBreakpointParams extends CdpObject {
        private RemoveDOMBreakpointParams(Map<String, Object> values) { super(values); }
        @Nullable public static RemoveDOMBreakpointParams fromMap(@Nullable Map<String, Object> values) {
            return values == null ? null : new RemoveDOMBreakpointParams(values);
        }
        public static Builder builder() { return new Builder(); }
        /**
         * Identifier of the node to remove breakpoint from.
         * @return the protocol field value
         */
        @Nullable public Long nodeId() {
            return numberAsLong(value("nodeId"));
        }
        /**
         * Type of the breakpoint to remove.
         * @return the protocol field value
         */
        @Nullable public String type() {
            return (String) value("type");
        }
        public static final class Builder {
            private final Map<String, Object> values = new LinkedHashMap<>();
            /**
             * Identifier of the node to remove breakpoint from.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder nodeId(@Nullable Long value) {
                if (value == null) values.remove("nodeId");
                else values.put("nodeId", jsonValue(value));
                return this;
            }
            /**
             * Type of the breakpoint to remove.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder type(@Nullable String value) {
                if (value == null) values.remove("type");
                else values.put("type", jsonValue(value));
                return this;
            }
            public RemoveDOMBreakpointParams build() {
                if (!values.containsKey("nodeId")) throw new IllegalStateException("Missing required CDP field: nodeId");
                if (!values.containsKey("type")) throw new IllegalStateException("Missing required CDP field: type");
                return new RemoveDOMBreakpointParams(values);
            }
        }
    }
    /**
     * Removes DOM breakpoint that was set using {@code setDOMBreakpoint}.
     */
    public static final class RemoveDOMBreakpointResult extends CdpObject {
        private RemoveDOMBreakpointResult(Map<String, Object> values) { super(values); }
        @Nullable public static RemoveDOMBreakpointResult fromMap(@Nullable Map<String, Object> values) {
            return values == null ? null : new RemoveDOMBreakpointResult(values);
        }
        public static Builder builder() { return new Builder(); }
        public static final class Builder {
            private final Map<String, Object> values = new LinkedHashMap<>();
            public RemoveDOMBreakpointResult build() {
                return new RemoveDOMBreakpointResult(values);
            }
        }
    }
    /**
     * Removes breakpoint on particular DOM event.
     */
    public static final class RemoveEventListenerBreakpointParams extends CdpObject {
        private RemoveEventListenerBreakpointParams(Map<String, Object> values) { super(values); }
        @Nullable public static RemoveEventListenerBreakpointParams fromMap(@Nullable Map<String, Object> values) {
            return values == null ? null : new RemoveEventListenerBreakpointParams(values);
        }
        public static Builder builder() { return new Builder(); }
        /**
         * Event name.
         * @return the protocol field value
         */
        @Nullable public String eventName() {
            return (String) value("eventName");
        }
        /**
         * EventTarget interface name.
         * <p><b>Experimental:</b> this part of CDP may change without notice.
         * @return the protocol field value
         */
        @Nullable public String targetName() {
            return (String) value("targetName");
        }
        public static final class Builder {
            private final Map<String, Object> values = new LinkedHashMap<>();
            /**
             * Event name.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder eventName(@Nullable String value) {
                if (value == null) values.remove("eventName");
                else values.put("eventName", jsonValue(value));
                return this;
            }
            /**
             * EventTarget interface name.
             * <p><b>Experimental:</b> this part of CDP may change without notice.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder targetName(@Nullable String value) {
                if (value == null) values.remove("targetName");
                else values.put("targetName", jsonValue(value));
                return this;
            }
            public RemoveEventListenerBreakpointParams build() {
                if (!values.containsKey("eventName")) throw new IllegalStateException("Missing required CDP field: eventName");
                return new RemoveEventListenerBreakpointParams(values);
            }
        }
    }
    /**
     * Removes breakpoint on particular DOM event.
     */
    public static final class RemoveEventListenerBreakpointResult extends CdpObject {
        private RemoveEventListenerBreakpointResult(Map<String, Object> values) { super(values); }
        @Nullable public static RemoveEventListenerBreakpointResult fromMap(@Nullable Map<String, Object> values) {
            return values == null ? null : new RemoveEventListenerBreakpointResult(values);
        }
        public static Builder builder() { return new Builder(); }
        public static final class Builder {
            private final Map<String, Object> values = new LinkedHashMap<>();
            public RemoveEventListenerBreakpointResult build() {
                return new RemoveEventListenerBreakpointResult(values);
            }
        }
    }
    /**
     * Removes breakpoint on particular native event.
     * <p><b>Experimental:</b> this part of CDP may change without notice.
     * @deprecated Deprecated by the Chromium DevTools Protocol.
     */
    @Deprecated
    public static final class RemoveInstrumentationBreakpointParams extends CdpObject {
        private RemoveInstrumentationBreakpointParams(Map<String, Object> values) { super(values); }
        @Nullable public static RemoveInstrumentationBreakpointParams fromMap(@Nullable Map<String, Object> values) {
            return values == null ? null : new RemoveInstrumentationBreakpointParams(values);
        }
        public static Builder builder() { return new Builder(); }
        /**
         * Instrumentation name to stop on.
         * @return the protocol field value
         */
        @Nullable public String eventName() {
            return (String) value("eventName");
        }
        public static final class Builder {
            private final Map<String, Object> values = new LinkedHashMap<>();
            /**
             * Instrumentation name to stop on.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder eventName(@Nullable String value) {
                if (value == null) values.remove("eventName");
                else values.put("eventName", jsonValue(value));
                return this;
            }
            public RemoveInstrumentationBreakpointParams build() {
                if (!values.containsKey("eventName")) throw new IllegalStateException("Missing required CDP field: eventName");
                return new RemoveInstrumentationBreakpointParams(values);
            }
        }
    }
    /**
     * Removes breakpoint on particular native event.
     * <p><b>Experimental:</b> this part of CDP may change without notice.
     * @deprecated Deprecated by the Chromium DevTools Protocol.
     */
    @Deprecated
    public static final class RemoveInstrumentationBreakpointResult extends CdpObject {
        private RemoveInstrumentationBreakpointResult(Map<String, Object> values) { super(values); }
        @Nullable public static RemoveInstrumentationBreakpointResult fromMap(@Nullable Map<String, Object> values) {
            return values == null ? null : new RemoveInstrumentationBreakpointResult(values);
        }
        public static Builder builder() { return new Builder(); }
        public static final class Builder {
            private final Map<String, Object> values = new LinkedHashMap<>();
            public RemoveInstrumentationBreakpointResult build() {
                return new RemoveInstrumentationBreakpointResult(values);
            }
        }
    }
    /**
     * Removes breakpoint from XMLHttpRequest.
     */
    public static final class RemoveXHRBreakpointParams extends CdpObject {
        private RemoveXHRBreakpointParams(Map<String, Object> values) { super(values); }
        @Nullable public static RemoveXHRBreakpointParams fromMap(@Nullable Map<String, Object> values) {
            return values == null ? null : new RemoveXHRBreakpointParams(values);
        }
        public static Builder builder() { return new Builder(); }
        /**
         * Resource URL substring.
         * @return the protocol field value
         */
        @Nullable public String url() {
            return (String) value("url");
        }
        public static final class Builder {
            private final Map<String, Object> values = new LinkedHashMap<>();
            /**
             * Resource URL substring.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder url(@Nullable String value) {
                if (value == null) values.remove("url");
                else values.put("url", jsonValue(value));
                return this;
            }
            public RemoveXHRBreakpointParams build() {
                if (!values.containsKey("url")) throw new IllegalStateException("Missing required CDP field: url");
                return new RemoveXHRBreakpointParams(values);
            }
        }
    }
    /**
     * Removes breakpoint from XMLHttpRequest.
     */
    public static final class RemoveXHRBreakpointResult extends CdpObject {
        private RemoveXHRBreakpointResult(Map<String, Object> values) { super(values); }
        @Nullable public static RemoveXHRBreakpointResult fromMap(@Nullable Map<String, Object> values) {
            return values == null ? null : new RemoveXHRBreakpointResult(values);
        }
        public static Builder builder() { return new Builder(); }
        public static final class Builder {
            private final Map<String, Object> values = new LinkedHashMap<>();
            public RemoveXHRBreakpointResult build() {
                return new RemoveXHRBreakpointResult(values);
            }
        }
    }
    /**
     * Sets breakpoint on particular CSP violations.
     * <p><b>Experimental:</b> this part of CDP may change without notice.
     */
    public static final class SetBreakOnCSPViolationParams extends CdpObject {
        private SetBreakOnCSPViolationParams(Map<String, Object> values) { super(values); }
        @Nullable public static SetBreakOnCSPViolationParams fromMap(@Nullable Map<String, Object> values) {
            return values == null ? null : new SetBreakOnCSPViolationParams(values);
        }
        public static Builder builder() { return new Builder(); }
        /**
         * CSP Violations to stop upon.
         * @return the protocol field value
         */
        @Nullable public java.util.List<String> violationTypes() {
            return list(value("violationTypes"), element0 -> (String) element0);
        }
        public static final class Builder {
            private final Map<String, Object> values = new LinkedHashMap<>();
            /**
             * CSP Violations to stop upon.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder violationTypes(@Nullable java.util.List<String> value) {
                if (value == null) values.remove("violationTypes");
                else values.put("violationTypes", jsonValue(value));
                return this;
            }
            public SetBreakOnCSPViolationParams build() {
                if (!values.containsKey("violationTypes")) throw new IllegalStateException("Missing required CDP field: violationTypes");
                return new SetBreakOnCSPViolationParams(values);
            }
        }
    }
    /**
     * Sets breakpoint on particular CSP violations.
     * <p><b>Experimental:</b> this part of CDP may change without notice.
     */
    public static final class SetBreakOnCSPViolationResult extends CdpObject {
        private SetBreakOnCSPViolationResult(Map<String, Object> values) { super(values); }
        @Nullable public static SetBreakOnCSPViolationResult fromMap(@Nullable Map<String, Object> values) {
            return values == null ? null : new SetBreakOnCSPViolationResult(values);
        }
        public static Builder builder() { return new Builder(); }
        public static final class Builder {
            private final Map<String, Object> values = new LinkedHashMap<>();
            public SetBreakOnCSPViolationResult build() {
                return new SetBreakOnCSPViolationResult(values);
            }
        }
    }
    /**
     * Sets breakpoint on particular operation with DOM.
     */
    public static final class SetDOMBreakpointParams extends CdpObject {
        private SetDOMBreakpointParams(Map<String, Object> values) { super(values); }
        @Nullable public static SetDOMBreakpointParams fromMap(@Nullable Map<String, Object> values) {
            return values == null ? null : new SetDOMBreakpointParams(values);
        }
        public static Builder builder() { return new Builder(); }
        /**
         * Identifier of the node to set breakpoint on.
         * @return the protocol field value
         */
        @Nullable public Long nodeId() {
            return numberAsLong(value("nodeId"));
        }
        /**
         * Type of the operation to stop upon.
         * @return the protocol field value
         */
        @Nullable public String type() {
            return (String) value("type");
        }
        public static final class Builder {
            private final Map<String, Object> values = new LinkedHashMap<>();
            /**
             * Identifier of the node to set breakpoint on.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder nodeId(@Nullable Long value) {
                if (value == null) values.remove("nodeId");
                else values.put("nodeId", jsonValue(value));
                return this;
            }
            /**
             * Type of the operation to stop upon.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder type(@Nullable String value) {
                if (value == null) values.remove("type");
                else values.put("type", jsonValue(value));
                return this;
            }
            public SetDOMBreakpointParams build() {
                if (!values.containsKey("nodeId")) throw new IllegalStateException("Missing required CDP field: nodeId");
                if (!values.containsKey("type")) throw new IllegalStateException("Missing required CDP field: type");
                return new SetDOMBreakpointParams(values);
            }
        }
    }
    /**
     * Sets breakpoint on particular operation with DOM.
     */
    public static final class SetDOMBreakpointResult extends CdpObject {
        private SetDOMBreakpointResult(Map<String, Object> values) { super(values); }
        @Nullable public static SetDOMBreakpointResult fromMap(@Nullable Map<String, Object> values) {
            return values == null ? null : new SetDOMBreakpointResult(values);
        }
        public static Builder builder() { return new Builder(); }
        public static final class Builder {
            private final Map<String, Object> values = new LinkedHashMap<>();
            public SetDOMBreakpointResult build() {
                return new SetDOMBreakpointResult(values);
            }
        }
    }
    /**
     * Sets breakpoint on particular DOM event.
     */
    public static final class SetEventListenerBreakpointParams extends CdpObject {
        private SetEventListenerBreakpointParams(Map<String, Object> values) { super(values); }
        @Nullable public static SetEventListenerBreakpointParams fromMap(@Nullable Map<String, Object> values) {
            return values == null ? null : new SetEventListenerBreakpointParams(values);
        }
        public static Builder builder() { return new Builder(); }
        /**
         * DOM Event name to stop on (any DOM event will do).
         * @return the protocol field value
         */
        @Nullable public String eventName() {
            return (String) value("eventName");
        }
        /**
         * EventTarget interface name to stop on. If equal to {@code &quot;*&quot;} or not provided, will stop on any EventTarget.
         * <p><b>Experimental:</b> this part of CDP may change without notice.
         * @return the protocol field value
         */
        @Nullable public String targetName() {
            return (String) value("targetName");
        }
        public static final class Builder {
            private final Map<String, Object> values = new LinkedHashMap<>();
            /**
             * DOM Event name to stop on (any DOM event will do).
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder eventName(@Nullable String value) {
                if (value == null) values.remove("eventName");
                else values.put("eventName", jsonValue(value));
                return this;
            }
            /**
             * EventTarget interface name to stop on. If equal to {@code &quot;*&quot;} or not provided, will stop on any EventTarget.
             * <p><b>Experimental:</b> this part of CDP may change without notice.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder targetName(@Nullable String value) {
                if (value == null) values.remove("targetName");
                else values.put("targetName", jsonValue(value));
                return this;
            }
            public SetEventListenerBreakpointParams build() {
                if (!values.containsKey("eventName")) throw new IllegalStateException("Missing required CDP field: eventName");
                return new SetEventListenerBreakpointParams(values);
            }
        }
    }
    /**
     * Sets breakpoint on particular DOM event.
     */
    public static final class SetEventListenerBreakpointResult extends CdpObject {
        private SetEventListenerBreakpointResult(Map<String, Object> values) { super(values); }
        @Nullable public static SetEventListenerBreakpointResult fromMap(@Nullable Map<String, Object> values) {
            return values == null ? null : new SetEventListenerBreakpointResult(values);
        }
        public static Builder builder() { return new Builder(); }
        public static final class Builder {
            private final Map<String, Object> values = new LinkedHashMap<>();
            public SetEventListenerBreakpointResult build() {
                return new SetEventListenerBreakpointResult(values);
            }
        }
    }
    /**
     * Sets breakpoint on particular native event.
     * <p><b>Experimental:</b> this part of CDP may change without notice.
     * @deprecated Deprecated by the Chromium DevTools Protocol.
     */
    @Deprecated
    public static final class SetInstrumentationBreakpointParams extends CdpObject {
        private SetInstrumentationBreakpointParams(Map<String, Object> values) { super(values); }
        @Nullable public static SetInstrumentationBreakpointParams fromMap(@Nullable Map<String, Object> values) {
            return values == null ? null : new SetInstrumentationBreakpointParams(values);
        }
        public static Builder builder() { return new Builder(); }
        /**
         * Instrumentation name to stop on.
         * @return the protocol field value
         */
        @Nullable public String eventName() {
            return (String) value("eventName");
        }
        public static final class Builder {
            private final Map<String, Object> values = new LinkedHashMap<>();
            /**
             * Instrumentation name to stop on.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder eventName(@Nullable String value) {
                if (value == null) values.remove("eventName");
                else values.put("eventName", jsonValue(value));
                return this;
            }
            public SetInstrumentationBreakpointParams build() {
                if (!values.containsKey("eventName")) throw new IllegalStateException("Missing required CDP field: eventName");
                return new SetInstrumentationBreakpointParams(values);
            }
        }
    }
    /**
     * Sets breakpoint on particular native event.
     * <p><b>Experimental:</b> this part of CDP may change without notice.
     * @deprecated Deprecated by the Chromium DevTools Protocol.
     */
    @Deprecated
    public static final class SetInstrumentationBreakpointResult extends CdpObject {
        private SetInstrumentationBreakpointResult(Map<String, Object> values) { super(values); }
        @Nullable public static SetInstrumentationBreakpointResult fromMap(@Nullable Map<String, Object> values) {
            return values == null ? null : new SetInstrumentationBreakpointResult(values);
        }
        public static Builder builder() { return new Builder(); }
        public static final class Builder {
            private final Map<String, Object> values = new LinkedHashMap<>();
            public SetInstrumentationBreakpointResult build() {
                return new SetInstrumentationBreakpointResult(values);
            }
        }
    }
    /**
     * Sets breakpoint on XMLHttpRequest.
     */
    public static final class SetXHRBreakpointParams extends CdpObject {
        private SetXHRBreakpointParams(Map<String, Object> values) { super(values); }
        @Nullable public static SetXHRBreakpointParams fromMap(@Nullable Map<String, Object> values) {
            return values == null ? null : new SetXHRBreakpointParams(values);
        }
        public static Builder builder() { return new Builder(); }
        /**
         * Resource URL substring. All XHRs having this substring in the URL will get stopped upon.
         * @return the protocol field value
         */
        @Nullable public String url() {
            return (String) value("url");
        }
        public static final class Builder {
            private final Map<String, Object> values = new LinkedHashMap<>();
            /**
             * Resource URL substring. All XHRs having this substring in the URL will get stopped upon.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder url(@Nullable String value) {
                if (value == null) values.remove("url");
                else values.put("url", jsonValue(value));
                return this;
            }
            public SetXHRBreakpointParams build() {
                if (!values.containsKey("url")) throw new IllegalStateException("Missing required CDP field: url");
                return new SetXHRBreakpointParams(values);
            }
        }
    }
    /**
     * Sets breakpoint on XMLHttpRequest.
     */
    public static final class SetXHRBreakpointResult extends CdpObject {
        private SetXHRBreakpointResult(Map<String, Object> values) { super(values); }
        @Nullable public static SetXHRBreakpointResult fromMap(@Nullable Map<String, Object> values) {
            return values == null ? null : new SetXHRBreakpointResult(values);
        }
        public static Builder builder() { return new Builder(); }
        public static final class Builder {
            private final Map<String, Object> values = new LinkedHashMap<>();
            public SetXHRBreakpointResult build() {
                return new SetXHRBreakpointResult(values);
            }
        }
    }
    public static final class Client {
        private final CdpClient client;
        public Client(CdpClient client) { this.client = client; }
        /**
         * Returns event listeners of the given object.
         * @param params command parameters
         * @return a stage completing with the command result
         */
        public CompletionStage<GetEventListenersResult> getEventListeners(GetEventListenersParams params) {
            return client.call("DOMDebugger.getEventListeners", params, GetEventListenersResult::fromMap);
        }
        /**
         * Removes DOM breakpoint that was set using {@code setDOMBreakpoint}.
         * @param params command parameters
         * @return a stage completing with the command result
         */
        public CompletionStage<RemoveDOMBreakpointResult> removeDOMBreakpoint(RemoveDOMBreakpointParams params) {
            return client.call("DOMDebugger.removeDOMBreakpoint", params, RemoveDOMBreakpointResult::fromMap);
        }
        /**
         * Removes breakpoint on particular DOM event.
         * @param params command parameters
         * @return a stage completing with the command result
         */
        public CompletionStage<RemoveEventListenerBreakpointResult> removeEventListenerBreakpoint(RemoveEventListenerBreakpointParams params) {
            return client.call("DOMDebugger.removeEventListenerBreakpoint", params, RemoveEventListenerBreakpointResult::fromMap);
        }
        /**
         * Removes breakpoint on particular native event.
         * <p><b>Experimental:</b> this part of CDP may change without notice.
         * @param params command parameters
         * @return a stage completing with the command result
         * @deprecated Deprecated by the Chromium DevTools Protocol.
         */
        @Deprecated
        public CompletionStage<RemoveInstrumentationBreakpointResult> removeInstrumentationBreakpoint(RemoveInstrumentationBreakpointParams params) {
            return client.call("DOMDebugger.removeInstrumentationBreakpoint", params, RemoveInstrumentationBreakpointResult::fromMap);
        }
        /**
         * Removes breakpoint from XMLHttpRequest.
         * @param params command parameters
         * @return a stage completing with the command result
         */
        public CompletionStage<RemoveXHRBreakpointResult> removeXHRBreakpoint(RemoveXHRBreakpointParams params) {
            return client.call("DOMDebugger.removeXHRBreakpoint", params, RemoveXHRBreakpointResult::fromMap);
        }
        /**
         * Sets breakpoint on particular CSP violations.
         * <p><b>Experimental:</b> this part of CDP may change without notice.
         * @param params command parameters
         * @return a stage completing with the command result
         */
        public CompletionStage<SetBreakOnCSPViolationResult> setBreakOnCSPViolation(SetBreakOnCSPViolationParams params) {
            return client.call("DOMDebugger.setBreakOnCSPViolation", params, SetBreakOnCSPViolationResult::fromMap);
        }
        /**
         * Sets breakpoint on particular operation with DOM.
         * @param params command parameters
         * @return a stage completing with the command result
         */
        public CompletionStage<SetDOMBreakpointResult> setDOMBreakpoint(SetDOMBreakpointParams params) {
            return client.call("DOMDebugger.setDOMBreakpoint", params, SetDOMBreakpointResult::fromMap);
        }
        /**
         * Sets breakpoint on particular DOM event.
         * @param params command parameters
         * @return a stage completing with the command result
         */
        public CompletionStage<SetEventListenerBreakpointResult> setEventListenerBreakpoint(SetEventListenerBreakpointParams params) {
            return client.call("DOMDebugger.setEventListenerBreakpoint", params, SetEventListenerBreakpointResult::fromMap);
        }
        /**
         * Sets breakpoint on particular native event.
         * <p><b>Experimental:</b> this part of CDP may change without notice.
         * @param params command parameters
         * @return a stage completing with the command result
         * @deprecated Deprecated by the Chromium DevTools Protocol.
         */
        @Deprecated
        public CompletionStage<SetInstrumentationBreakpointResult> setInstrumentationBreakpoint(SetInstrumentationBreakpointParams params) {
            return client.call("DOMDebugger.setInstrumentationBreakpoint", params, SetInstrumentationBreakpointResult::fromMap);
        }
        /**
         * Sets breakpoint on XMLHttpRequest.
         * @param params command parameters
         * @return a stage completing with the command result
         */
        public CompletionStage<SetXHRBreakpointResult> setXHRBreakpoint(SetXHRBreakpointParams params) {
            return client.call("DOMDebugger.setXHRBreakpoint", params, SetXHRBreakpointResult::fromMap);
        }
    }
}
