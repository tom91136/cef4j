// GENERATED - do not edit. Regenerate via: mvn generate-sources -pl cef4j-native
package net.kurobako.cef4j.gen;

import java.nio.ByteBuffer;
import javax.annotation.Nonnull;

/**
 * Implement this interface to handle HTTP server requests. A new thread will be created for each
 * CefServer.createServer() call (the "dedicated server thread"), and the methods of this class will be called on that
 * thread. It is therefore recommended to use a different CefServerHandler instance for each CefServer.createServer()
 * call to avoid thread safety issues in the CefServerHandler implementation.
 *
 * <p>Definition generated from cef_server_capi.h
 *
 * <pre>typedef struct _cef_server_handler_t {
 *   cef_base_ref_counted_t base;
 *   ...
 * } cef_server_handler_t;</pre>
 *
 * @see <a href="https://cef-builds.spotifycdn.com/docs/146.0/cef__server_8h.html">cef_server.h:209</a>
 */
public interface CefServerHandler extends CefClientHandler {

    /**
     * Called when {@code server} is created. If the server was started successfully then {@link CefServer#isRunning()}
     * will return {@code true}. The server will continue running until {@link CefServer#shutdown()} is called, after
     * which time OnServerDestroyed will be called. If the server failed to start then OnServerDestroyed will be called
     * immediately after this method returns.
     *
     * <p>Definition generated from cef_server_capi.h
     *
     * <pre>void (CEF_CALLBACK* on_server_created)(struct _cef_server_handler_t* self, struct _cef_server_t* server);
     * </pre>
     *
     * @see <a href="https://cef-builds.spotifycdn.com/docs/146.0/cef__server_8h.html">cef_server.h:220</a>
     */
    default void onServerCreated(@Nonnull CefServer server) {}

    /**
     * Called when {@code server} is destroyed. The server thread will be stopped after this method returns. The client
     * should release any references to {@code server} when this method is called. See OnServerCreated documentation for
     * a description of server lifespan.
     *
     * <p>Definition generated from cef_server_capi.h
     *
     * <pre>void (CEF_CALLBACK* on_server_destroyed)(struct _cef_server_handler_t* self, struct _cef_server_t* server);
     * </pre>
     *
     * @see <a href="https://cef-builds.spotifycdn.com/docs/146.0/cef__server_8h.html">cef_server.h:230</a>
     */
    default void onServerDestroyed(@Nonnull CefServer server) {}

    /**
     * Called when a client connects to {@code server}. {@code connection_id} uniquely identifies the connection. Each
     * call to this method will have a matching call to OnClientDisconnected.
     *
     * <p>Definition generated from cef_server_capi.h
     *
     * <pre>
     * void (CEF_CALLBACK* on_client_connected)(struct _cef_server_handler_t* self, struct _cef_server_t* server, int connection_id);
     * </pre>
     *
     * @see <a href="https://cef-builds.spotifycdn.com/docs/146.0/cef__server_8h.html">cef_server.h:239</a>
     */
    default void onClientConnected(@Nonnull CefServer server, int connectionId) {}

    /**
     * Called when a client disconnects from {@code server}. {@code connection_id} uniquely identifies the connection.
     * The client should release any data associated with {@code connection_id} when this method is called and
     * {@code connection_id} should no longer be passed to CefServer methods. Disconnects can originate from either the
     * client or the server. For example, the server will disconnect automatically after a
     * CefServer.sendHttpXxxresponse() method is called.
     *
     * <p>Definition generated from cef_server_capi.h
     *
     * <pre>
     * void (CEF_CALLBACK* on_client_disconnected)(struct _cef_server_handler_t* self, struct _cef_server_t* server, int connection_id);
     * </pre>
     *
     * @see <a href="https://cef-builds.spotifycdn.com/docs/146.0/cef__server_8h.html">cef_server.h:248</a>
     */
    default void onClientDisconnected(@Nonnull CefServer server, int connectionId) {}

