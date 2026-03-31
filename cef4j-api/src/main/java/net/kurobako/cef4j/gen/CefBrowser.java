// GENERATED - do not edit. Regenerate via: mvn generate-sources -pl cef4j-native
package net.kurobako.cef4j.gen;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

/**
 * Class used to represent a browser. When used in the browser process the methods of this class may be called on any
 * thread unless otherwise indicated in the comments. When used in the render process the methods of this class may only
 * be called on the main thread.
 */
public interface CefBrowser {

    /** Returns true if this object is valid. Do not call any other methods if this function returns false. */
    boolean isValid();

    /** Returns the browser host object. This method can only be called in the browser process. */
    long getHost();

    /** Returns true if the browser can navigate backwards. */
    boolean canGoBack();

    /** Navigate backwards. */
    void goBack();

    /** Returns true if the browser can navigate forwards. */
    boolean canGoForward();

    /** Navigate forwards. */
    void goForward();

    /** Returns true if the browser is currently loading. */
    boolean isLoading();

    /** Reload the current page. */
    void reload();

    /** Reload the current page ignoring any cached data. */
    void reloadIgnoreCache();

    /** Stop loading the page. */
    void stopLoad();

    /** Returns the globally unique identifier for this frame or empty if the underlying frame does not yet exist. */
    int getIdentifier();

    /** Returns true if this object is pointing to the same handle as |that| object. */
    boolean isSame(long that);

    /** Returns true if the browser is a popup. */
    boolean isPopup();

    /** Returns true if a document has been loaded in the browser. */
    boolean hasDocument();

    /**
     * Returns the main (top-level) frame for the browser. In the browser process this will return a valid object until
     * after CefLifeSpanHandler::OnBeforeClose is called. In the renderer process this will return NULL if the main
     * frame is hosted in a different renderer process (e.g. for cross-origin sub-frames). The main frame object will
     * change during cross-origin navigation or re-navigation after renderer process termination (due to crashes, etc).
     */
    long getMainFrame();

    /** Returns the focused frame for the browser. */
    long getFocusedFrame();

    /** Returns the frame with the specified identifier, or NULL if not found. */
    long getFrameByIdentifier(@Nonnull String identifier);

    /**
     * Returns the frame with the specified name, or NULL if not found.
     *
     * @param name may be null
     */
    long getFrameByName(@Nullable String name);

    /** Returns the number of stack frames. */
    long getFrameCount();

    /**
     * Returns the identifiers of all existing frames.
     *
     * <p>The size of {@code identifiers} is determined by {@code GetFrameCount()}.
     */
    void getFrameIdentifiers(@Nonnull java.util.List<String> identifiers);

    /** Returns the names of all existing frames. */
    void getFrameNames(@Nonnull java.util.List<String> names);

    static class NativePeer implements CefBrowser {
        private volatile long nativePtr;

        @Override
        public boolean isValid() {
            return N_IsValid(nativePtr);
        }

        @Override
        public long getHost() {
            return N_GetHost(nativePtr);
        }

        @Override
        public boolean canGoBack() {
            return N_CanGoBack(nativePtr);
        }

        @Override
        public void goBack() {
            N_GoBack(nativePtr);
        }

        @Override
        public boolean canGoForward() {
            return N_CanGoForward(nativePtr);
        }

        @Override
        public void goForward() {
            N_GoForward(nativePtr);
        }

        @Override
        public boolean isLoading() {
            return N_IsLoading(nativePtr);
        }

        @Override
        public void reload() {
            N_Reload(nativePtr);
        }

        @Override
        public void reloadIgnoreCache() {
            N_ReloadIgnoreCache(nativePtr);
        }

        @Override
        public void stopLoad() {
            N_StopLoad(nativePtr);
        }

        @Override
        public int getIdentifier() {
            return N_GetIdentifier(nativePtr);
        }

        @Override
        public boolean isSame(long that) {
            return N_IsSame(nativePtr, that);
        }

        @Override
        public boolean isPopup() {
            return N_IsPopup(nativePtr);
        }

        @Override
        public boolean hasDocument() {
            return N_HasDocument(nativePtr);
        }

        @Override
        public long getMainFrame() {
            return N_GetMainFrame(nativePtr);
        }

        @Override
        public long getFocusedFrame() {
            return N_GetFocusedFrame(nativePtr);
        }

        @Override
        public long getFrameByIdentifier(String identifier) {
            return N_GetFrameByIdentifier(nativePtr, identifier);
        }

        @Override
        public long getFrameByName(String name) {
            return N_GetFrameByName(nativePtr, name);
        }

        @Override
        public long getFrameCount() {
            return N_GetFrameCount(nativePtr);
        }

        @Override
        public void getFrameIdentifiers(java.util.List<String> identifiers) {
            N_GetFrameIdentifiers(nativePtr, identifiers);
        }

        @Override
        public void getFrameNames(java.util.List<String> names) {
            N_GetFrameNames(nativePtr, names);
        }

        private native boolean N_IsValid(long self);

        private native long N_GetHost(long self);

        private native boolean N_CanGoBack(long self);

        private native void N_GoBack(long self);

        private native boolean N_CanGoForward(long self);

        private native void N_GoForward(long self);

        private native boolean N_IsLoading(long self);

        private native void N_Reload(long self);

        private native void N_ReloadIgnoreCache(long self);

        private native void N_StopLoad(long self);

        private native int N_GetIdentifier(long self);

        private native boolean N_IsSame(long self, long that);

        private native boolean N_IsPopup(long self);

        private native boolean N_HasDocument(long self);

        private native long N_GetMainFrame(long self);

        private native long N_GetFocusedFrame(long self);

        private native long N_GetFrameByIdentifier(long self, String identifier);

        private native long N_GetFrameByName(long self, String name);

        private native long N_GetFrameCount(long self);

        private native void N_GetFrameIdentifiers(long self, java.util.List<String> identifiers);

        private native void N_GetFrameNames(long self, java.util.List<String> names);

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
            return "CefBrowser{0x" + Long.toHexString(nativePtr) + "}";
        }
    }
}
