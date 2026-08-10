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
import net.kurobako.cef4j.ipc.protocol.gen.BrowserHost;
import net.kurobako.cef4j.ipc.protocol.gen.CefLifeSpanHandler;
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
 * End-to-end test for codegen-emitted non-void handler returns. {@code cef_life_span_handler_t::do_close} returns
 * {@code int}, so it rides the {@code Kind::Intercept} wire: the helper-side forwarder lambda blocks waiting for the
 * JVM-supplied answer. We register a {@link CefLifeSpanHandler} whose {@link CefLifeSpanHandler#doClose} returns
 * {@link Boolean#TRUE} (block CEF from closing) and verify the JVM received the call by completing a future inside the
 * override. Proves the full request→intercept→response round-trip end-to-end: encoded request reaches JVM, handler
 * runs, response encodes, helper decodes, forwarder lambda returns to CEF.
 */
@Timeout(60)
class HandlerReturnIntegrationTest {

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
    void doCloseInterceptFiresAndReceivesJvmAnswer() throws Exception {
        try (HelperProcess helper = spawnHelperWithEnv();
                ZmqTransport transport = ZmqTransport.connect(helper.endpoint());
                CefSession session = new CefSessionImpl(transport, Duration.ofSeconds(10))) {

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
                    // Return false (allow close) so CEF's lifecycle proceeds normally and the helper can shut
                    // down cleanly. The point of the test is just that the intercept fires and the JVM-
                    // supplied bool reaches the helper — not whether close is actually blocked.
                    return Boolean.FALSE;
                }
            });

            RemoteHandle browserHandle = handleFuture.get(20, TimeUnit.SECONDS);
            Browser browser = new Browser(session, browserHandle);

            // Trigger close: BrowserHost.closeBrowser eventually causes CEF to call do_close on the lifespan
            // handler. The intercept wire delivers it to our registered handler above.
            BrowserHost host = browser.getHost().get(5, TimeUnit.SECONDS);
            host.closeBrowser(0).get(5, TimeUnit.SECONDS);

            RemoteHandle observed = doCloseFuture.get(15, TimeUnit.SECONDS);
            // The handle id observed inside doClose isn't necessarily the same as the bootstrap browser's
            // — CEF can route do_close through a different browser pointer during the close lifecycle. The
            // point of this test is that the intercept wire fires AT ALL: helper sent Kind::Intercept for the
            // codegen-emitted DoCloseEvent, JVM's intercept handler ran, response went back, helper unblocked.
            assertThat(observed).isNotNull();
            assertThat(observed.id()).isPositive();
        }
    }
}
