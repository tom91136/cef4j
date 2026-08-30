// GENERATED - do not edit. Regenerate via: ./mvnw generate-sources -pl cef4j-cdp -Dcef.version=150.0.18+gdb11278+chromium-150.0.7871.213
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
 * This domain allows detailed inspection of media elements.
 * <p><b>Experimental:</b> this part of CDP may change without notice.
 * @see <a href="https://chromium.googlesource.com/chromium/src/+/refs/tags/150.0.7871.213/third_party/blink/public/devtools_protocol/domains/Media.pdl">Pinned protocol source</a>
 */
@Generated("./mvnw generate-sources -pl cef4j-cdp -Dcef.version=150.0.18+gdb11278+chromium-150.0.7871.213")
@SuppressWarnings({"EscapedEntity", "InvalidParam", "JavaLangClash", "MissingSummary", "Unchecked", "UnusedMethod"})
public final class Media {
    private Media() {}
    /**
     * Players will get an ID that is unique within the agent context.
     */
    public static final class PlayerId implements CdpValue<String> {
        public final String value;
        public PlayerId(@Nonnull String value) { this.value = java.util.Objects.requireNonNull(value); }
        @Nonnull public String value() { return value; }
        @Override public boolean equals(Object other) {
            if (this == other) return true;
            if (!(other instanceof PlayerId)) return false;
            return value.equals(((PlayerId) other).value);
        }
        @Override public int hashCode() { return value.hashCode(); }
        @Override public String toString() { return "PlayerId(" + value + ")"; }
    }
    /**
     * Tagged double wire value for Timestamp.
     */
    public static final class Timestamp implements CdpValue<Double> {
        public final double value;
        public Timestamp(double value) { this.value = value; }
        @Nonnull public Double value() { return value; }
        @Override public boolean equals(Object other) {
            if (this == other) return true;
            if (!(other instanceof Timestamp)) return false;
            return Double.compare(value, ((Timestamp) other).value) == 0;
        }
        @Override public int hashCode() { return Double.hashCode(value); }
        @Override public String toString() { return "Timestamp(" + value + ")"; }
    }
    /**
     * Have one type per entry in MediaLogRecord::Type Corresponds to kMessage
     */
    public static final class PlayerMessage extends CdpObject {
        public PlayerMessage() {}
        private PlayerMessage(Map<String, Object> values) { super(values); }
        public static PlayerMessage fromMap(Map<String, Object> values) {
            return new PlayerMessage(values);
        }
        /**
         * Keep in sync with MediaLogMessageLevel We are currently keeping the message level &#x27;error&#x27; separate from the PlayerError type because right now they represent different things, this one being a DVLOG(ERROR) style log message that gets printed based on what log level is selected in the UI, and the other is a representation of a media::PipelineStatus object. Soon however we&#x27;re going to be moving away from using PipelineStatus for errors and introducing a new error type which should hopefully let us integrate the error log level into the PlayerError type.
         */
        public enum LevelValues implements CdpValue<String> {
            ERROR("error"),
            WARNING("warning"),
            INFO("info"),
            DEBUG("debug");
            public final String value;
            LevelValues(String value) { this.value = value; }
            @Nonnull public String value() { return value; }
            public static LevelValues of(@Nonnull String value) {
                for (LevelValues constant : values()) {
                    if (constant.value.equals(value)) return constant;
                }
                throw new IllegalArgumentException("Unknown LevelValues value: " + value);
            }
        }
        /**
         * Keep in sync with MediaLogMessageLevel We are currently keeping the message level &#x27;error&#x27; separate from the PlayerError type because right now they represent different things, this one being a DVLOG(ERROR) style log message that gets printed based on what log level is selected in the UI, and the other is a representation of a media::PipelineStatus object. Soon however we&#x27;re going to be moving away from using PipelineStatus for errors and introducing a new error type which should hopefully let us integrate the error log level into the PlayerError type.
         * @return the protocol field value
         */
        public PlayerMessage.LevelValues level() {
            return PlayerMessage.LevelValues.of((String) require("level"));
        }
        /**
         * Returns the message field.
         * @return the protocol field value
         */
        public String message() {
            return (String) require("message");
        }
        /**
         * Keep in sync with MediaLogMessageLevel We are currently keeping the message level &#x27;error&#x27; separate from the PlayerError type because right now they represent different things, this one being a DVLOG(ERROR) style log message that gets printed based on what log level is selected in the UI, and the other is a representation of a media::PipelineStatus object. Soon however we&#x27;re going to be moving away from using PipelineStatus for errors and introducing a new error type which should hopefully let us integrate the error log level into the PlayerError type.
         * @param level field value
         * @return this model
         */
        public PlayerMessage level(PlayerMessage.LevelValues level) {
            set("level", level);
            return this;
        }
        /**
         * Sets the message field.
         * @param message field value
         * @return this model
         */
        public PlayerMessage message(String message) {
            set("message", message);
            return this;
        }
    }
    /**
     * Corresponds to kMediaPropertyChange
     */
    public static final class PlayerProperty extends CdpObject {
        public PlayerProperty() {}
        private PlayerProperty(Map<String, Object> values) { super(values); }
        public static PlayerProperty fromMap(Map<String, Object> values) {
            return new PlayerProperty(values);
        }
        /**
         * Returns the name field.
         * @return the protocol field value
         */
        public String name() {
            return (String) require("name");
        }
        /**
         * Returns the value field.
         * @return the protocol field value
         */
        public String value() {
            return (String) require("value");
        }
        /**
         * Sets the name field.
         * @param name field value
         * @return this model
         */
        public PlayerProperty name(String name) {
            set("name", name);
            return this;
        }
        /**
         * Sets the value field.
         * @param value field value
         * @return this model
         */
        public PlayerProperty value(String value) {
            set("value", value);
            return this;
        }
    }
    /**
     * Corresponds to kMediaEventTriggered
     */
    public static final class PlayerEvent extends CdpObject {
        public PlayerEvent() {}
        private PlayerEvent(Map<String, Object> values) { super(values); }
        public static PlayerEvent fromMap(Map<String, Object> values) {
            return new PlayerEvent(values);
        }
        /**
         * Returns the timestamp field.
         * @return the protocol field value
         */
        public Media.Timestamp timestamp() {
            return new Media.Timestamp(((Number) require("timestamp")).doubleValue());
        }
        /**
         * Returns the value field.
         * @return the protocol field value
         */
        public String value() {
            return (String) require("value");
        }
        /**
         * Sets the timestamp field.
         * @param timestamp field value
         * @return this model
         */
        public PlayerEvent timestamp(Media.Timestamp timestamp) {
            set("timestamp", timestamp);
            return this;
        }
        /**
         * Sets the value field.
         * @param value field value
         * @return this model
         */
        public PlayerEvent value(String value) {
            set("value", value);
            return this;
        }
    }
    /**
     * Represents logged source line numbers reported in an error. NOTE: file and line are from chromium c++ implementation code, not js.
     */
    public static final class PlayerErrorSourceLocation extends CdpObject {
        public PlayerErrorSourceLocation() {}
        private PlayerErrorSourceLocation(Map<String, Object> values) { super(values); }
        public static PlayerErrorSourceLocation fromMap(Map<String, Object> values) {
            return new PlayerErrorSourceLocation(values);
        }
        /**
         * Returns the file field.
         * @return the protocol field value
         */
        public String file() {
            return (String) require("file");
        }
        /**
         * Returns the line field.
         * @return the protocol field value
         */
        public long line() {
            return ((Number) require("line")).longValue();
        }
        /**
         * Sets the file field.
         * @param file field value
         * @return this model
         */
        public PlayerErrorSourceLocation file(String file) {
            set("file", file);
            return this;
        }
        /**
         * Sets the line field.
         * @param line field value
         * @return this model
         */
        public PlayerErrorSourceLocation line(long line) {
            set("line", line);
            return this;
        }
    }
    /**
     * Corresponds to kMediaError
     */
    public static final class PlayerError extends CdpObject {
        public PlayerError() {}
        private PlayerError(Map<String, Object> values) { super(values); }
        public static PlayerError fromMap(Map<String, Object> values) {
            return new PlayerError(values);
        }
        /**
         * Returns the errorType field.
         * @return the protocol field value
         */
        public String errorType() {
            return (String) require("errorType");
        }
        /**
         * Code is the numeric enum entry for a specific set of error codes, such as PipelineStatusCodes in media/base/pipeline_status.h
         * @return the protocol field value
         */
        public long code() {
            return ((Number) require("code")).longValue();
        }
        /**
         * A trace of where this error was caused / where it passed through.
         * @return the protocol field value
         */
        public java.util.List<Media.PlayerErrorSourceLocation> stack() {
            return CdpObject.requireList(require("stack"), element0 -> java.util.Objects.requireNonNull(Media.PlayerErrorSourceLocation.fromMap(java.util.Objects.requireNonNull(CdpObject.objectMap(element0)))));
        }
        /**
         * Errors potentially have a root cause error, ie, a DecoderError might be caused by an WindowsError
         * @return the protocol field value
         */
        public java.util.List<Media.PlayerError> cause() {
            return CdpObject.requireList(require("cause"), element0 -> java.util.Objects.requireNonNull(Media.PlayerError.fromMap(java.util.Objects.requireNonNull(CdpObject.objectMap(element0)))));
        }
        /**
         * Extra data attached to an error, such as an HRESULT, Video Codec, etc.
         * @return the protocol field value
         */
        public java.util.Map<String, Object> data() {
            return java.util.Objects.requireNonNull(CdpObject.objectMap(require("data")));
        }
        /**
         * Sets the errorType field.
         * @param errorType field value
         * @return this model
         */
        public PlayerError errorType(String errorType) {
            set("errorType", errorType);
            return this;
        }
        /**
         * Code is the numeric enum entry for a specific set of error codes, such as PipelineStatusCodes in media/base/pipeline_status.h
         * @param code field value
         * @return this model
         */
        public PlayerError code(long code) {
            set("code", code);
            return this;
        }
        /**
         * A trace of where this error was caused / where it passed through.
         * @param stack field value
         * @return this model
         */
        public PlayerError stack(java.util.List<Media.PlayerErrorSourceLocation> stack) {
            set("stack", stack);
            return this;
        }
        /**
         * Errors potentially have a root cause error, ie, a DecoderError might be caused by an WindowsError
         * @param cause field value
         * @return this model
         */
        public PlayerError cause(java.util.List<Media.PlayerError> cause) {
            set("cause", cause);
            return this;
        }
        /**
         * Extra data attached to an error, such as an HRESULT, Video Codec, etc.
         * @param data field value
         * @return this model
         */
        public PlayerError data(java.util.Map<String, Object> data) {
            set("data", data);
            return this;
        }
    }
    /**
     */
    public static final class Player extends CdpObject {
        public Player() {}
        private Player(Map<String, Object> values) { super(values); }
        public static Player fromMap(Map<String, Object> values) {
            return new Player(values);
        }
        /**
         * Returns the playerId field.
         * @return the protocol field value
         */
        public Media.PlayerId playerId() {
            return new Media.PlayerId((String) require("playerId"));
        }
        /**
         * Returns the domNodeId field.
         * @return the protocol field value, empty when absent
         */
        public Optional<DOM.BackendNodeId> domNodeId() {
            return Optional.ofNullable(raw("domNodeId") == null ? null : new DOM.BackendNodeId(((Number) raw("domNodeId")).longValue()));
        }
        /**
         * Sets the playerId field.
         * @param playerId field value
         * @return this model
         */
        public Player playerId(Media.PlayerId playerId) {
            set("playerId", playerId);
            return this;
        }
        /**
         * Sets the domNodeId field.
         * @param domNodeId field value; empty omits the value
         * @return this model
         */
        public Player domNodeId(Optional<DOM.BackendNodeId> domNodeId) {
            set("domNodeId", domNodeId.orElse(null));
            return this;
        }
        /**
         * Sets the domNodeId field.
         * @param domNodeId field value; null removes the value
         * @return this model
         */
        public Player domNodeId(DOM.BackendNodeId domNodeId) {
            set("domNodeId", domNodeId);
            return this;
        }
    }
    /**
     * This can be called multiple times, and can be used to set / override / remove player properties. A null propValue indicates removal.
     */
    public static final class PlayerPropertiesChangedEvent extends CdpObject {
        public PlayerPropertiesChangedEvent() {}
        private PlayerPropertiesChangedEvent(Map<String, Object> values) { super(values); }
        public static PlayerPropertiesChangedEvent fromMap(Map<String, Object> values) {
            return new PlayerPropertiesChangedEvent(values);
        }
        /**
         * Returns the playerId field.
         * @return the protocol field value
         */
        public Media.PlayerId playerId() {
            return new Media.PlayerId((String) require("playerId"));
        }
        /**
         * Returns the properties field.
         * @return the protocol field value
         */
        public java.util.List<Media.PlayerProperty> properties() {
            return CdpObject.requireList(require("properties"), element0 -> java.util.Objects.requireNonNull(Media.PlayerProperty.fromMap(java.util.Objects.requireNonNull(CdpObject.objectMap(element0)))));
        }
        /**
         * Sets the playerId field.
         * @param playerId field value
         * @return this model
         */
        public PlayerPropertiesChangedEvent playerId(Media.PlayerId playerId) {
            set("playerId", playerId);
            return this;
        }
        /**
         * Sets the properties field.
         * @param properties field value
         * @return this model
         */
        public PlayerPropertiesChangedEvent properties(java.util.List<Media.PlayerProperty> properties) {
            set("properties", properties);
            return this;
        }
    }
    /**
     * Send events as a list, allowing them to be batched on the browser for less congestion. If batched, events must ALWAYS be in chronological order.
     */
    public static final class PlayerEventsAddedEvent extends CdpObject {
        public PlayerEventsAddedEvent() {}
        private PlayerEventsAddedEvent(Map<String, Object> values) { super(values); }
        public static PlayerEventsAddedEvent fromMap(Map<String, Object> values) {
            return new PlayerEventsAddedEvent(values);
        }
        /**
         * Returns the playerId field.
         * @return the protocol field value
         */
        public Media.PlayerId playerId() {
            return new Media.PlayerId((String) require("playerId"));
        }
        /**
         * Returns the events field.
         * @return the protocol field value
         */
        public java.util.List<Media.PlayerEvent> events() {
            return CdpObject.requireList(require("events"), element0 -> java.util.Objects.requireNonNull(Media.PlayerEvent.fromMap(java.util.Objects.requireNonNull(CdpObject.objectMap(element0)))));
        }
        /**
         * Sets the playerId field.
         * @param playerId field value
         * @return this model
         */
        public PlayerEventsAddedEvent playerId(Media.PlayerId playerId) {
            set("playerId", playerId);
            return this;
        }
        /**
         * Sets the events field.
         * @param events field value
         * @return this model
         */
        public PlayerEventsAddedEvent events(java.util.List<Media.PlayerEvent> events) {
            set("events", events);
            return this;
        }
    }
    /**
     * Send a list of any messages that need to be delivered.
     */
    public static final class PlayerMessagesLoggedEvent extends CdpObject {
        public PlayerMessagesLoggedEvent() {}
        private PlayerMessagesLoggedEvent(Map<String, Object> values) { super(values); }
        public static PlayerMessagesLoggedEvent fromMap(Map<String, Object> values) {
            return new PlayerMessagesLoggedEvent(values);
        }
        /**
         * Returns the playerId field.
         * @return the protocol field value
         */
        public Media.PlayerId playerId() {
            return new Media.PlayerId((String) require("playerId"));
        }
        /**
         * Returns the messages field.
         * @return the protocol field value
         */
        public java.util.List<Media.PlayerMessage> messages() {
            return CdpObject.requireList(require("messages"), element0 -> java.util.Objects.requireNonNull(Media.PlayerMessage.fromMap(java.util.Objects.requireNonNull(CdpObject.objectMap(element0)))));
        }
        /**
         * Sets the playerId field.
         * @param playerId field value
         * @return this model
         */
        public PlayerMessagesLoggedEvent playerId(Media.PlayerId playerId) {
            set("playerId", playerId);
            return this;
        }
        /**
         * Sets the messages field.
         * @param messages field value
         * @return this model
         */
        public PlayerMessagesLoggedEvent messages(java.util.List<Media.PlayerMessage> messages) {
            set("messages", messages);
            return this;
        }
    }
    /**
     * Send a list of any errors that need to be delivered.
     */
    public static final class PlayerErrorsRaisedEvent extends CdpObject {
        public PlayerErrorsRaisedEvent() {}
        private PlayerErrorsRaisedEvent(Map<String, Object> values) { super(values); }
        public static PlayerErrorsRaisedEvent fromMap(Map<String, Object> values) {
            return new PlayerErrorsRaisedEvent(values);
        }
        /**
         * Returns the playerId field.
         * @return the protocol field value
         */
        public Media.PlayerId playerId() {
            return new Media.PlayerId((String) require("playerId"));
        }
        /**
         * Returns the errors field.
         * @return the protocol field value
         */
        public java.util.List<Media.PlayerError> errors() {
            return CdpObject.requireList(require("errors"), element0 -> java.util.Objects.requireNonNull(Media.PlayerError.fromMap(java.util.Objects.requireNonNull(CdpObject.objectMap(element0)))));
        }
        /**
         * Sets the playerId field.
         * @param playerId field value
         * @return this model
         */
        public PlayerErrorsRaisedEvent playerId(Media.PlayerId playerId) {
            set("playerId", playerId);
            return this;
        }
        /**
         * Sets the errors field.
         * @param errors field value
         * @return this model
         */
        public PlayerErrorsRaisedEvent errors(java.util.List<Media.PlayerError> errors) {
            set("errors", errors);
            return this;
        }
    }
    /**
     * Called whenever a player is created, or when a new agent joins and receives a list of active players. If an agent is restored, it will receive one event for each active player.
     */
    public static final class PlayerCreatedEvent extends CdpObject {
        public PlayerCreatedEvent() {}
        private PlayerCreatedEvent(Map<String, Object> values) { super(values); }
        public static PlayerCreatedEvent fromMap(Map<String, Object> values) {
            return new PlayerCreatedEvent(values);
        }
        /**
         * Returns the player field.
         * @return the protocol field value
         */
        public Media.Player player() {
            return java.util.Objects.requireNonNull(Media.Player.fromMap(java.util.Objects.requireNonNull(CdpObject.objectMap(require("player")))));
        }
        /**
         * Sets the player field.
         * @param player field value
         * @return this model
         */
        public PlayerCreatedEvent player(Media.Player player) {
            set("player", player);
            return this;
        }
    }
    public static final class Client {
        private final CdpClient client;
        public Client(CdpClient client) { this.client = client; }
        /**
         * Enables the Media domain
         * @return a stage completing when the command completes
         */
        public CompletionStage<Void> enable() {
            return client.call("Media.enable", null, result_ -> null);
        }
        /**
         * Disables the Media domain.
         * @return a stage completing when the command completes
         */
        public CompletionStage<Void> disable() {
            return client.call("Media.disable", null, result_ -> null);
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
