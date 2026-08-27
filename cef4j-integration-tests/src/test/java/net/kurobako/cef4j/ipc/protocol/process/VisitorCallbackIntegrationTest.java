package net.kurobako.cef4j.ipc.protocol.process;

import static org.assertj.core.api.Assertions.assertThat;

import java.time.Duration;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;
import net.kurobako.cef4j.ipc.protocol.gen.Browser;
import net.kurobako.cef4j.ipc.protocol.gen.CefStringVisitor;
import net.kurobako.cef4j.ipc.protocol.gen.Frame;
import net.kurobako.cef4j.ipc.protocol.gen.LifeSpanHandlerOnAfterCreatedEvent;
import net.kurobako.cef4j.ipc.session.CefSession;
import net.kurobako.cef4j.ipc.session.CefSessionImpl;
import net.kurobako.cef4j.ipc.session.JvmCallbackTable;
import net.kurobako.cef4j.ipc.session.RemoteHandle;
import net.kurobako.cef4j.ipc.session.process.RuntimeServerProcess;
import net.kurobako.cef4j.ipc.transport.CefTransport;
import net.kurobako.cef4j.test.RuntimeServerTestEnvironment;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Timeout;

@Timeout(600)
class VisitorCallbackIntegrationTest {

    private static final RuntimeServerTestEnvironment RUNTIME = RuntimeServerTestEnvironment.require();

    @Test
    void getSourceVisitorRoundTrip() throws Exception {
        try (RuntimeServerProcess server = RUNTIME.spawn();
                CefTransport transport = server.connect();
                CefSession session = new CefSessionImpl(transport, Duration.ofSeconds(30))) {

            JvmCallbackTable<CefStringVisitor> visitors = new JvmCallbackTable<>();
            CefSession.HandlerRegistration visitorRoute = CefStringVisitor.route(session, visitors);

            CompletableFuture<RemoteHandle> handleFuture = new CompletableFuture<>();
            session.onLatest(
                    LifeSpanHandlerOnAfterCreatedEvent.MESSAGE_ID, LifeSpanHandlerOnAfterCreatedEvent.DECODER, ev -> {
                        if (!handleFuture.isDone()) handleFuture.complete(ev.browser());
                    });

            RemoteHandle browserHandle = handleFuture.get(20, TimeUnit.SECONDS);
            try (visitorRoute;
                    Browser browser = new Browser(session, browserHandle);
                    Frame mainFrame = browser.getMainFrame().get(5, TimeUnit.SECONDS)) {
                String dataUrl = "data:text/html,<html><body>RMI-callback-marker-9341</body></html>";
                mainFrame.loadUrl(dataUrl).get(5, TimeUnit.SECONDS);

                String observed = "";
                long deadline = System.nanoTime() + Duration.ofSeconds(20).toNanos();
                while (System.nanoTime() < deadline && !observed.contains("RMI-callback-marker-9341")) {
                    CompletableFuture<String> sourceFuture = new CompletableFuture<>();
                    CefStringVisitor visitor = text -> {
                        if (!sourceFuture.isDone()) sourceFuture.complete(text);
                    };
                    int callbackId = visitors.register(visitor);
                    try {
                        try (Frame currentFrame = browser.getMainFrame().get(5, TimeUnit.SECONDS)) {
                            currentFrame.getSource(new RemoteHandle(callbackId)).get(5, TimeUnit.SECONDS);
                            try {
                                observed = sourceFuture.get(5, TimeUnit.SECONDS);
                            } catch (TimeoutException ignored) {
                                observed = "";
                            }
                        }
                    } finally {
                        visitors.release(callbackId);
                    }
                    if (!observed.contains("RMI-callback-marker-9341")) Thread.sleep(100);
                }
                assertThat(observed).contains("RMI-callback-marker-9341");
            }
        }
    }
}
