// GENERATED - do not edit. Regenerate via: mvn generate-sources -pl cef4j-native
package net.kurobako.cef4j.gen;

import java.util.Optional;
import javax.annotation.Nonnull;
import javax.annotation.Nullable;

/**
 * Class that supports the reading of XML data via the libxml streaming API. The methods of this class should only be
 * called on the thread that creates the object.
 *
 * <p>Definition generated from cef_xml_reader_capi.h
 *
 * <pre>typedef struct _cef_xml_reader_t {
 *   cef_base_ref_counted_t base;
 *   ...
 * } cef_xml_reader_t;</pre>
 *
 * @see <a href="https://cef-builds.spotifycdn.com/docs/146.0/cef__xml__reader_8h.html">cef_xml_reader.h:44</a>
 */
public interface CefXmlReader extends CefLibraryObject {

    /**
     * Moves the cursor to the next node in the document. This method must be called at least once to set the current
     * cursor position. Returns {@code true} if the cursor position was set successfully.
     *
     * <p>Definition generated from cef_xml_reader_capi.h
     *
     * <pre>int (CEF_CALLBACK* move_to_next_node)(struct _cef_xml_reader_t* self);</pre>
     *
     * @see <a href="https://cef-builds.spotifycdn.com/docs/146.0/cef__xml__reader_8h.html">cef_xml_reader.h:64</a>
     */
    boolean moveToNextNode();

    /**
     * Close the document. This should be called directly to ensure that cleanup occurs on the correct thread.
     *
     * <p>Definition generated from cef_xml_reader_capi.h
     *
     * <pre>int (CEF_CALLBACK* close)(struct _cef_xml_reader_t* self);</pre>
     *
     * @see <a href="https://cef-builds.spotifycdn.com/docs/146.0/cef__xml__reader_8h.html">cef_xml_reader.h:72</a>
     */
    boolean cefClose();

    /**
     * Returns {@code true} if an error has been reported by the XML parser.
     *
     * <p>Definition generated from cef_xml_reader_capi.h
     *
     * <pre>int (CEF_CALLBACK* has_error)(struct _cef_xml_reader_t* self);</pre>
     *
     * @see <a href="https://cef-builds.spotifycdn.com/docs/146.0/cef__xml__reader_8h.html">cef_xml_reader.h:79</a>
     */
    boolean hasError();

    /**
     * Returns the error string.
     *
     * <p>Definition generated from cef_xml_reader_capi.h
     *
     * <pre>cef_string_userfree_t (CEF_CALLBACK* get_error)(struct _cef_xml_reader_t* self);</pre>
     *
     * @see <a href="https://cef-builds.spotifycdn.com/docs/146.0/cef__xml__reader_8h.html">cef_xml_reader.h:85</a>
     */
    Optional<String> getError();

    /**
     * Returns the node type.
     *
     * <p>Definition generated from cef_xml_reader_capi.h
     *
     * <pre>cef_xml_node_type_t (CEF_CALLBACK* get_type)(struct _cef_xml_reader_t* self);</pre>
     *
     * @return the result, or {@code MENUITEMTYPE_NONE} for default handling
     * @see <a href="https://cef-builds.spotifycdn.com/docs/146.0/cef__xml__reader_8h.html">cef_xml_reader.h:94</a>
     */
    CefXmlNodeType getType();

    /**
     * Returns the node depth. Depth starts at 0 for the root node.
     *
     * <p>Definition generated from cef_xml_reader_capi.h
     *
     * <pre>int (CEF_CALLBACK* get_depth)(struct _cef_xml_reader_t* self);</pre>
     *
     * @see <a href="https://cef-builds.spotifycdn.com/docs/146.0/cef__xml__reader_8h.html">cef_xml_reader.h:100</a>
     */
    int getDepth();

