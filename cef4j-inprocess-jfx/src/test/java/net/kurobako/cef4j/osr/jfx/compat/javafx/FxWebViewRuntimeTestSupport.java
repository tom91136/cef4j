package net.kurobako.cef4j.osr.jfx.compat.javafx;

import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpServer;
import java.io.IOException;
import java.io.OutputStream;
import java.net.InetSocketAddress;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.concurrent.Callable;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;
import java.util.concurrent.atomic.AtomicReference;
import java.util.function.BooleanSupplier;
import javafx.application.Platform;
import javafx.concurrent.Worker;
import javafx.geometry.Bounds;
import javafx.geometry.Point2D;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Labeled;
import javafx.scene.input.Clipboard;
import javafx.scene.input.ClipboardContent;
import javafx.scene.input.ContextMenuEvent;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.input.PickResult;
import javafx.scene.input.ScrollEvent;
import javafx.scene.layout.StackPane;
import javafx.scene.text.Text;
import javafx.scene.web.WebEngine;
import javafx.scene.web.WebView;
import javafx.stage.Stage;
import javafx.stage.Window;
import javax.annotation.Nullable;
import net.kurobako.cef4j.Cef;
import org.junit.jupiter.api.Assumptions;
import org.opentest4j.TestAbortedException;

final class FxWebViewRuntimeTestSupport {
    private static volatile boolean started;

    @SuppressWarnings("NullAway.Init")
    private static volatile Path cefCachePath;

    private static final CopyOnWriteArrayList<Stage> STAGES = new CopyOnWriteArrayList<>();

    private FxWebViewRuntimeTestSupport() {}

    static void ensureStarted() throws Exception {
        if (started) return;

        Assumptions.assumeTrue(
                System.getenv("DISPLAY") != null || System.getenv("WAYLAND_DISPLAY") != null,
                "Runtime WebView compatibility tests require a display server; set DISPLAY or run with: xvfb-run -a mvn test");
        CountDownLatch latch = new CountDownLatch(1);
        AtomicReference<Throwable> error = new AtomicReference<>();
        try {
            Platform.startup(() -> {
                try {
                    Platform.setImplicitExit(false);
                    started = true;
                } catch (Throwable t) {
                    error.set(t);
                } finally {
                    latch.countDown();
                }
            });
        } catch (UnsupportedOperationException unsupported) {
            throw new TestAbortedException(
                    "Runtime WebView compatibility tests require a working display server; run them under Xvfb or Wayland.",
                    unsupported);
        } catch (IllegalStateException alreadyStarted) {
            started = true;
            return;
        }
        if (!latch.await(10, TimeUnit.SECONDS)) {
            throw new TimeoutException("Timed out starting JavaFX platform");
        }
        if (error.get() != null) {
            throw new RuntimeException("Failed to start JavaFX platform", error.get());
        }
        postStartup();
    }

    static void postStartup() {
        if (!isCefCompatHarness()) return;
        if (Cef.INSTANCE.state() == Cef.State.INITIALISED) return;
        initialiseCef();
    }

    static void shutdownCefHarness() {
        if (!isCefCompatHarness()) return;
        Cef.INSTANCE.terminate();
    }

    private static void initialiseCef() {
        Cef.LaunchArgs launch = Cef.osrLaunchArgs();
        Path cacheDir = cefCachePath;
        if (cacheDir == null) {
            cacheDir = Path.of(System.getProperty("java.io.tmpdir"), "cef4j-jfx-cache");
        }
        try {
            Files.createDirectories(cacheDir);
        } catch (IOException e) {
            // Best-effort; CEF will fail loudly later if the cache dir is unusable.
        }
        launch.settings().cachePath = cacheDir.toAbsolutePath().toString();
        Cef.INSTANCE.initialise(launch.settings(), launch.args());
    }

    static void setCefCachePath(Path cachePath) {
        cefCachePath = cachePath;
    }

    static Path getCefCachePath() {
        return cefCachePath;
    }

