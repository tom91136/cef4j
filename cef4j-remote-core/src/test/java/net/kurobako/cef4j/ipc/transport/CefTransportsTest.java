package net.kurobako.cef4j.ipc.transport;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import java.nio.file.Path;
import net.kurobako.cef4j.test.ServiceLoaderFixture;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;

final class CefTransportsTest {
    @Test
    void discoversPortableProvidersWithoutOpeningNativeLibraries() {
        assertThat(CefTransports.available()).contains("local", "websocket", "zmq");
    }

    @Test
    void rejectsDuplicateProviderNames(@TempDir Path temporaryDirectory) throws Exception {
        try (ServiceLoaderFixture fixture = new ServiceLoaderFixture(
                temporaryDirectory, CefTransportProvider.class, FirstProvider.class, SecondProvider.class)) {
            assertThat(fixture.isActive()).isTrue();
            assertThatThrownBy(CefTransports::available)
                    .isInstanceOf(IllegalStateException.class)
                    .hasMessageContaining("Duplicate cef4j transport provider name 'duplicate'");
        }
    }

    public static final class FirstProvider extends DuplicateProvider {}

    public static final class SecondProvider extends DuplicateProvider {}

    public abstract static class DuplicateProvider implements CefTransportProvider {
        @Override
        public String name() {
            return "duplicate";
        }

        @Override
        public CefTransport connect(String endpoint) {
            throw new UnsupportedOperationException();
        }
    }
}
