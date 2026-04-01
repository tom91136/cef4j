// GENERATED - do not edit. Regenerate via: mvn generate-sources -pl cef4j-native
package net.kurobako.cef4j.gen;

import java.util.Optional;
import javax.annotation.Nonnull;
import javax.annotation.Nullable;

/**
 * Class used to represent a frame in the browser window. When used in the browser process the methods of this class may
 * be called on any thread unless otherwise indicated in the comments. When used in the render process the methods of
 * this class may only be called on the main thread.
 *
 * <p>Definition generated from cef_frame_capi.h
 *
 * <pre>typedef struct _cef_frame_t {
 *   cef_base_ref_counted_t base;
 *   ...
 * } cef_frame_t;</pre>
 *
 * @see <a href="https://cef-builds.spotifycdn.com/docs/146.0/cef__frame_8h.html">cef_frame.h:53</a>
 */
public interface CefFrame extends CefLibraryObject {

    /**
     * Returns {@code true} if this object is valid. Do not call any other methods if this function returns
     * {@code false}.
     *
     * <p>Definition generated from cef_frame_capi.h
     *
     * <pre>int (CEF_CALLBACK* is_valid)(struct _cef_frame_t* self);</pre>
     *
     * @see <a
     *     href="https://cef-builds.spotifycdn.com/docs/146.0/cef__download__item_8h.html">cef_download_item.h:49</a>
     */
    boolean isValid();

    /**
     * Execute undo in this frame.
     *
     * <p>Definition generated from cef_frame_capi.h
     *
     * <pre>void (CEF_CALLBACK* undo)(struct _cef_frame_t* self);</pre>
     *
     * @see <a href="https://cef-builds.spotifycdn.com/docs/146.0/cef__frame_8h.html">cef_frame.h:68</a>
     */
    void undo();

    /**
     * Execute redo in this frame.
     *
     * <p>Definition generated from cef_frame_capi.h
     *
     * <pre>void (CEF_CALLBACK* redo)(struct _cef_frame_t* self);</pre>
     *
     * @see <a href="https://cef-builds.spotifycdn.com/docs/146.0/cef__frame_8h.html">cef_frame.h:74</a>
     */
    void redo();

    /**
     * Execute cut in this frame.
     *
     * <p>Definition generated from cef_frame_capi.h
     *
     * <pre>void (CEF_CALLBACK* cut)(struct _cef_frame_t* self);</pre>
     *
     * @see <a href="https://cef-builds.spotifycdn.com/docs/146.0/cef__frame_8h.html">cef_frame.h:80</a>
     */
    void cut();

    /**
     * Returns a writable copy of this object.
     *
     * <p>Definition generated from cef_frame_capi.h
     *
     * <pre>void (CEF_CALLBACK* copy)(struct _cef_frame_t* self);</pre>
     *
     * @see <a href="https://cef-builds.spotifycdn.com/docs/146.0/cef__command__line_8h.html">cef_command_line.h:90</a>
     */
    void copy();

    /**
     * Execute paste in this frame.
     *
     * <p>Definition generated from cef_frame_capi.h
     *
     * <pre>void (CEF_CALLBACK* paste)(struct _cef_frame_t* self);</pre>
     *
     * @see <a href="https://cef-builds.spotifycdn.com/docs/146.0/cef__frame_8h.html">cef_frame.h:92</a>
     */
    void paste();

    /**
     * Execute paste and match style in this frame.
     *
     * <p>Definition generated from cef_frame_capi.h
     *
     * <pre>void (CEF_CALLBACK* paste_and_match_style)(struct _cef_frame_t* self);</pre>
     *
     * @see <a href="https://cef-builds.spotifycdn.com/docs/146.0/cef__frame_8h.html">cef_frame.h:98</a>
     */
    void pasteAndMatchStyle();

    /**
     * Execute delete in this frame.
     *
     * <p>Definition generated from cef_frame_capi.h
     *
     * <pre>void (CEF_CALLBACK* del)(struct _cef_frame_t* self);</pre>
     *
     * @see <a href="https://cef-builds.spotifycdn.com/docs/146.0/cef__frame_8h.html">cef_frame.h:104</a>
     */
    void del();

    /**
     * Execute select all in this frame.
     *
     * <p>Definition generated from cef_frame_capi.h
     *
     * <pre>void (CEF_CALLBACK* select_all)(struct _cef_frame_t* self);</pre>
     *
     * @see <a href="https://cef-builds.spotifycdn.com/docs/146.0/cef__frame_8h.html">cef_frame.h:110</a>
     */
    void selectAll();

