package net.kurobako.cef4j.packager;

import java.util.Arrays;
import java.util.Locale;

/** A CEF binary-distribution target and its cef4j runtime resource identifier. */
public enum CefPlatform {
    /** Linux on x86-64. */
    LINUX_X86_64("linux-x86_64", "linux64", "libcef.so"),
    /** Linux on ARM64. */
    LINUX_ARM64("linux-arm64", "linuxarm64", "libcef.so"),
    /** Windows on x86-64. */
    WINDOWS_X86_64("windows-x86_64", "windows64", "libcef.dll"),
    /** Windows on ARM64. */
    WINDOWS_ARM64("windows-arm64", "windowsarm64", "libcef.dll"),
    /** macOS on Intel x86-64. */
    MACOS_X86_64("macos-x86_64", "macosx64", "Chromium Embedded Framework.framework/Chromium Embedded Framework"),
    /** macOS on Apple Silicon ARM64. */
    MACOS_ARM64("macos-arm64", "macosarm64", "Chromium Embedded Framework.framework/Chromium Embedded Framework");

    private final String externalName;
    private final String cefName;
    private final String runtimeBinary;

    CefPlatform(String externalName, String cefName, String runtimeBinary) {
        this.externalName = externalName;
        this.cefName = cefName;
        this.runtimeBinary = runtimeBinary;
    }

    /** Returns the stable user-facing platform name accepted by the CLI. */
    public String externalName() {
        return externalName;
    }

    /** Returns the upstream CEF distribution identifier. */
    public String cefName() {
        return cefName;
    }

    /** Returns the relative path of the platform's primary CEF binary. */
    public String runtimeBinary() {
        return runtimeBinary;
    }

    /** Returns whether this target is macOS. */
    public boolean isMacOS() {
        return cefName.startsWith("macos");
    }

    /** Returns whether this target is Windows. */
    public boolean isWindows() {
        return cefName.startsWith("windows");
    }

    /** Returns the official minimal-distribution archive name for an exact CEF version. */
    public String archiveName(String cefVersion) {
        return "cef_binary_" + cefVersion + "_" + cefName + "_minimal.tar.bz2";
    }

    /** Parses either a public or upstream platform name. */
    public static CefPlatform parse(String value) {
        String normalized = normalize(value);
        return Arrays.stream(values())
                .filter(platform -> normalize(platform.externalName).equals(normalized)
                        || normalize(platform.cefName).equals(normalized)
                        || (platform.externalName.startsWith("macos-")
                                && normalize(platform.externalName.replace("macos-", "macosx-")).equals(normalized)))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("Unsupported CEF platform: " + value));
    }

    private static String normalize(String value) {
        return value.toLowerCase(Locale.ROOT).replace("_", "").replace("-", "");
    }
}
