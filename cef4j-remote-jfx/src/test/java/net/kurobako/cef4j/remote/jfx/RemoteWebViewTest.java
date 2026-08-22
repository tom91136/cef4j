package net.kurobako.cef4j.remote.jfx;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicReference;
import java.util.function.Consumer;
import javafx.application.Platform;
import javax.annotation.Nullable;
import net.kurobako.cef4j.ipc.frame.FrameTransport;
import net.kurobako.cef4j.ipc.protocol.gen.LifeSpanHandlerOnAfterCreatedEvent;
import net.kurobako.cef4j.ipc.protocol.gen.SetViewportSizeRequest;
import net.kurobako.cef4j.ipc.protocol.gen.SetViewportSizeResponse;
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
    private static final AtomicBoolean FX_STARTED = new AtomicBoolean();

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
    void explicitViewportResizeFollowsPendingFxResize() throws Exception {
        startJavaFx();
        FakeSession session = new FakeSession();
        AtomicReference<RemoteWebView> viewRef = new AtomicReference<>();
        AtomicReference<CompletableFuture<Void>> resizeRef = new AtomicReference<>();

        onFxThread(() -> {
            RemoteWebView view = new RemoteWebView(ignored -> new FakeFrameTransport());
            view.resize(640, 480);
            view.attach(session);
            session.emit(new LifeSpanHandlerOnAfterCreatedEvent(new RemoteHandle(17)));
            Platform.runLater(() -> view.resize(640, 480));
            resizeRef.set(view.resizeViewport(512, 384));
            viewRef.set(view);
        });
        onFxThread(() -> {});

        assertThat(session.requests.get(session.requests.size() - 1))
                .isInstanceOfSatisfying(SetViewportSizeRequest.class, request -> {
                    assertThat(request.width()).isEqualTo(512);
                    assertThat(request.height()).isEqualTo(384);
                });
        CompletableFuture<Void> resized = Objects.requireNonNull(resizeRef.get(), "resize acknowledgement");
        assertThat(resized).isNotDone();
        session.completeLast(new SetViewportSizeResponse());
        assertThat(resized).isCompleted();
        Objects.requireNonNull(viewRef.get(), "remote view").release();
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

    private static void startJavaFx() throws Exception {
        if (FX_STARTED.get()) return;
        CompletableFuture<Void> started = new CompletableFuture<>();
        try {
            Platform.startup(() -> {
                Platform.setImplicitExit(false);
                FX_STARTED.set(true);
                started.complete(null);
            });
        } catch (IllegalStateException alreadyStarted) {
            FX_STARTED.set(true);
            started.complete(null);
        }
        started.get(15, TimeUnit.SECONDS);
    }

    private static void onFxThread(Runnable action) throws Exception {
        CompletableFuture<Void> done = new CompletableFuture<>();
        Platform.runLater(() -> {
            try {
                action.run();
                done.complete(null);
            } catch (Throwable failure) {
                done.completeExceptionally(failure);
            }
        });
        done.get(15, TimeUnit.SECONDS);
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
        private final List<CompletableFuture<?>> responses = new ArrayList<>();

        @Override
        public <R extends CefMessageView> CompletableFuture<R> request(
                CefMessageEncoder request, CefMessageDecoder<R> decoder) {
            requests.add(request);
            CompletableFuture<R> response = new CompletableFuture<>();
            responses.add(response);
            return response;
        }

        @SuppressWarnings("unchecked")
        private <R extends CefMessageView> void completeLast(R response) {
            ((CompletableFuture<R>) responses.get(responses.size() - 1)).complete(response);
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
