package net.kurobako.cef4j;

import javax.annotation.Nullable;
import net.kurobako.cef4j.policy.NullableBoundary;

/** Thrown when a JavaScript evaluation or handle operation fails in the renderer process. */
@SuppressWarnings("unused")
@NullableBoundary("exception messages follow the nullable JDK Throwable contract")
public class CefScriptException extends RuntimeException {

    private static final long serialVersionUID = 1L;

    public CefScriptException(@Nullable String message) {
        super(message);
    }

    public CefScriptException(@Nullable String message, Throwable cause) {
        super(message, cause);
    }
}
