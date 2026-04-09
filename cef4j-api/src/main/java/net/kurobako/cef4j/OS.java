package net.kurobako.cef4j;

import java.util.Locale;

/** Platform detection utility. Identifies the current OS and architecture for native library loading. */
@SuppressWarnings("unused")
public final class OS {

    private OS() {}

    private static final String OS_NAME = System.getProperty("os.name", "").toLowerCase(Locale.ROOT);
    private static final String OS_ARCH = System.getProperty("os.arch", "").toLowerCase(Locale.ROOT);

    public static boolean isWindows() {
        return OS_NAME.contains("win");
    }

    public static boolean isMacOS() {
        return OS_NAME.contains("mac") || OS_NAME.contains("darwin");
    }

    public static boolean isLinux() {
        return OS_NAME.contains("linux");
    }

    public static boolean isArm64() {
        return OS_ARCH.equals("aarch64") || OS_ARCH.equals("arm64");
    }

    public static boolean isAmd64() {
        return OS_ARCH.equals("amd64") || OS_ARCH.equals("x86_64");
    }

    /**
     * Returns the CEF platform identifier (e.g. "linux64", "macosarm64", "windows64").
     *
     * @return the platform string matching CEF binary distribution naming
     * @throws UnsupportedOperationException if the platform is not supported
     */
    public static String getPlatform() {
        if (isLinux()) {
            if (isArm64()) return "linuxarm64";
            if (isAmd64()) return "linux64";
        } else if (isMacOS()) {
            if (isArm64()) return "macosarm64";
            if (isAmd64()) return "macosx64";
        } else if (isWindows()) {
            return "windows64";
        }
        throw new UnsupportedOperationException("Unsupported platform: " + OS_NAME + "/" + OS_ARCH);
    }

    /**
     * Returns the native library file name for the current platform.
     *
     * @param baseName the library base name (e.g. "cef4j")
     * @return platform-specific name (e.g. "libcef4j.so", "cef4j.dll", "libcef4j.dylib")
     */
    public static String mapLibraryName(String baseName) {
        if (isWindows()) return baseName + ".dll";
        if (isMacOS()) return "lib" + baseName + ".dylib";
        return "lib" + baseName + ".so";
    }
}
