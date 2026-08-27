package net.kurobako.cef4j.ipc.session.process;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.file.Files;
import java.nio.file.Path;
import java.time.Duration;
import java.util.Locale;
import java.util.Map;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicReference;
import java.util.function.Consumer;
import javax.annotation.Nonnull;
import net.kurobako.cef4j.ipc.session.CefMessageDecoder;
import net.kurobako.cef4j.ipc.session.CefMessageEncoder;
import net.kurobako.cef4j.ipc.session.CefMessageView;
import net.kurobako.cef4j.ipc.session.CefSession;
import net.kurobako.cef4j.ipc.transport.CefTransportException;
import net.kurobako.cef4j.test.TestDeadline;
import net.kurobako.cef4j.test.TestGate;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Timeout;
import org.junit.jupiter.api.io.TempDir;

@Timeout(120)
final class RuntimeServerSupervisorTest {
    private static final Duration TEST_TIMEOUT = Duration.ofSeconds(60);

    @Test
    void closeRejectsNewStartsAndListenersWithoutExecutorLeak() {
        RuntimeServerSupervisor supervisor =
                new RuntimeServerSupervisor(RuntimeServerSupervisor.Configuration.defaults(Path.of("missing")));
        supervisor.close();

        CompletableFuture<RuntimeServerSupervisor.Connection> started = supervisor.start();

        assertThat(started).isCompletedExceptionally();
        assertThatThrownBy(() -> supervisor.onConnection(ignored -> {}))
                .isInstanceOf(IllegalStateException.class)
                .hasMessage("supervisor is closed");
    }

    @Test
    void terminatesSpawnedProcessWhenTransportConnectionFails(@TempDir Path temporary) throws Exception {
        Path pidFile = temporary.resolve("server.pid");
        RuntimeServerSupervisor.Configuration configuration = new RuntimeServerSupervisor.Configuration(
                launcher(temporary),
                "missing-transport",
                "tcp://127.0.0.1:0",
                "shared-file",
                Duration.ofSeconds(5),
                Duration.ofSeconds(2),
                Duration.ofMillis(25),
                Duration.ofMillis(100),
                0,
                Map.of("CEF4J_STUB_PID_FILE", pidFile.toString()));
        try (RuntimeServerSupervisor supervisor = new RuntimeServerSupervisor(configuration)) {
            TestDeadline deadline = TestDeadline.after(TEST_TIMEOUT);
            assertThatThrownBy(() -> deadline.await(supervisor.start(), "failed transport startup"))
                    .hasRootCauseInstanceOf(CefTransportException.class);
        }

        long pid = Long.parseLong(Files.readString(pidFile));
        TestDeadline.after(Duration.ofSeconds(5))
                .until(
                        () -> !ProcessHandle.of(pid).map(ProcessHandle::isAlive).orElse(false),
                        Duration.ofMillis(10),
                        "spawned process exit");
        assertThat(ProcessHandle.of(pid).map(ProcessHandle::isAlive).orElse(false))
                .isFalse();
    }

    @Test
    void replacesGenerationWhenTransportDiesBeforeProcess(@TempDir Path temporary) throws Exception {
        RuntimeServerSupervisor.Configuration configuration = new RuntimeServerSupervisor.Configuration(
                launcher(temporary),
                "zmq",
                "tcp://127.0.0.1:0",
                "shared-file",
                Duration.ofSeconds(5),
                Duration.ofSeconds(2),
                Duration.ofMillis(25),
                Duration.ofMillis(100),
                3,
                Map.of("CEF4J_STUB_DROP_AFTER_MS", "100"));
        LinkedBlockingQueue<RuntimeServerSupervisor.Connection> generations = new LinkedBlockingQueue<>();
        try (RuntimeServerSupervisor supervisor = new RuntimeServerSupervisor(configuration);
                AutoCloseable registration = supervisor.onConnection(generations::offer)) {
            TestDeadline deadline = TestDeadline.after(TEST_TIMEOUT);
            assertThat(registration).isNotNull();
            RuntimeServerSupervisor.Connection first = deadline.await(supervisor.start(), "first generation startup");
            assertThat(poll(generations, deadline, "first generation delivery")).isSameAs(first);
            RuntimeServerSupervisor.Connection second = poll(generations, deadline, "replacement generation delivery");
            assertThat(second).isNotNull();
            assertThat(second.pid()).isNotEqualTo(first.pid());
        }
    }

