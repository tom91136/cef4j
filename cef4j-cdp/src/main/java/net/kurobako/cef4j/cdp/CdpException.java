package net.kurobako.cef4j.cdp;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

/** Failure returned by the Chrome DevTools Protocol. */
public final class CdpException extends RuntimeException {
    private static final long serialVersionUID = 1L;

    private final int code;

    @Nullable
    private final transient Object data;

    // JSON error data may be absent or null
    @SuppressWarnings("NullableForbidden")
    public CdpException(int code, @Nonnull String message, @Nullable Object data) {
        super(message);
        this.code = code;
        this.data = data;
    }

    public int code() {
        return code;
    }

    // JSON error data may be absent or null
    @SuppressWarnings("NullableForbidden")
    @Nullable
    public Object data() {
        return data;
    }
}
