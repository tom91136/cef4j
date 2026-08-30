package net.kurobako.cef4j.webdriver;

import static org.assertj.core.api.Assertions.assertThatThrownBy;

import java.nio.file.Path;
import net.kurobako.cef4j.test.ServiceLoaderFixture;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;

final class WebDriverJsonCodecTest {
    @Test
    void rejectsAmbiguousInstalledProviders(@TempDir Path temporaryDirectory) throws Exception {
        try (ServiceLoaderFixture fixture = new ServiceLoaderFixture(
                temporaryDirectory, WebDriverJsonCodec.class, FirstCodec.class, SecondCodec.class)) {
            org.assertj.core.api.Assertions.assertThat(fixture.isActive()).isTrue();
            assertThatThrownBy(WebDriverJsonCodec::installed)
                    .isInstanceOf(IllegalStateException.class)
                    .hasMessageContaining("Multiple WebDriver JSON codecs")
                    .hasMessageContaining("supply one explicitly");
        }
    }

    public static final class FirstCodec extends UnsupportedCodec {}

    public static final class SecondCodec extends UnsupportedCodec {}

    public abstract static class UnsupportedCodec implements WebDriverJsonCodec {
        @Override
        public JsonElement decode(byte[] json) {
            throw new UnsupportedOperationException();
        }

        @Override
        public byte[] encode(JsonElement value) {
            throw new UnsupportedOperationException();
        }
    }
}
