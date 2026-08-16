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
 * Audits domain allows investigation of page violations and possible improvements.
 * <p><b>Experimental:</b> this part of CDP may change without notice.
 * @see <a href="https://chromium.googlesource.com/chromium/src/+/refs/tags/146.0.7680.165/third_party/blink/public/devtools_protocol/domains/Audits.pdl">Pinned protocol source</a>
 */
@SuppressWarnings({"EscapedEntity", "JavaLangClash", "MissingSummary", "UnusedMethod"})
public final class Audits {
    private Audits() {}
    @Nullable private static Long numberAsLong(@Nullable Object value) { return value == null ? null : ((Number) value).longValue(); }
    @Nullable private static Double numberAsDouble(@Nullable Object value) { return value == null ? null : ((Number) value).doubleValue(); }
    /**
     * Information about a cookie that is affected by an inspector issue.
     */
    public static final class AffectedCookie extends CdpObject {
        private AffectedCookie(Map<String, Object> values) { super(values); }
        @Nullable public static AffectedCookie fromMap(@Nullable Map<String, Object> values) {
            return values == null ? null : new AffectedCookie(values);
        }
        public static Builder builder() { return new Builder(); }
        /**
         * The following three properties uniquely identify a cookie
         * @return the protocol field value
         */
        @Nullable public String name() {
            return (String) value("name");
        }
        /**
         * Returns the path field.
         * @return the protocol field value
         */
        @Nullable public String path() {
            return (String) value("path");
        }
        /**
         * Returns the domain field.
         * @return the protocol field value
         */
        @Nullable public String domain() {
            return (String) value("domain");
        }
        public static final class Builder {
            private final Map<String, Object> values = new LinkedHashMap<>();
            /**
             * The following three properties uniquely identify a cookie
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder name(@Nullable String value) {
                if (value == null) values.remove("name");
                else values.put("name", jsonValue(value));
                return this;
            }
            /**
             * Sets the path field.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder path(@Nullable String value) {
                if (value == null) values.remove("path");
                else values.put("path", jsonValue(value));
                return this;
            }
            /**
             * Sets the domain field.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder domain(@Nullable String value) {
                if (value == null) values.remove("domain");
                else values.put("domain", jsonValue(value));
                return this;
            }
            public AffectedCookie build() {
                if (!values.containsKey("name")) throw new IllegalStateException("Missing required CDP field: name");
                if (!values.containsKey("path")) throw new IllegalStateException("Missing required CDP field: path");
                if (!values.containsKey("domain")) throw new IllegalStateException("Missing required CDP field: domain");
                return new AffectedCookie(values);
            }
        }
    }
    /**
     * Information about a request that is affected by an inspector issue.
     */
    public static final class AffectedRequest extends CdpObject {
        private AffectedRequest(Map<String, Object> values) { super(values); }
        @Nullable public static AffectedRequest fromMap(@Nullable Map<String, Object> values) {
            return values == null ? null : new AffectedRequest(values);
        }
        public static Builder builder() { return new Builder(); }
        /**
         * The unique request id.
         * @return the protocol field value
         */
        @Nullable public String requestId() {
            return (String) value("requestId");
        }
        /**
         * Returns the url field.
         * @return the protocol field value
         */
        @Nullable public String url() {
            return (String) value("url");
        }
        public static final class Builder {
            private final Map<String, Object> values = new LinkedHashMap<>();
            /**
             * The unique request id.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder requestId(@Nullable String value) {
                if (value == null) values.remove("requestId");
                else values.put("requestId", jsonValue(value));
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
            public AffectedRequest build() {
                if (!values.containsKey("url")) throw new IllegalStateException("Missing required CDP field: url");
                return new AffectedRequest(values);
            }
        }
    }
    /**
     * Information about the frame affected by an inspector issue.
     */
    public static final class AffectedFrame extends CdpObject {
        private AffectedFrame(Map<String, Object> values) { super(values); }
        @Nullable public static AffectedFrame fromMap(@Nullable Map<String, Object> values) {
            return values == null ? null : new AffectedFrame(values);
        }
        public static Builder builder() { return new Builder(); }
        /**
         * Returns the frameId field.
         * @return the protocol field value
         */
        @Nullable public String frameId() {
            return (String) value("frameId");
        }
        public static final class Builder {
            private final Map<String, Object> values = new LinkedHashMap<>();
            /**
             * Sets the frameId field.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder frameId(@Nullable String value) {
                if (value == null) values.remove("frameId");
                else values.put("frameId", jsonValue(value));
                return this;
            }
            public AffectedFrame build() {
                if (!values.containsKey("frameId")) throw new IllegalStateException("Missing required CDP field: frameId");
                return new AffectedFrame(values);
            }
        }
    }
    /**
     * Wire values for CookieExclusionReason.
     */
    public static final class CookieExclusionReason {
        private CookieExclusionReason() {}
        public static final String EXCLUDESAMESITEUNSPECIFIEDTREATEDASLAX = "ExcludeSameSiteUnspecifiedTreatedAsLax";
        public static final String EXCLUDESAMESITENONEINSECURE = "ExcludeSameSiteNoneInsecure";
        public static final String EXCLUDESAMESITELAX = "ExcludeSameSiteLax";
        public static final String EXCLUDESAMESITESTRICT = "ExcludeSameSiteStrict";
        public static final String EXCLUDEDOMAINNONASCII = "ExcludeDomainNonASCII";
        public static final String EXCLUDETHIRDPARTYCOOKIEBLOCKEDINFIRSTPARTYSET = "ExcludeThirdPartyCookieBlockedInFirstPartySet";
        public static final String EXCLUDETHIRDPARTYPHASEOUT = "ExcludeThirdPartyPhaseout";
        public static final String EXCLUDEPORTMISMATCH = "ExcludePortMismatch";
        public static final String EXCLUDESCHEMEMISMATCH = "ExcludeSchemeMismatch";
    }
    /**
     * Wire values for CookieWarningReason.
     */
    public static final class CookieWarningReason {
        private CookieWarningReason() {}
        public static final String WARNSAMESITEUNSPECIFIEDCROSSSITECONTEXT = "WarnSameSiteUnspecifiedCrossSiteContext";
        public static final String WARNSAMESITENONEINSECURE = "WarnSameSiteNoneInsecure";
        public static final String WARNSAMESITEUNSPECIFIEDLAXALLOWUNSAFE = "WarnSameSiteUnspecifiedLaxAllowUnsafe";
        public static final String WARNSAMESITESTRICTLAXDOWNGRADESTRICT = "WarnSameSiteStrictLaxDowngradeStrict";
        public static final String WARNSAMESITESTRICTCROSSDOWNGRADESTRICT = "WarnSameSiteStrictCrossDowngradeStrict";
        public static final String WARNSAMESITESTRICTCROSSDOWNGRADELAX = "WarnSameSiteStrictCrossDowngradeLax";
        public static final String WARNSAMESITELAXCROSSDOWNGRADESTRICT = "WarnSameSiteLaxCrossDowngradeStrict";
        public static final String WARNSAMESITELAXCROSSDOWNGRADELAX = "WarnSameSiteLaxCrossDowngradeLax";
        public static final String WARNATTRIBUTEVALUEEXCEEDSMAXSIZE = "WarnAttributeValueExceedsMaxSize";
        public static final String WARNDOMAINNONASCII = "WarnDomainNonASCII";
        public static final String WARNTHIRDPARTYPHASEOUT = "WarnThirdPartyPhaseout";
        public static final String WARNCROSSSITEREDIRECTDOWNGRADECHANGESINCLUSION = "WarnCrossSiteRedirectDowngradeChangesInclusion";
        public static final String WARNDEPRECATIONTRIALMETADATA = "WarnDeprecationTrialMetadata";
        public static final String WARNTHIRDPARTYCOOKIEHEURISTIC = "WarnThirdPartyCookieHeuristic";
    }
    /**
     * Wire values for CookieOperation.
     */
    public static final class CookieOperation {
        private CookieOperation() {}
        public static final String SETCOOKIE = "SetCookie";
        public static final String READCOOKIE = "ReadCookie";
    }
    /**
     * Represents the category of insight that a cookie issue falls under.
     */
    public static final class InsightType {
        private InsightType() {}
        public static final String GITHUBRESOURCE = "GitHubResource";
        public static final String GRACEPERIOD = "GracePeriod";
        public static final String HEURISTICS = "Heuristics";
    }
    /**
     * Information about the suggested solution to a cookie issue.
     */
    public static final class CookieIssueInsight extends CdpObject {
        private CookieIssueInsight(Map<String, Object> values) { super(values); }
        @Nullable public static CookieIssueInsight fromMap(@Nullable Map<String, Object> values) {
            return values == null ? null : new CookieIssueInsight(values);
        }
        public static Builder builder() { return new Builder(); }
        /**
         * Returns the type field.
         * @return the protocol field value
         */
        @Nullable public String type() {
            return (String) value("type");
        }
        /**
         * Link to table entry in third-party cookie migration readiness list.
         * @return the protocol field value
         */
        @Nullable public String tableEntryUrl() {
            return (String) value("tableEntryUrl");
        }
        public static final class Builder {
            private final Map<String, Object> values = new LinkedHashMap<>();
            /**
             * Sets the type field.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder type(@Nullable String value) {
                if (value == null) values.remove("type");
                else values.put("type", jsonValue(value));
                return this;
            }
            /**
             * Link to table entry in third-party cookie migration readiness list.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder tableEntryUrl(@Nullable String value) {
                if (value == null) values.remove("tableEntryUrl");
                else values.put("tableEntryUrl", jsonValue(value));
                return this;
            }
            public CookieIssueInsight build() {
                if (!values.containsKey("type")) throw new IllegalStateException("Missing required CDP field: type");
                return new CookieIssueInsight(values);
            }
        }
    }
    /**
     * This information is currently necessary, as the front-end has a difficult time finding a specific cookie. With this, we can convey specific error information without the cookie.
     */
    public static final class CookieIssueDetails extends CdpObject {
        private CookieIssueDetails(Map<String, Object> values) { super(values); }
        @Nullable public static CookieIssueDetails fromMap(@Nullable Map<String, Object> values) {
            return values == null ? null : new CookieIssueDetails(values);
        }
        public static Builder builder() { return new Builder(); }
        /**
         * If AffectedCookie is not set then rawCookieLine contains the raw Set-Cookie header string. This hints at a problem where the cookie line is syntactically or semantically malformed in a way that no valid cookie could be created.
         * @return the protocol field value
         */
        @Nullable public Audits.AffectedCookie cookie() {
            return Audits.AffectedCookie.fromMap(objectMap(value("cookie")));
        }
        /**
         * Returns the rawCookieLine field.
         * @return the protocol field value
         */
        @Nullable public String rawCookieLine() {
            return (String) value("rawCookieLine");
        }
        /**
         * Returns the cookieWarningReasons field.
         * @return the protocol field value
         */
        @Nullable public java.util.List<String> cookieWarningReasons() {
            return list(value("cookieWarningReasons"), element0 -> (String) element0);
        }
        /**
         * Returns the cookieExclusionReasons field.
         * @return the protocol field value
         */
        @Nullable public java.util.List<String> cookieExclusionReasons() {
            return list(value("cookieExclusionReasons"), element0 -> (String) element0);
        }
        /**
         * Optionally identifies the site-for-cookies and the cookie url, which may be used by the front-end as additional context.
         * @return the protocol field value
         */
        @Nullable public String operation() {
            return (String) value("operation");
        }
        /**
         * Returns the siteForCookies field.
         * @return the protocol field value
         */
        @Nullable public String siteForCookies() {
            return (String) value("siteForCookies");
        }
        /**
         * Returns the cookieUrl field.
         * @return the protocol field value
         */
        @Nullable public String cookieUrl() {
            return (String) value("cookieUrl");
        }
        /**
         * Returns the request field.
         * @return the protocol field value
         */
        @Nullable public Audits.AffectedRequest request() {
            return Audits.AffectedRequest.fromMap(objectMap(value("request")));
        }
        /**
         * The recommended solution to the issue.
         * @return the protocol field value
         */
        @Nullable public Audits.CookieIssueInsight insight() {
            return Audits.CookieIssueInsight.fromMap(objectMap(value("insight")));
        }
        public static final class Builder {
            private final Map<String, Object> values = new LinkedHashMap<>();
            /**
             * If AffectedCookie is not set then rawCookieLine contains the raw Set-Cookie header string. This hints at a problem where the cookie line is syntactically or semantically malformed in a way that no valid cookie could be created.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder cookie(@Nullable Audits.AffectedCookie value) {
                if (value == null) values.remove("cookie");
                else values.put("cookie", jsonValue(value));
                return this;
            }
            /**
             * Sets the rawCookieLine field.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder rawCookieLine(@Nullable String value) {
                if (value == null) values.remove("rawCookieLine");
                else values.put("rawCookieLine", jsonValue(value));
                return this;
            }
            /**
             * Sets the cookieWarningReasons field.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder cookieWarningReasons(@Nullable java.util.List<String> value) {
                if (value == null) values.remove("cookieWarningReasons");
                else values.put("cookieWarningReasons", jsonValue(value));
                return this;
            }
            /**
             * Sets the cookieExclusionReasons field.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder cookieExclusionReasons(@Nullable java.util.List<String> value) {
                if (value == null) values.remove("cookieExclusionReasons");
                else values.put("cookieExclusionReasons", jsonValue(value));
                return this;
            }
            /**
             * Optionally identifies the site-for-cookies and the cookie url, which may be used by the front-end as additional context.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder operation(@Nullable String value) {
                if (value == null) values.remove("operation");
                else values.put("operation", jsonValue(value));
                return this;
            }
            /**
             * Sets the siteForCookies field.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder siteForCookies(@Nullable String value) {
                if (value == null) values.remove("siteForCookies");
                else values.put("siteForCookies", jsonValue(value));
                return this;
            }
            /**
             * Sets the cookieUrl field.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder cookieUrl(@Nullable String value) {
                if (value == null) values.remove("cookieUrl");
                else values.put("cookieUrl", jsonValue(value));
                return this;
            }
            /**
             * Sets the request field.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder request(@Nullable Audits.AffectedRequest value) {
                if (value == null) values.remove("request");
                else values.put("request", jsonValue(value));
                return this;
            }
            /**
             * The recommended solution to the issue.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder insight(@Nullable Audits.CookieIssueInsight value) {
                if (value == null) values.remove("insight");
                else values.put("insight", jsonValue(value));
                return this;
            }
            public CookieIssueDetails build() {
                if (!values.containsKey("cookieWarningReasons")) throw new IllegalStateException("Missing required CDP field: cookieWarningReasons");
                if (!values.containsKey("cookieExclusionReasons")) throw new IllegalStateException("Missing required CDP field: cookieExclusionReasons");
                if (!values.containsKey("operation")) throw new IllegalStateException("Missing required CDP field: operation");
                return new CookieIssueDetails(values);
            }
        }
    }
    /**
     * Wire values for MixedContentResolutionStatus.
     */
    public static final class MixedContentResolutionStatus {
        private MixedContentResolutionStatus() {}
        public static final String MIXEDCONTENTBLOCKED = "MixedContentBlocked";
        public static final String MIXEDCONTENTAUTOMATICALLYUPGRADED = "MixedContentAutomaticallyUpgraded";
        public static final String MIXEDCONTENTWARNING = "MixedContentWarning";
    }
    /**
     * Wire values for MixedContentResourceType.
     */
    public static final class MixedContentResourceType {
        private MixedContentResourceType() {}
        public static final String ATTRIBUTIONSRC = "AttributionSrc";
        public static final String AUDIO = "Audio";
        public static final String BEACON = "Beacon";
        public static final String CSPREPORT = "CSPReport";
        public static final String DOWNLOAD = "Download";
        public static final String EVENTSOURCE = "EventSource";
        public static final String FAVICON = "Favicon";
        public static final String FONT = "Font";
        public static final String FORM = "Form";
        public static final String FRAME = "Frame";
        public static final String IMAGE = "Image";
        public static final String IMPORT = "Import";
        public static final String JSON = "JSON";
        public static final String MANIFEST = "Manifest";
        public static final String PING = "Ping";
        public static final String PLUGINDATA = "PluginData";
        public static final String PLUGINRESOURCE = "PluginResource";
        public static final String PREFETCH = "Prefetch";
        public static final String RESOURCE = "Resource";
        public static final String SCRIPT = "Script";
        public static final String SERVICEWORKER = "ServiceWorker";
        public static final String SHAREDWORKER = "SharedWorker";
        public static final String SPECULATIONRULES = "SpeculationRules";
        public static final String STYLESHEET = "Stylesheet";
        public static final String TRACK = "Track";
        public static final String VIDEO = "Video";
        public static final String WORKER = "Worker";
        public static final String XMLHTTPREQUEST = "XMLHttpRequest";
        public static final String XSLT = "XSLT";
    }
    /**
     */
    public static final class MixedContentIssueDetails extends CdpObject {
        private MixedContentIssueDetails(Map<String, Object> values) { super(values); }
        @Nullable public static MixedContentIssueDetails fromMap(@Nullable Map<String, Object> values) {
            return values == null ? null : new MixedContentIssueDetails(values);
        }
        public static Builder builder() { return new Builder(); }
        /**
         * The type of resource causing the mixed content issue (css, js, iframe, form,...). Marked as optional because it is mapped to from blink::mojom::RequestContextType, which will be replaced by network::mojom::RequestDestination
         * @return the protocol field value
         */
        @Nullable public String resourceType() {
            return (String) value("resourceType");
        }
        /**
         * The way the mixed content issue is being resolved.
         * @return the protocol field value
         */
        @Nullable public String resolutionStatus() {
            return (String) value("resolutionStatus");
        }
        /**
         * The unsafe http url causing the mixed content issue.
         * @return the protocol field value
         */
        @Nullable public String insecureURL() {
            return (String) value("insecureURL");
        }
        /**
         * The url responsible for the call to an unsafe url.
         * @return the protocol field value
         */
        @Nullable public String mainResourceURL() {
            return (String) value("mainResourceURL");
        }
        /**
         * The mixed content request. Does not always exist (e.g. for unsafe form submission urls).
         * @return the protocol field value
         */
        @Nullable public Audits.AffectedRequest request() {
            return Audits.AffectedRequest.fromMap(objectMap(value("request")));
        }
        /**
         * Optional because not every mixed content issue is necessarily linked to a frame.
         * @return the protocol field value
         */
        @Nullable public Audits.AffectedFrame frame() {
            return Audits.AffectedFrame.fromMap(objectMap(value("frame")));
        }
        public static final class Builder {
            private final Map<String, Object> values = new LinkedHashMap<>();
            /**
             * The type of resource causing the mixed content issue (css, js, iframe, form,...). Marked as optional because it is mapped to from blink::mojom::RequestContextType, which will be replaced by network::mojom::RequestDestination
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder resourceType(@Nullable String value) {
                if (value == null) values.remove("resourceType");
                else values.put("resourceType", jsonValue(value));
                return this;
            }
            /**
             * The way the mixed content issue is being resolved.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder resolutionStatus(@Nullable String value) {
                if (value == null) values.remove("resolutionStatus");
                else values.put("resolutionStatus", jsonValue(value));
                return this;
            }
            /**
             * The unsafe http url causing the mixed content issue.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder insecureURL(@Nullable String value) {
                if (value == null) values.remove("insecureURL");
                else values.put("insecureURL", jsonValue(value));
                return this;
            }
            /**
             * The url responsible for the call to an unsafe url.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder mainResourceURL(@Nullable String value) {
                if (value == null) values.remove("mainResourceURL");
                else values.put("mainResourceURL", jsonValue(value));
                return this;
            }
            /**
             * The mixed content request. Does not always exist (e.g. for unsafe form submission urls).
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder request(@Nullable Audits.AffectedRequest value) {
                if (value == null) values.remove("request");
                else values.put("request", jsonValue(value));
                return this;
            }
            /**
             * Optional because not every mixed content issue is necessarily linked to a frame.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder frame(@Nullable Audits.AffectedFrame value) {
                if (value == null) values.remove("frame");
                else values.put("frame", jsonValue(value));
                return this;
            }
            public MixedContentIssueDetails build() {
                if (!values.containsKey("resolutionStatus")) throw new IllegalStateException("Missing required CDP field: resolutionStatus");
                if (!values.containsKey("insecureURL")) throw new IllegalStateException("Missing required CDP field: insecureURL");
                if (!values.containsKey("mainResourceURL")) throw new IllegalStateException("Missing required CDP field: mainResourceURL");
                return new MixedContentIssueDetails(values);
            }
        }
    }
    /**
     * Enum indicating the reason a response has been blocked. These reasons are refinements of the net error BLOCKED_BY_RESPONSE.
     */
    public static final class BlockedByResponseReason {
        private BlockedByResponseReason() {}
        public static final String COEPFRAMERESOURCENEEDSCOEPHEADER = "CoepFrameResourceNeedsCoepHeader";
        public static final String COOPSANDBOXEDIFRAMECANNOTNAVIGATETOCOOPPAGE = "CoopSandboxedIFrameCannotNavigateToCoopPage";
        public static final String CORPNOTSAMEORIGIN = "CorpNotSameOrigin";
        public static final String CORPNOTSAMEORIGINAFTERDEFAULTEDTOSAMEORIGINBYCOEP = "CorpNotSameOriginAfterDefaultedToSameOriginByCoep";
        public static final String CORPNOTSAMEORIGINAFTERDEFAULTEDTOSAMEORIGINBYDIP = "CorpNotSameOriginAfterDefaultedToSameOriginByDip";
        public static final String CORPNOTSAMEORIGINAFTERDEFAULTEDTOSAMEORIGINBYCOEPANDDIP = "CorpNotSameOriginAfterDefaultedToSameOriginByCoepAndDip";
        public static final String CORPNOTSAMESITE = "CorpNotSameSite";
        public static final String SRIMESSAGESIGNATUREMISMATCH = "SRIMessageSignatureMismatch";
    }
    /**
     * Details for a request that has been blocked with the BLOCKED_BY_RESPONSE code. Currently only used for COEP/COOP, but may be extended to include some CSP errors in the future.
     */
    public static final class BlockedByResponseIssueDetails extends CdpObject {
        private BlockedByResponseIssueDetails(Map<String, Object> values) { super(values); }
        @Nullable public static BlockedByResponseIssueDetails fromMap(@Nullable Map<String, Object> values) {
            return values == null ? null : new BlockedByResponseIssueDetails(values);
        }
        public static Builder builder() { return new Builder(); }
        /**
         * Returns the request field.
         * @return the protocol field value
         */
        @Nullable public Audits.AffectedRequest request() {
            return Audits.AffectedRequest.fromMap(objectMap(value("request")));
        }
        /**
         * Returns the parentFrame field.
         * @return the protocol field value
         */
        @Nullable public Audits.AffectedFrame parentFrame() {
            return Audits.AffectedFrame.fromMap(objectMap(value("parentFrame")));
        }
        /**
         * Returns the blockedFrame field.
         * @return the protocol field value
         */
        @Nullable public Audits.AffectedFrame blockedFrame() {
            return Audits.AffectedFrame.fromMap(objectMap(value("blockedFrame")));
        }
        /**
         * Returns the reason field.
         * @return the protocol field value
         */
        @Nullable public String reason() {
            return (String) value("reason");
        }
        public static final class Builder {
            private final Map<String, Object> values = new LinkedHashMap<>();
            /**
             * Sets the request field.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder request(@Nullable Audits.AffectedRequest value) {
                if (value == null) values.remove("request");
                else values.put("request", jsonValue(value));
                return this;
            }
            /**
             * Sets the parentFrame field.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder parentFrame(@Nullable Audits.AffectedFrame value) {
                if (value == null) values.remove("parentFrame");
                else values.put("parentFrame", jsonValue(value));
                return this;
            }
            /**
             * Sets the blockedFrame field.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder blockedFrame(@Nullable Audits.AffectedFrame value) {
                if (value == null) values.remove("blockedFrame");
                else values.put("blockedFrame", jsonValue(value));
                return this;
            }
            /**
             * Sets the reason field.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder reason(@Nullable String value) {
                if (value == null) values.remove("reason");
                else values.put("reason", jsonValue(value));
                return this;
            }
            public BlockedByResponseIssueDetails build() {
                if (!values.containsKey("request")) throw new IllegalStateException("Missing required CDP field: request");
                if (!values.containsKey("reason")) throw new IllegalStateException("Missing required CDP field: reason");
                return new BlockedByResponseIssueDetails(values);
            }
        }
    }
    /**
     * Wire values for HeavyAdResolutionStatus.
     */
    public static final class HeavyAdResolutionStatus {
        private HeavyAdResolutionStatus() {}
        public static final String HEAVYADBLOCKED = "HeavyAdBlocked";
        public static final String HEAVYADWARNING = "HeavyAdWarning";
    }
    /**
     * Wire values for HeavyAdReason.
     */
    public static final class HeavyAdReason {
        private HeavyAdReason() {}
        public static final String NETWORKTOTALLIMIT = "NetworkTotalLimit";
        public static final String CPUTOTALLIMIT = "CpuTotalLimit";
        public static final String CPUPEAKLIMIT = "CpuPeakLimit";
    }
    /**
     */
    public static final class HeavyAdIssueDetails extends CdpObject {
        private HeavyAdIssueDetails(Map<String, Object> values) { super(values); }
        @Nullable public static HeavyAdIssueDetails fromMap(@Nullable Map<String, Object> values) {
            return values == null ? null : new HeavyAdIssueDetails(values);
        }
        public static Builder builder() { return new Builder(); }
        /**
         * The resolution status, either blocking the content or warning.
         * @return the protocol field value
         */
        @Nullable public String resolution() {
            return (String) value("resolution");
        }
        /**
         * The reason the ad was blocked, total network or cpu or peak cpu.
         * @return the protocol field value
         */
        @Nullable public String reason() {
            return (String) value("reason");
        }
        /**
         * The frame that was blocked.
         * @return the protocol field value
         */
        @Nullable public Audits.AffectedFrame frame() {
            return Audits.AffectedFrame.fromMap(objectMap(value("frame")));
        }
        public static final class Builder {
            private final Map<String, Object> values = new LinkedHashMap<>();
            /**
             * The resolution status, either blocking the content or warning.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder resolution(@Nullable String value) {
                if (value == null) values.remove("resolution");
                else values.put("resolution", jsonValue(value));
                return this;
            }
            /**
             * The reason the ad was blocked, total network or cpu or peak cpu.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder reason(@Nullable String value) {
                if (value == null) values.remove("reason");
                else values.put("reason", jsonValue(value));
                return this;
            }
            /**
             * The frame that was blocked.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder frame(@Nullable Audits.AffectedFrame value) {
                if (value == null) values.remove("frame");
                else values.put("frame", jsonValue(value));
                return this;
            }
            public HeavyAdIssueDetails build() {
                if (!values.containsKey("resolution")) throw new IllegalStateException("Missing required CDP field: resolution");
                if (!values.containsKey("reason")) throw new IllegalStateException("Missing required CDP field: reason");
                if (!values.containsKey("frame")) throw new IllegalStateException("Missing required CDP field: frame");
                return new HeavyAdIssueDetails(values);
            }
        }
    }
    /**
     * Wire values for ContentSecurityPolicyViolationType.
     */
    public static final class ContentSecurityPolicyViolationType {
        private ContentSecurityPolicyViolationType() {}
        public static final String KINLINEVIOLATION = "kInlineViolation";
        public static final String KEVALVIOLATION = "kEvalViolation";
        public static final String KURLVIOLATION = "kURLViolation";
        public static final String KSRIVIOLATION = "kSRIViolation";
        public static final String KTRUSTEDTYPESSINKVIOLATION = "kTrustedTypesSinkViolation";
        public static final String KTRUSTEDTYPESPOLICYVIOLATION = "kTrustedTypesPolicyViolation";
        public static final String KWASMEVALVIOLATION = "kWasmEvalViolation";
    }
    /**
     */
    public static final class SourceCodeLocation extends CdpObject {
        private SourceCodeLocation(Map<String, Object> values) { super(values); }
        @Nullable public static SourceCodeLocation fromMap(@Nullable Map<String, Object> values) {
            return values == null ? null : new SourceCodeLocation(values);
        }
        public static Builder builder() { return new Builder(); }
        /**
         * Returns the scriptId field.
         * @return the protocol field value
         */
        @Nullable public String scriptId() {
            return (String) value("scriptId");
        }
        /**
         * Returns the url field.
         * @return the protocol field value
         */
        @Nullable public String url() {
            return (String) value("url");
        }
        /**
         * Returns the lineNumber field.
         * @return the protocol field value
         */
        @Nullable public Long lineNumber() {
            return numberAsLong(value("lineNumber"));
        }
        /**
         * Returns the columnNumber field.
         * @return the protocol field value
         */
        @Nullable public Long columnNumber() {
            return numberAsLong(value("columnNumber"));
        }
        public static final class Builder {
            private final Map<String, Object> values = new LinkedHashMap<>();
            /**
             * Sets the scriptId field.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder scriptId(@Nullable String value) {
                if (value == null) values.remove("scriptId");
                else values.put("scriptId", jsonValue(value));
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
             * Sets the lineNumber field.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder lineNumber(@Nullable Long value) {
                if (value == null) values.remove("lineNumber");
                else values.put("lineNumber", jsonValue(value));
                return this;
            }
            /**
             * Sets the columnNumber field.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder columnNumber(@Nullable Long value) {
                if (value == null) values.remove("columnNumber");
                else values.put("columnNumber", jsonValue(value));
                return this;
            }
            public SourceCodeLocation build() {
                if (!values.containsKey("url")) throw new IllegalStateException("Missing required CDP field: url");
                if (!values.containsKey("lineNumber")) throw new IllegalStateException("Missing required CDP field: lineNumber");
                if (!values.containsKey("columnNumber")) throw new IllegalStateException("Missing required CDP field: columnNumber");
                return new SourceCodeLocation(values);
            }
        }
    }
    /**
     */
    public static final class ContentSecurityPolicyIssueDetails extends CdpObject {
        private ContentSecurityPolicyIssueDetails(Map<String, Object> values) { super(values); }
        @Nullable public static ContentSecurityPolicyIssueDetails fromMap(@Nullable Map<String, Object> values) {
            return values == null ? null : new ContentSecurityPolicyIssueDetails(values);
        }
        public static Builder builder() { return new Builder(); }
        /**
         * The url not included in allowed sources.
         * @return the protocol field value
         */
        @Nullable public String blockedURL() {
            return (String) value("blockedURL");
        }
        /**
         * Specific directive that is violated, causing the CSP issue.
         * @return the protocol field value
         */
        @Nullable public String violatedDirective() {
            return (String) value("violatedDirective");
        }
        /**
         * Returns the isReportOnly field.
         * @return the protocol field value
         */
        @Nullable public Boolean isReportOnly() {
            return (Boolean) value("isReportOnly");
        }
        /**
         * Returns the contentSecurityPolicyViolationType field.
         * @return the protocol field value
         */
        @Nullable public String contentSecurityPolicyViolationType() {
            return (String) value("contentSecurityPolicyViolationType");
        }
        /**
         * Returns the frameAncestor field.
         * @return the protocol field value
         */
        @Nullable public Audits.AffectedFrame frameAncestor() {
            return Audits.AffectedFrame.fromMap(objectMap(value("frameAncestor")));
        }
        /**
         * Returns the sourceCodeLocation field.
         * @return the protocol field value
         */
        @Nullable public Audits.SourceCodeLocation sourceCodeLocation() {
            return Audits.SourceCodeLocation.fromMap(objectMap(value("sourceCodeLocation")));
        }
        /**
         * Returns the violatingNodeId field.
         * @return the protocol field value
         */
        @Nullable public Long violatingNodeId() {
            return numberAsLong(value("violatingNodeId"));
        }
        public static final class Builder {
            private final Map<String, Object> values = new LinkedHashMap<>();
            /**
             * The url not included in allowed sources.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder blockedURL(@Nullable String value) {
                if (value == null) values.remove("blockedURL");
                else values.put("blockedURL", jsonValue(value));
                return this;
            }
            /**
             * Specific directive that is violated, causing the CSP issue.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder violatedDirective(@Nullable String value) {
                if (value == null) values.remove("violatedDirective");
                else values.put("violatedDirective", jsonValue(value));
                return this;
            }
            /**
             * Sets the isReportOnly field.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder isReportOnly(@Nullable Boolean value) {
                if (value == null) values.remove("isReportOnly");
                else values.put("isReportOnly", jsonValue(value));
                return this;
            }
            /**
             * Sets the contentSecurityPolicyViolationType field.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder contentSecurityPolicyViolationType(@Nullable String value) {
                if (value == null) values.remove("contentSecurityPolicyViolationType");
                else values.put("contentSecurityPolicyViolationType", jsonValue(value));
                return this;
            }
            /**
             * Sets the frameAncestor field.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder frameAncestor(@Nullable Audits.AffectedFrame value) {
                if (value == null) values.remove("frameAncestor");
                else values.put("frameAncestor", jsonValue(value));
                return this;
            }
            /**
             * Sets the sourceCodeLocation field.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder sourceCodeLocation(@Nullable Audits.SourceCodeLocation value) {
                if (value == null) values.remove("sourceCodeLocation");
                else values.put("sourceCodeLocation", jsonValue(value));
                return this;
            }
            /**
             * Sets the violatingNodeId field.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder violatingNodeId(@Nullable Long value) {
                if (value == null) values.remove("violatingNodeId");
                else values.put("violatingNodeId", jsonValue(value));
                return this;
            }
            public ContentSecurityPolicyIssueDetails build() {
                if (!values.containsKey("violatedDirective")) throw new IllegalStateException("Missing required CDP field: violatedDirective");
                if (!values.containsKey("isReportOnly")) throw new IllegalStateException("Missing required CDP field: isReportOnly");
                if (!values.containsKey("contentSecurityPolicyViolationType")) throw new IllegalStateException("Missing required CDP field: contentSecurityPolicyViolationType");
                return new ContentSecurityPolicyIssueDetails(values);
            }
        }
    }
    /**
     * Wire values for SharedArrayBufferIssueType.
     */
    public static final class SharedArrayBufferIssueType {
        private SharedArrayBufferIssueType() {}
        public static final String TRANSFERISSUE = "TransferIssue";
        public static final String CREATIONISSUE = "CreationIssue";
    }
    /**
     * Details for a issue arising from an SAB being instantiated in, or transferred to a context that is not cross-origin isolated.
     */
    public static final class SharedArrayBufferIssueDetails extends CdpObject {
        private SharedArrayBufferIssueDetails(Map<String, Object> values) { super(values); }
        @Nullable public static SharedArrayBufferIssueDetails fromMap(@Nullable Map<String, Object> values) {
            return values == null ? null : new SharedArrayBufferIssueDetails(values);
        }
        public static Builder builder() { return new Builder(); }
        /**
         * Returns the sourceCodeLocation field.
         * @return the protocol field value
         */
        @Nullable public Audits.SourceCodeLocation sourceCodeLocation() {
            return Audits.SourceCodeLocation.fromMap(objectMap(value("sourceCodeLocation")));
        }
        /**
         * Returns the isWarning field.
         * @return the protocol field value
         */
        @Nullable public Boolean isWarning() {
            return (Boolean) value("isWarning");
        }
        /**
         * Returns the type field.
         * @return the protocol field value
         */
        @Nullable public String type() {
            return (String) value("type");
        }
        public static final class Builder {
            private final Map<String, Object> values = new LinkedHashMap<>();
            /**
             * Sets the sourceCodeLocation field.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder sourceCodeLocation(@Nullable Audits.SourceCodeLocation value) {
                if (value == null) values.remove("sourceCodeLocation");
                else values.put("sourceCodeLocation", jsonValue(value));
                return this;
            }
            /**
             * Sets the isWarning field.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder isWarning(@Nullable Boolean value) {
                if (value == null) values.remove("isWarning");
                else values.put("isWarning", jsonValue(value));
                return this;
            }
            /**
             * Sets the type field.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder type(@Nullable String value) {
                if (value == null) values.remove("type");
                else values.put("type", jsonValue(value));
                return this;
            }
            public SharedArrayBufferIssueDetails build() {
                if (!values.containsKey("sourceCodeLocation")) throw new IllegalStateException("Missing required CDP field: sourceCodeLocation");
                if (!values.containsKey("isWarning")) throw new IllegalStateException("Missing required CDP field: isWarning");
                if (!values.containsKey("type")) throw new IllegalStateException("Missing required CDP field: type");
                return new SharedArrayBufferIssueDetails(values);
            }
        }
    }
    /**
     */
    public static final class LowTextContrastIssueDetails extends CdpObject {
        private LowTextContrastIssueDetails(Map<String, Object> values) { super(values); }
        @Nullable public static LowTextContrastIssueDetails fromMap(@Nullable Map<String, Object> values) {
            return values == null ? null : new LowTextContrastIssueDetails(values);
        }
        public static Builder builder() { return new Builder(); }
        /**
         * Returns the violatingNodeId field.
         * @return the protocol field value
         */
        @Nullable public Long violatingNodeId() {
            return numberAsLong(value("violatingNodeId"));
        }
        /**
         * Returns the violatingNodeSelector field.
         * @return the protocol field value
         */
        @Nullable public String violatingNodeSelector() {
            return (String) value("violatingNodeSelector");
        }
        /**
         * Returns the contrastRatio field.
         * @return the protocol field value
         */
        @Nullable public Double contrastRatio() {
            return numberAsDouble(value("contrastRatio"));
        }
        /**
         * Returns the thresholdAA field.
         * @return the protocol field value
         */
        @Nullable public Double thresholdAA() {
            return numberAsDouble(value("thresholdAA"));
        }
        /**
         * Returns the thresholdAAA field.
         * @return the protocol field value
         */
        @Nullable public Double thresholdAAA() {
            return numberAsDouble(value("thresholdAAA"));
        }
        /**
         * Returns the fontSize field.
         * @return the protocol field value
         */
        @Nullable public String fontSize() {
            return (String) value("fontSize");
        }
        /**
         * Returns the fontWeight field.
         * @return the protocol field value
         */
        @Nullable public String fontWeight() {
            return (String) value("fontWeight");
        }
        public static final class Builder {
            private final Map<String, Object> values = new LinkedHashMap<>();
            /**
             * Sets the violatingNodeId field.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder violatingNodeId(@Nullable Long value) {
                if (value == null) values.remove("violatingNodeId");
                else values.put("violatingNodeId", jsonValue(value));
                return this;
            }
            /**
             * Sets the violatingNodeSelector field.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder violatingNodeSelector(@Nullable String value) {
                if (value == null) values.remove("violatingNodeSelector");
                else values.put("violatingNodeSelector", jsonValue(value));
                return this;
            }
            /**
             * Sets the contrastRatio field.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder contrastRatio(@Nullable Double value) {
                if (value == null) values.remove("contrastRatio");
                else values.put("contrastRatio", jsonValue(value));
                return this;
            }
            /**
             * Sets the thresholdAA field.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder thresholdAA(@Nullable Double value) {
                if (value == null) values.remove("thresholdAA");
                else values.put("thresholdAA", jsonValue(value));
                return this;
            }
            /**
             * Sets the thresholdAAA field.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder thresholdAAA(@Nullable Double value) {
                if (value == null) values.remove("thresholdAAA");
                else values.put("thresholdAAA", jsonValue(value));
                return this;
            }
            /**
             * Sets the fontSize field.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder fontSize(@Nullable String value) {
                if (value == null) values.remove("fontSize");
                else values.put("fontSize", jsonValue(value));
                return this;
            }
            /**
             * Sets the fontWeight field.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder fontWeight(@Nullable String value) {
                if (value == null) values.remove("fontWeight");
                else values.put("fontWeight", jsonValue(value));
                return this;
            }
            public LowTextContrastIssueDetails build() {
                if (!values.containsKey("violatingNodeId")) throw new IllegalStateException("Missing required CDP field: violatingNodeId");
                if (!values.containsKey("violatingNodeSelector")) throw new IllegalStateException("Missing required CDP field: violatingNodeSelector");
                if (!values.containsKey("contrastRatio")) throw new IllegalStateException("Missing required CDP field: contrastRatio");
                if (!values.containsKey("thresholdAA")) throw new IllegalStateException("Missing required CDP field: thresholdAA");
                if (!values.containsKey("thresholdAAA")) throw new IllegalStateException("Missing required CDP field: thresholdAAA");
                if (!values.containsKey("fontSize")) throw new IllegalStateException("Missing required CDP field: fontSize");
                if (!values.containsKey("fontWeight")) throw new IllegalStateException("Missing required CDP field: fontWeight");
                return new LowTextContrastIssueDetails(values);
            }
        }
    }
    /**
     * Details for a CORS related issue, e.g. a warning or error related to CORS RFC1918 enforcement.
     */
    public static final class CorsIssueDetails extends CdpObject {
        private CorsIssueDetails(Map<String, Object> values) { super(values); }
        @Nullable public static CorsIssueDetails fromMap(@Nullable Map<String, Object> values) {
            return values == null ? null : new CorsIssueDetails(values);
        }
        public static Builder builder() { return new Builder(); }
        /**
         * Returns the corsErrorStatus field.
         * @return the protocol field value
         */
        @Nullable public Network.CorsErrorStatus corsErrorStatus() {
            return Network.CorsErrorStatus.fromMap(objectMap(value("corsErrorStatus")));
        }
        /**
         * Returns the isWarning field.
         * @return the protocol field value
         */
        @Nullable public Boolean isWarning() {
            return (Boolean) value("isWarning");
        }
        /**
         * Returns the request field.
         * @return the protocol field value
         */
        @Nullable public Audits.AffectedRequest request() {
            return Audits.AffectedRequest.fromMap(objectMap(value("request")));
        }
        /**
         * Returns the location field.
         * @return the protocol field value
         */
        @Nullable public Audits.SourceCodeLocation location() {
            return Audits.SourceCodeLocation.fromMap(objectMap(value("location")));
        }
        /**
         * Returns the initiatorOrigin field.
         * @return the protocol field value
         */
        @Nullable public String initiatorOrigin() {
            return (String) value("initiatorOrigin");
        }
        /**
         * Returns the resourceIPAddressSpace field.
         * @return the protocol field value
         */
        @Nullable public String resourceIPAddressSpace() {
            return (String) value("resourceIPAddressSpace");
        }
        /**
         * Returns the clientSecurityState field.
         * @return the protocol field value
         */
        @Nullable public Network.ClientSecurityState clientSecurityState() {
            return Network.ClientSecurityState.fromMap(objectMap(value("clientSecurityState")));
        }
        public static final class Builder {
            private final Map<String, Object> values = new LinkedHashMap<>();
            /**
             * Sets the corsErrorStatus field.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder corsErrorStatus(@Nullable Network.CorsErrorStatus value) {
                if (value == null) values.remove("corsErrorStatus");
                else values.put("corsErrorStatus", jsonValue(value));
                return this;
            }
            /**
             * Sets the isWarning field.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder isWarning(@Nullable Boolean value) {
                if (value == null) values.remove("isWarning");
                else values.put("isWarning", jsonValue(value));
                return this;
            }
            /**
             * Sets the request field.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder request(@Nullable Audits.AffectedRequest value) {
                if (value == null) values.remove("request");
                else values.put("request", jsonValue(value));
                return this;
            }
            /**
             * Sets the location field.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder location(@Nullable Audits.SourceCodeLocation value) {
                if (value == null) values.remove("location");
                else values.put("location", jsonValue(value));
                return this;
            }
            /**
             * Sets the initiatorOrigin field.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder initiatorOrigin(@Nullable String value) {
                if (value == null) values.remove("initiatorOrigin");
                else values.put("initiatorOrigin", jsonValue(value));
                return this;
            }
            /**
             * Sets the resourceIPAddressSpace field.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder resourceIPAddressSpace(@Nullable String value) {
                if (value == null) values.remove("resourceIPAddressSpace");
                else values.put("resourceIPAddressSpace", jsonValue(value));
                return this;
            }
            /**
             * Sets the clientSecurityState field.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder clientSecurityState(@Nullable Network.ClientSecurityState value) {
                if (value == null) values.remove("clientSecurityState");
                else values.put("clientSecurityState", jsonValue(value));
                return this;
            }
            public CorsIssueDetails build() {
                if (!values.containsKey("corsErrorStatus")) throw new IllegalStateException("Missing required CDP field: corsErrorStatus");
                if (!values.containsKey("isWarning")) throw new IllegalStateException("Missing required CDP field: isWarning");
                if (!values.containsKey("request")) throw new IllegalStateException("Missing required CDP field: request");
                return new CorsIssueDetails(values);
            }
        }
    }
    /**
     * Wire values for AttributionReportingIssueType.
     */
    public static final class AttributionReportingIssueType {
        private AttributionReportingIssueType() {}
        public static final String PERMISSIONPOLICYDISABLED = "PermissionPolicyDisabled";
        public static final String UNTRUSTWORTHYREPORTINGORIGIN = "UntrustworthyReportingOrigin";
        public static final String INSECURECONTEXT = "InsecureContext";
        public static final String INVALIDHEADER = "InvalidHeader";
        public static final String INVALIDREGISTERTRIGGERHEADER = "InvalidRegisterTriggerHeader";
        public static final String SOURCEANDTRIGGERHEADERS = "SourceAndTriggerHeaders";
        public static final String SOURCEIGNORED = "SourceIgnored";
        public static final String TRIGGERIGNORED = "TriggerIgnored";
        public static final String OSSOURCEIGNORED = "OsSourceIgnored";
        public static final String OSTRIGGERIGNORED = "OsTriggerIgnored";
        public static final String INVALIDREGISTEROSSOURCEHEADER = "InvalidRegisterOsSourceHeader";
        public static final String INVALIDREGISTEROSTRIGGERHEADER = "InvalidRegisterOsTriggerHeader";
        public static final String WEBANDOSHEADERS = "WebAndOsHeaders";
        public static final String NOWEBOROSSUPPORT = "NoWebOrOsSupport";
        public static final String NAVIGATIONREGISTRATIONWITHOUTTRANSIENTUSERACTIVATION = "NavigationRegistrationWithoutTransientUserActivation";
        public static final String INVALIDINFOHEADER = "InvalidInfoHeader";
        public static final String NOREGISTERSOURCEHEADER = "NoRegisterSourceHeader";
        public static final String NOREGISTERTRIGGERHEADER = "NoRegisterTriggerHeader";
        public static final String NOREGISTEROSSOURCEHEADER = "NoRegisterOsSourceHeader";
        public static final String NOREGISTEROSTRIGGERHEADER = "NoRegisterOsTriggerHeader";
        public static final String NAVIGATIONREGISTRATIONUNIQUESCOPEALREADYSET = "NavigationRegistrationUniqueScopeAlreadySet";
    }
    /**
     * Wire values for SharedDictionaryError.
     */
    public static final class SharedDictionaryError {
        private SharedDictionaryError() {}
        public static final String USEERRORCROSSORIGINNOCORSREQUEST = "UseErrorCrossOriginNoCorsRequest";
        public static final String USEERRORDICTIONARYLOADFAILURE = "UseErrorDictionaryLoadFailure";
        public static final String USEERRORMATCHINGDICTIONARYNOTUSED = "UseErrorMatchingDictionaryNotUsed";
        public static final String USEERRORUNEXPECTEDCONTENTDICTIONARYHEADER = "UseErrorUnexpectedContentDictionaryHeader";
        public static final String WRITEERRORCOSSORIGINNOCORSREQUEST = "WriteErrorCossOriginNoCorsRequest";
        public static final String WRITEERRORDISALLOWEDBYSETTINGS = "WriteErrorDisallowedBySettings";
        public static final String WRITEERROREXPIREDRESPONSE = "WriteErrorExpiredResponse";
        public static final String WRITEERRORFEATUREDISABLED = "WriteErrorFeatureDisabled";
        public static final String WRITEERRORINSUFFICIENTRESOURCES = "WriteErrorInsufficientResources";
        public static final String WRITEERRORINVALIDMATCHFIELD = "WriteErrorInvalidMatchField";
        public static final String WRITEERRORINVALIDSTRUCTUREDHEADER = "WriteErrorInvalidStructuredHeader";
        public static final String WRITEERRORINVALIDTTLFIELD = "WriteErrorInvalidTTLField";
        public static final String WRITEERRORNAVIGATIONREQUEST = "WriteErrorNavigationRequest";
        public static final String WRITEERRORNOMATCHFIELD = "WriteErrorNoMatchField";
        public static final String WRITEERRORNONINTEGERTTLFIELD = "WriteErrorNonIntegerTTLField";
        public static final String WRITEERRORNONLISTMATCHDESTFIELD = "WriteErrorNonListMatchDestField";
        public static final String WRITEERRORNONSECURECONTEXT = "WriteErrorNonSecureContext";
        public static final String WRITEERRORNONSTRINGIDFIELD = "WriteErrorNonStringIdField";
        public static final String WRITEERRORNONSTRINGINMATCHDESTLIST = "WriteErrorNonStringInMatchDestList";
        public static final String WRITEERRORNONSTRINGMATCHFIELD = "WriteErrorNonStringMatchField";
        public static final String WRITEERRORNONTOKENTYPEFIELD = "WriteErrorNonTokenTypeField";
        public static final String WRITEERRORREQUESTABORTED = "WriteErrorRequestAborted";
        public static final String WRITEERRORSHUTTINGDOWN = "WriteErrorShuttingDown";
        public static final String WRITEERRORTOOLONGIDFIELD = "WriteErrorTooLongIdField";
        public static final String WRITEERRORUNSUPPORTEDTYPE = "WriteErrorUnsupportedType";
    }
    /**
     * Wire values for SRIMessageSignatureError.
     */
    public static final class SRIMessageSignatureError {
        private SRIMessageSignatureError() {}
        public static final String MISSINGSIGNATUREHEADER = "MissingSignatureHeader";
        public static final String MISSINGSIGNATUREINPUTHEADER = "MissingSignatureInputHeader";
        public static final String INVALIDSIGNATUREHEADER = "InvalidSignatureHeader";
        public static final String INVALIDSIGNATUREINPUTHEADER = "InvalidSignatureInputHeader";
        public static final String SIGNATUREHEADERVALUEISNOTBYTESEQUENCE = "SignatureHeaderValueIsNotByteSequence";
        public static final String SIGNATUREHEADERVALUEISPARAMETERIZED = "SignatureHeaderValueIsParameterized";
        public static final String SIGNATUREHEADERVALUEISINCORRECTLENGTH = "SignatureHeaderValueIsIncorrectLength";
        public static final String SIGNATUREINPUTHEADERMISSINGLABEL = "SignatureInputHeaderMissingLabel";
        public static final String SIGNATUREINPUTHEADERVALUENOTINNERLIST = "SignatureInputHeaderValueNotInnerList";
        public static final String SIGNATUREINPUTHEADERVALUEMISSINGCOMPONENTS = "SignatureInputHeaderValueMissingComponents";
        public static final String SIGNATUREINPUTHEADERINVALIDCOMPONENTTYPE = "SignatureInputHeaderInvalidComponentType";
        public static final String SIGNATUREINPUTHEADERINVALIDCOMPONENTNAME = "SignatureInputHeaderInvalidComponentName";
        public static final String SIGNATUREINPUTHEADERINVALIDHEADERCOMPONENTPARAMETER = "SignatureInputHeaderInvalidHeaderComponentParameter";
        public static final String SIGNATUREINPUTHEADERINVALIDDERIVEDCOMPONENTPARAMETER = "SignatureInputHeaderInvalidDerivedComponentParameter";
        public static final String SIGNATUREINPUTHEADERKEYIDLENGTH = "SignatureInputHeaderKeyIdLength";
        public static final String SIGNATUREINPUTHEADERINVALIDPARAMETER = "SignatureInputHeaderInvalidParameter";
        public static final String SIGNATUREINPUTHEADERMISSINGREQUIREDPARAMETERS = "SignatureInputHeaderMissingRequiredParameters";
        public static final String VALIDATIONFAILEDSIGNATUREEXPIRED = "ValidationFailedSignatureExpired";
        public static final String VALIDATIONFAILEDINVALIDLENGTH = "ValidationFailedInvalidLength";
        public static final String VALIDATIONFAILEDSIGNATUREMISMATCH = "ValidationFailedSignatureMismatch";
        public static final String VALIDATIONFAILEDINTEGRITYMISMATCH = "ValidationFailedIntegrityMismatch";
    }
    /**
     * Wire values for UnencodedDigestError.
     */
    public static final class UnencodedDigestError {
        private UnencodedDigestError() {}
        public static final String MALFORMEDDICTIONARY = "MalformedDictionary";
        public static final String UNKNOWNALGORITHM = "UnknownAlgorithm";
        public static final String INCORRECTDIGESTTYPE = "IncorrectDigestType";
        public static final String INCORRECTDIGESTLENGTH = "IncorrectDigestLength";
    }
    /**
     * Wire values for ConnectionAllowlistError.
     */
    public static final class ConnectionAllowlistError {
        private ConnectionAllowlistError() {}
        public static final String INVALIDHEADER = "InvalidHeader";
        public static final String MORETHANONELIST = "MoreThanOneList";
        public static final String ITEMNOTINNERLIST = "ItemNotInnerList";
        public static final String INVALIDALLOWLISTITEMTYPE = "InvalidAllowlistItemType";
        public static final String REPORTINGENDPOINTNOTTOKEN = "ReportingEndpointNotToken";
        public static final String INVALIDURLPATTERN = "InvalidUrlPattern";
    }
    /**
     * Details for issues around &quot;Attribution Reporting API&quot; usage. Explainer: https://github.com/WICG/attribution-reporting-api
     */
    public static final class AttributionReportingIssueDetails extends CdpObject {
        private AttributionReportingIssueDetails(Map<String, Object> values) { super(values); }
        @Nullable public static AttributionReportingIssueDetails fromMap(@Nullable Map<String, Object> values) {
            return values == null ? null : new AttributionReportingIssueDetails(values);
        }
        public static Builder builder() { return new Builder(); }
        /**
         * Returns the violationType field.
         * @return the protocol field value
         */
        @Nullable public String violationType() {
            return (String) value("violationType");
        }
        /**
         * Returns the request field.
         * @return the protocol field value
         */
        @Nullable public Audits.AffectedRequest request() {
            return Audits.AffectedRequest.fromMap(objectMap(value("request")));
        }
        /**
         * Returns the violatingNodeId field.
         * @return the protocol field value
         */
        @Nullable public Long violatingNodeId() {
            return numberAsLong(value("violatingNodeId"));
        }
        /**
         * Returns the invalidParameter field.
         * @return the protocol field value
         */
        @Nullable public String invalidParameter() {
            return (String) value("invalidParameter");
        }
        public static final class Builder {
            private final Map<String, Object> values = new LinkedHashMap<>();
            /**
             * Sets the violationType field.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder violationType(@Nullable String value) {
                if (value == null) values.remove("violationType");
                else values.put("violationType", jsonValue(value));
                return this;
            }
            /**
             * Sets the request field.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder request(@Nullable Audits.AffectedRequest value) {
                if (value == null) values.remove("request");
                else values.put("request", jsonValue(value));
                return this;
            }
            /**
             * Sets the violatingNodeId field.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder violatingNodeId(@Nullable Long value) {
                if (value == null) values.remove("violatingNodeId");
                else values.put("violatingNodeId", jsonValue(value));
                return this;
            }
            /**
             * Sets the invalidParameter field.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder invalidParameter(@Nullable String value) {
                if (value == null) values.remove("invalidParameter");
                else values.put("invalidParameter", jsonValue(value));
                return this;
            }
            public AttributionReportingIssueDetails build() {
                if (!values.containsKey("violationType")) throw new IllegalStateException("Missing required CDP field: violationType");
                return new AttributionReportingIssueDetails(values);
            }
        }
    }
    /**
     * Details for issues about documents in Quirks Mode or Limited Quirks Mode that affects page layouting.
     */
    public static final class QuirksModeIssueDetails extends CdpObject {
        private QuirksModeIssueDetails(Map<String, Object> values) { super(values); }
        @Nullable public static QuirksModeIssueDetails fromMap(@Nullable Map<String, Object> values) {
            return values == null ? null : new QuirksModeIssueDetails(values);
        }
        public static Builder builder() { return new Builder(); }
        /**
         * If false, it means the document&#x27;s mode is &quot;quirks&quot; instead of &quot;limited-quirks&quot;.
         * @return the protocol field value
         */
        @Nullable public Boolean isLimitedQuirksMode() {
            return (Boolean) value("isLimitedQuirksMode");
        }
        /**
         * Returns the documentNodeId field.
         * @return the protocol field value
         */
        @Nullable public Long documentNodeId() {
            return numberAsLong(value("documentNodeId"));
        }
        /**
         * Returns the url field.
         * @return the protocol field value
         */
        @Nullable public String url() {
            return (String) value("url");
        }
        /**
         * Returns the frameId field.
         * @return the protocol field value
         */
        @Nullable public String frameId() {
            return (String) value("frameId");
        }
        /**
         * Returns the loaderId field.
         * @return the protocol field value
         */
        @Nullable public String loaderId() {
            return (String) value("loaderId");
        }
        public static final class Builder {
            private final Map<String, Object> values = new LinkedHashMap<>();
            /**
             * If false, it means the document&#x27;s mode is &quot;quirks&quot; instead of &quot;limited-quirks&quot;.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder isLimitedQuirksMode(@Nullable Boolean value) {
                if (value == null) values.remove("isLimitedQuirksMode");
                else values.put("isLimitedQuirksMode", jsonValue(value));
                return this;
            }
            /**
             * Sets the documentNodeId field.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder documentNodeId(@Nullable Long value) {
                if (value == null) values.remove("documentNodeId");
                else values.put("documentNodeId", jsonValue(value));
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
             * Sets the frameId field.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder frameId(@Nullable String value) {
                if (value == null) values.remove("frameId");
                else values.put("frameId", jsonValue(value));
                return this;
            }
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
            public QuirksModeIssueDetails build() {
                if (!values.containsKey("isLimitedQuirksMode")) throw new IllegalStateException("Missing required CDP field: isLimitedQuirksMode");
                if (!values.containsKey("documentNodeId")) throw new IllegalStateException("Missing required CDP field: documentNodeId");
                if (!values.containsKey("url")) throw new IllegalStateException("Missing required CDP field: url");
                if (!values.containsKey("frameId")) throw new IllegalStateException("Missing required CDP field: frameId");
                if (!values.containsKey("loaderId")) throw new IllegalStateException("Missing required CDP field: loaderId");
                return new QuirksModeIssueDetails(values);
            }
        }
    }
    /**
     * @deprecated Deprecated by the Chromium DevTools Protocol.
     */
    @Deprecated
    public static final class NavigatorUserAgentIssueDetails extends CdpObject {
        private NavigatorUserAgentIssueDetails(Map<String, Object> values) { super(values); }
        @Nullable public static NavigatorUserAgentIssueDetails fromMap(@Nullable Map<String, Object> values) {
            return values == null ? null : new NavigatorUserAgentIssueDetails(values);
        }
        public static Builder builder() { return new Builder(); }
        /**
         * Returns the url field.
         * @return the protocol field value
         */
        @Nullable public String url() {
            return (String) value("url");
        }
        /**
         * Returns the location field.
         * @return the protocol field value
         */
        @Nullable public Audits.SourceCodeLocation location() {
            return Audits.SourceCodeLocation.fromMap(objectMap(value("location")));
        }
        public static final class Builder {
            private final Map<String, Object> values = new LinkedHashMap<>();
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
             * Sets the location field.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder location(@Nullable Audits.SourceCodeLocation value) {
                if (value == null) values.remove("location");
                else values.put("location", jsonValue(value));
                return this;
            }
            public NavigatorUserAgentIssueDetails build() {
                if (!values.containsKey("url")) throw new IllegalStateException("Missing required CDP field: url");
                return new NavigatorUserAgentIssueDetails(values);
            }
        }
    }
    /**
     */
    public static final class SharedDictionaryIssueDetails extends CdpObject {
        private SharedDictionaryIssueDetails(Map<String, Object> values) { super(values); }
        @Nullable public static SharedDictionaryIssueDetails fromMap(@Nullable Map<String, Object> values) {
            return values == null ? null : new SharedDictionaryIssueDetails(values);
        }
        public static Builder builder() { return new Builder(); }
        /**
         * Returns the sharedDictionaryError field.
         * @return the protocol field value
         */
        @Nullable public String sharedDictionaryError() {
            return (String) value("sharedDictionaryError");
        }
        /**
         * Returns the request field.
         * @return the protocol field value
         */
        @Nullable public Audits.AffectedRequest request() {
            return Audits.AffectedRequest.fromMap(objectMap(value("request")));
        }
        public static final class Builder {
            private final Map<String, Object> values = new LinkedHashMap<>();
            /**
             * Sets the sharedDictionaryError field.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder sharedDictionaryError(@Nullable String value) {
                if (value == null) values.remove("sharedDictionaryError");
                else values.put("sharedDictionaryError", jsonValue(value));
                return this;
            }
            /**
             * Sets the request field.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder request(@Nullable Audits.AffectedRequest value) {
                if (value == null) values.remove("request");
                else values.put("request", jsonValue(value));
                return this;
            }
            public SharedDictionaryIssueDetails build() {
                if (!values.containsKey("sharedDictionaryError")) throw new IllegalStateException("Missing required CDP field: sharedDictionaryError");
                if (!values.containsKey("request")) throw new IllegalStateException("Missing required CDP field: request");
                return new SharedDictionaryIssueDetails(values);
            }
        }
    }
    /**
     */
    public static final class SRIMessageSignatureIssueDetails extends CdpObject {
        private SRIMessageSignatureIssueDetails(Map<String, Object> values) { super(values); }
        @Nullable public static SRIMessageSignatureIssueDetails fromMap(@Nullable Map<String, Object> values) {
            return values == null ? null : new SRIMessageSignatureIssueDetails(values);
        }
        public static Builder builder() { return new Builder(); }
        /**
         * Returns the error field.
         * @return the protocol field value
         */
        @Nullable public String error() {
            return (String) value("error");
        }
        /**
         * Returns the signatureBase field.
         * @return the protocol field value
         */
        @Nullable public String signatureBase() {
            return (String) value("signatureBase");
        }
        /**
         * Returns the integrityAssertions field.
         * @return the protocol field value
         */
        @Nullable public java.util.List<String> integrityAssertions() {
            return list(value("integrityAssertions"), element0 -> (String) element0);
        }
        /**
         * Returns the request field.
         * @return the protocol field value
         */
        @Nullable public Audits.AffectedRequest request() {
            return Audits.AffectedRequest.fromMap(objectMap(value("request")));
        }
        public static final class Builder {
            private final Map<String, Object> values = new LinkedHashMap<>();
            /**
             * Sets the error field.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder error(@Nullable String value) {
                if (value == null) values.remove("error");
                else values.put("error", jsonValue(value));
                return this;
            }
            /**
             * Sets the signatureBase field.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder signatureBase(@Nullable String value) {
                if (value == null) values.remove("signatureBase");
                else values.put("signatureBase", jsonValue(value));
                return this;
            }
            /**
             * Sets the integrityAssertions field.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder integrityAssertions(@Nullable java.util.List<String> value) {
                if (value == null) values.remove("integrityAssertions");
                else values.put("integrityAssertions", jsonValue(value));
                return this;
            }
            /**
             * Sets the request field.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder request(@Nullable Audits.AffectedRequest value) {
                if (value == null) values.remove("request");
                else values.put("request", jsonValue(value));
                return this;
            }
            public SRIMessageSignatureIssueDetails build() {
                if (!values.containsKey("error")) throw new IllegalStateException("Missing required CDP field: error");
                if (!values.containsKey("signatureBase")) throw new IllegalStateException("Missing required CDP field: signatureBase");
                if (!values.containsKey("integrityAssertions")) throw new IllegalStateException("Missing required CDP field: integrityAssertions");
                if (!values.containsKey("request")) throw new IllegalStateException("Missing required CDP field: request");
                return new SRIMessageSignatureIssueDetails(values);
            }
        }
    }
    /**
     */
    public static final class UnencodedDigestIssueDetails extends CdpObject {
        private UnencodedDigestIssueDetails(Map<String, Object> values) { super(values); }
        @Nullable public static UnencodedDigestIssueDetails fromMap(@Nullable Map<String, Object> values) {
            return values == null ? null : new UnencodedDigestIssueDetails(values);
        }
        public static Builder builder() { return new Builder(); }
        /**
         * Returns the error field.
         * @return the protocol field value
         */
        @Nullable public String error() {
            return (String) value("error");
        }
        /**
         * Returns the request field.
         * @return the protocol field value
         */
        @Nullable public Audits.AffectedRequest request() {
            return Audits.AffectedRequest.fromMap(objectMap(value("request")));
        }
        public static final class Builder {
            private final Map<String, Object> values = new LinkedHashMap<>();
            /**
             * Sets the error field.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder error(@Nullable String value) {
                if (value == null) values.remove("error");
                else values.put("error", jsonValue(value));
                return this;
            }
            /**
             * Sets the request field.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder request(@Nullable Audits.AffectedRequest value) {
                if (value == null) values.remove("request");
                else values.put("request", jsonValue(value));
                return this;
            }
            public UnencodedDigestIssueDetails build() {
                if (!values.containsKey("error")) throw new IllegalStateException("Missing required CDP field: error");
                if (!values.containsKey("request")) throw new IllegalStateException("Missing required CDP field: request");
                return new UnencodedDigestIssueDetails(values);
            }
        }
    }
    /**
     */
    public static final class ConnectionAllowlistIssueDetails extends CdpObject {
        private ConnectionAllowlistIssueDetails(Map<String, Object> values) { super(values); }
        @Nullable public static ConnectionAllowlistIssueDetails fromMap(@Nullable Map<String, Object> values) {
            return values == null ? null : new ConnectionAllowlistIssueDetails(values);
        }
        public static Builder builder() { return new Builder(); }
        /**
         * Returns the error field.
         * @return the protocol field value
         */
        @Nullable public String error() {
            return (String) value("error");
        }
        /**
         * Returns the request field.
         * @return the protocol field value
         */
        @Nullable public Audits.AffectedRequest request() {
            return Audits.AffectedRequest.fromMap(objectMap(value("request")));
        }
        public static final class Builder {
            private final Map<String, Object> values = new LinkedHashMap<>();
            /**
             * Sets the error field.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder error(@Nullable String value) {
                if (value == null) values.remove("error");
                else values.put("error", jsonValue(value));
                return this;
            }
            /**
             * Sets the request field.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder request(@Nullable Audits.AffectedRequest value) {
                if (value == null) values.remove("request");
                else values.put("request", jsonValue(value));
                return this;
            }
            public ConnectionAllowlistIssueDetails build() {
                if (!values.containsKey("error")) throw new IllegalStateException("Missing required CDP field: error");
                if (!values.containsKey("request")) throw new IllegalStateException("Missing required CDP field: request");
                return new ConnectionAllowlistIssueDetails(values);
            }
        }
    }
    /**
     * Wire values for GenericIssueErrorType.
     */
    public static final class GenericIssueErrorType {
        private GenericIssueErrorType() {}
        public static final String FORMLABELFORNAMEERROR = "FormLabelForNameError";
        public static final String FORMDUPLICATEIDFORINPUTERROR = "FormDuplicateIdForInputError";
        public static final String FORMINPUTWITHNOLABELERROR = "FormInputWithNoLabelError";
        public static final String FORMAUTOCOMPLETEATTRIBUTEEMPTYERROR = "FormAutocompleteAttributeEmptyError";
        public static final String FORMEMPTYIDANDNAMEATTRIBUTESFORINPUTERROR = "FormEmptyIdAndNameAttributesForInputError";
        public static final String FORMARIALABELLEDBYTONONEXISTINGIDERROR = "FormAriaLabelledByToNonExistingIdError";
        public static final String FORMINPUTASSIGNEDAUTOCOMPLETEVALUETOIDORNAMEATTRIBUTEERROR = "FormInputAssignedAutocompleteValueToIdOrNameAttributeError";
        public static final String FORMLABELHASNEITHERFORNORNESTEDINPUTERROR = "FormLabelHasNeitherForNorNestedInputError";
        public static final String FORMLABELFORMATCHESNONEXISTINGIDERROR = "FormLabelForMatchesNonExistingIdError";
        public static final String FORMINPUTHASWRONGBUTWELLINTENDEDAUTOCOMPLETEVALUEERROR = "FormInputHasWrongButWellIntendedAutocompleteValueError";
        public static final String RESPONSEWASBLOCKEDBYORB = "ResponseWasBlockedByORB";
        public static final String NAVIGATIONENTRYMARKEDSKIPPABLE = "NavigationEntryMarkedSkippable";
        public static final String AUTOFILLANDMANUALTEXTPOLICYCONTROLLEDFEATURESINFO = "AutofillAndManualTextPolicyControlledFeaturesInfo";
        public static final String AUTOFILLPOLICYCONTROLLEDFEATUREINFO = "AutofillPolicyControlledFeatureInfo";
        public static final String MANUALTEXTPOLICYCONTROLLEDFEATUREINFO = "ManualTextPolicyControlledFeatureInfo";
    }
    /**
     * Depending on the concrete errorType, different properties are set.
     */
    public static final class GenericIssueDetails extends CdpObject {
        private GenericIssueDetails(Map<String, Object> values) { super(values); }
        @Nullable public static GenericIssueDetails fromMap(@Nullable Map<String, Object> values) {
            return values == null ? null : new GenericIssueDetails(values);
        }
        public static Builder builder() { return new Builder(); }
        /**
         * Issues with the same errorType are aggregated in the frontend.
         * @return the protocol field value
         */
        @Nullable public String errorType() {
            return (String) value("errorType");
        }
        /**
         * Returns the frameId field.
         * @return the protocol field value
         */
        @Nullable public String frameId() {
            return (String) value("frameId");
        }
        /**
         * Returns the violatingNodeId field.
         * @return the protocol field value
         */
        @Nullable public Long violatingNodeId() {
            return numberAsLong(value("violatingNodeId"));
        }
        /**
         * Returns the violatingNodeAttribute field.
         * @return the protocol field value
         */
        @Nullable public String violatingNodeAttribute() {
            return (String) value("violatingNodeAttribute");
        }
        /**
         * Returns the request field.
         * @return the protocol field value
         */
        @Nullable public Audits.AffectedRequest request() {
            return Audits.AffectedRequest.fromMap(objectMap(value("request")));
        }
        public static final class Builder {
            private final Map<String, Object> values = new LinkedHashMap<>();
            /**
             * Issues with the same errorType are aggregated in the frontend.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder errorType(@Nullable String value) {
                if (value == null) values.remove("errorType");
                else values.put("errorType", jsonValue(value));
                return this;
            }
            /**
             * Sets the frameId field.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder frameId(@Nullable String value) {
                if (value == null) values.remove("frameId");
                else values.put("frameId", jsonValue(value));
                return this;
            }
            /**
             * Sets the violatingNodeId field.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder violatingNodeId(@Nullable Long value) {
                if (value == null) values.remove("violatingNodeId");
                else values.put("violatingNodeId", jsonValue(value));
                return this;
            }
            /**
             * Sets the violatingNodeAttribute field.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder violatingNodeAttribute(@Nullable String value) {
                if (value == null) values.remove("violatingNodeAttribute");
                else values.put("violatingNodeAttribute", jsonValue(value));
                return this;
            }
            /**
             * Sets the request field.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder request(@Nullable Audits.AffectedRequest value) {
                if (value == null) values.remove("request");
                else values.put("request", jsonValue(value));
                return this;
            }
            public GenericIssueDetails build() {
                if (!values.containsKey("errorType")) throw new IllegalStateException("Missing required CDP field: errorType");
                return new GenericIssueDetails(values);
            }
        }
    }
    /**
     * This issue tracks information needed to print a deprecation message. https://source.chromium.org/chromium/chromium/src/+/main:third_party/blink/renderer/core/frame/third_party/blink/renderer/core/frame/deprecation/README.md
     */
    public static final class DeprecationIssueDetails extends CdpObject {
        private DeprecationIssueDetails(Map<String, Object> values) { super(values); }
        @Nullable public static DeprecationIssueDetails fromMap(@Nullable Map<String, Object> values) {
            return values == null ? null : new DeprecationIssueDetails(values);
        }
        public static Builder builder() { return new Builder(); }
        /**
         * Returns the affectedFrame field.
         * @return the protocol field value
         */
        @Nullable public Audits.AffectedFrame affectedFrame() {
            return Audits.AffectedFrame.fromMap(objectMap(value("affectedFrame")));
        }
        /**
         * Returns the sourceCodeLocation field.
         * @return the protocol field value
         */
        @Nullable public Audits.SourceCodeLocation sourceCodeLocation() {
            return Audits.SourceCodeLocation.fromMap(objectMap(value("sourceCodeLocation")));
        }
        /**
         * One of the deprecation names from third_party/blink/renderer/core/frame/deprecation/deprecation.json5
         * @return the protocol field value
         */
        @Nullable public String type() {
            return (String) value("type");
        }
        public static final class Builder {
            private final Map<String, Object> values = new LinkedHashMap<>();
            /**
             * Sets the affectedFrame field.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder affectedFrame(@Nullable Audits.AffectedFrame value) {
                if (value == null) values.remove("affectedFrame");
                else values.put("affectedFrame", jsonValue(value));
                return this;
            }
            /**
             * Sets the sourceCodeLocation field.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder sourceCodeLocation(@Nullable Audits.SourceCodeLocation value) {
                if (value == null) values.remove("sourceCodeLocation");
                else values.put("sourceCodeLocation", jsonValue(value));
                return this;
            }
            /**
             * One of the deprecation names from third_party/blink/renderer/core/frame/deprecation/deprecation.json5
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder type(@Nullable String value) {
                if (value == null) values.remove("type");
                else values.put("type", jsonValue(value));
                return this;
            }
            public DeprecationIssueDetails build() {
                if (!values.containsKey("sourceCodeLocation")) throw new IllegalStateException("Missing required CDP field: sourceCodeLocation");
                if (!values.containsKey("type")) throw new IllegalStateException("Missing required CDP field: type");
                return new DeprecationIssueDetails(values);
            }
        }
    }
    /**
     * This issue warns about sites in the redirect chain of a finished navigation that may be flagged as trackers and have their state cleared if they don&#x27;t receive a user interaction. Note that in this context &#x27;site&#x27; means eTLD+1. For example, if the URL {@code https://example.test:80/bounce} was in the redirect chain, the site reported would be {@code example.test}.
     */
    public static final class BounceTrackingIssueDetails extends CdpObject {
        private BounceTrackingIssueDetails(Map<String, Object> values) { super(values); }
        @Nullable public static BounceTrackingIssueDetails fromMap(@Nullable Map<String, Object> values) {
            return values == null ? null : new BounceTrackingIssueDetails(values);
        }
        public static Builder builder() { return new Builder(); }
        /**
         * Returns the trackingSites field.
         * @return the protocol field value
         */
        @Nullable public java.util.List<String> trackingSites() {
            return list(value("trackingSites"), element0 -> (String) element0);
        }
        public static final class Builder {
            private final Map<String, Object> values = new LinkedHashMap<>();
            /**
             * Sets the trackingSites field.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder trackingSites(@Nullable java.util.List<String> value) {
                if (value == null) values.remove("trackingSites");
                else values.put("trackingSites", jsonValue(value));
                return this;
            }
            public BounceTrackingIssueDetails build() {
                if (!values.containsKey("trackingSites")) throw new IllegalStateException("Missing required CDP field: trackingSites");
                return new BounceTrackingIssueDetails(values);
            }
        }
    }
    /**
     * This issue warns about third-party sites that are accessing cookies on the current page, and have been permitted due to having a global metadata grant. Note that in this context &#x27;site&#x27; means eTLD+1. For example, if the URL {@code https://example.test:80/web_page} was accessing cookies, the site reported would be {@code example.test}.
     */
    public static final class CookieDeprecationMetadataIssueDetails extends CdpObject {
        private CookieDeprecationMetadataIssueDetails(Map<String, Object> values) { super(values); }
        @Nullable public static CookieDeprecationMetadataIssueDetails fromMap(@Nullable Map<String, Object> values) {
            return values == null ? null : new CookieDeprecationMetadataIssueDetails(values);
        }
        public static Builder builder() { return new Builder(); }
        /**
         * Returns the allowedSites field.
         * @return the protocol field value
         */
        @Nullable public java.util.List<String> allowedSites() {
            return list(value("allowedSites"), element0 -> (String) element0);
        }
        /**
         * Returns the optOutPercentage field.
         * @return the protocol field value
         */
        @Nullable public Double optOutPercentage() {
            return numberAsDouble(value("optOutPercentage"));
        }
        /**
         * Returns the isOptOutTopLevel field.
         * @return the protocol field value
         */
        @Nullable public Boolean isOptOutTopLevel() {
            return (Boolean) value("isOptOutTopLevel");
        }
        /**
         * Returns the operation field.
         * @return the protocol field value
         */
        @Nullable public String operation() {
            return (String) value("operation");
        }
        public static final class Builder {
            private final Map<String, Object> values = new LinkedHashMap<>();
            /**
             * Sets the allowedSites field.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder allowedSites(@Nullable java.util.List<String> value) {
                if (value == null) values.remove("allowedSites");
                else values.put("allowedSites", jsonValue(value));
                return this;
            }
            /**
             * Sets the optOutPercentage field.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder optOutPercentage(@Nullable Double value) {
                if (value == null) values.remove("optOutPercentage");
                else values.put("optOutPercentage", jsonValue(value));
                return this;
            }
            /**
             * Sets the isOptOutTopLevel field.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder isOptOutTopLevel(@Nullable Boolean value) {
                if (value == null) values.remove("isOptOutTopLevel");
                else values.put("isOptOutTopLevel", jsonValue(value));
                return this;
            }
            /**
             * Sets the operation field.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder operation(@Nullable String value) {
                if (value == null) values.remove("operation");
                else values.put("operation", jsonValue(value));
                return this;
            }
            public CookieDeprecationMetadataIssueDetails build() {
                if (!values.containsKey("allowedSites")) throw new IllegalStateException("Missing required CDP field: allowedSites");
                if (!values.containsKey("optOutPercentage")) throw new IllegalStateException("Missing required CDP field: optOutPercentage");
                if (!values.containsKey("isOptOutTopLevel")) throw new IllegalStateException("Missing required CDP field: isOptOutTopLevel");
                if (!values.containsKey("operation")) throw new IllegalStateException("Missing required CDP field: operation");
                return new CookieDeprecationMetadataIssueDetails(values);
            }
        }
    }
    /**
     * Wire values for ClientHintIssueReason.
     */
    public static final class ClientHintIssueReason {
        private ClientHintIssueReason() {}
        public static final String METATAGALLOWLISTINVALIDORIGIN = "MetaTagAllowListInvalidOrigin";
        public static final String METATAGMODIFIEDHTML = "MetaTagModifiedHTML";
    }
    /**
     */
    public static final class FederatedAuthRequestIssueDetails extends CdpObject {
        private FederatedAuthRequestIssueDetails(Map<String, Object> values) { super(values); }
        @Nullable public static FederatedAuthRequestIssueDetails fromMap(@Nullable Map<String, Object> values) {
            return values == null ? null : new FederatedAuthRequestIssueDetails(values);
        }
        public static Builder builder() { return new Builder(); }
        /**
         * Returns the federatedAuthRequestIssueReason field.
         * @return the protocol field value
         */
        @Nullable public String federatedAuthRequestIssueReason() {
            return (String) value("federatedAuthRequestIssueReason");
        }
        public static final class Builder {
            private final Map<String, Object> values = new LinkedHashMap<>();
            /**
             * Sets the federatedAuthRequestIssueReason field.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder federatedAuthRequestIssueReason(@Nullable String value) {
                if (value == null) values.remove("federatedAuthRequestIssueReason");
                else values.put("federatedAuthRequestIssueReason", jsonValue(value));
                return this;
            }
            public FederatedAuthRequestIssueDetails build() {
                if (!values.containsKey("federatedAuthRequestIssueReason")) throw new IllegalStateException("Missing required CDP field: federatedAuthRequestIssueReason");
                return new FederatedAuthRequestIssueDetails(values);
            }
        }
    }
    /**
     * Represents the failure reason when a federated authentication reason fails. Should be updated alongside RequestIdTokenStatus in third_party/blink/public/mojom/devtools/inspector_issue.mojom to include all cases except for success.
     */
    public static final class FederatedAuthRequestIssueReason {
        private FederatedAuthRequestIssueReason() {}
        public static final String SHOULDEMBARGO = "ShouldEmbargo";
        public static final String TOOMANYREQUESTS = "TooManyRequests";
        public static final String WELLKNOWNHTTPNOTFOUND = "WellKnownHttpNotFound";
        public static final String WELLKNOWNNORESPONSE = "WellKnownNoResponse";
        public static final String WELLKNOWNINVALIDRESPONSE = "WellKnownInvalidResponse";
        public static final String WELLKNOWNLISTEMPTY = "WellKnownListEmpty";
        public static final String WELLKNOWNINVALIDCONTENTTYPE = "WellKnownInvalidContentType";
        public static final String CONFIGNOTINWELLKNOWN = "ConfigNotInWellKnown";
        public static final String WELLKNOWNTOOBIG = "WellKnownTooBig";
        public static final String CONFIGHTTPNOTFOUND = "ConfigHttpNotFound";
        public static final String CONFIGNORESPONSE = "ConfigNoResponse";
        public static final String CONFIGINVALIDRESPONSE = "ConfigInvalidResponse";
        public static final String CONFIGINVALIDCONTENTTYPE = "ConfigInvalidContentType";
        public static final String CLIENTMETADATAHTTPNOTFOUND = "ClientMetadataHttpNotFound";
        public static final String CLIENTMETADATANORESPONSE = "ClientMetadataNoResponse";
        public static final String CLIENTMETADATAINVALIDRESPONSE = "ClientMetadataInvalidResponse";
        public static final String CLIENTMETADATAINVALIDCONTENTTYPE = "ClientMetadataInvalidContentType";
        public static final String IDPNOTPOTENTIALLYTRUSTWORTHY = "IdpNotPotentiallyTrustworthy";
        public static final String DISABLEDINSETTINGS = "DisabledInSettings";
        public static final String DISABLEDINFLAGS = "DisabledInFlags";
        public static final String ERRORFETCHINGSIGNIN = "ErrorFetchingSignin";
        public static final String INVALIDSIGNINRESPONSE = "InvalidSigninResponse";
        public static final String ACCOUNTSHTTPNOTFOUND = "AccountsHttpNotFound";
        public static final String ACCOUNTSNORESPONSE = "AccountsNoResponse";
        public static final String ACCOUNTSINVALIDRESPONSE = "AccountsInvalidResponse";
        public static final String ACCOUNTSLISTEMPTY = "AccountsListEmpty";
        public static final String ACCOUNTSINVALIDCONTENTTYPE = "AccountsInvalidContentType";
        public static final String IDTOKENHTTPNOTFOUND = "IdTokenHttpNotFound";
        public static final String IDTOKENNORESPONSE = "IdTokenNoResponse";
        public static final String IDTOKENINVALIDRESPONSE = "IdTokenInvalidResponse";
        public static final String IDTOKENIDPERRORRESPONSE = "IdTokenIdpErrorResponse";
        public static final String IDTOKENCROSSSITEIDPERRORRESPONSE = "IdTokenCrossSiteIdpErrorResponse";
        public static final String IDTOKENINVALIDREQUEST = "IdTokenInvalidRequest";
        public static final String IDTOKENINVALIDCONTENTTYPE = "IdTokenInvalidContentType";
        public static final String ERRORIDTOKEN = "ErrorIdToken";
        public static final String CANCELED = "Canceled";
        public static final String RPPAGENOTVISIBLE = "RpPageNotVisible";
        public static final String SILENTMEDIATIONFAILURE = "SilentMediationFailure";
        public static final String THIRDPARTYCOOKIESBLOCKED = "ThirdPartyCookiesBlocked";
        public static final String NOTSIGNEDINWITHIDP = "NotSignedInWithIdp";
        public static final String MISSINGTRANSIENTUSERACTIVATION = "MissingTransientUserActivation";
        public static final String REPLACEDBYACTIVEMODE = "ReplacedByActiveMode";
        public static final String INVALIDFIELDSSPECIFIED = "InvalidFieldsSpecified";
        public static final String RELYINGPARTYORIGINISOPAQUE = "RelyingPartyOriginIsOpaque";
        public static final String TYPENOTMATCHING = "TypeNotMatching";
        public static final String UIDISMISSEDNOEMBARGO = "UiDismissedNoEmbargo";
        public static final String CORSERROR = "CorsError";
        public static final String SUPPRESSEDBYSEGMENTATIONPLATFORM = "SuppressedBySegmentationPlatform";
    }
    /**
     */
    public static final class FederatedAuthUserInfoRequestIssueDetails extends CdpObject {
        private FederatedAuthUserInfoRequestIssueDetails(Map<String, Object> values) { super(values); }
        @Nullable public static FederatedAuthUserInfoRequestIssueDetails fromMap(@Nullable Map<String, Object> values) {
            return values == null ? null : new FederatedAuthUserInfoRequestIssueDetails(values);
        }
        public static Builder builder() { return new Builder(); }
        /**
         * Returns the federatedAuthUserInfoRequestIssueReason field.
         * @return the protocol field value
         */
        @Nullable public String federatedAuthUserInfoRequestIssueReason() {
            return (String) value("federatedAuthUserInfoRequestIssueReason");
        }
        public static final class Builder {
            private final Map<String, Object> values = new LinkedHashMap<>();
            /**
             * Sets the federatedAuthUserInfoRequestIssueReason field.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder federatedAuthUserInfoRequestIssueReason(@Nullable String value) {
                if (value == null) values.remove("federatedAuthUserInfoRequestIssueReason");
                else values.put("federatedAuthUserInfoRequestIssueReason", jsonValue(value));
                return this;
            }
            public FederatedAuthUserInfoRequestIssueDetails build() {
                if (!values.containsKey("federatedAuthUserInfoRequestIssueReason")) throw new IllegalStateException("Missing required CDP field: federatedAuthUserInfoRequestIssueReason");
                return new FederatedAuthUserInfoRequestIssueDetails(values);
            }
        }
    }
    /**
     * Represents the failure reason when a getUserInfo() call fails. Should be updated alongside FederatedAuthUserInfoRequestResult in third_party/blink/public/mojom/devtools/inspector_issue.mojom.
     */
    public static final class FederatedAuthUserInfoRequestIssueReason {
        private FederatedAuthUserInfoRequestIssueReason() {}
        public static final String NOTSAMEORIGIN = "NotSameOrigin";
        public static final String NOTIFRAME = "NotIframe";
        public static final String NOTPOTENTIALLYTRUSTWORTHY = "NotPotentiallyTrustworthy";
        public static final String NOAPIPERMISSION = "NoApiPermission";
        public static final String NOTSIGNEDINWITHIDP = "NotSignedInWithIdp";
        public static final String NOACCOUNTSHARINGPERMISSION = "NoAccountSharingPermission";
        public static final String INVALIDCONFIGORWELLKNOWN = "InvalidConfigOrWellKnown";
        public static final String INVALIDACCOUNTSRESPONSE = "InvalidAccountsResponse";
        public static final String NORETURNINGUSERFROMFETCHEDACCOUNTS = "NoReturningUserFromFetchedAccounts";
    }
    /**
     * This issue tracks client hints related issues. It&#x27;s used to deprecate old features, encourage the use of new ones, and provide general guidance.
     */
    public static final class ClientHintIssueDetails extends CdpObject {
        private ClientHintIssueDetails(Map<String, Object> values) { super(values); }
        @Nullable public static ClientHintIssueDetails fromMap(@Nullable Map<String, Object> values) {
            return values == null ? null : new ClientHintIssueDetails(values);
        }
        public static Builder builder() { return new Builder(); }
        /**
         * Returns the sourceCodeLocation field.
         * @return the protocol field value
         */
        @Nullable public Audits.SourceCodeLocation sourceCodeLocation() {
            return Audits.SourceCodeLocation.fromMap(objectMap(value("sourceCodeLocation")));
        }
        /**
         * Returns the clientHintIssueReason field.
         * @return the protocol field value
         */
        @Nullable public String clientHintIssueReason() {
            return (String) value("clientHintIssueReason");
        }
        public static final class Builder {
            private final Map<String, Object> values = new LinkedHashMap<>();
            /**
             * Sets the sourceCodeLocation field.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder sourceCodeLocation(@Nullable Audits.SourceCodeLocation value) {
                if (value == null) values.remove("sourceCodeLocation");
                else values.put("sourceCodeLocation", jsonValue(value));
                return this;
            }
            /**
             * Sets the clientHintIssueReason field.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder clientHintIssueReason(@Nullable String value) {
                if (value == null) values.remove("clientHintIssueReason");
                else values.put("clientHintIssueReason", jsonValue(value));
                return this;
            }
            public ClientHintIssueDetails build() {
                if (!values.containsKey("sourceCodeLocation")) throw new IllegalStateException("Missing required CDP field: sourceCodeLocation");
                if (!values.containsKey("clientHintIssueReason")) throw new IllegalStateException("Missing required CDP field: clientHintIssueReason");
                return new ClientHintIssueDetails(values);
            }
        }
    }
    /**
     */
    public static final class FailedRequestInfo extends CdpObject {
        private FailedRequestInfo(Map<String, Object> values) { super(values); }
        @Nullable public static FailedRequestInfo fromMap(@Nullable Map<String, Object> values) {
            return values == null ? null : new FailedRequestInfo(values);
        }
        public static Builder builder() { return new Builder(); }
        /**
         * The URL that failed to load.
         * @return the protocol field value
         */
        @Nullable public String url() {
            return (String) value("url");
        }
        /**
         * The failure message for the failed request.
         * @return the protocol field value
         */
        @Nullable public String failureMessage() {
            return (String) value("failureMessage");
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
             * The URL that failed to load.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder url(@Nullable String value) {
                if (value == null) values.remove("url");
                else values.put("url", jsonValue(value));
                return this;
            }
            /**
             * The failure message for the failed request.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder failureMessage(@Nullable String value) {
                if (value == null) values.remove("failureMessage");
                else values.put("failureMessage", jsonValue(value));
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
            public FailedRequestInfo build() {
                if (!values.containsKey("url")) throw new IllegalStateException("Missing required CDP field: url");
                if (!values.containsKey("failureMessage")) throw new IllegalStateException("Missing required CDP field: failureMessage");
                return new FailedRequestInfo(values);
            }
        }
    }
    /**
     * Wire values for PartitioningBlobURLInfo.
     */
    public static final class PartitioningBlobURLInfo {
        private PartitioningBlobURLInfo() {}
        public static final String BLOCKEDCROSSPARTITIONFETCHING = "BlockedCrossPartitionFetching";
        public static final String ENFORCENOOPENERFORNAVIGATION = "EnforceNoopenerForNavigation";
    }
    /**
     */
    public static final class PartitioningBlobURLIssueDetails extends CdpObject {
        private PartitioningBlobURLIssueDetails(Map<String, Object> values) { super(values); }
        @Nullable public static PartitioningBlobURLIssueDetails fromMap(@Nullable Map<String, Object> values) {
            return values == null ? null : new PartitioningBlobURLIssueDetails(values);
        }
        public static Builder builder() { return new Builder(); }
        /**
         * The BlobURL that failed to load.
         * @return the protocol field value
         */
        @Nullable public String url() {
            return (String) value("url");
        }
        /**
         * Additional information about the Partitioning Blob URL issue.
         * @return the protocol field value
         */
        @Nullable public String partitioningBlobURLInfo() {
            return (String) value("partitioningBlobURLInfo");
        }
        public static final class Builder {
            private final Map<String, Object> values = new LinkedHashMap<>();
            /**
             * The BlobURL that failed to load.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder url(@Nullable String value) {
                if (value == null) values.remove("url");
                else values.put("url", jsonValue(value));
                return this;
            }
            /**
             * Additional information about the Partitioning Blob URL issue.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder partitioningBlobURLInfo(@Nullable String value) {
                if (value == null) values.remove("partitioningBlobURLInfo");
                else values.put("partitioningBlobURLInfo", jsonValue(value));
                return this;
            }
            public PartitioningBlobURLIssueDetails build() {
                if (!values.containsKey("url")) throw new IllegalStateException("Missing required CDP field: url");
                if (!values.containsKey("partitioningBlobURLInfo")) throw new IllegalStateException("Missing required CDP field: partitioningBlobURLInfo");
                return new PartitioningBlobURLIssueDetails(values);
            }
        }
    }
    /**
     * Wire values for ElementAccessibilityIssueReason.
     */
    public static final class ElementAccessibilityIssueReason {
        private ElementAccessibilityIssueReason() {}
        public static final String DISALLOWEDSELECTCHILD = "DisallowedSelectChild";
        public static final String DISALLOWEDOPTGROUPCHILD = "DisallowedOptGroupChild";
        public static final String NONPHRASINGCONTENTOPTIONCHILD = "NonPhrasingContentOptionChild";
        public static final String INTERACTIVECONTENTOPTIONCHILD = "InteractiveContentOptionChild";
        public static final String INTERACTIVECONTENTLEGENDCHILD = "InteractiveContentLegendChild";
        public static final String INTERACTIVECONTENTSUMMARYDESCENDANT = "InteractiveContentSummaryDescendant";
    }
    /**
     * This issue warns about errors in the select or summary element content model.
     */
    public static final class ElementAccessibilityIssueDetails extends CdpObject {
        private ElementAccessibilityIssueDetails(Map<String, Object> values) { super(values); }
        @Nullable public static ElementAccessibilityIssueDetails fromMap(@Nullable Map<String, Object> values) {
            return values == null ? null : new ElementAccessibilityIssueDetails(values);
        }
        public static Builder builder() { return new Builder(); }
        /**
         * Returns the nodeId field.
         * @return the protocol field value
         */
        @Nullable public Long nodeId() {
            return numberAsLong(value("nodeId"));
        }
        /**
         * Returns the elementAccessibilityIssueReason field.
         * @return the protocol field value
         */
        @Nullable public String elementAccessibilityIssueReason() {
            return (String) value("elementAccessibilityIssueReason");
        }
        /**
         * Returns the hasDisallowedAttributes field.
         * @return the protocol field value
         */
        @Nullable public Boolean hasDisallowedAttributes() {
            return (Boolean) value("hasDisallowedAttributes");
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
            /**
             * Sets the elementAccessibilityIssueReason field.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder elementAccessibilityIssueReason(@Nullable String value) {
                if (value == null) values.remove("elementAccessibilityIssueReason");
                else values.put("elementAccessibilityIssueReason", jsonValue(value));
                return this;
            }
            /**
             * Sets the hasDisallowedAttributes field.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder hasDisallowedAttributes(@Nullable Boolean value) {
                if (value == null) values.remove("hasDisallowedAttributes");
                else values.put("hasDisallowedAttributes", jsonValue(value));
                return this;
            }
            public ElementAccessibilityIssueDetails build() {
                if (!values.containsKey("nodeId")) throw new IllegalStateException("Missing required CDP field: nodeId");
                if (!values.containsKey("elementAccessibilityIssueReason")) throw new IllegalStateException("Missing required CDP field: elementAccessibilityIssueReason");
                if (!values.containsKey("hasDisallowedAttributes")) throw new IllegalStateException("Missing required CDP field: hasDisallowedAttributes");
                return new ElementAccessibilityIssueDetails(values);
            }
        }
    }
    /**
     * Wire values for StyleSheetLoadingIssueReason.
     */
    public static final class StyleSheetLoadingIssueReason {
        private StyleSheetLoadingIssueReason() {}
        public static final String LATEIMPORTRULE = "LateImportRule";
        public static final String REQUESTFAILED = "RequestFailed";
    }
    /**
     * This issue warns when a referenced stylesheet couldn&#x27;t be loaded.
     */
    public static final class StylesheetLoadingIssueDetails extends CdpObject {
        private StylesheetLoadingIssueDetails(Map<String, Object> values) { super(values); }
        @Nullable public static StylesheetLoadingIssueDetails fromMap(@Nullable Map<String, Object> values) {
            return values == null ? null : new StylesheetLoadingIssueDetails(values);
        }
        public static Builder builder() { return new Builder(); }
        /**
         * Source code position that referenced the failing stylesheet.
         * @return the protocol field value
         */
        @Nullable public Audits.SourceCodeLocation sourceCodeLocation() {
            return Audits.SourceCodeLocation.fromMap(objectMap(value("sourceCodeLocation")));
        }
        /**
         * Reason why the stylesheet couldn&#x27;t be loaded.
         * @return the protocol field value
         */
        @Nullable public String styleSheetLoadingIssueReason() {
            return (String) value("styleSheetLoadingIssueReason");
        }
        /**
         * Contains additional info when the failure was due to a request.
         * @return the protocol field value
         */
        @Nullable public Audits.FailedRequestInfo failedRequestInfo() {
            return Audits.FailedRequestInfo.fromMap(objectMap(value("failedRequestInfo")));
        }
        public static final class Builder {
            private final Map<String, Object> values = new LinkedHashMap<>();
            /**
             * Source code position that referenced the failing stylesheet.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder sourceCodeLocation(@Nullable Audits.SourceCodeLocation value) {
                if (value == null) values.remove("sourceCodeLocation");
                else values.put("sourceCodeLocation", jsonValue(value));
                return this;
            }
            /**
             * Reason why the stylesheet couldn&#x27;t be loaded.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder styleSheetLoadingIssueReason(@Nullable String value) {
                if (value == null) values.remove("styleSheetLoadingIssueReason");
                else values.put("styleSheetLoadingIssueReason", jsonValue(value));
                return this;
            }
            /**
             * Contains additional info when the failure was due to a request.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder failedRequestInfo(@Nullable Audits.FailedRequestInfo value) {
                if (value == null) values.remove("failedRequestInfo");
                else values.put("failedRequestInfo", jsonValue(value));
                return this;
            }
            public StylesheetLoadingIssueDetails build() {
                if (!values.containsKey("sourceCodeLocation")) throw new IllegalStateException("Missing required CDP field: sourceCodeLocation");
                if (!values.containsKey("styleSheetLoadingIssueReason")) throw new IllegalStateException("Missing required CDP field: styleSheetLoadingIssueReason");
                return new StylesheetLoadingIssueDetails(values);
            }
        }
    }
    /**
     * Wire values for PropertyRuleIssueReason.
     */
    public static final class PropertyRuleIssueReason {
        private PropertyRuleIssueReason() {}
        public static final String INVALIDSYNTAX = "InvalidSyntax";
        public static final String INVALIDINITIALVALUE = "InvalidInitialValue";
        public static final String INVALIDINHERITS = "InvalidInherits";
        public static final String INVALIDNAME = "InvalidName";
    }
    /**
     * This issue warns about errors in property rules that lead to property registrations being ignored.
     */
    public static final class PropertyRuleIssueDetails extends CdpObject {
        private PropertyRuleIssueDetails(Map<String, Object> values) { super(values); }
        @Nullable public static PropertyRuleIssueDetails fromMap(@Nullable Map<String, Object> values) {
            return values == null ? null : new PropertyRuleIssueDetails(values);
        }
        public static Builder builder() { return new Builder(); }
        /**
         * Source code position of the property rule.
         * @return the protocol field value
         */
        @Nullable public Audits.SourceCodeLocation sourceCodeLocation() {
            return Audits.SourceCodeLocation.fromMap(objectMap(value("sourceCodeLocation")));
        }
        /**
         * Reason why the property rule was discarded.
         * @return the protocol field value
         */
        @Nullable public String propertyRuleIssueReason() {
            return (String) value("propertyRuleIssueReason");
        }
        /**
         * The value of the property rule property that failed to parse
         * @return the protocol field value
         */
        @Nullable public String propertyValue() {
            return (String) value("propertyValue");
        }
        public static final class Builder {
            private final Map<String, Object> values = new LinkedHashMap<>();
            /**
             * Source code position of the property rule.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder sourceCodeLocation(@Nullable Audits.SourceCodeLocation value) {
                if (value == null) values.remove("sourceCodeLocation");
                else values.put("sourceCodeLocation", jsonValue(value));
                return this;
            }
            /**
             * Reason why the property rule was discarded.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder propertyRuleIssueReason(@Nullable String value) {
                if (value == null) values.remove("propertyRuleIssueReason");
                else values.put("propertyRuleIssueReason", jsonValue(value));
                return this;
            }
            /**
             * The value of the property rule property that failed to parse
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder propertyValue(@Nullable String value) {
                if (value == null) values.remove("propertyValue");
                else values.put("propertyValue", jsonValue(value));
                return this;
            }
            public PropertyRuleIssueDetails build() {
                if (!values.containsKey("sourceCodeLocation")) throw new IllegalStateException("Missing required CDP field: sourceCodeLocation");
                if (!values.containsKey("propertyRuleIssueReason")) throw new IllegalStateException("Missing required CDP field: propertyRuleIssueReason");
                return new PropertyRuleIssueDetails(values);
            }
        }
    }
    /**
     * Wire values for UserReidentificationIssueType.
     */
    public static final class UserReidentificationIssueType {
        private UserReidentificationIssueType() {}
        public static final String BLOCKEDFRAMENAVIGATION = "BlockedFrameNavigation";
        public static final String BLOCKEDSUBRESOURCE = "BlockedSubresource";
        public static final String NOISEDCANVASREADBACK = "NoisedCanvasReadback";
    }
    /**
     * This issue warns about uses of APIs that may be considered misuse to re-identify users.
     */
    public static final class UserReidentificationIssueDetails extends CdpObject {
        private UserReidentificationIssueDetails(Map<String, Object> values) { super(values); }
        @Nullable public static UserReidentificationIssueDetails fromMap(@Nullable Map<String, Object> values) {
            return values == null ? null : new UserReidentificationIssueDetails(values);
        }
        public static Builder builder() { return new Builder(); }
        /**
         * Returns the type field.
         * @return the protocol field value
         */
        @Nullable public String type() {
            return (String) value("type");
        }
        /**
         * Applies to BlockedFrameNavigation and BlockedSubresource issue types.
         * @return the protocol field value
         */
        @Nullable public Audits.AffectedRequest request() {
            return Audits.AffectedRequest.fromMap(objectMap(value("request")));
        }
        /**
         * Applies to NoisedCanvasReadback issue type.
         * @return the protocol field value
         */
        @Nullable public Audits.SourceCodeLocation sourceCodeLocation() {
            return Audits.SourceCodeLocation.fromMap(objectMap(value("sourceCodeLocation")));
        }
        public static final class Builder {
            private final Map<String, Object> values = new LinkedHashMap<>();
            /**
             * Sets the type field.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder type(@Nullable String value) {
                if (value == null) values.remove("type");
                else values.put("type", jsonValue(value));
                return this;
            }
            /**
             * Applies to BlockedFrameNavigation and BlockedSubresource issue types.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder request(@Nullable Audits.AffectedRequest value) {
                if (value == null) values.remove("request");
                else values.put("request", jsonValue(value));
                return this;
            }
            /**
             * Applies to NoisedCanvasReadback issue type.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder sourceCodeLocation(@Nullable Audits.SourceCodeLocation value) {
                if (value == null) values.remove("sourceCodeLocation");
                else values.put("sourceCodeLocation", jsonValue(value));
                return this;
            }
            public UserReidentificationIssueDetails build() {
                if (!values.containsKey("type")) throw new IllegalStateException("Missing required CDP field: type");
                return new UserReidentificationIssueDetails(values);
            }
        }
    }
    /**
     * Wire values for PermissionElementIssueType.
     */
    public static final class PermissionElementIssueType {
        private PermissionElementIssueType() {}
        public static final String INVALIDTYPE = "InvalidType";
        public static final String FENCEDFRAMEDISALLOWED = "FencedFrameDisallowed";
        public static final String CSPFRAMEANCESTORSMISSING = "CspFrameAncestorsMissing";
        public static final String PERMISSIONSPOLICYBLOCKED = "PermissionsPolicyBlocked";
        public static final String PADDINGRIGHTUNSUPPORTED = "PaddingRightUnsupported";
        public static final String PADDINGBOTTOMUNSUPPORTED = "PaddingBottomUnsupported";
        public static final String INSETBOXSHADOWUNSUPPORTED = "InsetBoxShadowUnsupported";
        public static final String REQUESTINPROGRESS = "RequestInProgress";
        public static final String UNTRUSTEDEVENT = "UntrustedEvent";
        public static final String REGISTRATIONFAILED = "RegistrationFailed";
        public static final String TYPENOTSUPPORTED = "TypeNotSupported";
        public static final String INVALIDTYPEACTIVATION = "InvalidTypeActivation";
        public static final String SECURITYCHECKSFAILED = "SecurityChecksFailed";
        public static final String ACTIVATIONDISABLED = "ActivationDisabled";
        public static final String GEOLOCATIONDEPRECATED = "GeolocationDeprecated";
        public static final String INVALIDDISPLAYSTYLE = "InvalidDisplayStyle";
        public static final String NONOPAQUECOLOR = "NonOpaqueColor";
        public static final String LOWCONTRAST = "LowContrast";
        public static final String FONTSIZETOOSMALL = "FontSizeTooSmall";
        public static final String FONTSIZETOOLARGE = "FontSizeTooLarge";
        public static final String INVALIDSIZEVALUE = "InvalidSizeValue";
    }
    /**
     * This issue warns about improper usage of the &lt;permission&gt; element.
     */
    public static final class PermissionElementIssueDetails extends CdpObject {
        private PermissionElementIssueDetails(Map<String, Object> values) { super(values); }
        @Nullable public static PermissionElementIssueDetails fromMap(@Nullable Map<String, Object> values) {
            return values == null ? null : new PermissionElementIssueDetails(values);
        }
        public static Builder builder() { return new Builder(); }
        /**
         * Returns the issueType field.
         * @return the protocol field value
         */
        @Nullable public String issueType() {
            return (String) value("issueType");
        }
        /**
         * The value of the type attribute.
         * @return the protocol field value
         */
        @Nullable public String type() {
            return (String) value("type");
        }
        /**
         * The node ID of the &lt;permission&gt; element.
         * @return the protocol field value
         */
        @Nullable public Long nodeId() {
            return numberAsLong(value("nodeId"));
        }
        /**
         * True if the issue is a warning, false if it is an error.
         * @return the protocol field value
         */
        @Nullable public Boolean isWarning() {
            return (Boolean) value("isWarning");
        }
        /**
         * Fields for message construction: Used for messages that reference a specific permission name
         * @return the protocol field value
         */
        @Nullable public String permissionName() {
            return (String) value("permissionName");
        }
        /**
         * Used for messages about occlusion
         * @return the protocol field value
         */
        @Nullable public String occluderNodeInfo() {
            return (String) value("occluderNodeInfo");
        }
        /**
         * Used for messages about occluder&#x27;s parent
         * @return the protocol field value
         */
        @Nullable public String occluderParentNodeInfo() {
            return (String) value("occluderParentNodeInfo");
        }
        /**
         * Used for messages about activation disabled reason
         * @return the protocol field value
         */
        @Nullable public String disableReason() {
            return (String) value("disableReason");
        }
        public static final class Builder {
            private final Map<String, Object> values = new LinkedHashMap<>();
            /**
             * Sets the issueType field.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder issueType(@Nullable String value) {
                if (value == null) values.remove("issueType");
                else values.put("issueType", jsonValue(value));
                return this;
            }
            /**
             * The value of the type attribute.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder type(@Nullable String value) {
                if (value == null) values.remove("type");
                else values.put("type", jsonValue(value));
                return this;
            }
            /**
             * The node ID of the &lt;permission&gt; element.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder nodeId(@Nullable Long value) {
                if (value == null) values.remove("nodeId");
                else values.put("nodeId", jsonValue(value));
                return this;
            }
            /**
             * True if the issue is a warning, false if it is an error.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder isWarning(@Nullable Boolean value) {
                if (value == null) values.remove("isWarning");
                else values.put("isWarning", jsonValue(value));
                return this;
            }
            /**
             * Fields for message construction: Used for messages that reference a specific permission name
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder permissionName(@Nullable String value) {
                if (value == null) values.remove("permissionName");
                else values.put("permissionName", jsonValue(value));
                return this;
            }
            /**
             * Used for messages about occlusion
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder occluderNodeInfo(@Nullable String value) {
                if (value == null) values.remove("occluderNodeInfo");
                else values.put("occluderNodeInfo", jsonValue(value));
                return this;
            }
            /**
             * Used for messages about occluder&#x27;s parent
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder occluderParentNodeInfo(@Nullable String value) {
                if (value == null) values.remove("occluderParentNodeInfo");
                else values.put("occluderParentNodeInfo", jsonValue(value));
                return this;
            }
            /**
             * Used for messages about activation disabled reason
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder disableReason(@Nullable String value) {
                if (value == null) values.remove("disableReason");
                else values.put("disableReason", jsonValue(value));
                return this;
            }
            public PermissionElementIssueDetails build() {
                if (!values.containsKey("issueType")) throw new IllegalStateException("Missing required CDP field: issueType");
                return new PermissionElementIssueDetails(values);
            }
        }
    }
    /**
     * A unique identifier for the type of issue. Each type may use one of the optional fields in InspectorIssueDetails to convey more specific information about the kind of issue.
     */
    public static final class InspectorIssueCode {
        private InspectorIssueCode() {}
        public static final String COOKIEISSUE = "CookieIssue";
        public static final String MIXEDCONTENTISSUE = "MixedContentIssue";
        public static final String BLOCKEDBYRESPONSEISSUE = "BlockedByResponseIssue";
        public static final String HEAVYADISSUE = "HeavyAdIssue";
        public static final String CONTENTSECURITYPOLICYISSUE = "ContentSecurityPolicyIssue";
        public static final String SHAREDARRAYBUFFERISSUE = "SharedArrayBufferIssue";
        public static final String LOWTEXTCONTRASTISSUE = "LowTextContrastIssue";
        public static final String CORSISSUE = "CorsIssue";
        public static final String ATTRIBUTIONREPORTINGISSUE = "AttributionReportingIssue";
        public static final String QUIRKSMODEISSUE = "QuirksModeIssue";
        public static final String PARTITIONINGBLOBURLISSUE = "PartitioningBlobURLIssue";
        public static final String NAVIGATORUSERAGENTISSUE = "NavigatorUserAgentIssue";
        public static final String GENERICISSUE = "GenericIssue";
        public static final String DEPRECATIONISSUE = "DeprecationIssue";
        public static final String CLIENTHINTISSUE = "ClientHintIssue";
        public static final String FEDERATEDAUTHREQUESTISSUE = "FederatedAuthRequestIssue";
        public static final String BOUNCETRACKINGISSUE = "BounceTrackingIssue";
        public static final String COOKIEDEPRECATIONMETADATAISSUE = "CookieDeprecationMetadataIssue";
        public static final String STYLESHEETLOADINGISSUE = "StylesheetLoadingIssue";
        public static final String FEDERATEDAUTHUSERINFOREQUESTISSUE = "FederatedAuthUserInfoRequestIssue";
        public static final String PROPERTYRULEISSUE = "PropertyRuleIssue";
        public static final String SHAREDDICTIONARYISSUE = "SharedDictionaryIssue";
        public static final String ELEMENTACCESSIBILITYISSUE = "ElementAccessibilityIssue";
        public static final String SRIMESSAGESIGNATUREISSUE = "SRIMessageSignatureIssue";
        public static final String UNENCODEDDIGESTISSUE = "UnencodedDigestIssue";
        public static final String CONNECTIONALLOWLISTISSUE = "ConnectionAllowlistIssue";
        public static final String USERREIDENTIFICATIONISSUE = "UserReidentificationIssue";
        public static final String PERMISSIONELEMENTISSUE = "PermissionElementIssue";
    }
    /**
     * This struct holds a list of optional fields with additional information specific to the kind of issue. When adding a new issue code, please also add a new optional field to this type.
     */
    public static final class InspectorIssueDetails extends CdpObject {
        private InspectorIssueDetails(Map<String, Object> values) { super(values); }
        @Nullable public static InspectorIssueDetails fromMap(@Nullable Map<String, Object> values) {
            return values == null ? null : new InspectorIssueDetails(values);
        }
        public static Builder builder() { return new Builder(); }
        /**
         * Returns the cookieIssueDetails field.
         * @return the protocol field value
         */
        @Nullable public Audits.CookieIssueDetails cookieIssueDetails() {
            return Audits.CookieIssueDetails.fromMap(objectMap(value("cookieIssueDetails")));
        }
        /**
         * Returns the mixedContentIssueDetails field.
         * @return the protocol field value
         */
        @Nullable public Audits.MixedContentIssueDetails mixedContentIssueDetails() {
            return Audits.MixedContentIssueDetails.fromMap(objectMap(value("mixedContentIssueDetails")));
        }
        /**
         * Returns the blockedByResponseIssueDetails field.
         * @return the protocol field value
         */
        @Nullable public Audits.BlockedByResponseIssueDetails blockedByResponseIssueDetails() {
            return Audits.BlockedByResponseIssueDetails.fromMap(objectMap(value("blockedByResponseIssueDetails")));
        }
        /**
         * Returns the heavyAdIssueDetails field.
         * @return the protocol field value
         */
        @Nullable public Audits.HeavyAdIssueDetails heavyAdIssueDetails() {
            return Audits.HeavyAdIssueDetails.fromMap(objectMap(value("heavyAdIssueDetails")));
        }
        /**
         * Returns the contentSecurityPolicyIssueDetails field.
         * @return the protocol field value
         */
        @Nullable public Audits.ContentSecurityPolicyIssueDetails contentSecurityPolicyIssueDetails() {
            return Audits.ContentSecurityPolicyIssueDetails.fromMap(objectMap(value("contentSecurityPolicyIssueDetails")));
        }
        /**
         * Returns the sharedArrayBufferIssueDetails field.
         * @return the protocol field value
         */
        @Nullable public Audits.SharedArrayBufferIssueDetails sharedArrayBufferIssueDetails() {
            return Audits.SharedArrayBufferIssueDetails.fromMap(objectMap(value("sharedArrayBufferIssueDetails")));
        }
        /**
         * Returns the lowTextContrastIssueDetails field.
         * @return the protocol field value
         */
        @Nullable public Audits.LowTextContrastIssueDetails lowTextContrastIssueDetails() {
            return Audits.LowTextContrastIssueDetails.fromMap(objectMap(value("lowTextContrastIssueDetails")));
        }
        /**
         * Returns the corsIssueDetails field.
         * @return the protocol field value
         */
        @Nullable public Audits.CorsIssueDetails corsIssueDetails() {
            return Audits.CorsIssueDetails.fromMap(objectMap(value("corsIssueDetails")));
        }
        /**
         * Returns the attributionReportingIssueDetails field.
         * @return the protocol field value
         */
        @Nullable public Audits.AttributionReportingIssueDetails attributionReportingIssueDetails() {
            return Audits.AttributionReportingIssueDetails.fromMap(objectMap(value("attributionReportingIssueDetails")));
        }
        /**
         * Returns the quirksModeIssueDetails field.
         * @return the protocol field value
         */
        @Nullable public Audits.QuirksModeIssueDetails quirksModeIssueDetails() {
            return Audits.QuirksModeIssueDetails.fromMap(objectMap(value("quirksModeIssueDetails")));
        }
        /**
         * Returns the partitioningBlobURLIssueDetails field.
         * @return the protocol field value
         */
        @Nullable public Audits.PartitioningBlobURLIssueDetails partitioningBlobURLIssueDetails() {
            return Audits.PartitioningBlobURLIssueDetails.fromMap(objectMap(value("partitioningBlobURLIssueDetails")));
        }
        /**
         * Returns the navigatorUserAgentIssueDetails field.
         * @return the protocol field value
         * @deprecated Deprecated by the Chromium DevTools Protocol.
         */
        @Deprecated
        @Nullable public Audits.NavigatorUserAgentIssueDetails navigatorUserAgentIssueDetails() {
            return Audits.NavigatorUserAgentIssueDetails.fromMap(objectMap(value("navigatorUserAgentIssueDetails")));
        }
        /**
         * Returns the genericIssueDetails field.
         * @return the protocol field value
         */
        @Nullable public Audits.GenericIssueDetails genericIssueDetails() {
            return Audits.GenericIssueDetails.fromMap(objectMap(value("genericIssueDetails")));
        }
        /**
         * Returns the deprecationIssueDetails field.
         * @return the protocol field value
         */
        @Nullable public Audits.DeprecationIssueDetails deprecationIssueDetails() {
            return Audits.DeprecationIssueDetails.fromMap(objectMap(value("deprecationIssueDetails")));
        }
        /**
         * Returns the clientHintIssueDetails field.
         * @return the protocol field value
         */
        @Nullable public Audits.ClientHintIssueDetails clientHintIssueDetails() {
            return Audits.ClientHintIssueDetails.fromMap(objectMap(value("clientHintIssueDetails")));
        }
        /**
         * Returns the federatedAuthRequestIssueDetails field.
         * @return the protocol field value
         */
        @Nullable public Audits.FederatedAuthRequestIssueDetails federatedAuthRequestIssueDetails() {
            return Audits.FederatedAuthRequestIssueDetails.fromMap(objectMap(value("federatedAuthRequestIssueDetails")));
        }
        /**
         * Returns the bounceTrackingIssueDetails field.
         * @return the protocol field value
         */
        @Nullable public Audits.BounceTrackingIssueDetails bounceTrackingIssueDetails() {
            return Audits.BounceTrackingIssueDetails.fromMap(objectMap(value("bounceTrackingIssueDetails")));
        }
        /**
         * Returns the cookieDeprecationMetadataIssueDetails field.
         * @return the protocol field value
         */
        @Nullable public Audits.CookieDeprecationMetadataIssueDetails cookieDeprecationMetadataIssueDetails() {
            return Audits.CookieDeprecationMetadataIssueDetails.fromMap(objectMap(value("cookieDeprecationMetadataIssueDetails")));
        }
        /**
         * Returns the stylesheetLoadingIssueDetails field.
         * @return the protocol field value
         */
        @Nullable public Audits.StylesheetLoadingIssueDetails stylesheetLoadingIssueDetails() {
            return Audits.StylesheetLoadingIssueDetails.fromMap(objectMap(value("stylesheetLoadingIssueDetails")));
        }
        /**
         * Returns the propertyRuleIssueDetails field.
         * @return the protocol field value
         */
        @Nullable public Audits.PropertyRuleIssueDetails propertyRuleIssueDetails() {
            return Audits.PropertyRuleIssueDetails.fromMap(objectMap(value("propertyRuleIssueDetails")));
        }
        /**
         * Returns the federatedAuthUserInfoRequestIssueDetails field.
         * @return the protocol field value
         */
        @Nullable public Audits.FederatedAuthUserInfoRequestIssueDetails federatedAuthUserInfoRequestIssueDetails() {
            return Audits.FederatedAuthUserInfoRequestIssueDetails.fromMap(objectMap(value("federatedAuthUserInfoRequestIssueDetails")));
        }
        /**
         * Returns the sharedDictionaryIssueDetails field.
         * @return the protocol field value
         */
        @Nullable public Audits.SharedDictionaryIssueDetails sharedDictionaryIssueDetails() {
            return Audits.SharedDictionaryIssueDetails.fromMap(objectMap(value("sharedDictionaryIssueDetails")));
        }
        /**
         * Returns the elementAccessibilityIssueDetails field.
         * @return the protocol field value
         */
        @Nullable public Audits.ElementAccessibilityIssueDetails elementAccessibilityIssueDetails() {
            return Audits.ElementAccessibilityIssueDetails.fromMap(objectMap(value("elementAccessibilityIssueDetails")));
        }
        /**
         * Returns the sriMessageSignatureIssueDetails field.
         * @return the protocol field value
         */
        @Nullable public Audits.SRIMessageSignatureIssueDetails sriMessageSignatureIssueDetails() {
            return Audits.SRIMessageSignatureIssueDetails.fromMap(objectMap(value("sriMessageSignatureIssueDetails")));
        }
        /**
         * Returns the unencodedDigestIssueDetails field.
         * @return the protocol field value
         */
        @Nullable public Audits.UnencodedDigestIssueDetails unencodedDigestIssueDetails() {
            return Audits.UnencodedDigestIssueDetails.fromMap(objectMap(value("unencodedDigestIssueDetails")));
        }
        /**
         * Returns the connectionAllowlistIssueDetails field.
         * @return the protocol field value
         */
        @Nullable public Audits.ConnectionAllowlistIssueDetails connectionAllowlistIssueDetails() {
            return Audits.ConnectionAllowlistIssueDetails.fromMap(objectMap(value("connectionAllowlistIssueDetails")));
        }
        /**
         * Returns the userReidentificationIssueDetails field.
         * @return the protocol field value
         */
        @Nullable public Audits.UserReidentificationIssueDetails userReidentificationIssueDetails() {
            return Audits.UserReidentificationIssueDetails.fromMap(objectMap(value("userReidentificationIssueDetails")));
        }
        /**
         * Returns the permissionElementIssueDetails field.
         * @return the protocol field value
         */
        @Nullable public Audits.PermissionElementIssueDetails permissionElementIssueDetails() {
            return Audits.PermissionElementIssueDetails.fromMap(objectMap(value("permissionElementIssueDetails")));
        }
        public static final class Builder {
            private final Map<String, Object> values = new LinkedHashMap<>();
            /**
             * Sets the cookieIssueDetails field.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder cookieIssueDetails(@Nullable Audits.CookieIssueDetails value) {
                if (value == null) values.remove("cookieIssueDetails");
                else values.put("cookieIssueDetails", jsonValue(value));
                return this;
            }
            /**
             * Sets the mixedContentIssueDetails field.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder mixedContentIssueDetails(@Nullable Audits.MixedContentIssueDetails value) {
                if (value == null) values.remove("mixedContentIssueDetails");
                else values.put("mixedContentIssueDetails", jsonValue(value));
                return this;
            }
            /**
             * Sets the blockedByResponseIssueDetails field.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder blockedByResponseIssueDetails(@Nullable Audits.BlockedByResponseIssueDetails value) {
                if (value == null) values.remove("blockedByResponseIssueDetails");
                else values.put("blockedByResponseIssueDetails", jsonValue(value));
                return this;
            }
            /**
             * Sets the heavyAdIssueDetails field.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder heavyAdIssueDetails(@Nullable Audits.HeavyAdIssueDetails value) {
                if (value == null) values.remove("heavyAdIssueDetails");
                else values.put("heavyAdIssueDetails", jsonValue(value));
                return this;
            }
            /**
             * Sets the contentSecurityPolicyIssueDetails field.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder contentSecurityPolicyIssueDetails(@Nullable Audits.ContentSecurityPolicyIssueDetails value) {
                if (value == null) values.remove("contentSecurityPolicyIssueDetails");
                else values.put("contentSecurityPolicyIssueDetails", jsonValue(value));
                return this;
            }
            /**
             * Sets the sharedArrayBufferIssueDetails field.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder sharedArrayBufferIssueDetails(@Nullable Audits.SharedArrayBufferIssueDetails value) {
                if (value == null) values.remove("sharedArrayBufferIssueDetails");
                else values.put("sharedArrayBufferIssueDetails", jsonValue(value));
                return this;
            }
            /**
             * Sets the lowTextContrastIssueDetails field.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder lowTextContrastIssueDetails(@Nullable Audits.LowTextContrastIssueDetails value) {
                if (value == null) values.remove("lowTextContrastIssueDetails");
                else values.put("lowTextContrastIssueDetails", jsonValue(value));
                return this;
            }
            /**
             * Sets the corsIssueDetails field.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder corsIssueDetails(@Nullable Audits.CorsIssueDetails value) {
                if (value == null) values.remove("corsIssueDetails");
                else values.put("corsIssueDetails", jsonValue(value));
                return this;
            }
            /**
             * Sets the attributionReportingIssueDetails field.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder attributionReportingIssueDetails(@Nullable Audits.AttributionReportingIssueDetails value) {
                if (value == null) values.remove("attributionReportingIssueDetails");
                else values.put("attributionReportingIssueDetails", jsonValue(value));
                return this;
            }
            /**
             * Sets the quirksModeIssueDetails field.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder quirksModeIssueDetails(@Nullable Audits.QuirksModeIssueDetails value) {
                if (value == null) values.remove("quirksModeIssueDetails");
                else values.put("quirksModeIssueDetails", jsonValue(value));
                return this;
            }
            /**
             * Sets the partitioningBlobURLIssueDetails field.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder partitioningBlobURLIssueDetails(@Nullable Audits.PartitioningBlobURLIssueDetails value) {
                if (value == null) values.remove("partitioningBlobURLIssueDetails");
                else values.put("partitioningBlobURLIssueDetails", jsonValue(value));
                return this;
            }
            /**
             * Sets the navigatorUserAgentIssueDetails field.
             * @param value field value; null removes an optional value
             * @return this builder
             * @deprecated Deprecated by the Chromium DevTools Protocol.
             */
            @Deprecated
            public Builder navigatorUserAgentIssueDetails(@Nullable Audits.NavigatorUserAgentIssueDetails value) {
                if (value == null) values.remove("navigatorUserAgentIssueDetails");
                else values.put("navigatorUserAgentIssueDetails", jsonValue(value));
                return this;
            }
            /**
             * Sets the genericIssueDetails field.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder genericIssueDetails(@Nullable Audits.GenericIssueDetails value) {
                if (value == null) values.remove("genericIssueDetails");
                else values.put("genericIssueDetails", jsonValue(value));
                return this;
            }
            /**
             * Sets the deprecationIssueDetails field.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder deprecationIssueDetails(@Nullable Audits.DeprecationIssueDetails value) {
                if (value == null) values.remove("deprecationIssueDetails");
                else values.put("deprecationIssueDetails", jsonValue(value));
                return this;
            }
            /**
             * Sets the clientHintIssueDetails field.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder clientHintIssueDetails(@Nullable Audits.ClientHintIssueDetails value) {
                if (value == null) values.remove("clientHintIssueDetails");
                else values.put("clientHintIssueDetails", jsonValue(value));
                return this;
            }
            /**
             * Sets the federatedAuthRequestIssueDetails field.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder federatedAuthRequestIssueDetails(@Nullable Audits.FederatedAuthRequestIssueDetails value) {
                if (value == null) values.remove("federatedAuthRequestIssueDetails");
                else values.put("federatedAuthRequestIssueDetails", jsonValue(value));
                return this;
            }
            /**
             * Sets the bounceTrackingIssueDetails field.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder bounceTrackingIssueDetails(@Nullable Audits.BounceTrackingIssueDetails value) {
                if (value == null) values.remove("bounceTrackingIssueDetails");
                else values.put("bounceTrackingIssueDetails", jsonValue(value));
                return this;
            }
            /**
             * Sets the cookieDeprecationMetadataIssueDetails field.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder cookieDeprecationMetadataIssueDetails(@Nullable Audits.CookieDeprecationMetadataIssueDetails value) {
                if (value == null) values.remove("cookieDeprecationMetadataIssueDetails");
                else values.put("cookieDeprecationMetadataIssueDetails", jsonValue(value));
                return this;
            }
            /**
             * Sets the stylesheetLoadingIssueDetails field.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder stylesheetLoadingIssueDetails(@Nullable Audits.StylesheetLoadingIssueDetails value) {
                if (value == null) values.remove("stylesheetLoadingIssueDetails");
                else values.put("stylesheetLoadingIssueDetails", jsonValue(value));
                return this;
            }
            /**
             * Sets the propertyRuleIssueDetails field.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder propertyRuleIssueDetails(@Nullable Audits.PropertyRuleIssueDetails value) {
                if (value == null) values.remove("propertyRuleIssueDetails");
                else values.put("propertyRuleIssueDetails", jsonValue(value));
                return this;
            }
            /**
             * Sets the federatedAuthUserInfoRequestIssueDetails field.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder federatedAuthUserInfoRequestIssueDetails(@Nullable Audits.FederatedAuthUserInfoRequestIssueDetails value) {
                if (value == null) values.remove("federatedAuthUserInfoRequestIssueDetails");
                else values.put("federatedAuthUserInfoRequestIssueDetails", jsonValue(value));
                return this;
            }
            /**
             * Sets the sharedDictionaryIssueDetails field.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder sharedDictionaryIssueDetails(@Nullable Audits.SharedDictionaryIssueDetails value) {
                if (value == null) values.remove("sharedDictionaryIssueDetails");
                else values.put("sharedDictionaryIssueDetails", jsonValue(value));
                return this;
            }
            /**
             * Sets the elementAccessibilityIssueDetails field.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder elementAccessibilityIssueDetails(@Nullable Audits.ElementAccessibilityIssueDetails value) {
                if (value == null) values.remove("elementAccessibilityIssueDetails");
                else values.put("elementAccessibilityIssueDetails", jsonValue(value));
                return this;
            }
            /**
             * Sets the sriMessageSignatureIssueDetails field.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder sriMessageSignatureIssueDetails(@Nullable Audits.SRIMessageSignatureIssueDetails value) {
                if (value == null) values.remove("sriMessageSignatureIssueDetails");
                else values.put("sriMessageSignatureIssueDetails", jsonValue(value));
                return this;
            }
            /**
             * Sets the unencodedDigestIssueDetails field.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder unencodedDigestIssueDetails(@Nullable Audits.UnencodedDigestIssueDetails value) {
                if (value == null) values.remove("unencodedDigestIssueDetails");
                else values.put("unencodedDigestIssueDetails", jsonValue(value));
                return this;
            }
            /**
             * Sets the connectionAllowlistIssueDetails field.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder connectionAllowlistIssueDetails(@Nullable Audits.ConnectionAllowlistIssueDetails value) {
                if (value == null) values.remove("connectionAllowlistIssueDetails");
                else values.put("connectionAllowlistIssueDetails", jsonValue(value));
                return this;
            }
            /**
             * Sets the userReidentificationIssueDetails field.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder userReidentificationIssueDetails(@Nullable Audits.UserReidentificationIssueDetails value) {
                if (value == null) values.remove("userReidentificationIssueDetails");
                else values.put("userReidentificationIssueDetails", jsonValue(value));
                return this;
            }
            /**
             * Sets the permissionElementIssueDetails field.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder permissionElementIssueDetails(@Nullable Audits.PermissionElementIssueDetails value) {
                if (value == null) values.remove("permissionElementIssueDetails");
                else values.put("permissionElementIssueDetails", jsonValue(value));
                return this;
            }
            public InspectorIssueDetails build() {
                return new InspectorIssueDetails(values);
            }
        }
    }
    /**
     * An inspector issue reported from the back-end.
     */
    public static final class InspectorIssue extends CdpObject {
        private InspectorIssue(Map<String, Object> values) { super(values); }
        @Nullable public static InspectorIssue fromMap(@Nullable Map<String, Object> values) {
            return values == null ? null : new InspectorIssue(values);
        }
        public static Builder builder() { return new Builder(); }
        /**
         * Returns the code field.
         * @return the protocol field value
         */
        @Nullable public String code() {
            return (String) value("code");
        }
        /**
         * Returns the details field.
         * @return the protocol field value
         */
        @Nullable public Audits.InspectorIssueDetails details() {
            return Audits.InspectorIssueDetails.fromMap(objectMap(value("details")));
        }
        /**
         * A unique id for this issue. May be omitted if no other entity (e.g. exception, CDP message, etc.) is referencing this issue.
         * @return the protocol field value
         */
        @Nullable public String issueId() {
            return (String) value("issueId");
        }
        public static final class Builder {
            private final Map<String, Object> values = new LinkedHashMap<>();
            /**
             * Sets the code field.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder code(@Nullable String value) {
                if (value == null) values.remove("code");
                else values.put("code", jsonValue(value));
                return this;
            }
            /**
             * Sets the details field.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder details(@Nullable Audits.InspectorIssueDetails value) {
                if (value == null) values.remove("details");
                else values.put("details", jsonValue(value));
                return this;
            }
            /**
             * A unique id for this issue. May be omitted if no other entity (e.g. exception, CDP message, etc.) is referencing this issue.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder issueId(@Nullable String value) {
                if (value == null) values.remove("issueId");
                else values.put("issueId", jsonValue(value));
                return this;
            }
            public InspectorIssue build() {
                if (!values.containsKey("code")) throw new IllegalStateException("Missing required CDP field: code");
                if (!values.containsKey("details")) throw new IllegalStateException("Missing required CDP field: details");
                return new InspectorIssue(values);
            }
        }
    }
    /**
     * Returns the response body and size if it were re-encoded with the specified settings. Only applies to images.
     */
    public static final class GetEncodedResponseParams extends CdpObject {
        private GetEncodedResponseParams(Map<String, Object> values) { super(values); }
        @Nullable public static GetEncodedResponseParams fromMap(@Nullable Map<String, Object> values) {
            return values == null ? null : new GetEncodedResponseParams(values);
        }
        public static Builder builder() { return new Builder(); }
        /**
         * Identifier of the network request to get content for.
         * @return the protocol field value
         */
        @Nullable public String requestId() {
            return (String) value("requestId");
        }
        /**
         * The encoding to use.
         * @return the protocol field value
         */
        @Nullable public String encoding() {
            return (String) value("encoding");
        }
        /**
         * The encoding to use.
         */
        public static final class EncodingValues {
            private EncodingValues() {}
            public static final String WEBP = "webp";
            public static final String JPEG = "jpeg";
            public static final String PNG = "png";
        }
        /**
         * The quality of the encoding (0-1). (defaults to 1)
         * @return the protocol field value
         */
        @Nullable public Double quality() {
            return numberAsDouble(value("quality"));
        }
        /**
         * Whether to only return the size information (defaults to false).
         * @return the protocol field value
         */
        @Nullable public Boolean sizeOnly() {
            return (Boolean) value("sizeOnly");
        }
        public static final class Builder {
            private final Map<String, Object> values = new LinkedHashMap<>();
            /**
             * Identifier of the network request to get content for.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder requestId(@Nullable String value) {
                if (value == null) values.remove("requestId");
                else values.put("requestId", jsonValue(value));
                return this;
            }
            /**
             * The encoding to use.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder encoding(@Nullable String value) {
                if (value == null) values.remove("encoding");
                else values.put("encoding", jsonValue(value));
                return this;
            }
            /**
             * The quality of the encoding (0-1). (defaults to 1)
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder quality(@Nullable Double value) {
                if (value == null) values.remove("quality");
                else values.put("quality", jsonValue(value));
                return this;
            }
            /**
             * Whether to only return the size information (defaults to false).
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder sizeOnly(@Nullable Boolean value) {
                if (value == null) values.remove("sizeOnly");
                else values.put("sizeOnly", jsonValue(value));
                return this;
            }
            public GetEncodedResponseParams build() {
                if (!values.containsKey("requestId")) throw new IllegalStateException("Missing required CDP field: requestId");
                if (!values.containsKey("encoding")) throw new IllegalStateException("Missing required CDP field: encoding");
                return new GetEncodedResponseParams(values);
            }
        }
    }
    /**
     * Returns the response body and size if it were re-encoded with the specified settings. Only applies to images.
     */
    public static final class GetEncodedResponseResult extends CdpObject {
        private GetEncodedResponseResult(Map<String, Object> values) { super(values); }
        @Nullable public static GetEncodedResponseResult fromMap(@Nullable Map<String, Object> values) {
            return values == null ? null : new GetEncodedResponseResult(values);
        }
        public static Builder builder() { return new Builder(); }
        /**
         * The encoded body as a base64 string. Omitted if sizeOnly is true. (Encoded as a base64 string when passed over JSON)
         * @return the protocol field value
         */
        @Nullable public String body() {
            return (String) value("body");
        }
        /**
         * Size before re-encoding.
         * @return the protocol field value
         */
        @Nullable public Long originalSize() {
            return numberAsLong(value("originalSize"));
        }
        /**
         * Size after re-encoding.
         * @return the protocol field value
         */
        @Nullable public Long encodedSize() {
            return numberAsLong(value("encodedSize"));
        }
        public static final class Builder {
            private final Map<String, Object> values = new LinkedHashMap<>();
            /**
             * The encoded body as a base64 string. Omitted if sizeOnly is true. (Encoded as a base64 string when passed over JSON)
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder body(@Nullable String value) {
                if (value == null) values.remove("body");
                else values.put("body", jsonValue(value));
                return this;
            }
            /**
             * Size before re-encoding.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder originalSize(@Nullable Long value) {
                if (value == null) values.remove("originalSize");
                else values.put("originalSize", jsonValue(value));
                return this;
            }
            /**
             * Size after re-encoding.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder encodedSize(@Nullable Long value) {
                if (value == null) values.remove("encodedSize");
                else values.put("encodedSize", jsonValue(value));
                return this;
            }
            public GetEncodedResponseResult build() {
                if (!values.containsKey("originalSize")) throw new IllegalStateException("Missing required CDP field: originalSize");
                if (!values.containsKey("encodedSize")) throw new IllegalStateException("Missing required CDP field: encodedSize");
                return new GetEncodedResponseResult(values);
            }
        }
    }
    /**
     * Disables issues domain, prevents further issues from being reported to the client.
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
     * Disables issues domain, prevents further issues from being reported to the client.
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
     * Enables issues domain, sends the issues collected so far to the client by means of the {@code issueAdded} event.
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
     * Enables issues domain, sends the issues collected so far to the client by means of the {@code issueAdded} event.
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
     * Runs the contrast check for the target page. Found issues are reported using Audits.issueAdded event.
     */
    public static final class CheckContrastParams extends CdpObject {
        private CheckContrastParams(Map<String, Object> values) { super(values); }
        @Nullable public static CheckContrastParams fromMap(@Nullable Map<String, Object> values) {
            return values == null ? null : new CheckContrastParams(values);
        }
        public static Builder builder() { return new Builder(); }
        /**
         * Whether to report WCAG AAA level issues. Default is false.
         * @return the protocol field value
         */
        @Nullable public Boolean reportAAA() {
            return (Boolean) value("reportAAA");
        }
        public static final class Builder {
            private final Map<String, Object> values = new LinkedHashMap<>();
            /**
             * Whether to report WCAG AAA level issues. Default is false.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder reportAAA(@Nullable Boolean value) {
                if (value == null) values.remove("reportAAA");
                else values.put("reportAAA", jsonValue(value));
                return this;
            }
            public CheckContrastParams build() {
                return new CheckContrastParams(values);
            }
        }
    }
    /**
     * Runs the contrast check for the target page. Found issues are reported using Audits.issueAdded event.
     */
    public static final class CheckContrastResult extends CdpObject {
        private CheckContrastResult(Map<String, Object> values) { super(values); }
        @Nullable public static CheckContrastResult fromMap(@Nullable Map<String, Object> values) {
            return values == null ? null : new CheckContrastResult(values);
        }
        public static Builder builder() { return new Builder(); }
        public static final class Builder {
            private final Map<String, Object> values = new LinkedHashMap<>();
            public CheckContrastResult build() {
                return new CheckContrastResult(values);
            }
        }
    }
    /**
     * Runs the form issues check for the target page. Found issues are reported using Audits.issueAdded event.
     */
    public static final class CheckFormsIssuesParams extends CdpObject {
        private CheckFormsIssuesParams(Map<String, Object> values) { super(values); }
        @Nullable public static CheckFormsIssuesParams fromMap(@Nullable Map<String, Object> values) {
            return values == null ? null : new CheckFormsIssuesParams(values);
        }
        public static Builder builder() { return new Builder(); }
        public static final class Builder {
            private final Map<String, Object> values = new LinkedHashMap<>();
            public CheckFormsIssuesParams build() {
                return new CheckFormsIssuesParams(values);
            }
        }
    }
    /**
     * Runs the form issues check for the target page. Found issues are reported using Audits.issueAdded event.
     */
    public static final class CheckFormsIssuesResult extends CdpObject {
        private CheckFormsIssuesResult(Map<String, Object> values) { super(values); }
        @Nullable public static CheckFormsIssuesResult fromMap(@Nullable Map<String, Object> values) {
            return values == null ? null : new CheckFormsIssuesResult(values);
        }
        public static Builder builder() { return new Builder(); }
        /**
         * Returns the formIssues field.
         * @return the protocol field value
         */
        @Nullable public java.util.List<Audits.GenericIssueDetails> formIssues() {
            return list(value("formIssues"), element0 -> Audits.GenericIssueDetails.fromMap(objectMap(element0)));
        }
        public static final class Builder {
            private final Map<String, Object> values = new LinkedHashMap<>();
            /**
             * Sets the formIssues field.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder formIssues(@Nullable java.util.List<Audits.GenericIssueDetails> value) {
                if (value == null) values.remove("formIssues");
                else values.put("formIssues", jsonValue(value));
                return this;
            }
            public CheckFormsIssuesResult build() {
                if (!values.containsKey("formIssues")) throw new IllegalStateException("Missing required CDP field: formIssues");
                return new CheckFormsIssuesResult(values);
            }
        }
    }
    /**
     * Payload of the Audits.issueAdded event.
     */
    public static final class IssueAddedEvent extends CdpObject {
        private IssueAddedEvent(Map<String, Object> values) { super(values); }
        @Nullable public static IssueAddedEvent fromMap(@Nullable Map<String, Object> values) {
            return values == null ? null : new IssueAddedEvent(values);
        }
        public static Builder builder() { return new Builder(); }
        /**
         * Returns the issue field.
         * @return the protocol field value
         */
        @Nullable public Audits.InspectorIssue issue() {
            return Audits.InspectorIssue.fromMap(objectMap(value("issue")));
        }
        public static final class Builder {
            private final Map<String, Object> values = new LinkedHashMap<>();
            /**
             * Sets the issue field.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder issue(@Nullable Audits.InspectorIssue value) {
                if (value == null) values.remove("issue");
                else values.put("issue", jsonValue(value));
                return this;
            }
            public IssueAddedEvent build() {
                if (!values.containsKey("issue")) throw new IllegalStateException("Missing required CDP field: issue");
                return new IssueAddedEvent(values);
            }
        }
    }
    public static final class Client {
        private final CdpClient client;
        public Client(CdpClient client) { this.client = client; }
        /**
         * Returns the response body and size if it were re-encoded with the specified settings. Only applies to images.
         * @param params command parameters
         * @return a stage completing with the command result
         */
        public CompletionStage<GetEncodedResponseResult> getEncodedResponse(GetEncodedResponseParams params) {
            return client.call("Audits.getEncodedResponse", params, GetEncodedResponseResult::fromMap);
        }
        /**
         * Disables issues domain, prevents further issues from being reported to the client.
         * @return a stage completing with the command result
         */
        public CompletionStage<DisableResult> disable() {
            return client.call("Audits.disable", null, DisableResult::fromMap);
        }
        /**
         * Enables issues domain, sends the issues collected so far to the client by means of the {@code issueAdded} event.
         * @return a stage completing with the command result
         */
        public CompletionStage<EnableResult> enable() {
            return client.call("Audits.enable", null, EnableResult::fromMap);
        }
        /**
         * Runs the contrast check for the target page. Found issues are reported using Audits.issueAdded event.
         * @param params command parameters
         * @return a stage completing with the command result
         */
        public CompletionStage<CheckContrastResult> checkContrast(CheckContrastParams params) {
            return client.call("Audits.checkContrast", params, CheckContrastResult::fromMap);
        }
        /**
         * Runs the form issues check for the target page. Found issues are reported using Audits.issueAdded event.
         * @return a stage completing with the command result
         */
        public CompletionStage<CheckFormsIssuesResult> checkFormsIssues() {
            return client.call("Audits.checkFormsIssues", null, CheckFormsIssuesResult::fromMap);
        }
        /**
         * Subscribes to Audits.issueAdded.
         * @param handler event callback
         * @return a removable subscription
         */
        public CdpSubscription onIssueAdded(Consumer<IssueAddedEvent> handler) {
            return client.on("Audits.issueAdded", IssueAddedEvent::fromMap, handler);
        }
    }
}
