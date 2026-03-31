// GENERATED - do not edit. Regenerate via: mvn generate-sources -pl cef4j-native
package net.kurobako.cef4j.gen;

/** Supported file dialog modes. */
public enum CefFileDialogMode {

    /** Requires that the file exists before allowing the user to pick it. */
    FILE_DIALOG_OPEN(0L),
    /** Like Open, but allows picking multiple files to open. */
    FILE_DIALOG_OPEN_MULTIPLE(1L),
    /** Like Open, but selects a folder to open. */
    FILE_DIALOG_OPEN_FOLDER(2L),
    /** Allows picking a nonexistent file, and prompts to overwrite if the file already exists. */
    FILE_DIALOG_SAVE(3L),
    FILE_DIALOG_NUM_VALUES(4L),
    UNKNOWN(-1L);

    public final long value;

    CefFileDialogMode(long v) {
        this.value = v;
    }

    public static CefFileDialogMode fromLong(long v) {
        for (CefFileDialogMode e : values()) {
            if (e.value == v) return e;
        }
        return UNKNOWN;
    }
}
