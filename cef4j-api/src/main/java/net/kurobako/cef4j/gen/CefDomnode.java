// GENERATED - do not edit. Regenerate via: mvn generate-sources -pl cef4j-native
package net.kurobako.cef4j.gen;

import java.util.Optional;
import javax.annotation.Nonnull;

/**
 * Class used to represent a DOM node. The methods of this class should only be called on the render process main
 * thread.
 */
public interface CefDomnode {

    /**
     * Returns the item type for the specified |command_id|.
     *
     * @return the result, or {@code MENUITEMTYPE_NONE} for default handling
     */
    CefDomNodeType getType();

    /** Returns true if this is a text node. */
    boolean isText();

    /** Returns true if this is an element node. */
    boolean isElement();

    /** Returns true if this is an editable node. */
    boolean isEditable();

    /**
     * Returns true if this is a form control element node.
     *
     * @return the result, or {@code DOM_NODE_TYPE_UNSUPPORTED} for default handling
     */
    boolean isFormControlElement();

    /**
     * Returns the type of this form control element node.
     *
     * @return the result, or {@code DOM_FORM_CONTROL_TYPE_UNSUPPORTED} for default handling
     */
    CefDomFormControlType getFormControlElementType();

    /** Returns true if this object is pointing to the same handle as |that| object. */
    boolean isSame(long that);

    /** Returns the name of this node. */
    Optional<String> getName();

    /** Returns the value of this node. */
    Optional<String> getValue();

    /** Set the value of this node. Returns true on success. */
    boolean setValue(@Nonnull String value);

    /** Returns the contents of this node as markup. */
    Optional<String> getAsMarkup();

    /** Returns the document associated with this node. */
    long getDocument();

    /** Returns the parent node. */
    long getParent();

    /** Returns the previous sibling node. */
    long getPreviousSibling();

    /** Returns the next sibling node. */
    long getNextSibling();

    /** Returns true if this node has child nodes. */
    boolean hasChildren();

    /** Return the first child node. */
    long getFirstChild();

    /** Returns the last child node. */
    long getLastChild();

    /** Returns the tag name of this element. */
    Optional<String> getElementTagName();

    /** Returns true if this element has attributes. */
    boolean hasElementAttributes();

    /** Returns true if this element has an attribute named |attrName|. */
    boolean hasElementAttribute(@Nonnull String attrName);

    /** Returns the element attribute named |attrName|. */
    Optional<String> getElementAttribute(@Nonnull String attrName);

    /** Returns a map of all element attributes. */
    void getElementAttributes(@Nonnull java.util.Map<String, String> attrMap);

    /** Set the value for the element attribute named |attrName|. Returns true on success. */
    boolean setElementAttribute(@Nonnull String attrName, @Nonnull String value);

    /** Returns the inner text of the element. */
    Optional<String> getElementInnerText();

    /**
     * Returns the bounds of the element in device pixels. Use "window.devicePixelRatio" to convert to/from CSS pixels.
     */
    CefRect getElementBounds();

    static class NativePeer implements CefDomnode {
        private volatile long nativePtr;

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
        public boolean isSame(long that) {
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
        public boolean setValue(String value) {
            return N_SetValue(nativePtr, value);
        }

        @Override
        public Optional<String> getAsMarkup() {
            return Optional.ofNullable(N_GetAsMarkup(nativePtr));
        }

        @Override
        public long getDocument() {
            return N_GetDocument(nativePtr);
        }

        @Override
        public long getParent() {
            return N_GetParent(nativePtr);
        }

        @Override
        public long getPreviousSibling() {
            return N_GetPreviousSibling(nativePtr);
        }

        @Override
        public long getNextSibling() {
            return N_GetNextSibling(nativePtr);
        }

        @Override
        public boolean hasChildren() {
            return N_HasChildren(nativePtr);
        }

        @Override
        public long getFirstChild() {
            return N_GetFirstChild(nativePtr);
        }

        @Override
        public long getLastChild() {
            return N_GetLastChild(nativePtr);
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
        public boolean hasElementAttribute(String attrName) {
            return N_HasElementAttribute(nativePtr, attrName);
        }

        @Override
        public Optional<String> getElementAttribute(String attrName) {
            return Optional.ofNullable(N_GetElementAttribute(nativePtr, attrName));
        }

        @Override
        public void getElementAttributes(java.util.Map<String, String> attrMap) {
            N_GetElementAttributes(nativePtr, attrMap);
        }

        @Override
        public boolean setElementAttribute(String attrName, String value) {
            return N_SetElementAttribute(nativePtr, attrName, value);
        }

        @Override
        public Optional<String> getElementInnerText() {
            return Optional.ofNullable(N_GetElementInnerText(nativePtr));
        }

        @Override
        public CefRect getElementBounds() {
            return N_GetElementBounds(nativePtr);
        }

        private native CefDomNodeType N_GetType(long self);

        private native boolean N_IsText(long self);

        private native boolean N_IsElement(long self);

        private native boolean N_IsEditable(long self);

        private native boolean N_IsFormControlElement(long self);

        private native CefDomFormControlType N_GetFormControlElementType(long self);

        private native boolean N_IsSame(long self, long that);

        private native String N_GetName(long self);

        private native String N_GetValue(long self);

        private native boolean N_SetValue(long self, String value);

        private native String N_GetAsMarkup(long self);

        private native long N_GetDocument(long self);

        private native long N_GetParent(long self);

        private native long N_GetPreviousSibling(long self);

        private native long N_GetNextSibling(long self);

        private native boolean N_HasChildren(long self);

        private native long N_GetFirstChild(long self);

        private native long N_GetLastChild(long self);

        private native String N_GetElementTagName(long self);

        private native boolean N_HasElementAttributes(long self);

        private native boolean N_HasElementAttribute(long self, String attrName);

        private native String N_GetElementAttribute(long self, String attrName);

        private native void N_GetElementAttributes(long self, java.util.Map<String, String> attrMap);

        private native boolean N_SetElementAttribute(long self, String attrName, String value);

        private native String N_GetElementInnerText(long self);

        private native CefRect N_GetElementBounds(long self);

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
            return "CefDomnode{0x" + Long.toHexString(nativePtr) + "}";
        }
    }
}
