// GENERATED - do not edit. Regenerate via: mvn generate-sources -pl cef4j-native
package net.kurobako.cef4j.gen;

/** DOM event processing phases. */
public enum CefDomEventPhase {
    DOM_EVENT_PHASE_UNKNOWN(0L),
    DOM_EVENT_PHASE_CAPTURING(1L),
    DOM_EVENT_PHASE_AT_TARGET(2L),
    DOM_EVENT_PHASE_BUBBLING(3L),
    DOM_EVENT_PHASE_NUM_VALUES(4L),
    UNKNOWN(-1L);

    public final long value;

    CefDomEventPhase(long v) {
        this.value = v;
    }

    public static CefDomEventPhase fromLong(long v) {
        for (CefDomEventPhase e : values()) {
            if (e.value == v) return e;
        }
        return UNKNOWN;
    }
}
