package net.kurobako.cef4j.ipc.session;

/**
 * Thrown (via {@link java.util.concurrent.CompletionException}) when the helper-side dispatcher reports a structured
 * error — currently the "handle no longer exists" signal that comes back as {@link Envelope.Kind#ERROR} instead of a
 * silent zero-default response. Carries an error code so callers can distinguish recoverable cases (handle gone after
 * close, race with shutdown) from unrecoverable ones (decode failure, internal bug).
 */
public class CefRemoteException extends RuntimeException {

    private static final long serialVersionUID = 1L;

    /** Receiver handle was missing from the helper's HandleTable when the dispatcher tried to resolve it. */
    public static final int CODE_RECEIVER_GONE = 1;

    private final int code;

    public CefRemoteException(int code, String message) {
        super(message);
        this.code = code;
    }

    public int code() {
        return code;
    }
}
