// GENERATED - do not edit. Regenerate via: mvn generate-sources -pl cef4j-platform -Dcef.version=150.0.18+gdb11278+chromium-150.0.7871.213
package net.kurobako.cef4j.gen;

import javax.annotation.processing.Generated;
import java.util.Map;
import java.util.Optional;
import javax.annotation.Nonnull;
import javax.annotation.Nullable;

/**
 * Class used to represent a DOM node. The methods of this class should only be called on the render process main thread.
 * <p>Definition generated from cef_dom_capi.h
 * <pre>typedef struct _cef_domnode_t {
 *   cef_base_ref_counted_t base;
 *   ...
 * } cef_domnode_t;</pre>
 *
 * @see <a href="https://cef-builds.spotifycdn.com/docs/150.0/cef__dom_8h.html">cef_dom.h:161</a>
 */
@Generated("mvn generate-sources -pl cef4j-platform -Dcef.version=150.0.18+gdb11278+chromium-150.0.7871.213")
@SuppressWarnings({"SameReturnValue", "EmptyMethod", "UnusedReturnValue", "unused"})
public interface CefDomNode extends CefLibraryObject {

    /**
     * Returns the type for this node.
     * <p>Definition generated from cef_dom_capi.h
     * <pre>cef_dom_node_type_t (CEF_CALLBACK* get_type)(struct _cef_domnode_t* self);</pre>
     *
     * @return the result, or {@code DOM_NODE_TYPE_UNSUPPORTED} for default handling
     *
     * @see <a href="https://cef-builds.spotifycdn.com/docs/150.0/cef__dom_8h.html">cef_dom.h:172</a>
     */
    CefDomNodeType getType();

    /**
     * Returns {@code true} if this is a text node.
     * <p>Definition generated from cef_dom_capi.h
     * <pre>int (CEF_CALLBACK* is_text)(struct _cef_domnode_t* self);</pre>
     *
     * @see <a href="https://cef-builds.spotifycdn.com/docs/150.0/cef__dom_8h.html">cef_dom.h:178</a>
     */
    boolean isText();

    /**
     * Returns {@code true} if this is an element node.
     * <p>Definition generated from cef_dom_capi.h
     * <pre>int (CEF_CALLBACK* is_element)(struct _cef_domnode_t* self);</pre>
     *
     * @see <a href="https://cef-builds.spotifycdn.com/docs/150.0/cef__dom_8h.html">cef_dom.h:184</a>
     */
    boolean isElement();

    /**
     * Returns {@code true} if this is an editable node.
     * <p>Definition generated from cef_dom_capi.h
     * <pre>int (CEF_CALLBACK* is_editable)(struct _cef_domnode_t* self);</pre>
     *
     * @see <a href="https://cef-builds.spotifycdn.com/docs/150.0/cef__dom_8h.html">cef_dom.h:190</a>
     */
    boolean isEditable();

    /**
     * Returns {@code true} if this is a form control element node.
     * <p>Definition generated from cef_dom_capi.h
     * <pre>int (CEF_CALLBACK* is_form_control_element)(struct _cef_domnode_t* self);</pre>
     *
     * @return the result, or {@code DOM_NODE_TYPE_UNSUPPORTED} for default handling
     *
     * @see <a href="https://cef-builds.spotifycdn.com/docs/150.0/cef__dom_8h.html">cef_dom.h:196</a>
     */
    boolean isFormControlElement();

    /**
     * Returns the type of this form control element node.
     * <p>Definition generated from cef_dom_capi.h
     * <pre>cef_dom_form_control_type_t (CEF_CALLBACK* get_form_control_element_type)(struct _cef_domnode_t* self);</pre>
     *
     * @return the result, or {@code DOM_FORM_CONTROL_TYPE_UNSUPPORTED} for default handling
     *
     * @see <a href="https://cef-builds.spotifycdn.com/docs/150.0/cef__dom_8h.html">cef_dom.h:202</a>
     */
    CefDomFormControlType getFormControlElementType();

    /**
     * Returns {@code true} if this object is pointing to the same handle as {@code that} object.
     * <p>Definition generated from cef_dom_capi.h
     * <pre>int (CEF_CALLBACK* is_same)(struct _cef_domnode_t* self, struct _cef_domnode_t* that);</pre>
     *
     * @see <a href="https://cef-builds.spotifycdn.com/docs/150.0/cef__dom_8h.html">cef_dom.h:208</a>
     */
    boolean isSame(@Nullable CefDomNode that);

    /**
     * Returns the name of this node.
     * <p>Definition generated from cef_dom_capi.h
     * <pre>cef_string_userfree_t (CEF_CALLBACK* get_name)(struct _cef_domnode_t* self);</pre>
     *
     * @see <a href="https://cef-builds.spotifycdn.com/docs/150.0/cef__dom_8h.html">cef_dom.h:215</a>
     */
    Optional<String> getName();

