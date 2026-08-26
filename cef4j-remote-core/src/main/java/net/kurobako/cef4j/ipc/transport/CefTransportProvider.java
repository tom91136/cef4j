package net.kurobako.cef4j.ipc.transport;

import java.util.function.BooleanSupplier;
import javax.annotation.Nonnull;

/** Service-provider interface for named IPC transports. */
public interface CefTransportProvider {
    @Nonnull
    String name();

    @Nonnull
    CefTransport connect(@Nonnull String endpoint) throws CefTransportException;

    @Nonnull
    default CefTransport connect(@Nonnull String endpoint, @Nonnull BooleanSupplier reconnectContinuity)
            throws CefTransportException {
        return connect(endpoint);
    }
}
