package net.kurobako.cef4j.ipc.transport;

/** Unix-domain socket transport service provider. */
public final class UdsTransportProvider implements CefTransportProvider {
    @Override
    public String name() {
        return "uds";
    }

    @Override
    public CefTransport connect(String endpoint) throws CefTransportException {
        return connectOptional(endpoint);
    }

    static CefTransport connectOptional(String endpoint) throws CefTransportException {
        try {
            return UdsTransport.connect(endpoint);
        } catch (NoClassDefFoundError unavailable) {
            throw new CefTransportException(
                    "UDS requires the optional com.kohlschutter.junixsocket:junixsocket-core dependency", unavailable);
        }
    }
}
