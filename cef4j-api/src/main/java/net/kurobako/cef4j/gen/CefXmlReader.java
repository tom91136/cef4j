// GENERATED - do not edit. Regenerate via: mvn generate-sources -pl cef4j-native
package net.kurobako.cef4j.gen;

import java.util.Optional;
import javax.annotation.Nonnull;

/**
 * Class that supports the reading of XML data via the libxml streaming API. The methods of this class should only be
 * called on the thread that creates the object.
 */
public interface CefXmlReader {

    /**
     * Moves the cursor to the next node in the document. This method must be called at least once to set the current
     * cursor position. Returns true if the cursor position was set successfully.
     */
    boolean moveToNextNode();

    /** Close the document. This should be called directly to ensure that cleanup occurs on the correct thread. */
    boolean close();

    /** Returns true if an error has been reported by the XML parser. */
    boolean hasError();

    /** Returns the error string. */
    Optional<String> getError();

    /**
     * Returns the item type for the specified |command_id|.
     *
     * @return the result, or {@code MENUITEMTYPE_NONE} for default handling
     */
    CefXmlNodeType getType();

    /** Returns the node depth. Depth starts at 0 for the root node. */
    int getDepth();

    /** Returns the local name. See http://www.w3.org/TR/REC-xml-names/#NT-LocalPart for additional details. */
    Optional<String> getLocalName();

    /** Returns the namespace prefix. See http://www.w3.org/TR/REC-xml-names/ for additional details. */
    Optional<String> getPrefix();

    /**
     * Returns the qualified name, equal to (Prefix:)LocalName. See http://www.w3.org/TR/REC-xml-names/#ns-qualnames for
     * additional details.
     */
    Optional<String> getQualifiedName();

    Optional<String> getNamespaceUri();

    Optional<String> getBaseUri();

    /**
     * Returns the xml:lang scope within which the node resides. See http://www.w3.org/TR/REC-xml/#sec-lang-tag for
     * additional details.
     */
    Optional<String> getXmlLang();

    /** Returns true if the node represents an empty element. "<a/>" is considered empty but "<a></a>" is not. */
    boolean isEmptyElement();

    /** Returns true if the object has a value with the specified identifier. */
    boolean hasValue();

    /** Returns the value of this node. */
    Optional<String> getValue();

    /** Returns true if the node has attributes. */
    boolean hasAttributes();

    /** Returns the number of attributes. */
    long getAttributeCount();

    /**
     * Returns the value of the attribute at the specified 0-based index.
     *
     * @param index zero-based index
     */
    Optional<String> getAttributeByindex(int index);

    /** Returns the value of the attribute with the specified qualified name. */
    Optional<String> getAttributeByqname(@Nonnull String qualifiedName);

    /** Returns the value of the attribute with the specified local name and namespace URI. */
    Optional<String> getAttributeBylname(@Nonnull String localName, @Nonnull String namespaceURI);

    /** Returns an XML representation of the current node's children. */
    Optional<String> getInnerXml();

    /** Returns an XML representation of the current node including its children. */
    Optional<String> getOuterXml();

    /** Returns the 1-based line number for the function call or 0 if unknown. */
    int getLineNumber();

    /**
     * Moves the cursor to the attribute at the specified 0-based index. Returns true if the cursor position was set
     * successfully.
     *
     * @param index zero-based index
     */
    int moveToAttributeByindex(int index);

    /**
     * Moves the cursor to the attribute with the specified qualified name. Returns true if the cursor position was set
     * successfully.
     */
    int moveToAttributeByqname(@Nonnull String qualifiedName);

    /**
     * Moves the cursor to the attribute with the specified local name and namespace URI. Returns true if the cursor
     * position was set successfully.
     */
    int moveToAttributeBylname(@Nonnull String localName, @Nonnull String namespaceURI);

    /**
     * Moves the cursor to the first attribute in the current element. Returns true if the cursor position was set
     * successfully.
     */
    boolean moveToFirstAttribute();

    /**
     * Moves the cursor to the next attribute in the current element. Returns true if the cursor position was set
     * successfully.
     */
    boolean moveToNextAttribute();

    /** Moves the cursor back to the carrying element. Returns true if the cursor position was set successfully. */
    boolean moveToCarryingElement();

    static class NativePeer implements CefXmlReader {
        private volatile long nativePtr;

        @Override
        public boolean moveToNextNode() {
            return N_MoveToNextNode(nativePtr);
        }

        @Override
        public boolean close() {
            return N_Close(nativePtr);
        }

        @Override
        public boolean hasError() {
            return N_HasError(nativePtr);
        }

        @Override
        public Optional<String> getError() {
            return Optional.ofNullable(N_GetError(nativePtr));
        }

        @Override
        public CefXmlNodeType getType() {
            return N_GetType(nativePtr);
        }

