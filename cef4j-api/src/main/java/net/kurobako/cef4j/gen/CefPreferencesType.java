// GENERATED - do not edit. Regenerate via: mvn generate-sources -pl cef4j-native
package net.kurobako.cef4j.gen;

/** Preferences type passed to CefBrowserProcessHandler::OnRegisterCustomPreferences. */
public enum CefPreferencesType {

    /** Global preferences registered a single time at application startup. */
    CEF_PREFERENCES_TYPE_GLOBAL(0L),
    /** Request context preferences registered each time a new CefRequestContext is created. */
    CEF_PREFERENCES_TYPE_REQUEST_CONTEXT(1L),
    CEF_PREFERENCES_TYPE_NUM_VALUES(2L),
    UNKNOWN(-1L);

    public final long value;

    CefPreferencesType(long v) {
        this.value = v;
    }

    public static CefPreferencesType fromLong(long v) {
        for (CefPreferencesType e : values()) {
            if (e.value == v) return e;
        }
        return UNKNOWN;
    }
}
