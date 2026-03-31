// GENERATED - do not edit. Regenerate via: mvn generate-sources -pl cef4j-native
package net.kurobako.cef4j.gen;

/**
 * Input mode of a virtual keyboard. These constants match their equivalents in Chromium's text_input_mode.h and should
 * not be renumbered. See https://html.spec.whatwg.org/#input-modalities:-the-inputmode-attribute
 */
public enum CefTextInputMode {
    CEF_TEXT_INPUT_MODE_DEFAULT(0L),
    CEF_TEXT_INPUT_MODE_NONE(1L),
    CEF_TEXT_INPUT_MODE_TEXT(2L),
    CEF_TEXT_INPUT_MODE_TEL(3L),
    CEF_TEXT_INPUT_MODE_URL(4L),
    CEF_TEXT_INPUT_MODE_EMAIL(5L),
    CEF_TEXT_INPUT_MODE_NUMERIC(6L),
    CEF_TEXT_INPUT_MODE_DECIMAL(7L),
    CEF_TEXT_INPUT_MODE_SEARCH(8L),
    CEF_TEXT_INPUT_MODE_NUM_VALUES(9L),
    UNKNOWN(-1L);

    public final long value;

    CefTextInputMode(long v) {
        this.value = v;
    }

    public static CefTextInputMode fromLong(long v) {
        for (CefTextInputMode e : values()) {
            if (e.value == v) return e;
        }
        return UNKNOWN;
    }
}
