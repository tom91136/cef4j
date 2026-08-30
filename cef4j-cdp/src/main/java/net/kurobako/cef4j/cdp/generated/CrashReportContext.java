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
 * This domain exposes the current state of the CrashReportContext API.
 * <p><b>Experimental:</b> this part of CDP may change without notice.
 * @see <a href="https://chromium.googlesource.com/chromium/src/+/refs/tags/150.0.7871.213/third_party/blink/public/devtools_protocol/domains/CrashReportContext.pdl">Pinned protocol source</a>
 */
@Generated("./mvnw generate-sources -pl cef4j-cdp -Dcef.version=150.0.18+gdb11278+chromium-150.0.7871.213")
@SuppressWarnings({"EscapedEntity", "InvalidParam", "JavaLangClash", "MissingSummary", "Unchecked", "UnusedMethod"})
public final class CrashReportContext {
    private CrashReportContext() {}
    /**
     * Key-value pair in CrashReportContext.
     */
    public static final class CrashReportContextEntry extends CdpObject {
        public CrashReportContextEntry() {}
        private CrashReportContextEntry(Map<String, Object> values) { super(values); }
        public static CrashReportContextEntry fromMap(Map<String, Object> values) {
            return new CrashReportContextEntry(values);
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
         * The ID of the frame where the key-value pair was set.
         * @return the protocol field value
         */
        public Page.FrameId frameId() {
            return new Page.FrameId((String) require("frameId"));
        }
        /**
         * Sets the key field.
         * @param key field value
         * @return this model
         */
        public CrashReportContextEntry key(String key) {
            set("key", key);
            return this;
        }
        /**
         * Sets the value field.
         * @param value field value
         * @return this model
         */
        public CrashReportContextEntry value(String value) {
            set("value", value);
            return this;
        }
        /**
         * The ID of the frame where the key-value pair was set.
         * @param frameId field value
         * @return this model
         */
        public CrashReportContextEntry frameId(Page.FrameId frameId) {
            set("frameId", frameId);
            return this;
        }
    }
    public static final class Client {
        private final CdpClient client;
        public Client(CdpClient client) { this.client = client; }
        /**
         * Returns all entries in the CrashReportContext across all frames in the page.
         * @return a stage completing with the command result
         */
        public CompletionStage<java.util.List<CrashReportContext.CrashReportContextEntry>> getEntries() {
            return client.call("CrashReportContext.getEntries", null, result_ -> CdpObject.requireList(java.util.Objects.requireNonNull(result_.get("entries")), element0 -> java.util.Objects.requireNonNull(CrashReportContext.CrashReportContextEntry.fromMap(java.util.Objects.requireNonNull(CdpObject.objectMap(element0))))));
        }
    }
}
