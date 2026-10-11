package net.kurobako.cef4j.ipc.transport;

/** Service-loader entry for the WebSocket transport. */
public final class WebSocketTransportProvider implements CefTransportProvider {
    @Override
    public String name() {
        return "websocket";
    }

    @Override
    public CefTransport connect(String endpoint) throws CefTransportException {
        return WebSocketTransport.connect(endpoint);
    }
}
