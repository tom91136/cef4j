// GENERATED - do not edit. Regenerate via: ./mvnw generate-sources -pl cef4j-cdp -Dcef.version=150.0.18+gdb11278+chromium-150.0.7871.213
package net.kurobako.cef4j.cdp.generated;

import java.util.Map;
import java.util.Optional;
import java.util.OptionalLong;
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
import org.jspecify.annotations.Nullable;

/**
 * Supports additional targets discovery and allows to attach to them.
 * @see <a href="https://chromium.googlesource.com/chromium/src/+/refs/tags/150.0.7871.213/third_party/blink/public/devtools_protocol/domains/Target.pdl">Pinned protocol source</a>
 */
@Generated("./mvnw generate-sources -pl cef4j-cdp -Dcef.version=150.0.18+gdb11278+chromium-150.0.7871.213")
@SuppressWarnings({"EscapedEntity", "InvalidParam", "JavaLangClash", "MissingSummary", "Unchecked", "UnusedMethod"})
@NullableBoundary("CDP wire objects use null for absent optional fields")
public final class Target {
    private Target() {}
    private static final CdpField<Browser.BrowserContextID> BROWSER_CONTEXT_ID = CdpField.of("browserContextId", value -> new Browser.BrowserContextID((String) value));
    private static final CdpField<java.util.List<Browser.BrowserContextID>> BROWSER_CONTEXT_IDS = CdpField.of("browserContextIds", value -> CdpObject.requireList(value, element0 -> new Browser.BrowserContextID((String) element0)));
    private static final CdpField<Browser.BrowserContextID> DEFAULT_BROWSER_CONTEXT_ID = CdpField.of("defaultBrowserContextId", value -> new Browser.BrowserContextID((String) value));
    private static final CdpField<java.util.List<Target.FilterEntry>> FILTER = CdpField.of("filter", value -> CdpObject.requireList(value, element0 -> java.util.Objects.requireNonNull(Target.FilterEntry.fromMap(CdpObject.requireObjectMap(element0)))));
    private static final CdpField<java.util.List<Target.RemoteLocation>> LOCATIONS = CdpField.of("locations", value -> CdpObject.requireList(value, element0 -> java.util.Objects.requireNonNull(Target.RemoteLocation.fromMap(CdpObject.requireObjectMap(element0)))));
    private static final CdpField<Page.FrameId> OPENER_FRAME_ID = CdpField.of("openerFrameId", value -> new Page.FrameId((String) value));
    private static final CdpField<Target.TargetID> OPENER_ID = CdpField.of("openerId", value -> new Target.TargetID((String) value));
    private static final CdpField<java.util.List<String>> ORIGINS_WITH_UNIVERSAL_NETWORK_ACCESS = CdpField.of("originsWithUniversalNetworkAccess", value -> CdpObject.requireList(value, element0 -> (String) element0));
    private static final CdpField<Page.FrameId> PARENT_FRAME_ID = CdpField.of("parentFrameId", value -> new Page.FrameId((String) value));
    private static final CdpField<Target.TargetID> PARENT_ID = CdpField.of("parentId", value -> new Target.TargetID((String) value));
    private static final CdpField<Target.SessionID> SESSION_ID = CdpField.of("sessionId", value -> new Target.SessionID((String) value));
    private static final CdpField<Boolean> SUCCESS = CdpField.of("success", value -> (Boolean) value);
    private static final CdpField<Target.TargetID> TARGET_ID = CdpField.of("targetId", value -> new Target.TargetID((String) value));
    private static final CdpField<Target.TargetInfo> TARGET_INFO = CdpField.of("targetInfo", value -> java.util.Objects.requireNonNull(Target.TargetInfo.fromMap(CdpObject.requireObjectMap(value))));
    private static final CdpField<java.util.List<Target.TargetInfo>> TARGET_INFOS = CdpField.of("targetInfos", value -> CdpObject.requireList(value, element0 -> java.util.Objects.requireNonNull(Target.TargetInfo.fromMap(CdpObject.requireObjectMap(element0)))));
    private static final CdpField<Target.WindowState> WINDOW_STATE = CdpField.of("windowState", value -> Target.WindowState.of((String) value));
    /**
     * Tagged String wire value for TargetID.
     */
    public static final class TargetID implements CdpValue<String> {
        public final String value;
        public TargetID(String value) { this.value = java.util.Objects.requireNonNull(value); }
        @Override public String value() { return value; }
        @Override public boolean equals(@org.jspecify.annotations.Nullable Object other) {
            if (this == other) return true;
            if (!(other instanceof TargetID)) return false;
            return value.equals(((TargetID) other).value);
        }
        @Override public int hashCode() { return value.hashCode(); }
        @Override public String toString() { return "TargetID(" + value + ")"; }
    }
    /**
     * Unique identifier of attached debugging session.
     */
    public static final class SessionID implements CdpValue<String> {
        public final String value;
        public SessionID(String value) { this.value = java.util.Objects.requireNonNull(value); }
        @Override public String value() { return value; }
        @Override public boolean equals(@org.jspecify.annotations.Nullable Object other) {
            if (this == other) return true;
            if (!(other instanceof SessionID)) return false;
            return value.equals(((SessionID) other).value);
        }
        @Override public int hashCode() { return value.hashCode(); }
        @Override public String toString() { return "SessionID(" + value + ")"; }
    }
    /**
     */
    public static final class TargetInfo extends CdpObject {
        public TargetInfo() {}
        private TargetInfo(Map<String, Object> values) { super(values); }
        public static TargetInfo fromMap(Map<String, Object> values) {
            return new TargetInfo(values);
        }
        /**
         * Returns the targetId field.
         * @return the protocol field value
         */
        public Target.TargetID targetId() {
            return require(TARGET_ID);
        }
        /**
         * List of types: https://source.chromium.org/chromium/chromium/src/+/main:content/browser/devtools/devtools_agent_host_impl.cc?ss=chromium&amp;q=f:devtools%20-f:out%20%22::kTypeTab%5B%5D%22
         * @return the protocol field value
         */
        public String type() {
            return requireString("type");
        }
        /**
         * Returns the title field.
         * @return the protocol field value
         */
        public String title() {
            return requireString("title");
        }
        /**
         * Returns the url field.
         * @return the protocol field value
         */
        public String url() {
            return requireString("url");
        }
        /**
         * Whether the target has an attached client.
         * @return the protocol field value
         */
        public boolean attached() {
            return requireBoolean("attached");
        }
        /**
         * Id of the parent target, if any. For example, &quot;iframe&quot; target may have a &quot;page&quot; parent.
         * @return the protocol field value, empty when absent
         */
        public Optional<Target.TargetID> parentId() {
            return optional(PARENT_ID);
        }
        /**
         * Opener target Id
         * @return the protocol field value, empty when absent
         */
        public Optional<Target.TargetID> openerId() {
            return optional(OPENER_ID);
        }
        /**
         * Whether the target has access to the originating window.
         * <p><b>Experimental:</b> this part of CDP may change without notice.
         * @return the protocol field value
         */
        public boolean canAccessOpener() {
            return requireBoolean("canAccessOpener");
        }
        /**
         * Frame id of originating window (is only set if target has an opener).
         * <p><b>Experimental:</b> this part of CDP may change without notice.
         * @return the protocol field value, empty when absent
         */
        public Optional<Page.FrameId> openerFrameId() {
            return optional(OPENER_FRAME_ID);
        }
        /**
         * Id of the parent frame, present for &quot;iframe&quot; and &quot;worker&quot; targets. For nested workers, this is the &quot;ancestor&quot; frame that created the first worker in the nested chain.
         * <p><b>Experimental:</b> this part of CDP may change without notice.
         * @return the protocol field value, empty when absent
         */
        public Optional<Page.FrameId> parentFrameId() {
            return optional(PARENT_FRAME_ID);
        }
        /**
         * Returns the browserContextId field.
         * <p><b>Experimental:</b> this part of CDP may change without notice.
         * @return the protocol field value, empty when absent
         */
        public Optional<Browser.BrowserContextID> browserContextId() {
            return optional(BROWSER_CONTEXT_ID);
        }
        /**
         * Provides additional details for specific target types. For example, for the type of &quot;page&quot;, this may be set to &quot;prerender&quot;.
         * <p><b>Experimental:</b> this part of CDP may change without notice.
         * @return the protocol field value, empty when absent
         */
        public Optional<String> subtype() {
            return optionalString("subtype");
        }
        /**
         * Embedder-specific target metadata. This is only set for targets of type &quot;tab&quot;.
         * <p><b>Experimental:</b> this part of CDP may change without notice.
         * @return the protocol field value, empty when absent
         */
        public Optional<java.util.Map<String, Object>> embedderData() {
            return optionalObject("embedderData");
        }
        /**
         * Sets the targetId field.
         * @param targetId field value
         * @return this model
         */
        public TargetInfo targetId(Target.TargetID targetId) {
            set("targetId", targetId);
            return this;
        }
        /**
         * List of types: https://source.chromium.org/chromium/chromium/src/+/main:content/browser/devtools/devtools_agent_host_impl.cc?ss=chromium&amp;q=f:devtools%20-f:out%20%22::kTypeTab%5B%5D%22
         * @param type field value
         * @return this model
         */
        public TargetInfo type(String type) {
            set("type", type);
            return this;
        }
        /**
         * Sets the title field.
         * @param title field value
         * @return this model
         */
        public TargetInfo title(String title) {
            set("title", title);
            return this;
        }
        /**
         * Sets the url field.
         * @param url field value
         * @return this model
         */
        public TargetInfo url(String url) {
            set("url", url);
            return this;
        }
        /**
         * Whether the target has an attached client.
         * @param attached field value
         * @return this model
         */
        public TargetInfo attached(boolean attached) {
            set("attached", attached);
            return this;
        }
        /**
         * Id of the parent target, if any. For example, &quot;iframe&quot; target may have a &quot;page&quot; parent.
         * @param parentId field value
         * @return this model
         */
        public TargetInfo parentId(Target.TargetID parentId) {
            set("parentId", parentId);
            return this;
        }
        /**
         * Opener target Id
         * @param openerId field value
         * @return this model
         */
        public TargetInfo openerId(Target.TargetID openerId) {
            set("openerId", openerId);
            return this;
        }
        /**
         * Whether the target has access to the originating window.
         * <p><b>Experimental:</b> this part of CDP may change without notice.
         * @param canAccessOpener field value
         * @return this model
         */
        public TargetInfo canAccessOpener(boolean canAccessOpener) {
            set("canAccessOpener", canAccessOpener);
            return this;
        }
        /**
         * Frame id of originating window (is only set if target has an opener).
         * <p><b>Experimental:</b> this part of CDP may change without notice.
         * @param openerFrameId field value
         * @return this model
         */
        public TargetInfo openerFrameId(Page.FrameId openerFrameId) {
            set("openerFrameId", openerFrameId);
            return this;
        }
        /**
         * Id of the parent frame, present for &quot;iframe&quot; and &quot;worker&quot; targets. For nested workers, this is the &quot;ancestor&quot; frame that created the first worker in the nested chain.
         * <p><b>Experimental:</b> this part of CDP may change without notice.
         * @param parentFrameId field value
         * @return this model
         */
        public TargetInfo parentFrameId(Page.FrameId parentFrameId) {
            set("parentFrameId", parentFrameId);
            return this;
        }
        /**
         * Sets the browserContextId field.
         * <p><b>Experimental:</b> this part of CDP may change without notice.
         * @param browserContextId field value
         * @return this model
         */
        public TargetInfo browserContextId(Browser.BrowserContextID browserContextId) {
            set("browserContextId", browserContextId);
            return this;
        }
        /**
         * Provides additional details for specific target types. For example, for the type of &quot;page&quot;, this may be set to &quot;prerender&quot;.
         * <p><b>Experimental:</b> this part of CDP may change without notice.
         * @param subtype field value
         * @return this model
         */
        public TargetInfo subtype(String subtype) {
            set("subtype", subtype);
            return this;
        }
        /**
         * Embedder-specific target metadata. This is only set for targets of type &quot;tab&quot;.
         * <p><b>Experimental:</b> this part of CDP may change without notice.
         * @param embedderData field value
         * @return this model
         */
        public TargetInfo embedderData(java.util.Map<String, Object> embedderData) {
            set("embedderData", embedderData);
            return this;
        }
    }
    /**
     * A filter used by target query/discovery/auto-attach operations.
     * <p><b>Experimental:</b> this part of CDP may change without notice.
     */
    public static final class FilterEntry extends CdpObject {
        public FilterEntry() {}
        private FilterEntry(Map<String, Object> values) { super(values); }
        public static FilterEntry fromMap(Map<String, Object> values) {
            return new FilterEntry(values);
        }
        /**
         * If set, causes exclusion of matching targets from the list.
         * @return the protocol field value, empty when absent
         */
        public Optional<Boolean> exclude() {
            return optionalBoolean("exclude");
        }
        /**
         * If not present, matches any type.
         * @return the protocol field value, empty when absent
         */
        public Optional<String> type() {
            return optionalString("type");
        }
        /**
         * If set, causes exclusion of matching targets from the list.
         * @param exclude field value
         * @return this model
         */
        public FilterEntry exclude(boolean exclude) {
            set("exclude", exclude);
            return this;
        }
        /**
         * If not present, matches any type.
         * @param type field value
         * @return this model
         */
        public FilterEntry type(String type) {
            set("type", type);
            return this;
        }
    }
    /**
     * <p><b>Experimental:</b> this part of CDP may change without notice.
     */
    public static final class RemoteLocation extends CdpObject {
        public RemoteLocation() {}
        private RemoteLocation(Map<String, Object> values) { super(values); }
        public static RemoteLocation fromMap(Map<String, Object> values) {
            return new RemoteLocation(values);
        }
        /**
         * Returns the host field.
         * @return the protocol field value
         */
        public String host() {
            return requireString("host");
        }
        /**
         * Returns the port field.
         * @return the protocol field value
         */
        public long port() {
            return requireLong("port");
        }
        /**
         * Sets the host field.
         * @param host field value
         * @return this model
         */
        public RemoteLocation host(String host) {
            set("host", host);
            return this;
        }
        /**
         * Sets the port field.
         * @param port field value
         * @return this model
         */
        public RemoteLocation port(long port) {
            set("port", port);
            return this;
        }
    }
    /**
     * The state of the target window.
     * <p><b>Experimental:</b> this part of CDP may change without notice.
     */
    public enum WindowState implements CdpValue<String> {
        NORMAL("normal"),
        MINIMIZED("minimized"),
        MAXIMIZED("maximized"),
        FULLSCREEN("fullscreen");
        public final String value;
        WindowState(String value) { this.value = value; }
        @Override public String value() { return value; }
        public static WindowState of(String value) {
            for (WindowState constant : values()) {
                if (constant.value.equals(value)) return constant;
            }
            throw new IllegalArgumentException("Unknown WindowState value: " + value);
        }
    }
    /**
     * Activates (focuses) the target.
     */
    public static final class ActivateTargetRequest extends CdpObject {
        public ActivateTargetRequest() {}
        public static ActivateTargetRequest fromMap(Map<String, Object> values) {
            final ActivateTargetRequest instance_ = new ActivateTargetRequest();
            if (values != null) instance_.values.putAll(values);
            return instance_;
        }
        /**
         * Returns the targetId field.
         * @return the protocol field value
         */
        public Target.TargetID targetId() {
            return require(TARGET_ID);
        }
        /**
         * Sets the targetId field.
         * @param targetId field value
         * @return this model
         */
        public ActivateTargetRequest targetId(Target.TargetID targetId) {
            set("targetId", targetId);
            return this;
        }
    }
    /**
     * Attaches to the target with given id.
     */
    public static final class AttachToTargetRequest extends CdpObject {
        public AttachToTargetRequest() {}
        public static AttachToTargetRequest fromMap(Map<String, Object> values) {
            final AttachToTargetRequest instance_ = new AttachToTargetRequest();
            if (values != null) instance_.values.putAll(values);
            return instance_;
        }
        /**
         * Returns the targetId field.
         * @return the protocol field value
         */
        public Target.TargetID targetId() {
            return require(TARGET_ID);
        }
        /**
         * Enables &quot;flat&quot; access to the session via specifying sessionId attribute in the commands. We plan to make this the default, deprecate non-flattened mode, and eventually retire it. See crbug.com/991325.
         * @return the protocol field value, empty when absent
         */
        public Optional<Boolean> flatten() {
            return optionalBoolean("flatten");
        }
        /**
         * Sets the targetId field.
         * @param targetId field value
         * @return this model
         */
        public AttachToTargetRequest targetId(Target.TargetID targetId) {
            set("targetId", targetId);
            return this;
        }
        /**
         * Enables &quot;flat&quot; access to the session via specifying sessionId attribute in the commands. We plan to make this the default, deprecate non-flattened mode, and eventually retire it. See crbug.com/991325.
         * @param flatten field value
         * @return this model
         */
        public AttachToTargetRequest flatten(boolean flatten) {
            set("flatten", flatten);
            return this;
        }
    }
    /**
     * Closes the target. If the target is a page that gets closed too.
     */
    public static final class CloseTargetRequest extends CdpObject {
        public CloseTargetRequest() {}
        public static CloseTargetRequest fromMap(Map<String, Object> values) {
            final CloseTargetRequest instance_ = new CloseTargetRequest();
            if (values != null) instance_.values.putAll(values);
            return instance_;
        }
        /**
         * Returns the targetId field.
         * @return the protocol field value
         */
        public Target.TargetID targetId() {
            return require(TARGET_ID);
        }
        /**
         * Sets the targetId field.
         * @param targetId field value
         * @return this model
         */
        public CloseTargetRequest targetId(Target.TargetID targetId) {
            set("targetId", targetId);
            return this;
        }
    }
    /**
     * Inject object to the target&#x27;s main frame that provides a communication channel with browser target.
     * <p>Injected object will be available as {@code window[bindingName]}.
     * <p>The object has the following API: - {@code binding.send(json)} - a method to send messages over the remote debugging protocol - {@code binding.onmessage = json =&gt; handleMessage(json)} - a callback that will be called for the protocol notifications and command responses.
     * <p><b>Experimental:</b> this part of CDP may change without notice.
     */
    public static final class ExposeDevToolsProtocolRequest extends CdpObject {
        public ExposeDevToolsProtocolRequest() {}
        public static ExposeDevToolsProtocolRequest fromMap(Map<String, Object> values) {
            final ExposeDevToolsProtocolRequest instance_ = new ExposeDevToolsProtocolRequest();
            if (values != null) instance_.values.putAll(values);
            return instance_;
        }
        /**
         * Returns the targetId field.
         * @return the protocol field value
         */
        public Target.TargetID targetId() {
            return require(TARGET_ID);
        }
        /**
         * Binding name, &#x27;cdp&#x27; if not specified.
         * @return the protocol field value, empty when absent
         */
        public Optional<String> bindingName() {
            return optionalString("bindingName");
        }
        /**
         * If true, inherits the current root session&#x27;s permissions (default: false).
         * @return the protocol field value, empty when absent
         */
        public Optional<Boolean> inheritPermissions() {
            return optionalBoolean("inheritPermissions");
        }
        /**
         * Sets the targetId field.
         * @param targetId field value
         * @return this model
         */
        public ExposeDevToolsProtocolRequest targetId(Target.TargetID targetId) {
            set("targetId", targetId);
            return this;
        }
        /**
         * Binding name, &#x27;cdp&#x27; if not specified.
         * @param bindingName field value
         * @return this model
         */
        public ExposeDevToolsProtocolRequest bindingName(String bindingName) {
            set("bindingName", bindingName);
            return this;
        }
        /**
         * If true, inherits the current root session&#x27;s permissions (default: false).
         * @param inheritPermissions field value
         * @return this model
         */
        public ExposeDevToolsProtocolRequest inheritPermissions(boolean inheritPermissions) {
            set("inheritPermissions", inheritPermissions);
            return this;
        }
    }
    /**
     * Creates a new empty BrowserContext. Similar to an incognito profile but you can have more than one.
     */
    public static final class CreateBrowserContextRequest extends CdpObject {
        public CreateBrowserContextRequest() {}
        public static CreateBrowserContextRequest fromMap(Map<String, Object> values) {
            final CreateBrowserContextRequest instance_ = new CreateBrowserContextRequest();
            if (values != null) instance_.values.putAll(values);
            return instance_;
        }
        /**
         * If specified, disposes this context when debugging session disconnects.
         * <p><b>Experimental:</b> this part of CDP may change without notice.
         * @return the protocol field value, empty when absent
         */
        public Optional<Boolean> disposeOnDetach() {
            return optionalBoolean("disposeOnDetach");
        }
        /**
         * Proxy server, similar to the one passed to --proxy-server
         * <p><b>Experimental:</b> this part of CDP may change without notice.
         * @return the protocol field value, empty when absent
         */
        public Optional<String> proxyServer() {
            return optionalString("proxyServer");
        }
        /**
         * Proxy bypass list, similar to the one passed to --proxy-bypass-list
         * <p><b>Experimental:</b> this part of CDP may change without notice.
         * @return the protocol field value, empty when absent
         */
        public Optional<String> proxyBypassList() {
            return optionalString("proxyBypassList");
        }
        /**
         * An optional list of origins to grant unlimited cross-origin access to. Parts of the URL other than those constituting origin are ignored.
         * <p><b>Experimental:</b> this part of CDP may change without notice.
         * @return the protocol field value, empty when absent
         */
        public Optional<java.util.List<String>> originsWithUniversalNetworkAccess() {
            return optional(ORIGINS_WITH_UNIVERSAL_NETWORK_ACCESS);
        }
        /**
         * If specified, disposes this context when debugging session disconnects.
         * <p><b>Experimental:</b> this part of CDP may change without notice.
         * @param disposeOnDetach field value
         * @return this model
         */
        public CreateBrowserContextRequest disposeOnDetach(boolean disposeOnDetach) {
            set("disposeOnDetach", disposeOnDetach);
            return this;
        }
        /**
         * Proxy server, similar to the one passed to --proxy-server
         * <p><b>Experimental:</b> this part of CDP may change without notice.
         * @param proxyServer field value
         * @return this model
         */
        public CreateBrowserContextRequest proxyServer(String proxyServer) {
            set("proxyServer", proxyServer);
            return this;
        }
        /**
         * Proxy bypass list, similar to the one passed to --proxy-bypass-list
         * <p><b>Experimental:</b> this part of CDP may change without notice.
         * @param proxyBypassList field value
         * @return this model
         */
        public CreateBrowserContextRequest proxyBypassList(String proxyBypassList) {
            set("proxyBypassList", proxyBypassList);
            return this;
        }
        /**
         * An optional list of origins to grant unlimited cross-origin access to. Parts of the URL other than those constituting origin are ignored.
         * <p><b>Experimental:</b> this part of CDP may change without notice.
         * @param originsWithUniversalNetworkAccess field value
         * @return this model
         */
        public CreateBrowserContextRequest originsWithUniversalNetworkAccess(java.util.List<String> originsWithUniversalNetworkAccess) {
            set("originsWithUniversalNetworkAccess", originsWithUniversalNetworkAccess);
            return this;
        }
    }
    /**
     * Creates a new page.
     */
    public static final class CreateTargetRequest extends CdpObject {
        public CreateTargetRequest() {}
        public static CreateTargetRequest fromMap(Map<String, Object> values) {
            final CreateTargetRequest instance_ = new CreateTargetRequest();
            if (values != null) instance_.values.putAll(values);
            return instance_;
        }
        /**
         * The initial URL the page will be navigated to. An empty string indicates about:blank.
         * @return the protocol field value
         */
        public String url() {
            return requireString("url");
        }
        /**
         * Frame left origin in DIP (requires newWindow to be true or headless shell).
         * <p><b>Experimental:</b> this part of CDP may change without notice.
         * @return the protocol field value, empty when absent
         */
        public OptionalLong left() {
            return optionalLong("left");
        }
        /**
         * Frame top origin in DIP (requires newWindow to be true or headless shell).
         * <p><b>Experimental:</b> this part of CDP may change without notice.
         * @return the protocol field value, empty when absent
         */
        public OptionalLong top() {
            return optionalLong("top");
        }
        /**
         * Frame width in DIP (requires newWindow to be true or headless shell).
         * @return the protocol field value, empty when absent
         */
        public OptionalLong width() {
            return optionalLong("width");
        }
        /**
         * Frame height in DIP (requires newWindow to be true or headless shell).
         * @return the protocol field value, empty when absent
         */
        public OptionalLong height() {
            return optionalLong("height");
        }
        /**
         * Frame window state (requires newWindow to be true or headless shell). Default is normal.
         * @return the protocol field value, empty when absent
         */
        public Optional<Target.WindowState> windowState() {
            return optional(WINDOW_STATE);
        }
        /**
         * The browser context to create the page in.
         * <p><b>Experimental:</b> this part of CDP may change without notice.
         * @return the protocol field value, empty when absent
         */
        public Optional<Browser.BrowserContextID> browserContextId() {
            return optional(BROWSER_CONTEXT_ID);
        }
        /**
         * Whether BeginFrames for this target will be controlled via DevTools (headless shell only, not supported on MacOS yet, false by default).
         * <p><b>Experimental:</b> this part of CDP may change without notice.
         * @return the protocol field value, empty when absent
         */
        public Optional<Boolean> enableBeginFrameControl() {
            return optionalBoolean("enableBeginFrameControl");
        }
        /**
         * Whether to create a new Window or Tab (false by default, not supported by headless shell).
         * @return the protocol field value, empty when absent
         */
        public Optional<Boolean> newWindow() {
            return optionalBoolean("newWindow");
        }
        /**
         * Whether to create the target in background or foreground (false by default, not supported by headless shell).
         * @return the protocol field value, empty when absent
         */
        public Optional<Boolean> background() {
            return optionalBoolean("background");
        }
        /**
         * Whether to create the target of type &quot;tab&quot;.
         * <p><b>Experimental:</b> this part of CDP may change without notice.
         * @return the protocol field value, empty when absent
         */
        public Optional<Boolean> forTab() {
            return optionalBoolean("forTab");
        }
        /**
         * Whether to create a hidden target. The hidden target is observable via protocol, but not present in the tab UI strip. Cannot be created with {@code forTab: true}, {@code newWindow: true} or {@code background: false}. The life-time of the tab is limited to the life-time of the session.
         * <p><b>Experimental:</b> this part of CDP may change without notice.
         * @return the protocol field value, empty when absent
         */
        public Optional<Boolean> hidden() {
            return optionalBoolean("hidden");
        }
        /**
         * If specified, the option is used to determine if the new target should be focused or not. By default, the focus behavior depends on the value of the background field. For example, background=false and focus=false will result in the target tab being opened but the browser window remain unchanged (if it was in the background, it will remain in the background) and background=false with focus=undefined will result in the window being focused. Using background: true and focus: true is not supported and will result in an error.
         * <p><b>Experimental:</b> this part of CDP may change without notice.
         * @return the protocol field value, empty when absent
         */
        public Optional<Boolean> focus() {
            return optionalBoolean("focus");
        }
        /**
         * The initial URL the page will be navigated to. An empty string indicates about:blank.
         * @param url field value
         * @return this model
         */
        public CreateTargetRequest url(String url) {
            set("url", url);
            return this;
        }
        /**
         * Frame left origin in DIP (requires newWindow to be true or headless shell).
         * <p><b>Experimental:</b> this part of CDP may change without notice.
         * @param left field value
         * @return this model
         */
        public CreateTargetRequest left(long left) {
            set("left", left);
            return this;
        }
        /**
         * Frame top origin in DIP (requires newWindow to be true or headless shell).
         * <p><b>Experimental:</b> this part of CDP may change without notice.
         * @param top field value
         * @return this model
         */
        public CreateTargetRequest top(long top) {
            set("top", top);
            return this;
        }
        /**
         * Frame width in DIP (requires newWindow to be true or headless shell).
         * @param width field value
         * @return this model
         */
        public CreateTargetRequest width(long width) {
            set("width", width);
            return this;
        }
        /**
         * Frame height in DIP (requires newWindow to be true or headless shell).
         * @param height field value
         * @return this model
         */
        public CreateTargetRequest height(long height) {
            set("height", height);
            return this;
        }
        /**
         * Frame window state (requires newWindow to be true or headless shell). Default is normal.
         * @param windowState field value
         * @return this model
         */
        public CreateTargetRequest windowState(Target.WindowState windowState) {
            set("windowState", windowState);
            return this;
        }
        /**
         * The browser context to create the page in.
         * <p><b>Experimental:</b> this part of CDP may change without notice.
         * @param browserContextId field value
         * @return this model
         */
        public CreateTargetRequest browserContextId(Browser.BrowserContextID browserContextId) {
            set("browserContextId", browserContextId);
            return this;
        }
        /**
         * Whether BeginFrames for this target will be controlled via DevTools (headless shell only, not supported on MacOS yet, false by default).
         * <p><b>Experimental:</b> this part of CDP may change without notice.
         * @param enableBeginFrameControl field value
         * @return this model
         */
        public CreateTargetRequest enableBeginFrameControl(boolean enableBeginFrameControl) {
            set("enableBeginFrameControl", enableBeginFrameControl);
            return this;
        }
        /**
         * Whether to create a new Window or Tab (false by default, not supported by headless shell).
         * @param newWindow field value
         * @return this model
         */
        public CreateTargetRequest newWindow(boolean newWindow) {
            set("newWindow", newWindow);
            return this;
        }
        /**
         * Whether to create the target in background or foreground (false by default, not supported by headless shell).
         * @param background field value
         * @return this model
         */
        public CreateTargetRequest background(boolean background) {
            set("background", background);
            return this;
        }
        /**
         * Whether to create the target of type &quot;tab&quot;.
         * <p><b>Experimental:</b> this part of CDP may change without notice.
         * @param forTab field value
         * @return this model
         */
        public CreateTargetRequest forTab(boolean forTab) {
            set("forTab", forTab);
            return this;
        }
        /**
         * Whether to create a hidden target. The hidden target is observable via protocol, but not present in the tab UI strip. Cannot be created with {@code forTab: true}, {@code newWindow: true} or {@code background: false}. The life-time of the tab is limited to the life-time of the session.
         * <p><b>Experimental:</b> this part of CDP may change without notice.
         * @param hidden field value
         * @return this model
         */
        public CreateTargetRequest hidden(boolean hidden) {
            set("hidden", hidden);
            return this;
        }
        /**
         * If specified, the option is used to determine if the new target should be focused or not. By default, the focus behavior depends on the value of the background field. For example, background=false and focus=false will result in the target tab being opened but the browser window remain unchanged (if it was in the background, it will remain in the background) and background=false with focus=undefined will result in the window being focused. Using background: true and focus: true is not supported and will result in an error.
         * <p><b>Experimental:</b> this part of CDP may change without notice.
         * @param focus field value
         * @return this model
         */
        public CreateTargetRequest focus(boolean focus) {
            set("focus", focus);
            return this;
        }
    }
    /**
     * Detaches session with given id.
     */
    public static final class DetachFromTargetRequest extends CdpObject {
        public DetachFromTargetRequest() {}
        public static DetachFromTargetRequest fromMap(Map<String, Object> values) {
            final DetachFromTargetRequest instance_ = new DetachFromTargetRequest();
            if (values != null) instance_.values.putAll(values);
            return instance_;
        }
        /**
         * Session to detach.
         * @return the protocol field value, empty when absent
         */
        public Optional<Target.SessionID> sessionId() {
            return optional(SESSION_ID);
        }
        /**
         * Deprecated.
         * @return the protocol field value, empty when absent
         * @deprecated Deprecated by the Chromium DevTools Protocol.
         */
        @Deprecated
        public Optional<Target.TargetID> targetId() {
            return optional(TARGET_ID);
        }
        /**
         * Session to detach.
         * @param sessionId field value
         * @return this model
         */
        public DetachFromTargetRequest sessionId(Target.SessionID sessionId) {
            set("sessionId", sessionId);
            return this;
        }
        /**
         * Deprecated.
         * @param targetId field value
         * @return this model
         * @deprecated Deprecated by the Chromium DevTools Protocol.
         */
        @Deprecated
        public DetachFromTargetRequest targetId(Target.TargetID targetId) {
            set("targetId", targetId);
            return this;
        }
    }
    /**
     * Deletes a BrowserContext. All the belonging pages will be closed without calling their beforeunload hooks.
     */
    public static final class DisposeBrowserContextRequest extends CdpObject {
        public DisposeBrowserContextRequest() {}
        public static DisposeBrowserContextRequest fromMap(Map<String, Object> values) {
            final DisposeBrowserContextRequest instance_ = new DisposeBrowserContextRequest();
            if (values != null) instance_.values.putAll(values);
            return instance_;
        }
        /**
         * Returns the browserContextId field.
         * @return the protocol field value
         */
        public Browser.BrowserContextID browserContextId() {
            return require(BROWSER_CONTEXT_ID);
        }
        /**
         * Sets the browserContextId field.
         * @param browserContextId field value
         * @return this model
         */
        public DisposeBrowserContextRequest browserContextId(Browser.BrowserContextID browserContextId) {
            set("browserContextId", browserContextId);
            return this;
        }
    }
    /**
     * Returns information about a target.
     * <p><b>Experimental:</b> this part of CDP may change without notice.
     */
    public static final class GetTargetInfoRequest extends CdpObject {
        public GetTargetInfoRequest() {}
        public static GetTargetInfoRequest fromMap(Map<String, Object> values) {
            final GetTargetInfoRequest instance_ = new GetTargetInfoRequest();
            if (values != null) instance_.values.putAll(values);
            return instance_;
        }
        /**
         * Returns the targetId field.
         * @return the protocol field value, empty when absent
         */
        public Optional<Target.TargetID> targetId() {
            return optional(TARGET_ID);
        }
        /**
         * Sets the targetId field.
         * @param targetId field value
         * @return this model
         */
        public GetTargetInfoRequest targetId(Target.TargetID targetId) {
            set("targetId", targetId);
            return this;
        }
    }
    /**
     * Retrieves a list of available targets.
     */
    public static final class GetTargetsRequest extends CdpObject {
        public GetTargetsRequest() {}
        public static GetTargetsRequest fromMap(Map<String, Object> values) {
            final GetTargetsRequest instance_ = new GetTargetsRequest();
            if (values != null) instance_.values.putAll(values);
            return instance_;
        }
        /**
         * Only targets matching filter will be reported. If filter is not specified and target discovery is currently enabled, a filter used for target discovery is used for consistency.
         * <p><b>Experimental:</b> this part of CDP may change without notice.
         * @return the protocol field value, empty when absent
         */
        public Optional<java.util.List<Target.FilterEntry>> filter() {
            return optional(FILTER);
        }
        /**
         * Only targets matching filter will be reported. If filter is not specified and target discovery is currently enabled, a filter used for target discovery is used for consistency.
         * <p><b>Experimental:</b> this part of CDP may change without notice.
         * @param filter field value
         * @return this model
         */
        public GetTargetsRequest filter(java.util.List<Target.FilterEntry> filter) {
            set("filter", filter);
            return this;
        }
    }
    /**
     * Sends protocol message over session with given id. Consider using flat mode instead; see commands attachToTarget, setAutoAttach, and crbug.com/991325.
     * @deprecated Deprecated by the Chromium DevTools Protocol.
     */
    @Deprecated
    public static final class SendMessageToTargetRequest extends CdpObject {
        public SendMessageToTargetRequest() {}
        public static SendMessageToTargetRequest fromMap(Map<String, Object> values) {
            final SendMessageToTargetRequest instance_ = new SendMessageToTargetRequest();
            if (values != null) instance_.values.putAll(values);
            return instance_;
        }
        /**
         * Returns the message field.
         * @return the protocol field value
         */
        public String message() {
            return requireString("message");
        }
        /**
         * Identifier of the session.
         * @return the protocol field value, empty when absent
         */
        public Optional<Target.SessionID> sessionId() {
            return optional(SESSION_ID);
        }
        /**
         * Deprecated.
         * @return the protocol field value, empty when absent
         * @deprecated Deprecated by the Chromium DevTools Protocol.
         */
        @Deprecated
        public Optional<Target.TargetID> targetId() {
            return optional(TARGET_ID);
        }
        /**
         * Sets the message field.
         * @param message field value
         * @return this model
         */
        public SendMessageToTargetRequest message(String message) {
            set("message", message);
            return this;
        }
        /**
         * Identifier of the session.
         * @param sessionId field value
         * @return this model
         */
        public SendMessageToTargetRequest sessionId(Target.SessionID sessionId) {
            set("sessionId", sessionId);
            return this;
        }
        /**
         * Deprecated.
         * @param targetId field value
         * @return this model
         * @deprecated Deprecated by the Chromium DevTools Protocol.
         */
        @Deprecated
        public SendMessageToTargetRequest targetId(Target.TargetID targetId) {
            set("targetId", targetId);
            return this;
        }
    }
    /**
     * Controls whether to automatically attach to new targets which are considered to be directly related to this one (for example, iframes or workers). When turned on, attaches to all existing related targets as well. When turned off, automatically detaches from all currently attached targets. This also clears all targets added by {@code autoAttachRelated} from the list of targets to watch for creation of related targets. You might want to call this recursively for auto-attached targets to attach to all available targets.
     */
    public static final class SetAutoAttachRequest extends CdpObject {
        public SetAutoAttachRequest() {}
        public static SetAutoAttachRequest fromMap(Map<String, Object> values) {
            final SetAutoAttachRequest instance_ = new SetAutoAttachRequest();
            if (values != null) instance_.values.putAll(values);
            return instance_;
        }
        /**
         * Whether to auto-attach to related targets.
         * @return the protocol field value
         */
        public boolean autoAttach() {
            return requireBoolean("autoAttach");
        }
        /**
         * Whether to pause new targets when attaching to them. Use {@code Runtime.runIfWaitingForDebugger} to run paused targets.
         * @return the protocol field value
         */
        public boolean waitForDebuggerOnStart() {
            return requireBoolean("waitForDebuggerOnStart");
        }
        /**
         * Enables &quot;flat&quot; access to the session via specifying sessionId attribute in the commands. We plan to make this the default, deprecate non-flattened mode, and eventually retire it. See crbug.com/991325.
         * <p><b>Experimental:</b> this part of CDP may change without notice.
         * @return the protocol field value, empty when absent
         */
        public Optional<Boolean> flatten() {
            return optionalBoolean("flatten");
        }
        /**
         * Only targets matching filter will be attached.
         * <p><b>Experimental:</b> this part of CDP may change without notice.
         * @return the protocol field value, empty when absent
         */
        public Optional<java.util.List<Target.FilterEntry>> filter() {
            return optional(FILTER);
        }
        /**
         * Whether to auto-attach to related targets.
         * @param autoAttach field value
         * @return this model
         */
        public SetAutoAttachRequest autoAttach(boolean autoAttach) {
            set("autoAttach", autoAttach);
            return this;
        }
        /**
         * Whether to pause new targets when attaching to them. Use {@code Runtime.runIfWaitingForDebugger} to run paused targets.
         * @param waitForDebuggerOnStart field value
         * @return this model
         */
        public SetAutoAttachRequest waitForDebuggerOnStart(boolean waitForDebuggerOnStart) {
            set("waitForDebuggerOnStart", waitForDebuggerOnStart);
            return this;
        }
        /**
         * Enables &quot;flat&quot; access to the session via specifying sessionId attribute in the commands. We plan to make this the default, deprecate non-flattened mode, and eventually retire it. See crbug.com/991325.
         * <p><b>Experimental:</b> this part of CDP may change without notice.
         * @param flatten field value
         * @return this model
         */
        public SetAutoAttachRequest flatten(boolean flatten) {
            set("flatten", flatten);
            return this;
        }
        /**
         * Only targets matching filter will be attached.
         * <p><b>Experimental:</b> this part of CDP may change without notice.
         * @param filter field value
         * @return this model
         */
        public SetAutoAttachRequest filter(java.util.List<Target.FilterEntry> filter) {
            set("filter", filter);
            return this;
        }
    }
    /**
     * Adds the specified target to the list of targets that will be monitored for any related target creation (such as child frames, child workers and new versions of service worker) and reported through {@code attachedToTarget}. The specified target is also auto-attached. This cancels the effect of any previous {@code setAutoAttach} and is also cancelled by subsequent {@code setAutoAttach}. Only available at the Browser target.
     * <p><b>Experimental:</b> this part of CDP may change without notice.
     */
    public static final class AutoAttachRelatedRequest extends CdpObject {
        public AutoAttachRelatedRequest() {}
        public static AutoAttachRelatedRequest fromMap(Map<String, Object> values) {
            final AutoAttachRelatedRequest instance_ = new AutoAttachRelatedRequest();
            if (values != null) instance_.values.putAll(values);
            return instance_;
        }
        /**
         * Returns the targetId field.
         * @return the protocol field value
         */
        public Target.TargetID targetId() {
            return require(TARGET_ID);
        }
        /**
         * Whether to pause new targets when attaching to them. Use {@code Runtime.runIfWaitingForDebugger} to run paused targets.
         * @return the protocol field value
         */
        public boolean waitForDebuggerOnStart() {
            return requireBoolean("waitForDebuggerOnStart");
        }
        /**
         * Only targets matching filter will be attached.
         * <p><b>Experimental:</b> this part of CDP may change without notice.
         * @return the protocol field value, empty when absent
         */
        public Optional<java.util.List<Target.FilterEntry>> filter() {
            return optional(FILTER);
        }
        /**
         * Sets the targetId field.
         * @param targetId field value
         * @return this model
         */
        public AutoAttachRelatedRequest targetId(Target.TargetID targetId) {
            set("targetId", targetId);
            return this;
        }
        /**
         * Whether to pause new targets when attaching to them. Use {@code Runtime.runIfWaitingForDebugger} to run paused targets.
         * @param waitForDebuggerOnStart field value
         * @return this model
         */
        public AutoAttachRelatedRequest waitForDebuggerOnStart(boolean waitForDebuggerOnStart) {
            set("waitForDebuggerOnStart", waitForDebuggerOnStart);
            return this;
        }
        /**
         * Only targets matching filter will be attached.
         * <p><b>Experimental:</b> this part of CDP may change without notice.
         * @param filter field value
         * @return this model
         */
        public AutoAttachRelatedRequest filter(java.util.List<Target.FilterEntry> filter) {
            set("filter", filter);
            return this;
        }
    }
    /**
     * Controls whether to discover available targets and notify via {@code targetCreated/targetInfoChanged/targetDestroyed} events.
     */
    public static final class SetDiscoverTargetsRequest extends CdpObject {
        public SetDiscoverTargetsRequest() {}
        public static SetDiscoverTargetsRequest fromMap(Map<String, Object> values) {
            final SetDiscoverTargetsRequest instance_ = new SetDiscoverTargetsRequest();
            if (values != null) instance_.values.putAll(values);
            return instance_;
        }
        /**
         * Whether to discover available targets.
         * @return the protocol field value
         */
        public boolean discover() {
            return requireBoolean("discover");
        }
        /**
         * Only targets matching filter will be attached. If {@code discover} is false, {@code filter} must be omitted or empty.
         * <p><b>Experimental:</b> this part of CDP may change without notice.
         * @return the protocol field value, empty when absent
         */
        public Optional<java.util.List<Target.FilterEntry>> filter() {
            return optional(FILTER);
        }
        /**
         * Whether to discover available targets.
         * @param discover field value
         * @return this model
         */
        public SetDiscoverTargetsRequest discover(boolean discover) {
            set("discover", discover);
            return this;
        }
        /**
         * Only targets matching filter will be attached. If {@code discover} is false, {@code filter} must be omitted or empty.
         * <p><b>Experimental:</b> this part of CDP may change without notice.
         * @param filter field value
         * @return this model
         */
        public SetDiscoverTargetsRequest filter(java.util.List<Target.FilterEntry> filter) {
            set("filter", filter);
            return this;
        }
    }
    /**
     * Enables target discovery for the specified locations, when {@code setDiscoverTargets} was set to {@code true}.
     * <p><b>Experimental:</b> this part of CDP may change without notice.
     */
    public static final class SetRemoteLocationsRequest extends CdpObject {
        public SetRemoteLocationsRequest() {}
        public static SetRemoteLocationsRequest fromMap(Map<String, Object> values) {
            final SetRemoteLocationsRequest instance_ = new SetRemoteLocationsRequest();
            if (values != null) instance_.values.putAll(values);
            return instance_;
        }
        /**
         * List of remote locations.
         * @return the protocol field value
         */
        public java.util.List<Target.RemoteLocation> locations() {
            return require(LOCATIONS);
        }
        /**
         * List of remote locations.
         * @param locations field value
         * @return this model
         */
        public SetRemoteLocationsRequest locations(java.util.List<Target.RemoteLocation> locations) {
            set("locations", locations);
            return this;
        }
    }
    /**
     * Gets the targetId of the DevTools page target opened for the given target (if any).
     * <p><b>Experimental:</b> this part of CDP may change without notice.
     */
    public static final class GetDevToolsTargetRequest extends CdpObject {
        public GetDevToolsTargetRequest() {}
        public static GetDevToolsTargetRequest fromMap(Map<String, Object> values) {
            final GetDevToolsTargetRequest instance_ = new GetDevToolsTargetRequest();
            if (values != null) instance_.values.putAll(values);
            return instance_;
        }
        /**
         * Page or tab target ID.
         * @return the protocol field value
         */
        public Target.TargetID targetId() {
            return require(TARGET_ID);
        }
        /**
         * Page or tab target ID.
         * @param targetId field value
         * @return this model
         */
        public GetDevToolsTargetRequest targetId(Target.TargetID targetId) {
            set("targetId", targetId);
            return this;
        }
    }
    /**
     * Opens a DevTools window for the target.
     * <p><b>Experimental:</b> this part of CDP may change without notice.
     */
    public static final class OpenDevToolsRequest extends CdpObject {
        public OpenDevToolsRequest() {}
        public static OpenDevToolsRequest fromMap(Map<String, Object> values) {
            final OpenDevToolsRequest instance_ = new OpenDevToolsRequest();
            if (values != null) instance_.values.putAll(values);
            return instance_;
        }
        /**
         * This can be the page or tab target ID.
         * @return the protocol field value
         */
        public Target.TargetID targetId() {
            return require(TARGET_ID);
        }
        /**
         * The id of the panel we want DevTools to open initially. Currently supported panels are elements, console, network, sources, resources, timeline, chrome-recorder, heap-profiler, lighthouse, and security.
         * @return the protocol field value, empty when absent
         */
        public Optional<String> panelId() {
            return optionalString("panelId");
        }
        /**
         * This can be the page or tab target ID.
         * @param targetId field value
         * @return this model
         */
        public OpenDevToolsRequest targetId(Target.TargetID targetId) {
            set("targetId", targetId);
            return this;
        }
        /**
         * The id of the panel we want DevTools to open initially. Currently supported panels are elements, console, network, sources, resources, timeline, chrome-recorder, heap-profiler, lighthouse, and security.
         * @param panelId field value
         * @return this model
         */
        public OpenDevToolsRequest panelId(String panelId) {
            set("panelId", panelId);
            return this;
        }
    }
    /**
     * Returns all browser contexts created with {@code Target.createBrowserContext} method.
     */
    public static final class GetBrowserContextsResult extends CdpObject {
        public GetBrowserContextsResult() {}
        private GetBrowserContextsResult(Map<String, Object> values) { super(values); }
        public static GetBrowserContextsResult fromMap(Map<String, Object> values) {
            return new GetBrowserContextsResult(values);
        }
        /**
         * An array of browser context ids.
         * @return the protocol field value
         */
        public java.util.List<Browser.BrowserContextID> browserContextIds() {
            return require(BROWSER_CONTEXT_IDS);
        }
        /**
         * The id of the default browser context if available.
         * <p><b>Experimental:</b> this part of CDP may change without notice.
         * @return the protocol field value, empty when absent
         */
        public Optional<Browser.BrowserContextID> defaultBrowserContextId() {
            return optional(DEFAULT_BROWSER_CONTEXT_ID);
        }
        /**
         * An array of browser context ids.
         * @param browserContextIds field value
         * @return this model
         */
        public GetBrowserContextsResult browserContextIds(java.util.List<Browser.BrowserContextID> browserContextIds) {
            set("browserContextIds", browserContextIds);
            return this;
        }
        /**
         * The id of the default browser context if available.
         * <p><b>Experimental:</b> this part of CDP may change without notice.
         * @param defaultBrowserContextId field value
         * @return this model
         */
        public GetBrowserContextsResult defaultBrowserContextId(Browser.BrowserContextID defaultBrowserContextId) {
            set("defaultBrowserContextId", defaultBrowserContextId);
            return this;
        }
    }
    /**
     * Issued when attached to target because of auto-attach or {@code attachToTarget} command.
     * <p><b>Experimental:</b> this part of CDP may change without notice.
     */
    public static final class AttachedToTargetEvent extends CdpObject {
        public AttachedToTargetEvent() {}
        private AttachedToTargetEvent(Map<String, Object> values) { super(values); }
        public static AttachedToTargetEvent fromMap(Map<String, Object> values) {
            return new AttachedToTargetEvent(values);
        }
        /**
         * Identifier assigned to the session used to send/receive messages.
         * @return the protocol field value
         */
        public Target.SessionID sessionId() {
            return require(SESSION_ID);
        }
        /**
         * Returns the targetInfo field.
         * @return the protocol field value
         */
        public Target.TargetInfo targetInfo() {
            return require(TARGET_INFO);
        }
        /**
         * Returns the waitingForDebugger field.
         * @return the protocol field value
         */
        public boolean waitingForDebugger() {
            return requireBoolean("waitingForDebugger");
        }
        /**
         * Identifier assigned to the session used to send/receive messages.
         * @param sessionId field value
         * @return this model
         */
        public AttachedToTargetEvent sessionId(Target.SessionID sessionId) {
            set("sessionId", sessionId);
            return this;
        }
        /**
         * Sets the targetInfo field.
         * @param targetInfo field value
         * @return this model
         */
        public AttachedToTargetEvent targetInfo(Target.TargetInfo targetInfo) {
            set("targetInfo", targetInfo);
            return this;
        }
        /**
         * Sets the waitingForDebugger field.
         * @param waitingForDebugger field value
         * @return this model
         */
        public AttachedToTargetEvent waitingForDebugger(boolean waitingForDebugger) {
            set("waitingForDebugger", waitingForDebugger);
            return this;
        }
    }
    /**
     * Issued when detached from target for any reason (including {@code detachFromTarget} command). Can be issued multiple times per target if multiple sessions have been attached to it.
     * <p><b>Experimental:</b> this part of CDP may change without notice.
     */
    public static final class DetachedFromTargetEvent extends CdpObject {
        public DetachedFromTargetEvent() {}
        private DetachedFromTargetEvent(Map<String, Object> values) { super(values); }
        public static DetachedFromTargetEvent fromMap(Map<String, Object> values) {
            return new DetachedFromTargetEvent(values);
        }
        /**
         * Detached session identifier.
         * @return the protocol field value
         */
        public Target.SessionID sessionId() {
            return require(SESSION_ID);
        }
        /**
         * Deprecated.
         * @return the protocol field value, empty when absent
         * @deprecated Deprecated by the Chromium DevTools Protocol.
         */
        @Deprecated
        public Optional<Target.TargetID> targetId() {
            return optional(TARGET_ID);
        }
        /**
         * Detached session identifier.
         * @param sessionId field value
         * @return this model
         */
        public DetachedFromTargetEvent sessionId(Target.SessionID sessionId) {
            set("sessionId", sessionId);
            return this;
        }
        /**
         * Deprecated.
         * @param targetId field value
         * @return this model
         * @deprecated Deprecated by the Chromium DevTools Protocol.
         */
        @Deprecated
        public DetachedFromTargetEvent targetId(Target.TargetID targetId) {
            set("targetId", targetId);
            return this;
        }
    }
    /**
     * Notifies about a new protocol message received from the session (as reported in {@code attachedToTarget} event).
     */
    public static final class ReceivedMessageFromTargetEvent extends CdpObject {
        public ReceivedMessageFromTargetEvent() {}
        private ReceivedMessageFromTargetEvent(Map<String, Object> values) { super(values); }
        public static ReceivedMessageFromTargetEvent fromMap(Map<String, Object> values) {
            return new ReceivedMessageFromTargetEvent(values);
        }
        /**
         * Identifier of a session which sends a message.
         * @return the protocol field value
         */
        public Target.SessionID sessionId() {
            return require(SESSION_ID);
        }
        /**
         * Returns the message field.
         * @return the protocol field value
         */
        public String message() {
            return requireString("message");
        }
        /**
         * Deprecated.
         * @return the protocol field value, empty when absent
         * @deprecated Deprecated by the Chromium DevTools Protocol.
         */
        @Deprecated
        public Optional<Target.TargetID> targetId() {
            return optional(TARGET_ID);
        }
        /**
         * Identifier of a session which sends a message.
         * @param sessionId field value
         * @return this model
         */
        public ReceivedMessageFromTargetEvent sessionId(Target.SessionID sessionId) {
            set("sessionId", sessionId);
            return this;
        }
        /**
         * Sets the message field.
         * @param message field value
         * @return this model
         */
        public ReceivedMessageFromTargetEvent message(String message) {
            set("message", message);
            return this;
        }
        /**
         * Deprecated.
         * @param targetId field value
         * @return this model
         * @deprecated Deprecated by the Chromium DevTools Protocol.
         */
        @Deprecated
        public ReceivedMessageFromTargetEvent targetId(Target.TargetID targetId) {
            set("targetId", targetId);
            return this;
        }
    }
    /**
     * Issued when a possible inspection target is created.
     */
    public static final class TargetCreatedEvent extends CdpObject {
        public TargetCreatedEvent() {}
        private TargetCreatedEvent(Map<String, Object> values) { super(values); }
        public static TargetCreatedEvent fromMap(Map<String, Object> values) {
            return new TargetCreatedEvent(values);
        }
        /**
         * Returns the targetInfo field.
         * @return the protocol field value
         */
        public Target.TargetInfo targetInfo() {
            return require(TARGET_INFO);
        }
        /**
         * Sets the targetInfo field.
         * @param targetInfo field value
         * @return this model
         */
        public TargetCreatedEvent targetInfo(Target.TargetInfo targetInfo) {
            set("targetInfo", targetInfo);
            return this;
        }
    }
    /**
     * Issued when a target is destroyed.
     */
    public static final class TargetDestroyedEvent extends CdpObject {
        public TargetDestroyedEvent() {}
        private TargetDestroyedEvent(Map<String, Object> values) { super(values); }
        public static TargetDestroyedEvent fromMap(Map<String, Object> values) {
            return new TargetDestroyedEvent(values);
        }
        /**
         * Returns the targetId field.
         * @return the protocol field value
         */
        public Target.TargetID targetId() {
            return require(TARGET_ID);
        }
        /**
         * Sets the targetId field.
         * @param targetId field value
         * @return this model
         */
        public TargetDestroyedEvent targetId(Target.TargetID targetId) {
            set("targetId", targetId);
            return this;
        }
    }
    /**
     * Issued when a target has crashed.
     */
    public static final class TargetCrashedEvent extends CdpObject {
        public TargetCrashedEvent() {}
        private TargetCrashedEvent(Map<String, Object> values) { super(values); }
        public static TargetCrashedEvent fromMap(Map<String, Object> values) {
            return new TargetCrashedEvent(values);
        }
        /**
         * Returns the targetId field.
         * @return the protocol field value
         */
        public Target.TargetID targetId() {
            return require(TARGET_ID);
        }
        /**
         * Termination status type.
         * @return the protocol field value
         */
        public String status() {
            return requireString("status");
        }
        /**
         * Termination error code.
         * @return the protocol field value
         */
        public long errorCode() {
            return requireLong("errorCode");
        }
        /**
         * Sets the targetId field.
         * @param targetId field value
         * @return this model
         */
        public TargetCrashedEvent targetId(Target.TargetID targetId) {
            set("targetId", targetId);
            return this;
        }
        /**
         * Termination status type.
         * @param status field value
         * @return this model
         */
        public TargetCrashedEvent status(String status) {
            set("status", status);
            return this;
        }
        /**
         * Termination error code.
         * @param errorCode field value
         * @return this model
         */
        public TargetCrashedEvent errorCode(long errorCode) {
            set("errorCode", errorCode);
            return this;
        }
    }
    /**
     * Issued when some information about a target has changed. This only happens between {@code targetCreated} and {@code targetDestroyed}.
     */
    public static final class TargetInfoChangedEvent extends CdpObject {
        public TargetInfoChangedEvent() {}
        private TargetInfoChangedEvent(Map<String, Object> values) { super(values); }
        public static TargetInfoChangedEvent fromMap(Map<String, Object> values) {
            return new TargetInfoChangedEvent(values);
        }
        /**
         * Returns the targetInfo field.
         * @return the protocol field value
         */
        public Target.TargetInfo targetInfo() {
            return require(TARGET_INFO);
        }
        /**
         * Sets the targetInfo field.
         * @param targetInfo field value
         * @return this model
         */
        public TargetInfoChangedEvent targetInfo(Target.TargetInfo targetInfo) {
            set("targetInfo", targetInfo);
            return this;
        }
    }
    public static final class Client extends CdpDomainClient {
        public Client(CdpClient client) { super(client); }
        /**
         * Activates (focuses) the target.
         * @param targetId protocol value
         * @return a stage completing when the command completes
         */
        public CompletionStage<Void> activateTarget(Target.TargetID targetId) {
            return call("Target.activateTarget", params("targetId", targetId));
        }
        /**
         * Activates (focuses) the target.
         * @param request request parameters
         * @return a stage completing when the command completes
         */
        public CompletionStage<Void> activateTarget(ActivateTargetRequest request) {
            return call("Target.activateTarget", request);
        }
        /**
         * Attaches to the target with given id.
         * @param targetId protocol value
         * @param flatten protocol value
         * @return a stage completing with the command result
         */
        public CompletionStage<Target.SessionID> attachToTarget(Target.TargetID targetId, boolean flatten) {
            return call("Target.attachToTarget", params("targetId", targetId, "flatten", flatten), SESSION_ID::require);
        }
        /**
         * Attaches to the target with given id.
         * @param targetId protocol value
         * @return a stage completing with the command result
         */
        public CompletionStage<Target.SessionID> attachToTarget(Target.TargetID targetId) {
            return call("Target.attachToTarget", params("targetId", targetId), SESSION_ID::require);
        }
        /**
         * Attaches to the target with given id.
         * @param request request parameters
         * @return a stage completing with the command result
         */
        public CompletionStage<Target.SessionID> attachToTarget(AttachToTargetRequest request) {
            return call("Target.attachToTarget", request, SESSION_ID::require);
        }
        /**
         * Attaches to the browser target, only uses flat sessionId mode.
         * <p><b>Experimental:</b> this part of CDP may change without notice.
         * @return a stage completing with the command result
         */
        public CompletionStage<Target.SessionID> attachToBrowserTarget() {
            return call("Target.attachToBrowserTarget", SESSION_ID::require);
        }
        /**
         * Closes the target. If the target is a page that gets closed too.
         * @param targetId protocol value
         * @return a stage completing with the command result
         */
        public CompletionStage<Boolean> closeTarget(Target.TargetID targetId) {
            return call("Target.closeTarget", params("targetId", targetId), SUCCESS::require);
        }
        /**
         * Closes the target. If the target is a page that gets closed too.
         * @param request request parameters
         * @return a stage completing with the command result
         */
        public CompletionStage<Boolean> closeTarget(CloseTargetRequest request) {
            return call("Target.closeTarget", request, SUCCESS::require);
        }
        /**
         * Inject object to the target&#x27;s main frame that provides a communication channel with browser target.
         * <p>Injected object will be available as {@code window[bindingName]}.
         * <p>The object has the following API: - {@code binding.send(json)} - a method to send messages over the remote debugging protocol - {@code binding.onmessage = json =&gt; handleMessage(json)} - a callback that will be called for the protocol notifications and command responses.
         * <p><b>Experimental:</b> this part of CDP may change without notice.
         * @param targetId protocol value
         * @param bindingName protocol value
         * @param inheritPermissions protocol value
         * @return a stage completing when the command completes
         */
        public CompletionStage<Void> exposeDevToolsProtocol(Target.TargetID targetId, @Nullable String bindingName, @Nullable Boolean inheritPermissions) {
            return call("Target.exposeDevToolsProtocol", params("targetId", targetId, "bindingName", bindingName, "inheritPermissions", inheritPermissions));
        }
        /**
         * Inject object to the target&#x27;s main frame that provides a communication channel with browser target.
         * <p>Injected object will be available as {@code window[bindingName]}.
         * <p>The object has the following API: - {@code binding.send(json)} - a method to send messages over the remote debugging protocol - {@code binding.onmessage = json =&gt; handleMessage(json)} - a callback that will be called for the protocol notifications and command responses.
         * <p><b>Experimental:</b> this part of CDP may change without notice.
         * @param targetId protocol value
         * @return a stage completing when the command completes
         */
        public CompletionStage<Void> exposeDevToolsProtocol(Target.TargetID targetId) {
            return call("Target.exposeDevToolsProtocol", params("targetId", targetId));
        }
        /**
         * Inject object to the target&#x27;s main frame that provides a communication channel with browser target.
         * <p>Injected object will be available as {@code window[bindingName]}.
         * <p>The object has the following API: - {@code binding.send(json)} - a method to send messages over the remote debugging protocol - {@code binding.onmessage = json =&gt; handleMessage(json)} - a callback that will be called for the protocol notifications and command responses.
         * <p><b>Experimental:</b> this part of CDP may change without notice.
         * @param request request parameters
         * @return a stage completing when the command completes
         */
        public CompletionStage<Void> exposeDevToolsProtocol(ExposeDevToolsProtocolRequest request) {
            return call("Target.exposeDevToolsProtocol", request);
        }
        /**
         * Creates a new empty BrowserContext. Similar to an incognito profile but you can have more than one.
         * @param disposeOnDetach protocol value
         * @param proxyServer protocol value
         * @param proxyBypassList protocol value
         * @param originsWithUniversalNetworkAccess protocol value
         * @return a stage completing with the command result
         */
        public CompletionStage<Browser.BrowserContextID> createBrowserContext(@Nullable Boolean disposeOnDetach, @Nullable String proxyServer, @Nullable String proxyBypassList, java.util.@Nullable List<String> originsWithUniversalNetworkAccess) {
            return call("Target.createBrowserContext", params("disposeOnDetach", disposeOnDetach, "proxyServer", proxyServer, "proxyBypassList", proxyBypassList, "originsWithUniversalNetworkAccess", originsWithUniversalNetworkAccess), BROWSER_CONTEXT_ID::require);
        }
        /**
         * Creates a new empty BrowserContext. Similar to an incognito profile but you can have more than one.
         * @return a stage completing with the command result
         */
        public CompletionStage<Browser.BrowserContextID> createBrowserContext() {
            return call("Target.createBrowserContext", BROWSER_CONTEXT_ID::require);
        }
        /**
         * Creates a new empty BrowserContext. Similar to an incognito profile but you can have more than one.
         * @param request request parameters
         * @return a stage completing with the command result
         */
        public CompletionStage<Browser.BrowserContextID> createBrowserContext(CreateBrowserContextRequest request) {
            return call("Target.createBrowserContext", request, BROWSER_CONTEXT_ID::require);
        }
        /**
         * Returns all browser contexts created with {@code Target.createBrowserContext} method.
         * @return a stage completing with the command result
         */
        public CompletionStage<GetBrowserContextsResult> getBrowserContexts() {
            return call("Target.getBrowserContexts", GetBrowserContextsResult::fromMap);
        }
        /**
         * Creates a new page.
         * @param url protocol value
         * @param left protocol value
         * @param top protocol value
         * @param width protocol value
         * @param height protocol value
         * @param windowState protocol value
         * @param browserContextId protocol value
         * @param enableBeginFrameControl protocol value
         * @param newWindow protocol value
         * @param background protocol value
         * @param forTab protocol value
         * @param hidden protocol value
         * @param focus protocol value
         * @return a stage completing with the command result
         */
        public CompletionStage<Target.TargetID> createTarget(String url, @Nullable Long left, @Nullable Long top, @Nullable Long width, @Nullable Long height, Target.@Nullable WindowState windowState, Browser.@Nullable BrowserContextID browserContextId, @Nullable Boolean enableBeginFrameControl, @Nullable Boolean newWindow, @Nullable Boolean background, @Nullable Boolean forTab, @Nullable Boolean hidden, @Nullable Boolean focus) {
            return call("Target.createTarget", params("url", url, "left", left, "top", top, "width", width, "height", height, "windowState", windowState, "browserContextId", browserContextId, "enableBeginFrameControl", enableBeginFrameControl, "newWindow", newWindow, "background", background, "forTab", forTab, "hidden", hidden, "focus", focus), TARGET_ID::require);
        }
        /**
         * Creates a new page.
         * @param url protocol value
         * @return a stage completing with the command result
         */
        public CompletionStage<Target.TargetID> createTarget(String url) {
            return call("Target.createTarget", params("url", url), TARGET_ID::require);
        }
        /**
         * Creates a new page.
         * @param request request parameters
         * @return a stage completing with the command result
         */
        public CompletionStage<Target.TargetID> createTarget(CreateTargetRequest request) {
            return call("Target.createTarget", request, TARGET_ID::require);
        }
        /**
         * Detaches session with given id.
         * @param sessionId protocol value
         * @param targetId protocol value
         * @return a stage completing when the command completes
         */
        public CompletionStage<Void> detachFromTarget(Target.@Nullable SessionID sessionId, Target.@Nullable TargetID targetId) {
            return call("Target.detachFromTarget", params("sessionId", sessionId, "targetId", targetId));
        }
        /**
         * Detaches session with given id.
         * @return a stage completing when the command completes
         */
        public CompletionStage<Void> detachFromTarget() {
            return call("Target.detachFromTarget");
        }
        /**
         * Detaches session with given id.
         * @param request request parameters
         * @return a stage completing when the command completes
         */
        public CompletionStage<Void> detachFromTarget(DetachFromTargetRequest request) {
            return call("Target.detachFromTarget", request);
        }
        /**
         * Deletes a BrowserContext. All the belonging pages will be closed without calling their beforeunload hooks.
         * @param browserContextId protocol value
         * @return a stage completing when the command completes
         */
        public CompletionStage<Void> disposeBrowserContext(Browser.BrowserContextID browserContextId) {
            return call("Target.disposeBrowserContext", params("browserContextId", browserContextId));
        }
        /**
         * Deletes a BrowserContext. All the belonging pages will be closed without calling their beforeunload hooks.
         * @param request request parameters
         * @return a stage completing when the command completes
         */
        public CompletionStage<Void> disposeBrowserContext(DisposeBrowserContextRequest request) {
            return call("Target.disposeBrowserContext", request);
        }
        /**
         * Returns information about a target.
         * <p><b>Experimental:</b> this part of CDP may change without notice.
         * @param targetId protocol value
         * @return a stage completing with the command result
         */
        public CompletionStage<Target.TargetInfo> getTargetInfo(Target.TargetID targetId) {
            return call("Target.getTargetInfo", params("targetId", targetId), TARGET_INFO::require);
        }
        /**
         * Returns information about a target.
         * <p><b>Experimental:</b> this part of CDP may change without notice.
         * @return a stage completing with the command result
         */
        public CompletionStage<Target.TargetInfo> getTargetInfo() {
            return call("Target.getTargetInfo", TARGET_INFO::require);
        }
        /**
         * Returns information about a target.
         * <p><b>Experimental:</b> this part of CDP may change without notice.
         * @param request request parameters
         * @return a stage completing with the command result
         */
        public CompletionStage<Target.TargetInfo> getTargetInfo(GetTargetInfoRequest request) {
            return call("Target.getTargetInfo", request, TARGET_INFO::require);
        }
        /**
         * Retrieves a list of available targets.
         * @param filter protocol value
         * @return a stage completing with the command result
         */
        public CompletionStage<java.util.List<Target.TargetInfo>> getTargets(java.util.List<Target.FilterEntry> filter) {
            return call("Target.getTargets", params("filter", filter), TARGET_INFOS::require);
        }
        /**
         * Retrieves a list of available targets.
         * @return a stage completing with the command result
         */
        public CompletionStage<java.util.List<Target.TargetInfo>> getTargets() {
            return call("Target.getTargets", TARGET_INFOS::require);
        }
        /**
         * Retrieves a list of available targets.
         * @param request request parameters
         * @return a stage completing with the command result
         */
        public CompletionStage<java.util.List<Target.TargetInfo>> getTargets(GetTargetsRequest request) {
            return call("Target.getTargets", request, TARGET_INFOS::require);
        }
        /**
         * Sends protocol message over session with given id. Consider using flat mode instead; see commands attachToTarget, setAutoAttach, and crbug.com/991325.
         * @param message protocol value
         * @param sessionId protocol value
         * @param targetId protocol value
         * @return a stage completing when the command completes
         * @deprecated Deprecated by the Chromium DevTools Protocol.
         */
        @Deprecated
        public CompletionStage<Void> sendMessageToTarget(String message, Target.@Nullable SessionID sessionId, Target.@Nullable TargetID targetId) {
            return call("Target.sendMessageToTarget", params("message", message, "sessionId", sessionId, "targetId", targetId));
        }
        /**
         * Sends protocol message over session with given id. Consider using flat mode instead; see commands attachToTarget, setAutoAttach, and crbug.com/991325.
         * @param message protocol value
         * @return a stage completing when the command completes
         * @deprecated Deprecated by the Chromium DevTools Protocol.
         */
        @Deprecated
        public CompletionStage<Void> sendMessageToTarget(String message) {
            return call("Target.sendMessageToTarget", params("message", message));
        }
        /**
         * Sends protocol message over session with given id. Consider using flat mode instead; see commands attachToTarget, setAutoAttach, and crbug.com/991325.
         * @param request request parameters
         * @return a stage completing when the command completes
         * @deprecated Deprecated by the Chromium DevTools Protocol.
         */
        @Deprecated
        public CompletionStage<Void> sendMessageToTarget(SendMessageToTargetRequest request) {
            return call("Target.sendMessageToTarget", request);
        }
        /**
         * Controls whether to automatically attach to new targets which are considered to be directly related to this one (for example, iframes or workers). When turned on, attaches to all existing related targets as well. When turned off, automatically detaches from all currently attached targets. This also clears all targets added by {@code autoAttachRelated} from the list of targets to watch for creation of related targets. You might want to call this recursively for auto-attached targets to attach to all available targets.
         * @param autoAttach protocol value
         * @param waitForDebuggerOnStart protocol value
         * @param flatten protocol value
         * @param filter protocol value
         * @return a stage completing when the command completes
         */
        public CompletionStage<Void> setAutoAttach(boolean autoAttach, boolean waitForDebuggerOnStart, @Nullable Boolean flatten, java.util.@Nullable List<Target.FilterEntry> filter) {
            return call("Target.setAutoAttach", params("autoAttach", autoAttach, "waitForDebuggerOnStart", waitForDebuggerOnStart, "flatten", flatten, "filter", filter));
        }
        /**
         * Controls whether to automatically attach to new targets which are considered to be directly related to this one (for example, iframes or workers). When turned on, attaches to all existing related targets as well. When turned off, automatically detaches from all currently attached targets. This also clears all targets added by {@code autoAttachRelated} from the list of targets to watch for creation of related targets. You might want to call this recursively for auto-attached targets to attach to all available targets.
         * @param autoAttach protocol value
         * @param waitForDebuggerOnStart protocol value
         * @return a stage completing when the command completes
         */
        public CompletionStage<Void> setAutoAttach(boolean autoAttach, boolean waitForDebuggerOnStart) {
            return call("Target.setAutoAttach", params("autoAttach", autoAttach, "waitForDebuggerOnStart", waitForDebuggerOnStart));
        }
        /**
         * Controls whether to automatically attach to new targets which are considered to be directly related to this one (for example, iframes or workers). When turned on, attaches to all existing related targets as well. When turned off, automatically detaches from all currently attached targets. This also clears all targets added by {@code autoAttachRelated} from the list of targets to watch for creation of related targets. You might want to call this recursively for auto-attached targets to attach to all available targets.
         * @param request request parameters
         * @return a stage completing when the command completes
         */
        public CompletionStage<Void> setAutoAttach(SetAutoAttachRequest request) {
            return call("Target.setAutoAttach", request);
        }
        /**
         * Adds the specified target to the list of targets that will be monitored for any related target creation (such as child frames, child workers and new versions of service worker) and reported through {@code attachedToTarget}. The specified target is also auto-attached. This cancels the effect of any previous {@code setAutoAttach} and is also cancelled by subsequent {@code setAutoAttach}. Only available at the Browser target.
         * <p><b>Experimental:</b> this part of CDP may change without notice.
         * @param targetId protocol value
         * @param waitForDebuggerOnStart protocol value
         * @param filter protocol value
         * @return a stage completing when the command completes
         */
        public CompletionStage<Void> autoAttachRelated(Target.TargetID targetId, boolean waitForDebuggerOnStart, java.util.List<Target.FilterEntry> filter) {
            return call("Target.autoAttachRelated", params("targetId", targetId, "waitForDebuggerOnStart", waitForDebuggerOnStart, "filter", filter));
        }
        /**
         * Adds the specified target to the list of targets that will be monitored for any related target creation (such as child frames, child workers and new versions of service worker) and reported through {@code attachedToTarget}. The specified target is also auto-attached. This cancels the effect of any previous {@code setAutoAttach} and is also cancelled by subsequent {@code setAutoAttach}. Only available at the Browser target.
         * <p><b>Experimental:</b> this part of CDP may change without notice.
         * @param targetId protocol value
         * @param waitForDebuggerOnStart protocol value
         * @return a stage completing when the command completes
         */
        public CompletionStage<Void> autoAttachRelated(Target.TargetID targetId, boolean waitForDebuggerOnStart) {
            return call("Target.autoAttachRelated", params("targetId", targetId, "waitForDebuggerOnStart", waitForDebuggerOnStart));
        }
        /**
         * Adds the specified target to the list of targets that will be monitored for any related target creation (such as child frames, child workers and new versions of service worker) and reported through {@code attachedToTarget}. The specified target is also auto-attached. This cancels the effect of any previous {@code setAutoAttach} and is also cancelled by subsequent {@code setAutoAttach}. Only available at the Browser target.
         * <p><b>Experimental:</b> this part of CDP may change without notice.
         * @param request request parameters
         * @return a stage completing when the command completes
         */
        public CompletionStage<Void> autoAttachRelated(AutoAttachRelatedRequest request) {
            return call("Target.autoAttachRelated", request);
        }
        /**
         * Controls whether to discover available targets and notify via {@code targetCreated/targetInfoChanged/targetDestroyed} events.
         * @param discover protocol value
         * @param filter protocol value
         * @return a stage completing when the command completes
         */
        public CompletionStage<Void> setDiscoverTargets(boolean discover, java.util.List<Target.FilterEntry> filter) {
            return call("Target.setDiscoverTargets", params("discover", discover, "filter", filter));
        }
        /**
         * Controls whether to discover available targets and notify via {@code targetCreated/targetInfoChanged/targetDestroyed} events.
         * @param discover protocol value
         * @return a stage completing when the command completes
         */
        public CompletionStage<Void> setDiscoverTargets(boolean discover) {
            return call("Target.setDiscoverTargets", params("discover", discover));
        }
        /**
         * Controls whether to discover available targets and notify via {@code targetCreated/targetInfoChanged/targetDestroyed} events.
         * @param request request parameters
         * @return a stage completing when the command completes
         */
        public CompletionStage<Void> setDiscoverTargets(SetDiscoverTargetsRequest request) {
            return call("Target.setDiscoverTargets", request);
        }
        /**
         * Enables target discovery for the specified locations, when {@code setDiscoverTargets} was set to {@code true}.
         * <p><b>Experimental:</b> this part of CDP may change without notice.
         * @param locations protocol value
         * @return a stage completing when the command completes
         */
        public CompletionStage<Void> setRemoteLocations(java.util.List<Target.RemoteLocation> locations) {
            return call("Target.setRemoteLocations", params("locations", locations));
        }
        /**
         * Enables target discovery for the specified locations, when {@code setDiscoverTargets} was set to {@code true}.
         * <p><b>Experimental:</b> this part of CDP may change without notice.
         * @param request request parameters
         * @return a stage completing when the command completes
         */
        public CompletionStage<Void> setRemoteLocations(SetRemoteLocationsRequest request) {
            return call("Target.setRemoteLocations", request);
        }
        /**
         * Gets the targetId of the DevTools page target opened for the given target (if any).
         * <p><b>Experimental:</b> this part of CDP may change without notice.
         * @param targetId protocol value
         * @return a stage completing with the command result
         */
        public CompletionStage<Optional<Target.TargetID>> getDevToolsTarget(Target.TargetID targetId) {
            return call("Target.getDevToolsTarget", params("targetId", targetId), TARGET_ID::optional);
        }
        /**
         * Gets the targetId of the DevTools page target opened for the given target (if any).
         * <p><b>Experimental:</b> this part of CDP may change without notice.
         * @param request request parameters
         * @return a stage completing with the command result
         */
        public CompletionStage<Optional<Target.TargetID>> getDevToolsTarget(GetDevToolsTargetRequest request) {
            return call("Target.getDevToolsTarget", request, TARGET_ID::optional);
        }
        /**
         * Opens a DevTools window for the target.
         * <p><b>Experimental:</b> this part of CDP may change without notice.
         * @param targetId protocol value
         * @param panelId protocol value
         * @return a stage completing with the command result
         */
        public CompletionStage<Target.TargetID> openDevTools(Target.TargetID targetId, String panelId) {
            return call("Target.openDevTools", params("targetId", targetId, "panelId", panelId), TARGET_ID::require);
        }
        /**
         * Opens a DevTools window for the target.
         * <p><b>Experimental:</b> this part of CDP may change without notice.
         * @param targetId protocol value
         * @return a stage completing with the command result
         */
        public CompletionStage<Target.TargetID> openDevTools(Target.TargetID targetId) {
            return call("Target.openDevTools", params("targetId", targetId), TARGET_ID::require);
        }
        /**
         * Opens a DevTools window for the target.
         * <p><b>Experimental:</b> this part of CDP may change without notice.
         * @param request request parameters
         * @return a stage completing with the command result
         */
        public CompletionStage<Target.TargetID> openDevTools(OpenDevToolsRequest request) {
            return call("Target.openDevTools", request, TARGET_ID::require);
        }
        /**
         * Issued when attached to target because of auto-attach or {@code attachToTarget} command.
         * <p><b>Experimental:</b> this part of CDP may change without notice.
         * @param handler event callback
         * @return a removable subscription
         */
        public CdpSubscription onAttachedToTarget(Consumer<AttachedToTargetEvent> handler) {
            return on("Target.attachedToTarget", AttachedToTargetEvent::fromMap, handler);
        }
        /**
         * Issued when detached from target for any reason (including {@code detachFromTarget} command). Can be issued multiple times per target if multiple sessions have been attached to it.
         * <p><b>Experimental:</b> this part of CDP may change without notice.
         * @param handler event callback
         * @return a removable subscription
         */
        public CdpSubscription onDetachedFromTarget(Consumer<DetachedFromTargetEvent> handler) {
            return on("Target.detachedFromTarget", DetachedFromTargetEvent::fromMap, handler);
        }
        /**
         * Notifies about a new protocol message received from the session (as reported in {@code attachedToTarget} event).
         * @param handler event callback
         * @return a removable subscription
         */
        public CdpSubscription onReceivedMessageFromTarget(Consumer<ReceivedMessageFromTargetEvent> handler) {
            return on("Target.receivedMessageFromTarget", ReceivedMessageFromTargetEvent::fromMap, handler);
        }
        /**
         * Issued when a possible inspection target is created.
         * @param handler event callback
         * @return a removable subscription
         */
        public CdpSubscription onTargetCreated(Consumer<TargetCreatedEvent> handler) {
            return on("Target.targetCreated", TargetCreatedEvent::fromMap, handler);
        }
        /**
         * Issued when a target is destroyed.
         * @param handler event callback
         * @return a removable subscription
         */
        public CdpSubscription onTargetDestroyed(Consumer<TargetDestroyedEvent> handler) {
            return on("Target.targetDestroyed", TargetDestroyedEvent::fromMap, handler);
        }
        /**
         * Issued when a target has crashed.
         * @param handler event callback
         * @return a removable subscription
         */
        public CdpSubscription onTargetCrashed(Consumer<TargetCrashedEvent> handler) {
            return on("Target.targetCrashed", TargetCrashedEvent::fromMap, handler);
        }
        /**
         * Issued when some information about a target has changed. This only happens between {@code targetCreated} and {@code targetDestroyed}.
         * @param handler event callback
         * @return a removable subscription
         */
        public CdpSubscription onTargetInfoChanged(Consumer<TargetInfoChangedEvent> handler) {
            return on("Target.targetInfoChanged", TargetInfoChangedEvent::fromMap, handler);
        }
    }
}
