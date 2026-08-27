package net.kurobako.cef4j.remote.swing;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicReference;
import java.util.function.Consumer;
import javax.annotation.Nullable;
import javax.swing.SwingUtilities;
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
class RemoteBrowserPanelTest {

    @Test
    void discoversBootstrapBrowserAndOwnsFrameTransport() {
        FakeSession session = new FakeSession();
        FakeFrameTransport frames = new FakeFrameTransport();
        RemoteBrowserPanel panel = new RemoteBrowserPanel((connected, browser) -> {
            assertThat(connected).isSameAs(session);
            assertThat(browser).isEqualTo(new RemoteHandle(17));
            return frames;
        });

        panel.attach(session);
        session.emit(new LifeSpanHandlerOnAfterCreatedEvent(new RemoteHandle(17)));

        assertThat(panel.browserReady()).isCompletedWithValue(new RemoteHandle(17));
        assertThat(frames.consumer).isNotNull();
        panel.close();
        assertThat(frames.closed).isTrue();
    }

    @Test
    void attachIsIdempotentOnlyForOriginalSession() {
        FakeSession first = new FakeSession();
        FakeSession second = new FakeSession();
        RemoteBrowserPanel panel = new RemoteBrowserPanel((ignored, browser) -> new FakeFrameTransport());

        panel.attach(first);
        panel.attach(first);

        assertThatThrownBy(() -> panel.attach(second))
                .isInstanceOf(IllegalStateException.class)
                .hasMessageContaining("more than one session");
        panel.release();
    }

    @Test
    void explicitViewportResizeCompletesAfterRemoteAcknowledgement() {
        FakeSession session = new FakeSession();
        RemoteBrowserPanel panel = new RemoteBrowserPanel((ignored, browser) -> new FakeFrameTransport());
        panel.setSize(640, 480);
        panel.attach(session);
        session.emit(new LifeSpanHandlerOnAfterCreatedEvent(new RemoteHandle(17)));

        CompletableFuture<Void> resized = panel.resizeViewport(512, 384);

        assertThat(resized).isNotDone();
        assertThat(session.requests.get(session.requests.size() - 1))
                .isInstanceOfSatisfying(SetViewportSizeRequest.class, request -> {
                    assertThat(request.browser()).isEqualTo(new RemoteHandle(17));
                    assertThat(request.width()).isEqualTo(512);
                    assertThat(request.height()).isEqualTo(384);
                });
        session.completeLast(new SetViewportSizeResponse());
        assertThat(resized).isCompleted();
        panel.release();
    }

    @Test
    void explicitViewportResizeFollowsPendingComponentResize() throws Exception {
        FakeSession session = new FakeSession();
        RemoteBrowserPanel panel = new RemoteBrowserPanel((ignored, browser) -> new FakeFrameTransport());
        panel.setSize(640, 480);
        panel.attach(session);
        session.emit(new LifeSpanHandlerOnAfterCreatedEvent(new RemoteHandle(17)));
        AtomicReference<CompletableFuture<Void>> resized = new AtomicReference<>();

        SwingUtilities.invokeAndWait(() -> {
            SwingUtilities.invokeLater(() -> panel.dispatchEvent(
                    new java.awt.event.ComponentEvent(panel, java.awt.event.ComponentEvent.COMPONENT_RESIZED)));
            resized.set(panel.resizeViewport(512, 384));
        });
        SwingUtilities.invokeAndWait(() -> {});

        assertThat(session.requests.get(session.requests.size() - 1))
                .isInstanceOfSatisfying(SetViewportSizeRequest.class, request -> {
                    assertThat(request.width()).isEqualTo(512);
                    assertThat(request.height()).isEqualTo(384);
                });
        session.completeLast(new SetViewportSizeResponse());
        assertThat(resized.get()).isCompleted();
        panel.release();
    }

    @Test
    void explicitViewportResizeOffEdtFollowsPendingComponentResize() throws Exception {
        FakeSession session = new FakeSession();
        RemoteBrowserPanel panel = new RemoteBrowserPanel((ignored, browser) -> new FakeFrameTransport());
        panel.setSize(640, 480);
        panel.attach(session);
        session.emit(new LifeSpanHandlerOnAfterCreatedEvent(new RemoteHandle(17)));

        SwingUtilities.invokeAndWait(() -> SwingUtilities.invokeLater(() -> panel.dispatchEvent(
                new java.awt.event.ComponentEvent(panel, java.awt.event.ComponentEvent.COMPONENT_RESIZED))));
        CompletableFuture<Void> resized = panel.resizeViewport(512, 384);
        SwingUtilities.invokeAndWait(() -> {});

        assertThat(session.requests.get(session.requests.size() - 1))
                .isInstanceOfSatisfying(SetViewportSizeRequest.class, request -> {
                    assertThat(request.width()).isEqualTo(512);
                    assertThat(request.height()).isEqualTo(384);
                });
        assertThat(resized).isNotDone();
        session.completeLast(new SetViewportSizeResponse());
        assertThat(resized).isCompleted();
        panel.release();
    }

    @Test
    void rejectsViewportOutsideSharedRuntimeBudget() {
        RemoteBrowserPanel panel = new RemoteBrowserPanel();

        assertThatThrownBy(() -> panel.resizeViewport(8193, 1)).isInstanceOf(IllegalArgumentException.class);
        assertThatThrownBy(() -> panel.resizeViewport(3840, 2161)).isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    void failedFrameSubscriptionClosesPartialTransportAndCanBeRetried() {
        FakeSession session = new FakeSession();
        AtomicBoolean rejectFirst = new AtomicBoolean(true);
        FakeFrameTransport rejected = new FakeFrameTransport();
        FakeFrameTransport accepted = new FakeFrameTransport();
        RemoteBrowserPanel panel = new RemoteBrowserPanel((ignored, browser) -> {
            if (!rejectFirst.getAndSet(false)) return accepted;
            rejected.rejectConsumer = true;
            return rejected;
        });

        panel.attach(session);
        CompletableFuture<RemoteHandle> failedReady = panel.browserReady();
        session.emit(new LifeSpanHandlerOnAfterCreatedEvent(new RemoteHandle(29)));
        assertThat(failedReady).isCompletedExceptionally();
        assertThat(rejected.closed).isTrue();

        panel.attach(session);
        session.emit(new LifeSpanHandlerOnAfterCreatedEvent(new RemoteHandle(31)));
        assertThat(panel.browserReady()).isCompletedWithValue(new RemoteHandle(31));
        panel.release();
        assertThat(accepted.closed).isTrue();
    }

    private static final class FakeFrameTransport implements FrameTransport {
        @Nullable
        private FrameConsumer consumer;

        private boolean closed;
        private boolean rejectConsumer;

        @Override
        public void onFrame(@Nullable FrameConsumer consumer) {
            if (rejectConsumer) throw new IllegalStateException("consumer rejected");
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
            for (Consumer<?> handler : new ArrayList<>(handlers.getOrDefault(event.messageId(), List.of()))) {
                ((Consumer<E>) handler).accept(event);
            }
        }

        @Override
        public void close() {}
    }
}