    @Nullable
    static <T> T onFxThread(Callable<T> task) throws Exception {
        ensureStarted();
        if (Platform.isFxApplicationThread()) {
            return task.call();
        }
        CountDownLatch latch = new CountDownLatch(1);
        AtomicReference<T> result = new AtomicReference<>();
        AtomicReference<Throwable> error = new AtomicReference<>();
        Platform.runLater(() -> {
            try {
                result.set(task.call());
            } catch (Throwable t) {
                error.set(t);
            } finally {
                latch.countDown();
            }
        });
        if (!latch.await(10, TimeUnit.SECONDS)) {
            throw new TimeoutException("Timed out waiting for JavaFX task");
        }
        if (error.get() != null) {
            throw new RuntimeException(error.get());
        }
        return result.get();
    }

    static void onFxThread(Runnable task) throws Exception {
        onFxThread(() -> {
            task.run();
            return null;
        });
    }

    static WebView createAttachedWebView() throws Exception {
        return Objects.requireNonNull(
                onFxThread(() -> {
                    WebView view = new WebView();
                    Stage stage = new Stage();
                    stage.setScene(new Scene(new StackPane(view), 800, 600));
                    stage.setOnHidden(event -> STAGES.remove(stage));
                    stage.show();
                    stage.toFront();
                    stage.requestFocus();
                    view.requestFocus();
                    STAGES.add(stage);
                    return view;
                }),
                "view");
    }

    static void closeStages() throws Exception {
        onFxThread(() -> {
            List<Window> windows = new ArrayList<>(Window.getWindows());
            for (Window window : windows) {
                if (window.isShowing()) {
                    window.hide();
                }
            }
            for (Stage stage : STAGES) {
                stage.close();
            }
            STAGES.clear();
            ClipboardContent content = new ClipboardContent();
            content.putString("");
            Clipboard.getSystemClipboard().setContent(content);
        });
    }

    static boolean waitUntil(BooleanSupplier condition, long timeoutMillis) throws Exception {
        long deadline = System.nanoTime() + TimeUnit.MILLISECONDS.toNanos(timeoutMillis);
        while (System.nanoTime() < deadline) {
            if (condition.getAsBoolean()) return true;
            Thread.sleep(20);
        }
        return condition.getAsBoolean();
    }

    static boolean waitUntilOnFx(Callable<Boolean> condition, long timeoutMillis) throws Exception {
        return waitUntil(
                () -> {
                    try {
                        return Boolean.TRUE.equals(onFxThread(condition));
                    } catch (Exception e) {
                        throw new RuntimeException(e);
                    }
                },
                timeoutMillis);
    }

    static boolean waitUntilFiringOnFx(Callable<Boolean> condition, long timeoutMillis, Runnable fxAction)
            throws Exception {
        long deadline = System.nanoTime() + TimeUnit.MILLISECONDS.toNanos(timeoutMillis);
        while (System.nanoTime() < deadline) {
            onFxThread(fxAction);
            long pollEnd = System.nanoTime() + TimeUnit.MILLISECONDS.toNanos(100);
            while (System.nanoTime() < pollEnd) {
                if (Boolean.TRUE.equals(onFxThread(condition))) return true;
                Thread.sleep(10);
            }
        }
        return Boolean.TRUE.equals(onFxThread(condition));
    }

    static boolean waitForWorkerState(WebEngine engine, Worker.State state, long timeoutMillis) throws Exception {
        return waitUntilOnFx(() -> engine.getLoadWorker().getState() == state, timeoutMillis);
    }

    static void setClipboardText(String text) throws Exception {
        String value = Objects.requireNonNullElse(text, "");
        onFxThread(() -> {
            ClipboardContent content = new ClipboardContent();
            content.putString(value);
            Clipboard.getSystemClipboard().setContent(content);
        });
    }

    @Nullable
    static String getClipboardText() throws Exception {
        return onFxThread(() -> Clipboard.getSystemClipboard().getString());
    }

