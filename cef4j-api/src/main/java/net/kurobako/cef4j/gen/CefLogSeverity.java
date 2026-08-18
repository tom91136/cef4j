// GENERATED - do not edit. Regenerate via: mvn generate-sources -pl cef4j-platform -Dcef.version=150.0.18+gdb11278+chromium-150.0.7871.213
package net.kurobako.cef4j.gen;

import javax.annotation.processing.Generated;

/**
 * Log severity levels.
 * <p>Definition generated from cef_types.h
 * <pre>typedef enum {
 *   LOGSEVERITY_DEFAULT = 0,
 *   LOGSEVERITY_VERBOSE = 1,
 *   LOGSEVERITY_DEBUG = LOGSEVERITY_VERBOSE,
 *   LOGSEVERITY_INFO = 2,
 *   LOGSEVERITY_WARNING = 3,
 *   ...
 * } cef_log_severity_t;</pre>
 * <p>Possible values: {@link Kind#DEFAULT}, {@link Kind#VERBOSE}, {@link Kind#DEBUG}, {@link Kind#INFO}, {@link Kind#WARNING}, {@link Kind#ERROR}, {@link Kind#FATAL}, {@link Kind#DISABLE}
 *
 * @see <a href="https://cef-builds.spotifycdn.com/docs/150.0/cef__types_8h.html">cef_types.h</a>
 */
@Generated("mvn generate-sources -pl cef4j-platform -Dcef.version=150.0.18+gdb11278+chromium-150.0.7871.213")
@SuppressWarnings({"SameReturnValue", "EmptyMethod", "UnusedReturnValue", "unused", "NullableForbidden"})
public final class CefLogSeverity implements CefEnum<CefLogSeverity> {

    /** Known constants for {@link CefLogSeverity}. */
    public enum Kind {
        /** Default logging (currently INFO logging).  */
        DEFAULT(0, "0", "LOGSEVERITY_DEFAULT"),
        /** Verbose logging.  */
        VERBOSE(1, "1", "LOGSEVERITY_VERBOSE"),
        /** DEBUG logging.  */
        DEBUG(1L, "LOGSEVERITY_VERBOSE", "LOGSEVERITY_DEBUG"),
        /** INFO logging.  */
        INFO(2, "2", "LOGSEVERITY_INFO"),
        /** WARNING logging.  */
        WARNING(3, "3", "LOGSEVERITY_WARNING"),
        /** ERROR logging.  */
        ERROR(4, "4", "LOGSEVERITY_ERROR"),
        /** FATAL logging.  */
        FATAL(5, "5", "LOGSEVERITY_FATAL"),
        /** Disable logging to file for all messages, and to stderr for messages with severity less than FATAL.  */
        DISABLE(99, "99", "LOGSEVERITY_DISABLE");

        private static final Kind[] VALUES = Kind.values();

        /** The underlying C enum numeric value. */
        public final long value;

        /** The original C expression/literal (e.g. {@code "1 << 3"}), or the numeric string. */
        public final String expr;

        /** The C constant name (e.g., {@code "cef_log_severity_t"}). */
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

    private CefLogSeverity(long value) {
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
    public static CefLogSeverity of(long v) {
        return new CefLogSeverity(v);
    }

    /** Returns an instance for the given known constant. */
    public static CefLogSeverity of(Kind k) {
        return new CefLogSeverity(k.value);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (!(obj instanceof CefLogSeverity)) return false;
        return this.value == ((CefLogSeverity) obj).value;
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