    /**
     * Returns the value of this node.
     * <p>Definition generated from cef_dom_capi.h
     * <pre>cef_string_userfree_t (CEF_CALLBACK* get_value)(struct _cef_domnode_t* self);</pre>
     *
     * @see <a href="https://cef-builds.spotifycdn.com/docs/150.0/cef__dom_8h.html">cef_dom.h:221</a>
     */
    Optional<String> getValue();

    /**
     * Set the value of this node. Returns {@code true} on success.
     * <p>Definition generated from cef_dom_capi.h
     * <pre>int (CEF_CALLBACK* set_value)(struct _cef_domnode_t* self, const cef_string_t* value);</pre>
     *
     * @see <a href="https://cef-builds.spotifycdn.com/docs/150.0/cef__dom_8h.html">cef_dom.h:227</a>
     */
    boolean setValue(@Nullable String value);

    /**
     * Returns the contents of this node as markup.
     * <p>Definition generated from cef_dom_capi.h
     * <pre>cef_string_userfree_t (CEF_CALLBACK* get_as_markup)(struct _cef_domnode_t* self);</pre>
     *
     * @see <a href="https://cef-builds.spotifycdn.com/docs/150.0/cef__dom_8h.html">cef_dom.h:233</a>
     */
    Optional<String> getAsMarkup();

    /**
     * Returns the document associated with this node.
     * <p>Definition generated from cef_dom_capi.h
     * <pre>cef_domdocument_t* (CEF_CALLBACK* get_document)(struct _cef_domnode_t* self);</pre>
     *
     * @see <a href="https://cef-builds.spotifycdn.com/docs/150.0/cef__dom_8h.html">cef_dom.h:239</a>
     */
    Optional<CefDomDocument> getDocument();

    /**
     * Returns the parent node.
     * <p>Definition generated from cef_dom_capi.h
     * <pre>cef_domnode_t* (CEF_CALLBACK* get_parent)(struct _cef_domnode_t* self);</pre>
     *
     * @see <a href="https://cef-builds.spotifycdn.com/docs/150.0/cef__dom_8h.html">cef_dom.h:245</a>
     */
    Optional<CefDomNode> getParent();

    /**
     * Returns the previous sibling node.
     * <p>Definition generated from cef_dom_capi.h
     * <pre>cef_domnode_t* (CEF_CALLBACK* get_previous_sibling)(struct _cef_domnode_t* self);</pre>
     *
     * @see <a href="https://cef-builds.spotifycdn.com/docs/150.0/cef__dom_8h.html">cef_dom.h:251</a>
     */
    Optional<CefDomNode> getPreviousSibling();

    /**
     * Returns the next sibling node.
     * <p>Definition generated from cef_dom_capi.h
     * <pre>cef_domnode_t* (CEF_CALLBACK* get_next_sibling)(struct _cef_domnode_t* self);</pre>
     *
     * @see <a href="https://cef-builds.spotifycdn.com/docs/150.0/cef__dom_8h.html">cef_dom.h:257</a>
     */
    Optional<CefDomNode> getNextSibling();

    /**
     * Returns {@code true} if this node has child nodes.
     * <p>Definition generated from cef_dom_capi.h
     * <pre>int (CEF_CALLBACK* has_children)(struct _cef_domnode_t* self);</pre>
     *
     * @see <a href="https://cef-builds.spotifycdn.com/docs/150.0/cef__dom_8h.html">cef_dom.h:263</a>
     */
    boolean hasChildren();

    /**
     * Return the first child node.
     * <p>Definition generated from cef_dom_capi.h
     * <pre>cef_domnode_t* (CEF_CALLBACK* get_first_child)(struct _cef_domnode_t* self);</pre>
     *
     * @see <a href="https://cef-builds.spotifycdn.com/docs/150.0/cef__dom_8h.html">cef_dom.h:269</a>
     */
    Optional<CefDomNode> getFirstChild();

    /**
     * Returns the last child node.
     * <p>Definition generated from cef_dom_capi.h
     * <pre>cef_domnode_t* (CEF_CALLBACK* get_last_child)(struct _cef_domnode_t* self);</pre>
     *
     * @see <a href="https://cef-builds.spotifycdn.com/docs/150.0/cef__dom_8h.html">cef_dom.h:275</a>
     */
    Optional<CefDomNode> getLastChild();

    /**
     * Returns the tag name of this element.
     * <p>Definition generated from cef_dom_capi.h
     * <pre>cef_string_userfree_t (CEF_CALLBACK* get_element_tag_name)(struct _cef_domnode_t* self);</pre>
     *
     * @see <a href="https://cef-builds.spotifycdn.com/docs/150.0/cef__dom_8h.html">cef_dom.h:283</a>
     */
    Optional<String> getElementTagName();