    /**
     * Save this frame's HTML source to a temporary file and open it in the default text viewing application. This
     * method can only be called from the browser process.
     *
     * <p>Definition generated from cef_frame_capi.h
     *
     * <pre>void (CEF_CALLBACK* view_source)(struct _cef_frame_t* self);</pre>
     *
     * @see <a href="https://cef-builds.spotifycdn.com/docs/146.0/cef__frame_8h.html">cef_frame.h:116</a>
     */
    void viewSource();

    /**
     * Retrieve this frame's HTML source as a string sent to the specified visitor.
     *
     * <p>Definition generated from cef_frame_capi.h
     *
     * <pre>void (CEF_CALLBACK* get_source)(struct _cef_frame_t* self, struct _cef_string_visitor_t* visitor);</pre>
     *
     * @see <a href="https://cef-builds.spotifycdn.com/docs/146.0/cef__frame_8h.html">cef_frame.h:124</a>
     */
    void getSource(@Nonnull CefStringVisitor visitor);

    /**
     * Retrieve this frame's display text as a string sent to the specified visitor.
     *
     * <p>Definition generated from cef_frame_capi.h
     *
     * <pre>void (CEF_CALLBACK* get_text)(struct _cef_frame_t* self, struct _cef_string_visitor_t* visitor);</pre>
     *
     * @see <a href="https://cef-builds.spotifycdn.com/docs/146.0/cef__frame_8h.html">cef_frame.h:131</a>
     */
    void getText(@Nonnull CefStringVisitor visitor);

    /**
     * Load the request represented by the {@code request} object.
     *
     * <p><b>WARNING:</b> This method will fail with "bad IPC message" reason INVALID_INITIATOR_ORIGIN (213) unless you
     * first navigate to the request origin using some other mechanism (LoadURL, link click, etc).
     *
     * <p>Definition generated from cef_frame_capi.h
     *
     * <pre>void (CEF_CALLBACK* load_request)(struct _cef_frame_t* self, struct _cef_request_t* request);</pre>
     *
     * @see <a href="https://cef-builds.spotifycdn.com/docs/146.0/cef__frame_8h.html">cef_frame.h:138</a>
     */
    void loadRequest(@Nonnull CefRequest request);

    /**
     * Load the specified {@code url}.
     *
     * <p>Definition generated from cef_frame_capi.h
     *
     * <pre>void (CEF_CALLBACK* load_url)(struct _cef_frame_t* self, const cef_string_t* url);</pre>
     *
     * @see <a href="https://cef-builds.spotifycdn.com/docs/146.0/cef__frame_8h.html">cef_frame.h:148</a>
     */
    void loadUrl(@Nonnull String url);

    /**
     * Execute a string of JavaScript code in this frame. The {@code script_url} parameter is the URL where the script
     * in question can be found, if any. The renderer may request this URL to show the developer the source of the
     * error. The {@code start_line} parameter is the base line number to use for error reporting.
     *
     * <p>Definition generated from cef_frame_capi.h
     *
     * <pre>
     * void (CEF_CALLBACK* execute_java_script)(struct _cef_frame_t* self, const cef_string_t* code, const cef_string_t* script_url, int start_line);
     * </pre>
     *
     * @param scriptUrl may be null
     * @see <a href="https://cef-builds.spotifycdn.com/docs/146.0/cef__frame_8h.html">cef_frame.h:154</a>
     */
    void executeJavaScript(@Nonnull String code, @Nullable String scriptUrl, int startLine);

    /**
     * Returns {@code true} if this is the main (top-level) frame.
     *
     * <p>Definition generated from cef_frame_capi.h
     *
     * <pre>int (CEF_CALLBACK* is_main)(struct _cef_frame_t* self);</pre>
     *
     * @see <a href="https://cef-builds.spotifycdn.com/docs/146.0/cef__frame_8h.html">cef_frame.h:166</a>
     */
    boolean isMain();

    /**
     * Returns {@code true} if this is the focused frame.
     *
     * <p>Definition generated from cef_frame_capi.h
     *
     * <pre>int (CEF_CALLBACK* is_focused)(struct _cef_frame_t* self);</pre>
     *
     * @see <a href="https://cef-builds.spotifycdn.com/docs/146.0/cef__frame_8h.html">cef_frame.h:172</a>
     */
    boolean isFocused();