    static boolean isCefCompatHarness() {
        return FxWebViewRuntimeTestSupport.class.getPackageName().endsWith(".compat.cef");
    }

    static int cefApiVersion() {
        String apiVersion = System.getProperty("cef.api.version");
        if (apiVersion != null && !apiVersion.isBlank()) {
            return Integer.parseInt(apiVersion.trim());
        }
        String cefVersion = System.getProperty("cef.version");
        if (cefVersion != null && !cefVersion.isBlank()) {
            int plus = cefVersion.indexOf('+');
            String major = plus >= 0 ? cefVersion.substring(0, plus) : cefVersion;
            int dot = major.indexOf('.');
            if (dot >= 0) major = major.substring(0, dot);
            return Integer.parseInt(major);
        }
        return 146;
    }

    static void assumeCefCompatStressSuiteSupported(String suiteName) {
        Assumptions.assumeTrue(
                !isCefCompatHarness() || cefApiVersion() > 116,
                suiteName + " crashes the native runtime on CEF <= 116");
    }

    static void leftClick(WebView view, double x, double y) throws Exception {
        click(view, x, y, MouseButton.PRIMARY);
    }

    static void rightClick(WebView view, double x, double y) throws Exception {
        click(view, x, y, MouseButton.SECONDARY);
    }

    static void dragSelect(WebView view, double startX, double startY, double endX, double endY) throws Exception {
        onFxThread(() -> {
            focusView(view);
            Point2D start = screenPoint(view, startX, startY);
            Point2D end = screenPoint(view, endX, endY);
            fireMouseEvent(
                    view, MouseEvent.MOUSE_MOVED, startX, startY, start.getX(), start.getY(), MouseButton.NONE, false);
            fireMouseEvent(
                    view,
                    MouseEvent.MOUSE_PRESSED,
                    startX,
                    startY,
                    start.getX(),
                    start.getY(),
                    MouseButton.PRIMARY,
                    false);
            int steps = 8;
            for (int i = 1; i <= steps; i++) {
                double t = i / (double) steps;
                double x = startX + ((endX - startX) * t);
                double y = startY + ((endY - startY) * t);
                Point2D point = screenPoint(view, x, y);
                fireMouseEvent(
                        view, MouseEvent.MOUSE_DRAGGED, x, y, point.getX(), point.getY(), MouseButton.PRIMARY, false);
            }
            fireMouseEvent(
                    view, MouseEvent.MOUSE_RELEASED, endX, endY, end.getX(), end.getY(), MouseButton.PRIMARY, false);
        });
        Thread.sleep(100);
    }

    static void invokeShortcut(WebView view, KeyCode key) throws Exception {
        onFxThread(() -> {
            focusView(view);
            fireKeyEvent(view, KeyEvent.KEY_PRESSED, "", KeyCode.CONTROL, false, true);
            fireKeyEvent(view, KeyEvent.KEY_PRESSED, "", key, false, true);
            fireKeyEvent(view, KeyEvent.KEY_RELEASED, "", key, false, true);
            fireKeyEvent(view, KeyEvent.KEY_RELEASED, "", KeyCode.CONTROL, false, false);
        });
        Thread.sleep(75);
    }

    static String title(WebView view) throws Exception {
        String value = onFxThread(() -> view.getEngine().getTitle());
        return Objects.requireNonNullElse(value, "");
    }

    @SuppressWarnings("deprecation") // CefWebEngine.executeScript is deprecated but exercised for JFX parity
    static String evalToString(WebView view, String script) throws Exception {
        Object value = onFxThread(() -> view.getEngine().executeScript(script));
        return value != null ? value.toString() : "";
    }

    static void fireScroll(WebView view, double x, double y, double deltaX, double deltaY) {
        view.fireEvent(new ScrollEvent(
                ScrollEvent.SCROLL,
                x,
                y,
                x,
                y,
                false,
                false,
                false,
                false,
                false,
                false,
                deltaX,
                deltaY,
                deltaX,
                deltaY,
                ScrollEvent.HorizontalTextScrollUnits.NONE,
                0,
                ScrollEvent.VerticalTextScrollUnits.NONE,
                0,
                0,
                new PickResult(view, x, y)));
    }

