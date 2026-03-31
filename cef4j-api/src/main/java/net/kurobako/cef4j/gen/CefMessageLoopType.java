// GENERATED - do not edit. Regenerate via: mvn generate-sources -pl cef4j-native
package net.kurobako.cef4j.gen;

/** Message loop types. Indicates the set of asynchronous events that a message loop can process. */
public enum CefMessageLoopType {

    /** Supports tasks and timers. */
    ML_TYPE_DEFAULT(0L),
    /** Supports tasks, timers and native UI events (e.g. Windows messages). */
    ML_TYPE_UI(1L),
    /** Supports tasks, timers and asynchronous IO events. */
    ML_TYPE_IO(2L),
    ML_NUM_VALUES(3L),
    UNKNOWN(-1L);

    public final long value;

    CefMessageLoopType(long v) {
        this.value = v;
    }

    public static CefMessageLoopType fromLong(long v) {
        for (CefMessageLoopType e : values()) {
            if (e.value == v) return e;
        }
        return UNKNOWN;
    }
}
