package net.kurobako.cef4j.ipc.protocol.gen;

import static org.assertj.core.api.Assertions.assertThat;

import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.file.Path;
import java.util.concurrent.CompletableFuture;
import java.util.function.Consumer;
import javax.annotation.Nonnull;
import net.kurobako.cef4j.ipc.session.CefMessageDecoder;
import net.kurobako.cef4j.ipc.session.CefMessageEncoder;
import net.kurobako.cef4j.ipc.session.CefMessageView;
import net.kurobako.cef4j.ipc.session.CefSession;
import net.kurobako.cef4j.ipc.session.RemoteHandle;
import net.kurobako.cef4j.ipc.session.middleware.RecordingCefSession;
import net.kurobako.cef4j.ipc.session.middleware.ReplayCefSession;
import net.kurobako.cef4j.ipc.session.middleware.ReplayMode;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;

class GeneratedApiRecordingReplayTest {
    @Test
    void generatedFacadeRunsUnchangedAgainstReplay(@TempDir Path directory) throws Exception {
        Path trace = directory.resolve("browser-api.cef4japi.jsonl");
        Browser liveBrowser;
        try (RecordingCefSession recording = RecordingCefSession.toFile(new CanGoBackSession(), trace)) {
            liveBrowser = new Browser(recording, new RemoteHandle(42));
            assertThat(liveBrowser.canGoBack().get()).isEqualTo(1);
        }

        ReplayCefSession replay = ReplayCefSession.fromFile(trace, ReplayMode.IMMEDIATE);
        Browser replayBrowser = new Browser(replay, new RemoteHandle(42));
        replay.start();
        assertThat(replayBrowser.canGoBack().get()).isEqualTo(1);
        replay.close();
        replay.verifyComplete();
    }

    private static final class CanGoBackSession implements CefSession {
        @Override
        @Nonnull
        public <R extends CefMessageView> CompletableFuture<R> request(
                @Nonnull CefMessageEncoder request, @Nonnull CefMessageDecoder<R> decoder) {
            if (request.messageId() != BrowserCanGoBackRequest.MESSAGE_ID) {
                CompletableFuture<R> failure = new CompletableFuture<>();
                failure.completeExceptionally(new IllegalArgumentException("unexpected generated request"));
                return failure;
            }
            BrowserCanGoBackResponse response = new BrowserCanGoBackResponse(1);
            ByteBuffer payload = ByteBuffer.allocate(response.encodedSize()).order(ByteOrder.LITTLE_ENDIAN);
            response.encodeInto(payload);
            payload.flip();
            return CompletableFuture.completedFuture(decoder.decode(payload));
        }

        @Override
        @Nonnull
        public <E extends CefMessageView> HandlerRegistration on(
                int messageId, @Nonnull CefMessageDecoder<E> decoder, @Nonnull Consumer<E> handler) {
            throw new UnsupportedOperationException();
        }

        @Override
        @Nonnull
        public <E extends CefMessageView> HandlerRegistration intercept(
                int messageId, @Nonnull CefMessageDecoder<E> decoder, @Nonnull InterceptHandler<E> handler) {
            throw new UnsupportedOperationException();
        }

        @Override
        public void close() {}
    }
}
