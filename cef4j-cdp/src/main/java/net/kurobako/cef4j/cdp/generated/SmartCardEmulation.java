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
 * Chrome DevTools Protocol SmartCardEmulation domain.
 * <p><b>Experimental:</b> this part of CDP may change without notice.
 * @see <a href="https://chromium.googlesource.com/chromium/src/+/refs/tags/150.0.7871.213/third_party/blink/public/devtools_protocol/domains/SmartCardEmulation.pdl">Pinned protocol source</a>
 */
@Generated("./mvnw generate-sources -pl cef4j-cdp -Dcef.version=150.0.18+gdb11278+chromium-150.0.7871.213")
@SuppressWarnings({"EscapedEntity", "InvalidParam", "JavaLangClash", "MissingSummary", "Unchecked", "UnusedMethod"})
@NullableBoundary("CDP wire objects use null for absent optional fields")
public final class SmartCardEmulation {
    private SmartCardEmulation() {}
    private static final CdpField<SmartCardEmulation.Protocol> ACTIVE_PROTOCOL = CdpField.of("activeProtocol", value -> SmartCardEmulation.Protocol.of((String) value));
    private static final CdpField<SmartCardEmulation.ReaderStateFlags> CURRENT_STATE = CdpField.of("currentState", value -> java.util.Objects.requireNonNull(SmartCardEmulation.ReaderStateFlags.fromMap(CdpObject.requireObjectMap(value))));
    private static final CdpField<SmartCardEmulation.Disposition> DISPOSITION = CdpField.of("disposition", value -> SmartCardEmulation.Disposition.of((String) value));
    private static final CdpField<SmartCardEmulation.ReaderStateFlags> EVENT_STATE = CdpField.of("eventState", value -> java.util.Objects.requireNonNull(SmartCardEmulation.ReaderStateFlags.fromMap(CdpObject.requireObjectMap(value))));
    private static final CdpField<SmartCardEmulation.ProtocolSet> PREFERRED_PROTOCOLS = CdpField.of("preferredProtocols", value -> java.util.Objects.requireNonNull(SmartCardEmulation.ProtocolSet.fromMap(CdpObject.requireObjectMap(value))));
    private static final CdpField<SmartCardEmulation.Protocol> PROTOCOL = CdpField.of("protocol", value -> SmartCardEmulation.Protocol.of((String) value));
    private static final CdpField<java.util.List<String>> READERS = CdpField.of("readers", value -> CdpObject.requireList(value, element0 -> (String) element0));
    private static final CdpField<java.util.List<SmartCardEmulation.ReaderStateOut>> READER_STATES = CdpField.of("readerStates", value -> CdpObject.requireList(value, element0 -> java.util.Objects.requireNonNull(SmartCardEmulation.ReaderStateOut.fromMap(CdpObject.requireObjectMap(element0)))));
    private static final CdpField<java.util.List<SmartCardEmulation.ReaderStateIn>> READER_STATES_2 = CdpField.of("readerStates", value -> CdpObject.requireList(value, element0 -> java.util.Objects.requireNonNull(SmartCardEmulation.ReaderStateIn.fromMap(CdpObject.requireObjectMap(element0)))));
    private static final CdpField<SmartCardEmulation.ResultCode> RESULT_CODE = CdpField.of("resultCode", value -> SmartCardEmulation.ResultCode.of((String) value));
    private static final CdpField<SmartCardEmulation.ShareMode> SHARE_MODE = CdpField.of("shareMode", value -> SmartCardEmulation.ShareMode.of((String) value));
    private static final CdpField<SmartCardEmulation.ConnectionState> STATE = CdpField.of("state", value -> SmartCardEmulation.ConnectionState.of((String) value));
    /**
     * Indicates the PC/SC error code.
     * <p>This maps to: PC/SC Lite: https://pcsclite.apdu.fr/api/group__ErrorCodes.html Microsoft: https://learn.microsoft.com/en-us/windows/win32/secauthn/authentication-return-values
     */
    public enum ResultCode implements CdpValue<String> {
        SUCCESS("success"),
        REMOVED_CARD("removed-card"),
        RESET_CARD("reset-card"),
        UNPOWERED_CARD("unpowered-card"),
        UNRESPONSIVE_CARD("unresponsive-card"),
        UNSUPPORTED_CARD("unsupported-card"),
        READER_UNAVAILABLE("reader-unavailable"),
        SHARING_VIOLATION("sharing-violation"),
        NOT_TRANSACTED("not-transacted"),
        NO_SMARTCARD("no-smartcard"),
        PROTO_MISMATCH("proto-mismatch"),
        SYSTEM_CANCELLED("system-cancelled"),
        NOT_READY("not-ready"),
        CANCELLED("cancelled"),
        INSUFFICIENT_BUFFER("insufficient-buffer"),
        INVALID_HANDLE("invalid-handle"),
        INVALID_PARAMETER("invalid-parameter"),
        INVALID_VALUE("invalid-value"),
        NO_MEMORY("no-memory"),
        TIMEOUT("timeout"),
        UNKNOWN_READER("unknown-reader"),
        UNSUPPORTED_FEATURE("unsupported-feature"),
        NO_READERS_AVAILABLE("no-readers-available"),
        SERVICE_STOPPED("service-stopped"),
        NO_SERVICE("no-service"),
        COMM_ERROR("comm-error"),
        INTERNAL_ERROR("internal-error"),
        SERVER_TOO_BUSY("server-too-busy"),
        UNEXPECTED("unexpected"),
        SHUTDOWN("shutdown"),
        UNKNOWN_CARD("unknown-card"),
        UNKNOWN("unknown");
        public final String value;
        ResultCode(String value) { this.value = value; }
        @Override public String value() { return value; }
        public static ResultCode of(String value) {
            for (ResultCode constant : values()) {
                if (constant.value.equals(value)) return constant;
            }
            throw new IllegalArgumentException("Unknown ResultCode value: " + value);
        }
    }
    /**
     * Maps to the |SCARD_SHARE_*| values.
     */
    public enum ShareMode implements CdpValue<String> {
        SHARED("shared"),
        EXCLUSIVE("exclusive"),
        DIRECT("direct");
        public final String value;
        ShareMode(String value) { this.value = value; }
        @Override public String value() { return value; }
        public static ShareMode of(String value) {
            for (ShareMode constant : values()) {
                if (constant.value.equals(value)) return constant;
            }
            throw new IllegalArgumentException("Unknown ShareMode value: " + value);
        }
    }
    /**
     * Indicates what the reader should do with the card.
     */
    public enum Disposition implements CdpValue<String> {
        LEAVE_CARD("leave-card"),
        RESET_CARD("reset-card"),
        UNPOWER_CARD("unpower-card"),
        EJECT_CARD("eject-card");
        public final String value;
        Disposition(String value) { this.value = value; }
        @Override public String value() { return value; }
        public static Disposition of(String value) {
            for (Disposition constant : values()) {
                if (constant.value.equals(value)) return constant;
            }
            throw new IllegalArgumentException("Unknown Disposition value: " + value);
        }
    }
    /**
     * Maps to |SCARD_*| connection state values.
     */
    public enum ConnectionState implements CdpValue<String> {
        ABSENT("absent"),
        PRESENT("present"),
        SWALLOWED("swallowed"),
        POWERED("powered"),
        NEGOTIABLE("negotiable"),
        SPECIFIC("specific");
        public final String value;
        ConnectionState(String value) { this.value = value; }
        @Override public String value() { return value; }
        public static ConnectionState of(String value) {
            for (ConnectionState constant : values()) {
                if (constant.value.equals(value)) return constant;
            }
            throw new IllegalArgumentException("Unknown ConnectionState value: " + value);
        }
    }
    /**
     * Maps to the |SCARD_STATE_*| flags.
     */
    public static final class ReaderStateFlags extends CdpObject {
        public ReaderStateFlags() {}
        private ReaderStateFlags(Map<String, Object> values) { super(values); }
        public static ReaderStateFlags fromMap(Map<String, Object> values) {
            return new ReaderStateFlags(values);
        }
        /**
         * Returns the unaware field.
         * @return the protocol field value, empty when absent
         */
        public Optional<Boolean> unaware() {
            return optionalBoolean("unaware");
        }
        /**
         * Returns the ignore field.
         * @return the protocol field value, empty when absent
         */
        public Optional<Boolean> ignore() {
            return optionalBoolean("ignore");
        }
        /**
         * Returns the changed field.
         * @return the protocol field value, empty when absent
         */
        public Optional<Boolean> changed() {
            return optionalBoolean("changed");
        }
        /**
         * Returns the unknown field.
         * @return the protocol field value, empty when absent
         */
        public Optional<Boolean> unknown() {
            return optionalBoolean("unknown");
        }
        /**
         * Returns the unavailable field.
         * @return the protocol field value, empty when absent
         */
        public Optional<Boolean> unavailable() {
            return optionalBoolean("unavailable");
        }
        /**
         * Returns the empty field.
         * @return the protocol field value, empty when absent
         */
        public Optional<Boolean> empty() {
            return optionalBoolean("empty");
        }
        /**
         * Returns the present field.
         * @return the protocol field value, empty when absent
         */
        public Optional<Boolean> present() {
            return optionalBoolean("present");
        }
        /**
         * Returns the exclusive field.
         * @return the protocol field value, empty when absent
         */
        public Optional<Boolean> exclusive() {
            return optionalBoolean("exclusive");
        }
        /**
         * Returns the inuse field.
         * @return the protocol field value, empty when absent
         */
        public Optional<Boolean> inuse() {
            return optionalBoolean("inuse");
        }
        /**
         * Returns the mute field.
         * @return the protocol field value, empty when absent
         */
        public Optional<Boolean> mute() {
            return optionalBoolean("mute");
        }
        /**
         * Returns the unpowered field.
         * @return the protocol field value, empty when absent
         */
        public Optional<Boolean> unpowered() {
            return optionalBoolean("unpowered");
        }
        /**
         * Sets the unaware field.
         * @param unaware field value
         * @return this model
         */
        public ReaderStateFlags unaware(boolean unaware) {
            set("unaware", unaware);
            return this;
        }
        /**
         * Sets the ignore field.
         * @param ignore field value
         * @return this model
         */
        public ReaderStateFlags ignore(boolean ignore) {
            set("ignore", ignore);
            return this;
        }
        /**
         * Sets the changed field.
         * @param changed field value
         * @return this model
         */
        public ReaderStateFlags changed(boolean changed) {
            set("changed", changed);
            return this;
        }
        /**
         * Sets the unknown field.
         * @param unknown field value
         * @return this model
         */
        public ReaderStateFlags unknown(boolean unknown) {
            set("unknown", unknown);
            return this;
        }
        /**
         * Sets the unavailable field.
         * @param unavailable field value
         * @return this model
         */
        public ReaderStateFlags unavailable(boolean unavailable) {
            set("unavailable", unavailable);
            return this;
        }
        /**
         * Sets the empty field.
         * @param empty field value
         * @return this model
         */
        public ReaderStateFlags empty(boolean empty) {
            set("empty", empty);
            return this;
        }
        /**
         * Sets the present field.
         * @param present field value
         * @return this model
         */
        public ReaderStateFlags present(boolean present) {
            set("present", present);
            return this;
        }
        /**
         * Sets the exclusive field.
         * @param exclusive field value
         * @return this model
         */
        public ReaderStateFlags exclusive(boolean exclusive) {
            set("exclusive", exclusive);
            return this;
        }
        /**
         * Sets the inuse field.
         * @param inuse field value
         * @return this model
         */
        public ReaderStateFlags inuse(boolean inuse) {
            set("inuse", inuse);
            return this;
        }
        /**
         * Sets the mute field.
         * @param mute field value
         * @return this model
         */
        public ReaderStateFlags mute(boolean mute) {
            set("mute", mute);
            return this;
        }
        /**
         * Sets the unpowered field.
         * @param unpowered field value
         * @return this model
         */
        public ReaderStateFlags unpowered(boolean unpowered) {
            set("unpowered", unpowered);
            return this;
        }
    }
    /**
     * Maps to the |SCARD_PROTOCOL_*| flags.
     */
    public static final class ProtocolSet extends CdpObject {
        public ProtocolSet() {}
        private ProtocolSet(Map<String, Object> values) { super(values); }
        public static ProtocolSet fromMap(Map<String, Object> values) {
            return new ProtocolSet(values);
        }
        /**
         * Returns the t0 field.
         * @return the protocol field value, empty when absent
         */
        public Optional<Boolean> t0() {
            return optionalBoolean("t0");
        }
        /**
         * Returns the t1 field.
         * @return the protocol field value, empty when absent
         */
        public Optional<Boolean> t1() {
            return optionalBoolean("t1");
        }
        /**
         * Returns the raw field.
         * @return the protocol field value, empty when absent
         */
        public Optional<Boolean> raw() {
            return optionalBoolean("raw");
        }
        /**
         * Sets the t0 field.
         * @param t0 field value
         * @return this model
         */
        public ProtocolSet t0(boolean t0) {
            set("t0", t0);
            return this;
        }
        /**
         * Sets the t1 field.
         * @param t1 field value
         * @return this model
         */
        public ProtocolSet t1(boolean t1) {
            set("t1", t1);
            return this;
        }
        /**
         * Sets the raw field.
         * @param raw field value
         * @return this model
         */
        public ProtocolSet raw(boolean raw) {
            set("raw", raw);
            return this;
        }
    }
    /**
     * Maps to the |SCARD_PROTOCOL_*| values.
     */
    public enum Protocol implements CdpValue<String> {
        T0("t0"),
        T1("t1"),
        RAW("raw");
        public final String value;
        Protocol(String value) { this.value = value; }
        @Override public String value() { return value; }
        public static Protocol of(String value) {
            for (Protocol constant : values()) {
                if (constant.value.equals(value)) return constant;
            }
            throw new IllegalArgumentException("Unknown Protocol value: " + value);
        }
    }
    /**
     */
    public static final class ReaderStateIn extends CdpObject {
        public ReaderStateIn() {}
        private ReaderStateIn(Map<String, Object> values) { super(values); }
        public static ReaderStateIn fromMap(Map<String, Object> values) {
            return new ReaderStateIn(values);
        }
        /**
         * Returns the reader field.
         * @return the protocol field value
         */
        public String reader() {
            return requireString("reader");
        }
        /**
         * Returns the currentState field.
         * @return the protocol field value
         */
        public SmartCardEmulation.ReaderStateFlags currentState() {
            return require(CURRENT_STATE);
        }
        /**
         * Returns the currentInsertionCount field.
         * @return the protocol field value
         */
        public long currentInsertionCount() {
            return requireLong("currentInsertionCount");
        }
        /**
         * Sets the reader field.
         * @param reader field value
         * @return this model
         */
        public ReaderStateIn reader(String reader) {
            set("reader", reader);
            return this;
        }
        /**
         * Sets the currentState field.
         * @param currentState field value
         * @return this model
         */
        public ReaderStateIn currentState(SmartCardEmulation.ReaderStateFlags currentState) {
            set("currentState", currentState);
            return this;
        }
        /**
         * Sets the currentInsertionCount field.
         * @param currentInsertionCount field value
         * @return this model
         */
        public ReaderStateIn currentInsertionCount(long currentInsertionCount) {
            set("currentInsertionCount", currentInsertionCount);
            return this;
        }
    }
    /**
     */
    public static final class ReaderStateOut extends CdpObject {
        public ReaderStateOut() {}
        private ReaderStateOut(Map<String, Object> values) { super(values); }
        public static ReaderStateOut fromMap(Map<String, Object> values) {
            return new ReaderStateOut(values);
        }
        /**
         * Returns the reader field.
         * @return the protocol field value
         */
        public String reader() {
            return requireString("reader");
        }
        /**
         * Returns the eventState field.
         * @return the protocol field value
         */
        public SmartCardEmulation.ReaderStateFlags eventState() {
            return require(EVENT_STATE);
        }
        /**
         * Returns the eventCount field.
         * @return the protocol field value
         */
        public long eventCount() {
            return requireLong("eventCount");
        }
        /**
         * Returns the atr field.
         * @return the protocol field value
         */
        public String atr() {
            return requireString("atr");
        }
        /**
         * Sets the reader field.
         * @param reader field value
         * @return this model
         */
        public ReaderStateOut reader(String reader) {
            set("reader", reader);
            return this;
        }
        /**
         * Sets the eventState field.
         * @param eventState field value
         * @return this model
         */
        public ReaderStateOut eventState(SmartCardEmulation.ReaderStateFlags eventState) {
            set("eventState", eventState);
            return this;
        }
        /**
         * Sets the eventCount field.
         * @param eventCount field value
         * @return this model
         */
        public ReaderStateOut eventCount(long eventCount) {
            set("eventCount", eventCount);
            return this;
        }
        /**
         * Sets the atr field.
         * @param atr field value
         * @return this model
         */
        public ReaderStateOut atr(String atr) {
            set("atr", atr);
            return this;
        }
    }
    /**
     * Reports the successful result of a |SCardEstablishContext| call.
     * <p>This maps to: PC/SC Lite: https://pcsclite.apdu.fr/api/group__API.html#gaa1b8970169fd4883a6dc4a8f43f19b67 Microsoft: https://learn.microsoft.com/en-us/windows/win32/api/winscard/nf-winscard-scardestablishcontext
     */
    public static final class ReportEstablishContextResultRequest extends CdpObject {
        public ReportEstablishContextResultRequest() {}
        public static ReportEstablishContextResultRequest fromMap(Map<String, Object> values) {
            final ReportEstablishContextResultRequest instance_ = new ReportEstablishContextResultRequest();
            if (values != null) instance_.values.putAll(values);
            return instance_;
        }
        /**
         * Returns the requestId field.
         * @return the protocol field value
         */
        public String requestId() {
            return requireString("requestId");
        }
        /**
         * Returns the contextId field.
         * @return the protocol field value
         */
        public long contextId() {
            return requireLong("contextId");
        }
        /**
         * Sets the requestId field.
         * @param requestId field value
         * @return this model
         */
        public ReportEstablishContextResultRequest requestId(String requestId) {
            set("requestId", requestId);
            return this;
        }
        /**
         * Sets the contextId field.
         * @param contextId field value
         * @return this model
         */
        public ReportEstablishContextResultRequest contextId(long contextId) {
            set("contextId", contextId);
            return this;
        }
    }
    /**
     * Reports the successful result of a |SCardReleaseContext| call.
     * <p>This maps to: PC/SC Lite: https://pcsclite.apdu.fr/api/group__API.html#ga6aabcba7744c5c9419fdd6404f73a934 Microsoft: https://learn.microsoft.com/en-us/windows/win32/api/winscard/nf-winscard-scardreleasecontext
     */
    public static final class ReportReleaseContextResultRequest extends CdpObject {
        public ReportReleaseContextResultRequest() {}
        public static ReportReleaseContextResultRequest fromMap(Map<String, Object> values) {
            final ReportReleaseContextResultRequest instance_ = new ReportReleaseContextResultRequest();
            if (values != null) instance_.values.putAll(values);
            return instance_;
        }
        /**
         * Returns the requestId field.
         * @return the protocol field value
         */
        public String requestId() {
            return requireString("requestId");
        }
        /**
         * Sets the requestId field.
         * @param requestId field value
         * @return this model
         */
        public ReportReleaseContextResultRequest requestId(String requestId) {
            set("requestId", requestId);
            return this;
        }
    }
    /**
     * Reports the successful result of a |SCardListReaders| call.
     * <p>This maps to: PC/SC Lite: https://pcsclite.apdu.fr/api/group__API.html#ga93b07815789b3cf2629d439ecf20f0d9 Microsoft: https://learn.microsoft.com/en-us/windows/win32/api/winscard/nf-winscard-scardlistreadersa
     */
    public static final class ReportListReadersResultRequest extends CdpObject {
        public ReportListReadersResultRequest() {}
        public static ReportListReadersResultRequest fromMap(Map<String, Object> values) {
            final ReportListReadersResultRequest instance_ = new ReportListReadersResultRequest();
            if (values != null) instance_.values.putAll(values);
            return instance_;
        }
        /**
         * Returns the requestId field.
         * @return the protocol field value
         */
        public String requestId() {
            return requireString("requestId");
        }
        /**
         * Returns the readers field.
         * @return the protocol field value
         */
        public java.util.List<String> readers() {
            return require(READERS);
        }
        /**
         * Sets the requestId field.
         * @param requestId field value
         * @return this model
         */
        public ReportListReadersResultRequest requestId(String requestId) {
            set("requestId", requestId);
            return this;
        }
        /**
         * Sets the readers field.
         * @param readers field value
         * @return this model
         */
        public ReportListReadersResultRequest readers(java.util.List<String> readers) {
            set("readers", readers);
            return this;
        }
    }
    /**
     * Reports the successful result of a |SCardGetStatusChange| call.
     * <p>This maps to: PC/SC Lite: https://pcsclite.apdu.fr/api/group__API.html#ga33247d5d1257d59e55647c3bb717db24 Microsoft: https://learn.microsoft.com/en-us/windows/win32/api/winscard/nf-winscard-scardgetstatuschangea
     */
    public static final class ReportGetStatusChangeResultRequest extends CdpObject {
        public ReportGetStatusChangeResultRequest() {}
        public static ReportGetStatusChangeResultRequest fromMap(Map<String, Object> values) {
            final ReportGetStatusChangeResultRequest instance_ = new ReportGetStatusChangeResultRequest();
            if (values != null) instance_.values.putAll(values);
            return instance_;
        }
        /**
         * Returns the requestId field.
         * @return the protocol field value
         */
        public String requestId() {
            return requireString("requestId");
        }
        /**
         * Returns the readerStates field.
         * @return the protocol field value
         */
        public java.util.List<SmartCardEmulation.ReaderStateOut> readerStates() {
            return require(READER_STATES);
        }
        /**
         * Sets the requestId field.
         * @param requestId field value
         * @return this model
         */
        public ReportGetStatusChangeResultRequest requestId(String requestId) {
            set("requestId", requestId);
            return this;
        }
        /**
         * Sets the readerStates field.
         * @param readerStates field value
         * @return this model
         */
        public ReportGetStatusChangeResultRequest readerStates(java.util.List<SmartCardEmulation.ReaderStateOut> readerStates) {
            set("readerStates", readerStates);
            return this;
        }
    }
    /**
     * Reports the result of a |SCardBeginTransaction| call. On success, this creates a new transaction object.
     * <p>This maps to: PC/SC Lite: https://pcsclite.apdu.fr/api/group__API.html#gaddb835dce01a0da1d6ca02d33ee7d861 Microsoft: https://learn.microsoft.com/en-us/windows/win32/api/winscard/nf-winscard-scardbegintransaction
     */
    public static final class ReportBeginTransactionResultRequest extends CdpObject {
        public ReportBeginTransactionResultRequest() {}
        public static ReportBeginTransactionResultRequest fromMap(Map<String, Object> values) {
            final ReportBeginTransactionResultRequest instance_ = new ReportBeginTransactionResultRequest();
            if (values != null) instance_.values.putAll(values);
            return instance_;
        }
        /**
         * Returns the requestId field.
         * @return the protocol field value
         */
        public String requestId() {
            return requireString("requestId");
        }
        /**
         * Returns the handle field.
         * @return the protocol field value
         */
        public long handle() {
            return requireLong("handle");
        }
        /**
         * Sets the requestId field.
         * @param requestId field value
         * @return this model
         */
        public ReportBeginTransactionResultRequest requestId(String requestId) {
            set("requestId", requestId);
            return this;
        }
        /**
         * Sets the handle field.
         * @param handle field value
         * @return this model
         */
        public ReportBeginTransactionResultRequest handle(long handle) {
            set("handle", handle);
            return this;
        }
    }
    /**
     * Reports the successful result of a call that returns only a result code. Used for: |SCardCancel|, |SCardDisconnect|, |SCardSetAttrib|, |SCardEndTransaction|.
     * <p>This maps to: 1. SCardCancel PC/SC Lite: https://pcsclite.apdu.fr/api/group__API.html#gaacbbc0c6d6c0cbbeb4f4debf6fbeeee6 Microsoft: https://learn.microsoft.com/en-us/windows/win32/api/winscard/nf-winscard-scardcancel
     * <p>2. SCardDisconnect PC/SC Lite: https://pcsclite.apdu.fr/api/group__API.html#ga4be198045c73ec0deb79e66c0ca1738a Microsoft: https://learn.microsoft.com/en-us/windows/win32/api/winscard/nf-winscard-scarddisconnect
     * <p>3. SCardSetAttrib PC/SC Lite: https://pcsclite.apdu.fr/api/group__API.html#ga060f0038a4ddfd5dd2b8fadf3c3a2e4f Microsoft: https://learn.microsoft.com/en-us/windows/win32/api/winscard/nf-winscard-scardsetattrib
     * <p>4. SCardEndTransaction PC/SC Lite: https://pcsclite.apdu.fr/api/group__API.html#gae8742473b404363e5c587f570d7e2f3b Microsoft: https://learn.microsoft.com/en-us/windows/win32/api/winscard/nf-winscard-scardendtransaction
     */
    public static final class ReportPlainResultRequest extends CdpObject {
        public ReportPlainResultRequest() {}
        public static ReportPlainResultRequest fromMap(Map<String, Object> values) {
            final ReportPlainResultRequest instance_ = new ReportPlainResultRequest();
            if (values != null) instance_.values.putAll(values);
            return instance_;
        }
        /**
         * Returns the requestId field.
         * @return the protocol field value
         */
        public String requestId() {
            return requireString("requestId");
        }
        /**
         * Sets the requestId field.
         * @param requestId field value
         * @return this model
         */
        public ReportPlainResultRequest requestId(String requestId) {
            set("requestId", requestId);
            return this;
        }
    }
    /**
     * Reports the successful result of a |SCardConnect| call.
     * <p>This maps to: PC/SC Lite: https://pcsclite.apdu.fr/api/group__API.html#ga4e515829752e0a8dbc4d630696a8d6a5 Microsoft: https://learn.microsoft.com/en-us/windows/win32/api/winscard/nf-winscard-scardconnecta
     */
    public static final class ReportConnectResultRequest extends CdpObject {
        public ReportConnectResultRequest() {}
        public static ReportConnectResultRequest fromMap(Map<String, Object> values) {
            final ReportConnectResultRequest instance_ = new ReportConnectResultRequest();
            if (values != null) instance_.values.putAll(values);
            return instance_;
        }
        /**
         * Returns the requestId field.
         * @return the protocol field value
         */
        public String requestId() {
            return requireString("requestId");
        }
        /**
         * Returns the handle field.
         * @return the protocol field value
         */
        public long handle() {
            return requireLong("handle");
        }
        /**
         * Returns the activeProtocol field.
         * @return the protocol field value, empty when absent
         */
        public Optional<SmartCardEmulation.Protocol> activeProtocol() {
            return optional(ACTIVE_PROTOCOL);
        }
        /**
         * Sets the requestId field.
         * @param requestId field value
         * @return this model
         */
        public ReportConnectResultRequest requestId(String requestId) {
            set("requestId", requestId);
            return this;
        }
        /**
         * Sets the handle field.
         * @param handle field value
         * @return this model
         */
        public ReportConnectResultRequest handle(long handle) {
            set("handle", handle);
            return this;
        }
        /**
         * Sets the activeProtocol field.
         * @param activeProtocol field value
         * @return this model
         */
        public ReportConnectResultRequest activeProtocol(SmartCardEmulation.Protocol activeProtocol) {
            set("activeProtocol", activeProtocol);
            return this;
        }
    }
    /**
     * Reports the successful result of a call that sends back data on success. Used for |SCardTransmit|, |SCardControl|, and |SCardGetAttrib|.
     * <p>This maps to: 1. SCardTransmit PC/SC Lite: https://pcsclite.apdu.fr/api/group__API.html#ga9a2d77242a271310269065e64633ab99 Microsoft: https://learn.microsoft.com/en-us/windows/win32/api/winscard/nf-winscard-scardtransmit
     * <p>2. SCardControl PC/SC Lite: https://pcsclite.apdu.fr/api/group__API.html#gac3454d4657110fd7f753b2d3d8f4e32f Microsoft: https://learn.microsoft.com/en-us/windows/win32/api/winscard/nf-winscard-scardcontrol
     * <p>3. SCardGetAttrib PC/SC Lite: https://pcsclite.apdu.fr/api/group__API.html#gaacfec51917255b7a25b94c5104961602 Microsoft: https://learn.microsoft.com/en-us/windows/win32/api/winscard/nf-winscard-scardgetattrib
     */
    public static final class ReportDataResultRequest extends CdpObject {
        public ReportDataResultRequest() {}
        public static ReportDataResultRequest fromMap(Map<String, Object> values) {
            final ReportDataResultRequest instance_ = new ReportDataResultRequest();
            if (values != null) instance_.values.putAll(values);
            return instance_;
        }
        /**
         * Returns the requestId field.
         * @return the protocol field value
         */
        public String requestId() {
            return requireString("requestId");
        }
        /**
         * Returns the data field.
         * @return the protocol field value
         */
        public String data() {
            return requireString("data");
        }
        /**
         * Sets the requestId field.
         * @param requestId field value
         * @return this model
         */
        public ReportDataResultRequest requestId(String requestId) {
            set("requestId", requestId);
            return this;
        }
        /**
         * Sets the data field.
         * @param data field value
         * @return this model
         */
        public ReportDataResultRequest data(String data) {
            set("data", data);
            return this;
        }
    }
    /**
     * Reports the successful result of a |SCardStatus| call.
     * <p>This maps to: PC/SC Lite: https://pcsclite.apdu.fr/api/group__API.html#gae49c3c894ad7ac12a5b896bde70d0382 Microsoft: https://learn.microsoft.com/en-us/windows/win32/api/winscard/nf-winscard-scardstatusa
     */
    public static final class ReportStatusResultRequest extends CdpObject {
        public ReportStatusResultRequest() {}
        public static ReportStatusResultRequest fromMap(Map<String, Object> values) {
            final ReportStatusResultRequest instance_ = new ReportStatusResultRequest();
            if (values != null) instance_.values.putAll(values);
            return instance_;
        }
        /**
         * Returns the requestId field.
         * @return the protocol field value
         */
        public String requestId() {
            return requireString("requestId");
        }
        /**
         * Returns the readerName field.
         * @return the protocol field value
         */
        public String readerName() {
            return requireString("readerName");
        }
        /**
         * Returns the state field.
         * @return the protocol field value
         */
        public SmartCardEmulation.ConnectionState state() {
            return require(STATE);
        }
        /**
         * Returns the atr field.
         * @return the protocol field value
         */
        public String atr() {
            return requireString("atr");
        }
        /**
         * Returns the protocol field.
         * @return the protocol field value, empty when absent
         */
        public Optional<SmartCardEmulation.Protocol> protocol() {
            return optional(PROTOCOL);
        }
        /**
         * Sets the requestId field.
         * @param requestId field value
         * @return this model
         */
        public ReportStatusResultRequest requestId(String requestId) {
            set("requestId", requestId);
            return this;
        }
        /**
         * Sets the readerName field.
         * @param readerName field value
         * @return this model
         */
        public ReportStatusResultRequest readerName(String readerName) {
            set("readerName", readerName);
            return this;
        }
        /**
         * Sets the state field.
         * @param state field value
         * @return this model
         */
        public ReportStatusResultRequest state(SmartCardEmulation.ConnectionState state) {
            set("state", state);
            return this;
        }
        /**
         * Sets the atr field.
         * @param atr field value
         * @return this model
         */
        public ReportStatusResultRequest atr(String atr) {
            set("atr", atr);
            return this;
        }
        /**
         * Sets the protocol field.
         * @param protocol field value
         * @return this model
         */
        public ReportStatusResultRequest protocol(SmartCardEmulation.Protocol protocol) {
            set("protocol", protocol);
            return this;
        }
    }
    /**
     * Reports an error result for the given request.
     */
    public static final class ReportErrorRequest extends CdpObject {
        public ReportErrorRequest() {}
        public static ReportErrorRequest fromMap(Map<String, Object> values) {
            final ReportErrorRequest instance_ = new ReportErrorRequest();
            if (values != null) instance_.values.putAll(values);
            return instance_;
        }
        /**
         * Returns the requestId field.
         * @return the protocol field value
         */
        public String requestId() {
            return requireString("requestId");
        }
        /**
         * Returns the resultCode field.
         * @return the protocol field value
         */
        public SmartCardEmulation.ResultCode resultCode() {
            return require(RESULT_CODE);
        }
        /**
         * Sets the requestId field.
         * @param requestId field value
         * @return this model
         */
        public ReportErrorRequest requestId(String requestId) {
            set("requestId", requestId);
            return this;
        }
        /**
         * Sets the resultCode field.
         * @param resultCode field value
         * @return this model
         */
        public ReportErrorRequest resultCode(SmartCardEmulation.ResultCode resultCode) {
            set("resultCode", resultCode);
            return this;
        }
    }
    /**
     * Fired when |SCardEstablishContext| is called.
     * <p>This maps to: PC/SC Lite: https://pcsclite.apdu.fr/api/group__API.html#gaa1b8970169fd4883a6dc4a8f43f19b67 Microsoft: https://learn.microsoft.com/en-us/windows/win32/api/winscard/nf-winscard-scardestablishcontext
     */
    public static final class EstablishContextRequestedEvent extends CdpObject {
        public EstablishContextRequestedEvent() {}
        private EstablishContextRequestedEvent(Map<String, Object> values) { super(values); }
        public static EstablishContextRequestedEvent fromMap(Map<String, Object> values) {
            return new EstablishContextRequestedEvent(values);
        }
        /**
         * Returns the requestId field.
         * @return the protocol field value
         */
        public String requestId() {
            return requireString("requestId");
        }
        /**
         * Sets the requestId field.
         * @param requestId field value
         * @return this model
         */
        public EstablishContextRequestedEvent requestId(String requestId) {
            set("requestId", requestId);
            return this;
        }
    }
    /**
     * Fired when |SCardReleaseContext| is called.
     * <p>This maps to: PC/SC Lite: https://pcsclite.apdu.fr/api/group__API.html#ga6aabcba7744c5c9419fdd6404f73a934 Microsoft: https://learn.microsoft.com/en-us/windows/win32/api/winscard/nf-winscard-scardreleasecontext
     */
    public static final class ReleaseContextRequestedEvent extends CdpObject {
        public ReleaseContextRequestedEvent() {}
        private ReleaseContextRequestedEvent(Map<String, Object> values) { super(values); }
        public static ReleaseContextRequestedEvent fromMap(Map<String, Object> values) {
            return new ReleaseContextRequestedEvent(values);
        }
        /**
         * Returns the requestId field.
         * @return the protocol field value
         */
        public String requestId() {
            return requireString("requestId");
        }
        /**
         * Returns the contextId field.
         * @return the protocol field value
         */
        public long contextId() {
            return requireLong("contextId");
        }
        /**
         * Sets the requestId field.
         * @param requestId field value
         * @return this model
         */
        public ReleaseContextRequestedEvent requestId(String requestId) {
            set("requestId", requestId);
            return this;
        }
        /**
         * Sets the contextId field.
         * @param contextId field value
         * @return this model
         */
        public ReleaseContextRequestedEvent contextId(long contextId) {
            set("contextId", contextId);
            return this;
        }
    }
    /**
     * Fired when |SCardListReaders| is called.
     * <p>This maps to: PC/SC Lite: https://pcsclite.apdu.fr/api/group__API.html#ga93b07815789b3cf2629d439ecf20f0d9 Microsoft: https://learn.microsoft.com/en-us/windows/win32/api/winscard/nf-winscard-scardlistreadersa
     */
    public static final class ListReadersRequestedEvent extends CdpObject {
        public ListReadersRequestedEvent() {}
        private ListReadersRequestedEvent(Map<String, Object> values) { super(values); }
        public static ListReadersRequestedEvent fromMap(Map<String, Object> values) {
            return new ListReadersRequestedEvent(values);
        }
        /**
         * Returns the requestId field.
         * @return the protocol field value
         */
        public String requestId() {
            return requireString("requestId");
        }
        /**
         * Returns the contextId field.
         * @return the protocol field value
         */
        public long contextId() {
            return requireLong("contextId");
        }
        /**
         * Sets the requestId field.
         * @param requestId field value
         * @return this model
         */
        public ListReadersRequestedEvent requestId(String requestId) {
            set("requestId", requestId);
            return this;
        }
        /**
         * Sets the contextId field.
         * @param contextId field value
         * @return this model
         */
        public ListReadersRequestedEvent contextId(long contextId) {
            set("contextId", contextId);
            return this;
        }
    }
    /**
     * Fired when |SCardGetStatusChange| is called. Timeout is specified in milliseconds.
     * <p>This maps to: PC/SC Lite: https://pcsclite.apdu.fr/api/group__API.html#ga33247d5d1257d59e55647c3bb717db24 Microsoft: https://learn.microsoft.com/en-us/windows/win32/api/winscard/nf-winscard-scardgetstatuschangea
     */
    public static final class GetStatusChangeRequestedEvent extends CdpObject {
        public GetStatusChangeRequestedEvent() {}
        private GetStatusChangeRequestedEvent(Map<String, Object> values) { super(values); }
        public static GetStatusChangeRequestedEvent fromMap(Map<String, Object> values) {
            return new GetStatusChangeRequestedEvent(values);
        }
        /**
         * Returns the requestId field.
         * @return the protocol field value
         */
        public String requestId() {
            return requireString("requestId");
        }
        /**
         * Returns the contextId field.
         * @return the protocol field value
         */
        public long contextId() {
            return requireLong("contextId");
        }
        /**
         * Returns the readerStates field.
         * @return the protocol field value
         */
        public java.util.List<SmartCardEmulation.ReaderStateIn> readerStates() {
            return require(READER_STATES_2);
        }
        /**
         * in milliseconds, if absent, it means &quot;infinite&quot;
         * @return the protocol field value, empty when absent
         */
        public OptionalLong timeout() {
            return optionalLong("timeout");
        }
        /**
         * Sets the requestId field.
         * @param requestId field value
         * @return this model
         */
        public GetStatusChangeRequestedEvent requestId(String requestId) {
            set("requestId", requestId);
            return this;
        }
        /**
         * Sets the contextId field.
         * @param contextId field value
         * @return this model
         */
        public GetStatusChangeRequestedEvent contextId(long contextId) {
            set("contextId", contextId);
            return this;
        }
        /**
         * Sets the readerStates field.
         * @param readerStates field value
         * @return this model
         */
        public GetStatusChangeRequestedEvent readerStates(java.util.List<SmartCardEmulation.ReaderStateIn> readerStates) {
            set("readerStates", readerStates);
            return this;
        }
        /**
         * in milliseconds, if absent, it means &quot;infinite&quot;
         * @param timeout field value
         * @return this model
         */
        public GetStatusChangeRequestedEvent timeout(long timeout) {
            set("timeout", timeout);
            return this;
        }
    }
    /**
     * Fired when |SCardCancel| is called.
     * <p>This maps to: PC/SC Lite: https://pcsclite.apdu.fr/api/group__API.html#gaacbbc0c6d6c0cbbeb4f4debf6fbeeee6 Microsoft: https://learn.microsoft.com/en-us/windows/win32/api/winscard/nf-winscard-scardcancel
     */
    public static final class CancelRequestedEvent extends CdpObject {
        public CancelRequestedEvent() {}
        private CancelRequestedEvent(Map<String, Object> values) { super(values); }
        public static CancelRequestedEvent fromMap(Map<String, Object> values) {
            return new CancelRequestedEvent(values);
        }
        /**
         * Returns the requestId field.
         * @return the protocol field value
         */
        public String requestId() {
            return requireString("requestId");
        }
        /**
         * Returns the contextId field.
         * @return the protocol field value
         */
        public long contextId() {
            return requireLong("contextId");
        }
        /**
         * Sets the requestId field.
         * @param requestId field value
         * @return this model
         */
        public CancelRequestedEvent requestId(String requestId) {
            set("requestId", requestId);
            return this;
        }
        /**
         * Sets the contextId field.
         * @param contextId field value
         * @return this model
         */
        public CancelRequestedEvent contextId(long contextId) {
            set("contextId", contextId);
            return this;
        }
    }
    /**
     * Fired when |SCardConnect| is called.
     * <p>This maps to: PC/SC Lite: https://pcsclite.apdu.fr/api/group__API.html#ga4e515829752e0a8dbc4d630696a8d6a5 Microsoft: https://learn.microsoft.com/en-us/windows/win32/api/winscard/nf-winscard-scardconnecta
     */
    public static final class ConnectRequestedEvent extends CdpObject {
        public ConnectRequestedEvent() {}
        private ConnectRequestedEvent(Map<String, Object> values) { super(values); }
        public static ConnectRequestedEvent fromMap(Map<String, Object> values) {
            return new ConnectRequestedEvent(values);
        }
        /**
         * Returns the requestId field.
         * @return the protocol field value
         */
        public String requestId() {
            return requireString("requestId");
        }
        /**
         * Returns the contextId field.
         * @return the protocol field value
         */
        public long contextId() {
            return requireLong("contextId");
        }
        /**
         * Returns the reader field.
         * @return the protocol field value
         */
        public String reader() {
            return requireString("reader");
        }
        /**
         * Returns the shareMode field.
         * @return the protocol field value
         */
        public SmartCardEmulation.ShareMode shareMode() {
            return require(SHARE_MODE);
        }
        /**
         * Returns the preferredProtocols field.
         * @return the protocol field value
         */
        public SmartCardEmulation.ProtocolSet preferredProtocols() {
            return require(PREFERRED_PROTOCOLS);
        }
        /**
         * Sets the requestId field.
         * @param requestId field value
         * @return this model
         */
        public ConnectRequestedEvent requestId(String requestId) {
            set("requestId", requestId);
            return this;
        }
        /**
         * Sets the contextId field.
         * @param contextId field value
         * @return this model
         */
        public ConnectRequestedEvent contextId(long contextId) {
            set("contextId", contextId);
            return this;
        }
        /**
         * Sets the reader field.
         * @param reader field value
         * @return this model
         */
        public ConnectRequestedEvent reader(String reader) {
            set("reader", reader);
            return this;
        }
        /**
         * Sets the shareMode field.
         * @param shareMode field value
         * @return this model
         */
        public ConnectRequestedEvent shareMode(SmartCardEmulation.ShareMode shareMode) {
            set("shareMode", shareMode);
            return this;
        }
        /**
         * Sets the preferredProtocols field.
         * @param preferredProtocols field value
         * @return this model
         */
        public ConnectRequestedEvent preferredProtocols(SmartCardEmulation.ProtocolSet preferredProtocols) {
            set("preferredProtocols", preferredProtocols);
            return this;
        }
    }
    /**
     * Fired when |SCardDisconnect| is called.
     * <p>This maps to: PC/SC Lite: https://pcsclite.apdu.fr/api/group__API.html#ga4be198045c73ec0deb79e66c0ca1738a Microsoft: https://learn.microsoft.com/en-us/windows/win32/api/winscard/nf-winscard-scarddisconnect
     */
    public static final class DisconnectRequestedEvent extends CdpObject {
        public DisconnectRequestedEvent() {}
        private DisconnectRequestedEvent(Map<String, Object> values) { super(values); }
        public static DisconnectRequestedEvent fromMap(Map<String, Object> values) {
            return new DisconnectRequestedEvent(values);
        }
        /**
         * Returns the requestId field.
         * @return the protocol field value
         */
        public String requestId() {
            return requireString("requestId");
        }
        /**
         * Returns the handle field.
         * @return the protocol field value
         */
        public long handle() {
            return requireLong("handle");
        }
        /**
         * Returns the disposition field.
         * @return the protocol field value
         */
        public SmartCardEmulation.Disposition disposition() {
            return require(DISPOSITION);
        }
        /**
         * Sets the requestId field.
         * @param requestId field value
         * @return this model
         */
        public DisconnectRequestedEvent requestId(String requestId) {
            set("requestId", requestId);
            return this;
        }
        /**
         * Sets the handle field.
         * @param handle field value
         * @return this model
         */
        public DisconnectRequestedEvent handle(long handle) {
            set("handle", handle);
            return this;
        }
        /**
         * Sets the disposition field.
         * @param disposition field value
         * @return this model
         */
        public DisconnectRequestedEvent disposition(SmartCardEmulation.Disposition disposition) {
            set("disposition", disposition);
            return this;
        }
    }
    /**
     * Fired when |SCardTransmit| is called.
     * <p>This maps to: PC/SC Lite: https://pcsclite.apdu.fr/api/group__API.html#ga9a2d77242a271310269065e64633ab99 Microsoft: https://learn.microsoft.com/en-us/windows/win32/api/winscard/nf-winscard-scardtransmit
     */
    public static final class TransmitRequestedEvent extends CdpObject {
        public TransmitRequestedEvent() {}
        private TransmitRequestedEvent(Map<String, Object> values) { super(values); }
        public static TransmitRequestedEvent fromMap(Map<String, Object> values) {
            return new TransmitRequestedEvent(values);
        }
        /**
         * Returns the requestId field.
         * @return the protocol field value
         */
        public String requestId() {
            return requireString("requestId");
        }
        /**
         * Returns the handle field.
         * @return the protocol field value
         */
        public long handle() {
            return requireLong("handle");
        }
        /**
         * Returns the data field.
         * @return the protocol field value
         */
        public String data() {
            return requireString("data");
        }
        /**
         * Returns the protocol field.
         * @return the protocol field value, empty when absent
         */
        public Optional<SmartCardEmulation.Protocol> protocol() {
            return optional(PROTOCOL);
        }
        /**
         * Sets the requestId field.
         * @param requestId field value
         * @return this model
         */
        public TransmitRequestedEvent requestId(String requestId) {
            set("requestId", requestId);
            return this;
        }
        /**
         * Sets the handle field.
         * @param handle field value
         * @return this model
         */
        public TransmitRequestedEvent handle(long handle) {
            set("handle", handle);
            return this;
        }
        /**
         * Sets the data field.
         * @param data field value
         * @return this model
         */
        public TransmitRequestedEvent data(String data) {
            set("data", data);
            return this;
        }
        /**
         * Sets the protocol field.
         * @param protocol field value
         * @return this model
         */
        public TransmitRequestedEvent protocol(SmartCardEmulation.Protocol protocol) {
            set("protocol", protocol);
            return this;
        }
    }
    /**
     * Fired when |SCardControl| is called.
     * <p>This maps to: PC/SC Lite: https://pcsclite.apdu.fr/api/group__API.html#gac3454d4657110fd7f753b2d3d8f4e32f Microsoft: https://learn.microsoft.com/en-us/windows/win32/api/winscard/nf-winscard-scardcontrol
     */
    public static final class ControlRequestedEvent extends CdpObject {
        public ControlRequestedEvent() {}
        private ControlRequestedEvent(Map<String, Object> values) { super(values); }
        public static ControlRequestedEvent fromMap(Map<String, Object> values) {
            return new ControlRequestedEvent(values);
        }
        /**
         * Returns the requestId field.
         * @return the protocol field value
         */
        public String requestId() {
            return requireString("requestId");
        }
        /**
         * Returns the handle field.
         * @return the protocol field value
         */
        public long handle() {
            return requireLong("handle");
        }
        /**
         * Returns the controlCode field.
         * @return the protocol field value
         */
        public long controlCode() {
            return requireLong("controlCode");
        }
        /**
         * Returns the data field.
         * @return the protocol field value
         */
        public String data() {
            return requireString("data");
        }
        /**
         * Sets the requestId field.
         * @param requestId field value
         * @return this model
         */
        public ControlRequestedEvent requestId(String requestId) {
            set("requestId", requestId);
            return this;
        }
        /**
         * Sets the handle field.
         * @param handle field value
         * @return this model
         */
        public ControlRequestedEvent handle(long handle) {
            set("handle", handle);
            return this;
        }
        /**
         * Sets the controlCode field.
         * @param controlCode field value
         * @return this model
         */
        public ControlRequestedEvent controlCode(long controlCode) {
            set("controlCode", controlCode);
            return this;
        }
        /**
         * Sets the data field.
         * @param data field value
         * @return this model
         */
        public ControlRequestedEvent data(String data) {
            set("data", data);
            return this;
        }
    }
    /**
     * Fired when |SCardGetAttrib| is called.
     * <p>This maps to: PC/SC Lite: https://pcsclite.apdu.fr/api/group__API.html#gaacfec51917255b7a25b94c5104961602 Microsoft: https://learn.microsoft.com/en-us/windows/win32/api/winscard/nf-winscard-scardgetattrib
     */
    public static final class GetAttribRequestedEvent extends CdpObject {
        public GetAttribRequestedEvent() {}
        private GetAttribRequestedEvent(Map<String, Object> values) { super(values); }
        public static GetAttribRequestedEvent fromMap(Map<String, Object> values) {
            return new GetAttribRequestedEvent(values);
        }
        /**
         * Returns the requestId field.
         * @return the protocol field value
         */
        public String requestId() {
            return requireString("requestId");
        }
        /**
         * Returns the handle field.
         * @return the protocol field value
         */
        public long handle() {
            return requireLong("handle");
        }
        /**
         * Returns the attribId field.
         * @return the protocol field value
         */
        public long attribId() {
            return requireLong("attribId");
        }
        /**
         * Sets the requestId field.
         * @param requestId field value
         * @return this model
         */
        public GetAttribRequestedEvent requestId(String requestId) {
            set("requestId", requestId);
            return this;
        }
        /**
         * Sets the handle field.
         * @param handle field value
         * @return this model
         */
        public GetAttribRequestedEvent handle(long handle) {
            set("handle", handle);
            return this;
        }
        /**
         * Sets the attribId field.
         * @param attribId field value
         * @return this model
         */
        public GetAttribRequestedEvent attribId(long attribId) {
            set("attribId", attribId);
            return this;
        }
    }
    /**
     * Fired when |SCardSetAttrib| is called.
     * <p>This maps to: PC/SC Lite: https://pcsclite.apdu.fr/api/group__API.html#ga060f0038a4ddfd5dd2b8fadf3c3a2e4f Microsoft: https://learn.microsoft.com/en-us/windows/win32/api/winscard/nf-winscard-scardsetattrib
     */
    public static final class SetAttribRequestedEvent extends CdpObject {
        public SetAttribRequestedEvent() {}
        private SetAttribRequestedEvent(Map<String, Object> values) { super(values); }
        public static SetAttribRequestedEvent fromMap(Map<String, Object> values) {
            return new SetAttribRequestedEvent(values);
        }
        /**
         * Returns the requestId field.
         * @return the protocol field value
         */
        public String requestId() {
            return requireString("requestId");
        }
        /**
         * Returns the handle field.
         * @return the protocol field value
         */
        public long handle() {
            return requireLong("handle");
        }
        /**
         * Returns the attribId field.
         * @return the protocol field value
         */
        public long attribId() {
            return requireLong("attribId");
        }
        /**
         * Returns the data field.
         * @return the protocol field value
         */
        public String data() {
            return requireString("data");
        }
        /**
         * Sets the requestId field.
         * @param requestId field value
         * @return this model
         */
        public SetAttribRequestedEvent requestId(String requestId) {
            set("requestId", requestId);
            return this;
        }
        /**
         * Sets the handle field.
         * @param handle field value
         * @return this model
         */
        public SetAttribRequestedEvent handle(long handle) {
            set("handle", handle);
            return this;
        }
        /**
         * Sets the attribId field.
         * @param attribId field value
         * @return this model
         */
        public SetAttribRequestedEvent attribId(long attribId) {
            set("attribId", attribId);
            return this;
        }
        /**
         * Sets the data field.
         * @param data field value
         * @return this model
         */
        public SetAttribRequestedEvent data(String data) {
            set("data", data);
            return this;
        }
    }
    /**
     * Fired when |SCardStatus| is called.
     * <p>This maps to: PC/SC Lite: https://pcsclite.apdu.fr/api/group__API.html#gae49c3c894ad7ac12a5b896bde70d0382 Microsoft: https://learn.microsoft.com/en-us/windows/win32/api/winscard/nf-winscard-scardstatusa
     */
    public static final class StatusRequestedEvent extends CdpObject {
        public StatusRequestedEvent() {}
        private StatusRequestedEvent(Map<String, Object> values) { super(values); }
        public static StatusRequestedEvent fromMap(Map<String, Object> values) {
            return new StatusRequestedEvent(values);
        }
        /**
         * Returns the requestId field.
         * @return the protocol field value
         */
        public String requestId() {
            return requireString("requestId");
        }
        /**
         * Returns the handle field.
         * @return the protocol field value
         */
        public long handle() {
            return requireLong("handle");
        }
        /**
         * Sets the requestId field.
         * @param requestId field value
         * @return this model
         */
        public StatusRequestedEvent requestId(String requestId) {
            set("requestId", requestId);
            return this;
        }
        /**
         * Sets the handle field.
         * @param handle field value
         * @return this model
         */
        public StatusRequestedEvent handle(long handle) {
            set("handle", handle);
            return this;
        }
    }
    /**
     * Fired when |SCardBeginTransaction| is called.
     * <p>This maps to: PC/SC Lite: https://pcsclite.apdu.fr/api/group__API.html#gaddb835dce01a0da1d6ca02d33ee7d861 Microsoft: https://learn.microsoft.com/en-us/windows/win32/api/winscard/nf-winscard-scardbegintransaction
     */
    public static final class BeginTransactionRequestedEvent extends CdpObject {
        public BeginTransactionRequestedEvent() {}
        private BeginTransactionRequestedEvent(Map<String, Object> values) { super(values); }
        public static BeginTransactionRequestedEvent fromMap(Map<String, Object> values) {
            return new BeginTransactionRequestedEvent(values);
        }
        /**
         * Returns the requestId field.
         * @return the protocol field value
         */
        public String requestId() {
            return requireString("requestId");
        }
        /**
         * Returns the handle field.
         * @return the protocol field value
         */
        public long handle() {
            return requireLong("handle");
        }
        /**
         * Sets the requestId field.
         * @param requestId field value
         * @return this model
         */
        public BeginTransactionRequestedEvent requestId(String requestId) {
            set("requestId", requestId);
            return this;
        }
        /**
         * Sets the handle field.
         * @param handle field value
         * @return this model
         */
        public BeginTransactionRequestedEvent handle(long handle) {
            set("handle", handle);
            return this;
        }
    }
    /**
     * Fired when |SCardEndTransaction| is called.
     * <p>This maps to: PC/SC Lite: https://pcsclite.apdu.fr/api/group__API.html#gae8742473b404363e5c587f570d7e2f3b Microsoft: https://learn.microsoft.com/en-us/windows/win32/api/winscard/nf-winscard-scardendtransaction
     */
    public static final class EndTransactionRequestedEvent extends CdpObject {
        public EndTransactionRequestedEvent() {}
        private EndTransactionRequestedEvent(Map<String, Object> values) { super(values); }
        public static EndTransactionRequestedEvent fromMap(Map<String, Object> values) {
            return new EndTransactionRequestedEvent(values);
        }
        /**
         * Returns the requestId field.
         * @return the protocol field value
         */
        public String requestId() {
            return requireString("requestId");
        }
        /**
         * Returns the handle field.
         * @return the protocol field value
         */
        public long handle() {
            return requireLong("handle");
        }
        /**
         * Returns the disposition field.
         * @return the protocol field value
         */
        public SmartCardEmulation.Disposition disposition() {
            return require(DISPOSITION);
        }
        /**
         * Sets the requestId field.
         * @param requestId field value
         * @return this model
         */
        public EndTransactionRequestedEvent requestId(String requestId) {
            set("requestId", requestId);
            return this;
        }
        /**
         * Sets the handle field.
         * @param handle field value
         * @return this model
         */
        public EndTransactionRequestedEvent handle(long handle) {
            set("handle", handle);
            return this;
        }
        /**
         * Sets the disposition field.
         * @param disposition field value
         * @return this model
         */
        public EndTransactionRequestedEvent disposition(SmartCardEmulation.Disposition disposition) {
            set("disposition", disposition);
            return this;
        }
    }
    public static final class Client extends CdpDomainClient {
        public Client(CdpClient client) { super(client); }
        /**
         * Enables the |SmartCardEmulation| domain.
         * @return a stage completing when the command completes
         */
        public CompletionStage<Void> enable() {
            return call("SmartCardEmulation.enable");
        }
        /**
         * Disables the |SmartCardEmulation| domain.
         * @return a stage completing when the command completes
         */
        public CompletionStage<Void> disable() {
            return call("SmartCardEmulation.disable");
        }
        /**
         * Reports the successful result of a |SCardEstablishContext| call.
         * <p>This maps to: PC/SC Lite: https://pcsclite.apdu.fr/api/group__API.html#gaa1b8970169fd4883a6dc4a8f43f19b67 Microsoft: https://learn.microsoft.com/en-us/windows/win32/api/winscard/nf-winscard-scardestablishcontext
         * @param requestId protocol value
         * @param contextId protocol value
         * @return a stage completing when the command completes
         */
        public CompletionStage<Void> reportEstablishContextResult(String requestId, long contextId) {
            return call("SmartCardEmulation.reportEstablishContextResult", params("requestId", requestId, "contextId", contextId));
        }
        /**
         * Reports the successful result of a |SCardEstablishContext| call.
         * <p>This maps to: PC/SC Lite: https://pcsclite.apdu.fr/api/group__API.html#gaa1b8970169fd4883a6dc4a8f43f19b67 Microsoft: https://learn.microsoft.com/en-us/windows/win32/api/winscard/nf-winscard-scardestablishcontext
         * @param request request parameters
         * @return a stage completing when the command completes
         */
        public CompletionStage<Void> reportEstablishContextResult(ReportEstablishContextResultRequest request) {
            return call("SmartCardEmulation.reportEstablishContextResult", request);
        }
        /**
         * Reports the successful result of a |SCardReleaseContext| call.
         * <p>This maps to: PC/SC Lite: https://pcsclite.apdu.fr/api/group__API.html#ga6aabcba7744c5c9419fdd6404f73a934 Microsoft: https://learn.microsoft.com/en-us/windows/win32/api/winscard/nf-winscard-scardreleasecontext
         * @param requestId protocol value
         * @return a stage completing when the command completes
         */
        public CompletionStage<Void> reportReleaseContextResult(String requestId) {
            return call("SmartCardEmulation.reportReleaseContextResult", params("requestId", requestId));
        }
        /**
         * Reports the successful result of a |SCardReleaseContext| call.
         * <p>This maps to: PC/SC Lite: https://pcsclite.apdu.fr/api/group__API.html#ga6aabcba7744c5c9419fdd6404f73a934 Microsoft: https://learn.microsoft.com/en-us/windows/win32/api/winscard/nf-winscard-scardreleasecontext
         * @param request request parameters
         * @return a stage completing when the command completes
         */
        public CompletionStage<Void> reportReleaseContextResult(ReportReleaseContextResultRequest request) {
            return call("SmartCardEmulation.reportReleaseContextResult", request);
        }
        /**
         * Reports the successful result of a |SCardListReaders| call.
         * <p>This maps to: PC/SC Lite: https://pcsclite.apdu.fr/api/group__API.html#ga93b07815789b3cf2629d439ecf20f0d9 Microsoft: https://learn.microsoft.com/en-us/windows/win32/api/winscard/nf-winscard-scardlistreadersa
         * @param requestId protocol value
         * @param readers protocol value
         * @return a stage completing when the command completes
         */
        public CompletionStage<Void> reportListReadersResult(String requestId, java.util.List<String> readers) {
            return call("SmartCardEmulation.reportListReadersResult", params("requestId", requestId, "readers", readers));
        }
        /**
         * Reports the successful result of a |SCardListReaders| call.
         * <p>This maps to: PC/SC Lite: https://pcsclite.apdu.fr/api/group__API.html#ga93b07815789b3cf2629d439ecf20f0d9 Microsoft: https://learn.microsoft.com/en-us/windows/win32/api/winscard/nf-winscard-scardlistreadersa
         * @param request request parameters
         * @return a stage completing when the command completes
         */
        public CompletionStage<Void> reportListReadersResult(ReportListReadersResultRequest request) {
            return call("SmartCardEmulation.reportListReadersResult", request);
        }
        /**
         * Reports the successful result of a |SCardGetStatusChange| call.
         * <p>This maps to: PC/SC Lite: https://pcsclite.apdu.fr/api/group__API.html#ga33247d5d1257d59e55647c3bb717db24 Microsoft: https://learn.microsoft.com/en-us/windows/win32/api/winscard/nf-winscard-scardgetstatuschangea
         * @param requestId protocol value
         * @param readerStates protocol value
         * @return a stage completing when the command completes
         */
        public CompletionStage<Void> reportGetStatusChangeResult(String requestId, java.util.List<SmartCardEmulation.ReaderStateOut> readerStates) {
            return call("SmartCardEmulation.reportGetStatusChangeResult", params("requestId", requestId, "readerStates", readerStates));
        }
        /**
         * Reports the successful result of a |SCardGetStatusChange| call.
         * <p>This maps to: PC/SC Lite: https://pcsclite.apdu.fr/api/group__API.html#ga33247d5d1257d59e55647c3bb717db24 Microsoft: https://learn.microsoft.com/en-us/windows/win32/api/winscard/nf-winscard-scardgetstatuschangea
         * @param request request parameters
         * @return a stage completing when the command completes
         */
        public CompletionStage<Void> reportGetStatusChangeResult(ReportGetStatusChangeResultRequest request) {
            return call("SmartCardEmulation.reportGetStatusChangeResult", request);
        }
        /**
         * Reports the result of a |SCardBeginTransaction| call. On success, this creates a new transaction object.
         * <p>This maps to: PC/SC Lite: https://pcsclite.apdu.fr/api/group__API.html#gaddb835dce01a0da1d6ca02d33ee7d861 Microsoft: https://learn.microsoft.com/en-us/windows/win32/api/winscard/nf-winscard-scardbegintransaction
         * @param requestId protocol value
         * @param handle protocol value
         * @return a stage completing when the command completes
         */
        public CompletionStage<Void> reportBeginTransactionResult(String requestId, long handle) {
            return call("SmartCardEmulation.reportBeginTransactionResult", params("requestId", requestId, "handle", handle));
        }
        /**
         * Reports the result of a |SCardBeginTransaction| call. On success, this creates a new transaction object.
         * <p>This maps to: PC/SC Lite: https://pcsclite.apdu.fr/api/group__API.html#gaddb835dce01a0da1d6ca02d33ee7d861 Microsoft: https://learn.microsoft.com/en-us/windows/win32/api/winscard/nf-winscard-scardbegintransaction
         * @param request request parameters
         * @return a stage completing when the command completes
         */
        public CompletionStage<Void> reportBeginTransactionResult(ReportBeginTransactionResultRequest request) {
            return call("SmartCardEmulation.reportBeginTransactionResult", request);
        }
        /**
         * Reports the successful result of a call that returns only a result code. Used for: |SCardCancel|, |SCardDisconnect|, |SCardSetAttrib|, |SCardEndTransaction|.
         * <p>This maps to: 1. SCardCancel PC/SC Lite: https://pcsclite.apdu.fr/api/group__API.html#gaacbbc0c6d6c0cbbeb4f4debf6fbeeee6 Microsoft: https://learn.microsoft.com/en-us/windows/win32/api/winscard/nf-winscard-scardcancel
         * <p>2. SCardDisconnect PC/SC Lite: https://pcsclite.apdu.fr/api/group__API.html#ga4be198045c73ec0deb79e66c0ca1738a Microsoft: https://learn.microsoft.com/en-us/windows/win32/api/winscard/nf-winscard-scarddisconnect
         * <p>3. SCardSetAttrib PC/SC Lite: https://pcsclite.apdu.fr/api/group__API.html#ga060f0038a4ddfd5dd2b8fadf3c3a2e4f Microsoft: https://learn.microsoft.com/en-us/windows/win32/api/winscard/nf-winscard-scardsetattrib
         * <p>4. SCardEndTransaction PC/SC Lite: https://pcsclite.apdu.fr/api/group__API.html#gae8742473b404363e5c587f570d7e2f3b Microsoft: https://learn.microsoft.com/en-us/windows/win32/api/winscard/nf-winscard-scardendtransaction
         * @param requestId protocol value
         * @return a stage completing when the command completes
         */
        public CompletionStage<Void> reportPlainResult(String requestId) {
            return call("SmartCardEmulation.reportPlainResult", params("requestId", requestId));
        }
        /**
         * Reports the successful result of a call that returns only a result code. Used for: |SCardCancel|, |SCardDisconnect|, |SCardSetAttrib|, |SCardEndTransaction|.
         * <p>This maps to: 1. SCardCancel PC/SC Lite: https://pcsclite.apdu.fr/api/group__API.html#gaacbbc0c6d6c0cbbeb4f4debf6fbeeee6 Microsoft: https://learn.microsoft.com/en-us/windows/win32/api/winscard/nf-winscard-scardcancel
         * <p>2. SCardDisconnect PC/SC Lite: https://pcsclite.apdu.fr/api/group__API.html#ga4be198045c73ec0deb79e66c0ca1738a Microsoft: https://learn.microsoft.com/en-us/windows/win32/api/winscard/nf-winscard-scarddisconnect
         * <p>3. SCardSetAttrib PC/SC Lite: https://pcsclite.apdu.fr/api/group__API.html#ga060f0038a4ddfd5dd2b8fadf3c3a2e4f Microsoft: https://learn.microsoft.com/en-us/windows/win32/api/winscard/nf-winscard-scardsetattrib
         * <p>4. SCardEndTransaction PC/SC Lite: https://pcsclite.apdu.fr/api/group__API.html#gae8742473b404363e5c587f570d7e2f3b Microsoft: https://learn.microsoft.com/en-us/windows/win32/api/winscard/nf-winscard-scardendtransaction
         * @param request request parameters
         * @return a stage completing when the command completes
         */
        public CompletionStage<Void> reportPlainResult(ReportPlainResultRequest request) {
            return call("SmartCardEmulation.reportPlainResult", request);
        }
        /**
         * Reports the successful result of a |SCardConnect| call.
         * <p>This maps to: PC/SC Lite: https://pcsclite.apdu.fr/api/group__API.html#ga4e515829752e0a8dbc4d630696a8d6a5 Microsoft: https://learn.microsoft.com/en-us/windows/win32/api/winscard/nf-winscard-scardconnecta
         * @param requestId protocol value
         * @param handle protocol value
         * @param activeProtocol protocol value
         * @return a stage completing when the command completes
         */
        public CompletionStage<Void> reportConnectResult(String requestId, long handle, SmartCardEmulation.Protocol activeProtocol) {
            return call("SmartCardEmulation.reportConnectResult", params("requestId", requestId, "handle", handle, "activeProtocol", activeProtocol));
        }
        /**
         * Reports the successful result of a |SCardConnect| call.
         * <p>This maps to: PC/SC Lite: https://pcsclite.apdu.fr/api/group__API.html#ga4e515829752e0a8dbc4d630696a8d6a5 Microsoft: https://learn.microsoft.com/en-us/windows/win32/api/winscard/nf-winscard-scardconnecta
         * @param requestId protocol value
         * @param handle protocol value
         * @return a stage completing when the command completes
         */
        public CompletionStage<Void> reportConnectResult(String requestId, long handle) {
            return call("SmartCardEmulation.reportConnectResult", params("requestId", requestId, "handle", handle));
        }
        /**
         * Reports the successful result of a |SCardConnect| call.
         * <p>This maps to: PC/SC Lite: https://pcsclite.apdu.fr/api/group__API.html#ga4e515829752e0a8dbc4d630696a8d6a5 Microsoft: https://learn.microsoft.com/en-us/windows/win32/api/winscard/nf-winscard-scardconnecta
         * @param request request parameters
         * @return a stage completing when the command completes
         */
        public CompletionStage<Void> reportConnectResult(ReportConnectResultRequest request) {
            return call("SmartCardEmulation.reportConnectResult", request);
        }
        /**
         * Reports the successful result of a call that sends back data on success. Used for |SCardTransmit|, |SCardControl|, and |SCardGetAttrib|.
         * <p>This maps to: 1. SCardTransmit PC/SC Lite: https://pcsclite.apdu.fr/api/group__API.html#ga9a2d77242a271310269065e64633ab99 Microsoft: https://learn.microsoft.com/en-us/windows/win32/api/winscard/nf-winscard-scardtransmit
         * <p>2. SCardControl PC/SC Lite: https://pcsclite.apdu.fr/api/group__API.html#gac3454d4657110fd7f753b2d3d8f4e32f Microsoft: https://learn.microsoft.com/en-us/windows/win32/api/winscard/nf-winscard-scardcontrol
         * <p>3. SCardGetAttrib PC/SC Lite: https://pcsclite.apdu.fr/api/group__API.html#gaacfec51917255b7a25b94c5104961602 Microsoft: https://learn.microsoft.com/en-us/windows/win32/api/winscard/nf-winscard-scardgetattrib
         * @param requestId protocol value
         * @param data protocol value
         * @return a stage completing when the command completes
         */
        public CompletionStage<Void> reportDataResult(String requestId, String data) {
            return call("SmartCardEmulation.reportDataResult", params("requestId", requestId, "data", data));
        }
        /**
         * Reports the successful result of a call that sends back data on success. Used for |SCardTransmit|, |SCardControl|, and |SCardGetAttrib|.
         * <p>This maps to: 1. SCardTransmit PC/SC Lite: https://pcsclite.apdu.fr/api/group__API.html#ga9a2d77242a271310269065e64633ab99 Microsoft: https://learn.microsoft.com/en-us/windows/win32/api/winscard/nf-winscard-scardtransmit
         * <p>2. SCardControl PC/SC Lite: https://pcsclite.apdu.fr/api/group__API.html#gac3454d4657110fd7f753b2d3d8f4e32f Microsoft: https://learn.microsoft.com/en-us/windows/win32/api/winscard/nf-winscard-scardcontrol
         * <p>3. SCardGetAttrib PC/SC Lite: https://pcsclite.apdu.fr/api/group__API.html#gaacfec51917255b7a25b94c5104961602 Microsoft: https://learn.microsoft.com/en-us/windows/win32/api/winscard/nf-winscard-scardgetattrib
         * @param request request parameters
         * @return a stage completing when the command completes
         */
        public CompletionStage<Void> reportDataResult(ReportDataResultRequest request) {
            return call("SmartCardEmulation.reportDataResult", request);
        }
        /**
         * Reports the successful result of a |SCardStatus| call.
         * <p>This maps to: PC/SC Lite: https://pcsclite.apdu.fr/api/group__API.html#gae49c3c894ad7ac12a5b896bde70d0382 Microsoft: https://learn.microsoft.com/en-us/windows/win32/api/winscard/nf-winscard-scardstatusa
         * @param requestId protocol value
         * @param readerName protocol value
         * @param state protocol value
         * @param atr protocol value
         * @param protocol protocol value
         * @return a stage completing when the command completes
         */
        public CompletionStage<Void> reportStatusResult(String requestId, String readerName, SmartCardEmulation.ConnectionState state, String atr, SmartCardEmulation.Protocol protocol) {
            return call("SmartCardEmulation.reportStatusResult", params("requestId", requestId, "readerName", readerName, "state", state, "atr", atr, "protocol", protocol));
        }
        /**
         * Reports the successful result of a |SCardStatus| call.
         * <p>This maps to: PC/SC Lite: https://pcsclite.apdu.fr/api/group__API.html#gae49c3c894ad7ac12a5b896bde70d0382 Microsoft: https://learn.microsoft.com/en-us/windows/win32/api/winscard/nf-winscard-scardstatusa
         * @param requestId protocol value
         * @param readerName protocol value
         * @param state protocol value
         * @param atr protocol value
         * @return a stage completing when the command completes
         */
        public CompletionStage<Void> reportStatusResult(String requestId, String readerName, SmartCardEmulation.ConnectionState state, String atr) {
            return call("SmartCardEmulation.reportStatusResult", params("requestId", requestId, "readerName", readerName, "state", state, "atr", atr));
        }
        /**
         * Reports the successful result of a |SCardStatus| call.
         * <p>This maps to: PC/SC Lite: https://pcsclite.apdu.fr/api/group__API.html#gae49c3c894ad7ac12a5b896bde70d0382 Microsoft: https://learn.microsoft.com/en-us/windows/win32/api/winscard/nf-winscard-scardstatusa
         * @param request request parameters
         * @return a stage completing when the command completes
         */
        public CompletionStage<Void> reportStatusResult(ReportStatusResultRequest request) {
            return call("SmartCardEmulation.reportStatusResult", request);
        }
        /**
         * Reports an error result for the given request.
         * @param requestId protocol value
         * @param resultCode protocol value
         * @return a stage completing when the command completes
         */
        public CompletionStage<Void> reportError(String requestId, SmartCardEmulation.ResultCode resultCode) {
            return call("SmartCardEmulation.reportError", params("requestId", requestId, "resultCode", resultCode));
        }
        /**
         * Reports an error result for the given request.
         * @param request request parameters
         * @return a stage completing when the command completes
         */
        public CompletionStage<Void> reportError(ReportErrorRequest request) {
            return call("SmartCardEmulation.reportError", request);
        }
        /**
         * Fired when |SCardEstablishContext| is called.
         * <p>This maps to: PC/SC Lite: https://pcsclite.apdu.fr/api/group__API.html#gaa1b8970169fd4883a6dc4a8f43f19b67 Microsoft: https://learn.microsoft.com/en-us/windows/win32/api/winscard/nf-winscard-scardestablishcontext
         * @param handler event callback
         * @return a removable subscription
         */
        public CdpSubscription onEstablishContextRequested(Consumer<EstablishContextRequestedEvent> handler) {
            return on("SmartCardEmulation.establishContextRequested", EstablishContextRequestedEvent::fromMap, handler);
        }
        /**
         * Fired when |SCardReleaseContext| is called.
         * <p>This maps to: PC/SC Lite: https://pcsclite.apdu.fr/api/group__API.html#ga6aabcba7744c5c9419fdd6404f73a934 Microsoft: https://learn.microsoft.com/en-us/windows/win32/api/winscard/nf-winscard-scardreleasecontext
         * @param handler event callback
         * @return a removable subscription
         */
        public CdpSubscription onReleaseContextRequested(Consumer<ReleaseContextRequestedEvent> handler) {
            return on("SmartCardEmulation.releaseContextRequested", ReleaseContextRequestedEvent::fromMap, handler);
        }
        /**
         * Fired when |SCardListReaders| is called.
         * <p>This maps to: PC/SC Lite: https://pcsclite.apdu.fr/api/group__API.html#ga93b07815789b3cf2629d439ecf20f0d9 Microsoft: https://learn.microsoft.com/en-us/windows/win32/api/winscard/nf-winscard-scardlistreadersa
         * @param handler event callback
         * @return a removable subscription
         */
        public CdpSubscription onListReadersRequested(Consumer<ListReadersRequestedEvent> handler) {
            return on("SmartCardEmulation.listReadersRequested", ListReadersRequestedEvent::fromMap, handler);
        }
        /**
         * Fired when |SCardGetStatusChange| is called. Timeout is specified in milliseconds.
         * <p>This maps to: PC/SC Lite: https://pcsclite.apdu.fr/api/group__API.html#ga33247d5d1257d59e55647c3bb717db24 Microsoft: https://learn.microsoft.com/en-us/windows/win32/api/winscard/nf-winscard-scardgetstatuschangea
         * @param handler event callback
         * @return a removable subscription
         */
        public CdpSubscription onGetStatusChangeRequested(Consumer<GetStatusChangeRequestedEvent> handler) {
            return on("SmartCardEmulation.getStatusChangeRequested", GetStatusChangeRequestedEvent::fromMap, handler);
        }
        /**
         * Fired when |SCardCancel| is called.
         * <p>This maps to: PC/SC Lite: https://pcsclite.apdu.fr/api/group__API.html#gaacbbc0c6d6c0cbbeb4f4debf6fbeeee6 Microsoft: https://learn.microsoft.com/en-us/windows/win32/api/winscard/nf-winscard-scardcancel
         * @param handler event callback
         * @return a removable subscription
         */
        public CdpSubscription onCancelRequested(Consumer<CancelRequestedEvent> handler) {
            return on("SmartCardEmulation.cancelRequested", CancelRequestedEvent::fromMap, handler);
        }
        /**
         * Fired when |SCardConnect| is called.
         * <p>This maps to: PC/SC Lite: https://pcsclite.apdu.fr/api/group__API.html#ga4e515829752e0a8dbc4d630696a8d6a5 Microsoft: https://learn.microsoft.com/en-us/windows/win32/api/winscard/nf-winscard-scardconnecta
         * @param handler event callback
         * @return a removable subscription
         */
        public CdpSubscription onConnectRequested(Consumer<ConnectRequestedEvent> handler) {
            return on("SmartCardEmulation.connectRequested", ConnectRequestedEvent::fromMap, handler);
        }
        /**
         * Fired when |SCardDisconnect| is called.
         * <p>This maps to: PC/SC Lite: https://pcsclite.apdu.fr/api/group__API.html#ga4be198045c73ec0deb79e66c0ca1738a Microsoft: https://learn.microsoft.com/en-us/windows/win32/api/winscard/nf-winscard-scarddisconnect
         * @param handler event callback
         * @return a removable subscription
         */
        public CdpSubscription onDisconnectRequested(Consumer<DisconnectRequestedEvent> handler) {
            return on("SmartCardEmulation.disconnectRequested", DisconnectRequestedEvent::fromMap, handler);
        }
        /**
         * Fired when |SCardTransmit| is called.
         * <p>This maps to: PC/SC Lite: https://pcsclite.apdu.fr/api/group__API.html#ga9a2d77242a271310269065e64633ab99 Microsoft: https://learn.microsoft.com/en-us/windows/win32/api/winscard/nf-winscard-scardtransmit
         * @param handler event callback
         * @return a removable subscription
         */
        public CdpSubscription onTransmitRequested(Consumer<TransmitRequestedEvent> handler) {
            return on("SmartCardEmulation.transmitRequested", TransmitRequestedEvent::fromMap, handler);
        }
        /**
         * Fired when |SCardControl| is called.
         * <p>This maps to: PC/SC Lite: https://pcsclite.apdu.fr/api/group__API.html#gac3454d4657110fd7f753b2d3d8f4e32f Microsoft: https://learn.microsoft.com/en-us/windows/win32/api/winscard/nf-winscard-scardcontrol
         * @param handler event callback
         * @return a removable subscription
         */
        public CdpSubscription onControlRequested(Consumer<ControlRequestedEvent> handler) {
            return on("SmartCardEmulation.controlRequested", ControlRequestedEvent::fromMap, handler);
        }
        /**
         * Fired when |SCardGetAttrib| is called.
         * <p>This maps to: PC/SC Lite: https://pcsclite.apdu.fr/api/group__API.html#gaacfec51917255b7a25b94c5104961602 Microsoft: https://learn.microsoft.com/en-us/windows/win32/api/winscard/nf-winscard-scardgetattrib
         * @param handler event callback
         * @return a removable subscription
         */
        public CdpSubscription onGetAttribRequested(Consumer<GetAttribRequestedEvent> handler) {
            return on("SmartCardEmulation.getAttribRequested", GetAttribRequestedEvent::fromMap, handler);
        }
        /**
         * Fired when |SCardSetAttrib| is called.
         * <p>This maps to: PC/SC Lite: https://pcsclite.apdu.fr/api/group__API.html#ga060f0038a4ddfd5dd2b8fadf3c3a2e4f Microsoft: https://learn.microsoft.com/en-us/windows/win32/api/winscard/nf-winscard-scardsetattrib
         * @param handler event callback
         * @return a removable subscription
         */
        public CdpSubscription onSetAttribRequested(Consumer<SetAttribRequestedEvent> handler) {
            return on("SmartCardEmulation.setAttribRequested", SetAttribRequestedEvent::fromMap, handler);
        }
        /**
         * Fired when |SCardStatus| is called.
         * <p>This maps to: PC/SC Lite: https://pcsclite.apdu.fr/api/group__API.html#gae49c3c894ad7ac12a5b896bde70d0382 Microsoft: https://learn.microsoft.com/en-us/windows/win32/api/winscard/nf-winscard-scardstatusa
         * @param handler event callback
         * @return a removable subscription
         */
        public CdpSubscription onStatusRequested(Consumer<StatusRequestedEvent> handler) {
            return on("SmartCardEmulation.statusRequested", StatusRequestedEvent::fromMap, handler);
        }
        /**
         * Fired when |SCardBeginTransaction| is called.
         * <p>This maps to: PC/SC Lite: https://pcsclite.apdu.fr/api/group__API.html#gaddb835dce01a0da1d6ca02d33ee7d861 Microsoft: https://learn.microsoft.com/en-us/windows/win32/api/winscard/nf-winscard-scardbegintransaction
         * @param handler event callback
         * @return a removable subscription
         */
        public CdpSubscription onBeginTransactionRequested(Consumer<BeginTransactionRequestedEvent> handler) {
            return on("SmartCardEmulation.beginTransactionRequested", BeginTransactionRequestedEvent::fromMap, handler);
        }
        /**
         * Fired when |SCardEndTransaction| is called.
         * <p>This maps to: PC/SC Lite: https://pcsclite.apdu.fr/api/group__API.html#gae8742473b404363e5c587f570d7e2f3b Microsoft: https://learn.microsoft.com/en-us/windows/win32/api/winscard/nf-winscard-scardendtransaction
         * @param handler event callback
         * @return a removable subscription
         */
        public CdpSubscription onEndTransactionRequested(Consumer<EndTransactionRequestedEvent> handler) {
            return on("SmartCardEmulation.endTransactionRequested", EndTransactionRequestedEvent::fromMap, handler);
        }
    }
}
