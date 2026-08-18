// GENERATED - do not edit. Regenerate via: mvn generate-sources -pl cef4j-platform -Dcef.version=150.0.18+gdb11278+chromium-150.0.7871.213
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
 * Chrome DevTools Protocol Accessibility domain.
 * <p><b>Experimental:</b> this part of CDP may change without notice.
 * @see <a href="https://chromium.googlesource.com/chromium/src/+/refs/tags/150.0.7871.213/third_party/blink/public/devtools_protocol/domains/Accessibility.pdl">Pinned protocol source</a>
 */
@Generated("mvn generate-sources -pl cef4j-platform -Dcef.version=150.0.18+gdb11278+chromium-150.0.7871.213")
@SuppressWarnings({"EscapedEntity", "InvalidParam", "JavaLangClash", "MissingSummary", "Unchecked", "UnusedMethod"})
public final class Accessibility {
    private Accessibility() {}
    /**
     * Unique accessibility node identifier.
     */
    public static final class AXNodeId implements CdpValue<String> {
        public final String value;
        public AXNodeId(@Nonnull String value) { this.value = java.util.Objects.requireNonNull(value); }
        @Nonnull public String value() { return value; }
        @Override public boolean equals(Object other) {
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
        @Nonnull public String value() { return value; }
        public static AXValueType of(@Nonnull String value) {
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
        @Nonnull public String value() { return value; }
        public static AXValueSourceType of(@Nonnull String value) {
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
        @Nonnull public String value() { return value; }
        public static AXValueNativeSourceType of(@Nonnull String value) {
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
            return Optional.ofNullable(raw("value") == null ? null : Accessibility.AXValue.fromMap(java.util.Objects.requireNonNull(objectMap(raw("value")))));
        }
        /**
         * The name of the relevant attribute, if any.
         * @return the protocol field value, empty when absent
         */
        public Optional<String> attribute() {
            return Optional.ofNullable((String) raw("attribute"));
        }
        /**
         * The value of the relevant attribute, if any.
         * @return the protocol field value, empty when absent
         */
        public Optional<Accessibility.AXValue> attributeValue() {
            return Optional.ofNullable(raw("attributeValue") == null ? null : Accessibility.AXValue.fromMap(java.util.Objects.requireNonNull(objectMap(raw("attributeValue")))));
        }
        /**
         * Whether this source is superseded by a higher priority source.
         * @return the protocol field value, empty when absent
         */
        public Optional<Boolean> superseded() {
            return Optional.ofNullable((Boolean) raw("superseded"));
        }
        /**
         * The native markup source for this value, e.g. a {@code &lt;label&gt;} element.
         * @return the protocol field value, empty when absent
         */
        public Optional<Accessibility.AXValueNativeSourceType> nativeSource() {
            return Optional.ofNullable(raw("nativeSource") == null ? null : Accessibility.AXValueNativeSourceType.of((String) raw("nativeSource")));
        }
        /**
         * The value, such as a node or node list, of the native source.
         * @return the protocol field value, empty when absent
         */
        public Optional<Accessibility.AXValue> nativeSourceValue() {
            return Optional.ofNullable(raw("nativeSourceValue") == null ? null : Accessibility.AXValue.fromMap(java.util.Objects.requireNonNull(objectMap(raw("nativeSourceValue")))));
        }
        /**
         * Whether the value for this property is invalid.
         * @return the protocol field value, empty when absent
         */
        public Optional<Boolean> invalid() {
            return Optional.ofNullable((Boolean) raw("invalid"));
        }
        /**
         * Reason for the value being invalid, if it is.
         * @return the protocol field value, empty when absent
         */
        public Optional<String> invalidReason() {
            return Optional.ofNullable((String) raw("invalidReason"));
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
         * @param value field value; empty omits the value
         * @return this model
         */
        public AXValueSource value(Optional<Accessibility.AXValue> value) {
            set("value", value.orElse(null));
            return this;
        }
        /**
         * The value of this property source.
         * @param value field value; null removes the value
         * @return this model
         */
        public AXValueSource value(Accessibility.AXValue value) {
            set("value", value);
            return this;
        }
        /**
         * The name of the relevant attribute, if any.
         * @param attribute field value; empty omits the value
         * @return this model
         */
        public AXValueSource attribute(Optional<String> attribute) {
            set("attribute", attribute.orElse(null));
            return this;
        }
        /**
         * The name of the relevant attribute, if any.
         * @param attribute field value; null removes the value
         * @return this model
         */
        public AXValueSource attribute(String attribute) {
            set("attribute", attribute);
            return this;
        }
        /**
         * The value of the relevant attribute, if any.
         * @param attributeValue field value; empty omits the value
         * @return this model
         */
        public AXValueSource attributeValue(Optional<Accessibility.AXValue> attributeValue) {
            set("attributeValue", attributeValue.orElse(null));
            return this;
        }
        /**
         * The value of the relevant attribute, if any.
         * @param attributeValue field value; null removes the value
         * @return this model
         */
        public AXValueSource attributeValue(Accessibility.AXValue attributeValue) {
            set("attributeValue", attributeValue);
            return this;
        }
        /**
         * Whether this source is superseded by a higher priority source.
         * @param superseded field value; empty omits the value
         * @return this model
         */
        public AXValueSource superseded(Optional<Boolean> superseded) {
            set("superseded", superseded.orElse(null));
            return this;
        }
        /**
         * Whether this source is superseded by a higher priority source.
         * @param superseded field value; null removes the value
         * @return this model
         */
        public AXValueSource superseded(Boolean superseded) {
            set("superseded", superseded);
            return this;
        }
        /**
         * The native markup source for this value, e.g. a {@code &lt;label&gt;} element.
         * @param nativeSource field value; empty omits the value
         * @return this model
         */
        public AXValueSource nativeSource(Optional<Accessibility.AXValueNativeSourceType> nativeSource) {
            set("nativeSource", nativeSource.orElse(null));
            return this;
        }
        /**
         * The native markup source for this value, e.g. a {@code &lt;label&gt;} element.
         * @param nativeSource field value; null removes the value
         * @return this model
         */
        public AXValueSource nativeSource(Accessibility.AXValueNativeSourceType nativeSource) {
            set("nativeSource", nativeSource);
            return this;
        }
        /**
         * The value, such as a node or node list, of the native source.
         * @param nativeSourceValue field value; empty omits the value
         * @return this model
         */
        public AXValueSource nativeSourceValue(Optional<Accessibility.AXValue> nativeSourceValue) {
            set("nativeSourceValue", nativeSourceValue.orElse(null));
            return this;
        }
        /**
         * The value, such as a node or node list, of the native source.
         * @param nativeSourceValue field value; null removes the value
         * @return this model
         */
        public AXValueSource nativeSourceValue(Accessibility.AXValue nativeSourceValue) {
            set("nativeSourceValue", nativeSourceValue);
            return this;
        }
        /**
         * Whether the value for this property is invalid.
         * @param invalid field value; empty omits the value
         * @return this model
         */
        public AXValueSource invalid(Optional<Boolean> invalid) {
            set("invalid", invalid.orElse(null));
            return this;
        }
        /**
         * Whether the value for this property is invalid.
         * @param invalid field value; null removes the value
         * @return this model
         */
        public AXValueSource invalid(Boolean invalid) {
            set("invalid", invalid);
            return this;
        }
        /**
         * Reason for the value being invalid, if it is.
         * @param invalidReason field value; empty omits the value
         * @return this model
         */
        public AXValueSource invalidReason(Optional<String> invalidReason) {
            set("invalidReason", invalidReason.orElse(null));
            return this;
        }
        /**
         * Reason for the value being invalid, if it is.
         * @param invalidReason field value; null removes the value
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
            return Optional.ofNullable((String) raw("idref"));
        }
        /**
         * The text alternative of this node in the current context.
         * @return the protocol field value, empty when absent
         */
        public Optional<String> text() {
            return Optional.ofNullable((String) raw("text"));
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
         * @param idref field value; empty omits the value
         * @return this model
         */
        public AXRelatedNode idref(Optional<String> idref) {
            set("idref", idref.orElse(null));
            return this;
        }
        /**
         * The IDRef value provided, if any.
         * @param idref field value; null removes the value
         * @return this model
         */
        public AXRelatedNode idref(String idref) {
            set("idref", idref);
            return this;
        }
        /**
         * The text alternative of this node in the current context.
         * @param text field value; empty omits the value
         * @return this model
         */
        public AXRelatedNode text(Optional<String> text) {
            set("text", text.orElse(null));
            return this;
        }
        /**
         * The text alternative of this node in the current context.
         * @param text field value; null removes the value
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
            return java.util.Objects.requireNonNull(Accessibility.AXValue.fromMap(java.util.Objects.requireNonNull(CdpObject.objectMap(require("value")))));
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
            return Optional.ofNullable(raw("value"));
        }
        /**
         * One or more related nodes, if applicable.
         * @return the protocol field value, empty when absent
         */
        public Optional<java.util.List<Accessibility.AXRelatedNode>> relatedNodes() {
            return Optional.ofNullable(list(raw("relatedNodes"), element0 -> java.util.Objects.requireNonNull(Accessibility.AXRelatedNode.fromMap(java.util.Objects.requireNonNull(CdpObject.objectMap(element0))))));
        }
        /**
         * The sources which contributed to the computation of this property.
         * @return the protocol field value, empty when absent
         */
        public Optional<java.util.List<Accessibility.AXValueSource>> sources() {
            return Optional.ofNullable(list(raw("sources"), element0 -> java.util.Objects.requireNonNull(Accessibility.AXValueSource.fromMap(java.util.Objects.requireNonNull(CdpObject.objectMap(element0))))));
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
         * @param value field value; empty omits the value
         * @return this model
         */
        public AXValue value(Optional<Object> value) {
            set("value", value.orElse(null));
            return this;
        }
        /**
         * The computed value of this property.
         * @param value field value; null removes the value
         * @return this model
         */
        public AXValue value(Object value) {
            set("value", value);
            return this;
        }
        /**
         * One or more related nodes, if applicable.
         * @param relatedNodes field value; empty omits the value
         * @return this model
         */
        public AXValue relatedNodes(Optional<java.util.List<Accessibility.AXRelatedNode>> relatedNodes) {
            set("relatedNodes", relatedNodes.orElse(null));
            return this;
        }
        /**
         * One or more related nodes, if applicable.
         * @param relatedNodes field value; null removes the value
         * @return this model
         */
        public AXValue relatedNodes(java.util.List<Accessibility.AXRelatedNode> relatedNodes) {
            set("relatedNodes", relatedNodes);
            return this;
        }
        /**
         * The sources which contributed to the computation of this property.
         * @param sources field value; empty omits the value
         * @return this model
         */
        public AXValue sources(Optional<java.util.List<Accessibility.AXValueSource>> sources) {
            set("sources", sources.orElse(null));
            return this;
        }
        /**
         * The sources which contributed to the computation of this property.
         * @param sources field value; null removes the value
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
        @Nonnull public String value() { return value; }
        public static AXPropertyName of(@Nonnull String value) {
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
            return Optional.ofNullable(list(raw("ignoredReasons"), element0 -> java.util.Objects.requireNonNull(Accessibility.AXProperty.fromMap(java.util.Objects.requireNonNull(CdpObject.objectMap(element0))))));
        }
        /**
         * This {@code Node}&#x27;s role, whether explicit or implicit.
         * @return the protocol field value, empty when absent
         */
        public Optional<Accessibility.AXValue> role() {
            return Optional.ofNullable(raw("role") == null ? null : Accessibility.AXValue.fromMap(java.util.Objects.requireNonNull(objectMap(raw("role")))));
        }
        /**
         * This {@code Node}&#x27;s Chrome raw role.
         * @return the protocol field value, empty when absent
         */
        public Optional<Accessibility.AXValue> chromeRole() {
            return Optional.ofNullable(raw("chromeRole") == null ? null : Accessibility.AXValue.fromMap(java.util.Objects.requireNonNull(objectMap(raw("chromeRole")))));
        }
        /**
         * The accessible name for this {@code Node}.
         * @return the protocol field value, empty when absent
         */
        public Optional<Accessibility.AXValue> name() {
            return Optional.ofNullable(raw("name") == null ? null : Accessibility.AXValue.fromMap(java.util.Objects.requireNonNull(objectMap(raw("name")))));
        }
        /**
         * The accessible description for this {@code Node}.
         * @return the protocol field value, empty when absent
         */
        public Optional<Accessibility.AXValue> description() {
            return Optional.ofNullable(raw("description") == null ? null : Accessibility.AXValue.fromMap(java.util.Objects.requireNonNull(objectMap(raw("description")))));
        }
        /**
         * The value for this {@code Node}.
         * @return the protocol field value, empty when absent
         */
        public Optional<Accessibility.AXValue> value() {
            return Optional.ofNullable(raw("value") == null ? null : Accessibility.AXValue.fromMap(java.util.Objects.requireNonNull(objectMap(raw("value")))));
        }
        /**
         * All other properties
         * @return the protocol field value, empty when absent
         */
        public Optional<java.util.List<Accessibility.AXProperty>> properties() {
            return Optional.ofNullable(list(raw("properties"), element0 -> java.util.Objects.requireNonNull(Accessibility.AXProperty.fromMap(java.util.Objects.requireNonNull(CdpObject.objectMap(element0))))));
        }
        /**
         * ID for this node&#x27;s parent.
         * @return the protocol field value, empty when absent
         */
        public Optional<Accessibility.AXNodeId> parentId() {
            return Optional.ofNullable(raw("parentId") == null ? null : new Accessibility.AXNodeId((String) raw("parentId")));
        }
        /**
         * IDs for each of this node&#x27;s child nodes.
         * @return the protocol field value, empty when absent
         */
        public Optional<java.util.List<Accessibility.AXNodeId>> childIds() {
            return Optional.ofNullable(list(raw("childIds"), element0 -> new Accessibility.AXNodeId((String) element0)));
        }
        /**
         * The backend ID for the associated DOM node, if any.
         * @return the protocol field value, empty when absent
         */
        public Optional<DOM.BackendNodeId> backendDOMNodeId() {
            return Optional.ofNullable(raw("backendDOMNodeId") == null ? null : new DOM.BackendNodeId(((Number) raw("backendDOMNodeId")).longValue()));
        }
        /**
         * The frame ID for the frame associated with this nodes document.
         * @return the protocol field value, empty when absent
         */
        public Optional<Page.FrameId> frameId() {
            return Optional.ofNullable(raw("frameId") == null ? null : new Page.FrameId((String) raw("frameId")));
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
         * @param ignoredReasons field value; empty omits the value
         * @return this model
         */
        public AXNode ignoredReasons(Optional<java.util.List<Accessibility.AXProperty>> ignoredReasons) {
            set("ignoredReasons", ignoredReasons.orElse(null));
            return this;
        }
        /**
         * Collection of reasons why this node is hidden.
         * @param ignoredReasons field value; null removes the value
         * @return this model
         */
        public AXNode ignoredReasons(java.util.List<Accessibility.AXProperty> ignoredReasons) {
            set("ignoredReasons", ignoredReasons);
            return this;
        }
        /**
         * This {@code Node}&#x27;s role, whether explicit or implicit.
         * @param role field value; empty omits the value
         * @return this model
         */
        public AXNode role(Optional<Accessibility.AXValue> role) {
            set("role", role.orElse(null));
            return this;
        }
        /**
         * This {@code Node}&#x27;s role, whether explicit or implicit.
         * @param role field value; null removes the value
         * @return this model
         */
        public AXNode role(Accessibility.AXValue role) {
            set("role", role);
            return this;
        }
        /**
         * This {@code Node}&#x27;s Chrome raw role.
         * @param chromeRole field value; empty omits the value
         * @return this model
         */
        public AXNode chromeRole(Optional<Accessibility.AXValue> chromeRole) {
            set("chromeRole", chromeRole.orElse(null));
            return this;
        }
        /**
         * This {@code Node}&#x27;s Chrome raw role.
         * @param chromeRole field value; null removes the value
         * @return this model
         */
        public AXNode chromeRole(Accessibility.AXValue chromeRole) {
            set("chromeRole", chromeRole);
            return this;
        }
        /**
         * The accessible name for this {@code Node}.
         * @param name field value; empty omits the value
         * @return this model
         */
        public AXNode name(Optional<Accessibility.AXValue> name) {
            set("name", name.orElse(null));
            return this;
        }
        /**
         * The accessible name for this {@code Node}.
         * @param name field value; null removes the value
         * @return this model
         */
        public AXNode name(Accessibility.AXValue name) {
            set("name", name);
            return this;
        }
        /**
         * The accessible description for this {@code Node}.
         * @param description field value; empty omits the value
         * @return this model
         */
        public AXNode description(Optional<Accessibility.AXValue> description) {
            set("description", description.orElse(null));
            return this;
        }
        /**
         * The accessible description for this {@code Node}.
         * @param description field value; null removes the value
         * @return this model
         */
        public AXNode description(Accessibility.AXValue description) {
            set("description", description);
            return this;
        }
        /**
         * The value for this {@code Node}.
         * @param value field value; empty omits the value
         * @return this model
         */
        public AXNode value(Optional<Accessibility.AXValue> value) {
            set("value", value.orElse(null));
            return this;
        }
        /**
         * The value for this {@code Node}.
         * @param value field value; null removes the value
         * @return this model
         */
        public AXNode value(Accessibility.AXValue value) {
            set("value", value);
            return this;
        }
        /**
         * All other properties
         * @param properties field value; empty omits the value
         * @return this model
         */
        public AXNode properties(Optional<java.util.List<Accessibility.AXProperty>> properties) {
            set("properties", properties.orElse(null));
            return this;
        }
        /**
         * All other properties
         * @param properties field value; null removes the value
         * @return this model
         */
        public AXNode properties(java.util.List<Accessibility.AXProperty> properties) {
            set("properties", properties);
            return this;
        }
        /**
         * ID for this node&#x27;s parent.
         * @param parentId field value; empty omits the value
         * @return this model
         */
        public AXNode parentId(Optional<Accessibility.AXNodeId> parentId) {
            set("parentId", parentId.orElse(null));
            return this;
        }
        /**
         * ID for this node&#x27;s parent.
         * @param parentId field value; null removes the value
         * @return this model
         */
        public AXNode parentId(Accessibility.AXNodeId parentId) {
            set("parentId", parentId);
            return this;
        }
        /**
         * IDs for each of this node&#x27;s child nodes.
         * @param childIds field value; empty omits the value
         * @return this model
         */
        public AXNode childIds(Optional<java.util.List<Accessibility.AXNodeId>> childIds) {
            set("childIds", childIds.orElse(null));
            return this;
        }
        /**
         * IDs for each of this node&#x27;s child nodes.
         * @param childIds field value; null removes the value
         * @return this model
         */
        public AXNode childIds(java.util.List<Accessibility.AXNodeId> childIds) {
            set("childIds", childIds);
            return this;
        }
        /**
         * The backend ID for the associated DOM node, if any.
         * @param backendDOMNodeId field value; empty omits the value
         * @return this model
         */
        public AXNode backendDOMNodeId(Optional<DOM.BackendNodeId> backendDOMNodeId) {
            set("backendDOMNodeId", backendDOMNodeId.orElse(null));
            return this;
        }
        /**
         * The backend ID for the associated DOM node, if any.
         * @param backendDOMNodeId field value; null removes the value
         * @return this model
         */
        public AXNode backendDOMNodeId(DOM.BackendNodeId backendDOMNodeId) {
            set("backendDOMNodeId", backendDOMNodeId);
            return this;
        }
        /**
         * The frame ID for the frame associated with this nodes document.
         * @param frameId field value; empty omits the value
         * @return this model
         */
        public AXNode frameId(Optional<Page.FrameId> frameId) {
            set("frameId", frameId.orElse(null));
            return this;
        }
        /**
         * The frame ID for the frame associated with this nodes document.
         * @param frameId field value; null removes the value
         * @return this model
         */
        public AXNode frameId(Page.FrameId frameId) {
            set("frameId", frameId);
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
            return java.util.Objects.requireNonNull(Accessibility.AXNode.fromMap(java.util.Objects.requireNonNull(CdpObject.objectMap(require("root")))));
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
            return CdpObject.requireList(require("nodes"), element0 -> java.util.Objects.requireNonNull(Accessibility.AXNode.fromMap(java.util.Objects.requireNonNull(CdpObject.objectMap(element0)))));
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
            return client.call("Accessibility.disable", null, result_ -> null);
        }
        /**
         * Enables the accessibility domain which causes {@code AXNodeId}s to remain consistent between method calls. This turns on accessibility for the page, which can impact performance until accessibility is disabled.
         * @return a stage completing when the command completes
         */
        public CompletionStage<Void> enable() {
            return client.call("Accessibility.enable", null, result_ -> null);
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
        public CompletionStage<java.util.List<Accessibility.AXNode>> getPartialAXTree(Optional<DOM.NodeId> nodeId, Optional<DOM.BackendNodeId> backendNodeId, Optional<Runtime.RemoteObjectId> objectId, Optional<Boolean> fetchRelatives) {
            Map<String, Object> params = new LinkedHashMap<>();
            nodeId.ifPresent(value_ -> params.put("nodeId", CdpObject.json(value_)));
            backendNodeId.ifPresent(value_ -> params.put("backendNodeId", CdpObject.json(value_)));
            objectId.ifPresent(value_ -> params.put("objectId", CdpObject.json(value_)));
            fetchRelatives.ifPresent(value_ -> params.put("fetchRelatives", value_));
            return client.call("Accessibility.getPartialAXTree", params, result_ -> CdpObject.requireList(java.util.Objects.requireNonNull(result_.get("nodes")), element0 -> java.util.Objects.requireNonNull(Accessibility.AXNode.fromMap(java.util.Objects.requireNonNull(CdpObject.objectMap(element0))))));
        }
        /**
         * Fetches the accessibility node and partial accessibility tree for this DOM node, if it exists.
         * <p><b>Experimental:</b> this part of CDP may change without notice.
         * @return a stage completing with the command result
         */
        public CompletionStage<java.util.List<Accessibility.AXNode>> getPartialAXTree() {
            return getPartialAXTree(Optional.empty(), Optional.empty(), Optional.empty(), Optional.empty());
        }
        /**
         * Fetches the entire accessibility tree for the root Document
         * <p><b>Experimental:</b> this part of CDP may change without notice.
         * @param depth protocol value
         * @param frameId protocol value
         * @return a stage completing with the command result
         */
        public CompletionStage<java.util.List<Accessibility.AXNode>> getFullAXTree(OptionalLong depth, Optional<Page.FrameId> frameId) {
            Map<String, Object> params = new LinkedHashMap<>();
            depth.ifPresent(value_ -> params.put("depth", value_));
            frameId.ifPresent(value_ -> params.put("frameId", CdpObject.json(value_)));
            return client.call("Accessibility.getFullAXTree", params, result_ -> CdpObject.requireList(java.util.Objects.requireNonNull(result_.get("nodes")), element0 -> java.util.Objects.requireNonNull(Accessibility.AXNode.fromMap(java.util.Objects.requireNonNull(CdpObject.objectMap(element0))))));
        }
        /**
         * Fetches the entire accessibility tree for the root Document
         * <p><b>Experimental:</b> this part of CDP may change without notice.
         * @return a stage completing with the command result
         */
        public CompletionStage<java.util.List<Accessibility.AXNode>> getFullAXTree() {
            return getFullAXTree(OptionalLong.empty(), Optional.empty());
        }
        /**
         * Fetches the root node. Requires {@code enable()} to have been called previously.
         * <p><b>Experimental:</b> this part of CDP may change without notice.
         * @param frameId protocol value
         * @return a stage completing with the command result
         */
        public CompletionStage<Accessibility.AXNode> getRootAXNode(Optional<Page.FrameId> frameId) {
            Map<String, Object> params = new LinkedHashMap<>();
            frameId.ifPresent(value_ -> params.put("frameId", CdpObject.json(value_)));
            return client.call("Accessibility.getRootAXNode", params, result_ -> java.util.Objects.requireNonNull(Accessibility.AXNode.fromMap(java.util.Objects.requireNonNull(CdpObject.objectMap(java.util.Objects.requireNonNull(result_.get("node")))))));
        }
        /**
         * Fetches the root node. Requires {@code enable()} to have been called previously.
         * <p><b>Experimental:</b> this part of CDP may change without notice.
         * @return a stage completing with the command result
         */
        public CompletionStage<Accessibility.AXNode> getRootAXNode() {
            return getRootAXNode(Optional.empty());
        }
        /**
         * Fetches a node and all ancestors up to and including the root. Requires {@code enable()} to have been called previously.
         * <p><b>Experimental:</b> this part of CDP may change without notice.
         * @param nodeId protocol value
         * @param backendNodeId protocol value
         * @param objectId protocol value
         * @return a stage completing with the command result
         */
        public CompletionStage<java.util.List<Accessibility.AXNode>> getAXNodeAndAncestors(Optional<DOM.NodeId> nodeId, Optional<DOM.BackendNodeId> backendNodeId, Optional<Runtime.RemoteObjectId> objectId) {
            Map<String, Object> params = new LinkedHashMap<>();
            nodeId.ifPresent(value_ -> params.put("nodeId", CdpObject.json(value_)));
            backendNodeId.ifPresent(value_ -> params.put("backendNodeId", CdpObject.json(value_)));
            objectId.ifPresent(value_ -> params.put("objectId", CdpObject.json(value_)));
            return client.call("Accessibility.getAXNodeAndAncestors", params, result_ -> CdpObject.requireList(java.util.Objects.requireNonNull(result_.get("nodes")), element0 -> java.util.Objects.requireNonNull(Accessibility.AXNode.fromMap(java.util.Objects.requireNonNull(CdpObject.objectMap(element0))))));
        }
        /**
         * Fetches a node and all ancestors up to and including the root. Requires {@code enable()} to have been called previously.
         * <p><b>Experimental:</b> this part of CDP may change without notice.
         * @return a stage completing with the command result
         */
        public CompletionStage<java.util.List<Accessibility.AXNode>> getAXNodeAndAncestors() {
            return getAXNodeAndAncestors(Optional.empty(), Optional.empty(), Optional.empty());
        }
        /**
         * Fetches a particular accessibility node by AXNodeId. Requires {@code enable()} to have been called previously.
         * <p><b>Experimental:</b> this part of CDP may change without notice.
         * @param id protocol value
         * @param frameId protocol value
         * @return a stage completing with the command result
         */
        public CompletionStage<java.util.List<Accessibility.AXNode>> getChildAXNodes(Accessibility.AXNodeId id, Optional<Page.FrameId> frameId) {
            Map<String, Object> params = new LinkedHashMap<>();
            params.put("id", CdpObject.json(id));
            frameId.ifPresent(value_ -> params.put("frameId", CdpObject.json(value_)));
            return client.call("Accessibility.getChildAXNodes", params, result_ -> CdpObject.requireList(java.util.Objects.requireNonNull(result_.get("nodes")), element0 -> java.util.Objects.requireNonNull(Accessibility.AXNode.fromMap(java.util.Objects.requireNonNull(CdpObject.objectMap(element0))))));
        }
        /**
         * Fetches a particular accessibility node by AXNodeId. Requires {@code enable()} to have been called previously.
         * <p><b>Experimental:</b> this part of CDP may change without notice.
         * @param id protocol value
         * @return a stage completing with the command result
         */
        public CompletionStage<java.util.List<Accessibility.AXNode>> getChildAXNodes(Accessibility.AXNodeId id) {
            return getChildAXNodes(id, Optional.empty());
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
        public CompletionStage<java.util.List<Accessibility.AXNode>> queryAXTree(Optional<DOM.NodeId> nodeId, Optional<DOM.BackendNodeId> backendNodeId, Optional<Runtime.RemoteObjectId> objectId, Optional<String> accessibleName, Optional<String> role) {
            Map<String, Object> params = new LinkedHashMap<>();
            nodeId.ifPresent(value_ -> params.put("nodeId", CdpObject.json(value_)));
            backendNodeId.ifPresent(value_ -> params.put("backendNodeId", CdpObject.json(value_)));
            objectId.ifPresent(value_ -> params.put("objectId", CdpObject.json(value_)));
            accessibleName.ifPresent(value_ -> params.put("accessibleName", CdpObject.json(value_)));
            role.ifPresent(value_ -> params.put("role", CdpObject.json(value_)));
            return client.call("Accessibility.queryAXTree", params, result_ -> CdpObject.requireList(java.util.Objects.requireNonNull(result_.get("nodes")), element0 -> java.util.Objects.requireNonNull(Accessibility.AXNode.fromMap(java.util.Objects.requireNonNull(CdpObject.objectMap(element0))))));
        }
        /**
         * Query a DOM node&#x27;s accessibility subtree for accessible name and role. This command computes the name and role for all nodes in the subtree, including those that are ignored for accessibility, and returns those that match the specified name and role. If no DOM node is specified, or the DOM node does not exist, the command returns an error. If neither {@code accessibleName} or {@code role} is specified, it returns all the accessibility nodes in the subtree.
         * <p><b>Experimental:</b> this part of CDP may change without notice.
         * @return a stage completing with the command result
         */
        public CompletionStage<java.util.List<Accessibility.AXNode>> queryAXTree() {
            return queryAXTree(Optional.empty(), Optional.empty(), Optional.empty(), Optional.empty(), Optional.empty());
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
