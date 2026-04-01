// GENERATED - do not edit. Regenerate via: mvn generate-sources -pl cef4j-native
package net.kurobako.cef4j.gen;

import java.util.Map;
import java.util.Optional;
import javax.annotation.Nonnull;

/**
 * Class used to represent a DOM node. The methods of this class should only be called on the render process main
 * thread.
 *
 * <p>Definition generated from cef_dom_capi.h
 *
 * <pre>typedef struct _cef_domnode_t {
 *   cef_base_ref_counted_t base;
 *   ...
 * } cef_domnode_t;</pre>
 *
 * @see <a href="https://cef-builds.spotifycdn.com/docs/146.0/cef__dom_8h.html">cef_dom.h:161</a>
 */
public interface CefDomNode extends CefLibraryObject {

    /**
     * Returns the item type for the specified {@code command_id}.
     *
     * <p>Definition generated from cef_dom_capi.h
     *
     * <pre>cef_dom_node_type_t (CEF_CALLBACK* get_type)(struct _cef_domnode_t* self);</pre>
     *
     * @return the result, or {@code MENUITEMTYPE_NONE} for default handling
     * @see <a href="https://cef-builds.spotifycdn.com/docs/146.0/cef__menu__model_8h.html">cef_menu_model.h:215</a>
     */
    CefDomNodeType getType();

    /**
     * Returns {@code true} if this is a text node.
     *
     * <p>Definition generated from cef_dom_capi.h
     *
     * <pre>int (CEF_CALLBACK* is_text)(struct _cef_domnode_t* self);</pre>
     *
     * @see <a href="https://cef-builds.spotifycdn.com/docs/146.0/cef__dom_8h.html">cef_dom.h:178</a>
     */
    boolean isText();

    /**
     * Returns {@code true} if this is an element node.
     *
     * <p>Definition generated from cef_dom_capi.h
     *
     * <pre>int (CEF_CALLBACK* is_element)(struct _cef_domnode_t* self);</pre>
     *
     * @see <a href="https://cef-builds.spotifycdn.com/docs/146.0/cef__dom_8h.html">cef_dom.h:184</a>
     */
    boolean isElement();

    /**
     * Returns {@code true} if this is an editable node.
     *
     * <p>Definition generated from cef_dom_capi.h
     *
     * <pre>int (CEF_CALLBACK* is_editable)(struct _cef_domnode_t* self);</pre>
     *
     * @see <a href="https://cef-builds.spotifycdn.com/docs/146.0/cef__dom_8h.html">cef_dom.h:190</a>
     */
    boolean isEditable();

    /**
     * Returns {@code true} if this is a form control element node.
     *
     * <p>Definition generated from cef_dom_capi.h
     *
     * <pre>int (CEF_CALLBACK* is_form_control_element)(struct _cef_domnode_t* self);</pre>
     *
     * @return the result, or {@code DOM_NODE_TYPE_UNSUPPORTED} for default handling
     * @see <a href="https://cef-builds.spotifycdn.com/docs/146.0/cef__dom_8h.html">cef_dom.h:196</a>
     */
    boolean isFormControlElement();

    /**
     * Returns the type of this form control element node.
     *
     * <p>Definition generated from cef_dom_capi.h
     *
     * <pre>cef_dom_form_control_type_t (CEF_CALLBACK* get_form_control_element_type)(struct _cef_domnode_t* self);
     * </pre>
     *
     * @return the result, or {@code DOM_FORM_CONTROL_TYPE_UNSUPPORTED} for default handling
     * @see <a href="https://cef-builds.spotifycdn.com/docs/146.0/cef__dom_8h.html">cef_dom.h:202</a>
     */
    CefDomFormControlType getFormControlElementType();

    /**
     * Returns {@code true} if this object is pointing to the same handle as {@code that} object.
     *
     * <p>Definition generated from cef_dom_capi.h
     *
     * <pre>int (CEF_CALLBACK* is_same)(struct _cef_domnode_t* self, struct _cef_domnode_t* that);</pre>
     *
     * @see <a href="https://cef-builds.spotifycdn.com/docs/146.0/cef__dom_8h.html">cef_dom.h:208</a>
     */
    boolean isSame(@Nonnull CefDomNode that);

    /**
     * Returns the name of this node.
     *
     * <p>Definition generated from cef_dom_capi.h
     *
     * <pre>cef_string_userfree_t (CEF_CALLBACK* get_name)(struct _cef_domnode_t* self);</pre>
     *
     * @see <a href="https://cef-builds.spotifycdn.com/docs/146.0/cef__dom_8h.html">cef_dom.h:215</a>
     */
    Optional<String> getName();

