// GENERATED - do not edit. Regenerate via: mvn generate-sources -pl cef4j-platform -Dcef.version=150.0.18+gdb11278+chromium-150.0.7871.213
package net.kurobako.cef4j.gen;

import javax.annotation.processing.Generated;
import java.util.Optional;
import javax.annotation.Nonnull;
import javax.annotation.Nullable;

/**
 * Class used to represent a frame in the browser window. When used in the browser process the methods of this class may be called on any thread unless otherwise indicated in the comments. When used in the render process the methods of this class may only be called on the main thread.
 * <p>Definition generated from cef_frame_capi.h
 * <pre>typedef struct _cef_frame_t {
 *   cef_base_ref_counted_t base;
 *   ...
 * } cef_frame_t;</pre>
 *
 * @see <a href="https://cef-builds.spotifycdn.com/docs/150.0/cef__frame_8h.html">cef_frame.h:53</a>
 */
@Generated("mvn generate-sources -pl cef4j-platform -Dcef.version=150.0.18+gdb11278+chromium-150.0.7871.213")
@SuppressWarnings({"SameReturnValue", "EmptyMethod", "UnusedReturnValue", "unused"})
public interface CefFrame extends CefLibraryObject {

    /**
     * True if this object is currently attached to a valid frame.
     * <p>Definition generated from cef_frame_capi.h
     * <pre>int (CEF_CALLBACK* is_valid)(struct _cef_frame_t* self);</pre>
     *
     * @see <a href="https://cef-builds.spotifycdn.com/docs/150.0/cef__frame_8h.html">cef_frame.h:62</a>
     */
    boolean isValid();

    /**
     * Execute undo in this frame.
     * <p>Definition generated from cef_frame_capi.h
     * <pre>void (CEF_CALLBACK* undo)(struct _cef_frame_t* self);</pre>
     *
     * @see <a href="https://cef-builds.spotifycdn.com/docs/150.0/cef__frame_8h.html">cef_frame.h:68</a>
     */
    void undo();

    /**
     * Execute redo in this frame.
     * <p>Definition generated from cef_frame_capi.h
     * <pre>void (CEF_CALLBACK* redo)(struct _cef_frame_t* self);</pre>
     *
     * @see <a href="https://cef-builds.spotifycdn.com/docs/150.0/cef__frame_8h.html">cef_frame.h:74</a>
     */
    void redo();

    /**
     * Execute cut in this frame.
     * <p>Definition generated from cef_frame_capi.h
     * <pre>void (CEF_CALLBACK* cut)(struct _cef_frame_t* self);</pre>
     *
     * @see <a href="https://cef-builds.spotifycdn.com/docs/150.0/cef__frame_8h.html">cef_frame.h:80</a>
     */
    void cut();

    /**
     * Execute copy in this frame.
     * <p>Definition generated from cef_frame_capi.h
     * <pre>void (CEF_CALLBACK* copy)(struct _cef_frame_t* self);</pre>
     *
     * @see <a href="https://cef-builds.spotifycdn.com/docs/150.0/cef__frame_8h.html">cef_frame.h:86</a>
     */
    void copy();

    /**
     * Execute paste in this frame.
     * <p>Definition generated from cef_frame_capi.h
     * <pre>void (CEF_CALLBACK* paste)(struct _cef_frame_t* self);</pre>
     *
     * @see <a href="https://cef-builds.spotifycdn.com/docs/150.0/cef__frame_8h.html">cef_frame.h:92</a>
     */
    void paste();

    /**
     * Execute paste and match style in this frame.
     * <p>Definition generated from cef_frame_capi.h
     * <pre>void (CEF_CALLBACK* paste_and_match_style)(struct _cef_frame_t* self);</pre>
     *
     * @see <a href="https://cef-builds.spotifycdn.com/docs/150.0/cef__frame_8h.html">cef_frame.h:98</a>
     */
    void pasteAndMatchStyle();

