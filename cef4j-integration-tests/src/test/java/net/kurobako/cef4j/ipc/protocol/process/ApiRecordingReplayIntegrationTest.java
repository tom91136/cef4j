package net.kurobako.cef4j.ipc.protocol.process;

import static org.assertj.core.api.Assertions.assertThat;

import java.nio.file.Path;
import java.time.Duration;
import java.util.Objects;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicReference;
import net.kurobako.cef4j.ipc.frame.RemoteCefBrowserBackend;
import net.kurobako.cef4j.ipc.protocol.gen.Browser;
import net.kurobako.cef4j.ipc.protocol.gen.LifeSpanHandlerOnAfterCreatedEvent;
import net.kurobako.cef4j.ipc.session.CefSession;
import net.kurobako.cef4j.ipc.session.CefSessionImpl;
import net.kurobako.cef4j.ipc.session.RemoteHandle;
import net.kurobako.cef4j.ipc.session.middleware.RecordingCefSession;
import net.kurobako.cef4j.ipc.session.middleware.ReplayCefSession;
import net.kurobako.cef4j.ipc.session.middleware.ReplayMode;
import net.kurobako.cef4j.ipc.session.process.RuntimeServerProcess;
import net.kurobako.cef4j.ipc.transport.ZmqTransport;
import net.kurobako.cef4j.test.RuntimeServerTestEnvironment;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Timeout;
import org.junit.jupiter.api.io.TempDir;

/** Proves a trace captured from packaged CEF drives the same generated facade after the native server is gone. */
@Timeout(30)
class ApiRecordingReplayIntegrationTest {
    @Test
    void packagedCefInteractionReplaysWithoutRuntimeServer(@TempDir Path directory) throws Exception {
        RuntimeServerTestEnvironment environment = RuntimeServerTestEnvironment.require();

        Path trace = directory.resolve("packaged-runtime.cef4japi.jsonl");
        int liveCanGoBack;
        try (RuntimeServerProcess server = RemoteCefBrowserBackend.launchServer(
                        environment.binary(), environment.resources(), Duration.ofSeconds(20));
                ZmqTransport transport = ZmqTransport.connect(server.endpoint());
                RecordingCefSession recording =
                        RecordingCefSession.toFile(new CefSessionImpl(transport, Duration.ofSeconds(20)), trace)) {
            AtomicReference<RemoteHandle> browserHandle = new AtomicReference<>();
            CefSession.HandlerRegistration lifecycle = recording.onLatest(
                    LifeSpanHandlerOnAfterCreatedEvent.MESSAGE_ID,
                    LifeSpanHandlerOnAfterCreatedEvent.DECODER,
                    event -> browserHandle.set(event.browser()));
            long deadline = System.nanoTime() + TimeUnit.SECONDS.toNanos(20);
            while (browserHandle.get() == null && System.nanoTime() < deadline) Thread.sleep(10);
            RemoteHandle handle = Objects.requireNonNull(browserHandle.get());
            liveCanGoBack = new Browser(recording, handle).canGoBack().get(20, TimeUnit.SECONDS);
            lifecycle.unregister();
        }

        // The server and transport are closed before replay begins.
        ReplayCefSession replay = ReplayCefSession.fromFile(trace, ReplayMode.IMMEDIATE);
        AtomicReference<RemoteHandle> replayedHandle = new AtomicReference<>();
        CefSession.HandlerRegistration lifecycle = replay.onLatest(
                LifeSpanHandlerOnAfterCreatedEvent.MESSAGE_ID,
                LifeSpanHandlerOnAfterCreatedEvent.DECODER,
                event -> replayedHandle.set(event.browser()));
        replay.start();
        RemoteHandle handle = Objects.requireNonNull(replayedHandle.get());
        assertThat(new Browser(replay, handle).canGoBack().get(5, TimeUnit.SECONDS))
                .isEqualTo(liveCanGoBack);
        lifecycle.unregister();
        replay.close();
        replay.verifyComplete();
    }
}
