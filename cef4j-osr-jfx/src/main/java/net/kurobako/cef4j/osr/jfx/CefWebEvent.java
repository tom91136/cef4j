package net.kurobako.cef4j.osr.jfx;

import javafx.event.Event;
import javafx.event.EventTarget;
import javafx.event.EventType;

/** JavaFX WebView-compatiable web event without depending on {@code javafx-web}. */
@SuppressWarnings("unused")
public class CefWebEvent<T> extends Event {
    private static final long serialVersionUID = 1L;

    public static final EventType<CefWebEvent<?>> ANY = new EventType<>(Event.ANY, "CEF_WEB");
    public static final EventType<CefWebEvent<?>> ALERT = new EventType<>(ANY, "ALERT");
    public static final EventType<CefWebEvent<?>> RESIZED = new EventType<>(ANY, "RESIZED");
    public static final EventType<CefWebEvent<?>> STATUS_CHANGED = new EventType<>(ANY, "STATUS_CHANGED");
    public static final EventType<CefWebEvent<?>> VISIBILITY_CHANGED = new EventType<>(ANY, "VISIBILITY_CHANGED");

    private final transient T data;

    public CefWebEvent(Object source, EventType<? extends Event> eventType, T data) {
        super(source, source instanceof EventTarget ? (EventTarget) source : null, eventType);
        this.data = data;
    }

    public T getData() {
        return data;
    }
}
