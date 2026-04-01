// GENERATED - do not edit. Regenerate via: mvn generate-sources -pl cef4j-native
package net.kurobako.cef4j.gen;

/**
 * Certificate types supported by CefTestServer.createAndStart(). The matching certificate file must exist in the
 * "net/data/ssl/certificates" directory. See CefSetDataDirectoryForTests() for related configuration.
 *
 * <p>Definition generated from cef_types.h
 *
 * <pre>typedef enum {
 *   CEF_TEST_CERT_OK_IP = 0,
 *   CEF_TEST_CERT_OK_DOMAIN = 1,
 *   CEF_TEST_CERT_EXPIRED = 2,
 *   CEF_TEST_CERT_NUM_VALUES = 3
 * } cef_test_cert_type_t;</pre>
 *
 * <p>Possible values: {@link Kind#OK_IP}, {@link Kind#OK_DOMAIN}, {@link Kind#EXPIRED}, {@link Kind#NUM_VALUES}
 *
 * @see <a href="https://cef-builds.spotifycdn.com/docs/146.0/cef__types_8h.html">cef_types.h</a>
 */
public final class CefTestCertType implements CefEnum<CefTestCertType> {

    /** Known constants for {@link CefTestCertType}. */
    public enum Kind {
        /** Valid certificate using the IP (127.0.0.1). Loads the "ok_cert.pem" file. */
        OK_IP(0, "0", "CEF_TEST_CERT_OK_IP"),
        /** Valid certificate using the domain ("localhost"). Loads the "localhost_cert.pem" file. */
        OK_DOMAIN(1, "1", "CEF_TEST_CERT_OK_DOMAIN"),
        /** Expired certificate. Loads the "expired_cert.pem" file. */
        EXPIRED(2, "2", "CEF_TEST_CERT_EXPIRED"),
        NUM_VALUES(3, "3", "CEF_TEST_CERT_NUM_VALUES");

        private static final Kind[] VALUES = Kind.values();

        /** The underlying C enum numeric value. */
        public final long value;

        /** The original C expression (e.g., {@code "1 << 3"}), or the numeric string. */
        public final String expr;

        /** The C constant name (e.g., {@code "cef_test_cert_type_t"}). */
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

    /** The underlying C enum numeric value. May not correspond to any known {@link Kind}. */
    public final long value;

    private CefTestCertType(long value) {
        this.value = value;
    }

    @Override
    public long value() {
        return value;
    }

    @Override
    public String expr() {
        return kind().map(k -> k.expr).orElse(String.valueOf(value));
    }

    @Override
    public String name() {
        return kind().map(k -> k.name).orElse("UNKNOWN(" + value + ")");
    }

    /**
     * Returns the {@link Kind} matching this value, or empty for unknown/composite values. Use this for exhaustive
     * switch over known constants.
     */
    public java.util.Optional<Kind> kind() {
        for (Kind k : Kind.VALUES) {
            if (k.value == value) return java.util.Optional.of(k);
        }
        return java.util.Optional.empty();
    }

    /** Returns an instance for the given raw value. No data is lost — unknown or composite values are preserved. */
    public static CefTestCertType of(long v) {
        return new CefTestCertType(v);
    }

    /** Returns an instance for the given known constant. */
    public static CefTestCertType of(Kind k) {
        return new CefTestCertType(k.value);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (!(obj instanceof CefTestCertType)) return false;
        return this.value == ((CefTestCertType) obj).value;
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
