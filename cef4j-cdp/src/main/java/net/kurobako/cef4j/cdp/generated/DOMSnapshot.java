// GENERATED - do not edit. Regenerate via: ./mvnw generate-sources -pl cef4j-cdp -Dcef.version=150.0.18+gdb11278+chromium-150.0.7871.213
package net.kurobako.cef4j.cdp.generated;

import java.util.Map;
import java.util.Optional;
import java.util.OptionalDouble;
import java.util.OptionalLong;
import java.util.concurrent.CompletionStage;
import javax.annotation.processing.Generated;
import net.kurobako.cef4j.cdp.CdpClient;
import net.kurobako.cef4j.cdp.CdpDomainClient;
import net.kurobako.cef4j.cdp.CdpField;
import net.kurobako.cef4j.cdp.CdpObject;
import net.kurobako.cef4j.cdp.CdpValue;
import net.kurobako.cef4j.policy.NullableBoundary;
import org.jspecify.annotations.Nullable;

/**
 * This domain facilitates obtaining document snapshots with DOM, layout, and style information.
 * <p><b>Experimental:</b> this part of CDP may change without notice.
 * @see <a href="https://chromium.googlesource.com/chromium/src/+/refs/tags/150.0.7871.213/third_party/blink/public/devtools_protocol/domains/DOMSnapshot.pdl">Pinned protocol source</a>
 */
