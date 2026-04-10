package net.kurobako.cef4j.osr.jfx.compat.javafx;

import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpServer;
import java.io.IOException;
import java.io.OutputStream;
import java.net.InetSocketAddress;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
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
import javafx.scene.layout.StackPane;
import javafx.scene.text.Text;
import javafx.scene.web.WebEngine;
import javafx.scene.web.WebView;
import javafx.stage.Stage;
import javafx.stage.Window;
import org.junit.jupiter.api.Assumptions;
import org.opentest4j.TestAbortedException;

final class FxWebViewRuntimeTestSupport {
    private static volatile boolean started;
    private static final CopyOnWriteArrayList<Stage> STAGES = new CopyOnWriteArrayList<>();

    private FxWebViewRuntimeTestSupport() {}

    static void ensureStarted() throws Exception {
        if (started) return;

        Assumptions.assumeTrue(
                System.getenv("DISPLAY") != null || System.getenv("WAYLAND_DISPLAY") != null,
                "Runtime WebView compatibility tests require a display server; run them under Xvfb or Wayland.");
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
    }

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
        return onFxThread(() -> {
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
        });
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

    static boolean waitForWorkerState(WebEngine engine, Worker.State state, long timeoutMillis) throws Exception {
        return waitUntilOnFx(() -> engine.getLoadWorker().getState() == state, timeoutMillis);
    }

    static void setClipboardText(String text) throws Exception {
        onFxThread(() -> {
            ClipboardContent content = new ClipboardContent();
            content.putString(text);
            Clipboard.getSystemClipboard().setContent(content);
        });
    }

    static String getClipboardText() throws Exception {
        return onFxThread(() -> Clipboard.getSystemClipboard().getString());
    }

    static void leftClick(WebView view, double x, double y) throws Exception {
        click(view, x, y, MouseButton.PRIMARY);
    }

    static void rightClick(WebView view, double x, double y) throws Exception {
        click(view, x, y, MouseButton.SECONDARY);
    }

    static void invokeShortcut(WebView view, KeyCode key) throws Exception {
        onFxThread(() -> {
            if (view.getScene() != null && view.getScene().getWindow() != null) {
                if (view.getScene().getWindow() instanceof Stage) {
                    ((Stage) view.getScene().getWindow()).toFront();
                }
                view.getScene().getWindow().requestFocus();
            }
            view.requestFocus();
            fireKeyEvent(view, KeyEvent.KEY_PRESSED, "", KeyCode.CONTROL, false, true);
            fireKeyEvent(view, KeyEvent.KEY_PRESSED, "", key, false, true);
            fireKeyEvent(view, KeyEvent.KEY_RELEASED, "", key, false, true);
            fireKeyEvent(view, KeyEvent.KEY_RELEASED, "", KeyCode.CONTROL, false, false);
        });
        Thread.sleep(75);
    }

    static void invokeContextMenuItem(WebView view, double x, double y, String itemText) throws Exception {
        TimeoutException lastError = null;
        for (int attempt = 0; attempt < 3; attempt++) {
            rightClick(view, x, y);
            try {
                assertMenuItemVisible(itemText, 1_000);
                Boolean activated = onFxThread(() -> activateMenuItem(itemText));
                if (Boolean.TRUE.equals(activated)) {
                    Thread.sleep(75);
                    return;
                }
            } catch (TimeoutException e) {
                lastError = e;
            }
        }
        throw lastError != null ? lastError : new TimeoutException("Context menu item not found: " + itemText);
    }

    static void assertMenuItemVisible(String itemText, long timeoutMillis) throws Exception {
        if (!waitUntilOnFx(() -> findMenuItemNode(itemText) != null, timeoutMillis)) {
            throw new TimeoutException("Timed out waiting for context menu item: " + itemText);
        }
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

    private static void click(WebView view, double x, double y, MouseButton button) throws Exception {
        onFxThread(() -> {
            Point2D point = screenPoint(view, x, y);
            if (view.getScene() != null && view.getScene().getWindow() != null) {
                if (view.getScene().getWindow() instanceof Stage) {
                    ((Stage) view.getScene().getWindow()).toFront();
                }
                view.getScene().getWindow().requestFocus();
            }
            view.requestFocus();
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

    private static Node findMenuItemNode(String itemText) {
        List<Window> windows = Window.getWindows();
        for (int i = windows.size() - 1; i >= 0; i--) {
            Window window = windows.get(i);
            if (!window.isShowing() || window.getScene() == null || window instanceof Stage) continue;
            Node node = findNodeWithText(window.getScene().getRoot(), normalizeMenuText(itemText));
            if (node != null) {
                return node.getParent() != null ? node.getParent() : node;
            }
        }
        return null;
    }

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

    private static String extractNodeText(Node node) {
        if (node instanceof Labeled) {
            return ((Labeled) node).getText();
        }
        if (node instanceof Text) {
            return ((Text) node).getText();
        }
        return null;
    }

    private static String normalizeMenuText(String text) {
        return text == null ? "" : text.replaceAll("[^A-Za-z]", "").toLowerCase();
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
                button == MouseButton.PRIMARY,
                button == MouseButton.MIDDLE,
                button == MouseButton.SECONDARY,
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

    private static Point2D screenPoint(WebView view, double x, double y) {
        Point2D point = view.localToScreen(x, y);
        if (point == null) throw new IllegalStateException("View is not on screen");
        return point;
    }
}
