package net.kurobako.cef4j.packager;

import java.util.Locale;

/** Upstream CEF binary configuration selected from a binary distribution. */
public enum CefBuildType {
    /** Optimized runtime from the minimal distribution. */
    RELEASE("Release", true),
    /** Diagnostic runtime from the standard distribution. */
    DEBUG("Debug", false);

    private final String directory;
    private final boolean minimalArchive;

    CefBuildType(String directory, boolean minimalArchive) {
        this.directory = directory;
        this.minimalArchive = minimalArchive;
    }

    String directory() {
        return directory;
    }

    boolean usesMinimalArchive() {
        return minimalArchive;
    }

    static CefBuildType parse(String value) {
        try {
            return valueOf(value.trim().toUpperCase(Locale.ROOT));
        } catch (IllegalArgumentException failure) {
            throw new IllegalArgumentException("Unsupported CEF build type: " + value + " (expected release or debug)");
        }
    }
}
