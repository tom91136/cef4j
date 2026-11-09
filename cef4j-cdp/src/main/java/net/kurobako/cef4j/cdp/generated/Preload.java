// GENERATED - do not edit. Regenerate via: ./mvnw generate-sources -pl cef4j-cdp -Dcef.version=150.0.18+gdb11278+chromium-150.0.7871.213
package net.kurobako.cef4j.cdp.generated;

import java.util.Map;
import java.util.Optional;
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

/**
 * Chrome DevTools Protocol Preload domain.
 * <p><b>Experimental:</b> this part of CDP may change without notice.
 * @see <a href="https://chromium.googlesource.com/chromium/src/+/refs/tags/150.0.7871.213/third_party/blink/public/devtools_protocol/domains/Preload.pdl">Pinned protocol source</a>
 */
@Generated("./mvnw generate-sources -pl cef4j-cdp -Dcef.version=150.0.18+gdb11278+chromium-150.0.7871.213")
@SuppressWarnings({"EscapedEntity", "InvalidParam", "JavaLangClash", "MissingSummary", "Unchecked", "UnusedMethod"})
@NullableBoundary("CDP wire objects use null for absent optional fields")
public final class Preload {
    private Preload() {}
    private static final CdpField<Preload.SpeculationAction> ACTION = CdpField.of("action", value -> Preload.SpeculationAction.of((String) value));
    private static final CdpField<DOM.BackendNodeId> BACKEND_NODE_ID = CdpField.of("backendNodeId", value -> new DOM.BackendNodeId(((Number) value).longValue()));
    private static final CdpField<Preload.RuleSetErrorType> ERROR_TYPE = CdpField.of("errorType", value -> Preload.RuleSetErrorType.of((String) value));
    private static final CdpField<Preload.RuleSetId> ID = CdpField.of("id", value -> new Preload.RuleSetId((String) value));
    private static final CdpField<Page.FrameId> INITIATING_FRAME_ID = CdpField.of("initiatingFrameId", value -> new Page.FrameId((String) value));
    private static final CdpField<Preload.PreloadingAttemptKey> KEY = CdpField.of("key", value -> java.util.Objects.requireNonNull(Preload.PreloadingAttemptKey.fromMap(CdpObject.requireObjectMap(value))));
    private static final CdpField<Network.LoaderId> LOADER_ID = CdpField.of("loaderId", value -> new Network.LoaderId((String) value));
    private static final CdpField<java.util.List<Preload.PrerenderMismatchedHeaders>> MISMATCHED_HEADERS = CdpField.of("mismatchedHeaders", value -> CdpObject.requireList(value, element0 -> java.util.Objects.requireNonNull(Preload.PrerenderMismatchedHeaders.fromMap(CdpObject.requireObjectMap(element0)))));
    private static final CdpField<java.util.List<DOM.BackendNodeId>> NODE_IDS = CdpField.of("nodeIds", value -> CdpObject.requireList(value, element0 -> new DOM.BackendNodeId(((Number) element0).longValue())));
    private static final CdpField<Preload.PreloadPipelineId> PIPELINE_ID = CdpField.of("pipelineId", value -> new Preload.PreloadPipelineId((String) value));
    private static final CdpField<Preload.PrefetchStatus> PREFETCH_STATUS = CdpField.of("prefetchStatus", value -> Preload.PrefetchStatus.of((String) value));
    private static final CdpField<java.util.List<Preload.PreloadingAttemptSource>> PRELOADING_ATTEMPT_SOURCES = CdpField.of("preloadingAttemptSources", value -> CdpObject.requireList(value, element0 -> java.util.Objects.requireNonNull(Preload.PreloadingAttemptSource.fromMap(CdpObject.requireObjectMap(element0)))));
    private static final CdpField<Preload.PrerenderFinalStatus> PRERENDER_STATUS = CdpField.of("prerenderStatus", value -> Preload.PrerenderFinalStatus.of((String) value));
    private static final CdpField<Network.RequestId> REQUEST_ID = CdpField.of("requestId", value -> new Network.RequestId((String) value));
    private static final CdpField<Preload.RuleSet> RULE_SET = CdpField.of("ruleSet", value -> java.util.Objects.requireNonNull(Preload.RuleSet.fromMap(CdpObject.requireObjectMap(value))));
    private static final CdpField<java.util.List<Preload.RuleSetId>> RULE_SET_IDS = CdpField.of("ruleSetIds", value -> CdpObject.requireList(value, element0 -> new Preload.RuleSetId((String) element0)));
    private static final CdpField<Preload.PreloadingStatus> STATUS = CdpField.of("status", value -> Preload.PreloadingStatus.of((String) value));
    private static final CdpField<Preload.SpeculationTargetHint> TARGET_HINT = CdpField.of("targetHint", value -> Preload.SpeculationTargetHint.of((String) value));
    /**
     * Unique id
     */
    public static final class RuleSetId implements CdpValue<String> {
        public final String value;
        public RuleSetId(String value) { this.value = java.util.Objects.requireNonNull(value); }
        @Override public String value() { return value; }
        @Override public boolean equals(@org.jspecify.annotations.Nullable Object other) {
            if (this == other) return true;
            if (!(other instanceof RuleSetId)) return false;
            return value.equals(((RuleSetId) other).value);
        }
        @Override public int hashCode() { return value.hashCode(); }
        @Override public String toString() { return "RuleSetId(" + value + ")"; }
    }
    /**
     * Corresponds to SpeculationRuleSet
     */
    public static final class RuleSet extends CdpObject {
        public RuleSet() {}
        private RuleSet(Map<String, Object> values) { super(values); }
        public static RuleSet fromMap(Map<String, Object> values) {
            return new RuleSet(values);
        }
        /**
         * Returns the id field.
         * @return the protocol field value
         */
        public Preload.RuleSetId id() {
            return require(ID);
        }
        /**
         * Identifies a document which the rule set is associated with.
         * @return the protocol field value
         */
        public Network.LoaderId loaderId() {
            return require(LOADER_ID);
        }
        /**
         * Source text of JSON representing the rule set. If it comes from {@code &lt;script&gt;} tag, it is the textContent of the node. Note that it is a JSON for valid case.
         * <p>See also: - https://wicg.github.io/nav-speculation/speculation-rules.html - https://github.com/WICG/nav-speculation/blob/main/triggers.md
         * @return the protocol field value
         */
        public String sourceText() {
            return requireString("sourceText");
        }
        /**
         * A speculation rule set is either added through an inline {@code &lt;script&gt;} tag or through an external resource via the &#x27;Speculation-Rules&#x27; HTTP header. For the first case, we include the BackendNodeId of the relevant {@code &lt;script&gt;} tag. For the second case, we include the external URL where the rule set was loaded from, and also RequestId if Network domain is enabled.
         * <p>See also: - https://wicg.github.io/nav-speculation/speculation-rules.html#speculation-rules-script - https://wicg.github.io/nav-speculation/speculation-rules.html#speculation-rules-header
         * @return the protocol field value, empty when absent
         */
        public Optional<DOM.BackendNodeId> backendNodeId() {
            return optional(BACKEND_NODE_ID);
        }
        /**
         * Returns the url field.
         * @return the protocol field value, empty when absent
         */
        public Optional<String> url() {
            return optionalString("url");
        }
        /**
         * Returns the requestId field.
         * @return the protocol field value, empty when absent
         */
        public Optional<Network.RequestId> requestId() {
            return optional(REQUEST_ID);
        }
        /**
         * Error information {@code errorMessage} is null iff {@code errorType} is null.
         * @return the protocol field value, empty when absent
         */
        public Optional<Preload.RuleSetErrorType> errorType() {
            return optional(ERROR_TYPE);
        }
        /**
         * TODO(https://crbug.com/1425354): Replace this property with structured error.
         * @return the protocol field value, empty when absent
         * @deprecated Deprecated by the Chromium DevTools Protocol.
         */
        @Deprecated
        public Optional<String> errorMessage() {
            return optionalString("errorMessage");
        }
        /**
         * For more details, see: https://github.com/WICG/nav-speculation/blob/main/speculation-rules-tags.md
         * @return the protocol field value, empty when absent
         */
        public Optional<String> tag() {
            return optionalString("tag");
        }
        /**
         * Sets the id field.
         * @param id field value
         * @return this model
         */
        public RuleSet id(Preload.RuleSetId id) {
            set("id", id);
            return this;
        }
        /**
         * Identifies a document which the rule set is associated with.
         * @param loaderId field value
         * @return this model
         */
        public RuleSet loaderId(Network.LoaderId loaderId) {
            set("loaderId", loaderId);
            return this;
        }
        /**
         * Source text of JSON representing the rule set. If it comes from {@code &lt;script&gt;} tag, it is the textContent of the node. Note that it is a JSON for valid case.
         * <p>See also: - https://wicg.github.io/nav-speculation/speculation-rules.html - https://github.com/WICG/nav-speculation/blob/main/triggers.md
         * @param sourceText field value
         * @return this model
         */
        public RuleSet sourceText(String sourceText) {
            set("sourceText", sourceText);
            return this;
        }
        /**
         * A speculation rule set is either added through an inline {@code &lt;script&gt;} tag or through an external resource via the &#x27;Speculation-Rules&#x27; HTTP header. For the first case, we include the BackendNodeId of the relevant {@code &lt;script&gt;} tag. For the second case, we include the external URL where the rule set was loaded from, and also RequestId if Network domain is enabled.
         * <p>See also: - https://wicg.github.io/nav-speculation/speculation-rules.html#speculation-rules-script - https://wicg.github.io/nav-speculation/speculation-rules.html#speculation-rules-header
         * @param backendNodeId field value
         * @return this model
         */
        public RuleSet backendNodeId(DOM.BackendNodeId backendNodeId) {
            set("backendNodeId", backendNodeId);
            return this;
        }
        /**
         * Sets the url field.
         * @param url field value
         * @return this model
         */
        public RuleSet url(String url) {
            set("url", url);
            return this;
        }
        /**
         * Sets the requestId field.
         * @param requestId field value
         * @return this model
         */
        public RuleSet requestId(Network.RequestId requestId) {
            set("requestId", requestId);
            return this;
        }
        /**
         * Error information {@code errorMessage} is null iff {@code errorType} is null.
         * @param errorType field value
         * @return this model
         */
        public RuleSet errorType(Preload.RuleSetErrorType errorType) {
            set("errorType", errorType);
            return this;
        }
        /**
         * TODO(https://crbug.com/1425354): Replace this property with structured error.
         * @param errorMessage field value
         * @return this model
         * @deprecated Deprecated by the Chromium DevTools Protocol.
         */
        @Deprecated
        public RuleSet errorMessage(String errorMessage) {
            set("errorMessage", errorMessage);
            return this;
        }
        /**
         * For more details, see: https://github.com/WICG/nav-speculation/blob/main/speculation-rules-tags.md
         * @param tag field value
         * @return this model
         */
        public RuleSet tag(String tag) {
            set("tag", tag);
            return this;
        }
    }
    /**
     * Wire values for RuleSetErrorType.
     */
    public enum RuleSetErrorType implements CdpValue<String> {
        SOURCEISNOTJSONOBJECT("SourceIsNotJsonObject"),
        INVALIDRULESSKIPPED("InvalidRulesSkipped"),
        INVALIDRULESETLEVELTAG("InvalidRulesetLevelTag");
        public final String value;
        RuleSetErrorType(String value) { this.value = value; }
        @Override public String value() { return value; }
        public static RuleSetErrorType of(String value) {
            for (RuleSetErrorType constant : values()) {
                if (constant.value.equals(value)) return constant;
            }
            throw new IllegalArgumentException("Unknown RuleSetErrorType value: " + value);
        }
    }
    /**
     * The type of preloading attempted. It corresponds to mojom::SpeculationAction (although PrefetchWithSubresources is omitted as it isn&#x27;t being used by clients).
     */
    public enum SpeculationAction implements CdpValue<String> {
        PREFETCH("Prefetch"),
        PRERENDER("Prerender"),
        PRERENDERUNTILSCRIPT("PrerenderUntilScript");
        public final String value;
        SpeculationAction(String value) { this.value = value; }
        @Override public String value() { return value; }
        public static SpeculationAction of(String value) {
            for (SpeculationAction constant : values()) {
                if (constant.value.equals(value)) return constant;
            }
            throw new IllegalArgumentException("Unknown SpeculationAction value: " + value);
        }
    }
    /**
     * Corresponds to mojom::SpeculationTargetHint. See https://github.com/WICG/nav-speculation/blob/main/triggers.md#window-name-targeting-hints
     */
    public enum SpeculationTargetHint implements CdpValue<String> {
        BLANK("Blank"),
        SELF("Self");
        public final String value;
        SpeculationTargetHint(String value) { this.value = value; }
        @Override public String value() { return value; }
        public static SpeculationTargetHint of(String value) {
            for (SpeculationTargetHint constant : values()) {
                if (constant.value.equals(value)) return constant;
            }
            throw new IllegalArgumentException("Unknown SpeculationTargetHint value: " + value);
        }
    }
    /**
     * A key that identifies a preloading attempt.
     * <p>The url used is the url specified by the trigger (i.e. the initial URL), and not the final url that is navigated to. For example, prerendering allows same-origin main frame navigations during the attempt, but the attempt is still keyed with the initial URL.
     */
    public static final class PreloadingAttemptKey extends CdpObject {
        public PreloadingAttemptKey() {}
        private PreloadingAttemptKey(Map<String, Object> values) { super(values); }
        public static PreloadingAttemptKey fromMap(Map<String, Object> values) {
            return new PreloadingAttemptKey(values);
        }
        /**
         * Returns the loaderId field.
         * @return the protocol field value
         */
        public Network.LoaderId loaderId() {
            return require(LOADER_ID);
        }
        /**
         * Returns the action field.
         * @return the protocol field value
         */
        public Preload.SpeculationAction action() {
            return require(ACTION);
        }
        /**
         * Returns the url field.
         * @return the protocol field value
         */
        public String url() {
            return requireString("url");
        }
        /**
         * Returns the formSubmission field.
         * @return the protocol field value, empty when absent
         */
        public Optional<Boolean> formSubmission() {
            return optionalBoolean("formSubmission");
        }
        /**
         * Returns the targetHint field.
         * @return the protocol field value, empty when absent
         */
        public Optional<Preload.SpeculationTargetHint> targetHint() {
            return optional(TARGET_HINT);
        }
        /**
         * Sets the loaderId field.
         * @param loaderId field value
         * @return this model
         */
        public PreloadingAttemptKey loaderId(Network.LoaderId loaderId) {
            set("loaderId", loaderId);
            return this;
        }
        /**
         * Sets the action field.
         * @param action field value
         * @return this model
         */
        public PreloadingAttemptKey action(Preload.SpeculationAction action) {
            set("action", action);
            return this;
        }
        /**
         * Sets the url field.
         * @param url field value
         * @return this model
         */
        public PreloadingAttemptKey url(String url) {
            set("url", url);
            return this;
        }
        /**
         * Sets the formSubmission field.
         * @param formSubmission field value
         * @return this model
         */
        public PreloadingAttemptKey formSubmission(boolean formSubmission) {
            set("formSubmission", formSubmission);
            return this;
        }
        /**
         * Sets the targetHint field.
         * @param targetHint field value
         * @return this model
         */
        public PreloadingAttemptKey targetHint(Preload.SpeculationTargetHint targetHint) {
            set("targetHint", targetHint);
            return this;
        }
    }
    /**
     * Lists sources for a preloading attempt, specifically the ids of rule sets that had a speculation rule that triggered the attempt, and the BackendNodeIds of &lt;a href&gt; or &lt;area href&gt; elements that triggered the attempt (in the case of attempts triggered by a document rule). It is possible for multiple rule sets and links to trigger a single attempt.
     */
    public static final class PreloadingAttemptSource extends CdpObject {
        public PreloadingAttemptSource() {}
        private PreloadingAttemptSource(Map<String, Object> values) { super(values); }
        public static PreloadingAttemptSource fromMap(Map<String, Object> values) {
            return new PreloadingAttemptSource(values);
        }
        /**
         * Returns the key field.
         * @return the protocol field value
         */
        public Preload.PreloadingAttemptKey key() {
            return require(KEY);
        }
        /**
         * Returns the ruleSetIds field.
         * @return the protocol field value
         */
        public java.util.List<Preload.RuleSetId> ruleSetIds() {
            return require(RULE_SET_IDS);
        }
        /**
         * Returns the nodeIds field.
         * @return the protocol field value
         */
        public java.util.List<DOM.BackendNodeId> nodeIds() {
            return require(NODE_IDS);
        }
        /**
         * Sets the key field.
         * @param key field value
         * @return this model
         */
        public PreloadingAttemptSource key(Preload.PreloadingAttemptKey key) {
            set("key", key);
            return this;
        }
        /**
         * Sets the ruleSetIds field.
         * @param ruleSetIds field value
         * @return this model
         */
        public PreloadingAttemptSource ruleSetIds(java.util.List<Preload.RuleSetId> ruleSetIds) {
            set("ruleSetIds", ruleSetIds);
            return this;
        }
        /**
         * Sets the nodeIds field.
         * @param nodeIds field value
         * @return this model
         */
        public PreloadingAttemptSource nodeIds(java.util.List<DOM.BackendNodeId> nodeIds) {
            set("nodeIds", nodeIds);
            return this;
        }
    }
    /**
     * Chrome manages different types of preloads together using a concept of preloading pipeline. For example, if a site uses a SpeculationRules for prerender, Chrome first starts a prefetch and then upgrades it to prerender.
     * <p>CDP events for them are emitted separately but they share {@code PreloadPipelineId}.
     */
    public static final class PreloadPipelineId implements CdpValue<String> {
        public final String value;
        public PreloadPipelineId(String value) { this.value = java.util.Objects.requireNonNull(value); }
        @Override public String value() { return value; }
        @Override public boolean equals(@org.jspecify.annotations.Nullable Object other) {
            if (this == other) return true;
            if (!(other instanceof PreloadPipelineId)) return false;
            return value.equals(((PreloadPipelineId) other).value);
        }
        @Override public int hashCode() { return value.hashCode(); }
        @Override public String toString() { return "PreloadPipelineId(" + value + ")"; }
    }
    /**
     * List of FinalStatus reasons for Prerender2.
     */
    public enum PrerenderFinalStatus implements CdpValue<String> {
        ACTIVATED("Activated"),
        DESTROYED("Destroyed"),
        LOWENDDEVICE("LowEndDevice"),
        INVALIDSCHEMEREDIRECT("InvalidSchemeRedirect"),
        INVALIDSCHEMENAVIGATION("InvalidSchemeNavigation"),
        NAVIGATIONREQUESTBLOCKEDBYCSP("NavigationRequestBlockedByCsp"),
        MOJOBINDERPOLICY("MojoBinderPolicy"),
        RENDERERPROCESSCRASHED("RendererProcessCrashed"),
        RENDERERPROCESSKILLED("RendererProcessKilled"),
        DOWNLOAD("Download"),
        TRIGGERDESTROYED("TriggerDestroyed"),
        NAVIGATIONNOTCOMMITTED("NavigationNotCommitted"),
        NAVIGATIONBADHTTPSTATUS("NavigationBadHttpStatus"),
        CLIENTCERTREQUESTED("ClientCertRequested"),
        NAVIGATIONREQUESTNETWORKERROR("NavigationRequestNetworkError"),
        CANCELALLHOSTSFORTESTING("CancelAllHostsForTesting"),
        DIDFAILLOAD("DidFailLoad"),
        STOP("Stop"),
        SSLCERTIFICATEERROR("SslCertificateError"),
        LOGINAUTHREQUESTED("LoginAuthRequested"),
        UACHANGEREQUIRESRELOAD("UaChangeRequiresReload"),
        BLOCKEDBYCLIENT("BlockedByClient"),
        AUDIOOUTPUTDEVICEREQUESTED("AudioOutputDeviceRequested"),
        MIXEDCONTENT("MixedContent"),
        TRIGGERBACKGROUNDED("TriggerBackgrounded"),
        MEMORYLIMITEXCEEDED("MemoryLimitExceeded"),
        DATASAVERENABLED("DataSaverEnabled"),
        TRIGGERURLHASEFFECTIVEURL("TriggerUrlHasEffectiveUrl"),
        ACTIVATEDBEFORESTARTED("ActivatedBeforeStarted"),
        INACTIVEPAGERESTRICTION("InactivePageRestriction"),
        STARTFAILED("StartFailed"),
        TIMEOUTBACKGROUNDED("TimeoutBackgrounded"),
        CROSSSITEREDIRECTININITIALNAVIGATION("CrossSiteRedirectInInitialNavigation"),
        CROSSSITENAVIGATIONININITIALNAVIGATION("CrossSiteNavigationInInitialNavigation"),
        SAMESITECROSSORIGINREDIRECTNOTOPTINININITIALNAVIGATION("SameSiteCrossOriginRedirectNotOptInInInitialNavigation"),
        SAMESITECROSSORIGINNAVIGATIONNOTOPTINININITIALNAVIGATION("SameSiteCrossOriginNavigationNotOptInInInitialNavigation"),
        ACTIVATIONNAVIGATIONPARAMETERMISMATCH("ActivationNavigationParameterMismatch"),
        ACTIVATEDINBACKGROUND("ActivatedInBackground"),
        EMBEDDERHOSTDISALLOWED("EmbedderHostDisallowed"),
        ACTIVATIONNAVIGATIONDESTROYEDBEFORESUCCESS("ActivationNavigationDestroyedBeforeSuccess"),
        TABCLOSEDBYUSERGESTURE("TabClosedByUserGesture"),
        TABCLOSEDWITHOUTUSERGESTURE("TabClosedWithoutUserGesture"),
        PRIMARYMAINFRAMERENDERERPROCESSCRASHED("PrimaryMainFrameRendererProcessCrashed"),
        PRIMARYMAINFRAMERENDERERPROCESSKILLED("PrimaryMainFrameRendererProcessKilled"),
        ACTIVATIONFRAMEPOLICYNOTCOMPATIBLE("ActivationFramePolicyNotCompatible"),
        PRELOADINGDISABLED("PreloadingDisabled"),
        BATTERYSAVERENABLED("BatterySaverEnabled"),
        ACTIVATEDDURINGMAINFRAMENAVIGATION("ActivatedDuringMainFrameNavigation"),
        PRELOADINGUNSUPPORTEDBYWEBCONTENTS("PreloadingUnsupportedByWebContents"),
        CROSSSITEREDIRECTINMAINFRAMENAVIGATION("CrossSiteRedirectInMainFrameNavigation"),
        CROSSSITENAVIGATIONINMAINFRAMENAVIGATION("CrossSiteNavigationInMainFrameNavigation"),
        SAMESITECROSSORIGINREDIRECTNOTOPTININMAINFRAMENAVIGATION("SameSiteCrossOriginRedirectNotOptInInMainFrameNavigation"),
        SAMESITECROSSORIGINNAVIGATIONNOTOPTININMAINFRAMENAVIGATION("SameSiteCrossOriginNavigationNotOptInInMainFrameNavigation"),
        MEMORYPRESSUREONTRIGGER("MemoryPressureOnTrigger"),
        MEMORYPRESSUREAFTERTRIGGERED("MemoryPressureAfterTriggered"),
        PRERENDERINGDISABLEDBYDEVTOOLS("PrerenderingDisabledByDevTools"),
        SPECULATIONRULEREMOVED("SpeculationRuleRemoved"),
        ACTIVATEDWITHAUXILIARYBROWSINGCONTEXTS("ActivatedWithAuxiliaryBrowsingContexts"),
        MAXNUMOFRUNNINGEAGERPRERENDERSEXCEEDED("MaxNumOfRunningEagerPrerendersExceeded"),
        MAXNUMOFRUNNINGNONEAGERPRERENDERSEXCEEDED("MaxNumOfRunningNonEagerPrerendersExceeded"),
        MAXNUMOFRUNNINGEMBEDDERPRERENDERSEXCEEDED("MaxNumOfRunningEmbedderPrerendersExceeded"),
        PRERENDERINGURLHASEFFECTIVEURL("PrerenderingUrlHasEffectiveUrl"),
        REDIRECTEDPRERENDERINGURLHASEFFECTIVEURL("RedirectedPrerenderingUrlHasEffectiveUrl"),
        ACTIVATIONURLHASEFFECTIVEURL("ActivationUrlHasEffectiveUrl"),
        JAVASCRIPTINTERFACEADDED("JavaScriptInterfaceAdded"),
        JAVASCRIPTINTERFACEREMOVED("JavaScriptInterfaceRemoved"),
        ALLPRERENDERINGCANCELED("AllPrerenderingCanceled"),
        WINDOWCLOSED("WindowClosed"),
        SLOWNETWORK("SlowNetwork"),
        OTHERPRERENDEREDPAGEACTIVATED("OtherPrerenderedPageActivated"),
        V8OPTIMIZERDISABLED("V8OptimizerDisabled"),
        PRERENDERFAILEDDURINGPREFETCH("PrerenderFailedDuringPrefetch"),
        BROWSINGDATAREMOVED("BrowsingDataRemoved"),
        PRERENDERHOSTREUSED("PrerenderHostReused"),
        FORMSUBMITWHENPRERENDERING("FormSubmitWhenPrerendering"),
        CROSSDOCUMENTRESTART("CrossDocumentRestart");
        public final String value;
        PrerenderFinalStatus(String value) { this.value = value; }
        @Override public String value() { return value; }
        public static PrerenderFinalStatus of(String value) {
            for (PrerenderFinalStatus constant : values()) {
                if (constant.value.equals(value)) return constant;
            }
            throw new IllegalArgumentException("Unknown PrerenderFinalStatus value: " + value);
        }
    }
    /**
     * Preloading status values, see also PreloadingTriggeringOutcome. This status is shared by prefetchStatusUpdated and prerenderStatusUpdated.
     */
    public enum PreloadingStatus implements CdpValue<String> {
        PENDING("Pending"),
        RUNNING("Running"),
        READY("Ready"),
        SUCCESS("Success"),
        FAILURE("Failure"),
        NOTSUPPORTED("NotSupported");
        public final String value;
        PreloadingStatus(String value) { this.value = value; }
        @Override public String value() { return value; }
        public static PreloadingStatus of(String value) {
            for (PreloadingStatus constant : values()) {
                if (constant.value.equals(value)) return constant;
            }
            throw new IllegalArgumentException("Unknown PreloadingStatus value: " + value);
        }
    }
    /**
     * TODO(https://crbug.com/1384419): revisit the list of PrefetchStatus and filter out the ones that aren&#x27;t necessary to the developers.
     */
    public enum PrefetchStatus implements CdpValue<String> {
        PREFETCHALLOWED("PrefetchAllowed"),
        PREFETCHFAILEDINELIGIBLEREDIRECT("PrefetchFailedIneligibleRedirect"),
        PREFETCHFAILEDINVALIDREDIRECT("PrefetchFailedInvalidRedirect"),
        PREFETCHFAILEDMIMENOTSUPPORTED("PrefetchFailedMIMENotSupported"),
        PREFETCHFAILEDNETERROR("PrefetchFailedNetError"),
        PREFETCHFAILEDNON2XX("PrefetchFailedNon2XX"),
        PREFETCHEVICTEDAFTERBROWSINGDATAREMOVED("PrefetchEvictedAfterBrowsingDataRemoved"),
        PREFETCHEVICTEDAFTERCANDIDATEREMOVED("PrefetchEvictedAfterCandidateRemoved"),
        PREFETCHEVICTEDFORNEWERPREFETCH("PrefetchEvictedForNewerPrefetch"),
        PREFETCHHELDBACK("PrefetchHeldback"),
        PREFETCHINELIGIBLERETRYAFTER("PrefetchIneligibleRetryAfter"),
        PREFETCHISPRIVACYDECOY("PrefetchIsPrivacyDecoy"),
        PREFETCHISSTALE("PrefetchIsStale"),
        PREFETCHNOTELIGIBLEBROWSERCONTEXTOFFTHERECORD("PrefetchNotEligibleBrowserContextOffTheRecord"),
        PREFETCHNOTELIGIBLEDATASAVERENABLED("PrefetchNotEligibleDataSaverEnabled"),
        PREFETCHNOTELIGIBLEEXISTINGPROXY("PrefetchNotEligibleExistingProxy"),
        PREFETCHNOTELIGIBLEHOSTISNONUNIQUE("PrefetchNotEligibleHostIsNonUnique"),
        PREFETCHNOTELIGIBLENONDEFAULTSTORAGEPARTITION("PrefetchNotEligibleNonDefaultStoragePartition"),
        PREFETCHNOTELIGIBLESAMESITECROSSORIGINPREFETCHREQUIREDPROXY("PrefetchNotEligibleSameSiteCrossOriginPrefetchRequiredProxy"),
        PREFETCHNOTELIGIBLESCHEMEISNOTHTTPS("PrefetchNotEligibleSchemeIsNotHttps"),
        PREFETCHNOTELIGIBLEUSERHASCOOKIES("PrefetchNotEligibleUserHasCookies"),
        PREFETCHNOTELIGIBLEUSERHASSERVICEWORKER("PrefetchNotEligibleUserHasServiceWorker"),
        PREFETCHNOTELIGIBLEUSERHASSERVICEWORKERNOFETCHHANDLER("PrefetchNotEligibleUserHasServiceWorkerNoFetchHandler"),
        PREFETCHNOTELIGIBLEREDIRECTFROMSERVICEWORKER("PrefetchNotEligibleRedirectFromServiceWorker"),
        PREFETCHNOTELIGIBLEREDIRECTTOSERVICEWORKER("PrefetchNotEligibleRedirectToServiceWorker"),
        PREFETCHNOTELIGIBLEBATTERYSAVERENABLED("PrefetchNotEligibleBatterySaverEnabled"),
        PREFETCHNOTELIGIBLEPRELOADINGDISABLED("PrefetchNotEligiblePreloadingDisabled"),
        PREFETCHNOTFINISHEDINTIME("PrefetchNotFinishedInTime"),
        PREFETCHNOTSTARTED("PrefetchNotStarted"),
        PREFETCHNOTUSEDCOOKIESCHANGED("PrefetchNotUsedCookiesChanged"),
        PREFETCHPROXYNOTAVAILABLE("PrefetchProxyNotAvailable"),
        PREFETCHRESPONSEUSED("PrefetchResponseUsed"),
        PREFETCHSUCCESSFULBUTNOTUSED("PrefetchSuccessfulButNotUsed"),
        PREFETCHNOTUSEDPROBEFAILED("PrefetchNotUsedProbeFailed"),
        PREFETCHCANCELLEDONUSERNAVIGATION("PrefetchCancelledOnUserNavigation");
        public final String value;
        PrefetchStatus(String value) { this.value = value; }
        @Override public String value() { return value; }
        public static PrefetchStatus of(String value) {
            for (PrefetchStatus constant : values()) {
                if (constant.value.equals(value)) return constant;
            }
            throw new IllegalArgumentException("Unknown PrefetchStatus value: " + value);
        }
    }
    /**
     * Information of headers to be displayed when the header mismatch occurred.
     */
    public static final class PrerenderMismatchedHeaders extends CdpObject {
        public PrerenderMismatchedHeaders() {}
        private PrerenderMismatchedHeaders(Map<String, Object> values) { super(values); }
        public static PrerenderMismatchedHeaders fromMap(Map<String, Object> values) {
            return new PrerenderMismatchedHeaders(values);
        }
        /**
         * Returns the headerName field.
         * @return the protocol field value
         */
        public String headerName() {
            return requireString("headerName");
        }
        /**
         * Returns the initialValue field.
         * @return the protocol field value, empty when absent
         */
        public Optional<String> initialValue() {
            return optionalString("initialValue");
        }
        /**
         * Returns the activationValue field.
         * @return the protocol field value, empty when absent
         */
        public Optional<String> activationValue() {
            return optionalString("activationValue");
        }
        /**
         * Sets the headerName field.
         * @param headerName field value
         * @return this model
         */
        public PrerenderMismatchedHeaders headerName(String headerName) {
            set("headerName", headerName);
            return this;
        }
        /**
         * Sets the initialValue field.
         * @param initialValue field value
         * @return this model
         */
        public PrerenderMismatchedHeaders initialValue(String initialValue) {
            set("initialValue", initialValue);
            return this;
        }
        /**
         * Sets the activationValue field.
         * @param activationValue field value
         * @return this model
         */
        public PrerenderMismatchedHeaders activationValue(String activationValue) {
            set("activationValue", activationValue);
            return this;
        }
    }
    /**
     * Upsert. Currently, it is only emitted when a rule set added.
     */
    public static final class RuleSetUpdatedEvent extends CdpObject {
        public RuleSetUpdatedEvent() {}
        private RuleSetUpdatedEvent(Map<String, Object> values) { super(values); }
        public static RuleSetUpdatedEvent fromMap(Map<String, Object> values) {
            return new RuleSetUpdatedEvent(values);
        }
        /**
         * Returns the ruleSet field.
         * @return the protocol field value
         */
        public Preload.RuleSet ruleSet() {
            return require(RULE_SET);
        }
        /**
         * Sets the ruleSet field.
         * @param ruleSet field value
         * @return this model
         */
        public RuleSetUpdatedEvent ruleSet(Preload.RuleSet ruleSet) {
            set("ruleSet", ruleSet);
            return this;
        }
    }
    /**
     * Payload of the Preload.ruleSetRemoved event.
     */
    public static final class RuleSetRemovedEvent extends CdpObject {
        public RuleSetRemovedEvent() {}
        private RuleSetRemovedEvent(Map<String, Object> values) { super(values); }
        public static RuleSetRemovedEvent fromMap(Map<String, Object> values) {
            return new RuleSetRemovedEvent(values);
        }
        /**
         * Returns the id field.
         * @return the protocol field value
         */
        public Preload.RuleSetId id() {
            return require(ID);
        }
        /**
         * Sets the id field.
         * @param id field value
         * @return this model
         */
        public RuleSetRemovedEvent id(Preload.RuleSetId id) {
            set("id", id);
            return this;
        }
    }
    /**
     * Fired when a preload enabled state is updated.
     */
    public static final class PreloadEnabledStateUpdatedEvent extends CdpObject {
        public PreloadEnabledStateUpdatedEvent() {}
        private PreloadEnabledStateUpdatedEvent(Map<String, Object> values) { super(values); }
        public static PreloadEnabledStateUpdatedEvent fromMap(Map<String, Object> values) {
            return new PreloadEnabledStateUpdatedEvent(values);
        }
        /**
         * Returns the disabledByPreference field.
         * @return the protocol field value
         */
        public boolean disabledByPreference() {
            return requireBoolean("disabledByPreference");
        }
        /**
         * Returns the disabledByDataSaver field.
         * @return the protocol field value
         */
        public boolean disabledByDataSaver() {
            return requireBoolean("disabledByDataSaver");
        }
        /**
         * Returns the disabledByBatterySaver field.
         * @return the protocol field value
         */
        public boolean disabledByBatterySaver() {
            return requireBoolean("disabledByBatterySaver");
        }
        /**
         * Returns the disabledByHoldbackPrefetchSpeculationRules field.
         * @return the protocol field value
         */
        public boolean disabledByHoldbackPrefetchSpeculationRules() {
            return requireBoolean("disabledByHoldbackPrefetchSpeculationRules");
        }
        /**
         * Returns the disabledByHoldbackPrerenderSpeculationRules field.
         * @return the protocol field value
         */
        public boolean disabledByHoldbackPrerenderSpeculationRules() {
            return requireBoolean("disabledByHoldbackPrerenderSpeculationRules");
        }
        /**
         * Sets the disabledByPreference field.
         * @param disabledByPreference field value
         * @return this model
         */
        public PreloadEnabledStateUpdatedEvent disabledByPreference(boolean disabledByPreference) {
            set("disabledByPreference", disabledByPreference);
            return this;
        }
        /**
         * Sets the disabledByDataSaver field.
         * @param disabledByDataSaver field value
         * @return this model
         */
        public PreloadEnabledStateUpdatedEvent disabledByDataSaver(boolean disabledByDataSaver) {
            set("disabledByDataSaver", disabledByDataSaver);
            return this;
        }
        /**
         * Sets the disabledByBatterySaver field.
         * @param disabledByBatterySaver field value
         * @return this model
         */
        public PreloadEnabledStateUpdatedEvent disabledByBatterySaver(boolean disabledByBatterySaver) {
            set("disabledByBatterySaver", disabledByBatterySaver);
            return this;
        }
        /**
         * Sets the disabledByHoldbackPrefetchSpeculationRules field.
         * @param disabledByHoldbackPrefetchSpeculationRules field value
         * @return this model
         */
        public PreloadEnabledStateUpdatedEvent disabledByHoldbackPrefetchSpeculationRules(boolean disabledByHoldbackPrefetchSpeculationRules) {
            set("disabledByHoldbackPrefetchSpeculationRules", disabledByHoldbackPrefetchSpeculationRules);
            return this;
        }
        /**
         * Sets the disabledByHoldbackPrerenderSpeculationRules field.
         * @param disabledByHoldbackPrerenderSpeculationRules field value
         * @return this model
         */
        public PreloadEnabledStateUpdatedEvent disabledByHoldbackPrerenderSpeculationRules(boolean disabledByHoldbackPrerenderSpeculationRules) {
            set("disabledByHoldbackPrerenderSpeculationRules", disabledByHoldbackPrerenderSpeculationRules);
            return this;
        }
    }
    /**
     * Fired when a prefetch attempt is updated.
     */
    public static final class PrefetchStatusUpdatedEvent extends CdpObject {
        public PrefetchStatusUpdatedEvent() {}
        private PrefetchStatusUpdatedEvent(Map<String, Object> values) { super(values); }
        public static PrefetchStatusUpdatedEvent fromMap(Map<String, Object> values) {
            return new PrefetchStatusUpdatedEvent(values);
        }
        /**
         * Returns the key field.
         * @return the protocol field value
         */
        public Preload.PreloadingAttemptKey key() {
            return require(KEY);
        }
        /**
         * Returns the pipelineId field.
         * @return the protocol field value
         */
        public Preload.PreloadPipelineId pipelineId() {
            return require(PIPELINE_ID);
        }
        /**
         * The frame id of the frame initiating prefetch.
         * @return the protocol field value
         */
        public Page.FrameId initiatingFrameId() {
            return require(INITIATING_FRAME_ID);
        }
        /**
         * Returns the prefetchUrl field.
         * @return the protocol field value
         */
        public String prefetchUrl() {
            return requireString("prefetchUrl");
        }
        /**
         * Returns the status field.
         * @return the protocol field value
         */
        public Preload.PreloadingStatus status() {
            return require(STATUS);
        }
        /**
         * Returns the prefetchStatus field.
         * @return the protocol field value
         */
        public Preload.PrefetchStatus prefetchStatus() {
            return require(PREFETCH_STATUS);
        }
        /**
         * Returns the requestId field.
         * @return the protocol field value
         */
        public Network.RequestId requestId() {
            return require(REQUEST_ID);
        }
        /**
         * Sets the key field.
         * @param key field value
         * @return this model
         */
        public PrefetchStatusUpdatedEvent key(Preload.PreloadingAttemptKey key) {
            set("key", key);
            return this;
        }
        /**
         * Sets the pipelineId field.
         * @param pipelineId field value
         * @return this model
         */
        public PrefetchStatusUpdatedEvent pipelineId(Preload.PreloadPipelineId pipelineId) {
            set("pipelineId", pipelineId);
            return this;
        }
        /**
         * The frame id of the frame initiating prefetch.
         * @param initiatingFrameId field value
         * @return this model
         */
        public PrefetchStatusUpdatedEvent initiatingFrameId(Page.FrameId initiatingFrameId) {
            set("initiatingFrameId", initiatingFrameId);
            return this;
        }
        /**
         * Sets the prefetchUrl field.
         * @param prefetchUrl field value
         * @return this model
         */
        public PrefetchStatusUpdatedEvent prefetchUrl(String prefetchUrl) {
            set("prefetchUrl", prefetchUrl);
            return this;
        }
        /**
         * Sets the status field.
         * @param status field value
         * @return this model
         */
        public PrefetchStatusUpdatedEvent status(Preload.PreloadingStatus status) {
            set("status", status);
            return this;
        }
        /**
         * Sets the prefetchStatus field.
         * @param prefetchStatus field value
         * @return this model
         */
        public PrefetchStatusUpdatedEvent prefetchStatus(Preload.PrefetchStatus prefetchStatus) {
            set("prefetchStatus", prefetchStatus);
            return this;
        }
        /**
         * Sets the requestId field.
         * @param requestId field value
         * @return this model
         */
        public PrefetchStatusUpdatedEvent requestId(Network.RequestId requestId) {
            set("requestId", requestId);
            return this;
        }
    }
    /**
     * Fired when a prerender attempt is updated.
     */
    public static final class PrerenderStatusUpdatedEvent extends CdpObject {
        public PrerenderStatusUpdatedEvent() {}
        private PrerenderStatusUpdatedEvent(Map<String, Object> values) { super(values); }
        public static PrerenderStatusUpdatedEvent fromMap(Map<String, Object> values) {
            return new PrerenderStatusUpdatedEvent(values);
        }
        /**
         * Returns the key field.
         * @return the protocol field value
         */
        public Preload.PreloadingAttemptKey key() {
            return require(KEY);
        }
        /**
         * Returns the pipelineId field.
         * @return the protocol field value
         */
        public Preload.PreloadPipelineId pipelineId() {
            return require(PIPELINE_ID);
        }
        /**
         * Returns the status field.
         * @return the protocol field value
         */
        public Preload.PreloadingStatus status() {
            return require(STATUS);
        }
        /**
         * Returns the prerenderStatus field.
         * @return the protocol field value, empty when absent
         */
        public Optional<Preload.PrerenderFinalStatus> prerenderStatus() {
            return optional(PRERENDER_STATUS);
        }
        /**
         * This is used to give users more information about the name of Mojo interface that is incompatible with prerender and has caused the cancellation of the attempt.
         * @return the protocol field value, empty when absent
         */
        public Optional<String> disallowedMojoInterface() {
            return optionalString("disallowedMojoInterface");
        }
        /**
         * Returns the mismatchedHeaders field.
         * @return the protocol field value, empty when absent
         */
        public Optional<java.util.List<Preload.PrerenderMismatchedHeaders>> mismatchedHeaders() {
            return optional(MISMATCHED_HEADERS);
        }
        /**
         * Sets the key field.
         * @param key field value
         * @return this model
         */
        public PrerenderStatusUpdatedEvent key(Preload.PreloadingAttemptKey key) {
            set("key", key);
            return this;
        }
        /**
         * Sets the pipelineId field.
         * @param pipelineId field value
         * @return this model
         */
        public PrerenderStatusUpdatedEvent pipelineId(Preload.PreloadPipelineId pipelineId) {
            set("pipelineId", pipelineId);
            return this;
        }
        /**
         * Sets the status field.
         * @param status field value
         * @return this model
         */
        public PrerenderStatusUpdatedEvent status(Preload.PreloadingStatus status) {
            set("status", status);
            return this;
        }
        /**
         * Sets the prerenderStatus field.
         * @param prerenderStatus field value
         * @return this model
         */
        public PrerenderStatusUpdatedEvent prerenderStatus(Preload.PrerenderFinalStatus prerenderStatus) {
            set("prerenderStatus", prerenderStatus);
            return this;
        }
        /**
         * This is used to give users more information about the name of Mojo interface that is incompatible with prerender and has caused the cancellation of the attempt.
         * @param disallowedMojoInterface field value
         * @return this model
         */
        public PrerenderStatusUpdatedEvent disallowedMojoInterface(String disallowedMojoInterface) {
            set("disallowedMojoInterface", disallowedMojoInterface);
            return this;
        }
        /**
         * Sets the mismatchedHeaders field.
         * @param mismatchedHeaders field value
         * @return this model
         */
        public PrerenderStatusUpdatedEvent mismatchedHeaders(java.util.List<Preload.PrerenderMismatchedHeaders> mismatchedHeaders) {
            set("mismatchedHeaders", mismatchedHeaders);
            return this;
        }
    }
    /**
     * Send a list of sources for all preloading attempts in a document.
     */
    public static final class PreloadingAttemptSourcesUpdatedEvent extends CdpObject {
        public PreloadingAttemptSourcesUpdatedEvent() {}
        private PreloadingAttemptSourcesUpdatedEvent(Map<String, Object> values) { super(values); }
        public static PreloadingAttemptSourcesUpdatedEvent fromMap(Map<String, Object> values) {
            return new PreloadingAttemptSourcesUpdatedEvent(values);
        }
        /**
         * Returns the loaderId field.
         * @return the protocol field value
         */
        public Network.LoaderId loaderId() {
            return require(LOADER_ID);
        }
        /**
         * Returns the preloadingAttemptSources field.
         * @return the protocol field value
         */
        public java.util.List<Preload.PreloadingAttemptSource> preloadingAttemptSources() {
            return require(PRELOADING_ATTEMPT_SOURCES);
        }
        /**
         * Sets the loaderId field.
         * @param loaderId field value
         * @return this model
         */
        public PreloadingAttemptSourcesUpdatedEvent loaderId(Network.LoaderId loaderId) {
            set("loaderId", loaderId);
            return this;
        }
        /**
         * Sets the preloadingAttemptSources field.
         * @param preloadingAttemptSources field value
         * @return this model
         */
        public PreloadingAttemptSourcesUpdatedEvent preloadingAttemptSources(java.util.List<Preload.PreloadingAttemptSource> preloadingAttemptSources) {
            set("preloadingAttemptSources", preloadingAttemptSources);
            return this;
        }
    }
    public static final class Client extends CdpDomainClient {
        public Client(CdpClient client) { super(client); }
        /**
         * Invokes Preload.enable.
         * @return a stage completing when the command completes
         */
        public CompletionStage<Void> enable() {
            return call("Preload.enable");
        }
        /**
         * Invokes Preload.disable.
         * @return a stage completing when the command completes
         */
        public CompletionStage<Void> disable() {
            return call("Preload.disable");
        }
        /**
         * Upsert. Currently, it is only emitted when a rule set added.
         * @param handler event callback
         * @return a removable subscription
         */
        public CdpSubscription onRuleSetUpdated(Consumer<RuleSetUpdatedEvent> handler) {
            return on("Preload.ruleSetUpdated", RuleSetUpdatedEvent::fromMap, handler);
        }
        /**
         * Subscribes to Preload.ruleSetRemoved.
         * @param handler event callback
         * @return a removable subscription
         */
        public CdpSubscription onRuleSetRemoved(Consumer<RuleSetRemovedEvent> handler) {
            return on("Preload.ruleSetRemoved", RuleSetRemovedEvent::fromMap, handler);
        }
        /**
         * Fired when a preload enabled state is updated.
         * @param handler event callback
         * @return a removable subscription
         */
        public CdpSubscription onPreloadEnabledStateUpdated(Consumer<PreloadEnabledStateUpdatedEvent> handler) {
            return on("Preload.preloadEnabledStateUpdated", PreloadEnabledStateUpdatedEvent::fromMap, handler);
        }
        /**
         * Fired when a prefetch attempt is updated.
         * @param handler event callback
         * @return a removable subscription
         */
        public CdpSubscription onPrefetchStatusUpdated(Consumer<PrefetchStatusUpdatedEvent> handler) {
            return on("Preload.prefetchStatusUpdated", PrefetchStatusUpdatedEvent::fromMap, handler);
        }
        /**
         * Fired when a prerender attempt is updated.
         * @param handler event callback
         * @return a removable subscription
         */
        public CdpSubscription onPrerenderStatusUpdated(Consumer<PrerenderStatusUpdatedEvent> handler) {
            return on("Preload.prerenderStatusUpdated", PrerenderStatusUpdatedEvent::fromMap, handler);
        }
        /**
         * Send a list of sources for all preloading attempts in a document.
         * @param handler event callback
         * @return a removable subscription
         */
        public CdpSubscription onPreloadingAttemptSourcesUpdated(Consumer<PreloadingAttemptSourcesUpdatedEvent> handler) {
            return on("Preload.preloadingAttemptSourcesUpdated", PreloadingAttemptSourcesUpdatedEvent::fromMap, handler);
        }
    }
}