    /**
     * Returns the local name. See <a
     * href="http://www.w3.org/TR/REC-xml-names/#NT-LocalPart">http://www.w3.org/TR/REC-xml-names/#NT-LocalPart</a> for
     * additional details.
     *
     * <p>Definition generated from cef_xml_reader_capi.h
     *
     * <pre>cef_string_userfree_t (CEF_CALLBACK* get_local_name)(struct _cef_xml_reader_t* self);</pre>
     *
     * @see <a href="https://cef-builds.spotifycdn.com/docs/146.0/cef__xml__reader_8h.html">cef_xml_reader.h:106</a>
     */
    Optional<String> getLocalName();

    /**
     * Returns the namespace prefix. See <a
     * href="http://www.w3.org/TR/REC-xml-names/">http://www.w3.org/TR/REC-xml-names/</a> for additional details.
     *
     * <p>Definition generated from cef_xml_reader_capi.h
     *
     * <pre>cef_string_userfree_t (CEF_CALLBACK* get_prefix)(struct _cef_xml_reader_t* self);</pre>
     *
     * @see <a href="https://cef-builds.spotifycdn.com/docs/146.0/cef__xml__reader_8h.html">cef_xml_reader.h:113</a>
     */
    Optional<String> getPrefix();

    /**
     * Returns the qualified name, equal to (Prefix:)LocalName. See <a
     * href="http://www.w3.org/TR/REC-xml-names/#ns-qualnames">http://www.w3.org/TR/REC-xml-names/#ns-qualnames</a> for
     * additional details.
     *
     * <p>Definition generated from cef_xml_reader_capi.h
     *
     * <pre>cef_string_userfree_t (CEF_CALLBACK* get_qualified_name)(struct _cef_xml_reader_t* self);</pre>
     *
     * @see <a href="https://cef-builds.spotifycdn.com/docs/146.0/cef__xml__reader_8h.html">cef_xml_reader.h:120</a>
     */
    Optional<String> getQualifiedName();

    /**
     * Returns the URI defining the namespace associated with the node. See <a
     * href="http://www.w3.org/TR/REC-xml-names/">http://www.w3.org/TR/REC-xml-names/</a> for additional details.
     *
     * <p>Definition generated from cef_xml_reader_capi.h
     *
     * <pre>cef_string_userfree_t (CEF_CALLBACK* get_namespace_uri)(struct _cef_xml_reader_t* self);</pre>
     *
     * @see <a href="https://cef-builds.spotifycdn.com/docs/146.0/cef__xml__reader_8h.html">cef_xml_reader.h:127</a>
     */
    Optional<String> getNamespaceUri();

    /**
     * Returns the base URI of the node. See <a href="http://www.w3.org/TR/xmlbase/">http://www.w3.org/TR/xmlbase/</a>
     * for additional details.
     *
     * <p>Definition generated from cef_xml_reader_capi.h
     *
     * <pre>cef_string_userfree_t (CEF_CALLBACK* get_base_uri)(struct _cef_xml_reader_t* self);</pre>
     *
     * @see <a href="https://cef-builds.spotifycdn.com/docs/146.0/cef__xml__reader_8h.html">cef_xml_reader.h:134</a>
     */
    Optional<String> getBaseUri();

    /**
     * Returns the xml:lang scope within which the node resides. See <a
     * href="http://www.w3.org/TR/REC-xml/#sec-lang-tag">http://www.w3.org/TR/REC-xml/#sec-lang-tag</a> for additional
     * details.
     *
     * <p>Definition generated from cef_xml_reader_capi.h
     *
     * <pre>cef_string_userfree_t (CEF_CALLBACK* get_xml_lang)(struct _cef_xml_reader_t* self);</pre>
     *
     * @see <a href="https://cef-builds.spotifycdn.com/docs/146.0/cef__xml__reader_8h.html">cef_xml_reader.h:141</a>
     */
    Optional<String> getXmlLang();

    /**
     * Returns {@code true} if the node represents an empty element. "&lt;a/&gt;" is considered empty but
     * "&lt;a&gt;&lt;/a&gt;" is not.
     *
     * <p>Definition generated from cef_xml_reader_capi.h
     *
     * <pre>int (CEF_CALLBACK* is_empty_element)(struct _cef_xml_reader_t* self);</pre>
     *
     * @see <a href="https://cef-builds.spotifycdn.com/docs/146.0/cef__xml__reader_8h.html">cef_xml_reader.h:148</a>
     */
    boolean isEmptyElement();

