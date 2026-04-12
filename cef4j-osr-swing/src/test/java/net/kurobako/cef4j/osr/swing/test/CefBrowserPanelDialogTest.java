package net.kurobako.cef4j.osr.swing.test;

import static net.kurobako.cef4j.osr.swing.test.SwingBrowserPanelTestSupport.*;
import static org.assertj.core.api.Assertions.assertThat;

import java.util.Optional;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicReference;
import javax.swing.SwingUtilities;
import net.kurobako.cef4j.gen.CefBrowser;
import net.kurobako.cef4j.gen.CefBrowserHost;
import net.kurobako.cef4j.gen.CefClient;
import net.kurobako.cef4j.gen.CefDisplayHandler;
import net.kurobako.cef4j.gen.CefFrame;
import net.kurobako.cef4j.gen.CefJsDialogCallback;
import net.kurobako.cef4j.gen.CefJsDialogHandler;
import net.kurobako.cef4j.gen.CefJsDialogType;
import net.kurobako.cef4j.gen.CefLifeSpanHandler;
import net.kurobako.cef4j.gen.CefLoadHandler;
import net.kurobako.cef4j.gen.CefRenderHandler;
import net.kurobako.cef4j.osr.swing.CefBrowserPanel;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Timeout;

@Timeout(30)
class CefBrowserPanelDialogTest extends SwingBrowserPanelTestBase {

    @FunctionalInterface
    interface DialogHandler {
        boolean handle(
                CefJsDialogType dialogType,
                String messageText,
                String defaultPromptText,
                CefJsDialogCallback callback,
                int[] suppressMessage);
    }

    private CefBrowserPanel createPanelWithDialogHandler(DialogHandler handler) throws Exception {
        AtomicReference<CefBrowserPanel> panelRef = new AtomicReference<>();
        PanelState state = new PanelState();
        CountDownLatch ready = state.browserReady;

        onSwingThread(() -> {
            CefBrowserPanel panel = new CefBrowserPanel();
            STATES.put(panel, state);
            panelRef.set(panel);

            CefRenderHandler renderHandler = panel.createRenderHandler();

            CefClient client = new CefClient() {
                @Override
                public Optional<CefRenderHandler> getRenderHandler() {
                    return Optional.of(renderHandler);
                }

                @Override
                public Optional<CefLifeSpanHandler> getLifeSpanHandler() {
                    return Optional.of(new CefLifeSpanHandler() {
                        @Override
                        public void onAfterCreated(CefBrowser b) {
                            SwingUtilities.invokeLater(() -> {
                                panel.setBrowser(b);
                                state.browserReady.countDown();
                            });
                        }
                    });
                }

                @Override
                public Optional<CefLoadHandler> getLoadHandler() {
                    return Optional.of(new CefLoadHandler() {
                        @Override
                        public void onLoadingStateChange(
                                CefBrowser b, boolean isLoading, boolean canGoBack, boolean canGoForward) {
                            state.loading = isLoading;
                            if (!isLoading) state.loadEnded = true;
                        }
                    });
                }

                @Override
                public Optional<CefDisplayHandler> getDisplayHandler() {
                    return Optional.of(new CefDisplayHandler() {
                        @Override
                        public void onTitleChange(CefBrowser b, String title) {
                            state.title = title != null ? title : "";
                        }

                        @Override
                        public void onAddressChange(CefBrowser b, CefFrame f, String url) {
                            state.location = url != null ? url : "";
                        }
                    });
                }

                @Override
                public Optional<CefJsDialogHandler> getJsDialogHandler() {
                    return Optional.of(new CefJsDialogHandler() {
                        @Override
                        public boolean onJsDialog(
                                CefBrowser browser,
                                String originUrl,
                                CefJsDialogType dialogType,
                                String messageText,
                                String defaultPromptText,
                                CefJsDialogCallback callback,
                                int[] suppressMessage) {
                            return handler.handle(
                                    dialogType, messageText, defaultPromptText, callback, suppressMessage);
                        }
                    });
                }
            };

            javax.swing.JFrame frame = new javax.swing.JFrame("cef4j Dialog Test");
            frame.setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
            frame.setLayout(new java.awt.BorderLayout());
            frame.add(panel, java.awt.BorderLayout.CENTER);
            frame.setSize(800, 600);
            frame.setVisible(true);

            net.kurobako.cef4j.gen.CefWindowInfo windowInfo =
                    net.kurobako.cef4j.Cef.createWindowlessInfo(new net.kurobako.cef4j.gen.CefRect(
                            0, 0, Math.max(1, panel.getWidth()), Math.max(1, panel.getHeight())));
            net.kurobako.cef4j.gen.CefBrowserSettings.Mutable browserSettings =
                    new net.kurobako.cef4j.gen.CefBrowserSettings.Mutable();
            browserSettings.windowlessFrameRate = 60;
            CefBrowserHost.createBrowser(windowInfo, client, "", browserSettings.toImmutable(), null, null);
        });

        if (net.kurobako.cef4j.OS.isMacOS()) {
            long deadline = System.currentTimeMillis() + 10_000;
            while (ready.getCount() > 0 && System.currentTimeMillis() < deadline) {
                net.kurobako.cef4j.Cef.INSTANCE.doMessageLoopWork();
                Thread.sleep(5);
            }
            if (ready.getCount() > 0) {
                throw new java.util.concurrent.TimeoutException("Timed out waiting for browser creation");
            }
        } else if (!ready.await(10, TimeUnit.SECONDS)) {
            throw new java.util.concurrent.TimeoutException("Timed out waiting for browser creation");
        }
        return panelRef.get();
    }