    static void typeText(WebView view, KeyCode keyCode, String typedText) throws Exception {
        onFxThread(() -> {
            view.fireEvent(
                    new KeyEvent(KeyEvent.KEY_PRESSED, "", keyCode.getName(), keyCode, false, false, false, false));
            view.fireEvent(new KeyEvent(
                    KeyEvent.KEY_TYPED, typedText, typedText, KeyCode.UNDEFINED, false, false, false, false));
            view.fireEvent(
                    new KeyEvent(KeyEvent.KEY_RELEASED, "", keyCode.getName(), keyCode, false, false, false, false));
        });
        Thread.sleep(75);
    }

    static boolean tryInvokeContextMenuItem(
            WebView view, double x, double y, String itemText, int maxAttempts, long visibleTimeoutMillis)
            throws Exception {
        for (int attempt = 0; attempt < maxAttempts; attempt++) {
            rightClick(view, x, y);
            if (!waitUntilOnFx(() -> findMenuItemNode(itemText) != null, visibleTimeoutMillis)) {
                continue;
            }
            Boolean activated = onFxThread(() -> activateMenuItem(itemText));
            if (Boolean.TRUE.equals(activated)) {
                Thread.sleep(75);
                return true;
            }
        }
        return false;
    }

    static LocalTestServer startServer(Map<String, String> routes) throws IOException {
        Map<String, ResponseSpec> specs = new LinkedHashMap<>();
        for (Map.Entry<String, String> entry : routes.entrySet()) {
            specs.put(entry.getKey(), ResponseSpec.html(entry.getValue()));
        }
        return startServerWithResponses(specs);
    }

