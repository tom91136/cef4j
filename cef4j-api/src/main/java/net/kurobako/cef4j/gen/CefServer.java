// GENERATED - do not edit. Regenerate via: mvn generate-sources -pl cef4j-native
package net.kurobako.cef4j.gen;

import java.nio.ByteBuffer;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import javax.annotation.Nonnull;
import javax.annotation.Nullable;

/**
 * Class representing a server that supports HTTP and WebSocket requests. Server capacity is limited and is intended to
 * handle only a small number of simultaneous connections (e.g. for communicating between applications on localhost).
 * The methods of this class are safe to call from any thread in the brower process unless otherwise indicated.
 *
 * <p>Definition generated from cef_server_capi.h
 *
 * <pre>typedef struct _cef_server_t {
 *   cef_base_ref_counted_t base;
 *   ...
 * } cef_server_t;</pre>
 *
 * @see <a href="https://cef-builds.spotifycdn.com/docs/146.0/cef__server_8h.html">cef_server.h:50</a>
 */
public interface CefServer extends CefLibraryObject {

    /**
     * Returns the CefTaskRunner that will execute code on this thread's message loop. This method is safe to call from
     * any thread.
     *
     * <p>Definition generated from cef_server_capi.h
     *
     * <pre>cef_task_runner_t* (CEF_CALLBACK* get_task_runner)(struct _cef_server_t* self);</pre>
     *
     * @see <a href="https://cef-builds.spotifycdn.com/docs/146.0/cef__thread_8h.html">cef_thread.h:87</a>
     */
    Optional<CefTaskRunner> getTaskRunner();

    /**
     * Stop the server and shut down the dedicated server thread. See
     * {@link CefServerHandler#onServerCreated(CefServer)} documentation for a description of server lifespan.
     *
     * <p>Definition generated from cef_server_capi.h
     *
     * <pre>void (CEF_CALLBACK* shutdown)(struct _cef_server_t* self);</pre>
     *
     * @see <a href="https://cef-builds.spotifycdn.com/docs/146.0/cef__server_8h.html">cef_server.h:88</a>
     */
    void shutdown();

    /**
     * Returns {@code true} if the thread is currently running. This method must be called from the same thread that
     * called CreateThread().
     *
     * <p>Definition generated from cef_server_capi.h
     *
     * <pre>int (CEF_CALLBACK* is_running)(struct _cef_server_t* self);</pre>
     *
     * @see <a href="https://cef-builds.spotifycdn.com/docs/146.0/cef__thread_8h.html">cef_thread.h:109</a>
     */
    boolean isRunning();

    /**
     * Returns the server address including the port number.
     *
     * <p>Definition generated from cef_server_capi.h
     *
     * <pre>cef_string_userfree_t (CEF_CALLBACK* get_address)(struct _cef_server_t* self);</pre>
     *
     * @see <a href="https://cef-builds.spotifycdn.com/docs/146.0/cef__server_8h.html">cef_server.h:105</a>
     */
    Optional<String> getAddress();

    /**
     * Returns {@code true} if the server currently has a connection. This method must be called on the dedicated server
     * thread.
     *
     * <p>Definition generated from cef_server_capi.h
     *
     * <pre>int (CEF_CALLBACK* has_connection)(struct _cef_server_t* self);</pre>
     *
     * @see <a href="https://cef-builds.spotifycdn.com/docs/146.0/cef__server_8h.html">cef_server.h:111</a>
     */
    boolean hasConnection();

    /**
     * Returns {@code true} if {@code connection_id} represents a valid connection. This method must be called on the
     * dedicated server thread.
     *
     * <p>Definition generated from cef_server_capi.h
     *
     * <pre>int (CEF_CALLBACK* is_valid_connection)(struct _cef_server_t* self, int connection_id);</pre>
     *
     * @see <a href="https://cef-builds.spotifycdn.com/docs/146.0/cef__server_8h.html">cef_server.h:118</a>
     */
    boolean isValidConnection(int connectionId);

    /**
     * Send an HTTP 200 "OK" response to the connection identified by {@code connection_id}. {@code content_type} is the
     * response content type (e.g. "text/html"), {@code data} is the response content, and {@code data_size} is the size
     * of {@code data} in bytes. The contents of {@code data} will be copied. The connection will be closed
     * automatically after the response is sent.
     *
     * <p>Definition generated from cef_server_capi.h
     *
     * <pre>
     * void (CEF_CALLBACK* send_http200_response)(struct _cef_server_t* self, int connection_id, const cef_string_t* content_type, const void* data, size_t data_size);
     * </pre>
     *
     * @see <a href="https://cef-builds.spotifycdn.com/docs/146.0/cef__server_8h.html">cef_server.h:125</a>
     */
    void sendHttp200response(int connectionId, @Nonnull String contentType, @Nonnull ByteBuffer data);