    /**
     * Returns the value of this node.
     *
     * <p>Definition generated from cef_dom_capi.h
     *
     * <pre>cef_string_userfree_t (CEF_CALLBACK* get_value)(struct _cef_domnode_t* self);</pre>
     *
     * @see <a href="https://cef-builds.spotifycdn.com/docs/146.0/cef__dom_8h.html">cef_dom.h:221</a>
     */
    Optional<String> getValue();

    /**
     * Set the value of this node. Returns {@code true} on success.
     *
     * <p>Definition generated from cef_dom_capi.h
     *
     * <pre>int (CEF_CALLBACK* set_value)(struct _cef_domnode_t* self, const cef_string_t* value);</pre>
     *
     * @see <a href="https://cef-builds.spotifycdn.com/docs/146.0/cef__dom_8h.html">cef_dom.h:227</a>
     */
    boolean setValue(@Nonnull String value);

    /**
     * Returns the contents of this node as markup.
     *
     * <p>Definition generated from cef_dom_capi.h
     *
     * <pre>cef_string_userfree_t (CEF_CALLBACK* get_as_markup)(struct _cef_domnode_t* self);</pre>
     *
     * @see <a href="https://cef-builds.spotifycdn.com/docs/146.0/cef__dom_8h.html">cef_dom.h:233</a>
     */
    Optional<String> getAsMarkup();

    /**
     * Returns the document associated with this node.
     *
     * <p>Definition generated from cef_dom_capi.h
     *
     * <pre>cef_domdocument_t* (CEF_CALLBACK* get_document)(struct _cef_domnode_t* self);</pre>
     *
     * @see <a href="https://cef-builds.spotifycdn.com/docs/146.0/cef__dom_8h.html">cef_dom.h:239</a>
     */
    Optional<CefDomDocument> getDocument();

    /**
     * Returns the parent node.
     *
     * <p>Definition generated from cef_dom_capi.h
     *
     * <pre>cef_domnode_t* (CEF_CALLBACK* get_parent)(struct _cef_domnode_t* self);</pre>
     *
     * @see <a href="https://cef-builds.spotifycdn.com/docs/146.0/cef__dom_8h.html">cef_dom.h:245</a>
     */
    Optional<CefDomNode> getParent();

    /**
     * Returns the previous sibling node.
     *
     * <p>Definition generated from cef_dom_capi.h
     *
     * <pre>cef_domnode_t* (CEF_CALLBACK* get_previous_sibling)(struct _cef_domnode_t* self);</pre>
     *
     * @see <a href="https://cef-builds.spotifycdn.com/docs/146.0/cef__dom_8h.html">cef_dom.h:251</a>
     */
    Optional<CefDomNode> getPreviousSibling();

    /**
     * Returns the next sibling node.
     *
     * <p>Definition generated from cef_dom_capi.h
     *
     * <pre>cef_domnode_t* (CEF_CALLBACK* get_next_sibling)(struct _cef_domnode_t* self);</pre>
     *
     * @see <a href="https://cef-builds.spotifycdn.com/docs/146.0/cef__dom_8h.html">cef_dom.h:257</a>
     */
    Optional<CefDomNode> getNextSibling();

    /**
     * Returns {@code true} if this node has child nodes.
     *
     * <p>Definition generated from cef_dom_capi.h
     *
     * <pre>int (CEF_CALLBACK* has_children)(struct _cef_domnode_t* self);</pre>
     *
     * @see <a href="https://cef-builds.spotifycdn.com/docs/146.0/cef__dom_8h.html">cef_dom.h:263</a>
     */
    boolean hasChildren();

    /**
     * Return the first child node.
     *
     * <p>Definition generated from cef_dom_capi.h
     *
     * <pre>cef_domnode_t* (CEF_CALLBACK* get_first_child)(struct _cef_domnode_t* self);</pre>
     *
     * @see <a href="https://cef-builds.spotifycdn.com/docs/146.0/cef__dom_8h.html">cef_dom.h:269</a>
     */
    Optional<CefDomNode> getFirstChild();

    /**
     * Returns the last child node.
     *
     * <p>Definition generated from cef_dom_capi.h
     *
     * <pre>cef_domnode_t* (CEF_CALLBACK* get_last_child)(struct _cef_domnode_t* self);</pre>
     *
     * @see <a href="https://cef-builds.spotifycdn.com/docs/146.0/cef__dom_8h.html">cef_dom.h:275</a>
     */
    Optional<CefDomNode> getLastChild();

