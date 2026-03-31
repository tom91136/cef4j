// GENERATED - do not edit. Regenerate via: mvn generate-sources -pl cef4j-native
package net.kurobako.cef4j.gen;

/**
 * Certificate types supported by CefTestServer::CreateAndStart. The matching certificate file must exist in the
 * "net/data/ssl/certificates" directory. See CefSetDataDirectoryForTests() for related configuration.
 */
public enum CefTestCertType {

    /** Valid certificate using the IP (127.0.0.1). Loads the "ok_cert.pem" file. */
    CEF_TEST_CERT_OK_IP(0L),
    /** Valid certificate using the domain ("localhost"). Loads the "localhost_cert.pem" file. */
    CEF_TEST_CERT_OK_DOMAIN(1L),
    /** Expired certificate. Loads the "expired_cert.pem" file. */
    CEF_TEST_CERT_EXPIRED(2L),
    CEF_TEST_CERT_NUM_VALUES(3L),
    UNKNOWN(-1L);

    public final long value;

    CefTestCertType(long v) {
        this.value = v;
    }

    public static CefTestCertType fromLong(long v) {
        for (CefTestCertType e : values()) {
            if (e.value == v) return e;
        }
        return UNKNOWN;
    }
}
