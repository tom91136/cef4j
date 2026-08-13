package net.kurobako.cef4j.ipc.devtools.gson;

import com.google.gson.JsonElement;
import javax.annotation.Nonnull;
import javax.annotation.Nullable;

/** Failure returned by the Chrome DevTools Protocol. */
public final class CdpException extends RuntimeException {
    private static final long serialVersionUID = 1L;

    private final int code;

    @Nullable
    private final transient JsonElement data;

    public CdpException(int code, @Nonnull String message, @Nullable JsonElement data) {
        super(message);
        this.code = code;
        this.data = data;
    }

    public int code() {
        return code;
    }

    @Nullable
    public JsonElement data() {
        return data;
    }
}
