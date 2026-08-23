package net.kurobako.cef4j.test;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.time.Duration;
import java.util.Map;
import net.kurobako.cef4j.ipc.frame.RemoteCefBrowserBackend;
import net.kurobako.cef4j.ipc.session.process.RuntimeServerProcess;

public final class RuntimeServerTestEnvironment {
    private final Path binary;
    private final Path resources;

    private RuntimeServerTestEnvironment(Path binary, Path resources) {
        this.binary = binary;
        this.resources = resources;
    }

    public static RuntimeServerTestEnvironment require() {
        Path binary = property("cef4j.runtime.server.binary");
        Path resources = property("cef4j.runtime.server.resources");
        if (!Files.isExecutable(binary)) {
            throw new IllegalStateException("Runtime server is not executable: " + binary);
        }
        if (!Files.isDirectory(resources)) {
            throw new IllegalStateException("CEF runtime is not a directory: " + resources);
        }
        return new RuntimeServerTestEnvironment(binary, resources);
    }

    private static Path property(String name) {
        String value = System.getProperty(name);
        if (value == null || value.isBlank()) {
            throw new IllegalStateException("Missing system property: " + name);
        }
        return Path.of(value);
    }

    public Path binary() {
        return binary;
    }

    public Path resources() {
        return resources;
    }

    public Map<String, String> processEnvironment() {
        return RemoteCefBrowserBackend.runtimeEnvironment(resources);
    }

    public RuntimeServerProcess spawn() throws IOException {
        return spawn("zmq", "tcp://127.0.0.1:0", "shared-file", Duration.ofSeconds(30));
    }

    public RuntimeServerProcess spawn(String transport, String endpoint, String frameTransport, Duration timeout)
            throws IOException {
        return RuntimeServerProcess.spawn(binary, transport, endpoint, frameTransport, timeout, processEnvironment());
    }
}
