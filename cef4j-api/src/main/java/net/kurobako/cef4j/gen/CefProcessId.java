// GENERATED - do not edit. Regenerate via: mvn generate-sources -pl cef4j-native
package net.kurobako.cef4j.gen;

/** Existing process IDs. */
public enum CefProcessId {

    /** Browser process. */
    PID_BROWSER(0L),
    /** Renderer process. */
    PID_RENDERER(1L),
    UNKNOWN(-1L);

    public final long value;

    CefProcessId(long v) {
        this.value = v;
    }

    public static CefProcessId fromLong(long v) {
        for (CefProcessId e : values()) {
            if (e.value == v) return e;
        }
        return UNKNOWN;
    }
}
