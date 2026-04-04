// GENERATED - do not edit. Regenerate via: mvn generate-sources -pl cef4j-native
// -Dcef.version=146.0.9+g3ca6a87+chromium-146.0.7680.165
package net.kurobako.cef4j.gen;

import javax.annotation.processing.Generated;

/**
 * Return values for {@link net.kurobako.cef4j.gen.CefResponseFilter#filter(java.nio.ByteBuffer, long[],
 * java.nio.ByteBuffer, long[])}.
 *
 * <p>Definition generated from cef_types.h
 *
 * <pre>typedef enum {
 *   RESPONSE_FILTER_NEED_MORE_DATA = 0,
 *   RESPONSE_FILTER_DONE = 1,
 *   RESPONSE_FILTER_ERROR = 2
 * } cef_response_filter_status_t;</pre>
 *
 * <p>Possible values: {@link Kind#NEED_MORE_DATA}, {@link Kind#DONE}, {@link Kind#ERROR}
 *
 * @see <a href="https://cef-builds.spotifycdn.com/docs/146.0/cef__types_8h.html">cef_types.h</a>
 */
@Generated("mvn generate-sources -pl cef4j-native -Dcef.version=146.0.9+g3ca6a87+chromium-146.0.7680.165")
public final class CefResponseFilterStatus implements CefEnum<CefResponseFilterStatus> {

    /** Known constants for {@link CefResponseFilterStatus}. */
    public enum Kind {
        /**
         * Some or all of the pre-filter data was read successfully but more data is needed in order to continue
         * filtering (filtered output is pending).
         */
        NEED_MORE_DATA(0, "0", "RESPONSE_FILTER_NEED_MORE_DATA"),
        /**
         * Some or all of the pre-filter data was read successfully and all available filtered output has been written.
         */
        DONE(1, "1", "RESPONSE_FILTER_DONE"),
        /** An error occurred during filtering. */
        ERROR(2, "2", "RESPONSE_FILTER_ERROR");

        private static final Kind[] VALUES = Kind.values();

        /** The underlying C enum numeric value. */
        public final long value;

        /** The original C expression/literal (e.g. {@code "1 << 3"}), or the numeric string. */
        public final String expr;

        /** The C constant name (e.g., {@code "cef_response_filter_status_t"}). */
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

    private CefResponseFilterStatus(long value) {
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
     * Returns the {@link Kind} matching this value, or empty for unknown/composite values. Use this for exhaustive
     * switch over known constants.
     */
    public java.util.Optional<Kind> kind() {
        for (Kind k : Kind.VALUES) {
            if (k.value == value) return java.util.Optional.of(k);
        }
        return java.util.Optional.empty();
    }

    /** Returns an instance for the given raw value, use {@link #kind} to resolve to a concrete enum. */
    public static CefResponseFilterStatus of(long v) {
        return new CefResponseFilterStatus(v);
    }

    /** Returns an instance for the given known constant. */
    public static CefResponseFilterStatus of(Kind k) {
        return new CefResponseFilterStatus(k.value);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (!(obj instanceof CefResponseFilterStatus)) return false;
        return this.value == ((CefResponseFilterStatus) obj).value;
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
