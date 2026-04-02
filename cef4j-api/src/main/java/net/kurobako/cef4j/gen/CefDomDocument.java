// GENERATED - do not edit. Regenerate via: mvn generate-sources -pl cef4j-native
package net.kurobako.cef4j.gen;

import java.util.Optional;
import javax.annotation.Nullable;

/**
 * Class used to represent a DOM document. The methods of this class should only be called on the render process main
 * thread thread.
 *
 * <p>Definition generated from cef_dom_capi.h
 *
 * <pre>typedef struct _cef_domdocument_t {
 *   cef_base_ref_counted_t base;
 *   ...
 * } cef_domdocument_t;</pre>
 *
 * @see <a href="https://cef-builds.spotifycdn.com/docs/146.0/cef__dom_8h.html">cef_dom.h:66</a>
 */
public interface CefDomDocument extends CefLibraryObject {

    /**
     * Returns the document type.
     *
     * <p>Definition generated from cef_dom_capi.h
     *
     * <pre>cef_dom_document_type_t (CEF_CALLBACK* get_type)(struct _cef_domdocument_t* self);</pre>
     *
     * @return the result, or {@code MENUITEMTYPE_NONE} for default handling
     * @see <a href="https://cef-builds.spotifycdn.com/docs/146.0/cef__dom_8h.html">cef_dom.h:75</a>
     */
    CefDomDocumentType getType();

    /**
     * Returns the root document node.
     *
     * <p>Definition generated from cef_dom_capi.h
     *
     * <pre>cef_domnode_t* (CEF_CALLBACK* get_document)(struct _cef_domdocument_t* self);</pre>
     *
     * @see <a href="https://cef-builds.spotifycdn.com/docs/146.0/cef__dom_8h.html">cef_dom.h:81</a>
     */
    Optional<CefDomNode> getDocument();

    /**
     * Returns the BODY node of an HTML document.
     *
     * <p>Definition generated from cef_dom_capi.h
     *
     * <pre>cef_domnode_t* (CEF_CALLBACK* get_body)(struct _cef_domdocument_t* self);</pre>
     *
     * @see <a href="https://cef-builds.spotifycdn.com/docs/146.0/cef__dom_8h.html">cef_dom.h:87</a>
     */
    Optional<CefDomNode> getBody();

    /**
     * Returns the HEAD node of an HTML document.
     *
     * <p>Definition generated from cef_dom_capi.h
     *
     * <pre>cef_domnode_t* (CEF_CALLBACK* get_head)(struct _cef_domdocument_t* self);</pre>
     *
     * @see <a href="https://cef-builds.spotifycdn.com/docs/146.0/cef__dom_8h.html">cef_dom.h:93</a>
     */
    Optional<CefDomNode> getHead();

    /**
     * Returns the title of an HTML document.
     *
     * <p>Definition generated from cef_dom_capi.h
     *
     * <pre>cef_string_userfree_t (CEF_CALLBACK* get_title)(struct _cef_domdocument_t* self);</pre>
     *
     * @see <a href="https://cef-builds.spotifycdn.com/docs/146.0/cef__dom_8h.html">cef_dom.h:99</a>
     */
    Optional<String> getTitle();

    /**
     * Returns the document element with the specified ID value.
     *
     * <p>Definition generated from cef_dom_capi.h
     *
     * <pre>cef_domnode_t* (CEF_CALLBACK* get_element_by_id)(struct _cef_domdocument_t* self, const cef_string_t* id);
     * </pre>
     *
     * @see <a href="https://cef-builds.spotifycdn.com/docs/146.0/cef__dom_8h.html">cef_dom.h:105</a>
     */
    Optional<CefDomNode> getElementById(@Nullable String id);

