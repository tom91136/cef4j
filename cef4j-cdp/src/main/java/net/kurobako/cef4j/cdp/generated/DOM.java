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
 * This domain exposes DOM read/write operations. Each DOM Node is represented with its mirror object that has an {@code id}. This {@code id} can be used to get additional information on the Node, resolve it into the JavaScript object wrapper, etc. It is important that client receives DOM events only for the nodes that are known to the client. Backend keeps track of the nodes that were sent to the client and never sends the same node twice. It is client&#x27;s responsibility to collect information about the nodes that were sent to the client. Note that {@code iframe} owner elements will return corresponding document elements as their child nodes.
 * @see <a href="https://chromium.googlesource.com/chromium/src/+/refs/tags/146.0.7680.165/third_party/blink/public/devtools_protocol/domains/DOM.pdl">Pinned protocol source</a>
 */
@SuppressWarnings({"EscapedEntity", "JavaLangClash", "MissingSummary", "UnusedMethod"})
public final class DOM {
    private DOM() {}
    @Nullable private static Long numberAsLong(@Nullable Object value) { return value == null ? null : ((Number) value).longValue(); }
    @Nullable private static Double numberAsDouble(@Nullable Object value) { return value == null ? null : ((Number) value).doubleValue(); }
    /**
     * Backend node with a friendly name.
     */
    public static final class BackendNode extends CdpObject {
        private BackendNode(Map<String, Object> values) { super(values); }
        @Nullable public static BackendNode fromMap(@Nullable Map<String, Object> values) {
            return values == null ? null : new BackendNode(values);
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
         * Returns the backendNodeId field.
         * @return the protocol field value
         */
        @Nullable public Long backendNodeId() {
            return numberAsLong(value("backendNodeId"));
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
             * Sets the backendNodeId field.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder backendNodeId(@Nullable Long value) {
                if (value == null) values.remove("backendNodeId");
                else values.put("backendNodeId", jsonValue(value));
                return this;
            }
            public BackendNode build() {
                if (!values.containsKey("nodeType")) throw new IllegalStateException("Missing required CDP field: nodeType");
                if (!values.containsKey("nodeName")) throw new IllegalStateException("Missing required CDP field: nodeName");
                if (!values.containsKey("backendNodeId")) throw new IllegalStateException("Missing required CDP field: backendNodeId");
                return new BackendNode(values);
            }
        }
    }
    /**
     * Pseudo element type.
     */
    public static final class PseudoType {
        private PseudoType() {}
        public static final String FIRST_LINE = "first-line";
        public static final String FIRST_LETTER = "first-letter";
        public static final String CHECKMARK = "checkmark";
        public static final String BEFORE = "before";
        public static final String AFTER = "after";
        public static final String PICKER_ICON = "picker-icon";
        public static final String INTEREST_HINT = "interest-hint";
        public static final String MARKER = "marker";
        public static final String BACKDROP = "backdrop";
        public static final String COLUMN = "column";
        public static final String SELECTION = "selection";
        public static final String SEARCH_TEXT = "search-text";
        public static final String TARGET_TEXT = "target-text";
        public static final String SPELLING_ERROR = "spelling-error";
        public static final String GRAMMAR_ERROR = "grammar-error";
        public static final String HIGHLIGHT = "highlight";
        public static final String FIRST_LINE_INHERITED = "first-line-inherited";
        public static final String SCROLL_MARKER = "scroll-marker";
        public static final String SCROLL_MARKER_GROUP = "scroll-marker-group";
        public static final String SCROLL_BUTTON = "scroll-button";
        public static final String SCROLLBAR = "scrollbar";
        public static final String SCROLLBAR_THUMB = "scrollbar-thumb";
        public static final String SCROLLBAR_BUTTON = "scrollbar-button";
        public static final String SCROLLBAR_TRACK = "scrollbar-track";
        public static final String SCROLLBAR_TRACK_PIECE = "scrollbar-track-piece";
        public static final String SCROLLBAR_CORNER = "scrollbar-corner";
        public static final String RESIZER = "resizer";
        public static final String INPUT_LIST_BUTTON = "input-list-button";
        public static final String VIEW_TRANSITION = "view-transition";
        public static final String VIEW_TRANSITION_GROUP = "view-transition-group";
        public static final String VIEW_TRANSITION_IMAGE_PAIR = "view-transition-image-pair";
        public static final String VIEW_TRANSITION_GROUP_CHILDREN = "view-transition-group-children";
        public static final String VIEW_TRANSITION_OLD = "view-transition-old";
        public static final String VIEW_TRANSITION_NEW = "view-transition-new";
        public static final String PLACEHOLDER = "placeholder";
        public static final String FILE_SELECTOR_BUTTON = "file-selector-button";
        public static final String DETAILS_CONTENT = "details-content";
        public static final String PICKER = "picker";
        public static final String PERMISSION_ICON = "permission-icon";
        public static final String OVERSCROLL_AREA_PARENT = "overscroll-area-parent";
    }
    /**
     * Shadow root type.
     */
    public static final class ShadowRootType {
        private ShadowRootType() {}
        public static final String USER_AGENT = "user-agent";
        public static final String OPEN = "open";
        public static final String CLOSED = "closed";
    }
    /**
     * Document compatibility mode.
     */
    public static final class CompatibilityMode {
        private CompatibilityMode() {}
        public static final String QUIRKSMODE = "QuirksMode";
        public static final String LIMITEDQUIRKSMODE = "LimitedQuirksMode";
        public static final String NOQUIRKSMODE = "NoQuirksMode";
    }
    /**
     * ContainerSelector physical axes
     */
    public static final class PhysicalAxes {
        private PhysicalAxes() {}
        public static final String HORIZONTAL = "Horizontal";
        public static final String VERTICAL = "Vertical";
        public static final String BOTH = "Both";
    }
    /**
     * ContainerSelector logical axes
     */
    public static final class LogicalAxes {
        private LogicalAxes() {}
        public static final String INLINE = "Inline";
        public static final String BLOCK = "Block";
        public static final String BOTH = "Both";
    }
    /**
     * Physical scroll orientation
     */
    public static final class ScrollOrientation {
        private ScrollOrientation() {}
        public static final String HORIZONTAL = "horizontal";
        public static final String VERTICAL = "vertical";
    }
    /**
     * DOM interaction is implemented in terms of mirror objects that represent the actual DOM nodes. DOMNode is a base node mirror type.
     */
    public static final class Node extends CdpObject {
        private Node(Map<String, Object> values) { super(values); }
        @Nullable public static Node fromMap(@Nullable Map<String, Object> values) {
            return values == null ? null : new Node(values);
        }
        public static Builder builder() { return new Builder(); }
        /**
         * Node identifier that is passed into the rest of the DOM messages as the {@code nodeId}. Backend will only push node with given {@code id} once. It is aware of all requested nodes and will only fire DOM events for nodes known to the client.
         * @return the protocol field value
         */
        @Nullable public Long nodeId() {
            return numberAsLong(value("nodeId"));
        }
        /**
         * The id of the parent node if any.
         * @return the protocol field value
         */
        @Nullable public Long parentId() {
            return numberAsLong(value("parentId"));
        }
        /**
         * The BackendNodeId for this node.
         * @return the protocol field value
         */
        @Nullable public Long backendNodeId() {
            return numberAsLong(value("backendNodeId"));
        }
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
         * {@code Node}&#x27;s localName.
         * @return the protocol field value
         */
        @Nullable public String localName() {
            return (String) value("localName");
        }
        /**
         * {@code Node}&#x27;s nodeValue.
         * @return the protocol field value
         */
        @Nullable public String nodeValue() {
            return (String) value("nodeValue");
        }
        /**
         * Child count for {@code Container} nodes.
         * @return the protocol field value
         */
        @Nullable public Long childNodeCount() {
            return numberAsLong(value("childNodeCount"));
        }
        /**
         * Child nodes of this node when requested with children.
         * @return the protocol field value
         */
        @Nullable public java.util.List<DOM.Node> children() {
            return list(value("children"), element0 -> DOM.Node.fromMap(objectMap(element0)));
        }
        /**
         * Attributes of the {@code Element} node in the form of flat array {@code [name1, value1, name2, value2]}.
         * @return the protocol field value
         */
        @Nullable public java.util.List<String> attributes() {
            return list(value("attributes"), element0 -> (String) element0);
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
         * {@code DocumentType}&#x27;s publicId.
         * @return the protocol field value
         */
        @Nullable public String publicId() {
            return (String) value("publicId");
        }
        /**
         * {@code DocumentType}&#x27;s systemId.
         * @return the protocol field value
         */
        @Nullable public String systemId() {
            return (String) value("systemId");
        }
        /**
         * {@code DocumentType}&#x27;s internalSubset.
         * @return the protocol field value
         */
        @Nullable public String internalSubset() {
            return (String) value("internalSubset");
        }
        /**
         * {@code Document}&#x27;s XML version in case of XML documents.
         * @return the protocol field value
         */
        @Nullable public String xmlVersion() {
            return (String) value("xmlVersion");
        }
        /**
         * {@code Attr}&#x27;s name.
         * @return the protocol field value
         */
        @Nullable public String name() {
            return (String) value("name");
        }
        /**
         * {@code Attr}&#x27;s value.
         * @return the protocol field value
         */
        @Nullable public String value() {
            return (String) value("value");
        }
        /**
         * Pseudo element type for this node.
         * @return the protocol field value
         */
        @Nullable public String pseudoType() {
            return (String) value("pseudoType");
        }
        /**
         * Pseudo element identifier for this node. Only present if there is a valid pseudoType.
         * @return the protocol field value
         */
        @Nullable public String pseudoIdentifier() {
            return (String) value("pseudoIdentifier");
        }
        /**
         * Shadow root type.
         * @return the protocol field value
         */
        @Nullable public String shadowRootType() {
            return (String) value("shadowRootType");
        }
        /**
         * Frame ID for frame owner elements.
         * @return the protocol field value
         */
        @Nullable public String frameId() {
            return (String) value("frameId");
        }
        /**
         * Content document for frame owner elements.
         * @return the protocol field value
         */
        @Nullable public DOM.Node contentDocument() {
            return DOM.Node.fromMap(objectMap(value("contentDocument")));
        }
        /**
         * Shadow root list for given element host.
         * @return the protocol field value
         */
        @Nullable public java.util.List<DOM.Node> shadowRoots() {
            return list(value("shadowRoots"), element0 -> DOM.Node.fromMap(objectMap(element0)));
        }
        /**
         * Content document fragment for template elements.
         * @return the protocol field value
         */
        @Nullable public DOM.Node templateContent() {
            return DOM.Node.fromMap(objectMap(value("templateContent")));
        }
        /**
         * Pseudo elements associated with this node.
         * @return the protocol field value
         */
        @Nullable public java.util.List<DOM.Node> pseudoElements() {
            return list(value("pseudoElements"), element0 -> DOM.Node.fromMap(objectMap(element0)));
        }
        /**
         * Deprecated, as the HTML Imports API has been removed (crbug.com/937746). This property used to return the imported document for the HTMLImport links. The property is always undefined now.
         * @return the protocol field value
         * @deprecated Deprecated by the Chromium DevTools Protocol.
         */
        @Deprecated
        @Nullable public DOM.Node importedDocument() {
            return DOM.Node.fromMap(objectMap(value("importedDocument")));
        }
        /**
         * Distributed nodes for given insertion point.
         * @return the protocol field value
         */
        @Nullable public java.util.List<DOM.BackendNode> distributedNodes() {
            return list(value("distributedNodes"), element0 -> DOM.BackendNode.fromMap(objectMap(element0)));
        }
        /**
         * Whether the node is SVG.
         * @return the protocol field value
         */
        @Nullable public Boolean isSVG() {
            return (Boolean) value("isSVG");
        }
        /**
         * Returns the compatibilityMode field.
         * @return the protocol field value
         */
        @Nullable public String compatibilityMode() {
            return (String) value("compatibilityMode");
        }
        /**
         * Returns the assignedSlot field.
         * @return the protocol field value
         */
        @Nullable public DOM.BackendNode assignedSlot() {
            return DOM.BackendNode.fromMap(objectMap(value("assignedSlot")));
        }
        /**
         * Returns the isScrollable field.
         * <p><b>Experimental:</b> this part of CDP may change without notice.
         * @return the protocol field value
         */
        @Nullable public Boolean isScrollable() {
            return (Boolean) value("isScrollable");
        }
        /**
         * Returns the affectedByStartingStyles field.
         * <p><b>Experimental:</b> this part of CDP may change without notice.
         * @return the protocol field value
         */
        @Nullable public Boolean affectedByStartingStyles() {
            return (Boolean) value("affectedByStartingStyles");
        }
        /**
         * Returns the adoptedStyleSheets field.
         * <p><b>Experimental:</b> this part of CDP may change without notice.
         * @return the protocol field value
         */
        @Nullable public java.util.List<String> adoptedStyleSheets() {
            return list(value("adoptedStyleSheets"), element0 -> (String) element0);
        }
        public static final class Builder {
            private final Map<String, Object> values = new LinkedHashMap<>();
            /**
             * Node identifier that is passed into the rest of the DOM messages as the {@code nodeId}. Backend will only push node with given {@code id} once. It is aware of all requested nodes and will only fire DOM events for nodes known to the client.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder nodeId(@Nullable Long value) {
                if (value == null) values.remove("nodeId");
                else values.put("nodeId", jsonValue(value));
                return this;
            }
            /**
             * The id of the parent node if any.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder parentId(@Nullable Long value) {
                if (value == null) values.remove("parentId");
                else values.put("parentId", jsonValue(value));
                return this;
            }
            /**
             * The BackendNodeId for this node.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder backendNodeId(@Nullable Long value) {
                if (value == null) values.remove("backendNodeId");
                else values.put("backendNodeId", jsonValue(value));
                return this;
            }
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
             * {@code Node}&#x27;s localName.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder localName(@Nullable String value) {
                if (value == null) values.remove("localName");
                else values.put("localName", jsonValue(value));
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
             * Child count for {@code Container} nodes.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder childNodeCount(@Nullable Long value) {
                if (value == null) values.remove("childNodeCount");
                else values.put("childNodeCount", jsonValue(value));
                return this;
            }
            /**
             * Child nodes of this node when requested with children.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder children(@Nullable java.util.List<DOM.Node> value) {
                if (value == null) values.remove("children");
                else values.put("children", jsonValue(value));
                return this;
            }
            /**
             * Attributes of the {@code Element} node in the form of flat array {@code [name1, value1, name2, value2]}.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder attributes(@Nullable java.util.List<String> value) {
                if (value == null) values.remove("attributes");
                else values.put("attributes", jsonValue(value));
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
             * {@code DocumentType}&#x27;s publicId.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder publicId(@Nullable String value) {
                if (value == null) values.remove("publicId");
                else values.put("publicId", jsonValue(value));
                return this;
            }
            /**
             * {@code DocumentType}&#x27;s systemId.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder systemId(@Nullable String value) {
                if (value == null) values.remove("systemId");
                else values.put("systemId", jsonValue(value));
                return this;
            }
            /**
             * {@code DocumentType}&#x27;s internalSubset.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder internalSubset(@Nullable String value) {
                if (value == null) values.remove("internalSubset");
                else values.put("internalSubset", jsonValue(value));
                return this;
            }
            /**
             * {@code Document}&#x27;s XML version in case of XML documents.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder xmlVersion(@Nullable String value) {
                if (value == null) values.remove("xmlVersion");
                else values.put("xmlVersion", jsonValue(value));
                return this;
            }
            /**
             * {@code Attr}&#x27;s name.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder name(@Nullable String value) {
                if (value == null) values.remove("name");
                else values.put("name", jsonValue(value));
                return this;
            }
            /**
             * {@code Attr}&#x27;s value.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder value(@Nullable String value) {
                if (value == null) values.remove("value");
                else values.put("value", jsonValue(value));
                return this;
            }
            /**
             * Pseudo element type for this node.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder pseudoType(@Nullable String value) {
                if (value == null) values.remove("pseudoType");
                else values.put("pseudoType", jsonValue(value));
                return this;
            }
            /**
             * Pseudo element identifier for this node. Only present if there is a valid pseudoType.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder pseudoIdentifier(@Nullable String value) {
                if (value == null) values.remove("pseudoIdentifier");
                else values.put("pseudoIdentifier", jsonValue(value));
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
             * Frame ID for frame owner elements.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder frameId(@Nullable String value) {
                if (value == null) values.remove("frameId");
                else values.put("frameId", jsonValue(value));
                return this;
            }
            /**
             * Content document for frame owner elements.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder contentDocument(@Nullable DOM.Node value) {
                if (value == null) values.remove("contentDocument");
                else values.put("contentDocument", jsonValue(value));
                return this;
            }
            /**
             * Shadow root list for given element host.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder shadowRoots(@Nullable java.util.List<DOM.Node> value) {
                if (value == null) values.remove("shadowRoots");
                else values.put("shadowRoots", jsonValue(value));
                return this;
            }
            /**
             * Content document fragment for template elements.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder templateContent(@Nullable DOM.Node value) {
                if (value == null) values.remove("templateContent");
                else values.put("templateContent", jsonValue(value));
                return this;
            }
            /**
             * Pseudo elements associated with this node.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder pseudoElements(@Nullable java.util.List<DOM.Node> value) {
                if (value == null) values.remove("pseudoElements");
                else values.put("pseudoElements", jsonValue(value));
                return this;
            }
            /**
             * Deprecated, as the HTML Imports API has been removed (crbug.com/937746). This property used to return the imported document for the HTMLImport links. The property is always undefined now.
             * @param value field value; null removes an optional value
             * @return this builder
             * @deprecated Deprecated by the Chromium DevTools Protocol.
             */
            @Deprecated
            public Builder importedDocument(@Nullable DOM.Node value) {
                if (value == null) values.remove("importedDocument");
                else values.put("importedDocument", jsonValue(value));
                return this;
            }
            /**
             * Distributed nodes for given insertion point.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder distributedNodes(@Nullable java.util.List<DOM.BackendNode> value) {
                if (value == null) values.remove("distributedNodes");
                else values.put("distributedNodes", jsonValue(value));
                return this;
            }
            /**
             * Whether the node is SVG.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder isSVG(@Nullable Boolean value) {
                if (value == null) values.remove("isSVG");
                else values.put("isSVG", jsonValue(value));
                return this;
            }
            /**
             * Sets the compatibilityMode field.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder compatibilityMode(@Nullable String value) {
                if (value == null) values.remove("compatibilityMode");
                else values.put("compatibilityMode", jsonValue(value));
                return this;
            }
            /**
             * Sets the assignedSlot field.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder assignedSlot(@Nullable DOM.BackendNode value) {
                if (value == null) values.remove("assignedSlot");
                else values.put("assignedSlot", jsonValue(value));
                return this;
            }
            /**
             * Sets the isScrollable field.
             * <p><b>Experimental:</b> this part of CDP may change without notice.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder isScrollable(@Nullable Boolean value) {
                if (value == null) values.remove("isScrollable");
                else values.put("isScrollable", jsonValue(value));
                return this;
            }
            /**
             * Sets the affectedByStartingStyles field.
             * <p><b>Experimental:</b> this part of CDP may change without notice.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder affectedByStartingStyles(@Nullable Boolean value) {
                if (value == null) values.remove("affectedByStartingStyles");
                else values.put("affectedByStartingStyles", jsonValue(value));
                return this;
            }
            /**
             * Sets the adoptedStyleSheets field.
             * <p><b>Experimental:</b> this part of CDP may change without notice.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder adoptedStyleSheets(@Nullable java.util.List<String> value) {
                if (value == null) values.remove("adoptedStyleSheets");
                else values.put("adoptedStyleSheets", jsonValue(value));
                return this;
            }
            public Node build() {
                if (!values.containsKey("nodeId")) throw new IllegalStateException("Missing required CDP field: nodeId");
                if (!values.containsKey("backendNodeId")) throw new IllegalStateException("Missing required CDP field: backendNodeId");
                if (!values.containsKey("nodeType")) throw new IllegalStateException("Missing required CDP field: nodeType");
                if (!values.containsKey("nodeName")) throw new IllegalStateException("Missing required CDP field: nodeName");
                if (!values.containsKey("localName")) throw new IllegalStateException("Missing required CDP field: localName");
                if (!values.containsKey("nodeValue")) throw new IllegalStateException("Missing required CDP field: nodeValue");
                return new Node(values);
            }
        }
    }
    /**
     * A structure to hold the top-level node of a detached tree and an array of its retained descendants.
     */
    public static final class DetachedElementInfo extends CdpObject {
        private DetachedElementInfo(Map<String, Object> values) { super(values); }
        @Nullable public static DetachedElementInfo fromMap(@Nullable Map<String, Object> values) {
            return values == null ? null : new DetachedElementInfo(values);
        }
        public static Builder builder() { return new Builder(); }
        /**
         * Returns the treeNode field.
         * @return the protocol field value
         */
        @Nullable public DOM.Node treeNode() {
            return DOM.Node.fromMap(objectMap(value("treeNode")));
        }
        /**
         * Returns the retainedNodeIds field.
         * @return the protocol field value
         */
        @Nullable public java.util.List<Long> retainedNodeIds() {
            return list(value("retainedNodeIds"), element0 -> numberAsLong(element0));
        }
        public static final class Builder {
            private final Map<String, Object> values = new LinkedHashMap<>();
            /**
             * Sets the treeNode field.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder treeNode(@Nullable DOM.Node value) {
                if (value == null) values.remove("treeNode");
                else values.put("treeNode", jsonValue(value));
                return this;
            }
            /**
             * Sets the retainedNodeIds field.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder retainedNodeIds(@Nullable java.util.List<Long> value) {
                if (value == null) values.remove("retainedNodeIds");
                else values.put("retainedNodeIds", jsonValue(value));
                return this;
            }
            public DetachedElementInfo build() {
                if (!values.containsKey("treeNode")) throw new IllegalStateException("Missing required CDP field: treeNode");
                if (!values.containsKey("retainedNodeIds")) throw new IllegalStateException("Missing required CDP field: retainedNodeIds");
                return new DetachedElementInfo(values);
            }
        }
    }
    /**
     * A structure holding an RGBA color.
     */
    public static final class RGBA extends CdpObject {
        private RGBA(Map<String, Object> values) { super(values); }
        @Nullable public static RGBA fromMap(@Nullable Map<String, Object> values) {
            return values == null ? null : new RGBA(values);
        }
        public static Builder builder() { return new Builder(); }
        /**
         * The red component, in the [0-255] range.
         * @return the protocol field value
         */
        @Nullable public Long r() {
            return numberAsLong(value("r"));
        }
        /**
         * The green component, in the [0-255] range.
         * @return the protocol field value
         */
        @Nullable public Long g() {
            return numberAsLong(value("g"));
        }
        /**
         * The blue component, in the [0-255] range.
         * @return the protocol field value
         */
        @Nullable public Long b() {
            return numberAsLong(value("b"));
        }
        /**
         * The alpha component, in the [0-1] range (default: 1).
         * @return the protocol field value
         */
        @Nullable public Double a() {
            return numberAsDouble(value("a"));
        }
        public static final class Builder {
            private final Map<String, Object> values = new LinkedHashMap<>();
            /**
             * The red component, in the [0-255] range.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder r(@Nullable Long value) {
                if (value == null) values.remove("r");
                else values.put("r", jsonValue(value));
                return this;
            }
            /**
             * The green component, in the [0-255] range.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder g(@Nullable Long value) {
                if (value == null) values.remove("g");
                else values.put("g", jsonValue(value));
                return this;
            }
            /**
             * The blue component, in the [0-255] range.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder b(@Nullable Long value) {
                if (value == null) values.remove("b");
                else values.put("b", jsonValue(value));
                return this;
            }
            /**
             * The alpha component, in the [0-1] range (default: 1).
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder a(@Nullable Double value) {
                if (value == null) values.remove("a");
                else values.put("a", jsonValue(value));
                return this;
            }
            public RGBA build() {
                if (!values.containsKey("r")) throw new IllegalStateException("Missing required CDP field: r");
                if (!values.containsKey("g")) throw new IllegalStateException("Missing required CDP field: g");
                if (!values.containsKey("b")) throw new IllegalStateException("Missing required CDP field: b");
                return new RGBA(values);
            }
        }
    }
    /**
     * Box model.
     */
    public static final class BoxModel extends CdpObject {
        private BoxModel(Map<String, Object> values) { super(values); }
        @Nullable public static BoxModel fromMap(@Nullable Map<String, Object> values) {
            return values == null ? null : new BoxModel(values);
        }
        public static Builder builder() { return new Builder(); }
        /**
         * Content box
         * @return the protocol field value
         */
        @Nullable public java.util.List<Double> content() {
            return list(value("content"), element0 -> numberAsDouble(element0));
        }
        /**
         * Padding box
         * @return the protocol field value
         */
        @Nullable public java.util.List<Double> padding() {
            return list(value("padding"), element0 -> numberAsDouble(element0));
        }
        /**
         * Border box
         * @return the protocol field value
         */
        @Nullable public java.util.List<Double> border() {
            return list(value("border"), element0 -> numberAsDouble(element0));
        }
        /**
         * Margin box
         * @return the protocol field value
         */
        @Nullable public java.util.List<Double> margin() {
            return list(value("margin"), element0 -> numberAsDouble(element0));
        }
        /**
         * Node width
         * @return the protocol field value
         */
        @Nullable public Long width() {
            return numberAsLong(value("width"));
        }
        /**
         * Node height
         * @return the protocol field value
         */
        @Nullable public Long height() {
            return numberAsLong(value("height"));
        }
        /**
         * Shape outside coordinates
         * @return the protocol field value
         */
        @Nullable public DOM.ShapeOutsideInfo shapeOutside() {
            return DOM.ShapeOutsideInfo.fromMap(objectMap(value("shapeOutside")));
        }
        public static final class Builder {
            private final Map<String, Object> values = new LinkedHashMap<>();
            /**
             * Content box
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder content(@Nullable java.util.List<Double> value) {
                if (value == null) values.remove("content");
                else values.put("content", jsonValue(value));
                return this;
            }
            /**
             * Padding box
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder padding(@Nullable java.util.List<Double> value) {
                if (value == null) values.remove("padding");
                else values.put("padding", jsonValue(value));
                return this;
            }
            /**
             * Border box
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder border(@Nullable java.util.List<Double> value) {
                if (value == null) values.remove("border");
                else values.put("border", jsonValue(value));
                return this;
            }
            /**
             * Margin box
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder margin(@Nullable java.util.List<Double> value) {
                if (value == null) values.remove("margin");
                else values.put("margin", jsonValue(value));
                return this;
            }
            /**
             * Node width
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder width(@Nullable Long value) {
                if (value == null) values.remove("width");
                else values.put("width", jsonValue(value));
                return this;
            }
            /**
             * Node height
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder height(@Nullable Long value) {
                if (value == null) values.remove("height");
                else values.put("height", jsonValue(value));
                return this;
            }
            /**
             * Shape outside coordinates
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder shapeOutside(@Nullable DOM.ShapeOutsideInfo value) {
                if (value == null) values.remove("shapeOutside");
                else values.put("shapeOutside", jsonValue(value));
                return this;
            }
            public BoxModel build() {
                if (!values.containsKey("content")) throw new IllegalStateException("Missing required CDP field: content");
                if (!values.containsKey("padding")) throw new IllegalStateException("Missing required CDP field: padding");
                if (!values.containsKey("border")) throw new IllegalStateException("Missing required CDP field: border");
                if (!values.containsKey("margin")) throw new IllegalStateException("Missing required CDP field: margin");
                if (!values.containsKey("width")) throw new IllegalStateException("Missing required CDP field: width");
                if (!values.containsKey("height")) throw new IllegalStateException("Missing required CDP field: height");
                return new BoxModel(values);
            }
        }
    }
    /**
     * CSS Shape Outside details.
     */
    public static final class ShapeOutsideInfo extends CdpObject {
        private ShapeOutsideInfo(Map<String, Object> values) { super(values); }
        @Nullable public static ShapeOutsideInfo fromMap(@Nullable Map<String, Object> values) {
            return values == null ? null : new ShapeOutsideInfo(values);
        }
        public static Builder builder() { return new Builder(); }
        /**
         * Shape bounds
         * @return the protocol field value
         */
        @Nullable public java.util.List<Double> bounds() {
            return list(value("bounds"), element0 -> numberAsDouble(element0));
        }
        /**
         * Shape coordinate details
         * @return the protocol field value
         */
        @Nullable public java.util.List<Object> shape() {
            return list(value("shape"), element0 -> element0);
        }
        /**
         * Margin shape bounds
         * @return the protocol field value
         */
        @Nullable public java.util.List<Object> marginShape() {
            return list(value("marginShape"), element0 -> element0);
        }
        public static final class Builder {
            private final Map<String, Object> values = new LinkedHashMap<>();
            /**
             * Shape bounds
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder bounds(@Nullable java.util.List<Double> value) {
                if (value == null) values.remove("bounds");
                else values.put("bounds", jsonValue(value));
                return this;
            }
            /**
             * Shape coordinate details
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder shape(@Nullable java.util.List<Object> value) {
                if (value == null) values.remove("shape");
                else values.put("shape", jsonValue(value));
                return this;
            }
            /**
             * Margin shape bounds
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder marginShape(@Nullable java.util.List<Object> value) {
                if (value == null) values.remove("marginShape");
                else values.put("marginShape", jsonValue(value));
                return this;
            }
            public ShapeOutsideInfo build() {
                if (!values.containsKey("bounds")) throw new IllegalStateException("Missing required CDP field: bounds");
                if (!values.containsKey("shape")) throw new IllegalStateException("Missing required CDP field: shape");
                if (!values.containsKey("marginShape")) throw new IllegalStateException("Missing required CDP field: marginShape");
                return new ShapeOutsideInfo(values);
            }
        }
    }
    /**
     * Rectangle.
     */
    public static final class Rect extends CdpObject {
        private Rect(Map<String, Object> values) { super(values); }
        @Nullable public static Rect fromMap(@Nullable Map<String, Object> values) {
            return values == null ? null : new Rect(values);
        }
        public static Builder builder() { return new Builder(); }
        /**
         * X coordinate
         * @return the protocol field value
         */
        @Nullable public Double x() {
            return numberAsDouble(value("x"));
        }
        /**
         * Y coordinate
         * @return the protocol field value
         */
        @Nullable public Double y() {
            return numberAsDouble(value("y"));
        }
        /**
         * Rectangle width
         * @return the protocol field value
         */
        @Nullable public Double width() {
            return numberAsDouble(value("width"));
        }
        /**
         * Rectangle height
         * @return the protocol field value
         */
        @Nullable public Double height() {
            return numberAsDouble(value("height"));
        }
        public static final class Builder {
            private final Map<String, Object> values = new LinkedHashMap<>();
            /**
             * X coordinate
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder x(@Nullable Double value) {
                if (value == null) values.remove("x");
                else values.put("x", jsonValue(value));
                return this;
            }
            /**
             * Y coordinate
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder y(@Nullable Double value) {
                if (value == null) values.remove("y");
                else values.put("y", jsonValue(value));
                return this;
            }
            /**
             * Rectangle width
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder width(@Nullable Double value) {
                if (value == null) values.remove("width");
                else values.put("width", jsonValue(value));
                return this;
            }
            /**
             * Rectangle height
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder height(@Nullable Double value) {
                if (value == null) values.remove("height");
                else values.put("height", jsonValue(value));
                return this;
            }
            public Rect build() {
                if (!values.containsKey("x")) throw new IllegalStateException("Missing required CDP field: x");
                if (!values.containsKey("y")) throw new IllegalStateException("Missing required CDP field: y");
                if (!values.containsKey("width")) throw new IllegalStateException("Missing required CDP field: width");
                if (!values.containsKey("height")) throw new IllegalStateException("Missing required CDP field: height");
                return new Rect(values);
            }
        }
    }
    /**
     */
    public static final class CSSComputedStyleProperty extends CdpObject {
        private CSSComputedStyleProperty(Map<String, Object> values) { super(values); }
        @Nullable public static CSSComputedStyleProperty fromMap(@Nullable Map<String, Object> values) {
            return values == null ? null : new CSSComputedStyleProperty(values);
        }
        public static Builder builder() { return new Builder(); }
        /**
         * Computed style property name.
         * @return the protocol field value
         */
        @Nullable public String name() {
            return (String) value("name");
        }
        /**
         * Computed style property value.
         * @return the protocol field value
         */
        @Nullable public String value() {
            return (String) value("value");
        }
        public static final class Builder {
            private final Map<String, Object> values = new LinkedHashMap<>();
            /**
             * Computed style property name.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder name(@Nullable String value) {
                if (value == null) values.remove("name");
                else values.put("name", jsonValue(value));
                return this;
            }
            /**
             * Computed style property value.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder value(@Nullable String value) {
                if (value == null) values.remove("value");
                else values.put("value", jsonValue(value));
                return this;
            }
            public CSSComputedStyleProperty build() {
                if (!values.containsKey("name")) throw new IllegalStateException("Missing required CDP field: name");
                if (!values.containsKey("value")) throw new IllegalStateException("Missing required CDP field: value");
                return new CSSComputedStyleProperty(values);
            }
        }
    }
    /**
     * Collects class names for the node with given id and all of it&#x27;s child nodes.
     * <p><b>Experimental:</b> this part of CDP may change without notice.
     */
    public static final class CollectClassNamesFromSubtreeParams extends CdpObject {
        private CollectClassNamesFromSubtreeParams(Map<String, Object> values) { super(values); }
        @Nullable public static CollectClassNamesFromSubtreeParams fromMap(@Nullable Map<String, Object> values) {
            return values == null ? null : new CollectClassNamesFromSubtreeParams(values);
        }
        public static Builder builder() { return new Builder(); }
        /**
         * Id of the node to collect class names.
         * @return the protocol field value
         */
        @Nullable public Long nodeId() {
            return numberAsLong(value("nodeId"));
        }
        public static final class Builder {
            private final Map<String, Object> values = new LinkedHashMap<>();
            /**
             * Id of the node to collect class names.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder nodeId(@Nullable Long value) {
                if (value == null) values.remove("nodeId");
                else values.put("nodeId", jsonValue(value));
                return this;
            }
            public CollectClassNamesFromSubtreeParams build() {
                if (!values.containsKey("nodeId")) throw new IllegalStateException("Missing required CDP field: nodeId");
                return new CollectClassNamesFromSubtreeParams(values);
            }
        }
    }
    /**
     * Collects class names for the node with given id and all of it&#x27;s child nodes.
     * <p><b>Experimental:</b> this part of CDP may change without notice.
     */
    public static final class CollectClassNamesFromSubtreeResult extends CdpObject {
        private CollectClassNamesFromSubtreeResult(Map<String, Object> values) { super(values); }
        @Nullable public static CollectClassNamesFromSubtreeResult fromMap(@Nullable Map<String, Object> values) {
            return values == null ? null : new CollectClassNamesFromSubtreeResult(values);
        }
        public static Builder builder() { return new Builder(); }
        /**
         * Class name list.
         * @return the protocol field value
         */
        @Nullable public java.util.List<String> classNames() {
            return list(value("classNames"), element0 -> (String) element0);
        }
        public static final class Builder {
            private final Map<String, Object> values = new LinkedHashMap<>();
            /**
             * Class name list.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder classNames(@Nullable java.util.List<String> value) {
                if (value == null) values.remove("classNames");
                else values.put("classNames", jsonValue(value));
                return this;
            }
            public CollectClassNamesFromSubtreeResult build() {
                if (!values.containsKey("classNames")) throw new IllegalStateException("Missing required CDP field: classNames");
                return new CollectClassNamesFromSubtreeResult(values);
            }
        }
    }
    /**
     * Creates a deep copy of the specified node and places it into the target container before the given anchor.
     * <p><b>Experimental:</b> this part of CDP may change without notice.
     */
    public static final class CopyToParams extends CdpObject {
        private CopyToParams(Map<String, Object> values) { super(values); }
        @Nullable public static CopyToParams fromMap(@Nullable Map<String, Object> values) {
            return values == null ? null : new CopyToParams(values);
        }
        public static Builder builder() { return new Builder(); }
        /**
         * Id of the node to copy.
         * @return the protocol field value
         */
        @Nullable public Long nodeId() {
            return numberAsLong(value("nodeId"));
        }
        /**
         * Id of the element to drop the copy into.
         * @return the protocol field value
         */
        @Nullable public Long targetNodeId() {
            return numberAsLong(value("targetNodeId"));
        }
        /**
         * Drop the copy before this node (if absent, the copy becomes the last child of {@code targetNodeId}).
         * @return the protocol field value
         */
        @Nullable public Long insertBeforeNodeId() {
            return numberAsLong(value("insertBeforeNodeId"));
        }
        public static final class Builder {
            private final Map<String, Object> values = new LinkedHashMap<>();
            /**
             * Id of the node to copy.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder nodeId(@Nullable Long value) {
                if (value == null) values.remove("nodeId");
                else values.put("nodeId", jsonValue(value));
                return this;
            }
            /**
             * Id of the element to drop the copy into.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder targetNodeId(@Nullable Long value) {
                if (value == null) values.remove("targetNodeId");
                else values.put("targetNodeId", jsonValue(value));
                return this;
            }
            /**
             * Drop the copy before this node (if absent, the copy becomes the last child of {@code targetNodeId}).
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder insertBeforeNodeId(@Nullable Long value) {
                if (value == null) values.remove("insertBeforeNodeId");
                else values.put("insertBeforeNodeId", jsonValue(value));
                return this;
            }
            public CopyToParams build() {
                if (!values.containsKey("nodeId")) throw new IllegalStateException("Missing required CDP field: nodeId");
                if (!values.containsKey("targetNodeId")) throw new IllegalStateException("Missing required CDP field: targetNodeId");
                return new CopyToParams(values);
            }
        }
    }
    /**
     * Creates a deep copy of the specified node and places it into the target container before the given anchor.
     * <p><b>Experimental:</b> this part of CDP may change without notice.
     */
    public static final class CopyToResult extends CdpObject {
        private CopyToResult(Map<String, Object> values) { super(values); }
        @Nullable public static CopyToResult fromMap(@Nullable Map<String, Object> values) {
            return values == null ? null : new CopyToResult(values);
        }
        public static Builder builder() { return new Builder(); }
        /**
         * Id of the node clone.
         * @return the protocol field value
         */
        @Nullable public Long nodeId() {
            return numberAsLong(value("nodeId"));
        }
        public static final class Builder {
            private final Map<String, Object> values = new LinkedHashMap<>();
            /**
             * Id of the node clone.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder nodeId(@Nullable Long value) {
                if (value == null) values.remove("nodeId");
                else values.put("nodeId", jsonValue(value));
                return this;
            }
            public CopyToResult build() {
                if (!values.containsKey("nodeId")) throw new IllegalStateException("Missing required CDP field: nodeId");
                return new CopyToResult(values);
            }
        }
    }
    /**
     * Describes node given its id, does not require domain to be enabled. Does not start tracking any objects, can be used for automation.
     */
    public static final class DescribeNodeParams extends CdpObject {
        private DescribeNodeParams(Map<String, Object> values) { super(values); }
        @Nullable public static DescribeNodeParams fromMap(@Nullable Map<String, Object> values) {
            return values == null ? null : new DescribeNodeParams(values);
        }
        public static Builder builder() { return new Builder(); }
        /**
         * Identifier of the node.
         * @return the protocol field value
         */
        @Nullable public Long nodeId() {
            return numberAsLong(value("nodeId"));
        }
        /**
         * Identifier of the backend node.
         * @return the protocol field value
         */
        @Nullable public Long backendNodeId() {
            return numberAsLong(value("backendNodeId"));
        }
        /**
         * JavaScript object id of the node wrapper.
         * @return the protocol field value
         */
        @Nullable public String objectId() {
            return (String) value("objectId");
        }
        /**
         * The maximum depth at which children should be retrieved, defaults to 1. Use -1 for the entire subtree or provide an integer larger than 0.
         * @return the protocol field value
         */
        @Nullable public Long depth() {
            return numberAsLong(value("depth"));
        }
        /**
         * Whether or not iframes and shadow roots should be traversed when returning the subtree (default is false).
         * @return the protocol field value
         */
        @Nullable public Boolean pierce() {
            return (Boolean) value("pierce");
        }
        public static final class Builder {
            private final Map<String, Object> values = new LinkedHashMap<>();
            /**
             * Identifier of the node.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder nodeId(@Nullable Long value) {
                if (value == null) values.remove("nodeId");
                else values.put("nodeId", jsonValue(value));
                return this;
            }
            /**
             * Identifier of the backend node.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder backendNodeId(@Nullable Long value) {
                if (value == null) values.remove("backendNodeId");
                else values.put("backendNodeId", jsonValue(value));
                return this;
            }
            /**
             * JavaScript object id of the node wrapper.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder objectId(@Nullable String value) {
                if (value == null) values.remove("objectId");
                else values.put("objectId", jsonValue(value));
                return this;
            }
            /**
             * The maximum depth at which children should be retrieved, defaults to 1. Use -1 for the entire subtree or provide an integer larger than 0.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder depth(@Nullable Long value) {
                if (value == null) values.remove("depth");
                else values.put("depth", jsonValue(value));
                return this;
            }
            /**
             * Whether or not iframes and shadow roots should be traversed when returning the subtree (default is false).
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder pierce(@Nullable Boolean value) {
                if (value == null) values.remove("pierce");
                else values.put("pierce", jsonValue(value));
                return this;
            }
            public DescribeNodeParams build() {
                return new DescribeNodeParams(values);
            }
        }
    }
    /**
     * Describes node given its id, does not require domain to be enabled. Does not start tracking any objects, can be used for automation.
     */
    public static final class DescribeNodeResult extends CdpObject {
        private DescribeNodeResult(Map<String, Object> values) { super(values); }
        @Nullable public static DescribeNodeResult fromMap(@Nullable Map<String, Object> values) {
            return values == null ? null : new DescribeNodeResult(values);
        }
        public static Builder builder() { return new Builder(); }
        /**
         * Node description.
         * @return the protocol field value
         */
        @Nullable public DOM.Node node() {
            return DOM.Node.fromMap(objectMap(value("node")));
        }
        public static final class Builder {
            private final Map<String, Object> values = new LinkedHashMap<>();
            /**
             * Node description.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder node(@Nullable DOM.Node value) {
                if (value == null) values.remove("node");
                else values.put("node", jsonValue(value));
                return this;
            }
            public DescribeNodeResult build() {
                if (!values.containsKey("node")) throw new IllegalStateException("Missing required CDP field: node");
                return new DescribeNodeResult(values);
            }
        }
    }
    /**
     * Scrolls the specified rect of the given node into view if not already visible. Note: exactly one between nodeId, backendNodeId and objectId should be passed to identify the node.
     */
    public static final class ScrollIntoViewIfNeededParams extends CdpObject {
        private ScrollIntoViewIfNeededParams(Map<String, Object> values) { super(values); }
        @Nullable public static ScrollIntoViewIfNeededParams fromMap(@Nullable Map<String, Object> values) {
            return values == null ? null : new ScrollIntoViewIfNeededParams(values);
        }
        public static Builder builder() { return new Builder(); }
        /**
         * Identifier of the node.
         * @return the protocol field value
         */
        @Nullable public Long nodeId() {
            return numberAsLong(value("nodeId"));
        }
        /**
         * Identifier of the backend node.
         * @return the protocol field value
         */
        @Nullable public Long backendNodeId() {
            return numberAsLong(value("backendNodeId"));
        }
        /**
         * JavaScript object id of the node wrapper.
         * @return the protocol field value
         */
        @Nullable public String objectId() {
            return (String) value("objectId");
        }
        /**
         * The rect to be scrolled into view, relative to the node&#x27;s border box, in CSS pixels. When omitted, center of the node will be used, similar to Element.scrollIntoView.
         * @return the protocol field value
         */
        @Nullable public DOM.Rect rect() {
            return DOM.Rect.fromMap(objectMap(value("rect")));
        }
        public static final class Builder {
            private final Map<String, Object> values = new LinkedHashMap<>();
            /**
             * Identifier of the node.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder nodeId(@Nullable Long value) {
                if (value == null) values.remove("nodeId");
                else values.put("nodeId", jsonValue(value));
                return this;
            }
            /**
             * Identifier of the backend node.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder backendNodeId(@Nullable Long value) {
                if (value == null) values.remove("backendNodeId");
                else values.put("backendNodeId", jsonValue(value));
                return this;
            }
            /**
             * JavaScript object id of the node wrapper.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder objectId(@Nullable String value) {
                if (value == null) values.remove("objectId");
                else values.put("objectId", jsonValue(value));
                return this;
            }
            /**
             * The rect to be scrolled into view, relative to the node&#x27;s border box, in CSS pixels. When omitted, center of the node will be used, similar to Element.scrollIntoView.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder rect(@Nullable DOM.Rect value) {
                if (value == null) values.remove("rect");
                else values.put("rect", jsonValue(value));
                return this;
            }
            public ScrollIntoViewIfNeededParams build() {
                return new ScrollIntoViewIfNeededParams(values);
            }
        }
    }
    /**
     * Scrolls the specified rect of the given node into view if not already visible. Note: exactly one between nodeId, backendNodeId and objectId should be passed to identify the node.
     */
    public static final class ScrollIntoViewIfNeededResult extends CdpObject {
        private ScrollIntoViewIfNeededResult(Map<String, Object> values) { super(values); }
        @Nullable public static ScrollIntoViewIfNeededResult fromMap(@Nullable Map<String, Object> values) {
            return values == null ? null : new ScrollIntoViewIfNeededResult(values);
        }
        public static Builder builder() { return new Builder(); }
        public static final class Builder {
            private final Map<String, Object> values = new LinkedHashMap<>();
            public ScrollIntoViewIfNeededResult build() {
                return new ScrollIntoViewIfNeededResult(values);
            }
        }
    }
    /**
     * Disables DOM agent for the given page.
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
     * Disables DOM agent for the given page.
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
     * Discards search results from the session with the given id. {@code getSearchResults} should no longer be called for that search.
     * <p><b>Experimental:</b> this part of CDP may change without notice.
     */
    public static final class DiscardSearchResultsParams extends CdpObject {
        private DiscardSearchResultsParams(Map<String, Object> values) { super(values); }
        @Nullable public static DiscardSearchResultsParams fromMap(@Nullable Map<String, Object> values) {
            return values == null ? null : new DiscardSearchResultsParams(values);
        }
        public static Builder builder() { return new Builder(); }
        /**
         * Unique search session identifier.
         * @return the protocol field value
         */
        @Nullable public String searchId() {
            return (String) value("searchId");
        }
        public static final class Builder {
            private final Map<String, Object> values = new LinkedHashMap<>();
            /**
             * Unique search session identifier.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder searchId(@Nullable String value) {
                if (value == null) values.remove("searchId");
                else values.put("searchId", jsonValue(value));
                return this;
            }
            public DiscardSearchResultsParams build() {
                if (!values.containsKey("searchId")) throw new IllegalStateException("Missing required CDP field: searchId");
                return new DiscardSearchResultsParams(values);
            }
        }
    }
    /**
     * Discards search results from the session with the given id. {@code getSearchResults} should no longer be called for that search.
     * <p><b>Experimental:</b> this part of CDP may change without notice.
     */
    public static final class DiscardSearchResultsResult extends CdpObject {
        private DiscardSearchResultsResult(Map<String, Object> values) { super(values); }
        @Nullable public static DiscardSearchResultsResult fromMap(@Nullable Map<String, Object> values) {
            return values == null ? null : new DiscardSearchResultsResult(values);
        }
        public static Builder builder() { return new Builder(); }
        public static final class Builder {
            private final Map<String, Object> values = new LinkedHashMap<>();
            public DiscardSearchResultsResult build() {
                return new DiscardSearchResultsResult(values);
            }
        }
    }
    /**
     * Enables DOM agent for the given page.
     */
    public static final class EnableParams extends CdpObject {
        private EnableParams(Map<String, Object> values) { super(values); }
        @Nullable public static EnableParams fromMap(@Nullable Map<String, Object> values) {
            return values == null ? null : new EnableParams(values);
        }
        public static Builder builder() { return new Builder(); }
        /**
         * Whether to include whitespaces in the children array of returned Nodes.
         * <p><b>Experimental:</b> this part of CDP may change without notice.
         * @return the protocol field value
         */
        @Nullable public String includeWhitespace() {
            return (String) value("includeWhitespace");
        }
        /**
         * Whether to include whitespaces in the children array of returned Nodes.
         * <p><b>Experimental:</b> this part of CDP may change without notice.
         */
        public static final class IncludeWhitespaceValues {
            private IncludeWhitespaceValues() {}
            public static final String NONE = "none";
            public static final String ALL = "all";
        }
        public static final class Builder {
            private final Map<String, Object> values = new LinkedHashMap<>();
            /**
             * Whether to include whitespaces in the children array of returned Nodes.
             * <p><b>Experimental:</b> this part of CDP may change without notice.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder includeWhitespace(@Nullable String value) {
                if (value == null) values.remove("includeWhitespace");
                else values.put("includeWhitespace", jsonValue(value));
                return this;
            }
            public EnableParams build() {
                return new EnableParams(values);
            }
        }
    }
    /**
     * Enables DOM agent for the given page.
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
     * Focuses the given element.
     */
    public static final class FocusParams extends CdpObject {
        private FocusParams(Map<String, Object> values) { super(values); }
        @Nullable public static FocusParams fromMap(@Nullable Map<String, Object> values) {
            return values == null ? null : new FocusParams(values);
        }
        public static Builder builder() { return new Builder(); }
        /**
         * Identifier of the node.
         * @return the protocol field value
         */
        @Nullable public Long nodeId() {
            return numberAsLong(value("nodeId"));
        }
        /**
         * Identifier of the backend node.
         * @return the protocol field value
         */
        @Nullable public Long backendNodeId() {
            return numberAsLong(value("backendNodeId"));
        }
        /**
         * JavaScript object id of the node wrapper.
         * @return the protocol field value
         */
        @Nullable public String objectId() {
            return (String) value("objectId");
        }
        public static final class Builder {
            private final Map<String, Object> values = new LinkedHashMap<>();
            /**
             * Identifier of the node.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder nodeId(@Nullable Long value) {
                if (value == null) values.remove("nodeId");
                else values.put("nodeId", jsonValue(value));
                return this;
            }
            /**
             * Identifier of the backend node.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder backendNodeId(@Nullable Long value) {
                if (value == null) values.remove("backendNodeId");
                else values.put("backendNodeId", jsonValue(value));
                return this;
            }
            /**
             * JavaScript object id of the node wrapper.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder objectId(@Nullable String value) {
                if (value == null) values.remove("objectId");
                else values.put("objectId", jsonValue(value));
                return this;
            }
            public FocusParams build() {
                return new FocusParams(values);
            }
        }
    }
    /**
     * Focuses the given element.
     */
    public static final class FocusResult extends CdpObject {
        private FocusResult(Map<String, Object> values) { super(values); }
        @Nullable public static FocusResult fromMap(@Nullable Map<String, Object> values) {
            return values == null ? null : new FocusResult(values);
        }
        public static Builder builder() { return new Builder(); }
        public static final class Builder {
            private final Map<String, Object> values = new LinkedHashMap<>();
            public FocusResult build() {
                return new FocusResult(values);
            }
        }
    }
    /**
     * Returns attributes for the specified node.
     */
    public static final class GetAttributesParams extends CdpObject {
        private GetAttributesParams(Map<String, Object> values) { super(values); }
        @Nullable public static GetAttributesParams fromMap(@Nullable Map<String, Object> values) {
            return values == null ? null : new GetAttributesParams(values);
        }
        public static Builder builder() { return new Builder(); }
        /**
         * Id of the node to retrieve attributes for.
         * @return the protocol field value
         */
        @Nullable public Long nodeId() {
            return numberAsLong(value("nodeId"));
        }
        public static final class Builder {
            private final Map<String, Object> values = new LinkedHashMap<>();
            /**
             * Id of the node to retrieve attributes for.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder nodeId(@Nullable Long value) {
                if (value == null) values.remove("nodeId");
                else values.put("nodeId", jsonValue(value));
                return this;
            }
            public GetAttributesParams build() {
                if (!values.containsKey("nodeId")) throw new IllegalStateException("Missing required CDP field: nodeId");
                return new GetAttributesParams(values);
            }
        }
    }
    /**
     * Returns attributes for the specified node.
     */
    public static final class GetAttributesResult extends CdpObject {
        private GetAttributesResult(Map<String, Object> values) { super(values); }
        @Nullable public static GetAttributesResult fromMap(@Nullable Map<String, Object> values) {
            return values == null ? null : new GetAttributesResult(values);
        }
        public static Builder builder() { return new Builder(); }
        /**
         * An interleaved array of node attribute names and values.
         * @return the protocol field value
         */
        @Nullable public java.util.List<String> attributes() {
            return list(value("attributes"), element0 -> (String) element0);
        }
        public static final class Builder {
            private final Map<String, Object> values = new LinkedHashMap<>();
            /**
             * An interleaved array of node attribute names and values.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder attributes(@Nullable java.util.List<String> value) {
                if (value == null) values.remove("attributes");
                else values.put("attributes", jsonValue(value));
                return this;
            }
            public GetAttributesResult build() {
                if (!values.containsKey("attributes")) throw new IllegalStateException("Missing required CDP field: attributes");
                return new GetAttributesResult(values);
            }
        }
    }
    /**
     * Returns boxes for the given node.
     */
    public static final class GetBoxModelParams extends CdpObject {
        private GetBoxModelParams(Map<String, Object> values) { super(values); }
        @Nullable public static GetBoxModelParams fromMap(@Nullable Map<String, Object> values) {
            return values == null ? null : new GetBoxModelParams(values);
        }
        public static Builder builder() { return new Builder(); }
        /**
         * Identifier of the node.
         * @return the protocol field value
         */
        @Nullable public Long nodeId() {
            return numberAsLong(value("nodeId"));
        }
        /**
         * Identifier of the backend node.
         * @return the protocol field value
         */
        @Nullable public Long backendNodeId() {
            return numberAsLong(value("backendNodeId"));
        }
        /**
         * JavaScript object id of the node wrapper.
         * @return the protocol field value
         */
        @Nullable public String objectId() {
            return (String) value("objectId");
        }
        public static final class Builder {
            private final Map<String, Object> values = new LinkedHashMap<>();
            /**
             * Identifier of the node.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder nodeId(@Nullable Long value) {
                if (value == null) values.remove("nodeId");
                else values.put("nodeId", jsonValue(value));
                return this;
            }
            /**
             * Identifier of the backend node.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder backendNodeId(@Nullable Long value) {
                if (value == null) values.remove("backendNodeId");
                else values.put("backendNodeId", jsonValue(value));
                return this;
            }
            /**
             * JavaScript object id of the node wrapper.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder objectId(@Nullable String value) {
                if (value == null) values.remove("objectId");
                else values.put("objectId", jsonValue(value));
                return this;
            }
            public GetBoxModelParams build() {
                return new GetBoxModelParams(values);
            }
        }
    }
    /**
     * Returns boxes for the given node.
     */
    public static final class GetBoxModelResult extends CdpObject {
        private GetBoxModelResult(Map<String, Object> values) { super(values); }
        @Nullable public static GetBoxModelResult fromMap(@Nullable Map<String, Object> values) {
            return values == null ? null : new GetBoxModelResult(values);
        }
        public static Builder builder() { return new Builder(); }
        /**
         * Box model for the node.
         * @return the protocol field value
         */
        @Nullable public DOM.BoxModel model() {
            return DOM.BoxModel.fromMap(objectMap(value("model")));
        }
        public static final class Builder {
            private final Map<String, Object> values = new LinkedHashMap<>();
            /**
             * Box model for the node.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder model(@Nullable DOM.BoxModel value) {
                if (value == null) values.remove("model");
                else values.put("model", jsonValue(value));
                return this;
            }
            public GetBoxModelResult build() {
                if (!values.containsKey("model")) throw new IllegalStateException("Missing required CDP field: model");
                return new GetBoxModelResult(values);
            }
        }
    }
    /**
     * Returns quads that describe node position on the page. This method might return multiple quads for inline nodes.
     * <p><b>Experimental:</b> this part of CDP may change without notice.
     */
    public static final class GetContentQuadsParams extends CdpObject {
        private GetContentQuadsParams(Map<String, Object> values) { super(values); }
        @Nullable public static GetContentQuadsParams fromMap(@Nullable Map<String, Object> values) {
            return values == null ? null : new GetContentQuadsParams(values);
        }
        public static Builder builder() { return new Builder(); }
        /**
         * Identifier of the node.
         * @return the protocol field value
         */
        @Nullable public Long nodeId() {
            return numberAsLong(value("nodeId"));
        }
        /**
         * Identifier of the backend node.
         * @return the protocol field value
         */
        @Nullable public Long backendNodeId() {
            return numberAsLong(value("backendNodeId"));
        }
        /**
         * JavaScript object id of the node wrapper.
         * @return the protocol field value
         */
        @Nullable public String objectId() {
            return (String) value("objectId");
        }
        public static final class Builder {
            private final Map<String, Object> values = new LinkedHashMap<>();
            /**
             * Identifier of the node.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder nodeId(@Nullable Long value) {
                if (value == null) values.remove("nodeId");
                else values.put("nodeId", jsonValue(value));
                return this;
            }
            /**
             * Identifier of the backend node.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder backendNodeId(@Nullable Long value) {
                if (value == null) values.remove("backendNodeId");
                else values.put("backendNodeId", jsonValue(value));
                return this;
            }
            /**
             * JavaScript object id of the node wrapper.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder objectId(@Nullable String value) {
                if (value == null) values.remove("objectId");
                else values.put("objectId", jsonValue(value));
                return this;
            }
            public GetContentQuadsParams build() {
                return new GetContentQuadsParams(values);
            }
        }
    }
    /**
     * Returns quads that describe node position on the page. This method might return multiple quads for inline nodes.
     * <p><b>Experimental:</b> this part of CDP may change without notice.
     */
    public static final class GetContentQuadsResult extends CdpObject {
        private GetContentQuadsResult(Map<String, Object> values) { super(values); }
        @Nullable public static GetContentQuadsResult fromMap(@Nullable Map<String, Object> values) {
            return values == null ? null : new GetContentQuadsResult(values);
        }
        public static Builder builder() { return new Builder(); }
        /**
         * Quads that describe node layout relative to viewport.
         * @return the protocol field value
         */
        @Nullable public java.util.List<java.util.List<Double>> quads() {
            return list(value("quads"), element0 -> list(element0, element1 -> numberAsDouble(element1)));
        }
        public static final class Builder {
            private final Map<String, Object> values = new LinkedHashMap<>();
            /**
             * Quads that describe node layout relative to viewport.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder quads(@Nullable java.util.List<java.util.List<Double>> value) {
                if (value == null) values.remove("quads");
                else values.put("quads", jsonValue(value));
                return this;
            }
            public GetContentQuadsResult build() {
                if (!values.containsKey("quads")) throw new IllegalStateException("Missing required CDP field: quads");
                return new GetContentQuadsResult(values);
            }
        }
    }
    /**
     * Returns the root DOM node (and optionally the subtree) to the caller. Implicitly enables the DOM domain events for the current target.
     */
    public static final class GetDocumentParams extends CdpObject {
        private GetDocumentParams(Map<String, Object> values) { super(values); }
        @Nullable public static GetDocumentParams fromMap(@Nullable Map<String, Object> values) {
            return values == null ? null : new GetDocumentParams(values);
        }
        public static Builder builder() { return new Builder(); }
        /**
         * The maximum depth at which children should be retrieved, defaults to 1. Use -1 for the entire subtree or provide an integer larger than 0.
         * @return the protocol field value
         */
        @Nullable public Long depth() {
            return numberAsLong(value("depth"));
        }
        /**
         * Whether or not iframes and shadow roots should be traversed when returning the subtree (default is false).
         * @return the protocol field value
         */
        @Nullable public Boolean pierce() {
            return (Boolean) value("pierce");
        }
        public static final class Builder {
            private final Map<String, Object> values = new LinkedHashMap<>();
            /**
             * The maximum depth at which children should be retrieved, defaults to 1. Use -1 for the entire subtree or provide an integer larger than 0.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder depth(@Nullable Long value) {
                if (value == null) values.remove("depth");
                else values.put("depth", jsonValue(value));
                return this;
            }
            /**
             * Whether or not iframes and shadow roots should be traversed when returning the subtree (default is false).
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder pierce(@Nullable Boolean value) {
                if (value == null) values.remove("pierce");
                else values.put("pierce", jsonValue(value));
                return this;
            }
            public GetDocumentParams build() {
                return new GetDocumentParams(values);
            }
        }
    }
    /**
     * Returns the root DOM node (and optionally the subtree) to the caller. Implicitly enables the DOM domain events for the current target.
     */
    public static final class GetDocumentResult extends CdpObject {
        private GetDocumentResult(Map<String, Object> values) { super(values); }
        @Nullable public static GetDocumentResult fromMap(@Nullable Map<String, Object> values) {
            return values == null ? null : new GetDocumentResult(values);
        }
        public static Builder builder() { return new Builder(); }
        /**
         * Resulting node.
         * @return the protocol field value
         */
        @Nullable public DOM.Node root() {
            return DOM.Node.fromMap(objectMap(value("root")));
        }
        public static final class Builder {
            private final Map<String, Object> values = new LinkedHashMap<>();
            /**
             * Resulting node.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder root(@Nullable DOM.Node value) {
                if (value == null) values.remove("root");
                else values.put("root", jsonValue(value));
                return this;
            }
            public GetDocumentResult build() {
                if (!values.containsKey("root")) throw new IllegalStateException("Missing required CDP field: root");
                return new GetDocumentResult(values);
            }
        }
    }
    /**
     * Returns the root DOM node (and optionally the subtree) to the caller. Deprecated, as it is not designed to work well with the rest of the DOM agent. Use DOMSnapshot.captureSnapshot instead.
     * @deprecated Deprecated by the Chromium DevTools Protocol.
     */
    @Deprecated
    public static final class GetFlattenedDocumentParams extends CdpObject {
        private GetFlattenedDocumentParams(Map<String, Object> values) { super(values); }
        @Nullable public static GetFlattenedDocumentParams fromMap(@Nullable Map<String, Object> values) {
            return values == null ? null : new GetFlattenedDocumentParams(values);
        }
        public static Builder builder() { return new Builder(); }
        /**
         * The maximum depth at which children should be retrieved, defaults to 1. Use -1 for the entire subtree or provide an integer larger than 0.
         * @return the protocol field value
         */
        @Nullable public Long depth() {
            return numberAsLong(value("depth"));
        }
        /**
         * Whether or not iframes and shadow roots should be traversed when returning the subtree (default is false).
         * @return the protocol field value
         */
        @Nullable public Boolean pierce() {
            return (Boolean) value("pierce");
        }
        public static final class Builder {
            private final Map<String, Object> values = new LinkedHashMap<>();
            /**
             * The maximum depth at which children should be retrieved, defaults to 1. Use -1 for the entire subtree or provide an integer larger than 0.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder depth(@Nullable Long value) {
                if (value == null) values.remove("depth");
                else values.put("depth", jsonValue(value));
                return this;
            }
            /**
             * Whether or not iframes and shadow roots should be traversed when returning the subtree (default is false).
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder pierce(@Nullable Boolean value) {
                if (value == null) values.remove("pierce");
                else values.put("pierce", jsonValue(value));
                return this;
            }
            public GetFlattenedDocumentParams build() {
                return new GetFlattenedDocumentParams(values);
            }
        }
    }
    /**
     * Returns the root DOM node (and optionally the subtree) to the caller. Deprecated, as it is not designed to work well with the rest of the DOM agent. Use DOMSnapshot.captureSnapshot instead.
     * @deprecated Deprecated by the Chromium DevTools Protocol.
     */
    @Deprecated
    public static final class GetFlattenedDocumentResult extends CdpObject {
        private GetFlattenedDocumentResult(Map<String, Object> values) { super(values); }
        @Nullable public static GetFlattenedDocumentResult fromMap(@Nullable Map<String, Object> values) {
            return values == null ? null : new GetFlattenedDocumentResult(values);
        }
        public static Builder builder() { return new Builder(); }
        /**
         * Resulting node.
         * @return the protocol field value
         */
        @Nullable public java.util.List<DOM.Node> nodes() {
            return list(value("nodes"), element0 -> DOM.Node.fromMap(objectMap(element0)));
        }
        public static final class Builder {
            private final Map<String, Object> values = new LinkedHashMap<>();
            /**
             * Resulting node.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder nodes(@Nullable java.util.List<DOM.Node> value) {
                if (value == null) values.remove("nodes");
                else values.put("nodes", jsonValue(value));
                return this;
            }
            public GetFlattenedDocumentResult build() {
                if (!values.containsKey("nodes")) throw new IllegalStateException("Missing required CDP field: nodes");
                return new GetFlattenedDocumentResult(values);
            }
        }
    }
    /**
     * Finds nodes with a given computed style in a subtree.
     * <p><b>Experimental:</b> this part of CDP may change without notice.
     */
    public static final class GetNodesForSubtreeByStyleParams extends CdpObject {
        private GetNodesForSubtreeByStyleParams(Map<String, Object> values) { super(values); }
        @Nullable public static GetNodesForSubtreeByStyleParams fromMap(@Nullable Map<String, Object> values) {
            return values == null ? null : new GetNodesForSubtreeByStyleParams(values);
        }
        public static Builder builder() { return new Builder(); }
        /**
         * Node ID pointing to the root of a subtree.
         * @return the protocol field value
         */
        @Nullable public Long nodeId() {
            return numberAsLong(value("nodeId"));
        }
        /**
         * The style to filter nodes by (includes nodes if any of properties matches).
         * @return the protocol field value
         */
        @Nullable public java.util.List<DOM.CSSComputedStyleProperty> computedStyles() {
            return list(value("computedStyles"), element0 -> DOM.CSSComputedStyleProperty.fromMap(objectMap(element0)));
        }
        /**
         * Whether or not iframes and shadow roots in the same target should be traversed when returning the results (default is false).
         * @return the protocol field value
         */
        @Nullable public Boolean pierce() {
            return (Boolean) value("pierce");
        }
        public static final class Builder {
            private final Map<String, Object> values = new LinkedHashMap<>();
            /**
             * Node ID pointing to the root of a subtree.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder nodeId(@Nullable Long value) {
                if (value == null) values.remove("nodeId");
                else values.put("nodeId", jsonValue(value));
                return this;
            }
            /**
             * The style to filter nodes by (includes nodes if any of properties matches).
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder computedStyles(@Nullable java.util.List<DOM.CSSComputedStyleProperty> value) {
                if (value == null) values.remove("computedStyles");
                else values.put("computedStyles", jsonValue(value));
                return this;
            }
            /**
             * Whether or not iframes and shadow roots in the same target should be traversed when returning the results (default is false).
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder pierce(@Nullable Boolean value) {
                if (value == null) values.remove("pierce");
                else values.put("pierce", jsonValue(value));
                return this;
            }
            public GetNodesForSubtreeByStyleParams build() {
                if (!values.containsKey("nodeId")) throw new IllegalStateException("Missing required CDP field: nodeId");
                if (!values.containsKey("computedStyles")) throw new IllegalStateException("Missing required CDP field: computedStyles");
                return new GetNodesForSubtreeByStyleParams(values);
            }
        }
    }
    /**
     * Finds nodes with a given computed style in a subtree.
     * <p><b>Experimental:</b> this part of CDP may change without notice.
     */
    public static final class GetNodesForSubtreeByStyleResult extends CdpObject {
        private GetNodesForSubtreeByStyleResult(Map<String, Object> values) { super(values); }
        @Nullable public static GetNodesForSubtreeByStyleResult fromMap(@Nullable Map<String, Object> values) {
            return values == null ? null : new GetNodesForSubtreeByStyleResult(values);
        }
        public static Builder builder() { return new Builder(); }
        /**
         * Resulting nodes.
         * @return the protocol field value
         */
        @Nullable public java.util.List<Long> nodeIds() {
            return list(value("nodeIds"), element0 -> numberAsLong(element0));
        }
        public static final class Builder {
            private final Map<String, Object> values = new LinkedHashMap<>();
            /**
             * Resulting nodes.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder nodeIds(@Nullable java.util.List<Long> value) {
                if (value == null) values.remove("nodeIds");
                else values.put("nodeIds", jsonValue(value));
                return this;
            }
            public GetNodesForSubtreeByStyleResult build() {
                if (!values.containsKey("nodeIds")) throw new IllegalStateException("Missing required CDP field: nodeIds");
                return new GetNodesForSubtreeByStyleResult(values);
            }
        }
    }
    /**
     * Returns node id at given location. Depending on whether DOM domain is enabled, nodeId is either returned or not.
     */
    public static final class GetNodeForLocationParams extends CdpObject {
        private GetNodeForLocationParams(Map<String, Object> values) { super(values); }
        @Nullable public static GetNodeForLocationParams fromMap(@Nullable Map<String, Object> values) {
            return values == null ? null : new GetNodeForLocationParams(values);
        }
        public static Builder builder() { return new Builder(); }
        /**
         * X coordinate.
         * @return the protocol field value
         */
        @Nullable public Long x() {
            return numberAsLong(value("x"));
        }
        /**
         * Y coordinate.
         * @return the protocol field value
         */
        @Nullable public Long y() {
            return numberAsLong(value("y"));
        }
        /**
         * False to skip to the nearest non-UA shadow root ancestor (default: false).
         * @return the protocol field value
         */
        @Nullable public Boolean includeUserAgentShadowDOM() {
            return (Boolean) value("includeUserAgentShadowDOM");
        }
        /**
         * Whether to ignore pointer-events: none on elements and hit test them.
         * @return the protocol field value
         */
        @Nullable public Boolean ignorePointerEventsNone() {
            return (Boolean) value("ignorePointerEventsNone");
        }
        public static final class Builder {
            private final Map<String, Object> values = new LinkedHashMap<>();
            /**
             * X coordinate.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder x(@Nullable Long value) {
                if (value == null) values.remove("x");
                else values.put("x", jsonValue(value));
                return this;
            }
            /**
             * Y coordinate.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder y(@Nullable Long value) {
                if (value == null) values.remove("y");
                else values.put("y", jsonValue(value));
                return this;
            }
            /**
             * False to skip to the nearest non-UA shadow root ancestor (default: false).
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder includeUserAgentShadowDOM(@Nullable Boolean value) {
                if (value == null) values.remove("includeUserAgentShadowDOM");
                else values.put("includeUserAgentShadowDOM", jsonValue(value));
                return this;
            }
            /**
             * Whether to ignore pointer-events: none on elements and hit test them.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder ignorePointerEventsNone(@Nullable Boolean value) {
                if (value == null) values.remove("ignorePointerEventsNone");
                else values.put("ignorePointerEventsNone", jsonValue(value));
                return this;
            }
            public GetNodeForLocationParams build() {
                if (!values.containsKey("x")) throw new IllegalStateException("Missing required CDP field: x");
                if (!values.containsKey("y")) throw new IllegalStateException("Missing required CDP field: y");
                return new GetNodeForLocationParams(values);
            }
        }
    }
    /**
     * Returns node id at given location. Depending on whether DOM domain is enabled, nodeId is either returned or not.
     */
    public static final class GetNodeForLocationResult extends CdpObject {
        private GetNodeForLocationResult(Map<String, Object> values) { super(values); }
        @Nullable public static GetNodeForLocationResult fromMap(@Nullable Map<String, Object> values) {
            return values == null ? null : new GetNodeForLocationResult(values);
        }
        public static Builder builder() { return new Builder(); }
        /**
         * Resulting node.
         * @return the protocol field value
         */
        @Nullable public Long backendNodeId() {
            return numberAsLong(value("backendNodeId"));
        }
        /**
         * Frame this node belongs to.
         * @return the protocol field value
         */
        @Nullable public String frameId() {
            return (String) value("frameId");
        }
        /**
         * Id of the node at given coordinates, only when enabled and requested document.
         * @return the protocol field value
         */
        @Nullable public Long nodeId() {
            return numberAsLong(value("nodeId"));
        }
        public static final class Builder {
            private final Map<String, Object> values = new LinkedHashMap<>();
            /**
             * Resulting node.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder backendNodeId(@Nullable Long value) {
                if (value == null) values.remove("backendNodeId");
                else values.put("backendNodeId", jsonValue(value));
                return this;
            }
            /**
             * Frame this node belongs to.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder frameId(@Nullable String value) {
                if (value == null) values.remove("frameId");
                else values.put("frameId", jsonValue(value));
                return this;
            }
            /**
             * Id of the node at given coordinates, only when enabled and requested document.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder nodeId(@Nullable Long value) {
                if (value == null) values.remove("nodeId");
                else values.put("nodeId", jsonValue(value));
                return this;
            }
            public GetNodeForLocationResult build() {
                if (!values.containsKey("backendNodeId")) throw new IllegalStateException("Missing required CDP field: backendNodeId");
                if (!values.containsKey("frameId")) throw new IllegalStateException("Missing required CDP field: frameId");
                return new GetNodeForLocationResult(values);
            }
        }
    }
    /**
     * Returns node&#x27;s HTML markup.
     */
    public static final class GetOuterHTMLParams extends CdpObject {
        private GetOuterHTMLParams(Map<String, Object> values) { super(values); }
        @Nullable public static GetOuterHTMLParams fromMap(@Nullable Map<String, Object> values) {
            return values == null ? null : new GetOuterHTMLParams(values);
        }
        public static Builder builder() { return new Builder(); }
        /**
         * Identifier of the node.
         * @return the protocol field value
         */
        @Nullable public Long nodeId() {
            return numberAsLong(value("nodeId"));
        }
        /**
         * Identifier of the backend node.
         * @return the protocol field value
         */
        @Nullable public Long backendNodeId() {
            return numberAsLong(value("backendNodeId"));
        }
        /**
         * JavaScript object id of the node wrapper.
         * @return the protocol field value
         */
        @Nullable public String objectId() {
            return (String) value("objectId");
        }
        /**
         * Include all shadow roots. Equals to false if not specified.
         * <p><b>Experimental:</b> this part of CDP may change without notice.
         * @return the protocol field value
         */
        @Nullable public Boolean includeShadowDOM() {
            return (Boolean) value("includeShadowDOM");
        }
        public static final class Builder {
            private final Map<String, Object> values = new LinkedHashMap<>();
            /**
             * Identifier of the node.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder nodeId(@Nullable Long value) {
                if (value == null) values.remove("nodeId");
                else values.put("nodeId", jsonValue(value));
                return this;
            }
            /**
             * Identifier of the backend node.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder backendNodeId(@Nullable Long value) {
                if (value == null) values.remove("backendNodeId");
                else values.put("backendNodeId", jsonValue(value));
                return this;
            }
            /**
             * JavaScript object id of the node wrapper.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder objectId(@Nullable String value) {
                if (value == null) values.remove("objectId");
                else values.put("objectId", jsonValue(value));
                return this;
            }
            /**
             * Include all shadow roots. Equals to false if not specified.
             * <p><b>Experimental:</b> this part of CDP may change without notice.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder includeShadowDOM(@Nullable Boolean value) {
                if (value == null) values.remove("includeShadowDOM");
                else values.put("includeShadowDOM", jsonValue(value));
                return this;
            }
            public GetOuterHTMLParams build() {
                return new GetOuterHTMLParams(values);
            }
        }
    }
    /**
     * Returns node&#x27;s HTML markup.
     */
    public static final class GetOuterHTMLResult extends CdpObject {
        private GetOuterHTMLResult(Map<String, Object> values) { super(values); }
        @Nullable public static GetOuterHTMLResult fromMap(@Nullable Map<String, Object> values) {
            return values == null ? null : new GetOuterHTMLResult(values);
        }
        public static Builder builder() { return new Builder(); }
        /**
         * Outer HTML markup.
         * @return the protocol field value
         */
        @Nullable public String outerHTML() {
            return (String) value("outerHTML");
        }
        public static final class Builder {
            private final Map<String, Object> values = new LinkedHashMap<>();
            /**
             * Outer HTML markup.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder outerHTML(@Nullable String value) {
                if (value == null) values.remove("outerHTML");
                else values.put("outerHTML", jsonValue(value));
                return this;
            }
            public GetOuterHTMLResult build() {
                if (!values.containsKey("outerHTML")) throw new IllegalStateException("Missing required CDP field: outerHTML");
                return new GetOuterHTMLResult(values);
            }
        }
    }
    /**
     * Returns the id of the nearest ancestor that is a relayout boundary.
     * <p><b>Experimental:</b> this part of CDP may change without notice.
     */
    public static final class GetRelayoutBoundaryParams extends CdpObject {
        private GetRelayoutBoundaryParams(Map<String, Object> values) { super(values); }
        @Nullable public static GetRelayoutBoundaryParams fromMap(@Nullable Map<String, Object> values) {
            return values == null ? null : new GetRelayoutBoundaryParams(values);
        }
        public static Builder builder() { return new Builder(); }
        /**
         * Id of the node.
         * @return the protocol field value
         */
        @Nullable public Long nodeId() {
            return numberAsLong(value("nodeId"));
        }
        public static final class Builder {
            private final Map<String, Object> values = new LinkedHashMap<>();
            /**
             * Id of the node.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder nodeId(@Nullable Long value) {
                if (value == null) values.remove("nodeId");
                else values.put("nodeId", jsonValue(value));
                return this;
            }
            public GetRelayoutBoundaryParams build() {
                if (!values.containsKey("nodeId")) throw new IllegalStateException("Missing required CDP field: nodeId");
                return new GetRelayoutBoundaryParams(values);
            }
        }
    }
    /**
     * Returns the id of the nearest ancestor that is a relayout boundary.
     * <p><b>Experimental:</b> this part of CDP may change without notice.
     */
    public static final class GetRelayoutBoundaryResult extends CdpObject {
        private GetRelayoutBoundaryResult(Map<String, Object> values) { super(values); }
        @Nullable public static GetRelayoutBoundaryResult fromMap(@Nullable Map<String, Object> values) {
            return values == null ? null : new GetRelayoutBoundaryResult(values);
        }
        public static Builder builder() { return new Builder(); }
        /**
         * Relayout boundary node id for the given node.
         * @return the protocol field value
         */
        @Nullable public Long nodeId() {
            return numberAsLong(value("nodeId"));
        }
        public static final class Builder {
            private final Map<String, Object> values = new LinkedHashMap<>();
            /**
             * Relayout boundary node id for the given node.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder nodeId(@Nullable Long value) {
                if (value == null) values.remove("nodeId");
                else values.put("nodeId", jsonValue(value));
                return this;
            }
            public GetRelayoutBoundaryResult build() {
                if (!values.containsKey("nodeId")) throw new IllegalStateException("Missing required CDP field: nodeId");
                return new GetRelayoutBoundaryResult(values);
            }
        }
    }
    /**
     * Returns search results from given {@code fromIndex} to given {@code toIndex} from the search with the given identifier.
     * <p><b>Experimental:</b> this part of CDP may change without notice.
     */
    public static final class GetSearchResultsParams extends CdpObject {
        private GetSearchResultsParams(Map<String, Object> values) { super(values); }
        @Nullable public static GetSearchResultsParams fromMap(@Nullable Map<String, Object> values) {
            return values == null ? null : new GetSearchResultsParams(values);
        }
        public static Builder builder() { return new Builder(); }
        /**
         * Unique search session identifier.
         * @return the protocol field value
         */
        @Nullable public String searchId() {
            return (String) value("searchId");
        }
        /**
         * Start index of the search result to be returned.
         * @return the protocol field value
         */
        @Nullable public Long fromIndex() {
            return numberAsLong(value("fromIndex"));
        }
        /**
         * End index of the search result to be returned.
         * @return the protocol field value
         */
        @Nullable public Long toIndex() {
            return numberAsLong(value("toIndex"));
        }
        public static final class Builder {
            private final Map<String, Object> values = new LinkedHashMap<>();
            /**
             * Unique search session identifier.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder searchId(@Nullable String value) {
                if (value == null) values.remove("searchId");
                else values.put("searchId", jsonValue(value));
                return this;
            }
            /**
             * Start index of the search result to be returned.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder fromIndex(@Nullable Long value) {
                if (value == null) values.remove("fromIndex");
                else values.put("fromIndex", jsonValue(value));
                return this;
            }
            /**
             * End index of the search result to be returned.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder toIndex(@Nullable Long value) {
                if (value == null) values.remove("toIndex");
                else values.put("toIndex", jsonValue(value));
                return this;
            }
            public GetSearchResultsParams build() {
                if (!values.containsKey("searchId")) throw new IllegalStateException("Missing required CDP field: searchId");
                if (!values.containsKey("fromIndex")) throw new IllegalStateException("Missing required CDP field: fromIndex");
                if (!values.containsKey("toIndex")) throw new IllegalStateException("Missing required CDP field: toIndex");
                return new GetSearchResultsParams(values);
            }
        }
    }
    /**
     * Returns search results from given {@code fromIndex} to given {@code toIndex} from the search with the given identifier.
     * <p><b>Experimental:</b> this part of CDP may change without notice.
     */
    public static final class GetSearchResultsResult extends CdpObject {
        private GetSearchResultsResult(Map<String, Object> values) { super(values); }
        @Nullable public static GetSearchResultsResult fromMap(@Nullable Map<String, Object> values) {
            return values == null ? null : new GetSearchResultsResult(values);
        }
        public static Builder builder() { return new Builder(); }
        /**
         * Ids of the search result nodes.
         * @return the protocol field value
         */
        @Nullable public java.util.List<Long> nodeIds() {
            return list(value("nodeIds"), element0 -> numberAsLong(element0));
        }
        public static final class Builder {
            private final Map<String, Object> values = new LinkedHashMap<>();
            /**
             * Ids of the search result nodes.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder nodeIds(@Nullable java.util.List<Long> value) {
                if (value == null) values.remove("nodeIds");
                else values.put("nodeIds", jsonValue(value));
                return this;
            }
            public GetSearchResultsResult build() {
                if (!values.containsKey("nodeIds")) throw new IllegalStateException("Missing required CDP field: nodeIds");
                return new GetSearchResultsResult(values);
            }
        }
    }
    /**
     * Hides any highlight.
     */
    public static final class HideHighlightParams extends CdpObject {
        private HideHighlightParams(Map<String, Object> values) { super(values); }
        @Nullable public static HideHighlightParams fromMap(@Nullable Map<String, Object> values) {
            return values == null ? null : new HideHighlightParams(values);
        }
        public static Builder builder() { return new Builder(); }
        public static final class Builder {
            private final Map<String, Object> values = new LinkedHashMap<>();
            public HideHighlightParams build() {
                return new HideHighlightParams(values);
            }
        }
    }
    /**
     * Hides any highlight.
     */
    public static final class HideHighlightResult extends CdpObject {
        private HideHighlightResult(Map<String, Object> values) { super(values); }
        @Nullable public static HideHighlightResult fromMap(@Nullable Map<String, Object> values) {
            return values == null ? null : new HideHighlightResult(values);
        }
        public static Builder builder() { return new Builder(); }
        public static final class Builder {
            private final Map<String, Object> values = new LinkedHashMap<>();
            public HideHighlightResult build() {
                return new HideHighlightResult(values);
            }
        }
    }
    /**
     * Highlights DOM node.
     */
    public static final class HighlightNodeParams extends CdpObject {
        private HighlightNodeParams(Map<String, Object> values) { super(values); }
        @Nullable public static HighlightNodeParams fromMap(@Nullable Map<String, Object> values) {
            return values == null ? null : new HighlightNodeParams(values);
        }
        public static Builder builder() { return new Builder(); }
        public static final class Builder {
            private final Map<String, Object> values = new LinkedHashMap<>();
            public HighlightNodeParams build() {
                return new HighlightNodeParams(values);
            }
        }
    }
    /**
     * Highlights DOM node.
     */
    public static final class HighlightNodeResult extends CdpObject {
        private HighlightNodeResult(Map<String, Object> values) { super(values); }
        @Nullable public static HighlightNodeResult fromMap(@Nullable Map<String, Object> values) {
            return values == null ? null : new HighlightNodeResult(values);
        }
        public static Builder builder() { return new Builder(); }
        public static final class Builder {
            private final Map<String, Object> values = new LinkedHashMap<>();
            public HighlightNodeResult build() {
                return new HighlightNodeResult(values);
            }
        }
    }
    /**
     * Highlights given rectangle.
     */
    public static final class HighlightRectParams extends CdpObject {
        private HighlightRectParams(Map<String, Object> values) { super(values); }
        @Nullable public static HighlightRectParams fromMap(@Nullable Map<String, Object> values) {
            return values == null ? null : new HighlightRectParams(values);
        }
        public static Builder builder() { return new Builder(); }
        public static final class Builder {
            private final Map<String, Object> values = new LinkedHashMap<>();
            public HighlightRectParams build() {
                return new HighlightRectParams(values);
            }
        }
    }
    /**
     * Highlights given rectangle.
     */
    public static final class HighlightRectResult extends CdpObject {
        private HighlightRectResult(Map<String, Object> values) { super(values); }
        @Nullable public static HighlightRectResult fromMap(@Nullable Map<String, Object> values) {
            return values == null ? null : new HighlightRectResult(values);
        }
        public static Builder builder() { return new Builder(); }
        public static final class Builder {
            private final Map<String, Object> values = new LinkedHashMap<>();
            public HighlightRectResult build() {
                return new HighlightRectResult(values);
            }
        }
    }
    /**
     * Marks last undoable state.
     * <p><b>Experimental:</b> this part of CDP may change without notice.
     */
    public static final class MarkUndoableStateParams extends CdpObject {
        private MarkUndoableStateParams(Map<String, Object> values) { super(values); }
        @Nullable public static MarkUndoableStateParams fromMap(@Nullable Map<String, Object> values) {
            return values == null ? null : new MarkUndoableStateParams(values);
        }
        public static Builder builder() { return new Builder(); }
        public static final class Builder {
            private final Map<String, Object> values = new LinkedHashMap<>();
            public MarkUndoableStateParams build() {
                return new MarkUndoableStateParams(values);
            }
        }
    }
    /**
     * Marks last undoable state.
     * <p><b>Experimental:</b> this part of CDP may change without notice.
     */
    public static final class MarkUndoableStateResult extends CdpObject {
        private MarkUndoableStateResult(Map<String, Object> values) { super(values); }
        @Nullable public static MarkUndoableStateResult fromMap(@Nullable Map<String, Object> values) {
            return values == null ? null : new MarkUndoableStateResult(values);
        }
        public static Builder builder() { return new Builder(); }
        public static final class Builder {
            private final Map<String, Object> values = new LinkedHashMap<>();
            public MarkUndoableStateResult build() {
                return new MarkUndoableStateResult(values);
            }
        }
    }
    /**
     * Moves node into the new container, places it before the given anchor.
     */
    public static final class MoveToParams extends CdpObject {
        private MoveToParams(Map<String, Object> values) { super(values); }
        @Nullable public static MoveToParams fromMap(@Nullable Map<String, Object> values) {
            return values == null ? null : new MoveToParams(values);
        }
        public static Builder builder() { return new Builder(); }
        /**
         * Id of the node to move.
         * @return the protocol field value
         */
        @Nullable public Long nodeId() {
            return numberAsLong(value("nodeId"));
        }
        /**
         * Id of the element to drop the moved node into.
         * @return the protocol field value
         */
        @Nullable public Long targetNodeId() {
            return numberAsLong(value("targetNodeId"));
        }
        /**
         * Drop node before this one (if absent, the moved node becomes the last child of {@code targetNodeId}).
         * @return the protocol field value
         */
        @Nullable public Long insertBeforeNodeId() {
            return numberAsLong(value("insertBeforeNodeId"));
        }
        public static final class Builder {
            private final Map<String, Object> values = new LinkedHashMap<>();
            /**
             * Id of the node to move.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder nodeId(@Nullable Long value) {
                if (value == null) values.remove("nodeId");
                else values.put("nodeId", jsonValue(value));
                return this;
            }
            /**
             * Id of the element to drop the moved node into.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder targetNodeId(@Nullable Long value) {
                if (value == null) values.remove("targetNodeId");
                else values.put("targetNodeId", jsonValue(value));
                return this;
            }
            /**
             * Drop node before this one (if absent, the moved node becomes the last child of {@code targetNodeId}).
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder insertBeforeNodeId(@Nullable Long value) {
                if (value == null) values.remove("insertBeforeNodeId");
                else values.put("insertBeforeNodeId", jsonValue(value));
                return this;
            }
            public MoveToParams build() {
                if (!values.containsKey("nodeId")) throw new IllegalStateException("Missing required CDP field: nodeId");
                if (!values.containsKey("targetNodeId")) throw new IllegalStateException("Missing required CDP field: targetNodeId");
                return new MoveToParams(values);
            }
        }
    }
    /**
     * Moves node into the new container, places it before the given anchor.
     */
    public static final class MoveToResult extends CdpObject {
        private MoveToResult(Map<String, Object> values) { super(values); }
        @Nullable public static MoveToResult fromMap(@Nullable Map<String, Object> values) {
            return values == null ? null : new MoveToResult(values);
        }
        public static Builder builder() { return new Builder(); }
        /**
         * New id of the moved node.
         * @return the protocol field value
         */
        @Nullable public Long nodeId() {
            return numberAsLong(value("nodeId"));
        }
        public static final class Builder {
            private final Map<String, Object> values = new LinkedHashMap<>();
            /**
             * New id of the moved node.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder nodeId(@Nullable Long value) {
                if (value == null) values.remove("nodeId");
                else values.put("nodeId", jsonValue(value));
                return this;
            }
            public MoveToResult build() {
                if (!values.containsKey("nodeId")) throw new IllegalStateException("Missing required CDP field: nodeId");
                return new MoveToResult(values);
            }
        }
    }
    /**
     * Searches for a given string in the DOM tree. Use {@code getSearchResults} to access search results or {@code cancelSearch} to end this search session.
     * <p><b>Experimental:</b> this part of CDP may change without notice.
     */
    public static final class PerformSearchParams extends CdpObject {
        private PerformSearchParams(Map<String, Object> values) { super(values); }
        @Nullable public static PerformSearchParams fromMap(@Nullable Map<String, Object> values) {
            return values == null ? null : new PerformSearchParams(values);
        }
        public static Builder builder() { return new Builder(); }
        /**
         * Plain text or query selector or XPath search query.
         * @return the protocol field value
         */
        @Nullable public String query() {
            return (String) value("query");
        }
        /**
         * True to search in user agent shadow DOM.
         * @return the protocol field value
         */
        @Nullable public Boolean includeUserAgentShadowDOM() {
            return (Boolean) value("includeUserAgentShadowDOM");
        }
        public static final class Builder {
            private final Map<String, Object> values = new LinkedHashMap<>();
            /**
             * Plain text or query selector or XPath search query.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder query(@Nullable String value) {
                if (value == null) values.remove("query");
                else values.put("query", jsonValue(value));
                return this;
            }
            /**
             * True to search in user agent shadow DOM.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder includeUserAgentShadowDOM(@Nullable Boolean value) {
                if (value == null) values.remove("includeUserAgentShadowDOM");
                else values.put("includeUserAgentShadowDOM", jsonValue(value));
                return this;
            }
            public PerformSearchParams build() {
                if (!values.containsKey("query")) throw new IllegalStateException("Missing required CDP field: query");
                return new PerformSearchParams(values);
            }
        }
    }
    /**
     * Searches for a given string in the DOM tree. Use {@code getSearchResults} to access search results or {@code cancelSearch} to end this search session.
     * <p><b>Experimental:</b> this part of CDP may change without notice.
     */
    public static final class PerformSearchResult extends CdpObject {
        private PerformSearchResult(Map<String, Object> values) { super(values); }
        @Nullable public static PerformSearchResult fromMap(@Nullable Map<String, Object> values) {
            return values == null ? null : new PerformSearchResult(values);
        }
        public static Builder builder() { return new Builder(); }
        /**
         * Unique search session identifier.
         * @return the protocol field value
         */
        @Nullable public String searchId() {
            return (String) value("searchId");
        }
        /**
         * Number of search results.
         * @return the protocol field value
         */
        @Nullable public Long resultCount() {
            return numberAsLong(value("resultCount"));
        }
        public static final class Builder {
            private final Map<String, Object> values = new LinkedHashMap<>();
            /**
             * Unique search session identifier.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder searchId(@Nullable String value) {
                if (value == null) values.remove("searchId");
                else values.put("searchId", jsonValue(value));
                return this;
            }
            /**
             * Number of search results.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder resultCount(@Nullable Long value) {
                if (value == null) values.remove("resultCount");
                else values.put("resultCount", jsonValue(value));
                return this;
            }
            public PerformSearchResult build() {
                if (!values.containsKey("searchId")) throw new IllegalStateException("Missing required CDP field: searchId");
                if (!values.containsKey("resultCount")) throw new IllegalStateException("Missing required CDP field: resultCount");
                return new PerformSearchResult(values);
            }
        }
    }
    /**
     * Requests that the node is sent to the caller given its path. // FIXME, use XPath
     * <p><b>Experimental:</b> this part of CDP may change without notice.
     */
    public static final class PushNodeByPathToFrontendParams extends CdpObject {
        private PushNodeByPathToFrontendParams(Map<String, Object> values) { super(values); }
        @Nullable public static PushNodeByPathToFrontendParams fromMap(@Nullable Map<String, Object> values) {
            return values == null ? null : new PushNodeByPathToFrontendParams(values);
        }
        public static Builder builder() { return new Builder(); }
        /**
         * Path to node in the proprietary format.
         * @return the protocol field value
         */
        @Nullable public String path() {
            return (String) value("path");
        }
        public static final class Builder {
            private final Map<String, Object> values = new LinkedHashMap<>();
            /**
             * Path to node in the proprietary format.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder path(@Nullable String value) {
                if (value == null) values.remove("path");
                else values.put("path", jsonValue(value));
                return this;
            }
            public PushNodeByPathToFrontendParams build() {
                if (!values.containsKey("path")) throw new IllegalStateException("Missing required CDP field: path");
                return new PushNodeByPathToFrontendParams(values);
            }
        }
    }
    /**
     * Requests that the node is sent to the caller given its path. // FIXME, use XPath
     * <p><b>Experimental:</b> this part of CDP may change without notice.
     */
    public static final class PushNodeByPathToFrontendResult extends CdpObject {
        private PushNodeByPathToFrontendResult(Map<String, Object> values) { super(values); }
        @Nullable public static PushNodeByPathToFrontendResult fromMap(@Nullable Map<String, Object> values) {
            return values == null ? null : new PushNodeByPathToFrontendResult(values);
        }
        public static Builder builder() { return new Builder(); }
        /**
         * Id of the node for given path.
         * @return the protocol field value
         */
        @Nullable public Long nodeId() {
            return numberAsLong(value("nodeId"));
        }
        public static final class Builder {
            private final Map<String, Object> values = new LinkedHashMap<>();
            /**
             * Id of the node for given path.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder nodeId(@Nullable Long value) {
                if (value == null) values.remove("nodeId");
                else values.put("nodeId", jsonValue(value));
                return this;
            }
            public PushNodeByPathToFrontendResult build() {
                if (!values.containsKey("nodeId")) throw new IllegalStateException("Missing required CDP field: nodeId");
                return new PushNodeByPathToFrontendResult(values);
            }
        }
    }
    /**
     * Requests that a batch of nodes is sent to the caller given their backend node ids.
     * <p><b>Experimental:</b> this part of CDP may change without notice.
     */
    public static final class PushNodesByBackendIdsToFrontendParams extends CdpObject {
        private PushNodesByBackendIdsToFrontendParams(Map<String, Object> values) { super(values); }
        @Nullable public static PushNodesByBackendIdsToFrontendParams fromMap(@Nullable Map<String, Object> values) {
            return values == null ? null : new PushNodesByBackendIdsToFrontendParams(values);
        }
        public static Builder builder() { return new Builder(); }
        /**
         * The array of backend node ids.
         * @return the protocol field value
         */
        @Nullable public java.util.List<Long> backendNodeIds() {
            return list(value("backendNodeIds"), element0 -> numberAsLong(element0));
        }
        public static final class Builder {
            private final Map<String, Object> values = new LinkedHashMap<>();
            /**
             * The array of backend node ids.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder backendNodeIds(@Nullable java.util.List<Long> value) {
                if (value == null) values.remove("backendNodeIds");
                else values.put("backendNodeIds", jsonValue(value));
                return this;
            }
            public PushNodesByBackendIdsToFrontendParams build() {
                if (!values.containsKey("backendNodeIds")) throw new IllegalStateException("Missing required CDP field: backendNodeIds");
                return new PushNodesByBackendIdsToFrontendParams(values);
            }
        }
    }
    /**
     * Requests that a batch of nodes is sent to the caller given their backend node ids.
     * <p><b>Experimental:</b> this part of CDP may change without notice.
     */
    public static final class PushNodesByBackendIdsToFrontendResult extends CdpObject {
        private PushNodesByBackendIdsToFrontendResult(Map<String, Object> values) { super(values); }
        @Nullable public static PushNodesByBackendIdsToFrontendResult fromMap(@Nullable Map<String, Object> values) {
            return values == null ? null : new PushNodesByBackendIdsToFrontendResult(values);
        }
        public static Builder builder() { return new Builder(); }
        /**
         * The array of ids of pushed nodes that correspond to the backend ids specified in backendNodeIds.
         * @return the protocol field value
         */
        @Nullable public java.util.List<Long> nodeIds() {
            return list(value("nodeIds"), element0 -> numberAsLong(element0));
        }
        public static final class Builder {
            private final Map<String, Object> values = new LinkedHashMap<>();
            /**
             * The array of ids of pushed nodes that correspond to the backend ids specified in backendNodeIds.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder nodeIds(@Nullable java.util.List<Long> value) {
                if (value == null) values.remove("nodeIds");
                else values.put("nodeIds", jsonValue(value));
                return this;
            }
            public PushNodesByBackendIdsToFrontendResult build() {
                if (!values.containsKey("nodeIds")) throw new IllegalStateException("Missing required CDP field: nodeIds");
                return new PushNodesByBackendIdsToFrontendResult(values);
            }
        }
    }
    /**
     * Executes {@code querySelector} on a given node.
     */
    public static final class QuerySelectorParams extends CdpObject {
        private QuerySelectorParams(Map<String, Object> values) { super(values); }
        @Nullable public static QuerySelectorParams fromMap(@Nullable Map<String, Object> values) {
            return values == null ? null : new QuerySelectorParams(values);
        }
        public static Builder builder() { return new Builder(); }
        /**
         * Id of the node to query upon.
         * @return the protocol field value
         */
        @Nullable public Long nodeId() {
            return numberAsLong(value("nodeId"));
        }
        /**
         * Selector string.
         * @return the protocol field value
         */
        @Nullable public String selector() {
            return (String) value("selector");
        }
        public static final class Builder {
            private final Map<String, Object> values = new LinkedHashMap<>();
            /**
             * Id of the node to query upon.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder nodeId(@Nullable Long value) {
                if (value == null) values.remove("nodeId");
                else values.put("nodeId", jsonValue(value));
                return this;
            }
            /**
             * Selector string.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder selector(@Nullable String value) {
                if (value == null) values.remove("selector");
                else values.put("selector", jsonValue(value));
                return this;
            }
            public QuerySelectorParams build() {
                if (!values.containsKey("nodeId")) throw new IllegalStateException("Missing required CDP field: nodeId");
                if (!values.containsKey("selector")) throw new IllegalStateException("Missing required CDP field: selector");
                return new QuerySelectorParams(values);
            }
        }
    }
    /**
     * Executes {@code querySelector} on a given node.
     */
    public static final class QuerySelectorResult extends CdpObject {
        private QuerySelectorResult(Map<String, Object> values) { super(values); }
        @Nullable public static QuerySelectorResult fromMap(@Nullable Map<String, Object> values) {
            return values == null ? null : new QuerySelectorResult(values);
        }
        public static Builder builder() { return new Builder(); }
        /**
         * Query selector result.
         * @return the protocol field value
         */
        @Nullable public Long nodeId() {
            return numberAsLong(value("nodeId"));
        }
        public static final class Builder {
            private final Map<String, Object> values = new LinkedHashMap<>();
            /**
             * Query selector result.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder nodeId(@Nullable Long value) {
                if (value == null) values.remove("nodeId");
                else values.put("nodeId", jsonValue(value));
                return this;
            }
            public QuerySelectorResult build() {
                if (!values.containsKey("nodeId")) throw new IllegalStateException("Missing required CDP field: nodeId");
                return new QuerySelectorResult(values);
            }
        }
    }
    /**
     * Executes {@code querySelectorAll} on a given node.
     */
    public static final class QuerySelectorAllParams extends CdpObject {
        private QuerySelectorAllParams(Map<String, Object> values) { super(values); }
        @Nullable public static QuerySelectorAllParams fromMap(@Nullable Map<String, Object> values) {
            return values == null ? null : new QuerySelectorAllParams(values);
        }
        public static Builder builder() { return new Builder(); }
        /**
         * Id of the node to query upon.
         * @return the protocol field value
         */
        @Nullable public Long nodeId() {
            return numberAsLong(value("nodeId"));
        }
        /**
         * Selector string.
         * @return the protocol field value
         */
        @Nullable public String selector() {
            return (String) value("selector");
        }
        public static final class Builder {
            private final Map<String, Object> values = new LinkedHashMap<>();
            /**
             * Id of the node to query upon.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder nodeId(@Nullable Long value) {
                if (value == null) values.remove("nodeId");
                else values.put("nodeId", jsonValue(value));
                return this;
            }
            /**
             * Selector string.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder selector(@Nullable String value) {
                if (value == null) values.remove("selector");
                else values.put("selector", jsonValue(value));
                return this;
            }
            public QuerySelectorAllParams build() {
                if (!values.containsKey("nodeId")) throw new IllegalStateException("Missing required CDP field: nodeId");
                if (!values.containsKey("selector")) throw new IllegalStateException("Missing required CDP field: selector");
                return new QuerySelectorAllParams(values);
            }
        }
    }
    /**
     * Executes {@code querySelectorAll} on a given node.
     */
    public static final class QuerySelectorAllResult extends CdpObject {
        private QuerySelectorAllResult(Map<String, Object> values) { super(values); }
        @Nullable public static QuerySelectorAllResult fromMap(@Nullable Map<String, Object> values) {
            return values == null ? null : new QuerySelectorAllResult(values);
        }
        public static Builder builder() { return new Builder(); }
        /**
         * Query selector result.
         * @return the protocol field value
         */
        @Nullable public java.util.List<Long> nodeIds() {
            return list(value("nodeIds"), element0 -> numberAsLong(element0));
        }
        public static final class Builder {
            private final Map<String, Object> values = new LinkedHashMap<>();
            /**
             * Query selector result.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder nodeIds(@Nullable java.util.List<Long> value) {
                if (value == null) values.remove("nodeIds");
                else values.put("nodeIds", jsonValue(value));
                return this;
            }
            public QuerySelectorAllResult build() {
                if (!values.containsKey("nodeIds")) throw new IllegalStateException("Missing required CDP field: nodeIds");
                return new QuerySelectorAllResult(values);
            }
        }
    }
    /**
     * Returns NodeIds of current top layer elements. Top layer is rendered closest to the user within a viewport, therefore its elements always appear on top of all other content.
     * <p><b>Experimental:</b> this part of CDP may change without notice.
     */
    public static final class GetTopLayerElementsParams extends CdpObject {
        private GetTopLayerElementsParams(Map<String, Object> values) { super(values); }
        @Nullable public static GetTopLayerElementsParams fromMap(@Nullable Map<String, Object> values) {
            return values == null ? null : new GetTopLayerElementsParams(values);
        }
        public static Builder builder() { return new Builder(); }
        public static final class Builder {
            private final Map<String, Object> values = new LinkedHashMap<>();
            public GetTopLayerElementsParams build() {
                return new GetTopLayerElementsParams(values);
            }
        }
    }
    /**
     * Returns NodeIds of current top layer elements. Top layer is rendered closest to the user within a viewport, therefore its elements always appear on top of all other content.
     * <p><b>Experimental:</b> this part of CDP may change without notice.
     */
    public static final class GetTopLayerElementsResult extends CdpObject {
        private GetTopLayerElementsResult(Map<String, Object> values) { super(values); }
        @Nullable public static GetTopLayerElementsResult fromMap(@Nullable Map<String, Object> values) {
            return values == null ? null : new GetTopLayerElementsResult(values);
        }
        public static Builder builder() { return new Builder(); }
        /**
         * NodeIds of top layer elements
         * @return the protocol field value
         */
        @Nullable public java.util.List<Long> nodeIds() {
            return list(value("nodeIds"), element0 -> numberAsLong(element0));
        }
        public static final class Builder {
            private final Map<String, Object> values = new LinkedHashMap<>();
            /**
             * NodeIds of top layer elements
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder nodeIds(@Nullable java.util.List<Long> value) {
                if (value == null) values.remove("nodeIds");
                else values.put("nodeIds", jsonValue(value));
                return this;
            }
            public GetTopLayerElementsResult build() {
                if (!values.containsKey("nodeIds")) throw new IllegalStateException("Missing required CDP field: nodeIds");
                return new GetTopLayerElementsResult(values);
            }
        }
    }
    /**
     * Returns the NodeId of the matched element according to certain relations.
     * <p><b>Experimental:</b> this part of CDP may change without notice.
     */
    public static final class GetElementByRelationParams extends CdpObject {
        private GetElementByRelationParams(Map<String, Object> values) { super(values); }
        @Nullable public static GetElementByRelationParams fromMap(@Nullable Map<String, Object> values) {
            return values == null ? null : new GetElementByRelationParams(values);
        }
        public static Builder builder() { return new Builder(); }
        /**
         * Id of the node from which to query the relation.
         * @return the protocol field value
         */
        @Nullable public Long nodeId() {
            return numberAsLong(value("nodeId"));
        }
        /**
         * Type of relation to get.
         * @return the protocol field value
         */
        @Nullable public String relation() {
            return (String) value("relation");
        }
        /**
         * Type of relation to get.
         */
        public static final class RelationValues {
            private RelationValues() {}
            public static final String POPOVERTARGET = "PopoverTarget";
            public static final String INTERESTTARGET = "InterestTarget";
            public static final String COMMANDFOR = "CommandFor";
        }
        public static final class Builder {
            private final Map<String, Object> values = new LinkedHashMap<>();
            /**
             * Id of the node from which to query the relation.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder nodeId(@Nullable Long value) {
                if (value == null) values.remove("nodeId");
                else values.put("nodeId", jsonValue(value));
                return this;
            }
            /**
             * Type of relation to get.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder relation(@Nullable String value) {
                if (value == null) values.remove("relation");
                else values.put("relation", jsonValue(value));
                return this;
            }
            public GetElementByRelationParams build() {
                if (!values.containsKey("nodeId")) throw new IllegalStateException("Missing required CDP field: nodeId");
                if (!values.containsKey("relation")) throw new IllegalStateException("Missing required CDP field: relation");
                return new GetElementByRelationParams(values);
            }
        }
    }
    /**
     * Returns the NodeId of the matched element according to certain relations.
     * <p><b>Experimental:</b> this part of CDP may change without notice.
     */
    public static final class GetElementByRelationResult extends CdpObject {
        private GetElementByRelationResult(Map<String, Object> values) { super(values); }
        @Nullable public static GetElementByRelationResult fromMap(@Nullable Map<String, Object> values) {
            return values == null ? null : new GetElementByRelationResult(values);
        }
        public static Builder builder() { return new Builder(); }
        /**
         * NodeId of the element matching the queried relation.
         * @return the protocol field value
         */
        @Nullable public Long nodeId() {
            return numberAsLong(value("nodeId"));
        }
        public static final class Builder {
            private final Map<String, Object> values = new LinkedHashMap<>();
            /**
             * NodeId of the element matching the queried relation.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder nodeId(@Nullable Long value) {
                if (value == null) values.remove("nodeId");
                else values.put("nodeId", jsonValue(value));
                return this;
            }
            public GetElementByRelationResult build() {
                if (!values.containsKey("nodeId")) throw new IllegalStateException("Missing required CDP field: nodeId");
                return new GetElementByRelationResult(values);
            }
        }
    }
    /**
     * Re-does the last undone action.
     * <p><b>Experimental:</b> this part of CDP may change without notice.
     */
    public static final class RedoParams extends CdpObject {
        private RedoParams(Map<String, Object> values) { super(values); }
        @Nullable public static RedoParams fromMap(@Nullable Map<String, Object> values) {
            return values == null ? null : new RedoParams(values);
        }
        public static Builder builder() { return new Builder(); }
        public static final class Builder {
            private final Map<String, Object> values = new LinkedHashMap<>();
            public RedoParams build() {
                return new RedoParams(values);
            }
        }
    }
    /**
     * Re-does the last undone action.
     * <p><b>Experimental:</b> this part of CDP may change without notice.
     */
    public static final class RedoResult extends CdpObject {
        private RedoResult(Map<String, Object> values) { super(values); }
        @Nullable public static RedoResult fromMap(@Nullable Map<String, Object> values) {
            return values == null ? null : new RedoResult(values);
        }
        public static Builder builder() { return new Builder(); }
        public static final class Builder {
            private final Map<String, Object> values = new LinkedHashMap<>();
            public RedoResult build() {
                return new RedoResult(values);
            }
        }
    }
    /**
     * Removes attribute with given name from an element with given id.
     */
    public static final class RemoveAttributeParams extends CdpObject {
        private RemoveAttributeParams(Map<String, Object> values) { super(values); }
        @Nullable public static RemoveAttributeParams fromMap(@Nullable Map<String, Object> values) {
            return values == null ? null : new RemoveAttributeParams(values);
        }
        public static Builder builder() { return new Builder(); }
        /**
         * Id of the element to remove attribute from.
         * @return the protocol field value
         */
        @Nullable public Long nodeId() {
            return numberAsLong(value("nodeId"));
        }
        /**
         * Name of the attribute to remove.
         * @return the protocol field value
         */
        @Nullable public String name() {
            return (String) value("name");
        }
        public static final class Builder {
            private final Map<String, Object> values = new LinkedHashMap<>();
            /**
             * Id of the element to remove attribute from.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder nodeId(@Nullable Long value) {
                if (value == null) values.remove("nodeId");
                else values.put("nodeId", jsonValue(value));
                return this;
            }
            /**
             * Name of the attribute to remove.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder name(@Nullable String value) {
                if (value == null) values.remove("name");
                else values.put("name", jsonValue(value));
                return this;
            }
            public RemoveAttributeParams build() {
                if (!values.containsKey("nodeId")) throw new IllegalStateException("Missing required CDP field: nodeId");
                if (!values.containsKey("name")) throw new IllegalStateException("Missing required CDP field: name");
                return new RemoveAttributeParams(values);
            }
        }
    }
    /**
     * Removes attribute with given name from an element with given id.
     */
    public static final class RemoveAttributeResult extends CdpObject {
        private RemoveAttributeResult(Map<String, Object> values) { super(values); }
        @Nullable public static RemoveAttributeResult fromMap(@Nullable Map<String, Object> values) {
            return values == null ? null : new RemoveAttributeResult(values);
        }
        public static Builder builder() { return new Builder(); }
        public static final class Builder {
            private final Map<String, Object> values = new LinkedHashMap<>();
            public RemoveAttributeResult build() {
                return new RemoveAttributeResult(values);
            }
        }
    }
    /**
     * Removes node with given id.
     */
    public static final class RemoveNodeParams extends CdpObject {
        private RemoveNodeParams(Map<String, Object> values) { super(values); }
        @Nullable public static RemoveNodeParams fromMap(@Nullable Map<String, Object> values) {
            return values == null ? null : new RemoveNodeParams(values);
        }
        public static Builder builder() { return new Builder(); }
        /**
         * Id of the node to remove.
         * @return the protocol field value
         */
        @Nullable public Long nodeId() {
            return numberAsLong(value("nodeId"));
        }
        public static final class Builder {
            private final Map<String, Object> values = new LinkedHashMap<>();
            /**
             * Id of the node to remove.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder nodeId(@Nullable Long value) {
                if (value == null) values.remove("nodeId");
                else values.put("nodeId", jsonValue(value));
                return this;
            }
            public RemoveNodeParams build() {
                if (!values.containsKey("nodeId")) throw new IllegalStateException("Missing required CDP field: nodeId");
                return new RemoveNodeParams(values);
            }
        }
    }
    /**
     * Removes node with given id.
     */
    public static final class RemoveNodeResult extends CdpObject {
        private RemoveNodeResult(Map<String, Object> values) { super(values); }
        @Nullable public static RemoveNodeResult fromMap(@Nullable Map<String, Object> values) {
            return values == null ? null : new RemoveNodeResult(values);
        }
        public static Builder builder() { return new Builder(); }
        public static final class Builder {
            private final Map<String, Object> values = new LinkedHashMap<>();
            public RemoveNodeResult build() {
                return new RemoveNodeResult(values);
            }
        }
    }
    /**
     * Requests that children of the node with given id are returned to the caller in form of {@code setChildNodes} events where not only immediate children are retrieved, but all children down to the specified depth.
     */
    public static final class RequestChildNodesParams extends CdpObject {
        private RequestChildNodesParams(Map<String, Object> values) { super(values); }
        @Nullable public static RequestChildNodesParams fromMap(@Nullable Map<String, Object> values) {
            return values == null ? null : new RequestChildNodesParams(values);
        }
        public static Builder builder() { return new Builder(); }
        /**
         * Id of the node to get children for.
         * @return the protocol field value
         */
        @Nullable public Long nodeId() {
            return numberAsLong(value("nodeId"));
        }
        /**
         * The maximum depth at which children should be retrieved, defaults to 1. Use -1 for the entire subtree or provide an integer larger than 0.
         * @return the protocol field value
         */
        @Nullable public Long depth() {
            return numberAsLong(value("depth"));
        }
        /**
         * Whether or not iframes and shadow roots should be traversed when returning the sub-tree (default is false).
         * @return the protocol field value
         */
        @Nullable public Boolean pierce() {
            return (Boolean) value("pierce");
        }
        public static final class Builder {
            private final Map<String, Object> values = new LinkedHashMap<>();
            /**
             * Id of the node to get children for.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder nodeId(@Nullable Long value) {
                if (value == null) values.remove("nodeId");
                else values.put("nodeId", jsonValue(value));
                return this;
            }
            /**
             * The maximum depth at which children should be retrieved, defaults to 1. Use -1 for the entire subtree or provide an integer larger than 0.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder depth(@Nullable Long value) {
                if (value == null) values.remove("depth");
                else values.put("depth", jsonValue(value));
                return this;
            }
            /**
             * Whether or not iframes and shadow roots should be traversed when returning the sub-tree (default is false).
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder pierce(@Nullable Boolean value) {
                if (value == null) values.remove("pierce");
                else values.put("pierce", jsonValue(value));
                return this;
            }
            public RequestChildNodesParams build() {
                if (!values.containsKey("nodeId")) throw new IllegalStateException("Missing required CDP field: nodeId");
                return new RequestChildNodesParams(values);
            }
        }
    }
    /**
     * Requests that children of the node with given id are returned to the caller in form of {@code setChildNodes} events where not only immediate children are retrieved, but all children down to the specified depth.
     */
    public static final class RequestChildNodesResult extends CdpObject {
        private RequestChildNodesResult(Map<String, Object> values) { super(values); }
        @Nullable public static RequestChildNodesResult fromMap(@Nullable Map<String, Object> values) {
            return values == null ? null : new RequestChildNodesResult(values);
        }
        public static Builder builder() { return new Builder(); }
        public static final class Builder {
            private final Map<String, Object> values = new LinkedHashMap<>();
            public RequestChildNodesResult build() {
                return new RequestChildNodesResult(values);
            }
        }
    }
    /**
     * Requests that the node is sent to the caller given the JavaScript node object reference. All nodes that form the path from the node to the root are also sent to the client as a series of {@code setChildNodes} notifications.
     */
    public static final class RequestNodeParams extends CdpObject {
        private RequestNodeParams(Map<String, Object> values) { super(values); }
        @Nullable public static RequestNodeParams fromMap(@Nullable Map<String, Object> values) {
            return values == null ? null : new RequestNodeParams(values);
        }
        public static Builder builder() { return new Builder(); }
        /**
         * JavaScript object id to convert into node.
         * @return the protocol field value
         */
        @Nullable public String objectId() {
            return (String) value("objectId");
        }
        public static final class Builder {
            private final Map<String, Object> values = new LinkedHashMap<>();
            /**
             * JavaScript object id to convert into node.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder objectId(@Nullable String value) {
                if (value == null) values.remove("objectId");
                else values.put("objectId", jsonValue(value));
                return this;
            }
            public RequestNodeParams build() {
                if (!values.containsKey("objectId")) throw new IllegalStateException("Missing required CDP field: objectId");
                return new RequestNodeParams(values);
            }
        }
    }
    /**
     * Requests that the node is sent to the caller given the JavaScript node object reference. All nodes that form the path from the node to the root are also sent to the client as a series of {@code setChildNodes} notifications.
     */
    public static final class RequestNodeResult extends CdpObject {
        private RequestNodeResult(Map<String, Object> values) { super(values); }
        @Nullable public static RequestNodeResult fromMap(@Nullable Map<String, Object> values) {
            return values == null ? null : new RequestNodeResult(values);
        }
        public static Builder builder() { return new Builder(); }
        /**
         * Node id for given object.
         * @return the protocol field value
         */
        @Nullable public Long nodeId() {
            return numberAsLong(value("nodeId"));
        }
        public static final class Builder {
            private final Map<String, Object> values = new LinkedHashMap<>();
            /**
             * Node id for given object.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder nodeId(@Nullable Long value) {
                if (value == null) values.remove("nodeId");
                else values.put("nodeId", jsonValue(value));
                return this;
            }
            public RequestNodeResult build() {
                if (!values.containsKey("nodeId")) throw new IllegalStateException("Missing required CDP field: nodeId");
                return new RequestNodeResult(values);
            }
        }
    }
    /**
     * Resolves the JavaScript node object for a given NodeId or BackendNodeId.
     */
    public static final class ResolveNodeParams extends CdpObject {
        private ResolveNodeParams(Map<String, Object> values) { super(values); }
        @Nullable public static ResolveNodeParams fromMap(@Nullable Map<String, Object> values) {
            return values == null ? null : new ResolveNodeParams(values);
        }
        public static Builder builder() { return new Builder(); }
        /**
         * Id of the node to resolve.
         * @return the protocol field value
         */
        @Nullable public Long nodeId() {
            return numberAsLong(value("nodeId"));
        }
        /**
         * Backend identifier of the node to resolve.
         * @return the protocol field value
         */
        @Nullable public Long backendNodeId() {
            return numberAsLong(value("backendNodeId"));
        }
        /**
         * Symbolic group name that can be used to release multiple objects.
         * @return the protocol field value
         */
        @Nullable public String objectGroup() {
            return (String) value("objectGroup");
        }
        /**
         * Execution context in which to resolve the node.
         * @return the protocol field value
         */
        @Nullable public Long executionContextId() {
            return numberAsLong(value("executionContextId"));
        }
        public static final class Builder {
            private final Map<String, Object> values = new LinkedHashMap<>();
            /**
             * Id of the node to resolve.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder nodeId(@Nullable Long value) {
                if (value == null) values.remove("nodeId");
                else values.put("nodeId", jsonValue(value));
                return this;
            }
            /**
             * Backend identifier of the node to resolve.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder backendNodeId(@Nullable Long value) {
                if (value == null) values.remove("backendNodeId");
                else values.put("backendNodeId", jsonValue(value));
                return this;
            }
            /**
             * Symbolic group name that can be used to release multiple objects.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder objectGroup(@Nullable String value) {
                if (value == null) values.remove("objectGroup");
                else values.put("objectGroup", jsonValue(value));
                return this;
            }
            /**
             * Execution context in which to resolve the node.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder executionContextId(@Nullable Long value) {
                if (value == null) values.remove("executionContextId");
                else values.put("executionContextId", jsonValue(value));
                return this;
            }
            public ResolveNodeParams build() {
                return new ResolveNodeParams(values);
            }
        }
    }
    /**
     * Resolves the JavaScript node object for a given NodeId or BackendNodeId.
     */
    public static final class ResolveNodeResult extends CdpObject {
        private ResolveNodeResult(Map<String, Object> values) { super(values); }
        @Nullable public static ResolveNodeResult fromMap(@Nullable Map<String, Object> values) {
            return values == null ? null : new ResolveNodeResult(values);
        }
        public static Builder builder() { return new Builder(); }
        /**
         * JavaScript object wrapper for given node.
         * @return the protocol field value
         */
        @Nullable public Runtime.RemoteObject object() {
            return Runtime.RemoteObject.fromMap(objectMap(value("object")));
        }
        public static final class Builder {
            private final Map<String, Object> values = new LinkedHashMap<>();
            /**
             * JavaScript object wrapper for given node.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder object(@Nullable Runtime.RemoteObject value) {
                if (value == null) values.remove("object");
                else values.put("object", jsonValue(value));
                return this;
            }
            public ResolveNodeResult build() {
                if (!values.containsKey("object")) throw new IllegalStateException("Missing required CDP field: object");
                return new ResolveNodeResult(values);
            }
        }
    }
    /**
     * Sets attribute for an element with given id.
     */
    public static final class SetAttributeValueParams extends CdpObject {
        private SetAttributeValueParams(Map<String, Object> values) { super(values); }
        @Nullable public static SetAttributeValueParams fromMap(@Nullable Map<String, Object> values) {
            return values == null ? null : new SetAttributeValueParams(values);
        }
        public static Builder builder() { return new Builder(); }
        /**
         * Id of the element to set attribute for.
         * @return the protocol field value
         */
        @Nullable public Long nodeId() {
            return numberAsLong(value("nodeId"));
        }
        /**
         * Attribute name.
         * @return the protocol field value
         */
        @Nullable public String name() {
            return (String) value("name");
        }
        /**
         * Attribute value.
         * @return the protocol field value
         */
        @Nullable public String value() {
            return (String) value("value");
        }
        public static final class Builder {
            private final Map<String, Object> values = new LinkedHashMap<>();
            /**
             * Id of the element to set attribute for.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder nodeId(@Nullable Long value) {
                if (value == null) values.remove("nodeId");
                else values.put("nodeId", jsonValue(value));
                return this;
            }
            /**
             * Attribute name.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder name(@Nullable String value) {
                if (value == null) values.remove("name");
                else values.put("name", jsonValue(value));
                return this;
            }
            /**
             * Attribute value.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder value(@Nullable String value) {
                if (value == null) values.remove("value");
                else values.put("value", jsonValue(value));
                return this;
            }
            public SetAttributeValueParams build() {
                if (!values.containsKey("nodeId")) throw new IllegalStateException("Missing required CDP field: nodeId");
                if (!values.containsKey("name")) throw new IllegalStateException("Missing required CDP field: name");
                if (!values.containsKey("value")) throw new IllegalStateException("Missing required CDP field: value");
                return new SetAttributeValueParams(values);
            }
        }
    }
    /**
     * Sets attribute for an element with given id.
     */
    public static final class SetAttributeValueResult extends CdpObject {
        private SetAttributeValueResult(Map<String, Object> values) { super(values); }
        @Nullable public static SetAttributeValueResult fromMap(@Nullable Map<String, Object> values) {
            return values == null ? null : new SetAttributeValueResult(values);
        }
        public static Builder builder() { return new Builder(); }
        public static final class Builder {
            private final Map<String, Object> values = new LinkedHashMap<>();
            public SetAttributeValueResult build() {
                return new SetAttributeValueResult(values);
            }
        }
    }
    /**
     * Sets attributes on element with given id. This method is useful when user edits some existing attribute value and types in several attribute name/value pairs.
     */
    public static final class SetAttributesAsTextParams extends CdpObject {
        private SetAttributesAsTextParams(Map<String, Object> values) { super(values); }
        @Nullable public static SetAttributesAsTextParams fromMap(@Nullable Map<String, Object> values) {
            return values == null ? null : new SetAttributesAsTextParams(values);
        }
        public static Builder builder() { return new Builder(); }
        /**
         * Id of the element to set attributes for.
         * @return the protocol field value
         */
        @Nullable public Long nodeId() {
            return numberAsLong(value("nodeId"));
        }
        /**
         * Text with a number of attributes. Will parse this text using HTML parser.
         * @return the protocol field value
         */
        @Nullable public String text() {
            return (String) value("text");
        }
        /**
         * Attribute name to replace with new attributes derived from text in case text parsed successfully.
         * @return the protocol field value
         */
        @Nullable public String name() {
            return (String) value("name");
        }
        public static final class Builder {
            private final Map<String, Object> values = new LinkedHashMap<>();
            /**
             * Id of the element to set attributes for.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder nodeId(@Nullable Long value) {
                if (value == null) values.remove("nodeId");
                else values.put("nodeId", jsonValue(value));
                return this;
            }
            /**
             * Text with a number of attributes. Will parse this text using HTML parser.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder text(@Nullable String value) {
                if (value == null) values.remove("text");
                else values.put("text", jsonValue(value));
                return this;
            }
            /**
             * Attribute name to replace with new attributes derived from text in case text parsed successfully.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder name(@Nullable String value) {
                if (value == null) values.remove("name");
                else values.put("name", jsonValue(value));
                return this;
            }
            public SetAttributesAsTextParams build() {
                if (!values.containsKey("nodeId")) throw new IllegalStateException("Missing required CDP field: nodeId");
                if (!values.containsKey("text")) throw new IllegalStateException("Missing required CDP field: text");
                return new SetAttributesAsTextParams(values);
            }
        }
    }
    /**
     * Sets attributes on element with given id. This method is useful when user edits some existing attribute value and types in several attribute name/value pairs.
     */
    public static final class SetAttributesAsTextResult extends CdpObject {
        private SetAttributesAsTextResult(Map<String, Object> values) { super(values); }
        @Nullable public static SetAttributesAsTextResult fromMap(@Nullable Map<String, Object> values) {
            return values == null ? null : new SetAttributesAsTextResult(values);
        }
        public static Builder builder() { return new Builder(); }
        public static final class Builder {
            private final Map<String, Object> values = new LinkedHashMap<>();
            public SetAttributesAsTextResult build() {
                return new SetAttributesAsTextResult(values);
            }
        }
    }
    /**
     * Sets files for the given file input element.
     */
    public static final class SetFileInputFilesParams extends CdpObject {
        private SetFileInputFilesParams(Map<String, Object> values) { super(values); }
        @Nullable public static SetFileInputFilesParams fromMap(@Nullable Map<String, Object> values) {
            return values == null ? null : new SetFileInputFilesParams(values);
        }
        public static Builder builder() { return new Builder(); }
        /**
         * Array of file paths to set.
         * @return the protocol field value
         */
        @Nullable public java.util.List<String> files() {
            return list(value("files"), element0 -> (String) element0);
        }
        /**
         * Identifier of the node.
         * @return the protocol field value
         */
        @Nullable public Long nodeId() {
            return numberAsLong(value("nodeId"));
        }
        /**
         * Identifier of the backend node.
         * @return the protocol field value
         */
        @Nullable public Long backendNodeId() {
            return numberAsLong(value("backendNodeId"));
        }
        /**
         * JavaScript object id of the node wrapper.
         * @return the protocol field value
         */
        @Nullable public String objectId() {
            return (String) value("objectId");
        }
        public static final class Builder {
            private final Map<String, Object> values = new LinkedHashMap<>();
            /**
             * Array of file paths to set.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder files(@Nullable java.util.List<String> value) {
                if (value == null) values.remove("files");
                else values.put("files", jsonValue(value));
                return this;
            }
            /**
             * Identifier of the node.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder nodeId(@Nullable Long value) {
                if (value == null) values.remove("nodeId");
                else values.put("nodeId", jsonValue(value));
                return this;
            }
            /**
             * Identifier of the backend node.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder backendNodeId(@Nullable Long value) {
                if (value == null) values.remove("backendNodeId");
                else values.put("backendNodeId", jsonValue(value));
                return this;
            }
            /**
             * JavaScript object id of the node wrapper.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder objectId(@Nullable String value) {
                if (value == null) values.remove("objectId");
                else values.put("objectId", jsonValue(value));
                return this;
            }
            public SetFileInputFilesParams build() {
                if (!values.containsKey("files")) throw new IllegalStateException("Missing required CDP field: files");
                return new SetFileInputFilesParams(values);
            }
        }
    }
    /**
     * Sets files for the given file input element.
     */
    public static final class SetFileInputFilesResult extends CdpObject {
        private SetFileInputFilesResult(Map<String, Object> values) { super(values); }
        @Nullable public static SetFileInputFilesResult fromMap(@Nullable Map<String, Object> values) {
            return values == null ? null : new SetFileInputFilesResult(values);
        }
        public static Builder builder() { return new Builder(); }
        public static final class Builder {
            private final Map<String, Object> values = new LinkedHashMap<>();
            public SetFileInputFilesResult build() {
                return new SetFileInputFilesResult(values);
            }
        }
    }
    /**
     * Sets if stack traces should be captured for Nodes. See {@code Node.getNodeStackTraces}. Default is disabled.
     * <p><b>Experimental:</b> this part of CDP may change without notice.
     */
    public static final class SetNodeStackTracesEnabledParams extends CdpObject {
        private SetNodeStackTracesEnabledParams(Map<String, Object> values) { super(values); }
        @Nullable public static SetNodeStackTracesEnabledParams fromMap(@Nullable Map<String, Object> values) {
            return values == null ? null : new SetNodeStackTracesEnabledParams(values);
        }
        public static Builder builder() { return new Builder(); }
        /**
         * Enable or disable.
         * @return the protocol field value
         */
        @Nullable public Boolean enable() {
            return (Boolean) value("enable");
        }
        public static final class Builder {
            private final Map<String, Object> values = new LinkedHashMap<>();
            /**
             * Enable or disable.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder enable(@Nullable Boolean value) {
                if (value == null) values.remove("enable");
                else values.put("enable", jsonValue(value));
                return this;
            }
            public SetNodeStackTracesEnabledParams build() {
                if (!values.containsKey("enable")) throw new IllegalStateException("Missing required CDP field: enable");
                return new SetNodeStackTracesEnabledParams(values);
            }
        }
    }
    /**
     * Sets if stack traces should be captured for Nodes. See {@code Node.getNodeStackTraces}. Default is disabled.
     * <p><b>Experimental:</b> this part of CDP may change without notice.
     */
    public static final class SetNodeStackTracesEnabledResult extends CdpObject {
        private SetNodeStackTracesEnabledResult(Map<String, Object> values) { super(values); }
        @Nullable public static SetNodeStackTracesEnabledResult fromMap(@Nullable Map<String, Object> values) {
            return values == null ? null : new SetNodeStackTracesEnabledResult(values);
        }
        public static Builder builder() { return new Builder(); }
        public static final class Builder {
            private final Map<String, Object> values = new LinkedHashMap<>();
            public SetNodeStackTracesEnabledResult build() {
                return new SetNodeStackTracesEnabledResult(values);
            }
        }
    }
    /**
     * Gets stack traces associated with a Node. As of now, only provides stack trace for Node creation.
     * <p><b>Experimental:</b> this part of CDP may change without notice.
     */
    public static final class GetNodeStackTracesParams extends CdpObject {
        private GetNodeStackTracesParams(Map<String, Object> values) { super(values); }
        @Nullable public static GetNodeStackTracesParams fromMap(@Nullable Map<String, Object> values) {
            return values == null ? null : new GetNodeStackTracesParams(values);
        }
        public static Builder builder() { return new Builder(); }
        /**
         * Id of the node to get stack traces for.
         * @return the protocol field value
         */
        @Nullable public Long nodeId() {
            return numberAsLong(value("nodeId"));
        }
        public static final class Builder {
            private final Map<String, Object> values = new LinkedHashMap<>();
            /**
             * Id of the node to get stack traces for.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder nodeId(@Nullable Long value) {
                if (value == null) values.remove("nodeId");
                else values.put("nodeId", jsonValue(value));
                return this;
            }
            public GetNodeStackTracesParams build() {
                if (!values.containsKey("nodeId")) throw new IllegalStateException("Missing required CDP field: nodeId");
                return new GetNodeStackTracesParams(values);
            }
        }
    }
    /**
     * Gets stack traces associated with a Node. As of now, only provides stack trace for Node creation.
     * <p><b>Experimental:</b> this part of CDP may change without notice.
     */
    public static final class GetNodeStackTracesResult extends CdpObject {
        private GetNodeStackTracesResult(Map<String, Object> values) { super(values); }
        @Nullable public static GetNodeStackTracesResult fromMap(@Nullable Map<String, Object> values) {
            return values == null ? null : new GetNodeStackTracesResult(values);
        }
        public static Builder builder() { return new Builder(); }
        /**
         * Creation stack trace, if available.
         * @return the protocol field value
         */
        @Nullable public Runtime.StackTrace creation() {
            return Runtime.StackTrace.fromMap(objectMap(value("creation")));
        }
        public static final class Builder {
            private final Map<String, Object> values = new LinkedHashMap<>();
            /**
             * Creation stack trace, if available.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder creation(@Nullable Runtime.StackTrace value) {
                if (value == null) values.remove("creation");
                else values.put("creation", jsonValue(value));
                return this;
            }
            public GetNodeStackTracesResult build() {
                return new GetNodeStackTracesResult(values);
            }
        }
    }
    /**
     * Returns file information for the given File wrapper.
     * <p><b>Experimental:</b> this part of CDP may change without notice.
     */
    public static final class GetFileInfoParams extends CdpObject {
        private GetFileInfoParams(Map<String, Object> values) { super(values); }
        @Nullable public static GetFileInfoParams fromMap(@Nullable Map<String, Object> values) {
            return values == null ? null : new GetFileInfoParams(values);
        }
        public static Builder builder() { return new Builder(); }
        /**
         * JavaScript object id of the node wrapper.
         * @return the protocol field value
         */
        @Nullable public String objectId() {
            return (String) value("objectId");
        }
        public static final class Builder {
            private final Map<String, Object> values = new LinkedHashMap<>();
            /**
             * JavaScript object id of the node wrapper.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder objectId(@Nullable String value) {
                if (value == null) values.remove("objectId");
                else values.put("objectId", jsonValue(value));
                return this;
            }
            public GetFileInfoParams build() {
                if (!values.containsKey("objectId")) throw new IllegalStateException("Missing required CDP field: objectId");
                return new GetFileInfoParams(values);
            }
        }
    }
    /**
     * Returns file information for the given File wrapper.
     * <p><b>Experimental:</b> this part of CDP may change without notice.
     */
    public static final class GetFileInfoResult extends CdpObject {
        private GetFileInfoResult(Map<String, Object> values) { super(values); }
        @Nullable public static GetFileInfoResult fromMap(@Nullable Map<String, Object> values) {
            return values == null ? null : new GetFileInfoResult(values);
        }
        public static Builder builder() { return new Builder(); }
        /**
         * Returns the path field.
         * @return the protocol field value
         */
        @Nullable public String path() {
            return (String) value("path");
        }
        public static final class Builder {
            private final Map<String, Object> values = new LinkedHashMap<>();
            /**
             * Sets the path field.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder path(@Nullable String value) {
                if (value == null) values.remove("path");
                else values.put("path", jsonValue(value));
                return this;
            }
            public GetFileInfoResult build() {
                if (!values.containsKey("path")) throw new IllegalStateException("Missing required CDP field: path");
                return new GetFileInfoResult(values);
            }
        }
    }
    /**
     * Returns list of detached nodes
     * <p><b>Experimental:</b> this part of CDP may change without notice.
     */
    public static final class GetDetachedDomNodesParams extends CdpObject {
        private GetDetachedDomNodesParams(Map<String, Object> values) { super(values); }
        @Nullable public static GetDetachedDomNodesParams fromMap(@Nullable Map<String, Object> values) {
            return values == null ? null : new GetDetachedDomNodesParams(values);
        }
        public static Builder builder() { return new Builder(); }
        public static final class Builder {
            private final Map<String, Object> values = new LinkedHashMap<>();
            public GetDetachedDomNodesParams build() {
                return new GetDetachedDomNodesParams(values);
            }
        }
    }
    /**
     * Returns list of detached nodes
     * <p><b>Experimental:</b> this part of CDP may change without notice.
     */
    public static final class GetDetachedDomNodesResult extends CdpObject {
        private GetDetachedDomNodesResult(Map<String, Object> values) { super(values); }
        @Nullable public static GetDetachedDomNodesResult fromMap(@Nullable Map<String, Object> values) {
            return values == null ? null : new GetDetachedDomNodesResult(values);
        }
        public static Builder builder() { return new Builder(); }
        /**
         * The list of detached nodes
         * @return the protocol field value
         */
        @Nullable public java.util.List<DOM.DetachedElementInfo> detachedNodes() {
            return list(value("detachedNodes"), element0 -> DOM.DetachedElementInfo.fromMap(objectMap(element0)));
        }
        public static final class Builder {
            private final Map<String, Object> values = new LinkedHashMap<>();
            /**
             * The list of detached nodes
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder detachedNodes(@Nullable java.util.List<DOM.DetachedElementInfo> value) {
                if (value == null) values.remove("detachedNodes");
                else values.put("detachedNodes", jsonValue(value));
                return this;
            }
            public GetDetachedDomNodesResult build() {
                if (!values.containsKey("detachedNodes")) throw new IllegalStateException("Missing required CDP field: detachedNodes");
                return new GetDetachedDomNodesResult(values);
            }
        }
    }
    /**
     * Enables console to refer to the node with given id via $x (see Command Line API for more details $x functions).
     * <p><b>Experimental:</b> this part of CDP may change without notice.
     */
    public static final class SetInspectedNodeParams extends CdpObject {
        private SetInspectedNodeParams(Map<String, Object> values) { super(values); }
        @Nullable public static SetInspectedNodeParams fromMap(@Nullable Map<String, Object> values) {
            return values == null ? null : new SetInspectedNodeParams(values);
        }
        public static Builder builder() { return new Builder(); }
        /**
         * DOM node id to be accessible by means of $x command line API.
         * @return the protocol field value
         */
        @Nullable public Long nodeId() {
            return numberAsLong(value("nodeId"));
        }
        public static final class Builder {
            private final Map<String, Object> values = new LinkedHashMap<>();
            /**
             * DOM node id to be accessible by means of $x command line API.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder nodeId(@Nullable Long value) {
                if (value == null) values.remove("nodeId");
                else values.put("nodeId", jsonValue(value));
                return this;
            }
            public SetInspectedNodeParams build() {
                if (!values.containsKey("nodeId")) throw new IllegalStateException("Missing required CDP field: nodeId");
                return new SetInspectedNodeParams(values);
            }
        }
    }
    /**
     * Enables console to refer to the node with given id via $x (see Command Line API for more details $x functions).
     * <p><b>Experimental:</b> this part of CDP may change without notice.
     */
    public static final class SetInspectedNodeResult extends CdpObject {
        private SetInspectedNodeResult(Map<String, Object> values) { super(values); }
        @Nullable public static SetInspectedNodeResult fromMap(@Nullable Map<String, Object> values) {
            return values == null ? null : new SetInspectedNodeResult(values);
        }
        public static Builder builder() { return new Builder(); }
        public static final class Builder {
            private final Map<String, Object> values = new LinkedHashMap<>();
            public SetInspectedNodeResult build() {
                return new SetInspectedNodeResult(values);
            }
        }
    }
    /**
     * Sets node name for a node with given id.
     */
    public static final class SetNodeNameParams extends CdpObject {
        private SetNodeNameParams(Map<String, Object> values) { super(values); }
        @Nullable public static SetNodeNameParams fromMap(@Nullable Map<String, Object> values) {
            return values == null ? null : new SetNodeNameParams(values);
        }
        public static Builder builder() { return new Builder(); }
        /**
         * Id of the node to set name for.
         * @return the protocol field value
         */
        @Nullable public Long nodeId() {
            return numberAsLong(value("nodeId"));
        }
        /**
         * New node&#x27;s name.
         * @return the protocol field value
         */
        @Nullable public String name() {
            return (String) value("name");
        }
        public static final class Builder {
            private final Map<String, Object> values = new LinkedHashMap<>();
            /**
             * Id of the node to set name for.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder nodeId(@Nullable Long value) {
                if (value == null) values.remove("nodeId");
                else values.put("nodeId", jsonValue(value));
                return this;
            }
            /**
             * New node&#x27;s name.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder name(@Nullable String value) {
                if (value == null) values.remove("name");
                else values.put("name", jsonValue(value));
                return this;
            }
            public SetNodeNameParams build() {
                if (!values.containsKey("nodeId")) throw new IllegalStateException("Missing required CDP field: nodeId");
                if (!values.containsKey("name")) throw new IllegalStateException("Missing required CDP field: name");
                return new SetNodeNameParams(values);
            }
        }
    }
    /**
     * Sets node name for a node with given id.
     */
    public static final class SetNodeNameResult extends CdpObject {
        private SetNodeNameResult(Map<String, Object> values) { super(values); }
        @Nullable public static SetNodeNameResult fromMap(@Nullable Map<String, Object> values) {
            return values == null ? null : new SetNodeNameResult(values);
        }
        public static Builder builder() { return new Builder(); }
        /**
         * New node&#x27;s id.
         * @return the protocol field value
         */
        @Nullable public Long nodeId() {
            return numberAsLong(value("nodeId"));
        }
        public static final class Builder {
            private final Map<String, Object> values = new LinkedHashMap<>();
            /**
             * New node&#x27;s id.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder nodeId(@Nullable Long value) {
                if (value == null) values.remove("nodeId");
                else values.put("nodeId", jsonValue(value));
                return this;
            }
            public SetNodeNameResult build() {
                if (!values.containsKey("nodeId")) throw new IllegalStateException("Missing required CDP field: nodeId");
                return new SetNodeNameResult(values);
            }
        }
    }
    /**
     * Sets node value for a node with given id.
     */
    public static final class SetNodeValueParams extends CdpObject {
        private SetNodeValueParams(Map<String, Object> values) { super(values); }
        @Nullable public static SetNodeValueParams fromMap(@Nullable Map<String, Object> values) {
            return values == null ? null : new SetNodeValueParams(values);
        }
        public static Builder builder() { return new Builder(); }
        /**
         * Id of the node to set value for.
         * @return the protocol field value
         */
        @Nullable public Long nodeId() {
            return numberAsLong(value("nodeId"));
        }
        /**
         * New node&#x27;s value.
         * @return the protocol field value
         */
        @Nullable public String value() {
            return (String) value("value");
        }
        public static final class Builder {
            private final Map<String, Object> values = new LinkedHashMap<>();
            /**
             * Id of the node to set value for.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder nodeId(@Nullable Long value) {
                if (value == null) values.remove("nodeId");
                else values.put("nodeId", jsonValue(value));
                return this;
            }
            /**
             * New node&#x27;s value.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder value(@Nullable String value) {
                if (value == null) values.remove("value");
                else values.put("value", jsonValue(value));
                return this;
            }
            public SetNodeValueParams build() {
                if (!values.containsKey("nodeId")) throw new IllegalStateException("Missing required CDP field: nodeId");
                if (!values.containsKey("value")) throw new IllegalStateException("Missing required CDP field: value");
                return new SetNodeValueParams(values);
            }
        }
    }
    /**
     * Sets node value for a node with given id.
     */
    public static final class SetNodeValueResult extends CdpObject {
        private SetNodeValueResult(Map<String, Object> values) { super(values); }
        @Nullable public static SetNodeValueResult fromMap(@Nullable Map<String, Object> values) {
            return values == null ? null : new SetNodeValueResult(values);
        }
        public static Builder builder() { return new Builder(); }
        public static final class Builder {
            private final Map<String, Object> values = new LinkedHashMap<>();
            public SetNodeValueResult build() {
                return new SetNodeValueResult(values);
            }
        }
    }
    /**
     * Sets node HTML markup, returns new node id.
     */
    public static final class SetOuterHTMLParams extends CdpObject {
        private SetOuterHTMLParams(Map<String, Object> values) { super(values); }
        @Nullable public static SetOuterHTMLParams fromMap(@Nullable Map<String, Object> values) {
            return values == null ? null : new SetOuterHTMLParams(values);
        }
        public static Builder builder() { return new Builder(); }
        /**
         * Id of the node to set markup for.
         * @return the protocol field value
         */
        @Nullable public Long nodeId() {
            return numberAsLong(value("nodeId"));
        }
        /**
         * Outer HTML markup to set.
         * @return the protocol field value
         */
        @Nullable public String outerHTML() {
            return (String) value("outerHTML");
        }
        public static final class Builder {
            private final Map<String, Object> values = new LinkedHashMap<>();
            /**
             * Id of the node to set markup for.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder nodeId(@Nullable Long value) {
                if (value == null) values.remove("nodeId");
                else values.put("nodeId", jsonValue(value));
                return this;
            }
            /**
             * Outer HTML markup to set.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder outerHTML(@Nullable String value) {
                if (value == null) values.remove("outerHTML");
                else values.put("outerHTML", jsonValue(value));
                return this;
            }
            public SetOuterHTMLParams build() {
                if (!values.containsKey("nodeId")) throw new IllegalStateException("Missing required CDP field: nodeId");
                if (!values.containsKey("outerHTML")) throw new IllegalStateException("Missing required CDP field: outerHTML");
                return new SetOuterHTMLParams(values);
            }
        }
    }
    /**
     * Sets node HTML markup, returns new node id.
     */
    public static final class SetOuterHTMLResult extends CdpObject {
        private SetOuterHTMLResult(Map<String, Object> values) { super(values); }
        @Nullable public static SetOuterHTMLResult fromMap(@Nullable Map<String, Object> values) {
            return values == null ? null : new SetOuterHTMLResult(values);
        }
        public static Builder builder() { return new Builder(); }
        public static final class Builder {
            private final Map<String, Object> values = new LinkedHashMap<>();
            public SetOuterHTMLResult build() {
                return new SetOuterHTMLResult(values);
            }
        }
    }
    /**
     * Undoes the last performed action.
     * <p><b>Experimental:</b> this part of CDP may change without notice.
     */
    public static final class UndoParams extends CdpObject {
        private UndoParams(Map<String, Object> values) { super(values); }
        @Nullable public static UndoParams fromMap(@Nullable Map<String, Object> values) {
            return values == null ? null : new UndoParams(values);
        }
        public static Builder builder() { return new Builder(); }
        public static final class Builder {
            private final Map<String, Object> values = new LinkedHashMap<>();
            public UndoParams build() {
                return new UndoParams(values);
            }
        }
    }
    /**
     * Undoes the last performed action.
     * <p><b>Experimental:</b> this part of CDP may change without notice.
     */
    public static final class UndoResult extends CdpObject {
        private UndoResult(Map<String, Object> values) { super(values); }
        @Nullable public static UndoResult fromMap(@Nullable Map<String, Object> values) {
            return values == null ? null : new UndoResult(values);
        }
        public static Builder builder() { return new Builder(); }
        public static final class Builder {
            private final Map<String, Object> values = new LinkedHashMap<>();
            public UndoResult build() {
                return new UndoResult(values);
            }
        }
    }
    /**
     * Returns iframe node that owns iframe with the given domain.
     * <p><b>Experimental:</b> this part of CDP may change without notice.
     */
    public static final class GetFrameOwnerParams extends CdpObject {
        private GetFrameOwnerParams(Map<String, Object> values) { super(values); }
        @Nullable public static GetFrameOwnerParams fromMap(@Nullable Map<String, Object> values) {
            return values == null ? null : new GetFrameOwnerParams(values);
        }
        public static Builder builder() { return new Builder(); }
        /**
         * Returns the frameId field.
         * @return the protocol field value
         */
        @Nullable public String frameId() {
            return (String) value("frameId");
        }
        public static final class Builder {
            private final Map<String, Object> values = new LinkedHashMap<>();
            /**
             * Sets the frameId field.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder frameId(@Nullable String value) {
                if (value == null) values.remove("frameId");
                else values.put("frameId", jsonValue(value));
                return this;
            }
            public GetFrameOwnerParams build() {
                if (!values.containsKey("frameId")) throw new IllegalStateException("Missing required CDP field: frameId");
                return new GetFrameOwnerParams(values);
            }
        }
    }
    /**
     * Returns iframe node that owns iframe with the given domain.
     * <p><b>Experimental:</b> this part of CDP may change without notice.
     */
    public static final class GetFrameOwnerResult extends CdpObject {
        private GetFrameOwnerResult(Map<String, Object> values) { super(values); }
        @Nullable public static GetFrameOwnerResult fromMap(@Nullable Map<String, Object> values) {
            return values == null ? null : new GetFrameOwnerResult(values);
        }
        public static Builder builder() { return new Builder(); }
        /**
         * Resulting node.
         * @return the protocol field value
         */
        @Nullable public Long backendNodeId() {
            return numberAsLong(value("backendNodeId"));
        }
        /**
         * Id of the node at given coordinates, only when enabled and requested document.
         * @return the protocol field value
         */
        @Nullable public Long nodeId() {
            return numberAsLong(value("nodeId"));
        }
        public static final class Builder {
            private final Map<String, Object> values = new LinkedHashMap<>();
            /**
             * Resulting node.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder backendNodeId(@Nullable Long value) {
                if (value == null) values.remove("backendNodeId");
                else values.put("backendNodeId", jsonValue(value));
                return this;
            }
            /**
             * Id of the node at given coordinates, only when enabled and requested document.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder nodeId(@Nullable Long value) {
                if (value == null) values.remove("nodeId");
                else values.put("nodeId", jsonValue(value));
                return this;
            }
            public GetFrameOwnerResult build() {
                if (!values.containsKey("backendNodeId")) throw new IllegalStateException("Missing required CDP field: backendNodeId");
                return new GetFrameOwnerResult(values);
            }
        }
    }
    /**
     * Returns the query container of the given node based on container query conditions: containerName, physical and logical axes, and whether it queries scroll-state or anchored elements. If no axes are provided and queriesScrollState is false, the style container is returned, which is the direct parent or the closest element with a matching container-name.
     * <p><b>Experimental:</b> this part of CDP may change without notice.
     */
    public static final class GetContainerForNodeParams extends CdpObject {
        private GetContainerForNodeParams(Map<String, Object> values) { super(values); }
        @Nullable public static GetContainerForNodeParams fromMap(@Nullable Map<String, Object> values) {
            return values == null ? null : new GetContainerForNodeParams(values);
        }
        public static Builder builder() { return new Builder(); }
        /**
         * Returns the nodeId field.
         * @return the protocol field value
         */
        @Nullable public Long nodeId() {
            return numberAsLong(value("nodeId"));
        }
        /**
         * Returns the containerName field.
         * @return the protocol field value
         */
        @Nullable public String containerName() {
            return (String) value("containerName");
        }
        /**
         * Returns the physicalAxes field.
         * @return the protocol field value
         */
        @Nullable public String physicalAxes() {
            return (String) value("physicalAxes");
        }
        /**
         * Returns the logicalAxes field.
         * @return the protocol field value
         */
        @Nullable public String logicalAxes() {
            return (String) value("logicalAxes");
        }
        /**
         * Returns the queriesScrollState field.
         * @return the protocol field value
         */
        @Nullable public Boolean queriesScrollState() {
            return (Boolean) value("queriesScrollState");
        }
        /**
         * Returns the queriesAnchored field.
         * @return the protocol field value
         */
        @Nullable public Boolean queriesAnchored() {
            return (Boolean) value("queriesAnchored");
        }
        public static final class Builder {
            private final Map<String, Object> values = new LinkedHashMap<>();
            /**
             * Sets the nodeId field.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder nodeId(@Nullable Long value) {
                if (value == null) values.remove("nodeId");
                else values.put("nodeId", jsonValue(value));
                return this;
            }
            /**
             * Sets the containerName field.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder containerName(@Nullable String value) {
                if (value == null) values.remove("containerName");
                else values.put("containerName", jsonValue(value));
                return this;
            }
            /**
             * Sets the physicalAxes field.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder physicalAxes(@Nullable String value) {
                if (value == null) values.remove("physicalAxes");
                else values.put("physicalAxes", jsonValue(value));
                return this;
            }
            /**
             * Sets the logicalAxes field.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder logicalAxes(@Nullable String value) {
                if (value == null) values.remove("logicalAxes");
                else values.put("logicalAxes", jsonValue(value));
                return this;
            }
            /**
             * Sets the queriesScrollState field.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder queriesScrollState(@Nullable Boolean value) {
                if (value == null) values.remove("queriesScrollState");
                else values.put("queriesScrollState", jsonValue(value));
                return this;
            }
            /**
             * Sets the queriesAnchored field.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder queriesAnchored(@Nullable Boolean value) {
                if (value == null) values.remove("queriesAnchored");
                else values.put("queriesAnchored", jsonValue(value));
                return this;
            }
            public GetContainerForNodeParams build() {
                if (!values.containsKey("nodeId")) throw new IllegalStateException("Missing required CDP field: nodeId");
                return new GetContainerForNodeParams(values);
            }
        }
    }
    /**
     * Returns the query container of the given node based on container query conditions: containerName, physical and logical axes, and whether it queries scroll-state or anchored elements. If no axes are provided and queriesScrollState is false, the style container is returned, which is the direct parent or the closest element with a matching container-name.
     * <p><b>Experimental:</b> this part of CDP may change without notice.
     */
    public static final class GetContainerForNodeResult extends CdpObject {
        private GetContainerForNodeResult(Map<String, Object> values) { super(values); }
        @Nullable public static GetContainerForNodeResult fromMap(@Nullable Map<String, Object> values) {
            return values == null ? null : new GetContainerForNodeResult(values);
        }
        public static Builder builder() { return new Builder(); }
        /**
         * The container node for the given node, or null if not found.
         * @return the protocol field value
         */
        @Nullable public Long nodeId() {
            return numberAsLong(value("nodeId"));
        }
        public static final class Builder {
            private final Map<String, Object> values = new LinkedHashMap<>();
            /**
             * The container node for the given node, or null if not found.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder nodeId(@Nullable Long value) {
                if (value == null) values.remove("nodeId");
                else values.put("nodeId", jsonValue(value));
                return this;
            }
            public GetContainerForNodeResult build() {
                return new GetContainerForNodeResult(values);
            }
        }
    }
    /**
     * Returns the descendants of a container query container that have container queries against this container.
     * <p><b>Experimental:</b> this part of CDP may change without notice.
     */
    public static final class GetQueryingDescendantsForContainerParams extends CdpObject {
        private GetQueryingDescendantsForContainerParams(Map<String, Object> values) { super(values); }
        @Nullable public static GetQueryingDescendantsForContainerParams fromMap(@Nullable Map<String, Object> values) {
            return values == null ? null : new GetQueryingDescendantsForContainerParams(values);
        }
        public static Builder builder() { return new Builder(); }
        /**
         * Id of the container node to find querying descendants from.
         * @return the protocol field value
         */
        @Nullable public Long nodeId() {
            return numberAsLong(value("nodeId"));
        }
        public static final class Builder {
            private final Map<String, Object> values = new LinkedHashMap<>();
            /**
             * Id of the container node to find querying descendants from.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder nodeId(@Nullable Long value) {
                if (value == null) values.remove("nodeId");
                else values.put("nodeId", jsonValue(value));
                return this;
            }
            public GetQueryingDescendantsForContainerParams build() {
                if (!values.containsKey("nodeId")) throw new IllegalStateException("Missing required CDP field: nodeId");
                return new GetQueryingDescendantsForContainerParams(values);
            }
        }
    }
    /**
     * Returns the descendants of a container query container that have container queries against this container.
     * <p><b>Experimental:</b> this part of CDP may change without notice.
     */
    public static final class GetQueryingDescendantsForContainerResult extends CdpObject {
        private GetQueryingDescendantsForContainerResult(Map<String, Object> values) { super(values); }
        @Nullable public static GetQueryingDescendantsForContainerResult fromMap(@Nullable Map<String, Object> values) {
            return values == null ? null : new GetQueryingDescendantsForContainerResult(values);
        }
        public static Builder builder() { return new Builder(); }
        /**
         * Descendant nodes with container queries against the given container.
         * @return the protocol field value
         */
        @Nullable public java.util.List<Long> nodeIds() {
            return list(value("nodeIds"), element0 -> numberAsLong(element0));
        }
        public static final class Builder {
            private final Map<String, Object> values = new LinkedHashMap<>();
            /**
             * Descendant nodes with container queries against the given container.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder nodeIds(@Nullable java.util.List<Long> value) {
                if (value == null) values.remove("nodeIds");
                else values.put("nodeIds", jsonValue(value));
                return this;
            }
            public GetQueryingDescendantsForContainerResult build() {
                if (!values.containsKey("nodeIds")) throw new IllegalStateException("Missing required CDP field: nodeIds");
                return new GetQueryingDescendantsForContainerResult(values);
            }
        }
    }
    /**
     * Returns the target anchor element of the given anchor query according to https://www.w3.org/TR/css-anchor-position-1/#target.
     * <p><b>Experimental:</b> this part of CDP may change without notice.
     */
    public static final class GetAnchorElementParams extends CdpObject {
        private GetAnchorElementParams(Map<String, Object> values) { super(values); }
        @Nullable public static GetAnchorElementParams fromMap(@Nullable Map<String, Object> values) {
            return values == null ? null : new GetAnchorElementParams(values);
        }
        public static Builder builder() { return new Builder(); }
        /**
         * Id of the positioned element from which to find the anchor.
         * @return the protocol field value
         */
        @Nullable public Long nodeId() {
            return numberAsLong(value("nodeId"));
        }
        /**
         * An optional anchor specifier, as defined in https://www.w3.org/TR/css-anchor-position-1/#anchor-specifier. If not provided, it will return the implicit anchor element for the given positioned element.
         * @return the protocol field value
         */
        @Nullable public String anchorSpecifier() {
            return (String) value("anchorSpecifier");
        }
        public static final class Builder {
            private final Map<String, Object> values = new LinkedHashMap<>();
            /**
             * Id of the positioned element from which to find the anchor.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder nodeId(@Nullable Long value) {
                if (value == null) values.remove("nodeId");
                else values.put("nodeId", jsonValue(value));
                return this;
            }
            /**
             * An optional anchor specifier, as defined in https://www.w3.org/TR/css-anchor-position-1/#anchor-specifier. If not provided, it will return the implicit anchor element for the given positioned element.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder anchorSpecifier(@Nullable String value) {
                if (value == null) values.remove("anchorSpecifier");
                else values.put("anchorSpecifier", jsonValue(value));
                return this;
            }
            public GetAnchorElementParams build() {
                if (!values.containsKey("nodeId")) throw new IllegalStateException("Missing required CDP field: nodeId");
                return new GetAnchorElementParams(values);
            }
        }
    }
    /**
     * Returns the target anchor element of the given anchor query according to https://www.w3.org/TR/css-anchor-position-1/#target.
     * <p><b>Experimental:</b> this part of CDP may change without notice.
     */
    public static final class GetAnchorElementResult extends CdpObject {
        private GetAnchorElementResult(Map<String, Object> values) { super(values); }
        @Nullable public static GetAnchorElementResult fromMap(@Nullable Map<String, Object> values) {
            return values == null ? null : new GetAnchorElementResult(values);
        }
        public static Builder builder() { return new Builder(); }
        /**
         * The anchor element of the given anchor query.
         * @return the protocol field value
         */
        @Nullable public Long nodeId() {
            return numberAsLong(value("nodeId"));
        }
        public static final class Builder {
            private final Map<String, Object> values = new LinkedHashMap<>();
            /**
             * The anchor element of the given anchor query.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder nodeId(@Nullable Long value) {
                if (value == null) values.remove("nodeId");
                else values.put("nodeId", jsonValue(value));
                return this;
            }
            public GetAnchorElementResult build() {
                if (!values.containsKey("nodeId")) throw new IllegalStateException("Missing required CDP field: nodeId");
                return new GetAnchorElementResult(values);
            }
        }
    }
    /**
     * When enabling, this API force-opens the popover identified by nodeId and keeps it open until disabled.
     * <p><b>Experimental:</b> this part of CDP may change without notice.
     */
    public static final class ForceShowPopoverParams extends CdpObject {
        private ForceShowPopoverParams(Map<String, Object> values) { super(values); }
        @Nullable public static ForceShowPopoverParams fromMap(@Nullable Map<String, Object> values) {
            return values == null ? null : new ForceShowPopoverParams(values);
        }
        public static Builder builder() { return new Builder(); }
        /**
         * Id of the popover HTMLElement
         * @return the protocol field value
         */
        @Nullable public Long nodeId() {
            return numberAsLong(value("nodeId"));
        }
        /**
         * If true, opens the popover and keeps it open. If false, closes the popover if it was previously force-opened.
         * @return the protocol field value
         */
        @Nullable public Boolean enable() {
            return (Boolean) value("enable");
        }
        public static final class Builder {
            private final Map<String, Object> values = new LinkedHashMap<>();
            /**
             * Id of the popover HTMLElement
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder nodeId(@Nullable Long value) {
                if (value == null) values.remove("nodeId");
                else values.put("nodeId", jsonValue(value));
                return this;
            }
            /**
             * If true, opens the popover and keeps it open. If false, closes the popover if it was previously force-opened.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder enable(@Nullable Boolean value) {
                if (value == null) values.remove("enable");
                else values.put("enable", jsonValue(value));
                return this;
            }
            public ForceShowPopoverParams build() {
                if (!values.containsKey("nodeId")) throw new IllegalStateException("Missing required CDP field: nodeId");
                if (!values.containsKey("enable")) throw new IllegalStateException("Missing required CDP field: enable");
                return new ForceShowPopoverParams(values);
            }
        }
    }
    /**
     * When enabling, this API force-opens the popover identified by nodeId and keeps it open until disabled.
     * <p><b>Experimental:</b> this part of CDP may change without notice.
     */
    public static final class ForceShowPopoverResult extends CdpObject {
        private ForceShowPopoverResult(Map<String, Object> values) { super(values); }
        @Nullable public static ForceShowPopoverResult fromMap(@Nullable Map<String, Object> values) {
            return values == null ? null : new ForceShowPopoverResult(values);
        }
        public static Builder builder() { return new Builder(); }
        /**
         * List of popovers that were closed in order to respect popover stacking order.
         * @return the protocol field value
         */
        @Nullable public java.util.List<Long> nodeIds() {
            return list(value("nodeIds"), element0 -> numberAsLong(element0));
        }
        public static final class Builder {
            private final Map<String, Object> values = new LinkedHashMap<>();
            /**
             * List of popovers that were closed in order to respect popover stacking order.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder nodeIds(@Nullable java.util.List<Long> value) {
                if (value == null) values.remove("nodeIds");
                else values.put("nodeIds", jsonValue(value));
                return this;
            }
            public ForceShowPopoverResult build() {
                if (!values.containsKey("nodeIds")) throw new IllegalStateException("Missing required CDP field: nodeIds");
                return new ForceShowPopoverResult(values);
            }
        }
    }
    /**
     * Fired when {@code Element}&#x27;s attribute is modified.
     */
    public static final class AttributeModifiedEvent extends CdpObject {
        private AttributeModifiedEvent(Map<String, Object> values) { super(values); }
        @Nullable public static AttributeModifiedEvent fromMap(@Nullable Map<String, Object> values) {
            return values == null ? null : new AttributeModifiedEvent(values);
        }
        public static Builder builder() { return new Builder(); }
        /**
         * Id of the node that has changed.
         * @return the protocol field value
         */
        @Nullable public Long nodeId() {
            return numberAsLong(value("nodeId"));
        }
        /**
         * Attribute name.
         * @return the protocol field value
         */
        @Nullable public String name() {
            return (String) value("name");
        }
        /**
         * Attribute value.
         * @return the protocol field value
         */
        @Nullable public String value() {
            return (String) value("value");
        }
        public static final class Builder {
            private final Map<String, Object> values = new LinkedHashMap<>();
            /**
             * Id of the node that has changed.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder nodeId(@Nullable Long value) {
                if (value == null) values.remove("nodeId");
                else values.put("nodeId", jsonValue(value));
                return this;
            }
            /**
             * Attribute name.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder name(@Nullable String value) {
                if (value == null) values.remove("name");
                else values.put("name", jsonValue(value));
                return this;
            }
            /**
             * Attribute value.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder value(@Nullable String value) {
                if (value == null) values.remove("value");
                else values.put("value", jsonValue(value));
                return this;
            }
            public AttributeModifiedEvent build() {
                if (!values.containsKey("nodeId")) throw new IllegalStateException("Missing required CDP field: nodeId");
                if (!values.containsKey("name")) throw new IllegalStateException("Missing required CDP field: name");
                if (!values.containsKey("value")) throw new IllegalStateException("Missing required CDP field: value");
                return new AttributeModifiedEvent(values);
            }
        }
    }
    /**
     * Fired when {@code Element}&#x27;s adoptedStyleSheets are modified.
     * <p><b>Experimental:</b> this part of CDP may change without notice.
     */
    public static final class AdoptedStyleSheetsModifiedEvent extends CdpObject {
        private AdoptedStyleSheetsModifiedEvent(Map<String, Object> values) { super(values); }
        @Nullable public static AdoptedStyleSheetsModifiedEvent fromMap(@Nullable Map<String, Object> values) {
            return values == null ? null : new AdoptedStyleSheetsModifiedEvent(values);
        }
        public static Builder builder() { return new Builder(); }
        /**
         * Id of the node that has changed.
         * @return the protocol field value
         */
        @Nullable public Long nodeId() {
            return numberAsLong(value("nodeId"));
        }
        /**
         * New adoptedStyleSheets array.
         * <p><b>Experimental:</b> this part of CDP may change without notice.
         * @return the protocol field value
         */
        @Nullable public java.util.List<String> adoptedStyleSheets() {
            return list(value("adoptedStyleSheets"), element0 -> (String) element0);
        }
        public static final class Builder {
            private final Map<String, Object> values = new LinkedHashMap<>();
            /**
             * Id of the node that has changed.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder nodeId(@Nullable Long value) {
                if (value == null) values.remove("nodeId");
                else values.put("nodeId", jsonValue(value));
                return this;
            }
            /**
             * New adoptedStyleSheets array.
             * <p><b>Experimental:</b> this part of CDP may change without notice.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder adoptedStyleSheets(@Nullable java.util.List<String> value) {
                if (value == null) values.remove("adoptedStyleSheets");
                else values.put("adoptedStyleSheets", jsonValue(value));
                return this;
            }
            public AdoptedStyleSheetsModifiedEvent build() {
                if (!values.containsKey("nodeId")) throw new IllegalStateException("Missing required CDP field: nodeId");
                if (!values.containsKey("adoptedStyleSheets")) throw new IllegalStateException("Missing required CDP field: adoptedStyleSheets");
                return new AdoptedStyleSheetsModifiedEvent(values);
            }
        }
    }
    /**
     * Fired when {@code Element}&#x27;s attribute is removed.
     */
    public static final class AttributeRemovedEvent extends CdpObject {
        private AttributeRemovedEvent(Map<String, Object> values) { super(values); }
        @Nullable public static AttributeRemovedEvent fromMap(@Nullable Map<String, Object> values) {
            return values == null ? null : new AttributeRemovedEvent(values);
        }
        public static Builder builder() { return new Builder(); }
        /**
         * Id of the node that has changed.
         * @return the protocol field value
         */
        @Nullable public Long nodeId() {
            return numberAsLong(value("nodeId"));
        }
        /**
         * A ttribute name.
         * @return the protocol field value
         */
        @Nullable public String name() {
            return (String) value("name");
        }
        public static final class Builder {
            private final Map<String, Object> values = new LinkedHashMap<>();
            /**
             * Id of the node that has changed.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder nodeId(@Nullable Long value) {
                if (value == null) values.remove("nodeId");
                else values.put("nodeId", jsonValue(value));
                return this;
            }
            /**
             * A ttribute name.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder name(@Nullable String value) {
                if (value == null) values.remove("name");
                else values.put("name", jsonValue(value));
                return this;
            }
            public AttributeRemovedEvent build() {
                if (!values.containsKey("nodeId")) throw new IllegalStateException("Missing required CDP field: nodeId");
                if (!values.containsKey("name")) throw new IllegalStateException("Missing required CDP field: name");
                return new AttributeRemovedEvent(values);
            }
        }
    }
    /**
     * Mirrors {@code DOMCharacterDataModified} event.
     */
    public static final class CharacterDataModifiedEvent extends CdpObject {
        private CharacterDataModifiedEvent(Map<String, Object> values) { super(values); }
        @Nullable public static CharacterDataModifiedEvent fromMap(@Nullable Map<String, Object> values) {
            return values == null ? null : new CharacterDataModifiedEvent(values);
        }
        public static Builder builder() { return new Builder(); }
        /**
         * Id of the node that has changed.
         * @return the protocol field value
         */
        @Nullable public Long nodeId() {
            return numberAsLong(value("nodeId"));
        }
        /**
         * New text value.
         * @return the protocol field value
         */
        @Nullable public String characterData() {
            return (String) value("characterData");
        }
        public static final class Builder {
            private final Map<String, Object> values = new LinkedHashMap<>();
            /**
             * Id of the node that has changed.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder nodeId(@Nullable Long value) {
                if (value == null) values.remove("nodeId");
                else values.put("nodeId", jsonValue(value));
                return this;
            }
            /**
             * New text value.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder characterData(@Nullable String value) {
                if (value == null) values.remove("characterData");
                else values.put("characterData", jsonValue(value));
                return this;
            }
            public CharacterDataModifiedEvent build() {
                if (!values.containsKey("nodeId")) throw new IllegalStateException("Missing required CDP field: nodeId");
                if (!values.containsKey("characterData")) throw new IllegalStateException("Missing required CDP field: characterData");
                return new CharacterDataModifiedEvent(values);
            }
        }
    }
    /**
     * Fired when {@code Container}&#x27;s child node count has changed.
     */
    public static final class ChildNodeCountUpdatedEvent extends CdpObject {
        private ChildNodeCountUpdatedEvent(Map<String, Object> values) { super(values); }
        @Nullable public static ChildNodeCountUpdatedEvent fromMap(@Nullable Map<String, Object> values) {
            return values == null ? null : new ChildNodeCountUpdatedEvent(values);
        }
        public static Builder builder() { return new Builder(); }
        /**
         * Id of the node that has changed.
         * @return the protocol field value
         */
        @Nullable public Long nodeId() {
            return numberAsLong(value("nodeId"));
        }
        /**
         * New node count.
         * @return the protocol field value
         */
        @Nullable public Long childNodeCount() {
            return numberAsLong(value("childNodeCount"));
        }
        public static final class Builder {
            private final Map<String, Object> values = new LinkedHashMap<>();
            /**
             * Id of the node that has changed.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder nodeId(@Nullable Long value) {
                if (value == null) values.remove("nodeId");
                else values.put("nodeId", jsonValue(value));
                return this;
            }
            /**
             * New node count.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder childNodeCount(@Nullable Long value) {
                if (value == null) values.remove("childNodeCount");
                else values.put("childNodeCount", jsonValue(value));
                return this;
            }
            public ChildNodeCountUpdatedEvent build() {
                if (!values.containsKey("nodeId")) throw new IllegalStateException("Missing required CDP field: nodeId");
                if (!values.containsKey("childNodeCount")) throw new IllegalStateException("Missing required CDP field: childNodeCount");
                return new ChildNodeCountUpdatedEvent(values);
            }
        }
    }
    /**
     * Mirrors {@code DOMNodeInserted} event.
     */
    public static final class ChildNodeInsertedEvent extends CdpObject {
        private ChildNodeInsertedEvent(Map<String, Object> values) { super(values); }
        @Nullable public static ChildNodeInsertedEvent fromMap(@Nullable Map<String, Object> values) {
            return values == null ? null : new ChildNodeInsertedEvent(values);
        }
        public static Builder builder() { return new Builder(); }
        /**
         * Id of the node that has changed.
         * @return the protocol field value
         */
        @Nullable public Long parentNodeId() {
            return numberAsLong(value("parentNodeId"));
        }
        /**
         * Id of the previous sibling.
         * @return the protocol field value
         */
        @Nullable public Long previousNodeId() {
            return numberAsLong(value("previousNodeId"));
        }
        /**
         * Inserted node data.
         * @return the protocol field value
         */
        @Nullable public DOM.Node node() {
            return DOM.Node.fromMap(objectMap(value("node")));
        }
        public static final class Builder {
            private final Map<String, Object> values = new LinkedHashMap<>();
            /**
             * Id of the node that has changed.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder parentNodeId(@Nullable Long value) {
                if (value == null) values.remove("parentNodeId");
                else values.put("parentNodeId", jsonValue(value));
                return this;
            }
            /**
             * Id of the previous sibling.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder previousNodeId(@Nullable Long value) {
                if (value == null) values.remove("previousNodeId");
                else values.put("previousNodeId", jsonValue(value));
                return this;
            }
            /**
             * Inserted node data.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder node(@Nullable DOM.Node value) {
                if (value == null) values.remove("node");
                else values.put("node", jsonValue(value));
                return this;
            }
            public ChildNodeInsertedEvent build() {
                if (!values.containsKey("parentNodeId")) throw new IllegalStateException("Missing required CDP field: parentNodeId");
                if (!values.containsKey("previousNodeId")) throw new IllegalStateException("Missing required CDP field: previousNodeId");
                if (!values.containsKey("node")) throw new IllegalStateException("Missing required CDP field: node");
                return new ChildNodeInsertedEvent(values);
            }
        }
    }
    /**
     * Mirrors {@code DOMNodeRemoved} event.
     */
    public static final class ChildNodeRemovedEvent extends CdpObject {
        private ChildNodeRemovedEvent(Map<String, Object> values) { super(values); }
        @Nullable public static ChildNodeRemovedEvent fromMap(@Nullable Map<String, Object> values) {
            return values == null ? null : new ChildNodeRemovedEvent(values);
        }
        public static Builder builder() { return new Builder(); }
        /**
         * Parent id.
         * @return the protocol field value
         */
        @Nullable public Long parentNodeId() {
            return numberAsLong(value("parentNodeId"));
        }
        /**
         * Id of the node that has been removed.
         * @return the protocol field value
         */
        @Nullable public Long nodeId() {
            return numberAsLong(value("nodeId"));
        }
        public static final class Builder {
            private final Map<String, Object> values = new LinkedHashMap<>();
            /**
             * Parent id.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder parentNodeId(@Nullable Long value) {
                if (value == null) values.remove("parentNodeId");
                else values.put("parentNodeId", jsonValue(value));
                return this;
            }
            /**
             * Id of the node that has been removed.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder nodeId(@Nullable Long value) {
                if (value == null) values.remove("nodeId");
                else values.put("nodeId", jsonValue(value));
                return this;
            }
            public ChildNodeRemovedEvent build() {
                if (!values.containsKey("parentNodeId")) throw new IllegalStateException("Missing required CDP field: parentNodeId");
                if (!values.containsKey("nodeId")) throw new IllegalStateException("Missing required CDP field: nodeId");
                return new ChildNodeRemovedEvent(values);
            }
        }
    }
    /**
     * Called when distribution is changed.
     * <p><b>Experimental:</b> this part of CDP may change without notice.
     */
    public static final class DistributedNodesUpdatedEvent extends CdpObject {
        private DistributedNodesUpdatedEvent(Map<String, Object> values) { super(values); }
        @Nullable public static DistributedNodesUpdatedEvent fromMap(@Nullable Map<String, Object> values) {
            return values == null ? null : new DistributedNodesUpdatedEvent(values);
        }
        public static Builder builder() { return new Builder(); }
        /**
         * Insertion point where distributed nodes were updated.
         * @return the protocol field value
         */
        @Nullable public Long insertionPointId() {
            return numberAsLong(value("insertionPointId"));
        }
        /**
         * Distributed nodes for given insertion point.
         * @return the protocol field value
         */
        @Nullable public java.util.List<DOM.BackendNode> distributedNodes() {
            return list(value("distributedNodes"), element0 -> DOM.BackendNode.fromMap(objectMap(element0)));
        }
        public static final class Builder {
            private final Map<String, Object> values = new LinkedHashMap<>();
            /**
             * Insertion point where distributed nodes were updated.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder insertionPointId(@Nullable Long value) {
                if (value == null) values.remove("insertionPointId");
                else values.put("insertionPointId", jsonValue(value));
                return this;
            }
            /**
             * Distributed nodes for given insertion point.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder distributedNodes(@Nullable java.util.List<DOM.BackendNode> value) {
                if (value == null) values.remove("distributedNodes");
                else values.put("distributedNodes", jsonValue(value));
                return this;
            }
            public DistributedNodesUpdatedEvent build() {
                if (!values.containsKey("insertionPointId")) throw new IllegalStateException("Missing required CDP field: insertionPointId");
                if (!values.containsKey("distributedNodes")) throw new IllegalStateException("Missing required CDP field: distributedNodes");
                return new DistributedNodesUpdatedEvent(values);
            }
        }
    }
    /**
     * Fired when {@code Document} has been totally updated. Node ids are no longer valid.
     */
    public static final class DocumentUpdatedEvent extends CdpObject {
        private DocumentUpdatedEvent(Map<String, Object> values) { super(values); }
        @Nullable public static DocumentUpdatedEvent fromMap(@Nullable Map<String, Object> values) {
            return values == null ? null : new DocumentUpdatedEvent(values);
        }
        public static Builder builder() { return new Builder(); }
        public static final class Builder {
            private final Map<String, Object> values = new LinkedHashMap<>();
            public DocumentUpdatedEvent build() {
                return new DocumentUpdatedEvent(values);
            }
        }
    }
    /**
     * Fired when {@code Element}&#x27;s inline style is modified via a CSS property modification.
     * <p><b>Experimental:</b> this part of CDP may change without notice.
     */
    public static final class InlineStyleInvalidatedEvent extends CdpObject {
        private InlineStyleInvalidatedEvent(Map<String, Object> values) { super(values); }
        @Nullable public static InlineStyleInvalidatedEvent fromMap(@Nullable Map<String, Object> values) {
            return values == null ? null : new InlineStyleInvalidatedEvent(values);
        }
        public static Builder builder() { return new Builder(); }
        /**
         * Ids of the nodes for which the inline styles have been invalidated.
         * @return the protocol field value
         */
        @Nullable public java.util.List<Long> nodeIds() {
            return list(value("nodeIds"), element0 -> numberAsLong(element0));
        }
        public static final class Builder {
            private final Map<String, Object> values = new LinkedHashMap<>();
            /**
             * Ids of the nodes for which the inline styles have been invalidated.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder nodeIds(@Nullable java.util.List<Long> value) {
                if (value == null) values.remove("nodeIds");
                else values.put("nodeIds", jsonValue(value));
                return this;
            }
            public InlineStyleInvalidatedEvent build() {
                if (!values.containsKey("nodeIds")) throw new IllegalStateException("Missing required CDP field: nodeIds");
                return new InlineStyleInvalidatedEvent(values);
            }
        }
    }
    /**
     * Called when a pseudo element is added to an element.
     * <p><b>Experimental:</b> this part of CDP may change without notice.
     */
    public static final class PseudoElementAddedEvent extends CdpObject {
        private PseudoElementAddedEvent(Map<String, Object> values) { super(values); }
        @Nullable public static PseudoElementAddedEvent fromMap(@Nullable Map<String, Object> values) {
            return values == null ? null : new PseudoElementAddedEvent(values);
        }
        public static Builder builder() { return new Builder(); }
        /**
         * Pseudo element&#x27;s parent element id.
         * @return the protocol field value
         */
        @Nullable public Long parentId() {
            return numberAsLong(value("parentId"));
        }
        /**
         * The added pseudo element.
         * @return the protocol field value
         */
        @Nullable public DOM.Node pseudoElement() {
            return DOM.Node.fromMap(objectMap(value("pseudoElement")));
        }
        public static final class Builder {
            private final Map<String, Object> values = new LinkedHashMap<>();
            /**
             * Pseudo element&#x27;s parent element id.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder parentId(@Nullable Long value) {
                if (value == null) values.remove("parentId");
                else values.put("parentId", jsonValue(value));
                return this;
            }
            /**
             * The added pseudo element.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder pseudoElement(@Nullable DOM.Node value) {
                if (value == null) values.remove("pseudoElement");
                else values.put("pseudoElement", jsonValue(value));
                return this;
            }
            public PseudoElementAddedEvent build() {
                if (!values.containsKey("parentId")) throw new IllegalStateException("Missing required CDP field: parentId");
                if (!values.containsKey("pseudoElement")) throw new IllegalStateException("Missing required CDP field: pseudoElement");
                return new PseudoElementAddedEvent(values);
            }
        }
    }
    /**
     * Called when top layer elements are changed.
     * <p><b>Experimental:</b> this part of CDP may change without notice.
     */
    public static final class TopLayerElementsUpdatedEvent extends CdpObject {
        private TopLayerElementsUpdatedEvent(Map<String, Object> values) { super(values); }
        @Nullable public static TopLayerElementsUpdatedEvent fromMap(@Nullable Map<String, Object> values) {
            return values == null ? null : new TopLayerElementsUpdatedEvent(values);
        }
        public static Builder builder() { return new Builder(); }
        public static final class Builder {
            private final Map<String, Object> values = new LinkedHashMap<>();
            public TopLayerElementsUpdatedEvent build() {
                return new TopLayerElementsUpdatedEvent(values);
            }
        }
    }
    /**
     * Fired when a node&#x27;s scrollability state changes.
     * <p><b>Experimental:</b> this part of CDP may change without notice.
     */
    public static final class ScrollableFlagUpdatedEvent extends CdpObject {
        private ScrollableFlagUpdatedEvent(Map<String, Object> values) { super(values); }
        @Nullable public static ScrollableFlagUpdatedEvent fromMap(@Nullable Map<String, Object> values) {
            return values == null ? null : new ScrollableFlagUpdatedEvent(values);
        }
        public static Builder builder() { return new Builder(); }
        /**
         * The id of the node.
         * @return the protocol field value
         */
        @Nullable public Long nodeId() {
            return numberAsLong(value("nodeId"));
        }
        /**
         * If the node is scrollable.
         * @return the protocol field value
         */
        @Nullable public Boolean isScrollable() {
            return (Boolean) value("isScrollable");
        }
        public static final class Builder {
            private final Map<String, Object> values = new LinkedHashMap<>();
            /**
             * The id of the node.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder nodeId(@Nullable Long value) {
                if (value == null) values.remove("nodeId");
                else values.put("nodeId", jsonValue(value));
                return this;
            }
            /**
             * If the node is scrollable.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder isScrollable(@Nullable Boolean value) {
                if (value == null) values.remove("isScrollable");
                else values.put("isScrollable", jsonValue(value));
                return this;
            }
            public ScrollableFlagUpdatedEvent build() {
                if (!values.containsKey("nodeId")) throw new IllegalStateException("Missing required CDP field: nodeId");
                if (!values.containsKey("isScrollable")) throw new IllegalStateException("Missing required CDP field: isScrollable");
                return new ScrollableFlagUpdatedEvent(values);
            }
        }
    }
    /**
     * Fired when a node&#x27;s starting styles changes.
     * <p><b>Experimental:</b> this part of CDP may change without notice.
     */
    public static final class AffectedByStartingStylesFlagUpdatedEvent extends CdpObject {
        private AffectedByStartingStylesFlagUpdatedEvent(Map<String, Object> values) { super(values); }
        @Nullable public static AffectedByStartingStylesFlagUpdatedEvent fromMap(@Nullable Map<String, Object> values) {
            return values == null ? null : new AffectedByStartingStylesFlagUpdatedEvent(values);
        }
        public static Builder builder() { return new Builder(); }
        /**
         * The id of the node.
         * @return the protocol field value
         */
        @Nullable public Long nodeId() {
            return numberAsLong(value("nodeId"));
        }
        /**
         * If the node has starting styles.
         * @return the protocol field value
         */
        @Nullable public Boolean affectedByStartingStyles() {
            return (Boolean) value("affectedByStartingStyles");
        }
        public static final class Builder {
            private final Map<String, Object> values = new LinkedHashMap<>();
            /**
             * The id of the node.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder nodeId(@Nullable Long value) {
                if (value == null) values.remove("nodeId");
                else values.put("nodeId", jsonValue(value));
                return this;
            }
            /**
             * If the node has starting styles.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder affectedByStartingStyles(@Nullable Boolean value) {
                if (value == null) values.remove("affectedByStartingStyles");
                else values.put("affectedByStartingStyles", jsonValue(value));
                return this;
            }
            public AffectedByStartingStylesFlagUpdatedEvent build() {
                if (!values.containsKey("nodeId")) throw new IllegalStateException("Missing required CDP field: nodeId");
                if (!values.containsKey("affectedByStartingStyles")) throw new IllegalStateException("Missing required CDP field: affectedByStartingStyles");
                return new AffectedByStartingStylesFlagUpdatedEvent(values);
            }
        }
    }
    /**
     * Called when a pseudo element is removed from an element.
     * <p><b>Experimental:</b> this part of CDP may change without notice.
     */
    public static final class PseudoElementRemovedEvent extends CdpObject {
        private PseudoElementRemovedEvent(Map<String, Object> values) { super(values); }
        @Nullable public static PseudoElementRemovedEvent fromMap(@Nullable Map<String, Object> values) {
            return values == null ? null : new PseudoElementRemovedEvent(values);
        }
        public static Builder builder() { return new Builder(); }
        /**
         * Pseudo element&#x27;s parent element id.
         * @return the protocol field value
         */
        @Nullable public Long parentId() {
            return numberAsLong(value("parentId"));
        }
        /**
         * The removed pseudo element id.
         * @return the protocol field value
         */
        @Nullable public Long pseudoElementId() {
            return numberAsLong(value("pseudoElementId"));
        }
        public static final class Builder {
            private final Map<String, Object> values = new LinkedHashMap<>();
            /**
             * Pseudo element&#x27;s parent element id.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder parentId(@Nullable Long value) {
                if (value == null) values.remove("parentId");
                else values.put("parentId", jsonValue(value));
                return this;
            }
            /**
             * The removed pseudo element id.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder pseudoElementId(@Nullable Long value) {
                if (value == null) values.remove("pseudoElementId");
                else values.put("pseudoElementId", jsonValue(value));
                return this;
            }
            public PseudoElementRemovedEvent build() {
                if (!values.containsKey("parentId")) throw new IllegalStateException("Missing required CDP field: parentId");
                if (!values.containsKey("pseudoElementId")) throw new IllegalStateException("Missing required CDP field: pseudoElementId");
                return new PseudoElementRemovedEvent(values);
            }
        }
    }
    /**
     * Fired when backend wants to provide client with the missing DOM structure. This happens upon most of the calls requesting node ids.
     */
    public static final class SetChildNodesEvent extends CdpObject {
        private SetChildNodesEvent(Map<String, Object> values) { super(values); }
        @Nullable public static SetChildNodesEvent fromMap(@Nullable Map<String, Object> values) {
            return values == null ? null : new SetChildNodesEvent(values);
        }
        public static Builder builder() { return new Builder(); }
        /**
         * Parent node id to populate with children.
         * @return the protocol field value
         */
        @Nullable public Long parentId() {
            return numberAsLong(value("parentId"));
        }
        /**
         * Child nodes array.
         * @return the protocol field value
         */
        @Nullable public java.util.List<DOM.Node> nodes() {
            return list(value("nodes"), element0 -> DOM.Node.fromMap(objectMap(element0)));
        }
        public static final class Builder {
            private final Map<String, Object> values = new LinkedHashMap<>();
            /**
             * Parent node id to populate with children.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder parentId(@Nullable Long value) {
                if (value == null) values.remove("parentId");
                else values.put("parentId", jsonValue(value));
                return this;
            }
            /**
             * Child nodes array.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder nodes(@Nullable java.util.List<DOM.Node> value) {
                if (value == null) values.remove("nodes");
                else values.put("nodes", jsonValue(value));
                return this;
            }
            public SetChildNodesEvent build() {
                if (!values.containsKey("parentId")) throw new IllegalStateException("Missing required CDP field: parentId");
                if (!values.containsKey("nodes")) throw new IllegalStateException("Missing required CDP field: nodes");
                return new SetChildNodesEvent(values);
            }
        }
    }
    /**
     * Called when shadow root is popped from the element.
     * <p><b>Experimental:</b> this part of CDP may change without notice.
     */
    public static final class ShadowRootPoppedEvent extends CdpObject {
        private ShadowRootPoppedEvent(Map<String, Object> values) { super(values); }
        @Nullable public static ShadowRootPoppedEvent fromMap(@Nullable Map<String, Object> values) {
            return values == null ? null : new ShadowRootPoppedEvent(values);
        }
        public static Builder builder() { return new Builder(); }
        /**
         * Host element id.
         * @return the protocol field value
         */
        @Nullable public Long hostId() {
            return numberAsLong(value("hostId"));
        }
        /**
         * Shadow root id.
         * @return the protocol field value
         */
        @Nullable public Long rootId() {
            return numberAsLong(value("rootId"));
        }
        public static final class Builder {
            private final Map<String, Object> values = new LinkedHashMap<>();
            /**
             * Host element id.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder hostId(@Nullable Long value) {
                if (value == null) values.remove("hostId");
                else values.put("hostId", jsonValue(value));
                return this;
            }
            /**
             * Shadow root id.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder rootId(@Nullable Long value) {
                if (value == null) values.remove("rootId");
                else values.put("rootId", jsonValue(value));
                return this;
            }
            public ShadowRootPoppedEvent build() {
                if (!values.containsKey("hostId")) throw new IllegalStateException("Missing required CDP field: hostId");
                if (!values.containsKey("rootId")) throw new IllegalStateException("Missing required CDP field: rootId");
                return new ShadowRootPoppedEvent(values);
            }
        }
    }
    /**
     * Called when shadow root is pushed into the element.
     * <p><b>Experimental:</b> this part of CDP may change without notice.
     */
    public static final class ShadowRootPushedEvent extends CdpObject {
        private ShadowRootPushedEvent(Map<String, Object> values) { super(values); }
        @Nullable public static ShadowRootPushedEvent fromMap(@Nullable Map<String, Object> values) {
            return values == null ? null : new ShadowRootPushedEvent(values);
        }
        public static Builder builder() { return new Builder(); }
        /**
         * Host element id.
         * @return the protocol field value
         */
        @Nullable public Long hostId() {
            return numberAsLong(value("hostId"));
        }
        /**
         * Shadow root.
         * @return the protocol field value
         */
        @Nullable public DOM.Node root() {
            return DOM.Node.fromMap(objectMap(value("root")));
        }
        public static final class Builder {
            private final Map<String, Object> values = new LinkedHashMap<>();
            /**
             * Host element id.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder hostId(@Nullable Long value) {
                if (value == null) values.remove("hostId");
                else values.put("hostId", jsonValue(value));
                return this;
            }
            /**
             * Shadow root.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder root(@Nullable DOM.Node value) {
                if (value == null) values.remove("root");
                else values.put("root", jsonValue(value));
                return this;
            }
            public ShadowRootPushedEvent build() {
                if (!values.containsKey("hostId")) throw new IllegalStateException("Missing required CDP field: hostId");
                if (!values.containsKey("root")) throw new IllegalStateException("Missing required CDP field: root");
                return new ShadowRootPushedEvent(values);
            }
        }
    }
    public static final class Client {
        private final CdpClient client;
        public Client(CdpClient client) { this.client = client; }
        /**
         * Collects class names for the node with given id and all of it&#x27;s child nodes.
         * <p><b>Experimental:</b> this part of CDP may change without notice.
         * @param params command parameters
         * @return a stage completing with the command result
         */
        public CompletionStage<CollectClassNamesFromSubtreeResult> collectClassNamesFromSubtree(CollectClassNamesFromSubtreeParams params) {
            return client.call("DOM.collectClassNamesFromSubtree", params, CollectClassNamesFromSubtreeResult::fromMap);
        }
        /**
         * Creates a deep copy of the specified node and places it into the target container before the given anchor.
         * <p><b>Experimental:</b> this part of CDP may change without notice.
         * @param params command parameters
         * @return a stage completing with the command result
         */
        public CompletionStage<CopyToResult> copyTo(CopyToParams params) {
            return client.call("DOM.copyTo", params, CopyToResult::fromMap);
        }
        /**
         * Describes node given its id, does not require domain to be enabled. Does not start tracking any objects, can be used for automation.
         * @param params command parameters
         * @return a stage completing with the command result
         */
        public CompletionStage<DescribeNodeResult> describeNode(DescribeNodeParams params) {
            return client.call("DOM.describeNode", params, DescribeNodeResult::fromMap);
        }
        /**
         * Scrolls the specified rect of the given node into view if not already visible. Note: exactly one between nodeId, backendNodeId and objectId should be passed to identify the node.
         * @param params command parameters
         * @return a stage completing with the command result
         */
        public CompletionStage<ScrollIntoViewIfNeededResult> scrollIntoViewIfNeeded(ScrollIntoViewIfNeededParams params) {
            return client.call("DOM.scrollIntoViewIfNeeded", params, ScrollIntoViewIfNeededResult::fromMap);
        }
        /**
         * Disables DOM agent for the given page.
         * @return a stage completing with the command result
         */
        public CompletionStage<DisableResult> disable() {
            return client.call("DOM.disable", null, DisableResult::fromMap);
        }
        /**
         * Discards search results from the session with the given id. {@code getSearchResults} should no longer be called for that search.
         * <p><b>Experimental:</b> this part of CDP may change without notice.
         * @param params command parameters
         * @return a stage completing with the command result
         */
        public CompletionStage<DiscardSearchResultsResult> discardSearchResults(DiscardSearchResultsParams params) {
            return client.call("DOM.discardSearchResults", params, DiscardSearchResultsResult::fromMap);
        }
        /**
         * Enables DOM agent for the given page.
         * @param params command parameters
         * @return a stage completing with the command result
         */
        public CompletionStage<EnableResult> enable(EnableParams params) {
            return client.call("DOM.enable", params, EnableResult::fromMap);
        }
        /**
         * Focuses the given element.
         * @param params command parameters
         * @return a stage completing with the command result
         */
        public CompletionStage<FocusResult> focus(FocusParams params) {
            return client.call("DOM.focus", params, FocusResult::fromMap);
        }
        /**
         * Returns attributes for the specified node.
         * @param params command parameters
         * @return a stage completing with the command result
         */
        public CompletionStage<GetAttributesResult> getAttributes(GetAttributesParams params) {
            return client.call("DOM.getAttributes", params, GetAttributesResult::fromMap);
        }
        /**
         * Returns boxes for the given node.
         * @param params command parameters
         * @return a stage completing with the command result
         */
        public CompletionStage<GetBoxModelResult> getBoxModel(GetBoxModelParams params) {
            return client.call("DOM.getBoxModel", params, GetBoxModelResult::fromMap);
        }
        /**
         * Returns quads that describe node position on the page. This method might return multiple quads for inline nodes.
         * <p><b>Experimental:</b> this part of CDP may change without notice.
         * @param params command parameters
         * @return a stage completing with the command result
         */
        public CompletionStage<GetContentQuadsResult> getContentQuads(GetContentQuadsParams params) {
            return client.call("DOM.getContentQuads", params, GetContentQuadsResult::fromMap);
        }
        /**
         * Returns the root DOM node (and optionally the subtree) to the caller. Implicitly enables the DOM domain events for the current target.
         * @param params command parameters
         * @return a stage completing with the command result
         */
        public CompletionStage<GetDocumentResult> getDocument(GetDocumentParams params) {
            return client.call("DOM.getDocument", params, GetDocumentResult::fromMap);
        }
        /**
         * Returns the root DOM node (and optionally the subtree) to the caller. Deprecated, as it is not designed to work well with the rest of the DOM agent. Use DOMSnapshot.captureSnapshot instead.
         * @param params command parameters
         * @return a stage completing with the command result
         * @deprecated Deprecated by the Chromium DevTools Protocol.
         */
        @Deprecated
        public CompletionStage<GetFlattenedDocumentResult> getFlattenedDocument(GetFlattenedDocumentParams params) {
            return client.call("DOM.getFlattenedDocument", params, GetFlattenedDocumentResult::fromMap);
        }
        /**
         * Finds nodes with a given computed style in a subtree.
         * <p><b>Experimental:</b> this part of CDP may change without notice.
         * @param params command parameters
         * @return a stage completing with the command result
         */
        public CompletionStage<GetNodesForSubtreeByStyleResult> getNodesForSubtreeByStyle(GetNodesForSubtreeByStyleParams params) {
            return client.call("DOM.getNodesForSubtreeByStyle", params, GetNodesForSubtreeByStyleResult::fromMap);
        }
        /**
         * Returns node id at given location. Depending on whether DOM domain is enabled, nodeId is either returned or not.
         * @param params command parameters
         * @return a stage completing with the command result
         */
        public CompletionStage<GetNodeForLocationResult> getNodeForLocation(GetNodeForLocationParams params) {
            return client.call("DOM.getNodeForLocation", params, GetNodeForLocationResult::fromMap);
        }
        /**
         * Returns node&#x27;s HTML markup.
         * @param params command parameters
         * @return a stage completing with the command result
         */
        public CompletionStage<GetOuterHTMLResult> getOuterHTML(GetOuterHTMLParams params) {
            return client.call("DOM.getOuterHTML", params, GetOuterHTMLResult::fromMap);
        }
        /**
         * Returns the id of the nearest ancestor that is a relayout boundary.
         * <p><b>Experimental:</b> this part of CDP may change without notice.
         * @param params command parameters
         * @return a stage completing with the command result
         */
        public CompletionStage<GetRelayoutBoundaryResult> getRelayoutBoundary(GetRelayoutBoundaryParams params) {
            return client.call("DOM.getRelayoutBoundary", params, GetRelayoutBoundaryResult::fromMap);
        }
        /**
         * Returns search results from given {@code fromIndex} to given {@code toIndex} from the search with the given identifier.
         * <p><b>Experimental:</b> this part of CDP may change without notice.
         * @param params command parameters
         * @return a stage completing with the command result
         */
        public CompletionStage<GetSearchResultsResult> getSearchResults(GetSearchResultsParams params) {
            return client.call("DOM.getSearchResults", params, GetSearchResultsResult::fromMap);
        }
        /**
         * Hides any highlight.
         * @return a stage completing with the command result
         */
        public CompletionStage<HideHighlightResult> hideHighlight() {
            return client.call("DOM.hideHighlight", null, HideHighlightResult::fromMap);
        }
        /**
         * Highlights DOM node.
         * @return a stage completing with the command result
         */
        public CompletionStage<HighlightNodeResult> highlightNode() {
            return client.call("DOM.highlightNode", null, HighlightNodeResult::fromMap);
        }
        /**
         * Highlights given rectangle.
         * @return a stage completing with the command result
         */
        public CompletionStage<HighlightRectResult> highlightRect() {
            return client.call("DOM.highlightRect", null, HighlightRectResult::fromMap);
        }
        /**
         * Marks last undoable state.
         * <p><b>Experimental:</b> this part of CDP may change without notice.
         * @return a stage completing with the command result
         */
        public CompletionStage<MarkUndoableStateResult> markUndoableState() {
            return client.call("DOM.markUndoableState", null, MarkUndoableStateResult::fromMap);
        }
        /**
         * Moves node into the new container, places it before the given anchor.
         * @param params command parameters
         * @return a stage completing with the command result
         */
        public CompletionStage<MoveToResult> moveTo(MoveToParams params) {
            return client.call("DOM.moveTo", params, MoveToResult::fromMap);
        }
        /**
         * Searches for a given string in the DOM tree. Use {@code getSearchResults} to access search results or {@code cancelSearch} to end this search session.
         * <p><b>Experimental:</b> this part of CDP may change without notice.
         * @param params command parameters
         * @return a stage completing with the command result
         */
        public CompletionStage<PerformSearchResult> performSearch(PerformSearchParams params) {
            return client.call("DOM.performSearch", params, PerformSearchResult::fromMap);
        }
        /**
         * Requests that the node is sent to the caller given its path. // FIXME, use XPath
         * <p><b>Experimental:</b> this part of CDP may change without notice.
         * @param params command parameters
         * @return a stage completing with the command result
         */
        public CompletionStage<PushNodeByPathToFrontendResult> pushNodeByPathToFrontend(PushNodeByPathToFrontendParams params) {
            return client.call("DOM.pushNodeByPathToFrontend", params, PushNodeByPathToFrontendResult::fromMap);
        }
        /**
         * Requests that a batch of nodes is sent to the caller given their backend node ids.
         * <p><b>Experimental:</b> this part of CDP may change without notice.
         * @param params command parameters
         * @return a stage completing with the command result
         */
        public CompletionStage<PushNodesByBackendIdsToFrontendResult> pushNodesByBackendIdsToFrontend(PushNodesByBackendIdsToFrontendParams params) {
            return client.call("DOM.pushNodesByBackendIdsToFrontend", params, PushNodesByBackendIdsToFrontendResult::fromMap);
        }
        /**
         * Executes {@code querySelector} on a given node.
         * @param params command parameters
         * @return a stage completing with the command result
         */
        public CompletionStage<QuerySelectorResult> querySelector(QuerySelectorParams params) {
            return client.call("DOM.querySelector", params, QuerySelectorResult::fromMap);
        }
        /**
         * Executes {@code querySelectorAll} on a given node.
         * @param params command parameters
         * @return a stage completing with the command result
         */
        public CompletionStage<QuerySelectorAllResult> querySelectorAll(QuerySelectorAllParams params) {
            return client.call("DOM.querySelectorAll", params, QuerySelectorAllResult::fromMap);
        }
        /**
         * Returns NodeIds of current top layer elements. Top layer is rendered closest to the user within a viewport, therefore its elements always appear on top of all other content.
         * <p><b>Experimental:</b> this part of CDP may change without notice.
         * @return a stage completing with the command result
         */
        public CompletionStage<GetTopLayerElementsResult> getTopLayerElements() {
            return client.call("DOM.getTopLayerElements", null, GetTopLayerElementsResult::fromMap);
        }
        /**
         * Returns the NodeId of the matched element according to certain relations.
         * <p><b>Experimental:</b> this part of CDP may change without notice.
         * @param params command parameters
         * @return a stage completing with the command result
         */
        public CompletionStage<GetElementByRelationResult> getElementByRelation(GetElementByRelationParams params) {
            return client.call("DOM.getElementByRelation", params, GetElementByRelationResult::fromMap);
        }
        /**
         * Re-does the last undone action.
         * <p><b>Experimental:</b> this part of CDP may change without notice.
         * @return a stage completing with the command result
         */
        public CompletionStage<RedoResult> redo() {
            return client.call("DOM.redo", null, RedoResult::fromMap);
        }
        /**
         * Removes attribute with given name from an element with given id.
         * @param params command parameters
         * @return a stage completing with the command result
         */
        public CompletionStage<RemoveAttributeResult> removeAttribute(RemoveAttributeParams params) {
            return client.call("DOM.removeAttribute", params, RemoveAttributeResult::fromMap);
        }
        /**
         * Removes node with given id.
         * @param params command parameters
         * @return a stage completing with the command result
         */
        public CompletionStage<RemoveNodeResult> removeNode(RemoveNodeParams params) {
            return client.call("DOM.removeNode", params, RemoveNodeResult::fromMap);
        }
        /**
         * Requests that children of the node with given id are returned to the caller in form of {@code setChildNodes} events where not only immediate children are retrieved, but all children down to the specified depth.
         * @param params command parameters
         * @return a stage completing with the command result
         */
        public CompletionStage<RequestChildNodesResult> requestChildNodes(RequestChildNodesParams params) {
            return client.call("DOM.requestChildNodes", params, RequestChildNodesResult::fromMap);
        }
        /**
         * Requests that the node is sent to the caller given the JavaScript node object reference. All nodes that form the path from the node to the root are also sent to the client as a series of {@code setChildNodes} notifications.
         * @param params command parameters
         * @return a stage completing with the command result
         */
        public CompletionStage<RequestNodeResult> requestNode(RequestNodeParams params) {
            return client.call("DOM.requestNode", params, RequestNodeResult::fromMap);
        }
        /**
         * Resolves the JavaScript node object for a given NodeId or BackendNodeId.
         * @param params command parameters
         * @return a stage completing with the command result
         */
        public CompletionStage<ResolveNodeResult> resolveNode(ResolveNodeParams params) {
            return client.call("DOM.resolveNode", params, ResolveNodeResult::fromMap);
        }
        /**
         * Sets attribute for an element with given id.
         * @param params command parameters
         * @return a stage completing with the command result
         */
        public CompletionStage<SetAttributeValueResult> setAttributeValue(SetAttributeValueParams params) {
            return client.call("DOM.setAttributeValue", params, SetAttributeValueResult::fromMap);
        }
        /**
         * Sets attributes on element with given id. This method is useful when user edits some existing attribute value and types in several attribute name/value pairs.
         * @param params command parameters
         * @return a stage completing with the command result
         */
        public CompletionStage<SetAttributesAsTextResult> setAttributesAsText(SetAttributesAsTextParams params) {
            return client.call("DOM.setAttributesAsText", params, SetAttributesAsTextResult::fromMap);
        }
        /**
         * Sets files for the given file input element.
         * @param params command parameters
         * @return a stage completing with the command result
         */
        public CompletionStage<SetFileInputFilesResult> setFileInputFiles(SetFileInputFilesParams params) {
            return client.call("DOM.setFileInputFiles", params, SetFileInputFilesResult::fromMap);
        }
        /**
         * Sets if stack traces should be captured for Nodes. See {@code Node.getNodeStackTraces}. Default is disabled.
         * <p><b>Experimental:</b> this part of CDP may change without notice.
         * @param params command parameters
         * @return a stage completing with the command result
         */
        public CompletionStage<SetNodeStackTracesEnabledResult> setNodeStackTracesEnabled(SetNodeStackTracesEnabledParams params) {
            return client.call("DOM.setNodeStackTracesEnabled", params, SetNodeStackTracesEnabledResult::fromMap);
        }
        /**
         * Gets stack traces associated with a Node. As of now, only provides stack trace for Node creation.
         * <p><b>Experimental:</b> this part of CDP may change without notice.
         * @param params command parameters
         * @return a stage completing with the command result
         */
        public CompletionStage<GetNodeStackTracesResult> getNodeStackTraces(GetNodeStackTracesParams params) {
            return client.call("DOM.getNodeStackTraces", params, GetNodeStackTracesResult::fromMap);
        }
        /**
         * Returns file information for the given File wrapper.
         * <p><b>Experimental:</b> this part of CDP may change without notice.
         * @param params command parameters
         * @return a stage completing with the command result
         */
        public CompletionStage<GetFileInfoResult> getFileInfo(GetFileInfoParams params) {
            return client.call("DOM.getFileInfo", params, GetFileInfoResult::fromMap);
        }
        /**
         * Returns list of detached nodes
         * <p><b>Experimental:</b> this part of CDP may change without notice.
         * @return a stage completing with the command result
         */
        public CompletionStage<GetDetachedDomNodesResult> getDetachedDomNodes() {
            return client.call("DOM.getDetachedDomNodes", null, GetDetachedDomNodesResult::fromMap);
        }
        /**
         * Enables console to refer to the node with given id via $x (see Command Line API for more details $x functions).
         * <p><b>Experimental:</b> this part of CDP may change without notice.
         * @param params command parameters
         * @return a stage completing with the command result
         */
        public CompletionStage<SetInspectedNodeResult> setInspectedNode(SetInspectedNodeParams params) {
            return client.call("DOM.setInspectedNode", params, SetInspectedNodeResult::fromMap);
        }
        /**
         * Sets node name for a node with given id.
         * @param params command parameters
         * @return a stage completing with the command result
         */
        public CompletionStage<SetNodeNameResult> setNodeName(SetNodeNameParams params) {
            return client.call("DOM.setNodeName", params, SetNodeNameResult::fromMap);
        }
        /**
         * Sets node value for a node with given id.
         * @param params command parameters
         * @return a stage completing with the command result
         */
        public CompletionStage<SetNodeValueResult> setNodeValue(SetNodeValueParams params) {
            return client.call("DOM.setNodeValue", params, SetNodeValueResult::fromMap);
        }
        /**
         * Sets node HTML markup, returns new node id.
         * @param params command parameters
         * @return a stage completing with the command result
         */
        public CompletionStage<SetOuterHTMLResult> setOuterHTML(SetOuterHTMLParams params) {
            return client.call("DOM.setOuterHTML", params, SetOuterHTMLResult::fromMap);
        }
        /**
         * Undoes the last performed action.
         * <p><b>Experimental:</b> this part of CDP may change without notice.
         * @return a stage completing with the command result
         */
        public CompletionStage<UndoResult> undo() {
            return client.call("DOM.undo", null, UndoResult::fromMap);
        }
        /**
         * Returns iframe node that owns iframe with the given domain.
         * <p><b>Experimental:</b> this part of CDP may change without notice.
         * @param params command parameters
         * @return a stage completing with the command result
         */
        public CompletionStage<GetFrameOwnerResult> getFrameOwner(GetFrameOwnerParams params) {
            return client.call("DOM.getFrameOwner", params, GetFrameOwnerResult::fromMap);
        }
        /**
         * Returns the query container of the given node based on container query conditions: containerName, physical and logical axes, and whether it queries scroll-state or anchored elements. If no axes are provided and queriesScrollState is false, the style container is returned, which is the direct parent or the closest element with a matching container-name.
         * <p><b>Experimental:</b> this part of CDP may change without notice.
         * @param params command parameters
         * @return a stage completing with the command result
         */
        public CompletionStage<GetContainerForNodeResult> getContainerForNode(GetContainerForNodeParams params) {
            return client.call("DOM.getContainerForNode", params, GetContainerForNodeResult::fromMap);
        }
        /**
         * Returns the descendants of a container query container that have container queries against this container.
         * <p><b>Experimental:</b> this part of CDP may change without notice.
         * @param params command parameters
         * @return a stage completing with the command result
         */
        public CompletionStage<GetQueryingDescendantsForContainerResult> getQueryingDescendantsForContainer(GetQueryingDescendantsForContainerParams params) {
            return client.call("DOM.getQueryingDescendantsForContainer", params, GetQueryingDescendantsForContainerResult::fromMap);
        }
        /**
         * Returns the target anchor element of the given anchor query according to https://www.w3.org/TR/css-anchor-position-1/#target.
         * <p><b>Experimental:</b> this part of CDP may change without notice.
         * @param params command parameters
         * @return a stage completing with the command result
         */
        public CompletionStage<GetAnchorElementResult> getAnchorElement(GetAnchorElementParams params) {
            return client.call("DOM.getAnchorElement", params, GetAnchorElementResult::fromMap);
        }
        /**
         * When enabling, this API force-opens the popover identified by nodeId and keeps it open until disabled.
         * <p><b>Experimental:</b> this part of CDP may change without notice.
         * @param params command parameters
         * @return a stage completing with the command result
         */
        public CompletionStage<ForceShowPopoverResult> forceShowPopover(ForceShowPopoverParams params) {
            return client.call("DOM.forceShowPopover", params, ForceShowPopoverResult::fromMap);
        }
        /**
         * Fired when {@code Element}&#x27;s attribute is modified.
         * @param handler event callback
         * @return a removable subscription
         */
        public CdpSubscription onAttributeModified(Consumer<AttributeModifiedEvent> handler) {
            return client.on("DOM.attributeModified", AttributeModifiedEvent::fromMap, handler);
        }
        /**
         * Fired when {@code Element}&#x27;s adoptedStyleSheets are modified.
         * <p><b>Experimental:</b> this part of CDP may change without notice.
         * @param handler event callback
         * @return a removable subscription
         */
        public CdpSubscription onAdoptedStyleSheetsModified(Consumer<AdoptedStyleSheetsModifiedEvent> handler) {
            return client.on("DOM.adoptedStyleSheetsModified", AdoptedStyleSheetsModifiedEvent::fromMap, handler);
        }
        /**
         * Fired when {@code Element}&#x27;s attribute is removed.
         * @param handler event callback
         * @return a removable subscription
         */
        public CdpSubscription onAttributeRemoved(Consumer<AttributeRemovedEvent> handler) {
            return client.on("DOM.attributeRemoved", AttributeRemovedEvent::fromMap, handler);
        }
        /**
         * Mirrors {@code DOMCharacterDataModified} event.
         * @param handler event callback
         * @return a removable subscription
         */
        public CdpSubscription onCharacterDataModified(Consumer<CharacterDataModifiedEvent> handler) {
            return client.on("DOM.characterDataModified", CharacterDataModifiedEvent::fromMap, handler);
        }
        /**
         * Fired when {@code Container}&#x27;s child node count has changed.
         * @param handler event callback
         * @return a removable subscription
         */
        public CdpSubscription onChildNodeCountUpdated(Consumer<ChildNodeCountUpdatedEvent> handler) {
            return client.on("DOM.childNodeCountUpdated", ChildNodeCountUpdatedEvent::fromMap, handler);
        }
        /**
         * Mirrors {@code DOMNodeInserted} event.
         * @param handler event callback
         * @return a removable subscription
         */
        public CdpSubscription onChildNodeInserted(Consumer<ChildNodeInsertedEvent> handler) {
            return client.on("DOM.childNodeInserted", ChildNodeInsertedEvent::fromMap, handler);
        }
        /**
         * Mirrors {@code DOMNodeRemoved} event.
         * @param handler event callback
         * @return a removable subscription
         */
        public CdpSubscription onChildNodeRemoved(Consumer<ChildNodeRemovedEvent> handler) {
            return client.on("DOM.childNodeRemoved", ChildNodeRemovedEvent::fromMap, handler);
        }
        /**
         * Called when distribution is changed.
         * <p><b>Experimental:</b> this part of CDP may change without notice.
         * @param handler event callback
         * @return a removable subscription
         */
        public CdpSubscription onDistributedNodesUpdated(Consumer<DistributedNodesUpdatedEvent> handler) {
            return client.on("DOM.distributedNodesUpdated", DistributedNodesUpdatedEvent::fromMap, handler);
        }
        /**
         * Fired when {@code Document} has been totally updated. Node ids are no longer valid.
         * @param handler event callback
         * @return a removable subscription
         */
        public CdpSubscription onDocumentUpdated(Consumer<DocumentUpdatedEvent> handler) {
            return client.on("DOM.documentUpdated", DocumentUpdatedEvent::fromMap, handler);
        }
        /**
         * Fired when {@code Element}&#x27;s inline style is modified via a CSS property modification.
         * <p><b>Experimental:</b> this part of CDP may change without notice.
         * @param handler event callback
         * @return a removable subscription
         */
        public CdpSubscription onInlineStyleInvalidated(Consumer<InlineStyleInvalidatedEvent> handler) {
            return client.on("DOM.inlineStyleInvalidated", InlineStyleInvalidatedEvent::fromMap, handler);
        }
        /**
         * Called when a pseudo element is added to an element.
         * <p><b>Experimental:</b> this part of CDP may change without notice.
         * @param handler event callback
         * @return a removable subscription
         */
        public CdpSubscription onPseudoElementAdded(Consumer<PseudoElementAddedEvent> handler) {
            return client.on("DOM.pseudoElementAdded", PseudoElementAddedEvent::fromMap, handler);
        }
        /**
         * Called when top layer elements are changed.
         * <p><b>Experimental:</b> this part of CDP may change without notice.
         * @param handler event callback
         * @return a removable subscription
         */
        public CdpSubscription onTopLayerElementsUpdated(Consumer<TopLayerElementsUpdatedEvent> handler) {
            return client.on("DOM.topLayerElementsUpdated", TopLayerElementsUpdatedEvent::fromMap, handler);
        }
        /**
         * Fired when a node&#x27;s scrollability state changes.
         * <p><b>Experimental:</b> this part of CDP may change without notice.
         * @param handler event callback
         * @return a removable subscription
         */
        public CdpSubscription onScrollableFlagUpdated(Consumer<ScrollableFlagUpdatedEvent> handler) {
            return client.on("DOM.scrollableFlagUpdated", ScrollableFlagUpdatedEvent::fromMap, handler);
        }
        /**
         * Fired when a node&#x27;s starting styles changes.
         * <p><b>Experimental:</b> this part of CDP may change without notice.
         * @param handler event callback
         * @return a removable subscription
         */
        public CdpSubscription onAffectedByStartingStylesFlagUpdated(Consumer<AffectedByStartingStylesFlagUpdatedEvent> handler) {
            return client.on("DOM.affectedByStartingStylesFlagUpdated", AffectedByStartingStylesFlagUpdatedEvent::fromMap, handler);
        }
        /**
         * Called when a pseudo element is removed from an element.
         * <p><b>Experimental:</b> this part of CDP may change without notice.
         * @param handler event callback
         * @return a removable subscription
         */
        public CdpSubscription onPseudoElementRemoved(Consumer<PseudoElementRemovedEvent> handler) {
            return client.on("DOM.pseudoElementRemoved", PseudoElementRemovedEvent::fromMap, handler);
        }
        /**
         * Fired when backend wants to provide client with the missing DOM structure. This happens upon most of the calls requesting node ids.
         * @param handler event callback
         * @return a removable subscription
         */
        public CdpSubscription onSetChildNodes(Consumer<SetChildNodesEvent> handler) {
            return client.on("DOM.setChildNodes", SetChildNodesEvent::fromMap, handler);
        }
        /**
         * Called when shadow root is popped from the element.
         * <p><b>Experimental:</b> this part of CDP may change without notice.
         * @param handler event callback
         * @return a removable subscription
         */
        public CdpSubscription onShadowRootPopped(Consumer<ShadowRootPoppedEvent> handler) {
            return client.on("DOM.shadowRootPopped", ShadowRootPoppedEvent::fromMap, handler);
        }
        /**
         * Called when shadow root is pushed into the element.
         * <p><b>Experimental:</b> this part of CDP may change without notice.
         * @param handler event callback
         * @return a removable subscription
         */
        public CdpSubscription onShadowRootPushed(Consumer<ShadowRootPushedEvent> handler) {
            return client.on("DOM.shadowRootPushed", ShadowRootPushedEvent::fromMap, handler);
        }
    }
}
