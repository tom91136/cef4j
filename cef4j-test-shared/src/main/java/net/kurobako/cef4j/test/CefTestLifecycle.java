package net.kurobako.cef4j.test;

/** Platform/version-specific lifecycle policy for native CEF test forks. */
public final class CefTestLifecycle {
    /** Returns whether a test fork should call {@code cef_shutdown} explicitly. */
    public static boolean explicitShutdownSafe() {
        return true;
    }

    private CefTestLifecycle() {}
}
