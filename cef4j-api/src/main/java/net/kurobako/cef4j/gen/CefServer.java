// GENERATED - do not edit. Regenerate via: mvn generate-sources -pl cef4j-native
package net.kurobako.cef4j.gen;

import java.util.Optional;
import javax.annotation.Nonnull;
import javax.annotation.Nullable;

/**
 * Class representing a server that supports HTTP and WebSocket requests. Server capacity is limited and is intended to
 * handle only a small number of simultaneous connections (e.g. for communicating between applications on localhost).
 * The methods of this class are safe to call from any thread in the brower process unless otherwise indicated.
 */
public interface CefServer {

    /**
     * Returns the CefTaskRunner that will execute code on this thread's message loop. This method is safe to call from
     * any thread.
     */
    long getTaskRunner();

    /**
     * Stop the server and shut down the dedicated server thread. See CefServerHandler::OnServerCreated documentation
     * for a description of server lifespan.
     */
    void shutdown();

    /**
     * Returns true if the thread is currently running. This method must be called from the same thread that called
     * CreateThread().
     */
    boolean isRunning();

    /** Returns the server address including the port number. */
    Optional<String> getAddress();

    /**
     * Returns true if the server currently has a connection. This method must be called on the dedicated server thread.
     */
    boolean hasConnection();

    /**
     * Returns true if |connection_id| represents a valid connection. This method must be called on the dedicated server
     * thread.
     */
    boolean isValidConnection(int connectionId);

    /**
     * Send an HTTP 200 "OK" response to the connection identified by |connection_id|. |content_type| is the response
     * content type (e.g. "text/html"), |data| is the response content, and |data_size| is the size of |data| in bytes.
     * The contents of |data| will be copied. The connection will be closed automatically after the response is sent.
     */
    void sendHttp200Response(int connectionId, @Nonnull String contentType, long data, long dataSize);

    /**
     * Send an HTTP 404 "Not Found" response to the connection identified by |connection_id|. The connection will be
     * closed automatically after the response is sent.
     */
    void sendHttp404Response(int connectionId);

    /**
     * Send an HTTP 500 "Internal Server Error" response to the connection identified by |connection_id|.
     * |error_message| is the associated error message. The connection will be closed automatically after the response
     * is sent.
     */
    void sendHttp500Response(int connectionId, @Nonnull String errorMessage);

    /**
     * Send a custom HTTP response to the connection identified by |connection_id|. |response_code| is the HTTP response
     * code sent in the status line (e.g. 200), |content_type| is the response content type sent as the "Content-Type"
     * header (e.g. "text/html"), |content_length| is the expected content length, and |extra_headers| is the map of
     * extra response headers. If |content_length| is >= 0 then the "Content-Length" header will be sent. If
     * |content_length| is 0 then no content is expected and the connection will be closed automatically after the
     * response is sent. If |content_length| is < 0 then no "Content-Length" header will be sent and the client will
     * continue reading until the connection is closed. Use the SendRawData method to send the content, if applicable,
     * and call CloseConnection after all content has been sent.
     *
     * @param extraHeaders may be null
     */
    void sendHttpResponse(
            int connectionId,
            int responseCode,
            @Nonnull String contentType,
            long contentLength,
            @Nullable java.util.Map<String, java.util.List<String>> extraHeaders);

    /**
     * Send raw data directly to the connection identified by |connection_id|. |data| is the raw data and |data_size| is
     * the size of |data| in bytes. The contents of |data| will be copied. No validation of |data| is performed
     * internally so the client should be careful to send the amount indicated by the "Content-Length" header, if
     * specified. See SendHttpResponse documentation for intended usage.
     */
    void sendRawData(int connectionId, long data, long dataSize);

    /** Close the connection identified by |connection_id|. See SendHttpResponse documentation for intended usage. */
    void closeConnection(int connectionId);

    /**
     * Send a WebSocket message to the connection identified by |connection_id|. |data| is the response content and
     * |data_size| is the size of |data| in bytes. The contents of |data| will be copied. See
     * CefServerHandler::OnWebSocketRequest documentation for intended usage.
     */
    void sendWebSocketMessage(int connectionId, long data, long dataSize);

    static class NativePeer implements CefServer {
        private volatile long nativePtr;

        @Override
        public long getTaskRunner() {
            return N_GetTaskRunner(nativePtr);
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
        public void sendHttp200Response(int connectionId, String contentType, long data, long dataSize) {
            N_SendHttp200Response(nativePtr, connectionId, contentType, data, dataSize);
        }

        @Override
        public void sendHttp404Response(int connectionId) {
            N_SendHttp404Response(nativePtr, connectionId);
        }

        @Override
        public void sendHttp500Response(int connectionId, String errorMessage) {
            N_SendHttp500Response(nativePtr, connectionId, errorMessage);
        }

        @Override
        public void sendHttpResponse(
                int connectionId,
                int responseCode,
                String contentType,
                long contentLength,
                java.util.Map<String, java.util.List<String>> extraHeaders) {
            N_SendHttpResponse(nativePtr, connectionId, responseCode, contentType, contentLength, extraHeaders);
        }

        @Override
        public void sendRawData(int connectionId, long data, long dataSize) {
            N_SendRawData(nativePtr, connectionId, data, dataSize);
        }

        @Override
        public void closeConnection(int connectionId) {
            N_CloseConnection(nativePtr, connectionId);
        }

        @Override
        public void sendWebSocketMessage(int connectionId, long data, long dataSize) {
            N_SendWebSocketMessage(nativePtr, connectionId, data, dataSize);
        }

        private native long N_GetTaskRunner(long self);

        private native void N_Shutdown(long self);

        private native boolean N_IsRunning(long self);

        private native String N_GetAddress(long self);

        private native boolean N_HasConnection(long self);

        private native boolean N_IsValidConnection(long self, int connectionId);

        private native void N_SendHttp200Response(
                long self, int connectionId, String contentType, long data, long dataSize);

        private native void N_SendHttp404Response(long self, int connectionId);

        private native void N_SendHttp500Response(long self, int connectionId, String errorMessage);

        private native void N_SendHttpResponse(
                long self,
                int connectionId,
                int responseCode,
                String contentType,
                long contentLength,
                java.util.Map<String, java.util.List<String>> extraHeaders);

        private native void N_SendRawData(long self, int connectionId, long data, long dataSize);

        private native void N_CloseConnection(long self, int connectionId);

        private native void N_SendWebSocketMessage(long self, int connectionId, long data, long dataSize);

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