    @Test
    void rapidPostHandshakeCrashLoopExhaustsRecoveryBudget(@TempDir Path temporary) throws Exception {
        RuntimeServerSupervisor.Configuration configuration = new RuntimeServerSupervisor.Configuration(
                launcher(temporary),
                "zmq",
                "tcp://127.0.0.1:0",
                "shared-file",
                Duration.ofSeconds(5),
                Duration.ofSeconds(2),
                Duration.ofMillis(10),
                Duration.ofSeconds(5),
                1,
                Map.of("CEF4J_STUB_EXIT_AFTER_MS", "20"));
        try (RuntimeServerSupervisor supervisor = new RuntimeServerSupervisor(configuration)) {
            TestDeadline deadline = TestDeadline.after(TEST_TIMEOUT);
            deadline.await(supervisor.start(), "crashing generation startup");
            deadline.until(
                    () -> supervisor.state() == RuntimeServerSupervisor.State.FAILED,
                    Duration.ofMillis(10),
                    "recovery budget exhaustion");
            assertThat(supervisor.state()).isEqualTo(RuntimeServerSupervisor.State.FAILED);
            assertThat(supervisor.current()).isEmpty();
        }
    }

    @Test
    void replacesCrashedGenerationWithoutReusingItsSession(@TempDir Path temporary) throws Exception {
        Path launcher = launcher(temporary);
        AtomicInteger middlewareApplications = new AtomicInteger();
        RuntimeServerSupervisor.Configuration configuration = new RuntimeServerSupervisor.Configuration(
                        launcher,
                        "zmq",
                        "tcp://127.0.0.1:0",
                        "shared-file",
                        Duration.ofSeconds(5),
                        Duration.ofSeconds(2),
                        Duration.ofMillis(25),
                        Duration.ofMillis(100),
                        3,
                        Map.of())
                .withSessionMiddleware(delegate -> {
                    middlewareApplications.incrementAndGet();
                    return delegate;
                });
        LinkedBlockingQueue<RuntimeServerSupervisor.Connection> generations = new LinkedBlockingQueue<>();
        try (RuntimeServerSupervisor supervisor = new RuntimeServerSupervisor(configuration);
                AutoCloseable registration = supervisor.onConnection(generations::offer)) {
            TestDeadline deadline = TestDeadline.after(TEST_TIMEOUT);
            assertThat(registration).isNotNull();
            RuntimeServerSupervisor.Connection first = deadline.await(supervisor.start(), "first generation startup");
            assertThat(poll(generations, deadline, "first generation delivery")).isSameAs(first);

            supervisor.restart();
            RuntimeServerSupervisor.Connection second = poll(generations, deadline, "restarted generation delivery");
            assertThat(second).isNotNull();
            assertThat(second.generation()).isEqualTo(first.generation() + 1);
            assertThat(second.pid()).isNotEqualTo(first.pid());
            assertThat(supervisor.current()).contains(second);
            assertThat(middlewareApplications).hasValue(2);

            assertThat(first.session().request(new EmptyMessage(), payload -> new EmptyMessage()))
                    .isCompletedExceptionally();
        }
    }

    @Test
    void listenerRegisteredDuringInstallReceivesGenerationOnce(@TempDir Path temporary) throws Exception {
        TestGate installing = new TestGate();
        RuntimeServerSupervisor.Configuration configuration = new RuntimeServerSupervisor.Configuration(
                        launcher(temporary),
                        "zmq",
                        "tcp://127.0.0.1:0",
                        "shared-file",
                        Duration.ofSeconds(5),
                        Duration.ofSeconds(2),
                        Duration.ofMillis(25),
                        Duration.ofMillis(100),
                        0,
                        Map.of())
                .withSessionMiddleware(delegate -> new BlockingOnCloseSession(delegate, installing));
        AtomicInteger deliveries = new AtomicInteger();
        try (RuntimeServerSupervisor supervisor = new RuntimeServerSupervisor(configuration)) {
            TestDeadline deadline = TestDeadline.after(TEST_TIMEOUT);
            CompletableFuture<RuntimeServerSupervisor.Connection> started = supervisor.start();
            installing.awaitEntered(deadline, "generation install entry");
            AutoCloseable registration = supervisor.onConnection(connection -> deliveries.incrementAndGet());
            try {
                installing.close();
                deadline.await(started, "generation install completion");
                assertThat(deliveries).hasValue(1);
            } finally {
                installing.close();
                registration.close();
            }
        }
    }

