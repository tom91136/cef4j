// GENERATED - do not edit. Regenerate via: mvn generate-sources -pl cef4j-native
package net.kurobako.cef4j.gen;

/**
 * Process result codes. This is not a comprehensive list, as result codes might also include platform-specific crash
 * values (Posix signal or Windows hardware exception), or internal-only implementation values.
 */
public enum CefResultcode {
    CEF_RESULT_CODE_NORMAL_EXIT(0L),
    /** Process was killed by user or system. */
    CEF_RESULT_CODE_KILLED(1L),
    /** Process hung. */
    CEF_RESULT_CODE_HUNG(2L),
    /** A bad message caused the process termination. */
    CEF_RESULT_CODE_KILLED_BAD_MESSAGE(3L),
    /** The GPU process exited because initialization failed. */
    CEF_RESULT_CODE_GPU_DEAD_ON_ARRIVAL(4L),
    CEF_RESULT_CODE_CHROME_FIRST(5L),
    /** The process is of an unknown type. */
    CEF_RESULT_CODE_BAD_PROCESS_TYPE(6L),
    /** A critical chrome file is missing. */
    CEF_RESULT_CODE_MISSING_DATA(7L),
    /** Command line parameter is not supported. */
    CEF_RESULT_CODE_UNSUPPORTED_PARAM(13L),
    /** The profile was in use on another host. */
    CEF_RESULT_CODE_PROFILE_IN_USE(21L),
    /** Failed to pack an extension via the command line. */
    CEF_RESULT_CODE_PACK_EXTENSION_ERROR(22L),
    /** The browser process exited early by passing the command line to another running browser. */
    CEF_RESULT_CODE_NORMAL_EXIT_PROCESS_NOTIFIED(24L),
    /** A browser process was sandboxed. This should never happen. */
    CEF_RESULT_CODE_INVALID_SANDBOX_STATE(31L),
    /** Cloud policy enrollment failed or was given up by user. */
    CEF_RESULT_CODE_CLOUD_POLICY_ENROLLMENT_FAILED(32L),
    /** The GPU process was terminated due to context lost. */
    CEF_RESULT_CODE_GPU_EXIT_ON_CONTEXT_LOST(34L),
    /** An early startup command was executed and the browser must exit. */
    CEF_RESULT_CODE_NORMAL_EXIT_PACK_EXTENSION_SUCCESS(36L),
    /**
     * The browser process exited because system resources are exhausted. The system state can't be recovered and will
     * be unstable.
     */
    CEF_RESULT_CODE_SYSTEM_RESOURCE_EXHAUSTED(37L),
    /** The browser process exited because it was re-launched without elevation. */
    CEF_RESULT_CODE_NORMAL_EXIT_AUTO_DE_ELEVATED(38L),
    /**
     * Upon encountering a commit failure in a process, PartitionAlloc terminated another process deemed less important.
     */
    CEF_RESULT_CODE_TERMINATED_BY_OTHER_PROCESS_ON_COMMIT_FAILURE(39L),
    CEF_RESULT_CODE_CHROME_LAST(40L),
    CEF_RESULT_CODE_SANDBOX_FATAL_FIRST(7006L),
    /** Windows sandbox could not set the integrity level. */
    CEF_RESULT_CODE_SANDBOX_FATAL_INTEGRITY(7006L),
    /** Windows sandbox could not lower the token. */
    CEF_RESULT_CODE_SANDBOX_FATAL_DROPTOKEN(7007L),
    /** Windows sandbox failed to flush registry handles. */
    CEF_RESULT_CODE_SANDBOX_FATAL_FLUSHANDLES(7008L),
    /** Windows sandbox failed to forbid HCKU caching. */
    CEF_RESULT_CODE_SANDBOX_FATAL_CACHEDISABLE(7009L),
    /** Windows sandbox failed to close pending handles. */
    CEF_RESULT_CODE_SANDBOX_FATAL_CLOSEHANDLES(7010L),
    /** Windows sandbox could not set the mitigation policy. */
    CEF_RESULT_CODE_SANDBOX_FATAL_MITIGATION(7011L),
    /** Windows sandbox exceeded the job memory limit. */
    CEF_RESULT_CODE_SANDBOX_FATAL_MEMORY_EXCEEDED(7012L),
    /** Windows sandbox failed to warmup. */
    CEF_RESULT_CODE_SANDBOX_FATAL_WARMUP(7013L),
    CEF_RESULT_CODE_SANDBOX_FATAL_BROKER_SHUTDOWN_HUNG(7014L),
    CEF_RESULT_CODE_SANDBOX_FATAL_LAST(7015L),
    CEF_RESULT_CODE_NUM_VALUES(7016L),
    UNKNOWN(-1L);

    public final long value;

    CefResultcode(long v) {
        this.value = v;
    }

    public static CefResultcode fromLong(long v) {
        for (CefResultcode e : values()) {
            if (e.value == v) return e;
        }
        return UNKNOWN;
    }
}
