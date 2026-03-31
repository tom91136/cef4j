// GENERATED - do not edit. Regenerate via: mvn generate-sources -pl cef4j-native
package net.kurobako.cef4j.gen;

/** Log severity levels. */
public enum CefLogSeverity {

    /** Default logging (currently INFO logging). */
    LOGSEVERITY_DEFAULT(0L),
    /** Verbose logging. */
    LOGSEVERITY_VERBOSE(1L),
    /** DEBUG logging. */
    LOGSEVERITY_DEBUG(1L),
    /** INFO logging. */
    LOGSEVERITY_INFO(2L),
    /** WARNING logging. */
    LOGSEVERITY_WARNING(3L),
    /** ERROR logging. */
    LOGSEVERITY_ERROR(4L),
    /** FATAL logging. */
    LOGSEVERITY_FATAL(5L),
    /** Disable logging to file for all messages, and to stderr for messages with severity less than FATAL. */
    LOGSEVERITY_DISABLE(99L),
    UNKNOWN(-1L);

    public final long value;

    CefLogSeverity(long v) {
        this.value = v;
    }

    public static CefLogSeverity fromLong(long v) {
        for (CefLogSeverity e : values()) {
            if (e.value == v) return e;
        }
        return UNKNOWN;
    }
}
