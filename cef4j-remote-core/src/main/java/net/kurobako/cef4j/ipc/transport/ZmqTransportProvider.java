package net.kurobako.cef4j.ipc.transport;

import javax.annotation.Nonnull;

/** ZeroMQ transport service provider. */
public final class ZmqTransportProvider implements CefTransportProvider {
    @Override
    @Nonnull
    public String name() {
        return "zmq";
    }

    @Override
    @Nonnull
    public CefTransport connect(@Nonnull String endpoint) {
        return ZmqTransport.connect(endpoint);
    }
}
