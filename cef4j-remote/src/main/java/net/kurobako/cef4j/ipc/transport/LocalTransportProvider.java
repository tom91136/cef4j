package net.kurobako.cef4j.ipc.transport;

import java.time.Duration;
import java.util.function.BooleanSupplier;

/**
 * Platform-neutral local transport. Loopback ZMQ is the pure-Java Java-11 fallback; Unix sockets remain an explicit
 * optional optimization, while Windows runtime servers advertise a {@code pipe://} endpoint.
 */
public final class LocalTransportProvider implements CefTransportProvider {
    @Override
    public String name() {
        return "local";
    }

    @Override
    public CefTransport connect(String endpoint) throws CefTransportException {
        return connect(endpoint, () -> false);
    }

    @Override
    public CefTransport connect(String endpoint, BooleanSupplier reconnectContinuity) throws CefTransportException {
        if (endpoint.startsWith("unix://")) return UdsTransportProvider.connectOptional(endpoint);
        if (endpoint.startsWith("pipe://") || endpoint.startsWith("\\\\.\\pipe\\")) {
            return NamedPipeTransport.connect(endpoint);
        }
        if (endpoint.startsWith("tcp://")) return ZmqTransport.connect(endpoint, reconnectContinuity);
        throw new CefTransportException("Unsupported local endpoint: " + endpoint);
    }

    @Override
    public CefTransport connect(String endpoint, BooleanSupplier reconnectContinuity, Duration reconnectTimeout)
            throws CefTransportException {
        if (endpoint.startsWith("tcp://")) {
            return ZmqTransport.connect(endpoint, reconnectContinuity, reconnectTimeout);
        }
        return connect(endpoint, reconnectContinuity);
    }
}
