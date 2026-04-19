package net.kurobako.cef4j;

import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.atLeastOnce;
import static org.mockito.Mockito.spy;
import static org.mockito.Mockito.timeout;
import static org.mockito.Mockito.verify;

import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;
import java.util.Optional;
import net.kurobako.cef4j.gen.CefApp;
import net.kurobako.cef4j.gen.CefBrowserProcessHandler;
import net.kurobako.cef4j.gen.CefSettings;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Timeout;
import org.junit.jupiter.api.io.TempDir;

@Timeout(30)
class CefAppDelegatingTest {

    private static CefApp handlerA;
    private static CefApp handlerB;
    private static CefBrowserProcessHandler bphA;
    private static CefBrowserProcessHandler bphB;

    @BeforeAll
    static void setup(@TempDir Path tempDir) throws Exception {
        SystemBootstrap.load();

        bphA = spy(new CefBrowserProcessHandler() {});
        bphB = spy(new CefBrowserProcessHandler() {});

        handlerA = spy(new CefApp() {
            @Override
            public Optional<CefBrowserProcessHandler> getBrowserProcessHandler() {
                return Optional.of(bphA);
            }
        });
        handlerB = spy(new CefApp() {
            @Override
            public Optional<CefBrowserProcessHandler> getBrowserProcessHandler() {
                return Optional.of(bphB);
            }
        });

        Cef.INSTANCE.addAppHandler(handlerA);
        Cef.INSTANCE.addAppHandler(handlerB);

        Path cacheDir = Files.createDirectories(tempDir.resolve("cef-cache"));
        CefSettings.Mutable settings = new CefSettings.Mutable();
        settings.cachePath = cacheDir.toAbsolutePath().toString();
        settings.windowlessRenderingEnabled = 1;
        settings.externalMessagePump = 1;
        settings.multiThreadedMessageLoop = 0;
        Cef.INSTANCE.initialise(settings, List.of());

        // Pump briefly so onContextInitialized fires before the tests run.
        long deadline = System.currentTimeMillis() + 5_000;
        while (System.currentTimeMillis() < deadline) {
            Cef.INSTANCE.doMessageLoopWork();
            Thread.sleep(5);
        }
    }

    @AfterAll
    static void shutdown() {
        // No terminate: own surefire fork, JVM exit releases natives.
    }

    @Test
    void voidDispatch_onRegisterCustomSchemes_reachesBothHandlers() {
        verify(handlerA, atLeastOnce()).onRegisterCustomSchemes(any());
        verify(handlerB, atLeastOnce()).onRegisterCustomSchemes(any());
    }

    @Test
    void voidDispatch_onBeforeCommandLineProcessing_reachesBothHandlers() {
        verify(handlerA, atLeastOnce()).onBeforeCommandLineProcessing(any(), any());
        verify(handlerB, atLeastOnce()).onBeforeCommandLineProcessing(any(), any());
    }

    @Test
    void optionalDispatch_getBrowserProcessHandler_bothSubHandlersReceiveOnContextInitialized() {
        // Composite browser-process handler must fan out onContextInitialized to every sub-handler.
        verify(bphA, timeout(5_000).atLeastOnce()).onContextInitialized();
        verify(bphB, timeout(5_000).atLeastOnce()).onContextInitialized();
    }

    @Test
    void addAppHandler_afterInit_throws() {
        CefApp extra = spy(new CefApp() {});
        assertThatThrownBy(() -> Cef.INSTANCE.addAppHandler(extra))
                .isInstanceOf(IllegalStateException.class)
                .hasMessageContaining("already initialized");
    }

    @Test
    void removeAppHandler_afterInit_isNoOp() {
        // Contract: safe at any time; must not throw.
        Cef.INSTANCE.removeAppHandler(handlerA);
    }

    @Test
    @SuppressWarnings("NullAway")
    void nullHandler_isRejected() {
        assertThatThrownBy(() -> Cef.INSTANCE.addAppHandler(null)).isInstanceOf(NullPointerException.class);
        assertThatThrownBy(() -> Cef.INSTANCE.removeAppHandler(null)).isInstanceOf(NullPointerException.class);
    }
}
