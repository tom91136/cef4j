package net.kurobako.cef4j.ipc.protocol.process;

import static org.assertj.core.api.Assertions.assertThat;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.Duration;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.TimeUnit;
import net.kurobako.cef4j.ipc.protocol.gen.Browser;
import net.kurobako.cef4j.ipc.protocol.gen.LifeSpanHandlerOnAfterCreatedEvent;
import net.kurobako.cef4j.ipc.session.CefSession;
import net.kurobako.cef4j.ipc.session.CefSessionImpl;
import net.kurobako.cef4j.ipc.session.RemoteHandle;
import net.kurobako.cef4j.ipc.session.process.HelperProcess;
import net.kurobako.cef4j.ipc.transport.ZmqTransport;
import org.junit.jupiter.api.Assumptions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Timeout;

/**
 * End-to-end through the real C++ helper, exercising remote method invocation via {@link RemoteHandle}: the helper
 * allocates a handle for the browser created at startup, the generated LifeSpanHandlerForwarder emits a
 * {@link LifeSpanHandlerOnAfterCreatedEvent}, and the JVM side wraps it in a {@link Browser} facade and dispatches
 * `canGoBack`/`goBack` (AST-derived methods, generated dispatcher case in `Dispatcher.h`).
 *
 * <p>Smallest test validating the whole {@code RemoteHandle} round-trip through the AST pipeline: handle allocation,
 * wire encoding, helper-side dispatch with handle resolution, typed response decoding.
 */
@Timeout(60)
class RemoteHandleIntegrationTest {

    private static Path helperBinary;
    private static Path cefResources;

    @BeforeAll
    static void resolveBinary() {
        String bin = System.getProperty("cef4j.ipc.helper.binary");
        String res = System.getProperty("cef4j.ipc.cef.resources");
        Assumptions.assumeTrue(bin != null, "cef4j.ipc.helper.binary system property not set");
        Assumptions.assumeTrue(res != null, "cef4j.ipc.cef.resources system property not set");
        helperBinary = Paths.get(bin);
        cefResources = Paths.get(res);
        Assumptions.assumeTrue(Files.isExecutable(helperBinary), "helper binary not built at " + helperBinary);
        Assumptions.assumeTrue(Files.isDirectory(cefResources), "CEF resources dir missing at " + cefResources);
    }

    private static HelperProcess spawnHelperWithEnv() throws IOException {
        Path tmpDir = Files.createTempDirectory("cef4j-helper-launcher");
        Path script = tmpDir.resolve("helper-launch.sh");
        String content = "#!/bin/sh\n"
                + "export CEF_RESOURCES_DIR=\"" + cefResources + "\"\n"
                + "export LD_LIBRARY_PATH=\"" + cefResources + ":${LD_LIBRARY_PATH:-}\"\n"
                + "exec \"" + helperBinary + "\" \"$@\"\n";
        Files.writeString(script, content);
        script.toFile().setExecutable(true);
        return HelperProcess.spawn(script, "tcp://127.0.0.1:0", Duration.ofSeconds(30));
    }

    @Test
    void remoteHandleRoundTripThroughRealHelper() throws Exception {
        try (HelperProcess helper = spawnHelperWithEnv();
                ZmqTransport transport = ZmqTransport.connect(helper.endpoint());
                CefSession session = new CefSessionImpl(transport, Duration.ofSeconds(10))) {

            CompletableFuture<RemoteHandle> handleFuture = new CompletableFuture<>();
            session.on(
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
    void requestWithUnknownHandleDoesNotCrashHelper() throws Exception {
        try (HelperProcess helper = spawnHelperWithEnv();
                ZmqTransport transport = ZmqTransport.connect(helper.endpoint());
                CefSession session = new CefSessionImpl(transport, Duration.ofSeconds(2))) {

            // Bogus handle: dispatcher's null-receiver path now sends Kind::Error(ReceiverGone), which the
            // session translates into CefRemoteException. Helper stays alive (no UI-thread post, no crash).
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
            assertThat(helper.isAlive()).isTrue();
        }
    }
}
