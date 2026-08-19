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
 * Chrome DevTools Protocol ServiceWorker domain.
 * <p><b>Experimental:</b> this part of CDP may change without notice.
 * @see <a href="https://chromium.googlesource.com/chromium/src/+/refs/tags/150.0.7871.213/third_party/blink/public/devtools_protocol/domains/ServiceWorker.pdl">Pinned protocol source</a>
 */
@Generated("mvn generate-sources -pl cef4j-platform -Dcef.version=150.0.18+gdb11278+chromium-150.0.7871.213")
@SuppressWarnings({"EscapedEntity", "InvalidParam", "JavaLangClash", "MissingSummary", "Unchecked", "UnusedMethod"})
public final class ServiceWorker {
    private ServiceWorker() {}
    /**
     * Tagged String wire value for RegistrationID.
     */
    public static final class RegistrationID implements CdpValue<String> {
        public final String value;
        public RegistrationID(@Nonnull String value) { this.value = java.util.Objects.requireNonNull(value); }
        @Nonnull public String value() { return value; }
        @Override public boolean equals(Object other) {
            if (this == other) return true;
            if (!(other instanceof RegistrationID)) return false;
            return value.equals(((RegistrationID) other).value);
        }
        @Override public int hashCode() { return value.hashCode(); }
        @Override public String toString() { return "RegistrationID(" + value + ")"; }
    }
    /**
     * ServiceWorker registration.
     */
    public static final class ServiceWorkerRegistration extends CdpObject {
        public ServiceWorkerRegistration() {}
        private ServiceWorkerRegistration(Map<String, Object> values) { super(values); }
        public static ServiceWorkerRegistration fromMap(Map<String, Object> values) {
            return new ServiceWorkerRegistration(values);
        }
        /**
         * Returns the registrationId field.
         * @return the protocol field value
         */
        public ServiceWorker.RegistrationID registrationId() {
            return new ServiceWorker.RegistrationID((String) require("registrationId"));
        }
        /**
         * Returns the scopeURL field.
         * @return the protocol field value
         */
        public String scopeURL() {
            return (String) require("scopeURL");
        }
        /**
         * Returns the isDeleted field.
         * @return the protocol field value
         */
        public boolean isDeleted() {
            return (Boolean) require("isDeleted");
        }
        /**
         * Sets the registrationId field.
         * @param registrationId field value
         * @return this model
         */
        public ServiceWorkerRegistration registrationId(ServiceWorker.RegistrationID registrationId) {
            set("registrationId", registrationId);
            return this;
        }
        /**
         * Sets the scopeURL field.
         * @param scopeURL field value
         * @return this model
         */
        public ServiceWorkerRegistration scopeURL(String scopeURL) {
            set("scopeURL", scopeURL);
            return this;
        }
        /**
         * Sets the isDeleted field.
         * @param isDeleted field value
         * @return this model
         */
        public ServiceWorkerRegistration isDeleted(boolean isDeleted) {
            set("isDeleted", isDeleted);
            return this;
        }
    }
    /**
     * Wire values for ServiceWorkerVersionRunningStatus.
     */
    public enum ServiceWorkerVersionRunningStatus implements CdpValue<String> {
        STOPPED("stopped"),
        STARTING("starting"),
        RUNNING("running"),
        STOPPING("stopping");
        public final String value;
        ServiceWorkerVersionRunningStatus(String value) { this.value = value; }
        @Nonnull public String value() { return value; }
        public static ServiceWorkerVersionRunningStatus of(@Nonnull String value) {
            for (ServiceWorkerVersionRunningStatus constant : values()) {
                if (constant.value.equals(value)) return constant;
            }
            throw new IllegalArgumentException("Unknown ServiceWorkerVersionRunningStatus value: " + value);
        }
    }
    /**
     * Wire values for ServiceWorkerVersionStatus.
     */
    public enum ServiceWorkerVersionStatus implements CdpValue<String> {
        NEW("new"),
        INSTALLING("installing"),
        INSTALLED("installed"),
        ACTIVATING("activating"),
        ACTIVATED("activated"),
        REDUNDANT("redundant");
        public final String value;
        ServiceWorkerVersionStatus(String value) { this.value = value; }
        @Nonnull public String value() { return value; }
        public static ServiceWorkerVersionStatus of(@Nonnull String value) {
            for (ServiceWorkerVersionStatus constant : values()) {
                if (constant.value.equals(value)) return constant;
            }
            throw new IllegalArgumentException("Unknown ServiceWorkerVersionStatus value: " + value);
        }
    }
    /**
     * ServiceWorker version.
     */
    public static final class ServiceWorkerVersion extends CdpObject {
        public ServiceWorkerVersion() {}
        private ServiceWorkerVersion(Map<String, Object> values) { super(values); }
        public static ServiceWorkerVersion fromMap(Map<String, Object> values) {
            return new ServiceWorkerVersion(values);
        }
        /**
         * Returns the versionId field.
         * @return the protocol field value
         */
        public String versionId() {
            return (String) require("versionId");
        }
        /**
         * Returns the registrationId field.
         * @return the protocol field value
         */
        public ServiceWorker.RegistrationID registrationId() {
            return new ServiceWorker.RegistrationID((String) require("registrationId"));
        }
        /**
         * Returns the scriptURL field.
         * @return the protocol field value
         */
        public String scriptURL() {
            return (String) require("scriptURL");
        }
        /**
         * Returns the runningStatus field.
         * @return the protocol field value
         */
        public ServiceWorker.ServiceWorkerVersionRunningStatus runningStatus() {
            return ServiceWorker.ServiceWorkerVersionRunningStatus.of((String) require("runningStatus"));
        }
        /**
         * Returns the status field.
         * @return the protocol field value
         */
        public ServiceWorker.ServiceWorkerVersionStatus status() {
            return ServiceWorker.ServiceWorkerVersionStatus.of((String) require("status"));
        }
        /**
         * The Last-Modified header value of the main script.
         * @return the protocol field value, empty when absent
         */
        public OptionalDouble scriptLastModified() {
            Double value = CdpObject.numberAsDouble(raw("scriptLastModified"));
            return value == null ? OptionalDouble.empty() : OptionalDouble.of(value);
        }
        /**
         * The time at which the response headers of the main script were received from the server. For cached script it is the last time the cache entry was validated.
         * @return the protocol field value, empty when absent
         */
        public OptionalDouble scriptResponseTime() {
            Double value = CdpObject.numberAsDouble(raw("scriptResponseTime"));
            return value == null ? OptionalDouble.empty() : OptionalDouble.of(value);
        }
        /**
         * Returns the controlledClients field.
         * @return the protocol field value, empty when absent
         */
        public Optional<java.util.List<Target.TargetID>> controlledClients() {
            return Optional.ofNullable(list(raw("controlledClients"), element0 -> new Target.TargetID((String) element0)));
        }
        /**
         * Returns the targetId field.
         * @return the protocol field value, empty when absent
         */
        public Optional<Target.TargetID> targetId() {
            return Optional.ofNullable(raw("targetId") == null ? null : new Target.TargetID((String) raw("targetId")));
        }
        /**
         * Returns the routerRules field.
         * @return the protocol field value, empty when absent
         */
        public Optional<String> routerRules() {
            return Optional.ofNullable((String) raw("routerRules"));
        }
        /**
         * Sets the versionId field.
         * @param versionId field value
         * @return this model
         */
        public ServiceWorkerVersion versionId(String versionId) {
            set("versionId", versionId);
            return this;
        }
        /**
         * Sets the registrationId field.
         * @param registrationId field value
         * @return this model
         */
        public ServiceWorkerVersion registrationId(ServiceWorker.RegistrationID registrationId) {
            set("registrationId", registrationId);
            return this;
        }
        /**
         * Sets the scriptURL field.
         * @param scriptURL field value
         * @return this model
         */
        public ServiceWorkerVersion scriptURL(String scriptURL) {
            set("scriptURL", scriptURL);
            return this;
        }
        /**
         * Sets the runningStatus field.
         * @param runningStatus field value
         * @return this model
         */
        public ServiceWorkerVersion runningStatus(ServiceWorker.ServiceWorkerVersionRunningStatus runningStatus) {
            set("runningStatus", runningStatus);
            return this;
        }
        /**
         * Sets the status field.
         * @param status field value
         * @return this model
         */
        public ServiceWorkerVersion status(ServiceWorker.ServiceWorkerVersionStatus status) {
            set("status", status);
            return this;
        }
        /**
         * The Last-Modified header value of the main script.
         * @param scriptLastModified field value; empty omits the value
         * @return this model
         */
        public ServiceWorkerVersion scriptLastModified(OptionalDouble scriptLastModified) {
            set("scriptLastModified", scriptLastModified.isPresent() ? scriptLastModified.getAsDouble() : null);
            return this;
        }
        /**
         * The Last-Modified header value of the main script.
         * @param scriptLastModified field value; null removes the value
         * @return this model
         */
        public ServiceWorkerVersion scriptLastModified(Double scriptLastModified) {
            set("scriptLastModified", scriptLastModified);
            return this;
        }
        /**
         * The time at which the response headers of the main script were received from the server. For cached script it is the last time the cache entry was validated.
         * @param scriptResponseTime field value; empty omits the value
         * @return this model
         */
        public ServiceWorkerVersion scriptResponseTime(OptionalDouble scriptResponseTime) {
            set("scriptResponseTime", scriptResponseTime.isPresent() ? scriptResponseTime.getAsDouble() : null);
            return this;
        }
        /**
         * The time at which the response headers of the main script were received from the server. For cached script it is the last time the cache entry was validated.
         * @param scriptResponseTime field value; null removes the value
         * @return this model
         */
        public ServiceWorkerVersion scriptResponseTime(Double scriptResponseTime) {
            set("scriptResponseTime", scriptResponseTime);
            return this;
        }
        /**
         * Sets the controlledClients field.
         * @param controlledClients field value; empty omits the value
         * @return this model
         */
        public ServiceWorkerVersion controlledClients(Optional<java.util.List<Target.TargetID>> controlledClients) {
            set("controlledClients", controlledClients.orElse(null));
            return this;
        }
        /**
         * Sets the controlledClients field.
         * @param controlledClients field value; null removes the value
         * @return this model
         */
        public ServiceWorkerVersion controlledClients(java.util.List<Target.TargetID> controlledClients) {
            set("controlledClients", controlledClients);
            return this;
        }
        /**
         * Sets the targetId field.
         * @param targetId field value; empty omits the value
         * @return this model
         */
        public ServiceWorkerVersion targetId(Optional<Target.TargetID> targetId) {
            set("targetId", targetId.orElse(null));
            return this;
        }
        /**
         * Sets the targetId field.
         * @param targetId field value; null removes the value
         * @return this model
         */
        public ServiceWorkerVersion targetId(Target.TargetID targetId) {
            set("targetId", targetId);
            return this;
        }
        /**
         * Sets the routerRules field.
         * @param routerRules field value; empty omits the value
         * @return this model
         */
        public ServiceWorkerVersion routerRules(Optional<String> routerRules) {
            set("routerRules", routerRules.orElse(null));
            return this;
        }
        /**
         * Sets the routerRules field.
         * @param routerRules field value; null removes the value
         * @return this model
         */
        public ServiceWorkerVersion routerRules(String routerRules) {
            set("routerRules", routerRules);
            return this;
        }
    }
    /**
     * ServiceWorker error message.
     */
    public static final class ServiceWorkerErrorMessage extends CdpObject {
        public ServiceWorkerErrorMessage() {}
        private ServiceWorkerErrorMessage(Map<String, Object> values) { super(values); }
        public static ServiceWorkerErrorMessage fromMap(Map<String, Object> values) {
            return new ServiceWorkerErrorMessage(values);
        }
        /**
         * Returns the errorMessage field.
         * @return the protocol field value
         */
        public String errorMessage() {
            return (String) require("errorMessage");
        }
        /**
         * Returns the registrationId field.
         * @return the protocol field value
         */
        public ServiceWorker.RegistrationID registrationId() {
            return new ServiceWorker.RegistrationID((String) require("registrationId"));
        }
        /**
         * Returns the versionId field.
         * @return the protocol field value
         */
        public String versionId() {
            return (String) require("versionId");
        }
        /**
         * Returns the sourceURL field.
         * @return the protocol field value
         */
        public String sourceURL() {
            return (String) require("sourceURL");
        }
        /**
         * Returns the lineNumber field.
         * @return the protocol field value
         */
        public long lineNumber() {
            return ((Number) require("lineNumber")).longValue();
        }
        /**
         * Returns the columnNumber field.
         * @return the protocol field value
         */
        public long columnNumber() {
            return ((Number) require("columnNumber")).longValue();
        }
        /**
         * Sets the errorMessage field.
         * @param errorMessage field value
         * @return this model
         */
        public ServiceWorkerErrorMessage errorMessage(String errorMessage) {
            set("errorMessage", errorMessage);
            return this;
        }
        /**
         * Sets the registrationId field.
         * @param registrationId field value
         * @return this model
         */
        public ServiceWorkerErrorMessage registrationId(ServiceWorker.RegistrationID registrationId) {
            set("registrationId", registrationId);
            return this;
        }
        /**
         * Sets the versionId field.
         * @param versionId field value
         * @return this model
         */
        public ServiceWorkerErrorMessage versionId(String versionId) {
            set("versionId", versionId);
            return this;
        }
        /**
         * Sets the sourceURL field.
         * @param sourceURL field value
         * @return this model
         */
        public ServiceWorkerErrorMessage sourceURL(String sourceURL) {
            set("sourceURL", sourceURL);
            return this;
        }
        /**
         * Sets the lineNumber field.
         * @param lineNumber field value
         * @return this model
         */
        public ServiceWorkerErrorMessage lineNumber(long lineNumber) {
            set("lineNumber", lineNumber);
            return this;
        }
        /**
         * Sets the columnNumber field.
         * @param columnNumber field value
         * @return this model
         */
        public ServiceWorkerErrorMessage columnNumber(long columnNumber) {
            set("columnNumber", columnNumber);
            return this;
        }
    }
    /**
     * Request parameters for ServiceWorker.deliverPushMessage.
     */
    public static final class DeliverPushMessageRequest extends CdpObject {
        public DeliverPushMessageRequest() {}
        /**
         * Creates a new DeliverPushMessageRequest with all required parameters.
         * @param origin protocol value
         * @param registrationId protocol value
         * @param data protocol value
         */
        public DeliverPushMessageRequest(String origin, ServiceWorker.RegistrationID registrationId, String data) {
            set("origin", origin);
            set("registrationId", registrationId);
            set("data", data);
        }
        public static DeliverPushMessageRequest fromMap(Map<String, Object> values) {
            DeliverPushMessageRequest instance_ = new DeliverPushMessageRequest();
            if (values != null) instance_.values.putAll(values);
            return instance_;
        }
        /**
         * Returns the origin field.
         * @return the protocol field value
         */
        public String origin() {
            return (String) require("origin");
        }
        /**
         * Returns the registrationId field.
         * @return the protocol field value
         */
        public ServiceWorker.RegistrationID registrationId() {
            return new ServiceWorker.RegistrationID((String) require("registrationId"));
        }
        /**
         * Returns the data field.
         * @return the protocol field value
         */
        public String data() {
            return (String) require("data");
        }
        /**
         * Sets the origin field.
         * @param origin field value
         * @return this model
         */
        public DeliverPushMessageRequest origin(String origin) {
            set("origin", origin);
            return this;
        }
        /**
         * Sets the registrationId field.
         * @param registrationId field value
         * @return this model
         */
        public DeliverPushMessageRequest registrationId(ServiceWorker.RegistrationID registrationId) {
            set("registrationId", registrationId);
            return this;
        }
        /**
         * Sets the data field.
         * @param data field value
         * @return this model
         */
        public DeliverPushMessageRequest data(String data) {
            set("data", data);
            return this;
        }
    }
    /**
     * Request parameters for ServiceWorker.dispatchSyncEvent.
     */
    public static final class DispatchSyncEventRequest extends CdpObject {
        public DispatchSyncEventRequest() {}
        /**
         * Creates a new DispatchSyncEventRequest with all required parameters.
         * @param origin protocol value
         * @param registrationId protocol value
         * @param tag protocol value
         * @param lastChance protocol value
         */
        public DispatchSyncEventRequest(String origin, ServiceWorker.RegistrationID registrationId, String tag, boolean lastChance) {
            set("origin", origin);
            set("registrationId", registrationId);
            set("tag", tag);
            set("lastChance", lastChance);
        }
        public static DispatchSyncEventRequest fromMap(Map<String, Object> values) {
            DispatchSyncEventRequest instance_ = new DispatchSyncEventRequest();
            if (values != null) instance_.values.putAll(values);
            return instance_;
        }
        /**
         * Returns the origin field.
         * @return the protocol field value
         */
        public String origin() {
            return (String) require("origin");
        }
        /**
         * Returns the registrationId field.
         * @return the protocol field value
         */
        public ServiceWorker.RegistrationID registrationId() {
            return new ServiceWorker.RegistrationID((String) require("registrationId"));
        }
        /**
         * Returns the tag field.
         * @return the protocol field value
         */
        public String tag() {
            return (String) require("tag");
        }
        /**
         * Returns the lastChance field.
         * @return the protocol field value
         */
        public boolean lastChance() {
            return (Boolean) require("lastChance");
        }
        /**
         * Sets the origin field.
         * @param origin field value
         * @return this model
         */
        public DispatchSyncEventRequest origin(String origin) {
            set("origin", origin);
            return this;
        }
        /**
         * Sets the registrationId field.
         * @param registrationId field value
         * @return this model
         */
        public DispatchSyncEventRequest registrationId(ServiceWorker.RegistrationID registrationId) {
            set("registrationId", registrationId);
            return this;
        }
        /**
         * Sets the tag field.
         * @param tag field value
         * @return this model
         */
        public DispatchSyncEventRequest tag(String tag) {
            set("tag", tag);
            return this;
        }
        /**
         * Sets the lastChance field.
         * @param lastChance field value
         * @return this model
         */
        public DispatchSyncEventRequest lastChance(boolean lastChance) {
            set("lastChance", lastChance);
            return this;
        }
    }
    /**
     * Request parameters for ServiceWorker.dispatchPeriodicSyncEvent.
     */
    public static final class DispatchPeriodicSyncEventRequest extends CdpObject {
        public DispatchPeriodicSyncEventRequest() {}
        /**
         * Creates a new DispatchPeriodicSyncEventRequest with all required parameters.
         * @param origin protocol value
         * @param registrationId protocol value
         * @param tag protocol value
         */
        public DispatchPeriodicSyncEventRequest(String origin, ServiceWorker.RegistrationID registrationId, String tag) {
            set("origin", origin);
            set("registrationId", registrationId);
            set("tag", tag);
        }
        public static DispatchPeriodicSyncEventRequest fromMap(Map<String, Object> values) {
            DispatchPeriodicSyncEventRequest instance_ = new DispatchPeriodicSyncEventRequest();
            if (values != null) instance_.values.putAll(values);
            return instance_;
        }
        /**
         * Returns the origin field.
         * @return the protocol field value
         */
        public String origin() {
            return (String) require("origin");
        }
        /**
         * Returns the registrationId field.
         * @return the protocol field value
         */
        public ServiceWorker.RegistrationID registrationId() {
            return new ServiceWorker.RegistrationID((String) require("registrationId"));
        }
        /**
         * Returns the tag field.
         * @return the protocol field value
         */
        public String tag() {
            return (String) require("tag");
        }
        /**
         * Sets the origin field.
         * @param origin field value
         * @return this model
         */
        public DispatchPeriodicSyncEventRequest origin(String origin) {
            set("origin", origin);
            return this;
        }
        /**
         * Sets the registrationId field.
         * @param registrationId field value
         * @return this model
         */
        public DispatchPeriodicSyncEventRequest registrationId(ServiceWorker.RegistrationID registrationId) {
            set("registrationId", registrationId);
            return this;
        }
        /**
         * Sets the tag field.
         * @param tag field value
         * @return this model
         */
        public DispatchPeriodicSyncEventRequest tag(String tag) {
            set("tag", tag);
            return this;
        }
    }
    /**
     * Request parameters for ServiceWorker.setForceUpdateOnPageLoad.
     */
    public static final class SetForceUpdateOnPageLoadRequest extends CdpObject {
        public SetForceUpdateOnPageLoadRequest() {}
        /**
         * Creates a new SetForceUpdateOnPageLoadRequest with all required parameters.
         * @param forceUpdateOnPageLoad protocol value
         */
        public SetForceUpdateOnPageLoadRequest(boolean forceUpdateOnPageLoad) {
            set("forceUpdateOnPageLoad", forceUpdateOnPageLoad);
        }
        public static SetForceUpdateOnPageLoadRequest fromMap(Map<String, Object> values) {
            SetForceUpdateOnPageLoadRequest instance_ = new SetForceUpdateOnPageLoadRequest();
            if (values != null) instance_.values.putAll(values);
            return instance_;
        }
        /**
         * Returns the forceUpdateOnPageLoad field.
         * @return the protocol field value
         */
        public boolean forceUpdateOnPageLoad() {
            return (Boolean) require("forceUpdateOnPageLoad");
        }
        /**
         * Sets the forceUpdateOnPageLoad field.
         * @param forceUpdateOnPageLoad field value
         * @return this model
         */
        public SetForceUpdateOnPageLoadRequest forceUpdateOnPageLoad(boolean forceUpdateOnPageLoad) {
            set("forceUpdateOnPageLoad", forceUpdateOnPageLoad);
            return this;
        }
    }
    /**
     * Request parameters for ServiceWorker.skipWaiting.
     */
    public static final class SkipWaitingRequest extends CdpObject {
        public SkipWaitingRequest() {}
        /**
         * Creates a new SkipWaitingRequest with all required parameters.
         * @param scopeURL protocol value
         */
        public SkipWaitingRequest(String scopeURL) {
            set("scopeURL", scopeURL);
        }
        public static SkipWaitingRequest fromMap(Map<String, Object> values) {
            SkipWaitingRequest instance_ = new SkipWaitingRequest();
            if (values != null) instance_.values.putAll(values);
            return instance_;
        }
        /**
         * Returns the scopeURL field.
         * @return the protocol field value
         */
        public String scopeURL() {
            return (String) require("scopeURL");
        }
        /**
         * Sets the scopeURL field.
         * @param scopeURL field value
         * @return this model
         */
        public SkipWaitingRequest scopeURL(String scopeURL) {
            set("scopeURL", scopeURL);
            return this;
        }
    }
    /**
     * Request parameters for ServiceWorker.startWorker.
     */
    public static final class StartWorkerRequest extends CdpObject {
        public StartWorkerRequest() {}
        /**
         * Creates a new StartWorkerRequest with all required parameters.
         * @param scopeURL protocol value
         */
        public StartWorkerRequest(String scopeURL) {
            set("scopeURL", scopeURL);
        }
        public static StartWorkerRequest fromMap(Map<String, Object> values) {
            StartWorkerRequest instance_ = new StartWorkerRequest();
            if (values != null) instance_.values.putAll(values);
            return instance_;
        }
        /**
         * Returns the scopeURL field.
         * @return the protocol field value
         */
        public String scopeURL() {
            return (String) require("scopeURL");
        }
        /**
         * Sets the scopeURL field.
         * @param scopeURL field value
         * @return this model
         */
        public StartWorkerRequest scopeURL(String scopeURL) {
            set("scopeURL", scopeURL);
            return this;
        }
    }
    /**
     * Request parameters for ServiceWorker.stopWorker.
     */
    public static final class StopWorkerRequest extends CdpObject {
        public StopWorkerRequest() {}
        /**
         * Creates a new StopWorkerRequest with all required parameters.
         * @param versionId protocol value
         */
        public StopWorkerRequest(String versionId) {
            set("versionId", versionId);
        }
        public static StopWorkerRequest fromMap(Map<String, Object> values) {
            StopWorkerRequest instance_ = new StopWorkerRequest();
            if (values != null) instance_.values.putAll(values);
            return instance_;
        }
        /**
         * Returns the versionId field.
         * @return the protocol field value
         */
        public String versionId() {
            return (String) require("versionId");
        }
        /**
         * Sets the versionId field.
         * @param versionId field value
         * @return this model
         */
        public StopWorkerRequest versionId(String versionId) {
            set("versionId", versionId);
            return this;
        }
    }
    /**
     * Request parameters for ServiceWorker.unregister.
     */
    public static final class UnregisterRequest extends CdpObject {
        public UnregisterRequest() {}
        /**
         * Creates a new UnregisterRequest with all required parameters.
         * @param scopeURL protocol value
         */
        public UnregisterRequest(String scopeURL) {
            set("scopeURL", scopeURL);
        }
        public static UnregisterRequest fromMap(Map<String, Object> values) {
            UnregisterRequest instance_ = new UnregisterRequest();
            if (values != null) instance_.values.putAll(values);
            return instance_;
        }
        /**
         * Returns the scopeURL field.
         * @return the protocol field value
         */
        public String scopeURL() {
            return (String) require("scopeURL");
        }
        /**
         * Sets the scopeURL field.
         * @param scopeURL field value
         * @return this model
         */
        public UnregisterRequest scopeURL(String scopeURL) {
            set("scopeURL", scopeURL);
            return this;
        }
    }
    /**
     * Request parameters for ServiceWorker.updateRegistration.
     */
    public static final class UpdateRegistrationRequest extends CdpObject {
        public UpdateRegistrationRequest() {}
        /**
         * Creates a new UpdateRegistrationRequest with all required parameters.
         * @param scopeURL protocol value
         */
        public UpdateRegistrationRequest(String scopeURL) {
            set("scopeURL", scopeURL);
        }
        public static UpdateRegistrationRequest fromMap(Map<String, Object> values) {
            UpdateRegistrationRequest instance_ = new UpdateRegistrationRequest();
            if (values != null) instance_.values.putAll(values);
            return instance_;
        }
        /**
         * Returns the scopeURL field.
         * @return the protocol field value
         */
        public String scopeURL() {
            return (String) require("scopeURL");
        }
        /**
         * Sets the scopeURL field.
         * @param scopeURL field value
         * @return this model
         */
        public UpdateRegistrationRequest scopeURL(String scopeURL) {
            set("scopeURL", scopeURL);
            return this;
        }
    }
    /**
     * Payload of the ServiceWorker.workerErrorReported event.
     */
    public static final class WorkerErrorReportedEvent extends CdpObject {
        public WorkerErrorReportedEvent() {}
        private WorkerErrorReportedEvent(Map<String, Object> values) { super(values); }
        public static WorkerErrorReportedEvent fromMap(Map<String, Object> values) {
            return new WorkerErrorReportedEvent(values);
        }
        /**
         * Returns the errorMessage field.
         * @return the protocol field value
         */
        public ServiceWorker.ServiceWorkerErrorMessage errorMessage() {
            return java.util.Objects.requireNonNull(ServiceWorker.ServiceWorkerErrorMessage.fromMap(java.util.Objects.requireNonNull(CdpObject.objectMap(require("errorMessage")))));
        }
        /**
         * Sets the errorMessage field.
         * @param errorMessage field value
         * @return this model
         */
        public WorkerErrorReportedEvent errorMessage(ServiceWorker.ServiceWorkerErrorMessage errorMessage) {
            set("errorMessage", errorMessage);
            return this;
        }
    }
    /**
     * Payload of the ServiceWorker.workerRegistrationUpdated event.
     */
    public static final class WorkerRegistrationUpdatedEvent extends CdpObject {
        public WorkerRegistrationUpdatedEvent() {}
        private WorkerRegistrationUpdatedEvent(Map<String, Object> values) { super(values); }
        public static WorkerRegistrationUpdatedEvent fromMap(Map<String, Object> values) {
            return new WorkerRegistrationUpdatedEvent(values);
        }
        /**
         * Returns the registrations field.
         * @return the protocol field value
         */
        public java.util.List<ServiceWorker.ServiceWorkerRegistration> registrations() {
            return CdpObject.requireList(require("registrations"), element0 -> java.util.Objects.requireNonNull(ServiceWorker.ServiceWorkerRegistration.fromMap(java.util.Objects.requireNonNull(CdpObject.objectMap(element0)))));
        }
        /**
         * Sets the registrations field.
         * @param registrations field value
         * @return this model
         */
        public WorkerRegistrationUpdatedEvent registrations(java.util.List<ServiceWorker.ServiceWorkerRegistration> registrations) {
            set("registrations", registrations);
            return this;
        }
    }
    /**
     * Payload of the ServiceWorker.workerVersionUpdated event.
     */
    public static final class WorkerVersionUpdatedEvent extends CdpObject {
        public WorkerVersionUpdatedEvent() {}
        private WorkerVersionUpdatedEvent(Map<String, Object> values) { super(values); }
        public static WorkerVersionUpdatedEvent fromMap(Map<String, Object> values) {
            return new WorkerVersionUpdatedEvent(values);
        }
        /**
         * Returns the versions field.
         * @return the protocol field value
         */
        public java.util.List<ServiceWorker.ServiceWorkerVersion> versions() {
            return CdpObject.requireList(require("versions"), element0 -> java.util.Objects.requireNonNull(ServiceWorker.ServiceWorkerVersion.fromMap(java.util.Objects.requireNonNull(CdpObject.objectMap(element0)))));
        }
        /**
         * Sets the versions field.
         * @param versions field value
         * @return this model
         */
        public WorkerVersionUpdatedEvent versions(java.util.List<ServiceWorker.ServiceWorkerVersion> versions) {
            set("versions", versions);
            return this;
        }
    }
    public static final class Client {
        private final CdpClient client;
        public Client(CdpClient client) { this.client = client; }
        /**
         * Invokes ServiceWorker.deliverPushMessage.
         * @param origin protocol value
         * @param registrationId protocol value
         * @param data protocol value
         * @return a stage completing when the command completes
         */
        public CompletionStage<Void> deliverPushMessage(String origin, ServiceWorker.RegistrationID registrationId, String data) {
            Map<String, Object> params = new LinkedHashMap<>();
            params.put("origin", CdpObject.json(origin));
            params.put("registrationId", CdpObject.json(registrationId));
            params.put("data", CdpObject.json(data));
            return client.call("ServiceWorker.deliverPushMessage", params, result_ -> null);
        }
        /**
         * Invokes ServiceWorker.deliverPushMessage with a request object.
         * @param request request parameters
         * @return a stage completing when the command completes
         */
        public CompletionStage<Void> deliverPushMessage(DeliverPushMessageRequest request) {
            return client.call("ServiceWorker.deliverPushMessage", request == null ? null : request.toMap(), result_ -> null);
        }
        /**
         * Invokes ServiceWorker.disable.
         * @return a stage completing when the command completes
         */
        public CompletionStage<Void> disable() {
            return client.call("ServiceWorker.disable", null, result_ -> null);
        }
        /**
         * Invokes ServiceWorker.dispatchSyncEvent.
         * @param origin protocol value
         * @param registrationId protocol value
         * @param tag protocol value
         * @param lastChance protocol value
         * @return a stage completing when the command completes
         */
        public CompletionStage<Void> dispatchSyncEvent(String origin, ServiceWorker.RegistrationID registrationId, String tag, boolean lastChance) {
            Map<String, Object> params = new LinkedHashMap<>();
            params.put("origin", CdpObject.json(origin));
            params.put("registrationId", CdpObject.json(registrationId));
            params.put("tag", CdpObject.json(tag));
            params.put("lastChance", CdpObject.json(lastChance));
            return client.call("ServiceWorker.dispatchSyncEvent", params, result_ -> null);
        }
        /**
         * Invokes ServiceWorker.dispatchSyncEvent with a request object.
         * @param request request parameters
         * @return a stage completing when the command completes
         */
        public CompletionStage<Void> dispatchSyncEvent(DispatchSyncEventRequest request) {
            return client.call("ServiceWorker.dispatchSyncEvent", request == null ? null : request.toMap(), result_ -> null);
        }
        /**
         * Invokes ServiceWorker.dispatchPeriodicSyncEvent.
         * @param origin protocol value
         * @param registrationId protocol value
         * @param tag protocol value
         * @return a stage completing when the command completes
         */
        public CompletionStage<Void> dispatchPeriodicSyncEvent(String origin, ServiceWorker.RegistrationID registrationId, String tag) {
            Map<String, Object> params = new LinkedHashMap<>();
            params.put("origin", CdpObject.json(origin));
            params.put("registrationId", CdpObject.json(registrationId));
            params.put("tag", CdpObject.json(tag));
            return client.call("ServiceWorker.dispatchPeriodicSyncEvent", params, result_ -> null);
        }
        /**
         * Invokes ServiceWorker.dispatchPeriodicSyncEvent with a request object.
         * @param request request parameters
         * @return a stage completing when the command completes
         */
        public CompletionStage<Void> dispatchPeriodicSyncEvent(DispatchPeriodicSyncEventRequest request) {
            return client.call("ServiceWorker.dispatchPeriodicSyncEvent", request == null ? null : request.toMap(), result_ -> null);
        }
        /**
         * Invokes ServiceWorker.enable.
         * @return a stage completing when the command completes
         */
        public CompletionStage<Void> enable() {
            return client.call("ServiceWorker.enable", null, result_ -> null);
        }
        /**
         * Invokes ServiceWorker.setForceUpdateOnPageLoad.
         * @param forceUpdateOnPageLoad protocol value
         * @return a stage completing when the command completes
         */
        public CompletionStage<Void> setForceUpdateOnPageLoad(boolean forceUpdateOnPageLoad) {
            Map<String, Object> params = new LinkedHashMap<>();
            params.put("forceUpdateOnPageLoad", CdpObject.json(forceUpdateOnPageLoad));
            return client.call("ServiceWorker.setForceUpdateOnPageLoad", params, result_ -> null);
        }
        /**
         * Invokes ServiceWorker.setForceUpdateOnPageLoad with a request object.
         * @param request request parameters
         * @return a stage completing when the command completes
         */
        public CompletionStage<Void> setForceUpdateOnPageLoad(SetForceUpdateOnPageLoadRequest request) {
            return client.call("ServiceWorker.setForceUpdateOnPageLoad", request == null ? null : request.toMap(), result_ -> null);
        }
        /**
         * Invokes ServiceWorker.skipWaiting.
         * @param scopeURL protocol value
         * @return a stage completing when the command completes
         */
        public CompletionStage<Void> skipWaiting(String scopeURL) {
            Map<String, Object> params = new LinkedHashMap<>();
            params.put("scopeURL", CdpObject.json(scopeURL));
            return client.call("ServiceWorker.skipWaiting", params, result_ -> null);
        }
        /**
         * Invokes ServiceWorker.skipWaiting with a request object.
         * @param request request parameters
         * @return a stage completing when the command completes
         */
        public CompletionStage<Void> skipWaiting(SkipWaitingRequest request) {
            return client.call("ServiceWorker.skipWaiting", request == null ? null : request.toMap(), result_ -> null);
        }
        /**
         * Invokes ServiceWorker.startWorker.
         * @param scopeURL protocol value
         * @return a stage completing when the command completes
         */
        public CompletionStage<Void> startWorker(String scopeURL) {
            Map<String, Object> params = new LinkedHashMap<>();
            params.put("scopeURL", CdpObject.json(scopeURL));
            return client.call("ServiceWorker.startWorker", params, result_ -> null);
        }
        /**
         * Invokes ServiceWorker.startWorker with a request object.
         * @param request request parameters
         * @return a stage completing when the command completes
         */
        public CompletionStage<Void> startWorker(StartWorkerRequest request) {
            return client.call("ServiceWorker.startWorker", request == null ? null : request.toMap(), result_ -> null);
        }
        /**
         * Invokes ServiceWorker.stopAllWorkers.
         * @return a stage completing when the command completes
         */
        public CompletionStage<Void> stopAllWorkers() {
            return client.call("ServiceWorker.stopAllWorkers", null, result_ -> null);
        }
        /**
         * Invokes ServiceWorker.stopWorker.
         * @param versionId protocol value
         * @return a stage completing when the command completes
         */
        public CompletionStage<Void> stopWorker(String versionId) {
            Map<String, Object> params = new LinkedHashMap<>();
            params.put("versionId", CdpObject.json(versionId));
            return client.call("ServiceWorker.stopWorker", params, result_ -> null);
        }
        /**
         * Invokes ServiceWorker.stopWorker with a request object.
         * @param request request parameters
         * @return a stage completing when the command completes
         */
        public CompletionStage<Void> stopWorker(StopWorkerRequest request) {
            return client.call("ServiceWorker.stopWorker", request == null ? null : request.toMap(), result_ -> null);
        }
        /**
         * Invokes ServiceWorker.unregister.
         * @param scopeURL protocol value
         * @return a stage completing when the command completes
         */
        public CompletionStage<Void> unregister(String scopeURL) {
            Map<String, Object> params = new LinkedHashMap<>();
            params.put("scopeURL", CdpObject.json(scopeURL));
            return client.call("ServiceWorker.unregister", params, result_ -> null);
        }
        /**
         * Invokes ServiceWorker.unregister with a request object.
         * @param request request parameters
         * @return a stage completing when the command completes
         */
        public CompletionStage<Void> unregister(UnregisterRequest request) {
            return client.call("ServiceWorker.unregister", request == null ? null : request.toMap(), result_ -> null);
        }
        /**
         * Invokes ServiceWorker.updateRegistration.
         * @param scopeURL protocol value
         * @return a stage completing when the command completes
         */
        public CompletionStage<Void> updateRegistration(String scopeURL) {
            Map<String, Object> params = new LinkedHashMap<>();
            params.put("scopeURL", CdpObject.json(scopeURL));
            return client.call("ServiceWorker.updateRegistration", params, result_ -> null);
        }
        /**
         * Invokes ServiceWorker.updateRegistration with a request object.
         * @param request request parameters
         * @return a stage completing when the command completes
         */
        public CompletionStage<Void> updateRegistration(UpdateRegistrationRequest request) {
            return client.call("ServiceWorker.updateRegistration", request == null ? null : request.toMap(), result_ -> null);
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