    /**
     * Send an HTTP 404 "Not Found" response to the connection identified by {@code connection_id}. The connection will
     * be closed automatically after the response is sent.
     *
     * <p>Definition generated from cef_server_capi.h
     *
     * <pre>void (CEF_CALLBACK* send_http404_response)(struct _cef_server_t* self, int connection_id);</pre>
     *
     * @see <a href="https://cef-builds.spotifycdn.com/docs/146.0/cef__server_8h.html">cef_server.h:138</a>
     */
    void sendHttp404response(int connectionId);

    /**
     * Send an HTTP 500 "Internal Server Error" response to the connection identified by {@code connection_id}.
     * {@code error_message} is the associated error message. The connection will be closed automatically after the
     * response is sent.
     *
     * <p>Definition generated from cef_server_capi.h
     *
     * <pre>
     * void (CEF_CALLBACK* send_http500_response)(struct _cef_server_t* self, int connection_id, const cef_string_t* error_message);
     * </pre>
     *
     * @see <a href="https://cef-builds.spotifycdn.com/docs/146.0/cef__server_8h.html">cef_server.h:146</a>
     */
    void sendHttp500response(int connectionId, @Nonnull String errorMessage);

    /**
     * Send a custom HTTP response to the connection identified by {@code connection_id}. {@code response_code} is the
     * HTTP response code sent in the status line (e.g. 200), {@code content_type} is the response content type sent as
     * the "Content-Type" header (e.g. "text/html"), {@code content_length} is the expected content length, and
     * {@code extra_headers} is the map of extra response headers. If {@code content_length} is >= 0 then the
     * "Content-Length" header will be sent. If {@code content_length} is 0 then no content is expected and the
     * connection will be closed automatically after the response is sent. If {@code content_length} is < 0 then no
     * "Content-Length" header will be sent and the client will continue reading until the connection is closed. Use the
     * SendRawData method to send the content, if applicable, and call CloseConnection after all content has been sent.
     *
     * <p>Definition generated from cef_server_capi.h
     *
     * <pre>
     * void (CEF_CALLBACK* send_http_response)(struct _cef_server_t* self, int connection_id, int response_code, const cef_string_t* content_type, int64_t content_length, cef_string_multimap_t extra_headers);
     * </pre>
     *
     * @param extraHeaders may be null
     * @see <a href="https://cef-builds.spotifycdn.com/docs/146.0/cef__server_8h.html">cef_server.h:156</a>
     */
    void sendHttpResponse(
            int connectionId,
            int responseCode,
            @Nonnull String contentType,
            long contentLength,
            @Nullable Map<String, List<String>> extraHeaders);

    /**
     * Send raw data directly to the connection identified by {@code connection_id}. {@code data} is the raw data and
     * {@code data_size} is the size of {@code data} in bytes. The contents of {@code data} will be copied. No
     * validation of {@code data} is performed internally so the client should be careful to send the amount indicated
     * by the "Content-Length" header, if specified. See SendHttpResponse documentation for intended usage.
     *
     * <p>Definition generated from cef_server_capi.h
     *
     * <pre>
     * void (CEF_CALLBACK* send_raw_data)(struct _cef_server_t* self, int connection_id, const void* data, size_t data_size);
     * </pre>
     *
     * @see <a href="https://cef-builds.spotifycdn.com/docs/146.0/cef__server_8h.html">cef_server.h:177</a>
     */
    void sendRawData(int connectionId, @Nonnull ByteBuffer data);

    /**
     * Close the connection identified by {@code connection_id}. See SendHttpResponse documentation for intended usage.
     *
     * <p>Definition generated from cef_server_capi.h
     *
     * <pre>void (CEF_CALLBACK* close_connection)(struct _cef_server_t* self, int connection_id);</pre>
     *
     * @see <a href="https://cef-builds.spotifycdn.com/docs/146.0/cef__server_8h.html">cef_server.h:190</a>
     */
    void closeConnection(int connectionId);

    /**
     * Send a WebSocket message to the connection identified by {@code connection_id}. {@code data} is the response
     * content and {@code data_size} is the size of {@code data} in bytes. The contents of {@code data} will be copied.
     * See {@link CefServerHandler#onWebSocketRequest(CefServer, int, String, CefRequest, CefCallback)} documentation
     * for intended usage.
     *
     * <p>Definition generated from cef_server_capi.h
     *
     * <pre>
     * void (CEF_CALLBACK* send_web_socket_message)(struct _cef_server_t* self, int connection_id, const void* data, size_t data_size);
     * </pre>
     *
     * @see <a href="https://cef-builds.spotifycdn.com/docs/146.0/cef__server_8h.html">cef_server.h:197</a>
     */
    void sendWebSocketMessage(int connectionId, @Nonnull ByteBuffer data);
    /**
     * Create a new backing store with allocated memory of {@code byte_length} bytes. The memory is uninitialized. This
     * method must be called on a thread with a valid V8 isolate. The returned object can safely be passed to other
     * threads. Returns {@code null} on failure.
     *
     * <p>Definition generated from cef_server_capi.h
     *
     * <pre>
     * CEF_EXPORT void cef_server_create(const cef_string_t* address, uint16_t port, int backlog, struct _cef_server_handler_t* handler);
     * </pre>
     *
     * @see <a href="https://cef-builds.spotifycdn.com/docs/146.0/cef__v8_8h.html">cef_v8.h:445</a>
     */
    static void create(@Nonnull String address, int port, int backlog, @Nonnull CefServerHandler handler) {
        NativePeer.N_Create(address, port, backlog, handler);
    }

