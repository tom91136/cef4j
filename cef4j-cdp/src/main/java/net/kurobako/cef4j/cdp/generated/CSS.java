// GENERATED - do not edit. Regenerate via: mvn generate-sources -pl cef4j-platform -Dcef.version=150.0.18+gdb11278+chromium-150.0.7871.213
package net.kurobako.cef4j.cdp.generated;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.concurrent.CompletionStage;
import java.util.function.Consumer;
import javax.annotation.Nullable;
import javax.annotation.processing.Generated;
import net.kurobako.cef4j.cdp.CdpClient;
import net.kurobako.cef4j.cdp.CdpObject;
import net.kurobako.cef4j.cdp.CdpSubscription;

/**
 * This domain exposes CSS read/write operations. All CSS objects (stylesheets, rules, and styles) have an associated {@code id} used in subsequent operations on the related object. Each object type has a specific {@code id} structure, and those are not interchangeable between objects of different kinds. CSS objects can be loaded using the {@code get*ForNode()} calls (which accept a DOM node id). A client can also keep track of stylesheets via the {@code styleSheetAdded}/{@code styleSheetRemoved} events and subsequently load the required stylesheet contents using the {@code getStyleSheet[Text]()} methods.
 * <p><b>Experimental:</b> this part of CDP may change without notice.
 * @see <a href="https://chromium.googlesource.com/chromium/src/+/refs/tags/150.0.7871.213/third_party/blink/public/devtools_protocol/domains/CSS.pdl">Pinned protocol source</a>
 */
