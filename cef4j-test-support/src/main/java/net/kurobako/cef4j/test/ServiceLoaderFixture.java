package net.kurobako.cef4j.test;

import java.io.IOException;
import java.net.URLClassLoader;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.security.AccessController;
import java.security.PrivilegedAction;
import java.util.Arrays;
import java.util.Objects;
import java.util.stream.Collectors;
import javax.annotation.Nonnull;

/** Installs an isolated service-provider file on the current thread for a test scope. */
public final class ServiceLoaderFixture implements AutoCloseable {
    private final Thread thread;
    private final ClassLoader previousLoader;
    private final URLClassLoader serviceLoader;

    public ServiceLoaderFixture(@Nonnull Path root, @Nonnull Class<?> service, @Nonnull Class<?>... providers)
            throws IOException {
        Objects.requireNonNull(root, "root");
        Objects.requireNonNull(service, "service");
        Objects.requireNonNull(providers, "providers");
        Path serviceFile = root.resolve("META-INF/services").resolve(service.getName());
        Files.createDirectories(Objects.requireNonNull(serviceFile.getParent()));
        String providerNames = Arrays.stream(providers)
                .map(provider -> Objects.requireNonNull(provider, "provider"))
                .map(Class::getName)
                .collect(Collectors.joining(System.lineSeparator(), "", System.lineSeparator()));
        Files.write(serviceFile, providerNames.getBytes(StandardCharsets.UTF_8));

        thread = Thread.currentThread();
        previousLoader = thread.getContextClassLoader();
        java.net.URL serviceRoot = root.toUri().toURL();
        serviceLoader = AccessController.doPrivileged((PrivilegedAction<URLClassLoader>)
                () -> new URLClassLoader(new java.net.URL[] {serviceRoot}, previousLoader));
        thread.setContextClassLoader(serviceLoader);
    }

    public boolean isActive() {
        return thread.getContextClassLoader() == serviceLoader;
    }

    @Override
    public void close() throws IOException {
        thread.setContextClassLoader(previousLoader);
        serviceLoader.close();
    }
}
