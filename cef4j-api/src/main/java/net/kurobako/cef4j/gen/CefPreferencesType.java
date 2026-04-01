// GENERATED - do not edit. Regenerate via: mvn generate-sources -pl cef4j-native
package net.kurobako.cef4j.gen;

/**
 * Preferences type passed to {@link CefBrowserProcessHandler#onRegisterCustomPreferences(CefPreferencesType,
 * CefPreferenceRegistrar)}.
 *
 * <p>Definition generated from cef_types.h
 *
 * <pre>typedef enum {
 *   CEF_PREFERENCES_TYPE_GLOBAL = 0,
 *   CEF_PREFERENCES_TYPE_REQUEST_CONTEXT = 1,
 *   CEF_PREFERENCES_TYPE_NUM_VALUES = 2
 * } cef_preferences_type_t;</pre>
 *
 * <p>Possible values: {@link Kind#GLOBAL}, {@link Kind#REQUEST_CONTEXT}, {@link Kind#NUM_VALUES}
 *
 * @see <a href="https://cef-builds.spotifycdn.com/docs/146.0/cef__types_8h.html">cef_types.h</a>
 */
public final class CefPreferencesType implements CefEnum<CefPreferencesType> {

    /** Known constants for {@link CefPreferencesType}. */
    public enum Kind {
        /** Global preferences registered a single time at application startup. */
        GLOBAL(0, "0", "CEF_PREFERENCES_TYPE_GLOBAL"),
        /** Request context preferences registered each time a new CefRequestContext is created. */
        REQUEST_CONTEXT(1, "1", "CEF_PREFERENCES_TYPE_REQUEST_CONTEXT"),
        NUM_VALUES(2, "2", "CEF_PREFERENCES_TYPE_NUM_VALUES");

        private static final Kind[] VALUES = Kind.values();

        /** The underlying C enum numeric value. */
        public final long value;

        /** The original C expression (e.g., {@code "1 << 3"}), or the numeric string. */
        public final String expr;

        /** The C constant name (e.g., {@code "cef_preferences_type_t"}). */
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

    /** The underlying C enum numeric value. May not correspond to any known {@link Kind}. */
    public final long value;

    private CefPreferencesType(long value) {
        this.value = value;
    }

    @Override
    public long value() {
        return value;
    }

    @Override
    public String expr() {
        return kind().map(k -> k.expr).orElse(String.valueOf(value));
    }

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

    /** Returns an instance for the given raw value. No data is lost — unknown or composite values are preserved. */
    public static CefPreferencesType of(long v) {
        return new CefPreferencesType(v);
    }

    /** Returns an instance for the given known constant. */
    public static CefPreferencesType of(Kind k) {
        return new CefPreferencesType(k.value);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (!(obj instanceof CefPreferencesType)) return false;
        return this.value == ((CefPreferencesType) obj).value;
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
