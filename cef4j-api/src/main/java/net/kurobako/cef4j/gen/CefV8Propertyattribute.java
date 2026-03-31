// GENERATED - do not edit. Regenerate via: mvn generate-sources -pl cef4j-native
package net.kurobako.cef4j.gen;

/** V8 property attribute values. */
public enum CefV8Propertyattribute {

    /** Writeable, Enumerable, Configurable */
    V8_PROPERTY_ATTRIBUTE_NONE(0L),
    /** Not writeable */
    V8_PROPERTY_ATTRIBUTE_READONLY(1L),
    /** Not enumerable */
    V8_PROPERTY_ATTRIBUTE_DONTENUM(2L),
    /** Not configurable */
    V8_PROPERTY_ATTRIBUTE_DONTDELETE(4L),
    UNKNOWN(-1L);

    public final long value;

    CefV8Propertyattribute(long v) {
        this.value = v;
    }

    public static CefV8Propertyattribute fromLong(long v) {
        for (CefV8Propertyattribute e : values()) {
            if (e.value == v) return e;
        }
        return UNKNOWN;
    }
}
