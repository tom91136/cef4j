package net.kurobako.cef4j.ipc.jfx;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.CompletableFuture;
import java.util.function.Consumer;
import net.kurobako.cef4j.ipc.protocol.gen.LifeSpanHandlerOnAfterCreatedEvent;
import net.kurobako.cef4j.ipc.protocol.gen.SetViewportSizeRequest;
import net.kurobako.cef4j.ipc.session.CefMessageDecoder;
import net.kurobako.cef4j.ipc.session.CefMessageEncoder;
import net.kurobako.cef4j.ipc.session.CefMessageView;
import net.kurobako.cef4j.ipc.session.CefSession;
import net.kurobako.cef4j.ipc.session.RemoteHandle;
import org.junit.jupiter.api.Test;

class IpcWebViewTest {

    @Test
    void layoutBeforeAttachIsFlushedWhenBrowserBecomesReady() {
        FakeSession session = new FakeSession();
        IpcWebView view = new IpcWebView();
        view.resize(640, 480);
        view.layout();

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
        IpcWebView view = new IpcWebView();

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
        IpcWebView view = new IpcWebView();
        FakeSession session = new FakeSession();

        view.release();
        view.attach(session);
        session.emit(new LifeSpanHandlerOnAfterCreatedEvent(new RemoteHandle(13)));

        assertThat(view.browserReady()).isCompletedWithValue(new RemoteHandle(13));
        view.release();
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
