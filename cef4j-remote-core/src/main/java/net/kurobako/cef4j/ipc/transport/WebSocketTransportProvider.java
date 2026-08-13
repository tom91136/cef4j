package net.kurobako.cef4j.ipc.transport;

import javax.annotation.Nonnull;

/** Service-loader entry for the WebSocket transport. */
public final class WebSocketTransportProvider implements CefTransportProvider {
    @Override
    @Nonnull
    public String name() {
        return "websocket";
    }

    @Override
    @Nonnull
    public CefTransport connect(@Nonnull String endpoint) throws CefTransportException {
        return WebSocketTransport.connect(endpoint);
    }
}
