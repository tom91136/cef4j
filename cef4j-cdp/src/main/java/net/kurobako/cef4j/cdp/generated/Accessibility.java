// GENERATED - do not edit. Regenerate via: ./mvnw generate-sources -pl cef4j-cdp -Dcef.version=150.0.18+gdb11278+chromium-150.0.7871.213
package net.kurobako.cef4j.cdp.generated;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Optional;
import java.util.OptionalLong;
import java.util.concurrent.CompletionStage;
import java.util.function.Consumer;
import javax.annotation.processing.Generated;
import net.kurobako.cef4j.cdp.CdpClient;
import net.kurobako.cef4j.cdp.CdpObject;
import net.kurobako.cef4j.cdp.CdpSubscription;
import net.kurobako.cef4j.cdp.CdpValue;
import net.kurobako.cef4j.policy.NullableBoundary;
import org.jspecify.annotations.Nullable;

/**
 * Chrome DevTools Protocol Accessibility domain.
 * <p><b>Experimental:</b> this part of CDP may change without notice.
 * @see <a href="https://chromium.googlesource.com/chromium/src/+/refs/tags/150.0.7871.213/third_party/blink/public/devtools_protocol/domains/Accessibility.pdl">Pinned protocol source</a>
 */
@Generated("./mvnw generate-sources -pl cef4j-cdp -Dcef.version=150.0.18+gdb11278+chromium-150.0.7871.213")
@SuppressWarnings({"EscapedEntity", "InvalidParam", "JavaLangClash", "MissingSummary", "Unchecked", "UnusedMethod"})
@NullableBoundary("CDP wire objects use null for absent optional fields")
public final class Accessibility {
    private Accessibility() {}
    /**
     * Unique accessibility node identifier.
     */
    public static final class AXNodeId implements CdpValue<String> {
        public final String value;
        public AXNodeId(String value) { this.value = java.util.Objects.requireNonNull(value); }
        @Override public String value() { return value; }
        @Override public boolean equals(@org.jspecify.annotations.Nullable Object other) {
            if (this == other) return true;
            if (!(other instanceof AXNodeId)) return false;
            return value.equals(((AXNodeId) other).value);
        }
        @Override public int hashCode() { return value.hashCode(); }
        @Override public String toString() { return "AXNodeId(" + value + ")"; }
    }
    /**
     * Enum of possible property types.
     */
    public enum AXValueType implements CdpValue<String> {
        BOOLEAN("boolean"),
        TRISTATE("tristate"),
        BOOLEANORUNDEFINED("booleanOrUndefined"),
        IDREF("idref"),
        IDREFLIST("idrefList"),
        INTEGER("integer"),
        NODE("node"),
        NODELIST("nodeList"),
        NUMBER("number"),
        STRING("string"),
        COMPUTEDSTRING("computedString"),
        TOKEN("token"),
        TOKENLIST("tokenList"),
        DOMRELATION("domRelation"),
        ROLE("role"),
        INTERNALROLE("internalRole"),
        VALUEUNDEFINED("valueUndefined");
        public final String value;
        AXValueType(String value) { this.value = value; }
        @Override public String value() { return value; }
        public static AXValueType of(String value) {
            for (AXValueType constant : values()) {
                if (constant.value.equals(value)) return constant;
            }
            throw new IllegalArgumentException("Unknown AXValueType value: " + value);
        }
    }
    /**
     * Enum of possible property sources.
     */
    public enum AXValueSourceType implements CdpValue<String> {
        ATTRIBUTE("attribute"),
        IMPLICIT("implicit"),
        STYLE("style"),
        CONTENTS("contents"),
        PLACEHOLDER("placeholder"),
        RELATEDELEMENT("relatedElement");
        public final String value;
        AXValueSourceType(String value) { this.value = value; }
        @Override public String value() { return value; }
        public static AXValueSourceType of(String value) {
            for (AXValueSourceType constant : values()) {
                if (constant.value.equals(value)) return constant;
            }
            throw new IllegalArgumentException("Unknown AXValueSourceType value: " + value);
        }
    }
    /**
     * Enum of possible native property sources (as a subtype of a particular AXValueSourceType).
     */
    public enum AXValueNativeSourceType implements CdpValue<String> {
        DESCRIPTION("description"),
        FIGCAPTION("figcaption"),
        LABEL("label"),
        LABELFOR("labelfor"),
        LABELWRAPPED("labelwrapped"),
        LEGEND("legend"),
        RUBYANNOTATION("rubyannotation"),
        TABLECAPTION("tablecaption"),
        TITLE("title"),
        OTHER("other");
        public final String value;
        AXValueNativeSourceType(String value) { this.value = value; }
        @Override public String value() { return value; }
        public static AXValueNativeSourceType of(String value) {
            for (AXValueNativeSourceType constant : values()) {
                if (constant.value.equals(value)) return constant;
            }
            throw new IllegalArgumentException("Unknown AXValueNativeSourceType value: " + value);
        }
    }
    /**
     * A single source for a computed AX property.
     */
    public static final class AXValueSource extends CdpObject {
        public AXValueSource() {}
        private AXValueSource(Map<String, Object> values) { super(values); }
        public static AXValueSource fromMap(Map<String, Object> values) {
            return new AXValueSource(values);
        }
        /**
         * What type of source this is.
         * @return the protocol field value
         */
        public Accessibility.AXValueSourceType type() {
            return Accessibility.AXValueSourceType.of((String) require("type"));
        }
        /**
         * The value of this property source.
         * @return the protocol field value, empty when absent
         */
        public Optional<Accessibility.AXValue> value() {
            return raw("value").flatMap(CdpObject::objectMap).map(Accessibility.AXValue::fromMap);
        }
        /**
         * The name of the relevant attribute, if any.
         * @return the protocol field value, empty when absent
         */
        public Optional<String> attribute() {
            return raw("attribute").map(value -> (String) value);
        }
        /**
         * The value of the relevant attribute, if any.
         * @return the protocol field value, empty when absent
         */
        public Optional<Accessibility.AXValue> attributeValue() {
            return raw("attributeValue").flatMap(CdpObject::objectMap).map(Accessibility.AXValue::fromMap);
        }
        /**
         * Whether this source is superseded by a higher priority source.
         * @return the protocol field value, empty when absent
         */
        public Optional<Boolean> superseded() {
            return raw("superseded").map(value -> (Boolean) value);
        }
        /**
         * The native markup source for this value, e.g. a {@code &lt;label&gt;} element.
         * @return the protocol field value, empty when absent
         */
        public Optional<Accessibility.AXValueNativeSourceType> nativeSource() {
            return raw("nativeSource").map(value -> Accessibility.AXValueNativeSourceType.of((String) value));
        }
        /**
         * The value, such as a node or node list, of the native source.
         * @return the protocol field value, empty when absent
         */
        public Optional<Accessibility.AXValue> nativeSourceValue() {
            return raw("nativeSourceValue").flatMap(CdpObject::objectMap).map(Accessibility.AXValue::fromMap);
        }
        /**
         * Whether the value for this property is invalid.
         * @return the protocol field value, empty when absent
         */
        public Optional<Boolean> invalid() {
            return raw("invalid").map(value -> (Boolean) value);
        }
        /**
         * Reason for the value being invalid, if it is.
         * @return the protocol field value, empty when absent
         */
        public Optional<String> invalidReason() {
            return raw("invalidReason").map(value -> (String) value);
        }
        /**
         * What type of source this is.
         * @param type field value
         * @return this model
         */
        public AXValueSource type(Accessibility.AXValueSourceType type) {
            set("type", type);
            return this;
        }
        /**
         * The value of this property source.
         * @param value field value
         * @return this model
         */
        public AXValueSource value(Accessibility.AXValue value) {
            set("value", value);
            return this;
        }
        /**
         * The name of the relevant attribute, if any.
         * @param attribute field value
         * @return this model
         */
        public AXValueSource attribute(String attribute) {
            set("attribute", attribute);
            return this;
        }
        /**
         * The value of the relevant attribute, if any.
         * @param attributeValue field value
         * @return this model
         */
        public AXValueSource attributeValue(Accessibility.AXValue attributeValue) {
            set("attributeValue", attributeValue);
            return this;
        }
        /**
         * Whether this source is superseded by a higher priority source.
         * @param superseded field value
         * @return this model
         */
        public AXValueSource superseded(boolean superseded) {
            set("superseded", superseded);
            return this;
        }
        /**
         * The native markup source for this value, e.g. a {@code &lt;label&gt;} element.
         * @param nativeSource field value
         * @return this model
         */
        public AXValueSource nativeSource(Accessibility.AXValueNativeSourceType nativeSource) {
            set("nativeSource", nativeSource);
            return this;
        }
        /**
         * The value, such as a node or node list, of the native source.
         * @param nativeSourceValue field value
         * @return this model
         */
        public AXValueSource nativeSourceValue(Accessibility.AXValue nativeSourceValue) {
            set("nativeSourceValue", nativeSourceValue);
            return this;
        }
        /**
         * Whether the value for this property is invalid.
         * @param invalid field value
         * @return this model
         */
        public AXValueSource invalid(boolean invalid) {
            set("invalid", invalid);
            return this;
        }
        /**
         * Reason for the value being invalid, if it is.
         * @param invalidReason field value
         * @return this model
         */
        public AXValueSource invalidReason(String invalidReason) {
            set("invalidReason", invalidReason);
            return this;
        }
    }
    /**
     */
    public static final class AXRelatedNode extends CdpObject {
        public AXRelatedNode() {}
        private AXRelatedNode(Map<String, Object> values) { super(values); }
        public static AXRelatedNode fromMap(Map<String, Object> values) {
            return new AXRelatedNode(values);
        }
        /**
         * The BackendNodeId of the related DOM node.
         * @return the protocol field value
         */
        public DOM.BackendNodeId backendDOMNodeId() {
            return new DOM.BackendNodeId(((Number) require("backendDOMNodeId")).longValue());
        }
        /**
         * The IDRef value provided, if any.
         * @return the protocol field value, empty when absent
         */
        public Optional<String> idref() {
            return raw("idref").map(value -> (String) value);
        }
        /**
         * The text alternative of this node in the current context.
         * @return the protocol field value, empty when absent
         */
        public Optional<String> text() {
            return raw("text").map(value -> (String) value);
        }
        /**
         * The BackendNodeId of the related DOM node.
         * @param backendDOMNodeId field value
         * @return this model
         */
        public AXRelatedNode backendDOMNodeId(DOM.BackendNodeId backendDOMNodeId) {
            set("backendDOMNodeId", backendDOMNodeId);
            return this;
        }
        /**
         * The IDRef value provided, if any.
         * @param idref field value
         * @return this model
         */
        public AXRelatedNode idref(String idref) {
            set("idref", idref);
            return this;
        }
        /**
         * The text alternative of this node in the current context.
         * @param text field value
         * @return this model
         */
        public AXRelatedNode text(String text) {
            set("text", text);
            return this;
        }
    }
    /**
     */
    public static final class AXProperty extends CdpObject {
        public AXProperty() {}
        private AXProperty(Map<String, Object> values) { super(values); }
        public static AXProperty fromMap(Map<String, Object> values) {
            return new AXProperty(values);
        }
        /**
         * The name of this property.
         * @return the protocol field value
         */
        public Accessibility.AXPropertyName name() {
            return Accessibility.AXPropertyName.of((String) require("name"));
        }
        /**
         * The value of this property.
         * @return the protocol field value
         */
        public Accessibility.AXValue value() {
            return java.util.Objects.requireNonNull(Accessibility.AXValue.fromMap(CdpObject.requireObjectMap(require("value"))));
        }
        /**
         * The name of this property.
         * @param name field value
         * @return this model
         */
        public AXProperty name(Accessibility.AXPropertyName name) {
            set("name", name);
            return this;
        }
        /**
         * The value of this property.
         * @param value field value
         * @return this model
         */
        public AXProperty value(Accessibility.AXValue value) {
            set("value", value);
            return this;
        }
    }
    /**
     * A single computed AX property.
     */
    public static final class AXValue extends CdpObject {
        public AXValue() {}
        private AXValue(Map<String, Object> values) { super(values); }
        public static AXValue fromMap(Map<String, Object> values) {
            return new AXValue(values);
        }
        /**
         * The type of this value.
         * @return the protocol field value
         */
        public Accessibility.AXValueType type() {
            return Accessibility.AXValueType.of((String) require("type"));
        }
        /**
         * The computed value of this property.
         * @return the protocol field value, empty when absent
         */
        public Optional<Object> value() {
            return raw("value");
        }
        /**
         * One or more related nodes, if applicable.
         * @return the protocol field value, empty when absent
         */
        public Optional<java.util.List<Accessibility.AXRelatedNode>> relatedNodes() {
            return raw("relatedNodes").flatMap(value -> list(value, element0 -> java.util.Objects.requireNonNull(Accessibility.AXRelatedNode.fromMap(CdpObject.requireObjectMap(element0)))));
        }
        /**
         * The sources which contributed to the computation of this property.
         * @return the protocol field value, empty when absent
         */
        public Optional<java.util.List<Accessibility.AXValueSource>> sources() {
            return raw("sources").flatMap(value -> list(value, element0 -> java.util.Objects.requireNonNull(Accessibility.AXValueSource.fromMap(CdpObject.requireObjectMap(element0)))));
        }
        /**
         * The type of this value.
         * @param type field value
         * @return this model
         */
        public AXValue type(Accessibility.AXValueType type) {
            set("type", type);
            return this;
        }
        /**
         * The computed value of this property.
         * @param value field value
         * @return this model
         */
        public AXValue value(Object value) {
            set("value", value);
            return this;
        }
        /**
         * One or more related nodes, if applicable.
         * @param relatedNodes field value
         * @return this model
         */
        public AXValue relatedNodes(java.util.List<Accessibility.AXRelatedNode> relatedNodes) {
            set("relatedNodes", relatedNodes);
            return this;
        }
        /**
         * The sources which contributed to the computation of this property.
         * @param sources field value
         * @return this model
         */
        public AXValue sources(java.util.List<Accessibility.AXValueSource> sources) {
            set("sources", sources);
            return this;
        }
    }
    /**
     * Values of AXProperty name: - from &#x27;busy&#x27; to &#x27;roledescription&#x27;: states which apply to every AX node - from &#x27;live&#x27; to &#x27;root&#x27;: attributes which apply to nodes in live regions - from &#x27;autocomplete&#x27; to &#x27;valuetext&#x27;: attributes which apply to widgets - from &#x27;checked&#x27; to &#x27;selected&#x27;: states which apply to widgets - from &#x27;activedescendant&#x27; to &#x27;owns&#x27;: relationships between elements other than parent/child/sibling - from &#x27;activeFullscreenElement&#x27; to &#x27;uninteresting&#x27;: reasons why this noode is hidden
     */
    public enum AXPropertyName implements CdpValue<String> {
        ACTIONS("actions"),
        BUSY("busy"),
        DISABLED("disabled"),
        EDITABLE("editable"),
        FOCUSABLE("focusable"),
        FOCUSED("focused"),
        HIDDEN("hidden"),
        HIDDENROOT("hiddenRoot"),
        INVALID("invalid"),
        KEYSHORTCUTS("keyshortcuts"),
        SETTABLE("settable"),
        ROLEDESCRIPTION("roledescription"),
        LIVE("live"),
        ATOMIC("atomic"),
        RELEVANT("relevant"),
        ROOT("root"),
        AUTOCOMPLETE("autocomplete"),
        HASPOPUP("hasPopup"),
        LEVEL("level"),
        MULTISELECTABLE("multiselectable"),
        ORIENTATION("orientation"),
        MULTILINE("multiline"),
        READONLY("readonly"),
        REQUIRED("required"),
        VALUEMIN("valuemin"),
        VALUEMAX("valuemax"),
        VALUETEXT("valuetext"),
        CHECKED("checked"),
        EXPANDED("expanded"),
        MODAL("modal"),
        PRESSED("pressed"),
        SELECTED("selected"),
        ACTIVEDESCENDANT("activedescendant"),
        CONTROLS("controls"),
        DESCRIBEDBY("describedby"),
        DETAILS("details"),
        ERRORMESSAGE("errormessage"),
        FLOWTO("flowto"),
        LABELLEDBY("labelledby"),
        OWNS("owns"),
        URL("url"),
        ACTIVEFULLSCREENELEMENT("activeFullscreenElement"),
        ACTIVEMODALDIALOG("activeModalDialog"),
        ACTIVEARIAMODALDIALOG("activeAriaModalDialog"),
        ARIAHIDDENELEMENT("ariaHiddenElement"),
        ARIAHIDDENSUBTREE("ariaHiddenSubtree"),
        EMPTYALT("emptyAlt"),
        EMPTYTEXT("emptyText"),
        INERTELEMENT("inertElement"),
        INERTSUBTREE("inertSubtree"),
        LABELCONTAINER("labelContainer"),
        LABELFOR("labelFor"),
        NOTRENDERED("notRendered"),
        NOTVISIBLE("notVisible"),
        PRESENTATIONALROLE("presentationalRole"),
        PROBABLYPRESENTATIONAL("probablyPresentational"),
        INACTIVECAROUSELTABCONTENT("inactiveCarouselTabContent"),
        UNINTERESTING("uninteresting");
        public final String value;
        AXPropertyName(String value) { this.value = value; }
        @Override public String value() { return value; }
        public static AXPropertyName of(String value) {
            for (AXPropertyName constant : values()) {
                if (constant.value.equals(value)) return constant;
            }
            throw new IllegalArgumentException("Unknown AXPropertyName value: " + value);
        }
    }
    /**
     * A node in the accessibility tree.
     */
    public static final class AXNode extends CdpObject {
        public AXNode() {}
        private AXNode(Map<String, Object> values) { super(values); }
        public static AXNode fromMap(Map<String, Object> values) {
            return new AXNode(values);
        }
        /**
         * Unique identifier for this node.
         * @return the protocol field value
         */
        public Accessibility.AXNodeId nodeId() {
            return new Accessibility.AXNodeId((String) require("nodeId"));
        }
        /**
         * Whether this node is ignored for accessibility
         * @return the protocol field value
         */
        public boolean ignored() {
            return (Boolean) require("ignored");
        }
        /**
         * Collection of reasons why this node is hidden.
         * @return the protocol field value, empty when absent
         */
        public Optional<java.util.List<Accessibility.AXProperty>> ignoredReasons() {
            return raw("ignoredReasons").flatMap(value -> list(value, element0 -> java.util.Objects.requireNonNull(Accessibility.AXProperty.fromMap(CdpObject.requireObjectMap(element0)))));
        }
        /**
         * This {@code Node}&#x27;s role, whether explicit or implicit.
         * @return the protocol field value, empty when absent
         */
        public Optional<Accessibility.AXValue> role() {
            return raw("role").flatMap(CdpObject::objectMap).map(Accessibility.AXValue::fromMap);
        }
        /**
         * This {@code Node}&#x27;s Chrome raw role.
         * @return the protocol field value, empty when absent
         */
        public Optional<Accessibility.AXValue> chromeRole() {
            return raw("chromeRole").flatMap(CdpObject::objectMap).map(Accessibility.AXValue::fromMap);
        }
        /**
         * The accessible name for this {@code Node}.
         * @return the protocol field value, empty when absent
         */
        public Optional<Accessibility.AXValue> name() {
            return raw("name").flatMap(CdpObject::objectMap).map(Accessibility.AXValue::fromMap);
        }
        /**
         * The accessible description for this {@code Node}.
         * @return the protocol field value, empty when absent
         */
        public Optional<Accessibility.AXValue> description() {
            return raw("description").flatMap(CdpObject::objectMap).map(Accessibility.AXValue::fromMap);
        }
        /**
         * The value for this {@code Node}.
         * @return the protocol field value, empty when absent
         */
        public Optional<Accessibility.AXValue> value() {
            return raw("value").flatMap(CdpObject::objectMap).map(Accessibility.AXValue::fromMap);
        }
        /**
         * All other properties
         * @return the protocol field value, empty when absent
         */
        public Optional<java.util.List<Accessibility.AXProperty>> properties() {
            return raw("properties").flatMap(value -> list(value, element0 -> java.util.Objects.requireNonNull(Accessibility.AXProperty.fromMap(CdpObject.requireObjectMap(element0)))));
        }
        /**
         * ID for this node&#x27;s parent.
         * @return the protocol field value, empty when absent
         */
        public Optional<Accessibility.AXNodeId> parentId() {
            return raw("parentId").map(value -> new Accessibility.AXNodeId((String) value));
        }
        /**
         * IDs for each of this node&#x27;s child nodes.
         * @return the protocol field value, empty when absent
         */
        public Optional<java.util.List<Accessibility.AXNodeId>> childIds() {
            return raw("childIds").flatMap(value -> list(value, element0 -> new Accessibility.AXNodeId((String) element0)));
        }
        /**
         * The backend ID for the associated DOM node, if any.
         * @return the protocol field value, empty when absent
         */
        public Optional<DOM.BackendNodeId> backendDOMNodeId() {
            return raw("backendDOMNodeId").map(value -> new DOM.BackendNodeId(((Number) value).longValue()));
        }
        /**
         * The frame ID for the frame associated with this nodes document.
         * @return the protocol field value, empty when absent
         */
        public Optional<Page.FrameId> frameId() {
            return raw("frameId").map(value -> new Page.FrameId((String) value));
        }
        /**
         * Unique identifier for this node.
         * @param nodeId field value
         * @return this model
         */
        public AXNode nodeId(Accessibility.AXNodeId nodeId) {
            set("nodeId", nodeId);
            return this;
        }
        /**
         * Whether this node is ignored for accessibility
         * @param ignored field value
         * @return this model
         */
        public AXNode ignored(boolean ignored) {
            set("ignored", ignored);
            return this;
        }
        /**
         * Collection of reasons why this node is hidden.
         * @param ignoredReasons field value
         * @return this model
         */
        public AXNode ignoredReasons(java.util.List<Accessibility.AXProperty> ignoredReasons) {
            set("ignoredReasons", ignoredReasons);
            return this;
        }
        /**
         * This {@code Node}&#x27;s role, whether explicit or implicit.
         * @param role field value
         * @return this model
         */
        public AXNode role(Accessibility.AXValue role) {
            set("role", role);
            return this;
        }
        /**
         * This {@code Node}&#x27;s Chrome raw role.
         * @param chromeRole field value
         * @return this model
         */
        public AXNode chromeRole(Accessibility.AXValue chromeRole) {
            set("chromeRole", chromeRole);
            return this;
        }
        /**
         * The accessible name for this {@code Node}.
         * @param name field value
         * @return this model
         */
        public AXNode name(Accessibility.AXValue name) {
            set("name", name);
            return this;
        }
        /**
         * The accessible description for this {@code Node}.
         * @param description field value
         * @return this model
         */
        public AXNode description(Accessibility.AXValue description) {
            set("description", description);
            return this;
        }
        /**
         * The value for this {@code Node}.
         * @param value field value
         * @return this model
         */
        public AXNode value(Accessibility.AXValue value) {
            set("value", value);
            return this;
        }
        /**
         * All other properties
         * @param properties field value
         * @return this model
         */
        public AXNode properties(java.util.List<Accessibility.AXProperty> properties) {
            set("properties", properties);
            return this;
        }
        /**
         * ID for this node&#x27;s parent.
         * @param parentId field value
         * @return this model
         */
        public AXNode parentId(Accessibility.AXNodeId parentId) {
            set("parentId", parentId);
            return this;
        }
        /**
         * IDs for each of this node&#x27;s child nodes.
         * @param childIds field value
         * @return this model
         */
        public AXNode childIds(java.util.List<Accessibility.AXNodeId> childIds) {
            set("childIds", childIds);
            return this;
        }
        /**
         * The backend ID for the associated DOM node, if any.
         * @param backendDOMNodeId field value
         * @return this model
         */
        public AXNode backendDOMNodeId(DOM.BackendNodeId backendDOMNodeId) {
            set("backendDOMNodeId", backendDOMNodeId);
            return this;
        }
        /**
         * The frame ID for the frame associated with this nodes document.
         * @param frameId field value
         * @return this model
         */
        public AXNode frameId(Page.FrameId frameId) {
            set("frameId", frameId);
            return this;
        }
    }
    /**
     * Fetches the accessibility node and partial accessibility tree for this DOM node, if it exists.
     * <p><b>Experimental:</b> this part of CDP may change without notice.
     */
    public static final class GetPartialAXTreeRequest extends CdpObject {
        public GetPartialAXTreeRequest() {}
        public static GetPartialAXTreeRequest fromMap(Map<String, Object> values) {
            final GetPartialAXTreeRequest instance_ = new GetPartialAXTreeRequest();
            if (values != null) instance_.values.putAll(values);
            return instance_;
        }
        /**
         * Identifier of the node to get the partial accessibility tree for.
         * @return the protocol field value, empty when absent
         */
        public Optional<DOM.NodeId> nodeId() {
            return raw("nodeId").map(value -> new DOM.NodeId(((Number) value).longValue()));
        }
        /**
         * Identifier of the backend node to get the partial accessibility tree for.
         * @return the protocol field value, empty when absent
         */
        public Optional<DOM.BackendNodeId> backendNodeId() {
            return raw("backendNodeId").map(value -> new DOM.BackendNodeId(((Number) value).longValue()));
        }
        /**
         * JavaScript object id of the node wrapper to get the partial accessibility tree for.
         * @return the protocol field value, empty when absent
         */
        public Optional<Runtime.RemoteObjectId> objectId() {
            return raw("objectId").map(value -> new Runtime.RemoteObjectId((String) value));
        }
        /**
         * Whether to fetch this node&#x27;s ancestors, siblings and children. Defaults to true.
         * @return the protocol field value, empty when absent
         */
        public Optional<Boolean> fetchRelatives() {
            return raw("fetchRelatives").map(value -> (Boolean) value);
        }
        /**
         * Identifier of the node to get the partial accessibility tree for.
         * @param nodeId field value
         * @return this model
         */
        public GetPartialAXTreeRequest nodeId(DOM.NodeId nodeId) {
            set("nodeId", nodeId);
            return this;
        }
        /**
         * Identifier of the backend node to get the partial accessibility tree for.
         * @param backendNodeId field value
         * @return this model
         */
        public GetPartialAXTreeRequest backendNodeId(DOM.BackendNodeId backendNodeId) {
            set("backendNodeId", backendNodeId);
            return this;
        }
        /**
         * JavaScript object id of the node wrapper to get the partial accessibility tree for.
         * @param objectId field value
         * @return this model
         */
        public GetPartialAXTreeRequest objectId(Runtime.RemoteObjectId objectId) {
            set("objectId", objectId);
            return this;
        }
        /**
         * Whether to fetch this node&#x27;s ancestors, siblings and children. Defaults to true.
         * @param fetchRelatives field value
         * @return this model
         */
        public GetPartialAXTreeRequest fetchRelatives(boolean fetchRelatives) {
            set("fetchRelatives", fetchRelatives);
            return this;
        }
    }
    /**
     * Fetches the entire accessibility tree for the root Document
     * <p><b>Experimental:</b> this part of CDP may change without notice.
     */
    public static final class GetFullAXTreeRequest extends CdpObject {
        public GetFullAXTreeRequest() {}
        public static GetFullAXTreeRequest fromMap(Map<String, Object> values) {
            final GetFullAXTreeRequest instance_ = new GetFullAXTreeRequest();
            if (values != null) instance_.values.putAll(values);
            return instance_;
        }
        /**
         * The maximum depth at which descendants of the root node should be retrieved. If omitted, the full tree is returned.
         * @return the protocol field value, empty when absent
         */
        public OptionalLong depth() {
            return raw("depth").flatMap(CdpObject::numberAsLong).stream().mapToLong(Long::longValue).findFirst();
        }
        /**
         * The frame for whose document the AX tree should be retrieved. If omitted, the root frame is used.
         * @return the protocol field value, empty when absent
         */
        public Optional<Page.FrameId> frameId() {
            return raw("frameId").map(value -> new Page.FrameId((String) value));
        }
        /**
         * The maximum depth at which descendants of the root node should be retrieved. If omitted, the full tree is returned.
         * @param depth field value
         * @return this model
         */
        public GetFullAXTreeRequest depth(long depth) {
            set("depth", depth);
            return this;
        }
        /**
         * The frame for whose document the AX tree should be retrieved. If omitted, the root frame is used.
         * @param frameId field value
         * @return this model
         */
        public GetFullAXTreeRequest frameId(Page.FrameId frameId) {
            set("frameId", frameId);
            return this;
        }
    }
    /**
     * Fetches the root node. Requires {@code enable()} to have been called previously.
     * <p><b>Experimental:</b> this part of CDP may change without notice.
     */
    public static final class GetRootAXNodeRequest extends CdpObject {
        public GetRootAXNodeRequest() {}
        public static GetRootAXNodeRequest fromMap(Map<String, Object> values) {
            final GetRootAXNodeRequest instance_ = new GetRootAXNodeRequest();
            if (values != null) instance_.values.putAll(values);
            return instance_;
        }
        /**
         * The frame in whose document the node resides. If omitted, the root frame is used.
         * @return the protocol field value, empty when absent
         */
        public Optional<Page.FrameId> frameId() {
            return raw("frameId").map(value -> new Page.FrameId((String) value));
        }
        /**
         * The frame in whose document the node resides. If omitted, the root frame is used.
         * @param frameId field value
         * @return this model
         */
        public GetRootAXNodeRequest frameId(Page.FrameId frameId) {
            set("frameId", frameId);
            return this;
        }
    }
    /**
     * Fetches a node and all ancestors up to and including the root. Requires {@code enable()} to have been called previously.
     * <p><b>Experimental:</b> this part of CDP may change without notice.
     */
    public static final class GetAXNodeAndAncestorsRequest extends CdpObject {
        public GetAXNodeAndAncestorsRequest() {}
        public static GetAXNodeAndAncestorsRequest fromMap(Map<String, Object> values) {
            final GetAXNodeAndAncestorsRequest instance_ = new GetAXNodeAndAncestorsRequest();
            if (values != null) instance_.values.putAll(values);
            return instance_;
        }
        /**
         * Identifier of the node to get.
         * @return the protocol field value, empty when absent
         */
        public Optional<DOM.NodeId> nodeId() {
            return raw("nodeId").map(value -> new DOM.NodeId(((Number) value).longValue()));
        }
        /**
         * Identifier of the backend node to get.
         * @return the protocol field value, empty when absent
         */
        public Optional<DOM.BackendNodeId> backendNodeId() {
            return raw("backendNodeId").map(value -> new DOM.BackendNodeId(((Number) value).longValue()));
        }
        /**
         * JavaScript object id of the node wrapper to get.
         * @return the protocol field value, empty when absent
         */
        public Optional<Runtime.RemoteObjectId> objectId() {
            return raw("objectId").map(value -> new Runtime.RemoteObjectId((String) value));
        }
        /**
         * Identifier of the node to get.
         * @param nodeId field value
         * @return this model
         */
        public GetAXNodeAndAncestorsRequest nodeId(DOM.NodeId nodeId) {
            set("nodeId", nodeId);
            return this;
        }
        /**
         * Identifier of the backend node to get.
         * @param backendNodeId field value
         * @return this model
         */
        public GetAXNodeAndAncestorsRequest backendNodeId(DOM.BackendNodeId backendNodeId) {
            set("backendNodeId", backendNodeId);
            return this;
        }
        /**
         * JavaScript object id of the node wrapper to get.
         * @param objectId field value
         * @return this model
         */
        public GetAXNodeAndAncestorsRequest objectId(Runtime.RemoteObjectId objectId) {
            set("objectId", objectId);
            return this;
        }
    }
    /**
     * Fetches a particular accessibility node by AXNodeId. Requires {@code enable()} to have been called previously.
     * <p><b>Experimental:</b> this part of CDP may change without notice.
     */
    public static final class GetChildAXNodesRequest extends CdpObject {
        public GetChildAXNodesRequest() {}
        /**
         * Fetches a particular accessibility node by AXNodeId. Requires {@code enable()} to have been called previously.
         * <p><b>Experimental:</b> this part of CDP may change without notice.
         * @param id protocol value
         */
        public GetChildAXNodesRequest(Accessibility.AXNodeId id) {
            id(id);
        }
        public static GetChildAXNodesRequest fromMap(Map<String, Object> values) {
            final GetChildAXNodesRequest instance_ = new GetChildAXNodesRequest();
            if (values != null) instance_.values.putAll(values);
            return instance_;
        }
        /**
         * Returns the id field.
         * @return the protocol field value
         */
        public Accessibility.AXNodeId id() {
            return new Accessibility.AXNodeId((String) require("id"));
        }
        /**
         * The frame in whose document the node resides. If omitted, the root frame is used.
         * @return the protocol field value, empty when absent
         */
        public Optional<Page.FrameId> frameId() {
            return raw("frameId").map(value -> new Page.FrameId((String) value));
        }
        /**
         * Sets the id field.
         * @param id field value
         * @return this model
         */
        public GetChildAXNodesRequest id(Accessibility.AXNodeId id) {
            set("id", id);
            return this;
        }
        /**
         * The frame in whose document the node resides. If omitted, the root frame is used.
         * @param frameId field value
         * @return this model
         */
        public GetChildAXNodesRequest frameId(Page.FrameId frameId) {
            set("frameId", frameId);
            return this;
        }
    }
    /**
     * Query a DOM node&#x27;s accessibility subtree for accessible name and role. This command computes the name and role for all nodes in the subtree, including those that are ignored for accessibility, and returns those that match the specified name and role. If no DOM node is specified, or the DOM node does not exist, the command returns an error. If neither {@code accessibleName} or {@code role} is specified, it returns all the accessibility nodes in the subtree.
     * <p><b>Experimental:</b> this part of CDP may change without notice.
     */
    public static final class QueryAXTreeRequest extends CdpObject {
        public QueryAXTreeRequest() {}
        public static QueryAXTreeRequest fromMap(Map<String, Object> values) {
            final QueryAXTreeRequest instance_ = new QueryAXTreeRequest();
            if (values != null) instance_.values.putAll(values);
            return instance_;
        }
        /**
         * Identifier of the node for the root to query.
         * @return the protocol field value, empty when absent
         */
        public Optional<DOM.NodeId> nodeId() {
            return raw("nodeId").map(value -> new DOM.NodeId(((Number) value).longValue()));
        }
        /**
         * Identifier of the backend node for the root to query.
         * @return the protocol field value, empty when absent
         */
        public Optional<DOM.BackendNodeId> backendNodeId() {
            return raw("backendNodeId").map(value -> new DOM.BackendNodeId(((Number) value).longValue()));
        }
        /**
         * JavaScript object id of the node wrapper for the root to query.
         * @return the protocol field value, empty when absent
         */
        public Optional<Runtime.RemoteObjectId> objectId() {
            return raw("objectId").map(value -> new Runtime.RemoteObjectId((String) value));
        }
        /**
         * Find nodes with this computed name.
         * @return the protocol field value, empty when absent
         */
        public Optional<String> accessibleName() {
            return raw("accessibleName").map(value -> (String) value);
        }
        /**
         * Find nodes with this computed role.
         * @return the protocol field value, empty when absent
         */
        public Optional<String> role() {
            return raw("role").map(value -> (String) value);
        }
        /**
         * Identifier of the node for the root to query.
         * @param nodeId field value
         * @return this model
         */
        public QueryAXTreeRequest nodeId(DOM.NodeId nodeId) {
            set("nodeId", nodeId);
            return this;
        }
        /**
         * Identifier of the backend node for the root to query.
         * @param backendNodeId field value
         * @return this model
         */
        public QueryAXTreeRequest backendNodeId(DOM.BackendNodeId backendNodeId) {
            set("backendNodeId", backendNodeId);
            return this;
        }
        /**
         * JavaScript object id of the node wrapper for the root to query.
         * @param objectId field value
         * @return this model
         */
        public QueryAXTreeRequest objectId(Runtime.RemoteObjectId objectId) {
            set("objectId", objectId);
            return this;
        }
        /**
         * Find nodes with this computed name.
         * @param accessibleName field value
         * @return this model
         */
        public QueryAXTreeRequest accessibleName(String accessibleName) {
            set("accessibleName", accessibleName);
            return this;
        }
        /**
         * Find nodes with this computed role.
         * @param role field value
         * @return this model
         */
        public QueryAXTreeRequest role(String role) {
            set("role", role);
            return this;
        }
    }
    /**
     * The loadComplete event mirrors the load complete event sent by the browser to assistive technology when the web page has finished loading.
     * <p><b>Experimental:</b> this part of CDP may change without notice.
     */
    public static final class LoadCompleteEvent extends CdpObject {
        public LoadCompleteEvent() {}
        private LoadCompleteEvent(Map<String, Object> values) { super(values); }
        public static LoadCompleteEvent fromMap(Map<String, Object> values) {
            return new LoadCompleteEvent(values);
        }
        /**
         * New document root node.
         * @return the protocol field value
         */
        public Accessibility.AXNode root() {
            return java.util.Objects.requireNonNull(Accessibility.AXNode.fromMap(CdpObject.requireObjectMap(require("root"))));
        }
        /**
         * New document root node.
         * @param root field value
         * @return this model
         */
        public LoadCompleteEvent root(Accessibility.AXNode root) {
            set("root", root);
            return this;
        }
    }
    /**
     * The nodesUpdated event is sent every time a previously requested node has changed the in tree.
     * <p><b>Experimental:</b> this part of CDP may change without notice.
     */
    public static final class NodesUpdatedEvent extends CdpObject {
        public NodesUpdatedEvent() {}
        private NodesUpdatedEvent(Map<String, Object> values) { super(values); }
        public static NodesUpdatedEvent fromMap(Map<String, Object> values) {
            return new NodesUpdatedEvent(values);
        }
        /**
         * Updated node data.
         * @return the protocol field value
         */
        public java.util.List<Accessibility.AXNode> nodes() {
            return CdpObject.requireList(require("nodes"), element0 -> java.util.Objects.requireNonNull(Accessibility.AXNode.fromMap(CdpObject.requireObjectMap(element0))));
        }
        /**
         * Updated node data.
         * @param nodes field value
         * @return this model
         */
        public NodesUpdatedEvent nodes(java.util.List<Accessibility.AXNode> nodes) {
            set("nodes", nodes);
            return this;
        }
    }
    public static final class Client {
        private final CdpClient client;
        public Client(CdpClient client) { this.client = client; }
        /**
         * Disables the accessibility domain.
         * @return a stage completing when the command completes
         */
        public CompletionStage<Void> disable() {
            return client.call("Accessibility.disable", null);
        }
        /**
         * Enables the accessibility domain which causes {@code AXNodeId}s to remain consistent between method calls. This turns on accessibility for the page, which can impact performance until accessibility is disabled.
         * @return a stage completing when the command completes
         */
        public CompletionStage<Void> enable() {
            return client.call("Accessibility.enable", null);
        }
        /**
         * Fetches the accessibility node and partial accessibility tree for this DOM node, if it exists.
         * <p><b>Experimental:</b> this part of CDP may change without notice.
         * @param nodeId protocol value
         * @param backendNodeId protocol value
         * @param objectId protocol value
         * @param fetchRelatives protocol value
         * @return a stage completing with the command result
         */
        public CompletionStage<java.util.List<Accessibility.AXNode>> getPartialAXTree(DOM.@Nullable NodeId nodeId, DOM.@Nullable BackendNodeId backendNodeId, Runtime.@Nullable RemoteObjectId objectId, @Nullable Boolean fetchRelatives) {
            final Map<String, Object> params = new LinkedHashMap<>();
            if (nodeId != null) params.put("nodeId", CdpObject.json(nodeId));
            if (backendNodeId != null) params.put("backendNodeId", CdpObject.json(backendNodeId));
            if (objectId != null) params.put("objectId", CdpObject.json(objectId));
            if (fetchRelatives != null) params.put("fetchRelatives", CdpObject.json(fetchRelatives));
            return client.call("Accessibility.getPartialAXTree", params, result_ -> CdpObject.requireList(java.util.Objects.requireNonNull(result_.get("nodes")), element0 -> java.util.Objects.requireNonNull(Accessibility.AXNode.fromMap(CdpObject.requireObjectMap(element0)))));
        }
        /**
         * Fetches the accessibility node and partial accessibility tree for this DOM node, if it exists.
         * <p><b>Experimental:</b> this part of CDP may change without notice.
         * @return a stage completing with the command result
         */
        public CompletionStage<java.util.List<Accessibility.AXNode>> getPartialAXTree() {
            return client.call("Accessibility.getPartialAXTree", null, result_ -> CdpObject.requireList(java.util.Objects.requireNonNull(result_.get("nodes")), element0 -> java.util.Objects.requireNonNull(Accessibility.AXNode.fromMap(CdpObject.requireObjectMap(element0)))));
        }
        /**
         * Fetches the accessibility node and partial accessibility tree for this DOM node, if it exists.
         * <p><b>Experimental:</b> this part of CDP may change without notice.
         * @param request request parameters
         * @return a stage completing with the command result
         */
        public CompletionStage<java.util.List<Accessibility.AXNode>> getPartialAXTree(GetPartialAXTreeRequest request) {
            return client.call("Accessibility.getPartialAXTree", request == null ? null : request.toMap(), result_ -> CdpObject.requireList(java.util.Objects.requireNonNull(result_.get("nodes")), element0 -> java.util.Objects.requireNonNull(Accessibility.AXNode.fromMap(CdpObject.requireObjectMap(element0)))));
        }
        /**
         * Fetches the entire accessibility tree for the root Document
         * <p><b>Experimental:</b> this part of CDP may change without notice.
         * @param depth protocol value
         * @param frameId protocol value
         * @return a stage completing with the command result
         */
        public CompletionStage<java.util.List<Accessibility.AXNode>> getFullAXTree(@Nullable Long depth, Page.@Nullable FrameId frameId) {
            final Map<String, Object> params = new LinkedHashMap<>();
            if (depth != null) params.put("depth", CdpObject.json(depth));
            if (frameId != null) params.put("frameId", CdpObject.json(frameId));
            return client.call("Accessibility.getFullAXTree", params, result_ -> CdpObject.requireList(java.util.Objects.requireNonNull(result_.get("nodes")), element0 -> java.util.Objects.requireNonNull(Accessibility.AXNode.fromMap(CdpObject.requireObjectMap(element0)))));
        }
        /**
         * Fetches the entire accessibility tree for the root Document
         * <p><b>Experimental:</b> this part of CDP may change without notice.
         * @return a stage completing with the command result
         */
        public CompletionStage<java.util.List<Accessibility.AXNode>> getFullAXTree() {
            return client.call("Accessibility.getFullAXTree", null, result_ -> CdpObject.requireList(java.util.Objects.requireNonNull(result_.get("nodes")), element0 -> java.util.Objects.requireNonNull(Accessibility.AXNode.fromMap(CdpObject.requireObjectMap(element0)))));
        }
        /**
         * Fetches the entire accessibility tree for the root Document
         * <p><b>Experimental:</b> this part of CDP may change without notice.
         * @param request request parameters
         * @return a stage completing with the command result
         */
        public CompletionStage<java.util.List<Accessibility.AXNode>> getFullAXTree(GetFullAXTreeRequest request) {
            return client.call("Accessibility.getFullAXTree", request == null ? null : request.toMap(), result_ -> CdpObject.requireList(java.util.Objects.requireNonNull(result_.get("nodes")), element0 -> java.util.Objects.requireNonNull(Accessibility.AXNode.fromMap(CdpObject.requireObjectMap(element0)))));
        }
        /**
         * Fetches the root node. Requires {@code enable()} to have been called previously.
         * <p><b>Experimental:</b> this part of CDP may change without notice.
         * @param frameId protocol value
         * @return a stage completing with the command result
         */
        public CompletionStage<Accessibility.AXNode> getRootAXNode(Page.FrameId frameId) {
            final Map<String, Object> params = new LinkedHashMap<>();
            params.put("frameId", CdpObject.json(frameId));
            return client.call("Accessibility.getRootAXNode", params, result_ -> java.util.Objects.requireNonNull(Accessibility.AXNode.fromMap(CdpObject.requireObjectMap(java.util.Objects.requireNonNull(result_.get("node"))))));
        }
        /**
         * Fetches the root node. Requires {@code enable()} to have been called previously.
         * <p><b>Experimental:</b> this part of CDP may change without notice.
         * @return a stage completing with the command result
         */
        public CompletionStage<Accessibility.AXNode> getRootAXNode() {
            return client.call("Accessibility.getRootAXNode", null, result_ -> java.util.Objects.requireNonNull(Accessibility.AXNode.fromMap(CdpObject.requireObjectMap(java.util.Objects.requireNonNull(result_.get("node"))))));
        }
        /**
         * Fetches the root node. Requires {@code enable()} to have been called previously.
         * <p><b>Experimental:</b> this part of CDP may change without notice.
         * @param request request parameters
         * @return a stage completing with the command result
         */
        public CompletionStage<Accessibility.AXNode> getRootAXNode(GetRootAXNodeRequest request) {
            return client.call("Accessibility.getRootAXNode", request == null ? null : request.toMap(), result_ -> java.util.Objects.requireNonNull(Accessibility.AXNode.fromMap(CdpObject.requireObjectMap(java.util.Objects.requireNonNull(result_.get("node"))))));
        }
        /**
         * Fetches a node and all ancestors up to and including the root. Requires {@code enable()} to have been called previously.
         * <p><b>Experimental:</b> this part of CDP may change without notice.
         * @param nodeId protocol value
         * @param backendNodeId protocol value
         * @param objectId protocol value
         * @return a stage completing with the command result
         */
        public CompletionStage<java.util.List<Accessibility.AXNode>> getAXNodeAndAncestors(DOM.@Nullable NodeId nodeId, DOM.@Nullable BackendNodeId backendNodeId, Runtime.@Nullable RemoteObjectId objectId) {
            final Map<String, Object> params = new LinkedHashMap<>();
            if (nodeId != null) params.put("nodeId", CdpObject.json(nodeId));
            if (backendNodeId != null) params.put("backendNodeId", CdpObject.json(backendNodeId));
            if (objectId != null) params.put("objectId", CdpObject.json(objectId));
            return client.call("Accessibility.getAXNodeAndAncestors", params, result_ -> CdpObject.requireList(java.util.Objects.requireNonNull(result_.get("nodes")), element0 -> java.util.Objects.requireNonNull(Accessibility.AXNode.fromMap(CdpObject.requireObjectMap(element0)))));
        }
        /**
         * Fetches a node and all ancestors up to and including the root. Requires {@code enable()} to have been called previously.
         * <p><b>Experimental:</b> this part of CDP may change without notice.
         * @return a stage completing with the command result
         */
        public CompletionStage<java.util.List<Accessibility.AXNode>> getAXNodeAndAncestors() {
            return client.call("Accessibility.getAXNodeAndAncestors", null, result_ -> CdpObject.requireList(java.util.Objects.requireNonNull(result_.get("nodes")), element0 -> java.util.Objects.requireNonNull(Accessibility.AXNode.fromMap(CdpObject.requireObjectMap(element0)))));
        }
        /**
         * Fetches a node and all ancestors up to and including the root. Requires {@code enable()} to have been called previously.
         * <p><b>Experimental:</b> this part of CDP may change without notice.
         * @param request request parameters
         * @return a stage completing with the command result
         */
        public CompletionStage<java.util.List<Accessibility.AXNode>> getAXNodeAndAncestors(GetAXNodeAndAncestorsRequest request) {
            return client.call("Accessibility.getAXNodeAndAncestors", request == null ? null : request.toMap(), result_ -> CdpObject.requireList(java.util.Objects.requireNonNull(result_.get("nodes")), element0 -> java.util.Objects.requireNonNull(Accessibility.AXNode.fromMap(CdpObject.requireObjectMap(element0)))));
        }
        /**
         * Fetches a particular accessibility node by AXNodeId. Requires {@code enable()} to have been called previously.
         * <p><b>Experimental:</b> this part of CDP may change without notice.
         * @param id protocol value
         * @param frameId protocol value
         * @return a stage completing with the command result
         */
        public CompletionStage<java.util.List<Accessibility.AXNode>> getChildAXNodes(Accessibility.AXNodeId id, Page.FrameId frameId) {
            final Map<String, Object> params = new LinkedHashMap<>();
            params.put("id", CdpObject.json(id));
            params.put("frameId", CdpObject.json(frameId));
            return client.call("Accessibility.getChildAXNodes", params, result_ -> CdpObject.requireList(java.util.Objects.requireNonNull(result_.get("nodes")), element0 -> java.util.Objects.requireNonNull(Accessibility.AXNode.fromMap(CdpObject.requireObjectMap(element0)))));
        }
        /**
         * Fetches a particular accessibility node by AXNodeId. Requires {@code enable()} to have been called previously.
         * <p><b>Experimental:</b> this part of CDP may change without notice.
         * @param id protocol value
         * @return a stage completing with the command result
         */
        public CompletionStage<java.util.List<Accessibility.AXNode>> getChildAXNodes(Accessibility.AXNodeId id) {
            final Map<String, Object> params = new LinkedHashMap<>();
            params.put("id", CdpObject.json(id));
            return client.call("Accessibility.getChildAXNodes", params, result_ -> CdpObject.requireList(java.util.Objects.requireNonNull(result_.get("nodes")), element0 -> java.util.Objects.requireNonNull(Accessibility.AXNode.fromMap(CdpObject.requireObjectMap(element0)))));
        }
        /**
         * Fetches a particular accessibility node by AXNodeId. Requires {@code enable()} to have been called previously.
         * <p><b>Experimental:</b> this part of CDP may change without notice.
         * @param request request parameters
         * @return a stage completing with the command result
         */
        public CompletionStage<java.util.List<Accessibility.AXNode>> getChildAXNodes(GetChildAXNodesRequest request) {
            return client.call("Accessibility.getChildAXNodes", request == null ? null : request.toMap(), result_ -> CdpObject.requireList(java.util.Objects.requireNonNull(result_.get("nodes")), element0 -> java.util.Objects.requireNonNull(Accessibility.AXNode.fromMap(CdpObject.requireObjectMap(element0)))));
        }
        /**
         * Query a DOM node&#x27;s accessibility subtree for accessible name and role. This command computes the name and role for all nodes in the subtree, including those that are ignored for accessibility, and returns those that match the specified name and role. If no DOM node is specified, or the DOM node does not exist, the command returns an error. If neither {@code accessibleName} or {@code role} is specified, it returns all the accessibility nodes in the subtree.
         * <p><b>Experimental:</b> this part of CDP may change without notice.
         * @param nodeId protocol value
         * @param backendNodeId protocol value
         * @param objectId protocol value
         * @param accessibleName protocol value
         * @param role protocol value
         * @return a stage completing with the command result
         */
        public CompletionStage<java.util.List<Accessibility.AXNode>> queryAXTree(DOM.@Nullable NodeId nodeId, DOM.@Nullable BackendNodeId backendNodeId, Runtime.@Nullable RemoteObjectId objectId, @Nullable String accessibleName, @Nullable String role) {
            final Map<String, Object> params = new LinkedHashMap<>();
            if (nodeId != null) params.put("nodeId", CdpObject.json(nodeId));
            if (backendNodeId != null) params.put("backendNodeId", CdpObject.json(backendNodeId));
            if (objectId != null) params.put("objectId", CdpObject.json(objectId));
            if (accessibleName != null) params.put("accessibleName", CdpObject.json(accessibleName));
            if (role != null) params.put("role", CdpObject.json(role));
            return client.call("Accessibility.queryAXTree", params, result_ -> CdpObject.requireList(java.util.Objects.requireNonNull(result_.get("nodes")), element0 -> java.util.Objects.requireNonNull(Accessibility.AXNode.fromMap(CdpObject.requireObjectMap(element0)))));
        }
        /**
         * Query a DOM node&#x27;s accessibility subtree for accessible name and role. This command computes the name and role for all nodes in the subtree, including those that are ignored for accessibility, and returns those that match the specified name and role. If no DOM node is specified, or the DOM node does not exist, the command returns an error. If neither {@code accessibleName} or {@code role} is specified, it returns all the accessibility nodes in the subtree.
         * <p><b>Experimental:</b> this part of CDP may change without notice.
         * @return a stage completing with the command result
         */
        public CompletionStage<java.util.List<Accessibility.AXNode>> queryAXTree() {
            return client.call("Accessibility.queryAXTree", null, result_ -> CdpObject.requireList(java.util.Objects.requireNonNull(result_.get("nodes")), element0 -> java.util.Objects.requireNonNull(Accessibility.AXNode.fromMap(CdpObject.requireObjectMap(element0)))));
        }
        /**
         * Query a DOM node&#x27;s accessibility subtree for accessible name and role. This command computes the name and role for all nodes in the subtree, including those that are ignored for accessibility, and returns those that match the specified name and role. If no DOM node is specified, or the DOM node does not exist, the command returns an error. If neither {@code accessibleName} or {@code role} is specified, it returns all the accessibility nodes in the subtree.
         * <p><b>Experimental:</b> this part of CDP may change without notice.
         * @param request request parameters
         * @return a stage completing with the command result
         */
        public CompletionStage<java.util.List<Accessibility.AXNode>> queryAXTree(QueryAXTreeRequest request) {
            return client.call("Accessibility.queryAXTree", request == null ? null : request.toMap(), result_ -> CdpObject.requireList(java.util.Objects.requireNonNull(result_.get("nodes")), element0 -> java.util.Objects.requireNonNull(Accessibility.AXNode.fromMap(CdpObject.requireObjectMap(element0)))));
        }
        /**
         * The loadComplete event mirrors the load complete event sent by the browser to assistive technology when the web page has finished loading.
         * <p><b>Experimental:</b> this part of CDP may change without notice.
         * @param handler event callback
         * @return a removable subscription
         */
        public CdpSubscription onLoadComplete(Consumer<LoadCompleteEvent> handler) {
            return client.on("Accessibility.loadComplete", LoadCompleteEvent::fromMap, handler);
        }
        /**
         * The nodesUpdated event is sent every time a previously requested node has changed the in tree.
         * <p><b>Experimental:</b> this part of CDP may change without notice.
         * @param handler event callback
         * @return a removable subscription
         */
        public CdpSubscription onNodesUpdated(Consumer<NodesUpdatedEvent> handler) {
            return client.on("Accessibility.nodesUpdated", NodesUpdatedEvent::fromMap, handler);
        }
    }
}