    /**
     * Execute delete in this frame.
     * <p>Definition generated from cef_frame_capi.h
     * <pre>void (CEF_CALLBACK* del)(struct _cef_frame_t* self);</pre>
     *
     * @see <a href="https://cef-builds.spotifycdn.com/docs/150.0/cef__frame_8h.html">cef_frame.h:104</a>
     */
    void del();

    /**
     * Execute select all in this frame.
     * <p>Definition generated from cef_frame_capi.h
     * <pre>void (CEF_CALLBACK* select_all)(struct _cef_frame_t* self);</pre>
     *
     * @see <a href="https://cef-builds.spotifycdn.com/docs/150.0/cef__frame_8h.html">cef_frame.h:110</a>
     */
    void selectAll();

    /**
     * Save this frame's HTML source to a temporary file and open it in the default text viewing application. This method can only be called from the browser process.
     * <p>Definition generated from cef_frame_capi.h
     * <pre>void (CEF_CALLBACK* view_source)(struct _cef_frame_t* self);</pre>
     *
     * @see <a href="https://cef-builds.spotifycdn.com/docs/150.0/cef__frame_8h.html">cef_frame.h:116</a>
     */
    void viewSource();

    /**
     * Retrieve this frame's HTML source as a string sent to the specified visitor.
     * <p>Definition generated from cef_frame_capi.h
     * <pre>void (CEF_CALLBACK* get_source)(struct _cef_frame_t* self, struct _cef_string_visitor_t* visitor);</pre>
     *
     * @see <a href="https://cef-builds.spotifycdn.com/docs/150.0/cef__frame_8h.html">cef_frame.h:124</a>
     */
    void getSource(@Nullable CefStringVisitor visitor);

    /**
     * Retrieve this frame's display text as a string sent to the specified visitor.
     * <p>Definition generated from cef_frame_capi.h
     * <pre>void (CEF_CALLBACK* get_text)(struct _cef_frame_t* self, struct _cef_string_visitor_t* visitor);</pre>
     *
     * @see <a href="https://cef-builds.spotifycdn.com/docs/150.0/cef__frame_8h.html">cef_frame.h:131</a>
     */
    void getText(@Nullable CefStringVisitor visitor);

    /**
     * Load the request represented by the {@code request} object.
     * <p>
     * <b>WARNING:</b> This method will fail with "bad IPC message" reason INVALID_INITIATOR_ORIGIN (213) unless you first navigate to the request origin using some other mechanism (LoadURL, link click, etc).
     * <p>Definition generated from cef_frame_capi.h
     * <pre>void (CEF_CALLBACK* load_request)(struct _cef_frame_t* self, struct _cef_request_t* request);</pre>
     *
     * @see <a href="https://cef-builds.spotifycdn.com/docs/150.0/cef__frame_8h.html">cef_frame.h:138</a>
     */
    void loadRequest(@Nullable CefRequest request);

    /**
     * Load the specified {@code url}.
     * <p>Definition generated from cef_frame_capi.h
     * <pre>void (CEF_CALLBACK* load_url)(struct _cef_frame_t* self, const cef_string_t* url);</pre>
     *
     * @see <a href="https://cef-builds.spotifycdn.com/docs/150.0/cef__frame_8h.html">cef_frame.h:148</a>
     */
    void loadUrl(@Nullable String url);

    /**
     * Execute a string of JavaScript code in this frame. The {@code script_url} parameter is the URL where the script in question can be found, if any. The renderer may request this URL to show the developer the source of the error.  The {@code start_line} parameter is the base line number to use for error reporting.
     * <p>Definition generated from cef_frame_capi.h
     * <pre>void (CEF_CALLBACK* execute_java_script)(struct _cef_frame_t* self, const cef_string_t* code, const cef_string_t* script_url, int start_line);</pre>
     *
     * @param scriptUrl may be null
     *
     * @see <a href="https://cef-builds.spotifycdn.com/docs/150.0/cef__frame_8h.html">cef_frame.h:154</a>
     */
    void executeJavaScript(@Nullable String code, @Nullable String scriptUrl, int startLine);

