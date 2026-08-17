package net.kurobako.cef4j.test;

import java.nio.file.Files;
import java.nio.file.Path;

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
}
