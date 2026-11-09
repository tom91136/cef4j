// GENERATED - do not edit. Regenerate via: ./mvnw generate-sources -pl cef4j-cdp -Dcef.version=150.0.18+gdb11278+chromium-150.0.7871.213
package net.kurobako.cef4j.cdp.generated;

import java.util.Map;
import java.util.Optional;
import java.util.OptionalDouble;
import java.util.OptionalLong;
import java.util.concurrent.CompletionStage;
import java.util.function.Consumer;
import javax.annotation.processing.Generated;
import net.kurobako.cef4j.cdp.CdpClient;
import net.kurobako.cef4j.cdp.CdpDomainClient;
import net.kurobako.cef4j.cdp.CdpField;
import net.kurobako.cef4j.cdp.CdpObject;
import net.kurobako.cef4j.cdp.CdpSubscription;
import net.kurobako.cef4j.cdp.CdpValue;
import net.kurobako.cef4j.policy.NullableBoundary;
import org.jspecify.annotations.Nullable;

/**
 * This domain exposes DOM read/write operations. Each DOM Node is represented with its mirror object that has an {@code id}. This {@code id} can be used to get additional information on the Node, resolve it into the JavaScript object wrapper, etc. It is important that client receives DOM events only for the nodes that are known to the client. Backend keeps track of the nodes that were sent to the client and never sends the same node twice. It is client&#x27;s responsibility to collect information about the nodes that were sent to the client. Note that {@code iframe} owner elements will return corresponding document elements as their child nodes.
 * @see <a href="https://chromium.googlesource.com/chromium/src/+/refs/tags/150.0.7871.213/third_party/blink/public/devtools_protocol/domains/DOM.pdl">Pinned protocol source</a>
 */
