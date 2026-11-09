// GENERATED - do not edit. Regenerate via: ./mvnw generate-sources -pl cef4j-cdp -Dcef.version=150.0.18+gdb11278+chromium-150.0.7871.213
package net.kurobako.cef4j.cdp.generated;

import java.util.Map;
import java.util.concurrent.CompletionStage;
import javax.annotation.processing.Generated;
import net.kurobako.cef4j.cdp.CdpClient;
import net.kurobako.cef4j.cdp.CdpDomainClient;
import net.kurobako.cef4j.cdp.CdpField;
import net.kurobako.cef4j.cdp.CdpObject;
import net.kurobako.cef4j.policy.NullableBoundary;

/**
 * This domain exposes the current state of the CrashReportContext API.
 * <p><b>Experimental:</b> this part of CDP may change without notice.
 * @see <a href="https://chromium.googlesource.com/chromium/src/+/refs/tags/150.0.7871.213/third_party/blink/public/devtools_protocol/domains/CrashReportContext.pdl">Pinned protocol source</a>
 */
@Generated("./mvnw generate-sources -pl cef4j-cdp -Dcef.version=150.0.18+gdb11278+chromium-150.0.7871.213")
@SuppressWarnings({"EscapedEntity", "InvalidParam", "JavaLangClash", "MissingSummary", "Unchecked", "UnusedMethod"})
@NullableBoundary("CDP wire objects use null for absent optional fields")
public final class CrashReportContext {
    private CrashReportContext() {}
    private static final CdpField<java.util.List<CrashReportContext.CrashReportContextEntry>> ENTRIES = CdpField.of("entries", value -> CdpObject.requireList(value, element0 -> java.util.Objects.requireNonNull(CrashReportContext.CrashReportContextEntry.fromMap(CdpObject.requireObjectMap(element0)))));
    private static final CdpField<Page.FrameId> FRAME_ID = CdpField.of("frameId", value -> new Page.FrameId((String) value));
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
            return requireString("key");
        }
        /**
         * Returns the value field.
         * @return the protocol field value
         */
        public String value() {
            return requireString("value");
        }
        /**
         * The ID of the frame where the key-value pair was set.
         * @return the protocol field value
         */
        public Page.FrameId frameId() {
            return require(FRAME_ID);
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
    public static final class Client extends CdpDomainClient {
        public Client(CdpClient client) { super(client); }
        /**
         * Returns all entries in the CrashReportContext across all frames in the page.
         * @return a stage completing with the command result
         */
        public CompletionStage<java.util.List<CrashReportContext.CrashReportContextEntry>> getEntries() {
            return call("CrashReportContext.getEntries", ENTRIES::require);
        }
    }
}