    /**
     * Returns {@code true} if the node has a text value.
     *
     * <p>Definition generated from cef_xml_reader_capi.h
     *
     * <pre>int (CEF_CALLBACK* has_value)(struct _cef_xml_reader_t* self);</pre>
     *
     * @see <a href="https://cef-builds.spotifycdn.com/docs/146.0/cef__xml__reader_8h.html">cef_xml_reader.h:155</a>
     */
    boolean hasValue();

    /**
     * Returns the text value.
     *
     * <p>Definition generated from cef_xml_reader_capi.h
     *
     * <pre>cef_string_userfree_t (CEF_CALLBACK* get_value)(struct _cef_xml_reader_t* self);</pre>
     *
     * @see <a href="https://cef-builds.spotifycdn.com/docs/146.0/cef__xml__reader_8h.html">cef_xml_reader.h:161</a>
     */
    Optional<String> getValue();

    /**
     * Returns {@code true} if the node has attributes.
     *
     * <p>Definition generated from cef_xml_reader_capi.h
     *
     * <pre>int (CEF_CALLBACK* has_attributes)(struct _cef_xml_reader_t* self);</pre>
     *
     * @see <a href="https://cef-builds.spotifycdn.com/docs/146.0/cef__xml__reader_8h.html">cef_xml_reader.h:167</a>
     */
    boolean hasAttributes();

    /**
     * Returns the number of attributes.
     *
     * <p>Definition generated from cef_xml_reader_capi.h
     *
     * <pre>size_t (CEF_CALLBACK* get_attribute_count)(struct _cef_xml_reader_t* self);</pre>
     *
     * @see <a href="https://cef-builds.spotifycdn.com/docs/146.0/cef__xml__reader_8h.html">cef_xml_reader.h:173</a>
     */
    long getAttributeCount();

    /**
     * Returns the value of the attribute at the specified 0-based index.
     *
     * <p>Definition generated from cef_xml_reader_capi.h
     *
     * <pre>cef_string_userfree_t (CEF_CALLBACK* get_attribute_byindex)(struct _cef_xml_reader_t* self, int index);
     * </pre>
     *
     * @param index zero-based index
     * @see <a href="https://cef-builds.spotifycdn.com/docs/146.0/cef__xml__reader_8h.html">cef_xml_reader.h:179</a>
     */
    Optional<String> getAttributeByindex(int index);

    /**
     * Returns the value of the attribute with the specified qualified name.
     *
     * <p>Definition generated from cef_xml_reader_capi.h
     *
     * <pre>
     * cef_string_userfree_t (CEF_CALLBACK* get_attribute_byqname)(struct _cef_xml_reader_t* self, const cef_string_t* qualifiedName);
     * </pre>
     *
     * @see <a href="https://cef-builds.spotifycdn.com/docs/146.0/cef__xml__reader_8h.html">cef_xml_reader.h:185</a>
     */
    Optional<String> getAttributeByqname(@Nullable String qualifiedName);

    /**
     * Returns the value of the attribute with the specified local name and namespace URI.
     *
     * <p>Definition generated from cef_xml_reader_capi.h
     *
     * <pre>
     * cef_string_userfree_t (CEF_CALLBACK* get_attribute_bylname)(struct _cef_xml_reader_t* self, const cef_string_t* localName, const cef_string_t* namespaceURI);
     * </pre>
     *
     * @see <a href="https://cef-builds.spotifycdn.com/docs/146.0/cef__xml__reader_8h.html">cef_xml_reader.h:191</a>
     */
    Optional<String> getAttributeBylname(@Nullable String localName, @Nullable String namespaceURI);

    /**
     * Returns an XML representation of the current node's children.
     *
     * <p>Definition generated from cef_xml_reader_capi.h
     *
     * <pre>cef_string_userfree_t (CEF_CALLBACK* get_inner_xml)(struct _cef_xml_reader_t* self);</pre>
     *
     * @see <a href="https://cef-builds.spotifycdn.com/docs/146.0/cef__xml__reader_8h.html">cef_xml_reader.h:199</a>
     */
    Optional<String> getInnerXml();

