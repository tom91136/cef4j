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
 * Chrome DevTools Protocol WebMCP domain.
 * <p><b>Experimental:</b> this part of CDP may change without notice.
 * @see <a href="https://chromium.googlesource.com/chromium/src/+/refs/tags/150.0.7871.213/third_party/blink/public/devtools_protocol/domains/WebMCP.pdl">Pinned protocol source</a>
 */
@SuppressWarnings({"EscapedEntity", "JavaLangClash", "MissingSummary", "UnusedMethod"})
public final class WebMCP {
    private WebMCP() {}
    @Nullable private static Long numberAsLong(@Nullable Object value) { return value == null ? null : ((Number) value).longValue(); }
    @Nullable private static Double numberAsDouble(@Nullable Object value) { return value == null ? null : ((Number) value).doubleValue(); }
    /**
     * Tool annotations
     */
    public static final class Annotation extends CdpObject {
        private Annotation(Map<String, Object> values) { super(values); }
        @Nullable public static Annotation fromMap(@Nullable Map<String, Object> values) {
            return values == null ? null : new Annotation(values);
        }
        public static Builder builder() { return new Builder(); }
        /**
         * A hint indicating that the tool does not modify any state.
         * @return the protocol field value
         */
        @Nullable public Boolean readOnly() {
            return (Boolean) value("readOnly");
        }
        /**
         * A hint indicating that the tool output may contain untrusted content, ex: UGC, 3rd party data.
         * @return the protocol field value
         */
        @Nullable public Boolean untrustedContent() {
            return (Boolean) value("untrustedContent");
        }
        /**
         * If the declarative tool was declared with the autosubmit attribute.
         * @return the protocol field value
         */
        @Nullable public Boolean autosubmit() {
            return (Boolean) value("autosubmit");
        }
        public static final class Builder {
            private final Map<String, Object> values = new LinkedHashMap<>();
            /**
             * A hint indicating that the tool does not modify any state.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder readOnly(@Nullable Boolean value) {
                if (value == null) values.remove("readOnly");
                else values.put("readOnly", jsonValue(value));
                return this;
            }
            /**
             * A hint indicating that the tool output may contain untrusted content, ex: UGC, 3rd party data.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder untrustedContent(@Nullable Boolean value) {
                if (value == null) values.remove("untrustedContent");
                else values.put("untrustedContent", jsonValue(value));
                return this;
            }
            /**
             * If the declarative tool was declared with the autosubmit attribute.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder autosubmit(@Nullable Boolean value) {
                if (value == null) values.remove("autosubmit");
                else values.put("autosubmit", jsonValue(value));
                return this;
            }
            public Annotation build() {
                return new Annotation(values);
            }
        }
    }
    /**
     * Represents the status of a tool invocation.
     */
    public static final class InvocationStatus {
        private InvocationStatus() {}
        public static final String COMPLETED = "Completed";
        public static final String CANCELED = "Canceled";
        public static final String ERROR = "Error";
    }
    /**
     * Definition of a tool that can be invoked.
     */
    public static final class Tool extends CdpObject {
        private Tool(Map<String, Object> values) { super(values); }
        @Nullable public static Tool fromMap(@Nullable Map<String, Object> values) {
            return values == null ? null : new Tool(values);
        }
        public static Builder builder() { return new Builder(); }
        /**
         * Tool name.
         * @return the protocol field value
         */
        @Nullable public String name() {
            return (String) value("name");
        }
        /**
         * Tool description.
         * @return the protocol field value
         */
        @Nullable public String description() {
            return (String) value("description");
        }
        /**
         * Schema for the tool&#x27;s input parameters.
         * @return the protocol field value
         */
        @Nullable public java.util.Map<String, Object> inputSchema() {
            return objectMap(value("inputSchema"));
        }
        /**
         * Optional annotations for the tool.
         * @return the protocol field value
         */
        @Nullable public WebMCP.Annotation annotations() {
            return WebMCP.Annotation.fromMap(objectMap(value("annotations")));
        }
        /**
         * Frame identifier associated with the tool registration.
         * @return the protocol field value
         */
        @Nullable public String frameId() {
            return (String) value("frameId");
        }
        /**
         * Optional node ID for declarative tools.
         * @return the protocol field value
         */
        @Nullable public Long backendNodeId() {
            return numberAsLong(value("backendNodeId"));
        }
        /**
         * The stack trace at the time of the registration.
         * @return the protocol field value
         */
        @Nullable public Runtime.StackTrace stackTrace() {
            return Runtime.StackTrace.fromMap(objectMap(value("stackTrace")));
        }
        public static final class Builder {
            private final Map<String, Object> values = new LinkedHashMap<>();
            /**
             * Tool name.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder name(@Nullable String value) {
                if (value == null) values.remove("name");
                else values.put("name", jsonValue(value));
                return this;
            }
            /**
             * Tool description.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder description(@Nullable String value) {
                if (value == null) values.remove("description");
                else values.put("description", jsonValue(value));
                return this;
            }
            /**
             * Schema for the tool&#x27;s input parameters.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder inputSchema(@Nullable java.util.Map<String, Object> value) {
                if (value == null) values.remove("inputSchema");
                else values.put("inputSchema", jsonValue(value));
                return this;
            }
            /**
             * Optional annotations for the tool.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder annotations(@Nullable WebMCP.Annotation value) {
                if (value == null) values.remove("annotations");
                else values.put("annotations", jsonValue(value));
                return this;
            }
            /**
             * Frame identifier associated with the tool registration.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder frameId(@Nullable String value) {
                if (value == null) values.remove("frameId");
                else values.put("frameId", jsonValue(value));
                return this;
            }
            /**
             * Optional node ID for declarative tools.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder backendNodeId(@Nullable Long value) {
                if (value == null) values.remove("backendNodeId");
                else values.put("backendNodeId", jsonValue(value));
                return this;
            }
            /**
             * The stack trace at the time of the registration.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder stackTrace(@Nullable Runtime.StackTrace value) {
                if (value == null) values.remove("stackTrace");
                else values.put("stackTrace", jsonValue(value));
                return this;
            }
            public Tool build() {
                if (!values.containsKey("name")) throw new IllegalStateException("Missing required CDP field: name");
                if (!values.containsKey("description")) throw new IllegalStateException("Missing required CDP field: description");
                if (!values.containsKey("frameId")) throw new IllegalStateException("Missing required CDP field: frameId");
                return new Tool(values);
            }
        }
    }
    /**
     * Definition of a tool that was removed.
     */
    public static final class RemovedTool extends CdpObject {
        private RemovedTool(Map<String, Object> values) { super(values); }
        @Nullable public static RemovedTool fromMap(@Nullable Map<String, Object> values) {
            return values == null ? null : new RemovedTool(values);
        }
        public static Builder builder() { return new Builder(); }
        /**
         * Tool name.
         * @return the protocol field value
         */
        @Nullable public String name() {
            return (String) value("name");
        }
        /**
         * Frame identifier associated with the tool registration.
         * @return the protocol field value
         */
        @Nullable public String frameId() {
            return (String) value("frameId");
        }
        public static final class Builder {
            private final Map<String, Object> values = new LinkedHashMap<>();
            /**
             * Tool name.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder name(@Nullable String value) {
                if (value == null) values.remove("name");
                else values.put("name", jsonValue(value));
                return this;
            }
            /**
             * Frame identifier associated with the tool registration.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder frameId(@Nullable String value) {
                if (value == null) values.remove("frameId");
                else values.put("frameId", jsonValue(value));
                return this;
            }
            public RemovedTool build() {
                if (!values.containsKey("name")) throw new IllegalStateException("Missing required CDP field: name");
                if (!values.containsKey("frameId")) throw new IllegalStateException("Missing required CDP field: frameId");
                return new RemovedTool(values);
            }
        }
    }
    /**
     * Enables the WebMCP domain, allowing events to be sent. Enabling the domain will trigger a toolsAdded event for all currently registered tools.
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
     * Enables the WebMCP domain, allowing events to be sent. Enabling the domain will trigger a toolsAdded event for all currently registered tools.
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
     * Disables the WebMCP domain.
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
     * Disables the WebMCP domain.
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
     * Invokes a registered tool.
     */
    public static final class InvokeToolParams extends CdpObject {
        private InvokeToolParams(Map<String, Object> values) { super(values); }
        @Nullable public static InvokeToolParams fromMap(@Nullable Map<String, Object> values) {
            return values == null ? null : new InvokeToolParams(values);
        }
        public static Builder builder() { return new Builder(); }
        /**
         * Frame in which to invoke the tool.
         * @return the protocol field value
         */
        @Nullable public String frameId() {
            return (String) value("frameId");
        }
        /**
         * Name of the tool to invoke.
         * @return the protocol field value
         */
        @Nullable public String toolName() {
            return (String) value("toolName");
        }
        /**
         * Input parameters for the tool, matching the tool&#x27;s inputSchema.
         * @return the protocol field value
         */
        @Nullable public java.util.Map<String, Object> input() {
            return objectMap(value("input"));
        }
        public static final class Builder {
            private final Map<String, Object> values = new LinkedHashMap<>();
            /**
             * Frame in which to invoke the tool.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder frameId(@Nullable String value) {
                if (value == null) values.remove("frameId");
                else values.put("frameId", jsonValue(value));
                return this;
            }
            /**
             * Name of the tool to invoke.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder toolName(@Nullable String value) {
                if (value == null) values.remove("toolName");
                else values.put("toolName", jsonValue(value));
                return this;
            }
            /**
             * Input parameters for the tool, matching the tool&#x27;s inputSchema.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder input(@Nullable java.util.Map<String, Object> value) {
                if (value == null) values.remove("input");
                else values.put("input", jsonValue(value));
                return this;
            }
            public InvokeToolParams build() {
                if (!values.containsKey("frameId")) throw new IllegalStateException("Missing required CDP field: frameId");
                if (!values.containsKey("toolName")) throw new IllegalStateException("Missing required CDP field: toolName");
                if (!values.containsKey("input")) throw new IllegalStateException("Missing required CDP field: input");
                return new InvokeToolParams(values);
            }
        }
    }
    /**
     * Invokes a registered tool.
     */
    public static final class InvokeToolResult extends CdpObject {
        private InvokeToolResult(Map<String, Object> values) { super(values); }
        @Nullable public static InvokeToolResult fromMap(@Nullable Map<String, Object> values) {
            return values == null ? null : new InvokeToolResult(values);
        }
        public static Builder builder() { return new Builder(); }
        /**
         * Unique identifier for this invocation. Response is sent before tool events.
         * @return the protocol field value
         */
        @Nullable public String invocationId() {
            return (String) value("invocationId");
        }
        public static final class Builder {
            private final Map<String, Object> values = new LinkedHashMap<>();
            /**
             * Unique identifier for this invocation. Response is sent before tool events.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder invocationId(@Nullable String value) {
                if (value == null) values.remove("invocationId");
                else values.put("invocationId", jsonValue(value));
                return this;
            }
            public InvokeToolResult build() {
                if (!values.containsKey("invocationId")) throw new IllegalStateException("Missing required CDP field: invocationId");
                return new InvokeToolResult(values);
            }
        }
    }
    /**
     * Cancels a pending tool invocation.
     */
    public static final class CancelInvocationParams extends CdpObject {
        private CancelInvocationParams(Map<String, Object> values) { super(values); }
        @Nullable public static CancelInvocationParams fromMap(@Nullable Map<String, Object> values) {
            return values == null ? null : new CancelInvocationParams(values);
        }
        public static Builder builder() { return new Builder(); }
        /**
         * Invocation identifier to cancel.
         * @return the protocol field value
         */
        @Nullable public String invocationId() {
            return (String) value("invocationId");
        }
        public static final class Builder {
            private final Map<String, Object> values = new LinkedHashMap<>();
            /**
             * Invocation identifier to cancel.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder invocationId(@Nullable String value) {
                if (value == null) values.remove("invocationId");
                else values.put("invocationId", jsonValue(value));
                return this;
            }
            public CancelInvocationParams build() {
                if (!values.containsKey("invocationId")) throw new IllegalStateException("Missing required CDP field: invocationId");
                return new CancelInvocationParams(values);
            }
        }
    }
    /**
     * Cancels a pending tool invocation.
     */
    public static final class CancelInvocationResult extends CdpObject {
        private CancelInvocationResult(Map<String, Object> values) { super(values); }
        @Nullable public static CancelInvocationResult fromMap(@Nullable Map<String, Object> values) {
            return values == null ? null : new CancelInvocationResult(values);
        }
        public static Builder builder() { return new Builder(); }
        public static final class Builder {
            private final Map<String, Object> values = new LinkedHashMap<>();
            public CancelInvocationResult build() {
                return new CancelInvocationResult(values);
            }
        }
    }
    /**
     * Event fired when new tools are added.
     */
    public static final class ToolsAddedEvent extends CdpObject {
        private ToolsAddedEvent(Map<String, Object> values) { super(values); }
        @Nullable public static ToolsAddedEvent fromMap(@Nullable Map<String, Object> values) {
            return values == null ? null : new ToolsAddedEvent(values);
        }
        public static Builder builder() { return new Builder(); }
        /**
         * Array of tools that were added.
         * @return the protocol field value
         */
        @Nullable public java.util.List<WebMCP.Tool> tools() {
            return list(value("tools"), element0 -> WebMCP.Tool.fromMap(objectMap(element0)));
        }
        public static final class Builder {
            private final Map<String, Object> values = new LinkedHashMap<>();
            /**
             * Array of tools that were added.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder tools(@Nullable java.util.List<WebMCP.Tool> value) {
                if (value == null) values.remove("tools");
                else values.put("tools", jsonValue(value));
                return this;
            }
            public ToolsAddedEvent build() {
                if (!values.containsKey("tools")) throw new IllegalStateException("Missing required CDP field: tools");
                return new ToolsAddedEvent(values);
            }
        }
    }
    /**
     * Event fired when tools are removed.
     */
    public static final class ToolsRemovedEvent extends CdpObject {
        private ToolsRemovedEvent(Map<String, Object> values) { super(values); }
        @Nullable public static ToolsRemovedEvent fromMap(@Nullable Map<String, Object> values) {
            return values == null ? null : new ToolsRemovedEvent(values);
        }
        public static Builder builder() { return new Builder(); }
        /**
         * Array of tools that were removed.
         * @return the protocol field value
         */
        @Nullable public java.util.List<WebMCP.RemovedTool> tools() {
            return list(value("tools"), element0 -> WebMCP.RemovedTool.fromMap(objectMap(element0)));
        }
        public static final class Builder {
            private final Map<String, Object> values = new LinkedHashMap<>();
            /**
             * Array of tools that were removed.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder tools(@Nullable java.util.List<WebMCP.RemovedTool> value) {
                if (value == null) values.remove("tools");
                else values.put("tools", jsonValue(value));
                return this;
            }
            public ToolsRemovedEvent build() {
                if (!values.containsKey("tools")) throw new IllegalStateException("Missing required CDP field: tools");
                return new ToolsRemovedEvent(values);
            }
        }
    }
    /**
     * Event fired when a tool invocation starts.
     */
    public static final class ToolInvokedEvent extends CdpObject {
        private ToolInvokedEvent(Map<String, Object> values) { super(values); }
        @Nullable public static ToolInvokedEvent fromMap(@Nullable Map<String, Object> values) {
            return values == null ? null : new ToolInvokedEvent(values);
        }
        public static Builder builder() { return new Builder(); }
        /**
         * Name of the tool to invoke.
         * @return the protocol field value
         */
        @Nullable public String toolName() {
            return (String) value("toolName");
        }
        /**
         * Frame id
         * @return the protocol field value
         */
        @Nullable public String frameId() {
            return (String) value("frameId");
        }
        /**
         * Invocation identifier.
         * @return the protocol field value
         */
        @Nullable public String invocationId() {
            return (String) value("invocationId");
        }
        /**
         * The input parameters used for the invocation.
         * @return the protocol field value
         */
        @Nullable public String input() {
            return (String) value("input");
        }
        public static final class Builder {
            private final Map<String, Object> values = new LinkedHashMap<>();
            /**
             * Name of the tool to invoke.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder toolName(@Nullable String value) {
                if (value == null) values.remove("toolName");
                else values.put("toolName", jsonValue(value));
                return this;
            }
            /**
             * Frame id
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder frameId(@Nullable String value) {
                if (value == null) values.remove("frameId");
                else values.put("frameId", jsonValue(value));
                return this;
            }
            /**
             * Invocation identifier.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder invocationId(@Nullable String value) {
                if (value == null) values.remove("invocationId");
                else values.put("invocationId", jsonValue(value));
                return this;
            }
            /**
             * The input parameters used for the invocation.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder input(@Nullable String value) {
                if (value == null) values.remove("input");
                else values.put("input", jsonValue(value));
                return this;
            }
            public ToolInvokedEvent build() {
                if (!values.containsKey("toolName")) throw new IllegalStateException("Missing required CDP field: toolName");
                if (!values.containsKey("frameId")) throw new IllegalStateException("Missing required CDP field: frameId");
                if (!values.containsKey("invocationId")) throw new IllegalStateException("Missing required CDP field: invocationId");
                if (!values.containsKey("input")) throw new IllegalStateException("Missing required CDP field: input");
                return new ToolInvokedEvent(values);
            }
        }
    }
    /**
     * Event fired when a tool invocation completes or fails.
     */
    public static final class ToolRespondedEvent extends CdpObject {
        private ToolRespondedEvent(Map<String, Object> values) { super(values); }
        @Nullable public static ToolRespondedEvent fromMap(@Nullable Map<String, Object> values) {
            return values == null ? null : new ToolRespondedEvent(values);
        }
        public static Builder builder() { return new Builder(); }
        /**
         * Invocation identifier.
         * @return the protocol field value
         */
        @Nullable public String invocationId() {
            return (String) value("invocationId");
        }
        /**
         * Status of the invocation.
         * @return the protocol field value
         */
        @Nullable public String status() {
            return (String) value("status");
        }
        /**
         * Output or error delivered as delivered to the agent. Missing if {@code status} is anything other than Completed. Note: The output is untrusted and poses a prompt injection risk. Clients should treat this as potentially malicious user input.
         * @return the protocol field value
         */
        @Nullable public Object output() {
            return value("output");
        }
        /**
         * Error text for protocol users.
         * @return the protocol field value
         */
        @Nullable public String errorText() {
            return (String) value("errorText");
        }
        /**
         * The exception object, if the javascript tool threw an error&gt;
         * @return the protocol field value
         */
        @Nullable public Runtime.RemoteObject exception() {
            return Runtime.RemoteObject.fromMap(objectMap(value("exception")));
        }
        public static final class Builder {
            private final Map<String, Object> values = new LinkedHashMap<>();
            /**
             * Invocation identifier.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder invocationId(@Nullable String value) {
                if (value == null) values.remove("invocationId");
                else values.put("invocationId", jsonValue(value));
                return this;
            }
            /**
             * Status of the invocation.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder status(@Nullable String value) {
                if (value == null) values.remove("status");
                else values.put("status", jsonValue(value));
                return this;
            }
            /**
             * Output or error delivered as delivered to the agent. Missing if {@code status} is anything other than Completed. Note: The output is untrusted and poses a prompt injection risk. Clients should treat this as potentially malicious user input.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder output(@Nullable Object value) {
                if (value == null) values.remove("output");
                else values.put("output", jsonValue(value));
                return this;
            }
            /**
             * Error text for protocol users.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder errorText(@Nullable String value) {
                if (value == null) values.remove("errorText");
                else values.put("errorText", jsonValue(value));
                return this;
            }
            /**
             * The exception object, if the javascript tool threw an error&gt;
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder exception(@Nullable Runtime.RemoteObject value) {
                if (value == null) values.remove("exception");
                else values.put("exception", jsonValue(value));
                return this;
            }
            public ToolRespondedEvent build() {
                if (!values.containsKey("invocationId")) throw new IllegalStateException("Missing required CDP field: invocationId");
                if (!values.containsKey("status")) throw new IllegalStateException("Missing required CDP field: status");
                return new ToolRespondedEvent(values);
            }
        }
    }
    public static final class Client {
        private final CdpClient client;
        public Client(CdpClient client) { this.client = client; }
        /**
         * Enables the WebMCP domain, allowing events to be sent. Enabling the domain will trigger a toolsAdded event for all currently registered tools.
         * @return a stage completing with the command result
         */
        public CompletionStage<EnableResult> enable() {
            return client.call("WebMCP.enable", null, EnableResult::fromMap);
        }
        /**
         * Disables the WebMCP domain.
         * @return a stage completing with the command result
         */
        public CompletionStage<DisableResult> disable() {
            return client.call("WebMCP.disable", null, DisableResult::fromMap);
        }
        /**
         * Invokes a registered tool.
         * @param params command parameters
         * @return a stage completing with the command result
         */
        public CompletionStage<InvokeToolResult> invokeTool(InvokeToolParams params) {
            return client.call("WebMCP.invokeTool", params, InvokeToolResult::fromMap);
        }
        /**
         * Cancels a pending tool invocation.
         * @param params command parameters
         * @return a stage completing with the command result
         */
        public CompletionStage<CancelInvocationResult> cancelInvocation(CancelInvocationParams params) {
            return client.call("WebMCP.cancelInvocation", params, CancelInvocationResult::fromMap);
        }
        /**
         * Event fired when new tools are added.
         * @param handler event callback
         * @return a removable subscription
         */
        public CdpSubscription onToolsAdded(Consumer<ToolsAddedEvent> handler) {
            return client.on("WebMCP.toolsAdded", ToolsAddedEvent::fromMap, handler);
        }
        /**
         * Event fired when tools are removed.
         * @param handler event callback
         * @return a removable subscription
         */
        public CdpSubscription onToolsRemoved(Consumer<ToolsRemovedEvent> handler) {
            return client.on("WebMCP.toolsRemoved", ToolsRemovedEvent::fromMap, handler);
        }
        /**
         * Event fired when a tool invocation starts.
         * @param handler event callback
         * @return a removable subscription
         */
        public CdpSubscription onToolInvoked(Consumer<ToolInvokedEvent> handler) {
            return client.on("WebMCP.toolInvoked", ToolInvokedEvent::fromMap, handler);
        }
        /**
         * Event fired when a tool invocation completes or fails.
         * @param handler event callback
         * @return a removable subscription
         */
        public CdpSubscription onToolResponded(Consumer<ToolRespondedEvent> handler) {
            return client.on("WebMCP.toolResponded", ToolRespondedEvent::fromMap, handler);
        }
    }
}
