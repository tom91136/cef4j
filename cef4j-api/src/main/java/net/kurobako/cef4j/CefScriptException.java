package net.kurobako.cef4j;

import javax.annotation.Nullable;

/** Thrown when a JavaScript evaluation or handle operation fails in the renderer process. */
@SuppressWarnings("unused")
public class CefScriptException extends RuntimeException {

    private static final long serialVersionUID = 1L;

    // exception message is optional
    @SuppressWarnings("NullableForbidden")
    public CefScriptException(@Nullable String message) {
        super(message);
    }

    @SuppressWarnings("NullableForbidden")
    public CefScriptException(@Nullable String message, Throwable cause) {
        super(message, cause);
    }
}
