package net.kurobako.cef4j.ipc.frame;

/** Why a stateful codec must discard its reference-frame chain. */
public enum CodecResetReason {
    KEY_FRAME_REQUESTED,
    SOURCE_RESIZED,
    DEPENDENCY_LOST,
    STREAM_RESTARTED
}
