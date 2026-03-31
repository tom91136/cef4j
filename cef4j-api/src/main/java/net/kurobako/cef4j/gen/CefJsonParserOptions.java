// GENERATED - do not edit. Regenerate via: mvn generate-sources -pl cef4j-native
package net.kurobako.cef4j.gen;

/** Options that can be passed to CefParseJSON. */
public enum CefJsonParserOptions {

    /**
     * Parses the input strictly according to RFC 4627. See comments in Chromium's base/json/json_reader.h file for
     * known limitations/ deviations from the RFC.
     */
    JSON_PARSER_RFC(0L),
    /** Allows commas to exist after the last element in structures. */
    JSON_PARSER_ALLOW_TRAILING_COMMAS(1L),
    UNKNOWN(-1L);

    public final long value;

    CefJsonParserOptions(long v) {
        this.value = v;
    }

    public static CefJsonParserOptions fromLong(long v) {
        for (CefJsonParserOptions e : values()) {
            if (e.value == v) return e;
        }
        return UNKNOWN;
    }
}