    /**
     * Returns the name of this node.
     *
     * <p>Definition generated from cef_frame_capi.h
     *
     * <pre>cef_string_userfree_t (CEF_CALLBACK* get_name)(struct _cef_frame_t* self);</pre>
     *
     * @see <a href="https://cef-builds.spotifycdn.com/docs/146.0/cef__dom_8h.html">cef_dom.h:215</a>
     */
    Optional<String> getName();

    /**
     * Returns the globally unique identifier for this frame or empty if the underlying frame does not yet exist.
     *
     * <p>Definition generated from cef_frame_capi.h
     *
     * <pre>cef_string_userfree_t (CEF_CALLBACK* get_identifier)(struct _cef_frame_t* self);</pre>
     *
     * @see <a href="https://cef-builds.spotifycdn.com/docs/146.0/cef__frame_8h.html">cef_frame.h:188</a>
     */
    Optional<String> getIdentifier();

    /**
     * Returns the parent node.
     *
     * <p>Definition generated from cef_frame_capi.h
     *
     * <pre>cef_frame_t* (CEF_CALLBACK* get_parent)(struct _cef_frame_t* self);</pre>
     *
     * @see <a href="https://cef-builds.spotifycdn.com/docs/146.0/cef__dom_8h.html">cef_dom.h:245</a>
     */
    Optional<CefFrame> getParent();

    /**
     * Returns the URL.
     *
     * <p>Definition generated from cef_frame_capi.h
     *
     * <pre>cef_string_userfree_t (CEF_CALLBACK* get_url)(struct _cef_frame_t* self);</pre>
     *
     * @see <a
     *     href="https://cef-builds.spotifycdn.com/docs/146.0/cef__download__item_8h.html">cef_download_item.h:143</a>
     */
    Optional<String> getUrl();

    /**
     * Returns the browser for this context. This method will return an empty reference for WebWorker contexts.
     *
     * <p>Definition generated from cef_frame_capi.h
     *
     * <pre>cef_browser_t* (CEF_CALLBACK* get_browser)(struct _cef_frame_t* self);</pre>
     *
     * @see <a href="https://cef-builds.spotifycdn.com/docs/146.0/cef__v8_8h.html">cef_v8.h:163</a>
     */
    Optional<CefBrowser> getBrowser();

    /**
     * Get the V8 context associated with the frame. This method can only be called from the render process.
     *
     * <p>Definition generated from cef_frame_capi.h
     *
     * <pre>cef_v8_context_t* (CEF_CALLBACK* get_v8_context)(struct _cef_frame_t* self);</pre>
     *
     * @see <a href="https://cef-builds.spotifycdn.com/docs/146.0/cef__frame_8h.html">cef_frame.h:214</a>
     */
    Optional<CefV8Context> getV8context();

    /**
     * Visit the DOM document. This method can only be called from the render process.
     *
     * <p>Definition generated from cef_frame_capi.h
     *
     * <pre>void (CEF_CALLBACK* visit_dom)(struct _cef_frame_t* self, struct _cef_domvisitor_t* visitor);</pre>
     *
     * @see <a href="https://cef-builds.spotifycdn.com/docs/146.0/cef__frame_8h.html">cef_frame.h:221</a>
     */
    void visitDom(@Nonnull CefDomVisitor visitor);

    /**
     * Create a new URL request that will be treated as originating from this frame and the associated browser. Use
     * CefURLRequest.create() instead if you do not want the request to have this association, in which case it may be
     * handled differently (see documentation on that method). A request created with this method may only originate
     * from the browser process, and will behave as follows:
     *
     * <ul>
     *   <li>It may be intercepted by the client via CefResourceRequestHandler or
     * </ul>
     *
     * CefSchemeHandlerFactory.
     *
     * <ul>
     *   <li>POST data may only contain a single element of type PDE_TYPE_FILE or
     * </ul>
     *
     * PDE_TYPE_BYTES.
     *
     * <p>The {@code request} object will be marked as read-only after calling this method.
     *
     * <p>Definition generated from cef_frame_capi.h
     *
     * <pre>
     * cef_urlrequest_t* (CEF_CALLBACK* create_urlrequest)(struct _cef_frame_t* self, struct _cef_request_t* request, struct _cef_urlrequest_client_t* client);
     * </pre>
     *
     * @see <a href="https://cef-builds.spotifycdn.com/docs/146.0/cef__frame_8h.html">cef_frame.h:228</a>
     */
    Optional<CefUrlRequest> createUrlRequest(@Nonnull CefRequest request, @Nonnull CefUrlRequestClient client);

