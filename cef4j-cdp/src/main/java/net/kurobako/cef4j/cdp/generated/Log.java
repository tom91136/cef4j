// GENERATED - do not edit. Regenerate via: mvn generate-sources -pl cef4j-platform -Dcef.version=150.0.18+gdb11278+chromium-150.0.7871.213
package net.kurobako.cef4j.cdp.generated;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.concurrent.CompletionStage;
import java.util.function.Consumer;
import javax.annotation.Nullable;
import javax.annotation.processing.Generated;
import net.kurobako.cef4j.cdp.CdpClient;
import net.kurobako.cef4j.cdp.CdpObject;
import net.kurobako.cef4j.cdp.CdpSubscription;

/**
 * Provides access to log entries.
 * @see <a href="https://chromium.googlesource.com/chromium/src/+/refs/tags/150.0.7871.213/third_party/blink/public/devtools_protocol/domains/Log.pdl">Pinned protocol source</a>
 */
@Generated("mvn generate-sources -pl cef4j-platform -Dcef.version=150.0.18+gdb11278+chromium-150.0.7871.213")
@SuppressWarnings({"EscapedEntity", "JavaLangClash", "MissingSummary", "UnusedMethod"})
public final class Log {
    private Log() {}
    @Nullable private static Long numberAsLong(@Nullable Object value) { return value == null ? null : ((Number) value).longValue(); }
    @Nullable private static Double numberAsDouble(@Nullable Object value) { return value == null ? null : ((Number) value).doubleValue(); }
    /**
     * Log entry.
     */
    public static final class LogEntry extends CdpObject {
        private LogEntry(Map<String, Object> values) { super(values); }
        @Nullable public static LogEntry fromMap(@Nullable Map<String, Object> values) {
            return values == null ? null : new LogEntry(values);
        }
        public static Builder builder() { return new Builder(); }
        /**
         * Log entry source.
         * @return the protocol field value
         */
        @Nullable public String source() {
            return (String) value("source");
        }
        /**
         * Log entry source.
         */
        public static final class SourceValues {
            private SourceValues() {}
            public static final String XML = "xml";
            public static final String JAVASCRIPT = "javascript";
            public static final String NETWORK = "network";
            public static final String STORAGE = "storage";
            public static final String APPCACHE = "appcache";
            public static final String RENDERING = "rendering";
            public static final String SECURITY = "security";
            public static final String DEPRECATION = "deprecation";
            public static final String WORKER = "worker";
            public static final String VIOLATION = "violation";
            public static final String INTERVENTION = "intervention";
            public static final String RECOMMENDATION = "recommendation";
            public static final String OTHER = "other";
        }
        /**
         * Log entry severity.
         * @return the protocol field value
         */
        @Nullable public String level() {
            return (String) value("level");
        }
        /**
         * Log entry severity.
         */
        public static final class LevelValues {
            private LevelValues() {}
            public static final String VERBOSE = "verbose";
            public static final String INFO = "info";
            public static final String WARNING = "warning";
            public static final String ERROR = "error";
        }
        /**
         * Logged text.
         * @return the protocol field value
         */
        @Nullable public String text() {
            return (String) value("text");
        }
        /**
         * Returns the category field.
         * @return the protocol field value
         */
        @Nullable public String category() {
            return (String) value("category");
        }
        /**
         * Wire values for CategoryValues.
         */
        public static final class CategoryValues {
            private CategoryValues() {}
            public static final String CORS = "cors";
        }
        /**
         * Timestamp when this entry was added.
         * @return the protocol field value
         */
        @Nullable public Double timestamp() {
            return numberAsDouble(value("timestamp"));
        }
        /**
         * URL of the resource if known.
         * @return the protocol field value
         */
        @Nullable public String url() {
            return (String) value("url");
        }
        /**
         * Line number in the resource.
         * @return the protocol field value
         */
        @Nullable public Long lineNumber() {
            return numberAsLong(value("lineNumber"));
        }
        /**
         * JavaScript stack trace.
         * @return the protocol field value
         */
        @Nullable public Runtime.StackTrace stackTrace() {
            return Runtime.StackTrace.fromMap(objectMap(value("stackTrace")));
        }
        /**
         * Identifier of the network request associated with this entry.
         * @return the protocol field value
         */
        @Nullable public String networkRequestId() {
            return (String) value("networkRequestId");
        }
        /**
         * Identifier of the worker associated with this entry.
         * @return the protocol field value
         */
        @Nullable public String workerId() {
            return (String) value("workerId");
        }
        /**
         * Call arguments.
         * @return the protocol field value
         */
        @Nullable public java.util.List<Runtime.RemoteObject> args() {
            return list(value("args"), element0 -> Runtime.RemoteObject.fromMap(objectMap(element0)));
        }
        public static final class Builder {
            private final Map<String, Object> values = new LinkedHashMap<>();
            /**
             * Log entry source.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder source(@Nullable String value) {
                if (value == null) values.remove("source");
                else values.put("source", jsonValue(value));
                return this;
            }
            /**
             * Log entry severity.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder level(@Nullable String value) {
                if (value == null) values.remove("level");
                else values.put("level", jsonValue(value));
                return this;
            }
            /**
             * Logged text.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder text(@Nullable String value) {
                if (value == null) values.remove("text");
                else values.put("text", jsonValue(value));
                return this;
            }
            /**
             * Sets the category field.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder category(@Nullable String value) {
                if (value == null) values.remove("category");
                else values.put("category", jsonValue(value));
                return this;
            }
            /**
             * Timestamp when this entry was added.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder timestamp(@Nullable Double value) {
                if (value == null) values.remove("timestamp");
                else values.put("timestamp", jsonValue(value));
                return this;
            }
            /**
             * URL of the resource if known.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder url(@Nullable String value) {
                if (value == null) values.remove("url");
                else values.put("url", jsonValue(value));
                return this;
            }
            /**
             * Line number in the resource.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder lineNumber(@Nullable Long value) {
                if (value == null) values.remove("lineNumber");
                else values.put("lineNumber", jsonValue(value));
                return this;
            }
            /**
             * JavaScript stack trace.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder stackTrace(@Nullable Runtime.StackTrace value) {
                if (value == null) values.remove("stackTrace");
                else values.put("stackTrace", jsonValue(value));
                return this;
            }
            /**
             * Identifier of the network request associated with this entry.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder networkRequestId(@Nullable String value) {
                if (value == null) values.remove("networkRequestId");
                else values.put("networkRequestId", jsonValue(value));
                return this;
            }
            /**
             * Identifier of the worker associated with this entry.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder workerId(@Nullable String value) {
                if (value == null) values.remove("workerId");
                else values.put("workerId", jsonValue(value));
                return this;
            }
            /**
             * Call arguments.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder args(@Nullable java.util.List<Runtime.RemoteObject> value) {
                if (value == null) values.remove("args");
                else values.put("args", jsonValue(value));
                return this;
            }
            public LogEntry build() {
                if (!values.containsKey("source")) throw new IllegalStateException("Missing required CDP field: source");
                if (!values.containsKey("level")) throw new IllegalStateException("Missing required CDP field: level");
                if (!values.containsKey("text")) throw new IllegalStateException("Missing required CDP field: text");
                if (!values.containsKey("timestamp")) throw new IllegalStateException("Missing required CDP field: timestamp");
                return new LogEntry(values);
            }
        }
    }
    /**
     * Violation configuration setting.
     */
    public static final class ViolationSetting extends CdpObject {
        private ViolationSetting(Map<String, Object> values) { super(values); }
        @Nullable public static ViolationSetting fromMap(@Nullable Map<String, Object> values) {
            return values == null ? null : new ViolationSetting(values);
        }
        public static Builder builder() { return new Builder(); }
        /**
         * Violation type.
         * @return the protocol field value
         */
        @Nullable public String name() {
            return (String) value("name");
        }
        /**
         * Violation type.
         */
        public static final class NameValues {
            private NameValues() {}
            public static final String LONGTASK = "longTask";
            public static final String LONGLAYOUT = "longLayout";
            public static final String BLOCKEDEVENT = "blockedEvent";
            public static final String BLOCKEDPARSER = "blockedParser";
            public static final String DISCOURAGEDAPIUSE = "discouragedAPIUse";
            public static final String HANDLER = "handler";
            public static final String RECURRINGHANDLER = "recurringHandler";
        }
        /**
         * Time threshold to trigger upon.
         * @return the protocol field value
         */
        @Nullable public Double threshold() {
            return numberAsDouble(value("threshold"));
        }
        public static final class Builder {
            private final Map<String, Object> values = new LinkedHashMap<>();
            /**
             * Violation type.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder name(@Nullable String value) {
                if (value == null) values.remove("name");
                else values.put("name", jsonValue(value));
                return this;
            }
            /**
             * Time threshold to trigger upon.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder threshold(@Nullable Double value) {
                if (value == null) values.remove("threshold");
                else values.put("threshold", jsonValue(value));
                return this;
            }
            public ViolationSetting build() {
                if (!values.containsKey("name")) throw new IllegalStateException("Missing required CDP field: name");
                if (!values.containsKey("threshold")) throw new IllegalStateException("Missing required CDP field: threshold");
                return new ViolationSetting(values);
            }
        }
    }
    /**
     * Clears the log.
     */
    public static final class ClearParams extends CdpObject {
        private ClearParams(Map<String, Object> values) { super(values); }
        @Nullable public static ClearParams fromMap(@Nullable Map<String, Object> values) {
            return values == null ? null : new ClearParams(values);
        }
        public static Builder builder() { return new Builder(); }
        public static final class Builder {
            private final Map<String, Object> values = new LinkedHashMap<>();
            public ClearParams build() {
                return new ClearParams(values);
            }
        }
    }
    /**
     * Clears the log.
     */
    public static final class ClearResult extends CdpObject {
        private ClearResult(Map<String, Object> values) { super(values); }
        @Nullable public static ClearResult fromMap(@Nullable Map<String, Object> values) {
            return values == null ? null : new ClearResult(values);
        }
        public static Builder builder() { return new Builder(); }
        public static final class Builder {
            private final Map<String, Object> values = new LinkedHashMap<>();
            public ClearResult build() {
                return new ClearResult(values);
            }
        }
    }
    /**
     * Disables log domain, prevents further log entries from being reported to the client.
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
     * Disables log domain, prevents further log entries from being reported to the client.
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
     * Enables log domain, sends the entries collected so far to the client by means of the {@code entryAdded} notification.
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
     * Enables log domain, sends the entries collected so far to the client by means of the {@code entryAdded} notification.
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
     * start violation reporting.
     */
    public static final class StartViolationsReportParams extends CdpObject {
        private StartViolationsReportParams(Map<String, Object> values) { super(values); }
        @Nullable public static StartViolationsReportParams fromMap(@Nullable Map<String, Object> values) {
            return values == null ? null : new StartViolationsReportParams(values);
        }
        public static Builder builder() { return new Builder(); }
        /**
         * Configuration for violations.
         * @return the protocol field value
         */
        @Nullable public java.util.List<Log.ViolationSetting> config() {
            return list(value("config"), element0 -> Log.ViolationSetting.fromMap(objectMap(element0)));
        }
        public static final class Builder {
            private final Map<String, Object> values = new LinkedHashMap<>();
            /**
             * Configuration for violations.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder config(@Nullable java.util.List<Log.ViolationSetting> value) {
                if (value == null) values.remove("config");
                else values.put("config", jsonValue(value));
                return this;
            }
            public StartViolationsReportParams build() {
                if (!values.containsKey("config")) throw new IllegalStateException("Missing required CDP field: config");
                return new StartViolationsReportParams(values);
            }
        }
    }
    /**
     * start violation reporting.
     */
    public static final class StartViolationsReportResult extends CdpObject {
        private StartViolationsReportResult(Map<String, Object> values) { super(values); }
        @Nullable public static StartViolationsReportResult fromMap(@Nullable Map<String, Object> values) {
            return values == null ? null : new StartViolationsReportResult(values);
        }
        public static Builder builder() { return new Builder(); }
        public static final class Builder {
            private final Map<String, Object> values = new LinkedHashMap<>();
            public StartViolationsReportResult build() {
                return new StartViolationsReportResult(values);
            }
        }
    }
    /**
     * Stop violation reporting.
     */
    public static final class StopViolationsReportParams extends CdpObject {
        private StopViolationsReportParams(Map<String, Object> values) { super(values); }
        @Nullable public static StopViolationsReportParams fromMap(@Nullable Map<String, Object> values) {
            return values == null ? null : new StopViolationsReportParams(values);
        }
        public static Builder builder() { return new Builder(); }
        public static final class Builder {
            private final Map<String, Object> values = new LinkedHashMap<>();
            public StopViolationsReportParams build() {
                return new StopViolationsReportParams(values);
            }
        }
    }
    /**
     * Stop violation reporting.
     */
    public static final class StopViolationsReportResult extends CdpObject {
        private StopViolationsReportResult(Map<String, Object> values) { super(values); }
        @Nullable public static StopViolationsReportResult fromMap(@Nullable Map<String, Object> values) {
            return values == null ? null : new StopViolationsReportResult(values);
        }
        public static Builder builder() { return new Builder(); }
        public static final class Builder {
            private final Map<String, Object> values = new LinkedHashMap<>();
            public StopViolationsReportResult build() {
                return new StopViolationsReportResult(values);
            }
        }
    }
    /**
     * Issued when new message was logged.
     */
    public static final class EntryAddedEvent extends CdpObject {
        private EntryAddedEvent(Map<String, Object> values) { super(values); }
        @Nullable public static EntryAddedEvent fromMap(@Nullable Map<String, Object> values) {
            return values == null ? null : new EntryAddedEvent(values);
        }
        public static Builder builder() { return new Builder(); }
        /**
         * The entry.
         * @return the protocol field value
         */
        @Nullable public Log.LogEntry entry() {
            return Log.LogEntry.fromMap(objectMap(value("entry")));
        }
        public static final class Builder {
            private final Map<String, Object> values = new LinkedHashMap<>();
            /**
             * The entry.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder entry(@Nullable Log.LogEntry value) {
                if (value == null) values.remove("entry");
                else values.put("entry", jsonValue(value));
                return this;
            }
            public EntryAddedEvent build() {
                if (!values.containsKey("entry")) throw new IllegalStateException("Missing required CDP field: entry");
                return new EntryAddedEvent(values);
            }
        }
    }
    public static final class Client {
        private final CdpClient client;
        public Client(CdpClient client) { this.client = client; }
        /**
         * Clears the log.
         * @return a stage completing with the command result
         */
        public CompletionStage<ClearResult> clear() {
            return client.call("Log.clear", null, ClearResult::fromMap);
        }
        /**
         * Disables log domain, prevents further log entries from being reported to the client.
         * @return a stage completing with the command result
         */
        public CompletionStage<DisableResult> disable() {
            return client.call("Log.disable", null, DisableResult::fromMap);
        }
        /**
         * Enables log domain, sends the entries collected so far to the client by means of the {@code entryAdded} notification.
         * @return a stage completing with the command result
         */
        public CompletionStage<EnableResult> enable() {
            return client.call("Log.enable", null, EnableResult::fromMap);
        }
        /**
         * start violation reporting.
         * @param params command parameters
         * @return a stage completing with the command result
         */
        public CompletionStage<StartViolationsReportResult> startViolationsReport(StartViolationsReportParams params) {
            return client.call("Log.startViolationsReport", params, StartViolationsReportResult::fromMap);
        }
        /**
         * Stop violation reporting.
         * @return a stage completing with the command result
         */
        public CompletionStage<StopViolationsReportResult> stopViolationsReport() {
            return client.call("Log.stopViolationsReport", null, StopViolationsReportResult::fromMap);
        }
        /**
         * Issued when new message was logged.
         * @param handler event callback
         * @return a removable subscription
         */
        public CdpSubscription onEntryAdded(Consumer<EntryAddedEvent> handler) {
            return client.on("Log.entryAdded", EntryAddedEvent::fromMap, handler);
        }
    }
}