    /**
     * Returns the tag name of this element.
     *
     * <p>Definition generated from cef_dom_capi.h
     *
     * <pre>cef_string_userfree_t (CEF_CALLBACK* get_element_tag_name)(struct _cef_domnode_t* self);</pre>
     *
     * @see <a href="https://cef-builds.spotifycdn.com/docs/146.0/cef__dom_8h.html">cef_dom.h:283</a>
     */
    Optional<String> getElementTagName();

    /**
     * Returns {@code true} if this element has attributes.
     *
     * <p>Definition generated from cef_dom_capi.h
     *
     * <pre>int (CEF_CALLBACK* has_element_attributes)(struct _cef_domnode_t* self);</pre>
     *
     * @see <a href="https://cef-builds.spotifycdn.com/docs/146.0/cef__dom_8h.html">cef_dom.h:289</a>
     */
    boolean hasElementAttributes();

    /**
     * Returns {@code true} if this element has an attribute named {@code attrName}.
     *
     * <p>Definition generated from cef_dom_capi.h
     *
     * <pre>int (CEF_CALLBACK* has_element_attribute)(struct _cef_domnode_t* self, const cef_string_t* attrName);</pre>
     *
     * @see <a href="https://cef-builds.spotifycdn.com/docs/146.0/cef__dom_8h.html">cef_dom.h:295</a>
     */
    boolean hasElementAttribute(@Nonnull String attrname);

    /**
     * Returns the element attribute named {@code attrName}.
     *
     * <p>Definition generated from cef_dom_capi.h
     *
     * <pre>
     * cef_string_userfree_t (CEF_CALLBACK* get_element_attribute)(struct _cef_domnode_t* self, const cef_string_t* attrName);
     * </pre>
     *
     * @see <a href="https://cef-builds.spotifycdn.com/docs/146.0/cef__dom_8h.html">cef_dom.h:301</a>
     */
    Optional<String> getElementAttribute(@Nonnull String attrname);

    /**
     * Returns a map of all element attributes.
     *
     * <p>Definition generated from cef_dom_capi.h
     *
     * <pre>void (CEF_CALLBACK* get_element_attributes)(struct _cef_domnode_t* self, cef_string_map_t attrMap);</pre>
     *
     * @see <a href="https://cef-builds.spotifycdn.com/docs/146.0/cef__dom_8h.html">cef_dom.h:307</a>
     */
    void getElementAttributes(@Nonnull Map<String, String> attrmap);

    /**
     * Set the value for the element attribute named {@code attrName}. Returns {@code true} on success.
     *
     * <p>Definition generated from cef_dom_capi.h
     *
     * <pre>
     * int (CEF_CALLBACK* set_element_attribute)(struct _cef_domnode_t* self, const cef_string_t* attrName, const cef_string_t* value);
     * </pre>
     *
     * @see <a href="https://cef-builds.spotifycdn.com/docs/146.0/cef__dom_8h.html">cef_dom.h:313</a>
     */
    boolean setElementAttribute(@Nonnull String attrname, @Nonnull String value);

    /**
     * Returns the inner text of the element.
     *
     * <p>Definition generated from cef_dom_capi.h
     *
     * <pre>cef_string_userfree_t (CEF_CALLBACK* get_element_inner_text)(struct _cef_domnode_t* self);</pre>
     *
     * @see <a href="https://cef-builds.spotifycdn.com/docs/146.0/cef__dom_8h.html">cef_dom.h:321</a>
     */
    Optional<String> getElementInnerText();

    /**
     * Returns the bounds of the element in device pixels. Use "window.devicePixelRatio" to convert to/from CSS pixels.
     *
     * <p>Definition generated from cef_dom_capi.h
     *
     * <pre>cef_rect_t* (CEF_CALLBACK* get_element_bounds)(struct _cef_domnode_t* self);</pre>
     *
     * @see <a href="https://cef-builds.spotifycdn.com/docs/146.0/cef__dom_8h.html">cef_dom.h:327</a>
     */
    CefRect getElementBounds();

    final class NativePeer implements CefDomNode, AutoCloseable {
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

        private static final org.slf4j.Logger _log = org.slf4j.LoggerFactory.getLogger(CefDomNode.class);

        private static class Release implements Runnable {
            private final long ptr;

            Release(long ptr) {
                this.ptr = ptr;
            }

            @Override
            public void run() {
                if (_log.isTraceEnabled()) _log.trace("release CefDomNode 0x{}", Long.toHexString(ptr));
                N_Release(ptr);
            }
        }

        private static native void N_Release(long ptr);

        @Override
        public CefDomNodeType getType() {
            return N_GetType(nativePtr);
        }

        @Override
        public boolean isText() {
            return N_IsText(nativePtr);
        }

