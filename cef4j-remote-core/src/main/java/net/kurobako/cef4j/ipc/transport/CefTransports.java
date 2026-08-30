package net.kurobako.cef4j.ipc.transport;

import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
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
        CefTransportProvider provider = providers().get(name.toLowerCase(Locale.ROOT));
        if (provider != null) return provider;
        throw new CefTransportException(
                "No cef4j transport provider named '" + name + "' is present; available: " + available());
    }

    @Nonnull
    public static List<String> available() {
        List<String> names = new ArrayList<>(providers().keySet());
        Collections.sort(names);
        return Collections.unmodifiableList(names);
    }

    private static Map<String, CefTransportProvider> providers() {
        Map<String, CefTransportProvider> providers = new LinkedHashMap<>();
        for (CefTransportProvider provider : ServiceLoader.load(CefTransportProvider.class)) {
            String name = provider.name().toLowerCase(Locale.ROOT);
            CefTransportProvider previous = providers.putIfAbsent(name, provider);
            if (previous != null) {
                throw new IllegalStateException("Duplicate cef4j transport provider name '" + name + "': "
                        + previous.getClass().getName() + ", "
                        + provider.getClass().getName());
            }
        }
        return providers;
    }
}
