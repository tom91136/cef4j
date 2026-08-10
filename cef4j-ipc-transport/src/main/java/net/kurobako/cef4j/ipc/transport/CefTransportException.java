package net.kurobako.cef4j.ipc.transport;

import java.io.IOException;
import javax.annotation.Nullable;

/**
 * Raised when a {@link CefTransport} send fails or the transport is no longer usable. Callers treat this as an
 * unrecoverable transport-level error and either reconstruct the transport or fail upward.
 */
public class CefTransportException extends IOException {

    private static final long serialVersionUID = 1L;

    public CefTransportException(@Nullable String message) {
        super(message);
    }

    public CefTransportException(@Nullable String message, @Nullable Throwable cause) {
        super(message, cause);
    }
}
