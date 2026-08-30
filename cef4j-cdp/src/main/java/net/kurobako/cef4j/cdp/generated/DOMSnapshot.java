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
 * This domain facilitates obtaining document snapshots with DOM, layout, and style information.
 * <p><b>Experimental:</b> this part of CDP may change without notice.
 * @see <a href="https://chromium.googlesource.com/chromium/src/+/refs/tags/150.0.7871.213/third_party/blink/public/devtools_protocol/domains/DOMSnapshot.pdl">Pinned protocol source</a>
 */
@Generated("./mvnw generate-sources -pl cef4j-cdp -Dcef.version=150.0.18+gdb11278+chromium-150.0.7871.213")
@SuppressWarnings({"EscapedEntity", "InvalidParam", "JavaLangClash", "MissingSummary", "Unchecked", "UnusedMethod"})
public final class DOMSnapshot {
    private DOMSnapshot() {}
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
         * {@code Node}&#x27;s nodeValue.
         * @return the protocol field value
         */
        public String nodeValue() {
            return (String) require("nodeValue");
        }
        /**
         * Only set for textarea elements, contains the text value.
         * @return the protocol field value, empty when absent
         */
        public Optional<String> textValue() {
            return Optional.ofNullable((String) raw("textValue"));
        }
        /**
         * Only set for input elements, contains the input&#x27;s associated text value.
         * @return the protocol field value, empty when absent
         */
        public Optional<String> inputValue() {
            return Optional.ofNullable((String) raw("inputValue"));
        }
        /**
         * Only set for radio and checkbox input elements, indicates if the element has been checked
         * @return the protocol field value, empty when absent
         */
        public Optional<Boolean> inputChecked() {
            return Optional.ofNullable((Boolean) raw("inputChecked"));
        }
        /**
         * Only set for option elements, indicates if the element has been selected
         * @return the protocol field value, empty when absent
         */
        public Optional<Boolean> optionSelected() {
            return Optional.ofNullable((Boolean) raw("optionSelected"));
        }
        /**
         * {@code Node}&#x27;s id, corresponds to DOM.Node.backendNodeId.
         * @return the protocol field value
         */
        public DOM.BackendNodeId backendNodeId() {
            return new DOM.BackendNodeId(((Number) require("backendNodeId")).longValue());
        }
        /**
         * The indexes of the node&#x27;s child nodes in the {@code domNodes} array returned by {@code getSnapshot}, if any.
         * @return the protocol field value, empty when absent
         */
        public Optional<java.util.List<Long>> childNodeIndexes() {
            return Optional.ofNullable(list(raw("childNodeIndexes"), element0 -> ((Number) element0).longValue()));
        }
        /**
         * Attributes of an {@code Element} node.
         * @return the protocol field value, empty when absent
         */
        public Optional<java.util.List<DOMSnapshot.NameValue>> attributes() {
            return Optional.ofNullable(list(raw("attributes"), element0 -> java.util.Objects.requireNonNull(DOMSnapshot.NameValue.fromMap(java.util.Objects.requireNonNull(CdpObject.objectMap(element0))))));
        }
        /**
         * Indexes of pseudo elements associated with this node in the {@code domNodes} array returned by {@code getSnapshot}, if any.
         * @return the protocol field value, empty when absent
         */
        public Optional<java.util.List<Long>> pseudoElementIndexes() {
            return Optional.ofNullable(list(raw("pseudoElementIndexes"), element0 -> ((Number) element0).longValue()));
        }
        /**
         * The index of the node&#x27;s related layout tree node in the {@code layoutTreeNodes} array returned by {@code getSnapshot}, if any.
         * @return the protocol field value, empty when absent
         */
        public OptionalLong layoutNodeIndex() {
            Long value = CdpObject.numberAsLong(raw("layoutNodeIndex"));
            return value == null ? OptionalLong.empty() : OptionalLong.of(value);
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
         * Only set for documents, contains the document&#x27;s content language.
         * @return the protocol field value, empty when absent
         */
        public Optional<String> contentLanguage() {
            return Optional.ofNullable((String) raw("contentLanguage"));
        }
        /**
         * Only set for documents, contains the document&#x27;s character set encoding.
         * @return the protocol field value, empty when absent
         */
        public Optional<String> documentEncoding() {
            return Optional.ofNullable((String) raw("documentEncoding"));
        }
        /**
         * {@code DocumentType} node&#x27;s publicId.
         * @return the protocol field value, empty when absent
         */
        public Optional<String> publicId() {
            return Optional.ofNullable((String) raw("publicId"));
        }
        /**
         * {@code DocumentType} node&#x27;s systemId.
         * @return the protocol field value, empty when absent
         */
        public Optional<String> systemId() {
            return Optional.ofNullable((String) raw("systemId"));
        }
        /**
         * Frame ID for frame owner elements and also for the document node.
         * @return the protocol field value, empty when absent
         */
        public Optional<Page.FrameId> frameId() {
            return Optional.ofNullable(raw("frameId") == null ? null : new Page.FrameId((String) raw("frameId")));
        }
        /**
         * The index of a frame owner element&#x27;s content document in the {@code domNodes} array returned by {@code getSnapshot}, if any.
         * @return the protocol field value, empty when absent
         */
        public OptionalLong contentDocumentIndex() {
            Long value = CdpObject.numberAsLong(raw("contentDocumentIndex"));
            return value == null ? OptionalLong.empty() : OptionalLong.of(value);
        }
        /**
         * Type of a pseudo element node.
         * @return the protocol field value, empty when absent
         */
        public Optional<DOM.PseudoType> pseudoType() {
            return Optional.ofNullable(raw("pseudoType") == null ? null : DOM.PseudoType.of((String) raw("pseudoType")));
        }
        /**
         * Shadow root type.
         * @return the protocol field value, empty when absent
         */
        public Optional<DOM.ShadowRootType> shadowRootType() {
            return Optional.ofNullable(raw("shadowRootType") == null ? null : DOM.ShadowRootType.of((String) raw("shadowRootType")));
        }
        /**
         * Whether this DOM node responds to mouse clicks. This includes nodes that have had click event listeners attached via JavaScript as well as anchor tags that naturally navigate when clicked.
         * @return the protocol field value, empty when absent
         */
        public Optional<Boolean> isClickable() {
            return Optional.ofNullable((Boolean) raw("isClickable"));
        }
        /**
         * Details of the node&#x27;s event listeners, if any.
         * @return the protocol field value, empty when absent
         */
        public Optional<java.util.List<DOMDebugger.EventListener>> eventListeners() {
            return Optional.ofNullable(list(raw("eventListeners"), element0 -> java.util.Objects.requireNonNull(DOMDebugger.EventListener.fromMap(java.util.Objects.requireNonNull(CdpObject.objectMap(element0))))));
        }
        /**
         * The selected url for nodes with a srcset attribute.
         * @return the protocol field value, empty when absent
         */
        public Optional<String> currentSourceURL() {
            return Optional.ofNullable((String) raw("currentSourceURL"));
        }
        /**
         * The url of the script (if any) that generates this node.
         * @return the protocol field value, empty when absent
         */
        public Optional<String> originURL() {
            return Optional.ofNullable((String) raw("originURL"));
        }
        /**
         * Scroll offsets, set when this node is a Document.
         * @return the protocol field value, empty when absent
         */
        public OptionalDouble scrollOffsetX() {
            Double value = CdpObject.numberAsDouble(raw("scrollOffsetX"));
            return value == null ? OptionalDouble.empty() : OptionalDouble.of(value);
        }
        /**
         * Returns the scrollOffsetY field.
         * @return the protocol field value, empty when absent
         */
        public OptionalDouble scrollOffsetY() {
            Double value = CdpObject.numberAsDouble(raw("scrollOffsetY"));
            return value == null ? OptionalDouble.empty() : OptionalDouble.of(value);
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
         * @param textValue field value; empty omits the value
         * @return this model
         */
        public DOMNode textValue(Optional<String> textValue) {
            set("textValue", textValue.orElse(null));
            return this;
        }
        /**
         * Only set for textarea elements, contains the text value.
         * @param textValue field value; null removes the value
         * @return this model
         */
        public DOMNode textValue(String textValue) {
            set("textValue", textValue);
            return this;
        }
        /**
         * Only set for input elements, contains the input&#x27;s associated text value.
         * @param inputValue field value; empty omits the value
         * @return this model
         */
        public DOMNode inputValue(Optional<String> inputValue) {
            set("inputValue", inputValue.orElse(null));
            return this;
        }
        /**
         * Only set for input elements, contains the input&#x27;s associated text value.
         * @param inputValue field value; null removes the value
         * @return this model
         */
        public DOMNode inputValue(String inputValue) {
            set("inputValue", inputValue);
            return this;
        }
        /**
         * Only set for radio and checkbox input elements, indicates if the element has been checked
         * @param inputChecked field value; empty omits the value
         * @return this model
         */
        public DOMNode inputChecked(Optional<Boolean> inputChecked) {
            set("inputChecked", inputChecked.orElse(null));
            return this;
        }
        /**
         * Only set for radio and checkbox input elements, indicates if the element has been checked
         * @param inputChecked field value; null removes the value
         * @return this model
         */
        public DOMNode inputChecked(Boolean inputChecked) {
            set("inputChecked", inputChecked);
            return this;
        }
        /**
         * Only set for option elements, indicates if the element has been selected
         * @param optionSelected field value; empty omits the value
         * @return this model
         */
        public DOMNode optionSelected(Optional<Boolean> optionSelected) {
            set("optionSelected", optionSelected.orElse(null));
            return this;
        }
        /**
         * Only set for option elements, indicates if the element has been selected
         * @param optionSelected field value; null removes the value
         * @return this model
         */
        public DOMNode optionSelected(Boolean optionSelected) {
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
         * @param childNodeIndexes field value; empty omits the value
         * @return this model
         */
        public DOMNode childNodeIndexes(Optional<java.util.List<Long>> childNodeIndexes) {
            set("childNodeIndexes", childNodeIndexes.orElse(null));
            return this;
        }
        /**
         * The indexes of the node&#x27;s child nodes in the {@code domNodes} array returned by {@code getSnapshot}, if any.
         * @param childNodeIndexes field value; null removes the value
         * @return this model
         */
        public DOMNode childNodeIndexes(java.util.List<Long> childNodeIndexes) {
            set("childNodeIndexes", childNodeIndexes);
            return this;
        }
        /**
         * Attributes of an {@code Element} node.
         * @param attributes field value; empty omits the value
         * @return this model
         */
        public DOMNode attributes(Optional<java.util.List<DOMSnapshot.NameValue>> attributes) {
            set("attributes", attributes.orElse(null));
            return this;
        }
        /**
         * Attributes of an {@code Element} node.
         * @param attributes field value; null removes the value
         * @return this model
         */
        public DOMNode attributes(java.util.List<DOMSnapshot.NameValue> attributes) {
            set("attributes", attributes);
            return this;
        }
        /**
         * Indexes of pseudo elements associated with this node in the {@code domNodes} array returned by {@code getSnapshot}, if any.
         * @param pseudoElementIndexes field value; empty omits the value
         * @return this model
         */
        public DOMNode pseudoElementIndexes(Optional<java.util.List<Long>> pseudoElementIndexes) {
            set("pseudoElementIndexes", pseudoElementIndexes.orElse(null));
            return this;
        }
        /**
         * Indexes of pseudo elements associated with this node in the {@code domNodes} array returned by {@code getSnapshot}, if any.
         * @param pseudoElementIndexes field value; null removes the value
         * @return this model
         */
        public DOMNode pseudoElementIndexes(java.util.List<Long> pseudoElementIndexes) {
            set("pseudoElementIndexes", pseudoElementIndexes);
            return this;
        }
        /**
         * The index of the node&#x27;s related layout tree node in the {@code layoutTreeNodes} array returned by {@code getSnapshot}, if any.
         * @param layoutNodeIndex field value; empty omits the value
         * @return this model
         */
        public DOMNode layoutNodeIndex(OptionalLong layoutNodeIndex) {
            set("layoutNodeIndex", layoutNodeIndex.isPresent() ? layoutNodeIndex.getAsLong() : null);
            return this;
        }
        /**
         * The index of the node&#x27;s related layout tree node in the {@code layoutTreeNodes} array returned by {@code getSnapshot}, if any.
         * @param layoutNodeIndex field value; null removes the value
         * @return this model
         */
        public DOMNode layoutNodeIndex(Long layoutNodeIndex) {
            set("layoutNodeIndex", layoutNodeIndex);
            return this;
        }
        /**
         * Document URL that {@code Document} or {@code FrameOwner} node points to.
         * @param documentURL field value; empty omits the value
         * @return this model
         */
        public DOMNode documentURL(Optional<String> documentURL) {
            set("documentURL", documentURL.orElse(null));
            return this;
        }
        /**
         * Document URL that {@code Document} or {@code FrameOwner} node points to.
         * @param documentURL field value; null removes the value
         * @return this model
         */
        public DOMNode documentURL(String documentURL) {
            set("documentURL", documentURL);
            return this;
        }
        /**
         * Base URL that {@code Document} or {@code FrameOwner} node uses for URL completion.
         * @param baseURL field value; empty omits the value
         * @return this model
         */
        public DOMNode baseURL(Optional<String> baseURL) {
            set("baseURL", baseURL.orElse(null));
            return this;
        }
        /**
         * Base URL that {@code Document} or {@code FrameOwner} node uses for URL completion.
         * @param baseURL field value; null removes the value
         * @return this model
         */
        public DOMNode baseURL(String baseURL) {
            set("baseURL", baseURL);
            return this;
        }
        /**
         * Only set for documents, contains the document&#x27;s content language.
         * @param contentLanguage field value; empty omits the value
         * @return this model
         */
        public DOMNode contentLanguage(Optional<String> contentLanguage) {
            set("contentLanguage", contentLanguage.orElse(null));
            return this;
        }
        /**
         * Only set for documents, contains the document&#x27;s content language.
         * @param contentLanguage field value; null removes the value
         * @return this model
         */
        public DOMNode contentLanguage(String contentLanguage) {
            set("contentLanguage", contentLanguage);
            return this;
        }
        /**
         * Only set for documents, contains the document&#x27;s character set encoding.
         * @param documentEncoding field value; empty omits the value
         * @return this model
         */
        public DOMNode documentEncoding(Optional<String> documentEncoding) {
            set("documentEncoding", documentEncoding.orElse(null));
            return this;
        }
        /**
         * Only set for documents, contains the document&#x27;s character set encoding.
         * @param documentEncoding field value; null removes the value
         * @return this model
         */
        public DOMNode documentEncoding(String documentEncoding) {
            set("documentEncoding", documentEncoding);
            return this;
        }
        /**
         * {@code DocumentType} node&#x27;s publicId.
         * @param publicId field value; empty omits the value
         * @return this model
         */
        public DOMNode publicId(Optional<String> publicId) {
            set("publicId", publicId.orElse(null));
            return this;
        }
        /**
         * {@code DocumentType} node&#x27;s publicId.
         * @param publicId field value; null removes the value
         * @return this model
         */
        public DOMNode publicId(String publicId) {
            set("publicId", publicId);
            return this;
        }
        /**
         * {@code DocumentType} node&#x27;s systemId.
         * @param systemId field value; empty omits the value
         * @return this model
         */
        public DOMNode systemId(Optional<String> systemId) {
            set("systemId", systemId.orElse(null));
            return this;
        }
        /**
         * {@code DocumentType} node&#x27;s systemId.
         * @param systemId field value; null removes the value
         * @return this model
         */
        public DOMNode systemId(String systemId) {
            set("systemId", systemId);
            return this;
        }
        /**
         * Frame ID for frame owner elements and also for the document node.
         * @param frameId field value; empty omits the value
         * @return this model
         */
        public DOMNode frameId(Optional<Page.FrameId> frameId) {
            set("frameId", frameId.orElse(null));
            return this;
        }
        /**
         * Frame ID for frame owner elements and also for the document node.
         * @param frameId field value; null removes the value
         * @return this model
         */
        public DOMNode frameId(Page.FrameId frameId) {
            set("frameId", frameId);
            return this;
        }
        /**
         * The index of a frame owner element&#x27;s content document in the {@code domNodes} array returned by {@code getSnapshot}, if any.
         * @param contentDocumentIndex field value; empty omits the value
         * @return this model
         */
        public DOMNode contentDocumentIndex(OptionalLong contentDocumentIndex) {
            set("contentDocumentIndex", contentDocumentIndex.isPresent() ? contentDocumentIndex.getAsLong() : null);
            return this;
        }
        /**
         * The index of a frame owner element&#x27;s content document in the {@code domNodes} array returned by {@code getSnapshot}, if any.
         * @param contentDocumentIndex field value; null removes the value
         * @return this model
         */
        public DOMNode contentDocumentIndex(Long contentDocumentIndex) {
            set("contentDocumentIndex", contentDocumentIndex);
            return this;
        }
        /**
         * Type of a pseudo element node.
         * @param pseudoType field value; empty omits the value
         * @return this model
         */
        public DOMNode pseudoType(Optional<DOM.PseudoType> pseudoType) {
            set("pseudoType", pseudoType.orElse(null));
            return this;
        }
        /**
         * Type of a pseudo element node.
         * @param pseudoType field value; null removes the value
         * @return this model
         */
        public DOMNode pseudoType(DOM.PseudoType pseudoType) {
            set("pseudoType", pseudoType);
            return this;
        }
        /**
         * Shadow root type.
         * @param shadowRootType field value; empty omits the value
         * @return this model
         */
        public DOMNode shadowRootType(Optional<DOM.ShadowRootType> shadowRootType) {
            set("shadowRootType", shadowRootType.orElse(null));
            return this;
        }
        /**
         * Shadow root type.
         * @param shadowRootType field value; null removes the value
         * @return this model
         */
        public DOMNode shadowRootType(DOM.ShadowRootType shadowRootType) {
            set("shadowRootType", shadowRootType);
            return this;
        }
        /**
         * Whether this DOM node responds to mouse clicks. This includes nodes that have had click event listeners attached via JavaScript as well as anchor tags that naturally navigate when clicked.
         * @param isClickable field value; empty omits the value
         * @return this model
         */
        public DOMNode isClickable(Optional<Boolean> isClickable) {
            set("isClickable", isClickable.orElse(null));
            return this;
        }
        /**
         * Whether this DOM node responds to mouse clicks. This includes nodes that have had click event listeners attached via JavaScript as well as anchor tags that naturally navigate when clicked.
         * @param isClickable field value; null removes the value
         * @return this model
         */
        public DOMNode isClickable(Boolean isClickable) {
            set("isClickable", isClickable);
            return this;
        }
        /**
         * Details of the node&#x27;s event listeners, if any.
         * @param eventListeners field value; empty omits the value
         * @return this model
         */
        public DOMNode eventListeners(Optional<java.util.List<DOMDebugger.EventListener>> eventListeners) {
            set("eventListeners", eventListeners.orElse(null));
            return this;
        }
        /**
         * Details of the node&#x27;s event listeners, if any.
         * @param eventListeners field value; null removes the value
         * @return this model
         */
        public DOMNode eventListeners(java.util.List<DOMDebugger.EventListener> eventListeners) {
            set("eventListeners", eventListeners);
            return this;
        }
        /**
         * The selected url for nodes with a srcset attribute.
         * @param currentSourceURL field value; empty omits the value
         * @return this model
         */
        public DOMNode currentSourceURL(Optional<String> currentSourceURL) {
            set("currentSourceURL", currentSourceURL.orElse(null));
            return this;
        }
        /**
         * The selected url for nodes with a srcset attribute.
         * @param currentSourceURL field value; null removes the value
         * @return this model
         */
        public DOMNode currentSourceURL(String currentSourceURL) {
            set("currentSourceURL", currentSourceURL);
            return this;
        }
        /**
         * The url of the script (if any) that generates this node.
         * @param originURL field value; empty omits the value
         * @return this model
         */
        public DOMNode originURL(Optional<String> originURL) {
            set("originURL", originURL.orElse(null));
            return this;
        }
        /**
         * The url of the script (if any) that generates this node.
         * @param originURL field value; null removes the value
         * @return this model
         */
        public DOMNode originURL(String originURL) {
            set("originURL", originURL);
            return this;
        }
        /**
         * Scroll offsets, set when this node is a Document.
         * @param scrollOffsetX field value; empty omits the value
         * @return this model
         */
        public DOMNode scrollOffsetX(OptionalDouble scrollOffsetX) {
            set("scrollOffsetX", scrollOffsetX.isPresent() ? scrollOffsetX.getAsDouble() : null);
            return this;
        }
        /**
         * Scroll offsets, set when this node is a Document.
         * @param scrollOffsetX field value; null removes the value
         * @return this model
         */
        public DOMNode scrollOffsetX(Double scrollOffsetX) {
            set("scrollOffsetX", scrollOffsetX);
            return this;
        }
        /**
         * Sets the scrollOffsetY field.
         * @param scrollOffsetY field value; empty omits the value
         * @return this model
         */
        public DOMNode scrollOffsetY(OptionalDouble scrollOffsetY) {
            set("scrollOffsetY", scrollOffsetY.isPresent() ? scrollOffsetY.getAsDouble() : null);
            return this;
        }
        /**
         * Sets the scrollOffsetY field.
         * @param scrollOffsetY field value; null removes the value
         * @return this model
         */
        public DOMNode scrollOffsetY(Double scrollOffsetY) {
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
            return java.util.Objects.requireNonNull(DOM.Rect.fromMap(java.util.Objects.requireNonNull(CdpObject.objectMap(require("boundingBox")))));
        }
        /**
         * The starting index in characters, for this post layout textbox substring. Characters that would be represented as a surrogate pair in UTF-16 have length 2.
         * @return the protocol field value
         */
        public long startCharacterIndex() {
            return ((Number) require("startCharacterIndex")).longValue();
        }
        /**
         * The number of characters in this post layout textbox substring. Characters that would be represented as a surrogate pair in UTF-16 have length 2.
         * @return the protocol field value
         */
        public long numCharacters() {
            return ((Number) require("numCharacters")).longValue();
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
            return ((Number) require("domNodeIndex")).longValue();
        }
        /**
         * The bounding box in document coordinates. Note that scroll offset of the document is ignored.
         * @return the protocol field value
         */
        public DOM.Rect boundingBox() {
            return java.util.Objects.requireNonNull(DOM.Rect.fromMap(java.util.Objects.requireNonNull(CdpObject.objectMap(require("boundingBox")))));
        }
        /**
         * Contents of the LayoutText, if any.
         * @return the protocol field value, empty when absent
         */
        public Optional<String> layoutText() {
            return Optional.ofNullable((String) raw("layoutText"));
        }
        /**
         * The post-layout inline text nodes, if any.
         * @return the protocol field value, empty when absent
         */
        public Optional<java.util.List<DOMSnapshot.InlineTextBox>> inlineTextNodes() {
            return Optional.ofNullable(list(raw("inlineTextNodes"), element0 -> java.util.Objects.requireNonNull(DOMSnapshot.InlineTextBox.fromMap(java.util.Objects.requireNonNull(CdpObject.objectMap(element0))))));
        }
        /**
         * Index into the {@code computedStyles} array returned by {@code getSnapshot}.
         * @return the protocol field value, empty when absent
         */
        public OptionalLong styleIndex() {
            Long value = CdpObject.numberAsLong(raw("styleIndex"));
            return value == null ? OptionalLong.empty() : OptionalLong.of(value);
        }
        /**
         * Global paint order index, which is determined by the stacking order of the nodes. Nodes that are painted together will have the same index. Only provided if includePaintOrder in getSnapshot was true.
         * @return the protocol field value, empty when absent
         */
        public OptionalLong paintOrder() {
            Long value = CdpObject.numberAsLong(raw("paintOrder"));
            return value == null ? OptionalLong.empty() : OptionalLong.of(value);
        }
        /**
         * Set to true to indicate the element begins a new stacking context.
         * @return the protocol field value, empty when absent
         */
        public Optional<Boolean> isStackingContext() {
            return Optional.ofNullable((Boolean) raw("isStackingContext"));
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
         * @param layoutText field value; empty omits the value
         * @return this model
         */
        public LayoutTreeNode layoutText(Optional<String> layoutText) {
            set("layoutText", layoutText.orElse(null));
            return this;
        }
        /**
         * Contents of the LayoutText, if any.
         * @param layoutText field value; null removes the value
         * @return this model
         */
        public LayoutTreeNode layoutText(String layoutText) {
            set("layoutText", layoutText);
            return this;
        }
        /**
         * The post-layout inline text nodes, if any.
         * @param inlineTextNodes field value; empty omits the value
         * @return this model
         */
        public LayoutTreeNode inlineTextNodes(Optional<java.util.List<DOMSnapshot.InlineTextBox>> inlineTextNodes) {
            set("inlineTextNodes", inlineTextNodes.orElse(null));
            return this;
        }
        /**
         * The post-layout inline text nodes, if any.
         * @param inlineTextNodes field value; null removes the value
         * @return this model
         */
        public LayoutTreeNode inlineTextNodes(java.util.List<DOMSnapshot.InlineTextBox> inlineTextNodes) {
            set("inlineTextNodes", inlineTextNodes);
            return this;
        }
        /**
         * Index into the {@code computedStyles} array returned by {@code getSnapshot}.
         * @param styleIndex field value; empty omits the value
         * @return this model
         */
        public LayoutTreeNode styleIndex(OptionalLong styleIndex) {
            set("styleIndex", styleIndex.isPresent() ? styleIndex.getAsLong() : null);
            return this;
        }
        /**
         * Index into the {@code computedStyles} array returned by {@code getSnapshot}.
         * @param styleIndex field value; null removes the value
         * @return this model
         */
        public LayoutTreeNode styleIndex(Long styleIndex) {
            set("styleIndex", styleIndex);
            return this;
        }
        /**
         * Global paint order index, which is determined by the stacking order of the nodes. Nodes that are painted together will have the same index. Only provided if includePaintOrder in getSnapshot was true.
         * @param paintOrder field value; empty omits the value
         * @return this model
         */
        public LayoutTreeNode paintOrder(OptionalLong paintOrder) {
            set("paintOrder", paintOrder.isPresent() ? paintOrder.getAsLong() : null);
            return this;
        }
        /**
         * Global paint order index, which is determined by the stacking order of the nodes. Nodes that are painted together will have the same index. Only provided if includePaintOrder in getSnapshot was true.
         * @param paintOrder field value; null removes the value
         * @return this model
         */
        public LayoutTreeNode paintOrder(Long paintOrder) {
            set("paintOrder", paintOrder);
            return this;
        }
        /**
         * Set to true to indicate the element begins a new stacking context.
         * @param isStackingContext field value; empty omits the value
         * @return this model
         */
        public LayoutTreeNode isStackingContext(Optional<Boolean> isStackingContext) {
            set("isStackingContext", isStackingContext.orElse(null));
            return this;
        }
        /**
         * Set to true to indicate the element begins a new stacking context.
         * @param isStackingContext field value; null removes the value
         * @return this model
         */
        public LayoutTreeNode isStackingContext(Boolean isStackingContext) {
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
            return CdpObject.requireList(require("properties"), element0 -> java.util.Objects.requireNonNull(DOMSnapshot.NameValue.fromMap(java.util.Objects.requireNonNull(CdpObject.objectMap(element0)))));
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
            return (String) require("name");
        }
        /**
         * Attribute/property value.
         * @return the protocol field value
         */
        public String value() {
            return (String) require("value");
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
        @Nonnull public Long value() { return value; }
        @Override public boolean equals(Object other) {
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
            return CdpObject.requireList(require("index"), element0 -> ((Number) element0).longValue());
        }
        /**
         * Returns the value field.
         * @return the protocol field value
         */
        public java.util.List<DOMSnapshot.StringIndex> value() {
            return CdpObject.requireList(require("value"), element0 -> new DOMSnapshot.StringIndex(((Number) element0).longValue()));
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
            return CdpObject.requireList(require("index"), element0 -> ((Number) element0).longValue());
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
            return CdpObject.requireList(require("index"), element0 -> ((Number) element0).longValue());
        }
        /**
         * Returns the value field.
         * @return the protocol field value
         */
        public java.util.List<Long> value() {
            return CdpObject.requireList(require("value"), element0 -> ((Number) element0).longValue());
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
            return new DOMSnapshot.StringIndex(((Number) require("documentURL")).longValue());
        }
        /**
         * Document title.
         * @return the protocol field value
         */
        public DOMSnapshot.StringIndex title() {
            return new DOMSnapshot.StringIndex(((Number) require("title")).longValue());
        }
        /**
         * Base URL that {@code Document} or {@code FrameOwner} node uses for URL completion.
         * @return the protocol field value
         */
        public DOMSnapshot.StringIndex baseURL() {
            return new DOMSnapshot.StringIndex(((Number) require("baseURL")).longValue());
        }
        /**
         * Contains the document&#x27;s content language.
         * @return the protocol field value
         */
        public DOMSnapshot.StringIndex contentLanguage() {
            return new DOMSnapshot.StringIndex(((Number) require("contentLanguage")).longValue());
        }
        /**
         * Contains the document&#x27;s character set encoding.
         * @return the protocol field value
         */
        public DOMSnapshot.StringIndex encodingName() {
            return new DOMSnapshot.StringIndex(((Number) require("encodingName")).longValue());
        }
        /**
         * {@code DocumentType} node&#x27;s publicId.
         * @return the protocol field value
         */
        public DOMSnapshot.StringIndex publicId() {
            return new DOMSnapshot.StringIndex(((Number) require("publicId")).longValue());
        }
        /**
         * {@code DocumentType} node&#x27;s systemId.
         * @return the protocol field value
         */
        public DOMSnapshot.StringIndex systemId() {
            return new DOMSnapshot.StringIndex(((Number) require("systemId")).longValue());
        }
        /**
         * Frame ID for frame owner elements and also for the document node.
         * @return the protocol field value
         */
        public DOMSnapshot.StringIndex frameId() {
            return new DOMSnapshot.StringIndex(((Number) require("frameId")).longValue());
        }
        /**
         * A table with dom nodes.
         * @return the protocol field value
         */
        public DOMSnapshot.NodeTreeSnapshot nodes() {
            return java.util.Objects.requireNonNull(DOMSnapshot.NodeTreeSnapshot.fromMap(java.util.Objects.requireNonNull(CdpObject.objectMap(require("nodes")))));
        }
        /**
         * The nodes in the layout tree.
         * @return the protocol field value
         */
        public DOMSnapshot.LayoutTreeSnapshot layout() {
            return java.util.Objects.requireNonNull(DOMSnapshot.LayoutTreeSnapshot.fromMap(java.util.Objects.requireNonNull(CdpObject.objectMap(require("layout")))));
        }
        /**
         * The post-layout inline text nodes.
         * @return the protocol field value
         */
        public DOMSnapshot.TextBoxSnapshot textBoxes() {
            return java.util.Objects.requireNonNull(DOMSnapshot.TextBoxSnapshot.fromMap(java.util.Objects.requireNonNull(CdpObject.objectMap(require("textBoxes")))));
        }
        /**
         * Horizontal scroll offset.
         * @return the protocol field value, empty when absent
         */
        public OptionalDouble scrollOffsetX() {
            Double value = CdpObject.numberAsDouble(raw("scrollOffsetX"));
            return value == null ? OptionalDouble.empty() : OptionalDouble.of(value);
        }
        /**
         * Vertical scroll offset.
         * @return the protocol field value, empty when absent
         */
        public OptionalDouble scrollOffsetY() {
            Double value = CdpObject.numberAsDouble(raw("scrollOffsetY"));
            return value == null ? OptionalDouble.empty() : OptionalDouble.of(value);
        }
        /**
         * Document content width.
         * @return the protocol field value, empty when absent
         */
        public OptionalDouble contentWidth() {
            Double value = CdpObject.numberAsDouble(raw("contentWidth"));
            return value == null ? OptionalDouble.empty() : OptionalDouble.of(value);
        }
        /**
         * Document content height.
         * @return the protocol field value, empty when absent
         */
        public OptionalDouble contentHeight() {
            Double value = CdpObject.numberAsDouble(raw("contentHeight"));
            return value == null ? OptionalDouble.empty() : OptionalDouble.of(value);
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
         * @param scrollOffsetX field value; empty omits the value
         * @return this model
         */
        public DocumentSnapshot scrollOffsetX(OptionalDouble scrollOffsetX) {
            set("scrollOffsetX", scrollOffsetX.isPresent() ? scrollOffsetX.getAsDouble() : null);
            return this;
        }
        /**
         * Horizontal scroll offset.
         * @param scrollOffsetX field value; null removes the value
         * @return this model
         */
        public DocumentSnapshot scrollOffsetX(Double scrollOffsetX) {
            set("scrollOffsetX", scrollOffsetX);
            return this;
        }
        /**
         * Vertical scroll offset.
         * @param scrollOffsetY field value; empty omits the value
         * @return this model
         */
        public DocumentSnapshot scrollOffsetY(OptionalDouble scrollOffsetY) {
            set("scrollOffsetY", scrollOffsetY.isPresent() ? scrollOffsetY.getAsDouble() : null);
            return this;
        }
        /**
         * Vertical scroll offset.
         * @param scrollOffsetY field value; null removes the value
         * @return this model
         */
        public DocumentSnapshot scrollOffsetY(Double scrollOffsetY) {
            set("scrollOffsetY", scrollOffsetY);
            return this;
        }
        /**
         * Document content width.
         * @param contentWidth field value; empty omits the value
         * @return this model
         */
        public DocumentSnapshot contentWidth(OptionalDouble contentWidth) {
            set("contentWidth", contentWidth.isPresent() ? contentWidth.getAsDouble() : null);
            return this;
        }
        /**
         * Document content width.
         * @param contentWidth field value; null removes the value
         * @return this model
         */
        public DocumentSnapshot contentWidth(Double contentWidth) {
            set("contentWidth", contentWidth);
            return this;
        }
        /**
         * Document content height.
         * @param contentHeight field value; empty omits the value
         * @return this model
         */
        public DocumentSnapshot contentHeight(OptionalDouble contentHeight) {
            set("contentHeight", contentHeight.isPresent() ? contentHeight.getAsDouble() : null);
            return this;
        }
        /**
         * Document content height.
         * @param contentHeight field value; null removes the value
         * @return this model
         */
        public DocumentSnapshot contentHeight(Double contentHeight) {
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
            return Optional.ofNullable(list(raw("parentIndex"), element0 -> ((Number) element0).longValue()));
        }
        /**
         * {@code Node}&#x27;s nodeType.
         * @return the protocol field value, empty when absent
         */
        public Optional<java.util.List<Long>> nodeType() {
            return Optional.ofNullable(list(raw("nodeType"), element0 -> ((Number) element0).longValue()));
        }
        /**
         * Type of the shadow root the {@code Node} is in. String values are equal to the {@code ShadowRootType} enum.
         * @return the protocol field value, empty when absent
         */
        public Optional<DOMSnapshot.RareStringData> shadowRootType() {
            return Optional.ofNullable(raw("shadowRootType") == null ? null : DOMSnapshot.RareStringData.fromMap(java.util.Objects.requireNonNull(objectMap(raw("shadowRootType")))));
        }
        /**
         * {@code Node}&#x27;s nodeName.
         * @return the protocol field value, empty when absent
         */
        public Optional<java.util.List<DOMSnapshot.StringIndex>> nodeName() {
            return Optional.ofNullable(list(raw("nodeName"), element0 -> new DOMSnapshot.StringIndex(((Number) element0).longValue())));
        }
        /**
         * {@code Node}&#x27;s nodeValue.
         * @return the protocol field value, empty when absent
         */
        public Optional<java.util.List<DOMSnapshot.StringIndex>> nodeValue() {
            return Optional.ofNullable(list(raw("nodeValue"), element0 -> new DOMSnapshot.StringIndex(((Number) element0).longValue())));
        }
        /**
         * {@code Node}&#x27;s id, corresponds to DOM.Node.backendNodeId.
         * @return the protocol field value, empty when absent
         */
        public Optional<java.util.List<DOM.BackendNodeId>> backendNodeId() {
            return Optional.ofNullable(list(raw("backendNodeId"), element0 -> new DOM.BackendNodeId(((Number) element0).longValue())));
        }
        /**
         * Attributes of an {@code Element} node. Flatten name, value pairs.
         * @return the protocol field value, empty when absent
         */
        public Optional<java.util.List<java.util.List<DOMSnapshot.StringIndex>>> attributes() {
            return Optional.ofNullable(list(raw("attributes"), element0 -> CdpObject.requireList(element0, element1 -> new DOMSnapshot.StringIndex(((Number) element1).longValue()))));
        }
        /**
         * Only set for textarea elements, contains the text value.
         * @return the protocol field value, empty when absent
         */
        public Optional<DOMSnapshot.RareStringData> textValue() {
            return Optional.ofNullable(raw("textValue") == null ? null : DOMSnapshot.RareStringData.fromMap(java.util.Objects.requireNonNull(objectMap(raw("textValue")))));
        }
        /**
         * Only set for input elements, contains the input&#x27;s associated text value.
         * @return the protocol field value, empty when absent
         */
        public Optional<DOMSnapshot.RareStringData> inputValue() {
            return Optional.ofNullable(raw("inputValue") == null ? null : DOMSnapshot.RareStringData.fromMap(java.util.Objects.requireNonNull(objectMap(raw("inputValue")))));
        }
        /**
         * Only set for radio and checkbox input elements, indicates if the element has been checked
         * @return the protocol field value, empty when absent
         */
        public Optional<DOMSnapshot.RareBooleanData> inputChecked() {
            return Optional.ofNullable(raw("inputChecked") == null ? null : DOMSnapshot.RareBooleanData.fromMap(java.util.Objects.requireNonNull(objectMap(raw("inputChecked")))));
        }
        /**
         * Only set for option elements, indicates if the element has been selected
         * @return the protocol field value, empty when absent
         */
        public Optional<DOMSnapshot.RareBooleanData> optionSelected() {
            return Optional.ofNullable(raw("optionSelected") == null ? null : DOMSnapshot.RareBooleanData.fromMap(java.util.Objects.requireNonNull(objectMap(raw("optionSelected")))));
        }
        /**
         * The index of the document in the list of the snapshot documents.
         * @return the protocol field value, empty when absent
         */
        public Optional<DOMSnapshot.RareIntegerData> contentDocumentIndex() {
            return Optional.ofNullable(raw("contentDocumentIndex") == null ? null : DOMSnapshot.RareIntegerData.fromMap(java.util.Objects.requireNonNull(objectMap(raw("contentDocumentIndex")))));
        }
        /**
         * Type of a pseudo element node.
         * @return the protocol field value, empty when absent
         */
        public Optional<DOMSnapshot.RareStringData> pseudoType() {
            return Optional.ofNullable(raw("pseudoType") == null ? null : DOMSnapshot.RareStringData.fromMap(java.util.Objects.requireNonNull(objectMap(raw("pseudoType")))));
        }
        /**
         * Pseudo element identifier for this node. Only present if there is a valid pseudoType.
         * @return the protocol field value, empty when absent
         */
        public Optional<DOMSnapshot.RareStringData> pseudoIdentifier() {
            return Optional.ofNullable(raw("pseudoIdentifier") == null ? null : DOMSnapshot.RareStringData.fromMap(java.util.Objects.requireNonNull(objectMap(raw("pseudoIdentifier")))));
        }
        /**
         * Whether this DOM node responds to mouse clicks. This includes nodes that have had click event listeners attached via JavaScript as well as anchor tags that naturally navigate when clicked.
         * @return the protocol field value, empty when absent
         */
        public Optional<DOMSnapshot.RareBooleanData> isClickable() {
            return Optional.ofNullable(raw("isClickable") == null ? null : DOMSnapshot.RareBooleanData.fromMap(java.util.Objects.requireNonNull(objectMap(raw("isClickable")))));
        }
        /**
         * The selected url for nodes with a srcset attribute.
         * @return the protocol field value, empty when absent
         */
        public Optional<DOMSnapshot.RareStringData> currentSourceURL() {
            return Optional.ofNullable(raw("currentSourceURL") == null ? null : DOMSnapshot.RareStringData.fromMap(java.util.Objects.requireNonNull(objectMap(raw("currentSourceURL")))));
        }
        /**
         * The url of the script (if any) that generates this node.
         * @return the protocol field value, empty when absent
         */
        public Optional<DOMSnapshot.RareStringData> originURL() {
            return Optional.ofNullable(raw("originURL") == null ? null : DOMSnapshot.RareStringData.fromMap(java.util.Objects.requireNonNull(objectMap(raw("originURL")))));
        }
        /**
         * Parent node index.
         * @param parentIndex field value; empty omits the value
         * @return this model
         */
        public NodeTreeSnapshot parentIndex(Optional<java.util.List<Long>> parentIndex) {
            set("parentIndex", parentIndex.orElse(null));
            return this;
        }
        /**
         * Parent node index.
         * @param parentIndex field value; null removes the value
         * @return this model
         */
        public NodeTreeSnapshot parentIndex(java.util.List<Long> parentIndex) {
            set("parentIndex", parentIndex);
            return this;
        }
        /**
         * {@code Node}&#x27;s nodeType.
         * @param nodeType field value; empty omits the value
         * @return this model
         */
        public NodeTreeSnapshot nodeType(Optional<java.util.List<Long>> nodeType) {
            set("nodeType", nodeType.orElse(null));
            return this;
        }
        /**
         * {@code Node}&#x27;s nodeType.
         * @param nodeType field value; null removes the value
         * @return this model
         */
        public NodeTreeSnapshot nodeType(java.util.List<Long> nodeType) {
            set("nodeType", nodeType);
            return this;
        }
        /**
         * Type of the shadow root the {@code Node} is in. String values are equal to the {@code ShadowRootType} enum.
         * @param shadowRootType field value; empty omits the value
         * @return this model
         */
        public NodeTreeSnapshot shadowRootType(Optional<DOMSnapshot.RareStringData> shadowRootType) {
            set("shadowRootType", shadowRootType.orElse(null));
            return this;
        }
        /**
         * Type of the shadow root the {@code Node} is in. String values are equal to the {@code ShadowRootType} enum.
         * @param shadowRootType field value; null removes the value
         * @return this model
         */
        public NodeTreeSnapshot shadowRootType(DOMSnapshot.RareStringData shadowRootType) {
            set("shadowRootType", shadowRootType);
            return this;
        }
        /**
         * {@code Node}&#x27;s nodeName.
         * @param nodeName field value; empty omits the value
         * @return this model
         */
        public NodeTreeSnapshot nodeName(Optional<java.util.List<DOMSnapshot.StringIndex>> nodeName) {
            set("nodeName", nodeName.orElse(null));
            return this;
        }
        /**
         * {@code Node}&#x27;s nodeName.
         * @param nodeName field value; null removes the value
         * @return this model
         */
        public NodeTreeSnapshot nodeName(java.util.List<DOMSnapshot.StringIndex> nodeName) {
            set("nodeName", nodeName);
            return this;
        }
        /**
         * {@code Node}&#x27;s nodeValue.
         * @param nodeValue field value; empty omits the value
         * @return this model
         */
        public NodeTreeSnapshot nodeValue(Optional<java.util.List<DOMSnapshot.StringIndex>> nodeValue) {
            set("nodeValue", nodeValue.orElse(null));
            return this;
        }
        /**
         * {@code Node}&#x27;s nodeValue.
         * @param nodeValue field value; null removes the value
         * @return this model
         */
        public NodeTreeSnapshot nodeValue(java.util.List<DOMSnapshot.StringIndex> nodeValue) {
            set("nodeValue", nodeValue);
            return this;
        }
        /**
         * {@code Node}&#x27;s id, corresponds to DOM.Node.backendNodeId.
         * @param backendNodeId field value; empty omits the value
         * @return this model
         */
        public NodeTreeSnapshot backendNodeId(Optional<java.util.List<DOM.BackendNodeId>> backendNodeId) {
            set("backendNodeId", backendNodeId.orElse(null));
            return this;
        }
        /**
         * {@code Node}&#x27;s id, corresponds to DOM.Node.backendNodeId.
         * @param backendNodeId field value; null removes the value
         * @return this model
         */
        public NodeTreeSnapshot backendNodeId(java.util.List<DOM.BackendNodeId> backendNodeId) {
            set("backendNodeId", backendNodeId);
            return this;
        }
        /**
         * Attributes of an {@code Element} node. Flatten name, value pairs.
         * @param attributes field value; empty omits the value
         * @return this model
         */
        public NodeTreeSnapshot attributes(Optional<java.util.List<java.util.List<DOMSnapshot.StringIndex>>> attributes) {
            set("attributes", attributes.orElse(null));
            return this;
        }
        /**
         * Attributes of an {@code Element} node. Flatten name, value pairs.
         * @param attributes field value; null removes the value
         * @return this model
         */
        public NodeTreeSnapshot attributes(java.util.List<java.util.List<DOMSnapshot.StringIndex>> attributes) {
            set("attributes", attributes);
            return this;
        }
        /**
         * Only set for textarea elements, contains the text value.
         * @param textValue field value; empty omits the value
         * @return this model
         */
        public NodeTreeSnapshot textValue(Optional<DOMSnapshot.RareStringData> textValue) {
            set("textValue", textValue.orElse(null));
            return this;
        }
        /**
         * Only set for textarea elements, contains the text value.
         * @param textValue field value; null removes the value
         * @return this model
         */
        public NodeTreeSnapshot textValue(DOMSnapshot.RareStringData textValue) {
            set("textValue", textValue);
            return this;
        }
        /**
         * Only set for input elements, contains the input&#x27;s associated text value.
         * @param inputValue field value; empty omits the value
         * @return this model
         */
        public NodeTreeSnapshot inputValue(Optional<DOMSnapshot.RareStringData> inputValue) {
            set("inputValue", inputValue.orElse(null));
            return this;
        }
        /**
         * Only set for input elements, contains the input&#x27;s associated text value.
         * @param inputValue field value; null removes the value
         * @return this model
         */
        public NodeTreeSnapshot inputValue(DOMSnapshot.RareStringData inputValue) {
            set("inputValue", inputValue);
            return this;
        }
        /**
         * Only set for radio and checkbox input elements, indicates if the element has been checked
         * @param inputChecked field value; empty omits the value
         * @return this model
         */
        public NodeTreeSnapshot inputChecked(Optional<DOMSnapshot.RareBooleanData> inputChecked) {
            set("inputChecked", inputChecked.orElse(null));
            return this;
        }
        /**
         * Only set for radio and checkbox input elements, indicates if the element has been checked
         * @param inputChecked field value; null removes the value
         * @return this model
         */
        public NodeTreeSnapshot inputChecked(DOMSnapshot.RareBooleanData inputChecked) {
            set("inputChecked", inputChecked);
            return this;
        }
        /**
         * Only set for option elements, indicates if the element has been selected
         * @param optionSelected field value; empty omits the value
         * @return this model
         */
        public NodeTreeSnapshot optionSelected(Optional<DOMSnapshot.RareBooleanData> optionSelected) {
            set("optionSelected", optionSelected.orElse(null));
            return this;
        }
        /**
         * Only set for option elements, indicates if the element has been selected
         * @param optionSelected field value; null removes the value
         * @return this model
         */
        public NodeTreeSnapshot optionSelected(DOMSnapshot.RareBooleanData optionSelected) {
            set("optionSelected", optionSelected);
            return this;
        }
        /**
         * The index of the document in the list of the snapshot documents.
         * @param contentDocumentIndex field value; empty omits the value
         * @return this model
         */
        public NodeTreeSnapshot contentDocumentIndex(Optional<DOMSnapshot.RareIntegerData> contentDocumentIndex) {
            set("contentDocumentIndex", contentDocumentIndex.orElse(null));
            return this;
        }
        /**
         * The index of the document in the list of the snapshot documents.
         * @param contentDocumentIndex field value; null removes the value
         * @return this model
         */
        public NodeTreeSnapshot contentDocumentIndex(DOMSnapshot.RareIntegerData contentDocumentIndex) {
            set("contentDocumentIndex", contentDocumentIndex);
            return this;
        }
        /**
         * Type of a pseudo element node.
         * @param pseudoType field value; empty omits the value
         * @return this model
         */
        public NodeTreeSnapshot pseudoType(Optional<DOMSnapshot.RareStringData> pseudoType) {
            set("pseudoType", pseudoType.orElse(null));
            return this;
        }
        /**
         * Type of a pseudo element node.
         * @param pseudoType field value; null removes the value
         * @return this model
         */
        public NodeTreeSnapshot pseudoType(DOMSnapshot.RareStringData pseudoType) {
            set("pseudoType", pseudoType);
            return this;
        }
        /**
         * Pseudo element identifier for this node. Only present if there is a valid pseudoType.
         * @param pseudoIdentifier field value; empty omits the value
         * @return this model
         */
        public NodeTreeSnapshot pseudoIdentifier(Optional<DOMSnapshot.RareStringData> pseudoIdentifier) {
            set("pseudoIdentifier", pseudoIdentifier.orElse(null));
            return this;
        }
        /**
         * Pseudo element identifier for this node. Only present if there is a valid pseudoType.
         * @param pseudoIdentifier field value; null removes the value
         * @return this model
         */
        public NodeTreeSnapshot pseudoIdentifier(DOMSnapshot.RareStringData pseudoIdentifier) {
            set("pseudoIdentifier", pseudoIdentifier);
            return this;
        }
        /**
         * Whether this DOM node responds to mouse clicks. This includes nodes that have had click event listeners attached via JavaScript as well as anchor tags that naturally navigate when clicked.
         * @param isClickable field value; empty omits the value
         * @return this model
         */
        public NodeTreeSnapshot isClickable(Optional<DOMSnapshot.RareBooleanData> isClickable) {
            set("isClickable", isClickable.orElse(null));
            return this;
        }
        /**
         * Whether this DOM node responds to mouse clicks. This includes nodes that have had click event listeners attached via JavaScript as well as anchor tags that naturally navigate when clicked.
         * @param isClickable field value; null removes the value
         * @return this model
         */
        public NodeTreeSnapshot isClickable(DOMSnapshot.RareBooleanData isClickable) {
            set("isClickable", isClickable);
            return this;
        }
        /**
         * The selected url for nodes with a srcset attribute.
         * @param currentSourceURL field value; empty omits the value
         * @return this model
         */
        public NodeTreeSnapshot currentSourceURL(Optional<DOMSnapshot.RareStringData> currentSourceURL) {
            set("currentSourceURL", currentSourceURL.orElse(null));
            return this;
        }
        /**
         * The selected url for nodes with a srcset attribute.
         * @param currentSourceURL field value; null removes the value
         * @return this model
         */
        public NodeTreeSnapshot currentSourceURL(DOMSnapshot.RareStringData currentSourceURL) {
            set("currentSourceURL", currentSourceURL);
            return this;
        }
        /**
         * The url of the script (if any) that generates this node.
         * @param originURL field value; empty omits the value
         * @return this model
         */
        public NodeTreeSnapshot originURL(Optional<DOMSnapshot.RareStringData> originURL) {
            set("originURL", originURL.orElse(null));
            return this;
        }
        /**
         * The url of the script (if any) that generates this node.
         * @param originURL field value; null removes the value
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
            return CdpObject.requireList(require("nodeIndex"), element0 -> ((Number) element0).longValue());
        }
        /**
         * Array of indexes specifying computed style strings, filtered according to the {@code computedStyles} parameter passed to {@code captureSnapshot}.
         * @return the protocol field value
         */
        public java.util.List<java.util.List<DOMSnapshot.StringIndex>> styles() {
            return CdpObject.requireList(require("styles"), element0 -> CdpObject.requireList(element0, element1 -> new DOMSnapshot.StringIndex(((Number) element1).longValue())));
        }
        /**
         * The absolute position bounding box.
         * @return the protocol field value
         */
        public java.util.List<java.util.List<Double>> bounds() {
            return CdpObject.requireList(require("bounds"), element0 -> CdpObject.requireList(element0, element1 -> ((Number) element1).doubleValue()));
        }
        /**
         * Contents of the LayoutText, if any.
         * @return the protocol field value
         */
        public java.util.List<DOMSnapshot.StringIndex> text() {
            return CdpObject.requireList(require("text"), element0 -> new DOMSnapshot.StringIndex(((Number) element0).longValue()));
        }
        /**
         * Stacking context information.
         * @return the protocol field value
         */
        public DOMSnapshot.RareBooleanData stackingContexts() {
            return java.util.Objects.requireNonNull(DOMSnapshot.RareBooleanData.fromMap(java.util.Objects.requireNonNull(CdpObject.objectMap(require("stackingContexts")))));
        }
        /**
         * Global paint order index, which is determined by the stacking order of the nodes. Nodes that are painted together will have the same index. Only provided if includePaintOrder in captureSnapshot was true.
         * @return the protocol field value, empty when absent
         */
        public Optional<java.util.List<Long>> paintOrders() {
            return Optional.ofNullable(list(raw("paintOrders"), element0 -> ((Number) element0).longValue()));
        }
        /**
         * The offset rect of nodes. Only available when includeDOMRects is set to true
         * @return the protocol field value, empty when absent
         */
        public Optional<java.util.List<java.util.List<Double>>> offsetRects() {
            return Optional.ofNullable(list(raw("offsetRects"), element0 -> CdpObject.requireList(element0, element1 -> ((Number) element1).doubleValue())));
        }
        /**
         * The scroll rect of nodes. Only available when includeDOMRects is set to true
         * @return the protocol field value, empty when absent
         */
        public Optional<java.util.List<java.util.List<Double>>> scrollRects() {
            return Optional.ofNullable(list(raw("scrollRects"), element0 -> CdpObject.requireList(element0, element1 -> ((Number) element1).doubleValue())));
        }
        /**
         * The client rect of nodes. Only available when includeDOMRects is set to true
         * @return the protocol field value, empty when absent
         */
        public Optional<java.util.List<java.util.List<Double>>> clientRects() {
            return Optional.ofNullable(list(raw("clientRects"), element0 -> CdpObject.requireList(element0, element1 -> ((Number) element1).doubleValue())));
        }
        /**
         * The list of background colors that are blended with colors of overlapping elements.
         * <p><b>Experimental:</b> this part of CDP may change without notice.
         * @return the protocol field value, empty when absent
         */
        public Optional<java.util.List<DOMSnapshot.StringIndex>> blendedBackgroundColors() {
            return Optional.ofNullable(list(raw("blendedBackgroundColors"), element0 -> new DOMSnapshot.StringIndex(((Number) element0).longValue())));
        }
        /**
         * The list of computed text opacities.
         * <p><b>Experimental:</b> this part of CDP may change without notice.
         * @return the protocol field value, empty when absent
         */
        public Optional<java.util.List<Double>> textColorOpacities() {
            return Optional.ofNullable(list(raw("textColorOpacities"), element0 -> ((Number) element0).doubleValue()));
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
         * @param paintOrders field value; empty omits the value
         * @return this model
         */
        public LayoutTreeSnapshot paintOrders(Optional<java.util.List<Long>> paintOrders) {
            set("paintOrders", paintOrders.orElse(null));
            return this;
        }
        /**
         * Global paint order index, which is determined by the stacking order of the nodes. Nodes that are painted together will have the same index. Only provided if includePaintOrder in captureSnapshot was true.
         * @param paintOrders field value; null removes the value
         * @return this model
         */
        public LayoutTreeSnapshot paintOrders(java.util.List<Long> paintOrders) {
            set("paintOrders", paintOrders);
            return this;
        }
        /**
         * The offset rect of nodes. Only available when includeDOMRects is set to true
         * @param offsetRects field value; empty omits the value
         * @return this model
         */
        public LayoutTreeSnapshot offsetRects(Optional<java.util.List<java.util.List<Double>>> offsetRects) {
            set("offsetRects", offsetRects.orElse(null));
            return this;
        }
        /**
         * The offset rect of nodes. Only available when includeDOMRects is set to true
         * @param offsetRects field value; null removes the value
         * @return this model
         */
        public LayoutTreeSnapshot offsetRects(java.util.List<java.util.List<Double>> offsetRects) {
            set("offsetRects", offsetRects);
            return this;
        }
        /**
         * The scroll rect of nodes. Only available when includeDOMRects is set to true
         * @param scrollRects field value; empty omits the value
         * @return this model
         */
        public LayoutTreeSnapshot scrollRects(Optional<java.util.List<java.util.List<Double>>> scrollRects) {
            set("scrollRects", scrollRects.orElse(null));
            return this;
        }
        /**
         * The scroll rect of nodes. Only available when includeDOMRects is set to true
         * @param scrollRects field value; null removes the value
         * @return this model
         */
        public LayoutTreeSnapshot scrollRects(java.util.List<java.util.List<Double>> scrollRects) {
            set("scrollRects", scrollRects);
            return this;
        }
        /**
         * The client rect of nodes. Only available when includeDOMRects is set to true
         * @param clientRects field value; empty omits the value
         * @return this model
         */
        public LayoutTreeSnapshot clientRects(Optional<java.util.List<java.util.List<Double>>> clientRects) {
            set("clientRects", clientRects.orElse(null));
            return this;
        }
        /**
         * The client rect of nodes. Only available when includeDOMRects is set to true
         * @param clientRects field value; null removes the value
         * @return this model
         */
        public LayoutTreeSnapshot clientRects(java.util.List<java.util.List<Double>> clientRects) {
            set("clientRects", clientRects);
            return this;
        }
        /**
         * The list of background colors that are blended with colors of overlapping elements.
         * <p><b>Experimental:</b> this part of CDP may change without notice.
         * @param blendedBackgroundColors field value; empty omits the value
         * @return this model
         */
        public LayoutTreeSnapshot blendedBackgroundColors(Optional<java.util.List<DOMSnapshot.StringIndex>> blendedBackgroundColors) {
            set("blendedBackgroundColors", blendedBackgroundColors.orElse(null));
            return this;
        }
        /**
         * The list of background colors that are blended with colors of overlapping elements.
         * <p><b>Experimental:</b> this part of CDP may change without notice.
         * @param blendedBackgroundColors field value; null removes the value
         * @return this model
         */
        public LayoutTreeSnapshot blendedBackgroundColors(java.util.List<DOMSnapshot.StringIndex> blendedBackgroundColors) {
            set("blendedBackgroundColors", blendedBackgroundColors);
            return this;
        }
        /**
         * The list of computed text opacities.
         * <p><b>Experimental:</b> this part of CDP may change without notice.
         * @param textColorOpacities field value; empty omits the value
         * @return this model
         */
        public LayoutTreeSnapshot textColorOpacities(Optional<java.util.List<Double>> textColorOpacities) {
            set("textColorOpacities", textColorOpacities.orElse(null));
            return this;
        }
        /**
         * The list of computed text opacities.
         * <p><b>Experimental:</b> this part of CDP may change without notice.
         * @param textColorOpacities field value; null removes the value
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
            return CdpObject.requireList(require("layoutIndex"), element0 -> ((Number) element0).longValue());
        }
        /**
         * The absolute position bounding box.
         * @return the protocol field value
         */
        public java.util.List<java.util.List<Double>> bounds() {
            return CdpObject.requireList(require("bounds"), element0 -> CdpObject.requireList(element0, element1 -> ((Number) element1).doubleValue()));
        }
        /**
         * The starting index in characters, for this post layout textbox substring. Characters that would be represented as a surrogate pair in UTF-16 have length 2.
         * @return the protocol field value
         */
        public java.util.List<Long> start() {
            return CdpObject.requireList(require("start"), element0 -> ((Number) element0).longValue());
        }
        /**
         * The number of characters in this post layout textbox substring. Characters that would be represented as a surrogate pair in UTF-16 have length 2.
         * @return the protocol field value
         */
        public java.util.List<Long> length() {
            return CdpObject.requireList(require("length"), element0 -> ((Number) element0).longValue());
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
        /**
         * Returns a document snapshot, including the full DOM tree of the root node (including iframes, template contents, and imported documents) in a flattened array, as well as layout and white-listed computed style information for the nodes. Shadow DOM in the returned DOM tree is flattened.
         * @param computedStyleWhitelist protocol value
         * @deprecated Deprecated by the Chromium DevTools Protocol.
         */
        @Deprecated
        public GetSnapshotRequest(java.util.List<String> computedStyleWhitelist) {
            set("computedStyleWhitelist", computedStyleWhitelist);
        }
        public static GetSnapshotRequest fromMap(Map<String, Object> values) {
            GetSnapshotRequest instance_ = new GetSnapshotRequest();
            if (values != null) instance_.values.putAll(values);
            return instance_;
        }
        /**
         * Whitelist of computed styles to return.
         * @return the protocol field value
         */
        public java.util.List<String> computedStyleWhitelist() {
            return CdpObject.requireList(require("computedStyleWhitelist"), element0 -> (String) element0);
        }
        /**
         * Whether or not to retrieve details of DOM listeners (default false).
         * @return the protocol field value, empty when absent
         */
        public Optional<Boolean> includeEventListeners() {
            return Optional.ofNullable((Boolean) raw("includeEventListeners"));
        }
        /**
         * Whether to determine and include the paint order index of LayoutTreeNodes (default false).
         * @return the protocol field value, empty when absent
         */
        public Optional<Boolean> includePaintOrder() {
            return Optional.ofNullable((Boolean) raw("includePaintOrder"));
        }
        /**
         * Whether to include UA shadow tree in the snapshot (default false).
         * @return the protocol field value, empty when absent
         */
        public Optional<Boolean> includeUserAgentShadowTree() {
            return Optional.ofNullable((Boolean) raw("includeUserAgentShadowTree"));
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
         * @param includeEventListeners field value; empty omits the value
         * @return this model
         */
        public GetSnapshotRequest includeEventListeners(Optional<Boolean> includeEventListeners) {
            set("includeEventListeners", includeEventListeners.orElse(null));
            return this;
        }
        /**
         * Whether or not to retrieve details of DOM listeners (default false).
         * @param includeEventListeners field value; null removes the value
         * @return this model
         */
        public GetSnapshotRequest includeEventListeners(Boolean includeEventListeners) {
            set("includeEventListeners", includeEventListeners);
            return this;
        }
        /**
         * Whether to determine and include the paint order index of LayoutTreeNodes (default false).
         * @param includePaintOrder field value; empty omits the value
         * @return this model
         */
        public GetSnapshotRequest includePaintOrder(Optional<Boolean> includePaintOrder) {
            set("includePaintOrder", includePaintOrder.orElse(null));
            return this;
        }
        /**
         * Whether to determine and include the paint order index of LayoutTreeNodes (default false).
         * @param includePaintOrder field value; null removes the value
         * @return this model
         */
        public GetSnapshotRequest includePaintOrder(Boolean includePaintOrder) {
            set("includePaintOrder", includePaintOrder);
            return this;
        }
        /**
         * Whether to include UA shadow tree in the snapshot (default false).
         * @param includeUserAgentShadowTree field value; empty omits the value
         * @return this model
         */
        public GetSnapshotRequest includeUserAgentShadowTree(Optional<Boolean> includeUserAgentShadowTree) {
            set("includeUserAgentShadowTree", includeUserAgentShadowTree.orElse(null));
            return this;
        }
        /**
         * Whether to include UA shadow tree in the snapshot (default false).
         * @param includeUserAgentShadowTree field value; null removes the value
         * @return this model
         */
        public GetSnapshotRequest includeUserAgentShadowTree(Boolean includeUserAgentShadowTree) {
            set("includeUserAgentShadowTree", includeUserAgentShadowTree);
            return this;
        }
    }
    /**
     * Returns a document snapshot, including the full DOM tree of the root node (including iframes, template contents, and imported documents) in a flattened array, as well as layout and white-listed computed style information for the nodes. Shadow DOM in the returned DOM tree is flattened.
     */
    public static final class CaptureSnapshotRequest extends CdpObject {
        public CaptureSnapshotRequest() {}
        /**
         * Returns a document snapshot, including the full DOM tree of the root node (including iframes, template contents, and imported documents) in a flattened array, as well as layout and white-listed computed style information for the nodes. Shadow DOM in the returned DOM tree is flattened.
         * @param computedStyles protocol value
         */
        public CaptureSnapshotRequest(java.util.List<String> computedStyles) {
            set("computedStyles", computedStyles);
        }
        public static CaptureSnapshotRequest fromMap(Map<String, Object> values) {
            CaptureSnapshotRequest instance_ = new CaptureSnapshotRequest();
            if (values != null) instance_.values.putAll(values);
            return instance_;
        }
        /**
         * Whitelist of computed styles to return.
         * @return the protocol field value
         */
        public java.util.List<String> computedStyles() {
            return CdpObject.requireList(require("computedStyles"), element0 -> (String) element0);
        }
        /**
         * Whether to include layout object paint orders into the snapshot.
         * @return the protocol field value, empty when absent
         */
        public Optional<Boolean> includePaintOrder() {
            return Optional.ofNullable((Boolean) raw("includePaintOrder"));
        }
        /**
         * Whether to include DOM rectangles (offsetRects, clientRects, scrollRects) into the snapshot
         * @return the protocol field value, empty when absent
         */
        public Optional<Boolean> includeDOMRects() {
            return Optional.ofNullable((Boolean) raw("includeDOMRects"));
        }
        /**
         * Whether to include blended background colors in the snapshot (default: false). Blended background color is achieved by blending background colors of all elements that overlap with the current element.
         * <p><b>Experimental:</b> this part of CDP may change without notice.
         * @return the protocol field value, empty when absent
         */
        public Optional<Boolean> includeBlendedBackgroundColors() {
            return Optional.ofNullable((Boolean) raw("includeBlendedBackgroundColors"));
        }
        /**
         * Whether to include text color opacity in the snapshot (default: false). An element might have the opacity property set that affects the text color of the element. The final text color opacity is computed based on the opacity of all overlapping elements.
         * <p><b>Experimental:</b> this part of CDP may change without notice.
         * @return the protocol field value, empty when absent
         */
        public Optional<Boolean> includeTextColorOpacities() {
            return Optional.ofNullable((Boolean) raw("includeTextColorOpacities"));
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
         * @param includePaintOrder field value; empty omits the value
         * @return this model
         */
        public CaptureSnapshotRequest includePaintOrder(Optional<Boolean> includePaintOrder) {
            set("includePaintOrder", includePaintOrder.orElse(null));
            return this;
        }
        /**
         * Whether to include layout object paint orders into the snapshot.
         * @param includePaintOrder field value; null removes the value
         * @return this model
         */
        public CaptureSnapshotRequest includePaintOrder(Boolean includePaintOrder) {
            set("includePaintOrder", includePaintOrder);
            return this;
        }
        /**
         * Whether to include DOM rectangles (offsetRects, clientRects, scrollRects) into the snapshot
         * @param includeDOMRects field value; empty omits the value
         * @return this model
         */
        public CaptureSnapshotRequest includeDOMRects(Optional<Boolean> includeDOMRects) {
            set("includeDOMRects", includeDOMRects.orElse(null));
            return this;
        }
        /**
         * Whether to include DOM rectangles (offsetRects, clientRects, scrollRects) into the snapshot
         * @param includeDOMRects field value; null removes the value
         * @return this model
         */
        public CaptureSnapshotRequest includeDOMRects(Boolean includeDOMRects) {
            set("includeDOMRects", includeDOMRects);
            return this;
        }
        /**
         * Whether to include blended background colors in the snapshot (default: false). Blended background color is achieved by blending background colors of all elements that overlap with the current element.
         * <p><b>Experimental:</b> this part of CDP may change without notice.
         * @param includeBlendedBackgroundColors field value; empty omits the value
         * @return this model
         */
        public CaptureSnapshotRequest includeBlendedBackgroundColors(Optional<Boolean> includeBlendedBackgroundColors) {
            set("includeBlendedBackgroundColors", includeBlendedBackgroundColors.orElse(null));
            return this;
        }
        /**
         * Whether to include blended background colors in the snapshot (default: false). Blended background color is achieved by blending background colors of all elements that overlap with the current element.
         * <p><b>Experimental:</b> this part of CDP may change without notice.
         * @param includeBlendedBackgroundColors field value; null removes the value
         * @return this model
         */
        public CaptureSnapshotRequest includeBlendedBackgroundColors(Boolean includeBlendedBackgroundColors) {
            set("includeBlendedBackgroundColors", includeBlendedBackgroundColors);
            return this;
        }
        /**
         * Whether to include text color opacity in the snapshot (default: false). An element might have the opacity property set that affects the text color of the element. The final text color opacity is computed based on the opacity of all overlapping elements.
         * <p><b>Experimental:</b> this part of CDP may change without notice.
         * @param includeTextColorOpacities field value; empty omits the value
         * @return this model
         */
        public CaptureSnapshotRequest includeTextColorOpacities(Optional<Boolean> includeTextColorOpacities) {
            set("includeTextColorOpacities", includeTextColorOpacities.orElse(null));
            return this;
        }
        /**
         * Whether to include text color opacity in the snapshot (default: false). An element might have the opacity property set that affects the text color of the element. The final text color opacity is computed based on the opacity of all overlapping elements.
         * <p><b>Experimental:</b> this part of CDP may change without notice.
         * @param includeTextColorOpacities field value; null removes the value
         * @return this model
         */
        public CaptureSnapshotRequest includeTextColorOpacities(Boolean includeTextColorOpacities) {
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
            return CdpObject.requireList(require("domNodes"), element0 -> java.util.Objects.requireNonNull(DOMSnapshot.DOMNode.fromMap(java.util.Objects.requireNonNull(CdpObject.objectMap(element0)))));
        }
        /**
         * The nodes in the layout tree.
         * @return the protocol field value
         */
        public java.util.List<DOMSnapshot.LayoutTreeNode> layoutTreeNodes() {
            return CdpObject.requireList(require("layoutTreeNodes"), element0 -> java.util.Objects.requireNonNull(DOMSnapshot.LayoutTreeNode.fromMap(java.util.Objects.requireNonNull(CdpObject.objectMap(element0)))));
        }
        /**
         * Whitelisted ComputedStyle properties for each node in the layout tree.
         * @return the protocol field value
         */
        public java.util.List<DOMSnapshot.ComputedStyle> computedStyles() {
            return CdpObject.requireList(require("computedStyles"), element0 -> java.util.Objects.requireNonNull(DOMSnapshot.ComputedStyle.fromMap(java.util.Objects.requireNonNull(CdpObject.objectMap(element0)))));
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
            return CdpObject.requireList(require("documents"), element0 -> java.util.Objects.requireNonNull(DOMSnapshot.DocumentSnapshot.fromMap(java.util.Objects.requireNonNull(CdpObject.objectMap(element0)))));
        }
        /**
         * Shared string table that all string properties refer to with indexes.
         * @return the protocol field value
         */
        public java.util.List<String> strings() {
            return CdpObject.requireList(require("strings"), element0 -> (String) element0);
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
    public static final class Client {
        private final CdpClient client;
        public Client(CdpClient client) { this.client = client; }
        /**
         * Disables DOM snapshot agent for the given page.
         * @return a stage completing when the command completes
         */
        public CompletionStage<Void> disable() {
            return client.call("DOMSnapshot.disable", null, result_ -> null);
        }
        /**
         * Enables DOM snapshot agent for the given page.
         * @return a stage completing when the command completes
         */
        public CompletionStage<Void> enable() {
            return client.call("DOMSnapshot.enable", null, result_ -> null);
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
        public CompletionStage<GetSnapshotResult> getSnapshot(java.util.List<String> computedStyleWhitelist, Optional<Boolean> includeEventListeners, Optional<Boolean> includePaintOrder, Optional<Boolean> includeUserAgentShadowTree) {
            Map<String, Object> params = new LinkedHashMap<>();
            params.put("computedStyleWhitelist", CdpObject.json(computedStyleWhitelist));
            includeEventListeners.ifPresent(value_ -> params.put("includeEventListeners", value_));
            includePaintOrder.ifPresent(value_ -> params.put("includePaintOrder", value_));
            includeUserAgentShadowTree.ifPresent(value_ -> params.put("includeUserAgentShadowTree", value_));
            return client.call("DOMSnapshot.getSnapshot", params, result_ -> new GetSnapshotResult(result_));
        }
        /**
         * Returns a document snapshot, including the full DOM tree of the root node (including iframes, template contents, and imported documents) in a flattened array, as well as layout and white-listed computed style information for the nodes. Shadow DOM in the returned DOM tree is flattened.
         * @param computedStyleWhitelist protocol value
         * @return a stage completing with the command result
         * @deprecated Deprecated by the Chromium DevTools Protocol.
         */
        @Deprecated
        public CompletionStage<GetSnapshotResult> getSnapshot(java.util.List<String> computedStyleWhitelist) {
            return getSnapshot(computedStyleWhitelist, Optional.empty(), Optional.empty(), Optional.empty());
        }
        /**
         * Returns a document snapshot, including the full DOM tree of the root node (including iframes, template contents, and imported documents) in a flattened array, as well as layout and white-listed computed style information for the nodes. Shadow DOM in the returned DOM tree is flattened.
         * @param request request parameters
         * @return a stage completing with the command result
         * @deprecated Deprecated by the Chromium DevTools Protocol.
         */
        @Deprecated
        public CompletionStage<GetSnapshotResult> getSnapshot(GetSnapshotRequest request) {
            return client.call("DOMSnapshot.getSnapshot", request == null ? null : request.toMap(), result_ -> new GetSnapshotResult(result_));
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
        public CompletionStage<CaptureSnapshotResult> captureSnapshot(java.util.List<String> computedStyles, Optional<Boolean> includePaintOrder, Optional<Boolean> includeDOMRects, Optional<Boolean> includeBlendedBackgroundColors, Optional<Boolean> includeTextColorOpacities) {
            Map<String, Object> params = new LinkedHashMap<>();
            params.put("computedStyles", CdpObject.json(computedStyles));
            includePaintOrder.ifPresent(value_ -> params.put("includePaintOrder", value_));
            includeDOMRects.ifPresent(value_ -> params.put("includeDOMRects", value_));
            includeBlendedBackgroundColors.ifPresent(value_ -> params.put("includeBlendedBackgroundColors", value_));
            includeTextColorOpacities.ifPresent(value_ -> params.put("includeTextColorOpacities", value_));
            return client.call("DOMSnapshot.captureSnapshot", params, result_ -> new CaptureSnapshotResult(result_));
        }
        /**
         * Returns a document snapshot, including the full DOM tree of the root node (including iframes, template contents, and imported documents) in a flattened array, as well as layout and white-listed computed style information for the nodes. Shadow DOM in the returned DOM tree is flattened.
         * @param computedStyles protocol value
         * @return a stage completing with the command result
         */
        public CompletionStage<CaptureSnapshotResult> captureSnapshot(java.util.List<String> computedStyles) {
            return captureSnapshot(computedStyles, Optional.empty(), Optional.empty(), Optional.empty(), Optional.empty());
        }
        /**
         * Returns a document snapshot, including the full DOM tree of the root node (including iframes, template contents, and imported documents) in a flattened array, as well as layout and white-listed computed style information for the nodes. Shadow DOM in the returned DOM tree is flattened.
         * @param request request parameters
         * @return a stage completing with the command result
         */
        public CompletionStage<CaptureSnapshotResult> captureSnapshot(CaptureSnapshotRequest request) {
            return client.call("DOMSnapshot.captureSnapshot", request == null ? null : request.toMap(), result_ -> new CaptureSnapshotResult(result_));
        }
    }
}