    /**
     * Returns the node that currently has keyboard focus.
     *
     * <p>Definition generated from cef_dom_capi.h
     *
     * <pre>cef_domnode_t* (CEF_CALLBACK* get_focused_node)(struct _cef_domdocument_t* self);</pre>
     *
     * @see <a href="https://cef-builds.spotifycdn.com/docs/146.0/cef__dom_8h.html">cef_dom.h:111</a>
     */
    Optional<CefDomNode> getFocusedNode();

    /**
     * Returns {@code true} if a portion of the document is selected.
     *
     * <p>Definition generated from cef_dom_capi.h
     *
     * <pre>int (CEF_CALLBACK* has_selection)(struct _cef_domdocument_t* self);</pre>
     *
     * @see <a href="https://cef-builds.spotifycdn.com/docs/146.0/cef__dom_8h.html">cef_dom.h:117</a>
     */
    boolean hasSelection();

    /**
     * Returns the selection offset within the start node.
     *
     * <p>Definition generated from cef_dom_capi.h
     *
     * <pre>int (CEF_CALLBACK* get_selection_start_offset)(struct _cef_domdocument_t* self);</pre>
     *
     * @see <a href="https://cef-builds.spotifycdn.com/docs/146.0/cef__dom_8h.html">cef_dom.h:123</a>
     */
    int getSelectionStartOffset();

    /**
     * Returns the selection offset within the end node.
     *
     * <p>Definition generated from cef_dom_capi.h
     *
     * <pre>int (CEF_CALLBACK* get_selection_end_offset)(struct _cef_domdocument_t* self);</pre>
     *
     * @see <a href="https://cef-builds.spotifycdn.com/docs/146.0/cef__dom_8h.html">cef_dom.h:129</a>
     */
    int getSelectionEndOffset();

    /**
     * Returns the contents of this selection as markup.
     *
     * <p>Definition generated from cef_dom_capi.h
     *
     * <pre>cef_string_userfree_t (CEF_CALLBACK* get_selection_as_markup)(struct _cef_domdocument_t* self);</pre>
     *
     * @see <a href="https://cef-builds.spotifycdn.com/docs/146.0/cef__dom_8h.html">cef_dom.h:135</a>
     */
    Optional<String> getSelectionAsMarkup();

    /**
     * Returns the contents of this selection as text.
     *
     * <p>Definition generated from cef_dom_capi.h
     *
     * <pre>cef_string_userfree_t (CEF_CALLBACK* get_selection_as_text)(struct _cef_domdocument_t* self);</pre>
     *
     * @see <a href="https://cef-builds.spotifycdn.com/docs/146.0/cef__dom_8h.html">cef_dom.h:141</a>
     */
    Optional<String> getSelectionAsText();

    /**
     * Returns the base URL for the document.
     *
     * <p>Definition generated from cef_dom_capi.h
     *
     * <pre>cef_string_userfree_t (CEF_CALLBACK* get_base_url)(struct _cef_domdocument_t* self);</pre>
     *
     * @see <a href="https://cef-builds.spotifycdn.com/docs/146.0/cef__dom_8h.html">cef_dom.h:147</a>
     */
    Optional<String> getBaseUrl();

    /**
     * Returns a complete URL based on the document base URL and the specified partial URL.
     *
     * <p>Definition generated from cef_dom_capi.h
     *
     * <pre>
     * cef_string_userfree_t (CEF_CALLBACK* get_complete_url)(struct _cef_domdocument_t* self, const cef_string_t* partialURL);
     * </pre>
     *
     * @see <a href="https://cef-builds.spotifycdn.com/docs/146.0/cef__dom_8h.html">cef_dom.h:153</a>
     */
    Optional<String> getCompleteUrl(@Nullable String partialURL);

    final class NativePeer implements CefDomDocument, AutoCloseable {
        private final long nativePtr;
        private final java.lang.ref.Cleaner.Cleanable cleanable;
        private volatile boolean closed;

        NativePeer(long ptr) {
            this.nativePtr = ptr;
            this.cleanable = net.kurobako.cef4j.NativeCleaner.INSTANCE.register(this, new Release(ptr));
        }

