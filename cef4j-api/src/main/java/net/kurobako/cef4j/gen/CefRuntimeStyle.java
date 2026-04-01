// GENERATED - do not edit. Regenerate via: mvn generate-sources -pl cef4j-native
package net.kurobako.cef4j.gen;

/** Possible values: {@link Kind#DEFAULT}, {@link Kind#CHROME}, {@link Kind#ALLOY} */
public final class CefRuntimeStyle implements CefEnum<CefRuntimeStyle> {

    /** Known constants for {@link CefRuntimeStyle}. */
    public enum Kind {
        DEFAULT(0, "0", "CEF_RUNTIME_STYLE_DEFAULT"),
        CHROME(1, "1", "CEF_RUNTIME_STYLE_CHROME"),
        ALLOY(2, "2", "CEF_RUNTIME_STYLE_ALLOY");

        private static final Kind[] VALUES = Kind.values();

        /** The underlying C enum numeric value. */
        public final long value;

        /** The original C expression (e.g., {@code "1 << 3"}), or the numeric string. */
        public final String expr;

        /** The C constant name (e.g., {@code "cef_runtime_style_t"}). */
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

    private CefRuntimeStyle(long value) {
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
    public static CefRuntimeStyle of(long v) {
        return new CefRuntimeStyle(v);
    }

    /** Returns an instance for the given known constant. */
    public static CefRuntimeStyle of(Kind k) {
        return new CefRuntimeStyle(k.value);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (!(obj instanceof CefRuntimeStyle)) return false;
        return this.value == ((CefRuntimeStyle) obj).value;
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
