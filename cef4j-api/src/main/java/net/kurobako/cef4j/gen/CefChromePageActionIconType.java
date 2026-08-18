// GENERATED - do not edit. Regenerate via: mvn generate-sources -pl cef4j-platform -Dcef.version=150.0.18+gdb11278+chromium-150.0.7871.213
package net.kurobako.cef4j.gen;

import javax.annotation.processing.Generated;

/**
 * Chrome page action icon types. Should be kept in sync with Chromium's PageActionIconType type.
 * <p>Definition generated from cef_types.h
 * <pre>typedef enum {
 *   CEF_CPAIT_BOOKMARK_STAR = 0,
 *   CEF_CPAIT_CLICK_TO_CALL = 1,
 *   CEF_CPAIT_COOKIE_CONTROLS = 2,
 *   CEF_CPAIT_FILE_SYSTEM_ACCESS = 3,
 *   CEF_CPAIT_FIND = 4,
 *   ...
 * } cef_chrome_page_action_icon_type_t;</pre>
 * <p>Possible values: {@link Kind#BOOKMARK_STAR}, {@link Kind#CLICK_TO_CALL}, {@link Kind#COOKIE_CONTROLS}, {@link Kind#FILE_SYSTEM_ACCESS}, {@link Kind#FIND}, {@link Kind#MEMORY_SAVER}, {@link Kind#INTENT_PICKER}, {@link Kind#LOCAL_CARD_MIGRATION}, {@link Kind#MANAGE_PASSWORDS}, {@link Kind#PAYMENTS_OFFER_NOTIFICATION}, {@link Kind#PRICE_TRACKING}, {@link Kind#PWA_INSTALL}, {@link Kind#QR_CODE_GENERATOR_DEPRECATED}, {@link Kind#READER_MODE_DEPRECATED}, {@link Kind#SAVE_AUTOFILL_ADDRESS}, {@link Kind#SAVE_CARD}, {@link Kind#SEND_TAB_TO_SELF_DEPRECATED}, {@link Kind#SHARING_HUB}, {@link Kind#SIDE_SEARCH_DEPRECATED}, {@link Kind#SMS_REMOTE_FETCHER}, {@link Kind#TRANSLATE}, {@link Kind#VIRTUAL_CARD_ENROLL}, {@link Kind#VIRTUAL_CARD_INFORMATION}, {@link Kind#ZOOM}, {@link Kind#SAVE_IBAN}, {@link Kind#MANDATORY_REAUTH}, {@link Kind#PRICE_INSIGHTS}, {@link Kind#READ_ANYTHING_DEPRECATED}, {@link Kind#PRODUCT_SPECIFICATIONS}, {@link Kind#LENS_OVERLAY}, {@link Kind#DISCOUNTS}, {@link Kind#OPTIMIZATION_GUIDE}, {@link Kind#COLLABORATION_MESSAGING}, {@link Kind#CHANGE_PASSWORD}, {@link Kind#LENS_OVERLAY_HOMEWORK}, {@link Kind#AI_MODE}, {@link Kind#READING_MODE}, {@link Kind#CONTEXTUAL_SIDE_PANEL}, {@link Kind#JS_OPTIMIZATIONS}, {@link Kind#RECORD_REPLAY}, {@link Kind#INDIGO}, {@link Kind#FEDERATION}, {@link Kind#GLIC}, {@link Kind#ANCHORED_CONTEXTUAL_CUE}, {@link Kind#WEB_AUTHN_AMBIENT_SIGNIN}, {@link Kind#AUTOFILL_PAYMENT}, {@link Kind#MULTISTEP_FILTER}, {@link Kind#NUM_VALUES}
 *
 * @see <a href="https://cef-builds.spotifycdn.com/docs/150.0/cef__types_8h.html">cef_types.h</a>
 */
@Generated("mvn generate-sources -pl cef4j-platform -Dcef.version=150.0.18+gdb11278+chromium-150.0.7871.213")
@SuppressWarnings({"SameReturnValue", "EmptyMethod", "UnusedReturnValue", "unused", "NullableForbidden"})
public final class CefChromePageActionIconType implements CefEnum<CefChromePageActionIconType> {

