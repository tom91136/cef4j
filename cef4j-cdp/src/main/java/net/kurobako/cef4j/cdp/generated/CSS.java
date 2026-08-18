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
 * This domain exposes CSS read/write operations. All CSS objects (stylesheets, rules, and styles) have an associated {@code id} used in subsequent operations on the related object. Each object type has a specific {@code id} structure, and those are not interchangeable between objects of different kinds. CSS objects can be loaded using the {@code get*ForNode()} calls (which accept a DOM node id). A client can also keep track of stylesheets via the {@code styleSheetAdded}/{@code styleSheetRemoved} events and subsequently load the required stylesheet contents using the {@code getStyleSheet[Text]()} methods.
 * <p><b>Experimental:</b> this part of CDP may change without notice.
 * @see <a href="https://chromium.googlesource.com/chromium/src/+/refs/tags/150.0.7871.213/third_party/blink/public/devtools_protocol/domains/CSS.pdl">Pinned protocol source</a>
 */
@Generated("mvn generate-sources -pl cef4j-platform -Dcef.version=150.0.18+gdb11278+chromium-150.0.7871.213")
@SuppressWarnings({"EscapedEntity", "InvalidParam", "JavaLangClash", "MissingSummary", "Unchecked", "UnusedMethod"})
public final class CSS {
    private CSS() {}
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
        @Nonnull public String value() { return value; }
        public static StyleSheetOrigin of(@Nonnull String value) {
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
            return DOM.PseudoType.of((String) require("pseudoType"));
        }
        /**
         * Pseudo element custom ident.
         * @return the protocol field value, empty when absent
         */
        public Optional<String> pseudoIdentifier() {
            return Optional.ofNullable((String) raw("pseudoIdentifier"));
        }
        /**
         * Matches of CSS rules applicable to the pseudo style.
         * @return the protocol field value
         */
        public java.util.List<CSS.RuleMatch> matches() {
            return CdpObject.requireList(require("matches"), element0 -> java.util.Objects.requireNonNull(CSS.RuleMatch.fromMap(java.util.Objects.requireNonNull(CdpObject.objectMap(element0)))));
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
         * @param pseudoIdentifier field value; empty omits the value
         * @return this model
         */
        public PseudoElementMatches pseudoIdentifier(Optional<String> pseudoIdentifier) {
            set("pseudoIdentifier", pseudoIdentifier.orElse(null));
            return this;
        }
        /**
         * Pseudo element custom ident.
         * @param pseudoIdentifier field value; null removes the value
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
            return Optional.ofNullable((String) raw("name"));
        }
        /**
         * The style coming from the animation.
         * @return the protocol field value
         */
        public CSS.CSSStyle style() {
            return java.util.Objects.requireNonNull(CSS.CSSStyle.fromMap(java.util.Objects.requireNonNull(CdpObject.objectMap(require("style")))));
        }
        /**
         * The name of the animation.
         * @param name field value; empty omits the value
         * @return this model
         */
        public CSSAnimationStyle name(Optional<String> name) {
            set("name", name.orElse(null));
            return this;
        }
        /**
         * The name of the animation.
         * @param name field value; null removes the value
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
            return Optional.ofNullable(raw("inlineStyle") == null ? null : CSS.CSSStyle.fromMap(java.util.Objects.requireNonNull(objectMap(raw("inlineStyle")))));
        }
        /**
         * Matches of CSS rules matching the ancestor node in the style inheritance chain.
         * @return the protocol field value
         */
        public java.util.List<CSS.RuleMatch> matchedCSSRules() {
            return CdpObject.requireList(require("matchedCSSRules"), element0 -> java.util.Objects.requireNonNull(CSS.RuleMatch.fromMap(java.util.Objects.requireNonNull(CdpObject.objectMap(element0)))));
        }
        /**
         * The ancestor node&#x27;s inline style, if any, in the style inheritance chain.
         * @param inlineStyle field value; empty omits the value
         * @return this model
         */
        public InheritedStyleEntry inlineStyle(Optional<CSS.CSSStyle> inlineStyle) {
            set("inlineStyle", inlineStyle.orElse(null));
            return this;
        }
        /**
         * The ancestor node&#x27;s inline style, if any, in the style inheritance chain.
         * @param inlineStyle field value; null removes the value
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
            return Optional.ofNullable(list(raw("animationStyles"), element0 -> java.util.Objects.requireNonNull(CSS.CSSAnimationStyle.fromMap(java.util.Objects.requireNonNull(CdpObject.objectMap(element0))))));
        }
        /**
         * The style coming from the transitions of the ancestor, if any, in the style inheritance chain.
         * @return the protocol field value, empty when absent
         */
        public Optional<CSS.CSSStyle> transitionsStyle() {
            return Optional.ofNullable(raw("transitionsStyle") == null ? null : CSS.CSSStyle.fromMap(java.util.Objects.requireNonNull(objectMap(raw("transitionsStyle")))));
        }
        /**
         * Styles coming from the animations of the ancestor, if any, in the style inheritance chain.
         * @param animationStyles field value; empty omits the value
         * @return this model
         */
        public InheritedAnimatedStyleEntry animationStyles(Optional<java.util.List<CSS.CSSAnimationStyle>> animationStyles) {
            set("animationStyles", animationStyles.orElse(null));
            return this;
        }
        /**
         * Styles coming from the animations of the ancestor, if any, in the style inheritance chain.
         * @param animationStyles field value; null removes the value
         * @return this model
         */
        public InheritedAnimatedStyleEntry animationStyles(java.util.List<CSS.CSSAnimationStyle> animationStyles) {
            set("animationStyles", animationStyles);
            return this;
        }
        /**
         * The style coming from the transitions of the ancestor, if any, in the style inheritance chain.
         * @param transitionsStyle field value; empty omits the value
         * @return this model
         */
        public InheritedAnimatedStyleEntry transitionsStyle(Optional<CSS.CSSStyle> transitionsStyle) {
            set("transitionsStyle", transitionsStyle.orElse(null));
            return this;
        }
        /**
         * The style coming from the transitions of the ancestor, if any, in the style inheritance chain.
         * @param transitionsStyle field value; null removes the value
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
            return CdpObject.requireList(require("pseudoElements"), element0 -> java.util.Objects.requireNonNull(CSS.PseudoElementMatches.fromMap(java.util.Objects.requireNonNull(CdpObject.objectMap(element0)))));
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
            return java.util.Objects.requireNonNull(CSS.CSSRule.fromMap(java.util.Objects.requireNonNull(CdpObject.objectMap(require("rule")))));
        }
        /**
         * Matching selector indices in the rule&#x27;s selectorList selectors (0-based).
         * @return the protocol field value
         */
        public java.util.List<Long> matchingSelectors() {
            return CdpObject.requireList(require("matchingSelectors"), element0 -> ((Number) element0).longValue());
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
            return (String) require("text");
        }
        /**
         * Value range in the underlying resource (if available).
         * @return the protocol field value, empty when absent
         */
        public Optional<CSS.SourceRange> range() {
            return Optional.ofNullable(raw("range") == null ? null : CSS.SourceRange.fromMap(java.util.Objects.requireNonNull(objectMap(raw("range")))));
        }
        /**
         * Specificity of the selector.
         * <p><b>Experimental:</b> this part of CDP may change without notice.
         * @return the protocol field value, empty when absent
         */
        public Optional<CSS.Specificity> specificity() {
            return Optional.ofNullable(raw("specificity") == null ? null : CSS.Specificity.fromMap(java.util.Objects.requireNonNull(objectMap(raw("specificity")))));
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
         * @param range field value; empty omits the value
         * @return this model
         */
        public Value range(Optional<CSS.SourceRange> range) {
            set("range", range.orElse(null));
            return this;
        }
        /**
         * Value range in the underlying resource (if available).
         * @param range field value; null removes the value
         * @return this model
         */
        public Value range(CSS.SourceRange range) {
            set("range", range);
            return this;
        }
        /**
         * Specificity of the selector.
         * <p><b>Experimental:</b> this part of CDP may change without notice.
         * @param specificity field value; empty omits the value
         * @return this model
         */
        public Value specificity(Optional<CSS.Specificity> specificity) {
            set("specificity", specificity.orElse(null));
            return this;
        }
        /**
         * Specificity of the selector.
         * <p><b>Experimental:</b> this part of CDP may change without notice.
         * @param specificity field value; null removes the value
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
            return ((Number) require("a")).longValue();
        }
        /**
         * The b component, which represents the number of class selectors, attributes selectors, and pseudo-classes.
         * @return the protocol field value
         */
        public long b() {
            return ((Number) require("b")).longValue();
        }
        /**
         * The c component, which represents the number of type selectors and pseudo-elements.
         * @return the protocol field value
         */
        public long c() {
            return ((Number) require("c")).longValue();
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
            return CdpObject.requireList(require("selectors"), element0 -> java.util.Objects.requireNonNull(CSS.Value.fromMap(java.util.Objects.requireNonNull(CdpObject.objectMap(element0)))));
        }
        /**
         * Rule selector text.
         * @return the protocol field value
         */
        public String text() {
            return (String) require("text");
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
            return new DOM.StyleSheetId((String) require("styleSheetId"));
        }
        /**
         * Owner frame identifier.
         * @return the protocol field value
         */
        public Page.FrameId frameId() {
            return new Page.FrameId((String) require("frameId"));
        }
        /**
         * Stylesheet resource URL. Empty if this is a constructed stylesheet created using new CSSStyleSheet() (but non-empty if this is a constructed stylesheet imported as a CSS module script).
         * @return the protocol field value
         */
        public String sourceURL() {
            return (String) require("sourceURL");
        }
        /**
         * URL of source map associated with the stylesheet (if any).
         * @return the protocol field value, empty when absent
         */
        public Optional<String> sourceMapURL() {
            return Optional.ofNullable((String) raw("sourceMapURL"));
        }
        /**
         * Stylesheet origin.
         * @return the protocol field value
         */
        public CSS.StyleSheetOrigin origin() {
            return CSS.StyleSheetOrigin.of((String) require("origin"));
        }
        /**
         * Stylesheet title.
         * @return the protocol field value
         */
        public String title() {
            return (String) require("title");
        }
        /**
         * The backend id for the owner node of the stylesheet.
         * @return the protocol field value, empty when absent
         */
        public Optional<DOM.BackendNodeId> ownerNode() {
            return Optional.ofNullable(raw("ownerNode") == null ? null : new DOM.BackendNodeId(((Number) raw("ownerNode")).longValue()));
        }
        /**
         * Denotes whether the stylesheet is disabled.
         * @return the protocol field value
         */
        public boolean disabled() {
            return (Boolean) require("disabled");
        }
        /**
         * Whether the sourceURL field value comes from the sourceURL comment.
         * @return the protocol field value, empty when absent
         */
        public Optional<Boolean> hasSourceURL() {
            return Optional.ofNullable((Boolean) raw("hasSourceURL"));
        }
        /**
         * Whether this stylesheet is created for STYLE tag by parser. This flag is not set for document.written STYLE tags.
         * @return the protocol field value
         */
        public boolean isInline() {
            return (Boolean) require("isInline");
        }
        /**
         * Whether this stylesheet is mutable. Inline stylesheets become mutable after they have been modified via CSSOM API. {@code &lt;link&gt;} element&#x27;s stylesheets become mutable only if DevTools modifies them. Constructed stylesheets (new CSSStyleSheet()) are mutable immediately after creation.
         * @return the protocol field value
         */
        public boolean isMutable() {
            return (Boolean) require("isMutable");
        }
        /**
         * True if this stylesheet is created through new CSSStyleSheet() or imported as a CSS module script.
         * @return the protocol field value
         */
        public boolean isConstructed() {
            return (Boolean) require("isConstructed");
        }
        /**
         * Line offset of the stylesheet within the resource (zero based).
         * @return the protocol field value
         */
        public double startLine() {
            return ((Number) require("startLine")).doubleValue();
        }
        /**
         * Column offset of the stylesheet within the resource (zero based).
         * @return the protocol field value
         */
        public double startColumn() {
            return ((Number) require("startColumn")).doubleValue();
        }
        /**
         * Size of the content (in characters).
         * @return the protocol field value
         */
        public double length() {
            return ((Number) require("length")).doubleValue();
        }
        /**
         * Line offset of the end of the stylesheet within the resource (zero based).
         * @return the protocol field value
         */
        public double endLine() {
            return ((Number) require("endLine")).doubleValue();
        }
        /**
         * Column offset of the end of the stylesheet within the resource (zero based).
         * @return the protocol field value
         */
        public double endColumn() {
            return ((Number) require("endColumn")).doubleValue();
        }
        /**
         * If the style sheet was loaded from a network resource, this indicates when the resource failed to load
         * <p><b>Experimental:</b> this part of CDP may change without notice.
         * @return the protocol field value, empty when absent
         */
        public Optional<Boolean> loadingFailed() {
            return Optional.ofNullable((Boolean) raw("loadingFailed"));
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
         * @param sourceMapURL field value; empty omits the value
         * @return this model
         */
        public CSSStyleSheetHeader sourceMapURL(Optional<String> sourceMapURL) {
            set("sourceMapURL", sourceMapURL.orElse(null));
            return this;
        }
        /**
         * URL of source map associated with the stylesheet (if any).
         * @param sourceMapURL field value; null removes the value
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
         * @param ownerNode field value; empty omits the value
         * @return this model
         */
        public CSSStyleSheetHeader ownerNode(Optional<DOM.BackendNodeId> ownerNode) {
            set("ownerNode", ownerNode.orElse(null));
            return this;
        }
        /**
         * The backend id for the owner node of the stylesheet.
         * @param ownerNode field value; null removes the value
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
         * @param hasSourceURL field value; empty omits the value
         * @return this model
         */
        public CSSStyleSheetHeader hasSourceURL(Optional<Boolean> hasSourceURL) {
            set("hasSourceURL", hasSourceURL.orElse(null));
            return this;
        }
        /**
         * Whether the sourceURL field value comes from the sourceURL comment.
         * @param hasSourceURL field value; null removes the value
         * @return this model
         */
        public CSSStyleSheetHeader hasSourceURL(Boolean hasSourceURL) {
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
         * @param loadingFailed field value; empty omits the value
         * @return this model
         */
        public CSSStyleSheetHeader loadingFailed(Optional<Boolean> loadingFailed) {
            set("loadingFailed", loadingFailed.orElse(null));
            return this;
        }
        /**
         * If the style sheet was loaded from a network resource, this indicates when the resource failed to load
         * <p><b>Experimental:</b> this part of CDP may change without notice.
         * @param loadingFailed field value; null removes the value
         * @return this model
         */
        public CSSStyleSheetHeader loadingFailed(Boolean loadingFailed) {
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
            return Optional.ofNullable(raw("styleSheetId") == null ? null : new DOM.StyleSheetId((String) raw("styleSheetId")));
        }
        /**
         * Rule selector data.
         * @return the protocol field value
         */
        public CSS.SelectorList selectorList() {
            return java.util.Objects.requireNonNull(CSS.SelectorList.fromMap(java.util.Objects.requireNonNull(CdpObject.objectMap(require("selectorList")))));
        }
        /**
         * Array of selectors from ancestor style rules, sorted by distance from the current rule.
         * <p><b>Experimental:</b> this part of CDP may change without notice.
         * @return the protocol field value, empty when absent
         */
        public Optional<java.util.List<String>> nestingSelectors() {
            return Optional.ofNullable(list(raw("nestingSelectors"), element0 -> (String) element0));
        }
        /**
         * Parent stylesheet&#x27;s origin.
         * @return the protocol field value
         */
        public CSS.StyleSheetOrigin origin() {
            return CSS.StyleSheetOrigin.of((String) require("origin"));
        }
        /**
         * Associated style declaration.
         * @return the protocol field value
         */
        public CSS.CSSStyle style() {
            return java.util.Objects.requireNonNull(CSS.CSSStyle.fromMap(java.util.Objects.requireNonNull(CdpObject.objectMap(require("style")))));
        }
        /**
         * The BackendNodeId of the DOM node that constitutes the origin tree scope of this rule.
         * <p><b>Experimental:</b> this part of CDP may change without notice.
         * @return the protocol field value, empty when absent
         */
        public Optional<DOM.BackendNodeId> originTreeScopeNodeId() {
            return Optional.ofNullable(raw("originTreeScopeNodeId") == null ? null : new DOM.BackendNodeId(((Number) raw("originTreeScopeNodeId")).longValue()));
        }
        /**
         * Media list array (for rules involving media queries). The array enumerates media queries starting with the innermost one, going outwards.
         * @return the protocol field value, empty when absent
         */
        public Optional<java.util.List<CSS.CSSMedia>> media() {
            return Optional.ofNullable(list(raw("media"), element0 -> java.util.Objects.requireNonNull(CSS.CSSMedia.fromMap(java.util.Objects.requireNonNull(CdpObject.objectMap(element0))))));
        }
        /**
         * Container query list array (for rules involving container queries). The array enumerates container queries starting with the innermost one, going outwards.
         * <p><b>Experimental:</b> this part of CDP may change without notice.
         * @return the protocol field value, empty when absent
         */
        public Optional<java.util.List<CSS.CSSContainerQuery>> containerQueries() {
            return Optional.ofNullable(list(raw("containerQueries"), element0 -> java.util.Objects.requireNonNull(CSS.CSSContainerQuery.fromMap(java.util.Objects.requireNonNull(CdpObject.objectMap(element0))))));
        }
        /**
         * &#64;supports CSS at-rule array. The array enumerates &#64;supports at-rules starting with the innermost one, going outwards.
         * <p><b>Experimental:</b> this part of CDP may change without notice.
         * @return the protocol field value, empty when absent
         */
        public Optional<java.util.List<CSS.CSSSupports>> supports() {
            return Optional.ofNullable(list(raw("supports"), element0 -> java.util.Objects.requireNonNull(CSS.CSSSupports.fromMap(java.util.Objects.requireNonNull(CdpObject.objectMap(element0))))));
        }
        /**
         * Cascade layer array. Contains the layer hierarchy that this rule belongs to starting with the innermost layer and going outwards.
         * <p><b>Experimental:</b> this part of CDP may change without notice.
         * @return the protocol field value, empty when absent
         */
        public Optional<java.util.List<CSS.CSSLayer>> layers() {
            return Optional.ofNullable(list(raw("layers"), element0 -> java.util.Objects.requireNonNull(CSS.CSSLayer.fromMap(java.util.Objects.requireNonNull(CdpObject.objectMap(element0))))));
        }
        /**
         * &#64;scope CSS at-rule array. The array enumerates &#64;scope at-rules starting with the innermost one, going outwards.
         * <p><b>Experimental:</b> this part of CDP may change without notice.
         * @return the protocol field value, empty when absent
         */
        public Optional<java.util.List<CSS.CSSScope>> scopes() {
            return Optional.ofNullable(list(raw("scopes"), element0 -> java.util.Objects.requireNonNull(CSS.CSSScope.fromMap(java.util.Objects.requireNonNull(CdpObject.objectMap(element0))))));
        }
        /**
         * The array keeps the types of ancestor CSSRules from the innermost going outwards.
         * <p><b>Experimental:</b> this part of CDP may change without notice.
         * @return the protocol field value, empty when absent
         */
        public Optional<java.util.List<CSS.CSSRuleType>> ruleTypes() {
            return Optional.ofNullable(list(raw("ruleTypes"), element0 -> CSS.CSSRuleType.of((String) element0)));
        }
        /**
         * &#64;starting-style CSS at-rule array. The array enumerates &#64;starting-style at-rules starting with the innermost one, going outwards.
         * <p><b>Experimental:</b> this part of CDP may change without notice.
         * @return the protocol field value, empty when absent
         */
        public Optional<java.util.List<CSS.CSSStartingStyle>> startingStyles() {
            return Optional.ofNullable(list(raw("startingStyles"), element0 -> java.util.Objects.requireNonNull(CSS.CSSStartingStyle.fromMap(java.util.Objects.requireNonNull(CdpObject.objectMap(element0))))));
        }
        /**
         * &#64;navigation CSS at-rule array. The array enumerates &#64;navigation at-rules starting with the innermost one, going outwards.
         * <p><b>Experimental:</b> this part of CDP may change without notice.
         * @return the protocol field value, empty when absent
         */
        public Optional<java.util.List<CSS.CSSNavigation>> navigations() {
            return Optional.ofNullable(list(raw("navigations"), element0 -> java.util.Objects.requireNonNull(CSS.CSSNavigation.fromMap(java.util.Objects.requireNonNull(CdpObject.objectMap(element0))))));
        }
        /**
         * The css style sheet identifier (absent for user agent stylesheet and user-specified stylesheet rules) this rule came from.
         * @param styleSheetId field value; empty omits the value
         * @return this model
         */
        public CSSRule styleSheetId(Optional<DOM.StyleSheetId> styleSheetId) {
            set("styleSheetId", styleSheetId.orElse(null));
            return this;
        }
        /**
         * The css style sheet identifier (absent for user agent stylesheet and user-specified stylesheet rules) this rule came from.
         * @param styleSheetId field value; null removes the value
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
         * @param nestingSelectors field value; empty omits the value
         * @return this model
         */
        public CSSRule nestingSelectors(Optional<java.util.List<String>> nestingSelectors) {
            set("nestingSelectors", nestingSelectors.orElse(null));
            return this;
        }
        /**
         * Array of selectors from ancestor style rules, sorted by distance from the current rule.
         * <p><b>Experimental:</b> this part of CDP may change without notice.
         * @param nestingSelectors field value; null removes the value
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
         * @param originTreeScopeNodeId field value; empty omits the value
         * @return this model
         */
        public CSSRule originTreeScopeNodeId(Optional<DOM.BackendNodeId> originTreeScopeNodeId) {
            set("originTreeScopeNodeId", originTreeScopeNodeId.orElse(null));
            return this;
        }
        /**
         * The BackendNodeId of the DOM node that constitutes the origin tree scope of this rule.
         * <p><b>Experimental:</b> this part of CDP may change without notice.
         * @param originTreeScopeNodeId field value; null removes the value
         * @return this model
         */
        public CSSRule originTreeScopeNodeId(DOM.BackendNodeId originTreeScopeNodeId) {
            set("originTreeScopeNodeId", originTreeScopeNodeId);
            return this;
        }
        /**
         * Media list array (for rules involving media queries). The array enumerates media queries starting with the innermost one, going outwards.
         * @param media field value; empty omits the value
         * @return this model
         */
        public CSSRule media(Optional<java.util.List<CSS.CSSMedia>> media) {
            set("media", media.orElse(null));
            return this;
        }
        /**
         * Media list array (for rules involving media queries). The array enumerates media queries starting with the innermost one, going outwards.
         * @param media field value; null removes the value
         * @return this model
         */
        public CSSRule media(java.util.List<CSS.CSSMedia> media) {
            set("media", media);
            return this;
        }
        /**
         * Container query list array (for rules involving container queries). The array enumerates container queries starting with the innermost one, going outwards.
         * <p><b>Experimental:</b> this part of CDP may change without notice.
         * @param containerQueries field value; empty omits the value
         * @return this model
         */
        public CSSRule containerQueries(Optional<java.util.List<CSS.CSSContainerQuery>> containerQueries) {
            set("containerQueries", containerQueries.orElse(null));
            return this;
        }
        /**
         * Container query list array (for rules involving container queries). The array enumerates container queries starting with the innermost one, going outwards.
         * <p><b>Experimental:</b> this part of CDP may change without notice.
         * @param containerQueries field value; null removes the value
         * @return this model
         */
        public CSSRule containerQueries(java.util.List<CSS.CSSContainerQuery> containerQueries) {
            set("containerQueries", containerQueries);
            return this;
        }
        /**
         * &#64;supports CSS at-rule array. The array enumerates &#64;supports at-rules starting with the innermost one, going outwards.
         * <p><b>Experimental:</b> this part of CDP may change without notice.
         * @param supports field value; empty omits the value
         * @return this model
         */
        public CSSRule supports(Optional<java.util.List<CSS.CSSSupports>> supports) {
            set("supports", supports.orElse(null));
            return this;
        }
        /**
         * &#64;supports CSS at-rule array. The array enumerates &#64;supports at-rules starting with the innermost one, going outwards.
         * <p><b>Experimental:</b> this part of CDP may change without notice.
         * @param supports field value; null removes the value
         * @return this model
         */
        public CSSRule supports(java.util.List<CSS.CSSSupports> supports) {
            set("supports", supports);
            return this;
        }
        /**
         * Cascade layer array. Contains the layer hierarchy that this rule belongs to starting with the innermost layer and going outwards.
         * <p><b>Experimental:</b> this part of CDP may change without notice.
         * @param layers field value; empty omits the value
         * @return this model
         */
        public CSSRule layers(Optional<java.util.List<CSS.CSSLayer>> layers) {
            set("layers", layers.orElse(null));
            return this;
        }
        /**
         * Cascade layer array. Contains the layer hierarchy that this rule belongs to starting with the innermost layer and going outwards.
         * <p><b>Experimental:</b> this part of CDP may change without notice.
         * @param layers field value; null removes the value
         * @return this model
         */
        public CSSRule layers(java.util.List<CSS.CSSLayer> layers) {
            set("layers", layers);
            return this;
        }
        /**
         * &#64;scope CSS at-rule array. The array enumerates &#64;scope at-rules starting with the innermost one, going outwards.
         * <p><b>Experimental:</b> this part of CDP may change without notice.
         * @param scopes field value; empty omits the value
         * @return this model
         */
        public CSSRule scopes(Optional<java.util.List<CSS.CSSScope>> scopes) {
            set("scopes", scopes.orElse(null));
            return this;
        }
        /**
         * &#64;scope CSS at-rule array. The array enumerates &#64;scope at-rules starting with the innermost one, going outwards.
         * <p><b>Experimental:</b> this part of CDP may change without notice.
         * @param scopes field value; null removes the value
         * @return this model
         */
        public CSSRule scopes(java.util.List<CSS.CSSScope> scopes) {
            set("scopes", scopes);
            return this;
        }
        /**
         * The array keeps the types of ancestor CSSRules from the innermost going outwards.
         * <p><b>Experimental:</b> this part of CDP may change without notice.
         * @param ruleTypes field value; empty omits the value
         * @return this model
         */
        public CSSRule ruleTypes(Optional<java.util.List<CSS.CSSRuleType>> ruleTypes) {
            set("ruleTypes", ruleTypes.orElse(null));
            return this;
        }
        /**
         * The array keeps the types of ancestor CSSRules from the innermost going outwards.
         * <p><b>Experimental:</b> this part of CDP may change without notice.
         * @param ruleTypes field value; null removes the value
         * @return this model
         */
        public CSSRule ruleTypes(java.util.List<CSS.CSSRuleType> ruleTypes) {
            set("ruleTypes", ruleTypes);
            return this;
        }
        /**
         * &#64;starting-style CSS at-rule array. The array enumerates &#64;starting-style at-rules starting with the innermost one, going outwards.
         * <p><b>Experimental:</b> this part of CDP may change without notice.
         * @param startingStyles field value; empty omits the value
         * @return this model
         */
        public CSSRule startingStyles(Optional<java.util.List<CSS.CSSStartingStyle>> startingStyles) {
            set("startingStyles", startingStyles.orElse(null));
            return this;
        }
        /**
         * &#64;starting-style CSS at-rule array. The array enumerates &#64;starting-style at-rules starting with the innermost one, going outwards.
         * <p><b>Experimental:</b> this part of CDP may change without notice.
         * @param startingStyles field value; null removes the value
         * @return this model
         */
        public CSSRule startingStyles(java.util.List<CSS.CSSStartingStyle> startingStyles) {
            set("startingStyles", startingStyles);
            return this;
        }
        /**
         * &#64;navigation CSS at-rule array. The array enumerates &#64;navigation at-rules starting with the innermost one, going outwards.
         * <p><b>Experimental:</b> this part of CDP may change without notice.
         * @param navigations field value; empty omits the value
         * @return this model
         */
        public CSSRule navigations(Optional<java.util.List<CSS.CSSNavigation>> navigations) {
            set("navigations", navigations.orElse(null));
            return this;
        }
        /**
         * &#64;navigation CSS at-rule array. The array enumerates &#64;navigation at-rules starting with the innermost one, going outwards.
         * <p><b>Experimental:</b> this part of CDP may change without notice.
         * @param navigations field value; null removes the value
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
        @Nonnull public String value() { return value; }
        public static CSSRuleType of(@Nonnull String value) {
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
            return new DOM.StyleSheetId((String) require("styleSheetId"));
        }
        /**
         * Offset of the start of the rule (including selector) from the beginning of the stylesheet.
         * @return the protocol field value
         */
        public double startOffset() {
            return ((Number) require("startOffset")).doubleValue();
        }
        /**
         * Offset of the end of the rule body from the beginning of the stylesheet.
         * @return the protocol field value
         */
        public double endOffset() {
            return ((Number) require("endOffset")).doubleValue();
        }
        /**
         * Indicates whether the rule was actually used by some element in the page.
         * @return the protocol field value
         */
        public boolean used() {
            return (Boolean) require("used");
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
            return ((Number) require("startLine")).longValue();
        }
        /**
         * Start column of range (inclusive).
         * @return the protocol field value
         */
        public long startColumn() {
            return ((Number) require("startColumn")).longValue();
        }
        /**
         * End line of range
         * @return the protocol field value
         */
        public long endLine() {
            return ((Number) require("endLine")).longValue();
        }
        /**
         * End column of range (exclusive).
         * @return the protocol field value
         */
        public long endColumn() {
            return ((Number) require("endColumn")).longValue();
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
            return (String) require("name");
        }
        /**
         * Shorthand value.
         * @return the protocol field value
         */
        public String value() {
            return (String) require("value");
        }
        /**
         * Whether the property has &quot;!important&quot; annotation (implies {@code false} if absent).
         * @return the protocol field value, empty when absent
         */
        public Optional<Boolean> important() {
            return Optional.ofNullable((Boolean) raw("important"));
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
         * @param important field value; empty omits the value
         * @return this model
         */
        public ShorthandEntry important(Optional<Boolean> important) {
            set("important", important.orElse(null));
            return this;
        }
        /**
         * Whether the property has &quot;!important&quot; annotation (implies {@code false} if absent).
         * @param important field value; null removes the value
         * @return this model
         */
        public ShorthandEntry important(Boolean important) {
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
            return (Boolean) require("isAppearanceBase");
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
            return Optional.ofNullable(raw("styleSheetId") == null ? null : new DOM.StyleSheetId((String) raw("styleSheetId")));
        }
        /**
         * CSS properties in the style.
         * @return the protocol field value
         */
        public java.util.List<CSS.CSSProperty> cssProperties() {
            return CdpObject.requireList(require("cssProperties"), element0 -> java.util.Objects.requireNonNull(CSS.CSSProperty.fromMap(java.util.Objects.requireNonNull(CdpObject.objectMap(element0)))));
        }
        /**
         * Computed values for all shorthands found in the style.
         * @return the protocol field value
         */
        public java.util.List<CSS.ShorthandEntry> shorthandEntries() {
            return CdpObject.requireList(require("shorthandEntries"), element0 -> java.util.Objects.requireNonNull(CSS.ShorthandEntry.fromMap(java.util.Objects.requireNonNull(CdpObject.objectMap(element0)))));
        }
        /**
         * Style declaration text (if available).
         * @return the protocol field value, empty when absent
         */
        public Optional<String> cssText() {
            return Optional.ofNullable((String) raw("cssText"));
        }
        /**
         * Style declaration range in the enclosing stylesheet (if available).
         * @return the protocol field value, empty when absent
         */
        public Optional<CSS.SourceRange> range() {
            return Optional.ofNullable(raw("range") == null ? null : CSS.SourceRange.fromMap(java.util.Objects.requireNonNull(objectMap(raw("range")))));
        }
        /**
         * The css style sheet identifier (absent for user agent stylesheet and user-specified stylesheet rules) this rule came from.
         * @param styleSheetId field value; empty omits the value
         * @return this model
         */
        public CSSStyle styleSheetId(Optional<DOM.StyleSheetId> styleSheetId) {
            set("styleSheetId", styleSheetId.orElse(null));
            return this;
        }
        /**
         * The css style sheet identifier (absent for user agent stylesheet and user-specified stylesheet rules) this rule came from.
         * @param styleSheetId field value; null removes the value
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
         * @param cssText field value; empty omits the value
         * @return this model
         */
        public CSSStyle cssText(Optional<String> cssText) {
            set("cssText", cssText.orElse(null));
            return this;
        }
        /**
         * Style declaration text (if available).
         * @param cssText field value; null removes the value
         * @return this model
         */
        public CSSStyle cssText(String cssText) {
            set("cssText", cssText);
            return this;
        }
        /**
         * Style declaration range in the enclosing stylesheet (if available).
         * @param range field value; empty omits the value
         * @return this model
         */
        public CSSStyle range(Optional<CSS.SourceRange> range) {
            set("range", range.orElse(null));
            return this;
        }
        /**
         * Style declaration range in the enclosing stylesheet (if available).
         * @param range field value; null removes the value
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
            return (String) require("name");
        }
        /**
         * The property value.
         * @return the protocol field value
         */
        public String value() {
            return (String) require("value");
        }
        /**
         * Whether the property has &quot;!important&quot; annotation (implies {@code false} if absent).
         * @return the protocol field value, empty when absent
         */
        public Optional<Boolean> important() {
            return Optional.ofNullable((Boolean) raw("important"));
        }
        /**
         * Whether the property is implicit (implies {@code false} if absent).
         * @return the protocol field value, empty when absent
         */
        public Optional<Boolean> implicit() {
            return Optional.ofNullable((Boolean) raw("implicit"));
        }
        /**
         * The full property text as specified in the style.
         * @return the protocol field value, empty when absent
         */
        public Optional<String> text() {
            return Optional.ofNullable((String) raw("text"));
        }
        /**
         * Whether the property is understood by the browser (implies {@code true} if absent).
         * @return the protocol field value, empty when absent
         */
        public Optional<Boolean> parsedOk() {
            return Optional.ofNullable((Boolean) raw("parsedOk"));
        }
        /**
         * Whether the property is disabled by the user (present for source-based properties only).
         * @return the protocol field value, empty when absent
         */
        public Optional<Boolean> disabled() {
            return Optional.ofNullable((Boolean) raw("disabled"));
        }
        /**
         * The entire property range in the enclosing style declaration (if available).
         * @return the protocol field value, empty when absent
         */
        public Optional<CSS.SourceRange> range() {
            return Optional.ofNullable(raw("range") == null ? null : CSS.SourceRange.fromMap(java.util.Objects.requireNonNull(objectMap(raw("range")))));
        }
        /**
         * Parsed longhand components of this property if it is a shorthand. This field will be empty if the given property is not a shorthand.
         * <p><b>Experimental:</b> this part of CDP may change without notice.
         * @return the protocol field value, empty when absent
         */
        public Optional<java.util.List<CSS.CSSProperty>> longhandProperties() {
            return Optional.ofNullable(list(raw("longhandProperties"), element0 -> java.util.Objects.requireNonNull(CSS.CSSProperty.fromMap(java.util.Objects.requireNonNull(CdpObject.objectMap(element0))))));
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
         * @param important field value; empty omits the value
         * @return this model
         */
        public CSSProperty important(Optional<Boolean> important) {
            set("important", important.orElse(null));
            return this;
        }
        /**
         * Whether the property has &quot;!important&quot; annotation (implies {@code false} if absent).
         * @param important field value; null removes the value
         * @return this model
         */
        public CSSProperty important(Boolean important) {
            set("important", important);
            return this;
        }
        /**
         * Whether the property is implicit (implies {@code false} if absent).
         * @param implicit field value; empty omits the value
         * @return this model
         */
        public CSSProperty implicit(Optional<Boolean> implicit) {
            set("implicit", implicit.orElse(null));
            return this;
        }
        /**
         * Whether the property is implicit (implies {@code false} if absent).
         * @param implicit field value; null removes the value
         * @return this model
         */
        public CSSProperty implicit(Boolean implicit) {
            set("implicit", implicit);
            return this;
        }
        /**
         * The full property text as specified in the style.
         * @param text field value; empty omits the value
         * @return this model
         */
        public CSSProperty text(Optional<String> text) {
            set("text", text.orElse(null));
            return this;
        }
        /**
         * The full property text as specified in the style.
         * @param text field value; null removes the value
         * @return this model
         */
        public CSSProperty text(String text) {
            set("text", text);
            return this;
        }
        /**
         * Whether the property is understood by the browser (implies {@code true} if absent).
         * @param parsedOk field value; empty omits the value
         * @return this model
         */
        public CSSProperty parsedOk(Optional<Boolean> parsedOk) {
            set("parsedOk", parsedOk.orElse(null));
            return this;
        }
        /**
         * Whether the property is understood by the browser (implies {@code true} if absent).
         * @param parsedOk field value; null removes the value
         * @return this model
         */
        public CSSProperty parsedOk(Boolean parsedOk) {
            set("parsedOk", parsedOk);
            return this;
        }
        /**
         * Whether the property is disabled by the user (present for source-based properties only).
         * @param disabled field value; empty omits the value
         * @return this model
         */
        public CSSProperty disabled(Optional<Boolean> disabled) {
            set("disabled", disabled.orElse(null));
            return this;
        }
        /**
         * Whether the property is disabled by the user (present for source-based properties only).
         * @param disabled field value; null removes the value
         * @return this model
         */
        public CSSProperty disabled(Boolean disabled) {
            set("disabled", disabled);
            return this;
        }
        /**
         * The entire property range in the enclosing style declaration (if available).
         * @param range field value; empty omits the value
         * @return this model
         */
        public CSSProperty range(Optional<CSS.SourceRange> range) {
            set("range", range.orElse(null));
            return this;
        }
        /**
         * The entire property range in the enclosing style declaration (if available).
         * @param range field value; null removes the value
         * @return this model
         */
        public CSSProperty range(CSS.SourceRange range) {
            set("range", range);
            return this;
        }
        /**
         * Parsed longhand components of this property if it is a shorthand. This field will be empty if the given property is not a shorthand.
         * <p><b>Experimental:</b> this part of CDP may change without notice.
         * @param longhandProperties field value; empty omits the value
         * @return this model
         */
        public CSSProperty longhandProperties(Optional<java.util.List<CSS.CSSProperty>> longhandProperties) {
            set("longhandProperties", longhandProperties.orElse(null));
            return this;
        }
        /**
         * Parsed longhand components of this property if it is a shorthand. This field will be empty if the given property is not a shorthand.
         * <p><b>Experimental:</b> this part of CDP may change without notice.
         * @param longhandProperties field value; null removes the value
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
            @Nonnull public String value() { return value; }
            public static SourceValues of(@Nonnull String value) {
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
            return (String) require("text");
        }
        /**
         * Source of the media query: &quot;mediaRule&quot; if specified by a &#64;media rule, &quot;importRule&quot; if specified by an &#64;import rule, &quot;linkedSheet&quot; if specified by a &quot;media&quot; attribute in a linked stylesheet&#x27;s LINK tag, &quot;inlineSheet&quot; if specified by a &quot;media&quot; attribute in an inline stylesheet&#x27;s STYLE tag.
         * @return the protocol field value
         */
        public CSSMedia.SourceValues source() {
            return CSSMedia.SourceValues.of((String) require("source"));
        }
        /**
         * URL of the document containing the media query description.
         * @return the protocol field value, empty when absent
         */
        public Optional<String> sourceURL() {
            return Optional.ofNullable((String) raw("sourceURL"));
        }
        /**
         * The associated rule (&#64;media or &#64;import) header range in the enclosing stylesheet (if available).
         * @return the protocol field value, empty when absent
         */
        public Optional<CSS.SourceRange> range() {
            return Optional.ofNullable(raw("range") == null ? null : CSS.SourceRange.fromMap(java.util.Objects.requireNonNull(objectMap(raw("range")))));
        }
        /**
         * Identifier of the stylesheet containing this object (if exists).
         * @return the protocol field value, empty when absent
         */
        public Optional<DOM.StyleSheetId> styleSheetId() {
            return Optional.ofNullable(raw("styleSheetId") == null ? null : new DOM.StyleSheetId((String) raw("styleSheetId")));
        }
        /**
         * Array of media queries.
         * @return the protocol field value, empty when absent
         */
        public Optional<java.util.List<CSS.MediaQuery>> mediaList() {
            return Optional.ofNullable(list(raw("mediaList"), element0 -> java.util.Objects.requireNonNull(CSS.MediaQuery.fromMap(java.util.Objects.requireNonNull(CdpObject.objectMap(element0))))));
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
         * @param sourceURL field value; empty omits the value
         * @return this model
         */
        public CSSMedia sourceURL(Optional<String> sourceURL) {
            set("sourceURL", sourceURL.orElse(null));
            return this;
        }
        /**
         * URL of the document containing the media query description.
         * @param sourceURL field value; null removes the value
         * @return this model
         */
        public CSSMedia sourceURL(String sourceURL) {
            set("sourceURL", sourceURL);
            return this;
        }
        /**
         * The associated rule (&#64;media or &#64;import) header range in the enclosing stylesheet (if available).
         * @param range field value; empty omits the value
         * @return this model
         */
        public CSSMedia range(Optional<CSS.SourceRange> range) {
            set("range", range.orElse(null));
            return this;
        }
        /**
         * The associated rule (&#64;media or &#64;import) header range in the enclosing stylesheet (if available).
         * @param range field value; null removes the value
         * @return this model
         */
        public CSSMedia range(CSS.SourceRange range) {
            set("range", range);
            return this;
        }
        /**
         * Identifier of the stylesheet containing this object (if exists).
         * @param styleSheetId field value; empty omits the value
         * @return this model
         */
        public CSSMedia styleSheetId(Optional<DOM.StyleSheetId> styleSheetId) {
            set("styleSheetId", styleSheetId.orElse(null));
            return this;
        }
        /**
         * Identifier of the stylesheet containing this object (if exists).
         * @param styleSheetId field value; null removes the value
         * @return this model
         */
        public CSSMedia styleSheetId(DOM.StyleSheetId styleSheetId) {
            set("styleSheetId", styleSheetId);
            return this;
        }
        /**
         * Array of media queries.
         * @param mediaList field value; empty omits the value
         * @return this model
         */
        public CSSMedia mediaList(Optional<java.util.List<CSS.MediaQuery>> mediaList) {
            set("mediaList", mediaList.orElse(null));
            return this;
        }
        /**
         * Array of media queries.
         * @param mediaList field value; null removes the value
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
            return CdpObject.requireList(require("expressions"), element0 -> java.util.Objects.requireNonNull(CSS.MediaQueryExpression.fromMap(java.util.Objects.requireNonNull(CdpObject.objectMap(element0)))));
        }
        /**
         * Whether the media query condition is satisfied.
         * @return the protocol field value
         */
        public boolean active() {
            return (Boolean) require("active");
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
            return ((Number) require("value")).doubleValue();
        }
        /**
         * Media query expression units.
         * @return the protocol field value
         */
        public String unit() {
            return (String) require("unit");
        }
        /**
         * Media query expression feature.
         * @return the protocol field value
         */
        public String feature() {
            return (String) require("feature");
        }
        /**
         * The associated range of the value text in the enclosing stylesheet (if available).
         * @return the protocol field value, empty when absent
         */
        public Optional<CSS.SourceRange> valueRange() {
            return Optional.ofNullable(raw("valueRange") == null ? null : CSS.SourceRange.fromMap(java.util.Objects.requireNonNull(objectMap(raw("valueRange")))));
        }
        /**
         * Computed length of media query expression (if applicable).
         * @return the protocol field value, empty when absent
         */
        public OptionalDouble computedLength() {
            Double value = CdpObject.numberAsDouble(raw("computedLength"));
            return value == null ? OptionalDouble.empty() : OptionalDouble.of(value);
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
         * @param valueRange field value; empty omits the value
         * @return this model
         */
        public MediaQueryExpression valueRange(Optional<CSS.SourceRange> valueRange) {
            set("valueRange", valueRange.orElse(null));
            return this;
        }
        /**
         * The associated range of the value text in the enclosing stylesheet (if available).
         * @param valueRange field value; null removes the value
         * @return this model
         */
        public MediaQueryExpression valueRange(CSS.SourceRange valueRange) {
            set("valueRange", valueRange);
            return this;
        }
        /**
         * Computed length of media query expression (if applicable).
         * @param computedLength field value; empty omits the value
         * @return this model
         */
        public MediaQueryExpression computedLength(OptionalDouble computedLength) {
            set("computedLength", computedLength.isPresent() ? computedLength.getAsDouble() : null);
            return this;
        }
        /**
         * Computed length of media query expression (if applicable).
         * @param computedLength field value; null removes the value
         * @return this model
         */
        public MediaQueryExpression computedLength(Double computedLength) {
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
            return (String) require("text");
        }
        /**
         * The associated rule header range in the enclosing stylesheet (if available).
         * @return the protocol field value, empty when absent
         */
        public Optional<CSS.SourceRange> range() {
            return Optional.ofNullable(raw("range") == null ? null : CSS.SourceRange.fromMap(java.util.Objects.requireNonNull(objectMap(raw("range")))));
        }
        /**
         * Identifier of the stylesheet containing this object (if exists).
         * @return the protocol field value, empty when absent
         */
        public Optional<DOM.StyleSheetId> styleSheetId() {
            return Optional.ofNullable(raw("styleSheetId") == null ? null : new DOM.StyleSheetId((String) raw("styleSheetId")));
        }
        /**
         * Optional name for the container.
         * @return the protocol field value, empty when absent
         */
        public Optional<String> name() {
            return Optional.ofNullable((String) raw("name"));
        }
        /**
         * Optional physical axes queried for the container.
         * @return the protocol field value, empty when absent
         */
        public Optional<DOM.PhysicalAxes> physicalAxes() {
            return Optional.ofNullable(raw("physicalAxes") == null ? null : DOM.PhysicalAxes.of((String) raw("physicalAxes")));
        }
        /**
         * Optional logical axes queried for the container.
         * @return the protocol field value, empty when absent
         */
        public Optional<DOM.LogicalAxes> logicalAxes() {
            return Optional.ofNullable(raw("logicalAxes") == null ? null : DOM.LogicalAxes.of((String) raw("logicalAxes")));
        }
        /**
         * true if the query contains scroll-state() queries.
         * @return the protocol field value, empty when absent
         */
        public Optional<Boolean> queriesScrollState() {
            return Optional.ofNullable((Boolean) raw("queriesScrollState"));
        }
        /**
         * true if the query contains anchored() queries.
         * @return the protocol field value, empty when absent
         */
        public Optional<Boolean> queriesAnchored() {
            return Optional.ofNullable((Boolean) raw("queriesAnchored"));
        }
        /**
         * CSSContainerRule.conditionText
         * @return the protocol field value
         */
        public String conditionText() {
            return (String) require("conditionText");
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
         * @param range field value; empty omits the value
         * @return this model
         */
        public CSSContainerQuery range(Optional<CSS.SourceRange> range) {
            set("range", range.orElse(null));
            return this;
        }
        /**
         * The associated rule header range in the enclosing stylesheet (if available).
         * @param range field value; null removes the value
         * @return this model
         */
        public CSSContainerQuery range(CSS.SourceRange range) {
            set("range", range);
            return this;
        }
        /**
         * Identifier of the stylesheet containing this object (if exists).
         * @param styleSheetId field value; empty omits the value
         * @return this model
         */
        public CSSContainerQuery styleSheetId(Optional<DOM.StyleSheetId> styleSheetId) {
            set("styleSheetId", styleSheetId.orElse(null));
            return this;
        }
        /**
         * Identifier of the stylesheet containing this object (if exists).
         * @param styleSheetId field value; null removes the value
         * @return this model
         */
        public CSSContainerQuery styleSheetId(DOM.StyleSheetId styleSheetId) {
            set("styleSheetId", styleSheetId);
            return this;
        }
        /**
         * Optional name for the container.
         * @param name field value; empty omits the value
         * @return this model
         */
        public CSSContainerQuery name(Optional<String> name) {
            set("name", name.orElse(null));
            return this;
        }
        /**
         * Optional name for the container.
         * @param name field value; null removes the value
         * @return this model
         */
        public CSSContainerQuery name(String name) {
            set("name", name);
            return this;
        }
        /**
         * Optional physical axes queried for the container.
         * @param physicalAxes field value; empty omits the value
         * @return this model
         */
        public CSSContainerQuery physicalAxes(Optional<DOM.PhysicalAxes> physicalAxes) {
            set("physicalAxes", physicalAxes.orElse(null));
            return this;
        }
        /**
         * Optional physical axes queried for the container.
         * @param physicalAxes field value; null removes the value
         * @return this model
         */
        public CSSContainerQuery physicalAxes(DOM.PhysicalAxes physicalAxes) {
            set("physicalAxes", physicalAxes);
            return this;
        }
        /**
         * Optional logical axes queried for the container.
         * @param logicalAxes field value; empty omits the value
         * @return this model
         */
        public CSSContainerQuery logicalAxes(Optional<DOM.LogicalAxes> logicalAxes) {
            set("logicalAxes", logicalAxes.orElse(null));
            return this;
        }
        /**
         * Optional logical axes queried for the container.
         * @param logicalAxes field value; null removes the value
         * @return this model
         */
        public CSSContainerQuery logicalAxes(DOM.LogicalAxes logicalAxes) {
            set("logicalAxes", logicalAxes);
            return this;
        }
        /**
         * true if the query contains scroll-state() queries.
         * @param queriesScrollState field value; empty omits the value
         * @return this model
         */
        public CSSContainerQuery queriesScrollState(Optional<Boolean> queriesScrollState) {
            set("queriesScrollState", queriesScrollState.orElse(null));
            return this;
        }
        /**
         * true if the query contains scroll-state() queries.
         * @param queriesScrollState field value; null removes the value
         * @return this model
         */
        public CSSContainerQuery queriesScrollState(Boolean queriesScrollState) {
            set("queriesScrollState", queriesScrollState);
            return this;
        }
        /**
         * true if the query contains anchored() queries.
         * @param queriesAnchored field value; empty omits the value
         * @return this model
         */
        public CSSContainerQuery queriesAnchored(Optional<Boolean> queriesAnchored) {
            set("queriesAnchored", queriesAnchored.orElse(null));
            return this;
        }
        /**
         * true if the query contains anchored() queries.
         * @param queriesAnchored field value; null removes the value
         * @return this model
         */
        public CSSContainerQuery queriesAnchored(Boolean queriesAnchored) {
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
            return (String) require("text");
        }
        /**
         * Whether the supports condition is satisfied.
         * @return the protocol field value
         */
        public boolean active() {
            return (Boolean) require("active");
        }
        /**
         * The associated rule header range in the enclosing stylesheet (if available).
         * @return the protocol field value, empty when absent
         */
        public Optional<CSS.SourceRange> range() {
            return Optional.ofNullable(raw("range") == null ? null : CSS.SourceRange.fromMap(java.util.Objects.requireNonNull(objectMap(raw("range")))));
        }
        /**
         * Identifier of the stylesheet containing this object (if exists).
         * @return the protocol field value, empty when absent
         */
        public Optional<DOM.StyleSheetId> styleSheetId() {
            return Optional.ofNullable(raw("styleSheetId") == null ? null : new DOM.StyleSheetId((String) raw("styleSheetId")));
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
         * @param range field value; empty omits the value
         * @return this model
         */
        public CSSSupports range(Optional<CSS.SourceRange> range) {
            set("range", range.orElse(null));
            return this;
        }
        /**
         * The associated rule header range in the enclosing stylesheet (if available).
         * @param range field value; null removes the value
         * @return this model
         */
        public CSSSupports range(CSS.SourceRange range) {
            set("range", range);
            return this;
        }
        /**
         * Identifier of the stylesheet containing this object (if exists).
         * @param styleSheetId field value; empty omits the value
         * @return this model
         */
        public CSSSupports styleSheetId(Optional<DOM.StyleSheetId> styleSheetId) {
            set("styleSheetId", styleSheetId.orElse(null));
            return this;
        }
        /**
         * Identifier of the stylesheet containing this object (if exists).
         * @param styleSheetId field value; null removes the value
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
            return (String) require("text");
        }
        /**
         * Whether the navigation condition is satisfied.
         * @return the protocol field value, empty when absent
         */
        public Optional<Boolean> active() {
            return Optional.ofNullable((Boolean) raw("active"));
        }
        /**
         * The associated rule header range in the enclosing stylesheet (if available).
         * @return the protocol field value, empty when absent
         */
        public Optional<CSS.SourceRange> range() {
            return Optional.ofNullable(raw("range") == null ? null : CSS.SourceRange.fromMap(java.util.Objects.requireNonNull(objectMap(raw("range")))));
        }
        /**
         * Identifier of the stylesheet containing this object (if exists).
         * @return the protocol field value, empty when absent
         */
        public Optional<DOM.StyleSheetId> styleSheetId() {
            return Optional.ofNullable(raw("styleSheetId") == null ? null : new DOM.StyleSheetId((String) raw("styleSheetId")));
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
         * @param active field value; empty omits the value
         * @return this model
         */
        public CSSNavigation active(Optional<Boolean> active) {
            set("active", active.orElse(null));
            return this;
        }
        /**
         * Whether the navigation condition is satisfied.
         * @param active field value; null removes the value
         * @return this model
         */
        public CSSNavigation active(Boolean active) {
            set("active", active);
            return this;
        }
        /**
         * The associated rule header range in the enclosing stylesheet (if available).
         * @param range field value; empty omits the value
         * @return this model
         */
        public CSSNavigation range(Optional<CSS.SourceRange> range) {
            set("range", range.orElse(null));
            return this;
        }
        /**
         * The associated rule header range in the enclosing stylesheet (if available).
         * @param range field value; null removes the value
         * @return this model
         */
        public CSSNavigation range(CSS.SourceRange range) {
            set("range", range);
            return this;
        }
        /**
         * Identifier of the stylesheet containing this object (if exists).
         * @param styleSheetId field value; empty omits the value
         * @return this model
         */
        public CSSNavigation styleSheetId(Optional<DOM.StyleSheetId> styleSheetId) {
            set("styleSheetId", styleSheetId.orElse(null));
            return this;
        }
        /**
         * Identifier of the stylesheet containing this object (if exists).
         * @param styleSheetId field value; null removes the value
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
            return (String) require("text");
        }
        /**
         * The associated rule header range in the enclosing stylesheet (if available).
         * @return the protocol field value, empty when absent
         */
        public Optional<CSS.SourceRange> range() {
            return Optional.ofNullable(raw("range") == null ? null : CSS.SourceRange.fromMap(java.util.Objects.requireNonNull(objectMap(raw("range")))));
        }
        /**
         * Identifier of the stylesheet containing this object (if exists).
         * @return the protocol field value, empty when absent
         */
        public Optional<DOM.StyleSheetId> styleSheetId() {
            return Optional.ofNullable(raw("styleSheetId") == null ? null : new DOM.StyleSheetId((String) raw("styleSheetId")));
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
         * @param range field value; empty omits the value
         * @return this model
         */
        public CSSScope range(Optional<CSS.SourceRange> range) {
            set("range", range.orElse(null));
            return this;
        }
        /**
         * The associated rule header range in the enclosing stylesheet (if available).
         * @param range field value; null removes the value
         * @return this model
         */
        public CSSScope range(CSS.SourceRange range) {
            set("range", range);
            return this;
        }
        /**
         * Identifier of the stylesheet containing this object (if exists).
         * @param styleSheetId field value; empty omits the value
         * @return this model
         */
        public CSSScope styleSheetId(Optional<DOM.StyleSheetId> styleSheetId) {
            set("styleSheetId", styleSheetId.orElse(null));
            return this;
        }
        /**
         * Identifier of the stylesheet containing this object (if exists).
         * @param styleSheetId field value; null removes the value
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
            return (String) require("text");
        }
        /**
         * The associated rule header range in the enclosing stylesheet (if available).
         * @return the protocol field value, empty when absent
         */
        public Optional<CSS.SourceRange> range() {
            return Optional.ofNullable(raw("range") == null ? null : CSS.SourceRange.fromMap(java.util.Objects.requireNonNull(objectMap(raw("range")))));
        }
        /**
         * Identifier of the stylesheet containing this object (if exists).
         * @return the protocol field value, empty when absent
         */
        public Optional<DOM.StyleSheetId> styleSheetId() {
            return Optional.ofNullable(raw("styleSheetId") == null ? null : new DOM.StyleSheetId((String) raw("styleSheetId")));
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
         * @param range field value; empty omits the value
         * @return this model
         */
        public CSSLayer range(Optional<CSS.SourceRange> range) {
            set("range", range.orElse(null));
            return this;
        }
        /**
         * The associated rule header range in the enclosing stylesheet (if available).
         * @param range field value; null removes the value
         * @return this model
         */
        public CSSLayer range(CSS.SourceRange range) {
            set("range", range);
            return this;
        }
        /**
         * Identifier of the stylesheet containing this object (if exists).
         * @param styleSheetId field value; empty omits the value
         * @return this model
         */
        public CSSLayer styleSheetId(Optional<DOM.StyleSheetId> styleSheetId) {
            set("styleSheetId", styleSheetId.orElse(null));
            return this;
        }
        /**
         * Identifier of the stylesheet containing this object (if exists).
         * @param styleSheetId field value; null removes the value
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
            return Optional.ofNullable(raw("range") == null ? null : CSS.SourceRange.fromMap(java.util.Objects.requireNonNull(objectMap(raw("range")))));
        }
        /**
         * Identifier of the stylesheet containing this object (if exists).
         * @return the protocol field value, empty when absent
         */
        public Optional<DOM.StyleSheetId> styleSheetId() {
            return Optional.ofNullable(raw("styleSheetId") == null ? null : new DOM.StyleSheetId((String) raw("styleSheetId")));
        }
        /**
         * The associated rule header range in the enclosing stylesheet (if available).
         * @param range field value; empty omits the value
         * @return this model
         */
        public CSSStartingStyle range(Optional<CSS.SourceRange> range) {
            set("range", range.orElse(null));
            return this;
        }
        /**
         * The associated rule header range in the enclosing stylesheet (if available).
         * @param range field value; null removes the value
         * @return this model
         */
        public CSSStartingStyle range(CSS.SourceRange range) {
            set("range", range);
            return this;
        }
        /**
         * Identifier of the stylesheet containing this object (if exists).
         * @param styleSheetId field value; empty omits the value
         * @return this model
         */
        public CSSStartingStyle styleSheetId(Optional<DOM.StyleSheetId> styleSheetId) {
            set("styleSheetId", styleSheetId.orElse(null));
            return this;
        }
        /**
         * Identifier of the stylesheet containing this object (if exists).
         * @param styleSheetId field value; null removes the value
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
            return (String) require("name");
        }
        /**
         * Direct sub-layers
         * @return the protocol field value, empty when absent
         */
        public Optional<java.util.List<CSS.CSSLayerData>> subLayers() {
            return Optional.ofNullable(list(raw("subLayers"), element0 -> java.util.Objects.requireNonNull(CSS.CSSLayerData.fromMap(java.util.Objects.requireNonNull(CdpObject.objectMap(element0))))));
        }
        /**
         * Layer order. The order determines the order of the layer in the cascade order. A higher number has higher priority in the cascade order.
         * @return the protocol field value
         */
        public double order() {
            return ((Number) require("order")).doubleValue();
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
         * @param subLayers field value; empty omits the value
         * @return this model
         */
        public CSSLayerData subLayers(Optional<java.util.List<CSS.CSSLayerData>> subLayers) {
            set("subLayers", subLayers.orElse(null));
            return this;
        }
        /**
         * Direct sub-layers
         * @param subLayers field value; null removes the value
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
            return (String) require("familyName");
        }
        /**
         * Font&#x27;s PostScript name reported by platform.
         * @return the protocol field value
         */
        public String postScriptName() {
            return (String) require("postScriptName");
        }
        /**
         * Indicates if the font was downloaded or resolved locally.
         * @return the protocol field value
         */
        public boolean isCustomFont() {
            return (Boolean) require("isCustomFont");
        }
        /**
         * Amount of glyphs that were rendered with this font.
         * @return the protocol field value
         */
        public double glyphCount() {
            return ((Number) require("glyphCount")).doubleValue();
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
            return (String) require("tag");
        }
        /**
         * Human-readable variation name in the default language (normally, &quot;en&quot;).
         * @return the protocol field value
         */
        public String name() {
            return (String) require("name");
        }
        /**
         * The minimum value (inclusive) the font supports for this tag.
         * @return the protocol field value
         */
        public double minValue() {
            return ((Number) require("minValue")).doubleValue();
        }
        /**
         * The maximum value (inclusive) the font supports for this tag.
         * @return the protocol field value
         */
        public double maxValue() {
            return ((Number) require("maxValue")).doubleValue();
        }
        /**
         * The default value.
         * @return the protocol field value
         */
        public double defaultValue() {
            return ((Number) require("defaultValue")).doubleValue();
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
            return (String) require("fontFamily");
        }
        /**
         * The font-style.
         * @return the protocol field value
         */
        public String fontStyle() {
            return (String) require("fontStyle");
        }
        /**
         * The font-variant.
         * @return the protocol field value
         */
        public String fontVariant() {
            return (String) require("fontVariant");
        }
        /**
         * The font-weight.
         * @return the protocol field value
         */
        public String fontWeight() {
            return (String) require("fontWeight");
        }
        /**
         * The font-stretch.
         * @return the protocol field value
         */
        public String fontStretch() {
            return (String) require("fontStretch");
        }
        /**
         * The font-display.
         * @return the protocol field value
         */
        public String fontDisplay() {
            return (String) require("fontDisplay");
        }
        /**
         * The unicode-range.
         * @return the protocol field value
         */
        public String unicodeRange() {
            return (String) require("unicodeRange");
        }
        /**
         * The src.
         * @return the protocol field value
         */
        public String src() {
            return (String) require("src");
        }
        /**
         * The resolved platform font family
         * @return the protocol field value
         */
        public String platformFontFamily() {
            return (String) require("platformFontFamily");
        }
        /**
         * Available variation settings (a.k.a. &quot;axes&quot;).
         * @return the protocol field value, empty when absent
         */
        public Optional<java.util.List<CSS.FontVariationAxis>> fontVariationAxes() {
            return Optional.ofNullable(list(raw("fontVariationAxes"), element0 -> java.util.Objects.requireNonNull(CSS.FontVariationAxis.fromMap(java.util.Objects.requireNonNull(CdpObject.objectMap(element0))))));
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
         * @param fontVariationAxes field value; empty omits the value
         * @return this model
         */
        public FontFace fontVariationAxes(Optional<java.util.List<CSS.FontVariationAxis>> fontVariationAxes) {
            set("fontVariationAxes", fontVariationAxes.orElse(null));
            return this;
        }
        /**
         * Available variation settings (a.k.a. &quot;axes&quot;).
         * @param fontVariationAxes field value; null removes the value
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
            return Optional.ofNullable(raw("styleSheetId") == null ? null : new DOM.StyleSheetId((String) raw("styleSheetId")));
        }
        /**
         * Parent stylesheet&#x27;s origin.
         * @return the protocol field value
         */
        public CSS.StyleSheetOrigin origin() {
            return CSS.StyleSheetOrigin.of((String) require("origin"));
        }
        /**
         * Associated style declaration.
         * @return the protocol field value
         */
        public CSS.CSSStyle style() {
            return java.util.Objects.requireNonNull(CSS.CSSStyle.fromMap(java.util.Objects.requireNonNull(CdpObject.objectMap(require("style")))));
        }
        /**
         * The css style sheet identifier (absent for user agent stylesheet and user-specified stylesheet rules) this rule came from.
         * @param styleSheetId field value; empty omits the value
         * @return this model
         */
        public CSSTryRule styleSheetId(Optional<DOM.StyleSheetId> styleSheetId) {
            set("styleSheetId", styleSheetId.orElse(null));
            return this;
        }
        /**
         * The css style sheet identifier (absent for user agent stylesheet and user-specified stylesheet rules) this rule came from.
         * @param styleSheetId field value; null removes the value
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
            return java.util.Objects.requireNonNull(CSS.Value.fromMap(java.util.Objects.requireNonNull(CdpObject.objectMap(require("name")))));
        }
        /**
         * The css style sheet identifier (absent for user agent stylesheet and user-specified stylesheet rules) this rule came from.
         * @return the protocol field value, empty when absent
         */
        public Optional<DOM.StyleSheetId> styleSheetId() {
            return Optional.ofNullable(raw("styleSheetId") == null ? null : new DOM.StyleSheetId((String) raw("styleSheetId")));
        }
        /**
         * Parent stylesheet&#x27;s origin.
         * @return the protocol field value
         */
        public CSS.StyleSheetOrigin origin() {
            return CSS.StyleSheetOrigin.of((String) require("origin"));
        }
        /**
         * Associated style declaration.
         * @return the protocol field value
         */
        public CSS.CSSStyle style() {
            return java.util.Objects.requireNonNull(CSS.CSSStyle.fromMap(java.util.Objects.requireNonNull(CdpObject.objectMap(require("style")))));
        }
        /**
         * Returns the active field.
         * @return the protocol field value
         */
        public boolean active() {
            return (Boolean) require("active");
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
         * @param styleSheetId field value; empty omits the value
         * @return this model
         */
        public CSSPositionTryRule styleSheetId(Optional<DOM.StyleSheetId> styleSheetId) {
            set("styleSheetId", styleSheetId.orElse(null));
            return this;
        }
        /**
         * The css style sheet identifier (absent for user agent stylesheet and user-specified stylesheet rules) this rule came from.
         * @param styleSheetId field value; null removes the value
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
            return java.util.Objects.requireNonNull(CSS.Value.fromMap(java.util.Objects.requireNonNull(CdpObject.objectMap(require("animationName")))));
        }
        /**
         * List of keyframes.
         * @return the protocol field value
         */
        public java.util.List<CSS.CSSKeyframeRule> keyframes() {
            return CdpObject.requireList(require("keyframes"), element0 -> java.util.Objects.requireNonNull(CSS.CSSKeyframeRule.fromMap(java.util.Objects.requireNonNull(CdpObject.objectMap(element0)))));
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
            return (String) require("propertyName");
        }
        /**
         * Returns the initialValue field.
         * @return the protocol field value, empty when absent
         */
        public Optional<CSS.Value> initialValue() {
            return Optional.ofNullable(raw("initialValue") == null ? null : CSS.Value.fromMap(java.util.Objects.requireNonNull(objectMap(raw("initialValue")))));
        }
        /**
         * Returns the inherits field.
         * @return the protocol field value
         */
        public boolean inherits() {
            return (Boolean) require("inherits");
        }
        /**
         * Returns the syntax field.
         * @return the protocol field value
         */
        public String syntax() {
            return (String) require("syntax");
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
         * @param initialValue field value; empty omits the value
         * @return this model
         */
        public CSSPropertyRegistration initialValue(Optional<CSS.Value> initialValue) {
            set("initialValue", initialValue.orElse(null));
            return this;
        }
        /**
         * Sets the initialValue field.
         * @param initialValue field value; null removes the value
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
            @Nonnull public String value() { return value; }
            public static TypeValues of(@Nonnull String value) {
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
            @Nonnull public String value() { return value; }
            public static SubsectionValues of(@Nonnull String value) {
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
            return CSSAtRule.TypeValues.of((String) require("type"));
        }
        /**
         * Subsection of font-feature-values, if this is a subsection.
         * @return the protocol field value, empty when absent
         */
        public Optional<CSSAtRule.SubsectionValues> subsection() {
            return Optional.ofNullable(raw("subsection") == null ? null : CSSAtRule.SubsectionValues.of((String) raw("subsection")));
        }
        /**
         * LINT.ThenChange(//third_party/blink/renderer/core/inspector/inspector_style_sheet.cc:FontVariantAlternatesFeatureType,//third_party/blink/renderer/core/inspector/inspector_css_agent.cc:FontVariantAlternatesFeatureType) Associated name, if applicable.
         * @return the protocol field value, empty when absent
         */
        public Optional<CSS.Value> name() {
            return Optional.ofNullable(raw("name") == null ? null : CSS.Value.fromMap(java.util.Objects.requireNonNull(objectMap(raw("name")))));
        }
        /**
         * The css style sheet identifier (absent for user agent stylesheet and user-specified stylesheet rules) this rule came from.
         * @return the protocol field value, empty when absent
         */
        public Optional<DOM.StyleSheetId> styleSheetId() {
            return Optional.ofNullable(raw("styleSheetId") == null ? null : new DOM.StyleSheetId((String) raw("styleSheetId")));
        }
        /**
         * Parent stylesheet&#x27;s origin.
         * @return the protocol field value
         */
        public CSS.StyleSheetOrigin origin() {
            return CSS.StyleSheetOrigin.of((String) require("origin"));
        }
        /**
         * Associated style declaration.
         * @return the protocol field value
         */
        public CSS.CSSStyle style() {
            return java.util.Objects.requireNonNull(CSS.CSSStyle.fromMap(java.util.Objects.requireNonNull(CdpObject.objectMap(require("style")))));
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
         * @param subsection field value; empty omits the value
         * @return this model
         */
        public CSSAtRule subsection(Optional<CSSAtRule.SubsectionValues> subsection) {
            set("subsection", subsection.orElse(null));
            return this;
        }
        /**
         * Subsection of font-feature-values, if this is a subsection.
         * @param subsection field value; null removes the value
         * @return this model
         */
        public CSSAtRule subsection(CSSAtRule.SubsectionValues subsection) {
            set("subsection", subsection);
            return this;
        }
        /**
         * LINT.ThenChange(//third_party/blink/renderer/core/inspector/inspector_style_sheet.cc:FontVariantAlternatesFeatureType,//third_party/blink/renderer/core/inspector/inspector_css_agent.cc:FontVariantAlternatesFeatureType) Associated name, if applicable.
         * @param name field value; empty omits the value
         * @return this model
         */
        public CSSAtRule name(Optional<CSS.Value> name) {
            set("name", name.orElse(null));
            return this;
        }
        /**
         * LINT.ThenChange(//third_party/blink/renderer/core/inspector/inspector_style_sheet.cc:FontVariantAlternatesFeatureType,//third_party/blink/renderer/core/inspector/inspector_css_agent.cc:FontVariantAlternatesFeatureType) Associated name, if applicable.
         * @param name field value; null removes the value
         * @return this model
         */
        public CSSAtRule name(CSS.Value name) {
            set("name", name);
            return this;
        }
        /**
         * The css style sheet identifier (absent for user agent stylesheet and user-specified stylesheet rules) this rule came from.
         * @param styleSheetId field value; empty omits the value
         * @return this model
         */
        public CSSAtRule styleSheetId(Optional<DOM.StyleSheetId> styleSheetId) {
            set("styleSheetId", styleSheetId.orElse(null));
            return this;
        }
        /**
         * The css style sheet identifier (absent for user agent stylesheet and user-specified stylesheet rules) this rule came from.
         * @param styleSheetId field value; null removes the value
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
            return Optional.ofNullable(raw("styleSheetId") == null ? null : new DOM.StyleSheetId((String) raw("styleSheetId")));
        }
        /**
         * Parent stylesheet&#x27;s origin.
         * @return the protocol field value
         */
        public CSS.StyleSheetOrigin origin() {
            return CSS.StyleSheetOrigin.of((String) require("origin"));
        }
        /**
         * Associated property name.
         * @return the protocol field value
         */
        public CSS.Value propertyName() {
            return java.util.Objects.requireNonNull(CSS.Value.fromMap(java.util.Objects.requireNonNull(CdpObject.objectMap(require("propertyName")))));
        }
        /**
         * Associated style declaration.
         * @return the protocol field value
         */
        public CSS.CSSStyle style() {
            return java.util.Objects.requireNonNull(CSS.CSSStyle.fromMap(java.util.Objects.requireNonNull(CdpObject.objectMap(require("style")))));
        }
        /**
         * The css style sheet identifier (absent for user agent stylesheet and user-specified stylesheet rules) this rule came from.
         * @param styleSheetId field value; empty omits the value
         * @return this model
         */
        public CSSPropertyRule styleSheetId(Optional<DOM.StyleSheetId> styleSheetId) {
            set("styleSheetId", styleSheetId.orElse(null));
            return this;
        }
        /**
         * The css style sheet identifier (absent for user agent stylesheet and user-specified stylesheet rules) this rule came from.
         * @param styleSheetId field value; null removes the value
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
            return (String) require("name");
        }
        /**
         * The parameter type.
         * @return the protocol field value
         */
        public String type() {
            return (String) require("type");
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
            return Optional.ofNullable(raw("media") == null ? null : CSS.CSSMedia.fromMap(java.util.Objects.requireNonNull(objectMap(raw("media")))));
        }
        /**
         * Container query for this conditional block. Only one type of condition should be set.
         * @return the protocol field value, empty when absent
         */
        public Optional<CSS.CSSContainerQuery> containerQueries() {
            return Optional.ofNullable(raw("containerQueries") == null ? null : CSS.CSSContainerQuery.fromMap(java.util.Objects.requireNonNull(objectMap(raw("containerQueries")))));
        }
        /**
         * &#64;supports CSS at-rule condition. Only one type of condition should be set.
         * @return the protocol field value, empty when absent
         */
        public Optional<CSS.CSSSupports> supports() {
            return Optional.ofNullable(raw("supports") == null ? null : CSS.CSSSupports.fromMap(java.util.Objects.requireNonNull(objectMap(raw("supports")))));
        }
        /**
         * &#64;navigation condition. Only one type of condition should be set.
         * @return the protocol field value, empty when absent
         */
        public Optional<CSS.CSSNavigation> navigation() {
            return Optional.ofNullable(raw("navigation") == null ? null : CSS.CSSNavigation.fromMap(java.util.Objects.requireNonNull(objectMap(raw("navigation")))));
        }
        /**
         * Block body.
         * @return the protocol field value
         */
        public java.util.List<CSS.CSSFunctionNode> children() {
            return CdpObject.requireList(require("children"), element0 -> java.util.Objects.requireNonNull(CSS.CSSFunctionNode.fromMap(java.util.Objects.requireNonNull(CdpObject.objectMap(element0)))));
        }
        /**
         * The condition text.
         * @return the protocol field value
         */
        public String conditionText() {
            return (String) require("conditionText");
        }
        /**
         * Media query for this conditional block. Only one type of condition should be set.
         * @param media field value; empty omits the value
         * @return this model
         */
        public CSSFunctionConditionNode media(Optional<CSS.CSSMedia> media) {
            set("media", media.orElse(null));
            return this;
        }
        /**
         * Media query for this conditional block. Only one type of condition should be set.
         * @param media field value; null removes the value
         * @return this model
         */
        public CSSFunctionConditionNode media(CSS.CSSMedia media) {
            set("media", media);
            return this;
        }
        /**
         * Container query for this conditional block. Only one type of condition should be set.
         * @param containerQueries field value; empty omits the value
         * @return this model
         */
        public CSSFunctionConditionNode containerQueries(Optional<CSS.CSSContainerQuery> containerQueries) {
            set("containerQueries", containerQueries.orElse(null));
            return this;
        }
        /**
         * Container query for this conditional block. Only one type of condition should be set.
         * @param containerQueries field value; null removes the value
         * @return this model
         */
        public CSSFunctionConditionNode containerQueries(CSS.CSSContainerQuery containerQueries) {
            set("containerQueries", containerQueries);
            return this;
        }
        /**
         * &#64;supports CSS at-rule condition. Only one type of condition should be set.
         * @param supports field value; empty omits the value
         * @return this model
         */
        public CSSFunctionConditionNode supports(Optional<CSS.CSSSupports> supports) {
            set("supports", supports.orElse(null));
            return this;
        }
        /**
         * &#64;supports CSS at-rule condition. Only one type of condition should be set.
         * @param supports field value; null removes the value
         * @return this model
         */
        public CSSFunctionConditionNode supports(CSS.CSSSupports supports) {
            set("supports", supports);
            return this;
        }
        /**
         * &#64;navigation condition. Only one type of condition should be set.
         * @param navigation field value; empty omits the value
         * @return this model
         */
        public CSSFunctionConditionNode navigation(Optional<CSS.CSSNavigation> navigation) {
            set("navigation", navigation.orElse(null));
            return this;
        }
        /**
         * &#64;navigation condition. Only one type of condition should be set.
         * @param navigation field value; null removes the value
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
            return Optional.ofNullable(raw("condition") == null ? null : CSS.CSSFunctionConditionNode.fromMap(java.util.Objects.requireNonNull(objectMap(raw("condition")))));
        }
        /**
         * Values set by this node. If set, condition should not be set.
         * @return the protocol field value, empty when absent
         */
        public Optional<CSS.CSSStyle> style() {
            return Optional.ofNullable(raw("style") == null ? null : CSS.CSSStyle.fromMap(java.util.Objects.requireNonNull(objectMap(raw("style")))));
        }
        /**
         * A conditional block. If set, style should not be set.
         * @param condition field value; empty omits the value
         * @return this model
         */
        public CSSFunctionNode condition(Optional<CSS.CSSFunctionConditionNode> condition) {
            set("condition", condition.orElse(null));
            return this;
        }
        /**
         * A conditional block. If set, style should not be set.
         * @param condition field value; null removes the value
         * @return this model
         */
        public CSSFunctionNode condition(CSS.CSSFunctionConditionNode condition) {
            set("condition", condition);
            return this;
        }
        /**
         * Values set by this node. If set, condition should not be set.
         * @param style field value; empty omits the value
         * @return this model
         */
        public CSSFunctionNode style(Optional<CSS.CSSStyle> style) {
            set("style", style.orElse(null));
            return this;
        }
        /**
         * Values set by this node. If set, condition should not be set.
         * @param style field value; null removes the value
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
            return java.util.Objects.requireNonNull(CSS.Value.fromMap(java.util.Objects.requireNonNull(CdpObject.objectMap(require("name")))));
        }
        /**
         * The css style sheet identifier (absent for user agent stylesheet and user-specified stylesheet rules) this rule came from.
         * @return the protocol field value, empty when absent
         */
        public Optional<DOM.StyleSheetId> styleSheetId() {
            return Optional.ofNullable(raw("styleSheetId") == null ? null : new DOM.StyleSheetId((String) raw("styleSheetId")));
        }
        /**
         * Parent stylesheet&#x27;s origin.
         * @return the protocol field value
         */
        public CSS.StyleSheetOrigin origin() {
            return CSS.StyleSheetOrigin.of((String) require("origin"));
        }
        /**
         * List of parameters.
         * @return the protocol field value
         */
        public java.util.List<CSS.CSSFunctionParameter> parameters() {
            return CdpObject.requireList(require("parameters"), element0 -> java.util.Objects.requireNonNull(CSS.CSSFunctionParameter.fromMap(java.util.Objects.requireNonNull(CdpObject.objectMap(element0)))));
        }
        /**
         * Function body.
         * @return the protocol field value
         */
        public java.util.List<CSS.CSSFunctionNode> children() {
            return CdpObject.requireList(require("children"), element0 -> java.util.Objects.requireNonNull(CSS.CSSFunctionNode.fromMap(java.util.Objects.requireNonNull(CdpObject.objectMap(element0)))));
        }
        /**
         * The BackendNodeId of the DOM node that constitutes the origin tree scope of this rule.
         * <p><b>Experimental:</b> this part of CDP may change without notice.
         * @return the protocol field value, empty when absent
         */
        public Optional<DOM.BackendNodeId> originTreeScopeNodeId() {
            return Optional.ofNullable(raw("originTreeScopeNodeId") == null ? null : new DOM.BackendNodeId(((Number) raw("originTreeScopeNodeId")).longValue()));
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
         * @param styleSheetId field value; empty omits the value
         * @return this model
         */
        public CSSFunctionRule styleSheetId(Optional<DOM.StyleSheetId> styleSheetId) {
            set("styleSheetId", styleSheetId.orElse(null));
            return this;
        }
        /**
         * The css style sheet identifier (absent for user agent stylesheet and user-specified stylesheet rules) this rule came from.
         * @param styleSheetId field value; null removes the value
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
         * @param originTreeScopeNodeId field value; empty omits the value
         * @return this model
         */
        public CSSFunctionRule originTreeScopeNodeId(Optional<DOM.BackendNodeId> originTreeScopeNodeId) {
            set("originTreeScopeNodeId", originTreeScopeNodeId.orElse(null));
            return this;
        }
        /**
         * The BackendNodeId of the DOM node that constitutes the origin tree scope of this rule.
         * <p><b>Experimental:</b> this part of CDP may change without notice.
         * @param originTreeScopeNodeId field value; null removes the value
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
            return Optional.ofNullable(raw("styleSheetId") == null ? null : new DOM.StyleSheetId((String) raw("styleSheetId")));
        }
        /**
         * Parent stylesheet&#x27;s origin.
         * @return the protocol field value
         */
        public CSS.StyleSheetOrigin origin() {
            return CSS.StyleSheetOrigin.of((String) require("origin"));
        }
        /**
         * Associated key text.
         * @return the protocol field value
         */
        public CSS.Value keyText() {
            return java.util.Objects.requireNonNull(CSS.Value.fromMap(java.util.Objects.requireNonNull(CdpObject.objectMap(require("keyText")))));
        }
        /**
         * Associated style declaration.
         * @return the protocol field value
         */
        public CSS.CSSStyle style() {
            return java.util.Objects.requireNonNull(CSS.CSSStyle.fromMap(java.util.Objects.requireNonNull(CdpObject.objectMap(require("style")))));
        }
        /**
         * The css style sheet identifier (absent for user agent stylesheet and user-specified stylesheet rules) this rule came from.
         * @param styleSheetId field value; empty omits the value
         * @return this model
         */
        public CSSKeyframeRule styleSheetId(Optional<DOM.StyleSheetId> styleSheetId) {
            set("styleSheetId", styleSheetId.orElse(null));
            return this;
        }
        /**
         * The css style sheet identifier (absent for user agent stylesheet and user-specified stylesheet rules) this rule came from.
         * @param styleSheetId field value; null removes the value
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
            return new DOM.StyleSheetId((String) require("styleSheetId"));
        }
        /**
         * The range of the style text in the enclosing stylesheet.
         * @return the protocol field value
         */
        public CSS.SourceRange range() {
            return java.util.Objects.requireNonNull(CSS.SourceRange.fromMap(java.util.Objects.requireNonNull(CdpObject.objectMap(require("range")))));
        }
        /**
         * New style text.
         * @return the protocol field value
         */
        public String text() {
            return (String) require("text");
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
            return Optional.ofNullable(list(raw("backgroundColors"), element0 -> (String) element0));
        }
        /**
         * The computed font size for this node, as a CSS computed value string (e.g. &#x27;12px&#x27;).
         * @return the protocol field value, empty when absent
         */
        public Optional<String> computedFontSize() {
            return Optional.ofNullable((String) raw("computedFontSize"));
        }
        /**
         * The computed font weight for this node, as a CSS computed value string (e.g. &#x27;normal&#x27; or &#x27;100&#x27;).
         * @return the protocol field value, empty when absent
         */
        public Optional<String> computedFontWeight() {
            return Optional.ofNullable((String) raw("computedFontWeight"));
        }
        /**
         * The range of background colors behind this element, if it contains any visible text. If no visible text is present, this will be undefined. In the case of a flat background color, this will consist of simply that color. In the case of a gradient, this will consist of each of the color stops. For anything more complicated, this will be an empty array. Images will be ignored (as if the image had failed to load).
         * @param backgroundColors field value; empty omits the value
         * @return this model
         */
        public GetBackgroundColorsResult backgroundColors(Optional<java.util.List<String>> backgroundColors) {
            set("backgroundColors", backgroundColors.orElse(null));
            return this;
        }
        /**
         * The range of background colors behind this element, if it contains any visible text. If no visible text is present, this will be undefined. In the case of a flat background color, this will consist of simply that color. In the case of a gradient, this will consist of each of the color stops. For anything more complicated, this will be an empty array. Images will be ignored (as if the image had failed to load).
         * @param backgroundColors field value; null removes the value
         * @return this model
         */
        public GetBackgroundColorsResult backgroundColors(java.util.List<String> backgroundColors) {
            set("backgroundColors", backgroundColors);
            return this;
        }
        /**
         * The computed font size for this node, as a CSS computed value string (e.g. &#x27;12px&#x27;).
         * @param computedFontSize field value; empty omits the value
         * @return this model
         */
        public GetBackgroundColorsResult computedFontSize(Optional<String> computedFontSize) {
            set("computedFontSize", computedFontSize.orElse(null));
            return this;
        }
        /**
         * The computed font size for this node, as a CSS computed value string (e.g. &#x27;12px&#x27;).
         * @param computedFontSize field value; null removes the value
         * @return this model
         */
        public GetBackgroundColorsResult computedFontSize(String computedFontSize) {
            set("computedFontSize", computedFontSize);
            return this;
        }
        /**
         * The computed font weight for this node, as a CSS computed value string (e.g. &#x27;normal&#x27; or &#x27;100&#x27;).
         * @param computedFontWeight field value; empty omits the value
         * @return this model
         */
        public GetBackgroundColorsResult computedFontWeight(Optional<String> computedFontWeight) {
            set("computedFontWeight", computedFontWeight.orElse(null));
            return this;
        }
        /**
         * The computed font weight for this node, as a CSS computed value string (e.g. &#x27;normal&#x27; or &#x27;100&#x27;).
         * @param computedFontWeight field value; null removes the value
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
            return CdpObject.requireList(require("computedStyle"), element0 -> java.util.Objects.requireNonNull(CSS.CSSComputedStyleProperty.fromMap(java.util.Objects.requireNonNull(CdpObject.objectMap(element0)))));
        }
        /**
         * A list of non-standard &quot;extra fields&quot; which blink stores alongside each computed style.
         * <p><b>Experimental:</b> this part of CDP may change without notice.
         * @return the protocol field value
         */
        public CSS.ComputedStyleExtraFields extraFields() {
            return java.util.Objects.requireNonNull(CSS.ComputedStyleExtraFields.fromMap(java.util.Objects.requireNonNull(CdpObject.objectMap(require("extraFields")))));
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
            return Optional.ofNullable(raw("inlineStyle") == null ? null : CSS.CSSStyle.fromMap(java.util.Objects.requireNonNull(objectMap(raw("inlineStyle")))));
        }
        /**
         * Attribute-defined element style (e.g. resulting from &quot;width=20 height=100%&quot;).
         * @return the protocol field value, empty when absent
         */
        public Optional<CSS.CSSStyle> attributesStyle() {
            return Optional.ofNullable(raw("attributesStyle") == null ? null : CSS.CSSStyle.fromMap(java.util.Objects.requireNonNull(objectMap(raw("attributesStyle")))));
        }
        /**
         * Inline style for the specified DOM node.
         * @param inlineStyle field value; empty omits the value
         * @return this model
         */
        public GetInlineStylesForNodeResult inlineStyle(Optional<CSS.CSSStyle> inlineStyle) {
            set("inlineStyle", inlineStyle.orElse(null));
            return this;
        }
        /**
         * Inline style for the specified DOM node.
         * @param inlineStyle field value; null removes the value
         * @return this model
         */
        public GetInlineStylesForNodeResult inlineStyle(CSS.CSSStyle inlineStyle) {
            set("inlineStyle", inlineStyle);
            return this;
        }
        /**
         * Attribute-defined element style (e.g. resulting from &quot;width=20 height=100%&quot;).
         * @param attributesStyle field value; empty omits the value
         * @return this model
         */
        public GetInlineStylesForNodeResult attributesStyle(Optional<CSS.CSSStyle> attributesStyle) {
            set("attributesStyle", attributesStyle.orElse(null));
            return this;
        }
        /**
         * Attribute-defined element style (e.g. resulting from &quot;width=20 height=100%&quot;).
         * @param attributesStyle field value; null removes the value
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
            return Optional.ofNullable(list(raw("animationStyles"), element0 -> java.util.Objects.requireNonNull(CSS.CSSAnimationStyle.fromMap(java.util.Objects.requireNonNull(CdpObject.objectMap(element0))))));
        }
        /**
         * Style coming from transitions.
         * @return the protocol field value, empty when absent
         */
        public Optional<CSS.CSSStyle> transitionsStyle() {
            return Optional.ofNullable(raw("transitionsStyle") == null ? null : CSS.CSSStyle.fromMap(java.util.Objects.requireNonNull(objectMap(raw("transitionsStyle")))));
        }
        /**
         * Inherited style entries for animationsStyle and transitionsStyle from the inheritance chain of the element.
         * @return the protocol field value, empty when absent
         */
        public Optional<java.util.List<CSS.InheritedAnimatedStyleEntry>> inherited() {
            return Optional.ofNullable(list(raw("inherited"), element0 -> java.util.Objects.requireNonNull(CSS.InheritedAnimatedStyleEntry.fromMap(java.util.Objects.requireNonNull(CdpObject.objectMap(element0))))));
        }
        /**
         * Styles coming from animations.
         * @param animationStyles field value; empty omits the value
         * @return this model
         */
        public GetAnimatedStylesForNodeResult animationStyles(Optional<java.util.List<CSS.CSSAnimationStyle>> animationStyles) {
            set("animationStyles", animationStyles.orElse(null));
            return this;
        }
        /**
         * Styles coming from animations.
         * @param animationStyles field value; null removes the value
         * @return this model
         */
        public GetAnimatedStylesForNodeResult animationStyles(java.util.List<CSS.CSSAnimationStyle> animationStyles) {
            set("animationStyles", animationStyles);
            return this;
        }
        /**
         * Style coming from transitions.
         * @param transitionsStyle field value; empty omits the value
         * @return this model
         */
        public GetAnimatedStylesForNodeResult transitionsStyle(Optional<CSS.CSSStyle> transitionsStyle) {
            set("transitionsStyle", transitionsStyle.orElse(null));
            return this;
        }
        /**
         * Style coming from transitions.
         * @param transitionsStyle field value; null removes the value
         * @return this model
         */
        public GetAnimatedStylesForNodeResult transitionsStyle(CSS.CSSStyle transitionsStyle) {
            set("transitionsStyle", transitionsStyle);
            return this;
        }
        /**
         * Inherited style entries for animationsStyle and transitionsStyle from the inheritance chain of the element.
         * @param inherited field value; empty omits the value
         * @return this model
         */
        public GetAnimatedStylesForNodeResult inherited(Optional<java.util.List<CSS.InheritedAnimatedStyleEntry>> inherited) {
            set("inherited", inherited.orElse(null));
            return this;
        }
        /**
         * Inherited style entries for animationsStyle and transitionsStyle from the inheritance chain of the element.
         * @param inherited field value; null removes the value
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
            return Optional.ofNullable(raw("inlineStyle") == null ? null : CSS.CSSStyle.fromMap(java.util.Objects.requireNonNull(objectMap(raw("inlineStyle")))));
        }
        /**
         * Attribute-defined element style (e.g. resulting from &quot;width=20 height=100%&quot;).
         * @return the protocol field value, empty when absent
         */
        public Optional<CSS.CSSStyle> attributesStyle() {
            return Optional.ofNullable(raw("attributesStyle") == null ? null : CSS.CSSStyle.fromMap(java.util.Objects.requireNonNull(objectMap(raw("attributesStyle")))));
        }
        /**
         * CSS rules matching this node, from all applicable stylesheets.
         * @return the protocol field value, empty when absent
         */
        public Optional<java.util.List<CSS.RuleMatch>> matchedCSSRules() {
            return Optional.ofNullable(list(raw("matchedCSSRules"), element0 -> java.util.Objects.requireNonNull(CSS.RuleMatch.fromMap(java.util.Objects.requireNonNull(CdpObject.objectMap(element0))))));
        }
        /**
         * Pseudo style matches for this node.
         * @return the protocol field value, empty when absent
         */
        public Optional<java.util.List<CSS.PseudoElementMatches>> pseudoElements() {
            return Optional.ofNullable(list(raw("pseudoElements"), element0 -> java.util.Objects.requireNonNull(CSS.PseudoElementMatches.fromMap(java.util.Objects.requireNonNull(CdpObject.objectMap(element0))))));
        }
        /**
         * A chain of inherited styles (from the immediate node parent up to the DOM tree root).
         * @return the protocol field value, empty when absent
         */
        public Optional<java.util.List<CSS.InheritedStyleEntry>> inherited() {
            return Optional.ofNullable(list(raw("inherited"), element0 -> java.util.Objects.requireNonNull(CSS.InheritedStyleEntry.fromMap(java.util.Objects.requireNonNull(CdpObject.objectMap(element0))))));
        }
        /**
         * A chain of inherited pseudo element styles (from the immediate node parent up to the DOM tree root).
         * @return the protocol field value, empty when absent
         */
        public Optional<java.util.List<CSS.InheritedPseudoElementMatches>> inheritedPseudoElements() {
            return Optional.ofNullable(list(raw("inheritedPseudoElements"), element0 -> java.util.Objects.requireNonNull(CSS.InheritedPseudoElementMatches.fromMap(java.util.Objects.requireNonNull(CdpObject.objectMap(element0))))));
        }
        /**
         * A list of CSS keyframed animations matching this node.
         * @return the protocol field value, empty when absent
         */
        public Optional<java.util.List<CSS.CSSKeyframesRule>> cssKeyframesRules() {
            return Optional.ofNullable(list(raw("cssKeyframesRules"), element0 -> java.util.Objects.requireNonNull(CSS.CSSKeyframesRule.fromMap(java.util.Objects.requireNonNull(CdpObject.objectMap(element0))))));
        }
        /**
         * A list of CSS &#64;position-try rules matching this node, based on the position-try-fallbacks property.
         * @return the protocol field value, empty when absent
         */
        public Optional<java.util.List<CSS.CSSPositionTryRule>> cssPositionTryRules() {
            return Optional.ofNullable(list(raw("cssPositionTryRules"), element0 -> java.util.Objects.requireNonNull(CSS.CSSPositionTryRule.fromMap(java.util.Objects.requireNonNull(CdpObject.objectMap(element0))))));
        }
        /**
         * Index of the active fallback in the applied position-try-fallback property, will not be set if there is no active position-try fallback.
         * @return the protocol field value, empty when absent
         */
        public OptionalLong activePositionFallbackIndex() {
            Long value = CdpObject.numberAsLong(raw("activePositionFallbackIndex"));
            return value == null ? OptionalLong.empty() : OptionalLong.of(value);
        }
        /**
         * A list of CSS at-property rules matching this node.
         * @return the protocol field value, empty when absent
         */
        public Optional<java.util.List<CSS.CSSPropertyRule>> cssPropertyRules() {
            return Optional.ofNullable(list(raw("cssPropertyRules"), element0 -> java.util.Objects.requireNonNull(CSS.CSSPropertyRule.fromMap(java.util.Objects.requireNonNull(CdpObject.objectMap(element0))))));
        }
        /**
         * A list of CSS property registrations matching this node.
         * @return the protocol field value, empty when absent
         */
        public Optional<java.util.List<CSS.CSSPropertyRegistration>> cssPropertyRegistrations() {
            return Optional.ofNullable(list(raw("cssPropertyRegistrations"), element0 -> java.util.Objects.requireNonNull(CSS.CSSPropertyRegistration.fromMap(java.util.Objects.requireNonNull(CdpObject.objectMap(element0))))));
        }
        /**
         * A list of simple &#64;rules matching this node or its pseudo-elements.
         * @return the protocol field value, empty when absent
         */
        public Optional<java.util.List<CSS.CSSAtRule>> cssAtRules() {
            return Optional.ofNullable(list(raw("cssAtRules"), element0 -> java.util.Objects.requireNonNull(CSS.CSSAtRule.fromMap(java.util.Objects.requireNonNull(CdpObject.objectMap(element0))))));
        }
        /**
         * Id of the first parent element that does not have display: contents.
         * <p><b>Experimental:</b> this part of CDP may change without notice.
         * @return the protocol field value, empty when absent
         */
        public Optional<DOM.NodeId> parentLayoutNodeId() {
            return Optional.ofNullable(raw("parentLayoutNodeId") == null ? null : new DOM.NodeId(((Number) raw("parentLayoutNodeId")).longValue()));
        }
        /**
         * A list of CSS at-function rules referenced by styles of this node.
         * <p><b>Experimental:</b> this part of CDP may change without notice.
         * @return the protocol field value, empty when absent
         */
        public Optional<java.util.List<CSS.CSSFunctionRule>> cssFunctionRules() {
            return Optional.ofNullable(list(raw("cssFunctionRules"), element0 -> java.util.Objects.requireNonNull(CSS.CSSFunctionRule.fromMap(java.util.Objects.requireNonNull(CdpObject.objectMap(element0))))));
        }
        /**
         * Inline style for the specified DOM node.
         * @param inlineStyle field value; empty omits the value
         * @return this model
         */
        public GetMatchedStylesForNodeResult inlineStyle(Optional<CSS.CSSStyle> inlineStyle) {
            set("inlineStyle", inlineStyle.orElse(null));
            return this;
        }
        /**
         * Inline style for the specified DOM node.
         * @param inlineStyle field value; null removes the value
         * @return this model
         */
        public GetMatchedStylesForNodeResult inlineStyle(CSS.CSSStyle inlineStyle) {
            set("inlineStyle", inlineStyle);
            return this;
        }
        /**
         * Attribute-defined element style (e.g. resulting from &quot;width=20 height=100%&quot;).
         * @param attributesStyle field value; empty omits the value
         * @return this model
         */
        public GetMatchedStylesForNodeResult attributesStyle(Optional<CSS.CSSStyle> attributesStyle) {
            set("attributesStyle", attributesStyle.orElse(null));
            return this;
        }
        /**
         * Attribute-defined element style (e.g. resulting from &quot;width=20 height=100%&quot;).
         * @param attributesStyle field value; null removes the value
         * @return this model
         */
        public GetMatchedStylesForNodeResult attributesStyle(CSS.CSSStyle attributesStyle) {
            set("attributesStyle", attributesStyle);
            return this;
        }
        /**
         * CSS rules matching this node, from all applicable stylesheets.
         * @param matchedCSSRules field value; empty omits the value
         * @return this model
         */
        public GetMatchedStylesForNodeResult matchedCSSRules(Optional<java.util.List<CSS.RuleMatch>> matchedCSSRules) {
            set("matchedCSSRules", matchedCSSRules.orElse(null));
            return this;
        }
        /**
         * CSS rules matching this node, from all applicable stylesheets.
         * @param matchedCSSRules field value; null removes the value
         * @return this model
         */
        public GetMatchedStylesForNodeResult matchedCSSRules(java.util.List<CSS.RuleMatch> matchedCSSRules) {
            set("matchedCSSRules", matchedCSSRules);
            return this;
        }
        /**
         * Pseudo style matches for this node.
         * @param pseudoElements field value; empty omits the value
         * @return this model
         */
        public GetMatchedStylesForNodeResult pseudoElements(Optional<java.util.List<CSS.PseudoElementMatches>> pseudoElements) {
            set("pseudoElements", pseudoElements.orElse(null));
            return this;
        }
        /**
         * Pseudo style matches for this node.
         * @param pseudoElements field value; null removes the value
         * @return this model
         */
        public GetMatchedStylesForNodeResult pseudoElements(java.util.List<CSS.PseudoElementMatches> pseudoElements) {
            set("pseudoElements", pseudoElements);
            return this;
        }
        /**
         * A chain of inherited styles (from the immediate node parent up to the DOM tree root).
         * @param inherited field value; empty omits the value
         * @return this model
         */
        public GetMatchedStylesForNodeResult inherited(Optional<java.util.List<CSS.InheritedStyleEntry>> inherited) {
            set("inherited", inherited.orElse(null));
            return this;
        }
        /**
         * A chain of inherited styles (from the immediate node parent up to the DOM tree root).
         * @param inherited field value; null removes the value
         * @return this model
         */
        public GetMatchedStylesForNodeResult inherited(java.util.List<CSS.InheritedStyleEntry> inherited) {
            set("inherited", inherited);
            return this;
        }
        /**
         * A chain of inherited pseudo element styles (from the immediate node parent up to the DOM tree root).
         * @param inheritedPseudoElements field value; empty omits the value
         * @return this model
         */
        public GetMatchedStylesForNodeResult inheritedPseudoElements(Optional<java.util.List<CSS.InheritedPseudoElementMatches>> inheritedPseudoElements) {
            set("inheritedPseudoElements", inheritedPseudoElements.orElse(null));
            return this;
        }
        /**
         * A chain of inherited pseudo element styles (from the immediate node parent up to the DOM tree root).
         * @param inheritedPseudoElements field value; null removes the value
         * @return this model
         */
        public GetMatchedStylesForNodeResult inheritedPseudoElements(java.util.List<CSS.InheritedPseudoElementMatches> inheritedPseudoElements) {
            set("inheritedPseudoElements", inheritedPseudoElements);
            return this;
        }
        /**
         * A list of CSS keyframed animations matching this node.
         * @param cssKeyframesRules field value; empty omits the value
         * @return this model
         */
        public GetMatchedStylesForNodeResult cssKeyframesRules(Optional<java.util.List<CSS.CSSKeyframesRule>> cssKeyframesRules) {
            set("cssKeyframesRules", cssKeyframesRules.orElse(null));
            return this;
        }
        /**
         * A list of CSS keyframed animations matching this node.
         * @param cssKeyframesRules field value; null removes the value
         * @return this model
         */
        public GetMatchedStylesForNodeResult cssKeyframesRules(java.util.List<CSS.CSSKeyframesRule> cssKeyframesRules) {
            set("cssKeyframesRules", cssKeyframesRules);
            return this;
        }
        /**
         * A list of CSS &#64;position-try rules matching this node, based on the position-try-fallbacks property.
         * @param cssPositionTryRules field value; empty omits the value
         * @return this model
         */
        public GetMatchedStylesForNodeResult cssPositionTryRules(Optional<java.util.List<CSS.CSSPositionTryRule>> cssPositionTryRules) {
            set("cssPositionTryRules", cssPositionTryRules.orElse(null));
            return this;
        }
        /**
         * A list of CSS &#64;position-try rules matching this node, based on the position-try-fallbacks property.
         * @param cssPositionTryRules field value; null removes the value
         * @return this model
         */
        public GetMatchedStylesForNodeResult cssPositionTryRules(java.util.List<CSS.CSSPositionTryRule> cssPositionTryRules) {
            set("cssPositionTryRules", cssPositionTryRules);
            return this;
        }
        /**
         * Index of the active fallback in the applied position-try-fallback property, will not be set if there is no active position-try fallback.
         * @param activePositionFallbackIndex field value; empty omits the value
         * @return this model
         */
        public GetMatchedStylesForNodeResult activePositionFallbackIndex(OptionalLong activePositionFallbackIndex) {
            set("activePositionFallbackIndex", activePositionFallbackIndex.isPresent() ? activePositionFallbackIndex.getAsLong() : null);
            return this;
        }
        /**
         * Index of the active fallback in the applied position-try-fallback property, will not be set if there is no active position-try fallback.
         * @param activePositionFallbackIndex field value; null removes the value
         * @return this model
         */
        public GetMatchedStylesForNodeResult activePositionFallbackIndex(Long activePositionFallbackIndex) {
            set("activePositionFallbackIndex", activePositionFallbackIndex);
            return this;
        }
        /**
         * A list of CSS at-property rules matching this node.
         * @param cssPropertyRules field value; empty omits the value
         * @return this model
         */
        public GetMatchedStylesForNodeResult cssPropertyRules(Optional<java.util.List<CSS.CSSPropertyRule>> cssPropertyRules) {
            set("cssPropertyRules", cssPropertyRules.orElse(null));
            return this;
        }
        /**
         * A list of CSS at-property rules matching this node.
         * @param cssPropertyRules field value; null removes the value
         * @return this model
         */
        public GetMatchedStylesForNodeResult cssPropertyRules(java.util.List<CSS.CSSPropertyRule> cssPropertyRules) {
            set("cssPropertyRules", cssPropertyRules);
            return this;
        }
        /**
         * A list of CSS property registrations matching this node.
         * @param cssPropertyRegistrations field value; empty omits the value
         * @return this model
         */
        public GetMatchedStylesForNodeResult cssPropertyRegistrations(Optional<java.util.List<CSS.CSSPropertyRegistration>> cssPropertyRegistrations) {
            set("cssPropertyRegistrations", cssPropertyRegistrations.orElse(null));
            return this;
        }
        /**
         * A list of CSS property registrations matching this node.
         * @param cssPropertyRegistrations field value; null removes the value
         * @return this model
         */
        public GetMatchedStylesForNodeResult cssPropertyRegistrations(java.util.List<CSS.CSSPropertyRegistration> cssPropertyRegistrations) {
            set("cssPropertyRegistrations", cssPropertyRegistrations);
            return this;
        }
        /**
         * A list of simple &#64;rules matching this node or its pseudo-elements.
         * @param cssAtRules field value; empty omits the value
         * @return this model
         */
        public GetMatchedStylesForNodeResult cssAtRules(Optional<java.util.List<CSS.CSSAtRule>> cssAtRules) {
            set("cssAtRules", cssAtRules.orElse(null));
            return this;
        }
        /**
         * A list of simple &#64;rules matching this node or its pseudo-elements.
         * @param cssAtRules field value; null removes the value
         * @return this model
         */
        public GetMatchedStylesForNodeResult cssAtRules(java.util.List<CSS.CSSAtRule> cssAtRules) {
            set("cssAtRules", cssAtRules);
            return this;
        }
        /**
         * Id of the first parent element that does not have display: contents.
         * <p><b>Experimental:</b> this part of CDP may change without notice.
         * @param parentLayoutNodeId field value; empty omits the value
         * @return this model
         */
        public GetMatchedStylesForNodeResult parentLayoutNodeId(Optional<DOM.NodeId> parentLayoutNodeId) {
            set("parentLayoutNodeId", parentLayoutNodeId.orElse(null));
            return this;
        }
        /**
         * Id of the first parent element that does not have display: contents.
         * <p><b>Experimental:</b> this part of CDP may change without notice.
         * @param parentLayoutNodeId field value; null removes the value
         * @return this model
         */
        public GetMatchedStylesForNodeResult parentLayoutNodeId(DOM.NodeId parentLayoutNodeId) {
            set("parentLayoutNodeId", parentLayoutNodeId);
            return this;
        }
        /**
         * A list of CSS at-function rules referenced by styles of this node.
         * <p><b>Experimental:</b> this part of CDP may change without notice.
         * @param cssFunctionRules field value; empty omits the value
         * @return this model
         */
        public GetMatchedStylesForNodeResult cssFunctionRules(Optional<java.util.List<CSS.CSSFunctionRule>> cssFunctionRules) {
            set("cssFunctionRules", cssFunctionRules.orElse(null));
            return this;
        }
        /**
         * A list of CSS at-function rules referenced by styles of this node.
         * <p><b>Experimental:</b> this part of CDP may change without notice.
         * @param cssFunctionRules field value; null removes the value
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
            return CdpObject.requireList(require("coverage"), element0 -> java.util.Objects.requireNonNull(CSS.RuleUsage.fromMap(java.util.Objects.requireNonNull(CdpObject.objectMap(element0)))));
        }
        /**
         * Monotonically increasing time, in seconds.
         * @return the protocol field value
         */
        public double timestamp() {
            return ((Number) require("timestamp")).doubleValue();
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
            return Optional.ofNullable(raw("font") == null ? null : CSS.FontFace.fromMap(java.util.Objects.requireNonNull(objectMap(raw("font")))));
        }
        /**
         * The web font that has loaded.
         * @param font field value; empty omits the value
         * @return this model
         */
        public FontsUpdatedEvent font(Optional<CSS.FontFace> font) {
            set("font", font.orElse(null));
            return this;
        }
        /**
         * The web font that has loaded.
         * @param font field value; null removes the value
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
            return java.util.Objects.requireNonNull(CSS.CSSStyleSheetHeader.fromMap(java.util.Objects.requireNonNull(CdpObject.objectMap(require("header")))));
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
            return new DOM.StyleSheetId((String) require("styleSheetId"));
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
            return new DOM.StyleSheetId((String) require("styleSheetId"));
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
            return new DOM.NodeId(((Number) require("nodeId")).longValue());
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
    public static final class Client {
        private final CdpClient client;
        public Client(CdpClient client) { this.client = client; }
        /**
         * Inserts a new rule with the given {@code ruleText} in a stylesheet with given {@code styleSheetId}, at the position specified by {@code location}.
         * @param styleSheetId protocol value
         * @param ruleText protocol value
         * @param location protocol value
         * @param nodeForPropertySyntaxValidation protocol value
         * @return a stage completing with the command result
         */
        public CompletionStage<CSS.CSSRule> addRule(DOM.StyleSheetId styleSheetId, String ruleText, CSS.SourceRange location, Optional<DOM.NodeId> nodeForPropertySyntaxValidation) {
            Map<String, Object> params = new LinkedHashMap<>();
            params.put("styleSheetId", CdpObject.json(styleSheetId));
            params.put("ruleText", CdpObject.json(ruleText));
            params.put("location", CdpObject.json(location));
            nodeForPropertySyntaxValidation.ifPresent(value_ -> params.put("nodeForPropertySyntaxValidation", CdpObject.json(value_)));
            return client.call("CSS.addRule", params, result_ -> java.util.Objects.requireNonNull(CSS.CSSRule.fromMap(java.util.Objects.requireNonNull(CdpObject.objectMap(java.util.Objects.requireNonNull(result_.get("rule")))))));
        }
        /**
         * Inserts a new rule with the given {@code ruleText} in a stylesheet with given {@code styleSheetId}, at the position specified by {@code location}.
         * @param styleSheetId protocol value
         * @param ruleText protocol value
         * @param location protocol value
         * @return a stage completing with the command result
         */
        public CompletionStage<CSS.CSSRule> addRule(DOM.StyleSheetId styleSheetId, String ruleText, CSS.SourceRange location) {
            return addRule(styleSheetId, ruleText, location, Optional.empty());
        }
        /**
         * Returns all class names from specified stylesheet.
         * @param styleSheetId protocol value
         * @return a stage completing with the command result
         */
        public CompletionStage<java.util.List<String>> collectClassNames(DOM.StyleSheetId styleSheetId) {
            Map<String, Object> params = new LinkedHashMap<>();
            params.put("styleSheetId", CdpObject.json(styleSheetId));
            return client.call("CSS.collectClassNames", params, result_ -> CdpObject.requireList(java.util.Objects.requireNonNull(result_.get("classNames")), element0 -> (String) element0));
        }
        /**
         * Creates a new special &quot;via-inspector&quot; stylesheet in the frame with given {@code frameId}.
         * @param frameId protocol value
         * @param force protocol value
         * @return a stage completing with the command result
         */
        public CompletionStage<DOM.StyleSheetId> createStyleSheet(Page.FrameId frameId, Optional<Boolean> force) {
            Map<String, Object> params = new LinkedHashMap<>();
            params.put("frameId", CdpObject.json(frameId));
            force.ifPresent(value_ -> params.put("force", value_));
            return client.call("CSS.createStyleSheet", params, result_ -> new DOM.StyleSheetId((String) java.util.Objects.requireNonNull(result_.get("styleSheetId"))));
        }
        /**
         * Creates a new special &quot;via-inspector&quot; stylesheet in the frame with given {@code frameId}.
         * @param frameId protocol value
         * @return a stage completing with the command result
         */
        public CompletionStage<DOM.StyleSheetId> createStyleSheet(Page.FrameId frameId) {
            return createStyleSheet(frameId, Optional.empty());
        }
        /**
         * Disables the CSS agent for the given page.
         * @return a stage completing when the command completes
         */
        public CompletionStage<Void> disable() {
            return client.call("CSS.disable", null, result_ -> null);
        }
        /**
         * Enables the CSS agent for the given page. Clients should not assume that the CSS agent has been enabled until the result of this command is received.
         * @return a stage completing when the command completes
         */
        public CompletionStage<Void> enable() {
            return client.call("CSS.enable", null, result_ -> null);
        }
        /**
         * Ensures that the given node will have specified pseudo-classes whenever its style is computed by the browser.
         * @param nodeId protocol value
         * @param forcedPseudoClasses protocol value
         * @return a stage completing when the command completes
         */
        public CompletionStage<Void> forcePseudoState(DOM.NodeId nodeId, java.util.List<String> forcedPseudoClasses) {
            Map<String, Object> params = new LinkedHashMap<>();
            params.put("nodeId", CdpObject.json(nodeId));
            params.put("forcedPseudoClasses", CdpObject.json(forcedPseudoClasses));
            return client.call("CSS.forcePseudoState", params, result_ -> null);
        }
        /**
         * Ensures that the given node is in its starting-style state.
         * @param nodeId protocol value
         * @param forced protocol value
         * @return a stage completing when the command completes
         */
        public CompletionStage<Void> forceStartingStyle(DOM.NodeId nodeId, boolean forced) {
            Map<String, Object> params = new LinkedHashMap<>();
            params.put("nodeId", CdpObject.json(nodeId));
            params.put("forced", CdpObject.json(forced));
            return client.call("CSS.forceStartingStyle", params, result_ -> null);
        }
        /**
         * Invokes CSS.getBackgroundColors.
         * @param nodeId protocol value
         * @return a stage completing with the command result
         */
        public CompletionStage<GetBackgroundColorsResult> getBackgroundColors(DOM.NodeId nodeId) {
            Map<String, Object> params = new LinkedHashMap<>();
            params.put("nodeId", CdpObject.json(nodeId));
            return client.call("CSS.getBackgroundColors", params, result_ -> new GetBackgroundColorsResult(result_));
        }
        /**
         * Returns the computed style for a DOM node identified by {@code nodeId}.
         * @param nodeId protocol value
         * @return a stage completing with the command result
         */
        public CompletionStage<GetComputedStyleForNodeResult> getComputedStyleForNode(DOM.NodeId nodeId) {
            Map<String, Object> params = new LinkedHashMap<>();
            params.put("nodeId", CdpObject.json(nodeId));
            return client.call("CSS.getComputedStyleForNode", params, result_ -> new GetComputedStyleForNodeResult(result_));
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
        public CompletionStage<java.util.List<String>> resolveValues(java.util.List<String> values, DOM.NodeId nodeId, Optional<String> propertyName, Optional<DOM.PseudoType> pseudoType, Optional<String> pseudoIdentifier) {
            Map<String, Object> params = new LinkedHashMap<>();
            params.put("values", CdpObject.json(values));
            params.put("nodeId", CdpObject.json(nodeId));
            propertyName.ifPresent(value_ -> params.put("propertyName", CdpObject.json(value_)));
            pseudoType.ifPresent(value_ -> params.put("pseudoType", CdpObject.json(value_)));
            pseudoIdentifier.ifPresent(value_ -> params.put("pseudoIdentifier", CdpObject.json(value_)));
            return client.call("CSS.resolveValues", params, result_ -> CdpObject.requireList(java.util.Objects.requireNonNull(result_.get("results")), element0 -> (String) element0));
        }
        /**
         * Resolve the specified values in the context of the provided element. For example, a value of &#x27;1em&#x27; is evaluated according to the computed &#x27;font-size&#x27; of the element and a value &#x27;calc(1px + 2px)&#x27; will be resolved to &#x27;3px&#x27;. If the {@code propertyName} was specified the {@code values} are resolved as if they were property&#x27;s declaration. If a value cannot be parsed according to the provided property syntax, the value is parsed using combined syntax as if null {@code propertyName} was provided. If the value cannot be resolved even then, return the provided value without any changes. Note: this function currently does not resolve CSS random() function, it returns unmodified random() function parts.`
         * <p><b>Experimental:</b> this part of CDP may change without notice.
         * @param values protocol value
         * @param nodeId protocol value
         * @return a stage completing with the command result
         */
        public CompletionStage<java.util.List<String>> resolveValues(java.util.List<String> values, DOM.NodeId nodeId) {
            return resolveValues(values, nodeId, Optional.empty(), Optional.empty(), Optional.empty());
        }
        /**
         * Invokes CSS.getLonghandProperties.
         * <p><b>Experimental:</b> this part of CDP may change without notice.
         * @param shorthandName protocol value
         * @param value protocol value
         * @return a stage completing with the command result
         */
        public CompletionStage<java.util.List<CSS.CSSProperty>> getLonghandProperties(String shorthandName, String value) {
            Map<String, Object> params = new LinkedHashMap<>();
            params.put("shorthandName", CdpObject.json(shorthandName));
            params.put("value", CdpObject.json(value));
            return client.call("CSS.getLonghandProperties", params, result_ -> CdpObject.requireList(java.util.Objects.requireNonNull(result_.get("longhandProperties")), element0 -> java.util.Objects.requireNonNull(CSS.CSSProperty.fromMap(java.util.Objects.requireNonNull(CdpObject.objectMap(element0))))));
        }
        /**
         * Returns the styles defined inline (explicitly in the &quot;style&quot; attribute and implicitly, using DOM attributes) for a DOM node identified by {@code nodeId}.
         * @param nodeId protocol value
         * @return a stage completing with the command result
         */
        public CompletionStage<GetInlineStylesForNodeResult> getInlineStylesForNode(DOM.NodeId nodeId) {
            Map<String, Object> params = new LinkedHashMap<>();
            params.put("nodeId", CdpObject.json(nodeId));
            return client.call("CSS.getInlineStylesForNode", params, result_ -> new GetInlineStylesForNodeResult(result_));
        }
        /**
         * Returns the styles coming from animations &amp; transitions including the animation &amp; transition styles coming from inheritance chain.
         * <p><b>Experimental:</b> this part of CDP may change without notice.
         * @param nodeId protocol value
         * @return a stage completing with the command result
         */
        public CompletionStage<GetAnimatedStylesForNodeResult> getAnimatedStylesForNode(DOM.NodeId nodeId) {
            Map<String, Object> params = new LinkedHashMap<>();
            params.put("nodeId", CdpObject.json(nodeId));
            return client.call("CSS.getAnimatedStylesForNode", params, result_ -> new GetAnimatedStylesForNodeResult(result_));
        }
        /**
         * Returns requested styles for a DOM node identified by {@code nodeId}.
         * @param nodeId protocol value
         * @return a stage completing with the command result
         */
        public CompletionStage<GetMatchedStylesForNodeResult> getMatchedStylesForNode(DOM.NodeId nodeId) {
            Map<String, Object> params = new LinkedHashMap<>();
            params.put("nodeId", CdpObject.json(nodeId));
            return client.call("CSS.getMatchedStylesForNode", params, result_ -> new GetMatchedStylesForNodeResult(result_));
        }
        /**
         * Returns the values of the default UA-defined environment variables used in env()
         * <p><b>Experimental:</b> this part of CDP may change without notice.
         * @return a stage completing with the command result
         */
        public CompletionStage<java.util.Map<String, Object>> getEnvironmentVariables() {
            return client.call("CSS.getEnvironmentVariables", null, result_ -> java.util.Objects.requireNonNull(CdpObject.objectMap(java.util.Objects.requireNonNull(result_.get("environmentVariables")))));
        }
        /**
         * Returns all media queries parsed by the rendering engine.
         * @return a stage completing with the command result
         */
        public CompletionStage<java.util.List<CSS.CSSMedia>> getMediaQueries() {
            return client.call("CSS.getMediaQueries", null, result_ -> CdpObject.requireList(java.util.Objects.requireNonNull(result_.get("medias")), element0 -> java.util.Objects.requireNonNull(CSS.CSSMedia.fromMap(java.util.Objects.requireNonNull(CdpObject.objectMap(element0))))));
        }
        /**
         * Requests information about platform fonts which we used to render child TextNodes in the given node.
         * @param nodeId protocol value
         * @return a stage completing with the command result
         */
        public CompletionStage<java.util.List<CSS.PlatformFontUsage>> getPlatformFontsForNode(DOM.NodeId nodeId) {
            Map<String, Object> params = new LinkedHashMap<>();
            params.put("nodeId", CdpObject.json(nodeId));
            return client.call("CSS.getPlatformFontsForNode", params, result_ -> CdpObject.requireList(java.util.Objects.requireNonNull(result_.get("fonts")), element0 -> java.util.Objects.requireNonNull(CSS.PlatformFontUsage.fromMap(java.util.Objects.requireNonNull(CdpObject.objectMap(element0))))));
        }
        /**
         * Returns the current textual content for a stylesheet.
         * @param styleSheetId protocol value
         * @return a stage completing with the command result
         */
        public CompletionStage<String> getStyleSheetText(DOM.StyleSheetId styleSheetId) {
            Map<String, Object> params = new LinkedHashMap<>();
            params.put("styleSheetId", CdpObject.json(styleSheetId));
            return client.call("CSS.getStyleSheetText", params, result_ -> (String) java.util.Objects.requireNonNull(result_.get("text")));
        }
        /**
         * Returns all layers parsed by the rendering engine for the tree scope of a node. Given a DOM element identified by nodeId, getLayersForNode returns the root layer for the nearest ancestor document or shadow root. The layer root contains the full layer tree for the tree scope and their ordering.
         * <p><b>Experimental:</b> this part of CDP may change without notice.
         * @param nodeId protocol value
         * @return a stage completing with the command result
         */
        public CompletionStage<CSS.CSSLayerData> getLayersForNode(DOM.NodeId nodeId) {
            Map<String, Object> params = new LinkedHashMap<>();
            params.put("nodeId", CdpObject.json(nodeId));
            return client.call("CSS.getLayersForNode", params, result_ -> java.util.Objects.requireNonNull(CSS.CSSLayerData.fromMap(java.util.Objects.requireNonNull(CdpObject.objectMap(java.util.Objects.requireNonNull(result_.get("rootLayer")))))));
        }
        /**
         * Given a CSS selector text and a style sheet ID, getLocationForSelector returns an array of locations of the CSS selector in the style sheet.
         * <p><b>Experimental:</b> this part of CDP may change without notice.
         * @param styleSheetId protocol value
         * @param selectorText protocol value
         * @return a stage completing with the command result
         */
        public CompletionStage<java.util.List<CSS.SourceRange>> getLocationForSelector(DOM.StyleSheetId styleSheetId, String selectorText) {
            Map<String, Object> params = new LinkedHashMap<>();
            params.put("styleSheetId", CdpObject.json(styleSheetId));
            params.put("selectorText", CdpObject.json(selectorText));
            return client.call("CSS.getLocationForSelector", params, result_ -> CdpObject.requireList(java.util.Objects.requireNonNull(result_.get("ranges")), element0 -> java.util.Objects.requireNonNull(CSS.SourceRange.fromMap(java.util.Objects.requireNonNull(CdpObject.objectMap(element0))))));
        }
        /**
         * Starts tracking the given node for the computed style updates and whenever the computed style is updated for node, it queues a {@code computedStyleUpdated} event with throttling. There can only be 1 node tracked for computed style updates so passing a new node id removes tracking from the previous node. Pass {@code undefined} to disable tracking.
         * <p><b>Experimental:</b> this part of CDP may change without notice.
         * @param nodeId protocol value
         * @return a stage completing when the command completes
         */
        public CompletionStage<Void> trackComputedStyleUpdatesForNode(Optional<DOM.NodeId> nodeId) {
            Map<String, Object> params = new LinkedHashMap<>();
            nodeId.ifPresent(value_ -> params.put("nodeId", CdpObject.json(value_)));
            return client.call("CSS.trackComputedStyleUpdatesForNode", params, result_ -> null);
        }
        /**
         * Starts tracking the given node for the computed style updates and whenever the computed style is updated for node, it queues a {@code computedStyleUpdated} event with throttling. There can only be 1 node tracked for computed style updates so passing a new node id removes tracking from the previous node. Pass {@code undefined} to disable tracking.
         * <p><b>Experimental:</b> this part of CDP may change without notice.
         * @return a stage completing when the command completes
         */
        public CompletionStage<Void> trackComputedStyleUpdatesForNode() {
            return trackComputedStyleUpdatesForNode(Optional.empty());
        }
        /**
         * Starts tracking the given computed styles for updates. The specified array of properties replaces the one previously specified. Pass empty array to disable tracking. Use takeComputedStyleUpdates to retrieve the list of nodes that had properties modified. The changes to computed style properties are only tracked for nodes pushed to the front-end by the DOM agent. If no changes to the tracked properties occur after the node has been pushed to the front-end, no updates will be issued for the node.
         * <p><b>Experimental:</b> this part of CDP may change without notice.
         * @param propertiesToTrack protocol value
         * @return a stage completing when the command completes
         */
        public CompletionStage<Void> trackComputedStyleUpdates(java.util.List<CSS.CSSComputedStyleProperty> propertiesToTrack) {
            Map<String, Object> params = new LinkedHashMap<>();
            params.put("propertiesToTrack", CdpObject.json(propertiesToTrack));
            return client.call("CSS.trackComputedStyleUpdates", params, result_ -> null);
        }
        /**
         * Polls the next batch of computed style updates.
         * <p><b>Experimental:</b> this part of CDP may change without notice.
         * @return a stage completing with the command result
         */
        public CompletionStage<java.util.List<DOM.NodeId>> takeComputedStyleUpdates() {
            return client.call("CSS.takeComputedStyleUpdates", null, result_ -> CdpObject.requireList(java.util.Objects.requireNonNull(result_.get("nodeIds")), element0 -> new DOM.NodeId(((Number) element0).longValue())));
        }
        /**
         * Find a rule with the given active property for the given node and set the new value for this property
         * @param nodeId protocol value
         * @param propertyName protocol value
         * @param value protocol value
         * @return a stage completing when the command completes
         */
        public CompletionStage<Void> setEffectivePropertyValueForNode(DOM.NodeId nodeId, String propertyName, String value) {
            Map<String, Object> params = new LinkedHashMap<>();
            params.put("nodeId", CdpObject.json(nodeId));
            params.put("propertyName", CdpObject.json(propertyName));
            params.put("value", CdpObject.json(value));
            return client.call("CSS.setEffectivePropertyValueForNode", params, result_ -> null);
        }
        /**
         * Modifies the property rule property name.
         * @param styleSheetId protocol value
         * @param range protocol value
         * @param propertyName protocol value
         * @return a stage completing with the command result
         */
        public CompletionStage<CSS.Value> setPropertyRulePropertyName(DOM.StyleSheetId styleSheetId, CSS.SourceRange range, String propertyName) {
            Map<String, Object> params = new LinkedHashMap<>();
            params.put("styleSheetId", CdpObject.json(styleSheetId));
            params.put("range", CdpObject.json(range));
            params.put("propertyName", CdpObject.json(propertyName));
            return client.call("CSS.setPropertyRulePropertyName", params, result_ -> java.util.Objects.requireNonNull(CSS.Value.fromMap(java.util.Objects.requireNonNull(CdpObject.objectMap(java.util.Objects.requireNonNull(result_.get("propertyName")))))));
        }
        /**
         * Modifies the keyframe rule key text.
         * @param styleSheetId protocol value
         * @param range protocol value
         * @param keyText protocol value
         * @return a stage completing with the command result
         */
        public CompletionStage<CSS.Value> setKeyframeKey(DOM.StyleSheetId styleSheetId, CSS.SourceRange range, String keyText) {
            Map<String, Object> params = new LinkedHashMap<>();
            params.put("styleSheetId", CdpObject.json(styleSheetId));
            params.put("range", CdpObject.json(range));
            params.put("keyText", CdpObject.json(keyText));
            return client.call("CSS.setKeyframeKey", params, result_ -> java.util.Objects.requireNonNull(CSS.Value.fromMap(java.util.Objects.requireNonNull(CdpObject.objectMap(java.util.Objects.requireNonNull(result_.get("keyText")))))));
        }
        /**
         * Modifies the rule selector.
         * @param styleSheetId protocol value
         * @param range protocol value
         * @param text protocol value
         * @return a stage completing with the command result
         */
        public CompletionStage<CSS.CSSMedia> setMediaText(DOM.StyleSheetId styleSheetId, CSS.SourceRange range, String text) {
            Map<String, Object> params = new LinkedHashMap<>();
            params.put("styleSheetId", CdpObject.json(styleSheetId));
            params.put("range", CdpObject.json(range));
            params.put("text", CdpObject.json(text));
            return client.call("CSS.setMediaText", params, result_ -> java.util.Objects.requireNonNull(CSS.CSSMedia.fromMap(java.util.Objects.requireNonNull(CdpObject.objectMap(java.util.Objects.requireNonNull(result_.get("media")))))));
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
            Map<String, Object> params = new LinkedHashMap<>();
            params.put("styleSheetId", CdpObject.json(styleSheetId));
            params.put("range", CdpObject.json(range));
            params.put("text", CdpObject.json(text));
            return client.call("CSS.setContainerQueryText", params, result_ -> java.util.Objects.requireNonNull(CSS.CSSContainerQuery.fromMap(java.util.Objects.requireNonNull(CdpObject.objectMap(java.util.Objects.requireNonNull(result_.get("containerQuery")))))));
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
            Map<String, Object> params = new LinkedHashMap<>();
            params.put("styleSheetId", CdpObject.json(styleSheetId));
            params.put("range", CdpObject.json(range));
            params.put("text", CdpObject.json(text));
            return client.call("CSS.setContainerQueryConditionText", params, result_ -> java.util.Objects.requireNonNull(CSS.CSSContainerQuery.fromMap(java.util.Objects.requireNonNull(CdpObject.objectMap(java.util.Objects.requireNonNull(result_.get("containerQuery")))))));
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
            Map<String, Object> params = new LinkedHashMap<>();
            params.put("styleSheetId", CdpObject.json(styleSheetId));
            params.put("range", CdpObject.json(range));
            params.put("text", CdpObject.json(text));
            return client.call("CSS.setSupportsText", params, result_ -> java.util.Objects.requireNonNull(CSS.CSSSupports.fromMap(java.util.Objects.requireNonNull(CdpObject.objectMap(java.util.Objects.requireNonNull(result_.get("supports")))))));
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
            Map<String, Object> params = new LinkedHashMap<>();
            params.put("styleSheetId", CdpObject.json(styleSheetId));
            params.put("range", CdpObject.json(range));
            params.put("text", CdpObject.json(text));
            return client.call("CSS.setNavigationText", params, result_ -> java.util.Objects.requireNonNull(CSS.CSSNavigation.fromMap(java.util.Objects.requireNonNull(CdpObject.objectMap(java.util.Objects.requireNonNull(result_.get("navigation")))))));
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
            Map<String, Object> params = new LinkedHashMap<>();
            params.put("styleSheetId", CdpObject.json(styleSheetId));
            params.put("range", CdpObject.json(range));
            params.put("text", CdpObject.json(text));
            return client.call("CSS.setScopeText", params, result_ -> java.util.Objects.requireNonNull(CSS.CSSScope.fromMap(java.util.Objects.requireNonNull(CdpObject.objectMap(java.util.Objects.requireNonNull(result_.get("scope")))))));
        }
        /**
         * Modifies the rule selector.
         * @param styleSheetId protocol value
         * @param range protocol value
         * @param selector protocol value
         * @return a stage completing with the command result
         */
        public CompletionStage<CSS.SelectorList> setRuleSelector(DOM.StyleSheetId styleSheetId, CSS.SourceRange range, String selector) {
            Map<String, Object> params = new LinkedHashMap<>();
            params.put("styleSheetId", CdpObject.json(styleSheetId));
            params.put("range", CdpObject.json(range));
            params.put("selector", CdpObject.json(selector));
            return client.call("CSS.setRuleSelector", params, result_ -> java.util.Objects.requireNonNull(CSS.SelectorList.fromMap(java.util.Objects.requireNonNull(CdpObject.objectMap(java.util.Objects.requireNonNull(result_.get("selectorList")))))));
        }
        /**
         * Sets the new stylesheet text.
         * @param styleSheetId protocol value
         * @param text protocol value
         * @return a stage completing with the command result
         */
        public CompletionStage<Optional<String>> setStyleSheetText(DOM.StyleSheetId styleSheetId, String text) {
            Map<String, Object> params = new LinkedHashMap<>();
            params.put("styleSheetId", CdpObject.json(styleSheetId));
            params.put("text", CdpObject.json(text));
            return client.call("CSS.setStyleSheetText", params, result_ -> Optional.ofNullable((String) result_.get("sourceMapURL")));
        }
        /**
         * Applies specified style edits one after another in the given order.
         * @param edits protocol value
         * @param nodeForPropertySyntaxValidation protocol value
         * @return a stage completing with the command result
         */
        public CompletionStage<java.util.List<CSS.CSSStyle>> setStyleTexts(java.util.List<CSS.StyleDeclarationEdit> edits, Optional<DOM.NodeId> nodeForPropertySyntaxValidation) {
            Map<String, Object> params = new LinkedHashMap<>();
            params.put("edits", CdpObject.json(edits));
            nodeForPropertySyntaxValidation.ifPresent(value_ -> params.put("nodeForPropertySyntaxValidation", CdpObject.json(value_)));
            return client.call("CSS.setStyleTexts", params, result_ -> CdpObject.requireList(java.util.Objects.requireNonNull(result_.get("styles")), element0 -> java.util.Objects.requireNonNull(CSS.CSSStyle.fromMap(java.util.Objects.requireNonNull(CdpObject.objectMap(element0))))));
        }
        /**
         * Applies specified style edits one after another in the given order.
         * @param edits protocol value
         * @return a stage completing with the command result
         */
        public CompletionStage<java.util.List<CSS.CSSStyle>> setStyleTexts(java.util.List<CSS.StyleDeclarationEdit> edits) {
            return setStyleTexts(edits, Optional.empty());
        }
        /**
         * Enables the selector recording.
         * @return a stage completing when the command completes
         */
        public CompletionStage<Void> startRuleUsageTracking() {
            return client.call("CSS.startRuleUsageTracking", null, result_ -> null);
        }
        /**
         * Stop tracking rule usage and return the list of rules that were used since last call to {@code takeCoverageDelta} (or since start of coverage instrumentation).
         * @return a stage completing with the command result
         */
        public CompletionStage<java.util.List<CSS.RuleUsage>> stopRuleUsageTracking() {
            return client.call("CSS.stopRuleUsageTracking", null, result_ -> CdpObject.requireList(java.util.Objects.requireNonNull(result_.get("ruleUsage")), element0 -> java.util.Objects.requireNonNull(CSS.RuleUsage.fromMap(java.util.Objects.requireNonNull(CdpObject.objectMap(element0))))));
        }
        /**
         * Obtain list of rules that became used since last call to this method (or since start of coverage instrumentation).
         * @return a stage completing with the command result
         */
        public CompletionStage<TakeCoverageDeltaResult> takeCoverageDelta() {
            return client.call("CSS.takeCoverageDelta", null, result_ -> new TakeCoverageDeltaResult(result_));
        }
        /**
         * Enables/disables rendering of local CSS fonts (enabled by default).
         * <p><b>Experimental:</b> this part of CDP may change without notice.
         * @param enabled protocol value
         * @return a stage completing when the command completes
         */
        public CompletionStage<Void> setLocalFontsEnabled(boolean enabled) {
            Map<String, Object> params = new LinkedHashMap<>();
            params.put("enabled", CdpObject.json(enabled));
            return client.call("CSS.setLocalFontsEnabled", params, result_ -> null);
        }
        /**
         * Fires whenever a web font is updated. A non-empty font parameter indicates a successfully loaded web font.
         * @param handler event callback
         * @return a removable subscription
         */
        public CdpSubscription onFontsUpdated(Consumer<FontsUpdatedEvent> handler) {
            return client.on("CSS.fontsUpdated", FontsUpdatedEvent::fromMap, handler);
        }
        /**
         * Fires whenever a MediaQuery result changes (for example, after a browser window has been resized.) The current implementation considers only viewport-dependent media features.
         * @param handler event callback
         * @return a removable subscription
         */
        public CdpSubscription onMediaQueryResultChanged(Consumer<MediaQueryResultChangedEvent> handler) {
            return client.on("CSS.mediaQueryResultChanged", MediaQueryResultChangedEvent::fromMap, handler);
        }
        /**
         * Fired whenever an active document stylesheet is added.
         * @param handler event callback
         * @return a removable subscription
         */
        public CdpSubscription onStyleSheetAdded(Consumer<StyleSheetAddedEvent> handler) {
            return client.on("CSS.styleSheetAdded", StyleSheetAddedEvent::fromMap, handler);
        }
        /**
         * Fired whenever a stylesheet is changed as a result of the client operation.
         * @param handler event callback
         * @return a removable subscription
         */
        public CdpSubscription onStyleSheetChanged(Consumer<StyleSheetChangedEvent> handler) {
            return client.on("CSS.styleSheetChanged", StyleSheetChangedEvent::fromMap, handler);
        }
        /**
         * Fired whenever an active document stylesheet is removed.
         * @param handler event callback
         * @return a removable subscription
         */
        public CdpSubscription onStyleSheetRemoved(Consumer<StyleSheetRemovedEvent> handler) {
            return client.on("CSS.styleSheetRemoved", StyleSheetRemovedEvent::fromMap, handler);
        }
        /**
         * Subscribes to CSS.computedStyleUpdated.
         * <p><b>Experimental:</b> this part of CDP may change without notice.
         * @param handler event callback
         * @return a removable subscription
         */
        public CdpSubscription onComputedStyleUpdated(Consumer<ComputedStyleUpdatedEvent> handler) {
            return client.on("CSS.computedStyleUpdated", ComputedStyleUpdatedEvent::fromMap, handler);
        }
    }
}
