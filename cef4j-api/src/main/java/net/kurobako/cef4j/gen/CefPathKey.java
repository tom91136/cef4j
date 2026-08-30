// GENERATED - do not edit. Regenerate via: ./mvnw generate-sources -pl cef4j-api
// -Dcef.version=150.0.18+gdb11278+chromium-150.0.7871.213
package net.kurobako.cef4j.gen;

import javax.annotation.processing.Generated;
import net.kurobako.cef4j.policy.NullableBoundary;

/**
 * Path key values.
 *
 * <p>Definition generated from cef_types.h
 *
 * <pre>typedef enum {
 *   PK_DIR_CURRENT = 0,
 *   PK_DIR_EXE = 1,
 *   PK_DIR_MODULE = 2,
 *   PK_DIR_TEMP = 3,
 *   PK_FILE_EXE = 4,
 *   ...
 * } cef_path_key_t;</pre>
 *
 * <p>Possible values: {@link Kind#DIR_CURRENT}, {@link Kind#DIR_EXE}, {@link Kind#DIR_MODULE}, {@link Kind#DIR_TEMP},
 * {@link Kind#FILE_EXE}, {@link Kind#FILE_MODULE}, {@link Kind#LOCAL_APP_DATA}, {@link Kind#USER_DATA},
 * {@link Kind#DIR_RESOURCES}, {@link Kind#NUM_VALUES}
 *
 * @see <a href="https://cef-builds.spotifycdn.com/docs/150.0/cef__types_8h.html">cef_types.h</a>
 */
@Generated("./mvnw generate-sources -pl cef4j-api -Dcef.version=150.0.18+gdb11278+chromium-150.0.7871.213")
@SuppressWarnings({"SameReturnValue", "EmptyMethod", "UnusedReturnValue", "unused"})
@NullableBoundary("generated external ABI")
public final class CefPathKey implements CefEnum<CefPathKey> {

    /** Known constants for {@link CefPathKey}. */
    public enum Kind {
        /** Current directory. */
        DIR_CURRENT(0, "0", "PK_DIR_CURRENT"),
        /** Directory containing PK_FILE_EXE. */
        DIR_EXE(1, "1", "PK_DIR_EXE"),
        /** Directory containing PK_FILE_MODULE. */
        DIR_MODULE(2, "2", "PK_DIR_MODULE"),
        /** Temporary directory. */
        DIR_TEMP(3, "3", "PK_DIR_TEMP"),
        /** Path and filename of the current executable. */
        FILE_EXE(4, "4", "PK_FILE_EXE"),
        /** Path and filename of the module containing the CEF code (usually the libcef module). */
        FILE_MODULE(5, "5", "PK_FILE_MODULE"),
        /** "Local Settings\Application Data" directory under the user profile directory on Windows. */
        LOCAL_APP_DATA(6, "6", "PK_LOCAL_APP_DATA"),
        /**
         * "Application Data" directory under the user profile directory on Windows and "~/Library/Application Support"
         * directory on MacOS.
         */
        USER_DATA(7, "7", "PK_USER_DATA"),
        /** Directory containing application resources. Can be configured via CefSettings.resources_dir_path. */
        DIR_RESOURCES(8, "8", "PK_DIR_RESOURCES"),
        NUM_VALUES(9, "9", "PK_NUM_VALUES");

        private static final Kind[] VALUES = Kind.values();

        /** The underlying C enum numeric value. */
        public final long value;

        /** The original C expression/literal (e.g. {@code "1 << 3"}), or the numeric string. */
        public final String expr;

        /** The C constant name (e.g., {@code "cef_path_key_t"}). */
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

    private CefPathKey(long value) {
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
    public static CefPathKey of(long v) {
        return new CefPathKey(v);
    }

    /** Returns an instance for the given known constant. */
    public static CefPathKey of(Kind k) {
        return new CefPathKey(k.value);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (!(obj instanceof CefPathKey)) return false;
        return this.value == ((CefPathKey) obj).value;
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
