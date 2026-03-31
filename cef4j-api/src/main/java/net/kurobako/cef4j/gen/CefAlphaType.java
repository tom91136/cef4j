// GENERATED - do not edit. Regenerate via: mvn generate-sources -pl cef4j-native
package net.kurobako.cef4j.gen;

/** Describes how to interpret the alpha component of a pixel. */
public enum CefAlphaType {

    /** No transparency. The alpha component is ignored. */
    CEF_ALPHA_TYPE_OPAQUE(0L),
    /** Transparency with pre-multiplied alpha component. */
    CEF_ALPHA_TYPE_PREMULTIPLIED(1L),
    /** Transparency with post-multiplied alpha component. */
    CEF_ALPHA_TYPE_POSTMULTIPLIED(2L),
    UNKNOWN(-1L);

    public final long value;

    CefAlphaType(long v) {
        this.value = v;
    }

    public static CefAlphaType fromLong(long v) {
        for (CefAlphaType e : values()) {
            if (e.value == v) return e;
        }
        return UNKNOWN;
    }
}
