package net.kurobako.cef4j.remote;

import static org.assertj.core.api.Assertions.assertThat;

import java.nio.file.Path;
import java.time.Duration;
import java.util.Map;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.RejectedExecutionException;
import java.util.concurrent.atomic.AtomicReference;
import net.kurobako.cef4j.ipc.session.middleware.CefSessionMiddleware;
import org.junit.jupiter.api.Test;

class RuntimeServerBrowserRuntimeFactoryTest {
    @Test
    void submitsStartupToSuppliedExecutor() {
        AtomicReference<Runnable> submitted = new AtomicReference<>();
        RuntimeServerBrowserRuntimeFactory factory = new RuntimeServerBrowserRuntimeFactory(
                Path.of("missing-runtime-server"),
                "local",
                "tcp://127.0.0.1:0",
                "inline",
                Duration.ofSeconds(1),
                Map.of(),
                CefSessionMiddleware.identity(),
                submitted::set);

        CompletableFuture<? extends RemoteBrowserRuntime> runtime = factory.create();

        assertThat(submitted).doesNotHaveNullValue();
        assertThat(runtime).isNotDone();
    }

    @Test
    void reportsSuppliedExecutorRejectionThroughTheFuture() {
        RejectedExecutionException rejection = new RejectedExecutionException("stopped");
        RuntimeServerBrowserRuntimeFactory factory = new RuntimeServerBrowserRuntimeFactory(
                Path.of("missing-runtime-server"),
                "local",
                "tcp://127.0.0.1:0",
                "inline",
                Duration.ofSeconds(1),
                Map.of(),
                CefSessionMiddleware.identity(),
                command -> {
                    throw rejection;
                });

        assertThat(factory.create()).isCompletedExceptionally();
    }
}