    /** Known constants for {@link CefChromePageActionIconType}. */
    public enum Kind {
        BOOKMARK_STAR(0, "0", "CEF_CPAIT_BOOKMARK_STAR"),
        CLICK_TO_CALL(1, "1", "CEF_CPAIT_CLICK_TO_CALL"),
        COOKIE_CONTROLS(2, "2", "CEF_CPAIT_COOKIE_CONTROLS"),
        FILE_SYSTEM_ACCESS(3, "3", "CEF_CPAIT_FILE_SYSTEM_ACCESS"),
        FIND(4, "4", "CEF_CPAIT_FIND"),
        MEMORY_SAVER(5, "5", "CEF_CPAIT_MEMORY_SAVER"),
        INTENT_PICKER(6, "6", "CEF_CPAIT_INTENT_PICKER"),
        LOCAL_CARD_MIGRATION(7, "7", "CEF_CPAIT_LOCAL_CARD_MIGRATION"),
        MANAGE_PASSWORDS(8, "8", "CEF_CPAIT_MANAGE_PASSWORDS"),
        PAYMENTS_OFFER_NOTIFICATION(9, "9", "CEF_CPAIT_PAYMENTS_OFFER_NOTIFICATION"),
        PRICE_TRACKING(10, "10", "CEF_CPAIT_PRICE_TRACKING"),
        PWA_INSTALL(11, "11", "CEF_CPAIT_PWA_INSTALL"),
        QR_CODE_GENERATOR_DEPRECATED(12, "12", "CEF_CPAIT_QR_CODE_GENERATOR_DEPRECATED"),
        READER_MODE_DEPRECATED(13, "13", "CEF_CPAIT_READER_MODE_DEPRECATED"),
        SAVE_AUTOFILL_ADDRESS(14, "14", "CEF_CPAIT_SAVE_AUTOFILL_ADDRESS"),
        SAVE_CARD(15, "15", "CEF_CPAIT_SAVE_CARD"),
        SEND_TAB_TO_SELF_DEPRECATED(16, "16", "CEF_CPAIT_SEND_TAB_TO_SELF_DEPRECATED"),
        SHARING_HUB(17, "17", "CEF_CPAIT_SHARING_HUB"),
        SIDE_SEARCH_DEPRECATED(18, "18", "CEF_CPAIT_SIDE_SEARCH_DEPRECATED"),
        SMS_REMOTE_FETCHER(19, "19", "CEF_CPAIT_SMS_REMOTE_FETCHER"),
        TRANSLATE(20, "20", "CEF_CPAIT_TRANSLATE"),
        VIRTUAL_CARD_ENROLL(21, "21", "CEF_CPAIT_VIRTUAL_CARD_ENROLL"),
        VIRTUAL_CARD_INFORMATION(22, "22", "CEF_CPAIT_VIRTUAL_CARD_INFORMATION"),
        ZOOM(23, "23", "CEF_CPAIT_ZOOM"),
        SAVE_IBAN(24, "24", "CEF_CPAIT_SAVE_IBAN"),
        MANDATORY_REAUTH(25, "25", "CEF_CPAIT_MANDATORY_REAUTH"),
        PRICE_INSIGHTS(26, "26", "CEF_CPAIT_PRICE_INSIGHTS"),
        READ_ANYTHING_DEPRECATED(27, "27", "CEF_CPAIT_READ_ANYTHING_DEPRECATED"),
        PRODUCT_SPECIFICATIONS(28, "28", "CEF_CPAIT_PRODUCT_SPECIFICATIONS"),
        LENS_OVERLAY(29, "29", "CEF_CPAIT_LENS_OVERLAY"),
        DISCOUNTS(30, "30", "CEF_CPAIT_DISCOUNTS"),
        OPTIMIZATION_GUIDE(31, "31", "CEF_CPAIT_OPTIMIZATION_GUIDE"),
        COLLABORATION_MESSAGING(32, "32", "CEF_CPAIT_COLLABORATION_MESSAGING"),
        CHANGE_PASSWORD(33, "33", "CEF_CPAIT_CHANGE_PASSWORD"),
        LENS_OVERLAY_HOMEWORK(34, "34", "CEF_CPAIT_LENS_OVERLAY_HOMEWORK"),
        AI_MODE(35, "35", "CEF_CPAIT_AI_MODE"),
        READING_MODE(36, "36", "CEF_CPAIT_READING_MODE"),
        CONTEXTUAL_SIDE_PANEL(37, "37", "CEF_CPAIT_CONTEXTUAL_SIDE_PANEL"),
        JS_OPTIMIZATIONS(38, "38", "CEF_CPAIT_JS_OPTIMIZATIONS"),
        RECORD_REPLAY(39, "39", "CEF_CPAIT_RECORD_REPLAY"),
        INDIGO(40, "40", "CEF_CPAIT_INDIGO"),
        FEDERATION(41, "41", "CEF_CPAIT_FEDERATION"),
        GLIC(42, "42", "CEF_CPAIT_GLIC"),
        ANCHORED_CONTEXTUAL_CUE(43, "43", "CEF_CPAIT_ANCHORED_CONTEXTUAL_CUE"),
        WEB_AUTHN_AMBIENT_SIGNIN(44, "44", "CEF_CPAIT_WEB_AUTHN_AMBIENT_SIGNIN"),
        AUTOFILL_PAYMENT(45, "45", "CEF_CPAIT_AUTOFILL_PAYMENT"),
        MULTISTEP_FILTER(46, "46", "CEF_CPAIT_MULTISTEP_FILTER"),
        NUM_VALUES(47, "47", "CEF_CPAIT_NUM_VALUES");

