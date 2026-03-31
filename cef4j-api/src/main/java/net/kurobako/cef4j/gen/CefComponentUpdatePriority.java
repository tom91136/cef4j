// GENERATED - do not edit. Regenerate via: mvn generate-sources -pl cef4j-native
package net.kurobako.cef4j.gen;

public enum CefComponentUpdatePriority {
    CEF_COMPONENT_UPDATE_PRIORITY_BACKGROUND(0L),
    CEF_COMPONENT_UPDATE_PRIORITY_FOREGROUND(1L),
    UNKNOWN(-1L);

    public final long value;

    CefComponentUpdatePriority(long v) {
        this.value = v;
    }

    public static CefComponentUpdatePriority fromLong(long v) {
        for (CefComponentUpdatePriority e : values()) {
            if (e.value == v) return e;
        }
        return UNKNOWN;
    }
}
