// GENERATED - do not edit. Regenerate via: ./mvnw generate-sources -pl cef4j-types
// -Dcef.version=150.0.18+gdb11278+chromium-150.0.7871.213
package net.kurobako.cef4j.gen;

import javax.annotation.processing.Generated;
import net.kurobako.cef4j.policy.NullableBoundary;

/**
 * Docking modes supported by net.kurobako.cef4j.gen.views.CefWindow.addOverlay().
 *
 * <p>Definition generated from cef_types.h
 *
 * <pre>typedef enum {
 *   CEF_DOCKING_MODE_TOP_LEFT = 0,
 *   CEF_DOCKING_MODE_TOP_RIGHT = 1,
 *   CEF_DOCKING_MODE_BOTTOM_LEFT = 2,
 *   CEF_DOCKING_MODE_BOTTOM_RIGHT = 3,
 *   CEF_DOCKING_MODE_CUSTOM = 4,
 *   ...
 * } cef_docking_mode_t;</pre>
 *
 * <p>Possible values: {@link Kind#TOP_LEFT}, {@link Kind#TOP_RIGHT}, {@link Kind#BOTTOM_LEFT},
 * {@link Kind#BOTTOM_RIGHT}, {@link Kind#CUSTOM}, {@link Kind#NUM_VALUES}
 *
 * @see <a href="https://cef-builds.spotifycdn.com/docs/150.0/cef__types_8h.html">cef_types.h</a>
 */
@Generated("./mvnw generate-sources -pl cef4j-types -Dcef.version=150.0.18+gdb11278+chromium-150.0.7871.213")
@NullableBoundary("generated external ABI")
public final class CefDockingMode implements CefEnum<CefDockingMode> {

    /** Known constants for {@link CefDockingMode}. */
    public enum Kind {
        TOP_LEFT(new EnumConstant(0, "0", "CEF_DOCKING_MODE_TOP_LEFT")),
        TOP_RIGHT(new EnumConstant(1, "1", "CEF_DOCKING_MODE_TOP_RIGHT")),
        BOTTOM_LEFT(new EnumConstant(2, "2", "CEF_DOCKING_MODE_BOTTOM_LEFT")),
        BOTTOM_RIGHT(new EnumConstant(3, "3", "CEF_DOCKING_MODE_BOTTOM_RIGHT")),
        CUSTOM(new EnumConstant(4, "4", "CEF_DOCKING_MODE_CUSTOM")),
        NUM_VALUES(new EnumConstant(5, "5", "CEF_DOCKING_MODE_NUM_VALUES"));

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

        /** The C constant name (e.g., {@code "cef_docking_mode_t"}). */
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

    private CefDockingMode(long value) {
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
    public static CefDockingMode of(long v) {
        return new CefDockingMode(v);
    }

    /** Returns an instance for the given known constant. */
    public static CefDockingMode of(Kind k) {
        return new CefDockingMode(k.value());
    }

    @Override
    public boolean equals(@org.jspecify.annotations.Nullable Object obj) {
        if (this == obj) return true;
        if (!(obj instanceof CefDockingMode)) return false;
        return this.value == ((CefDockingMode) obj).value;
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