@Generated("./mvnw generate-sources -pl cef4j-cdp -Dcef.version=150.0.18+gdb11278+chromium-150.0.7871.213")
@SuppressWarnings({"EscapedEntity", "InvalidParam", "JavaLangClash", "MissingSummary", "Unchecked", "UnusedMethod"})
@NullableBoundary("CDP wire objects use null for absent optional fields")
public final class DOMSnapshot {
    private DOMSnapshot() {}
    private static final CdpField<java.util.List<DOMSnapshot.NameValue>> ATTRIBUTES = CdpField.of("attributes", value -> CdpObject.requireList(value, element0 -> java.util.Objects.requireNonNull(DOMSnapshot.NameValue.fromMap(CdpObject.requireObjectMap(element0)))));
    private static final CdpField<java.util.List<java.util.List<DOMSnapshot.StringIndex>>> ATTRIBUTES_2 = CdpField.of("attributes", value -> CdpObject.requireList(value, element0 -> CdpObject.requireList(element0, element1 -> new DOMSnapshot.StringIndex(((Number) element1).longValue()))));
    private static final CdpField<DOM.BackendNodeId> BACKEND_NODE_ID = CdpField.of("backendNodeId", value -> new DOM.BackendNodeId(((Number) value).longValue()));
    private static final CdpField<java.util.List<DOM.BackendNodeId>> BACKEND_NODE_ID_2 = CdpField.of("backendNodeId", value -> CdpObject.requireList(value, element0 -> new DOM.BackendNodeId(((Number) element0).longValue())));
    private static final CdpField<DOMSnapshot.StringIndex> BASE_URL = CdpField.of("baseURL", value -> new DOMSnapshot.StringIndex(((Number) value).longValue()));
    private static final CdpField<java.util.List<DOMSnapshot.StringIndex>> BLENDED_BACKGROUND_COLORS = CdpField.of("blendedBackgroundColors", value -> CdpObject.requireList(value, element0 -> new DOMSnapshot.StringIndex(((Number) element0).longValue())));
    private static final CdpField<DOM.Rect> BOUNDING_BOX = CdpField.of("boundingBox", value -> java.util.Objects.requireNonNull(DOM.Rect.fromMap(CdpObject.requireObjectMap(value))));
    private static final CdpField<java.util.List<java.util.List<Double>>> BOUNDS = CdpField.of("bounds", value -> CdpObject.requireList(value, element0 -> CdpObject.requireList(element0, element1 -> ((Number) element1).doubleValue())));
    private static final CdpField<java.util.List<Long>> CHILD_NODE_INDEXES = CdpField.of("childNodeIndexes", value -> CdpObject.requireList(value, element0 -> ((Number) element0).longValue()));
    private static final CdpField<java.util.List<java.util.List<Double>>> CLIENT_RECTS = CdpField.of("clientRects", value -> CdpObject.requireList(value, element0 -> CdpObject.requireList(element0, element1 -> ((Number) element1).doubleValue())));
    private static final CdpField<java.util.List<String>> COMPUTED_STYLES = CdpField.of("computedStyles", value -> CdpObject.requireList(value, element0 -> (String) element0));
    private static final CdpField<java.util.List<DOMSnapshot.ComputedStyle>> COMPUTED_STYLES_2 = CdpField.of("computedStyles", value -> CdpObject.requireList(value, element0 -> java.util.Objects.requireNonNull(DOMSnapshot.ComputedStyle.fromMap(CdpObject.requireObjectMap(element0)))));
    private static final CdpField<java.util.List<String>> COMPUTED_STYLE_WHITELIST = CdpField.of("computedStyleWhitelist", value -> CdpObject.requireList(value, element0 -> (String) element0));
    private static final CdpField<DOMSnapshot.RareIntegerData> CONTENT_DOCUMENT_INDEX = CdpField.of("contentDocumentIndex", value -> java.util.Objects.requireNonNull(DOMSnapshot.RareIntegerData.fromMap(CdpObject.requireObjectMap(value))));
    private static final CdpField<DOMSnapshot.StringIndex> CONTENT_LANGUAGE = CdpField.of("contentLanguage", value -> new DOMSnapshot.StringIndex(((Number) value).longValue()));
    private static final CdpField<DOMSnapshot.RareStringData> CURRENT_SOURCE_URL = CdpField.of("currentSourceURL", value -> java.util.Objects.requireNonNull(DOMSnapshot.RareStringData.fromMap(CdpObject.requireObjectMap(value))));
    private static final CdpField<java.util.List<DOMSnapshot.DocumentSnapshot>> DOCUMENTS = CdpField.of("documents", value -> CdpObject.requireList(value, element0 -> java.util.Objects.requireNonNull(DOMSnapshot.DocumentSnapshot.fromMap(CdpObject.requireObjectMap(element0)))));
    private static final CdpField<DOMSnapshot.StringIndex> DOCUMENT_URL = CdpField.of("documentURL", value -> new DOMSnapshot.StringIndex(((Number) value).longValue()));
    private static final CdpField<java.util.List<DOMSnapshot.DOMNode>> DOM_NODES = CdpField.of("domNodes", value -> CdpObject.requireList(value, element0 -> java.util.Objects.requireNonNull(DOMSnapshot.DOMNode.fromMap(CdpObject.requireObjectMap(element0)))));
    private static final CdpField<DOMSnapshot.StringIndex> ENCODING_NAME = CdpField.of("encodingName", value -> new DOMSnapshot.StringIndex(((Number) value).longValue()));
    private static final CdpField<java.util.List<DOMDebugger.EventListener>> EVENT_LISTENERS = CdpField.of("eventListeners", value -> CdpObject.requireList(value, element0 -> java.util.Objects.requireNonNull(DOMDebugger.EventListener.fromMap(CdpObject.requireObjectMap(element0)))));
    private static final CdpField<Page.FrameId> FRAME_ID = CdpField.of("frameId", value -> new Page.FrameId((String) value));
    private static final CdpField<DOMSnapshot.StringIndex> FRAME_ID_2 = CdpField.of("frameId", value -> new DOMSnapshot.StringIndex(((Number) value).longValue()));
    private static final CdpField<java.util.List<Long>> INDEX = CdpField.of("index", value -> CdpObject.requireList(value, element0 -> ((Number) element0).longValue()));
    private static final CdpField<java.util.List<DOMSnapshot.InlineTextBox>> INLINE_TEXT_NODES = CdpField.of("inlineTextNodes", value -> CdpObject.requireList(value, element0 -> java.util.Objects.requireNonNull(DOMSnapshot.InlineTextBox.fromMap(CdpObject.requireObjectMap(element0)))));
    private static final CdpField<DOMSnapshot.RareBooleanData> INPUT_CHECKED = CdpField.of("inputChecked", value -> java.util.Objects.requireNonNull(DOMSnapshot.RareBooleanData.fromMap(CdpObject.requireObjectMap(value))));
    private static final CdpField<DOMSnapshot.RareStringData> INPUT_VALUE = CdpField.of("inputValue", value -> java.util.Objects.requireNonNull(DOMSnapshot.RareStringData.fromMap(CdpObject.requireObjectMap(value))));
    private static final CdpField<DOMSnapshot.RareBooleanData> IS_CLICKABLE = CdpField.of("isClickable", value -> java.util.Objects.requireNonNull(DOMSnapshot.RareBooleanData.fromMap(CdpObject.requireObjectMap(value))));
    private static final CdpField<DOMSnapshot.LayoutTreeSnapshot> LAYOUT = CdpField.of("layout", value -> java.util.Objects.requireNonNull(DOMSnapshot.LayoutTreeSnapshot.fromMap(CdpObject.requireObjectMap(value))));
    private static final CdpField<java.util.List<Long>> LAYOUT_INDEX = CdpField.of("layoutIndex", value -> CdpObject.requireList(value, element0 -> ((Number) element0).longValue()));
    private static final CdpField<java.util.List<DOMSnapshot.LayoutTreeNode>> LAYOUT_TREE_NODES = CdpField.of("layoutTreeNodes", value -> CdpObject.requireList(value, element0 -> java.util.Objects.requireNonNull(DOMSnapshot.LayoutTreeNode.fromMap(CdpObject.requireObjectMap(element0)))));
    private static final CdpField<java.util.List<Long>> LENGTH = CdpField.of("length", value -> CdpObject.requireList(value, element0 -> ((Number) element0).longValue()));
    private static final CdpField<DOMSnapshot.NodeTreeSnapshot> NODES = CdpField.of("nodes", value -> java.util.Objects.requireNonNull(DOMSnapshot.NodeTreeSnapshot.fromMap(CdpObject.requireObjectMap(value))));
    private static final CdpField<java.util.List<Long>> NODE_INDEX = CdpField.of("nodeIndex", value -> CdpObject.requireList(value, element0 -> ((Number) element0).longValue()));
    private static final CdpField<java.util.List<DOMSnapshot.StringIndex>> NODE_NAME = CdpField.of("nodeName", value -> CdpObject.requireList(value, element0 -> new DOMSnapshot.StringIndex(((Number) element0).longValue())));
    private static final CdpField<java.util.List<Long>> NODE_TYPE = CdpField.of("nodeType", value -> CdpObject.requireList(value, element0 -> ((Number) element0).longValue()));
    private static final CdpField<java.util.List<DOMSnapshot.StringIndex>> NODE_VALUE = CdpField.of("nodeValue", value -> CdpObject.requireList(value, element0 -> new DOMSnapshot.StringIndex(((Number) element0).longValue())));
    private static final CdpField<java.util.List<java.util.List<Double>>> OFFSET_RECTS = CdpField.of("offsetRects", value -> CdpObject.requireList(value, element0 -> CdpObject.requireList(element0, element1 -> ((Number) element1).doubleValue())));
    private static final CdpField<DOMSnapshot.RareBooleanData> OPTION_SELECTED = CdpField.of("optionSelected", value -> java.util.Objects.requireNonNull(DOMSnapshot.RareBooleanData.fromMap(CdpObject.requireObjectMap(value))));
    private static final CdpField<DOMSnapshot.RareStringData> ORIGIN_URL = CdpField.of("originURL", value -> java.util.Objects.requireNonNull(DOMSnapshot.RareStringData.fromMap(CdpObject.requireObjectMap(value))));
    private static final CdpField<java.util.List<Long>> PAINT_ORDERS = CdpField.of("paintOrders", value -> CdpObject.requireList(value, element0 -> ((Number) element0).longValue()));
    private static final CdpField<java.util.List<Long>> PARENT_INDEX = CdpField.of("parentIndex", value -> CdpObject.requireList(value, element0 -> ((Number) element0).longValue()));
    private static final CdpField<java.util.List<DOMSnapshot.NameValue>> PROPERTIES = CdpField.of("properties", value -> CdpObject.requireList(value, element0 -> java.util.Objects.requireNonNull(DOMSnapshot.NameValue.fromMap(CdpObject.requireObjectMap(element0)))));
    private static final CdpField<java.util.List<Long>> PSEUDO_ELEMENT_INDEXES = CdpField.of("pseudoElementIndexes", value -> CdpObject.requireList(value, element0 -> ((Number) element0).longValue()));
    private static final CdpField<DOMSnapshot.RareStringData> PSEUDO_IDENTIFIER = CdpField.of("pseudoIdentifier", value -> java.util.Objects.requireNonNull(DOMSnapshot.RareStringData.fromMap(CdpObject.requireObjectMap(value))));
    private static final CdpField<DOM.PseudoType> PSEUDO_TYPE = CdpField.of("pseudoType", value -> DOM.PseudoType.of((String) value));
    private static final CdpField<DOMSnapshot.RareStringData> PSEUDO_TYPE_2 = CdpField.of("pseudoType", value -> java.util.Objects.requireNonNull(DOMSnapshot.RareStringData.fromMap(CdpObject.requireObjectMap(value))));
    private static final CdpField<DOMSnapshot.StringIndex> PUBLIC_ID = CdpField.of("publicId", value -> new DOMSnapshot.StringIndex(((Number) value).longValue()));
    private static final CdpField<java.util.List<java.util.List<Double>>> SCROLL_RECTS = CdpField.of("scrollRects", value -> CdpObject.requireList(value, element0 -> CdpObject.requireList(element0, element1 -> ((Number) element1).doubleValue())));
    private static final CdpField<DOM.ShadowRootType> SHADOW_ROOT_TYPE = CdpField.of("shadowRootType", value -> DOM.ShadowRootType.of((String) value));
    private static final CdpField<DOMSnapshot.RareStringData> SHADOW_ROOT_TYPE_2 = CdpField.of("shadowRootType", value -> java.util.Objects.requireNonNull(DOMSnapshot.RareStringData.fromMap(CdpObject.requireObjectMap(value))));
    private static final CdpField<DOMSnapshot.RareBooleanData> STACKING_CONTEXTS = CdpField.of("stackingContexts", value -> java.util.Objects.requireNonNull(DOMSnapshot.RareBooleanData.fromMap(CdpObject.requireObjectMap(value))));
    private static final CdpField<java.util.List<Long>> START = CdpField.of("start", value -> CdpObject.requireList(value, element0 -> ((Number) element0).longValue()));
    private static final CdpField<java.util.List<String>> STRINGS = CdpField.of("strings", value -> CdpObject.requireList(value, element0 -> (String) element0));
    private static final CdpField<java.util.List<java.util.List<DOMSnapshot.StringIndex>>> STYLES = CdpField.of("styles", value -> CdpObject.requireList(value, element0 -> CdpObject.requireList(element0, element1 -> new DOMSnapshot.StringIndex(((Number) element1).longValue()))));
    private static final CdpField<DOMSnapshot.StringIndex> SYSTEM_ID = CdpField.of("systemId", value -> new DOMSnapshot.StringIndex(((Number) value).longValue()));
    private static final CdpField<java.util.List<DOMSnapshot.StringIndex>> TEXT = CdpField.of("text", value -> CdpObject.requireList(value, element0 -> new DOMSnapshot.StringIndex(((Number) element0).longValue())));
    private static final CdpField<DOMSnapshot.TextBoxSnapshot> TEXT_BOXES = CdpField.of("textBoxes", value -> java.util.Objects.requireNonNull(DOMSnapshot.TextBoxSnapshot.fromMap(CdpObject.requireObjectMap(value))));
    private static final CdpField<java.util.List<Double>> TEXT_COLOR_OPACITIES = CdpField.of("textColorOpacities", value -> CdpObject.requireList(value, element0 -> ((Number) element0).doubleValue()));
    private static final CdpField<DOMSnapshot.RareStringData> TEXT_VALUE = CdpField.of("textValue", value -> java.util.Objects.requireNonNull(DOMSnapshot.RareStringData.fromMap(CdpObject.requireObjectMap(value))));
    private static final CdpField<DOMSnapshot.StringIndex> TITLE = CdpField.of("title", value -> new DOMSnapshot.StringIndex(((Number) value).longValue()));
    private static final CdpField<java.util.List<DOMSnapshot.StringIndex>> VALUE = CdpField.of("value", value -> CdpObject.requireList(value, element0 -> new DOMSnapshot.StringIndex(((Number) element0).longValue())));
    private static final CdpField<java.util.List<Long>> VALUE_2 = CdpField.of("value", value -> CdpObject.requireList(value, element0 -> ((Number) element0).longValue()));
    /**
     * A Node in the DOM tree.
     */
    public static final class DOMNode extends CdpObject {
        public DOMNode() {}
        private DOMNode(Map<String, Object> values) { super(values); }
        public static DOMNode fromMap(Map<String, Object> values) {
            return new DOMNode(values);
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
         * {@code Node}&#x27;s nodeValue.
         * @return the protocol field value
         */
        public String nodeValue() {
            return requireString("nodeValue");
        }
        /**
         * Only set for textarea elements, contains the text value.
         * @return the protocol field value, empty when absent
         */
        public Optional<String> textValue() {
            return optionalString("textValue");
        }
        /**
         * Only set for input elements, contains the input&#x27;s associated text value.
         * @return the protocol field value, empty when absent
         */
        public Optional<String> inputValue() {
            return optionalString("inputValue");
        }
        /**
         * Only set for radio and checkbox input elements, indicates if the element has been checked
         * @return the protocol field value, empty when absent
         */
        public Optional<Boolean> inputChecked() {
            return optionalBoolean("inputChecked");
        }
        /**
         * Only set for option elements, indicates if the element has been selected
         * @return the protocol field value, empty when absent
         */
        public Optional<Boolean> optionSelected() {
            return optionalBoolean("optionSelected");
        }
        /**
         * {@code Node}&#x27;s id, corresponds to DOM.Node.backendNodeId.
         * @return the protocol field value
         */
        public DOM.BackendNodeId backendNodeId() {
            return require(BACKEND_NODE_ID);
        }
        /**
         * The indexes of the node&#x27;s child nodes in the {@code domNodes} array returned by {@code getSnapshot}, if any.
         * @return the protocol field value, empty when absent
         */
        public Optional<java.util.List<Long>> childNodeIndexes() {
            return optional(CHILD_NODE_INDEXES);
        }
        /**
         * Attributes of an {@code Element} node.
         * @return the protocol field value, empty when absent
         */
        public Optional<java.util.List<DOMSnapshot.NameValue>> attributes() {
            return optional(ATTRIBUTES);
        }
        /**
         * Indexes of pseudo elements associated with this node in the {@code domNodes} array returned by {@code getSnapshot}, if any.
         * @return the protocol field value, empty when absent
         */
        public Optional<java.util.List<Long>> pseudoElementIndexes() {
            return optional(PSEUDO_ELEMENT_INDEXES);
        }
        /**
         * The index of the node&#x27;s related layout tree node in the {@code layoutTreeNodes} array returned by {@code getSnapshot}, if any.
         * @return the protocol field value, empty when absent
         */
        public OptionalLong layoutNodeIndex() {
            return optionalLong("layoutNodeIndex");
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
         * Only set for documents, contains the document&#x27;s content language.
         * @return the protocol field value, empty when absent
         */
        public Optional<String> contentLanguage() {
            return optionalString("contentLanguage");
        }
        /**
         * Only set for documents, contains the document&#x27;s character set encoding.
         * @return the protocol field value, empty when absent
         */
        public Optional<String> documentEncoding() {
            return optionalString("documentEncoding");
        }
        /**
         * {@code DocumentType} node&#x27;s publicId.
         * @return the protocol field value, empty when absent
         */
        public Optional<String> publicId() {
            return optionalString("publicId");
        }
        /**
         * {@code DocumentType} node&#x27;s systemId.
         * @return the protocol field value, empty when absent
         */
        public Optional<String> systemId() {
            return optionalString("systemId");
        }
        /**
         * Frame ID for frame owner elements and also for the document node.
         * @return the protocol field value, empty when absent
         */
        public Optional<Page.FrameId> frameId() {
            return optional(FRAME_ID);
        }
        /**
         * The index of a frame owner element&#x27;s content document in the {@code domNodes} array returned by {@code getSnapshot}, if any.
         * @return the protocol field value, empty when absent
         */
        public OptionalLong contentDocumentIndex() {
            return optionalLong("contentDocumentIndex");
        }
        /**
         * Type of a pseudo element node.
         * @return the protocol field value, empty when absent
         */
        public Optional<DOM.PseudoType> pseudoType() {
            return optional(PSEUDO_TYPE);
        }
        /**
         * Shadow root type.
         * @return the protocol field value, empty when absent
         */
        public Optional<DOM.ShadowRootType> shadowRootType() {
            return optional(SHADOW_ROOT_TYPE);
        }
        /**
         * Whether this DOM node responds to mouse clicks. This includes nodes that have had click event listeners attached via JavaScript as well as anchor tags that naturally navigate when clicked.
         * @return the protocol field value, empty when absent
         */
        public Optional<Boolean> isClickable() {
            return optionalBoolean("isClickable");
        }
        /**
         * Details of the node&#x27;s event listeners, if any.
         * @return the protocol field value, empty when absent
         */
        public Optional<java.util.List<DOMDebugger.EventListener>> eventListeners() {
            return optional(EVENT_LISTENERS);
        }
        /**
         * The selected url for nodes with a srcset attribute.
         * @return the protocol field value, empty when absent
         */
        public Optional<String> currentSourceURL() {
            return optionalString("currentSourceURL");
        }
        /**
         * The url of the script (if any) that generates this node.
         * @return the protocol field value, empty when absent
         */
        public Optional<String> originURL() {
            return optionalString("originURL");
        }
        /**
         * Scroll offsets, set when this node is a Document.
         * @return the protocol field value, empty when absent
         */
        public OptionalDouble scrollOffsetX() {
            return optionalDouble("scrollOffsetX");
        }
        /**
         * Returns the scrollOffsetY field.
         * @return the protocol field value, empty when absent
         */
        public OptionalDouble scrollOffsetY() {
            return optionalDouble("scrollOffsetY");
        }
        /**
         * {@code Node}&#x27;s nodeType.
         * @param nodeType field value
         * @return this model
         */
        public DOMNode nodeType(long nodeType) {
            set("nodeType", nodeType);
            return this;
        }
        /**
         * {@code Node}&#x27;s nodeName.
         * @param nodeName field value
         * @return this model
         */
        public DOMNode nodeName(String nodeName) {
            set("nodeName", nodeName);
            return this;
        }
        /**
         * {@code Node}&#x27;s nodeValue.
         * @param nodeValue field value
         * @return this model
         */
        public DOMNode nodeValue(String nodeValue) {
            set("nodeValue", nodeValue);
            return this;
        }
        /**
         * Only set for textarea elements, contains the text value.
         * @param textValue field value
         * @return this model
         */
        public DOMNode textValue(String textValue) {
            set("textValue", textValue);
            return this;
        }
        /**
         * Only set for input elements, contains the input&#x27;s associated text value.
         * @param inputValue field value
         * @return this model
         */
        public DOMNode inputValue(String inputValue) {
            set("inputValue", inputValue);
            return this;
        }
        /**
         * Only set for radio and checkbox input elements, indicates if the element has been checked
         * @param inputChecked field value
         * @return this model
         */
        public DOMNode inputChecked(boolean inputChecked) {
            set("inputChecked", inputChecked);
            return this;
        }
        /**
         * Only set for option elements, indicates if the element has been selected
         * @param optionSelected field value
         * @return this model
         */
        public DOMNode optionSelected(boolean optionSelected) {
            set("optionSelected", optionSelected);
            return this;
        }
        /**
         * {@code Node}&#x27;s id, corresponds to DOM.Node.backendNodeId.
         * @param backendNodeId field value
         * @return this model
         */
        public DOMNode backendNodeId(DOM.BackendNodeId backendNodeId) {
            set("backendNodeId", backendNodeId);
            return this;
        }
        /**
         * The indexes of the node&#x27;s child nodes in the {@code domNodes} array returned by {@code getSnapshot}, if any.
         * @param childNodeIndexes field value
         * @return this model
         */
        public DOMNode childNodeIndexes(java.util.List<Long> childNodeIndexes) {
            set("childNodeIndexes", childNodeIndexes);
            return this;
        }
        /**
         * Attributes of an {@code Element} node.
         * @param attributes field value
         * @return this model
         */
        public DOMNode attributes(java.util.List<DOMSnapshot.NameValue> attributes) {
            set("attributes", attributes);
            return this;
        }
        /**
         * Indexes of pseudo elements associated with this node in the {@code domNodes} array returned by {@code getSnapshot}, if any.
         * @param pseudoElementIndexes field value
         * @return this model
         */
        public DOMNode pseudoElementIndexes(java.util.List<Long> pseudoElementIndexes) {
            set("pseudoElementIndexes", pseudoElementIndexes);
            return this;
        }
        /**
         * The index of the node&#x27;s related layout tree node in the {@code layoutTreeNodes} array returned by {@code getSnapshot}, if any.
         * @param layoutNodeIndex field value
         * @return this model
         */
        public DOMNode layoutNodeIndex(long layoutNodeIndex) {
            set("layoutNodeIndex", layoutNodeIndex);
            return this;
        }
        /**
         * Document URL that {@code Document} or {@code FrameOwner} node points to.
         * @param documentURL field value
         * @return this model
         */
        public DOMNode documentURL(String documentURL) {
            set("documentURL", documentURL);
            return this;
        }
        /**
         * Base URL that {@code Document} or {@code FrameOwner} node uses for URL completion.
         * @param baseURL field value
         * @return this model
         */
        public DOMNode baseURL(String baseURL) {
            set("baseURL", baseURL);
            return this;
        }
        /**
         * Only set for documents, contains the document&#x27;s content language.
         * @param contentLanguage field value
         * @return this model
         */
        public DOMNode contentLanguage(String contentLanguage) {
            set("contentLanguage", contentLanguage);
            return this;
        }
        /**
         * Only set for documents, contains the document&#x27;s character set encoding.
         * @param documentEncoding field value
         * @return this model
         */
        public DOMNode documentEncoding(String documentEncoding) {
            set("documentEncoding", documentEncoding);
            return this;
        }
        /**
         * {@code DocumentType} node&#x27;s publicId.
         * @param publicId field value
         * @return this model
         */
        public DOMNode publicId(String publicId) {
            set("publicId", publicId);
            return this;
        }
        /**
         * {@code DocumentType} node&#x27;s systemId.
         * @param systemId field value
         * @return this model
         */
        public DOMNode systemId(String systemId) {
            set("systemId", systemId);
            return this;
        }
        /**
         * Frame ID for frame owner elements and also for the document node.
         * @param frameId field value
         * @return this model
         */
        public DOMNode frameId(Page.FrameId frameId) {
            set("frameId", frameId);
            return this;
        }
        /**
         * The index of a frame owner element&#x27;s content document in the {@code domNodes} array returned by {@code getSnapshot}, if any.
         * @param contentDocumentIndex field value
         * @return this model
         */
        public DOMNode contentDocumentIndex(long contentDocumentIndex) {
            set("contentDocumentIndex", contentDocumentIndex);
            return this;
        }
        /**
         * Type of a pseudo element node.
         * @param pseudoType field value
         * @return this model
         */
        public DOMNode pseudoType(DOM.PseudoType pseudoType) {
            set("pseudoType", pseudoType);
            return this;
        }
        /**
         * Shadow root type.
         * @param shadowRootType field value
         * @return this model
         */
        public DOMNode shadowRootType(DOM.ShadowRootType shadowRootType) {
            set("shadowRootType", shadowRootType);
            return this;
        }
        /**
         * Whether this DOM node responds to mouse clicks. This includes nodes that have had click event listeners attached via JavaScript as well as anchor tags that naturally navigate when clicked.
         * @param isClickable field value
         * @return this model
         */
        public DOMNode isClickable(boolean isClickable) {
            set("isClickable", isClickable);
            return this;
        }
        /**
         * Details of the node&#x27;s event listeners, if any.
         * @param eventListeners field value
         * @return this model
         */
        public DOMNode eventListeners(java.util.List<DOMDebugger.EventListener> eventListeners) {
            set("eventListeners", eventListeners);
            return this;
        }
        /**
         * The selected url for nodes with a srcset attribute.
         * @param currentSourceURL field value
         * @return this model
         */
        public DOMNode currentSourceURL(String currentSourceURL) {
            set("currentSourceURL", currentSourceURL);
            return this;
        }
        /**
         * The url of the script (if any) that generates this node.
         * @param originURL field value
         * @return this model
         */
        public DOMNode originURL(String originURL) {
            set("originURL", originURL);
            return this;
        }
        /**
         * Scroll offsets, set when this node is a Document.
         * @param scrollOffsetX field value
         * @return this model
         */
        public DOMNode scrollOffsetX(double scrollOffsetX) {
            set("scrollOffsetX", scrollOffsetX);
            return this;
        }
        /**
         * Sets the scrollOffsetY field.
         * @param scrollOffsetY field value
         * @return this model
         */
        public DOMNode scrollOffsetY(double scrollOffsetY) {
            set("scrollOffsetY", scrollOffsetY);
            return this;
        }
    }
    /**
     * Details of post layout rendered text positions. The exact layout should not be regarded as stable and may change between versions.
     */
    public static final class InlineTextBox extends CdpObject {
        public InlineTextBox() {}
        private InlineTextBox(Map<String, Object> values) { super(values); }
        public static InlineTextBox fromMap(Map<String, Object> values) {
            return new InlineTextBox(values);
        }
        /**
         * The bounding box in document coordinates. Note that scroll offset of the document is ignored.
         * @return the protocol field value
         */
        public DOM.Rect boundingBox() {
            return require(BOUNDING_BOX);
        }
        /**
         * The starting index in characters, for this post layout textbox substring. Characters that would be represented as a surrogate pair in UTF-16 have length 2.
         * @return the protocol field value
         */
        public long startCharacterIndex() {
            return requireLong("startCharacterIndex");
        }
        /**
         * The number of characters in this post layout textbox substring. Characters that would be represented as a surrogate pair in UTF-16 have length 2.
         * @return the protocol field value
         */
        public long numCharacters() {
            return requireLong("numCharacters");
        }
        /**
         * The bounding box in document coordinates. Note that scroll offset of the document is ignored.
         * @param boundingBox field value
         * @return this model
         */
        public InlineTextBox boundingBox(DOM.Rect boundingBox) {
            set("boundingBox", boundingBox);
            return this;
        }
        /**
         * The starting index in characters, for this post layout textbox substring. Characters that would be represented as a surrogate pair in UTF-16 have length 2.
         * @param startCharacterIndex field value
         * @return this model
         */
        public InlineTextBox startCharacterIndex(long startCharacterIndex) {
            set("startCharacterIndex", startCharacterIndex);
            return this;
        }
        /**
         * The number of characters in this post layout textbox substring. Characters that would be represented as a surrogate pair in UTF-16 have length 2.
         * @param numCharacters field value
         * @return this model
         */
        public InlineTextBox numCharacters(long numCharacters) {
            set("numCharacters", numCharacters);
            return this;
        }
    }
    /**
     * Details of an element in the DOM tree with a LayoutObject.
     */
    public static final class LayoutTreeNode extends CdpObject {
        public LayoutTreeNode() {}
        private LayoutTreeNode(Map<String, Object> values) { super(values); }
        public static LayoutTreeNode fromMap(Map<String, Object> values) {
            return new LayoutTreeNode(values);
        }
        /**
         * The index of the related DOM node in the {@code domNodes} array returned by {@code getSnapshot}.
         * @return the protocol field value
         */
        public long domNodeIndex() {
            return requireLong("domNodeIndex");
        }
        /**
         * The bounding box in document coordinates. Note that scroll offset of the document is ignored.
         * @return the protocol field value
         */
        public DOM.Rect boundingBox() {
            return require(BOUNDING_BOX);
        }
        /**
         * Contents of the LayoutText, if any.
         * @return the protocol field value, empty when absent
         */
        public Optional<String> layoutText() {
            return optionalString("layoutText");
        }
        /**
         * The post-layout inline text nodes, if any.
         * @return the protocol field value, empty when absent
         */
        public Optional<java.util.List<DOMSnapshot.InlineTextBox>> inlineTextNodes() {
            return optional(INLINE_TEXT_NODES);
        }
        /**
         * Index into the {@code computedStyles} array returned by {@code getSnapshot}.
         * @return the protocol field value, empty when absent
         */
        public OptionalLong styleIndex() {
            return optionalLong("styleIndex");
        }
        /**
         * Global paint order index, which is determined by the stacking order of the nodes. Nodes that are painted together will have the same index. Only provided if includePaintOrder in getSnapshot was true.
         * @return the protocol field value, empty when absent
         */
        public OptionalLong paintOrder() {
            return optionalLong("paintOrder");
        }
        /**
         * Set to true to indicate the element begins a new stacking context.
         * @return the protocol field value, empty when absent
         */
        public Optional<Boolean> isStackingContext() {
            return optionalBoolean("isStackingContext");
        }
        /**
         * The index of the related DOM node in the {@code domNodes} array returned by {@code getSnapshot}.
         * @param domNodeIndex field value
         * @return this model
         */
        public LayoutTreeNode domNodeIndex(long domNodeIndex) {
            set("domNodeIndex", domNodeIndex);
            return this;
        }
        /**
         * The bounding box in document coordinates. Note that scroll offset of the document is ignored.
         * @param boundingBox field value
         * @return this model
         */
        public LayoutTreeNode boundingBox(DOM.Rect boundingBox) {
            set("boundingBox", boundingBox);
            return this;
        }
        /**
         * Contents of the LayoutText, if any.
         * @param layoutText field value
         * @return this model
         */
        public LayoutTreeNode layoutText(String layoutText) {
            set("layoutText", layoutText);
            return this;
        }
        /**
         * The post-layout inline text nodes, if any.
         * @param inlineTextNodes field value
         * @return this model
         */
        public LayoutTreeNode inlineTextNodes(java.util.List<DOMSnapshot.InlineTextBox> inlineTextNodes) {
            set("inlineTextNodes", inlineTextNodes);
            return this;
        }
        /**
         * Index into the {@code computedStyles} array returned by {@code getSnapshot}.
         * @param styleIndex field value
         * @return this model
         */
        public LayoutTreeNode styleIndex(long styleIndex) {
            set("styleIndex", styleIndex);
            return this;
        }
        /**
         * Global paint order index, which is determined by the stacking order of the nodes. Nodes that are painted together will have the same index. Only provided if includePaintOrder in getSnapshot was true.
         * @param paintOrder field value
         * @return this model
         */
        public LayoutTreeNode paintOrder(long paintOrder) {
            set("paintOrder", paintOrder);
            return this;
        }
        /**
         * Set to true to indicate the element begins a new stacking context.
         * @param isStackingContext field value
         * @return this model
         */
        public LayoutTreeNode isStackingContext(boolean isStackingContext) {
            set("isStackingContext", isStackingContext);
            return this;
        }
    }
    /**
     * A subset of the full ComputedStyle as defined by the request whitelist.
     */
    public static final class ComputedStyle extends CdpObject {
        public ComputedStyle() {}
        private ComputedStyle(Map<String, Object> values) { super(values); }
        public static ComputedStyle fromMap(Map<String, Object> values) {
            return new ComputedStyle(values);
        }
        /**
         * Name/value pairs of computed style properties.
         * @return the protocol field value
         */
        public java.util.List<DOMSnapshot.NameValue> properties() {
            return require(PROPERTIES);
        }
        /**
         * Name/value pairs of computed style properties.
         * @param properties field value
         * @return this model
         */
        public ComputedStyle properties(java.util.List<DOMSnapshot.NameValue> properties) {
            set("properties", properties);
            return this;
        }
    }
    /**
     * A name/value pair.
     */
    public static final class NameValue extends CdpObject {
        public NameValue() {}
        private NameValue(Map<String, Object> values) { super(values); }
        public static NameValue fromMap(Map<String, Object> values) {
            return new NameValue(values);
        }
        /**
         * Attribute/property name.
         * @return the protocol field value
         */
        public String name() {
            return requireString("name");
        }
        /**
         * Attribute/property value.
         * @return the protocol field value
         */
        public String value() {
            return requireString("value");
        }
        /**
         * Attribute/property name.
         * @param name field value
         * @return this model
         */
        public NameValue name(String name) {
            set("name", name);
            return this;
        }
        /**
         * Attribute/property value.
         * @param value field value
         * @return this model
         */
        public NameValue value(String value) {
            set("value", value);
            return this;
        }
    }
    /**
     * Index of the string in the strings table.
     */
    public static final class StringIndex implements CdpValue<Long> {
        public final long value;
        public StringIndex(long value) { this.value = value; }
        @Override public Long value() { return value; }
        @Override public boolean equals(@org.jspecify.annotations.Nullable Object other) {
            if (this == other) return true;
            if (!(other instanceof StringIndex)) return false;
            return value == ((StringIndex) other).value;
        }
        @Override public int hashCode() { return Long.hashCode(value); }
        @Override public String toString() { return "StringIndex(" + value + ")"; }
    }
    /**
     * Data that is only present on rare nodes.
     */
    public static final class RareStringData extends CdpObject {
        public RareStringData() {}
        private RareStringData(Map<String, Object> values) { super(values); }
        public static RareStringData fromMap(Map<String, Object> values) {
            return new RareStringData(values);
        }
        /**
         * Returns the index field.
         * @return the protocol field value
         */
        public java.util.List<Long> index() {
            return require(INDEX);
        }
        /**
         * Returns the value field.
         * @return the protocol field value
         */
        public java.util.List<DOMSnapshot.StringIndex> value() {
            return require(VALUE);
        }
        /**
         * Sets the index field.
         * @param index field value
         * @return this model
         */
        public RareStringData index(java.util.List<Long> index) {
            set("index", index);
            return this;
        }
        /**
         * Sets the value field.
         * @param value field value
         * @return this model
         */
        public RareStringData value(java.util.List<DOMSnapshot.StringIndex> value) {
            set("value", value);
            return this;
        }
    }
    /**
     */
    public static final class RareBooleanData extends CdpObject {
        public RareBooleanData() {}
        private RareBooleanData(Map<String, Object> values) { super(values); }
        public static RareBooleanData fromMap(Map<String, Object> values) {
            return new RareBooleanData(values);
        }
        /**
         * Returns the index field.
         * @return the protocol field value
         */
        public java.util.List<Long> index() {
            return require(INDEX);
        }
        /**
         * Sets the index field.
         * @param index field value
         * @return this model
         */
        public RareBooleanData index(java.util.List<Long> index) {
            set("index", index);
            return this;
        }
    }
    /**
     */
    public static final class RareIntegerData extends CdpObject {
        public RareIntegerData() {}
        private RareIntegerData(Map<String, Object> values) { super(values); }
        public static RareIntegerData fromMap(Map<String, Object> values) {
            return new RareIntegerData(values);
        }
        /**
         * Returns the index field.
         * @return the protocol field value
         */
        public java.util.List<Long> index() {
            return require(INDEX);
        }
        /**
         * Returns the value field.
         * @return the protocol field value
         */
        public java.util.List<Long> value() {
            return require(VALUE_2);
        }
        /**
         * Sets the index field.
         * @param index field value
         * @return this model
         */
        public RareIntegerData index(java.util.List<Long> index) {
            set("index", index);
            return this;
        }
        /**
         * Sets the value field.
         * @param value field value
         * @return this model
         */
        public RareIntegerData value(java.util.List<Long> value) {
            set("value", value);
            return this;
        }
    }
    /**
     * Document snapshot.
     */
    public static final class DocumentSnapshot extends CdpObject {
        public DocumentSnapshot() {}
        private DocumentSnapshot(Map<String, Object> values) { super(values); }
        public static DocumentSnapshot fromMap(Map<String, Object> values) {
            return new DocumentSnapshot(values);
        }
        /**
         * Document URL that {@code Document} or {@code FrameOwner} node points to.
         * @return the protocol field value
         */
        public DOMSnapshot.StringIndex documentURL() {
            return require(DOCUMENT_URL);
        }
        /**
         * Document title.
         * @return the protocol field value
         */
        public DOMSnapshot.StringIndex title() {
            return require(TITLE);
        }
        /**
         * Base URL that {@code Document} or {@code FrameOwner} node uses for URL completion.
         * @return the protocol field value
         */
        public DOMSnapshot.StringIndex baseURL() {
            return require(BASE_URL);
        }
        /**
         * Contains the document&#x27;s content language.
         * @return the protocol field value
         */
        public DOMSnapshot.StringIndex contentLanguage() {
            return require(CONTENT_LANGUAGE);
        }
        /**
         * Contains the document&#x27;s character set encoding.
         * @return the protocol field value
         */
        public DOMSnapshot.StringIndex encodingName() {
            return require(ENCODING_NAME);
        }
        /**
         * {@code DocumentType} node&#x27;s publicId.
         * @return the protocol field value
         */
        public DOMSnapshot.StringIndex publicId() {
            return require(PUBLIC_ID);
        }
        /**
         * {@code DocumentType} node&#x27;s systemId.
         * @return the protocol field value
         */
        public DOMSnapshot.StringIndex systemId() {
            return require(SYSTEM_ID);
        }
        /**
         * Frame ID for frame owner elements and also for the document node.
         * @return the protocol field value
         */
        public DOMSnapshot.StringIndex frameId() {
            return require(FRAME_ID_2);
        }
        /**
         * A table with dom nodes.
         * @return the protocol field value
         */
        public DOMSnapshot.NodeTreeSnapshot nodes() {
            return require(NODES);
        }
        /**
         * The nodes in the layout tree.
         * @return the protocol field value
         */
        public DOMSnapshot.LayoutTreeSnapshot layout() {
            return require(LAYOUT);
        }
        /**
         * The post-layout inline text nodes.
         * @return the protocol field value
         */
        public DOMSnapshot.TextBoxSnapshot textBoxes() {
            return require(TEXT_BOXES);
        }
        /**
         * Horizontal scroll offset.
         * @return the protocol field value, empty when absent
         */
        public OptionalDouble scrollOffsetX() {
            return optionalDouble("scrollOffsetX");
        }
        /**
         * Vertical scroll offset.
         * @return the protocol field value, empty when absent
         */
        public OptionalDouble scrollOffsetY() {
            return optionalDouble("scrollOffsetY");
        }
        /**
         * Document content width.
         * @return the protocol field value, empty when absent
         */
        public OptionalDouble contentWidth() {
            return optionalDouble("contentWidth");
        }
        /**
         * Document content height.
         * @return the protocol field value, empty when absent
         */
        public OptionalDouble contentHeight() {
            return optionalDouble("contentHeight");
        }
        /**
         * Document URL that {@code Document} or {@code FrameOwner} node points to.
         * @param documentURL field value
         * @return this model
         */
        public DocumentSnapshot documentURL(DOMSnapshot.StringIndex documentURL) {
            set("documentURL", documentURL);
            return this;
        }
        /**
         * Document title.
         * @param title field value
         * @return this model
         */
        public DocumentSnapshot title(DOMSnapshot.StringIndex title) {
            set("title", title);
            return this;
        }
        /**
         * Base URL that {@code Document} or {@code FrameOwner} node uses for URL completion.
         * @param baseURL field value
         * @return this model
         */
        public DocumentSnapshot baseURL(DOMSnapshot.StringIndex baseURL) {
            set("baseURL", baseURL);
            return this;
        }
        /**
         * Contains the document&#x27;s content language.
         * @param contentLanguage field value
         * @return this model
         */
        public DocumentSnapshot contentLanguage(DOMSnapshot.StringIndex contentLanguage) {
            set("contentLanguage", contentLanguage);
            return this;
        }
        /**
         * Contains the document&#x27;s character set encoding.
         * @param encodingName field value
         * @return this model
         */
        public DocumentSnapshot encodingName(DOMSnapshot.StringIndex encodingName) {
            set("encodingName", encodingName);
            return this;
        }
        /**
         * {@code DocumentType} node&#x27;s publicId.
         * @param publicId field value
         * @return this model
         */
        public DocumentSnapshot publicId(DOMSnapshot.StringIndex publicId) {
            set("publicId", publicId);
            return this;
        }
        /**
         * {@code DocumentType} node&#x27;s systemId.
         * @param systemId field value
         * @return this model
         */
        public DocumentSnapshot systemId(DOMSnapshot.StringIndex systemId) {
            set("systemId", systemId);
            return this;
        }
        /**
         * Frame ID for frame owner elements and also for the document node.
         * @param frameId field value
         * @return this model
         */
        public DocumentSnapshot frameId(DOMSnapshot.StringIndex frameId) {
            set("frameId", frameId);
            return this;
        }
        /**
         * A table with dom nodes.
         * @param nodes field value
         * @return this model
         */
        public DocumentSnapshot nodes(DOMSnapshot.NodeTreeSnapshot nodes) {
            set("nodes", nodes);
            return this;
        }
        /**
         * The nodes in the layout tree.
         * @param layout field value
         * @return this model
         */
        public DocumentSnapshot layout(DOMSnapshot.LayoutTreeSnapshot layout) {
            set("layout", layout);
            return this;
        }
        /**
         * The post-layout inline text nodes.
         * @param textBoxes field value
         * @return this model
         */
        public DocumentSnapshot textBoxes(DOMSnapshot.TextBoxSnapshot textBoxes) {
            set("textBoxes", textBoxes);
            return this;
        }
        /**
         * Horizontal scroll offset.
         * @param scrollOffsetX field value
         * @return this model
         */
        public DocumentSnapshot scrollOffsetX(double scrollOffsetX) {
            set("scrollOffsetX", scrollOffsetX);
            return this;
        }
        /**
         * Vertical scroll offset.
         * @param scrollOffsetY field value
         * @return this model
         */
        public DocumentSnapshot scrollOffsetY(double scrollOffsetY) {
            set("scrollOffsetY", scrollOffsetY);
            return this;
        }
        /**
         * Document content width.
         * @param contentWidth field value
         * @return this model
         */
        public DocumentSnapshot contentWidth(double contentWidth) {
            set("contentWidth", contentWidth);
            return this;
        }
        /**
         * Document content height.
         * @param contentHeight field value
         * @return this model
         */
        public DocumentSnapshot contentHeight(double contentHeight) {
            set("contentHeight", contentHeight);
            return this;
        }
    }
    /**
     * Table containing nodes.
     */
    public static final class NodeTreeSnapshot extends CdpObject {
        public NodeTreeSnapshot() {}
        private NodeTreeSnapshot(Map<String, Object> values) { super(values); }
        public static NodeTreeSnapshot fromMap(Map<String, Object> values) {
            return new NodeTreeSnapshot(values);
        }
        /**
         * Parent node index.
         * @return the protocol field value, empty when absent
         */
        public Optional<java.util.List<Long>> parentIndex() {
            return optional(PARENT_INDEX);
        }
        /**
         * {@code Node}&#x27;s nodeType.
         * @return the protocol field value, empty when absent
         */
        public Optional<java.util.List<Long>> nodeType() {
            return optional(NODE_TYPE);
        }
        /**
         * Type of the shadow root the {@code Node} is in. String values are equal to the {@code ShadowRootType} enum.
         * @return the protocol field value, empty when absent
         */
        public Optional<DOMSnapshot.RareStringData> shadowRootType() {
            return optional(SHADOW_ROOT_TYPE_2);
        }
        /**
         * {@code Node}&#x27;s nodeName.
         * @return the protocol field value, empty when absent
         */
        public Optional<java.util.List<DOMSnapshot.StringIndex>> nodeName() {
            return optional(NODE_NAME);
        }
        /**
         * {@code Node}&#x27;s nodeValue.
         * @return the protocol field value, empty when absent
         */
        public Optional<java.util.List<DOMSnapshot.StringIndex>> nodeValue() {
            return optional(NODE_VALUE);
        }
        /**
         * {@code Node}&#x27;s id, corresponds to DOM.Node.backendNodeId.
         * @return the protocol field value, empty when absent
         */
        public Optional<java.util.List<DOM.BackendNodeId>> backendNodeId() {
            return optional(BACKEND_NODE_ID_2);
        }
        /**
         * Attributes of an {@code Element} node. Flatten name, value pairs.
         * @return the protocol field value, empty when absent
         */
        public Optional<java.util.List<java.util.List<DOMSnapshot.StringIndex>>> attributes() {
            return optional(ATTRIBUTES_2);
        }
        /**
         * Only set for textarea elements, contains the text value.
         * @return the protocol field value, empty when absent
         */
        public Optional<DOMSnapshot.RareStringData> textValue() {
            return optional(TEXT_VALUE);
        }
        /**
         * Only set for input elements, contains the input&#x27;s associated text value.
         * @return the protocol field value, empty when absent
         */
        public Optional<DOMSnapshot.RareStringData> inputValue() {
            return optional(INPUT_VALUE);
        }
        /**
         * Only set for radio and checkbox input elements, indicates if the element has been checked
         * @return the protocol field value, empty when absent
         */
        public Optional<DOMSnapshot.RareBooleanData> inputChecked() {
            return optional(INPUT_CHECKED);
        }
        /**
         * Only set for option elements, indicates if the element has been selected
         * @return the protocol field value, empty when absent
         */
        public Optional<DOMSnapshot.RareBooleanData> optionSelected() {
            return optional(OPTION_SELECTED);
        }
        /**
         * The index of the document in the list of the snapshot documents.
         * @return the protocol field value, empty when absent
         */
        public Optional<DOMSnapshot.RareIntegerData> contentDocumentIndex() {
            return optional(CONTENT_DOCUMENT_INDEX);
        }
        /**
         * Type of a pseudo element node.
         * @return the protocol field value, empty when absent
         */
        public Optional<DOMSnapshot.RareStringData> pseudoType() {
            return optional(PSEUDO_TYPE_2);
        }
        /**
         * Pseudo element identifier for this node. Only present if there is a valid pseudoType.
         * @return the protocol field value, empty when absent
         */
        public Optional<DOMSnapshot.RareStringData> pseudoIdentifier() {
            return optional(PSEUDO_IDENTIFIER);
        }
        /**
         * Whether this DOM node responds to mouse clicks. This includes nodes that have had click event listeners attached via JavaScript as well as anchor tags that naturally navigate when clicked.
         * @return the protocol field value, empty when absent
         */
        public Optional<DOMSnapshot.RareBooleanData> isClickable() {
            return optional(IS_CLICKABLE);
        }
        /**
         * The selected url for nodes with a srcset attribute.
         * @return the protocol field value, empty when absent
         */
        public Optional<DOMSnapshot.RareStringData> currentSourceURL() {
            return optional(CURRENT_SOURCE_URL);
        }
        /**
         * The url of the script (if any) that generates this node.
         * @return the protocol field value, empty when absent
         */
        public Optional<DOMSnapshot.RareStringData> originURL() {
            return optional(ORIGIN_URL);
        }
        /**
         * Parent node index.
         * @param parentIndex field value
         * @return this model
         */
        public NodeTreeSnapshot parentIndex(java.util.List<Long> parentIndex) {
            set("parentIndex", parentIndex);
            return this;
        }
        /**
         * {@code Node}&#x27;s nodeType.
         * @param nodeType field value
         * @return this model
         */
        public NodeTreeSnapshot nodeType(java.util.List<Long> nodeType) {
            set("nodeType", nodeType);
            return this;
        }
        /**
         * Type of the shadow root the {@code Node} is in. String values are equal to the {@code ShadowRootType} enum.
         * @param shadowRootType field value
         * @return this model
         */
        public NodeTreeSnapshot shadowRootType(DOMSnapshot.RareStringData shadowRootType) {
            set("shadowRootType", shadowRootType);
            return this;
        }
        /**
         * {@code Node}&#x27;s nodeName.
         * @param nodeName field value
         * @return this model
         */
        public NodeTreeSnapshot nodeName(java.util.List<DOMSnapshot.StringIndex> nodeName) {
            set("nodeName", nodeName);
            return this;
        }
        /**
         * {@code Node}&#x27;s nodeValue.
         * @param nodeValue field value
         * @return this model
         */
        public NodeTreeSnapshot nodeValue(java.util.List<DOMSnapshot.StringIndex> nodeValue) {
            set("nodeValue", nodeValue);
            return this;
        }
        /**
         * {@code Node}&#x27;s id, corresponds to DOM.Node.backendNodeId.
         * @param backendNodeId field value
         * @return this model
         */
        public NodeTreeSnapshot backendNodeId(java.util.List<DOM.BackendNodeId> backendNodeId) {
            set("backendNodeId", backendNodeId);
            return this;
        }
        /**
         * Attributes of an {@code Element} node. Flatten name, value pairs.
         * @param attributes field value
         * @return this model
         */
        public NodeTreeSnapshot attributes(java.util.List<java.util.List<DOMSnapshot.StringIndex>> attributes) {
            set("attributes", attributes);
            return this;
        }
        /**
         * Only set for textarea elements, contains the text value.
         * @param textValue field value
         * @return this model
         */
        public NodeTreeSnapshot textValue(DOMSnapshot.RareStringData textValue) {
            set("textValue", textValue);
            return this;
        }
        /**
         * Only set for input elements, contains the input&#x27;s associated text value.
         * @param inputValue field value
         * @return this model
         */
        public NodeTreeSnapshot inputValue(DOMSnapshot.RareStringData inputValue) {
            set("inputValue", inputValue);
            return this;
        }
        /**
         * Only set for radio and checkbox input elements, indicates if the element has been checked
         * @param inputChecked field value
         * @return this model
         */
        public NodeTreeSnapshot inputChecked(DOMSnapshot.RareBooleanData inputChecked) {
            set("inputChecked", inputChecked);
            return this;
        }
        /**
         * Only set for option elements, indicates if the element has been selected
         * @param optionSelected field value
         * @return this model
         */
        public NodeTreeSnapshot optionSelected(DOMSnapshot.RareBooleanData optionSelected) {
            set("optionSelected", optionSelected);
            return this;
        }
        /**
         * The index of the document in the list of the snapshot documents.
         * @param contentDocumentIndex field value
         * @return this model
         */
        public NodeTreeSnapshot contentDocumentIndex(DOMSnapshot.RareIntegerData contentDocumentIndex) {
            set("contentDocumentIndex", contentDocumentIndex);
            return this;
        }
        /**
         * Type of a pseudo element node.
         * @param pseudoType field value
         * @return this model
         */
        public NodeTreeSnapshot pseudoType(DOMSnapshot.RareStringData pseudoType) {
            set("pseudoType", pseudoType);
            return this;
        }
        /**
         * Pseudo element identifier for this node. Only present if there is a valid pseudoType.
         * @param pseudoIdentifier field value
         * @return this model
         */
        public NodeTreeSnapshot pseudoIdentifier(DOMSnapshot.RareStringData pseudoIdentifier) {
            set("pseudoIdentifier", pseudoIdentifier);
            return this;
        }
        /**
         * Whether this DOM node responds to mouse clicks. This includes nodes that have had click event listeners attached via JavaScript as well as anchor tags that naturally navigate when clicked.
         * @param isClickable field value
         * @return this model
         */
        public NodeTreeSnapshot isClickable(DOMSnapshot.RareBooleanData isClickable) {
            set("isClickable", isClickable);
            return this;
        }
        /**
         * The selected url for nodes with a srcset attribute.
         * @param currentSourceURL field value
         * @return this model
         */
        public NodeTreeSnapshot currentSourceURL(DOMSnapshot.RareStringData currentSourceURL) {
            set("currentSourceURL", currentSourceURL);
            return this;
        }
        /**
         * The url of the script (if any) that generates this node.
         * @param originURL field value
         * @return this model
         */
        public NodeTreeSnapshot originURL(DOMSnapshot.RareStringData originURL) {
            set("originURL", originURL);
            return this;
        }
    }
    /**
     * Table of details of an element in the DOM tree with a LayoutObject.
     */
    public static final class LayoutTreeSnapshot extends CdpObject {
        public LayoutTreeSnapshot() {}
        private LayoutTreeSnapshot(Map<String, Object> values) { super(values); }
        public static LayoutTreeSnapshot fromMap(Map<String, Object> values) {
            return new LayoutTreeSnapshot(values);
        }
        /**
         * Index of the corresponding node in the {@code NodeTreeSnapshot} array returned by {@code captureSnapshot}.
         * @return the protocol field value
         */
        public java.util.List<Long> nodeIndex() {
            return require(NODE_INDEX);
        }
        /**
         * Array of indexes specifying computed style strings, filtered according to the {@code computedStyles} parameter passed to {@code captureSnapshot}.
         * @return the protocol field value
         */
        public java.util.List<java.util.List<DOMSnapshot.StringIndex>> styles() {
            return require(STYLES);
        }
        /**
         * The absolute position bounding box.
         * @return the protocol field value
         */
        public java.util.List<java.util.List<Double>> bounds() {
            return require(BOUNDS);
        }
        /**
         * Contents of the LayoutText, if any.
         * @return the protocol field value
         */
        public java.util.List<DOMSnapshot.StringIndex> text() {
            return require(TEXT);
        }
        /**
         * Stacking context information.
         * @return the protocol field value
         */
        public DOMSnapshot.RareBooleanData stackingContexts() {
            return require(STACKING_CONTEXTS);
        }
        /**
         * Global paint order index, which is determined by the stacking order of the nodes. Nodes that are painted together will have the same index. Only provided if includePaintOrder in captureSnapshot was true.
         * @return the protocol field value, empty when absent
         */
        public Optional<java.util.List<Long>> paintOrders() {
            return optional(PAINT_ORDERS);
        }
        /**
         * The offset rect of nodes. Only available when includeDOMRects is set to true
         * @return the protocol field value, empty when absent
         */
        public Optional<java.util.List<java.util.List<Double>>> offsetRects() {
            return optional(OFFSET_RECTS);
        }
        /**
         * The scroll rect of nodes. Only available when includeDOMRects is set to true
         * @return the protocol field value, empty when absent
         */
        public Optional<java.util.List<java.util.List<Double>>> scrollRects() {
            return optional(SCROLL_RECTS);
        }
        /**
         * The client rect of nodes. Only available when includeDOMRects is set to true
         * @return the protocol field value, empty when absent
         */
        public Optional<java.util.List<java.util.List<Double>>> clientRects() {
            return optional(CLIENT_RECTS);
        }
        /**
         * The list of background colors that are blended with colors of overlapping elements.
         * <p><b>Experimental:</b> this part of CDP may change without notice.
         * @return the protocol field value, empty when absent
         */
        public Optional<java.util.List<DOMSnapshot.StringIndex>> blendedBackgroundColors() {
            return optional(BLENDED_BACKGROUND_COLORS);
        }
        /**
         * The list of computed text opacities.
         * <p><b>Experimental:</b> this part of CDP may change without notice.
         * @return the protocol field value, empty when absent
         */
        public Optional<java.util.List<Double>> textColorOpacities() {
            return optional(TEXT_COLOR_OPACITIES);
        }
        /**
         * Index of the corresponding node in the {@code NodeTreeSnapshot} array returned by {@code captureSnapshot}.
         * @param nodeIndex field value
         * @return this model
         */
        public LayoutTreeSnapshot nodeIndex(java.util.List<Long> nodeIndex) {
            set("nodeIndex", nodeIndex);
            return this;
        }
        /**
         * Array of indexes specifying computed style strings, filtered according to the {@code computedStyles} parameter passed to {@code captureSnapshot}.
         * @param styles field value
         * @return this model
         */
        public LayoutTreeSnapshot styles(java.util.List<java.util.List<DOMSnapshot.StringIndex>> styles) {
            set("styles", styles);
            return this;
        }
        /**
         * The absolute position bounding box.
         * @param bounds field value
         * @return this model
         */
        public LayoutTreeSnapshot bounds(java.util.List<java.util.List<Double>> bounds) {
            set("bounds", bounds);
            return this;
        }
        /**
         * Contents of the LayoutText, if any.
         * @param text field value
         * @return this model
         */
        public LayoutTreeSnapshot text(java.util.List<DOMSnapshot.StringIndex> text) {
            set("text", text);
            return this;
        }
        /**
         * Stacking context information.
         * @param stackingContexts field value
         * @return this model
         */
        public LayoutTreeSnapshot stackingContexts(DOMSnapshot.RareBooleanData stackingContexts) {
            set("stackingContexts", stackingContexts);
            return this;
        }
        /**
         * Global paint order index, which is determined by the stacking order of the nodes. Nodes that are painted together will have the same index. Only provided if includePaintOrder in captureSnapshot was true.
         * @param paintOrders field value
         * @return this model
         */
        public LayoutTreeSnapshot paintOrders(java.util.List<Long> paintOrders) {
            set("paintOrders", paintOrders);
            return this;
        }
        /**
         * The offset rect of nodes. Only available when includeDOMRects is set to true
         * @param offsetRects field value
         * @return this model
         */
        public LayoutTreeSnapshot offsetRects(java.util.List<java.util.List<Double>> offsetRects) {
            set("offsetRects", offsetRects);
            return this;
        }
        /**
         * The scroll rect of nodes. Only available when includeDOMRects is set to true
         * @param scrollRects field value
         * @return this model
         */
        public LayoutTreeSnapshot scrollRects(java.util.List<java.util.List<Double>> scrollRects) {
            set("scrollRects", scrollRects);
            return this;
        }
        /**
         * The client rect of nodes. Only available when includeDOMRects is set to true
         * @param clientRects field value
         * @return this model
         */
        public LayoutTreeSnapshot clientRects(java.util.List<java.util.List<Double>> clientRects) {
            set("clientRects", clientRects);
            return this;
        }
        /**
         * The list of background colors that are blended with colors of overlapping elements.
         * <p><b>Experimental:</b> this part of CDP may change without notice.
         * @param blendedBackgroundColors field value
         * @return this model
         */
        public LayoutTreeSnapshot blendedBackgroundColors(java.util.List<DOMSnapshot.StringIndex> blendedBackgroundColors) {
            set("blendedBackgroundColors", blendedBackgroundColors);
            return this;
        }
        /**
         * The list of computed text opacities.
         * <p><b>Experimental:</b> this part of CDP may change without notice.
         * @param textColorOpacities field value
         * @return this model
         */
        public LayoutTreeSnapshot textColorOpacities(java.util.List<Double> textColorOpacities) {
            set("textColorOpacities", textColorOpacities);
            return this;
        }
    }
    /**
     * Table of details of the post layout rendered text positions. The exact layout should not be regarded as stable and may change between versions.
     */
    public static final class TextBoxSnapshot extends CdpObject {
        public TextBoxSnapshot() {}
        private TextBoxSnapshot(Map<String, Object> values) { super(values); }
        public static TextBoxSnapshot fromMap(Map<String, Object> values) {
            return new TextBoxSnapshot(values);
        }
        /**
         * Index of the layout tree node that owns this box collection.
         * @return the protocol field value
         */
        public java.util.List<Long> layoutIndex() {
            return require(LAYOUT_INDEX);
        }
        /**
         * The absolute position bounding box.
         * @return the protocol field value
         */
        public java.util.List<java.util.List<Double>> bounds() {
            return require(BOUNDS);
        }
        /**
         * The starting index in characters, for this post layout textbox substring. Characters that would be represented as a surrogate pair in UTF-16 have length 2.
         * @return the protocol field value
         */
        public java.util.List<Long> start() {
            return require(START);
        }
        /**
         * The number of characters in this post layout textbox substring. Characters that would be represented as a surrogate pair in UTF-16 have length 2.
         * @return the protocol field value
         */
        public java.util.List<Long> length() {
            return require(LENGTH);
        }
        /**
         * Index of the layout tree node that owns this box collection.
         * @param layoutIndex field value
         * @return this model
         */
        public TextBoxSnapshot layoutIndex(java.util.List<Long> layoutIndex) {
            set("layoutIndex", layoutIndex);
            return this;
        }
        /**
         * The absolute position bounding box.
         * @param bounds field value
         * @return this model
         */
        public TextBoxSnapshot bounds(java.util.List<java.util.List<Double>> bounds) {
            set("bounds", bounds);
            return this;
        }
        /**
         * The starting index in characters, for this post layout textbox substring. Characters that would be represented as a surrogate pair in UTF-16 have length 2.
         * @param start field value
         * @return this model
         */
        public TextBoxSnapshot start(java.util.List<Long> start) {
            set("start", start);
            return this;
        }
        /**
         * The number of characters in this post layout textbox substring. Characters that would be represented as a surrogate pair in UTF-16 have length 2.
         * @param length field value
         * @return this model
         */
        public TextBoxSnapshot length(java.util.List<Long> length) {
            set("length", length);
            return this;
        }
    }
    /**
     * Returns a document snapshot, including the full DOM tree of the root node (including iframes, template contents, and imported documents) in a flattened array, as well as layout and white-listed computed style information for the nodes. Shadow DOM in the returned DOM tree is flattened.
     * @deprecated Deprecated by the Chromium DevTools Protocol.
     */
    @Deprecated
    public static final class GetSnapshotRequest extends CdpObject {
        public GetSnapshotRequest() {}
        public static GetSnapshotRequest fromMap(Map<String, Object> values) {
            final GetSnapshotRequest instance_ = new GetSnapshotRequest();
            if (values != null) instance_.values.putAll(values);
            return instance_;
        }
        /**
         * Whitelist of computed styles to return.
         * @return the protocol field value
         */
        public java.util.List<String> computedStyleWhitelist() {
            return require(COMPUTED_STYLE_WHITELIST);
        }
        /**
         * Whether or not to retrieve details of DOM listeners (default false).
         * @return the protocol field value, empty when absent
         */
        public Optional<Boolean> includeEventListeners() {
            return optionalBoolean("includeEventListeners");
        }
        /**
         * Whether to determine and include the paint order index of LayoutTreeNodes (default false).
         * @return the protocol field value, empty when absent
         */
        public Optional<Boolean> includePaintOrder() {
            return optionalBoolean("includePaintOrder");
        }
        /**
         * Whether to include UA shadow tree in the snapshot (default false).
         * @return the protocol field value, empty when absent
         */
        public Optional<Boolean> includeUserAgentShadowTree() {
            return optionalBoolean("includeUserAgentShadowTree");
        }
        /**
         * Whitelist of computed styles to return.
         * @param computedStyleWhitelist field value
         * @return this model
         */
        public GetSnapshotRequest computedStyleWhitelist(java.util.List<String> computedStyleWhitelist) {
            set("computedStyleWhitelist", computedStyleWhitelist);
            return this;
        }
        /**
         * Whether or not to retrieve details of DOM listeners (default false).
         * @param includeEventListeners field value
         * @return this model
         */
        public GetSnapshotRequest includeEventListeners(boolean includeEventListeners) {
            set("includeEventListeners", includeEventListeners);
            return this;
        }
        /**
         * Whether to determine and include the paint order index of LayoutTreeNodes (default false).
         * @param includePaintOrder field value
         * @return this model
         */
        public GetSnapshotRequest includePaintOrder(boolean includePaintOrder) {
            set("includePaintOrder", includePaintOrder);
            return this;
        }
        /**
         * Whether to include UA shadow tree in the snapshot (default false).
         * @param includeUserAgentShadowTree field value
         * @return this model
         */
        public GetSnapshotRequest includeUserAgentShadowTree(boolean includeUserAgentShadowTree) {
            set("includeUserAgentShadowTree", includeUserAgentShadowTree);
            return this;
        }
    }
    /**
     * Returns a document snapshot, including the full DOM tree of the root node (including iframes, template contents, and imported documents) in a flattened array, as well as layout and white-listed computed style information for the nodes. Shadow DOM in the returned DOM tree is flattened.
     */
    public static final class CaptureSnapshotRequest extends CdpObject {
        public CaptureSnapshotRequest() {}
        public static CaptureSnapshotRequest fromMap(Map<String, Object> values) {
            final CaptureSnapshotRequest instance_ = new CaptureSnapshotRequest();
            if (values != null) instance_.values.putAll(values);
            return instance_;
        }
        /**
         * Whitelist of computed styles to return.
         * @return the protocol field value
         */
        public java.util.List<String> computedStyles() {
            return require(COMPUTED_STYLES);
        }
        /**
         * Whether to include layout object paint orders into the snapshot.
         * @return the protocol field value, empty when absent
         */
        public Optional<Boolean> includePaintOrder() {
            return optionalBoolean("includePaintOrder");
        }
        /**
         * Whether to include DOM rectangles (offsetRects, clientRects, scrollRects) into the snapshot
         * @return the protocol field value, empty when absent
         */
        public Optional<Boolean> includeDOMRects() {
            return optionalBoolean("includeDOMRects");
        }
        /**
         * Whether to include blended background colors in the snapshot (default: false). Blended background color is achieved by blending background colors of all elements that overlap with the current element.
         * <p><b>Experimental:</b> this part of CDP may change without notice.
         * @return the protocol field value, empty when absent
         */
        public Optional<Boolean> includeBlendedBackgroundColors() {
            return optionalBoolean("includeBlendedBackgroundColors");
        }
        /**
         * Whether to include text color opacity in the snapshot (default: false). An element might have the opacity property set that affects the text color of the element. The final text color opacity is computed based on the opacity of all overlapping elements.
         * <p><b>Experimental:</b> this part of CDP may change without notice.
         * @return the protocol field value, empty when absent
         */
        public Optional<Boolean> includeTextColorOpacities() {
            return optionalBoolean("includeTextColorOpacities");
        }
        /**
         * Whitelist of computed styles to return.
         * @param computedStyles field value
         * @return this model
         */
        public CaptureSnapshotRequest computedStyles(java.util.List<String> computedStyles) {
            set("computedStyles", computedStyles);
            return this;
        }
        /**
         * Whether to include layout object paint orders into the snapshot.
         * @param includePaintOrder field value
         * @return this model
         */
        public CaptureSnapshotRequest includePaintOrder(boolean includePaintOrder) {
            set("includePaintOrder", includePaintOrder);
            return this;
        }
        /**
         * Whether to include DOM rectangles (offsetRects, clientRects, scrollRects) into the snapshot
         * @param includeDOMRects field value
         * @return this model
         */
        public CaptureSnapshotRequest includeDOMRects(boolean includeDOMRects) {
            set("includeDOMRects", includeDOMRects);
            return this;
        }
        /**
         * Whether to include blended background colors in the snapshot (default: false). Blended background color is achieved by blending background colors of all elements that overlap with the current element.
         * <p><b>Experimental:</b> this part of CDP may change without notice.
         * @param includeBlendedBackgroundColors field value
         * @return this model
         */
        public CaptureSnapshotRequest includeBlendedBackgroundColors(boolean includeBlendedBackgroundColors) {
            set("includeBlendedBackgroundColors", includeBlendedBackgroundColors);
            return this;
        }
        /**
         * Whether to include text color opacity in the snapshot (default: false). An element might have the opacity property set that affects the text color of the element. The final text color opacity is computed based on the opacity of all overlapping elements.
         * <p><b>Experimental:</b> this part of CDP may change without notice.
         * @param includeTextColorOpacities field value
         * @return this model
         */
        public CaptureSnapshotRequest includeTextColorOpacities(boolean includeTextColorOpacities) {
            set("includeTextColorOpacities", includeTextColorOpacities);
            return this;
        }
    }
    /**
     * Returns a document snapshot, including the full DOM tree of the root node (including iframes, template contents, and imported documents) in a flattened array, as well as layout and white-listed computed style information for the nodes. Shadow DOM in the returned DOM tree is flattened.
     * @deprecated Deprecated by the Chromium DevTools Protocol.
     */
    @Deprecated
    public static final class GetSnapshotResult extends CdpObject {
        public GetSnapshotResult() {}
        private GetSnapshotResult(Map<String, Object> values) { super(values); }
        public static GetSnapshotResult fromMap(Map<String, Object> values) {
            return new GetSnapshotResult(values);
        }
        /**
         * The nodes in the DOM tree. The DOMNode at index 0 corresponds to the root document.
         * @return the protocol field value
         */
        public java.util.List<DOMSnapshot.DOMNode> domNodes() {
            return require(DOM_NODES);
        }
        /**
         * The nodes in the layout tree.
         * @return the protocol field value
         */
        public java.util.List<DOMSnapshot.LayoutTreeNode> layoutTreeNodes() {
            return require(LAYOUT_TREE_NODES);
        }
        /**
         * Whitelisted ComputedStyle properties for each node in the layout tree.
         * @return the protocol field value
         */
        public java.util.List<DOMSnapshot.ComputedStyle> computedStyles() {
            return require(COMPUTED_STYLES_2);
        }
        /**
         * The nodes in the DOM tree. The DOMNode at index 0 corresponds to the root document.
         * @param domNodes field value
         * @return this model
         */
        public GetSnapshotResult domNodes(java.util.List<DOMSnapshot.DOMNode> domNodes) {
            set("domNodes", domNodes);
            return this;
        }
        /**
         * The nodes in the layout tree.
         * @param layoutTreeNodes field value
         * @return this model
         */
        public GetSnapshotResult layoutTreeNodes(java.util.List<DOMSnapshot.LayoutTreeNode> layoutTreeNodes) {
            set("layoutTreeNodes", layoutTreeNodes);
            return this;
        }
        /**
         * Whitelisted ComputedStyle properties for each node in the layout tree.
         * @param computedStyles field value
         * @return this model
         */
        public GetSnapshotResult computedStyles(java.util.List<DOMSnapshot.ComputedStyle> computedStyles) {
            set("computedStyles", computedStyles);
            return this;
        }
    }
    /**
     * Returns a document snapshot, including the full DOM tree of the root node (including iframes, template contents, and imported documents) in a flattened array, as well as layout and white-listed computed style information for the nodes. Shadow DOM in the returned DOM tree is flattened.
     */
    public static final class CaptureSnapshotResult extends CdpObject {
        public CaptureSnapshotResult() {}
        private CaptureSnapshotResult(Map<String, Object> values) { super(values); }
        public static CaptureSnapshotResult fromMap(Map<String, Object> values) {
            return new CaptureSnapshotResult(values);
        }
        /**
         * The nodes in the DOM tree. The DOMNode at index 0 corresponds to the root document.
         * @return the protocol field value
         */
        public java.util.List<DOMSnapshot.DocumentSnapshot> documents() {
            return require(DOCUMENTS);
        }
        /**
         * Shared string table that all string properties refer to with indexes.
         * @return the protocol field value
         */
        public java.util.List<String> strings() {
            return require(STRINGS);
        }
        /**
         * The nodes in the DOM tree. The DOMNode at index 0 corresponds to the root document.
         * @param documents field value
         * @return this model
         */
        public CaptureSnapshotResult documents(java.util.List<DOMSnapshot.DocumentSnapshot> documents) {
            set("documents", documents);
            return this;
        }
        /**
         * Shared string table that all string properties refer to with indexes.
         * @param strings field value
         * @return this model
         */
        public CaptureSnapshotResult strings(java.util.List<String> strings) {
            set("strings", strings);
            return this;
        }
    }
    public static final class Client extends CdpDomainClient {
        public Client(CdpClient client) { super(client); }
        /**
         * Disables DOM snapshot agent for the given page.
         * @return a stage completing when the command completes
         */
        public CompletionStage<Void> disable() {
            return call("DOMSnapshot.disable");
        }
        /**
         * Enables DOM snapshot agent for the given page.
         * @return a stage completing when the command completes
         */
        public CompletionStage<Void> enable() {
            return call("DOMSnapshot.enable");
        }
        /**
         * Returns a document snapshot, including the full DOM tree of the root node (including iframes, template contents, and imported documents) in a flattened array, as well as layout and white-listed computed style information for the nodes. Shadow DOM in the returned DOM tree is flattened.
         * @param computedStyleWhitelist protocol value
         * @param includeEventListeners protocol value
         * @param includePaintOrder protocol value
         * @param includeUserAgentShadowTree protocol value
         * @return a stage completing with the command result
         * @deprecated Deprecated by the Chromium DevTools Protocol.
         */
        @Deprecated
        public CompletionStage<GetSnapshotResult> getSnapshot(java.util.List<String> computedStyleWhitelist, @Nullable Boolean includeEventListeners, @Nullable Boolean includePaintOrder, @Nullable Boolean includeUserAgentShadowTree) {
            return call("DOMSnapshot.getSnapshot", params("computedStyleWhitelist", computedStyleWhitelist, "includeEventListeners", includeEventListeners, "includePaintOrder", includePaintOrder, "includeUserAgentShadowTree", includeUserAgentShadowTree), GetSnapshotResult::fromMap);
        }
        /**
         * Returns a document snapshot, including the full DOM tree of the root node (including iframes, template contents, and imported documents) in a flattened array, as well as layout and white-listed computed style information for the nodes. Shadow DOM in the returned DOM tree is flattened.
         * @param computedStyleWhitelist protocol value
         * @return a stage completing with the command result
         * @deprecated Deprecated by the Chromium DevTools Protocol.
         */
        @Deprecated
        public CompletionStage<GetSnapshotResult> getSnapshot(java.util.List<String> computedStyleWhitelist) {
            return call("DOMSnapshot.getSnapshot", params("computedStyleWhitelist", computedStyleWhitelist), GetSnapshotResult::fromMap);
        }
        /**
         * Returns a document snapshot, including the full DOM tree of the root node (including iframes, template contents, and imported documents) in a flattened array, as well as layout and white-listed computed style information for the nodes. Shadow DOM in the returned DOM tree is flattened.
         * @param request request parameters
         * @return a stage completing with the command result
         * @deprecated Deprecated by the Chromium DevTools Protocol.
         */
        @Deprecated
        public CompletionStage<GetSnapshotResult> getSnapshot(GetSnapshotRequest request) {
            return call("DOMSnapshot.getSnapshot", request, GetSnapshotResult::fromMap);
        }
        /**
         * Returns a document snapshot, including the full DOM tree of the root node (including iframes, template contents, and imported documents) in a flattened array, as well as layout and white-listed computed style information for the nodes. Shadow DOM in the returned DOM tree is flattened.
         * @param computedStyles protocol value
         * @param includePaintOrder protocol value
         * @param includeDOMRects protocol value
         * @param includeBlendedBackgroundColors protocol value
         * @param includeTextColorOpacities protocol value
         * @return a stage completing with the command result
         */
        public CompletionStage<CaptureSnapshotResult> captureSnapshot(java.util.List<String> computedStyles, @Nullable Boolean includePaintOrder, @Nullable Boolean includeDOMRects, @Nullable Boolean includeBlendedBackgroundColors, @Nullable Boolean includeTextColorOpacities) {
            return call("DOMSnapshot.captureSnapshot", params("computedStyles", computedStyles, "includePaintOrder", includePaintOrder, "includeDOMRects", includeDOMRects, "includeBlendedBackgroundColors", includeBlendedBackgroundColors, "includeTextColorOpacities", includeTextColorOpacities), CaptureSnapshotResult::fromMap);
        }
        /**
         * Returns a document snapshot, including the full DOM tree of the root node (including iframes, template contents, and imported documents) in a flattened array, as well as layout and white-listed computed style information for the nodes. Shadow DOM in the returned DOM tree is flattened.
         * @param computedStyles protocol value
         * @return a stage completing with the command result
         */
        public CompletionStage<CaptureSnapshotResult> captureSnapshot(java.util.List<String> computedStyles) {
            return call("DOMSnapshot.captureSnapshot", params("computedStyles", computedStyles), CaptureSnapshotResult::fromMap);
        }
        /**
         * Returns a document snapshot, including the full DOM tree of the root node (including iframes, template contents, and imported documents) in a flattened array, as well as layout and white-listed computed style information for the nodes. Shadow DOM in the returned DOM tree is flattened.
         * @param request request parameters
         * @return a stage completing with the command result
         */
        public CompletionStage<CaptureSnapshotResult> captureSnapshot(CaptureSnapshotRequest request) {
            return call("DOMSnapshot.captureSnapshot", request, CaptureSnapshotResult::fromMap);
        }
    }
}
