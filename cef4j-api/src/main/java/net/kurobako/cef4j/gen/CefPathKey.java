// GENERATED - do not edit. Regenerate via: mvn generate-sources -pl cef4j-native
package net.kurobako.cef4j.gen;

/** Path key values. */
public enum CefPathKey {

    /** Current directory. */
    PK_DIR_CURRENT(0L),
    /** Directory containing PK_FILE_EXE. */
    PK_DIR_EXE(1L),
    /** Directory containing PK_FILE_MODULE. */
    PK_DIR_MODULE(2L),
    /** Temporary directory. */
    PK_DIR_TEMP(3L),
    /** Path and filename of the current executable. */
    PK_FILE_EXE(4L),
    /** Path and filename of the module containing the CEF code (usually the libcef module). */
    PK_FILE_MODULE(5L),
    /** "Local Settings\Application Data" directory under the user profile directory on Windows. */
    PK_LOCAL_APP_DATA(6L),
    /**
     * "Application Data" directory under the user profile directory on Windows and "~/Library/Application Support"
     * directory on MacOS.
     */
    PK_USER_DATA(7L),
    /** Directory containing application resources. Can be configured via CefSettings.resources_dir_path. */
    PK_DIR_RESOURCES(8L),
    PK_NUM_VALUES(9L),
    UNKNOWN(-1L);

    public final long value;

    CefPathKey(long v) {
        this.value = v;
    }

    public static CefPathKey fromLong(long v) {
        for (CefPathKey e : values()) {
            if (e.value == v) return e;
        }
        return UNKNOWN;
    }
}
