// GENERATED - do not edit. Regenerate via: ./mvnw generate-sources -pl cef4j-api
// -Dcef.version=150.0.18+gdb11278+chromium-150.0.7871.213
package net.kurobako.cef4j.gen;

import javax.annotation.processing.Generated;
import net.kurobako.cef4j.policy.NullableBoundary;

/**
 * Process result codes. This is not a comprehensive list, as result codes might also include platform-specific crash
 * values (Posix signal or Windows hardware exception), or internal-only implementation values.
 *
 * <p>Definition generated from cef_types.h
 *
 * <pre>typedef enum {
 *   CEF_RESULT_CODE_NORMAL_EXIT = 0,
 *   CEF_RESULT_CODE_KILLED = 1,
 *   CEF_RESULT_CODE_HUNG = 2,
 *   CEF_RESULT_CODE_KILLED_BAD_MESSAGE = 3,
 *   CEF_RESULT_CODE_GPU_DEAD_ON_ARRIVAL = 4,
 *   ...
 * } cef_resultcode_t;</pre>
 *
 * <p>Possible values: {@link Kind#NORMAL_EXIT}, {@link Kind#KILLED}, {@link Kind#HUNG},
 * {@link Kind#KILLED_BAD_MESSAGE}, {@link Kind#GPU_DEAD_ON_ARRIVAL}, {@link Kind#CHROME_FIRST},
 * {@link Kind#BAD_PROCESS_TYPE}, {@link Kind#MISSING_DATA}, {@link Kind#UNSUPPORTED_PARAM},
 * {@link Kind#PROFILE_IN_USE}, {@link Kind#PACK_EXTENSION_ERROR}, {@link Kind#NORMAL_EXIT_PROCESS_NOTIFIED},
 * {@link Kind#INVALID_SANDBOX_STATE}, {@link Kind#CLOUD_POLICY_ENROLLMENT_FAILED},
 * {@link Kind#GPU_EXIT_ON_CONTEXT_LOST}, {@link Kind#NORMAL_EXIT_PACK_EXTENSION_SUCCESS},
 * {@link Kind#SYSTEM_RESOURCE_EXHAUSTED}, {@link Kind#NORMAL_EXIT_AUTO_DE_ELEVATED},
 * {@link Kind#TERMINATED_BY_OTHER_PROCESS_ON_COMMIT_FAILURE}, {@link Kind#INVALID_ISOLATED_BROWSER_PROCESS},
 * {@link Kind#CHROME_LAST}, {@link Kind#SANDBOX_FATAL_FIRST}, {@link Kind#SANDBOX_FATAL_INTEGRITY},
 * {@link Kind#SANDBOX_FATAL_DROPTOKEN}, {@link Kind#SANDBOX_FATAL_FLUSHANDLES},
 * {@link Kind#SANDBOX_FATAL_CACHEDISABLE}, {@link Kind#SANDBOX_FATAL_CLOSEHANDLES},
 * {@link Kind#SANDBOX_FATAL_MITIGATION}, {@link Kind#SANDBOX_FATAL_MEMORY_EXCEEDED}, {@link Kind#SANDBOX_FATAL_WARMUP},
 * {@link Kind#SANDBOX_FATAL_BROKER_SHUTDOWN_HUNG}, {@link Kind#SANDBOX_FATAL_LAST}, {@link Kind#NUM_VALUES}
 *
 * @see <a href="https://cef-builds.spotifycdn.com/docs/150.0/cef__types_8h.html">cef_types.h</a>
 */
@Generated("./mvnw generate-sources -pl cef4j-api -Dcef.version=150.0.18+gdb11278+chromium-150.0.7871.213")
@SuppressWarnings({"SameReturnValue", "EmptyMethod", "UnusedReturnValue", "unused"})
@NullableBoundary("generated external ABI")
public final class CefResultCode implements CefEnum<CefResultCode> {