@Generated("mvn generate-sources -pl cef4j-platform -Dcef.version=150.0.18+gdb11278+chromium-150.0.7871.213")
@SuppressWarnings({"EscapedEntity", "JavaLangClash", "MissingSummary", "UnusedMethod"})
public final class CSS {
    private CSS() {}
    @Nullable private static Long numberAsLong(@Nullable Object value) { return value == null ? null : ((Number) value).longValue(); }
    @Nullable private static Double numberAsDouble(@Nullable Object value) { return value == null ? null : ((Number) value).doubleValue(); }
    /**
     * Stylesheet type: &quot;injected&quot; for stylesheets injected via extension, &quot;user-agent&quot; for user-agent stylesheets, &quot;inspector&quot; for stylesheets created by the inspector (i.e. those holding the &quot;via inspector&quot; rules), &quot;regular&quot; for regular stylesheets.
     */
    public static final class StyleSheetOrigin {
        private StyleSheetOrigin() {}
        public static final String INJECTED = "injected";
        public static final String USER_AGENT = "user-agent";
        public static final String INSPECTOR = "inspector";
        public static final String REGULAR = "regular";
    }
    /**
     * CSS rule collection for a single pseudo style.
     */
    public static final class PseudoElementMatches extends CdpObject {
        private PseudoElementMatches(Map<String, Object> values) { super(values); }
        @Nullable public static PseudoElementMatches fromMap(@Nullable Map<String, Object> values) {
            return values == null ? null : new PseudoElementMatches(values);
        }
        public static Builder builder() { return new Builder(); }
        /**
         * Pseudo element type.
         * @return the protocol field value
         */
        @Nullable public String pseudoType() {
            return (String) value("pseudoType");
        }
        /**
         * Pseudo element custom ident.
         * @return the protocol field value
         */
        @Nullable public String pseudoIdentifier() {
            return (String) value("pseudoIdentifier");
        }
        /**
         * Matches of CSS rules applicable to the pseudo style.
         * @return the protocol field value
         */
        @Nullable public java.util.List<CSS.RuleMatch> matches() {
            return list(value("matches"), element0 -> CSS.RuleMatch.fromMap(objectMap(element0)));
        }
        public static final class Builder {
            private final Map<String, Object> values = new LinkedHashMap<>();
            /**
             * Pseudo element type.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder pseudoType(@Nullable String value) {
                if (value == null) values.remove("pseudoType");
                else values.put("pseudoType", jsonValue(value));
                return this;
            }
            /**
             * Pseudo element custom ident.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder pseudoIdentifier(@Nullable String value) {
                if (value == null) values.remove("pseudoIdentifier");
                else values.put("pseudoIdentifier", jsonValue(value));
                return this;
            }
            /**
             * Matches of CSS rules applicable to the pseudo style.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder matches(@Nullable java.util.List<CSS.RuleMatch> value) {
                if (value == null) values.remove("matches");
                else values.put("matches", jsonValue(value));
                return this;
            }
            public PseudoElementMatches build() {
                if (!values.containsKey("pseudoType")) throw new IllegalStateException("Missing required CDP field: pseudoType");
                if (!values.containsKey("matches")) throw new IllegalStateException("Missing required CDP field: matches");
                return new PseudoElementMatches(values);
            }
        }
    }
    /**
     * CSS style coming from animations with the name of the animation.
     */
    public static final class CSSAnimationStyle extends CdpObject {
        private CSSAnimationStyle(Map<String, Object> values) { super(values); }
        @Nullable public static CSSAnimationStyle fromMap(@Nullable Map<String, Object> values) {
            return values == null ? null : new CSSAnimationStyle(values);
        }
        public static Builder builder() { return new Builder(); }
        /**
         * The name of the animation.
         * @return the protocol field value
         */
        @Nullable public String name() {
            return (String) value("name");
        }
        /**
         * The style coming from the animation.
         * @return the protocol field value
         */
        @Nullable public CSS.CSSStyle style() {
            return CSS.CSSStyle.fromMap(objectMap(value("style")));
        }
        public static final class Builder {
            private final Map<String, Object> values = new LinkedHashMap<>();
            /**
             * The name of the animation.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder name(@Nullable String value) {
                if (value == null) values.remove("name");
                else values.put("name", jsonValue(value));
                return this;
            }
            /**
             * The style coming from the animation.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder style(@Nullable CSS.CSSStyle value) {
                if (value == null) values.remove("style");
                else values.put("style", jsonValue(value));
                return this;
            }
            public CSSAnimationStyle build() {
                if (!values.containsKey("style")) throw new IllegalStateException("Missing required CDP field: style");
                return new CSSAnimationStyle(values);
            }
        }
    }
    /**
     * Inherited CSS rule collection from ancestor node.
     */
    public static final class InheritedStyleEntry extends CdpObject {
        private InheritedStyleEntry(Map<String, Object> values) { super(values); }
        @Nullable public static InheritedStyleEntry fromMap(@Nullable Map<String, Object> values) {
            return values == null ? null : new InheritedStyleEntry(values);
        }
        public static Builder builder() { return new Builder(); }
        /**
         * The ancestor node&#x27;s inline style, if any, in the style inheritance chain.
         * @return the protocol field value
         */
        @Nullable public CSS.CSSStyle inlineStyle() {
            return CSS.CSSStyle.fromMap(objectMap(value("inlineStyle")));
        }
        /**
         * Matches of CSS rules matching the ancestor node in the style inheritance chain.
         * @return the protocol field value
         */
        @Nullable public java.util.List<CSS.RuleMatch> matchedCSSRules() {
            return list(value("matchedCSSRules"), element0 -> CSS.RuleMatch.fromMap(objectMap(element0)));
        }
        public static final class Builder {
            private final Map<String, Object> values = new LinkedHashMap<>();
            /**
             * The ancestor node&#x27;s inline style, if any, in the style inheritance chain.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder inlineStyle(@Nullable CSS.CSSStyle value) {
                if (value == null) values.remove("inlineStyle");
                else values.put("inlineStyle", jsonValue(value));
                return this;
            }
            /**
             * Matches of CSS rules matching the ancestor node in the style inheritance chain.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder matchedCSSRules(@Nullable java.util.List<CSS.RuleMatch> value) {
                if (value == null) values.remove("matchedCSSRules");
                else values.put("matchedCSSRules", jsonValue(value));
                return this;
            }
            public InheritedStyleEntry build() {
                if (!values.containsKey("matchedCSSRules")) throw new IllegalStateException("Missing required CDP field: matchedCSSRules");
                return new InheritedStyleEntry(values);
            }
        }
    }
    /**
     * Inherited CSS style collection for animated styles from ancestor node.
     */
    public static final class InheritedAnimatedStyleEntry extends CdpObject {
        private InheritedAnimatedStyleEntry(Map<String, Object> values) { super(values); }
        @Nullable public static InheritedAnimatedStyleEntry fromMap(@Nullable Map<String, Object> values) {
            return values == null ? null : new InheritedAnimatedStyleEntry(values);
        }
        public static Builder builder() { return new Builder(); }
        /**
         * Styles coming from the animations of the ancestor, if any, in the style inheritance chain.
         * @return the protocol field value
         */
        @Nullable public java.util.List<CSS.CSSAnimationStyle> animationStyles() {
            return list(value("animationStyles"), element0 -> CSS.CSSAnimationStyle.fromMap(objectMap(element0)));
        }
        /**
         * The style coming from the transitions of the ancestor, if any, in the style inheritance chain.
         * @return the protocol field value
         */
        @Nullable public CSS.CSSStyle transitionsStyle() {
            return CSS.CSSStyle.fromMap(objectMap(value("transitionsStyle")));
        }
        public static final class Builder {
            private final Map<String, Object> values = new LinkedHashMap<>();
            /**
             * Styles coming from the animations of the ancestor, if any, in the style inheritance chain.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder animationStyles(@Nullable java.util.List<CSS.CSSAnimationStyle> value) {
                if (value == null) values.remove("animationStyles");
                else values.put("animationStyles", jsonValue(value));
                return this;
            }
            /**
             * The style coming from the transitions of the ancestor, if any, in the style inheritance chain.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder transitionsStyle(@Nullable CSS.CSSStyle value) {
                if (value == null) values.remove("transitionsStyle");
                else values.put("transitionsStyle", jsonValue(value));
                return this;
            }
            public InheritedAnimatedStyleEntry build() {
                return new InheritedAnimatedStyleEntry(values);
            }
        }
    }
    /**
     * Inherited pseudo element matches from pseudos of an ancestor node.
     */
    public static final class InheritedPseudoElementMatches extends CdpObject {
        private InheritedPseudoElementMatches(Map<String, Object> values) { super(values); }
        @Nullable public static InheritedPseudoElementMatches fromMap(@Nullable Map<String, Object> values) {
            return values == null ? null : new InheritedPseudoElementMatches(values);
        }
        public static Builder builder() { return new Builder(); }
        /**
         * Matches of pseudo styles from the pseudos of an ancestor node.
         * @return the protocol field value
         */
        @Nullable public java.util.List<CSS.PseudoElementMatches> pseudoElements() {
            return list(value("pseudoElements"), element0 -> CSS.PseudoElementMatches.fromMap(objectMap(element0)));
        }
        public static final class Builder {
            private final Map<String, Object> values = new LinkedHashMap<>();
            /**
             * Matches of pseudo styles from the pseudos of an ancestor node.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder pseudoElements(@Nullable java.util.List<CSS.PseudoElementMatches> value) {
                if (value == null) values.remove("pseudoElements");
                else values.put("pseudoElements", jsonValue(value));
                return this;
            }
            public InheritedPseudoElementMatches build() {
                if (!values.containsKey("pseudoElements")) throw new IllegalStateException("Missing required CDP field: pseudoElements");
                return new InheritedPseudoElementMatches(values);
            }
        }
    }
    /**
     * Match data for a CSS rule.
     */
    public static final class RuleMatch extends CdpObject {
        private RuleMatch(Map<String, Object> values) { super(values); }
        @Nullable public static RuleMatch fromMap(@Nullable Map<String, Object> values) {
            return values == null ? null : new RuleMatch(values);
        }
        public static Builder builder() { return new Builder(); }
        /**
         * CSS rule in the match.
         * @return the protocol field value
         */
        @Nullable public CSS.CSSRule rule() {
            return CSS.CSSRule.fromMap(objectMap(value("rule")));
        }
        /**
         * Matching selector indices in the rule&#x27;s selectorList selectors (0-based).
         * @return the protocol field value
         */
        @Nullable public java.util.List<Long> matchingSelectors() {
            return list(value("matchingSelectors"), element0 -> numberAsLong(element0));
        }
        public static final class Builder {
            private final Map<String, Object> values = new LinkedHashMap<>();
            /**
             * CSS rule in the match.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder rule(@Nullable CSS.CSSRule value) {
                if (value == null) values.remove("rule");
                else values.put("rule", jsonValue(value));
                return this;
            }
            /**
             * Matching selector indices in the rule&#x27;s selectorList selectors (0-based).
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder matchingSelectors(@Nullable java.util.List<Long> value) {
                if (value == null) values.remove("matchingSelectors");
                else values.put("matchingSelectors", jsonValue(value));
                return this;
            }
            public RuleMatch build() {
                if (!values.containsKey("rule")) throw new IllegalStateException("Missing required CDP field: rule");
                if (!values.containsKey("matchingSelectors")) throw new IllegalStateException("Missing required CDP field: matchingSelectors");
                return new RuleMatch(values);
            }
        }
    }
    /**
     * Data for a simple selector (these are delimited by commas in a selector list).
     */
    public static final class Value extends CdpObject {
        private Value(Map<String, Object> values) { super(values); }
        @Nullable public static Value fromMap(@Nullable Map<String, Object> values) {
            return values == null ? null : new Value(values);
        }
        public static Builder builder() { return new Builder(); }
        /**
         * Value text.
         * @return the protocol field value
         */
        @Nullable public String text() {
            return (String) value("text");
        }
        /**
         * Value range in the underlying resource (if available).
         * @return the protocol field value
         */
        @Nullable public CSS.SourceRange range() {
            return CSS.SourceRange.fromMap(objectMap(value("range")));
        }
        /**
         * Specificity of the selector.
         * <p><b>Experimental:</b> this part of CDP may change without notice.
         * @return the protocol field value
         */
        @Nullable public CSS.Specificity specificity() {
            return CSS.Specificity.fromMap(objectMap(value("specificity")));
        }
        public static final class Builder {
            private final Map<String, Object> values = new LinkedHashMap<>();
            /**
             * Value text.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder text(@Nullable String value) {
                if (value == null) values.remove("text");
                else values.put("text", jsonValue(value));
                return this;
            }
            /**
             * Value range in the underlying resource (if available).
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder range(@Nullable CSS.SourceRange value) {
                if (value == null) values.remove("range");
                else values.put("range", jsonValue(value));
                return this;
            }
            /**
             * Specificity of the selector.
             * <p><b>Experimental:</b> this part of CDP may change without notice.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder specificity(@Nullable CSS.Specificity value) {
                if (value == null) values.remove("specificity");
                else values.put("specificity", jsonValue(value));
                return this;
            }
            public Value build() {
                if (!values.containsKey("text")) throw new IllegalStateException("Missing required CDP field: text");
                return new Value(values);
            }
        }
    }
    /**
     * Specificity: https://drafts.csswg.org/selectors/#specificity-rules
     * <p><b>Experimental:</b> this part of CDP may change without notice.
     */
    public static final class Specificity extends CdpObject {
        private Specificity(Map<String, Object> values) { super(values); }
        @Nullable public static Specificity fromMap(@Nullable Map<String, Object> values) {
            return values == null ? null : new Specificity(values);
        }
        public static Builder builder() { return new Builder(); }
        /**
         * The a component, which represents the number of ID selectors.
         * @return the protocol field value
         */
        @Nullable public Long a() {
            return numberAsLong(value("a"));
        }
        /**
         * The b component, which represents the number of class selectors, attributes selectors, and pseudo-classes.
         * @return the protocol field value
         */
        @Nullable public Long b() {
            return numberAsLong(value("b"));
        }
        /**
         * The c component, which represents the number of type selectors and pseudo-elements.
         * @return the protocol field value
         */
        @Nullable public Long c() {
            return numberAsLong(value("c"));
        }
        public static final class Builder {
            private final Map<String, Object> values = new LinkedHashMap<>();
            /**
             * The a component, which represents the number of ID selectors.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder a(@Nullable Long value) {
                if (value == null) values.remove("a");
                else values.put("a", jsonValue(value));
                return this;
            }
            /**
             * The b component, which represents the number of class selectors, attributes selectors, and pseudo-classes.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder b(@Nullable Long value) {
                if (value == null) values.remove("b");
                else values.put("b", jsonValue(value));
                return this;
            }
            /**
             * The c component, which represents the number of type selectors and pseudo-elements.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder c(@Nullable Long value) {
                if (value == null) values.remove("c");
                else values.put("c", jsonValue(value));
                return this;
            }
            public Specificity build() {
                if (!values.containsKey("a")) throw new IllegalStateException("Missing required CDP field: a");
                if (!values.containsKey("b")) throw new IllegalStateException("Missing required CDP field: b");
                if (!values.containsKey("c")) throw new IllegalStateException("Missing required CDP field: c");
                return new Specificity(values);
            }
        }
    }
    /**
     * Selector list data.
     */
    public static final class SelectorList extends CdpObject {
        private SelectorList(Map<String, Object> values) { super(values); }
        @Nullable public static SelectorList fromMap(@Nullable Map<String, Object> values) {
            return values == null ? null : new SelectorList(values);
        }
        public static Builder builder() { return new Builder(); }
        /**
         * Selectors in the list.
         * @return the protocol field value
         */
        @Nullable public java.util.List<CSS.Value> selectors() {
            return list(value("selectors"), element0 -> CSS.Value.fromMap(objectMap(element0)));
        }
        /**
         * Rule selector text.
         * @return the protocol field value
         */
        @Nullable public String text() {
            return (String) value("text");
        }
        public static final class Builder {
            private final Map<String, Object> values = new LinkedHashMap<>();
            /**
             * Selectors in the list.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder selectors(@Nullable java.util.List<CSS.Value> value) {
                if (value == null) values.remove("selectors");
                else values.put("selectors", jsonValue(value));
                return this;
            }
            /**
             * Rule selector text.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder text(@Nullable String value) {
                if (value == null) values.remove("text");
                else values.put("text", jsonValue(value));
                return this;
            }
            public SelectorList build() {
                if (!values.containsKey("selectors")) throw new IllegalStateException("Missing required CDP field: selectors");
                if (!values.containsKey("text")) throw new IllegalStateException("Missing required CDP field: text");
                return new SelectorList(values);
            }
        }
    }
    /**
     * CSS stylesheet metainformation.
     */
    public static final class CSSStyleSheetHeader extends CdpObject {
        private CSSStyleSheetHeader(Map<String, Object> values) { super(values); }
        @Nullable public static CSSStyleSheetHeader fromMap(@Nullable Map<String, Object> values) {
            return values == null ? null : new CSSStyleSheetHeader(values);
        }
        public static Builder builder() { return new Builder(); }
        /**
         * The stylesheet identifier.
         * @return the protocol field value
         */
        @Nullable public String styleSheetId() {
            return (String) value("styleSheetId");
        }
        /**
         * Owner frame identifier.
         * @return the protocol field value
         */
        @Nullable public String frameId() {
            return (String) value("frameId");
        }
        /**
         * Stylesheet resource URL. Empty if this is a constructed stylesheet created using new CSSStyleSheet() (but non-empty if this is a constructed stylesheet imported as a CSS module script).
         * @return the protocol field value
         */
        @Nullable public String sourceURL() {
            return (String) value("sourceURL");
        }
        /**
         * URL of source map associated with the stylesheet (if any).
         * @return the protocol field value
         */
        @Nullable public String sourceMapURL() {
            return (String) value("sourceMapURL");
        }
        /**
         * Stylesheet origin.
         * @return the protocol field value
         */
        @Nullable public String origin() {
            return (String) value("origin");
        }
        /**
         * Stylesheet title.
         * @return the protocol field value
         */
        @Nullable public String title() {
            return (String) value("title");
        }
        /**
         * The backend id for the owner node of the stylesheet.
         * @return the protocol field value
         */
        @Nullable public Long ownerNode() {
            return numberAsLong(value("ownerNode"));
        }
        /**
         * Denotes whether the stylesheet is disabled.
         * @return the protocol field value
         */
        @Nullable public Boolean disabled() {
            return (Boolean) value("disabled");
        }
        /**
         * Whether the sourceURL field value comes from the sourceURL comment.
         * @return the protocol field value
         */
        @Nullable public Boolean hasSourceURL() {
            return (Boolean) value("hasSourceURL");
        }
        /**
         * Whether this stylesheet is created for STYLE tag by parser. This flag is not set for document.written STYLE tags.
         * @return the protocol field value
         */
        @Nullable public Boolean isInline() {
            return (Boolean) value("isInline");
        }
        /**
         * Whether this stylesheet is mutable. Inline stylesheets become mutable after they have been modified via CSSOM API. {@code &lt;link&gt;} element&#x27;s stylesheets become mutable only if DevTools modifies them. Constructed stylesheets (new CSSStyleSheet()) are mutable immediately after creation.
         * @return the protocol field value
         */
        @Nullable public Boolean isMutable() {
            return (Boolean) value("isMutable");
        }
        /**
         * True if this stylesheet is created through new CSSStyleSheet() or imported as a CSS module script.
         * @return the protocol field value
         */
        @Nullable public Boolean isConstructed() {
            return (Boolean) value("isConstructed");
        }
        /**
         * Line offset of the stylesheet within the resource (zero based).
         * @return the protocol field value
         */
        @Nullable public Double startLine() {
            return numberAsDouble(value("startLine"));
        }
        /**
         * Column offset of the stylesheet within the resource (zero based).
         * @return the protocol field value
         */
        @Nullable public Double startColumn() {
            return numberAsDouble(value("startColumn"));
        }
        /**
         * Size of the content (in characters).
         * @return the protocol field value
         */
        @Nullable public Double length() {
            return numberAsDouble(value("length"));
        }
        /**
         * Line offset of the end of the stylesheet within the resource (zero based).
         * @return the protocol field value
         */
        @Nullable public Double endLine() {
            return numberAsDouble(value("endLine"));
        }
        /**
         * Column offset of the end of the stylesheet within the resource (zero based).
         * @return the protocol field value
         */
        @Nullable public Double endColumn() {
            return numberAsDouble(value("endColumn"));
        }
        /**
         * If the style sheet was loaded from a network resource, this indicates when the resource failed to load
         * <p><b>Experimental:</b> this part of CDP may change without notice.
         * @return the protocol field value
         */
        @Nullable public Boolean loadingFailed() {
            return (Boolean) value("loadingFailed");
        }
        public static final class Builder {
            private final Map<String, Object> values = new LinkedHashMap<>();
            /**
             * The stylesheet identifier.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder styleSheetId(@Nullable String value) {
                if (value == null) values.remove("styleSheetId");
                else values.put("styleSheetId", jsonValue(value));
                return this;
            }
            /**
             * Owner frame identifier.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder frameId(@Nullable String value) {
                if (value == null) values.remove("frameId");
                else values.put("frameId", jsonValue(value));
                return this;
            }
            /**
             * Stylesheet resource URL. Empty if this is a constructed stylesheet created using new CSSStyleSheet() (but non-empty if this is a constructed stylesheet imported as a CSS module script).
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder sourceURL(@Nullable String value) {
                if (value == null) values.remove("sourceURL");
                else values.put("sourceURL", jsonValue(value));
                return this;
            }
            /**
             * URL of source map associated with the stylesheet (if any).
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder sourceMapURL(@Nullable String value) {
                if (value == null) values.remove("sourceMapURL");
                else values.put("sourceMapURL", jsonValue(value));
                return this;
            }
            /**
             * Stylesheet origin.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder origin(@Nullable String value) {
                if (value == null) values.remove("origin");
                else values.put("origin", jsonValue(value));
                return this;
            }
            /**
             * Stylesheet title.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder title(@Nullable String value) {
                if (value == null) values.remove("title");
                else values.put("title", jsonValue(value));
                return this;
            }
            /**
             * The backend id for the owner node of the stylesheet.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder ownerNode(@Nullable Long value) {
                if (value == null) values.remove("ownerNode");
                else values.put("ownerNode", jsonValue(value));
                return this;
            }
            /**
             * Denotes whether the stylesheet is disabled.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder disabled(@Nullable Boolean value) {
                if (value == null) values.remove("disabled");
                else values.put("disabled", jsonValue(value));
                return this;
            }
            /**
             * Whether the sourceURL field value comes from the sourceURL comment.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder hasSourceURL(@Nullable Boolean value) {
                if (value == null) values.remove("hasSourceURL");
                else values.put("hasSourceURL", jsonValue(value));
                return this;
            }
            /**
             * Whether this stylesheet is created for STYLE tag by parser. This flag is not set for document.written STYLE tags.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder isInline(@Nullable Boolean value) {
                if (value == null) values.remove("isInline");
                else values.put("isInline", jsonValue(value));
                return this;
            }
            /**
             * Whether this stylesheet is mutable. Inline stylesheets become mutable after they have been modified via CSSOM API. {@code &lt;link&gt;} element&#x27;s stylesheets become mutable only if DevTools modifies them. Constructed stylesheets (new CSSStyleSheet()) are mutable immediately after creation.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder isMutable(@Nullable Boolean value) {
                if (value == null) values.remove("isMutable");
                else values.put("isMutable", jsonValue(value));
                return this;
            }
            /**
             * True if this stylesheet is created through new CSSStyleSheet() or imported as a CSS module script.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder isConstructed(@Nullable Boolean value) {
                if (value == null) values.remove("isConstructed");
                else values.put("isConstructed", jsonValue(value));
                return this;
            }
            /**
             * Line offset of the stylesheet within the resource (zero based).
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder startLine(@Nullable Double value) {
                if (value == null) values.remove("startLine");
                else values.put("startLine", jsonValue(value));
                return this;
            }
            /**
             * Column offset of the stylesheet within the resource (zero based).
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder startColumn(@Nullable Double value) {
                if (value == null) values.remove("startColumn");
                else values.put("startColumn", jsonValue(value));
                return this;
            }
            /**
             * Size of the content (in characters).
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder length(@Nullable Double value) {
                if (value == null) values.remove("length");
                else values.put("length", jsonValue(value));
                return this;
            }
            /**
             * Line offset of the end of the stylesheet within the resource (zero based).
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder endLine(@Nullable Double value) {
                if (value == null) values.remove("endLine");
                else values.put("endLine", jsonValue(value));
                return this;
            }
            /**
             * Column offset of the end of the stylesheet within the resource (zero based).
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder endColumn(@Nullable Double value) {
                if (value == null) values.remove("endColumn");
                else values.put("endColumn", jsonValue(value));
                return this;
            }
            /**
             * If the style sheet was loaded from a network resource, this indicates when the resource failed to load
             * <p><b>Experimental:</b> this part of CDP may change without notice.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder loadingFailed(@Nullable Boolean value) {
                if (value == null) values.remove("loadingFailed");
                else values.put("loadingFailed", jsonValue(value));
                return this;
            }
            public CSSStyleSheetHeader build() {
                if (!values.containsKey("styleSheetId")) throw new IllegalStateException("Missing required CDP field: styleSheetId");
                if (!values.containsKey("frameId")) throw new IllegalStateException("Missing required CDP field: frameId");
                if (!values.containsKey("sourceURL")) throw new IllegalStateException("Missing required CDP field: sourceURL");
                if (!values.containsKey("origin")) throw new IllegalStateException("Missing required CDP field: origin");
                if (!values.containsKey("title")) throw new IllegalStateException("Missing required CDP field: title");
                if (!values.containsKey("disabled")) throw new IllegalStateException("Missing required CDP field: disabled");
                if (!values.containsKey("isInline")) throw new IllegalStateException("Missing required CDP field: isInline");
                if (!values.containsKey("isMutable")) throw new IllegalStateException("Missing required CDP field: isMutable");
                if (!values.containsKey("isConstructed")) throw new IllegalStateException("Missing required CDP field: isConstructed");
                if (!values.containsKey("startLine")) throw new IllegalStateException("Missing required CDP field: startLine");
                if (!values.containsKey("startColumn")) throw new IllegalStateException("Missing required CDP field: startColumn");
                if (!values.containsKey("length")) throw new IllegalStateException("Missing required CDP field: length");
                if (!values.containsKey("endLine")) throw new IllegalStateException("Missing required CDP field: endLine");
                if (!values.containsKey("endColumn")) throw new IllegalStateException("Missing required CDP field: endColumn");
                return new CSSStyleSheetHeader(values);
            }
        }
    }
    /**
     * CSS rule representation.
     */
    public static final class CSSRule extends CdpObject {
        private CSSRule(Map<String, Object> values) { super(values); }
        @Nullable public static CSSRule fromMap(@Nullable Map<String, Object> values) {
            return values == null ? null : new CSSRule(values);
        }
        public static Builder builder() { return new Builder(); }
        /**
         * The css style sheet identifier (absent for user agent stylesheet and user-specified stylesheet rules) this rule came from.
         * @return the protocol field value
         */
        @Nullable public String styleSheetId() {
            return (String) value("styleSheetId");
        }
        /**
         * Rule selector data.
         * @return the protocol field value
         */
        @Nullable public CSS.SelectorList selectorList() {
            return CSS.SelectorList.fromMap(objectMap(value("selectorList")));
        }
        /**
         * Array of selectors from ancestor style rules, sorted by distance from the current rule.
         * <p><b>Experimental:</b> this part of CDP may change without notice.
         * @return the protocol field value
         */
        @Nullable public java.util.List<String> nestingSelectors() {
            return list(value("nestingSelectors"), element0 -> (String) element0);
        }
        /**
         * Parent stylesheet&#x27;s origin.
         * @return the protocol field value
         */
        @Nullable public String origin() {
            return (String) value("origin");
        }
        /**
         * Associated style declaration.
         * @return the protocol field value
         */
        @Nullable public CSS.CSSStyle style() {
            return CSS.CSSStyle.fromMap(objectMap(value("style")));
        }
        /**
         * The BackendNodeId of the DOM node that constitutes the origin tree scope of this rule.
         * <p><b>Experimental:</b> this part of CDP may change without notice.
         * @return the protocol field value
         */
        @Nullable public Long originTreeScopeNodeId() {
            return numberAsLong(value("originTreeScopeNodeId"));
        }
        /**
         * Media list array (for rules involving media queries). The array enumerates media queries starting with the innermost one, going outwards.
         * @return the protocol field value
         */
        @Nullable public java.util.List<CSS.CSSMedia> media() {
            return list(value("media"), element0 -> CSS.CSSMedia.fromMap(objectMap(element0)));
        }
        /**
         * Container query list array (for rules involving container queries). The array enumerates container queries starting with the innermost one, going outwards.
         * <p><b>Experimental:</b> this part of CDP may change without notice.
         * @return the protocol field value
         */
        @Nullable public java.util.List<CSS.CSSContainerQuery> containerQueries() {
            return list(value("containerQueries"), element0 -> CSS.CSSContainerQuery.fromMap(objectMap(element0)));
        }
        /**
         * &#64;supports CSS at-rule array. The array enumerates &#64;supports at-rules starting with the innermost one, going outwards.
         * <p><b>Experimental:</b> this part of CDP may change without notice.
         * @return the protocol field value
         */
        @Nullable public java.util.List<CSS.CSSSupports> supports() {
            return list(value("supports"), element0 -> CSS.CSSSupports.fromMap(objectMap(element0)));
        }
        /**
         * Cascade layer array. Contains the layer hierarchy that this rule belongs to starting with the innermost layer and going outwards.
         * <p><b>Experimental:</b> this part of CDP may change without notice.
         * @return the protocol field value
         */
        @Nullable public java.util.List<CSS.CSSLayer> layers() {
            return list(value("layers"), element0 -> CSS.CSSLayer.fromMap(objectMap(element0)));
        }
        /**
         * &#64;scope CSS at-rule array. The array enumerates &#64;scope at-rules starting with the innermost one, going outwards.
         * <p><b>Experimental:</b> this part of CDP may change without notice.
         * @return the protocol field value
         */
        @Nullable public java.util.List<CSS.CSSScope> scopes() {
            return list(value("scopes"), element0 -> CSS.CSSScope.fromMap(objectMap(element0)));
        }
        /**
         * The array keeps the types of ancestor CSSRules from the innermost going outwards.
         * <p><b>Experimental:</b> this part of CDP may change without notice.
         * @return the protocol field value
         */
        @Nullable public java.util.List<String> ruleTypes() {
            return list(value("ruleTypes"), element0 -> (String) element0);
        }
        /**
         * &#64;starting-style CSS at-rule array. The array enumerates &#64;starting-style at-rules starting with the innermost one, going outwards.
         * <p><b>Experimental:</b> this part of CDP may change without notice.
         * @return the protocol field value
         */
        @Nullable public java.util.List<CSS.CSSStartingStyle> startingStyles() {
            return list(value("startingStyles"), element0 -> CSS.CSSStartingStyle.fromMap(objectMap(element0)));
        }
        /**
         * &#64;navigation CSS at-rule array. The array enumerates &#64;navigation at-rules starting with the innermost one, going outwards.
         * <p><b>Experimental:</b> this part of CDP may change without notice.
         * @return the protocol field value
         */
        @Nullable public java.util.List<CSS.CSSNavigation> navigations() {
            return list(value("navigations"), element0 -> CSS.CSSNavigation.fromMap(objectMap(element0)));
        }
        public static final class Builder {
            private final Map<String, Object> values = new LinkedHashMap<>();
            /**
             * The css style sheet identifier (absent for user agent stylesheet and user-specified stylesheet rules) this rule came from.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder styleSheetId(@Nullable String value) {
                if (value == null) values.remove("styleSheetId");
                else values.put("styleSheetId", jsonValue(value));
                return this;
            }
            /**
             * Rule selector data.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder selectorList(@Nullable CSS.SelectorList value) {
                if (value == null) values.remove("selectorList");
                else values.put("selectorList", jsonValue(value));
                return this;
            }
            /**
             * Array of selectors from ancestor style rules, sorted by distance from the current rule.
             * <p><b>Experimental:</b> this part of CDP may change without notice.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder nestingSelectors(@Nullable java.util.List<String> value) {
                if (value == null) values.remove("nestingSelectors");
                else values.put("nestingSelectors", jsonValue(value));
                return this;
            }
            /**
             * Parent stylesheet&#x27;s origin.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder origin(@Nullable String value) {
                if (value == null) values.remove("origin");
                else values.put("origin", jsonValue(value));
                return this;
            }
            /**
             * Associated style declaration.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder style(@Nullable CSS.CSSStyle value) {
                if (value == null) values.remove("style");
                else values.put("style", jsonValue(value));
                return this;
            }
            /**
             * The BackendNodeId of the DOM node that constitutes the origin tree scope of this rule.
             * <p><b>Experimental:</b> this part of CDP may change without notice.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder originTreeScopeNodeId(@Nullable Long value) {
                if (value == null) values.remove("originTreeScopeNodeId");
                else values.put("originTreeScopeNodeId", jsonValue(value));
                return this;
            }
            /**
             * Media list array (for rules involving media queries). The array enumerates media queries starting with the innermost one, going outwards.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder media(@Nullable java.util.List<CSS.CSSMedia> value) {
                if (value == null) values.remove("media");
                else values.put("media", jsonValue(value));
                return this;
            }
            /**
             * Container query list array (for rules involving container queries). The array enumerates container queries starting with the innermost one, going outwards.
             * <p><b>Experimental:</b> this part of CDP may change without notice.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder containerQueries(@Nullable java.util.List<CSS.CSSContainerQuery> value) {
                if (value == null) values.remove("containerQueries");
                else values.put("containerQueries", jsonValue(value));
                return this;
            }
            /**
             * &#64;supports CSS at-rule array. The array enumerates &#64;supports at-rules starting with the innermost one, going outwards.
             * <p><b>Experimental:</b> this part of CDP may change without notice.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder supports(@Nullable java.util.List<CSS.CSSSupports> value) {
                if (value == null) values.remove("supports");
                else values.put("supports", jsonValue(value));
                return this;
            }
            /**
             * Cascade layer array. Contains the layer hierarchy that this rule belongs to starting with the innermost layer and going outwards.
             * <p><b>Experimental:</b> this part of CDP may change without notice.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder layers(@Nullable java.util.List<CSS.CSSLayer> value) {
                if (value == null) values.remove("layers");
                else values.put("layers", jsonValue(value));
                return this;
            }
            /**
             * &#64;scope CSS at-rule array. The array enumerates &#64;scope at-rules starting with the innermost one, going outwards.
             * <p><b>Experimental:</b> this part of CDP may change without notice.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder scopes(@Nullable java.util.List<CSS.CSSScope> value) {
                if (value == null) values.remove("scopes");
                else values.put("scopes", jsonValue(value));
                return this;
            }
            /**
             * The array keeps the types of ancestor CSSRules from the innermost going outwards.
             * <p><b>Experimental:</b> this part of CDP may change without notice.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder ruleTypes(@Nullable java.util.List<String> value) {
                if (value == null) values.remove("ruleTypes");
                else values.put("ruleTypes", jsonValue(value));
                return this;
            }
            /**
             * &#64;starting-style CSS at-rule array. The array enumerates &#64;starting-style at-rules starting with the innermost one, going outwards.
             * <p><b>Experimental:</b> this part of CDP may change without notice.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder startingStyles(@Nullable java.util.List<CSS.CSSStartingStyle> value) {
                if (value == null) values.remove("startingStyles");
                else values.put("startingStyles", jsonValue(value));
                return this;
            }
            /**
             * &#64;navigation CSS at-rule array. The array enumerates &#64;navigation at-rules starting with the innermost one, going outwards.
             * <p><b>Experimental:</b> this part of CDP may change without notice.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder navigations(@Nullable java.util.List<CSS.CSSNavigation> value) {
                if (value == null) values.remove("navigations");
                else values.put("navigations", jsonValue(value));
                return this;
            }
            public CSSRule build() {
                if (!values.containsKey("selectorList")) throw new IllegalStateException("Missing required CDP field: selectorList");
                if (!values.containsKey("origin")) throw new IllegalStateException("Missing required CDP field: origin");
                if (!values.containsKey("style")) throw new IllegalStateException("Missing required CDP field: style");
                return new CSSRule(values);
            }
        }
    }
    /**
     * Enum indicating the type of a CSS rule, used to represent the order of a style rule&#x27;s ancestors. This list only contains rule types that are collected during the ancestor rule collection.
     * <p><b>Experimental:</b> this part of CDP may change without notice.
     */
    public static final class CSSRuleType {
        private CSSRuleType() {}
        public static final String MEDIARULE = "MediaRule";
        public static final String SUPPORTSRULE = "SupportsRule";
        public static final String CONTAINERRULE = "ContainerRule";
        public static final String LAYERRULE = "LayerRule";
        public static final String SCOPERULE = "ScopeRule";
        public static final String STYLERULE = "StyleRule";
        public static final String STARTINGSTYLERULE = "StartingStyleRule";
        public static final String NAVIGATIONRULE = "NavigationRule";
    }
    /**
     * CSS coverage information.
     */
    public static final class RuleUsage extends CdpObject {
        private RuleUsage(Map<String, Object> values) { super(values); }
        @Nullable public static RuleUsage fromMap(@Nullable Map<String, Object> values) {
            return values == null ? null : new RuleUsage(values);
        }
        public static Builder builder() { return new Builder(); }
        /**
         * The css style sheet identifier (absent for user agent stylesheet and user-specified stylesheet rules) this rule came from.
         * @return the protocol field value
         */
        @Nullable public String styleSheetId() {
            return (String) value("styleSheetId");
        }
        /**
         * Offset of the start of the rule (including selector) from the beginning of the stylesheet.
         * @return the protocol field value
         */
        @Nullable public Double startOffset() {
            return numberAsDouble(value("startOffset"));
        }
        /**
         * Offset of the end of the rule body from the beginning of the stylesheet.
         * @return the protocol field value
         */
        @Nullable public Double endOffset() {
            return numberAsDouble(value("endOffset"));
        }
        /**
         * Indicates whether the rule was actually used by some element in the page.
         * @return the protocol field value
         */
        @Nullable public Boolean used() {
            return (Boolean) value("used");
        }
        public static final class Builder {
            private final Map<String, Object> values = new LinkedHashMap<>();
            /**
             * The css style sheet identifier (absent for user agent stylesheet and user-specified stylesheet rules) this rule came from.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder styleSheetId(@Nullable String value) {
                if (value == null) values.remove("styleSheetId");
                else values.put("styleSheetId", jsonValue(value));
                return this;
            }
            /**
             * Offset of the start of the rule (including selector) from the beginning of the stylesheet.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder startOffset(@Nullable Double value) {
                if (value == null) values.remove("startOffset");
                else values.put("startOffset", jsonValue(value));
                return this;
            }
            /**
             * Offset of the end of the rule body from the beginning of the stylesheet.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder endOffset(@Nullable Double value) {
                if (value == null) values.remove("endOffset");
                else values.put("endOffset", jsonValue(value));
                return this;
            }
            /**
             * Indicates whether the rule was actually used by some element in the page.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder used(@Nullable Boolean value) {
                if (value == null) values.remove("used");
                else values.put("used", jsonValue(value));
                return this;
            }
            public RuleUsage build() {
                if (!values.containsKey("styleSheetId")) throw new IllegalStateException("Missing required CDP field: styleSheetId");
                if (!values.containsKey("startOffset")) throw new IllegalStateException("Missing required CDP field: startOffset");
                if (!values.containsKey("endOffset")) throw new IllegalStateException("Missing required CDP field: endOffset");
                if (!values.containsKey("used")) throw new IllegalStateException("Missing required CDP field: used");
                return new RuleUsage(values);
            }
        }
    }
    /**
     * Text range within a resource. All numbers are zero-based.
     */
    public static final class SourceRange extends CdpObject {
        private SourceRange(Map<String, Object> values) { super(values); }
        @Nullable public static SourceRange fromMap(@Nullable Map<String, Object> values) {
            return values == null ? null : new SourceRange(values);
        }
        public static Builder builder() { return new Builder(); }
        /**
         * Start line of range.
         * @return the protocol field value
         */
        @Nullable public Long startLine() {
            return numberAsLong(value("startLine"));
        }
        /**
         * Start column of range (inclusive).
         * @return the protocol field value
         */
        @Nullable public Long startColumn() {
            return numberAsLong(value("startColumn"));
        }
        /**
         * End line of range
         * @return the protocol field value
         */
        @Nullable public Long endLine() {
            return numberAsLong(value("endLine"));
        }
        /**
         * End column of range (exclusive).
         * @return the protocol field value
         */
        @Nullable public Long endColumn() {
            return numberAsLong(value("endColumn"));
        }
        public static final class Builder {
            private final Map<String, Object> values = new LinkedHashMap<>();
            /**
             * Start line of range.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder startLine(@Nullable Long value) {
                if (value == null) values.remove("startLine");
                else values.put("startLine", jsonValue(value));
                return this;
            }
            /**
             * Start column of range (inclusive).
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder startColumn(@Nullable Long value) {
                if (value == null) values.remove("startColumn");
                else values.put("startColumn", jsonValue(value));
                return this;
            }
            /**
             * End line of range
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder endLine(@Nullable Long value) {
                if (value == null) values.remove("endLine");
                else values.put("endLine", jsonValue(value));
                return this;
            }
            /**
             * End column of range (exclusive).
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder endColumn(@Nullable Long value) {
                if (value == null) values.remove("endColumn");
                else values.put("endColumn", jsonValue(value));
                return this;
            }
            public SourceRange build() {
                if (!values.containsKey("startLine")) throw new IllegalStateException("Missing required CDP field: startLine");
                if (!values.containsKey("startColumn")) throw new IllegalStateException("Missing required CDP field: startColumn");
                if (!values.containsKey("endLine")) throw new IllegalStateException("Missing required CDP field: endLine");
                if (!values.containsKey("endColumn")) throw new IllegalStateException("Missing required CDP field: endColumn");
                return new SourceRange(values);
            }
        }
    }
    /**
     */
    public static final class ShorthandEntry extends CdpObject {
        private ShorthandEntry(Map<String, Object> values) { super(values); }
        @Nullable public static ShorthandEntry fromMap(@Nullable Map<String, Object> values) {
            return values == null ? null : new ShorthandEntry(values);
        }
        public static Builder builder() { return new Builder(); }
        /**
         * Shorthand name.
         * @return the protocol field value
         */
        @Nullable public String name() {
            return (String) value("name");
        }
        /**
         * Shorthand value.
         * @return the protocol field value
         */
        @Nullable public String value() {
            return (String) value("value");
        }
        /**
         * Whether the property has &quot;!important&quot; annotation (implies {@code false} if absent).
         * @return the protocol field value
         */
        @Nullable public Boolean important() {
            return (Boolean) value("important");
        }
        public static final class Builder {
            private final Map<String, Object> values = new LinkedHashMap<>();
            /**
             * Shorthand name.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder name(@Nullable String value) {
                if (value == null) values.remove("name");
                else values.put("name", jsonValue(value));
                return this;
            }
            /**
             * Shorthand value.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder value(@Nullable String value) {
                if (value == null) values.remove("value");
                else values.put("value", jsonValue(value));
                return this;
            }
            /**
             * Whether the property has &quot;!important&quot; annotation (implies {@code false} if absent).
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder important(@Nullable Boolean value) {
                if (value == null) values.remove("important");
                else values.put("important", jsonValue(value));
                return this;
            }
            public ShorthandEntry build() {
                if (!values.containsKey("name")) throw new IllegalStateException("Missing required CDP field: name");
                if (!values.containsKey("value")) throw new IllegalStateException("Missing required CDP field: value");
                return new ShorthandEntry(values);
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
     * <p><b>Experimental:</b> this part of CDP may change without notice.
     */
    public static final class ComputedStyleExtraFields extends CdpObject {
        private ComputedStyleExtraFields(Map<String, Object> values) { super(values); }
        @Nullable public static ComputedStyleExtraFields fromMap(@Nullable Map<String, Object> values) {
            return values == null ? null : new ComputedStyleExtraFields(values);
        }
        public static Builder builder() { return new Builder(); }
        /**
         * Returns whether or not this node is being rendered with base appearance, which happens when it has its appearance property set to base/base-select or it is in the subtree of an element being rendered with base appearance.
         * @return the protocol field value
         */
        @Nullable public Boolean isAppearanceBase() {
            return (Boolean) value("isAppearanceBase");
        }
        public static final class Builder {
            private final Map<String, Object> values = new LinkedHashMap<>();
            /**
             * Returns whether or not this node is being rendered with base appearance, which happens when it has its appearance property set to base/base-select or it is in the subtree of an element being rendered with base appearance.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder isAppearanceBase(@Nullable Boolean value) {
                if (value == null) values.remove("isAppearanceBase");
                else values.put("isAppearanceBase", jsonValue(value));
                return this;
            }
            public ComputedStyleExtraFields build() {
                if (!values.containsKey("isAppearanceBase")) throw new IllegalStateException("Missing required CDP field: isAppearanceBase");
                return new ComputedStyleExtraFields(values);
            }
        }
    }
    /**
     * CSS style representation.
     */
    public static final class CSSStyle extends CdpObject {
        private CSSStyle(Map<String, Object> values) { super(values); }
        @Nullable public static CSSStyle fromMap(@Nullable Map<String, Object> values) {
            return values == null ? null : new CSSStyle(values);
        }
        public static Builder builder() { return new Builder(); }
        /**
         * The css style sheet identifier (absent for user agent stylesheet and user-specified stylesheet rules) this rule came from.
         * @return the protocol field value
         */
        @Nullable public String styleSheetId() {
            return (String) value("styleSheetId");
        }
        /**
         * CSS properties in the style.
         * @return the protocol field value
         */
        @Nullable public java.util.List<CSS.CSSProperty> cssProperties() {
            return list(value("cssProperties"), element0 -> CSS.CSSProperty.fromMap(objectMap(element0)));
        }
        /**
         * Computed values for all shorthands found in the style.
         * @return the protocol field value
         */
        @Nullable public java.util.List<CSS.ShorthandEntry> shorthandEntries() {
            return list(value("shorthandEntries"), element0 -> CSS.ShorthandEntry.fromMap(objectMap(element0)));
        }
        /**
         * Style declaration text (if available).
         * @return the protocol field value
         */
        @Nullable public String cssText() {
            return (String) value("cssText");
        }
        /**
         * Style declaration range in the enclosing stylesheet (if available).
         * @return the protocol field value
         */
        @Nullable public CSS.SourceRange range() {
            return CSS.SourceRange.fromMap(objectMap(value("range")));
        }
        public static final class Builder {
            private final Map<String, Object> values = new LinkedHashMap<>();
            /**
             * The css style sheet identifier (absent for user agent stylesheet and user-specified stylesheet rules) this rule came from.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder styleSheetId(@Nullable String value) {
                if (value == null) values.remove("styleSheetId");
                else values.put("styleSheetId", jsonValue(value));
                return this;
            }
            /**
             * CSS properties in the style.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder cssProperties(@Nullable java.util.List<CSS.CSSProperty> value) {
                if (value == null) values.remove("cssProperties");
                else values.put("cssProperties", jsonValue(value));
                return this;
            }
            /**
             * Computed values for all shorthands found in the style.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder shorthandEntries(@Nullable java.util.List<CSS.ShorthandEntry> value) {
                if (value == null) values.remove("shorthandEntries");
                else values.put("shorthandEntries", jsonValue(value));
                return this;
            }
            /**
             * Style declaration text (if available).
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder cssText(@Nullable String value) {
                if (value == null) values.remove("cssText");
                else values.put("cssText", jsonValue(value));
                return this;
            }
            /**
             * Style declaration range in the enclosing stylesheet (if available).
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder range(@Nullable CSS.SourceRange value) {
                if (value == null) values.remove("range");
                else values.put("range", jsonValue(value));
                return this;
            }
            public CSSStyle build() {
                if (!values.containsKey("cssProperties")) throw new IllegalStateException("Missing required CDP field: cssProperties");
                if (!values.containsKey("shorthandEntries")) throw new IllegalStateException("Missing required CDP field: shorthandEntries");
                return new CSSStyle(values);
            }
        }
    }
    /**
     * CSS property declaration data.
     */
    public static final class CSSProperty extends CdpObject {
        private CSSProperty(Map<String, Object> values) { super(values); }
        @Nullable public static CSSProperty fromMap(@Nullable Map<String, Object> values) {
            return values == null ? null : new CSSProperty(values);
        }
        public static Builder builder() { return new Builder(); }
        /**
         * The property name.
         * @return the protocol field value
         */
        @Nullable public String name() {
            return (String) value("name");
        }
        /**
         * The property value.
         * @return the protocol field value
         */
        @Nullable public String value() {
            return (String) value("value");
        }
        /**
         * Whether the property has &quot;!important&quot; annotation (implies {@code false} if absent).
         * @return the protocol field value
         */
        @Nullable public Boolean important() {
            return (Boolean) value("important");
        }
        /**
         * Whether the property is implicit (implies {@code false} if absent).
         * @return the protocol field value
         */
        @Nullable public Boolean implicit() {
            return (Boolean) value("implicit");
        }
        /**
         * The full property text as specified in the style.
         * @return the protocol field value
         */
        @Nullable public String text() {
            return (String) value("text");
        }
        /**
         * Whether the property is understood by the browser (implies {@code true} if absent).
         * @return the protocol field value
         */
        @Nullable public Boolean parsedOk() {
            return (Boolean) value("parsedOk");
        }
        /**
         * Whether the property is disabled by the user (present for source-based properties only).
         * @return the protocol field value
         */
        @Nullable public Boolean disabled() {
            return (Boolean) value("disabled");
        }
        /**
         * The entire property range in the enclosing style declaration (if available).
         * @return the protocol field value
         */
        @Nullable public CSS.SourceRange range() {
            return CSS.SourceRange.fromMap(objectMap(value("range")));
        }
        /**
         * Parsed longhand components of this property if it is a shorthand. This field will be empty if the given property is not a shorthand.
         * <p><b>Experimental:</b> this part of CDP may change without notice.
         * @return the protocol field value
         */
        @Nullable public java.util.List<CSS.CSSProperty> longhandProperties() {
            return list(value("longhandProperties"), element0 -> CSS.CSSProperty.fromMap(objectMap(element0)));
        }
        public static final class Builder {
            private final Map<String, Object> values = new LinkedHashMap<>();
            /**
             * The property name.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder name(@Nullable String value) {
                if (value == null) values.remove("name");
                else values.put("name", jsonValue(value));
                return this;
            }
            /**
             * The property value.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder value(@Nullable String value) {
                if (value == null) values.remove("value");
                else values.put("value", jsonValue(value));
                return this;
            }
            /**
             * Whether the property has &quot;!important&quot; annotation (implies {@code false} if absent).
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder important(@Nullable Boolean value) {
                if (value == null) values.remove("important");
                else values.put("important", jsonValue(value));
                return this;
            }
            /**
             * Whether the property is implicit (implies {@code false} if absent).
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder implicit(@Nullable Boolean value) {
                if (value == null) values.remove("implicit");
                else values.put("implicit", jsonValue(value));
                return this;
            }
            /**
             * The full property text as specified in the style.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder text(@Nullable String value) {
                if (value == null) values.remove("text");
                else values.put("text", jsonValue(value));
                return this;
            }
            /**
             * Whether the property is understood by the browser (implies {@code true} if absent).
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder parsedOk(@Nullable Boolean value) {
                if (value == null) values.remove("parsedOk");
                else values.put("parsedOk", jsonValue(value));
                return this;
            }
            /**
             * Whether the property is disabled by the user (present for source-based properties only).
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder disabled(@Nullable Boolean value) {
                if (value == null) values.remove("disabled");
                else values.put("disabled", jsonValue(value));
                return this;
            }
            /**
             * The entire property range in the enclosing style declaration (if available).
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder range(@Nullable CSS.SourceRange value) {
                if (value == null) values.remove("range");
                else values.put("range", jsonValue(value));
                return this;
            }
            /**
             * Parsed longhand components of this property if it is a shorthand. This field will be empty if the given property is not a shorthand.
             * <p><b>Experimental:</b> this part of CDP may change without notice.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder longhandProperties(@Nullable java.util.List<CSS.CSSProperty> value) {
                if (value == null) values.remove("longhandProperties");
                else values.put("longhandProperties", jsonValue(value));
                return this;
            }
            public CSSProperty build() {
                if (!values.containsKey("name")) throw new IllegalStateException("Missing required CDP field: name");
                if (!values.containsKey("value")) throw new IllegalStateException("Missing required CDP field: value");
                return new CSSProperty(values);
            }
        }
    }
    /**
     * CSS media rule descriptor.
     */
    public static final class CSSMedia extends CdpObject {
        private CSSMedia(Map<String, Object> values) { super(values); }
        @Nullable public static CSSMedia fromMap(@Nullable Map<String, Object> values) {
            return values == null ? null : new CSSMedia(values);
        }
        public static Builder builder() { return new Builder(); }
        /**
         * Media query text.
         * @return the protocol field value
         */
        @Nullable public String text() {
            return (String) value("text");
        }
        /**
         * Source of the media query: &quot;mediaRule&quot; if specified by a &#64;media rule, &quot;importRule&quot; if specified by an &#64;import rule, &quot;linkedSheet&quot; if specified by a &quot;media&quot; attribute in a linked stylesheet&#x27;s LINK tag, &quot;inlineSheet&quot; if specified by a &quot;media&quot; attribute in an inline stylesheet&#x27;s STYLE tag.
         * @return the protocol field value
         */
        @Nullable public String source() {
            return (String) value("source");
        }
        /**
         * Source of the media query: &quot;mediaRule&quot; if specified by a &#64;media rule, &quot;importRule&quot; if specified by an &#64;import rule, &quot;linkedSheet&quot; if specified by a &quot;media&quot; attribute in a linked stylesheet&#x27;s LINK tag, &quot;inlineSheet&quot; if specified by a &quot;media&quot; attribute in an inline stylesheet&#x27;s STYLE tag.
         */
        public static final class SourceValues {
            private SourceValues() {}
            public static final String MEDIARULE = "mediaRule";
            public static final String IMPORTRULE = "importRule";
            public static final String LINKEDSHEET = "linkedSheet";
            public static final String INLINESHEET = "inlineSheet";
        }
        /**
         * URL of the document containing the media query description.
         * @return the protocol field value
         */
        @Nullable public String sourceURL() {
            return (String) value("sourceURL");
        }
        /**
         * The associated rule (&#64;media or &#64;import) header range in the enclosing stylesheet (if available).
         * @return the protocol field value
         */
        @Nullable public CSS.SourceRange range() {
            return CSS.SourceRange.fromMap(objectMap(value("range")));
        }
        /**
         * Identifier of the stylesheet containing this object (if exists).
         * @return the protocol field value
         */
        @Nullable public String styleSheetId() {
            return (String) value("styleSheetId");
        }
        /**
         * Array of media queries.
         * @return the protocol field value
         */
        @Nullable public java.util.List<CSS.MediaQuery> mediaList() {
            return list(value("mediaList"), element0 -> CSS.MediaQuery.fromMap(objectMap(element0)));
        }
        public static final class Builder {
            private final Map<String, Object> values = new LinkedHashMap<>();
            /**
             * Media query text.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder text(@Nullable String value) {
                if (value == null) values.remove("text");
                else values.put("text", jsonValue(value));
                return this;
            }
            /**
             * Source of the media query: &quot;mediaRule&quot; if specified by a &#64;media rule, &quot;importRule&quot; if specified by an &#64;import rule, &quot;linkedSheet&quot; if specified by a &quot;media&quot; attribute in a linked stylesheet&#x27;s LINK tag, &quot;inlineSheet&quot; if specified by a &quot;media&quot; attribute in an inline stylesheet&#x27;s STYLE tag.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder source(@Nullable String value) {
                if (value == null) values.remove("source");
                else values.put("source", jsonValue(value));
                return this;
            }
            /**
             * URL of the document containing the media query description.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder sourceURL(@Nullable String value) {
                if (value == null) values.remove("sourceURL");
                else values.put("sourceURL", jsonValue(value));
                return this;
            }
            /**
             * The associated rule (&#64;media or &#64;import) header range in the enclosing stylesheet (if available).
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder range(@Nullable CSS.SourceRange value) {
                if (value == null) values.remove("range");
                else values.put("range", jsonValue(value));
                return this;
            }
            /**
             * Identifier of the stylesheet containing this object (if exists).
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder styleSheetId(@Nullable String value) {
                if (value == null) values.remove("styleSheetId");
                else values.put("styleSheetId", jsonValue(value));
                return this;
            }
            /**
             * Array of media queries.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder mediaList(@Nullable java.util.List<CSS.MediaQuery> value) {
                if (value == null) values.remove("mediaList");
                else values.put("mediaList", jsonValue(value));
                return this;
            }
            public CSSMedia build() {
                if (!values.containsKey("text")) throw new IllegalStateException("Missing required CDP field: text");
                if (!values.containsKey("source")) throw new IllegalStateException("Missing required CDP field: source");
                return new CSSMedia(values);
            }
        }
    }
    /**
     * Media query descriptor.
     */
    public static final class MediaQuery extends CdpObject {
        private MediaQuery(Map<String, Object> values) { super(values); }
        @Nullable public static MediaQuery fromMap(@Nullable Map<String, Object> values) {
            return values == null ? null : new MediaQuery(values);
        }
        public static Builder builder() { return new Builder(); }
        /**
         * Array of media query expressions.
         * @return the protocol field value
         */
        @Nullable public java.util.List<CSS.MediaQueryExpression> expressions() {
            return list(value("expressions"), element0 -> CSS.MediaQueryExpression.fromMap(objectMap(element0)));
        }
        /**
         * Whether the media query condition is satisfied.
         * @return the protocol field value
         */
        @Nullable public Boolean active() {
            return (Boolean) value("active");
        }
        public static final class Builder {
            private final Map<String, Object> values = new LinkedHashMap<>();
            /**
             * Array of media query expressions.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder expressions(@Nullable java.util.List<CSS.MediaQueryExpression> value) {
                if (value == null) values.remove("expressions");
                else values.put("expressions", jsonValue(value));
                return this;
            }
            /**
             * Whether the media query condition is satisfied.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder active(@Nullable Boolean value) {
                if (value == null) values.remove("active");
                else values.put("active", jsonValue(value));
                return this;
            }
            public MediaQuery build() {
                if (!values.containsKey("expressions")) throw new IllegalStateException("Missing required CDP field: expressions");
                if (!values.containsKey("active")) throw new IllegalStateException("Missing required CDP field: active");
                return new MediaQuery(values);
            }
        }
    }
    /**
     * Media query expression descriptor.
     */
    public static final class MediaQueryExpression extends CdpObject {
        private MediaQueryExpression(Map<String, Object> values) { super(values); }
        @Nullable public static MediaQueryExpression fromMap(@Nullable Map<String, Object> values) {
            return values == null ? null : new MediaQueryExpression(values);
        }
        public static Builder builder() { return new Builder(); }
        /**
         * Media query expression value.
         * @return the protocol field value
         */
        @Nullable public Double value() {
            return numberAsDouble(value("value"));
        }
        /**
         * Media query expression units.
         * @return the protocol field value
         */
        @Nullable public String unit() {
            return (String) value("unit");
        }
        /**
         * Media query expression feature.
         * @return the protocol field value
         */
        @Nullable public String feature() {
            return (String) value("feature");
        }
        /**
         * The associated range of the value text in the enclosing stylesheet (if available).
         * @return the protocol field value
         */
        @Nullable public CSS.SourceRange valueRange() {
            return CSS.SourceRange.fromMap(objectMap(value("valueRange")));
        }
        /**
         * Computed length of media query expression (if applicable).
         * @return the protocol field value
         */
        @Nullable public Double computedLength() {
            return numberAsDouble(value("computedLength"));
        }
        public static final class Builder {
            private final Map<String, Object> values = new LinkedHashMap<>();
            /**
             * Media query expression value.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder value(@Nullable Double value) {
                if (value == null) values.remove("value");
                else values.put("value", jsonValue(value));
                return this;
            }
            /**
             * Media query expression units.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder unit(@Nullable String value) {
                if (value == null) values.remove("unit");
                else values.put("unit", jsonValue(value));
                return this;
            }
            /**
             * Media query expression feature.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder feature(@Nullable String value) {
                if (value == null) values.remove("feature");
                else values.put("feature", jsonValue(value));
                return this;
            }
            /**
             * The associated range of the value text in the enclosing stylesheet (if available).
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder valueRange(@Nullable CSS.SourceRange value) {
                if (value == null) values.remove("valueRange");
                else values.put("valueRange", jsonValue(value));
                return this;
            }
            /**
             * Computed length of media query expression (if applicable).
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder computedLength(@Nullable Double value) {
                if (value == null) values.remove("computedLength");
                else values.put("computedLength", jsonValue(value));
                return this;
            }
            public MediaQueryExpression build() {
                if (!values.containsKey("value")) throw new IllegalStateException("Missing required CDP field: value");
                if (!values.containsKey("unit")) throw new IllegalStateException("Missing required CDP field: unit");
                if (!values.containsKey("feature")) throw new IllegalStateException("Missing required CDP field: feature");
                return new MediaQueryExpression(values);
            }
        }
    }
    /**
     * CSS container query rule descriptor.
     * <p><b>Experimental:</b> this part of CDP may change without notice.
     */
    public static final class CSSContainerQuery extends CdpObject {
        private CSSContainerQuery(Map<String, Object> values) { super(values); }
        @Nullable public static CSSContainerQuery fromMap(@Nullable Map<String, Object> values) {
            return values == null ? null : new CSSContainerQuery(values);
        }
        public static Builder builder() { return new Builder(); }
        /**
         * Container query text. Contains the query part without the container name for a single query. Deprecated in favor of conditionText which contains the full prelude after &#64;container.
         * @return the protocol field value
         * @deprecated Deprecated by the Chromium DevTools Protocol.
         */
        @Deprecated
        @Nullable public String text() {
            return (String) value("text");
        }
        /**
         * The associated rule header range in the enclosing stylesheet (if available).
         * @return the protocol field value
         */
        @Nullable public CSS.SourceRange range() {
            return CSS.SourceRange.fromMap(objectMap(value("range")));
        }
        /**
         * Identifier of the stylesheet containing this object (if exists).
         * @return the protocol field value
         */
        @Nullable public String styleSheetId() {
            return (String) value("styleSheetId");
        }
        /**
         * Optional name for the container.
         * @return the protocol field value
         */
        @Nullable public String name() {
            return (String) value("name");
        }
        /**
         * Optional physical axes queried for the container.
         * @return the protocol field value
         */
        @Nullable public String physicalAxes() {
            return (String) value("physicalAxes");
        }
        /**
         * Optional logical axes queried for the container.
         * @return the protocol field value
         */
        @Nullable public String logicalAxes() {
            return (String) value("logicalAxes");
        }
        /**
         * true if the query contains scroll-state() queries.
         * @return the protocol field value
         */
        @Nullable public Boolean queriesScrollState() {
            return (Boolean) value("queriesScrollState");
        }
        /**
         * true if the query contains anchored() queries.
         * @return the protocol field value
         */
        @Nullable public Boolean queriesAnchored() {
            return (Boolean) value("queriesAnchored");
        }
        /**
         * CSSContainerRule.conditionText
         * @return the protocol field value
         */
        @Nullable public String conditionText() {
            return (String) value("conditionText");
        }
        public static final class Builder {
            private final Map<String, Object> values = new LinkedHashMap<>();
            /**
             * Container query text. Contains the query part without the container name for a single query. Deprecated in favor of conditionText which contains the full prelude after &#64;container.
             * @param value field value; null removes an optional value
             * @return this builder
             * @deprecated Deprecated by the Chromium DevTools Protocol.
             */
            @Deprecated
            public Builder text(@Nullable String value) {
                if (value == null) values.remove("text");
                else values.put("text", jsonValue(value));
                return this;
            }
            /**
             * The associated rule header range in the enclosing stylesheet (if available).
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder range(@Nullable CSS.SourceRange value) {
                if (value == null) values.remove("range");
                else values.put("range", jsonValue(value));
                return this;
            }
            /**
             * Identifier of the stylesheet containing this object (if exists).
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder styleSheetId(@Nullable String value) {
                if (value == null) values.remove("styleSheetId");
                else values.put("styleSheetId", jsonValue(value));
                return this;
            }
            /**
             * Optional name for the container.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder name(@Nullable String value) {
                if (value == null) values.remove("name");
                else values.put("name", jsonValue(value));
                return this;
            }
            /**
             * Optional physical axes queried for the container.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder physicalAxes(@Nullable String value) {
                if (value == null) values.remove("physicalAxes");
                else values.put("physicalAxes", jsonValue(value));
                return this;
            }
            /**
             * Optional logical axes queried for the container.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder logicalAxes(@Nullable String value) {
                if (value == null) values.remove("logicalAxes");
                else values.put("logicalAxes", jsonValue(value));
                return this;
            }
            /**
             * true if the query contains scroll-state() queries.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder queriesScrollState(@Nullable Boolean value) {
                if (value == null) values.remove("queriesScrollState");
                else values.put("queriesScrollState", jsonValue(value));
                return this;
            }
            /**
             * true if the query contains anchored() queries.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder queriesAnchored(@Nullable Boolean value) {
                if (value == null) values.remove("queriesAnchored");
                else values.put("queriesAnchored", jsonValue(value));
                return this;
            }
            /**
             * CSSContainerRule.conditionText
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder conditionText(@Nullable String value) {
                if (value == null) values.remove("conditionText");
                else values.put("conditionText", jsonValue(value));
                return this;
            }
            public CSSContainerQuery build() {
                if (!values.containsKey("text")) throw new IllegalStateException("Missing required CDP field: text");
                if (!values.containsKey("conditionText")) throw new IllegalStateException("Missing required CDP field: conditionText");
                return new CSSContainerQuery(values);
            }
        }
    }
    /**
     * CSS Supports at-rule descriptor.
     * <p><b>Experimental:</b> this part of CDP may change without notice.
     */
    public static final class CSSSupports extends CdpObject {
        private CSSSupports(Map<String, Object> values) { super(values); }
        @Nullable public static CSSSupports fromMap(@Nullable Map<String, Object> values) {
            return values == null ? null : new CSSSupports(values);
        }
        public static Builder builder() { return new Builder(); }
        /**
         * Supports rule text.
         * @return the protocol field value
         */
        @Nullable public String text() {
            return (String) value("text");
        }
        /**
         * Whether the supports condition is satisfied.
         * @return the protocol field value
         */
        @Nullable public Boolean active() {
            return (Boolean) value("active");
        }
        /**
         * The associated rule header range in the enclosing stylesheet (if available).
         * @return the protocol field value
         */
        @Nullable public CSS.SourceRange range() {
            return CSS.SourceRange.fromMap(objectMap(value("range")));
        }
        /**
         * Identifier of the stylesheet containing this object (if exists).
         * @return the protocol field value
         */
        @Nullable public String styleSheetId() {
            return (String) value("styleSheetId");
        }
        public static final class Builder {
            private final Map<String, Object> values = new LinkedHashMap<>();
            /**
             * Supports rule text.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder text(@Nullable String value) {
                if (value == null) values.remove("text");
                else values.put("text", jsonValue(value));
                return this;
            }
            /**
             * Whether the supports condition is satisfied.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder active(@Nullable Boolean value) {
                if (value == null) values.remove("active");
                else values.put("active", jsonValue(value));
                return this;
            }
            /**
             * The associated rule header range in the enclosing stylesheet (if available).
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder range(@Nullable CSS.SourceRange value) {
                if (value == null) values.remove("range");
                else values.put("range", jsonValue(value));
                return this;
            }
            /**
             * Identifier of the stylesheet containing this object (if exists).
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder styleSheetId(@Nullable String value) {
                if (value == null) values.remove("styleSheetId");
                else values.put("styleSheetId", jsonValue(value));
                return this;
            }
            public CSSSupports build() {
                if (!values.containsKey("text")) throw new IllegalStateException("Missing required CDP field: text");
                if (!values.containsKey("active")) throw new IllegalStateException("Missing required CDP field: active");
                return new CSSSupports(values);
            }
        }
    }
    /**
     * CSS Navigation at-rule descriptor.
     * <p><b>Experimental:</b> this part of CDP may change without notice.
     */
    public static final class CSSNavigation extends CdpObject {
        private CSSNavigation(Map<String, Object> values) { super(values); }
        @Nullable public static CSSNavigation fromMap(@Nullable Map<String, Object> values) {
            return values == null ? null : new CSSNavigation(values);
        }
        public static Builder builder() { return new Builder(); }
        /**
         * Navigation rule text.
         * @return the protocol field value
         */
        @Nullable public String text() {
            return (String) value("text");
        }
        /**
         * Whether the navigation condition is satisfied.
         * @return the protocol field value
         */
        @Nullable public Boolean active() {
            return (Boolean) value("active");
        }
        /**
         * The associated rule header range in the enclosing stylesheet (if available).
         * @return the protocol field value
         */
        @Nullable public CSS.SourceRange range() {
            return CSS.SourceRange.fromMap(objectMap(value("range")));
        }
        /**
         * Identifier of the stylesheet containing this object (if exists).
         * @return the protocol field value
         */
        @Nullable public String styleSheetId() {
            return (String) value("styleSheetId");
        }
        public static final class Builder {
            private final Map<String, Object> values = new LinkedHashMap<>();
            /**
             * Navigation rule text.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder text(@Nullable String value) {
                if (value == null) values.remove("text");
                else values.put("text", jsonValue(value));
                return this;
            }
            /**
             * Whether the navigation condition is satisfied.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder active(@Nullable Boolean value) {
                if (value == null) values.remove("active");
                else values.put("active", jsonValue(value));
                return this;
            }
            /**
             * The associated rule header range in the enclosing stylesheet (if available).
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder range(@Nullable CSS.SourceRange value) {
                if (value == null) values.remove("range");
                else values.put("range", jsonValue(value));
                return this;
            }
            /**
             * Identifier of the stylesheet containing this object (if exists).
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder styleSheetId(@Nullable String value) {
                if (value == null) values.remove("styleSheetId");
                else values.put("styleSheetId", jsonValue(value));
                return this;
            }
            public CSSNavigation build() {
                if (!values.containsKey("text")) throw new IllegalStateException("Missing required CDP field: text");
                return new CSSNavigation(values);
            }
        }
    }
    /**
     * CSS Scope at-rule descriptor.
     * <p><b>Experimental:</b> this part of CDP may change without notice.
     */
    public static final class CSSScope extends CdpObject {
        private CSSScope(Map<String, Object> values) { super(values); }
        @Nullable public static CSSScope fromMap(@Nullable Map<String, Object> values) {
            return values == null ? null : new CSSScope(values);
        }
        public static Builder builder() { return new Builder(); }
        /**
         * Scope rule text.
         * @return the protocol field value
         */
        @Nullable public String text() {
            return (String) value("text");
        }
        /**
         * The associated rule header range in the enclosing stylesheet (if available).
         * @return the protocol field value
         */
        @Nullable public CSS.SourceRange range() {
            return CSS.SourceRange.fromMap(objectMap(value("range")));
        }
        /**
         * Identifier of the stylesheet containing this object (if exists).
         * @return the protocol field value
         */
        @Nullable public String styleSheetId() {
            return (String) value("styleSheetId");
        }
        public static final class Builder {
            private final Map<String, Object> values = new LinkedHashMap<>();
            /**
             * Scope rule text.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder text(@Nullable String value) {
                if (value == null) values.remove("text");
                else values.put("text", jsonValue(value));
                return this;
            }
            /**
             * The associated rule header range in the enclosing stylesheet (if available).
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder range(@Nullable CSS.SourceRange value) {
                if (value == null) values.remove("range");
                else values.put("range", jsonValue(value));
                return this;
            }
            /**
             * Identifier of the stylesheet containing this object (if exists).
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder styleSheetId(@Nullable String value) {
                if (value == null) values.remove("styleSheetId");
                else values.put("styleSheetId", jsonValue(value));
                return this;
            }
            public CSSScope build() {
                if (!values.containsKey("text")) throw new IllegalStateException("Missing required CDP field: text");
                return new CSSScope(values);
            }
        }
    }
    /**
     * CSS Layer at-rule descriptor.
     * <p><b>Experimental:</b> this part of CDP may change without notice.
     */
    public static final class CSSLayer extends CdpObject {
        private CSSLayer(Map<String, Object> values) { super(values); }
        @Nullable public static CSSLayer fromMap(@Nullable Map<String, Object> values) {
            return values == null ? null : new CSSLayer(values);
        }
        public static Builder builder() { return new Builder(); }
        /**
         * Layer name.
         * @return the protocol field value
         */
        @Nullable public String text() {
            return (String) value("text");
        }
        /**
         * The associated rule header range in the enclosing stylesheet (if available).
         * @return the protocol field value
         */
        @Nullable public CSS.SourceRange range() {
            return CSS.SourceRange.fromMap(objectMap(value("range")));
        }
        /**
         * Identifier of the stylesheet containing this object (if exists).
         * @return the protocol field value
         */
        @Nullable public String styleSheetId() {
            return (String) value("styleSheetId");
        }
        public static final class Builder {
            private final Map<String, Object> values = new LinkedHashMap<>();
            /**
             * Layer name.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder text(@Nullable String value) {
                if (value == null) values.remove("text");
                else values.put("text", jsonValue(value));
                return this;
            }
            /**
             * The associated rule header range in the enclosing stylesheet (if available).
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder range(@Nullable CSS.SourceRange value) {
                if (value == null) values.remove("range");
                else values.put("range", jsonValue(value));
                return this;
            }
            /**
             * Identifier of the stylesheet containing this object (if exists).
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder styleSheetId(@Nullable String value) {
                if (value == null) values.remove("styleSheetId");
                else values.put("styleSheetId", jsonValue(value));
                return this;
            }
            public CSSLayer build() {
                if (!values.containsKey("text")) throw new IllegalStateException("Missing required CDP field: text");
                return new CSSLayer(values);
            }
        }
    }
    /**
     * CSS Starting Style at-rule descriptor.
     * <p><b>Experimental:</b> this part of CDP may change without notice.
     */
    public static final class CSSStartingStyle extends CdpObject {
        private CSSStartingStyle(Map<String, Object> values) { super(values); }
        @Nullable public static CSSStartingStyle fromMap(@Nullable Map<String, Object> values) {
            return values == null ? null : new CSSStartingStyle(values);
        }
        public static Builder builder() { return new Builder(); }
        /**
         * The associated rule header range in the enclosing stylesheet (if available).
         * @return the protocol field value
         */
        @Nullable public CSS.SourceRange range() {
            return CSS.SourceRange.fromMap(objectMap(value("range")));
        }
        /**
         * Identifier of the stylesheet containing this object (if exists).
         * @return the protocol field value
         */
        @Nullable public String styleSheetId() {
            return (String) value("styleSheetId");
        }
        public static final class Builder {
            private final Map<String, Object> values = new LinkedHashMap<>();
            /**
             * The associated rule header range in the enclosing stylesheet (if available).
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder range(@Nullable CSS.SourceRange value) {
                if (value == null) values.remove("range");
                else values.put("range", jsonValue(value));
                return this;
            }
            /**
             * Identifier of the stylesheet containing this object (if exists).
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder styleSheetId(@Nullable String value) {
                if (value == null) values.remove("styleSheetId");
                else values.put("styleSheetId", jsonValue(value));
                return this;
            }
            public CSSStartingStyle build() {
                return new CSSStartingStyle(values);
            }
        }
    }
    /**
     * CSS Layer data.
     * <p><b>Experimental:</b> this part of CDP may change without notice.
     */
    public static final class CSSLayerData extends CdpObject {
        private CSSLayerData(Map<String, Object> values) { super(values); }
        @Nullable public static CSSLayerData fromMap(@Nullable Map<String, Object> values) {
            return values == null ? null : new CSSLayerData(values);
        }
        public static Builder builder() { return new Builder(); }
        /**
         * Layer name.
         * @return the protocol field value
         */
        @Nullable public String name() {
            return (String) value("name");
        }
        /**
         * Direct sub-layers
         * @return the protocol field value
         */
        @Nullable public java.util.List<CSS.CSSLayerData> subLayers() {
            return list(value("subLayers"), element0 -> CSS.CSSLayerData.fromMap(objectMap(element0)));
        }
        /**
         * Layer order. The order determines the order of the layer in the cascade order. A higher number has higher priority in the cascade order.
         * @return the protocol field value
         */
        @Nullable public Double order() {
            return numberAsDouble(value("order"));
        }
        public static final class Builder {
            private final Map<String, Object> values = new LinkedHashMap<>();
            /**
             * Layer name.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder name(@Nullable String value) {
                if (value == null) values.remove("name");
                else values.put("name", jsonValue(value));
                return this;
            }
            /**
             * Direct sub-layers
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder subLayers(@Nullable java.util.List<CSS.CSSLayerData> value) {
                if (value == null) values.remove("subLayers");
                else values.put("subLayers", jsonValue(value));
                return this;
            }
            /**
             * Layer order. The order determines the order of the layer in the cascade order. A higher number has higher priority in the cascade order.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder order(@Nullable Double value) {
                if (value == null) values.remove("order");
                else values.put("order", jsonValue(value));
                return this;
            }
            public CSSLayerData build() {
                if (!values.containsKey("name")) throw new IllegalStateException("Missing required CDP field: name");
                if (!values.containsKey("order")) throw new IllegalStateException("Missing required CDP field: order");
                return new CSSLayerData(values);
            }
        }
    }
    /**
     * Information about amount of glyphs that were rendered with given font.
     */
    public static final class PlatformFontUsage extends CdpObject {
        private PlatformFontUsage(Map<String, Object> values) { super(values); }
        @Nullable public static PlatformFontUsage fromMap(@Nullable Map<String, Object> values) {
            return values == null ? null : new PlatformFontUsage(values);
        }
        public static Builder builder() { return new Builder(); }
        /**
         * Font&#x27;s family name reported by platform.
         * @return the protocol field value
         */
        @Nullable public String familyName() {
            return (String) value("familyName");
        }
        /**
         * Font&#x27;s PostScript name reported by platform.
         * @return the protocol field value
         */
        @Nullable public String postScriptName() {
            return (String) value("postScriptName");
        }
        /**
         * Indicates if the font was downloaded or resolved locally.
         * @return the protocol field value
         */
        @Nullable public Boolean isCustomFont() {
            return (Boolean) value("isCustomFont");
        }
        /**
         * Amount of glyphs that were rendered with this font.
         * @return the protocol field value
         */
        @Nullable public Double glyphCount() {
            return numberAsDouble(value("glyphCount"));
        }
        public static final class Builder {
            private final Map<String, Object> values = new LinkedHashMap<>();
            /**
             * Font&#x27;s family name reported by platform.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder familyName(@Nullable String value) {
                if (value == null) values.remove("familyName");
                else values.put("familyName", jsonValue(value));
                return this;
            }
            /**
             * Font&#x27;s PostScript name reported by platform.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder postScriptName(@Nullable String value) {
                if (value == null) values.remove("postScriptName");
                else values.put("postScriptName", jsonValue(value));
                return this;
            }
            /**
             * Indicates if the font was downloaded or resolved locally.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder isCustomFont(@Nullable Boolean value) {
                if (value == null) values.remove("isCustomFont");
                else values.put("isCustomFont", jsonValue(value));
                return this;
            }
            /**
             * Amount of glyphs that were rendered with this font.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder glyphCount(@Nullable Double value) {
                if (value == null) values.remove("glyphCount");
                else values.put("glyphCount", jsonValue(value));
                return this;
            }
            public PlatformFontUsage build() {
                if (!values.containsKey("familyName")) throw new IllegalStateException("Missing required CDP field: familyName");
                if (!values.containsKey("postScriptName")) throw new IllegalStateException("Missing required CDP field: postScriptName");
                if (!values.containsKey("isCustomFont")) throw new IllegalStateException("Missing required CDP field: isCustomFont");
                if (!values.containsKey("glyphCount")) throw new IllegalStateException("Missing required CDP field: glyphCount");
                return new PlatformFontUsage(values);
            }
        }
    }
    /**
     * Information about font variation axes for variable fonts
     */
    public static final class FontVariationAxis extends CdpObject {
        private FontVariationAxis(Map<String, Object> values) { super(values); }
        @Nullable public static FontVariationAxis fromMap(@Nullable Map<String, Object> values) {
            return values == null ? null : new FontVariationAxis(values);
        }
        public static Builder builder() { return new Builder(); }
        /**
         * The font-variation-setting tag (a.k.a. &quot;axis tag&quot;).
         * @return the protocol field value
         */
        @Nullable public String tag() {
            return (String) value("tag");
        }
        /**
         * Human-readable variation name in the default language (normally, &quot;en&quot;).
         * @return the protocol field value
         */
        @Nullable public String name() {
            return (String) value("name");
        }
        /**
         * The minimum value (inclusive) the font supports for this tag.
         * @return the protocol field value
         */
        @Nullable public Double minValue() {
            return numberAsDouble(value("minValue"));
        }
        /**
         * The maximum value (inclusive) the font supports for this tag.
         * @return the protocol field value
         */
        @Nullable public Double maxValue() {
            return numberAsDouble(value("maxValue"));
        }
        /**
         * The default value.
         * @return the protocol field value
         */
        @Nullable public Double defaultValue() {
            return numberAsDouble(value("defaultValue"));
        }
        public static final class Builder {
            private final Map<String, Object> values = new LinkedHashMap<>();
            /**
             * The font-variation-setting tag (a.k.a. &quot;axis tag&quot;).
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder tag(@Nullable String value) {
                if (value == null) values.remove("tag");
                else values.put("tag", jsonValue(value));
                return this;
            }
            /**
             * Human-readable variation name in the default language (normally, &quot;en&quot;).
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder name(@Nullable String value) {
                if (value == null) values.remove("name");
                else values.put("name", jsonValue(value));
                return this;
            }
            /**
             * The minimum value (inclusive) the font supports for this tag.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder minValue(@Nullable Double value) {
                if (value == null) values.remove("minValue");
                else values.put("minValue", jsonValue(value));
                return this;
            }
            /**
             * The maximum value (inclusive) the font supports for this tag.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder maxValue(@Nullable Double value) {
                if (value == null) values.remove("maxValue");
                else values.put("maxValue", jsonValue(value));
                return this;
            }
            /**
             * The default value.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder defaultValue(@Nullable Double value) {
                if (value == null) values.remove("defaultValue");
                else values.put("defaultValue", jsonValue(value));
                return this;
            }
            public FontVariationAxis build() {
                if (!values.containsKey("tag")) throw new IllegalStateException("Missing required CDP field: tag");
                if (!values.containsKey("name")) throw new IllegalStateException("Missing required CDP field: name");
                if (!values.containsKey("minValue")) throw new IllegalStateException("Missing required CDP field: minValue");
                if (!values.containsKey("maxValue")) throw new IllegalStateException("Missing required CDP field: maxValue");
                if (!values.containsKey("defaultValue")) throw new IllegalStateException("Missing required CDP field: defaultValue");
                return new FontVariationAxis(values);
            }
        }
    }
    /**
     * Properties of a web font: https://www.w3.org/TR/2008/REC-CSS2-20080411/fonts.html#font-descriptions and additional information such as platformFontFamily and fontVariationAxes.
     */
    public static final class FontFace extends CdpObject {
        private FontFace(Map<String, Object> values) { super(values); }
        @Nullable public static FontFace fromMap(@Nullable Map<String, Object> values) {
            return values == null ? null : new FontFace(values);
        }
        public static Builder builder() { return new Builder(); }
        /**
         * The font-family.
         * @return the protocol field value
         */
        @Nullable public String fontFamily() {
            return (String) value("fontFamily");
        }
        /**
         * The font-style.
         * @return the protocol field value
         */
        @Nullable public String fontStyle() {
            return (String) value("fontStyle");
        }
        /**
         * The font-variant.
         * @return the protocol field value
         */
        @Nullable public String fontVariant() {
            return (String) value("fontVariant");
        }
        /**
         * The font-weight.
         * @return the protocol field value
         */
        @Nullable public String fontWeight() {
            return (String) value("fontWeight");
        }
        /**
         * The font-stretch.
         * @return the protocol field value
         */
        @Nullable public String fontStretch() {
            return (String) value("fontStretch");
        }
        /**
         * The font-display.
         * @return the protocol field value
         */
        @Nullable public String fontDisplay() {
            return (String) value("fontDisplay");
        }
        /**
         * The unicode-range.
         * @return the protocol field value
         */
        @Nullable public String unicodeRange() {
            return (String) value("unicodeRange");
        }
        /**
         * The src.
         * @return the protocol field value
         */
        @Nullable public String src() {
            return (String) value("src");
        }
        /**
         * The resolved platform font family
         * @return the protocol field value
         */
        @Nullable public String platformFontFamily() {
            return (String) value("platformFontFamily");
        }
        /**
         * Available variation settings (a.k.a. &quot;axes&quot;).
         * @return the protocol field value
         */
        @Nullable public java.util.List<CSS.FontVariationAxis> fontVariationAxes() {
            return list(value("fontVariationAxes"), element0 -> CSS.FontVariationAxis.fromMap(objectMap(element0)));
        }
        public static final class Builder {
            private final Map<String, Object> values = new LinkedHashMap<>();
            /**
             * The font-family.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder fontFamily(@Nullable String value) {
                if (value == null) values.remove("fontFamily");
                else values.put("fontFamily", jsonValue(value));
                return this;
            }
            /**
             * The font-style.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder fontStyle(@Nullable String value) {
                if (value == null) values.remove("fontStyle");
                else values.put("fontStyle", jsonValue(value));
                return this;
            }
            /**
             * The font-variant.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder fontVariant(@Nullable String value) {
                if (value == null) values.remove("fontVariant");
                else values.put("fontVariant", jsonValue(value));
                return this;
            }
            /**
             * The font-weight.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder fontWeight(@Nullable String value) {
                if (value == null) values.remove("fontWeight");
                else values.put("fontWeight", jsonValue(value));
                return this;
            }
            /**
             * The font-stretch.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder fontStretch(@Nullable String value) {
                if (value == null) values.remove("fontStretch");
                else values.put("fontStretch", jsonValue(value));
                return this;
            }
            /**
             * The font-display.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder fontDisplay(@Nullable String value) {
                if (value == null) values.remove("fontDisplay");
                else values.put("fontDisplay", jsonValue(value));
                return this;
            }
            /**
             * The unicode-range.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder unicodeRange(@Nullable String value) {
                if (value == null) values.remove("unicodeRange");
                else values.put("unicodeRange", jsonValue(value));
                return this;
            }
            /**
             * The src.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder src(@Nullable String value) {
                if (value == null) values.remove("src");
                else values.put("src", jsonValue(value));
                return this;
            }
            /**
             * The resolved platform font family
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder platformFontFamily(@Nullable String value) {
                if (value == null) values.remove("platformFontFamily");
                else values.put("platformFontFamily", jsonValue(value));
                return this;
            }
            /**
             * Available variation settings (a.k.a. &quot;axes&quot;).
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder fontVariationAxes(@Nullable java.util.List<CSS.FontVariationAxis> value) {
                if (value == null) values.remove("fontVariationAxes");
                else values.put("fontVariationAxes", jsonValue(value));
                return this;
            }
            public FontFace build() {
                if (!values.containsKey("fontFamily")) throw new IllegalStateException("Missing required CDP field: fontFamily");
                if (!values.containsKey("fontStyle")) throw new IllegalStateException("Missing required CDP field: fontStyle");
                if (!values.containsKey("fontVariant")) throw new IllegalStateException("Missing required CDP field: fontVariant");
                if (!values.containsKey("fontWeight")) throw new IllegalStateException("Missing required CDP field: fontWeight");
                if (!values.containsKey("fontStretch")) throw new IllegalStateException("Missing required CDP field: fontStretch");
                if (!values.containsKey("fontDisplay")) throw new IllegalStateException("Missing required CDP field: fontDisplay");
                if (!values.containsKey("unicodeRange")) throw new IllegalStateException("Missing required CDP field: unicodeRange");
                if (!values.containsKey("src")) throw new IllegalStateException("Missing required CDP field: src");
                if (!values.containsKey("platformFontFamily")) throw new IllegalStateException("Missing required CDP field: platformFontFamily");
                return new FontFace(values);
            }
        }
    }
    /**
     * CSS try rule representation.
     */
    public static final class CSSTryRule extends CdpObject {
        private CSSTryRule(Map<String, Object> values) { super(values); }
        @Nullable public static CSSTryRule fromMap(@Nullable Map<String, Object> values) {
            return values == null ? null : new CSSTryRule(values);
        }
        public static Builder builder() { return new Builder(); }
        /**
         * The css style sheet identifier (absent for user agent stylesheet and user-specified stylesheet rules) this rule came from.
         * @return the protocol field value
         */
        @Nullable public String styleSheetId() {
            return (String) value("styleSheetId");
        }
        /**
         * Parent stylesheet&#x27;s origin.
         * @return the protocol field value
         */
        @Nullable public String origin() {
            return (String) value("origin");
        }
        /**
         * Associated style declaration.
         * @return the protocol field value
         */
        @Nullable public CSS.CSSStyle style() {
            return CSS.CSSStyle.fromMap(objectMap(value("style")));
        }
        public static final class Builder {
            private final Map<String, Object> values = new LinkedHashMap<>();
            /**
             * The css style sheet identifier (absent for user agent stylesheet and user-specified stylesheet rules) this rule came from.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder styleSheetId(@Nullable String value) {
                if (value == null) values.remove("styleSheetId");
                else values.put("styleSheetId", jsonValue(value));
                return this;
            }
            /**
             * Parent stylesheet&#x27;s origin.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder origin(@Nullable String value) {
                if (value == null) values.remove("origin");
                else values.put("origin", jsonValue(value));
                return this;
            }
            /**
             * Associated style declaration.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder style(@Nullable CSS.CSSStyle value) {
                if (value == null) values.remove("style");
                else values.put("style", jsonValue(value));
                return this;
            }
            public CSSTryRule build() {
                if (!values.containsKey("origin")) throw new IllegalStateException("Missing required CDP field: origin");
                if (!values.containsKey("style")) throw new IllegalStateException("Missing required CDP field: style");
                return new CSSTryRule(values);
            }
        }
    }
    /**
     * CSS &#64;position-try rule representation.
     */
    public static final class CSSPositionTryRule extends CdpObject {
        private CSSPositionTryRule(Map<String, Object> values) { super(values); }
        @Nullable public static CSSPositionTryRule fromMap(@Nullable Map<String, Object> values) {
            return values == null ? null : new CSSPositionTryRule(values);
        }
        public static Builder builder() { return new Builder(); }
        /**
         * The prelude dashed-ident name
         * @return the protocol field value
         */
        @Nullable public CSS.Value name() {
            return CSS.Value.fromMap(objectMap(value("name")));
        }
        /**
         * The css style sheet identifier (absent for user agent stylesheet and user-specified stylesheet rules) this rule came from.
         * @return the protocol field value
         */
        @Nullable public String styleSheetId() {
            return (String) value("styleSheetId");
        }
        /**
         * Parent stylesheet&#x27;s origin.
         * @return the protocol field value
         */
        @Nullable public String origin() {
            return (String) value("origin");
        }
        /**
         * Associated style declaration.
         * @return the protocol field value
         */
        @Nullable public CSS.CSSStyle style() {
            return CSS.CSSStyle.fromMap(objectMap(value("style")));
        }
        /**
         * Returns the active field.
         * @return the protocol field value
         */
        @Nullable public Boolean active() {
            return (Boolean) value("active");
        }
        public static final class Builder {
            private final Map<String, Object> values = new LinkedHashMap<>();
            /**
             * The prelude dashed-ident name
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder name(@Nullable CSS.Value value) {
                if (value == null) values.remove("name");
                else values.put("name", jsonValue(value));
                return this;
            }
            /**
             * The css style sheet identifier (absent for user agent stylesheet and user-specified stylesheet rules) this rule came from.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder styleSheetId(@Nullable String value) {
                if (value == null) values.remove("styleSheetId");
                else values.put("styleSheetId", jsonValue(value));
                return this;
            }
            /**
             * Parent stylesheet&#x27;s origin.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder origin(@Nullable String value) {
                if (value == null) values.remove("origin");
                else values.put("origin", jsonValue(value));
                return this;
            }
            /**
             * Associated style declaration.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder style(@Nullable CSS.CSSStyle value) {
                if (value == null) values.remove("style");
                else values.put("style", jsonValue(value));
                return this;
            }
            /**
             * Sets the active field.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder active(@Nullable Boolean value) {
                if (value == null) values.remove("active");
                else values.put("active", jsonValue(value));
                return this;
            }
            public CSSPositionTryRule build() {
                if (!values.containsKey("name")) throw new IllegalStateException("Missing required CDP field: name");
                if (!values.containsKey("origin")) throw new IllegalStateException("Missing required CDP field: origin");
                if (!values.containsKey("style")) throw new IllegalStateException("Missing required CDP field: style");
                if (!values.containsKey("active")) throw new IllegalStateException("Missing required CDP field: active");
                return new CSSPositionTryRule(values);
            }
        }
    }
    /**
     * CSS keyframes rule representation.
     */
    public static final class CSSKeyframesRule extends CdpObject {
        private CSSKeyframesRule(Map<String, Object> values) { super(values); }
        @Nullable public static CSSKeyframesRule fromMap(@Nullable Map<String, Object> values) {
            return values == null ? null : new CSSKeyframesRule(values);
        }
        public static Builder builder() { return new Builder(); }
        /**
         * Animation name.
         * @return the protocol field value
         */
        @Nullable public CSS.Value animationName() {
            return CSS.Value.fromMap(objectMap(value("animationName")));
        }
        /**
         * List of keyframes.
         * @return the protocol field value
         */
        @Nullable public java.util.List<CSS.CSSKeyframeRule> keyframes() {
            return list(value("keyframes"), element0 -> CSS.CSSKeyframeRule.fromMap(objectMap(element0)));
        }
        public static final class Builder {
            private final Map<String, Object> values = new LinkedHashMap<>();
            /**
             * Animation name.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder animationName(@Nullable CSS.Value value) {
                if (value == null) values.remove("animationName");
                else values.put("animationName", jsonValue(value));
                return this;
            }
            /**
             * List of keyframes.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder keyframes(@Nullable java.util.List<CSS.CSSKeyframeRule> value) {
                if (value == null) values.remove("keyframes");
                else values.put("keyframes", jsonValue(value));
                return this;
            }
            public CSSKeyframesRule build() {
                if (!values.containsKey("animationName")) throw new IllegalStateException("Missing required CDP field: animationName");
                if (!values.containsKey("keyframes")) throw new IllegalStateException("Missing required CDP field: keyframes");
                return new CSSKeyframesRule(values);
            }
        }
    }
    /**
     * Representation of a custom property registration through CSS.registerProperty
     */
    public static final class CSSPropertyRegistration extends CdpObject {
        private CSSPropertyRegistration(Map<String, Object> values) { super(values); }
        @Nullable public static CSSPropertyRegistration fromMap(@Nullable Map<String, Object> values) {
            return values == null ? null : new CSSPropertyRegistration(values);
        }
        public static Builder builder() { return new Builder(); }
        /**
         * Returns the propertyName field.
         * @return the protocol field value
         */
        @Nullable public String propertyName() {
            return (String) value("propertyName");
        }
        /**
         * Returns the initialValue field.
         * @return the protocol field value
         */
        @Nullable public CSS.Value initialValue() {
            return CSS.Value.fromMap(objectMap(value("initialValue")));
        }
        /**
         * Returns the inherits field.
         * @return the protocol field value
         */
        @Nullable public Boolean inherits() {
            return (Boolean) value("inherits");
        }
        /**
         * Returns the syntax field.
         * @return the protocol field value
         */
        @Nullable public String syntax() {
            return (String) value("syntax");
        }
        public static final class Builder {
            private final Map<String, Object> values = new LinkedHashMap<>();
            /**
             * Sets the propertyName field.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder propertyName(@Nullable String value) {
                if (value == null) values.remove("propertyName");
                else values.put("propertyName", jsonValue(value));
                return this;
            }
            /**
             * Sets the initialValue field.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder initialValue(@Nullable CSS.Value value) {
                if (value == null) values.remove("initialValue");
                else values.put("initialValue", jsonValue(value));
                return this;
            }
            /**
             * Sets the inherits field.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder inherits(@Nullable Boolean value) {
                if (value == null) values.remove("inherits");
                else values.put("inherits", jsonValue(value));
                return this;
            }
            /**
             * Sets the syntax field.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder syntax(@Nullable String value) {
                if (value == null) values.remove("syntax");
                else values.put("syntax", jsonValue(value));
                return this;
            }
            public CSSPropertyRegistration build() {
                if (!values.containsKey("propertyName")) throw new IllegalStateException("Missing required CDP field: propertyName");
                if (!values.containsKey("inherits")) throw new IllegalStateException("Missing required CDP field: inherits");
                if (!values.containsKey("syntax")) throw new IllegalStateException("Missing required CDP field: syntax");
                return new CSSPropertyRegistration(values);
            }
        }
    }
    /**
     * CSS generic &#64;rule representation.
     */
    public static final class CSSAtRule extends CdpObject {
        private CSSAtRule(Map<String, Object> values) { super(values); }
        @Nullable public static CSSAtRule fromMap(@Nullable Map<String, Object> values) {
            return values == null ? null : new CSSAtRule(values);
        }
        public static Builder builder() { return new Builder(); }
        /**
         * Type of at-rule.
         * @return the protocol field value
         */
        @Nullable public String type() {
            return (String) value("type");
        }
        /**
         * Type of at-rule.
         */
        public static final class TypeValues {
            private TypeValues() {}
            public static final String FONT_FACE = "font-face";
            public static final String FONT_FEATURE_VALUES = "font-feature-values";
            public static final String FONT_PALETTE_VALUES = "font-palette-values";
            public static final String COUNTER_STYLE = "counter-style";
        }
        /**
         * Subsection of font-feature-values, if this is a subsection.
         * @return the protocol field value
         */
        @Nullable public String subsection() {
            return (String) value("subsection");
        }
        /**
         * Subsection of font-feature-values, if this is a subsection.
         */
        public static final class SubsectionValues {
            private SubsectionValues() {}
            public static final String SWASH = "swash";
            public static final String ANNOTATION = "annotation";
            public static final String ORNAMENTS = "ornaments";
            public static final String STYLISTIC = "stylistic";
            public static final String STYLESET = "styleset";
            public static final String CHARACTER_VARIANT = "character-variant";
        }
        /**
         * LINT.ThenChange(//third_party/blink/renderer/core/inspector/inspector_style_sheet.cc:FontVariantAlternatesFeatureType,//third_party/blink/renderer/core/inspector/inspector_css_agent.cc:FontVariantAlternatesFeatureType) Associated name, if applicable.
         * @return the protocol field value
         */
        @Nullable public CSS.Value name() {
            return CSS.Value.fromMap(objectMap(value("name")));
        }
        /**
         * The css style sheet identifier (absent for user agent stylesheet and user-specified stylesheet rules) this rule came from.
         * @return the protocol field value
         */
        @Nullable public String styleSheetId() {
            return (String) value("styleSheetId");
        }
        /**
         * Parent stylesheet&#x27;s origin.
         * @return the protocol field value
         */
        @Nullable public String origin() {
            return (String) value("origin");
        }
        /**
         * Associated style declaration.
         * @return the protocol field value
         */
        @Nullable public CSS.CSSStyle style() {
            return CSS.CSSStyle.fromMap(objectMap(value("style")));
        }
        public static final class Builder {
            private final Map<String, Object> values = new LinkedHashMap<>();
            /**
             * Type of at-rule.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder type(@Nullable String value) {
                if (value == null) values.remove("type");
                else values.put("type", jsonValue(value));
                return this;
            }
            /**
             * Subsection of font-feature-values, if this is a subsection.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder subsection(@Nullable String value) {
                if (value == null) values.remove("subsection");
                else values.put("subsection", jsonValue(value));
                return this;
            }
            /**
             * LINT.ThenChange(//third_party/blink/renderer/core/inspector/inspector_style_sheet.cc:FontVariantAlternatesFeatureType,//third_party/blink/renderer/core/inspector/inspector_css_agent.cc:FontVariantAlternatesFeatureType) Associated name, if applicable.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder name(@Nullable CSS.Value value) {
                if (value == null) values.remove("name");
                else values.put("name", jsonValue(value));
                return this;
            }
            /**
             * The css style sheet identifier (absent for user agent stylesheet and user-specified stylesheet rules) this rule came from.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder styleSheetId(@Nullable String value) {
                if (value == null) values.remove("styleSheetId");
                else values.put("styleSheetId", jsonValue(value));
                return this;
            }
            /**
             * Parent stylesheet&#x27;s origin.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder origin(@Nullable String value) {
                if (value == null) values.remove("origin");
                else values.put("origin", jsonValue(value));
                return this;
            }
            /**
             * Associated style declaration.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder style(@Nullable CSS.CSSStyle value) {
                if (value == null) values.remove("style");
                else values.put("style", jsonValue(value));
                return this;
            }
            public CSSAtRule build() {
                if (!values.containsKey("type")) throw new IllegalStateException("Missing required CDP field: type");
                if (!values.containsKey("origin")) throw new IllegalStateException("Missing required CDP field: origin");
                if (!values.containsKey("style")) throw new IllegalStateException("Missing required CDP field: style");
                return new CSSAtRule(values);
            }
        }
    }
    /**
     * CSS property at-rule representation.
     */
    public static final class CSSPropertyRule extends CdpObject {
        private CSSPropertyRule(Map<String, Object> values) { super(values); }
        @Nullable public static CSSPropertyRule fromMap(@Nullable Map<String, Object> values) {
            return values == null ? null : new CSSPropertyRule(values);
        }
        public static Builder builder() { return new Builder(); }
        /**
         * The css style sheet identifier (absent for user agent stylesheet and user-specified stylesheet rules) this rule came from.
         * @return the protocol field value
         */
        @Nullable public String styleSheetId() {
            return (String) value("styleSheetId");
        }
        /**
         * Parent stylesheet&#x27;s origin.
         * @return the protocol field value
         */
        @Nullable public String origin() {
            return (String) value("origin");
        }
        /**
         * Associated property name.
         * @return the protocol field value
         */
        @Nullable public CSS.Value propertyName() {
            return CSS.Value.fromMap(objectMap(value("propertyName")));
        }
        /**
         * Associated style declaration.
         * @return the protocol field value
         */
        @Nullable public CSS.CSSStyle style() {
            return CSS.CSSStyle.fromMap(objectMap(value("style")));
        }
        public static final class Builder {
            private final Map<String, Object> values = new LinkedHashMap<>();
            /**
             * The css style sheet identifier (absent for user agent stylesheet and user-specified stylesheet rules) this rule came from.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder styleSheetId(@Nullable String value) {
                if (value == null) values.remove("styleSheetId");
                else values.put("styleSheetId", jsonValue(value));
                return this;
            }
            /**
             * Parent stylesheet&#x27;s origin.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder origin(@Nullable String value) {
                if (value == null) values.remove("origin");
                else values.put("origin", jsonValue(value));
                return this;
            }
            /**
             * Associated property name.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder propertyName(@Nullable CSS.Value value) {
                if (value == null) values.remove("propertyName");
                else values.put("propertyName", jsonValue(value));
                return this;
            }
            /**
             * Associated style declaration.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder style(@Nullable CSS.CSSStyle value) {
                if (value == null) values.remove("style");
                else values.put("style", jsonValue(value));
                return this;
            }
            public CSSPropertyRule build() {
                if (!values.containsKey("origin")) throw new IllegalStateException("Missing required CDP field: origin");
                if (!values.containsKey("propertyName")) throw new IllegalStateException("Missing required CDP field: propertyName");
                if (!values.containsKey("style")) throw new IllegalStateException("Missing required CDP field: style");
                return new CSSPropertyRule(values);
            }
        }
    }
    /**
     * CSS function argument representation.
     */
    public static final class CSSFunctionParameter extends CdpObject {
        private CSSFunctionParameter(Map<String, Object> values) { super(values); }
        @Nullable public static CSSFunctionParameter fromMap(@Nullable Map<String, Object> values) {
            return values == null ? null : new CSSFunctionParameter(values);
        }
        public static Builder builder() { return new Builder(); }
        /**
         * The parameter name.
         * @return the protocol field value
         */
        @Nullable public String name() {
            return (String) value("name");
        }
        /**
         * The parameter type.
         * @return the protocol field value
         */
        @Nullable public String type() {
            return (String) value("type");
        }
        public static final class Builder {
            private final Map<String, Object> values = new LinkedHashMap<>();
            /**
             * The parameter name.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder name(@Nullable String value) {
                if (value == null) values.remove("name");
                else values.put("name", jsonValue(value));
                return this;
            }
            /**
             * The parameter type.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder type(@Nullable String value) {
                if (value == null) values.remove("type");
                else values.put("type", jsonValue(value));
                return this;
            }
            public CSSFunctionParameter build() {
                if (!values.containsKey("name")) throw new IllegalStateException("Missing required CDP field: name");
                if (!values.containsKey("type")) throw new IllegalStateException("Missing required CDP field: type");
                return new CSSFunctionParameter(values);
            }
        }
    }
    /**
     * CSS function conditional block representation.
     */
    public static final class CSSFunctionConditionNode extends CdpObject {
        private CSSFunctionConditionNode(Map<String, Object> values) { super(values); }
        @Nullable public static CSSFunctionConditionNode fromMap(@Nullable Map<String, Object> values) {
            return values == null ? null : new CSSFunctionConditionNode(values);
        }
        public static Builder builder() { return new Builder(); }
        /**
         * Media query for this conditional block. Only one type of condition should be set.
         * @return the protocol field value
         */
        @Nullable public CSS.CSSMedia media() {
            return CSS.CSSMedia.fromMap(objectMap(value("media")));
        }
        /**
         * Container query for this conditional block. Only one type of condition should be set.
         * @return the protocol field value
         */
        @Nullable public CSS.CSSContainerQuery containerQueries() {
            return CSS.CSSContainerQuery.fromMap(objectMap(value("containerQueries")));
        }
        /**
         * &#64;supports CSS at-rule condition. Only one type of condition should be set.
         * @return the protocol field value
         */
        @Nullable public CSS.CSSSupports supports() {
            return CSS.CSSSupports.fromMap(objectMap(value("supports")));
        }
        /**
         * &#64;navigation condition. Only one type of condition should be set.
         * @return the protocol field value
         */
        @Nullable public CSS.CSSNavigation navigation() {
            return CSS.CSSNavigation.fromMap(objectMap(value("navigation")));
        }
        /**
         * Block body.
         * @return the protocol field value
         */
        @Nullable public java.util.List<CSS.CSSFunctionNode> children() {
            return list(value("children"), element0 -> CSS.CSSFunctionNode.fromMap(objectMap(element0)));
        }
        /**
         * The condition text.
         * @return the protocol field value
         */
        @Nullable public String conditionText() {
            return (String) value("conditionText");
        }
        public static final class Builder {
            private final Map<String, Object> values = new LinkedHashMap<>();
            /**
             * Media query for this conditional block. Only one type of condition should be set.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder media(@Nullable CSS.CSSMedia value) {
                if (value == null) values.remove("media");
                else values.put("media", jsonValue(value));
                return this;
            }
            /**
             * Container query for this conditional block. Only one type of condition should be set.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder containerQueries(@Nullable CSS.CSSContainerQuery value) {
                if (value == null) values.remove("containerQueries");
                else values.put("containerQueries", jsonValue(value));
                return this;
            }
            /**
             * &#64;supports CSS at-rule condition. Only one type of condition should be set.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder supports(@Nullable CSS.CSSSupports value) {
                if (value == null) values.remove("supports");
                else values.put("supports", jsonValue(value));
                return this;
            }
            /**
             * &#64;navigation condition. Only one type of condition should be set.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder navigation(@Nullable CSS.CSSNavigation value) {
                if (value == null) values.remove("navigation");
                else values.put("navigation", jsonValue(value));
                return this;
            }
            /**
             * Block body.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder children(@Nullable java.util.List<CSS.CSSFunctionNode> value) {
                if (value == null) values.remove("children");
                else values.put("children", jsonValue(value));
                return this;
            }
            /**
             * The condition text.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder conditionText(@Nullable String value) {
                if (value == null) values.remove("conditionText");
                else values.put("conditionText", jsonValue(value));
                return this;
            }
            public CSSFunctionConditionNode build() {
                if (!values.containsKey("children")) throw new IllegalStateException("Missing required CDP field: children");
                if (!values.containsKey("conditionText")) throw new IllegalStateException("Missing required CDP field: conditionText");
                return new CSSFunctionConditionNode(values);
            }
        }
    }
    /**
     * Section of the body of a CSS function rule.
     */
    public static final class CSSFunctionNode extends CdpObject {
        private CSSFunctionNode(Map<String, Object> values) { super(values); }
        @Nullable public static CSSFunctionNode fromMap(@Nullable Map<String, Object> values) {
            return values == null ? null : new CSSFunctionNode(values);
        }
        public static Builder builder() { return new Builder(); }
        /**
         * A conditional block. If set, style should not be set.
         * @return the protocol field value
         */
        @Nullable public CSS.CSSFunctionConditionNode condition() {
            return CSS.CSSFunctionConditionNode.fromMap(objectMap(value("condition")));
        }
        /**
         * Values set by this node. If set, condition should not be set.
         * @return the protocol field value
         */
        @Nullable public CSS.CSSStyle style() {
            return CSS.CSSStyle.fromMap(objectMap(value("style")));
        }
        public static final class Builder {
            private final Map<String, Object> values = new LinkedHashMap<>();
            /**
             * A conditional block. If set, style should not be set.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder condition(@Nullable CSS.CSSFunctionConditionNode value) {
                if (value == null) values.remove("condition");
                else values.put("condition", jsonValue(value));
                return this;
            }
            /**
             * Values set by this node. If set, condition should not be set.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder style(@Nullable CSS.CSSStyle value) {
                if (value == null) values.remove("style");
                else values.put("style", jsonValue(value));
                return this;
            }
            public CSSFunctionNode build() {
                return new CSSFunctionNode(values);
            }
        }
    }
    /**
     * CSS function at-rule representation.
     */
    public static final class CSSFunctionRule extends CdpObject {
        private CSSFunctionRule(Map<String, Object> values) { super(values); }
        @Nullable public static CSSFunctionRule fromMap(@Nullable Map<String, Object> values) {
            return values == null ? null : new CSSFunctionRule(values);
        }
        public static Builder builder() { return new Builder(); }
        /**
         * Name of the function.
         * @return the protocol field value
         */
        @Nullable public CSS.Value name() {
            return CSS.Value.fromMap(objectMap(value("name")));
        }
        /**
         * The css style sheet identifier (absent for user agent stylesheet and user-specified stylesheet rules) this rule came from.
         * @return the protocol field value
         */
        @Nullable public String styleSheetId() {
            return (String) value("styleSheetId");
        }
        /**
         * Parent stylesheet&#x27;s origin.
         * @return the protocol field value
         */
        @Nullable public String origin() {
            return (String) value("origin");
        }
        /**
         * List of parameters.
         * @return the protocol field value
         */
        @Nullable public java.util.List<CSS.CSSFunctionParameter> parameters() {
            return list(value("parameters"), element0 -> CSS.CSSFunctionParameter.fromMap(objectMap(element0)));
        }
        /**
         * Function body.
         * @return the protocol field value
         */
        @Nullable public java.util.List<CSS.CSSFunctionNode> children() {
            return list(value("children"), element0 -> CSS.CSSFunctionNode.fromMap(objectMap(element0)));
        }
        /**
         * The BackendNodeId of the DOM node that constitutes the origin tree scope of this rule.
         * <p><b>Experimental:</b> this part of CDP may change without notice.
         * @return the protocol field value
         */
        @Nullable public Long originTreeScopeNodeId() {
            return numberAsLong(value("originTreeScopeNodeId"));
        }
        public static final class Builder {
            private final Map<String, Object> values = new LinkedHashMap<>();
            /**
             * Name of the function.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder name(@Nullable CSS.Value value) {
                if (value == null) values.remove("name");
                else values.put("name", jsonValue(value));
                return this;
            }
            /**
             * The css style sheet identifier (absent for user agent stylesheet and user-specified stylesheet rules) this rule came from.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder styleSheetId(@Nullable String value) {
                if (value == null) values.remove("styleSheetId");
                else values.put("styleSheetId", jsonValue(value));
                return this;
            }
            /**
             * Parent stylesheet&#x27;s origin.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder origin(@Nullable String value) {
                if (value == null) values.remove("origin");
                else values.put("origin", jsonValue(value));
                return this;
            }
            /**
             * List of parameters.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder parameters(@Nullable java.util.List<CSS.CSSFunctionParameter> value) {
                if (value == null) values.remove("parameters");
                else values.put("parameters", jsonValue(value));
                return this;
            }
            /**
             * Function body.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder children(@Nullable java.util.List<CSS.CSSFunctionNode> value) {
                if (value == null) values.remove("children");
                else values.put("children", jsonValue(value));
                return this;
            }
            /**
             * The BackendNodeId of the DOM node that constitutes the origin tree scope of this rule.
             * <p><b>Experimental:</b> this part of CDP may change without notice.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder originTreeScopeNodeId(@Nullable Long value) {
                if (value == null) values.remove("originTreeScopeNodeId");
                else values.put("originTreeScopeNodeId", jsonValue(value));
                return this;
            }
            public CSSFunctionRule build() {
                if (!values.containsKey("name")) throw new IllegalStateException("Missing required CDP field: name");
                if (!values.containsKey("origin")) throw new IllegalStateException("Missing required CDP field: origin");
                if (!values.containsKey("parameters")) throw new IllegalStateException("Missing required CDP field: parameters");
                if (!values.containsKey("children")) throw new IllegalStateException("Missing required CDP field: children");
                return new CSSFunctionRule(values);
            }
        }
    }
    /**
     * CSS keyframe rule representation.
     */
    public static final class CSSKeyframeRule extends CdpObject {
        private CSSKeyframeRule(Map<String, Object> values) { super(values); }
        @Nullable public static CSSKeyframeRule fromMap(@Nullable Map<String, Object> values) {
            return values == null ? null : new CSSKeyframeRule(values);
        }
        public static Builder builder() { return new Builder(); }
        /**
         * The css style sheet identifier (absent for user agent stylesheet and user-specified stylesheet rules) this rule came from.
         * @return the protocol field value
         */
        @Nullable public String styleSheetId() {
            return (String) value("styleSheetId");
        }
        /**
         * Parent stylesheet&#x27;s origin.
         * @return the protocol field value
         */
        @Nullable public String origin() {
            return (String) value("origin");
        }
        /**
         * Associated key text.
         * @return the protocol field value
         */
        @Nullable public CSS.Value keyText() {
            return CSS.Value.fromMap(objectMap(value("keyText")));
        }
        /**
         * Associated style declaration.
         * @return the protocol field value
         */
        @Nullable public CSS.CSSStyle style() {
            return CSS.CSSStyle.fromMap(objectMap(value("style")));
        }
        public static final class Builder {
            private final Map<String, Object> values = new LinkedHashMap<>();
            /**
             * The css style sheet identifier (absent for user agent stylesheet and user-specified stylesheet rules) this rule came from.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder styleSheetId(@Nullable String value) {
                if (value == null) values.remove("styleSheetId");
                else values.put("styleSheetId", jsonValue(value));
                return this;
            }
            /**
             * Parent stylesheet&#x27;s origin.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder origin(@Nullable String value) {
                if (value == null) values.remove("origin");
                else values.put("origin", jsonValue(value));
                return this;
            }
            /**
             * Associated key text.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder keyText(@Nullable CSS.Value value) {
                if (value == null) values.remove("keyText");
                else values.put("keyText", jsonValue(value));
                return this;
            }
            /**
             * Associated style declaration.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder style(@Nullable CSS.CSSStyle value) {
                if (value == null) values.remove("style");
                else values.put("style", jsonValue(value));
                return this;
            }
            public CSSKeyframeRule build() {
                if (!values.containsKey("origin")) throw new IllegalStateException("Missing required CDP field: origin");
                if (!values.containsKey("keyText")) throw new IllegalStateException("Missing required CDP field: keyText");
                if (!values.containsKey("style")) throw new IllegalStateException("Missing required CDP field: style");
                return new CSSKeyframeRule(values);
            }
        }
    }
    /**
     * A descriptor of operation to mutate style declaration text.
     */
    public static final class StyleDeclarationEdit extends CdpObject {
        private StyleDeclarationEdit(Map<String, Object> values) { super(values); }
        @Nullable public static StyleDeclarationEdit fromMap(@Nullable Map<String, Object> values) {
            return values == null ? null : new StyleDeclarationEdit(values);
        }
        public static Builder builder() { return new Builder(); }
        /**
         * The css style sheet identifier.
         * @return the protocol field value
         */
        @Nullable public String styleSheetId() {
            return (String) value("styleSheetId");
        }
        /**
         * The range of the style text in the enclosing stylesheet.
         * @return the protocol field value
         */
        @Nullable public CSS.SourceRange range() {
            return CSS.SourceRange.fromMap(objectMap(value("range")));
        }
        /**
         * New style text.
         * @return the protocol field value
         */
        @Nullable public String text() {
            return (String) value("text");
        }
        public static final class Builder {
            private final Map<String, Object> values = new LinkedHashMap<>();
            /**
             * The css style sheet identifier.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder styleSheetId(@Nullable String value) {
                if (value == null) values.remove("styleSheetId");
                else values.put("styleSheetId", jsonValue(value));
                return this;
            }
            /**
             * The range of the style text in the enclosing stylesheet.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder range(@Nullable CSS.SourceRange value) {
                if (value == null) values.remove("range");
                else values.put("range", jsonValue(value));
                return this;
            }
            /**
             * New style text.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder text(@Nullable String value) {
                if (value == null) values.remove("text");
                else values.put("text", jsonValue(value));
                return this;
            }
            public StyleDeclarationEdit build() {
                if (!values.containsKey("styleSheetId")) throw new IllegalStateException("Missing required CDP field: styleSheetId");
                if (!values.containsKey("range")) throw new IllegalStateException("Missing required CDP field: range");
                if (!values.containsKey("text")) throw new IllegalStateException("Missing required CDP field: text");
                return new StyleDeclarationEdit(values);
            }
        }
    }
    /**
     * Inserts a new rule with the given {@code ruleText} in a stylesheet with given {@code styleSheetId}, at the position specified by {@code location}.
     */
    public static final class AddRuleParams extends CdpObject {
        private AddRuleParams(Map<String, Object> values) { super(values); }
        @Nullable public static AddRuleParams fromMap(@Nullable Map<String, Object> values) {
            return values == null ? null : new AddRuleParams(values);
        }
        public static Builder builder() { return new Builder(); }
        /**
         * The css style sheet identifier where a new rule should be inserted.
         * @return the protocol field value
         */
        @Nullable public String styleSheetId() {
            return (String) value("styleSheetId");
        }
        /**
         * The text of a new rule.
         * @return the protocol field value
         */
        @Nullable public String ruleText() {
            return (String) value("ruleText");
        }
        /**
         * Text position of a new rule in the target style sheet.
         * @return the protocol field value
         */
        @Nullable public CSS.SourceRange location() {
            return CSS.SourceRange.fromMap(objectMap(value("location")));
        }
        /**
         * NodeId for the DOM node in whose context custom property declarations for registered properties should be validated. If omitted, declarations in the new rule text can only be validated statically, which may produce incorrect results if the declaration contains a var() for example.
         * <p><b>Experimental:</b> this part of CDP may change without notice.
         * @return the protocol field value
         */
        @Nullable public Long nodeForPropertySyntaxValidation() {
            return numberAsLong(value("nodeForPropertySyntaxValidation"));
        }
        public static final class Builder {
            private final Map<String, Object> values = new LinkedHashMap<>();
            /**
             * The css style sheet identifier where a new rule should be inserted.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder styleSheetId(@Nullable String value) {
                if (value == null) values.remove("styleSheetId");
                else values.put("styleSheetId", jsonValue(value));
                return this;
            }
            /**
             * The text of a new rule.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder ruleText(@Nullable String value) {
                if (value == null) values.remove("ruleText");
                else values.put("ruleText", jsonValue(value));
                return this;
            }
            /**
             * Text position of a new rule in the target style sheet.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder location(@Nullable CSS.SourceRange value) {
                if (value == null) values.remove("location");
                else values.put("location", jsonValue(value));
                return this;
            }
            /**
             * NodeId for the DOM node in whose context custom property declarations for registered properties should be validated. If omitted, declarations in the new rule text can only be validated statically, which may produce incorrect results if the declaration contains a var() for example.
             * <p><b>Experimental:</b> this part of CDP may change without notice.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder nodeForPropertySyntaxValidation(@Nullable Long value) {
                if (value == null) values.remove("nodeForPropertySyntaxValidation");
                else values.put("nodeForPropertySyntaxValidation", jsonValue(value));
                return this;
            }
            public AddRuleParams build() {
                if (!values.containsKey("styleSheetId")) throw new IllegalStateException("Missing required CDP field: styleSheetId");
                if (!values.containsKey("ruleText")) throw new IllegalStateException("Missing required CDP field: ruleText");
                if (!values.containsKey("location")) throw new IllegalStateException("Missing required CDP field: location");
                return new AddRuleParams(values);
            }
        }
    }
    /**
     * Inserts a new rule with the given {@code ruleText} in a stylesheet with given {@code styleSheetId}, at the position specified by {@code location}.
     */
    public static final class AddRuleResult extends CdpObject {
        private AddRuleResult(Map<String, Object> values) { super(values); }
        @Nullable public static AddRuleResult fromMap(@Nullable Map<String, Object> values) {
            return values == null ? null : new AddRuleResult(values);
        }
        public static Builder builder() { return new Builder(); }
        /**
         * The newly created rule.
         * @return the protocol field value
         */
        @Nullable public CSS.CSSRule rule() {
            return CSS.CSSRule.fromMap(objectMap(value("rule")));
        }
        public static final class Builder {
            private final Map<String, Object> values = new LinkedHashMap<>();
            /**
             * The newly created rule.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder rule(@Nullable CSS.CSSRule value) {
                if (value == null) values.remove("rule");
                else values.put("rule", jsonValue(value));
                return this;
            }
            public AddRuleResult build() {
                if (!values.containsKey("rule")) throw new IllegalStateException("Missing required CDP field: rule");
                return new AddRuleResult(values);
            }
        }
    }
    /**
     * Returns all class names from specified stylesheet.
     */
    public static final class CollectClassNamesParams extends CdpObject {
        private CollectClassNamesParams(Map<String, Object> values) { super(values); }
        @Nullable public static CollectClassNamesParams fromMap(@Nullable Map<String, Object> values) {
            return values == null ? null : new CollectClassNamesParams(values);
        }
        public static Builder builder() { return new Builder(); }
        /**
         * Returns the styleSheetId field.
         * @return the protocol field value
         */
        @Nullable public String styleSheetId() {
            return (String) value("styleSheetId");
        }
        public static final class Builder {
            private final Map<String, Object> values = new LinkedHashMap<>();
            /**
             * Sets the styleSheetId field.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder styleSheetId(@Nullable String value) {
                if (value == null) values.remove("styleSheetId");
                else values.put("styleSheetId", jsonValue(value));
                return this;
            }
            public CollectClassNamesParams build() {
                if (!values.containsKey("styleSheetId")) throw new IllegalStateException("Missing required CDP field: styleSheetId");
                return new CollectClassNamesParams(values);
            }
        }
    }
    /**
     * Returns all class names from specified stylesheet.
     */
    public static final class CollectClassNamesResult extends CdpObject {
        private CollectClassNamesResult(Map<String, Object> values) { super(values); }
        @Nullable public static CollectClassNamesResult fromMap(@Nullable Map<String, Object> values) {
            return values == null ? null : new CollectClassNamesResult(values);
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
            public CollectClassNamesResult build() {
                if (!values.containsKey("classNames")) throw new IllegalStateException("Missing required CDP field: classNames");
                return new CollectClassNamesResult(values);
            }
        }
    }
    /**
     * Creates a new special &quot;via-inspector&quot; stylesheet in the frame with given {@code frameId}.
     */
    public static final class CreateStyleSheetParams extends CdpObject {
        private CreateStyleSheetParams(Map<String, Object> values) { super(values); }
        @Nullable public static CreateStyleSheetParams fromMap(@Nullable Map<String, Object> values) {
            return values == null ? null : new CreateStyleSheetParams(values);
        }
        public static Builder builder() { return new Builder(); }
        /**
         * Identifier of the frame where &quot;via-inspector&quot; stylesheet should be created.
         * @return the protocol field value
         */
        @Nullable public String frameId() {
            return (String) value("frameId");
        }
        /**
         * If true, creates a new stylesheet for every call. If false, returns a stylesheet previously created by a call with force=false for the frame&#x27;s document if it exists or creates a new stylesheet (default: false).
         * @return the protocol field value
         */
        @Nullable public Boolean force() {
            return (Boolean) value("force");
        }
        public static final class Builder {
            private final Map<String, Object> values = new LinkedHashMap<>();
            /**
             * Identifier of the frame where &quot;via-inspector&quot; stylesheet should be created.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder frameId(@Nullable String value) {
                if (value == null) values.remove("frameId");
                else values.put("frameId", jsonValue(value));
                return this;
            }
            /**
             * If true, creates a new stylesheet for every call. If false, returns a stylesheet previously created by a call with force=false for the frame&#x27;s document if it exists or creates a new stylesheet (default: false).
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder force(@Nullable Boolean value) {
                if (value == null) values.remove("force");
                else values.put("force", jsonValue(value));
                return this;
            }
            public CreateStyleSheetParams build() {
                if (!values.containsKey("frameId")) throw new IllegalStateException("Missing required CDP field: frameId");
                return new CreateStyleSheetParams(values);
            }
        }
    }
    /**
     * Creates a new special &quot;via-inspector&quot; stylesheet in the frame with given {@code frameId}.
     */
    public static final class CreateStyleSheetResult extends CdpObject {
        private CreateStyleSheetResult(Map<String, Object> values) { super(values); }
        @Nullable public static CreateStyleSheetResult fromMap(@Nullable Map<String, Object> values) {
            return values == null ? null : new CreateStyleSheetResult(values);
        }
        public static Builder builder() { return new Builder(); }
        /**
         * Identifier of the created &quot;via-inspector&quot; stylesheet.
         * @return the protocol field value
         */
        @Nullable public String styleSheetId() {
            return (String) value("styleSheetId");
        }
        public static final class Builder {
            private final Map<String, Object> values = new LinkedHashMap<>();
            /**
             * Identifier of the created &quot;via-inspector&quot; stylesheet.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder styleSheetId(@Nullable String value) {
                if (value == null) values.remove("styleSheetId");
                else values.put("styleSheetId", jsonValue(value));
                return this;
            }
            public CreateStyleSheetResult build() {
                if (!values.containsKey("styleSheetId")) throw new IllegalStateException("Missing required CDP field: styleSheetId");
                return new CreateStyleSheetResult(values);
            }
        }
    }
    /**
     * Disables the CSS agent for the given page.
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
     * Disables the CSS agent for the given page.
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
     * Enables the CSS agent for the given page. Clients should not assume that the CSS agent has been enabled until the result of this command is received.
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
     * Enables the CSS agent for the given page. Clients should not assume that the CSS agent has been enabled until the result of this command is received.
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
     * Ensures that the given node will have specified pseudo-classes whenever its style is computed by the browser.
     */
    public static final class ForcePseudoStateParams extends CdpObject {
        private ForcePseudoStateParams(Map<String, Object> values) { super(values); }
        @Nullable public static ForcePseudoStateParams fromMap(@Nullable Map<String, Object> values) {
            return values == null ? null : new ForcePseudoStateParams(values);
        }
        public static Builder builder() { return new Builder(); }
        /**
         * The element id for which to force the pseudo state.
         * @return the protocol field value
         */
        @Nullable public Long nodeId() {
            return numberAsLong(value("nodeId"));
        }
        /**
         * Element pseudo classes to force when computing the element&#x27;s style.
         * @return the protocol field value
         */
        @Nullable public java.util.List<String> forcedPseudoClasses() {
            return list(value("forcedPseudoClasses"), element0 -> (String) element0);
        }
        public static final class Builder {
            private final Map<String, Object> values = new LinkedHashMap<>();
            /**
             * The element id for which to force the pseudo state.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder nodeId(@Nullable Long value) {
                if (value == null) values.remove("nodeId");
                else values.put("nodeId", jsonValue(value));
                return this;
            }
            /**
             * Element pseudo classes to force when computing the element&#x27;s style.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder forcedPseudoClasses(@Nullable java.util.List<String> value) {
                if (value == null) values.remove("forcedPseudoClasses");
                else values.put("forcedPseudoClasses", jsonValue(value));
                return this;
            }
            public ForcePseudoStateParams build() {
                if (!values.containsKey("nodeId")) throw new IllegalStateException("Missing required CDP field: nodeId");
                if (!values.containsKey("forcedPseudoClasses")) throw new IllegalStateException("Missing required CDP field: forcedPseudoClasses");
                return new ForcePseudoStateParams(values);
            }
        }
    }
    /**
     * Ensures that the given node will have specified pseudo-classes whenever its style is computed by the browser.
     */
    public static final class ForcePseudoStateResult extends CdpObject {
        private ForcePseudoStateResult(Map<String, Object> values) { super(values); }
        @Nullable public static ForcePseudoStateResult fromMap(@Nullable Map<String, Object> values) {
            return values == null ? null : new ForcePseudoStateResult(values);
        }
        public static Builder builder() { return new Builder(); }
        public static final class Builder {
            private final Map<String, Object> values = new LinkedHashMap<>();
            public ForcePseudoStateResult build() {
                return new ForcePseudoStateResult(values);
            }
        }
    }
    /**
     * Ensures that the given node is in its starting-style state.
     */
    public static final class ForceStartingStyleParams extends CdpObject {
        private ForceStartingStyleParams(Map<String, Object> values) { super(values); }
        @Nullable public static ForceStartingStyleParams fromMap(@Nullable Map<String, Object> values) {
            return values == null ? null : new ForceStartingStyleParams(values);
        }
        public static Builder builder() { return new Builder(); }
        /**
         * The element id for which to force the starting-style state.
         * @return the protocol field value
         */
        @Nullable public Long nodeId() {
            return numberAsLong(value("nodeId"));
        }
        /**
         * Boolean indicating if this is on or off.
         * @return the protocol field value
         */
        @Nullable public Boolean forced() {
            return (Boolean) value("forced");
        }
        public static final class Builder {
            private final Map<String, Object> values = new LinkedHashMap<>();
            /**
             * The element id for which to force the starting-style state.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder nodeId(@Nullable Long value) {
                if (value == null) values.remove("nodeId");
                else values.put("nodeId", jsonValue(value));
                return this;
            }
            /**
             * Boolean indicating if this is on or off.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder forced(@Nullable Boolean value) {
                if (value == null) values.remove("forced");
                else values.put("forced", jsonValue(value));
                return this;
            }
            public ForceStartingStyleParams build() {
                if (!values.containsKey("nodeId")) throw new IllegalStateException("Missing required CDP field: nodeId");
                if (!values.containsKey("forced")) throw new IllegalStateException("Missing required CDP field: forced");
                return new ForceStartingStyleParams(values);
            }
        }
    }
    /**
     * Ensures that the given node is in its starting-style state.
     */
    public static final class ForceStartingStyleResult extends CdpObject {
        private ForceStartingStyleResult(Map<String, Object> values) { super(values); }
        @Nullable public static ForceStartingStyleResult fromMap(@Nullable Map<String, Object> values) {
            return values == null ? null : new ForceStartingStyleResult(values);
        }
        public static Builder builder() { return new Builder(); }
        public static final class Builder {
            private final Map<String, Object> values = new LinkedHashMap<>();
            public ForceStartingStyleResult build() {
                return new ForceStartingStyleResult(values);
            }
        }
    }
    /**
     * Parameters for CSS.getBackgroundColors.
     */
    public static final class GetBackgroundColorsParams extends CdpObject {
        private GetBackgroundColorsParams(Map<String, Object> values) { super(values); }
        @Nullable public static GetBackgroundColorsParams fromMap(@Nullable Map<String, Object> values) {
            return values == null ? null : new GetBackgroundColorsParams(values);
        }
        public static Builder builder() { return new Builder(); }
        /**
         * Id of the node to get background colors for.
         * @return the protocol field value
         */
        @Nullable public Long nodeId() {
            return numberAsLong(value("nodeId"));
        }
        public static final class Builder {
            private final Map<String, Object> values = new LinkedHashMap<>();
            /**
             * Id of the node to get background colors for.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder nodeId(@Nullable Long value) {
                if (value == null) values.remove("nodeId");
                else values.put("nodeId", jsonValue(value));
                return this;
            }
            public GetBackgroundColorsParams build() {
                if (!values.containsKey("nodeId")) throw new IllegalStateException("Missing required CDP field: nodeId");
                return new GetBackgroundColorsParams(values);
            }
        }
    }
    /**
     * Result of CSS.getBackgroundColors.
     */
    public static final class GetBackgroundColorsResult extends CdpObject {
        private GetBackgroundColorsResult(Map<String, Object> values) { super(values); }
        @Nullable public static GetBackgroundColorsResult fromMap(@Nullable Map<String, Object> values) {
            return values == null ? null : new GetBackgroundColorsResult(values);
        }
        public static Builder builder() { return new Builder(); }
        /**
         * The range of background colors behind this element, if it contains any visible text. If no visible text is present, this will be undefined. In the case of a flat background color, this will consist of simply that color. In the case of a gradient, this will consist of each of the color stops. For anything more complicated, this will be an empty array. Images will be ignored (as if the image had failed to load).
         * @return the protocol field value
         */
        @Nullable public java.util.List<String> backgroundColors() {
            return list(value("backgroundColors"), element0 -> (String) element0);
        }
        /**
         * The computed font size for this node, as a CSS computed value string (e.g. &#x27;12px&#x27;).
         * @return the protocol field value
         */
        @Nullable public String computedFontSize() {
            return (String) value("computedFontSize");
        }
        /**
         * The computed font weight for this node, as a CSS computed value string (e.g. &#x27;normal&#x27; or &#x27;100&#x27;).
         * @return the protocol field value
         */
        @Nullable public String computedFontWeight() {
            return (String) value("computedFontWeight");
        }
        public static final class Builder {
            private final Map<String, Object> values = new LinkedHashMap<>();
            /**
             * The range of background colors behind this element, if it contains any visible text. If no visible text is present, this will be undefined. In the case of a flat background color, this will consist of simply that color. In the case of a gradient, this will consist of each of the color stops. For anything more complicated, this will be an empty array. Images will be ignored (as if the image had failed to load).
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder backgroundColors(@Nullable java.util.List<String> value) {
                if (value == null) values.remove("backgroundColors");
                else values.put("backgroundColors", jsonValue(value));
                return this;
            }
            /**
             * The computed font size for this node, as a CSS computed value string (e.g. &#x27;12px&#x27;).
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder computedFontSize(@Nullable String value) {
                if (value == null) values.remove("computedFontSize");
                else values.put("computedFontSize", jsonValue(value));
                return this;
            }
            /**
             * The computed font weight for this node, as a CSS computed value string (e.g. &#x27;normal&#x27; or &#x27;100&#x27;).
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder computedFontWeight(@Nullable String value) {
                if (value == null) values.remove("computedFontWeight");
                else values.put("computedFontWeight", jsonValue(value));
                return this;
            }
            public GetBackgroundColorsResult build() {
                return new GetBackgroundColorsResult(values);
            }
        }
    }
    /**
     * Returns the computed style for a DOM node identified by {@code nodeId}.
     */
    public static final class GetComputedStyleForNodeParams extends CdpObject {
        private GetComputedStyleForNodeParams(Map<String, Object> values) { super(values); }
        @Nullable public static GetComputedStyleForNodeParams fromMap(@Nullable Map<String, Object> values) {
            return values == null ? null : new GetComputedStyleForNodeParams(values);
        }
        public static Builder builder() { return new Builder(); }
        /**
         * Returns the nodeId field.
         * @return the protocol field value
         */
        @Nullable public Long nodeId() {
            return numberAsLong(value("nodeId"));
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
            public GetComputedStyleForNodeParams build() {
                if (!values.containsKey("nodeId")) throw new IllegalStateException("Missing required CDP field: nodeId");
                return new GetComputedStyleForNodeParams(values);
            }
        }
    }
    /**
     * Returns the computed style for a DOM node identified by {@code nodeId}.
     */
    public static final class GetComputedStyleForNodeResult extends CdpObject {
        private GetComputedStyleForNodeResult(Map<String, Object> values) { super(values); }
        @Nullable public static GetComputedStyleForNodeResult fromMap(@Nullable Map<String, Object> values) {
            return values == null ? null : new GetComputedStyleForNodeResult(values);
        }
        public static Builder builder() { return new Builder(); }
        /**
         * Computed style for the specified DOM node.
         * @return the protocol field value
         */
        @Nullable public java.util.List<CSS.CSSComputedStyleProperty> computedStyle() {
            return list(value("computedStyle"), element0 -> CSS.CSSComputedStyleProperty.fromMap(objectMap(element0)));
        }
        /**
         * A list of non-standard &quot;extra fields&quot; which blink stores alongside each computed style.
         * <p><b>Experimental:</b> this part of CDP may change without notice.
         * @return the protocol field value
         */
        @Nullable public CSS.ComputedStyleExtraFields extraFields() {
            return CSS.ComputedStyleExtraFields.fromMap(objectMap(value("extraFields")));
        }
        public static final class Builder {
            private final Map<String, Object> values = new LinkedHashMap<>();
            /**
             * Computed style for the specified DOM node.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder computedStyle(@Nullable java.util.List<CSS.CSSComputedStyleProperty> value) {
                if (value == null) values.remove("computedStyle");
                else values.put("computedStyle", jsonValue(value));
                return this;
            }
            /**
             * A list of non-standard &quot;extra fields&quot; which blink stores alongside each computed style.
             * <p><b>Experimental:</b> this part of CDP may change without notice.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder extraFields(@Nullable CSS.ComputedStyleExtraFields value) {
                if (value == null) values.remove("extraFields");
                else values.put("extraFields", jsonValue(value));
                return this;
            }
            public GetComputedStyleForNodeResult build() {
                if (!values.containsKey("computedStyle")) throw new IllegalStateException("Missing required CDP field: computedStyle");
                if (!values.containsKey("extraFields")) throw new IllegalStateException("Missing required CDP field: extraFields");
                return new GetComputedStyleForNodeResult(values);
            }
        }
    }
    /**
     * Resolve the specified values in the context of the provided element. For example, a value of &#x27;1em&#x27; is evaluated according to the computed &#x27;font-size&#x27; of the element and a value &#x27;calc(1px + 2px)&#x27; will be resolved to &#x27;3px&#x27;. If the {@code propertyName} was specified the {@code values} are resolved as if they were property&#x27;s declaration. If a value cannot be parsed according to the provided property syntax, the value is parsed using combined syntax as if null {@code propertyName} was provided. If the value cannot be resolved even then, return the provided value without any changes. Note: this function currently does not resolve CSS random() function, it returns unmodified random() function parts.`
     * <p><b>Experimental:</b> this part of CDP may change without notice.
     */
    public static final class ResolveValuesParams extends CdpObject {
        private ResolveValuesParams(Map<String, Object> values) { super(values); }
        @Nullable public static ResolveValuesParams fromMap(@Nullable Map<String, Object> values) {
            return values == null ? null : new ResolveValuesParams(values);
        }
        public static Builder builder() { return new Builder(); }
        /**
         * Cascade-dependent keywords (revert/revert-layer) do not work.
         * @return the protocol field value
         */
        @Nullable public java.util.List<String> values() {
            return list(value("values"), element0 -> (String) element0);
        }
        /**
         * Id of the node in whose context the expression is evaluated
         * @return the protocol field value
         */
        @Nullable public Long nodeId() {
            return numberAsLong(value("nodeId"));
        }
        /**
         * Only longhands and custom property names are accepted.
         * @return the protocol field value
         */
        @Nullable public String propertyName() {
            return (String) value("propertyName");
        }
        /**
         * Pseudo element type, only works for pseudo elements that generate elements in the tree, such as ::before and ::after.
         * @return the protocol field value
         */
        @Nullable public String pseudoType() {
            return (String) value("pseudoType");
        }
        /**
         * Pseudo element custom ident.
         * @return the protocol field value
         */
        @Nullable public String pseudoIdentifier() {
            return (String) value("pseudoIdentifier");
        }
        public static final class Builder {
            private final Map<String, Object> values = new LinkedHashMap<>();
            /**
             * Cascade-dependent keywords (revert/revert-layer) do not work.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder values(@Nullable java.util.List<String> value) {
                if (value == null) values.remove("values");
                else values.put("values", jsonValue(value));
                return this;
            }
            /**
             * Id of the node in whose context the expression is evaluated
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder nodeId(@Nullable Long value) {
                if (value == null) values.remove("nodeId");
                else values.put("nodeId", jsonValue(value));
                return this;
            }
            /**
             * Only longhands and custom property names are accepted.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder propertyName(@Nullable String value) {
                if (value == null) values.remove("propertyName");
                else values.put("propertyName", jsonValue(value));
                return this;
            }
            /**
             * Pseudo element type, only works for pseudo elements that generate elements in the tree, such as ::before and ::after.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder pseudoType(@Nullable String value) {
                if (value == null) values.remove("pseudoType");
                else values.put("pseudoType", jsonValue(value));
                return this;
            }
            /**
             * Pseudo element custom ident.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder pseudoIdentifier(@Nullable String value) {
                if (value == null) values.remove("pseudoIdentifier");
                else values.put("pseudoIdentifier", jsonValue(value));
                return this;
            }
            public ResolveValuesParams build() {
                if (!values.containsKey("values")) throw new IllegalStateException("Missing required CDP field: values");
                if (!values.containsKey("nodeId")) throw new IllegalStateException("Missing required CDP field: nodeId");
                return new ResolveValuesParams(values);
            }
        }
    }
    /**
     * Resolve the specified values in the context of the provided element. For example, a value of &#x27;1em&#x27; is evaluated according to the computed &#x27;font-size&#x27; of the element and a value &#x27;calc(1px + 2px)&#x27; will be resolved to &#x27;3px&#x27;. If the {@code propertyName} was specified the {@code values} are resolved as if they were property&#x27;s declaration. If a value cannot be parsed according to the provided property syntax, the value is parsed using combined syntax as if null {@code propertyName} was provided. If the value cannot be resolved even then, return the provided value without any changes. Note: this function currently does not resolve CSS random() function, it returns unmodified random() function parts.`
     * <p><b>Experimental:</b> this part of CDP may change without notice.
     */
    public static final class ResolveValuesResult extends CdpObject {
        private ResolveValuesResult(Map<String, Object> values) { super(values); }
        @Nullable public static ResolveValuesResult fromMap(@Nullable Map<String, Object> values) {
            return values == null ? null : new ResolveValuesResult(values);
        }
        public static Builder builder() { return new Builder(); }
        /**
         * Returns the results field.
         * @return the protocol field value
         */
        @Nullable public java.util.List<String> results() {
            return list(value("results"), element0 -> (String) element0);
        }
        public static final class Builder {
            private final Map<String, Object> values = new LinkedHashMap<>();
            /**
             * Sets the results field.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder results(@Nullable java.util.List<String> value) {
                if (value == null) values.remove("results");
                else values.put("results", jsonValue(value));
                return this;
            }
            public ResolveValuesResult build() {
                if (!values.containsKey("results")) throw new IllegalStateException("Missing required CDP field: results");
                return new ResolveValuesResult(values);
            }
        }
    }
    /**
     * Parameters for CSS.getLonghandProperties.
     * <p><b>Experimental:</b> this part of CDP may change without notice.
     */
    public static final class GetLonghandPropertiesParams extends CdpObject {
        private GetLonghandPropertiesParams(Map<String, Object> values) { super(values); }
        @Nullable public static GetLonghandPropertiesParams fromMap(@Nullable Map<String, Object> values) {
            return values == null ? null : new GetLonghandPropertiesParams(values);
        }
        public static Builder builder() { return new Builder(); }
        /**
         * Returns the shorthandName field.
         * @return the protocol field value
         */
        @Nullable public String shorthandName() {
            return (String) value("shorthandName");
        }
        /**
         * Returns the value field.
         * @return the protocol field value
         */
        @Nullable public String value() {
            return (String) value("value");
        }
        public static final class Builder {
            private final Map<String, Object> values = new LinkedHashMap<>();
            /**
             * Sets the shorthandName field.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder shorthandName(@Nullable String value) {
                if (value == null) values.remove("shorthandName");
                else values.put("shorthandName", jsonValue(value));
                return this;
            }
            /**
             * Sets the value field.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder value(@Nullable String value) {
                if (value == null) values.remove("value");
                else values.put("value", jsonValue(value));
                return this;
            }
            public GetLonghandPropertiesParams build() {
                if (!values.containsKey("shorthandName")) throw new IllegalStateException("Missing required CDP field: shorthandName");
                if (!values.containsKey("value")) throw new IllegalStateException("Missing required CDP field: value");
                return new GetLonghandPropertiesParams(values);
            }
        }
    }
    /**
     * Result of CSS.getLonghandProperties.
     * <p><b>Experimental:</b> this part of CDP may change without notice.
     */
    public static final class GetLonghandPropertiesResult extends CdpObject {
        private GetLonghandPropertiesResult(Map<String, Object> values) { super(values); }
        @Nullable public static GetLonghandPropertiesResult fromMap(@Nullable Map<String, Object> values) {
            return values == null ? null : new GetLonghandPropertiesResult(values);
        }
        public static Builder builder() { return new Builder(); }
        /**
         * Returns the longhandProperties field.
         * @return the protocol field value
         */
        @Nullable public java.util.List<CSS.CSSProperty> longhandProperties() {
            return list(value("longhandProperties"), element0 -> CSS.CSSProperty.fromMap(objectMap(element0)));
        }
        public static final class Builder {
            private final Map<String, Object> values = new LinkedHashMap<>();
            /**
             * Sets the longhandProperties field.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder longhandProperties(@Nullable java.util.List<CSS.CSSProperty> value) {
                if (value == null) values.remove("longhandProperties");
                else values.put("longhandProperties", jsonValue(value));
                return this;
            }
            public GetLonghandPropertiesResult build() {
                if (!values.containsKey("longhandProperties")) throw new IllegalStateException("Missing required CDP field: longhandProperties");
                return new GetLonghandPropertiesResult(values);
            }
        }
    }
    /**
     * Returns the styles defined inline (explicitly in the &quot;style&quot; attribute and implicitly, using DOM attributes) for a DOM node identified by {@code nodeId}.
     */
    public static final class GetInlineStylesForNodeParams extends CdpObject {
        private GetInlineStylesForNodeParams(Map<String, Object> values) { super(values); }
        @Nullable public static GetInlineStylesForNodeParams fromMap(@Nullable Map<String, Object> values) {
            return values == null ? null : new GetInlineStylesForNodeParams(values);
        }
        public static Builder builder() { return new Builder(); }
        /**
         * Returns the nodeId field.
         * @return the protocol field value
         */
        @Nullable public Long nodeId() {
            return numberAsLong(value("nodeId"));
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
            public GetInlineStylesForNodeParams build() {
                if (!values.containsKey("nodeId")) throw new IllegalStateException("Missing required CDP field: nodeId");
                return new GetInlineStylesForNodeParams(values);
            }
        }
    }
    /**
     * Returns the styles defined inline (explicitly in the &quot;style&quot; attribute and implicitly, using DOM attributes) for a DOM node identified by {@code nodeId}.
     */
    public static final class GetInlineStylesForNodeResult extends CdpObject {
        private GetInlineStylesForNodeResult(Map<String, Object> values) { super(values); }
        @Nullable public static GetInlineStylesForNodeResult fromMap(@Nullable Map<String, Object> values) {
            return values == null ? null : new GetInlineStylesForNodeResult(values);
        }
        public static Builder builder() { return new Builder(); }
        /**
         * Inline style for the specified DOM node.
         * @return the protocol field value
         */
        @Nullable public CSS.CSSStyle inlineStyle() {
            return CSS.CSSStyle.fromMap(objectMap(value("inlineStyle")));
        }
        /**
         * Attribute-defined element style (e.g. resulting from &quot;width=20 height=100%&quot;).
         * @return the protocol field value
         */
        @Nullable public CSS.CSSStyle attributesStyle() {
            return CSS.CSSStyle.fromMap(objectMap(value("attributesStyle")));
        }
        public static final class Builder {
            private final Map<String, Object> values = new LinkedHashMap<>();
            /**
             * Inline style for the specified DOM node.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder inlineStyle(@Nullable CSS.CSSStyle value) {
                if (value == null) values.remove("inlineStyle");
                else values.put("inlineStyle", jsonValue(value));
                return this;
            }
            /**
             * Attribute-defined element style (e.g. resulting from &quot;width=20 height=100%&quot;).
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder attributesStyle(@Nullable CSS.CSSStyle value) {
                if (value == null) values.remove("attributesStyle");
                else values.put("attributesStyle", jsonValue(value));
                return this;
            }
            public GetInlineStylesForNodeResult build() {
                return new GetInlineStylesForNodeResult(values);
            }
        }
    }
    /**
     * Returns the styles coming from animations &amp; transitions including the animation &amp; transition styles coming from inheritance chain.
     * <p><b>Experimental:</b> this part of CDP may change without notice.
     */
    public static final class GetAnimatedStylesForNodeParams extends CdpObject {
        private GetAnimatedStylesForNodeParams(Map<String, Object> values) { super(values); }
        @Nullable public static GetAnimatedStylesForNodeParams fromMap(@Nullable Map<String, Object> values) {
            return values == null ? null : new GetAnimatedStylesForNodeParams(values);
        }
        public static Builder builder() { return new Builder(); }
        /**
         * Returns the nodeId field.
         * @return the protocol field value
         */
        @Nullable public Long nodeId() {
            return numberAsLong(value("nodeId"));
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
            public GetAnimatedStylesForNodeParams build() {
                if (!values.containsKey("nodeId")) throw new IllegalStateException("Missing required CDP field: nodeId");
                return new GetAnimatedStylesForNodeParams(values);
            }
        }
    }
    /**
     * Returns the styles coming from animations &amp; transitions including the animation &amp; transition styles coming from inheritance chain.
     * <p><b>Experimental:</b> this part of CDP may change without notice.
     */
    public static final class GetAnimatedStylesForNodeResult extends CdpObject {
        private GetAnimatedStylesForNodeResult(Map<String, Object> values) { super(values); }
        @Nullable public static GetAnimatedStylesForNodeResult fromMap(@Nullable Map<String, Object> values) {
            return values == null ? null : new GetAnimatedStylesForNodeResult(values);
        }
        public static Builder builder() { return new Builder(); }
        /**
         * Styles coming from animations.
         * @return the protocol field value
         */
        @Nullable public java.util.List<CSS.CSSAnimationStyle> animationStyles() {
            return list(value("animationStyles"), element0 -> CSS.CSSAnimationStyle.fromMap(objectMap(element0)));
        }
        /**
         * Style coming from transitions.
         * @return the protocol field value
         */
        @Nullable public CSS.CSSStyle transitionsStyle() {
            return CSS.CSSStyle.fromMap(objectMap(value("transitionsStyle")));
        }
        /**
         * Inherited style entries for animationsStyle and transitionsStyle from the inheritance chain of the element.
         * @return the protocol field value
         */
        @Nullable public java.util.List<CSS.InheritedAnimatedStyleEntry> inherited() {
            return list(value("inherited"), element0 -> CSS.InheritedAnimatedStyleEntry.fromMap(objectMap(element0)));
        }
        public static final class Builder {
            private final Map<String, Object> values = new LinkedHashMap<>();
            /**
             * Styles coming from animations.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder animationStyles(@Nullable java.util.List<CSS.CSSAnimationStyle> value) {
                if (value == null) values.remove("animationStyles");
                else values.put("animationStyles", jsonValue(value));
                return this;
            }
            /**
             * Style coming from transitions.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder transitionsStyle(@Nullable CSS.CSSStyle value) {
                if (value == null) values.remove("transitionsStyle");
                else values.put("transitionsStyle", jsonValue(value));
                return this;
            }
            /**
             * Inherited style entries for animationsStyle and transitionsStyle from the inheritance chain of the element.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder inherited(@Nullable java.util.List<CSS.InheritedAnimatedStyleEntry> value) {
                if (value == null) values.remove("inherited");
                else values.put("inherited", jsonValue(value));
                return this;
            }
            public GetAnimatedStylesForNodeResult build() {
                return new GetAnimatedStylesForNodeResult(values);
            }
        }
    }
    /**
     * Returns requested styles for a DOM node identified by {@code nodeId}.
     */
    public static final class GetMatchedStylesForNodeParams extends CdpObject {
        private GetMatchedStylesForNodeParams(Map<String, Object> values) { super(values); }
        @Nullable public static GetMatchedStylesForNodeParams fromMap(@Nullable Map<String, Object> values) {
            return values == null ? null : new GetMatchedStylesForNodeParams(values);
        }
        public static Builder builder() { return new Builder(); }
        /**
         * Returns the nodeId field.
         * @return the protocol field value
         */
        @Nullable public Long nodeId() {
            return numberAsLong(value("nodeId"));
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
            public GetMatchedStylesForNodeParams build() {
                if (!values.containsKey("nodeId")) throw new IllegalStateException("Missing required CDP field: nodeId");
                return new GetMatchedStylesForNodeParams(values);
            }
        }
    }
    /**
     * Returns requested styles for a DOM node identified by {@code nodeId}.
     */
    public static final class GetMatchedStylesForNodeResult extends CdpObject {
        private GetMatchedStylesForNodeResult(Map<String, Object> values) { super(values); }
        @Nullable public static GetMatchedStylesForNodeResult fromMap(@Nullable Map<String, Object> values) {
            return values == null ? null : new GetMatchedStylesForNodeResult(values);
        }
        public static Builder builder() { return new Builder(); }
        /**
         * Inline style for the specified DOM node.
         * @return the protocol field value
         */
        @Nullable public CSS.CSSStyle inlineStyle() {
            return CSS.CSSStyle.fromMap(objectMap(value("inlineStyle")));
        }
        /**
         * Attribute-defined element style (e.g. resulting from &quot;width=20 height=100%&quot;).
         * @return the protocol field value
         */
        @Nullable public CSS.CSSStyle attributesStyle() {
            return CSS.CSSStyle.fromMap(objectMap(value("attributesStyle")));
        }
        /**
         * CSS rules matching this node, from all applicable stylesheets.
         * @return the protocol field value
         */
        @Nullable public java.util.List<CSS.RuleMatch> matchedCSSRules() {
            return list(value("matchedCSSRules"), element0 -> CSS.RuleMatch.fromMap(objectMap(element0)));
        }
        /**
         * Pseudo style matches for this node.
         * @return the protocol field value
         */
        @Nullable public java.util.List<CSS.PseudoElementMatches> pseudoElements() {
            return list(value("pseudoElements"), element0 -> CSS.PseudoElementMatches.fromMap(objectMap(element0)));
        }
        /**
         * A chain of inherited styles (from the immediate node parent up to the DOM tree root).
         * @return the protocol field value
         */
        @Nullable public java.util.List<CSS.InheritedStyleEntry> inherited() {
            return list(value("inherited"), element0 -> CSS.InheritedStyleEntry.fromMap(objectMap(element0)));
        }
        /**
         * A chain of inherited pseudo element styles (from the immediate node parent up to the DOM tree root).
         * @return the protocol field value
         */
        @Nullable public java.util.List<CSS.InheritedPseudoElementMatches> inheritedPseudoElements() {
            return list(value("inheritedPseudoElements"), element0 -> CSS.InheritedPseudoElementMatches.fromMap(objectMap(element0)));
        }
        /**
         * A list of CSS keyframed animations matching this node.
         * @return the protocol field value
         */
        @Nullable public java.util.List<CSS.CSSKeyframesRule> cssKeyframesRules() {
            return list(value("cssKeyframesRules"), element0 -> CSS.CSSKeyframesRule.fromMap(objectMap(element0)));
        }
        /**
         * A list of CSS &#64;position-try rules matching this node, based on the position-try-fallbacks property.
         * @return the protocol field value
         */
        @Nullable public java.util.List<CSS.CSSPositionTryRule> cssPositionTryRules() {
            return list(value("cssPositionTryRules"), element0 -> CSS.CSSPositionTryRule.fromMap(objectMap(element0)));
        }
        /**
         * Index of the active fallback in the applied position-try-fallback property, will not be set if there is no active position-try fallback.
         * @return the protocol field value
         */
        @Nullable public Long activePositionFallbackIndex() {
            return numberAsLong(value("activePositionFallbackIndex"));
        }
        /**
         * A list of CSS at-property rules matching this node.
         * @return the protocol field value
         */
        @Nullable public java.util.List<CSS.CSSPropertyRule> cssPropertyRules() {
            return list(value("cssPropertyRules"), element0 -> CSS.CSSPropertyRule.fromMap(objectMap(element0)));
        }
        /**
         * A list of CSS property registrations matching this node.
         * @return the protocol field value
         */
        @Nullable public java.util.List<CSS.CSSPropertyRegistration> cssPropertyRegistrations() {
            return list(value("cssPropertyRegistrations"), element0 -> CSS.CSSPropertyRegistration.fromMap(objectMap(element0)));
        }
        /**
         * A list of simple &#64;rules matching this node or its pseudo-elements.
         * @return the protocol field value
         */
        @Nullable public java.util.List<CSS.CSSAtRule> cssAtRules() {
            return list(value("cssAtRules"), element0 -> CSS.CSSAtRule.fromMap(objectMap(element0)));
        }
        /**
         * Id of the first parent element that does not have display: contents.
         * <p><b>Experimental:</b> this part of CDP may change without notice.
         * @return the protocol field value
         */
        @Nullable public Long parentLayoutNodeId() {
            return numberAsLong(value("parentLayoutNodeId"));
        }
        /**
         * A list of CSS at-function rules referenced by styles of this node.
         * <p><b>Experimental:</b> this part of CDP may change without notice.
         * @return the protocol field value
         */
        @Nullable public java.util.List<CSS.CSSFunctionRule> cssFunctionRules() {
            return list(value("cssFunctionRules"), element0 -> CSS.CSSFunctionRule.fromMap(objectMap(element0)));
        }
        public static final class Builder {
            private final Map<String, Object> values = new LinkedHashMap<>();
            /**
             * Inline style for the specified DOM node.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder inlineStyle(@Nullable CSS.CSSStyle value) {
                if (value == null) values.remove("inlineStyle");
                else values.put("inlineStyle", jsonValue(value));
                return this;
            }
            /**
             * Attribute-defined element style (e.g. resulting from &quot;width=20 height=100%&quot;).
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder attributesStyle(@Nullable CSS.CSSStyle value) {
                if (value == null) values.remove("attributesStyle");
                else values.put("attributesStyle", jsonValue(value));
                return this;
            }
            /**
             * CSS rules matching this node, from all applicable stylesheets.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder matchedCSSRules(@Nullable java.util.List<CSS.RuleMatch> value) {
                if (value == null) values.remove("matchedCSSRules");
                else values.put("matchedCSSRules", jsonValue(value));
                return this;
            }
            /**
             * Pseudo style matches for this node.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder pseudoElements(@Nullable java.util.List<CSS.PseudoElementMatches> value) {
                if (value == null) values.remove("pseudoElements");
                else values.put("pseudoElements", jsonValue(value));
                return this;
            }
            /**
             * A chain of inherited styles (from the immediate node parent up to the DOM tree root).
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder inherited(@Nullable java.util.List<CSS.InheritedStyleEntry> value) {
                if (value == null) values.remove("inherited");
                else values.put("inherited", jsonValue(value));
                return this;
            }
            /**
             * A chain of inherited pseudo element styles (from the immediate node parent up to the DOM tree root).
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder inheritedPseudoElements(@Nullable java.util.List<CSS.InheritedPseudoElementMatches> value) {
                if (value == null) values.remove("inheritedPseudoElements");
                else values.put("inheritedPseudoElements", jsonValue(value));
                return this;
            }
            /**
             * A list of CSS keyframed animations matching this node.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder cssKeyframesRules(@Nullable java.util.List<CSS.CSSKeyframesRule> value) {
                if (value == null) values.remove("cssKeyframesRules");
                else values.put("cssKeyframesRules", jsonValue(value));
                return this;
            }
            /**
             * A list of CSS &#64;position-try rules matching this node, based on the position-try-fallbacks property.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder cssPositionTryRules(@Nullable java.util.List<CSS.CSSPositionTryRule> value) {
                if (value == null) values.remove("cssPositionTryRules");
                else values.put("cssPositionTryRules", jsonValue(value));
                return this;
            }
            /**
             * Index of the active fallback in the applied position-try-fallback property, will not be set if there is no active position-try fallback.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder activePositionFallbackIndex(@Nullable Long value) {
                if (value == null) values.remove("activePositionFallbackIndex");
                else values.put("activePositionFallbackIndex", jsonValue(value));
                return this;
            }
            /**
             * A list of CSS at-property rules matching this node.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder cssPropertyRules(@Nullable java.util.List<CSS.CSSPropertyRule> value) {
                if (value == null) values.remove("cssPropertyRules");
                else values.put("cssPropertyRules", jsonValue(value));
                return this;
            }
            /**
             * A list of CSS property registrations matching this node.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder cssPropertyRegistrations(@Nullable java.util.List<CSS.CSSPropertyRegistration> value) {
                if (value == null) values.remove("cssPropertyRegistrations");
                else values.put("cssPropertyRegistrations", jsonValue(value));
                return this;
            }
            /**
             * A list of simple &#64;rules matching this node or its pseudo-elements.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder cssAtRules(@Nullable java.util.List<CSS.CSSAtRule> value) {
                if (value == null) values.remove("cssAtRules");
                else values.put("cssAtRules", jsonValue(value));
                return this;
            }
            /**
             * Id of the first parent element that does not have display: contents.
             * <p><b>Experimental:</b> this part of CDP may change without notice.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder parentLayoutNodeId(@Nullable Long value) {
                if (value == null) values.remove("parentLayoutNodeId");
                else values.put("parentLayoutNodeId", jsonValue(value));
                return this;
            }
            /**
             * A list of CSS at-function rules referenced by styles of this node.
             * <p><b>Experimental:</b> this part of CDP may change without notice.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder cssFunctionRules(@Nullable java.util.List<CSS.CSSFunctionRule> value) {
                if (value == null) values.remove("cssFunctionRules");
                else values.put("cssFunctionRules", jsonValue(value));
                return this;
            }
            public GetMatchedStylesForNodeResult build() {
                return new GetMatchedStylesForNodeResult(values);
            }
        }
    }
    /**
     * Returns the values of the default UA-defined environment variables used in env()
     * <p><b>Experimental:</b> this part of CDP may change without notice.
     */
    public static final class GetEnvironmentVariablesParams extends CdpObject {
        private GetEnvironmentVariablesParams(Map<String, Object> values) { super(values); }
        @Nullable public static GetEnvironmentVariablesParams fromMap(@Nullable Map<String, Object> values) {
            return values == null ? null : new GetEnvironmentVariablesParams(values);
        }
        public static Builder builder() { return new Builder(); }
        public static final class Builder {
            private final Map<String, Object> values = new LinkedHashMap<>();
            public GetEnvironmentVariablesParams build() {
                return new GetEnvironmentVariablesParams(values);
            }
        }
    }
    /**
     * Returns the values of the default UA-defined environment variables used in env()
     * <p><b>Experimental:</b> this part of CDP may change without notice.
     */
    public static final class GetEnvironmentVariablesResult extends CdpObject {
        private GetEnvironmentVariablesResult(Map<String, Object> values) { super(values); }
        @Nullable public static GetEnvironmentVariablesResult fromMap(@Nullable Map<String, Object> values) {
            return values == null ? null : new GetEnvironmentVariablesResult(values);
        }
        public static Builder builder() { return new Builder(); }
        /**
         * Returns the environmentVariables field.
         * @return the protocol field value
         */
        @Nullable public java.util.Map<String, Object> environmentVariables() {
            return objectMap(value("environmentVariables"));
        }
        public static final class Builder {
            private final Map<String, Object> values = new LinkedHashMap<>();
            /**
             * Sets the environmentVariables field.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder environmentVariables(@Nullable java.util.Map<String, Object> value) {
                if (value == null) values.remove("environmentVariables");
                else values.put("environmentVariables", jsonValue(value));
                return this;
            }
            public GetEnvironmentVariablesResult build() {
                if (!values.containsKey("environmentVariables")) throw new IllegalStateException("Missing required CDP field: environmentVariables");
                return new GetEnvironmentVariablesResult(values);
            }
        }
    }
    /**
     * Returns all media queries parsed by the rendering engine.
     */
    public static final class GetMediaQueriesParams extends CdpObject {
        private GetMediaQueriesParams(Map<String, Object> values) { super(values); }
        @Nullable public static GetMediaQueriesParams fromMap(@Nullable Map<String, Object> values) {
            return values == null ? null : new GetMediaQueriesParams(values);
        }
        public static Builder builder() { return new Builder(); }
        public static final class Builder {
            private final Map<String, Object> values = new LinkedHashMap<>();
            public GetMediaQueriesParams build() {
                return new GetMediaQueriesParams(values);
            }
        }
    }
    /**
     * Returns all media queries parsed by the rendering engine.
     */
    public static final class GetMediaQueriesResult extends CdpObject {
        private GetMediaQueriesResult(Map<String, Object> values) { super(values); }
        @Nullable public static GetMediaQueriesResult fromMap(@Nullable Map<String, Object> values) {
            return values == null ? null : new GetMediaQueriesResult(values);
        }
        public static Builder builder() { return new Builder(); }
        /**
         * Returns the medias field.
         * @return the protocol field value
         */
        @Nullable public java.util.List<CSS.CSSMedia> medias() {
            return list(value("medias"), element0 -> CSS.CSSMedia.fromMap(objectMap(element0)));
        }
        public static final class Builder {
            private final Map<String, Object> values = new LinkedHashMap<>();
            /**
             * Sets the medias field.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder medias(@Nullable java.util.List<CSS.CSSMedia> value) {
                if (value == null) values.remove("medias");
                else values.put("medias", jsonValue(value));
                return this;
            }
            public GetMediaQueriesResult build() {
                if (!values.containsKey("medias")) throw new IllegalStateException("Missing required CDP field: medias");
                return new GetMediaQueriesResult(values);
            }
        }
    }
    /**
     * Requests information about platform fonts which we used to render child TextNodes in the given node.
     */
    public static final class GetPlatformFontsForNodeParams extends CdpObject {
        private GetPlatformFontsForNodeParams(Map<String, Object> values) { super(values); }
        @Nullable public static GetPlatformFontsForNodeParams fromMap(@Nullable Map<String, Object> values) {
            return values == null ? null : new GetPlatformFontsForNodeParams(values);
        }
        public static Builder builder() { return new Builder(); }
        /**
         * Returns the nodeId field.
         * @return the protocol field value
         */
        @Nullable public Long nodeId() {
            return numberAsLong(value("nodeId"));
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
            public GetPlatformFontsForNodeParams build() {
                if (!values.containsKey("nodeId")) throw new IllegalStateException("Missing required CDP field: nodeId");
                return new GetPlatformFontsForNodeParams(values);
            }
        }
    }
    /**
     * Requests information about platform fonts which we used to render child TextNodes in the given node.
     */
    public static final class GetPlatformFontsForNodeResult extends CdpObject {
        private GetPlatformFontsForNodeResult(Map<String, Object> values) { super(values); }
        @Nullable public static GetPlatformFontsForNodeResult fromMap(@Nullable Map<String, Object> values) {
            return values == null ? null : new GetPlatformFontsForNodeResult(values);
        }
        public static Builder builder() { return new Builder(); }
        /**
         * Usage statistics for every employed platform font.
         * @return the protocol field value
         */
        @Nullable public java.util.List<CSS.PlatformFontUsage> fonts() {
            return list(value("fonts"), element0 -> CSS.PlatformFontUsage.fromMap(objectMap(element0)));
        }
        public static final class Builder {
            private final Map<String, Object> values = new LinkedHashMap<>();
            /**
             * Usage statistics for every employed platform font.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder fonts(@Nullable java.util.List<CSS.PlatformFontUsage> value) {
                if (value == null) values.remove("fonts");
                else values.put("fonts", jsonValue(value));
                return this;
            }
            public GetPlatformFontsForNodeResult build() {
                if (!values.containsKey("fonts")) throw new IllegalStateException("Missing required CDP field: fonts");
                return new GetPlatformFontsForNodeResult(values);
            }
        }
    }
    /**
     * Returns the current textual content for a stylesheet.
     */
    public static final class GetStyleSheetTextParams extends CdpObject {
        private GetStyleSheetTextParams(Map<String, Object> values) { super(values); }
        @Nullable public static GetStyleSheetTextParams fromMap(@Nullable Map<String, Object> values) {
            return values == null ? null : new GetStyleSheetTextParams(values);
        }
        public static Builder builder() { return new Builder(); }
        /**
         * Returns the styleSheetId field.
         * @return the protocol field value
         */
        @Nullable public String styleSheetId() {
            return (String) value("styleSheetId");
        }
        public static final class Builder {
            private final Map<String, Object> values = new LinkedHashMap<>();
            /**
             * Sets the styleSheetId field.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder styleSheetId(@Nullable String value) {
                if (value == null) values.remove("styleSheetId");
                else values.put("styleSheetId", jsonValue(value));
                return this;
            }
            public GetStyleSheetTextParams build() {
                if (!values.containsKey("styleSheetId")) throw new IllegalStateException("Missing required CDP field: styleSheetId");
                return new GetStyleSheetTextParams(values);
            }
        }
    }
    /**
     * Returns the current textual content for a stylesheet.
     */
    public static final class GetStyleSheetTextResult extends CdpObject {
        private GetStyleSheetTextResult(Map<String, Object> values) { super(values); }
        @Nullable public static GetStyleSheetTextResult fromMap(@Nullable Map<String, Object> values) {
            return values == null ? null : new GetStyleSheetTextResult(values);
        }
        public static Builder builder() { return new Builder(); }
        /**
         * The stylesheet text.
         * @return the protocol field value
         */
        @Nullable public String text() {
            return (String) value("text");
        }
        public static final class Builder {
            private final Map<String, Object> values = new LinkedHashMap<>();
            /**
             * The stylesheet text.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder text(@Nullable String value) {
                if (value == null) values.remove("text");
                else values.put("text", jsonValue(value));
                return this;
            }
            public GetStyleSheetTextResult build() {
                if (!values.containsKey("text")) throw new IllegalStateException("Missing required CDP field: text");
                return new GetStyleSheetTextResult(values);
            }
        }
    }
    /**
     * Returns all layers parsed by the rendering engine for the tree scope of a node. Given a DOM element identified by nodeId, getLayersForNode returns the root layer for the nearest ancestor document or shadow root. The layer root contains the full layer tree for the tree scope and their ordering.
     * <p><b>Experimental:</b> this part of CDP may change without notice.
     */
    public static final class GetLayersForNodeParams extends CdpObject {
        private GetLayersForNodeParams(Map<String, Object> values) { super(values); }
        @Nullable public static GetLayersForNodeParams fromMap(@Nullable Map<String, Object> values) {
            return values == null ? null : new GetLayersForNodeParams(values);
        }
        public static Builder builder() { return new Builder(); }
        /**
         * Returns the nodeId field.
         * @return the protocol field value
         */
        @Nullable public Long nodeId() {
            return numberAsLong(value("nodeId"));
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
            public GetLayersForNodeParams build() {
                if (!values.containsKey("nodeId")) throw new IllegalStateException("Missing required CDP field: nodeId");
                return new GetLayersForNodeParams(values);
            }
        }
    }
    /**
     * Returns all layers parsed by the rendering engine for the tree scope of a node. Given a DOM element identified by nodeId, getLayersForNode returns the root layer for the nearest ancestor document or shadow root. The layer root contains the full layer tree for the tree scope and their ordering.
     * <p><b>Experimental:</b> this part of CDP may change without notice.
     */
    public static final class GetLayersForNodeResult extends CdpObject {
        private GetLayersForNodeResult(Map<String, Object> values) { super(values); }
        @Nullable public static GetLayersForNodeResult fromMap(@Nullable Map<String, Object> values) {
            return values == null ? null : new GetLayersForNodeResult(values);
        }
        public static Builder builder() { return new Builder(); }
        /**
         * Returns the rootLayer field.
         * @return the protocol field value
         */
        @Nullable public CSS.CSSLayerData rootLayer() {
            return CSS.CSSLayerData.fromMap(objectMap(value("rootLayer")));
        }
        public static final class Builder {
            private final Map<String, Object> values = new LinkedHashMap<>();
            /**
             * Sets the rootLayer field.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder rootLayer(@Nullable CSS.CSSLayerData value) {
                if (value == null) values.remove("rootLayer");
                else values.put("rootLayer", jsonValue(value));
                return this;
            }
            public GetLayersForNodeResult build() {
                if (!values.containsKey("rootLayer")) throw new IllegalStateException("Missing required CDP field: rootLayer");
                return new GetLayersForNodeResult(values);
            }
        }
    }
    /**
     * Given a CSS selector text and a style sheet ID, getLocationForSelector returns an array of locations of the CSS selector in the style sheet.
     * <p><b>Experimental:</b> this part of CDP may change without notice.
     */
    public static final class GetLocationForSelectorParams extends CdpObject {
        private GetLocationForSelectorParams(Map<String, Object> values) { super(values); }
        @Nullable public static GetLocationForSelectorParams fromMap(@Nullable Map<String, Object> values) {
            return values == null ? null : new GetLocationForSelectorParams(values);
        }
        public static Builder builder() { return new Builder(); }
        /**
         * Returns the styleSheetId field.
         * @return the protocol field value
         */
        @Nullable public String styleSheetId() {
            return (String) value("styleSheetId");
        }
        /**
         * Returns the selectorText field.
         * @return the protocol field value
         */
        @Nullable public String selectorText() {
            return (String) value("selectorText");
        }
        public static final class Builder {
            private final Map<String, Object> values = new LinkedHashMap<>();
            /**
             * Sets the styleSheetId field.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder styleSheetId(@Nullable String value) {
                if (value == null) values.remove("styleSheetId");
                else values.put("styleSheetId", jsonValue(value));
                return this;
            }
            /**
             * Sets the selectorText field.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder selectorText(@Nullable String value) {
                if (value == null) values.remove("selectorText");
                else values.put("selectorText", jsonValue(value));
                return this;
            }
            public GetLocationForSelectorParams build() {
                if (!values.containsKey("styleSheetId")) throw new IllegalStateException("Missing required CDP field: styleSheetId");
                if (!values.containsKey("selectorText")) throw new IllegalStateException("Missing required CDP field: selectorText");
                return new GetLocationForSelectorParams(values);
            }
        }
    }
    /**
     * Given a CSS selector text and a style sheet ID, getLocationForSelector returns an array of locations of the CSS selector in the style sheet.
     * <p><b>Experimental:</b> this part of CDP may change without notice.
     */
    public static final class GetLocationForSelectorResult extends CdpObject {
        private GetLocationForSelectorResult(Map<String, Object> values) { super(values); }
        @Nullable public static GetLocationForSelectorResult fromMap(@Nullable Map<String, Object> values) {
            return values == null ? null : new GetLocationForSelectorResult(values);
        }
        public static Builder builder() { return new Builder(); }
        /**
         * Returns the ranges field.
         * @return the protocol field value
         */
        @Nullable public java.util.List<CSS.SourceRange> ranges() {
            return list(value("ranges"), element0 -> CSS.SourceRange.fromMap(objectMap(element0)));
        }
        public static final class Builder {
            private final Map<String, Object> values = new LinkedHashMap<>();
            /**
             * Sets the ranges field.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder ranges(@Nullable java.util.List<CSS.SourceRange> value) {
                if (value == null) values.remove("ranges");
                else values.put("ranges", jsonValue(value));
                return this;
            }
            public GetLocationForSelectorResult build() {
                if (!values.containsKey("ranges")) throw new IllegalStateException("Missing required CDP field: ranges");
                return new GetLocationForSelectorResult(values);
            }
        }
    }
    /**
     * Starts tracking the given node for the computed style updates and whenever the computed style is updated for node, it queues a {@code computedStyleUpdated} event with throttling. There can only be 1 node tracked for computed style updates so passing a new node id removes tracking from the previous node. Pass {@code undefined} to disable tracking.
     * <p><b>Experimental:</b> this part of CDP may change without notice.
     */
    public static final class TrackComputedStyleUpdatesForNodeParams extends CdpObject {
        private TrackComputedStyleUpdatesForNodeParams(Map<String, Object> values) { super(values); }
        @Nullable public static TrackComputedStyleUpdatesForNodeParams fromMap(@Nullable Map<String, Object> values) {
            return values == null ? null : new TrackComputedStyleUpdatesForNodeParams(values);
        }
        public static Builder builder() { return new Builder(); }
        /**
         * Returns the nodeId field.
         * @return the protocol field value
         */
        @Nullable public Long nodeId() {
            return numberAsLong(value("nodeId"));
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
            public TrackComputedStyleUpdatesForNodeParams build() {
                return new TrackComputedStyleUpdatesForNodeParams(values);
            }
        }
    }
    /**
     * Starts tracking the given node for the computed style updates and whenever the computed style is updated for node, it queues a {@code computedStyleUpdated} event with throttling. There can only be 1 node tracked for computed style updates so passing a new node id removes tracking from the previous node. Pass {@code undefined} to disable tracking.
     * <p><b>Experimental:</b> this part of CDP may change without notice.
     */
    public static final class TrackComputedStyleUpdatesForNodeResult extends CdpObject {
        private TrackComputedStyleUpdatesForNodeResult(Map<String, Object> values) { super(values); }
        @Nullable public static TrackComputedStyleUpdatesForNodeResult fromMap(@Nullable Map<String, Object> values) {
            return values == null ? null : new TrackComputedStyleUpdatesForNodeResult(values);
        }
        public static Builder builder() { return new Builder(); }
        public static final class Builder {
            private final Map<String, Object> values = new LinkedHashMap<>();
            public TrackComputedStyleUpdatesForNodeResult build() {
                return new TrackComputedStyleUpdatesForNodeResult(values);
            }
        }
    }
    /**
     * Starts tracking the given computed styles for updates. The specified array of properties replaces the one previously specified. Pass empty array to disable tracking. Use takeComputedStyleUpdates to retrieve the list of nodes that had properties modified. The changes to computed style properties are only tracked for nodes pushed to the front-end by the DOM agent. If no changes to the tracked properties occur after the node has been pushed to the front-end, no updates will be issued for the node.
     * <p><b>Experimental:</b> this part of CDP may change without notice.
     */
    public static final class TrackComputedStyleUpdatesParams extends CdpObject {
        private TrackComputedStyleUpdatesParams(Map<String, Object> values) { super(values); }
        @Nullable public static TrackComputedStyleUpdatesParams fromMap(@Nullable Map<String, Object> values) {
            return values == null ? null : new TrackComputedStyleUpdatesParams(values);
        }
        public static Builder builder() { return new Builder(); }
        /**
         * Returns the propertiesToTrack field.
         * @return the protocol field value
         */
        @Nullable public java.util.List<CSS.CSSComputedStyleProperty> propertiesToTrack() {
            return list(value("propertiesToTrack"), element0 -> CSS.CSSComputedStyleProperty.fromMap(objectMap(element0)));
        }
        public static final class Builder {
            private final Map<String, Object> values = new LinkedHashMap<>();
            /**
             * Sets the propertiesToTrack field.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder propertiesToTrack(@Nullable java.util.List<CSS.CSSComputedStyleProperty> value) {
                if (value == null) values.remove("propertiesToTrack");
                else values.put("propertiesToTrack", jsonValue(value));
                return this;
            }
            public TrackComputedStyleUpdatesParams build() {
                if (!values.containsKey("propertiesToTrack")) throw new IllegalStateException("Missing required CDP field: propertiesToTrack");
                return new TrackComputedStyleUpdatesParams(values);
            }
        }
    }
    /**
     * Starts tracking the given computed styles for updates. The specified array of properties replaces the one previously specified. Pass empty array to disable tracking. Use takeComputedStyleUpdates to retrieve the list of nodes that had properties modified. The changes to computed style properties are only tracked for nodes pushed to the front-end by the DOM agent. If no changes to the tracked properties occur after the node has been pushed to the front-end, no updates will be issued for the node.
     * <p><b>Experimental:</b> this part of CDP may change without notice.
     */
    public static final class TrackComputedStyleUpdatesResult extends CdpObject {
        private TrackComputedStyleUpdatesResult(Map<String, Object> values) { super(values); }
        @Nullable public static TrackComputedStyleUpdatesResult fromMap(@Nullable Map<String, Object> values) {
            return values == null ? null : new TrackComputedStyleUpdatesResult(values);
        }
        public static Builder builder() { return new Builder(); }
        public static final class Builder {
            private final Map<String, Object> values = new LinkedHashMap<>();
            public TrackComputedStyleUpdatesResult build() {
                return new TrackComputedStyleUpdatesResult(values);
            }
        }
    }
    /**
     * Polls the next batch of computed style updates.
     * <p><b>Experimental:</b> this part of CDP may change without notice.
     */
    public static final class TakeComputedStyleUpdatesParams extends CdpObject {
        private TakeComputedStyleUpdatesParams(Map<String, Object> values) { super(values); }
        @Nullable public static TakeComputedStyleUpdatesParams fromMap(@Nullable Map<String, Object> values) {
            return values == null ? null : new TakeComputedStyleUpdatesParams(values);
        }
        public static Builder builder() { return new Builder(); }
        public static final class Builder {
            private final Map<String, Object> values = new LinkedHashMap<>();
            public TakeComputedStyleUpdatesParams build() {
                return new TakeComputedStyleUpdatesParams(values);
            }
        }
    }
    /**
     * Polls the next batch of computed style updates.
     * <p><b>Experimental:</b> this part of CDP may change without notice.
     */
    public static final class TakeComputedStyleUpdatesResult extends CdpObject {
        private TakeComputedStyleUpdatesResult(Map<String, Object> values) { super(values); }
        @Nullable public static TakeComputedStyleUpdatesResult fromMap(@Nullable Map<String, Object> values) {
            return values == null ? null : new TakeComputedStyleUpdatesResult(values);
        }
        public static Builder builder() { return new Builder(); }
        /**
         * The list of node Ids that have their tracked computed styles updated.
         * @return the protocol field value
         */
        @Nullable public java.util.List<Long> nodeIds() {
            return list(value("nodeIds"), element0 -> numberAsLong(element0));
        }
        public static final class Builder {
            private final Map<String, Object> values = new LinkedHashMap<>();
            /**
             * The list of node Ids that have their tracked computed styles updated.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder nodeIds(@Nullable java.util.List<Long> value) {
                if (value == null) values.remove("nodeIds");
                else values.put("nodeIds", jsonValue(value));
                return this;
            }
            public TakeComputedStyleUpdatesResult build() {
                if (!values.containsKey("nodeIds")) throw new IllegalStateException("Missing required CDP field: nodeIds");
                return new TakeComputedStyleUpdatesResult(values);
            }
        }
    }
    /**
     * Find a rule with the given active property for the given node and set the new value for this property
     */
    public static final class SetEffectivePropertyValueForNodeParams extends CdpObject {
        private SetEffectivePropertyValueForNodeParams(Map<String, Object> values) { super(values); }
        @Nullable public static SetEffectivePropertyValueForNodeParams fromMap(@Nullable Map<String, Object> values) {
            return values == null ? null : new SetEffectivePropertyValueForNodeParams(values);
        }
        public static Builder builder() { return new Builder(); }
        /**
         * The element id for which to set property.
         * @return the protocol field value
         */
        @Nullable public Long nodeId() {
            return numberAsLong(value("nodeId"));
        }
        /**
         * Returns the propertyName field.
         * @return the protocol field value
         */
        @Nullable public String propertyName() {
            return (String) value("propertyName");
        }
        /**
         * Returns the value field.
         * @return the protocol field value
         */
        @Nullable public String value() {
            return (String) value("value");
        }
        public static final class Builder {
            private final Map<String, Object> values = new LinkedHashMap<>();
            /**
             * The element id for which to set property.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder nodeId(@Nullable Long value) {
                if (value == null) values.remove("nodeId");
                else values.put("nodeId", jsonValue(value));
                return this;
            }
            /**
             * Sets the propertyName field.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder propertyName(@Nullable String value) {
                if (value == null) values.remove("propertyName");
                else values.put("propertyName", jsonValue(value));
                return this;
            }
            /**
             * Sets the value field.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder value(@Nullable String value) {
                if (value == null) values.remove("value");
                else values.put("value", jsonValue(value));
                return this;
            }
            public SetEffectivePropertyValueForNodeParams build() {
                if (!values.containsKey("nodeId")) throw new IllegalStateException("Missing required CDP field: nodeId");
                if (!values.containsKey("propertyName")) throw new IllegalStateException("Missing required CDP field: propertyName");
                if (!values.containsKey("value")) throw new IllegalStateException("Missing required CDP field: value");
                return new SetEffectivePropertyValueForNodeParams(values);
            }
        }
    }
    /**
     * Find a rule with the given active property for the given node and set the new value for this property
     */
    public static final class SetEffectivePropertyValueForNodeResult extends CdpObject {
        private SetEffectivePropertyValueForNodeResult(Map<String, Object> values) { super(values); }
        @Nullable public static SetEffectivePropertyValueForNodeResult fromMap(@Nullable Map<String, Object> values) {
            return values == null ? null : new SetEffectivePropertyValueForNodeResult(values);
        }
        public static Builder builder() { return new Builder(); }
        public static final class Builder {
            private final Map<String, Object> values = new LinkedHashMap<>();
            public SetEffectivePropertyValueForNodeResult build() {
                return new SetEffectivePropertyValueForNodeResult(values);
            }
        }
    }
    /**
     * Modifies the property rule property name.
     */
    public static final class SetPropertyRulePropertyNameParams extends CdpObject {
        private SetPropertyRulePropertyNameParams(Map<String, Object> values) { super(values); }
        @Nullable public static SetPropertyRulePropertyNameParams fromMap(@Nullable Map<String, Object> values) {
            return values == null ? null : new SetPropertyRulePropertyNameParams(values);
        }
        public static Builder builder() { return new Builder(); }
        /**
         * Returns the styleSheetId field.
         * @return the protocol field value
         */
        @Nullable public String styleSheetId() {
            return (String) value("styleSheetId");
        }
        /**
         * Returns the range field.
         * @return the protocol field value
         */
        @Nullable public CSS.SourceRange range() {
            return CSS.SourceRange.fromMap(objectMap(value("range")));
        }
        /**
         * Returns the propertyName field.
         * @return the protocol field value
         */
        @Nullable public String propertyName() {
            return (String) value("propertyName");
        }
        public static final class Builder {
            private final Map<String, Object> values = new LinkedHashMap<>();
            /**
             * Sets the styleSheetId field.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder styleSheetId(@Nullable String value) {
                if (value == null) values.remove("styleSheetId");
                else values.put("styleSheetId", jsonValue(value));
                return this;
            }
            /**
             * Sets the range field.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder range(@Nullable CSS.SourceRange value) {
                if (value == null) values.remove("range");
                else values.put("range", jsonValue(value));
                return this;
            }
            /**
             * Sets the propertyName field.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder propertyName(@Nullable String value) {
                if (value == null) values.remove("propertyName");
                else values.put("propertyName", jsonValue(value));
                return this;
            }
            public SetPropertyRulePropertyNameParams build() {
                if (!values.containsKey("styleSheetId")) throw new IllegalStateException("Missing required CDP field: styleSheetId");
                if (!values.containsKey("range")) throw new IllegalStateException("Missing required CDP field: range");
                if (!values.containsKey("propertyName")) throw new IllegalStateException("Missing required CDP field: propertyName");
                return new SetPropertyRulePropertyNameParams(values);
            }
        }
    }
    /**
     * Modifies the property rule property name.
     */
    public static final class SetPropertyRulePropertyNameResult extends CdpObject {
        private SetPropertyRulePropertyNameResult(Map<String, Object> values) { super(values); }
        @Nullable public static SetPropertyRulePropertyNameResult fromMap(@Nullable Map<String, Object> values) {
            return values == null ? null : new SetPropertyRulePropertyNameResult(values);
        }
        public static Builder builder() { return new Builder(); }
        /**
         * The resulting key text after modification.
         * @return the protocol field value
         */
        @Nullable public CSS.Value propertyName() {
            return CSS.Value.fromMap(objectMap(value("propertyName")));
        }
        public static final class Builder {
            private final Map<String, Object> values = new LinkedHashMap<>();
            /**
             * The resulting key text after modification.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder propertyName(@Nullable CSS.Value value) {
                if (value == null) values.remove("propertyName");
                else values.put("propertyName", jsonValue(value));
                return this;
            }
            public SetPropertyRulePropertyNameResult build() {
                if (!values.containsKey("propertyName")) throw new IllegalStateException("Missing required CDP field: propertyName");
                return new SetPropertyRulePropertyNameResult(values);
            }
        }
    }
    /**
     * Modifies the keyframe rule key text.
     */
    public static final class SetKeyframeKeyParams extends CdpObject {
        private SetKeyframeKeyParams(Map<String, Object> values) { super(values); }
        @Nullable public static SetKeyframeKeyParams fromMap(@Nullable Map<String, Object> values) {
            return values == null ? null : new SetKeyframeKeyParams(values);
        }
        public static Builder builder() { return new Builder(); }
        /**
         * Returns the styleSheetId field.
         * @return the protocol field value
         */
        @Nullable public String styleSheetId() {
            return (String) value("styleSheetId");
        }
        /**
         * Returns the range field.
         * @return the protocol field value
         */
        @Nullable public CSS.SourceRange range() {
            return CSS.SourceRange.fromMap(objectMap(value("range")));
        }
        /**
         * Returns the keyText field.
         * @return the protocol field value
         */
        @Nullable public String keyText() {
            return (String) value("keyText");
        }
        public static final class Builder {
            private final Map<String, Object> values = new LinkedHashMap<>();
            /**
             * Sets the styleSheetId field.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder styleSheetId(@Nullable String value) {
                if (value == null) values.remove("styleSheetId");
                else values.put("styleSheetId", jsonValue(value));
                return this;
            }
            /**
             * Sets the range field.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder range(@Nullable CSS.SourceRange value) {
                if (value == null) values.remove("range");
                else values.put("range", jsonValue(value));
                return this;
            }
            /**
             * Sets the keyText field.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder keyText(@Nullable String value) {
                if (value == null) values.remove("keyText");
                else values.put("keyText", jsonValue(value));
                return this;
            }
            public SetKeyframeKeyParams build() {
                if (!values.containsKey("styleSheetId")) throw new IllegalStateException("Missing required CDP field: styleSheetId");
                if (!values.containsKey("range")) throw new IllegalStateException("Missing required CDP field: range");
                if (!values.containsKey("keyText")) throw new IllegalStateException("Missing required CDP field: keyText");
                return new SetKeyframeKeyParams(values);
            }
        }
    }
    /**
     * Modifies the keyframe rule key text.
     */
    public static final class SetKeyframeKeyResult extends CdpObject {
        private SetKeyframeKeyResult(Map<String, Object> values) { super(values); }
        @Nullable public static SetKeyframeKeyResult fromMap(@Nullable Map<String, Object> values) {
            return values == null ? null : new SetKeyframeKeyResult(values);
        }
        public static Builder builder() { return new Builder(); }
        /**
         * The resulting key text after modification.
         * @return the protocol field value
         */
        @Nullable public CSS.Value keyText() {
            return CSS.Value.fromMap(objectMap(value("keyText")));
        }
        public static final class Builder {
            private final Map<String, Object> values = new LinkedHashMap<>();
            /**
             * The resulting key text after modification.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder keyText(@Nullable CSS.Value value) {
                if (value == null) values.remove("keyText");
                else values.put("keyText", jsonValue(value));
                return this;
            }
            public SetKeyframeKeyResult build() {
                if (!values.containsKey("keyText")) throw new IllegalStateException("Missing required CDP field: keyText");
                return new SetKeyframeKeyResult(values);
            }
        }
    }
    /**
     * Modifies the rule selector.
     */
    public static final class SetMediaTextParams extends CdpObject {
        private SetMediaTextParams(Map<String, Object> values) { super(values); }
        @Nullable public static SetMediaTextParams fromMap(@Nullable Map<String, Object> values) {
            return values == null ? null : new SetMediaTextParams(values);
        }
        public static Builder builder() { return new Builder(); }
        /**
         * Returns the styleSheetId field.
         * @return the protocol field value
         */
        @Nullable public String styleSheetId() {
            return (String) value("styleSheetId");
        }
        /**
         * Returns the range field.
         * @return the protocol field value
         */
        @Nullable public CSS.SourceRange range() {
            return CSS.SourceRange.fromMap(objectMap(value("range")));
        }
        /**
         * Returns the text field.
         * @return the protocol field value
         */
        @Nullable public String text() {
            return (String) value("text");
        }
        public static final class Builder {
            private final Map<String, Object> values = new LinkedHashMap<>();
            /**
             * Sets the styleSheetId field.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder styleSheetId(@Nullable String value) {
                if (value == null) values.remove("styleSheetId");
                else values.put("styleSheetId", jsonValue(value));
                return this;
            }
            /**
             * Sets the range field.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder range(@Nullable CSS.SourceRange value) {
                if (value == null) values.remove("range");
                else values.put("range", jsonValue(value));
                return this;
            }
            /**
             * Sets the text field.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder text(@Nullable String value) {
                if (value == null) values.remove("text");
                else values.put("text", jsonValue(value));
                return this;
            }
            public SetMediaTextParams build() {
                if (!values.containsKey("styleSheetId")) throw new IllegalStateException("Missing required CDP field: styleSheetId");
                if (!values.containsKey("range")) throw new IllegalStateException("Missing required CDP field: range");
                if (!values.containsKey("text")) throw new IllegalStateException("Missing required CDP field: text");
                return new SetMediaTextParams(values);
            }
        }
    }
    /**
     * Modifies the rule selector.
     */
    public static final class SetMediaTextResult extends CdpObject {
        private SetMediaTextResult(Map<String, Object> values) { super(values); }
        @Nullable public static SetMediaTextResult fromMap(@Nullable Map<String, Object> values) {
            return values == null ? null : new SetMediaTextResult(values);
        }
        public static Builder builder() { return new Builder(); }
        /**
         * The resulting CSS media rule after modification.
         * @return the protocol field value
         */
        @Nullable public CSS.CSSMedia media() {
            return CSS.CSSMedia.fromMap(objectMap(value("media")));
        }
        public static final class Builder {
            private final Map<String, Object> values = new LinkedHashMap<>();
            /**
             * The resulting CSS media rule after modification.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder media(@Nullable CSS.CSSMedia value) {
                if (value == null) values.remove("media");
                else values.put("media", jsonValue(value));
                return this;
            }
            public SetMediaTextResult build() {
                if (!values.containsKey("media")) throw new IllegalStateException("Missing required CDP field: media");
                return new SetMediaTextResult(values);
            }
        }
    }
    /**
     * Modifies the expression of a container query. Deprecated. Use setContainerQueryConditionText instead.
     * <p><b>Experimental:</b> this part of CDP may change without notice.
     * @deprecated Deprecated by the Chromium DevTools Protocol.
     */
    @Deprecated
    public static final class SetContainerQueryTextParams extends CdpObject {
        private SetContainerQueryTextParams(Map<String, Object> values) { super(values); }
        @Nullable public static SetContainerQueryTextParams fromMap(@Nullable Map<String, Object> values) {
            return values == null ? null : new SetContainerQueryTextParams(values);
        }
        public static Builder builder() { return new Builder(); }
        /**
         * Returns the styleSheetId field.
         * @return the protocol field value
         */
        @Nullable public String styleSheetId() {
            return (String) value("styleSheetId");
        }
        /**
         * Returns the range field.
         * @return the protocol field value
         */
        @Nullable public CSS.SourceRange range() {
            return CSS.SourceRange.fromMap(objectMap(value("range")));
        }
        /**
         * Returns the text field.
         * @return the protocol field value
         */
        @Nullable public String text() {
            return (String) value("text");
        }
        public static final class Builder {
            private final Map<String, Object> values = new LinkedHashMap<>();
            /**
             * Sets the styleSheetId field.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder styleSheetId(@Nullable String value) {
                if (value == null) values.remove("styleSheetId");
                else values.put("styleSheetId", jsonValue(value));
                return this;
            }
            /**
             * Sets the range field.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder range(@Nullable CSS.SourceRange value) {
                if (value == null) values.remove("range");
                else values.put("range", jsonValue(value));
                return this;
            }
            /**
             * Sets the text field.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder text(@Nullable String value) {
                if (value == null) values.remove("text");
                else values.put("text", jsonValue(value));
                return this;
            }
            public SetContainerQueryTextParams build() {
                if (!values.containsKey("styleSheetId")) throw new IllegalStateException("Missing required CDP field: styleSheetId");
                if (!values.containsKey("range")) throw new IllegalStateException("Missing required CDP field: range");
                if (!values.containsKey("text")) throw new IllegalStateException("Missing required CDP field: text");
                return new SetContainerQueryTextParams(values);
            }
        }
    }
    /**
     * Modifies the expression of a container query. Deprecated. Use setContainerQueryConditionText instead.
     * <p><b>Experimental:</b> this part of CDP may change without notice.
     * @deprecated Deprecated by the Chromium DevTools Protocol.
     */
    @Deprecated
    public static final class SetContainerQueryTextResult extends CdpObject {
        private SetContainerQueryTextResult(Map<String, Object> values) { super(values); }
        @Nullable public static SetContainerQueryTextResult fromMap(@Nullable Map<String, Object> values) {
            return values == null ? null : new SetContainerQueryTextResult(values);
        }
        public static Builder builder() { return new Builder(); }
        /**
         * The resulting CSS container query rule after modification.
         * @return the protocol field value
         */
        @Nullable public CSS.CSSContainerQuery containerQuery() {
            return CSS.CSSContainerQuery.fromMap(objectMap(value("containerQuery")));
        }
        public static final class Builder {
            private final Map<String, Object> values = new LinkedHashMap<>();
            /**
             * The resulting CSS container query rule after modification.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder containerQuery(@Nullable CSS.CSSContainerQuery value) {
                if (value == null) values.remove("containerQuery");
                else values.put("containerQuery", jsonValue(value));
                return this;
            }
            public SetContainerQueryTextResult build() {
                if (!values.containsKey("containerQuery")) throw new IllegalStateException("Missing required CDP field: containerQuery");
                return new SetContainerQueryTextResult(values);
            }
        }
    }
    /**
     * Parameters for CSS.setContainerQueryConditionText.
     * <p><b>Experimental:</b> this part of CDP may change without notice.
     */
    public static final class SetContainerQueryConditionTextParams extends CdpObject {
        private SetContainerQueryConditionTextParams(Map<String, Object> values) { super(values); }
        @Nullable public static SetContainerQueryConditionTextParams fromMap(@Nullable Map<String, Object> values) {
            return values == null ? null : new SetContainerQueryConditionTextParams(values);
        }
        public static Builder builder() { return new Builder(); }
        /**
         * Returns the styleSheetId field.
         * @return the protocol field value
         */
        @Nullable public String styleSheetId() {
            return (String) value("styleSheetId");
        }
        /**
         * Returns the range field.
         * @return the protocol field value
         */
        @Nullable public CSS.SourceRange range() {
            return CSS.SourceRange.fromMap(objectMap(value("range")));
        }
        /**
         * Returns the text field.
         * @return the protocol field value
         */
        @Nullable public String text() {
            return (String) value("text");
        }
        public static final class Builder {
            private final Map<String, Object> values = new LinkedHashMap<>();
            /**
             * Sets the styleSheetId field.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder styleSheetId(@Nullable String value) {
                if (value == null) values.remove("styleSheetId");
                else values.put("styleSheetId", jsonValue(value));
                return this;
            }
            /**
             * Sets the range field.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder range(@Nullable CSS.SourceRange value) {
                if (value == null) values.remove("range");
                else values.put("range", jsonValue(value));
                return this;
            }
            /**
             * Sets the text field.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder text(@Nullable String value) {
                if (value == null) values.remove("text");
                else values.put("text", jsonValue(value));
                return this;
            }
            public SetContainerQueryConditionTextParams build() {
                if (!values.containsKey("styleSheetId")) throw new IllegalStateException("Missing required CDP field: styleSheetId");
                if (!values.containsKey("range")) throw new IllegalStateException("Missing required CDP field: range");
                if (!values.containsKey("text")) throw new IllegalStateException("Missing required CDP field: text");
                return new SetContainerQueryConditionTextParams(values);
            }
        }
    }
    /**
     * Result of CSS.setContainerQueryConditionText.
     * <p><b>Experimental:</b> this part of CDP may change without notice.
     */
    public static final class SetContainerQueryConditionTextResult extends CdpObject {
        private SetContainerQueryConditionTextResult(Map<String, Object> values) { super(values); }
        @Nullable public static SetContainerQueryConditionTextResult fromMap(@Nullable Map<String, Object> values) {
            return values == null ? null : new SetContainerQueryConditionTextResult(values);
        }
        public static Builder builder() { return new Builder(); }
        /**
         * The resulting CSS container query rule after modification.
         * @return the protocol field value
         */
        @Nullable public CSS.CSSContainerQuery containerQuery() {
            return CSS.CSSContainerQuery.fromMap(objectMap(value("containerQuery")));
        }
        public static final class Builder {
            private final Map<String, Object> values = new LinkedHashMap<>();
            /**
             * The resulting CSS container query rule after modification.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder containerQuery(@Nullable CSS.CSSContainerQuery value) {
                if (value == null) values.remove("containerQuery");
                else values.put("containerQuery", jsonValue(value));
                return this;
            }
            public SetContainerQueryConditionTextResult build() {
                if (!values.containsKey("containerQuery")) throw new IllegalStateException("Missing required CDP field: containerQuery");
                return new SetContainerQueryConditionTextResult(values);
            }
        }
    }
    /**
     * Modifies the expression of a supports at-rule.
     * <p><b>Experimental:</b> this part of CDP may change without notice.
     */
    public static final class SetSupportsTextParams extends CdpObject {
        private SetSupportsTextParams(Map<String, Object> values) { super(values); }
        @Nullable public static SetSupportsTextParams fromMap(@Nullable Map<String, Object> values) {
            return values == null ? null : new SetSupportsTextParams(values);
        }
        public static Builder builder() { return new Builder(); }
        /**
         * Returns the styleSheetId field.
         * @return the protocol field value
         */
        @Nullable public String styleSheetId() {
            return (String) value("styleSheetId");
        }
        /**
         * Returns the range field.
         * @return the protocol field value
         */
        @Nullable public CSS.SourceRange range() {
            return CSS.SourceRange.fromMap(objectMap(value("range")));
        }
        /**
         * Returns the text field.
         * @return the protocol field value
         */
        @Nullable public String text() {
            return (String) value("text");
        }
        public static final class Builder {
            private final Map<String, Object> values = new LinkedHashMap<>();
            /**
             * Sets the styleSheetId field.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder styleSheetId(@Nullable String value) {
                if (value == null) values.remove("styleSheetId");
                else values.put("styleSheetId", jsonValue(value));
                return this;
            }
            /**
             * Sets the range field.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder range(@Nullable CSS.SourceRange value) {
                if (value == null) values.remove("range");
                else values.put("range", jsonValue(value));
                return this;
            }
            /**
             * Sets the text field.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder text(@Nullable String value) {
                if (value == null) values.remove("text");
                else values.put("text", jsonValue(value));
                return this;
            }
            public SetSupportsTextParams build() {
                if (!values.containsKey("styleSheetId")) throw new IllegalStateException("Missing required CDP field: styleSheetId");
                if (!values.containsKey("range")) throw new IllegalStateException("Missing required CDP field: range");
                if (!values.containsKey("text")) throw new IllegalStateException("Missing required CDP field: text");
                return new SetSupportsTextParams(values);
            }
        }
    }
    /**
     * Modifies the expression of a supports at-rule.
     * <p><b>Experimental:</b> this part of CDP may change without notice.
     */
    public static final class SetSupportsTextResult extends CdpObject {
        private SetSupportsTextResult(Map<String, Object> values) { super(values); }
        @Nullable public static SetSupportsTextResult fromMap(@Nullable Map<String, Object> values) {
            return values == null ? null : new SetSupportsTextResult(values);
        }
        public static Builder builder() { return new Builder(); }
        /**
         * The resulting CSS Supports rule after modification.
         * @return the protocol field value
         */
        @Nullable public CSS.CSSSupports supports() {
            return CSS.CSSSupports.fromMap(objectMap(value("supports")));
        }
        public static final class Builder {
            private final Map<String, Object> values = new LinkedHashMap<>();
            /**
             * The resulting CSS Supports rule after modification.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder supports(@Nullable CSS.CSSSupports value) {
                if (value == null) values.remove("supports");
                else values.put("supports", jsonValue(value));
                return this;
            }
            public SetSupportsTextResult build() {
                if (!values.containsKey("supports")) throw new IllegalStateException("Missing required CDP field: supports");
                return new SetSupportsTextResult(values);
            }
        }
    }
    /**
     * Modifies the expression of a navigation at-rule.
     * <p><b>Experimental:</b> this part of CDP may change without notice.
     */
    public static final class SetNavigationTextParams extends CdpObject {
        private SetNavigationTextParams(Map<String, Object> values) { super(values); }
        @Nullable public static SetNavigationTextParams fromMap(@Nullable Map<String, Object> values) {
            return values == null ? null : new SetNavigationTextParams(values);
        }
        public static Builder builder() { return new Builder(); }
        /**
         * Returns the styleSheetId field.
         * @return the protocol field value
         */
        @Nullable public String styleSheetId() {
            return (String) value("styleSheetId");
        }
        /**
         * Returns the range field.
         * @return the protocol field value
         */
        @Nullable public CSS.SourceRange range() {
            return CSS.SourceRange.fromMap(objectMap(value("range")));
        }
        /**
         * Returns the text field.
         * @return the protocol field value
         */
        @Nullable public String text() {
            return (String) value("text");
        }
        public static final class Builder {
            private final Map<String, Object> values = new LinkedHashMap<>();
            /**
             * Sets the styleSheetId field.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder styleSheetId(@Nullable String value) {
                if (value == null) values.remove("styleSheetId");
                else values.put("styleSheetId", jsonValue(value));
                return this;
            }
            /**
             * Sets the range field.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder range(@Nullable CSS.SourceRange value) {
                if (value == null) values.remove("range");
                else values.put("range", jsonValue(value));
                return this;
            }
            /**
             * Sets the text field.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder text(@Nullable String value) {
                if (value == null) values.remove("text");
                else values.put("text", jsonValue(value));
                return this;
            }
            public SetNavigationTextParams build() {
                if (!values.containsKey("styleSheetId")) throw new IllegalStateException("Missing required CDP field: styleSheetId");
                if (!values.containsKey("range")) throw new IllegalStateException("Missing required CDP field: range");
                if (!values.containsKey("text")) throw new IllegalStateException("Missing required CDP field: text");
                return new SetNavigationTextParams(values);
            }
        }
    }
    /**
     * Modifies the expression of a navigation at-rule.
     * <p><b>Experimental:</b> this part of CDP may change without notice.
     */
    public static final class SetNavigationTextResult extends CdpObject {
        private SetNavigationTextResult(Map<String, Object> values) { super(values); }
        @Nullable public static SetNavigationTextResult fromMap(@Nullable Map<String, Object> values) {
            return values == null ? null : new SetNavigationTextResult(values);
        }
        public static Builder builder() { return new Builder(); }
        /**
         * The resulting CSS Navigation rule after modification.
         * @return the protocol field value
         */
        @Nullable public CSS.CSSNavigation navigation() {
            return CSS.CSSNavigation.fromMap(objectMap(value("navigation")));
        }
        public static final class Builder {
            private final Map<String, Object> values = new LinkedHashMap<>();
            /**
             * The resulting CSS Navigation rule after modification.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder navigation(@Nullable CSS.CSSNavigation value) {
                if (value == null) values.remove("navigation");
                else values.put("navigation", jsonValue(value));
                return this;
            }
            public SetNavigationTextResult build() {
                if (!values.containsKey("navigation")) throw new IllegalStateException("Missing required CDP field: navigation");
                return new SetNavigationTextResult(values);
            }
        }
    }
    /**
     * Modifies the expression of a scope at-rule.
     * <p><b>Experimental:</b> this part of CDP may change without notice.
     */
    public static final class SetScopeTextParams extends CdpObject {
        private SetScopeTextParams(Map<String, Object> values) { super(values); }
        @Nullable public static SetScopeTextParams fromMap(@Nullable Map<String, Object> values) {
            return values == null ? null : new SetScopeTextParams(values);
        }
        public static Builder builder() { return new Builder(); }
        /**
         * Returns the styleSheetId field.
         * @return the protocol field value
         */
        @Nullable public String styleSheetId() {
            return (String) value("styleSheetId");
        }
        /**
         * Returns the range field.
         * @return the protocol field value
         */
        @Nullable public CSS.SourceRange range() {
            return CSS.SourceRange.fromMap(objectMap(value("range")));
        }
        /**
         * Returns the text field.
         * @return the protocol field value
         */
        @Nullable public String text() {
            return (String) value("text");
        }
        public static final class Builder {
            private final Map<String, Object> values = new LinkedHashMap<>();
            /**
             * Sets the styleSheetId field.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder styleSheetId(@Nullable String value) {
                if (value == null) values.remove("styleSheetId");
                else values.put("styleSheetId", jsonValue(value));
                return this;
            }
            /**
             * Sets the range field.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder range(@Nullable CSS.SourceRange value) {
                if (value == null) values.remove("range");
                else values.put("range", jsonValue(value));
                return this;
            }
            /**
             * Sets the text field.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder text(@Nullable String value) {
                if (value == null) values.remove("text");
                else values.put("text", jsonValue(value));
                return this;
            }
            public SetScopeTextParams build() {
                if (!values.containsKey("styleSheetId")) throw new IllegalStateException("Missing required CDP field: styleSheetId");
                if (!values.containsKey("range")) throw new IllegalStateException("Missing required CDP field: range");
                if (!values.containsKey("text")) throw new IllegalStateException("Missing required CDP field: text");
                return new SetScopeTextParams(values);
            }
        }
    }
    /**
     * Modifies the expression of a scope at-rule.
     * <p><b>Experimental:</b> this part of CDP may change without notice.
     */
    public static final class SetScopeTextResult extends CdpObject {
        private SetScopeTextResult(Map<String, Object> values) { super(values); }
        @Nullable public static SetScopeTextResult fromMap(@Nullable Map<String, Object> values) {
            return values == null ? null : new SetScopeTextResult(values);
        }
        public static Builder builder() { return new Builder(); }
        /**
         * The resulting CSS Scope rule after modification.
         * @return the protocol field value
         */
        @Nullable public CSS.CSSScope scope() {
            return CSS.CSSScope.fromMap(objectMap(value("scope")));
        }
        public static final class Builder {
            private final Map<String, Object> values = new LinkedHashMap<>();
            /**
             * The resulting CSS Scope rule after modification.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder scope(@Nullable CSS.CSSScope value) {
                if (value == null) values.remove("scope");
                else values.put("scope", jsonValue(value));
                return this;
            }
            public SetScopeTextResult build() {
                if (!values.containsKey("scope")) throw new IllegalStateException("Missing required CDP field: scope");
                return new SetScopeTextResult(values);
            }
        }
    }
    /**
     * Modifies the rule selector.
     */
    public static final class SetRuleSelectorParams extends CdpObject {
        private SetRuleSelectorParams(Map<String, Object> values) { super(values); }
        @Nullable public static SetRuleSelectorParams fromMap(@Nullable Map<String, Object> values) {
            return values == null ? null : new SetRuleSelectorParams(values);
        }
        public static Builder builder() { return new Builder(); }
        /**
         * Returns the styleSheetId field.
         * @return the protocol field value
         */
        @Nullable public String styleSheetId() {
            return (String) value("styleSheetId");
        }
        /**
         * Returns the range field.
         * @return the protocol field value
         */
        @Nullable public CSS.SourceRange range() {
            return CSS.SourceRange.fromMap(objectMap(value("range")));
        }
        /**
         * Returns the selector field.
         * @return the protocol field value
         */
        @Nullable public String selector() {
            return (String) value("selector");
        }
        public static final class Builder {
            private final Map<String, Object> values = new LinkedHashMap<>();
            /**
             * Sets the styleSheetId field.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder styleSheetId(@Nullable String value) {
                if (value == null) values.remove("styleSheetId");
                else values.put("styleSheetId", jsonValue(value));
                return this;
            }
            /**
             * Sets the range field.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder range(@Nullable CSS.SourceRange value) {
                if (value == null) values.remove("range");
                else values.put("range", jsonValue(value));
                return this;
            }
            /**
             * Sets the selector field.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder selector(@Nullable String value) {
                if (value == null) values.remove("selector");
                else values.put("selector", jsonValue(value));
                return this;
            }
            public SetRuleSelectorParams build() {
                if (!values.containsKey("styleSheetId")) throw new IllegalStateException("Missing required CDP field: styleSheetId");
                if (!values.containsKey("range")) throw new IllegalStateException("Missing required CDP field: range");
                if (!values.containsKey("selector")) throw new IllegalStateException("Missing required CDP field: selector");
                return new SetRuleSelectorParams(values);
            }
        }
    }
    /**
     * Modifies the rule selector.
     */
    public static final class SetRuleSelectorResult extends CdpObject {
        private SetRuleSelectorResult(Map<String, Object> values) { super(values); }
        @Nullable public static SetRuleSelectorResult fromMap(@Nullable Map<String, Object> values) {
            return values == null ? null : new SetRuleSelectorResult(values);
        }
        public static Builder builder() { return new Builder(); }
        /**
         * The resulting selector list after modification.
         * @return the protocol field value
         */
        @Nullable public CSS.SelectorList selectorList() {
            return CSS.SelectorList.fromMap(objectMap(value("selectorList")));
        }
        public static final class Builder {
            private final Map<String, Object> values = new LinkedHashMap<>();
            /**
             * The resulting selector list after modification.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder selectorList(@Nullable CSS.SelectorList value) {
                if (value == null) values.remove("selectorList");
                else values.put("selectorList", jsonValue(value));
                return this;
            }
            public SetRuleSelectorResult build() {
                if (!values.containsKey("selectorList")) throw new IllegalStateException("Missing required CDP field: selectorList");
                return new SetRuleSelectorResult(values);
            }
        }
    }
    /**
     * Sets the new stylesheet text.
     */
    public static final class SetStyleSheetTextParams extends CdpObject {
        private SetStyleSheetTextParams(Map<String, Object> values) { super(values); }
        @Nullable public static SetStyleSheetTextParams fromMap(@Nullable Map<String, Object> values) {
            return values == null ? null : new SetStyleSheetTextParams(values);
        }
        public static Builder builder() { return new Builder(); }
        /**
         * Returns the styleSheetId field.
         * @return the protocol field value
         */
        @Nullable public String styleSheetId() {
            return (String) value("styleSheetId");
        }
        /**
         * Returns the text field.
         * @return the protocol field value
         */
        @Nullable public String text() {
            return (String) value("text");
        }
        public static final class Builder {
            private final Map<String, Object> values = new LinkedHashMap<>();
            /**
             * Sets the styleSheetId field.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder styleSheetId(@Nullable String value) {
                if (value == null) values.remove("styleSheetId");
                else values.put("styleSheetId", jsonValue(value));
                return this;
            }
            /**
             * Sets the text field.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder text(@Nullable String value) {
                if (value == null) values.remove("text");
                else values.put("text", jsonValue(value));
                return this;
            }
            public SetStyleSheetTextParams build() {
                if (!values.containsKey("styleSheetId")) throw new IllegalStateException("Missing required CDP field: styleSheetId");
                if (!values.containsKey("text")) throw new IllegalStateException("Missing required CDP field: text");
                return new SetStyleSheetTextParams(values);
            }
        }
    }
    /**
     * Sets the new stylesheet text.
     */
    public static final class SetStyleSheetTextResult extends CdpObject {
        private SetStyleSheetTextResult(Map<String, Object> values) { super(values); }
        @Nullable public static SetStyleSheetTextResult fromMap(@Nullable Map<String, Object> values) {
            return values == null ? null : new SetStyleSheetTextResult(values);
        }
        public static Builder builder() { return new Builder(); }
        /**
         * URL of source map associated with script (if any).
         * @return the protocol field value
         */
        @Nullable public String sourceMapURL() {
            return (String) value("sourceMapURL");
        }
        public static final class Builder {
            private final Map<String, Object> values = new LinkedHashMap<>();
            /**
             * URL of source map associated with script (if any).
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder sourceMapURL(@Nullable String value) {
                if (value == null) values.remove("sourceMapURL");
                else values.put("sourceMapURL", jsonValue(value));
                return this;
            }
            public SetStyleSheetTextResult build() {
                return new SetStyleSheetTextResult(values);
            }
        }
    }
    /**
     * Applies specified style edits one after another in the given order.
     */
    public static final class SetStyleTextsParams extends CdpObject {
        private SetStyleTextsParams(Map<String, Object> values) { super(values); }
        @Nullable public static SetStyleTextsParams fromMap(@Nullable Map<String, Object> values) {
            return values == null ? null : new SetStyleTextsParams(values);
        }
        public static Builder builder() { return new Builder(); }
        /**
         * Returns the edits field.
         * @return the protocol field value
         */
        @Nullable public java.util.List<CSS.StyleDeclarationEdit> edits() {
            return list(value("edits"), element0 -> CSS.StyleDeclarationEdit.fromMap(objectMap(element0)));
        }
        /**
         * NodeId for the DOM node in whose context custom property declarations for registered properties should be validated. If omitted, declarations in the new rule text can only be validated statically, which may produce incorrect results if the declaration contains a var() for example.
         * <p><b>Experimental:</b> this part of CDP may change without notice.
         * @return the protocol field value
         */
        @Nullable public Long nodeForPropertySyntaxValidation() {
            return numberAsLong(value("nodeForPropertySyntaxValidation"));
        }
        public static final class Builder {
            private final Map<String, Object> values = new LinkedHashMap<>();
            /**
             * Sets the edits field.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder edits(@Nullable java.util.List<CSS.StyleDeclarationEdit> value) {
                if (value == null) values.remove("edits");
                else values.put("edits", jsonValue(value));
                return this;
            }
            /**
             * NodeId for the DOM node in whose context custom property declarations for registered properties should be validated. If omitted, declarations in the new rule text can only be validated statically, which may produce incorrect results if the declaration contains a var() for example.
             * <p><b>Experimental:</b> this part of CDP may change without notice.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder nodeForPropertySyntaxValidation(@Nullable Long value) {
                if (value == null) values.remove("nodeForPropertySyntaxValidation");
                else values.put("nodeForPropertySyntaxValidation", jsonValue(value));
                return this;
            }
            public SetStyleTextsParams build() {
                if (!values.containsKey("edits")) throw new IllegalStateException("Missing required CDP field: edits");
                return new SetStyleTextsParams(values);
            }
        }
    }
    /**
     * Applies specified style edits one after another in the given order.
     */
    public static final class SetStyleTextsResult extends CdpObject {
        private SetStyleTextsResult(Map<String, Object> values) { super(values); }
        @Nullable public static SetStyleTextsResult fromMap(@Nullable Map<String, Object> values) {
            return values == null ? null : new SetStyleTextsResult(values);
        }
        public static Builder builder() { return new Builder(); }
        /**
         * The resulting styles after modification.
         * @return the protocol field value
         */
        @Nullable public java.util.List<CSS.CSSStyle> styles() {
            return list(value("styles"), element0 -> CSS.CSSStyle.fromMap(objectMap(element0)));
        }
        public static final class Builder {
            private final Map<String, Object> values = new LinkedHashMap<>();
            /**
             * The resulting styles after modification.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder styles(@Nullable java.util.List<CSS.CSSStyle> value) {
                if (value == null) values.remove("styles");
                else values.put("styles", jsonValue(value));
                return this;
            }
            public SetStyleTextsResult build() {
                if (!values.containsKey("styles")) throw new IllegalStateException("Missing required CDP field: styles");
                return new SetStyleTextsResult(values);
            }
        }
    }
    /**
     * Enables the selector recording.
     */
    public static final class StartRuleUsageTrackingParams extends CdpObject {
        private StartRuleUsageTrackingParams(Map<String, Object> values) { super(values); }
        @Nullable public static StartRuleUsageTrackingParams fromMap(@Nullable Map<String, Object> values) {
            return values == null ? null : new StartRuleUsageTrackingParams(values);
        }
        public static Builder builder() { return new Builder(); }
        public static final class Builder {
            private final Map<String, Object> values = new LinkedHashMap<>();
            public StartRuleUsageTrackingParams build() {
                return new StartRuleUsageTrackingParams(values);
            }
        }
    }
    /**
     * Enables the selector recording.
     */
    public static final class StartRuleUsageTrackingResult extends CdpObject {
        private StartRuleUsageTrackingResult(Map<String, Object> values) { super(values); }
        @Nullable public static StartRuleUsageTrackingResult fromMap(@Nullable Map<String, Object> values) {
            return values == null ? null : new StartRuleUsageTrackingResult(values);
        }
        public static Builder builder() { return new Builder(); }
        public static final class Builder {
            private final Map<String, Object> values = new LinkedHashMap<>();
            public StartRuleUsageTrackingResult build() {
                return new StartRuleUsageTrackingResult(values);
            }
        }
    }
    /**
     * Stop tracking rule usage and return the list of rules that were used since last call to {@code takeCoverageDelta} (or since start of coverage instrumentation).
     */
    public static final class StopRuleUsageTrackingParams extends CdpObject {
        private StopRuleUsageTrackingParams(Map<String, Object> values) { super(values); }
        @Nullable public static StopRuleUsageTrackingParams fromMap(@Nullable Map<String, Object> values) {
            return values == null ? null : new StopRuleUsageTrackingParams(values);
        }
        public static Builder builder() { return new Builder(); }
        public static final class Builder {
            private final Map<String, Object> values = new LinkedHashMap<>();
            public StopRuleUsageTrackingParams build() {
                return new StopRuleUsageTrackingParams(values);
            }
        }
    }
    /**
     * Stop tracking rule usage and return the list of rules that were used since last call to {@code takeCoverageDelta} (or since start of coverage instrumentation).
     */
    public static final class StopRuleUsageTrackingResult extends CdpObject {
        private StopRuleUsageTrackingResult(Map<String, Object> values) { super(values); }
        @Nullable public static StopRuleUsageTrackingResult fromMap(@Nullable Map<String, Object> values) {
            return values == null ? null : new StopRuleUsageTrackingResult(values);
        }
        public static Builder builder() { return new Builder(); }
        /**
         * Returns the ruleUsage field.
         * @return the protocol field value
         */
        @Nullable public java.util.List<CSS.RuleUsage> ruleUsage() {
            return list(value("ruleUsage"), element0 -> CSS.RuleUsage.fromMap(objectMap(element0)));
        }
        public static final class Builder {
            private final Map<String, Object> values = new LinkedHashMap<>();
            /**
             * Sets the ruleUsage field.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder ruleUsage(@Nullable java.util.List<CSS.RuleUsage> value) {
                if (value == null) values.remove("ruleUsage");
                else values.put("ruleUsage", jsonValue(value));
                return this;
            }
            public StopRuleUsageTrackingResult build() {
                if (!values.containsKey("ruleUsage")) throw new IllegalStateException("Missing required CDP field: ruleUsage");
                return new StopRuleUsageTrackingResult(values);
            }
        }
    }
    /**
     * Obtain list of rules that became used since last call to this method (or since start of coverage instrumentation).
     */
    public static final class TakeCoverageDeltaParams extends CdpObject {
        private TakeCoverageDeltaParams(Map<String, Object> values) { super(values); }
        @Nullable public static TakeCoverageDeltaParams fromMap(@Nullable Map<String, Object> values) {
            return values == null ? null : new TakeCoverageDeltaParams(values);
        }
        public static Builder builder() { return new Builder(); }
        public static final class Builder {
            private final Map<String, Object> values = new LinkedHashMap<>();
            public TakeCoverageDeltaParams build() {
                return new TakeCoverageDeltaParams(values);
            }
        }
    }
    /**
     * Obtain list of rules that became used since last call to this method (or since start of coverage instrumentation).
     */
    public static final class TakeCoverageDeltaResult extends CdpObject {
        private TakeCoverageDeltaResult(Map<String, Object> values) { super(values); }
        @Nullable public static TakeCoverageDeltaResult fromMap(@Nullable Map<String, Object> values) {
            return values == null ? null : new TakeCoverageDeltaResult(values);
        }
        public static Builder builder() { return new Builder(); }
        /**
         * Returns the coverage field.
         * @return the protocol field value
         */
        @Nullable public java.util.List<CSS.RuleUsage> coverage() {
            return list(value("coverage"), element0 -> CSS.RuleUsage.fromMap(objectMap(element0)));
        }
        /**
         * Monotonically increasing time, in seconds.
         * @return the protocol field value
         */
        @Nullable public Double timestamp() {
            return numberAsDouble(value("timestamp"));
        }
        public static final class Builder {
            private final Map<String, Object> values = new LinkedHashMap<>();
            /**
             * Sets the coverage field.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder coverage(@Nullable java.util.List<CSS.RuleUsage> value) {
                if (value == null) values.remove("coverage");
                else values.put("coverage", jsonValue(value));
                return this;
            }
            /**
             * Monotonically increasing time, in seconds.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder timestamp(@Nullable Double value) {
                if (value == null) values.remove("timestamp");
                else values.put("timestamp", jsonValue(value));
                return this;
            }
            public TakeCoverageDeltaResult build() {
                if (!values.containsKey("coverage")) throw new IllegalStateException("Missing required CDP field: coverage");
                if (!values.containsKey("timestamp")) throw new IllegalStateException("Missing required CDP field: timestamp");
                return new TakeCoverageDeltaResult(values);
            }
        }
    }
    /**
     * Enables/disables rendering of local CSS fonts (enabled by default).
     * <p><b>Experimental:</b> this part of CDP may change without notice.
     */
    public static final class SetLocalFontsEnabledParams extends CdpObject {
        private SetLocalFontsEnabledParams(Map<String, Object> values) { super(values); }
        @Nullable public static SetLocalFontsEnabledParams fromMap(@Nullable Map<String, Object> values) {
            return values == null ? null : new SetLocalFontsEnabledParams(values);
        }
        public static Builder builder() { return new Builder(); }
        /**
         * Whether rendering of local fonts is enabled.
         * @return the protocol field value
         */
        @Nullable public Boolean enabled() {
            return (Boolean) value("enabled");
        }
        public static final class Builder {
            private final Map<String, Object> values = new LinkedHashMap<>();
            /**
             * Whether rendering of local fonts is enabled.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder enabled(@Nullable Boolean value) {
                if (value == null) values.remove("enabled");
                else values.put("enabled", jsonValue(value));
                return this;
            }
            public SetLocalFontsEnabledParams build() {
                if (!values.containsKey("enabled")) throw new IllegalStateException("Missing required CDP field: enabled");
                return new SetLocalFontsEnabledParams(values);
            }
        }
    }
    /**
     * Enables/disables rendering of local CSS fonts (enabled by default).
     * <p><b>Experimental:</b> this part of CDP may change without notice.
     */
    public static final class SetLocalFontsEnabledResult extends CdpObject {
        private SetLocalFontsEnabledResult(Map<String, Object> values) { super(values); }
        @Nullable public static SetLocalFontsEnabledResult fromMap(@Nullable Map<String, Object> values) {
            return values == null ? null : new SetLocalFontsEnabledResult(values);
        }
        public static Builder builder() { return new Builder(); }
        public static final class Builder {
            private final Map<String, Object> values = new LinkedHashMap<>();
            public SetLocalFontsEnabledResult build() {
                return new SetLocalFontsEnabledResult(values);
            }
        }
    }
    /**
     * Fires whenever a web font is updated. A non-empty font parameter indicates a successfully loaded web font.
     */
    public static final class FontsUpdatedEvent extends CdpObject {
        private FontsUpdatedEvent(Map<String, Object> values) { super(values); }
        @Nullable public static FontsUpdatedEvent fromMap(@Nullable Map<String, Object> values) {
            return values == null ? null : new FontsUpdatedEvent(values);
        }
        public static Builder builder() { return new Builder(); }
        /**
         * The web font that has loaded.
         * @return the protocol field value
         */
        @Nullable public CSS.FontFace font() {
            return CSS.FontFace.fromMap(objectMap(value("font")));
        }
        public static final class Builder {
            private final Map<String, Object> values = new LinkedHashMap<>();
            /**
             * The web font that has loaded.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder font(@Nullable CSS.FontFace value) {
                if (value == null) values.remove("font");
                else values.put("font", jsonValue(value));
                return this;
            }
            public FontsUpdatedEvent build() {
                return new FontsUpdatedEvent(values);
            }
        }
    }
    /**
     * Fires whenever a MediaQuery result changes (for example, after a browser window has been resized.) The current implementation considers only viewport-dependent media features.
     */
    public static final class MediaQueryResultChangedEvent extends CdpObject {
        private MediaQueryResultChangedEvent(Map<String, Object> values) { super(values); }
        @Nullable public static MediaQueryResultChangedEvent fromMap(@Nullable Map<String, Object> values) {
            return values == null ? null : new MediaQueryResultChangedEvent(values);
        }
        public static Builder builder() { return new Builder(); }
        public static final class Builder {
            private final Map<String, Object> values = new LinkedHashMap<>();
            public MediaQueryResultChangedEvent build() {
                return new MediaQueryResultChangedEvent(values);
            }
        }
    }
    /**
     * Fired whenever an active document stylesheet is added.
     */
    public static final class StyleSheetAddedEvent extends CdpObject {
        private StyleSheetAddedEvent(Map<String, Object> values) { super(values); }
        @Nullable public static StyleSheetAddedEvent fromMap(@Nullable Map<String, Object> values) {
            return values == null ? null : new StyleSheetAddedEvent(values);
        }
        public static Builder builder() { return new Builder(); }
        /**
         * Added stylesheet metainfo.
         * @return the protocol field value
         */
        @Nullable public CSS.CSSStyleSheetHeader header() {
            return CSS.CSSStyleSheetHeader.fromMap(objectMap(value("header")));
        }
        public static final class Builder {
            private final Map<String, Object> values = new LinkedHashMap<>();
            /**
             * Added stylesheet metainfo.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder header(@Nullable CSS.CSSStyleSheetHeader value) {
                if (value == null) values.remove("header");
                else values.put("header", jsonValue(value));
                return this;
            }
            public StyleSheetAddedEvent build() {
                if (!values.containsKey("header")) throw new IllegalStateException("Missing required CDP field: header");
                return new StyleSheetAddedEvent(values);
            }
        }
    }
    /**
     * Fired whenever a stylesheet is changed as a result of the client operation.
     */
    public static final class StyleSheetChangedEvent extends CdpObject {
        private StyleSheetChangedEvent(Map<String, Object> values) { super(values); }
        @Nullable public static StyleSheetChangedEvent fromMap(@Nullable Map<String, Object> values) {
            return values == null ? null : new StyleSheetChangedEvent(values);
        }
        public static Builder builder() { return new Builder(); }
        /**
         * Returns the styleSheetId field.
         * @return the protocol field value
         */
        @Nullable public String styleSheetId() {
            return (String) value("styleSheetId");
        }
        public static final class Builder {
            private final Map<String, Object> values = new LinkedHashMap<>();
            /**
             * Sets the styleSheetId field.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder styleSheetId(@Nullable String value) {
                if (value == null) values.remove("styleSheetId");
                else values.put("styleSheetId", jsonValue(value));
                return this;
            }
            public StyleSheetChangedEvent build() {
                if (!values.containsKey("styleSheetId")) throw new IllegalStateException("Missing required CDP field: styleSheetId");
                return new StyleSheetChangedEvent(values);
            }
        }
    }
    /**
     * Fired whenever an active document stylesheet is removed.
     */
    public static final class StyleSheetRemovedEvent extends CdpObject {
        private StyleSheetRemovedEvent(Map<String, Object> values) { super(values); }
        @Nullable public static StyleSheetRemovedEvent fromMap(@Nullable Map<String, Object> values) {
            return values == null ? null : new StyleSheetRemovedEvent(values);
        }
        public static Builder builder() { return new Builder(); }
        /**
         * Identifier of the removed stylesheet.
         * @return the protocol field value
         */
        @Nullable public String styleSheetId() {
            return (String) value("styleSheetId");
        }
        public static final class Builder {
            private final Map<String, Object> values = new LinkedHashMap<>();
            /**
             * Identifier of the removed stylesheet.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder styleSheetId(@Nullable String value) {
                if (value == null) values.remove("styleSheetId");
                else values.put("styleSheetId", jsonValue(value));
                return this;
            }
            public StyleSheetRemovedEvent build() {
                if (!values.containsKey("styleSheetId")) throw new IllegalStateException("Missing required CDP field: styleSheetId");
                return new StyleSheetRemovedEvent(values);
            }
        }
    }
    /**
     * Payload of the CSS.computedStyleUpdated event.
     * <p><b>Experimental:</b> this part of CDP may change without notice.
     */
    public static final class ComputedStyleUpdatedEvent extends CdpObject {
        private ComputedStyleUpdatedEvent(Map<String, Object> values) { super(values); }
        @Nullable public static ComputedStyleUpdatedEvent fromMap(@Nullable Map<String, Object> values) {
            return values == null ? null : new ComputedStyleUpdatedEvent(values);
        }
        public static Builder builder() { return new Builder(); }
        /**
         * The node id that has updated computed styles.
         * @return the protocol field value
         */
        @Nullable public Long nodeId() {
            return numberAsLong(value("nodeId"));
        }
        public static final class Builder {
            private final Map<String, Object> values = new LinkedHashMap<>();
            /**
             * The node id that has updated computed styles.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder nodeId(@Nullable Long value) {
                if (value == null) values.remove("nodeId");
                else values.put("nodeId", jsonValue(value));
                return this;
            }
            public ComputedStyleUpdatedEvent build() {
                if (!values.containsKey("nodeId")) throw new IllegalStateException("Missing required CDP field: nodeId");
                return new ComputedStyleUpdatedEvent(values);
            }
        }
    }
    public static final class Client {
        private final CdpClient client;
        public Client(CdpClient client) { this.client = client; }
        /**
         * Inserts a new rule with the given {@code ruleText} in a stylesheet with given {@code styleSheetId}, at the position specified by {@code location}.
         * @param params command parameters
         * @return a stage completing with the command result
         */
        public CompletionStage<AddRuleResult> addRule(AddRuleParams params) {
            return client.call("CSS.addRule", params, AddRuleResult::fromMap);
        }
        /**
         * Returns all class names from specified stylesheet.
         * @param params command parameters
         * @return a stage completing with the command result
         */
        public CompletionStage<CollectClassNamesResult> collectClassNames(CollectClassNamesParams params) {
            return client.call("CSS.collectClassNames", params, CollectClassNamesResult::fromMap);
        }
        /**
         * Creates a new special &quot;via-inspector&quot; stylesheet in the frame with given {@code frameId}.
         * @param params command parameters
         * @return a stage completing with the command result
         */
        public CompletionStage<CreateStyleSheetResult> createStyleSheet(CreateStyleSheetParams params) {
            return client.call("CSS.createStyleSheet", params, CreateStyleSheetResult::fromMap);
        }
        /**
         * Disables the CSS agent for the given page.
         * @return a stage completing with the command result
         */
        public CompletionStage<DisableResult> disable() {
            return client.call("CSS.disable", null, DisableResult::fromMap);
        }
        /**
         * Enables the CSS agent for the given page. Clients should not assume that the CSS agent has been enabled until the result of this command is received.
         * @return a stage completing with the command result
         */
        public CompletionStage<EnableResult> enable() {
            return client.call("CSS.enable", null, EnableResult::fromMap);
        }
        /**
         * Ensures that the given node will have specified pseudo-classes whenever its style is computed by the browser.
         * @param params command parameters
         * @return a stage completing with the command result
         */
        public CompletionStage<ForcePseudoStateResult> forcePseudoState(ForcePseudoStateParams params) {
            return client.call("CSS.forcePseudoState", params, ForcePseudoStateResult::fromMap);
        }
        /**
         * Ensures that the given node is in its starting-style state.
         * @param params command parameters
         * @return a stage completing with the command result
         */
        public CompletionStage<ForceStartingStyleResult> forceStartingStyle(ForceStartingStyleParams params) {
            return client.call("CSS.forceStartingStyle", params, ForceStartingStyleResult::fromMap);
        }
        /**
         * Invokes CSS.getBackgroundColors.
         * @param params command parameters
         * @return a stage completing with the command result
         */
        public CompletionStage<GetBackgroundColorsResult> getBackgroundColors(GetBackgroundColorsParams params) {
            return client.call("CSS.getBackgroundColors", params, GetBackgroundColorsResult::fromMap);
        }
        /**
         * Returns the computed style for a DOM node identified by {@code nodeId}.
         * @param params command parameters
         * @return a stage completing with the command result
         */
        public CompletionStage<GetComputedStyleForNodeResult> getComputedStyleForNode(GetComputedStyleForNodeParams params) {
            return client.call("CSS.getComputedStyleForNode", params, GetComputedStyleForNodeResult::fromMap);
        }
        /**
         * Resolve the specified values in the context of the provided element. For example, a value of &#x27;1em&#x27; is evaluated according to the computed &#x27;font-size&#x27; of the element and a value &#x27;calc(1px + 2px)&#x27; will be resolved to &#x27;3px&#x27;. If the {@code propertyName} was specified the {@code values} are resolved as if they were property&#x27;s declaration. If a value cannot be parsed according to the provided property syntax, the value is parsed using combined syntax as if null {@code propertyName} was provided. If the value cannot be resolved even then, return the provided value without any changes. Note: this function currently does not resolve CSS random() function, it returns unmodified random() function parts.`
         * <p><b>Experimental:</b> this part of CDP may change without notice.
         * @param params command parameters
         * @return a stage completing with the command result
         */
        public CompletionStage<ResolveValuesResult> resolveValues(ResolveValuesParams params) {
            return client.call("CSS.resolveValues", params, ResolveValuesResult::fromMap);
        }
        /**
         * Invokes CSS.getLonghandProperties.
         * <p><b>Experimental:</b> this part of CDP may change without notice.
         * @param params command parameters
         * @return a stage completing with the command result
         */
        public CompletionStage<GetLonghandPropertiesResult> getLonghandProperties(GetLonghandPropertiesParams params) {
            return client.call("CSS.getLonghandProperties", params, GetLonghandPropertiesResult::fromMap);
        }
        /**
         * Returns the styles defined inline (explicitly in the &quot;style&quot; attribute and implicitly, using DOM attributes) for a DOM node identified by {@code nodeId}.
         * @param params command parameters
         * @return a stage completing with the command result
         */
        public CompletionStage<GetInlineStylesForNodeResult> getInlineStylesForNode(GetInlineStylesForNodeParams params) {
            return client.call("CSS.getInlineStylesForNode", params, GetInlineStylesForNodeResult::fromMap);
        }
        /**
         * Returns the styles coming from animations &amp; transitions including the animation &amp; transition styles coming from inheritance chain.
         * <p><b>Experimental:</b> this part of CDP may change without notice.
         * @param params command parameters
         * @return a stage completing with the command result
         */
        public CompletionStage<GetAnimatedStylesForNodeResult> getAnimatedStylesForNode(GetAnimatedStylesForNodeParams params) {
            return client.call("CSS.getAnimatedStylesForNode", params, GetAnimatedStylesForNodeResult::fromMap);
        }
        /**
         * Returns requested styles for a DOM node identified by {@code nodeId}.
         * @param params command parameters
         * @return a stage completing with the command result
         */
        public CompletionStage<GetMatchedStylesForNodeResult> getMatchedStylesForNode(GetMatchedStylesForNodeParams params) {
            return client.call("CSS.getMatchedStylesForNode", params, GetMatchedStylesForNodeResult::fromMap);
        }
        /**
         * Returns the values of the default UA-defined environment variables used in env()
         * <p><b>Experimental:</b> this part of CDP may change without notice.
         * @return a stage completing with the command result
         */
        public CompletionStage<GetEnvironmentVariablesResult> getEnvironmentVariables() {
            return client.call("CSS.getEnvironmentVariables", null, GetEnvironmentVariablesResult::fromMap);
        }
        /**
         * Returns all media queries parsed by the rendering engine.
         * @return a stage completing with the command result
         */
        public CompletionStage<GetMediaQueriesResult> getMediaQueries() {
            return client.call("CSS.getMediaQueries", null, GetMediaQueriesResult::fromMap);
        }
        /**
         * Requests information about platform fonts which we used to render child TextNodes in the given node.
         * @param params command parameters
         * @return a stage completing with the command result
         */
        public CompletionStage<GetPlatformFontsForNodeResult> getPlatformFontsForNode(GetPlatformFontsForNodeParams params) {
            return client.call("CSS.getPlatformFontsForNode", params, GetPlatformFontsForNodeResult::fromMap);
        }
        /**
         * Returns the current textual content for a stylesheet.
         * @param params command parameters
         * @return a stage completing with the command result
         */
        public CompletionStage<GetStyleSheetTextResult> getStyleSheetText(GetStyleSheetTextParams params) {
            return client.call("CSS.getStyleSheetText", params, GetStyleSheetTextResult::fromMap);
        }
        /**
         * Returns all layers parsed by the rendering engine for the tree scope of a node. Given a DOM element identified by nodeId, getLayersForNode returns the root layer for the nearest ancestor document or shadow root. The layer root contains the full layer tree for the tree scope and their ordering.
         * <p><b>Experimental:</b> this part of CDP may change without notice.
         * @param params command parameters
         * @return a stage completing with the command result
         */
        public CompletionStage<GetLayersForNodeResult> getLayersForNode(GetLayersForNodeParams params) {
            return client.call("CSS.getLayersForNode", params, GetLayersForNodeResult::fromMap);
        }
        /**
         * Given a CSS selector text and a style sheet ID, getLocationForSelector returns an array of locations of the CSS selector in the style sheet.
         * <p><b>Experimental:</b> this part of CDP may change without notice.
         * @param params command parameters
         * @return a stage completing with the command result
         */
        public CompletionStage<GetLocationForSelectorResult> getLocationForSelector(GetLocationForSelectorParams params) {
            return client.call("CSS.getLocationForSelector", params, GetLocationForSelectorResult::fromMap);
        }
        /**
         * Starts tracking the given node for the computed style updates and whenever the computed style is updated for node, it queues a {@code computedStyleUpdated} event with throttling. There can only be 1 node tracked for computed style updates so passing a new node id removes tracking from the previous node. Pass {@code undefined} to disable tracking.
         * <p><b>Experimental:</b> this part of CDP may change without notice.
         * @param params command parameters
         * @return a stage completing with the command result
         */
        public CompletionStage<TrackComputedStyleUpdatesForNodeResult> trackComputedStyleUpdatesForNode(TrackComputedStyleUpdatesForNodeParams params) {
            return client.call("CSS.trackComputedStyleUpdatesForNode", params, TrackComputedStyleUpdatesForNodeResult::fromMap);
        }
        /**
         * Starts tracking the given node for the computed style updates and whenever the computed style is updated for node, it queues a {@code computedStyleUpdated} event with throttling. There can only be 1 node tracked for computed style updates so passing a new node id removes tracking from the previous node. Pass {@code undefined} to disable tracking.
         * <p><b>Experimental:</b> this part of CDP may change without notice.
         * @return a stage completing with the command result
         */
        public CompletionStage<TrackComputedStyleUpdatesForNodeResult> trackComputedStyleUpdatesForNode() {
            return trackComputedStyleUpdatesForNode(TrackComputedStyleUpdatesForNodeParams.builder().build());
        }
        /**
         * Starts tracking the given computed styles for updates. The specified array of properties replaces the one previously specified. Pass empty array to disable tracking. Use takeComputedStyleUpdates to retrieve the list of nodes that had properties modified. The changes to computed style properties are only tracked for nodes pushed to the front-end by the DOM agent. If no changes to the tracked properties occur after the node has been pushed to the front-end, no updates will be issued for the node.
         * <p><b>Experimental:</b> this part of CDP may change without notice.
         * @param params command parameters
         * @return a stage completing with the command result
         */
        public CompletionStage<TrackComputedStyleUpdatesResult> trackComputedStyleUpdates(TrackComputedStyleUpdatesParams params) {
            return client.call("CSS.trackComputedStyleUpdates", params, TrackComputedStyleUpdatesResult::fromMap);
        }
        /**
         * Polls the next batch of computed style updates.
         * <p><b>Experimental:</b> this part of CDP may change without notice.
         * @return a stage completing with the command result
         */
        public CompletionStage<TakeComputedStyleUpdatesResult> takeComputedStyleUpdates() {
            return client.call("CSS.takeComputedStyleUpdates", null, TakeComputedStyleUpdatesResult::fromMap);
        }
        /**
         * Find a rule with the given active property for the given node and set the new value for this property
         * @param params command parameters
         * @return a stage completing with the command result
         */
        public CompletionStage<SetEffectivePropertyValueForNodeResult> setEffectivePropertyValueForNode(SetEffectivePropertyValueForNodeParams params) {
            return client.call("CSS.setEffectivePropertyValueForNode", params, SetEffectivePropertyValueForNodeResult::fromMap);
        }
        /**
         * Modifies the property rule property name.
         * @param params command parameters
         * @return a stage completing with the command result
         */
        public CompletionStage<SetPropertyRulePropertyNameResult> setPropertyRulePropertyName(SetPropertyRulePropertyNameParams params) {
            return client.call("CSS.setPropertyRulePropertyName", params, SetPropertyRulePropertyNameResult::fromMap);
        }
        /**
         * Modifies the keyframe rule key text.
         * @param params command parameters
         * @return a stage completing with the command result
         */
        public CompletionStage<SetKeyframeKeyResult> setKeyframeKey(SetKeyframeKeyParams params) {
            return client.call("CSS.setKeyframeKey", params, SetKeyframeKeyResult::fromMap);
        }
        /**
         * Modifies the rule selector.
         * @param params command parameters
         * @return a stage completing with the command result
         */
        public CompletionStage<SetMediaTextResult> setMediaText(SetMediaTextParams params) {
            return client.call("CSS.setMediaText", params, SetMediaTextResult::fromMap);
        }
        /**
         * Modifies the expression of a container query. Deprecated. Use setContainerQueryConditionText instead.
         * <p><b>Experimental:</b> this part of CDP may change without notice.
         * @param params command parameters
         * @return a stage completing with the command result
         * @deprecated Deprecated by the Chromium DevTools Protocol.
         */
        @Deprecated
        public CompletionStage<SetContainerQueryTextResult> setContainerQueryText(SetContainerQueryTextParams params) {
            return client.call("CSS.setContainerQueryText", params, SetContainerQueryTextResult::fromMap);
        }
        /**
         * Invokes CSS.setContainerQueryConditionText.
         * <p><b>Experimental:</b> this part of CDP may change without notice.
         * @param params command parameters
         * @return a stage completing with the command result
         */
        public CompletionStage<SetContainerQueryConditionTextResult> setContainerQueryConditionText(SetContainerQueryConditionTextParams params) {
            return client.call("CSS.setContainerQueryConditionText", params, SetContainerQueryConditionTextResult::fromMap);
        }
        /**
         * Modifies the expression of a supports at-rule.
         * <p><b>Experimental:</b> this part of CDP may change without notice.
         * @param params command parameters
         * @return a stage completing with the command result
         */
        public CompletionStage<SetSupportsTextResult> setSupportsText(SetSupportsTextParams params) {
            return client.call("CSS.setSupportsText", params, SetSupportsTextResult::fromMap);
        }
        /**
         * Modifies the expression of a navigation at-rule.
         * <p><b>Experimental:</b> this part of CDP may change without notice.
         * @param params command parameters
         * @return a stage completing with the command result
         */
        public CompletionStage<SetNavigationTextResult> setNavigationText(SetNavigationTextParams params) {
            return client.call("CSS.setNavigationText", params, SetNavigationTextResult::fromMap);
        }
        /**
         * Modifies the expression of a scope at-rule.
         * <p><b>Experimental:</b> this part of CDP may change without notice.
         * @param params command parameters
         * @return a stage completing with the command result
         */
        public CompletionStage<SetScopeTextResult> setScopeText(SetScopeTextParams params) {
            return client.call("CSS.setScopeText", params, SetScopeTextResult::fromMap);
        }
        /**
         * Modifies the rule selector.
         * @param params command parameters
         * @return a stage completing with the command result
         */
        public CompletionStage<SetRuleSelectorResult> setRuleSelector(SetRuleSelectorParams params) {
            return client.call("CSS.setRuleSelector", params, SetRuleSelectorResult::fromMap);
        }
        /**
         * Sets the new stylesheet text.
         * @param params command parameters
         * @return a stage completing with the command result
         */
        public CompletionStage<SetStyleSheetTextResult> setStyleSheetText(SetStyleSheetTextParams params) {
            return client.call("CSS.setStyleSheetText", params, SetStyleSheetTextResult::fromMap);
        }
        /**
         * Applies specified style edits one after another in the given order.
         * @param params command parameters
         * @return a stage completing with the command result
         */
        public CompletionStage<SetStyleTextsResult> setStyleTexts(SetStyleTextsParams params) {
            return client.call("CSS.setStyleTexts", params, SetStyleTextsResult::fromMap);
        }
        /**
         * Enables the selector recording.
         * @return a stage completing with the command result
         */
        public CompletionStage<StartRuleUsageTrackingResult> startRuleUsageTracking() {
            return client.call("CSS.startRuleUsageTracking", null, StartRuleUsageTrackingResult::fromMap);
        }
        /**
         * Stop tracking rule usage and return the list of rules that were used since last call to {@code takeCoverageDelta} (or since start of coverage instrumentation).
         * @return a stage completing with the command result
         */
        public CompletionStage<StopRuleUsageTrackingResult> stopRuleUsageTracking() {
            return client.call("CSS.stopRuleUsageTracking", null, StopRuleUsageTrackingResult::fromMap);
        }
        /**
         * Obtain list of rules that became used since last call to this method (or since start of coverage instrumentation).
         * @return a stage completing with the command result
         */
        public CompletionStage<TakeCoverageDeltaResult> takeCoverageDelta() {
            return client.call("CSS.takeCoverageDelta", null, TakeCoverageDeltaResult::fromMap);
        }
        /**
         * Enables/disables rendering of local CSS fonts (enabled by default).
         * <p><b>Experimental:</b> this part of CDP may change without notice.
         * @param params command parameters
         * @return a stage completing with the command result
         */
        public CompletionStage<SetLocalFontsEnabledResult> setLocalFontsEnabled(SetLocalFontsEnabledParams params) {
            return client.call("CSS.setLocalFontsEnabled", params, SetLocalFontsEnabledResult::fromMap);
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
