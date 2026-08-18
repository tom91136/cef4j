// GENERATED - do not edit. Regenerate via: mvn generate-sources -pl cef4j-platform -Dcef.version=150.0.18+gdb11278+chromium-150.0.7871.213
package net.kurobako.cef4j.gen;

import javax.annotation.processing.Generated;

/**
 * Supported SSL content status flags. See content/public/common/ssl_status.h for more information.
 * <p>Definition generated from cef_types.h
 * <pre>typedef enum {
 *   SSL_CONTENT_NORMAL_CONTENT = 0,
 *   SSL_CONTENT_DISPLAYED_INSECURE_CONTENT = 1 &lt;&lt; 0,
 *   SSL_CONTENT_RAN_INSECURE_CONTENT = 1 &lt;&lt; 1
 * } cef_ssl_content_status_t;</pre>
 * <p>Possible values: {@link Kind#NORMAL_CONTENT}, {@link Kind#DISPLAYED_INSECURE_CONTENT}, {@link Kind#RAN_INSECURE_CONTENT}
 *
 * @see <a href="https://cef-builds.spotifycdn.com/docs/150.0/cef__types_8h.html">cef_types.h</a>
 */
@Generated("mvn generate-sources -pl cef4j-platform -Dcef.version=150.0.18+gdb11278+chromium-150.0.7871.213")
@SuppressWarnings({"SameReturnValue", "EmptyMethod", "UnusedReturnValue", "unused", "NullableForbidden"})
public final class CefSslContentStatus implements CefEnum<CefSslContentStatus> {

    /** Known constants for {@link CefSslContentStatus}. */
    public enum Kind {
        NORMAL_CONTENT(0, "0", "SSL_CONTENT_NORMAL_CONTENT"),
        DISPLAYED_INSECURE_CONTENT(1 << 0, "1 << 0", "SSL_CONTENT_DISPLAYED_INSECURE_CONTENT"),
        RAN_INSECURE_CONTENT(1 << 1, "1 << 1", "SSL_CONTENT_RAN_INSECURE_CONTENT");

        private static final Kind[] VALUES = Kind.values();

        /** The underlying C enum numeric value. */
        public final long value;

        /** The original C expression/literal (e.g. {@code "1 << 3"}), or the numeric string. */
        public final String expr;

        /** The C constant name (e.g., {@code "cef_ssl_content_status_t"}). */
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

    private CefSslContentStatus(long value) {
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
    public static CefSslContentStatus of(long v) {
        return new CefSslContentStatus(v);
    }

    /** Returns an instance for the given known constant. */
    public static CefSslContentStatus of(Kind k) {
        return new CefSslContentStatus(k.value);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (!(obj instanceof CefSslContentStatus)) return false;
        return this.value == ((CefSslContentStatus) obj).value;
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
