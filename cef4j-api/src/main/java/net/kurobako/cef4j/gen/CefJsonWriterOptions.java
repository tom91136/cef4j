// GENERATED - do not edit. Regenerate via: mvn generate-sources -pl cef4j-platform -Dcef.version=150.0.18+gdb11278+chromium-150.0.7871.213
package net.kurobako.cef4j.gen;

import javax.annotation.processing.Generated;

/**
 * Options that can be passed to CefWriteJSON.
 * <p>Definition generated from cef_types.h
 * <pre>typedef enum {
 *   JSON_WRITER_DEFAULT = 0,
 *   JSON_WRITER_OMIT_BINARY_VALUES = 1 &lt;&lt; 0,
 *   JSON_WRITER_OMIT_DOUBLE_TYPE_PRESERVATION = 1 &lt;&lt; 1,
 *   JSON_WRITER_PRETTY_PRINT = 1 &lt;&lt; 2
 * } cef_json_writer_options_t;</pre>
 * <p>Possible values: {@link Kind#DEFAULT}, {@link Kind#OMIT_BINARY_VALUES}, {@link Kind#OMIT_DOUBLE_TYPE_PRESERVATION}, {@link Kind#PRETTY_PRINT}
 *
 * @see <a href="https://cef-builds.spotifycdn.com/docs/150.0/cef__types_8h.html">cef_types.h</a>
 */
@Generated("mvn generate-sources -pl cef4j-platform -Dcef.version=150.0.18+gdb11278+chromium-150.0.7871.213")
@SuppressWarnings({"SameReturnValue", "EmptyMethod", "UnusedReturnValue", "unused"})
public final class CefJsonWriterOptions implements CefEnum<CefJsonWriterOptions> {

    /** Known constants for {@link CefJsonWriterOptions}. */
    public enum Kind {
        /** Default behavior.  */
        DEFAULT(0, "0", "JSON_WRITER_DEFAULT"),
        /** This option instructs the writer that if a Binary value is encountered, the value (and key if within a dictionary) will be omitted from the output, and success will be returned. Otherwise, if a binary value is encountered, failure will be returned.  */
        OMIT_BINARY_VALUES(1 << 0, "1 << 0", "JSON_WRITER_OMIT_BINARY_VALUES"),
        /** This option instructs the writer to write doubles that have no fractional part as a normal integer (i.e., without using exponential notation or appending a '.0') as long as the value is within the range of a 64-bit int.  */
        OMIT_DOUBLE_TYPE_PRESERVATION(1 << 1, "1 << 1", "JSON_WRITER_OMIT_DOUBLE_TYPE_PRESERVATION"),
        /** Return a slightly nicer formatted json string (pads with whitespace to help with readability).  */
        PRETTY_PRINT(1 << 2, "1 << 2", "JSON_WRITER_PRETTY_PRINT");

        private static final Kind[] VALUES = Kind.values();

        /** The underlying C enum numeric value. */
        public final long value;

        /** The original C expression/literal (e.g. {@code "1 << 3"}), or the numeric string. */
        public final String expr;

        /** The C constant name (e.g., {@code "cef_json_writer_options_t"}). */
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

    private CefJsonWriterOptions(long value) {
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
    public static CefJsonWriterOptions of(long v) {
        return new CefJsonWriterOptions(v);
    }

    /** Returns an instance for the given known constant. */
    public static CefJsonWriterOptions of(Kind k) {
        return new CefJsonWriterOptions(k.value);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (!(obj instanceof CefJsonWriterOptions)) return false;
        return this.value == ((CefJsonWriterOptions) obj).value;
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
