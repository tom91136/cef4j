// GENERATED - do not edit. Regenerate via: ./mvnw generate-sources -pl cef4j-types
// -Dcef.version=150.0.18+gdb11278+chromium-150.0.7871.213
package net.kurobako.cef4j.gen;

import javax.annotation.processing.Generated;
import net.kurobako.cef4j.policy.NullableBoundary;

/**
 * Supported menu IDs. Non-English translations can be provided for the IDS_MENU_* strings in
 * {@link net.kurobako.cef4j.gen.CefResourceBundleHandler#getLocalizedString(int, String)}.
 *
 * <p>Definition generated from cef_types.h
 *
 * <pre>typedef enum {
 *   MENU_ID_BACK = 100,
 *   MENU_ID_FORWARD = 101,
 *   MENU_ID_RELOAD = 102,
 *   MENU_ID_RELOAD_NOCACHE = 103,
 *   MENU_ID_STOPLOAD = 104,
 *   ...
 * } cef_menu_id_t;</pre>
 *
 * <p>Possible values: {@link Kind#BACK}, {@link Kind#FORWARD}, {@link Kind#RELOAD}, {@link Kind#RELOAD_NOCACHE},
 * {@link Kind#STOPLOAD}, {@link Kind#UNDO}, {@link Kind#REDO}, {@link Kind#CUT}, {@link Kind#COPY}, {@link Kind#PASTE},
 * {@link Kind#PASTE_MATCH_STYLE}, {@link Kind#DELETE}, {@link Kind#SELECT_ALL}, {@link Kind#FIND}, {@link Kind#PRINT},
 * {@link Kind#VIEW_SOURCE}, {@link Kind#SPELLCHECK_SUGGESTION_0}, {@link Kind#SPELLCHECK_SUGGESTION_1},
 * {@link Kind#SPELLCHECK_SUGGESTION_2}, {@link Kind#SPELLCHECK_SUGGESTION_3}, {@link Kind#SPELLCHECK_SUGGESTION_4},
 * {@link Kind#SPELLCHECK_SUGGESTION_LAST}, {@link Kind#NO_SPELLING_SUGGESTIONS}, {@link Kind#ADD_TO_DICTIONARY},
 * {@link Kind#CUSTOM_FIRST}, {@link Kind#CUSTOM_LAST}, {@link Kind#USER_FIRST}, {@link Kind#USER_LAST}
 *
 * @see <a href="https://cef-builds.spotifycdn.com/docs/150.0/cef__types_8h.html">cef_types.h</a>
 */
@Generated("./mvnw generate-sources -pl cef4j-types -Dcef.version=150.0.18+gdb11278+chromium-150.0.7871.213")
@NullableBoundary("generated external ABI")
public final class CefMenuId implements CefEnum<CefMenuId> {