    /**
     * Called when {@code server} receives an HTTP request. {@code connection_id} uniquely identifies the connection,
     * {@code client_address} is the requesting IPv4 or IPv6 client address including port number, and {@code request}
     * contains the request contents (URL, method, headers and optional POST data). Call CefServer methods either
     * synchronously or asynchronusly to send a response.
     *
     * <p>Definition generated from cef_server_capi.h
     *
     * <pre>
     * void (CEF_CALLBACK* on_http_request)(struct _cef_server_handler_t* self, struct _cef_server_t* server, int connection_id, const cef_string_t* client_address, struct _cef_request_t* request);
     * </pre>
     *
     * @see <a href="https://cef-builds.spotifycdn.com/docs/146.0/cef__server_8h.html">cef_server.h:260</a>
     */
    default void onHttpRequest(
            @Nonnull CefServer server, int connectionId, @Nonnull String clientAddress, @Nonnull CefRequest request) {}

    /**
     * Called when {@code server} receives a WebSocket request. {@code connection_id} uniquely identifies the
     * connection, {@code client_address} is the requesting IPv4 or IPv6 client address including port number, and
     * {@code request} contains the request contents (URL, method, headers and optional POST data). Execute
     * {@code callback} either synchronously or asynchronously to accept or decline the WebSocket connection. If the
     * request is accepted then OnWebSocketConnected will be called after the WebSocket has connected and incoming
     * messages will be delivered to the OnWebSocketMessage callback. If the request is declined then the client will be
     * disconnected and OnClientDisconnected will be called. Call the {@link CefServer#sendWebSocketMessage(int,
     * ByteBuffer)} method after receiving the OnWebSocketConnected callback to respond with WebSocket messages.
     *
     * <p>Definition generated from cef_server_capi.h
     *
     * <pre>
     * void (CEF_CALLBACK* on_web_socket_request)(struct _cef_server_handler_t* self, struct _cef_server_t* server, int connection_id, const cef_string_t* client_address, struct _cef_request_t* request, struct _cef_callback_t* callback);
     * </pre>
     *
     * @see <a href="https://cef-builds.spotifycdn.com/docs/146.0/cef__server_8h.html">cef_server.h:273</a>
     */
    default void onWebSocketRequest(
            @Nonnull CefServer server,
            int connectionId,
            @Nonnull String clientAddress,
            @Nonnull CefRequest request,
            @Nonnull CefCallback callback) {}

    /**
     * Called after the client has accepted the WebSocket connection for {@code server} and {@code connection_id} via
     * the OnWebSocketRequest callback. See OnWebSocketRequest documentation for intended usage.
     *
     * <p>Definition generated from cef_server_capi.h
     *
     * <pre>
     * void (CEF_CALLBACK* on_web_socket_connected)(struct _cef_server_handler_t* self, struct _cef_server_t* server, int connection_id);
     * </pre>
     *
     * @see <a href="https://cef-builds.spotifycdn.com/docs/146.0/cef__server_8h.html">cef_server.h:294</a>
     */
    default void onWebSocketConnected(@Nonnull CefServer server, int connectionId) {}

    /**
     * Called when {@code server} receives an WebSocket message. {@code connection_id} uniquely identifies the
     * connection, {@code data} is the message content and {@code data_size} is the size of {@code data} in bytes. Do
     * not keep a reference to {@code data} outside of this method. See OnWebSocketRequest documentation for intended
     * usage.
     *
     * <p>Definition generated from cef_server_capi.h
     *
     * <pre>
     * void (CEF_CALLBACK* on_web_socket_message)(struct _cef_server_handler_t* self, struct _cef_server_t* server, int connection_id, const void* data, size_t data_size);
     * </pre>
     *
     * @see <a href="https://cef-builds.spotifycdn.com/docs/146.0/cef__server_8h.html">cef_server.h:303</a>
     */
    default void onWebSocketMessage(@Nonnull CefServer server, int connectionId, @Nonnull ByteBuffer data) {}
}
