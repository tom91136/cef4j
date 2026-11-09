// GENERATED - do not edit. Regenerate via: ./mvnw generate-sources -pl cef4j-cdp -Dcef.version=150.0.18+gdb11278+chromium-150.0.7871.213
package net.kurobako.cef4j.cdp.generated;

import java.util.Map;
import java.util.Optional;
import java.util.OptionalDouble;
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
 * Chrome DevTools Protocol ServiceWorker domain.
 * <p><b>Experimental:</b> this part of CDP may change without notice.
 * @see <a href="https://chromium.googlesource.com/chromium/src/+/refs/tags/150.0.7871.213/third_party/blink/public/devtools_protocol/domains/ServiceWorker.pdl">Pinned protocol source</a>
 */
@Generated("./mvnw generate-sources -pl cef4j-cdp -Dcef.version=150.0.18+gdb11278+chromium-150.0.7871.213")
@SuppressWarnings({"EscapedEntity", "InvalidParam", "JavaLangClash", "MissingSummary", "Unchecked", "UnusedMethod"})
@NullableBoundary("CDP wire objects use null for absent optional fields")
public final class ServiceWorker {
    private ServiceWorker() {}
    private static final CdpField<java.util.List<Target.TargetID>> CONTROLLED_CLIENTS = CdpField.of("controlledClients", value -> CdpObject.requireList(value, element0 -> new Target.TargetID((String) element0)));
    private static final CdpField<ServiceWorker.ServiceWorkerErrorMessage> ERROR_MESSAGE = CdpField.of("errorMessage", value -> java.util.Objects.requireNonNull(ServiceWorker.ServiceWorkerErrorMessage.fromMap(CdpObject.requireObjectMap(value))));
    private static final CdpField<java.util.List<ServiceWorker.ServiceWorkerRegistration>> REGISTRATIONS = CdpField.of("registrations", value -> CdpObject.requireList(value, element0 -> java.util.Objects.requireNonNull(ServiceWorker.ServiceWorkerRegistration.fromMap(CdpObject.requireObjectMap(element0)))));
    private static final CdpField<ServiceWorker.RegistrationID> REGISTRATION_ID = CdpField.of("registrationId", value -> new ServiceWorker.RegistrationID((String) value));
    private static final CdpField<ServiceWorker.ServiceWorkerVersionRunningStatus> RUNNING_STATUS = CdpField.of("runningStatus", value -> ServiceWorker.ServiceWorkerVersionRunningStatus.of((String) value));
    private static final CdpField<ServiceWorker.ServiceWorkerVersionStatus> STATUS = CdpField.of("status", value -> ServiceWorker.ServiceWorkerVersionStatus.of((String) value));
    private static final CdpField<Target.TargetID> TARGET_ID = CdpField.of("targetId", value -> new Target.TargetID((String) value));
    private static final CdpField<java.util.List<ServiceWorker.ServiceWorkerVersion>> VERSIONS = CdpField.of("versions", value -> CdpObject.requireList(value, element0 -> java.util.Objects.requireNonNull(ServiceWorker.ServiceWorkerVersion.fromMap(CdpObject.requireObjectMap(element0)))));
    /**
     * Tagged String wire value for RegistrationID.
     */
    public static final class RegistrationID implements CdpValue<String> {
        public final String value;
        public RegistrationID(String value) { this.value = java.util.Objects.requireNonNull(value); }
        @Override public String value() { return value; }
        @Override public boolean equals(@org.jspecify.annotations.Nullable Object other) {
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
            return require(REGISTRATION_ID);
        }
        /**
         * Returns the scopeURL field.
         * @return the protocol field value
         */
        public String scopeURL() {
            return requireString("scopeURL");
        }
        /**
         * Returns the isDeleted field.
         * @return the protocol field value
         */
        public boolean isDeleted() {
            return requireBoolean("isDeleted");
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
        @Override public String value() { return value; }
        public static ServiceWorkerVersionRunningStatus of(String value) {
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
        @Override public String value() { return value; }
        public static ServiceWorkerVersionStatus of(String value) {
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
            return requireString("versionId");
        }
        /**
         * Returns the registrationId field.
         * @return the protocol field value
         */
        public ServiceWorker.RegistrationID registrationId() {
            return require(REGISTRATION_ID);
        }
        /**
         * Returns the scriptURL field.
         * @return the protocol field value
         */
        public String scriptURL() {
            return requireString("scriptURL");
        }
        /**
         * Returns the runningStatus field.
         * @return the protocol field value
         */
        public ServiceWorker.ServiceWorkerVersionRunningStatus runningStatus() {
            return require(RUNNING_STATUS);
        }
        /**
         * Returns the status field.
         * @return the protocol field value
         */
        public ServiceWorker.ServiceWorkerVersionStatus status() {
            return require(STATUS);
        }
        /**
         * The Last-Modified header value of the main script.
         * @return the protocol field value, empty when absent
         */
        public OptionalDouble scriptLastModified() {
            return optionalDouble("scriptLastModified");
        }
        /**
         * The time at which the response headers of the main script were received from the server. For cached script it is the last time the cache entry was validated.
         * @return the protocol field value, empty when absent
         */
        public OptionalDouble scriptResponseTime() {
            return optionalDouble("scriptResponseTime");
        }
        /**
         * Returns the controlledClients field.
         * @return the protocol field value, empty when absent
         */
        public Optional<java.util.List<Target.TargetID>> controlledClients() {
            return optional(CONTROLLED_CLIENTS);
        }
        /**
         * Returns the targetId field.
         * @return the protocol field value, empty when absent
         */
        public Optional<Target.TargetID> targetId() {
            return optional(TARGET_ID);
        }
        /**
         * Returns the routerRules field.
         * @return the protocol field value, empty when absent
         */
        public Optional<String> routerRules() {
            return optionalString("routerRules");
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
         * @param scriptLastModified field value
         * @return this model
         */
        public ServiceWorkerVersion scriptLastModified(double scriptLastModified) {
            set("scriptLastModified", scriptLastModified);
            return this;
        }
        /**
         * The time at which the response headers of the main script were received from the server. For cached script it is the last time the cache entry was validated.
         * @param scriptResponseTime field value
         * @return this model
         */
        public ServiceWorkerVersion scriptResponseTime(double scriptResponseTime) {
            set("scriptResponseTime", scriptResponseTime);
            return this;
        }
        /**
         * Sets the controlledClients field.
         * @param controlledClients field value
         * @return this model
         */
        public ServiceWorkerVersion controlledClients(java.util.List<Target.TargetID> controlledClients) {
            set("controlledClients", controlledClients);
            return this;
        }
        /**
         * Sets the targetId field.
         * @param targetId field value
         * @return this model
         */
        public ServiceWorkerVersion targetId(Target.TargetID targetId) {
            set("targetId", targetId);
            return this;
        }
        /**
         * Sets the routerRules field.
         * @param routerRules field value
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
            return requireString("errorMessage");
        }
        /**
         * Returns the registrationId field.
         * @return the protocol field value
         */
        public ServiceWorker.RegistrationID registrationId() {
            return require(REGISTRATION_ID);
        }
        /**
         * Returns the versionId field.
         * @return the protocol field value
         */
        public String versionId() {
            return requireString("versionId");
        }
        /**
         * Returns the sourceURL field.
         * @return the protocol field value
         */
        public String sourceURL() {
            return requireString("sourceURL");
        }
        /**
         * Returns the lineNumber field.
         * @return the protocol field value
         */
        public long lineNumber() {
            return requireLong("lineNumber");
        }
        /**
         * Returns the columnNumber field.
         * @return the protocol field value
         */
        public long columnNumber() {
            return requireLong("columnNumber");
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
        public static DeliverPushMessageRequest fromMap(Map<String, Object> values) {
            final DeliverPushMessageRequest instance_ = new DeliverPushMessageRequest();
            if (values != null) instance_.values.putAll(values);
            return instance_;
        }
        /**
         * Returns the origin field.
         * @return the protocol field value
         */
        public String origin() {
            return requireString("origin");
        }
        /**
         * Returns the registrationId field.
         * @return the protocol field value
         */
        public ServiceWorker.RegistrationID registrationId() {
            return require(REGISTRATION_ID);
        }
        /**
         * Returns the data field.
         * @return the protocol field value
         */
        public String data() {
            return requireString("data");
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
        public static DispatchSyncEventRequest fromMap(Map<String, Object> values) {
            final DispatchSyncEventRequest instance_ = new DispatchSyncEventRequest();
            if (values != null) instance_.values.putAll(values);
            return instance_;
        }
        /**
         * Returns the origin field.
         * @return the protocol field value
         */
        public String origin() {
            return requireString("origin");
        }
        /**
         * Returns the registrationId field.
         * @return the protocol field value
         */
        public ServiceWorker.RegistrationID registrationId() {
            return require(REGISTRATION_ID);
        }
        /**
         * Returns the tag field.
         * @return the protocol field value
         */
        public String tag() {
            return requireString("tag");
        }
        /**
         * Returns the lastChance field.
         * @return the protocol field value
         */
        public boolean lastChance() {
            return requireBoolean("lastChance");
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
        public static DispatchPeriodicSyncEventRequest fromMap(Map<String, Object> values) {
            final DispatchPeriodicSyncEventRequest instance_ = new DispatchPeriodicSyncEventRequest();
            if (values != null) instance_.values.putAll(values);
            return instance_;
        }
        /**
         * Returns the origin field.
         * @return the protocol field value
         */
        public String origin() {
            return requireString("origin");
        }
        /**
         * Returns the registrationId field.
         * @return the protocol field value
         */
        public ServiceWorker.RegistrationID registrationId() {
            return require(REGISTRATION_ID);
        }
        /**
         * Returns the tag field.
         * @return the protocol field value
         */
        public String tag() {
            return requireString("tag");
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
        public static SetForceUpdateOnPageLoadRequest fromMap(Map<String, Object> values) {
            final SetForceUpdateOnPageLoadRequest instance_ = new SetForceUpdateOnPageLoadRequest();
            if (values != null) instance_.values.putAll(values);
            return instance_;
        }
        /**
         * Returns the forceUpdateOnPageLoad field.
         * @return the protocol field value
         */
        public boolean forceUpdateOnPageLoad() {
            return requireBoolean("forceUpdateOnPageLoad");
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
        public static SkipWaitingRequest fromMap(Map<String, Object> values) {
            final SkipWaitingRequest instance_ = new SkipWaitingRequest();
            if (values != null) instance_.values.putAll(values);
            return instance_;
        }
        /**
         * Returns the scopeURL field.
         * @return the protocol field value
         */
        public String scopeURL() {
            return requireString("scopeURL");
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
        public static StartWorkerRequest fromMap(Map<String, Object> values) {
            final StartWorkerRequest instance_ = new StartWorkerRequest();
            if (values != null) instance_.values.putAll(values);
            return instance_;
        }
        /**
         * Returns the scopeURL field.
         * @return the protocol field value
         */
        public String scopeURL() {
            return requireString("scopeURL");
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
        public static StopWorkerRequest fromMap(Map<String, Object> values) {
            final StopWorkerRequest instance_ = new StopWorkerRequest();
            if (values != null) instance_.values.putAll(values);
            return instance_;
        }
        /**
         * Returns the versionId field.
         * @return the protocol field value
         */
        public String versionId() {
            return requireString("versionId");
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
        public static UnregisterRequest fromMap(Map<String, Object> values) {
            final UnregisterRequest instance_ = new UnregisterRequest();
            if (values != null) instance_.values.putAll(values);
            return instance_;
        }
        /**
         * Returns the scopeURL field.
         * @return the protocol field value
         */
        public String scopeURL() {
            return requireString("scopeURL");
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
        public static UpdateRegistrationRequest fromMap(Map<String, Object> values) {
            final UpdateRegistrationRequest instance_ = new UpdateRegistrationRequest();
            if (values != null) instance_.values.putAll(values);
            return instance_;
        }
        /**
         * Returns the scopeURL field.
         * @return the protocol field value
         */
        public String scopeURL() {
            return requireString("scopeURL");
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
            return require(ERROR_MESSAGE);
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
            return require(REGISTRATIONS);
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
            return require(VERSIONS);
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
    public static final class Client extends CdpDomainClient {
        public Client(CdpClient client) { super(client); }
        /**
         * Invokes ServiceWorker.deliverPushMessage.
         * @param origin protocol value
         * @param registrationId protocol value
         * @param data protocol value
         * @return a stage completing when the command completes
         */
        public CompletionStage<Void> deliverPushMessage(String origin, ServiceWorker.RegistrationID registrationId, String data) {
            return call("ServiceWorker.deliverPushMessage", params("origin", origin, "registrationId", registrationId, "data", data));
        }
        /**
         * Invokes ServiceWorker.deliverPushMessage with a request object.
         * @param request request parameters
         * @return a stage completing when the command completes
         */
        public CompletionStage<Void> deliverPushMessage(DeliverPushMessageRequest request) {
            return call("ServiceWorker.deliverPushMessage", request);
        }
        /**
         * Invokes ServiceWorker.disable.
         * @return a stage completing when the command completes
         */
        public CompletionStage<Void> disable() {
            return call("ServiceWorker.disable");
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
            return call("ServiceWorker.dispatchSyncEvent", params("origin", origin, "registrationId", registrationId, "tag", tag, "lastChance", lastChance));
        }
        /**
         * Invokes ServiceWorker.dispatchSyncEvent with a request object.
         * @param request request parameters
         * @return a stage completing when the command completes
         */
        public CompletionStage<Void> dispatchSyncEvent(DispatchSyncEventRequest request) {
            return call("ServiceWorker.dispatchSyncEvent", request);
        }
        /**
         * Invokes ServiceWorker.dispatchPeriodicSyncEvent.
         * @param origin protocol value
         * @param registrationId protocol value
         * @param tag protocol value
         * @return a stage completing when the command completes
         */
        public CompletionStage<Void> dispatchPeriodicSyncEvent(String origin, ServiceWorker.RegistrationID registrationId, String tag) {
            return call("ServiceWorker.dispatchPeriodicSyncEvent", params("origin", origin, "registrationId", registrationId, "tag", tag));
        }
        /**
         * Invokes ServiceWorker.dispatchPeriodicSyncEvent with a request object.
         * @param request request parameters
         * @return a stage completing when the command completes
         */
        public CompletionStage<Void> dispatchPeriodicSyncEvent(DispatchPeriodicSyncEventRequest request) {
            return call("ServiceWorker.dispatchPeriodicSyncEvent", request);
        }
        /**
         * Invokes ServiceWorker.enable.
         * @return a stage completing when the command completes
         */
        public CompletionStage<Void> enable() {
            return call("ServiceWorker.enable");
        }
        /**
         * Invokes ServiceWorker.setForceUpdateOnPageLoad.
         * @param forceUpdateOnPageLoad protocol value
         * @return a stage completing when the command completes
         */
        public CompletionStage<Void> setForceUpdateOnPageLoad(boolean forceUpdateOnPageLoad) {
            return call("ServiceWorker.setForceUpdateOnPageLoad", params("forceUpdateOnPageLoad", forceUpdateOnPageLoad));
        }
        /**
         * Invokes ServiceWorker.setForceUpdateOnPageLoad with a request object.
         * @param request request parameters
         * @return a stage completing when the command completes
         */
        public CompletionStage<Void> setForceUpdateOnPageLoad(SetForceUpdateOnPageLoadRequest request) {
            return call("ServiceWorker.setForceUpdateOnPageLoad", request);
        }
        /**
         * Invokes ServiceWorker.skipWaiting.
         * @param scopeURL protocol value
         * @return a stage completing when the command completes
         */
        public CompletionStage<Void> skipWaiting(String scopeURL) {
            return call("ServiceWorker.skipWaiting", params("scopeURL", scopeURL));
        }
        /**
         * Invokes ServiceWorker.skipWaiting with a request object.
         * @param request request parameters
         * @return a stage completing when the command completes
         */
        public CompletionStage<Void> skipWaiting(SkipWaitingRequest request) {
            return call("ServiceWorker.skipWaiting", request);
        }
        /**
         * Invokes ServiceWorker.startWorker.
         * @param scopeURL protocol value
         * @return a stage completing when the command completes
         */
        public CompletionStage<Void> startWorker(String scopeURL) {
            return call("ServiceWorker.startWorker", params("scopeURL", scopeURL));
        }
        /**
         * Invokes ServiceWorker.startWorker with a request object.
         * @param request request parameters
         * @return a stage completing when the command completes
         */
        public CompletionStage<Void> startWorker(StartWorkerRequest request) {
            return call("ServiceWorker.startWorker", request);
        }
        /**
         * Invokes ServiceWorker.stopAllWorkers.
         * @return a stage completing when the command completes
         */
        public CompletionStage<Void> stopAllWorkers() {
            return call("ServiceWorker.stopAllWorkers");
        }
        /**
         * Invokes ServiceWorker.stopWorker.
         * @param versionId protocol value
         * @return a stage completing when the command completes
         */
        public CompletionStage<Void> stopWorker(String versionId) {
            return call("ServiceWorker.stopWorker", params("versionId", versionId));
        }
        /**
         * Invokes ServiceWorker.stopWorker with a request object.
         * @param request request parameters
         * @return a stage completing when the command completes
         */
        public CompletionStage<Void> stopWorker(StopWorkerRequest request) {
            return call("ServiceWorker.stopWorker", request);
        }
        /**
         * Invokes ServiceWorker.unregister.
         * @param scopeURL protocol value
         * @return a stage completing when the command completes
         */
        public CompletionStage<Void> unregister(String scopeURL) {
            return call("ServiceWorker.unregister", params("scopeURL", scopeURL));
        }
        /**
         * Invokes ServiceWorker.unregister with a request object.
         * @param request request parameters
         * @return a stage completing when the command completes
         */
        public CompletionStage<Void> unregister(UnregisterRequest request) {
            return call("ServiceWorker.unregister", request);
        }
        /**
         * Invokes ServiceWorker.updateRegistration.
         * @param scopeURL protocol value
         * @return a stage completing when the command completes
         */
        public CompletionStage<Void> updateRegistration(String scopeURL) {
            return call("ServiceWorker.updateRegistration", params("scopeURL", scopeURL));
        }
        /**
         * Invokes ServiceWorker.updateRegistration with a request object.
         * @param request request parameters
         * @return a stage completing when the command completes
         */
        public CompletionStage<Void> updateRegistration(UpdateRegistrationRequest request) {
            return call("ServiceWorker.updateRegistration", request);
        }
        /**
         * Subscribes to ServiceWorker.workerErrorReported.
         * @param handler event callback
         * @return a removable subscription
         */
        public CdpSubscription onWorkerErrorReported(Consumer<WorkerErrorReportedEvent> handler) {
            return on("ServiceWorker.workerErrorReported", WorkerErrorReportedEvent::fromMap, handler);
        }
        /**
         * Subscribes to ServiceWorker.workerRegistrationUpdated.
         * @param handler event callback
         * @return a removable subscription
         */
        public CdpSubscription onWorkerRegistrationUpdated(Consumer<WorkerRegistrationUpdatedEvent> handler) {
            return on("ServiceWorker.workerRegistrationUpdated", WorkerRegistrationUpdatedEvent::fromMap, handler);
        }
        /**
         * Subscribes to ServiceWorker.workerVersionUpdated.
         * @param handler event callback
         * @return a removable subscription
         */
        public CdpSubscription onWorkerVersionUpdated(Consumer<WorkerVersionUpdatedEvent> handler) {
            return on("ServiceWorker.workerVersionUpdated", WorkerVersionUpdatedEvent::fromMap, handler);
        }
    }
}
