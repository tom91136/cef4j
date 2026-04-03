// GENERATED - do not edit. Regenerate via: mvn generate-sources -pl cef4j-native
// -Dcef.version=146.0.9+g3ca6a87+chromium-146.0.7680.165
package net.kurobako.cef4j.gen;

import javax.annotation.processing.Generated;

/**
 * Possible values: {@link Kind#DEFAULT}, {@link Kind#ALLOW}, {@link Kind#BLOCK}, {@link Kind#ASK},
 * {@link Kind#SESSION_ONLY}, {@link Kind#DETECT_IMPORTANT_CONTENT_DEPRECATED}, {@link Kind#NUM_VALUES}
 */
@Generated("mvn generate-sources -pl cef4j-native -Dcef.version=146.0.9+g3ca6a87+chromium-146.0.7680.165")
public final class CefContentSettingValues implements CefEnum<CefContentSettingValues> {

    /** Known constants for {@link CefContentSettingValues}. */
    public enum Kind {
        DEFAULT(0, "0", "CEF_CONTENT_SETTING_VALUE_DEFAULT"),
        ALLOW(1, "1", "CEF_CONTENT_SETTING_VALUE_ALLOW"),
        BLOCK(2, "2", "CEF_CONTENT_SETTING_VALUE_BLOCK"),
        ASK(3, "3", "CEF_CONTENT_SETTING_VALUE_ASK"),
        SESSION_ONLY(4, "4", "CEF_CONTENT_SETTING_VALUE_SESSION_ONLY"),
        DETECT_IMPORTANT_CONTENT_DEPRECATED(5, "5", "CEF_CONTENT_SETTING_VALUE_DETECT_IMPORTANT_CONTENT_DEPRECATED"),
        NUM_VALUES(6, "6", "CEF_CONTENT_SETTING_VALUE_NUM_VALUES");

        private static final Kind[] VALUES = Kind.values();

        /** The underlying C enum numeric value. */
        public final long value;

        /** The original C expression/literal (e.g. {@code "1 << 3"}), or the numeric string. */
        public final String expr;

        /** The C constant name (e.g., {@code "cef_content_setting_values_t"}). */
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
    public static CefContentSettingValues of(long v) {
        return new CefContentSettingValues(v);
    }

    /** Returns an instance for the given known constant. */
    public static CefContentSettingValues of(Kind k) {
        return new CefContentSettingValues(k.value);
    }

    @Override
    public boolean equals(Object obj) {
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
