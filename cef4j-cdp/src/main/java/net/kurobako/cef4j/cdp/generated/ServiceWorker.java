// GENERATED - do not edit. Regenerate via: mvn generate-sources -pl cef4j-platform -Dcef.version=150.0.18+gdb11278+chromium-150.0.7871.213
package net.kurobako.cef4j.cdp.generated;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.concurrent.CompletionStage;
import java.util.function.Consumer;
import javax.annotation.Nullable;
import javax.annotation.processing.Generated;
import net.kurobako.cef4j.cdp.CdpClient;
import net.kurobako.cef4j.cdp.CdpObject;
import net.kurobako.cef4j.cdp.CdpSubscription;

/**
 * Chrome DevTools Protocol ServiceWorker domain.
 * <p><b>Experimental:</b> this part of CDP may change without notice.
 * @see <a href="https://chromium.googlesource.com/chromium/src/+/refs/tags/150.0.7871.213/third_party/blink/public/devtools_protocol/domains/ServiceWorker.pdl">Pinned protocol source</a>
 */
@Generated("mvn generate-sources -pl cef4j-platform -Dcef.version=150.0.18+gdb11278+chromium-150.0.7871.213")
@SuppressWarnings({"EscapedEntity", "JavaLangClash", "MissingSummary", "UnusedMethod"})
public final class ServiceWorker {
    private ServiceWorker() {}
    @Nullable private static Long numberAsLong(@Nullable Object value) { return value == null ? null : ((Number) value).longValue(); }
    @Nullable private static Double numberAsDouble(@Nullable Object value) { return value == null ? null : ((Number) value).doubleValue(); }
    /**
     * ServiceWorker registration.
     */
    public static final class ServiceWorkerRegistration extends CdpObject {
        private ServiceWorkerRegistration(Map<String, Object> values) { super(values); }
        @Nullable public static ServiceWorkerRegistration fromMap(@Nullable Map<String, Object> values) {
            return values == null ? null : new ServiceWorkerRegistration(values);
        }
        public static Builder builder() { return new Builder(); }
        /**
         * Returns the registrationId field.
         * @return the protocol field value
         */
        @Nullable public String registrationId() {
            return (String) value("registrationId");
        }
        /**
         * Returns the scopeURL field.
         * @return the protocol field value
         */
        @Nullable public String scopeURL() {
            return (String) value("scopeURL");
        }
        /**
         * Returns the isDeleted field.
         * @return the protocol field value
         */
        @Nullable public Boolean isDeleted() {
            return (Boolean) value("isDeleted");
        }
        public static final class Builder {
            private final Map<String, Object> values = new LinkedHashMap<>();
            /**
             * Sets the registrationId field.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder registrationId(@Nullable String value) {
                if (value == null) values.remove("registrationId");
                else values.put("registrationId", jsonValue(value));
                return this;
            }
            /**
             * Sets the scopeURL field.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder scopeURL(@Nullable String value) {
                if (value == null) values.remove("scopeURL");
                else values.put("scopeURL", jsonValue(value));
                return this;
            }
            /**
             * Sets the isDeleted field.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder isDeleted(@Nullable Boolean value) {
                if (value == null) values.remove("isDeleted");
                else values.put("isDeleted", jsonValue(value));
                return this;
            }
            public ServiceWorkerRegistration build() {
                if (!values.containsKey("registrationId")) throw new IllegalStateException("Missing required CDP field: registrationId");
                if (!values.containsKey("scopeURL")) throw new IllegalStateException("Missing required CDP field: scopeURL");
                if (!values.containsKey("isDeleted")) throw new IllegalStateException("Missing required CDP field: isDeleted");
                return new ServiceWorkerRegistration(values);
            }
        }
    }
    /**
     * Wire values for ServiceWorkerVersionRunningStatus.
     */
    public static final class ServiceWorkerVersionRunningStatus {
        private ServiceWorkerVersionRunningStatus() {}
        public static final String STOPPED = "stopped";
        public static final String STARTING = "starting";
        public static final String RUNNING = "running";
        public static final String STOPPING = "stopping";
    }
    /**
     * Wire values for ServiceWorkerVersionStatus.
     */
    public static final class ServiceWorkerVersionStatus {
        private ServiceWorkerVersionStatus() {}
        public static final String NEW = "new";
        public static final String INSTALLING = "installing";
        public static final String INSTALLED = "installed";
        public static final String ACTIVATING = "activating";
        public static final String ACTIVATED = "activated";
        public static final String REDUNDANT = "redundant";
    }
    /**
     * ServiceWorker version.
     */
    public static final class ServiceWorkerVersion extends CdpObject {
        private ServiceWorkerVersion(Map<String, Object> values) { super(values); }
        @Nullable public static ServiceWorkerVersion fromMap(@Nullable Map<String, Object> values) {
            return values == null ? null : new ServiceWorkerVersion(values);
        }
        public static Builder builder() { return new Builder(); }
        /**
         * Returns the versionId field.
         * @return the protocol field value
         */
        @Nullable public String versionId() {
            return (String) value("versionId");
        }
        /**
         * Returns the registrationId field.
         * @return the protocol field value
         */
        @Nullable public String registrationId() {
            return (String) value("registrationId");
        }
        /**
         * Returns the scriptURL field.
         * @return the protocol field value
         */
        @Nullable public String scriptURL() {
            return (String) value("scriptURL");
        }
        /**
         * Returns the runningStatus field.
         * @return the protocol field value
         */
        @Nullable public String runningStatus() {
            return (String) value("runningStatus");
        }
        /**
         * Returns the status field.
         * @return the protocol field value
         */
        @Nullable public String status() {
            return (String) value("status");
        }
        /**
         * The Last-Modified header value of the main script.
         * @return the protocol field value
         */
        @Nullable public Double scriptLastModified() {
            return numberAsDouble(value("scriptLastModified"));
        }
        /**
         * The time at which the response headers of the main script were received from the server. For cached script it is the last time the cache entry was validated.
         * @return the protocol field value
         */
        @Nullable public Double scriptResponseTime() {
            return numberAsDouble(value("scriptResponseTime"));
        }
        /**
         * Returns the controlledClients field.
         * @return the protocol field value
         */
        @Nullable public java.util.List<String> controlledClients() {
            return list(value("controlledClients"), element0 -> (String) element0);
        }
        /**
         * Returns the targetId field.
         * @return the protocol field value
         */
        @Nullable public String targetId() {
            return (String) value("targetId");
        }
        /**
         * Returns the routerRules field.
         * @return the protocol field value
         */
        @Nullable public String routerRules() {
            return (String) value("routerRules");
        }
        public static final class Builder {
            private final Map<String, Object> values = new LinkedHashMap<>();
            /**
             * Sets the versionId field.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder versionId(@Nullable String value) {
                if (value == null) values.remove("versionId");
                else values.put("versionId", jsonValue(value));
                return this;
            }
            /**
             * Sets the registrationId field.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder registrationId(@Nullable String value) {
                if (value == null) values.remove("registrationId");
                else values.put("registrationId", jsonValue(value));
                return this;
            }
            /**
             * Sets the scriptURL field.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder scriptURL(@Nullable String value) {
                if (value == null) values.remove("scriptURL");
                else values.put("scriptURL", jsonValue(value));
                return this;
            }
            /**
             * Sets the runningStatus field.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder runningStatus(@Nullable String value) {
                if (value == null) values.remove("runningStatus");
                else values.put("runningStatus", jsonValue(value));
                return this;
            }
            /**
             * Sets the status field.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder status(@Nullable String value) {
                if (value == null) values.remove("status");
                else values.put("status", jsonValue(value));
                return this;
            }
            /**
             * The Last-Modified header value of the main script.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder scriptLastModified(@Nullable Double value) {
                if (value == null) values.remove("scriptLastModified");
                else values.put("scriptLastModified", jsonValue(value));
                return this;
            }
            /**
             * The time at which the response headers of the main script were received from the server. For cached script it is the last time the cache entry was validated.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder scriptResponseTime(@Nullable Double value) {
                if (value == null) values.remove("scriptResponseTime");
                else values.put("scriptResponseTime", jsonValue(value));
                return this;
            }
            /**
             * Sets the controlledClients field.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder controlledClients(@Nullable java.util.List<String> value) {
                if (value == null) values.remove("controlledClients");
                else values.put("controlledClients", jsonValue(value));
                return this;
            }
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
             * Sets the routerRules field.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder routerRules(@Nullable String value) {
                if (value == null) values.remove("routerRules");
                else values.put("routerRules", jsonValue(value));
                return this;
            }
            public ServiceWorkerVersion build() {
                if (!values.containsKey("versionId")) throw new IllegalStateException("Missing required CDP field: versionId");
                if (!values.containsKey("registrationId")) throw new IllegalStateException("Missing required CDP field: registrationId");
                if (!values.containsKey("scriptURL")) throw new IllegalStateException("Missing required CDP field: scriptURL");
                if (!values.containsKey("runningStatus")) throw new IllegalStateException("Missing required CDP field: runningStatus");
                if (!values.containsKey("status")) throw new IllegalStateException("Missing required CDP field: status");
                return new ServiceWorkerVersion(values);
            }
        }
    }
    /**
     * ServiceWorker error message.
     */
    public static final class ServiceWorkerErrorMessage extends CdpObject {
        private ServiceWorkerErrorMessage(Map<String, Object> values) { super(values); }
        @Nullable public static ServiceWorkerErrorMessage fromMap(@Nullable Map<String, Object> values) {
            return values == null ? null : new ServiceWorkerErrorMessage(values);
        }
        public static Builder builder() { return new Builder(); }
        /**
         * Returns the errorMessage field.
         * @return the protocol field value
         */
        @Nullable public String errorMessage() {
            return (String) value("errorMessage");
        }
        /**
         * Returns the registrationId field.
         * @return the protocol field value
         */
        @Nullable public String registrationId() {
            return (String) value("registrationId");
        }
        /**
         * Returns the versionId field.
         * @return the protocol field value
         */
        @Nullable public String versionId() {
            return (String) value("versionId");
        }
        /**
         * Returns the sourceURL field.
         * @return the protocol field value
         */
        @Nullable public String sourceURL() {
            return (String) value("sourceURL");
        }
        /**
         * Returns the lineNumber field.
         * @return the protocol field value
         */
        @Nullable public Long lineNumber() {
            return numberAsLong(value("lineNumber"));
        }
        /**
         * Returns the columnNumber field.
         * @return the protocol field value
         */
        @Nullable public Long columnNumber() {
            return numberAsLong(value("columnNumber"));
        }
        public static final class Builder {
            private final Map<String, Object> values = new LinkedHashMap<>();
            /**
             * Sets the errorMessage field.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder errorMessage(@Nullable String value) {
                if (value == null) values.remove("errorMessage");
                else values.put("errorMessage", jsonValue(value));
                return this;
            }
            /**
             * Sets the registrationId field.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder registrationId(@Nullable String value) {
                if (value == null) values.remove("registrationId");
                else values.put("registrationId", jsonValue(value));
                return this;
            }
            /**
             * Sets the versionId field.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder versionId(@Nullable String value) {
                if (value == null) values.remove("versionId");
                else values.put("versionId", jsonValue(value));
                return this;
            }
            /**
             * Sets the sourceURL field.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder sourceURL(@Nullable String value) {
                if (value == null) values.remove("sourceURL");
                else values.put("sourceURL", jsonValue(value));
                return this;
            }
            /**
             * Sets the lineNumber field.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder lineNumber(@Nullable Long value) {
                if (value == null) values.remove("lineNumber");
                else values.put("lineNumber", jsonValue(value));
                return this;
            }
            /**
             * Sets the columnNumber field.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder columnNumber(@Nullable Long value) {
                if (value == null) values.remove("columnNumber");
                else values.put("columnNumber", jsonValue(value));
                return this;
            }
            public ServiceWorkerErrorMessage build() {
                if (!values.containsKey("errorMessage")) throw new IllegalStateException("Missing required CDP field: errorMessage");
                if (!values.containsKey("registrationId")) throw new IllegalStateException("Missing required CDP field: registrationId");
                if (!values.containsKey("versionId")) throw new IllegalStateException("Missing required CDP field: versionId");
                if (!values.containsKey("sourceURL")) throw new IllegalStateException("Missing required CDP field: sourceURL");
                if (!values.containsKey("lineNumber")) throw new IllegalStateException("Missing required CDP field: lineNumber");
                if (!values.containsKey("columnNumber")) throw new IllegalStateException("Missing required CDP field: columnNumber");
                return new ServiceWorkerErrorMessage(values);
            }
        }
    }
    /**
     * Parameters for ServiceWorker.deliverPushMessage.
     */
    public static final class DeliverPushMessageParams extends CdpObject {
        private DeliverPushMessageParams(Map<String, Object> values) { super(values); }
        @Nullable public static DeliverPushMessageParams fromMap(@Nullable Map<String, Object> values) {
            return values == null ? null : new DeliverPushMessageParams(values);
        }
        public static Builder builder() { return new Builder(); }
        /**
         * Returns the origin field.
         * @return the protocol field value
         */
        @Nullable public String origin() {
            return (String) value("origin");
        }
        /**
         * Returns the registrationId field.
         * @return the protocol field value
         */
        @Nullable public String registrationId() {
            return (String) value("registrationId");
        }
        /**
         * Returns the data field.
         * @return the protocol field value
         */
        @Nullable public String data() {
            return (String) value("data");
        }
        public static final class Builder {
            private final Map<String, Object> values = new LinkedHashMap<>();
            /**
             * Sets the origin field.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder origin(@Nullable String value) {
                if (value == null) values.remove("origin");
                else values.put("origin", jsonValue(value));
                return this;
            }
            /**
             * Sets the registrationId field.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder registrationId(@Nullable String value) {
                if (value == null) values.remove("registrationId");
                else values.put("registrationId", jsonValue(value));
                return this;
            }
            /**
             * Sets the data field.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder data(@Nullable String value) {
                if (value == null) values.remove("data");
                else values.put("data", jsonValue(value));
                return this;
            }
            public DeliverPushMessageParams build() {
                if (!values.containsKey("origin")) throw new IllegalStateException("Missing required CDP field: origin");
                if (!values.containsKey("registrationId")) throw new IllegalStateException("Missing required CDP field: registrationId");
                if (!values.containsKey("data")) throw new IllegalStateException("Missing required CDP field: data");
                return new DeliverPushMessageParams(values);
            }
        }
    }
    /**
     * Result of ServiceWorker.deliverPushMessage.
     */
    public static final class DeliverPushMessageResult extends CdpObject {
        private DeliverPushMessageResult(Map<String, Object> values) { super(values); }
        @Nullable public static DeliverPushMessageResult fromMap(@Nullable Map<String, Object> values) {
            return values == null ? null : new DeliverPushMessageResult(values);
        }
        public static Builder builder() { return new Builder(); }
        public static final class Builder {
            private final Map<String, Object> values = new LinkedHashMap<>();
            public DeliverPushMessageResult build() {
                return new DeliverPushMessageResult(values);
            }
        }
    }
    /**
     * Parameters for ServiceWorker.disable.
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
     * Result of ServiceWorker.disable.
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
     * Parameters for ServiceWorker.dispatchSyncEvent.
     */
    public static final class DispatchSyncEventParams extends CdpObject {
        private DispatchSyncEventParams(Map<String, Object> values) { super(values); }
        @Nullable public static DispatchSyncEventParams fromMap(@Nullable Map<String, Object> values) {
            return values == null ? null : new DispatchSyncEventParams(values);
        }
        public static Builder builder() { return new Builder(); }
        /**
         * Returns the origin field.
         * @return the protocol field value
         */
        @Nullable public String origin() {
            return (String) value("origin");
        }
        /**
         * Returns the registrationId field.
         * @return the protocol field value
         */
        @Nullable public String registrationId() {
            return (String) value("registrationId");
        }
        /**
         * Returns the tag field.
         * @return the protocol field value
         */
        @Nullable public String tag() {
            return (String) value("tag");
        }
        /**
         * Returns the lastChance field.
         * @return the protocol field value
         */
        @Nullable public Boolean lastChance() {
            return (Boolean) value("lastChance");
        }
        public static final class Builder {
            private final Map<String, Object> values = new LinkedHashMap<>();
            /**
             * Sets the origin field.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder origin(@Nullable String value) {
                if (value == null) values.remove("origin");
                else values.put("origin", jsonValue(value));
                return this;
            }
            /**
             * Sets the registrationId field.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder registrationId(@Nullable String value) {
                if (value == null) values.remove("registrationId");
                else values.put("registrationId", jsonValue(value));
                return this;
            }
            /**
             * Sets the tag field.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder tag(@Nullable String value) {
                if (value == null) values.remove("tag");
                else values.put("tag", jsonValue(value));
                return this;
            }
            /**
             * Sets the lastChance field.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder lastChance(@Nullable Boolean value) {
                if (value == null) values.remove("lastChance");
                else values.put("lastChance", jsonValue(value));
                return this;
            }
            public DispatchSyncEventParams build() {
                if (!values.containsKey("origin")) throw new IllegalStateException("Missing required CDP field: origin");
                if (!values.containsKey("registrationId")) throw new IllegalStateException("Missing required CDP field: registrationId");
                if (!values.containsKey("tag")) throw new IllegalStateException("Missing required CDP field: tag");
                if (!values.containsKey("lastChance")) throw new IllegalStateException("Missing required CDP field: lastChance");
                return new DispatchSyncEventParams(values);
            }
        }
    }
    /**
     * Result of ServiceWorker.dispatchSyncEvent.
     */
    public static final class DispatchSyncEventResult extends CdpObject {
        private DispatchSyncEventResult(Map<String, Object> values) { super(values); }
        @Nullable public static DispatchSyncEventResult fromMap(@Nullable Map<String, Object> values) {
            return values == null ? null : new DispatchSyncEventResult(values);
        }
        public static Builder builder() { return new Builder(); }
        public static final class Builder {
            private final Map<String, Object> values = new LinkedHashMap<>();
            public DispatchSyncEventResult build() {
                return new DispatchSyncEventResult(values);
            }
        }
    }
    /**
     * Parameters for ServiceWorker.dispatchPeriodicSyncEvent.
     */
    public static final class DispatchPeriodicSyncEventParams extends CdpObject {
        private DispatchPeriodicSyncEventParams(Map<String, Object> values) { super(values); }
        @Nullable public static DispatchPeriodicSyncEventParams fromMap(@Nullable Map<String, Object> values) {
            return values == null ? null : new DispatchPeriodicSyncEventParams(values);
        }
        public static Builder builder() { return new Builder(); }
        /**
         * Returns the origin field.
         * @return the protocol field value
         */
        @Nullable public String origin() {
            return (String) value("origin");
        }
        /**
         * Returns the registrationId field.
         * @return the protocol field value
         */
        @Nullable public String registrationId() {
            return (String) value("registrationId");
        }
        /**
         * Returns the tag field.
         * @return the protocol field value
         */
        @Nullable public String tag() {
            return (String) value("tag");
        }
        public static final class Builder {
            private final Map<String, Object> values = new LinkedHashMap<>();
            /**
             * Sets the origin field.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder origin(@Nullable String value) {
                if (value == null) values.remove("origin");
                else values.put("origin", jsonValue(value));
                return this;
            }
            /**
             * Sets the registrationId field.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder registrationId(@Nullable String value) {
                if (value == null) values.remove("registrationId");
                else values.put("registrationId", jsonValue(value));
                return this;
            }
            /**
             * Sets the tag field.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder tag(@Nullable String value) {
                if (value == null) values.remove("tag");
                else values.put("tag", jsonValue(value));
                return this;
            }
            public DispatchPeriodicSyncEventParams build() {
                if (!values.containsKey("origin")) throw new IllegalStateException("Missing required CDP field: origin");
                if (!values.containsKey("registrationId")) throw new IllegalStateException("Missing required CDP field: registrationId");
                if (!values.containsKey("tag")) throw new IllegalStateException("Missing required CDP field: tag");
                return new DispatchPeriodicSyncEventParams(values);
            }
        }
    }
    /**
     * Result of ServiceWorker.dispatchPeriodicSyncEvent.
     */
    public static final class DispatchPeriodicSyncEventResult extends CdpObject {
        private DispatchPeriodicSyncEventResult(Map<String, Object> values) { super(values); }
        @Nullable public static DispatchPeriodicSyncEventResult fromMap(@Nullable Map<String, Object> values) {
            return values == null ? null : new DispatchPeriodicSyncEventResult(values);
        }
        public static Builder builder() { return new Builder(); }
        public static final class Builder {
            private final Map<String, Object> values = new LinkedHashMap<>();
            public DispatchPeriodicSyncEventResult build() {
                return new DispatchPeriodicSyncEventResult(values);
            }
        }
    }
    /**
     * Parameters for ServiceWorker.enable.
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
     * Result of ServiceWorker.enable.
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
     * Parameters for ServiceWorker.setForceUpdateOnPageLoad.
     */
    public static final class SetForceUpdateOnPageLoadParams extends CdpObject {
        private SetForceUpdateOnPageLoadParams(Map<String, Object> values) { super(values); }
        @Nullable public static SetForceUpdateOnPageLoadParams fromMap(@Nullable Map<String, Object> values) {
            return values == null ? null : new SetForceUpdateOnPageLoadParams(values);
        }
        public static Builder builder() { return new Builder(); }
        /**
         * Returns the forceUpdateOnPageLoad field.
         * @return the protocol field value
         */
        @Nullable public Boolean forceUpdateOnPageLoad() {
            return (Boolean) value("forceUpdateOnPageLoad");
        }
        public static final class Builder {
            private final Map<String, Object> values = new LinkedHashMap<>();
            /**
             * Sets the forceUpdateOnPageLoad field.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder forceUpdateOnPageLoad(@Nullable Boolean value) {
                if (value == null) values.remove("forceUpdateOnPageLoad");
                else values.put("forceUpdateOnPageLoad", jsonValue(value));
                return this;
            }
            public SetForceUpdateOnPageLoadParams build() {
                if (!values.containsKey("forceUpdateOnPageLoad")) throw new IllegalStateException("Missing required CDP field: forceUpdateOnPageLoad");
                return new SetForceUpdateOnPageLoadParams(values);
            }
        }
    }
    /**
     * Result of ServiceWorker.setForceUpdateOnPageLoad.
     */
    public static final class SetForceUpdateOnPageLoadResult extends CdpObject {
        private SetForceUpdateOnPageLoadResult(Map<String, Object> values) { super(values); }
        @Nullable public static SetForceUpdateOnPageLoadResult fromMap(@Nullable Map<String, Object> values) {
            return values == null ? null : new SetForceUpdateOnPageLoadResult(values);
        }
        public static Builder builder() { return new Builder(); }
        public static final class Builder {
            private final Map<String, Object> values = new LinkedHashMap<>();
            public SetForceUpdateOnPageLoadResult build() {
                return new SetForceUpdateOnPageLoadResult(values);
            }
        }
    }
    /**
     * Parameters for ServiceWorker.skipWaiting.
     */
    public static final class SkipWaitingParams extends CdpObject {
        private SkipWaitingParams(Map<String, Object> values) { super(values); }
        @Nullable public static SkipWaitingParams fromMap(@Nullable Map<String, Object> values) {
            return values == null ? null : new SkipWaitingParams(values);
        }
        public static Builder builder() { return new Builder(); }
        /**
         * Returns the scopeURL field.
         * @return the protocol field value
         */
        @Nullable public String scopeURL() {
            return (String) value("scopeURL");
        }
        public static final class Builder {
            private final Map<String, Object> values = new LinkedHashMap<>();
            /**
             * Sets the scopeURL field.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder scopeURL(@Nullable String value) {
                if (value == null) values.remove("scopeURL");
                else values.put("scopeURL", jsonValue(value));
                return this;
            }
            public SkipWaitingParams build() {
                if (!values.containsKey("scopeURL")) throw new IllegalStateException("Missing required CDP field: scopeURL");
                return new SkipWaitingParams(values);
            }
        }
    }
    /**
     * Result of ServiceWorker.skipWaiting.
     */
    public static final class SkipWaitingResult extends CdpObject {
        private SkipWaitingResult(Map<String, Object> values) { super(values); }
        @Nullable public static SkipWaitingResult fromMap(@Nullable Map<String, Object> values) {
            return values == null ? null : new SkipWaitingResult(values);
        }
        public static Builder builder() { return new Builder(); }
        public static final class Builder {
            private final Map<String, Object> values = new LinkedHashMap<>();
            public SkipWaitingResult build() {
                return new SkipWaitingResult(values);
            }
        }
    }
    /**
     * Parameters for ServiceWorker.startWorker.
     */
    public static final class StartWorkerParams extends CdpObject {
        private StartWorkerParams(Map<String, Object> values) { super(values); }
        @Nullable public static StartWorkerParams fromMap(@Nullable Map<String, Object> values) {
            return values == null ? null : new StartWorkerParams(values);
        }
        public static Builder builder() { return new Builder(); }
        /**
         * Returns the scopeURL field.
         * @return the protocol field value
         */
        @Nullable public String scopeURL() {
            return (String) value("scopeURL");
        }
        public static final class Builder {
            private final Map<String, Object> values = new LinkedHashMap<>();
            /**
             * Sets the scopeURL field.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder scopeURL(@Nullable String value) {
                if (value == null) values.remove("scopeURL");
                else values.put("scopeURL", jsonValue(value));
                return this;
            }
            public StartWorkerParams build() {
                if (!values.containsKey("scopeURL")) throw new IllegalStateException("Missing required CDP field: scopeURL");
                return new StartWorkerParams(values);
            }
        }
    }
    /**
     * Result of ServiceWorker.startWorker.
     */
    public static final class StartWorkerResult extends CdpObject {
        private StartWorkerResult(Map<String, Object> values) { super(values); }
        @Nullable public static StartWorkerResult fromMap(@Nullable Map<String, Object> values) {
            return values == null ? null : new StartWorkerResult(values);
        }
        public static Builder builder() { return new Builder(); }
        public static final class Builder {
            private final Map<String, Object> values = new LinkedHashMap<>();
            public StartWorkerResult build() {
                return new StartWorkerResult(values);
            }
        }
    }
    /**
     * Parameters for ServiceWorker.stopAllWorkers.
     */
    public static final class StopAllWorkersParams extends CdpObject {
        private StopAllWorkersParams(Map<String, Object> values) { super(values); }
        @Nullable public static StopAllWorkersParams fromMap(@Nullable Map<String, Object> values) {
            return values == null ? null : new StopAllWorkersParams(values);
        }
        public static Builder builder() { return new Builder(); }
        public static final class Builder {
            private final Map<String, Object> values = new LinkedHashMap<>();
            public StopAllWorkersParams build() {
                return new StopAllWorkersParams(values);
            }
        }
    }
    /**
     * Result of ServiceWorker.stopAllWorkers.
     */
    public static final class StopAllWorkersResult extends CdpObject {
        private StopAllWorkersResult(Map<String, Object> values) { super(values); }
        @Nullable public static StopAllWorkersResult fromMap(@Nullable Map<String, Object> values) {
            return values == null ? null : new StopAllWorkersResult(values);
        }
        public static Builder builder() { return new Builder(); }
        public static final class Builder {
            private final Map<String, Object> values = new LinkedHashMap<>();
            public StopAllWorkersResult build() {
                return new StopAllWorkersResult(values);
            }
        }
    }
    /**
     * Parameters for ServiceWorker.stopWorker.
     */
    public static final class StopWorkerParams extends CdpObject {
        private StopWorkerParams(Map<String, Object> values) { super(values); }
        @Nullable public static StopWorkerParams fromMap(@Nullable Map<String, Object> values) {
            return values == null ? null : new StopWorkerParams(values);
        }
        public static Builder builder() { return new Builder(); }
        /**
         * Returns the versionId field.
         * @return the protocol field value
         */
        @Nullable public String versionId() {
            return (String) value("versionId");
        }
        public static final class Builder {
            private final Map<String, Object> values = new LinkedHashMap<>();
            /**
             * Sets the versionId field.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder versionId(@Nullable String value) {
                if (value == null) values.remove("versionId");
                else values.put("versionId", jsonValue(value));
                return this;
            }
            public StopWorkerParams build() {
                if (!values.containsKey("versionId")) throw new IllegalStateException("Missing required CDP field: versionId");
                return new StopWorkerParams(values);
            }
        }
    }
    /**
     * Result of ServiceWorker.stopWorker.
     */
    public static final class StopWorkerResult extends CdpObject {
        private StopWorkerResult(Map<String, Object> values) { super(values); }
        @Nullable public static StopWorkerResult fromMap(@Nullable Map<String, Object> values) {
            return values == null ? null : new StopWorkerResult(values);
        }
        public static Builder builder() { return new Builder(); }
        public static final class Builder {
            private final Map<String, Object> values = new LinkedHashMap<>();
            public StopWorkerResult build() {
                return new StopWorkerResult(values);
            }
        }
    }
    /**
     * Parameters for ServiceWorker.unregister.
     */
    public static final class UnregisterParams extends CdpObject {
        private UnregisterParams(Map<String, Object> values) { super(values); }
        @Nullable public static UnregisterParams fromMap(@Nullable Map<String, Object> values) {
            return values == null ? null : new UnregisterParams(values);
        }
        public static Builder builder() { return new Builder(); }
        /**
         * Returns the scopeURL field.
         * @return the protocol field value
         */
        @Nullable public String scopeURL() {
            return (String) value("scopeURL");
        }
        public static final class Builder {
            private final Map<String, Object> values = new LinkedHashMap<>();
            /**
             * Sets the scopeURL field.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder scopeURL(@Nullable String value) {
                if (value == null) values.remove("scopeURL");
                else values.put("scopeURL", jsonValue(value));
                return this;
            }
            public UnregisterParams build() {
                if (!values.containsKey("scopeURL")) throw new IllegalStateException("Missing required CDP field: scopeURL");
                return new UnregisterParams(values);
            }
        }
    }
    /**
     * Result of ServiceWorker.unregister.
     */
    public static final class UnregisterResult extends CdpObject {
        private UnregisterResult(Map<String, Object> values) { super(values); }
        @Nullable public static UnregisterResult fromMap(@Nullable Map<String, Object> values) {
            return values == null ? null : new UnregisterResult(values);
        }
        public static Builder builder() { return new Builder(); }
        public static final class Builder {
            private final Map<String, Object> values = new LinkedHashMap<>();
            public UnregisterResult build() {
                return new UnregisterResult(values);
            }
        }
    }
    /**
     * Parameters for ServiceWorker.updateRegistration.
     */
    public static final class UpdateRegistrationParams extends CdpObject {
        private UpdateRegistrationParams(Map<String, Object> values) { super(values); }
        @Nullable public static UpdateRegistrationParams fromMap(@Nullable Map<String, Object> values) {
            return values == null ? null : new UpdateRegistrationParams(values);
        }
        public static Builder builder() { return new Builder(); }
        /**
         * Returns the scopeURL field.
         * @return the protocol field value
         */
        @Nullable public String scopeURL() {
            return (String) value("scopeURL");
        }
        public static final class Builder {
            private final Map<String, Object> values = new LinkedHashMap<>();
            /**
             * Sets the scopeURL field.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder scopeURL(@Nullable String value) {
                if (value == null) values.remove("scopeURL");
                else values.put("scopeURL", jsonValue(value));
                return this;
            }
            public UpdateRegistrationParams build() {
                if (!values.containsKey("scopeURL")) throw new IllegalStateException("Missing required CDP field: scopeURL");
                return new UpdateRegistrationParams(values);
            }
        }
    }
    /**
     * Result of ServiceWorker.updateRegistration.
     */
    public static final class UpdateRegistrationResult extends CdpObject {
        private UpdateRegistrationResult(Map<String, Object> values) { super(values); }
        @Nullable public static UpdateRegistrationResult fromMap(@Nullable Map<String, Object> values) {
            return values == null ? null : new UpdateRegistrationResult(values);
        }
        public static Builder builder() { return new Builder(); }
        public static final class Builder {
            private final Map<String, Object> values = new LinkedHashMap<>();
            public UpdateRegistrationResult build() {
                return new UpdateRegistrationResult(values);
            }
        }
    }
    /**
     * Payload of the ServiceWorker.workerErrorReported event.
     */
    public static final class WorkerErrorReportedEvent extends CdpObject {
        private WorkerErrorReportedEvent(Map<String, Object> values) { super(values); }
        @Nullable public static WorkerErrorReportedEvent fromMap(@Nullable Map<String, Object> values) {
            return values == null ? null : new WorkerErrorReportedEvent(values);
        }
        public static Builder builder() { return new Builder(); }
        /**
         * Returns the errorMessage field.
         * @return the protocol field value
         */
        @Nullable public ServiceWorker.ServiceWorkerErrorMessage errorMessage() {
            return ServiceWorker.ServiceWorkerErrorMessage.fromMap(objectMap(value("errorMessage")));
        }
        public static final class Builder {
            private final Map<String, Object> values = new LinkedHashMap<>();
            /**
             * Sets the errorMessage field.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder errorMessage(@Nullable ServiceWorker.ServiceWorkerErrorMessage value) {
                if (value == null) values.remove("errorMessage");
                else values.put("errorMessage", jsonValue(value));
                return this;
            }
            public WorkerErrorReportedEvent build() {
                if (!values.containsKey("errorMessage")) throw new IllegalStateException("Missing required CDP field: errorMessage");
                return new WorkerErrorReportedEvent(values);
            }
        }
    }
    /**
     * Payload of the ServiceWorker.workerRegistrationUpdated event.
     */
    public static final class WorkerRegistrationUpdatedEvent extends CdpObject {
        private WorkerRegistrationUpdatedEvent(Map<String, Object> values) { super(values); }
        @Nullable public static WorkerRegistrationUpdatedEvent fromMap(@Nullable Map<String, Object> values) {
            return values == null ? null : new WorkerRegistrationUpdatedEvent(values);
        }
        public static Builder builder() { return new Builder(); }
        /**
         * Returns the registrations field.
         * @return the protocol field value
         */
        @Nullable public java.util.List<ServiceWorker.ServiceWorkerRegistration> registrations() {
            return list(value("registrations"), element0 -> ServiceWorker.ServiceWorkerRegistration.fromMap(objectMap(element0)));
        }
        public static final class Builder {
            private final Map<String, Object> values = new LinkedHashMap<>();
            /**
             * Sets the registrations field.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder registrations(@Nullable java.util.List<ServiceWorker.ServiceWorkerRegistration> value) {
                if (value == null) values.remove("registrations");
                else values.put("registrations", jsonValue(value));
                return this;
            }
            public WorkerRegistrationUpdatedEvent build() {
                if (!values.containsKey("registrations")) throw new IllegalStateException("Missing required CDP field: registrations");
                return new WorkerRegistrationUpdatedEvent(values);
            }
        }
    }
    /**
     * Payload of the ServiceWorker.workerVersionUpdated event.
     */
    public static final class WorkerVersionUpdatedEvent extends CdpObject {
        private WorkerVersionUpdatedEvent(Map<String, Object> values) { super(values); }
        @Nullable public static WorkerVersionUpdatedEvent fromMap(@Nullable Map<String, Object> values) {
            return values == null ? null : new WorkerVersionUpdatedEvent(values);
        }
        public static Builder builder() { return new Builder(); }
        /**
         * Returns the versions field.
         * @return the protocol field value
         */
        @Nullable public java.util.List<ServiceWorker.ServiceWorkerVersion> versions() {
            return list(value("versions"), element0 -> ServiceWorker.ServiceWorkerVersion.fromMap(objectMap(element0)));
        }
        public static final class Builder {
            private final Map<String, Object> values = new LinkedHashMap<>();
            /**
             * Sets the versions field.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder versions(@Nullable java.util.List<ServiceWorker.ServiceWorkerVersion> value) {
                if (value == null) values.remove("versions");
                else values.put("versions", jsonValue(value));
                return this;
            }
            public WorkerVersionUpdatedEvent build() {
                if (!values.containsKey("versions")) throw new IllegalStateException("Missing required CDP field: versions");
                return new WorkerVersionUpdatedEvent(values);
            }
        }
    }
    public static final class Client {
        private final CdpClient client;
        public Client(CdpClient client) { this.client = client; }
        /**
         * Invokes ServiceWorker.deliverPushMessage.
         * @param params command parameters
         * @return a stage completing with the command result
         */
        public CompletionStage<DeliverPushMessageResult> deliverPushMessage(DeliverPushMessageParams params) {
            return client.call("ServiceWorker.deliverPushMessage", params, DeliverPushMessageResult::fromMap);
        }
        /**
         * Invokes ServiceWorker.disable.
         * @return a stage completing with the command result
         */
        public CompletionStage<DisableResult> disable() {
            return client.call("ServiceWorker.disable", null, DisableResult::fromMap);
        }
        /**
         * Invokes ServiceWorker.dispatchSyncEvent.
         * @param params command parameters
         * @return a stage completing with the command result
         */
        public CompletionStage<DispatchSyncEventResult> dispatchSyncEvent(DispatchSyncEventParams params) {
            return client.call("ServiceWorker.dispatchSyncEvent", params, DispatchSyncEventResult::fromMap);
        }
        /**
         * Invokes ServiceWorker.dispatchPeriodicSyncEvent.
         * @param params command parameters
         * @return a stage completing with the command result
         */
        public CompletionStage<DispatchPeriodicSyncEventResult> dispatchPeriodicSyncEvent(DispatchPeriodicSyncEventParams params) {
            return client.call("ServiceWorker.dispatchPeriodicSyncEvent", params, DispatchPeriodicSyncEventResult::fromMap);
        }
        /**
         * Invokes ServiceWorker.enable.
         * @return a stage completing with the command result
         */
        public CompletionStage<EnableResult> enable() {
            return client.call("ServiceWorker.enable", null, EnableResult::fromMap);
        }
        /**
         * Invokes ServiceWorker.setForceUpdateOnPageLoad.
         * @param params command parameters
         * @return a stage completing with the command result
         */
        public CompletionStage<SetForceUpdateOnPageLoadResult> setForceUpdateOnPageLoad(SetForceUpdateOnPageLoadParams params) {
            return client.call("ServiceWorker.setForceUpdateOnPageLoad", params, SetForceUpdateOnPageLoadResult::fromMap);
        }
        /**
         * Invokes ServiceWorker.skipWaiting.
         * @param params command parameters
         * @return a stage completing with the command result
         */
        public CompletionStage<SkipWaitingResult> skipWaiting(SkipWaitingParams params) {
            return client.call("ServiceWorker.skipWaiting", params, SkipWaitingResult::fromMap);
        }
        /**
         * Invokes ServiceWorker.startWorker.
         * @param params command parameters
         * @return a stage completing with the command result
         */
        public CompletionStage<StartWorkerResult> startWorker(StartWorkerParams params) {
            return client.call("ServiceWorker.startWorker", params, StartWorkerResult::fromMap);
        }
        /**
         * Invokes ServiceWorker.stopAllWorkers.
         * @return a stage completing with the command result
         */
        public CompletionStage<StopAllWorkersResult> stopAllWorkers() {
            return client.call("ServiceWorker.stopAllWorkers", null, StopAllWorkersResult::fromMap);
        }
        /**
         * Invokes ServiceWorker.stopWorker.
         * @param params command parameters
         * @return a stage completing with the command result
         */
        public CompletionStage<StopWorkerResult> stopWorker(StopWorkerParams params) {
            return client.call("ServiceWorker.stopWorker", params, StopWorkerResult::fromMap);
        }
        /**
         * Invokes ServiceWorker.unregister.
         * @param params command parameters
         * @return a stage completing with the command result
         */
        public CompletionStage<UnregisterResult> unregister(UnregisterParams params) {
            return client.call("ServiceWorker.unregister", params, UnregisterResult::fromMap);
        }
        /**
         * Invokes ServiceWorker.updateRegistration.
         * @param params command parameters
         * @return a stage completing with the command result
         */
        public CompletionStage<UpdateRegistrationResult> updateRegistration(UpdateRegistrationParams params) {
            return client.call("ServiceWorker.updateRegistration", params, UpdateRegistrationResult::fromMap);
        }
        /**
         * Subscribes to ServiceWorker.workerErrorReported.
         * @param handler event callback
         * @return a removable subscription
         */
        public CdpSubscription onWorkerErrorReported(Consumer<WorkerErrorReportedEvent> handler) {
            return client.on("ServiceWorker.workerErrorReported", WorkerErrorReportedEvent::fromMap, handler);
        }
        /**
         * Subscribes to ServiceWorker.workerRegistrationUpdated.
         * @param handler event callback
         * @return a removable subscription
         */
        public CdpSubscription onWorkerRegistrationUpdated(Consumer<WorkerRegistrationUpdatedEvent> handler) {
            return client.on("ServiceWorker.workerRegistrationUpdated", WorkerRegistrationUpdatedEvent::fromMap, handler);
        }
        /**
         * Subscribes to ServiceWorker.workerVersionUpdated.
         * @param handler event callback
         * @return a removable subscription
         */
        public CdpSubscription onWorkerVersionUpdated(Consumer<WorkerVersionUpdatedEvent> handler) {
            return client.on("ServiceWorker.workerVersionUpdated", WorkerVersionUpdatedEvent::fromMap, handler);
        }
    }
}
