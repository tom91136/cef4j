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
 * Supports additional targets discovery and allows to attach to them.
 * @see <a href="https://chromium.googlesource.com/chromium/src/+/refs/tags/150.0.7871.213/third_party/blink/public/devtools_protocol/domains/Target.pdl">Pinned protocol source</a>
 */
@Generated("mvn generate-sources -pl cef4j-platform -Dcef.version=150.0.18+gdb11278+chromium-150.0.7871.213")
@SuppressWarnings({"EscapedEntity", "InvalidParam", "JavaLangClash", "MissingSummary", "Unchecked", "UnusedMethod"})
public final class Target {
    private Target() {}
    /**
     * Tagged String wire value for TargetID.
     */
    public static final class TargetID implements CdpValue<String> {
        public final String value;
        public TargetID(@Nonnull String value) { this.value = java.util.Objects.requireNonNull(value); }
        @Nonnull public String value() { return value; }
        @Override public boolean equals(Object other) {
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
        public SessionID(@Nonnull String value) { this.value = java.util.Objects.requireNonNull(value); }
        @Nonnull public String value() { return value; }
        @Override public boolean equals(Object other) {
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
            return new Target.TargetID((String) require("targetId"));
        }
        /**
         * List of types: https://source.chromium.org/chromium/chromium/src/+/main:content/browser/devtools/devtools_agent_host_impl.cc?ss=chromium&amp;q=f:devtools%20-f:out%20%22::kTypeTab%5B%5D%22
         * @return the protocol field value
         */
        public String type() {
            return (String) require("type");
        }
        /**
         * Returns the title field.
         * @return the protocol field value
         */
        public String title() {
            return (String) require("title");
        }
        /**
         * Returns the url field.
         * @return the protocol field value
         */
        public String url() {
            return (String) require("url");
        }
        /**
         * Whether the target has an attached client.
         * @return the protocol field value
         */
        public boolean attached() {
            return (Boolean) require("attached");
        }
        /**
         * Id of the parent target, if any. For example, &quot;iframe&quot; target may have a &quot;page&quot; parent.
         * @return the protocol field value, empty when absent
         */
        public Optional<Target.TargetID> parentId() {
            return Optional.ofNullable(raw("parentId") == null ? null : new Target.TargetID((String) raw("parentId")));
        }
        /**
         * Opener target Id
         * @return the protocol field value, empty when absent
         */
        public Optional<Target.TargetID> openerId() {
            return Optional.ofNullable(raw("openerId") == null ? null : new Target.TargetID((String) raw("openerId")));
        }
        /**
         * Whether the target has access to the originating window.
         * <p><b>Experimental:</b> this part of CDP may change without notice.
         * @return the protocol field value
         */
        public boolean canAccessOpener() {
            return (Boolean) require("canAccessOpener");
        }
        /**
         * Frame id of originating window (is only set if target has an opener).
         * <p><b>Experimental:</b> this part of CDP may change without notice.
         * @return the protocol field value, empty when absent
         */
        public Optional<Page.FrameId> openerFrameId() {
            return Optional.ofNullable(raw("openerFrameId") == null ? null : new Page.FrameId((String) raw("openerFrameId")));
        }
        /**
         * Id of the parent frame, present for &quot;iframe&quot; and &quot;worker&quot; targets. For nested workers, this is the &quot;ancestor&quot; frame that created the first worker in the nested chain.
         * <p><b>Experimental:</b> this part of CDP may change without notice.
         * @return the protocol field value, empty when absent
         */
        public Optional<Page.FrameId> parentFrameId() {
            return Optional.ofNullable(raw("parentFrameId") == null ? null : new Page.FrameId((String) raw("parentFrameId")));
        }
        /**
         * Returns the browserContextId field.
         * <p><b>Experimental:</b> this part of CDP may change without notice.
         * @return the protocol field value, empty when absent
         */
        public Optional<Browser.BrowserContextID> browserContextId() {
            return Optional.ofNullable(raw("browserContextId") == null ? null : new Browser.BrowserContextID((String) raw("browserContextId")));
        }
        /**
         * Provides additional details for specific target types. For example, for the type of &quot;page&quot;, this may be set to &quot;prerender&quot;.
         * <p><b>Experimental:</b> this part of CDP may change without notice.
         * @return the protocol field value, empty when absent
         */
        public Optional<String> subtype() {
            return Optional.ofNullable((String) raw("subtype"));
        }
        /**
         * Embedder-specific target metadata. This is only set for targets of type &quot;tab&quot;.
         * <p><b>Experimental:</b> this part of CDP may change without notice.
         * @return the protocol field value, empty when absent
         */
        public Optional<java.util.Map<String, Object>> embedderData() {
            return Optional.ofNullable(objectMap(raw("embedderData")));
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
         * @param parentId field value; empty omits the value
         * @return this model
         */
        public TargetInfo parentId(Optional<Target.TargetID> parentId) {
            set("parentId", parentId.orElse(null));
            return this;
        }
        /**
         * Id of the parent target, if any. For example, &quot;iframe&quot; target may have a &quot;page&quot; parent.
         * @param parentId field value; null removes the value
         * @return this model
         */
        public TargetInfo parentId(Target.TargetID parentId) {
            set("parentId", parentId);
            return this;
        }
        /**
         * Opener target Id
         * @param openerId field value; empty omits the value
         * @return this model
         */
        public TargetInfo openerId(Optional<Target.TargetID> openerId) {
            set("openerId", openerId.orElse(null));
            return this;
        }
        /**
         * Opener target Id
         * @param openerId field value; null removes the value
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
         * @param openerFrameId field value; empty omits the value
         * @return this model
         */
        public TargetInfo openerFrameId(Optional<Page.FrameId> openerFrameId) {
            set("openerFrameId", openerFrameId.orElse(null));
            return this;
        }
        /**
         * Frame id of originating window (is only set if target has an opener).
         * <p><b>Experimental:</b> this part of CDP may change without notice.
         * @param openerFrameId field value; null removes the value
         * @return this model
         */
        public TargetInfo openerFrameId(Page.FrameId openerFrameId) {
            set("openerFrameId", openerFrameId);
            return this;
        }
        /**
         * Id of the parent frame, present for &quot;iframe&quot; and &quot;worker&quot; targets. For nested workers, this is the &quot;ancestor&quot; frame that created the first worker in the nested chain.
         * <p><b>Experimental:</b> this part of CDP may change without notice.
         * @param parentFrameId field value; empty omits the value
         * @return this model
         */
        public TargetInfo parentFrameId(Optional<Page.FrameId> parentFrameId) {
            set("parentFrameId", parentFrameId.orElse(null));
            return this;
        }
        /**
         * Id of the parent frame, present for &quot;iframe&quot; and &quot;worker&quot; targets. For nested workers, this is the &quot;ancestor&quot; frame that created the first worker in the nested chain.
         * <p><b>Experimental:</b> this part of CDP may change without notice.
         * @param parentFrameId field value; null removes the value
         * @return this model
         */
        public TargetInfo parentFrameId(Page.FrameId parentFrameId) {
            set("parentFrameId", parentFrameId);
            return this;
        }
        /**
         * Sets the browserContextId field.
         * <p><b>Experimental:</b> this part of CDP may change without notice.
         * @param browserContextId field value; empty omits the value
         * @return this model
         */
        public TargetInfo browserContextId(Optional<Browser.BrowserContextID> browserContextId) {
            set("browserContextId", browserContextId.orElse(null));
            return this;
        }
        /**
         * Sets the browserContextId field.
         * <p><b>Experimental:</b> this part of CDP may change without notice.
         * @param browserContextId field value; null removes the value
         * @return this model
         */
        public TargetInfo browserContextId(Browser.BrowserContextID browserContextId) {
            set("browserContextId", browserContextId);
            return this;
        }
        /**
         * Provides additional details for specific target types. For example, for the type of &quot;page&quot;, this may be set to &quot;prerender&quot;.
         * <p><b>Experimental:</b> this part of CDP may change without notice.
         * @param subtype field value; empty omits the value
         * @return this model
         */
        public TargetInfo subtype(Optional<String> subtype) {
            set("subtype", subtype.orElse(null));
            return this;
        }
        /**
         * Provides additional details for specific target types. For example, for the type of &quot;page&quot;, this may be set to &quot;prerender&quot;.
         * <p><b>Experimental:</b> this part of CDP may change without notice.
         * @param subtype field value; null removes the value
         * @return this model
         */
        public TargetInfo subtype(String subtype) {
            set("subtype", subtype);
            return this;
        }
        /**
         * Embedder-specific target metadata. This is only set for targets of type &quot;tab&quot;.
         * <p><b>Experimental:</b> this part of CDP may change without notice.
         * @param embedderData field value; empty omits the value
         * @return this model
         */
        public TargetInfo embedderData(Optional<java.util.Map<String, Object>> embedderData) {
            set("embedderData", embedderData.orElse(null));
            return this;
        }
        /**
         * Embedder-specific target metadata. This is only set for targets of type &quot;tab&quot;.
         * <p><b>Experimental:</b> this part of CDP may change without notice.
         * @param embedderData field value; null removes the value
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
            return Optional.ofNullable((Boolean) raw("exclude"));
        }
        /**
         * If not present, matches any type.
         * @return the protocol field value, empty when absent
         */
        public Optional<String> type() {
            return Optional.ofNullable((String) raw("type"));
        }
        /**
         * If set, causes exclusion of matching targets from the list.
         * @param exclude field value; empty omits the value
         * @return this model
         */
        public FilterEntry exclude(Optional<Boolean> exclude) {
            set("exclude", exclude.orElse(null));
            return this;
        }
        /**
         * If set, causes exclusion of matching targets from the list.
         * @param exclude field value; null removes the value
         * @return this model
         */
        public FilterEntry exclude(Boolean exclude) {
            set("exclude", exclude);
            return this;
        }
        /**
         * If not present, matches any type.
         * @param type field value; empty omits the value
         * @return this model
         */
        public FilterEntry type(Optional<String> type) {
            set("type", type.orElse(null));
            return this;
        }
        /**
         * If not present, matches any type.
         * @param type field value; null removes the value
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
            return (String) require("host");
        }
        /**
         * Returns the port field.
         * @return the protocol field value
         */
        public long port() {
            return ((Number) require("port")).longValue();
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
        @Nonnull public String value() { return value; }
        public static WindowState of(@Nonnull String value) {
            for (WindowState constant : values()) {
                if (constant.value.equals(value)) return constant;
            }
            throw new IllegalArgumentException("Unknown WindowState value: " + value);
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
            return CdpObject.requireList(require("browserContextIds"), element0 -> new Browser.BrowserContextID((String) element0));
        }
        /**
         * The id of the default browser context if available.
         * <p><b>Experimental:</b> this part of CDP may change without notice.
         * @return the protocol field value, empty when absent
         */
        public Optional<Browser.BrowserContextID> defaultBrowserContextId() {
            return Optional.ofNullable(raw("defaultBrowserContextId") == null ? null : new Browser.BrowserContextID((String) raw("defaultBrowserContextId")));
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
         * @param defaultBrowserContextId field value; empty omits the value
         * @return this model
         */
        public GetBrowserContextsResult defaultBrowserContextId(Optional<Browser.BrowserContextID> defaultBrowserContextId) {
            set("defaultBrowserContextId", defaultBrowserContextId.orElse(null));
            return this;
        }
        /**
         * The id of the default browser context if available.
         * <p><b>Experimental:</b> this part of CDP may change without notice.
         * @param defaultBrowserContextId field value; null removes the value
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
            return new Target.SessionID((String) require("sessionId"));
        }
        /**
         * Returns the targetInfo field.
         * @return the protocol field value
         */
        public Target.TargetInfo targetInfo() {
            return java.util.Objects.requireNonNull(Target.TargetInfo.fromMap(java.util.Objects.requireNonNull(CdpObject.objectMap(require("targetInfo")))));
        }
        /**
         * Returns the waitingForDebugger field.
         * @return the protocol field value
         */
        public boolean waitingForDebugger() {
            return (Boolean) require("waitingForDebugger");
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
            return new Target.SessionID((String) require("sessionId"));
        }
        /**
         * Deprecated.
         * @return the protocol field value, empty when absent
         * @deprecated Deprecated by the Chromium DevTools Protocol.
         */
        @Deprecated
        public Optional<Target.TargetID> targetId() {
            return Optional.ofNullable(raw("targetId") == null ? null : new Target.TargetID((String) raw("targetId")));
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
         * @param targetId field value; empty omits the value
         * @return this model
         * @deprecated Deprecated by the Chromium DevTools Protocol.
         */
        @Deprecated
        public DetachedFromTargetEvent targetId(Optional<Target.TargetID> targetId) {
            set("targetId", targetId.orElse(null));
            return this;
        }
        /**
         * Deprecated.
         * @param targetId field value; null removes the value
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
            return new Target.SessionID((String) require("sessionId"));
        }
        /**
         * Returns the message field.
         * @return the protocol field value
         */
        public String message() {
            return (String) require("message");
        }
        /**
         * Deprecated.
         * @return the protocol field value, empty when absent
         * @deprecated Deprecated by the Chromium DevTools Protocol.
         */
        @Deprecated
        public Optional<Target.TargetID> targetId() {
            return Optional.ofNullable(raw("targetId") == null ? null : new Target.TargetID((String) raw("targetId")));
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
         * @param targetId field value; empty omits the value
         * @return this model
         * @deprecated Deprecated by the Chromium DevTools Protocol.
         */
        @Deprecated
        public ReceivedMessageFromTargetEvent targetId(Optional<Target.TargetID> targetId) {
            set("targetId", targetId.orElse(null));
            return this;
        }
        /**
         * Deprecated.
         * @param targetId field value; null removes the value
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
            return java.util.Objects.requireNonNull(Target.TargetInfo.fromMap(java.util.Objects.requireNonNull(CdpObject.objectMap(require("targetInfo")))));
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
            return new Target.TargetID((String) require("targetId"));
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
            return new Target.TargetID((String) require("targetId"));
        }
        /**
         * Termination status type.
         * @return the protocol field value
         */
        public String status() {
            return (String) require("status");
        }
        /**
         * Termination error code.
         * @return the protocol field value
         */
        public long errorCode() {
            return ((Number) require("errorCode")).longValue();
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
            return java.util.Objects.requireNonNull(Target.TargetInfo.fromMap(java.util.Objects.requireNonNull(CdpObject.objectMap(require("targetInfo")))));
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
    public static final class Client {
        private final CdpClient client;
        public Client(CdpClient client) { this.client = client; }
        /**
         * Activates (focuses) the target.
         * @param targetId protocol value
         * @return a stage completing when the command completes
         */
        public CompletionStage<Void> activateTarget(Target.TargetID targetId) {
            Map<String, Object> params = new LinkedHashMap<>();
            params.put("targetId", CdpObject.json(targetId));
            return client.call("Target.activateTarget", params, result_ -> null);
        }
        /**
         * Attaches to the target with given id.
         * @param targetId protocol value
         * @param flatten protocol value
         * @return a stage completing with the command result
         */
        public CompletionStage<Target.SessionID> attachToTarget(Target.TargetID targetId, Optional<Boolean> flatten) {
            Map<String, Object> params = new LinkedHashMap<>();
            params.put("targetId", CdpObject.json(targetId));
            flatten.ifPresent(value_ -> params.put("flatten", value_));
            return client.call("Target.attachToTarget", params, result_ -> new Target.SessionID((String) java.util.Objects.requireNonNull(result_.get("sessionId"))));
        }
        /**
         * Attaches to the target with given id.
         * @param targetId protocol value
         * @return a stage completing with the command result
         */
        public CompletionStage<Target.SessionID> attachToTarget(Target.TargetID targetId) {
            return attachToTarget(targetId, Optional.empty());
        }
        /**
         * Attaches to the browser target, only uses flat sessionId mode.
         * <p><b>Experimental:</b> this part of CDP may change without notice.
         * @return a stage completing with the command result
         */
        public CompletionStage<Target.SessionID> attachToBrowserTarget() {
            return client.call("Target.attachToBrowserTarget", null, result_ -> new Target.SessionID((String) java.util.Objects.requireNonNull(result_.get("sessionId"))));
        }
        /**
         * Closes the target. If the target is a page that gets closed too.
         * @param targetId protocol value
         * @return a stage completing with the command result
         */
        public CompletionStage<Boolean> closeTarget(Target.TargetID targetId) {
            Map<String, Object> params = new LinkedHashMap<>();
            params.put("targetId", CdpObject.json(targetId));
            return client.call("Target.closeTarget", params, result_ -> (Boolean) java.util.Objects.requireNonNull(result_.get("success")));
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
        public CompletionStage<Void> exposeDevToolsProtocol(Target.TargetID targetId, Optional<String> bindingName, Optional<Boolean> inheritPermissions) {
            Map<String, Object> params = new LinkedHashMap<>();
            params.put("targetId", CdpObject.json(targetId));
            bindingName.ifPresent(value_ -> params.put("bindingName", CdpObject.json(value_)));
            inheritPermissions.ifPresent(value_ -> params.put("inheritPermissions", value_));
            return client.call("Target.exposeDevToolsProtocol", params, result_ -> null);
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
            return exposeDevToolsProtocol(targetId, Optional.empty(), Optional.empty());
        }
        /**
         * Creates a new empty BrowserContext. Similar to an incognito profile but you can have more than one.
         * @param disposeOnDetach protocol value
         * @param proxyServer protocol value
         * @param proxyBypassList protocol value
         * @param originsWithUniversalNetworkAccess protocol value
         * @return a stage completing with the command result
         */
        public CompletionStage<Browser.BrowserContextID> createBrowserContext(Optional<Boolean> disposeOnDetach, Optional<String> proxyServer, Optional<String> proxyBypassList, Optional<java.util.List<String>> originsWithUniversalNetworkAccess) {
            Map<String, Object> params = new LinkedHashMap<>();
            disposeOnDetach.ifPresent(value_ -> params.put("disposeOnDetach", value_));
            proxyServer.ifPresent(value_ -> params.put("proxyServer", CdpObject.json(value_)));
            proxyBypassList.ifPresent(value_ -> params.put("proxyBypassList", CdpObject.json(value_)));
            originsWithUniversalNetworkAccess.ifPresent(value_ -> params.put("originsWithUniversalNetworkAccess", CdpObject.json(value_)));
            return client.call("Target.createBrowserContext", params, result_ -> new Browser.BrowserContextID((String) java.util.Objects.requireNonNull(result_.get("browserContextId"))));
        }
        /**
         * Creates a new empty BrowserContext. Similar to an incognito profile but you can have more than one.
         * @return a stage completing with the command result
         */
        public CompletionStage<Browser.BrowserContextID> createBrowserContext() {
            return createBrowserContext(Optional.empty(), Optional.empty(), Optional.empty(), Optional.empty());
        }
        /**
         * Returns all browser contexts created with {@code Target.createBrowserContext} method.
         * @return a stage completing with the command result
         */
        public CompletionStage<GetBrowserContextsResult> getBrowserContexts() {
            return client.call("Target.getBrowserContexts", null, result_ -> new GetBrowserContextsResult(result_));
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
        public CompletionStage<Target.TargetID> createTarget(String url, OptionalLong left, OptionalLong top, OptionalLong width, OptionalLong height, Optional<Target.WindowState> windowState, Optional<Browser.BrowserContextID> browserContextId, Optional<Boolean> enableBeginFrameControl, Optional<Boolean> newWindow, Optional<Boolean> background, Optional<Boolean> forTab, Optional<Boolean> hidden, Optional<Boolean> focus) {
            Map<String, Object> params = new LinkedHashMap<>();
            params.put("url", CdpObject.json(url));
            left.ifPresent(value_ -> params.put("left", value_));
            top.ifPresent(value_ -> params.put("top", value_));
            width.ifPresent(value_ -> params.put("width", value_));
            height.ifPresent(value_ -> params.put("height", value_));
            windowState.ifPresent(value_ -> params.put("windowState", CdpObject.json(value_)));
            browserContextId.ifPresent(value_ -> params.put("browserContextId", CdpObject.json(value_)));
            enableBeginFrameControl.ifPresent(value_ -> params.put("enableBeginFrameControl", value_));
            newWindow.ifPresent(value_ -> params.put("newWindow", value_));
            background.ifPresent(value_ -> params.put("background", value_));
            forTab.ifPresent(value_ -> params.put("forTab", value_));
            hidden.ifPresent(value_ -> params.put("hidden", value_));
            focus.ifPresent(value_ -> params.put("focus", value_));
            return client.call("Target.createTarget", params, result_ -> new Target.TargetID((String) java.util.Objects.requireNonNull(result_.get("targetId"))));
        }
        /**
         * Creates a new page.
         * @param url protocol value
         * @return a stage completing with the command result
         */
        public CompletionStage<Target.TargetID> createTarget(String url) {
            return createTarget(url, OptionalLong.empty(), OptionalLong.empty(), OptionalLong.empty(), OptionalLong.empty(), Optional.empty(), Optional.empty(), Optional.empty(), Optional.empty(), Optional.empty(), Optional.empty(), Optional.empty(), Optional.empty());
        }
        /**
         * Detaches session with given id.
         * @param sessionId protocol value
         * @param targetId protocol value
         * @return a stage completing when the command completes
         */
        public CompletionStage<Void> detachFromTarget(Optional<Target.SessionID> sessionId, Optional<Target.TargetID> targetId) {
            Map<String, Object> params = new LinkedHashMap<>();
            sessionId.ifPresent(value_ -> params.put("sessionId", CdpObject.json(value_)));
            targetId.ifPresent(value_ -> params.put("targetId", CdpObject.json(value_)));
            return client.call("Target.detachFromTarget", params, result_ -> null);
        }
        /**
         * Detaches session with given id.
         * @return a stage completing when the command completes
         */
        public CompletionStage<Void> detachFromTarget() {
            return detachFromTarget(Optional.empty(), Optional.empty());
        }
        /**
         * Deletes a BrowserContext. All the belonging pages will be closed without calling their beforeunload hooks.
         * @param browserContextId protocol value
         * @return a stage completing when the command completes
         */
        public CompletionStage<Void> disposeBrowserContext(Browser.BrowserContextID browserContextId) {
            Map<String, Object> params = new LinkedHashMap<>();
            params.put("browserContextId", CdpObject.json(browserContextId));
            return client.call("Target.disposeBrowserContext", params, result_ -> null);
        }
        /**
         * Returns information about a target.
         * <p><b>Experimental:</b> this part of CDP may change without notice.
         * @param targetId protocol value
         * @return a stage completing with the command result
         */
        public CompletionStage<Target.TargetInfo> getTargetInfo(Optional<Target.TargetID> targetId) {
            Map<String, Object> params = new LinkedHashMap<>();
            targetId.ifPresent(value_ -> params.put("targetId", CdpObject.json(value_)));
            return client.call("Target.getTargetInfo", params, result_ -> java.util.Objects.requireNonNull(Target.TargetInfo.fromMap(java.util.Objects.requireNonNull(CdpObject.objectMap(java.util.Objects.requireNonNull(result_.get("targetInfo")))))));
        }
        /**
         * Returns information about a target.
         * <p><b>Experimental:</b> this part of CDP may change without notice.
         * @return a stage completing with the command result
         */
        public CompletionStage<Target.TargetInfo> getTargetInfo() {
            return getTargetInfo(Optional.empty());
        }
        /**
         * Retrieves a list of available targets.
         * @param filter protocol value
         * @return a stage completing with the command result
         */
        public CompletionStage<java.util.List<Target.TargetInfo>> getTargets(Optional<java.util.List<Target.FilterEntry>> filter) {
            Map<String, Object> params = new LinkedHashMap<>();
            filter.ifPresent(value_ -> params.put("filter", CdpObject.json(value_)));
            return client.call("Target.getTargets", params, result_ -> CdpObject.requireList(java.util.Objects.requireNonNull(result_.get("targetInfos")), element0 -> java.util.Objects.requireNonNull(Target.TargetInfo.fromMap(java.util.Objects.requireNonNull(CdpObject.objectMap(element0))))));
        }
        /**
         * Retrieves a list of available targets.
         * @return a stage completing with the command result
         */
        public CompletionStage<java.util.List<Target.TargetInfo>> getTargets() {
            return getTargets(Optional.empty());
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
        public CompletionStage<Void> sendMessageToTarget(String message, Optional<Target.SessionID> sessionId, Optional<Target.TargetID> targetId) {
            Map<String, Object> params = new LinkedHashMap<>();
            params.put("message", CdpObject.json(message));
            sessionId.ifPresent(value_ -> params.put("sessionId", CdpObject.json(value_)));
            targetId.ifPresent(value_ -> params.put("targetId", CdpObject.json(value_)));
            return client.call("Target.sendMessageToTarget", params, result_ -> null);
        }
        /**
         * Sends protocol message over session with given id. Consider using flat mode instead; see commands attachToTarget, setAutoAttach, and crbug.com/991325.
         * @param message protocol value
         * @return a stage completing when the command completes
         * @deprecated Deprecated by the Chromium DevTools Protocol.
         */
        @Deprecated
        public CompletionStage<Void> sendMessageToTarget(String message) {
            return sendMessageToTarget(message, Optional.empty(), Optional.empty());
        }
        /**
         * Controls whether to automatically attach to new targets which are considered to be directly related to this one (for example, iframes or workers). When turned on, attaches to all existing related targets as well. When turned off, automatically detaches from all currently attached targets. This also clears all targets added by {@code autoAttachRelated} from the list of targets to watch for creation of related targets. You might want to call this recursively for auto-attached targets to attach to all available targets.
         * @param autoAttach protocol value
         * @param waitForDebuggerOnStart protocol value
         * @param flatten protocol value
         * @param filter protocol value
         * @return a stage completing when the command completes
         */
        public CompletionStage<Void> setAutoAttach(boolean autoAttach, boolean waitForDebuggerOnStart, Optional<Boolean> flatten, Optional<java.util.List<Target.FilterEntry>> filter) {
            Map<String, Object> params = new LinkedHashMap<>();
            params.put("autoAttach", CdpObject.json(autoAttach));
            params.put("waitForDebuggerOnStart", CdpObject.json(waitForDebuggerOnStart));
            flatten.ifPresent(value_ -> params.put("flatten", value_));
            filter.ifPresent(value_ -> params.put("filter", CdpObject.json(value_)));
            return client.call("Target.setAutoAttach", params, result_ -> null);
        }
        /**
         * Controls whether to automatically attach to new targets which are considered to be directly related to this one (for example, iframes or workers). When turned on, attaches to all existing related targets as well. When turned off, automatically detaches from all currently attached targets. This also clears all targets added by {@code autoAttachRelated} from the list of targets to watch for creation of related targets. You might want to call this recursively for auto-attached targets to attach to all available targets.
         * @param autoAttach protocol value
         * @param waitForDebuggerOnStart protocol value
         * @return a stage completing when the command completes
         */
        public CompletionStage<Void> setAutoAttach(boolean autoAttach, boolean waitForDebuggerOnStart) {
            return setAutoAttach(autoAttach, waitForDebuggerOnStart, Optional.empty(), Optional.empty());
        }
        /**
         * Adds the specified target to the list of targets that will be monitored for any related target creation (such as child frames, child workers and new versions of service worker) and reported through {@code attachedToTarget}. The specified target is also auto-attached. This cancels the effect of any previous {@code setAutoAttach} and is also cancelled by subsequent {@code setAutoAttach}. Only available at the Browser target.
         * <p><b>Experimental:</b> this part of CDP may change without notice.
         * @param targetId protocol value
         * @param waitForDebuggerOnStart protocol value
         * @param filter protocol value
         * @return a stage completing when the command completes
         */
        public CompletionStage<Void> autoAttachRelated(Target.TargetID targetId, boolean waitForDebuggerOnStart, Optional<java.util.List<Target.FilterEntry>> filter) {
            Map<String, Object> params = new LinkedHashMap<>();
            params.put("targetId", CdpObject.json(targetId));
            params.put("waitForDebuggerOnStart", CdpObject.json(waitForDebuggerOnStart));
            filter.ifPresent(value_ -> params.put("filter", CdpObject.json(value_)));
            return client.call("Target.autoAttachRelated", params, result_ -> null);
        }
        /**
         * Adds the specified target to the list of targets that will be monitored for any related target creation (such as child frames, child workers and new versions of service worker) and reported through {@code attachedToTarget}. The specified target is also auto-attached. This cancels the effect of any previous {@code setAutoAttach} and is also cancelled by subsequent {@code setAutoAttach}. Only available at the Browser target.
         * <p><b>Experimental:</b> this part of CDP may change without notice.
         * @param targetId protocol value
         * @param waitForDebuggerOnStart protocol value
         * @return a stage completing when the command completes
         */
        public CompletionStage<Void> autoAttachRelated(Target.TargetID targetId, boolean waitForDebuggerOnStart) {
            return autoAttachRelated(targetId, waitForDebuggerOnStart, Optional.empty());
        }
        /**
         * Controls whether to discover available targets and notify via {@code targetCreated/targetInfoChanged/targetDestroyed} events.
         * @param discover protocol value
         * @param filter protocol value
         * @return a stage completing when the command completes
         */
        public CompletionStage<Void> setDiscoverTargets(boolean discover, Optional<java.util.List<Target.FilterEntry>> filter) {
            Map<String, Object> params = new LinkedHashMap<>();
            params.put("discover", CdpObject.json(discover));
            filter.ifPresent(value_ -> params.put("filter", CdpObject.json(value_)));
            return client.call("Target.setDiscoverTargets", params, result_ -> null);
        }
        /**
         * Controls whether to discover available targets and notify via {@code targetCreated/targetInfoChanged/targetDestroyed} events.
         * @param discover protocol value
         * @return a stage completing when the command completes
         */
        public CompletionStage<Void> setDiscoverTargets(boolean discover) {
            return setDiscoverTargets(discover, Optional.empty());
        }
        /**
         * Enables target discovery for the specified locations, when {@code setDiscoverTargets} was set to {@code true}.
         * <p><b>Experimental:</b> this part of CDP may change without notice.
         * @param locations protocol value
         * @return a stage completing when the command completes
         */
        public CompletionStage<Void> setRemoteLocations(java.util.List<Target.RemoteLocation> locations) {
            Map<String, Object> params = new LinkedHashMap<>();
            params.put("locations", CdpObject.json(locations));
            return client.call("Target.setRemoteLocations", params, result_ -> null);
        }
        /**
         * Gets the targetId of the DevTools page target opened for the given target (if any).
         * <p><b>Experimental:</b> this part of CDP may change without notice.
         * @param targetId protocol value
         * @return a stage completing with the command result
         */
        public CompletionStage<Optional<Target.TargetID>> getDevToolsTarget(Target.TargetID targetId) {
            Map<String, Object> params = new LinkedHashMap<>();
            params.put("targetId", CdpObject.json(targetId));
            return client.call("Target.getDevToolsTarget", params, result_ -> Optional.ofNullable(result_.get("targetId") == null ? null : new Target.TargetID((String) result_.get("targetId"))));
        }
        /**
         * Opens a DevTools window for the target.
         * <p><b>Experimental:</b> this part of CDP may change without notice.
         * @param targetId protocol value
         * @param panelId protocol value
         * @return a stage completing with the command result
         */
        public CompletionStage<Target.TargetID> openDevTools(Target.TargetID targetId, Optional<String> panelId) {
            Map<String, Object> params = new LinkedHashMap<>();
            params.put("targetId", CdpObject.json(targetId));
            panelId.ifPresent(value_ -> params.put("panelId", CdpObject.json(value_)));
            return client.call("Target.openDevTools", params, result_ -> new Target.TargetID((String) java.util.Objects.requireNonNull(result_.get("targetId"))));
        }
        /**
         * Opens a DevTools window for the target.
         * <p><b>Experimental:</b> this part of CDP may change without notice.
         * @param targetId protocol value
         * @return a stage completing with the command result
         */
        public CompletionStage<Target.TargetID> openDevTools(Target.TargetID targetId) {
            return openDevTools(targetId, Optional.empty());
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
