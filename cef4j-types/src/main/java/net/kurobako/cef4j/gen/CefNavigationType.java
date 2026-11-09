// GENERATED - do not edit. Regenerate via: ./mvnw generate-sources -pl cef4j-types
// -Dcef.version=150.0.18+gdb11278+chromium-150.0.7871.213
package net.kurobako.cef4j.gen;

import javax.annotation.processing.Generated;
import net.kurobako.cef4j.policy.NullableBoundary;

/**
 * Navigation types.
 *
 * <p>Definition generated from cef_types.h
 *
 * <pre>typedef enum {
 *   NAVIGATION_LINK_CLICKED = 0,
 *   NAVIGATION_FORM_SUBMITTED = 1,
 *   NAVIGATION_BACK_FORWARD = 2,
 *   NAVIGATION_RELOAD = 3,
 *   NAVIGATION_FORM_RESUBMITTED = 4,
 *   ...
 * } cef_navigation_type_t;</pre>
 *
 * <p>Possible values: {@link Kind#LINK_CLICKED}, {@link Kind#FORM_SUBMITTED}, {@link Kind#BACK_FORWARD},
 * {@link Kind#RELOAD}, {@link Kind#FORM_RESUBMITTED}, {@link Kind#OTHER}, {@link Kind#NUM_VALUES}
 *
 * @see <a href="https://cef-builds.spotifycdn.com/docs/150.0/cef__types_8h.html">cef_types.h</a>
 */
@Generated("./mvnw generate-sources -pl cef4j-types -Dcef.version=150.0.18+gdb11278+chromium-150.0.7871.213")
@NullableBoundary("generated external ABI")
public final class CefNavigationType implements CefEnum<CefNavigationType> {

    /** Known constants for {@link CefNavigationType}. */
    public enum Kind {
        LINK_CLICKED(new EnumConstant(0, "0", "NAVIGATION_LINK_CLICKED")),
        FORM_SUBMITTED(new EnumConstant(1, "1", "NAVIGATION_FORM_SUBMITTED")),
        BACK_FORWARD(new EnumConstant(2, "2", "NAVIGATION_BACK_FORWARD")),
        RELOAD(new EnumConstant(3, "3", "NAVIGATION_RELOAD")),
        FORM_RESUBMITTED(new EnumConstant(4, "4", "NAVIGATION_FORM_RESUBMITTED")),
        OTHER(new EnumConstant(5, "5", "NAVIGATION_OTHER")),
        NUM_VALUES(new EnumConstant(6, "6", "NAVIGATION_NUM_VALUES"));

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

        /** The C constant name (e.g., {@code "cef_navigation_type_t"}). */
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

    private CefNavigationType(long value) {
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
    public static CefNavigationType of(long v) {
        return new CefNavigationType(v);
    }

    /** Returns an instance for the given known constant. */
    public static CefNavigationType of(Kind k) {
        return new CefNavigationType(k.value());
    }

    @Override
    public boolean equals(@org.jspecify.annotations.Nullable Object obj) {
        if (this == obj) return true;
        if (!(obj instanceof CefNavigationType)) return false;
        return this.value == ((CefNavigationType) obj).value;
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
