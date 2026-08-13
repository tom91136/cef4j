package net.kurobako.cef4j.ipc.session.middleware;

/** The program under replay diverged from the recorded API operation sequence. */
public final class ReplayMismatchException extends IllegalStateException {
    private static final long serialVersionUID = 1L;

    public ReplayMismatchException(String message) {
        super(message);
    }
}
