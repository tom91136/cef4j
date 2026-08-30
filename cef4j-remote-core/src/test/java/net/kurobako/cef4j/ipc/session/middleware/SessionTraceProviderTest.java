package net.kurobako.cef4j.ipc.session.middleware;

import static org.assertj.core.api.Assertions.assertThatThrownBy;

import java.io.InputStream;
import java.io.OutputStream;
import java.nio.file.Path;
import java.util.Map;
import net.kurobako.cef4j.test.ServiceLoaderFixture;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;

final class SessionTraceProviderTest {
    @Test
    void rejectsAmbiguousInstalledProviders(@TempDir Path temporaryDirectory) throws Exception {
        try (ServiceLoaderFixture fixture = new ServiceLoaderFixture(
                temporaryDirectory, SessionTraceCodec.class, FirstCodec.class, SecondCodec.class)) {
            org.assertj.core.api.Assertions.assertThat(fixture.isActive()).isTrue();
            assertThatThrownBy(SessionTrace::defaultCodec)
                    .isInstanceOf(IllegalStateException.class)
                    .hasMessageContaining("Multiple SessionTraceCodec providers")
                    .hasMessageContaining("supply one explicitly");
        }
    }

    public static final class FirstCodec extends UnsupportedCodec {}

    public static final class SecondCodec extends UnsupportedCodec {}

    public abstract static class UnsupportedCodec implements SessionTraceCodec {
        @Override
        public String format() {
            return "test";
        }

        @Override
        public String fileExtension() {
            return ".test";
        }

        @Override
        public SessionTraceWriter openWriter(OutputStream destination, Map<String, String> metadata) {
            throw new UnsupportedOperationException();
        }

        @Override
        public SessionTrace.Recording read(InputStream source) {
            throw new UnsupportedOperationException();
        }
    }
}
