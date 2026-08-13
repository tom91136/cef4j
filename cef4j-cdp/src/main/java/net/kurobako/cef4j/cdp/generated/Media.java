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
 * This domain allows detailed inspection of media elements.
 * <p><b>Experimental:</b> this part of CDP may change without notice.
 * @see <a href="https://chromium.googlesource.com/chromium/src/+/refs/tags/146.0.7680.165/third_party/blink/public/devtools_protocol/domains/Media.pdl">Pinned protocol source</a>
 */
@SuppressWarnings({"JavaLangClash", "UnusedMethod"})
public final class Media {
    private Media() {}
    @Nullable private static Long numberAsLong(@Nullable Object value) { return value == null ? null : ((Number) value).longValue(); }
    @Nullable private static Double numberAsDouble(@Nullable Object value) { return value == null ? null : ((Number) value).doubleValue(); }
    /**
     * Have one type per entry in MediaLogRecord::Type Corresponds to kMessage
     */
    public static final class PlayerMessage extends CdpObject {
        private PlayerMessage(Map<String, Object> values) { super(values); }
        @Nullable public static PlayerMessage fromMap(@Nullable Map<String, Object> values) {
            return values == null ? null : new PlayerMessage(values);
        }
        public static Builder builder() { return new Builder(); }
        /**
         * Keep in sync with MediaLogMessageLevel We are currently keeping the message level &#x27;error&#x27; separate from the PlayerError type because right now they represent different things, this one being a DVLOG(ERROR) style log message that gets printed based on what log level is selected in the UI, and the other is a representation of a media::PipelineStatus object. Soon however we&#x27;re going to be moving away from using PipelineStatus for errors and introducing a new error type which should hopefully let us integrate the error log level into the PlayerError type.
         * @return the protocol field value
         */
        @Nullable public String level() {
            return (String) value("level");
        }
        /**
         * Keep in sync with MediaLogMessageLevel We are currently keeping the message level &#x27;error&#x27; separate from the PlayerError type because right now they represent different things, this one being a DVLOG(ERROR) style log message that gets printed based on what log level is selected in the UI, and the other is a representation of a media::PipelineStatus object. Soon however we&#x27;re going to be moving away from using PipelineStatus for errors and introducing a new error type which should hopefully let us integrate the error log level into the PlayerError type.
         */
        public static final class LevelValues {
            private LevelValues() {}
            public static final String ERROR = "error";
            public static final String WARNING = "warning";
            public static final String INFO = "info";
            public static final String DEBUG = "debug";
        }
        /**
         * Returns the message field.
         * @return the protocol field value
         */
        @Nullable public String message() {
            return (String) value("message");
        }
        public static final class Builder {
            private final Map<String, Object> values = new LinkedHashMap<>();
            /**
             * Keep in sync with MediaLogMessageLevel We are currently keeping the message level &#x27;error&#x27; separate from the PlayerError type because right now they represent different things, this one being a DVLOG(ERROR) style log message that gets printed based on what log level is selected in the UI, and the other is a representation of a media::PipelineStatus object. Soon however we&#x27;re going to be moving away from using PipelineStatus for errors and introducing a new error type which should hopefully let us integrate the error log level into the PlayerError type.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder level(@Nullable String value) {
                if (value == null) values.remove("level");
                else values.put("level", jsonValue(value));
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
            public PlayerMessage build() {
                if (!values.containsKey("level")) throw new IllegalStateException("Missing required CDP field: level");
                if (!values.containsKey("message")) throw new IllegalStateException("Missing required CDP field: message");
                return new PlayerMessage(values);
            }
        }
    }
    /**
     * Corresponds to kMediaPropertyChange
     */
    public static final class PlayerProperty extends CdpObject {
        private PlayerProperty(Map<String, Object> values) { super(values); }
        @Nullable public static PlayerProperty fromMap(@Nullable Map<String, Object> values) {
            return values == null ? null : new PlayerProperty(values);
        }
        public static Builder builder() { return new Builder(); }
        /**
         * Returns the name field.
         * @return the protocol field value
         */
        @Nullable public String name() {
            return (String) value("name");
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
             * Sets the name field.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder name(@Nullable String value) {
                if (value == null) values.remove("name");
                else values.put("name", jsonValue(value));
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
            public PlayerProperty build() {
                if (!values.containsKey("name")) throw new IllegalStateException("Missing required CDP field: name");
                if (!values.containsKey("value")) throw new IllegalStateException("Missing required CDP field: value");
                return new PlayerProperty(values);
            }
        }
    }
    /**
     * Corresponds to kMediaEventTriggered
     */
    public static final class PlayerEvent extends CdpObject {
        private PlayerEvent(Map<String, Object> values) { super(values); }
        @Nullable public static PlayerEvent fromMap(@Nullable Map<String, Object> values) {
            return values == null ? null : new PlayerEvent(values);
        }
        public static Builder builder() { return new Builder(); }
        /**
         * Returns the timestamp field.
         * @return the protocol field value
         */
        @Nullable public Double timestamp() {
            return numberAsDouble(value("timestamp"));
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
             * Sets the timestamp field.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder timestamp(@Nullable Double value) {
                if (value == null) values.remove("timestamp");
                else values.put("timestamp", jsonValue(value));
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
            public PlayerEvent build() {
                if (!values.containsKey("timestamp")) throw new IllegalStateException("Missing required CDP field: timestamp");
                if (!values.containsKey("value")) throw new IllegalStateException("Missing required CDP field: value");
                return new PlayerEvent(values);
            }
        }
    }
    /**
     * Represents logged source line numbers reported in an error. NOTE: file and line are from chromium c++ implementation code, not js.
     */
    public static final class PlayerErrorSourceLocation extends CdpObject {
        private PlayerErrorSourceLocation(Map<String, Object> values) { super(values); }
        @Nullable public static PlayerErrorSourceLocation fromMap(@Nullable Map<String, Object> values) {
            return values == null ? null : new PlayerErrorSourceLocation(values);
        }
        public static Builder builder() { return new Builder(); }
        /**
         * Returns the file field.
         * @return the protocol field value
         */
        @Nullable public String file() {
            return (String) value("file");
        }
        /**
         * Returns the line field.
         * @return the protocol field value
         */
        @Nullable public Long line() {
            return numberAsLong(value("line"));
        }
        public static final class Builder {
            private final Map<String, Object> values = new LinkedHashMap<>();
            /**
             * Sets the file field.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder file(@Nullable String value) {
                if (value == null) values.remove("file");
                else values.put("file", jsonValue(value));
                return this;
            }
            /**
             * Sets the line field.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder line(@Nullable Long value) {
                if (value == null) values.remove("line");
                else values.put("line", jsonValue(value));
                return this;
            }
            public PlayerErrorSourceLocation build() {
                if (!values.containsKey("file")) throw new IllegalStateException("Missing required CDP field: file");
                if (!values.containsKey("line")) throw new IllegalStateException("Missing required CDP field: line");
                return new PlayerErrorSourceLocation(values);
            }
        }
    }
    /**
     * Corresponds to kMediaError
     */
    public static final class PlayerError extends CdpObject {
        private PlayerError(Map<String, Object> values) { super(values); }
        @Nullable public static PlayerError fromMap(@Nullable Map<String, Object> values) {
            return values == null ? null : new PlayerError(values);
        }
        public static Builder builder() { return new Builder(); }
        /**
         * Returns the errorType field.
         * @return the protocol field value
         */
        @Nullable public String errorType() {
            return (String) value("errorType");
        }
        /**
         * Code is the numeric enum entry for a specific set of error codes, such as PipelineStatusCodes in media/base/pipeline_status.h
         * @return the protocol field value
         */
        @Nullable public Long code() {
            return numberAsLong(value("code"));
        }
        /**
         * A trace of where this error was caused / where it passed through.
         * @return the protocol field value
         */
        @Nullable public java.util.List<Media.PlayerErrorSourceLocation> stack() {
            return list(value("stack"), element0 -> Media.PlayerErrorSourceLocation.fromMap(objectMap(element0)));
        }
        /**
         * Errors potentially have a root cause error, ie, a DecoderError might be caused by an WindowsError
         * @return the protocol field value
         */
        @Nullable public java.util.List<Media.PlayerError> cause() {
            return list(value("cause"), element0 -> Media.PlayerError.fromMap(objectMap(element0)));
        }
        /**
         * Extra data attached to an error, such as an HRESULT, Video Codec, etc.
         * @return the protocol field value
         */
        @Nullable public java.util.Map<String, Object> data() {
            return objectMap(value("data"));
        }
        public static final class Builder {
            private final Map<String, Object> values = new LinkedHashMap<>();
            /**
             * Sets the errorType field.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder errorType(@Nullable String value) {
                if (value == null) values.remove("errorType");
                else values.put("errorType", jsonValue(value));
                return this;
            }
            /**
             * Code is the numeric enum entry for a specific set of error codes, such as PipelineStatusCodes in media/base/pipeline_status.h
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder code(@Nullable Long value) {
                if (value == null) values.remove("code");
                else values.put("code", jsonValue(value));
                return this;
            }
            /**
             * A trace of where this error was caused / where it passed through.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder stack(@Nullable java.util.List<Media.PlayerErrorSourceLocation> value) {
                if (value == null) values.remove("stack");
                else values.put("stack", jsonValue(value));
                return this;
            }
            /**
             * Errors potentially have a root cause error, ie, a DecoderError might be caused by an WindowsError
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder cause(@Nullable java.util.List<Media.PlayerError> value) {
                if (value == null) values.remove("cause");
                else values.put("cause", jsonValue(value));
                return this;
            }
            /**
             * Extra data attached to an error, such as an HRESULT, Video Codec, etc.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder data(@Nullable java.util.Map<String, Object> value) {
                if (value == null) values.remove("data");
                else values.put("data", jsonValue(value));
                return this;
            }
            public PlayerError build() {
                if (!values.containsKey("errorType")) throw new IllegalStateException("Missing required CDP field: errorType");
                if (!values.containsKey("code")) throw new IllegalStateException("Missing required CDP field: code");
                if (!values.containsKey("stack")) throw new IllegalStateException("Missing required CDP field: stack");
                if (!values.containsKey("cause")) throw new IllegalStateException("Missing required CDP field: cause");
                if (!values.containsKey("data")) throw new IllegalStateException("Missing required CDP field: data");
                return new PlayerError(values);
            }
        }
    }
    /**
     */
    public static final class Player extends CdpObject {
        private Player(Map<String, Object> values) { super(values); }
        @Nullable public static Player fromMap(@Nullable Map<String, Object> values) {
            return values == null ? null : new Player(values);
        }
        public static Builder builder() { return new Builder(); }
        /**
         * Returns the playerId field.
         * @return the protocol field value
         */
        @Nullable public String playerId() {
            return (String) value("playerId");
        }
        /**
         * Returns the domNodeId field.
         * @return the protocol field value
         */
        @Nullable public Long domNodeId() {
            return numberAsLong(value("domNodeId"));
        }
        public static final class Builder {
            private final Map<String, Object> values = new LinkedHashMap<>();
            /**
             * Sets the playerId field.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder playerId(@Nullable String value) {
                if (value == null) values.remove("playerId");
                else values.put("playerId", jsonValue(value));
                return this;
            }
            /**
             * Sets the domNodeId field.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder domNodeId(@Nullable Long value) {
                if (value == null) values.remove("domNodeId");
                else values.put("domNodeId", jsonValue(value));
                return this;
            }
            public Player build() {
                if (!values.containsKey("playerId")) throw new IllegalStateException("Missing required CDP field: playerId");
                return new Player(values);
            }
        }
    }
    /**
     * Enables the Media domain
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
     * Enables the Media domain
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
     * Disables the Media domain.
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
     * Disables the Media domain.
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
     * This can be called multiple times, and can be used to set / override / remove player properties. A null propValue indicates removal.
     */
    public static final class PlayerPropertiesChangedEvent extends CdpObject {
        private PlayerPropertiesChangedEvent(Map<String, Object> values) { super(values); }
        @Nullable public static PlayerPropertiesChangedEvent fromMap(@Nullable Map<String, Object> values) {
            return values == null ? null : new PlayerPropertiesChangedEvent(values);
        }
        public static Builder builder() { return new Builder(); }
        /**
         * Returns the playerId field.
         * @return the protocol field value
         */
        @Nullable public String playerId() {
            return (String) value("playerId");
        }
        /**
         * Returns the properties field.
         * @return the protocol field value
         */
        @Nullable public java.util.List<Media.PlayerProperty> properties() {
            return list(value("properties"), element0 -> Media.PlayerProperty.fromMap(objectMap(element0)));
        }
        public static final class Builder {
            private final Map<String, Object> values = new LinkedHashMap<>();
            /**
             * Sets the playerId field.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder playerId(@Nullable String value) {
                if (value == null) values.remove("playerId");
                else values.put("playerId", jsonValue(value));
                return this;
            }
            /**
             * Sets the properties field.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder properties(@Nullable java.util.List<Media.PlayerProperty> value) {
                if (value == null) values.remove("properties");
                else values.put("properties", jsonValue(value));
                return this;
            }
            public PlayerPropertiesChangedEvent build() {
                if (!values.containsKey("playerId")) throw new IllegalStateException("Missing required CDP field: playerId");
                if (!values.containsKey("properties")) throw new IllegalStateException("Missing required CDP field: properties");
                return new PlayerPropertiesChangedEvent(values);
            }
        }
    }
    /**
     * Send events as a list, allowing them to be batched on the browser for less congestion. If batched, events must ALWAYS be in chronological order.
     */
    public static final class PlayerEventsAddedEvent extends CdpObject {
        private PlayerEventsAddedEvent(Map<String, Object> values) { super(values); }
        @Nullable public static PlayerEventsAddedEvent fromMap(@Nullable Map<String, Object> values) {
            return values == null ? null : new PlayerEventsAddedEvent(values);
        }
        public static Builder builder() { return new Builder(); }
        /**
         * Returns the playerId field.
         * @return the protocol field value
         */
        @Nullable public String playerId() {
            return (String) value("playerId");
        }
        /**
         * Returns the events field.
         * @return the protocol field value
         */
        @Nullable public java.util.List<Media.PlayerEvent> events() {
            return list(value("events"), element0 -> Media.PlayerEvent.fromMap(objectMap(element0)));
        }
        public static final class Builder {
            private final Map<String, Object> values = new LinkedHashMap<>();
            /**
             * Sets the playerId field.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder playerId(@Nullable String value) {
                if (value == null) values.remove("playerId");
                else values.put("playerId", jsonValue(value));
                return this;
            }
            /**
             * Sets the events field.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder events(@Nullable java.util.List<Media.PlayerEvent> value) {
                if (value == null) values.remove("events");
                else values.put("events", jsonValue(value));
                return this;
            }
            public PlayerEventsAddedEvent build() {
                if (!values.containsKey("playerId")) throw new IllegalStateException("Missing required CDP field: playerId");
                if (!values.containsKey("events")) throw new IllegalStateException("Missing required CDP field: events");
                return new PlayerEventsAddedEvent(values);
            }
        }
    }
    /**
     * Send a list of any messages that need to be delivered.
     */
    public static final class PlayerMessagesLoggedEvent extends CdpObject {
        private PlayerMessagesLoggedEvent(Map<String, Object> values) { super(values); }
        @Nullable public static PlayerMessagesLoggedEvent fromMap(@Nullable Map<String, Object> values) {
            return values == null ? null : new PlayerMessagesLoggedEvent(values);
        }
        public static Builder builder() { return new Builder(); }
        /**
         * Returns the playerId field.
         * @return the protocol field value
         */
        @Nullable public String playerId() {
            return (String) value("playerId");
        }
        /**
         * Returns the messages field.
         * @return the protocol field value
         */
        @Nullable public java.util.List<Media.PlayerMessage> messages() {
            return list(value("messages"), element0 -> Media.PlayerMessage.fromMap(objectMap(element0)));
        }
        public static final class Builder {
            private final Map<String, Object> values = new LinkedHashMap<>();
            /**
             * Sets the playerId field.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder playerId(@Nullable String value) {
                if (value == null) values.remove("playerId");
                else values.put("playerId", jsonValue(value));
                return this;
            }
            /**
             * Sets the messages field.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder messages(@Nullable java.util.List<Media.PlayerMessage> value) {
                if (value == null) values.remove("messages");
                else values.put("messages", jsonValue(value));
                return this;
            }
            public PlayerMessagesLoggedEvent build() {
                if (!values.containsKey("playerId")) throw new IllegalStateException("Missing required CDP field: playerId");
                if (!values.containsKey("messages")) throw new IllegalStateException("Missing required CDP field: messages");
                return new PlayerMessagesLoggedEvent(values);
            }
        }
    }
    /**
     * Send a list of any errors that need to be delivered.
     */
    public static final class PlayerErrorsRaisedEvent extends CdpObject {
        private PlayerErrorsRaisedEvent(Map<String, Object> values) { super(values); }
        @Nullable public static PlayerErrorsRaisedEvent fromMap(@Nullable Map<String, Object> values) {
            return values == null ? null : new PlayerErrorsRaisedEvent(values);
        }
        public static Builder builder() { return new Builder(); }
        /**
         * Returns the playerId field.
         * @return the protocol field value
         */
        @Nullable public String playerId() {
            return (String) value("playerId");
        }
        /**
         * Returns the errors field.
         * @return the protocol field value
         */
        @Nullable public java.util.List<Media.PlayerError> errors() {
            return list(value("errors"), element0 -> Media.PlayerError.fromMap(objectMap(element0)));
        }
        public static final class Builder {
            private final Map<String, Object> values = new LinkedHashMap<>();
            /**
             * Sets the playerId field.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder playerId(@Nullable String value) {
                if (value == null) values.remove("playerId");
                else values.put("playerId", jsonValue(value));
                return this;
            }
            /**
             * Sets the errors field.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder errors(@Nullable java.util.List<Media.PlayerError> value) {
                if (value == null) values.remove("errors");
                else values.put("errors", jsonValue(value));
                return this;
            }
            public PlayerErrorsRaisedEvent build() {
                if (!values.containsKey("playerId")) throw new IllegalStateException("Missing required CDP field: playerId");
                if (!values.containsKey("errors")) throw new IllegalStateException("Missing required CDP field: errors");
                return new PlayerErrorsRaisedEvent(values);
            }
        }
    }
    /**
     * Called whenever a player is created, or when a new agent joins and receives a list of active players. If an agent is restored, it will receive one event for each active player.
     */
    public static final class PlayerCreatedEvent extends CdpObject {
        private PlayerCreatedEvent(Map<String, Object> values) { super(values); }
        @Nullable public static PlayerCreatedEvent fromMap(@Nullable Map<String, Object> values) {
            return values == null ? null : new PlayerCreatedEvent(values);
        }
        public static Builder builder() { return new Builder(); }
        /**
         * Returns the player field.
         * @return the protocol field value
         */
        @Nullable public Media.Player player() {
            return Media.Player.fromMap(objectMap(value("player")));
        }
        public static final class Builder {
            private final Map<String, Object> values = new LinkedHashMap<>();
            /**
             * Sets the player field.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder player(@Nullable Media.Player value) {
                if (value == null) values.remove("player");
                else values.put("player", jsonValue(value));
                return this;
            }
            public PlayerCreatedEvent build() {
                if (!values.containsKey("player")) throw new IllegalStateException("Missing required CDP field: player");
                return new PlayerCreatedEvent(values);
            }
        }
    }
    public static final class Client {
        private final CdpClient client;
        public Client(CdpClient client) { this.client = client; }
        /**
         * Enables the Media domain
         * @return a stage completing with the command result
         */
        public CompletionStage<EnableResult> enable() {
            return client.call("Media.enable", null, EnableResult::fromMap);
        }
        /**
         * Disables the Media domain.
         * @return a stage completing with the command result
         */
        public CompletionStage<DisableResult> disable() {
            return client.call("Media.disable", null, DisableResult::fromMap);
        }
        /**
         * This can be called multiple times, and can be used to set / override / remove player properties. A null propValue indicates removal.
         * @param handler event callback
         * @return a removable subscription
         */
        public CdpSubscription onPlayerPropertiesChanged(Consumer<PlayerPropertiesChangedEvent> handler) {
            return client.on("Media.playerPropertiesChanged", PlayerPropertiesChangedEvent::fromMap, handler);
        }
        /**
         * Send events as a list, allowing them to be batched on the browser for less congestion. If batched, events must ALWAYS be in chronological order.
         * @param handler event callback
         * @return a removable subscription
         */
        public CdpSubscription onPlayerEventsAdded(Consumer<PlayerEventsAddedEvent> handler) {
            return client.on("Media.playerEventsAdded", PlayerEventsAddedEvent::fromMap, handler);
        }
        /**
         * Send a list of any messages that need to be delivered.
         * @param handler event callback
         * @return a removable subscription
         */
        public CdpSubscription onPlayerMessagesLogged(Consumer<PlayerMessagesLoggedEvent> handler) {
            return client.on("Media.playerMessagesLogged", PlayerMessagesLoggedEvent::fromMap, handler);
        }
        /**
         * Send a list of any errors that need to be delivered.
         * @param handler event callback
         * @return a removable subscription
         */
        public CdpSubscription onPlayerErrorsRaised(Consumer<PlayerErrorsRaisedEvent> handler) {
            return client.on("Media.playerErrorsRaised", PlayerErrorsRaisedEvent::fromMap, handler);
        }
        /**
         * Called whenever a player is created, or when a new agent joins and receives a list of active players. If an agent is restored, it will receive one event for each active player.
         * @param handler event callback
         * @return a removable subscription
         */
        public CdpSubscription onPlayerCreated(Consumer<PlayerCreatedEvent> handler) {
            return client.on("Media.playerCreated", PlayerCreatedEvent::fromMap, handler);
        }
    }
}
