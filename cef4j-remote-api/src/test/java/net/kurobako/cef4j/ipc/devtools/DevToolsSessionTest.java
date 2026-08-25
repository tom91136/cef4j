package net.kurobako.cef4j.ipc.devtools;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.concurrent.CompletableFuture;
import java.util.function.Consumer;
import net.kurobako.cef4j.cdp.CdpCodec;
import net.kurobako.cef4j.ipc.protocol.gen.BrowserHost;
import net.kurobako.cef4j.ipc.session.CefMessageDecoder;
import net.kurobako.cef4j.ipc.session.CefMessageEncoder;
import net.kurobako.cef4j.ipc.session.CefMessageView;
import net.kurobako.cef4j.ipc.session.CefSession;
import net.kurobako.cef4j.ipc.session.RemoteHandle;
import org.junit.jupiter.api.Test;

class DevToolsSessionTest {
    @Test
    void cancellingAttachCancelsSessionRequest() {
        RecordingSession session = new RecordingSession();
        CompletableFuture<DevToolsSession> attaching = DevToolsSession.attach(
                session, new RemoteHandle(1), new BrowserHost(session, new RemoteHandle(2)), new UnusedCodec());

        attaching.cancel(true);

        assertThat(session.request).isCancelled();
    }

    private static final class RecordingSession implements CefSession {
        private final CompletableFuture<CefMessageView> request = new CompletableFuture<>();

        @Override
        @SuppressWarnings("unchecked")
        public <R extends CefMessageView> CompletableFuture<R> request(
                CefMessageEncoder request, CefMessageDecoder<R> decoder) {
            return (CompletableFuture<R>) (CompletableFuture<?>) this.request;
        }

        @Override
        public <E extends CefMessageView> HandlerRegistration on(
                int messageId, CefMessageDecoder<E> decoder, Consumer<E> handler) {
            return () -> {};
        }

        @Override
        public <E extends CefMessageView> HandlerRegistration intercept(
                int messageId, CefMessageDecoder<E> decoder, InterceptHandler<E> handler) {
            return () -> {};
        }

        @Override
        public void close() {}
    }

    private static final class UnusedCodec implements CdpCodec {
        @Override
        public Object decode(byte[] json) {
            throw new AssertionError("unexpected decode");
        }

        @Override
        public byte[] encode(Object value) {
            throw new AssertionError("unexpected encode");
        }
    }
}
