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
 * This domain is deprecated - use Runtime or Log instead.
 * @deprecated Deprecated by the Chromium DevTools Protocol.
 * @see <a href="https://chromium.googlesource.com/v8/v8/+/0e999a528db40a3ef6fa917adf96370a18b87d70/include/js_protocol.pdl">Pinned protocol source</a>
 */
@Deprecated
@SuppressWarnings({"JavaLangClash", "UnusedMethod"})
public final class Console {
    private Console() {}
    @Nullable private static Long numberAsLong(@Nullable Object value) { return value == null ? null : ((Number) value).longValue(); }
    @Nullable private static Double numberAsDouble(@Nullable Object value) { return value == null ? null : ((Number) value).doubleValue(); }
    /**
     * Console message.
     */
    public static final class ConsoleMessage extends CdpObject {
        private ConsoleMessage(Map<String, Object> values) { super(values); }
        @Nullable public static ConsoleMessage fromMap(@Nullable Map<String, Object> values) {
            return values == null ? null : new ConsoleMessage(values);
        }
        public static Builder builder() { return new Builder(); }
        /**
         * Message source.
         * @return the protocol field value
         */
        @Nullable public String source() {
            return (String) value("source");
        }
        /**
         * Message source.
         */
        public static final class SourceValues {
            private SourceValues() {}
            public static final String XML = "xml";
            public static final String JAVASCRIPT = "javascript";
            public static final String NETWORK = "network";
            public static final String CONSOLE_API = "console-api";
            public static final String STORAGE = "storage";
            public static final String APPCACHE = "appcache";
            public static final String RENDERING = "rendering";
            public static final String SECURITY = "security";
            public static final String OTHER = "other";
            public static final String DEPRECATION = "deprecation";
            public static final String WORKER = "worker";
        }
        /**
         * Message severity.
         * @return the protocol field value
         */
        @Nullable public String level() {
            return (String) value("level");
        }
        /**
         * Message severity.
         */
        public static final class LevelValues {
            private LevelValues() {}
            public static final String LOG = "log";
            public static final String WARNING = "warning";
            public static final String ERROR = "error";
            public static final String DEBUG = "debug";
            public static final String INFO = "info";
        }
        /**
         * Message text.
         * @return the protocol field value
         */
        @Nullable public String text() {
            return (String) value("text");
        }
        /**
         * URL of the message origin.
         * @return the protocol field value
         */
        @Nullable public String url() {
            return (String) value("url");
        }
        /**
         * Line number in the resource that generated this message (1-based).
         * @return the protocol field value
         */
        @Nullable public Long line() {
            return numberAsLong(value("line"));
        }
        /**
         * Column number in the resource that generated this message (1-based).
         * @return the protocol field value
         */
        @Nullable public Long column() {
            return numberAsLong(value("column"));
        }
        public static final class Builder {
            private final Map<String, Object> values = new LinkedHashMap<>();
            /**
             * Message source.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder source(@Nullable String value) {
                if (value == null) values.remove("source");
                else values.put("source", jsonValue(value));
                return this;
            }
            /**
             * Message severity.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder level(@Nullable String value) {
                if (value == null) values.remove("level");
                else values.put("level", jsonValue(value));
                return this;
            }
            /**
             * Message text.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder text(@Nullable String value) {
                if (value == null) values.remove("text");
                else values.put("text", jsonValue(value));
                return this;
            }
            /**
             * URL of the message origin.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder url(@Nullable String value) {
                if (value == null) values.remove("url");
                else values.put("url", jsonValue(value));
                return this;
            }
            /**
             * Line number in the resource that generated this message (1-based).
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder line(@Nullable Long value) {
                if (value == null) values.remove("line");
                else values.put("line", jsonValue(value));
                return this;
            }
            /**
             * Column number in the resource that generated this message (1-based).
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder column(@Nullable Long value) {
                if (value == null) values.remove("column");
                else values.put("column", jsonValue(value));
                return this;
            }
            public ConsoleMessage build() {
                if (!values.containsKey("source")) throw new IllegalStateException("Missing required CDP field: source");
                if (!values.containsKey("level")) throw new IllegalStateException("Missing required CDP field: level");
                if (!values.containsKey("text")) throw new IllegalStateException("Missing required CDP field: text");
                return new ConsoleMessage(values);
            }
        }
    }
    /**
     * Does nothing.
     */
    public static final class ClearMessagesParams extends CdpObject {
        private ClearMessagesParams(Map<String, Object> values) { super(values); }
        @Nullable public static ClearMessagesParams fromMap(@Nullable Map<String, Object> values) {
            return values == null ? null : new ClearMessagesParams(values);
        }
        public static Builder builder() { return new Builder(); }
        public static final class Builder {
            private final Map<String, Object> values = new LinkedHashMap<>();
            public ClearMessagesParams build() {
                return new ClearMessagesParams(values);
            }
        }
    }
    /**
     * Does nothing.
     */
    public static final class ClearMessagesResult extends CdpObject {
        private ClearMessagesResult(Map<String, Object> values) { super(values); }
        @Nullable public static ClearMessagesResult fromMap(@Nullable Map<String, Object> values) {
            return values == null ? null : new ClearMessagesResult(values);
        }
        public static Builder builder() { return new Builder(); }
        public static final class Builder {
            private final Map<String, Object> values = new LinkedHashMap<>();
            public ClearMessagesResult build() {
                return new ClearMessagesResult(values);
            }
        }
    }
    /**
     * Disables console domain, prevents further console messages from being reported to the client.
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
     * Disables console domain, prevents further console messages from being reported to the client.
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
     * Enables console domain, sends the messages collected so far to the client by means of the {@code messageAdded} notification.
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
     * Enables console domain, sends the messages collected so far to the client by means of the {@code messageAdded} notification.
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
     * Issued when new console message is added.
     */
    public static final class MessageAddedEvent extends CdpObject {
        private MessageAddedEvent(Map<String, Object> values) { super(values); }
        @Nullable public static MessageAddedEvent fromMap(@Nullable Map<String, Object> values) {
            return values == null ? null : new MessageAddedEvent(values);
        }
        public static Builder builder() { return new Builder(); }
        /**
         * Console message that has been added.
         * @return the protocol field value
         */
        @Nullable public Console.ConsoleMessage message() {
            return Console.ConsoleMessage.fromMap(objectMap(value("message")));
        }
        public static final class Builder {
            private final Map<String, Object> values = new LinkedHashMap<>();
            /**
             * Console message that has been added.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder message(@Nullable Console.ConsoleMessage value) {
                if (value == null) values.remove("message");
                else values.put("message", jsonValue(value));
                return this;
            }
            public MessageAddedEvent build() {
                if (!values.containsKey("message")) throw new IllegalStateException("Missing required CDP field: message");
                return new MessageAddedEvent(values);
            }
        }
    }
    public static final class Client {
        private final CdpClient client;
        public Client(CdpClient client) { this.client = client; }
        /**
         * Does nothing.
         * @return a stage completing with the command result
         */
        public CompletionStage<ClearMessagesResult> clearMessages() {
            return client.call("Console.clearMessages", null, ClearMessagesResult::fromMap);
        }
        /**
         * Disables console domain, prevents further console messages from being reported to the client.
         * @return a stage completing with the command result
         */
        public CompletionStage<DisableResult> disable() {
            return client.call("Console.disable", null, DisableResult::fromMap);
        }
        /**
         * Enables console domain, sends the messages collected so far to the client by means of the {@code messageAdded} notification.
         * @return a stage completing with the command result
         */
        public CompletionStage<EnableResult> enable() {
            return client.call("Console.enable", null, EnableResult::fromMap);
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
