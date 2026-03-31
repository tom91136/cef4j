// GENERATED - do not edit. Regenerate via: mvn generate-sources -pl cef4j-native
package net.kurobako.cef4j.gen;

/** Options that can be passed to CefWriteJSON. */
public enum CefJsonWriterOptions {

    /** Default behavior. */
    JSON_WRITER_DEFAULT(0L),
    /**
     * This option instructs the writer that if a Binary value is encountered, the value (and key if within a
     * dictionary) will be omitted from the output, and success will be returned. Otherwise, if a binary value is
     * encountered, failure will be returned.
     */
    JSON_WRITER_OMIT_BINARY_VALUES(1L),
    /**
     * This option instructs the writer to write doubles that have no fractional part as a normal integer (i.e., without
     * using exponential notation or appending a '.0') as long as the value is within the range of a 64-bit int.
     */
    JSON_WRITER_OMIT_DOUBLE_TYPE_PRESERVATION(2L),
    /** Return a slightly nicer formatted json string (pads with whitespace to help with readability). */
    JSON_WRITER_PRETTY_PRINT(4L),
    UNKNOWN(-1L);

    public final long value;

    CefJsonWriterOptions(long v) {
        this.value = v;
    }

    public static CefJsonWriterOptions fromLong(long v) {
        for (CefJsonWriterOptions e : values()) {
            if (e.value == v) return e;
        }
        return UNKNOWN;
    }
}
