package net.kurobako.cef4j.ipc.transport;

import javax.annotation.Nonnull;

/**
 * Platform-neutral local transport. Loopback ZMQ is the pure-Java Java-11 fallback; Unix sockets remain an explicit
 * optional optimization, while Windows runtime servers advertise a {@code pipe://} endpoint.
 */
public final class LocalTransportProvider implements CefTransportProvider {
    @Override
    @Nonnull
    public String name() {
        return "local";
    }

    @Override
    @Nonnull
    public CefTransport connect(@Nonnull String endpoint) throws CefTransportException {
        if (endpoint.startsWith("unix://")) return UdsTransportProvider.connectOptional(endpoint);
        if (endpoint.startsWith("pipe://") || endpoint.startsWith("\\\\.\\pipe\\")) {
            return NamedPipeTransport.connect(endpoint);
        }
        if (endpoint.startsWith("tcp://")) return ZmqTransport.connect(endpoint);
        throw new CefTransportException("Unsupported local endpoint: " + endpoint);
    }
}
