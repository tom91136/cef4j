package net.kurobako.cef4j.ipc.session.middleware;

/** Clock used by {@link ReplayCefSession} for recorded responses, events, and intercepts. */
public enum ReplayMode {
    /** Deliver as soon as the preceding recorded API action has been matched. */
    IMMEDIATE,
    /** Preserve each entry's delay relative to the beginning of the recording. */
    TIMED,
    /** Deliver only when {@link ReplayCefSession#advance()} is called. */
    MANUAL
}
