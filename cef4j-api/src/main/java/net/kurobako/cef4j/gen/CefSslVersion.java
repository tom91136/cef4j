// GENERATED - do not edit. Regenerate via: mvn generate-sources -pl cef4j-native -Dcef.version=146.0.9+g3ca6a87+chromium-146.0.7680.165
package net.kurobako.cef4j.gen;

import javax.annotation.processing.Generated;

/**
 * Supported SSL version values. See net/ssl/ssl_connection_status_flags.h for more information.
 * <p>Definition generated from cef_types.h
 * <pre>typedef enum {
 *   SSL_CONNECTION_VERSION_UNKNOWN = 0,
 *   SSL_CONNECTION_VERSION_SSL2 = 1,
 *   SSL_CONNECTION_VERSION_SSL3 = 2,
 *   SSL_CONNECTION_VERSION_TLS1 = 3,
 *   SSL_CONNECTION_VERSION_TLS1_1 = 4,
 *   ...
 * } cef_ssl_version_t;</pre>
 * <p>Possible values: {@link Kind#UNKNOWN}, {@link Kind#SSL2}, {@link Kind#SSL3}, {@link Kind#TLS1}, {@link Kind#TLS1_1}, {@link Kind#TLS1_2}, {@link Kind#TLS1_3}, {@link Kind#QUIC}, {@link Kind#NUM_VALUES}
 *
 * @see <a href="https://cef-builds.spotifycdn.com/docs/146.0/cef__types_8h.html">cef_types.h</a>
 */
@Generated("mvn generate-sources -pl cef4j-native -Dcef.version=146.0.9+g3ca6a87+chromium-146.0.7680.165")
@SuppressWarnings({"SameReturnValue", "EmptyMethod", "UnusedReturnValue", "unused"})
public final class CefSslVersion implements CefEnum<CefSslVersion> {

    /** Known constants for {@link CefSslVersion}. */
    public enum Kind {
        /** Unknown SSL version. */
        UNKNOWN(0, "0", "SSL_CONNECTION_VERSION_UNKNOWN"),
        SSL2(1, "1", "SSL_CONNECTION_VERSION_SSL2"),
        SSL3(2, "2", "SSL_CONNECTION_VERSION_SSL3"),
        TLS1(3, "3", "SSL_CONNECTION_VERSION_TLS1"),
        TLS1_1(4, "4", "SSL_CONNECTION_VERSION_TLS1_1"),
        TLS1_2(5, "5", "SSL_CONNECTION_VERSION_TLS1_2"),
        TLS1_3(6, "6", "SSL_CONNECTION_VERSION_TLS1_3"),
        QUIC(7, "7", "SSL_CONNECTION_VERSION_QUIC"),
        NUM_VALUES(8, "8", "SSL_CONNECTION_VERSION_NUM_VALUES");

        private static final Kind[] VALUES = Kind.values();

        /** The underlying C enum numeric value. */
        public final long value;

        /** The original C expression/literal (e.g. {@code "1 << 3"}), or the numeric string. */
        public final String expr;

        /** The C constant name (e.g., {@code "cef_ssl_version_t"}). */
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

    private CefSslVersion(long value) {
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
    public static CefSslVersion of(long v) {
        return new CefSslVersion(v);
    }

    /** Returns an instance for the given known constant. */
    public static CefSslVersion of(Kind k) {
        return new CefSslVersion(k.value);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (!(obj instanceof CefSslVersion)) return false;
        return this.value == ((CefSslVersion) obj).value;
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
