package net.kurobako.cef4j.ipc.transport;

import javax.annotation.Nonnull;

/** Service-provider interface for named IPC transports. */
public interface CefTransportProvider {
    @Nonnull
    String name();

    @Nonnull
    CefTransport connect(@Nonnull String endpoint) throws CefTransportException;
}
