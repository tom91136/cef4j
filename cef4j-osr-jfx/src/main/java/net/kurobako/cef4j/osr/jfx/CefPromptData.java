package net.kurobako.cef4j.osr.jfx;

/** JavaFX-style prompt payload without depending on {@code javafx-web}. */
public final class CefPromptData {
    private final String message;
    private final String defaultValue;

    public CefPromptData(String message, String defaultValue) {
        this.message = message;
        this.defaultValue = defaultValue;
    }

    public String getMessage() {
        return message;
    }

    public String getDefaultValue() {
        return defaultValue;
    }
}
