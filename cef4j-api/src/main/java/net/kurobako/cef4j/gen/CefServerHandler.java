// GENERATED - do not edit. Regenerate via: mvn generate-sources -pl cef4j-native -Dcef.version=146.0.9+g3ca6a87+chromium-146.0.7680.165
package net.kurobako.cef4j.gen;

import javax.annotation.processing.Generated;
import java.nio.ByteBuffer;
import javax.annotation.Nonnull;
import javax.annotation.Nullable;

/**
 * Implement this interface to handle HTTP server requests. A new thread will be created for each net.kurobako.cef4j.gen.CefServer.createServer() call (the "dedicated server thread"), and the methods of this class will be called on that thread. It is therefore recommended to use a different CefServerHandler instance for each net.kurobako.cef4j.gen.CefServer.createServer() call to avoid thread safety issues in the CefServerHandler implementation.
 * <p>Definition generated from cef_server_capi.h
 * <pre>typedef struct _cef_server_handler_t {
 *   cef_base_ref_counted_t base;
 *   ...
 * } cef_server_handler_t;</pre>
 *
 * @see <a href="https://cef-builds.spotifycdn.com/docs/146.0/cef__server_8h.html">cef_server.h:209</a>
 */
@Generated("mvn generate-sources -pl cef4j-native -Dcef.version=146.0.9+g3ca6a87+chromium-146.0.7680.165")
@SuppressWarnings({"SameReturnValue", "EmptyMethod", "UnusedReturnValue", "unused"})
public interface CefServerHandler extends CefClientHandler {

    /**
     * Called when {@code server} is created. If the server was started successfully then {@link net.kurobako.cef4j.gen.CefServer#isRunning()} will return {@code true}. The server will continue running until {@link net.kurobako.cef4j.gen.CefServer#shutdown()} is called, after which time OnServerDestroyed will be called. If the server failed to start then OnServerDestroyed will be called immediately after this method returns.
     * <p>Definition generated from cef_server_capi.h
     * <pre>void (CEF_CALLBACK* on_server_created)(struct _cef_server_handler_t* self, struct _cef_server_t* server);</pre>
     *
     * @see <a href="https://cef-builds.spotifycdn.com/docs/146.0/cef__server_8h.html">cef_server.h:220</a>
     */
    default void onServerCreated(@Nullable CefServer server) {
    }

    /**
     * Called when {@code server} is destroyed. The server thread will be stopped after this method returns. The client should release any references to {@code server} when this method is called. See OnServerCreated documentation for a description of server lifespan.
     * <p>Definition generated from cef_server_capi.h
     * <pre>void (CEF_CALLBACK* on_server_destroyed)(struct _cef_server_handler_t* self, struct _cef_server_t* server);</pre>
     *
     * @see <a href="https://cef-builds.spotifycdn.com/docs/146.0/cef__server_8h.html">cef_server.h:230</a>
     */
    default void onServerDestroyed(@Nullable CefServer server) {
    }

    /**
     * Called when a client connects to {@code server}. {@code connection_id} uniquely identifies the connection. Each call to this method will have a matching call to OnClientDisconnected.
     * <p>Definition generated from cef_server_capi.h
     * <pre>void (CEF_CALLBACK* on_client_connected)(struct _cef_server_handler_t* self, struct _cef_server_t* server, int connection_id);</pre>
     *
     * @see <a href="https://cef-builds.spotifycdn.com/docs/146.0/cef__server_8h.html">cef_server.h:239</a>
     */
    default void onClientConnected(@Nullable CefServer server, int connectionId) {
    }

    /**
     * Called when a client disconnects from {@code server}. {@code connection_id} uniquely identifies the connection. The client should release any data associated with {@code connection_id} when this method is called and {@code connection_id} should no longer be passed to CefServer methods. Disconnects can originate from either the client or the server. For example, the server will disconnect automatically after a net.kurobako.cef4j.gen.CefServer.sendHttpXxxresponse() method is called.
     * <p>Definition generated from cef_server_capi.h
     * <pre>void (CEF_CALLBACK* on_client_disconnected)(struct _cef_server_handler_t* self, struct _cef_server_t* server, int connection_id);</pre>
     *
     * @see <a href="https://cef-builds.spotifycdn.com/docs/146.0/cef__server_8h.html">cef_server.h:248</a>
     */
    default void onClientDisconnected(@Nullable CefServer server, int connectionId) {
    }

    /**
     * Called when {@code server} receives an HTTP request. {@code connection_id} uniquely identifies the connection, {@code client_address} is the requesting IPv4 or IPv6 client address including port number, and {@code request} contains the request contents (URL, method, headers and optional POST data). Call CefServer methods either synchronously or asynchronusly to send a response.
     * <p>Definition generated from cef_server_capi.h
     * <pre>void (CEF_CALLBACK* on_http_request)(struct _cef_server_handler_t* self, struct _cef_server_t* server, int connection_id, const cef_string_t* client_address, struct _cef_request_t* request);</pre>
     *
     * @see <a href="https://cef-builds.spotifycdn.com/docs/146.0/cef__server_8h.html">cef_server.h:260</a>
     */
    default void onHttpRequest(@Nullable CefServer server, int connectionId, @Nullable String clientAddress, @Nullable CefRequest request) {
    }

