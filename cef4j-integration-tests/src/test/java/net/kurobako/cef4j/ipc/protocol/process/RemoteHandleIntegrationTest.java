package net.kurobako.cef4j.ipc.protocol.process;

import static org.assertj.core.api.Assertions.assertThat;

import java.time.Duration;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.TimeUnit;
import net.kurobako.cef4j.ipc.protocol.gen.Browser;
import net.kurobako.cef4j.ipc.protocol.gen.LifeSpanHandlerOnAfterCreatedEvent;
import net.kurobako.cef4j.ipc.session.CefSession;
import net.kurobako.cef4j.ipc.session.CefSessionImpl;
import net.kurobako.cef4j.ipc.session.RemoteHandle;
import net.kurobako.cef4j.ipc.session.process.RuntimeServerProcess;
import net.kurobako.cef4j.ipc.transport.CefTransport;
import net.kurobako.cef4j.test.RuntimeServerTestEnvironment;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Timeout;

/**
 * End-to-end through the real C++ server, exercising remote method invocation via {@link RemoteHandle}: the server
 * allocates a handle for the browser created at startup, the generated LifeSpanHandlerForwarder emits a
 * {@link LifeSpanHandlerOnAfterCreatedEvent}, and the JVM side wraps it in a {@link Browser} facade and dispatches
 * `canGoBack`/`goBack` (AST-derived methods, generated dispatcher case in `Dispatcher.h`).
 *
 * <p>Smallest test validating the whole {@code RemoteHandle} round-trip through the AST pipeline: handle allocation,
 * wire encoding, server-side dispatch with handle resolution, typed response decoding.
 */
@Timeout(600)
class RemoteHandleIntegrationTest {

    private static final RuntimeServerTestEnvironment RUNTIME = RuntimeServerTestEnvironment.require();

    @Test
    void remoteHandleRoundTripThroughRealRuntimeServer() throws Exception {
        try (RuntimeServerProcess server = RUNTIME.spawn();
                CefTransport transport = server.connect();
                CefSession session = new CefSessionImpl(transport, Duration.ofSeconds(30))) {

            CompletableFuture<RemoteHandle> handleFuture = new CompletableFuture<>();
            session.onLatest(
                    LifeSpanHandlerOnAfterCreatedEvent.MESSAGE_ID, LifeSpanHandlerOnAfterCreatedEvent.DECODER, ev -> {
                        if (!handleFuture.isDone()) handleFuture.complete(ev.browser());
                    });

            RemoteHandle handle = handleFuture.get(20, TimeUnit.SECONDS);
            assertThat(handle.id()).isPositive();
            Browser browser = new Browser(session, handle);

            // CEF's can_go_back returns int (non-zero == true). A freshly created browser cannot go back.
            assertThat(browser.canGoBack().get(5, TimeUnit.SECONDS)).isZero();

            // goBack returns void; the future just acks completion of the dispatch + UI-thread call.
            browser.goBack().get(5, TimeUnit.SECONDS);

            assertThat(browser.canGoBack().get(5, TimeUnit.SECONDS)).isZero();
        }
    }

    @Test
    void requestWithUnknownHandleDoesNotCrashRuntimeServer() throws Exception {
        try (RuntimeServerProcess server = RUNTIME.spawn();
                CefTransport transport = server.connect();
                CefSession session = new CefSessionImpl(transport, Duration.ofSeconds(30))) {

            // Bogus handle: dispatcher's null-receiver path now sends Kind::Error(ReceiverGone), which the
            // session translates into CefRemoteException. Runtime server stays alive (no UI-thread post, no crash).
            Browser browser = new Browser(session, new RemoteHandle(0xDEADBEEF));
            try {
                browser.goBack().get(5, TimeUnit.SECONDS);
                org.assertj.core.api.Assertions.failBecauseExceptionWasNotThrown(
                        java.util.concurrent.ExecutionException.class);
            } catch (java.util.concurrent.ExecutionException e) {
                Throwable cause = java.util.Objects.requireNonNull(e.getCause());
                assertThat(cause).isInstanceOf(net.kurobako.cef4j.ipc.session.CefRemoteException.class);
                assertThat(((net.kurobako.cef4j.ipc.session.CefRemoteException) cause).code())
                        .isEqualTo(net.kurobako.cef4j.ipc.session.CefRemoteException.CODE_RECEIVER_GONE);
            }
            assertThat(server.isAlive()).isTrue();
        }
    }
}