        private static final Kind[] VALUES = Kind.values();

        /** The underlying C enum numeric value. */
        public final long value;

        /** The original C expression/literal (e.g. {@code "1 << 3"}), or the numeric string. */
        public final String expr;

        /** The C constant name (e.g., {@code "cef_chrome_page_action_icon_type_t"}). */
        public final String name;

        Kind(long value, String expr, String name) {
            this.value = value;
            this.expr = expr;
            this.name = name;
        }

        @Override
        public String toString() {
            return name + "(expr=" + expr + ", value=" + value + ")";
        }
    }

    /** The underlying C enum numeric value. This may not correspond to any known {@link Kind}. */
    public final long value;

    private CefChromePageActionIconType(long value) {
        this.value = value;
    }

    /** {@inheritDoc} */
    @Override
    public long value() {
        return value;
    }

    /** {@inheritDoc} */
    @Override
    public String expr() {
        return kind().map(k -> k.expr).orElse(String.valueOf(value));
    }

    /** {@inheritDoc} */
    @Override
    public String name() {
        return kind().map(k -> k.name).orElse("UNKNOWN(" + value + ")");
    }

    /**
     * Returns the {@link Kind} matching this value, or empty for unknown/composite values.
     * Use this for exhaustive switch over known constants.
     */
    public java.util.Optional<Kind> kind() {
        for (Kind k : Kind.VALUES) {
            if (k.value == value) return java.util.Optional.of(k);
        }
        return java.util.Optional.empty();
    }

    /** Returns an instance for the given raw value, use {@link #kind} to resolve to a concrete enum. */
    public static CefChromePageActionIconType of(long v) {
        return new CefChromePageActionIconType(v);
    }

    /** Returns an instance for the given known constant. */
    public static CefChromePageActionIconType of(Kind k) {
        return new CefChromePageActionIconType(k.value);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (!(obj instanceof CefChromePageActionIconType)) return false;
        return this.value == ((CefChromePageActionIconType) obj).value;
    }

    @Override
    public int hashCode() {
        return Long.hashCode(value);
    }

    @Override
    public String toString() {
        return kind().map(Kind::toString).orElse("UNKNOWN(value=" + value + ")");
    }
}