    /**
     * Send a message to the specified {@code target_process}. Ownership of the message contents will be transferred and
     * the {@code message} reference will be invalidated. Message delivery is not guaranteed in all cases (for example,
     * if the browser is closing, navigating, or if the target process crashes). Send an ACK message back from the
     * target process if confirmation is required.
     *
     * <p>Definition generated from cef_frame_capi.h
     *
     * <pre>
     * void (CEF_CALLBACK* send_process_message)(struct _cef_frame_t* self, cef_process_id_t target_process, struct _cef_process_message_t* message);
     * </pre>
     *
     * @see <a href="https://cef-builds.spotifycdn.com/docs/146.0/cef__frame_8h.html">cef_frame.h:248</a>
     */
    void sendProcessMessage(@Nonnull CefProcessId targetProcess, @Nonnull CefProcessMessage message);

    final class NativePeer implements CefFrame, AutoCloseable {
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

        private static final org.slf4j.Logger _log = org.slf4j.LoggerFactory.getLogger(CefFrame.class);

        private static class Release implements Runnable {
            private final long ptr;

            Release(long ptr) {
                this.ptr = ptr;
            }

            @Override
            public void run() {
                if (_log.isTraceEnabled()) _log.trace("release CefFrame 0x{}", Long.toHexString(ptr));
                N_Release(ptr);
            }
        }

        private static native void N_Release(long ptr);

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
        public void getSource(@Nonnull CefStringVisitor visitor) {
            N_GetSource(nativePtr, visitor);
        }

        @Override
        public void getText(@Nonnull CefStringVisitor visitor) {
            N_GetText(nativePtr, visitor);
        }

        @Override
        public void loadRequest(@Nonnull CefRequest request) {
            N_LoadRequest(nativePtr, request);
        }

        @Override
        public void loadUrl(@Nonnull String url) {
            N_LoadUrl(nativePtr, url);
        }

        @Override
        public void executeJavaScript(@Nonnull String code, @Nullable String scriptUrl, int startLine) {
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
        public Optional<CefFrame> getParent() {
            return Optional.ofNullable(N_GetParent(nativePtr));
        }

        @Override
        public Optional<String> getUrl() {
            return Optional.ofNullable(N_GetUrl(nativePtr));
        }

        @Override
        public Optional<CefBrowser> getBrowser() {
            return Optional.ofNullable(N_GetBrowser(nativePtr));
        }

        @Override
        public Optional<CefV8Context> getV8context() {
            return Optional.ofNullable(N_GetV8context(nativePtr));
        }

        @Override
        public void visitDom(@Nonnull CefDomVisitor visitor) {
            N_VisitDom(nativePtr, visitor);
        }

        @Override
        public Optional<CefUrlRequest> createUrlRequest(
                @Nonnull CefRequest request, @Nonnull CefUrlRequestClient client) {
            return Optional.ofNullable(N_CreateUrlRequest(nativePtr, request, client));
        }

        @Override
        public void sendProcessMessage(@Nonnull CefProcessId targetProcess, @Nonnull CefProcessMessage message) {
            N_SendProcessMessage(nativePtr, targetProcess, message);
        }

        private static native boolean N_IsValid(long self);

        private static native void N_Undo(long self);

        private static native void N_Redo(long self);

        private static native void N_Cut(long self);

        private static native void N_Copy(long self);

        private static native void N_Paste(long self);

        private static native void N_PasteAndMatchStyle(long self);

        private static native void N_Del(long self);

        private static native void N_SelectAll(long self);

        private static native void N_ViewSource(long self);

        private static native void N_GetSource(long self, CefStringVisitor visitor);

        private static native void N_GetText(long self, CefStringVisitor visitor);

        private static native void N_LoadRequest(long self, CefRequest request);

        private static native void N_LoadUrl(long self, String url);

        private static native void N_ExecuteJavaScript(long self, String code, String scriptUrl, int startLine);

        private static native boolean N_IsMain(long self);

        private static native boolean N_IsFocused(long self);

        private static native String N_GetName(long self);

        private static native String N_GetIdentifier(long self);

        private static native CefFrame N_GetParent(long self);

        private static native String N_GetUrl(long self);

        private static native CefBrowser N_GetBrowser(long self);

        private static native CefV8Context N_GetV8context(long self);

        private static native void N_VisitDom(long self, CefDomVisitor visitor);

        private static native CefUrlRequest N_CreateUrlRequest(
                long self, CefRequest request, CefUrlRequestClient client);

        private static native void N_SendProcessMessage(
                long self, CefProcessId targetProcess, CefProcessMessage message);

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
