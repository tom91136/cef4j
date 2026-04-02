// GENERATED - do not edit. Regenerate via: mvn generate-sources -pl cef4j-native
package net.kurobako.cef4j.gen;

/**
 * Process termination status values.
 *
 * <p>Definition generated from cef_types.h
 *
 * <pre>typedef enum {
 *   TS_ABNORMAL_TERMINATION = 0,
 *   TS_PROCESS_WAS_KILLED = 1,
 *   TS_PROCESS_CRASHED = 2,
 *   TS_PROCESS_OOM = 3,
 *   TS_LAUNCH_FAILED = 4,
 *   ...
 * } cef_termination_status_t;</pre>
 *
 * <p>Possible values: {@link Kind#ABNORMAL_TERMINATION}, {@link Kind#PROCESS_WAS_KILLED}, {@link Kind#PROCESS_CRASHED},
 * {@link Kind#PROCESS_OOM}, {@link Kind#LAUNCH_FAILED}, {@link Kind#INTEGRITY_FAILURE}, {@link Kind#NUM_VALUES}
 *
 * @see <a href="https://cef-builds.spotifycdn.com/docs/146.0/cef__types_8h.html">cef_types.h</a>
 */
public final class CefTerminationStatus implements CefEnum<CefTerminationStatus> {

    /** Known constants for {@link CefTerminationStatus}. */
    public enum Kind {
        /** Non-zero exit status. */
        ABNORMAL_TERMINATION(0, "0", "TS_ABNORMAL_TERMINATION"),
        /** SIGKILL or task manager kill. */
        PROCESS_WAS_KILLED(1, "1", "TS_PROCESS_WAS_KILLED"),
        /** Segmentation fault. */
        PROCESS_CRASHED(2, "2", "TS_PROCESS_CRASHED"),
        /** Out of memory. Some platforms may use TS_PROCESS_CRASHED instead. */
        PROCESS_OOM(3, "3", "TS_PROCESS_OOM"),
        /** Child process never launched. */
        LAUNCH_FAILED(4, "4", "TS_LAUNCH_FAILED"),
        /** On Windows, the OS terminated the process due to code integrity failure. */
        INTEGRITY_FAILURE(5, "5", "TS_INTEGRITY_FAILURE"),
        NUM_VALUES(6, "6", "TS_NUM_VALUES");

        private static final Kind[] VALUES = Kind.values();

        /** The underlying C enum numeric value. */
        public final long value;

        /** The original C expression/literal (e.g. {@code "1 << 3"}), or the numeric string. */
        public final String expr;

        /** The C constant name (e.g., {@code "cef_termination_status_t"}). */
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

    private CefTerminationStatus(long value) {
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
    public static CefTerminationStatus of(long v) {
        return new CefTerminationStatus(v);
    }

    /** Returns an instance for the given known constant. */
    public static CefTerminationStatus of(Kind k) {
        return new CefTerminationStatus(k.value);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (!(obj instanceof CefTerminationStatus)) return false;
        return this.value == ((CefTerminationStatus) obj).value;
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
