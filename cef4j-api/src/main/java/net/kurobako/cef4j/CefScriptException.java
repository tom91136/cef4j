package net.kurobako.cef4j;

/** Thrown when a JavaScript evaluation or handle operation fails in the renderer process. */
@SuppressWarnings("unused")
public class CefScriptException extends RuntimeException {

    private static final long serialVersionUID = 1L;

    public CefScriptException(String message) {
        super(message);
    }

    public CefScriptException(String message, Throwable cause) {
        super(message, cause);
    }
}
