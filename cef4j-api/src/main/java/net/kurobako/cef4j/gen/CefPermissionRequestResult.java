// GENERATED - do not edit. Regenerate via: mvn generate-sources -pl cef4j-native
package net.kurobako.cef4j.gen;

/** Permission request results. */
public enum CefPermissionRequestResult {

    /** Accept the permission request as an explicit user action. */
    CEF_PERMISSION_RESULT_ACCEPT(0L),
    /** Deny the permission request as an explicit user action. */
    CEF_PERMISSION_RESULT_DENY(1L),
    /** Dismiss the permission request as an explicit user action. */
    CEF_PERMISSION_RESULT_DISMISS(2L),
    /**
     * Ignore the permission request. If the prompt remains unhandled (e.g. OnShowPermissionPrompt returns false and
     * there is no default permissions UI) then any related promises may remain unresolved.
     */
    CEF_PERMISSION_RESULT_IGNORE(3L),
    CEF_PERMISSION_RESULT_NUM_VALUES(4L),
    UNKNOWN(-1L);

    public final long value;

    CefPermissionRequestResult(long v) {
        this.value = v;
    }

    public static CefPermissionRequestResult fromLong(long v) {
        for (CefPermissionRequestResult e : values()) {
            if (e.value == v) return e;
        }
        return UNKNOWN;
    }
}