    /** Known constants for {@link CefResultCode}. */
    public enum Kind {
        NORMAL_EXIT(0, "0", "CEF_RESULT_CODE_NORMAL_EXIT"),
        /** Process was killed by user or system. */
        KILLED(1, "1", "CEF_RESULT_CODE_KILLED"),
        /** Process hung. */
        HUNG(2, "2", "CEF_RESULT_CODE_HUNG"),
        /** A bad message caused the process termination. */
        KILLED_BAD_MESSAGE(3, "3", "CEF_RESULT_CODE_KILLED_BAD_MESSAGE"),
        /** The GPU process exited because initialization failed. */
        GPU_DEAD_ON_ARRIVAL(4, "4", "CEF_RESULT_CODE_GPU_DEAD_ON_ARRIVAL"),
        CHROME_FIRST(5, "5", "CEF_RESULT_CODE_CHROME_FIRST"),
        /** The process is of an unknown type. */
        BAD_PROCESS_TYPE(6, "6", "CEF_RESULT_CODE_BAD_PROCESS_TYPE"),
        /** A critical chrome file is missing. */
        MISSING_DATA(7, "7", "CEF_RESULT_CODE_MISSING_DATA"),
        /** Command line parameter is not supported. */
        UNSUPPORTED_PARAM(13, "13", "CEF_RESULT_CODE_UNSUPPORTED_PARAM"),
        /** The profile was in use on another host. */
        PROFILE_IN_USE(21, "21", "CEF_RESULT_CODE_PROFILE_IN_USE"),
        /** Failed to pack an extension via the command line. */
        PACK_EXTENSION_ERROR(22, "22", "CEF_RESULT_CODE_PACK_EXTENSION_ERROR"),
        /** The browser process exited early by passing the command line to another running browser. */
        NORMAL_EXIT_PROCESS_NOTIFIED(24, "24", "CEF_RESULT_CODE_NORMAL_EXIT_PROCESS_NOTIFIED"),
        /** A browser process was sandboxed. This should never happen. */
        INVALID_SANDBOX_STATE(31, "31", "CEF_RESULT_CODE_INVALID_SANDBOX_STATE"),
        /** Cloud policy enrollment failed or was given up by user. */
        CLOUD_POLICY_ENROLLMENT_FAILED(32, "32", "CEF_RESULT_CODE_CLOUD_POLICY_ENROLLMENT_FAILED"),
        /** The GPU process was terminated due to context lost. */
        GPU_EXIT_ON_CONTEXT_LOST(34, "34", "CEF_RESULT_CODE_GPU_EXIT_ON_CONTEXT_LOST"),
        /** An early startup command was executed and the browser must exit. */
        NORMAL_EXIT_PACK_EXTENSION_SUCCESS(36, "36", "CEF_RESULT_CODE_NORMAL_EXIT_PACK_EXTENSION_SUCCESS"),
        /**
         * The browser process exited because system resources are exhausted. The system state can't be recovered and
         * will be unstable.
         */
        SYSTEM_RESOURCE_EXHAUSTED(37, "37", "CEF_RESULT_CODE_SYSTEM_RESOURCE_EXHAUSTED"),
        /** The browser process exited because it was re-launched without elevation. */
        NORMAL_EXIT_AUTO_DE_ELEVATED(38, "38", "CEF_RESULT_CODE_NORMAL_EXIT_AUTO_DE_ELEVATED"),
        /**
         * Upon encountering a commit failure in a process, PartitionAlloc terminated another process deemed less
         * important.
         */
        TERMINATED_BY_OTHER_PROCESS_ON_COMMIT_FAILURE(
                39, "39", "CEF_RESULT_CODE_TERMINATED_BY_OTHER_PROCESS_ON_COMMIT_FAILURE"),
        /**
         * The isolated browser process launched but it was not possible to wait on the exit of the process, so the
         * browser must exit.
         */
        INVALID_ISOLATED_BROWSER_PROCESS(40, "40", "CEF_RESULT_CODE_INVALID_ISOLATED_BROWSER_PROCESS"),
        CHROME_LAST(41, "41", "CEF_RESULT_CODE_CHROME_LAST"),
        SANDBOX_FATAL_FIRST(7006, "7006", "CEF_RESULT_CODE_SANDBOX_FATAL_FIRST"),
        /** Windows sandbox could not set the integrity level. */
        SANDBOX_FATAL_INTEGRITY(
                7006L, "CEF_RESULT_CODE_SANDBOX_FATAL_FIRST", "CEF_RESULT_CODE_SANDBOX_FATAL_INTEGRITY"),
        /** Windows sandbox could not lower the token. */
        SANDBOX_FATAL_DROPTOKEN(7007, "7007", "CEF_RESULT_CODE_SANDBOX_FATAL_DROPTOKEN"),
        /** Windows sandbox failed to flush registry handles. */
        SANDBOX_FATAL_FLUSHANDLES(7008, "7008", "CEF_RESULT_CODE_SANDBOX_FATAL_FLUSHANDLES"),
        /** Windows sandbox failed to forbid HCKU caching. */
        SANDBOX_FATAL_CACHEDISABLE(7009, "7009", "CEF_RESULT_CODE_SANDBOX_FATAL_CACHEDISABLE"),
        /** Windows sandbox failed to close pending handles. */
        SANDBOX_FATAL_CLOSEHANDLES(7010, "7010", "CEF_RESULT_CODE_SANDBOX_FATAL_CLOSEHANDLES"),
        /** Windows sandbox could not set the mitigation policy. */
        SANDBOX_FATAL_MITIGATION(7011, "7011", "CEF_RESULT_CODE_SANDBOX_FATAL_MITIGATION"),
        /** Windows sandbox exceeded the job memory limit. */
        SANDBOX_FATAL_MEMORY_EXCEEDED(7012, "7012", "CEF_RESULT_CODE_SANDBOX_FATAL_MEMORY_EXCEEDED"),
        /** Windows sandbox failed to warmup. */
        SANDBOX_FATAL_WARMUP(7013, "7013", "CEF_RESULT_CODE_SANDBOX_FATAL_WARMUP"),
        SANDBOX_FATAL_BROKER_SHUTDOWN_HUNG(7014, "7014", "CEF_RESULT_CODE_SANDBOX_FATAL_BROKER_SHUTDOWN_HUNG"),
        SANDBOX_FATAL_LAST(7015, "7015", "CEF_RESULT_CODE_SANDBOX_FATAL_LAST"),
        NUM_VALUES(7016, "7016", "CEF_RESULT_CODE_NUM_VALUES");

        private static final Kind[] VALUES = Kind.values();

        /** The underlying C enum numeric value. */
        public final long value;

        /** The original C expression/literal (e.g. {@code "1 << 3"}), or the numeric string. */
        public final String expr;

        /** The C constant name (e.g., {@code "cef_resultcode_t"}). */
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

    private CefResultCode(long value) {
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
    public static CefResultCode of(long v) {
        return new CefResultCode(v);
    }

    /** Returns an instance for the given known constant. */
    public static CefResultCode of(Kind k) {
        return new CefResultCode(k.value);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (!(obj instanceof CefResultCode)) return false;
        return this.value == ((CefResultCode) obj).value;
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
