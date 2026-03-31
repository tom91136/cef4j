// GENERATED - do not edit. Regenerate via: mvn generate-sources -pl cef4j-native
package net.kurobako.cef4j.gen;

import java.util.Optional;
import javax.annotation.Nonnull;
import javax.annotation.Nullable;

/**
 * Class used to represent a frame in the browser window. When used in the browser process the methods of this class may
 * be called on any thread unless otherwise indicated in the comments. When used in the render process the methods of
 * this class may only be called on the main thread.
 */
public interface CefFrame {

    /** Returns true if this object is valid. Do not call any other methods if this function returns false. */
    boolean isValid();

    /** Execute undo in this frame. */
    void undo();

    /** Execute redo in this frame. */
    void redo();

    /** Execute cut in this frame. */
    void cut();

    /** Returns a writable copy of this object. */
    void copy();

    /** Execute paste in this frame. */
    void paste();

    /** Execute paste and match style in this frame. */
    void pasteAndMatchStyle();

    /** Execute delete in this frame. */
    void del();

    /** Execute select all in this frame. */
    void selectAll();

    /**
     * Save this frame's HTML source to a temporary file and open it in the default text viewing application. This
     * method can only be called from the browser process.
     */
    void viewSource();

    /** Retrieve this frame's HTML source as a string sent to the specified visitor. */
    void getSource(long visitor);

    /** Retrieve this frame's display text as a string sent to the specified visitor. */
    void getText(long visitor);

    /**
     * Load the request represented by the |request| object. WARNING: This method will fail with "bad IPC message"
     * reason INVALID_INITIATOR_ORIGIN (213) unless you first navigate to the request origin using some other mechanism
     * (LoadURL, link click, etc).
     */
    void loadRequest(long request);

    void loadUrl(@Nonnull String url);

    /**
     * Execute a string of JavaScript code in this frame. The |script_url| parameter is the URL where the script in
     * question can be found, if any. The renderer may request this URL to show the developer the source of the error.
     * The |start_line| parameter is the base line number to use for error reporting.
     *
     * @param scriptUrl may be null
     */
    void executeJavaScript(@Nonnull String code, @Nullable String scriptUrl, int startLine);

    /** Returns true if this is the main (top-level) frame. */
    boolean isMain();

    /** Returns true if this is the focused frame. */
    boolean isFocused();

    /** Returns the name of this node. */
    Optional<String> getName();

    /** Returns the globally unique identifier for this frame or empty if the underlying frame does not yet exist. */
    Optional<String> getIdentifier();

    /** Returns the parent node. */
    long getParent();

    Optional<String> getUrl();

    /** Returns the browser for this context. This method will return an empty reference for WebWorker contexts. */
    long getBrowser();

    /** Get the V8 context associated with the frame. This method can only be called from the render process. */
    long getV8Context();

    void visitDom(long visitor);

    long createUrlrequest(long request, long client);

    /**
     * Send a message to the specified |target_process|. Ownership of the message contents will be transferred and the
     * |message| reference will be invalidated. Message delivery is not guaranteed in all cases (for example, if the
     * browser is closing, navigating, or if the target process crashes). Send an ACK message back from the target
     * process if confirmation is required.
     */
    void sendProcessMessage(@Nonnull CefProcessId targetProcess, long message);

    static class NativePeer implements CefFrame {
        private volatile long nativePtr;

        @Override
        public boolean isValid() {
            return N_IsValid(nativePtr);
        }

        @Override
        public void undo() {
            N_Undo(nativePtr);
        }

        @Override
        public void redo() {
            N_Redo(nativePtr);
        }

        @Override
        public void cut() {
            N_Cut(nativePtr);
        }

        @Override
        public void copy() {
            N_Copy(nativePtr);
        }

        @Override
        public void paste() {
            N_Paste(nativePtr);
        }

        @Override
        public void pasteAndMatchStyle() {
            N_PasteAndMatchStyle(nativePtr);
        }

        @Override
        public void del() {
            N_Del(nativePtr);
        }

        @Override
        public void selectAll() {
            N_SelectAll(nativePtr);
        }

        @Override
        public void viewSource() {
            N_ViewSource(nativePtr);
        }

        @Override
        public void getSource(long visitor) {
            N_GetSource(nativePtr, visitor);
        }

        @Override
        public void getText(long visitor) {
            N_GetText(nativePtr, visitor);
        }

        @Override
        public void loadRequest(long request) {
            N_LoadRequest(nativePtr, request);
        }

        @Override
        public void loadUrl(String url) {
            N_LoadUrl(nativePtr, url);
        }

        @Override
        public void executeJavaScript(String code, String scriptUrl, int startLine) {
            N_ExecuteJavaScript(nativePtr, code, scriptUrl, startLine);
        }

        @Override
        public boolean isMain() {
            return N_IsMain(nativePtr);
        }

        @Override
        public boolean isFocused() {
            return N_IsFocused(nativePtr);
        }

        @Override
        public Optional<String> getName() {
            return Optional.ofNullable(N_GetName(nativePtr));
        }

        @Override
        public Optional<String> getIdentifier() {
            return Optional.ofNullable(N_GetIdentifier(nativePtr));
        }

        @Override
        public long getParent() {
            return N_GetParent(nativePtr);
        }

        @Override
        public Optional<String> getUrl() {
            return Optional.ofNullable(N_GetUrl(nativePtr));
        }

        @Override
        public long getBrowser() {
            return N_GetBrowser(nativePtr);
        }

        @Override
        public long getV8Context() {
            return N_GetV8Context(nativePtr);
        }

        @Override
        public void visitDom(long visitor) {
            N_VisitDom(nativePtr, visitor);
        }

        @Override
        public long createUrlrequest(long request, long client) {
            return N_CreateUrlrequest(nativePtr, request, client);
        }

        @Override
        public void sendProcessMessage(CefProcessId targetProcess, long message) {
            N_SendProcessMessage(nativePtr, targetProcess, message);
        }

        private native boolean N_IsValid(long self);

        private native void N_Undo(long self);

        private native void N_Redo(long self);

        private native void N_Cut(long self);

        private native void N_Copy(long self);

        private native void N_Paste(long self);

        private native void N_PasteAndMatchStyle(long self);

        private native void N_Del(long self);

        private native void N_SelectAll(long self);

        private native void N_ViewSource(long self);

        private native void N_GetSource(long self, long visitor);

        private native void N_GetText(long self, long visitor);

        private native void N_LoadRequest(long self, long request);

        private native void N_LoadUrl(long self, String url);

        private native void N_ExecuteJavaScript(long self, String code, String scriptUrl, int startLine);

        private native boolean N_IsMain(long self);

        private native boolean N_IsFocused(long self);

        private native String N_GetName(long self);

        private native String N_GetIdentifier(long self);

        private native long N_GetParent(long self);

        private native String N_GetUrl(long self);

        private native long N_GetBrowser(long self);

        private native long N_GetV8Context(long self);

        private native void N_VisitDom(long self, long visitor);

        private native long N_CreateUrlrequest(long self, long request, long client);

        private native void N_SendProcessMessage(long self, CefProcessId targetProcess, long message);

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
            return "CefFrame{0x" + Long.toHexString(nativePtr) + "}";
        }
    }
}
