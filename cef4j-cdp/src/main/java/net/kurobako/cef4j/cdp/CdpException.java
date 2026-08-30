package net.kurobako.cef4j.cdp;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import net.kurobako.cef4j.policy.NullableBoundary;

/** Failure returned by the Chrome DevTools Protocol. */
@NullableBoundary("CDP error responses may omit data")
public final class CdpException extends RuntimeException {
    private static final long serialVersionUID = 1L;

    private final int code;

    @Nullable
    private final transient Object data;

    public CdpException(int code, @Nonnull String message, @Nullable Object data) {
        super(message);
        this.code = code;
        this.data = data;
    }

    public int code() {
        return code;
    }

    @Nullable
    public Object data() {
        return data;
    }
}