    @Test
    void alertHandlerFires() throws Exception {
        AtomicReference<String> alertText = new AtomicReference<>();

        CefBrowserPanel panel = createPanelWithDialogHandler((type, message, defaultPrompt, callback, suppress) -> {
            if (type.kind().orElse(null) == CefJsDialogType.Kind.ALERT) {
                alertText.set(message);
                if (callback != null) callback.cont(1, null);
                return true;
            }
            return false;
        });

        loadContent(panel, "<html><body><script>alert('swing-alert')</script></body></html>");

        assertThat(waitUntil(() -> "swing-alert".equals(alertText.get()), 5_000))
                .isTrue();
    }

    @Test
    void confirmHandlerReceivesMessageAndReturnsResult() throws Exception {
        AtomicReference<String> receivedMessage = new AtomicReference<>();

        CefBrowserPanel panel = createPanelWithDialogHandler((type, message, defaultPrompt, callback, suppress) -> {
            if (type.kind().orElse(null) == CefJsDialogType.Kind.CONFIRM) {
                receivedMessage.set(message);
                if (callback != null) callback.cont(1, null);
                return true;
            }
            if (type.kind().orElse(null) == CefJsDialogType.Kind.ALERT) {
                if (callback != null) callback.cont(1, null);
                return true;
            }
            return false;
        });

        loadContent(
                panel,
                "<html><body><script>document.title = confirm('swing-confirm') ? 'yes' : 'no';</script></body></html>");

        assertThat(waitUntil(() -> "swing-confirm".equals(receivedMessage.get()), 5_000))
                .isTrue();
        assertThat(waitUntil(() -> "yes".equals(getTitle(panel)), 5_000)).isTrue();
    }

    @Test
    void confirmHandlerDenialReturnsCorrectResult() throws Exception {
        CefBrowserPanel panel = createPanelWithDialogHandler((type, message, defaultPrompt, callback, suppress) -> {
            if (type.kind().orElse(null) == CefJsDialogType.Kind.CONFIRM) {
                if (callback != null) callback.cont(0, null);
                return true;
            }
            return false;
        });

        loadContent(
                panel,
                "<html><body><script>document.title = confirm('deny-me') ? 'yes' : 'no';</script></body></html>");

        assertThat(waitUntil(() -> "no".equals(getTitle(panel)), 5_000)).isTrue();
    }

    @Test
    void promptHandlerReceivesMessageAndDefaultValue() throws Exception {
        AtomicReference<String> receivedMessage = new AtomicReference<>();
        AtomicReference<String> receivedDefault = new AtomicReference<>();

        CefBrowserPanel panel = createPanelWithDialogHandler((type, message, defaultPrompt, callback, suppress) -> {
            if (type.kind().orElse(null) == CefJsDialogType.Kind.PROMPT) {
                receivedMessage.set(message);
                receivedDefault.set(defaultPrompt);
                if (callback != null) callback.cont(1, "user-input");
                return true;
            }
            return false;
        });

        loadContent(
                panel,
                "<html><body><script>document.title = prompt('swing-prompt', 'default-val');</script></body></html>");

        assertThat(waitUntil(() -> "swing-prompt".equals(receivedMessage.get()), 5_000))
                .isTrue();
        assertThat(receivedDefault.get()).isEqualTo("default-val");
        assertThat(waitUntil(() -> "user-input".equals(getTitle(panel)), 5_000)).isTrue();
    }

    @Test
    void promptHandlerReturningNullActsAsCancel() throws Exception {
        CefBrowserPanel panel = createPanelWithDialogHandler((type, message, defaultPrompt, callback, suppress) -> {
            if (type.kind().orElse(null) == CefJsDialogType.Kind.PROMPT) {
                if (callback != null) callback.cont(0, null);
                return true;
            }
            return false;
        });

        loadContent(
                panel,
                "<html><body><script>document.title = prompt('cancel-me') === null ? 'cancelled' : 'not-cancelled';</script></body></html>");

        assertThat(waitUntil(() -> "cancelled".equals(getTitle(panel)), 5_000)).isTrue();
    }
}
