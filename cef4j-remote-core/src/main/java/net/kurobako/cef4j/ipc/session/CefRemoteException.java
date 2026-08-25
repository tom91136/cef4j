package net.kurobako.cef4j.ipc.session;

/**
 * Thrown (via {@link java.util.concurrent.CompletionException}) when the server-side dispatcher reports a structured
 * error that comes back as {@link Envelope.Kind#ERROR} instead of a silent response or an eventual client timeout.
 * Carries an error code so callers can distinguish missing handles, malformed requests, and rejected CEF task
 * submissions.
 */
public class CefRemoteException extends RuntimeException {

    private static final long serialVersionUID = 1L;

    /** Receiver handle was missing from the server's HandleTable when the dispatcher tried to resolve it. */
    public static final int CODE_RECEIVER_GONE = 1;

    public static final int CODE_MALFORMED_REQUEST = 2;

    public static final int CODE_TASK_REJECTED = 3;

    private final int code;

    public CefRemoteException(int code, String message) {
        super(message);
        this.code = code;
    }

    public int code() {
        return code;
    }
}