    /**
     * Returns {@code true} if this is the main (top-level) frame.
     * <p>Definition generated from cef_frame_capi.h
     * <pre>int (CEF_CALLBACK* is_main)(struct _cef_frame_t* self);</pre>
     *
     * @see <a href="https://cef-builds.spotifycdn.com/docs/150.0/cef__frame_8h.html">cef_frame.h:166</a>
     */
    boolean isMain();

    /**
     * Returns {@code true} if this is the focused frame.
     * <p>Definition generated from cef_frame_capi.h
     * <pre>int (CEF_CALLBACK* is_focused)(struct _cef_frame_t* self);</pre>
     *
     * @see <a href="https://cef-builds.spotifycdn.com/docs/150.0/cef__frame_8h.html">cef_frame.h:172</a>
     */
    boolean isFocused();

    /**
     * Returns the name for this frame. If the frame has an assigned name (for example, set via the iframe "name" attribute) then that value will be returned. Otherwise a unique name will be constructed based on the frame parent hierarchy. The main (top-level) frame will always have an empty name value.
     * <p>Definition generated from cef_frame_capi.h
     * <pre>cef_string_userfree_t (CEF_CALLBACK* get_name)(struct _cef_frame_t* self);</pre>
     *
     * @see <a href="https://cef-builds.spotifycdn.com/docs/150.0/cef__frame_8h.html">cef_frame.h:178</a>
     */
    Optional<String> getName();

    /**
     * Returns the globally unique identifier for this frame or empty if the underlying frame does not yet exist.
     * <p>Definition generated from cef_frame_capi.h
     * <pre>cef_string_userfree_t (CEF_CALLBACK* get_identifier)(struct _cef_frame_t* self);</pre>
     *
     * @see <a href="https://cef-builds.spotifycdn.com/docs/150.0/cef__frame_8h.html">cef_frame.h:188</a>
     */
    Optional<String> getIdentifier();

    /**
     * Returns the parent of this frame or {@code null} if this is the main (top-level) frame.
     * <p>Definition generated from cef_frame_capi.h
     * <pre>cef_frame_t* (CEF_CALLBACK* get_parent)(struct _cef_frame_t* self);</pre>
     *
     * @see <a href="https://cef-builds.spotifycdn.com/docs/150.0/cef__frame_8h.html">cef_frame.h:195</a>
     */
    Optional<CefFrame> getParent();

    /**
     * Returns the URL currently loaded in this frame.
     * <p>Definition generated from cef_frame_capi.h
     * <pre>cef_string_userfree_t (CEF_CALLBACK* get_url)(struct _cef_frame_t* self);</pre>
     *
     * @see <a href="https://cef-builds.spotifycdn.com/docs/150.0/cef__frame_8h.html">cef_frame.h:202</a>
     */
    Optional<String> getUrl();

    /**
     * Returns the browser that this frame belongs to.
     * <p>Definition generated from cef_frame_capi.h
     * <pre>cef_browser_t* (CEF_CALLBACK* get_browser)(struct _cef_frame_t* self);</pre>
     *
     * @see <a href="https://cef-builds.spotifycdn.com/docs/150.0/cef__frame_8h.html">cef_frame.h:208</a>
     */
    Optional<CefBrowser> getBrowser();

    /**
     * Get the V8 context associated with the frame. This method can only be called from the render process.
     * <p>Definition generated from cef_frame_capi.h
     * <pre>cef_v8_context_t* (CEF_CALLBACK* get_v8_context)(struct _cef_frame_t* self);</pre>
     *
     * @see <a href="https://cef-builds.spotifycdn.com/docs/150.0/cef__frame_8h.html">cef_frame.h:214</a>
     */
    Optional<CefV8Context> getV8Context();

    /**
     * Visit the DOM document. This method can only be called from the render process.
     * <p>Definition generated from cef_frame_capi.h
     * <pre>void (CEF_CALLBACK* visit_dom)(struct _cef_frame_t* self, struct _cef_domvisitor_t* visitor);</pre>
     *
     * @see <a href="https://cef-builds.spotifycdn.com/docs/150.0/cef__frame_8h.html">cef_frame.h:221</a>
     */
    void visitDom(@Nullable CefDomVisitor visitor);

