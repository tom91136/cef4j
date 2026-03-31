// GENERATED - do not edit. Regenerate via: mvn generate-sources -pl cef4j-native
package net.kurobako.cef4j.gen;

/** Windows COM initialization mode. Specifies how COM will be initialized for a new thread. */
public enum CefComInitMode {

    /** No COM initialization. */
    COM_INIT_MODE_NONE(0L),
    /** Initialize COM using single-threaded apartments. */
    COM_INIT_MODE_STA(1L),
    /** Initialize COM using multi-threaded apartments. */
    COM_INIT_MODE_MTA(2L),
    UNKNOWN(-1L);

    public final long value;

    CefComInitMode(long v) {
        this.value = v;
    }

    public static CefComInitMode fromLong(long v) {
        for (CefComInitMode e : values()) {
            if (e.value == v) return e;
        }
        return UNKNOWN;
    }
}
