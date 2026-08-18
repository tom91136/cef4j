// GENERATED - do not edit. Regenerate via: mvn generate-sources -pl cef4j-platform -Dcef.version=150.0.18+gdb11278+chromium-150.0.7871.213
package net.kurobako.cef4j.gen;

import javax.annotation.processing.Generated;

/**
 * DOM event processing phases.
 * <p>Definition generated from cef_types.h
 * <pre>typedef enum {
 *   DOM_EVENT_PHASE_UNKNOWN = 0,
 *   DOM_EVENT_PHASE_CAPTURING = 1,
 *   DOM_EVENT_PHASE_AT_TARGET = 2,
 *   DOM_EVENT_PHASE_BUBBLING = 3,
 *   DOM_EVENT_PHASE_NUM_VALUES = 4
 * } cef_dom_event_phase_t;</pre>
 * <p>Possible values: {@link Kind#UNKNOWN}, {@link Kind#CAPTURING}, {@link Kind#AT_TARGET}, {@link Kind#BUBBLING}, {@link Kind#NUM_VALUES}
 *
 * @see <a href="https://cef-builds.spotifycdn.com/docs/150.0/cef__types_8h.html">cef_types.h</a>
 */
@Generated("mvn generate-sources -pl cef4j-platform -Dcef.version=150.0.18+gdb11278+chromium-150.0.7871.213")
@SuppressWarnings({"SameReturnValue", "EmptyMethod", "UnusedReturnValue", "unused", "NullableForbidden"})
public final class CefDomEventPhase implements CefEnum<CefDomEventPhase> {

    /** Known constants for {@link CefDomEventPhase}. */
    public enum Kind {
        UNKNOWN(0, "0", "DOM_EVENT_PHASE_UNKNOWN"),
        CAPTURING(1, "1", "DOM_EVENT_PHASE_CAPTURING"),
        AT_TARGET(2, "2", "DOM_EVENT_PHASE_AT_TARGET"),
        BUBBLING(3, "3", "DOM_EVENT_PHASE_BUBBLING"),
        NUM_VALUES(4, "4", "DOM_EVENT_PHASE_NUM_VALUES");

        private static final Kind[] VALUES = Kind.values();

        /** The underlying C enum numeric value. */
        public final long value;

        /** The original C expression/literal (e.g. {@code "1 << 3"}), or the numeric string. */
        public final String expr;

        /** The C constant name (e.g., {@code "cef_dom_event_phase_t"}). */
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

    private CefDomEventPhase(long value) {
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
    public static CefDomEventPhase of(long v) {
        return new CefDomEventPhase(v);
    }

    /** Returns an instance for the given known constant. */
    public static CefDomEventPhase of(Kind k) {
        return new CefDomEventPhase(k.value);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (!(obj instanceof CefDomEventPhase)) return false;
        return this.value == ((CefDomEventPhase) obj).value;
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
