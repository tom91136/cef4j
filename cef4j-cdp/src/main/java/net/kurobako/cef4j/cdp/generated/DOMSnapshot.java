// GENERATED - do not edit. Run scripts/update-cdp-schema.sh.
package net.kurobako.cef4j.cdp.generated;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.concurrent.CompletionStage;
import java.util.function.Consumer;
import javax.annotation.Nullable;
import net.kurobako.cef4j.cdp.CdpClient;
import net.kurobako.cef4j.cdp.CdpObject;
import net.kurobako.cef4j.cdp.CdpSubscription;

/**
 * This domain facilitates obtaining document snapshots with DOM, layout, and style information.
 * <p><b>Experimental:</b> this part of CDP may change without notice.
 * @see <a href="https://chromium.googlesource.com/chromium/src/+/refs/tags/150.0.7871.213/third_party/blink/public/devtools_protocol/domains/DOMSnapshot.pdl">Pinned protocol source</a>
 */
@SuppressWarnings({"EscapedEntity", "JavaLangClash", "MissingSummary", "UnusedMethod"})
public final class DOMSnapshot {
    private DOMSnapshot() {}
    @Nullable private static Long numberAsLong(@Nullable Object value) { return value == null ? null : ((Number) value).longValue(); }
    @Nullable private static Double numberAsDouble(@Nullable Object value) { return value == null ? null : ((Number) value).doubleValue(); }
    /**
     * A Node in the DOM tree.
     */
    public static final class DOMNode extends CdpObject {
        private DOMNode(Map<String, Object> values) { super(values); }
        @Nullable public static DOMNode fromMap(@Nullable Map<String, Object> values) {
            return values == null ? null : new DOMNode(values);
        }
        public static Builder builder() { return new Builder(); }
        /**
         * {@code Node}&#x27;s nodeType.
         * @return the protocol field value
         */
        @Nullable public Long nodeType() {
            return numberAsLong(value("nodeType"));
        }
        /**
         * {@code Node}&#x27;s nodeName.
         * @return the protocol field value
         */
        @Nullable public String nodeName() {
            return (String) value("nodeName");
        }
        /**
         * {@code Node}&#x27;s nodeValue.
         * @return the protocol field value
         */
        @Nullable public String nodeValue() {
            return (String) value("nodeValue");
        }
        /**
         * Only set for textarea elements, contains the text value.
         * @return the protocol field value
         */
        @Nullable public String textValue() {
            return (String) value("textValue");
        }
        /**
         * Only set for input elements, contains the input&#x27;s associated text value.
         * @return the protocol field value
         */
        @Nullable public String inputValue() {
            return (String) value("inputValue");
        }
        /**
         * Only set for radio and checkbox input elements, indicates if the element has been checked
         * @return the protocol field value
         */
        @Nullable public Boolean inputChecked() {
            return (Boolean) value("inputChecked");
        }
        /**
         * Only set for option elements, indicates if the element has been selected
         * @return the protocol field value
         */
        @Nullable public Boolean optionSelected() {
            return (Boolean) value("optionSelected");
        }
        /**
         * {@code Node}&#x27;s id, corresponds to DOM.Node.backendNodeId.
         * @return the protocol field value
         */
        @Nullable public Long backendNodeId() {
            return numberAsLong(value("backendNodeId"));
        }
        /**
         * The indexes of the node&#x27;s child nodes in the {@code domNodes} array returned by {@code getSnapshot}, if any.
         * @return the protocol field value
         */
        @Nullable public java.util.List<Long> childNodeIndexes() {
            return list(value("childNodeIndexes"), element0 -> numberAsLong(element0));
        }
        /**
         * Attributes of an {@code Element} node.
         * @return the protocol field value
         */
        @Nullable public java.util.List<DOMSnapshot.NameValue> attributes() {
            return list(value("attributes"), element0 -> DOMSnapshot.NameValue.fromMap(objectMap(element0)));
        }
        /**
         * Indexes of pseudo elements associated with this node in the {@code domNodes} array returned by {@code getSnapshot}, if any.
         * @return the protocol field value
         */
        @Nullable public java.util.List<Long> pseudoElementIndexes() {
            return list(value("pseudoElementIndexes"), element0 -> numberAsLong(element0));
        }
        /**
         * The index of the node&#x27;s related layout tree node in the {@code layoutTreeNodes} array returned by {@code getSnapshot}, if any.
         * @return the protocol field value
         */
        @Nullable public Long layoutNodeIndex() {
            return numberAsLong(value("layoutNodeIndex"));
        }
        /**
         * Document URL that {@code Document} or {@code FrameOwner} node points to.
         * @return the protocol field value
         */
        @Nullable public String documentURL() {
            return (String) value("documentURL");
        }
        /**
         * Base URL that {@code Document} or {@code FrameOwner} node uses for URL completion.
         * @return the protocol field value
         */
        @Nullable public String baseURL() {
            return (String) value("baseURL");
        }
        /**
         * Only set for documents, contains the document&#x27;s content language.
         * @return the protocol field value
         */
        @Nullable public String contentLanguage() {
            return (String) value("contentLanguage");
        }
        /**
         * Only set for documents, contains the document&#x27;s character set encoding.
         * @return the protocol field value
         */
        @Nullable public String documentEncoding() {
            return (String) value("documentEncoding");
        }
        /**
         * {@code DocumentType} node&#x27;s publicId.
         * @return the protocol field value
         */
        @Nullable public String publicId() {
            return (String) value("publicId");
        }
        /**
         * {@code DocumentType} node&#x27;s systemId.
         * @return the protocol field value
         */
        @Nullable public String systemId() {
            return (String) value("systemId");
        }
        /**
         * Frame ID for frame owner elements and also for the document node.
         * @return the protocol field value
         */
        @Nullable public String frameId() {
            return (String) value("frameId");
        }
        /**
         * The index of a frame owner element&#x27;s content document in the {@code domNodes} array returned by {@code getSnapshot}, if any.
         * @return the protocol field value
         */
        @Nullable public Long contentDocumentIndex() {
            return numberAsLong(value("contentDocumentIndex"));
        }
        /**
         * Type of a pseudo element node.
         * @return the protocol field value
         */
        @Nullable public String pseudoType() {
            return (String) value("pseudoType");
        }
        /**
         * Shadow root type.
         * @return the protocol field value
         */
        @Nullable public String shadowRootType() {
            return (String) value("shadowRootType");
        }
        /**
         * Whether this DOM node responds to mouse clicks. This includes nodes that have had click event listeners attached via JavaScript as well as anchor tags that naturally navigate when clicked.
         * @return the protocol field value
         */
        @Nullable public Boolean isClickable() {
            return (Boolean) value("isClickable");
        }
        /**
         * Details of the node&#x27;s event listeners, if any.
         * @return the protocol field value
         */
        @Nullable public java.util.List<DOMDebugger.EventListener> eventListeners() {
            return list(value("eventListeners"), element0 -> DOMDebugger.EventListener.fromMap(objectMap(element0)));
        }
        /**
         * The selected url for nodes with a srcset attribute.
         * @return the protocol field value
         */
        @Nullable public String currentSourceURL() {
            return (String) value("currentSourceURL");
        }
        /**
         * The url of the script (if any) that generates this node.
         * @return the protocol field value
         */
        @Nullable public String originURL() {
            return (String) value("originURL");
        }
        /**
         * Scroll offsets, set when this node is a Document.
         * @return the protocol field value
         */
        @Nullable public Double scrollOffsetX() {
            return numberAsDouble(value("scrollOffsetX"));
        }
        /**
         * Returns the scrollOffsetY field.
         * @return the protocol field value
         */
        @Nullable public Double scrollOffsetY() {
            return numberAsDouble(value("scrollOffsetY"));
        }
        public static final class Builder {
            private final Map<String, Object> values = new LinkedHashMap<>();
            /**
             * {@code Node}&#x27;s nodeType.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder nodeType(@Nullable Long value) {
                if (value == null) values.remove("nodeType");
                else values.put("nodeType", jsonValue(value));
                return this;
            }
            /**
             * {@code Node}&#x27;s nodeName.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder nodeName(@Nullable String value) {
                if (value == null) values.remove("nodeName");
                else values.put("nodeName", jsonValue(value));
                return this;
            }
            /**
             * {@code Node}&#x27;s nodeValue.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder nodeValue(@Nullable String value) {
                if (value == null) values.remove("nodeValue");
                else values.put("nodeValue", jsonValue(value));
                return this;
            }
            /**
             * Only set for textarea elements, contains the text value.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder textValue(@Nullable String value) {
                if (value == null) values.remove("textValue");
                else values.put("textValue", jsonValue(value));
                return this;
            }
            /**
             * Only set for input elements, contains the input&#x27;s associated text value.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder inputValue(@Nullable String value) {
                if (value == null) values.remove("inputValue");
                else values.put("inputValue", jsonValue(value));
                return this;
            }
            /**
             * Only set for radio and checkbox input elements, indicates if the element has been checked
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder inputChecked(@Nullable Boolean value) {
                if (value == null) values.remove("inputChecked");
                else values.put("inputChecked", jsonValue(value));
                return this;
            }
            /**
             * Only set for option elements, indicates if the element has been selected
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder optionSelected(@Nullable Boolean value) {
                if (value == null) values.remove("optionSelected");
                else values.put("optionSelected", jsonValue(value));
                return this;
            }
            /**
             * {@code Node}&#x27;s id, corresponds to DOM.Node.backendNodeId.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder backendNodeId(@Nullable Long value) {
                if (value == null) values.remove("backendNodeId");
                else values.put("backendNodeId", jsonValue(value));
                return this;
            }
            /**
             * The indexes of the node&#x27;s child nodes in the {@code domNodes} array returned by {@code getSnapshot}, if any.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder childNodeIndexes(@Nullable java.util.List<Long> value) {
                if (value == null) values.remove("childNodeIndexes");
                else values.put("childNodeIndexes", jsonValue(value));
                return this;
            }
            /**
             * Attributes of an {@code Element} node.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder attributes(@Nullable java.util.List<DOMSnapshot.NameValue> value) {
                if (value == null) values.remove("attributes");
                else values.put("attributes", jsonValue(value));
                return this;
            }
            /**
             * Indexes of pseudo elements associated with this node in the {@code domNodes} array returned by {@code getSnapshot}, if any.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder pseudoElementIndexes(@Nullable java.util.List<Long> value) {
                if (value == null) values.remove("pseudoElementIndexes");
                else values.put("pseudoElementIndexes", jsonValue(value));
                return this;
            }
            /**
             * The index of the node&#x27;s related layout tree node in the {@code layoutTreeNodes} array returned by {@code getSnapshot}, if any.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder layoutNodeIndex(@Nullable Long value) {
                if (value == null) values.remove("layoutNodeIndex");
                else values.put("layoutNodeIndex", jsonValue(value));
                return this;
            }
            /**
             * Document URL that {@code Document} or {@code FrameOwner} node points to.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder documentURL(@Nullable String value) {
                if (value == null) values.remove("documentURL");
                else values.put("documentURL", jsonValue(value));
                return this;
            }
            /**
             * Base URL that {@code Document} or {@code FrameOwner} node uses for URL completion.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder baseURL(@Nullable String value) {
                if (value == null) values.remove("baseURL");
                else values.put("baseURL", jsonValue(value));
                return this;
            }
            /**
             * Only set for documents, contains the document&#x27;s content language.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder contentLanguage(@Nullable String value) {
                if (value == null) values.remove("contentLanguage");
                else values.put("contentLanguage", jsonValue(value));
                return this;
            }
            /**
             * Only set for documents, contains the document&#x27;s character set encoding.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder documentEncoding(@Nullable String value) {
                if (value == null) values.remove("documentEncoding");
                else values.put("documentEncoding", jsonValue(value));
                return this;
            }
            /**
             * {@code DocumentType} node&#x27;s publicId.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder publicId(@Nullable String value) {
                if (value == null) values.remove("publicId");
                else values.put("publicId", jsonValue(value));
                return this;
            }
            /**
             * {@code DocumentType} node&#x27;s systemId.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder systemId(@Nullable String value) {
                if (value == null) values.remove("systemId");
                else values.put("systemId", jsonValue(value));
                return this;
            }
            /**
             * Frame ID for frame owner elements and also for the document node.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder frameId(@Nullable String value) {
                if (value == null) values.remove("frameId");
                else values.put("frameId", jsonValue(value));
                return this;
            }
            /**
             * The index of a frame owner element&#x27;s content document in the {@code domNodes} array returned by {@code getSnapshot}, if any.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder contentDocumentIndex(@Nullable Long value) {
                if (value == null) values.remove("contentDocumentIndex");
                else values.put("contentDocumentIndex", jsonValue(value));
                return this;
            }
            /**
             * Type of a pseudo element node.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder pseudoType(@Nullable String value) {
                if (value == null) values.remove("pseudoType");
                else values.put("pseudoType", jsonValue(value));
                return this;
            }
            /**
             * Shadow root type.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder shadowRootType(@Nullable String value) {
                if (value == null) values.remove("shadowRootType");
                else values.put("shadowRootType", jsonValue(value));
                return this;
            }
            /**
             * Whether this DOM node responds to mouse clicks. This includes nodes that have had click event listeners attached via JavaScript as well as anchor tags that naturally navigate when clicked.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder isClickable(@Nullable Boolean value) {
                if (value == null) values.remove("isClickable");
                else values.put("isClickable", jsonValue(value));
                return this;
            }
            /**
             * Details of the node&#x27;s event listeners, if any.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder eventListeners(@Nullable java.util.List<DOMDebugger.EventListener> value) {
                if (value == null) values.remove("eventListeners");
                else values.put("eventListeners", jsonValue(value));
                return this;
            }
            /**
             * The selected url for nodes with a srcset attribute.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder currentSourceURL(@Nullable String value) {
                if (value == null) values.remove("currentSourceURL");
                else values.put("currentSourceURL", jsonValue(value));
                return this;
            }
            /**
             * The url of the script (if any) that generates this node.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder originURL(@Nullable String value) {
                if (value == null) values.remove("originURL");
                else values.put("originURL", jsonValue(value));
                return this;
            }
            /**
             * Scroll offsets, set when this node is a Document.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder scrollOffsetX(@Nullable Double value) {
                if (value == null) values.remove("scrollOffsetX");
                else values.put("scrollOffsetX", jsonValue(value));
                return this;
            }
            /**
             * Sets the scrollOffsetY field.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder scrollOffsetY(@Nullable Double value) {
                if (value == null) values.remove("scrollOffsetY");
                else values.put("scrollOffsetY", jsonValue(value));
                return this;
            }
            public DOMNode build() {
                if (!values.containsKey("nodeType")) throw new IllegalStateException("Missing required CDP field: nodeType");
                if (!values.containsKey("nodeName")) throw new IllegalStateException("Missing required CDP field: nodeName");
                if (!values.containsKey("nodeValue")) throw new IllegalStateException("Missing required CDP field: nodeValue");
                if (!values.containsKey("backendNodeId")) throw new IllegalStateException("Missing required CDP field: backendNodeId");
                return new DOMNode(values);
            }
        }
    }
    /**
     * Details of post layout rendered text positions. The exact layout should not be regarded as stable and may change between versions.
     */
    public static final class InlineTextBox extends CdpObject {
        private InlineTextBox(Map<String, Object> values) { super(values); }
        @Nullable public static InlineTextBox fromMap(@Nullable Map<String, Object> values) {
            return values == null ? null : new InlineTextBox(values);
        }
        public static Builder builder() { return new Builder(); }
        /**
         * The bounding box in document coordinates. Note that scroll offset of the document is ignored.
         * @return the protocol field value
         */
        @Nullable public DOM.Rect boundingBox() {
            return DOM.Rect.fromMap(objectMap(value("boundingBox")));
        }
        /**
         * The starting index in characters, for this post layout textbox substring. Characters that would be represented as a surrogate pair in UTF-16 have length 2.
         * @return the protocol field value
         */
        @Nullable public Long startCharacterIndex() {
            return numberAsLong(value("startCharacterIndex"));
        }
        /**
         * The number of characters in this post layout textbox substring. Characters that would be represented as a surrogate pair in UTF-16 have length 2.
         * @return the protocol field value
         */
        @Nullable public Long numCharacters() {
            return numberAsLong(value("numCharacters"));
        }
        public static final class Builder {
            private final Map<String, Object> values = new LinkedHashMap<>();
            /**
             * The bounding box in document coordinates. Note that scroll offset of the document is ignored.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder boundingBox(@Nullable DOM.Rect value) {
                if (value == null) values.remove("boundingBox");
                else values.put("boundingBox", jsonValue(value));
                return this;
            }
            /**
             * The starting index in characters, for this post layout textbox substring. Characters that would be represented as a surrogate pair in UTF-16 have length 2.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder startCharacterIndex(@Nullable Long value) {
                if (value == null) values.remove("startCharacterIndex");
                else values.put("startCharacterIndex", jsonValue(value));
                return this;
            }
            /**
             * The number of characters in this post layout textbox substring. Characters that would be represented as a surrogate pair in UTF-16 have length 2.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder numCharacters(@Nullable Long value) {
                if (value == null) values.remove("numCharacters");
                else values.put("numCharacters", jsonValue(value));
                return this;
            }
            public InlineTextBox build() {
                if (!values.containsKey("boundingBox")) throw new IllegalStateException("Missing required CDP field: boundingBox");
                if (!values.containsKey("startCharacterIndex")) throw new IllegalStateException("Missing required CDP field: startCharacterIndex");
                if (!values.containsKey("numCharacters")) throw new IllegalStateException("Missing required CDP field: numCharacters");
                return new InlineTextBox(values);
            }
        }
    }
    /**
     * Details of an element in the DOM tree with a LayoutObject.
     */
    public static final class LayoutTreeNode extends CdpObject {
        private LayoutTreeNode(Map<String, Object> values) { super(values); }
        @Nullable public static LayoutTreeNode fromMap(@Nullable Map<String, Object> values) {
            return values == null ? null : new LayoutTreeNode(values);
        }
        public static Builder builder() { return new Builder(); }
        /**
         * The index of the related DOM node in the {@code domNodes} array returned by {@code getSnapshot}.
         * @return the protocol field value
         */
        @Nullable public Long domNodeIndex() {
            return numberAsLong(value("domNodeIndex"));
        }
        /**
         * The bounding box in document coordinates. Note that scroll offset of the document is ignored.
         * @return the protocol field value
         */
        @Nullable public DOM.Rect boundingBox() {
            return DOM.Rect.fromMap(objectMap(value("boundingBox")));
        }
        /**
         * Contents of the LayoutText, if any.
         * @return the protocol field value
         */
        @Nullable public String layoutText() {
            return (String) value("layoutText");
        }
        /**
         * The post-layout inline text nodes, if any.
         * @return the protocol field value
         */
        @Nullable public java.util.List<DOMSnapshot.InlineTextBox> inlineTextNodes() {
            return list(value("inlineTextNodes"), element0 -> DOMSnapshot.InlineTextBox.fromMap(objectMap(element0)));
        }
        /**
         * Index into the {@code computedStyles} array returned by {@code getSnapshot}.
         * @return the protocol field value
         */
        @Nullable public Long styleIndex() {
            return numberAsLong(value("styleIndex"));
        }
        /**
         * Global paint order index, which is determined by the stacking order of the nodes. Nodes that are painted together will have the same index. Only provided if includePaintOrder in getSnapshot was true.
         * @return the protocol field value
         */
        @Nullable public Long paintOrder() {
            return numberAsLong(value("paintOrder"));
        }
        /**
         * Set to true to indicate the element begins a new stacking context.
         * @return the protocol field value
         */
        @Nullable public Boolean isStackingContext() {
            return (Boolean) value("isStackingContext");
        }
        public static final class Builder {
            private final Map<String, Object> values = new LinkedHashMap<>();
            /**
             * The index of the related DOM node in the {@code domNodes} array returned by {@code getSnapshot}.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder domNodeIndex(@Nullable Long value) {
                if (value == null) values.remove("domNodeIndex");
                else values.put("domNodeIndex", jsonValue(value));
                return this;
            }
            /**
             * The bounding box in document coordinates. Note that scroll offset of the document is ignored.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder boundingBox(@Nullable DOM.Rect value) {
                if (value == null) values.remove("boundingBox");
                else values.put("boundingBox", jsonValue(value));
                return this;
            }
            /**
             * Contents of the LayoutText, if any.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder layoutText(@Nullable String value) {
                if (value == null) values.remove("layoutText");
                else values.put("layoutText", jsonValue(value));
                return this;
            }
            /**
             * The post-layout inline text nodes, if any.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder inlineTextNodes(@Nullable java.util.List<DOMSnapshot.InlineTextBox> value) {
                if (value == null) values.remove("inlineTextNodes");
                else values.put("inlineTextNodes", jsonValue(value));
                return this;
            }
            /**
             * Index into the {@code computedStyles} array returned by {@code getSnapshot}.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder styleIndex(@Nullable Long value) {
                if (value == null) values.remove("styleIndex");
                else values.put("styleIndex", jsonValue(value));
                return this;
            }
            /**
             * Global paint order index, which is determined by the stacking order of the nodes. Nodes that are painted together will have the same index. Only provided if includePaintOrder in getSnapshot was true.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder paintOrder(@Nullable Long value) {
                if (value == null) values.remove("paintOrder");
                else values.put("paintOrder", jsonValue(value));
                return this;
            }
            /**
             * Set to true to indicate the element begins a new stacking context.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder isStackingContext(@Nullable Boolean value) {
                if (value == null) values.remove("isStackingContext");
                else values.put("isStackingContext", jsonValue(value));
                return this;
            }
            public LayoutTreeNode build() {
                if (!values.containsKey("domNodeIndex")) throw new IllegalStateException("Missing required CDP field: domNodeIndex");
                if (!values.containsKey("boundingBox")) throw new IllegalStateException("Missing required CDP field: boundingBox");
                return new LayoutTreeNode(values);
            }
        }
    }
    /**
     * A subset of the full ComputedStyle as defined by the request whitelist.
     */
    public static final class ComputedStyle extends CdpObject {
        private ComputedStyle(Map<String, Object> values) { super(values); }
        @Nullable public static ComputedStyle fromMap(@Nullable Map<String, Object> values) {
            return values == null ? null : new ComputedStyle(values);
        }
        public static Builder builder() { return new Builder(); }
        /**
         * Name/value pairs of computed style properties.
         * @return the protocol field value
         */
        @Nullable public java.util.List<DOMSnapshot.NameValue> properties() {
            return list(value("properties"), element0 -> DOMSnapshot.NameValue.fromMap(objectMap(element0)));
        }
        public static final class Builder {
            private final Map<String, Object> values = new LinkedHashMap<>();
            /**
             * Name/value pairs of computed style properties.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder properties(@Nullable java.util.List<DOMSnapshot.NameValue> value) {
                if (value == null) values.remove("properties");
                else values.put("properties", jsonValue(value));
                return this;
            }
            public ComputedStyle build() {
                if (!values.containsKey("properties")) throw new IllegalStateException("Missing required CDP field: properties");
                return new ComputedStyle(values);
            }
        }
    }
    /**
     * A name/value pair.
     */
    public static final class NameValue extends CdpObject {
        private NameValue(Map<String, Object> values) { super(values); }
        @Nullable public static NameValue fromMap(@Nullable Map<String, Object> values) {
            return values == null ? null : new NameValue(values);
        }
        public static Builder builder() { return new Builder(); }
        /**
         * Attribute/property name.
         * @return the protocol field value
         */
        @Nullable public String name() {
            return (String) value("name");
        }
        /**
         * Attribute/property value.
         * @return the protocol field value
         */
        @Nullable public String value() {
            return (String) value("value");
        }
        public static final class Builder {
            private final Map<String, Object> values = new LinkedHashMap<>();
            /**
             * Attribute/property name.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder name(@Nullable String value) {
                if (value == null) values.remove("name");
                else values.put("name", jsonValue(value));
                return this;
            }
            /**
             * Attribute/property value.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder value(@Nullable String value) {
                if (value == null) values.remove("value");
                else values.put("value", jsonValue(value));
                return this;
            }
            public NameValue build() {
                if (!values.containsKey("name")) throw new IllegalStateException("Missing required CDP field: name");
                if (!values.containsKey("value")) throw new IllegalStateException("Missing required CDP field: value");
                return new NameValue(values);
            }
        }
    }
    /**
     * Data that is only present on rare nodes.
     */
    public static final class RareStringData extends CdpObject {
        private RareStringData(Map<String, Object> values) { super(values); }
        @Nullable public static RareStringData fromMap(@Nullable Map<String, Object> values) {
            return values == null ? null : new RareStringData(values);
        }
        public static Builder builder() { return new Builder(); }
        /**
         * Returns the index field.
         * @return the protocol field value
         */
        @Nullable public java.util.List<Long> index() {
            return list(value("index"), element0 -> numberAsLong(element0));
        }
        /**
         * Returns the value field.
         * @return the protocol field value
         */
        @Nullable public java.util.List<Long> value() {
            return list(value("value"), element0 -> numberAsLong(element0));
        }
        public static final class Builder {
            private final Map<String, Object> values = new LinkedHashMap<>();
            /**
             * Sets the index field.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder index(@Nullable java.util.List<Long> value) {
                if (value == null) values.remove("index");
                else values.put("index", jsonValue(value));
                return this;
            }
            /**
             * Sets the value field.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder value(@Nullable java.util.List<Long> value) {
                if (value == null) values.remove("value");
                else values.put("value", jsonValue(value));
                return this;
            }
            public RareStringData build() {
                if (!values.containsKey("index")) throw new IllegalStateException("Missing required CDP field: index");
                if (!values.containsKey("value")) throw new IllegalStateException("Missing required CDP field: value");
                return new RareStringData(values);
            }
        }
    }
    /**
     */
    public static final class RareBooleanData extends CdpObject {
        private RareBooleanData(Map<String, Object> values) { super(values); }
        @Nullable public static RareBooleanData fromMap(@Nullable Map<String, Object> values) {
            return values == null ? null : new RareBooleanData(values);
        }
        public static Builder builder() { return new Builder(); }
        /**
         * Returns the index field.
         * @return the protocol field value
         */
        @Nullable public java.util.List<Long> index() {
            return list(value("index"), element0 -> numberAsLong(element0));
        }
        public static final class Builder {
            private final Map<String, Object> values = new LinkedHashMap<>();
            /**
             * Sets the index field.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder index(@Nullable java.util.List<Long> value) {
                if (value == null) values.remove("index");
                else values.put("index", jsonValue(value));
                return this;
            }
            public RareBooleanData build() {
                if (!values.containsKey("index")) throw new IllegalStateException("Missing required CDP field: index");
                return new RareBooleanData(values);
            }
        }
    }
    /**
     */
    public static final class RareIntegerData extends CdpObject {
        private RareIntegerData(Map<String, Object> values) { super(values); }
        @Nullable public static RareIntegerData fromMap(@Nullable Map<String, Object> values) {
            return values == null ? null : new RareIntegerData(values);
        }
        public static Builder builder() { return new Builder(); }
        /**
         * Returns the index field.
         * @return the protocol field value
         */
        @Nullable public java.util.List<Long> index() {
            return list(value("index"), element0 -> numberAsLong(element0));
        }
        /**
         * Returns the value field.
         * @return the protocol field value
         */
        @Nullable public java.util.List<Long> value() {
            return list(value("value"), element0 -> numberAsLong(element0));
        }
        public static final class Builder {
            private final Map<String, Object> values = new LinkedHashMap<>();
            /**
             * Sets the index field.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder index(@Nullable java.util.List<Long> value) {
                if (value == null) values.remove("index");
                else values.put("index", jsonValue(value));
                return this;
            }
            /**
             * Sets the value field.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder value(@Nullable java.util.List<Long> value) {
                if (value == null) values.remove("value");
                else values.put("value", jsonValue(value));
                return this;
            }
            public RareIntegerData build() {
                if (!values.containsKey("index")) throw new IllegalStateException("Missing required CDP field: index");
                if (!values.containsKey("value")) throw new IllegalStateException("Missing required CDP field: value");
                return new RareIntegerData(values);
            }
        }
    }
    /**
     * Document snapshot.
     */
    public static final class DocumentSnapshot extends CdpObject {
        private DocumentSnapshot(Map<String, Object> values) { super(values); }
        @Nullable public static DocumentSnapshot fromMap(@Nullable Map<String, Object> values) {
            return values == null ? null : new DocumentSnapshot(values);
        }
        public static Builder builder() { return new Builder(); }
        /**
         * Document URL that {@code Document} or {@code FrameOwner} node points to.
         * @return the protocol field value
         */
        @Nullable public Long documentURL() {
            return numberAsLong(value("documentURL"));
        }
        /**
         * Document title.
         * @return the protocol field value
         */
        @Nullable public Long title() {
            return numberAsLong(value("title"));
        }
        /**
         * Base URL that {@code Document} or {@code FrameOwner} node uses for URL completion.
         * @return the protocol field value
         */
        @Nullable public Long baseURL() {
            return numberAsLong(value("baseURL"));
        }
        /**
         * Contains the document&#x27;s content language.
         * @return the protocol field value
         */
        @Nullable public Long contentLanguage() {
            return numberAsLong(value("contentLanguage"));
        }
        /**
         * Contains the document&#x27;s character set encoding.
         * @return the protocol field value
         */
        @Nullable public Long encodingName() {
            return numberAsLong(value("encodingName"));
        }
        /**
         * {@code DocumentType} node&#x27;s publicId.
         * @return the protocol field value
         */
        @Nullable public Long publicId() {
            return numberAsLong(value("publicId"));
        }
        /**
         * {@code DocumentType} node&#x27;s systemId.
         * @return the protocol field value
         */
        @Nullable public Long systemId() {
            return numberAsLong(value("systemId"));
        }
        /**
         * Frame ID for frame owner elements and also for the document node.
         * @return the protocol field value
         */
        @Nullable public Long frameId() {
            return numberAsLong(value("frameId"));
        }
        /**
         * A table with dom nodes.
         * @return the protocol field value
         */
        @Nullable public DOMSnapshot.NodeTreeSnapshot nodes() {
            return DOMSnapshot.NodeTreeSnapshot.fromMap(objectMap(value("nodes")));
        }
        /**
         * The nodes in the layout tree.
         * @return the protocol field value
         */
        @Nullable public DOMSnapshot.LayoutTreeSnapshot layout() {
            return DOMSnapshot.LayoutTreeSnapshot.fromMap(objectMap(value("layout")));
        }
        /**
         * The post-layout inline text nodes.
         * @return the protocol field value
         */
        @Nullable public DOMSnapshot.TextBoxSnapshot textBoxes() {
            return DOMSnapshot.TextBoxSnapshot.fromMap(objectMap(value("textBoxes")));
        }
        /**
         * Horizontal scroll offset.
         * @return the protocol field value
         */
        @Nullable public Double scrollOffsetX() {
            return numberAsDouble(value("scrollOffsetX"));
        }
        /**
         * Vertical scroll offset.
         * @return the protocol field value
         */
        @Nullable public Double scrollOffsetY() {
            return numberAsDouble(value("scrollOffsetY"));
        }
        /**
         * Document content width.
         * @return the protocol field value
         */
        @Nullable public Double contentWidth() {
            return numberAsDouble(value("contentWidth"));
        }
        /**
         * Document content height.
         * @return the protocol field value
         */
        @Nullable public Double contentHeight() {
            return numberAsDouble(value("contentHeight"));
        }
        public static final class Builder {
            private final Map<String, Object> values = new LinkedHashMap<>();
            /**
             * Document URL that {@code Document} or {@code FrameOwner} node points to.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder documentURL(@Nullable Long value) {
                if (value == null) values.remove("documentURL");
                else values.put("documentURL", jsonValue(value));
                return this;
            }
            /**
             * Document title.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder title(@Nullable Long value) {
                if (value == null) values.remove("title");
                else values.put("title", jsonValue(value));
                return this;
            }
            /**
             * Base URL that {@code Document} or {@code FrameOwner} node uses for URL completion.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder baseURL(@Nullable Long value) {
                if (value == null) values.remove("baseURL");
                else values.put("baseURL", jsonValue(value));
                return this;
            }
            /**
             * Contains the document&#x27;s content language.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder contentLanguage(@Nullable Long value) {
                if (value == null) values.remove("contentLanguage");
                else values.put("contentLanguage", jsonValue(value));
                return this;
            }
            /**
             * Contains the document&#x27;s character set encoding.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder encodingName(@Nullable Long value) {
                if (value == null) values.remove("encodingName");
                else values.put("encodingName", jsonValue(value));
                return this;
            }
            /**
             * {@code DocumentType} node&#x27;s publicId.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder publicId(@Nullable Long value) {
                if (value == null) values.remove("publicId");
                else values.put("publicId", jsonValue(value));
                return this;
            }
            /**
             * {@code DocumentType} node&#x27;s systemId.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder systemId(@Nullable Long value) {
                if (value == null) values.remove("systemId");
                else values.put("systemId", jsonValue(value));
                return this;
            }
            /**
             * Frame ID for frame owner elements and also for the document node.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder frameId(@Nullable Long value) {
                if (value == null) values.remove("frameId");
                else values.put("frameId", jsonValue(value));
                return this;
            }
            /**
             * A table with dom nodes.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder nodes(@Nullable DOMSnapshot.NodeTreeSnapshot value) {
                if (value == null) values.remove("nodes");
                else values.put("nodes", jsonValue(value));
                return this;
            }
            /**
             * The nodes in the layout tree.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder layout(@Nullable DOMSnapshot.LayoutTreeSnapshot value) {
                if (value == null) values.remove("layout");
                else values.put("layout", jsonValue(value));
                return this;
            }
            /**
             * The post-layout inline text nodes.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder textBoxes(@Nullable DOMSnapshot.TextBoxSnapshot value) {
                if (value == null) values.remove("textBoxes");
                else values.put("textBoxes", jsonValue(value));
                return this;
            }
            /**
             * Horizontal scroll offset.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder scrollOffsetX(@Nullable Double value) {
                if (value == null) values.remove("scrollOffsetX");
                else values.put("scrollOffsetX", jsonValue(value));
                return this;
            }
            /**
             * Vertical scroll offset.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder scrollOffsetY(@Nullable Double value) {
                if (value == null) values.remove("scrollOffsetY");
                else values.put("scrollOffsetY", jsonValue(value));
                return this;
            }
            /**
             * Document content width.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder contentWidth(@Nullable Double value) {
                if (value == null) values.remove("contentWidth");
                else values.put("contentWidth", jsonValue(value));
                return this;
            }
            /**
             * Document content height.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder contentHeight(@Nullable Double value) {
                if (value == null) values.remove("contentHeight");
                else values.put("contentHeight", jsonValue(value));
                return this;
            }
            public DocumentSnapshot build() {
                if (!values.containsKey("documentURL")) throw new IllegalStateException("Missing required CDP field: documentURL");
                if (!values.containsKey("title")) throw new IllegalStateException("Missing required CDP field: title");
                if (!values.containsKey("baseURL")) throw new IllegalStateException("Missing required CDP field: baseURL");
                if (!values.containsKey("contentLanguage")) throw new IllegalStateException("Missing required CDP field: contentLanguage");
                if (!values.containsKey("encodingName")) throw new IllegalStateException("Missing required CDP field: encodingName");
                if (!values.containsKey("publicId")) throw new IllegalStateException("Missing required CDP field: publicId");
                if (!values.containsKey("systemId")) throw new IllegalStateException("Missing required CDP field: systemId");
                if (!values.containsKey("frameId")) throw new IllegalStateException("Missing required CDP field: frameId");
                if (!values.containsKey("nodes")) throw new IllegalStateException("Missing required CDP field: nodes");
                if (!values.containsKey("layout")) throw new IllegalStateException("Missing required CDP field: layout");
                if (!values.containsKey("textBoxes")) throw new IllegalStateException("Missing required CDP field: textBoxes");
                return new DocumentSnapshot(values);
            }
        }
    }
    /**
     * Table containing nodes.
     */
    public static final class NodeTreeSnapshot extends CdpObject {
        private NodeTreeSnapshot(Map<String, Object> values) { super(values); }
        @Nullable public static NodeTreeSnapshot fromMap(@Nullable Map<String, Object> values) {
            return values == null ? null : new NodeTreeSnapshot(values);
        }
        public static Builder builder() { return new Builder(); }
        /**
         * Parent node index.
         * @return the protocol field value
         */
        @Nullable public java.util.List<Long> parentIndex() {
            return list(value("parentIndex"), element0 -> numberAsLong(element0));
        }
        /**
         * {@code Node}&#x27;s nodeType.
         * @return the protocol field value
         */
        @Nullable public java.util.List<Long> nodeType() {
            return list(value("nodeType"), element0 -> numberAsLong(element0));
        }
        /**
         * Type of the shadow root the {@code Node} is in. String values are equal to the {@code ShadowRootType} enum.
         * @return the protocol field value
         */
        @Nullable public DOMSnapshot.RareStringData shadowRootType() {
            return DOMSnapshot.RareStringData.fromMap(objectMap(value("shadowRootType")));
        }
        /**
         * {@code Node}&#x27;s nodeName.
         * @return the protocol field value
         */
        @Nullable public java.util.List<Long> nodeName() {
            return list(value("nodeName"), element0 -> numberAsLong(element0));
        }
        /**
         * {@code Node}&#x27;s nodeValue.
         * @return the protocol field value
         */
        @Nullable public java.util.List<Long> nodeValue() {
            return list(value("nodeValue"), element0 -> numberAsLong(element0));
        }
        /**
         * {@code Node}&#x27;s id, corresponds to DOM.Node.backendNodeId.
         * @return the protocol field value
         */
        @Nullable public java.util.List<Long> backendNodeId() {
            return list(value("backendNodeId"), element0 -> numberAsLong(element0));
        }
        /**
         * Attributes of an {@code Element} node. Flatten name, value pairs.
         * @return the protocol field value
         */
        @Nullable public java.util.List<java.util.List<Long>> attributes() {
            return list(value("attributes"), element0 -> list(element0, element1 -> numberAsLong(element1)));
        }
        /**
         * Only set for textarea elements, contains the text value.
         * @return the protocol field value
         */
        @Nullable public DOMSnapshot.RareStringData textValue() {
            return DOMSnapshot.RareStringData.fromMap(objectMap(value("textValue")));
        }
        /**
         * Only set for input elements, contains the input&#x27;s associated text value.
         * @return the protocol field value
         */
        @Nullable public DOMSnapshot.RareStringData inputValue() {
            return DOMSnapshot.RareStringData.fromMap(objectMap(value("inputValue")));
        }
        /**
         * Only set for radio and checkbox input elements, indicates if the element has been checked
         * @return the protocol field value
         */
        @Nullable public DOMSnapshot.RareBooleanData inputChecked() {
            return DOMSnapshot.RareBooleanData.fromMap(objectMap(value("inputChecked")));
        }
        /**
         * Only set for option elements, indicates if the element has been selected
         * @return the protocol field value
         */
        @Nullable public DOMSnapshot.RareBooleanData optionSelected() {
            return DOMSnapshot.RareBooleanData.fromMap(objectMap(value("optionSelected")));
        }
        /**
         * The index of the document in the list of the snapshot documents.
         * @return the protocol field value
         */
        @Nullable public DOMSnapshot.RareIntegerData contentDocumentIndex() {
            return DOMSnapshot.RareIntegerData.fromMap(objectMap(value("contentDocumentIndex")));
        }
        /**
         * Type of a pseudo element node.
         * @return the protocol field value
         */
        @Nullable public DOMSnapshot.RareStringData pseudoType() {
            return DOMSnapshot.RareStringData.fromMap(objectMap(value("pseudoType")));
        }
        /**
         * Pseudo element identifier for this node. Only present if there is a valid pseudoType.
         * @return the protocol field value
         */
        @Nullable public DOMSnapshot.RareStringData pseudoIdentifier() {
            return DOMSnapshot.RareStringData.fromMap(objectMap(value("pseudoIdentifier")));
        }
        /**
         * Whether this DOM node responds to mouse clicks. This includes nodes that have had click event listeners attached via JavaScript as well as anchor tags that naturally navigate when clicked.
         * @return the protocol field value
         */
        @Nullable public DOMSnapshot.RareBooleanData isClickable() {
            return DOMSnapshot.RareBooleanData.fromMap(objectMap(value("isClickable")));
        }
        /**
         * The selected url for nodes with a srcset attribute.
         * @return the protocol field value
         */
        @Nullable public DOMSnapshot.RareStringData currentSourceURL() {
            return DOMSnapshot.RareStringData.fromMap(objectMap(value("currentSourceURL")));
        }
        /**
         * The url of the script (if any) that generates this node.
         * @return the protocol field value
         */
        @Nullable public DOMSnapshot.RareStringData originURL() {
            return DOMSnapshot.RareStringData.fromMap(objectMap(value("originURL")));
        }
        public static final class Builder {
            private final Map<String, Object> values = new LinkedHashMap<>();
            /**
             * Parent node index.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder parentIndex(@Nullable java.util.List<Long> value) {
                if (value == null) values.remove("parentIndex");
                else values.put("parentIndex", jsonValue(value));
                return this;
            }
            /**
             * {@code Node}&#x27;s nodeType.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder nodeType(@Nullable java.util.List<Long> value) {
                if (value == null) values.remove("nodeType");
                else values.put("nodeType", jsonValue(value));
                return this;
            }
            /**
             * Type of the shadow root the {@code Node} is in. String values are equal to the {@code ShadowRootType} enum.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder shadowRootType(@Nullable DOMSnapshot.RareStringData value) {
                if (value == null) values.remove("shadowRootType");
                else values.put("shadowRootType", jsonValue(value));
                return this;
            }
            /**
             * {@code Node}&#x27;s nodeName.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder nodeName(@Nullable java.util.List<Long> value) {
                if (value == null) values.remove("nodeName");
                else values.put("nodeName", jsonValue(value));
                return this;
            }
            /**
             * {@code Node}&#x27;s nodeValue.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder nodeValue(@Nullable java.util.List<Long> value) {
                if (value == null) values.remove("nodeValue");
                else values.put("nodeValue", jsonValue(value));
                return this;
            }
            /**
             * {@code Node}&#x27;s id, corresponds to DOM.Node.backendNodeId.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder backendNodeId(@Nullable java.util.List<Long> value) {
                if (value == null) values.remove("backendNodeId");
                else values.put("backendNodeId", jsonValue(value));
                return this;
            }
            /**
             * Attributes of an {@code Element} node. Flatten name, value pairs.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder attributes(@Nullable java.util.List<java.util.List<Long>> value) {
                if (value == null) values.remove("attributes");
                else values.put("attributes", jsonValue(value));
                return this;
            }
            /**
             * Only set for textarea elements, contains the text value.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder textValue(@Nullable DOMSnapshot.RareStringData value) {
                if (value == null) values.remove("textValue");
                else values.put("textValue", jsonValue(value));
                return this;
            }
            /**
             * Only set for input elements, contains the input&#x27;s associated text value.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder inputValue(@Nullable DOMSnapshot.RareStringData value) {
                if (value == null) values.remove("inputValue");
                else values.put("inputValue", jsonValue(value));
                return this;
            }
            /**
             * Only set for radio and checkbox input elements, indicates if the element has been checked
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder inputChecked(@Nullable DOMSnapshot.RareBooleanData value) {
                if (value == null) values.remove("inputChecked");
                else values.put("inputChecked", jsonValue(value));
                return this;
            }
            /**
             * Only set for option elements, indicates if the element has been selected
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder optionSelected(@Nullable DOMSnapshot.RareBooleanData value) {
                if (value == null) values.remove("optionSelected");
                else values.put("optionSelected", jsonValue(value));
                return this;
            }
            /**
             * The index of the document in the list of the snapshot documents.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder contentDocumentIndex(@Nullable DOMSnapshot.RareIntegerData value) {
                if (value == null) values.remove("contentDocumentIndex");
                else values.put("contentDocumentIndex", jsonValue(value));
                return this;
            }
            /**
             * Type of a pseudo element node.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder pseudoType(@Nullable DOMSnapshot.RareStringData value) {
                if (value == null) values.remove("pseudoType");
                else values.put("pseudoType", jsonValue(value));
                return this;
            }
            /**
             * Pseudo element identifier for this node. Only present if there is a valid pseudoType.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder pseudoIdentifier(@Nullable DOMSnapshot.RareStringData value) {
                if (value == null) values.remove("pseudoIdentifier");
                else values.put("pseudoIdentifier", jsonValue(value));
                return this;
            }
            /**
             * Whether this DOM node responds to mouse clicks. This includes nodes that have had click event listeners attached via JavaScript as well as anchor tags that naturally navigate when clicked.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder isClickable(@Nullable DOMSnapshot.RareBooleanData value) {
                if (value == null) values.remove("isClickable");
                else values.put("isClickable", jsonValue(value));
                return this;
            }
            /**
             * The selected url for nodes with a srcset attribute.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder currentSourceURL(@Nullable DOMSnapshot.RareStringData value) {
                if (value == null) values.remove("currentSourceURL");
                else values.put("currentSourceURL", jsonValue(value));
                return this;
            }
            /**
             * The url of the script (if any) that generates this node.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder originURL(@Nullable DOMSnapshot.RareStringData value) {
                if (value == null) values.remove("originURL");
                else values.put("originURL", jsonValue(value));
                return this;
            }
            public NodeTreeSnapshot build() {
                return new NodeTreeSnapshot(values);
            }
        }
    }
    /**
     * Table of details of an element in the DOM tree with a LayoutObject.
     */
    public static final class LayoutTreeSnapshot extends CdpObject {
        private LayoutTreeSnapshot(Map<String, Object> values) { super(values); }
        @Nullable public static LayoutTreeSnapshot fromMap(@Nullable Map<String, Object> values) {
            return values == null ? null : new LayoutTreeSnapshot(values);
        }
        public static Builder builder() { return new Builder(); }
        /**
         * Index of the corresponding node in the {@code NodeTreeSnapshot} array returned by {@code captureSnapshot}.
         * @return the protocol field value
         */
        @Nullable public java.util.List<Long> nodeIndex() {
            return list(value("nodeIndex"), element0 -> numberAsLong(element0));
        }
        /**
         * Array of indexes specifying computed style strings, filtered according to the {@code computedStyles} parameter passed to {@code captureSnapshot}.
         * @return the protocol field value
         */
        @Nullable public java.util.List<java.util.List<Long>> styles() {
            return list(value("styles"), element0 -> list(element0, element1 -> numberAsLong(element1)));
        }
        /**
         * The absolute position bounding box.
         * @return the protocol field value
         */
        @Nullable public java.util.List<java.util.List<Double>> bounds() {
            return list(value("bounds"), element0 -> list(element0, element1 -> numberAsDouble(element1)));
        }
        /**
         * Contents of the LayoutText, if any.
         * @return the protocol field value
         */
        @Nullable public java.util.List<Long> text() {
            return list(value("text"), element0 -> numberAsLong(element0));
        }
        /**
         * Stacking context information.
         * @return the protocol field value
         */
        @Nullable public DOMSnapshot.RareBooleanData stackingContexts() {
            return DOMSnapshot.RareBooleanData.fromMap(objectMap(value("stackingContexts")));
        }
        /**
         * Global paint order index, which is determined by the stacking order of the nodes. Nodes that are painted together will have the same index. Only provided if includePaintOrder in captureSnapshot was true.
         * @return the protocol field value
         */
        @Nullable public java.util.List<Long> paintOrders() {
            return list(value("paintOrders"), element0 -> numberAsLong(element0));
        }
        /**
         * The offset rect of nodes. Only available when includeDOMRects is set to true
         * @return the protocol field value
         */
        @Nullable public java.util.List<java.util.List<Double>> offsetRects() {
            return list(value("offsetRects"), element0 -> list(element0, element1 -> numberAsDouble(element1)));
        }
        /**
         * The scroll rect of nodes. Only available when includeDOMRects is set to true
         * @return the protocol field value
         */
        @Nullable public java.util.List<java.util.List<Double>> scrollRects() {
            return list(value("scrollRects"), element0 -> list(element0, element1 -> numberAsDouble(element1)));
        }
        /**
         * The client rect of nodes. Only available when includeDOMRects is set to true
         * @return the protocol field value
         */
        @Nullable public java.util.List<java.util.List<Double>> clientRects() {
            return list(value("clientRects"), element0 -> list(element0, element1 -> numberAsDouble(element1)));
        }
        /**
         * The list of background colors that are blended with colors of overlapping elements.
         * <p><b>Experimental:</b> this part of CDP may change without notice.
         * @return the protocol field value
         */
        @Nullable public java.util.List<Long> blendedBackgroundColors() {
            return list(value("blendedBackgroundColors"), element0 -> numberAsLong(element0));
        }
        /**
         * The list of computed text opacities.
         * <p><b>Experimental:</b> this part of CDP may change without notice.
         * @return the protocol field value
         */
        @Nullable public java.util.List<Double> textColorOpacities() {
            return list(value("textColorOpacities"), element0 -> numberAsDouble(element0));
        }
        public static final class Builder {
            private final Map<String, Object> values = new LinkedHashMap<>();
            /**
             * Index of the corresponding node in the {@code NodeTreeSnapshot} array returned by {@code captureSnapshot}.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder nodeIndex(@Nullable java.util.List<Long> value) {
                if (value == null) values.remove("nodeIndex");
                else values.put("nodeIndex", jsonValue(value));
                return this;
            }
            /**
             * Array of indexes specifying computed style strings, filtered according to the {@code computedStyles} parameter passed to {@code captureSnapshot}.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder styles(@Nullable java.util.List<java.util.List<Long>> value) {
                if (value == null) values.remove("styles");
                else values.put("styles", jsonValue(value));
                return this;
            }
            /**
             * The absolute position bounding box.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder bounds(@Nullable java.util.List<java.util.List<Double>> value) {
                if (value == null) values.remove("bounds");
                else values.put("bounds", jsonValue(value));
                return this;
            }
            /**
             * Contents of the LayoutText, if any.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder text(@Nullable java.util.List<Long> value) {
                if (value == null) values.remove("text");
                else values.put("text", jsonValue(value));
                return this;
            }
            /**
             * Stacking context information.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder stackingContexts(@Nullable DOMSnapshot.RareBooleanData value) {
                if (value == null) values.remove("stackingContexts");
                else values.put("stackingContexts", jsonValue(value));
                return this;
            }
            /**
             * Global paint order index, which is determined by the stacking order of the nodes. Nodes that are painted together will have the same index. Only provided if includePaintOrder in captureSnapshot was true.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder paintOrders(@Nullable java.util.List<Long> value) {
                if (value == null) values.remove("paintOrders");
                else values.put("paintOrders", jsonValue(value));
                return this;
            }
            /**
             * The offset rect of nodes. Only available when includeDOMRects is set to true
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder offsetRects(@Nullable java.util.List<java.util.List<Double>> value) {
                if (value == null) values.remove("offsetRects");
                else values.put("offsetRects", jsonValue(value));
                return this;
            }
            /**
             * The scroll rect of nodes. Only available when includeDOMRects is set to true
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder scrollRects(@Nullable java.util.List<java.util.List<Double>> value) {
                if (value == null) values.remove("scrollRects");
                else values.put("scrollRects", jsonValue(value));
                return this;
            }
            /**
             * The client rect of nodes. Only available when includeDOMRects is set to true
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder clientRects(@Nullable java.util.List<java.util.List<Double>> value) {
                if (value == null) values.remove("clientRects");
                else values.put("clientRects", jsonValue(value));
                return this;
            }
            /**
             * The list of background colors that are blended with colors of overlapping elements.
             * <p><b>Experimental:</b> this part of CDP may change without notice.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder blendedBackgroundColors(@Nullable java.util.List<Long> value) {
                if (value == null) values.remove("blendedBackgroundColors");
                else values.put("blendedBackgroundColors", jsonValue(value));
                return this;
            }
            /**
             * The list of computed text opacities.
             * <p><b>Experimental:</b> this part of CDP may change without notice.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder textColorOpacities(@Nullable java.util.List<Double> value) {
                if (value == null) values.remove("textColorOpacities");
                else values.put("textColorOpacities", jsonValue(value));
                return this;
            }
            public LayoutTreeSnapshot build() {
                if (!values.containsKey("nodeIndex")) throw new IllegalStateException("Missing required CDP field: nodeIndex");
                if (!values.containsKey("styles")) throw new IllegalStateException("Missing required CDP field: styles");
                if (!values.containsKey("bounds")) throw new IllegalStateException("Missing required CDP field: bounds");
                if (!values.containsKey("text")) throw new IllegalStateException("Missing required CDP field: text");
                if (!values.containsKey("stackingContexts")) throw new IllegalStateException("Missing required CDP field: stackingContexts");
                return new LayoutTreeSnapshot(values);
            }
        }
    }
    /**
     * Table of details of the post layout rendered text positions. The exact layout should not be regarded as stable and may change between versions.
     */
    public static final class TextBoxSnapshot extends CdpObject {
        private TextBoxSnapshot(Map<String, Object> values) { super(values); }
        @Nullable public static TextBoxSnapshot fromMap(@Nullable Map<String, Object> values) {
            return values == null ? null : new TextBoxSnapshot(values);
        }
        public static Builder builder() { return new Builder(); }
        /**
         * Index of the layout tree node that owns this box collection.
         * @return the protocol field value
         */
        @Nullable public java.util.List<Long> layoutIndex() {
            return list(value("layoutIndex"), element0 -> numberAsLong(element0));
        }
        /**
         * The absolute position bounding box.
         * @return the protocol field value
         */
        @Nullable public java.util.List<java.util.List<Double>> bounds() {
            return list(value("bounds"), element0 -> list(element0, element1 -> numberAsDouble(element1)));
        }
        /**
         * The starting index in characters, for this post layout textbox substring. Characters that would be represented as a surrogate pair in UTF-16 have length 2.
         * @return the protocol field value
         */
        @Nullable public java.util.List<Long> start() {
            return list(value("start"), element0 -> numberAsLong(element0));
        }
        /**
         * The number of characters in this post layout textbox substring. Characters that would be represented as a surrogate pair in UTF-16 have length 2.
         * @return the protocol field value
         */
        @Nullable public java.util.List<Long> length() {
            return list(value("length"), element0 -> numberAsLong(element0));
        }
        public static final class Builder {
            private final Map<String, Object> values = new LinkedHashMap<>();
            /**
             * Index of the layout tree node that owns this box collection.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder layoutIndex(@Nullable java.util.List<Long> value) {
                if (value == null) values.remove("layoutIndex");
                else values.put("layoutIndex", jsonValue(value));
                return this;
            }
            /**
             * The absolute position bounding box.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder bounds(@Nullable java.util.List<java.util.List<Double>> value) {
                if (value == null) values.remove("bounds");
                else values.put("bounds", jsonValue(value));
                return this;
            }
            /**
             * The starting index in characters, for this post layout textbox substring. Characters that would be represented as a surrogate pair in UTF-16 have length 2.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder start(@Nullable java.util.List<Long> value) {
                if (value == null) values.remove("start");
                else values.put("start", jsonValue(value));
                return this;
            }
            /**
             * The number of characters in this post layout textbox substring. Characters that would be represented as a surrogate pair in UTF-16 have length 2.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder length(@Nullable java.util.List<Long> value) {
                if (value == null) values.remove("length");
                else values.put("length", jsonValue(value));
                return this;
            }
            public TextBoxSnapshot build() {
                if (!values.containsKey("layoutIndex")) throw new IllegalStateException("Missing required CDP field: layoutIndex");
                if (!values.containsKey("bounds")) throw new IllegalStateException("Missing required CDP field: bounds");
                if (!values.containsKey("start")) throw new IllegalStateException("Missing required CDP field: start");
                if (!values.containsKey("length")) throw new IllegalStateException("Missing required CDP field: length");
                return new TextBoxSnapshot(values);
            }
        }
    }
    /**
     * Disables DOM snapshot agent for the given page.
     */
    public static final class DisableParams extends CdpObject {
        private DisableParams(Map<String, Object> values) { super(values); }
        @Nullable public static DisableParams fromMap(@Nullable Map<String, Object> values) {
            return values == null ? null : new DisableParams(values);
        }
        public static Builder builder() { return new Builder(); }
        public static final class Builder {
            private final Map<String, Object> values = new LinkedHashMap<>();
            public DisableParams build() {
                return new DisableParams(values);
            }
        }
    }
    /**
     * Disables DOM snapshot agent for the given page.
     */
    public static final class DisableResult extends CdpObject {
        private DisableResult(Map<String, Object> values) { super(values); }
        @Nullable public static DisableResult fromMap(@Nullable Map<String, Object> values) {
            return values == null ? null : new DisableResult(values);
        }
        public static Builder builder() { return new Builder(); }
        public static final class Builder {
            private final Map<String, Object> values = new LinkedHashMap<>();
            public DisableResult build() {
                return new DisableResult(values);
            }
        }
    }
    /**
     * Enables DOM snapshot agent for the given page.
     */
    public static final class EnableParams extends CdpObject {
        private EnableParams(Map<String, Object> values) { super(values); }
        @Nullable public static EnableParams fromMap(@Nullable Map<String, Object> values) {
            return values == null ? null : new EnableParams(values);
        }
        public static Builder builder() { return new Builder(); }
        public static final class Builder {
            private final Map<String, Object> values = new LinkedHashMap<>();
            public EnableParams build() {
                return new EnableParams(values);
            }
        }
    }
    /**
     * Enables DOM snapshot agent for the given page.
     */
    public static final class EnableResult extends CdpObject {
        private EnableResult(Map<String, Object> values) { super(values); }
        @Nullable public static EnableResult fromMap(@Nullable Map<String, Object> values) {
            return values == null ? null : new EnableResult(values);
        }
        public static Builder builder() { return new Builder(); }
        public static final class Builder {
            private final Map<String, Object> values = new LinkedHashMap<>();
            public EnableResult build() {
                return new EnableResult(values);
            }
        }
    }
    /**
     * Returns a document snapshot, including the full DOM tree of the root node (including iframes, template contents, and imported documents) in a flattened array, as well as layout and white-listed computed style information for the nodes. Shadow DOM in the returned DOM tree is flattened.
     * @deprecated Deprecated by the Chromium DevTools Protocol.
     */
    @Deprecated
    public static final class GetSnapshotParams extends CdpObject {
        private GetSnapshotParams(Map<String, Object> values) { super(values); }
        @Nullable public static GetSnapshotParams fromMap(@Nullable Map<String, Object> values) {
            return values == null ? null : new GetSnapshotParams(values);
        }
        public static Builder builder() { return new Builder(); }
        /**
         * Whitelist of computed styles to return.
         * @return the protocol field value
         */
        @Nullable public java.util.List<String> computedStyleWhitelist() {
            return list(value("computedStyleWhitelist"), element0 -> (String) element0);
        }
        /**
         * Whether or not to retrieve details of DOM listeners (default false).
         * @return the protocol field value
         */
        @Nullable public Boolean includeEventListeners() {
            return (Boolean) value("includeEventListeners");
        }
        /**
         * Whether to determine and include the paint order index of LayoutTreeNodes (default false).
         * @return the protocol field value
         */
        @Nullable public Boolean includePaintOrder() {
            return (Boolean) value("includePaintOrder");
        }
        /**
         * Whether to include UA shadow tree in the snapshot (default false).
         * @return the protocol field value
         */
        @Nullable public Boolean includeUserAgentShadowTree() {
            return (Boolean) value("includeUserAgentShadowTree");
        }
        public static final class Builder {
            private final Map<String, Object> values = new LinkedHashMap<>();
            /**
             * Whitelist of computed styles to return.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder computedStyleWhitelist(@Nullable java.util.List<String> value) {
                if (value == null) values.remove("computedStyleWhitelist");
                else values.put("computedStyleWhitelist", jsonValue(value));
                return this;
            }
            /**
             * Whether or not to retrieve details of DOM listeners (default false).
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder includeEventListeners(@Nullable Boolean value) {
                if (value == null) values.remove("includeEventListeners");
                else values.put("includeEventListeners", jsonValue(value));
                return this;
            }
            /**
             * Whether to determine and include the paint order index of LayoutTreeNodes (default false).
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder includePaintOrder(@Nullable Boolean value) {
                if (value == null) values.remove("includePaintOrder");
                else values.put("includePaintOrder", jsonValue(value));
                return this;
            }
            /**
             * Whether to include UA shadow tree in the snapshot (default false).
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder includeUserAgentShadowTree(@Nullable Boolean value) {
                if (value == null) values.remove("includeUserAgentShadowTree");
                else values.put("includeUserAgentShadowTree", jsonValue(value));
                return this;
            }
            public GetSnapshotParams build() {
                if (!values.containsKey("computedStyleWhitelist")) throw new IllegalStateException("Missing required CDP field: computedStyleWhitelist");
                return new GetSnapshotParams(values);
            }
        }
    }
    /**
     * Returns a document snapshot, including the full DOM tree of the root node (including iframes, template contents, and imported documents) in a flattened array, as well as layout and white-listed computed style information for the nodes. Shadow DOM in the returned DOM tree is flattened.
     * @deprecated Deprecated by the Chromium DevTools Protocol.
     */
    @Deprecated
    public static final class GetSnapshotResult extends CdpObject {
        private GetSnapshotResult(Map<String, Object> values) { super(values); }
        @Nullable public static GetSnapshotResult fromMap(@Nullable Map<String, Object> values) {
            return values == null ? null : new GetSnapshotResult(values);
        }
        public static Builder builder() { return new Builder(); }
        /**
         * The nodes in the DOM tree. The DOMNode at index 0 corresponds to the root document.
         * @return the protocol field value
         */
        @Nullable public java.util.List<DOMSnapshot.DOMNode> domNodes() {
            return list(value("domNodes"), element0 -> DOMSnapshot.DOMNode.fromMap(objectMap(element0)));
        }
        /**
         * The nodes in the layout tree.
         * @return the protocol field value
         */
        @Nullable public java.util.List<DOMSnapshot.LayoutTreeNode> layoutTreeNodes() {
            return list(value("layoutTreeNodes"), element0 -> DOMSnapshot.LayoutTreeNode.fromMap(objectMap(element0)));
        }
        /**
         * Whitelisted ComputedStyle properties for each node in the layout tree.
         * @return the protocol field value
         */
        @Nullable public java.util.List<DOMSnapshot.ComputedStyle> computedStyles() {
            return list(value("computedStyles"), element0 -> DOMSnapshot.ComputedStyle.fromMap(objectMap(element0)));
        }
        public static final class Builder {
            private final Map<String, Object> values = new LinkedHashMap<>();
            /**
             * The nodes in the DOM tree. The DOMNode at index 0 corresponds to the root document.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder domNodes(@Nullable java.util.List<DOMSnapshot.DOMNode> value) {
                if (value == null) values.remove("domNodes");
                else values.put("domNodes", jsonValue(value));
                return this;
            }
            /**
             * The nodes in the layout tree.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder layoutTreeNodes(@Nullable java.util.List<DOMSnapshot.LayoutTreeNode> value) {
                if (value == null) values.remove("layoutTreeNodes");
                else values.put("layoutTreeNodes", jsonValue(value));
                return this;
            }
            /**
             * Whitelisted ComputedStyle properties for each node in the layout tree.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder computedStyles(@Nullable java.util.List<DOMSnapshot.ComputedStyle> value) {
                if (value == null) values.remove("computedStyles");
                else values.put("computedStyles", jsonValue(value));
                return this;
            }
            public GetSnapshotResult build() {
                if (!values.containsKey("domNodes")) throw new IllegalStateException("Missing required CDP field: domNodes");
                if (!values.containsKey("layoutTreeNodes")) throw new IllegalStateException("Missing required CDP field: layoutTreeNodes");
                if (!values.containsKey("computedStyles")) throw new IllegalStateException("Missing required CDP field: computedStyles");
                return new GetSnapshotResult(values);
            }
        }
    }
    /**
     * Returns a document snapshot, including the full DOM tree of the root node (including iframes, template contents, and imported documents) in a flattened array, as well as layout and white-listed computed style information for the nodes. Shadow DOM in the returned DOM tree is flattened.
     */
    public static final class CaptureSnapshotParams extends CdpObject {
        private CaptureSnapshotParams(Map<String, Object> values) { super(values); }
        @Nullable public static CaptureSnapshotParams fromMap(@Nullable Map<String, Object> values) {
            return values == null ? null : new CaptureSnapshotParams(values);
        }
        public static Builder builder() { return new Builder(); }
        /**
         * Whitelist of computed styles to return.
         * @return the protocol field value
         */
        @Nullable public java.util.List<String> computedStyles() {
            return list(value("computedStyles"), element0 -> (String) element0);
        }
        /**
         * Whether to include layout object paint orders into the snapshot.
         * @return the protocol field value
         */
        @Nullable public Boolean includePaintOrder() {
            return (Boolean) value("includePaintOrder");
        }
        /**
         * Whether to include DOM rectangles (offsetRects, clientRects, scrollRects) into the snapshot
         * @return the protocol field value
         */
        @Nullable public Boolean includeDOMRects() {
            return (Boolean) value("includeDOMRects");
        }
        /**
         * Whether to include blended background colors in the snapshot (default: false). Blended background color is achieved by blending background colors of all elements that overlap with the current element.
         * <p><b>Experimental:</b> this part of CDP may change without notice.
         * @return the protocol field value
         */
        @Nullable public Boolean includeBlendedBackgroundColors() {
            return (Boolean) value("includeBlendedBackgroundColors");
        }
        /**
         * Whether to include text color opacity in the snapshot (default: false). An element might have the opacity property set that affects the text color of the element. The final text color opacity is computed based on the opacity of all overlapping elements.
         * <p><b>Experimental:</b> this part of CDP may change without notice.
         * @return the protocol field value
         */
        @Nullable public Boolean includeTextColorOpacities() {
            return (Boolean) value("includeTextColorOpacities");
        }
        public static final class Builder {
            private final Map<String, Object> values = new LinkedHashMap<>();
            /**
             * Whitelist of computed styles to return.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder computedStyles(@Nullable java.util.List<String> value) {
                if (value == null) values.remove("computedStyles");
                else values.put("computedStyles", jsonValue(value));
                return this;
            }
            /**
             * Whether to include layout object paint orders into the snapshot.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder includePaintOrder(@Nullable Boolean value) {
                if (value == null) values.remove("includePaintOrder");
                else values.put("includePaintOrder", jsonValue(value));
                return this;
            }
            /**
             * Whether to include DOM rectangles (offsetRects, clientRects, scrollRects) into the snapshot
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder includeDOMRects(@Nullable Boolean value) {
                if (value == null) values.remove("includeDOMRects");
                else values.put("includeDOMRects", jsonValue(value));
                return this;
            }
            /**
             * Whether to include blended background colors in the snapshot (default: false). Blended background color is achieved by blending background colors of all elements that overlap with the current element.
             * <p><b>Experimental:</b> this part of CDP may change without notice.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder includeBlendedBackgroundColors(@Nullable Boolean value) {
                if (value == null) values.remove("includeBlendedBackgroundColors");
                else values.put("includeBlendedBackgroundColors", jsonValue(value));
                return this;
            }
            /**
             * Whether to include text color opacity in the snapshot (default: false). An element might have the opacity property set that affects the text color of the element. The final text color opacity is computed based on the opacity of all overlapping elements.
             * <p><b>Experimental:</b> this part of CDP may change without notice.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder includeTextColorOpacities(@Nullable Boolean value) {
                if (value == null) values.remove("includeTextColorOpacities");
                else values.put("includeTextColorOpacities", jsonValue(value));
                return this;
            }
            public CaptureSnapshotParams build() {
                if (!values.containsKey("computedStyles")) throw new IllegalStateException("Missing required CDP field: computedStyles");
                return new CaptureSnapshotParams(values);
            }
        }
    }
    /**
     * Returns a document snapshot, including the full DOM tree of the root node (including iframes, template contents, and imported documents) in a flattened array, as well as layout and white-listed computed style information for the nodes. Shadow DOM in the returned DOM tree is flattened.
     */
    public static final class CaptureSnapshotResult extends CdpObject {
        private CaptureSnapshotResult(Map<String, Object> values) { super(values); }
        @Nullable public static CaptureSnapshotResult fromMap(@Nullable Map<String, Object> values) {
            return values == null ? null : new CaptureSnapshotResult(values);
        }
        public static Builder builder() { return new Builder(); }
        /**
         * The nodes in the DOM tree. The DOMNode at index 0 corresponds to the root document.
         * @return the protocol field value
         */
        @Nullable public java.util.List<DOMSnapshot.DocumentSnapshot> documents() {
            return list(value("documents"), element0 -> DOMSnapshot.DocumentSnapshot.fromMap(objectMap(element0)));
        }
        /**
         * Shared string table that all string properties refer to with indexes.
         * @return the protocol field value
         */
        @Nullable public java.util.List<String> strings() {
            return list(value("strings"), element0 -> (String) element0);
        }
        public static final class Builder {
            private final Map<String, Object> values = new LinkedHashMap<>();
            /**
             * The nodes in the DOM tree. The DOMNode at index 0 corresponds to the root document.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder documents(@Nullable java.util.List<DOMSnapshot.DocumentSnapshot> value) {
                if (value == null) values.remove("documents");
                else values.put("documents", jsonValue(value));
                return this;
            }
            /**
             * Shared string table that all string properties refer to with indexes.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder strings(@Nullable java.util.List<String> value) {
                if (value == null) values.remove("strings");
                else values.put("strings", jsonValue(value));
                return this;
            }
            public CaptureSnapshotResult build() {
                if (!values.containsKey("documents")) throw new IllegalStateException("Missing required CDP field: documents");
                if (!values.containsKey("strings")) throw new IllegalStateException("Missing required CDP field: strings");
                return new CaptureSnapshotResult(values);
            }
        }
    }
    public static final class Client {
        private final CdpClient client;
        public Client(CdpClient client) { this.client = client; }
        /**
         * Disables DOM snapshot agent for the given page.
         * @return a stage completing with the command result
         */
        public CompletionStage<DisableResult> disable() {
            return client.call("DOMSnapshot.disable", null, DisableResult::fromMap);
        }
        /**
         * Enables DOM snapshot agent for the given page.
         * @return a stage completing with the command result
         */
        public CompletionStage<EnableResult> enable() {
            return client.call("DOMSnapshot.enable", null, EnableResult::fromMap);
        }
        /**
         * Returns a document snapshot, including the full DOM tree of the root node (including iframes, template contents, and imported documents) in a flattened array, as well as layout and white-listed computed style information for the nodes. Shadow DOM in the returned DOM tree is flattened.
         * @param params command parameters
         * @return a stage completing with the command result
         * @deprecated Deprecated by the Chromium DevTools Protocol.
         */
        @Deprecated
        public CompletionStage<GetSnapshotResult> getSnapshot(GetSnapshotParams params) {
            return client.call("DOMSnapshot.getSnapshot", params, GetSnapshotResult::fromMap);
        }
        /**
         * Returns a document snapshot, including the full DOM tree of the root node (including iframes, template contents, and imported documents) in a flattened array, as well as layout and white-listed computed style information for the nodes. Shadow DOM in the returned DOM tree is flattened.
         * @param params command parameters
         * @return a stage completing with the command result
         */
        public CompletionStage<CaptureSnapshotResult> captureSnapshot(CaptureSnapshotParams params) {
            return client.call("DOMSnapshot.captureSnapshot", params, CaptureSnapshotResult::fromMap);
        }
    }
}
