package net.kurobako.cef4j.ipc.protocol.process;

import static org.assertj.core.api.Assertions.assertThat;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.Duration;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.TimeUnit;
import net.kurobako.cef4j.ipc.protocol.gen.V8ContextCreatedEvent;
import net.kurobako.cef4j.ipc.session.CefSession;
import net.kurobako.cef4j.ipc.session.CefSessionImpl;
import net.kurobako.cef4j.ipc.session.process.HelperProcess;
import net.kurobako.cef4j.ipc.transport.ZmqTransport;
import org.junit.jupiter.api.Assumptions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Timeout;

/**
 * End-to-end test for the renderer→browser→JVM relay path. The renderer subprocess installs a
 * {@code cef_render_process_handler_t} whose {@code on_context_created} sends a CEF process message to the browser
 * process; the browser-process {@code cef_client_t::on_process_message_received} translates that into a
 * {@link V8ContextCreatedEvent} and ships it over the IPC PAIR socket.
 *
 * <p>This is the foundation for the JVM observing renderer-process state (V8 context lifecycle, focused-node changes,
 * uncaught exceptions). Method dispatch in the reverse direction (JVM→renderer for V8 method calls) uses the same
 * process-message hop and is tracked separately.
 */
@Timeout(60)
class RendererRelayIntegrationTest {

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
    void v8ContextCreatedEventReachesJvmFromRenderer() throws Exception {
        try (HelperProcess helper = spawnHelperWithEnv();
                ZmqTransport transport = ZmqTransport.connect(helper.endpoint());
                CefSession session = new CefSessionImpl(transport, Duration.ofSeconds(10))) {

            LinkedBlockingQueue<V8ContextCreatedEvent> events = new LinkedBlockingQueue<>();
            session.on(V8ContextCreatedEvent.MESSAGE_ID, V8ContextCreatedEvent.DECODER, events::offer);

            // Bootstrap browser navigates to about:blank, which mints at least one V8 context (the main
            // frame's). The renderer subprocess fires on_context_created once; the relay turns it into the
            // event we're waiting for.
            V8ContextCreatedEvent ev = events.poll(20, TimeUnit.SECONDS);
            assertThat(ev)
                    .as("expected V8ContextCreatedEvent from the renderer relay")
                    .isNotNull();
            assertThat(ev.browser().id()).isPositive();
            // about:blank's main frame URL is "about:blank" once the context is established.
            assertThat(ev.frameUrl()).isNotNull();
        }
    }
}
