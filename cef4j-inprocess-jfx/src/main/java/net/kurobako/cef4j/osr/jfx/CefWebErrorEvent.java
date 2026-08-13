package net.kurobako.cef4j.osr.jfx;

import javafx.event.EventType;

/** JavaFX WebView-compatible web error event without depending on {@code javafx-web}. */
@SuppressWarnings("unused")
public final class CefWebErrorEvent extends CefWebEvent<String> {
    private static final long serialVersionUID = 1L;

    public static final EventType<CefWebErrorEvent> ANY = new EventType<>(CefWebEvent.ANY, "CEF_WEB_ERROR");

    private final Throwable exception;

    public CefWebErrorEvent(
            Object source, EventType<? extends CefWebErrorEvent> eventType, String message, Throwable exception) {
        super(source, eventType, message);
        this.exception = exception;
    }

    public String getMessage() {
        return getData();
    }

    public Throwable getException() {
        return exception;
    }

    public String message() {
        return getData();
    }

    public Throwable exception() {
        return exception;
    }
}
