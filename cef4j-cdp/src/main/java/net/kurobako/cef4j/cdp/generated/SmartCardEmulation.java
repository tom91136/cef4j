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
 * Chrome DevTools Protocol SmartCardEmulation domain.
 * <p><b>Experimental:</b> this part of CDP may change without notice.
 * @see <a href="https://chromium.googlesource.com/chromium/src/+/refs/tags/146.0.7680.165/third_party/blink/public/devtools_protocol/domains/SmartCardEmulation.pdl">Pinned protocol source</a>
 */
@SuppressWarnings({"JavaLangClash", "UnusedMethod"})
public final class SmartCardEmulation {
    private SmartCardEmulation() {}
    @Nullable private static Long numberAsLong(@Nullable Object value) { return value == null ? null : ((Number) value).longValue(); }
    @Nullable private static Double numberAsDouble(@Nullable Object value) { return value == null ? null : ((Number) value).doubleValue(); }
    /**
     * Indicates the PC/SC error code.
     * <p>This maps to: PC/SC Lite: https://pcsclite.apdu.fr/api/group__ErrorCodes.html Microsoft: https://learn.microsoft.com/en-us/windows/win32/secauthn/authentication-return-values
     */
    public static final class ResultCode {
        private ResultCode() {}
        public static final String SUCCESS = "success";
        public static final String REMOVED_CARD = "removed-card";
        public static final String RESET_CARD = "reset-card";
        public static final String UNPOWERED_CARD = "unpowered-card";
        public static final String UNRESPONSIVE_CARD = "unresponsive-card";
        public static final String UNSUPPORTED_CARD = "unsupported-card";
        public static final String READER_UNAVAILABLE = "reader-unavailable";
        public static final String SHARING_VIOLATION = "sharing-violation";
        public static final String NOT_TRANSACTED = "not-transacted";
        public static final String NO_SMARTCARD = "no-smartcard";
        public static final String PROTO_MISMATCH = "proto-mismatch";
        public static final String SYSTEM_CANCELLED = "system-cancelled";
        public static final String NOT_READY = "not-ready";
        public static final String CANCELLED = "cancelled";
        public static final String INSUFFICIENT_BUFFER = "insufficient-buffer";
        public static final String INVALID_HANDLE = "invalid-handle";
        public static final String INVALID_PARAMETER = "invalid-parameter";
        public static final String INVALID_VALUE = "invalid-value";
        public static final String NO_MEMORY = "no-memory";
        public static final String TIMEOUT = "timeout";
        public static final String UNKNOWN_READER = "unknown-reader";
        public static final String UNSUPPORTED_FEATURE = "unsupported-feature";
        public static final String NO_READERS_AVAILABLE = "no-readers-available";
        public static final String SERVICE_STOPPED = "service-stopped";
        public static final String NO_SERVICE = "no-service";
        public static final String COMM_ERROR = "comm-error";
        public static final String INTERNAL_ERROR = "internal-error";
        public static final String SERVER_TOO_BUSY = "server-too-busy";
        public static final String UNEXPECTED = "unexpected";
        public static final String SHUTDOWN = "shutdown";
        public static final String UNKNOWN_CARD = "unknown-card";
        public static final String UNKNOWN = "unknown";
    }
    /**
     * Maps to the |SCARD_SHARE_*| values.
     */
    public static final class ShareMode {
        private ShareMode() {}
        public static final String SHARED = "shared";
        public static final String EXCLUSIVE = "exclusive";
        public static final String DIRECT = "direct";
    }
    /**
     * Indicates what the reader should do with the card.
     */
    public static final class Disposition {
        private Disposition() {}
        public static final String LEAVE_CARD = "leave-card";
        public static final String RESET_CARD = "reset-card";
        public static final String UNPOWER_CARD = "unpower-card";
        public static final String EJECT_CARD = "eject-card";
    }
    /**
     * Maps to |SCARD_*| connection state values.
     */
    public static final class ConnectionState {
        private ConnectionState() {}
        public static final String ABSENT = "absent";
        public static final String PRESENT = "present";
        public static final String SWALLOWED = "swallowed";
        public static final String POWERED = "powered";
        public static final String NEGOTIABLE = "negotiable";
        public static final String SPECIFIC = "specific";
    }
    /**
     * Maps to the |SCARD_STATE_*| flags.
     */
    public static final class ReaderStateFlags extends CdpObject {
        private ReaderStateFlags(Map<String, Object> values) { super(values); }
        @Nullable public static ReaderStateFlags fromMap(@Nullable Map<String, Object> values) {
            return values == null ? null : new ReaderStateFlags(values);
        }
        public static Builder builder() { return new Builder(); }
        /**
         * Returns the unaware field.
         * @return the protocol field value
         */
        @Nullable public Boolean unaware() {
            return (Boolean) value("unaware");
        }
        /**
         * Returns the ignore field.
         * @return the protocol field value
         */
        @Nullable public Boolean ignore() {
            return (Boolean) value("ignore");
        }
        /**
         * Returns the changed field.
         * @return the protocol field value
         */
        @Nullable public Boolean changed() {
            return (Boolean) value("changed");
        }
        /**
         * Returns the unknown field.
         * @return the protocol field value
         */
        @Nullable public Boolean unknown() {
            return (Boolean) value("unknown");
        }
        /**
         * Returns the unavailable field.
         * @return the protocol field value
         */
        @Nullable public Boolean unavailable() {
            return (Boolean) value("unavailable");
        }
        /**
         * Returns the empty field.
         * @return the protocol field value
         */
        @Nullable public Boolean empty() {
            return (Boolean) value("empty");
        }
        /**
         * Returns the present field.
         * @return the protocol field value
         */
        @Nullable public Boolean present() {
            return (Boolean) value("present");
        }
        /**
         * Returns the exclusive field.
         * @return the protocol field value
         */
        @Nullable public Boolean exclusive() {
            return (Boolean) value("exclusive");
        }
        /**
         * Returns the inuse field.
         * @return the protocol field value
         */
        @Nullable public Boolean inuse() {
            return (Boolean) value("inuse");
        }
        /**
         * Returns the mute field.
         * @return the protocol field value
         */
        @Nullable public Boolean mute() {
            return (Boolean) value("mute");
        }
        /**
         * Returns the unpowered field.
         * @return the protocol field value
         */
        @Nullable public Boolean unpowered() {
            return (Boolean) value("unpowered");
        }
        public static final class Builder {
            private final Map<String, Object> values = new LinkedHashMap<>();
            /**
             * Sets the unaware field.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder unaware(@Nullable Boolean value) {
                if (value == null) values.remove("unaware");
                else values.put("unaware", jsonValue(value));
                return this;
            }
            /**
             * Sets the ignore field.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder ignore(@Nullable Boolean value) {
                if (value == null) values.remove("ignore");
                else values.put("ignore", jsonValue(value));
                return this;
            }
            /**
             * Sets the changed field.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder changed(@Nullable Boolean value) {
                if (value == null) values.remove("changed");
                else values.put("changed", jsonValue(value));
                return this;
            }
            /**
             * Sets the unknown field.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder unknown(@Nullable Boolean value) {
                if (value == null) values.remove("unknown");
                else values.put("unknown", jsonValue(value));
                return this;
            }
            /**
             * Sets the unavailable field.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder unavailable(@Nullable Boolean value) {
                if (value == null) values.remove("unavailable");
                else values.put("unavailable", jsonValue(value));
                return this;
            }
            /**
             * Sets the empty field.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder empty(@Nullable Boolean value) {
                if (value == null) values.remove("empty");
                else values.put("empty", jsonValue(value));
                return this;
            }
            /**
             * Sets the present field.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder present(@Nullable Boolean value) {
                if (value == null) values.remove("present");
                else values.put("present", jsonValue(value));
                return this;
            }
            /**
             * Sets the exclusive field.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder exclusive(@Nullable Boolean value) {
                if (value == null) values.remove("exclusive");
                else values.put("exclusive", jsonValue(value));
                return this;
            }
            /**
             * Sets the inuse field.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder inuse(@Nullable Boolean value) {
                if (value == null) values.remove("inuse");
                else values.put("inuse", jsonValue(value));
                return this;
            }
            /**
             * Sets the mute field.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder mute(@Nullable Boolean value) {
                if (value == null) values.remove("mute");
                else values.put("mute", jsonValue(value));
                return this;
            }
            /**
             * Sets the unpowered field.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder unpowered(@Nullable Boolean value) {
                if (value == null) values.remove("unpowered");
                else values.put("unpowered", jsonValue(value));
                return this;
            }
            public ReaderStateFlags build() {
                return new ReaderStateFlags(values);
            }
        }
    }
    /**
     * Maps to the |SCARD_PROTOCOL_*| flags.
     */
    public static final class ProtocolSet extends CdpObject {
        private ProtocolSet(Map<String, Object> values) { super(values); }
        @Nullable public static ProtocolSet fromMap(@Nullable Map<String, Object> values) {
            return values == null ? null : new ProtocolSet(values);
        }
        public static Builder builder() { return new Builder(); }
        /**
         * Returns the t0 field.
         * @return the protocol field value
         */
        @Nullable public Boolean t0() {
            return (Boolean) value("t0");
        }
        /**
         * Returns the t1 field.
         * @return the protocol field value
         */
        @Nullable public Boolean t1() {
            return (Boolean) value("t1");
        }
        /**
         * Returns the raw field.
         * @return the protocol field value
         */
        @Nullable public Boolean raw() {
            return (Boolean) value("raw");
        }
        public static final class Builder {
            private final Map<String, Object> values = new LinkedHashMap<>();
            /**
             * Sets the t0 field.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder t0(@Nullable Boolean value) {
                if (value == null) values.remove("t0");
                else values.put("t0", jsonValue(value));
                return this;
            }
            /**
             * Sets the t1 field.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder t1(@Nullable Boolean value) {
                if (value == null) values.remove("t1");
                else values.put("t1", jsonValue(value));
                return this;
            }
            /**
             * Sets the raw field.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder raw(@Nullable Boolean value) {
                if (value == null) values.remove("raw");
                else values.put("raw", jsonValue(value));
                return this;
            }
            public ProtocolSet build() {
                return new ProtocolSet(values);
            }
        }
    }
    /**
     * Maps to the |SCARD_PROTOCOL_*| values.
     */
    public static final class Protocol {
        private Protocol() {}
        public static final String T0 = "t0";
        public static final String T1 = "t1";
        public static final String RAW = "raw";
    }
    /**
     */
    public static final class ReaderStateIn extends CdpObject {
        private ReaderStateIn(Map<String, Object> values) { super(values); }
        @Nullable public static ReaderStateIn fromMap(@Nullable Map<String, Object> values) {
            return values == null ? null : new ReaderStateIn(values);
        }
        public static Builder builder() { return new Builder(); }
        /**
         * Returns the reader field.
         * @return the protocol field value
         */
        @Nullable public String reader() {
            return (String) value("reader");
        }
        /**
         * Returns the currentState field.
         * @return the protocol field value
         */
        @Nullable public SmartCardEmulation.ReaderStateFlags currentState() {
            return SmartCardEmulation.ReaderStateFlags.fromMap(objectMap(value("currentState")));
        }
        /**
         * Returns the currentInsertionCount field.
         * @return the protocol field value
         */
        @Nullable public Long currentInsertionCount() {
            return numberAsLong(value("currentInsertionCount"));
        }
        public static final class Builder {
            private final Map<String, Object> values = new LinkedHashMap<>();
            /**
             * Sets the reader field.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder reader(@Nullable String value) {
                if (value == null) values.remove("reader");
                else values.put("reader", jsonValue(value));
                return this;
            }
            /**
             * Sets the currentState field.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder currentState(@Nullable SmartCardEmulation.ReaderStateFlags value) {
                if (value == null) values.remove("currentState");
                else values.put("currentState", jsonValue(value));
                return this;
            }
            /**
             * Sets the currentInsertionCount field.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder currentInsertionCount(@Nullable Long value) {
                if (value == null) values.remove("currentInsertionCount");
                else values.put("currentInsertionCount", jsonValue(value));
                return this;
            }
            public ReaderStateIn build() {
                if (!values.containsKey("reader")) throw new IllegalStateException("Missing required CDP field: reader");
                if (!values.containsKey("currentState")) throw new IllegalStateException("Missing required CDP field: currentState");
                if (!values.containsKey("currentInsertionCount")) throw new IllegalStateException("Missing required CDP field: currentInsertionCount");
                return new ReaderStateIn(values);
            }
        }
    }
    /**
     */
    public static final class ReaderStateOut extends CdpObject {
        private ReaderStateOut(Map<String, Object> values) { super(values); }
        @Nullable public static ReaderStateOut fromMap(@Nullable Map<String, Object> values) {
            return values == null ? null : new ReaderStateOut(values);
        }
        public static Builder builder() { return new Builder(); }
        /**
         * Returns the reader field.
         * @return the protocol field value
         */
        @Nullable public String reader() {
            return (String) value("reader");
        }
        /**
         * Returns the eventState field.
         * @return the protocol field value
         */
        @Nullable public SmartCardEmulation.ReaderStateFlags eventState() {
            return SmartCardEmulation.ReaderStateFlags.fromMap(objectMap(value("eventState")));
        }
        /**
         * Returns the eventCount field.
         * @return the protocol field value
         */
        @Nullable public Long eventCount() {
            return numberAsLong(value("eventCount"));
        }
        /**
         * Returns the atr field.
         * @return the protocol field value
         */
        @Nullable public String atr() {
            return (String) value("atr");
        }
        public static final class Builder {
            private final Map<String, Object> values = new LinkedHashMap<>();
            /**
             * Sets the reader field.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder reader(@Nullable String value) {
                if (value == null) values.remove("reader");
                else values.put("reader", jsonValue(value));
                return this;
            }
            /**
             * Sets the eventState field.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder eventState(@Nullable SmartCardEmulation.ReaderStateFlags value) {
                if (value == null) values.remove("eventState");
                else values.put("eventState", jsonValue(value));
                return this;
            }
            /**
             * Sets the eventCount field.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder eventCount(@Nullable Long value) {
                if (value == null) values.remove("eventCount");
                else values.put("eventCount", jsonValue(value));
                return this;
            }
            /**
             * Sets the atr field.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder atr(@Nullable String value) {
                if (value == null) values.remove("atr");
                else values.put("atr", jsonValue(value));
                return this;
            }
            public ReaderStateOut build() {
                if (!values.containsKey("reader")) throw new IllegalStateException("Missing required CDP field: reader");
                if (!values.containsKey("eventState")) throw new IllegalStateException("Missing required CDP field: eventState");
                if (!values.containsKey("eventCount")) throw new IllegalStateException("Missing required CDP field: eventCount");
                if (!values.containsKey("atr")) throw new IllegalStateException("Missing required CDP field: atr");
                return new ReaderStateOut(values);
            }
        }
    }
    /**
     * Enables the |SmartCardEmulation| domain.
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
     * Enables the |SmartCardEmulation| domain.
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
     * Disables the |SmartCardEmulation| domain.
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
     * Disables the |SmartCardEmulation| domain.
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
     * Reports the successful result of a |SCardEstablishContext| call.
     * <p>This maps to: PC/SC Lite: https://pcsclite.apdu.fr/api/group__API.html#gaa1b8970169fd4883a6dc4a8f43f19b67 Microsoft: https://learn.microsoft.com/en-us/windows/win32/api/winscard/nf-winscard-scardestablishcontext
     */
    public static final class ReportEstablishContextResultParams extends CdpObject {
        private ReportEstablishContextResultParams(Map<String, Object> values) { super(values); }
        @Nullable public static ReportEstablishContextResultParams fromMap(@Nullable Map<String, Object> values) {
            return values == null ? null : new ReportEstablishContextResultParams(values);
        }
        public static Builder builder() { return new Builder(); }
        /**
         * Returns the requestId field.
         * @return the protocol field value
         */
        @Nullable public String requestId() {
            return (String) value("requestId");
        }
        /**
         * Returns the contextId field.
         * @return the protocol field value
         */
        @Nullable public Long contextId() {
            return numberAsLong(value("contextId"));
        }
        public static final class Builder {
            private final Map<String, Object> values = new LinkedHashMap<>();
            /**
             * Sets the requestId field.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder requestId(@Nullable String value) {
                if (value == null) values.remove("requestId");
                else values.put("requestId", jsonValue(value));
                return this;
            }
            /**
             * Sets the contextId field.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder contextId(@Nullable Long value) {
                if (value == null) values.remove("contextId");
                else values.put("contextId", jsonValue(value));
                return this;
            }
            public ReportEstablishContextResultParams build() {
                if (!values.containsKey("requestId")) throw new IllegalStateException("Missing required CDP field: requestId");
                if (!values.containsKey("contextId")) throw new IllegalStateException("Missing required CDP field: contextId");
                return new ReportEstablishContextResultParams(values);
            }
        }
    }
    /**
     * Reports the successful result of a |SCardEstablishContext| call.
     * <p>This maps to: PC/SC Lite: https://pcsclite.apdu.fr/api/group__API.html#gaa1b8970169fd4883a6dc4a8f43f19b67 Microsoft: https://learn.microsoft.com/en-us/windows/win32/api/winscard/nf-winscard-scardestablishcontext
     */
    public static final class ReportEstablishContextResultResult extends CdpObject {
        private ReportEstablishContextResultResult(Map<String, Object> values) { super(values); }
        @Nullable public static ReportEstablishContextResultResult fromMap(@Nullable Map<String, Object> values) {
            return values == null ? null : new ReportEstablishContextResultResult(values);
        }
        public static Builder builder() { return new Builder(); }
        public static final class Builder {
            private final Map<String, Object> values = new LinkedHashMap<>();
            public ReportEstablishContextResultResult build() {
                return new ReportEstablishContextResultResult(values);
            }
        }
    }
    /**
     * Reports the successful result of a |SCardReleaseContext| call.
     * <p>This maps to: PC/SC Lite: https://pcsclite.apdu.fr/api/group__API.html#ga6aabcba7744c5c9419fdd6404f73a934 Microsoft: https://learn.microsoft.com/en-us/windows/win32/api/winscard/nf-winscard-scardreleasecontext
     */
    public static final class ReportReleaseContextResultParams extends CdpObject {
        private ReportReleaseContextResultParams(Map<String, Object> values) { super(values); }
        @Nullable public static ReportReleaseContextResultParams fromMap(@Nullable Map<String, Object> values) {
            return values == null ? null : new ReportReleaseContextResultParams(values);
        }
        public static Builder builder() { return new Builder(); }
        /**
         * Returns the requestId field.
         * @return the protocol field value
         */
        @Nullable public String requestId() {
            return (String) value("requestId");
        }
        public static final class Builder {
            private final Map<String, Object> values = new LinkedHashMap<>();
            /**
             * Sets the requestId field.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder requestId(@Nullable String value) {
                if (value == null) values.remove("requestId");
                else values.put("requestId", jsonValue(value));
                return this;
            }
            public ReportReleaseContextResultParams build() {
                if (!values.containsKey("requestId")) throw new IllegalStateException("Missing required CDP field: requestId");
                return new ReportReleaseContextResultParams(values);
            }
        }
    }
    /**
     * Reports the successful result of a |SCardReleaseContext| call.
     * <p>This maps to: PC/SC Lite: https://pcsclite.apdu.fr/api/group__API.html#ga6aabcba7744c5c9419fdd6404f73a934 Microsoft: https://learn.microsoft.com/en-us/windows/win32/api/winscard/nf-winscard-scardreleasecontext
     */
    public static final class ReportReleaseContextResultResult extends CdpObject {
        private ReportReleaseContextResultResult(Map<String, Object> values) { super(values); }
        @Nullable public static ReportReleaseContextResultResult fromMap(@Nullable Map<String, Object> values) {
            return values == null ? null : new ReportReleaseContextResultResult(values);
        }
        public static Builder builder() { return new Builder(); }
        public static final class Builder {
            private final Map<String, Object> values = new LinkedHashMap<>();
            public ReportReleaseContextResultResult build() {
                return new ReportReleaseContextResultResult(values);
            }
        }
    }
    /**
     * Reports the successful result of a |SCardListReaders| call.
     * <p>This maps to: PC/SC Lite: https://pcsclite.apdu.fr/api/group__API.html#ga93b07815789b3cf2629d439ecf20f0d9 Microsoft: https://learn.microsoft.com/en-us/windows/win32/api/winscard/nf-winscard-scardlistreadersa
     */
    public static final class ReportListReadersResultParams extends CdpObject {
        private ReportListReadersResultParams(Map<String, Object> values) { super(values); }
        @Nullable public static ReportListReadersResultParams fromMap(@Nullable Map<String, Object> values) {
            return values == null ? null : new ReportListReadersResultParams(values);
        }
        public static Builder builder() { return new Builder(); }
        /**
         * Returns the requestId field.
         * @return the protocol field value
         */
        @Nullable public String requestId() {
            return (String) value("requestId");
        }
        /**
         * Returns the readers field.
         * @return the protocol field value
         */
        @Nullable public java.util.List<String> readers() {
            return list(value("readers"), element0 -> (String) element0);
        }
        public static final class Builder {
            private final Map<String, Object> values = new LinkedHashMap<>();
            /**
             * Sets the requestId field.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder requestId(@Nullable String value) {
                if (value == null) values.remove("requestId");
                else values.put("requestId", jsonValue(value));
                return this;
            }
            /**
             * Sets the readers field.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder readers(@Nullable java.util.List<String> value) {
                if (value == null) values.remove("readers");
                else values.put("readers", jsonValue(value));
                return this;
            }
            public ReportListReadersResultParams build() {
                if (!values.containsKey("requestId")) throw new IllegalStateException("Missing required CDP field: requestId");
                if (!values.containsKey("readers")) throw new IllegalStateException("Missing required CDP field: readers");
                return new ReportListReadersResultParams(values);
            }
        }
    }
    /**
     * Reports the successful result of a |SCardListReaders| call.
     * <p>This maps to: PC/SC Lite: https://pcsclite.apdu.fr/api/group__API.html#ga93b07815789b3cf2629d439ecf20f0d9 Microsoft: https://learn.microsoft.com/en-us/windows/win32/api/winscard/nf-winscard-scardlistreadersa
     */
    public static final class ReportListReadersResultResult extends CdpObject {
        private ReportListReadersResultResult(Map<String, Object> values) { super(values); }
        @Nullable public static ReportListReadersResultResult fromMap(@Nullable Map<String, Object> values) {
            return values == null ? null : new ReportListReadersResultResult(values);
        }
        public static Builder builder() { return new Builder(); }
        public static final class Builder {
            private final Map<String, Object> values = new LinkedHashMap<>();
            public ReportListReadersResultResult build() {
                return new ReportListReadersResultResult(values);
            }
        }
    }
    /**
     * Reports the successful result of a |SCardGetStatusChange| call.
     * <p>This maps to: PC/SC Lite: https://pcsclite.apdu.fr/api/group__API.html#ga33247d5d1257d59e55647c3bb717db24 Microsoft: https://learn.microsoft.com/en-us/windows/win32/api/winscard/nf-winscard-scardgetstatuschangea
     */
    public static final class ReportGetStatusChangeResultParams extends CdpObject {
        private ReportGetStatusChangeResultParams(Map<String, Object> values) { super(values); }
        @Nullable public static ReportGetStatusChangeResultParams fromMap(@Nullable Map<String, Object> values) {
            return values == null ? null : new ReportGetStatusChangeResultParams(values);
        }
        public static Builder builder() { return new Builder(); }
        /**
         * Returns the requestId field.
         * @return the protocol field value
         */
        @Nullable public String requestId() {
            return (String) value("requestId");
        }
        /**
         * Returns the readerStates field.
         * @return the protocol field value
         */
        @Nullable public java.util.List<SmartCardEmulation.ReaderStateOut> readerStates() {
            return list(value("readerStates"), element0 -> SmartCardEmulation.ReaderStateOut.fromMap(objectMap(element0)));
        }
        public static final class Builder {
            private final Map<String, Object> values = new LinkedHashMap<>();
            /**
             * Sets the requestId field.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder requestId(@Nullable String value) {
                if (value == null) values.remove("requestId");
                else values.put("requestId", jsonValue(value));
                return this;
            }
            /**
             * Sets the readerStates field.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder readerStates(@Nullable java.util.List<SmartCardEmulation.ReaderStateOut> value) {
                if (value == null) values.remove("readerStates");
                else values.put("readerStates", jsonValue(value));
                return this;
            }
            public ReportGetStatusChangeResultParams build() {
                if (!values.containsKey("requestId")) throw new IllegalStateException("Missing required CDP field: requestId");
                if (!values.containsKey("readerStates")) throw new IllegalStateException("Missing required CDP field: readerStates");
                return new ReportGetStatusChangeResultParams(values);
            }
        }
    }
    /**
     * Reports the successful result of a |SCardGetStatusChange| call.
     * <p>This maps to: PC/SC Lite: https://pcsclite.apdu.fr/api/group__API.html#ga33247d5d1257d59e55647c3bb717db24 Microsoft: https://learn.microsoft.com/en-us/windows/win32/api/winscard/nf-winscard-scardgetstatuschangea
     */
    public static final class ReportGetStatusChangeResultResult extends CdpObject {
        private ReportGetStatusChangeResultResult(Map<String, Object> values) { super(values); }
        @Nullable public static ReportGetStatusChangeResultResult fromMap(@Nullable Map<String, Object> values) {
            return values == null ? null : new ReportGetStatusChangeResultResult(values);
        }
        public static Builder builder() { return new Builder(); }
        public static final class Builder {
            private final Map<String, Object> values = new LinkedHashMap<>();
            public ReportGetStatusChangeResultResult build() {
                return new ReportGetStatusChangeResultResult(values);
            }
        }
    }
    /**
     * Reports the result of a |SCardBeginTransaction| call. On success, this creates a new transaction object.
     * <p>This maps to: PC/SC Lite: https://pcsclite.apdu.fr/api/group__API.html#gaddb835dce01a0da1d6ca02d33ee7d861 Microsoft: https://learn.microsoft.com/en-us/windows/win32/api/winscard/nf-winscard-scardbegintransaction
     */
    public static final class ReportBeginTransactionResultParams extends CdpObject {
        private ReportBeginTransactionResultParams(Map<String, Object> values) { super(values); }
        @Nullable public static ReportBeginTransactionResultParams fromMap(@Nullable Map<String, Object> values) {
            return values == null ? null : new ReportBeginTransactionResultParams(values);
        }
        public static Builder builder() { return new Builder(); }
        /**
         * Returns the requestId field.
         * @return the protocol field value
         */
        @Nullable public String requestId() {
            return (String) value("requestId");
        }
        /**
         * Returns the handle field.
         * @return the protocol field value
         */
        @Nullable public Long handle() {
            return numberAsLong(value("handle"));
        }
        public static final class Builder {
            private final Map<String, Object> values = new LinkedHashMap<>();
            /**
             * Sets the requestId field.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder requestId(@Nullable String value) {
                if (value == null) values.remove("requestId");
                else values.put("requestId", jsonValue(value));
                return this;
            }
            /**
             * Sets the handle field.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder handle(@Nullable Long value) {
                if (value == null) values.remove("handle");
                else values.put("handle", jsonValue(value));
                return this;
            }
            public ReportBeginTransactionResultParams build() {
                if (!values.containsKey("requestId")) throw new IllegalStateException("Missing required CDP field: requestId");
                if (!values.containsKey("handle")) throw new IllegalStateException("Missing required CDP field: handle");
                return new ReportBeginTransactionResultParams(values);
            }
        }
    }
    /**
     * Reports the result of a |SCardBeginTransaction| call. On success, this creates a new transaction object.
     * <p>This maps to: PC/SC Lite: https://pcsclite.apdu.fr/api/group__API.html#gaddb835dce01a0da1d6ca02d33ee7d861 Microsoft: https://learn.microsoft.com/en-us/windows/win32/api/winscard/nf-winscard-scardbegintransaction
     */
    public static final class ReportBeginTransactionResultResult extends CdpObject {
        private ReportBeginTransactionResultResult(Map<String, Object> values) { super(values); }
        @Nullable public static ReportBeginTransactionResultResult fromMap(@Nullable Map<String, Object> values) {
            return values == null ? null : new ReportBeginTransactionResultResult(values);
        }
        public static Builder builder() { return new Builder(); }
        public static final class Builder {
            private final Map<String, Object> values = new LinkedHashMap<>();
            public ReportBeginTransactionResultResult build() {
                return new ReportBeginTransactionResultResult(values);
            }
        }
    }
    /**
     * Reports the successful result of a call that returns only a result code. Used for: |SCardCancel|, |SCardDisconnect|, |SCardSetAttrib|, |SCardEndTransaction|.
     * <p>This maps to: 1. SCardCancel PC/SC Lite: https://pcsclite.apdu.fr/api/group__API.html#gaacbbc0c6d6c0cbbeb4f4debf6fbeeee6 Microsoft: https://learn.microsoft.com/en-us/windows/win32/api/winscard/nf-winscard-scardcancel
     * <p>2. SCardDisconnect PC/SC Lite: https://pcsclite.apdu.fr/api/group__API.html#ga4be198045c73ec0deb79e66c0ca1738a Microsoft: https://learn.microsoft.com/en-us/windows/win32/api/winscard/nf-winscard-scarddisconnect
     * <p>3. SCardSetAttrib PC/SC Lite: https://pcsclite.apdu.fr/api/group__API.html#ga060f0038a4ddfd5dd2b8fadf3c3a2e4f Microsoft: https://learn.microsoft.com/en-us/windows/win32/api/winscard/nf-winscard-scardsetattrib
     * <p>4. SCardEndTransaction PC/SC Lite: https://pcsclite.apdu.fr/api/group__API.html#gae8742473b404363e5c587f570d7e2f3b Microsoft: https://learn.microsoft.com/en-us/windows/win32/api/winscard/nf-winscard-scardendtransaction
     */
    public static final class ReportPlainResultParams extends CdpObject {
        private ReportPlainResultParams(Map<String, Object> values) { super(values); }
        @Nullable public static ReportPlainResultParams fromMap(@Nullable Map<String, Object> values) {
            return values == null ? null : new ReportPlainResultParams(values);
        }
        public static Builder builder() { return new Builder(); }
        /**
         * Returns the requestId field.
         * @return the protocol field value
         */
        @Nullable public String requestId() {
            return (String) value("requestId");
        }
        public static final class Builder {
            private final Map<String, Object> values = new LinkedHashMap<>();
            /**
             * Sets the requestId field.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder requestId(@Nullable String value) {
                if (value == null) values.remove("requestId");
                else values.put("requestId", jsonValue(value));
                return this;
            }
            public ReportPlainResultParams build() {
                if (!values.containsKey("requestId")) throw new IllegalStateException("Missing required CDP field: requestId");
                return new ReportPlainResultParams(values);
            }
        }
    }
    /**
     * Reports the successful result of a call that returns only a result code. Used for: |SCardCancel|, |SCardDisconnect|, |SCardSetAttrib|, |SCardEndTransaction|.
     * <p>This maps to: 1. SCardCancel PC/SC Lite: https://pcsclite.apdu.fr/api/group__API.html#gaacbbc0c6d6c0cbbeb4f4debf6fbeeee6 Microsoft: https://learn.microsoft.com/en-us/windows/win32/api/winscard/nf-winscard-scardcancel
     * <p>2. SCardDisconnect PC/SC Lite: https://pcsclite.apdu.fr/api/group__API.html#ga4be198045c73ec0deb79e66c0ca1738a Microsoft: https://learn.microsoft.com/en-us/windows/win32/api/winscard/nf-winscard-scarddisconnect
     * <p>3. SCardSetAttrib PC/SC Lite: https://pcsclite.apdu.fr/api/group__API.html#ga060f0038a4ddfd5dd2b8fadf3c3a2e4f Microsoft: https://learn.microsoft.com/en-us/windows/win32/api/winscard/nf-winscard-scardsetattrib
     * <p>4. SCardEndTransaction PC/SC Lite: https://pcsclite.apdu.fr/api/group__API.html#gae8742473b404363e5c587f570d7e2f3b Microsoft: https://learn.microsoft.com/en-us/windows/win32/api/winscard/nf-winscard-scardendtransaction
     */
    public static final class ReportPlainResultResult extends CdpObject {
        private ReportPlainResultResult(Map<String, Object> values) { super(values); }
        @Nullable public static ReportPlainResultResult fromMap(@Nullable Map<String, Object> values) {
            return values == null ? null : new ReportPlainResultResult(values);
        }
        public static Builder builder() { return new Builder(); }
        public static final class Builder {
            private final Map<String, Object> values = new LinkedHashMap<>();
            public ReportPlainResultResult build() {
                return new ReportPlainResultResult(values);
            }
        }
    }
    /**
     * Reports the successful result of a |SCardConnect| call.
     * <p>This maps to: PC/SC Lite: https://pcsclite.apdu.fr/api/group__API.html#ga4e515829752e0a8dbc4d630696a8d6a5 Microsoft: https://learn.microsoft.com/en-us/windows/win32/api/winscard/nf-winscard-scardconnecta
     */
    public static final class ReportConnectResultParams extends CdpObject {
        private ReportConnectResultParams(Map<String, Object> values) { super(values); }
        @Nullable public static ReportConnectResultParams fromMap(@Nullable Map<String, Object> values) {
            return values == null ? null : new ReportConnectResultParams(values);
        }
        public static Builder builder() { return new Builder(); }
        /**
         * Returns the requestId field.
         * @return the protocol field value
         */
        @Nullable public String requestId() {
            return (String) value("requestId");
        }
        /**
         * Returns the handle field.
         * @return the protocol field value
         */
        @Nullable public Long handle() {
            return numberAsLong(value("handle"));
        }
        /**
         * Returns the activeProtocol field.
         * @return the protocol field value
         */
        @Nullable public String activeProtocol() {
            return (String) value("activeProtocol");
        }
        public static final class Builder {
            private final Map<String, Object> values = new LinkedHashMap<>();
            /**
             * Sets the requestId field.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder requestId(@Nullable String value) {
                if (value == null) values.remove("requestId");
                else values.put("requestId", jsonValue(value));
                return this;
            }
            /**
             * Sets the handle field.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder handle(@Nullable Long value) {
                if (value == null) values.remove("handle");
                else values.put("handle", jsonValue(value));
                return this;
            }
            /**
             * Sets the activeProtocol field.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder activeProtocol(@Nullable String value) {
                if (value == null) values.remove("activeProtocol");
                else values.put("activeProtocol", jsonValue(value));
                return this;
            }
            public ReportConnectResultParams build() {
                if (!values.containsKey("requestId")) throw new IllegalStateException("Missing required CDP field: requestId");
                if (!values.containsKey("handle")) throw new IllegalStateException("Missing required CDP field: handle");
                return new ReportConnectResultParams(values);
            }
        }
    }
    /**
     * Reports the successful result of a |SCardConnect| call.
     * <p>This maps to: PC/SC Lite: https://pcsclite.apdu.fr/api/group__API.html#ga4e515829752e0a8dbc4d630696a8d6a5 Microsoft: https://learn.microsoft.com/en-us/windows/win32/api/winscard/nf-winscard-scardconnecta
     */
    public static final class ReportConnectResultResult extends CdpObject {
        private ReportConnectResultResult(Map<String, Object> values) { super(values); }
        @Nullable public static ReportConnectResultResult fromMap(@Nullable Map<String, Object> values) {
            return values == null ? null : new ReportConnectResultResult(values);
        }
        public static Builder builder() { return new Builder(); }
        public static final class Builder {
            private final Map<String, Object> values = new LinkedHashMap<>();
            public ReportConnectResultResult build() {
                return new ReportConnectResultResult(values);
            }
        }
    }
    /**
     * Reports the successful result of a call that sends back data on success. Used for |SCardTransmit|, |SCardControl|, and |SCardGetAttrib|.
     * <p>This maps to: 1. SCardTransmit PC/SC Lite: https://pcsclite.apdu.fr/api/group__API.html#ga9a2d77242a271310269065e64633ab99 Microsoft: https://learn.microsoft.com/en-us/windows/win32/api/winscard/nf-winscard-scardtransmit
     * <p>2. SCardControl PC/SC Lite: https://pcsclite.apdu.fr/api/group__API.html#gac3454d4657110fd7f753b2d3d8f4e32f Microsoft: https://learn.microsoft.com/en-us/windows/win32/api/winscard/nf-winscard-scardcontrol
     * <p>3. SCardGetAttrib PC/SC Lite: https://pcsclite.apdu.fr/api/group__API.html#gaacfec51917255b7a25b94c5104961602 Microsoft: https://learn.microsoft.com/en-us/windows/win32/api/winscard/nf-winscard-scardgetattrib
     */
    public static final class ReportDataResultParams extends CdpObject {
        private ReportDataResultParams(Map<String, Object> values) { super(values); }
        @Nullable public static ReportDataResultParams fromMap(@Nullable Map<String, Object> values) {
            return values == null ? null : new ReportDataResultParams(values);
        }
        public static Builder builder() { return new Builder(); }
        /**
         * Returns the requestId field.
         * @return the protocol field value
         */
        @Nullable public String requestId() {
            return (String) value("requestId");
        }
        /**
         * Returns the data field.
         * @return the protocol field value
         */
        @Nullable public String data() {
            return (String) value("data");
        }
        public static final class Builder {
            private final Map<String, Object> values = new LinkedHashMap<>();
            /**
             * Sets the requestId field.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder requestId(@Nullable String value) {
                if (value == null) values.remove("requestId");
                else values.put("requestId", jsonValue(value));
                return this;
            }
            /**
             * Sets the data field.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder data(@Nullable String value) {
                if (value == null) values.remove("data");
                else values.put("data", jsonValue(value));
                return this;
            }
            public ReportDataResultParams build() {
                if (!values.containsKey("requestId")) throw new IllegalStateException("Missing required CDP field: requestId");
                if (!values.containsKey("data")) throw new IllegalStateException("Missing required CDP field: data");
                return new ReportDataResultParams(values);
            }
        }
    }
    /**
     * Reports the successful result of a call that sends back data on success. Used for |SCardTransmit|, |SCardControl|, and |SCardGetAttrib|.
     * <p>This maps to: 1. SCardTransmit PC/SC Lite: https://pcsclite.apdu.fr/api/group__API.html#ga9a2d77242a271310269065e64633ab99 Microsoft: https://learn.microsoft.com/en-us/windows/win32/api/winscard/nf-winscard-scardtransmit
     * <p>2. SCardControl PC/SC Lite: https://pcsclite.apdu.fr/api/group__API.html#gac3454d4657110fd7f753b2d3d8f4e32f Microsoft: https://learn.microsoft.com/en-us/windows/win32/api/winscard/nf-winscard-scardcontrol
     * <p>3. SCardGetAttrib PC/SC Lite: https://pcsclite.apdu.fr/api/group__API.html#gaacfec51917255b7a25b94c5104961602 Microsoft: https://learn.microsoft.com/en-us/windows/win32/api/winscard/nf-winscard-scardgetattrib
     */
    public static final class ReportDataResultResult extends CdpObject {
        private ReportDataResultResult(Map<String, Object> values) { super(values); }
        @Nullable public static ReportDataResultResult fromMap(@Nullable Map<String, Object> values) {
            return values == null ? null : new ReportDataResultResult(values);
        }
        public static Builder builder() { return new Builder(); }
        public static final class Builder {
            private final Map<String, Object> values = new LinkedHashMap<>();
            public ReportDataResultResult build() {
                return new ReportDataResultResult(values);
            }
        }
    }
    /**
     * Reports the successful result of a |SCardStatus| call.
     * <p>This maps to: PC/SC Lite: https://pcsclite.apdu.fr/api/group__API.html#gae49c3c894ad7ac12a5b896bde70d0382 Microsoft: https://learn.microsoft.com/en-us/windows/win32/api/winscard/nf-winscard-scardstatusa
     */
    public static final class ReportStatusResultParams extends CdpObject {
        private ReportStatusResultParams(Map<String, Object> values) { super(values); }
        @Nullable public static ReportStatusResultParams fromMap(@Nullable Map<String, Object> values) {
            return values == null ? null : new ReportStatusResultParams(values);
        }
        public static Builder builder() { return new Builder(); }
        /**
         * Returns the requestId field.
         * @return the protocol field value
         */
        @Nullable public String requestId() {
            return (String) value("requestId");
        }
        /**
         * Returns the readerName field.
         * @return the protocol field value
         */
        @Nullable public String readerName() {
            return (String) value("readerName");
        }
        /**
         * Returns the state field.
         * @return the protocol field value
         */
        @Nullable public String state() {
            return (String) value("state");
        }
        /**
         * Returns the atr field.
         * @return the protocol field value
         */
        @Nullable public String atr() {
            return (String) value("atr");
        }
        /**
         * Returns the protocol field.
         * @return the protocol field value
         */
        @Nullable public String protocol() {
            return (String) value("protocol");
        }
        public static final class Builder {
            private final Map<String, Object> values = new LinkedHashMap<>();
            /**
             * Sets the requestId field.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder requestId(@Nullable String value) {
                if (value == null) values.remove("requestId");
                else values.put("requestId", jsonValue(value));
                return this;
            }
            /**
             * Sets the readerName field.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder readerName(@Nullable String value) {
                if (value == null) values.remove("readerName");
                else values.put("readerName", jsonValue(value));
                return this;
            }
            /**
             * Sets the state field.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder state(@Nullable String value) {
                if (value == null) values.remove("state");
                else values.put("state", jsonValue(value));
                return this;
            }
            /**
             * Sets the atr field.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder atr(@Nullable String value) {
                if (value == null) values.remove("atr");
                else values.put("atr", jsonValue(value));
                return this;
            }
            /**
             * Sets the protocol field.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder protocol(@Nullable String value) {
                if (value == null) values.remove("protocol");
                else values.put("protocol", jsonValue(value));
                return this;
            }
            public ReportStatusResultParams build() {
                if (!values.containsKey("requestId")) throw new IllegalStateException("Missing required CDP field: requestId");
                if (!values.containsKey("readerName")) throw new IllegalStateException("Missing required CDP field: readerName");
                if (!values.containsKey("state")) throw new IllegalStateException("Missing required CDP field: state");
                if (!values.containsKey("atr")) throw new IllegalStateException("Missing required CDP field: atr");
                return new ReportStatusResultParams(values);
            }
        }
    }
    /**
     * Reports the successful result of a |SCardStatus| call.
     * <p>This maps to: PC/SC Lite: https://pcsclite.apdu.fr/api/group__API.html#gae49c3c894ad7ac12a5b896bde70d0382 Microsoft: https://learn.microsoft.com/en-us/windows/win32/api/winscard/nf-winscard-scardstatusa
     */
    public static final class ReportStatusResultResult extends CdpObject {
        private ReportStatusResultResult(Map<String, Object> values) { super(values); }
        @Nullable public static ReportStatusResultResult fromMap(@Nullable Map<String, Object> values) {
            return values == null ? null : new ReportStatusResultResult(values);
        }
        public static Builder builder() { return new Builder(); }
        public static final class Builder {
            private final Map<String, Object> values = new LinkedHashMap<>();
            public ReportStatusResultResult build() {
                return new ReportStatusResultResult(values);
            }
        }
    }
    /**
     * Reports an error result for the given request.
     */
    public static final class ReportErrorParams extends CdpObject {
        private ReportErrorParams(Map<String, Object> values) { super(values); }
        @Nullable public static ReportErrorParams fromMap(@Nullable Map<String, Object> values) {
            return values == null ? null : new ReportErrorParams(values);
        }
        public static Builder builder() { return new Builder(); }
        /**
         * Returns the requestId field.
         * @return the protocol field value
         */
        @Nullable public String requestId() {
            return (String) value("requestId");
        }
        /**
         * Returns the resultCode field.
         * @return the protocol field value
         */
        @Nullable public String resultCode() {
            return (String) value("resultCode");
        }
        public static final class Builder {
            private final Map<String, Object> values = new LinkedHashMap<>();
            /**
             * Sets the requestId field.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder requestId(@Nullable String value) {
                if (value == null) values.remove("requestId");
                else values.put("requestId", jsonValue(value));
                return this;
            }
            /**
             * Sets the resultCode field.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder resultCode(@Nullable String value) {
                if (value == null) values.remove("resultCode");
                else values.put("resultCode", jsonValue(value));
                return this;
            }
            public ReportErrorParams build() {
                if (!values.containsKey("requestId")) throw new IllegalStateException("Missing required CDP field: requestId");
                if (!values.containsKey("resultCode")) throw new IllegalStateException("Missing required CDP field: resultCode");
                return new ReportErrorParams(values);
            }
        }
    }
    /**
     * Reports an error result for the given request.
     */
    public static final class ReportErrorResult extends CdpObject {
        private ReportErrorResult(Map<String, Object> values) { super(values); }
        @Nullable public static ReportErrorResult fromMap(@Nullable Map<String, Object> values) {
            return values == null ? null : new ReportErrorResult(values);
        }
        public static Builder builder() { return new Builder(); }
        public static final class Builder {
            private final Map<String, Object> values = new LinkedHashMap<>();
            public ReportErrorResult build() {
                return new ReportErrorResult(values);
            }
        }
    }
    /**
     * Fired when |SCardEstablishContext| is called.
     * <p>This maps to: PC/SC Lite: https://pcsclite.apdu.fr/api/group__API.html#gaa1b8970169fd4883a6dc4a8f43f19b67 Microsoft: https://learn.microsoft.com/en-us/windows/win32/api/winscard/nf-winscard-scardestablishcontext
     */
    public static final class EstablishContextRequestedEvent extends CdpObject {
        private EstablishContextRequestedEvent(Map<String, Object> values) { super(values); }
        @Nullable public static EstablishContextRequestedEvent fromMap(@Nullable Map<String, Object> values) {
            return values == null ? null : new EstablishContextRequestedEvent(values);
        }
        public static Builder builder() { return new Builder(); }
        /**
         * Returns the requestId field.
         * @return the protocol field value
         */
        @Nullable public String requestId() {
            return (String) value("requestId");
        }
        public static final class Builder {
            private final Map<String, Object> values = new LinkedHashMap<>();
            /**
             * Sets the requestId field.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder requestId(@Nullable String value) {
                if (value == null) values.remove("requestId");
                else values.put("requestId", jsonValue(value));
                return this;
            }
            public EstablishContextRequestedEvent build() {
                if (!values.containsKey("requestId")) throw new IllegalStateException("Missing required CDP field: requestId");
                return new EstablishContextRequestedEvent(values);
            }
        }
    }
    /**
     * Fired when |SCardReleaseContext| is called.
     * <p>This maps to: PC/SC Lite: https://pcsclite.apdu.fr/api/group__API.html#ga6aabcba7744c5c9419fdd6404f73a934 Microsoft: https://learn.microsoft.com/en-us/windows/win32/api/winscard/nf-winscard-scardreleasecontext
     */
    public static final class ReleaseContextRequestedEvent extends CdpObject {
        private ReleaseContextRequestedEvent(Map<String, Object> values) { super(values); }
        @Nullable public static ReleaseContextRequestedEvent fromMap(@Nullable Map<String, Object> values) {
            return values == null ? null : new ReleaseContextRequestedEvent(values);
        }
        public static Builder builder() { return new Builder(); }
        /**
         * Returns the requestId field.
         * @return the protocol field value
         */
        @Nullable public String requestId() {
            return (String) value("requestId");
        }
        /**
         * Returns the contextId field.
         * @return the protocol field value
         */
        @Nullable public Long contextId() {
            return numberAsLong(value("contextId"));
        }
        public static final class Builder {
            private final Map<String, Object> values = new LinkedHashMap<>();
            /**
             * Sets the requestId field.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder requestId(@Nullable String value) {
                if (value == null) values.remove("requestId");
                else values.put("requestId", jsonValue(value));
                return this;
            }
            /**
             * Sets the contextId field.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder contextId(@Nullable Long value) {
                if (value == null) values.remove("contextId");
                else values.put("contextId", jsonValue(value));
                return this;
            }
            public ReleaseContextRequestedEvent build() {
                if (!values.containsKey("requestId")) throw new IllegalStateException("Missing required CDP field: requestId");
                if (!values.containsKey("contextId")) throw new IllegalStateException("Missing required CDP field: contextId");
                return new ReleaseContextRequestedEvent(values);
            }
        }
    }
    /**
     * Fired when |SCardListReaders| is called.
     * <p>This maps to: PC/SC Lite: https://pcsclite.apdu.fr/api/group__API.html#ga93b07815789b3cf2629d439ecf20f0d9 Microsoft: https://learn.microsoft.com/en-us/windows/win32/api/winscard/nf-winscard-scardlistreadersa
     */
    public static final class ListReadersRequestedEvent extends CdpObject {
        private ListReadersRequestedEvent(Map<String, Object> values) { super(values); }
        @Nullable public static ListReadersRequestedEvent fromMap(@Nullable Map<String, Object> values) {
            return values == null ? null : new ListReadersRequestedEvent(values);
        }
        public static Builder builder() { return new Builder(); }
        /**
         * Returns the requestId field.
         * @return the protocol field value
         */
        @Nullable public String requestId() {
            return (String) value("requestId");
        }
        /**
         * Returns the contextId field.
         * @return the protocol field value
         */
        @Nullable public Long contextId() {
            return numberAsLong(value("contextId"));
        }
        public static final class Builder {
            private final Map<String, Object> values = new LinkedHashMap<>();
            /**
             * Sets the requestId field.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder requestId(@Nullable String value) {
                if (value == null) values.remove("requestId");
                else values.put("requestId", jsonValue(value));
                return this;
            }
            /**
             * Sets the contextId field.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder contextId(@Nullable Long value) {
                if (value == null) values.remove("contextId");
                else values.put("contextId", jsonValue(value));
                return this;
            }
            public ListReadersRequestedEvent build() {
                if (!values.containsKey("requestId")) throw new IllegalStateException("Missing required CDP field: requestId");
                if (!values.containsKey("contextId")) throw new IllegalStateException("Missing required CDP field: contextId");
                return new ListReadersRequestedEvent(values);
            }
        }
    }
    /**
     * Fired when |SCardGetStatusChange| is called. Timeout is specified in milliseconds.
     * <p>This maps to: PC/SC Lite: https://pcsclite.apdu.fr/api/group__API.html#ga33247d5d1257d59e55647c3bb717db24 Microsoft: https://learn.microsoft.com/en-us/windows/win32/api/winscard/nf-winscard-scardgetstatuschangea
     */
    public static final class GetStatusChangeRequestedEvent extends CdpObject {
        private GetStatusChangeRequestedEvent(Map<String, Object> values) { super(values); }
        @Nullable public static GetStatusChangeRequestedEvent fromMap(@Nullable Map<String, Object> values) {
            return values == null ? null : new GetStatusChangeRequestedEvent(values);
        }
        public static Builder builder() { return new Builder(); }
        /**
         * Returns the requestId field.
         * @return the protocol field value
         */
        @Nullable public String requestId() {
            return (String) value("requestId");
        }
        /**
         * Returns the contextId field.
         * @return the protocol field value
         */
        @Nullable public Long contextId() {
            return numberAsLong(value("contextId"));
        }
        /**
         * Returns the readerStates field.
         * @return the protocol field value
         */
        @Nullable public java.util.List<SmartCardEmulation.ReaderStateIn> readerStates() {
            return list(value("readerStates"), element0 -> SmartCardEmulation.ReaderStateIn.fromMap(objectMap(element0)));
        }
        /**
         * in milliseconds, if absent, it means &quot;infinite&quot;
         * @return the protocol field value
         */
        @Nullable public Long timeout() {
            return numberAsLong(value("timeout"));
        }
        public static final class Builder {
            private final Map<String, Object> values = new LinkedHashMap<>();
            /**
             * Sets the requestId field.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder requestId(@Nullable String value) {
                if (value == null) values.remove("requestId");
                else values.put("requestId", jsonValue(value));
                return this;
            }
            /**
             * Sets the contextId field.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder contextId(@Nullable Long value) {
                if (value == null) values.remove("contextId");
                else values.put("contextId", jsonValue(value));
                return this;
            }
            /**
             * Sets the readerStates field.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder readerStates(@Nullable java.util.List<SmartCardEmulation.ReaderStateIn> value) {
                if (value == null) values.remove("readerStates");
                else values.put("readerStates", jsonValue(value));
                return this;
            }
            /**
             * in milliseconds, if absent, it means &quot;infinite&quot;
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder timeout(@Nullable Long value) {
                if (value == null) values.remove("timeout");
                else values.put("timeout", jsonValue(value));
                return this;
            }
            public GetStatusChangeRequestedEvent build() {
                if (!values.containsKey("requestId")) throw new IllegalStateException("Missing required CDP field: requestId");
                if (!values.containsKey("contextId")) throw new IllegalStateException("Missing required CDP field: contextId");
                if (!values.containsKey("readerStates")) throw new IllegalStateException("Missing required CDP field: readerStates");
                return new GetStatusChangeRequestedEvent(values);
            }
        }
    }
    /**
     * Fired when |SCardCancel| is called.
     * <p>This maps to: PC/SC Lite: https://pcsclite.apdu.fr/api/group__API.html#gaacbbc0c6d6c0cbbeb4f4debf6fbeeee6 Microsoft: https://learn.microsoft.com/en-us/windows/win32/api/winscard/nf-winscard-scardcancel
     */
    public static final class CancelRequestedEvent extends CdpObject {
        private CancelRequestedEvent(Map<String, Object> values) { super(values); }
        @Nullable public static CancelRequestedEvent fromMap(@Nullable Map<String, Object> values) {
            return values == null ? null : new CancelRequestedEvent(values);
        }
        public static Builder builder() { return new Builder(); }
        /**
         * Returns the requestId field.
         * @return the protocol field value
         */
        @Nullable public String requestId() {
            return (String) value("requestId");
        }
        /**
         * Returns the contextId field.
         * @return the protocol field value
         */
        @Nullable public Long contextId() {
            return numberAsLong(value("contextId"));
        }
        public static final class Builder {
            private final Map<String, Object> values = new LinkedHashMap<>();
            /**
             * Sets the requestId field.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder requestId(@Nullable String value) {
                if (value == null) values.remove("requestId");
                else values.put("requestId", jsonValue(value));
                return this;
            }
            /**
             * Sets the contextId field.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder contextId(@Nullable Long value) {
                if (value == null) values.remove("contextId");
                else values.put("contextId", jsonValue(value));
                return this;
            }
            public CancelRequestedEvent build() {
                if (!values.containsKey("requestId")) throw new IllegalStateException("Missing required CDP field: requestId");
                if (!values.containsKey("contextId")) throw new IllegalStateException("Missing required CDP field: contextId");
                return new CancelRequestedEvent(values);
            }
        }
    }
    /**
     * Fired when |SCardConnect| is called.
     * <p>This maps to: PC/SC Lite: https://pcsclite.apdu.fr/api/group__API.html#ga4e515829752e0a8dbc4d630696a8d6a5 Microsoft: https://learn.microsoft.com/en-us/windows/win32/api/winscard/nf-winscard-scardconnecta
     */
    public static final class ConnectRequestedEvent extends CdpObject {
        private ConnectRequestedEvent(Map<String, Object> values) { super(values); }
        @Nullable public static ConnectRequestedEvent fromMap(@Nullable Map<String, Object> values) {
            return values == null ? null : new ConnectRequestedEvent(values);
        }
        public static Builder builder() { return new Builder(); }
        /**
         * Returns the requestId field.
         * @return the protocol field value
         */
        @Nullable public String requestId() {
            return (String) value("requestId");
        }
        /**
         * Returns the contextId field.
         * @return the protocol field value
         */
        @Nullable public Long contextId() {
            return numberAsLong(value("contextId"));
        }
        /**
         * Returns the reader field.
         * @return the protocol field value
         */
        @Nullable public String reader() {
            return (String) value("reader");
        }
        /**
         * Returns the shareMode field.
         * @return the protocol field value
         */
        @Nullable public String shareMode() {
            return (String) value("shareMode");
        }
        /**
         * Returns the preferredProtocols field.
         * @return the protocol field value
         */
        @Nullable public SmartCardEmulation.ProtocolSet preferredProtocols() {
            return SmartCardEmulation.ProtocolSet.fromMap(objectMap(value("preferredProtocols")));
        }
        public static final class Builder {
            private final Map<String, Object> values = new LinkedHashMap<>();
            /**
             * Sets the requestId field.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder requestId(@Nullable String value) {
                if (value == null) values.remove("requestId");
                else values.put("requestId", jsonValue(value));
                return this;
            }
            /**
             * Sets the contextId field.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder contextId(@Nullable Long value) {
                if (value == null) values.remove("contextId");
                else values.put("contextId", jsonValue(value));
                return this;
            }
            /**
             * Sets the reader field.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder reader(@Nullable String value) {
                if (value == null) values.remove("reader");
                else values.put("reader", jsonValue(value));
                return this;
            }
            /**
             * Sets the shareMode field.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder shareMode(@Nullable String value) {
                if (value == null) values.remove("shareMode");
                else values.put("shareMode", jsonValue(value));
                return this;
            }
            /**
             * Sets the preferredProtocols field.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder preferredProtocols(@Nullable SmartCardEmulation.ProtocolSet value) {
                if (value == null) values.remove("preferredProtocols");
                else values.put("preferredProtocols", jsonValue(value));
                return this;
            }
            public ConnectRequestedEvent build() {
                if (!values.containsKey("requestId")) throw new IllegalStateException("Missing required CDP field: requestId");
                if (!values.containsKey("contextId")) throw new IllegalStateException("Missing required CDP field: contextId");
                if (!values.containsKey("reader")) throw new IllegalStateException("Missing required CDP field: reader");
                if (!values.containsKey("shareMode")) throw new IllegalStateException("Missing required CDP field: shareMode");
                if (!values.containsKey("preferredProtocols")) throw new IllegalStateException("Missing required CDP field: preferredProtocols");
                return new ConnectRequestedEvent(values);
            }
        }
    }
    /**
     * Fired when |SCardDisconnect| is called.
     * <p>This maps to: PC/SC Lite: https://pcsclite.apdu.fr/api/group__API.html#ga4be198045c73ec0deb79e66c0ca1738a Microsoft: https://learn.microsoft.com/en-us/windows/win32/api/winscard/nf-winscard-scarddisconnect
     */
    public static final class DisconnectRequestedEvent extends CdpObject {
        private DisconnectRequestedEvent(Map<String, Object> values) { super(values); }
        @Nullable public static DisconnectRequestedEvent fromMap(@Nullable Map<String, Object> values) {
            return values == null ? null : new DisconnectRequestedEvent(values);
        }
        public static Builder builder() { return new Builder(); }
        /**
         * Returns the requestId field.
         * @return the protocol field value
         */
        @Nullable public String requestId() {
            return (String) value("requestId");
        }
        /**
         * Returns the handle field.
         * @return the protocol field value
         */
        @Nullable public Long handle() {
            return numberAsLong(value("handle"));
        }
        /**
         * Returns the disposition field.
         * @return the protocol field value
         */
        @Nullable public String disposition() {
            return (String) value("disposition");
        }
        public static final class Builder {
            private final Map<String, Object> values = new LinkedHashMap<>();
            /**
             * Sets the requestId field.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder requestId(@Nullable String value) {
                if (value == null) values.remove("requestId");
                else values.put("requestId", jsonValue(value));
                return this;
            }
            /**
             * Sets the handle field.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder handle(@Nullable Long value) {
                if (value == null) values.remove("handle");
                else values.put("handle", jsonValue(value));
                return this;
            }
            /**
             * Sets the disposition field.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder disposition(@Nullable String value) {
                if (value == null) values.remove("disposition");
                else values.put("disposition", jsonValue(value));
                return this;
            }
            public DisconnectRequestedEvent build() {
                if (!values.containsKey("requestId")) throw new IllegalStateException("Missing required CDP field: requestId");
                if (!values.containsKey("handle")) throw new IllegalStateException("Missing required CDP field: handle");
                if (!values.containsKey("disposition")) throw new IllegalStateException("Missing required CDP field: disposition");
                return new DisconnectRequestedEvent(values);
            }
        }
    }
    /**
     * Fired when |SCardTransmit| is called.
     * <p>This maps to: PC/SC Lite: https://pcsclite.apdu.fr/api/group__API.html#ga9a2d77242a271310269065e64633ab99 Microsoft: https://learn.microsoft.com/en-us/windows/win32/api/winscard/nf-winscard-scardtransmit
     */
    public static final class TransmitRequestedEvent extends CdpObject {
        private TransmitRequestedEvent(Map<String, Object> values) { super(values); }
        @Nullable public static TransmitRequestedEvent fromMap(@Nullable Map<String, Object> values) {
            return values == null ? null : new TransmitRequestedEvent(values);
        }
        public static Builder builder() { return new Builder(); }
        /**
         * Returns the requestId field.
         * @return the protocol field value
         */
        @Nullable public String requestId() {
            return (String) value("requestId");
        }
        /**
         * Returns the handle field.
         * @return the protocol field value
         */
        @Nullable public Long handle() {
            return numberAsLong(value("handle"));
        }
        /**
         * Returns the data field.
         * @return the protocol field value
         */
        @Nullable public String data() {
            return (String) value("data");
        }
        /**
         * Returns the protocol field.
         * @return the protocol field value
         */
        @Nullable public String protocol() {
            return (String) value("protocol");
        }
        public static final class Builder {
            private final Map<String, Object> values = new LinkedHashMap<>();
            /**
             * Sets the requestId field.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder requestId(@Nullable String value) {
                if (value == null) values.remove("requestId");
                else values.put("requestId", jsonValue(value));
                return this;
            }
            /**
             * Sets the handle field.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder handle(@Nullable Long value) {
                if (value == null) values.remove("handle");
                else values.put("handle", jsonValue(value));
                return this;
            }
            /**
             * Sets the data field.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder data(@Nullable String value) {
                if (value == null) values.remove("data");
                else values.put("data", jsonValue(value));
                return this;
            }
            /**
             * Sets the protocol field.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder protocol(@Nullable String value) {
                if (value == null) values.remove("protocol");
                else values.put("protocol", jsonValue(value));
                return this;
            }
            public TransmitRequestedEvent build() {
                if (!values.containsKey("requestId")) throw new IllegalStateException("Missing required CDP field: requestId");
                if (!values.containsKey("handle")) throw new IllegalStateException("Missing required CDP field: handle");
                if (!values.containsKey("data")) throw new IllegalStateException("Missing required CDP field: data");
                return new TransmitRequestedEvent(values);
            }
        }
    }
    /**
     * Fired when |SCardControl| is called.
     * <p>This maps to: PC/SC Lite: https://pcsclite.apdu.fr/api/group__API.html#gac3454d4657110fd7f753b2d3d8f4e32f Microsoft: https://learn.microsoft.com/en-us/windows/win32/api/winscard/nf-winscard-scardcontrol
     */
    public static final class ControlRequestedEvent extends CdpObject {
        private ControlRequestedEvent(Map<String, Object> values) { super(values); }
        @Nullable public static ControlRequestedEvent fromMap(@Nullable Map<String, Object> values) {
            return values == null ? null : new ControlRequestedEvent(values);
        }
        public static Builder builder() { return new Builder(); }
        /**
         * Returns the requestId field.
         * @return the protocol field value
         */
        @Nullable public String requestId() {
            return (String) value("requestId");
        }
        /**
         * Returns the handle field.
         * @return the protocol field value
         */
        @Nullable public Long handle() {
            return numberAsLong(value("handle"));
        }
        /**
         * Returns the controlCode field.
         * @return the protocol field value
         */
        @Nullable public Long controlCode() {
            return numberAsLong(value("controlCode"));
        }
        /**
         * Returns the data field.
         * @return the protocol field value
         */
        @Nullable public String data() {
            return (String) value("data");
        }
        public static final class Builder {
            private final Map<String, Object> values = new LinkedHashMap<>();
            /**
             * Sets the requestId field.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder requestId(@Nullable String value) {
                if (value == null) values.remove("requestId");
                else values.put("requestId", jsonValue(value));
                return this;
            }
            /**
             * Sets the handle field.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder handle(@Nullable Long value) {
                if (value == null) values.remove("handle");
                else values.put("handle", jsonValue(value));
                return this;
            }
            /**
             * Sets the controlCode field.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder controlCode(@Nullable Long value) {
                if (value == null) values.remove("controlCode");
                else values.put("controlCode", jsonValue(value));
                return this;
            }
            /**
             * Sets the data field.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder data(@Nullable String value) {
                if (value == null) values.remove("data");
                else values.put("data", jsonValue(value));
                return this;
            }
            public ControlRequestedEvent build() {
                if (!values.containsKey("requestId")) throw new IllegalStateException("Missing required CDP field: requestId");
                if (!values.containsKey("handle")) throw new IllegalStateException("Missing required CDP field: handle");
                if (!values.containsKey("controlCode")) throw new IllegalStateException("Missing required CDP field: controlCode");
                if (!values.containsKey("data")) throw new IllegalStateException("Missing required CDP field: data");
                return new ControlRequestedEvent(values);
            }
        }
    }
    /**
     * Fired when |SCardGetAttrib| is called.
     * <p>This maps to: PC/SC Lite: https://pcsclite.apdu.fr/api/group__API.html#gaacfec51917255b7a25b94c5104961602 Microsoft: https://learn.microsoft.com/en-us/windows/win32/api/winscard/nf-winscard-scardgetattrib
     */
    public static final class GetAttribRequestedEvent extends CdpObject {
        private GetAttribRequestedEvent(Map<String, Object> values) { super(values); }
        @Nullable public static GetAttribRequestedEvent fromMap(@Nullable Map<String, Object> values) {
            return values == null ? null : new GetAttribRequestedEvent(values);
        }
        public static Builder builder() { return new Builder(); }
        /**
         * Returns the requestId field.
         * @return the protocol field value
         */
        @Nullable public String requestId() {
            return (String) value("requestId");
        }
        /**
         * Returns the handle field.
         * @return the protocol field value
         */
        @Nullable public Long handle() {
            return numberAsLong(value("handle"));
        }
        /**
         * Returns the attribId field.
         * @return the protocol field value
         */
        @Nullable public Long attribId() {
            return numberAsLong(value("attribId"));
        }
        public static final class Builder {
            private final Map<String, Object> values = new LinkedHashMap<>();
            /**
             * Sets the requestId field.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder requestId(@Nullable String value) {
                if (value == null) values.remove("requestId");
                else values.put("requestId", jsonValue(value));
                return this;
            }
            /**
             * Sets the handle field.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder handle(@Nullable Long value) {
                if (value == null) values.remove("handle");
                else values.put("handle", jsonValue(value));
                return this;
            }
            /**
             * Sets the attribId field.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder attribId(@Nullable Long value) {
                if (value == null) values.remove("attribId");
                else values.put("attribId", jsonValue(value));
                return this;
            }
            public GetAttribRequestedEvent build() {
                if (!values.containsKey("requestId")) throw new IllegalStateException("Missing required CDP field: requestId");
                if (!values.containsKey("handle")) throw new IllegalStateException("Missing required CDP field: handle");
                if (!values.containsKey("attribId")) throw new IllegalStateException("Missing required CDP field: attribId");
                return new GetAttribRequestedEvent(values);
            }
        }
    }
    /**
     * Fired when |SCardSetAttrib| is called.
     * <p>This maps to: PC/SC Lite: https://pcsclite.apdu.fr/api/group__API.html#ga060f0038a4ddfd5dd2b8fadf3c3a2e4f Microsoft: https://learn.microsoft.com/en-us/windows/win32/api/winscard/nf-winscard-scardsetattrib
     */
    public static final class SetAttribRequestedEvent extends CdpObject {
        private SetAttribRequestedEvent(Map<String, Object> values) { super(values); }
        @Nullable public static SetAttribRequestedEvent fromMap(@Nullable Map<String, Object> values) {
            return values == null ? null : new SetAttribRequestedEvent(values);
        }
        public static Builder builder() { return new Builder(); }
        /**
         * Returns the requestId field.
         * @return the protocol field value
         */
        @Nullable public String requestId() {
            return (String) value("requestId");
        }
        /**
         * Returns the handle field.
         * @return the protocol field value
         */
        @Nullable public Long handle() {
            return numberAsLong(value("handle"));
        }
        /**
         * Returns the attribId field.
         * @return the protocol field value
         */
        @Nullable public Long attribId() {
            return numberAsLong(value("attribId"));
        }
        /**
         * Returns the data field.
         * @return the protocol field value
         */
        @Nullable public String data() {
            return (String) value("data");
        }
        public static final class Builder {
            private final Map<String, Object> values = new LinkedHashMap<>();
            /**
             * Sets the requestId field.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder requestId(@Nullable String value) {
                if (value == null) values.remove("requestId");
                else values.put("requestId", jsonValue(value));
                return this;
            }
            /**
             * Sets the handle field.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder handle(@Nullable Long value) {
                if (value == null) values.remove("handle");
                else values.put("handle", jsonValue(value));
                return this;
            }
            /**
             * Sets the attribId field.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder attribId(@Nullable Long value) {
                if (value == null) values.remove("attribId");
                else values.put("attribId", jsonValue(value));
                return this;
            }
            /**
             * Sets the data field.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder data(@Nullable String value) {
                if (value == null) values.remove("data");
                else values.put("data", jsonValue(value));
                return this;
            }
            public SetAttribRequestedEvent build() {
                if (!values.containsKey("requestId")) throw new IllegalStateException("Missing required CDP field: requestId");
                if (!values.containsKey("handle")) throw new IllegalStateException("Missing required CDP field: handle");
                if (!values.containsKey("attribId")) throw new IllegalStateException("Missing required CDP field: attribId");
                if (!values.containsKey("data")) throw new IllegalStateException("Missing required CDP field: data");
                return new SetAttribRequestedEvent(values);
            }
        }
    }
    /**
     * Fired when |SCardStatus| is called.
     * <p>This maps to: PC/SC Lite: https://pcsclite.apdu.fr/api/group__API.html#gae49c3c894ad7ac12a5b896bde70d0382 Microsoft: https://learn.microsoft.com/en-us/windows/win32/api/winscard/nf-winscard-scardstatusa
     */
    public static final class StatusRequestedEvent extends CdpObject {
        private StatusRequestedEvent(Map<String, Object> values) { super(values); }
        @Nullable public static StatusRequestedEvent fromMap(@Nullable Map<String, Object> values) {
            return values == null ? null : new StatusRequestedEvent(values);
        }
        public static Builder builder() { return new Builder(); }
        /**
         * Returns the requestId field.
         * @return the protocol field value
         */
        @Nullable public String requestId() {
            return (String) value("requestId");
        }
        /**
         * Returns the handle field.
         * @return the protocol field value
         */
        @Nullable public Long handle() {
            return numberAsLong(value("handle"));
        }
        public static final class Builder {
            private final Map<String, Object> values = new LinkedHashMap<>();
            /**
             * Sets the requestId field.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder requestId(@Nullable String value) {
                if (value == null) values.remove("requestId");
                else values.put("requestId", jsonValue(value));
                return this;
            }
            /**
             * Sets the handle field.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder handle(@Nullable Long value) {
                if (value == null) values.remove("handle");
                else values.put("handle", jsonValue(value));
                return this;
            }
            public StatusRequestedEvent build() {
                if (!values.containsKey("requestId")) throw new IllegalStateException("Missing required CDP field: requestId");
                if (!values.containsKey("handle")) throw new IllegalStateException("Missing required CDP field: handle");
                return new StatusRequestedEvent(values);
            }
        }
    }
    /**
     * Fired when |SCardBeginTransaction| is called.
     * <p>This maps to: PC/SC Lite: https://pcsclite.apdu.fr/api/group__API.html#gaddb835dce01a0da1d6ca02d33ee7d861 Microsoft: https://learn.microsoft.com/en-us/windows/win32/api/winscard/nf-winscard-scardbegintransaction
     */
    public static final class BeginTransactionRequestedEvent extends CdpObject {
        private BeginTransactionRequestedEvent(Map<String, Object> values) { super(values); }
        @Nullable public static BeginTransactionRequestedEvent fromMap(@Nullable Map<String, Object> values) {
            return values == null ? null : new BeginTransactionRequestedEvent(values);
        }
        public static Builder builder() { return new Builder(); }
        /**
         * Returns the requestId field.
         * @return the protocol field value
         */
        @Nullable public String requestId() {
            return (String) value("requestId");
        }
        /**
         * Returns the handle field.
         * @return the protocol field value
         */
        @Nullable public Long handle() {
            return numberAsLong(value("handle"));
        }
        public static final class Builder {
            private final Map<String, Object> values = new LinkedHashMap<>();
            /**
             * Sets the requestId field.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder requestId(@Nullable String value) {
                if (value == null) values.remove("requestId");
                else values.put("requestId", jsonValue(value));
                return this;
            }
            /**
             * Sets the handle field.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder handle(@Nullable Long value) {
                if (value == null) values.remove("handle");
                else values.put("handle", jsonValue(value));
                return this;
            }
            public BeginTransactionRequestedEvent build() {
                if (!values.containsKey("requestId")) throw new IllegalStateException("Missing required CDP field: requestId");
                if (!values.containsKey("handle")) throw new IllegalStateException("Missing required CDP field: handle");
                return new BeginTransactionRequestedEvent(values);
            }
        }
    }
    /**
     * Fired when |SCardEndTransaction| is called.
     * <p>This maps to: PC/SC Lite: https://pcsclite.apdu.fr/api/group__API.html#gae8742473b404363e5c587f570d7e2f3b Microsoft: https://learn.microsoft.com/en-us/windows/win32/api/winscard/nf-winscard-scardendtransaction
     */
    public static final class EndTransactionRequestedEvent extends CdpObject {
        private EndTransactionRequestedEvent(Map<String, Object> values) { super(values); }
        @Nullable public static EndTransactionRequestedEvent fromMap(@Nullable Map<String, Object> values) {
            return values == null ? null : new EndTransactionRequestedEvent(values);
        }
        public static Builder builder() { return new Builder(); }
        /**
         * Returns the requestId field.
         * @return the protocol field value
         */
        @Nullable public String requestId() {
            return (String) value("requestId");
        }
        /**
         * Returns the handle field.
         * @return the protocol field value
         */
        @Nullable public Long handle() {
            return numberAsLong(value("handle"));
        }
        /**
         * Returns the disposition field.
         * @return the protocol field value
         */
        @Nullable public String disposition() {
            return (String) value("disposition");
        }
        public static final class Builder {
            private final Map<String, Object> values = new LinkedHashMap<>();
            /**
             * Sets the requestId field.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder requestId(@Nullable String value) {
                if (value == null) values.remove("requestId");
                else values.put("requestId", jsonValue(value));
                return this;
            }
            /**
             * Sets the handle field.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder handle(@Nullable Long value) {
                if (value == null) values.remove("handle");
                else values.put("handle", jsonValue(value));
                return this;
            }
            /**
             * Sets the disposition field.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder disposition(@Nullable String value) {
                if (value == null) values.remove("disposition");
                else values.put("disposition", jsonValue(value));
                return this;
            }
            public EndTransactionRequestedEvent build() {
                if (!values.containsKey("requestId")) throw new IllegalStateException("Missing required CDP field: requestId");
                if (!values.containsKey("handle")) throw new IllegalStateException("Missing required CDP field: handle");
                if (!values.containsKey("disposition")) throw new IllegalStateException("Missing required CDP field: disposition");
                return new EndTransactionRequestedEvent(values);
            }
        }
    }
    public static final class Client {
        private final CdpClient client;
        public Client(CdpClient client) { this.client = client; }
        /**
         * Enables the |SmartCardEmulation| domain.
         * @return a stage completing with the command result
         */
        public CompletionStage<EnableResult> enable() {
            return client.call("SmartCardEmulation.enable", null, EnableResult::fromMap);
        }
        /**
         * Disables the |SmartCardEmulation| domain.
         * @return a stage completing with the command result
         */
        public CompletionStage<DisableResult> disable() {
            return client.call("SmartCardEmulation.disable", null, DisableResult::fromMap);
        }
        /**
         * Reports the successful result of a |SCardEstablishContext| call.
         * <p>This maps to: PC/SC Lite: https://pcsclite.apdu.fr/api/group__API.html#gaa1b8970169fd4883a6dc4a8f43f19b67 Microsoft: https://learn.microsoft.com/en-us/windows/win32/api/winscard/nf-winscard-scardestablishcontext
         * @param params command parameters
         * @return a stage completing with the command result
         */
        public CompletionStage<ReportEstablishContextResultResult> reportEstablishContextResult(ReportEstablishContextResultParams params) {
            return client.call("SmartCardEmulation.reportEstablishContextResult", params, ReportEstablishContextResultResult::fromMap);
        }
        /**
         * Reports the successful result of a |SCardReleaseContext| call.
         * <p>This maps to: PC/SC Lite: https://pcsclite.apdu.fr/api/group__API.html#ga6aabcba7744c5c9419fdd6404f73a934 Microsoft: https://learn.microsoft.com/en-us/windows/win32/api/winscard/nf-winscard-scardreleasecontext
         * @param params command parameters
         * @return a stage completing with the command result
         */
        public CompletionStage<ReportReleaseContextResultResult> reportReleaseContextResult(ReportReleaseContextResultParams params) {
            return client.call("SmartCardEmulation.reportReleaseContextResult", params, ReportReleaseContextResultResult::fromMap);
        }
        /**
         * Reports the successful result of a |SCardListReaders| call.
         * <p>This maps to: PC/SC Lite: https://pcsclite.apdu.fr/api/group__API.html#ga93b07815789b3cf2629d439ecf20f0d9 Microsoft: https://learn.microsoft.com/en-us/windows/win32/api/winscard/nf-winscard-scardlistreadersa
         * @param params command parameters
         * @return a stage completing with the command result
         */
        public CompletionStage<ReportListReadersResultResult> reportListReadersResult(ReportListReadersResultParams params) {
            return client.call("SmartCardEmulation.reportListReadersResult", params, ReportListReadersResultResult::fromMap);
        }
        /**
         * Reports the successful result of a |SCardGetStatusChange| call.
         * <p>This maps to: PC/SC Lite: https://pcsclite.apdu.fr/api/group__API.html#ga33247d5d1257d59e55647c3bb717db24 Microsoft: https://learn.microsoft.com/en-us/windows/win32/api/winscard/nf-winscard-scardgetstatuschangea
         * @param params command parameters
         * @return a stage completing with the command result
         */
        public CompletionStage<ReportGetStatusChangeResultResult> reportGetStatusChangeResult(ReportGetStatusChangeResultParams params) {
            return client.call("SmartCardEmulation.reportGetStatusChangeResult", params, ReportGetStatusChangeResultResult::fromMap);
        }
        /**
         * Reports the result of a |SCardBeginTransaction| call. On success, this creates a new transaction object.
         * <p>This maps to: PC/SC Lite: https://pcsclite.apdu.fr/api/group__API.html#gaddb835dce01a0da1d6ca02d33ee7d861 Microsoft: https://learn.microsoft.com/en-us/windows/win32/api/winscard/nf-winscard-scardbegintransaction
         * @param params command parameters
         * @return a stage completing with the command result
         */
        public CompletionStage<ReportBeginTransactionResultResult> reportBeginTransactionResult(ReportBeginTransactionResultParams params) {
            return client.call("SmartCardEmulation.reportBeginTransactionResult", params, ReportBeginTransactionResultResult::fromMap);
        }
        /**
         * Reports the successful result of a call that returns only a result code. Used for: |SCardCancel|, |SCardDisconnect|, |SCardSetAttrib|, |SCardEndTransaction|.
         * <p>This maps to: 1. SCardCancel PC/SC Lite: https://pcsclite.apdu.fr/api/group__API.html#gaacbbc0c6d6c0cbbeb4f4debf6fbeeee6 Microsoft: https://learn.microsoft.com/en-us/windows/win32/api/winscard/nf-winscard-scardcancel
         * <p>2. SCardDisconnect PC/SC Lite: https://pcsclite.apdu.fr/api/group__API.html#ga4be198045c73ec0deb79e66c0ca1738a Microsoft: https://learn.microsoft.com/en-us/windows/win32/api/winscard/nf-winscard-scarddisconnect
         * <p>3. SCardSetAttrib PC/SC Lite: https://pcsclite.apdu.fr/api/group__API.html#ga060f0038a4ddfd5dd2b8fadf3c3a2e4f Microsoft: https://learn.microsoft.com/en-us/windows/win32/api/winscard/nf-winscard-scardsetattrib
         * <p>4. SCardEndTransaction PC/SC Lite: https://pcsclite.apdu.fr/api/group__API.html#gae8742473b404363e5c587f570d7e2f3b Microsoft: https://learn.microsoft.com/en-us/windows/win32/api/winscard/nf-winscard-scardendtransaction
         * @param params command parameters
         * @return a stage completing with the command result
         */
        public CompletionStage<ReportPlainResultResult> reportPlainResult(ReportPlainResultParams params) {
            return client.call("SmartCardEmulation.reportPlainResult", params, ReportPlainResultResult::fromMap);
        }
        /**
         * Reports the successful result of a |SCardConnect| call.
         * <p>This maps to: PC/SC Lite: https://pcsclite.apdu.fr/api/group__API.html#ga4e515829752e0a8dbc4d630696a8d6a5 Microsoft: https://learn.microsoft.com/en-us/windows/win32/api/winscard/nf-winscard-scardconnecta
         * @param params command parameters
         * @return a stage completing with the command result
         */
        public CompletionStage<ReportConnectResultResult> reportConnectResult(ReportConnectResultParams params) {
            return client.call("SmartCardEmulation.reportConnectResult", params, ReportConnectResultResult::fromMap);
        }
        /**
         * Reports the successful result of a call that sends back data on success. Used for |SCardTransmit|, |SCardControl|, and |SCardGetAttrib|.
         * <p>This maps to: 1. SCardTransmit PC/SC Lite: https://pcsclite.apdu.fr/api/group__API.html#ga9a2d77242a271310269065e64633ab99 Microsoft: https://learn.microsoft.com/en-us/windows/win32/api/winscard/nf-winscard-scardtransmit
         * <p>2. SCardControl PC/SC Lite: https://pcsclite.apdu.fr/api/group__API.html#gac3454d4657110fd7f753b2d3d8f4e32f Microsoft: https://learn.microsoft.com/en-us/windows/win32/api/winscard/nf-winscard-scardcontrol
         * <p>3. SCardGetAttrib PC/SC Lite: https://pcsclite.apdu.fr/api/group__API.html#gaacfec51917255b7a25b94c5104961602 Microsoft: https://learn.microsoft.com/en-us/windows/win32/api/winscard/nf-winscard-scardgetattrib
         * @param params command parameters
         * @return a stage completing with the command result
         */
        public CompletionStage<ReportDataResultResult> reportDataResult(ReportDataResultParams params) {
            return client.call("SmartCardEmulation.reportDataResult", params, ReportDataResultResult::fromMap);
        }
        /**
         * Reports the successful result of a |SCardStatus| call.
         * <p>This maps to: PC/SC Lite: https://pcsclite.apdu.fr/api/group__API.html#gae49c3c894ad7ac12a5b896bde70d0382 Microsoft: https://learn.microsoft.com/en-us/windows/win32/api/winscard/nf-winscard-scardstatusa
         * @param params command parameters
         * @return a stage completing with the command result
         */
        public CompletionStage<ReportStatusResultResult> reportStatusResult(ReportStatusResultParams params) {
            return client.call("SmartCardEmulation.reportStatusResult", params, ReportStatusResultResult::fromMap);
        }
        /**
         * Reports an error result for the given request.
         * @param params command parameters
         * @return a stage completing with the command result
         */
        public CompletionStage<ReportErrorResult> reportError(ReportErrorParams params) {
            return client.call("SmartCardEmulation.reportError", params, ReportErrorResult::fromMap);
        }
        /**
         * Fired when |SCardEstablishContext| is called.
         * <p>This maps to: PC/SC Lite: https://pcsclite.apdu.fr/api/group__API.html#gaa1b8970169fd4883a6dc4a8f43f19b67 Microsoft: https://learn.microsoft.com/en-us/windows/win32/api/winscard/nf-winscard-scardestablishcontext
         * @param handler event callback
         * @return a removable subscription
         */
        public CdpSubscription onEstablishContextRequested(Consumer<EstablishContextRequestedEvent> handler) {
            return client.on("SmartCardEmulation.establishContextRequested", EstablishContextRequestedEvent::fromMap, handler);
        }
        /**
         * Fired when |SCardReleaseContext| is called.
         * <p>This maps to: PC/SC Lite: https://pcsclite.apdu.fr/api/group__API.html#ga6aabcba7744c5c9419fdd6404f73a934 Microsoft: https://learn.microsoft.com/en-us/windows/win32/api/winscard/nf-winscard-scardreleasecontext
         * @param handler event callback
         * @return a removable subscription
         */
        public CdpSubscription onReleaseContextRequested(Consumer<ReleaseContextRequestedEvent> handler) {
            return client.on("SmartCardEmulation.releaseContextRequested", ReleaseContextRequestedEvent::fromMap, handler);
        }
        /**
         * Fired when |SCardListReaders| is called.
         * <p>This maps to: PC/SC Lite: https://pcsclite.apdu.fr/api/group__API.html#ga93b07815789b3cf2629d439ecf20f0d9 Microsoft: https://learn.microsoft.com/en-us/windows/win32/api/winscard/nf-winscard-scardlistreadersa
         * @param handler event callback
         * @return a removable subscription
         */
        public CdpSubscription onListReadersRequested(Consumer<ListReadersRequestedEvent> handler) {
            return client.on("SmartCardEmulation.listReadersRequested", ListReadersRequestedEvent::fromMap, handler);
        }
        /**
         * Fired when |SCardGetStatusChange| is called. Timeout is specified in milliseconds.
         * <p>This maps to: PC/SC Lite: https://pcsclite.apdu.fr/api/group__API.html#ga33247d5d1257d59e55647c3bb717db24 Microsoft: https://learn.microsoft.com/en-us/windows/win32/api/winscard/nf-winscard-scardgetstatuschangea
         * @param handler event callback
         * @return a removable subscription
         */
        public CdpSubscription onGetStatusChangeRequested(Consumer<GetStatusChangeRequestedEvent> handler) {
            return client.on("SmartCardEmulation.getStatusChangeRequested", GetStatusChangeRequestedEvent::fromMap, handler);
        }
        /**
         * Fired when |SCardCancel| is called.
         * <p>This maps to: PC/SC Lite: https://pcsclite.apdu.fr/api/group__API.html#gaacbbc0c6d6c0cbbeb4f4debf6fbeeee6 Microsoft: https://learn.microsoft.com/en-us/windows/win32/api/winscard/nf-winscard-scardcancel
         * @param handler event callback
         * @return a removable subscription
         */
        public CdpSubscription onCancelRequested(Consumer<CancelRequestedEvent> handler) {
            return client.on("SmartCardEmulation.cancelRequested", CancelRequestedEvent::fromMap, handler);
        }
        /**
         * Fired when |SCardConnect| is called.
         * <p>This maps to: PC/SC Lite: https://pcsclite.apdu.fr/api/group__API.html#ga4e515829752e0a8dbc4d630696a8d6a5 Microsoft: https://learn.microsoft.com/en-us/windows/win32/api/winscard/nf-winscard-scardconnecta
         * @param handler event callback
         * @return a removable subscription
         */
        public CdpSubscription onConnectRequested(Consumer<ConnectRequestedEvent> handler) {
            return client.on("SmartCardEmulation.connectRequested", ConnectRequestedEvent::fromMap, handler);
        }
        /**
         * Fired when |SCardDisconnect| is called.
         * <p>This maps to: PC/SC Lite: https://pcsclite.apdu.fr/api/group__API.html#ga4be198045c73ec0deb79e66c0ca1738a Microsoft: https://learn.microsoft.com/en-us/windows/win32/api/winscard/nf-winscard-scarddisconnect
         * @param handler event callback
         * @return a removable subscription
         */
        public CdpSubscription onDisconnectRequested(Consumer<DisconnectRequestedEvent> handler) {
            return client.on("SmartCardEmulation.disconnectRequested", DisconnectRequestedEvent::fromMap, handler);
        }
        /**
         * Fired when |SCardTransmit| is called.
         * <p>This maps to: PC/SC Lite: https://pcsclite.apdu.fr/api/group__API.html#ga9a2d77242a271310269065e64633ab99 Microsoft: https://learn.microsoft.com/en-us/windows/win32/api/winscard/nf-winscard-scardtransmit
         * @param handler event callback
         * @return a removable subscription
         */
        public CdpSubscription onTransmitRequested(Consumer<TransmitRequestedEvent> handler) {
            return client.on("SmartCardEmulation.transmitRequested", TransmitRequestedEvent::fromMap, handler);
        }
        /**
         * Fired when |SCardControl| is called.
         * <p>This maps to: PC/SC Lite: https://pcsclite.apdu.fr/api/group__API.html#gac3454d4657110fd7f753b2d3d8f4e32f Microsoft: https://learn.microsoft.com/en-us/windows/win32/api/winscard/nf-winscard-scardcontrol
         * @param handler event callback
         * @return a removable subscription
         */
        public CdpSubscription onControlRequested(Consumer<ControlRequestedEvent> handler) {
            return client.on("SmartCardEmulation.controlRequested", ControlRequestedEvent::fromMap, handler);
        }
        /**
         * Fired when |SCardGetAttrib| is called.
         * <p>This maps to: PC/SC Lite: https://pcsclite.apdu.fr/api/group__API.html#gaacfec51917255b7a25b94c5104961602 Microsoft: https://learn.microsoft.com/en-us/windows/win32/api/winscard/nf-winscard-scardgetattrib
         * @param handler event callback
         * @return a removable subscription
         */
        public CdpSubscription onGetAttribRequested(Consumer<GetAttribRequestedEvent> handler) {
            return client.on("SmartCardEmulation.getAttribRequested", GetAttribRequestedEvent::fromMap, handler);
        }
        /**
         * Fired when |SCardSetAttrib| is called.
         * <p>This maps to: PC/SC Lite: https://pcsclite.apdu.fr/api/group__API.html#ga060f0038a4ddfd5dd2b8fadf3c3a2e4f Microsoft: https://learn.microsoft.com/en-us/windows/win32/api/winscard/nf-winscard-scardsetattrib
         * @param handler event callback
         * @return a removable subscription
         */
        public CdpSubscription onSetAttribRequested(Consumer<SetAttribRequestedEvent> handler) {
            return client.on("SmartCardEmulation.setAttribRequested", SetAttribRequestedEvent::fromMap, handler);
        }
        /**
         * Fired when |SCardStatus| is called.
         * <p>This maps to: PC/SC Lite: https://pcsclite.apdu.fr/api/group__API.html#gae49c3c894ad7ac12a5b896bde70d0382 Microsoft: https://learn.microsoft.com/en-us/windows/win32/api/winscard/nf-winscard-scardstatusa
         * @param handler event callback
         * @return a removable subscription
         */
        public CdpSubscription onStatusRequested(Consumer<StatusRequestedEvent> handler) {
            return client.on("SmartCardEmulation.statusRequested", StatusRequestedEvent::fromMap, handler);
        }
        /**
         * Fired when |SCardBeginTransaction| is called.
         * <p>This maps to: PC/SC Lite: https://pcsclite.apdu.fr/api/group__API.html#gaddb835dce01a0da1d6ca02d33ee7d861 Microsoft: https://learn.microsoft.com/en-us/windows/win32/api/winscard/nf-winscard-scardbegintransaction
         * @param handler event callback
         * @return a removable subscription
         */
        public CdpSubscription onBeginTransactionRequested(Consumer<BeginTransactionRequestedEvent> handler) {
            return client.on("SmartCardEmulation.beginTransactionRequested", BeginTransactionRequestedEvent::fromMap, handler);
        }
        /**
         * Fired when |SCardEndTransaction| is called.
         * <p>This maps to: PC/SC Lite: https://pcsclite.apdu.fr/api/group__API.html#gae8742473b404363e5c587f570d7e2f3b Microsoft: https://learn.microsoft.com/en-us/windows/win32/api/winscard/nf-winscard-scardendtransaction
         * @param handler event callback
         * @return a removable subscription
         */
        public CdpSubscription onEndTransactionRequested(Consumer<EndTransactionRequestedEvent> handler) {
            return client.on("SmartCardEmulation.endTransactionRequested", EndTransactionRequestedEvent::fromMap, handler);
        }
    }
}
