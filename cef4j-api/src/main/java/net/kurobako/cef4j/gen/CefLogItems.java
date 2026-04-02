// GENERATED - do not edit. Regenerate via: mvn generate-sources -pl cef4j-native
package net.kurobako.cef4j.gen;

/**
 * Log items prepended to each log line.
 *
 * <p>Definition generated from cef_types.h
 *
 * <pre>typedef enum {
 *   LOG_ITEMS_DEFAULT = 0,
 *   LOG_ITEMS_NONE = 1,
 *   LOG_ITEMS_FLAG_PROCESS_ID = 1 &lt;&lt; 1,
 *   LOG_ITEMS_FLAG_THREAD_ID = 1 &lt;&lt; 2,
 *   LOG_ITEMS_FLAG_TIME_STAMP = 1 &lt;&lt; 3,
 *   ...
 * } cef_log_items_t;</pre>
 *
 * <p>Possible values: {@link Kind#DEFAULT}, {@link Kind#NONE}, {@link Kind#FLAG_PROCESS_ID},
 * {@link Kind#FLAG_THREAD_ID}, {@link Kind#FLAG_TIME_STAMP}, {@link Kind#FLAG_TICK_COUNT}
 *
 * @see <a href="https://cef-builds.spotifycdn.com/docs/146.0/cef__types_8h.html">cef_types.h</a>
 */
public final class CefLogItems implements CefEnum<CefLogItems> {

    /** Known constants for {@link CefLogItems}. */
    public enum Kind {
        /** Prepend the default list of items. */
        DEFAULT(0, "0", "LOG_ITEMS_DEFAULT"),
        /** Prepend no items. */
        NONE(1, "1", "LOG_ITEMS_NONE"),
        /** Prepend the process ID. */
        FLAG_PROCESS_ID(1 << 1, "1 << 1", "LOG_ITEMS_FLAG_PROCESS_ID"),
        /** Prepend the thread ID. */
        FLAG_THREAD_ID(1 << 2, "1 << 2", "LOG_ITEMS_FLAG_THREAD_ID"),
        /** Prepend the timestamp. */
        FLAG_TIME_STAMP(1 << 3, "1 << 3", "LOG_ITEMS_FLAG_TIME_STAMP"),
        /** Prepend the tickcount. */
        FLAG_TICK_COUNT(1 << 4, "1 << 4", "LOG_ITEMS_FLAG_TICK_COUNT");

        private static final Kind[] VALUES = Kind.values();

        /** The underlying C enum numeric value. */
        public final long value;

        /** The original C expression/literal (e.g. {@code "1 << 3"}), or the numeric string. */
        public final String expr;

        /** The C constant name (e.g., {@code "cef_log_items_t"}). */
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

    private CefLogItems(long value) {
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
    public static CefLogItems of(long v) {
        return new CefLogItems(v);
    }

    /** Returns an instance for the given known constant. */
    public static CefLogItems of(Kind k) {
        return new CefLogItems(k.value);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (!(obj instanceof CefLogItems)) return false;
        return this.value == ((CefLogItems) obj).value;
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
