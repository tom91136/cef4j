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
 * This domain exposes the current state of the CrashReportContext API.
 * <p><b>Experimental:</b> this part of CDP may change without notice.
 * @see <a href="https://chromium.googlesource.com/chromium/src/+/refs/tags/150.0.7871.213/third_party/blink/public/devtools_protocol/domains/CrashReportContext.pdl">Pinned protocol source</a>
 */
@SuppressWarnings({"EscapedEntity", "JavaLangClash", "MissingSummary", "UnusedMethod"})
public final class CrashReportContext {
    private CrashReportContext() {}
    @Nullable private static Long numberAsLong(@Nullable Object value) { return value == null ? null : ((Number) value).longValue(); }
    @Nullable private static Double numberAsDouble(@Nullable Object value) { return value == null ? null : ((Number) value).doubleValue(); }
    /**
     * Key-value pair in CrashReportContext.
     */
    public static final class CrashReportContextEntry extends CdpObject {
        private CrashReportContextEntry(Map<String, Object> values) { super(values); }
        @Nullable public static CrashReportContextEntry fromMap(@Nullable Map<String, Object> values) {
            return values == null ? null : new CrashReportContextEntry(values);
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
        /**
         * The ID of the frame where the key-value pair was set.
         * @return the protocol field value
         */
        @Nullable public String frameId() {
            return (String) value("frameId");
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
            /**
             * The ID of the frame where the key-value pair was set.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder frameId(@Nullable String value) {
                if (value == null) values.remove("frameId");
                else values.put("frameId", jsonValue(value));
                return this;
            }
            public CrashReportContextEntry build() {
                if (!values.containsKey("key")) throw new IllegalStateException("Missing required CDP field: key");
                if (!values.containsKey("value")) throw new IllegalStateException("Missing required CDP field: value");
                if (!values.containsKey("frameId")) throw new IllegalStateException("Missing required CDP field: frameId");
                return new CrashReportContextEntry(values);
            }
        }
    }
    /**
     * Returns all entries in the CrashReportContext across all frames in the page.
     */
    public static final class GetEntriesParams extends CdpObject {
        private GetEntriesParams(Map<String, Object> values) { super(values); }
        @Nullable public static GetEntriesParams fromMap(@Nullable Map<String, Object> values) {
            return values == null ? null : new GetEntriesParams(values);
        }
        public static Builder builder() { return new Builder(); }
        public static final class Builder {
            private final Map<String, Object> values = new LinkedHashMap<>();
            public GetEntriesParams build() {
                return new GetEntriesParams(values);
            }
        }
    }
    /**
     * Returns all entries in the CrashReportContext across all frames in the page.
     */
    public static final class GetEntriesResult extends CdpObject {
        private GetEntriesResult(Map<String, Object> values) { super(values); }
        @Nullable public static GetEntriesResult fromMap(@Nullable Map<String, Object> values) {
            return values == null ? null : new GetEntriesResult(values);
        }
        public static Builder builder() { return new Builder(); }
        /**
         * Returns the entries field.
         * @return the protocol field value
         */
        @Nullable public java.util.List<CrashReportContext.CrashReportContextEntry> entries() {
            return list(value("entries"), element0 -> CrashReportContext.CrashReportContextEntry.fromMap(objectMap(element0)));
        }
        public static final class Builder {
            private final Map<String, Object> values = new LinkedHashMap<>();
            /**
             * Sets the entries field.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder entries(@Nullable java.util.List<CrashReportContext.CrashReportContextEntry> value) {
                if (value == null) values.remove("entries");
                else values.put("entries", jsonValue(value));
                return this;
            }
            public GetEntriesResult build() {
                if (!values.containsKey("entries")) throw new IllegalStateException("Missing required CDP field: entries");
                return new GetEntriesResult(values);
            }
        }
    }
    public static final class Client {
        private final CdpClient client;
        public Client(CdpClient client) { this.client = client; }
        /**
         * Returns all entries in the CrashReportContext across all frames in the page.
         * @return a stage completing with the command result
         */
        public CompletionStage<GetEntriesResult> getEntries() {
            return client.call("CrashReportContext.getEntries", null, GetEntriesResult::fromMap);
        }
    }
}
