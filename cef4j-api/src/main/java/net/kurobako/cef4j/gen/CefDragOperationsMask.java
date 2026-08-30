// GENERATED - do not edit. Regenerate via: ./mvnw generate-sources -pl cef4j-api
// -Dcef.version=150.0.18+gdb11278+chromium-150.0.7871.213
package net.kurobako.cef4j.gen;

import javax.annotation.processing.Generated;
import net.kurobako.cef4j.policy.NullableBoundary;

/**
 * "Verb" of a drag-and-drop operation as negotiated between the source and destination. These constants match their
 * equivalents in WebCore's DragActions.h and should not be renumbered.
 *
 * <p>Definition generated from cef_types.h
 *
 * <pre>typedef enum {
 *   DRAG_OPERATION_NONE = 0,
 *   DRAG_OPERATION_COPY = 1,
 *   DRAG_OPERATION_LINK = 2,
 *   DRAG_OPERATION_GENERIC = 4,
 *   DRAG_OPERATION_PRIVATE = 8,
 *   ...
 * } cef_drag_operations_mask_t;</pre>
 *
 * <p>Possible values: {@link Kind#NONE}, {@link Kind#COPY}, {@link Kind#LINK}, {@link Kind#GENERIC},
 * {@link Kind#PRIVATE}, {@link Kind#MOVE}, {@link Kind#DELETE}, {@link Kind#EVERY}
 *
 * @see <a href="https://cef-builds.spotifycdn.com/docs/150.0/cef__types_8h.html">cef_types.h</a>
 */
@Generated("./mvnw generate-sources -pl cef4j-api -Dcef.version=150.0.18+gdb11278+chromium-150.0.7871.213")
@SuppressWarnings({"SameReturnValue", "EmptyMethod", "UnusedReturnValue", "unused"})
@NullableBoundary("generated external ABI")
public final class CefDragOperationsMask implements CefEnum<CefDragOperationsMask> {

    /** Known constants for {@link CefDragOperationsMask}. */
    public enum Kind {
        NONE(0, "0", "DRAG_OPERATION_NONE"),
        COPY(1, "1", "DRAG_OPERATION_COPY"),
        LINK(2, "2", "DRAG_OPERATION_LINK"),
        GENERIC(4, "4", "DRAG_OPERATION_GENERIC"),
        PRIVATE(8, "8", "DRAG_OPERATION_PRIVATE"),
        MOVE(16, "16", "DRAG_OPERATION_MOVE"),
        DELETE(32, "32", "DRAG_OPERATION_DELETE"),
        EVERY(0xffffffffL, "0xffffffff", "DRAG_OPERATION_EVERY");

        private static final Kind[] VALUES = Kind.values();

        /** The underlying C enum numeric value. */
        public final long value;

        /** The original C expression/literal (e.g. {@code "1 << 3"}), or the numeric string. */
        public final String expr;

        /** The C constant name (e.g., {@code "cef_drag_operations_mask_t"}). */
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

    private CefDragOperationsMask(long value) {
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
    public static CefDragOperationsMask of(long v) {
        return new CefDragOperationsMask(v);
    }

    /** Returns an instance for the given known constant. */
    public static CefDragOperationsMask of(Kind k) {
        return new CefDragOperationsMask(k.value);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (!(obj instanceof CefDragOperationsMask)) return false;
        return this.value == ((CefDragOperationsMask) obj).value;
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