    /**
     * Returns {@code true} if this element has attributes.
     * <p>Definition generated from cef_dom_capi.h
     * <pre>int (CEF_CALLBACK* has_element_attributes)(struct _cef_domnode_t* self);</pre>
     *
     * @see <a href="https://cef-builds.spotifycdn.com/docs/150.0/cef__dom_8h.html">cef_dom.h:289</a>
     */
    boolean hasElementAttributes();

    /**
     * Returns {@code true} if this element has an attribute named {@code attrName}.
     * <p>Definition generated from cef_dom_capi.h
     * <pre>int (CEF_CALLBACK* has_element_attribute)(struct _cef_domnode_t* self, const cef_string_t* attrName);</pre>
     *
     * @see <a href="https://cef-builds.spotifycdn.com/docs/150.0/cef__dom_8h.html">cef_dom.h:295</a>
     */
    boolean hasElementAttribute(@Nullable String attrName);

    /**
     * Returns the element attribute named {@code attrName}.
     * <p>Definition generated from cef_dom_capi.h
     * <pre>cef_string_userfree_t (CEF_CALLBACK* get_element_attribute)(struct _cef_domnode_t* self, const cef_string_t* attrName);</pre>
     *
     * @see <a href="https://cef-builds.spotifycdn.com/docs/150.0/cef__dom_8h.html">cef_dom.h:301</a>
     */
    Optional<String> getElementAttribute(@Nullable String attrName);

    /**
     * Returns a map of all element attributes.
     * <p>Definition generated from cef_dom_capi.h
     * <pre>void (CEF_CALLBACK* get_element_attributes)(struct _cef_domnode_t* self, cef_string_map_t attrMap);</pre>
     *
     * @see <a href="https://cef-builds.spotifycdn.com/docs/150.0/cef__dom_8h.html">cef_dom.h:307</a>
     */
    void getElementAttributes(@Nonnull Map<String, String> attrMap);

    /**
     * Set the value for the element attribute named {@code attrName}. Returns {@code true} on success.
     * <p>Definition generated from cef_dom_capi.h
     * <pre>int (CEF_CALLBACK* set_element_attribute)(struct _cef_domnode_t* self, const cef_string_t* attrName, const cef_string_t* value);</pre>
     *
     * @see <a href="https://cef-builds.spotifycdn.com/docs/150.0/cef__dom_8h.html">cef_dom.h:313</a>
     */
    boolean setElementAttribute(@Nullable String attrName, @Nullable String value);

    /**
     * Returns the inner text of the element.
     * <p>Definition generated from cef_dom_capi.h
     * <pre>cef_string_userfree_t (CEF_CALLBACK* get_element_inner_text)(struct _cef_domnode_t* self);</pre>
     *
     * @see <a href="https://cef-builds.spotifycdn.com/docs/150.0/cef__dom_8h.html">cef_dom.h:321</a>
     */
    Optional<String> getElementInnerText();

    /**
     * Returns the bounds of the element in device pixels. Use "window.devicePixelRatio" to convert to/from CSS pixels.
     * <p>Definition generated from cef_dom_capi.h
     * <pre>cef_rect_t* (CEF_CALLBACK* get_element_bounds)(struct _cef_domnode_t* self);</pre>
     *
     * @see <a href="https://cef-builds.spotifycdn.com/docs/150.0/cef__dom_8h.html">cef_dom.h:327</a>
     */
    CefRect getElementBounds();
    final class NativePeer implements CefDomNode, AutoCloseable {
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
            if (closed) throw new IllegalStateException("CefDomNode has been closed");
        }

        private static final org.slf4j.Logger _log = org.slf4j.LoggerFactory.getLogger(CefDomNode.class);

        private static class Release implements Runnable {
            private final long ptr;

            Release(long ptr) {
                this.ptr = ptr;
            }

            @Override
            public void run() {
                if (_log.isTraceEnabled()) _log.trace("release CefDomNode 0x{}", Long.toHexString(ptr));
                release0(ptr);
            }
        }

        private static native void release0(long ptr);

        @Override
      public CefDomNodeType getType() {
          checkNotClosed();
          return getType0(nativePtr);
      }

        @Override
      public boolean isText() {
          checkNotClosed();
          return isText0(nativePtr);
      }

        @Override
      public boolean isElement() {
          checkNotClosed();
          return isElement0(nativePtr);
      }

        @Override
      public boolean isEditable() {
          checkNotClosed();
          return isEditable0(nativePtr);
      }

        @Override
      public boolean isFormControlElement() {
          checkNotClosed();
          return isFormControlElement0(nativePtr);
      }

