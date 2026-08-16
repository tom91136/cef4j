package net.kurobako.cef4j.test;

/** Platform/version-specific policy for native CEF test forks. */
public final class CefTestLifecycle {
    /**
     * Returns whether a test fork should call {@code cef_shutdown} explicitly.
     *
     * <p>Every supported CEF version must be shut down before its test JVM exits. Leaving CEF initialized lets native
     * worker threads race JVM teardown and can crash an otherwise successful test fork.
     */
    public static boolean explicitShutdownSafe() {
        return true;
    }

    /** Legacy macOS CEF releases do not run the native Swing contract reliably under hosted test runners. */
    public static boolean nativeSwingContractAvailable() {
        return nativeSwingContractAvailable(System.getProperty("os.name", ""), cefApiVersion());
    }

    static boolean nativeSwingContractAvailable(String osName, int cefApiVersion) {
        String os = osName.toLowerCase(java.util.Locale.ROOT);
        return !os.contains("mac") || cefApiVersion > 116;
    }

    private static int cefApiVersion() {
        for (String property : new String[] {"cef.api.version", "cef4j.test.cefApiVersion"}) {
            String value = System.getProperty(property);
            if (value != null && !value.isBlank()) return Integer.parseInt(value.trim());
        }
        String cefVersion = System.getProperty("cef.version");
        if (cefVersion == null || cefVersion.isBlank()) return Integer.MAX_VALUE;
        int separator = cefVersion.indexOf('.');
        return Integer.parseInt((separator >= 0 ? cefVersion.substring(0, separator) : cefVersion).trim());
    }

    private CefTestLifecycle() {}
}