@Generated("./mvnw generate-sources -pl cef4j-cdp -Dcef.version=150.0.18+gdb11278+chromium-150.0.7871.213")
@SuppressWarnings({"EscapedEntity", "InvalidParam", "JavaLangClash", "MissingSummary", "Unchecked", "UnusedMethod"})
@NullableBoundary("CDP wire objects use null for absent optional fields")
public final class DOM {
    private DOM() {}
    private static final CdpField<java.util.List<DOM.StyleSheetId>> ADOPTED_STYLE_SHEETS = CdpField.of("adoptedStyleSheets", value -> CdpObject.requireList(value, element0 -> new DOM.StyleSheetId((String) element0)));
    private static final CdpField<Network.AdProvenance> AD_PROVENANCE = CdpField.of("adProvenance", value -> java.util.Objects.requireNonNull(Network.AdProvenance.fromMap(CdpObject.requireObjectMap(value))));
    private static final CdpField<DOM.BackendNode> ASSIGNED_SLOT = CdpField.of("assignedSlot", value -> java.util.Objects.requireNonNull(DOM.BackendNode.fromMap(CdpObject.requireObjectMap(value))));
    private static final CdpField<java.util.List<String>> ATTRIBUTES = CdpField.of("attributes", value -> CdpObject.requireList(value, element0 -> (String) element0));
    private static final CdpField<DOM.BackendNodeId> BACKEND_NODE_ID = CdpField.of("backendNodeId", value -> new DOM.BackendNodeId(((Number) value).longValue()));
    private static final CdpField<java.util.List<DOM.BackendNodeId>> BACKEND_NODE_IDS = CdpField.of("backendNodeIds", value -> CdpObject.requireList(value, element0 -> new DOM.BackendNodeId(((Number) element0).longValue())));
    private static final CdpField<java.util.List<Double>> BORDER = CdpField.of("border", value -> CdpObject.requireList(value, element0 -> ((Number) element0).doubleValue()));
    private static final CdpField<java.util.List<Double>> BOUNDS = CdpField.of("bounds", value -> CdpObject.requireList(value, element0 -> ((Number) element0).doubleValue()));
    private static final CdpField<java.util.List<DOM.Node>> CHILDREN = CdpField.of("children", value -> CdpObject.requireList(value, element0 -> java.util.Objects.requireNonNull(DOM.Node.fromMap(CdpObject.requireObjectMap(element0)))));
    private static final CdpField<java.util.List<String>> CLASS_NAMES = CdpField.of("classNames", value -> CdpObject.requireList(value, element0 -> (String) element0));
    private static final CdpField<DOM.CompatibilityMode> COMPATIBILITY_MODE = CdpField.of("compatibilityMode", value -> DOM.CompatibilityMode.of((String) value));
    private static final CdpField<java.util.List<DOM.CSSComputedStyleProperty>> COMPUTED_STYLES = CdpField.of("computedStyles", value -> CdpObject.requireList(value, element0 -> java.util.Objects.requireNonNull(DOM.CSSComputedStyleProperty.fromMap(CdpObject.requireObjectMap(element0)))));
    private static final CdpField<java.util.List<Double>> CONTENT = CdpField.of("content", value -> CdpObject.requireList(value, element0 -> ((Number) element0).doubleValue()));
    private static final CdpField<DOM.Node> CONTENT_DOCUMENT = CdpField.of("contentDocument", value -> java.util.Objects.requireNonNull(DOM.Node.fromMap(CdpObject.requireObjectMap(value))));
    private static final CdpField<Runtime.StackTrace> CREATION = CdpField.of("creation", value -> java.util.Objects.requireNonNull(Runtime.StackTrace.fromMap(CdpObject.requireObjectMap(value))));
    private static final CdpField<java.util.List<DOM.DetachedElementInfo>> DETACHED_NODES = CdpField.of("detachedNodes", value -> CdpObject.requireList(value, element0 -> java.util.Objects.requireNonNull(DOM.DetachedElementInfo.fromMap(CdpObject.requireObjectMap(element0)))));
    private static final CdpField<java.util.List<DOM.BackendNode>> DISTRIBUTED_NODES = CdpField.of("distributedNodes", value -> CdpObject.requireList(value, element0 -> java.util.Objects.requireNonNull(DOM.BackendNode.fromMap(CdpObject.requireObjectMap(element0)))));
    private static final CdpField<Runtime.ExecutionContextId> EXECUTION_CONTEXT_ID = CdpField.of("executionContextId", value -> new Runtime.ExecutionContextId(((Number) value).longValue()));
    private static final CdpField<java.util.List<String>> FILES = CdpField.of("files", value -> CdpObject.requireList(value, element0 -> (String) element0));
    private static final CdpField<Page.FrameId> FRAME_ID = CdpField.of("frameId", value -> new Page.FrameId((String) value));
    private static final CdpField<DOM.NodeId> HOST_ID = CdpField.of("hostId", value -> new DOM.NodeId(((Number) value).longValue()));
    private static final CdpField<DOM.Node> IMPORTED_DOCUMENT = CdpField.of("importedDocument", value -> java.util.Objects.requireNonNull(DOM.Node.fromMap(CdpObject.requireObjectMap(value))));
    private static final CdpField<EnableIncludeWhitespaceValues> INCLUDE_WHITESPACE = CdpField.of("includeWhitespace", value -> EnableIncludeWhitespaceValues.of((String) value));
    private static final CdpField<DOM.NodeId> INSERTION_POINT_ID = CdpField.of("insertionPointId", value -> new DOM.NodeId(((Number) value).longValue()));
    private static final CdpField<DOM.NodeId> INSERT_BEFORE_NODE_ID = CdpField.of("insertBeforeNodeId", value -> new DOM.NodeId(((Number) value).longValue()));
    private static final CdpField<DOM.LogicalAxes> LOGICAL_AXES = CdpField.of("logicalAxes", value -> DOM.LogicalAxes.of((String) value));
    private static final CdpField<java.util.List<Double>> MARGIN = CdpField.of("margin", value -> CdpObject.requireList(value, element0 -> ((Number) element0).doubleValue()));
    private static final CdpField<java.util.List<Object>> MARGIN_SHAPE = CdpField.of("marginShape", value -> CdpObject.requireList(value, element0 -> element0));
    private static final CdpField<DOM.BoxModel> MODEL = CdpField.of("model", value -> java.util.Objects.requireNonNull(DOM.BoxModel.fromMap(CdpObject.requireObjectMap(value))));
    private static final CdpField<DOM.Node> NODE = CdpField.of("node", value -> java.util.Objects.requireNonNull(DOM.Node.fromMap(CdpObject.requireObjectMap(value))));
    private static final CdpField<java.util.List<DOM.Node>> NODES = CdpField.of("nodes", value -> CdpObject.requireList(value, element0 -> java.util.Objects.requireNonNull(DOM.Node.fromMap(CdpObject.requireObjectMap(element0)))));
    private static final CdpField<DOM.NodeId> NODE_ID = CdpField.of("nodeId", value -> new DOM.NodeId(((Number) value).longValue()));
    private static final CdpField<java.util.List<DOM.NodeId>> NODE_IDS = CdpField.of("nodeIds", value -> CdpObject.requireList(value, element0 -> new DOM.NodeId(((Number) element0).longValue())));
    private static final CdpField<Runtime.RemoteObject> OBJECT = CdpField.of("object", value -> java.util.Objects.requireNonNull(Runtime.RemoteObject.fromMap(CdpObject.requireObjectMap(value))));
    private static final CdpField<Runtime.RemoteObjectId> OBJECT_ID = CdpField.of("objectId", value -> new Runtime.RemoteObjectId((String) value));
    private static final CdpField<String> OUTER_HTML = CdpField.of("outerHTML", value -> (String) value);
    private static final CdpField<java.util.List<Double>> PADDING = CdpField.of("padding", value -> CdpObject.requireList(value, element0 -> ((Number) element0).doubleValue()));
    private static final CdpField<DOM.NodeId> PARENT_ID = CdpField.of("parentId", value -> new DOM.NodeId(((Number) value).longValue()));
    private static final CdpField<DOM.NodeId> PARENT_NODE_ID = CdpField.of("parentNodeId", value -> new DOM.NodeId(((Number) value).longValue()));
    private static final CdpField<String> PATH = CdpField.of("path", value -> (String) value);
    private static final CdpField<DOM.PhysicalAxes> PHYSICAL_AXES = CdpField.of("physicalAxes", value -> DOM.PhysicalAxes.of((String) value));
    private static final CdpField<DOM.NodeId> PREVIOUS_NODE_ID = CdpField.of("previousNodeId", value -> new DOM.NodeId(((Number) value).longValue()));
    private static final CdpField<DOM.Node> PSEUDO_ELEMENT = CdpField.of("pseudoElement", value -> java.util.Objects.requireNonNull(DOM.Node.fromMap(CdpObject.requireObjectMap(value))));
    private static final CdpField<java.util.List<DOM.Node>> PSEUDO_ELEMENTS = CdpField.of("pseudoElements", value -> CdpObject.requireList(value, element0 -> java.util.Objects.requireNonNull(DOM.Node.fromMap(CdpObject.requireObjectMap(element0)))));
    private static final CdpField<DOM.NodeId> PSEUDO_ELEMENT_ID = CdpField.of("pseudoElementId", value -> new DOM.NodeId(((Number) value).longValue()));
    private static final CdpField<DOM.PseudoType> PSEUDO_TYPE = CdpField.of("pseudoType", value -> DOM.PseudoType.of((String) value));
    private static final CdpField<java.util.List<java.util.List<Double>>> QUADS = CdpField.of("quads", value -> CdpObject.requireList(value, element0 -> CdpObject.requireList(element0, element1 -> ((Number) element1).doubleValue())));
    private static final CdpField<DOM.Rect> RECT = CdpField.of("rect", value -> java.util.Objects.requireNonNull(DOM.Rect.fromMap(CdpObject.requireObjectMap(value))));
    private static final CdpField<GetElementByRelationRelationValues> RELATION = CdpField.of("relation", value -> GetElementByRelationRelationValues.of((String) value));
    private static final CdpField<java.util.List<DOM.NodeId>> RETAINED_NODE_IDS = CdpField.of("retainedNodeIds", value -> CdpObject.requireList(value, element0 -> new DOM.NodeId(((Number) element0).longValue())));
    private static final CdpField<DOM.Node> ROOT = CdpField.of("root", value -> java.util.Objects.requireNonNull(DOM.Node.fromMap(CdpObject.requireObjectMap(value))));
    private static final CdpField<DOM.NodeId> ROOT_ID = CdpField.of("rootId", value -> new DOM.NodeId(((Number) value).longValue()));
    private static final CdpField<java.util.List<DOM.Node>> SHADOW_ROOTS = CdpField.of("shadowRoots", value -> CdpObject.requireList(value, element0 -> java.util.Objects.requireNonNull(DOM.Node.fromMap(CdpObject.requireObjectMap(element0)))));
    private static final CdpField<DOM.ShadowRootType> SHADOW_ROOT_TYPE = CdpField.of("shadowRootType", value -> DOM.ShadowRootType.of((String) value));
    private static final CdpField<java.util.List<Object>> SHAPE = CdpField.of("shape", value -> CdpObject.requireList(value, element0 -> element0));
    private static final CdpField<DOM.ShapeOutsideInfo> SHAPE_OUTSIDE = CdpField.of("shapeOutside", value -> java.util.Objects.requireNonNull(DOM.ShapeOutsideInfo.fromMap(CdpObject.requireObjectMap(value))));
    private static final CdpField<DOM.NodeId> TARGET_NODE_ID = CdpField.of("targetNodeId", value -> new DOM.NodeId(((Number) value).longValue()));
    private static final CdpField<DOM.Node> TEMPLATE_CONTENT = CdpField.of("templateContent", value -> java.util.Objects.requireNonNull(DOM.Node.fromMap(CdpObject.requireObjectMap(value))));
    private static final CdpField<DOM.Node> TREE_NODE = CdpField.of("treeNode", value -> java.util.Objects.requireNonNull(DOM.Node.fromMap(CdpObject.requireObjectMap(value))));
    /**
     * Unique DOM node identifier.
     */
    public static final class NodeId implements CdpValue<Long> {
        public final long value;
        public NodeId(long value) { this.value = value; }
        @Override public Long value() { return value; }
        @Override public boolean equals(@org.jspecify.annotations.Nullable Object other) {
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
        @Override public Long value() { return value; }
        @Override public boolean equals(@org.jspecify.annotations.Nullable Object other) {
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
        public StyleSheetId(String value) { this.value = java.util.Objects.requireNonNull(value); }
        @Override public String value() { return value; }
        @Override public boolean equals(@org.jspecify.annotations.Nullable Object other) {
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
            return requireLong("nodeType");
        }
        /**
         * {@code Node}&#x27;s nodeName.
         * @return the protocol field value
         */
        public String nodeName() {
            return requireString("nodeName");
        }
        /**
         * Returns the backendNodeId field.
         * @return the protocol field value
         */
        public DOM.BackendNodeId backendNodeId() {
            return require(BACKEND_NODE_ID);
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
        @Override public String value() { return value; }
        public static PseudoType of(String value) {
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
        @Override public String value() { return value; }
        public static ShadowRootType of(String value) {
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
        @Override public String value() { return value; }
        public static CompatibilityMode of(String value) {
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
        @Override public String value() { return value; }
        public static PhysicalAxes of(String value) {
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
        @Override public String value() { return value; }
        public static LogicalAxes of(String value) {
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
        @Override public String value() { return value; }
        public static ScrollOrientation of(String value) {
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
            return require(NODE_ID);
        }
        /**
         * The id of the parent node if any.
         * @return the protocol field value, empty when absent
         */
        public Optional<DOM.NodeId> parentId() {
            return optional(PARENT_ID);
        }
        /**
         * The BackendNodeId for this node.
         * @return the protocol field value
         */
        public DOM.BackendNodeId backendNodeId() {
            return require(BACKEND_NODE_ID);
        }
        /**
         * {@code Node}&#x27;s nodeType.
         * @return the protocol field value
         */
        public long nodeType() {
            return requireLong("nodeType");
        }
        /**
         * {@code Node}&#x27;s nodeName.
         * @return the protocol field value
         */
        public String nodeName() {
            return requireString("nodeName");
        }
        /**
         * {@code Node}&#x27;s localName.
         * @return the protocol field value
         */
        public String localName() {
            return requireString("localName");
        }
        /**
         * {@code Node}&#x27;s nodeValue.
         * @return the protocol field value
         */
        public String nodeValue() {
            return requireString("nodeValue");
        }
        /**
         * Child count for {@code Container} nodes.
         * @return the protocol field value, empty when absent
         */
        public OptionalLong childNodeCount() {
            return optionalLong("childNodeCount");
        }
        /**
         * Child nodes of this node when requested with children.
         * @return the protocol field value, empty when absent
         */
        public Optional<java.util.List<DOM.Node>> children() {
            return optional(CHILDREN);
        }
        /**
         * Attributes of the {@code Element} node in the form of flat array {@code [name1, value1, name2, value2]}.
         * @return the protocol field value, empty when absent
         */
        public Optional<java.util.List<String>> attributes() {
            return optional(ATTRIBUTES);
        }
        /**
         * Document URL that {@code Document} or {@code FrameOwner} node points to.
         * @return the protocol field value, empty when absent
         */
        public Optional<String> documentURL() {
            return optionalString("documentURL");
        }
        /**
         * Base URL that {@code Document} or {@code FrameOwner} node uses for URL completion.
         * @return the protocol field value, empty when absent
         */
        public Optional<String> baseURL() {
            return optionalString("baseURL");
        }
        /**
         * {@code DocumentType}&#x27;s publicId.
         * @return the protocol field value, empty when absent
         */
        public Optional<String> publicId() {
            return optionalString("publicId");
        }
        /**
         * {@code DocumentType}&#x27;s systemId.
         * @return the protocol field value, empty when absent
         */
        public Optional<String> systemId() {
            return optionalString("systemId");
        }
        /**
         * {@code DocumentType}&#x27;s internalSubset.
         * @return the protocol field value, empty when absent
         */
        public Optional<String> internalSubset() {
            return optionalString("internalSubset");
        }
        /**
         * {@code Document}&#x27;s XML version in case of XML documents.
         * @return the protocol field value, empty when absent
         */
        public Optional<String> xmlVersion() {
            return optionalString("xmlVersion");
        }
        /**
         * {@code Attr}&#x27;s name.
         * @return the protocol field value, empty when absent
         */
        public Optional<String> name() {
            return optionalString("name");
        }
        /**
         * {@code Attr}&#x27;s value.
         * @return the protocol field value, empty when absent
         */
        public Optional<String> value() {
            return optionalString("value");
        }
        /**
         * Pseudo element type for this node.
         * @return the protocol field value, empty when absent
         */
        public Optional<DOM.PseudoType> pseudoType() {
            return optional(PSEUDO_TYPE);
        }
        /**
         * Pseudo element identifier for this node. Only present if there is a valid pseudoType.
         * @return the protocol field value, empty when absent
         */
        public Optional<String> pseudoIdentifier() {
            return optionalString("pseudoIdentifier");
        }
        /**
         * Shadow root type.
         * @return the protocol field value, empty when absent
         */
        public Optional<DOM.ShadowRootType> shadowRootType() {
            return optional(SHADOW_ROOT_TYPE);
        }
        /**
         * Frame ID for frame owner elements.
         * @return the protocol field value, empty when absent
         */
        public Optional<Page.FrameId> frameId() {
            return optional(FRAME_ID);
        }
        /**
         * Content document for frame owner elements.
         * @return the protocol field value, empty when absent
         */
        public Optional<DOM.Node> contentDocument() {
            return optional(CONTENT_DOCUMENT);
        }
        /**
         * Shadow root list for given element host.
         * @return the protocol field value, empty when absent
         */
        public Optional<java.util.List<DOM.Node>> shadowRoots() {
            return optional(SHADOW_ROOTS);
        }
        /**
         * Content document fragment for template elements.
         * @return the protocol field value, empty when absent
         */
        public Optional<DOM.Node> templateContent() {
            return optional(TEMPLATE_CONTENT);
        }
        /**
         * Pseudo elements associated with this node.
         * @return the protocol field value, empty when absent
         */
        public Optional<java.util.List<DOM.Node>> pseudoElements() {
            return optional(PSEUDO_ELEMENTS);
        }
        /**
         * Deprecated, as the HTML Imports API has been removed (crbug.com/937746). This property used to return the imported document for the HTMLImport links. The property is always undefined now.
         * @return the protocol field value, empty when absent
         * @deprecated Deprecated by the Chromium DevTools Protocol.
         */
        @Deprecated
        public Optional<DOM.Node> importedDocument() {
            return optional(IMPORTED_DOCUMENT);
        }
        /**
         * Distributed nodes for given insertion point.
         * @return the protocol field value, empty when absent
         */
        public Optional<java.util.List<DOM.BackendNode>> distributedNodes() {
            return optional(DISTRIBUTED_NODES);
        }
        /**
         * Whether the node is SVG.
         * @return the protocol field value, empty when absent
         */
        public Optional<Boolean> isSVG() {
            return optionalBoolean("isSVG");
        }
        /**
         * Returns the compatibilityMode field.
         * @return the protocol field value, empty when absent
         */
        public Optional<DOM.CompatibilityMode> compatibilityMode() {
            return optional(COMPATIBILITY_MODE);
        }
        /**
         * Returns the assignedSlot field.
         * @return the protocol field value, empty when absent
         */
        public Optional<DOM.BackendNode> assignedSlot() {
            return optional(ASSIGNED_SLOT);
        }
        /**
         * Returns the isScrollable field.
         * <p><b>Experimental:</b> this part of CDP may change without notice.
         * @return the protocol field value, empty when absent
         */
        public Optional<Boolean> isScrollable() {
            return optionalBoolean("isScrollable");
        }
        /**
         * Returns the affectedByStartingStyles field.
         * <p><b>Experimental:</b> this part of CDP may change without notice.
         * @return the protocol field value, empty when absent
         */
        public Optional<Boolean> affectedByStartingStyles() {
            return optionalBoolean("affectedByStartingStyles");
        }
        /**
         * Returns the adoptedStyleSheets field.
         * <p><b>Experimental:</b> this part of CDP may change without notice.
         * @return the protocol field value, empty when absent
         */
        public Optional<java.util.List<DOM.StyleSheetId>> adoptedStyleSheets() {
            return optional(ADOPTED_STYLE_SHEETS);
        }
        /**
         * Returns the adProvenance field.
         * <p><b>Experimental:</b> this part of CDP may change without notice.
         * @return the protocol field value, empty when absent
         */
        public Optional<Network.AdProvenance> adProvenance() {
            return optional(AD_PROVENANCE);
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
         * @param parentId field value
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
         * @param childNodeCount field value
         * @return this model
         */
        public Node childNodeCount(long childNodeCount) {
            set("childNodeCount", childNodeCount);
            return this;
        }
        /**
         * Child nodes of this node when requested with children.
         * @param children field value
         * @return this model
         */
        public Node children(java.util.List<DOM.Node> children) {
            set("children", children);
            return this;
        }
        /**
         * Attributes of the {@code Element} node in the form of flat array {@code [name1, value1, name2, value2]}.
         * @param attributes field value
         * @return this model
         */
        public Node attributes(java.util.List<String> attributes) {
            set("attributes", attributes);
            return this;
        }
        /**
         * Document URL that {@code Document} or {@code FrameOwner} node points to.
         * @param documentURL field value
         * @return this model
         */
        public Node documentURL(String documentURL) {
            set("documentURL", documentURL);
            return this;
        }
        /**
         * Base URL that {@code Document} or {@code FrameOwner} node uses for URL completion.
         * @param baseURL field value
         * @return this model
         */
        public Node baseURL(String baseURL) {
            set("baseURL", baseURL);
            return this;
        }
        /**
         * {@code DocumentType}&#x27;s publicId.
         * @param publicId field value
         * @return this model
         */
        public Node publicId(String publicId) {
            set("publicId", publicId);
            return this;
        }
        /**
         * {@code DocumentType}&#x27;s systemId.
         * @param systemId field value
         * @return this model
         */
        public Node systemId(String systemId) {
            set("systemId", systemId);
            return this;
        }
        /**
         * {@code DocumentType}&#x27;s internalSubset.
         * @param internalSubset field value
         * @return this model
         */
        public Node internalSubset(String internalSubset) {
            set("internalSubset", internalSubset);
            return this;
        }
        /**
         * {@code Document}&#x27;s XML version in case of XML documents.
         * @param xmlVersion field value
         * @return this model
         */
        public Node xmlVersion(String xmlVersion) {
            set("xmlVersion", xmlVersion);
            return this;
        }
        /**
         * {@code Attr}&#x27;s name.
         * @param name field value
         * @return this model
         */
        public Node name(String name) {
            set("name", name);
            return this;
        }
        /**
         * {@code Attr}&#x27;s value.
         * @param value field value
         * @return this model
         */
        public Node value(String value) {
            set("value", value);
            return this;
        }
        /**
         * Pseudo element type for this node.
         * @param pseudoType field value
         * @return this model
         */
        public Node pseudoType(DOM.PseudoType pseudoType) {
            set("pseudoType", pseudoType);
            return this;
        }
        /**
         * Pseudo element identifier for this node. Only present if there is a valid pseudoType.
         * @param pseudoIdentifier field value
         * @return this model
         */
        public Node pseudoIdentifier(String pseudoIdentifier) {
            set("pseudoIdentifier", pseudoIdentifier);
            return this;
        }
        /**
         * Shadow root type.
         * @param shadowRootType field value
         * @return this model
         */
        public Node shadowRootType(DOM.ShadowRootType shadowRootType) {
            set("shadowRootType", shadowRootType);
            return this;
        }
        /**
         * Frame ID for frame owner elements.
         * @param frameId field value
         * @return this model
         */
        public Node frameId(Page.FrameId frameId) {
            set("frameId", frameId);
            return this;
        }
        /**
         * Content document for frame owner elements.
         * @param contentDocument field value
         * @return this model
         */
        public Node contentDocument(DOM.Node contentDocument) {
            set("contentDocument", contentDocument);
            return this;
        }
        /**
         * Shadow root list for given element host.
         * @param shadowRoots field value
         * @return this model
         */
        public Node shadowRoots(java.util.List<DOM.Node> shadowRoots) {
            set("shadowRoots", shadowRoots);
            return this;
        }
        /**
         * Content document fragment for template elements.
         * @param templateContent field value
         * @return this model
         */
        public Node templateContent(DOM.Node templateContent) {
            set("templateContent", templateContent);
            return this;
        }
        /**
         * Pseudo elements associated with this node.
         * @param pseudoElements field value
         * @return this model
         */
        public Node pseudoElements(java.util.List<DOM.Node> pseudoElements) {
            set("pseudoElements", pseudoElements);
            return this;
        }
        /**
         * Deprecated, as the HTML Imports API has been removed (crbug.com/937746). This property used to return the imported document for the HTMLImport links. The property is always undefined now.
         * @param importedDocument field value
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
         * @param distributedNodes field value
         * @return this model
         */
        public Node distributedNodes(java.util.List<DOM.BackendNode> distributedNodes) {
            set("distributedNodes", distributedNodes);
            return this;
        }
        /**
         * Whether the node is SVG.
         * @param isSVG field value
         * @return this model
         */
        public Node isSVG(boolean isSVG) {
            set("isSVG", isSVG);
            return this;
        }
        /**
         * Sets the compatibilityMode field.
         * @param compatibilityMode field value
         * @return this model
         */
        public Node compatibilityMode(DOM.CompatibilityMode compatibilityMode) {
            set("compatibilityMode", compatibilityMode);
            return this;
        }
        /**
         * Sets the assignedSlot field.
         * @param assignedSlot field value
         * @return this model
         */
        public Node assignedSlot(DOM.BackendNode assignedSlot) {
            set("assignedSlot", assignedSlot);
            return this;
        }
        /**
         * Sets the isScrollable field.
         * <p><b>Experimental:</b> this part of CDP may change without notice.
         * @param isScrollable field value
         * @return this model
         */
        public Node isScrollable(boolean isScrollable) {
            set("isScrollable", isScrollable);
            return this;
        }
        /**
         * Sets the affectedByStartingStyles field.
         * <p><b>Experimental:</b> this part of CDP may change without notice.
         * @param affectedByStartingStyles field value
         * @return this model
         */
        public Node affectedByStartingStyles(boolean affectedByStartingStyles) {
            set("affectedByStartingStyles", affectedByStartingStyles);
            return this;
        }
        /**
         * Sets the adoptedStyleSheets field.
         * <p><b>Experimental:</b> this part of CDP may change without notice.
         * @param adoptedStyleSheets field value
         * @return this model
         */
        public Node adoptedStyleSheets(java.util.List<DOM.StyleSheetId> adoptedStyleSheets) {
            set("adoptedStyleSheets", adoptedStyleSheets);
            return this;
        }
        /**
         * Sets the adProvenance field.
         * <p><b>Experimental:</b> this part of CDP may change without notice.
         * @param adProvenance field value
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
            return require(TREE_NODE);
        }
        /**
         * Returns the retainedNodeIds field.
         * @return the protocol field value
         */
        public java.util.List<DOM.NodeId> retainedNodeIds() {
            return require(RETAINED_NODE_IDS);
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
            return requireLong("r");
        }
        /**
         * The green component, in the [0-255] range.
         * @return the protocol field value
         */
        public long g() {
            return requireLong("g");
        }
        /**
         * The blue component, in the [0-255] range.
         * @return the protocol field value
         */
        public long b() {
            return requireLong("b");
        }
        /**
         * The alpha component, in the [0-1] range (default: 1).
         * @return the protocol field value, empty when absent
         */
        public OptionalDouble a() {
            return optionalDouble("a");
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
         * @param a field value
         * @return this model
         */
        public RGBA a(double a) {
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
            return require(CONTENT);
        }
        /**
         * Padding box
         * @return the protocol field value
         */
        public java.util.List<Double> padding() {
            return require(PADDING);
        }
        /**
         * Border box
         * @return the protocol field value
         */
        public java.util.List<Double> border() {
            return require(BORDER);
        }
        /**
         * Margin box
         * @return the protocol field value
         */
        public java.util.List<Double> margin() {
            return require(MARGIN);
        }
        /**
         * Node width
         * @return the protocol field value
         */
        public long width() {
            return requireLong("width");
        }
        /**
         * Node height
         * @return the protocol field value
         */
        public long height() {
            return requireLong("height");
        }
        /**
         * Shape outside coordinates
         * @return the protocol field value, empty when absent
         */
        public Optional<DOM.ShapeOutsideInfo> shapeOutside() {
            return optional(SHAPE_OUTSIDE);
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
         * @param shapeOutside field value
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
            return require(BOUNDS);
        }
        /**
         * Shape coordinate details
         * @return the protocol field value
         */
        public java.util.List<Object> shape() {
            return require(SHAPE);
        }
        /**
         * Margin shape bounds
         * @return the protocol field value
         */
        public java.util.List<Object> marginShape() {
            return require(MARGIN_SHAPE);
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
            return requireDouble("x");
        }
        /**
         * Y coordinate
         * @return the protocol field value
         */
        public double y() {
            return requireDouble("y");
        }
        /**
         * Rectangle width
         * @return the protocol field value
         */
        public double width() {
            return requireDouble("width");
        }
        /**
         * Rectangle height
         * @return the protocol field value
         */
        public double height() {
            return requireDouble("height");
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
            return requireString("name");
        }
        /**
         * Computed style property value.
         * @return the protocol field value
         */
        public String value() {
            return requireString("value");
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
        public static CollectClassNamesFromSubtreeRequest fromMap(Map<String, Object> values) {
            final CollectClassNamesFromSubtreeRequest instance_ = new CollectClassNamesFromSubtreeRequest();
            if (values != null) instance_.values.putAll(values);
            return instance_;
        }
        /**
         * Id of the node to collect class names.
         * @return the protocol field value
         */
        public DOM.NodeId nodeId() {
            return require(NODE_ID);
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
        public static CopyToRequest fromMap(Map<String, Object> values) {
            final CopyToRequest instance_ = new CopyToRequest();
            if (values != null) instance_.values.putAll(values);
            return instance_;
        }
        /**
         * Id of the node to copy.
         * @return the protocol field value
         */
        public DOM.NodeId nodeId() {
            return require(NODE_ID);
        }
        /**
         * Id of the element to drop the copy into.
         * @return the protocol field value
         */
        public DOM.NodeId targetNodeId() {
            return require(TARGET_NODE_ID);
        }
        /**
         * Drop the copy before this node (if absent, the copy becomes the last child of {@code targetNodeId}).
         * @return the protocol field value, empty when absent
         */
        public Optional<DOM.NodeId> insertBeforeNodeId() {
            return optional(INSERT_BEFORE_NODE_ID);
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
         * @param insertBeforeNodeId field value
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
            final DescribeNodeRequest instance_ = new DescribeNodeRequest();
            if (values != null) instance_.values.putAll(values);
            return instance_;
        }
        /**
         * Identifier of the node.
         * @return the protocol field value, empty when absent
         */
        public Optional<DOM.NodeId> nodeId() {
            return optional(NODE_ID);
        }
        /**
         * Identifier of the backend node.
         * @return the protocol field value, empty when absent
         */
        public Optional<DOM.BackendNodeId> backendNodeId() {
            return optional(BACKEND_NODE_ID);
        }
        /**
         * JavaScript object id of the node wrapper.
         * @return the protocol field value, empty when absent
         */
        public Optional<Runtime.RemoteObjectId> objectId() {
            return optional(OBJECT_ID);
        }
        /**
         * The maximum depth at which children should be retrieved, defaults to 1. Use -1 for the entire subtree or provide an integer larger than 0.
         * @return the protocol field value, empty when absent
         */
        public OptionalLong depth() {
            return optionalLong("depth");
        }
        /**
         * Whether or not iframes and shadow roots should be traversed when returning the subtree (default is false).
         * @return the protocol field value, empty when absent
         */
        public Optional<Boolean> pierce() {
            return optionalBoolean("pierce");
        }
        /**
         * Identifier of the node.
         * @param nodeId field value
         * @return this model
         */
        public DescribeNodeRequest nodeId(DOM.NodeId nodeId) {
            set("nodeId", nodeId);
            return this;
        }
        /**
         * Identifier of the backend node.
         * @param backendNodeId field value
         * @return this model
         */
        public DescribeNodeRequest backendNodeId(DOM.BackendNodeId backendNodeId) {
            set("backendNodeId", backendNodeId);
            return this;
        }
        /**
         * JavaScript object id of the node wrapper.
         * @param objectId field value
         * @return this model
         */
        public DescribeNodeRequest objectId(Runtime.RemoteObjectId objectId) {
            set("objectId", objectId);
            return this;
        }
        /**
         * The maximum depth at which children should be retrieved, defaults to 1. Use -1 for the entire subtree or provide an integer larger than 0.
         * @param depth field value
         * @return this model
         */
        public DescribeNodeRequest depth(long depth) {
            set("depth", depth);
            return this;
        }
        /**
         * Whether or not iframes and shadow roots should be traversed when returning the subtree (default is false).
         * @param pierce field value
         * @return this model
         */
        public DescribeNodeRequest pierce(boolean pierce) {
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
            final ScrollIntoViewIfNeededRequest instance_ = new ScrollIntoViewIfNeededRequest();
            if (values != null) instance_.values.putAll(values);
            return instance_;
        }
        /**
         * Identifier of the node.
         * @return the protocol field value, empty when absent
         */
        public Optional<DOM.NodeId> nodeId() {
            return optional(NODE_ID);
        }
        /**
         * Identifier of the backend node.
         * @return the protocol field value, empty when absent
         */
        public Optional<DOM.BackendNodeId> backendNodeId() {
            return optional(BACKEND_NODE_ID);
        }
        /**
         * JavaScript object id of the node wrapper.
         * @return the protocol field value, empty when absent
         */
        public Optional<Runtime.RemoteObjectId> objectId() {
            return optional(OBJECT_ID);
        }
        /**
         * The rect to be scrolled into view, relative to the node&#x27;s border box, in CSS pixels. When omitted, center of the node will be used, similar to Element.scrollIntoView.
         * @return the protocol field value, empty when absent
         */
        public Optional<DOM.Rect> rect() {
            return optional(RECT);
        }
        /**
         * Identifier of the node.
         * @param nodeId field value
         * @return this model
         */
        public ScrollIntoViewIfNeededRequest nodeId(DOM.NodeId nodeId) {
            set("nodeId", nodeId);
            return this;
        }
        /**
         * Identifier of the backend node.
         * @param backendNodeId field value
         * @return this model
         */
        public ScrollIntoViewIfNeededRequest backendNodeId(DOM.BackendNodeId backendNodeId) {
            set("backendNodeId", backendNodeId);
            return this;
        }
        /**
         * JavaScript object id of the node wrapper.
         * @param objectId field value
         * @return this model
         */
        public ScrollIntoViewIfNeededRequest objectId(Runtime.RemoteObjectId objectId) {
            set("objectId", objectId);
            return this;
        }
        /**
         * The rect to be scrolled into view, relative to the node&#x27;s border box, in CSS pixels. When omitted, center of the node will be used, similar to Element.scrollIntoView.
         * @param rect field value
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
        public static DiscardSearchResultsRequest fromMap(Map<String, Object> values) {
            final DiscardSearchResultsRequest instance_ = new DiscardSearchResultsRequest();
            if (values != null) instance_.values.putAll(values);
            return instance_;
        }
        /**
         * Unique search session identifier.
         * @return the protocol field value
         */
        public String searchId() {
            return requireString("searchId");
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
            final EnableRequest instance_ = new EnableRequest();
            if (values != null) instance_.values.putAll(values);
            return instance_;
        }
        /**
         * Whether to include whitespaces in the children array of returned Nodes.
         * <p><b>Experimental:</b> this part of CDP may change without notice.
         * @return the protocol field value, empty when absent
         */
        public Optional<EnableIncludeWhitespaceValues> includeWhitespace() {
            return optional(INCLUDE_WHITESPACE);
        }
        /**
         * Whether to include whitespaces in the children array of returned Nodes.
         * <p><b>Experimental:</b> this part of CDP may change without notice.
         * @param includeWhitespace field value
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
            final FocusRequest instance_ = new FocusRequest();
            if (values != null) instance_.values.putAll(values);
            return instance_;
        }
        /**
         * Identifier of the node.
         * @return the protocol field value, empty when absent
         */
        public Optional<DOM.NodeId> nodeId() {
            return optional(NODE_ID);
        }
        /**
         * Identifier of the backend node.
         * @return the protocol field value, empty when absent
         */
        public Optional<DOM.BackendNodeId> backendNodeId() {
            return optional(BACKEND_NODE_ID);
        }
        /**
         * JavaScript object id of the node wrapper.
         * @return the protocol field value, empty when absent
         */
        public Optional<Runtime.RemoteObjectId> objectId() {
            return optional(OBJECT_ID);
        }
        /**
         * Identifier of the node.
         * @param nodeId field value
         * @return this model
         */
        public FocusRequest nodeId(DOM.NodeId nodeId) {
            set("nodeId", nodeId);
            return this;
        }
        /**
         * Identifier of the backend node.
         * @param backendNodeId field value
         * @return this model
         */
        public FocusRequest backendNodeId(DOM.BackendNodeId backendNodeId) {
            set("backendNodeId", backendNodeId);
            return this;
        }
        /**
         * JavaScript object id of the node wrapper.
         * @param objectId field value
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
        public static GetAttributesRequest fromMap(Map<String, Object> values) {
            final GetAttributesRequest instance_ = new GetAttributesRequest();
            if (values != null) instance_.values.putAll(values);
            return instance_;
        }
        /**
         * Id of the node to retrieve attributes for.
         * @return the protocol field value
         */
        public DOM.NodeId nodeId() {
            return require(NODE_ID);
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
            final GetBoxModelRequest instance_ = new GetBoxModelRequest();
            if (values != null) instance_.values.putAll(values);
            return instance_;
        }
        /**
         * Identifier of the node.
         * @return the protocol field value, empty when absent
         */
        public Optional<DOM.NodeId> nodeId() {
            return optional(NODE_ID);
        }
        /**
         * Identifier of the backend node.
         * @return the protocol field value, empty when absent
         */
        public Optional<DOM.BackendNodeId> backendNodeId() {
            return optional(BACKEND_NODE_ID);
        }
        /**
         * JavaScript object id of the node wrapper.
         * @return the protocol field value, empty when absent
         */
        public Optional<Runtime.RemoteObjectId> objectId() {
            return optional(OBJECT_ID);
        }
        /**
         * Identifier of the node.
         * @param nodeId field value
         * @return this model
         */
        public GetBoxModelRequest nodeId(DOM.NodeId nodeId) {
            set("nodeId", nodeId);
            return this;
        }
        /**
         * Identifier of the backend node.
         * @param backendNodeId field value
         * @return this model
         */
        public GetBoxModelRequest backendNodeId(DOM.BackendNodeId backendNodeId) {
            set("backendNodeId", backendNodeId);
            return this;
        }
        /**
         * JavaScript object id of the node wrapper.
         * @param objectId field value
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
            final GetContentQuadsRequest instance_ = new GetContentQuadsRequest();
            if (values != null) instance_.values.putAll(values);
            return instance_;
        }
        /**
         * Identifier of the node.
         * @return the protocol field value, empty when absent
         */
        public Optional<DOM.NodeId> nodeId() {
            return optional(NODE_ID);
        }
        /**
         * Identifier of the backend node.
         * @return the protocol field value, empty when absent
         */
        public Optional<DOM.BackendNodeId> backendNodeId() {
            return optional(BACKEND_NODE_ID);
        }
        /**
         * JavaScript object id of the node wrapper.
         * @return the protocol field value, empty when absent
         */
        public Optional<Runtime.RemoteObjectId> objectId() {
            return optional(OBJECT_ID);
        }
        /**
         * Identifier of the node.
         * @param nodeId field value
         * @return this model
         */
        public GetContentQuadsRequest nodeId(DOM.NodeId nodeId) {
            set("nodeId", nodeId);
            return this;
        }
        /**
         * Identifier of the backend node.
         * @param backendNodeId field value
         * @return this model
         */
        public GetContentQuadsRequest backendNodeId(DOM.BackendNodeId backendNodeId) {
            set("backendNodeId", backendNodeId);
            return this;
        }
        /**
         * JavaScript object id of the node wrapper.
         * @param objectId field value
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
            final GetDocumentRequest instance_ = new GetDocumentRequest();
            if (values != null) instance_.values.putAll(values);
            return instance_;
        }
        /**
         * The maximum depth at which children should be retrieved, defaults to 1. Use -1 for the entire subtree or provide an integer larger than 0.
         * @return the protocol field value, empty when absent
         */
        public OptionalLong depth() {
            return optionalLong("depth");
        }
        /**
         * Whether or not iframes and shadow roots should be traversed when returning the subtree (default is false).
         * @return the protocol field value, empty when absent
         */
        public Optional<Boolean> pierce() {
            return optionalBoolean("pierce");
        }
        /**
         * The maximum depth at which children should be retrieved, defaults to 1. Use -1 for the entire subtree or provide an integer larger than 0.
         * @param depth field value
         * @return this model
         */
        public GetDocumentRequest depth(long depth) {
            set("depth", depth);
            return this;
        }
        /**
         * Whether or not iframes and shadow roots should be traversed when returning the subtree (default is false).
         * @param pierce field value
         * @return this model
         */
        public GetDocumentRequest pierce(boolean pierce) {
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
            final GetFlattenedDocumentRequest instance_ = new GetFlattenedDocumentRequest();
            if (values != null) instance_.values.putAll(values);
            return instance_;
        }
        /**
         * The maximum depth at which children should be retrieved, defaults to 1. Use -1 for the entire subtree or provide an integer larger than 0.
         * @return the protocol field value, empty when absent
         */
        public OptionalLong depth() {
            return optionalLong("depth");
        }
        /**
         * Whether or not iframes and shadow roots should be traversed when returning the subtree (default is false).
         * @return the protocol field value, empty when absent
         */
        public Optional<Boolean> pierce() {
            return optionalBoolean("pierce");
        }
        /**
         * The maximum depth at which children should be retrieved, defaults to 1. Use -1 for the entire subtree or provide an integer larger than 0.
         * @param depth field value
         * @return this model
         */
        public GetFlattenedDocumentRequest depth(long depth) {
            set("depth", depth);
            return this;
        }
        /**
         * Whether or not iframes and shadow roots should be traversed when returning the subtree (default is false).
         * @param pierce field value
         * @return this model
         */
        public GetFlattenedDocumentRequest pierce(boolean pierce) {
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
        public static GetNodesForSubtreeByStyleRequest fromMap(Map<String, Object> values) {
            final GetNodesForSubtreeByStyleRequest instance_ = new GetNodesForSubtreeByStyleRequest();
            if (values != null) instance_.values.putAll(values);
            return instance_;
        }
        /**
         * Node ID pointing to the root of a subtree.
         * @return the protocol field value
         */
        public DOM.NodeId nodeId() {
            return require(NODE_ID);
        }
        /**
         * The style to filter nodes by (includes nodes if any of properties matches).
         * @return the protocol field value
         */
        public java.util.List<DOM.CSSComputedStyleProperty> computedStyles() {
            return require(COMPUTED_STYLES);
        }
        /**
         * Whether or not iframes and shadow roots in the same target should be traversed when returning the results (default is false).
         * @return the protocol field value, empty when absent
         */
        public Optional<Boolean> pierce() {
            return optionalBoolean("pierce");
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
         * @param pierce field value
         * @return this model
         */
        public GetNodesForSubtreeByStyleRequest pierce(boolean pierce) {
            set("pierce", pierce);
            return this;
        }
    }
    /**
     * Returns node id at given location. Depending on whether DOM domain is enabled, nodeId is either returned or not.
     */
    public static final class GetNodeForLocationRequest extends CdpObject {
        public GetNodeForLocationRequest() {}
        public static GetNodeForLocationRequest fromMap(Map<String, Object> values) {
            final GetNodeForLocationRequest instance_ = new GetNodeForLocationRequest();
            if (values != null) instance_.values.putAll(values);
            return instance_;
        }
        /**
         * X coordinate.
         * @return the protocol field value
         */
        public long x() {
            return requireLong("x");
        }
        /**
         * Y coordinate.
         * @return the protocol field value
         */
        public long y() {
            return requireLong("y");
        }
        /**
         * False to skip to the nearest non-UA shadow root ancestor (default: false).
         * @return the protocol field value, empty when absent
         */
        public Optional<Boolean> includeUserAgentShadowDOM() {
            return optionalBoolean("includeUserAgentShadowDOM");
        }
        /**
         * Whether to ignore pointer-events: none on elements and hit test them.
         * @return the protocol field value, empty when absent
         */
        public Optional<Boolean> ignorePointerEventsNone() {
            return optionalBoolean("ignorePointerEventsNone");
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
         * @param includeUserAgentShadowDOM field value
         * @return this model
         */
        public GetNodeForLocationRequest includeUserAgentShadowDOM(boolean includeUserAgentShadowDOM) {
            set("includeUserAgentShadowDOM", includeUserAgentShadowDOM);
            return this;
        }
        /**
         * Whether to ignore pointer-events: none on elements and hit test them.
         * @param ignorePointerEventsNone field value
         * @return this model
         */
        public GetNodeForLocationRequest ignorePointerEventsNone(boolean ignorePointerEventsNone) {
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
            final GetOuterHTMLRequest instance_ = new GetOuterHTMLRequest();
            if (values != null) instance_.values.putAll(values);
            return instance_;
        }
        /**
         * Identifier of the node.
         * @return the protocol field value, empty when absent
         */
        public Optional<DOM.NodeId> nodeId() {
            return optional(NODE_ID);
        }
        /**
         * Identifier of the backend node.
         * @return the protocol field value, empty when absent
         */
        public Optional<DOM.BackendNodeId> backendNodeId() {
            return optional(BACKEND_NODE_ID);
        }
        /**
         * JavaScript object id of the node wrapper.
         * @return the protocol field value, empty when absent
         */
        public Optional<Runtime.RemoteObjectId> objectId() {
            return optional(OBJECT_ID);
        }
        /**
         * Include all shadow roots. Equals to false if not specified.
         * <p><b>Experimental:</b> this part of CDP may change without notice.
         * @return the protocol field value, empty when absent
         */
        public Optional<Boolean> includeShadowDOM() {
            return optionalBoolean("includeShadowDOM");
        }
        /**
         * Identifier of the node.
         * @param nodeId field value
         * @return this model
         */
        public GetOuterHTMLRequest nodeId(DOM.NodeId nodeId) {
            set("nodeId", nodeId);
            return this;
        }
        /**
         * Identifier of the backend node.
         * @param backendNodeId field value
         * @return this model
         */
        public GetOuterHTMLRequest backendNodeId(DOM.BackendNodeId backendNodeId) {
            set("backendNodeId", backendNodeId);
            return this;
        }
        /**
         * JavaScript object id of the node wrapper.
         * @param objectId field value
         * @return this model
         */
        public GetOuterHTMLRequest objectId(Runtime.RemoteObjectId objectId) {
            set("objectId", objectId);
            return this;
        }
        /**
         * Include all shadow roots. Equals to false if not specified.
         * <p><b>Experimental:</b> this part of CDP may change without notice.
         * @param includeShadowDOM field value
         * @return this model
         */
        public GetOuterHTMLRequest includeShadowDOM(boolean includeShadowDOM) {
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
        public static GetRelayoutBoundaryRequest fromMap(Map<String, Object> values) {
            final GetRelayoutBoundaryRequest instance_ = new GetRelayoutBoundaryRequest();
            if (values != null) instance_.values.putAll(values);
            return instance_;
        }
        /**
         * Id of the node.
         * @return the protocol field value
         */
        public DOM.NodeId nodeId() {
            return require(NODE_ID);
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
        public static GetSearchResultsRequest fromMap(Map<String, Object> values) {
            final GetSearchResultsRequest instance_ = new GetSearchResultsRequest();
            if (values != null) instance_.values.putAll(values);
            return instance_;
        }
        /**
         * Unique search session identifier.
         * @return the protocol field value
         */
        public String searchId() {
            return requireString("searchId");
        }
        /**
         * Start index of the search result to be returned.
         * @return the protocol field value
         */
        public long fromIndex() {
            return requireLong("fromIndex");
        }
        /**
         * End index of the search result to be returned.
         * @return the protocol field value
         */
        public long toIndex() {
            return requireLong("toIndex");
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
        public static MoveToRequest fromMap(Map<String, Object> values) {
            final MoveToRequest instance_ = new MoveToRequest();
            if (values != null) instance_.values.putAll(values);
            return instance_;
        }
        /**
         * Id of the node to move.
         * @return the protocol field value
         */
        public DOM.NodeId nodeId() {
            return require(NODE_ID);
        }
        /**
         * Id of the element to drop the moved node into.
         * @return the protocol field value
         */
        public DOM.NodeId targetNodeId() {
            return require(TARGET_NODE_ID);
        }
        /**
         * Drop node before this one (if absent, the moved node becomes the last child of {@code targetNodeId}).
         * @return the protocol field value, empty when absent
         */
        public Optional<DOM.NodeId> insertBeforeNodeId() {
            return optional(INSERT_BEFORE_NODE_ID);
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
         * @param insertBeforeNodeId field value
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
        public static PerformSearchRequest fromMap(Map<String, Object> values) {
            final PerformSearchRequest instance_ = new PerformSearchRequest();
            if (values != null) instance_.values.putAll(values);
            return instance_;
        }
        /**
         * Plain text or query selector or XPath search query.
         * @return the protocol field value
         */
        public String query() {
            return requireString("query");
        }
        /**
         * True to search in user agent shadow DOM.
         * @return the protocol field value, empty when absent
         */
        public Optional<Boolean> includeUserAgentShadowDOM() {
            return optionalBoolean("includeUserAgentShadowDOM");
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
         * @param includeUserAgentShadowDOM field value
         * @return this model
         */
        public PerformSearchRequest includeUserAgentShadowDOM(boolean includeUserAgentShadowDOM) {
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
        public static PushNodeByPathToFrontendRequest fromMap(Map<String, Object> values) {
            final PushNodeByPathToFrontendRequest instance_ = new PushNodeByPathToFrontendRequest();
            if (values != null) instance_.values.putAll(values);
            return instance_;
        }
        /**
         * Path to node in the proprietary format.
         * @return the protocol field value
         */
        public String path() {
            return requireString("path");
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
        public static PushNodesByBackendIdsToFrontendRequest fromMap(Map<String, Object> values) {
            final PushNodesByBackendIdsToFrontendRequest instance_ = new PushNodesByBackendIdsToFrontendRequest();
            if (values != null) instance_.values.putAll(values);
            return instance_;
        }
        /**
         * The array of backend node ids.
         * @return the protocol field value
         */
        public java.util.List<DOM.BackendNodeId> backendNodeIds() {
            return require(BACKEND_NODE_IDS);
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
        public static QuerySelectorRequest fromMap(Map<String, Object> values) {
            final QuerySelectorRequest instance_ = new QuerySelectorRequest();
            if (values != null) instance_.values.putAll(values);
            return instance_;
        }
        /**
         * Id of the node to query upon.
         * @return the protocol field value
         */
        public DOM.NodeId nodeId() {
            return require(NODE_ID);
        }
        /**
         * Selector string.
         * @return the protocol field value
         */
        public String selector() {
            return requireString("selector");
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
        public static QuerySelectorAllRequest fromMap(Map<String, Object> values) {
            final QuerySelectorAllRequest instance_ = new QuerySelectorAllRequest();
            if (values != null) instance_.values.putAll(values);
            return instance_;
        }
        /**
         * Id of the node to query upon.
         * @return the protocol field value
         */
        public DOM.NodeId nodeId() {
            return require(NODE_ID);
        }
        /**
         * Selector string.
         * @return the protocol field value
         */
        public String selector() {
            return requireString("selector");
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
        public static GetElementByRelationRequest fromMap(Map<String, Object> values) {
            final GetElementByRelationRequest instance_ = new GetElementByRelationRequest();
            if (values != null) instance_.values.putAll(values);
            return instance_;
        }
        /**
         * Id of the node from which to query the relation.
         * @return the protocol field value
         */
        public DOM.NodeId nodeId() {
            return require(NODE_ID);
        }
        /**
         * Type of relation to get.
         * @return the protocol field value
         */
        public GetElementByRelationRelationValues relation() {
            return require(RELATION);
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
        public static RemoveAttributeRequest fromMap(Map<String, Object> values) {
            final RemoveAttributeRequest instance_ = new RemoveAttributeRequest();
            if (values != null) instance_.values.putAll(values);
            return instance_;
        }
        /**
         * Id of the element to remove attribute from.
         * @return the protocol field value
         */
        public DOM.NodeId nodeId() {
            return require(NODE_ID);
        }
        /**
         * Name of the attribute to remove.
         * @return the protocol field value
         */
        public String name() {
            return requireString("name");
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
        public static RemoveNodeRequest fromMap(Map<String, Object> values) {
            final RemoveNodeRequest instance_ = new RemoveNodeRequest();
            if (values != null) instance_.values.putAll(values);
            return instance_;
        }
        /**
         * Id of the node to remove.
         * @return the protocol field value
         */
        public DOM.NodeId nodeId() {
            return require(NODE_ID);
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
        public static RequestChildNodesRequest fromMap(Map<String, Object> values) {
            final RequestChildNodesRequest instance_ = new RequestChildNodesRequest();
            if (values != null) instance_.values.putAll(values);
            return instance_;
        }
        /**
         * Id of the node to get children for.
         * @return the protocol field value
         */
        public DOM.NodeId nodeId() {
            return require(NODE_ID);
        }
        /**
         * The maximum depth at which children should be retrieved, defaults to 1. Use -1 for the entire subtree or provide an integer larger than 0.
         * @return the protocol field value, empty when absent
         */
        public OptionalLong depth() {
            return optionalLong("depth");
        }
        /**
         * Whether or not iframes and shadow roots should be traversed when returning the sub-tree (default is false).
         * @return the protocol field value, empty when absent
         */
        public Optional<Boolean> pierce() {
            return optionalBoolean("pierce");
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
         * @param depth field value
         * @return this model
         */
        public RequestChildNodesRequest depth(long depth) {
            set("depth", depth);
            return this;
        }
        /**
         * Whether or not iframes and shadow roots should be traversed when returning the sub-tree (default is false).
         * @param pierce field value
         * @return this model
         */
        public RequestChildNodesRequest pierce(boolean pierce) {
            set("pierce", pierce);
            return this;
        }
    }
    /**
     * Requests that the node is sent to the caller given the JavaScript node object reference. All nodes that form the path from the node to the root are also sent to the client as a series of {@code setChildNodes} notifications.
     */
    public static final class RequestNodeRequest extends CdpObject {
        public RequestNodeRequest() {}
        public static RequestNodeRequest fromMap(Map<String, Object> values) {
            final RequestNodeRequest instance_ = new RequestNodeRequest();
            if (values != null) instance_.values.putAll(values);
            return instance_;
        }
        /**
         * JavaScript object id to convert into node.
         * @return the protocol field value
         */
        public Runtime.RemoteObjectId objectId() {
            return require(OBJECT_ID);
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
            final ResolveNodeRequest instance_ = new ResolveNodeRequest();
            if (values != null) instance_.values.putAll(values);
            return instance_;
        }
        /**
         * Id of the node to resolve.
         * @return the protocol field value, empty when absent
         */
        public Optional<DOM.NodeId> nodeId() {
            return optional(NODE_ID);
        }
        /**
         * Backend identifier of the node to resolve.
         * @return the protocol field value, empty when absent
         */
        public Optional<DOM.BackendNodeId> backendNodeId() {
            return optional(BACKEND_NODE_ID);
        }
        /**
         * Symbolic group name that can be used to release multiple objects.
         * @return the protocol field value, empty when absent
         */
        public Optional<String> objectGroup() {
            return optionalString("objectGroup");
        }
        /**
         * Execution context in which to resolve the node.
         * @return the protocol field value, empty when absent
         */
        public Optional<Runtime.ExecutionContextId> executionContextId() {
            return optional(EXECUTION_CONTEXT_ID);
        }
        /**
         * Id of the node to resolve.
         * @param nodeId field value
         * @return this model
         */
        public ResolveNodeRequest nodeId(DOM.NodeId nodeId) {
            set("nodeId", nodeId);
            return this;
        }
        /**
         * Backend identifier of the node to resolve.
         * @param backendNodeId field value
         * @return this model
         */
        public ResolveNodeRequest backendNodeId(DOM.BackendNodeId backendNodeId) {
            set("backendNodeId", backendNodeId);
            return this;
        }
        /**
         * Symbolic group name that can be used to release multiple objects.
         * @param objectGroup field value
         * @return this model
         */
        public ResolveNodeRequest objectGroup(String objectGroup) {
            set("objectGroup", objectGroup);
            return this;
        }
        /**
         * Execution context in which to resolve the node.
         * @param executionContextId field value
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
        public static SetAttributeValueRequest fromMap(Map<String, Object> values) {
            final SetAttributeValueRequest instance_ = new SetAttributeValueRequest();
            if (values != null) instance_.values.putAll(values);
            return instance_;
        }
        /**
         * Id of the element to set attribute for.
         * @return the protocol field value
         */
        public DOM.NodeId nodeId() {
            return require(NODE_ID);
        }
        /**
         * Attribute name.
         * @return the protocol field value
         */
        public String name() {
            return requireString("name");
        }
        /**
         * Attribute value.
         * @return the protocol field value
         */
        public String value() {
            return requireString("value");
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
        public static SetAttributesAsTextRequest fromMap(Map<String, Object> values) {
            final SetAttributesAsTextRequest instance_ = new SetAttributesAsTextRequest();
            if (values != null) instance_.values.putAll(values);
            return instance_;
        }
        /**
         * Id of the element to set attributes for.
         * @return the protocol field value
         */
        public DOM.NodeId nodeId() {
            return require(NODE_ID);
        }
        /**
         * Text with a number of attributes. Will parse this text using HTML parser.
         * @return the protocol field value
         */
        public String text() {
            return requireString("text");
        }
        /**
         * Attribute name to replace with new attributes derived from text in case text parsed successfully.
         * @return the protocol field value, empty when absent
         */
        public Optional<String> name() {
            return optionalString("name");
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
         * @param name field value
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
        public static SetFileInputFilesRequest fromMap(Map<String, Object> values) {
            final SetFileInputFilesRequest instance_ = new SetFileInputFilesRequest();
            if (values != null) instance_.values.putAll(values);
            return instance_;
        }
        /**
         * Array of file paths to set.
         * @return the protocol field value
         */
        public java.util.List<String> files() {
            return require(FILES);
        }
        /**
         * Identifier of the node.
         * @return the protocol field value, empty when absent
         */
        public Optional<DOM.NodeId> nodeId() {
            return optional(NODE_ID);
        }
        /**
         * Identifier of the backend node.
         * @return the protocol field value, empty when absent
         */
        public Optional<DOM.BackendNodeId> backendNodeId() {
            return optional(BACKEND_NODE_ID);
        }
        /**
         * JavaScript object id of the node wrapper.
         * @return the protocol field value, empty when absent
         */
        public Optional<Runtime.RemoteObjectId> objectId() {
            return optional(OBJECT_ID);
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
         * @param nodeId field value
         * @return this model
         */
        public SetFileInputFilesRequest nodeId(DOM.NodeId nodeId) {
            set("nodeId", nodeId);
            return this;
        }
        /**
         * Identifier of the backend node.
         * @param backendNodeId field value
         * @return this model
         */
        public SetFileInputFilesRequest backendNodeId(DOM.BackendNodeId backendNodeId) {
            set("backendNodeId", backendNodeId);
            return this;
        }
        /**
         * JavaScript object id of the node wrapper.
         * @param objectId field value
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
        public static SetNodeStackTracesEnabledRequest fromMap(Map<String, Object> values) {
            final SetNodeStackTracesEnabledRequest instance_ = new SetNodeStackTracesEnabledRequest();
            if (values != null) instance_.values.putAll(values);
            return instance_;
        }
        /**
         * Enable or disable.
         * @return the protocol field value
         */
        public boolean enable() {
            return requireBoolean("enable");
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
        public static GetNodeStackTracesRequest fromMap(Map<String, Object> values) {
            final GetNodeStackTracesRequest instance_ = new GetNodeStackTracesRequest();
            if (values != null) instance_.values.putAll(values);
            return instance_;
        }
        /**
         * Id of the node to get stack traces for.
         * @return the protocol field value
         */
        public DOM.NodeId nodeId() {
            return require(NODE_ID);
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
        public static GetFileInfoRequest fromMap(Map<String, Object> values) {
            final GetFileInfoRequest instance_ = new GetFileInfoRequest();
            if (values != null) instance_.values.putAll(values);
            return instance_;
        }
        /**
         * JavaScript object id of the node wrapper.
         * @return the protocol field value
         */
        public Runtime.RemoteObjectId objectId() {
            return require(OBJECT_ID);
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
        public static SetInspectedNodeRequest fromMap(Map<String, Object> values) {
            final SetInspectedNodeRequest instance_ = new SetInspectedNodeRequest();
            if (values != null) instance_.values.putAll(values);
            return instance_;
        }
        /**
         * DOM node id to be accessible by means of $x command line API.
         * @return the protocol field value
         */
        public DOM.NodeId nodeId() {
            return require(NODE_ID);
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
        public static SetNodeNameRequest fromMap(Map<String, Object> values) {
            final SetNodeNameRequest instance_ = new SetNodeNameRequest();
            if (values != null) instance_.values.putAll(values);
            return instance_;
        }
        /**
         * Id of the node to set name for.
         * @return the protocol field value
         */
        public DOM.NodeId nodeId() {
            return require(NODE_ID);
        }
        /**
         * New node&#x27;s name.
         * @return the protocol field value
         */
        public String name() {
            return requireString("name");
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
        public static SetNodeValueRequest fromMap(Map<String, Object> values) {
            final SetNodeValueRequest instance_ = new SetNodeValueRequest();
            if (values != null) instance_.values.putAll(values);
            return instance_;
        }
        /**
         * Id of the node to set value for.
         * @return the protocol field value
         */
        public DOM.NodeId nodeId() {
            return require(NODE_ID);
        }
        /**
         * New node&#x27;s value.
         * @return the protocol field value
         */
        public String value() {
            return requireString("value");
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
        public static SetOuterHTMLRequest fromMap(Map<String, Object> values) {
            final SetOuterHTMLRequest instance_ = new SetOuterHTMLRequest();
            if (values != null) instance_.values.putAll(values);
            return instance_;
        }
        /**
         * Id of the node to set markup for.
         * @return the protocol field value
         */
        public DOM.NodeId nodeId() {
            return require(NODE_ID);
        }
        /**
         * Outer HTML markup to set.
         * @return the protocol field value
         */
        public String outerHTML() {
            return requireString("outerHTML");
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
        public static GetFrameOwnerRequest fromMap(Map<String, Object> values) {
            final GetFrameOwnerRequest instance_ = new GetFrameOwnerRequest();
            if (values != null) instance_.values.putAll(values);
            return instance_;
        }
        /**
         * Returns the frameId field.
         * @return the protocol field value
         */
        public Page.FrameId frameId() {
            return require(FRAME_ID);
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
        public static GetContainerForNodeRequest fromMap(Map<String, Object> values) {
            final GetContainerForNodeRequest instance_ = new GetContainerForNodeRequest();
            if (values != null) instance_.values.putAll(values);
            return instance_;
        }
        /**
         * Returns the nodeId field.
         * @return the protocol field value
         */
        public DOM.NodeId nodeId() {
            return require(NODE_ID);
        }
        /**
         * Returns the containerName field.
         * @return the protocol field value, empty when absent
         */
        public Optional<String> containerName() {
            return optionalString("containerName");
        }
        /**
         * Returns the physicalAxes field.
         * @return the protocol field value, empty when absent
         */
        public Optional<DOM.PhysicalAxes> physicalAxes() {
            return optional(PHYSICAL_AXES);
        }
        /**
         * Returns the logicalAxes field.
         * @return the protocol field value, empty when absent
         */
        public Optional<DOM.LogicalAxes> logicalAxes() {
            return optional(LOGICAL_AXES);
        }
        /**
         * Returns the queriesScrollState field.
         * @return the protocol field value, empty when absent
         */
        public Optional<Boolean> queriesScrollState() {
            return optionalBoolean("queriesScrollState");
        }
        /**
         * Returns the queriesAnchored field.
         * @return the protocol field value, empty when absent
         */
        public Optional<Boolean> queriesAnchored() {
            return optionalBoolean("queriesAnchored");
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
         * @param containerName field value
         * @return this model
         */
        public GetContainerForNodeRequest containerName(String containerName) {
            set("containerName", containerName);
            return this;
        }
        /**
         * Sets the physicalAxes field.
         * @param physicalAxes field value
         * @return this model
         */
        public GetContainerForNodeRequest physicalAxes(DOM.PhysicalAxes physicalAxes) {
            set("physicalAxes", physicalAxes);
            return this;
        }
        /**
         * Sets the logicalAxes field.
         * @param logicalAxes field value
         * @return this model
         */
        public GetContainerForNodeRequest logicalAxes(DOM.LogicalAxes logicalAxes) {
            set("logicalAxes", logicalAxes);
            return this;
        }
        /**
         * Sets the queriesScrollState field.
         * @param queriesScrollState field value
         * @return this model
         */
        public GetContainerForNodeRequest queriesScrollState(boolean queriesScrollState) {
            set("queriesScrollState", queriesScrollState);
            return this;
        }
        /**
         * Sets the queriesAnchored field.
         * @param queriesAnchored field value
         * @return this model
         */
        public GetContainerForNodeRequest queriesAnchored(boolean queriesAnchored) {
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
        public static GetQueryingDescendantsForContainerRequest fromMap(Map<String, Object> values) {
            final GetQueryingDescendantsForContainerRequest instance_ = new GetQueryingDescendantsForContainerRequest();
            if (values != null) instance_.values.putAll(values);
            return instance_;
        }
        /**
         * Id of the container node to find querying descendants from.
         * @return the protocol field value
         */
        public DOM.NodeId nodeId() {
            return require(NODE_ID);
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
        public static GetAnchorElementRequest fromMap(Map<String, Object> values) {
            final GetAnchorElementRequest instance_ = new GetAnchorElementRequest();
            if (values != null) instance_.values.putAll(values);
            return instance_;
        }
        /**
         * Id of the positioned element from which to find the anchor.
         * @return the protocol field value
         */
        public DOM.NodeId nodeId() {
            return require(NODE_ID);
        }
        /**
         * An optional anchor specifier, as defined in https://www.w3.org/TR/css-anchor-position-1/#anchor-specifier. If not provided, it will return the implicit anchor element for the given positioned element.
         * @return the protocol field value, empty when absent
         */
        public Optional<String> anchorSpecifier() {
            return optionalString("anchorSpecifier");
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
         * @param anchorSpecifier field value
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
        public static ForceShowPopoverRequest fromMap(Map<String, Object> values) {
            final ForceShowPopoverRequest instance_ = new ForceShowPopoverRequest();
            if (values != null) instance_.values.putAll(values);
            return instance_;
        }
        /**
         * Id of the popover HTMLElement
         * @return the protocol field value
         */
        public DOM.NodeId nodeId() {
            return require(NODE_ID);
        }
        /**
         * If true, opens the popover and keeps it open. If false, closes the popover if it was previously force-opened.
         * @return the protocol field value
         */
        public boolean enable() {
            return requireBoolean("enable");
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
            return require(BACKEND_NODE_ID);
        }
        /**
         * Frame this node belongs to.
         * @return the protocol field value
         */
        public Page.FrameId frameId() {
            return require(FRAME_ID);
        }
        /**
         * Id of the node at given coordinates, only when enabled and requested document.
         * @return the protocol field value, empty when absent
         */
        public Optional<DOM.NodeId> nodeId() {
            return optional(NODE_ID);
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
         * @param nodeId field value
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
            return requireString("searchId");
        }
        /**
         * Number of search results.
         * @return the protocol field value
         */
        public long resultCount() {
            return requireLong("resultCount");
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
            return require(BACKEND_NODE_ID);
        }
        /**
         * Id of the node at given coordinates, only when enabled and requested document.
         * @return the protocol field value, empty when absent
         */
        public Optional<DOM.NodeId> nodeId() {
            return optional(NODE_ID);
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
         * @param nodeId field value
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
            return require(NODE_ID);
        }
        /**
         * Attribute name.
         * @return the protocol field value
         */
        public String name() {
            return requireString("name");
        }
        /**
         * Attribute value.
         * @return the protocol field value
         */
        public String value() {
            return requireString("value");
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
            return require(NODE_ID);
        }
        /**
         * New adoptedStyleSheets array.
         * <p><b>Experimental:</b> this part of CDP may change without notice.
         * @return the protocol field value
         */
        public java.util.List<DOM.StyleSheetId> adoptedStyleSheets() {
            return require(ADOPTED_STYLE_SHEETS);
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
            return require(NODE_ID);
        }
        /**
         * A ttribute name.
         * @return the protocol field value
         */
        public String name() {
            return requireString("name");
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
            return require(NODE_ID);
        }
        /**
         * New text value.
         * @return the protocol field value
         */
        public String characterData() {
            return requireString("characterData");
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
            return require(NODE_ID);
        }
        /**
         * New node count.
         * @return the protocol field value
         */
        public long childNodeCount() {
            return requireLong("childNodeCount");
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
            return require(PARENT_NODE_ID);
        }
        /**
         * Id of the previous sibling.
         * @return the protocol field value
         */
        public DOM.NodeId previousNodeId() {
            return require(PREVIOUS_NODE_ID);
        }
        /**
         * Inserted node data.
         * @return the protocol field value
         */
        public DOM.Node node() {
            return require(NODE);
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
            return require(PARENT_NODE_ID);
        }
        /**
         * Id of the node that has been removed.
         * @return the protocol field value
         */
        public DOM.NodeId nodeId() {
            return require(NODE_ID);
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
            return require(INSERTION_POINT_ID);
        }
        /**
         * Distributed nodes for given insertion point.
         * @return the protocol field value
         */
        public java.util.List<DOM.BackendNode> distributedNodes() {
            return require(DISTRIBUTED_NODES);
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
            return require(NODE_IDS);
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
            return require(PARENT_ID);
        }
        /**
         * The added pseudo element.
         * @return the protocol field value
         */
        public DOM.Node pseudoElement() {
            return require(PSEUDO_ELEMENT);
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
            return require(NODE_ID);
        }
        /**
         * If the node is scrollable.
         * @return the protocol field value
         */
        public boolean isScrollable() {
            return requireBoolean("isScrollable");
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
            return require(NODE_ID);
        }
        /**
         * The provenance of the ad related node, if it is ad related.
         * @return the protocol field value, empty when absent
         */
        public Optional<Network.AdProvenance> adProvenance() {
            return optional(AD_PROVENANCE);
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
         * @param adProvenance field value
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
            return require(NODE_ID);
        }
        /**
         * If the node has starting styles.
         * @return the protocol field value
         */
        public boolean affectedByStartingStyles() {
            return requireBoolean("affectedByStartingStyles");
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
            return require(PARENT_ID);
        }
        /**
         * The removed pseudo element id.
         * @return the protocol field value
         */
        public DOM.NodeId pseudoElementId() {
            return require(PSEUDO_ELEMENT_ID);
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
            return require(PARENT_ID);
        }
        /**
         * Child nodes array.
         * @return the protocol field value
         */
        public java.util.List<DOM.Node> nodes() {
            return require(NODES);
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
            return require(HOST_ID);
        }
        /**
         * Shadow root id.
         * @return the protocol field value
         */
        public DOM.NodeId rootId() {
            return require(ROOT_ID);
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
            return require(HOST_ID);
        }
        /**
         * Shadow root.
         * @return the protocol field value
         */
        public DOM.Node root() {
            return require(ROOT);
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
        @Override public String value() { return value; }
        public static EnableIncludeWhitespaceValues of(String value) {
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
        @Override public String value() { return value; }
        public static GetElementByRelationRelationValues of(String value) {
            for (GetElementByRelationRelationValues constant : values()) {
                if (constant.value.equals(value)) return constant;
            }
            throw new IllegalArgumentException("Unknown GetElementByRelationRelationValues value: " + value);
        }
    }
    public static final class Client extends CdpDomainClient {
        public Client(CdpClient client) { super(client); }
        /**
         * Collects class names for the node with given id and all of it&#x27;s child nodes.
         * <p><b>Experimental:</b> this part of CDP may change without notice.
         * @param nodeId protocol value
         * @return a stage completing with the command result
         */
        public CompletionStage<java.util.List<String>> collectClassNamesFromSubtree(DOM.NodeId nodeId) {
            return call("DOM.collectClassNamesFromSubtree", params("nodeId", nodeId), CLASS_NAMES::require);
        }
        /**
         * Collects class names for the node with given id and all of it&#x27;s child nodes.
         * <p><b>Experimental:</b> this part of CDP may change without notice.
         * @param request request parameters
         * @return a stage completing with the command result
         */
        public CompletionStage<java.util.List<String>> collectClassNamesFromSubtree(CollectClassNamesFromSubtreeRequest request) {
            return call("DOM.collectClassNamesFromSubtree", request, CLASS_NAMES::require);
        }
        /**
         * Creates a deep copy of the specified node and places it into the target container before the given anchor.
         * <p><b>Experimental:</b> this part of CDP may change without notice.
         * @param nodeId protocol value
         * @param targetNodeId protocol value
         * @param insertBeforeNodeId protocol value
         * @return a stage completing with the command result
         */
        public CompletionStage<DOM.NodeId> copyTo(DOM.NodeId nodeId, DOM.NodeId targetNodeId, DOM.NodeId insertBeforeNodeId) {
            return call("DOM.copyTo", params("nodeId", nodeId, "targetNodeId", targetNodeId, "insertBeforeNodeId", insertBeforeNodeId), NODE_ID::require);
        }
        /**
         * Creates a deep copy of the specified node and places it into the target container before the given anchor.
         * <p><b>Experimental:</b> this part of CDP may change without notice.
         * @param nodeId protocol value
         * @param targetNodeId protocol value
         * @return a stage completing with the command result
         */
        public CompletionStage<DOM.NodeId> copyTo(DOM.NodeId nodeId, DOM.NodeId targetNodeId) {
            return call("DOM.copyTo", params("nodeId", nodeId, "targetNodeId", targetNodeId), NODE_ID::require);
        }
        /**
         * Creates a deep copy of the specified node and places it into the target container before the given anchor.
         * <p><b>Experimental:</b> this part of CDP may change without notice.
         * @param request request parameters
         * @return a stage completing with the command result
         */
        public CompletionStage<DOM.NodeId> copyTo(CopyToRequest request) {
            return call("DOM.copyTo", request, NODE_ID::require);
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
        public CompletionStage<DOM.Node> describeNode(DOM.@Nullable NodeId nodeId, DOM.@Nullable BackendNodeId backendNodeId, Runtime.@Nullable RemoteObjectId objectId, @Nullable Long depth, @Nullable Boolean pierce) {
            return call("DOM.describeNode", params("nodeId", nodeId, "backendNodeId", backendNodeId, "objectId", objectId, "depth", depth, "pierce", pierce), NODE::require);
        }
        /**
         * Describes node given its id, does not require domain to be enabled. Does not start tracking any objects, can be used for automation.
         * @return a stage completing with the command result
         */
        public CompletionStage<DOM.Node> describeNode() {
            return call("DOM.describeNode", NODE::require);
        }
        /**
         * Describes node given its id, does not require domain to be enabled. Does not start tracking any objects, can be used for automation.
         * @param request request parameters
         * @return a stage completing with the command result
         */
        public CompletionStage<DOM.Node> describeNode(DescribeNodeRequest request) {
            return call("DOM.describeNode", request, NODE::require);
        }
        /**
         * Scrolls the specified rect of the given node into view if not already visible. Note: exactly one between nodeId, backendNodeId and objectId should be passed to identify the node.
         * @param nodeId protocol value
         * @param backendNodeId protocol value
         * @param objectId protocol value
         * @param rect protocol value
         * @return a stage completing when the command completes
         */
        public CompletionStage<Void> scrollIntoViewIfNeeded(DOM.@Nullable NodeId nodeId, DOM.@Nullable BackendNodeId backendNodeId, Runtime.@Nullable RemoteObjectId objectId, DOM.@Nullable Rect rect) {
            return call("DOM.scrollIntoViewIfNeeded", params("nodeId", nodeId, "backendNodeId", backendNodeId, "objectId", objectId, "rect", rect));
        }
        /**
         * Scrolls the specified rect of the given node into view if not already visible. Note: exactly one between nodeId, backendNodeId and objectId should be passed to identify the node.
         * @return a stage completing when the command completes
         */
        public CompletionStage<Void> scrollIntoViewIfNeeded() {
            return call("DOM.scrollIntoViewIfNeeded");
        }
        /**
         * Scrolls the specified rect of the given node into view if not already visible. Note: exactly one between nodeId, backendNodeId and objectId should be passed to identify the node.
         * @param request request parameters
         * @return a stage completing when the command completes
         */
        public CompletionStage<Void> scrollIntoViewIfNeeded(ScrollIntoViewIfNeededRequest request) {
            return call("DOM.scrollIntoViewIfNeeded", request);
        }
        /**
         * Disables DOM agent for the given page.
         * @return a stage completing when the command completes
         */
        public CompletionStage<Void> disable() {
            return call("DOM.disable");
        }
        /**
         * Discards search results from the session with the given id. {@code getSearchResults} should no longer be called for that search.
         * <p><b>Experimental:</b> this part of CDP may change without notice.
         * @param searchId protocol value
         * @return a stage completing when the command completes
         */
        public CompletionStage<Void> discardSearchResults(String searchId) {
            return call("DOM.discardSearchResults", params("searchId", searchId));
        }
        /**
         * Discards search results from the session with the given id. {@code getSearchResults} should no longer be called for that search.
         * <p><b>Experimental:</b> this part of CDP may change without notice.
         * @param request request parameters
         * @return a stage completing when the command completes
         */
        public CompletionStage<Void> discardSearchResults(DiscardSearchResultsRequest request) {
            return call("DOM.discardSearchResults", request);
        }
        /**
         * Enables DOM agent for the given page.
         * @param includeWhitespace protocol value
         * @return a stage completing when the command completes
         */
        public CompletionStage<Void> enable(EnableIncludeWhitespaceValues includeWhitespace) {
            return call("DOM.enable", params("includeWhitespace", includeWhitespace));
        }
        /**
         * Enables DOM agent for the given page.
         * @return a stage completing when the command completes
         */
        public CompletionStage<Void> enable() {
            return call("DOM.enable");
        }
        /**
         * Enables DOM agent for the given page.
         * @param request request parameters
         * @return a stage completing when the command completes
         */
        public CompletionStage<Void> enable(EnableRequest request) {
            return call("DOM.enable", request);
        }
        /**
         * Focuses the given element.
         * @param nodeId protocol value
         * @param backendNodeId protocol value
         * @param objectId protocol value
         * @return a stage completing when the command completes
         */
        public CompletionStage<Void> focus(DOM.@Nullable NodeId nodeId, DOM.@Nullable BackendNodeId backendNodeId, Runtime.@Nullable RemoteObjectId objectId) {
            return call("DOM.focus", params("nodeId", nodeId, "backendNodeId", backendNodeId, "objectId", objectId));
        }
        /**
         * Focuses the given element.
         * @return a stage completing when the command completes
         */
        public CompletionStage<Void> focus() {
            return call("DOM.focus");
        }
        /**
         * Focuses the given element.
         * @param request request parameters
         * @return a stage completing when the command completes
         */
        public CompletionStage<Void> focus(FocusRequest request) {
            return call("DOM.focus", request);
        }
        /**
         * Returns attributes for the specified node.
         * @param nodeId protocol value
         * @return a stage completing with the command result
         */
        public CompletionStage<java.util.List<String>> getAttributes(DOM.NodeId nodeId) {
            return call("DOM.getAttributes", params("nodeId", nodeId), ATTRIBUTES::require);
        }
        /**
         * Returns attributes for the specified node.
         * @param request request parameters
         * @return a stage completing with the command result
         */
        public CompletionStage<java.util.List<String>> getAttributes(GetAttributesRequest request) {
            return call("DOM.getAttributes", request, ATTRIBUTES::require);
        }
        /**
         * Returns boxes for the given node.
         * @param nodeId protocol value
         * @param backendNodeId protocol value
         * @param objectId protocol value
         * @return a stage completing with the command result
         */
        public CompletionStage<DOM.BoxModel> getBoxModel(DOM.@Nullable NodeId nodeId, DOM.@Nullable BackendNodeId backendNodeId, Runtime.@Nullable RemoteObjectId objectId) {
            return call("DOM.getBoxModel", params("nodeId", nodeId, "backendNodeId", backendNodeId, "objectId", objectId), MODEL::require);
        }
        /**
         * Returns boxes for the given node.
         * @return a stage completing with the command result
         */
        public CompletionStage<DOM.BoxModel> getBoxModel() {
            return call("DOM.getBoxModel", MODEL::require);
        }
        /**
         * Returns boxes for the given node.
         * @param request request parameters
         * @return a stage completing with the command result
         */
        public CompletionStage<DOM.BoxModel> getBoxModel(GetBoxModelRequest request) {
            return call("DOM.getBoxModel", request, MODEL::require);
        }
        /**
         * Returns quads that describe node position on the page. This method might return multiple quads for inline nodes.
         * <p><b>Experimental:</b> this part of CDP may change without notice.
         * @param nodeId protocol value
         * @param backendNodeId protocol value
         * @param objectId protocol value
         * @return a stage completing with the command result
         */
        public CompletionStage<java.util.List<java.util.List<Double>>> getContentQuads(DOM.@Nullable NodeId nodeId, DOM.@Nullable BackendNodeId backendNodeId, Runtime.@Nullable RemoteObjectId objectId) {
            return call("DOM.getContentQuads", params("nodeId", nodeId, "backendNodeId", backendNodeId, "objectId", objectId), QUADS::require);
        }
        /**
         * Returns quads that describe node position on the page. This method might return multiple quads for inline nodes.
         * <p><b>Experimental:</b> this part of CDP may change without notice.
         * @return a stage completing with the command result
         */
        public CompletionStage<java.util.List<java.util.List<Double>>> getContentQuads() {
            return call("DOM.getContentQuads", QUADS::require);
        }
        /**
         * Returns quads that describe node position on the page. This method might return multiple quads for inline nodes.
         * <p><b>Experimental:</b> this part of CDP may change without notice.
         * @param request request parameters
         * @return a stage completing with the command result
         */
        public CompletionStage<java.util.List<java.util.List<Double>>> getContentQuads(GetContentQuadsRequest request) {
            return call("DOM.getContentQuads", request, QUADS::require);
        }
        /**
         * Returns the root DOM node (and optionally the subtree) to the caller. Implicitly enables the DOM domain events for the current target.
         * @param depth protocol value
         * @param pierce protocol value
         * @return a stage completing with the command result
         */
        public CompletionStage<DOM.Node> getDocument(@Nullable Long depth, @Nullable Boolean pierce) {
            return call("DOM.getDocument", params("depth", depth, "pierce", pierce), ROOT::require);
        }
        /**
         * Returns the root DOM node (and optionally the subtree) to the caller. Implicitly enables the DOM domain events for the current target.
         * @return a stage completing with the command result
         */
        public CompletionStage<DOM.Node> getDocument() {
            return call("DOM.getDocument", ROOT::require);
        }
        /**
         * Returns the root DOM node (and optionally the subtree) to the caller. Implicitly enables the DOM domain events for the current target.
         * @param request request parameters
         * @return a stage completing with the command result
         */
        public CompletionStage<DOM.Node> getDocument(GetDocumentRequest request) {
            return call("DOM.getDocument", request, ROOT::require);
        }
        /**
         * Returns the root DOM node (and optionally the subtree) to the caller. Deprecated, as it is not designed to work well with the rest of the DOM agent. Use DOMSnapshot.captureSnapshot instead.
         * @param depth protocol value
         * @param pierce protocol value
         * @return a stage completing with the command result
         * @deprecated Deprecated by the Chromium DevTools Protocol.
         */
        @Deprecated
        public CompletionStage<java.util.List<DOM.Node>> getFlattenedDocument(@Nullable Long depth, @Nullable Boolean pierce) {
            return call("DOM.getFlattenedDocument", params("depth", depth, "pierce", pierce), NODES::require);
        }
        /**
         * Returns the root DOM node (and optionally the subtree) to the caller. Deprecated, as it is not designed to work well with the rest of the DOM agent. Use DOMSnapshot.captureSnapshot instead.
         * @return a stage completing with the command result
         * @deprecated Deprecated by the Chromium DevTools Protocol.
         */
        @Deprecated
        public CompletionStage<java.util.List<DOM.Node>> getFlattenedDocument() {
            return call("DOM.getFlattenedDocument", NODES::require);
        }
        /**
         * Returns the root DOM node (and optionally the subtree) to the caller. Deprecated, as it is not designed to work well with the rest of the DOM agent. Use DOMSnapshot.captureSnapshot instead.
         * @param request request parameters
         * @return a stage completing with the command result
         * @deprecated Deprecated by the Chromium DevTools Protocol.
         */
        @Deprecated
        public CompletionStage<java.util.List<DOM.Node>> getFlattenedDocument(GetFlattenedDocumentRequest request) {
            return call("DOM.getFlattenedDocument", request, NODES::require);
        }
        /**
         * Finds nodes with a given computed style in a subtree.
         * <p><b>Experimental:</b> this part of CDP may change without notice.
         * @param nodeId protocol value
         * @param computedStyles protocol value
         * @param pierce protocol value
         * @return a stage completing with the command result
         */
        public CompletionStage<java.util.List<DOM.NodeId>> getNodesForSubtreeByStyle(DOM.NodeId nodeId, java.util.List<DOM.CSSComputedStyleProperty> computedStyles, boolean pierce) {
            return call("DOM.getNodesForSubtreeByStyle", params("nodeId", nodeId, "computedStyles", computedStyles, "pierce", pierce), NODE_IDS::require);
        }
        /**
         * Finds nodes with a given computed style in a subtree.
         * <p><b>Experimental:</b> this part of CDP may change without notice.
         * @param nodeId protocol value
         * @param computedStyles protocol value
         * @return a stage completing with the command result
         */
        public CompletionStage<java.util.List<DOM.NodeId>> getNodesForSubtreeByStyle(DOM.NodeId nodeId, java.util.List<DOM.CSSComputedStyleProperty> computedStyles) {
            return call("DOM.getNodesForSubtreeByStyle", params("nodeId", nodeId, "computedStyles", computedStyles), NODE_IDS::require);
        }
        /**
         * Finds nodes with a given computed style in a subtree.
         * <p><b>Experimental:</b> this part of CDP may change without notice.
         * @param request request parameters
         * @return a stage completing with the command result
         */
        public CompletionStage<java.util.List<DOM.NodeId>> getNodesForSubtreeByStyle(GetNodesForSubtreeByStyleRequest request) {
            return call("DOM.getNodesForSubtreeByStyle", request, NODE_IDS::require);
        }
        /**
         * Returns node id at given location. Depending on whether DOM domain is enabled, nodeId is either returned or not.
         * @param x protocol value
         * @param y protocol value
         * @param includeUserAgentShadowDOM protocol value
         * @param ignorePointerEventsNone protocol value
         * @return a stage completing with the command result
         */
        public CompletionStage<GetNodeForLocationResult> getNodeForLocation(long x, long y, @Nullable Boolean includeUserAgentShadowDOM, @Nullable Boolean ignorePointerEventsNone) {
            return call("DOM.getNodeForLocation", params("x", x, "y", y, "includeUserAgentShadowDOM", includeUserAgentShadowDOM, "ignorePointerEventsNone", ignorePointerEventsNone), GetNodeForLocationResult::fromMap);
        }
        /**
         * Returns node id at given location. Depending on whether DOM domain is enabled, nodeId is either returned or not.
         * @param x protocol value
         * @param y protocol value
         * @return a stage completing with the command result
         */
        public CompletionStage<GetNodeForLocationResult> getNodeForLocation(long x, long y) {
            return call("DOM.getNodeForLocation", params("x", x, "y", y), GetNodeForLocationResult::fromMap);
        }
        /**
         * Returns node id at given location. Depending on whether DOM domain is enabled, nodeId is either returned or not.
         * @param request request parameters
         * @return a stage completing with the command result
         */
        public CompletionStage<GetNodeForLocationResult> getNodeForLocation(GetNodeForLocationRequest request) {
            return call("DOM.getNodeForLocation", request, GetNodeForLocationResult::fromMap);
        }
        /**
         * Returns node&#x27;s HTML markup.
         * @param nodeId protocol value
         * @param backendNodeId protocol value
         * @param objectId protocol value
         * @param includeShadowDOM protocol value
         * @return a stage completing with the command result
         */
        public CompletionStage<String> getOuterHTML(DOM.@Nullable NodeId nodeId, DOM.@Nullable BackendNodeId backendNodeId, Runtime.@Nullable RemoteObjectId objectId, @Nullable Boolean includeShadowDOM) {
            return call("DOM.getOuterHTML", params("nodeId", nodeId, "backendNodeId", backendNodeId, "objectId", objectId, "includeShadowDOM", includeShadowDOM), OUTER_HTML::require);
        }
        /**
         * Returns node&#x27;s HTML markup.
         * @return a stage completing with the command result
         */
        public CompletionStage<String> getOuterHTML() {
            return call("DOM.getOuterHTML", OUTER_HTML::require);
        }
        /**
         * Returns node&#x27;s HTML markup.
         * @param request request parameters
         * @return a stage completing with the command result
         */
        public CompletionStage<String> getOuterHTML(GetOuterHTMLRequest request) {
            return call("DOM.getOuterHTML", request, OUTER_HTML::require);
        }
        /**
         * Returns the id of the nearest ancestor that is a relayout boundary.
         * <p><b>Experimental:</b> this part of CDP may change without notice.
         * @param nodeId protocol value
         * @return a stage completing with the command result
         */
        public CompletionStage<DOM.NodeId> getRelayoutBoundary(DOM.NodeId nodeId) {
            return call("DOM.getRelayoutBoundary", params("nodeId", nodeId), NODE_ID::require);
        }
        /**
         * Returns the id of the nearest ancestor that is a relayout boundary.
         * <p><b>Experimental:</b> this part of CDP may change without notice.
         * @param request request parameters
         * @return a stage completing with the command result
         */
        public CompletionStage<DOM.NodeId> getRelayoutBoundary(GetRelayoutBoundaryRequest request) {
            return call("DOM.getRelayoutBoundary", request, NODE_ID::require);
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
            return call("DOM.getSearchResults", params("searchId", searchId, "fromIndex", fromIndex, "toIndex", toIndex), NODE_IDS::require);
        }
        /**
         * Returns search results from given {@code fromIndex} to given {@code toIndex} from the search with the given identifier.
         * <p><b>Experimental:</b> this part of CDP may change without notice.
         * @param request request parameters
         * @return a stage completing with the command result
         */
        public CompletionStage<java.util.List<DOM.NodeId>> getSearchResults(GetSearchResultsRequest request) {
            return call("DOM.getSearchResults", request, NODE_IDS::require);
        }
        /**
         * Hides any highlight.
         * @return a stage completing when the command completes
         */
        public CompletionStage<Void> hideHighlight() {
            return call("DOM.hideHighlight");
        }
        /**
         * Highlights DOM node.
         * @return a stage completing when the command completes
         */
        public CompletionStage<Void> highlightNode() {
            return call("DOM.highlightNode");
        }
        /**
         * Highlights given rectangle.
         * @return a stage completing when the command completes
         */
        public CompletionStage<Void> highlightRect() {
            return call("DOM.highlightRect");
        }
        /**
         * Marks last undoable state.
         * <p><b>Experimental:</b> this part of CDP may change without notice.
         * @return a stage completing when the command completes
         */
        public CompletionStage<Void> markUndoableState() {
            return call("DOM.markUndoableState");
        }
        /**
         * Moves node into the new container, places it before the given anchor.
         * @param nodeId protocol value
         * @param targetNodeId protocol value
         * @param insertBeforeNodeId protocol value
         * @return a stage completing with the command result
         */
        public CompletionStage<DOM.NodeId> moveTo(DOM.NodeId nodeId, DOM.NodeId targetNodeId, DOM.NodeId insertBeforeNodeId) {
            return call("DOM.moveTo", params("nodeId", nodeId, "targetNodeId", targetNodeId, "insertBeforeNodeId", insertBeforeNodeId), NODE_ID::require);
        }
        /**
         * Moves node into the new container, places it before the given anchor.
         * @param nodeId protocol value
         * @param targetNodeId protocol value
         * @return a stage completing with the command result
         */
        public CompletionStage<DOM.NodeId> moveTo(DOM.NodeId nodeId, DOM.NodeId targetNodeId) {
            return call("DOM.moveTo", params("nodeId", nodeId, "targetNodeId", targetNodeId), NODE_ID::require);
        }
        /**
         * Moves node into the new container, places it before the given anchor.
         * @param request request parameters
         * @return a stage completing with the command result
         */
        public CompletionStage<DOM.NodeId> moveTo(MoveToRequest request) {
            return call("DOM.moveTo", request, NODE_ID::require);
        }
        /**
         * Searches for a given string in the DOM tree. Use {@code getSearchResults} to access search results or {@code cancelSearch} to end this search session.
         * <p><b>Experimental:</b> this part of CDP may change without notice.
         * @param query protocol value
         * @param includeUserAgentShadowDOM protocol value
         * @return a stage completing with the command result
         */
        public CompletionStage<PerformSearchResult> performSearch(String query, boolean includeUserAgentShadowDOM) {
            return call("DOM.performSearch", params("query", query, "includeUserAgentShadowDOM", includeUserAgentShadowDOM), PerformSearchResult::fromMap);
        }
        /**
         * Searches for a given string in the DOM tree. Use {@code getSearchResults} to access search results or {@code cancelSearch} to end this search session.
         * <p><b>Experimental:</b> this part of CDP may change without notice.
         * @param query protocol value
         * @return a stage completing with the command result
         */
        public CompletionStage<PerformSearchResult> performSearch(String query) {
            return call("DOM.performSearch", params("query", query), PerformSearchResult::fromMap);
        }
        /**
         * Searches for a given string in the DOM tree. Use {@code getSearchResults} to access search results or {@code cancelSearch} to end this search session.
         * <p><b>Experimental:</b> this part of CDP may change without notice.
         * @param request request parameters
         * @return a stage completing with the command result
         */
        public CompletionStage<PerformSearchResult> performSearch(PerformSearchRequest request) {
            return call("DOM.performSearch", request, PerformSearchResult::fromMap);
        }
        /**
         * Requests that the node is sent to the caller given its path. // FIXME, use XPath
         * <p><b>Experimental:</b> this part of CDP may change without notice.
         * @param path protocol value
         * @return a stage completing with the command result
         */
        public CompletionStage<DOM.NodeId> pushNodeByPathToFrontend(String path) {
            return call("DOM.pushNodeByPathToFrontend", params("path", path), NODE_ID::require);
        }
        /**
         * Requests that the node is sent to the caller given its path. // FIXME, use XPath
         * <p><b>Experimental:</b> this part of CDP may change without notice.
         * @param request request parameters
         * @return a stage completing with the command result
         */
        public CompletionStage<DOM.NodeId> pushNodeByPathToFrontend(PushNodeByPathToFrontendRequest request) {
            return call("DOM.pushNodeByPathToFrontend", request, NODE_ID::require);
        }
        /**
         * Requests that a batch of nodes is sent to the caller given their backend node ids.
         * <p><b>Experimental:</b> this part of CDP may change without notice.
         * @param backendNodeIds protocol value
         * @return a stage completing with the command result
         */
        public CompletionStage<java.util.List<DOM.NodeId>> pushNodesByBackendIdsToFrontend(java.util.List<DOM.BackendNodeId> backendNodeIds) {
            return call("DOM.pushNodesByBackendIdsToFrontend", params("backendNodeIds", backendNodeIds), NODE_IDS::require);
        }
        /**
         * Requests that a batch of nodes is sent to the caller given their backend node ids.
         * <p><b>Experimental:</b> this part of CDP may change without notice.
         * @param request request parameters
         * @return a stage completing with the command result
         */
        public CompletionStage<java.util.List<DOM.NodeId>> pushNodesByBackendIdsToFrontend(PushNodesByBackendIdsToFrontendRequest request) {
            return call("DOM.pushNodesByBackendIdsToFrontend", request, NODE_IDS::require);
        }
        /**
         * Executes {@code querySelector} on a given node.
         * @param nodeId protocol value
         * @param selector protocol value
         * @return a stage completing with the command result
         */
        public CompletionStage<DOM.NodeId> querySelector(DOM.NodeId nodeId, String selector) {
            return call("DOM.querySelector", params("nodeId", nodeId, "selector", selector), NODE_ID::require);
        }
        /**
         * Executes {@code querySelector} on a given node.
         * @param request request parameters
         * @return a stage completing with the command result
         */
        public CompletionStage<DOM.NodeId> querySelector(QuerySelectorRequest request) {
            return call("DOM.querySelector", request, NODE_ID::require);
        }
        /**
         * Executes {@code querySelectorAll} on a given node.
         * @param nodeId protocol value
         * @param selector protocol value
         * @return a stage completing with the command result
         */
        public CompletionStage<java.util.List<DOM.NodeId>> querySelectorAll(DOM.NodeId nodeId, String selector) {
            return call("DOM.querySelectorAll", params("nodeId", nodeId, "selector", selector), NODE_IDS::require);
        }
        /**
         * Executes {@code querySelectorAll} on a given node.
         * @param request request parameters
         * @return a stage completing with the command result
         */
        public CompletionStage<java.util.List<DOM.NodeId>> querySelectorAll(QuerySelectorAllRequest request) {
            return call("DOM.querySelectorAll", request, NODE_IDS::require);
        }
        /**
         * Returns NodeIds of current top layer elements. Top layer is rendered closest to the user within a viewport, therefore its elements always appear on top of all other content.
         * <p><b>Experimental:</b> this part of CDP may change without notice.
         * @return a stage completing with the command result
         */
        public CompletionStage<java.util.List<DOM.NodeId>> getTopLayerElements() {
            return call("DOM.getTopLayerElements", NODE_IDS::require);
        }
        /**
         * Returns the NodeId of the matched element according to certain relations.
         * <p><b>Experimental:</b> this part of CDP may change without notice.
         * @param nodeId protocol value
         * @param relation protocol value
         * @return a stage completing with the command result
         */
        public CompletionStage<DOM.NodeId> getElementByRelation(DOM.NodeId nodeId, GetElementByRelationRelationValues relation) {
            return call("DOM.getElementByRelation", params("nodeId", nodeId, "relation", relation), NODE_ID::require);
        }
        /**
         * Returns the NodeId of the matched element according to certain relations.
         * <p><b>Experimental:</b> this part of CDP may change without notice.
         * @param request request parameters
         * @return a stage completing with the command result
         */
        public CompletionStage<DOM.NodeId> getElementByRelation(GetElementByRelationRequest request) {
            return call("DOM.getElementByRelation", request, NODE_ID::require);
        }
        /**
         * Re-does the last undone action.
         * <p><b>Experimental:</b> this part of CDP may change without notice.
         * @return a stage completing when the command completes
         */
        public CompletionStage<Void> redo() {
            return call("DOM.redo");
        }
        /**
         * Removes attribute with given name from an element with given id.
         * @param nodeId protocol value
         * @param name protocol value
         * @return a stage completing when the command completes
         */
        public CompletionStage<Void> removeAttribute(DOM.NodeId nodeId, String name) {
            return call("DOM.removeAttribute", params("nodeId", nodeId, "name", name));
        }
        /**
         * Removes attribute with given name from an element with given id.
         * @param request request parameters
         * @return a stage completing when the command completes
         */
        public CompletionStage<Void> removeAttribute(RemoveAttributeRequest request) {
            return call("DOM.removeAttribute", request);
        }
        /**
         * Removes node with given id.
         * @param nodeId protocol value
         * @return a stage completing when the command completes
         */
        public CompletionStage<Void> removeNode(DOM.NodeId nodeId) {
            return call("DOM.removeNode", params("nodeId", nodeId));
        }
        /**
         * Removes node with given id.
         * @param request request parameters
         * @return a stage completing when the command completes
         */
        public CompletionStage<Void> removeNode(RemoveNodeRequest request) {
            return call("DOM.removeNode", request);
        }
        /**
         * Requests that children of the node with given id are returned to the caller in form of {@code setChildNodes} events where not only immediate children are retrieved, but all children down to the specified depth.
         * @param nodeId protocol value
         * @param depth protocol value
         * @param pierce protocol value
         * @return a stage completing when the command completes
         */
        public CompletionStage<Void> requestChildNodes(DOM.NodeId nodeId, @Nullable Long depth, @Nullable Boolean pierce) {
            return call("DOM.requestChildNodes", params("nodeId", nodeId, "depth", depth, "pierce", pierce));
        }
        /**
         * Requests that children of the node with given id are returned to the caller in form of {@code setChildNodes} events where not only immediate children are retrieved, but all children down to the specified depth.
         * @param nodeId protocol value
         * @return a stage completing when the command completes
         */
        public CompletionStage<Void> requestChildNodes(DOM.NodeId nodeId) {
            return call("DOM.requestChildNodes", params("nodeId", nodeId));
        }
        /**
         * Requests that children of the node with given id are returned to the caller in form of {@code setChildNodes} events where not only immediate children are retrieved, but all children down to the specified depth.
         * @param request request parameters
         * @return a stage completing when the command completes
         */
        public CompletionStage<Void> requestChildNodes(RequestChildNodesRequest request) {
            return call("DOM.requestChildNodes", request);
        }
        /**
         * Requests that the node is sent to the caller given the JavaScript node object reference. All nodes that form the path from the node to the root are also sent to the client as a series of {@code setChildNodes} notifications.
         * @param objectId protocol value
         * @return a stage completing with the command result
         */
        public CompletionStage<DOM.NodeId> requestNode(Runtime.RemoteObjectId objectId) {
            return call("DOM.requestNode", params("objectId", objectId), NODE_ID::require);
        }
        /**
         * Requests that the node is sent to the caller given the JavaScript node object reference. All nodes that form the path from the node to the root are also sent to the client as a series of {@code setChildNodes} notifications.
         * @param request request parameters
         * @return a stage completing with the command result
         */
        public CompletionStage<DOM.NodeId> requestNode(RequestNodeRequest request) {
            return call("DOM.requestNode", request, NODE_ID::require);
        }
        /**
         * Resolves the JavaScript node object for a given NodeId or BackendNodeId.
         * @param nodeId protocol value
         * @param backendNodeId protocol value
         * @param objectGroup protocol value
         * @param executionContextId protocol value
         * @return a stage completing with the command result
         */
        public CompletionStage<Runtime.RemoteObject> resolveNode(DOM.@Nullable NodeId nodeId, DOM.@Nullable BackendNodeId backendNodeId, @Nullable String objectGroup, Runtime.@Nullable ExecutionContextId executionContextId) {
            return call("DOM.resolveNode", params("nodeId", nodeId, "backendNodeId", backendNodeId, "objectGroup", objectGroup, "executionContextId", executionContextId), OBJECT::require);
        }
        /**
         * Resolves the JavaScript node object for a given NodeId or BackendNodeId.
         * @return a stage completing with the command result
         */
        public CompletionStage<Runtime.RemoteObject> resolveNode() {
            return call("DOM.resolveNode", OBJECT::require);
        }
        /**
         * Resolves the JavaScript node object for a given NodeId or BackendNodeId.
         * @param request request parameters
         * @return a stage completing with the command result
         */
        public CompletionStage<Runtime.RemoteObject> resolveNode(ResolveNodeRequest request) {
            return call("DOM.resolveNode", request, OBJECT::require);
        }
        /**
         * Sets attribute for an element with given id.
         * @param nodeId protocol value
         * @param name protocol value
         * @param value protocol value
         * @return a stage completing when the command completes
         */
        public CompletionStage<Void> setAttributeValue(DOM.NodeId nodeId, String name, String value) {
            return call("DOM.setAttributeValue", params("nodeId", nodeId, "name", name, "value", value));
        }
        /**
         * Sets attribute for an element with given id.
         * @param request request parameters
         * @return a stage completing when the command completes
         */
        public CompletionStage<Void> setAttributeValue(SetAttributeValueRequest request) {
            return call("DOM.setAttributeValue", request);
        }
        /**
         * Sets attributes on element with given id. This method is useful when user edits some existing attribute value and types in several attribute name/value pairs.
         * @param nodeId protocol value
         * @param text protocol value
         * @param name protocol value
         * @return a stage completing when the command completes
         */
        public CompletionStage<Void> setAttributesAsText(DOM.NodeId nodeId, String text, String name) {
            return call("DOM.setAttributesAsText", params("nodeId", nodeId, "text", text, "name", name));
        }
        /**
         * Sets attributes on element with given id. This method is useful when user edits some existing attribute value and types in several attribute name/value pairs.
         * @param nodeId protocol value
         * @param text protocol value
         * @return a stage completing when the command completes
         */
        public CompletionStage<Void> setAttributesAsText(DOM.NodeId nodeId, String text) {
            return call("DOM.setAttributesAsText", params("nodeId", nodeId, "text", text));
        }
        /**
         * Sets attributes on element with given id. This method is useful when user edits some existing attribute value and types in several attribute name/value pairs.
         * @param request request parameters
         * @return a stage completing when the command completes
         */
        public CompletionStage<Void> setAttributesAsText(SetAttributesAsTextRequest request) {
            return call("DOM.setAttributesAsText", request);
        }
        /**
         * Sets files for the given file input element.
         * @param files protocol value
         * @param nodeId protocol value
         * @param backendNodeId protocol value
         * @param objectId protocol value
         * @return a stage completing when the command completes
         */
        public CompletionStage<Void> setFileInputFiles(java.util.List<String> files, DOM.@Nullable NodeId nodeId, DOM.@Nullable BackendNodeId backendNodeId, Runtime.@Nullable RemoteObjectId objectId) {
            return call("DOM.setFileInputFiles", params("files", files, "nodeId", nodeId, "backendNodeId", backendNodeId, "objectId", objectId));
        }
        /**
         * Sets files for the given file input element.
         * @param files protocol value
         * @return a stage completing when the command completes
         */
        public CompletionStage<Void> setFileInputFiles(java.util.List<String> files) {
            return call("DOM.setFileInputFiles", params("files", files));
        }
        /**
         * Sets files for the given file input element.
         * @param request request parameters
         * @return a stage completing when the command completes
         */
        public CompletionStage<Void> setFileInputFiles(SetFileInputFilesRequest request) {
            return call("DOM.setFileInputFiles", request);
        }
        /**
         * Sets if stack traces should be captured for Nodes. See {@code Node.getNodeStackTraces}. Default is disabled.
         * <p><b>Experimental:</b> this part of CDP may change without notice.
         * @param enable protocol value
         * @return a stage completing when the command completes
         */
        public CompletionStage<Void> setNodeStackTracesEnabled(boolean enable) {
            return call("DOM.setNodeStackTracesEnabled", params("enable", enable));
        }
        /**
         * Sets if stack traces should be captured for Nodes. See {@code Node.getNodeStackTraces}. Default is disabled.
         * <p><b>Experimental:</b> this part of CDP may change without notice.
         * @param request request parameters
         * @return a stage completing when the command completes
         */
        public CompletionStage<Void> setNodeStackTracesEnabled(SetNodeStackTracesEnabledRequest request) {
            return call("DOM.setNodeStackTracesEnabled", request);
        }
        /**
         * Gets stack traces associated with a Node. As of now, only provides stack trace for Node creation.
         * <p><b>Experimental:</b> this part of CDP may change without notice.
         * @param nodeId protocol value
         * @return a stage completing with the command result
         */
        public CompletionStage<Optional<Runtime.StackTrace>> getNodeStackTraces(DOM.NodeId nodeId) {
            return call("DOM.getNodeStackTraces", params("nodeId", nodeId), CREATION::optional);
        }
        /**
         * Gets stack traces associated with a Node. As of now, only provides stack trace for Node creation.
         * <p><b>Experimental:</b> this part of CDP may change without notice.
         * @param request request parameters
         * @return a stage completing with the command result
         */
        public CompletionStage<Optional<Runtime.StackTrace>> getNodeStackTraces(GetNodeStackTracesRequest request) {
            return call("DOM.getNodeStackTraces", request, CREATION::optional);
        }
        /**
         * Returns file information for the given File wrapper.
         * <p><b>Experimental:</b> this part of CDP may change without notice.
         * @param objectId protocol value
         * @return a stage completing with the command result
         */
        public CompletionStage<String> getFileInfo(Runtime.RemoteObjectId objectId) {
            return call("DOM.getFileInfo", params("objectId", objectId), PATH::require);
        }
        /**
         * Returns file information for the given File wrapper.
         * <p><b>Experimental:</b> this part of CDP may change without notice.
         * @param request request parameters
         * @return a stage completing with the command result
         */
        public CompletionStage<String> getFileInfo(GetFileInfoRequest request) {
            return call("DOM.getFileInfo", request, PATH::require);
        }
        /**
         * Returns list of detached nodes
         * <p><b>Experimental:</b> this part of CDP may change without notice.
         * @return a stage completing with the command result
         */
        public CompletionStage<java.util.List<DOM.DetachedElementInfo>> getDetachedDomNodes() {
            return call("DOM.getDetachedDomNodes", DETACHED_NODES::require);
        }
        /**
         * Enables console to refer to the node with given id via $x (see Command Line API for more details $x functions).
         * <p><b>Experimental:</b> this part of CDP may change without notice.
         * @param nodeId protocol value
         * @return a stage completing when the command completes
         */
        public CompletionStage<Void> setInspectedNode(DOM.NodeId nodeId) {
            return call("DOM.setInspectedNode", params("nodeId", nodeId));
        }
        /**
         * Enables console to refer to the node with given id via $x (see Command Line API for more details $x functions).
         * <p><b>Experimental:</b> this part of CDP may change without notice.
         * @param request request parameters
         * @return a stage completing when the command completes
         */
        public CompletionStage<Void> setInspectedNode(SetInspectedNodeRequest request) {
            return call("DOM.setInspectedNode", request);
        }
        /**
         * Sets node name for a node with given id.
         * @param nodeId protocol value
         * @param name protocol value
         * @return a stage completing with the command result
         */
        public CompletionStage<DOM.NodeId> setNodeName(DOM.NodeId nodeId, String name) {
            return call("DOM.setNodeName", params("nodeId", nodeId, "name", name), NODE_ID::require);
        }
        /**
         * Sets node name for a node with given id.
         * @param request request parameters
         * @return a stage completing with the command result
         */
        public CompletionStage<DOM.NodeId> setNodeName(SetNodeNameRequest request) {
            return call("DOM.setNodeName", request, NODE_ID::require);
        }
        /**
         * Sets node value for a node with given id.
         * @param nodeId protocol value
         * @param value protocol value
         * @return a stage completing when the command completes
         */
        public CompletionStage<Void> setNodeValue(DOM.NodeId nodeId, String value) {
            return call("DOM.setNodeValue", params("nodeId", nodeId, "value", value));
        }
        /**
         * Sets node value for a node with given id.
         * @param request request parameters
         * @return a stage completing when the command completes
         */
        public CompletionStage<Void> setNodeValue(SetNodeValueRequest request) {
            return call("DOM.setNodeValue", request);
        }
        /**
         * Sets node HTML markup, returns new node id.
         * @param nodeId protocol value
         * @param outerHTML protocol value
         * @return a stage completing when the command completes
         */
        public CompletionStage<Void> setOuterHTML(DOM.NodeId nodeId, String outerHTML) {
            return call("DOM.setOuterHTML", params("nodeId", nodeId, "outerHTML", outerHTML));
        }
        /**
         * Sets node HTML markup, returns new node id.
         * @param request request parameters
         * @return a stage completing when the command completes
         */
        public CompletionStage<Void> setOuterHTML(SetOuterHTMLRequest request) {
            return call("DOM.setOuterHTML", request);
        }
        /**
         * Undoes the last performed action.
         * <p><b>Experimental:</b> this part of CDP may change without notice.
         * @return a stage completing when the command completes
         */
        public CompletionStage<Void> undo() {
            return call("DOM.undo");
        }
        /**
         * Returns iframe node that owns iframe with the given domain.
         * <p><b>Experimental:</b> this part of CDP may change without notice.
         * @param frameId protocol value
         * @return a stage completing with the command result
         */
        public CompletionStage<GetFrameOwnerResult> getFrameOwner(Page.FrameId frameId) {
            return call("DOM.getFrameOwner", params("frameId", frameId), GetFrameOwnerResult::fromMap);
        }
        /**
         * Returns iframe node that owns iframe with the given domain.
         * <p><b>Experimental:</b> this part of CDP may change without notice.
         * @param request request parameters
         * @return a stage completing with the command result
         */
        public CompletionStage<GetFrameOwnerResult> getFrameOwner(GetFrameOwnerRequest request) {
            return call("DOM.getFrameOwner", request, GetFrameOwnerResult::fromMap);
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
        public CompletionStage<Optional<DOM.NodeId>> getContainerForNode(DOM.NodeId nodeId, @Nullable String containerName, DOM.@Nullable PhysicalAxes physicalAxes, DOM.@Nullable LogicalAxes logicalAxes, @Nullable Boolean queriesScrollState, @Nullable Boolean queriesAnchored) {
            return call("DOM.getContainerForNode", params("nodeId", nodeId, "containerName", containerName, "physicalAxes", physicalAxes, "logicalAxes", logicalAxes, "queriesScrollState", queriesScrollState, "queriesAnchored", queriesAnchored), NODE_ID::optional);
        }
        /**
         * Returns the query container of the given node based on container query conditions: containerName, physical and logical axes, and whether it queries scroll-state or anchored elements. If no axes are provided and queriesScrollState is false, the style container is returned, which is the direct parent or the closest element with a matching container-name.
         * <p><b>Experimental:</b> this part of CDP may change without notice.
         * @param nodeId protocol value
         * @return a stage completing with the command result
         */
        public CompletionStage<Optional<DOM.NodeId>> getContainerForNode(DOM.NodeId nodeId) {
            return call("DOM.getContainerForNode", params("nodeId", nodeId), NODE_ID::optional);
        }
        /**
         * Returns the query container of the given node based on container query conditions: containerName, physical and logical axes, and whether it queries scroll-state or anchored elements. If no axes are provided and queriesScrollState is false, the style container is returned, which is the direct parent or the closest element with a matching container-name.
         * <p><b>Experimental:</b> this part of CDP may change without notice.
         * @param request request parameters
         * @return a stage completing with the command result
         */
        public CompletionStage<Optional<DOM.NodeId>> getContainerForNode(GetContainerForNodeRequest request) {
            return call("DOM.getContainerForNode", request, NODE_ID::optional);
        }
        /**
         * Returns the descendants of a container query container that have container queries against this container.
         * <p><b>Experimental:</b> this part of CDP may change without notice.
         * @param nodeId protocol value
         * @return a stage completing with the command result
         */
        public CompletionStage<java.util.List<DOM.NodeId>> getQueryingDescendantsForContainer(DOM.NodeId nodeId) {
            return call("DOM.getQueryingDescendantsForContainer", params("nodeId", nodeId), NODE_IDS::require);
        }
        /**
         * Returns the descendants of a container query container that have container queries against this container.
         * <p><b>Experimental:</b> this part of CDP may change without notice.
         * @param request request parameters
         * @return a stage completing with the command result
         */
        public CompletionStage<java.util.List<DOM.NodeId>> getQueryingDescendantsForContainer(GetQueryingDescendantsForContainerRequest request) {
            return call("DOM.getQueryingDescendantsForContainer", request, NODE_IDS::require);
        }
        /**
         * Returns the target anchor element of the given anchor query according to https://www.w3.org/TR/css-anchor-position-1/#target.
         * <p><b>Experimental:</b> this part of CDP may change without notice.
         * @param nodeId protocol value
         * @param anchorSpecifier protocol value
         * @return a stage completing with the command result
         */
        public CompletionStage<DOM.NodeId> getAnchorElement(DOM.NodeId nodeId, String anchorSpecifier) {
            return call("DOM.getAnchorElement", params("nodeId", nodeId, "anchorSpecifier", anchorSpecifier), NODE_ID::require);
        }
        /**
         * Returns the target anchor element of the given anchor query according to https://www.w3.org/TR/css-anchor-position-1/#target.
         * <p><b>Experimental:</b> this part of CDP may change without notice.
         * @param nodeId protocol value
         * @return a stage completing with the command result
         */
        public CompletionStage<DOM.NodeId> getAnchorElement(DOM.NodeId nodeId) {
            return call("DOM.getAnchorElement", params("nodeId", nodeId), NODE_ID::require);
        }
        /**
         * Returns the target anchor element of the given anchor query according to https://www.w3.org/TR/css-anchor-position-1/#target.
         * <p><b>Experimental:</b> this part of CDP may change without notice.
         * @param request request parameters
         * @return a stage completing with the command result
         */
        public CompletionStage<DOM.NodeId> getAnchorElement(GetAnchorElementRequest request) {
            return call("DOM.getAnchorElement", request, NODE_ID::require);
        }
        /**
         * When enabling, this API force-opens the popover identified by nodeId and keeps it open until disabled.
         * <p><b>Experimental:</b> this part of CDP may change without notice.
         * @param nodeId protocol value
         * @param enable protocol value
         * @return a stage completing with the command result
         */
        public CompletionStage<java.util.List<DOM.NodeId>> forceShowPopover(DOM.NodeId nodeId, boolean enable) {
            return call("DOM.forceShowPopover", params("nodeId", nodeId, "enable", enable), NODE_IDS::require);
        }
        /**
         * When enabling, this API force-opens the popover identified by nodeId and keeps it open until disabled.
         * <p><b>Experimental:</b> this part of CDP may change without notice.
         * @param request request parameters
         * @return a stage completing with the command result
         */
        public CompletionStage<java.util.List<DOM.NodeId>> forceShowPopover(ForceShowPopoverRequest request) {
            return call("DOM.forceShowPopover", request, NODE_IDS::require);
        }
        /**
         * Fired when {@code Element}&#x27;s attribute is modified.
         * @param handler event callback
         * @return a removable subscription
         */
        public CdpSubscription onAttributeModified(Consumer<AttributeModifiedEvent> handler) {
            return on("DOM.attributeModified", AttributeModifiedEvent::fromMap, handler);
        }
        /**
         * Fired when {@code Element}&#x27;s adoptedStyleSheets are modified.
         * <p><b>Experimental:</b> this part of CDP may change without notice.
         * @param handler event callback
         * @return a removable subscription
         */
        public CdpSubscription onAdoptedStyleSheetsModified(Consumer<AdoptedStyleSheetsModifiedEvent> handler) {
            return on("DOM.adoptedStyleSheetsModified", AdoptedStyleSheetsModifiedEvent::fromMap, handler);
        }
        /**
         * Fired when {@code Element}&#x27;s attribute is removed.
         * @param handler event callback
         * @return a removable subscription
         */
        public CdpSubscription onAttributeRemoved(Consumer<AttributeRemovedEvent> handler) {
            return on("DOM.attributeRemoved", AttributeRemovedEvent::fromMap, handler);
        }
        /**
         * Mirrors {@code DOMCharacterDataModified} event.
         * @param handler event callback
         * @return a removable subscription
         */
        public CdpSubscription onCharacterDataModified(Consumer<CharacterDataModifiedEvent> handler) {
            return on("DOM.characterDataModified", CharacterDataModifiedEvent::fromMap, handler);
        }
        /**
         * Fired when {@code Container}&#x27;s child node count has changed.
         * @param handler event callback
         * @return a removable subscription
         */
        public CdpSubscription onChildNodeCountUpdated(Consumer<ChildNodeCountUpdatedEvent> handler) {
            return on("DOM.childNodeCountUpdated", ChildNodeCountUpdatedEvent::fromMap, handler);
        }
        /**
         * Mirrors {@code DOMNodeInserted} event.
         * @param handler event callback
         * @return a removable subscription
         */
        public CdpSubscription onChildNodeInserted(Consumer<ChildNodeInsertedEvent> handler) {
            return on("DOM.childNodeInserted", ChildNodeInsertedEvent::fromMap, handler);
        }
        /**
         * Mirrors {@code DOMNodeRemoved} event.
         * @param handler event callback
         * @return a removable subscription
         */
        public CdpSubscription onChildNodeRemoved(Consumer<ChildNodeRemovedEvent> handler) {
            return on("DOM.childNodeRemoved", ChildNodeRemovedEvent::fromMap, handler);
        }
        /**
         * Called when distribution is changed.
         * <p><b>Experimental:</b> this part of CDP may change without notice.
         * @param handler event callback
         * @return a removable subscription
         */
        public CdpSubscription onDistributedNodesUpdated(Consumer<DistributedNodesUpdatedEvent> handler) {
            return on("DOM.distributedNodesUpdated", DistributedNodesUpdatedEvent::fromMap, handler);
        }
        /**
         * Fired when {@code Document} has been totally updated. Node ids are no longer valid.
         * @param handler event callback
         * @return a removable subscription
         */
        public CdpSubscription onDocumentUpdated(Consumer<DocumentUpdatedEvent> handler) {
            return on("DOM.documentUpdated", DocumentUpdatedEvent::fromMap, handler);
        }
        /**
         * Fired when {@code Element}&#x27;s inline style is modified via a CSS property modification.
         * <p><b>Experimental:</b> this part of CDP may change without notice.
         * @param handler event callback
         * @return a removable subscription
         */
        public CdpSubscription onInlineStyleInvalidated(Consumer<InlineStyleInvalidatedEvent> handler) {
            return on("DOM.inlineStyleInvalidated", InlineStyleInvalidatedEvent::fromMap, handler);
        }
        /**
         * Called when a pseudo element is added to an element.
         * <p><b>Experimental:</b> this part of CDP may change without notice.
         * @param handler event callback
         * @return a removable subscription
         */
        public CdpSubscription onPseudoElementAdded(Consumer<PseudoElementAddedEvent> handler) {
            return on("DOM.pseudoElementAdded", PseudoElementAddedEvent::fromMap, handler);
        }
        /**
         * Called when top layer elements are changed.
         * <p><b>Experimental:</b> this part of CDP may change without notice.
         * @param handler event callback
         * @return a removable subscription
         */
        public CdpSubscription onTopLayerElementsUpdated(Consumer<TopLayerElementsUpdatedEvent> handler) {
            return on("DOM.topLayerElementsUpdated", TopLayerElementsUpdatedEvent::fromMap, handler);
        }
        /**
         * Fired when a node&#x27;s scrollability state changes.
         * <p><b>Experimental:</b> this part of CDP may change without notice.
         * @param handler event callback
         * @return a removable subscription
         */
        public CdpSubscription onScrollableFlagUpdated(Consumer<ScrollableFlagUpdatedEvent> handler) {
            return on("DOM.scrollableFlagUpdated", ScrollableFlagUpdatedEvent::fromMap, handler);
        }
        /**
         * Fired when a node&#x27;s ad related state changes.
         * <p><b>Experimental:</b> this part of CDP may change without notice.
         * @param handler event callback
         * @return a removable subscription
         */
        public CdpSubscription onAdRelatedStateUpdated(Consumer<AdRelatedStateUpdatedEvent> handler) {
            return on("DOM.adRelatedStateUpdated", AdRelatedStateUpdatedEvent::fromMap, handler);
        }
        /**
         * Fired when a node&#x27;s starting styles changes.
         * <p><b>Experimental:</b> this part of CDP may change without notice.
         * @param handler event callback
         * @return a removable subscription
         */
        public CdpSubscription onAffectedByStartingStylesFlagUpdated(Consumer<AffectedByStartingStylesFlagUpdatedEvent> handler) {
            return on("DOM.affectedByStartingStylesFlagUpdated", AffectedByStartingStylesFlagUpdatedEvent::fromMap, handler);
        }
        /**
         * Called when a pseudo element is removed from an element.
         * <p><b>Experimental:</b> this part of CDP may change without notice.
         * @param handler event callback
         * @return a removable subscription
         */
        public CdpSubscription onPseudoElementRemoved(Consumer<PseudoElementRemovedEvent> handler) {
            return on("DOM.pseudoElementRemoved", PseudoElementRemovedEvent::fromMap, handler);
        }
        /**
         * Fired when backend wants to provide client with the missing DOM structure. This happens upon most of the calls requesting node ids.
         * @param handler event callback
         * @return a removable subscription
         */
        public CdpSubscription onSetChildNodes(Consumer<SetChildNodesEvent> handler) {
            return on("DOM.setChildNodes", SetChildNodesEvent::fromMap, handler);
        }
        /**
         * Called when shadow root is popped from the element.
         * <p><b>Experimental:</b> this part of CDP may change without notice.
         * @param handler event callback
         * @return a removable subscription
         */
        public CdpSubscription onShadowRootPopped(Consumer<ShadowRootPoppedEvent> handler) {
            return on("DOM.shadowRootPopped", ShadowRootPoppedEvent::fromMap, handler);
        }
        /**
         * Called when shadow root is pushed into the element.
         * <p><b>Experimental:</b> this part of CDP may change without notice.
         * @param handler event callback
         * @return a removable subscription
         */
        public CdpSubscription onShadowRootPushed(Consumer<ShadowRootPushedEvent> handler) {
            return on("DOM.shadowRootPushed", ShadowRootPushedEvent::fromMap, handler);
        }
    }
}
