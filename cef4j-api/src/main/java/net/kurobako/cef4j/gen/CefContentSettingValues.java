// GENERATED - do not edit. Regenerate via: mvn generate-sources -pl cef4j-native
package net.kurobako.cef4j.gen;

public enum CefContentSettingValues {
    CEF_CONTENT_SETTING_VALUE_DEFAULT(0L),
    CEF_CONTENT_SETTING_VALUE_ALLOW(1L),
    CEF_CONTENT_SETTING_VALUE_BLOCK(2L),
    CEF_CONTENT_SETTING_VALUE_ASK(3L),
    CEF_CONTENT_SETTING_VALUE_SESSION_ONLY(4L),
    CEF_CONTENT_SETTING_VALUE_DETECT_IMPORTANT_CONTENT_DEPRECATED(5L),
    CEF_CONTENT_SETTING_VALUE_NUM_VALUES(6L),
    UNKNOWN(-1L);

    public final long value;

    CefContentSettingValues(long v) {
        this.value = v;
    }

    public static CefContentSettingValues fromLong(long v) {
        for (CefContentSettingValues e : values()) {
            if (e.value == v) return e;
        }
        return UNKNOWN;
    }
}