        @Override
        public void close() {
            closed = true;
            cleanable.clean();
        }

        @Override
        public boolean isClosed() {
            return closed;
        }

        private void checkNotClosed() {
            if (closed) throw new IllegalStateException("CefDomDocument has been closed");
        }

        private static final org.slf4j.Logger _log = org.slf4j.LoggerFactory.getLogger(CefDomDocument.class);

        private static class Release implements Runnable {
            private final long ptr;

            Release(long ptr) {
                this.ptr = ptr;
            }

            @Override
            public void run() {
                if (_log.isTraceEnabled()) _log.trace("release CefDomDocument 0x{}", Long.toHexString(ptr));
                N_Release(ptr);
            }
        }

        private static native void N_Release(long ptr);

        @Override
        public CefDomDocumentType getType() {
            checkNotClosed();
            return N_GetType(nativePtr);
        }

        @Override
        public Optional<CefDomNode> getDocument() {
            checkNotClosed();
            return Optional.ofNullable(N_GetDocument(nativePtr));
        }

        @Override
        public Optional<CefDomNode> getBody() {
            checkNotClosed();
            return Optional.ofNullable(N_GetBody(nativePtr));
        }

        @Override
        public Optional<CefDomNode> getHead() {
            checkNotClosed();
            return Optional.ofNullable(N_GetHead(nativePtr));
        }

        @Override
        public Optional<String> getTitle() {
            checkNotClosed();
            return Optional.ofNullable(N_GetTitle(nativePtr));
        }

        @Override
        public Optional<CefDomNode> getElementById(@Nullable String id) {
            checkNotClosed();
            return Optional.ofNullable(N_GetElementById(nativePtr, id));
        }

        @Override
        public Optional<CefDomNode> getFocusedNode() {
            checkNotClosed();
            return Optional.ofNullable(N_GetFocusedNode(nativePtr));
        }

        @Override
        public boolean hasSelection() {
            checkNotClosed();
            return N_HasSelection(nativePtr);
        }

        @Override
        public int getSelectionStartOffset() {
            checkNotClosed();
            return N_GetSelectionStartOffset(nativePtr);
        }

        @Override
        public int getSelectionEndOffset() {
            checkNotClosed();
            return N_GetSelectionEndOffset(nativePtr);
        }

        @Override
        public Optional<String> getSelectionAsMarkup() {
            checkNotClosed();
            return Optional.ofNullable(N_GetSelectionAsMarkup(nativePtr));
        }

        @Override
        public Optional<String> getSelectionAsText() {
            checkNotClosed();
            return Optional.ofNullable(N_GetSelectionAsText(nativePtr));
        }

        @Override
        public Optional<String> getBaseUrl() {
            checkNotClosed();
            return Optional.ofNullable(N_GetBaseUrl(nativePtr));
        }

        @Override
        public Optional<String> getCompleteUrl(@Nullable String partialURL) {
            checkNotClosed();
            return Optional.ofNullable(N_GetCompleteUrl(nativePtr, partialURL));
        }

        private static native CefDomDocumentType N_GetType(long self);

        private static native CefDomNode N_GetDocument(long self);

        private static native CefDomNode N_GetBody(long self);

        private static native CefDomNode N_GetHead(long self);

        private static native String N_GetTitle(long self);

        private static native CefDomNode N_GetElementById(long self, String id);

        private static native CefDomNode N_GetFocusedNode(long self);

        private static native boolean N_HasSelection(long self);

        private static native int N_GetSelectionStartOffset(long self);

        private static native int N_GetSelectionEndOffset(long self);

        private static native String N_GetSelectionAsMarkup(long self);

        private static native String N_GetSelectionAsText(long self);

        private static native String N_GetBaseUrl(long self);

        private static native String N_GetCompleteUrl(long self, String partialURL);

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
            return "CefDomDocument{0x" + Long.toHexString(nativePtr) + "}";
        }
    }
}
