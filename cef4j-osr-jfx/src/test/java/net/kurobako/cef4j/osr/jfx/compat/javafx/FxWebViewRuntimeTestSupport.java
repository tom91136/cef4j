package net.kurobako.cef4j.osr.jfx.compat.javafx;

import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpServer;
import java.io.IOException;
import java.io.OutputStream;
import java.net.InetSocketAddress;
import java.nio.charset.StandardCharsets;
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
import javafx.scene.Scene;
import javafx.scene.layout.StackPane;
import javafx.scene.web.WebEngine;
import javafx.scene.web.WebView;
import javafx.stage.Stage;
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
            stage.show();
            STAGES.add(stage);
            return view;
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

    static LocalTestServer startServer(Map<String, String> routes) throws IOException {
        HttpServer server = HttpServer.create(new InetSocketAddress("127.0.0.1", 0), 0);
        for (Map.Entry<String, String> entry : routes.entrySet()) {
            server.createContext(entry.getKey(), exchange -> respondHtml(exchange, entry.getValue()));
        }
        server.start();
        return new LocalTestServer(server);
    }

    private static void respondHtml(HttpExchange exchange, String body) throws IOException {
        byte[] bytes = body.getBytes(StandardCharsets.UTF_8);
        exchange.getResponseHeaders().set("Content-Type", "text/html; charset=UTF-8");
        exchange.sendResponseHeaders(200, bytes.length);
        try (OutputStream out = exchange.getResponseBody()) {
            out.write(bytes);
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
