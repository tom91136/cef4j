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
 * EventBreakpoints permits setting JavaScript breakpoints on operations and events occurring in native code invoked from JavaScript. Once breakpoint is hit, it is reported through Debugger domain, similarly to regular breakpoints being hit.
 * <p><b>Experimental:</b> this part of CDP may change without notice.
 * @see <a href="https://chromium.googlesource.com/chromium/src/+/refs/tags/150.0.7871.213/third_party/blink/public/devtools_protocol/domains/EventBreakpoints.pdl">Pinned protocol source</a>
 */
@Generated("mvn generate-sources -pl cef4j-platform -Dcef.version=150.0.18+gdb11278+chromium-150.0.7871.213")
@SuppressWarnings({"EscapedEntity", "InvalidParam", "JavaLangClash", "MissingSummary", "Unchecked", "UnusedMethod"})
public final class EventBreakpoints {
    private EventBreakpoints() {}
    /**
     * Sets breakpoint on particular native event.
     */
    public static final class SetInstrumentationBreakpointRequest extends CdpObject {
        public SetInstrumentationBreakpointRequest() {}
        /**
         * Sets breakpoint on particular native event.
         * @param eventName protocol value
         */
        public SetInstrumentationBreakpointRequest(String eventName) {
            set("eventName", eventName);
        }
        public static SetInstrumentationBreakpointRequest fromMap(Map<String, Object> values) {
            SetInstrumentationBreakpointRequest instance_ = new SetInstrumentationBreakpointRequest();
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
     * Removes breakpoint on particular native event.
     */
    public static final class RemoveInstrumentationBreakpointRequest extends CdpObject {
        public RemoveInstrumentationBreakpointRequest() {}
        /**
         * Removes breakpoint on particular native event.
         * @param eventName protocol value
         */
        public RemoveInstrumentationBreakpointRequest(String eventName) {
            set("eventName", eventName);
        }
        public static RemoveInstrumentationBreakpointRequest fromMap(Map<String, Object> values) {
            RemoveInstrumentationBreakpointRequest instance_ = new RemoveInstrumentationBreakpointRequest();
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
    public static final class Client {
        private final CdpClient client;
        public Client(CdpClient client) { this.client = client; }
        /**
         * Sets breakpoint on particular native event.
         * @param eventName protocol value
         * @return a stage completing when the command completes
         */
        public CompletionStage<Void> setInstrumentationBreakpoint(String eventName) {
            Map<String, Object> params = new LinkedHashMap<>();
            params.put("eventName", CdpObject.json(eventName));
            return client.call("EventBreakpoints.setInstrumentationBreakpoint", params, result_ -> null);
        }
        /**
         * Sets breakpoint on particular native event.
         * @param request request parameters
         * @return a stage completing when the command completes
         */
        public CompletionStage<Void> setInstrumentationBreakpoint(SetInstrumentationBreakpointRequest request) {
            return client.call("EventBreakpoints.setInstrumentationBreakpoint", request == null ? null : request.toMap(), result_ -> null);
        }
        /**
         * Removes breakpoint on particular native event.
         * @param eventName protocol value
         * @return a stage completing when the command completes
         */
        public CompletionStage<Void> removeInstrumentationBreakpoint(String eventName) {
            Map<String, Object> params = new LinkedHashMap<>();
            params.put("eventName", CdpObject.json(eventName));
            return client.call("EventBreakpoints.removeInstrumentationBreakpoint", params, result_ -> null);
        }
        /**
         * Removes breakpoint on particular native event.
         * @param request request parameters
         * @return a stage completing when the command completes
         */
        public CompletionStage<Void> removeInstrumentationBreakpoint(RemoveInstrumentationBreakpointRequest request) {
            return client.call("EventBreakpoints.removeInstrumentationBreakpoint", request == null ? null : request.toMap(), result_ -> null);
        }
        /**
         * Removes all breakpoints
         * @return a stage completing when the command completes
         */
        public CompletionStage<Void> disable() {
            return client.call("EventBreakpoints.disable", null, result_ -> null);
        }
    }
}