    @Test
    void closeDuringGenerationInstallLeavesSupervisorClosed(@TempDir Path temporary) throws Exception {
        Path pidFile = temporary.resolve("install-race.pid");
        TestGate installing = new TestGate();
        RuntimeServerSupervisor.Configuration configuration = new RuntimeServerSupervisor.Configuration(
                        launcher(temporary),
                        "zmq",
                        "tcp://127.0.0.1:0",
                        "shared-file",
                        Duration.ofSeconds(5),
                        Duration.ofSeconds(2),
                        Duration.ofMillis(25),
                        Duration.ofMillis(100),
                        3,
                        Map.of("CEF4J_STUB_PID_FILE", pidFile.toString()))
                .withSessionMiddleware(delegate -> new BlockingOnCloseSession(delegate, installing));
        RuntimeServerSupervisor supervisor = new RuntimeServerSupervisor(configuration);
        TestDeadline deadline = TestDeadline.after(TEST_TIMEOUT);
        try {
            CompletableFuture<RuntimeServerSupervisor.Connection> starting = supervisor.start();
            installing.awaitEntered(deadline, "generation install entry");
            AtomicReference<Throwable> closeFailure = new AtomicReference<>();
            Thread closing = new Thread(() -> {
                try {
                    supervisor.close();
                } catch (Throwable failure) {
                    closeFailure.set(failure);
                }
            });
            closing.start();
            installing.close();
            deadline.join(closing, "supervisor close during install");
            assertThat(closeFailure.get()).isNull();
            assertThat(starting).isCompletedExceptionally();
        } finally {
            installing.close();
            supervisor.close();
        }

        assertThat(supervisor.state()).isEqualTo(RuntimeServerSupervisor.State.CLOSED);
        assertThat(supervisor.current()).isEmpty();

        long pid = Long.parseLong(Files.readString(pidFile));
        TestDeadline.after(Duration.ofSeconds(5))
                .until(
                        () -> !ProcessHandle.of(pid).map(ProcessHandle::isAlive).orElse(false),
                        Duration.ofMillis(10),
                        "installed process exit");
        assertThat(ProcessHandle.of(pid).map(ProcessHandle::isAlive).orElse(false))
                .isFalse();
    }

    private static Path launcher(Path directory) throws IOException {
        boolean windows = System.getProperty("os.name").toLowerCase(Locale.ROOT).contains("win");
        Path java = Path.of(System.getProperty("java.home"), "bin", windows ? "java.exe" : "java");
        String classpath = System.getProperty("java.class.path");
        Path script = directory.resolve(windows ? "stub-supervised-server.bat" : "stub-supervised-server.sh");
        String content = windows
                ? "@echo off\r\n\"" + java + "\" -cp \"" + classpath + "\" " + StubSupervisedServerMain.class.getName()
                        + " %*\r\n"
                : "#!/bin/sh\nexec \"" + java + "\" -cp \"" + classpath + "\" "
                        + StubSupervisedServerMain.class.getName() + " \"$@\"\n";
        Files.writeString(script, content);
        if (!windows) assertThat(script.toFile().setExecutable(true)).isTrue();
        return script;
    }

    private static <T> T poll(LinkedBlockingQueue<T> queue, TestDeadline deadline, String phase) throws Exception {
        T value = queue.poll(deadline.remainingNanos(), TimeUnit.NANOSECONDS);
        if (value == null) throw new java.util.concurrent.TimeoutException(phase);
        return value;
    }

    private static final class EmptyMessage implements CefMessageEncoder, CefMessageView {
        @Override
        public int messageId() {
            return 1;
        }

        @Override
        public int encodedSize() {
            return 0;
        }

        @Override
        public void encodeInto(ByteBuffer destination) {}
    }

    private static final class BlockingOnCloseSession implements CefSession {
        private final CefSession delegate;
        private final TestGate installing;

        BlockingOnCloseSession(CefSession delegate, TestGate installing) {
            this.delegate = delegate;
            this.installing = installing;
        }

        @Override
        public <R extends CefMessageView> CompletableFuture<R> request(
                @Nonnull CefMessageEncoder request, @Nonnull CefMessageDecoder<R> decoder) {
            return delegate.request(request, decoder);
        }

        @Override
        public <E extends CefMessageView> HandlerRegistration on(
                int messageId, @Nonnull CefMessageDecoder<E> decoder, @Nonnull Consumer<E> handler) {
            return delegate.on(messageId, decoder, handler);
        }

        @Override
        public <E extends CefMessageView> HandlerRegistration intercept(
                int messageId, @Nonnull CefMessageDecoder<E> decoder, @Nonnull InterceptHandler<E> handler) {
            return delegate.intercept(messageId, decoder, handler);
        }

        @Override
        public HandlerRegistration onClose(@Nonnull Runnable handler) {
            installing.enter();
            return () -> {};
        }

        @Override
        public void close() {
            delegate.close();
        }
    }
}
