package net.kurobako.cef4j.webdriver;

/** Standard WebDriver error code and its HTTP status. */
public enum WebDriverError {
    ELEMENT_CLICK_INTERCEPTED("element click intercepted", 400),
    ELEMENT_NOT_INTERACTABLE("element not interactable", 400),
    INVALID_ARGUMENT("invalid argument", 400),
    INVALID_SESSION_ID("invalid session id", 404),
    INVALID_SELECTOR("invalid selector", 400),
    JAVASCRIPT_ERROR("javascript error", 500),
    NO_SUCH_ELEMENT("no such element", 404),
    NO_SUCH_COOKIE("no such cookie", 404),
    SESSION_NOT_CREATED("session not created", 500),
    TIMEOUT("timeout", 500),
    STALE_ELEMENT_REFERENCE("stale element reference", 404),
    UNKNOWN_COMMAND("unknown command", 404),
    UNKNOWN_ERROR("unknown error", 500),
    UNSUPPORTED_OPERATION("unsupported operation", 500);

    private final String code;
    private final int httpStatus;

    WebDriverError(String code, int httpStatus) {
        this.code = code;
        this.httpStatus = httpStatus;
    }

    public String code() {
        return code;
    }

    public int httpStatus() {
        return httpStatus;
    }
}
