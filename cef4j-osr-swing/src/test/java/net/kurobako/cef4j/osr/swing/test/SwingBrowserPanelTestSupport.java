package net.kurobako.cef4j.osr.swing.test;

import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpServer;
import java.awt.BorderLayout;
import java.io.IOException;
import java.io.OutputStream;
import java.net.InetSocketAddress;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.Callable;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;
import java.util.concurrent.atomic.AtomicReference;
import java.util.function.BooleanSupplier;
import javax.annotation.Nonnull;
import javax.swing.JFrame;
import javax.swing.SwingUtilities;
import javax.swing.WindowConstants;
import net.kurobako.cef4j.Cef;
import net.kurobako.cef4j.OS;
import net.kurobako.cef4j.SystemBootstrap;
import net.kurobako.cef4j.gen.CefBrowser;
import net.kurobako.cef4j.gen.CefBrowserHost;
import net.kurobako.cef4j.gen.CefBrowserSettings;
import net.kurobako.cef4j.gen.CefClient;
import net.kurobako.cef4j.gen.CefCursorInfo;
import net.kurobako.cef4j.gen.CefCursorType;
import net.kurobako.cef4j.gen.CefDisplayHandler;
import net.kurobako.cef4j.gen.CefFrame;
import net.kurobako.cef4j.gen.CefLifeSpanHandler;
import net.kurobako.cef4j.gen.CefLoadHandler;
import net.kurobako.cef4j.gen.CefRect;
import net.kurobako.cef4j.gen.CefRenderHandler;
import net.kurobako.cef4j.gen.CefSettings;
import net.kurobako.cef4j.gen.CefWindowInfo;
import net.kurobako.cef4j.osr.swing.CefBrowserPanel;
import org.junit.jupiter.api.Assumptions;
import org.opentest4j.TestAbortedException;

final class SwingBrowserPanelTestSupport {
    private static volatile boolean started;
    private static final CopyOnWriteArrayList<JFrame> FRAMES = new CopyOnWriteArrayList<>();
    static final Map<CefBrowserPanel, PanelState> STATES = new ConcurrentHashMap<>();

    private SwingBrowserPanelTestSupport() {}

    static final class PanelState {
        volatile String title = "";
        volatile String location = "";
        volatile boolean loading;
        volatile boolean canGoBack;
        volatile boolean canGoForward;
        volatile boolean loadEnded;
        final CountDownLatch browserReady = new CountDownLatch(1);
    }

    static void ensureCefStarted() {
        if (started) return;

        // Skip when no display server is available (e.g. SSH without X-forwarding,
        // macOS without Window Server access).  The swing pom sets -Djava.awt.headless=false
        // which makes isHeadless() return false even without a Window Server, so we also probe
        // the actual toolkit: getDefaultToolkit() throws AWTError on macOS without Window Server.
        if (java.awt.GraphicsEnvironment.isHeadless()) {
            Assumptions.assumeTrue(false, "Swing tests require a display (headless environment detected)");
        }
        try {
            java.awt.Toolkit.getDefaultToolkit();
        } catch (java.awt.AWTError e) {
            Assumptions.assumeTrue(
                    false, "AWT display not available (run from a GUI session, not SSH): " + e.getMessage());
        }

        try {
            SystemBootstrap.load();
            CefSettings.Mutable settings = new CefSettings.Mutable();
            settings.windowlessRenderingEnabled = 1;
            if (OS.isMacOS()) {
                settings.externalMessagePump = 1;
                settings.multiThreadedMessageLoop = 0;
                settings.noSandbox = 1;
            } else {
                settings.multiThreadedMessageLoop = 1;
                settings.noSandbox = 1;
            }
            List<String> extraArgs = new ArrayList<>();
            if (OS.isMacOS()) {
                extraArgs.add("--no-sandbox");
            }
            Cef.INSTANCE.initialise(settings, extraArgs);
            started = true;
        } catch (Exception e) {
            throw new TestAbortedException("Failed to initialise CEF for Swing tests", e);
        }
    }

    static <T> T onSwingThread(Callable<T> task) throws Exception {
        boolean isEdt;
        try {
            isEdt = SwingUtilities.isEventDispatchThread();
        } catch (java.awt.AWTError e) {
            throw new TestAbortedException("AWT Window Server is not available: " + e.getMessage(), e);
        }
        if (isEdt) {
            return task.call();
        }
        AtomicReference<T> result = new AtomicReference<>();
        AtomicReference<Throwable> error = new AtomicReference<>();
        CountDownLatch latch = new CountDownLatch(1);
        SwingUtilities.invokeLater(() -> {
            try {
                result.set(task.call());
            } catch (Throwable t) {
                error.set(t);
            } finally {
                latch.countDown();
            }
        });
        if (OS.isMacOS()) {
            long deadline = System.currentTimeMillis() + 10_000;
            while (latch.getCount() > 0 && System.currentTimeMillis() < deadline) {
                if (Cef.INSTANCE.getState() == Cef.State.INITIALISED) {
                    Cef.INSTANCE.doMessageLoopWork();
                }
                Thread.sleep(5);
            }
            if (latch.getCount() > 0) {
                throw new TimeoutException("Timed out waiting for Swing EDT task");
            }
        } else {
            if (!latch.await(10, TimeUnit.SECONDS)) {
                throw new TimeoutException("Timed out waiting for Swing EDT task");
            }
        }
        if (error.get() != null) {
            throw new RuntimeException(error.get());
        }
        return result.get();
    }