    /**
     * Returns an XML representation of the current node including its children.
     *
     * <p>Definition generated from cef_xml_reader_capi.h
     *
     * <pre>cef_string_userfree_t (CEF_CALLBACK* get_outer_xml)(struct _cef_xml_reader_t* self);</pre>
     *
     * @see <a href="https://cef-builds.spotifycdn.com/docs/146.0/cef__xml__reader_8h.html">cef_xml_reader.h:205</a>
     */
    Optional<String> getOuterXml();

    /**
     * Returns the line number for the current node.
     *
     * <p>Definition generated from cef_xml_reader_capi.h
     *
     * <pre>int (CEF_CALLBACK* get_line_number)(struct _cef_xml_reader_t* self);</pre>
     *
     * @see <a href="https://cef-builds.spotifycdn.com/docs/146.0/cef__xml__reader_8h.html">cef_xml_reader.h:211</a>
     */
    int getLineNumber();

    /**
     * Moves the cursor to the attribute at the specified 0-based index. Returns {@code true} if the cursor position was
     * set successfully.
     *
     * <p>Definition generated from cef_xml_reader_capi.h
     *
     * <pre>int (CEF_CALLBACK* move_to_attribute_byindex)(struct _cef_xml_reader_t* self, int index);</pre>
     *
     * @param index zero-based index
     * @see <a href="https://cef-builds.spotifycdn.com/docs/146.0/cef__xml__reader_8h.html">cef_xml_reader.h:222</a>
     */
    int moveToAttributeByindex(int index);

    /**
     * Moves the cursor to the attribute with the specified qualified name. Returns {@code true} if the cursor position
     * was set successfully.
     *
     * <p>Definition generated from cef_xml_reader_capi.h
     *
     * <pre>
     * int (CEF_CALLBACK* move_to_attribute_byqname)(struct _cef_xml_reader_t* self, const cef_string_t* qualifiedName);
     * </pre>
     *
     * @see <a href="https://cef-builds.spotifycdn.com/docs/146.0/cef__xml__reader_8h.html">cef_xml_reader.h:229</a>
     */
    int moveToAttributeByqname(@Nullable String qualifiedName);

    /**
     * Moves the cursor to the attribute with the specified local name and namespace URI. Returns {@code true} if the
     * cursor position was set successfully.
     *
     * <p>Definition generated from cef_xml_reader_capi.h
     *
     * <pre>
     * int (CEF_CALLBACK* move_to_attribute_bylname)(struct _cef_xml_reader_t* self, const cef_string_t* localName, const cef_string_t* namespaceURI);
     * </pre>
     *
     * @see <a href="https://cef-builds.spotifycdn.com/docs/146.0/cef__xml__reader_8h.html">cef_xml_reader.h:236</a>
     */
    int moveToAttributeBylname(@Nullable String localName, @Nullable String namespaceURI);

    /**
     * Moves the cursor to the first attribute in the current element. Returns {@code true} if the cursor position was
     * set successfully.
     *
     * <p>Definition generated from cef_xml_reader_capi.h
     *
     * <pre>int (CEF_CALLBACK* move_to_first_attribute)(struct _cef_xml_reader_t* self);</pre>
     *
     * @see <a href="https://cef-builds.spotifycdn.com/docs/146.0/cef__xml__reader_8h.html">cef_xml_reader.h:244</a>
     */
    boolean moveToFirstAttribute();

    /**
     * Moves the cursor to the next attribute in the current element. Returns {@code true} if the cursor position was
     * set successfully.
     *
     * <p>Definition generated from cef_xml_reader_capi.h
     *
     * <pre>int (CEF_CALLBACK* move_to_next_attribute)(struct _cef_xml_reader_t* self);</pre>
     *
     * @see <a href="https://cef-builds.spotifycdn.com/docs/146.0/cef__xml__reader_8h.html">cef_xml_reader.h:251</a>
     */
    boolean moveToNextAttribute();

