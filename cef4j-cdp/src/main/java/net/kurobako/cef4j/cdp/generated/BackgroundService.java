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
 * Defines events for background web platform features.
 * <p><b>Experimental:</b> this part of CDP may change without notice.
 * @see <a href="https://chromium.googlesource.com/chromium/src/+/refs/tags/150.0.7871.213/third_party/blink/public/devtools_protocol/domains/BackgroundService.pdl">Pinned protocol source</a>
 */
@Generated("mvn generate-sources -pl cef4j-platform -Dcef.version=150.0.18+gdb11278+chromium-150.0.7871.213")
@SuppressWarnings({"EscapedEntity", "InvalidParam", "JavaLangClash", "MissingSummary", "Unchecked", "UnusedMethod"})
public final class BackgroundService {
    private BackgroundService() {}
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
        @Nonnull public String value() { return value; }
        public static ServiceName of(@Nonnull String value) {
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
            return (String) require("key");
        }
        /**
         * Returns the value field.
         * @return the protocol field value
         */
        public String value() {
            return (String) require("value");
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
            return new Network.TimeSinceEpoch(((Number) require("timestamp")).doubleValue());
        }
        /**
         * The origin this event belongs to.
         * @return the protocol field value
         */
        public String origin() {
            return (String) require("origin");
        }
        /**
         * The Service Worker ID that initiated the event.
         * @return the protocol field value
         */
        public ServiceWorker.RegistrationID serviceWorkerRegistrationId() {
            return new ServiceWorker.RegistrationID((String) require("serviceWorkerRegistrationId"));
        }
        /**
         * The Background Service this event belongs to.
         * @return the protocol field value
         */
        public BackgroundService.ServiceName service() {
            return BackgroundService.ServiceName.of((String) require("service"));
        }
        /**
         * A description of the event.
         * @return the protocol field value
         */
        public String eventName() {
            return (String) require("eventName");
        }
        /**
         * An identifier that groups related events together.
         * @return the protocol field value
         */
        public String instanceId() {
            return (String) require("instanceId");
        }
        /**
         * A list of event-specific information.
         * @return the protocol field value
         */
        public java.util.List<BackgroundService.EventMetadata> eventMetadata() {
            return CdpObject.requireList(require("eventMetadata"), element0 -> java.util.Objects.requireNonNull(BackgroundService.EventMetadata.fromMap(java.util.Objects.requireNonNull(CdpObject.objectMap(element0)))));
        }
        /**
         * Storage key this event belongs to.
         * @return the protocol field value
         */
        public String storageKey() {
            return (String) require("storageKey");
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
        /**
         * Enables event updates for the service.
         * @param service protocol value
         */
        public StartObservingRequest(BackgroundService.ServiceName service) {
            set("service", service);
        }
        public static StartObservingRequest fromMap(Map<String, Object> values) {
            StartObservingRequest instance_ = new StartObservingRequest();
            if (values != null) instance_.values.putAll(values);
            return instance_;
        }
        /**
         * Returns the service field.
         * @return the protocol field value
         */
        public BackgroundService.ServiceName service() {
            return BackgroundService.ServiceName.of((String) require("service"));
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
        /**
         * Disables event updates for the service.
         * @param service protocol value
         */
        public StopObservingRequest(BackgroundService.ServiceName service) {
            set("service", service);
        }
        public static StopObservingRequest fromMap(Map<String, Object> values) {
            StopObservingRequest instance_ = new StopObservingRequest();
            if (values != null) instance_.values.putAll(values);
            return instance_;
        }
        /**
         * Returns the service field.
         * @return the protocol field value
         */
        public BackgroundService.ServiceName service() {
            return BackgroundService.ServiceName.of((String) require("service"));
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
        /**
         * Set the recording state for the service.
         * @param shouldRecord protocol value
         * @param service protocol value
         */
        public SetRecordingRequest(boolean shouldRecord, BackgroundService.ServiceName service) {
            set("shouldRecord", shouldRecord);
            set("service", service);
        }
        public static SetRecordingRequest fromMap(Map<String, Object> values) {
            SetRecordingRequest instance_ = new SetRecordingRequest();
            if (values != null) instance_.values.putAll(values);
            return instance_;
        }
        /**
         * Returns the shouldRecord field.
         * @return the protocol field value
         */
        public boolean shouldRecord() {
            return (Boolean) require("shouldRecord");
        }
        /**
         * Returns the service field.
         * @return the protocol field value
         */
        public BackgroundService.ServiceName service() {
            return BackgroundService.ServiceName.of((String) require("service"));
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
        /**
         * Clears all stored data for the service.
         * @param service protocol value
         */
        public ClearEventsRequest(BackgroundService.ServiceName service) {
            set("service", service);
        }
        public static ClearEventsRequest fromMap(Map<String, Object> values) {
            ClearEventsRequest instance_ = new ClearEventsRequest();
            if (values != null) instance_.values.putAll(values);
            return instance_;
        }
        /**
         * Returns the service field.
         * @return the protocol field value
         */
        public BackgroundService.ServiceName service() {
            return BackgroundService.ServiceName.of((String) require("service"));
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
            return (Boolean) require("isRecording");
        }
        /**
         * Returns the service field.
         * @return the protocol field value
         */
        public BackgroundService.ServiceName service() {
            return BackgroundService.ServiceName.of((String) require("service"));
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
            return java.util.Objects.requireNonNull(BackgroundService.BackgroundServiceEvent.fromMap(java.util.Objects.requireNonNull(CdpObject.objectMap(require("backgroundServiceEvent")))));
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
    public static final class Client {
        private final CdpClient client;
        public Client(CdpClient client) { this.client = client; }
        /**
         * Enables event updates for the service.
         * @param service protocol value
         * @return a stage completing when the command completes
         */
        public CompletionStage<Void> startObserving(BackgroundService.ServiceName service) {
            Map<String, Object> params = new LinkedHashMap<>();
            params.put("service", CdpObject.json(service));
            return client.call("BackgroundService.startObserving", params, result_ -> null);
        }
        /**
         * Enables event updates for the service.
         * @param request request parameters
         * @return a stage completing when the command completes
         */
        public CompletionStage<Void> startObserving(StartObservingRequest request) {
            return client.call("BackgroundService.startObserving", request == null ? null : request.toMap(), result_ -> null);
        }
        /**
         * Disables event updates for the service.
         * @param service protocol value
         * @return a stage completing when the command completes
         */
        public CompletionStage<Void> stopObserving(BackgroundService.ServiceName service) {
            Map<String, Object> params = new LinkedHashMap<>();
            params.put("service", CdpObject.json(service));
            return client.call("BackgroundService.stopObserving", params, result_ -> null);
        }
        /**
         * Disables event updates for the service.
         * @param request request parameters
         * @return a stage completing when the command completes
         */
        public CompletionStage<Void> stopObserving(StopObservingRequest request) {
            return client.call("BackgroundService.stopObserving", request == null ? null : request.toMap(), result_ -> null);
        }
        /**
         * Set the recording state for the service.
         * @param shouldRecord protocol value
         * @param service protocol value
         * @return a stage completing when the command completes
         */
        public CompletionStage<Void> setRecording(boolean shouldRecord, BackgroundService.ServiceName service) {
            Map<String, Object> params = new LinkedHashMap<>();
            params.put("shouldRecord", CdpObject.json(shouldRecord));
            params.put("service", CdpObject.json(service));
            return client.call("BackgroundService.setRecording", params, result_ -> null);
        }
        /**
         * Set the recording state for the service.
         * @param request request parameters
         * @return a stage completing when the command completes
         */
        public CompletionStage<Void> setRecording(SetRecordingRequest request) {
            return client.call("BackgroundService.setRecording", request == null ? null : request.toMap(), result_ -> null);
        }
        /**
         * Clears all stored data for the service.
         * @param service protocol value
         * @return a stage completing when the command completes
         */
        public CompletionStage<Void> clearEvents(BackgroundService.ServiceName service) {
            Map<String, Object> params = new LinkedHashMap<>();
            params.put("service", CdpObject.json(service));
            return client.call("BackgroundService.clearEvents", params, result_ -> null);
        }
        /**
         * Clears all stored data for the service.
         * @param request request parameters
         * @return a stage completing when the command completes
         */
        public CompletionStage<Void> clearEvents(ClearEventsRequest request) {
            return client.call("BackgroundService.clearEvents", request == null ? null : request.toMap(), result_ -> null);
        }
        /**
         * Called when the recording state for the service has been updated.
         * @param handler event callback
         * @return a removable subscription
         */
        public CdpSubscription onRecordingStateChanged(Consumer<RecordingStateChangedEvent> handler) {
            return client.on("BackgroundService.recordingStateChanged", RecordingStateChangedEvent::fromMap, handler);
        }
        /**
         * Called with all existing backgroundServiceEvents when enabled, and all new events afterwards if enabled and recording.
         * @param handler event callback
         * @return a removable subscription
         */
        public CdpSubscription onBackgroundServiceEventReceived(Consumer<BackgroundServiceEventReceivedEvent> handler) {
            return client.on("BackgroundService.backgroundServiceEventReceived", BackgroundServiceEventReceivedEvent::fromMap, handler);
        }
    }
}