    final class NativePeer implements CefServer, AutoCloseable {
        private final long nativePtr;
        private final java.lang.ref.Cleaner.Cleanable cleanable;

        NativePeer(long ptr) {
            this.nativePtr = ptr;
            this.cleanable = net.kurobako.cef4j.NativeCleaner.INSTANCE.register(this, new Release(ptr));
        }

        @Override
        public void close() {
            cleanable.clean();
        }

        private static final org.slf4j.Logger _log = org.slf4j.LoggerFactory.getLogger(CefServer.class);

        private static class Release implements Runnable {
            private final long ptr;

            Release(long ptr) {
                this.ptr = ptr;
            }

            @Override
            public void run() {
                if (_log.isTraceEnabled()) _log.trace("release CefServer 0x{}", Long.toHexString(ptr));
                N_Release(ptr);
            }
        }

        private static native void N_Release(long ptr);

        @Override
        public Optional<CefTaskRunner> getTaskRunner() {
            return Optional.ofNullable(N_GetTaskRunner(nativePtr));
        }

        @Override
        public void shutdown() {
            N_Shutdown(nativePtr);
        }

        @Override
        public boolean isRunning() {
            return N_IsRunning(nativePtr);
        }

        @Override
        public Optional<String> getAddress() {
            return Optional.ofNullable(N_GetAddress(nativePtr));
        }

        @Override
        public boolean hasConnection() {
            return N_HasConnection(nativePtr);
        }

        @Override
        public boolean isValidConnection(int connectionId) {
            return N_IsValidConnection(nativePtr, connectionId);
        }

        @Override
        public void sendHttp200response(int connectionId, @Nonnull String contentType, @Nonnull ByteBuffer data) {
            N_SendHttp200response(nativePtr, connectionId, contentType, data);
        }

        @Override
        public void sendHttp404response(int connectionId) {
            N_SendHttp404response(nativePtr, connectionId);
        }

        @Override
        public void sendHttp500response(int connectionId, @Nonnull String errorMessage) {
            N_SendHttp500response(nativePtr, connectionId, errorMessage);
        }

        @Override
        public void sendHttpResponse(
                int connectionId,
                int responseCode,
                @Nonnull String contentType,
                long contentLength,
                @Nullable Map<String, List<String>> extraHeaders) {
            N_SendHttpResponse(nativePtr, connectionId, responseCode, contentType, contentLength, extraHeaders);
        }

        @Override
        public void sendRawData(int connectionId, @Nonnull ByteBuffer data) {
            N_SendRawData(nativePtr, connectionId, data);
        }

        @Override
        public void closeConnection(int connectionId) {
            N_CloseConnection(nativePtr, connectionId);
        }

        @Override
        public void sendWebSocketMessage(int connectionId, @Nonnull ByteBuffer data) {
            N_SendWebSocketMessage(nativePtr, connectionId, data);
        }

        private static native CefTaskRunner N_GetTaskRunner(long self);

        private static native void N_Shutdown(long self);

        private static native boolean N_IsRunning(long self);

        private static native String N_GetAddress(long self);

        private static native boolean N_HasConnection(long self);

        private static native boolean N_IsValidConnection(long self, int connectionId);

        private static native void N_SendHttp200response(
                long self, int connectionId, String contentType, ByteBuffer data);

        private static native void N_SendHttp404response(long self, int connectionId);

        private static native void N_SendHttp500response(long self, int connectionId, String errorMessage);

        private static native void N_SendHttpResponse(
                long self,
                int connectionId,
                int responseCode,
                String contentType,
                long contentLength,
                Map<String, List<String>> extraHeaders);

        private static native void N_SendRawData(long self, int connectionId, ByteBuffer data);

        private static native void N_CloseConnection(long self, int connectionId);

        private static native void N_SendWebSocketMessage(long self, int connectionId, ByteBuffer data);

        static native void N_Create(String address, int port, int backlog, CefServerHandler handler);

        @Override
        public boolean equals(Object obj) {
            if (this == obj) return true;
            if (!(obj instanceof NativePeer)) return false;
            return this.nativePtr == ((NativePeer) obj).nativePtr;
        }

        @Override
        public int hashCode() {
            return Long.hashCode(nativePtr);
        }

        @Override
        public String toString() {
            return "CefServer{0x" + Long.toHexString(nativePtr) + "}";
        }
    }
}
