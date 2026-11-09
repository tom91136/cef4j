// GENERATED - do not edit. Regenerate via: ./mvnw generate-sources -pl cef4j-types
// -Dcef.version=150.0.18+gdb11278+chromium-150.0.7871.213
package net.kurobako.cef4j.gen;

import javax.annotation.processing.Generated;
import net.kurobako.cef4j.policy.NullableBoundary;

/**
 * Possible values: {@link Kind#DEFAULT}, {@link Kind#ALLOW}, {@link Kind#BLOCK}, {@link Kind#ASK},
 * {@link Kind#SESSION_ONLY}, {@link Kind#DETECT_IMPORTANT_CONTENT_DEPRECATED}, {@link Kind#NUM_VALUES}
 */
@Generated("./mvnw generate-sources -pl cef4j-types -Dcef.version=150.0.18+gdb11278+chromium-150.0.7871.213")
@NullableBoundary("generated external ABI")
public final class CefContentSettingValues implements CefEnum<CefContentSettingValues> {

    /** Known constants for {@link CefContentSettingValues}. */
    public enum Kind {
        DEFAULT(new EnumConstant(0, "0", "CEF_CONTENT_SETTING_VALUE_DEFAULT")),
        ALLOW(new EnumConstant(1, "1", "CEF_CONTENT_SETTING_VALUE_ALLOW")),
        BLOCK(new EnumConstant(2, "2", "CEF_CONTENT_SETTING_VALUE_BLOCK")),
        ASK(new EnumConstant(3, "3", "CEF_CONTENT_SETTING_VALUE_ASK")),
        SESSION_ONLY(new EnumConstant(4, "4", "CEF_CONTENT_SETTING_VALUE_SESSION_ONLY")),
        DETECT_IMPORTANT_CONTENT_DEPRECATED(
                new EnumConstant(5, "5", "CEF_CONTENT_SETTING_VALUE_DETECT_IMPORTANT_CONTENT_DEPRECATED")),
        NUM_VALUES(new EnumConstant(6, "6", "CEF_CONTENT_SETTING_VALUE_NUM_VALUES"));

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

        /** The C constant name (e.g., {@code "cef_content_setting_values_t"}). */
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

    private CefContentSettingValues(long value) {
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
    public static CefContentSettingValues of(long v) {
        return new CefContentSettingValues(v);
    }

    /** Returns an instance for the given known constant. */
    public static CefContentSettingValues of(Kind k) {
        return new CefContentSettingValues(k.value());
    }

    @Override
    public boolean equals(@org.jspecify.annotations.Nullable Object obj) {
        if (this == obj) return true;
        if (!(obj instanceof CefContentSettingValues)) return false;
        return this.value == ((CefContentSettingValues) obj).value;
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
