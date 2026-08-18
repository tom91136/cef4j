// GENERATED - do not edit. Regenerate via: mvn generate-sources -pl cef4j-platform -Dcef.version=150.0.18+gdb11278+chromium-150.0.7871.213
package net.kurobako.cef4j.gen;

import javax.annotation.processing.Generated;

/**
 * <p>Possible values: {@link Kind#NEW}, {@link Kind#CHECKING}, {@link Kind#CAN_UPDATE}, {@link Kind#DOWNLOADING}, {@link Kind#DECOMPRESSING}, {@link Kind#PATCHING}, {@link Kind#UPDATING}, {@link Kind#UPDATED}, {@link Kind#UP_TO_DATE}, {@link Kind#UPDATE_ERROR}, {@link Kind#RUN}
 */
@Generated("mvn generate-sources -pl cef4j-platform -Dcef.version=150.0.18+gdb11278+chromium-150.0.7871.213")
@SuppressWarnings({"SameReturnValue", "EmptyMethod", "UnusedReturnValue", "unused", "NullableForbidden"})
public final class CefComponentState implements CefEnum<CefComponentState> {

    /** Known constants for {@link CefComponentState}. */
    public enum Kind {
        NEW(0, "0", "CEF_COMPONENT_STATE_NEW"),
        CHECKING(1, "1", "CEF_COMPONENT_STATE_CHECKING"),
        CAN_UPDATE(2, "2", "CEF_COMPONENT_STATE_CAN_UPDATE"),
        DOWNLOADING(3, "3", "CEF_COMPONENT_STATE_DOWNLOADING"),
        DECOMPRESSING(4, "4", "CEF_COMPONENT_STATE_DECOMPRESSING"),
        PATCHING(5, "5", "CEF_COMPONENT_STATE_PATCHING"),
        UPDATING(6, "6", "CEF_COMPONENT_STATE_UPDATING"),
        UPDATED(7, "7", "CEF_COMPONENT_STATE_UPDATED"),
        UP_TO_DATE(8, "8", "CEF_COMPONENT_STATE_UP_TO_DATE"),
        UPDATE_ERROR(9, "9", "CEF_COMPONENT_STATE_UPDATE_ERROR"),
        RUN(10, "10", "CEF_COMPONENT_STATE_RUN");

        private static final Kind[] VALUES = Kind.values();

        /** The underlying C enum numeric value. */
        public final long value;

        /** The original C expression/literal (e.g. {@code "1 << 3"}), or the numeric string. */
        public final String expr;

        /** The C constant name (e.g., {@code "cef_component_state_t"}). */
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

    private CefComponentState(long value) {
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
    public static CefComponentState of(long v) {
        return new CefComponentState(v);
    }

    /** Returns an instance for the given known constant. */
    public static CefComponentState of(Kind k) {
        return new CefComponentState(k.value);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (!(obj instanceof CefComponentState)) return false;
        return this.value == ((CefComponentState) obj).value;
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
