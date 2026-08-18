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
 * Chrome DevTools Protocol Inspector domain.
 * <p><b>Experimental:</b> this part of CDP may change without notice.
 * @see <a href="https://chromium.googlesource.com/chromium/src/+/refs/tags/150.0.7871.213/third_party/blink/public/devtools_protocol/domains/Inspector.pdl">Pinned protocol source</a>
 */
@Generated("mvn generate-sources -pl cef4j-platform -Dcef.version=150.0.18+gdb11278+chromium-150.0.7871.213")
@SuppressWarnings({"EscapedEntity", "InvalidParam", "JavaLangClash", "MissingSummary", "Unchecked", "UnusedMethod"})
public final class Inspector {
    private Inspector() {}
    /**
     * Fired when remote debugging connection is about to be terminated. Contains detach reason.
     */
    public static final class DetachedEvent extends CdpObject {
        public DetachedEvent() {}
        private DetachedEvent(Map<String, Object> values) { super(values); }
        public static DetachedEvent fromMap(Map<String, Object> values) {
            return new DetachedEvent(values);
        }
        /**
         * The reason why connection has been terminated.
         * @return the protocol field value
         */
        public String reason() {
            return (String) require("reason");
        }
        /**
         * The reason why connection has been terminated.
         * @param reason field value
         * @return this model
         */
        public DetachedEvent reason(String reason) {
            set("reason", reason);
            return this;
        }
    }
    /**
     * Fired when debugging target has crashed
     */
    public static final class TargetCrashedEvent extends CdpObject {
        public TargetCrashedEvent() {}
        private TargetCrashedEvent(Map<String, Object> values) { super(values); }
        public static TargetCrashedEvent fromMap(Map<String, Object> values) {
            return new TargetCrashedEvent(values);
        }
    }
    /**
     * Fired when debugging target has reloaded after crash
     */
    public static final class TargetReloadedAfterCrashEvent extends CdpObject {
        public TargetReloadedAfterCrashEvent() {}
        private TargetReloadedAfterCrashEvent(Map<String, Object> values) { super(values); }
        public static TargetReloadedAfterCrashEvent fromMap(Map<String, Object> values) {
            return new TargetReloadedAfterCrashEvent(values);
        }
    }
    /**
     * Fired on worker targets when main worker script and any imported scripts have been evaluated.
     * <p><b>Experimental:</b> this part of CDP may change without notice.
     */
    public static final class WorkerScriptLoadedEvent extends CdpObject {
        public WorkerScriptLoadedEvent() {}
        private WorkerScriptLoadedEvent(Map<String, Object> values) { super(values); }
        public static WorkerScriptLoadedEvent fromMap(Map<String, Object> values) {
            return new WorkerScriptLoadedEvent(values);
        }
    }
    public static final class Client {
        private final CdpClient client;
        public Client(CdpClient client) { this.client = client; }
        /**
         * Disables inspector domain notifications.
         * @return a stage completing when the command completes
         */
        public CompletionStage<Void> disable() {
            return client.call("Inspector.disable", null, result_ -> null);
        }
        /**
         * Enables inspector domain notifications.
         * @return a stage completing when the command completes
         */
        public CompletionStage<Void> enable() {
            return client.call("Inspector.enable", null, result_ -> null);
        }
        /**
         * Fired when remote debugging connection is about to be terminated. Contains detach reason.
         * @param handler event callback
         * @return a removable subscription
         */
        public CdpSubscription onDetached(Consumer<DetachedEvent> handler) {
            return client.on("Inspector.detached", DetachedEvent::fromMap, handler);
        }
        /**
         * Fired when debugging target has crashed
         * @param handler event callback
         * @return a removable subscription
         */
        public CdpSubscription onTargetCrashed(Consumer<TargetCrashedEvent> handler) {
            return client.on("Inspector.targetCrashed", TargetCrashedEvent::fromMap, handler);
        }
        /**
         * Fired when debugging target has reloaded after crash
         * @param handler event callback
         * @return a removable subscription
         */
        public CdpSubscription onTargetReloadedAfterCrash(Consumer<TargetReloadedAfterCrashEvent> handler) {
            return client.on("Inspector.targetReloadedAfterCrash", TargetReloadedAfterCrashEvent::fromMap, handler);
        }
        /**
         * Fired on worker targets when main worker script and any imported scripts have been evaluated.
         * <p><b>Experimental:</b> this part of CDP may change without notice.
         * @param handler event callback
         * @return a removable subscription
         */
        public CdpSubscription onWorkerScriptLoaded(Consumer<WorkerScriptLoadedEvent> handler) {
            return client.on("Inspector.workerScriptLoaded", WorkerScriptLoadedEvent::fromMap, handler);
        }
    }
}
