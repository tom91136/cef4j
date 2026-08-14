package net.kurobako.cef4j.test;

/** Platform/version-specific lifecycle policy for native CEF test forks. */
public final class CefTestLifecycle {
    /**
     * Returns whether a test fork should call {@code cef_shutdown} explicitly.
     *
     * <p>CEF 109 and 116 can segfault after successful browser closure and test completion on Linux. Tests still close
     * every browser and verify the corresponding callbacks; their isolated Surefire JVM is the final native runtime
     * boundary, matching the policy already required on other affected platform/version combinations.
     */
    public static boolean explicitShutdownSafe() {
        return !isLinux() || cefApiVersion() > 116;
    }

    private static boolean isLinux() {
        return System.getProperty("os.name", "")
                .toLowerCase(java.util.Locale.ROOT)
                .contains("linux");
    }

    private static int cefApiVersion() {
        for (String property : new String[] {"cef.api.version", "cef4j.test.cefApiVersion"}) {
            String value = System.getProperty(property);
            if (value != null && !value.isBlank()) return Integer.parseInt(value.trim());
        }

        String cefVersion = System.getProperty("cef.version");
        if (cefVersion != null && !cefVersion.isBlank()) {
            int separator = cefVersion.indexOf('.');
            String major = separator >= 0 ? cefVersion.substring(0, separator) : cefVersion;
            return Integer.parseInt(major.trim());
        }
        return Integer.MAX_VALUE;
    }

    private CefTestLifecycle() {}
}
