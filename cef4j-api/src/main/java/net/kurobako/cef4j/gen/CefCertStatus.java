// GENERATED - do not edit. Regenerate via: mvn generate-sources -pl cef4j-native
package net.kurobako.cef4j.gen;

/**
 * Supported certificate status code values. See net\cert\cert_status_flags.h for more information. CERT_STATUS_NONE is
 * new in CEF because we use an enum while cert_status_flags.h uses a typedef and static const variables.
 */
public enum CefCertStatus {
    CERT_STATUS_NONE(0L),
    CERT_STATUS_COMMON_NAME_INVALID(1L),
    CERT_STATUS_DATE_INVALID(2L),
    CERT_STATUS_AUTHORITY_INVALID(4L),
    CERT_STATUS_NO_REVOCATION_MECHANISM(16L),
    CERT_STATUS_UNABLE_TO_CHECK_REVOCATION(32L),
    CERT_STATUS_REVOKED(64L),
    CERT_STATUS_INVALID(128L),
    CERT_STATUS_WEAK_SIGNATURE_ALGORITHM(256L),
    CERT_STATUS_NON_UNIQUE_NAME(1024L),
    CERT_STATUS_WEAK_KEY(2048L),
    CERT_STATUS_PINNED_KEY_MISSING(8192L),
    CERT_STATUS_NAME_CONSTRAINT_VIOLATION(16384L),
    CERT_STATUS_VALIDITY_TOO_LONG(32768L),
    CERT_STATUS_IS_EV(65536L),
    CERT_STATUS_REV_CHECKING_ENABLED(131072L),
    CERT_STATUS_SHA1_SIGNATURE_PRESENT(524288L),
    CERT_STATUS_CT_COMPLIANCE_FAILED(1048576L),
    UNKNOWN(-1L);

    public final long value;

    CefCertStatus(long v) {
        this.value = v;
    }

    public static CefCertStatus fromLong(long v) {
        for (CefCertStatus e : values()) {
            if (e.value == v) return e;
        }
        return UNKNOWN;
    }
}
