// GENERATED - do not edit. Regenerate via: mvn generate-sources -pl cef4j-native
package net.kurobako.cef4j.gen;

/** Supported value types. */
public enum CefValueType {
    VTYPE_INVALID(0L),
    VTYPE_NULL(1L),
    VTYPE_BOOL(2L),
    VTYPE_INT(3L),
    VTYPE_DOUBLE(4L),
    VTYPE_STRING(5L),
    VTYPE_BINARY(6L),
    VTYPE_DICTIONARY(7L),
    VTYPE_LIST(8L),
    VTYPE_NUM_VALUES(9L),
    UNKNOWN(-1L);

    public final long value;

    CefValueType(long v) {
        this.value = v;
    }

    public static CefValueType fromLong(long v) {
        for (CefValueType e : values()) {
            if (e.value == v) return e;
        }
        return UNKNOWN;
    }
}
