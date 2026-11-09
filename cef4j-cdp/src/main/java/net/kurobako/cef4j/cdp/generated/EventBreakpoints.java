// GENERATED - do not edit. Regenerate via: ./mvnw generate-sources -pl cef4j-cdp -Dcef.version=150.0.18+gdb11278+chromium-150.0.7871.213
package net.kurobako.cef4j.cdp.generated;

import java.util.Map;
import java.util.concurrent.CompletionStage;
import javax.annotation.processing.Generated;
import net.kurobako.cef4j.cdp.CdpClient;
import net.kurobako.cef4j.cdp.CdpDomainClient;
import net.kurobako.cef4j.cdp.CdpObject;
import net.kurobako.cef4j.policy.NullableBoundary;

/**
 * EventBreakpoints permits setting JavaScript breakpoints on operations and events occurring in native code invoked from JavaScript. Once breakpoint is hit, it is reported through Debugger domain, similarly to regular breakpoints being hit.
 * <p><b>Experimental:</b> this part of CDP may change without notice.
 * @see <a href="https://chromium.googlesource.com/chromium/src/+/refs/tags/150.0.7871.213/third_party/blink/public/devtools_protocol/domains/EventBreakpoints.pdl">Pinned protocol source</a>
 */
@Generated("./mvnw generate-sources -pl cef4j-cdp -Dcef.version=150.0.18+gdb11278+chromium-150.0.7871.213")
@SuppressWarnings({"EscapedEntity", "InvalidParam", "JavaLangClash", "MissingSummary", "Unchecked", "UnusedMethod"})
@NullableBoundary("CDP wire objects use null for absent optional fields")
public final class EventBreakpoints {
    private EventBreakpoints() {}
    /**
     * Sets breakpoint on particular native event.
     */
    public static final class SetInstrumentationBreakpointRequest extends CdpObject {
        public SetInstrumentationBreakpointRequest() {}
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
            return requireString("eventName");
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
            return requireString("eventName");
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
    public static final class Client extends CdpDomainClient {
        public Client(CdpClient client) { super(client); }
        /**
         * Sets breakpoint on particular native event.
         * @param eventName protocol value
         * @return a stage completing when the command completes
         */
        public CompletionStage<Void> setInstrumentationBreakpoint(String eventName) {
            return call("EventBreakpoints.setInstrumentationBreakpoint", params("eventName", eventName));
        }
        /**
         * Sets breakpoint on particular native event.
         * @param request request parameters
         * @return a stage completing when the command completes
         */
        public CompletionStage<Void> setInstrumentationBreakpoint(SetInstrumentationBreakpointRequest request) {
            return call("EventBreakpoints.setInstrumentationBreakpoint", request);
        }
        /**
         * Removes breakpoint on particular native event.
         * @param eventName protocol value
         * @return a stage completing when the command completes
         */
        public CompletionStage<Void> removeInstrumentationBreakpoint(String eventName) {
            return call("EventBreakpoints.removeInstrumentationBreakpoint", params("eventName", eventName));
        }
        /**
         * Removes breakpoint on particular native event.
         * @param request request parameters
         * @return a stage completing when the command completes
         */
        public CompletionStage<Void> removeInstrumentationBreakpoint(RemoveInstrumentationBreakpointRequest request) {
            return call("EventBreakpoints.removeInstrumentationBreakpoint", request);
        }
        /**
         * Removes all breakpoints
         * @return a stage completing when the command completes
         */
        public CompletionStage<Void> disable() {
            return call("EventBreakpoints.disable");
        }
    }
}
