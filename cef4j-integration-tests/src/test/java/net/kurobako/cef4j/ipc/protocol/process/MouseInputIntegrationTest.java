package net.kurobako.cef4j.ipc.protocol.process;

import static org.assertj.core.api.Assertions.assertThat;

import java.time.Duration;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.TimeUnit;
import net.kurobako.cef4j.gen.CefMouseEvent;
import net.kurobako.cef4j.ipc.protocol.gen.Browser;
import net.kurobako.cef4j.ipc.protocol.gen.BrowserHandleMessage;
import net.kurobako.cef4j.ipc.protocol.gen.BrowserHost;
import net.kurobako.cef4j.ipc.protocol.gen.EvaluateJavascriptRequest;
import net.kurobako.cef4j.ipc.protocol.gen.Frame;
import net.kurobako.cef4j.ipc.protocol.gen.MessageIds;
import net.kurobako.cef4j.ipc.protocol.gen.ValueKindIntBoolValueBoolIntValueIntDoubleValueLongStringValueStringErrorMessageStringEtc6a348bMessage;
import net.kurobako.cef4j.ipc.session.CefSession;
import net.kurobako.cef4j.ipc.session.CefSessionImpl;
import net.kurobako.cef4j.ipc.session.RemoteHandle;
import net.kurobako.cef4j.ipc.session.process.RuntimeServerProcess;
import net.kurobako.cef4j.ipc.transport.CefTransport;
import net.kurobako.cef4j.test.RuntimeServerTestEnvironment;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Timeout;

@Timeout(600)
class MouseInputIntegrationTest {

    private static final RuntimeServerTestEnvironment RUNTIME = RuntimeServerTestEnvironment.require();

    @Test
    void mouseClickEventDispatchesAcrossWire() throws Exception {
        try (RuntimeServerProcess server = RUNTIME.spawn();
                CefTransport transport = server.connect();
                CefSession session = new CefSessionImpl(transport, Duration.ofSeconds(30))) {

            CompletableFuture<RemoteHandle> handleFuture = new CompletableFuture<>();
            session.onLatest(MessageIds.LIFE_SPAN_HANDLER_ON_AFTER_CREATED_EVENT, BrowserHandleMessage.DECODER, e -> {
                if (!handleFuture.isDone()) handleFuture.complete(e.browser());
            });
            RemoteHandle browserHandle = handleFuture.get(20, TimeUnit.SECONDS);
            Browser browser = new Browser(session, browserHandle);
            BrowserHost host = browser.getHost().get(5, TimeUnit.SECONDS);

            host.sendMouseClickEvent(new CefMouseEvent(50, 50, 0), 0, 0, 1).get(5, TimeUnit.SECONDS);
            host.sendMouseClickEvent(new CefMouseEvent(50, 50, 0), 0, 1, 1).get(5, TimeUnit.SECONDS);
            host.sendMouseMoveEvent(new CefMouseEvent(75, 75, 0), 0).get(5, TimeUnit.SECONDS);
            host.sendMouseWheelEvent(new CefMouseEvent(100, 100, 0), 0, 120).get(5, TimeUnit.SECONDS);

            Frame frame = browser.getMainFrame().get(5, TimeUnit.SECONDS);
            ValueKindIntBoolValueBoolIntValueIntDoubleValueLongStringValueStringErrorMessageStringEtc6a348bMessage
                    resp = session.request(
                                    MessageIds.EVALUATE_JAVASCRIPT_REQUEST,
                                    new EvaluateJavascriptRequest(frame.handle(), "1 + 1", false),
                                    ValueKindIntBoolValueBoolIntValueIntDoubleValueLongStringValueStringErrorMessageStringEtc6a348bMessage
                                            .DECODER)
                            .get(5, TimeUnit.SECONDS);
            assertThat(resp.intValue()).isEqualTo(2);
        }
    }
}
