// GENERATED - do not edit. Regenerate via: mvn generate-sources -pl cef4j-native
package net.kurobako.cef4j.gen;

public enum CefComponentUpdateError {
    CEF_COMPONENT_UPDATE_ERROR_NONE(0L),
    CEF_COMPONENT_UPDATE_ERROR_UPDATE_IN_PROGRESS(1L),
    CEF_COMPONENT_UPDATE_ERROR_UPDATE_CANCELED(2L),
    CEF_COMPONENT_UPDATE_ERROR_RETRY_LATER(3L),
    CEF_COMPONENT_UPDATE_ERROR_SERVICE_ERROR(4L),
    CEF_COMPONENT_UPDATE_ERROR_UPDATE_CHECK_ERROR(5L),
    CEF_COMPONENT_UPDATE_ERROR_CRX_NOT_FOUND(6L),
    CEF_COMPONENT_UPDATE_ERROR_INVALID_ARGUMENT(7L),
    CEF_COMPONENT_UPDATE_ERROR_BAD_CRX_DATA_CALLBACK(8L),
    UNKNOWN(-1L);

    public final long value;

    CefComponentUpdateError(long v) {
        this.value = v;
    }

    public static CefComponentUpdateError fromLong(long v) {
        for (CefComponentUpdateError e : values()) {
            if (e.value == v) return e;
        }
        return UNKNOWN;
    }
}
