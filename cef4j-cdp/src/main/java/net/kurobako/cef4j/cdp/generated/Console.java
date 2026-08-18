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
 * This domain is deprecated - use Runtime or Log instead.
 * @deprecated Deprecated by the Chromium DevTools Protocol.
 * @see <a href="https://chromium.googlesource.com/v8/v8/+/3063ea3a0737a3fc4d4ed3babd595f1cace1e6ac/include/js_protocol.pdl">Pinned protocol source</a>
 */
@Deprecated
@Generated("mvn generate-sources -pl cef4j-platform -Dcef.version=150.0.18+gdb11278+chromium-150.0.7871.213")
@SuppressWarnings({"EscapedEntity", "InvalidParam", "JavaLangClash", "MissingSummary", "Unchecked", "UnusedMethod"})
public final class Console {
    private Console() {}
    /**
     * Console message.
     */
    public static final class ConsoleMessage extends CdpObject {
        public ConsoleMessage() {}
        private ConsoleMessage(Map<String, Object> values) { super(values); }
        public static ConsoleMessage fromMap(Map<String, Object> values) {
            return new ConsoleMessage(values);
        }
        /**
         * Message source.
         */
        public enum SourceValues implements CdpValue<String> {
            XML("xml"),
            JAVASCRIPT("javascript"),
            NETWORK("network"),
            CONSOLE_API("console-api"),
            STORAGE("storage"),
            APPCACHE("appcache"),
            RENDERING("rendering"),
            SECURITY("security"),
            OTHER("other"),
            DEPRECATION("deprecation"),
            WORKER("worker");
            public final String value;
            SourceValues(String value) { this.value = value; }
            @Nonnull public String value() { return value; }
            public static SourceValues of(@Nonnull String value) {
                for (SourceValues constant : values()) {
                    if (constant.value.equals(value)) return constant;
                }
                throw new IllegalArgumentException("Unknown SourceValues value: " + value);
            }
        }
        /**
         * Message severity.
         */
        public enum LevelValues implements CdpValue<String> {
            LOG("log"),
            WARNING("warning"),
            ERROR("error"),
            DEBUG("debug"),
            INFO("info");
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
         * Message source.
         * @return the protocol field value
         */
        public ConsoleMessage.SourceValues source() {
            return ConsoleMessage.SourceValues.of((String) require("source"));
        }
        /**
         * Message severity.
         * @return the protocol field value
         */
        public ConsoleMessage.LevelValues level() {
            return ConsoleMessage.LevelValues.of((String) require("level"));
        }
        /**
         * Message text.
         * @return the protocol field value
         */
        public String text() {
            return (String) require("text");
        }
        /**
         * URL of the message origin.
         * @return the protocol field value, empty when absent
         */
        public Optional<String> url() {
            return Optional.ofNullable((String) raw("url"));
        }
        /**
         * Line number in the resource that generated this message (1-based).
         * @return the protocol field value, empty when absent
         */
        public OptionalLong line() {
            Long value = CdpObject.numberAsLong(raw("line"));
            return value == null ? OptionalLong.empty() : OptionalLong.of(value);
        }
        /**
         * Column number in the resource that generated this message (1-based).
         * @return the protocol field value, empty when absent
         */
        public OptionalLong column() {
            Long value = CdpObject.numberAsLong(raw("column"));
            return value == null ? OptionalLong.empty() : OptionalLong.of(value);
        }
        /**
         * Message source.
         * @param source field value
         * @return this model
         */
        public ConsoleMessage source(ConsoleMessage.SourceValues source) {
            set("source", source);
            return this;
        }
        /**
         * Message severity.
         * @param level field value
         * @return this model
         */
        public ConsoleMessage level(ConsoleMessage.LevelValues level) {
            set("level", level);
            return this;
        }
        /**
         * Message text.
         * @param text field value
         * @return this model
         */
        public ConsoleMessage text(String text) {
            set("text", text);
            return this;
        }
        /**
         * URL of the message origin.
         * @param url field value; empty omits the value
         * @return this model
         */
        public ConsoleMessage url(Optional<String> url) {
            set("url", url.orElse(null));
            return this;
        }
        /**
         * URL of the message origin.
         * @param url field value; null removes the value
         * @return this model
         */
        public ConsoleMessage url(String url) {
            set("url", url);
            return this;
        }
        /**
         * Line number in the resource that generated this message (1-based).
         * @param line field value; empty omits the value
         * @return this model
         */
        public ConsoleMessage line(OptionalLong line) {
            set("line", line.isPresent() ? line.getAsLong() : null);
            return this;
        }
        /**
         * Line number in the resource that generated this message (1-based).
         * @param line field value; null removes the value
         * @return this model
         */
        public ConsoleMessage line(Long line) {
            set("line", line);
            return this;
        }
        /**
         * Column number in the resource that generated this message (1-based).
         * @param column field value; empty omits the value
         * @return this model
         */
        public ConsoleMessage column(OptionalLong column) {
            set("column", column.isPresent() ? column.getAsLong() : null);
            return this;
        }
        /**
         * Column number in the resource that generated this message (1-based).
         * @param column field value; null removes the value
         * @return this model
         */
        public ConsoleMessage column(Long column) {
            set("column", column);
            return this;
        }
    }
    /**
     * Issued when new console message is added.
     */
    public static final class MessageAddedEvent extends CdpObject {
        public MessageAddedEvent() {}
        private MessageAddedEvent(Map<String, Object> values) { super(values); }
        public static MessageAddedEvent fromMap(Map<String, Object> values) {
            return new MessageAddedEvent(values);
        }
        /**
         * Console message that has been added.
         * @return the protocol field value
         */
        public Console.ConsoleMessage message() {
            return java.util.Objects.requireNonNull(Console.ConsoleMessage.fromMap(java.util.Objects.requireNonNull(CdpObject.objectMap(require("message")))));
        }
        /**
         * Console message that has been added.
         * @param message field value
         * @return this model
         */
        public MessageAddedEvent message(Console.ConsoleMessage message) {
            set("message", message);
            return this;
        }
    }
    public static final class Client {
        private final CdpClient client;
        public Client(CdpClient client) { this.client = client; }
        /**
         * Does nothing.
         * @return a stage completing when the command completes
         */
        public CompletionStage<Void> clearMessages() {
            return client.call("Console.clearMessages", null, result_ -> null);
        }
        /**
         * Disables console domain, prevents further console messages from being reported to the client.
         * @return a stage completing when the command completes
         */
        public CompletionStage<Void> disable() {
            return client.call("Console.disable", null, result_ -> null);
        }
        /**
         * Enables console domain, sends the messages collected so far to the client by means of the {@code messageAdded} notification.
         * @return a stage completing when the command completes
         */
        public CompletionStage<Void> enable() {
            return client.call("Console.enable", null, result_ -> null);
        }
        /**
         * Issued when new console message is added.
         * @param handler event callback
         * @return a removable subscription
         */
        public CdpSubscription onMessageAdded(Consumer<MessageAddedEvent> handler) {
            return client.on("Console.messageAdded", MessageAddedEvent::fromMap, handler);
        }
    }
}
