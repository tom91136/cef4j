// GENERATED - do not edit. Regenerate via: ./mvnw generate-sources -pl cef4j-api
// -Dcef.version=150.0.18+gdb11278+chromium-150.0.7871.213
package net.kurobako.cef4j.gen;

import javax.annotation.processing.Generated;
import net.kurobako.cef4j.policy.NullableBoundary;

/**
 * Options that can be passed to CefParseJSON.
 *
 * <p>Definition generated from cef_types.h
 *
 * <pre>typedef enum {
 *   JSON_PARSER_RFC = 0,
 *   JSON_PARSER_ALLOW_TRAILING_COMMAS = 1 &lt;&lt; 0
 * } cef_json_parser_options_t;</pre>
 *
 * <p>Possible values: {@link Kind#RFC}, {@link Kind#ALLOW_TRAILING_COMMAS}
 *
 * @see <a href="https://cef-builds.spotifycdn.com/docs/150.0/cef__types_8h.html">cef_types.h</a>
 */
@Generated("./mvnw generate-sources -pl cef4j-api -Dcef.version=150.0.18+gdb11278+chromium-150.0.7871.213")
@SuppressWarnings({"SameReturnValue", "EmptyMethod", "UnusedReturnValue", "unused"})
@NullableBoundary("generated external ABI")
public final class CefJsonParserOptions implements CefEnum<CefJsonParserOptions> {

    /** Known constants for {@link CefJsonParserOptions}. */
    public enum Kind {
        /**
         * Parses the input strictly according to RFC 4627. See comments in Chromium's base/json/json_reader.h file for
         * known limitations/ deviations from the RFC.
         */
        RFC(0, "0", "JSON_PARSER_RFC"),
        /** Allows commas to exist after the last element in structures. */
        ALLOW_TRAILING_COMMAS(1 << 0, "1 << 0", "JSON_PARSER_ALLOW_TRAILING_COMMAS");

        private static final Kind[] VALUES = Kind.values();

        /** The underlying C enum numeric value. */
        public final long value;

        /** The original C expression/literal (e.g. {@code "1 << 3"}), or the numeric string. */
        public final String expr;

        /** The C constant name (e.g., {@code "cef_json_parser_options_t"}). */
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

    private CefJsonParserOptions(long value) {
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
    public static CefJsonParserOptions of(long v) {
        return new CefJsonParserOptions(v);
    }

    /** Returns an instance for the given known constant. */
    public static CefJsonParserOptions of(Kind k) {
        return new CefJsonParserOptions(k.value);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (!(obj instanceof CefJsonParserOptions)) return false;
        return this.value == ((CefJsonParserOptions) obj).value;
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
