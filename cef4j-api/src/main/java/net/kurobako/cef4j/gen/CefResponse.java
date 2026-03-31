// GENERATED - do not edit. Regenerate via: mvn generate-sources -pl cef4j-native
package net.kurobako.cef4j.gen;

import java.util.Optional;
import javax.annotation.Nonnull;
import javax.annotation.Nullable;

/** Class used to represent a web response. The methods of this class may be called on any thread. */
public interface CefResponse {

    /** Returns true if the values of this object are read-only. Some APIs may expose read-only objects. */
    boolean isReadOnly();

    /** Returns the error string. */
    CefErrorcode getError();

    /**
     * Set the response error code. This can be used by custom scheme handlers to return errors during initial request
     * processing.
     */
    void setError(@Nonnull CefErrorcode error);

    /** Get the response status code. */
    int getStatus();

    /** Set the response status code. */
    void setStatus(int status);

    /** Get the response status text. */
    Optional<String> getStatusText();

    /**
     * Set the response status text.
     *
     * @param statusText may be null
     */
    void setStatusText(@Nullable String statusText);

    /** Returns the mime type. */
    Optional<String> getMimeType();

    /**
     * Set the response mime type.
     *
     * @param mimeType may be null
     */
    void setMimeType(@Nullable String mimeType);

    /** Get the response charset. */
    Optional<String> getCharset();

    /**
     * Set the response charset.
     *
     * @param charset may be null
     */
    void setCharset(@Nullable String charset);

    /** Get the value for the specified response header field. */
    Optional<String> getHeaderByName(@Nonnull String name);

    /**
     * Set the header |name| to |value|. If |overwrite| is true any existing values will be replaced with the new value.
     * If |overwrite| is false any existing values will not be overwritten.
     *
     * @param value may be null
     */
    void setHeaderByName(@Nonnull String name, @Nullable String value, boolean overwrite);

    /** Get all response header fields. */
    void getHeaderMap(@Nonnull java.util.Map<String, java.util.List<String>> headerMap);

    /** Set all response header fields. */
    void setHeaderMap(@Nonnull java.util.Map<String, java.util.List<String>> headerMap);

    Optional<String> getUrl();

    void setUrl(@Nonnull String url);

    static class NativePeer implements CefResponse {
        private volatile long nativePtr;

        @Override
        public boolean isReadOnly() {
            return N_IsReadOnly(nativePtr);
        }

        @Override
        public CefErrorcode getError() {
            return N_GetError(nativePtr);
        }

        @Override
        public void setError(CefErrorcode error) {
            N_SetError(nativePtr, error);
        }

        @Override
        public int getStatus() {
            return N_GetStatus(nativePtr);
        }

        @Override
        public void setStatus(int status) {
            N_SetStatus(nativePtr, status);
        }

        @Override
        public Optional<String> getStatusText() {
            return Optional.ofNullable(N_GetStatusText(nativePtr));
        }

        @Override
        public void setStatusText(String statusText) {
            N_SetStatusText(nativePtr, statusText);
        }

        @Override
        public Optional<String> getMimeType() {
            return Optional.ofNullable(N_GetMimeType(nativePtr));
        }

        @Override
        public void setMimeType(String mimeType) {
            N_SetMimeType(nativePtr, mimeType);
        }

        @Override
        public Optional<String> getCharset() {
            return Optional.ofNullable(N_GetCharset(nativePtr));
        }

        @Override
        public void setCharset(String charset) {
            N_SetCharset(nativePtr, charset);
        }

        @Override
        public Optional<String> getHeaderByName(String name) {
            return Optional.ofNullable(N_GetHeaderByName(nativePtr, name));
        }

        @Override
        public void setHeaderByName(String name, String value, boolean overwrite) {
            N_SetHeaderByName(nativePtr, name, value, overwrite);
        }

        @Override
        public void getHeaderMap(java.util.Map<String, java.util.List<String>> headerMap) {
            N_GetHeaderMap(nativePtr, headerMap);
        }

        @Override
        public void setHeaderMap(java.util.Map<String, java.util.List<String>> headerMap) {
            N_SetHeaderMap(nativePtr, headerMap);
        }

        @Override
        public Optional<String> getUrl() {
            return Optional.ofNullable(N_GetUrl(nativePtr));
        }

        @Override
        public void setUrl(String url) {
            N_SetUrl(nativePtr, url);
        }

        private native boolean N_IsReadOnly(long self);

        private native CefErrorcode N_GetError(long self);

        private native void N_SetError(long self, CefErrorcode error);

        private native int N_GetStatus(long self);

        private native void N_SetStatus(long self, int status);

        private native String N_GetStatusText(long self);

        private native void N_SetStatusText(long self, String statusText);

        private native String N_GetMimeType(long self);

        private native void N_SetMimeType(long self, String mimeType);

        private native String N_GetCharset(long self);

        private native void N_SetCharset(long self, String charset);

        private native String N_GetHeaderByName(long self, String name);

        private native void N_SetHeaderByName(long self, String name, String value, boolean overwrite);

        private native void N_GetHeaderMap(long self, java.util.Map<String, java.util.List<String>> headerMap);

        private native void N_SetHeaderMap(long self, java.util.Map<String, java.util.List<String>> headerMap);

        private native String N_GetUrl(long self);

        private native void N_SetUrl(long self, String url);

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
            return "CefResponse{0x" + Long.toHexString(nativePtr) + "}";
        }
    }
}
