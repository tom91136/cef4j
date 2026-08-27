package net.kurobako.cef4j;

import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.atLeastOnce;
import static org.mockito.Mockito.spy;
import static org.mockito.Mockito.verify;

import java.nio.file.Files;
import java.nio.file.Path;
import java.time.Duration;
import java.util.Optional;
import java.util.concurrent.CountDownLatch;
import net.kurobako.cef4j.gen.CefApp;
import net.kurobako.cef4j.gen.CefBrowserProcessHandler;
import net.kurobako.cef4j.gen.CefSettings;
import net.kurobako.cef4j.test.CefTestLaunch;
import net.kurobako.cef4j.test.TestDeadline;
import net.kurobako.cef4j.test.TestTempDirs;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Timeout;
import org.junit.jupiter.api.io.CleanupMode;
import org.junit.jupiter.api.io.TempDir;

@Timeout(30)
class CefAppDelegatingTest {

    private static CefApp handlerA;
    private static CefApp handlerB;
    private static CefBrowserProcessHandler bphA;
    private static CefBrowserProcessHandler bphB;
    private static final CountDownLatch contextInitialised = new CountDownLatch(2);

    @BeforeAll
    static void setup(@TempDir(cleanup = CleanupMode.NEVER) Path tempDir) throws Exception {
        SystemBootstrap.load();

        bphA = spy(new CefBrowserProcessHandler() {
            @Override
            public void onContextInitialized() {
                contextInitialised.countDown();
            }
        });
        bphB = spy(new CefBrowserProcessHandler() {
            @Override
            public void onContextInitialized() {
                contextInitialised.countDown();
            }
        });

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
        TestTempDirs.cleanupAtExit(tempDir);
        CefSettings.Mutable settings = new CefSettings.Mutable();
        settings.noSandbox = 1;
        settings.cachePath = cacheDir.toAbsolutePath().toString();
        settings.rootCachePath = cacheDir.toAbsolutePath().toString();
        settings.windowlessRenderingEnabled = 1;
        settings.externalMessagePump = 1;
        settings.multiThreadedMessageLoop = 0;
        Cef.INSTANCE.initialise(settings, CefTestLaunch.extraArgs());

        TestDeadline.after(Duration.ofSeconds(5))
                .until(
                        () -> contextInitialised.getCount() == 0,
                        Cef.INSTANCE::doMessageLoopWork,
                        Duration.ofMillis(5),
                        "CEF context initialization");
    }

    @Test
    void voidDispatchOnRegisterCustomSchemesReachesBothHandlers() {
        verify(handlerA, atLeastOnce()).onRegisterCustomSchemes(any());
        verify(handlerB, atLeastOnce()).onRegisterCustomSchemes(any());
    }

    @Test
    void voidDispatchOnBeforeCommandLineProcessingReachesBothHandlers() {
        verify(handlerA, atLeastOnce()).onBeforeCommandLineProcessing(any(), any());
        verify(handlerB, atLeastOnce()).onBeforeCommandLineProcessing(any(), any());
    }

    @Test
    void optionalDispatchGetBrowserProcessHandlerBothSubHandlersReceiveOnContextInitialized() {
        verify(bphA, atLeastOnce()).onContextInitialized();
        verify(bphB, atLeastOnce()).onContextInitialized();
    }

    @Test
    void addAppHandlerAfterInitThrows() {
        CefApp extra = spy(new CefApp() {});
        assertThatThrownBy(() -> Cef.INSTANCE.addAppHandler(extra))
                .isInstanceOf(IllegalStateException.class)
                .hasMessageContaining("already initialized");
    }

    @Test
    void removeAppHandlerAfterInitIsNoOp() {
        Cef.INSTANCE.removeAppHandler(handlerA);
    }

    @Test
    @SuppressWarnings("NullAway")
    void nullHandlerIsRejected() {
        assertThatThrownBy(() -> Cef.INSTANCE.addAppHandler(null)).isInstanceOf(NullPointerException.class);
        assertThatThrownBy(() -> Cef.INSTANCE.removeAppHandler(null)).isInstanceOf(NullPointerException.class);
    }
}
