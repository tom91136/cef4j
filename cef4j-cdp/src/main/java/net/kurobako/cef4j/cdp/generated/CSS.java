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
 * This domain exposes CSS read/write operations. All CSS objects (stylesheets, rules, and styles) have an associated {@code id} used in subsequent operations on the related object. Each object type has a specific {@code id} structure, and those are not interchangeable between objects of different kinds. CSS objects can be loaded using the {@code get*ForNode()} calls (which accept a DOM node id). A client can also keep track of stylesheets via the {@code styleSheetAdded}/{@code styleSheetRemoved} events and subsequently load the required stylesheet contents using the {@code getStyleSheet[Text]()} methods.
 * <p><b>Experimental:</b> this part of CDP may change without notice.
 * @see <a href="https://chromium.googlesource.com/chromium/src/+/refs/tags/150.0.7871.213/third_party/blink/public/devtools_protocol/domains/CSS.pdl">Pinned protocol source</a>
 */
@Generated("./mvnw generate-sources -pl cef4j-cdp -Dcef.version=150.0.18+gdb11278+chromium-150.0.7871.213")
@SuppressWarnings({"EscapedEntity", "InvalidParam", "JavaLangClash", "MissingSummary", "Unchecked", "UnusedMethod"})
@NullableBoundary("CDP wire objects use null for absent optional fields")
public final class CSS {
    private CSS() {}
    private static final CdpField<CSS.Value> ANIMATION_NAME = CdpField.of("animationName", value -> java.util.Objects.requireNonNull(CSS.Value.fromMap(CdpObject.requireObjectMap(value))));
    private static final CdpField<java.util.List<CSS.CSSAnimationStyle>> ANIMATION_STYLES = CdpField.of("animationStyles", value -> CdpObject.requireList(value, element0 -> java.util.Objects.requireNonNull(CSS.CSSAnimationStyle.fromMap(CdpObject.requireObjectMap(element0)))));
    private static final CdpField<CSS.CSSStyle> ATTRIBUTES_STYLE = CdpField.of("attributesStyle", value -> java.util.Objects.requireNonNull(CSS.CSSStyle.fromMap(CdpObject.requireObjectMap(value))));
    private static final CdpField<java.util.List<String>> BACKGROUND_COLORS = CdpField.of("backgroundColors", value -> CdpObject.requireList(value, element0 -> (String) element0));
    private static final CdpField<java.util.List<CSS.CSSFunctionNode>> CHILDREN = CdpField.of("children", value -> CdpObject.requireList(value, element0 -> java.util.Objects.requireNonNull(CSS.CSSFunctionNode.fromMap(CdpObject.requireObjectMap(element0)))));
    private static final CdpField<java.util.List<String>> CLASS_NAMES = CdpField.of("classNames", value -> CdpObject.requireList(value, element0 -> (String) element0));
    private static final CdpField<java.util.List<CSS.CSSComputedStyleProperty>> COMPUTED_STYLE = CdpField.of("computedStyle", value -> CdpObject.requireList(value, element0 -> java.util.Objects.requireNonNull(CSS.CSSComputedStyleProperty.fromMap(CdpObject.requireObjectMap(element0)))));
    private static final CdpField<CSS.CSSFunctionConditionNode> CONDITION = CdpField.of("condition", value -> java.util.Objects.requireNonNull(CSS.CSSFunctionConditionNode.fromMap(CdpObject.requireObjectMap(value))));
    private static final CdpField<java.util.List<CSS.CSSContainerQuery>> CONTAINER_QUERIES = CdpField.of("containerQueries", value -> CdpObject.requireList(value, element0 -> java.util.Objects.requireNonNull(CSS.CSSContainerQuery.fromMap(CdpObject.requireObjectMap(element0)))));
    private static final CdpField<CSS.CSSContainerQuery> CONTAINER_QUERIES_2 = CdpField.of("containerQueries", value -> java.util.Objects.requireNonNull(CSS.CSSContainerQuery.fromMap(CdpObject.requireObjectMap(value))));
    private static final CdpField<CSS.CSSContainerQuery> CONTAINER_QUERY = CdpField.of("containerQuery", value -> java.util.Objects.requireNonNull(CSS.CSSContainerQuery.fromMap(CdpObject.requireObjectMap(value))));
    private static final CdpField<java.util.List<CSS.RuleUsage>> COVERAGE = CdpField.of("coverage", value -> CdpObject.requireList(value, element0 -> java.util.Objects.requireNonNull(CSS.RuleUsage.fromMap(CdpObject.requireObjectMap(element0)))));
    private static final CdpField<java.util.List<CSS.CSSAtRule>> CSS_AT_RULES = CdpField.of("cssAtRules", value -> CdpObject.requireList(value, element0 -> java.util.Objects.requireNonNull(CSS.CSSAtRule.fromMap(CdpObject.requireObjectMap(element0)))));
    private static final CdpField<java.util.List<CSS.CSSFunctionRule>> CSS_FUNCTION_RULES = CdpField.of("cssFunctionRules", value -> CdpObject.requireList(value, element0 -> java.util.Objects.requireNonNull(CSS.CSSFunctionRule.fromMap(CdpObject.requireObjectMap(element0)))));
    private static final CdpField<java.util.List<CSS.CSSKeyframesRule>> CSS_KEYFRAMES_RULES = CdpField.of("cssKeyframesRules", value -> CdpObject.requireList(value, element0 -> java.util.Objects.requireNonNull(CSS.CSSKeyframesRule.fromMap(CdpObject.requireObjectMap(element0)))));
    private static final CdpField<java.util.List<CSS.CSSPositionTryRule>> CSS_POSITION_TRY_RULES = CdpField.of("cssPositionTryRules", value -> CdpObject.requireList(value, element0 -> java.util.Objects.requireNonNull(CSS.CSSPositionTryRule.fromMap(CdpObject.requireObjectMap(element0)))));
    private static final CdpField<java.util.List<CSS.CSSProperty>> CSS_PROPERTIES = CdpField.of("cssProperties", value -> CdpObject.requireList(value, element0 -> java.util.Objects.requireNonNull(CSS.CSSProperty.fromMap(CdpObject.requireObjectMap(element0)))));
    private static final CdpField<java.util.List<CSS.CSSPropertyRegistration>> CSS_PROPERTY_REGISTRATIONS = CdpField.of("cssPropertyRegistrations", value -> CdpObject.requireList(value, element0 -> java.util.Objects.requireNonNull(CSS.CSSPropertyRegistration.fromMap(CdpObject.requireObjectMap(element0)))));
    private static final CdpField<java.util.List<CSS.CSSPropertyRule>> CSS_PROPERTY_RULES = CdpField.of("cssPropertyRules", value -> CdpObject.requireList(value, element0 -> java.util.Objects.requireNonNull(CSS.CSSPropertyRule.fromMap(CdpObject.requireObjectMap(element0)))));
    private static final CdpField<java.util.List<CSS.StyleDeclarationEdit>> EDITS = CdpField.of("edits", value -> CdpObject.requireList(value, element0 -> java.util.Objects.requireNonNull(CSS.StyleDeclarationEdit.fromMap(CdpObject.requireObjectMap(element0)))));
    private static final CdpField<java.util.Map<String, Object>> ENVIRONMENT_VARIABLES = CdpField.of("environmentVariables", value -> CdpObject.requireObjectMap(value));
    private static final CdpField<java.util.List<CSS.MediaQueryExpression>> EXPRESSIONS = CdpField.of("expressions", value -> CdpObject.requireList(value, element0 -> java.util.Objects.requireNonNull(CSS.MediaQueryExpression.fromMap(CdpObject.requireObjectMap(element0)))));
    private static final CdpField<CSS.ComputedStyleExtraFields> EXTRA_FIELDS = CdpField.of("extraFields", value -> java.util.Objects.requireNonNull(CSS.ComputedStyleExtraFields.fromMap(CdpObject.requireObjectMap(value))));
    private static final CdpField<CSS.FontFace> FONT = CdpField.of("font", value -> java.util.Objects.requireNonNull(CSS.FontFace.fromMap(CdpObject.requireObjectMap(value))));
    private static final CdpField<java.util.List<CSS.PlatformFontUsage>> FONTS = CdpField.of("fonts", value -> CdpObject.requireList(value, element0 -> java.util.Objects.requireNonNull(CSS.PlatformFontUsage.fromMap(CdpObject.requireObjectMap(element0)))));
    private static final CdpField<java.util.List<CSS.FontVariationAxis>> FONT_VARIATION_AXES = CdpField.of("fontVariationAxes", value -> CdpObject.requireList(value, element0 -> java.util.Objects.requireNonNull(CSS.FontVariationAxis.fromMap(CdpObject.requireObjectMap(element0)))));
    private static final CdpField<java.util.List<String>> FORCED_PSEUDO_CLASSES = CdpField.of("forcedPseudoClasses", value -> CdpObject.requireList(value, element0 -> (String) element0));
    private static final CdpField<Page.FrameId> FRAME_ID = CdpField.of("frameId", value -> new Page.FrameId((String) value));
    private static final CdpField<CSS.CSSStyleSheetHeader> HEADER = CdpField.of("header", value -> java.util.Objects.requireNonNull(CSS.CSSStyleSheetHeader.fromMap(CdpObject.requireObjectMap(value))));
    private static final CdpField<java.util.List<CSS.InheritedAnimatedStyleEntry>> INHERITED = CdpField.of("inherited", value -> CdpObject.requireList(value, element0 -> java.util.Objects.requireNonNull(CSS.InheritedAnimatedStyleEntry.fromMap(CdpObject.requireObjectMap(element0)))));
    private static final CdpField<java.util.List<CSS.InheritedStyleEntry>> INHERITED_2 = CdpField.of("inherited", value -> CdpObject.requireList(value, element0 -> java.util.Objects.requireNonNull(CSS.InheritedStyleEntry.fromMap(CdpObject.requireObjectMap(element0)))));
    private static final CdpField<java.util.List<CSS.InheritedPseudoElementMatches>> INHERITED_PSEUDO_ELEMENTS = CdpField.of("inheritedPseudoElements", value -> CdpObject.requireList(value, element0 -> java.util.Objects.requireNonNull(CSS.InheritedPseudoElementMatches.fromMap(CdpObject.requireObjectMap(element0)))));
    private static final CdpField<CSS.Value> INITIAL_VALUE = CdpField.of("initialValue", value -> java.util.Objects.requireNonNull(CSS.Value.fromMap(CdpObject.requireObjectMap(value))));
    private static final CdpField<CSS.CSSStyle> INLINE_STYLE = CdpField.of("inlineStyle", value -> java.util.Objects.requireNonNull(CSS.CSSStyle.fromMap(CdpObject.requireObjectMap(value))));
    private static final CdpField<java.util.List<CSS.CSSKeyframeRule>> KEYFRAMES = CdpField.of("keyframes", value -> CdpObject.requireList(value, element0 -> java.util.Objects.requireNonNull(CSS.CSSKeyframeRule.fromMap(CdpObject.requireObjectMap(element0)))));
    private static final CdpField<CSS.Value> KEY_TEXT = CdpField.of("keyText", value -> java.util.Objects.requireNonNull(CSS.Value.fromMap(CdpObject.requireObjectMap(value))));
    private static final CdpField<java.util.List<CSS.CSSLayer>> LAYERS = CdpField.of("layers", value -> CdpObject.requireList(value, element0 -> java.util.Objects.requireNonNull(CSS.CSSLayer.fromMap(CdpObject.requireObjectMap(element0)))));
    private static final CdpField<CSS.SourceRange> LOCATION = CdpField.of("location", value -> java.util.Objects.requireNonNull(CSS.SourceRange.fromMap(CdpObject.requireObjectMap(value))));
    private static final CdpField<DOM.LogicalAxes> LOGICAL_AXES = CdpField.of("logicalAxes", value -> DOM.LogicalAxes.of((String) value));
    private static final CdpField<java.util.List<CSS.CSSProperty>> LONGHAND_PROPERTIES = CdpField.of("longhandProperties", value -> CdpObject.requireList(value, element0 -> java.util.Objects.requireNonNull(CSS.CSSProperty.fromMap(CdpObject.requireObjectMap(element0)))));
    private static final CdpField<java.util.List<CSS.RuleMatch>> MATCHED_CSSRULES = CdpField.of("matchedCSSRules", value -> CdpObject.requireList(value, element0 -> java.util.Objects.requireNonNull(CSS.RuleMatch.fromMap(CdpObject.requireObjectMap(element0)))));
    private static final CdpField<java.util.List<CSS.RuleMatch>> MATCHES = CdpField.of("matches", value -> CdpObject.requireList(value, element0 -> java.util.Objects.requireNonNull(CSS.RuleMatch.fromMap(CdpObject.requireObjectMap(element0)))));
    private static final CdpField<java.util.List<Long>> MATCHING_SELECTORS = CdpField.of("matchingSelectors", value -> CdpObject.requireList(value, element0 -> ((Number) element0).longValue()));
    private static final CdpField<java.util.List<CSS.CSSMedia>> MEDIA = CdpField.of("media", value -> CdpObject.requireList(value, element0 -> java.util.Objects.requireNonNull(CSS.CSSMedia.fromMap(CdpObject.requireObjectMap(element0)))));
    private static final CdpField<java.util.List<CSS.CSSMedia>> MEDIAS = CdpField.of("medias", value -> CdpObject.requireList(value, element0 -> java.util.Objects.requireNonNull(CSS.CSSMedia.fromMap(CdpObject.requireObjectMap(element0)))));
    private static final CdpField<CSS.CSSMedia> MEDIA_2 = CdpField.of("media", value -> java.util.Objects.requireNonNull(CSS.CSSMedia.fromMap(CdpObject.requireObjectMap(value))));
    private static final CdpField<java.util.List<CSS.MediaQuery>> MEDIA_LIST = CdpField.of("mediaList", value -> CdpObject.requireList(value, element0 -> java.util.Objects.requireNonNull(CSS.MediaQuery.fromMap(CdpObject.requireObjectMap(element0)))));
    private static final CdpField<CSS.Value> NAME = CdpField.of("name", value -> java.util.Objects.requireNonNull(CSS.Value.fromMap(CdpObject.requireObjectMap(value))));
    private static final CdpField<CSS.CSSNavigation> NAVIGATION = CdpField.of("navigation", value -> java.util.Objects.requireNonNull(CSS.CSSNavigation.fromMap(CdpObject.requireObjectMap(value))));
    private static final CdpField<java.util.List<CSS.CSSNavigation>> NAVIGATIONS = CdpField.of("navigations", value -> CdpObject.requireList(value, element0 -> java.util.Objects.requireNonNull(CSS.CSSNavigation.fromMap(CdpObject.requireObjectMap(element0)))));
    private static final CdpField<java.util.List<String>> NESTING_SELECTORS = CdpField.of("nestingSelectors", value -> CdpObject.requireList(value, element0 -> (String) element0));
    private static final CdpField<DOM.NodeId> NODE_FOR_PROPERTY_SYNTAX_VALIDATION = CdpField.of("nodeForPropertySyntaxValidation", value -> new DOM.NodeId(((Number) value).longValue()));
    private static final CdpField<DOM.NodeId> NODE_ID = CdpField.of("nodeId", value -> new DOM.NodeId(((Number) value).longValue()));
    private static final CdpField<java.util.List<DOM.NodeId>> NODE_IDS = CdpField.of("nodeIds", value -> CdpObject.requireList(value, element0 -> new DOM.NodeId(((Number) element0).longValue())));
    private static final CdpField<CSS.StyleSheetOrigin> ORIGIN = CdpField.of("origin", value -> CSS.StyleSheetOrigin.of((String) value));
    private static final CdpField<DOM.BackendNodeId> ORIGIN_TREE_SCOPE_NODE_ID = CdpField.of("originTreeScopeNodeId", value -> new DOM.BackendNodeId(((Number) value).longValue()));
    private static final CdpField<DOM.BackendNodeId> OWNER_NODE = CdpField.of("ownerNode", value -> new DOM.BackendNodeId(((Number) value).longValue()));
    private static final CdpField<java.util.List<CSS.CSSFunctionParameter>> PARAMETERS = CdpField.of("parameters", value -> CdpObject.requireList(value, element0 -> java.util.Objects.requireNonNull(CSS.CSSFunctionParameter.fromMap(CdpObject.requireObjectMap(element0)))));
    private static final CdpField<DOM.NodeId> PARENT_LAYOUT_NODE_ID = CdpField.of("parentLayoutNodeId", value -> new DOM.NodeId(((Number) value).longValue()));
    private static final CdpField<DOM.PhysicalAxes> PHYSICAL_AXES = CdpField.of("physicalAxes", value -> DOM.PhysicalAxes.of((String) value));
    private static final CdpField<java.util.List<CSS.CSSComputedStyleProperty>> PROPERTIES_TO_TRACK = CdpField.of("propertiesToTrack", value -> CdpObject.requireList(value, element0 -> java.util.Objects.requireNonNull(CSS.CSSComputedStyleProperty.fromMap(CdpObject.requireObjectMap(element0)))));
    private static final CdpField<CSS.Value> PROPERTY_NAME = CdpField.of("propertyName", value -> java.util.Objects.requireNonNull(CSS.Value.fromMap(CdpObject.requireObjectMap(value))));
    private static final CdpField<java.util.List<CSS.PseudoElementMatches>> PSEUDO_ELEMENTS = CdpField.of("pseudoElements", value -> CdpObject.requireList(value, element0 -> java.util.Objects.requireNonNull(CSS.PseudoElementMatches.fromMap(CdpObject.requireObjectMap(element0)))));
    private static final CdpField<DOM.PseudoType> PSEUDO_TYPE = CdpField.of("pseudoType", value -> DOM.PseudoType.of((String) value));
    private static final CdpField<CSS.SourceRange> RANGE = CdpField.of("range", value -> java.util.Objects.requireNonNull(CSS.SourceRange.fromMap(CdpObject.requireObjectMap(value))));
    private static final CdpField<java.util.List<CSS.SourceRange>> RANGES = CdpField.of("ranges", value -> CdpObject.requireList(value, element0 -> java.util.Objects.requireNonNull(CSS.SourceRange.fromMap(CdpObject.requireObjectMap(element0)))));
    private static final CdpField<java.util.List<String>> RESULTS = CdpField.of("results", value -> CdpObject.requireList(value, element0 -> (String) element0));
    private static final CdpField<CSS.CSSLayerData> ROOT_LAYER = CdpField.of("rootLayer", value -> java.util.Objects.requireNonNull(CSS.CSSLayerData.fromMap(CdpObject.requireObjectMap(value))));
    private static final CdpField<CSS.CSSRule> RULE = CdpField.of("rule", value -> java.util.Objects.requireNonNull(CSS.CSSRule.fromMap(CdpObject.requireObjectMap(value))));
    private static final CdpField<java.util.List<CSS.CSSRuleType>> RULE_TYPES = CdpField.of("ruleTypes", value -> CdpObject.requireList(value, element0 -> CSS.CSSRuleType.of((String) element0)));
    private static final CdpField<java.util.List<CSS.RuleUsage>> RULE_USAGE = CdpField.of("ruleUsage", value -> CdpObject.requireList(value, element0 -> java.util.Objects.requireNonNull(CSS.RuleUsage.fromMap(CdpObject.requireObjectMap(element0)))));
    private static final CdpField<CSS.CSSScope> SCOPE = CdpField.of("scope", value -> java.util.Objects.requireNonNull(CSS.CSSScope.fromMap(CdpObject.requireObjectMap(value))));
    private static final CdpField<java.util.List<CSS.CSSScope>> SCOPES = CdpField.of("scopes", value -> CdpObject.requireList(value, element0 -> java.util.Objects.requireNonNull(CSS.CSSScope.fromMap(CdpObject.requireObjectMap(element0)))));
    private static final CdpField<java.util.List<CSS.Value>> SELECTORS = CdpField.of("selectors", value -> CdpObject.requireList(value, element0 -> java.util.Objects.requireNonNull(CSS.Value.fromMap(CdpObject.requireObjectMap(element0)))));
    private static final CdpField<CSS.SelectorList> SELECTOR_LIST = CdpField.of("selectorList", value -> java.util.Objects.requireNonNull(CSS.SelectorList.fromMap(CdpObject.requireObjectMap(value))));
    private static final CdpField<java.util.List<CSS.ShorthandEntry>> SHORTHAND_ENTRIES = CdpField.of("shorthandEntries", value -> CdpObject.requireList(value, element0 -> java.util.Objects.requireNonNull(CSS.ShorthandEntry.fromMap(CdpObject.requireObjectMap(element0)))));
    private static final CdpField<CSSMedia.SourceValues> SOURCE = CdpField.of("source", value -> CSSMedia.SourceValues.of((String) value));
    private static final CdpField<String> SOURCE_MAP_URL = CdpField.of("sourceMapURL", value -> (String) value);
    private static final CdpField<CSS.Specificity> SPECIFICITY = CdpField.of("specificity", value -> java.util.Objects.requireNonNull(CSS.Specificity.fromMap(CdpObject.requireObjectMap(value))));
    private static final CdpField<java.util.List<CSS.CSSStartingStyle>> STARTING_STYLES = CdpField.of("startingStyles", value -> CdpObject.requireList(value, element0 -> java.util.Objects.requireNonNull(CSS.CSSStartingStyle.fromMap(CdpObject.requireObjectMap(element0)))));
    private static final CdpField<CSS.CSSStyle> STYLE = CdpField.of("style", value -> java.util.Objects.requireNonNull(CSS.CSSStyle.fromMap(CdpObject.requireObjectMap(value))));
    private static final CdpField<java.util.List<CSS.CSSStyle>> STYLES = CdpField.of("styles", value -> CdpObject.requireList(value, element0 -> java.util.Objects.requireNonNull(CSS.CSSStyle.fromMap(CdpObject.requireObjectMap(element0)))));
    private static final CdpField<DOM.StyleSheetId> STYLE_SHEET_ID = CdpField.of("styleSheetId", value -> new DOM.StyleSheetId((String) value));
    private static final CdpField<CSSAtRule.SubsectionValues> SUBSECTION = CdpField.of("subsection", value -> CSSAtRule.SubsectionValues.of((String) value));
    private static final CdpField<java.util.List<CSS.CSSLayerData>> SUB_LAYERS = CdpField.of("subLayers", value -> CdpObject.requireList(value, element0 -> java.util.Objects.requireNonNull(CSS.CSSLayerData.fromMap(CdpObject.requireObjectMap(element0)))));
    private static final CdpField<java.util.List<CSS.CSSSupports>> SUPPORTS = CdpField.of("supports", value -> CdpObject.requireList(value, element0 -> java.util.Objects.requireNonNull(CSS.CSSSupports.fromMap(CdpObject.requireObjectMap(element0)))));
    private static final CdpField<CSS.CSSSupports> SUPPORTS_2 = CdpField.of("supports", value -> java.util.Objects.requireNonNull(CSS.CSSSupports.fromMap(CdpObject.requireObjectMap(value))));
    private static final CdpField<String> TEXT = CdpField.of("text", value -> (String) value);
    private static final CdpField<CSS.CSSStyle> TRANSITIONS_STYLE = CdpField.of("transitionsStyle", value -> java.util.Objects.requireNonNull(CSS.CSSStyle.fromMap(CdpObject.requireObjectMap(value))));
    private static final CdpField<CSSAtRule.TypeValues> TYPE = CdpField.of("type", value -> CSSAtRule.TypeValues.of((String) value));
    private static final CdpField<java.util.List<String>> VALUES = CdpField.of("values", value -> CdpObject.requireList(value, element0 -> (String) element0));
    private static final CdpField<CSS.SourceRange> VALUE_RANGE = CdpField.of("valueRange", value -> java.util.Objects.requireNonNull(CSS.SourceRange.fromMap(CdpObject.requireObjectMap(value))));
    /**
     * Stylesheet type: &quot;injected&quot; for stylesheets injected via extension, &quot;user-agent&quot; for user-agent stylesheets, &quot;inspector&quot; for stylesheets created by the inspector (i.e. those holding the &quot;via inspector&quot; rules), &quot;regular&quot; for regular stylesheets.
     */
    public enum StyleSheetOrigin implements CdpValue<String> {
        INJECTED("injected"),
        USER_AGENT("user-agent"),
        INSPECTOR("inspector"),
        REGULAR("regular");
        public final String value;
        StyleSheetOrigin(String value) { this.value = value; }
        @Override public String value() { return value; }
        public static StyleSheetOrigin of(String value) {
            for (StyleSheetOrigin constant : values()) {
                if (constant.value.equals(value)) return constant;
            }
            throw new IllegalArgumentException("Unknown StyleSheetOrigin value: " + value);
        }
    }
    /**
     * CSS rule collection for a single pseudo style.
     */
    public static final class PseudoElementMatches extends CdpObject {
        public PseudoElementMatches() {}
        private PseudoElementMatches(Map<String, Object> values) { super(values); }
        public static PseudoElementMatches fromMap(Map<String, Object> values) {
            return new PseudoElementMatches(values);
        }
        /**
         * Pseudo element type.
         * @return the protocol field value
         */
        public DOM.PseudoType pseudoType() {
            return require(PSEUDO_TYPE);
        }
        /**
         * Pseudo element custom ident.
         * @return the protocol field value, empty when absent
         */
        public Optional<String> pseudoIdentifier() {
            return optionalString("pseudoIdentifier");
        }
        /**
         * Matches of CSS rules applicable to the pseudo style.
         * @return the protocol field value
         */
        public java.util.List<CSS.RuleMatch> matches() {
            return require(MATCHES);
        }
        /**
         * Pseudo element type.
         * @param pseudoType field value
         * @return this model
         */
        public PseudoElementMatches pseudoType(DOM.PseudoType pseudoType) {
            set("pseudoType", pseudoType);
            return this;
        }
        /**
         * Pseudo element custom ident.
         * @param pseudoIdentifier field value
         * @return this model
         */
        public PseudoElementMatches pseudoIdentifier(String pseudoIdentifier) {
            set("pseudoIdentifier", pseudoIdentifier);
            return this;
        }
        /**
         * Matches of CSS rules applicable to the pseudo style.
         * @param matches field value
         * @return this model
         */
        public PseudoElementMatches matches(java.util.List<CSS.RuleMatch> matches) {
            set("matches", matches);
            return this;
        }
    }
    /**
     * CSS style coming from animations with the name of the animation.
     */
    public static final class CSSAnimationStyle extends CdpObject {
        public CSSAnimationStyle() {}
        private CSSAnimationStyle(Map<String, Object> values) { super(values); }
        public static CSSAnimationStyle fromMap(Map<String, Object> values) {
            return new CSSAnimationStyle(values);
        }
        /**
         * The name of the animation.
         * @return the protocol field value, empty when absent
         */
        public Optional<String> name() {
            return optionalString("name");
        }
        /**
         * The style coming from the animation.
         * @return the protocol field value
         */
        public CSS.CSSStyle style() {
            return require(STYLE);
        }
        /**
         * The name of the animation.
         * @param name field value
         * @return this model
         */
        public CSSAnimationStyle name(String name) {
            set("name", name);
            return this;
        }
        /**
         * The style coming from the animation.
         * @param style field value
         * @return this model
         */
        public CSSAnimationStyle style(CSS.CSSStyle style) {
            set("style", style);
            return this;
        }
    }
    /**
     * Inherited CSS rule collection from ancestor node.
     */
    public static final class InheritedStyleEntry extends CdpObject {
        public InheritedStyleEntry() {}
        private InheritedStyleEntry(Map<String, Object> values) { super(values); }
        public static InheritedStyleEntry fromMap(Map<String, Object> values) {
            return new InheritedStyleEntry(values);
        }
        /**
         * The ancestor node&#x27;s inline style, if any, in the style inheritance chain.
         * @return the protocol field value, empty when absent
         */
        public Optional<CSS.CSSStyle> inlineStyle() {
            return optional(INLINE_STYLE);
        }
        /**
         * Matches of CSS rules matching the ancestor node in the style inheritance chain.
         * @return the protocol field value
         */
        public java.util.List<CSS.RuleMatch> matchedCSSRules() {
            return require(MATCHED_CSSRULES);
        }
        /**
         * The ancestor node&#x27;s inline style, if any, in the style inheritance chain.
         * @param inlineStyle field value
         * @return this model
         */
        public InheritedStyleEntry inlineStyle(CSS.CSSStyle inlineStyle) {
            set("inlineStyle", inlineStyle);
            return this;
        }
        /**
         * Matches of CSS rules matching the ancestor node in the style inheritance chain.
         * @param matchedCSSRules field value
         * @return this model
         */
        public InheritedStyleEntry matchedCSSRules(java.util.List<CSS.RuleMatch> matchedCSSRules) {
            set("matchedCSSRules", matchedCSSRules);
            return this;
        }
    }
    /**
     * Inherited CSS style collection for animated styles from ancestor node.
     */
    public static final class InheritedAnimatedStyleEntry extends CdpObject {
        public InheritedAnimatedStyleEntry() {}
        private InheritedAnimatedStyleEntry(Map<String, Object> values) { super(values); }
        public static InheritedAnimatedStyleEntry fromMap(Map<String, Object> values) {
            return new InheritedAnimatedStyleEntry(values);
        }
        /**
         * Styles coming from the animations of the ancestor, if any, in the style inheritance chain.
         * @return the protocol field value, empty when absent
         */
        public Optional<java.util.List<CSS.CSSAnimationStyle>> animationStyles() {
            return optional(ANIMATION_STYLES);
        }
        /**
         * The style coming from the transitions of the ancestor, if any, in the style inheritance chain.
         * @return the protocol field value, empty when absent
         */
        public Optional<CSS.CSSStyle> transitionsStyle() {
            return optional(TRANSITIONS_STYLE);
        }
        /**
         * Styles coming from the animations of the ancestor, if any, in the style inheritance chain.
         * @param animationStyles field value
         * @return this model
         */
        public InheritedAnimatedStyleEntry animationStyles(java.util.List<CSS.CSSAnimationStyle> animationStyles) {
            set("animationStyles", animationStyles);
            return this;
        }
        /**
         * The style coming from the transitions of the ancestor, if any, in the style inheritance chain.
         * @param transitionsStyle field value
         * @return this model
         */
        public InheritedAnimatedStyleEntry transitionsStyle(CSS.CSSStyle transitionsStyle) {
            set("transitionsStyle", transitionsStyle);
            return this;
        }
    }
    /**
     * Inherited pseudo element matches from pseudos of an ancestor node.
     */
    public static final class InheritedPseudoElementMatches extends CdpObject {
        public InheritedPseudoElementMatches() {}
        private InheritedPseudoElementMatches(Map<String, Object> values) { super(values); }
        public static InheritedPseudoElementMatches fromMap(Map<String, Object> values) {
            return new InheritedPseudoElementMatches(values);
        }
        /**
         * Matches of pseudo styles from the pseudos of an ancestor node.
         * @return the protocol field value
         */
        public java.util.List<CSS.PseudoElementMatches> pseudoElements() {
            return require(PSEUDO_ELEMENTS);
        }
        /**
         * Matches of pseudo styles from the pseudos of an ancestor node.
         * @param pseudoElements field value
         * @return this model
         */
        public InheritedPseudoElementMatches pseudoElements(java.util.List<CSS.PseudoElementMatches> pseudoElements) {
            set("pseudoElements", pseudoElements);
            return this;
        }
    }
    /**
     * Match data for a CSS rule.
     */
    public static final class RuleMatch extends CdpObject {
        public RuleMatch() {}
        private RuleMatch(Map<String, Object> values) { super(values); }
        public static RuleMatch fromMap(Map<String, Object> values) {
            return new RuleMatch(values);
        }
        /**
         * CSS rule in the match.
         * @return the protocol field value
         */
        public CSS.CSSRule rule() {
            return require(RULE);
        }
        /**
         * Matching selector indices in the rule&#x27;s selectorList selectors (0-based).
         * @return the protocol field value
         */
        public java.util.List<Long> matchingSelectors() {
            return require(MATCHING_SELECTORS);
        }
        /**
         * CSS rule in the match.
         * @param rule field value
         * @return this model
         */
        public RuleMatch rule(CSS.CSSRule rule) {
            set("rule", rule);
            return this;
        }
        /**
         * Matching selector indices in the rule&#x27;s selectorList selectors (0-based).
         * @param matchingSelectors field value
         * @return this model
         */
        public RuleMatch matchingSelectors(java.util.List<Long> matchingSelectors) {
            set("matchingSelectors", matchingSelectors);
            return this;
        }
    }
    /**
     * Data for a simple selector (these are delimited by commas in a selector list).
     */
    public static final class Value extends CdpObject {
        public Value() {}
        private Value(Map<String, Object> values) { super(values); }
        public static Value fromMap(Map<String, Object> values) {
            return new Value(values);
        }
        /**
         * Value text.
         * @return the protocol field value
         */
        public String text() {
            return requireString("text");
        }
        /**
         * Value range in the underlying resource (if available).
         * @return the protocol field value, empty when absent
         */
        public Optional<CSS.SourceRange> range() {
            return optional(RANGE);
        }
        /**
         * Specificity of the selector.
         * <p><b>Experimental:</b> this part of CDP may change without notice.
         * @return the protocol field value, empty when absent
         */
        public Optional<CSS.Specificity> specificity() {
            return optional(SPECIFICITY);
        }
        /**
         * Value text.
         * @param text field value
         * @return this model
         */
        public Value text(String text) {
            set("text", text);
            return this;
        }
        /**
         * Value range in the underlying resource (if available).
         * @param range field value
         * @return this model
         */
        public Value range(CSS.SourceRange range) {
            set("range", range);
            return this;
        }
        /**
         * Specificity of the selector.
         * <p><b>Experimental:</b> this part of CDP may change without notice.
         * @param specificity field value
         * @return this model
         */
        public Value specificity(CSS.Specificity specificity) {
            set("specificity", specificity);
            return this;
        }
    }
    /**
     * Specificity: https://drafts.csswg.org/selectors/#specificity-rules
     * <p><b>Experimental:</b> this part of CDP may change without notice.
     */
    public static final class Specificity extends CdpObject {
        public Specificity() {}
        private Specificity(Map<String, Object> values) { super(values); }
        public static Specificity fromMap(Map<String, Object> values) {
            return new Specificity(values);
        }
        /**
         * The a component, which represents the number of ID selectors.
         * @return the protocol field value
         */
        public long a() {
            return requireLong("a");
        }
        /**
         * The b component, which represents the number of class selectors, attributes selectors, and pseudo-classes.
         * @return the protocol field value
         */
        public long b() {
            return requireLong("b");
        }
        /**
         * The c component, which represents the number of type selectors and pseudo-elements.
         * @return the protocol field value
         */
        public long c() {
            return requireLong("c");
        }
        /**
         * The a component, which represents the number of ID selectors.
         * @param a field value
         * @return this model
         */
        public Specificity a(long a) {
            set("a", a);
            return this;
        }
        /**
         * The b component, which represents the number of class selectors, attributes selectors, and pseudo-classes.
         * @param b field value
         * @return this model
         */
        public Specificity b(long b) {
            set("b", b);
            return this;
        }
        /**
         * The c component, which represents the number of type selectors and pseudo-elements.
         * @param c field value
         * @return this model
         */
        public Specificity c(long c) {
            set("c", c);
            return this;
        }
    }
    /**
     * Selector list data.
     */
    public static final class SelectorList extends CdpObject {
        public SelectorList() {}
        private SelectorList(Map<String, Object> values) { super(values); }
        public static SelectorList fromMap(Map<String, Object> values) {
            return new SelectorList(values);
        }
        /**
         * Selectors in the list.
         * @return the protocol field value
         */
        public java.util.List<CSS.Value> selectors() {
            return require(SELECTORS);
        }
        /**
         * Rule selector text.
         * @return the protocol field value
         */
        public String text() {
            return requireString("text");
        }
        /**
         * Selectors in the list.
         * @param selectors field value
         * @return this model
         */
        public SelectorList selectors(java.util.List<CSS.Value> selectors) {
            set("selectors", selectors);
            return this;
        }
        /**
         * Rule selector text.
         * @param text field value
         * @return this model
         */
        public SelectorList text(String text) {
            set("text", text);
            return this;
        }
    }
    /**
     * CSS stylesheet metainformation.
     */
    public static final class CSSStyleSheetHeader extends CdpObject {
        public CSSStyleSheetHeader() {}
        private CSSStyleSheetHeader(Map<String, Object> values) { super(values); }
        public static CSSStyleSheetHeader fromMap(Map<String, Object> values) {
            return new CSSStyleSheetHeader(values);
        }
        /**
         * The stylesheet identifier.
         * @return the protocol field value
         */
        public DOM.StyleSheetId styleSheetId() {
            return require(STYLE_SHEET_ID);
        }
        /**
         * Owner frame identifier.
         * @return the protocol field value
         */
        public Page.FrameId frameId() {
            return require(FRAME_ID);
        }
        /**
         * Stylesheet resource URL. Empty if this is a constructed stylesheet created using new CSSStyleSheet() (but non-empty if this is a constructed stylesheet imported as a CSS module script).
         * @return the protocol field value
         */
        public String sourceURL() {
            return requireString("sourceURL");
        }
        /**
         * URL of source map associated with the stylesheet (if any).
         * @return the protocol field value, empty when absent
         */
        public Optional<String> sourceMapURL() {
            return optionalString("sourceMapURL");
        }
        /**
         * Stylesheet origin.
         * @return the protocol field value
         */
        public CSS.StyleSheetOrigin origin() {
            return require(ORIGIN);
        }
        /**
         * Stylesheet title.
         * @return the protocol field value
         */
        public String title() {
            return requireString("title");
        }
        /**
         * The backend id for the owner node of the stylesheet.
         * @return the protocol field value, empty when absent
         */
        public Optional<DOM.BackendNodeId> ownerNode() {
            return optional(OWNER_NODE);
        }
        /**
         * Denotes whether the stylesheet is disabled.
         * @return the protocol field value
         */
        public boolean disabled() {
            return requireBoolean("disabled");
        }
        /**
         * Whether the sourceURL field value comes from the sourceURL comment.
         * @return the protocol field value, empty when absent
         */
        public Optional<Boolean> hasSourceURL() {
            return optionalBoolean("hasSourceURL");
        }
        /**
         * Whether this stylesheet is created for STYLE tag by parser. This flag is not set for document.written STYLE tags.
         * @return the protocol field value
         */
        public boolean isInline() {
            return requireBoolean("isInline");
        }
        /**
         * Whether this stylesheet is mutable. Inline stylesheets become mutable after they have been modified via CSSOM API. {@code &lt;link&gt;} element&#x27;s stylesheets become mutable only if DevTools modifies them. Constructed stylesheets (new CSSStyleSheet()) are mutable immediately after creation.
         * @return the protocol field value
         */
        public boolean isMutable() {
            return requireBoolean("isMutable");
        }
        /**
         * True if this stylesheet is created through new CSSStyleSheet() or imported as a CSS module script.
         * @return the protocol field value
         */
        public boolean isConstructed() {
            return requireBoolean("isConstructed");
        }
        /**
         * Line offset of the stylesheet within the resource (zero based).
         * @return the protocol field value
         */
        public double startLine() {
            return requireDouble("startLine");
        }
        /**
         * Column offset of the stylesheet within the resource (zero based).
         * @return the protocol field value
         */
        public double startColumn() {
            return requireDouble("startColumn");
        }
        /**
         * Size of the content (in characters).
         * @return the protocol field value
         */
        public double length() {
            return requireDouble("length");
        }
        /**
         * Line offset of the end of the stylesheet within the resource (zero based).
         * @return the protocol field value
         */
        public double endLine() {
            return requireDouble("endLine");
        }
        /**
         * Column offset of the end of the stylesheet within the resource (zero based).
         * @return the protocol field value
         */
        public double endColumn() {
            return requireDouble("endColumn");
        }
        /**
         * If the style sheet was loaded from a network resource, this indicates when the resource failed to load
         * <p><b>Experimental:</b> this part of CDP may change without notice.
         * @return the protocol field value, empty when absent
         */
        public Optional<Boolean> loadingFailed() {
            return optionalBoolean("loadingFailed");
        }
        /**
         * The stylesheet identifier.
         * @param styleSheetId field value
         * @return this model
         */
        public CSSStyleSheetHeader styleSheetId(DOM.StyleSheetId styleSheetId) {
            set("styleSheetId", styleSheetId);
            return this;
        }
        /**
         * Owner frame identifier.
         * @param frameId field value
         * @return this model
         */
        public CSSStyleSheetHeader frameId(Page.FrameId frameId) {
            set("frameId", frameId);
            return this;
        }
        /**
         * Stylesheet resource URL. Empty if this is a constructed stylesheet created using new CSSStyleSheet() (but non-empty if this is a constructed stylesheet imported as a CSS module script).
         * @param sourceURL field value
         * @return this model
         */
        public CSSStyleSheetHeader sourceURL(String sourceURL) {
            set("sourceURL", sourceURL);
            return this;
        }
        /**
         * URL of source map associated with the stylesheet (if any).
         * @param sourceMapURL field value
         * @return this model
         */
        public CSSStyleSheetHeader sourceMapURL(String sourceMapURL) {
            set("sourceMapURL", sourceMapURL);
            return this;
        }
        /**
         * Stylesheet origin.
         * @param origin field value
         * @return this model
         */
        public CSSStyleSheetHeader origin(CSS.StyleSheetOrigin origin) {
            set("origin", origin);
            return this;
        }
        /**
         * Stylesheet title.
         * @param title field value
         * @return this model
         */
        public CSSStyleSheetHeader title(String title) {
            set("title", title);
            return this;
        }
        /**
         * The backend id for the owner node of the stylesheet.
         * @param ownerNode field value
         * @return this model
         */
        public CSSStyleSheetHeader ownerNode(DOM.BackendNodeId ownerNode) {
            set("ownerNode", ownerNode);
            return this;
        }
        /**
         * Denotes whether the stylesheet is disabled.
         * @param disabled field value
         * @return this model
         */
        public CSSStyleSheetHeader disabled(boolean disabled) {
            set("disabled", disabled);
            return this;
        }
        /**
         * Whether the sourceURL field value comes from the sourceURL comment.
         * @param hasSourceURL field value
         * @return this model
         */
        public CSSStyleSheetHeader hasSourceURL(boolean hasSourceURL) {
            set("hasSourceURL", hasSourceURL);
            return this;
        }
        /**
         * Whether this stylesheet is created for STYLE tag by parser. This flag is not set for document.written STYLE tags.
         * @param isInline field value
         * @return this model
         */
        public CSSStyleSheetHeader isInline(boolean isInline) {
            set("isInline", isInline);
            return this;
        }
        /**
         * Whether this stylesheet is mutable. Inline stylesheets become mutable after they have been modified via CSSOM API. {@code &lt;link&gt;} element&#x27;s stylesheets become mutable only if DevTools modifies them. Constructed stylesheets (new CSSStyleSheet()) are mutable immediately after creation.
         * @param isMutable field value
         * @return this model
         */
        public CSSStyleSheetHeader isMutable(boolean isMutable) {
            set("isMutable", isMutable);
            return this;
        }
        /**
         * True if this stylesheet is created through new CSSStyleSheet() or imported as a CSS module script.
         * @param isConstructed field value
         * @return this model
         */
        public CSSStyleSheetHeader isConstructed(boolean isConstructed) {
            set("isConstructed", isConstructed);
            return this;
        }
        /**
         * Line offset of the stylesheet within the resource (zero based).
         * @param startLine field value
         * @return this model
         */
        public CSSStyleSheetHeader startLine(double startLine) {
            set("startLine", startLine);
            return this;
        }
        /**
         * Column offset of the stylesheet within the resource (zero based).
         * @param startColumn field value
         * @return this model
         */
        public CSSStyleSheetHeader startColumn(double startColumn) {
            set("startColumn", startColumn);
            return this;
        }
        /**
         * Size of the content (in characters).
         * @param length field value
         * @return this model
         */
        public CSSStyleSheetHeader length(double length) {
            set("length", length);
            return this;
        }
        /**
         * Line offset of the end of the stylesheet within the resource (zero based).
         * @param endLine field value
         * @return this model
         */
        public CSSStyleSheetHeader endLine(double endLine) {
            set("endLine", endLine);
            return this;
        }
        /**
         * Column offset of the end of the stylesheet within the resource (zero based).
         * @param endColumn field value
         * @return this model
         */
        public CSSStyleSheetHeader endColumn(double endColumn) {
            set("endColumn", endColumn);
            return this;
        }
        /**
         * If the style sheet was loaded from a network resource, this indicates when the resource failed to load
         * <p><b>Experimental:</b> this part of CDP may change without notice.
         * @param loadingFailed field value
         * @return this model
         */
        public CSSStyleSheetHeader loadingFailed(boolean loadingFailed) {
            set("loadingFailed", loadingFailed);
            return this;
        }
    }
    /**
     * CSS rule representation.
     */
    public static final class CSSRule extends CdpObject {
        public CSSRule() {}
        private CSSRule(Map<String, Object> values) { super(values); }
        public static CSSRule fromMap(Map<String, Object> values) {
            return new CSSRule(values);
        }
        /**
         * The css style sheet identifier (absent for user agent stylesheet and user-specified stylesheet rules) this rule came from.
         * @return the protocol field value, empty when absent
         */
        public Optional<DOM.StyleSheetId> styleSheetId() {
            return optional(STYLE_SHEET_ID);
        }
        /**
         * Rule selector data.
         * @return the protocol field value
         */
        public CSS.SelectorList selectorList() {
            return require(SELECTOR_LIST);
        }
        /**
         * Array of selectors from ancestor style rules, sorted by distance from the current rule.
         * <p><b>Experimental:</b> this part of CDP may change without notice.
         * @return the protocol field value, empty when absent
         */
        public Optional<java.util.List<String>> nestingSelectors() {
            return optional(NESTING_SELECTORS);
        }
        /**
         * Parent stylesheet&#x27;s origin.
         * @return the protocol field value
         */
        public CSS.StyleSheetOrigin origin() {
            return require(ORIGIN);
        }
        /**
         * Associated style declaration.
         * @return the protocol field value
         */
        public CSS.CSSStyle style() {
            return require(STYLE);
        }
        /**
         * The BackendNodeId of the DOM node that constitutes the origin tree scope of this rule.
         * <p><b>Experimental:</b> this part of CDP may change without notice.
         * @return the protocol field value, empty when absent
         */
        public Optional<DOM.BackendNodeId> originTreeScopeNodeId() {
            return optional(ORIGIN_TREE_SCOPE_NODE_ID);
        }
        /**
         * Media list array (for rules involving media queries). The array enumerates media queries starting with the innermost one, going outwards.
         * @return the protocol field value, empty when absent
         */
        public Optional<java.util.List<CSS.CSSMedia>> media() {
            return optional(MEDIA);
        }
        /**
         * Container query list array (for rules involving container queries). The array enumerates container queries starting with the innermost one, going outwards.
         * <p><b>Experimental:</b> this part of CDP may change without notice.
         * @return the protocol field value, empty when absent
         */
        public Optional<java.util.List<CSS.CSSContainerQuery>> containerQueries() {
            return optional(CONTAINER_QUERIES);
        }
        /**
         * &#64;supports CSS at-rule array. The array enumerates &#64;supports at-rules starting with the innermost one, going outwards.
         * <p><b>Experimental:</b> this part of CDP may change without notice.
         * @return the protocol field value, empty when absent
         */
        public Optional<java.util.List<CSS.CSSSupports>> supports() {
            return optional(SUPPORTS);
        }
        /**
         * Cascade layer array. Contains the layer hierarchy that this rule belongs to starting with the innermost layer and going outwards.
         * <p><b>Experimental:</b> this part of CDP may change without notice.
         * @return the protocol field value, empty when absent
         */
        public Optional<java.util.List<CSS.CSSLayer>> layers() {
            return optional(LAYERS);
        }
        /**
         * &#64;scope CSS at-rule array. The array enumerates &#64;scope at-rules starting with the innermost one, going outwards.
         * <p><b>Experimental:</b> this part of CDP may change without notice.
         * @return the protocol field value, empty when absent
         */
        public Optional<java.util.List<CSS.CSSScope>> scopes() {
            return optional(SCOPES);
        }
        /**
         * The array keeps the types of ancestor CSSRules from the innermost going outwards.
         * <p><b>Experimental:</b> this part of CDP may change without notice.
         * @return the protocol field value, empty when absent
         */
        public Optional<java.util.List<CSS.CSSRuleType>> ruleTypes() {
            return optional(RULE_TYPES);
        }
        /**
         * &#64;starting-style CSS at-rule array. The array enumerates &#64;starting-style at-rules starting with the innermost one, going outwards.
         * <p><b>Experimental:</b> this part of CDP may change without notice.
         * @return the protocol field value, empty when absent
         */
        public Optional<java.util.List<CSS.CSSStartingStyle>> startingStyles() {
            return optional(STARTING_STYLES);
        }
        /**
         * &#64;navigation CSS at-rule array. The array enumerates &#64;navigation at-rules starting with the innermost one, going outwards.
         * <p><b>Experimental:</b> this part of CDP may change without notice.
         * @return the protocol field value, empty when absent
         */
        public Optional<java.util.List<CSS.CSSNavigation>> navigations() {
            return optional(NAVIGATIONS);
        }
        /**
         * The css style sheet identifier (absent for user agent stylesheet and user-specified stylesheet rules) this rule came from.
         * @param styleSheetId field value
         * @return this model
         */
        public CSSRule styleSheetId(DOM.StyleSheetId styleSheetId) {
            set("styleSheetId", styleSheetId);
            return this;
        }
        /**
         * Rule selector data.
         * @param selectorList field value
         * @return this model
         */
        public CSSRule selectorList(CSS.SelectorList selectorList) {
            set("selectorList", selectorList);
            return this;
        }
        /**
         * Array of selectors from ancestor style rules, sorted by distance from the current rule.
         * <p><b>Experimental:</b> this part of CDP may change without notice.
         * @param nestingSelectors field value
         * @return this model
         */
        public CSSRule nestingSelectors(java.util.List<String> nestingSelectors) {
            set("nestingSelectors", nestingSelectors);
            return this;
        }
        /**
         * Parent stylesheet&#x27;s origin.
         * @param origin field value
         * @return this model
         */
        public CSSRule origin(CSS.StyleSheetOrigin origin) {
            set("origin", origin);
            return this;
        }
        /**
         * Associated style declaration.
         * @param style field value
         * @return this model
         */
        public CSSRule style(CSS.CSSStyle style) {
            set("style", style);
            return this;
        }
        /**
         * The BackendNodeId of the DOM node that constitutes the origin tree scope of this rule.
         * <p><b>Experimental:</b> this part of CDP may change without notice.
         * @param originTreeScopeNodeId field value
         * @return this model
         */
        public CSSRule originTreeScopeNodeId(DOM.BackendNodeId originTreeScopeNodeId) {
            set("originTreeScopeNodeId", originTreeScopeNodeId);
            return this;
        }
        /**
         * Media list array (for rules involving media queries). The array enumerates media queries starting with the innermost one, going outwards.
         * @param media field value
         * @return this model
         */
        public CSSRule media(java.util.List<CSS.CSSMedia> media) {
            set("media", media);
            return this;
        }
        /**
         * Container query list array (for rules involving container queries). The array enumerates container queries starting with the innermost one, going outwards.
         * <p><b>Experimental:</b> this part of CDP may change without notice.
         * @param containerQueries field value
         * @return this model
         */
        public CSSRule containerQueries(java.util.List<CSS.CSSContainerQuery> containerQueries) {
            set("containerQueries", containerQueries);
            return this;
        }
        /**
         * &#64;supports CSS at-rule array. The array enumerates &#64;supports at-rules starting with the innermost one, going outwards.
         * <p><b>Experimental:</b> this part of CDP may change without notice.
         * @param supports field value
         * @return this model
         */
        public CSSRule supports(java.util.List<CSS.CSSSupports> supports) {
            set("supports", supports);
            return this;
        }
        /**
         * Cascade layer array. Contains the layer hierarchy that this rule belongs to starting with the innermost layer and going outwards.
         * <p><b>Experimental:</b> this part of CDP may change without notice.
         * @param layers field value
         * @return this model
         */
        public CSSRule layers(java.util.List<CSS.CSSLayer> layers) {
            set("layers", layers);
            return this;
        }
        /**
         * &#64;scope CSS at-rule array. The array enumerates &#64;scope at-rules starting with the innermost one, going outwards.
         * <p><b>Experimental:</b> this part of CDP may change without notice.
         * @param scopes field value
         * @return this model
         */
        public CSSRule scopes(java.util.List<CSS.CSSScope> scopes) {
            set("scopes", scopes);
            return this;
        }
        /**
         * The array keeps the types of ancestor CSSRules from the innermost going outwards.
         * <p><b>Experimental:</b> this part of CDP may change without notice.
         * @param ruleTypes field value
         * @return this model
         */
        public CSSRule ruleTypes(java.util.List<CSS.CSSRuleType> ruleTypes) {
            set("ruleTypes", ruleTypes);
            return this;
        }
        /**
         * &#64;starting-style CSS at-rule array. The array enumerates &#64;starting-style at-rules starting with the innermost one, going outwards.
         * <p><b>Experimental:</b> this part of CDP may change without notice.
         * @param startingStyles field value
         * @return this model
         */
        public CSSRule startingStyles(java.util.List<CSS.CSSStartingStyle> startingStyles) {
            set("startingStyles", startingStyles);
            return this;
        }
        /**
         * &#64;navigation CSS at-rule array. The array enumerates &#64;navigation at-rules starting with the innermost one, going outwards.
         * <p><b>Experimental:</b> this part of CDP may change without notice.
         * @param navigations field value
         * @return this model
         */
        public CSSRule navigations(java.util.List<CSS.CSSNavigation> navigations) {
            set("navigations", navigations);
            return this;
        }
    }
    /**
     * Enum indicating the type of a CSS rule, used to represent the order of a style rule&#x27;s ancestors. This list only contains rule types that are collected during the ancestor rule collection.
     * <p><b>Experimental:</b> this part of CDP may change without notice.
     */
    public enum CSSRuleType implements CdpValue<String> {
        MEDIARULE("MediaRule"),
        SUPPORTSRULE("SupportsRule"),
        CONTAINERRULE("ContainerRule"),
        LAYERRULE("LayerRule"),
        SCOPERULE("ScopeRule"),
        STYLERULE("StyleRule"),
        STARTINGSTYLERULE("StartingStyleRule"),
        NAVIGATIONRULE("NavigationRule");
        public final String value;
        CSSRuleType(String value) { this.value = value; }
        @Override public String value() { return value; }
        public static CSSRuleType of(String value) {
            for (CSSRuleType constant : values()) {
                if (constant.value.equals(value)) return constant;
            }
            throw new IllegalArgumentException("Unknown CSSRuleType value: " + value);
        }
    }
    /**
     * CSS coverage information.
     */
    public static final class RuleUsage extends CdpObject {
        public RuleUsage() {}
        private RuleUsage(Map<String, Object> values) { super(values); }
        public static RuleUsage fromMap(Map<String, Object> values) {
            return new RuleUsage(values);
        }
        /**
         * The css style sheet identifier (absent for user agent stylesheet and user-specified stylesheet rules) this rule came from.
         * @return the protocol field value
         */
        public DOM.StyleSheetId styleSheetId() {
            return require(STYLE_SHEET_ID);
        }
        /**
         * Offset of the start of the rule (including selector) from the beginning of the stylesheet.
         * @return the protocol field value
         */
        public double startOffset() {
            return requireDouble("startOffset");
        }
        /**
         * Offset of the end of the rule body from the beginning of the stylesheet.
         * @return the protocol field value
         */
        public double endOffset() {
            return requireDouble("endOffset");
        }
        /**
         * Indicates whether the rule was actually used by some element in the page.
         * @return the protocol field value
         */
        public boolean used() {
            return requireBoolean("used");
        }
        /**
         * The css style sheet identifier (absent for user agent stylesheet and user-specified stylesheet rules) this rule came from.
         * @param styleSheetId field value
         * @return this model
         */
        public RuleUsage styleSheetId(DOM.StyleSheetId styleSheetId) {
            set("styleSheetId", styleSheetId);
            return this;
        }
        /**
         * Offset of the start of the rule (including selector) from the beginning of the stylesheet.
         * @param startOffset field value
         * @return this model
         */
        public RuleUsage startOffset(double startOffset) {
            set("startOffset", startOffset);
            return this;
        }
        /**
         * Offset of the end of the rule body from the beginning of the stylesheet.
         * @param endOffset field value
         * @return this model
         */
        public RuleUsage endOffset(double endOffset) {
            set("endOffset", endOffset);
            return this;
        }
        /**
         * Indicates whether the rule was actually used by some element in the page.
         * @param used field value
         * @return this model
         */
        public RuleUsage used(boolean used) {
            set("used", used);
            return this;
        }
    }
    /**
     * Text range within a resource. All numbers are zero-based.
     */
    public static final class SourceRange extends CdpObject {
        public SourceRange() {}
        private SourceRange(Map<String, Object> values) { super(values); }
        public static SourceRange fromMap(Map<String, Object> values) {
            return new SourceRange(values);
        }
        /**
         * Start line of range.
         * @return the protocol field value
         */
        public long startLine() {
            return requireLong("startLine");
        }
        /**
         * Start column of range (inclusive).
         * @return the protocol field value
         */
        public long startColumn() {
            return requireLong("startColumn");
        }
        /**
         * End line of range
         * @return the protocol field value
         */
        public long endLine() {
            return requireLong("endLine");
        }
        /**
         * End column of range (exclusive).
         * @return the protocol field value
         */
        public long endColumn() {
            return requireLong("endColumn");
        }
        /**
         * Start line of range.
         * @param startLine field value
         * @return this model
         */
        public SourceRange startLine(long startLine) {
            set("startLine", startLine);
            return this;
        }
        /**
         * Start column of range (inclusive).
         * @param startColumn field value
         * @return this model
         */
        public SourceRange startColumn(long startColumn) {
            set("startColumn", startColumn);
            return this;
        }
        /**
         * End line of range
         * @param endLine field value
         * @return this model
         */
        public SourceRange endLine(long endLine) {
            set("endLine", endLine);
            return this;
        }
        /**
         * End column of range (exclusive).
         * @param endColumn field value
         * @return this model
         */
        public SourceRange endColumn(long endColumn) {
            set("endColumn", endColumn);
            return this;
        }
    }
    /**
     */
    public static final class ShorthandEntry extends CdpObject {
        public ShorthandEntry() {}
        private ShorthandEntry(Map<String, Object> values) { super(values); }
        public static ShorthandEntry fromMap(Map<String, Object> values) {
            return new ShorthandEntry(values);
        }
        /**
         * Shorthand name.
         * @return the protocol field value
         */
        public String name() {
            return requireString("name");
        }
        /**
         * Shorthand value.
         * @return the protocol field value
         */
        public String value() {
            return requireString("value");
        }
        /**
         * Whether the property has &quot;!important&quot; annotation (implies {@code false} if absent).
         * @return the protocol field value, empty when absent
         */
        public Optional<Boolean> important() {
            return optionalBoolean("important");
        }
        /**
         * Shorthand name.
         * @param name field value
         * @return this model
         */
        public ShorthandEntry name(String name) {
            set("name", name);
            return this;
        }
        /**
         * Shorthand value.
         * @param value field value
         * @return this model
         */
        public ShorthandEntry value(String value) {
            set("value", value);
            return this;
        }
        /**
         * Whether the property has &quot;!important&quot; annotation (implies {@code false} if absent).
         * @param important field value
         * @return this model
         */
        public ShorthandEntry important(boolean important) {
            set("important", important);
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
     * <p><b>Experimental:</b> this part of CDP may change without notice.
     */
    public static final class ComputedStyleExtraFields extends CdpObject {
        public ComputedStyleExtraFields() {}
        private ComputedStyleExtraFields(Map<String, Object> values) { super(values); }
        public static ComputedStyleExtraFields fromMap(Map<String, Object> values) {
            return new ComputedStyleExtraFields(values);
        }
        /**
         * Returns whether or not this node is being rendered with base appearance, which happens when it has its appearance property set to base/base-select or it is in the subtree of an element being rendered with base appearance.
         * @return the protocol field value
         */
        public boolean isAppearanceBase() {
            return requireBoolean("isAppearanceBase");
        }
        /**
         * Returns whether or not this node is being rendered with base appearance, which happens when it has its appearance property set to base/base-select or it is in the subtree of an element being rendered with base appearance.
         * @param isAppearanceBase field value
         * @return this model
         */
        public ComputedStyleExtraFields isAppearanceBase(boolean isAppearanceBase) {
            set("isAppearanceBase", isAppearanceBase);
            return this;
        }
    }
    /**
     * CSS style representation.
     */
    public static final class CSSStyle extends CdpObject {
        public CSSStyle() {}
        private CSSStyle(Map<String, Object> values) { super(values); }
        public static CSSStyle fromMap(Map<String, Object> values) {
            return new CSSStyle(values);
        }
        /**
         * The css style sheet identifier (absent for user agent stylesheet and user-specified stylesheet rules) this rule came from.
         * @return the protocol field value, empty when absent
         */
        public Optional<DOM.StyleSheetId> styleSheetId() {
            return optional(STYLE_SHEET_ID);
        }
        /**
         * CSS properties in the style.
         * @return the protocol field value
         */
        public java.util.List<CSS.CSSProperty> cssProperties() {
            return require(CSS_PROPERTIES);
        }
        /**
         * Computed values for all shorthands found in the style.
         * @return the protocol field value
         */
        public java.util.List<CSS.ShorthandEntry> shorthandEntries() {
            return require(SHORTHAND_ENTRIES);
        }
        /**
         * Style declaration text (if available).
         * @return the protocol field value, empty when absent
         */
        public Optional<String> cssText() {
            return optionalString("cssText");
        }
        /**
         * Style declaration range in the enclosing stylesheet (if available).
         * @return the protocol field value, empty when absent
         */
        public Optional<CSS.SourceRange> range() {
            return optional(RANGE);
        }
        /**
         * The css style sheet identifier (absent for user agent stylesheet and user-specified stylesheet rules) this rule came from.
         * @param styleSheetId field value
         * @return this model
         */
        public CSSStyle styleSheetId(DOM.StyleSheetId styleSheetId) {
            set("styleSheetId", styleSheetId);
            return this;
        }
        /**
         * CSS properties in the style.
         * @param cssProperties field value
         * @return this model
         */
        public CSSStyle cssProperties(java.util.List<CSS.CSSProperty> cssProperties) {
            set("cssProperties", cssProperties);
            return this;
        }
        /**
         * Computed values for all shorthands found in the style.
         * @param shorthandEntries field value
         * @return this model
         */
        public CSSStyle shorthandEntries(java.util.List<CSS.ShorthandEntry> shorthandEntries) {
            set("shorthandEntries", shorthandEntries);
            return this;
        }
        /**
         * Style declaration text (if available).
         * @param cssText field value
         * @return this model
         */
        public CSSStyle cssText(String cssText) {
            set("cssText", cssText);
            return this;
        }
        /**
         * Style declaration range in the enclosing stylesheet (if available).
         * @param range field value
         * @return this model
         */
        public CSSStyle range(CSS.SourceRange range) {
            set("range", range);
            return this;
        }
    }
    /**
     * CSS property declaration data.
     */
    public static final class CSSProperty extends CdpObject {
        public CSSProperty() {}
        private CSSProperty(Map<String, Object> values) { super(values); }
        public static CSSProperty fromMap(Map<String, Object> values) {
            return new CSSProperty(values);
        }
        /**
         * The property name.
         * @return the protocol field value
         */
        public String name() {
            return requireString("name");
        }
        /**
         * The property value.
         * @return the protocol field value
         */
        public String value() {
            return requireString("value");
        }
        /**
         * Whether the property has &quot;!important&quot; annotation (implies {@code false} if absent).
         * @return the protocol field value, empty when absent
         */
        public Optional<Boolean> important() {
            return optionalBoolean("important");
        }
        /**
         * Whether the property is implicit (implies {@code false} if absent).
         * @return the protocol field value, empty when absent
         */
        public Optional<Boolean> implicit() {
            return optionalBoolean("implicit");
        }
        /**
         * The full property text as specified in the style.
         * @return the protocol field value, empty when absent
         */
        public Optional<String> text() {
            return optionalString("text");
        }
        /**
         * Whether the property is understood by the browser (implies {@code true} if absent).
         * @return the protocol field value, empty when absent
         */
        public Optional<Boolean> parsedOk() {
            return optionalBoolean("parsedOk");
        }
        /**
         * Whether the property is disabled by the user (present for source-based properties only).
         * @return the protocol field value, empty when absent
         */
        public Optional<Boolean> disabled() {
            return optionalBoolean("disabled");
        }
        /**
         * The entire property range in the enclosing style declaration (if available).
         * @return the protocol field value, empty when absent
         */
        public Optional<CSS.SourceRange> range() {
            return optional(RANGE);
        }
        /**
         * Parsed longhand components of this property if it is a shorthand. This field will be empty if the given property is not a shorthand.
         * <p><b>Experimental:</b> this part of CDP may change without notice.
         * @return the protocol field value, empty when absent
         */
        public Optional<java.util.List<CSS.CSSProperty>> longhandProperties() {
            return optional(LONGHAND_PROPERTIES);
        }
        /**
         * The property name.
         * @param name field value
         * @return this model
         */
        public CSSProperty name(String name) {
            set("name", name);
            return this;
        }
        /**
         * The property value.
         * @param value field value
         * @return this model
         */
        public CSSProperty value(String value) {
            set("value", value);
            return this;
        }
        /**
         * Whether the property has &quot;!important&quot; annotation (implies {@code false} if absent).
         * @param important field value
         * @return this model
         */
        public CSSProperty important(boolean important) {
            set("important", important);
            return this;
        }
        /**
         * Whether the property is implicit (implies {@code false} if absent).
         * @param implicit field value
         * @return this model
         */
        public CSSProperty implicit(boolean implicit) {
            set("implicit", implicit);
            return this;
        }
        /**
         * The full property text as specified in the style.
         * @param text field value
         * @return this model
         */
        public CSSProperty text(String text) {
            set("text", text);
            return this;
        }
        /**
         * Whether the property is understood by the browser (implies {@code true} if absent).
         * @param parsedOk field value
         * @return this model
         */
        public CSSProperty parsedOk(boolean parsedOk) {
            set("parsedOk", parsedOk);
            return this;
        }
        /**
         * Whether the property is disabled by the user (present for source-based properties only).
         * @param disabled field value
         * @return this model
         */
        public CSSProperty disabled(boolean disabled) {
            set("disabled", disabled);
            return this;
        }
        /**
         * The entire property range in the enclosing style declaration (if available).
         * @param range field value
         * @return this model
         */
        public CSSProperty range(CSS.SourceRange range) {
            set("range", range);
            return this;
        }
        /**
         * Parsed longhand components of this property if it is a shorthand. This field will be empty if the given property is not a shorthand.
         * <p><b>Experimental:</b> this part of CDP may change without notice.
         * @param longhandProperties field value
         * @return this model
         */
        public CSSProperty longhandProperties(java.util.List<CSS.CSSProperty> longhandProperties) {
            set("longhandProperties", longhandProperties);
            return this;
        }
    }
    /**
     * CSS media rule descriptor.
     */
    public static final class CSSMedia extends CdpObject {
        public CSSMedia() {}
        private CSSMedia(Map<String, Object> values) { super(values); }
        public static CSSMedia fromMap(Map<String, Object> values) {
            return new CSSMedia(values);
        }
        /**
         * Source of the media query: &quot;mediaRule&quot; if specified by a &#64;media rule, &quot;importRule&quot; if specified by an &#64;import rule, &quot;linkedSheet&quot; if specified by a &quot;media&quot; attribute in a linked stylesheet&#x27;s LINK tag, &quot;inlineSheet&quot; if specified by a &quot;media&quot; attribute in an inline stylesheet&#x27;s STYLE tag.
         */
        public enum SourceValues implements CdpValue<String> {
            MEDIARULE("mediaRule"),
            IMPORTRULE("importRule"),
            LINKEDSHEET("linkedSheet"),
            INLINESHEET("inlineSheet");
            public final String value;
            SourceValues(String value) { this.value = value; }
            @Override public String value() { return value; }
            public static SourceValues of(String value) {
                for (SourceValues constant : values()) {
                    if (constant.value.equals(value)) return constant;
                }
                throw new IllegalArgumentException("Unknown SourceValues value: " + value);
            }
        }
        /**
         * Media query text.
         * @return the protocol field value
         */
        public String text() {
            return requireString("text");
        }
        /**
         * Source of the media query: &quot;mediaRule&quot; if specified by a &#64;media rule, &quot;importRule&quot; if specified by an &#64;import rule, &quot;linkedSheet&quot; if specified by a &quot;media&quot; attribute in a linked stylesheet&#x27;s LINK tag, &quot;inlineSheet&quot; if specified by a &quot;media&quot; attribute in an inline stylesheet&#x27;s STYLE tag.
         * @return the protocol field value
         */
        public CSSMedia.SourceValues source() {
            return require(SOURCE);
        }
        /**
         * URL of the document containing the media query description.
         * @return the protocol field value, empty when absent
         */
        public Optional<String> sourceURL() {
            return optionalString("sourceURL");
        }
        /**
         * The associated rule (&#64;media or &#64;import) header range in the enclosing stylesheet (if available).
         * @return the protocol field value, empty when absent
         */
        public Optional<CSS.SourceRange> range() {
            return optional(RANGE);
        }
        /**
         * Identifier of the stylesheet containing this object (if exists).
         * @return the protocol field value, empty when absent
         */
        public Optional<DOM.StyleSheetId> styleSheetId() {
            return optional(STYLE_SHEET_ID);
        }
        /**
         * Array of media queries.
         * @return the protocol field value, empty when absent
         */
        public Optional<java.util.List<CSS.MediaQuery>> mediaList() {
            return optional(MEDIA_LIST);
        }
        /**
         * Media query text.
         * @param text field value
         * @return this model
         */
        public CSSMedia text(String text) {
            set("text", text);
            return this;
        }
        /**
         * Source of the media query: &quot;mediaRule&quot; if specified by a &#64;media rule, &quot;importRule&quot; if specified by an &#64;import rule, &quot;linkedSheet&quot; if specified by a &quot;media&quot; attribute in a linked stylesheet&#x27;s LINK tag, &quot;inlineSheet&quot; if specified by a &quot;media&quot; attribute in an inline stylesheet&#x27;s STYLE tag.
         * @param source field value
         * @return this model
         */
        public CSSMedia source(CSSMedia.SourceValues source) {
            set("source", source);
            return this;
        }
        /**
         * URL of the document containing the media query description.
         * @param sourceURL field value
         * @return this model
         */
        public CSSMedia sourceURL(String sourceURL) {
            set("sourceURL", sourceURL);
            return this;
        }
        /**
         * The associated rule (&#64;media or &#64;import) header range in the enclosing stylesheet (if available).
         * @param range field value
         * @return this model
         */
        public CSSMedia range(CSS.SourceRange range) {
            set("range", range);
            return this;
        }
        /**
         * Identifier of the stylesheet containing this object (if exists).
         * @param styleSheetId field value
         * @return this model
         */
        public CSSMedia styleSheetId(DOM.StyleSheetId styleSheetId) {
            set("styleSheetId", styleSheetId);
            return this;
        }
        /**
         * Array of media queries.
         * @param mediaList field value
         * @return this model
         */
        public CSSMedia mediaList(java.util.List<CSS.MediaQuery> mediaList) {
            set("mediaList", mediaList);
            return this;
        }
    }
    /**
     * Media query descriptor.
     */
    public static final class MediaQuery extends CdpObject {
        public MediaQuery() {}
        private MediaQuery(Map<String, Object> values) { super(values); }
        public static MediaQuery fromMap(Map<String, Object> values) {
            return new MediaQuery(values);
        }
        /**
         * Array of media query expressions.
         * @return the protocol field value
         */
        public java.util.List<CSS.MediaQueryExpression> expressions() {
            return require(EXPRESSIONS);
        }
        /**
         * Whether the media query condition is satisfied.
         * @return the protocol field value
         */
        public boolean active() {
            return requireBoolean("active");
        }
        /**
         * Array of media query expressions.
         * @param expressions field value
         * @return this model
         */
        public MediaQuery expressions(java.util.List<CSS.MediaQueryExpression> expressions) {
            set("expressions", expressions);
            return this;
        }
        /**
         * Whether the media query condition is satisfied.
         * @param active field value
         * @return this model
         */
        public MediaQuery active(boolean active) {
            set("active", active);
            return this;
        }
    }
    /**
     * Media query expression descriptor.
     */
    public static final class MediaQueryExpression extends CdpObject {
        public MediaQueryExpression() {}
        private MediaQueryExpression(Map<String, Object> values) { super(values); }
        public static MediaQueryExpression fromMap(Map<String, Object> values) {
            return new MediaQueryExpression(values);
        }
        /**
         * Media query expression value.
         * @return the protocol field value
         */
        public double value() {
            return requireDouble("value");
        }
        /**
         * Media query expression units.
         * @return the protocol field value
         */
        public String unit() {
            return requireString("unit");
        }
        /**
         * Media query expression feature.
         * @return the protocol field value
         */
        public String feature() {
            return requireString("feature");
        }
        /**
         * The associated range of the value text in the enclosing stylesheet (if available).
         * @return the protocol field value, empty when absent
         */
        public Optional<CSS.SourceRange> valueRange() {
            return optional(VALUE_RANGE);
        }
        /**
         * Computed length of media query expression (if applicable).
         * @return the protocol field value, empty when absent
         */
        public OptionalDouble computedLength() {
            return optionalDouble("computedLength");
        }
        /**
         * Media query expression value.
         * @param value field value
         * @return this model
         */
        public MediaQueryExpression value(double value) {
            set("value", value);
            return this;
        }
        /**
         * Media query expression units.
         * @param unit field value
         * @return this model
         */
        public MediaQueryExpression unit(String unit) {
            set("unit", unit);
            return this;
        }
        /**
         * Media query expression feature.
         * @param feature field value
         * @return this model
         */
        public MediaQueryExpression feature(String feature) {
            set("feature", feature);
            return this;
        }
        /**
         * The associated range of the value text in the enclosing stylesheet (if available).
         * @param valueRange field value
         * @return this model
         */
        public MediaQueryExpression valueRange(CSS.SourceRange valueRange) {
            set("valueRange", valueRange);
            return this;
        }
        /**
         * Computed length of media query expression (if applicable).
         * @param computedLength field value
         * @return this model
         */
        public MediaQueryExpression computedLength(double computedLength) {
            set("computedLength", computedLength);
            return this;
        }
    }
    /**
     * CSS container query rule descriptor.
     * <p><b>Experimental:</b> this part of CDP may change without notice.
     */
    public static final class CSSContainerQuery extends CdpObject {
        public CSSContainerQuery() {}
        private CSSContainerQuery(Map<String, Object> values) { super(values); }
        public static CSSContainerQuery fromMap(Map<String, Object> values) {
            return new CSSContainerQuery(values);
        }
        /**
         * Container query text. Contains the query part without the container name for a single query. Deprecated in favor of conditionText which contains the full prelude after &#64;container.
         * @return the protocol field value
         * @deprecated Deprecated by the Chromium DevTools Protocol.
         */
        @Deprecated
        public String text() {
            return requireString("text");
        }
        /**
         * The associated rule header range in the enclosing stylesheet (if available).
         * @return the protocol field value, empty when absent
         */
        public Optional<CSS.SourceRange> range() {
            return optional(RANGE);
        }
        /**
         * Identifier of the stylesheet containing this object (if exists).
         * @return the protocol field value, empty when absent
         */
        public Optional<DOM.StyleSheetId> styleSheetId() {
            return optional(STYLE_SHEET_ID);
        }
        /**
         * Optional name for the container.
         * @return the protocol field value, empty when absent
         */
        public Optional<String> name() {
            return optionalString("name");
        }
        /**
         * Optional physical axes queried for the container.
         * @return the protocol field value, empty when absent
         */
        public Optional<DOM.PhysicalAxes> physicalAxes() {
            return optional(PHYSICAL_AXES);
        }
        /**
         * Optional logical axes queried for the container.
         * @return the protocol field value, empty when absent
         */
        public Optional<DOM.LogicalAxes> logicalAxes() {
            return optional(LOGICAL_AXES);
        }
        /**
         * true if the query contains scroll-state() queries.
         * @return the protocol field value, empty when absent
         */
        public Optional<Boolean> queriesScrollState() {
            return optionalBoolean("queriesScrollState");
        }
        /**
         * true if the query contains anchored() queries.
         * @return the protocol field value, empty when absent
         */
        public Optional<Boolean> queriesAnchored() {
            return optionalBoolean("queriesAnchored");
        }
        /**
         * CSSContainerRule.conditionText
         * @return the protocol field value
         */
        public String conditionText() {
            return requireString("conditionText");
        }
        /**
         * Container query text. Contains the query part without the container name for a single query. Deprecated in favor of conditionText which contains the full prelude after &#64;container.
         * @param text field value
         * @return this model
         * @deprecated Deprecated by the Chromium DevTools Protocol.
         */
        @Deprecated
        public CSSContainerQuery text(String text) {
            set("text", text);
            return this;
        }
        /**
         * The associated rule header range in the enclosing stylesheet (if available).
         * @param range field value
         * @return this model
         */
        public CSSContainerQuery range(CSS.SourceRange range) {
            set("range", range);
            return this;
        }
        /**
         * Identifier of the stylesheet containing this object (if exists).
         * @param styleSheetId field value
         * @return this model
         */
        public CSSContainerQuery styleSheetId(DOM.StyleSheetId styleSheetId) {
            set("styleSheetId", styleSheetId);
            return this;
        }
        /**
         * Optional name for the container.
         * @param name field value
         * @return this model
         */
        public CSSContainerQuery name(String name) {
            set("name", name);
            return this;
        }
        /**
         * Optional physical axes queried for the container.
         * @param physicalAxes field value
         * @return this model
         */
        public CSSContainerQuery physicalAxes(DOM.PhysicalAxes physicalAxes) {
            set("physicalAxes", physicalAxes);
            return this;
        }
        /**
         * Optional logical axes queried for the container.
         * @param logicalAxes field value
         * @return this model
         */
        public CSSContainerQuery logicalAxes(DOM.LogicalAxes logicalAxes) {
            set("logicalAxes", logicalAxes);
            return this;
        }
        /**
         * true if the query contains scroll-state() queries.
         * @param queriesScrollState field value
         * @return this model
         */
        public CSSContainerQuery queriesScrollState(boolean queriesScrollState) {
            set("queriesScrollState", queriesScrollState);
            return this;
        }
        /**
         * true if the query contains anchored() queries.
         * @param queriesAnchored field value
         * @return this model
         */
        public CSSContainerQuery queriesAnchored(boolean queriesAnchored) {
            set("queriesAnchored", queriesAnchored);
            return this;
        }
        /**
         * CSSContainerRule.conditionText
         * @param conditionText field value
         * @return this model
         */
        public CSSContainerQuery conditionText(String conditionText) {
            set("conditionText", conditionText);
            return this;
        }
    }
    /**
     * CSS Supports at-rule descriptor.
     * <p><b>Experimental:</b> this part of CDP may change without notice.
     */
    public static final class CSSSupports extends CdpObject {
        public CSSSupports() {}
        private CSSSupports(Map<String, Object> values) { super(values); }
        public static CSSSupports fromMap(Map<String, Object> values) {
            return new CSSSupports(values);
        }
        /**
         * Supports rule text.
         * @return the protocol field value
         */
        public String text() {
            return requireString("text");
        }
        /**
         * Whether the supports condition is satisfied.
         * @return the protocol field value
         */
        public boolean active() {
            return requireBoolean("active");
        }
        /**
         * The associated rule header range in the enclosing stylesheet (if available).
         * @return the protocol field value, empty when absent
         */
        public Optional<CSS.SourceRange> range() {
            return optional(RANGE);
        }
        /**
         * Identifier of the stylesheet containing this object (if exists).
         * @return the protocol field value, empty when absent
         */
        public Optional<DOM.StyleSheetId> styleSheetId() {
            return optional(STYLE_SHEET_ID);
        }
        /**
         * Supports rule text.
         * @param text field value
         * @return this model
         */
        public CSSSupports text(String text) {
            set("text", text);
            return this;
        }
        /**
         * Whether the supports condition is satisfied.
         * @param active field value
         * @return this model
         */
        public CSSSupports active(boolean active) {
            set("active", active);
            return this;
        }
        /**
         * The associated rule header range in the enclosing stylesheet (if available).
         * @param range field value
         * @return this model
         */
        public CSSSupports range(CSS.SourceRange range) {
            set("range", range);
            return this;
        }
        /**
         * Identifier of the stylesheet containing this object (if exists).
         * @param styleSheetId field value
         * @return this model
         */
        public CSSSupports styleSheetId(DOM.StyleSheetId styleSheetId) {
            set("styleSheetId", styleSheetId);
            return this;
        }
    }
    /**
     * CSS Navigation at-rule descriptor.
     * <p><b>Experimental:</b> this part of CDP may change without notice.
     */
    public static final class CSSNavigation extends CdpObject {
        public CSSNavigation() {}
        private CSSNavigation(Map<String, Object> values) { super(values); }
        public static CSSNavigation fromMap(Map<String, Object> values) {
            return new CSSNavigation(values);
        }
        /**
         * Navigation rule text.
         * @return the protocol field value
         */
        public String text() {
            return requireString("text");
        }
        /**
         * Whether the navigation condition is satisfied.
         * @return the protocol field value, empty when absent
         */
        public Optional<Boolean> active() {
            return optionalBoolean("active");
        }
        /**
         * The associated rule header range in the enclosing stylesheet (if available).
         * @return the protocol field value, empty when absent
         */
        public Optional<CSS.SourceRange> range() {
            return optional(RANGE);
        }
        /**
         * Identifier of the stylesheet containing this object (if exists).
         * @return the protocol field value, empty when absent
         */
        public Optional<DOM.StyleSheetId> styleSheetId() {
            return optional(STYLE_SHEET_ID);
        }
        /**
         * Navigation rule text.
         * @param text field value
         * @return this model
         */
        public CSSNavigation text(String text) {
            set("text", text);
            return this;
        }
        /**
         * Whether the navigation condition is satisfied.
         * @param active field value
         * @return this model
         */
        public CSSNavigation active(boolean active) {
            set("active", active);
            return this;
        }
        /**
         * The associated rule header range in the enclosing stylesheet (if available).
         * @param range field value
         * @return this model
         */
        public CSSNavigation range(CSS.SourceRange range) {
            set("range", range);
            return this;
        }
        /**
         * Identifier of the stylesheet containing this object (if exists).
         * @param styleSheetId field value
         * @return this model
         */
        public CSSNavigation styleSheetId(DOM.StyleSheetId styleSheetId) {
            set("styleSheetId", styleSheetId);
            return this;
        }
    }
    /**
     * CSS Scope at-rule descriptor.
     * <p><b>Experimental:</b> this part of CDP may change without notice.
     */
    public static final class CSSScope extends CdpObject {
        public CSSScope() {}
        private CSSScope(Map<String, Object> values) { super(values); }
        public static CSSScope fromMap(Map<String, Object> values) {
            return new CSSScope(values);
        }
        /**
         * Scope rule text.
         * @return the protocol field value
         */
        public String text() {
            return requireString("text");
        }
        /**
         * The associated rule header range in the enclosing stylesheet (if available).
         * @return the protocol field value, empty when absent
         */
        public Optional<CSS.SourceRange> range() {
            return optional(RANGE);
        }
        /**
         * Identifier of the stylesheet containing this object (if exists).
         * @return the protocol field value, empty when absent
         */
        public Optional<DOM.StyleSheetId> styleSheetId() {
            return optional(STYLE_SHEET_ID);
        }
        /**
         * Scope rule text.
         * @param text field value
         * @return this model
         */
        public CSSScope text(String text) {
            set("text", text);
            return this;
        }
        /**
         * The associated rule header range in the enclosing stylesheet (if available).
         * @param range field value
         * @return this model
         */
        public CSSScope range(CSS.SourceRange range) {
            set("range", range);
            return this;
        }
        /**
         * Identifier of the stylesheet containing this object (if exists).
         * @param styleSheetId field value
         * @return this model
         */
        public CSSScope styleSheetId(DOM.StyleSheetId styleSheetId) {
            set("styleSheetId", styleSheetId);
            return this;
        }
    }
    /**
     * CSS Layer at-rule descriptor.
     * <p><b>Experimental:</b> this part of CDP may change without notice.
     */
    public static final class CSSLayer extends CdpObject {
        public CSSLayer() {}
        private CSSLayer(Map<String, Object> values) { super(values); }
        public static CSSLayer fromMap(Map<String, Object> values) {
            return new CSSLayer(values);
        }
        /**
         * Layer name.
         * @return the protocol field value
         */
        public String text() {
            return requireString("text");
        }
        /**
         * The associated rule header range in the enclosing stylesheet (if available).
         * @return the protocol field value, empty when absent
         */
        public Optional<CSS.SourceRange> range() {
            return optional(RANGE);
        }
        /**
         * Identifier of the stylesheet containing this object (if exists).
         * @return the protocol field value, empty when absent
         */
        public Optional<DOM.StyleSheetId> styleSheetId() {
            return optional(STYLE_SHEET_ID);
        }
        /**
         * Layer name.
         * @param text field value
         * @return this model
         */
        public CSSLayer text(String text) {
            set("text", text);
            return this;
        }
        /**
         * The associated rule header range in the enclosing stylesheet (if available).
         * @param range field value
         * @return this model
         */
        public CSSLayer range(CSS.SourceRange range) {
            set("range", range);
            return this;
        }
        /**
         * Identifier of the stylesheet containing this object (if exists).
         * @param styleSheetId field value
         * @return this model
         */
        public CSSLayer styleSheetId(DOM.StyleSheetId styleSheetId) {
            set("styleSheetId", styleSheetId);
            return this;
        }
    }
    /**
     * CSS Starting Style at-rule descriptor.
     * <p><b>Experimental:</b> this part of CDP may change without notice.
     */
    public static final class CSSStartingStyle extends CdpObject {
        public CSSStartingStyle() {}
        private CSSStartingStyle(Map<String, Object> values) { super(values); }
        public static CSSStartingStyle fromMap(Map<String, Object> values) {
            return new CSSStartingStyle(values);
        }
        /**
         * The associated rule header range in the enclosing stylesheet (if available).
         * @return the protocol field value, empty when absent
         */
        public Optional<CSS.SourceRange> range() {
            return optional(RANGE);
        }
        /**
         * Identifier of the stylesheet containing this object (if exists).
         * @return the protocol field value, empty when absent
         */
        public Optional<DOM.StyleSheetId> styleSheetId() {
            return optional(STYLE_SHEET_ID);
        }
        /**
         * The associated rule header range in the enclosing stylesheet (if available).
         * @param range field value
         * @return this model
         */
        public CSSStartingStyle range(CSS.SourceRange range) {
            set("range", range);
            return this;
        }
        /**
         * Identifier of the stylesheet containing this object (if exists).
         * @param styleSheetId field value
         * @return this model
         */
        public CSSStartingStyle styleSheetId(DOM.StyleSheetId styleSheetId) {
            set("styleSheetId", styleSheetId);
            return this;
        }
    }
    /**
     * CSS Layer data.
     * <p><b>Experimental:</b> this part of CDP may change without notice.
     */
    public static final class CSSLayerData extends CdpObject {
        public CSSLayerData() {}
        private CSSLayerData(Map<String, Object> values) { super(values); }
        public static CSSLayerData fromMap(Map<String, Object> values) {
            return new CSSLayerData(values);
        }
        /**
         * Layer name.
         * @return the protocol field value
         */
        public String name() {
            return requireString("name");
        }
        /**
         * Direct sub-layers
         * @return the protocol field value, empty when absent
         */
        public Optional<java.util.List<CSS.CSSLayerData>> subLayers() {
            return optional(SUB_LAYERS);
        }
        /**
         * Layer order. The order determines the order of the layer in the cascade order. A higher number has higher priority in the cascade order.
         * @return the protocol field value
         */
        public double order() {
            return requireDouble("order");
        }
        /**
         * Layer name.
         * @param name field value
         * @return this model
         */
        public CSSLayerData name(String name) {
            set("name", name);
            return this;
        }
        /**
         * Direct sub-layers
         * @param subLayers field value
         * @return this model
         */
        public CSSLayerData subLayers(java.util.List<CSS.CSSLayerData> subLayers) {
            set("subLayers", subLayers);
            return this;
        }
        /**
         * Layer order. The order determines the order of the layer in the cascade order. A higher number has higher priority in the cascade order.
         * @param order field value
         * @return this model
         */
        public CSSLayerData order(double order) {
            set("order", order);
            return this;
        }
    }
    /**
     * Information about amount of glyphs that were rendered with given font.
     */
    public static final class PlatformFontUsage extends CdpObject {
        public PlatformFontUsage() {}
        private PlatformFontUsage(Map<String, Object> values) { super(values); }
        public static PlatformFontUsage fromMap(Map<String, Object> values) {
            return new PlatformFontUsage(values);
        }
        /**
         * Font&#x27;s family name reported by platform.
         * @return the protocol field value
         */
        public String familyName() {
            return requireString("familyName");
        }
        /**
         * Font&#x27;s PostScript name reported by platform.
         * @return the protocol field value
         */
        public String postScriptName() {
            return requireString("postScriptName");
        }
        /**
         * Indicates if the font was downloaded or resolved locally.
         * @return the protocol field value
         */
        public boolean isCustomFont() {
            return requireBoolean("isCustomFont");
        }
        /**
         * Amount of glyphs that were rendered with this font.
         * @return the protocol field value
         */
        public double glyphCount() {
            return requireDouble("glyphCount");
        }
        /**
         * Font&#x27;s family name reported by platform.
         * @param familyName field value
         * @return this model
         */
        public PlatformFontUsage familyName(String familyName) {
            set("familyName", familyName);
            return this;
        }
        /**
         * Font&#x27;s PostScript name reported by platform.
         * @param postScriptName field value
         * @return this model
         */
        public PlatformFontUsage postScriptName(String postScriptName) {
            set("postScriptName", postScriptName);
            return this;
        }
        /**
         * Indicates if the font was downloaded or resolved locally.
         * @param isCustomFont field value
         * @return this model
         */
        public PlatformFontUsage isCustomFont(boolean isCustomFont) {
            set("isCustomFont", isCustomFont);
            return this;
        }
        /**
         * Amount of glyphs that were rendered with this font.
         * @param glyphCount field value
         * @return this model
         */
        public PlatformFontUsage glyphCount(double glyphCount) {
            set("glyphCount", glyphCount);
            return this;
        }
    }
    /**
     * Information about font variation axes for variable fonts
     */
    public static final class FontVariationAxis extends CdpObject {
        public FontVariationAxis() {}
        private FontVariationAxis(Map<String, Object> values) { super(values); }
        public static FontVariationAxis fromMap(Map<String, Object> values) {
            return new FontVariationAxis(values);
        }
        /**
         * The font-variation-setting tag (a.k.a. &quot;axis tag&quot;).
         * @return the protocol field value
         */
        public String tag() {
            return requireString("tag");
        }
        /**
         * Human-readable variation name in the default language (normally, &quot;en&quot;).
         * @return the protocol field value
         */
        public String name() {
            return requireString("name");
        }
        /**
         * The minimum value (inclusive) the font supports for this tag.
         * @return the protocol field value
         */
        public double minValue() {
            return requireDouble("minValue");
        }
        /**
         * The maximum value (inclusive) the font supports for this tag.
         * @return the protocol field value
         */
        public double maxValue() {
            return requireDouble("maxValue");
        }
        /**
         * The default value.
         * @return the protocol field value
         */
        public double defaultValue() {
            return requireDouble("defaultValue");
        }
        /**
         * The font-variation-setting tag (a.k.a. &quot;axis tag&quot;).
         * @param tag field value
         * @return this model
         */
        public FontVariationAxis tag(String tag) {
            set("tag", tag);
            return this;
        }
        /**
         * Human-readable variation name in the default language (normally, &quot;en&quot;).
         * @param name field value
         * @return this model
         */
        public FontVariationAxis name(String name) {
            set("name", name);
            return this;
        }
        /**
         * The minimum value (inclusive) the font supports for this tag.
         * @param minValue field value
         * @return this model
         */
        public FontVariationAxis minValue(double minValue) {
            set("minValue", minValue);
            return this;
        }
        /**
         * The maximum value (inclusive) the font supports for this tag.
         * @param maxValue field value
         * @return this model
         */
        public FontVariationAxis maxValue(double maxValue) {
            set("maxValue", maxValue);
            return this;
        }
        /**
         * The default value.
         * @param defaultValue field value
         * @return this model
         */
        public FontVariationAxis defaultValue(double defaultValue) {
            set("defaultValue", defaultValue);
            return this;
        }
    }
    /**
     * Properties of a web font: https://www.w3.org/TR/2008/REC-CSS2-20080411/fonts.html#font-descriptions and additional information such as platformFontFamily and fontVariationAxes.
     */
    public static final class FontFace extends CdpObject {
        public FontFace() {}
        private FontFace(Map<String, Object> values) { super(values); }
        public static FontFace fromMap(Map<String, Object> values) {
            return new FontFace(values);
        }
        /**
         * The font-family.
         * @return the protocol field value
         */
        public String fontFamily() {
            return requireString("fontFamily");
        }
        /**
         * The font-style.
         * @return the protocol field value
         */
        public String fontStyle() {
            return requireString("fontStyle");
        }
        /**
         * The font-variant.
         * @return the protocol field value
         */
        public String fontVariant() {
            return requireString("fontVariant");
        }
        /**
         * The font-weight.
         * @return the protocol field value
         */
        public String fontWeight() {
            return requireString("fontWeight");
        }
        /**
         * The font-stretch.
         * @return the protocol field value
         */
        public String fontStretch() {
            return requireString("fontStretch");
        }
        /**
         * The font-display.
         * @return the protocol field value
         */
        public String fontDisplay() {
            return requireString("fontDisplay");
        }
        /**
         * The unicode-range.
         * @return the protocol field value
         */
        public String unicodeRange() {
            return requireString("unicodeRange");
        }
        /**
         * The src.
         * @return the protocol field value
         */
        public String src() {
            return requireString("src");
        }
        /**
         * The resolved platform font family
         * @return the protocol field value
         */
        public String platformFontFamily() {
            return requireString("platformFontFamily");
        }
        /**
         * Available variation settings (a.k.a. &quot;axes&quot;).
         * @return the protocol field value, empty when absent
         */
        public Optional<java.util.List<CSS.FontVariationAxis>> fontVariationAxes() {
            return optional(FONT_VARIATION_AXES);
        }
        /**
         * The font-family.
         * @param fontFamily field value
         * @return this model
         */
        public FontFace fontFamily(String fontFamily) {
            set("fontFamily", fontFamily);
            return this;
        }
        /**
         * The font-style.
         * @param fontStyle field value
         * @return this model
         */
        public FontFace fontStyle(String fontStyle) {
            set("fontStyle", fontStyle);
            return this;
        }
        /**
         * The font-variant.
         * @param fontVariant field value
         * @return this model
         */
        public FontFace fontVariant(String fontVariant) {
            set("fontVariant", fontVariant);
            return this;
        }
        /**
         * The font-weight.
         * @param fontWeight field value
         * @return this model
         */
        public FontFace fontWeight(String fontWeight) {
            set("fontWeight", fontWeight);
            return this;
        }
        /**
         * The font-stretch.
         * @param fontStretch field value
         * @return this model
         */
        public FontFace fontStretch(String fontStretch) {
            set("fontStretch", fontStretch);
            return this;
        }
        /**
         * The font-display.
         * @param fontDisplay field value
         * @return this model
         */
        public FontFace fontDisplay(String fontDisplay) {
            set("fontDisplay", fontDisplay);
            return this;
        }
        /**
         * The unicode-range.
         * @param unicodeRange field value
         * @return this model
         */
        public FontFace unicodeRange(String unicodeRange) {
            set("unicodeRange", unicodeRange);
            return this;
        }
        /**
         * The src.
         * @param src field value
         * @return this model
         */
        public FontFace src(String src) {
            set("src", src);
            return this;
        }
        /**
         * The resolved platform font family
         * @param platformFontFamily field value
         * @return this model
         */
        public FontFace platformFontFamily(String platformFontFamily) {
            set("platformFontFamily", platformFontFamily);
            return this;
        }
        /**
         * Available variation settings (a.k.a. &quot;axes&quot;).
         * @param fontVariationAxes field value
         * @return this model
         */
        public FontFace fontVariationAxes(java.util.List<CSS.FontVariationAxis> fontVariationAxes) {
            set("fontVariationAxes", fontVariationAxes);
            return this;
        }
    }
    /**
     * CSS try rule representation.
     */
    public static final class CSSTryRule extends CdpObject {
        public CSSTryRule() {}
        private CSSTryRule(Map<String, Object> values) { super(values); }
        public static CSSTryRule fromMap(Map<String, Object> values) {
            return new CSSTryRule(values);
        }
        /**
         * The css style sheet identifier (absent for user agent stylesheet and user-specified stylesheet rules) this rule came from.
         * @return the protocol field value, empty when absent
         */
        public Optional<DOM.StyleSheetId> styleSheetId() {
            return optional(STYLE_SHEET_ID);
        }
        /**
         * Parent stylesheet&#x27;s origin.
         * @return the protocol field value
         */
        public CSS.StyleSheetOrigin origin() {
            return require(ORIGIN);
        }
        /**
         * Associated style declaration.
         * @return the protocol field value
         */
        public CSS.CSSStyle style() {
            return require(STYLE);
        }
        /**
         * The css style sheet identifier (absent for user agent stylesheet and user-specified stylesheet rules) this rule came from.
         * @param styleSheetId field value
         * @return this model
         */
        public CSSTryRule styleSheetId(DOM.StyleSheetId styleSheetId) {
            set("styleSheetId", styleSheetId);
            return this;
        }
        /**
         * Parent stylesheet&#x27;s origin.
         * @param origin field value
         * @return this model
         */
        public CSSTryRule origin(CSS.StyleSheetOrigin origin) {
            set("origin", origin);
            return this;
        }
        /**
         * Associated style declaration.
         * @param style field value
         * @return this model
         */
        public CSSTryRule style(CSS.CSSStyle style) {
            set("style", style);
            return this;
        }
    }
    /**
     * CSS &#64;position-try rule representation.
     */
    public static final class CSSPositionTryRule extends CdpObject {
        public CSSPositionTryRule() {}
        private CSSPositionTryRule(Map<String, Object> values) { super(values); }
        public static CSSPositionTryRule fromMap(Map<String, Object> values) {
            return new CSSPositionTryRule(values);
        }
        /**
         * The prelude dashed-ident name
         * @return the protocol field value
         */
        public CSS.Value name() {
            return require(NAME);
        }
        /**
         * The css style sheet identifier (absent for user agent stylesheet and user-specified stylesheet rules) this rule came from.
         * @return the protocol field value, empty when absent
         */
        public Optional<DOM.StyleSheetId> styleSheetId() {
            return optional(STYLE_SHEET_ID);
        }
        /**
         * Parent stylesheet&#x27;s origin.
         * @return the protocol field value
         */
        public CSS.StyleSheetOrigin origin() {
            return require(ORIGIN);
        }
        /**
         * Associated style declaration.
         * @return the protocol field value
         */
        public CSS.CSSStyle style() {
            return require(STYLE);
        }
        /**
         * Returns the active field.
         * @return the protocol field value
         */
        public boolean active() {
            return requireBoolean("active");
        }
        /**
         * The prelude dashed-ident name
         * @param name field value
         * @return this model
         */
        public CSSPositionTryRule name(CSS.Value name) {
            set("name", name);
            return this;
        }
        /**
         * The css style sheet identifier (absent for user agent stylesheet and user-specified stylesheet rules) this rule came from.
         * @param styleSheetId field value
         * @return this model
         */
        public CSSPositionTryRule styleSheetId(DOM.StyleSheetId styleSheetId) {
            set("styleSheetId", styleSheetId);
            return this;
        }
        /**
         * Parent stylesheet&#x27;s origin.
         * @param origin field value
         * @return this model
         */
        public CSSPositionTryRule origin(CSS.StyleSheetOrigin origin) {
            set("origin", origin);
            return this;
        }
        /**
         * Associated style declaration.
         * @param style field value
         * @return this model
         */
        public CSSPositionTryRule style(CSS.CSSStyle style) {
            set("style", style);
            return this;
        }
        /**
         * Sets the active field.
         * @param active field value
         * @return this model
         */
        public CSSPositionTryRule active(boolean active) {
            set("active", active);
            return this;
        }
    }
    /**
     * CSS keyframes rule representation.
     */
    public static final class CSSKeyframesRule extends CdpObject {
        public CSSKeyframesRule() {}
        private CSSKeyframesRule(Map<String, Object> values) { super(values); }
        public static CSSKeyframesRule fromMap(Map<String, Object> values) {
            return new CSSKeyframesRule(values);
        }
        /**
         * Animation name.
         * @return the protocol field value
         */
        public CSS.Value animationName() {
            return require(ANIMATION_NAME);
        }
        /**
         * List of keyframes.
         * @return the protocol field value
         */
        public java.util.List<CSS.CSSKeyframeRule> keyframes() {
            return require(KEYFRAMES);
        }
        /**
         * Animation name.
         * @param animationName field value
         * @return this model
         */
        public CSSKeyframesRule animationName(CSS.Value animationName) {
            set("animationName", animationName);
            return this;
        }
        /**
         * List of keyframes.
         * @param keyframes field value
         * @return this model
         */
        public CSSKeyframesRule keyframes(java.util.List<CSS.CSSKeyframeRule> keyframes) {
            set("keyframes", keyframes);
            return this;
        }
    }
    /**
     * Representation of a custom property registration through CSS.registerProperty
     */
    public static final class CSSPropertyRegistration extends CdpObject {
        public CSSPropertyRegistration() {}
        private CSSPropertyRegistration(Map<String, Object> values) { super(values); }
        public static CSSPropertyRegistration fromMap(Map<String, Object> values) {
            return new CSSPropertyRegistration(values);
        }
        /**
         * Returns the propertyName field.
         * @return the protocol field value
         */
        public String propertyName() {
            return requireString("propertyName");
        }
        /**
         * Returns the initialValue field.
         * @return the protocol field value, empty when absent
         */
        public Optional<CSS.Value> initialValue() {
            return optional(INITIAL_VALUE);
        }
        /**
         * Returns the inherits field.
         * @return the protocol field value
         */
        public boolean inherits() {
            return requireBoolean("inherits");
        }
        /**
         * Returns the syntax field.
         * @return the protocol field value
         */
        public String syntax() {
            return requireString("syntax");
        }
        /**
         * Sets the propertyName field.
         * @param propertyName field value
         * @return this model
         */
        public CSSPropertyRegistration propertyName(String propertyName) {
            set("propertyName", propertyName);
            return this;
        }
        /**
         * Sets the initialValue field.
         * @param initialValue field value
         * @return this model
         */
        public CSSPropertyRegistration initialValue(CSS.Value initialValue) {
            set("initialValue", initialValue);
            return this;
        }
        /**
         * Sets the inherits field.
         * @param inherits field value
         * @return this model
         */
        public CSSPropertyRegistration inherits(boolean inherits) {
            set("inherits", inherits);
            return this;
        }
        /**
         * Sets the syntax field.
         * @param syntax field value
         * @return this model
         */
        public CSSPropertyRegistration syntax(String syntax) {
            set("syntax", syntax);
            return this;
        }
    }
    /**
     * CSS generic &#64;rule representation.
     */
    public static final class CSSAtRule extends CdpObject {
        public CSSAtRule() {}
        private CSSAtRule(Map<String, Object> values) { super(values); }
        public static CSSAtRule fromMap(Map<String, Object> values) {
            return new CSSAtRule(values);
        }
        /**
         * Type of at-rule.
         */
        public enum TypeValues implements CdpValue<String> {
            FONT_FACE("font-face"),
            FONT_FEATURE_VALUES("font-feature-values"),
            FONT_PALETTE_VALUES("font-palette-values"),
            COUNTER_STYLE("counter-style");
            public final String value;
            TypeValues(String value) { this.value = value; }
            @Override public String value() { return value; }
            public static TypeValues of(String value) {
                for (TypeValues constant : values()) {
                    if (constant.value.equals(value)) return constant;
                }
                throw new IllegalArgumentException("Unknown TypeValues value: " + value);
            }
        }
        /**
         * Subsection of font-feature-values, if this is a subsection.
         */
        public enum SubsectionValues implements CdpValue<String> {
            SWASH("swash"),
            ANNOTATION("annotation"),
            ORNAMENTS("ornaments"),
            STYLISTIC("stylistic"),
            STYLESET("styleset"),
            CHARACTER_VARIANT("character-variant");
            public final String value;
            SubsectionValues(String value) { this.value = value; }
            @Override public String value() { return value; }
            public static SubsectionValues of(String value) {
                for (SubsectionValues constant : values()) {
                    if (constant.value.equals(value)) return constant;
                }
                throw new IllegalArgumentException("Unknown SubsectionValues value: " + value);
            }
        }
        /**
         * Type of at-rule.
         * @return the protocol field value
         */
        public CSSAtRule.TypeValues type() {
            return require(TYPE);
        }
        /**
         * Subsection of font-feature-values, if this is a subsection.
         * @return the protocol field value, empty when absent
         */
        public Optional<CSSAtRule.SubsectionValues> subsection() {
            return optional(SUBSECTION);
        }
        /**
         * LINT.ThenChange(//third_party/blink/renderer/core/inspector/inspector_style_sheet.cc:FontVariantAlternatesFeatureType,//third_party/blink/renderer/core/inspector/inspector_css_agent.cc:FontVariantAlternatesFeatureType) Associated name, if applicable.
         * @return the protocol field value, empty when absent
         */
        public Optional<CSS.Value> name() {
            return optional(NAME);
        }
        /**
         * The css style sheet identifier (absent for user agent stylesheet and user-specified stylesheet rules) this rule came from.
         * @return the protocol field value, empty when absent
         */
        public Optional<DOM.StyleSheetId> styleSheetId() {
            return optional(STYLE_SHEET_ID);
        }
        /**
         * Parent stylesheet&#x27;s origin.
         * @return the protocol field value
         */
        public CSS.StyleSheetOrigin origin() {
            return require(ORIGIN);
        }
        /**
         * Associated style declaration.
         * @return the protocol field value
         */
        public CSS.CSSStyle style() {
            return require(STYLE);
        }
        /**
         * Type of at-rule.
         * @param type field value
         * @return this model
         */
        public CSSAtRule type(CSSAtRule.TypeValues type) {
            set("type", type);
            return this;
        }
        /**
         * Subsection of font-feature-values, if this is a subsection.
         * @param subsection field value
         * @return this model
         */
        public CSSAtRule subsection(CSSAtRule.SubsectionValues subsection) {
            set("subsection", subsection);
            return this;
        }
        /**
         * LINT.ThenChange(//third_party/blink/renderer/core/inspector/inspector_style_sheet.cc:FontVariantAlternatesFeatureType,//third_party/blink/renderer/core/inspector/inspector_css_agent.cc:FontVariantAlternatesFeatureType) Associated name, if applicable.
         * @param name field value
         * @return this model
         */
        public CSSAtRule name(CSS.Value name) {
            set("name", name);
            return this;
        }
        /**
         * The css style sheet identifier (absent for user agent stylesheet and user-specified stylesheet rules) this rule came from.
         * @param styleSheetId field value
         * @return this model
         */
        public CSSAtRule styleSheetId(DOM.StyleSheetId styleSheetId) {
            set("styleSheetId", styleSheetId);
            return this;
        }
        /**
         * Parent stylesheet&#x27;s origin.
         * @param origin field value
         * @return this model
         */
        public CSSAtRule origin(CSS.StyleSheetOrigin origin) {
            set("origin", origin);
            return this;
        }
        /**
         * Associated style declaration.
         * @param style field value
         * @return this model
         */
        public CSSAtRule style(CSS.CSSStyle style) {
            set("style", style);
            return this;
        }
    }
    /**
     * CSS property at-rule representation.
     */
    public static final class CSSPropertyRule extends CdpObject {
        public CSSPropertyRule() {}
        private CSSPropertyRule(Map<String, Object> values) { super(values); }
        public static CSSPropertyRule fromMap(Map<String, Object> values) {
            return new CSSPropertyRule(values);
        }
        /**
         * The css style sheet identifier (absent for user agent stylesheet and user-specified stylesheet rules) this rule came from.
         * @return the protocol field value, empty when absent
         */
        public Optional<DOM.StyleSheetId> styleSheetId() {
            return optional(STYLE_SHEET_ID);
        }
        /**
         * Parent stylesheet&#x27;s origin.
         * @return the protocol field value
         */
        public CSS.StyleSheetOrigin origin() {
            return require(ORIGIN);
        }
        /**
         * Associated property name.
         * @return the protocol field value
         */
        public CSS.Value propertyName() {
            return require(PROPERTY_NAME);
        }
        /**
         * Associated style declaration.
         * @return the protocol field value
         */
        public CSS.CSSStyle style() {
            return require(STYLE);
        }
        /**
         * The css style sheet identifier (absent for user agent stylesheet and user-specified stylesheet rules) this rule came from.
         * @param styleSheetId field value
         * @return this model
         */
        public CSSPropertyRule styleSheetId(DOM.StyleSheetId styleSheetId) {
            set("styleSheetId", styleSheetId);
            return this;
        }
        /**
         * Parent stylesheet&#x27;s origin.
         * @param origin field value
         * @return this model
         */
        public CSSPropertyRule origin(CSS.StyleSheetOrigin origin) {
            set("origin", origin);
            return this;
        }
        /**
         * Associated property name.
         * @param propertyName field value
         * @return this model
         */
        public CSSPropertyRule propertyName(CSS.Value propertyName) {
            set("propertyName", propertyName);
            return this;
        }
        /**
         * Associated style declaration.
         * @param style field value
         * @return this model
         */
        public CSSPropertyRule style(CSS.CSSStyle style) {
            set("style", style);
            return this;
        }
    }
    /**
     * CSS function argument representation.
     */
    public static final class CSSFunctionParameter extends CdpObject {
        public CSSFunctionParameter() {}
        private CSSFunctionParameter(Map<String, Object> values) { super(values); }
        public static CSSFunctionParameter fromMap(Map<String, Object> values) {
            return new CSSFunctionParameter(values);
        }
        /**
         * The parameter name.
         * @return the protocol field value
         */
        public String name() {
            return requireString("name");
        }
        /**
         * The parameter type.
         * @return the protocol field value
         */
        public String type() {
            return requireString("type");
        }
        /**
         * The parameter name.
         * @param name field value
         * @return this model
         */
        public CSSFunctionParameter name(String name) {
            set("name", name);
            return this;
        }
        /**
         * The parameter type.
         * @param type field value
         * @return this model
         */
        public CSSFunctionParameter type(String type) {
            set("type", type);
            return this;
        }
    }
    /**
     * CSS function conditional block representation.
     */
    public static final class CSSFunctionConditionNode extends CdpObject {
        public CSSFunctionConditionNode() {}
        private CSSFunctionConditionNode(Map<String, Object> values) { super(values); }
        public static CSSFunctionConditionNode fromMap(Map<String, Object> values) {
            return new CSSFunctionConditionNode(values);
        }
        /**
         * Media query for this conditional block. Only one type of condition should be set.
         * @return the protocol field value, empty when absent
         */
        public Optional<CSS.CSSMedia> media() {
            return optional(MEDIA_2);
        }
        /**
         * Container query for this conditional block. Only one type of condition should be set.
         * @return the protocol field value, empty when absent
         */
        public Optional<CSS.CSSContainerQuery> containerQueries() {
            return optional(CONTAINER_QUERIES_2);
        }
        /**
         * &#64;supports CSS at-rule condition. Only one type of condition should be set.
         * @return the protocol field value, empty when absent
         */
        public Optional<CSS.CSSSupports> supports() {
            return optional(SUPPORTS_2);
        }
        /**
         * &#64;navigation condition. Only one type of condition should be set.
         * @return the protocol field value, empty when absent
         */
        public Optional<CSS.CSSNavigation> navigation() {
            return optional(NAVIGATION);
        }
        /**
         * Block body.
         * @return the protocol field value
         */
        public java.util.List<CSS.CSSFunctionNode> children() {
            return require(CHILDREN);
        }
        /**
         * The condition text.
         * @return the protocol field value
         */
        public String conditionText() {
            return requireString("conditionText");
        }
        /**
         * Media query for this conditional block. Only one type of condition should be set.
         * @param media field value
         * @return this model
         */
        public CSSFunctionConditionNode media(CSS.CSSMedia media) {
            set("media", media);
            return this;
        }
        /**
         * Container query for this conditional block. Only one type of condition should be set.
         * @param containerQueries field value
         * @return this model
         */
        public CSSFunctionConditionNode containerQueries(CSS.CSSContainerQuery containerQueries) {
            set("containerQueries", containerQueries);
            return this;
        }
        /**
         * &#64;supports CSS at-rule condition. Only one type of condition should be set.
         * @param supports field value
         * @return this model
         */
        public CSSFunctionConditionNode supports(CSS.CSSSupports supports) {
            set("supports", supports);
            return this;
        }
        /**
         * &#64;navigation condition. Only one type of condition should be set.
         * @param navigation field value
         * @return this model
         */
        public CSSFunctionConditionNode navigation(CSS.CSSNavigation navigation) {
            set("navigation", navigation);
            return this;
        }
        /**
         * Block body.
         * @param children field value
         * @return this model
         */
        public CSSFunctionConditionNode children(java.util.List<CSS.CSSFunctionNode> children) {
            set("children", children);
            return this;
        }
        /**
         * The condition text.
         * @param conditionText field value
         * @return this model
         */
        public CSSFunctionConditionNode conditionText(String conditionText) {
            set("conditionText", conditionText);
            return this;
        }
    }
    /**
     * Section of the body of a CSS function rule.
     */
    public static final class CSSFunctionNode extends CdpObject {
        public CSSFunctionNode() {}
        private CSSFunctionNode(Map<String, Object> values) { super(values); }
        public static CSSFunctionNode fromMap(Map<String, Object> values) {
            return new CSSFunctionNode(values);
        }
        /**
         * A conditional block. If set, style should not be set.
         * @return the protocol field value, empty when absent
         */
        public Optional<CSS.CSSFunctionConditionNode> condition() {
            return optional(CONDITION);
        }
        /**
         * Values set by this node. If set, condition should not be set.
         * @return the protocol field value, empty when absent
         */
        public Optional<CSS.CSSStyle> style() {
            return optional(STYLE);
        }
        /**
         * A conditional block. If set, style should not be set.
         * @param condition field value
         * @return this model
         */
        public CSSFunctionNode condition(CSS.CSSFunctionConditionNode condition) {
            set("condition", condition);
            return this;
        }
        /**
         * Values set by this node. If set, condition should not be set.
         * @param style field value
         * @return this model
         */
        public CSSFunctionNode style(CSS.CSSStyle style) {
            set("style", style);
            return this;
        }
    }
    /**
     * CSS function at-rule representation.
     */
    public static final class CSSFunctionRule extends CdpObject {
        public CSSFunctionRule() {}
        private CSSFunctionRule(Map<String, Object> values) { super(values); }
        public static CSSFunctionRule fromMap(Map<String, Object> values) {
            return new CSSFunctionRule(values);
        }
        /**
         * Name of the function.
         * @return the protocol field value
         */
        public CSS.Value name() {
            return require(NAME);
        }
        /**
         * The css style sheet identifier (absent for user agent stylesheet and user-specified stylesheet rules) this rule came from.
         * @return the protocol field value, empty when absent
         */
        public Optional<DOM.StyleSheetId> styleSheetId() {
            return optional(STYLE_SHEET_ID);
        }
        /**
         * Parent stylesheet&#x27;s origin.
         * @return the protocol field value
         */
        public CSS.StyleSheetOrigin origin() {
            return require(ORIGIN);
        }
        /**
         * List of parameters.
         * @return the protocol field value
         */
        public java.util.List<CSS.CSSFunctionParameter> parameters() {
            return require(PARAMETERS);
        }
        /**
         * Function body.
         * @return the protocol field value
         */
        public java.util.List<CSS.CSSFunctionNode> children() {
            return require(CHILDREN);
        }
        /**
         * The BackendNodeId of the DOM node that constitutes the origin tree scope of this rule.
         * <p><b>Experimental:</b> this part of CDP may change without notice.
         * @return the protocol field value, empty when absent
         */
        public Optional<DOM.BackendNodeId> originTreeScopeNodeId() {
            return optional(ORIGIN_TREE_SCOPE_NODE_ID);
        }
        /**
         * Name of the function.
         * @param name field value
         * @return this model
         */
        public CSSFunctionRule name(CSS.Value name) {
            set("name", name);
            return this;
        }
        /**
         * The css style sheet identifier (absent for user agent stylesheet and user-specified stylesheet rules) this rule came from.
         * @param styleSheetId field value
         * @return this model
         */
        public CSSFunctionRule styleSheetId(DOM.StyleSheetId styleSheetId) {
            set("styleSheetId", styleSheetId);
            return this;
        }
        /**
         * Parent stylesheet&#x27;s origin.
         * @param origin field value
         * @return this model
         */
        public CSSFunctionRule origin(CSS.StyleSheetOrigin origin) {
            set("origin", origin);
            return this;
        }
        /**
         * List of parameters.
         * @param parameters field value
         * @return this model
         */
        public CSSFunctionRule parameters(java.util.List<CSS.CSSFunctionParameter> parameters) {
            set("parameters", parameters);
            return this;
        }
        /**
         * Function body.
         * @param children field value
         * @return this model
         */
        public CSSFunctionRule children(java.util.List<CSS.CSSFunctionNode> children) {
            set("children", children);
            return this;
        }
        /**
         * The BackendNodeId of the DOM node that constitutes the origin tree scope of this rule.
         * <p><b>Experimental:</b> this part of CDP may change without notice.
         * @param originTreeScopeNodeId field value
         * @return this model
         */
        public CSSFunctionRule originTreeScopeNodeId(DOM.BackendNodeId originTreeScopeNodeId) {
            set("originTreeScopeNodeId", originTreeScopeNodeId);
            return this;
        }
    }
    /**
     * CSS keyframe rule representation.
     */
    public static final class CSSKeyframeRule extends CdpObject {
        public CSSKeyframeRule() {}
        private CSSKeyframeRule(Map<String, Object> values) { super(values); }
        public static CSSKeyframeRule fromMap(Map<String, Object> values) {
            return new CSSKeyframeRule(values);
        }
        /**
         * The css style sheet identifier (absent for user agent stylesheet and user-specified stylesheet rules) this rule came from.
         * @return the protocol field value, empty when absent
         */
        public Optional<DOM.StyleSheetId> styleSheetId() {
            return optional(STYLE_SHEET_ID);
        }
        /**
         * Parent stylesheet&#x27;s origin.
         * @return the protocol field value
         */
        public CSS.StyleSheetOrigin origin() {
            return require(ORIGIN);
        }
        /**
         * Associated key text.
         * @return the protocol field value
         */
        public CSS.Value keyText() {
            return require(KEY_TEXT);
        }
        /**
         * Associated style declaration.
         * @return the protocol field value
         */
        public CSS.CSSStyle style() {
            return require(STYLE);
        }
        /**
         * The css style sheet identifier (absent for user agent stylesheet and user-specified stylesheet rules) this rule came from.
         * @param styleSheetId field value
         * @return this model
         */
        public CSSKeyframeRule styleSheetId(DOM.StyleSheetId styleSheetId) {
            set("styleSheetId", styleSheetId);
            return this;
        }
        /**
         * Parent stylesheet&#x27;s origin.
         * @param origin field value
         * @return this model
         */
        public CSSKeyframeRule origin(CSS.StyleSheetOrigin origin) {
            set("origin", origin);
            return this;
        }
        /**
         * Associated key text.
         * @param keyText field value
         * @return this model
         */
        public CSSKeyframeRule keyText(CSS.Value keyText) {
            set("keyText", keyText);
            return this;
        }
        /**
         * Associated style declaration.
         * @param style field value
         * @return this model
         */
        public CSSKeyframeRule style(CSS.CSSStyle style) {
            set("style", style);
            return this;
        }
    }
    /**
     * A descriptor of operation to mutate style declaration text.
     */
    public static final class StyleDeclarationEdit extends CdpObject {
        public StyleDeclarationEdit() {}
        private StyleDeclarationEdit(Map<String, Object> values) { super(values); }
        public static StyleDeclarationEdit fromMap(Map<String, Object> values) {
            return new StyleDeclarationEdit(values);
        }
        /**
         * The css style sheet identifier.
         * @return the protocol field value
         */
        public DOM.StyleSheetId styleSheetId() {
            return require(STYLE_SHEET_ID);
        }
        /**
         * The range of the style text in the enclosing stylesheet.
         * @return the protocol field value
         */
        public CSS.SourceRange range() {
            return require(RANGE);
        }
        /**
         * New style text.
         * @return the protocol field value
         */
        public String text() {
            return requireString("text");
        }
        /**
         * The css style sheet identifier.
         * @param styleSheetId field value
         * @return this model
         */
        public StyleDeclarationEdit styleSheetId(DOM.StyleSheetId styleSheetId) {
            set("styleSheetId", styleSheetId);
            return this;
        }
        /**
         * The range of the style text in the enclosing stylesheet.
         * @param range field value
         * @return this model
         */
        public StyleDeclarationEdit range(CSS.SourceRange range) {
            set("range", range);
            return this;
        }
        /**
         * New style text.
         * @param text field value
         * @return this model
         */
        public StyleDeclarationEdit text(String text) {
            set("text", text);
            return this;
        }
    }
    /**
     * Inserts a new rule with the given {@code ruleText} in a stylesheet with given {@code styleSheetId}, at the position specified by {@code location}.
     */
    public static final class AddRuleRequest extends CdpObject {
        public AddRuleRequest() {}
        public static AddRuleRequest fromMap(Map<String, Object> values) {
            final AddRuleRequest instance_ = new AddRuleRequest();
            if (values != null) instance_.values.putAll(values);
            return instance_;
        }
        /**
         * The css style sheet identifier where a new rule should be inserted.
         * @return the protocol field value
         */
        public DOM.StyleSheetId styleSheetId() {
            return require(STYLE_SHEET_ID);
        }
        /**
         * The text of a new rule.
         * @return the protocol field value
         */
        public String ruleText() {
            return requireString("ruleText");
        }
        /**
         * Text position of a new rule in the target style sheet.
         * @return the protocol field value
         */
        public CSS.SourceRange location() {
            return require(LOCATION);
        }
        /**
         * NodeId for the DOM node in whose context custom property declarations for registered properties should be validated. If omitted, declarations in the new rule text can only be validated statically, which may produce incorrect results if the declaration contains a var() for example.
         * <p><b>Experimental:</b> this part of CDP may change without notice.
         * @return the protocol field value, empty when absent
         */
        public Optional<DOM.NodeId> nodeForPropertySyntaxValidation() {
            return optional(NODE_FOR_PROPERTY_SYNTAX_VALIDATION);
        }
        /**
         * The css style sheet identifier where a new rule should be inserted.
         * @param styleSheetId field value
         * @return this model
         */
        public AddRuleRequest styleSheetId(DOM.StyleSheetId styleSheetId) {
            set("styleSheetId", styleSheetId);
            return this;
        }
        /**
         * The text of a new rule.
         * @param ruleText field value
         * @return this model
         */
        public AddRuleRequest ruleText(String ruleText) {
            set("ruleText", ruleText);
            return this;
        }
        /**
         * Text position of a new rule in the target style sheet.
         * @param location field value
         * @return this model
         */
        public AddRuleRequest location(CSS.SourceRange location) {
            set("location", location);
            return this;
        }
        /**
         * NodeId for the DOM node in whose context custom property declarations for registered properties should be validated. If omitted, declarations in the new rule text can only be validated statically, which may produce incorrect results if the declaration contains a var() for example.
         * <p><b>Experimental:</b> this part of CDP may change without notice.
         * @param nodeForPropertySyntaxValidation field value
         * @return this model
         */
        public AddRuleRequest nodeForPropertySyntaxValidation(DOM.NodeId nodeForPropertySyntaxValidation) {
            set("nodeForPropertySyntaxValidation", nodeForPropertySyntaxValidation);
            return this;
        }
    }
    /**
     * Returns all class names from specified stylesheet.
     */
    public static final class CollectClassNamesRequest extends CdpObject {
        public CollectClassNamesRequest() {}
        public static CollectClassNamesRequest fromMap(Map<String, Object> values) {
            final CollectClassNamesRequest instance_ = new CollectClassNamesRequest();
            if (values != null) instance_.values.putAll(values);
            return instance_;
        }
        /**
         * Returns the styleSheetId field.
         * @return the protocol field value
         */
        public DOM.StyleSheetId styleSheetId() {
            return require(STYLE_SHEET_ID);
        }
        /**
         * Sets the styleSheetId field.
         * @param styleSheetId field value
         * @return this model
         */
        public CollectClassNamesRequest styleSheetId(DOM.StyleSheetId styleSheetId) {
            set("styleSheetId", styleSheetId);
            return this;
        }
    }
    /**
     * Creates a new special &quot;via-inspector&quot; stylesheet in the frame with given {@code frameId}.
     */
    public static final class CreateStyleSheetRequest extends CdpObject {
        public CreateStyleSheetRequest() {}
        public static CreateStyleSheetRequest fromMap(Map<String, Object> values) {
            final CreateStyleSheetRequest instance_ = new CreateStyleSheetRequest();
            if (values != null) instance_.values.putAll(values);
            return instance_;
        }
        /**
         * Identifier of the frame where &quot;via-inspector&quot; stylesheet should be created.
         * @return the protocol field value
         */
        public Page.FrameId frameId() {
            return require(FRAME_ID);
        }
        /**
         * If true, creates a new stylesheet for every call. If false, returns a stylesheet previously created by a call with force=false for the frame&#x27;s document if it exists or creates a new stylesheet (default: false).
         * @return the protocol field value, empty when absent
         */
        public Optional<Boolean> force() {
            return optionalBoolean("force");
        }
        /**
         * Identifier of the frame where &quot;via-inspector&quot; stylesheet should be created.
         * @param frameId field value
         * @return this model
         */
        public CreateStyleSheetRequest frameId(Page.FrameId frameId) {
            set("frameId", frameId);
            return this;
        }
        /**
         * If true, creates a new stylesheet for every call. If false, returns a stylesheet previously created by a call with force=false for the frame&#x27;s document if it exists or creates a new stylesheet (default: false).
         * @param force field value
         * @return this model
         */
        public CreateStyleSheetRequest force(boolean force) {
            set("force", force);
            return this;
        }
    }
    /**
     * Ensures that the given node will have specified pseudo-classes whenever its style is computed by the browser.
     */
    public static final class ForcePseudoStateRequest extends CdpObject {
        public ForcePseudoStateRequest() {}
        public static ForcePseudoStateRequest fromMap(Map<String, Object> values) {
            final ForcePseudoStateRequest instance_ = new ForcePseudoStateRequest();
            if (values != null) instance_.values.putAll(values);
            return instance_;
        }
        /**
         * The element id for which to force the pseudo state.
         * @return the protocol field value
         */
        public DOM.NodeId nodeId() {
            return require(NODE_ID);
        }
        /**
         * Element pseudo classes to force when computing the element&#x27;s style.
         * @return the protocol field value
         */
        public java.util.List<String> forcedPseudoClasses() {
            return require(FORCED_PSEUDO_CLASSES);
        }
        /**
         * The element id for which to force the pseudo state.
         * @param nodeId field value
         * @return this model
         */
        public ForcePseudoStateRequest nodeId(DOM.NodeId nodeId) {
            set("nodeId", nodeId);
            return this;
        }
        /**
         * Element pseudo classes to force when computing the element&#x27;s style.
         * @param forcedPseudoClasses field value
         * @return this model
         */
        public ForcePseudoStateRequest forcedPseudoClasses(java.util.List<String> forcedPseudoClasses) {
            set("forcedPseudoClasses", forcedPseudoClasses);
            return this;
        }
    }
    /**
     * Ensures that the given node is in its starting-style state.
     */
    public static final class ForceStartingStyleRequest extends CdpObject {
        public ForceStartingStyleRequest() {}
        public static ForceStartingStyleRequest fromMap(Map<String, Object> values) {
            final ForceStartingStyleRequest instance_ = new ForceStartingStyleRequest();
            if (values != null) instance_.values.putAll(values);
            return instance_;
        }
        /**
         * The element id for which to force the starting-style state.
         * @return the protocol field value
         */
        public DOM.NodeId nodeId() {
            return require(NODE_ID);
        }
        /**
         * Boolean indicating if this is on or off.
         * @return the protocol field value
         */
        public boolean forced() {
            return requireBoolean("forced");
        }
        /**
         * The element id for which to force the starting-style state.
         * @param nodeId field value
         * @return this model
         */
        public ForceStartingStyleRequest nodeId(DOM.NodeId nodeId) {
            set("nodeId", nodeId);
            return this;
        }
        /**
         * Boolean indicating if this is on or off.
         * @param forced field value
         * @return this model
         */
        public ForceStartingStyleRequest forced(boolean forced) {
            set("forced", forced);
            return this;
        }
    }
    /**
     * Request parameters for CSS.getBackgroundColors.
     */
    public static final class GetBackgroundColorsRequest extends CdpObject {
        public GetBackgroundColorsRequest() {}
        public static GetBackgroundColorsRequest fromMap(Map<String, Object> values) {
            final GetBackgroundColorsRequest instance_ = new GetBackgroundColorsRequest();
            if (values != null) instance_.values.putAll(values);
            return instance_;
        }
        /**
         * Id of the node to get background colors for.
         * @return the protocol field value
         */
        public DOM.NodeId nodeId() {
            return require(NODE_ID);
        }
        /**
         * Id of the node to get background colors for.
         * @param nodeId field value
         * @return this model
         */
        public GetBackgroundColorsRequest nodeId(DOM.NodeId nodeId) {
            set("nodeId", nodeId);
            return this;
        }
    }
    /**
     * Returns the computed style for a DOM node identified by {@code nodeId}.
     */
    public static final class GetComputedStyleForNodeRequest extends CdpObject {
        public GetComputedStyleForNodeRequest() {}
        public static GetComputedStyleForNodeRequest fromMap(Map<String, Object> values) {
            final GetComputedStyleForNodeRequest instance_ = new GetComputedStyleForNodeRequest();
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
         * Sets the nodeId field.
         * @param nodeId field value
         * @return this model
         */
        public GetComputedStyleForNodeRequest nodeId(DOM.NodeId nodeId) {
            set("nodeId", nodeId);
            return this;
        }
    }
    /**
     * Resolve the specified values in the context of the provided element. For example, a value of &#x27;1em&#x27; is evaluated according to the computed &#x27;font-size&#x27; of the element and a value &#x27;calc(1px + 2px)&#x27; will be resolved to &#x27;3px&#x27;. If the {@code propertyName} was specified the {@code values} are resolved as if they were property&#x27;s declaration. If a value cannot be parsed according to the provided property syntax, the value is parsed using combined syntax as if null {@code propertyName} was provided. If the value cannot be resolved even then, return the provided value without any changes. Note: this function currently does not resolve CSS random() function, it returns unmodified random() function parts.`
     * <p><b>Experimental:</b> this part of CDP may change without notice.
     */
    public static final class ResolveValuesRequest extends CdpObject {
        public ResolveValuesRequest() {}
        public static ResolveValuesRequest fromMap(Map<String, Object> values) {
            final ResolveValuesRequest instance_ = new ResolveValuesRequest();
            if (values != null) instance_.values.putAll(values);
            return instance_;
        }
        /**
         * Cascade-dependent keywords (revert/revert-layer) do not work.
         * @return the protocol field value
         */
        public java.util.List<String> values() {
            return require(VALUES);
        }
        /**
         * Id of the node in whose context the expression is evaluated
         * @return the protocol field value
         */
        public DOM.NodeId nodeId() {
            return require(NODE_ID);
        }
        /**
         * Only longhands and custom property names are accepted.
         * @return the protocol field value, empty when absent
         */
        public Optional<String> propertyName() {
            return optionalString("propertyName");
        }
        /**
         * Pseudo element type, only works for pseudo elements that generate elements in the tree, such as ::before and ::after.
         * @return the protocol field value, empty when absent
         */
        public Optional<DOM.PseudoType> pseudoType() {
            return optional(PSEUDO_TYPE);
        }
        /**
         * Pseudo element custom ident.
         * @return the protocol field value, empty when absent
         */
        public Optional<String> pseudoIdentifier() {
            return optionalString("pseudoIdentifier");
        }
        /**
         * Cascade-dependent keywords (revert/revert-layer) do not work.
         * @param values field value
         * @return this model
         */
        public ResolveValuesRequest values(java.util.List<String> values) {
            set("values", values);
            return this;
        }
        /**
         * Id of the node in whose context the expression is evaluated
         * @param nodeId field value
         * @return this model
         */
        public ResolveValuesRequest nodeId(DOM.NodeId nodeId) {
            set("nodeId", nodeId);
            return this;
        }
        /**
         * Only longhands and custom property names are accepted.
         * @param propertyName field value
         * @return this model
         */
        public ResolveValuesRequest propertyName(String propertyName) {
            set("propertyName", propertyName);
            return this;
        }
        /**
         * Pseudo element type, only works for pseudo elements that generate elements in the tree, such as ::before and ::after.
         * @param pseudoType field value
         * @return this model
         */
        public ResolveValuesRequest pseudoType(DOM.PseudoType pseudoType) {
            set("pseudoType", pseudoType);
            return this;
        }
        /**
         * Pseudo element custom ident.
         * @param pseudoIdentifier field value
         * @return this model
         */
        public ResolveValuesRequest pseudoIdentifier(String pseudoIdentifier) {
            set("pseudoIdentifier", pseudoIdentifier);
            return this;
        }
    }
    /**
     * Request parameters for CSS.getLonghandProperties.
     * <p><b>Experimental:</b> this part of CDP may change without notice.
     */
    public static final class GetLonghandPropertiesRequest extends CdpObject {
        public GetLonghandPropertiesRequest() {}
        public static GetLonghandPropertiesRequest fromMap(Map<String, Object> values) {
            final GetLonghandPropertiesRequest instance_ = new GetLonghandPropertiesRequest();
            if (values != null) instance_.values.putAll(values);
            return instance_;
        }
        /**
         * Returns the shorthandName field.
         * @return the protocol field value
         */
        public String shorthandName() {
            return requireString("shorthandName");
        }
        /**
         * Returns the value field.
         * @return the protocol field value
         */
        public String value() {
            return requireString("value");
        }
        /**
         * Sets the shorthandName field.
         * @param shorthandName field value
         * @return this model
         */
        public GetLonghandPropertiesRequest shorthandName(String shorthandName) {
            set("shorthandName", shorthandName);
            return this;
        }
        /**
         * Sets the value field.
         * @param value field value
         * @return this model
         */
        public GetLonghandPropertiesRequest value(String value) {
            set("value", value);
            return this;
        }
    }
    /**
     * Returns the styles defined inline (explicitly in the &quot;style&quot; attribute and implicitly, using DOM attributes) for a DOM node identified by {@code nodeId}.
     */
    public static final class GetInlineStylesForNodeRequest extends CdpObject {
        public GetInlineStylesForNodeRequest() {}
        public static GetInlineStylesForNodeRequest fromMap(Map<String, Object> values) {
            final GetInlineStylesForNodeRequest instance_ = new GetInlineStylesForNodeRequest();
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
         * Sets the nodeId field.
         * @param nodeId field value
         * @return this model
         */
        public GetInlineStylesForNodeRequest nodeId(DOM.NodeId nodeId) {
            set("nodeId", nodeId);
            return this;
        }
    }
    /**
     * Returns the styles coming from animations &amp; transitions including the animation &amp; transition styles coming from inheritance chain.
     * <p><b>Experimental:</b> this part of CDP may change without notice.
     */
    public static final class GetAnimatedStylesForNodeRequest extends CdpObject {
        public GetAnimatedStylesForNodeRequest() {}
        public static GetAnimatedStylesForNodeRequest fromMap(Map<String, Object> values) {
            final GetAnimatedStylesForNodeRequest instance_ = new GetAnimatedStylesForNodeRequest();
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
         * Sets the nodeId field.
         * @param nodeId field value
         * @return this model
         */
        public GetAnimatedStylesForNodeRequest nodeId(DOM.NodeId nodeId) {
            set("nodeId", nodeId);
            return this;
        }
    }
    /**
     * Returns requested styles for a DOM node identified by {@code nodeId}.
     */
    public static final class GetMatchedStylesForNodeRequest extends CdpObject {
        public GetMatchedStylesForNodeRequest() {}
        public static GetMatchedStylesForNodeRequest fromMap(Map<String, Object> values) {
            final GetMatchedStylesForNodeRequest instance_ = new GetMatchedStylesForNodeRequest();
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
         * Sets the nodeId field.
         * @param nodeId field value
         * @return this model
         */
        public GetMatchedStylesForNodeRequest nodeId(DOM.NodeId nodeId) {
            set("nodeId", nodeId);
            return this;
        }
    }
    /**
     * Requests information about platform fonts which we used to render child TextNodes in the given node.
     */
    public static final class GetPlatformFontsForNodeRequest extends CdpObject {
        public GetPlatformFontsForNodeRequest() {}
        public static GetPlatformFontsForNodeRequest fromMap(Map<String, Object> values) {
            final GetPlatformFontsForNodeRequest instance_ = new GetPlatformFontsForNodeRequest();
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
         * Sets the nodeId field.
         * @param nodeId field value
         * @return this model
         */
        public GetPlatformFontsForNodeRequest nodeId(DOM.NodeId nodeId) {
            set("nodeId", nodeId);
            return this;
        }
    }
    /**
     * Returns the current textual content for a stylesheet.
     */
    public static final class GetStyleSheetTextRequest extends CdpObject {
        public GetStyleSheetTextRequest() {}
        public static GetStyleSheetTextRequest fromMap(Map<String, Object> values) {
            final GetStyleSheetTextRequest instance_ = new GetStyleSheetTextRequest();
            if (values != null) instance_.values.putAll(values);
            return instance_;
        }
        /**
         * Returns the styleSheetId field.
         * @return the protocol field value
         */
        public DOM.StyleSheetId styleSheetId() {
            return require(STYLE_SHEET_ID);
        }
        /**
         * Sets the styleSheetId field.
         * @param styleSheetId field value
         * @return this model
         */
        public GetStyleSheetTextRequest styleSheetId(DOM.StyleSheetId styleSheetId) {
            set("styleSheetId", styleSheetId);
            return this;
        }
    }
    /**
     * Returns all layers parsed by the rendering engine for the tree scope of a node. Given a DOM element identified by nodeId, getLayersForNode returns the root layer for the nearest ancestor document or shadow root. The layer root contains the full layer tree for the tree scope and their ordering.
     * <p><b>Experimental:</b> this part of CDP may change without notice.
     */
    public static final class GetLayersForNodeRequest extends CdpObject {
        public GetLayersForNodeRequest() {}
        public static GetLayersForNodeRequest fromMap(Map<String, Object> values) {
            final GetLayersForNodeRequest instance_ = new GetLayersForNodeRequest();
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
         * Sets the nodeId field.
         * @param nodeId field value
         * @return this model
         */
        public GetLayersForNodeRequest nodeId(DOM.NodeId nodeId) {
            set("nodeId", nodeId);
            return this;
        }
    }
    /**
     * Given a CSS selector text and a style sheet ID, getLocationForSelector returns an array of locations of the CSS selector in the style sheet.
     * <p><b>Experimental:</b> this part of CDP may change without notice.
     */
    public static final class GetLocationForSelectorRequest extends CdpObject {
        public GetLocationForSelectorRequest() {}
        public static GetLocationForSelectorRequest fromMap(Map<String, Object> values) {
            final GetLocationForSelectorRequest instance_ = new GetLocationForSelectorRequest();
            if (values != null) instance_.values.putAll(values);
            return instance_;
        }
        /**
         * Returns the styleSheetId field.
         * @return the protocol field value
         */
        public DOM.StyleSheetId styleSheetId() {
            return require(STYLE_SHEET_ID);
        }
        /**
         * Returns the selectorText field.
         * @return the protocol field value
         */
        public String selectorText() {
            return requireString("selectorText");
        }
        /**
         * Sets the styleSheetId field.
         * @param styleSheetId field value
         * @return this model
         */
        public GetLocationForSelectorRequest styleSheetId(DOM.StyleSheetId styleSheetId) {
            set("styleSheetId", styleSheetId);
            return this;
        }
        /**
         * Sets the selectorText field.
         * @param selectorText field value
         * @return this model
         */
        public GetLocationForSelectorRequest selectorText(String selectorText) {
            set("selectorText", selectorText);
            return this;
        }
    }
    /**
     * Starts tracking the given node for the computed style updates and whenever the computed style is updated for node, it queues a {@code computedStyleUpdated} event with throttling. There can only be 1 node tracked for computed style updates so passing a new node id removes tracking from the previous node. Pass {@code undefined} to disable tracking.
     * <p><b>Experimental:</b> this part of CDP may change without notice.
     */
    public static final class TrackComputedStyleUpdatesForNodeRequest extends CdpObject {
        public TrackComputedStyleUpdatesForNodeRequest() {}
        public static TrackComputedStyleUpdatesForNodeRequest fromMap(Map<String, Object> values) {
            final TrackComputedStyleUpdatesForNodeRequest instance_ = new TrackComputedStyleUpdatesForNodeRequest();
            if (values != null) instance_.values.putAll(values);
            return instance_;
        }
        /**
         * Returns the nodeId field.
         * @return the protocol field value, empty when absent
         */
        public Optional<DOM.NodeId> nodeId() {
            return optional(NODE_ID);
        }
        /**
         * Sets the nodeId field.
         * @param nodeId field value
         * @return this model
         */
        public TrackComputedStyleUpdatesForNodeRequest nodeId(DOM.NodeId nodeId) {
            set("nodeId", nodeId);
            return this;
        }
    }
    /**
     * Starts tracking the given computed styles for updates. The specified array of properties replaces the one previously specified. Pass empty array to disable tracking. Use takeComputedStyleUpdates to retrieve the list of nodes that had properties modified. The changes to computed style properties are only tracked for nodes pushed to the front-end by the DOM agent. If no changes to the tracked properties occur after the node has been pushed to the front-end, no updates will be issued for the node.
     * <p><b>Experimental:</b> this part of CDP may change without notice.
     */
    public static final class TrackComputedStyleUpdatesRequest extends CdpObject {
        public TrackComputedStyleUpdatesRequest() {}
        public static TrackComputedStyleUpdatesRequest fromMap(Map<String, Object> values) {
            final TrackComputedStyleUpdatesRequest instance_ = new TrackComputedStyleUpdatesRequest();
            if (values != null) instance_.values.putAll(values);
            return instance_;
        }
        /**
         * Returns the propertiesToTrack field.
         * @return the protocol field value
         */
        public java.util.List<CSS.CSSComputedStyleProperty> propertiesToTrack() {
            return require(PROPERTIES_TO_TRACK);
        }
        /**
         * Sets the propertiesToTrack field.
         * @param propertiesToTrack field value
         * @return this model
         */
        public TrackComputedStyleUpdatesRequest propertiesToTrack(java.util.List<CSS.CSSComputedStyleProperty> propertiesToTrack) {
            set("propertiesToTrack", propertiesToTrack);
            return this;
        }
    }
    /**
     * Find a rule with the given active property for the given node and set the new value for this property
     */
    public static final class SetEffectivePropertyValueForNodeRequest extends CdpObject {
        public SetEffectivePropertyValueForNodeRequest() {}
        public static SetEffectivePropertyValueForNodeRequest fromMap(Map<String, Object> values) {
            final SetEffectivePropertyValueForNodeRequest instance_ = new SetEffectivePropertyValueForNodeRequest();
            if (values != null) instance_.values.putAll(values);
            return instance_;
        }
        /**
         * The element id for which to set property.
         * @return the protocol field value
         */
        public DOM.NodeId nodeId() {
            return require(NODE_ID);
        }
        /**
         * Returns the propertyName field.
         * @return the protocol field value
         */
        public String propertyName() {
            return requireString("propertyName");
        }
        /**
         * Returns the value field.
         * @return the protocol field value
         */
        public String value() {
            return requireString("value");
        }
        /**
         * The element id for which to set property.
         * @param nodeId field value
         * @return this model
         */
        public SetEffectivePropertyValueForNodeRequest nodeId(DOM.NodeId nodeId) {
            set("nodeId", nodeId);
            return this;
        }
        /**
         * Sets the propertyName field.
         * @param propertyName field value
         * @return this model
         */
        public SetEffectivePropertyValueForNodeRequest propertyName(String propertyName) {
            set("propertyName", propertyName);
            return this;
        }
        /**
         * Sets the value field.
         * @param value field value
         * @return this model
         */
        public SetEffectivePropertyValueForNodeRequest value(String value) {
            set("value", value);
            return this;
        }
    }
    /**
     * Modifies the property rule property name.
     */
    public static final class SetPropertyRulePropertyNameRequest extends CdpObject {
        public SetPropertyRulePropertyNameRequest() {}
        public static SetPropertyRulePropertyNameRequest fromMap(Map<String, Object> values) {
            final SetPropertyRulePropertyNameRequest instance_ = new SetPropertyRulePropertyNameRequest();
            if (values != null) instance_.values.putAll(values);
            return instance_;
        }
        /**
         * Returns the styleSheetId field.
         * @return the protocol field value
         */
        public DOM.StyleSheetId styleSheetId() {
            return require(STYLE_SHEET_ID);
        }
        /**
         * Returns the range field.
         * @return the protocol field value
         */
        public CSS.SourceRange range() {
            return require(RANGE);
        }
        /**
         * Returns the propertyName field.
         * @return the protocol field value
         */
        public String propertyName() {
            return requireString("propertyName");
        }
        /**
         * Sets the styleSheetId field.
         * @param styleSheetId field value
         * @return this model
         */
        public SetPropertyRulePropertyNameRequest styleSheetId(DOM.StyleSheetId styleSheetId) {
            set("styleSheetId", styleSheetId);
            return this;
        }
        /**
         * Sets the range field.
         * @param range field value
         * @return this model
         */
        public SetPropertyRulePropertyNameRequest range(CSS.SourceRange range) {
            set("range", range);
            return this;
        }
        /**
         * Sets the propertyName field.
         * @param propertyName field value
         * @return this model
         */
        public SetPropertyRulePropertyNameRequest propertyName(String propertyName) {
            set("propertyName", propertyName);
            return this;
        }
    }
    /**
     * Modifies the keyframe rule key text.
     */
    public static final class SetKeyframeKeyRequest extends CdpObject {
        public SetKeyframeKeyRequest() {}
        public static SetKeyframeKeyRequest fromMap(Map<String, Object> values) {
            final SetKeyframeKeyRequest instance_ = new SetKeyframeKeyRequest();
            if (values != null) instance_.values.putAll(values);
            return instance_;
        }
        /**
         * Returns the styleSheetId field.
         * @return the protocol field value
         */
        public DOM.StyleSheetId styleSheetId() {
            return require(STYLE_SHEET_ID);
        }
        /**
         * Returns the range field.
         * @return the protocol field value
         */
        public CSS.SourceRange range() {
            return require(RANGE);
        }
        /**
         * Returns the keyText field.
         * @return the protocol field value
         */
        public String keyText() {
            return requireString("keyText");
        }
        /**
         * Sets the styleSheetId field.
         * @param styleSheetId field value
         * @return this model
         */
        public SetKeyframeKeyRequest styleSheetId(DOM.StyleSheetId styleSheetId) {
            set("styleSheetId", styleSheetId);
            return this;
        }
        /**
         * Sets the range field.
         * @param range field value
         * @return this model
         */
        public SetKeyframeKeyRequest range(CSS.SourceRange range) {
            set("range", range);
            return this;
        }
        /**
         * Sets the keyText field.
         * @param keyText field value
         * @return this model
         */
        public SetKeyframeKeyRequest keyText(String keyText) {
            set("keyText", keyText);
            return this;
        }
    }
    /**
     * Modifies the rule selector.
     */
    public static final class SetMediaTextRequest extends CdpObject {
        public SetMediaTextRequest() {}
        public static SetMediaTextRequest fromMap(Map<String, Object> values) {
            final SetMediaTextRequest instance_ = new SetMediaTextRequest();
            if (values != null) instance_.values.putAll(values);
            return instance_;
        }
        /**
         * Returns the styleSheetId field.
         * @return the protocol field value
         */
        public DOM.StyleSheetId styleSheetId() {
            return require(STYLE_SHEET_ID);
        }
        /**
         * Returns the range field.
         * @return the protocol field value
         */
        public CSS.SourceRange range() {
            return require(RANGE);
        }
        /**
         * Returns the text field.
         * @return the protocol field value
         */
        public String text() {
            return requireString("text");
        }
        /**
         * Sets the styleSheetId field.
         * @param styleSheetId field value
         * @return this model
         */
        public SetMediaTextRequest styleSheetId(DOM.StyleSheetId styleSheetId) {
            set("styleSheetId", styleSheetId);
            return this;
        }
        /**
         * Sets the range field.
         * @param range field value
         * @return this model
         */
        public SetMediaTextRequest range(CSS.SourceRange range) {
            set("range", range);
            return this;
        }
        /**
         * Sets the text field.
         * @param text field value
         * @return this model
         */
        public SetMediaTextRequest text(String text) {
            set("text", text);
            return this;
        }
    }
    /**
     * Modifies the expression of a container query. Deprecated. Use setContainerQueryConditionText instead.
     * <p><b>Experimental:</b> this part of CDP may change without notice.
     * @deprecated Deprecated by the Chromium DevTools Protocol.
     */
    @Deprecated
    public static final class SetContainerQueryTextRequest extends CdpObject {
        public SetContainerQueryTextRequest() {}
        public static SetContainerQueryTextRequest fromMap(Map<String, Object> values) {
            final SetContainerQueryTextRequest instance_ = new SetContainerQueryTextRequest();
            if (values != null) instance_.values.putAll(values);
            return instance_;
        }
        /**
         * Returns the styleSheetId field.
         * @return the protocol field value
         */
        public DOM.StyleSheetId styleSheetId() {
            return require(STYLE_SHEET_ID);
        }
        /**
         * Returns the range field.
         * @return the protocol field value
         */
        public CSS.SourceRange range() {
            return require(RANGE);
        }
        /**
         * Returns the text field.
         * @return the protocol field value
         */
        public String text() {
            return requireString("text");
        }
        /**
         * Sets the styleSheetId field.
         * @param styleSheetId field value
         * @return this model
         */
        public SetContainerQueryTextRequest styleSheetId(DOM.StyleSheetId styleSheetId) {
            set("styleSheetId", styleSheetId);
            return this;
        }
        /**
         * Sets the range field.
         * @param range field value
         * @return this model
         */
        public SetContainerQueryTextRequest range(CSS.SourceRange range) {
            set("range", range);
            return this;
        }
        /**
         * Sets the text field.
         * @param text field value
         * @return this model
         */
        public SetContainerQueryTextRequest text(String text) {
            set("text", text);
            return this;
        }
    }
    /**
     * Request parameters for CSS.setContainerQueryConditionText.
     * <p><b>Experimental:</b> this part of CDP may change without notice.
     */
    public static final class SetContainerQueryConditionTextRequest extends CdpObject {
        public SetContainerQueryConditionTextRequest() {}
        public static SetContainerQueryConditionTextRequest fromMap(Map<String, Object> values) {
            final SetContainerQueryConditionTextRequest instance_ = new SetContainerQueryConditionTextRequest();
            if (values != null) instance_.values.putAll(values);
            return instance_;
        }
        /**
         * Returns the styleSheetId field.
         * @return the protocol field value
         */
        public DOM.StyleSheetId styleSheetId() {
            return require(STYLE_SHEET_ID);
        }
        /**
         * Returns the range field.
         * @return the protocol field value
         */
        public CSS.SourceRange range() {
            return require(RANGE);
        }
        /**
         * Returns the text field.
         * @return the protocol field value
         */
        public String text() {
            return requireString("text");
        }
        /**
         * Sets the styleSheetId field.
         * @param styleSheetId field value
         * @return this model
         */
        public SetContainerQueryConditionTextRequest styleSheetId(DOM.StyleSheetId styleSheetId) {
            set("styleSheetId", styleSheetId);
            return this;
        }
        /**
         * Sets the range field.
         * @param range field value
         * @return this model
         */
        public SetContainerQueryConditionTextRequest range(CSS.SourceRange range) {
            set("range", range);
            return this;
        }
        /**
         * Sets the text field.
         * @param text field value
         * @return this model
         */
        public SetContainerQueryConditionTextRequest text(String text) {
            set("text", text);
            return this;
        }
    }
    /**
     * Modifies the expression of a supports at-rule.
     * <p><b>Experimental:</b> this part of CDP may change without notice.
     */
    public static final class SetSupportsTextRequest extends CdpObject {
        public SetSupportsTextRequest() {}
        public static SetSupportsTextRequest fromMap(Map<String, Object> values) {
            final SetSupportsTextRequest instance_ = new SetSupportsTextRequest();
            if (values != null) instance_.values.putAll(values);
            return instance_;
        }
        /**
         * Returns the styleSheetId field.
         * @return the protocol field value
         */
        public DOM.StyleSheetId styleSheetId() {
            return require(STYLE_SHEET_ID);
        }
        /**
         * Returns the range field.
         * @return the protocol field value
         */
        public CSS.SourceRange range() {
            return require(RANGE);
        }
        /**
         * Returns the text field.
         * @return the protocol field value
         */
        public String text() {
            return requireString("text");
        }
        /**
         * Sets the styleSheetId field.
         * @param styleSheetId field value
         * @return this model
         */
        public SetSupportsTextRequest styleSheetId(DOM.StyleSheetId styleSheetId) {
            set("styleSheetId", styleSheetId);
            return this;
        }
        /**
         * Sets the range field.
         * @param range field value
         * @return this model
         */
        public SetSupportsTextRequest range(CSS.SourceRange range) {
            set("range", range);
            return this;
        }
        /**
         * Sets the text field.
         * @param text field value
         * @return this model
         */
        public SetSupportsTextRequest text(String text) {
            set("text", text);
            return this;
        }
    }
    /**
     * Modifies the expression of a navigation at-rule.
     * <p><b>Experimental:</b> this part of CDP may change without notice.
     */
    public static final class SetNavigationTextRequest extends CdpObject {
        public SetNavigationTextRequest() {}
        public static SetNavigationTextRequest fromMap(Map<String, Object> values) {
            final SetNavigationTextRequest instance_ = new SetNavigationTextRequest();
            if (values != null) instance_.values.putAll(values);
            return instance_;
        }
        /**
         * Returns the styleSheetId field.
         * @return the protocol field value
         */
        public DOM.StyleSheetId styleSheetId() {
            return require(STYLE_SHEET_ID);
        }
        /**
         * Returns the range field.
         * @return the protocol field value
         */
        public CSS.SourceRange range() {
            return require(RANGE);
        }
        /**
         * Returns the text field.
         * @return the protocol field value
         */
        public String text() {
            return requireString("text");
        }
        /**
         * Sets the styleSheetId field.
         * @param styleSheetId field value
         * @return this model
         */
        public SetNavigationTextRequest styleSheetId(DOM.StyleSheetId styleSheetId) {
            set("styleSheetId", styleSheetId);
            return this;
        }
        /**
         * Sets the range field.
         * @param range field value
         * @return this model
         */
        public SetNavigationTextRequest range(CSS.SourceRange range) {
            set("range", range);
            return this;
        }
        /**
         * Sets the text field.
         * @param text field value
         * @return this model
         */
        public SetNavigationTextRequest text(String text) {
            set("text", text);
            return this;
        }
    }
    /**
     * Modifies the expression of a scope at-rule.
     * <p><b>Experimental:</b> this part of CDP may change without notice.
     */
    public static final class SetScopeTextRequest extends CdpObject {
        public SetScopeTextRequest() {}
        public static SetScopeTextRequest fromMap(Map<String, Object> values) {
            final SetScopeTextRequest instance_ = new SetScopeTextRequest();
            if (values != null) instance_.values.putAll(values);
            return instance_;
        }
        /**
         * Returns the styleSheetId field.
         * @return the protocol field value
         */
        public DOM.StyleSheetId styleSheetId() {
            return require(STYLE_SHEET_ID);
        }
        /**
         * Returns the range field.
         * @return the protocol field value
         */
        public CSS.SourceRange range() {
            return require(RANGE);
        }
        /**
         * Returns the text field.
         * @return the protocol field value
         */
        public String text() {
            return requireString("text");
        }
        /**
         * Sets the styleSheetId field.
         * @param styleSheetId field value
         * @return this model
         */
        public SetScopeTextRequest styleSheetId(DOM.StyleSheetId styleSheetId) {
            set("styleSheetId", styleSheetId);
            return this;
        }
        /**
         * Sets the range field.
         * @param range field value
         * @return this model
         */
        public SetScopeTextRequest range(CSS.SourceRange range) {
            set("range", range);
            return this;
        }
        /**
         * Sets the text field.
         * @param text field value
         * @return this model
         */
        public SetScopeTextRequest text(String text) {
            set("text", text);
            return this;
        }
    }
    /**
     * Modifies the rule selector.
     */
    public static final class SetRuleSelectorRequest extends CdpObject {
        public SetRuleSelectorRequest() {}
        public static SetRuleSelectorRequest fromMap(Map<String, Object> values) {
            final SetRuleSelectorRequest instance_ = new SetRuleSelectorRequest();
            if (values != null) instance_.values.putAll(values);
            return instance_;
        }
        /**
         * Returns the styleSheetId field.
         * @return the protocol field value
         */
        public DOM.StyleSheetId styleSheetId() {
            return require(STYLE_SHEET_ID);
        }
        /**
         * Returns the range field.
         * @return the protocol field value
         */
        public CSS.SourceRange range() {
            return require(RANGE);
        }
        /**
         * Returns the selector field.
         * @return the protocol field value
         */
        public String selector() {
            return requireString("selector");
        }
        /**
         * Sets the styleSheetId field.
         * @param styleSheetId field value
         * @return this model
         */
        public SetRuleSelectorRequest styleSheetId(DOM.StyleSheetId styleSheetId) {
            set("styleSheetId", styleSheetId);
            return this;
        }
        /**
         * Sets the range field.
         * @param range field value
         * @return this model
         */
        public SetRuleSelectorRequest range(CSS.SourceRange range) {
            set("range", range);
            return this;
        }
        /**
         * Sets the selector field.
         * @param selector field value
         * @return this model
         */
        public SetRuleSelectorRequest selector(String selector) {
            set("selector", selector);
            return this;
        }
    }
    /**
     * Sets the new stylesheet text.
     */
    public static final class SetStyleSheetTextRequest extends CdpObject {
        public SetStyleSheetTextRequest() {}
        public static SetStyleSheetTextRequest fromMap(Map<String, Object> values) {
            final SetStyleSheetTextRequest instance_ = new SetStyleSheetTextRequest();
            if (values != null) instance_.values.putAll(values);
            return instance_;
        }
        /**
         * Returns the styleSheetId field.
         * @return the protocol field value
         */
        public DOM.StyleSheetId styleSheetId() {
            return require(STYLE_SHEET_ID);
        }
        /**
         * Returns the text field.
         * @return the protocol field value
         */
        public String text() {
            return requireString("text");
        }
        /**
         * Sets the styleSheetId field.
         * @param styleSheetId field value
         * @return this model
         */
        public SetStyleSheetTextRequest styleSheetId(DOM.StyleSheetId styleSheetId) {
            set("styleSheetId", styleSheetId);
            return this;
        }
        /**
         * Sets the text field.
         * @param text field value
         * @return this model
         */
        public SetStyleSheetTextRequest text(String text) {
            set("text", text);
            return this;
        }
    }
    /**
     * Applies specified style edits one after another in the given order.
     */
    public static final class SetStyleTextsRequest extends CdpObject {
        public SetStyleTextsRequest() {}
        public static SetStyleTextsRequest fromMap(Map<String, Object> values) {
            final SetStyleTextsRequest instance_ = new SetStyleTextsRequest();
            if (values != null) instance_.values.putAll(values);
            return instance_;
        }
        /**
         * Returns the edits field.
         * @return the protocol field value
         */
        public java.util.List<CSS.StyleDeclarationEdit> edits() {
            return require(EDITS);
        }
        /**
         * NodeId for the DOM node in whose context custom property declarations for registered properties should be validated. If omitted, declarations in the new rule text can only be validated statically, which may produce incorrect results if the declaration contains a var() for example.
         * <p><b>Experimental:</b> this part of CDP may change without notice.
         * @return the protocol field value, empty when absent
         */
        public Optional<DOM.NodeId> nodeForPropertySyntaxValidation() {
            return optional(NODE_FOR_PROPERTY_SYNTAX_VALIDATION);
        }
        /**
         * Sets the edits field.
         * @param edits field value
         * @return this model
         */
        public SetStyleTextsRequest edits(java.util.List<CSS.StyleDeclarationEdit> edits) {
            set("edits", edits);
            return this;
        }
        /**
         * NodeId for the DOM node in whose context custom property declarations for registered properties should be validated. If omitted, declarations in the new rule text can only be validated statically, which may produce incorrect results if the declaration contains a var() for example.
         * <p><b>Experimental:</b> this part of CDP may change without notice.
         * @param nodeForPropertySyntaxValidation field value
         * @return this model
         */
        public SetStyleTextsRequest nodeForPropertySyntaxValidation(DOM.NodeId nodeForPropertySyntaxValidation) {
            set("nodeForPropertySyntaxValidation", nodeForPropertySyntaxValidation);
            return this;
        }
    }
    /**
     * Enables/disables rendering of local CSS fonts (enabled by default).
     * <p><b>Experimental:</b> this part of CDP may change without notice.
     */
    public static final class SetLocalFontsEnabledRequest extends CdpObject {
        public SetLocalFontsEnabledRequest() {}
        public static SetLocalFontsEnabledRequest fromMap(Map<String, Object> values) {
            final SetLocalFontsEnabledRequest instance_ = new SetLocalFontsEnabledRequest();
            if (values != null) instance_.values.putAll(values);
            return instance_;
        }
        /**
         * Whether rendering of local fonts is enabled.
         * @return the protocol field value
         */
        public boolean enabled() {
            return requireBoolean("enabled");
        }
        /**
         * Whether rendering of local fonts is enabled.
         * @param enabled field value
         * @return this model
         */
        public SetLocalFontsEnabledRequest enabled(boolean enabled) {
            set("enabled", enabled);
            return this;
        }
    }
    /**
     * Result of CSS.getBackgroundColors.
     */
    public static final class GetBackgroundColorsResult extends CdpObject {
        public GetBackgroundColorsResult() {}
        private GetBackgroundColorsResult(Map<String, Object> values) { super(values); }
        public static GetBackgroundColorsResult fromMap(Map<String, Object> values) {
            return new GetBackgroundColorsResult(values);
        }
        /**
         * The range of background colors behind this element, if it contains any visible text. If no visible text is present, this will be undefined. In the case of a flat background color, this will consist of simply that color. In the case of a gradient, this will consist of each of the color stops. For anything more complicated, this will be an empty array. Images will be ignored (as if the image had failed to load).
         * @return the protocol field value, empty when absent
         */
        public Optional<java.util.List<String>> backgroundColors() {
            return optional(BACKGROUND_COLORS);
        }
        /**
         * The computed font size for this node, as a CSS computed value string (e.g. &#x27;12px&#x27;).
         * @return the protocol field value, empty when absent
         */
        public Optional<String> computedFontSize() {
            return optionalString("computedFontSize");
        }
        /**
         * The computed font weight for this node, as a CSS computed value string (e.g. &#x27;normal&#x27; or &#x27;100&#x27;).
         * @return the protocol field value, empty when absent
         */
        public Optional<String> computedFontWeight() {
            return optionalString("computedFontWeight");
        }
        /**
         * The range of background colors behind this element, if it contains any visible text. If no visible text is present, this will be undefined. In the case of a flat background color, this will consist of simply that color. In the case of a gradient, this will consist of each of the color stops. For anything more complicated, this will be an empty array. Images will be ignored (as if the image had failed to load).
         * @param backgroundColors field value
         * @return this model
         */
        public GetBackgroundColorsResult backgroundColors(java.util.List<String> backgroundColors) {
            set("backgroundColors", backgroundColors);
            return this;
        }
        /**
         * The computed font size for this node, as a CSS computed value string (e.g. &#x27;12px&#x27;).
         * @param computedFontSize field value
         * @return this model
         */
        public GetBackgroundColorsResult computedFontSize(String computedFontSize) {
            set("computedFontSize", computedFontSize);
            return this;
        }
        /**
         * The computed font weight for this node, as a CSS computed value string (e.g. &#x27;normal&#x27; or &#x27;100&#x27;).
         * @param computedFontWeight field value
         * @return this model
         */
        public GetBackgroundColorsResult computedFontWeight(String computedFontWeight) {
            set("computedFontWeight", computedFontWeight);
            return this;
        }
    }
    /**
     * Returns the computed style for a DOM node identified by {@code nodeId}.
     */
    public static final class GetComputedStyleForNodeResult extends CdpObject {
        public GetComputedStyleForNodeResult() {}
        private GetComputedStyleForNodeResult(Map<String, Object> values) { super(values); }
        public static GetComputedStyleForNodeResult fromMap(Map<String, Object> values) {
            return new GetComputedStyleForNodeResult(values);
        }
        /**
         * Computed style for the specified DOM node.
         * @return the protocol field value
         */
        public java.util.List<CSS.CSSComputedStyleProperty> computedStyle() {
            return require(COMPUTED_STYLE);
        }
        /**
         * A list of non-standard &quot;extra fields&quot; which blink stores alongside each computed style.
         * <p><b>Experimental:</b> this part of CDP may change without notice.
         * @return the protocol field value
         */
        public CSS.ComputedStyleExtraFields extraFields() {
            return require(EXTRA_FIELDS);
        }
        /**
         * Computed style for the specified DOM node.
         * @param computedStyle field value
         * @return this model
         */
        public GetComputedStyleForNodeResult computedStyle(java.util.List<CSS.CSSComputedStyleProperty> computedStyle) {
            set("computedStyle", computedStyle);
            return this;
        }
        /**
         * A list of non-standard &quot;extra fields&quot; which blink stores alongside each computed style.
         * <p><b>Experimental:</b> this part of CDP may change without notice.
         * @param extraFields field value
         * @return this model
         */
        public GetComputedStyleForNodeResult extraFields(CSS.ComputedStyleExtraFields extraFields) {
            set("extraFields", extraFields);
            return this;
        }
    }
    /**
     * Returns the styles defined inline (explicitly in the &quot;style&quot; attribute and implicitly, using DOM attributes) for a DOM node identified by {@code nodeId}.
     */
    public static final class GetInlineStylesForNodeResult extends CdpObject {
        public GetInlineStylesForNodeResult() {}
        private GetInlineStylesForNodeResult(Map<String, Object> values) { super(values); }
        public static GetInlineStylesForNodeResult fromMap(Map<String, Object> values) {
            return new GetInlineStylesForNodeResult(values);
        }
        /**
         * Inline style for the specified DOM node.
         * @return the protocol field value, empty when absent
         */
        public Optional<CSS.CSSStyle> inlineStyle() {
            return optional(INLINE_STYLE);
        }
        /**
         * Attribute-defined element style (e.g. resulting from &quot;width=20 height=100%&quot;).
         * @return the protocol field value, empty when absent
         */
        public Optional<CSS.CSSStyle> attributesStyle() {
            return optional(ATTRIBUTES_STYLE);
        }
        /**
         * Inline style for the specified DOM node.
         * @param inlineStyle field value
         * @return this model
         */
        public GetInlineStylesForNodeResult inlineStyle(CSS.CSSStyle inlineStyle) {
            set("inlineStyle", inlineStyle);
            return this;
        }
        /**
         * Attribute-defined element style (e.g. resulting from &quot;width=20 height=100%&quot;).
         * @param attributesStyle field value
         * @return this model
         */
        public GetInlineStylesForNodeResult attributesStyle(CSS.CSSStyle attributesStyle) {
            set("attributesStyle", attributesStyle);
            return this;
        }
    }
    /**
     * Returns the styles coming from animations &amp; transitions including the animation &amp; transition styles coming from inheritance chain.
     * <p><b>Experimental:</b> this part of CDP may change without notice.
     */
    public static final class GetAnimatedStylesForNodeResult extends CdpObject {
        public GetAnimatedStylesForNodeResult() {}
        private GetAnimatedStylesForNodeResult(Map<String, Object> values) { super(values); }
        public static GetAnimatedStylesForNodeResult fromMap(Map<String, Object> values) {
            return new GetAnimatedStylesForNodeResult(values);
        }
        /**
         * Styles coming from animations.
         * @return the protocol field value, empty when absent
         */
        public Optional<java.util.List<CSS.CSSAnimationStyle>> animationStyles() {
            return optional(ANIMATION_STYLES);
        }
        /**
         * Style coming from transitions.
         * @return the protocol field value, empty when absent
         */
        public Optional<CSS.CSSStyle> transitionsStyle() {
            return optional(TRANSITIONS_STYLE);
        }
        /**
         * Inherited style entries for animationsStyle and transitionsStyle from the inheritance chain of the element.
         * @return the protocol field value, empty when absent
         */
        public Optional<java.util.List<CSS.InheritedAnimatedStyleEntry>> inherited() {
            return optional(INHERITED);
        }
        /**
         * Styles coming from animations.
         * @param animationStyles field value
         * @return this model
         */
        public GetAnimatedStylesForNodeResult animationStyles(java.util.List<CSS.CSSAnimationStyle> animationStyles) {
            set("animationStyles", animationStyles);
            return this;
        }
        /**
         * Style coming from transitions.
         * @param transitionsStyle field value
         * @return this model
         */
        public GetAnimatedStylesForNodeResult transitionsStyle(CSS.CSSStyle transitionsStyle) {
            set("transitionsStyle", transitionsStyle);
            return this;
        }
        /**
         * Inherited style entries for animationsStyle and transitionsStyle from the inheritance chain of the element.
         * @param inherited field value
         * @return this model
         */
        public GetAnimatedStylesForNodeResult inherited(java.util.List<CSS.InheritedAnimatedStyleEntry> inherited) {
            set("inherited", inherited);
            return this;
        }
    }
    /**
     * Returns requested styles for a DOM node identified by {@code nodeId}.
     */
    public static final class GetMatchedStylesForNodeResult extends CdpObject {
        public GetMatchedStylesForNodeResult() {}
        private GetMatchedStylesForNodeResult(Map<String, Object> values) { super(values); }
        public static GetMatchedStylesForNodeResult fromMap(Map<String, Object> values) {
            return new GetMatchedStylesForNodeResult(values);
        }
        /**
         * Inline style for the specified DOM node.
         * @return the protocol field value, empty when absent
         */
        public Optional<CSS.CSSStyle> inlineStyle() {
            return optional(INLINE_STYLE);
        }
        /**
         * Attribute-defined element style (e.g. resulting from &quot;width=20 height=100%&quot;).
         * @return the protocol field value, empty when absent
         */
        public Optional<CSS.CSSStyle> attributesStyle() {
            return optional(ATTRIBUTES_STYLE);
        }
        /**
         * CSS rules matching this node, from all applicable stylesheets.
         * @return the protocol field value, empty when absent
         */
        public Optional<java.util.List<CSS.RuleMatch>> matchedCSSRules() {
            return optional(MATCHED_CSSRULES);
        }
        /**
         * Pseudo style matches for this node.
         * @return the protocol field value, empty when absent
         */
        public Optional<java.util.List<CSS.PseudoElementMatches>> pseudoElements() {
            return optional(PSEUDO_ELEMENTS);
        }
        /**
         * A chain of inherited styles (from the immediate node parent up to the DOM tree root).
         * @return the protocol field value, empty when absent
         */
        public Optional<java.util.List<CSS.InheritedStyleEntry>> inherited() {
            return optional(INHERITED_2);
        }
        /**
         * A chain of inherited pseudo element styles (from the immediate node parent up to the DOM tree root).
         * @return the protocol field value, empty when absent
         */
        public Optional<java.util.List<CSS.InheritedPseudoElementMatches>> inheritedPseudoElements() {
            return optional(INHERITED_PSEUDO_ELEMENTS);
        }
        /**
         * A list of CSS keyframed animations matching this node.
         * @return the protocol field value, empty when absent
         */
        public Optional<java.util.List<CSS.CSSKeyframesRule>> cssKeyframesRules() {
            return optional(CSS_KEYFRAMES_RULES);
        }
        /**
         * A list of CSS &#64;position-try rules matching this node, based on the position-try-fallbacks property.
         * @return the protocol field value, empty when absent
         */
        public Optional<java.util.List<CSS.CSSPositionTryRule>> cssPositionTryRules() {
            return optional(CSS_POSITION_TRY_RULES);
        }
        /**
         * Index of the active fallback in the applied position-try-fallback property, will not be set if there is no active position-try fallback.
         * @return the protocol field value, empty when absent
         */
        public OptionalLong activePositionFallbackIndex() {
            return optionalLong("activePositionFallbackIndex");
        }
        /**
         * A list of CSS at-property rules matching this node.
         * @return the protocol field value, empty when absent
         */
        public Optional<java.util.List<CSS.CSSPropertyRule>> cssPropertyRules() {
            return optional(CSS_PROPERTY_RULES);
        }
        /**
         * A list of CSS property registrations matching this node.
         * @return the protocol field value, empty when absent
         */
        public Optional<java.util.List<CSS.CSSPropertyRegistration>> cssPropertyRegistrations() {
            return optional(CSS_PROPERTY_REGISTRATIONS);
        }
        /**
         * A list of simple &#64;rules matching this node or its pseudo-elements.
         * @return the protocol field value, empty when absent
         */
        public Optional<java.util.List<CSS.CSSAtRule>> cssAtRules() {
            return optional(CSS_AT_RULES);
        }
        /**
         * Id of the first parent element that does not have display: contents.
         * <p><b>Experimental:</b> this part of CDP may change without notice.
         * @return the protocol field value, empty when absent
         */
        public Optional<DOM.NodeId> parentLayoutNodeId() {
            return optional(PARENT_LAYOUT_NODE_ID);
        }
        /**
         * A list of CSS at-function rules referenced by styles of this node.
         * <p><b>Experimental:</b> this part of CDP may change without notice.
         * @return the protocol field value, empty when absent
         */
        public Optional<java.util.List<CSS.CSSFunctionRule>> cssFunctionRules() {
            return optional(CSS_FUNCTION_RULES);
        }
        /**
         * Inline style for the specified DOM node.
         * @param inlineStyle field value
         * @return this model
         */
        public GetMatchedStylesForNodeResult inlineStyle(CSS.CSSStyle inlineStyle) {
            set("inlineStyle", inlineStyle);
            return this;
        }
        /**
         * Attribute-defined element style (e.g. resulting from &quot;width=20 height=100%&quot;).
         * @param attributesStyle field value
         * @return this model
         */
        public GetMatchedStylesForNodeResult attributesStyle(CSS.CSSStyle attributesStyle) {
            set("attributesStyle", attributesStyle);
            return this;
        }
        /**
         * CSS rules matching this node, from all applicable stylesheets.
         * @param matchedCSSRules field value
         * @return this model
         */
        public GetMatchedStylesForNodeResult matchedCSSRules(java.util.List<CSS.RuleMatch> matchedCSSRules) {
            set("matchedCSSRules", matchedCSSRules);
            return this;
        }
        /**
         * Pseudo style matches for this node.
         * @param pseudoElements field value
         * @return this model
         */
        public GetMatchedStylesForNodeResult pseudoElements(java.util.List<CSS.PseudoElementMatches> pseudoElements) {
            set("pseudoElements", pseudoElements);
            return this;
        }
        /**
         * A chain of inherited styles (from the immediate node parent up to the DOM tree root).
         * @param inherited field value
         * @return this model
         */
        public GetMatchedStylesForNodeResult inherited(java.util.List<CSS.InheritedStyleEntry> inherited) {
            set("inherited", inherited);
            return this;
        }
        /**
         * A chain of inherited pseudo element styles (from the immediate node parent up to the DOM tree root).
         * @param inheritedPseudoElements field value
         * @return this model
         */
        public GetMatchedStylesForNodeResult inheritedPseudoElements(java.util.List<CSS.InheritedPseudoElementMatches> inheritedPseudoElements) {
            set("inheritedPseudoElements", inheritedPseudoElements);
            return this;
        }
        /**
         * A list of CSS keyframed animations matching this node.
         * @param cssKeyframesRules field value
         * @return this model
         */
        public GetMatchedStylesForNodeResult cssKeyframesRules(java.util.List<CSS.CSSKeyframesRule> cssKeyframesRules) {
            set("cssKeyframesRules", cssKeyframesRules);
            return this;
        }
        /**
         * A list of CSS &#64;position-try rules matching this node, based on the position-try-fallbacks property.
         * @param cssPositionTryRules field value
         * @return this model
         */
        public GetMatchedStylesForNodeResult cssPositionTryRules(java.util.List<CSS.CSSPositionTryRule> cssPositionTryRules) {
            set("cssPositionTryRules", cssPositionTryRules);
            return this;
        }
        /**
         * Index of the active fallback in the applied position-try-fallback property, will not be set if there is no active position-try fallback.
         * @param activePositionFallbackIndex field value
         * @return this model
         */
        public GetMatchedStylesForNodeResult activePositionFallbackIndex(long activePositionFallbackIndex) {
            set("activePositionFallbackIndex", activePositionFallbackIndex);
            return this;
        }
        /**
         * A list of CSS at-property rules matching this node.
         * @param cssPropertyRules field value
         * @return this model
         */
        public GetMatchedStylesForNodeResult cssPropertyRules(java.util.List<CSS.CSSPropertyRule> cssPropertyRules) {
            set("cssPropertyRules", cssPropertyRules);
            return this;
        }
        /**
         * A list of CSS property registrations matching this node.
         * @param cssPropertyRegistrations field value
         * @return this model
         */
        public GetMatchedStylesForNodeResult cssPropertyRegistrations(java.util.List<CSS.CSSPropertyRegistration> cssPropertyRegistrations) {
            set("cssPropertyRegistrations", cssPropertyRegistrations);
            return this;
        }
        /**
         * A list of simple &#64;rules matching this node or its pseudo-elements.
         * @param cssAtRules field value
         * @return this model
         */
        public GetMatchedStylesForNodeResult cssAtRules(java.util.List<CSS.CSSAtRule> cssAtRules) {
            set("cssAtRules", cssAtRules);
            return this;
        }
        /**
         * Id of the first parent element that does not have display: contents.
         * <p><b>Experimental:</b> this part of CDP may change without notice.
         * @param parentLayoutNodeId field value
         * @return this model
         */
        public GetMatchedStylesForNodeResult parentLayoutNodeId(DOM.NodeId parentLayoutNodeId) {
            set("parentLayoutNodeId", parentLayoutNodeId);
            return this;
        }
        /**
         * A list of CSS at-function rules referenced by styles of this node.
         * <p><b>Experimental:</b> this part of CDP may change without notice.
         * @param cssFunctionRules field value
         * @return this model
         */
        public GetMatchedStylesForNodeResult cssFunctionRules(java.util.List<CSS.CSSFunctionRule> cssFunctionRules) {
            set("cssFunctionRules", cssFunctionRules);
            return this;
        }
    }
    /**
     * Obtain list of rules that became used since last call to this method (or since start of coverage instrumentation).
     */
    public static final class TakeCoverageDeltaResult extends CdpObject {
        public TakeCoverageDeltaResult() {}
        private TakeCoverageDeltaResult(Map<String, Object> values) { super(values); }
        public static TakeCoverageDeltaResult fromMap(Map<String, Object> values) {
            return new TakeCoverageDeltaResult(values);
        }
        /**
         * Returns the coverage field.
         * @return the protocol field value
         */
        public java.util.List<CSS.RuleUsage> coverage() {
            return require(COVERAGE);
        }
        /**
         * Monotonically increasing time, in seconds.
         * @return the protocol field value
         */
        public double timestamp() {
            return requireDouble("timestamp");
        }
        /**
         * Sets the coverage field.
         * @param coverage field value
         * @return this model
         */
        public TakeCoverageDeltaResult coverage(java.util.List<CSS.RuleUsage> coverage) {
            set("coverage", coverage);
            return this;
        }
        /**
         * Monotonically increasing time, in seconds.
         * @param timestamp field value
         * @return this model
         */
        public TakeCoverageDeltaResult timestamp(double timestamp) {
            set("timestamp", timestamp);
            return this;
        }
    }
    /**
     * Fires whenever a web font is updated. A non-empty font parameter indicates a successfully loaded web font.
     */
    public static final class FontsUpdatedEvent extends CdpObject {
        public FontsUpdatedEvent() {}
        private FontsUpdatedEvent(Map<String, Object> values) { super(values); }
        public static FontsUpdatedEvent fromMap(Map<String, Object> values) {
            return new FontsUpdatedEvent(values);
        }
        /**
         * The web font that has loaded.
         * @return the protocol field value, empty when absent
         */
        public Optional<CSS.FontFace> font() {
            return optional(FONT);
        }
        /**
         * The web font that has loaded.
         * @param font field value
         * @return this model
         */
        public FontsUpdatedEvent font(CSS.FontFace font) {
            set("font", font);
            return this;
        }
    }
    /**
     * Fires whenever a MediaQuery result changes (for example, after a browser window has been resized.) The current implementation considers only viewport-dependent media features.
     */
    public static final class MediaQueryResultChangedEvent extends CdpObject {
        public MediaQueryResultChangedEvent() {}
        private MediaQueryResultChangedEvent(Map<String, Object> values) { super(values); }
        public static MediaQueryResultChangedEvent fromMap(Map<String, Object> values) {
            return new MediaQueryResultChangedEvent(values);
        }
    }
    /**
     * Fired whenever an active document stylesheet is added.
     */
    public static final class StyleSheetAddedEvent extends CdpObject {
        public StyleSheetAddedEvent() {}
        private StyleSheetAddedEvent(Map<String, Object> values) { super(values); }
        public static StyleSheetAddedEvent fromMap(Map<String, Object> values) {
            return new StyleSheetAddedEvent(values);
        }
        /**
         * Added stylesheet metainfo.
         * @return the protocol field value
         */
        public CSS.CSSStyleSheetHeader header() {
            return require(HEADER);
        }
        /**
         * Added stylesheet metainfo.
         * @param header field value
         * @return this model
         */
        public StyleSheetAddedEvent header(CSS.CSSStyleSheetHeader header) {
            set("header", header);
            return this;
        }
    }
    /**
     * Fired whenever a stylesheet is changed as a result of the client operation.
     */
    public static final class StyleSheetChangedEvent extends CdpObject {
        public StyleSheetChangedEvent() {}
        private StyleSheetChangedEvent(Map<String, Object> values) { super(values); }
        public static StyleSheetChangedEvent fromMap(Map<String, Object> values) {
            return new StyleSheetChangedEvent(values);
        }
        /**
         * Returns the styleSheetId field.
         * @return the protocol field value
         */
        public DOM.StyleSheetId styleSheetId() {
            return require(STYLE_SHEET_ID);
        }
        /**
         * Sets the styleSheetId field.
         * @param styleSheetId field value
         * @return this model
         */
        public StyleSheetChangedEvent styleSheetId(DOM.StyleSheetId styleSheetId) {
            set("styleSheetId", styleSheetId);
            return this;
        }
    }
    /**
     * Fired whenever an active document stylesheet is removed.
     */
    public static final class StyleSheetRemovedEvent extends CdpObject {
        public StyleSheetRemovedEvent() {}
        private StyleSheetRemovedEvent(Map<String, Object> values) { super(values); }
        public static StyleSheetRemovedEvent fromMap(Map<String, Object> values) {
            return new StyleSheetRemovedEvent(values);
        }
        /**
         * Identifier of the removed stylesheet.
         * @return the protocol field value
         */
        public DOM.StyleSheetId styleSheetId() {
            return require(STYLE_SHEET_ID);
        }
        /**
         * Identifier of the removed stylesheet.
         * @param styleSheetId field value
         * @return this model
         */
        public StyleSheetRemovedEvent styleSheetId(DOM.StyleSheetId styleSheetId) {
            set("styleSheetId", styleSheetId);
            return this;
        }
    }
    /**
     * Payload of the CSS.computedStyleUpdated event.
     * <p><b>Experimental:</b> this part of CDP may change without notice.
     */
    public static final class ComputedStyleUpdatedEvent extends CdpObject {
        public ComputedStyleUpdatedEvent() {}
        private ComputedStyleUpdatedEvent(Map<String, Object> values) { super(values); }
        public static ComputedStyleUpdatedEvent fromMap(Map<String, Object> values) {
            return new ComputedStyleUpdatedEvent(values);
        }
        /**
         * The node id that has updated computed styles.
         * @return the protocol field value
         */
        public DOM.NodeId nodeId() {
            return require(NODE_ID);
        }
        /**
         * The node id that has updated computed styles.
         * @param nodeId field value
         * @return this model
         */
        public ComputedStyleUpdatedEvent nodeId(DOM.NodeId nodeId) {
            set("nodeId", nodeId);
            return this;
        }
    }
    public static final class Client extends CdpDomainClient {
        public Client(CdpClient client) { super(client); }
        /**
         * Inserts a new rule with the given {@code ruleText} in a stylesheet with given {@code styleSheetId}, at the position specified by {@code location}.
         * @param styleSheetId protocol value
         * @param ruleText protocol value
         * @param location protocol value
         * @param nodeForPropertySyntaxValidation protocol value
         * @return a stage completing with the command result
         */
        public CompletionStage<CSS.CSSRule> addRule(DOM.StyleSheetId styleSheetId, String ruleText, CSS.SourceRange location, DOM.NodeId nodeForPropertySyntaxValidation) {
            return call("CSS.addRule", params("styleSheetId", styleSheetId, "ruleText", ruleText, "location", location, "nodeForPropertySyntaxValidation", nodeForPropertySyntaxValidation), RULE::require);
        }
        /**
         * Inserts a new rule with the given {@code ruleText} in a stylesheet with given {@code styleSheetId}, at the position specified by {@code location}.
         * @param styleSheetId protocol value
         * @param ruleText protocol value
         * @param location protocol value
         * @return a stage completing with the command result
         */
        public CompletionStage<CSS.CSSRule> addRule(DOM.StyleSheetId styleSheetId, String ruleText, CSS.SourceRange location) {
            return call("CSS.addRule", params("styleSheetId", styleSheetId, "ruleText", ruleText, "location", location), RULE::require);
        }
        /**
         * Inserts a new rule with the given {@code ruleText} in a stylesheet with given {@code styleSheetId}, at the position specified by {@code location}.
         * @param request request parameters
         * @return a stage completing with the command result
         */
        public CompletionStage<CSS.CSSRule> addRule(AddRuleRequest request) {
            return call("CSS.addRule", request, RULE::require);
        }
        /**
         * Returns all class names from specified stylesheet.
         * @param styleSheetId protocol value
         * @return a stage completing with the command result
         */
        public CompletionStage<java.util.List<String>> collectClassNames(DOM.StyleSheetId styleSheetId) {
            return call("CSS.collectClassNames", params("styleSheetId", styleSheetId), CLASS_NAMES::require);
        }
        /**
         * Returns all class names from specified stylesheet.
         * @param request request parameters
         * @return a stage completing with the command result
         */
        public CompletionStage<java.util.List<String>> collectClassNames(CollectClassNamesRequest request) {
            return call("CSS.collectClassNames", request, CLASS_NAMES::require);
        }
        /**
         * Creates a new special &quot;via-inspector&quot; stylesheet in the frame with given {@code frameId}.
         * @param frameId protocol value
         * @param force protocol value
         * @return a stage completing with the command result
         */
        public CompletionStage<DOM.StyleSheetId> createStyleSheet(Page.FrameId frameId, boolean force) {
            return call("CSS.createStyleSheet", params("frameId", frameId, "force", force), STYLE_SHEET_ID::require);
        }
        /**
         * Creates a new special &quot;via-inspector&quot; stylesheet in the frame with given {@code frameId}.
         * @param frameId protocol value
         * @return a stage completing with the command result
         */
        public CompletionStage<DOM.StyleSheetId> createStyleSheet(Page.FrameId frameId) {
            return call("CSS.createStyleSheet", params("frameId", frameId), STYLE_SHEET_ID::require);
        }
        /**
         * Creates a new special &quot;via-inspector&quot; stylesheet in the frame with given {@code frameId}.
         * @param request request parameters
         * @return a stage completing with the command result
         */
        public CompletionStage<DOM.StyleSheetId> createStyleSheet(CreateStyleSheetRequest request) {
            return call("CSS.createStyleSheet", request, STYLE_SHEET_ID::require);
        }
        /**
         * Disables the CSS agent for the given page.
         * @return a stage completing when the command completes
         */
        public CompletionStage<Void> disable() {
            return call("CSS.disable");
        }
        /**
         * Enables the CSS agent for the given page. Clients should not assume that the CSS agent has been enabled until the result of this command is received.
         * @return a stage completing when the command completes
         */
        public CompletionStage<Void> enable() {
            return call("CSS.enable");
        }
        /**
         * Ensures that the given node will have specified pseudo-classes whenever its style is computed by the browser.
         * @param nodeId protocol value
         * @param forcedPseudoClasses protocol value
         * @return a stage completing when the command completes
         */
        public CompletionStage<Void> forcePseudoState(DOM.NodeId nodeId, java.util.List<String> forcedPseudoClasses) {
            return call("CSS.forcePseudoState", params("nodeId", nodeId, "forcedPseudoClasses", forcedPseudoClasses));
        }
        /**
         * Ensures that the given node will have specified pseudo-classes whenever its style is computed by the browser.
         * @param request request parameters
         * @return a stage completing when the command completes
         */
        public CompletionStage<Void> forcePseudoState(ForcePseudoStateRequest request) {
            return call("CSS.forcePseudoState", request);
        }
        /**
         * Ensures that the given node is in its starting-style state.
         * @param nodeId protocol value
         * @param forced protocol value
         * @return a stage completing when the command completes
         */
        public CompletionStage<Void> forceStartingStyle(DOM.NodeId nodeId, boolean forced) {
            return call("CSS.forceStartingStyle", params("nodeId", nodeId, "forced", forced));
        }
        /**
         * Ensures that the given node is in its starting-style state.
         * @param request request parameters
         * @return a stage completing when the command completes
         */
        public CompletionStage<Void> forceStartingStyle(ForceStartingStyleRequest request) {
            return call("CSS.forceStartingStyle", request);
        }
        /**
         * Invokes CSS.getBackgroundColors.
         * @param nodeId protocol value
         * @return a stage completing with the command result
         */
        public CompletionStage<GetBackgroundColorsResult> getBackgroundColors(DOM.NodeId nodeId) {
            return call("CSS.getBackgroundColors", params("nodeId", nodeId), GetBackgroundColorsResult::fromMap);
        }
        /**
         * Invokes CSS.getBackgroundColors with a request object.
         * @param request request parameters
         * @return a stage completing with the command result
         */
        public CompletionStage<GetBackgroundColorsResult> getBackgroundColors(GetBackgroundColorsRequest request) {
            return call("CSS.getBackgroundColors", request, GetBackgroundColorsResult::fromMap);
        }
        /**
         * Returns the computed style for a DOM node identified by {@code nodeId}.
         * @param nodeId protocol value
         * @return a stage completing with the command result
         */
        public CompletionStage<GetComputedStyleForNodeResult> getComputedStyleForNode(DOM.NodeId nodeId) {
            return call("CSS.getComputedStyleForNode", params("nodeId", nodeId), GetComputedStyleForNodeResult::fromMap);
        }
        /**
         * Returns the computed style for a DOM node identified by {@code nodeId}.
         * @param request request parameters
         * @return a stage completing with the command result
         */
        public CompletionStage<GetComputedStyleForNodeResult> getComputedStyleForNode(GetComputedStyleForNodeRequest request) {
            return call("CSS.getComputedStyleForNode", request, GetComputedStyleForNodeResult::fromMap);
        }
        /**
         * Resolve the specified values in the context of the provided element. For example, a value of &#x27;1em&#x27; is evaluated according to the computed &#x27;font-size&#x27; of the element and a value &#x27;calc(1px + 2px)&#x27; will be resolved to &#x27;3px&#x27;. If the {@code propertyName} was specified the {@code values} are resolved as if they were property&#x27;s declaration. If a value cannot be parsed according to the provided property syntax, the value is parsed using combined syntax as if null {@code propertyName} was provided. If the value cannot be resolved even then, return the provided value without any changes. Note: this function currently does not resolve CSS random() function, it returns unmodified random() function parts.`
         * <p><b>Experimental:</b> this part of CDP may change without notice.
         * @param values protocol value
         * @param nodeId protocol value
         * @param propertyName protocol value
         * @param pseudoType protocol value
         * @param pseudoIdentifier protocol value
         * @return a stage completing with the command result
         */
        public CompletionStage<java.util.List<String>> resolveValues(java.util.List<String> values, DOM.NodeId nodeId, @Nullable String propertyName, DOM.@Nullable PseudoType pseudoType, @Nullable String pseudoIdentifier) {
            return call("CSS.resolveValues", params("values", values, "nodeId", nodeId, "propertyName", propertyName, "pseudoType", pseudoType, "pseudoIdentifier", pseudoIdentifier), RESULTS::require);
        }
        /**
         * Resolve the specified values in the context of the provided element. For example, a value of &#x27;1em&#x27; is evaluated according to the computed &#x27;font-size&#x27; of the element and a value &#x27;calc(1px + 2px)&#x27; will be resolved to &#x27;3px&#x27;. If the {@code propertyName} was specified the {@code values} are resolved as if they were property&#x27;s declaration. If a value cannot be parsed according to the provided property syntax, the value is parsed using combined syntax as if null {@code propertyName} was provided. If the value cannot be resolved even then, return the provided value without any changes. Note: this function currently does not resolve CSS random() function, it returns unmodified random() function parts.`
         * <p><b>Experimental:</b> this part of CDP may change without notice.
         * @param values protocol value
         * @param nodeId protocol value
         * @return a stage completing with the command result
         */
        public CompletionStage<java.util.List<String>> resolveValues(java.util.List<String> values, DOM.NodeId nodeId) {
            return call("CSS.resolveValues", params("values", values, "nodeId", nodeId), RESULTS::require);
        }
        /**
         * Resolve the specified values in the context of the provided element. For example, a value of &#x27;1em&#x27; is evaluated according to the computed &#x27;font-size&#x27; of the element and a value &#x27;calc(1px + 2px)&#x27; will be resolved to &#x27;3px&#x27;. If the {@code propertyName} was specified the {@code values} are resolved as if they were property&#x27;s declaration. If a value cannot be parsed according to the provided property syntax, the value is parsed using combined syntax as if null {@code propertyName} was provided. If the value cannot be resolved even then, return the provided value without any changes. Note: this function currently does not resolve CSS random() function, it returns unmodified random() function parts.`
         * <p><b>Experimental:</b> this part of CDP may change without notice.
         * @param request request parameters
         * @return a stage completing with the command result
         */
        public CompletionStage<java.util.List<String>> resolveValues(ResolveValuesRequest request) {
            return call("CSS.resolveValues", request, RESULTS::require);
        }
        /**
         * Invokes CSS.getLonghandProperties.
         * <p><b>Experimental:</b> this part of CDP may change without notice.
         * @param shorthandName protocol value
         * @param value protocol value
         * @return a stage completing with the command result
         */
        public CompletionStage<java.util.List<CSS.CSSProperty>> getLonghandProperties(String shorthandName, String value) {
            return call("CSS.getLonghandProperties", params("shorthandName", shorthandName, "value", value), LONGHAND_PROPERTIES::require);
        }
        /**
         * Invokes CSS.getLonghandProperties with a request object.
         * <p><b>Experimental:</b> this part of CDP may change without notice.
         * @param request request parameters
         * @return a stage completing with the command result
         */
        public CompletionStage<java.util.List<CSS.CSSProperty>> getLonghandProperties(GetLonghandPropertiesRequest request) {
            return call("CSS.getLonghandProperties", request, LONGHAND_PROPERTIES::require);
        }
        /**
         * Returns the styles defined inline (explicitly in the &quot;style&quot; attribute and implicitly, using DOM attributes) for a DOM node identified by {@code nodeId}.
         * @param nodeId protocol value
         * @return a stage completing with the command result
         */
        public CompletionStage<GetInlineStylesForNodeResult> getInlineStylesForNode(DOM.NodeId nodeId) {
            return call("CSS.getInlineStylesForNode", params("nodeId", nodeId), GetInlineStylesForNodeResult::fromMap);
        }
        /**
         * Returns the styles defined inline (explicitly in the &quot;style&quot; attribute and implicitly, using DOM attributes) for a DOM node identified by {@code nodeId}.
         * @param request request parameters
         * @return a stage completing with the command result
         */
        public CompletionStage<GetInlineStylesForNodeResult> getInlineStylesForNode(GetInlineStylesForNodeRequest request) {
            return call("CSS.getInlineStylesForNode", request, GetInlineStylesForNodeResult::fromMap);
        }
        /**
         * Returns the styles coming from animations &amp; transitions including the animation &amp; transition styles coming from inheritance chain.
         * <p><b>Experimental:</b> this part of CDP may change without notice.
         * @param nodeId protocol value
         * @return a stage completing with the command result
         */
        public CompletionStage<GetAnimatedStylesForNodeResult> getAnimatedStylesForNode(DOM.NodeId nodeId) {
            return call("CSS.getAnimatedStylesForNode", params("nodeId", nodeId), GetAnimatedStylesForNodeResult::fromMap);
        }
        /**
         * Returns the styles coming from animations &amp; transitions including the animation &amp; transition styles coming from inheritance chain.
         * <p><b>Experimental:</b> this part of CDP may change without notice.
         * @param request request parameters
         * @return a stage completing with the command result
         */
        public CompletionStage<GetAnimatedStylesForNodeResult> getAnimatedStylesForNode(GetAnimatedStylesForNodeRequest request) {
            return call("CSS.getAnimatedStylesForNode", request, GetAnimatedStylesForNodeResult::fromMap);
        }
        /**
         * Returns requested styles for a DOM node identified by {@code nodeId}.
         * @param nodeId protocol value
         * @return a stage completing with the command result
         */
        public CompletionStage<GetMatchedStylesForNodeResult> getMatchedStylesForNode(DOM.NodeId nodeId) {
            return call("CSS.getMatchedStylesForNode", params("nodeId", nodeId), GetMatchedStylesForNodeResult::fromMap);
        }
        /**
         * Returns requested styles for a DOM node identified by {@code nodeId}.
         * @param request request parameters
         * @return a stage completing with the command result
         */
        public CompletionStage<GetMatchedStylesForNodeResult> getMatchedStylesForNode(GetMatchedStylesForNodeRequest request) {
            return call("CSS.getMatchedStylesForNode", request, GetMatchedStylesForNodeResult::fromMap);
        }
        /**
         * Returns the values of the default UA-defined environment variables used in env()
         * <p><b>Experimental:</b> this part of CDP may change without notice.
         * @return a stage completing with the command result
         */
        public CompletionStage<java.util.Map<String, Object>> getEnvironmentVariables() {
            return call("CSS.getEnvironmentVariables", ENVIRONMENT_VARIABLES::require);
        }
        /**
         * Returns all media queries parsed by the rendering engine.
         * @return a stage completing with the command result
         */
        public CompletionStage<java.util.List<CSS.CSSMedia>> getMediaQueries() {
            return call("CSS.getMediaQueries", MEDIAS::require);
        }
        /**
         * Requests information about platform fonts which we used to render child TextNodes in the given node.
         * @param nodeId protocol value
         * @return a stage completing with the command result
         */
        public CompletionStage<java.util.List<CSS.PlatformFontUsage>> getPlatformFontsForNode(DOM.NodeId nodeId) {
            return call("CSS.getPlatformFontsForNode", params("nodeId", nodeId), FONTS::require);
        }
        /**
         * Requests information about platform fonts which we used to render child TextNodes in the given node.
         * @param request request parameters
         * @return a stage completing with the command result
         */
        public CompletionStage<java.util.List<CSS.PlatformFontUsage>> getPlatformFontsForNode(GetPlatformFontsForNodeRequest request) {
            return call("CSS.getPlatformFontsForNode", request, FONTS::require);
        }
        /**
         * Returns the current textual content for a stylesheet.
         * @param styleSheetId protocol value
         * @return a stage completing with the command result
         */
        public CompletionStage<String> getStyleSheetText(DOM.StyleSheetId styleSheetId) {
            return call("CSS.getStyleSheetText", params("styleSheetId", styleSheetId), TEXT::require);
        }
        /**
         * Returns the current textual content for a stylesheet.
         * @param request request parameters
         * @return a stage completing with the command result
         */
        public CompletionStage<String> getStyleSheetText(GetStyleSheetTextRequest request) {
            return call("CSS.getStyleSheetText", request, TEXT::require);
        }
        /**
         * Returns all layers parsed by the rendering engine for the tree scope of a node. Given a DOM element identified by nodeId, getLayersForNode returns the root layer for the nearest ancestor document or shadow root. The layer root contains the full layer tree for the tree scope and their ordering.
         * <p><b>Experimental:</b> this part of CDP may change without notice.
         * @param nodeId protocol value
         * @return a stage completing with the command result
         */
        public CompletionStage<CSS.CSSLayerData> getLayersForNode(DOM.NodeId nodeId) {
            return call("CSS.getLayersForNode", params("nodeId", nodeId), ROOT_LAYER::require);
        }
        /**
         * Returns all layers parsed by the rendering engine for the tree scope of a node. Given a DOM element identified by nodeId, getLayersForNode returns the root layer for the nearest ancestor document or shadow root. The layer root contains the full layer tree for the tree scope and their ordering.
         * <p><b>Experimental:</b> this part of CDP may change without notice.
         * @param request request parameters
         * @return a stage completing with the command result
         */
        public CompletionStage<CSS.CSSLayerData> getLayersForNode(GetLayersForNodeRequest request) {
            return call("CSS.getLayersForNode", request, ROOT_LAYER::require);
        }
        /**
         * Given a CSS selector text and a style sheet ID, getLocationForSelector returns an array of locations of the CSS selector in the style sheet.
         * <p><b>Experimental:</b> this part of CDP may change without notice.
         * @param styleSheetId protocol value
         * @param selectorText protocol value
         * @return a stage completing with the command result
         */
        public CompletionStage<java.util.List<CSS.SourceRange>> getLocationForSelector(DOM.StyleSheetId styleSheetId, String selectorText) {
            return call("CSS.getLocationForSelector", params("styleSheetId", styleSheetId, "selectorText", selectorText), RANGES::require);
        }
        /**
         * Given a CSS selector text and a style sheet ID, getLocationForSelector returns an array of locations of the CSS selector in the style sheet.
         * <p><b>Experimental:</b> this part of CDP may change without notice.
         * @param request request parameters
         * @return a stage completing with the command result
         */
        public CompletionStage<java.util.List<CSS.SourceRange>> getLocationForSelector(GetLocationForSelectorRequest request) {
            return call("CSS.getLocationForSelector", request, RANGES::require);
        }
        /**
         * Starts tracking the given node for the computed style updates and whenever the computed style is updated for node, it queues a {@code computedStyleUpdated} event with throttling. There can only be 1 node tracked for computed style updates so passing a new node id removes tracking from the previous node. Pass {@code undefined} to disable tracking.
         * <p><b>Experimental:</b> this part of CDP may change without notice.
         * @param nodeId protocol value
         * @return a stage completing when the command completes
         */
        public CompletionStage<Void> trackComputedStyleUpdatesForNode(DOM.NodeId nodeId) {
            return call("CSS.trackComputedStyleUpdatesForNode", params("nodeId", nodeId));
        }
        /**
         * Starts tracking the given node for the computed style updates and whenever the computed style is updated for node, it queues a {@code computedStyleUpdated} event with throttling. There can only be 1 node tracked for computed style updates so passing a new node id removes tracking from the previous node. Pass {@code undefined} to disable tracking.
         * <p><b>Experimental:</b> this part of CDP may change without notice.
         * @return a stage completing when the command completes
         */
        public CompletionStage<Void> trackComputedStyleUpdatesForNode() {
            return call("CSS.trackComputedStyleUpdatesForNode");
        }
        /**
         * Starts tracking the given node for the computed style updates and whenever the computed style is updated for node, it queues a {@code computedStyleUpdated} event with throttling. There can only be 1 node tracked for computed style updates so passing a new node id removes tracking from the previous node. Pass {@code undefined} to disable tracking.
         * <p><b>Experimental:</b> this part of CDP may change without notice.
         * @param request request parameters
         * @return a stage completing when the command completes
         */
        public CompletionStage<Void> trackComputedStyleUpdatesForNode(TrackComputedStyleUpdatesForNodeRequest request) {
            return call("CSS.trackComputedStyleUpdatesForNode", request);
        }
        /**
         * Starts tracking the given computed styles for updates. The specified array of properties replaces the one previously specified. Pass empty array to disable tracking. Use takeComputedStyleUpdates to retrieve the list of nodes that had properties modified. The changes to computed style properties are only tracked for nodes pushed to the front-end by the DOM agent. If no changes to the tracked properties occur after the node has been pushed to the front-end, no updates will be issued for the node.
         * <p><b>Experimental:</b> this part of CDP may change without notice.
         * @param propertiesToTrack protocol value
         * @return a stage completing when the command completes
         */
        public CompletionStage<Void> trackComputedStyleUpdates(java.util.List<CSS.CSSComputedStyleProperty> propertiesToTrack) {
            return call("CSS.trackComputedStyleUpdates", params("propertiesToTrack", propertiesToTrack));
        }
        /**
         * Starts tracking the given computed styles for updates. The specified array of properties replaces the one previously specified. Pass empty array to disable tracking. Use takeComputedStyleUpdates to retrieve the list of nodes that had properties modified. The changes to computed style properties are only tracked for nodes pushed to the front-end by the DOM agent. If no changes to the tracked properties occur after the node has been pushed to the front-end, no updates will be issued for the node.
         * <p><b>Experimental:</b> this part of CDP may change without notice.
         * @param request request parameters
         * @return a stage completing when the command completes
         */
        public CompletionStage<Void> trackComputedStyleUpdates(TrackComputedStyleUpdatesRequest request) {
            return call("CSS.trackComputedStyleUpdates", request);
        }
        /**
         * Polls the next batch of computed style updates.
         * <p><b>Experimental:</b> this part of CDP may change without notice.
         * @return a stage completing with the command result
         */
        public CompletionStage<java.util.List<DOM.NodeId>> takeComputedStyleUpdates() {
            return call("CSS.takeComputedStyleUpdates", NODE_IDS::require);
        }
        /**
         * Find a rule with the given active property for the given node and set the new value for this property
         * @param nodeId protocol value
         * @param propertyName protocol value
         * @param value protocol value
         * @return a stage completing when the command completes
         */
        public CompletionStage<Void> setEffectivePropertyValueForNode(DOM.NodeId nodeId, String propertyName, String value) {
            return call("CSS.setEffectivePropertyValueForNode", params("nodeId", nodeId, "propertyName", propertyName, "value", value));
        }
        /**
         * Find a rule with the given active property for the given node and set the new value for this property
         * @param request request parameters
         * @return a stage completing when the command completes
         */
        public CompletionStage<Void> setEffectivePropertyValueForNode(SetEffectivePropertyValueForNodeRequest request) {
            return call("CSS.setEffectivePropertyValueForNode", request);
        }
        /**
         * Modifies the property rule property name.
         * @param styleSheetId protocol value
         * @param range protocol value
         * @param propertyName protocol value
         * @return a stage completing with the command result
         */
        public CompletionStage<CSS.Value> setPropertyRulePropertyName(DOM.StyleSheetId styleSheetId, CSS.SourceRange range, String propertyName) {
            return call("CSS.setPropertyRulePropertyName", params("styleSheetId", styleSheetId, "range", range, "propertyName", propertyName), PROPERTY_NAME::require);
        }
        /**
         * Modifies the property rule property name.
         * @param request request parameters
         * @return a stage completing with the command result
         */
        public CompletionStage<CSS.Value> setPropertyRulePropertyName(SetPropertyRulePropertyNameRequest request) {
            return call("CSS.setPropertyRulePropertyName", request, PROPERTY_NAME::require);
        }
        /**
         * Modifies the keyframe rule key text.
         * @param styleSheetId protocol value
         * @param range protocol value
         * @param keyText protocol value
         * @return a stage completing with the command result
         */
        public CompletionStage<CSS.Value> setKeyframeKey(DOM.StyleSheetId styleSheetId, CSS.SourceRange range, String keyText) {
            return call("CSS.setKeyframeKey", params("styleSheetId", styleSheetId, "range", range, "keyText", keyText), KEY_TEXT::require);
        }
        /**
         * Modifies the keyframe rule key text.
         * @param request request parameters
         * @return a stage completing with the command result
         */
        public CompletionStage<CSS.Value> setKeyframeKey(SetKeyframeKeyRequest request) {
            return call("CSS.setKeyframeKey", request, KEY_TEXT::require);
        }
        /**
         * Modifies the rule selector.
         * @param styleSheetId protocol value
         * @param range protocol value
         * @param text protocol value
         * @return a stage completing with the command result
         */
        public CompletionStage<CSS.CSSMedia> setMediaText(DOM.StyleSheetId styleSheetId, CSS.SourceRange range, String text) {
            return call("CSS.setMediaText", params("styleSheetId", styleSheetId, "range", range, "text", text), MEDIA_2::require);
        }
        /**
         * Modifies the rule selector.
         * @param request request parameters
         * @return a stage completing with the command result
         */
        public CompletionStage<CSS.CSSMedia> setMediaText(SetMediaTextRequest request) {
            return call("CSS.setMediaText", request, MEDIA_2::require);
        }
        /**
         * Modifies the expression of a container query. Deprecated. Use setContainerQueryConditionText instead.
         * <p><b>Experimental:</b> this part of CDP may change without notice.
         * @param styleSheetId protocol value
         * @param range protocol value
         * @param text protocol value
         * @return a stage completing with the command result
         * @deprecated Deprecated by the Chromium DevTools Protocol.
         */
        @Deprecated
        public CompletionStage<CSS.CSSContainerQuery> setContainerQueryText(DOM.StyleSheetId styleSheetId, CSS.SourceRange range, String text) {
            return call("CSS.setContainerQueryText", params("styleSheetId", styleSheetId, "range", range, "text", text), CONTAINER_QUERY::require);
        }
        /**
         * Modifies the expression of a container query. Deprecated. Use setContainerQueryConditionText instead.
         * <p><b>Experimental:</b> this part of CDP may change without notice.
         * @param request request parameters
         * @return a stage completing with the command result
         * @deprecated Deprecated by the Chromium DevTools Protocol.
         */
        @Deprecated
        public CompletionStage<CSS.CSSContainerQuery> setContainerQueryText(SetContainerQueryTextRequest request) {
            return call("CSS.setContainerQueryText", request, CONTAINER_QUERY::require);
        }
        /**
         * Invokes CSS.setContainerQueryConditionText.
         * <p><b>Experimental:</b> this part of CDP may change without notice.
         * @param styleSheetId protocol value
         * @param range protocol value
         * @param text protocol value
         * @return a stage completing with the command result
         */
        public CompletionStage<CSS.CSSContainerQuery> setContainerQueryConditionText(DOM.StyleSheetId styleSheetId, CSS.SourceRange range, String text) {
            return call("CSS.setContainerQueryConditionText", params("styleSheetId", styleSheetId, "range", range, "text", text), CONTAINER_QUERY::require);
        }
        /**
         * Invokes CSS.setContainerQueryConditionText with a request object.
         * <p><b>Experimental:</b> this part of CDP may change without notice.
         * @param request request parameters
         * @return a stage completing with the command result
         */
        public CompletionStage<CSS.CSSContainerQuery> setContainerQueryConditionText(SetContainerQueryConditionTextRequest request) {
            return call("CSS.setContainerQueryConditionText", request, CONTAINER_QUERY::require);
        }
        /**
         * Modifies the expression of a supports at-rule.
         * <p><b>Experimental:</b> this part of CDP may change without notice.
         * @param styleSheetId protocol value
         * @param range protocol value
         * @param text protocol value
         * @return a stage completing with the command result
         */
        public CompletionStage<CSS.CSSSupports> setSupportsText(DOM.StyleSheetId styleSheetId, CSS.SourceRange range, String text) {
            return call("CSS.setSupportsText", params("styleSheetId", styleSheetId, "range", range, "text", text), SUPPORTS_2::require);
        }
        /**
         * Modifies the expression of a supports at-rule.
         * <p><b>Experimental:</b> this part of CDP may change without notice.
         * @param request request parameters
         * @return a stage completing with the command result
         */
        public CompletionStage<CSS.CSSSupports> setSupportsText(SetSupportsTextRequest request) {
            return call("CSS.setSupportsText", request, SUPPORTS_2::require);
        }
        /**
         * Modifies the expression of a navigation at-rule.
         * <p><b>Experimental:</b> this part of CDP may change without notice.
         * @param styleSheetId protocol value
         * @param range protocol value
         * @param text protocol value
         * @return a stage completing with the command result
         */
        public CompletionStage<CSS.CSSNavigation> setNavigationText(DOM.StyleSheetId styleSheetId, CSS.SourceRange range, String text) {
            return call("CSS.setNavigationText", params("styleSheetId", styleSheetId, "range", range, "text", text), NAVIGATION::require);
        }
        /**
         * Modifies the expression of a navigation at-rule.
         * <p><b>Experimental:</b> this part of CDP may change without notice.
         * @param request request parameters
         * @return a stage completing with the command result
         */
        public CompletionStage<CSS.CSSNavigation> setNavigationText(SetNavigationTextRequest request) {
            return call("CSS.setNavigationText", request, NAVIGATION::require);
        }
        /**
         * Modifies the expression of a scope at-rule.
         * <p><b>Experimental:</b> this part of CDP may change without notice.
         * @param styleSheetId protocol value
         * @param range protocol value
         * @param text protocol value
         * @return a stage completing with the command result
         */
        public CompletionStage<CSS.CSSScope> setScopeText(DOM.StyleSheetId styleSheetId, CSS.SourceRange range, String text) {
            return call("CSS.setScopeText", params("styleSheetId", styleSheetId, "range", range, "text", text), SCOPE::require);
        }
        /**
         * Modifies the expression of a scope at-rule.
         * <p><b>Experimental:</b> this part of CDP may change without notice.
         * @param request request parameters
         * @return a stage completing with the command result
         */
        public CompletionStage<CSS.CSSScope> setScopeText(SetScopeTextRequest request) {
            return call("CSS.setScopeText", request, SCOPE::require);
        }
        /**
         * Modifies the rule selector.
         * @param styleSheetId protocol value
         * @param range protocol value
         * @param selector protocol value
         * @return a stage completing with the command result
         */
        public CompletionStage<CSS.SelectorList> setRuleSelector(DOM.StyleSheetId styleSheetId, CSS.SourceRange range, String selector) {
            return call("CSS.setRuleSelector", params("styleSheetId", styleSheetId, "range", range, "selector", selector), SELECTOR_LIST::require);
        }
        /**
         * Modifies the rule selector.
         * @param request request parameters
         * @return a stage completing with the command result
         */
        public CompletionStage<CSS.SelectorList> setRuleSelector(SetRuleSelectorRequest request) {
            return call("CSS.setRuleSelector", request, SELECTOR_LIST::require);
        }
        /**
         * Sets the new stylesheet text.
         * @param styleSheetId protocol value
         * @param text protocol value
         * @return a stage completing with the command result
         */
        public CompletionStage<Optional<String>> setStyleSheetText(DOM.StyleSheetId styleSheetId, String text) {
            return call("CSS.setStyleSheetText", params("styleSheetId", styleSheetId, "text", text), SOURCE_MAP_URL::optional);
        }
        /**
         * Sets the new stylesheet text.
         * @param request request parameters
         * @return a stage completing with the command result
         */
        public CompletionStage<Optional<String>> setStyleSheetText(SetStyleSheetTextRequest request) {
            return call("CSS.setStyleSheetText", request, SOURCE_MAP_URL::optional);
        }
        /**
         * Applies specified style edits one after another in the given order.
         * @param edits protocol value
         * @param nodeForPropertySyntaxValidation protocol value
         * @return a stage completing with the command result
         */
        public CompletionStage<java.util.List<CSS.CSSStyle>> setStyleTexts(java.util.List<CSS.StyleDeclarationEdit> edits, DOM.NodeId nodeForPropertySyntaxValidation) {
            return call("CSS.setStyleTexts", params("edits", edits, "nodeForPropertySyntaxValidation", nodeForPropertySyntaxValidation), STYLES::require);
        }
        /**
         * Applies specified style edits one after another in the given order.
         * @param edits protocol value
         * @return a stage completing with the command result
         */
        public CompletionStage<java.util.List<CSS.CSSStyle>> setStyleTexts(java.util.List<CSS.StyleDeclarationEdit> edits) {
            return call("CSS.setStyleTexts", params("edits", edits), STYLES::require);
        }
        /**
         * Applies specified style edits one after another in the given order.
         * @param request request parameters
         * @return a stage completing with the command result
         */
        public CompletionStage<java.util.List<CSS.CSSStyle>> setStyleTexts(SetStyleTextsRequest request) {
            return call("CSS.setStyleTexts", request, STYLES::require);
        }
        /**
         * Enables the selector recording.
         * @return a stage completing when the command completes
         */
        public CompletionStage<Void> startRuleUsageTracking() {
            return call("CSS.startRuleUsageTracking");
        }
        /**
         * Stop tracking rule usage and return the list of rules that were used since last call to {@code takeCoverageDelta} (or since start of coverage instrumentation).
         * @return a stage completing with the command result
         */
        public CompletionStage<java.util.List<CSS.RuleUsage>> stopRuleUsageTracking() {
            return call("CSS.stopRuleUsageTracking", RULE_USAGE::require);
        }
        /**
         * Obtain list of rules that became used since last call to this method (or since start of coverage instrumentation).
         * @return a stage completing with the command result
         */
        public CompletionStage<TakeCoverageDeltaResult> takeCoverageDelta() {
            return call("CSS.takeCoverageDelta", TakeCoverageDeltaResult::fromMap);
        }
        /**
         * Enables/disables rendering of local CSS fonts (enabled by default).
         * <p><b>Experimental:</b> this part of CDP may change without notice.
         * @param enabled protocol value
         * @return a stage completing when the command completes
         */
        public CompletionStage<Void> setLocalFontsEnabled(boolean enabled) {
            return call("CSS.setLocalFontsEnabled", params("enabled", enabled));
        }
        /**
         * Enables/disables rendering of local CSS fonts (enabled by default).
         * <p><b>Experimental:</b> this part of CDP may change without notice.
         * @param request request parameters
         * @return a stage completing when the command completes
         */
        public CompletionStage<Void> setLocalFontsEnabled(SetLocalFontsEnabledRequest request) {
            return call("CSS.setLocalFontsEnabled", request);
        }
        /**
         * Fires whenever a web font is updated. A non-empty font parameter indicates a successfully loaded web font.
         * @param handler event callback
         * @return a removable subscription
         */
        public CdpSubscription onFontsUpdated(Consumer<FontsUpdatedEvent> handler) {
            return on("CSS.fontsUpdated", FontsUpdatedEvent::fromMap, handler);
        }
        /**
         * Fires whenever a MediaQuery result changes (for example, after a browser window has been resized.) The current implementation considers only viewport-dependent media features.
         * @param handler event callback
         * @return a removable subscription
         */
        public CdpSubscription onMediaQueryResultChanged(Consumer<MediaQueryResultChangedEvent> handler) {
            return on("CSS.mediaQueryResultChanged", MediaQueryResultChangedEvent::fromMap, handler);
        }
        /**
         * Fired whenever an active document stylesheet is added.
         * @param handler event callback
         * @return a removable subscription
         */
        public CdpSubscription onStyleSheetAdded(Consumer<StyleSheetAddedEvent> handler) {
            return on("CSS.styleSheetAdded", StyleSheetAddedEvent::fromMap, handler);
        }
        /**
         * Fired whenever a stylesheet is changed as a result of the client operation.
         * @param handler event callback
         * @return a removable subscription
         */
        public CdpSubscription onStyleSheetChanged(Consumer<StyleSheetChangedEvent> handler) {
            return on("CSS.styleSheetChanged", StyleSheetChangedEvent::fromMap, handler);
        }
        /**
         * Fired whenever an active document stylesheet is removed.
         * @param handler event callback
         * @return a removable subscription
         */
        public CdpSubscription onStyleSheetRemoved(Consumer<StyleSheetRemovedEvent> handler) {
            return on("CSS.styleSheetRemoved", StyleSheetRemovedEvent::fromMap, handler);
        }
        /**
         * Subscribes to CSS.computedStyleUpdated.
         * <p><b>Experimental:</b> this part of CDP may change without notice.
         * @param handler event callback
         * @return a removable subscription
         */
        public CdpSubscription onComputedStyleUpdated(Consumer<ComputedStyleUpdatedEvent> handler) {
            return on("CSS.computedStyleUpdated", ComputedStyleUpdatedEvent::fromMap, handler);
        }
    }
}