        @Override
        public int getDepth() {
            return N_GetDepth(nativePtr);
        }

        @Override
        public Optional<String> getLocalName() {
            return Optional.ofNullable(N_GetLocalName(nativePtr));
        }

        @Override
        public Optional<String> getPrefix() {
            return Optional.ofNullable(N_GetPrefix(nativePtr));
        }

        @Override
        public Optional<String> getQualifiedName() {
            return Optional.ofNullable(N_GetQualifiedName(nativePtr));
        }

        @Override
        public Optional<String> getNamespaceUri() {
            return Optional.ofNullable(N_GetNamespaceUri(nativePtr));
        }

        @Override
        public Optional<String> getBaseUri() {
            return Optional.ofNullable(N_GetBaseUri(nativePtr));
        }

        @Override
        public Optional<String> getXmlLang() {
            return Optional.ofNullable(N_GetXmlLang(nativePtr));
        }

        @Override
        public boolean isEmptyElement() {
            return N_IsEmptyElement(nativePtr);
        }

        @Override
        public boolean hasValue() {
            return N_HasValue(nativePtr);
        }

        @Override
        public Optional<String> getValue() {
            return Optional.ofNullable(N_GetValue(nativePtr));
        }

        @Override
        public boolean hasAttributes() {
            return N_HasAttributes(nativePtr);
        }

        @Override
        public long getAttributeCount() {
            return N_GetAttributeCount(nativePtr);
        }

        @Override
        public Optional<String> getAttributeByindex(int index) {
            return Optional.ofNullable(N_GetAttributeByindex(nativePtr, index));
        }

        @Override
        public Optional<String> getAttributeByqname(String qualifiedName) {
            return Optional.ofNullable(N_GetAttributeByqname(nativePtr, qualifiedName));
        }

        @Override
        public Optional<String> getAttributeBylname(String localName, String namespaceURI) {
            return Optional.ofNullable(N_GetAttributeBylname(nativePtr, localName, namespaceURI));
        }

        @Override
        public Optional<String> getInnerXml() {
            return Optional.ofNullable(N_GetInnerXml(nativePtr));
        }

        @Override
        public Optional<String> getOuterXml() {
            return Optional.ofNullable(N_GetOuterXml(nativePtr));
        }

        @Override
        public int getLineNumber() {
            return N_GetLineNumber(nativePtr);
        }

        @Override
        public int moveToAttributeByindex(int index) {
            return N_MoveToAttributeByindex(nativePtr, index);
        }

        @Override
        public int moveToAttributeByqname(String qualifiedName) {
            return N_MoveToAttributeByqname(nativePtr, qualifiedName);
        }

        @Override
        public int moveToAttributeBylname(String localName, String namespaceURI) {
            return N_MoveToAttributeBylname(nativePtr, localName, namespaceURI);
        }

        @Override
        public boolean moveToFirstAttribute() {
            return N_MoveToFirstAttribute(nativePtr);
        }

        @Override
        public boolean moveToNextAttribute() {
            return N_MoveToNextAttribute(nativePtr);
        }

        @Override
        public boolean moveToCarryingElement() {
            return N_MoveToCarryingElement(nativePtr);
        }

        private native boolean N_MoveToNextNode(long self);

        private native boolean N_Close(long self);

        private native boolean N_HasError(long self);

        private native String N_GetError(long self);

        private native CefXmlNodeType N_GetType(long self);

        private native int N_GetDepth(long self);

        private native String N_GetLocalName(long self);

        private native String N_GetPrefix(long self);

        private native String N_GetQualifiedName(long self);

        private native String N_GetNamespaceUri(long self);

        private native String N_GetBaseUri(long self);

        private native String N_GetXmlLang(long self);

        private native boolean N_IsEmptyElement(long self);

        private native boolean N_HasValue(long self);

        private native String N_GetValue(long self);

        private native boolean N_HasAttributes(long self);

        private native long N_GetAttributeCount(long self);

        private native String N_GetAttributeByindex(long self, int index);

        private native String N_GetAttributeByqname(long self, String qualifiedName);

        private native String N_GetAttributeBylname(long self, String localName, String namespaceURI);

        private native String N_GetInnerXml(long self);

        private native String N_GetOuterXml(long self);

        private native int N_GetLineNumber(long self);

        private native int N_MoveToAttributeByindex(long self, int index);

        private native int N_MoveToAttributeByqname(long self, String qualifiedName);

        private native int N_MoveToAttributeBylname(long self, String localName, String namespaceURI);

        private native boolean N_MoveToFirstAttribute(long self);

        private native boolean N_MoveToNextAttribute(long self);

        private native boolean N_MoveToCarryingElement(long self);

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
            return "CefXmlReader{0x" + Long.toHexString(nativePtr) + "}";
        }
    }
}
