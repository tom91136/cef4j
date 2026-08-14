package net.kurobako.cef4j.test;

/** Lifecycle policy shared by native CEF test forks. */
public final class CefTestLifecycle {
    /**
     * Returns whether a test fork should call {@code cef_shutdown} explicitly.
     *
     * <p>Every supported CEF version must be shut down before its test JVM exits. In particular, leaving CEF 109 or 116
     * initialized lets native worker threads race JVM teardown and can segfault an otherwise successful test fork.
     */
    public static boolean explicitShutdownSafe() {
        return true;
    }

    private CefTestLifecycle() {}
}