        @Override
      public CefDomFormControlType getFormControlElementType() {
          checkNotClosed();
          return getFormControlElementType0(nativePtr);
      }

        @Override
      public boolean isSame(@Nullable CefDomNode that) {
          checkNotClosed();
            CefLibraryObject.requireOpen(that, "CefDomNode");
          return isSame0(nativePtr, that);
      }

        @Override
      public Optional<String> getName() {
          checkNotClosed();
          return Optional.ofNullable(getName0(nativePtr));
      }

        @Override
      public Optional<String> getValue() {
          checkNotClosed();
          return Optional.ofNullable(getValue0(nativePtr));
      }

        @Override
      public boolean setValue(@Nullable String value) {
          checkNotClosed();
          return setValue0(nativePtr, value);
      }

        @Override
      public Optional<String> getAsMarkup() {
          checkNotClosed();
          return Optional.ofNullable(getAsMarkup0(nativePtr));
      }

        @Override
      public Optional<CefDomDocument> getDocument() {
          checkNotClosed();
          return Optional.ofNullable(getDocument0(nativePtr));
      }

        @Override
      public Optional<CefDomNode> getParent() {
          checkNotClosed();
          return Optional.ofNullable(getParent0(nativePtr));
      }

        @Override
      public Optional<CefDomNode> getPreviousSibling() {
          checkNotClosed();
          return Optional.ofNullable(getPreviousSibling0(nativePtr));
      }

        @Override
      public Optional<CefDomNode> getNextSibling() {
          checkNotClosed();
          return Optional.ofNullable(getNextSibling0(nativePtr));
      }

        @Override
      public boolean hasChildren() {
          checkNotClosed();
          return hasChildren0(nativePtr);
      }

        @Override
      public Optional<CefDomNode> getFirstChild() {
          checkNotClosed();
          return Optional.ofNullable(getFirstChild0(nativePtr));
      }

        @Override
      public Optional<CefDomNode> getLastChild() {
          checkNotClosed();
          return Optional.ofNullable(getLastChild0(nativePtr));
      }

        @Override
      public Optional<String> getElementTagName() {
          checkNotClosed();
          return Optional.ofNullable(getElementTagName0(nativePtr));
      }

        @Override
      public boolean hasElementAttributes() {
          checkNotClosed();
          return hasElementAttributes0(nativePtr);
      }

        @Override
      public boolean hasElementAttribute(@Nullable String attrName) {
          checkNotClosed();
          return hasElementAttribute0(nativePtr, attrName);
      }

        @Override
      public Optional<String> getElementAttribute(@Nullable String attrName) {
          checkNotClosed();
          return Optional.ofNullable(getElementAttribute0(nativePtr, attrName));
      }

        @Override
      public void getElementAttributes(@Nonnull Map<String, String> attrMap) {
          checkNotClosed();
          getElementAttributes0(nativePtr, attrMap);
      }

        @Override
      public boolean setElementAttribute(@Nullable String attrName, @Nullable String value) {
          checkNotClosed();
          return setElementAttribute0(nativePtr, attrName, value);
      }

        @Override
      public Optional<String> getElementInnerText() {
          checkNotClosed();
          return Optional.ofNullable(getElementInnerText0(nativePtr));
      }

        @Override
      public CefRect getElementBounds() {
          checkNotClosed();
          return getElementBounds0(nativePtr);
      }


        static native CefDomNodeType getType0(long self);

        static native boolean isText0(long self);

        static native boolean isElement0(long self);

        static native boolean isEditable0(long self);

        static native boolean isFormControlElement0(long self);

        static native CefDomFormControlType getFormControlElementType0(long self);

        static native boolean isSame0(long self, @Nullable CefDomNode that);

        static native String getName0(long self);

        static native String getValue0(long self);

        static native boolean setValue0(long self, @Nullable String value);

        static native String getAsMarkup0(long self);

        static native CefDomDocument getDocument0(long self);

        static native CefDomNode getParent0(long self);

        static native CefDomNode getPreviousSibling0(long self);

        static native CefDomNode getNextSibling0(long self);

        static native boolean hasChildren0(long self);

        static native CefDomNode getFirstChild0(long self);

        static native CefDomNode getLastChild0(long self);

        static native String getElementTagName0(long self);

        static native boolean hasElementAttributes0(long self);

        static native boolean hasElementAttribute0(long self, @Nullable String attrName);

        static native String getElementAttribute0(long self, @Nullable String attrName);

        static native void getElementAttributes0(long self, @Nonnull Map<String, String> attrMap);

        static native boolean setElementAttribute0(long self, @Nullable String attrName, @Nullable String value);

        static native String getElementInnerText0(long self);

        static native CefRect getElementBounds0(long self);


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
            return "CefDomNode{0x" + Long.toHexString(nativePtr) + "}";
        }
    }

}