        @Override
        public boolean isElement() {
            return N_IsElement(nativePtr);
        }

        @Override
        public boolean isEditable() {
            return N_IsEditable(nativePtr);
        }

        @Override
        public boolean isFormControlElement() {
            return N_IsFormControlElement(nativePtr);
        }

        @Override
        public CefDomFormControlType getFormControlElementType() {
            return N_GetFormControlElementType(nativePtr);
        }

        @Override
        public boolean isSame(@Nonnull CefDomNode that) {
            return N_IsSame(nativePtr, that);
        }

        @Override
        public Optional<String> getName() {
            return Optional.ofNullable(N_GetName(nativePtr));
        }

        @Override
        public Optional<String> getValue() {
            return Optional.ofNullable(N_GetValue(nativePtr));
        }

        @Override
        public boolean setValue(@Nonnull String value) {
            return N_SetValue(nativePtr, value);
        }

        @Override
        public Optional<String> getAsMarkup() {
            return Optional.ofNullable(N_GetAsMarkup(nativePtr));
        }

        @Override
        public Optional<CefDomDocument> getDocument() {
            return Optional.ofNullable(N_GetDocument(nativePtr));
        }

        @Override
        public Optional<CefDomNode> getParent() {
            return Optional.ofNullable(N_GetParent(nativePtr));
        }

        @Override
        public Optional<CefDomNode> getPreviousSibling() {
            return Optional.ofNullable(N_GetPreviousSibling(nativePtr));
        }

        @Override
        public Optional<CefDomNode> getNextSibling() {
            return Optional.ofNullable(N_GetNextSibling(nativePtr));
        }

        @Override
        public boolean hasChildren() {
            return N_HasChildren(nativePtr);
        }

        @Override
        public Optional<CefDomNode> getFirstChild() {
            return Optional.ofNullable(N_GetFirstChild(nativePtr));
        }

        @Override
        public Optional<CefDomNode> getLastChild() {
            return Optional.ofNullable(N_GetLastChild(nativePtr));
        }

        @Override
        public Optional<String> getElementTagName() {
            return Optional.ofNullable(N_GetElementTagName(nativePtr));
        }

        @Override
        public boolean hasElementAttributes() {
            return N_HasElementAttributes(nativePtr);
        }

        @Override
        public boolean hasElementAttribute(@Nonnull String attrname) {
            return N_HasElementAttribute(nativePtr, attrname);
        }

        @Override
        public Optional<String> getElementAttribute(@Nonnull String attrname) {
            return Optional.ofNullable(N_GetElementAttribute(nativePtr, attrname));
        }

        @Override
        public void getElementAttributes(@Nonnull Map<String, String> attrmap) {
            N_GetElementAttributes(nativePtr, attrmap);
        }

        @Override
        public boolean setElementAttribute(@Nonnull String attrname, @Nonnull String value) {
            return N_SetElementAttribute(nativePtr, attrname, value);
        }

        @Override
        public Optional<String> getElementInnerText() {
            return Optional.ofNullable(N_GetElementInnerText(nativePtr));
        }

        @Override
        public CefRect getElementBounds() {
            return N_GetElementBounds(nativePtr);
        }

        private static native CefDomNodeType N_GetType(long self);

        private static native boolean N_IsText(long self);

        private static native boolean N_IsElement(long self);

        private static native boolean N_IsEditable(long self);

        private static native boolean N_IsFormControlElement(long self);

        private static native CefDomFormControlType N_GetFormControlElementType(long self);

        private static native boolean N_IsSame(long self, CefDomNode that);

        private static native String N_GetName(long self);

        private static native String N_GetValue(long self);

        private static native boolean N_SetValue(long self, String value);

        private static native String N_GetAsMarkup(long self);

        private static native CefDomDocument N_GetDocument(long self);

        private static native CefDomNode N_GetParent(long self);

        private static native CefDomNode N_GetPreviousSibling(long self);

        private static native CefDomNode N_GetNextSibling(long self);

        private static native boolean N_HasChildren(long self);

        private static native CefDomNode N_GetFirstChild(long self);

        private static native CefDomNode N_GetLastChild(long self);

        private static native String N_GetElementTagName(long self);

        private static native boolean N_HasElementAttributes(long self);

        private static native boolean N_HasElementAttribute(long self, String attrname);

        private static native String N_GetElementAttribute(long self, String attrname);

        private static native void N_GetElementAttributes(long self, Map<String, String> attrmap);

        private static native boolean N_SetElementAttribute(long self, String attrname, String value);

        private static native String N_GetElementInnerText(long self);

        private static native CefRect N_GetElementBounds(long self);

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
