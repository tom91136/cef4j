// GENERATED - do not edit. Regenerate via: ./mvnw generate-sources -pl cef4j-cdp -Dcef.version=150.0.18+gdb11278+chromium-150.0.7871.213
package net.kurobako.cef4j.cdp.generated;

import java.util.Map;
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
 * Defines events for background web platform features.
 * <p><b>Experimental:</b> this part of CDP may change without notice.
 * @see <a href="https://chromium.googlesource.com/chromium/src/+/refs/tags/150.0.7871.213/third_party/blink/public/devtools_protocol/domains/BackgroundService.pdl">Pinned protocol source</a>
 */
@Generated("./mvnw generate-sources -pl cef4j-cdp -Dcef.version=150.0.18+gdb11278+chromium-150.0.7871.213")
@SuppressWarnings({"EscapedEntity", "InvalidParam", "JavaLangClash", "MissingSummary", "Unchecked", "UnusedMethod"})
@NullableBoundary("CDP wire objects use null for absent optional fields")
public final class BackgroundService {
    private BackgroundService() {}
    private static final CdpField<BackgroundService.BackgroundServiceEvent> BACKGROUND_SERVICE_EVENT = CdpField.of("backgroundServiceEvent", value -> java.util.Objects.requireNonNull(BackgroundService.BackgroundServiceEvent.fromMap(CdpObject.requireObjectMap(value))));
    private static final CdpField<java.util.List<BackgroundService.EventMetadata>> EVENT_METADATA = CdpField.of("eventMetadata", value -> CdpObject.requireList(value, element0 -> java.util.Objects.requireNonNull(BackgroundService.EventMetadata.fromMap(CdpObject.requireObjectMap(element0)))));
    private static final CdpField<BackgroundService.ServiceName> SERVICE = CdpField.of("service", value -> BackgroundService.ServiceName.of((String) value));
    private static final CdpField<ServiceWorker.RegistrationID> SERVICE_WORKER_REGISTRATION_ID = CdpField.of("serviceWorkerRegistrationId", value -> new ServiceWorker.RegistrationID((String) value));
    private static final CdpField<Network.TimeSinceEpoch> TIMESTAMP = CdpField.of("timestamp", value -> new Network.TimeSinceEpoch(((Number) value).doubleValue()));
    /**
     * The Background Service that will be associated with the commands/events. Every Background Service operates independently, but they share the same API.
     */
    public enum ServiceName implements CdpValue<String> {
        BACKGROUNDFETCH("backgroundFetch"),
        BACKGROUNDSYNC("backgroundSync"),
        PUSHMESSAGING("pushMessaging"),
        NOTIFICATIONS("notifications"),
        PAYMENTHANDLER("paymentHandler"),
        PERIODICBACKGROUNDSYNC("periodicBackgroundSync");
        public final String value;
        ServiceName(String value) { this.value = value; }
        @Override public String value() { return value; }
        public static ServiceName of(String value) {
            for (ServiceName constant : values()) {
                if (constant.value.equals(value)) return constant;
            }
            throw new IllegalArgumentException("Unknown ServiceName value: " + value);
        }
    }
    /**
     * A key-value pair for additional event information to pass along.
     */
    public static final class EventMetadata extends CdpObject {
        public EventMetadata() {}
        private EventMetadata(Map<String, Object> values) { super(values); }
        public static EventMetadata fromMap(Map<String, Object> values) {
            return new EventMetadata(values);
        }
        /**
         * Returns the key field.
         * @return the protocol field value
         */
        public String key() {
            return requireString("key");
        }
        /**
         * Returns the value field.
         * @return the protocol field value
         */
        public String value() {
            return requireString("value");
        }
        /**
         * Sets the key field.
         * @param key field value
         * @return this model
         */
        public EventMetadata key(String key) {
            set("key", key);
            return this;
        }
        /**
         * Sets the value field.
         * @param value field value
         * @return this model
         */
        public EventMetadata value(String value) {
            set("value", value);
            return this;
        }
    }
    /**
     */
    public static final class BackgroundServiceEvent extends CdpObject {
        public BackgroundServiceEvent() {}
        private BackgroundServiceEvent(Map<String, Object> values) { super(values); }
        public static BackgroundServiceEvent fromMap(Map<String, Object> values) {
            return new BackgroundServiceEvent(values);
        }
        /**
         * Timestamp of the event (in seconds).
         * @return the protocol field value
         */
        public Network.TimeSinceEpoch timestamp() {
            return require(TIMESTAMP);
        }
        /**
         * The origin this event belongs to.
         * @return the protocol field value
         */
        public String origin() {
            return requireString("origin");
        }
        /**
         * The Service Worker ID that initiated the event.
         * @return the protocol field value
         */
        public ServiceWorker.RegistrationID serviceWorkerRegistrationId() {
            return require(SERVICE_WORKER_REGISTRATION_ID);
        }
        /**
         * The Background Service this event belongs to.
         * @return the protocol field value
         */
        public BackgroundService.ServiceName service() {
            return require(SERVICE);
        }
        /**
         * A description of the event.
         * @return the protocol field value
         */
        public String eventName() {
            return requireString("eventName");
        }
        /**
         * An identifier that groups related events together.
         * @return the protocol field value
         */
        public String instanceId() {
            return requireString("instanceId");
        }
        /**
         * A list of event-specific information.
         * @return the protocol field value
         */
        public java.util.List<BackgroundService.EventMetadata> eventMetadata() {
            return require(EVENT_METADATA);
        }
        /**
         * Storage key this event belongs to.
         * @return the protocol field value
         */
        public String storageKey() {
            return requireString("storageKey");
        }
        /**
         * Timestamp of the event (in seconds).
         * @param timestamp field value
         * @return this model
         */
        public BackgroundServiceEvent timestamp(Network.TimeSinceEpoch timestamp) {
            set("timestamp", timestamp);
            return this;
        }
        /**
         * The origin this event belongs to.
         * @param origin field value
         * @return this model
         */
        public BackgroundServiceEvent origin(String origin) {
            set("origin", origin);
            return this;
        }
        /**
         * The Service Worker ID that initiated the event.
         * @param serviceWorkerRegistrationId field value
         * @return this model
         */
        public BackgroundServiceEvent serviceWorkerRegistrationId(ServiceWorker.RegistrationID serviceWorkerRegistrationId) {
            set("serviceWorkerRegistrationId", serviceWorkerRegistrationId);
            return this;
        }
        /**
         * The Background Service this event belongs to.
         * @param service field value
         * @return this model
         */
        public BackgroundServiceEvent service(BackgroundService.ServiceName service) {
            set("service", service);
            return this;
        }
        /**
         * A description of the event.
         * @param eventName field value
         * @return this model
         */
        public BackgroundServiceEvent eventName(String eventName) {
            set("eventName", eventName);
            return this;
        }
        /**
         * An identifier that groups related events together.
         * @param instanceId field value
         * @return this model
         */
        public BackgroundServiceEvent instanceId(String instanceId) {
            set("instanceId", instanceId);
            return this;
        }
        /**
         * A list of event-specific information.
         * @param eventMetadata field value
         * @return this model
         */
        public BackgroundServiceEvent eventMetadata(java.util.List<BackgroundService.EventMetadata> eventMetadata) {
            set("eventMetadata", eventMetadata);
            return this;
        }
        /**
         * Storage key this event belongs to.
         * @param storageKey field value
         * @return this model
         */
        public BackgroundServiceEvent storageKey(String storageKey) {
            set("storageKey", storageKey);
            return this;
        }
    }
    /**
     * Enables event updates for the service.
     */
    public static final class StartObservingRequest extends CdpObject {
        public StartObservingRequest() {}
        public static StartObservingRequest fromMap(Map<String, Object> values) {
            final StartObservingRequest instance_ = new StartObservingRequest();
            if (values != null) instance_.values.putAll(values);
            return instance_;
        }
        /**
         * Returns the service field.
         * @return the protocol field value
         */
        public BackgroundService.ServiceName service() {
            return require(SERVICE);
        }
        /**
         * Sets the service field.
         * @param service field value
         * @return this model
         */
        public StartObservingRequest service(BackgroundService.ServiceName service) {
            set("service", service);
            return this;
        }
    }
    /**
     * Disables event updates for the service.
     */
    public static final class StopObservingRequest extends CdpObject {
        public StopObservingRequest() {}
        public static StopObservingRequest fromMap(Map<String, Object> values) {
            final StopObservingRequest instance_ = new StopObservingRequest();
            if (values != null) instance_.values.putAll(values);
            return instance_;
        }
        /**
         * Returns the service field.
         * @return the protocol field value
         */
        public BackgroundService.ServiceName service() {
            return require(SERVICE);
        }
        /**
         * Sets the service field.
         * @param service field value
         * @return this model
         */
        public StopObservingRequest service(BackgroundService.ServiceName service) {
            set("service", service);
            return this;
        }
    }
    /**
     * Set the recording state for the service.
     */
    public static final class SetRecordingRequest extends CdpObject {
        public SetRecordingRequest() {}
        public static SetRecordingRequest fromMap(Map<String, Object> values) {
            final SetRecordingRequest instance_ = new SetRecordingRequest();
            if (values != null) instance_.values.putAll(values);
            return instance_;
        }
        /**
         * Returns the shouldRecord field.
         * @return the protocol field value
         */
        public boolean shouldRecord() {
            return requireBoolean("shouldRecord");
        }
        /**
         * Returns the service field.
         * @return the protocol field value
         */
        public BackgroundService.ServiceName service() {
            return require(SERVICE);
        }
        /**
         * Sets the shouldRecord field.
         * @param shouldRecord field value
         * @return this model
         */
        public SetRecordingRequest shouldRecord(boolean shouldRecord) {
            set("shouldRecord", shouldRecord);
            return this;
        }
        /**
         * Sets the service field.
         * @param service field value
         * @return this model
         */
        public SetRecordingRequest service(BackgroundService.ServiceName service) {
            set("service", service);
            return this;
        }
    }
    /**
     * Clears all stored data for the service.
     */
    public static final class ClearEventsRequest extends CdpObject {
        public ClearEventsRequest() {}
        public static ClearEventsRequest fromMap(Map<String, Object> values) {
            final ClearEventsRequest instance_ = new ClearEventsRequest();
            if (values != null) instance_.values.putAll(values);
            return instance_;
        }
        /**
         * Returns the service field.
         * @return the protocol field value
         */
        public BackgroundService.ServiceName service() {
            return require(SERVICE);
        }
        /**
         * Sets the service field.
         * @param service field value
         * @return this model
         */
        public ClearEventsRequest service(BackgroundService.ServiceName service) {
            set("service", service);
            return this;
        }
    }
    /**
     * Called when the recording state for the service has been updated.
     */
    public static final class RecordingStateChangedEvent extends CdpObject {
        public RecordingStateChangedEvent() {}
        private RecordingStateChangedEvent(Map<String, Object> values) { super(values); }
        public static RecordingStateChangedEvent fromMap(Map<String, Object> values) {
            return new RecordingStateChangedEvent(values);
        }
        /**
         * Returns the isRecording field.
         * @return the protocol field value
         */
        public boolean isRecording() {
            return requireBoolean("isRecording");
        }
        /**
         * Returns the service field.
         * @return the protocol field value
         */
        public BackgroundService.ServiceName service() {
            return require(SERVICE);
        }
        /**
         * Sets the isRecording field.
         * @param isRecording field value
         * @return this model
         */
        public RecordingStateChangedEvent isRecording(boolean isRecording) {
            set("isRecording", isRecording);
            return this;
        }
        /**
         * Sets the service field.
         * @param service field value
         * @return this model
         */
        public RecordingStateChangedEvent service(BackgroundService.ServiceName service) {
            set("service", service);
            return this;
        }
    }
    /**
     * Called with all existing backgroundServiceEvents when enabled, and all new events afterwards if enabled and recording.
     */
    public static final class BackgroundServiceEventReceivedEvent extends CdpObject {
        public BackgroundServiceEventReceivedEvent() {}
        private BackgroundServiceEventReceivedEvent(Map<String, Object> values) { super(values); }
        public static BackgroundServiceEventReceivedEvent fromMap(Map<String, Object> values) {
            return new BackgroundServiceEventReceivedEvent(values);
        }
        /**
         * Returns the backgroundServiceEvent field.
         * @return the protocol field value
         */
        public BackgroundService.BackgroundServiceEvent backgroundServiceEvent() {
            return require(BACKGROUND_SERVICE_EVENT);
        }
        /**
         * Sets the backgroundServiceEvent field.
         * @param backgroundServiceEvent field value
         * @return this model
         */
        public BackgroundServiceEventReceivedEvent backgroundServiceEvent(BackgroundService.BackgroundServiceEvent backgroundServiceEvent) {
            set("backgroundServiceEvent", backgroundServiceEvent);
            return this;
        }
    }
    public static final class Client extends CdpDomainClient {
        public Client(CdpClient client) { super(client); }
        /**
         * Enables event updates for the service.
         * @param service protocol value
         * @return a stage completing when the command completes
         */
        public CompletionStage<Void> startObserving(BackgroundService.ServiceName service) {
            return call("BackgroundService.startObserving", params("service", service));
        }
        /**
         * Enables event updates for the service.
         * @param request request parameters
         * @return a stage completing when the command completes
         */
        public CompletionStage<Void> startObserving(StartObservingRequest request) {
            return call("BackgroundService.startObserving", request);
        }
        /**
         * Disables event updates for the service.
         * @param service protocol value
         * @return a stage completing when the command completes
         */
        public CompletionStage<Void> stopObserving(BackgroundService.ServiceName service) {
            return call("BackgroundService.stopObserving", params("service", service));
        }
        /**
         * Disables event updates for the service.
         * @param request request parameters
         * @return a stage completing when the command completes
         */
        public CompletionStage<Void> stopObserving(StopObservingRequest request) {
            return call("BackgroundService.stopObserving", request);
        }
        /**
         * Set the recording state for the service.
         * @param shouldRecord protocol value
         * @param service protocol value
         * @return a stage completing when the command completes
         */
        public CompletionStage<Void> setRecording(boolean shouldRecord, BackgroundService.ServiceName service) {
            return call("BackgroundService.setRecording", params("shouldRecord", shouldRecord, "service", service));
        }
        /**
         * Set the recording state for the service.
         * @param request request parameters
         * @return a stage completing when the command completes
         */
        public CompletionStage<Void> setRecording(SetRecordingRequest request) {
            return call("BackgroundService.setRecording", request);
        }
        /**
         * Clears all stored data for the service.
         * @param service protocol value
         * @return a stage completing when the command completes
         */
        public CompletionStage<Void> clearEvents(BackgroundService.ServiceName service) {
            return call("BackgroundService.clearEvents", params("service", service));
        }
        /**
         * Clears all stored data for the service.
         * @param request request parameters
         * @return a stage completing when the command completes
         */
        public CompletionStage<Void> clearEvents(ClearEventsRequest request) {
            return call("BackgroundService.clearEvents", request);
        }
        /**
         * Called when the recording state for the service has been updated.
         * @param handler event callback
         * @return a removable subscription
         */
        public CdpSubscription onRecordingStateChanged(Consumer<RecordingStateChangedEvent> handler) {
            return on("BackgroundService.recordingStateChanged", RecordingStateChangedEvent::fromMap, handler);
        }
        /**
         * Called with all existing backgroundServiceEvents when enabled, and all new events afterwards if enabled and recording.
         * @param handler event callback
         * @return a removable subscription
         */
        public CdpSubscription onBackgroundServiceEventReceived(Consumer<BackgroundServiceEventReceivedEvent> handler) {
            return on("BackgroundService.backgroundServiceEventReceived", BackgroundServiceEventReceivedEvent::fromMap, handler);
        }
    }
}
