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
 * Chrome DevTools Protocol Accessibility domain.
 * <p><b>Experimental:</b> this part of CDP may change without notice.
 * @see <a href="https://chromium.googlesource.com/chromium/src/+/refs/tags/146.0.7680.165/third_party/blink/public/devtools_protocol/domains/Accessibility.pdl">Pinned protocol source</a>
 */
@SuppressWarnings({"EscapedEntity", "JavaLangClash", "MissingSummary", "UnusedMethod"})
public final class Accessibility {
    private Accessibility() {}
    @Nullable private static Long numberAsLong(@Nullable Object value) { return value == null ? null : ((Number) value).longValue(); }
    @Nullable private static Double numberAsDouble(@Nullable Object value) { return value == null ? null : ((Number) value).doubleValue(); }
    /**
     * Enum of possible property types.
     */
    public static final class AXValueType {
        private AXValueType() {}
        public static final String BOOLEAN = "boolean";
        public static final String TRISTATE = "tristate";
        public static final String BOOLEANORUNDEFINED = "booleanOrUndefined";
        public static final String IDREF = "idref";
        public static final String IDREFLIST = "idrefList";
        public static final String INTEGER = "integer";
        public static final String NODE = "node";
        public static final String NODELIST = "nodeList";
        public static final String NUMBER = "number";
        public static final String STRING = "string";
        public static final String COMPUTEDSTRING = "computedString";
        public static final String TOKEN = "token";
        public static final String TOKENLIST = "tokenList";
        public static final String DOMRELATION = "domRelation";
        public static final String ROLE = "role";
        public static final String INTERNALROLE = "internalRole";
        public static final String VALUEUNDEFINED = "valueUndefined";
    }
    /**
     * Enum of possible property sources.
     */
    public static final class AXValueSourceType {
        private AXValueSourceType() {}
        public static final String ATTRIBUTE = "attribute";
        public static final String IMPLICIT = "implicit";
        public static final String STYLE = "style";
        public static final String CONTENTS = "contents";
        public static final String PLACEHOLDER = "placeholder";
        public static final String RELATEDELEMENT = "relatedElement";
    }
    /**
     * Enum of possible native property sources (as a subtype of a particular AXValueSourceType).
     */
    public static final class AXValueNativeSourceType {
        private AXValueNativeSourceType() {}
        public static final String DESCRIPTION = "description";
        public static final String FIGCAPTION = "figcaption";
        public static final String LABEL = "label";
        public static final String LABELFOR = "labelfor";
        public static final String LABELWRAPPED = "labelwrapped";
        public static final String LEGEND = "legend";
        public static final String RUBYANNOTATION = "rubyannotation";
        public static final String TABLECAPTION = "tablecaption";
        public static final String TITLE = "title";
        public static final String OTHER = "other";
    }
    /**
     * A single source for a computed AX property.
     */
    public static final class AXValueSource extends CdpObject {
        private AXValueSource(Map<String, Object> values) { super(values); }
        @Nullable public static AXValueSource fromMap(@Nullable Map<String, Object> values) {
            return values == null ? null : new AXValueSource(values);
        }
        public static Builder builder() { return new Builder(); }
        /**
         * What type of source this is.
         * @return the protocol field value
         */
        @Nullable public String type() {
            return (String) value("type");
        }
        /**
         * The value of this property source.
         * @return the protocol field value
         */
        @Nullable public Accessibility.AXValue value() {
            return Accessibility.AXValue.fromMap(objectMap(value("value")));
        }
        /**
         * The name of the relevant attribute, if any.
         * @return the protocol field value
         */
        @Nullable public String attribute() {
            return (String) value("attribute");
        }
        /**
         * The value of the relevant attribute, if any.
         * @return the protocol field value
         */
        @Nullable public Accessibility.AXValue attributeValue() {
            return Accessibility.AXValue.fromMap(objectMap(value("attributeValue")));
        }
        /**
         * Whether this source is superseded by a higher priority source.
         * @return the protocol field value
         */
        @Nullable public Boolean superseded() {
            return (Boolean) value("superseded");
        }
        /**
         * The native markup source for this value, e.g. a {@code &lt;label&gt;} element.
         * @return the protocol field value
         */
        @Nullable public String nativeSource() {
            return (String) value("nativeSource");
        }
        /**
         * The value, such as a node or node list, of the native source.
         * @return the protocol field value
         */
        @Nullable public Accessibility.AXValue nativeSourceValue() {
            return Accessibility.AXValue.fromMap(objectMap(value("nativeSourceValue")));
        }
        /**
         * Whether the value for this property is invalid.
         * @return the protocol field value
         */
        @Nullable public Boolean invalid() {
            return (Boolean) value("invalid");
        }
        /**
         * Reason for the value being invalid, if it is.
         * @return the protocol field value
         */
        @Nullable public String invalidReason() {
            return (String) value("invalidReason");
        }
        public static final class Builder {
            private final Map<String, Object> values = new LinkedHashMap<>();
            /**
             * What type of source this is.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder type(@Nullable String value) {
                if (value == null) values.remove("type");
                else values.put("type", jsonValue(value));
                return this;
            }
            /**
             * The value of this property source.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder value(@Nullable Accessibility.AXValue value) {
                if (value == null) values.remove("value");
                else values.put("value", jsonValue(value));
                return this;
            }
            /**
             * The name of the relevant attribute, if any.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder attribute(@Nullable String value) {
                if (value == null) values.remove("attribute");
                else values.put("attribute", jsonValue(value));
                return this;
            }
            /**
             * The value of the relevant attribute, if any.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder attributeValue(@Nullable Accessibility.AXValue value) {
                if (value == null) values.remove("attributeValue");
                else values.put("attributeValue", jsonValue(value));
                return this;
            }
            /**
             * Whether this source is superseded by a higher priority source.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder superseded(@Nullable Boolean value) {
                if (value == null) values.remove("superseded");
                else values.put("superseded", jsonValue(value));
                return this;
            }
            /**
             * The native markup source for this value, e.g. a {@code &lt;label&gt;} element.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder nativeSource(@Nullable String value) {
                if (value == null) values.remove("nativeSource");
                else values.put("nativeSource", jsonValue(value));
                return this;
            }
            /**
             * The value, such as a node or node list, of the native source.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder nativeSourceValue(@Nullable Accessibility.AXValue value) {
                if (value == null) values.remove("nativeSourceValue");
                else values.put("nativeSourceValue", jsonValue(value));
                return this;
            }
            /**
             * Whether the value for this property is invalid.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder invalid(@Nullable Boolean value) {
                if (value == null) values.remove("invalid");
                else values.put("invalid", jsonValue(value));
                return this;
            }
            /**
             * Reason for the value being invalid, if it is.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder invalidReason(@Nullable String value) {
                if (value == null) values.remove("invalidReason");
                else values.put("invalidReason", jsonValue(value));
                return this;
            }
            public AXValueSource build() {
                if (!values.containsKey("type")) throw new IllegalStateException("Missing required CDP field: type");
                return new AXValueSource(values);
            }
        }
    }
    /**
     */
    public static final class AXRelatedNode extends CdpObject {
        private AXRelatedNode(Map<String, Object> values) { super(values); }
        @Nullable public static AXRelatedNode fromMap(@Nullable Map<String, Object> values) {
            return values == null ? null : new AXRelatedNode(values);
        }
        public static Builder builder() { return new Builder(); }
        /**
         * The BackendNodeId of the related DOM node.
         * @return the protocol field value
         */
        @Nullable public Long backendDOMNodeId() {
            return numberAsLong(value("backendDOMNodeId"));
        }
        /**
         * The IDRef value provided, if any.
         * @return the protocol field value
         */
        @Nullable public String idref() {
            return (String) value("idref");
        }
        /**
         * The text alternative of this node in the current context.
         * @return the protocol field value
         */
        @Nullable public String text() {
            return (String) value("text");
        }
        public static final class Builder {
            private final Map<String, Object> values = new LinkedHashMap<>();
            /**
             * The BackendNodeId of the related DOM node.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder backendDOMNodeId(@Nullable Long value) {
                if (value == null) values.remove("backendDOMNodeId");
                else values.put("backendDOMNodeId", jsonValue(value));
                return this;
            }
            /**
             * The IDRef value provided, if any.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder idref(@Nullable String value) {
                if (value == null) values.remove("idref");
                else values.put("idref", jsonValue(value));
                return this;
            }
            /**
             * The text alternative of this node in the current context.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder text(@Nullable String value) {
                if (value == null) values.remove("text");
                else values.put("text", jsonValue(value));
                return this;
            }
            public AXRelatedNode build() {
                if (!values.containsKey("backendDOMNodeId")) throw new IllegalStateException("Missing required CDP field: backendDOMNodeId");
                return new AXRelatedNode(values);
            }
        }
    }
    /**
     */
    public static final class AXProperty extends CdpObject {
        private AXProperty(Map<String, Object> values) { super(values); }
        @Nullable public static AXProperty fromMap(@Nullable Map<String, Object> values) {
            return values == null ? null : new AXProperty(values);
        }
        public static Builder builder() { return new Builder(); }
        /**
         * The name of this property.
         * @return the protocol field value
         */
        @Nullable public String name() {
            return (String) value("name");
        }
        /**
         * The value of this property.
         * @return the protocol field value
         */
        @Nullable public Accessibility.AXValue value() {
            return Accessibility.AXValue.fromMap(objectMap(value("value")));
        }
        public static final class Builder {
            private final Map<String, Object> values = new LinkedHashMap<>();
            /**
             * The name of this property.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder name(@Nullable String value) {
                if (value == null) values.remove("name");
                else values.put("name", jsonValue(value));
                return this;
            }
            /**
             * The value of this property.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder value(@Nullable Accessibility.AXValue value) {
                if (value == null) values.remove("value");
                else values.put("value", jsonValue(value));
                return this;
            }
            public AXProperty build() {
                if (!values.containsKey("name")) throw new IllegalStateException("Missing required CDP field: name");
                if (!values.containsKey("value")) throw new IllegalStateException("Missing required CDP field: value");
                return new AXProperty(values);
            }
        }
    }
    /**
     * A single computed AX property.
     */
    public static final class AXValue extends CdpObject {
        private AXValue(Map<String, Object> values) { super(values); }
        @Nullable public static AXValue fromMap(@Nullable Map<String, Object> values) {
            return values == null ? null : new AXValue(values);
        }
        public static Builder builder() { return new Builder(); }
        /**
         * The type of this value.
         * @return the protocol field value
         */
        @Nullable public String type() {
            return (String) value("type");
        }
        /**
         * The computed value of this property.
         * @return the protocol field value
         */
        @Nullable public Object value() {
            return value("value");
        }
        /**
         * One or more related nodes, if applicable.
         * @return the protocol field value
         */
        @Nullable public java.util.List<Accessibility.AXRelatedNode> relatedNodes() {
            return list(value("relatedNodes"), element0 -> Accessibility.AXRelatedNode.fromMap(objectMap(element0)));
        }
        /**
         * The sources which contributed to the computation of this property.
         * @return the protocol field value
         */
        @Nullable public java.util.List<Accessibility.AXValueSource> sources() {
            return list(value("sources"), element0 -> Accessibility.AXValueSource.fromMap(objectMap(element0)));
        }
        public static final class Builder {
            private final Map<String, Object> values = new LinkedHashMap<>();
            /**
             * The type of this value.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder type(@Nullable String value) {
                if (value == null) values.remove("type");
                else values.put("type", jsonValue(value));
                return this;
            }
            /**
             * The computed value of this property.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder value(@Nullable Object value) {
                if (value == null) values.remove("value");
                else values.put("value", jsonValue(value));
                return this;
            }
            /**
             * One or more related nodes, if applicable.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder relatedNodes(@Nullable java.util.List<Accessibility.AXRelatedNode> value) {
                if (value == null) values.remove("relatedNodes");
                else values.put("relatedNodes", jsonValue(value));
                return this;
            }
            /**
             * The sources which contributed to the computation of this property.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder sources(@Nullable java.util.List<Accessibility.AXValueSource> value) {
                if (value == null) values.remove("sources");
                else values.put("sources", jsonValue(value));
                return this;
            }
            public AXValue build() {
                if (!values.containsKey("type")) throw new IllegalStateException("Missing required CDP field: type");
                return new AXValue(values);
            }
        }
    }
    /**
     * Values of AXProperty name: - from &#x27;busy&#x27; to &#x27;roledescription&#x27;: states which apply to every AX node - from &#x27;live&#x27; to &#x27;root&#x27;: attributes which apply to nodes in live regions - from &#x27;autocomplete&#x27; to &#x27;valuetext&#x27;: attributes which apply to widgets - from &#x27;checked&#x27; to &#x27;selected&#x27;: states which apply to widgets - from &#x27;activedescendant&#x27; to &#x27;owns&#x27;: relationships between elements other than parent/child/sibling - from &#x27;activeFullscreenElement&#x27; to &#x27;uninteresting&#x27;: reasons why this noode is hidden
     */
    public static final class AXPropertyName {
        private AXPropertyName() {}
        public static final String ACTIONS = "actions";
        public static final String BUSY = "busy";
        public static final String DISABLED = "disabled";
        public static final String EDITABLE = "editable";
        public static final String FOCUSABLE = "focusable";
        public static final String FOCUSED = "focused";
        public static final String HIDDEN = "hidden";
        public static final String HIDDENROOT = "hiddenRoot";
        public static final String INVALID = "invalid";
        public static final String KEYSHORTCUTS = "keyshortcuts";
        public static final String SETTABLE = "settable";
        public static final String ROLEDESCRIPTION = "roledescription";
        public static final String LIVE = "live";
        public static final String ATOMIC = "atomic";
        public static final String RELEVANT = "relevant";
        public static final String ROOT = "root";
        public static final String AUTOCOMPLETE = "autocomplete";
        public static final String HASPOPUP = "hasPopup";
        public static final String LEVEL = "level";
        public static final String MULTISELECTABLE = "multiselectable";
        public static final String ORIENTATION = "orientation";
        public static final String MULTILINE = "multiline";
        public static final String READONLY = "readonly";
        public static final String REQUIRED = "required";
        public static final String VALUEMIN = "valuemin";
        public static final String VALUEMAX = "valuemax";
        public static final String VALUETEXT = "valuetext";
        public static final String CHECKED = "checked";
        public static final String EXPANDED = "expanded";
        public static final String MODAL = "modal";
        public static final String PRESSED = "pressed";
        public static final String SELECTED = "selected";
        public static final String ACTIVEDESCENDANT = "activedescendant";
        public static final String CONTROLS = "controls";
        public static final String DESCRIBEDBY = "describedby";
        public static final String DETAILS = "details";
        public static final String ERRORMESSAGE = "errormessage";
        public static final String FLOWTO = "flowto";
        public static final String LABELLEDBY = "labelledby";
        public static final String OWNS = "owns";
        public static final String URL = "url";
        public static final String ACTIVEFULLSCREENELEMENT = "activeFullscreenElement";
        public static final String ACTIVEMODALDIALOG = "activeModalDialog";
        public static final String ACTIVEARIAMODALDIALOG = "activeAriaModalDialog";
        public static final String ARIAHIDDENELEMENT = "ariaHiddenElement";
        public static final String ARIAHIDDENSUBTREE = "ariaHiddenSubtree";
        public static final String EMPTYALT = "emptyAlt";
        public static final String EMPTYTEXT = "emptyText";
        public static final String INERTELEMENT = "inertElement";
        public static final String INERTSUBTREE = "inertSubtree";
        public static final String LABELCONTAINER = "labelContainer";
        public static final String LABELFOR = "labelFor";
        public static final String NOTRENDERED = "notRendered";
        public static final String NOTVISIBLE = "notVisible";
        public static final String PRESENTATIONALROLE = "presentationalRole";
        public static final String PROBABLYPRESENTATIONAL = "probablyPresentational";
        public static final String INACTIVECAROUSELTABCONTENT = "inactiveCarouselTabContent";
        public static final String UNINTERESTING = "uninteresting";
    }
    /**
     * A node in the accessibility tree.
     */
    public static final class AXNode extends CdpObject {
        private AXNode(Map<String, Object> values) { super(values); }
        @Nullable public static AXNode fromMap(@Nullable Map<String, Object> values) {
            return values == null ? null : new AXNode(values);
        }
        public static Builder builder() { return new Builder(); }
        /**
         * Unique identifier for this node.
         * @return the protocol field value
         */
        @Nullable public String nodeId() {
            return (String) value("nodeId");
        }
        /**
         * Whether this node is ignored for accessibility
         * @return the protocol field value
         */
        @Nullable public Boolean ignored() {
            return (Boolean) value("ignored");
        }
        /**
         * Collection of reasons why this node is hidden.
         * @return the protocol field value
         */
        @Nullable public java.util.List<Accessibility.AXProperty> ignoredReasons() {
            return list(value("ignoredReasons"), element0 -> Accessibility.AXProperty.fromMap(objectMap(element0)));
        }
        /**
         * This {@code Node}&#x27;s role, whether explicit or implicit.
         * @return the protocol field value
         */
        @Nullable public Accessibility.AXValue role() {
            return Accessibility.AXValue.fromMap(objectMap(value("role")));
        }
        /**
         * This {@code Node}&#x27;s Chrome raw role.
         * @return the protocol field value
         */
        @Nullable public Accessibility.AXValue chromeRole() {
            return Accessibility.AXValue.fromMap(objectMap(value("chromeRole")));
        }
        /**
         * The accessible name for this {@code Node}.
         * @return the protocol field value
         */
        @Nullable public Accessibility.AXValue name() {
            return Accessibility.AXValue.fromMap(objectMap(value("name")));
        }
        /**
         * The accessible description for this {@code Node}.
         * @return the protocol field value
         */
        @Nullable public Accessibility.AXValue description() {
            return Accessibility.AXValue.fromMap(objectMap(value("description")));
        }
        /**
         * The value for this {@code Node}.
         * @return the protocol field value
         */
        @Nullable public Accessibility.AXValue value() {
            return Accessibility.AXValue.fromMap(objectMap(value("value")));
        }
        /**
         * All other properties
         * @return the protocol field value
         */
        @Nullable public java.util.List<Accessibility.AXProperty> properties() {
            return list(value("properties"), element0 -> Accessibility.AXProperty.fromMap(objectMap(element0)));
        }
        /**
         * ID for this node&#x27;s parent.
         * @return the protocol field value
         */
        @Nullable public String parentId() {
            return (String) value("parentId");
        }
        /**
         * IDs for each of this node&#x27;s child nodes.
         * @return the protocol field value
         */
        @Nullable public java.util.List<String> childIds() {
            return list(value("childIds"), element0 -> (String) element0);
        }
        /**
         * The backend ID for the associated DOM node, if any.
         * @return the protocol field value
         */
        @Nullable public Long backendDOMNodeId() {
            return numberAsLong(value("backendDOMNodeId"));
        }
        /**
         * The frame ID for the frame associated with this nodes document.
         * @return the protocol field value
         */
        @Nullable public String frameId() {
            return (String) value("frameId");
        }
        public static final class Builder {
            private final Map<String, Object> values = new LinkedHashMap<>();
            /**
             * Unique identifier for this node.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder nodeId(@Nullable String value) {
                if (value == null) values.remove("nodeId");
                else values.put("nodeId", jsonValue(value));
                return this;
            }
            /**
             * Whether this node is ignored for accessibility
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder ignored(@Nullable Boolean value) {
                if (value == null) values.remove("ignored");
                else values.put("ignored", jsonValue(value));
                return this;
            }
            /**
             * Collection of reasons why this node is hidden.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder ignoredReasons(@Nullable java.util.List<Accessibility.AXProperty> value) {
                if (value == null) values.remove("ignoredReasons");
                else values.put("ignoredReasons", jsonValue(value));
                return this;
            }
            /**
             * This {@code Node}&#x27;s role, whether explicit or implicit.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder role(@Nullable Accessibility.AXValue value) {
                if (value == null) values.remove("role");
                else values.put("role", jsonValue(value));
                return this;
            }
            /**
             * This {@code Node}&#x27;s Chrome raw role.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder chromeRole(@Nullable Accessibility.AXValue value) {
                if (value == null) values.remove("chromeRole");
                else values.put("chromeRole", jsonValue(value));
                return this;
            }
            /**
             * The accessible name for this {@code Node}.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder name(@Nullable Accessibility.AXValue value) {
                if (value == null) values.remove("name");
                else values.put("name", jsonValue(value));
                return this;
            }
            /**
             * The accessible description for this {@code Node}.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder description(@Nullable Accessibility.AXValue value) {
                if (value == null) values.remove("description");
                else values.put("description", jsonValue(value));
                return this;
            }
            /**
             * The value for this {@code Node}.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder value(@Nullable Accessibility.AXValue value) {
                if (value == null) values.remove("value");
                else values.put("value", jsonValue(value));
                return this;
            }
            /**
             * All other properties
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder properties(@Nullable java.util.List<Accessibility.AXProperty> value) {
                if (value == null) values.remove("properties");
                else values.put("properties", jsonValue(value));
                return this;
            }
            /**
             * ID for this node&#x27;s parent.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder parentId(@Nullable String value) {
                if (value == null) values.remove("parentId");
                else values.put("parentId", jsonValue(value));
                return this;
            }
            /**
             * IDs for each of this node&#x27;s child nodes.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder childIds(@Nullable java.util.List<String> value) {
                if (value == null) values.remove("childIds");
                else values.put("childIds", jsonValue(value));
                return this;
            }
            /**
             * The backend ID for the associated DOM node, if any.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder backendDOMNodeId(@Nullable Long value) {
                if (value == null) values.remove("backendDOMNodeId");
                else values.put("backendDOMNodeId", jsonValue(value));
                return this;
            }
            /**
             * The frame ID for the frame associated with this nodes document.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder frameId(@Nullable String value) {
                if (value == null) values.remove("frameId");
                else values.put("frameId", jsonValue(value));
                return this;
            }
            public AXNode build() {
                if (!values.containsKey("nodeId")) throw new IllegalStateException("Missing required CDP field: nodeId");
                if (!values.containsKey("ignored")) throw new IllegalStateException("Missing required CDP field: ignored");
                return new AXNode(values);
            }
        }
    }
    /**
     * Disables the accessibility domain.
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
     * Disables the accessibility domain.
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
     * Enables the accessibility domain which causes {@code AXNodeId}s to remain consistent between method calls. This turns on accessibility for the page, which can impact performance until accessibility is disabled.
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
     * Enables the accessibility domain which causes {@code AXNodeId}s to remain consistent between method calls. This turns on accessibility for the page, which can impact performance until accessibility is disabled.
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
     * Fetches the accessibility node and partial accessibility tree for this DOM node, if it exists.
     * <p><b>Experimental:</b> this part of CDP may change without notice.
     */
    public static final class GetPartialAXTreeParams extends CdpObject {
        private GetPartialAXTreeParams(Map<String, Object> values) { super(values); }
        @Nullable public static GetPartialAXTreeParams fromMap(@Nullable Map<String, Object> values) {
            return values == null ? null : new GetPartialAXTreeParams(values);
        }
        public static Builder builder() { return new Builder(); }
        /**
         * Identifier of the node to get the partial accessibility tree for.
         * @return the protocol field value
         */
        @Nullable public Long nodeId() {
            return numberAsLong(value("nodeId"));
        }
        /**
         * Identifier of the backend node to get the partial accessibility tree for.
         * @return the protocol field value
         */
        @Nullable public Long backendNodeId() {
            return numberAsLong(value("backendNodeId"));
        }
        /**
         * JavaScript object id of the node wrapper to get the partial accessibility tree for.
         * @return the protocol field value
         */
        @Nullable public String objectId() {
            return (String) value("objectId");
        }
        /**
         * Whether to fetch this node&#x27;s ancestors, siblings and children. Defaults to true.
         * @return the protocol field value
         */
        @Nullable public Boolean fetchRelatives() {
            return (Boolean) value("fetchRelatives");
        }
        public static final class Builder {
            private final Map<String, Object> values = new LinkedHashMap<>();
            /**
             * Identifier of the node to get the partial accessibility tree for.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder nodeId(@Nullable Long value) {
                if (value == null) values.remove("nodeId");
                else values.put("nodeId", jsonValue(value));
                return this;
            }
            /**
             * Identifier of the backend node to get the partial accessibility tree for.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder backendNodeId(@Nullable Long value) {
                if (value == null) values.remove("backendNodeId");
                else values.put("backendNodeId", jsonValue(value));
                return this;
            }
            /**
             * JavaScript object id of the node wrapper to get the partial accessibility tree for.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder objectId(@Nullable String value) {
                if (value == null) values.remove("objectId");
                else values.put("objectId", jsonValue(value));
                return this;
            }
            /**
             * Whether to fetch this node&#x27;s ancestors, siblings and children. Defaults to true.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder fetchRelatives(@Nullable Boolean value) {
                if (value == null) values.remove("fetchRelatives");
                else values.put("fetchRelatives", jsonValue(value));
                return this;
            }
            public GetPartialAXTreeParams build() {
                return new GetPartialAXTreeParams(values);
            }
        }
    }
    /**
     * Fetches the accessibility node and partial accessibility tree for this DOM node, if it exists.
     * <p><b>Experimental:</b> this part of CDP may change without notice.
     */
    public static final class GetPartialAXTreeResult extends CdpObject {
        private GetPartialAXTreeResult(Map<String, Object> values) { super(values); }
        @Nullable public static GetPartialAXTreeResult fromMap(@Nullable Map<String, Object> values) {
            return values == null ? null : new GetPartialAXTreeResult(values);
        }
        public static Builder builder() { return new Builder(); }
        /**
         * The {@code Accessibility.AXNode} for this DOM node, if it exists, plus its ancestors, siblings and children, if requested.
         * @return the protocol field value
         */
        @Nullable public java.util.List<Accessibility.AXNode> nodes() {
            return list(value("nodes"), element0 -> Accessibility.AXNode.fromMap(objectMap(element0)));
        }
        public static final class Builder {
            private final Map<String, Object> values = new LinkedHashMap<>();
            /**
             * The {@code Accessibility.AXNode} for this DOM node, if it exists, plus its ancestors, siblings and children, if requested.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder nodes(@Nullable java.util.List<Accessibility.AXNode> value) {
                if (value == null) values.remove("nodes");
                else values.put("nodes", jsonValue(value));
                return this;
            }
            public GetPartialAXTreeResult build() {
                if (!values.containsKey("nodes")) throw new IllegalStateException("Missing required CDP field: nodes");
                return new GetPartialAXTreeResult(values);
            }
        }
    }
    /**
     * Fetches the entire accessibility tree for the root Document
     * <p><b>Experimental:</b> this part of CDP may change without notice.
     */
    public static final class GetFullAXTreeParams extends CdpObject {
        private GetFullAXTreeParams(Map<String, Object> values) { super(values); }
        @Nullable public static GetFullAXTreeParams fromMap(@Nullable Map<String, Object> values) {
            return values == null ? null : new GetFullAXTreeParams(values);
        }
        public static Builder builder() { return new Builder(); }
        /**
         * The maximum depth at which descendants of the root node should be retrieved. If omitted, the full tree is returned.
         * @return the protocol field value
         */
        @Nullable public Long depth() {
            return numberAsLong(value("depth"));
        }
        /**
         * The frame for whose document the AX tree should be retrieved. If omitted, the root frame is used.
         * @return the protocol field value
         */
        @Nullable public String frameId() {
            return (String) value("frameId");
        }
        public static final class Builder {
            private final Map<String, Object> values = new LinkedHashMap<>();
            /**
             * The maximum depth at which descendants of the root node should be retrieved. If omitted, the full tree is returned.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder depth(@Nullable Long value) {
                if (value == null) values.remove("depth");
                else values.put("depth", jsonValue(value));
                return this;
            }
            /**
             * The frame for whose document the AX tree should be retrieved. If omitted, the root frame is used.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder frameId(@Nullable String value) {
                if (value == null) values.remove("frameId");
                else values.put("frameId", jsonValue(value));
                return this;
            }
            public GetFullAXTreeParams build() {
                return new GetFullAXTreeParams(values);
            }
        }
    }
    /**
     * Fetches the entire accessibility tree for the root Document
     * <p><b>Experimental:</b> this part of CDP may change without notice.
     */
    public static final class GetFullAXTreeResult extends CdpObject {
        private GetFullAXTreeResult(Map<String, Object> values) { super(values); }
        @Nullable public static GetFullAXTreeResult fromMap(@Nullable Map<String, Object> values) {
            return values == null ? null : new GetFullAXTreeResult(values);
        }
        public static Builder builder() { return new Builder(); }
        /**
         * Returns the nodes field.
         * @return the protocol field value
         */
        @Nullable public java.util.List<Accessibility.AXNode> nodes() {
            return list(value("nodes"), element0 -> Accessibility.AXNode.fromMap(objectMap(element0)));
        }
        public static final class Builder {
            private final Map<String, Object> values = new LinkedHashMap<>();
            /**
             * Sets the nodes field.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder nodes(@Nullable java.util.List<Accessibility.AXNode> value) {
                if (value == null) values.remove("nodes");
                else values.put("nodes", jsonValue(value));
                return this;
            }
            public GetFullAXTreeResult build() {
                if (!values.containsKey("nodes")) throw new IllegalStateException("Missing required CDP field: nodes");
                return new GetFullAXTreeResult(values);
            }
        }
    }
    /**
     * Fetches the root node. Requires {@code enable()} to have been called previously.
     * <p><b>Experimental:</b> this part of CDP may change without notice.
     */
    public static final class GetRootAXNodeParams extends CdpObject {
        private GetRootAXNodeParams(Map<String, Object> values) { super(values); }
        @Nullable public static GetRootAXNodeParams fromMap(@Nullable Map<String, Object> values) {
            return values == null ? null : new GetRootAXNodeParams(values);
        }
        public static Builder builder() { return new Builder(); }
        /**
         * The frame in whose document the node resides. If omitted, the root frame is used.
         * @return the protocol field value
         */
        @Nullable public String frameId() {
            return (String) value("frameId");
        }
        public static final class Builder {
            private final Map<String, Object> values = new LinkedHashMap<>();
            /**
             * The frame in whose document the node resides. If omitted, the root frame is used.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder frameId(@Nullable String value) {
                if (value == null) values.remove("frameId");
                else values.put("frameId", jsonValue(value));
                return this;
            }
            public GetRootAXNodeParams build() {
                return new GetRootAXNodeParams(values);
            }
        }
    }
    /**
     * Fetches the root node. Requires {@code enable()} to have been called previously.
     * <p><b>Experimental:</b> this part of CDP may change without notice.
     */
    public static final class GetRootAXNodeResult extends CdpObject {
        private GetRootAXNodeResult(Map<String, Object> values) { super(values); }
        @Nullable public static GetRootAXNodeResult fromMap(@Nullable Map<String, Object> values) {
            return values == null ? null : new GetRootAXNodeResult(values);
        }
        public static Builder builder() { return new Builder(); }
        /**
         * Returns the node field.
         * @return the protocol field value
         */
        @Nullable public Accessibility.AXNode node() {
            return Accessibility.AXNode.fromMap(objectMap(value("node")));
        }
        public static final class Builder {
            private final Map<String, Object> values = new LinkedHashMap<>();
            /**
             * Sets the node field.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder node(@Nullable Accessibility.AXNode value) {
                if (value == null) values.remove("node");
                else values.put("node", jsonValue(value));
                return this;
            }
            public GetRootAXNodeResult build() {
                if (!values.containsKey("node")) throw new IllegalStateException("Missing required CDP field: node");
                return new GetRootAXNodeResult(values);
            }
        }
    }
    /**
     * Fetches a node and all ancestors up to and including the root. Requires {@code enable()} to have been called previously.
     * <p><b>Experimental:</b> this part of CDP may change without notice.
     */
    public static final class GetAXNodeAndAncestorsParams extends CdpObject {
        private GetAXNodeAndAncestorsParams(Map<String, Object> values) { super(values); }
        @Nullable public static GetAXNodeAndAncestorsParams fromMap(@Nullable Map<String, Object> values) {
            return values == null ? null : new GetAXNodeAndAncestorsParams(values);
        }
        public static Builder builder() { return new Builder(); }
        /**
         * Identifier of the node to get.
         * @return the protocol field value
         */
        @Nullable public Long nodeId() {
            return numberAsLong(value("nodeId"));
        }
        /**
         * Identifier of the backend node to get.
         * @return the protocol field value
         */
        @Nullable public Long backendNodeId() {
            return numberAsLong(value("backendNodeId"));
        }
        /**
         * JavaScript object id of the node wrapper to get.
         * @return the protocol field value
         */
        @Nullable public String objectId() {
            return (String) value("objectId");
        }
        public static final class Builder {
            private final Map<String, Object> values = new LinkedHashMap<>();
            /**
             * Identifier of the node to get.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder nodeId(@Nullable Long value) {
                if (value == null) values.remove("nodeId");
                else values.put("nodeId", jsonValue(value));
                return this;
            }
            /**
             * Identifier of the backend node to get.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder backendNodeId(@Nullable Long value) {
                if (value == null) values.remove("backendNodeId");
                else values.put("backendNodeId", jsonValue(value));
                return this;
            }
            /**
             * JavaScript object id of the node wrapper to get.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder objectId(@Nullable String value) {
                if (value == null) values.remove("objectId");
                else values.put("objectId", jsonValue(value));
                return this;
            }
            public GetAXNodeAndAncestorsParams build() {
                return new GetAXNodeAndAncestorsParams(values);
            }
        }
    }
    /**
     * Fetches a node and all ancestors up to and including the root. Requires {@code enable()} to have been called previously.
     * <p><b>Experimental:</b> this part of CDP may change without notice.
     */
    public static final class GetAXNodeAndAncestorsResult extends CdpObject {
        private GetAXNodeAndAncestorsResult(Map<String, Object> values) { super(values); }
        @Nullable public static GetAXNodeAndAncestorsResult fromMap(@Nullable Map<String, Object> values) {
            return values == null ? null : new GetAXNodeAndAncestorsResult(values);
        }
        public static Builder builder() { return new Builder(); }
        /**
         * Returns the nodes field.
         * @return the protocol field value
         */
        @Nullable public java.util.List<Accessibility.AXNode> nodes() {
            return list(value("nodes"), element0 -> Accessibility.AXNode.fromMap(objectMap(element0)));
        }
        public static final class Builder {
            private final Map<String, Object> values = new LinkedHashMap<>();
            /**
             * Sets the nodes field.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder nodes(@Nullable java.util.List<Accessibility.AXNode> value) {
                if (value == null) values.remove("nodes");
                else values.put("nodes", jsonValue(value));
                return this;
            }
            public GetAXNodeAndAncestorsResult build() {
                if (!values.containsKey("nodes")) throw new IllegalStateException("Missing required CDP field: nodes");
                return new GetAXNodeAndAncestorsResult(values);
            }
        }
    }
    /**
     * Fetches a particular accessibility node by AXNodeId. Requires {@code enable()} to have been called previously.
     * <p><b>Experimental:</b> this part of CDP may change without notice.
     */
    public static final class GetChildAXNodesParams extends CdpObject {
        private GetChildAXNodesParams(Map<String, Object> values) { super(values); }
        @Nullable public static GetChildAXNodesParams fromMap(@Nullable Map<String, Object> values) {
            return values == null ? null : new GetChildAXNodesParams(values);
        }
        public static Builder builder() { return new Builder(); }
        /**
         * Returns the id field.
         * @return the protocol field value
         */
        @Nullable public String id() {
            return (String) value("id");
        }
        /**
         * The frame in whose document the node resides. If omitted, the root frame is used.
         * @return the protocol field value
         */
        @Nullable public String frameId() {
            return (String) value("frameId");
        }
        public static final class Builder {
            private final Map<String, Object> values = new LinkedHashMap<>();
            /**
             * Sets the id field.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder id(@Nullable String value) {
                if (value == null) values.remove("id");
                else values.put("id", jsonValue(value));
                return this;
            }
            /**
             * The frame in whose document the node resides. If omitted, the root frame is used.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder frameId(@Nullable String value) {
                if (value == null) values.remove("frameId");
                else values.put("frameId", jsonValue(value));
                return this;
            }
            public GetChildAXNodesParams build() {
                if (!values.containsKey("id")) throw new IllegalStateException("Missing required CDP field: id");
                return new GetChildAXNodesParams(values);
            }
        }
    }
    /**
     * Fetches a particular accessibility node by AXNodeId. Requires {@code enable()} to have been called previously.
     * <p><b>Experimental:</b> this part of CDP may change without notice.
     */
    public static final class GetChildAXNodesResult extends CdpObject {
        private GetChildAXNodesResult(Map<String, Object> values) { super(values); }
        @Nullable public static GetChildAXNodesResult fromMap(@Nullable Map<String, Object> values) {
            return values == null ? null : new GetChildAXNodesResult(values);
        }
        public static Builder builder() { return new Builder(); }
        /**
         * Returns the nodes field.
         * @return the protocol field value
         */
        @Nullable public java.util.List<Accessibility.AXNode> nodes() {
            return list(value("nodes"), element0 -> Accessibility.AXNode.fromMap(objectMap(element0)));
        }
        public static final class Builder {
            private final Map<String, Object> values = new LinkedHashMap<>();
            /**
             * Sets the nodes field.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder nodes(@Nullable java.util.List<Accessibility.AXNode> value) {
                if (value == null) values.remove("nodes");
                else values.put("nodes", jsonValue(value));
                return this;
            }
            public GetChildAXNodesResult build() {
                if (!values.containsKey("nodes")) throw new IllegalStateException("Missing required CDP field: nodes");
                return new GetChildAXNodesResult(values);
            }
        }
    }
    /**
     * Query a DOM node&#x27;s accessibility subtree for accessible name and role. This command computes the name and role for all nodes in the subtree, including those that are ignored for accessibility, and returns those that match the specified name and role. If no DOM node is specified, or the DOM node does not exist, the command returns an error. If neither {@code accessibleName} or {@code role} is specified, it returns all the accessibility nodes in the subtree.
     * <p><b>Experimental:</b> this part of CDP may change without notice.
     */
    public static final class QueryAXTreeParams extends CdpObject {
        private QueryAXTreeParams(Map<String, Object> values) { super(values); }
        @Nullable public static QueryAXTreeParams fromMap(@Nullable Map<String, Object> values) {
            return values == null ? null : new QueryAXTreeParams(values);
        }
        public static Builder builder() { return new Builder(); }
        /**
         * Identifier of the node for the root to query.
         * @return the protocol field value
         */
        @Nullable public Long nodeId() {
            return numberAsLong(value("nodeId"));
        }
        /**
         * Identifier of the backend node for the root to query.
         * @return the protocol field value
         */
        @Nullable public Long backendNodeId() {
            return numberAsLong(value("backendNodeId"));
        }
        /**
         * JavaScript object id of the node wrapper for the root to query.
         * @return the protocol field value
         */
        @Nullable public String objectId() {
            return (String) value("objectId");
        }
        /**
         * Find nodes with this computed name.
         * @return the protocol field value
         */
        @Nullable public String accessibleName() {
            return (String) value("accessibleName");
        }
        /**
         * Find nodes with this computed role.
         * @return the protocol field value
         */
        @Nullable public String role() {
            return (String) value("role");
        }
        public static final class Builder {
            private final Map<String, Object> values = new LinkedHashMap<>();
            /**
             * Identifier of the node for the root to query.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder nodeId(@Nullable Long value) {
                if (value == null) values.remove("nodeId");
                else values.put("nodeId", jsonValue(value));
                return this;
            }
            /**
             * Identifier of the backend node for the root to query.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder backendNodeId(@Nullable Long value) {
                if (value == null) values.remove("backendNodeId");
                else values.put("backendNodeId", jsonValue(value));
                return this;
            }
            /**
             * JavaScript object id of the node wrapper for the root to query.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder objectId(@Nullable String value) {
                if (value == null) values.remove("objectId");
                else values.put("objectId", jsonValue(value));
                return this;
            }
            /**
             * Find nodes with this computed name.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder accessibleName(@Nullable String value) {
                if (value == null) values.remove("accessibleName");
                else values.put("accessibleName", jsonValue(value));
                return this;
            }
            /**
             * Find nodes with this computed role.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder role(@Nullable String value) {
                if (value == null) values.remove("role");
                else values.put("role", jsonValue(value));
                return this;
            }
            public QueryAXTreeParams build() {
                return new QueryAXTreeParams(values);
            }
        }
    }
    /**
     * Query a DOM node&#x27;s accessibility subtree for accessible name and role. This command computes the name and role for all nodes in the subtree, including those that are ignored for accessibility, and returns those that match the specified name and role. If no DOM node is specified, or the DOM node does not exist, the command returns an error. If neither {@code accessibleName} or {@code role} is specified, it returns all the accessibility nodes in the subtree.
     * <p><b>Experimental:</b> this part of CDP may change without notice.
     */
    public static final class QueryAXTreeResult extends CdpObject {
        private QueryAXTreeResult(Map<String, Object> values) { super(values); }
        @Nullable public static QueryAXTreeResult fromMap(@Nullable Map<String, Object> values) {
            return values == null ? null : new QueryAXTreeResult(values);
        }
        public static Builder builder() { return new Builder(); }
        /**
         * A list of {@code Accessibility.AXNode} matching the specified attributes, including nodes that are ignored for accessibility.
         * @return the protocol field value
         */
        @Nullable public java.util.List<Accessibility.AXNode> nodes() {
            return list(value("nodes"), element0 -> Accessibility.AXNode.fromMap(objectMap(element0)));
        }
        public static final class Builder {
            private final Map<String, Object> values = new LinkedHashMap<>();
            /**
             * A list of {@code Accessibility.AXNode} matching the specified attributes, including nodes that are ignored for accessibility.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder nodes(@Nullable java.util.List<Accessibility.AXNode> value) {
                if (value == null) values.remove("nodes");
                else values.put("nodes", jsonValue(value));
                return this;
            }
            public QueryAXTreeResult build() {
                if (!values.containsKey("nodes")) throw new IllegalStateException("Missing required CDP field: nodes");
                return new QueryAXTreeResult(values);
            }
        }
    }
    /**
     * The loadComplete event mirrors the load complete event sent by the browser to assistive technology when the web page has finished loading.
     * <p><b>Experimental:</b> this part of CDP may change without notice.
     */
    public static final class LoadCompleteEvent extends CdpObject {
        private LoadCompleteEvent(Map<String, Object> values) { super(values); }
        @Nullable public static LoadCompleteEvent fromMap(@Nullable Map<String, Object> values) {
            return values == null ? null : new LoadCompleteEvent(values);
        }
        public static Builder builder() { return new Builder(); }
        /**
         * New document root node.
         * @return the protocol field value
         */
        @Nullable public Accessibility.AXNode root() {
            return Accessibility.AXNode.fromMap(objectMap(value("root")));
        }
        public static final class Builder {
            private final Map<String, Object> values = new LinkedHashMap<>();
            /**
             * New document root node.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder root(@Nullable Accessibility.AXNode value) {
                if (value == null) values.remove("root");
                else values.put("root", jsonValue(value));
                return this;
            }
            public LoadCompleteEvent build() {
                if (!values.containsKey("root")) throw new IllegalStateException("Missing required CDP field: root");
                return new LoadCompleteEvent(values);
            }
        }
    }
    /**
     * The nodesUpdated event is sent every time a previously requested node has changed the in tree.
     * <p><b>Experimental:</b> this part of CDP may change without notice.
     */
    public static final class NodesUpdatedEvent extends CdpObject {
        private NodesUpdatedEvent(Map<String, Object> values) { super(values); }
        @Nullable public static NodesUpdatedEvent fromMap(@Nullable Map<String, Object> values) {
            return values == null ? null : new NodesUpdatedEvent(values);
        }
        public static Builder builder() { return new Builder(); }
        /**
         * Updated node data.
         * @return the protocol field value
         */
        @Nullable public java.util.List<Accessibility.AXNode> nodes() {
            return list(value("nodes"), element0 -> Accessibility.AXNode.fromMap(objectMap(element0)));
        }
        public static final class Builder {
            private final Map<String, Object> values = new LinkedHashMap<>();
            /**
             * Updated node data.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder nodes(@Nullable java.util.List<Accessibility.AXNode> value) {
                if (value == null) values.remove("nodes");
                else values.put("nodes", jsonValue(value));
                return this;
            }
            public NodesUpdatedEvent build() {
                if (!values.containsKey("nodes")) throw new IllegalStateException("Missing required CDP field: nodes");
                return new NodesUpdatedEvent(values);
            }
        }
    }
    public static final class Client {
        private final CdpClient client;
        public Client(CdpClient client) { this.client = client; }
        /**
         * Disables the accessibility domain.
         * @return a stage completing with the command result
         */
        public CompletionStage<DisableResult> disable() {
            return client.call("Accessibility.disable", null, DisableResult::fromMap);
        }
        /**
         * Enables the accessibility domain which causes {@code AXNodeId}s to remain consistent between method calls. This turns on accessibility for the page, which can impact performance until accessibility is disabled.
         * @return a stage completing with the command result
         */
        public CompletionStage<EnableResult> enable() {
            return client.call("Accessibility.enable", null, EnableResult::fromMap);
        }
        /**
         * Fetches the accessibility node and partial accessibility tree for this DOM node, if it exists.
         * <p><b>Experimental:</b> this part of CDP may change without notice.
         * @param params command parameters
         * @return a stage completing with the command result
         */
        public CompletionStage<GetPartialAXTreeResult> getPartialAXTree(GetPartialAXTreeParams params) {
            return client.call("Accessibility.getPartialAXTree", params, GetPartialAXTreeResult::fromMap);
        }
        /**
         * Fetches the entire accessibility tree for the root Document
         * <p><b>Experimental:</b> this part of CDP may change without notice.
         * @param params command parameters
         * @return a stage completing with the command result
         */
        public CompletionStage<GetFullAXTreeResult> getFullAXTree(GetFullAXTreeParams params) {
            return client.call("Accessibility.getFullAXTree", params, GetFullAXTreeResult::fromMap);
        }
        /**
         * Fetches the root node. Requires {@code enable()} to have been called previously.
         * <p><b>Experimental:</b> this part of CDP may change without notice.
         * @param params command parameters
         * @return a stage completing with the command result
         */
        public CompletionStage<GetRootAXNodeResult> getRootAXNode(GetRootAXNodeParams params) {
            return client.call("Accessibility.getRootAXNode", params, GetRootAXNodeResult::fromMap);
        }
        /**
         * Fetches a node and all ancestors up to and including the root. Requires {@code enable()} to have been called previously.
         * <p><b>Experimental:</b> this part of CDP may change without notice.
         * @param params command parameters
         * @return a stage completing with the command result
         */
        public CompletionStage<GetAXNodeAndAncestorsResult> getAXNodeAndAncestors(GetAXNodeAndAncestorsParams params) {
            return client.call("Accessibility.getAXNodeAndAncestors", params, GetAXNodeAndAncestorsResult::fromMap);
        }
        /**
         * Fetches a particular accessibility node by AXNodeId. Requires {@code enable()} to have been called previously.
         * <p><b>Experimental:</b> this part of CDP may change without notice.
         * @param params command parameters
         * @return a stage completing with the command result
         */
        public CompletionStage<GetChildAXNodesResult> getChildAXNodes(GetChildAXNodesParams params) {
            return client.call("Accessibility.getChildAXNodes", params, GetChildAXNodesResult::fromMap);
        }
        /**
         * Query a DOM node&#x27;s accessibility subtree for accessible name and role. This command computes the name and role for all nodes in the subtree, including those that are ignored for accessibility, and returns those that match the specified name and role. If no DOM node is specified, or the DOM node does not exist, the command returns an error. If neither {@code accessibleName} or {@code role} is specified, it returns all the accessibility nodes in the subtree.
         * <p><b>Experimental:</b> this part of CDP may change without notice.
         * @param params command parameters
         * @return a stage completing with the command result
         */
        public CompletionStage<QueryAXTreeResult> queryAXTree(QueryAXTreeParams params) {
            return client.call("Accessibility.queryAXTree", params, QueryAXTreeResult::fromMap);
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
