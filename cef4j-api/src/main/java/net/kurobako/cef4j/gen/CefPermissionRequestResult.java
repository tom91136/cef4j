// GENERATED - do not edit. Regenerate via: mvn generate-sources -pl cef4j-native
// -Dcef.version=146.0.9+g3ca6a87+chromium-146.0.7680.165
package net.kurobako.cef4j.gen;

import javax.annotation.processing.Generated;

/**
 * Permission request results.
 *
 * <p>Definition generated from cef_types.h
 *
 * <pre>typedef enum {
 *   CEF_PERMISSION_RESULT_ACCEPT = 0,
 *   CEF_PERMISSION_RESULT_DENY = 1,
 *   CEF_PERMISSION_RESULT_DISMISS = 2,
 *   CEF_PERMISSION_RESULT_IGNORE = 3,
 *   CEF_PERMISSION_RESULT_NUM_VALUES = 4
 * } cef_permission_request_result_t;</pre>
 *
 * <p>Possible values: {@link Kind#ACCEPT}, {@link Kind#DENY}, {@link Kind#DISMISS}, {@link Kind#IGNORE},
 * {@link Kind#NUM_VALUES}
 *
 * @see <a href="https://cef-builds.spotifycdn.com/docs/146.0/cef__types_8h.html">cef_types.h</a>
 */
@Generated("mvn generate-sources -pl cef4j-native -Dcef.version=146.0.9+g3ca6a87+chromium-146.0.7680.165")
public final class CefPermissionRequestResult implements CefEnum<CefPermissionRequestResult> {

    /** Known constants for {@link CefPermissionRequestResult}. */
    public enum Kind {
        /** Accept the permission request as an explicit user action. */
        ACCEPT(0, "0", "CEF_PERMISSION_RESULT_ACCEPT"),
        /** Deny the permission request as an explicit user action. */
        DENY(1, "1", "CEF_PERMISSION_RESULT_DENY"),
        /** Dismiss the permission request as an explicit user action. */
        DISMISS(2, "2", "CEF_PERMISSION_RESULT_DISMISS"),
        /**
         * Ignore the permission request. If the prompt remains unhandled (e.g. OnShowPermissionPrompt returns
         * {@code false} and there is no default permissions UI) then any related promises may remain unresolved.
         */
        IGNORE(3, "3", "CEF_PERMISSION_RESULT_IGNORE"),
        NUM_VALUES(4, "4", "CEF_PERMISSION_RESULT_NUM_VALUES");

        private static final Kind[] VALUES = Kind.values();

        /** The underlying C enum numeric value. */
        public final long value;

        /** The original C expression/literal (e.g. {@code "1 << 3"}), or the numeric string. */
        public final String expr;

        /** The C constant name (e.g., {@code "cef_permission_request_result_t"}). */
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

    private CefPermissionRequestResult(long value) {
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
    public static CefPermissionRequestResult of(long v) {
        return new CefPermissionRequestResult(v);
    }

    /** Returns an instance for the given known constant. */
    public static CefPermissionRequestResult of(Kind k) {
        return new CefPermissionRequestResult(k.value);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (!(obj instanceof CefPermissionRequestResult)) return false;
        return this.value == ((CefPermissionRequestResult) obj).value;
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
