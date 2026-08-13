package net.kurobako.cef4j.ipc.transport;

import javax.annotation.Nonnull;

/** Unix-domain socket transport service provider. */
public final class UdsTransportProvider implements CefTransportProvider {
    @Override
    @Nonnull
    public String name() {
        return "uds";
    }

    @Override
    @Nonnull
    public CefTransport connect(@Nonnull String endpoint) throws CefTransportException {
        return connectOptional(endpoint);
    }

    @Nonnull
    static CefTransport connectOptional(@Nonnull String endpoint) throws CefTransportException {
        try {
            return UdsTransport.connect(endpoint);
        } catch (NoClassDefFoundError unavailable) {
            throw new CefTransportException(
                    "UDS requires the optional com.kohlschutter.junixsocket:junixsocket-core dependency", unavailable);
        }
    }
}
