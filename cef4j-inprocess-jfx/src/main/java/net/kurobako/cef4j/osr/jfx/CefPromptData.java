package net.kurobako.cef4j.osr.jfx;

/** JavaFX WebView-compatible prompt payload without depending on {@code javafx-web}. */
@SuppressWarnings("unused")
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

    public String message() {
        return message;
    }

    public String defaultValue() {
        return defaultValue;
    }
}
