package net.kurobako.cef4j.ipc.session.middleware;

import javax.annotation.Nullable;

/** Failure terminal recorded from a live session and reproduced without loading its original exception class. */
public final class ReplayedSessionException extends RuntimeException {
    private static final long serialVersionUID = 1L;

    private final String recordedType;

    ReplayedSessionException(String recordedType, @Nullable String recordedMessage) {
        super(recordedType + (recordedMessage == null ? "" : ": " + recordedMessage));
        this.recordedType = recordedType;
    }

    public String recordedType() {
        return recordedType;
    }
}
