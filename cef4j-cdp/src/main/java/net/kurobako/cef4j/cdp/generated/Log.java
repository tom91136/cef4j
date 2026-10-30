// GENERATED - do not edit. Regenerate via: ./mvnw generate-sources -pl cef4j-cdp -Dcef.version=150.0.18+gdb11278+chromium-150.0.7871.213
package net.kurobako.cef4j.cdp.generated;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Optional;
import java.util.OptionalLong;
import java.util.concurrent.CompletionStage;
import java.util.function.Consumer;
import javax.annotation.processing.Generated;
import net.kurobako.cef4j.cdp.CdpClient;
import net.kurobako.cef4j.cdp.CdpObject;
import net.kurobako.cef4j.cdp.CdpSubscription;
import net.kurobako.cef4j.cdp.CdpValue;
import net.kurobako.cef4j.policy.NullableBoundary;

/**
 * Provides access to log entries.
 * @see <a href="https://chromium.googlesource.com/chromium/src/+/refs/tags/150.0.7871.213/third_party/blink/public/devtools_protocol/domains/Log.pdl">Pinned protocol source</a>
 */
@Generated("./mvnw generate-sources -pl cef4j-cdp -Dcef.version=150.0.18+gdb11278+chromium-150.0.7871.213")
@SuppressWarnings({"EscapedEntity", "InvalidParam", "JavaLangClash", "MissingSummary", "Unchecked", "UnusedMethod"})
@NullableBoundary("CDP wire objects use null for absent optional fields")
public final class Log {
    private Log() {}
    /**
     * Log entry.
     */
    public static final class LogEntry extends CdpObject {
        public LogEntry() {}
        private LogEntry(Map<String, Object> values) { super(values); }
        public static LogEntry fromMap(Map<String, Object> values) {
            return new LogEntry(values);
        }
        /**
         * Log entry source.
         */
        public enum SourceValues implements CdpValue<String> {
            XML("xml"),
            JAVASCRIPT("javascript"),
            NETWORK("network"),
            STORAGE("storage"),
            APPCACHE("appcache"),
            RENDERING("rendering"),
            SECURITY("security"),
            DEPRECATION("deprecation"),
            WORKER("worker"),
            VIOLATION("violation"),
            INTERVENTION("intervention"),
            RECOMMENDATION("recommendation"),
            OTHER("other");
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
         * Log entry severity.
         */
        public enum LevelValues implements CdpValue<String> {
            VERBOSE("verbose"),
            INFO("info"),
            WARNING("warning"),
            ERROR("error");
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
         * Wire values for CategoryValues.
         */
        public enum CategoryValues implements CdpValue<String> {
            CORS("cors");
            public final String value;
            CategoryValues(String value) { this.value = value; }
            @Override public String value() { return value; }
            public static CategoryValues of(String value) {
                for (CategoryValues constant : values()) {
                    if (constant.value.equals(value)) return constant;
                }
                throw new IllegalArgumentException("Unknown CategoryValues value: " + value);
            }
        }
        /**
         * Log entry source.
         * @return the protocol field value
         */
        public LogEntry.SourceValues source() {
            return LogEntry.SourceValues.of((String) require("source"));
        }
        /**
         * Log entry severity.
         * @return the protocol field value
         */
        public LogEntry.LevelValues level() {
            return LogEntry.LevelValues.of((String) require("level"));
        }
        /**
         * Logged text.
         * @return the protocol field value
         */
        public String text() {
            return (String) require("text");
        }
        /**
         * Returns the category field.
         * @return the protocol field value, empty when absent
         */
        public Optional<LogEntry.CategoryValues> category() {
            return raw("category").map(value -> LogEntry.CategoryValues.of((String) value));
        }
        /**
         * Timestamp when this entry was added.
         * @return the protocol field value
         */
        public Runtime.Timestamp timestamp() {
            return new Runtime.Timestamp(((Number) require("timestamp")).doubleValue());
        }
        /**
         * URL of the resource if known.
         * @return the protocol field value, empty when absent
         */
        public Optional<String> url() {
            return raw("url").map(value -> (String) value);
        }
        /**
         * Line number in the resource.
         * @return the protocol field value, empty when absent
         */
        public OptionalLong lineNumber() {
            return raw("lineNumber").flatMap(CdpObject::numberAsLong).stream().mapToLong(Long::longValue).findFirst();
        }
        /**
         * JavaScript stack trace.
         * @return the protocol field value, empty when absent
         */
        public Optional<Runtime.StackTrace> stackTrace() {
            return raw("stackTrace").flatMap(CdpObject::objectMap).map(Runtime.StackTrace::fromMap);
        }
        /**
         * Identifier of the network request associated with this entry.
         * @return the protocol field value, empty when absent
         */
        public Optional<Network.RequestId> networkRequestId() {
            return raw("networkRequestId").map(value -> new Network.RequestId((String) value));
        }
        /**
         * Identifier of the worker associated with this entry.
         * @return the protocol field value, empty when absent
         */
        public Optional<String> workerId() {
            return raw("workerId").map(value -> (String) value);
        }
        /**
         * Call arguments.
         * @return the protocol field value, empty when absent
         */
        public Optional<java.util.List<Runtime.RemoteObject>> args() {
            return raw("args").flatMap(value -> list(value, element0 -> java.util.Objects.requireNonNull(Runtime.RemoteObject.fromMap(CdpObject.requireObjectMap(element0)))));
        }
        /**
         * Log entry source.
         * @param source field value
         * @return this model
         */
        public LogEntry source(LogEntry.SourceValues source) {
            set("source", source);
            return this;
        }
        /**
         * Log entry severity.
         * @param level field value
         * @return this model
         */
        public LogEntry level(LogEntry.LevelValues level) {
            set("level", level);
            return this;
        }
        /**
         * Logged text.
         * @param text field value
         * @return this model
         */
        public LogEntry text(String text) {
            set("text", text);
            return this;
        }
        /**
         * Sets the category field.
         * @param category field value
         * @return this model
         */
        public LogEntry category(LogEntry.CategoryValues category) {
            set("category", category);
            return this;
        }
        /**
         * Timestamp when this entry was added.
         * @param timestamp field value
         * @return this model
         */
        public LogEntry timestamp(Runtime.Timestamp timestamp) {
            set("timestamp", timestamp);
            return this;
        }
        /**
         * URL of the resource if known.
         * @param url field value
         * @return this model
         */
        public LogEntry url(String url) {
            set("url", url);
            return this;
        }
        /**
         * Line number in the resource.
         * @param lineNumber field value
         * @return this model
         */
        public LogEntry lineNumber(long lineNumber) {
            set("lineNumber", lineNumber);
            return this;
        }
        /**
         * JavaScript stack trace.
         * @param stackTrace field value
         * @return this model
         */
        public LogEntry stackTrace(Runtime.StackTrace stackTrace) {
            set("stackTrace", stackTrace);
            return this;
        }
        /**
         * Identifier of the network request associated with this entry.
         * @param networkRequestId field value
         * @return this model
         */
        public LogEntry networkRequestId(Network.RequestId networkRequestId) {
            set("networkRequestId", networkRequestId);
            return this;
        }
        /**
         * Identifier of the worker associated with this entry.
         * @param workerId field value
         * @return this model
         */
        public LogEntry workerId(String workerId) {
            set("workerId", workerId);
            return this;
        }
        /**
         * Call arguments.
         * @param args field value
         * @return this model
         */
        public LogEntry args(java.util.List<Runtime.RemoteObject> args) {
            set("args", args);
            return this;
        }
    }
    /**
     * Violation configuration setting.
     */
    public static final class ViolationSetting extends CdpObject {
        public ViolationSetting() {}
        private ViolationSetting(Map<String, Object> values) { super(values); }
        public static ViolationSetting fromMap(Map<String, Object> values) {
            return new ViolationSetting(values);
        }
        /**
         * Violation type.
         */
        public enum NameValues implements CdpValue<String> {
            LONGTASK("longTask"),
            LONGLAYOUT("longLayout"),
            BLOCKEDEVENT("blockedEvent"),
            BLOCKEDPARSER("blockedParser"),
            DISCOURAGEDAPIUSE("discouragedAPIUse"),
            HANDLER("handler"),
            RECURRINGHANDLER("recurringHandler");
            public final String value;
            NameValues(String value) { this.value = value; }
            @Override public String value() { return value; }
            public static NameValues of(String value) {
                for (NameValues constant : values()) {
                    if (constant.value.equals(value)) return constant;
                }
                throw new IllegalArgumentException("Unknown NameValues value: " + value);
            }
        }
        /**
         * Violation type.
         * @return the protocol field value
         */
        public ViolationSetting.NameValues name() {
            return ViolationSetting.NameValues.of((String) require("name"));
        }
        /**
         * Time threshold to trigger upon.
         * @return the protocol field value
         */
        public double threshold() {
            return ((Number) require("threshold")).doubleValue();
        }
        /**
         * Violation type.
         * @param name field value
         * @return this model
         */
        public ViolationSetting name(ViolationSetting.NameValues name) {
            set("name", name);
            return this;
        }
        /**
         * Time threshold to trigger upon.
         * @param threshold field value
         * @return this model
         */
        public ViolationSetting threshold(double threshold) {
            set("threshold", threshold);
            return this;
        }
    }
    /**
     * start violation reporting.
     */
    public static final class StartViolationsReportRequest extends CdpObject {
        public StartViolationsReportRequest() {}
        /**
         * start violation reporting.
         * @param config protocol value
         */
        public StartViolationsReportRequest(java.util.List<Log.ViolationSetting> config) {
            config(config);
        }
        public static StartViolationsReportRequest fromMap(Map<String, Object> values) {
            final StartViolationsReportRequest instance_ = new StartViolationsReportRequest();
            if (values != null) instance_.values.putAll(values);
            return instance_;
        }
        /**
         * Configuration for violations.
         * @return the protocol field value
         */
        public java.util.List<Log.ViolationSetting> config() {
            return CdpObject.requireList(require("config"), element0 -> java.util.Objects.requireNonNull(Log.ViolationSetting.fromMap(CdpObject.requireObjectMap(element0))));
        }
        /**
         * Configuration for violations.
         * @param config field value
         * @return this model
         */
        public StartViolationsReportRequest config(java.util.List<Log.ViolationSetting> config) {
            set("config", config);
            return this;
        }
    }
    /**
     * Issued when new message was logged.
     */
    public static final class EntryAddedEvent extends CdpObject {
        public EntryAddedEvent() {}
        private EntryAddedEvent(Map<String, Object> values) { super(values); }
        public static EntryAddedEvent fromMap(Map<String, Object> values) {
            return new EntryAddedEvent(values);
        }
        /**
         * The entry.
         * @return the protocol field value
         */
        public Log.LogEntry entry() {
            return java.util.Objects.requireNonNull(Log.LogEntry.fromMap(CdpObject.requireObjectMap(require("entry"))));
        }
        /**
         * The entry.
         * @param entry field value
         * @return this model
         */
        public EntryAddedEvent entry(Log.LogEntry entry) {
            set("entry", entry);
            return this;
        }
    }
    public static final class Client {
        private final CdpClient client;
        public Client(CdpClient client) { this.client = client; }
        /**
         * Clears the log.
         * @return a stage completing when the command completes
         */
        public CompletionStage<Void> clear() {
            return client.call("Log.clear", null);
        }
        /**
         * Disables log domain, prevents further log entries from being reported to the client.
         * @return a stage completing when the command completes
         */
        public CompletionStage<Void> disable() {
            return client.call("Log.disable", null);
        }
        /**
         * Enables log domain, sends the entries collected so far to the client by means of the {@code entryAdded} notification.
         * @return a stage completing when the command completes
         */
        public CompletionStage<Void> enable() {
            return client.call("Log.enable", null);
        }
        /**
         * start violation reporting.
         * @param config protocol value
         * @return a stage completing when the command completes
         */
        public CompletionStage<Void> startViolationsReport(java.util.List<Log.ViolationSetting> config) {
            final Map<String, Object> params = new LinkedHashMap<>();
            params.put("config", CdpObject.json(config));
            return client.call("Log.startViolationsReport", params);
        }
        /**
         * start violation reporting.
         * @param request request parameters
         * @return a stage completing when the command completes
         */
        public CompletionStage<Void> startViolationsReport(StartViolationsReportRequest request) {
            return client.call("Log.startViolationsReport", request == null ? null : request.toMap());
        }
        /**
         * Stop violation reporting.
         * @return a stage completing when the command completes
         */
        public CompletionStage<Void> stopViolationsReport() {
            return client.call("Log.stopViolationsReport", null);
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
