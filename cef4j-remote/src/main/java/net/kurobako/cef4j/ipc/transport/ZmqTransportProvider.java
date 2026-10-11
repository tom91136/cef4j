package net.kurobako.cef4j.ipc.transport;

import java.time.Duration;
import java.util.function.BooleanSupplier;

/** ZeroMQ transport service provider. */
public final class ZmqTransportProvider implements CefTransportProvider {
    @Override
    public String name() {
        return "zmq";
    }

    @Override
    public CefTransport connect(String endpoint) {
        return ZmqTransport.connect(endpoint);
    }

    @Override
    public CefTransport connect(String endpoint, BooleanSupplier reconnectContinuity) {
        return ZmqTransport.connect(endpoint, reconnectContinuity);
    }

    @Override
    public CefTransport connect(String endpoint, BooleanSupplier reconnectContinuity, Duration reconnectTimeout) {
        return ZmqTransport.connect(endpoint, reconnectContinuity, reconnectTimeout);
    }
}
