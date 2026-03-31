// GENERATED - do not edit. Regenerate via: mvn generate-sources -pl cef4j-native
package net.kurobako.cef4j.gen;

public enum CefRuntimeStyle {
    CEF_RUNTIME_STYLE_DEFAULT(0L),
    CEF_RUNTIME_STYLE_CHROME(1L),
    CEF_RUNTIME_STYLE_ALLOY(2L),
    UNKNOWN(-1L);

    public final long value;

    CefRuntimeStyle(long v) {
        this.value = v;
    }

    public static CefRuntimeStyle fromLong(long v) {
        for (CefRuntimeStyle e : values()) {
            if (e.value == v) return e;
        }
        return UNKNOWN;
    }
}
