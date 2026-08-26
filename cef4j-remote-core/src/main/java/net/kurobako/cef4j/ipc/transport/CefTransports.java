package net.kurobako.cef4j.ipc.transport;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.ServiceLoader;
import java.util.function.BooleanSupplier;
import javax.annotation.Nonnull;

/** Discovers transport modules through {@link ServiceLoader}. */
public final class CefTransports {
    private CefTransports() {}

    @Nonnull
    public static CefTransport connect(@Nonnull String name, @Nonnull String endpoint) throws CefTransportException {
        return provider(name).connect(endpoint);
    }

    @Nonnull
    public static CefTransport connect(
            @Nonnull String name, @Nonnull String endpoint, @Nonnull BooleanSupplier reconnectContinuity)
            throws CefTransportException {
        return provider(name).connect(endpoint, reconnectContinuity);
    }

    private static CefTransportProvider provider(String name) throws CefTransportException {
        for (CefTransportProvider provider : ServiceLoader.load(CefTransportProvider.class)) {
            if (provider.name().equalsIgnoreCase(name)) return provider;
        }
        throw new CefTransportException(
                "No cef4j transport provider named '" + name + "' is present; available: " + available());
    }

    @Nonnull
    public static List<String> available() {
        List<String> names = new ArrayList<>();
        for (CefTransportProvider provider : ServiceLoader.load(CefTransportProvider.class)) names.add(provider.name());
        Collections.sort(names);
        return Collections.unmodifiableList(names);
    }
}
