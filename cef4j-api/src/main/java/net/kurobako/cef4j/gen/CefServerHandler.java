// GENERATED - do not edit. Regenerate via: mvn generate-sources -pl cef4j-native
package net.kurobako.cef4j.gen;

import javax.annotation.Nonnull;

/**
 * Implement this interface to handle HTTP server requests. A new thread will be created for each
 * CefServer::CreateServer call (the "dedicated server thread"), and the methods of this class will be called on that
 * thread. It is therefore recommended to use a different CefServerHandler instance for each CefServer::CreateServer
 * call to avoid thread safety issues in the CefServerHandler implementation.
 */
public interface CefServerHandler {

    /**
     * Called when |server| is created. If the server was started successfully then CefServer::IsRunning will return
     * true. The server will continue running until CefServer::Shutdown is called, after which time OnServerDestroyed
     * will be called. If the server failed to start then OnServerDestroyed will be called immediately after this method
     * returns.
     */
    default void onServerCreated(long server) {}

    /**
     * Called when |server| is destroyed. The server thread will be stopped after this method returns. The client should
     * release any references to |server| when this method is called. See OnServerCreated documentation for a
     * description of server lifespan.
     */
    default void onServerDestroyed(long server) {}

    /**
     * Called when a client connects to |server|. |connection_id| uniquely identifies the connection. Each call to this
     * method will have a matching call to OnClientDisconnected.
     */
    default void onClientConnected(long server, int connectionId) {}

    /**
     * Called when a client disconnects from |server|. |connection_id| uniquely identifies the connection. The client
     * should release any data associated with |connection_id| when this method is called and |connection_id| should no
     * longer be passed to CefServer methods. Disconnects can originate from either the client or the server. For
     * example, the server will disconnect automatically after a CefServer::SendHttpXXXResponse method is called.
     */
    default void onClientDisconnected(long server, int connectionId) {}

    /**
     * Called when |server| receives an HTTP request. |connection_id| uniquely identifies the connection,
     * |client_address| is the requesting IPv4 or IPv6 client address including port number, and |request| contains the
     * request contents (URL, method, headers and optional POST data). Call CefServer methods either synchronously or
     * asynchronusly to send a response.
     */
    default void onHttpRequest(long server, int connectionId, @Nonnull String clientAddress, long request) {}

    /**
     * Called when |server| receives a WebSocket request. |connection_id| uniquely identifies the connection,
     * |client_address| is the requesting IPv4 or IPv6 client address including port number, and |request| contains the
     * request contents (URL, method, headers and optional POST data). Execute |callback| either synchronously or
     * asynchronously to accept or decline the WebSocket connection. If the request is accepted then
     * OnWebSocketConnected will be called after the WebSocket has connected and incoming messages will be delivered to
     * the OnWebSocketMessage callback. If the request is declined then the client will be disconnected and
     * OnClientDisconnected will be called. Call the CefServer::SendWebSocketMessage method after receiving the
     * OnWebSocketConnected callback to respond with WebSocket messages.
     */
    default void onWebSocketRequest(
            long server, int connectionId, @Nonnull String clientAddress, long request, long callback) {}

    /**
     * Called after the client has accepted the WebSocket connection for |server| and |connection_id| via the
     * OnWebSocketRequest callback. See OnWebSocketRequest documentation for intended usage.
     */
    default void onWebSocketConnected(long server, int connectionId) {}

    /**
     * Called when |server| receives an WebSocket message. |connection_id| uniquely identifies the connection, |data| is
     * the message content and |data_size| is the size of |data| in bytes. Do not keep a reference to |data| outside of
     * this method. See OnWebSocketRequest documentation for intended usage.
     */
    default void onWebSocketMessage(long server, int connectionId, long data, long dataSize) {}
}
