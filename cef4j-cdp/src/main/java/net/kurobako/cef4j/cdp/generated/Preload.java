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
 * Chrome DevTools Protocol Preload domain.
 * <p><b>Experimental:</b> this part of CDP may change without notice.
 * @see <a href="https://chromium.googlesource.com/chromium/src/+/refs/tags/150.0.7871.213/third_party/blink/public/devtools_protocol/domains/Preload.pdl">Pinned protocol source</a>
 */
@SuppressWarnings({"EscapedEntity", "JavaLangClash", "MissingSummary", "UnusedMethod"})
public final class Preload {
    private Preload() {}
    @Nullable private static Long numberAsLong(@Nullable Object value) { return value == null ? null : ((Number) value).longValue(); }
    @Nullable private static Double numberAsDouble(@Nullable Object value) { return value == null ? null : ((Number) value).doubleValue(); }
    /**
     * Corresponds to SpeculationRuleSet
     */
    public static final class RuleSet extends CdpObject {
        private RuleSet(Map<String, Object> values) { super(values); }
        @Nullable public static RuleSet fromMap(@Nullable Map<String, Object> values) {
            return values == null ? null : new RuleSet(values);
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
         * Identifies a document which the rule set is associated with.
         * @return the protocol field value
         */
        @Nullable public String loaderId() {
            return (String) value("loaderId");
        }
        /**
         * Source text of JSON representing the rule set. If it comes from {@code &lt;script&gt;} tag, it is the textContent of the node. Note that it is a JSON for valid case.
         * <p>See also: - https://wicg.github.io/nav-speculation/speculation-rules.html - https://github.com/WICG/nav-speculation/blob/main/triggers.md
         * @return the protocol field value
         */
        @Nullable public String sourceText() {
            return (String) value("sourceText");
        }
        /**
         * A speculation rule set is either added through an inline {@code &lt;script&gt;} tag or through an external resource via the &#x27;Speculation-Rules&#x27; HTTP header. For the first case, we include the BackendNodeId of the relevant {@code &lt;script&gt;} tag. For the second case, we include the external URL where the rule set was loaded from, and also RequestId if Network domain is enabled.
         * <p>See also: - https://wicg.github.io/nav-speculation/speculation-rules.html#speculation-rules-script - https://wicg.github.io/nav-speculation/speculation-rules.html#speculation-rules-header
         * @return the protocol field value
         */
        @Nullable public Long backendNodeId() {
            return numberAsLong(value("backendNodeId"));
        }
        /**
         * Returns the url field.
         * @return the protocol field value
         */
        @Nullable public String url() {
            return (String) value("url");
        }
        /**
         * Returns the requestId field.
         * @return the protocol field value
         */
        @Nullable public String requestId() {
            return (String) value("requestId");
        }
        /**
         * Error information {@code errorMessage} is null iff {@code errorType} is null.
         * @return the protocol field value
         */
        @Nullable public String errorType() {
            return (String) value("errorType");
        }
        /**
         * TODO(https://crbug.com/1425354): Replace this property with structured error.
         * @return the protocol field value
         * @deprecated Deprecated by the Chromium DevTools Protocol.
         */
        @Deprecated
        @Nullable public String errorMessage() {
            return (String) value("errorMessage");
        }
        /**
         * For more details, see: https://github.com/WICG/nav-speculation/blob/main/speculation-rules-tags.md
         * @return the protocol field value
         */
        @Nullable public String tag() {
            return (String) value("tag");
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
             * Identifies a document which the rule set is associated with.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder loaderId(@Nullable String value) {
                if (value == null) values.remove("loaderId");
                else values.put("loaderId", jsonValue(value));
                return this;
            }
            /**
             * Source text of JSON representing the rule set. If it comes from {@code &lt;script&gt;} tag, it is the textContent of the node. Note that it is a JSON for valid case.
             * <p>See also: - https://wicg.github.io/nav-speculation/speculation-rules.html - https://github.com/WICG/nav-speculation/blob/main/triggers.md
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder sourceText(@Nullable String value) {
                if (value == null) values.remove("sourceText");
                else values.put("sourceText", jsonValue(value));
                return this;
            }
            /**
             * A speculation rule set is either added through an inline {@code &lt;script&gt;} tag or through an external resource via the &#x27;Speculation-Rules&#x27; HTTP header. For the first case, we include the BackendNodeId of the relevant {@code &lt;script&gt;} tag. For the second case, we include the external URL where the rule set was loaded from, and also RequestId if Network domain is enabled.
             * <p>See also: - https://wicg.github.io/nav-speculation/speculation-rules.html#speculation-rules-script - https://wicg.github.io/nav-speculation/speculation-rules.html#speculation-rules-header
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder backendNodeId(@Nullable Long value) {
                if (value == null) values.remove("backendNodeId");
                else values.put("backendNodeId", jsonValue(value));
                return this;
            }
            /**
             * Sets the url field.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder url(@Nullable String value) {
                if (value == null) values.remove("url");
                else values.put("url", jsonValue(value));
                return this;
            }
            /**
             * Sets the requestId field.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder requestId(@Nullable String value) {
                if (value == null) values.remove("requestId");
                else values.put("requestId", jsonValue(value));
                return this;
            }
            /**
             * Error information {@code errorMessage} is null iff {@code errorType} is null.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder errorType(@Nullable String value) {
                if (value == null) values.remove("errorType");
                else values.put("errorType", jsonValue(value));
                return this;
            }
            /**
             * TODO(https://crbug.com/1425354): Replace this property with structured error.
             * @param value field value; null removes an optional value
             * @return this builder
             * @deprecated Deprecated by the Chromium DevTools Protocol.
             */
            @Deprecated
            public Builder errorMessage(@Nullable String value) {
                if (value == null) values.remove("errorMessage");
                else values.put("errorMessage", jsonValue(value));
                return this;
            }
            /**
             * For more details, see: https://github.com/WICG/nav-speculation/blob/main/speculation-rules-tags.md
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder tag(@Nullable String value) {
                if (value == null) values.remove("tag");
                else values.put("tag", jsonValue(value));
                return this;
            }
            public RuleSet build() {
                if (!values.containsKey("id")) throw new IllegalStateException("Missing required CDP field: id");
                if (!values.containsKey("loaderId")) throw new IllegalStateException("Missing required CDP field: loaderId");
                if (!values.containsKey("sourceText")) throw new IllegalStateException("Missing required CDP field: sourceText");
                return new RuleSet(values);
            }
        }
    }
    /**
     * Wire values for RuleSetErrorType.
     */
    public static final class RuleSetErrorType {
        private RuleSetErrorType() {}
        public static final String SOURCEISNOTJSONOBJECT = "SourceIsNotJsonObject";
        public static final String INVALIDRULESSKIPPED = "InvalidRulesSkipped";
        public static final String INVALIDRULESETLEVELTAG = "InvalidRulesetLevelTag";
    }
    /**
     * The type of preloading attempted. It corresponds to mojom::SpeculationAction (although PrefetchWithSubresources is omitted as it isn&#x27;t being used by clients).
     */
    public static final class SpeculationAction {
        private SpeculationAction() {}
        public static final String PREFETCH = "Prefetch";
        public static final String PRERENDER = "Prerender";
        public static final String PRERENDERUNTILSCRIPT = "PrerenderUntilScript";
    }
    /**
     * Corresponds to mojom::SpeculationTargetHint. See https://github.com/WICG/nav-speculation/blob/main/triggers.md#window-name-targeting-hints
     */
    public static final class SpeculationTargetHint {
        private SpeculationTargetHint() {}
        public static final String BLANK = "Blank";
        public static final String SELF = "Self";
    }
    /**
     * A key that identifies a preloading attempt.
     * <p>The url used is the url specified by the trigger (i.e. the initial URL), and not the final url that is navigated to. For example, prerendering allows same-origin main frame navigations during the attempt, but the attempt is still keyed with the initial URL.
     */
    public static final class PreloadingAttemptKey extends CdpObject {
        private PreloadingAttemptKey(Map<String, Object> values) { super(values); }
        @Nullable public static PreloadingAttemptKey fromMap(@Nullable Map<String, Object> values) {
            return values == null ? null : new PreloadingAttemptKey(values);
        }
        public static Builder builder() { return new Builder(); }
        /**
         * Returns the loaderId field.
         * @return the protocol field value
         */
        @Nullable public String loaderId() {
            return (String) value("loaderId");
        }
        /**
         * Returns the action field.
         * @return the protocol field value
         */
        @Nullable public String action() {
            return (String) value("action");
        }
        /**
         * Returns the url field.
         * @return the protocol field value
         */
        @Nullable public String url() {
            return (String) value("url");
        }
        /**
         * Returns the formSubmission field.
         * @return the protocol field value
         */
        @Nullable public Boolean formSubmission() {
            return (Boolean) value("formSubmission");
        }
        /**
         * Returns the targetHint field.
         * @return the protocol field value
         */
        @Nullable public String targetHint() {
            return (String) value("targetHint");
        }
        public static final class Builder {
            private final Map<String, Object> values = new LinkedHashMap<>();
            /**
             * Sets the loaderId field.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder loaderId(@Nullable String value) {
                if (value == null) values.remove("loaderId");
                else values.put("loaderId", jsonValue(value));
                return this;
            }
            /**
             * Sets the action field.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder action(@Nullable String value) {
                if (value == null) values.remove("action");
                else values.put("action", jsonValue(value));
                return this;
            }
            /**
             * Sets the url field.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder url(@Nullable String value) {
                if (value == null) values.remove("url");
                else values.put("url", jsonValue(value));
                return this;
            }
            /**
             * Sets the formSubmission field.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder formSubmission(@Nullable Boolean value) {
                if (value == null) values.remove("formSubmission");
                else values.put("formSubmission", jsonValue(value));
                return this;
            }
            /**
             * Sets the targetHint field.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder targetHint(@Nullable String value) {
                if (value == null) values.remove("targetHint");
                else values.put("targetHint", jsonValue(value));
                return this;
            }
            public PreloadingAttemptKey build() {
                if (!values.containsKey("loaderId")) throw new IllegalStateException("Missing required CDP field: loaderId");
                if (!values.containsKey("action")) throw new IllegalStateException("Missing required CDP field: action");
                if (!values.containsKey("url")) throw new IllegalStateException("Missing required CDP field: url");
                return new PreloadingAttemptKey(values);
            }
        }
    }
    /**
     * Lists sources for a preloading attempt, specifically the ids of rule sets that had a speculation rule that triggered the attempt, and the BackendNodeIds of &lt;a href&gt; or &lt;area href&gt; elements that triggered the attempt (in the case of attempts triggered by a document rule). It is possible for multiple rule sets and links to trigger a single attempt.
     */
    public static final class PreloadingAttemptSource extends CdpObject {
        private PreloadingAttemptSource(Map<String, Object> values) { super(values); }
        @Nullable public static PreloadingAttemptSource fromMap(@Nullable Map<String, Object> values) {
            return values == null ? null : new PreloadingAttemptSource(values);
        }
        public static Builder builder() { return new Builder(); }
        /**
         * Returns the key field.
         * @return the protocol field value
         */
        @Nullable public Preload.PreloadingAttemptKey key() {
            return Preload.PreloadingAttemptKey.fromMap(objectMap(value("key")));
        }
        /**
         * Returns the ruleSetIds field.
         * @return the protocol field value
         */
        @Nullable public java.util.List<String> ruleSetIds() {
            return list(value("ruleSetIds"), element0 -> (String) element0);
        }
        /**
         * Returns the nodeIds field.
         * @return the protocol field value
         */
        @Nullable public java.util.List<Long> nodeIds() {
            return list(value("nodeIds"), element0 -> numberAsLong(element0));
        }
        public static final class Builder {
            private final Map<String, Object> values = new LinkedHashMap<>();
            /**
             * Sets the key field.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder key(@Nullable Preload.PreloadingAttemptKey value) {
                if (value == null) values.remove("key");
                else values.put("key", jsonValue(value));
                return this;
            }
            /**
             * Sets the ruleSetIds field.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder ruleSetIds(@Nullable java.util.List<String> value) {
                if (value == null) values.remove("ruleSetIds");
                else values.put("ruleSetIds", jsonValue(value));
                return this;
            }
            /**
             * Sets the nodeIds field.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder nodeIds(@Nullable java.util.List<Long> value) {
                if (value == null) values.remove("nodeIds");
                else values.put("nodeIds", jsonValue(value));
                return this;
            }
            public PreloadingAttemptSource build() {
                if (!values.containsKey("key")) throw new IllegalStateException("Missing required CDP field: key");
                if (!values.containsKey("ruleSetIds")) throw new IllegalStateException("Missing required CDP field: ruleSetIds");
                if (!values.containsKey("nodeIds")) throw new IllegalStateException("Missing required CDP field: nodeIds");
                return new PreloadingAttemptSource(values);
            }
        }
    }
    /**
     * List of FinalStatus reasons for Prerender2.
     */
    public static final class PrerenderFinalStatus {
        private PrerenderFinalStatus() {}
        public static final String ACTIVATED = "Activated";
        public static final String DESTROYED = "Destroyed";
        public static final String LOWENDDEVICE = "LowEndDevice";
        public static final String INVALIDSCHEMEREDIRECT = "InvalidSchemeRedirect";
        public static final String INVALIDSCHEMENAVIGATION = "InvalidSchemeNavigation";
        public static final String NAVIGATIONREQUESTBLOCKEDBYCSP = "NavigationRequestBlockedByCsp";
        public static final String MOJOBINDERPOLICY = "MojoBinderPolicy";
        public static final String RENDERERPROCESSCRASHED = "RendererProcessCrashed";
        public static final String RENDERERPROCESSKILLED = "RendererProcessKilled";
        public static final String DOWNLOAD = "Download";
        public static final String TRIGGERDESTROYED = "TriggerDestroyed";
        public static final String NAVIGATIONNOTCOMMITTED = "NavigationNotCommitted";
        public static final String NAVIGATIONBADHTTPSTATUS = "NavigationBadHttpStatus";
        public static final String CLIENTCERTREQUESTED = "ClientCertRequested";
        public static final String NAVIGATIONREQUESTNETWORKERROR = "NavigationRequestNetworkError";
        public static final String CANCELALLHOSTSFORTESTING = "CancelAllHostsForTesting";
        public static final String DIDFAILLOAD = "DidFailLoad";
        public static final String STOP = "Stop";
        public static final String SSLCERTIFICATEERROR = "SslCertificateError";
        public static final String LOGINAUTHREQUESTED = "LoginAuthRequested";
        public static final String UACHANGEREQUIRESRELOAD = "UaChangeRequiresReload";
        public static final String BLOCKEDBYCLIENT = "BlockedByClient";
        public static final String AUDIOOUTPUTDEVICEREQUESTED = "AudioOutputDeviceRequested";
        public static final String MIXEDCONTENT = "MixedContent";
        public static final String TRIGGERBACKGROUNDED = "TriggerBackgrounded";
        public static final String MEMORYLIMITEXCEEDED = "MemoryLimitExceeded";
        public static final String DATASAVERENABLED = "DataSaverEnabled";
        public static final String TRIGGERURLHASEFFECTIVEURL = "TriggerUrlHasEffectiveUrl";
        public static final String ACTIVATEDBEFORESTARTED = "ActivatedBeforeStarted";
        public static final String INACTIVEPAGERESTRICTION = "InactivePageRestriction";
        public static final String STARTFAILED = "StartFailed";
        public static final String TIMEOUTBACKGROUNDED = "TimeoutBackgrounded";
        public static final String CROSSSITEREDIRECTININITIALNAVIGATION = "CrossSiteRedirectInInitialNavigation";
        public static final String CROSSSITENAVIGATIONININITIALNAVIGATION = "CrossSiteNavigationInInitialNavigation";
        public static final String SAMESITECROSSORIGINREDIRECTNOTOPTINININITIALNAVIGATION = "SameSiteCrossOriginRedirectNotOptInInInitialNavigation";
        public static final String SAMESITECROSSORIGINNAVIGATIONNOTOPTINININITIALNAVIGATION = "SameSiteCrossOriginNavigationNotOptInInInitialNavigation";
        public static final String ACTIVATIONNAVIGATIONPARAMETERMISMATCH = "ActivationNavigationParameterMismatch";
        public static final String ACTIVATEDINBACKGROUND = "ActivatedInBackground";
        public static final String EMBEDDERHOSTDISALLOWED = "EmbedderHostDisallowed";
        public static final String ACTIVATIONNAVIGATIONDESTROYEDBEFORESUCCESS = "ActivationNavigationDestroyedBeforeSuccess";
        public static final String TABCLOSEDBYUSERGESTURE = "TabClosedByUserGesture";
        public static final String TABCLOSEDWITHOUTUSERGESTURE = "TabClosedWithoutUserGesture";
        public static final String PRIMARYMAINFRAMERENDERERPROCESSCRASHED = "PrimaryMainFrameRendererProcessCrashed";
        public static final String PRIMARYMAINFRAMERENDERERPROCESSKILLED = "PrimaryMainFrameRendererProcessKilled";
        public static final String ACTIVATIONFRAMEPOLICYNOTCOMPATIBLE = "ActivationFramePolicyNotCompatible";
        public static final String PRELOADINGDISABLED = "PreloadingDisabled";
        public static final String BATTERYSAVERENABLED = "BatterySaverEnabled";
        public static final String ACTIVATEDDURINGMAINFRAMENAVIGATION = "ActivatedDuringMainFrameNavigation";
        public static final String PRELOADINGUNSUPPORTEDBYWEBCONTENTS = "PreloadingUnsupportedByWebContents";
        public static final String CROSSSITEREDIRECTINMAINFRAMENAVIGATION = "CrossSiteRedirectInMainFrameNavigation";
        public static final String CROSSSITENAVIGATIONINMAINFRAMENAVIGATION = "CrossSiteNavigationInMainFrameNavigation";
        public static final String SAMESITECROSSORIGINREDIRECTNOTOPTININMAINFRAMENAVIGATION = "SameSiteCrossOriginRedirectNotOptInInMainFrameNavigation";
        public static final String SAMESITECROSSORIGINNAVIGATIONNOTOPTININMAINFRAMENAVIGATION = "SameSiteCrossOriginNavigationNotOptInInMainFrameNavigation";
        public static final String MEMORYPRESSUREONTRIGGER = "MemoryPressureOnTrigger";
        public static final String MEMORYPRESSUREAFTERTRIGGERED = "MemoryPressureAfterTriggered";
        public static final String PRERENDERINGDISABLEDBYDEVTOOLS = "PrerenderingDisabledByDevTools";
        public static final String SPECULATIONRULEREMOVED = "SpeculationRuleRemoved";
        public static final String ACTIVATEDWITHAUXILIARYBROWSINGCONTEXTS = "ActivatedWithAuxiliaryBrowsingContexts";
        public static final String MAXNUMOFRUNNINGEAGERPRERENDERSEXCEEDED = "MaxNumOfRunningEagerPrerendersExceeded";
        public static final String MAXNUMOFRUNNINGNONEAGERPRERENDERSEXCEEDED = "MaxNumOfRunningNonEagerPrerendersExceeded";
        public static final String MAXNUMOFRUNNINGEMBEDDERPRERENDERSEXCEEDED = "MaxNumOfRunningEmbedderPrerendersExceeded";
        public static final String PRERENDERINGURLHASEFFECTIVEURL = "PrerenderingUrlHasEffectiveUrl";
        public static final String REDIRECTEDPRERENDERINGURLHASEFFECTIVEURL = "RedirectedPrerenderingUrlHasEffectiveUrl";
        public static final String ACTIVATIONURLHASEFFECTIVEURL = "ActivationUrlHasEffectiveUrl";
        public static final String JAVASCRIPTINTERFACEADDED = "JavaScriptInterfaceAdded";
        public static final String JAVASCRIPTINTERFACEREMOVED = "JavaScriptInterfaceRemoved";
        public static final String ALLPRERENDERINGCANCELED = "AllPrerenderingCanceled";
        public static final String WINDOWCLOSED = "WindowClosed";
        public static final String SLOWNETWORK = "SlowNetwork";
        public static final String OTHERPRERENDEREDPAGEACTIVATED = "OtherPrerenderedPageActivated";
        public static final String V8OPTIMIZERDISABLED = "V8OptimizerDisabled";
        public static final String PRERENDERFAILEDDURINGPREFETCH = "PrerenderFailedDuringPrefetch";
        public static final String BROWSINGDATAREMOVED = "BrowsingDataRemoved";
        public static final String PRERENDERHOSTREUSED = "PrerenderHostReused";
        public static final String FORMSUBMITWHENPRERENDERING = "FormSubmitWhenPrerendering";
        public static final String CROSSDOCUMENTRESTART = "CrossDocumentRestart";
    }
    /**
     * Preloading status values, see also PreloadingTriggeringOutcome. This status is shared by prefetchStatusUpdated and prerenderStatusUpdated.
     */
    public static final class PreloadingStatus {
        private PreloadingStatus() {}
        public static final String PENDING = "Pending";
        public static final String RUNNING = "Running";
        public static final String READY = "Ready";
        public static final String SUCCESS = "Success";
        public static final String FAILURE = "Failure";
        public static final String NOTSUPPORTED = "NotSupported";
    }
    /**
     * TODO(https://crbug.com/1384419): revisit the list of PrefetchStatus and filter out the ones that aren&#x27;t necessary to the developers.
     */
    public static final class PrefetchStatus {
        private PrefetchStatus() {}
        public static final String PREFETCHALLOWED = "PrefetchAllowed";
        public static final String PREFETCHFAILEDINELIGIBLEREDIRECT = "PrefetchFailedIneligibleRedirect";
        public static final String PREFETCHFAILEDINVALIDREDIRECT = "PrefetchFailedInvalidRedirect";
        public static final String PREFETCHFAILEDMIMENOTSUPPORTED = "PrefetchFailedMIMENotSupported";
        public static final String PREFETCHFAILEDNETERROR = "PrefetchFailedNetError";
        public static final String PREFETCHFAILEDNON2XX = "PrefetchFailedNon2XX";
        public static final String PREFETCHEVICTEDAFTERBROWSINGDATAREMOVED = "PrefetchEvictedAfterBrowsingDataRemoved";
        public static final String PREFETCHEVICTEDAFTERCANDIDATEREMOVED = "PrefetchEvictedAfterCandidateRemoved";
        public static final String PREFETCHEVICTEDFORNEWERPREFETCH = "PrefetchEvictedForNewerPrefetch";
        public static final String PREFETCHHELDBACK = "PrefetchHeldback";
        public static final String PREFETCHINELIGIBLERETRYAFTER = "PrefetchIneligibleRetryAfter";
        public static final String PREFETCHISPRIVACYDECOY = "PrefetchIsPrivacyDecoy";
        public static final String PREFETCHISSTALE = "PrefetchIsStale";
        public static final String PREFETCHNOTELIGIBLEBROWSERCONTEXTOFFTHERECORD = "PrefetchNotEligibleBrowserContextOffTheRecord";
        public static final String PREFETCHNOTELIGIBLEDATASAVERENABLED = "PrefetchNotEligibleDataSaverEnabled";
        public static final String PREFETCHNOTELIGIBLEEXISTINGPROXY = "PrefetchNotEligibleExistingProxy";
        public static final String PREFETCHNOTELIGIBLEHOSTISNONUNIQUE = "PrefetchNotEligibleHostIsNonUnique";
        public static final String PREFETCHNOTELIGIBLENONDEFAULTSTORAGEPARTITION = "PrefetchNotEligibleNonDefaultStoragePartition";
        public static final String PREFETCHNOTELIGIBLESAMESITECROSSORIGINPREFETCHREQUIREDPROXY = "PrefetchNotEligibleSameSiteCrossOriginPrefetchRequiredProxy";
        public static final String PREFETCHNOTELIGIBLESCHEMEISNOTHTTPS = "PrefetchNotEligibleSchemeIsNotHttps";
        public static final String PREFETCHNOTELIGIBLEUSERHASCOOKIES = "PrefetchNotEligibleUserHasCookies";
        public static final String PREFETCHNOTELIGIBLEUSERHASSERVICEWORKER = "PrefetchNotEligibleUserHasServiceWorker";
        public static final String PREFETCHNOTELIGIBLEUSERHASSERVICEWORKERNOFETCHHANDLER = "PrefetchNotEligibleUserHasServiceWorkerNoFetchHandler";
        public static final String PREFETCHNOTELIGIBLEREDIRECTFROMSERVICEWORKER = "PrefetchNotEligibleRedirectFromServiceWorker";
        public static final String PREFETCHNOTELIGIBLEREDIRECTTOSERVICEWORKER = "PrefetchNotEligibleRedirectToServiceWorker";
        public static final String PREFETCHNOTELIGIBLEBATTERYSAVERENABLED = "PrefetchNotEligibleBatterySaverEnabled";
        public static final String PREFETCHNOTELIGIBLEPRELOADINGDISABLED = "PrefetchNotEligiblePreloadingDisabled";
        public static final String PREFETCHNOTFINISHEDINTIME = "PrefetchNotFinishedInTime";
        public static final String PREFETCHNOTSTARTED = "PrefetchNotStarted";
        public static final String PREFETCHNOTUSEDCOOKIESCHANGED = "PrefetchNotUsedCookiesChanged";
        public static final String PREFETCHPROXYNOTAVAILABLE = "PrefetchProxyNotAvailable";
        public static final String PREFETCHRESPONSEUSED = "PrefetchResponseUsed";
        public static final String PREFETCHSUCCESSFULBUTNOTUSED = "PrefetchSuccessfulButNotUsed";
        public static final String PREFETCHNOTUSEDPROBEFAILED = "PrefetchNotUsedProbeFailed";
        public static final String PREFETCHCANCELLEDONUSERNAVIGATION = "PrefetchCancelledOnUserNavigation";
    }
    /**
     * Information of headers to be displayed when the header mismatch occurred.
     */
    public static final class PrerenderMismatchedHeaders extends CdpObject {
        private PrerenderMismatchedHeaders(Map<String, Object> values) { super(values); }
        @Nullable public static PrerenderMismatchedHeaders fromMap(@Nullable Map<String, Object> values) {
            return values == null ? null : new PrerenderMismatchedHeaders(values);
        }
        public static Builder builder() { return new Builder(); }
        /**
         * Returns the headerName field.
         * @return the protocol field value
         */
        @Nullable public String headerName() {
            return (String) value("headerName");
        }
        /**
         * Returns the initialValue field.
         * @return the protocol field value
         */
        @Nullable public String initialValue() {
            return (String) value("initialValue");
        }
        /**
         * Returns the activationValue field.
         * @return the protocol field value
         */
        @Nullable public String activationValue() {
            return (String) value("activationValue");
        }
        public static final class Builder {
            private final Map<String, Object> values = new LinkedHashMap<>();
            /**
             * Sets the headerName field.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder headerName(@Nullable String value) {
                if (value == null) values.remove("headerName");
                else values.put("headerName", jsonValue(value));
                return this;
            }
            /**
             * Sets the initialValue field.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder initialValue(@Nullable String value) {
                if (value == null) values.remove("initialValue");
                else values.put("initialValue", jsonValue(value));
                return this;
            }
            /**
             * Sets the activationValue field.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder activationValue(@Nullable String value) {
                if (value == null) values.remove("activationValue");
                else values.put("activationValue", jsonValue(value));
                return this;
            }
            public PrerenderMismatchedHeaders build() {
                if (!values.containsKey("headerName")) throw new IllegalStateException("Missing required CDP field: headerName");
                return new PrerenderMismatchedHeaders(values);
            }
        }
    }
    /**
     * Parameters for Preload.enable.
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
     * Result of Preload.enable.
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
     * Parameters for Preload.disable.
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
     * Result of Preload.disable.
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
     * Upsert. Currently, it is only emitted when a rule set added.
     */
    public static final class RuleSetUpdatedEvent extends CdpObject {
        private RuleSetUpdatedEvent(Map<String, Object> values) { super(values); }
        @Nullable public static RuleSetUpdatedEvent fromMap(@Nullable Map<String, Object> values) {
            return values == null ? null : new RuleSetUpdatedEvent(values);
        }
        public static Builder builder() { return new Builder(); }
        /**
         * Returns the ruleSet field.
         * @return the protocol field value
         */
        @Nullable public Preload.RuleSet ruleSet() {
            return Preload.RuleSet.fromMap(objectMap(value("ruleSet")));
        }
        public static final class Builder {
            private final Map<String, Object> values = new LinkedHashMap<>();
            /**
             * Sets the ruleSet field.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder ruleSet(@Nullable Preload.RuleSet value) {
                if (value == null) values.remove("ruleSet");
                else values.put("ruleSet", jsonValue(value));
                return this;
            }
            public RuleSetUpdatedEvent build() {
                if (!values.containsKey("ruleSet")) throw new IllegalStateException("Missing required CDP field: ruleSet");
                return new RuleSetUpdatedEvent(values);
            }
        }
    }
    /**
     * Payload of the Preload.ruleSetRemoved event.
     */
    public static final class RuleSetRemovedEvent extends CdpObject {
        private RuleSetRemovedEvent(Map<String, Object> values) { super(values); }
        @Nullable public static RuleSetRemovedEvent fromMap(@Nullable Map<String, Object> values) {
            return values == null ? null : new RuleSetRemovedEvent(values);
        }
        public static Builder builder() { return new Builder(); }
        /**
         * Returns the id field.
         * @return the protocol field value
         */
        @Nullable public String id() {
            return (String) value("id");
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
            public RuleSetRemovedEvent build() {
                if (!values.containsKey("id")) throw new IllegalStateException("Missing required CDP field: id");
                return new RuleSetRemovedEvent(values);
            }
        }
    }
    /**
     * Fired when a preload enabled state is updated.
     */
    public static final class PreloadEnabledStateUpdatedEvent extends CdpObject {
        private PreloadEnabledStateUpdatedEvent(Map<String, Object> values) { super(values); }
        @Nullable public static PreloadEnabledStateUpdatedEvent fromMap(@Nullable Map<String, Object> values) {
            return values == null ? null : new PreloadEnabledStateUpdatedEvent(values);
        }
        public static Builder builder() { return new Builder(); }
        /**
         * Returns the disabledByPreference field.
         * @return the protocol field value
         */
        @Nullable public Boolean disabledByPreference() {
            return (Boolean) value("disabledByPreference");
        }
        /**
         * Returns the disabledByDataSaver field.
         * @return the protocol field value
         */
        @Nullable public Boolean disabledByDataSaver() {
            return (Boolean) value("disabledByDataSaver");
        }
        /**
         * Returns the disabledByBatterySaver field.
         * @return the protocol field value
         */
        @Nullable public Boolean disabledByBatterySaver() {
            return (Boolean) value("disabledByBatterySaver");
        }
        /**
         * Returns the disabledByHoldbackPrefetchSpeculationRules field.
         * @return the protocol field value
         */
        @Nullable public Boolean disabledByHoldbackPrefetchSpeculationRules() {
            return (Boolean) value("disabledByHoldbackPrefetchSpeculationRules");
        }
        /**
         * Returns the disabledByHoldbackPrerenderSpeculationRules field.
         * @return the protocol field value
         */
        @Nullable public Boolean disabledByHoldbackPrerenderSpeculationRules() {
            return (Boolean) value("disabledByHoldbackPrerenderSpeculationRules");
        }
        public static final class Builder {
            private final Map<String, Object> values = new LinkedHashMap<>();
            /**
             * Sets the disabledByPreference field.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder disabledByPreference(@Nullable Boolean value) {
                if (value == null) values.remove("disabledByPreference");
                else values.put("disabledByPreference", jsonValue(value));
                return this;
            }
            /**
             * Sets the disabledByDataSaver field.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder disabledByDataSaver(@Nullable Boolean value) {
                if (value == null) values.remove("disabledByDataSaver");
                else values.put("disabledByDataSaver", jsonValue(value));
                return this;
            }
            /**
             * Sets the disabledByBatterySaver field.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder disabledByBatterySaver(@Nullable Boolean value) {
                if (value == null) values.remove("disabledByBatterySaver");
                else values.put("disabledByBatterySaver", jsonValue(value));
                return this;
            }
            /**
             * Sets the disabledByHoldbackPrefetchSpeculationRules field.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder disabledByHoldbackPrefetchSpeculationRules(@Nullable Boolean value) {
                if (value == null) values.remove("disabledByHoldbackPrefetchSpeculationRules");
                else values.put("disabledByHoldbackPrefetchSpeculationRules", jsonValue(value));
                return this;
            }
            /**
             * Sets the disabledByHoldbackPrerenderSpeculationRules field.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder disabledByHoldbackPrerenderSpeculationRules(@Nullable Boolean value) {
                if (value == null) values.remove("disabledByHoldbackPrerenderSpeculationRules");
                else values.put("disabledByHoldbackPrerenderSpeculationRules", jsonValue(value));
                return this;
            }
            public PreloadEnabledStateUpdatedEvent build() {
                if (!values.containsKey("disabledByPreference")) throw new IllegalStateException("Missing required CDP field: disabledByPreference");
                if (!values.containsKey("disabledByDataSaver")) throw new IllegalStateException("Missing required CDP field: disabledByDataSaver");
                if (!values.containsKey("disabledByBatterySaver")) throw new IllegalStateException("Missing required CDP field: disabledByBatterySaver");
                if (!values.containsKey("disabledByHoldbackPrefetchSpeculationRules")) throw new IllegalStateException("Missing required CDP field: disabledByHoldbackPrefetchSpeculationRules");
                if (!values.containsKey("disabledByHoldbackPrerenderSpeculationRules")) throw new IllegalStateException("Missing required CDP field: disabledByHoldbackPrerenderSpeculationRules");
                return new PreloadEnabledStateUpdatedEvent(values);
            }
        }
    }
    /**
     * Fired when a prefetch attempt is updated.
     */
    public static final class PrefetchStatusUpdatedEvent extends CdpObject {
        private PrefetchStatusUpdatedEvent(Map<String, Object> values) { super(values); }
        @Nullable public static PrefetchStatusUpdatedEvent fromMap(@Nullable Map<String, Object> values) {
            return values == null ? null : new PrefetchStatusUpdatedEvent(values);
        }
        public static Builder builder() { return new Builder(); }
        /**
         * Returns the key field.
         * @return the protocol field value
         */
        @Nullable public Preload.PreloadingAttemptKey key() {
            return Preload.PreloadingAttemptKey.fromMap(objectMap(value("key")));
        }
        /**
         * Returns the pipelineId field.
         * @return the protocol field value
         */
        @Nullable public String pipelineId() {
            return (String) value("pipelineId");
        }
        /**
         * The frame id of the frame initiating prefetch.
         * @return the protocol field value
         */
        @Nullable public String initiatingFrameId() {
            return (String) value("initiatingFrameId");
        }
        /**
         * Returns the prefetchUrl field.
         * @return the protocol field value
         */
        @Nullable public String prefetchUrl() {
            return (String) value("prefetchUrl");
        }
        /**
         * Returns the status field.
         * @return the protocol field value
         */
        @Nullable public String status() {
            return (String) value("status");
        }
        /**
         * Returns the prefetchStatus field.
         * @return the protocol field value
         */
        @Nullable public String prefetchStatus() {
            return (String) value("prefetchStatus");
        }
        /**
         * Returns the requestId field.
         * @return the protocol field value
         */
        @Nullable public String requestId() {
            return (String) value("requestId");
        }
        public static final class Builder {
            private final Map<String, Object> values = new LinkedHashMap<>();
            /**
             * Sets the key field.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder key(@Nullable Preload.PreloadingAttemptKey value) {
                if (value == null) values.remove("key");
                else values.put("key", jsonValue(value));
                return this;
            }
            /**
             * Sets the pipelineId field.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder pipelineId(@Nullable String value) {
                if (value == null) values.remove("pipelineId");
                else values.put("pipelineId", jsonValue(value));
                return this;
            }
            /**
             * The frame id of the frame initiating prefetch.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder initiatingFrameId(@Nullable String value) {
                if (value == null) values.remove("initiatingFrameId");
                else values.put("initiatingFrameId", jsonValue(value));
                return this;
            }
            /**
             * Sets the prefetchUrl field.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder prefetchUrl(@Nullable String value) {
                if (value == null) values.remove("prefetchUrl");
                else values.put("prefetchUrl", jsonValue(value));
                return this;
            }
            /**
             * Sets the status field.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder status(@Nullable String value) {
                if (value == null) values.remove("status");
                else values.put("status", jsonValue(value));
                return this;
            }
            /**
             * Sets the prefetchStatus field.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder prefetchStatus(@Nullable String value) {
                if (value == null) values.remove("prefetchStatus");
                else values.put("prefetchStatus", jsonValue(value));
                return this;
            }
            /**
             * Sets the requestId field.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder requestId(@Nullable String value) {
                if (value == null) values.remove("requestId");
                else values.put("requestId", jsonValue(value));
                return this;
            }
            public PrefetchStatusUpdatedEvent build() {
                if (!values.containsKey("key")) throw new IllegalStateException("Missing required CDP field: key");
                if (!values.containsKey("pipelineId")) throw new IllegalStateException("Missing required CDP field: pipelineId");
                if (!values.containsKey("initiatingFrameId")) throw new IllegalStateException("Missing required CDP field: initiatingFrameId");
                if (!values.containsKey("prefetchUrl")) throw new IllegalStateException("Missing required CDP field: prefetchUrl");
                if (!values.containsKey("status")) throw new IllegalStateException("Missing required CDP field: status");
                if (!values.containsKey("prefetchStatus")) throw new IllegalStateException("Missing required CDP field: prefetchStatus");
                if (!values.containsKey("requestId")) throw new IllegalStateException("Missing required CDP field: requestId");
                return new PrefetchStatusUpdatedEvent(values);
            }
        }
    }
    /**
     * Fired when a prerender attempt is updated.
     */
    public static final class PrerenderStatusUpdatedEvent extends CdpObject {
        private PrerenderStatusUpdatedEvent(Map<String, Object> values) { super(values); }
        @Nullable public static PrerenderStatusUpdatedEvent fromMap(@Nullable Map<String, Object> values) {
            return values == null ? null : new PrerenderStatusUpdatedEvent(values);
        }
        public static Builder builder() { return new Builder(); }
        /**
         * Returns the key field.
         * @return the protocol field value
         */
        @Nullable public Preload.PreloadingAttemptKey key() {
            return Preload.PreloadingAttemptKey.fromMap(objectMap(value("key")));
        }
        /**
         * Returns the pipelineId field.
         * @return the protocol field value
         */
        @Nullable public String pipelineId() {
            return (String) value("pipelineId");
        }
        /**
         * Returns the status field.
         * @return the protocol field value
         */
        @Nullable public String status() {
            return (String) value("status");
        }
        /**
         * Returns the prerenderStatus field.
         * @return the protocol field value
         */
        @Nullable public String prerenderStatus() {
            return (String) value("prerenderStatus");
        }
        /**
         * This is used to give users more information about the name of Mojo interface that is incompatible with prerender and has caused the cancellation of the attempt.
         * @return the protocol field value
         */
        @Nullable public String disallowedMojoInterface() {
            return (String) value("disallowedMojoInterface");
        }
        /**
         * Returns the mismatchedHeaders field.
         * @return the protocol field value
         */
        @Nullable public java.util.List<Preload.PrerenderMismatchedHeaders> mismatchedHeaders() {
            return list(value("mismatchedHeaders"), element0 -> Preload.PrerenderMismatchedHeaders.fromMap(objectMap(element0)));
        }
        public static final class Builder {
            private final Map<String, Object> values = new LinkedHashMap<>();
            /**
             * Sets the key field.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder key(@Nullable Preload.PreloadingAttemptKey value) {
                if (value == null) values.remove("key");
                else values.put("key", jsonValue(value));
                return this;
            }
            /**
             * Sets the pipelineId field.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder pipelineId(@Nullable String value) {
                if (value == null) values.remove("pipelineId");
                else values.put("pipelineId", jsonValue(value));
                return this;
            }
            /**
             * Sets the status field.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder status(@Nullable String value) {
                if (value == null) values.remove("status");
                else values.put("status", jsonValue(value));
                return this;
            }
            /**
             * Sets the prerenderStatus field.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder prerenderStatus(@Nullable String value) {
                if (value == null) values.remove("prerenderStatus");
                else values.put("prerenderStatus", jsonValue(value));
                return this;
            }
            /**
             * This is used to give users more information about the name of Mojo interface that is incompatible with prerender and has caused the cancellation of the attempt.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder disallowedMojoInterface(@Nullable String value) {
                if (value == null) values.remove("disallowedMojoInterface");
                else values.put("disallowedMojoInterface", jsonValue(value));
                return this;
            }
            /**
             * Sets the mismatchedHeaders field.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder mismatchedHeaders(@Nullable java.util.List<Preload.PrerenderMismatchedHeaders> value) {
                if (value == null) values.remove("mismatchedHeaders");
                else values.put("mismatchedHeaders", jsonValue(value));
                return this;
            }
            public PrerenderStatusUpdatedEvent build() {
                if (!values.containsKey("key")) throw new IllegalStateException("Missing required CDP field: key");
                if (!values.containsKey("pipelineId")) throw new IllegalStateException("Missing required CDP field: pipelineId");
                if (!values.containsKey("status")) throw new IllegalStateException("Missing required CDP field: status");
                return new PrerenderStatusUpdatedEvent(values);
            }
        }
    }
    /**
     * Send a list of sources for all preloading attempts in a document.
     */
    public static final class PreloadingAttemptSourcesUpdatedEvent extends CdpObject {
        private PreloadingAttemptSourcesUpdatedEvent(Map<String, Object> values) { super(values); }
        @Nullable public static PreloadingAttemptSourcesUpdatedEvent fromMap(@Nullable Map<String, Object> values) {
            return values == null ? null : new PreloadingAttemptSourcesUpdatedEvent(values);
        }
        public static Builder builder() { return new Builder(); }
        /**
         * Returns the loaderId field.
         * @return the protocol field value
         */
        @Nullable public String loaderId() {
            return (String) value("loaderId");
        }
        /**
         * Returns the preloadingAttemptSources field.
         * @return the protocol field value
         */
        @Nullable public java.util.List<Preload.PreloadingAttemptSource> preloadingAttemptSources() {
            return list(value("preloadingAttemptSources"), element0 -> Preload.PreloadingAttemptSource.fromMap(objectMap(element0)));
        }
        public static final class Builder {
            private final Map<String, Object> values = new LinkedHashMap<>();
            /**
             * Sets the loaderId field.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder loaderId(@Nullable String value) {
                if (value == null) values.remove("loaderId");
                else values.put("loaderId", jsonValue(value));
                return this;
            }
            /**
             * Sets the preloadingAttemptSources field.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder preloadingAttemptSources(@Nullable java.util.List<Preload.PreloadingAttemptSource> value) {
                if (value == null) values.remove("preloadingAttemptSources");
                else values.put("preloadingAttemptSources", jsonValue(value));
                return this;
            }
            public PreloadingAttemptSourcesUpdatedEvent build() {
                if (!values.containsKey("loaderId")) throw new IllegalStateException("Missing required CDP field: loaderId");
                if (!values.containsKey("preloadingAttemptSources")) throw new IllegalStateException("Missing required CDP field: preloadingAttemptSources");
                return new PreloadingAttemptSourcesUpdatedEvent(values);
            }
        }
    }
    public static final class Client {
        private final CdpClient client;
        public Client(CdpClient client) { this.client = client; }
        /**
         * Invokes Preload.enable.
         * @return a stage completing with the command result
         */
        public CompletionStage<EnableResult> enable() {
            return client.call("Preload.enable", null, EnableResult::fromMap);
        }
        /**
         * Invokes Preload.disable.
         * @return a stage completing with the command result
         */
        public CompletionStage<DisableResult> disable() {
            return client.call("Preload.disable", null, DisableResult::fromMap);
        }
        /**
         * Upsert. Currently, it is only emitted when a rule set added.
         * @param handler event callback
         * @return a removable subscription
         */
        public CdpSubscription onRuleSetUpdated(Consumer<RuleSetUpdatedEvent> handler) {
            return client.on("Preload.ruleSetUpdated", RuleSetUpdatedEvent::fromMap, handler);
        }
        /**
         * Subscribes to Preload.ruleSetRemoved.
         * @param handler event callback
         * @return a removable subscription
         */
        public CdpSubscription onRuleSetRemoved(Consumer<RuleSetRemovedEvent> handler) {
            return client.on("Preload.ruleSetRemoved", RuleSetRemovedEvent::fromMap, handler);
        }
        /**
         * Fired when a preload enabled state is updated.
         * @param handler event callback
         * @return a removable subscription
         */
        public CdpSubscription onPreloadEnabledStateUpdated(Consumer<PreloadEnabledStateUpdatedEvent> handler) {
            return client.on("Preload.preloadEnabledStateUpdated", PreloadEnabledStateUpdatedEvent::fromMap, handler);
        }
        /**
         * Fired when a prefetch attempt is updated.
         * @param handler event callback
         * @return a removable subscription
         */
        public CdpSubscription onPrefetchStatusUpdated(Consumer<PrefetchStatusUpdatedEvent> handler) {
            return client.on("Preload.prefetchStatusUpdated", PrefetchStatusUpdatedEvent::fromMap, handler);
        }
        /**
         * Fired when a prerender attempt is updated.
         * @param handler event callback
         * @return a removable subscription
         */
        public CdpSubscription onPrerenderStatusUpdated(Consumer<PrerenderStatusUpdatedEvent> handler) {
            return client.on("Preload.prerenderStatusUpdated", PrerenderStatusUpdatedEvent::fromMap, handler);
        }
        /**
         * Send a list of sources for all preloading attempts in a document.
         * @param handler event callback
         * @return a removable subscription
         */
        public CdpSubscription onPreloadingAttemptSourcesUpdated(Consumer<PreloadingAttemptSourcesUpdatedEvent> handler) {
            return client.on("Preload.preloadingAttemptSourcesUpdated", PreloadingAttemptSourcesUpdatedEvent::fromMap, handler);
        }
    }
}
