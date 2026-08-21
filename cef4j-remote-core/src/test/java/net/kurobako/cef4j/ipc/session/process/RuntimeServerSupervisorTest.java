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
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.function.Consumer;
import javax.annotation.Nonnull;
import net.kurobako.cef4j.ipc.session.CefMessageDecoder;
import net.kurobako.cef4j.ipc.session.CefMessageEncoder;
import net.kurobako.cef4j.ipc.session.CefMessageView;
import net.kurobako.cef4j.ipc.session.CefSession;
import net.kurobako.cef4j.ipc.transport.CefTransportException;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Timeout;
import org.junit.jupiter.api.io.TempDir;

@Timeout(900)
final class RuntimeServerSupervisorTest {
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
            assertThatThrownBy(() -> supervisor.start().get(30, TimeUnit.SECONDS))
                    .hasRootCauseInstanceOf(CefTransportException.class);
        }

        long pid = Long.parseLong(Files.readString(pidFile));
        long deadline = System.nanoTime() + TimeUnit.SECONDS.toNanos(5);
        while (ProcessHandle.of(pid).map(ProcessHandle::isAlive).orElse(false) && System.nanoTime() < deadline)
            Thread.sleep(10);
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
            assertThat(registration).isNotNull();
            RuntimeServerSupervisor.Connection first = supervisor.start().get(300, TimeUnit.SECONDS);
            assertThat(generations.poll(300, TimeUnit.SECONDS)).isSameAs(first);
            RuntimeServerSupervisor.Connection second = generations.poll(300, TimeUnit.SECONDS);
            assertThat(second).isNotNull();
            assertThat(second.pid()).isNotEqualTo(first.pid());
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
            assertThat(registration).isNotNull();
            RuntimeServerSupervisor.Connection first = supervisor.start().get(300, TimeUnit.SECONDS);
            assertThat(generations.poll(300, TimeUnit.SECONDS)).isSameAs(first);

            supervisor.restart();
            RuntimeServerSupervisor.Connection second = generations.poll(300, TimeUnit.SECONDS);
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
    void closeDuringGenerationInstallLeavesSupervisorClosed(@TempDir Path temporary) throws Exception {
        Path pidFile = temporary.resolve("install-race.pid");
        CountDownLatch installing = new CountDownLatch(1);
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
        var unused = supervisor.start();
        // The latch guards the race being tested, not startup performance. Native CI builds can starve the stub
        // process for more than ten seconds before it reaches the install transition.
        assertThat(installing.await(300, TimeUnit.SECONDS)).isTrue();
        supervisor.close();

        // Wait for spawn to execute its install transition, then for the final state to settle at CLOSED.
        long transition = System.nanoTime() + TimeUnit.MILLISECONDS.toNanos(500);
        while (supervisor.state() == RuntimeServerSupervisor.State.CLOSED && System.nanoTime() < transition)
            Thread.sleep(1);
        long deadline = System.nanoTime() + TimeUnit.SECONDS.toNanos(5);
        while (supervisor.state() != RuntimeServerSupervisor.State.CLOSED && System.nanoTime() < deadline)
            Thread.sleep(10);
        assertThat(supervisor.state()).isEqualTo(RuntimeServerSupervisor.State.CLOSED);
        assertThat(supervisor.current()).isEmpty();

        long pid = Long.parseLong(Files.readString(pidFile));
        long pidDeadline = System.nanoTime() + TimeUnit.SECONDS.toNanos(5);
        while (ProcessHandle.of(pid).map(ProcessHandle::isAlive).orElse(false) && System.nanoTime() < pidDeadline)
            Thread.sleep(10);
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

    /**
     * Delegating session whose {@code onClose} blocks so the test can race {@link RuntimeServerSupervisor#close()}
     * against the connection install.
     */
    private static final class BlockingOnCloseSession implements CefSession {
        private final CefSession delegate;
        private final CountDownLatch installing;

        BlockingOnCloseSession(CefSession delegate, CountDownLatch installing) {
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
            installing.countDown();
            try {
                Thread.sleep(500);
            } catch (InterruptedException e) {
                // close() interrupted the sleep, which means it has fully run.
            }
            return () -> {};
        }

        @Override
        public void close() {
            delegate.close();
        }
    }
}