    /**
     * Called when {@code server} receives a WebSocket request. {@code connection_id} uniquely identifies the connection, {@code client_address} is the requesting IPv4 or IPv6 client address including port number, and {@code request} contains the request contents (URL, method, headers and optional POST data). Execute {@code callback} either synchronously or asynchronously to accept or decline the WebSocket connection. If the request is accepted then OnWebSocketConnected will be called after the WebSocket has connected and incoming messages will be delivered to the OnWebSocketMessage callback. If the request is declined then the client will be disconnected and OnClientDisconnected will be called. Call the {@link net.kurobako.cef4j.gen.CefServer#sendWebSocketMessage(int, java.nio.ByteBuffer)} method after receiving the OnWebSocketConnected callback to respond with WebSocket messages.
     * <p>Definition generated from cef_server_capi.h
     * <pre>void (CEF_CALLBACK* on_web_socket_request)(struct _cef_server_handler_t* self, struct _cef_server_t* server, int connection_id, const cef_string_t* client_address, struct _cef_request_t* request, struct _cef_callback_t* callback);</pre>
     *
     * @see <a href="https://cef-builds.spotifycdn.com/docs/146.0/cef__server_8h.html">cef_server.h:273</a>
     */
    default void onWebSocketRequest(@Nullable CefServer server, int connectionId, @Nullable String clientAddress, @Nullable CefRequest request, @Nullable CefCallback callback) {
    }

    /**
     * Called after the client has accepted the WebSocket connection for {@code server} and {@code connection_id} via the OnWebSocketRequest callback. See OnWebSocketRequest documentation for intended usage.
     * <p>Definition generated from cef_server_capi.h
     * <pre>void (CEF_CALLBACK* on_web_socket_connected)(struct _cef_server_handler_t* self, struct _cef_server_t* server, int connection_id);</pre>
     *
     * @see <a href="https://cef-builds.spotifycdn.com/docs/146.0/cef__server_8h.html">cef_server.h:294</a>
     */
    default void onWebSocketConnected(@Nullable CefServer server, int connectionId) {
    }

    /**
     * Called when {@code server} receives an WebSocket message. {@code connection_id} uniquely identifies the connection, {@code data} is the message content and {@code data_size} is the size of {@code data} in bytes. Do not keep a reference to {@code data} outside of this method. See OnWebSocketRequest documentation for intended usage.
     * <p><b>The C API {@code void*} buffer parameter has been converted to {@link java.nio.ByteBuffer}; the hidden {@code dataSize} parameter is derived from the buffer's capacity.</b>
     * <p>Definition generated from cef_server_capi.h
     * <pre>void (CEF_CALLBACK* on_web_socket_message)(struct _cef_server_handler_t* self, struct _cef_server_t* server, int connection_id, const void* data, size_t data_size);</pre>
     *
     * @param data <b>a direct {@link java.nio.ByteBuffer} whose capacity is the buffer size. This buffer is not reference-counted; its lifetime is not predictable beyond the scope of this callback. Storing a reference to it is unsafe unless explicitly permitted by the CEF documentation and may lead to native crashes.</b>
     *
     * @see <a href="https://cef-builds.spotifycdn.com/docs/146.0/cef__server_8h.html">cef_server.h:303</a>
     */
    default void onWebSocketMessage(@Nullable CefServer server, int connectionId, @Nonnull ByteBuffer data) {
    }
    /**
     * Composite that fans callbacks out to every registered delegate. {@code void} methods invoke all
     * delegates in order; {@code boolean} methods short-circuit on the first {@code true}; handler-returning
     * {@code Optional}s collect every non-empty delegate and wrap them in the handler's own {@code Delegating}
     * wrapper; other {@code Optional}s pick the first non-empty; any other return type yields the first
     * delegate's value.
     */
    class Delegating implements CefServerHandler {
        private final java.util.List<CefServerHandler> delegates;

        public Delegating(java.util.List<CefServerHandler> delegates) {
            this.delegates = java.util.List.copyOf(delegates);
        }

        @Override
        public void onServerCreated(@Nullable CefServer server) {
            for (CefServerHandler d : delegates) d.onServerCreated(server);
        }

        @Override
        public void onServerDestroyed(@Nullable CefServer server) {
            for (CefServerHandler d : delegates) d.onServerDestroyed(server);
        }

        @Override
        public void onClientConnected(@Nullable CefServer server, int connectionId) {
            for (CefServerHandler d : delegates) d.onClientConnected(server, connectionId);
        }

        @Override
        public void onClientDisconnected(@Nullable CefServer server, int connectionId) {
            for (CefServerHandler d : delegates) d.onClientDisconnected(server, connectionId);
        }

        @Override
        public void onHttpRequest(@Nullable CefServer server, int connectionId, @Nullable String clientAddress, @Nullable CefRequest request) {
            for (CefServerHandler d : delegates) d.onHttpRequest(server, connectionId, clientAddress, request);
        }

        @Override
        public void onWebSocketRequest(@Nullable CefServer server, int connectionId, @Nullable String clientAddress, @Nullable CefRequest request, @Nullable CefCallback callback) {
            for (CefServerHandler d : delegates) d.onWebSocketRequest(server, connectionId, clientAddress, request, callback);
        }

        @Override
        public void onWebSocketConnected(@Nullable CefServer server, int connectionId) {
            for (CefServerHandler d : delegates) d.onWebSocketConnected(server, connectionId);
        }

        @Override
        public void onWebSocketMessage(@Nullable CefServer server, int connectionId, @Nonnull ByteBuffer data) {
            for (CefServerHandler d : delegates) d.onWebSocketMessage(server, connectionId, data);
        }
    }

}