    static void onSwingThread(Runnable task) throws Exception {
        onSwingThread(() -> {
            task.run();
            return null;
        });
    }

    static CefBrowserPanel createAttachedPanel() throws Exception {
        AtomicReference<CefBrowserPanel> panelRef = new AtomicReference<>();
        AtomicReference<PanelState> stateRef = new AtomicReference<>();

        onSwingThread(() -> {
            CefBrowserPanel panel = new CefBrowserPanel();
            PanelState state = new PanelState();
            STATES.put(panel, state);
            panelRef.set(panel);
            stateRef.set(state);

            JFrame frame = new JFrame("cef4j Swing Test");
            frame.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
            frame.setLayout(new BorderLayout());
            frame.add(panel, BorderLayout.CENTER);
            frame.setSize(800, 600);
            frame.setVisible(true);
            FRAMES.add(frame);

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
                                b.getHost().ifPresent(host -> host.setFocus(true));
                                panel.requestFocusInWindow();
                                state.browserReady.countDown();
                            });
                        }

                        @Override
                        public void onBeforeClose(CefBrowser b) {
                            if (panel.getBrowser() != null && panel.getBrowser().isSame(b)) {
                                panel.setBrowser(null);
                            }
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
                            state.canGoBack = canGoBack;
                            state.canGoForward = canGoForward;
                            if (!isLoading) {
                                state.loadEnded = true;
                            }
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

                        @Override
                        public boolean onCursorChange(
                                CefBrowser b,
                                long cursor,
                                @Nonnull CefCursorType type,
                                CefCursorInfo customCursorInfo) {
                            java.awt.Cursor awtCursor = panel.mapCursor(type);
                            SwingUtilities.invokeLater(() -> panel.setCursor(awtCursor));
                            return true;
                        }
                    });
                }
            };

            CefWindowInfo windowInfo = Cef.createWindowlessInfo(
                    new CefRect(0, 0, Math.max(1, panel.getWidth()), Math.max(1, panel.getHeight())));
            CefBrowserSettings.Mutable browserSettings = new CefBrowserSettings.Mutable();
            browserSettings.windowlessFrameRate = 60;
            CefBrowserHost.createBrowser(windowInfo, client, "", browserSettings.toImmutable(), null, null);
        });

        CefBrowserPanel panel = panelRef.get();
        PanelState state = stateRef.get();
        if (OS.isMacOS()) {
            long deadline = System.currentTimeMillis() + 10_000;
            while (state.browserReady.getCount() > 0 && System.currentTimeMillis() < deadline) {
                Cef.INSTANCE.doMessageLoopWork();
                Thread.sleep(5);
            }
            if (state.browserReady.getCount() > 0) {
                throw new TimeoutException("Timed out waiting for CEF browser creation");
            }
        } else {
            if (!state.browserReady.await(10, TimeUnit.SECONDS)) {
                throw new TimeoutException("Timed out waiting for CEF browser creation");
            }
        }
        return panel;
    }

    static CefBrowserPanel createAttachedPanelWithClient(CefClient client) throws Exception {
        AtomicReference<CefBrowserPanel> panelRef = new AtomicReference<>();
        PanelState state = new PanelState();

        onSwingThread(() -> {
            CefBrowserPanel panel = new CefBrowserPanel();
            STATES.put(panel, state);
            panelRef.set(panel);

            JFrame frame = new JFrame("cef4j Swing Test");
            frame.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
            frame.setLayout(new BorderLayout());
            frame.add(panel, BorderLayout.CENTER);
            frame.setSize(800, 600);
            frame.setVisible(true);
            FRAMES.add(frame);

            CefWindowInfo windowInfo = Cef.createWindowlessInfo(
                    new CefRect(0, 0, Math.max(1, panel.getWidth()), Math.max(1, panel.getHeight())));
            CefBrowserSettings.Mutable browserSettings = new CefBrowserSettings.Mutable();
            browserSettings.windowlessFrameRate = 60;
            CefBrowserHost.createBrowser(windowInfo, client, "", browserSettings.toImmutable(), null, null);
        });

        CefBrowserPanel panel = panelRef.get();
        if (OS.isMacOS()) {
            long deadline = System.currentTimeMillis() + 10_000;
            while (state.browserReady.getCount() > 0 && System.currentTimeMillis() < deadline) {
                Cef.INSTANCE.doMessageLoopWork();
                Thread.sleep(5);
            }
            if (state.browserReady.getCount() > 0) {
                throw new TimeoutException("Timed out waiting for CEF browser creation");
            }
        } else {
            if (!state.browserReady.await(10, TimeUnit.SECONDS)) {
                throw new TimeoutException("Timed out waiting for CEF browser creation");
            }
        }
        return panel;
    }

    static void closeFrames() throws Exception {
        onSwingThread(() -> {
            for (JFrame frame : FRAMES) {
                frame.dispose();
            }
            FRAMES.clear();
        });
        STATES.clear();
    }

    static void loadUrl(CefBrowserPanel panel, String url) {
        CefBrowser b = panel.getBrowser();
        if (b != null) {
            b.getMainFrame().ifPresent(frame -> frame.loadUrl(url));
        }
    }

    static void loadContent(CefBrowserPanel panel, String html) {
        CefBrowser b = panel.getBrowser();
        if (b != null) {
            b.getMainFrame()
                    .ifPresent(frame -> frame.loadUrl("data:text/html;charset=utf-8,"
                            + java.net.URLEncoder.encode(html, StandardCharsets.UTF_8)
                                    .replace("+", "%20")));
        }
    }

    static void executeJavaScript(CefBrowserPanel panel, String script) {
        CefBrowser b = panel.getBrowser();
        if (b != null) {
            b.getMainFrame().ifPresent(frame -> frame.executeJavaScript(script, "", 0));
        }
    }

    static String getTitle(CefBrowserPanel panel) {
        PanelState state = STATES.get(panel);
        return state != null ? state.title : "";
    }

    static String getLocation(CefBrowserPanel panel) {
        PanelState state = STATES.get(panel);
        return state != null ? state.location : "";
    }

    static boolean isLoading(CefBrowserPanel panel) {
        PanelState state = STATES.get(panel);
        return state != null && state.loading;
    }

    static boolean waitForLoadEnd(CefBrowserPanel panel, long timeoutMillis) throws Exception {
        PanelState state = STATES.get(panel);
        if (state == null) return false;
        state.loadEnded = false;
        return waitUntil(() -> state.loadEnded, timeoutMillis);
    }

    static boolean waitUntil(BooleanSupplier condition, long timeoutMillis) throws Exception {
        long deadline = System.nanoTime() + TimeUnit.MILLISECONDS.toNanos(timeoutMillis);
        while (System.nanoTime() < deadline) {
            if (condition.getAsBoolean()) return true;
            if (OS.isMacOS() && Cef.INSTANCE.getState() == Cef.State.INITIALISED) {
                Cef.INSTANCE.doMessageLoopWork();
                Thread.sleep(5);
            } else {
                Thread.sleep(20);
            }
        }
        return condition.getAsBoolean();
    }

    static LocalTestServer startServer(Map<String, String> routes) throws IOException {
        Map<String, ResponseSpec> specs = new LinkedHashMap<>();
        for (Map.Entry<String, String> entry : routes.entrySet()) {
            specs.put(entry.getKey(), ResponseSpec.html(entry.getValue()));
        }
        return startServerWithResponses(specs);
    }

    static LocalTestServer startServerWithResponses(Map<String, ResponseSpec> routes) throws IOException {
        HttpServer server = HttpServer.create(new InetSocketAddress("127.0.0.1", 0), 0);
        for (Map.Entry<String, ResponseSpec> entry : routes.entrySet()) {
            server.createContext(entry.getKey(), exchange -> respond(exchange, entry.getValue()));
        }
        server.start();
        return new LocalTestServer(server);
    }

    private static void respond(HttpExchange exchange, ResponseSpec response) throws IOException {
        if (response.delayMillis > 0) {
            try {
                Thread.sleep(response.delayMillis);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
                throw new IOException("Interrupted while delaying response", e);
            }
        }
        for (Map.Entry<String, String> header : response.headers.entrySet()) {
            exchange.getResponseHeaders().set(header.getKey(), header.getValue());
        }
        byte[] bytes = response.body.getBytes(StandardCharsets.UTF_8);
        exchange.sendResponseHeaders(response.statusCode, bytes.length);
        try (OutputStream out = exchange.getResponseBody()) {
            out.write(bytes);
        }
    }

    static final class ResponseSpec {
        private final int statusCode;
        private final Map<String, String> headers;
        private final String body;
        private final long delayMillis;

        private ResponseSpec(int statusCode, Map<String, String> headers, String body, long delayMillis) {
            this.statusCode = statusCode;
            this.headers = headers;
            this.body = body;
            this.delayMillis = delayMillis;
        }

        static ResponseSpec html(String body) {
            return new ResponseSpec(200, Map.of("Content-Type", "text/html; charset=UTF-8"), body, 0);
        }

        static ResponseSpec html(String body, long delayMillis) {
            return new ResponseSpec(200, Map.of("Content-Type", "text/html; charset=UTF-8"), body, delayMillis);
        }

        static ResponseSpec redirect(String location) {
            return new ResponseSpec(302, Map.of("Location", location), "", 0);
        }
    }

    static final class LocalTestServer implements AutoCloseable {
        private final HttpServer server;

        LocalTestServer(HttpServer server) {
            this.server = server;
        }

        String url(String path) {
            return "http://127.0.0.1:" + server.getAddress().getPort() + path;
        }

        @Override
        public void close() {
            server.stop(0);
        }
    }
}
