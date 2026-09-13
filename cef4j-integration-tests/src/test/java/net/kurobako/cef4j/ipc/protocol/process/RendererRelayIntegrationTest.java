package net.kurobako.cef4j.ipc.protocol.process;

import static org.assertj.core.api.Assertions.assertThat;

import java.time.Duration;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.TimeUnit;
import net.kurobako.cef4j.ipc.protocol.gen.Browser;
import net.kurobako.cef4j.ipc.protocol.gen.LifeSpanHandlerOnAfterCreatedEvent;
import net.kurobako.cef4j.ipc.protocol.gen.V8ContextCreatedEvent;
import net.kurobako.cef4j.ipc.session.CefSession;
import net.kurobako.cef4j.ipc.session.CefSessionImpl;
import net.kurobako.cef4j.ipc.session.process.RuntimeServerProcess;
import net.kurobako.cef4j.ipc.transport.CefTransport;
import net.kurobako.cef4j.test.RuntimeServerTestEnvironment;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Timeout;

@Timeout(600)
class RendererRelayIntegrationTest {

    private static final RuntimeServerTestEnvironment RUNTIME = RuntimeServerTestEnvironment.require();

    @Test
    void v8ContextCreatedEventReachesJvmFromRenderer() throws Exception {
        try (RuntimeServerProcess server = RUNTIME.spawn();
                CefTransport transport = server.connect();
                CefSession session = new CefSessionImpl(transport, Duration.ofSeconds(30))) {

            LinkedBlockingQueue<V8ContextCreatedEvent> events = new LinkedBlockingQueue<>();
            session.onLatest(V8ContextCreatedEvent.MESSAGE_ID, V8ContextCreatedEvent.DECODER, events::offer);
            LinkedBlockingQueue<LifeSpanHandlerOnAfterCreatedEvent> browsers = new LinkedBlockingQueue<>();
            session.onLatest(
                    LifeSpanHandlerOnAfterCreatedEvent.MESSAGE_ID,
                    LifeSpanHandlerOnAfterCreatedEvent.DECODER,
                    browsers::offer);

            LifeSpanHandlerOnAfterCreatedEvent created = browsers.poll(20, TimeUnit.SECONDS);
            assertThat(created).as("expected bootstrap browser creation event").isNotNull();
            String target = "data:text/html,%3Cscript%3Evoid%200%3C/script%3Ecef4j-navigation-probe";
            Browser browser = new Browser(session, created.browser());
            browser.getMainFrame().thenCompose(frame -> frame.loadUrl(target)).get(20, TimeUnit.SECONDS);

            V8ContextCreatedEvent navigated = pollContextForUrl(events, "cef4j-navigation-probe", 20, TimeUnit.SECONDS);
            assertThat(navigated)
                    .as("expected V8ContextCreatedEvent from the renderer relay")
                    .isNotNull();
            assertThat(navigated.browser()).isEqualTo(created.browser());
            assertThat(navigated.frameUrl()).contains("cef4j-navigation-probe");
        }
    }

    private static V8ContextCreatedEvent pollContextForUrl(
            LinkedBlockingQueue<V8ContextCreatedEvent> contexts, String marker, long timeout, TimeUnit unit)
            throws InterruptedException {
        long deadline = System.nanoTime() + unit.toNanos(timeout);
        while (true) {
            long remaining = deadline - System.nanoTime();
            if (remaining <= 0) throw new AssertionError("timed out waiting for V8 context at " + marker);
            V8ContextCreatedEvent event = contexts.poll(remaining, TimeUnit.NANOSECONDS);
            if (event == null) throw new AssertionError("timed out waiting for V8 context at " + marker);
            if (event.frameUrl().contains(marker)) return event;
        }
    }
}
