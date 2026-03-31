// GENERATED - do not edit. Regenerate via: mvn generate-sources -pl cef4j-native
package net.kurobako.cef4j.gen;

/** Supported SSL version values. See net/ssl/ssl_connection_status_flags.h for more information. */
public enum CefSslVersion {

    /** Unknown SSL version. */
    SSL_CONNECTION_VERSION_UNKNOWN(0L),
    SSL_CONNECTION_VERSION_SSL2(1L),
    SSL_CONNECTION_VERSION_SSL3(2L),
    SSL_CONNECTION_VERSION_TLS1(3L),
    SSL_CONNECTION_VERSION_TLS1_1(4L),
    SSL_CONNECTION_VERSION_TLS1_2(5L),
    SSL_CONNECTION_VERSION_TLS1_3(6L),
    SSL_CONNECTION_VERSION_QUIC(7L),
    SSL_CONNECTION_VERSION_NUM_VALUES(8L),
    UNKNOWN(-1L);

    public final long value;

    CefSslVersion(long v) {
        this.value = v;
    }

    public static CefSslVersion fromLong(long v) {
        for (CefSslVersion e : values()) {
            if (e.value == v) return e;
        }
        return UNKNOWN;
    }
}
