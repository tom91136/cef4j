// GENERATED - do not edit. Regenerate via: mvn generate-sources -pl cef4j-native
package net.kurobako.cef4j.gen;

public enum CefComponentState {
    CEF_COMPONENT_STATE_NEW(0L),
    CEF_COMPONENT_STATE_CHECKING(1L),
    CEF_COMPONENT_STATE_CAN_UPDATE(2L),
    CEF_COMPONENT_STATE_DOWNLOADING(3L),
    CEF_COMPONENT_STATE_DECOMPRESSING(4L),
    CEF_COMPONENT_STATE_PATCHING(5L),
    CEF_COMPONENT_STATE_UPDATING(6L),
    CEF_COMPONENT_STATE_UPDATED(7L),
    CEF_COMPONENT_STATE_UP_TO_DATE(8L),
    CEF_COMPONENT_STATE_UPDATE_ERROR(9L),
    CEF_COMPONENT_STATE_RUN(10L),
    UNKNOWN(-1L);

    public final long value;

    CefComponentState(long v) {
        this.value = v;
    }

    public static CefComponentState fromLong(long v) {
        for (CefComponentState e : values()) {
            if (e.value == v) return e;
        }
        return UNKNOWN;
    }
}
