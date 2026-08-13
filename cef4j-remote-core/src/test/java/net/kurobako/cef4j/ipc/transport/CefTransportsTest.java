package net.kurobako.cef4j.ipc.transport;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;

final class CefTransportsTest {
    @Test
    void discoversPortableProvidersWithoutOpeningNativeLibraries() {
        assertThat(CefTransports.available()).contains("local", "websocket", "zmq");
    }
}
