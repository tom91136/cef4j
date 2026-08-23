package net.kurobako.cef4j.ipc.protocol.process;

import static org.assertj.core.api.Assertions.assertThat;

import java.time.Duration;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.TimeUnit;
import net.kurobako.cef4j.ipc.protocol.gen.Browser;
import net.kurobako.cef4j.ipc.protocol.gen.BrowserHost;
import net.kurobako.cef4j.ipc.protocol.gen.CefLifeSpanHandler;
import net.kurobako.cef4j.ipc.session.CefSession;
import net.kurobako.cef4j.ipc.session.CefSessionImpl;
import net.kurobako.cef4j.ipc.session.RemoteHandle;
import net.kurobako.cef4j.ipc.session.process.RuntimeServerProcess;
import net.kurobako.cef4j.ipc.transport.ZmqTransport;
import net.kurobako.cef4j.test.RuntimeServerTestEnvironment;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Timeout;

/** Exercises a code-generated synchronous handler return across the intercept wire. */
@Timeout(600)
class HandlerReturnIntegrationTest {

    private static final RuntimeServerTestEnvironment RUNTIME = RuntimeServerTestEnvironment.require();

    @Test
    void doCloseInterceptFiresAndReceivesJvmAnswer() throws Exception {
        try (RuntimeServerProcess server = RUNTIME.spawn();
                ZmqTransport transport = ZmqTransport.connect(server.endpoint());
                CefSession session = new CefSessionImpl(transport, Duration.ofSeconds(30))) {

            CompletableFuture<RemoteHandle> handleFuture = new CompletableFuture<>();
            CompletableFuture<RemoteHandle> doCloseFuture = new CompletableFuture<>();

            CefLifeSpanHandler.register(session, new CefLifeSpanHandler() {
                @Override
                public void onAfterCreated(RemoteHandle browser) {
                    if (!handleFuture.isDone()) handleFuture.complete(browser);
                }

                @Override
                public Boolean doClose(RemoteHandle browser) {
                    if (!doCloseFuture.isDone()) doCloseFuture.complete(browser);
                    return Boolean.FALSE;
                }
            });

            RemoteHandle browserHandle = handleFuture.get(20, TimeUnit.SECONDS);
            Browser browser = new Browser(session, browserHandle);

            BrowserHost host = browser.getHost().get(5, TimeUnit.SECONDS);
            host.closeBrowser(0).get(5, TimeUnit.SECONDS);

            RemoteHandle observed = doCloseFuture.get(15, TimeUnit.SECONDS);
            assertThat(observed).isNotNull();
            assertThat(observed.id()).isPositive();
        }
    }
}
