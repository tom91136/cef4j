// GENERATED - do not edit. Regenerate via: ./mvnw generate-sources -pl cef4j-cdp -Dcef.version=150.0.18+gdb11278+chromium-150.0.7871.213
package net.kurobako.cef4j.cdp.generated;

import java.util.Map;
import java.util.Optional;
import java.util.concurrent.CompletionStage;
import java.util.function.Consumer;
import javax.annotation.processing.Generated;
import net.kurobako.cef4j.cdp.CdpClient;
import net.kurobako.cef4j.cdp.CdpDomainClient;
import net.kurobako.cef4j.cdp.CdpField;
import net.kurobako.cef4j.cdp.CdpObject;
import net.kurobako.cef4j.cdp.CdpSubscription;
import net.kurobako.cef4j.cdp.CdpValue;
import net.kurobako.cef4j.policy.NullableBoundary;

/**
 * Chrome DevTools Protocol WebMCP domain.
 * <p><b>Experimental:</b> this part of CDP may change without notice.
 * @see <a href="https://chromium.googlesource.com/chromium/src/+/refs/tags/150.0.7871.213/third_party/blink/public/devtools_protocol/domains/WebMCP.pdl">Pinned protocol source</a>
 */
@Generated("./mvnw generate-sources -pl cef4j-cdp -Dcef.version=150.0.18+gdb11278+chromium-150.0.7871.213")
@SuppressWarnings({"EscapedEntity", "InvalidParam", "JavaLangClash", "MissingSummary", "Unchecked", "UnusedMethod"})
@NullableBoundary("CDP wire objects use null for absent optional fields")
public final class WebMCP {
    private WebMCP() {}
    private static final CdpField<WebMCP.Annotation> ANNOTATIONS = CdpField.of("annotations", value -> java.util.Objects.requireNonNull(WebMCP.Annotation.fromMap(CdpObject.requireObjectMap(value))));
    private static final CdpField<DOM.BackendNodeId> BACKEND_NODE_ID = CdpField.of("backendNodeId", value -> new DOM.BackendNodeId(((Number) value).longValue()));
    private static final CdpField<Runtime.RemoteObject> EXCEPTION = CdpField.of("exception", value -> java.util.Objects.requireNonNull(Runtime.RemoteObject.fromMap(CdpObject.requireObjectMap(value))));
    private static final CdpField<Page.FrameId> FRAME_ID = CdpField.of("frameId", value -> new Page.FrameId((String) value));
    private static final CdpField<String> INVOCATION_ID = CdpField.of("invocationId", value -> (String) value);
    private static final CdpField<Runtime.StackTrace> STACK_TRACE = CdpField.of("stackTrace", value -> java.util.Objects.requireNonNull(Runtime.StackTrace.fromMap(CdpObject.requireObjectMap(value))));
    private static final CdpField<WebMCP.InvocationStatus> STATUS = CdpField.of("status", value -> WebMCP.InvocationStatus.of((String) value));
    private static final CdpField<java.util.List<WebMCP.Tool>> TOOLS = CdpField.of("tools", value -> CdpObject.requireList(value, element0 -> java.util.Objects.requireNonNull(WebMCP.Tool.fromMap(CdpObject.requireObjectMap(element0)))));
    private static final CdpField<java.util.List<WebMCP.RemovedTool>> TOOLS_2 = CdpField.of("tools", value -> CdpObject.requireList(value, element0 -> java.util.Objects.requireNonNull(WebMCP.RemovedTool.fromMap(CdpObject.requireObjectMap(element0)))));
    /**
     * Tool annotations
     */
    public static final class Annotation extends CdpObject {
        public Annotation() {}
        private Annotation(Map<String, Object> values) { super(values); }
        public static Annotation fromMap(Map<String, Object> values) {
            return new Annotation(values);
        }
        /**
         * A hint indicating that the tool does not modify any state.
         * @return the protocol field value, empty when absent
         */
        public Optional<Boolean> readOnly() {
            return optionalBoolean("readOnly");
        }
        /**
         * A hint indicating that the tool output may contain untrusted content, ex: UGC, 3rd party data.
         * @return the protocol field value, empty when absent
         */
        public Optional<Boolean> untrustedContent() {
            return optionalBoolean("untrustedContent");
        }
        /**
         * If the declarative tool was declared with the autosubmit attribute.
         * @return the protocol field value, empty when absent
         */
        public Optional<Boolean> autosubmit() {
            return optionalBoolean("autosubmit");
        }
        /**
         * A hint indicating that the tool does not modify any state.
         * @param readOnly field value
         * @return this model
         */
        public Annotation readOnly(boolean readOnly) {
            set("readOnly", readOnly);
            return this;
        }
        /**
         * A hint indicating that the tool output may contain untrusted content, ex: UGC, 3rd party data.
         * @param untrustedContent field value
         * @return this model
         */
        public Annotation untrustedContent(boolean untrustedContent) {
            set("untrustedContent", untrustedContent);
            return this;
        }
        /**
         * If the declarative tool was declared with the autosubmit attribute.
         * @param autosubmit field value
         * @return this model
         */
        public Annotation autosubmit(boolean autosubmit) {
            set("autosubmit", autosubmit);
            return this;
        }
    }
    /**
     * Represents the status of a tool invocation.
     */
    public enum InvocationStatus implements CdpValue<String> {
        COMPLETED("Completed"),
        CANCELED("Canceled"),
        ERROR("Error");
        public final String value;
        InvocationStatus(String value) { this.value = value; }
        @Override public String value() { return value; }
        public static InvocationStatus of(String value) {
            for (InvocationStatus constant : values()) {
                if (constant.value.equals(value)) return constant;
            }
            throw new IllegalArgumentException("Unknown InvocationStatus value: " + value);
        }
    }
    /**
     * Definition of a tool that can be invoked.
     */
    public static final class Tool extends CdpObject {
        public Tool() {}
        private Tool(Map<String, Object> values) { super(values); }
        public static Tool fromMap(Map<String, Object> values) {
            return new Tool(values);
        }
        /**
         * Tool name.
         * @return the protocol field value
         */
        public String name() {
            return requireString("name");
        }
        /**
         * Tool description.
         * @return the protocol field value
         */
        public String description() {
            return requireString("description");
        }
        /**
         * Schema for the tool&#x27;s input parameters.
         * @return the protocol field value, empty when absent
         */
        public Optional<java.util.Map<String, Object>> inputSchema() {
            return optionalObject("inputSchema");
        }
        /**
         * Optional annotations for the tool.
         * @return the protocol field value, empty when absent
         */
        public Optional<WebMCP.Annotation> annotations() {
            return optional(ANNOTATIONS);
        }
        /**
         * Frame identifier associated with the tool registration.
         * @return the protocol field value
         */
        public Page.FrameId frameId() {
            return require(FRAME_ID);
        }
        /**
         * Optional node ID for declarative tools.
         * @return the protocol field value, empty when absent
         */
        public Optional<DOM.BackendNodeId> backendNodeId() {
            return optional(BACKEND_NODE_ID);
        }
        /**
         * The stack trace at the time of the registration.
         * @return the protocol field value, empty when absent
         */
        public Optional<Runtime.StackTrace> stackTrace() {
            return optional(STACK_TRACE);
        }
        /**
         * Tool name.
         * @param name field value
         * @return this model
         */
        public Tool name(String name) {
            set("name", name);
            return this;
        }
        /**
         * Tool description.
         * @param description field value
         * @return this model
         */
        public Tool description(String description) {
            set("description", description);
            return this;
        }
        /**
         * Schema for the tool&#x27;s input parameters.
         * @param inputSchema field value
         * @return this model
         */
        public Tool inputSchema(java.util.Map<String, Object> inputSchema) {
            set("inputSchema", inputSchema);
            return this;
        }
        /**
         * Optional annotations for the tool.
         * @param annotations field value
         * @return this model
         */
        public Tool annotations(WebMCP.Annotation annotations) {
            set("annotations", annotations);
            return this;
        }
        /**
         * Frame identifier associated with the tool registration.
         * @param frameId field value
         * @return this model
         */
        public Tool frameId(Page.FrameId frameId) {
            set("frameId", frameId);
            return this;
        }
        /**
         * Optional node ID for declarative tools.
         * @param backendNodeId field value
         * @return this model
         */
        public Tool backendNodeId(DOM.BackendNodeId backendNodeId) {
            set("backendNodeId", backendNodeId);
            return this;
        }
        /**
         * The stack trace at the time of the registration.
         * @param stackTrace field value
         * @return this model
         */
        public Tool stackTrace(Runtime.StackTrace stackTrace) {
            set("stackTrace", stackTrace);
            return this;
        }
    }
    /**
     * Definition of a tool that was removed.
     */
    public static final class RemovedTool extends CdpObject {
        public RemovedTool() {}
        private RemovedTool(Map<String, Object> values) { super(values); }
        public static RemovedTool fromMap(Map<String, Object> values) {
            return new RemovedTool(values);
        }
        /**
         * Tool name.
         * @return the protocol field value
         */
        public String name() {
            return requireString("name");
        }
        /**
         * Frame identifier associated with the tool registration.
         * @return the protocol field value
         */
        public Page.FrameId frameId() {
            return require(FRAME_ID);
        }
        /**
         * Tool name.
         * @param name field value
         * @return this model
         */
        public RemovedTool name(String name) {
            set("name", name);
            return this;
        }
        /**
         * Frame identifier associated with the tool registration.
         * @param frameId field value
         * @return this model
         */
        public RemovedTool frameId(Page.FrameId frameId) {
            set("frameId", frameId);
            return this;
        }
    }
    /**
     * Invokes a registered tool.
     */
    public static final class InvokeToolRequest extends CdpObject {
        public InvokeToolRequest() {}
        public static InvokeToolRequest fromMap(Map<String, Object> values) {
            final InvokeToolRequest instance_ = new InvokeToolRequest();
            if (values != null) instance_.values.putAll(values);
            return instance_;
        }
        /**
         * Frame in which to invoke the tool.
         * @return the protocol field value
         */
        public Page.FrameId frameId() {
            return require(FRAME_ID);
        }
        /**
         * Name of the tool to invoke.
         * @return the protocol field value
         */
        public String toolName() {
            return requireString("toolName");
        }
        /**
         * Input parameters for the tool, matching the tool&#x27;s inputSchema.
         * @return the protocol field value
         */
        public java.util.Map<String, Object> input() {
            return requireObject("input");
        }
        /**
         * Frame in which to invoke the tool.
         * @param frameId field value
         * @return this model
         */
        public InvokeToolRequest frameId(Page.FrameId frameId) {
            set("frameId", frameId);
            return this;
        }
        /**
         * Name of the tool to invoke.
         * @param toolName field value
         * @return this model
         */
        public InvokeToolRequest toolName(String toolName) {
            set("toolName", toolName);
            return this;
        }
        /**
         * Input parameters for the tool, matching the tool&#x27;s inputSchema.
         * @param input field value
         * @return this model
         */
        public InvokeToolRequest input(java.util.Map<String, Object> input) {
            set("input", input);
            return this;
        }
    }
    /**
     * Cancels a pending tool invocation.
     */
    public static final class CancelInvocationRequest extends CdpObject {
        public CancelInvocationRequest() {}
        public static CancelInvocationRequest fromMap(Map<String, Object> values) {
            final CancelInvocationRequest instance_ = new CancelInvocationRequest();
            if (values != null) instance_.values.putAll(values);
            return instance_;
        }
        /**
         * Invocation identifier to cancel.
         * @return the protocol field value
         */
        public String invocationId() {
            return requireString("invocationId");
        }
        /**
         * Invocation identifier to cancel.
         * @param invocationId field value
         * @return this model
         */
        public CancelInvocationRequest invocationId(String invocationId) {
            set("invocationId", invocationId);
            return this;
        }
    }
    /**
     * Event fired when new tools are added.
     */
    public static final class ToolsAddedEvent extends CdpObject {
        public ToolsAddedEvent() {}
        private ToolsAddedEvent(Map<String, Object> values) { super(values); }
        public static ToolsAddedEvent fromMap(Map<String, Object> values) {
            return new ToolsAddedEvent(values);
        }
        /**
         * Array of tools that were added.
         * @return the protocol field value
         */
        public java.util.List<WebMCP.Tool> tools() {
            return require(TOOLS);
        }
        /**
         * Array of tools that were added.
         * @param tools field value
         * @return this model
         */
        public ToolsAddedEvent tools(java.util.List<WebMCP.Tool> tools) {
            set("tools", tools);
            return this;
        }
    }
    /**
     * Event fired when tools are removed.
     */
    public static final class ToolsRemovedEvent extends CdpObject {
        public ToolsRemovedEvent() {}
        private ToolsRemovedEvent(Map<String, Object> values) { super(values); }
        public static ToolsRemovedEvent fromMap(Map<String, Object> values) {
            return new ToolsRemovedEvent(values);
        }
        /**
         * Array of tools that were removed.
         * @return the protocol field value
         */
        public java.util.List<WebMCP.RemovedTool> tools() {
            return require(TOOLS_2);
        }
        /**
         * Array of tools that were removed.
         * @param tools field value
         * @return this model
         */
        public ToolsRemovedEvent tools(java.util.List<WebMCP.RemovedTool> tools) {
            set("tools", tools);
            return this;
        }
    }
    /**
     * Event fired when a tool invocation starts.
     */
    public static final class ToolInvokedEvent extends CdpObject {
        public ToolInvokedEvent() {}
        private ToolInvokedEvent(Map<String, Object> values) { super(values); }
        public static ToolInvokedEvent fromMap(Map<String, Object> values) {
            return new ToolInvokedEvent(values);
        }
        /**
         * Name of the tool to invoke.
         * @return the protocol field value
         */
        public String toolName() {
            return requireString("toolName");
        }
        /**
         * Frame id
         * @return the protocol field value
         */
        public Page.FrameId frameId() {
            return require(FRAME_ID);
        }
        /**
         * Invocation identifier.
         * @return the protocol field value
         */
        public String invocationId() {
            return requireString("invocationId");
        }
        /**
         * The input parameters used for the invocation.
         * @return the protocol field value
         */
        public String input() {
            return requireString("input");
        }
        /**
         * Name of the tool to invoke.
         * @param toolName field value
         * @return this model
         */
        public ToolInvokedEvent toolName(String toolName) {
            set("toolName", toolName);
            return this;
        }
        /**
         * Frame id
         * @param frameId field value
         * @return this model
         */
        public ToolInvokedEvent frameId(Page.FrameId frameId) {
            set("frameId", frameId);
            return this;
        }
        /**
         * Invocation identifier.
         * @param invocationId field value
         * @return this model
         */
        public ToolInvokedEvent invocationId(String invocationId) {
            set("invocationId", invocationId);
            return this;
        }
        /**
         * The input parameters used for the invocation.
         * @param input field value
         * @return this model
         */
        public ToolInvokedEvent input(String input) {
            set("input", input);
            return this;
        }
    }
    /**
     * Event fired when a tool invocation completes or fails.
     */
    public static final class ToolRespondedEvent extends CdpObject {
        public ToolRespondedEvent() {}
        private ToolRespondedEvent(Map<String, Object> values) { super(values); }
        public static ToolRespondedEvent fromMap(Map<String, Object> values) {
            return new ToolRespondedEvent(values);
        }
        /**
         * Invocation identifier.
         * @return the protocol field value
         */
        public String invocationId() {
            return requireString("invocationId");
        }
        /**
         * Status of the invocation.
         * @return the protocol field value
         */
        public WebMCP.InvocationStatus status() {
            return require(STATUS);
        }
        /**
         * Output or error delivered as delivered to the agent. Missing if {@code status} is anything other than Completed. Note: The output is untrusted and poses a prompt injection risk. Clients should treat this as potentially malicious user input.
         * @return the protocol field value, empty when absent
         */
        public Optional<Object> output() {
            return raw("output");
        }
        /**
         * Error text for protocol users.
         * @return the protocol field value, empty when absent
         */
        public Optional<String> errorText() {
            return optionalString("errorText");
        }
        /**
         * The exception object, if the javascript tool threw an error&gt;
         * @return the protocol field value, empty when absent
         */
        public Optional<Runtime.RemoteObject> exception() {
            return optional(EXCEPTION);
        }
        /**
         * Invocation identifier.
         * @param invocationId field value
         * @return this model
         */
        public ToolRespondedEvent invocationId(String invocationId) {
            set("invocationId", invocationId);
            return this;
        }
        /**
         * Status of the invocation.
         * @param status field value
         * @return this model
         */
        public ToolRespondedEvent status(WebMCP.InvocationStatus status) {
            set("status", status);
            return this;
        }
        /**
         * Output or error delivered as delivered to the agent. Missing if {@code status} is anything other than Completed. Note: The output is untrusted and poses a prompt injection risk. Clients should treat this as potentially malicious user input.
         * @param output field value
         * @return this model
         */
        public ToolRespondedEvent output(Object output) {
            set("output", output);
            return this;
        }
        /**
         * Error text for protocol users.
         * @param errorText field value
         * @return this model
         */
        public ToolRespondedEvent errorText(String errorText) {
            set("errorText", errorText);
            return this;
        }
        /**
         * The exception object, if the javascript tool threw an error&gt;
         * @param exception field value
         * @return this model
         */
        public ToolRespondedEvent exception(Runtime.RemoteObject exception) {
            set("exception", exception);
            return this;
        }
    }
    public static final class Client extends CdpDomainClient {
        public Client(CdpClient client) { super(client); }
        /**
         * Enables the WebMCP domain, allowing events to be sent. Enabling the domain will trigger a toolsAdded event for all currently registered tools.
         * @return a stage completing when the command completes
         */
        public CompletionStage<Void> enable() {
            return call("WebMCP.enable");
        }
        /**
         * Disables the WebMCP domain.
         * @return a stage completing when the command completes
         */
        public CompletionStage<Void> disable() {
            return call("WebMCP.disable");
        }
        /**
         * Invokes a registered tool.
         * @param frameId protocol value
         * @param toolName protocol value
         * @param input protocol value
         * @return a stage completing with the command result
         */
        public CompletionStage<String> invokeTool(Page.FrameId frameId, String toolName, java.util.Map<String, Object> input) {
            return call("WebMCP.invokeTool", params("frameId", frameId, "toolName", toolName, "input", input), INVOCATION_ID::require);
        }
        /**
         * Invokes a registered tool.
         * @param request request parameters
         * @return a stage completing with the command result
         */
        public CompletionStage<String> invokeTool(InvokeToolRequest request) {
            return call("WebMCP.invokeTool", request, INVOCATION_ID::require);
        }
        /**
         * Cancels a pending tool invocation.
         * @param invocationId protocol value
         * @return a stage completing when the command completes
         */
        public CompletionStage<Void> cancelInvocation(String invocationId) {
            return call("WebMCP.cancelInvocation", params("invocationId", invocationId));
        }
        /**
         * Cancels a pending tool invocation.
         * @param request request parameters
         * @return a stage completing when the command completes
         */
        public CompletionStage<Void> cancelInvocation(CancelInvocationRequest request) {
            return call("WebMCP.cancelInvocation", request);
        }
        /**
         * Event fired when new tools are added.
         * @param handler event callback
         * @return a removable subscription
         */
        public CdpSubscription onToolsAdded(Consumer<ToolsAddedEvent> handler) {
            return on("WebMCP.toolsAdded", ToolsAddedEvent::fromMap, handler);
        }
        /**
         * Event fired when tools are removed.
         * @param handler event callback
         * @return a removable subscription
         */
        public CdpSubscription onToolsRemoved(Consumer<ToolsRemovedEvent> handler) {
            return on("WebMCP.toolsRemoved", ToolsRemovedEvent::fromMap, handler);
        }
        /**
         * Event fired when a tool invocation starts.
         * @param handler event callback
         * @return a removable subscription
         */
        public CdpSubscription onToolInvoked(Consumer<ToolInvokedEvent> handler) {
            return on("WebMCP.toolInvoked", ToolInvokedEvent::fromMap, handler);
        }
        /**
         * Event fired when a tool invocation completes or fails.
         * @param handler event callback
         * @return a removable subscription
         */
        public CdpSubscription onToolResponded(Consumer<ToolRespondedEvent> handler) {
            return on("WebMCP.toolResponded", ToolRespondedEvent::fromMap, handler);
        }
    }
}
