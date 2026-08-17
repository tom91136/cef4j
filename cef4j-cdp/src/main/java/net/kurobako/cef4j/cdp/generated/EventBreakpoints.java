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
 * EventBreakpoints permits setting JavaScript breakpoints on operations and events occurring in native code invoked from JavaScript. Once breakpoint is hit, it is reported through Debugger domain, similarly to regular breakpoints being hit.
 * <p><b>Experimental:</b> this part of CDP may change without notice.
 * @see <a href="https://chromium.googlesource.com/chromium/src/+/refs/tags/150.0.7871.213/third_party/blink/public/devtools_protocol/domains/EventBreakpoints.pdl">Pinned protocol source</a>
 */
@SuppressWarnings({"EscapedEntity", "JavaLangClash", "MissingSummary", "UnusedMethod"})
public final class EventBreakpoints {
    private EventBreakpoints() {}
    @Nullable private static Long numberAsLong(@Nullable Object value) { return value == null ? null : ((Number) value).longValue(); }
    @Nullable private static Double numberAsDouble(@Nullable Object value) { return value == null ? null : ((Number) value).doubleValue(); }
    /**
     * Sets breakpoint on particular native event.
     */
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
     */
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
     * Removes breakpoint on particular native event.
     */
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
     */
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
     * Removes all breakpoints
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
     * Removes all breakpoints
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
    public static final class Client {
        private final CdpClient client;
        public Client(CdpClient client) { this.client = client; }
        /**
         * Sets breakpoint on particular native event.
         * @param params command parameters
         * @return a stage completing with the command result
         */
        public CompletionStage<SetInstrumentationBreakpointResult> setInstrumentationBreakpoint(SetInstrumentationBreakpointParams params) {
            return client.call("EventBreakpoints.setInstrumentationBreakpoint", params, SetInstrumentationBreakpointResult::fromMap);
        }
        /**
         * Removes breakpoint on particular native event.
         * @param params command parameters
         * @return a stage completing with the command result
         */
        public CompletionStage<RemoveInstrumentationBreakpointResult> removeInstrumentationBreakpoint(RemoveInstrumentationBreakpointParams params) {
            return client.call("EventBreakpoints.removeInstrumentationBreakpoint", params, RemoveInstrumentationBreakpointResult::fromMap);
        }
        /**
         * Removes all breakpoints
         * @return a stage completing with the command result
         */
        public CompletionStage<DisableResult> disable() {
            return client.call("EventBreakpoints.disable", null, DisableResult::fromMap);
        }
    }
}