    /** Known constants for {@link CefMenuId}. */
    public enum Kind {
        BACK(new EnumConstant(100, "100", "MENU_ID_BACK")),
        FORWARD(new EnumConstant(101, "101", "MENU_ID_FORWARD")),
        RELOAD(new EnumConstant(102, "102", "MENU_ID_RELOAD")),
        RELOAD_NOCACHE(new EnumConstant(103, "103", "MENU_ID_RELOAD_NOCACHE")),
        STOPLOAD(new EnumConstant(104, "104", "MENU_ID_STOPLOAD")),
        UNDO(new EnumConstant(110, "110", "MENU_ID_UNDO")),
        REDO(new EnumConstant(111, "111", "MENU_ID_REDO")),
        CUT(new EnumConstant(112, "112", "MENU_ID_CUT")),
        COPY(new EnumConstant(113, "113", "MENU_ID_COPY")),
        PASTE(new EnumConstant(114, "114", "MENU_ID_PASTE")),
        PASTE_MATCH_STYLE(new EnumConstant(115, "115", "MENU_ID_PASTE_MATCH_STYLE")),
        DELETE(new EnumConstant(116, "116", "MENU_ID_DELETE")),
        SELECT_ALL(new EnumConstant(117, "117", "MENU_ID_SELECT_ALL")),
        FIND(new EnumConstant(130, "130", "MENU_ID_FIND")),
        PRINT(new EnumConstant(131, "131", "MENU_ID_PRINT")),
        VIEW_SOURCE(new EnumConstant(132, "132", "MENU_ID_VIEW_SOURCE")),
        SPELLCHECK_SUGGESTION_0(new EnumConstant(200, "200", "MENU_ID_SPELLCHECK_SUGGESTION_0")),
        SPELLCHECK_SUGGESTION_1(new EnumConstant(201, "201", "MENU_ID_SPELLCHECK_SUGGESTION_1")),
        SPELLCHECK_SUGGESTION_2(new EnumConstant(202, "202", "MENU_ID_SPELLCHECK_SUGGESTION_2")),
        SPELLCHECK_SUGGESTION_3(new EnumConstant(203, "203", "MENU_ID_SPELLCHECK_SUGGESTION_3")),
        SPELLCHECK_SUGGESTION_4(new EnumConstant(204, "204", "MENU_ID_SPELLCHECK_SUGGESTION_4")),
        SPELLCHECK_SUGGESTION_LAST(new EnumConstant(204, "204", "MENU_ID_SPELLCHECK_SUGGESTION_LAST")),
        NO_SPELLING_SUGGESTIONS(new EnumConstant(205, "205", "MENU_ID_NO_SPELLING_SUGGESTIONS")),
        ADD_TO_DICTIONARY(new EnumConstant(206, "206", "MENU_ID_ADD_TO_DICTIONARY")),
        CUSTOM_FIRST(new EnumConstant(220, "220", "MENU_ID_CUSTOM_FIRST")),
        CUSTOM_LAST(new EnumConstant(250, "250", "MENU_ID_CUSTOM_LAST")),
        USER_FIRST(new EnumConstant(26500, "26500", "MENU_ID_USER_FIRST")),
        USER_LAST(new EnumConstant(28500, "28500", "MENU_ID_USER_LAST"));

        private static final Kind[] VALUES = Kind.values();

        private final EnumConstant constant;

        Kind(EnumConstant constant) {
            this.constant = constant;
        }

        /** The underlying C enum numeric value. */
        public long value() {
            return constant.value();
        }

        /** The original C expression/literal (e.g. {@code "1 << 3"}), or the numeric string. */
        public String expr() {
            return constant.expr();
        }

        /** The C constant name (e.g., {@code "cef_menu_id_t"}). */
        public String cName() {
            return constant.name();
        }

        @Override
        public String toString() {
            return cName() + "(expr=" + expr() + ", value=" + value() + ")";
        }
    }

    /** The underlying C enum numeric value. This may not correspond to any known {@link Kind}. */
    public final long value;

    private CefMenuId(long value) {
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
        return kind().map(Kind::expr).orElse(String.valueOf(value));
    }

    /** {@inheritDoc} */
    @Override
    public String name() {
        return kind().map(Kind::cName).orElse("UNKNOWN(" + value + ")");
    }

    /**
     * Returns the {@link Kind} matching this value, or empty for unknown/composite values. Use this for exhaustive
     * switch over known constants.
     */
    public java.util.Optional<Kind> kind() {
        for (Kind k : Kind.VALUES) {
            if (k.value() == value) return java.util.Optional.of(k);
        }
        return java.util.Optional.empty();
    }

    /** Returns an instance for the given raw value, use {@link #kind} to resolve to a concrete enum. */
    public static CefMenuId of(long v) {
        return new CefMenuId(v);
    }

    /** Returns an instance for the given known constant. */
    public static CefMenuId of(Kind k) {
        return new CefMenuId(k.value());
    }

    @Override
    public boolean equals(@org.jspecify.annotations.Nullable Object obj) {
        if (this == obj) return true;
        if (!(obj instanceof CefMenuId)) return false;
        return this.value == ((CefMenuId) obj).value;
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
