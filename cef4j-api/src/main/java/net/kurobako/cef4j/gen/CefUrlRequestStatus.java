// GENERATED - do not edit. Regenerate via: mvn generate-sources -pl cef4j-platform -Dcef.version=150.0.18+gdb11278+chromium-150.0.7871.213
package net.kurobako.cef4j.gen;

import javax.annotation.processing.Generated;

/**
 * Flags that represent CefURLRequest status.
 * <p>Definition generated from cef_types.h
 * <pre>typedef enum {
 *   UR_UNKNOWN = 0,
 *   UR_SUCCESS = 1,
 *   UR_IO_PENDING = 2,
 *   UR_CANCELED = 3,
 *   UR_FAILED = 4,
 *   ...
 * } cef_urlrequest_status_t;</pre>
 * <p>Possible values: {@link Kind#UNKNOWN}, {@link Kind#SUCCESS}, {@link Kind#IO_PENDING}, {@link Kind#CANCELED}, {@link Kind#FAILED}, {@link Kind#NUM_VALUES}
 *
 * @see <a href="https://cef-builds.spotifycdn.com/docs/150.0/cef__types_8h.html">cef_types.h</a>
 */
@Generated("mvn generate-sources -pl cef4j-platform -Dcef.version=150.0.18+gdb11278+chromium-150.0.7871.213")
@SuppressWarnings({"SameReturnValue", "EmptyMethod", "UnusedReturnValue", "unused", "NullableForbidden"})
public final class CefUrlRequestStatus implements CefEnum<CefUrlRequestStatus> {

    /** Known constants for {@link CefUrlRequestStatus}. */
    public enum Kind {
        /** Unknown status.  */
        UNKNOWN(0, "0", "UR_UNKNOWN"),
        /** Request succeeded.  */
        SUCCESS(1, "1", "UR_SUCCESS"),
        /** An IO request is pending, and the caller will be informed when it is completed.  */
        IO_PENDING(2, "2", "UR_IO_PENDING"),
        /** Request was canceled programatically.  */
        CANCELED(3, "3", "UR_CANCELED"),
        /** Request failed for some reason.  */
        FAILED(4, "4", "UR_FAILED"),
        NUM_VALUES(5, "5", "UR_NUM_VALUES");

        private static final Kind[] VALUES = Kind.values();

        /** The underlying C enum numeric value. */
        public final long value;

        /** The original C expression/literal (e.g. {@code "1 << 3"}), or the numeric string. */
        public final String expr;

        /** The C constant name (e.g., {@code "cef_urlrequest_status_t"}). */
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

    private CefUrlRequestStatus(long value) {
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
    public static CefUrlRequestStatus of(long v) {
        return new CefUrlRequestStatus(v);
    }

    /** Returns an instance for the given known constant. */
    public static CefUrlRequestStatus of(Kind k) {
        return new CefUrlRequestStatus(k.value);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (!(obj instanceof CefUrlRequestStatus)) return false;
        return this.value == ((CefUrlRequestStatus) obj).value;
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
