// GENERATED - do not edit. Regenerate via: mvn generate-sources -pl cef4j-platform -Dcef.version=150.0.18+gdb11278+chromium-150.0.7871.213
package net.kurobako.cef4j.gen;

import javax.annotation.processing.Generated;

/**
 * Supported file dialog modes.
 * <p>Definition generated from cef_types.h
 * <pre>typedef enum {
 *   FILE_DIALOG_OPEN = 0,
 *   FILE_DIALOG_OPEN_MULTIPLE = 1,
 *   FILE_DIALOG_OPEN_FOLDER = 2,
 *   FILE_DIALOG_SAVE = 3,
 *   FILE_DIALOG_NUM_VALUES = 4
 * } cef_file_dialog_mode_t;</pre>
 * <p>Possible values: {@link Kind#OPEN}, {@link Kind#OPEN_MULTIPLE}, {@link Kind#OPEN_FOLDER}, {@link Kind#SAVE}, {@link Kind#NUM_VALUES}
 *
 * @see <a href="https://cef-builds.spotifycdn.com/docs/150.0/cef__types_8h.html">cef_types.h</a>
 */
@Generated("mvn generate-sources -pl cef4j-platform -Dcef.version=150.0.18+gdb11278+chromium-150.0.7871.213")
@SuppressWarnings({"SameReturnValue", "EmptyMethod", "UnusedReturnValue", "unused", "NullableForbidden"})
public final class CefFileDialogMode implements CefEnum<CefFileDialogMode> {

    /** Known constants for {@link CefFileDialogMode}. */
    public enum Kind {
        /** Requires that the file exists before allowing the user to pick it.  */
        OPEN(0, "0", "FILE_DIALOG_OPEN"),
        /** Like Open, but allows picking multiple files to open.  */
        OPEN_MULTIPLE(1, "1", "FILE_DIALOG_OPEN_MULTIPLE"),
        /** Like Open, but selects a folder to open.  */
        OPEN_FOLDER(2, "2", "FILE_DIALOG_OPEN_FOLDER"),
        /** Allows picking a nonexistent file, and prompts to overwrite if the file already exists.  */
        SAVE(3, "3", "FILE_DIALOG_SAVE"),
        NUM_VALUES(4, "4", "FILE_DIALOG_NUM_VALUES");

        private static final Kind[] VALUES = Kind.values();

        /** The underlying C enum numeric value. */
        public final long value;

        /** The original C expression/literal (e.g. {@code "1 << 3"}), or the numeric string. */
        public final String expr;

        /** The C constant name (e.g., {@code "cef_file_dialog_mode_t"}). */
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

    private CefFileDialogMode(long value) {
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
    public static CefFileDialogMode of(long v) {
        return new CefFileDialogMode(v);
    }

    /** Returns an instance for the given known constant. */
    public static CefFileDialogMode of(Kind k) {
        return new CefFileDialogMode(k.value);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (!(obj instanceof CefFileDialogMode)) return false;
        return this.value == ((CefFileDialogMode) obj).value;
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
