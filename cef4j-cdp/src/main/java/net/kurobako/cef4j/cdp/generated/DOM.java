// GENERATED - do not edit. Regenerate via: ./mvnw generate-sources -pl cef4j-cdp -Dcef.version=150.0.18+gdb11278+chromium-150.0.7871.213
package net.kurobako.cef4j.cdp.generated;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Optional;
import java.util.OptionalDouble;
import java.util.OptionalLong;
import java.util.concurrent.CompletionStage;
import java.util.function.Consumer;
import javax.annotation.Nonnull;
import javax.annotation.processing.Generated;
import net.kurobako.cef4j.cdp.CdpClient;
import net.kurobako.cef4j.cdp.CdpObject;
import net.kurobako.cef4j.cdp.CdpSubscription;
import net.kurobako.cef4j.cdp.CdpValue;

/**
 * This domain exposes DOM read/write operations. Each DOM Node is represented with its mirror object that has an {@code id}. This {@code id} can be used to get additional information on the Node, resolve it into the JavaScript object wrapper, etc. It is important that client receives DOM events only for the nodes that are known to the client. Backend keeps track of the nodes that were sent to the client and never sends the same node twice. It is client&#x27;s responsibility to collect information about the nodes that were sent to the client. Note that {@code iframe} owner elements will return corresponding document elements as their child nodes.
 * @see <a href="https://chromium.googlesource.com/chromium/src/+/refs/tags/150.0.7871.213/third_party/blink/public/devtools_protocol/domains/DOM.pdl">Pinned protocol source</a>
 */
