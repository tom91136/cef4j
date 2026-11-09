// GENERATED - do not edit. Regenerate via: ./mvnw generate-sources -pl cef4j-cdp -Dcef.version=150.0.18+gdb11278+chromium-150.0.7871.213
package net.kurobako.cef4j.cdp.generated;

import java.util.Map;
import java.util.Optional;
import java.util.OptionalLong;
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
 * This domain is deprecated - use Runtime or Log instead.
 * @deprecated Deprecated by the Chromium DevTools Protocol.
 * @see <a href="https://chromium.googlesource.com/v8/v8/+/3063ea3a0737a3fc4d4ed3babd595f1cace1e6ac/include/js_protocol.pdl">Pinned protocol source</a>
 */
@Deprecated
@Generated("./mvnw generate-sources -pl cef4j-cdp -Dcef.version=150.0.18+gdb11278+chromium-150.0.7871.213")
@SuppressWarnings({"EscapedEntity", "InvalidParam", "JavaLangClash", "MissingSummary", "Unchecked", "UnusedMethod"})
@NullableBoundary("CDP wire objects use null for absent optional fields")
public final class Console {
    private Console() {}
    private static final CdpField<ConsoleMessage.LevelValues> LEVEL = CdpField.of("level", value -> ConsoleMessage.LevelValues.of((String) value));
    private static final CdpField<Console.ConsoleMessage> MESSAGE = CdpField.of("message", value -> java.util.Objects.requireNonNull(Console.ConsoleMessage.fromMap(CdpObject.requireObjectMap(value))));
    private static final CdpField<ConsoleMessage.SourceValues> SOURCE = CdpField.of("source", value -> ConsoleMessage.SourceValues.of((String) value));
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
            @Override public String value() { return value; }
            public static SourceValues of(String value) {
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
            @Override public String value() { return value; }
            public static LevelValues of(String value) {
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
            return require(SOURCE);
        }
        /**
         * Message severity.
         * @return the protocol field value
         */
        public ConsoleMessage.LevelValues level() {
            return require(LEVEL);
        }
        /**
         * Message text.
         * @return the protocol field value
         */
        public String text() {
            return requireString("text");
        }
        /**
         * URL of the message origin.
         * @return the protocol field value, empty when absent
         */
        public Optional<String> url() {
            return optionalString("url");
        }
        /**
         * Line number in the resource that generated this message (1-based).
         * @return the protocol field value, empty when absent
         */
        public OptionalLong line() {
            return optionalLong("line");
        }
        /**
         * Column number in the resource that generated this message (1-based).
         * @return the protocol field value, empty when absent
         */
        public OptionalLong column() {
            return optionalLong("column");
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
         * @param url field value
         * @return this model
         */
        public ConsoleMessage url(String url) {
            set("url", url);
            return this;
        }
        /**
         * Line number in the resource that generated this message (1-based).
         * @param line field value
         * @return this model
         */
        public ConsoleMessage line(long line) {
            set("line", line);
            return this;
        }
        /**
         * Column number in the resource that generated this message (1-based).
         * @param column field value
         * @return this model
         */
        public ConsoleMessage column(long column) {
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
            return require(MESSAGE);
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
    public static final class Client extends CdpDomainClient {
        public Client(CdpClient client) { super(client); }
        /**
         * Does nothing.
         * @return a stage completing when the command completes
         */
        public CompletionStage<Void> clearMessages() {
            return call("Console.clearMessages");
        }
        /**
         * Disables console domain, prevents further console messages from being reported to the client.
         * @return a stage completing when the command completes
         */
        public CompletionStage<Void> disable() {
            return call("Console.disable");
        }
        /**
         * Enables console domain, sends the messages collected so far to the client by means of the {@code messageAdded} notification.
         * @return a stage completing when the command completes
         */
        public CompletionStage<Void> enable() {
            return call("Console.enable");
        }
        /**
         * Issued when new console message is added.
         * @param handler event callback
         * @return a removable subscription
         */
        public CdpSubscription onMessageAdded(Consumer<MessageAddedEvent> handler) {
            return on("Console.messageAdded", MessageAddedEvent::fromMap, handler);
        }
    }
}
