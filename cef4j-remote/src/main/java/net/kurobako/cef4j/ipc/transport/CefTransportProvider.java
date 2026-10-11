package net.kurobako.cef4j.ipc.transport;

import java.time.Duration;
import java.util.function.BooleanSupplier;

/** Service-provider interface for named IPC transports. */
public interface CefTransportProvider {
    String name();

    CefTransport connect(String endpoint) throws CefTransportException;

    default CefTransport connect(String endpoint, BooleanSupplier reconnectContinuity) throws CefTransportException {
        return connect(endpoint);
    }

    default CefTransport connect(String endpoint, BooleanSupplier reconnectContinuity, Duration reconnectTimeout)
            throws CefTransportException {
        return connect(endpoint, reconnectContinuity);
    }
}
