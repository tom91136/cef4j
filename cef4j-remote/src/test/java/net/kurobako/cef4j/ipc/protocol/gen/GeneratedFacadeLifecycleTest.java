package net.kurobako.cef4j.ipc.protocol.gen;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.function.Consumer;
import javax.annotation.Nonnull;
import net.kurobako.cef4j.ipc.session.CefMessageDecoder;
import net.kurobako.cef4j.ipc.session.CefMessageEncoder;
import net.kurobako.cef4j.ipc.session.CefMessageView;
import net.kurobako.cef4j.ipc.session.CefSession;
import net.kurobako.cef4j.ipc.session.RemoteHandle;
import org.junit.jupiter.api.Test;

class GeneratedFacadeLifecycleTest {
    @Test
    void browserCloseIsIdempotentAcrossAllReleaseEntryPoints() {
        AtomicInteger requests = new AtomicInteger();
        CefSession session = releaseSession(requests, ReleaseHandleRequest.class, ReleaseHandleResponse::new);
        Browser browser = new Browser(session, new RemoteHandle(7));

        CompletableFuture<Void> first = browser.closeAsync();
        CompletableFuture<Void> second = browser.releaseHandle();
        browser.close();

        assertThat(first).isSameAs(second).isCompleted();
        assertThat(requests).hasValue(1);
    }

    @Test
    void nativeCloseOperationDoesNotCollideWithHandleClose() throws Exception {
        assertThat(Window.class.getMethod("cefClose").getReturnType()).isEqualTo(CompletableFuture.class);
        assertThat(Window.class.getMethod("close").getReturnType()).isEqualTo(Void.TYPE);
        assertThat(XmlReader.class.getMethod("cefClose").getReturnType()).isEqualTo(CompletableFuture.class);
        assertThat(ZipReader.class.getMethod("cefClose").getReturnType()).isEqualTo(CompletableFuture.class);
    }

    @Test
    void generatedHandlerRegistrationClosesEverySubscriptionOnce() {
        AtomicInteger registrations = new AtomicInteger();
        AtomicInteger closes = new AtomicInteger();
        CefSession session = subscriptionSession(registrations, closes);

        CefSession.HandlerRegistration registration = CefLoadHandler.register(session, new CefLoadHandler() {});
        registration.close();
        registration.close();

        assertThat(registrations).hasValue(4);
        assertThat(closes).hasValue(4);
    }

    @Test
    void generatedVisitorRouteExposesItsSubscription() {
        AtomicInteger registrations = new AtomicInteger();
        AtomicInteger closes = new AtomicInteger();
        CefSession session = subscriptionSession(registrations, closes);

        CefStringVisitor.route(session, new net.kurobako.cef4j.ipc.session.JvmCallbackTable<>())
                .close();

        assertThat(registrations).hasValue(1);
        assertThat(closes).hasValue(1);
    }

    private static CefSession releaseSession(
            AtomicInteger requests,
            Class<? extends CefMessageEncoder> requestType,
            java.util.function.Supplier<? extends CefMessageView> response) {
        return new CefSession() {
            @Override
            @SuppressWarnings("unchecked")
            public <R extends CefMessageView> CompletableFuture<R> request(
                    CefMessageEncoder request, CefMessageDecoder<R> decoder) {
                assertThat(request).isInstanceOf(requestType);
                requests.incrementAndGet();
                return CompletableFuture.completedFuture((R) response.get());
            }

            @Override
            public <E extends CefMessageView> HandlerRegistration on(
                    int messageId, @Nonnull CefMessageDecoder<E> decoder, @Nonnull Consumer<E> handler) {
                throw new UnsupportedOperationException();
            }

            @Override
            public <E extends CefMessageView> HandlerRegistration intercept(
                    int messageId, @Nonnull CefMessageDecoder<E> decoder, @Nonnull InterceptHandler<E> handler) {
                throw new UnsupportedOperationException();
            }

            @Override
            public void close() {}
        };
    }

    private static CefSession subscriptionSession(AtomicInteger registrations, AtomicInteger closes) {
        return new CefSession() {
            @Override
            public <R extends CefMessageView> CompletableFuture<R> request(
                    CefMessageEncoder request, CefMessageDecoder<R> decoder) {
                throw new UnsupportedOperationException();
            }

            @Override
            public <E extends CefMessageView> HandlerRegistration on(
                    int messageId, @Nonnull CefMessageDecoder<E> decoder, @Nonnull Consumer<E> handler) {
                registrations.incrementAndGet();
                return closes::incrementAndGet;
            }

            @Override
            public <E extends CefMessageView> HandlerRegistration intercept(
                    int messageId, @Nonnull CefMessageDecoder<E> decoder, @Nonnull InterceptHandler<E> handler) {
                registrations.incrementAndGet();
                return closes::incrementAndGet;
            }

            @Override
            public void close() {}
        };
    }
}
