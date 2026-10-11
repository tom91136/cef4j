package net.kurobako.cef4j.test.backend;

import java.time.Duration;
import java.util.Collections;
import java.util.Set;

/** Test SPI shared by in-process and remote browser contracts. */
public interface BrowserBackend {

    String NATIVE_NAME = "native";
    String IPC_NAME = "ipc";

    String name();

    boolean isAvailable();

    default Set<Capability> capabilities() {
        return Collections.emptySet();
    }

    BrowserSession openSession(SessionConfig config);

    enum Capability {
        VIEWPORT_RESIZE
    }

    final class SessionConfig {
        private final String initialUrl;
        private final int width;
        private final int height;
        private final Duration startupTimeout;

        public SessionConfig(String initialUrl, int width, int height, Duration startupTimeout) {
            this.initialUrl = initialUrl;
            this.width = width;
            this.height = height;
            this.startupTimeout = startupTimeout;
        }

        public String initialUrl() {
            return initialUrl;
        }

        public int width() {
            return width;
        }

        public int height() {
            return height;
        }

        public Duration startupTimeout() {
            return startupTimeout;
        }
    }

    static java.util.List<BrowserBackend> discover() {
        java.util.List<BrowserBackend> result = new java.util.ArrayList<>();
        for (BrowserBackend b : java.util.ServiceLoader.load(BrowserBackend.class)) {
            result.add(b);
        }
        return result;
    }
}