@Generated("./mvnw generate-sources -pl cef4j-cdp -Dcef.version=150.0.18+gdb11278+chromium-150.0.7871.213")
@SuppressWarnings({"EscapedEntity", "InvalidParam", "JavaLangClash", "MissingSummary", "Unchecked", "UnusedMethod"})
public final class DOM {
    private DOM() {}
    /**
     * Unique DOM node identifier.
     */
    public static final class NodeId implements CdpValue<Long> {
        public final long value;
        public NodeId(long value) { this.value = value; }
        @Nonnull public Long value() { return value; }
        @Override public boolean equals(Object other) {
            if (this == other) return true;
            if (!(other instanceof NodeId)) return false;
            return value == ((NodeId) other).value;
        }
        @Override public int hashCode() { return Long.hashCode(value); }
        @Override public String toString() { return "NodeId(" + value + ")"; }
    }
    /**
     * Unique DOM node identifier used to reference a node that may not have been pushed to the front-end.
     */
    public static final class BackendNodeId implements CdpValue<Long> {
        public final long value;
        public BackendNodeId(long value) { this.value = value; }
        @Nonnull public Long value() { return value; }
        @Override public boolean equals(Object other) {
            if (this == other) return true;
            if (!(other instanceof BackendNodeId)) return false;
            return value == ((BackendNodeId) other).value;
        }
        @Override public int hashCode() { return Long.hashCode(value); }
        @Override public String toString() { return "BackendNodeId(" + value + ")"; }
    }
    /**
     * Unique identifier for a CSS stylesheet.
     */
    public static final class StyleSheetId implements CdpValue<String> {
        public final String value;
        public StyleSheetId(@Nonnull String value) { this.value = java.util.Objects.requireNonNull(value); }
        @Nonnull public String value() { return value; }
        @Override public boolean equals(Object other) {
            if (this == other) return true;
            if (!(other instanceof StyleSheetId)) return false;
            return value.equals(((StyleSheetId) other).value);
        }
        @Override public int hashCode() { return value.hashCode(); }
        @Override public String toString() { return "StyleSheetId(" + value + ")"; }
    }
    /**
     * Backend node with a friendly name.
     */
    public static final class BackendNode extends CdpObject {
        public BackendNode() {}
        private BackendNode(Map<String, Object> values) { super(values); }
        public static BackendNode fromMap(Map<String, Object> values) {
            return new BackendNode(values);
        }
        /**
         * {@code Node}&#x27;s nodeType.
         * @return the protocol field value
         */
        public long nodeType() {
            return ((Number) require("nodeType")).longValue();
        }
        /**
         * {@code Node}&#x27;s nodeName.
         * @return the protocol field value
         */
        public String nodeName() {
            return (String) require("nodeName");
        }
        /**
         * Returns the backendNodeId field.
         * @return the protocol field value
         */
        public DOM.BackendNodeId backendNodeId() {
            return new DOM.BackendNodeId(((Number) require("backendNodeId")).longValue());
        }
        /**
         * {@code Node}&#x27;s nodeType.
         * @param nodeType field value
         * @return this model
         */
        public BackendNode nodeType(long nodeType) {
            set("nodeType", nodeType);
            return this;
        }
        /**
         * {@code Node}&#x27;s nodeName.
         * @param nodeName field value
         * @return this model
         */
        public BackendNode nodeName(String nodeName) {
            set("nodeName", nodeName);
            return this;
        }
        /**
         * Sets the backendNodeId field.
         * @param backendNodeId field value
         * @return this model
         */
        public BackendNode backendNodeId(DOM.BackendNodeId backendNodeId) {
            set("backendNodeId", backendNodeId);
            return this;
        }
    }
    /**
     * Pseudo element type.
     */
    public enum PseudoType implements CdpValue<String> {
        FIRST_LINE("first-line"),
        FIRST_LETTER("first-letter"),
        CHECKMARK("checkmark"),
        BEFORE("before"),
        AFTER("after"),
        EXPAND_ICON("expand-icon"),
        PICKER_ICON("picker-icon"),
        INTEREST_BUTTON("interest-button"),
        MARKER("marker"),
        BACKDROP("backdrop"),
        COLUMN("column"),
        SELECTION("selection"),
        SEARCH_TEXT("search-text"),
        TARGET_TEXT("target-text"),
        SPELLING_ERROR("spelling-error"),
        GRAMMAR_ERROR("grammar-error"),
        HIGHLIGHT("highlight"),
        FIRST_LINE_INHERITED("first-line-inherited"),
        SCROLL_MARKER("scroll-marker"),
        SCROLL_MARKER_GROUP("scroll-marker-group"),
        SCROLL_BUTTON("scroll-button"),
        SCROLLBAR("scrollbar"),
        SCROLLBAR_THUMB("scrollbar-thumb"),
        SCROLLBAR_BUTTON("scrollbar-button"),
        SCROLLBAR_TRACK("scrollbar-track"),
        SCROLLBAR_TRACK_PIECE("scrollbar-track-piece"),
        SCROLLBAR_CORNER("scrollbar-corner"),
        RESIZER("resizer"),
        INPUT_LIST_BUTTON("input-list-button"),
        VIEW_TRANSITION("view-transition"),
        VIEW_TRANSITION_GROUP("view-transition-group"),
        VIEW_TRANSITION_IMAGE_PAIR("view-transition-image-pair"),
        VIEW_TRANSITION_GROUP_CHILDREN("view-transition-group-children"),
        VIEW_TRANSITION_OLD("view-transition-old"),
        VIEW_TRANSITION_NEW("view-transition-new"),
        PLACEHOLDER("placeholder"),
        FILE_SELECTOR_BUTTON("file-selector-button"),
        DETAILS_CONTENT("details-content"),
        PICKER("picker"),
        PERMISSION_ICON("permission-icon"),
        OVERSCROLL_AREA_PARENT("overscroll-area-parent");
        public final String value;
        PseudoType(String value) { this.value = value; }
        @Nonnull public String value() { return value; }
        public static PseudoType of(@Nonnull String value) {
            for (PseudoType constant : values()) {
                if (constant.value.equals(value)) return constant;
            }
            throw new IllegalArgumentException("Unknown PseudoType value: " + value);
        }
    }
    /**
     * Shadow root type.
     */
    public enum ShadowRootType implements CdpValue<String> {
        USER_AGENT("user-agent"),
        OPEN("open"),
        CLOSED("closed");
        public final String value;
        ShadowRootType(String value) { this.value = value; }
        @Nonnull public String value() { return value; }
        public static ShadowRootType of(@Nonnull String value) {
            for (ShadowRootType constant : values()) {
                if (constant.value.equals(value)) return constant;
            }
            throw new IllegalArgumentException("Unknown ShadowRootType value: " + value);
        }
    }
    /**
     * Document compatibility mode.
     */
    public enum CompatibilityMode implements CdpValue<String> {
        QUIRKSMODE("QuirksMode"),
        LIMITEDQUIRKSMODE("LimitedQuirksMode"),
        NOQUIRKSMODE("NoQuirksMode");
        public final String value;
        CompatibilityMode(String value) { this.value = value; }
        @Nonnull public String value() { return value; }
        public static CompatibilityMode of(@Nonnull String value) {
            for (CompatibilityMode constant : values()) {
                if (constant.value.equals(value)) return constant;
            }
            throw new IllegalArgumentException("Unknown CompatibilityMode value: " + value);
        }
    }
    /**
     * ContainerSelector physical axes
     */
    public enum PhysicalAxes implements CdpValue<String> {
        HORIZONTAL("Horizontal"),
        VERTICAL("Vertical"),
        BOTH("Both");
        public final String value;
        PhysicalAxes(String value) { this.value = value; }
        @Nonnull public String value() { return value; }
        public static PhysicalAxes of(@Nonnull String value) {
            for (PhysicalAxes constant : values()) {
                if (constant.value.equals(value)) return constant;
            }
            throw new IllegalArgumentException("Unknown PhysicalAxes value: " + value);
        }
    }
    /**
     * ContainerSelector logical axes
     */
    public enum LogicalAxes implements CdpValue<String> {
        INLINE("Inline"),
        BLOCK("Block"),
        BOTH("Both");
        public final String value;
        LogicalAxes(String value) { this.value = value; }
        @Nonnull public String value() { return value; }
        public static LogicalAxes of(@Nonnull String value) {
            for (LogicalAxes constant : values()) {
                if (constant.value.equals(value)) return constant;
            }
            throw new IllegalArgumentException("Unknown LogicalAxes value: " + value);
        }
    }
    /**
     * Physical scroll orientation
     */
    public enum ScrollOrientation implements CdpValue<String> {
        HORIZONTAL("horizontal"),
        VERTICAL("vertical");
        public final String value;
        ScrollOrientation(String value) { this.value = value; }
        @Nonnull public String value() { return value; }
        public static ScrollOrientation of(@Nonnull String value) {
            for (ScrollOrientation constant : values()) {
                if (constant.value.equals(value)) return constant;
            }
            throw new IllegalArgumentException("Unknown ScrollOrientation value: " + value);
        }
    }
    /**
     * DOM interaction is implemented in terms of mirror objects that represent the actual DOM nodes. DOMNode is a base node mirror type.
     */
    public static final class Node extends CdpObject {
        public Node() {}
        private Node(Map<String, Object> values) { super(values); }
        public static Node fromMap(Map<String, Object> values) {
            return new Node(values);
        }
        /**
         * Node identifier that is passed into the rest of the DOM messages as the {@code nodeId}. Backend will only push node with given {@code id} once. It is aware of all requested nodes and will only fire DOM events for nodes known to the client.
         * @return the protocol field value
         */
        public DOM.NodeId nodeId() {
            return new DOM.NodeId(((Number) require("nodeId")).longValue());
        }
        /**
         * The id of the parent node if any.
         * @return the protocol field value, empty when absent
         */
        public Optional<DOM.NodeId> parentId() {
            return Optional.ofNullable(raw("parentId") == null ? null : new DOM.NodeId(((Number) raw("parentId")).longValue()));
        }
        /**
         * The BackendNodeId for this node.
         * @return the protocol field value
         */
        public DOM.BackendNodeId backendNodeId() {
            return new DOM.BackendNodeId(((Number) require("backendNodeId")).longValue());
        }
        /**
         * {@code Node}&#x27;s nodeType.
         * @return the protocol field value
         */
        public long nodeType() {
            return ((Number) require("nodeType")).longValue();
        }
        /**
         * {@code Node}&#x27;s nodeName.
         * @return the protocol field value
         */
        public String nodeName() {
            return (String) require("nodeName");
        }
        /**
         * {@code Node}&#x27;s localName.
         * @return the protocol field value
         */
        public String localName() {
            return (String) require("localName");
        }
        /**
         * {@code Node}&#x27;s nodeValue.
         * @return the protocol field value
         */
        public String nodeValue() {
            return (String) require("nodeValue");
        }
        /**
         * Child count for {@code Container} nodes.
         * @return the protocol field value, empty when absent
         */
        public OptionalLong childNodeCount() {
            Long value = CdpObject.numberAsLong(raw("childNodeCount"));
            return value == null ? OptionalLong.empty() : OptionalLong.of(value);
        }
        /**
         * Child nodes of this node when requested with children.
         * @return the protocol field value, empty when absent
         */
        public Optional<java.util.List<DOM.Node>> children() {
            return Optional.ofNullable(list(raw("children"), element0 -> java.util.Objects.requireNonNull(DOM.Node.fromMap(java.util.Objects.requireNonNull(CdpObject.objectMap(element0))))));
        }
        /**
         * Attributes of the {@code Element} node in the form of flat array {@code [name1, value1, name2, value2]}.
         * @return the protocol field value, empty when absent
         */
        public Optional<java.util.List<String>> attributes() {
            return Optional.ofNullable(list(raw("attributes"), element0 -> (String) element0));
        }
        /**
         * Document URL that {@code Document} or {@code FrameOwner} node points to.
         * @return the protocol field value, empty when absent
         */
        public Optional<String> documentURL() {
            return Optional.ofNullable((String) raw("documentURL"));
        }
        /**
         * Base URL that {@code Document} or {@code FrameOwner} node uses for URL completion.
         * @return the protocol field value, empty when absent
         */
        public Optional<String> baseURL() {
            return Optional.ofNullable((String) raw("baseURL"));
        }
        /**
         * {@code DocumentType}&#x27;s publicId.
         * @return the protocol field value, empty when absent
         */
        public Optional<String> publicId() {
            return Optional.ofNullable((String) raw("publicId"));
        }
        /**
         * {@code DocumentType}&#x27;s systemId.
         * @return the protocol field value, empty when absent
         */
        public Optional<String> systemId() {
            return Optional.ofNullable((String) raw("systemId"));
        }
        /**
         * {@code DocumentType}&#x27;s internalSubset.
         * @return the protocol field value, empty when absent
         */
        public Optional<String> internalSubset() {
            return Optional.ofNullable((String) raw("internalSubset"));
        }
        /**
         * {@code Document}&#x27;s XML version in case of XML documents.
         * @return the protocol field value, empty when absent
         */
        public Optional<String> xmlVersion() {
            return Optional.ofNullable((String) raw("xmlVersion"));
        }
        /**
         * {@code Attr}&#x27;s name.
         * @return the protocol field value, empty when absent
         */
        public Optional<String> name() {
            return Optional.ofNullable((String) raw("name"));
        }
        /**
         * {@code Attr}&#x27;s value.
         * @return the protocol field value, empty when absent
         */
        public Optional<String> value() {
            return Optional.ofNullable((String) raw("value"));
        }
        /**
         * Pseudo element type for this node.
         * @return the protocol field value, empty when absent
         */
        public Optional<DOM.PseudoType> pseudoType() {
            return Optional.ofNullable(raw("pseudoType") == null ? null : DOM.PseudoType.of((String) raw("pseudoType")));
        }
        /**
         * Pseudo element identifier for this node. Only present if there is a valid pseudoType.
         * @return the protocol field value, empty when absent
         */
        public Optional<String> pseudoIdentifier() {
            return Optional.ofNullable((String) raw("pseudoIdentifier"));
        }
        /**
         * Shadow root type.
         * @return the protocol field value, empty when absent
         */
        public Optional<DOM.ShadowRootType> shadowRootType() {
            return Optional.ofNullable(raw("shadowRootType") == null ? null : DOM.ShadowRootType.of((String) raw("shadowRootType")));
        }
        /**
         * Frame ID for frame owner elements.
         * @return the protocol field value, empty when absent
         */
        public Optional<Page.FrameId> frameId() {
            return Optional.ofNullable(raw("frameId") == null ? null : new Page.FrameId((String) raw("frameId")));
        }
        /**
         * Content document for frame owner elements.
         * @return the protocol field value, empty when absent
         */
        public Optional<DOM.Node> contentDocument() {
            return Optional.ofNullable(raw("contentDocument") == null ? null : DOM.Node.fromMap(java.util.Objects.requireNonNull(objectMap(raw("contentDocument")))));
        }
        /**
         * Shadow root list for given element host.
         * @return the protocol field value, empty when absent
         */
        public Optional<java.util.List<DOM.Node>> shadowRoots() {
            return Optional.ofNullable(list(raw("shadowRoots"), element0 -> java.util.Objects.requireNonNull(DOM.Node.fromMap(java.util.Objects.requireNonNull(CdpObject.objectMap(element0))))));
        }
        /**
         * Content document fragment for template elements.
         * @return the protocol field value, empty when absent
         */
        public Optional<DOM.Node> templateContent() {
            return Optional.ofNullable(raw("templateContent") == null ? null : DOM.Node.fromMap(java.util.Objects.requireNonNull(objectMap(raw("templateContent")))));
        }
        /**
         * Pseudo elements associated with this node.
         * @return the protocol field value, empty when absent
         */
        public Optional<java.util.List<DOM.Node>> pseudoElements() {
            return Optional.ofNullable(list(raw("pseudoElements"), element0 -> java.util.Objects.requireNonNull(DOM.Node.fromMap(java.util.Objects.requireNonNull(CdpObject.objectMap(element0))))));
        }
        /**
         * Deprecated, as the HTML Imports API has been removed (crbug.com/937746). This property used to return the imported document for the HTMLImport links. The property is always undefined now.
         * @return the protocol field value, empty when absent
         * @deprecated Deprecated by the Chromium DevTools Protocol.
         */
        @Deprecated
        public Optional<DOM.Node> importedDocument() {
            return Optional.ofNullable(raw("importedDocument") == null ? null : DOM.Node.fromMap(java.util.Objects.requireNonNull(objectMap(raw("importedDocument")))));
        }
        /**
         * Distributed nodes for given insertion point.
         * @return the protocol field value, empty when absent
         */
        public Optional<java.util.List<DOM.BackendNode>> distributedNodes() {
            return Optional.ofNullable(list(raw("distributedNodes"), element0 -> java.util.Objects.requireNonNull(DOM.BackendNode.fromMap(java.util.Objects.requireNonNull(CdpObject.objectMap(element0))))));
        }
        /**
         * Whether the node is SVG.
         * @return the protocol field value, empty when absent
         */
        public Optional<Boolean> isSVG() {
            return Optional.ofNullable((Boolean) raw("isSVG"));
        }
        /**
         * Returns the compatibilityMode field.
         * @return the protocol field value, empty when absent
         */
        public Optional<DOM.CompatibilityMode> compatibilityMode() {
            return Optional.ofNullable(raw("compatibilityMode") == null ? null : DOM.CompatibilityMode.of((String) raw("compatibilityMode")));
        }
        /**
         * Returns the assignedSlot field.
         * @return the protocol field value, empty when absent
         */
        public Optional<DOM.BackendNode> assignedSlot() {
            return Optional.ofNullable(raw("assignedSlot") == null ? null : DOM.BackendNode.fromMap(java.util.Objects.requireNonNull(objectMap(raw("assignedSlot")))));
        }
        /**
         * Returns the isScrollable field.
         * <p><b>Experimental:</b> this part of CDP may change without notice.
         * @return the protocol field value, empty when absent
         */
        public Optional<Boolean> isScrollable() {
            return Optional.ofNullable((Boolean) raw("isScrollable"));
        }
        /**
         * Returns the affectedByStartingStyles field.
         * <p><b>Experimental:</b> this part of CDP may change without notice.
         * @return the protocol field value, empty when absent
         */
        public Optional<Boolean> affectedByStartingStyles() {
            return Optional.ofNullable((Boolean) raw("affectedByStartingStyles"));
        }
        /**
         * Returns the adoptedStyleSheets field.
         * <p><b>Experimental:</b> this part of CDP may change without notice.
         * @return the protocol field value, empty when absent
         */
        public Optional<java.util.List<DOM.StyleSheetId>> adoptedStyleSheets() {
            return Optional.ofNullable(list(raw("adoptedStyleSheets"), element0 -> new DOM.StyleSheetId((String) element0)));
        }
        /**
         * Returns the adProvenance field.
         * <p><b>Experimental:</b> this part of CDP may change without notice.
         * @return the protocol field value, empty when absent
         */
        public Optional<Network.AdProvenance> adProvenance() {
            return Optional.ofNullable(raw("adProvenance") == null ? null : Network.AdProvenance.fromMap(java.util.Objects.requireNonNull(objectMap(raw("adProvenance")))));
        }
        /**
         * Node identifier that is passed into the rest of the DOM messages as the {@code nodeId}. Backend will only push node with given {@code id} once. It is aware of all requested nodes and will only fire DOM events for nodes known to the client.
         * @param nodeId field value
         * @return this model
         */
        public Node nodeId(DOM.NodeId nodeId) {
            set("nodeId", nodeId);
            return this;
        }
        /**
         * The id of the parent node if any.
         * @param parentId field value; empty omits the value
         * @return this model
         */
        public Node parentId(Optional<DOM.NodeId> parentId) {
            set("parentId", parentId.orElse(null));
            return this;
        }
        /**
         * The id of the parent node if any.
         * @param parentId field value; null removes the value
         * @return this model
         */
        public Node parentId(DOM.NodeId parentId) {
            set("parentId", parentId);
            return this;
        }
        /**
         * The BackendNodeId for this node.
         * @param backendNodeId field value
         * @return this model
         */
        public Node backendNodeId(DOM.BackendNodeId backendNodeId) {
            set("backendNodeId", backendNodeId);
            return this;
        }
        /**
         * {@code Node}&#x27;s nodeType.
         * @param nodeType field value
         * @return this model
         */
        public Node nodeType(long nodeType) {
            set("nodeType", nodeType);
            return this;
        }
        /**
         * {@code Node}&#x27;s nodeName.
         * @param nodeName field value
         * @return this model
         */
        public Node nodeName(String nodeName) {
            set("nodeName", nodeName);
            return this;
        }
        /**
         * {@code Node}&#x27;s localName.
         * @param localName field value
         * @return this model
         */
        public Node localName(String localName) {
            set("localName", localName);
            return this;
        }
        /**
         * {@code Node}&#x27;s nodeValue.
         * @param nodeValue field value
         * @return this model
         */
        public Node nodeValue(String nodeValue) {
            set("nodeValue", nodeValue);
            return this;
        }
        /**
         * Child count for {@code Container} nodes.
         * @param childNodeCount field value; empty omits the value
         * @return this model
         */
        public Node childNodeCount(OptionalLong childNodeCount) {
            set("childNodeCount", childNodeCount.isPresent() ? childNodeCount.getAsLong() : null);
            return this;
        }
        /**
         * Child count for {@code Container} nodes.
         * @param childNodeCount field value; null removes the value
         * @return this model
         */
        public Node childNodeCount(Long childNodeCount) {
            set("childNodeCount", childNodeCount);
            return this;
        }
        /**
         * Child nodes of this node when requested with children.
         * @param children field value; empty omits the value
         * @return this model
         */
        public Node children(Optional<java.util.List<DOM.Node>> children) {
            set("children", children.orElse(null));
            return this;
        }
        /**
         * Child nodes of this node when requested with children.
         * @param children field value; null removes the value
         * @return this model
         */
        public Node children(java.util.List<DOM.Node> children) {
            set("children", children);
            return this;
        }
        /**
         * Attributes of the {@code Element} node in the form of flat array {@code [name1, value1, name2, value2]}.
         * @param attributes field value; empty omits the value
         * @return this model
         */
        public Node attributes(Optional<java.util.List<String>> attributes) {
            set("attributes", attributes.orElse(null));
            return this;
        }
        /**
         * Attributes of the {@code Element} node in the form of flat array {@code [name1, value1, name2, value2]}.
         * @param attributes field value; null removes the value
         * @return this model
         */
        public Node attributes(java.util.List<String> attributes) {
            set("attributes", attributes);
            return this;
        }
        /**
         * Document URL that {@code Document} or {@code FrameOwner} node points to.
         * @param documentURL field value; empty omits the value
         * @return this model
         */
        public Node documentURL(Optional<String> documentURL) {
            set("documentURL", documentURL.orElse(null));
            return this;
        }
        /**
         * Document URL that {@code Document} or {@code FrameOwner} node points to.
         * @param documentURL field value; null removes the value
         * @return this model
         */
        public Node documentURL(String documentURL) {
            set("documentURL", documentURL);
            return this;
        }
        /**
         * Base URL that {@code Document} or {@code FrameOwner} node uses for URL completion.
         * @param baseURL field value; empty omits the value
         * @return this model
         */
        public Node baseURL(Optional<String> baseURL) {
            set("baseURL", baseURL.orElse(null));
            return this;
        }
        /**
         * Base URL that {@code Document} or {@code FrameOwner} node uses for URL completion.
         * @param baseURL field value; null removes the value
         * @return this model
         */
        public Node baseURL(String baseURL) {
            set("baseURL", baseURL);
            return this;
        }
        /**
         * {@code DocumentType}&#x27;s publicId.
         * @param publicId field value; empty omits the value
         * @return this model
         */
        public Node publicId(Optional<String> publicId) {
            set("publicId", publicId.orElse(null));
            return this;
        }
        /**
         * {@code DocumentType}&#x27;s publicId.
         * @param publicId field value; null removes the value
         * @return this model
         */
        public Node publicId(String publicId) {
            set("publicId", publicId);
            return this;
        }
        /**
         * {@code DocumentType}&#x27;s systemId.
         * @param systemId field value; empty omits the value
         * @return this model
         */
        public Node systemId(Optional<String> systemId) {
            set("systemId", systemId.orElse(null));
            return this;
        }
        /**
         * {@code DocumentType}&#x27;s systemId.
         * @param systemId field value; null removes the value
         * @return this model
         */
        public Node systemId(String systemId) {
            set("systemId", systemId);
            return this;
        }
        /**
         * {@code DocumentType}&#x27;s internalSubset.
         * @param internalSubset field value; empty omits the value
         * @return this model
         */
        public Node internalSubset(Optional<String> internalSubset) {
            set("internalSubset", internalSubset.orElse(null));
            return this;
        }
        /**
         * {@code DocumentType}&#x27;s internalSubset.
         * @param internalSubset field value; null removes the value
         * @return this model
         */
        public Node internalSubset(String internalSubset) {
            set("internalSubset", internalSubset);
            return this;
        }
        /**
         * {@code Document}&#x27;s XML version in case of XML documents.
         * @param xmlVersion field value; empty omits the value
         * @return this model
         */
        public Node xmlVersion(Optional<String> xmlVersion) {
            set("xmlVersion", xmlVersion.orElse(null));
            return this;
        }
        /**
         * {@code Document}&#x27;s XML version in case of XML documents.
         * @param xmlVersion field value; null removes the value
         * @return this model
         */
        public Node xmlVersion(String xmlVersion) {
            set("xmlVersion", xmlVersion);
            return this;
        }
        /**
         * {@code Attr}&#x27;s name.
         * @param name field value; empty omits the value
         * @return this model
         */
        public Node name(Optional<String> name) {
            set("name", name.orElse(null));
            return this;
        }
        /**
         * {@code Attr}&#x27;s name.
         * @param name field value; null removes the value
         * @return this model
         */
        public Node name(String name) {
            set("name", name);
            return this;
        }
        /**
         * {@code Attr}&#x27;s value.
         * @param value field value; empty omits the value
         * @return this model
         */
        public Node value(Optional<String> value) {
            set("value", value.orElse(null));
            return this;
        }
        /**
         * {@code Attr}&#x27;s value.
         * @param value field value; null removes the value
         * @return this model
         */
        public Node value(String value) {
            set("value", value);
            return this;
        }
        /**
         * Pseudo element type for this node.
         * @param pseudoType field value; empty omits the value
         * @return this model
         */
        public Node pseudoType(Optional<DOM.PseudoType> pseudoType) {
            set("pseudoType", pseudoType.orElse(null));
            return this;
        }
        /**
         * Pseudo element type for this node.
         * @param pseudoType field value; null removes the value
         * @return this model
         */
        public Node pseudoType(DOM.PseudoType pseudoType) {
            set("pseudoType", pseudoType);
            return this;
        }
        /**
         * Pseudo element identifier for this node. Only present if there is a valid pseudoType.
         * @param pseudoIdentifier field value; empty omits the value
         * @return this model
         */
        public Node pseudoIdentifier(Optional<String> pseudoIdentifier) {
            set("pseudoIdentifier", pseudoIdentifier.orElse(null));
            return this;
        }
        /**
         * Pseudo element identifier for this node. Only present if there is a valid pseudoType.
         * @param pseudoIdentifier field value; null removes the value
         * @return this model
         */
        public Node pseudoIdentifier(String pseudoIdentifier) {
            set("pseudoIdentifier", pseudoIdentifier);
            return this;
        }
        /**
         * Shadow root type.
         * @param shadowRootType field value; empty omits the value
         * @return this model
         */
        public Node shadowRootType(Optional<DOM.ShadowRootType> shadowRootType) {
            set("shadowRootType", shadowRootType.orElse(null));
            return this;
        }
        /**
         * Shadow root type.
         * @param shadowRootType field value; null removes the value
         * @return this model
         */
        public Node shadowRootType(DOM.ShadowRootType shadowRootType) {
            set("shadowRootType", shadowRootType);
            return this;
        }
        /**
         * Frame ID for frame owner elements.
         * @param frameId field value; empty omits the value
         * @return this model
         */
        public Node frameId(Optional<Page.FrameId> frameId) {
            set("frameId", frameId.orElse(null));
            return this;
        }
        /**
         * Frame ID for frame owner elements.
         * @param frameId field value; null removes the value
         * @return this model
         */
        public Node frameId(Page.FrameId frameId) {
            set("frameId", frameId);
            return this;
        }
        /**
         * Content document for frame owner elements.
         * @param contentDocument field value; empty omits the value
         * @return this model
         */
        public Node contentDocument(Optional<DOM.Node> contentDocument) {
            set("contentDocument", contentDocument.orElse(null));
            return this;
        }
        /**
         * Content document for frame owner elements.
         * @param contentDocument field value; null removes the value
         * @return this model
         */
        public Node contentDocument(DOM.Node contentDocument) {
            set("contentDocument", contentDocument);
            return this;
        }
        /**
         * Shadow root list for given element host.
         * @param shadowRoots field value; empty omits the value
         * @return this model
         */
        public Node shadowRoots(Optional<java.util.List<DOM.Node>> shadowRoots) {
            set("shadowRoots", shadowRoots.orElse(null));
            return this;
        }
        /**
         * Shadow root list for given element host.
         * @param shadowRoots field value; null removes the value
         * @return this model
         */
        public Node shadowRoots(java.util.List<DOM.Node> shadowRoots) {
            set("shadowRoots", shadowRoots);
            return this;
        }
        /**
         * Content document fragment for template elements.
         * @param templateContent field value; empty omits the value
         * @return this model
         */
        public Node templateContent(Optional<DOM.Node> templateContent) {
            set("templateContent", templateContent.orElse(null));
            return this;
        }
        /**
         * Content document fragment for template elements.
         * @param templateContent field value; null removes the value
         * @return this model
         */
        public Node templateContent(DOM.Node templateContent) {
            set("templateContent", templateContent);
            return this;
        }
        /**
         * Pseudo elements associated with this node.
         * @param pseudoElements field value; empty omits the value
         * @return this model
         */
        public Node pseudoElements(Optional<java.util.List<DOM.Node>> pseudoElements) {
            set("pseudoElements", pseudoElements.orElse(null));
            return this;
        }
        /**
         * Pseudo elements associated with this node.
         * @param pseudoElements field value; null removes the value
         * @return this model
         */
        public Node pseudoElements(java.util.List<DOM.Node> pseudoElements) {
            set("pseudoElements", pseudoElements);
            return this;
        }
        /**
         * Deprecated, as the HTML Imports API has been removed (crbug.com/937746). This property used to return the imported document for the HTMLImport links. The property is always undefined now.
         * @param importedDocument field value; empty omits the value
         * @return this model
         * @deprecated Deprecated by the Chromium DevTools Protocol.
         */
        @Deprecated
        public Node importedDocument(Optional<DOM.Node> importedDocument) {
            set("importedDocument", importedDocument.orElse(null));
            return this;
        }
        /**
         * Deprecated, as the HTML Imports API has been removed (crbug.com/937746). This property used to return the imported document for the HTMLImport links. The property is always undefined now.
         * @param importedDocument field value; null removes the value
         * @return this model
         * @deprecated Deprecated by the Chromium DevTools Protocol.
         */
        @Deprecated
        public Node importedDocument(DOM.Node importedDocument) {
            set("importedDocument", importedDocument);
            return this;
        }
        /**
         * Distributed nodes for given insertion point.
         * @param distributedNodes field value; empty omits the value
         * @return this model
         */
        public Node distributedNodes(Optional<java.util.List<DOM.BackendNode>> distributedNodes) {
            set("distributedNodes", distributedNodes.orElse(null));
            return this;
        }
        /**
         * Distributed nodes for given insertion point.
         * @param distributedNodes field value; null removes the value
         * @return this model
         */
        public Node distributedNodes(java.util.List<DOM.BackendNode> distributedNodes) {
            set("distributedNodes", distributedNodes);
            return this;
        }
        /**
         * Whether the node is SVG.
         * @param isSVG field value; empty omits the value
         * @return this model
         */
        public Node isSVG(Optional<Boolean> isSVG) {
            set("isSVG", isSVG.orElse(null));
            return this;
        }
        /**
         * Whether the node is SVG.
         * @param isSVG field value; null removes the value
         * @return this model
         */
        public Node isSVG(Boolean isSVG) {
            set("isSVG", isSVG);
            return this;
        }
        /**
         * Sets the compatibilityMode field.
         * @param compatibilityMode field value; empty omits the value
         * @return this model
         */
        public Node compatibilityMode(Optional<DOM.CompatibilityMode> compatibilityMode) {
            set("compatibilityMode", compatibilityMode.orElse(null));
            return this;
        }
        /**
         * Sets the compatibilityMode field.
         * @param compatibilityMode field value; null removes the value
         * @return this model
         */
        public Node compatibilityMode(DOM.CompatibilityMode compatibilityMode) {
            set("compatibilityMode", compatibilityMode);
            return this;
        }
        /**
         * Sets the assignedSlot field.
         * @param assignedSlot field value; empty omits the value
         * @return this model
         */
        public Node assignedSlot(Optional<DOM.BackendNode> assignedSlot) {
            set("assignedSlot", assignedSlot.orElse(null));
            return this;
        }
        /**
         * Sets the assignedSlot field.
         * @param assignedSlot field value; null removes the value
         * @return this model
         */
        public Node assignedSlot(DOM.BackendNode assignedSlot) {
            set("assignedSlot", assignedSlot);
            return this;
        }
        /**
         * Sets the isScrollable field.
         * <p><b>Experimental:</b> this part of CDP may change without notice.
         * @param isScrollable field value; empty omits the value
         * @return this model
         */
        public Node isScrollable(Optional<Boolean> isScrollable) {
            set("isScrollable", isScrollable.orElse(null));
            return this;
        }
        /**
         * Sets the isScrollable field.
         * <p><b>Experimental:</b> this part of CDP may change without notice.
         * @param isScrollable field value; null removes the value
         * @return this model
         */
        public Node isScrollable(Boolean isScrollable) {
            set("isScrollable", isScrollable);
            return this;
        }
        /**
         * Sets the affectedByStartingStyles field.
         * <p><b>Experimental:</b> this part of CDP may change without notice.
         * @param affectedByStartingStyles field value; empty omits the value
         * @return this model
         */
        public Node affectedByStartingStyles(Optional<Boolean> affectedByStartingStyles) {
            set("affectedByStartingStyles", affectedByStartingStyles.orElse(null));
            return this;
        }
        /**
         * Sets the affectedByStartingStyles field.
         * <p><b>Experimental:</b> this part of CDP may change without notice.
         * @param affectedByStartingStyles field value; null removes the value
         * @return this model
         */
        public Node affectedByStartingStyles(Boolean affectedByStartingStyles) {
            set("affectedByStartingStyles", affectedByStartingStyles);
            return this;
        }
        /**
         * Sets the adoptedStyleSheets field.
         * <p><b>Experimental:</b> this part of CDP may change without notice.
         * @param adoptedStyleSheets field value; empty omits the value
         * @return this model
         */
        public Node adoptedStyleSheets(Optional<java.util.List<DOM.StyleSheetId>> adoptedStyleSheets) {
            set("adoptedStyleSheets", adoptedStyleSheets.orElse(null));
            return this;
        }
        /**
         * Sets the adoptedStyleSheets field.
         * <p><b>Experimental:</b> this part of CDP may change without notice.
         * @param adoptedStyleSheets field value; null removes the value
         * @return this model
         */
        public Node adoptedStyleSheets(java.util.List<DOM.StyleSheetId> adoptedStyleSheets) {
            set("adoptedStyleSheets", adoptedStyleSheets);
            return this;
        }
        /**
         * Sets the adProvenance field.
         * <p><b>Experimental:</b> this part of CDP may change without notice.
         * @param adProvenance field value; empty omits the value
         * @return this model
         */
        public Node adProvenance(Optional<Network.AdProvenance> adProvenance) {
            set("adProvenance", adProvenance.orElse(null));
            return this;
        }
        /**
         * Sets the adProvenance field.
         * <p><b>Experimental:</b> this part of CDP may change without notice.
         * @param adProvenance field value; null removes the value
         * @return this model
         */
        public Node adProvenance(Network.AdProvenance adProvenance) {
            set("adProvenance", adProvenance);
            return this;
        }
    }
    /**
     * A structure to hold the top-level node of a detached tree and an array of its retained descendants.
     */
    public static final class DetachedElementInfo extends CdpObject {
        public DetachedElementInfo() {}
        private DetachedElementInfo(Map<String, Object> values) { super(values); }
        public static DetachedElementInfo fromMap(Map<String, Object> values) {
            return new DetachedElementInfo(values);
        }
        /**
         * Returns the treeNode field.
         * @return the protocol field value
         */
        public DOM.Node treeNode() {
            return java.util.Objects.requireNonNull(DOM.Node.fromMap(java.util.Objects.requireNonNull(CdpObject.objectMap(require("treeNode")))));
        }
        /**
         * Returns the retainedNodeIds field.
         * @return the protocol field value
         */
        public java.util.List<DOM.NodeId> retainedNodeIds() {
            return CdpObject.requireList(require("retainedNodeIds"), element0 -> new DOM.NodeId(((Number) element0).longValue()));
        }
        /**
         * Sets the treeNode field.
         * @param treeNode field value
         * @return this model
         */
        public DetachedElementInfo treeNode(DOM.Node treeNode) {
            set("treeNode", treeNode);
            return this;
        }
        /**
         * Sets the retainedNodeIds field.
         * @param retainedNodeIds field value
         * @return this model
         */
        public DetachedElementInfo retainedNodeIds(java.util.List<DOM.NodeId> retainedNodeIds) {
            set("retainedNodeIds", retainedNodeIds);
            return this;
        }
    }
    /**
     * A structure holding an RGBA color.
     */
    public static final class RGBA extends CdpObject {
        public RGBA() {}
        private RGBA(Map<String, Object> values) { super(values); }
        public static RGBA fromMap(Map<String, Object> values) {
            return new RGBA(values);
        }
        /**
         * The red component, in the [0-255] range.
         * @return the protocol field value
         */
        public long r() {
            return ((Number) require("r")).longValue();
        }
        /**
         * The green component, in the [0-255] range.
         * @return the protocol field value
         */
        public long g() {
            return ((Number) require("g")).longValue();
        }
        /**
         * The blue component, in the [0-255] range.
         * @return the protocol field value
         */
        public long b() {
            return ((Number) require("b")).longValue();
        }
        /**
         * The alpha component, in the [0-1] range (default: 1).
         * @return the protocol field value, empty when absent
         */
        public OptionalDouble a() {
            Double value = CdpObject.numberAsDouble(raw("a"));
            return value == null ? OptionalDouble.empty() : OptionalDouble.of(value);
        }
        /**
         * The red component, in the [0-255] range.
         * @param r field value
         * @return this model
         */
        public RGBA r(long r) {
            set("r", r);
            return this;
        }
        /**
         * The green component, in the [0-255] range.
         * @param g field value
         * @return this model
         */
        public RGBA g(long g) {
            set("g", g);
            return this;
        }
        /**
         * The blue component, in the [0-255] range.
         * @param b field value
         * @return this model
         */
        public RGBA b(long b) {
            set("b", b);
            return this;
        }
        /**
         * The alpha component, in the [0-1] range (default: 1).
         * @param a field value; empty omits the value
         * @return this model
         */
        public RGBA a(OptionalDouble a) {
            set("a", a.isPresent() ? a.getAsDouble() : null);
            return this;
        }
        /**
         * The alpha component, in the [0-1] range (default: 1).
         * @param a field value; null removes the value
         * @return this model
         */
        public RGBA a(Double a) {
            set("a", a);
            return this;
        }
    }
    /**
     * Box model.
     */
    public static final class BoxModel extends CdpObject {
        public BoxModel() {}
        private BoxModel(Map<String, Object> values) { super(values); }
        public static BoxModel fromMap(Map<String, Object> values) {
            return new BoxModel(values);
        }
        /**
         * Content box
         * @return the protocol field value
         */
        public java.util.List<Double> content() {
            return CdpObject.requireList(require("content"), element0 -> ((Number) element0).doubleValue());
        }
        /**
         * Padding box
         * @return the protocol field value
         */
        public java.util.List<Double> padding() {
            return CdpObject.requireList(require("padding"), element0 -> ((Number) element0).doubleValue());
        }
        /**
         * Border box
         * @return the protocol field value
         */
        public java.util.List<Double> border() {
            return CdpObject.requireList(require("border"), element0 -> ((Number) element0).doubleValue());
        }
        /**
         * Margin box
         * @return the protocol field value
         */
        public java.util.List<Double> margin() {
            return CdpObject.requireList(require("margin"), element0 -> ((Number) element0).doubleValue());
        }
        /**
         * Node width
         * @return the protocol field value
         */
        public long width() {
            return ((Number) require("width")).longValue();
        }
        /**
         * Node height
         * @return the protocol field value
         */
        public long height() {
            return ((Number) require("height")).longValue();
        }
        /**
         * Shape outside coordinates
         * @return the protocol field value, empty when absent
         */
        public Optional<DOM.ShapeOutsideInfo> shapeOutside() {
            return Optional.ofNullable(raw("shapeOutside") == null ? null : DOM.ShapeOutsideInfo.fromMap(java.util.Objects.requireNonNull(objectMap(raw("shapeOutside")))));
        }
        /**
         * Content box
         * @param content field value
         * @return this model
         */
        public BoxModel content(java.util.List<Double> content) {
            set("content", content);
            return this;
        }
        /**
         * Padding box
         * @param padding field value
         * @return this model
         */
        public BoxModel padding(java.util.List<Double> padding) {
            set("padding", padding);
            return this;
        }
        /**
         * Border box
         * @param border field value
         * @return this model
         */
        public BoxModel border(java.util.List<Double> border) {
            set("border", border);
            return this;
        }
        /**
         * Margin box
         * @param margin field value
         * @return this model
         */
        public BoxModel margin(java.util.List<Double> margin) {
            set("margin", margin);
            return this;
        }
        /**
         * Node width
         * @param width field value
         * @return this model
         */
        public BoxModel width(long width) {
            set("width", width);
            return this;
        }
        /**
         * Node height
         * @param height field value
         * @return this model
         */
        public BoxModel height(long height) {
            set("height", height);
            return this;
        }
        /**
         * Shape outside coordinates
         * @param shapeOutside field value; empty omits the value
         * @return this model
         */
        public BoxModel shapeOutside(Optional<DOM.ShapeOutsideInfo> shapeOutside) {
            set("shapeOutside", shapeOutside.orElse(null));
            return this;
        }
        /**
         * Shape outside coordinates
         * @param shapeOutside field value; null removes the value
         * @return this model
         */
        public BoxModel shapeOutside(DOM.ShapeOutsideInfo shapeOutside) {
            set("shapeOutside", shapeOutside);
            return this;
        }
    }
    /**
     * CSS Shape Outside details.
     */
    public static final class ShapeOutsideInfo extends CdpObject {
        public ShapeOutsideInfo() {}
        private ShapeOutsideInfo(Map<String, Object> values) { super(values); }
        public static ShapeOutsideInfo fromMap(Map<String, Object> values) {
            return new ShapeOutsideInfo(values);
        }
        /**
         * Shape bounds
         * @return the protocol field value
         */
        public java.util.List<Double> bounds() {
            return CdpObject.requireList(require("bounds"), element0 -> ((Number) element0).doubleValue());
        }
        /**
         * Shape coordinate details
         * @return the protocol field value
         */
        public java.util.List<Object> shape() {
            return CdpObject.requireList(require("shape"), element0 -> element0);
        }
        /**
         * Margin shape bounds
         * @return the protocol field value
         */
        public java.util.List<Object> marginShape() {
            return CdpObject.requireList(require("marginShape"), element0 -> element0);
        }
        /**
         * Shape bounds
         * @param bounds field value
         * @return this model
         */
        public ShapeOutsideInfo bounds(java.util.List<Double> bounds) {
            set("bounds", bounds);
            return this;
        }
        /**
         * Shape coordinate details
         * @param shape field value
         * @return this model
         */
        public ShapeOutsideInfo shape(java.util.List<Object> shape) {
            set("shape", shape);
            return this;
        }
        /**
         * Margin shape bounds
         * @param marginShape field value
         * @return this model
         */
        public ShapeOutsideInfo marginShape(java.util.List<Object> marginShape) {
            set("marginShape", marginShape);
            return this;
        }
    }
    /**
     * Rectangle.
     */
    public static final class Rect extends CdpObject {
        public Rect() {}
        private Rect(Map<String, Object> values) { super(values); }
        public static Rect fromMap(Map<String, Object> values) {
            return new Rect(values);
        }
        /**
         * X coordinate
         * @return the protocol field value
         */
        public double x() {
            return ((Number) require("x")).doubleValue();
        }
        /**
         * Y coordinate
         * @return the protocol field value
         */
        public double y() {
            return ((Number) require("y")).doubleValue();
        }
        /**
         * Rectangle width
         * @return the protocol field value
         */
        public double width() {
            return ((Number) require("width")).doubleValue();
        }
        /**
         * Rectangle height
         * @return the protocol field value
         */
        public double height() {
            return ((Number) require("height")).doubleValue();
        }
        /**
         * X coordinate
         * @param x field value
         * @return this model
         */
        public Rect x(double x) {
            set("x", x);
            return this;
        }
        /**
         * Y coordinate
         * @param y field value
         * @return this model
         */
        public Rect y(double y) {
            set("y", y);
            return this;
        }
        /**
         * Rectangle width
         * @param width field value
         * @return this model
         */
        public Rect width(double width) {
            set("width", width);
            return this;
        }
        /**
         * Rectangle height
         * @param height field value
         * @return this model
         */
        public Rect height(double height) {
            set("height", height);
            return this;
        }
    }
    /**
     */
    public static final class CSSComputedStyleProperty extends CdpObject {
        public CSSComputedStyleProperty() {}
        private CSSComputedStyleProperty(Map<String, Object> values) { super(values); }
        public static CSSComputedStyleProperty fromMap(Map<String, Object> values) {
            return new CSSComputedStyleProperty(values);
        }
        /**
         * Computed style property name.
         * @return the protocol field value
         */
        public String name() {
            return (String) require("name");
        }
        /**
         * Computed style property value.
         * @return the protocol field value
         */
        public String value() {
            return (String) require("value");
        }
        /**
         * Computed style property name.
         * @param name field value
         * @return this model
         */
        public CSSComputedStyleProperty name(String name) {
            set("name", name);
            return this;
        }
        /**
         * Computed style property value.
         * @param value field value
         * @return this model
         */
        public CSSComputedStyleProperty value(String value) {
            set("value", value);
            return this;
        }
    }
    /**
     * Collects class names for the node with given id and all of it&#x27;s child nodes.
     * <p><b>Experimental:</b> this part of CDP may change without notice.
     */
    public static final class CollectClassNamesFromSubtreeRequest extends CdpObject {
        public CollectClassNamesFromSubtreeRequest() {}
        /**
         * Collects class names for the node with given id and all of it&#x27;s child nodes.
         * <p><b>Experimental:</b> this part of CDP may change without notice.
         * @param nodeId protocol value
         */
        public CollectClassNamesFromSubtreeRequest(DOM.NodeId nodeId) {
            set("nodeId", nodeId);
        }
        public static CollectClassNamesFromSubtreeRequest fromMap(Map<String, Object> values) {
            CollectClassNamesFromSubtreeRequest instance_ = new CollectClassNamesFromSubtreeRequest();
            if (values != null) instance_.values.putAll(values);
            return instance_;
        }
        /**
         * Id of the node to collect class names.
         * @return the protocol field value
         */
        public DOM.NodeId nodeId() {
            return new DOM.NodeId(((Number) require("nodeId")).longValue());
        }
        /**
         * Id of the node to collect class names.
         * @param nodeId field value
         * @return this model
         */
        public CollectClassNamesFromSubtreeRequest nodeId(DOM.NodeId nodeId) {
            set("nodeId", nodeId);
            return this;
        }
    }
    /**
     * Creates a deep copy of the specified node and places it into the target container before the given anchor.
     * <p><b>Experimental:</b> this part of CDP may change without notice.
     */
    public static final class CopyToRequest extends CdpObject {
        public CopyToRequest() {}
        /**
         * Creates a deep copy of the specified node and places it into the target container before the given anchor.
         * <p><b>Experimental:</b> this part of CDP may change without notice.
         * @param nodeId protocol value
         * @param targetNodeId protocol value
         */
        public CopyToRequest(DOM.NodeId nodeId, DOM.NodeId targetNodeId) {
            set("nodeId", nodeId);
            set("targetNodeId", targetNodeId);
        }
        public static CopyToRequest fromMap(Map<String, Object> values) {
            CopyToRequest instance_ = new CopyToRequest();
            if (values != null) instance_.values.putAll(values);
            return instance_;
        }
        /**
         * Id of the node to copy.
         * @return the protocol field value
         */
        public DOM.NodeId nodeId() {
            return new DOM.NodeId(((Number) require("nodeId")).longValue());
        }
        /**
         * Id of the element to drop the copy into.
         * @return the protocol field value
         */
        public DOM.NodeId targetNodeId() {
            return new DOM.NodeId(((Number) require("targetNodeId")).longValue());
        }
        /**
         * Drop the copy before this node (if absent, the copy becomes the last child of {@code targetNodeId}).
         * @return the protocol field value, empty when absent
         */
        public Optional<DOM.NodeId> insertBeforeNodeId() {
            return Optional.ofNullable(raw("insertBeforeNodeId") == null ? null : new DOM.NodeId(((Number) raw("insertBeforeNodeId")).longValue()));
        }
        /**
         * Id of the node to copy.
         * @param nodeId field value
         * @return this model
         */
        public CopyToRequest nodeId(DOM.NodeId nodeId) {
            set("nodeId", nodeId);
            return this;
        }
        /**
         * Id of the element to drop the copy into.
         * @param targetNodeId field value
         * @return this model
         */
        public CopyToRequest targetNodeId(DOM.NodeId targetNodeId) {
            set("targetNodeId", targetNodeId);
            return this;
        }
        /**
         * Drop the copy before this node (if absent, the copy becomes the last child of {@code targetNodeId}).
         * @param insertBeforeNodeId field value; empty omits the value
         * @return this model
         */
        public CopyToRequest insertBeforeNodeId(Optional<DOM.NodeId> insertBeforeNodeId) {
            set("insertBeforeNodeId", insertBeforeNodeId.orElse(null));
            return this;
        }
        /**
         * Drop the copy before this node (if absent, the copy becomes the last child of {@code targetNodeId}).
         * @param insertBeforeNodeId field value; null removes the value
         * @return this model
         */
        public CopyToRequest insertBeforeNodeId(DOM.NodeId insertBeforeNodeId) {
            set("insertBeforeNodeId", insertBeforeNodeId);
            return this;
        }
    }
    /**
     * Describes node given its id, does not require domain to be enabled. Does not start tracking any objects, can be used for automation.
     */
    public static final class DescribeNodeRequest extends CdpObject {
        public DescribeNodeRequest() {}
        public static DescribeNodeRequest fromMap(Map<String, Object> values) {
            DescribeNodeRequest instance_ = new DescribeNodeRequest();
            if (values != null) instance_.values.putAll(values);
            return instance_;
        }
        /**
         * Identifier of the node.
         * @return the protocol field value, empty when absent
         */
        public Optional<DOM.NodeId> nodeId() {
            return Optional.ofNullable(raw("nodeId") == null ? null : new DOM.NodeId(((Number) raw("nodeId")).longValue()));
        }
        /**
         * Identifier of the backend node.
         * @return the protocol field value, empty when absent
         */
        public Optional<DOM.BackendNodeId> backendNodeId() {
            return Optional.ofNullable(raw("backendNodeId") == null ? null : new DOM.BackendNodeId(((Number) raw("backendNodeId")).longValue()));
        }
        /**
         * JavaScript object id of the node wrapper.
         * @return the protocol field value, empty when absent
         */
        public Optional<Runtime.RemoteObjectId> objectId() {
            return Optional.ofNullable(raw("objectId") == null ? null : new Runtime.RemoteObjectId((String) raw("objectId")));
        }
        /**
         * The maximum depth at which children should be retrieved, defaults to 1. Use -1 for the entire subtree or provide an integer larger than 0.
         * @return the protocol field value, empty when absent
         */
        public OptionalLong depth() {
            Long value = CdpObject.numberAsLong(raw("depth"));
            return value == null ? OptionalLong.empty() : OptionalLong.of(value);
        }
        /**
         * Whether or not iframes and shadow roots should be traversed when returning the subtree (default is false).
         * @return the protocol field value, empty when absent
         */
        public Optional<Boolean> pierce() {
            return Optional.ofNullable((Boolean) raw("pierce"));
        }
        /**
         * Identifier of the node.
         * @param nodeId field value; empty omits the value
         * @return this model
         */
        public DescribeNodeRequest nodeId(Optional<DOM.NodeId> nodeId) {
            set("nodeId", nodeId.orElse(null));
            return this;
        }
        /**
         * Identifier of the node.
         * @param nodeId field value; null removes the value
         * @return this model
         */
        public DescribeNodeRequest nodeId(DOM.NodeId nodeId) {
            set("nodeId", nodeId);
            return this;
        }
        /**
         * Identifier of the backend node.
         * @param backendNodeId field value; empty omits the value
         * @return this model
         */
        public DescribeNodeRequest backendNodeId(Optional<DOM.BackendNodeId> backendNodeId) {
            set("backendNodeId", backendNodeId.orElse(null));
            return this;
        }
        /**
         * Identifier of the backend node.
         * @param backendNodeId field value; null removes the value
         * @return this model
         */
        public DescribeNodeRequest backendNodeId(DOM.BackendNodeId backendNodeId) {
            set("backendNodeId", backendNodeId);
            return this;
        }
        /**
         * JavaScript object id of the node wrapper.
         * @param objectId field value; empty omits the value
         * @return this model
         */
        public DescribeNodeRequest objectId(Optional<Runtime.RemoteObjectId> objectId) {
            set("objectId", objectId.orElse(null));
            return this;
        }
        /**
         * JavaScript object id of the node wrapper.
         * @param objectId field value; null removes the value
         * @return this model
         */
        public DescribeNodeRequest objectId(Runtime.RemoteObjectId objectId) {
            set("objectId", objectId);
            return this;
        }
        /**
         * The maximum depth at which children should be retrieved, defaults to 1. Use -1 for the entire subtree or provide an integer larger than 0.
         * @param depth field value; empty omits the value
         * @return this model
         */
        public DescribeNodeRequest depth(OptionalLong depth) {
            set("depth", depth.isPresent() ? depth.getAsLong() : null);
            return this;
        }
        /**
         * The maximum depth at which children should be retrieved, defaults to 1. Use -1 for the entire subtree or provide an integer larger than 0.
         * @param depth field value; null removes the value
         * @return this model
         */
        public DescribeNodeRequest depth(Long depth) {
            set("depth", depth);
            return this;
        }
        /**
         * Whether or not iframes and shadow roots should be traversed when returning the subtree (default is false).
         * @param pierce field value; empty omits the value
         * @return this model
         */
        public DescribeNodeRequest pierce(Optional<Boolean> pierce) {
            set("pierce", pierce.orElse(null));
            return this;
        }
        /**
         * Whether or not iframes and shadow roots should be traversed when returning the subtree (default is false).
         * @param pierce field value; null removes the value
         * @return this model
         */
        public DescribeNodeRequest pierce(Boolean pierce) {
            set("pierce", pierce);
            return this;
        }
    }
    /**
     * Scrolls the specified rect of the given node into view if not already visible. Note: exactly one between nodeId, backendNodeId and objectId should be passed to identify the node.
     */
    public static final class ScrollIntoViewIfNeededRequest extends CdpObject {
        public ScrollIntoViewIfNeededRequest() {}
        public static ScrollIntoViewIfNeededRequest fromMap(Map<String, Object> values) {
            ScrollIntoViewIfNeededRequest instance_ = new ScrollIntoViewIfNeededRequest();
            if (values != null) instance_.values.putAll(values);
            return instance_;
        }
        /**
         * Identifier of the node.
         * @return the protocol field value, empty when absent
         */
        public Optional<DOM.NodeId> nodeId() {
            return Optional.ofNullable(raw("nodeId") == null ? null : new DOM.NodeId(((Number) raw("nodeId")).longValue()));
        }
        /**
         * Identifier of the backend node.
         * @return the protocol field value, empty when absent
         */
        public Optional<DOM.BackendNodeId> backendNodeId() {
            return Optional.ofNullable(raw("backendNodeId") == null ? null : new DOM.BackendNodeId(((Number) raw("backendNodeId")).longValue()));
        }
        /**
         * JavaScript object id of the node wrapper.
         * @return the protocol field value, empty when absent
         */
        public Optional<Runtime.RemoteObjectId> objectId() {
            return Optional.ofNullable(raw("objectId") == null ? null : new Runtime.RemoteObjectId((String) raw("objectId")));
        }
        /**
         * The rect to be scrolled into view, relative to the node&#x27;s border box, in CSS pixels. When omitted, center of the node will be used, similar to Element.scrollIntoView.
         * @return the protocol field value, empty when absent
         */
        public Optional<DOM.Rect> rect() {
            return Optional.ofNullable(raw("rect") == null ? null : DOM.Rect.fromMap(java.util.Objects.requireNonNull(objectMap(raw("rect")))));
        }
        /**
         * Identifier of the node.
         * @param nodeId field value; empty omits the value
         * @return this model
         */
        public ScrollIntoViewIfNeededRequest nodeId(Optional<DOM.NodeId> nodeId) {
            set("nodeId", nodeId.orElse(null));
            return this;
        }
        /**
         * Identifier of the node.
         * @param nodeId field value; null removes the value
         * @return this model
         */
        public ScrollIntoViewIfNeededRequest nodeId(DOM.NodeId nodeId) {
            set("nodeId", nodeId);
            return this;
        }
        /**
         * Identifier of the backend node.
         * @param backendNodeId field value; empty omits the value
         * @return this model
         */
        public ScrollIntoViewIfNeededRequest backendNodeId(Optional<DOM.BackendNodeId> backendNodeId) {
            set("backendNodeId", backendNodeId.orElse(null));
            return this;
        }
        /**
         * Identifier of the backend node.
         * @param backendNodeId field value; null removes the value
         * @return this model
         */
        public ScrollIntoViewIfNeededRequest backendNodeId(DOM.BackendNodeId backendNodeId) {
            set("backendNodeId", backendNodeId);
            return this;
        }
        /**
         * JavaScript object id of the node wrapper.
         * @param objectId field value; empty omits the value
         * @return this model
         */
        public ScrollIntoViewIfNeededRequest objectId(Optional<Runtime.RemoteObjectId> objectId) {
            set("objectId", objectId.orElse(null));
            return this;
        }
        /**
         * JavaScript object id of the node wrapper.
         * @param objectId field value; null removes the value
         * @return this model
         */
        public ScrollIntoViewIfNeededRequest objectId(Runtime.RemoteObjectId objectId) {
            set("objectId", objectId);
            return this;
        }
        /**
         * The rect to be scrolled into view, relative to the node&#x27;s border box, in CSS pixels. When omitted, center of the node will be used, similar to Element.scrollIntoView.
         * @param rect field value; empty omits the value
         * @return this model
         */
        public ScrollIntoViewIfNeededRequest rect(Optional<DOM.Rect> rect) {
            set("rect", rect.orElse(null));
            return this;
        }
        /**
         * The rect to be scrolled into view, relative to the node&#x27;s border box, in CSS pixels. When omitted, center of the node will be used, similar to Element.scrollIntoView.
         * @param rect field value; null removes the value
         * @return this model
         */
        public ScrollIntoViewIfNeededRequest rect(DOM.Rect rect) {
            set("rect", rect);
            return this;
        }
    }
    /**
     * Discards search results from the session with the given id. {@code getSearchResults} should no longer be called for that search.
     * <p><b>Experimental:</b> this part of CDP may change without notice.
     */
    public static final class DiscardSearchResultsRequest extends CdpObject {
        public DiscardSearchResultsRequest() {}
        /**
         * Discards search results from the session with the given id. {@code getSearchResults} should no longer be called for that search.
         * <p><b>Experimental:</b> this part of CDP may change without notice.
         * @param searchId protocol value
         */
        public DiscardSearchResultsRequest(String searchId) {
            set("searchId", searchId);
        }
        public static DiscardSearchResultsRequest fromMap(Map<String, Object> values) {
            DiscardSearchResultsRequest instance_ = new DiscardSearchResultsRequest();
            if (values != null) instance_.values.putAll(values);
            return instance_;
        }
        /**
         * Unique search session identifier.
         * @return the protocol field value
         */
        public String searchId() {
            return (String) require("searchId");
        }
        /**
         * Unique search session identifier.
         * @param searchId field value
         * @return this model
         */
        public DiscardSearchResultsRequest searchId(String searchId) {
            set("searchId", searchId);
            return this;
        }
    }
    /**
     * Enables DOM agent for the given page.
     */
    public static final class EnableRequest extends CdpObject {
        public EnableRequest() {}
        public static EnableRequest fromMap(Map<String, Object> values) {
            EnableRequest instance_ = new EnableRequest();
            if (values != null) instance_.values.putAll(values);
            return instance_;
        }
        /**
         * Whether to include whitespaces in the children array of returned Nodes.
         * <p><b>Experimental:</b> this part of CDP may change without notice.
         * @return the protocol field value, empty when absent
         */
        public Optional<EnableIncludeWhitespaceValues> includeWhitespace() {
            return Optional.ofNullable(raw("includeWhitespace") == null ? null : EnableIncludeWhitespaceValues.of((String) raw("includeWhitespace")));
        }
        /**
         * Whether to include whitespaces in the children array of returned Nodes.
         * <p><b>Experimental:</b> this part of CDP may change without notice.
         * @param includeWhitespace field value; empty omits the value
         * @return this model
         */
        public EnableRequest includeWhitespace(Optional<EnableIncludeWhitespaceValues> includeWhitespace) {
            set("includeWhitespace", includeWhitespace.orElse(null));
            return this;
        }
        /**
         * Whether to include whitespaces in the children array of returned Nodes.
         * <p><b>Experimental:</b> this part of CDP may change without notice.
         * @param includeWhitespace field value; null removes the value
         * @return this model
         */
        public EnableRequest includeWhitespace(EnableIncludeWhitespaceValues includeWhitespace) {
            set("includeWhitespace", includeWhitespace);
            return this;
        }
    }
    /**
     * Focuses the given element.
     */
    public static final class FocusRequest extends CdpObject {
        public FocusRequest() {}
        public static FocusRequest fromMap(Map<String, Object> values) {
            FocusRequest instance_ = new FocusRequest();
            if (values != null) instance_.values.putAll(values);
            return instance_;
        }
        /**
         * Identifier of the node.
         * @return the protocol field value, empty when absent
         */
        public Optional<DOM.NodeId> nodeId() {
            return Optional.ofNullable(raw("nodeId") == null ? null : new DOM.NodeId(((Number) raw("nodeId")).longValue()));
        }
        /**
         * Identifier of the backend node.
         * @return the protocol field value, empty when absent
         */
        public Optional<DOM.BackendNodeId> backendNodeId() {
            return Optional.ofNullable(raw("backendNodeId") == null ? null : new DOM.BackendNodeId(((Number) raw("backendNodeId")).longValue()));
        }
        /**
         * JavaScript object id of the node wrapper.
         * @return the protocol field value, empty when absent
         */
        public Optional<Runtime.RemoteObjectId> objectId() {
            return Optional.ofNullable(raw("objectId") == null ? null : new Runtime.RemoteObjectId((String) raw("objectId")));
        }
        /**
         * Identifier of the node.
         * @param nodeId field value; empty omits the value
         * @return this model
         */
        public FocusRequest nodeId(Optional<DOM.NodeId> nodeId) {
            set("nodeId", nodeId.orElse(null));
            return this;
        }
        /**
         * Identifier of the node.
         * @param nodeId field value; null removes the value
         * @return this model
         */
        public FocusRequest nodeId(DOM.NodeId nodeId) {
            set("nodeId", nodeId);
            return this;
        }
        /**
         * Identifier of the backend node.
         * @param backendNodeId field value; empty omits the value
         * @return this model
         */
        public FocusRequest backendNodeId(Optional<DOM.BackendNodeId> backendNodeId) {
            set("backendNodeId", backendNodeId.orElse(null));
            return this;
        }
        /**
         * Identifier of the backend node.
         * @param backendNodeId field value; null removes the value
         * @return this model
         */
        public FocusRequest backendNodeId(DOM.BackendNodeId backendNodeId) {
            set("backendNodeId", backendNodeId);
            return this;
        }
        /**
         * JavaScript object id of the node wrapper.
         * @param objectId field value; empty omits the value
         * @return this model
         */
        public FocusRequest objectId(Optional<Runtime.RemoteObjectId> objectId) {
            set("objectId", objectId.orElse(null));
            return this;
        }
        /**
         * JavaScript object id of the node wrapper.
         * @param objectId field value; null removes the value
         * @return this model
         */
        public FocusRequest objectId(Runtime.RemoteObjectId objectId) {
            set("objectId", objectId);
            return this;
        }
    }
    /**
     * Returns attributes for the specified node.
     */
    public static final class GetAttributesRequest extends CdpObject {
        public GetAttributesRequest() {}
        /**
         * Returns attributes for the specified node.
         * @param nodeId protocol value
         */
        public GetAttributesRequest(DOM.NodeId nodeId) {
            set("nodeId", nodeId);
        }
        public static GetAttributesRequest fromMap(Map<String, Object> values) {
            GetAttributesRequest instance_ = new GetAttributesRequest();
            if (values != null) instance_.values.putAll(values);
            return instance_;
        }
        /**
         * Id of the node to retrieve attributes for.
         * @return the protocol field value
         */
        public DOM.NodeId nodeId() {
            return new DOM.NodeId(((Number) require("nodeId")).longValue());
        }
        /**
         * Id of the node to retrieve attributes for.
         * @param nodeId field value
         * @return this model
         */
        public GetAttributesRequest nodeId(DOM.NodeId nodeId) {
            set("nodeId", nodeId);
            return this;
        }
    }
    /**
     * Returns boxes for the given node.
     */
    public static final class GetBoxModelRequest extends CdpObject {
        public GetBoxModelRequest() {}
        public static GetBoxModelRequest fromMap(Map<String, Object> values) {
            GetBoxModelRequest instance_ = new GetBoxModelRequest();
            if (values != null) instance_.values.putAll(values);
            return instance_;
        }
        /**
         * Identifier of the node.
         * @return the protocol field value, empty when absent
         */
        public Optional<DOM.NodeId> nodeId() {
            return Optional.ofNullable(raw("nodeId") == null ? null : new DOM.NodeId(((Number) raw("nodeId")).longValue()));
        }
        /**
         * Identifier of the backend node.
         * @return the protocol field value, empty when absent
         */
        public Optional<DOM.BackendNodeId> backendNodeId() {
            return Optional.ofNullable(raw("backendNodeId") == null ? null : new DOM.BackendNodeId(((Number) raw("backendNodeId")).longValue()));
        }
        /**
         * JavaScript object id of the node wrapper.
         * @return the protocol field value, empty when absent
         */
        public Optional<Runtime.RemoteObjectId> objectId() {
            return Optional.ofNullable(raw("objectId") == null ? null : new Runtime.RemoteObjectId((String) raw("objectId")));
        }
        /**
         * Identifier of the node.
         * @param nodeId field value; empty omits the value
         * @return this model
         */
        public GetBoxModelRequest nodeId(Optional<DOM.NodeId> nodeId) {
            set("nodeId", nodeId.orElse(null));
            return this;
        }
        /**
         * Identifier of the node.
         * @param nodeId field value; null removes the value
         * @return this model
         */
        public GetBoxModelRequest nodeId(DOM.NodeId nodeId) {
            set("nodeId", nodeId);
            return this;
        }
        /**
         * Identifier of the backend node.
         * @param backendNodeId field value; empty omits the value
         * @return this model
         */
        public GetBoxModelRequest backendNodeId(Optional<DOM.BackendNodeId> backendNodeId) {
            set("backendNodeId", backendNodeId.orElse(null));
            return this;
        }
        /**
         * Identifier of the backend node.
         * @param backendNodeId field value; null removes the value
         * @return this model
         */
        public GetBoxModelRequest backendNodeId(DOM.BackendNodeId backendNodeId) {
            set("backendNodeId", backendNodeId);
            return this;
        }
        /**
         * JavaScript object id of the node wrapper.
         * @param objectId field value; empty omits the value
         * @return this model
         */
        public GetBoxModelRequest objectId(Optional<Runtime.RemoteObjectId> objectId) {
            set("objectId", objectId.orElse(null));
            return this;
        }
        /**
         * JavaScript object id of the node wrapper.
         * @param objectId field value; null removes the value
         * @return this model
         */
        public GetBoxModelRequest objectId(Runtime.RemoteObjectId objectId) {
            set("objectId", objectId);
            return this;
        }
    }
    /**
     * Returns quads that describe node position on the page. This method might return multiple quads for inline nodes.
     * <p><b>Experimental:</b> this part of CDP may change without notice.
     */
    public static final class GetContentQuadsRequest extends CdpObject {
        public GetContentQuadsRequest() {}
        public static GetContentQuadsRequest fromMap(Map<String, Object> values) {
            GetContentQuadsRequest instance_ = new GetContentQuadsRequest();
            if (values != null) instance_.values.putAll(values);
            return instance_;
        }
        /**
         * Identifier of the node.
         * @return the protocol field value, empty when absent
         */
        public Optional<DOM.NodeId> nodeId() {
            return Optional.ofNullable(raw("nodeId") == null ? null : new DOM.NodeId(((Number) raw("nodeId")).longValue()));
        }
        /**
         * Identifier of the backend node.
         * @return the protocol field value, empty when absent
         */
        public Optional<DOM.BackendNodeId> backendNodeId() {
            return Optional.ofNullable(raw("backendNodeId") == null ? null : new DOM.BackendNodeId(((Number) raw("backendNodeId")).longValue()));
        }
        /**
         * JavaScript object id of the node wrapper.
         * @return the protocol field value, empty when absent
         */
        public Optional<Runtime.RemoteObjectId> objectId() {
            return Optional.ofNullable(raw("objectId") == null ? null : new Runtime.RemoteObjectId((String) raw("objectId")));
        }
        /**
         * Identifier of the node.
         * @param nodeId field value; empty omits the value
         * @return this model
         */
        public GetContentQuadsRequest nodeId(Optional<DOM.NodeId> nodeId) {
            set("nodeId", nodeId.orElse(null));
            return this;
        }
        /**
         * Identifier of the node.
         * @param nodeId field value; null removes the value
         * @return this model
         */
        public GetContentQuadsRequest nodeId(DOM.NodeId nodeId) {
            set("nodeId", nodeId);
            return this;
        }
        /**
         * Identifier of the backend node.
         * @param backendNodeId field value; empty omits the value
         * @return this model
         */
        public GetContentQuadsRequest backendNodeId(Optional<DOM.BackendNodeId> backendNodeId) {
            set("backendNodeId", backendNodeId.orElse(null));
            return this;
        }
        /**
         * Identifier of the backend node.
         * @param backendNodeId field value; null removes the value
         * @return this model
         */
        public GetContentQuadsRequest backendNodeId(DOM.BackendNodeId backendNodeId) {
            set("backendNodeId", backendNodeId);
            return this;
        }
        /**
         * JavaScript object id of the node wrapper.
         * @param objectId field value; empty omits the value
         * @return this model
         */
        public GetContentQuadsRequest objectId(Optional<Runtime.RemoteObjectId> objectId) {
            set("objectId", objectId.orElse(null));
            return this;
        }
        /**
         * JavaScript object id of the node wrapper.
         * @param objectId field value; null removes the value
         * @return this model
         */
        public GetContentQuadsRequest objectId(Runtime.RemoteObjectId objectId) {
            set("objectId", objectId);
            return this;
        }
    }
    /**
     * Returns the root DOM node (and optionally the subtree) to the caller. Implicitly enables the DOM domain events for the current target.
     */
    public static final class GetDocumentRequest extends CdpObject {
        public GetDocumentRequest() {}
        public static GetDocumentRequest fromMap(Map<String, Object> values) {
            GetDocumentRequest instance_ = new GetDocumentRequest();
            if (values != null) instance_.values.putAll(values);
            return instance_;
        }
        /**
         * The maximum depth at which children should be retrieved, defaults to 1. Use -1 for the entire subtree or provide an integer larger than 0.
         * @return the protocol field value, empty when absent
         */
        public OptionalLong depth() {
            Long value = CdpObject.numberAsLong(raw("depth"));
            return value == null ? OptionalLong.empty() : OptionalLong.of(value);
        }
        /**
         * Whether or not iframes and shadow roots should be traversed when returning the subtree (default is false).
         * @return the protocol field value, empty when absent
         */
        public Optional<Boolean> pierce() {
            return Optional.ofNullable((Boolean) raw("pierce"));
        }
        /**
         * The maximum depth at which children should be retrieved, defaults to 1. Use -1 for the entire subtree or provide an integer larger than 0.
         * @param depth field value; empty omits the value
         * @return this model
         */
        public GetDocumentRequest depth(OptionalLong depth) {
            set("depth", depth.isPresent() ? depth.getAsLong() : null);
            return this;
        }
        /**
         * The maximum depth at which children should be retrieved, defaults to 1. Use -1 for the entire subtree or provide an integer larger than 0.
         * @param depth field value; null removes the value
         * @return this model
         */
        public GetDocumentRequest depth(Long depth) {
            set("depth", depth);
            return this;
        }
        /**
         * Whether or not iframes and shadow roots should be traversed when returning the subtree (default is false).
         * @param pierce field value; empty omits the value
         * @return this model
         */
        public GetDocumentRequest pierce(Optional<Boolean> pierce) {
            set("pierce", pierce.orElse(null));
            return this;
        }
        /**
         * Whether or not iframes and shadow roots should be traversed when returning the subtree (default is false).
         * @param pierce field value; null removes the value
         * @return this model
         */
        public GetDocumentRequest pierce(Boolean pierce) {
            set("pierce", pierce);
            return this;
        }
    }
    /**
     * Returns the root DOM node (and optionally the subtree) to the caller. Deprecated, as it is not designed to work well with the rest of the DOM agent. Use DOMSnapshot.captureSnapshot instead.
     * @deprecated Deprecated by the Chromium DevTools Protocol.
     */
    @Deprecated
    public static final class GetFlattenedDocumentRequest extends CdpObject {
        public GetFlattenedDocumentRequest() {}
        public static GetFlattenedDocumentRequest fromMap(Map<String, Object> values) {
            GetFlattenedDocumentRequest instance_ = new GetFlattenedDocumentRequest();
            if (values != null) instance_.values.putAll(values);
            return instance_;
        }
        /**
         * The maximum depth at which children should be retrieved, defaults to 1. Use -1 for the entire subtree or provide an integer larger than 0.
         * @return the protocol field value, empty when absent
         */
        public OptionalLong depth() {
            Long value = CdpObject.numberAsLong(raw("depth"));
            return value == null ? OptionalLong.empty() : OptionalLong.of(value);
        }
        /**
         * Whether or not iframes and shadow roots should be traversed when returning the subtree (default is false).
         * @return the protocol field value, empty when absent
         */
        public Optional<Boolean> pierce() {
            return Optional.ofNullable((Boolean) raw("pierce"));
        }
        /**
         * The maximum depth at which children should be retrieved, defaults to 1. Use -1 for the entire subtree or provide an integer larger than 0.
         * @param depth field value; empty omits the value
         * @return this model
         */
        public GetFlattenedDocumentRequest depth(OptionalLong depth) {
            set("depth", depth.isPresent() ? depth.getAsLong() : null);
            return this;
        }
        /**
         * The maximum depth at which children should be retrieved, defaults to 1. Use -1 for the entire subtree or provide an integer larger than 0.
         * @param depth field value; null removes the value
         * @return this model
         */
        public GetFlattenedDocumentRequest depth(Long depth) {
            set("depth", depth);
            return this;
        }
        /**
         * Whether or not iframes and shadow roots should be traversed when returning the subtree (default is false).
         * @param pierce field value; empty omits the value
         * @return this model
         */
        public GetFlattenedDocumentRequest pierce(Optional<Boolean> pierce) {
            set("pierce", pierce.orElse(null));
            return this;
        }
        /**
         * Whether or not iframes and shadow roots should be traversed when returning the subtree (default is false).
         * @param pierce field value; null removes the value
         * @return this model
         */
        public GetFlattenedDocumentRequest pierce(Boolean pierce) {
            set("pierce", pierce);
            return this;
        }
    }
    /**
     * Finds nodes with a given computed style in a subtree.
     * <p><b>Experimental:</b> this part of CDP may change without notice.
     */
    public static final class GetNodesForSubtreeByStyleRequest extends CdpObject {
        public GetNodesForSubtreeByStyleRequest() {}
        /**
         * Finds nodes with a given computed style in a subtree.
         * <p><b>Experimental:</b> this part of CDP may change without notice.
         * @param nodeId protocol value
         * @param computedStyles protocol value
         */
        public GetNodesForSubtreeByStyleRequest(DOM.NodeId nodeId, java.util.List<DOM.CSSComputedStyleProperty> computedStyles) {
            set("nodeId", nodeId);
            set("computedStyles", computedStyles);
        }
        public static GetNodesForSubtreeByStyleRequest fromMap(Map<String, Object> values) {
            GetNodesForSubtreeByStyleRequest instance_ = new GetNodesForSubtreeByStyleRequest();
            if (values != null) instance_.values.putAll(values);
            return instance_;
        }
        /**
         * Node ID pointing to the root of a subtree.
         * @return the protocol field value
         */
        public DOM.NodeId nodeId() {
            return new DOM.NodeId(((Number) require("nodeId")).longValue());
        }
        /**
         * The style to filter nodes by (includes nodes if any of properties matches).
         * @return the protocol field value
         */
        public java.util.List<DOM.CSSComputedStyleProperty> computedStyles() {
            return CdpObject.requireList(require("computedStyles"), element0 -> java.util.Objects.requireNonNull(DOM.CSSComputedStyleProperty.fromMap(java.util.Objects.requireNonNull(CdpObject.objectMap(element0)))));
        }
        /**
         * Whether or not iframes and shadow roots in the same target should be traversed when returning the results (default is false).
         * @return the protocol field value, empty when absent
         */
        public Optional<Boolean> pierce() {
            return Optional.ofNullable((Boolean) raw("pierce"));
        }
        /**
         * Node ID pointing to the root of a subtree.
         * @param nodeId field value
         * @return this model
         */
        public GetNodesForSubtreeByStyleRequest nodeId(DOM.NodeId nodeId) {
            set("nodeId", nodeId);
            return this;
        }
        /**
         * The style to filter nodes by (includes nodes if any of properties matches).
         * @param computedStyles field value
         * @return this model
         */
        public GetNodesForSubtreeByStyleRequest computedStyles(java.util.List<DOM.CSSComputedStyleProperty> computedStyles) {
            set("computedStyles", computedStyles);
            return this;
        }
        /**
         * Whether or not iframes and shadow roots in the same target should be traversed when returning the results (default is false).
         * @param pierce field value; empty omits the value
         * @return this model
         */
        public GetNodesForSubtreeByStyleRequest pierce(Optional<Boolean> pierce) {
            set("pierce", pierce.orElse(null));
            return this;
        }
        /**
         * Whether or not iframes and shadow roots in the same target should be traversed when returning the results (default is false).
         * @param pierce field value; null removes the value
         * @return this model
         */
        public GetNodesForSubtreeByStyleRequest pierce(Boolean pierce) {
            set("pierce", pierce);
            return this;
        }
    }
    /**
     * Returns node id at given location. Depending on whether DOM domain is enabled, nodeId is either returned or not.
     */
    public static final class GetNodeForLocationRequest extends CdpObject {
        public GetNodeForLocationRequest() {}
        /**
         * Returns node id at given location. Depending on whether DOM domain is enabled, nodeId is either returned or not.
         * @param x protocol value
         * @param y protocol value
         */
        public GetNodeForLocationRequest(long x, long y) {
            set("x", x);
            set("y", y);
        }
        public static GetNodeForLocationRequest fromMap(Map<String, Object> values) {
            GetNodeForLocationRequest instance_ = new GetNodeForLocationRequest();
            if (values != null) instance_.values.putAll(values);
            return instance_;
        }
        /**
         * X coordinate.
         * @return the protocol field value
         */
        public long x() {
            return ((Number) require("x")).longValue();
        }
        /**
         * Y coordinate.
         * @return the protocol field value
         */
        public long y() {
            return ((Number) require("y")).longValue();
        }
        /**
         * False to skip to the nearest non-UA shadow root ancestor (default: false).
         * @return the protocol field value, empty when absent
         */
        public Optional<Boolean> includeUserAgentShadowDOM() {
            return Optional.ofNullable((Boolean) raw("includeUserAgentShadowDOM"));
        }
        /**
         * Whether to ignore pointer-events: none on elements and hit test them.
         * @return the protocol field value, empty when absent
         */
        public Optional<Boolean> ignorePointerEventsNone() {
            return Optional.ofNullable((Boolean) raw("ignorePointerEventsNone"));
        }
        /**
         * X coordinate.
         * @param x field value
         * @return this model
         */
        public GetNodeForLocationRequest x(long x) {
            set("x", x);
            return this;
        }
        /**
         * Y coordinate.
         * @param y field value
         * @return this model
         */
        public GetNodeForLocationRequest y(long y) {
            set("y", y);
            return this;
        }
        /**
         * False to skip to the nearest non-UA shadow root ancestor (default: false).
         * @param includeUserAgentShadowDOM field value; empty omits the value
         * @return this model
         */
        public GetNodeForLocationRequest includeUserAgentShadowDOM(Optional<Boolean> includeUserAgentShadowDOM) {
            set("includeUserAgentShadowDOM", includeUserAgentShadowDOM.orElse(null));
            return this;
        }
        /**
         * False to skip to the nearest non-UA shadow root ancestor (default: false).
         * @param includeUserAgentShadowDOM field value; null removes the value
         * @return this model
         */
        public GetNodeForLocationRequest includeUserAgentShadowDOM(Boolean includeUserAgentShadowDOM) {
            set("includeUserAgentShadowDOM", includeUserAgentShadowDOM);
            return this;
        }
        /**
         * Whether to ignore pointer-events: none on elements and hit test them.
         * @param ignorePointerEventsNone field value; empty omits the value
         * @return this model
         */
        public GetNodeForLocationRequest ignorePointerEventsNone(Optional<Boolean> ignorePointerEventsNone) {
            set("ignorePointerEventsNone", ignorePointerEventsNone.orElse(null));
            return this;
        }
        /**
         * Whether to ignore pointer-events: none on elements and hit test them.
         * @param ignorePointerEventsNone field value; null removes the value
         * @return this model
         */
        public GetNodeForLocationRequest ignorePointerEventsNone(Boolean ignorePointerEventsNone) {
            set("ignorePointerEventsNone", ignorePointerEventsNone);
            return this;
        }
    }
    /**
     * Returns node&#x27;s HTML markup.
     */
    public static final class GetOuterHTMLRequest extends CdpObject {
        public GetOuterHTMLRequest() {}
        public static GetOuterHTMLRequest fromMap(Map<String, Object> values) {
            GetOuterHTMLRequest instance_ = new GetOuterHTMLRequest();
            if (values != null) instance_.values.putAll(values);
            return instance_;
        }
        /**
         * Identifier of the node.
         * @return the protocol field value, empty when absent
         */
        public Optional<DOM.NodeId> nodeId() {
            return Optional.ofNullable(raw("nodeId") == null ? null : new DOM.NodeId(((Number) raw("nodeId")).longValue()));
        }
        /**
         * Identifier of the backend node.
         * @return the protocol field value, empty when absent
         */
        public Optional<DOM.BackendNodeId> backendNodeId() {
            return Optional.ofNullable(raw("backendNodeId") == null ? null : new DOM.BackendNodeId(((Number) raw("backendNodeId")).longValue()));
        }
        /**
         * JavaScript object id of the node wrapper.
         * @return the protocol field value, empty when absent
         */
        public Optional<Runtime.RemoteObjectId> objectId() {
            return Optional.ofNullable(raw("objectId") == null ? null : new Runtime.RemoteObjectId((String) raw("objectId")));
        }
        /**
         * Include all shadow roots. Equals to false if not specified.
         * <p><b>Experimental:</b> this part of CDP may change without notice.
         * @return the protocol field value, empty when absent
         */
        public Optional<Boolean> includeShadowDOM() {
            return Optional.ofNullable((Boolean) raw("includeShadowDOM"));
        }
        /**
         * Identifier of the node.
         * @param nodeId field value; empty omits the value
         * @return this model
         */
        public GetOuterHTMLRequest nodeId(Optional<DOM.NodeId> nodeId) {
            set("nodeId", nodeId.orElse(null));
            return this;
        }
        /**
         * Identifier of the node.
         * @param nodeId field value; null removes the value
         * @return this model
         */
        public GetOuterHTMLRequest nodeId(DOM.NodeId nodeId) {
            set("nodeId", nodeId);
            return this;
        }
        /**
         * Identifier of the backend node.
         * @param backendNodeId field value; empty omits the value
         * @return this model
         */
        public GetOuterHTMLRequest backendNodeId(Optional<DOM.BackendNodeId> backendNodeId) {
            set("backendNodeId", backendNodeId.orElse(null));
            return this;
        }
        /**
         * Identifier of the backend node.
         * @param backendNodeId field value; null removes the value
         * @return this model
         */
        public GetOuterHTMLRequest backendNodeId(DOM.BackendNodeId backendNodeId) {
            set("backendNodeId", backendNodeId);
            return this;
        }
        /**
         * JavaScript object id of the node wrapper.
         * @param objectId field value; empty omits the value
         * @return this model
         */
        public GetOuterHTMLRequest objectId(Optional<Runtime.RemoteObjectId> objectId) {
            set("objectId", objectId.orElse(null));
            return this;
        }
        /**
         * JavaScript object id of the node wrapper.
         * @param objectId field value; null removes the value
         * @return this model
         */
        public GetOuterHTMLRequest objectId(Runtime.RemoteObjectId objectId) {
            set("objectId", objectId);
            return this;
        }
        /**
         * Include all shadow roots. Equals to false if not specified.
         * <p><b>Experimental:</b> this part of CDP may change without notice.
         * @param includeShadowDOM field value; empty omits the value
         * @return this model
         */
        public GetOuterHTMLRequest includeShadowDOM(Optional<Boolean> includeShadowDOM) {
            set("includeShadowDOM", includeShadowDOM.orElse(null));
            return this;
        }
        /**
         * Include all shadow roots. Equals to false if not specified.
         * <p><b>Experimental:</b> this part of CDP may change without notice.
         * @param includeShadowDOM field value; null removes the value
         * @return this model
         */
        public GetOuterHTMLRequest includeShadowDOM(Boolean includeShadowDOM) {
            set("includeShadowDOM", includeShadowDOM);
            return this;
        }
    }
    /**
     * Returns the id of the nearest ancestor that is a relayout boundary.
     * <p><b>Experimental:</b> this part of CDP may change without notice.
     */
    public static final class GetRelayoutBoundaryRequest extends CdpObject {
        public GetRelayoutBoundaryRequest() {}
        /**
         * Returns the id of the nearest ancestor that is a relayout boundary.
         * <p><b>Experimental:</b> this part of CDP may change without notice.
         * @param nodeId protocol value
         */
        public GetRelayoutBoundaryRequest(DOM.NodeId nodeId) {
            set("nodeId", nodeId);
        }
        public static GetRelayoutBoundaryRequest fromMap(Map<String, Object> values) {
            GetRelayoutBoundaryRequest instance_ = new GetRelayoutBoundaryRequest();
            if (values != null) instance_.values.putAll(values);
            return instance_;
        }
        /**
         * Id of the node.
         * @return the protocol field value
         */
        public DOM.NodeId nodeId() {
            return new DOM.NodeId(((Number) require("nodeId")).longValue());
        }
        /**
         * Id of the node.
         * @param nodeId field value
         * @return this model
         */
        public GetRelayoutBoundaryRequest nodeId(DOM.NodeId nodeId) {
            set("nodeId", nodeId);
            return this;
        }
    }
    /**
     * Returns search results from given {@code fromIndex} to given {@code toIndex} from the search with the given identifier.
     * <p><b>Experimental:</b> this part of CDP may change without notice.
     */
    public static final class GetSearchResultsRequest extends CdpObject {
        public GetSearchResultsRequest() {}
        /**
         * Returns search results from given {@code fromIndex} to given {@code toIndex} from the search with the given identifier.
         * <p><b>Experimental:</b> this part of CDP may change without notice.
         * @param searchId protocol value
         * @param fromIndex protocol value
         * @param toIndex protocol value
         */
        public GetSearchResultsRequest(String searchId, long fromIndex, long toIndex) {
            set("searchId", searchId);
            set("fromIndex", fromIndex);
            set("toIndex", toIndex);
        }
        public static GetSearchResultsRequest fromMap(Map<String, Object> values) {
            GetSearchResultsRequest instance_ = new GetSearchResultsRequest();
            if (values != null) instance_.values.putAll(values);
            return instance_;
        }
        /**
         * Unique search session identifier.
         * @return the protocol field value
         */
        public String searchId() {
            return (String) require("searchId");
        }
        /**
         * Start index of the search result to be returned.
         * @return the protocol field value
         */
        public long fromIndex() {
            return ((Number) require("fromIndex")).longValue();
        }
        /**
         * End index of the search result to be returned.
         * @return the protocol field value
         */
        public long toIndex() {
            return ((Number) require("toIndex")).longValue();
        }
        /**
         * Unique search session identifier.
         * @param searchId field value
         * @return this model
         */
        public GetSearchResultsRequest searchId(String searchId) {
            set("searchId", searchId);
            return this;
        }
        /**
         * Start index of the search result to be returned.
         * @param fromIndex field value
         * @return this model
         */
        public GetSearchResultsRequest fromIndex(long fromIndex) {
            set("fromIndex", fromIndex);
            return this;
        }
        /**
         * End index of the search result to be returned.
         * @param toIndex field value
         * @return this model
         */
        public GetSearchResultsRequest toIndex(long toIndex) {
            set("toIndex", toIndex);
            return this;
        }
    }
    /**
     * Moves node into the new container, places it before the given anchor.
     */
    public static final class MoveToRequest extends CdpObject {
        public MoveToRequest() {}
        /**
         * Moves node into the new container, places it before the given anchor.
         * @param nodeId protocol value
         * @param targetNodeId protocol value
         */
        public MoveToRequest(DOM.NodeId nodeId, DOM.NodeId targetNodeId) {
            set("nodeId", nodeId);
            set("targetNodeId", targetNodeId);
        }
        public static MoveToRequest fromMap(Map<String, Object> values) {
            MoveToRequest instance_ = new MoveToRequest();
            if (values != null) instance_.values.putAll(values);
            return instance_;
        }
        /**
         * Id of the node to move.
         * @return the protocol field value
         */
        public DOM.NodeId nodeId() {
            return new DOM.NodeId(((Number) require("nodeId")).longValue());
        }
        /**
         * Id of the element to drop the moved node into.
         * @return the protocol field value
         */
        public DOM.NodeId targetNodeId() {
            return new DOM.NodeId(((Number) require("targetNodeId")).longValue());
        }
        /**
         * Drop node before this one (if absent, the moved node becomes the last child of {@code targetNodeId}).
         * @return the protocol field value, empty when absent
         */
        public Optional<DOM.NodeId> insertBeforeNodeId() {
            return Optional.ofNullable(raw("insertBeforeNodeId") == null ? null : new DOM.NodeId(((Number) raw("insertBeforeNodeId")).longValue()));
        }
        /**
         * Id of the node to move.
         * @param nodeId field value
         * @return this model
         */
        public MoveToRequest nodeId(DOM.NodeId nodeId) {
            set("nodeId", nodeId);
            return this;
        }
        /**
         * Id of the element to drop the moved node into.
         * @param targetNodeId field value
         * @return this model
         */
        public MoveToRequest targetNodeId(DOM.NodeId targetNodeId) {
            set("targetNodeId", targetNodeId);
            return this;
        }
        /**
         * Drop node before this one (if absent, the moved node becomes the last child of {@code targetNodeId}).
         * @param insertBeforeNodeId field value; empty omits the value
         * @return this model
         */
        public MoveToRequest insertBeforeNodeId(Optional<DOM.NodeId> insertBeforeNodeId) {
            set("insertBeforeNodeId", insertBeforeNodeId.orElse(null));
            return this;
        }
        /**
         * Drop node before this one (if absent, the moved node becomes the last child of {@code targetNodeId}).
         * @param insertBeforeNodeId field value; null removes the value
         * @return this model
         */
        public MoveToRequest insertBeforeNodeId(DOM.NodeId insertBeforeNodeId) {
            set("insertBeforeNodeId", insertBeforeNodeId);
            return this;
        }
    }
    /**
     * Searches for a given string in the DOM tree. Use {@code getSearchResults} to access search results or {@code cancelSearch} to end this search session.
     * <p><b>Experimental:</b> this part of CDP may change without notice.
     */
    public static final class PerformSearchRequest extends CdpObject {
        public PerformSearchRequest() {}
        /**
         * Searches for a given string in the DOM tree. Use {@code getSearchResults} to access search results or {@code cancelSearch} to end this search session.
         * <p><b>Experimental:</b> this part of CDP may change without notice.
         * @param query protocol value
         */
        public PerformSearchRequest(String query) {
            set("query", query);
        }
        public static PerformSearchRequest fromMap(Map<String, Object> values) {
            PerformSearchRequest instance_ = new PerformSearchRequest();
            if (values != null) instance_.values.putAll(values);
            return instance_;
        }
        /**
         * Plain text or query selector or XPath search query.
         * @return the protocol field value
         */
        public String query() {
            return (String) require("query");
        }
        /**
         * True to search in user agent shadow DOM.
         * @return the protocol field value, empty when absent
         */
        public Optional<Boolean> includeUserAgentShadowDOM() {
            return Optional.ofNullable((Boolean) raw("includeUserAgentShadowDOM"));
        }
        /**
         * Plain text or query selector or XPath search query.
         * @param query field value
         * @return this model
         */
        public PerformSearchRequest query(String query) {
            set("query", query);
            return this;
        }
        /**
         * True to search in user agent shadow DOM.
         * @param includeUserAgentShadowDOM field value; empty omits the value
         * @return this model
         */
        public PerformSearchRequest includeUserAgentShadowDOM(Optional<Boolean> includeUserAgentShadowDOM) {
            set("includeUserAgentShadowDOM", includeUserAgentShadowDOM.orElse(null));
            return this;
        }
        /**
         * True to search in user agent shadow DOM.
         * @param includeUserAgentShadowDOM field value; null removes the value
         * @return this model
         */
        public PerformSearchRequest includeUserAgentShadowDOM(Boolean includeUserAgentShadowDOM) {
            set("includeUserAgentShadowDOM", includeUserAgentShadowDOM);
            return this;
        }
    }
    /**
     * Requests that the node is sent to the caller given its path. // FIXME, use XPath
     * <p><b>Experimental:</b> this part of CDP may change without notice.
     */
    public static final class PushNodeByPathToFrontendRequest extends CdpObject {
        public PushNodeByPathToFrontendRequest() {}
        /**
         * Requests that the node is sent to the caller given its path. // FIXME, use XPath
         * <p><b>Experimental:</b> this part of CDP may change without notice.
         * @param path protocol value
         */
        public PushNodeByPathToFrontendRequest(String path) {
            set("path", path);
        }
        public static PushNodeByPathToFrontendRequest fromMap(Map<String, Object> values) {
            PushNodeByPathToFrontendRequest instance_ = new PushNodeByPathToFrontendRequest();
            if (values != null) instance_.values.putAll(values);
            return instance_;
        }
        /**
         * Path to node in the proprietary format.
         * @return the protocol field value
         */
        public String path() {
            return (String) require("path");
        }
        /**
         * Path to node in the proprietary format.
         * @param path field value
         * @return this model
         */
        public PushNodeByPathToFrontendRequest path(String path) {
            set("path", path);
            return this;
        }
    }
    /**
     * Requests that a batch of nodes is sent to the caller given their backend node ids.
     * <p><b>Experimental:</b> this part of CDP may change without notice.
     */
    public static final class PushNodesByBackendIdsToFrontendRequest extends CdpObject {
        public PushNodesByBackendIdsToFrontendRequest() {}
        /**
         * Requests that a batch of nodes is sent to the caller given their backend node ids.
         * <p><b>Experimental:</b> this part of CDP may change without notice.
         * @param backendNodeIds protocol value
         */
        public PushNodesByBackendIdsToFrontendRequest(java.util.List<DOM.BackendNodeId> backendNodeIds) {
            set("backendNodeIds", backendNodeIds);
        }
        public static PushNodesByBackendIdsToFrontendRequest fromMap(Map<String, Object> values) {
            PushNodesByBackendIdsToFrontendRequest instance_ = new PushNodesByBackendIdsToFrontendRequest();
            if (values != null) instance_.values.putAll(values);
            return instance_;
        }
        /**
         * The array of backend node ids.
         * @return the protocol field value
         */
        public java.util.List<DOM.BackendNodeId> backendNodeIds() {
            return CdpObject.requireList(require("backendNodeIds"), element0 -> new DOM.BackendNodeId(((Number) element0).longValue()));
        }
        /**
         * The array of backend node ids.
         * @param backendNodeIds field value
         * @return this model
         */
        public PushNodesByBackendIdsToFrontendRequest backendNodeIds(java.util.List<DOM.BackendNodeId> backendNodeIds) {
            set("backendNodeIds", backendNodeIds);
            return this;
        }
    }
    /**
     * Executes {@code querySelector} on a given node.
     */
    public static final class QuerySelectorRequest extends CdpObject {
        public QuerySelectorRequest() {}
        /**
         * Executes {@code querySelector} on a given node.
         * @param nodeId protocol value
         * @param selector protocol value
         */
        public QuerySelectorRequest(DOM.NodeId nodeId, String selector) {
            set("nodeId", nodeId);
            set("selector", selector);
        }
        public static QuerySelectorRequest fromMap(Map<String, Object> values) {
            QuerySelectorRequest instance_ = new QuerySelectorRequest();
            if (values != null) instance_.values.putAll(values);
            return instance_;
        }
        /**
         * Id of the node to query upon.
         * @return the protocol field value
         */
        public DOM.NodeId nodeId() {
            return new DOM.NodeId(((Number) require("nodeId")).longValue());
        }
        /**
         * Selector string.
         * @return the protocol field value
         */
        public String selector() {
            return (String) require("selector");
        }
        /**
         * Id of the node to query upon.
         * @param nodeId field value
         * @return this model
         */
        public QuerySelectorRequest nodeId(DOM.NodeId nodeId) {
            set("nodeId", nodeId);
            return this;
        }
        /**
         * Selector string.
         * @param selector field value
         * @return this model
         */
        public QuerySelectorRequest selector(String selector) {
            set("selector", selector);
            return this;
        }
    }
    /**
     * Executes {@code querySelectorAll} on a given node.
     */
    public static final class QuerySelectorAllRequest extends CdpObject {
        public QuerySelectorAllRequest() {}
        /**
         * Executes {@code querySelectorAll} on a given node.
         * @param nodeId protocol value
         * @param selector protocol value
         */
        public QuerySelectorAllRequest(DOM.NodeId nodeId, String selector) {
            set("nodeId", nodeId);
            set("selector", selector);
        }
        public static QuerySelectorAllRequest fromMap(Map<String, Object> values) {
            QuerySelectorAllRequest instance_ = new QuerySelectorAllRequest();
            if (values != null) instance_.values.putAll(values);
            return instance_;
        }
        /**
         * Id of the node to query upon.
         * @return the protocol field value
         */
        public DOM.NodeId nodeId() {
            return new DOM.NodeId(((Number) require("nodeId")).longValue());
        }
        /**
         * Selector string.
         * @return the protocol field value
         */
        public String selector() {
            return (String) require("selector");
        }
        /**
         * Id of the node to query upon.
         * @param nodeId field value
         * @return this model
         */
        public QuerySelectorAllRequest nodeId(DOM.NodeId nodeId) {
            set("nodeId", nodeId);
            return this;
        }
        /**
         * Selector string.
         * @param selector field value
         * @return this model
         */
        public QuerySelectorAllRequest selector(String selector) {
            set("selector", selector);
            return this;
        }
    }
    /**
     * Returns the NodeId of the matched element according to certain relations.
     * <p><b>Experimental:</b> this part of CDP may change without notice.
     */
    public static final class GetElementByRelationRequest extends CdpObject {
        public GetElementByRelationRequest() {}
        /**
         * Returns the NodeId of the matched element according to certain relations.
         * <p><b>Experimental:</b> this part of CDP may change without notice.
         * @param nodeId protocol value
         * @param relation protocol value
         */
        public GetElementByRelationRequest(DOM.NodeId nodeId, GetElementByRelationRelationValues relation) {
            set("nodeId", nodeId);
            set("relation", relation);
        }
        public static GetElementByRelationRequest fromMap(Map<String, Object> values) {
            GetElementByRelationRequest instance_ = new GetElementByRelationRequest();
            if (values != null) instance_.values.putAll(values);
            return instance_;
        }
        /**
         * Id of the node from which to query the relation.
         * @return the protocol field value
         */
        public DOM.NodeId nodeId() {
            return new DOM.NodeId(((Number) require("nodeId")).longValue());
        }
        /**
         * Type of relation to get.
         * @return the protocol field value
         */
        public GetElementByRelationRelationValues relation() {
            return GetElementByRelationRelationValues.of((String) require("relation"));
        }
        /**
         * Id of the node from which to query the relation.
         * @param nodeId field value
         * @return this model
         */
        public GetElementByRelationRequest nodeId(DOM.NodeId nodeId) {
            set("nodeId", nodeId);
            return this;
        }
        /**
         * Type of relation to get.
         * @param relation field value
         * @return this model
         */
        public GetElementByRelationRequest relation(GetElementByRelationRelationValues relation) {
            set("relation", relation);
            return this;
        }
    }
    /**
     * Removes attribute with given name from an element with given id.
     */
    public static final class RemoveAttributeRequest extends CdpObject {
        public RemoveAttributeRequest() {}
        /**
         * Removes attribute with given name from an element with given id.
         * @param nodeId protocol value
         * @param name protocol value
         */
        public RemoveAttributeRequest(DOM.NodeId nodeId, String name) {
            set("nodeId", nodeId);
            set("name", name);
        }
        public static RemoveAttributeRequest fromMap(Map<String, Object> values) {
            RemoveAttributeRequest instance_ = new RemoveAttributeRequest();
            if (values != null) instance_.values.putAll(values);
            return instance_;
        }
        /**
         * Id of the element to remove attribute from.
         * @return the protocol field value
         */
        public DOM.NodeId nodeId() {
            return new DOM.NodeId(((Number) require("nodeId")).longValue());
        }
        /**
         * Name of the attribute to remove.
         * @return the protocol field value
         */
        public String name() {
            return (String) require("name");
        }
        /**
         * Id of the element to remove attribute from.
         * @param nodeId field value
         * @return this model
         */
        public RemoveAttributeRequest nodeId(DOM.NodeId nodeId) {
            set("nodeId", nodeId);
            return this;
        }
        /**
         * Name of the attribute to remove.
         * @param name field value
         * @return this model
         */
        public RemoveAttributeRequest name(String name) {
            set("name", name);
            return this;
        }
    }
    /**
     * Removes node with given id.
     */
    public static final class RemoveNodeRequest extends CdpObject {
        public RemoveNodeRequest() {}
        /**
         * Removes node with given id.
         * @param nodeId protocol value
         */
        public RemoveNodeRequest(DOM.NodeId nodeId) {
            set("nodeId", nodeId);
        }
        public static RemoveNodeRequest fromMap(Map<String, Object> values) {
            RemoveNodeRequest instance_ = new RemoveNodeRequest();
            if (values != null) instance_.values.putAll(values);
            return instance_;
        }
        /**
         * Id of the node to remove.
         * @return the protocol field value
         */
        public DOM.NodeId nodeId() {
            return new DOM.NodeId(((Number) require("nodeId")).longValue());
        }
        /**
         * Id of the node to remove.
         * @param nodeId field value
         * @return this model
         */
        public RemoveNodeRequest nodeId(DOM.NodeId nodeId) {
            set("nodeId", nodeId);
            return this;
        }
    }
    /**
     * Requests that children of the node with given id are returned to the caller in form of {@code setChildNodes} events where not only immediate children are retrieved, but all children down to the specified depth.
     */
    public static final class RequestChildNodesRequest extends CdpObject {
        public RequestChildNodesRequest() {}
        /**
         * Requests that children of the node with given id are returned to the caller in form of {@code setChildNodes} events where not only immediate children are retrieved, but all children down to the specified depth.
         * @param nodeId protocol value
         */
        public RequestChildNodesRequest(DOM.NodeId nodeId) {
            set("nodeId", nodeId);
        }
        public static RequestChildNodesRequest fromMap(Map<String, Object> values) {
            RequestChildNodesRequest instance_ = new RequestChildNodesRequest();
            if (values != null) instance_.values.putAll(values);
            return instance_;
        }
        /**
         * Id of the node to get children for.
         * @return the protocol field value
         */
        public DOM.NodeId nodeId() {
            return new DOM.NodeId(((Number) require("nodeId")).longValue());
        }
        /**
         * The maximum depth at which children should be retrieved, defaults to 1. Use -1 for the entire subtree or provide an integer larger than 0.
         * @return the protocol field value, empty when absent
         */
        public OptionalLong depth() {
            Long value = CdpObject.numberAsLong(raw("depth"));
            return value == null ? OptionalLong.empty() : OptionalLong.of(value);
        }
        /**
         * Whether or not iframes and shadow roots should be traversed when returning the sub-tree (default is false).
         * @return the protocol field value, empty when absent
         */
        public Optional<Boolean> pierce() {
            return Optional.ofNullable((Boolean) raw("pierce"));
        }
        /**
         * Id of the node to get children for.
         * @param nodeId field value
         * @return this model
         */
        public RequestChildNodesRequest nodeId(DOM.NodeId nodeId) {
            set("nodeId", nodeId);
            return this;
        }
        /**
         * The maximum depth at which children should be retrieved, defaults to 1. Use -1 for the entire subtree or provide an integer larger than 0.
         * @param depth field value; empty omits the value
         * @return this model
         */
        public RequestChildNodesRequest depth(OptionalLong depth) {
            set("depth", depth.isPresent() ? depth.getAsLong() : null);
            return this;
        }
        /**
         * The maximum depth at which children should be retrieved, defaults to 1. Use -1 for the entire subtree or provide an integer larger than 0.
         * @param depth field value; null removes the value
         * @return this model
         */
        public RequestChildNodesRequest depth(Long depth) {
            set("depth", depth);
            return this;
        }
        /**
         * Whether or not iframes and shadow roots should be traversed when returning the sub-tree (default is false).
         * @param pierce field value; empty omits the value
         * @return this model
         */
        public RequestChildNodesRequest pierce(Optional<Boolean> pierce) {
            set("pierce", pierce.orElse(null));
            return this;
        }
        /**
         * Whether or not iframes and shadow roots should be traversed when returning the sub-tree (default is false).
         * @param pierce field value; null removes the value
         * @return this model
         */
        public RequestChildNodesRequest pierce(Boolean pierce) {
            set("pierce", pierce);
            return this;
        }
    }
    /**
     * Requests that the node is sent to the caller given the JavaScript node object reference. All nodes that form the path from the node to the root are also sent to the client as a series of {@code setChildNodes} notifications.
     */
    public static final class RequestNodeRequest extends CdpObject {
        public RequestNodeRequest() {}
        /**
         * Requests that the node is sent to the caller given the JavaScript node object reference. All nodes that form the path from the node to the root are also sent to the client as a series of {@code setChildNodes} notifications.
         * @param objectId protocol value
         */
        public RequestNodeRequest(Runtime.RemoteObjectId objectId) {
            set("objectId", objectId);
        }
        public static RequestNodeRequest fromMap(Map<String, Object> values) {
            RequestNodeRequest instance_ = new RequestNodeRequest();
            if (values != null) instance_.values.putAll(values);
            return instance_;
        }
        /**
         * JavaScript object id to convert into node.
         * @return the protocol field value
         */
        public Runtime.RemoteObjectId objectId() {
            return new Runtime.RemoteObjectId((String) require("objectId"));
        }
        /**
         * JavaScript object id to convert into node.
         * @param objectId field value
         * @return this model
         */
        public RequestNodeRequest objectId(Runtime.RemoteObjectId objectId) {
            set("objectId", objectId);
            return this;
        }
    }
    /**
     * Resolves the JavaScript node object for a given NodeId or BackendNodeId.
     */
    public static final class ResolveNodeRequest extends CdpObject {
        public ResolveNodeRequest() {}
        public static ResolveNodeRequest fromMap(Map<String, Object> values) {
            ResolveNodeRequest instance_ = new ResolveNodeRequest();
            if (values != null) instance_.values.putAll(values);
            return instance_;
        }
        /**
         * Id of the node to resolve.
         * @return the protocol field value, empty when absent
         */
        public Optional<DOM.NodeId> nodeId() {
            return Optional.ofNullable(raw("nodeId") == null ? null : new DOM.NodeId(((Number) raw("nodeId")).longValue()));
        }
        /**
         * Backend identifier of the node to resolve.
         * @return the protocol field value, empty when absent
         */
        public Optional<DOM.BackendNodeId> backendNodeId() {
            return Optional.ofNullable(raw("backendNodeId") == null ? null : new DOM.BackendNodeId(((Number) raw("backendNodeId")).longValue()));
        }
        /**
         * Symbolic group name that can be used to release multiple objects.
         * @return the protocol field value, empty when absent
         */
        public Optional<String> objectGroup() {
            return Optional.ofNullable((String) raw("objectGroup"));
        }
        /**
         * Execution context in which to resolve the node.
         * @return the protocol field value, empty when absent
         */
        public Optional<Runtime.ExecutionContextId> executionContextId() {
            return Optional.ofNullable(raw("executionContextId") == null ? null : new Runtime.ExecutionContextId(((Number) raw("executionContextId")).longValue()));
        }
        /**
         * Id of the node to resolve.
         * @param nodeId field value; empty omits the value
         * @return this model
         */
        public ResolveNodeRequest nodeId(Optional<DOM.NodeId> nodeId) {
            set("nodeId", nodeId.orElse(null));
            return this;
        }
        /**
         * Id of the node to resolve.
         * @param nodeId field value; null removes the value
         * @return this model
         */
        public ResolveNodeRequest nodeId(DOM.NodeId nodeId) {
            set("nodeId", nodeId);
            return this;
        }
        /**
         * Backend identifier of the node to resolve.
         * @param backendNodeId field value; empty omits the value
         * @return this model
         */
        public ResolveNodeRequest backendNodeId(Optional<DOM.BackendNodeId> backendNodeId) {
            set("backendNodeId", backendNodeId.orElse(null));
            return this;
        }
        /**
         * Backend identifier of the node to resolve.
         * @param backendNodeId field value; null removes the value
         * @return this model
         */
        public ResolveNodeRequest backendNodeId(DOM.BackendNodeId backendNodeId) {
            set("backendNodeId", backendNodeId);
            return this;
        }
        /**
         * Symbolic group name that can be used to release multiple objects.
         * @param objectGroup field value; empty omits the value
         * @return this model
         */
        public ResolveNodeRequest objectGroup(Optional<String> objectGroup) {
            set("objectGroup", objectGroup.orElse(null));
            return this;
        }
        /**
         * Symbolic group name that can be used to release multiple objects.
         * @param objectGroup field value; null removes the value
         * @return this model
         */
        public ResolveNodeRequest objectGroup(String objectGroup) {
            set("objectGroup", objectGroup);
            return this;
        }
        /**
         * Execution context in which to resolve the node.
         * @param executionContextId field value; empty omits the value
         * @return this model
         */
        public ResolveNodeRequest executionContextId(Optional<Runtime.ExecutionContextId> executionContextId) {
            set("executionContextId", executionContextId.orElse(null));
            return this;
        }
        /**
         * Execution context in which to resolve the node.
         * @param executionContextId field value; null removes the value
         * @return this model
         */
        public ResolveNodeRequest executionContextId(Runtime.ExecutionContextId executionContextId) {
            set("executionContextId", executionContextId);
            return this;
        }
    }
    /**
     * Sets attribute for an element with given id.
     */
    public static final class SetAttributeValueRequest extends CdpObject {
        public SetAttributeValueRequest() {}
        /**
         * Sets attribute for an element with given id.
         * @param nodeId protocol value
         * @param name protocol value
         * @param value protocol value
         */
        public SetAttributeValueRequest(DOM.NodeId nodeId, String name, String value) {
            set("nodeId", nodeId);
            set("name", name);
            set("value", value);
        }
        public static SetAttributeValueRequest fromMap(Map<String, Object> values) {
            SetAttributeValueRequest instance_ = new SetAttributeValueRequest();
            if (values != null) instance_.values.putAll(values);
            return instance_;
        }
        /**
         * Id of the element to set attribute for.
         * @return the protocol field value
         */
        public DOM.NodeId nodeId() {
            return new DOM.NodeId(((Number) require("nodeId")).longValue());
        }
        /**
         * Attribute name.
         * @return the protocol field value
         */
        public String name() {
            return (String) require("name");
        }
        /**
         * Attribute value.
         * @return the protocol field value
         */
        public String value() {
            return (String) require("value");
        }
        /**
         * Id of the element to set attribute for.
         * @param nodeId field value
         * @return this model
         */
        public SetAttributeValueRequest nodeId(DOM.NodeId nodeId) {
            set("nodeId", nodeId);
            return this;
        }
        /**
         * Attribute name.
         * @param name field value
         * @return this model
         */
        public SetAttributeValueRequest name(String name) {
            set("name", name);
            return this;
        }
        /**
         * Attribute value.
         * @param value field value
         * @return this model
         */
        public SetAttributeValueRequest value(String value) {
            set("value", value);
            return this;
        }
    }
    /**
     * Sets attributes on element with given id. This method is useful when user edits some existing attribute value and types in several attribute name/value pairs.
     */
    public static final class SetAttributesAsTextRequest extends CdpObject {
        public SetAttributesAsTextRequest() {}
        /**
         * Sets attributes on element with given id. This method is useful when user edits some existing attribute value and types in several attribute name/value pairs.
         * @param nodeId protocol value
         * @param text protocol value
         */
        public SetAttributesAsTextRequest(DOM.NodeId nodeId, String text) {
            set("nodeId", nodeId);
            set("text", text);
        }
        public static SetAttributesAsTextRequest fromMap(Map<String, Object> values) {
            SetAttributesAsTextRequest instance_ = new SetAttributesAsTextRequest();
            if (values != null) instance_.values.putAll(values);
            return instance_;
        }
        /**
         * Id of the element to set attributes for.
         * @return the protocol field value
         */
        public DOM.NodeId nodeId() {
            return new DOM.NodeId(((Number) require("nodeId")).longValue());
        }
        /**
         * Text with a number of attributes. Will parse this text using HTML parser.
         * @return the protocol field value
         */
        public String text() {
            return (String) require("text");
        }
        /**
         * Attribute name to replace with new attributes derived from text in case text parsed successfully.
         * @return the protocol field value, empty when absent
         */
        public Optional<String> name() {
            return Optional.ofNullable((String) raw("name"));
        }
        /**
         * Id of the element to set attributes for.
         * @param nodeId field value
         * @return this model
         */
        public SetAttributesAsTextRequest nodeId(DOM.NodeId nodeId) {
            set("nodeId", nodeId);
            return this;
        }
        /**
         * Text with a number of attributes. Will parse this text using HTML parser.
         * @param text field value
         * @return this model
         */
        public SetAttributesAsTextRequest text(String text) {
            set("text", text);
            return this;
        }
        /**
         * Attribute name to replace with new attributes derived from text in case text parsed successfully.
         * @param name field value; empty omits the value
         * @return this model
         */
        public SetAttributesAsTextRequest name(Optional<String> name) {
            set("name", name.orElse(null));
            return this;
        }
        /**
         * Attribute name to replace with new attributes derived from text in case text parsed successfully.
         * @param name field value; null removes the value
         * @return this model
         */
        public SetAttributesAsTextRequest name(String name) {
            set("name", name);
            return this;
        }
    }
    /**
     * Sets files for the given file input element.
     */
    public static final class SetFileInputFilesRequest extends CdpObject {
        public SetFileInputFilesRequest() {}
        /**
         * Sets files for the given file input element.
         * @param files protocol value
         */
        public SetFileInputFilesRequest(java.util.List<String> files) {
            set("files", files);
        }
        public static SetFileInputFilesRequest fromMap(Map<String, Object> values) {
            SetFileInputFilesRequest instance_ = new SetFileInputFilesRequest();
            if (values != null) instance_.values.putAll(values);
            return instance_;
        }
        /**
         * Array of file paths to set.
         * @return the protocol field value
         */
        public java.util.List<String> files() {
            return CdpObject.requireList(require("files"), element0 -> (String) element0);
        }
        /**
         * Identifier of the node.
         * @return the protocol field value, empty when absent
         */
        public Optional<DOM.NodeId> nodeId() {
            return Optional.ofNullable(raw("nodeId") == null ? null : new DOM.NodeId(((Number) raw("nodeId")).longValue()));
        }
        /**
         * Identifier of the backend node.
         * @return the protocol field value, empty when absent
         */
        public Optional<DOM.BackendNodeId> backendNodeId() {
            return Optional.ofNullable(raw("backendNodeId") == null ? null : new DOM.BackendNodeId(((Number) raw("backendNodeId")).longValue()));
        }
        /**
         * JavaScript object id of the node wrapper.
         * @return the protocol field value, empty when absent
         */
        public Optional<Runtime.RemoteObjectId> objectId() {
            return Optional.ofNullable(raw("objectId") == null ? null : new Runtime.RemoteObjectId((String) raw("objectId")));
        }
        /**
         * Array of file paths to set.
         * @param files field value
         * @return this model
         */
        public SetFileInputFilesRequest files(java.util.List<String> files) {
            set("files", files);
            return this;
        }
        /**
         * Identifier of the node.
         * @param nodeId field value; empty omits the value
         * @return this model
         */
        public SetFileInputFilesRequest nodeId(Optional<DOM.NodeId> nodeId) {
            set("nodeId", nodeId.orElse(null));
            return this;
        }
        /**
         * Identifier of the node.
         * @param nodeId field value; null removes the value
         * @return this model
         */
        public SetFileInputFilesRequest nodeId(DOM.NodeId nodeId) {
            set("nodeId", nodeId);
            return this;
        }
        /**
         * Identifier of the backend node.
         * @param backendNodeId field value; empty omits the value
         * @return this model
         */
        public SetFileInputFilesRequest backendNodeId(Optional<DOM.BackendNodeId> backendNodeId) {
            set("backendNodeId", backendNodeId.orElse(null));
            return this;
        }
        /**
         * Identifier of the backend node.
         * @param backendNodeId field value; null removes the value
         * @return this model
         */
        public SetFileInputFilesRequest backendNodeId(DOM.BackendNodeId backendNodeId) {
            set("backendNodeId", backendNodeId);
            return this;
        }
        /**
         * JavaScript object id of the node wrapper.
         * @param objectId field value; empty omits the value
         * @return this model
         */
        public SetFileInputFilesRequest objectId(Optional<Runtime.RemoteObjectId> objectId) {
            set("objectId", objectId.orElse(null));
            return this;
        }
        /**
         * JavaScript object id of the node wrapper.
         * @param objectId field value; null removes the value
         * @return this model
         */
        public SetFileInputFilesRequest objectId(Runtime.RemoteObjectId objectId) {
            set("objectId", objectId);
            return this;
        }
    }
    /**
     * Sets if stack traces should be captured for Nodes. See {@code Node.getNodeStackTraces}. Default is disabled.
     * <p><b>Experimental:</b> this part of CDP may change without notice.
     */
    public static final class SetNodeStackTracesEnabledRequest extends CdpObject {
        public SetNodeStackTracesEnabledRequest() {}
        /**
         * Sets if stack traces should be captured for Nodes. See {@code Node.getNodeStackTraces}. Default is disabled.
         * <p><b>Experimental:</b> this part of CDP may change without notice.
         * @param enable protocol value
         */
        public SetNodeStackTracesEnabledRequest(boolean enable) {
            set("enable", enable);
        }
        public static SetNodeStackTracesEnabledRequest fromMap(Map<String, Object> values) {
            SetNodeStackTracesEnabledRequest instance_ = new SetNodeStackTracesEnabledRequest();
            if (values != null) instance_.values.putAll(values);
            return instance_;
        }
        /**
         * Enable or disable.
         * @return the protocol field value
         */
        public boolean enable() {
            return (Boolean) require("enable");
        }
        /**
         * Enable or disable.
         * @param enable field value
         * @return this model
         */
        public SetNodeStackTracesEnabledRequest enable(boolean enable) {
            set("enable", enable);
            return this;
        }
    }
    /**
     * Gets stack traces associated with a Node. As of now, only provides stack trace for Node creation.
     * <p><b>Experimental:</b> this part of CDP may change without notice.
     */
    public static final class GetNodeStackTracesRequest extends CdpObject {
        public GetNodeStackTracesRequest() {}
        /**
         * Gets stack traces associated with a Node. As of now, only provides stack trace for Node creation.
         * <p><b>Experimental:</b> this part of CDP may change without notice.
         * @param nodeId protocol value
         */
        public GetNodeStackTracesRequest(DOM.NodeId nodeId) {
            set("nodeId", nodeId);
        }
        public static GetNodeStackTracesRequest fromMap(Map<String, Object> values) {
            GetNodeStackTracesRequest instance_ = new GetNodeStackTracesRequest();
            if (values != null) instance_.values.putAll(values);
            return instance_;
        }
        /**
         * Id of the node to get stack traces for.
         * @return the protocol field value
         */
        public DOM.NodeId nodeId() {
            return new DOM.NodeId(((Number) require("nodeId")).longValue());
        }
        /**
         * Id of the node to get stack traces for.
         * @param nodeId field value
         * @return this model
         */
        public GetNodeStackTracesRequest nodeId(DOM.NodeId nodeId) {
            set("nodeId", nodeId);
            return this;
        }
    }
    /**
     * Returns file information for the given File wrapper.
     * <p><b>Experimental:</b> this part of CDP may change without notice.
     */
    public static final class GetFileInfoRequest extends CdpObject {
        public GetFileInfoRequest() {}
        /**
         * Returns file information for the given File wrapper.
         * <p><b>Experimental:</b> this part of CDP may change without notice.
         * @param objectId protocol value
         */
        public GetFileInfoRequest(Runtime.RemoteObjectId objectId) {
            set("objectId", objectId);
        }
        public static GetFileInfoRequest fromMap(Map<String, Object> values) {
            GetFileInfoRequest instance_ = new GetFileInfoRequest();
            if (values != null) instance_.values.putAll(values);
            return instance_;
        }
        /**
         * JavaScript object id of the node wrapper.
         * @return the protocol field value
         */
        public Runtime.RemoteObjectId objectId() {
            return new Runtime.RemoteObjectId((String) require("objectId"));
        }
        /**
         * JavaScript object id of the node wrapper.
         * @param objectId field value
         * @return this model
         */
        public GetFileInfoRequest objectId(Runtime.RemoteObjectId objectId) {
            set("objectId", objectId);
            return this;
        }
    }
    /**
     * Enables console to refer to the node with given id via $x (see Command Line API for more details $x functions).
     * <p><b>Experimental:</b> this part of CDP may change without notice.
     */
    public static final class SetInspectedNodeRequest extends CdpObject {
        public SetInspectedNodeRequest() {}
        /**
         * Enables console to refer to the node with given id via $x (see Command Line API for more details $x functions).
         * <p><b>Experimental:</b> this part of CDP may change without notice.
         * @param nodeId protocol value
         */
        public SetInspectedNodeRequest(DOM.NodeId nodeId) {
            set("nodeId", nodeId);
        }
        public static SetInspectedNodeRequest fromMap(Map<String, Object> values) {
            SetInspectedNodeRequest instance_ = new SetInspectedNodeRequest();
            if (values != null) instance_.values.putAll(values);
            return instance_;
        }
        /**
         * DOM node id to be accessible by means of $x command line API.
         * @return the protocol field value
         */
        public DOM.NodeId nodeId() {
            return new DOM.NodeId(((Number) require("nodeId")).longValue());
        }
        /**
         * DOM node id to be accessible by means of $x command line API.
         * @param nodeId field value
         * @return this model
         */
        public SetInspectedNodeRequest nodeId(DOM.NodeId nodeId) {
            set("nodeId", nodeId);
            return this;
        }
    }
    /**
     * Sets node name for a node with given id.
     */
    public static final class SetNodeNameRequest extends CdpObject {
        public SetNodeNameRequest() {}
        /**
         * Sets node name for a node with given id.
         * @param nodeId protocol value
         * @param name protocol value
         */
        public SetNodeNameRequest(DOM.NodeId nodeId, String name) {
            set("nodeId", nodeId);
            set("name", name);
        }
        public static SetNodeNameRequest fromMap(Map<String, Object> values) {
            SetNodeNameRequest instance_ = new SetNodeNameRequest();
            if (values != null) instance_.values.putAll(values);
            return instance_;
        }
        /**
         * Id of the node to set name for.
         * @return the protocol field value
         */
        public DOM.NodeId nodeId() {
            return new DOM.NodeId(((Number) require("nodeId")).longValue());
        }
        /**
         * New node&#x27;s name.
         * @return the protocol field value
         */
        public String name() {
            return (String) require("name");
        }
        /**
         * Id of the node to set name for.
         * @param nodeId field value
         * @return this model
         */
        public SetNodeNameRequest nodeId(DOM.NodeId nodeId) {
            set("nodeId", nodeId);
            return this;
        }
        /**
         * New node&#x27;s name.
         * @param name field value
         * @return this model
         */
        public SetNodeNameRequest name(String name) {
            set("name", name);
            return this;
        }
    }
    /**
     * Sets node value for a node with given id.
     */
    public static final class SetNodeValueRequest extends CdpObject {
        public SetNodeValueRequest() {}
        /**
         * Sets node value for a node with given id.
         * @param nodeId protocol value
         * @param value protocol value
         */
        public SetNodeValueRequest(DOM.NodeId nodeId, String value) {
            set("nodeId", nodeId);
            set("value", value);
        }
        public static SetNodeValueRequest fromMap(Map<String, Object> values) {
            SetNodeValueRequest instance_ = new SetNodeValueRequest();
            if (values != null) instance_.values.putAll(values);
            return instance_;
        }
        /**
         * Id of the node to set value for.
         * @return the protocol field value
         */
        public DOM.NodeId nodeId() {
            return new DOM.NodeId(((Number) require("nodeId")).longValue());
        }
        /**
         * New node&#x27;s value.
         * @return the protocol field value
         */
        public String value() {
            return (String) require("value");
        }
        /**
         * Id of the node to set value for.
         * @param nodeId field value
         * @return this model
         */
        public SetNodeValueRequest nodeId(DOM.NodeId nodeId) {
            set("nodeId", nodeId);
            return this;
        }
        /**
         * New node&#x27;s value.
         * @param value field value
         * @return this model
         */
        public SetNodeValueRequest value(String value) {
            set("value", value);
            return this;
        }
    }
    /**
     * Sets node HTML markup, returns new node id.
     */
    public static final class SetOuterHTMLRequest extends CdpObject {
        public SetOuterHTMLRequest() {}
        /**
         * Sets node HTML markup, returns new node id.
         * @param nodeId protocol value
         * @param outerHTML protocol value
         */
        public SetOuterHTMLRequest(DOM.NodeId nodeId, String outerHTML) {
            set("nodeId", nodeId);
            set("outerHTML", outerHTML);
        }
        public static SetOuterHTMLRequest fromMap(Map<String, Object> values) {
            SetOuterHTMLRequest instance_ = new SetOuterHTMLRequest();
            if (values != null) instance_.values.putAll(values);
            return instance_;
        }
        /**
         * Id of the node to set markup for.
         * @return the protocol field value
         */
        public DOM.NodeId nodeId() {
            return new DOM.NodeId(((Number) require("nodeId")).longValue());
        }
        /**
         * Outer HTML markup to set.
         * @return the protocol field value
         */
        public String outerHTML() {
            return (String) require("outerHTML");
        }
        /**
         * Id of the node to set markup for.
         * @param nodeId field value
         * @return this model
         */
        public SetOuterHTMLRequest nodeId(DOM.NodeId nodeId) {
            set("nodeId", nodeId);
            return this;
        }
        /**
         * Outer HTML markup to set.
         * @param outerHTML field value
         * @return this model
         */
        public SetOuterHTMLRequest outerHTML(String outerHTML) {
            set("outerHTML", outerHTML);
            return this;
        }
    }
    /**
     * Returns iframe node that owns iframe with the given domain.
     * <p><b>Experimental:</b> this part of CDP may change without notice.
     */
    public static final class GetFrameOwnerRequest extends CdpObject {
        public GetFrameOwnerRequest() {}
        /**
         * Returns iframe node that owns iframe with the given domain.
         * <p><b>Experimental:</b> this part of CDP may change without notice.
         * @param frameId protocol value
         */
        public GetFrameOwnerRequest(Page.FrameId frameId) {
            set("frameId", frameId);
        }
        public static GetFrameOwnerRequest fromMap(Map<String, Object> values) {
            GetFrameOwnerRequest instance_ = new GetFrameOwnerRequest();
            if (values != null) instance_.values.putAll(values);
            return instance_;
        }
        /**
         * Returns the frameId field.
         * @return the protocol field value
         */
        public Page.FrameId frameId() {
            return new Page.FrameId((String) require("frameId"));
        }
        /**
         * Sets the frameId field.
         * @param frameId field value
         * @return this model
         */
        public GetFrameOwnerRequest frameId(Page.FrameId frameId) {
            set("frameId", frameId);
            return this;
        }
    }
    /**
     * Returns the query container of the given node based on container query conditions: containerName, physical and logical axes, and whether it queries scroll-state or anchored elements. If no axes are provided and queriesScrollState is false, the style container is returned, which is the direct parent or the closest element with a matching container-name.
     * <p><b>Experimental:</b> this part of CDP may change without notice.
     */
    public static final class GetContainerForNodeRequest extends CdpObject {
        public GetContainerForNodeRequest() {}
        /**
         * Returns the query container of the given node based on container query conditions: containerName, physical and logical axes, and whether it queries scroll-state or anchored elements. If no axes are provided and queriesScrollState is false, the style container is returned, which is the direct parent or the closest element with a matching container-name.
         * <p><b>Experimental:</b> this part of CDP may change without notice.
         * @param nodeId protocol value
         */
        public GetContainerForNodeRequest(DOM.NodeId nodeId) {
            set("nodeId", nodeId);
        }
        public static GetContainerForNodeRequest fromMap(Map<String, Object> values) {
            GetContainerForNodeRequest instance_ = new GetContainerForNodeRequest();
            if (values != null) instance_.values.putAll(values);
            return instance_;
        }
        /**
         * Returns the nodeId field.
         * @return the protocol field value
         */
        public DOM.NodeId nodeId() {
            return new DOM.NodeId(((Number) require("nodeId")).longValue());
        }
        /**
         * Returns the containerName field.
         * @return the protocol field value, empty when absent
         */
        public Optional<String> containerName() {
            return Optional.ofNullable((String) raw("containerName"));
        }
        /**
         * Returns the physicalAxes field.
         * @return the protocol field value, empty when absent
         */
        public Optional<DOM.PhysicalAxes> physicalAxes() {
            return Optional.ofNullable(raw("physicalAxes") == null ? null : DOM.PhysicalAxes.of((String) raw("physicalAxes")));
        }
        /**
         * Returns the logicalAxes field.
         * @return the protocol field value, empty when absent
         */
        public Optional<DOM.LogicalAxes> logicalAxes() {
            return Optional.ofNullable(raw("logicalAxes") == null ? null : DOM.LogicalAxes.of((String) raw("logicalAxes")));
        }
        /**
         * Returns the queriesScrollState field.
         * @return the protocol field value, empty when absent
         */
        public Optional<Boolean> queriesScrollState() {
            return Optional.ofNullable((Boolean) raw("queriesScrollState"));
        }
        /**
         * Returns the queriesAnchored field.
         * @return the protocol field value, empty when absent
         */
        public Optional<Boolean> queriesAnchored() {
            return Optional.ofNullable((Boolean) raw("queriesAnchored"));
        }
        /**
         * Sets the nodeId field.
         * @param nodeId field value
         * @return this model
         */
        public GetContainerForNodeRequest nodeId(DOM.NodeId nodeId) {
            set("nodeId", nodeId);
            return this;
        }
        /**
         * Sets the containerName field.
         * @param containerName field value; empty omits the value
         * @return this model
         */
        public GetContainerForNodeRequest containerName(Optional<String> containerName) {
            set("containerName", containerName.orElse(null));
            return this;
        }
        /**
         * Sets the containerName field.
         * @param containerName field value; null removes the value
         * @return this model
         */
        public GetContainerForNodeRequest containerName(String containerName) {
            set("containerName", containerName);
            return this;
        }
        /**
         * Sets the physicalAxes field.
         * @param physicalAxes field value; empty omits the value
         * @return this model
         */
        public GetContainerForNodeRequest physicalAxes(Optional<DOM.PhysicalAxes> physicalAxes) {
            set("physicalAxes", physicalAxes.orElse(null));
            return this;
        }
        /**
         * Sets the physicalAxes field.
         * @param physicalAxes field value; null removes the value
         * @return this model
         */
        public GetContainerForNodeRequest physicalAxes(DOM.PhysicalAxes physicalAxes) {
            set("physicalAxes", physicalAxes);
            return this;
        }
        /**
         * Sets the logicalAxes field.
         * @param logicalAxes field value; empty omits the value
         * @return this model
         */
        public GetContainerForNodeRequest logicalAxes(Optional<DOM.LogicalAxes> logicalAxes) {
            set("logicalAxes", logicalAxes.orElse(null));
            return this;
        }
        /**
         * Sets the logicalAxes field.
         * @param logicalAxes field value; null removes the value
         * @return this model
         */
        public GetContainerForNodeRequest logicalAxes(DOM.LogicalAxes logicalAxes) {
            set("logicalAxes", logicalAxes);
            return this;
        }
        /**
         * Sets the queriesScrollState field.
         * @param queriesScrollState field value; empty omits the value
         * @return this model
         */
        public GetContainerForNodeRequest queriesScrollState(Optional<Boolean> queriesScrollState) {
            set("queriesScrollState", queriesScrollState.orElse(null));
            return this;
        }
        /**
         * Sets the queriesScrollState field.
         * @param queriesScrollState field value; null removes the value
         * @return this model
         */
        public GetContainerForNodeRequest queriesScrollState(Boolean queriesScrollState) {
            set("queriesScrollState", queriesScrollState);
            return this;
        }
        /**
         * Sets the queriesAnchored field.
         * @param queriesAnchored field value; empty omits the value
         * @return this model
         */
        public GetContainerForNodeRequest queriesAnchored(Optional<Boolean> queriesAnchored) {
            set("queriesAnchored", queriesAnchored.orElse(null));
            return this;
        }
        /**
         * Sets the queriesAnchored field.
         * @param queriesAnchored field value; null removes the value
         * @return this model
         */
        public GetContainerForNodeRequest queriesAnchored(Boolean queriesAnchored) {
            set("queriesAnchored", queriesAnchored);
            return this;
        }
    }
    /**
     * Returns the descendants of a container query container that have container queries against this container.
     * <p><b>Experimental:</b> this part of CDP may change without notice.
     */
    public static final class GetQueryingDescendantsForContainerRequest extends CdpObject {
        public GetQueryingDescendantsForContainerRequest() {}
        /**
         * Returns the descendants of a container query container that have container queries against this container.
         * <p><b>Experimental:</b> this part of CDP may change without notice.
         * @param nodeId protocol value
         */
        public GetQueryingDescendantsForContainerRequest(DOM.NodeId nodeId) {
            set("nodeId", nodeId);
        }
        public static GetQueryingDescendantsForContainerRequest fromMap(Map<String, Object> values) {
            GetQueryingDescendantsForContainerRequest instance_ = new GetQueryingDescendantsForContainerRequest();
            if (values != null) instance_.values.putAll(values);
            return instance_;
        }
        /**
         * Id of the container node to find querying descendants from.
         * @return the protocol field value
         */
        public DOM.NodeId nodeId() {
            return new DOM.NodeId(((Number) require("nodeId")).longValue());
        }
        /**
         * Id of the container node to find querying descendants from.
         * @param nodeId field value
         * @return this model
         */
        public GetQueryingDescendantsForContainerRequest nodeId(DOM.NodeId nodeId) {
            set("nodeId", nodeId);
            return this;
        }
    }
    /**
     * Returns the target anchor element of the given anchor query according to https://www.w3.org/TR/css-anchor-position-1/#target.
     * <p><b>Experimental:</b> this part of CDP may change without notice.
     */
    public static final class GetAnchorElementRequest extends CdpObject {
        public GetAnchorElementRequest() {}
        /**
         * Returns the target anchor element of the given anchor query according to https://www.w3.org/TR/css-anchor-position-1/#target.
         * <p><b>Experimental:</b> this part of CDP may change without notice.
         * @param nodeId protocol value
         */
        public GetAnchorElementRequest(DOM.NodeId nodeId) {
            set("nodeId", nodeId);
        }
        public static GetAnchorElementRequest fromMap(Map<String, Object> values) {
            GetAnchorElementRequest instance_ = new GetAnchorElementRequest();
            if (values != null) instance_.values.putAll(values);
            return instance_;
        }
        /**
         * Id of the positioned element from which to find the anchor.
         * @return the protocol field value
         */
        public DOM.NodeId nodeId() {
            return new DOM.NodeId(((Number) require("nodeId")).longValue());
        }
        /**
         * An optional anchor specifier, as defined in https://www.w3.org/TR/css-anchor-position-1/#anchor-specifier. If not provided, it will return the implicit anchor element for the given positioned element.
         * @return the protocol field value, empty when absent
         */
        public Optional<String> anchorSpecifier() {
            return Optional.ofNullable((String) raw("anchorSpecifier"));
        }
        /**
         * Id of the positioned element from which to find the anchor.
         * @param nodeId field value
         * @return this model
         */
        public GetAnchorElementRequest nodeId(DOM.NodeId nodeId) {
            set("nodeId", nodeId);
            return this;
        }
        /**
         * An optional anchor specifier, as defined in https://www.w3.org/TR/css-anchor-position-1/#anchor-specifier. If not provided, it will return the implicit anchor element for the given positioned element.
         * @param anchorSpecifier field value; empty omits the value
         * @return this model
         */
        public GetAnchorElementRequest anchorSpecifier(Optional<String> anchorSpecifier) {
            set("anchorSpecifier", anchorSpecifier.orElse(null));
            return this;
        }
        /**
         * An optional anchor specifier, as defined in https://www.w3.org/TR/css-anchor-position-1/#anchor-specifier. If not provided, it will return the implicit anchor element for the given positioned element.
         * @param anchorSpecifier field value; null removes the value
         * @return this model
         */
        public GetAnchorElementRequest anchorSpecifier(String anchorSpecifier) {
            set("anchorSpecifier", anchorSpecifier);
            return this;
        }
    }
    /**
     * When enabling, this API force-opens the popover identified by nodeId and keeps it open until disabled.
     * <p><b>Experimental:</b> this part of CDP may change without notice.
     */
    public static final class ForceShowPopoverRequest extends CdpObject {
        public ForceShowPopoverRequest() {}
        /**
         * When enabling, this API force-opens the popover identified by nodeId and keeps it open until disabled.
         * <p><b>Experimental:</b> this part of CDP may change without notice.
         * @param nodeId protocol value
         * @param enable protocol value
         */
        public ForceShowPopoverRequest(DOM.NodeId nodeId, boolean enable) {
            set("nodeId", nodeId);
            set("enable", enable);
        }
        public static ForceShowPopoverRequest fromMap(Map<String, Object> values) {
            ForceShowPopoverRequest instance_ = new ForceShowPopoverRequest();
            if (values != null) instance_.values.putAll(values);
            return instance_;
        }
        /**
         * Id of the popover HTMLElement
         * @return the protocol field value
         */
        public DOM.NodeId nodeId() {
            return new DOM.NodeId(((Number) require("nodeId")).longValue());
        }
        /**
         * If true, opens the popover and keeps it open. If false, closes the popover if it was previously force-opened.
         * @return the protocol field value
         */
        public boolean enable() {
            return (Boolean) require("enable");
        }
        /**
         * Id of the popover HTMLElement
         * @param nodeId field value
         * @return this model
         */
        public ForceShowPopoverRequest nodeId(DOM.NodeId nodeId) {
            set("nodeId", nodeId);
            return this;
        }
        /**
         * If true, opens the popover and keeps it open. If false, closes the popover if it was previously force-opened.
         * @param enable field value
         * @return this model
         */
        public ForceShowPopoverRequest enable(boolean enable) {
            set("enable", enable);
            return this;
        }
    }
    /**
     * Returns node id at given location. Depending on whether DOM domain is enabled, nodeId is either returned or not.
     */
    public static final class GetNodeForLocationResult extends CdpObject {
        public GetNodeForLocationResult() {}
        private GetNodeForLocationResult(Map<String, Object> values) { super(values); }
        public static GetNodeForLocationResult fromMap(Map<String, Object> values) {
            return new GetNodeForLocationResult(values);
        }
        /**
         * Resulting node.
         * @return the protocol field value
         */
        public DOM.BackendNodeId backendNodeId() {
            return new DOM.BackendNodeId(((Number) require("backendNodeId")).longValue());
        }
        /**
         * Frame this node belongs to.
         * @return the protocol field value
         */
        public Page.FrameId frameId() {
            return new Page.FrameId((String) require("frameId"));
        }
        /**
         * Id of the node at given coordinates, only when enabled and requested document.
         * @return the protocol field value, empty when absent
         */
        public Optional<DOM.NodeId> nodeId() {
            return Optional.ofNullable(raw("nodeId") == null ? null : new DOM.NodeId(((Number) raw("nodeId")).longValue()));
        }
        /**
         * Resulting node.
         * @param backendNodeId field value
         * @return this model
         */
        public GetNodeForLocationResult backendNodeId(DOM.BackendNodeId backendNodeId) {
            set("backendNodeId", backendNodeId);
            return this;
        }
        /**
         * Frame this node belongs to.
         * @param frameId field value
         * @return this model
         */
        public GetNodeForLocationResult frameId(Page.FrameId frameId) {
            set("frameId", frameId);
            return this;
        }
        /**
         * Id of the node at given coordinates, only when enabled and requested document.
         * @param nodeId field value; empty omits the value
         * @return this model
         */
        public GetNodeForLocationResult nodeId(Optional<DOM.NodeId> nodeId) {
            set("nodeId", nodeId.orElse(null));
            return this;
        }
        /**
         * Id of the node at given coordinates, only when enabled and requested document.
         * @param nodeId field value; null removes the value
         * @return this model
         */
        public GetNodeForLocationResult nodeId(DOM.NodeId nodeId) {
            set("nodeId", nodeId);
            return this;
        }
    }
    /**
     * Searches for a given string in the DOM tree. Use {@code getSearchResults} to access search results or {@code cancelSearch} to end this search session.
     * <p><b>Experimental:</b> this part of CDP may change without notice.
     */
    public static final class PerformSearchResult extends CdpObject {
        public PerformSearchResult() {}
        private PerformSearchResult(Map<String, Object> values) { super(values); }
        public static PerformSearchResult fromMap(Map<String, Object> values) {
            return new PerformSearchResult(values);
        }
        /**
         * Unique search session identifier.
         * @return the protocol field value
         */
        public String searchId() {
            return (String) require("searchId");
        }
        /**
         * Number of search results.
         * @return the protocol field value
         */
        public long resultCount() {
            return ((Number) require("resultCount")).longValue();
        }
        /**
         * Unique search session identifier.
         * @param searchId field value
         * @return this model
         */
        public PerformSearchResult searchId(String searchId) {
            set("searchId", searchId);
            return this;
        }
        /**
         * Number of search results.
         * @param resultCount field value
         * @return this model
         */
        public PerformSearchResult resultCount(long resultCount) {
            set("resultCount", resultCount);
            return this;
        }
    }
    /**
     * Returns iframe node that owns iframe with the given domain.
     * <p><b>Experimental:</b> this part of CDP may change without notice.
     */
    public static final class GetFrameOwnerResult extends CdpObject {
        public GetFrameOwnerResult() {}
        private GetFrameOwnerResult(Map<String, Object> values) { super(values); }
        public static GetFrameOwnerResult fromMap(Map<String, Object> values) {
            return new GetFrameOwnerResult(values);
        }
        /**
         * Resulting node.
         * @return the protocol field value
         */
        public DOM.BackendNodeId backendNodeId() {
            return new DOM.BackendNodeId(((Number) require("backendNodeId")).longValue());
        }
        /**
         * Id of the node at given coordinates, only when enabled and requested document.
         * @return the protocol field value, empty when absent
         */
        public Optional<DOM.NodeId> nodeId() {
            return Optional.ofNullable(raw("nodeId") == null ? null : new DOM.NodeId(((Number) raw("nodeId")).longValue()));
        }
        /**
         * Resulting node.
         * @param backendNodeId field value
         * @return this model
         */
        public GetFrameOwnerResult backendNodeId(DOM.BackendNodeId backendNodeId) {
            set("backendNodeId", backendNodeId);
            return this;
        }
        /**
         * Id of the node at given coordinates, only when enabled and requested document.
         * @param nodeId field value; empty omits the value
         * @return this model
         */
        public GetFrameOwnerResult nodeId(Optional<DOM.NodeId> nodeId) {
            set("nodeId", nodeId.orElse(null));
            return this;
        }
        /**
         * Id of the node at given coordinates, only when enabled and requested document.
         * @param nodeId field value; null removes the value
         * @return this model
         */
        public GetFrameOwnerResult nodeId(DOM.NodeId nodeId) {
            set("nodeId", nodeId);
            return this;
        }
    }
    /**
     * Fired when {@code Element}&#x27;s attribute is modified.
     */
    public static final class AttributeModifiedEvent extends CdpObject {
        public AttributeModifiedEvent() {}
        private AttributeModifiedEvent(Map<String, Object> values) { super(values); }
        public static AttributeModifiedEvent fromMap(Map<String, Object> values) {
            return new AttributeModifiedEvent(values);
        }
        /**
         * Id of the node that has changed.
         * @return the protocol field value
         */
        public DOM.NodeId nodeId() {
            return new DOM.NodeId(((Number) require("nodeId")).longValue());
        }
        /**
         * Attribute name.
         * @return the protocol field value
         */
        public String name() {
            return (String) require("name");
        }
        /**
         * Attribute value.
         * @return the protocol field value
         */
        public String value() {
            return (String) require("value");
        }
        /**
         * Id of the node that has changed.
         * @param nodeId field value
         * @return this model
         */
        public AttributeModifiedEvent nodeId(DOM.NodeId nodeId) {
            set("nodeId", nodeId);
            return this;
        }
        /**
         * Attribute name.
         * @param name field value
         * @return this model
         */
        public AttributeModifiedEvent name(String name) {
            set("name", name);
            return this;
        }
        /**
         * Attribute value.
         * @param value field value
         * @return this model
         */
        public AttributeModifiedEvent value(String value) {
            set("value", value);
            return this;
        }
    }
    /**
     * Fired when {@code Element}&#x27;s adoptedStyleSheets are modified.
     * <p><b>Experimental:</b> this part of CDP may change without notice.
     */
    public static final class AdoptedStyleSheetsModifiedEvent extends CdpObject {
        public AdoptedStyleSheetsModifiedEvent() {}
        private AdoptedStyleSheetsModifiedEvent(Map<String, Object> values) { super(values); }
        public static AdoptedStyleSheetsModifiedEvent fromMap(Map<String, Object> values) {
            return new AdoptedStyleSheetsModifiedEvent(values);
        }
        /**
         * Id of the node that has changed.
         * @return the protocol field value
         */
        public DOM.NodeId nodeId() {
            return new DOM.NodeId(((Number) require("nodeId")).longValue());
        }
        /**
         * New adoptedStyleSheets array.
         * <p><b>Experimental:</b> this part of CDP may change without notice.
         * @return the protocol field value
         */
        public java.util.List<DOM.StyleSheetId> adoptedStyleSheets() {
            return CdpObject.requireList(require("adoptedStyleSheets"), element0 -> new DOM.StyleSheetId((String) element0));
        }
        /**
         * Id of the node that has changed.
         * @param nodeId field value
         * @return this model
         */
        public AdoptedStyleSheetsModifiedEvent nodeId(DOM.NodeId nodeId) {
            set("nodeId", nodeId);
            return this;
        }
        /**
         * New adoptedStyleSheets array.
         * <p><b>Experimental:</b> this part of CDP may change without notice.
         * @param adoptedStyleSheets field value
         * @return this model
         */
        public AdoptedStyleSheetsModifiedEvent adoptedStyleSheets(java.util.List<DOM.StyleSheetId> adoptedStyleSheets) {
            set("adoptedStyleSheets", adoptedStyleSheets);
            return this;
        }
    }
    /**
     * Fired when {@code Element}&#x27;s attribute is removed.
     */
    public static final class AttributeRemovedEvent extends CdpObject {
        public AttributeRemovedEvent() {}
        private AttributeRemovedEvent(Map<String, Object> values) { super(values); }
        public static AttributeRemovedEvent fromMap(Map<String, Object> values) {
            return new AttributeRemovedEvent(values);
        }
        /**
         * Id of the node that has changed.
         * @return the protocol field value
         */
        public DOM.NodeId nodeId() {
            return new DOM.NodeId(((Number) require("nodeId")).longValue());
        }
        /**
         * A ttribute name.
         * @return the protocol field value
         */
        public String name() {
            return (String) require("name");
        }
        /**
         * Id of the node that has changed.
         * @param nodeId field value
         * @return this model
         */
        public AttributeRemovedEvent nodeId(DOM.NodeId nodeId) {
            set("nodeId", nodeId);
            return this;
        }
        /**
         * A ttribute name.
         * @param name field value
         * @return this model
         */
        public AttributeRemovedEvent name(String name) {
            set("name", name);
            return this;
        }
    }
    /**
     * Mirrors {@code DOMCharacterDataModified} event.
     */
    public static final class CharacterDataModifiedEvent extends CdpObject {
        public CharacterDataModifiedEvent() {}
        private CharacterDataModifiedEvent(Map<String, Object> values) { super(values); }
        public static CharacterDataModifiedEvent fromMap(Map<String, Object> values) {
            return new CharacterDataModifiedEvent(values);
        }
        /**
         * Id of the node that has changed.
         * @return the protocol field value
         */
        public DOM.NodeId nodeId() {
            return new DOM.NodeId(((Number) require("nodeId")).longValue());
        }
        /**
         * New text value.
         * @return the protocol field value
         */
        public String characterData() {
            return (String) require("characterData");
        }
        /**
         * Id of the node that has changed.
         * @param nodeId field value
         * @return this model
         */
        public CharacterDataModifiedEvent nodeId(DOM.NodeId nodeId) {
            set("nodeId", nodeId);
            return this;
        }
        /**
         * New text value.
         * @param characterData field value
         * @return this model
         */
        public CharacterDataModifiedEvent characterData(String characterData) {
            set("characterData", characterData);
            return this;
        }
    }
    /**
     * Fired when {@code Container}&#x27;s child node count has changed.
     */
    public static final class ChildNodeCountUpdatedEvent extends CdpObject {
        public ChildNodeCountUpdatedEvent() {}
        private ChildNodeCountUpdatedEvent(Map<String, Object> values) { super(values); }
        public static ChildNodeCountUpdatedEvent fromMap(Map<String, Object> values) {
            return new ChildNodeCountUpdatedEvent(values);
        }
        /**
         * Id of the node that has changed.
         * @return the protocol field value
         */
        public DOM.NodeId nodeId() {
            return new DOM.NodeId(((Number) require("nodeId")).longValue());
        }
        /**
         * New node count.
         * @return the protocol field value
         */
        public long childNodeCount() {
            return ((Number) require("childNodeCount")).longValue();
        }
        /**
         * Id of the node that has changed.
         * @param nodeId field value
         * @return this model
         */
        public ChildNodeCountUpdatedEvent nodeId(DOM.NodeId nodeId) {
            set("nodeId", nodeId);
            return this;
        }
        /**
         * New node count.
         * @param childNodeCount field value
         * @return this model
         */
        public ChildNodeCountUpdatedEvent childNodeCount(long childNodeCount) {
            set("childNodeCount", childNodeCount);
            return this;
        }
    }
    /**
     * Mirrors {@code DOMNodeInserted} event.
     */
    public static final class ChildNodeInsertedEvent extends CdpObject {
        public ChildNodeInsertedEvent() {}
        private ChildNodeInsertedEvent(Map<String, Object> values) { super(values); }
        public static ChildNodeInsertedEvent fromMap(Map<String, Object> values) {
            return new ChildNodeInsertedEvent(values);
        }
        /**
         * Id of the node that has changed.
         * @return the protocol field value
         */
        public DOM.NodeId parentNodeId() {
            return new DOM.NodeId(((Number) require("parentNodeId")).longValue());
        }
        /**
         * Id of the previous sibling.
         * @return the protocol field value
         */
        public DOM.NodeId previousNodeId() {
            return new DOM.NodeId(((Number) require("previousNodeId")).longValue());
        }
        /**
         * Inserted node data.
         * @return the protocol field value
         */
        public DOM.Node node() {
            return java.util.Objects.requireNonNull(DOM.Node.fromMap(java.util.Objects.requireNonNull(CdpObject.objectMap(require("node")))));
        }
        /**
         * Id of the node that has changed.
         * @param parentNodeId field value
         * @return this model
         */
        public ChildNodeInsertedEvent parentNodeId(DOM.NodeId parentNodeId) {
            set("parentNodeId", parentNodeId);
            return this;
        }
        /**
         * Id of the previous sibling.
         * @param previousNodeId field value
         * @return this model
         */
        public ChildNodeInsertedEvent previousNodeId(DOM.NodeId previousNodeId) {
            set("previousNodeId", previousNodeId);
            return this;
        }
        /**
         * Inserted node data.
         * @param node field value
         * @return this model
         */
        public ChildNodeInsertedEvent node(DOM.Node node) {
            set("node", node);
            return this;
        }
    }
    /**
     * Mirrors {@code DOMNodeRemoved} event.
     */
    public static final class ChildNodeRemovedEvent extends CdpObject {
        public ChildNodeRemovedEvent() {}
        private ChildNodeRemovedEvent(Map<String, Object> values) { super(values); }
        public static ChildNodeRemovedEvent fromMap(Map<String, Object> values) {
            return new ChildNodeRemovedEvent(values);
        }
        /**
         * Parent id.
         * @return the protocol field value
         */
        public DOM.NodeId parentNodeId() {
            return new DOM.NodeId(((Number) require("parentNodeId")).longValue());
        }
        /**
         * Id of the node that has been removed.
         * @return the protocol field value
         */
        public DOM.NodeId nodeId() {
            return new DOM.NodeId(((Number) require("nodeId")).longValue());
        }
        /**
         * Parent id.
         * @param parentNodeId field value
         * @return this model
         */
        public ChildNodeRemovedEvent parentNodeId(DOM.NodeId parentNodeId) {
            set("parentNodeId", parentNodeId);
            return this;
        }
        /**
         * Id of the node that has been removed.
         * @param nodeId field value
         * @return this model
         */
        public ChildNodeRemovedEvent nodeId(DOM.NodeId nodeId) {
            set("nodeId", nodeId);
            return this;
        }
    }
    /**
     * Called when distribution is changed.
     * <p><b>Experimental:</b> this part of CDP may change without notice.
     */
    public static final class DistributedNodesUpdatedEvent extends CdpObject {
        public DistributedNodesUpdatedEvent() {}
        private DistributedNodesUpdatedEvent(Map<String, Object> values) { super(values); }
        public static DistributedNodesUpdatedEvent fromMap(Map<String, Object> values) {
            return new DistributedNodesUpdatedEvent(values);
        }
        /**
         * Insertion point where distributed nodes were updated.
         * @return the protocol field value
         */
        public DOM.NodeId insertionPointId() {
            return new DOM.NodeId(((Number) require("insertionPointId")).longValue());
        }
        /**
         * Distributed nodes for given insertion point.
         * @return the protocol field value
         */
        public java.util.List<DOM.BackendNode> distributedNodes() {
            return CdpObject.requireList(require("distributedNodes"), element0 -> java.util.Objects.requireNonNull(DOM.BackendNode.fromMap(java.util.Objects.requireNonNull(CdpObject.objectMap(element0)))));
        }
        /**
         * Insertion point where distributed nodes were updated.
         * @param insertionPointId field value
         * @return this model
         */
        public DistributedNodesUpdatedEvent insertionPointId(DOM.NodeId insertionPointId) {
            set("insertionPointId", insertionPointId);
            return this;
        }
        /**
         * Distributed nodes for given insertion point.
         * @param distributedNodes field value
         * @return this model
         */
        public DistributedNodesUpdatedEvent distributedNodes(java.util.List<DOM.BackendNode> distributedNodes) {
            set("distributedNodes", distributedNodes);
            return this;
        }
    }
    /**
     * Fired when {@code Document} has been totally updated. Node ids are no longer valid.
     */
    public static final class DocumentUpdatedEvent extends CdpObject {
        public DocumentUpdatedEvent() {}
        private DocumentUpdatedEvent(Map<String, Object> values) { super(values); }
        public static DocumentUpdatedEvent fromMap(Map<String, Object> values) {
            return new DocumentUpdatedEvent(values);
        }
    }
    /**
     * Fired when {@code Element}&#x27;s inline style is modified via a CSS property modification.
     * <p><b>Experimental:</b> this part of CDP may change without notice.
     */
    public static final class InlineStyleInvalidatedEvent extends CdpObject {
        public InlineStyleInvalidatedEvent() {}
        private InlineStyleInvalidatedEvent(Map<String, Object> values) { super(values); }
        public static InlineStyleInvalidatedEvent fromMap(Map<String, Object> values) {
            return new InlineStyleInvalidatedEvent(values);
        }
        /**
         * Ids of the nodes for which the inline styles have been invalidated.
         * @return the protocol field value
         */
        public java.util.List<DOM.NodeId> nodeIds() {
            return CdpObject.requireList(require("nodeIds"), element0 -> new DOM.NodeId(((Number) element0).longValue()));
        }
        /**
         * Ids of the nodes for which the inline styles have been invalidated.
         * @param nodeIds field value
         * @return this model
         */
        public InlineStyleInvalidatedEvent nodeIds(java.util.List<DOM.NodeId> nodeIds) {
            set("nodeIds", nodeIds);
            return this;
        }
    }
    /**
     * Called when a pseudo element is added to an element.
     * <p><b>Experimental:</b> this part of CDP may change without notice.
     */
    public static final class PseudoElementAddedEvent extends CdpObject {
        public PseudoElementAddedEvent() {}
        private PseudoElementAddedEvent(Map<String, Object> values) { super(values); }
        public static PseudoElementAddedEvent fromMap(Map<String, Object> values) {
            return new PseudoElementAddedEvent(values);
        }
        /**
         * Pseudo element&#x27;s parent element id.
         * @return the protocol field value
         */
        public DOM.NodeId parentId() {
            return new DOM.NodeId(((Number) require("parentId")).longValue());
        }
        /**
         * The added pseudo element.
         * @return the protocol field value
         */
        public DOM.Node pseudoElement() {
            return java.util.Objects.requireNonNull(DOM.Node.fromMap(java.util.Objects.requireNonNull(CdpObject.objectMap(require("pseudoElement")))));
        }
        /**
         * Pseudo element&#x27;s parent element id.
         * @param parentId field value
         * @return this model
         */
        public PseudoElementAddedEvent parentId(DOM.NodeId parentId) {
            set("parentId", parentId);
            return this;
        }
        /**
         * The added pseudo element.
         * @param pseudoElement field value
         * @return this model
         */
        public PseudoElementAddedEvent pseudoElement(DOM.Node pseudoElement) {
            set("pseudoElement", pseudoElement);
            return this;
        }
    }
    /**
     * Called when top layer elements are changed.
     * <p><b>Experimental:</b> this part of CDP may change without notice.
     */
    public static final class TopLayerElementsUpdatedEvent extends CdpObject {
        public TopLayerElementsUpdatedEvent() {}
        private TopLayerElementsUpdatedEvent(Map<String, Object> values) { super(values); }
        public static TopLayerElementsUpdatedEvent fromMap(Map<String, Object> values) {
            return new TopLayerElementsUpdatedEvent(values);
        }
    }
    /**
     * Fired when a node&#x27;s scrollability state changes.
     * <p><b>Experimental:</b> this part of CDP may change without notice.
     */
    public static final class ScrollableFlagUpdatedEvent extends CdpObject {
        public ScrollableFlagUpdatedEvent() {}
        private ScrollableFlagUpdatedEvent(Map<String, Object> values) { super(values); }
        public static ScrollableFlagUpdatedEvent fromMap(Map<String, Object> values) {
            return new ScrollableFlagUpdatedEvent(values);
        }
        /**
         * The id of the node.
         * @return the protocol field value
         */
        public DOM.NodeId nodeId() {
            return new DOM.NodeId(((Number) require("nodeId")).longValue());
        }
        /**
         * If the node is scrollable.
         * @return the protocol field value
         */
        public boolean isScrollable() {
            return (Boolean) require("isScrollable");
        }
        /**
         * The id of the node.
         * @param nodeId field value
         * @return this model
         */
        public ScrollableFlagUpdatedEvent nodeId(DOM.NodeId nodeId) {
            set("nodeId", nodeId);
            return this;
        }
        /**
         * If the node is scrollable.
         * @param isScrollable field value
         * @return this model
         */
        public ScrollableFlagUpdatedEvent isScrollable(boolean isScrollable) {
            set("isScrollable", isScrollable);
            return this;
        }
    }
    /**
     * Fired when a node&#x27;s ad related state changes.
     * <p><b>Experimental:</b> this part of CDP may change without notice.
     */
    public static final class AdRelatedStateUpdatedEvent extends CdpObject {
        public AdRelatedStateUpdatedEvent() {}
        private AdRelatedStateUpdatedEvent(Map<String, Object> values) { super(values); }
        public static AdRelatedStateUpdatedEvent fromMap(Map<String, Object> values) {
            return new AdRelatedStateUpdatedEvent(values);
        }
        /**
         * The id of the node.
         * @return the protocol field value
         */
        public DOM.NodeId nodeId() {
            return new DOM.NodeId(((Number) require("nodeId")).longValue());
        }
        /**
         * The provenance of the ad related node, if it is ad related.
         * @return the protocol field value, empty when absent
         */
        public Optional<Network.AdProvenance> adProvenance() {
            return Optional.ofNullable(raw("adProvenance") == null ? null : Network.AdProvenance.fromMap(java.util.Objects.requireNonNull(objectMap(raw("adProvenance")))));
        }
        /**
         * The id of the node.
         * @param nodeId field value
         * @return this model
         */
        public AdRelatedStateUpdatedEvent nodeId(DOM.NodeId nodeId) {
            set("nodeId", nodeId);
            return this;
        }
        /**
         * The provenance of the ad related node, if it is ad related.
         * @param adProvenance field value; empty omits the value
         * @return this model
         */
        public AdRelatedStateUpdatedEvent adProvenance(Optional<Network.AdProvenance> adProvenance) {
            set("adProvenance", adProvenance.orElse(null));
            return this;
        }
        /**
         * The provenance of the ad related node, if it is ad related.
         * @param adProvenance field value; null removes the value
         * @return this model
         */
        public AdRelatedStateUpdatedEvent adProvenance(Network.AdProvenance adProvenance) {
            set("adProvenance", adProvenance);
            return this;
        }
    }
    /**
     * Fired when a node&#x27;s starting styles changes.
     * <p><b>Experimental:</b> this part of CDP may change without notice.
     */
    public static final class AffectedByStartingStylesFlagUpdatedEvent extends CdpObject {
        public AffectedByStartingStylesFlagUpdatedEvent() {}
        private AffectedByStartingStylesFlagUpdatedEvent(Map<String, Object> values) { super(values); }
        public static AffectedByStartingStylesFlagUpdatedEvent fromMap(Map<String, Object> values) {
            return new AffectedByStartingStylesFlagUpdatedEvent(values);
        }
        /**
         * The id of the node.
         * @return the protocol field value
         */
        public DOM.NodeId nodeId() {
            return new DOM.NodeId(((Number) require("nodeId")).longValue());
        }
        /**
         * If the node has starting styles.
         * @return the protocol field value
         */
        public boolean affectedByStartingStyles() {
            return (Boolean) require("affectedByStartingStyles");
        }
        /**
         * The id of the node.
         * @param nodeId field value
         * @return this model
         */
        public AffectedByStartingStylesFlagUpdatedEvent nodeId(DOM.NodeId nodeId) {
            set("nodeId", nodeId);
            return this;
        }
        /**
         * If the node has starting styles.
         * @param affectedByStartingStyles field value
         * @return this model
         */
        public AffectedByStartingStylesFlagUpdatedEvent affectedByStartingStyles(boolean affectedByStartingStyles) {
            set("affectedByStartingStyles", affectedByStartingStyles);
            return this;
        }
    }
    /**
     * Called when a pseudo element is removed from an element.
     * <p><b>Experimental:</b> this part of CDP may change without notice.
     */
    public static final class PseudoElementRemovedEvent extends CdpObject {
        public PseudoElementRemovedEvent() {}
        private PseudoElementRemovedEvent(Map<String, Object> values) { super(values); }
        public static PseudoElementRemovedEvent fromMap(Map<String, Object> values) {
            return new PseudoElementRemovedEvent(values);
        }
        /**
         * Pseudo element&#x27;s parent element id.
         * @return the protocol field value
         */
        public DOM.NodeId parentId() {
            return new DOM.NodeId(((Number) require("parentId")).longValue());
        }
        /**
         * The removed pseudo element id.
         * @return the protocol field value
         */
        public DOM.NodeId pseudoElementId() {
            return new DOM.NodeId(((Number) require("pseudoElementId")).longValue());
        }
        /**
         * Pseudo element&#x27;s parent element id.
         * @param parentId field value
         * @return this model
         */
        public PseudoElementRemovedEvent parentId(DOM.NodeId parentId) {
            set("parentId", parentId);
            return this;
        }
        /**
         * The removed pseudo element id.
         * @param pseudoElementId field value
         * @return this model
         */
        public PseudoElementRemovedEvent pseudoElementId(DOM.NodeId pseudoElementId) {
            set("pseudoElementId", pseudoElementId);
            return this;
        }
    }
    /**
     * Fired when backend wants to provide client with the missing DOM structure. This happens upon most of the calls requesting node ids.
     */
    public static final class SetChildNodesEvent extends CdpObject {
        public SetChildNodesEvent() {}
        private SetChildNodesEvent(Map<String, Object> values) { super(values); }
        public static SetChildNodesEvent fromMap(Map<String, Object> values) {
            return new SetChildNodesEvent(values);
        }
        /**
         * Parent node id to populate with children.
         * @return the protocol field value
         */
        public DOM.NodeId parentId() {
            return new DOM.NodeId(((Number) require("parentId")).longValue());
        }
        /**
         * Child nodes array.
         * @return the protocol field value
         */
        public java.util.List<DOM.Node> nodes() {
            return CdpObject.requireList(require("nodes"), element0 -> java.util.Objects.requireNonNull(DOM.Node.fromMap(java.util.Objects.requireNonNull(CdpObject.objectMap(element0)))));
        }
        /**
         * Parent node id to populate with children.
         * @param parentId field value
         * @return this model
         */
        public SetChildNodesEvent parentId(DOM.NodeId parentId) {
            set("parentId", parentId);
            return this;
        }
        /**
         * Child nodes array.
         * @param nodes field value
         * @return this model
         */
        public SetChildNodesEvent nodes(java.util.List<DOM.Node> nodes) {
            set("nodes", nodes);
            return this;
        }
    }
    /**
     * Called when shadow root is popped from the element.
     * <p><b>Experimental:</b> this part of CDP may change without notice.
     */
    public static final class ShadowRootPoppedEvent extends CdpObject {
        public ShadowRootPoppedEvent() {}
        private ShadowRootPoppedEvent(Map<String, Object> values) { super(values); }
        public static ShadowRootPoppedEvent fromMap(Map<String, Object> values) {
            return new ShadowRootPoppedEvent(values);
        }
        /**
         * Host element id.
         * @return the protocol field value
         */
        public DOM.NodeId hostId() {
            return new DOM.NodeId(((Number) require("hostId")).longValue());
        }
        /**
         * Shadow root id.
         * @return the protocol field value
         */
        public DOM.NodeId rootId() {
            return new DOM.NodeId(((Number) require("rootId")).longValue());
        }
        /**
         * Host element id.
         * @param hostId field value
         * @return this model
         */
        public ShadowRootPoppedEvent hostId(DOM.NodeId hostId) {
            set("hostId", hostId);
            return this;
        }
        /**
         * Shadow root id.
         * @param rootId field value
         * @return this model
         */
        public ShadowRootPoppedEvent rootId(DOM.NodeId rootId) {
            set("rootId", rootId);
            return this;
        }
    }
    /**
     * Called when shadow root is pushed into the element.
     * <p><b>Experimental:</b> this part of CDP may change without notice.
     */
    public static final class ShadowRootPushedEvent extends CdpObject {
        public ShadowRootPushedEvent() {}
        private ShadowRootPushedEvent(Map<String, Object> values) { super(values); }
        public static ShadowRootPushedEvent fromMap(Map<String, Object> values) {
            return new ShadowRootPushedEvent(values);
        }
        /**
         * Host element id.
         * @return the protocol field value
         */
        public DOM.NodeId hostId() {
            return new DOM.NodeId(((Number) require("hostId")).longValue());
        }
        /**
         * Shadow root.
         * @return the protocol field value
         */
        public DOM.Node root() {
            return java.util.Objects.requireNonNull(DOM.Node.fromMap(java.util.Objects.requireNonNull(CdpObject.objectMap(require("root")))));
        }
        /**
         * Host element id.
         * @param hostId field value
         * @return this model
         */
        public ShadowRootPushedEvent hostId(DOM.NodeId hostId) {
            set("hostId", hostId);
            return this;
        }
        /**
         * Shadow root.
         * @param root field value
         * @return this model
         */
        public ShadowRootPushedEvent root(DOM.Node root) {
            set("root", root);
            return this;
        }
    }
    /**
     * Whether to include whitespaces in the children array of returned Nodes.
     * <p><b>Experimental:</b> this part of CDP may change without notice.
     */
    public enum EnableIncludeWhitespaceValues implements CdpValue<String> {
        NONE("none"),
        ALL("all");
        public final String value;
        EnableIncludeWhitespaceValues(String value) { this.value = value; }
        @Nonnull public String value() { return value; }
        public static EnableIncludeWhitespaceValues of(@Nonnull String value) {
            for (EnableIncludeWhitespaceValues constant : values()) {
                if (constant.value.equals(value)) return constant;
            }
            throw new IllegalArgumentException("Unknown EnableIncludeWhitespaceValues value: " + value);
        }
    }
    /**
     * Type of relation to get.
     */
    public enum GetElementByRelationRelationValues implements CdpValue<String> {
        POPOVERTARGET("PopoverTarget"),
        INTERESTTARGET("InterestTarget"),
        COMMANDFOR("CommandFor");
        public final String value;
        GetElementByRelationRelationValues(String value) { this.value = value; }
        @Nonnull public String value() { return value; }
        public static GetElementByRelationRelationValues of(@Nonnull String value) {
            for (GetElementByRelationRelationValues constant : values()) {
                if (constant.value.equals(value)) return constant;
            }
            throw new IllegalArgumentException("Unknown GetElementByRelationRelationValues value: " + value);
        }
    }
    public static final class Client {
        private final CdpClient client;
        public Client(CdpClient client) { this.client = client; }
        /**
         * Collects class names for the node with given id and all of it&#x27;s child nodes.
         * <p><b>Experimental:</b> this part of CDP may change without notice.
         * @param nodeId protocol value
         * @return a stage completing with the command result
         */
        public CompletionStage<java.util.List<String>> collectClassNamesFromSubtree(DOM.NodeId nodeId) {
            Map<String, Object> params = new LinkedHashMap<>();
            params.put("nodeId", CdpObject.json(nodeId));
            return client.call("DOM.collectClassNamesFromSubtree", params, result_ -> CdpObject.requireList(java.util.Objects.requireNonNull(result_.get("classNames")), element0 -> (String) element0));
        }
        /**
         * Collects class names for the node with given id and all of it&#x27;s child nodes.
         * <p><b>Experimental:</b> this part of CDP may change without notice.
         * @param request request parameters
         * @return a stage completing with the command result
         */
        public CompletionStage<java.util.List<String>> collectClassNamesFromSubtree(CollectClassNamesFromSubtreeRequest request) {
            return client.call("DOM.collectClassNamesFromSubtree", request == null ? null : request.toMap(), result_ -> CdpObject.requireList(java.util.Objects.requireNonNull(result_.get("classNames")), element0 -> (String) element0));
        }
        /**
         * Creates a deep copy of the specified node and places it into the target container before the given anchor.
         * <p><b>Experimental:</b> this part of CDP may change without notice.
         * @param nodeId protocol value
         * @param targetNodeId protocol value
         * @param insertBeforeNodeId protocol value
         * @return a stage completing with the command result
         */
        public CompletionStage<DOM.NodeId> copyTo(DOM.NodeId nodeId, DOM.NodeId targetNodeId, Optional<DOM.NodeId> insertBeforeNodeId) {
            Map<String, Object> params = new LinkedHashMap<>();
            params.put("nodeId", CdpObject.json(nodeId));
            params.put("targetNodeId", CdpObject.json(targetNodeId));
            insertBeforeNodeId.ifPresent(value_ -> params.put("insertBeforeNodeId", CdpObject.json(value_)));
            return client.call("DOM.copyTo", params, result_ -> new DOM.NodeId(((Number) java.util.Objects.requireNonNull(result_.get("nodeId"))).longValue()));
        }
        /**
         * Creates a deep copy of the specified node and places it into the target container before the given anchor.
         * <p><b>Experimental:</b> this part of CDP may change without notice.
         * @param nodeId protocol value
         * @param targetNodeId protocol value
         * @return a stage completing with the command result
         */
        public CompletionStage<DOM.NodeId> copyTo(DOM.NodeId nodeId, DOM.NodeId targetNodeId) {
            return copyTo(nodeId, targetNodeId, Optional.empty());
        }
        /**
         * Creates a deep copy of the specified node and places it into the target container before the given anchor.
         * <p><b>Experimental:</b> this part of CDP may change without notice.
         * @param request request parameters
         * @return a stage completing with the command result
         */
        public CompletionStage<DOM.NodeId> copyTo(CopyToRequest request) {
            return client.call("DOM.copyTo", request == null ? null : request.toMap(), result_ -> new DOM.NodeId(((Number) java.util.Objects.requireNonNull(result_.get("nodeId"))).longValue()));
        }
        /**
         * Describes node given its id, does not require domain to be enabled. Does not start tracking any objects, can be used for automation.
         * @param nodeId protocol value
         * @param backendNodeId protocol value
         * @param objectId protocol value
         * @param depth protocol value
         * @param pierce protocol value
         * @return a stage completing with the command result
         */
        public CompletionStage<DOM.Node> describeNode(Optional<DOM.NodeId> nodeId, Optional<DOM.BackendNodeId> backendNodeId, Optional<Runtime.RemoteObjectId> objectId, OptionalLong depth, Optional<Boolean> pierce) {
            Map<String, Object> params = new LinkedHashMap<>();
            nodeId.ifPresent(value_ -> params.put("nodeId", CdpObject.json(value_)));
            backendNodeId.ifPresent(value_ -> params.put("backendNodeId", CdpObject.json(value_)));
            objectId.ifPresent(value_ -> params.put("objectId", CdpObject.json(value_)));
            depth.ifPresent(value_ -> params.put("depth", value_));
            pierce.ifPresent(value_ -> params.put("pierce", value_));
            return client.call("DOM.describeNode", params, result_ -> java.util.Objects.requireNonNull(DOM.Node.fromMap(java.util.Objects.requireNonNull(CdpObject.objectMap(java.util.Objects.requireNonNull(result_.get("node")))))));
        }
        /**
         * Describes node given its id, does not require domain to be enabled. Does not start tracking any objects, can be used for automation.
         * @return a stage completing with the command result
         */
        public CompletionStage<DOM.Node> describeNode() {
            return describeNode(Optional.empty(), Optional.empty(), Optional.empty(), OptionalLong.empty(), Optional.empty());
        }
        /**
         * Describes node given its id, does not require domain to be enabled. Does not start tracking any objects, can be used for automation.
         * @param request request parameters
         * @return a stage completing with the command result
         */
        public CompletionStage<DOM.Node> describeNode(DescribeNodeRequest request) {
            return client.call("DOM.describeNode", request == null ? null : request.toMap(), result_ -> java.util.Objects.requireNonNull(DOM.Node.fromMap(java.util.Objects.requireNonNull(CdpObject.objectMap(java.util.Objects.requireNonNull(result_.get("node")))))));
        }
        /**
         * Scrolls the specified rect of the given node into view if not already visible. Note: exactly one between nodeId, backendNodeId and objectId should be passed to identify the node.
         * @param nodeId protocol value
         * @param backendNodeId protocol value
         * @param objectId protocol value
         * @param rect protocol value
         * @return a stage completing when the command completes
         */
        public CompletionStage<Void> scrollIntoViewIfNeeded(Optional<DOM.NodeId> nodeId, Optional<DOM.BackendNodeId> backendNodeId, Optional<Runtime.RemoteObjectId> objectId, Optional<DOM.Rect> rect) {
            Map<String, Object> params = new LinkedHashMap<>();
            nodeId.ifPresent(value_ -> params.put("nodeId", CdpObject.json(value_)));
            backendNodeId.ifPresent(value_ -> params.put("backendNodeId", CdpObject.json(value_)));
            objectId.ifPresent(value_ -> params.put("objectId", CdpObject.json(value_)));
            rect.ifPresent(value_ -> params.put("rect", CdpObject.json(value_)));
            return client.call("DOM.scrollIntoViewIfNeeded", params, result_ -> null);
        }
        /**
         * Scrolls the specified rect of the given node into view if not already visible. Note: exactly one between nodeId, backendNodeId and objectId should be passed to identify the node.
         * @return a stage completing when the command completes
         */
        public CompletionStage<Void> scrollIntoViewIfNeeded() {
            return scrollIntoViewIfNeeded(Optional.empty(), Optional.empty(), Optional.empty(), Optional.empty());
        }
        /**
         * Scrolls the specified rect of the given node into view if not already visible. Note: exactly one between nodeId, backendNodeId and objectId should be passed to identify the node.
         * @param request request parameters
         * @return a stage completing when the command completes
         */
        public CompletionStage<Void> scrollIntoViewIfNeeded(ScrollIntoViewIfNeededRequest request) {
            return client.call("DOM.scrollIntoViewIfNeeded", request == null ? null : request.toMap(), result_ -> null);
        }
        /**
         * Disables DOM agent for the given page.
         * @return a stage completing when the command completes
         */
        public CompletionStage<Void> disable() {
            return client.call("DOM.disable", null, result_ -> null);
        }
        /**
         * Discards search results from the session with the given id. {@code getSearchResults} should no longer be called for that search.
         * <p><b>Experimental:</b> this part of CDP may change without notice.
         * @param searchId protocol value
         * @return a stage completing when the command completes
         */
        public CompletionStage<Void> discardSearchResults(String searchId) {
            Map<String, Object> params = new LinkedHashMap<>();
            params.put("searchId", CdpObject.json(searchId));
            return client.call("DOM.discardSearchResults", params, result_ -> null);
        }
        /**
         * Discards search results from the session with the given id. {@code getSearchResults} should no longer be called for that search.
         * <p><b>Experimental:</b> this part of CDP may change without notice.
         * @param request request parameters
         * @return a stage completing when the command completes
         */
        public CompletionStage<Void> discardSearchResults(DiscardSearchResultsRequest request) {
            return client.call("DOM.discardSearchResults", request == null ? null : request.toMap(), result_ -> null);
        }
        /**
         * Enables DOM agent for the given page.
         * @param includeWhitespace protocol value
         * @return a stage completing when the command completes
         */
        public CompletionStage<Void> enable(Optional<EnableIncludeWhitespaceValues> includeWhitespace) {
            Map<String, Object> params = new LinkedHashMap<>();
            includeWhitespace.ifPresent(value_ -> params.put("includeWhitespace", CdpObject.json(value_)));
            return client.call("DOM.enable", params, result_ -> null);
        }
        /**
         * Enables DOM agent for the given page.
         * @return a stage completing when the command completes
         */
        public CompletionStage<Void> enable() {
            return enable(Optional.empty());
        }
        /**
         * Enables DOM agent for the given page.
         * @param request request parameters
         * @return a stage completing when the command completes
         */
        public CompletionStage<Void> enable(EnableRequest request) {
            return client.call("DOM.enable", request == null ? null : request.toMap(), result_ -> null);
        }
        /**
         * Focuses the given element.
         * @param nodeId protocol value
         * @param backendNodeId protocol value
         * @param objectId protocol value
         * @return a stage completing when the command completes
         */
        public CompletionStage<Void> focus(Optional<DOM.NodeId> nodeId, Optional<DOM.BackendNodeId> backendNodeId, Optional<Runtime.RemoteObjectId> objectId) {
            Map<String, Object> params = new LinkedHashMap<>();
            nodeId.ifPresent(value_ -> params.put("nodeId", CdpObject.json(value_)));
            backendNodeId.ifPresent(value_ -> params.put("backendNodeId", CdpObject.json(value_)));
            objectId.ifPresent(value_ -> params.put("objectId", CdpObject.json(value_)));
            return client.call("DOM.focus", params, result_ -> null);
        }
        /**
         * Focuses the given element.
         * @return a stage completing when the command completes
         */
        public CompletionStage<Void> focus() {
            return focus(Optional.empty(), Optional.empty(), Optional.empty());
        }
        /**
         * Focuses the given element.
         * @param request request parameters
         * @return a stage completing when the command completes
         */
        public CompletionStage<Void> focus(FocusRequest request) {
            return client.call("DOM.focus", request == null ? null : request.toMap(), result_ -> null);
        }
        /**
         * Returns attributes for the specified node.
         * @param nodeId protocol value
         * @return a stage completing with the command result
         */
        public CompletionStage<java.util.List<String>> getAttributes(DOM.NodeId nodeId) {
            Map<String, Object> params = new LinkedHashMap<>();
            params.put("nodeId", CdpObject.json(nodeId));
            return client.call("DOM.getAttributes", params, result_ -> CdpObject.requireList(java.util.Objects.requireNonNull(result_.get("attributes")), element0 -> (String) element0));
        }
        /**
         * Returns attributes for the specified node.
         * @param request request parameters
         * @return a stage completing with the command result
         */
        public CompletionStage<java.util.List<String>> getAttributes(GetAttributesRequest request) {
            return client.call("DOM.getAttributes", request == null ? null : request.toMap(), result_ -> CdpObject.requireList(java.util.Objects.requireNonNull(result_.get("attributes")), element0 -> (String) element0));
        }
        /**
         * Returns boxes for the given node.
         * @param nodeId protocol value
         * @param backendNodeId protocol value
         * @param objectId protocol value
         * @return a stage completing with the command result
         */
        public CompletionStage<DOM.BoxModel> getBoxModel(Optional<DOM.NodeId> nodeId, Optional<DOM.BackendNodeId> backendNodeId, Optional<Runtime.RemoteObjectId> objectId) {
            Map<String, Object> params = new LinkedHashMap<>();
            nodeId.ifPresent(value_ -> params.put("nodeId", CdpObject.json(value_)));
            backendNodeId.ifPresent(value_ -> params.put("backendNodeId", CdpObject.json(value_)));
            objectId.ifPresent(value_ -> params.put("objectId", CdpObject.json(value_)));
            return client.call("DOM.getBoxModel", params, result_ -> java.util.Objects.requireNonNull(DOM.BoxModel.fromMap(java.util.Objects.requireNonNull(CdpObject.objectMap(java.util.Objects.requireNonNull(result_.get("model")))))));
        }
        /**
         * Returns boxes for the given node.
         * @return a stage completing with the command result
         */
        public CompletionStage<DOM.BoxModel> getBoxModel() {
            return getBoxModel(Optional.empty(), Optional.empty(), Optional.empty());
        }
        /**
         * Returns boxes for the given node.
         * @param request request parameters
         * @return a stage completing with the command result
         */
        public CompletionStage<DOM.BoxModel> getBoxModel(GetBoxModelRequest request) {
            return client.call("DOM.getBoxModel", request == null ? null : request.toMap(), result_ -> java.util.Objects.requireNonNull(DOM.BoxModel.fromMap(java.util.Objects.requireNonNull(CdpObject.objectMap(java.util.Objects.requireNonNull(result_.get("model")))))));
        }
        /**
         * Returns quads that describe node position on the page. This method might return multiple quads for inline nodes.
         * <p><b>Experimental:</b> this part of CDP may change without notice.
         * @param nodeId protocol value
         * @param backendNodeId protocol value
         * @param objectId protocol value
         * @return a stage completing with the command result
         */
        public CompletionStage<java.util.List<java.util.List<Double>>> getContentQuads(Optional<DOM.NodeId> nodeId, Optional<DOM.BackendNodeId> backendNodeId, Optional<Runtime.RemoteObjectId> objectId) {
            Map<String, Object> params = new LinkedHashMap<>();
            nodeId.ifPresent(value_ -> params.put("nodeId", CdpObject.json(value_)));
            backendNodeId.ifPresent(value_ -> params.put("backendNodeId", CdpObject.json(value_)));
            objectId.ifPresent(value_ -> params.put("objectId", CdpObject.json(value_)));
            return client.call("DOM.getContentQuads", params, result_ -> CdpObject.requireList(java.util.Objects.requireNonNull(result_.get("quads")), element0 -> CdpObject.requireList(element0, element1 -> ((Number) element1).doubleValue())));
        }
        /**
         * Returns quads that describe node position on the page. This method might return multiple quads for inline nodes.
         * <p><b>Experimental:</b> this part of CDP may change without notice.
         * @return a stage completing with the command result
         */
        public CompletionStage<java.util.List<java.util.List<Double>>> getContentQuads() {
            return getContentQuads(Optional.empty(), Optional.empty(), Optional.empty());
        }
        /**
         * Returns quads that describe node position on the page. This method might return multiple quads for inline nodes.
         * <p><b>Experimental:</b> this part of CDP may change without notice.
         * @param request request parameters
         * @return a stage completing with the command result
         */
        public CompletionStage<java.util.List<java.util.List<Double>>> getContentQuads(GetContentQuadsRequest request) {
            return client.call("DOM.getContentQuads", request == null ? null : request.toMap(), result_ -> CdpObject.requireList(java.util.Objects.requireNonNull(result_.get("quads")), element0 -> CdpObject.requireList(element0, element1 -> ((Number) element1).doubleValue())));
        }
        /**
         * Returns the root DOM node (and optionally the subtree) to the caller. Implicitly enables the DOM domain events for the current target.
         * @param depth protocol value
         * @param pierce protocol value
         * @return a stage completing with the command result
         */
        public CompletionStage<DOM.Node> getDocument(OptionalLong depth, Optional<Boolean> pierce) {
            Map<String, Object> params = new LinkedHashMap<>();
            depth.ifPresent(value_ -> params.put("depth", value_));
            pierce.ifPresent(value_ -> params.put("pierce", value_));
            return client.call("DOM.getDocument", params, result_ -> java.util.Objects.requireNonNull(DOM.Node.fromMap(java.util.Objects.requireNonNull(CdpObject.objectMap(java.util.Objects.requireNonNull(result_.get("root")))))));
        }
        /**
         * Returns the root DOM node (and optionally the subtree) to the caller. Implicitly enables the DOM domain events for the current target.
         * @return a stage completing with the command result
         */
        public CompletionStage<DOM.Node> getDocument() {
            return getDocument(OptionalLong.empty(), Optional.empty());
        }
        /**
         * Returns the root DOM node (and optionally the subtree) to the caller. Implicitly enables the DOM domain events for the current target.
         * @param request request parameters
         * @return a stage completing with the command result
         */
        public CompletionStage<DOM.Node> getDocument(GetDocumentRequest request) {
            return client.call("DOM.getDocument", request == null ? null : request.toMap(), result_ -> java.util.Objects.requireNonNull(DOM.Node.fromMap(java.util.Objects.requireNonNull(CdpObject.objectMap(java.util.Objects.requireNonNull(result_.get("root")))))));
        }
        /**
         * Returns the root DOM node (and optionally the subtree) to the caller. Deprecated, as it is not designed to work well with the rest of the DOM agent. Use DOMSnapshot.captureSnapshot instead.
         * @param depth protocol value
         * @param pierce protocol value
         * @return a stage completing with the command result
         * @deprecated Deprecated by the Chromium DevTools Protocol.
         */
        @Deprecated
        public CompletionStage<java.util.List<DOM.Node>> getFlattenedDocument(OptionalLong depth, Optional<Boolean> pierce) {
            Map<String, Object> params = new LinkedHashMap<>();
            depth.ifPresent(value_ -> params.put("depth", value_));
            pierce.ifPresent(value_ -> params.put("pierce", value_));
            return client.call("DOM.getFlattenedDocument", params, result_ -> CdpObject.requireList(java.util.Objects.requireNonNull(result_.get("nodes")), element0 -> java.util.Objects.requireNonNull(DOM.Node.fromMap(java.util.Objects.requireNonNull(CdpObject.objectMap(element0))))));
        }
        /**
         * Returns the root DOM node (and optionally the subtree) to the caller. Deprecated, as it is not designed to work well with the rest of the DOM agent. Use DOMSnapshot.captureSnapshot instead.
         * @return a stage completing with the command result
         * @deprecated Deprecated by the Chromium DevTools Protocol.
         */
        @Deprecated
        public CompletionStage<java.util.List<DOM.Node>> getFlattenedDocument() {
            return getFlattenedDocument(OptionalLong.empty(), Optional.empty());
        }
        /**
         * Returns the root DOM node (and optionally the subtree) to the caller. Deprecated, as it is not designed to work well with the rest of the DOM agent. Use DOMSnapshot.captureSnapshot instead.
         * @param request request parameters
         * @return a stage completing with the command result
         * @deprecated Deprecated by the Chromium DevTools Protocol.
         */
        @Deprecated
        public CompletionStage<java.util.List<DOM.Node>> getFlattenedDocument(GetFlattenedDocumentRequest request) {
            return client.call("DOM.getFlattenedDocument", request == null ? null : request.toMap(), result_ -> CdpObject.requireList(java.util.Objects.requireNonNull(result_.get("nodes")), element0 -> java.util.Objects.requireNonNull(DOM.Node.fromMap(java.util.Objects.requireNonNull(CdpObject.objectMap(element0))))));
        }
        /**
         * Finds nodes with a given computed style in a subtree.
         * <p><b>Experimental:</b> this part of CDP may change without notice.
         * @param nodeId protocol value
         * @param computedStyles protocol value
         * @param pierce protocol value
         * @return a stage completing with the command result
         */
        public CompletionStage<java.util.List<DOM.NodeId>> getNodesForSubtreeByStyle(DOM.NodeId nodeId, java.util.List<DOM.CSSComputedStyleProperty> computedStyles, Optional<Boolean> pierce) {
            Map<String, Object> params = new LinkedHashMap<>();
            params.put("nodeId", CdpObject.json(nodeId));
            params.put("computedStyles", CdpObject.json(computedStyles));
            pierce.ifPresent(value_ -> params.put("pierce", value_));
            return client.call("DOM.getNodesForSubtreeByStyle", params, result_ -> CdpObject.requireList(java.util.Objects.requireNonNull(result_.get("nodeIds")), element0 -> new DOM.NodeId(((Number) element0).longValue())));
        }
        /**
         * Finds nodes with a given computed style in a subtree.
         * <p><b>Experimental:</b> this part of CDP may change without notice.
         * @param nodeId protocol value
         * @param computedStyles protocol value
         * @return a stage completing with the command result
         */
        public CompletionStage<java.util.List<DOM.NodeId>> getNodesForSubtreeByStyle(DOM.NodeId nodeId, java.util.List<DOM.CSSComputedStyleProperty> computedStyles) {
            return getNodesForSubtreeByStyle(nodeId, computedStyles, Optional.empty());
        }
        /**
         * Finds nodes with a given computed style in a subtree.
         * <p><b>Experimental:</b> this part of CDP may change without notice.
         * @param request request parameters
         * @return a stage completing with the command result
         */
        public CompletionStage<java.util.List<DOM.NodeId>> getNodesForSubtreeByStyle(GetNodesForSubtreeByStyleRequest request) {
            return client.call("DOM.getNodesForSubtreeByStyle", request == null ? null : request.toMap(), result_ -> CdpObject.requireList(java.util.Objects.requireNonNull(result_.get("nodeIds")), element0 -> new DOM.NodeId(((Number) element0).longValue())));
        }
        /**
         * Returns node id at given location. Depending on whether DOM domain is enabled, nodeId is either returned or not.
         * @param x protocol value
         * @param y protocol value
         * @param includeUserAgentShadowDOM protocol value
         * @param ignorePointerEventsNone protocol value
         * @return a stage completing with the command result
         */
        public CompletionStage<GetNodeForLocationResult> getNodeForLocation(long x, long y, Optional<Boolean> includeUserAgentShadowDOM, Optional<Boolean> ignorePointerEventsNone) {
            Map<String, Object> params = new LinkedHashMap<>();
            params.put("x", CdpObject.json(x));
            params.put("y", CdpObject.json(y));
            includeUserAgentShadowDOM.ifPresent(value_ -> params.put("includeUserAgentShadowDOM", value_));
            ignorePointerEventsNone.ifPresent(value_ -> params.put("ignorePointerEventsNone", value_));
            return client.call("DOM.getNodeForLocation", params, result_ -> new GetNodeForLocationResult(result_));
        }
        /**
         * Returns node id at given location. Depending on whether DOM domain is enabled, nodeId is either returned or not.
         * @param x protocol value
         * @param y protocol value
         * @return a stage completing with the command result
         */
        public CompletionStage<GetNodeForLocationResult> getNodeForLocation(long x, long y) {
            return getNodeForLocation(x, y, Optional.empty(), Optional.empty());
        }
        /**
         * Returns node id at given location. Depending on whether DOM domain is enabled, nodeId is either returned or not.
         * @param request request parameters
         * @return a stage completing with the command result
         */
        public CompletionStage<GetNodeForLocationResult> getNodeForLocation(GetNodeForLocationRequest request) {
            return client.call("DOM.getNodeForLocation", request == null ? null : request.toMap(), result_ -> new GetNodeForLocationResult(result_));
        }
        /**
         * Returns node&#x27;s HTML markup.
         * @param nodeId protocol value
         * @param backendNodeId protocol value
         * @param objectId protocol value
         * @param includeShadowDOM protocol value
         * @return a stage completing with the command result
         */
        public CompletionStage<String> getOuterHTML(Optional<DOM.NodeId> nodeId, Optional<DOM.BackendNodeId> backendNodeId, Optional<Runtime.RemoteObjectId> objectId, Optional<Boolean> includeShadowDOM) {
            Map<String, Object> params = new LinkedHashMap<>();
            nodeId.ifPresent(value_ -> params.put("nodeId", CdpObject.json(value_)));
            backendNodeId.ifPresent(value_ -> params.put("backendNodeId", CdpObject.json(value_)));
            objectId.ifPresent(value_ -> params.put("objectId", CdpObject.json(value_)));
            includeShadowDOM.ifPresent(value_ -> params.put("includeShadowDOM", value_));
            return client.call("DOM.getOuterHTML", params, result_ -> (String) java.util.Objects.requireNonNull(result_.get("outerHTML")));
        }
        /**
         * Returns node&#x27;s HTML markup.
         * @return a stage completing with the command result
         */
        public CompletionStage<String> getOuterHTML() {
            return getOuterHTML(Optional.empty(), Optional.empty(), Optional.empty(), Optional.empty());
        }
        /**
         * Returns node&#x27;s HTML markup.
         * @param request request parameters
         * @return a stage completing with the command result
         */
        public CompletionStage<String> getOuterHTML(GetOuterHTMLRequest request) {
            return client.call("DOM.getOuterHTML", request == null ? null : request.toMap(), result_ -> (String) java.util.Objects.requireNonNull(result_.get("outerHTML")));
        }
        /**
         * Returns the id of the nearest ancestor that is a relayout boundary.
         * <p><b>Experimental:</b> this part of CDP may change without notice.
         * @param nodeId protocol value
         * @return a stage completing with the command result
         */
        public CompletionStage<DOM.NodeId> getRelayoutBoundary(DOM.NodeId nodeId) {
            Map<String, Object> params = new LinkedHashMap<>();
            params.put("nodeId", CdpObject.json(nodeId));
            return client.call("DOM.getRelayoutBoundary", params, result_ -> new DOM.NodeId(((Number) java.util.Objects.requireNonNull(result_.get("nodeId"))).longValue()));
        }
        /**
         * Returns the id of the nearest ancestor that is a relayout boundary.
         * <p><b>Experimental:</b> this part of CDP may change without notice.
         * @param request request parameters
         * @return a stage completing with the command result
         */
        public CompletionStage<DOM.NodeId> getRelayoutBoundary(GetRelayoutBoundaryRequest request) {
            return client.call("DOM.getRelayoutBoundary", request == null ? null : request.toMap(), result_ -> new DOM.NodeId(((Number) java.util.Objects.requireNonNull(result_.get("nodeId"))).longValue()));
        }
        /**
         * Returns search results from given {@code fromIndex} to given {@code toIndex} from the search with the given identifier.
         * <p><b>Experimental:</b> this part of CDP may change without notice.
         * @param searchId protocol value
         * @param fromIndex protocol value
         * @param toIndex protocol value
         * @return a stage completing with the command result
         */
        public CompletionStage<java.util.List<DOM.NodeId>> getSearchResults(String searchId, long fromIndex, long toIndex) {
            Map<String, Object> params = new LinkedHashMap<>();
            params.put("searchId", CdpObject.json(searchId));
            params.put("fromIndex", CdpObject.json(fromIndex));
            params.put("toIndex", CdpObject.json(toIndex));
            return client.call("DOM.getSearchResults", params, result_ -> CdpObject.requireList(java.util.Objects.requireNonNull(result_.get("nodeIds")), element0 -> new DOM.NodeId(((Number) element0).longValue())));
        }
        /**
         * Returns search results from given {@code fromIndex} to given {@code toIndex} from the search with the given identifier.
         * <p><b>Experimental:</b> this part of CDP may change without notice.
         * @param request request parameters
         * @return a stage completing with the command result
         */
        public CompletionStage<java.util.List<DOM.NodeId>> getSearchResults(GetSearchResultsRequest request) {
            return client.call("DOM.getSearchResults", request == null ? null : request.toMap(), result_ -> CdpObject.requireList(java.util.Objects.requireNonNull(result_.get("nodeIds")), element0 -> new DOM.NodeId(((Number) element0).longValue())));
        }
        /**
         * Hides any highlight.
         * @return a stage completing when the command completes
         */
        public CompletionStage<Void> hideHighlight() {
            return client.call("DOM.hideHighlight", null, result_ -> null);
        }
        /**
         * Highlights DOM node.
         * @return a stage completing when the command completes
         */
        public CompletionStage<Void> highlightNode() {
            return client.call("DOM.highlightNode", null, result_ -> null);
        }
        /**
         * Highlights given rectangle.
         * @return a stage completing when the command completes
         */
        public CompletionStage<Void> highlightRect() {
            return client.call("DOM.highlightRect", null, result_ -> null);
        }
        /**
         * Marks last undoable state.
         * <p><b>Experimental:</b> this part of CDP may change without notice.
         * @return a stage completing when the command completes
         */
        public CompletionStage<Void> markUndoableState() {
            return client.call("DOM.markUndoableState", null, result_ -> null);
        }
        /**
         * Moves node into the new container, places it before the given anchor.
         * @param nodeId protocol value
         * @param targetNodeId protocol value
         * @param insertBeforeNodeId protocol value
         * @return a stage completing with the command result
         */
        public CompletionStage<DOM.NodeId> moveTo(DOM.NodeId nodeId, DOM.NodeId targetNodeId, Optional<DOM.NodeId> insertBeforeNodeId) {
            Map<String, Object> params = new LinkedHashMap<>();
            params.put("nodeId", CdpObject.json(nodeId));
            params.put("targetNodeId", CdpObject.json(targetNodeId));
            insertBeforeNodeId.ifPresent(value_ -> params.put("insertBeforeNodeId", CdpObject.json(value_)));
            return client.call("DOM.moveTo", params, result_ -> new DOM.NodeId(((Number) java.util.Objects.requireNonNull(result_.get("nodeId"))).longValue()));
        }
        /**
         * Moves node into the new container, places it before the given anchor.
         * @param nodeId protocol value
         * @param targetNodeId protocol value
         * @return a stage completing with the command result
         */
        public CompletionStage<DOM.NodeId> moveTo(DOM.NodeId nodeId, DOM.NodeId targetNodeId) {
            return moveTo(nodeId, targetNodeId, Optional.empty());
        }
        /**
         * Moves node into the new container, places it before the given anchor.
         * @param request request parameters
         * @return a stage completing with the command result
         */
        public CompletionStage<DOM.NodeId> moveTo(MoveToRequest request) {
            return client.call("DOM.moveTo", request == null ? null : request.toMap(), result_ -> new DOM.NodeId(((Number) java.util.Objects.requireNonNull(result_.get("nodeId"))).longValue()));
        }
        /**
         * Searches for a given string in the DOM tree. Use {@code getSearchResults} to access search results or {@code cancelSearch} to end this search session.
         * <p><b>Experimental:</b> this part of CDP may change without notice.
         * @param query protocol value
         * @param includeUserAgentShadowDOM protocol value
         * @return a stage completing with the command result
         */
        public CompletionStage<PerformSearchResult> performSearch(String query, Optional<Boolean> includeUserAgentShadowDOM) {
            Map<String, Object> params = new LinkedHashMap<>();
            params.put("query", CdpObject.json(query));
            includeUserAgentShadowDOM.ifPresent(value_ -> params.put("includeUserAgentShadowDOM", value_));
            return client.call("DOM.performSearch", params, result_ -> new PerformSearchResult(result_));
        }
        /**
         * Searches for a given string in the DOM tree. Use {@code getSearchResults} to access search results or {@code cancelSearch} to end this search session.
         * <p><b>Experimental:</b> this part of CDP may change without notice.
         * @param query protocol value
         * @return a stage completing with the command result
         */
        public CompletionStage<PerformSearchResult> performSearch(String query) {
            return performSearch(query, Optional.empty());
        }
        /**
         * Searches for a given string in the DOM tree. Use {@code getSearchResults} to access search results or {@code cancelSearch} to end this search session.
         * <p><b>Experimental:</b> this part of CDP may change without notice.
         * @param request request parameters
         * @return a stage completing with the command result
         */
        public CompletionStage<PerformSearchResult> performSearch(PerformSearchRequest request) {
            return client.call("DOM.performSearch", request == null ? null : request.toMap(), result_ -> new PerformSearchResult(result_));
        }
        /**
         * Requests that the node is sent to the caller given its path. // FIXME, use XPath
         * <p><b>Experimental:</b> this part of CDP may change without notice.
         * @param path protocol value
         * @return a stage completing with the command result
         */
        public CompletionStage<DOM.NodeId> pushNodeByPathToFrontend(String path) {
            Map<String, Object> params = new LinkedHashMap<>();
            params.put("path", CdpObject.json(path));
            return client.call("DOM.pushNodeByPathToFrontend", params, result_ -> new DOM.NodeId(((Number) java.util.Objects.requireNonNull(result_.get("nodeId"))).longValue()));
        }
        /**
         * Requests that the node is sent to the caller given its path. // FIXME, use XPath
         * <p><b>Experimental:</b> this part of CDP may change without notice.
         * @param request request parameters
         * @return a stage completing with the command result
         */
        public CompletionStage<DOM.NodeId> pushNodeByPathToFrontend(PushNodeByPathToFrontendRequest request) {
            return client.call("DOM.pushNodeByPathToFrontend", request == null ? null : request.toMap(), result_ -> new DOM.NodeId(((Number) java.util.Objects.requireNonNull(result_.get("nodeId"))).longValue()));
        }
        /**
         * Requests that a batch of nodes is sent to the caller given their backend node ids.
         * <p><b>Experimental:</b> this part of CDP may change without notice.
         * @param backendNodeIds protocol value
         * @return a stage completing with the command result
         */
        public CompletionStage<java.util.List<DOM.NodeId>> pushNodesByBackendIdsToFrontend(java.util.List<DOM.BackendNodeId> backendNodeIds) {
            Map<String, Object> params = new LinkedHashMap<>();
            params.put("backendNodeIds", CdpObject.json(backendNodeIds));
            return client.call("DOM.pushNodesByBackendIdsToFrontend", params, result_ -> CdpObject.requireList(java.util.Objects.requireNonNull(result_.get("nodeIds")), element0 -> new DOM.NodeId(((Number) element0).longValue())));
        }
        /**
         * Requests that a batch of nodes is sent to the caller given their backend node ids.
         * <p><b>Experimental:</b> this part of CDP may change without notice.
         * @param request request parameters
         * @return a stage completing with the command result
         */
        public CompletionStage<java.util.List<DOM.NodeId>> pushNodesByBackendIdsToFrontend(PushNodesByBackendIdsToFrontendRequest request) {
            return client.call("DOM.pushNodesByBackendIdsToFrontend", request == null ? null : request.toMap(), result_ -> CdpObject.requireList(java.util.Objects.requireNonNull(result_.get("nodeIds")), element0 -> new DOM.NodeId(((Number) element0).longValue())));
        }
        /**
         * Executes {@code querySelector} on a given node.
         * @param nodeId protocol value
         * @param selector protocol value
         * @return a stage completing with the command result
         */
        public CompletionStage<DOM.NodeId> querySelector(DOM.NodeId nodeId, String selector) {
            Map<String, Object> params = new LinkedHashMap<>();
            params.put("nodeId", CdpObject.json(nodeId));
            params.put("selector", CdpObject.json(selector));
            return client.call("DOM.querySelector", params, result_ -> new DOM.NodeId(((Number) java.util.Objects.requireNonNull(result_.get("nodeId"))).longValue()));
        }
        /**
         * Executes {@code querySelector} on a given node.
         * @param request request parameters
         * @return a stage completing with the command result
         */
        public CompletionStage<DOM.NodeId> querySelector(QuerySelectorRequest request) {
            return client.call("DOM.querySelector", request == null ? null : request.toMap(), result_ -> new DOM.NodeId(((Number) java.util.Objects.requireNonNull(result_.get("nodeId"))).longValue()));
        }
        /**
         * Executes {@code querySelectorAll} on a given node.
         * @param nodeId protocol value
         * @param selector protocol value
         * @return a stage completing with the command result
         */
        public CompletionStage<java.util.List<DOM.NodeId>> querySelectorAll(DOM.NodeId nodeId, String selector) {
            Map<String, Object> params = new LinkedHashMap<>();
            params.put("nodeId", CdpObject.json(nodeId));
            params.put("selector", CdpObject.json(selector));
            return client.call("DOM.querySelectorAll", params, result_ -> CdpObject.requireList(java.util.Objects.requireNonNull(result_.get("nodeIds")), element0 -> new DOM.NodeId(((Number) element0).longValue())));
        }
        /**
         * Executes {@code querySelectorAll} on a given node.
         * @param request request parameters
         * @return a stage completing with the command result
         */
        public CompletionStage<java.util.List<DOM.NodeId>> querySelectorAll(QuerySelectorAllRequest request) {
            return client.call("DOM.querySelectorAll", request == null ? null : request.toMap(), result_ -> CdpObject.requireList(java.util.Objects.requireNonNull(result_.get("nodeIds")), element0 -> new DOM.NodeId(((Number) element0).longValue())));
        }
        /**
         * Returns NodeIds of current top layer elements. Top layer is rendered closest to the user within a viewport, therefore its elements always appear on top of all other content.
         * <p><b>Experimental:</b> this part of CDP may change without notice.
         * @return a stage completing with the command result
         */
        public CompletionStage<java.util.List<DOM.NodeId>> getTopLayerElements() {
            return client.call("DOM.getTopLayerElements", null, result_ -> CdpObject.requireList(java.util.Objects.requireNonNull(result_.get("nodeIds")), element0 -> new DOM.NodeId(((Number) element0).longValue())));
        }
        /**
         * Returns the NodeId of the matched element according to certain relations.
         * <p><b>Experimental:</b> this part of CDP may change without notice.
         * @param nodeId protocol value
         * @param relation protocol value
         * @return a stage completing with the command result
         */
        public CompletionStage<DOM.NodeId> getElementByRelation(DOM.NodeId nodeId, GetElementByRelationRelationValues relation) {
            Map<String, Object> params = new LinkedHashMap<>();
            params.put("nodeId", CdpObject.json(nodeId));
            params.put("relation", CdpObject.json(relation));
            return client.call("DOM.getElementByRelation", params, result_ -> new DOM.NodeId(((Number) java.util.Objects.requireNonNull(result_.get("nodeId"))).longValue()));
        }
        /**
         * Returns the NodeId of the matched element according to certain relations.
         * <p><b>Experimental:</b> this part of CDP may change without notice.
         * @param request request parameters
         * @return a stage completing with the command result
         */
        public CompletionStage<DOM.NodeId> getElementByRelation(GetElementByRelationRequest request) {
            return client.call("DOM.getElementByRelation", request == null ? null : request.toMap(), result_ -> new DOM.NodeId(((Number) java.util.Objects.requireNonNull(result_.get("nodeId"))).longValue()));
        }
        /**
         * Re-does the last undone action.
         * <p><b>Experimental:</b> this part of CDP may change without notice.
         * @return a stage completing when the command completes
         */
        public CompletionStage<Void> redo() {
            return client.call("DOM.redo", null, result_ -> null);
        }
        /**
         * Removes attribute with given name from an element with given id.
         * @param nodeId protocol value
         * @param name protocol value
         * @return a stage completing when the command completes
         */
        public CompletionStage<Void> removeAttribute(DOM.NodeId nodeId, String name) {
            Map<String, Object> params = new LinkedHashMap<>();
            params.put("nodeId", CdpObject.json(nodeId));
            params.put("name", CdpObject.json(name));
            return client.call("DOM.removeAttribute", params, result_ -> null);
        }
        /**
         * Removes attribute with given name from an element with given id.
         * @param request request parameters
         * @return a stage completing when the command completes
         */
        public CompletionStage<Void> removeAttribute(RemoveAttributeRequest request) {
            return client.call("DOM.removeAttribute", request == null ? null : request.toMap(), result_ -> null);
        }
        /**
         * Removes node with given id.
         * @param nodeId protocol value
         * @return a stage completing when the command completes
         */
        public CompletionStage<Void> removeNode(DOM.NodeId nodeId) {
            Map<String, Object> params = new LinkedHashMap<>();
            params.put("nodeId", CdpObject.json(nodeId));
            return client.call("DOM.removeNode", params, result_ -> null);
        }
        /**
         * Removes node with given id.
         * @param request request parameters
         * @return a stage completing when the command completes
         */
        public CompletionStage<Void> removeNode(RemoveNodeRequest request) {
            return client.call("DOM.removeNode", request == null ? null : request.toMap(), result_ -> null);
        }
        /**
         * Requests that children of the node with given id are returned to the caller in form of {@code setChildNodes} events where not only immediate children are retrieved, but all children down to the specified depth.
         * @param nodeId protocol value
         * @param depth protocol value
         * @param pierce protocol value
         * @return a stage completing when the command completes
         */
        public CompletionStage<Void> requestChildNodes(DOM.NodeId nodeId, OptionalLong depth, Optional<Boolean> pierce) {
            Map<String, Object> params = new LinkedHashMap<>();
            params.put("nodeId", CdpObject.json(nodeId));
            depth.ifPresent(value_ -> params.put("depth", value_));
            pierce.ifPresent(value_ -> params.put("pierce", value_));
            return client.call("DOM.requestChildNodes", params, result_ -> null);
        }
        /**
         * Requests that children of the node with given id are returned to the caller in form of {@code setChildNodes} events where not only immediate children are retrieved, but all children down to the specified depth.
         * @param nodeId protocol value
         * @return a stage completing when the command completes
         */
        public CompletionStage<Void> requestChildNodes(DOM.NodeId nodeId) {
            return requestChildNodes(nodeId, OptionalLong.empty(), Optional.empty());
        }
        /**
         * Requests that children of the node with given id are returned to the caller in form of {@code setChildNodes} events where not only immediate children are retrieved, but all children down to the specified depth.
         * @param request request parameters
         * @return a stage completing when the command completes
         */
        public CompletionStage<Void> requestChildNodes(RequestChildNodesRequest request) {
            return client.call("DOM.requestChildNodes", request == null ? null : request.toMap(), result_ -> null);
        }
        /**
         * Requests that the node is sent to the caller given the JavaScript node object reference. All nodes that form the path from the node to the root are also sent to the client as a series of {@code setChildNodes} notifications.
         * @param objectId protocol value
         * @return a stage completing with the command result
         */
        public CompletionStage<DOM.NodeId> requestNode(Runtime.RemoteObjectId objectId) {
            Map<String, Object> params = new LinkedHashMap<>();
            params.put("objectId", CdpObject.json(objectId));
            return client.call("DOM.requestNode", params, result_ -> new DOM.NodeId(((Number) java.util.Objects.requireNonNull(result_.get("nodeId"))).longValue()));
        }
        /**
         * Requests that the node is sent to the caller given the JavaScript node object reference. All nodes that form the path from the node to the root are also sent to the client as a series of {@code setChildNodes} notifications.
         * @param request request parameters
         * @return a stage completing with the command result
         */
        public CompletionStage<DOM.NodeId> requestNode(RequestNodeRequest request) {
            return client.call("DOM.requestNode", request == null ? null : request.toMap(), result_ -> new DOM.NodeId(((Number) java.util.Objects.requireNonNull(result_.get("nodeId"))).longValue()));
        }
        /**
         * Resolves the JavaScript node object for a given NodeId or BackendNodeId.
         * @param nodeId protocol value
         * @param backendNodeId protocol value
         * @param objectGroup protocol value
         * @param executionContextId protocol value
         * @return a stage completing with the command result
         */
        public CompletionStage<Runtime.RemoteObject> resolveNode(Optional<DOM.NodeId> nodeId, Optional<DOM.BackendNodeId> backendNodeId, Optional<String> objectGroup, Optional<Runtime.ExecutionContextId> executionContextId) {
            Map<String, Object> params = new LinkedHashMap<>();
            nodeId.ifPresent(value_ -> params.put("nodeId", CdpObject.json(value_)));
            backendNodeId.ifPresent(value_ -> params.put("backendNodeId", CdpObject.json(value_)));
            objectGroup.ifPresent(value_ -> params.put("objectGroup", CdpObject.json(value_)));
            executionContextId.ifPresent(value_ -> params.put("executionContextId", CdpObject.json(value_)));
            return client.call("DOM.resolveNode", params, result_ -> java.util.Objects.requireNonNull(Runtime.RemoteObject.fromMap(java.util.Objects.requireNonNull(CdpObject.objectMap(java.util.Objects.requireNonNull(result_.get("object")))))));
        }
        /**
         * Resolves the JavaScript node object for a given NodeId or BackendNodeId.
         * @return a stage completing with the command result
         */
        public CompletionStage<Runtime.RemoteObject> resolveNode() {
            return resolveNode(Optional.empty(), Optional.empty(), Optional.empty(), Optional.empty());
        }
        /**
         * Resolves the JavaScript node object for a given NodeId or BackendNodeId.
         * @param request request parameters
         * @return a stage completing with the command result
         */
        public CompletionStage<Runtime.RemoteObject> resolveNode(ResolveNodeRequest request) {
            return client.call("DOM.resolveNode", request == null ? null : request.toMap(), result_ -> java.util.Objects.requireNonNull(Runtime.RemoteObject.fromMap(java.util.Objects.requireNonNull(CdpObject.objectMap(java.util.Objects.requireNonNull(result_.get("object")))))));
        }
        /**
         * Sets attribute for an element with given id.
         * @param nodeId protocol value
         * @param name protocol value
         * @param value protocol value
         * @return a stage completing when the command completes
         */
        public CompletionStage<Void> setAttributeValue(DOM.NodeId nodeId, String name, String value) {
            Map<String, Object> params = new LinkedHashMap<>();
            params.put("nodeId", CdpObject.json(nodeId));
            params.put("name", CdpObject.json(name));
            params.put("value", CdpObject.json(value));
            return client.call("DOM.setAttributeValue", params, result_ -> null);
        }
        /**
         * Sets attribute for an element with given id.
         * @param request request parameters
         * @return a stage completing when the command completes
         */
        public CompletionStage<Void> setAttributeValue(SetAttributeValueRequest request) {
            return client.call("DOM.setAttributeValue", request == null ? null : request.toMap(), result_ -> null);
        }
        /**
         * Sets attributes on element with given id. This method is useful when user edits some existing attribute value and types in several attribute name/value pairs.
         * @param nodeId protocol value
         * @param text protocol value
         * @param name protocol value
         * @return a stage completing when the command completes
         */
        public CompletionStage<Void> setAttributesAsText(DOM.NodeId nodeId, String text, Optional<String> name) {
            Map<String, Object> params = new LinkedHashMap<>();
            params.put("nodeId", CdpObject.json(nodeId));
            params.put("text", CdpObject.json(text));
            name.ifPresent(value_ -> params.put("name", CdpObject.json(value_)));
            return client.call("DOM.setAttributesAsText", params, result_ -> null);
        }
        /**
         * Sets attributes on element with given id. This method is useful when user edits some existing attribute value and types in several attribute name/value pairs.
         * @param nodeId protocol value
         * @param text protocol value
         * @return a stage completing when the command completes
         */
        public CompletionStage<Void> setAttributesAsText(DOM.NodeId nodeId, String text) {
            return setAttributesAsText(nodeId, text, Optional.empty());
        }
        /**
         * Sets attributes on element with given id. This method is useful when user edits some existing attribute value and types in several attribute name/value pairs.
         * @param request request parameters
         * @return a stage completing when the command completes
         */
        public CompletionStage<Void> setAttributesAsText(SetAttributesAsTextRequest request) {
            return client.call("DOM.setAttributesAsText", request == null ? null : request.toMap(), result_ -> null);
        }
        /**
         * Sets files for the given file input element.
         * @param files protocol value
         * @param nodeId protocol value
         * @param backendNodeId protocol value
         * @param objectId protocol value
         * @return a stage completing when the command completes
         */
        public CompletionStage<Void> setFileInputFiles(java.util.List<String> files, Optional<DOM.NodeId> nodeId, Optional<DOM.BackendNodeId> backendNodeId, Optional<Runtime.RemoteObjectId> objectId) {
            Map<String, Object> params = new LinkedHashMap<>();
            params.put("files", CdpObject.json(files));
            nodeId.ifPresent(value_ -> params.put("nodeId", CdpObject.json(value_)));
            backendNodeId.ifPresent(value_ -> params.put("backendNodeId", CdpObject.json(value_)));
            objectId.ifPresent(value_ -> params.put("objectId", CdpObject.json(value_)));
            return client.call("DOM.setFileInputFiles", params, result_ -> null);
        }
        /**
         * Sets files for the given file input element.
         * @param files protocol value
         * @return a stage completing when the command completes
         */
        public CompletionStage<Void> setFileInputFiles(java.util.List<String> files) {
            return setFileInputFiles(files, Optional.empty(), Optional.empty(), Optional.empty());
        }
        /**
         * Sets files for the given file input element.
         * @param request request parameters
         * @return a stage completing when the command completes
         */
        public CompletionStage<Void> setFileInputFiles(SetFileInputFilesRequest request) {
            return client.call("DOM.setFileInputFiles", request == null ? null : request.toMap(), result_ -> null);
        }
        /**
         * Sets if stack traces should be captured for Nodes. See {@code Node.getNodeStackTraces}. Default is disabled.
         * <p><b>Experimental:</b> this part of CDP may change without notice.
         * @param enable protocol value
         * @return a stage completing when the command completes
         */
        public CompletionStage<Void> setNodeStackTracesEnabled(boolean enable) {
            Map<String, Object> params = new LinkedHashMap<>();
            params.put("enable", CdpObject.json(enable));
            return client.call("DOM.setNodeStackTracesEnabled", params, result_ -> null);
        }
        /**
         * Sets if stack traces should be captured for Nodes. See {@code Node.getNodeStackTraces}. Default is disabled.
         * <p><b>Experimental:</b> this part of CDP may change without notice.
         * @param request request parameters
         * @return a stage completing when the command completes
         */
        public CompletionStage<Void> setNodeStackTracesEnabled(SetNodeStackTracesEnabledRequest request) {
            return client.call("DOM.setNodeStackTracesEnabled", request == null ? null : request.toMap(), result_ -> null);
        }
        /**
         * Gets stack traces associated with a Node. As of now, only provides stack trace for Node creation.
         * <p><b>Experimental:</b> this part of CDP may change without notice.
         * @param nodeId protocol value
         * @return a stage completing with the command result
         */
        public CompletionStage<Optional<Runtime.StackTrace>> getNodeStackTraces(DOM.NodeId nodeId) {
            Map<String, Object> params = new LinkedHashMap<>();
            params.put("nodeId", CdpObject.json(nodeId));
            return client.call("DOM.getNodeStackTraces", params, result_ -> Optional.ofNullable(result_.get("creation") == null ? null : Runtime.StackTrace.fromMap(java.util.Objects.requireNonNull(CdpObject.objectMap(result_.get("creation"))))));
        }
        /**
         * Gets stack traces associated with a Node. As of now, only provides stack trace for Node creation.
         * <p><b>Experimental:</b> this part of CDP may change without notice.
         * @param request request parameters
         * @return a stage completing with the command result
         */
        public CompletionStage<Optional<Runtime.StackTrace>> getNodeStackTraces(GetNodeStackTracesRequest request) {
            return client.call("DOM.getNodeStackTraces", request == null ? null : request.toMap(), result_ -> Optional.ofNullable(result_.get("creation") == null ? null : Runtime.StackTrace.fromMap(java.util.Objects.requireNonNull(CdpObject.objectMap(result_.get("creation"))))));
        }
        /**
         * Returns file information for the given File wrapper.
         * <p><b>Experimental:</b> this part of CDP may change without notice.
         * @param objectId protocol value
         * @return a stage completing with the command result
         */
        public CompletionStage<String> getFileInfo(Runtime.RemoteObjectId objectId) {
            Map<String, Object> params = new LinkedHashMap<>();
            params.put("objectId", CdpObject.json(objectId));
            return client.call("DOM.getFileInfo", params, result_ -> (String) java.util.Objects.requireNonNull(result_.get("path")));
        }
        /**
         * Returns file information for the given File wrapper.
         * <p><b>Experimental:</b> this part of CDP may change without notice.
         * @param request request parameters
         * @return a stage completing with the command result
         */
        public CompletionStage<String> getFileInfo(GetFileInfoRequest request) {
            return client.call("DOM.getFileInfo", request == null ? null : request.toMap(), result_ -> (String) java.util.Objects.requireNonNull(result_.get("path")));
        }
        /**
         * Returns list of detached nodes
         * <p><b>Experimental:</b> this part of CDP may change without notice.
         * @return a stage completing with the command result
         */
        public CompletionStage<java.util.List<DOM.DetachedElementInfo>> getDetachedDomNodes() {
            return client.call("DOM.getDetachedDomNodes", null, result_ -> CdpObject.requireList(java.util.Objects.requireNonNull(result_.get("detachedNodes")), element0 -> java.util.Objects.requireNonNull(DOM.DetachedElementInfo.fromMap(java.util.Objects.requireNonNull(CdpObject.objectMap(element0))))));
        }
        /**
         * Enables console to refer to the node with given id via $x (see Command Line API for more details $x functions).
         * <p><b>Experimental:</b> this part of CDP may change without notice.
         * @param nodeId protocol value
         * @return a stage completing when the command completes
         */
        public CompletionStage<Void> setInspectedNode(DOM.NodeId nodeId) {
            Map<String, Object> params = new LinkedHashMap<>();
            params.put("nodeId", CdpObject.json(nodeId));
            return client.call("DOM.setInspectedNode", params, result_ -> null);
        }
        /**
         * Enables console to refer to the node with given id via $x (see Command Line API for more details $x functions).
         * <p><b>Experimental:</b> this part of CDP may change without notice.
         * @param request request parameters
         * @return a stage completing when the command completes
         */
        public CompletionStage<Void> setInspectedNode(SetInspectedNodeRequest request) {
            return client.call("DOM.setInspectedNode", request == null ? null : request.toMap(), result_ -> null);
        }
        /**
         * Sets node name for a node with given id.
         * @param nodeId protocol value
         * @param name protocol value
         * @return a stage completing with the command result
         */
        public CompletionStage<DOM.NodeId> setNodeName(DOM.NodeId nodeId, String name) {
            Map<String, Object> params = new LinkedHashMap<>();
            params.put("nodeId", CdpObject.json(nodeId));
            params.put("name", CdpObject.json(name));
            return client.call("DOM.setNodeName", params, result_ -> new DOM.NodeId(((Number) java.util.Objects.requireNonNull(result_.get("nodeId"))).longValue()));
        }
        /**
         * Sets node name for a node with given id.
         * @param request request parameters
         * @return a stage completing with the command result
         */
        public CompletionStage<DOM.NodeId> setNodeName(SetNodeNameRequest request) {
            return client.call("DOM.setNodeName", request == null ? null : request.toMap(), result_ -> new DOM.NodeId(((Number) java.util.Objects.requireNonNull(result_.get("nodeId"))).longValue()));
        }
        /**
         * Sets node value for a node with given id.
         * @param nodeId protocol value
         * @param value protocol value
         * @return a stage completing when the command completes
         */
        public CompletionStage<Void> setNodeValue(DOM.NodeId nodeId, String value) {
            Map<String, Object> params = new LinkedHashMap<>();
            params.put("nodeId", CdpObject.json(nodeId));
            params.put("value", CdpObject.json(value));
            return client.call("DOM.setNodeValue", params, result_ -> null);
        }
        /**
         * Sets node value for a node with given id.
         * @param request request parameters
         * @return a stage completing when the command completes
         */
        public CompletionStage<Void> setNodeValue(SetNodeValueRequest request) {
            return client.call("DOM.setNodeValue", request == null ? null : request.toMap(), result_ -> null);
        }
        /**
         * Sets node HTML markup, returns new node id.
         * @param nodeId protocol value
         * @param outerHTML protocol value
         * @return a stage completing when the command completes
         */
        public CompletionStage<Void> setOuterHTML(DOM.NodeId nodeId, String outerHTML) {
            Map<String, Object> params = new LinkedHashMap<>();
            params.put("nodeId", CdpObject.json(nodeId));
            params.put("outerHTML", CdpObject.json(outerHTML));
            return client.call("DOM.setOuterHTML", params, result_ -> null);
        }
        /**
         * Sets node HTML markup, returns new node id.
         * @param request request parameters
         * @return a stage completing when the command completes
         */
        public CompletionStage<Void> setOuterHTML(SetOuterHTMLRequest request) {
            return client.call("DOM.setOuterHTML", request == null ? null : request.toMap(), result_ -> null);
        }
        /**
         * Undoes the last performed action.
         * <p><b>Experimental:</b> this part of CDP may change without notice.
         * @return a stage completing when the command completes
         */
        public CompletionStage<Void> undo() {
            return client.call("DOM.undo", null, result_ -> null);
        }
        /**
         * Returns iframe node that owns iframe with the given domain.
         * <p><b>Experimental:</b> this part of CDP may change without notice.
         * @param frameId protocol value
         * @return a stage completing with the command result
         */
        public CompletionStage<GetFrameOwnerResult> getFrameOwner(Page.FrameId frameId) {
            Map<String, Object> params = new LinkedHashMap<>();
            params.put("frameId", CdpObject.json(frameId));
            return client.call("DOM.getFrameOwner", params, result_ -> new GetFrameOwnerResult(result_));
        }
        /**
         * Returns iframe node that owns iframe with the given domain.
         * <p><b>Experimental:</b> this part of CDP may change without notice.
         * @param request request parameters
         * @return a stage completing with the command result
         */
        public CompletionStage<GetFrameOwnerResult> getFrameOwner(GetFrameOwnerRequest request) {
            return client.call("DOM.getFrameOwner", request == null ? null : request.toMap(), result_ -> new GetFrameOwnerResult(result_));
        }
        /**
         * Returns the query container of the given node based on container query conditions: containerName, physical and logical axes, and whether it queries scroll-state or anchored elements. If no axes are provided and queriesScrollState is false, the style container is returned, which is the direct parent or the closest element with a matching container-name.
         * <p><b>Experimental:</b> this part of CDP may change without notice.
         * @param nodeId protocol value
         * @param containerName protocol value
         * @param physicalAxes protocol value
         * @param logicalAxes protocol value
         * @param queriesScrollState protocol value
         * @param queriesAnchored protocol value
         * @return a stage completing with the command result
         */
        public CompletionStage<Optional<DOM.NodeId>> getContainerForNode(DOM.NodeId nodeId, Optional<String> containerName, Optional<DOM.PhysicalAxes> physicalAxes, Optional<DOM.LogicalAxes> logicalAxes, Optional<Boolean> queriesScrollState, Optional<Boolean> queriesAnchored) {
            Map<String, Object> params = new LinkedHashMap<>();
            params.put("nodeId", CdpObject.json(nodeId));
            containerName.ifPresent(value_ -> params.put("containerName", CdpObject.json(value_)));
            physicalAxes.ifPresent(value_ -> params.put("physicalAxes", CdpObject.json(value_)));
            logicalAxes.ifPresent(value_ -> params.put("logicalAxes", CdpObject.json(value_)));
            queriesScrollState.ifPresent(value_ -> params.put("queriesScrollState", value_));
            queriesAnchored.ifPresent(value_ -> params.put("queriesAnchored", value_));
            return client.call("DOM.getContainerForNode", params, result_ -> Optional.ofNullable(result_.get("nodeId") == null ? null : new DOM.NodeId(((Number) result_.get("nodeId")).longValue())));
        }
        /**
         * Returns the query container of the given node based on container query conditions: containerName, physical and logical axes, and whether it queries scroll-state or anchored elements. If no axes are provided and queriesScrollState is false, the style container is returned, which is the direct parent or the closest element with a matching container-name.
         * <p><b>Experimental:</b> this part of CDP may change without notice.
         * @param nodeId protocol value
         * @return a stage completing with the command result
         */
        public CompletionStage<Optional<DOM.NodeId>> getContainerForNode(DOM.NodeId nodeId) {
            return getContainerForNode(nodeId, Optional.empty(), Optional.empty(), Optional.empty(), Optional.empty(), Optional.empty());
        }
        /**
         * Returns the query container of the given node based on container query conditions: containerName, physical and logical axes, and whether it queries scroll-state or anchored elements. If no axes are provided and queriesScrollState is false, the style container is returned, which is the direct parent or the closest element with a matching container-name.
         * <p><b>Experimental:</b> this part of CDP may change without notice.
         * @param request request parameters
         * @return a stage completing with the command result
         */
        public CompletionStage<Optional<DOM.NodeId>> getContainerForNode(GetContainerForNodeRequest request) {
            return client.call("DOM.getContainerForNode", request == null ? null : request.toMap(), result_ -> Optional.ofNullable(result_.get("nodeId") == null ? null : new DOM.NodeId(((Number) result_.get("nodeId")).longValue())));
        }
        /**
         * Returns the descendants of a container query container that have container queries against this container.
         * <p><b>Experimental:</b> this part of CDP may change without notice.
         * @param nodeId protocol value
         * @return a stage completing with the command result
         */
        public CompletionStage<java.util.List<DOM.NodeId>> getQueryingDescendantsForContainer(DOM.NodeId nodeId) {
            Map<String, Object> params = new LinkedHashMap<>();
            params.put("nodeId", CdpObject.json(nodeId));
            return client.call("DOM.getQueryingDescendantsForContainer", params, result_ -> CdpObject.requireList(java.util.Objects.requireNonNull(result_.get("nodeIds")), element0 -> new DOM.NodeId(((Number) element0).longValue())));
        }
        /**
         * Returns the descendants of a container query container that have container queries against this container.
         * <p><b>Experimental:</b> this part of CDP may change without notice.
         * @param request request parameters
         * @return a stage completing with the command result
         */
        public CompletionStage<java.util.List<DOM.NodeId>> getQueryingDescendantsForContainer(GetQueryingDescendantsForContainerRequest request) {
            return client.call("DOM.getQueryingDescendantsForContainer", request == null ? null : request.toMap(), result_ -> CdpObject.requireList(java.util.Objects.requireNonNull(result_.get("nodeIds")), element0 -> new DOM.NodeId(((Number) element0).longValue())));
        }
        /**
         * Returns the target anchor element of the given anchor query according to https://www.w3.org/TR/css-anchor-position-1/#target.
         * <p><b>Experimental:</b> this part of CDP may change without notice.
         * @param nodeId protocol value
         * @param anchorSpecifier protocol value
         * @return a stage completing with the command result
         */
        public CompletionStage<DOM.NodeId> getAnchorElement(DOM.NodeId nodeId, Optional<String> anchorSpecifier) {
            Map<String, Object> params = new LinkedHashMap<>();
            params.put("nodeId", CdpObject.json(nodeId));
            anchorSpecifier.ifPresent(value_ -> params.put("anchorSpecifier", CdpObject.json(value_)));
            return client.call("DOM.getAnchorElement", params, result_ -> new DOM.NodeId(((Number) java.util.Objects.requireNonNull(result_.get("nodeId"))).longValue()));
        }
        /**
         * Returns the target anchor element of the given anchor query according to https://www.w3.org/TR/css-anchor-position-1/#target.
         * <p><b>Experimental:</b> this part of CDP may change without notice.
         * @param nodeId protocol value
         * @return a stage completing with the command result
         */
        public CompletionStage<DOM.NodeId> getAnchorElement(DOM.NodeId nodeId) {
            return getAnchorElement(nodeId, Optional.empty());
        }
        /**
         * Returns the target anchor element of the given anchor query according to https://www.w3.org/TR/css-anchor-position-1/#target.
         * <p><b>Experimental:</b> this part of CDP may change without notice.
         * @param request request parameters
         * @return a stage completing with the command result
         */
        public CompletionStage<DOM.NodeId> getAnchorElement(GetAnchorElementRequest request) {
            return client.call("DOM.getAnchorElement", request == null ? null : request.toMap(), result_ -> new DOM.NodeId(((Number) java.util.Objects.requireNonNull(result_.get("nodeId"))).longValue()));
        }
        /**
         * When enabling, this API force-opens the popover identified by nodeId and keeps it open until disabled.
         * <p><b>Experimental:</b> this part of CDP may change without notice.
         * @param nodeId protocol value
         * @param enable protocol value
         * @return a stage completing with the command result
         */
        public CompletionStage<java.util.List<DOM.NodeId>> forceShowPopover(DOM.NodeId nodeId, boolean enable) {
            Map<String, Object> params = new LinkedHashMap<>();
            params.put("nodeId", CdpObject.json(nodeId));
            params.put("enable", CdpObject.json(enable));
            return client.call("DOM.forceShowPopover", params, result_ -> CdpObject.requireList(java.util.Objects.requireNonNull(result_.get("nodeIds")), element0 -> new DOM.NodeId(((Number) element0).longValue())));
        }
        /**
         * When enabling, this API force-opens the popover identified by nodeId and keeps it open until disabled.
         * <p><b>Experimental:</b> this part of CDP may change without notice.
         * @param request request parameters
         * @return a stage completing with the command result
         */
        public CompletionStage<java.util.List<DOM.NodeId>> forceShowPopover(ForceShowPopoverRequest request) {
            return client.call("DOM.forceShowPopover", request == null ? null : request.toMap(), result_ -> CdpObject.requireList(java.util.Objects.requireNonNull(result_.get("nodeIds")), element0 -> new DOM.NodeId(((Number) element0).longValue())));
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
         * Fired when a node&#x27;s ad related state changes.
         * <p><b>Experimental:</b> this part of CDP may change without notice.
         * @param handler event callback
         * @return a removable subscription
         */
        public CdpSubscription onAdRelatedStateUpdated(Consumer<AdRelatedStateUpdatedEvent> handler) {
            return client.on("DOM.adRelatedStateUpdated", AdRelatedStateUpdatedEvent::fromMap, handler);
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
