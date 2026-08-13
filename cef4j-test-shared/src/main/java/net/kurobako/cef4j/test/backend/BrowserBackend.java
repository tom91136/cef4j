package net.kurobako.cef4j.test.backend;

import java.time.Duration;
import java.util.Collections;
import java.util.Set;
import javax.annotation.Nonnull;

/**
 * Cross-backend factory for opening {@link BrowserSession}s. Each existing CEF integration test should be writable once
 * against this SPI and then run twice (once with {@link #NATIVE_NAME native}, once with {@link #IPC_NAME ipc} via
 * {@code @ParameterizedTest}). That replaces the per-backend test forks the project has today with a single matrix.
 *
 * <p>Discovery: implementations are registered via Java's {@link java.util.ServiceLoader}; the {@code @MethodSource}
 * for parameterised tests calls {@link #discover()} which collects whatever's on the test classpath. The
 * {@code cef4j-inprocess-jfx} module's test scope provides {@code NativeBrowserBackend}; the future
 * {@code cef4j-remote-jfx} test scope provides {@code RemoteCefBrowserBackend}.
 */
public interface BrowserBackend {

    String NATIVE_NAME = "native";
    String IPC_NAME = "ipc";

    /** Stable identifier — appears in test report names so failures point at the right backend. */
    @Nonnull
    String name();

    /** True if this backend can run on the current OS / display server / available binaries. */
    boolean isAvailable();

    /** Optional behaviours that shared contracts may exercise without weakening their assertions. */
    @Nonnull
    default Set<Capability> capabilities() {
        return Collections.emptySet();
    }

    /**
     * Open a browser session. The implementation handles whatever lifecycle is needed: in-process CEF init for
     * {@code native}, helper subprocess + ZMQ session for {@code ipc}. Closing the session releases all backend state —
     * closing the last live session of an in-process backend may also tear CEF down if reference counts permit.
     */
    @Nonnull
    BrowserSession openSession(@Nonnull SessionConfig config);

    enum Capability {
        VIEWPORT_RESIZE
    }

    /**
     * Implementation-defined startup configuration. Captures everything the backends share without leaking
     * backend-specific knobs into the test code.
     */
    final class SessionConfig {
        private final String initialUrl;
        private final int width;
        private final int height;
        private final Duration startupTimeout;

        public SessionConfig(@Nonnull String initialUrl, int width, int height, @Nonnull Duration startupTimeout) {
            this.initialUrl = initialUrl;
            this.width = width;
            this.height = height;
            this.startupTimeout = startupTimeout;
        }

        @Nonnull
        public String initialUrl() {
            return initialUrl;
        }

        public int width() {
            return width;
        }

        public int height() {
            return height;
        }

        @Nonnull
        public Duration startupTimeout() {
            return startupTimeout;
        }
    }

    /**
     * Snapshot of registered backends, regardless of availability. Tests use this directly via {@code @MethodSource}
     * and are expected to gate each row with {@code Assumptions.assumeTrue(backend.isAvailable())}. We deliberately do
     * NOT filter here: an empty {@code @MethodSource} causes JUnit to fail the parameterised test with "Configuration
     * error: You must configure at least one set of arguments", which is wrong on a developer box that simply doesn't
     * have a helper binary built — the right outcome there is "skipped", which Assumptions deliver.
     */
    @Nonnull
    static java.util.List<BrowserBackend> discover() {
        java.util.List<BrowserBackend> result = new java.util.ArrayList<>();
        for (BrowserBackend b : java.util.ServiceLoader.load(BrowserBackend.class)) {
            result.add(b);
        }
        return result;
    }
}
