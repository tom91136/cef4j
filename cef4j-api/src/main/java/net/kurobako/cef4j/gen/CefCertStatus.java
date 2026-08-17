// GENERATED - do not edit. Regenerate via: mvn generate-sources -pl cef4j-platform -Dcef.version=150.0.18+gdb11278+chromium-150.0.7871.213
package net.kurobako.cef4j.gen;

import javax.annotation.processing.Generated;

/**
 * Supported certificate status code values. See net\cert\cert_status_flags.h for more information. CERT_STATUS_NONE is new in CEF because we use an enum while cert_status_flags.h uses a typedef and static const variables.
 * <p>Definition generated from cef_types.h
 * <pre>typedef enum {
 *   CERT_STATUS_NONE = 0,
 *   CERT_STATUS_COMMON_NAME_INVALID = 1 &lt;&lt; 0,
 *   CERT_STATUS_DATE_INVALID = 1 &lt;&lt; 1,
 *   CERT_STATUS_AUTHORITY_INVALID = 1 &lt;&lt; 2,
 *   CERT_STATUS_NO_REVOCATION_MECHANISM = 1 &lt;&lt; 4,
 *   ...
 * } cef_cert_status_t;</pre>
 * <p>Possible values: {@link Kind#NONE}, {@link Kind#COMMON_NAME_INVALID}, {@link Kind#DATE_INVALID}, {@link Kind#AUTHORITY_INVALID}, {@link Kind#NO_REVOCATION_MECHANISM}, {@link Kind#UNABLE_TO_CHECK_REVOCATION}, {@link Kind#REVOKED}, {@link Kind#INVALID}, {@link Kind#WEAK_SIGNATURE_ALGORITHM}, {@link Kind#NON_UNIQUE_NAME}, {@link Kind#WEAK_KEY}, {@link Kind#PINNED_KEY_MISSING}, {@link Kind#NAME_CONSTRAINT_VIOLATION}, {@link Kind#VALIDITY_TOO_LONG}, {@link Kind#IS_EV}, {@link Kind#REV_CHECKING_ENABLED}, {@link Kind#SHA1_SIGNATURE_PRESENT}, {@link Kind#CT_COMPLIANCE_FAILED}
 *
 * @see <a href="https://cef-builds.spotifycdn.com/docs/150.0/cef__types_8h.html">cef_types.h</a>
 */
@Generated("mvn generate-sources -pl cef4j-platform -Dcef.version=150.0.18+gdb11278+chromium-150.0.7871.213")
@SuppressWarnings({"SameReturnValue", "EmptyMethod", "UnusedReturnValue", "unused"})
public final class CefCertStatus implements CefEnum<CefCertStatus> {

    /** Known constants for {@link CefCertStatus}. */
    public enum Kind {
        NONE(0, "0", "CERT_STATUS_NONE"),
        COMMON_NAME_INVALID(1 << 0, "1 << 0", "CERT_STATUS_COMMON_NAME_INVALID"),
        DATE_INVALID(1 << 1, "1 << 1", "CERT_STATUS_DATE_INVALID"),
        AUTHORITY_INVALID(1 << 2, "1 << 2", "CERT_STATUS_AUTHORITY_INVALID"),
        NO_REVOCATION_MECHANISM(1 << 4, "1 << 4", "CERT_STATUS_NO_REVOCATION_MECHANISM"),
        UNABLE_TO_CHECK_REVOCATION(1 << 5, "1 << 5", "CERT_STATUS_UNABLE_TO_CHECK_REVOCATION"),
        REVOKED(1 << 6, "1 << 6", "CERT_STATUS_REVOKED"),
        INVALID(1 << 7, "1 << 7", "CERT_STATUS_INVALID"),
        WEAK_SIGNATURE_ALGORITHM(1 << 8, "1 << 8", "CERT_STATUS_WEAK_SIGNATURE_ALGORITHM"),
        NON_UNIQUE_NAME(1 << 10, "1 << 10", "CERT_STATUS_NON_UNIQUE_NAME"),
        WEAK_KEY(1 << 11, "1 << 11", "CERT_STATUS_WEAK_KEY"),
        PINNED_KEY_MISSING(1 << 13, "1 << 13", "CERT_STATUS_PINNED_KEY_MISSING"),
        NAME_CONSTRAINT_VIOLATION(1 << 14, "1 << 14", "CERT_STATUS_NAME_CONSTRAINT_VIOLATION"),
        VALIDITY_TOO_LONG(1 << 15, "1 << 15", "CERT_STATUS_VALIDITY_TOO_LONG"),
        IS_EV(1 << 16, "1 << 16", "CERT_STATUS_IS_EV"),
        REV_CHECKING_ENABLED(1 << 17, "1 << 17", "CERT_STATUS_REV_CHECKING_ENABLED"),
        SHA1_SIGNATURE_PRESENT(1 << 19, "1 << 19", "CERT_STATUS_SHA1_SIGNATURE_PRESENT"),
        CT_COMPLIANCE_FAILED(1 << 20, "1 << 20", "CERT_STATUS_CT_COMPLIANCE_FAILED");

        private static final Kind[] VALUES = Kind.values();

        /** The underlying C enum numeric value. */
        public final long value;

        /** The original C expression/literal (e.g. {@code "1 << 3"}), or the numeric string. */
        public final String expr;

        /** The C constant name (e.g., {@code "cef_cert_status_t"}). */
        public final String name;

        Kind(long value, String expr, String name) {
            this.value = value;
            this.expr = expr;
            this.name = name;
        }

        @Override
        public String toString() {
            return name + "(expr=" + expr + ", value=" + value + ")";
        }
    }

    /** The underlying C enum numeric value. This may not correspond to any known {@link Kind}. */
    public final long value;

    private CefCertStatus(long value) {
        this.value = value;
    }

    /** {@inheritDoc} */
    @Override
    public long value() {
        return value;
    }

    /** {@inheritDoc} */
    @Override
    public String expr() {
        return kind().map(k -> k.expr).orElse(String.valueOf(value));
    }

    /** {@inheritDoc} */
    @Override
    public String name() {
        return kind().map(k -> k.name).orElse("UNKNOWN(" + value + ")");
    }

    /**
     * Returns the {@link Kind} matching this value, or empty for unknown/composite values.
     * Use this for exhaustive switch over known constants.
     */
    public java.util.Optional<Kind> kind() {
        for (Kind k : Kind.VALUES) {
            if (k.value == value) return java.util.Optional.of(k);
        }
        return java.util.Optional.empty();
    }

    /** Returns an instance for the given raw value, use {@link #kind} to resolve to a concrete enum. */
    public static CefCertStatus of(long v) {
        return new CefCertStatus(v);
    }

    /** Returns an instance for the given known constant. */
    public static CefCertStatus of(Kind k) {
        return new CefCertStatus(k.value);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (!(obj instanceof CefCertStatus)) return false;
        return this.value == ((CefCertStatus) obj).value;
    }

    @Override
    public int hashCode() {
        return Long.hashCode(value);
    }

    @Override
    public String toString() {
        return kind().map(Kind::toString).orElse("UNKNOWN(value=" + value + ")");
    }
}
