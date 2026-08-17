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
 * Defines events for background web platform features.
 * <p><b>Experimental:</b> this part of CDP may change without notice.
 * @see <a href="https://chromium.googlesource.com/chromium/src/+/refs/tags/150.0.7871.213/third_party/blink/public/devtools_protocol/domains/BackgroundService.pdl">Pinned protocol source</a>
 */
@SuppressWarnings({"EscapedEntity", "JavaLangClash", "MissingSummary", "UnusedMethod"})
public final class BackgroundService {
    private BackgroundService() {}
    @Nullable private static Long numberAsLong(@Nullable Object value) { return value == null ? null : ((Number) value).longValue(); }
    @Nullable private static Double numberAsDouble(@Nullable Object value) { return value == null ? null : ((Number) value).doubleValue(); }
    /**
     * The Background Service that will be associated with the commands/events. Every Background Service operates independently, but they share the same API.
     */
    public static final class ServiceName {
        private ServiceName() {}
        public static final String BACKGROUNDFETCH = "backgroundFetch";
        public static final String BACKGROUNDSYNC = "backgroundSync";
        public static final String PUSHMESSAGING = "pushMessaging";
        public static final String NOTIFICATIONS = "notifications";
        public static final String PAYMENTHANDLER = "paymentHandler";
        public static final String PERIODICBACKGROUNDSYNC = "periodicBackgroundSync";
    }
    /**
     * A key-value pair for additional event information to pass along.
     */
    public static final class EventMetadata extends CdpObject {
        private EventMetadata(Map<String, Object> values) { super(values); }
        @Nullable public static EventMetadata fromMap(@Nullable Map<String, Object> values) {
            return values == null ? null : new EventMetadata(values);
        }
        public static Builder builder() { return new Builder(); }
        /**
         * Returns the key field.
         * @return the protocol field value
         */
        @Nullable public String key() {
            return (String) value("key");
        }
        /**
         * Returns the value field.
         * @return the protocol field value
         */
        @Nullable public String value() {
            return (String) value("value");
        }
        public static final class Builder {
            private final Map<String, Object> values = new LinkedHashMap<>();
            /**
             * Sets the key field.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder key(@Nullable String value) {
                if (value == null) values.remove("key");
                else values.put("key", jsonValue(value));
                return this;
            }
            /**
             * Sets the value field.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder value(@Nullable String value) {
                if (value == null) values.remove("value");
                else values.put("value", jsonValue(value));
                return this;
            }
            public EventMetadata build() {
                if (!values.containsKey("key")) throw new IllegalStateException("Missing required CDP field: key");
                if (!values.containsKey("value")) throw new IllegalStateException("Missing required CDP field: value");
                return new EventMetadata(values);
            }
        }
    }
    /**
     */
    public static final class BackgroundServiceEvent extends CdpObject {
        private BackgroundServiceEvent(Map<String, Object> values) { super(values); }
        @Nullable public static BackgroundServiceEvent fromMap(@Nullable Map<String, Object> values) {
            return values == null ? null : new BackgroundServiceEvent(values);
        }
        public static Builder builder() { return new Builder(); }
        /**
         * Timestamp of the event (in seconds).
         * @return the protocol field value
         */
        @Nullable public Double timestamp() {
            return numberAsDouble(value("timestamp"));
        }
        /**
         * The origin this event belongs to.
         * @return the protocol field value
         */
        @Nullable public String origin() {
            return (String) value("origin");
        }
        /**
         * The Service Worker ID that initiated the event.
         * @return the protocol field value
         */
        @Nullable public String serviceWorkerRegistrationId() {
            return (String) value("serviceWorkerRegistrationId");
        }
        /**
         * The Background Service this event belongs to.
         * @return the protocol field value
         */
        @Nullable public String service() {
            return (String) value("service");
        }
        /**
         * A description of the event.
         * @return the protocol field value
         */
        @Nullable public String eventName() {
            return (String) value("eventName");
        }
        /**
         * An identifier that groups related events together.
         * @return the protocol field value
         */
        @Nullable public String instanceId() {
            return (String) value("instanceId");
        }
        /**
         * A list of event-specific information.
         * @return the protocol field value
         */
        @Nullable public java.util.List<BackgroundService.EventMetadata> eventMetadata() {
            return list(value("eventMetadata"), element0 -> BackgroundService.EventMetadata.fromMap(objectMap(element0)));
        }
        /**
         * Storage key this event belongs to.
         * @return the protocol field value
         */
        @Nullable public String storageKey() {
            return (String) value("storageKey");
        }
        public static final class Builder {
            private final Map<String, Object> values = new LinkedHashMap<>();
            /**
             * Timestamp of the event (in seconds).
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder timestamp(@Nullable Double value) {
                if (value == null) values.remove("timestamp");
                else values.put("timestamp", jsonValue(value));
                return this;
            }
            /**
             * The origin this event belongs to.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder origin(@Nullable String value) {
                if (value == null) values.remove("origin");
                else values.put("origin", jsonValue(value));
                return this;
            }
            /**
             * The Service Worker ID that initiated the event.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder serviceWorkerRegistrationId(@Nullable String value) {
                if (value == null) values.remove("serviceWorkerRegistrationId");
                else values.put("serviceWorkerRegistrationId", jsonValue(value));
                return this;
            }
            /**
             * The Background Service this event belongs to.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder service(@Nullable String value) {
                if (value == null) values.remove("service");
                else values.put("service", jsonValue(value));
                return this;
            }
            /**
             * A description of the event.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder eventName(@Nullable String value) {
                if (value == null) values.remove("eventName");
                else values.put("eventName", jsonValue(value));
                return this;
            }
            /**
             * An identifier that groups related events together.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder instanceId(@Nullable String value) {
                if (value == null) values.remove("instanceId");
                else values.put("instanceId", jsonValue(value));
                return this;
            }
            /**
             * A list of event-specific information.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder eventMetadata(@Nullable java.util.List<BackgroundService.EventMetadata> value) {
                if (value == null) values.remove("eventMetadata");
                else values.put("eventMetadata", jsonValue(value));
                return this;
            }
            /**
             * Storage key this event belongs to.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder storageKey(@Nullable String value) {
                if (value == null) values.remove("storageKey");
                else values.put("storageKey", jsonValue(value));
                return this;
            }
            public BackgroundServiceEvent build() {
                if (!values.containsKey("timestamp")) throw new IllegalStateException("Missing required CDP field: timestamp");
                if (!values.containsKey("origin")) throw new IllegalStateException("Missing required CDP field: origin");
                if (!values.containsKey("serviceWorkerRegistrationId")) throw new IllegalStateException("Missing required CDP field: serviceWorkerRegistrationId");
                if (!values.containsKey("service")) throw new IllegalStateException("Missing required CDP field: service");
                if (!values.containsKey("eventName")) throw new IllegalStateException("Missing required CDP field: eventName");
                if (!values.containsKey("instanceId")) throw new IllegalStateException("Missing required CDP field: instanceId");
                if (!values.containsKey("eventMetadata")) throw new IllegalStateException("Missing required CDP field: eventMetadata");
                if (!values.containsKey("storageKey")) throw new IllegalStateException("Missing required CDP field: storageKey");
                return new BackgroundServiceEvent(values);
            }
        }
    }
    /**
     * Enables event updates for the service.
     */
    public static final class StartObservingParams extends CdpObject {
        private StartObservingParams(Map<String, Object> values) { super(values); }
        @Nullable public static StartObservingParams fromMap(@Nullable Map<String, Object> values) {
            return values == null ? null : new StartObservingParams(values);
        }
        public static Builder builder() { return new Builder(); }
        /**
         * Returns the service field.
         * @return the protocol field value
         */
        @Nullable public String service() {
            return (String) value("service");
        }
        public static final class Builder {
            private final Map<String, Object> values = new LinkedHashMap<>();
            /**
             * Sets the service field.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder service(@Nullable String value) {
                if (value == null) values.remove("service");
                else values.put("service", jsonValue(value));
                return this;
            }
            public StartObservingParams build() {
                if (!values.containsKey("service")) throw new IllegalStateException("Missing required CDP field: service");
                return new StartObservingParams(values);
            }
        }
    }
    /**
     * Enables event updates for the service.
     */
    public static final class StartObservingResult extends CdpObject {
        private StartObservingResult(Map<String, Object> values) { super(values); }
        @Nullable public static StartObservingResult fromMap(@Nullable Map<String, Object> values) {
            return values == null ? null : new StartObservingResult(values);
        }
        public static Builder builder() { return new Builder(); }
        public static final class Builder {
            private final Map<String, Object> values = new LinkedHashMap<>();
            public StartObservingResult build() {
                return new StartObservingResult(values);
            }
        }
    }
    /**
     * Disables event updates for the service.
     */
    public static final class StopObservingParams extends CdpObject {
        private StopObservingParams(Map<String, Object> values) { super(values); }
        @Nullable public static StopObservingParams fromMap(@Nullable Map<String, Object> values) {
            return values == null ? null : new StopObservingParams(values);
        }
        public static Builder builder() { return new Builder(); }
        /**
         * Returns the service field.
         * @return the protocol field value
         */
        @Nullable public String service() {
            return (String) value("service");
        }
        public static final class Builder {
            private final Map<String, Object> values = new LinkedHashMap<>();
            /**
             * Sets the service field.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder service(@Nullable String value) {
                if (value == null) values.remove("service");
                else values.put("service", jsonValue(value));
                return this;
            }
            public StopObservingParams build() {
                if (!values.containsKey("service")) throw new IllegalStateException("Missing required CDP field: service");
                return new StopObservingParams(values);
            }
        }
    }
    /**
     * Disables event updates for the service.
     */
    public static final class StopObservingResult extends CdpObject {
        private StopObservingResult(Map<String, Object> values) { super(values); }
        @Nullable public static StopObservingResult fromMap(@Nullable Map<String, Object> values) {
            return values == null ? null : new StopObservingResult(values);
        }
        public static Builder builder() { return new Builder(); }
        public static final class Builder {
            private final Map<String, Object> values = new LinkedHashMap<>();
            public StopObservingResult build() {
                return new StopObservingResult(values);
            }
        }
    }
    /**
     * Set the recording state for the service.
     */
    public static final class SetRecordingParams extends CdpObject {
        private SetRecordingParams(Map<String, Object> values) { super(values); }
        @Nullable public static SetRecordingParams fromMap(@Nullable Map<String, Object> values) {
            return values == null ? null : new SetRecordingParams(values);
        }
        public static Builder builder() { return new Builder(); }
        /**
         * Returns the shouldRecord field.
         * @return the protocol field value
         */
        @Nullable public Boolean shouldRecord() {
            return (Boolean) value("shouldRecord");
        }
        /**
         * Returns the service field.
         * @return the protocol field value
         */
        @Nullable public String service() {
            return (String) value("service");
        }
        public static final class Builder {
            private final Map<String, Object> values = new LinkedHashMap<>();
            /**
             * Sets the shouldRecord field.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder shouldRecord(@Nullable Boolean value) {
                if (value == null) values.remove("shouldRecord");
                else values.put("shouldRecord", jsonValue(value));
                return this;
            }
            /**
             * Sets the service field.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder service(@Nullable String value) {
                if (value == null) values.remove("service");
                else values.put("service", jsonValue(value));
                return this;
            }
            public SetRecordingParams build() {
                if (!values.containsKey("shouldRecord")) throw new IllegalStateException("Missing required CDP field: shouldRecord");
                if (!values.containsKey("service")) throw new IllegalStateException("Missing required CDP field: service");
                return new SetRecordingParams(values);
            }
        }
    }
    /**
     * Set the recording state for the service.
     */
    public static final class SetRecordingResult extends CdpObject {
        private SetRecordingResult(Map<String, Object> values) { super(values); }
        @Nullable public static SetRecordingResult fromMap(@Nullable Map<String, Object> values) {
            return values == null ? null : new SetRecordingResult(values);
        }
        public static Builder builder() { return new Builder(); }
        public static final class Builder {
            private final Map<String, Object> values = new LinkedHashMap<>();
            public SetRecordingResult build() {
                return new SetRecordingResult(values);
            }
        }
    }
    /**
     * Clears all stored data for the service.
     */
    public static final class ClearEventsParams extends CdpObject {
        private ClearEventsParams(Map<String, Object> values) { super(values); }
        @Nullable public static ClearEventsParams fromMap(@Nullable Map<String, Object> values) {
            return values == null ? null : new ClearEventsParams(values);
        }
        public static Builder builder() { return new Builder(); }
        /**
         * Returns the service field.
         * @return the protocol field value
         */
        @Nullable public String service() {
            return (String) value("service");
        }
        public static final class Builder {
            private final Map<String, Object> values = new LinkedHashMap<>();
            /**
             * Sets the service field.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder service(@Nullable String value) {
                if (value == null) values.remove("service");
                else values.put("service", jsonValue(value));
                return this;
            }
            public ClearEventsParams build() {
                if (!values.containsKey("service")) throw new IllegalStateException("Missing required CDP field: service");
                return new ClearEventsParams(values);
            }
        }
    }
    /**
     * Clears all stored data for the service.
     */
    public static final class ClearEventsResult extends CdpObject {
        private ClearEventsResult(Map<String, Object> values) { super(values); }
        @Nullable public static ClearEventsResult fromMap(@Nullable Map<String, Object> values) {
            return values == null ? null : new ClearEventsResult(values);
        }
        public static Builder builder() { return new Builder(); }
        public static final class Builder {
            private final Map<String, Object> values = new LinkedHashMap<>();
            public ClearEventsResult build() {
                return new ClearEventsResult(values);
            }
        }
    }
    /**
     * Called when the recording state for the service has been updated.
     */
    public static final class RecordingStateChangedEvent extends CdpObject {
        private RecordingStateChangedEvent(Map<String, Object> values) { super(values); }
        @Nullable public static RecordingStateChangedEvent fromMap(@Nullable Map<String, Object> values) {
            return values == null ? null : new RecordingStateChangedEvent(values);
        }
        public static Builder builder() { return new Builder(); }
        /**
         * Returns the isRecording field.
         * @return the protocol field value
         */
        @Nullable public Boolean isRecording() {
            return (Boolean) value("isRecording");
        }
        /**
         * Returns the service field.
         * @return the protocol field value
         */
        @Nullable public String service() {
            return (String) value("service");
        }
        public static final class Builder {
            private final Map<String, Object> values = new LinkedHashMap<>();
            /**
             * Sets the isRecording field.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder isRecording(@Nullable Boolean value) {
                if (value == null) values.remove("isRecording");
                else values.put("isRecording", jsonValue(value));
                return this;
            }
            /**
             * Sets the service field.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder service(@Nullable String value) {
                if (value == null) values.remove("service");
                else values.put("service", jsonValue(value));
                return this;
            }
            public RecordingStateChangedEvent build() {
                if (!values.containsKey("isRecording")) throw new IllegalStateException("Missing required CDP field: isRecording");
                if (!values.containsKey("service")) throw new IllegalStateException("Missing required CDP field: service");
                return new RecordingStateChangedEvent(values);
            }
        }
    }
    /**
     * Called with all existing backgroundServiceEvents when enabled, and all new events afterwards if enabled and recording.
     */
    public static final class BackgroundServiceEventReceivedEvent extends CdpObject {
        private BackgroundServiceEventReceivedEvent(Map<String, Object> values) { super(values); }
        @Nullable public static BackgroundServiceEventReceivedEvent fromMap(@Nullable Map<String, Object> values) {
            return values == null ? null : new BackgroundServiceEventReceivedEvent(values);
        }
        public static Builder builder() { return new Builder(); }
        /**
         * Returns the backgroundServiceEvent field.
         * @return the protocol field value
         */
        @Nullable public BackgroundService.BackgroundServiceEvent backgroundServiceEvent() {
            return BackgroundService.BackgroundServiceEvent.fromMap(objectMap(value("backgroundServiceEvent")));
        }
        public static final class Builder {
            private final Map<String, Object> values = new LinkedHashMap<>();
            /**
             * Sets the backgroundServiceEvent field.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder backgroundServiceEvent(@Nullable BackgroundService.BackgroundServiceEvent value) {
                if (value == null) values.remove("backgroundServiceEvent");
                else values.put("backgroundServiceEvent", jsonValue(value));
                return this;
            }
            public BackgroundServiceEventReceivedEvent build() {
                if (!values.containsKey("backgroundServiceEvent")) throw new IllegalStateException("Missing required CDP field: backgroundServiceEvent");
                return new BackgroundServiceEventReceivedEvent(values);
            }
        }
    }
    public static final class Client {
        private final CdpClient client;
        public Client(CdpClient client) { this.client = client; }
        /**
         * Enables event updates for the service.
         * @param params command parameters
         * @return a stage completing with the command result
         */
        public CompletionStage<StartObservingResult> startObserving(StartObservingParams params) {
            return client.call("BackgroundService.startObserving", params, StartObservingResult::fromMap);
        }
        /**
         * Disables event updates for the service.
         * @param params command parameters
         * @return a stage completing with the command result
         */
        public CompletionStage<StopObservingResult> stopObserving(StopObservingParams params) {
            return client.call("BackgroundService.stopObserving", params, StopObservingResult::fromMap);
        }
        /**
         * Set the recording state for the service.
         * @param params command parameters
         * @return a stage completing with the command result
         */
        public CompletionStage<SetRecordingResult> setRecording(SetRecordingParams params) {
            return client.call("BackgroundService.setRecording", params, SetRecordingResult::fromMap);
        }
        /**
         * Clears all stored data for the service.
         * @param params command parameters
         * @return a stage completing with the command result
         */
        public CompletionStage<ClearEventsResult> clearEvents(ClearEventsParams params) {
            return client.call("BackgroundService.clearEvents", params, ClearEventsResult::fromMap);
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
