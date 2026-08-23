package net.kurobako.cef4j.webdriver;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

/** Failure carrying the standard WebDriver error that the HTTP layer must return. */
public final class WebDriverException extends RuntimeException {
    private static final long serialVersionUID = 1L;

    private final WebDriverError error;

    public WebDriverException(@Nonnull WebDriverError error, @Nonnull String message) {
        super(message);
        this.error = error;
    }

    @SuppressWarnings("NullableForbidden")
    public WebDriverException(@Nonnull WebDriverError error, @Nonnull String message, @Nullable Throwable cause) {
        super(message, cause);
        this.error = error;
    }

    @Nonnull
    public WebDriverError error() {
        return error;
    }
}