    /**
     * Create a new URL request that will be treated as originating from this frame and the associated browser. Use CefURLRequest.create() instead if you do not want the request to have this association, in which case it may be handled differently (see documentation on that method). A request created with this method may only originate from the browser process, and will behave as follows:
     * <ul>
     * <li>It may be intercepted by the client via CefResourceRequestHandler or</li>
     * </ul>
     * CefSchemeHandlerFactory.
     * <ul>
     * <li>POST data may only contain a single element of type PDE_TYPE_FILE or</li>
     * </ul>
     * PDE_TYPE_BYTES.
     * <p>
     * The {@code request} object will be marked as read-only after calling this method.
     * <p>Definition generated from cef_frame_capi.h
     * <pre>cef_urlrequest_t* (CEF_CALLBACK* create_urlrequest)(struct _cef_frame_t* self, struct _cef_request_t* request, struct _cef_urlrequest_client_t* client);</pre>
     *
     * @see <a href="https://cef-builds.spotifycdn.com/docs/150.0/cef__frame_8h.html">cef_frame.h:228</a>
     */
    Optional<CefUrlRequest> createUrlRequest(@Nullable CefRequest request, @Nullable CefUrlRequestClient client);

    /**
     * Send a message to the specified {@code target_process}. Ownership of the message contents will be transferred and the {@code message} reference will be invalidated. Message delivery is not guaranteed in all cases (for example, if the browser is closing, navigating, or if the target process crashes). Send an ACK message back from the target process if confirmation is required.
     * <p>Definition generated from cef_frame_capi.h
     * <pre>void (CEF_CALLBACK* send_process_message)(struct _cef_frame_t* self, cef_process_id_t target_process, struct _cef_process_message_t* message);</pre>
     *
     * @see <a href="https://cef-builds.spotifycdn.com/docs/150.0/cef__frame_8h.html">cef_frame.h:248</a>
     */
    void sendProcessMessage(@Nonnull CefProcessId targetProcess, @Nullable CefProcessMessage message);
    final class NativePeer implements CefFrame, AutoCloseable {
        private final long nativePtr;
        private final java.lang.ref.Cleaner.Cleanable cleanable;
        private volatile boolean closed;

        NativePeer(long ptr) {
            this.nativePtr = ptr;
            this.cleanable = net.kurobako.cef4j.NativeCleaner.INSTANCE.register(this, new Release(ptr));
        }

        @Override
        public void peerClose() {
            closed = true;
            cleanable.clean();
        }

        @Override
        public boolean peerIsClosed() {
            return closed;
        }

        private void checkNotClosed() {
            if (closed) throw new IllegalStateException("CefFrame has been closed");
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
                release0(ptr);
            }
        }

        private static native void release0(long ptr);

        @Override
      public boolean isValid() {
          checkNotClosed();
          return isValid0(nativePtr);
      }

        @Override
      public void undo() {
          checkNotClosed();
          undo0(nativePtr);
      }

        @Override
      public void redo() {
          checkNotClosed();
          redo0(nativePtr);
      }

        @Override
      public void cut() {
          checkNotClosed();
          cut0(nativePtr);
      }

        @Override
      public void copy() {
          checkNotClosed();
          copy0(nativePtr);
      }

        @Override
      public void paste() {
          checkNotClosed();
          paste0(nativePtr);
      }

        @Override
      public void pasteAndMatchStyle() {
          checkNotClosed();
          pasteAndMatchStyle0(nativePtr);
      }

        @Override
      public void del() {
          checkNotClosed();
          del0(nativePtr);
      }

        @Override
      public void selectAll() {
          checkNotClosed();
          selectAll0(nativePtr);
      }

        @Override
      public void viewSource() {
          checkNotClosed();
          viewSource0(nativePtr);
      }

        @Override
      public void getSource(@Nullable CefStringVisitor visitor) {
          checkNotClosed();
          getSource0(nativePtr, visitor);
      }

