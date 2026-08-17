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
 * Chrome DevTools Protocol Inspector domain.
 * <p><b>Experimental:</b> this part of CDP may change without notice.
 * @see <a href="https://chromium.googlesource.com/chromium/src/+/refs/tags/150.0.7871.213/third_party/blink/public/devtools_protocol/domains/Inspector.pdl">Pinned protocol source</a>
 */
@SuppressWarnings({"EscapedEntity", "JavaLangClash", "MissingSummary", "UnusedMethod"})
public final class Inspector {
    private Inspector() {}
    @Nullable private static Long numberAsLong(@Nullable Object value) { return value == null ? null : ((Number) value).longValue(); }
    @Nullable private static Double numberAsDouble(@Nullable Object value) { return value == null ? null : ((Number) value).doubleValue(); }
    /**
     * Disables inspector domain notifications.
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
     * Disables inspector domain notifications.
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
     * Enables inspector domain notifications.
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
     * Enables inspector domain notifications.
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
     * Fired when remote debugging connection is about to be terminated. Contains detach reason.
     */
    public static final class DetachedEvent extends CdpObject {
        private DetachedEvent(Map<String, Object> values) { super(values); }
        @Nullable public static DetachedEvent fromMap(@Nullable Map<String, Object> values) {
            return values == null ? null : new DetachedEvent(values);
        }
        public static Builder builder() { return new Builder(); }
        /**
         * The reason why connection has been terminated.
         * @return the protocol field value
         */
        @Nullable public String reason() {
            return (String) value("reason");
        }
        public static final class Builder {
            private final Map<String, Object> values = new LinkedHashMap<>();
            /**
             * The reason why connection has been terminated.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder reason(@Nullable String value) {
                if (value == null) values.remove("reason");
                else values.put("reason", jsonValue(value));
                return this;
            }
            public DetachedEvent build() {
                if (!values.containsKey("reason")) throw new IllegalStateException("Missing required CDP field: reason");
                return new DetachedEvent(values);
            }
        }
    }
    /**
     * Fired when debugging target has crashed
     */
    public static final class TargetCrashedEvent extends CdpObject {
        private TargetCrashedEvent(Map<String, Object> values) { super(values); }
        @Nullable public static TargetCrashedEvent fromMap(@Nullable Map<String, Object> values) {
            return values == null ? null : new TargetCrashedEvent(values);
        }
        public static Builder builder() { return new Builder(); }
        public static final class Builder {
            private final Map<String, Object> values = new LinkedHashMap<>();
            public TargetCrashedEvent build() {
                return new TargetCrashedEvent(values);
            }
        }
    }
    /**
     * Fired when debugging target has reloaded after crash
     */
    public static final class TargetReloadedAfterCrashEvent extends CdpObject {
        private TargetReloadedAfterCrashEvent(Map<String, Object> values) { super(values); }
        @Nullable public static TargetReloadedAfterCrashEvent fromMap(@Nullable Map<String, Object> values) {
            return values == null ? null : new TargetReloadedAfterCrashEvent(values);
        }
        public static Builder builder() { return new Builder(); }
        public static final class Builder {
            private final Map<String, Object> values = new LinkedHashMap<>();
            public TargetReloadedAfterCrashEvent build() {
                return new TargetReloadedAfterCrashEvent(values);
            }
        }
    }
    /**
     * Fired on worker targets when main worker script and any imported scripts have been evaluated.
     * <p><b>Experimental:</b> this part of CDP may change without notice.
     */
    public static final class WorkerScriptLoadedEvent extends CdpObject {
        private WorkerScriptLoadedEvent(Map<String, Object> values) { super(values); }
        @Nullable public static WorkerScriptLoadedEvent fromMap(@Nullable Map<String, Object> values) {
            return values == null ? null : new WorkerScriptLoadedEvent(values);
        }
        public static Builder builder() { return new Builder(); }
        public static final class Builder {
            private final Map<String, Object> values = new LinkedHashMap<>();
            public WorkerScriptLoadedEvent build() {
                return new WorkerScriptLoadedEvent(values);
            }
        }
    }
    public static final class Client {
        private final CdpClient client;
        public Client(CdpClient client) { this.client = client; }
        /**
         * Disables inspector domain notifications.
         * @return a stage completing with the command result
         */
        public CompletionStage<DisableResult> disable() {
            return client.call("Inspector.disable", null, DisableResult::fromMap);
        }
        /**
         * Enables inspector domain notifications.
         * @return a stage completing with the command result
         */
        public CompletionStage<EnableResult> enable() {
            return client.call("Inspector.enable", null, EnableResult::fromMap);
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