    /**
     * Moves the cursor back to the carrying element. Returns {@code true} if the cursor position was set successfully.
     *
     * <p>Definition generated from cef_xml_reader_capi.h
     *
     * <pre>int (CEF_CALLBACK* move_to_carrying_element)(struct _cef_xml_reader_t* self);</pre>
     *
     * @see <a href="https://cef-builds.spotifycdn.com/docs/146.0/cef__xml__reader_8h.html">cef_xml_reader.h:258</a>
     */
    boolean moveToCarryingElement();
    /**
     * Create a new backing store with allocated memory of {@code byte_length} bytes. The memory is uninitialized. This
     * method must be called on a thread with a valid V8 isolate. The returned object can safely be passed to other
     * threads. Returns {@code null} on failure.
     *
     * <p>Definition generated from cef_xml_reader_capi.h
     *
     * <pre>
     * CEF_EXPORT cef_xml_reader_t* cef_xml_reader_create(struct _cef_stream_reader_t* stream, cef_xml_encoding_type_t encodingType, const cef_string_t* URI);
     * </pre>
     *
     * @see <a href="https://cef-builds.spotifycdn.com/docs/146.0/cef__v8_8h.html">cef_v8.h:445</a>
     */
    static Optional<CefXmlReader> create(
            @Nullable CefStreamReader stream, @Nonnull CefXmlEncodingType encodingType, @Nullable String uRI) {
        return Optional.ofNullable(NativePeer.N_Create(stream, encodingType, uRI));
    }

    final class NativePeer implements CefXmlReader, AutoCloseable {
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
            if (closed) throw new IllegalStateException("CefXmlReader has been closed");
        }

        private static final org.slf4j.Logger _log = org.slf4j.LoggerFactory.getLogger(CefXmlReader.class);

        private static class Release implements Runnable {
            private final long ptr;

            Release(long ptr) {
                this.ptr = ptr;
            }

            @Override
            public void run() {
                if (_log.isTraceEnabled()) _log.trace("release CefXmlReader 0x{}", Long.toHexString(ptr));
                N_Release(ptr);
            }
        }

        private static native void N_Release(long ptr);

        @Override
        public boolean moveToNextNode() {
            checkNotClosed();
            return N_MoveToNextNode(nativePtr);
        }

        @Override
        public boolean cefClose() {
            checkNotClosed();
            return N_Close(nativePtr);
        }

        @Override
        public boolean hasError() {
            checkNotClosed();
            return N_HasError(nativePtr);
        }

        @Override
        public Optional<String> getError() {
            checkNotClosed();
            return Optional.ofNullable(N_GetError(nativePtr));
        }

        @Override
        public CefXmlNodeType getType() {
            checkNotClosed();
            return N_GetType(nativePtr);
        }

        @Override
        public int getDepth() {
            checkNotClosed();
            return N_GetDepth(nativePtr);
        }

        @Override
        public Optional<String> getLocalName() {
            checkNotClosed();
            return Optional.ofNullable(N_GetLocalName(nativePtr));
        }

        @Override
        public Optional<String> getPrefix() {
            checkNotClosed();
            return Optional.ofNullable(N_GetPrefix(nativePtr));
        }

        @Override
        public Optional<String> getQualifiedName() {
            checkNotClosed();
            return Optional.ofNullable(N_GetQualifiedName(nativePtr));
        }

        @Override
        public Optional<String> getNamespaceUri() {
            checkNotClosed();
            return Optional.ofNullable(N_GetNamespaceUri(nativePtr));
        }

        @Override
        public Optional<String> getBaseUri() {
            checkNotClosed();
            return Optional.ofNullable(N_GetBaseUri(nativePtr));
        }

        @Override
        public Optional<String> getXmlLang() {
            checkNotClosed();
            return Optional.ofNullable(N_GetXmlLang(nativePtr));
        }

        @Override
        public boolean isEmptyElement() {
            checkNotClosed();
            return N_IsEmptyElement(nativePtr);
        }

        @Override
        public boolean hasValue() {
            checkNotClosed();
            return N_HasValue(nativePtr);
        }

        @Override
        public Optional<String> getValue() {
            checkNotClosed();
            return Optional.ofNullable(N_GetValue(nativePtr));
        }

