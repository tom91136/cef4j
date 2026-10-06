package net.kurobako.cef4j.ipc.devtools;

import static org.assertj.core.api.Assertions.assertThatThrownBy;

import java.nio.file.Path;
import java.util.concurrent.CompletableFuture;
import net.kurobako.cef4j.cdp.CdpTransport;
import net.kurobako.cef4j.ipc.protocol.gen.BrowserHost;
import net.kurobako.cef4j.ipc.session.CefSession;
import net.kurobako.cef4j.ipc.session.RemoteHandle;
import net.kurobako.cef4j.test.ServiceLoaderFixture;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;

final class RemoteDevToolsSessionFactoryProviderTest {
    @Test
    void rejectsAmbiguousInstalledProviders(@TempDir Path temporaryDirectory) throws Exception {
        try (ServiceLoaderFixture fixture = new ServiceLoaderFixture(
                temporaryDirectory, RemoteDevToolsSessionFactory.class, FirstFactory.class, SecondFactory.class)) {
            org.assertj.core.api.Assertions.assertThat(fixture.isActive()).isTrue();
            assertThatThrownBy(RemoteDevToolsSessionFactory::installed)
                    .isInstanceOf(IllegalStateException.class)
                    .hasMessageContaining("Multiple Remote CDP adapters")
                    .hasMessageContaining("supply one explicitly");
        }
    }

    public static final class FirstFactory extends UnsupportedFactory {}

    public static final class SecondFactory extends UnsupportedFactory {}

    public abstract static class UnsupportedFactory implements RemoteDevToolsSessionFactory {
        @Override
        public CompletableFuture<? extends CdpTransport> attach(
                CefSession session, RemoteHandle browser, BrowserHost host) {
            throw new UnsupportedOperationException();
        }
    }
}
