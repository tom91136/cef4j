package net.kurobako.cef4j.ipc.devtools.jackson;

import com.fasterxml.jackson.databind.JsonNode;
import javax.annotation.Nullable;

/** Failure returned by the Chrome DevTools Protocol. */
public final class CdpException extends RuntimeException {
    private static final long serialVersionUID = 1L;
    private final int code;

    @Nullable
    private final transient JsonNode data;

    public CdpException(int code, String message, @Nullable JsonNode data) {
        super(message);
        this.code = code;
        this.data = data;
    }

    public int code() {
        return code;
    }

    @Nullable
    public JsonNode data() {
        return data;
    }
}
