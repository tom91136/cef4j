package net.kurobako.cef4j.webdriver;

import net.kurobako.cef4j.policy.NullableBoundary;
import org.jspecify.annotations.Nullable;

/** Failure carrying the standard WebDriver error that the HTTP layer must return. */
@NullableBoundary("exception causes follow the nullable JDK Throwable contract")
public final class WebDriverException extends RuntimeException {
    private static final long serialVersionUID = 1L;

    private final WebDriverError error;

    public WebDriverException(WebDriverError error, String message) {
        super(message);
        this.error = error;
    }

    public WebDriverException(WebDriverError error, String message, @Nullable Throwable cause) {
        super(message, cause);
        this.error = error;
    }

    public WebDriverError error() {
        return error;
    }
}