        @Override
      public void getText(@Nullable CefStringVisitor visitor) {
          checkNotClosed();
          getText0(nativePtr, visitor);
      }

        @Override
      public void loadRequest(@Nullable CefRequest request) {
          checkNotClosed();
            CefLibraryObject.requireOpen(request, "CefRequest");
          loadRequest0(nativePtr, request);
      }

        @Override
      public void loadUrl(@Nullable String url) {
          checkNotClosed();
          loadUrl0(nativePtr, url);
      }

        @Override
      public void executeJavaScript(@Nullable String code, @Nullable String scriptUrl, int startLine) {
          checkNotClosed();
          executeJavaScript0(nativePtr, code, scriptUrl, startLine);
      }

        @Override
      public boolean isMain() {
          checkNotClosed();
          return isMain0(nativePtr);
      }

        @Override
      public boolean isFocused() {
          checkNotClosed();
          return isFocused0(nativePtr);
      }

        @Override
      public Optional<String> getName() {
          checkNotClosed();
          return Optional.ofNullable(getName0(nativePtr));
      }

        @Override
      public Optional<String> getIdentifier() {
          checkNotClosed();
          return Optional.ofNullable(getIdentifier0(nativePtr));
      }

        @Override
      public Optional<CefFrame> getParent() {
          checkNotClosed();
          return Optional.ofNullable(getParent0(nativePtr));
      }

        @Override
      public Optional<String> getUrl() {
          checkNotClosed();
          return Optional.ofNullable(getUrl0(nativePtr));
      }

        @Override
      public Optional<CefBrowser> getBrowser() {
          checkNotClosed();
          return Optional.ofNullable(getBrowser0(nativePtr));
      }

        @Override
      public Optional<CefV8Context> getV8Context() {
          checkNotClosed();
          return Optional.ofNullable(getV8Context0(nativePtr));
      }

        @Override
      public void visitDom(@Nullable CefDomVisitor visitor) {
          checkNotClosed();
          visitDom0(nativePtr, visitor);
      }

        @Override
      public Optional<CefUrlRequest> createUrlRequest(@Nullable CefRequest request, @Nullable CefUrlRequestClient client) {
          checkNotClosed();
            CefLibraryObject.requireOpen(request, "CefRequest");
          return Optional.ofNullable(createUrlRequest0(nativePtr, request, client));
      }

        @Override
      public void sendProcessMessage(@Nonnull CefProcessId targetProcess, @Nullable CefProcessMessage message) {
          checkNotClosed();
            CefLibraryObject.requireOpen(message, "CefProcessMessage");
          sendProcessMessage0(nativePtr, targetProcess, message);
      }


        static native boolean isValid0(long self);

        static native void undo0(long self);

        static native void redo0(long self);

        static native void cut0(long self);

        static native void copy0(long self);

        static native void paste0(long self);

        static native void pasteAndMatchStyle0(long self);

        static native void del0(long self);

        static native void selectAll0(long self);

        static native void viewSource0(long self);

        static native void getSource0(long self, @Nullable CefStringVisitor visitor);

        static native void getText0(long self, @Nullable CefStringVisitor visitor);

        static native void loadRequest0(long self, @Nullable CefRequest request);

        static native void loadUrl0(long self, @Nullable String url);

        static native void executeJavaScript0(long self, @Nullable String code, @Nullable String scriptUrl, int startLine);

        static native boolean isMain0(long self);

        static native boolean isFocused0(long self);

        static native String getName0(long self);

        static native String getIdentifier0(long self);

        static native CefFrame getParent0(long self);

        static native String getUrl0(long self);

        static native CefBrowser getBrowser0(long self);

        static native CefV8Context getV8Context0(long self);

        static native void visitDom0(long self, @Nullable CefDomVisitor visitor);

        static native CefUrlRequest createUrlRequest0(long self, @Nullable CefRequest request, @Nullable CefUrlRequestClient client);

        static native void sendProcessMessage0(long self, @Nonnull CefProcessId targetProcess, @Nullable CefProcessMessage message);


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
