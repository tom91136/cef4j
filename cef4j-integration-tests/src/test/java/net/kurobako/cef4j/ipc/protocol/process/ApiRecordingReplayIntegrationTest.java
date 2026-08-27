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
import net.kurobako.cef4j.ipc.transport.CefTransport;
import net.kurobako.cef4j.test.RuntimeServerTestEnvironment;
import net.kurobako.cef4j.test.TestDeadline;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Timeout;
import org.junit.jupiter.api.io.TempDir;

@Timeout(600)
class ApiRecordingReplayIntegrationTest {
    @Test
    void packagedCefInteractionReplaysWithoutRuntimeServer(@TempDir Path directory) throws Exception {
        RuntimeServerTestEnvironment environment = RuntimeServerTestEnvironment.require();

        Path trace = directory.resolve("packaged-runtime.cef4japi.jsonl");
        int liveCanGoBack;
        try (RuntimeServerProcess server = RemoteCefBrowserBackend.launchServer(
                        environment.binary(), environment.resources(), Duration.ofSeconds(20));
                CefTransport transport = server.connect();
                RecordingCefSession recording =
                        RecordingCefSession.toFile(new CefSessionImpl(transport, Duration.ofSeconds(30)), trace)) {
            AtomicReference<RemoteHandle> browserHandle = new AtomicReference<>();
            CefSession.HandlerRegistration lifecycle = recording.onLatest(
                    LifeSpanHandlerOnAfterCreatedEvent.MESSAGE_ID,
                    LifeSpanHandlerOnAfterCreatedEvent.DECODER,
                    event -> browserHandle.set(event.browser()));
            TestDeadline.after(Duration.ofSeconds(20))
                    .until(() -> browserHandle.get() != null, Duration.ofMillis(10), "live browser handle");
            try (lifecycle;
                    Browser browser = new Browser(recording, Objects.requireNonNull(browserHandle.get()))) {
                liveCanGoBack = browser.canGoBack().get(20, TimeUnit.SECONDS);
            }
        }

        ReplayCefSession replay = ReplayCefSession.fromFile(trace, ReplayMode.IMMEDIATE);
        AtomicReference<RemoteHandle> replayedHandle = new AtomicReference<>();
        CefSession.HandlerRegistration lifecycle = replay.onLatest(
                LifeSpanHandlerOnAfterCreatedEvent.MESSAGE_ID,
                LifeSpanHandlerOnAfterCreatedEvent.DECODER,
                event -> replayedHandle.set(event.browser()));
        try {
            replay.start();
            RemoteHandle handle = Objects.requireNonNull(replayedHandle.get());
            try (Browser browser = new Browser(replay, handle)) {
                assertThat(browser.canGoBack().get(5, TimeUnit.SECONDS)).isEqualTo(liveCanGoBack);
            }
        } finally {
            lifecycle.close();
            replay.close();
        }
        replay.verifyComplete();
    }
}
