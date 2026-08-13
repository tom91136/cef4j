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
 * Supports additional targets discovery and allows to attach to them.
 * @see <a href="https://chromium.googlesource.com/chromium/src/+/refs/tags/146.0.7680.165/third_party/blink/public/devtools_protocol/domains/Target.pdl">Pinned protocol source</a>
 */
@SuppressWarnings({"JavaLangClash", "UnusedMethod"})
public final class Target {
    private Target() {}
    @Nullable private static Long numberAsLong(@Nullable Object value) { return value == null ? null : ((Number) value).longValue(); }
    @Nullable private static Double numberAsDouble(@Nullable Object value) { return value == null ? null : ((Number) value).doubleValue(); }
    /**
     */
    public static final class TargetInfo extends CdpObject {
        private TargetInfo(Map<String, Object> values) { super(values); }
        @Nullable public static TargetInfo fromMap(@Nullable Map<String, Object> values) {
            return values == null ? null : new TargetInfo(values);
        }
        public static Builder builder() { return new Builder(); }
        /**
         * Returns the targetId field.
         * @return the protocol field value
         */
        @Nullable public String targetId() {
            return (String) value("targetId");
        }
        /**
         * List of types: https://source.chromium.org/chromium/chromium/src/+/main:content/browser/devtools/devtools_agent_host_impl.cc?ss=chromium&amp;q=f:devtools%20-f:out%20%22::kTypeTab%5B%5D%22
         * @return the protocol field value
         */
        @Nullable public String type() {
            return (String) value("type");
        }
        /**
         * Returns the title field.
         * @return the protocol field value
         */
        @Nullable public String title() {
            return (String) value("title");
        }
        /**
         * Returns the url field.
         * @return the protocol field value
         */
        @Nullable public String url() {
            return (String) value("url");
        }
        /**
         * Whether the target has an attached client.
         * @return the protocol field value
         */
        @Nullable public Boolean attached() {
            return (Boolean) value("attached");
        }
        /**
         * Opener target Id
         * @return the protocol field value
         */
        @Nullable public String openerId() {
            return (String) value("openerId");
        }
        /**
         * Whether the target has access to the originating window.
         * <p><b>Experimental:</b> this part of CDP may change without notice.
         * @return the protocol field value
         */
        @Nullable public Boolean canAccessOpener() {
            return (Boolean) value("canAccessOpener");
        }
        /**
         * Frame id of originating window (is only set if target has an opener).
         * <p><b>Experimental:</b> this part of CDP may change without notice.
         * @return the protocol field value
         */
        @Nullable public String openerFrameId() {
            return (String) value("openerFrameId");
        }
        /**
         * Id of the parent frame, only present for the &quot;iframe&quot; targets.
         * <p><b>Experimental:</b> this part of CDP may change without notice.
         * @return the protocol field value
         */
        @Nullable public String parentFrameId() {
            return (String) value("parentFrameId");
        }
        /**
         * Returns the browserContextId field.
         * <p><b>Experimental:</b> this part of CDP may change without notice.
         * @return the protocol field value
         */
        @Nullable public String browserContextId() {
            return (String) value("browserContextId");
        }
        /**
         * Provides additional details for specific target types. For example, for the type of &quot;page&quot;, this may be set to &quot;prerender&quot;.
         * <p><b>Experimental:</b> this part of CDP may change without notice.
         * @return the protocol field value
         */
        @Nullable public String subtype() {
            return (String) value("subtype");
        }
        public static final class Builder {
            private final Map<String, Object> values = new LinkedHashMap<>();
            /**
             * Sets the targetId field.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder targetId(@Nullable String value) {
                if (value == null) values.remove("targetId");
                else values.put("targetId", jsonValue(value));
                return this;
            }
            /**
             * List of types: https://source.chromium.org/chromium/chromium/src/+/main:content/browser/devtools/devtools_agent_host_impl.cc?ss=chromium&amp;q=f:devtools%20-f:out%20%22::kTypeTab%5B%5D%22
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder type(@Nullable String value) {
                if (value == null) values.remove("type");
                else values.put("type", jsonValue(value));
                return this;
            }
            /**
             * Sets the title field.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder title(@Nullable String value) {
                if (value == null) values.remove("title");
                else values.put("title", jsonValue(value));
                return this;
            }
            /**
             * Sets the url field.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder url(@Nullable String value) {
                if (value == null) values.remove("url");
                else values.put("url", jsonValue(value));
                return this;
            }
            /**
             * Whether the target has an attached client.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder attached(@Nullable Boolean value) {
                if (value == null) values.remove("attached");
                else values.put("attached", jsonValue(value));
                return this;
            }
            /**
             * Opener target Id
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder openerId(@Nullable String value) {
                if (value == null) values.remove("openerId");
                else values.put("openerId", jsonValue(value));
                return this;
            }
            /**
             * Whether the target has access to the originating window.
             * <p><b>Experimental:</b> this part of CDP may change without notice.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder canAccessOpener(@Nullable Boolean value) {
                if (value == null) values.remove("canAccessOpener");
                else values.put("canAccessOpener", jsonValue(value));
                return this;
            }
            /**
             * Frame id of originating window (is only set if target has an opener).
             * <p><b>Experimental:</b> this part of CDP may change without notice.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder openerFrameId(@Nullable String value) {
                if (value == null) values.remove("openerFrameId");
                else values.put("openerFrameId", jsonValue(value));
                return this;
            }
            /**
             * Id of the parent frame, only present for the &quot;iframe&quot; targets.
             * <p><b>Experimental:</b> this part of CDP may change without notice.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder parentFrameId(@Nullable String value) {
                if (value == null) values.remove("parentFrameId");
                else values.put("parentFrameId", jsonValue(value));
                return this;
            }
            /**
             * Sets the browserContextId field.
             * <p><b>Experimental:</b> this part of CDP may change without notice.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder browserContextId(@Nullable String value) {
                if (value == null) values.remove("browserContextId");
                else values.put("browserContextId", jsonValue(value));
                return this;
            }
            /**
             * Provides additional details for specific target types. For example, for the type of &quot;page&quot;, this may be set to &quot;prerender&quot;.
             * <p><b>Experimental:</b> this part of CDP may change without notice.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder subtype(@Nullable String value) {
                if (value == null) values.remove("subtype");
                else values.put("subtype", jsonValue(value));
                return this;
            }
            public TargetInfo build() {
                if (!values.containsKey("targetId")) throw new IllegalStateException("Missing required CDP field: targetId");
                if (!values.containsKey("type")) throw new IllegalStateException("Missing required CDP field: type");
                if (!values.containsKey("title")) throw new IllegalStateException("Missing required CDP field: title");
                if (!values.containsKey("url")) throw new IllegalStateException("Missing required CDP field: url");
                if (!values.containsKey("attached")) throw new IllegalStateException("Missing required CDP field: attached");
                if (!values.containsKey("canAccessOpener")) throw new IllegalStateException("Missing required CDP field: canAccessOpener");
                return new TargetInfo(values);
            }
        }
    }
    /**
     * A filter used by target query/discovery/auto-attach operations.
     * <p><b>Experimental:</b> this part of CDP may change without notice.
     */
    public static final class FilterEntry extends CdpObject {
        private FilterEntry(Map<String, Object> values) { super(values); }
        @Nullable public static FilterEntry fromMap(@Nullable Map<String, Object> values) {
            return values == null ? null : new FilterEntry(values);
        }
        public static Builder builder() { return new Builder(); }
        /**
         * If set, causes exclusion of matching targets from the list.
         * @return the protocol field value
         */
        @Nullable public Boolean exclude() {
            return (Boolean) value("exclude");
        }
        /**
         * If not present, matches any type.
         * @return the protocol field value
         */
        @Nullable public String type() {
            return (String) value("type");
        }
        public static final class Builder {
            private final Map<String, Object> values = new LinkedHashMap<>();
            /**
             * If set, causes exclusion of matching targets from the list.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder exclude(@Nullable Boolean value) {
                if (value == null) values.remove("exclude");
                else values.put("exclude", jsonValue(value));
                return this;
            }
            /**
             * If not present, matches any type.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder type(@Nullable String value) {
                if (value == null) values.remove("type");
                else values.put("type", jsonValue(value));
                return this;
            }
            public FilterEntry build() {
                return new FilterEntry(values);
            }
        }
    }
    /**
     * <p><b>Experimental:</b> this part of CDP may change without notice.
     */
    public static final class RemoteLocation extends CdpObject {
        private RemoteLocation(Map<String, Object> values) { super(values); }
        @Nullable public static RemoteLocation fromMap(@Nullable Map<String, Object> values) {
            return values == null ? null : new RemoteLocation(values);
        }
        public static Builder builder() { return new Builder(); }
        /**
         * Returns the host field.
         * @return the protocol field value
         */
        @Nullable public String host() {
            return (String) value("host");
        }
        /**
         * Returns the port field.
         * @return the protocol field value
         */
        @Nullable public Long port() {
            return numberAsLong(value("port"));
        }
        public static final class Builder {
            private final Map<String, Object> values = new LinkedHashMap<>();
            /**
             * Sets the host field.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder host(@Nullable String value) {
                if (value == null) values.remove("host");
                else values.put("host", jsonValue(value));
                return this;
            }
            /**
             * Sets the port field.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder port(@Nullable Long value) {
                if (value == null) values.remove("port");
                else values.put("port", jsonValue(value));
                return this;
            }
            public RemoteLocation build() {
                if (!values.containsKey("host")) throw new IllegalStateException("Missing required CDP field: host");
                if (!values.containsKey("port")) throw new IllegalStateException("Missing required CDP field: port");
                return new RemoteLocation(values);
            }
        }
    }
    /**
     * The state of the target window.
     * <p><b>Experimental:</b> this part of CDP may change without notice.
     */
    public static final class WindowState {
        private WindowState() {}
        public static final String NORMAL = "normal";
        public static final String MINIMIZED = "minimized";
        public static final String MAXIMIZED = "maximized";
        public static final String FULLSCREEN = "fullscreen";
    }
    /**
     * Activates (focuses) the target.
     */
    public static final class ActivateTargetParams extends CdpObject {
        private ActivateTargetParams(Map<String, Object> values) { super(values); }
        @Nullable public static ActivateTargetParams fromMap(@Nullable Map<String, Object> values) {
            return values == null ? null : new ActivateTargetParams(values);
        }
        public static Builder builder() { return new Builder(); }
        /**
         * Returns the targetId field.
         * @return the protocol field value
         */
        @Nullable public String targetId() {
            return (String) value("targetId");
        }
        public static final class Builder {
            private final Map<String, Object> values = new LinkedHashMap<>();
            /**
             * Sets the targetId field.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder targetId(@Nullable String value) {
                if (value == null) values.remove("targetId");
                else values.put("targetId", jsonValue(value));
                return this;
            }
            public ActivateTargetParams build() {
                if (!values.containsKey("targetId")) throw new IllegalStateException("Missing required CDP field: targetId");
                return new ActivateTargetParams(values);
            }
        }
    }
    /**
     * Activates (focuses) the target.
     */
    public static final class ActivateTargetResult extends CdpObject {
        private ActivateTargetResult(Map<String, Object> values) { super(values); }
        @Nullable public static ActivateTargetResult fromMap(@Nullable Map<String, Object> values) {
            return values == null ? null : new ActivateTargetResult(values);
        }
        public static Builder builder() { return new Builder(); }
        public static final class Builder {
            private final Map<String, Object> values = new LinkedHashMap<>();
            public ActivateTargetResult build() {
                return new ActivateTargetResult(values);
            }
        }
    }
    /**
     * Attaches to the target with given id.
     */
    public static final class AttachToTargetParams extends CdpObject {
        private AttachToTargetParams(Map<String, Object> values) { super(values); }
        @Nullable public static AttachToTargetParams fromMap(@Nullable Map<String, Object> values) {
            return values == null ? null : new AttachToTargetParams(values);
        }
        public static Builder builder() { return new Builder(); }
        /**
         * Returns the targetId field.
         * @return the protocol field value
         */
        @Nullable public String targetId() {
            return (String) value("targetId");
        }
        /**
         * Enables &quot;flat&quot; access to the session via specifying sessionId attribute in the commands. We plan to make this the default, deprecate non-flattened mode, and eventually retire it. See crbug.com/991325.
         * @return the protocol field value
         */
        @Nullable public Boolean flatten() {
            return (Boolean) value("flatten");
        }
        public static final class Builder {
            private final Map<String, Object> values = new LinkedHashMap<>();
            /**
             * Sets the targetId field.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder targetId(@Nullable String value) {
                if (value == null) values.remove("targetId");
                else values.put("targetId", jsonValue(value));
                return this;
            }
            /**
             * Enables &quot;flat&quot; access to the session via specifying sessionId attribute in the commands. We plan to make this the default, deprecate non-flattened mode, and eventually retire it. See crbug.com/991325.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder flatten(@Nullable Boolean value) {
                if (value == null) values.remove("flatten");
                else values.put("flatten", jsonValue(value));
                return this;
            }
            public AttachToTargetParams build() {
                if (!values.containsKey("targetId")) throw new IllegalStateException("Missing required CDP field: targetId");
                return new AttachToTargetParams(values);
            }
        }
    }
    /**
     * Attaches to the target with given id.
     */
    public static final class AttachToTargetResult extends CdpObject {
        private AttachToTargetResult(Map<String, Object> values) { super(values); }
        @Nullable public static AttachToTargetResult fromMap(@Nullable Map<String, Object> values) {
            return values == null ? null : new AttachToTargetResult(values);
        }
        public static Builder builder() { return new Builder(); }
        /**
         * Id assigned to the session.
         * @return the protocol field value
         */
        @Nullable public String sessionId() {
            return (String) value("sessionId");
        }
        public static final class Builder {
            private final Map<String, Object> values = new LinkedHashMap<>();
            /**
             * Id assigned to the session.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder sessionId(@Nullable String value) {
                if (value == null) values.remove("sessionId");
                else values.put("sessionId", jsonValue(value));
                return this;
            }
            public AttachToTargetResult build() {
                if (!values.containsKey("sessionId")) throw new IllegalStateException("Missing required CDP field: sessionId");
                return new AttachToTargetResult(values);
            }
        }
    }
    /**
     * Attaches to the browser target, only uses flat sessionId mode.
     * <p><b>Experimental:</b> this part of CDP may change without notice.
     */
    public static final class AttachToBrowserTargetParams extends CdpObject {
        private AttachToBrowserTargetParams(Map<String, Object> values) { super(values); }
        @Nullable public static AttachToBrowserTargetParams fromMap(@Nullable Map<String, Object> values) {
            return values == null ? null : new AttachToBrowserTargetParams(values);
        }
        public static Builder builder() { return new Builder(); }
        public static final class Builder {
            private final Map<String, Object> values = new LinkedHashMap<>();
            public AttachToBrowserTargetParams build() {
                return new AttachToBrowserTargetParams(values);
            }
        }
    }
    /**
     * Attaches to the browser target, only uses flat sessionId mode.
     * <p><b>Experimental:</b> this part of CDP may change without notice.
     */
    public static final class AttachToBrowserTargetResult extends CdpObject {
        private AttachToBrowserTargetResult(Map<String, Object> values) { super(values); }
        @Nullable public static AttachToBrowserTargetResult fromMap(@Nullable Map<String, Object> values) {
            return values == null ? null : new AttachToBrowserTargetResult(values);
        }
        public static Builder builder() { return new Builder(); }
        /**
         * Id assigned to the session.
         * @return the protocol field value
         */
        @Nullable public String sessionId() {
            return (String) value("sessionId");
        }
        public static final class Builder {
            private final Map<String, Object> values = new LinkedHashMap<>();
            /**
             * Id assigned to the session.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder sessionId(@Nullable String value) {
                if (value == null) values.remove("sessionId");
                else values.put("sessionId", jsonValue(value));
                return this;
            }
            public AttachToBrowserTargetResult build() {
                if (!values.containsKey("sessionId")) throw new IllegalStateException("Missing required CDP field: sessionId");
                return new AttachToBrowserTargetResult(values);
            }
        }
    }
    /**
     * Closes the target. If the target is a page that gets closed too.
     */
    public static final class CloseTargetParams extends CdpObject {
        private CloseTargetParams(Map<String, Object> values) { super(values); }
        @Nullable public static CloseTargetParams fromMap(@Nullable Map<String, Object> values) {
            return values == null ? null : new CloseTargetParams(values);
        }
        public static Builder builder() { return new Builder(); }
        /**
         * Returns the targetId field.
         * @return the protocol field value
         */
        @Nullable public String targetId() {
            return (String) value("targetId");
        }
        public static final class Builder {
            private final Map<String, Object> values = new LinkedHashMap<>();
            /**
             * Sets the targetId field.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder targetId(@Nullable String value) {
                if (value == null) values.remove("targetId");
                else values.put("targetId", jsonValue(value));
                return this;
            }
            public CloseTargetParams build() {
                if (!values.containsKey("targetId")) throw new IllegalStateException("Missing required CDP field: targetId");
                return new CloseTargetParams(values);
            }
        }
    }
    /**
     * Closes the target. If the target is a page that gets closed too.
     */
    public static final class CloseTargetResult extends CdpObject {
        private CloseTargetResult(Map<String, Object> values) { super(values); }
        @Nullable public static CloseTargetResult fromMap(@Nullable Map<String, Object> values) {
            return values == null ? null : new CloseTargetResult(values);
        }
        public static Builder builder() { return new Builder(); }
        /**
         * Always set to true. If an error occurs, the response indicates protocol error.
         * @return the protocol field value
         * @deprecated Deprecated by the Chromium DevTools Protocol.
         */
        @Deprecated
        @Nullable public Boolean success() {
            return (Boolean) value("success");
        }
        public static final class Builder {
            private final Map<String, Object> values = new LinkedHashMap<>();
            /**
             * Always set to true. If an error occurs, the response indicates protocol error.
             * @param value field value; null removes an optional value
             * @return this builder
             * @deprecated Deprecated by the Chromium DevTools Protocol.
             */
            @Deprecated
            public Builder success(@Nullable Boolean value) {
                if (value == null) values.remove("success");
                else values.put("success", jsonValue(value));
                return this;
            }
            public CloseTargetResult build() {
                if (!values.containsKey("success")) throw new IllegalStateException("Missing required CDP field: success");
                return new CloseTargetResult(values);
            }
        }
    }
    /**
     * Inject object to the target&#x27;s main frame that provides a communication channel with browser target.
     * <p>Injected object will be available as {@code window[bindingName]}.
     * <p>The object has the following API: - {@code binding.send(json)} - a method to send messages over the remote debugging protocol - {@code binding.onmessage = json =&gt; handleMessage(json)} - a callback that will be called for the protocol notifications and command responses.
     * <p><b>Experimental:</b> this part of CDP may change without notice.
     */
    public static final class ExposeDevToolsProtocolParams extends CdpObject {
        private ExposeDevToolsProtocolParams(Map<String, Object> values) { super(values); }
        @Nullable public static ExposeDevToolsProtocolParams fromMap(@Nullable Map<String, Object> values) {
            return values == null ? null : new ExposeDevToolsProtocolParams(values);
        }
        public static Builder builder() { return new Builder(); }
        /**
         * Returns the targetId field.
         * @return the protocol field value
         */
        @Nullable public String targetId() {
            return (String) value("targetId");
        }
        /**
         * Binding name, &#x27;cdp&#x27; if not specified.
         * @return the protocol field value
         */
        @Nullable public String bindingName() {
            return (String) value("bindingName");
        }
        /**
         * If true, inherits the current root session&#x27;s permissions (default: false).
         * @return the protocol field value
         */
        @Nullable public Boolean inheritPermissions() {
            return (Boolean) value("inheritPermissions");
        }
        public static final class Builder {
            private final Map<String, Object> values = new LinkedHashMap<>();
            /**
             * Sets the targetId field.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder targetId(@Nullable String value) {
                if (value == null) values.remove("targetId");
                else values.put("targetId", jsonValue(value));
                return this;
            }
            /**
             * Binding name, &#x27;cdp&#x27; if not specified.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder bindingName(@Nullable String value) {
                if (value == null) values.remove("bindingName");
                else values.put("bindingName", jsonValue(value));
                return this;
            }
            /**
             * If true, inherits the current root session&#x27;s permissions (default: false).
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder inheritPermissions(@Nullable Boolean value) {
                if (value == null) values.remove("inheritPermissions");
                else values.put("inheritPermissions", jsonValue(value));
                return this;
            }
            public ExposeDevToolsProtocolParams build() {
                if (!values.containsKey("targetId")) throw new IllegalStateException("Missing required CDP field: targetId");
                return new ExposeDevToolsProtocolParams(values);
            }
        }
    }
    /**
     * Inject object to the target&#x27;s main frame that provides a communication channel with browser target.
     * <p>Injected object will be available as {@code window[bindingName]}.
     * <p>The object has the following API: - {@code binding.send(json)} - a method to send messages over the remote debugging protocol - {@code binding.onmessage = json =&gt; handleMessage(json)} - a callback that will be called for the protocol notifications and command responses.
     * <p><b>Experimental:</b> this part of CDP may change without notice.
     */
    public static final class ExposeDevToolsProtocolResult extends CdpObject {
        private ExposeDevToolsProtocolResult(Map<String, Object> values) { super(values); }
        @Nullable public static ExposeDevToolsProtocolResult fromMap(@Nullable Map<String, Object> values) {
            return values == null ? null : new ExposeDevToolsProtocolResult(values);
        }
        public static Builder builder() { return new Builder(); }
        public static final class Builder {
            private final Map<String, Object> values = new LinkedHashMap<>();
            public ExposeDevToolsProtocolResult build() {
                return new ExposeDevToolsProtocolResult(values);
            }
        }
    }
    /**
     * Creates a new empty BrowserContext. Similar to an incognito profile but you can have more than one.
     */
    public static final class CreateBrowserContextParams extends CdpObject {
        private CreateBrowserContextParams(Map<String, Object> values) { super(values); }
        @Nullable public static CreateBrowserContextParams fromMap(@Nullable Map<String, Object> values) {
            return values == null ? null : new CreateBrowserContextParams(values);
        }
        public static Builder builder() { return new Builder(); }
        /**
         * If specified, disposes this context when debugging session disconnects.
         * <p><b>Experimental:</b> this part of CDP may change without notice.
         * @return the protocol field value
         */
        @Nullable public Boolean disposeOnDetach() {
            return (Boolean) value("disposeOnDetach");
        }
        /**
         * Proxy server, similar to the one passed to --proxy-server
         * <p><b>Experimental:</b> this part of CDP may change without notice.
         * @return the protocol field value
         */
        @Nullable public String proxyServer() {
            return (String) value("proxyServer");
        }
        /**
         * Proxy bypass list, similar to the one passed to --proxy-bypass-list
         * <p><b>Experimental:</b> this part of CDP may change without notice.
         * @return the protocol field value
         */
        @Nullable public String proxyBypassList() {
            return (String) value("proxyBypassList");
        }
        /**
         * An optional list of origins to grant unlimited cross-origin access to. Parts of the URL other than those constituting origin are ignored.
         * <p><b>Experimental:</b> this part of CDP may change without notice.
         * @return the protocol field value
         */
        @Nullable public java.util.List<String> originsWithUniversalNetworkAccess() {
            return list(value("originsWithUniversalNetworkAccess"), element0 -> (String) element0);
        }
        public static final class Builder {
            private final Map<String, Object> values = new LinkedHashMap<>();
            /**
             * If specified, disposes this context when debugging session disconnects.
             * <p><b>Experimental:</b> this part of CDP may change without notice.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder disposeOnDetach(@Nullable Boolean value) {
                if (value == null) values.remove("disposeOnDetach");
                else values.put("disposeOnDetach", jsonValue(value));
                return this;
            }
            /**
             * Proxy server, similar to the one passed to --proxy-server
             * <p><b>Experimental:</b> this part of CDP may change without notice.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder proxyServer(@Nullable String value) {
                if (value == null) values.remove("proxyServer");
                else values.put("proxyServer", jsonValue(value));
                return this;
            }
            /**
             * Proxy bypass list, similar to the one passed to --proxy-bypass-list
             * <p><b>Experimental:</b> this part of CDP may change without notice.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder proxyBypassList(@Nullable String value) {
                if (value == null) values.remove("proxyBypassList");
                else values.put("proxyBypassList", jsonValue(value));
                return this;
            }
            /**
             * An optional list of origins to grant unlimited cross-origin access to. Parts of the URL other than those constituting origin are ignored.
             * <p><b>Experimental:</b> this part of CDP may change without notice.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder originsWithUniversalNetworkAccess(@Nullable java.util.List<String> value) {
                if (value == null) values.remove("originsWithUniversalNetworkAccess");
                else values.put("originsWithUniversalNetworkAccess", jsonValue(value));
                return this;
            }
            public CreateBrowserContextParams build() {
                return new CreateBrowserContextParams(values);
            }
        }
    }
    /**
     * Creates a new empty BrowserContext. Similar to an incognito profile but you can have more than one.
     */
    public static final class CreateBrowserContextResult extends CdpObject {
        private CreateBrowserContextResult(Map<String, Object> values) { super(values); }
        @Nullable public static CreateBrowserContextResult fromMap(@Nullable Map<String, Object> values) {
            return values == null ? null : new CreateBrowserContextResult(values);
        }
        public static Builder builder() { return new Builder(); }
        /**
         * The id of the context created.
         * @return the protocol field value
         */
        @Nullable public String browserContextId() {
            return (String) value("browserContextId");
        }
        public static final class Builder {
            private final Map<String, Object> values = new LinkedHashMap<>();
            /**
             * The id of the context created.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder browserContextId(@Nullable String value) {
                if (value == null) values.remove("browserContextId");
                else values.put("browserContextId", jsonValue(value));
                return this;
            }
            public CreateBrowserContextResult build() {
                if (!values.containsKey("browserContextId")) throw new IllegalStateException("Missing required CDP field: browserContextId");
                return new CreateBrowserContextResult(values);
            }
        }
    }
    /**
     * Returns all browser contexts created with {@code Target.createBrowserContext} method.
     */
    public static final class GetBrowserContextsParams extends CdpObject {
        private GetBrowserContextsParams(Map<String, Object> values) { super(values); }
        @Nullable public static GetBrowserContextsParams fromMap(@Nullable Map<String, Object> values) {
            return values == null ? null : new GetBrowserContextsParams(values);
        }
        public static Builder builder() { return new Builder(); }
        public static final class Builder {
            private final Map<String, Object> values = new LinkedHashMap<>();
            public GetBrowserContextsParams build() {
                return new GetBrowserContextsParams(values);
            }
        }
    }
    /**
     * Returns all browser contexts created with {@code Target.createBrowserContext} method.
     */
    public static final class GetBrowserContextsResult extends CdpObject {
        private GetBrowserContextsResult(Map<String, Object> values) { super(values); }
        @Nullable public static GetBrowserContextsResult fromMap(@Nullable Map<String, Object> values) {
            return values == null ? null : new GetBrowserContextsResult(values);
        }
        public static Builder builder() { return new Builder(); }
        /**
         * An array of browser context ids.
         * @return the protocol field value
         */
        @Nullable public java.util.List<String> browserContextIds() {
            return list(value("browserContextIds"), element0 -> (String) element0);
        }
        /**
         * The id of the default browser context if available.
         * <p><b>Experimental:</b> this part of CDP may change without notice.
         * @return the protocol field value
         */
        @Nullable public String defaultBrowserContextId() {
            return (String) value("defaultBrowserContextId");
        }
        public static final class Builder {
            private final Map<String, Object> values = new LinkedHashMap<>();
            /**
             * An array of browser context ids.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder browserContextIds(@Nullable java.util.List<String> value) {
                if (value == null) values.remove("browserContextIds");
                else values.put("browserContextIds", jsonValue(value));
                return this;
            }
            /**
             * The id of the default browser context if available.
             * <p><b>Experimental:</b> this part of CDP may change without notice.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder defaultBrowserContextId(@Nullable String value) {
                if (value == null) values.remove("defaultBrowserContextId");
                else values.put("defaultBrowserContextId", jsonValue(value));
                return this;
            }
            public GetBrowserContextsResult build() {
                if (!values.containsKey("browserContextIds")) throw new IllegalStateException("Missing required CDP field: browserContextIds");
                return new GetBrowserContextsResult(values);
            }
        }
    }
    /**
     * Creates a new page.
     */
    public static final class CreateTargetParams extends CdpObject {
        private CreateTargetParams(Map<String, Object> values) { super(values); }
        @Nullable public static CreateTargetParams fromMap(@Nullable Map<String, Object> values) {
            return values == null ? null : new CreateTargetParams(values);
        }
        public static Builder builder() { return new Builder(); }
        /**
         * The initial URL the page will be navigated to. An empty string indicates about:blank.
         * @return the protocol field value
         */
        @Nullable public String url() {
            return (String) value("url");
        }
        /**
         * Frame left origin in DIP (requires newWindow to be true or headless shell).
         * <p><b>Experimental:</b> this part of CDP may change without notice.
         * @return the protocol field value
         */
        @Nullable public Long left() {
            return numberAsLong(value("left"));
        }
        /**
         * Frame top origin in DIP (requires newWindow to be true or headless shell).
         * <p><b>Experimental:</b> this part of CDP may change without notice.
         * @return the protocol field value
         */
        @Nullable public Long top() {
            return numberAsLong(value("top"));
        }
        /**
         * Frame width in DIP (requires newWindow to be true or headless shell).
         * @return the protocol field value
         */
        @Nullable public Long width() {
            return numberAsLong(value("width"));
        }
        /**
         * Frame height in DIP (requires newWindow to be true or headless shell).
         * @return the protocol field value
         */
        @Nullable public Long height() {
            return numberAsLong(value("height"));
        }
        /**
         * Frame window state (requires newWindow to be true or headless shell). Default is normal.
         * @return the protocol field value
         */
        @Nullable public String windowState() {
            return (String) value("windowState");
        }
        /**
         * The browser context to create the page in.
         * <p><b>Experimental:</b> this part of CDP may change without notice.
         * @return the protocol field value
         */
        @Nullable public String browserContextId() {
            return (String) value("browserContextId");
        }
        /**
         * Whether BeginFrames for this target will be controlled via DevTools (headless shell only, not supported on MacOS yet, false by default).
         * <p><b>Experimental:</b> this part of CDP may change without notice.
         * @return the protocol field value
         */
        @Nullable public Boolean enableBeginFrameControl() {
            return (Boolean) value("enableBeginFrameControl");
        }
        /**
         * Whether to create a new Window or Tab (false by default, not supported by headless shell).
         * @return the protocol field value
         */
        @Nullable public Boolean newWindow() {
            return (Boolean) value("newWindow");
        }
        /**
         * Whether to create the target in background or foreground (false by default, not supported by headless shell).
         * @return the protocol field value
         */
        @Nullable public Boolean background() {
            return (Boolean) value("background");
        }
        /**
         * Whether to create the target of type &quot;tab&quot;.
         * <p><b>Experimental:</b> this part of CDP may change without notice.
         * @return the protocol field value
         */
        @Nullable public Boolean forTab() {
            return (Boolean) value("forTab");
        }
        /**
         * Whether to create a hidden target. The hidden target is observable via protocol, but not present in the tab UI strip. Cannot be created with {@code forTab: true}, {@code newWindow: true} or {@code background: false}. The life-time of the tab is limited to the life-time of the session.
         * <p><b>Experimental:</b> this part of CDP may change without notice.
         * @return the protocol field value
         */
        @Nullable public Boolean hidden() {
            return (Boolean) value("hidden");
        }
        /**
         * If specified, the option is used to determine if the new target should be focused or not. By default, the focus behavior depends on the value of the background field. For example, background=false and focus=false will result in the target tab being opened but the browser window remain unchanged (if it was in the background, it will remain in the background) and background=false with focus=undefined will result in the window being focused. Using background: true and focus: true is not supported and will result in an error.
         * <p><b>Experimental:</b> this part of CDP may change without notice.
         * @return the protocol field value
         */
        @Nullable public Boolean focus() {
            return (Boolean) value("focus");
        }
        public static final class Builder {
            private final Map<String, Object> values = new LinkedHashMap<>();
            /**
             * The initial URL the page will be navigated to. An empty string indicates about:blank.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder url(@Nullable String value) {
                if (value == null) values.remove("url");
                else values.put("url", jsonValue(value));
                return this;
            }
            /**
             * Frame left origin in DIP (requires newWindow to be true or headless shell).
             * <p><b>Experimental:</b> this part of CDP may change without notice.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder left(@Nullable Long value) {
                if (value == null) values.remove("left");
                else values.put("left", jsonValue(value));
                return this;
            }
            /**
             * Frame top origin in DIP (requires newWindow to be true or headless shell).
             * <p><b>Experimental:</b> this part of CDP may change without notice.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder top(@Nullable Long value) {
                if (value == null) values.remove("top");
                else values.put("top", jsonValue(value));
                return this;
            }
            /**
             * Frame width in DIP (requires newWindow to be true or headless shell).
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder width(@Nullable Long value) {
                if (value == null) values.remove("width");
                else values.put("width", jsonValue(value));
                return this;
            }
            /**
             * Frame height in DIP (requires newWindow to be true or headless shell).
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder height(@Nullable Long value) {
                if (value == null) values.remove("height");
                else values.put("height", jsonValue(value));
                return this;
            }
            /**
             * Frame window state (requires newWindow to be true or headless shell). Default is normal.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder windowState(@Nullable String value) {
                if (value == null) values.remove("windowState");
                else values.put("windowState", jsonValue(value));
                return this;
            }
            /**
             * The browser context to create the page in.
             * <p><b>Experimental:</b> this part of CDP may change without notice.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder browserContextId(@Nullable String value) {
                if (value == null) values.remove("browserContextId");
                else values.put("browserContextId", jsonValue(value));
                return this;
            }
            /**
             * Whether BeginFrames for this target will be controlled via DevTools (headless shell only, not supported on MacOS yet, false by default).
             * <p><b>Experimental:</b> this part of CDP may change without notice.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder enableBeginFrameControl(@Nullable Boolean value) {
                if (value == null) values.remove("enableBeginFrameControl");
                else values.put("enableBeginFrameControl", jsonValue(value));
                return this;
            }
            /**
             * Whether to create a new Window or Tab (false by default, not supported by headless shell).
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder newWindow(@Nullable Boolean value) {
                if (value == null) values.remove("newWindow");
                else values.put("newWindow", jsonValue(value));
                return this;
            }
            /**
             * Whether to create the target in background or foreground (false by default, not supported by headless shell).
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder background(@Nullable Boolean value) {
                if (value == null) values.remove("background");
                else values.put("background", jsonValue(value));
                return this;
            }
            /**
             * Whether to create the target of type &quot;tab&quot;.
             * <p><b>Experimental:</b> this part of CDP may change without notice.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder forTab(@Nullable Boolean value) {
                if (value == null) values.remove("forTab");
                else values.put("forTab", jsonValue(value));
                return this;
            }
            /**
             * Whether to create a hidden target. The hidden target is observable via protocol, but not present in the tab UI strip. Cannot be created with {@code forTab: true}, {@code newWindow: true} or {@code background: false}. The life-time of the tab is limited to the life-time of the session.
             * <p><b>Experimental:</b> this part of CDP may change without notice.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder hidden(@Nullable Boolean value) {
                if (value == null) values.remove("hidden");
                else values.put("hidden", jsonValue(value));
                return this;
            }
            /**
             * If specified, the option is used to determine if the new target should be focused or not. By default, the focus behavior depends on the value of the background field. For example, background=false and focus=false will result in the target tab being opened but the browser window remain unchanged (if it was in the background, it will remain in the background) and background=false with focus=undefined will result in the window being focused. Using background: true and focus: true is not supported and will result in an error.
             * <p><b>Experimental:</b> this part of CDP may change without notice.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder focus(@Nullable Boolean value) {
                if (value == null) values.remove("focus");
                else values.put("focus", jsonValue(value));
                return this;
            }
            public CreateTargetParams build() {
                if (!values.containsKey("url")) throw new IllegalStateException("Missing required CDP field: url");
                return new CreateTargetParams(values);
            }
        }
    }
    /**
     * Creates a new page.
     */
    public static final class CreateTargetResult extends CdpObject {
        private CreateTargetResult(Map<String, Object> values) { super(values); }
        @Nullable public static CreateTargetResult fromMap(@Nullable Map<String, Object> values) {
            return values == null ? null : new CreateTargetResult(values);
        }
        public static Builder builder() { return new Builder(); }
        /**
         * The id of the page opened.
         * @return the protocol field value
         */
        @Nullable public String targetId() {
            return (String) value("targetId");
        }
        public static final class Builder {
            private final Map<String, Object> values = new LinkedHashMap<>();
            /**
             * The id of the page opened.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder targetId(@Nullable String value) {
                if (value == null) values.remove("targetId");
                else values.put("targetId", jsonValue(value));
                return this;
            }
            public CreateTargetResult build() {
                if (!values.containsKey("targetId")) throw new IllegalStateException("Missing required CDP field: targetId");
                return new CreateTargetResult(values);
            }
        }
    }
    /**
     * Detaches session with given id.
     */
    public static final class DetachFromTargetParams extends CdpObject {
        private DetachFromTargetParams(Map<String, Object> values) { super(values); }
        @Nullable public static DetachFromTargetParams fromMap(@Nullable Map<String, Object> values) {
            return values == null ? null : new DetachFromTargetParams(values);
        }
        public static Builder builder() { return new Builder(); }
        /**
         * Session to detach.
         * @return the protocol field value
         */
        @Nullable public String sessionId() {
            return (String) value("sessionId");
        }
        /**
         * Deprecated.
         * @return the protocol field value
         * @deprecated Deprecated by the Chromium DevTools Protocol.
         */
        @Deprecated
        @Nullable public String targetId() {
            return (String) value("targetId");
        }
        public static final class Builder {
            private final Map<String, Object> values = new LinkedHashMap<>();
            /**
             * Session to detach.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder sessionId(@Nullable String value) {
                if (value == null) values.remove("sessionId");
                else values.put("sessionId", jsonValue(value));
                return this;
            }
            /**
             * Deprecated.
             * @param value field value; null removes an optional value
             * @return this builder
             * @deprecated Deprecated by the Chromium DevTools Protocol.
             */
            @Deprecated
            public Builder targetId(@Nullable String value) {
                if (value == null) values.remove("targetId");
                else values.put("targetId", jsonValue(value));
                return this;
            }
            public DetachFromTargetParams build() {
                return new DetachFromTargetParams(values);
            }
        }
    }
    /**
     * Detaches session with given id.
     */
    public static final class DetachFromTargetResult extends CdpObject {
        private DetachFromTargetResult(Map<String, Object> values) { super(values); }
        @Nullable public static DetachFromTargetResult fromMap(@Nullable Map<String, Object> values) {
            return values == null ? null : new DetachFromTargetResult(values);
        }
        public static Builder builder() { return new Builder(); }
        public static final class Builder {
            private final Map<String, Object> values = new LinkedHashMap<>();
            public DetachFromTargetResult build() {
                return new DetachFromTargetResult(values);
            }
        }
    }
    /**
     * Deletes a BrowserContext. All the belonging pages will be closed without calling their beforeunload hooks.
     */
    public static final class DisposeBrowserContextParams extends CdpObject {
        private DisposeBrowserContextParams(Map<String, Object> values) { super(values); }
        @Nullable public static DisposeBrowserContextParams fromMap(@Nullable Map<String, Object> values) {
            return values == null ? null : new DisposeBrowserContextParams(values);
        }
        public static Builder builder() { return new Builder(); }
        /**
         * Returns the browserContextId field.
         * @return the protocol field value
         */
        @Nullable public String browserContextId() {
            return (String) value("browserContextId");
        }
        public static final class Builder {
            private final Map<String, Object> values = new LinkedHashMap<>();
            /**
             * Sets the browserContextId field.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder browserContextId(@Nullable String value) {
                if (value == null) values.remove("browserContextId");
                else values.put("browserContextId", jsonValue(value));
                return this;
            }
            public DisposeBrowserContextParams build() {
                if (!values.containsKey("browserContextId")) throw new IllegalStateException("Missing required CDP field: browserContextId");
                return new DisposeBrowserContextParams(values);
            }
        }
    }
    /**
     * Deletes a BrowserContext. All the belonging pages will be closed without calling their beforeunload hooks.
     */
    public static final class DisposeBrowserContextResult extends CdpObject {
        private DisposeBrowserContextResult(Map<String, Object> values) { super(values); }
        @Nullable public static DisposeBrowserContextResult fromMap(@Nullable Map<String, Object> values) {
            return values == null ? null : new DisposeBrowserContextResult(values);
        }
        public static Builder builder() { return new Builder(); }
        public static final class Builder {
            private final Map<String, Object> values = new LinkedHashMap<>();
            public DisposeBrowserContextResult build() {
                return new DisposeBrowserContextResult(values);
            }
        }
    }
    /**
     * Returns information about a target.
     * <p><b>Experimental:</b> this part of CDP may change without notice.
     */
    public static final class GetTargetInfoParams extends CdpObject {
        private GetTargetInfoParams(Map<String, Object> values) { super(values); }
        @Nullable public static GetTargetInfoParams fromMap(@Nullable Map<String, Object> values) {
            return values == null ? null : new GetTargetInfoParams(values);
        }
        public static Builder builder() { return new Builder(); }
        /**
         * Returns the targetId field.
         * @return the protocol field value
         */
        @Nullable public String targetId() {
            return (String) value("targetId");
        }
        public static final class Builder {
            private final Map<String, Object> values = new LinkedHashMap<>();
            /**
             * Sets the targetId field.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder targetId(@Nullable String value) {
                if (value == null) values.remove("targetId");
                else values.put("targetId", jsonValue(value));
                return this;
            }
            public GetTargetInfoParams build() {
                return new GetTargetInfoParams(values);
            }
        }
    }
    /**
     * Returns information about a target.
     * <p><b>Experimental:</b> this part of CDP may change without notice.
     */
    public static final class GetTargetInfoResult extends CdpObject {
        private GetTargetInfoResult(Map<String, Object> values) { super(values); }
        @Nullable public static GetTargetInfoResult fromMap(@Nullable Map<String, Object> values) {
            return values == null ? null : new GetTargetInfoResult(values);
        }
        public static Builder builder() { return new Builder(); }
        /**
         * Returns the targetInfo field.
         * @return the protocol field value
         */
        @Nullable public Target.TargetInfo targetInfo() {
            return Target.TargetInfo.fromMap(objectMap(value("targetInfo")));
        }
        public static final class Builder {
            private final Map<String, Object> values = new LinkedHashMap<>();
            /**
             * Sets the targetInfo field.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder targetInfo(@Nullable Target.TargetInfo value) {
                if (value == null) values.remove("targetInfo");
                else values.put("targetInfo", jsonValue(value));
                return this;
            }
            public GetTargetInfoResult build() {
                if (!values.containsKey("targetInfo")) throw new IllegalStateException("Missing required CDP field: targetInfo");
                return new GetTargetInfoResult(values);
            }
        }
    }
    /**
     * Retrieves a list of available targets.
     */
    public static final class GetTargetsParams extends CdpObject {
        private GetTargetsParams(Map<String, Object> values) { super(values); }
        @Nullable public static GetTargetsParams fromMap(@Nullable Map<String, Object> values) {
            return values == null ? null : new GetTargetsParams(values);
        }
        public static Builder builder() { return new Builder(); }
        /**
         * Only targets matching filter will be reported. If filter is not specified and target discovery is currently enabled, a filter used for target discovery is used for consistency.
         * <p><b>Experimental:</b> this part of CDP may change without notice.
         * @return the protocol field value
         */
        @Nullable public java.util.List<Target.FilterEntry> filter() {
            return list(value("filter"), element0 -> Target.FilterEntry.fromMap(objectMap(element0)));
        }
        public static final class Builder {
            private final Map<String, Object> values = new LinkedHashMap<>();
            /**
             * Only targets matching filter will be reported. If filter is not specified and target discovery is currently enabled, a filter used for target discovery is used for consistency.
             * <p><b>Experimental:</b> this part of CDP may change without notice.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder filter(@Nullable java.util.List<Target.FilterEntry> value) {
                if (value == null) values.remove("filter");
                else values.put("filter", jsonValue(value));
                return this;
            }
            public GetTargetsParams build() {
                return new GetTargetsParams(values);
            }
        }
    }
    /**
     * Retrieves a list of available targets.
     */
    public static final class GetTargetsResult extends CdpObject {
        private GetTargetsResult(Map<String, Object> values) { super(values); }
        @Nullable public static GetTargetsResult fromMap(@Nullable Map<String, Object> values) {
            return values == null ? null : new GetTargetsResult(values);
        }
        public static Builder builder() { return new Builder(); }
        /**
         * The list of targets.
         * @return the protocol field value
         */
        @Nullable public java.util.List<Target.TargetInfo> targetInfos() {
            return list(value("targetInfos"), element0 -> Target.TargetInfo.fromMap(objectMap(element0)));
        }
        public static final class Builder {
            private final Map<String, Object> values = new LinkedHashMap<>();
            /**
             * The list of targets.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder targetInfos(@Nullable java.util.List<Target.TargetInfo> value) {
                if (value == null) values.remove("targetInfos");
                else values.put("targetInfos", jsonValue(value));
                return this;
            }
            public GetTargetsResult build() {
                if (!values.containsKey("targetInfos")) throw new IllegalStateException("Missing required CDP field: targetInfos");
                return new GetTargetsResult(values);
            }
        }
    }
    /**
     * Sends protocol message over session with given id. Consider using flat mode instead; see commands attachToTarget, setAutoAttach, and crbug.com/991325.
     * @deprecated Deprecated by the Chromium DevTools Protocol.
     */
    @Deprecated
    public static final class SendMessageToTargetParams extends CdpObject {
        private SendMessageToTargetParams(Map<String, Object> values) { super(values); }
        @Nullable public static SendMessageToTargetParams fromMap(@Nullable Map<String, Object> values) {
            return values == null ? null : new SendMessageToTargetParams(values);
        }
        public static Builder builder() { return new Builder(); }
        /**
         * Returns the message field.
         * @return the protocol field value
         */
        @Nullable public String message() {
            return (String) value("message");
        }
        /**
         * Identifier of the session.
         * @return the protocol field value
         */
        @Nullable public String sessionId() {
            return (String) value("sessionId");
        }
        /**
         * Deprecated.
         * @return the protocol field value
         * @deprecated Deprecated by the Chromium DevTools Protocol.
         */
        @Deprecated
        @Nullable public String targetId() {
            return (String) value("targetId");
        }
        public static final class Builder {
            private final Map<String, Object> values = new LinkedHashMap<>();
            /**
             * Sets the message field.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder message(@Nullable String value) {
                if (value == null) values.remove("message");
                else values.put("message", jsonValue(value));
                return this;
            }
            /**
             * Identifier of the session.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder sessionId(@Nullable String value) {
                if (value == null) values.remove("sessionId");
                else values.put("sessionId", jsonValue(value));
                return this;
            }
            /**
             * Deprecated.
             * @param value field value; null removes an optional value
             * @return this builder
             * @deprecated Deprecated by the Chromium DevTools Protocol.
             */
            @Deprecated
            public Builder targetId(@Nullable String value) {
                if (value == null) values.remove("targetId");
                else values.put("targetId", jsonValue(value));
                return this;
            }
            public SendMessageToTargetParams build() {
                if (!values.containsKey("message")) throw new IllegalStateException("Missing required CDP field: message");
                return new SendMessageToTargetParams(values);
            }
        }
    }
    /**
     * Sends protocol message over session with given id. Consider using flat mode instead; see commands attachToTarget, setAutoAttach, and crbug.com/991325.
     * @deprecated Deprecated by the Chromium DevTools Protocol.
     */
    @Deprecated
    public static final class SendMessageToTargetResult extends CdpObject {
        private SendMessageToTargetResult(Map<String, Object> values) { super(values); }
        @Nullable public static SendMessageToTargetResult fromMap(@Nullable Map<String, Object> values) {
            return values == null ? null : new SendMessageToTargetResult(values);
        }
        public static Builder builder() { return new Builder(); }
        public static final class Builder {
            private final Map<String, Object> values = new LinkedHashMap<>();
            public SendMessageToTargetResult build() {
                return new SendMessageToTargetResult(values);
            }
        }
    }
    /**
     * Controls whether to automatically attach to new targets which are considered to be directly related to this one (for example, iframes or workers). When turned on, attaches to all existing related targets as well. When turned off, automatically detaches from all currently attached targets. This also clears all targets added by {@code autoAttachRelated} from the list of targets to watch for creation of related targets. You might want to call this recursively for auto-attached targets to attach to all available targets.
     */
    public static final class SetAutoAttachParams extends CdpObject {
        private SetAutoAttachParams(Map<String, Object> values) { super(values); }
        @Nullable public static SetAutoAttachParams fromMap(@Nullable Map<String, Object> values) {
            return values == null ? null : new SetAutoAttachParams(values);
        }
        public static Builder builder() { return new Builder(); }
        /**
         * Whether to auto-attach to related targets.
         * @return the protocol field value
         */
        @Nullable public Boolean autoAttach() {
            return (Boolean) value("autoAttach");
        }
        /**
         * Whether to pause new targets when attaching to them. Use {@code Runtime.runIfWaitingForDebugger} to run paused targets.
         * @return the protocol field value
         */
        @Nullable public Boolean waitForDebuggerOnStart() {
            return (Boolean) value("waitForDebuggerOnStart");
        }
        /**
         * Enables &quot;flat&quot; access to the session via specifying sessionId attribute in the commands. We plan to make this the default, deprecate non-flattened mode, and eventually retire it. See crbug.com/991325.
         * <p><b>Experimental:</b> this part of CDP may change without notice.
         * @return the protocol field value
         */
        @Nullable public Boolean flatten() {
            return (Boolean) value("flatten");
        }
        /**
         * Only targets matching filter will be attached.
         * <p><b>Experimental:</b> this part of CDP may change without notice.
         * @return the protocol field value
         */
        @Nullable public java.util.List<Target.FilterEntry> filter() {
            return list(value("filter"), element0 -> Target.FilterEntry.fromMap(objectMap(element0)));
        }
        public static final class Builder {
            private final Map<String, Object> values = new LinkedHashMap<>();
            /**
             * Whether to auto-attach to related targets.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder autoAttach(@Nullable Boolean value) {
                if (value == null) values.remove("autoAttach");
                else values.put("autoAttach", jsonValue(value));
                return this;
            }
            /**
             * Whether to pause new targets when attaching to them. Use {@code Runtime.runIfWaitingForDebugger} to run paused targets.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder waitForDebuggerOnStart(@Nullable Boolean value) {
                if (value == null) values.remove("waitForDebuggerOnStart");
                else values.put("waitForDebuggerOnStart", jsonValue(value));
                return this;
            }
            /**
             * Enables &quot;flat&quot; access to the session via specifying sessionId attribute in the commands. We plan to make this the default, deprecate non-flattened mode, and eventually retire it. See crbug.com/991325.
             * <p><b>Experimental:</b> this part of CDP may change without notice.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder flatten(@Nullable Boolean value) {
                if (value == null) values.remove("flatten");
                else values.put("flatten", jsonValue(value));
                return this;
            }
            /**
             * Only targets matching filter will be attached.
             * <p><b>Experimental:</b> this part of CDP may change without notice.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder filter(@Nullable java.util.List<Target.FilterEntry> value) {
                if (value == null) values.remove("filter");
                else values.put("filter", jsonValue(value));
                return this;
            }
            public SetAutoAttachParams build() {
                if (!values.containsKey("autoAttach")) throw new IllegalStateException("Missing required CDP field: autoAttach");
                if (!values.containsKey("waitForDebuggerOnStart")) throw new IllegalStateException("Missing required CDP field: waitForDebuggerOnStart");
                return new SetAutoAttachParams(values);
            }
        }
    }
    /**
     * Controls whether to automatically attach to new targets which are considered to be directly related to this one (for example, iframes or workers). When turned on, attaches to all existing related targets as well. When turned off, automatically detaches from all currently attached targets. This also clears all targets added by {@code autoAttachRelated} from the list of targets to watch for creation of related targets. You might want to call this recursively for auto-attached targets to attach to all available targets.
     */
    public static final class SetAutoAttachResult extends CdpObject {
        private SetAutoAttachResult(Map<String, Object> values) { super(values); }
        @Nullable public static SetAutoAttachResult fromMap(@Nullable Map<String, Object> values) {
            return values == null ? null : new SetAutoAttachResult(values);
        }
        public static Builder builder() { return new Builder(); }
        public static final class Builder {
            private final Map<String, Object> values = new LinkedHashMap<>();
            public SetAutoAttachResult build() {
                return new SetAutoAttachResult(values);
            }
        }
    }
    /**
     * Adds the specified target to the list of targets that will be monitored for any related target creation (such as child frames, child workers and new versions of service worker) and reported through {@code attachedToTarget}. The specified target is also auto-attached. This cancels the effect of any previous {@code setAutoAttach} and is also cancelled by subsequent {@code setAutoAttach}. Only available at the Browser target.
     * <p><b>Experimental:</b> this part of CDP may change without notice.
     */
    public static final class AutoAttachRelatedParams extends CdpObject {
        private AutoAttachRelatedParams(Map<String, Object> values) { super(values); }
        @Nullable public static AutoAttachRelatedParams fromMap(@Nullable Map<String, Object> values) {
            return values == null ? null : new AutoAttachRelatedParams(values);
        }
        public static Builder builder() { return new Builder(); }
        /**
         * Returns the targetId field.
         * @return the protocol field value
         */
        @Nullable public String targetId() {
            return (String) value("targetId");
        }
        /**
         * Whether to pause new targets when attaching to them. Use {@code Runtime.runIfWaitingForDebugger} to run paused targets.
         * @return the protocol field value
         */
        @Nullable public Boolean waitForDebuggerOnStart() {
            return (Boolean) value("waitForDebuggerOnStart");
        }
        /**
         * Only targets matching filter will be attached.
         * <p><b>Experimental:</b> this part of CDP may change without notice.
         * @return the protocol field value
         */
        @Nullable public java.util.List<Target.FilterEntry> filter() {
            return list(value("filter"), element0 -> Target.FilterEntry.fromMap(objectMap(element0)));
        }
        public static final class Builder {
            private final Map<String, Object> values = new LinkedHashMap<>();
            /**
             * Sets the targetId field.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder targetId(@Nullable String value) {
                if (value == null) values.remove("targetId");
                else values.put("targetId", jsonValue(value));
                return this;
            }
            /**
             * Whether to pause new targets when attaching to them. Use {@code Runtime.runIfWaitingForDebugger} to run paused targets.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder waitForDebuggerOnStart(@Nullable Boolean value) {
                if (value == null) values.remove("waitForDebuggerOnStart");
                else values.put("waitForDebuggerOnStart", jsonValue(value));
                return this;
            }
            /**
             * Only targets matching filter will be attached.
             * <p><b>Experimental:</b> this part of CDP may change without notice.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder filter(@Nullable java.util.List<Target.FilterEntry> value) {
                if (value == null) values.remove("filter");
                else values.put("filter", jsonValue(value));
                return this;
            }
            public AutoAttachRelatedParams build() {
                if (!values.containsKey("targetId")) throw new IllegalStateException("Missing required CDP field: targetId");
                if (!values.containsKey("waitForDebuggerOnStart")) throw new IllegalStateException("Missing required CDP field: waitForDebuggerOnStart");
                return new AutoAttachRelatedParams(values);
            }
        }
    }
    /**
     * Adds the specified target to the list of targets that will be monitored for any related target creation (such as child frames, child workers and new versions of service worker) and reported through {@code attachedToTarget}. The specified target is also auto-attached. This cancels the effect of any previous {@code setAutoAttach} and is also cancelled by subsequent {@code setAutoAttach}. Only available at the Browser target.
     * <p><b>Experimental:</b> this part of CDP may change without notice.
     */
    public static final class AutoAttachRelatedResult extends CdpObject {
        private AutoAttachRelatedResult(Map<String, Object> values) { super(values); }
        @Nullable public static AutoAttachRelatedResult fromMap(@Nullable Map<String, Object> values) {
            return values == null ? null : new AutoAttachRelatedResult(values);
        }
        public static Builder builder() { return new Builder(); }
        public static final class Builder {
            private final Map<String, Object> values = new LinkedHashMap<>();
            public AutoAttachRelatedResult build() {
                return new AutoAttachRelatedResult(values);
            }
        }
    }
    /**
     * Controls whether to discover available targets and notify via {@code targetCreated/targetInfoChanged/targetDestroyed} events.
     */
    public static final class SetDiscoverTargetsParams extends CdpObject {
        private SetDiscoverTargetsParams(Map<String, Object> values) { super(values); }
        @Nullable public static SetDiscoverTargetsParams fromMap(@Nullable Map<String, Object> values) {
            return values == null ? null : new SetDiscoverTargetsParams(values);
        }
        public static Builder builder() { return new Builder(); }
        /**
         * Whether to discover available targets.
         * @return the protocol field value
         */
        @Nullable public Boolean discover() {
            return (Boolean) value("discover");
        }
        /**
         * Only targets matching filter will be attached. If {@code discover} is false, {@code filter} must be omitted or empty.
         * <p><b>Experimental:</b> this part of CDP may change without notice.
         * @return the protocol field value
         */
        @Nullable public java.util.List<Target.FilterEntry> filter() {
            return list(value("filter"), element0 -> Target.FilterEntry.fromMap(objectMap(element0)));
        }
        public static final class Builder {
            private final Map<String, Object> values = new LinkedHashMap<>();
            /**
             * Whether to discover available targets.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder discover(@Nullable Boolean value) {
                if (value == null) values.remove("discover");
                else values.put("discover", jsonValue(value));
                return this;
            }
            /**
             * Only targets matching filter will be attached. If {@code discover} is false, {@code filter} must be omitted or empty.
             * <p><b>Experimental:</b> this part of CDP may change without notice.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder filter(@Nullable java.util.List<Target.FilterEntry> value) {
                if (value == null) values.remove("filter");
                else values.put("filter", jsonValue(value));
                return this;
            }
            public SetDiscoverTargetsParams build() {
                if (!values.containsKey("discover")) throw new IllegalStateException("Missing required CDP field: discover");
                return new SetDiscoverTargetsParams(values);
            }
        }
    }
    /**
     * Controls whether to discover available targets and notify via {@code targetCreated/targetInfoChanged/targetDestroyed} events.
     */
    public static final class SetDiscoverTargetsResult extends CdpObject {
        private SetDiscoverTargetsResult(Map<String, Object> values) { super(values); }
        @Nullable public static SetDiscoverTargetsResult fromMap(@Nullable Map<String, Object> values) {
            return values == null ? null : new SetDiscoverTargetsResult(values);
        }
        public static Builder builder() { return new Builder(); }
        public static final class Builder {
            private final Map<String, Object> values = new LinkedHashMap<>();
            public SetDiscoverTargetsResult build() {
                return new SetDiscoverTargetsResult(values);
            }
        }
    }
    /**
     * Enables target discovery for the specified locations, when {@code setDiscoverTargets} was set to {@code true}.
     * <p><b>Experimental:</b> this part of CDP may change without notice.
     */
    public static final class SetRemoteLocationsParams extends CdpObject {
        private SetRemoteLocationsParams(Map<String, Object> values) { super(values); }
        @Nullable public static SetRemoteLocationsParams fromMap(@Nullable Map<String, Object> values) {
            return values == null ? null : new SetRemoteLocationsParams(values);
        }
        public static Builder builder() { return new Builder(); }
        /**
         * List of remote locations.
         * @return the protocol field value
         */
        @Nullable public java.util.List<Target.RemoteLocation> locations() {
            return list(value("locations"), element0 -> Target.RemoteLocation.fromMap(objectMap(element0)));
        }
        public static final class Builder {
            private final Map<String, Object> values = new LinkedHashMap<>();
            /**
             * List of remote locations.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder locations(@Nullable java.util.List<Target.RemoteLocation> value) {
                if (value == null) values.remove("locations");
                else values.put("locations", jsonValue(value));
                return this;
            }
            public SetRemoteLocationsParams build() {
                if (!values.containsKey("locations")) throw new IllegalStateException("Missing required CDP field: locations");
                return new SetRemoteLocationsParams(values);
            }
        }
    }
    /**
     * Enables target discovery for the specified locations, when {@code setDiscoverTargets} was set to {@code true}.
     * <p><b>Experimental:</b> this part of CDP may change without notice.
     */
    public static final class SetRemoteLocationsResult extends CdpObject {
        private SetRemoteLocationsResult(Map<String, Object> values) { super(values); }
        @Nullable public static SetRemoteLocationsResult fromMap(@Nullable Map<String, Object> values) {
            return values == null ? null : new SetRemoteLocationsResult(values);
        }
        public static Builder builder() { return new Builder(); }
        public static final class Builder {
            private final Map<String, Object> values = new LinkedHashMap<>();
            public SetRemoteLocationsResult build() {
                return new SetRemoteLocationsResult(values);
            }
        }
    }
    /**
     * Gets the targetId of the DevTools page target opened for the given target (if any).
     * <p><b>Experimental:</b> this part of CDP may change without notice.
     */
    public static final class GetDevToolsTargetParams extends CdpObject {
        private GetDevToolsTargetParams(Map<String, Object> values) { super(values); }
        @Nullable public static GetDevToolsTargetParams fromMap(@Nullable Map<String, Object> values) {
            return values == null ? null : new GetDevToolsTargetParams(values);
        }
        public static Builder builder() { return new Builder(); }
        /**
         * Page or tab target ID.
         * @return the protocol field value
         */
        @Nullable public String targetId() {
            return (String) value("targetId");
        }
        public static final class Builder {
            private final Map<String, Object> values = new LinkedHashMap<>();
            /**
             * Page or tab target ID.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder targetId(@Nullable String value) {
                if (value == null) values.remove("targetId");
                else values.put("targetId", jsonValue(value));
                return this;
            }
            public GetDevToolsTargetParams build() {
                if (!values.containsKey("targetId")) throw new IllegalStateException("Missing required CDP field: targetId");
                return new GetDevToolsTargetParams(values);
            }
        }
    }
    /**
     * Gets the targetId of the DevTools page target opened for the given target (if any).
     * <p><b>Experimental:</b> this part of CDP may change without notice.
     */
    public static final class GetDevToolsTargetResult extends CdpObject {
        private GetDevToolsTargetResult(Map<String, Object> values) { super(values); }
        @Nullable public static GetDevToolsTargetResult fromMap(@Nullable Map<String, Object> values) {
            return values == null ? null : new GetDevToolsTargetResult(values);
        }
        public static Builder builder() { return new Builder(); }
        /**
         * The targetId of DevTools page target if exists.
         * @return the protocol field value
         */
        @Nullable public String targetId() {
            return (String) value("targetId");
        }
        public static final class Builder {
            private final Map<String, Object> values = new LinkedHashMap<>();
            /**
             * The targetId of DevTools page target if exists.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder targetId(@Nullable String value) {
                if (value == null) values.remove("targetId");
                else values.put("targetId", jsonValue(value));
                return this;
            }
            public GetDevToolsTargetResult build() {
                return new GetDevToolsTargetResult(values);
            }
        }
    }
    /**
     * Opens a DevTools window for the target.
     * <p><b>Experimental:</b> this part of CDP may change without notice.
     */
    public static final class OpenDevToolsParams extends CdpObject {
        private OpenDevToolsParams(Map<String, Object> values) { super(values); }
        @Nullable public static OpenDevToolsParams fromMap(@Nullable Map<String, Object> values) {
            return values == null ? null : new OpenDevToolsParams(values);
        }
        public static Builder builder() { return new Builder(); }
        /**
         * This can be the page or tab target ID.
         * @return the protocol field value
         */
        @Nullable public String targetId() {
            return (String) value("targetId");
        }
        /**
         * The id of the panel we want DevTools to open initially. Currently supported panels are elements, console, network, sources, resources and performance.
         * @return the protocol field value
         */
        @Nullable public String panelId() {
            return (String) value("panelId");
        }
        public static final class Builder {
            private final Map<String, Object> values = new LinkedHashMap<>();
            /**
             * This can be the page or tab target ID.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder targetId(@Nullable String value) {
                if (value == null) values.remove("targetId");
                else values.put("targetId", jsonValue(value));
                return this;
            }
            /**
             * The id of the panel we want DevTools to open initially. Currently supported panels are elements, console, network, sources, resources and performance.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder panelId(@Nullable String value) {
                if (value == null) values.remove("panelId");
                else values.put("panelId", jsonValue(value));
                return this;
            }
            public OpenDevToolsParams build() {
                if (!values.containsKey("targetId")) throw new IllegalStateException("Missing required CDP field: targetId");
                return new OpenDevToolsParams(values);
            }
        }
    }
    /**
     * Opens a DevTools window for the target.
     * <p><b>Experimental:</b> this part of CDP may change without notice.
     */
    public static final class OpenDevToolsResult extends CdpObject {
        private OpenDevToolsResult(Map<String, Object> values) { super(values); }
        @Nullable public static OpenDevToolsResult fromMap(@Nullable Map<String, Object> values) {
            return values == null ? null : new OpenDevToolsResult(values);
        }
        public static Builder builder() { return new Builder(); }
        /**
         * The targetId of DevTools page target.
         * @return the protocol field value
         */
        @Nullable public String targetId() {
            return (String) value("targetId");
        }
        public static final class Builder {
            private final Map<String, Object> values = new LinkedHashMap<>();
            /**
             * The targetId of DevTools page target.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder targetId(@Nullable String value) {
                if (value == null) values.remove("targetId");
                else values.put("targetId", jsonValue(value));
                return this;
            }
            public OpenDevToolsResult build() {
                if (!values.containsKey("targetId")) throw new IllegalStateException("Missing required CDP field: targetId");
                return new OpenDevToolsResult(values);
            }
        }
    }
    /**
     * Issued when attached to target because of auto-attach or {@code attachToTarget} command.
     * <p><b>Experimental:</b> this part of CDP may change without notice.
     */
    public static final class AttachedToTargetEvent extends CdpObject {
        private AttachedToTargetEvent(Map<String, Object> values) { super(values); }
        @Nullable public static AttachedToTargetEvent fromMap(@Nullable Map<String, Object> values) {
            return values == null ? null : new AttachedToTargetEvent(values);
        }
        public static Builder builder() { return new Builder(); }
        /**
         * Identifier assigned to the session used to send/receive messages.
         * @return the protocol field value
         */
        @Nullable public String sessionId() {
            return (String) value("sessionId");
        }
        /**
         * Returns the targetInfo field.
         * @return the protocol field value
         */
        @Nullable public Target.TargetInfo targetInfo() {
            return Target.TargetInfo.fromMap(objectMap(value("targetInfo")));
        }
        /**
         * Returns the waitingForDebugger field.
         * @return the protocol field value
         */
        @Nullable public Boolean waitingForDebugger() {
            return (Boolean) value("waitingForDebugger");
        }
        public static final class Builder {
            private final Map<String, Object> values = new LinkedHashMap<>();
            /**
             * Identifier assigned to the session used to send/receive messages.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder sessionId(@Nullable String value) {
                if (value == null) values.remove("sessionId");
                else values.put("sessionId", jsonValue(value));
                return this;
            }
            /**
             * Sets the targetInfo field.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder targetInfo(@Nullable Target.TargetInfo value) {
                if (value == null) values.remove("targetInfo");
                else values.put("targetInfo", jsonValue(value));
                return this;
            }
            /**
             * Sets the waitingForDebugger field.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder waitingForDebugger(@Nullable Boolean value) {
                if (value == null) values.remove("waitingForDebugger");
                else values.put("waitingForDebugger", jsonValue(value));
                return this;
            }
            public AttachedToTargetEvent build() {
                if (!values.containsKey("sessionId")) throw new IllegalStateException("Missing required CDP field: sessionId");
                if (!values.containsKey("targetInfo")) throw new IllegalStateException("Missing required CDP field: targetInfo");
                if (!values.containsKey("waitingForDebugger")) throw new IllegalStateException("Missing required CDP field: waitingForDebugger");
                return new AttachedToTargetEvent(values);
            }
        }
    }
    /**
     * Issued when detached from target for any reason (including {@code detachFromTarget} command). Can be issued multiple times per target if multiple sessions have been attached to it.
     * <p><b>Experimental:</b> this part of CDP may change without notice.
     */
    public static final class DetachedFromTargetEvent extends CdpObject {
        private DetachedFromTargetEvent(Map<String, Object> values) { super(values); }
        @Nullable public static DetachedFromTargetEvent fromMap(@Nullable Map<String, Object> values) {
            return values == null ? null : new DetachedFromTargetEvent(values);
        }
        public static Builder builder() { return new Builder(); }
        /**
         * Detached session identifier.
         * @return the protocol field value
         */
        @Nullable public String sessionId() {
            return (String) value("sessionId");
        }
        /**
         * Deprecated.
         * @return the protocol field value
         * @deprecated Deprecated by the Chromium DevTools Protocol.
         */
        @Deprecated
        @Nullable public String targetId() {
            return (String) value("targetId");
        }
        public static final class Builder {
            private final Map<String, Object> values = new LinkedHashMap<>();
            /**
             * Detached session identifier.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder sessionId(@Nullable String value) {
                if (value == null) values.remove("sessionId");
                else values.put("sessionId", jsonValue(value));
                return this;
            }
            /**
             * Deprecated.
             * @param value field value; null removes an optional value
             * @return this builder
             * @deprecated Deprecated by the Chromium DevTools Protocol.
             */
            @Deprecated
            public Builder targetId(@Nullable String value) {
                if (value == null) values.remove("targetId");
                else values.put("targetId", jsonValue(value));
                return this;
            }
            public DetachedFromTargetEvent build() {
                if (!values.containsKey("sessionId")) throw new IllegalStateException("Missing required CDP field: sessionId");
                return new DetachedFromTargetEvent(values);
            }
        }
    }
    /**
     * Notifies about a new protocol message received from the session (as reported in {@code attachedToTarget} event).
     */
    public static final class ReceivedMessageFromTargetEvent extends CdpObject {
        private ReceivedMessageFromTargetEvent(Map<String, Object> values) { super(values); }
        @Nullable public static ReceivedMessageFromTargetEvent fromMap(@Nullable Map<String, Object> values) {
            return values == null ? null : new ReceivedMessageFromTargetEvent(values);
        }
        public static Builder builder() { return new Builder(); }
        /**
         * Identifier of a session which sends a message.
         * @return the protocol field value
         */
        @Nullable public String sessionId() {
            return (String) value("sessionId");
        }
        /**
         * Returns the message field.
         * @return the protocol field value
         */
        @Nullable public String message() {
            return (String) value("message");
        }
        /**
         * Deprecated.
         * @return the protocol field value
         * @deprecated Deprecated by the Chromium DevTools Protocol.
         */
        @Deprecated
        @Nullable public String targetId() {
            return (String) value("targetId");
        }
        public static final class Builder {
            private final Map<String, Object> values = new LinkedHashMap<>();
            /**
             * Identifier of a session which sends a message.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder sessionId(@Nullable String value) {
                if (value == null) values.remove("sessionId");
                else values.put("sessionId", jsonValue(value));
                return this;
            }
            /**
             * Sets the message field.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder message(@Nullable String value) {
                if (value == null) values.remove("message");
                else values.put("message", jsonValue(value));
                return this;
            }
            /**
             * Deprecated.
             * @param value field value; null removes an optional value
             * @return this builder
             * @deprecated Deprecated by the Chromium DevTools Protocol.
             */
            @Deprecated
            public Builder targetId(@Nullable String value) {
                if (value == null) values.remove("targetId");
                else values.put("targetId", jsonValue(value));
                return this;
            }
            public ReceivedMessageFromTargetEvent build() {
                if (!values.containsKey("sessionId")) throw new IllegalStateException("Missing required CDP field: sessionId");
                if (!values.containsKey("message")) throw new IllegalStateException("Missing required CDP field: message");
                return new ReceivedMessageFromTargetEvent(values);
            }
        }
    }
    /**
     * Issued when a possible inspection target is created.
     */
    public static final class TargetCreatedEvent extends CdpObject {
        private TargetCreatedEvent(Map<String, Object> values) { super(values); }
        @Nullable public static TargetCreatedEvent fromMap(@Nullable Map<String, Object> values) {
            return values == null ? null : new TargetCreatedEvent(values);
        }
        public static Builder builder() { return new Builder(); }
        /**
         * Returns the targetInfo field.
         * @return the protocol field value
         */
        @Nullable public Target.TargetInfo targetInfo() {
            return Target.TargetInfo.fromMap(objectMap(value("targetInfo")));
        }
        public static final class Builder {
            private final Map<String, Object> values = new LinkedHashMap<>();
            /**
             * Sets the targetInfo field.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder targetInfo(@Nullable Target.TargetInfo value) {
                if (value == null) values.remove("targetInfo");
                else values.put("targetInfo", jsonValue(value));
                return this;
            }
            public TargetCreatedEvent build() {
                if (!values.containsKey("targetInfo")) throw new IllegalStateException("Missing required CDP field: targetInfo");
                return new TargetCreatedEvent(values);
            }
        }
    }
    /**
     * Issued when a target is destroyed.
     */
    public static final class TargetDestroyedEvent extends CdpObject {
        private TargetDestroyedEvent(Map<String, Object> values) { super(values); }
        @Nullable public static TargetDestroyedEvent fromMap(@Nullable Map<String, Object> values) {
            return values == null ? null : new TargetDestroyedEvent(values);
        }
        public static Builder builder() { return new Builder(); }
        /**
         * Returns the targetId field.
         * @return the protocol field value
         */
        @Nullable public String targetId() {
            return (String) value("targetId");
        }
        public static final class Builder {
            private final Map<String, Object> values = new LinkedHashMap<>();
            /**
             * Sets the targetId field.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder targetId(@Nullable String value) {
                if (value == null) values.remove("targetId");
                else values.put("targetId", jsonValue(value));
                return this;
            }
            public TargetDestroyedEvent build() {
                if (!values.containsKey("targetId")) throw new IllegalStateException("Missing required CDP field: targetId");
                return new TargetDestroyedEvent(values);
            }
        }
    }
    /**
     * Issued when a target has crashed.
     */
    public static final class TargetCrashedEvent extends CdpObject {
        private TargetCrashedEvent(Map<String, Object> values) { super(values); }
        @Nullable public static TargetCrashedEvent fromMap(@Nullable Map<String, Object> values) {
            return values == null ? null : new TargetCrashedEvent(values);
        }
        public static Builder builder() { return new Builder(); }
        /**
         * Returns the targetId field.
         * @return the protocol field value
         */
        @Nullable public String targetId() {
            return (String) value("targetId");
        }
        /**
         * Termination status type.
         * @return the protocol field value
         */
        @Nullable public String status() {
            return (String) value("status");
        }
        /**
         * Termination error code.
         * @return the protocol field value
         */
        @Nullable public Long errorCode() {
            return numberAsLong(value("errorCode"));
        }
        public static final class Builder {
            private final Map<String, Object> values = new LinkedHashMap<>();
            /**
             * Sets the targetId field.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder targetId(@Nullable String value) {
                if (value == null) values.remove("targetId");
                else values.put("targetId", jsonValue(value));
                return this;
            }
            /**
             * Termination status type.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder status(@Nullable String value) {
                if (value == null) values.remove("status");
                else values.put("status", jsonValue(value));
                return this;
            }
            /**
             * Termination error code.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder errorCode(@Nullable Long value) {
                if (value == null) values.remove("errorCode");
                else values.put("errorCode", jsonValue(value));
                return this;
            }
            public TargetCrashedEvent build() {
                if (!values.containsKey("targetId")) throw new IllegalStateException("Missing required CDP field: targetId");
                if (!values.containsKey("status")) throw new IllegalStateException("Missing required CDP field: status");
                if (!values.containsKey("errorCode")) throw new IllegalStateException("Missing required CDP field: errorCode");
                return new TargetCrashedEvent(values);
            }
        }
    }
    /**
     * Issued when some information about a target has changed. This only happens between {@code targetCreated} and {@code targetDestroyed}.
     */
    public static final class TargetInfoChangedEvent extends CdpObject {
        private TargetInfoChangedEvent(Map<String, Object> values) { super(values); }
        @Nullable public static TargetInfoChangedEvent fromMap(@Nullable Map<String, Object> values) {
            return values == null ? null : new TargetInfoChangedEvent(values);
        }
        public static Builder builder() { return new Builder(); }
        /**
         * Returns the targetInfo field.
         * @return the protocol field value
         */
        @Nullable public Target.TargetInfo targetInfo() {
            return Target.TargetInfo.fromMap(objectMap(value("targetInfo")));
        }
        public static final class Builder {
            private final Map<String, Object> values = new LinkedHashMap<>();
            /**
             * Sets the targetInfo field.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder targetInfo(@Nullable Target.TargetInfo value) {
                if (value == null) values.remove("targetInfo");
                else values.put("targetInfo", jsonValue(value));
                return this;
            }
            public TargetInfoChangedEvent build() {
                if (!values.containsKey("targetInfo")) throw new IllegalStateException("Missing required CDP field: targetInfo");
                return new TargetInfoChangedEvent(values);
            }
        }
    }
    public static final class Client {
        private final CdpClient client;
        public Client(CdpClient client) { this.client = client; }
        /**
         * Activates (focuses) the target.
         * @param params command parameters
         * @return a stage completing with the command result
         */
        public CompletionStage<ActivateTargetResult> activateTarget(ActivateTargetParams params) {
            return client.call("Target.activateTarget", params, ActivateTargetResult::fromMap);
        }
        /**
         * Attaches to the target with given id.
         * @param params command parameters
         * @return a stage completing with the command result
         */
        public CompletionStage<AttachToTargetResult> attachToTarget(AttachToTargetParams params) {
            return client.call("Target.attachToTarget", params, AttachToTargetResult::fromMap);
        }
        /**
         * Attaches to the browser target, only uses flat sessionId mode.
         * <p><b>Experimental:</b> this part of CDP may change without notice.
         * @return a stage completing with the command result
         */
        public CompletionStage<AttachToBrowserTargetResult> attachToBrowserTarget() {
            return client.call("Target.attachToBrowserTarget", null, AttachToBrowserTargetResult::fromMap);
        }
        /**
         * Closes the target. If the target is a page that gets closed too.
         * @param params command parameters
         * @return a stage completing with the command result
         */
        public CompletionStage<CloseTargetResult> closeTarget(CloseTargetParams params) {
            return client.call("Target.closeTarget", params, CloseTargetResult::fromMap);
        }
        /**
         * Inject object to the target&#x27;s main frame that provides a communication channel with browser target.
         * <p>Injected object will be available as {@code window[bindingName]}.
         * <p>The object has the following API: - {@code binding.send(json)} - a method to send messages over the remote debugging protocol - {@code binding.onmessage = json =&gt; handleMessage(json)} - a callback that will be called for the protocol notifications and command responses.
         * <p><b>Experimental:</b> this part of CDP may change without notice.
         * @param params command parameters
         * @return a stage completing with the command result
         */
        public CompletionStage<ExposeDevToolsProtocolResult> exposeDevToolsProtocol(ExposeDevToolsProtocolParams params) {
            return client.call("Target.exposeDevToolsProtocol", params, ExposeDevToolsProtocolResult::fromMap);
        }
        /**
         * Creates a new empty BrowserContext. Similar to an incognito profile but you can have more than one.
         * @param params command parameters
         * @return a stage completing with the command result
         */
        public CompletionStage<CreateBrowserContextResult> createBrowserContext(CreateBrowserContextParams params) {
            return client.call("Target.createBrowserContext", params, CreateBrowserContextResult::fromMap);
        }
        /**
         * Returns all browser contexts created with {@code Target.createBrowserContext} method.
         * @return a stage completing with the command result
         */
        public CompletionStage<GetBrowserContextsResult> getBrowserContexts() {
            return client.call("Target.getBrowserContexts", null, GetBrowserContextsResult::fromMap);
        }
        /**
         * Creates a new page.
         * @param params command parameters
         * @return a stage completing with the command result
         */
        public CompletionStage<CreateTargetResult> createTarget(CreateTargetParams params) {
            return client.call("Target.createTarget", params, CreateTargetResult::fromMap);
        }
        /**
         * Detaches session with given id.
         * @param params command parameters
         * @return a stage completing with the command result
         */
        public CompletionStage<DetachFromTargetResult> detachFromTarget(DetachFromTargetParams params) {
            return client.call("Target.detachFromTarget", params, DetachFromTargetResult::fromMap);
        }
        /**
         * Deletes a BrowserContext. All the belonging pages will be closed without calling their beforeunload hooks.
         * @param params command parameters
         * @return a stage completing with the command result
         */
        public CompletionStage<DisposeBrowserContextResult> disposeBrowserContext(DisposeBrowserContextParams params) {
            return client.call("Target.disposeBrowserContext", params, DisposeBrowserContextResult::fromMap);
        }
        /**
         * Returns information about a target.
         * <p><b>Experimental:</b> this part of CDP may change without notice.
         * @param params command parameters
         * @return a stage completing with the command result
         */
        public CompletionStage<GetTargetInfoResult> getTargetInfo(GetTargetInfoParams params) {
            return client.call("Target.getTargetInfo", params, GetTargetInfoResult::fromMap);
        }
        /**
         * Retrieves a list of available targets.
         * @param params command parameters
         * @return a stage completing with the command result
         */
        public CompletionStage<GetTargetsResult> getTargets(GetTargetsParams params) {
            return client.call("Target.getTargets", params, GetTargetsResult::fromMap);
        }
        /**
         * Sends protocol message over session with given id. Consider using flat mode instead; see commands attachToTarget, setAutoAttach, and crbug.com/991325.
         * @param params command parameters
         * @return a stage completing with the command result
         * @deprecated Deprecated by the Chromium DevTools Protocol.
         */
        @Deprecated
        public CompletionStage<SendMessageToTargetResult> sendMessageToTarget(SendMessageToTargetParams params) {
            return client.call("Target.sendMessageToTarget", params, SendMessageToTargetResult::fromMap);
        }
        /**
         * Controls whether to automatically attach to new targets which are considered to be directly related to this one (for example, iframes or workers). When turned on, attaches to all existing related targets as well. When turned off, automatically detaches from all currently attached targets. This also clears all targets added by {@code autoAttachRelated} from the list of targets to watch for creation of related targets. You might want to call this recursively for auto-attached targets to attach to all available targets.
         * @param params command parameters
         * @return a stage completing with the command result
         */
        public CompletionStage<SetAutoAttachResult> setAutoAttach(SetAutoAttachParams params) {
            return client.call("Target.setAutoAttach", params, SetAutoAttachResult::fromMap);
        }
        /**
         * Adds the specified target to the list of targets that will be monitored for any related target creation (such as child frames, child workers and new versions of service worker) and reported through {@code attachedToTarget}. The specified target is also auto-attached. This cancels the effect of any previous {@code setAutoAttach} and is also cancelled by subsequent {@code setAutoAttach}. Only available at the Browser target.
         * <p><b>Experimental:</b> this part of CDP may change without notice.
         * @param params command parameters
         * @return a stage completing with the command result
         */
        public CompletionStage<AutoAttachRelatedResult> autoAttachRelated(AutoAttachRelatedParams params) {
            return client.call("Target.autoAttachRelated", params, AutoAttachRelatedResult::fromMap);
        }
        /**
         * Controls whether to discover available targets and notify via {@code targetCreated/targetInfoChanged/targetDestroyed} events.
         * @param params command parameters
         * @return a stage completing with the command result
         */
        public CompletionStage<SetDiscoverTargetsResult> setDiscoverTargets(SetDiscoverTargetsParams params) {
            return client.call("Target.setDiscoverTargets", params, SetDiscoverTargetsResult::fromMap);
        }
        /**
         * Enables target discovery for the specified locations, when {@code setDiscoverTargets} was set to {@code true}.
         * <p><b>Experimental:</b> this part of CDP may change without notice.
         * @param params command parameters
         * @return a stage completing with the command result
         */
        public CompletionStage<SetRemoteLocationsResult> setRemoteLocations(SetRemoteLocationsParams params) {
            return client.call("Target.setRemoteLocations", params, SetRemoteLocationsResult::fromMap);
        }
        /**
         * Gets the targetId of the DevTools page target opened for the given target (if any).
         * <p><b>Experimental:</b> this part of CDP may change without notice.
         * @param params command parameters
         * @return a stage completing with the command result
         */
        public CompletionStage<GetDevToolsTargetResult> getDevToolsTarget(GetDevToolsTargetParams params) {
            return client.call("Target.getDevToolsTarget", params, GetDevToolsTargetResult::fromMap);
        }
        /**
         * Opens a DevTools window for the target.
         * <p><b>Experimental:</b> this part of CDP may change without notice.
         * @param params command parameters
         * @return a stage completing with the command result
         */
        public CompletionStage<OpenDevToolsResult> openDevTools(OpenDevToolsParams params) {
            return client.call("Target.openDevTools", params, OpenDevToolsResult::fromMap);
        }
        /**
         * Issued when attached to target because of auto-attach or {@code attachToTarget} command.
         * <p><b>Experimental:</b> this part of CDP may change without notice.
         * @param handler event callback
         * @return a removable subscription
         */
        public CdpSubscription onAttachedToTarget(Consumer<AttachedToTargetEvent> handler) {
            return client.on("Target.attachedToTarget", AttachedToTargetEvent::fromMap, handler);
        }
        /**
         * Issued when detached from target for any reason (including {@code detachFromTarget} command). Can be issued multiple times per target if multiple sessions have been attached to it.
         * <p><b>Experimental:</b> this part of CDP may change without notice.
         * @param handler event callback
         * @return a removable subscription
         */
        public CdpSubscription onDetachedFromTarget(Consumer<DetachedFromTargetEvent> handler) {
            return client.on("Target.detachedFromTarget", DetachedFromTargetEvent::fromMap, handler);
        }
        /**
         * Notifies about a new protocol message received from the session (as reported in {@code attachedToTarget} event).
         * @param handler event callback
         * @return a removable subscription
         */
        public CdpSubscription onReceivedMessageFromTarget(Consumer<ReceivedMessageFromTargetEvent> handler) {
            return client.on("Target.receivedMessageFromTarget", ReceivedMessageFromTargetEvent::fromMap, handler);
        }
        /**
         * Issued when a possible inspection target is created.
         * @param handler event callback
         * @return a removable subscription
         */
        public CdpSubscription onTargetCreated(Consumer<TargetCreatedEvent> handler) {
            return client.on("Target.targetCreated", TargetCreatedEvent::fromMap, handler);
        }
        /**
         * Issued when a target is destroyed.
         * @param handler event callback
         * @return a removable subscription
         */
        public CdpSubscription onTargetDestroyed(Consumer<TargetDestroyedEvent> handler) {
            return client.on("Target.targetDestroyed", TargetDestroyedEvent::fromMap, handler);
        }
        /**
         * Issued when a target has crashed.
         * @param handler event callback
         * @return a removable subscription
         */
        public CdpSubscription onTargetCrashed(Consumer<TargetCrashedEvent> handler) {
            return client.on("Target.targetCrashed", TargetCrashedEvent::fromMap, handler);
        }
        /**
         * Issued when some information about a target has changed. This only happens between {@code targetCreated} and {@code targetDestroyed}.
         * @param handler event callback
         * @return a removable subscription
         */
        public CdpSubscription onTargetInfoChanged(Consumer<TargetInfoChangedEvent> handler) {
            return client.on("Target.targetInfoChanged", TargetInfoChangedEvent::fromMap, handler);
        }
    }
}
