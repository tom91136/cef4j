// GENERATED - do not edit. Regenerate via: ./mvnw generate-sources -pl cef4j-api
// -Dcef.version=150.0.18+gdb11278+chromium-150.0.7871.213
package net.kurobako.cef4j.gen;

import javax.annotation.processing.Generated;
import net.kurobako.cef4j.policy.NullableBoundary;

/**
 * DOM event category flags.
 *
 * <p>Definition generated from cef_types.h
 *
 * <pre>typedef enum {
 *   DOM_EVENT_CATEGORY_UNKNOWN = 0x0,
 *   DOM_EVENT_CATEGORY_UI = 0x1,
 *   DOM_EVENT_CATEGORY_MOUSE = 0x2,
 *   DOM_EVENT_CATEGORY_MUTATION = 0x4,
 *   DOM_EVENT_CATEGORY_KEYBOARD = 0x8,
 *   ...
 * } cef_dom_event_category_t;</pre>
 *
 * <p>Possible values: {@link Kind#UNKNOWN}, {@link Kind#UI}, {@link Kind#MOUSE}, {@link Kind#MUTATION},
 * {@link Kind#KEYBOARD}, {@link Kind#TEXT}, {@link Kind#COMPOSITION}, {@link Kind#DRAG}, {@link Kind#CLIPBOARD},
 * {@link Kind#MESSAGE}, {@link Kind#WHEEL}, {@link Kind#BEFORE_TEXT_INSERTED}, {@link Kind#OVERFLOW},
 * {@link Kind#PAGE_TRANSITION}, {@link Kind#POPSTATE}, {@link Kind#PROGRESS}, {@link Kind#XMLHTTPREQUEST_PROGRESS}
 *
 * @see <a href="https://cef-builds.spotifycdn.com/docs/150.0/cef__types_8h.html">cef_types.h</a>
 */
@Generated("./mvnw generate-sources -pl cef4j-api -Dcef.version=150.0.18+gdb11278+chromium-150.0.7871.213")
@SuppressWarnings({"SameReturnValue", "EmptyMethod", "UnusedReturnValue", "unused"})
@NullableBoundary("generated external ABI")
public final class CefDomEventCategory implements CefEnum<CefDomEventCategory> {

    /** Known constants for {@link CefDomEventCategory}. */
    public enum Kind {
        UNKNOWN(0x0, "0x0", "DOM_EVENT_CATEGORY_UNKNOWN"),
        UI(0x1, "0x1", "DOM_EVENT_CATEGORY_UI"),
        MOUSE(0x2, "0x2", "DOM_EVENT_CATEGORY_MOUSE"),
        MUTATION(0x4, "0x4", "DOM_EVENT_CATEGORY_MUTATION"),
        KEYBOARD(0x8, "0x8", "DOM_EVENT_CATEGORY_KEYBOARD"),
        TEXT(0x10, "0x10", "DOM_EVENT_CATEGORY_TEXT"),
        COMPOSITION(0x20, "0x20", "DOM_EVENT_CATEGORY_COMPOSITION"),
        DRAG(0x40, "0x40", "DOM_EVENT_CATEGORY_DRAG"),
        CLIPBOARD(0x80, "0x80", "DOM_EVENT_CATEGORY_CLIPBOARD"),
        MESSAGE(0x100, "0x100", "DOM_EVENT_CATEGORY_MESSAGE"),
        WHEEL(0x200, "0x200", "DOM_EVENT_CATEGORY_WHEEL"),
        BEFORE_TEXT_INSERTED(0x400, "0x400", "DOM_EVENT_CATEGORY_BEFORE_TEXT_INSERTED"),
        OVERFLOW(0x800, "0x800", "DOM_EVENT_CATEGORY_OVERFLOW"),
        PAGE_TRANSITION(0x1000, "0x1000", "DOM_EVENT_CATEGORY_PAGE_TRANSITION"),
        POPSTATE(0x2000, "0x2000", "DOM_EVENT_CATEGORY_POPSTATE"),
        PROGRESS(0x4000, "0x4000", "DOM_EVENT_CATEGORY_PROGRESS"),
        XMLHTTPREQUEST_PROGRESS(0x8000, "0x8000", "DOM_EVENT_CATEGORY_XMLHTTPREQUEST_PROGRESS");

        private static final Kind[] VALUES = Kind.values();

        /** The underlying C enum numeric value. */
        public final long value;

        /** The original C expression/literal (e.g. {@code "1 << 3"}), or the numeric string. */
        public final String expr;

        /** The C constant name (e.g., {@code "cef_dom_event_category_t"}). */
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

    private CefDomEventCategory(long value) {
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
     * Returns the {@link Kind} matching this value, or empty for unknown/composite values. Use this for exhaustive
     * switch over known constants.
     */
    public java.util.Optional<Kind> kind() {
        for (Kind k : Kind.VALUES) {
            if (k.value == value) return java.util.Optional.of(k);
        }
        return java.util.Optional.empty();
    }

    /** Returns an instance for the given raw value, use {@link #kind} to resolve to a concrete enum. */
    public static CefDomEventCategory of(long v) {
        return new CefDomEventCategory(v);
    }

    /** Returns an instance for the given known constant. */
    public static CefDomEventCategory of(Kind k) {
        return new CefDomEventCategory(k.value);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (!(obj instanceof CefDomEventCategory)) return false;
        return this.value == ((CefDomEventCategory) obj).value;
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