    static LocalTestServer startServerWithResponses(Map<String, ResponseSpec> routes) throws IOException {
        HttpServer server = HttpServer.create(new InetSocketAddress(java.net.InetAddress.getLoopbackAddress(), 0), 0);
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

    private static void click(WebView view, double x, double y, MouseButton button) throws Exception {
        onFxThread(() -> {
            Point2D point = screenPoint(view, x, y);
            focusView(view);
            fireMouseEvent(view, MouseEvent.MOUSE_MOVED, x, y, point.getX(), point.getY(), MouseButton.NONE, false);
            if (button == MouseButton.SECONDARY) {
                fireMouseEvent(view, MouseEvent.MOUSE_PRESSED, x, y, point.getX(), point.getY(), button, false);
                fireMouseEvent(view, MouseEvent.MOUSE_RELEASED, x, y, point.getX(), point.getY(), button, true);
                fireMouseEvent(view, MouseEvent.MOUSE_CLICKED, x, y, point.getX(), point.getY(), button, true);
                view.fireEvent(new ContextMenuEvent(
                        ContextMenuEvent.CONTEXT_MENU_REQUESTED,
                        x,
                        y,
                        point.getX(),
                        point.getY(),
                        false,
                        new PickResult(view, x, y)));
                return;
            }
            fireMouseEvent(view, MouseEvent.MOUSE_PRESSED, x, y, point.getX(), point.getY(), button, false);
            fireMouseEvent(view, MouseEvent.MOUSE_RELEASED, x, y, point.getX(), point.getY(), button, false);
            fireMouseEvent(view, MouseEvent.MOUSE_CLICKED, x, y, point.getX(), point.getY(), button, false);
        });
        Thread.sleep(75);
    }

    @Nullable
    private static Node findMenuItemNode(String itemText) {
        List<Window> windows = Window.getWindows();
        Node stageFallback = null;
        for (int i = windows.size() - 1; i >= 0; i--) {
            Window window = windows.get(i);
            if (!window.isShowing() || window.getScene() == null) continue;
            Node node = findNodeWithText(window.getScene().getRoot(), normalizeMenuText(itemText));
            if (node != null) {
                Node candidate = node.getParent() != null ? node.getParent() : node;
                if (!(window instanceof Stage)) {
                    return candidate;
                }
                if (stageFallback == null) {
                    stageFallback = candidate;
                }
            }
        }
        return stageFallback;
    }

    @Nullable
    private static Node findNodeWithText(Node node, String normalizedTarget) {
        if (!node.isVisible()) return null;
        String text = extractNodeText(node);
        if (text != null && normalizeMenuText(text).contains(normalizedTarget)) {
            return node;
        }
        if (node instanceof Parent) {
            for (Node child : ((Parent) node).getChildrenUnmodifiable()) {
                Node found = findNodeWithText(child, normalizedTarget);
                if (found != null) return found;
            }
        }
        return null;
    }

    @Nullable
    private static String extractNodeText(Node node) {
        if (node instanceof Labeled) {
            return ((Labeled) node).getText();
        }
        if (node instanceof Text) {
            return ((Text) node).getText();
        }
        return null;
    }

    private static String normalizeMenuText(@Nullable String text) {
        return text == null ? "" : text.replaceAll("[^A-Za-z]", "").toLowerCase(java.util.Locale.ROOT);
    }

    private static boolean activateMenuItem(String itemText) {
        Node node = findMenuItemNode(itemText);
        if (node == null) return false;
        Bounds localBounds = node.getBoundsInLocal();
        Bounds screenBounds = node.localToScreen(localBounds);
        if (screenBounds == null) return false;
        double localX = localBounds.getWidth() / 2.0;
        double localY = localBounds.getHeight() / 2.0;
        double screenX = screenBounds.getMinX() + screenBounds.getWidth() / 2.0;
        double screenY = screenBounds.getMinY() + screenBounds.getHeight() / 2.0;
        fireMouseEvent(node, MouseEvent.MOUSE_PRESSED, localX, localY, screenX, screenY, MouseButton.PRIMARY, false);
        fireMouseEvent(node, MouseEvent.MOUSE_RELEASED, localX, localY, screenX, screenY, MouseButton.PRIMARY, false);
        fireMouseEvent(node, MouseEvent.MOUSE_CLICKED, localX, localY, screenX, screenY, MouseButton.PRIMARY, false);
        return true;
    }

    private static void fireMouseEvent(
            Node node,
            javafx.event.EventType<MouseEvent> type,
            double x,
            double y,
            double screenX,
            double screenY,
            MouseButton button,
            boolean popupTrigger) {
        boolean buttonDown = type == MouseEvent.MOUSE_PRESSED || type == MouseEvent.MOUSE_DRAGGED;
        node.fireEvent(new MouseEvent(
                type,
                x,
                y,
                screenX,
                screenY,
                button,
                1,
                false,
                false,
                false,
                false,
                buttonDown && button == MouseButton.PRIMARY,
                buttonDown && button == MouseButton.MIDDLE,
                buttonDown && button == MouseButton.SECONDARY,
                false,
                popupTrigger,
                false,
                new PickResult(node, x, y)));
    }

    private static void fireKeyEvent(
            WebView view,
            javafx.event.EventType<KeyEvent> type,
            String character,
            KeyCode code,
            boolean shift,
            boolean control) {
        view.fireEvent(new KeyEvent(type, character, code.getName(), code, shift, control, false, false));
    }

    private static void focusView(WebView view) {
        if (view.getScene() == null || view.getScene().getWindow() == null) {
            view.requestFocus();
            return;
        }
        Window window = view.getScene().getWindow();
        if (window instanceof Stage) {
            ((Stage) window).toFront();
        }
        window.requestFocus();
        view.requestFocus();
    }

    private static Point2D screenPoint(WebView view, double x, double y) {
        Point2D point = view.localToScreen(x, y);
        if (point == null) throw new IllegalStateException("View is not on screen");
        return point;
    }
}
