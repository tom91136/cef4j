package net.kurobako.cef4j.remote.jfx;

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
import net.kurobako.cef4j.ipc.protocol.gen.SetViewportSizeRequest;
import net.kurobako.cef4j.ipc.session.CefMessageDecoder;
import net.kurobako.cef4j.ipc.session.CefMessageEncoder;
import net.kurobako.cef4j.ipc.session.CefMessageView;
import net.kurobako.cef4j.ipc.session.CefSession;
import net.kurobako.cef4j.ipc.session.RemoteHandle;
import net.kurobako.cef4j.test.DisplayLock;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

@ExtendWith(DisplayLock.class)
class RemoteWebViewTest {

    @Test
    void resizeBeforeAttachIsFlushedWhenBrowserBecomesReady() {
        FakeSession session = new FakeSession();
        RemoteWebView view = new RemoteWebView();
        view.resize(640, 480);

        view.attach(session);
        session.emit(new LifeSpanHandlerOnAfterCreatedEvent(new RemoteHandle(7)));

        assertThat(session.requests)
                .filteredOn(SetViewportSizeRequest.class::isInstance)
                .singleElement()
                .satisfies(r -> {
                    SetViewportSizeRequest resize = (SetViewportSizeRequest) r;
                    assertThat(resize.browser().id()).isEqualTo(7);
                    assertThat(resize.width()).isEqualTo(640);
                    assertThat(resize.height()).isEqualTo(480);
                });
        view.release();
    }

    @Test
    void attachIsIdempotentOnlyForTheOriginalSession() {
        FakeSession first = new FakeSession();
        FakeSession second = new FakeSession();
        RemoteWebView view = new RemoteWebView();

        view.attach(first);
        view.attach(first);

        assertThatThrownBy(() -> view.attach(second))
                .isInstanceOf(IllegalStateException.class)
                .hasMessageContaining("more than one session");
        first.emit(new LifeSpanHandlerOnAfterCreatedEvent(new RemoteHandle(11)));
        view.release();
    }

    @Test
    void releaseBeforeAttachIsANoOp() {
        RemoteWebView view = new RemoteWebView();
        FakeSession session = new FakeSession();

        view.release();
        view.attach(session);
        session.emit(new LifeSpanHandlerOnAfterCreatedEvent(new RemoteHandle(13)));

        assertThat(view.browserReady()).isCompletedWithValue(new RemoteHandle(13));
        view.release();
    }

    @Test
    void customFrameTransportIsBoundAndOwnedByTheView() {
        FakeSession session = new FakeSession();
        FakeFrameTransport frames = new FakeFrameTransport();
        RemoteWebView view = new RemoteWebView(boundSession -> {
            assertThat(boundSession).isSameAs(session);
            return frames;
        });

        view.attach(session);
        assertThat(frames.consumer).isNotNull();
        view.release();
        assertThat(frames.closed).isTrue();
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
