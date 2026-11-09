// GENERATED - do not edit. Regenerate via: ./mvnw generate-sources -pl cef4j-types
// -Dcef.version=150.0.18+gdb11278+chromium-150.0.7871.213
package net.kurobako.cef4j.gen;

import javax.annotation.processing.Generated;
import net.kurobako.cef4j.policy.NullableBoundary;

/**
 * Supported SSL version values. See net/ssl/ssl_connection_status_flags.h for more information.
 *
 * <p>Definition generated from cef_types.h
 *
 * <pre>typedef enum {
 *   SSL_CONNECTION_VERSION_UNKNOWN = 0,
 *   SSL_CONNECTION_VERSION_SSL2 = 1,
 *   SSL_CONNECTION_VERSION_SSL3 = 2,
 *   SSL_CONNECTION_VERSION_TLS1 = 3,
 *   SSL_CONNECTION_VERSION_TLS1_1 = 4,
 *   ...
 * } cef_ssl_version_t;</pre>
 *
 * <p>Possible values: {@link Kind#UNKNOWN}, {@link Kind#SSL2}, {@link Kind#SSL3}, {@link Kind#TLS1},
 * {@link Kind#TLS1_1}, {@link Kind#TLS1_2}, {@link Kind#TLS1_3}, {@link Kind#QUIC}, {@link Kind#NUM_VALUES}
 *
 * @see <a href="https://cef-builds.spotifycdn.com/docs/150.0/cef__types_8h.html">cef_types.h</a>
 */
@Generated("./mvnw generate-sources -pl cef4j-types -Dcef.version=150.0.18+gdb11278+chromium-150.0.7871.213")
@NullableBoundary("generated external ABI")
public final class CefSslVersion implements CefEnum<CefSslVersion> {

    /** Known constants for {@link CefSslVersion}. */
    public enum Kind {
        /** Unknown SSL version. */
        UNKNOWN(new EnumConstant(0, "0", "SSL_CONNECTION_VERSION_UNKNOWN")),
        SSL2(new EnumConstant(1, "1", "SSL_CONNECTION_VERSION_SSL2")),
        SSL3(new EnumConstant(2, "2", "SSL_CONNECTION_VERSION_SSL3")),
        TLS1(new EnumConstant(3, "3", "SSL_CONNECTION_VERSION_TLS1")),
        TLS1_1(new EnumConstant(4, "4", "SSL_CONNECTION_VERSION_TLS1_1")),
        TLS1_2(new EnumConstant(5, "5", "SSL_CONNECTION_VERSION_TLS1_2")),
        TLS1_3(new EnumConstant(6, "6", "SSL_CONNECTION_VERSION_TLS1_3")),
        QUIC(new EnumConstant(7, "7", "SSL_CONNECTION_VERSION_QUIC")),
        NUM_VALUES(new EnumConstant(8, "8", "SSL_CONNECTION_VERSION_NUM_VALUES"));

        private static final Kind[] VALUES = Kind.values();

        private final EnumConstant constant;

        Kind(EnumConstant constant) {
            this.constant = constant;
        }

        /** The underlying C enum numeric value. */
        public long value() {
            return constant.value();
        }

        /** The original C expression/literal (e.g. {@code "1 << 3"}), or the numeric string. */
        public String expr() {
            return constant.expr();
        }

        /** The C constant name (e.g., {@code "cef_ssl_version_t"}). */
        public String cName() {
            return constant.name();
        }

        @Override
        public String toString() {
            return cName() + "(expr=" + expr() + ", value=" + value() + ")";
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
        return kind().map(Kind::expr).orElse(String.valueOf(value));
    }

    /** {@inheritDoc} */
    @Override
    public String name() {
        return kind().map(Kind::cName).orElse("UNKNOWN(" + value + ")");
    }

    /**
     * Returns the {@link Kind} matching this value, or empty for unknown/composite values. Use this for exhaustive
     * switch over known constants.
     */
    public java.util.Optional<Kind> kind() {
        for (Kind k : Kind.VALUES) {
            if (k.value() == value) return java.util.Optional.of(k);
        }
        return java.util.Optional.empty();
    }

    /** Returns an instance for the given raw value, use {@link #kind} to resolve to a concrete enum. */
    public static CefSslVersion of(long v) {
        return new CefSslVersion(v);
    }

    /** Returns an instance for the given known constant. */
    public static CefSslVersion of(Kind k) {
        return new CefSslVersion(k.value());
    }

    @Override
    public boolean equals(@org.jspecify.annotations.Nullable Object obj) {
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
