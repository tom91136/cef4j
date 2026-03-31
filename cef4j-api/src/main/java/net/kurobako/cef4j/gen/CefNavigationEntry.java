// GENERATED - do not edit. Regenerate via: mvn generate-sources -pl cef4j-native
package net.kurobako.cef4j.gen;

import java.util.Optional;

/** Class used to represent an entry in navigation history. */
public interface CefNavigationEntry {

    /** Returns true if this object is valid. Do not call any other methods if this function returns false. */
    boolean isValid();

    Optional<String> getUrl();

    Optional<String> getDisplayUrl();

    /** Returns the original URL before any redirections. */
    Optional<String> getOriginalUrl();

    /** Returns the title of an HTML document. */
    Optional<String> getTitle();

    /**
     * Returns the transition type which indicates what the user did to move to this page from the previous page.
     *
     * @return the result, or {@code TT_EXPLICIT} for default handling
     */
    CefTransitionType getTransitionType();

    /** Returns true if this navigation includes post data. */
    boolean hasPostData();

    /**
     * Returns the time for the last known successful navigation completion. A navigation may be completed more than
     * once if the page is reloaded. May be 0 if the navigation has not yet completed.
     */
    CefBasetime getCompletionTime();

    /**
     * Returns the HTTP status code for the last known successful navigation response. May be 0 if the response has not
     * yet been received or if the navigation has not yet completed.
     */
    int getHttpStatusCode();

    long getSslstatus();

    static class NativePeer implements CefNavigationEntry {
        private volatile long nativePtr;

        @Override
        public boolean isValid() {
            return N_IsValid(nativePtr);
        }

        @Override
        public Optional<String> getUrl() {
            return Optional.ofNullable(N_GetUrl(nativePtr));
        }

        @Override
        public Optional<String> getDisplayUrl() {
            return Optional.ofNullable(N_GetDisplayUrl(nativePtr));
        }

        @Override
        public Optional<String> getOriginalUrl() {
            return Optional.ofNullable(N_GetOriginalUrl(nativePtr));
        }

        @Override
        public Optional<String> getTitle() {
            return Optional.ofNullable(N_GetTitle(nativePtr));
        }

        @Override
        public CefTransitionType getTransitionType() {
            return N_GetTransitionType(nativePtr);
        }

        @Override
        public boolean hasPostData() {
            return N_HasPostData(nativePtr);
        }

        @Override
        public CefBasetime getCompletionTime() {
            return N_GetCompletionTime(nativePtr);
        }

        @Override
        public int getHttpStatusCode() {
            return N_GetHttpStatusCode(nativePtr);
        }

        @Override
        public long getSslstatus() {
            return N_GetSslstatus(nativePtr);
        }

        private native boolean N_IsValid(long self);

        private native String N_GetUrl(long self);

        private native String N_GetDisplayUrl(long self);

        private native String N_GetOriginalUrl(long self);

        private native String N_GetTitle(long self);

        private native CefTransitionType N_GetTransitionType(long self);

        private native boolean N_HasPostData(long self);

        private native CefBasetime N_GetCompletionTime(long self);

        private native int N_GetHttpStatusCode(long self);

        private native long N_GetSslstatus(long self);

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
            return "CefNavigationEntry{0x" + Long.toHexString(nativePtr) + "}";
        }
    }
}