        @Override
        public boolean hasAttributes() {
            checkNotClosed();
            return N_HasAttributes(nativePtr);
        }

        @Override
        public long getAttributeCount() {
            checkNotClosed();
            return N_GetAttributeCount(nativePtr);
        }

        @Override
        public Optional<String> getAttributeByindex(int index) {
            checkNotClosed();
            return Optional.ofNullable(N_GetAttributeByindex(nativePtr, index));
        }

        @Override
        public Optional<String> getAttributeByqname(@Nullable String qualifiedName) {
            checkNotClosed();
            return Optional.ofNullable(N_GetAttributeByqname(nativePtr, qualifiedName));
        }

        @Override
        public Optional<String> getAttributeBylname(@Nullable String localName, @Nullable String namespaceURI) {
            checkNotClosed();
            return Optional.ofNullable(N_GetAttributeBylname(nativePtr, localName, namespaceURI));
        }

        @Override
        public Optional<String> getInnerXml() {
            checkNotClosed();
            return Optional.ofNullable(N_GetInnerXml(nativePtr));
        }

        @Override
        public Optional<String> getOuterXml() {
            checkNotClosed();
            return Optional.ofNullable(N_GetOuterXml(nativePtr));
        }

        @Override
        public int getLineNumber() {
            checkNotClosed();
            return N_GetLineNumber(nativePtr);
        }

        @Override
        public int moveToAttributeByindex(int index) {
            checkNotClosed();
            return N_MoveToAttributeByindex(nativePtr, index);
        }

        @Override
        public int moveToAttributeByqname(@Nullable String qualifiedName) {
            checkNotClosed();
            return N_MoveToAttributeByqname(nativePtr, qualifiedName);
        }

        @Override
        public int moveToAttributeBylname(@Nullable String localName, @Nullable String namespaceURI) {
            checkNotClosed();
            return N_MoveToAttributeBylname(nativePtr, localName, namespaceURI);
        }

        @Override
        public boolean moveToFirstAttribute() {
            checkNotClosed();
            return N_MoveToFirstAttribute(nativePtr);
        }

        @Override
        public boolean moveToNextAttribute() {
            checkNotClosed();
            return N_MoveToNextAttribute(nativePtr);
        }

        @Override
        public boolean moveToCarryingElement() {
            checkNotClosed();
            return N_MoveToCarryingElement(nativePtr);
        }

        private static native boolean N_MoveToNextNode(long self);

        private static native boolean N_Close(long self);

        private static native boolean N_HasError(long self);

        private static native String N_GetError(long self);

        private static native CefXmlNodeType N_GetType(long self);

        private static native int N_GetDepth(long self);

        private static native String N_GetLocalName(long self);

        private static native String N_GetPrefix(long self);

        private static native String N_GetQualifiedName(long self);

        private static native String N_GetNamespaceUri(long self);

        private static native String N_GetBaseUri(long self);

        private static native String N_GetXmlLang(long self);

        private static native boolean N_IsEmptyElement(long self);

        private static native boolean N_HasValue(long self);

        private static native String N_GetValue(long self);

        private static native boolean N_HasAttributes(long self);

        private static native long N_GetAttributeCount(long self);

        private static native String N_GetAttributeByindex(long self, int index);

        private static native String N_GetAttributeByqname(long self, String qualifiedName);

        private static native String N_GetAttributeBylname(long self, String localName, String namespaceURI);

        private static native String N_GetInnerXml(long self);

        private static native String N_GetOuterXml(long self);

        private static native int N_GetLineNumber(long self);

        private static native int N_MoveToAttributeByindex(long self, int index);

        private static native int N_MoveToAttributeByqname(long self, String qualifiedName);

        private static native int N_MoveToAttributeBylname(long self, String localName, String namespaceURI);

        private static native boolean N_MoveToFirstAttribute(long self);

        private static native boolean N_MoveToNextAttribute(long self);

        private static native boolean N_MoveToCarryingElement(long self);

        static native CefXmlReader N_Create(CefStreamReader stream, CefXmlEncodingType encodingType, String uRI);

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
