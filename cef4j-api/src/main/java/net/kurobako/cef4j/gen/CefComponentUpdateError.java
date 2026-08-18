// GENERATED - do not edit. Regenerate via: mvn generate-sources -pl cef4j-platform -Dcef.version=150.0.18+gdb11278+chromium-150.0.7871.213
package net.kurobako.cef4j.gen;

import javax.annotation.processing.Generated;

/**
 * <p>Possible values: {@link Kind#NONE}, {@link Kind#UPDATE_IN_PROGRESS}, {@link Kind#UPDATE_CANCELED}, {@link Kind#RETRY_LATER}, {@link Kind#SERVICE_ERROR}, {@link Kind#UPDATE_CHECK_ERROR}, {@link Kind#CRX_NOT_FOUND}, {@link Kind#INVALID_ARGUMENT}, {@link Kind#BAD_CRX_DATA_CALLBACK}
 */
@Generated("mvn generate-sources -pl cef4j-platform -Dcef.version=150.0.18+gdb11278+chromium-150.0.7871.213")
@SuppressWarnings({"SameReturnValue", "EmptyMethod", "UnusedReturnValue", "unused", "NullableForbidden"})
public final class CefComponentUpdateError implements CefEnum<CefComponentUpdateError> {

    /** Known constants for {@link CefComponentUpdateError}. */
    public enum Kind {
        NONE(0, "0", "CEF_COMPONENT_UPDATE_ERROR_NONE"),
        UPDATE_IN_PROGRESS(1, "1", "CEF_COMPONENT_UPDATE_ERROR_UPDATE_IN_PROGRESS"),
        UPDATE_CANCELED(2, "2", "CEF_COMPONENT_UPDATE_ERROR_UPDATE_CANCELED"),
        RETRY_LATER(3, "3", "CEF_COMPONENT_UPDATE_ERROR_RETRY_LATER"),
        SERVICE_ERROR(4, "4", "CEF_COMPONENT_UPDATE_ERROR_SERVICE_ERROR"),
        UPDATE_CHECK_ERROR(5, "5", "CEF_COMPONENT_UPDATE_ERROR_UPDATE_CHECK_ERROR"),
        CRX_NOT_FOUND(6, "6", "CEF_COMPONENT_UPDATE_ERROR_CRX_NOT_FOUND"),
        INVALID_ARGUMENT(7, "7", "CEF_COMPONENT_UPDATE_ERROR_INVALID_ARGUMENT"),
        BAD_CRX_DATA_CALLBACK(8, "8", "CEF_COMPONENT_UPDATE_ERROR_BAD_CRX_DATA_CALLBACK");

        private static final Kind[] VALUES = Kind.values();

        /** The underlying C enum numeric value. */
        public final long value;

        /** The original C expression/literal (e.g. {@code "1 << 3"}), or the numeric string. */
        public final String expr;

        /** The C constant name (e.g., {@code "cef_component_update_error_t"}). */
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

    private CefComponentUpdateError(long value) {
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
    public static CefComponentUpdateError of(long v) {
        return new CefComponentUpdateError(v);
    }

    /** Returns an instance for the given known constant. */
    public static CefComponentUpdateError of(Kind k) {
        return new CefComponentUpdateError(k.value);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (!(obj instanceof CefComponentUpdateError)) return false;
        return this.value == ((CefComponentUpdateError) obj).value;
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
