package net.kurobako.cef4j.osr.jfx;

import javafx.event.EventType;

/** JavaFX-style web error event without depending on {@code javafx-web}. */
public final class CefWebErrorEvent extends CefWebEvent<String> {
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
}
