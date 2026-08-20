package net.kurobako.cef4j.remote.swing;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.CompletableFuture;
import java.util.function.Consumer;
import javax.annotation.Nullable;
import net.kurobako.cef4j.ipc.frame.FrameTransport;
import net.kurobako.cef4j.ipc.protocol.gen.LifeSpanHandlerOnAfterCreatedEvent;
import net.kurobako.cef4j.ipc.session.CefMessageDecoder;
import net.kurobako.cef4j.ipc.session.CefMessageEncoder;
import net.kurobako.cef4j.ipc.session.CefMessageView;
import net.kurobako.cef4j.ipc.session.CefSession;
import net.kurobako.cef4j.ipc.session.RemoteHandle;
import net.kurobako.cef4j.test.DisplayLock;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

@ExtendWith(DisplayLock.class)
class RemoteBrowserPanelTest {

    @Test
    void discoversBootstrapBrowserAndOwnsFrameTransport() {
        FakeSession session = new FakeSession();
        FakeFrameTransport frames = new FakeFrameTransport();
        RemoteBrowserPanel panel = new RemoteBrowserPanel(connected -> {
            assertThat(connected).isSameAs(session);
            return frames;
        });

        panel.attach(session);
        session.emit(new LifeSpanHandlerOnAfterCreatedEvent(new RemoteHandle(17)));

        assertThat(panel.browserReady()).isCompletedWithValue(new RemoteHandle(17));
        assertThat(frames.consumer).isNotNull();
        panel.release();
        assertThat(frames.closed).isTrue();
    }

    @Test
    void attachIsIdempotentOnlyForOriginalSession() {
        FakeSession first = new FakeSession();
        FakeSession second = new FakeSession();
        RemoteBrowserPanel panel = new RemoteBrowserPanel(ignored -> new FakeFrameTransport());

        panel.attach(first);
        panel.attach(first);

        assertThatThrownBy(() -> panel.attach(second))
                .isInstanceOf(IllegalStateException.class)
                .hasMessageContaining("more than one session");
        panel.release();
    }

    private static final class FakeFrameTransport implements FrameTransport {
        @Nullable
        private FrameConsumer consumer;

        private boolean closed;

        @Override
        public void onFrame(@Nullable FrameConsumer consumer) {
            this.consumer = consumer;
        }

        @Override
        public void close() {
            closed = true;
        }
    }

    private static final class FakeSession implements CefSession {
        private final Map<Integer, List<Consumer<?>>> handlers = new HashMap<>();
        private final List<CefMessageEncoder> requests = new ArrayList<>();

        @Override
        public <R extends CefMessageView> CompletableFuture<R> request(
                CefMessageEncoder request, CefMessageDecoder<R> decoder) {
            requests.add(request);
            return new CompletableFuture<>();
        }

        @Override
        public <E extends CefMessageView> HandlerRegistration on(
                int messageId, CefMessageDecoder<E> decoder, Consumer<E> handler) {
            handlers.computeIfAbsent(messageId, ignored -> new ArrayList<>()).add(handler);
            return () -> handlers.getOrDefault(messageId, List.of()).remove(handler);
        }

        @Override
        public <E extends CefMessageView> HandlerRegistration intercept(
                int messageId, CefMessageDecoder<E> decoder, InterceptHandler<E> handler) {
            throw new UnsupportedOperationException();
        }

        @SuppressWarnings("unchecked")
        private <E extends CefMessageView> void emit(E event) {
            for (Consumer<?> handler : handlers.getOrDefault(event.messageId(), List.of())) {
                ((Consumer<E>) handler).accept(event);
            }
        }

        @Override
        public void close() {}
    }
}
