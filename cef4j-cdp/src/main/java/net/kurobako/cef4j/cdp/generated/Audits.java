// GENERATED - do not edit. Regenerate via: ./mvnw generate-sources -pl cef4j-cdp -Dcef.version=150.0.18+gdb11278+chromium-150.0.7871.213
package net.kurobako.cef4j.cdp.generated;

import java.util.Map;
import java.util.Optional;
import java.util.OptionalDouble;
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
 * Audits domain allows investigation of page violations and possible improvements.
 * <p><b>Experimental:</b> this part of CDP may change without notice.
 * @see <a href="https://chromium.googlesource.com/chromium/src/+/refs/tags/150.0.7871.213/third_party/blink/public/devtools_protocol/domains/Audits.pdl">Pinned protocol source</a>
 */
@Generated("./mvnw generate-sources -pl cef4j-cdp -Dcef.version=150.0.18+gdb11278+chromium-150.0.7871.213")
@SuppressWarnings({"EscapedEntity", "InvalidParam", "JavaLangClash", "MissingSummary", "Unchecked", "UnusedMethod"})
@NullableBoundary("CDP wire objects use null for absent optional fields")
public final class Audits {
    private Audits() {}
    private static final CdpField<Network.AdAncestry> AD_ANCESTRY = CdpField.of("adAncestry", value -> java.util.Objects.requireNonNull(Network.AdAncestry.fromMap(CdpObject.requireObjectMap(value))));
    private static final CdpField<Audits.AffectedFrame> AFFECTED_FRAME = CdpField.of("affectedFrame", value -> java.util.Objects.requireNonNull(Audits.AffectedFrame.fromMap(CdpObject.requireObjectMap(value))));
    private static final CdpField<java.util.List<String>> ALLOWED_SITES = CdpField.of("allowedSites", value -> CdpObject.requireList(value, element0 -> (String) element0));
    private static final CdpField<Audits.AttributionReportingIssueDetails> ATTRIBUTION_REPORTING_ISSUE_DETAILS = CdpField.of("attributionReportingIssueDetails", value -> java.util.Objects.requireNonNull(Audits.AttributionReportingIssueDetails.fromMap(CdpObject.requireObjectMap(value))));
    private static final CdpField<Audits.BlockedByResponseIssueDetails> BLOCKED_BY_RESPONSE_ISSUE_DETAILS = CdpField.of("blockedByResponseIssueDetails", value -> java.util.Objects.requireNonNull(Audits.BlockedByResponseIssueDetails.fromMap(CdpObject.requireObjectMap(value))));
    private static final CdpField<Audits.AffectedFrame> BLOCKED_FRAME = CdpField.of("blockedFrame", value -> java.util.Objects.requireNonNull(Audits.AffectedFrame.fromMap(CdpObject.requireObjectMap(value))));
    private static final CdpField<Audits.BounceTrackingIssueDetails> BOUNCE_TRACKING_ISSUE_DETAILS = CdpField.of("bounceTrackingIssueDetails", value -> java.util.Objects.requireNonNull(Audits.BounceTrackingIssueDetails.fromMap(CdpObject.requireObjectMap(value))));
    private static final CdpField<Audits.ClientHintIssueDetails> CLIENT_HINT_ISSUE_DETAILS = CdpField.of("clientHintIssueDetails", value -> java.util.Objects.requireNonNull(Audits.ClientHintIssueDetails.fromMap(CdpObject.requireObjectMap(value))));
    private static final CdpField<Audits.ClientHintIssueReason> CLIENT_HINT_ISSUE_REASON = CdpField.of("clientHintIssueReason", value -> Audits.ClientHintIssueReason.of((String) value));
    private static final CdpField<Network.ClientSecurityState> CLIENT_SECURITY_STATE = CdpField.of("clientSecurityState", value -> java.util.Objects.requireNonNull(Network.ClientSecurityState.fromMap(CdpObject.requireObjectMap(value))));
    private static final CdpField<Audits.InspectorIssueCode> CODE = CdpField.of("code", value -> Audits.InspectorIssueCode.of((String) value));
    private static final CdpField<Audits.ConnectionAllowlistIssueDetails> CONNECTION_ALLOWLIST_ISSUE_DETAILS = CdpField.of("connectionAllowlistIssueDetails", value -> java.util.Objects.requireNonNull(Audits.ConnectionAllowlistIssueDetails.fromMap(CdpObject.requireObjectMap(value))));
    private static final CdpField<Audits.ContentSecurityPolicyIssueDetails> CONTENT_SECURITY_POLICY_ISSUE_DETAILS = CdpField.of("contentSecurityPolicyIssueDetails", value -> java.util.Objects.requireNonNull(Audits.ContentSecurityPolicyIssueDetails.fromMap(CdpObject.requireObjectMap(value))));
    private static final CdpField<Audits.ContentSecurityPolicyViolationType> CONTENT_SECURITY_POLICY_VIOLATION_TYPE = CdpField.of("contentSecurityPolicyViolationType", value -> Audits.ContentSecurityPolicyViolationType.of((String) value));
    private static final CdpField<Audits.AffectedCookie> COOKIE = CdpField.of("cookie", value -> java.util.Objects.requireNonNull(Audits.AffectedCookie.fromMap(CdpObject.requireObjectMap(value))));
    private static final CdpField<Audits.CookieDeprecationMetadataIssueDetails> COOKIE_DEPRECATION_METADATA_ISSUE_DETAILS = CdpField.of("cookieDeprecationMetadataIssueDetails", value -> java.util.Objects.requireNonNull(Audits.CookieDeprecationMetadataIssueDetails.fromMap(CdpObject.requireObjectMap(value))));
    private static final CdpField<java.util.List<Audits.CookieExclusionReason>> COOKIE_EXCLUSION_REASONS = CdpField.of("cookieExclusionReasons", value -> CdpObject.requireList(value, element0 -> Audits.CookieExclusionReason.of((String) element0)));
    private static final CdpField<Audits.CookieIssueDetails> COOKIE_ISSUE_DETAILS = CdpField.of("cookieIssueDetails", value -> java.util.Objects.requireNonNull(Audits.CookieIssueDetails.fromMap(CdpObject.requireObjectMap(value))));
    private static final CdpField<java.util.List<Audits.CookieWarningReason>> COOKIE_WARNING_REASONS = CdpField.of("cookieWarningReasons", value -> CdpObject.requireList(value, element0 -> Audits.CookieWarningReason.of((String) element0)));
    private static final CdpField<Network.CorsErrorStatus> CORS_ERROR_STATUS = CdpField.of("corsErrorStatus", value -> java.util.Objects.requireNonNull(Network.CorsErrorStatus.fromMap(CdpObject.requireObjectMap(value))));
    private static final CdpField<Audits.CorsIssueDetails> CORS_ISSUE_DETAILS = CdpField.of("corsIssueDetails", value -> java.util.Objects.requireNonNull(Audits.CorsIssueDetails.fromMap(CdpObject.requireObjectMap(value))));
    private static final CdpField<Audits.DeprecationIssueDetails> DEPRECATION_ISSUE_DETAILS = CdpField.of("deprecationIssueDetails", value -> java.util.Objects.requireNonNull(Audits.DeprecationIssueDetails.fromMap(CdpObject.requireObjectMap(value))));
    private static final CdpField<Audits.InspectorIssueDetails> DETAILS = CdpField.of("details", value -> java.util.Objects.requireNonNull(Audits.InspectorIssueDetails.fromMap(CdpObject.requireObjectMap(value))));
    private static final CdpField<DOM.BackendNodeId> DOCUMENT_NODE_ID = CdpField.of("documentNodeId", value -> new DOM.BackendNodeId(((Number) value).longValue()));
    private static final CdpField<Audits.ElementAccessibilityIssueDetails> ELEMENT_ACCESSIBILITY_ISSUE_DETAILS = CdpField.of("elementAccessibilityIssueDetails", value -> java.util.Objects.requireNonNull(Audits.ElementAccessibilityIssueDetails.fromMap(CdpObject.requireObjectMap(value))));
    private static final CdpField<Audits.ElementAccessibilityIssueReason> ELEMENT_ACCESSIBILITY_ISSUE_REASON = CdpField.of("elementAccessibilityIssueReason", value -> Audits.ElementAccessibilityIssueReason.of((String) value));
    private static final CdpField<Audits.EmailVerificationRequestIssueDetails> EMAIL_VERIFICATION_REQUEST_ISSUE_DETAILS = CdpField.of("emailVerificationRequestIssueDetails", value -> java.util.Objects.requireNonNull(Audits.EmailVerificationRequestIssueDetails.fromMap(CdpObject.requireObjectMap(value))));
    private static final CdpField<Audits.EmailVerificationRequestIssueReason> EMAIL_VERIFICATION_REQUEST_ISSUE_REASON = CdpField.of("emailVerificationRequestIssueReason", value -> Audits.EmailVerificationRequestIssueReason.of((String) value));
    private static final CdpField<GetEncodedResponseEncodingValues> ENCODING = CdpField.of("encoding", value -> GetEncodedResponseEncodingValues.of((String) value));
    private static final CdpField<Audits.SRIMessageSignatureError> ERROR = CdpField.of("error", value -> Audits.SRIMessageSignatureError.of((String) value));
    private static final CdpField<Audits.UnencodedDigestError> ERROR_2 = CdpField.of("error", value -> Audits.UnencodedDigestError.of((String) value));
    private static final CdpField<Audits.ConnectionAllowlistError> ERROR_3 = CdpField.of("error", value -> Audits.ConnectionAllowlistError.of((String) value));
    private static final CdpField<Audits.GenericIssueErrorType> ERROR_TYPE = CdpField.of("errorType", value -> Audits.GenericIssueErrorType.of((String) value));
    private static final CdpField<Audits.FailedRequestInfo> FAILED_REQUEST_INFO = CdpField.of("failedRequestInfo", value -> java.util.Objects.requireNonNull(Audits.FailedRequestInfo.fromMap(CdpObject.requireObjectMap(value))));
    private static final CdpField<Audits.FederatedAuthRequestIssueDetails> FEDERATED_AUTH_REQUEST_ISSUE_DETAILS = CdpField.of("federatedAuthRequestIssueDetails", value -> java.util.Objects.requireNonNull(Audits.FederatedAuthRequestIssueDetails.fromMap(CdpObject.requireObjectMap(value))));
    private static final CdpField<Audits.FederatedAuthRequestIssueReason> FEDERATED_AUTH_REQUEST_ISSUE_REASON = CdpField.of("federatedAuthRequestIssueReason", value -> Audits.FederatedAuthRequestIssueReason.of((String) value));
    private static final CdpField<Audits.FederatedAuthUserInfoRequestIssueDetails> FEDERATED_AUTH_USER_INFO_REQUEST_ISSUE_DETAILS = CdpField.of("federatedAuthUserInfoRequestIssueDetails", value -> java.util.Objects.requireNonNull(Audits.FederatedAuthUserInfoRequestIssueDetails.fromMap(CdpObject.requireObjectMap(value))));
    private static final CdpField<Audits.FederatedAuthUserInfoRequestIssueReason> FEDERATED_AUTH_USER_INFO_REQUEST_ISSUE_REASON = CdpField.of("federatedAuthUserInfoRequestIssueReason", value -> Audits.FederatedAuthUserInfoRequestIssueReason.of((String) value));
    private static final CdpField<java.util.List<Audits.GenericIssueDetails>> FORM_ISSUES = CdpField.of("formIssues", value -> CdpObject.requireList(value, element0 -> java.util.Objects.requireNonNull(Audits.GenericIssueDetails.fromMap(CdpObject.requireObjectMap(element0)))));
    private static final CdpField<Audits.AffectedFrame> FRAME = CdpField.of("frame", value -> java.util.Objects.requireNonNull(Audits.AffectedFrame.fromMap(CdpObject.requireObjectMap(value))));
    private static final CdpField<Audits.AffectedFrame> FRAME_ANCESTOR = CdpField.of("frameAncestor", value -> java.util.Objects.requireNonNull(Audits.AffectedFrame.fromMap(CdpObject.requireObjectMap(value))));
    private static final CdpField<Page.FrameId> FRAME_ID = CdpField.of("frameId", value -> new Page.FrameId((String) value));
    private static final CdpField<Audits.GenericIssueDetails> GENERIC_ISSUE_DETAILS = CdpField.of("genericIssueDetails", value -> java.util.Objects.requireNonNull(Audits.GenericIssueDetails.fromMap(CdpObject.requireObjectMap(value))));
    private static final CdpField<Audits.HeavyAdIssueDetails> HEAVY_AD_ISSUE_DETAILS = CdpField.of("heavyAdIssueDetails", value -> java.util.Objects.requireNonNull(Audits.HeavyAdIssueDetails.fromMap(CdpObject.requireObjectMap(value))));
    private static final CdpField<Audits.CookieIssueInsight> INSIGHT = CdpField.of("insight", value -> java.util.Objects.requireNonNull(Audits.CookieIssueInsight.fromMap(CdpObject.requireObjectMap(value))));
    private static final CdpField<java.util.List<String>> INTEGRITY_ASSERTIONS = CdpField.of("integrityAssertions", value -> CdpObject.requireList(value, element0 -> (String) element0));
    private static final CdpField<Audits.InspectorIssue> ISSUE = CdpField.of("issue", value -> java.util.Objects.requireNonNull(Audits.InspectorIssue.fromMap(CdpObject.requireObjectMap(value))));
    private static final CdpField<Audits.IssueId> ISSUE_ID = CdpField.of("issueId", value -> new Audits.IssueId((String) value));
    private static final CdpField<Audits.PermissionElementIssueType> ISSUE_TYPE = CdpField.of("issueType", value -> Audits.PermissionElementIssueType.of((String) value));
    private static final CdpField<Network.LoaderId> LOADER_ID = CdpField.of("loaderId", value -> new Network.LoaderId((String) value));
    private static final CdpField<Audits.SourceCodeLocation> LOCATION = CdpField.of("location", value -> java.util.Objects.requireNonNull(Audits.SourceCodeLocation.fromMap(CdpObject.requireObjectMap(value))));
    private static final CdpField<Audits.MixedContentIssueDetails> MIXED_CONTENT_ISSUE_DETAILS = CdpField.of("mixedContentIssueDetails", value -> java.util.Objects.requireNonNull(Audits.MixedContentIssueDetails.fromMap(CdpObject.requireObjectMap(value))));
    private static final CdpField<Audits.NavigatorUserAgentIssueDetails> NAVIGATOR_USER_AGENT_ISSUE_DETAILS = CdpField.of("navigatorUserAgentIssueDetails", value -> java.util.Objects.requireNonNull(Audits.NavigatorUserAgentIssueDetails.fromMap(CdpObject.requireObjectMap(value))));
    private static final CdpField<DOM.BackendNodeId> NODE_ID = CdpField.of("nodeId", value -> new DOM.BackendNodeId(((Number) value).longValue()));
    private static final CdpField<Audits.CookieOperation> OPERATION = CdpField.of("operation", value -> Audits.CookieOperation.of((String) value));
    private static final CdpField<Audits.AffectedFrame> PARENT_FRAME = CdpField.of("parentFrame", value -> java.util.Objects.requireNonNull(Audits.AffectedFrame.fromMap(CdpObject.requireObjectMap(value))));
    private static final CdpField<Audits.PartitioningBlobURLInfo> PARTITIONING_BLOB_URLINFO = CdpField.of("partitioningBlobURLInfo", value -> Audits.PartitioningBlobURLInfo.of((String) value));
    private static final CdpField<Audits.PartitioningBlobURLIssueDetails> PARTITIONING_BLOB_URLISSUE_DETAILS = CdpField.of("partitioningBlobURLIssueDetails", value -> java.util.Objects.requireNonNull(Audits.PartitioningBlobURLIssueDetails.fromMap(CdpObject.requireObjectMap(value))));
    private static final CdpField<Audits.PerformanceIssueDetails> PERFORMANCE_ISSUE_DETAILS = CdpField.of("performanceIssueDetails", value -> java.util.Objects.requireNonNull(Audits.PerformanceIssueDetails.fromMap(CdpObject.requireObjectMap(value))));
    private static final CdpField<Audits.PerformanceIssueType> PERFORMANCE_ISSUE_TYPE = CdpField.of("performanceIssueType", value -> Audits.PerformanceIssueType.of((String) value));
    private static final CdpField<Audits.PermissionElementIssueDetails> PERMISSION_ELEMENT_ISSUE_DETAILS = CdpField.of("permissionElementIssueDetails", value -> java.util.Objects.requireNonNull(Audits.PermissionElementIssueDetails.fromMap(CdpObject.requireObjectMap(value))));
    private static final CdpField<Audits.PropertyRuleIssueDetails> PROPERTY_RULE_ISSUE_DETAILS = CdpField.of("propertyRuleIssueDetails", value -> java.util.Objects.requireNonNull(Audits.PropertyRuleIssueDetails.fromMap(CdpObject.requireObjectMap(value))));
    private static final CdpField<Audits.PropertyRuleIssueReason> PROPERTY_RULE_ISSUE_REASON = CdpField.of("propertyRuleIssueReason", value -> Audits.PropertyRuleIssueReason.of((String) value));
    private static final CdpField<Audits.QuirksModeIssueDetails> QUIRKS_MODE_ISSUE_DETAILS = CdpField.of("quirksModeIssueDetails", value -> java.util.Objects.requireNonNull(Audits.QuirksModeIssueDetails.fromMap(CdpObject.requireObjectMap(value))));
    private static final CdpField<Audits.BlockedByResponseReason> REASON = CdpField.of("reason", value -> Audits.BlockedByResponseReason.of((String) value));
    private static final CdpField<Audits.HeavyAdReason> REASON_2 = CdpField.of("reason", value -> Audits.HeavyAdReason.of((String) value));
    private static final CdpField<Audits.AffectedRequest> REQUEST = CdpField.of("request", value -> java.util.Objects.requireNonNull(Audits.AffectedRequest.fromMap(CdpObject.requireObjectMap(value))));
    private static final CdpField<Network.RequestId> REQUEST_ID = CdpField.of("requestId", value -> new Network.RequestId((String) value));
    private static final CdpField<Audits.HeavyAdResolutionStatus> RESOLUTION = CdpField.of("resolution", value -> Audits.HeavyAdResolutionStatus.of((String) value));
    private static final CdpField<Audits.MixedContentResolutionStatus> RESOLUTION_STATUS = CdpField.of("resolutionStatus", value -> Audits.MixedContentResolutionStatus.of((String) value));
    private static final CdpField<Network.IPAddressSpace> RESOURCE_IPADDRESS_SPACE = CdpField.of("resourceIPAddressSpace", value -> Network.IPAddressSpace.of((String) value));
    private static final CdpField<Audits.MixedContentResourceType> RESOURCE_TYPE = CdpField.of("resourceType", value -> Audits.MixedContentResourceType.of((String) value));
    private static final CdpField<Runtime.ScriptId> SCRIPT_ID = CdpField.of("scriptId", value -> new Runtime.ScriptId((String) value));
    private static final CdpField<Audits.SelectivePermissionsInterventionIssueDetails> SELECTIVE_PERMISSIONS_INTERVENTION_ISSUE_DETAILS = CdpField.of("selectivePermissionsInterventionIssueDetails", value -> java.util.Objects.requireNonNull(Audits.SelectivePermissionsInterventionIssueDetails.fromMap(CdpObject.requireObjectMap(value))));
    private static final CdpField<Audits.SharedArrayBufferIssueDetails> SHARED_ARRAY_BUFFER_ISSUE_DETAILS = CdpField.of("sharedArrayBufferIssueDetails", value -> java.util.Objects.requireNonNull(Audits.SharedArrayBufferIssueDetails.fromMap(CdpObject.requireObjectMap(value))));
    private static final CdpField<Audits.SharedDictionaryError> SHARED_DICTIONARY_ERROR = CdpField.of("sharedDictionaryError", value -> Audits.SharedDictionaryError.of((String) value));
    private static final CdpField<Audits.SharedDictionaryIssueDetails> SHARED_DICTIONARY_ISSUE_DETAILS = CdpField.of("sharedDictionaryIssueDetails", value -> java.util.Objects.requireNonNull(Audits.SharedDictionaryIssueDetails.fromMap(CdpObject.requireObjectMap(value))));
    private static final CdpField<Audits.SourceCodeLocation> SOURCE_CODE_LOCATION = CdpField.of("sourceCodeLocation", value -> java.util.Objects.requireNonNull(Audits.SourceCodeLocation.fromMap(CdpObject.requireObjectMap(value))));
    private static final CdpField<Audits.SRIMessageSignatureIssueDetails> SRI_MESSAGE_SIGNATURE_ISSUE_DETAILS = CdpField.of("sriMessageSignatureIssueDetails", value -> java.util.Objects.requireNonNull(Audits.SRIMessageSignatureIssueDetails.fromMap(CdpObject.requireObjectMap(value))));
    private static final CdpField<Runtime.StackTrace> STACK_TRACE = CdpField.of("stackTrace", value -> java.util.Objects.requireNonNull(Runtime.StackTrace.fromMap(CdpObject.requireObjectMap(value))));
    private static final CdpField<Audits.StylesheetLoadingIssueDetails> STYLESHEET_LOADING_ISSUE_DETAILS = CdpField.of("stylesheetLoadingIssueDetails", value -> java.util.Objects.requireNonNull(Audits.StylesheetLoadingIssueDetails.fromMap(CdpObject.requireObjectMap(value))));
    private static final CdpField<Audits.StyleSheetLoadingIssueReason> STYLE_SHEET_LOADING_ISSUE_REASON = CdpField.of("styleSheetLoadingIssueReason", value -> Audits.StyleSheetLoadingIssueReason.of((String) value));
    private static final CdpField<java.util.List<String>> TRACKING_SITES = CdpField.of("trackingSites", value -> CdpObject.requireList(value, element0 -> (String) element0));
    private static final CdpField<Audits.InsightType> TYPE = CdpField.of("type", value -> Audits.InsightType.of((String) value));
    private static final CdpField<Audits.SharedArrayBufferIssueType> TYPE_2 = CdpField.of("type", value -> Audits.SharedArrayBufferIssueType.of((String) value));
    private static final CdpField<Audits.UserReidentificationIssueType> TYPE_3 = CdpField.of("type", value -> Audits.UserReidentificationIssueType.of((String) value));
    private static final CdpField<Audits.UnencodedDigestIssueDetails> UNENCODED_DIGEST_ISSUE_DETAILS = CdpField.of("unencodedDigestIssueDetails", value -> java.util.Objects.requireNonNull(Audits.UnencodedDigestIssueDetails.fromMap(CdpObject.requireObjectMap(value))));
    private static final CdpField<Audits.UserReidentificationIssueDetails> USER_REIDENTIFICATION_ISSUE_DETAILS = CdpField.of("userReidentificationIssueDetails", value -> java.util.Objects.requireNonNull(Audits.UserReidentificationIssueDetails.fromMap(CdpObject.requireObjectMap(value))));
    private static final CdpField<DOM.BackendNodeId> VIOLATING_NODE_ID = CdpField.of("violatingNodeId", value -> new DOM.BackendNodeId(((Number) value).longValue()));
    private static final CdpField<Audits.AttributionReportingIssueType> VIOLATION_TYPE = CdpField.of("violationType", value -> Audits.AttributionReportingIssueType.of((String) value));
    /**
     * Information about a cookie that is affected by an inspector issue.
     */
    public static final class AffectedCookie extends CdpObject {
        public AffectedCookie() {}
        private AffectedCookie(Map<String, Object> values) { super(values); }
        public static AffectedCookie fromMap(Map<String, Object> values) {
            return new AffectedCookie(values);
        }
        /**
         * The following three properties uniquely identify a cookie
         * @return the protocol field value
         */
        public String name() {
            return requireString("name");
        }
        /**
         * Returns the path field.
         * @return the protocol field value
         */
        public String path() {
            return requireString("path");
        }
        /**
         * Returns the domain field.
         * @return the protocol field value
         */
        public String domain() {
            return requireString("domain");
        }
        /**
         * The following three properties uniquely identify a cookie
         * @param name field value
         * @return this model
         */
        public AffectedCookie name(String name) {
            set("name", name);
            return this;
        }
        /**
         * Sets the path field.
         * @param path field value
         * @return this model
         */
        public AffectedCookie path(String path) {
            set("path", path);
            return this;
        }
        /**
         * Sets the domain field.
         * @param domain field value
         * @return this model
         */
        public AffectedCookie domain(String domain) {
            set("domain", domain);
            return this;
        }
    }
    /**
     * Information about a request that is affected by an inspector issue.
     */
    public static final class AffectedRequest extends CdpObject {
        public AffectedRequest() {}
        private AffectedRequest(Map<String, Object> values) { super(values); }
        public static AffectedRequest fromMap(Map<String, Object> values) {
            return new AffectedRequest(values);
        }
        /**
         * The unique request id.
         * @return the protocol field value, empty when absent
         */
        public Optional<Network.RequestId> requestId() {
            return optional(REQUEST_ID);
        }
        /**
         * Returns the url field.
         * @return the protocol field value
         */
        public String url() {
            return requireString("url");
        }
        /**
         * The unique request id.
         * @param requestId field value
         * @return this model
         */
        public AffectedRequest requestId(Network.RequestId requestId) {
            set("requestId", requestId);
            return this;
        }
        /**
         * Sets the url field.
         * @param url field value
         * @return this model
         */
        public AffectedRequest url(String url) {
            set("url", url);
            return this;
        }
    }
    /**
     * Information about the frame affected by an inspector issue.
     */
    public static final class AffectedFrame extends CdpObject {
        public AffectedFrame() {}
        private AffectedFrame(Map<String, Object> values) { super(values); }
        public static AffectedFrame fromMap(Map<String, Object> values) {
            return new AffectedFrame(values);
        }
        /**
         * Returns the frameId field.
         * @return the protocol field value
         */
        public Page.FrameId frameId() {
            return require(FRAME_ID);
        }
        /**
         * Sets the frameId field.
         * @param frameId field value
         * @return this model
         */
        public AffectedFrame frameId(Page.FrameId frameId) {
            set("frameId", frameId);
            return this;
        }
    }
    /**
     * Wire values for CookieExclusionReason.
     */
    public enum CookieExclusionReason implements CdpValue<String> {
        EXCLUDESAMESITEUNSPECIFIEDTREATEDASLAX("ExcludeSameSiteUnspecifiedTreatedAsLax"),
        EXCLUDESAMESITENONEINSECURE("ExcludeSameSiteNoneInsecure"),
        EXCLUDESAMESITELAX("ExcludeSameSiteLax"),
        EXCLUDESAMESITESTRICT("ExcludeSameSiteStrict"),
        EXCLUDEDOMAINNONASCII("ExcludeDomainNonASCII"),
        EXCLUDETHIRDPARTYCOOKIEBLOCKEDINFIRSTPARTYSET("ExcludeThirdPartyCookieBlockedInFirstPartySet"),
        EXCLUDETHIRDPARTYPHASEOUT("ExcludeThirdPartyPhaseout"),
        EXCLUDEPORTMISMATCH("ExcludePortMismatch"),
        EXCLUDESCHEMEMISMATCH("ExcludeSchemeMismatch");
        public final String value;
        CookieExclusionReason(String value) { this.value = value; }
        @Override public String value() { return value; }
        public static CookieExclusionReason of(String value) {
            for (CookieExclusionReason constant : values()) {
                if (constant.value.equals(value)) return constant;
            }
            throw new IllegalArgumentException("Unknown CookieExclusionReason value: " + value);
        }
    }
    /**
     * Wire values for CookieWarningReason.
     */
    public enum CookieWarningReason implements CdpValue<String> {
        WARNSAMESITEUNSPECIFIEDCROSSSITECONTEXT("WarnSameSiteUnspecifiedCrossSiteContext"),
        WARNSAMESITENONEINSECURE("WarnSameSiteNoneInsecure"),
        WARNSAMESITEUNSPECIFIEDLAXALLOWUNSAFE("WarnSameSiteUnspecifiedLaxAllowUnsafe"),
        WARNSAMESITESTRICTLAXDOWNGRADESTRICT("WarnSameSiteStrictLaxDowngradeStrict"),
        WARNSAMESITESTRICTCROSSDOWNGRADESTRICT("WarnSameSiteStrictCrossDowngradeStrict"),
        WARNSAMESITESTRICTCROSSDOWNGRADELAX("WarnSameSiteStrictCrossDowngradeLax"),
        WARNSAMESITELAXCROSSDOWNGRADESTRICT("WarnSameSiteLaxCrossDowngradeStrict"),
        WARNSAMESITELAXCROSSDOWNGRADELAX("WarnSameSiteLaxCrossDowngradeLax"),
        WARNATTRIBUTEVALUEEXCEEDSMAXSIZE("WarnAttributeValueExceedsMaxSize"),
        WARNDOMAINNONASCII("WarnDomainNonASCII"),
        WARNTHIRDPARTYPHASEOUT("WarnThirdPartyPhaseout"),
        WARNCROSSSITEREDIRECTDOWNGRADECHANGESINCLUSION("WarnCrossSiteRedirectDowngradeChangesInclusion"),
        WARNDEPRECATIONTRIALMETADATA("WarnDeprecationTrialMetadata"),
        WARNTHIRDPARTYCOOKIEHEURISTIC("WarnThirdPartyCookieHeuristic");
        public final String value;
        CookieWarningReason(String value) { this.value = value; }
        @Override public String value() { return value; }
        public static CookieWarningReason of(String value) {
            for (CookieWarningReason constant : values()) {
                if (constant.value.equals(value)) return constant;
            }
            throw new IllegalArgumentException("Unknown CookieWarningReason value: " + value);
        }
    }
    /**
     * Wire values for CookieOperation.
     */
    public enum CookieOperation implements CdpValue<String> {
        SETCOOKIE("SetCookie"),
        READCOOKIE("ReadCookie");
        public final String value;
        CookieOperation(String value) { this.value = value; }
        @Override public String value() { return value; }
        public static CookieOperation of(String value) {
            for (CookieOperation constant : values()) {
                if (constant.value.equals(value)) return constant;
            }
            throw new IllegalArgumentException("Unknown CookieOperation value: " + value);
        }
    }
    /**
     * Represents the category of insight that a cookie issue falls under.
     */
    public enum InsightType implements CdpValue<String> {
        GITHUBRESOURCE("GitHubResource"),
        GRACEPERIOD("GracePeriod"),
        HEURISTICS("Heuristics");
        public final String value;
        InsightType(String value) { this.value = value; }
        @Override public String value() { return value; }
        public static InsightType of(String value) {
            for (InsightType constant : values()) {
                if (constant.value.equals(value)) return constant;
            }
            throw new IllegalArgumentException("Unknown InsightType value: " + value);
        }
    }
    /**
     * Information about the suggested solution to a cookie issue.
     */
    public static final class CookieIssueInsight extends CdpObject {
        public CookieIssueInsight() {}
        private CookieIssueInsight(Map<String, Object> values) { super(values); }
        public static CookieIssueInsight fromMap(Map<String, Object> values) {
            return new CookieIssueInsight(values);
        }
        /**
         * Returns the type field.
         * @return the protocol field value
         */
        public Audits.InsightType type() {
            return require(TYPE);
        }
        /**
         * Link to table entry in third-party cookie migration readiness list.
         * @return the protocol field value, empty when absent
         */
        public Optional<String> tableEntryUrl() {
            return optionalString("tableEntryUrl");
        }
        /**
         * Sets the type field.
         * @param type field value
         * @return this model
         */
        public CookieIssueInsight type(Audits.InsightType type) {
            set("type", type);
            return this;
        }
        /**
         * Link to table entry in third-party cookie migration readiness list.
         * @param tableEntryUrl field value
         * @return this model
         */
        public CookieIssueInsight tableEntryUrl(String tableEntryUrl) {
            set("tableEntryUrl", tableEntryUrl);
            return this;
        }
    }
    /**
     * This information is currently necessary, as the front-end has a difficult time finding a specific cookie. With this, we can convey specific error information without the cookie.
     */
    public static final class CookieIssueDetails extends CdpObject {
        public CookieIssueDetails() {}
        private CookieIssueDetails(Map<String, Object> values) { super(values); }
        public static CookieIssueDetails fromMap(Map<String, Object> values) {
            return new CookieIssueDetails(values);
        }
        /**
         * If AffectedCookie is not set then rawCookieLine contains the raw Set-Cookie header string. This hints at a problem where the cookie line is syntactically or semantically malformed in a way that no valid cookie could be created.
         * @return the protocol field value, empty when absent
         */
        public Optional<Audits.AffectedCookie> cookie() {
            return optional(COOKIE);
        }
        /**
         * Returns the rawCookieLine field.
         * @return the protocol field value, empty when absent
         */
        public Optional<String> rawCookieLine() {
            return optionalString("rawCookieLine");
        }
        /**
         * Returns the cookieWarningReasons field.
         * @return the protocol field value
         */
        public java.util.List<Audits.CookieWarningReason> cookieWarningReasons() {
            return require(COOKIE_WARNING_REASONS);
        }
        /**
         * Returns the cookieExclusionReasons field.
         * @return the protocol field value
         */
        public java.util.List<Audits.CookieExclusionReason> cookieExclusionReasons() {
            return require(COOKIE_EXCLUSION_REASONS);
        }
        /**
         * Optionally identifies the site-for-cookies and the cookie url, which may be used by the front-end as additional context.
         * @return the protocol field value
         */
        public Audits.CookieOperation operation() {
            return require(OPERATION);
        }
        /**
         * Returns the siteForCookies field.
         * @return the protocol field value, empty when absent
         */
        public Optional<String> siteForCookies() {
            return optionalString("siteForCookies");
        }
        /**
         * Returns the cookieUrl field.
         * @return the protocol field value, empty when absent
         */
        public Optional<String> cookieUrl() {
            return optionalString("cookieUrl");
        }
        /**
         * Returns the request field.
         * @return the protocol field value, empty when absent
         */
        public Optional<Audits.AffectedRequest> request() {
            return optional(REQUEST);
        }
        /**
         * The recommended solution to the issue.
         * @return the protocol field value, empty when absent
         */
        public Optional<Audits.CookieIssueInsight> insight() {
            return optional(INSIGHT);
        }
        /**
         * If AffectedCookie is not set then rawCookieLine contains the raw Set-Cookie header string. This hints at a problem where the cookie line is syntactically or semantically malformed in a way that no valid cookie could be created.
         * @param cookie field value
         * @return this model
         */
        public CookieIssueDetails cookie(Audits.AffectedCookie cookie) {
            set("cookie", cookie);
            return this;
        }
        /**
         * Sets the rawCookieLine field.
         * @param rawCookieLine field value
         * @return this model
         */
        public CookieIssueDetails rawCookieLine(String rawCookieLine) {
            set("rawCookieLine", rawCookieLine);
            return this;
        }
        /**
         * Sets the cookieWarningReasons field.
         * @param cookieWarningReasons field value
         * @return this model
         */
        public CookieIssueDetails cookieWarningReasons(java.util.List<Audits.CookieWarningReason> cookieWarningReasons) {
            set("cookieWarningReasons", cookieWarningReasons);
            return this;
        }
        /**
         * Sets the cookieExclusionReasons field.
         * @param cookieExclusionReasons field value
         * @return this model
         */
        public CookieIssueDetails cookieExclusionReasons(java.util.List<Audits.CookieExclusionReason> cookieExclusionReasons) {
            set("cookieExclusionReasons", cookieExclusionReasons);
            return this;
        }
        /**
         * Optionally identifies the site-for-cookies and the cookie url, which may be used by the front-end as additional context.
         * @param operation field value
         * @return this model
         */
        public CookieIssueDetails operation(Audits.CookieOperation operation) {
            set("operation", operation);
            return this;
        }
        /**
         * Sets the siteForCookies field.
         * @param siteForCookies field value
         * @return this model
         */
        public CookieIssueDetails siteForCookies(String siteForCookies) {
            set("siteForCookies", siteForCookies);
            return this;
        }
        /**
         * Sets the cookieUrl field.
         * @param cookieUrl field value
         * @return this model
         */
        public CookieIssueDetails cookieUrl(String cookieUrl) {
            set("cookieUrl", cookieUrl);
            return this;
        }
        /**
         * Sets the request field.
         * @param request field value
         * @return this model
         */
        public CookieIssueDetails request(Audits.AffectedRequest request) {
            set("request", request);
            return this;
        }
        /**
         * The recommended solution to the issue.
         * @param insight field value
         * @return this model
         */
        public CookieIssueDetails insight(Audits.CookieIssueInsight insight) {
            set("insight", insight);
            return this;
        }
    }
    /**
     * Wire values for PerformanceIssueType.
     */
    public enum PerformanceIssueType implements CdpValue<String> {
        DOCUMENTCOOKIE("DocumentCookie");
        public final String value;
        PerformanceIssueType(String value) { this.value = value; }
        @Override public String value() { return value; }
        public static PerformanceIssueType of(String value) {
            for (PerformanceIssueType constant : values()) {
                if (constant.value.equals(value)) return constant;
            }
            throw new IllegalArgumentException("Unknown PerformanceIssueType value: " + value);
        }
    }
    /**
     * Details for a performance issue.
     */
    public static final class PerformanceIssueDetails extends CdpObject {
        public PerformanceIssueDetails() {}
        private PerformanceIssueDetails(Map<String, Object> values) { super(values); }
        public static PerformanceIssueDetails fromMap(Map<String, Object> values) {
            return new PerformanceIssueDetails(values);
        }
        /**
         * Returns the performanceIssueType field.
         * @return the protocol field value
         */
        public Audits.PerformanceIssueType performanceIssueType() {
            return require(PERFORMANCE_ISSUE_TYPE);
        }
        /**
         * Returns the sourceCodeLocation field.
         * @return the protocol field value, empty when absent
         */
        public Optional<Audits.SourceCodeLocation> sourceCodeLocation() {
            return optional(SOURCE_CODE_LOCATION);
        }
        /**
         * Sets the performanceIssueType field.
         * @param performanceIssueType field value
         * @return this model
         */
        public PerformanceIssueDetails performanceIssueType(Audits.PerformanceIssueType performanceIssueType) {
            set("performanceIssueType", performanceIssueType);
            return this;
        }
        /**
         * Sets the sourceCodeLocation field.
         * @param sourceCodeLocation field value
         * @return this model
         */
        public PerformanceIssueDetails sourceCodeLocation(Audits.SourceCodeLocation sourceCodeLocation) {
            set("sourceCodeLocation", sourceCodeLocation);
            return this;
        }
    }
    /**
     * Wire values for MixedContentResolutionStatus.
     */
    public enum MixedContentResolutionStatus implements CdpValue<String> {
        MIXEDCONTENTBLOCKED("MixedContentBlocked"),
        MIXEDCONTENTAUTOMATICALLYUPGRADED("MixedContentAutomaticallyUpgraded"),
        MIXEDCONTENTWARNING("MixedContentWarning");
        public final String value;
        MixedContentResolutionStatus(String value) { this.value = value; }
        @Override public String value() { return value; }
        public static MixedContentResolutionStatus of(String value) {
            for (MixedContentResolutionStatus constant : values()) {
                if (constant.value.equals(value)) return constant;
            }
            throw new IllegalArgumentException("Unknown MixedContentResolutionStatus value: " + value);
        }
    }
    /**
     * Wire values for MixedContentResourceType.
     */
    public enum MixedContentResourceType implements CdpValue<String> {
        ATTRIBUTIONSRC("AttributionSrc"),
        AUDIO("Audio"),
        BEACON("Beacon"),
        CSPREPORT("CSPReport"),
        DOWNLOAD("Download"),
        EVENTSOURCE("EventSource"),
        FAVICON("Favicon"),
        FONT("Font"),
        FORM("Form"),
        FRAME("Frame"),
        IMAGE("Image"),
        IMPORT("Import"),
        JSON("JSON"),
        MANIFEST("Manifest"),
        PING("Ping"),
        PLUGINDATA("PluginData"),
        PLUGINRESOURCE("PluginResource"),
        PREFETCH("Prefetch"),
        RESOURCE("Resource"),
        SCRIPT("Script"),
        SERVICEWORKER("ServiceWorker"),
        SHAREDWORKER("SharedWorker"),
        SPECULATIONRULES("SpeculationRules"),
        STYLESHEET("Stylesheet"),
        TRACK("Track"),
        VIDEO("Video"),
        WORKER("Worker"),
        XMLHTTPREQUEST("XMLHttpRequest"),
        XSLT("XSLT");
        public final String value;
        MixedContentResourceType(String value) { this.value = value; }
        @Override public String value() { return value; }
        public static MixedContentResourceType of(String value) {
            for (MixedContentResourceType constant : values()) {
                if (constant.value.equals(value)) return constant;
            }
            throw new IllegalArgumentException("Unknown MixedContentResourceType value: " + value);
        }
    }
    /**
     */
    public static final class MixedContentIssueDetails extends CdpObject {
        public MixedContentIssueDetails() {}
        private MixedContentIssueDetails(Map<String, Object> values) { super(values); }
        public static MixedContentIssueDetails fromMap(Map<String, Object> values) {
            return new MixedContentIssueDetails(values);
        }
        /**
         * The type of resource causing the mixed content issue (css, js, iframe, form,...). Marked as optional because it is mapped to from blink::mojom::RequestContextType, which will be replaced by network::mojom::RequestDestination
         * @return the protocol field value, empty when absent
         */
        public Optional<Audits.MixedContentResourceType> resourceType() {
            return optional(RESOURCE_TYPE);
        }
        /**
         * The way the mixed content issue is being resolved.
         * @return the protocol field value
         */
        public Audits.MixedContentResolutionStatus resolutionStatus() {
            return require(RESOLUTION_STATUS);
        }
        /**
         * The unsafe http url causing the mixed content issue.
         * @return the protocol field value
         */
        public String insecureURL() {
            return requireString("insecureURL");
        }
        /**
         * The url responsible for the call to an unsafe url.
         * @return the protocol field value
         */
        public String mainResourceURL() {
            return requireString("mainResourceURL");
        }
        /**
         * The mixed content request. Does not always exist (e.g. for unsafe form submission urls).
         * @return the protocol field value, empty when absent
         */
        public Optional<Audits.AffectedRequest> request() {
            return optional(REQUEST);
        }
        /**
         * Optional because not every mixed content issue is necessarily linked to a frame.
         * @return the protocol field value, empty when absent
         */
        public Optional<Audits.AffectedFrame> frame() {
            return optional(FRAME);
        }
        /**
         * The type of resource causing the mixed content issue (css, js, iframe, form,...). Marked as optional because it is mapped to from blink::mojom::RequestContextType, which will be replaced by network::mojom::RequestDestination
         * @param resourceType field value
         * @return this model
         */
        public MixedContentIssueDetails resourceType(Audits.MixedContentResourceType resourceType) {
            set("resourceType", resourceType);
            return this;
        }
        /**
         * The way the mixed content issue is being resolved.
         * @param resolutionStatus field value
         * @return this model
         */
        public MixedContentIssueDetails resolutionStatus(Audits.MixedContentResolutionStatus resolutionStatus) {
            set("resolutionStatus", resolutionStatus);
            return this;
        }
        /**
         * The unsafe http url causing the mixed content issue.
         * @param insecureURL field value
         * @return this model
         */
        public MixedContentIssueDetails insecureURL(String insecureURL) {
            set("insecureURL", insecureURL);
            return this;
        }
        /**
         * The url responsible for the call to an unsafe url.
         * @param mainResourceURL field value
         * @return this model
         */
        public MixedContentIssueDetails mainResourceURL(String mainResourceURL) {
            set("mainResourceURL", mainResourceURL);
            return this;
        }
        /**
         * The mixed content request. Does not always exist (e.g. for unsafe form submission urls).
         * @param request field value
         * @return this model
         */
        public MixedContentIssueDetails request(Audits.AffectedRequest request) {
            set("request", request);
            return this;
        }
        /**
         * Optional because not every mixed content issue is necessarily linked to a frame.
         * @param frame field value
         * @return this model
         */
        public MixedContentIssueDetails frame(Audits.AffectedFrame frame) {
            set("frame", frame);
            return this;
        }
    }
    /**
     * Enum indicating the reason a response has been blocked. These reasons are refinements of the net error BLOCKED_BY_RESPONSE.
     */
    public enum BlockedByResponseReason implements CdpValue<String> {
        COEPFRAMERESOURCENEEDSCOEPHEADER("CoepFrameResourceNeedsCoepHeader"),
        COOPSANDBOXEDIFRAMECANNOTNAVIGATETOCOOPPAGE("CoopSandboxedIFrameCannotNavigateToCoopPage"),
        CORPNOTSAMEORIGIN("CorpNotSameOrigin"),
        CORPNOTSAMEORIGINAFTERDEFAULTEDTOSAMEORIGINBYCOEP("CorpNotSameOriginAfterDefaultedToSameOriginByCoep"),
        CORPNOTSAMEORIGINAFTERDEFAULTEDTOSAMEORIGINBYDIP("CorpNotSameOriginAfterDefaultedToSameOriginByDip"),
        CORPNOTSAMEORIGINAFTERDEFAULTEDTOSAMEORIGINBYCOEPANDDIP("CorpNotSameOriginAfterDefaultedToSameOriginByCoepAndDip"),
        CORPNOTSAMESITE("CorpNotSameSite"),
        SRIMESSAGESIGNATUREMISMATCH("SRIMessageSignatureMismatch");
        public final String value;
        BlockedByResponseReason(String value) { this.value = value; }
        @Override public String value() { return value; }
        public static BlockedByResponseReason of(String value) {
            for (BlockedByResponseReason constant : values()) {
                if (constant.value.equals(value)) return constant;
            }
            throw new IllegalArgumentException("Unknown BlockedByResponseReason value: " + value);
        }
    }
    /**
     * Details for a request that has been blocked with the BLOCKED_BY_RESPONSE code. Currently only used for COEP/COOP, but may be extended to include some CSP errors in the future.
     */
    public static final class BlockedByResponseIssueDetails extends CdpObject {
        public BlockedByResponseIssueDetails() {}
        private BlockedByResponseIssueDetails(Map<String, Object> values) { super(values); }
        public static BlockedByResponseIssueDetails fromMap(Map<String, Object> values) {
            return new BlockedByResponseIssueDetails(values);
        }
        /**
         * Returns the request field.
         * @return the protocol field value
         */
        public Audits.AffectedRequest request() {
            return require(REQUEST);
        }
        /**
         * Returns the parentFrame field.
         * @return the protocol field value, empty when absent
         */
        public Optional<Audits.AffectedFrame> parentFrame() {
            return optional(PARENT_FRAME);
        }
        /**
         * Returns the blockedFrame field.
         * @return the protocol field value, empty when absent
         */
        public Optional<Audits.AffectedFrame> blockedFrame() {
            return optional(BLOCKED_FRAME);
        }
        /**
         * Returns the reason field.
         * @return the protocol field value
         */
        public Audits.BlockedByResponseReason reason() {
            return require(REASON);
        }
        /**
         * Sets the request field.
         * @param request field value
         * @return this model
         */
        public BlockedByResponseIssueDetails request(Audits.AffectedRequest request) {
            set("request", request);
            return this;
        }
        /**
         * Sets the parentFrame field.
         * @param parentFrame field value
         * @return this model
         */
        public BlockedByResponseIssueDetails parentFrame(Audits.AffectedFrame parentFrame) {
            set("parentFrame", parentFrame);
            return this;
        }
        /**
         * Sets the blockedFrame field.
         * @param blockedFrame field value
         * @return this model
         */
        public BlockedByResponseIssueDetails blockedFrame(Audits.AffectedFrame blockedFrame) {
            set("blockedFrame", blockedFrame);
            return this;
        }
        /**
         * Sets the reason field.
         * @param reason field value
         * @return this model
         */
        public BlockedByResponseIssueDetails reason(Audits.BlockedByResponseReason reason) {
            set("reason", reason);
            return this;
        }
    }
    /**
     * Wire values for HeavyAdResolutionStatus.
     */
    public enum HeavyAdResolutionStatus implements CdpValue<String> {
        HEAVYADBLOCKED("HeavyAdBlocked"),
        HEAVYADWARNING("HeavyAdWarning");
        public final String value;
        HeavyAdResolutionStatus(String value) { this.value = value; }
        @Override public String value() { return value; }
        public static HeavyAdResolutionStatus of(String value) {
            for (HeavyAdResolutionStatus constant : values()) {
                if (constant.value.equals(value)) return constant;
            }
            throw new IllegalArgumentException("Unknown HeavyAdResolutionStatus value: " + value);
        }
    }
    /**
     * Wire values for HeavyAdReason.
     */
    public enum HeavyAdReason implements CdpValue<String> {
        NETWORKTOTALLIMIT("NetworkTotalLimit"),
        CPUTOTALLIMIT("CpuTotalLimit"),
        CPUPEAKLIMIT("CpuPeakLimit");
        public final String value;
        HeavyAdReason(String value) { this.value = value; }
        @Override public String value() { return value; }
        public static HeavyAdReason of(String value) {
            for (HeavyAdReason constant : values()) {
                if (constant.value.equals(value)) return constant;
            }
            throw new IllegalArgumentException("Unknown HeavyAdReason value: " + value);
        }
    }
    /**
     */
    public static final class HeavyAdIssueDetails extends CdpObject {
        public HeavyAdIssueDetails() {}
        private HeavyAdIssueDetails(Map<String, Object> values) { super(values); }
        public static HeavyAdIssueDetails fromMap(Map<String, Object> values) {
            return new HeavyAdIssueDetails(values);
        }
        /**
         * The resolution status, either blocking the content or warning.
         * @return the protocol field value
         */
        public Audits.HeavyAdResolutionStatus resolution() {
            return require(RESOLUTION);
        }
        /**
         * The reason the ad was blocked, total network or cpu or peak cpu.
         * @return the protocol field value
         */
        public Audits.HeavyAdReason reason() {
            return require(REASON_2);
        }
        /**
         * The frame that was blocked.
         * @return the protocol field value
         */
        public Audits.AffectedFrame frame() {
            return require(FRAME);
        }
        /**
         * The resolution status, either blocking the content or warning.
         * @param resolution field value
         * @return this model
         */
        public HeavyAdIssueDetails resolution(Audits.HeavyAdResolutionStatus resolution) {
            set("resolution", resolution);
            return this;
        }
        /**
         * The reason the ad was blocked, total network or cpu or peak cpu.
         * @param reason field value
         * @return this model
         */
        public HeavyAdIssueDetails reason(Audits.HeavyAdReason reason) {
            set("reason", reason);
            return this;
        }
        /**
         * The frame that was blocked.
         * @param frame field value
         * @return this model
         */
        public HeavyAdIssueDetails frame(Audits.AffectedFrame frame) {
            set("frame", frame);
            return this;
        }
    }
    /**
     * Wire values for ContentSecurityPolicyViolationType.
     */
    public enum ContentSecurityPolicyViolationType implements CdpValue<String> {
        KINLINEVIOLATION("kInlineViolation"),
        KEVALVIOLATION("kEvalViolation"),
        KURLVIOLATION("kURLViolation"),
        KSRIVIOLATION("kSRIViolation"),
        KTRUSTEDTYPESSINKVIOLATION("kTrustedTypesSinkViolation"),
        KTRUSTEDTYPESPOLICYVIOLATION("kTrustedTypesPolicyViolation"),
        KWASMEVALVIOLATION("kWasmEvalViolation");
        public final String value;
        ContentSecurityPolicyViolationType(String value) { this.value = value; }
        @Override public String value() { return value; }
        public static ContentSecurityPolicyViolationType of(String value) {
            for (ContentSecurityPolicyViolationType constant : values()) {
                if (constant.value.equals(value)) return constant;
            }
            throw new IllegalArgumentException("Unknown ContentSecurityPolicyViolationType value: " + value);
        }
    }
    /**
     */
    public static final class SourceCodeLocation extends CdpObject {
        public SourceCodeLocation() {}
        private SourceCodeLocation(Map<String, Object> values) { super(values); }
        public static SourceCodeLocation fromMap(Map<String, Object> values) {
            return new SourceCodeLocation(values);
        }
        /**
         * Returns the scriptId field.
         * @return the protocol field value, empty when absent
         */
        public Optional<Runtime.ScriptId> scriptId() {
            return optional(SCRIPT_ID);
        }
        /**
         * Returns the url field.
         * @return the protocol field value
         */
        public String url() {
            return requireString("url");
        }
        /**
         * Returns the lineNumber field.
         * @return the protocol field value
         */
        public long lineNumber() {
            return requireLong("lineNumber");
        }
        /**
         * Returns the columnNumber field.
         * @return the protocol field value
         */
        public long columnNumber() {
            return requireLong("columnNumber");
        }
        /**
         * Sets the scriptId field.
         * @param scriptId field value
         * @return this model
         */
        public SourceCodeLocation scriptId(Runtime.ScriptId scriptId) {
            set("scriptId", scriptId);
            return this;
        }
        /**
         * Sets the url field.
         * @param url field value
         * @return this model
         */
        public SourceCodeLocation url(String url) {
            set("url", url);
            return this;
        }
        /**
         * Sets the lineNumber field.
         * @param lineNumber field value
         * @return this model
         */
        public SourceCodeLocation lineNumber(long lineNumber) {
            set("lineNumber", lineNumber);
            return this;
        }
        /**
         * Sets the columnNumber field.
         * @param columnNumber field value
         * @return this model
         */
        public SourceCodeLocation columnNumber(long columnNumber) {
            set("columnNumber", columnNumber);
            return this;
        }
    }
    /**
     */
    public static final class ContentSecurityPolicyIssueDetails extends CdpObject {
        public ContentSecurityPolicyIssueDetails() {}
        private ContentSecurityPolicyIssueDetails(Map<String, Object> values) { super(values); }
        public static ContentSecurityPolicyIssueDetails fromMap(Map<String, Object> values) {
            return new ContentSecurityPolicyIssueDetails(values);
        }
        /**
         * The url not included in allowed sources.
         * @return the protocol field value, empty when absent
         */
        public Optional<String> blockedURL() {
            return optionalString("blockedURL");
        }
        /**
         * Specific directive that is violated, causing the CSP issue.
         * @return the protocol field value
         */
        public String violatedDirective() {
            return requireString("violatedDirective");
        }
        /**
         * Returns the isReportOnly field.
         * @return the protocol field value
         */
        public boolean isReportOnly() {
            return requireBoolean("isReportOnly");
        }
        /**
         * Returns the contentSecurityPolicyViolationType field.
         * @return the protocol field value
         */
        public Audits.ContentSecurityPolicyViolationType contentSecurityPolicyViolationType() {
            return require(CONTENT_SECURITY_POLICY_VIOLATION_TYPE);
        }
        /**
         * Returns the frameAncestor field.
         * @return the protocol field value, empty when absent
         */
        public Optional<Audits.AffectedFrame> frameAncestor() {
            return optional(FRAME_ANCESTOR);
        }
        /**
         * Returns the sourceCodeLocation field.
         * @return the protocol field value, empty when absent
         */
        public Optional<Audits.SourceCodeLocation> sourceCodeLocation() {
            return optional(SOURCE_CODE_LOCATION);
        }
        /**
         * Returns the violatingNodeId field.
         * @return the protocol field value, empty when absent
         */
        public Optional<DOM.BackendNodeId> violatingNodeId() {
            return optional(VIOLATING_NODE_ID);
        }
        /**
         * The url not included in allowed sources.
         * @param blockedURL field value
         * @return this model
         */
        public ContentSecurityPolicyIssueDetails blockedURL(String blockedURL) {
            set("blockedURL", blockedURL);
            return this;
        }
        /**
         * Specific directive that is violated, causing the CSP issue.
         * @param violatedDirective field value
         * @return this model
         */
        public ContentSecurityPolicyIssueDetails violatedDirective(String violatedDirective) {
            set("violatedDirective", violatedDirective);
            return this;
        }
        /**
         * Sets the isReportOnly field.
         * @param isReportOnly field value
         * @return this model
         */
        public ContentSecurityPolicyIssueDetails isReportOnly(boolean isReportOnly) {
            set("isReportOnly", isReportOnly);
            return this;
        }
        /**
         * Sets the contentSecurityPolicyViolationType field.
         * @param contentSecurityPolicyViolationType field value
         * @return this model
         */
        public ContentSecurityPolicyIssueDetails contentSecurityPolicyViolationType(Audits.ContentSecurityPolicyViolationType contentSecurityPolicyViolationType) {
            set("contentSecurityPolicyViolationType", contentSecurityPolicyViolationType);
            return this;
        }
        /**
         * Sets the frameAncestor field.
         * @param frameAncestor field value
         * @return this model
         */
        public ContentSecurityPolicyIssueDetails frameAncestor(Audits.AffectedFrame frameAncestor) {
            set("frameAncestor", frameAncestor);
            return this;
        }
        /**
         * Sets the sourceCodeLocation field.
         * @param sourceCodeLocation field value
         * @return this model
         */
        public ContentSecurityPolicyIssueDetails sourceCodeLocation(Audits.SourceCodeLocation sourceCodeLocation) {
            set("sourceCodeLocation", sourceCodeLocation);
            return this;
        }
        /**
         * Sets the violatingNodeId field.
         * @param violatingNodeId field value
         * @return this model
         */
        public ContentSecurityPolicyIssueDetails violatingNodeId(DOM.BackendNodeId violatingNodeId) {
            set("violatingNodeId", violatingNodeId);
            return this;
        }
    }
    /**
     * Wire values for SharedArrayBufferIssueType.
     */
    public enum SharedArrayBufferIssueType implements CdpValue<String> {
        TRANSFERISSUE("TransferIssue"),
        CREATIONISSUE("CreationIssue");
        public final String value;
        SharedArrayBufferIssueType(String value) { this.value = value; }
        @Override public String value() { return value; }
        public static SharedArrayBufferIssueType of(String value) {
            for (SharedArrayBufferIssueType constant : values()) {
                if (constant.value.equals(value)) return constant;
            }
            throw new IllegalArgumentException("Unknown SharedArrayBufferIssueType value: " + value);
        }
    }
    /**
     * Details for a issue arising from an SAB being instantiated in, or transferred to a context that is not cross-origin isolated.
     */
    public static final class SharedArrayBufferIssueDetails extends CdpObject {
        public SharedArrayBufferIssueDetails() {}
        private SharedArrayBufferIssueDetails(Map<String, Object> values) { super(values); }
        public static SharedArrayBufferIssueDetails fromMap(Map<String, Object> values) {
            return new SharedArrayBufferIssueDetails(values);
        }
        /**
         * Returns the sourceCodeLocation field.
         * @return the protocol field value
         */
        public Audits.SourceCodeLocation sourceCodeLocation() {
            return require(SOURCE_CODE_LOCATION);
        }
        /**
         * Returns the isWarning field.
         * @return the protocol field value
         */
        public boolean isWarning() {
            return requireBoolean("isWarning");
        }
        /**
         * Returns the type field.
         * @return the protocol field value
         */
        public Audits.SharedArrayBufferIssueType type() {
            return require(TYPE_2);
        }
        /**
         * Sets the sourceCodeLocation field.
         * @param sourceCodeLocation field value
         * @return this model
         */
        public SharedArrayBufferIssueDetails sourceCodeLocation(Audits.SourceCodeLocation sourceCodeLocation) {
            set("sourceCodeLocation", sourceCodeLocation);
            return this;
        }
        /**
         * Sets the isWarning field.
         * @param isWarning field value
         * @return this model
         */
        public SharedArrayBufferIssueDetails isWarning(boolean isWarning) {
            set("isWarning", isWarning);
            return this;
        }
        /**
         * Sets the type field.
         * @param type field value
         * @return this model
         */
        public SharedArrayBufferIssueDetails type(Audits.SharedArrayBufferIssueType type) {
            set("type", type);
            return this;
        }
    }
    /**
     * Details for a CORS related issue, e.g. a warning or error related to CORS RFC1918 enforcement.
     */
    public static final class CorsIssueDetails extends CdpObject {
        public CorsIssueDetails() {}
        private CorsIssueDetails(Map<String, Object> values) { super(values); }
        public static CorsIssueDetails fromMap(Map<String, Object> values) {
            return new CorsIssueDetails(values);
        }
        /**
         * Returns the corsErrorStatus field.
         * @return the protocol field value
         */
        public Network.CorsErrorStatus corsErrorStatus() {
            return require(CORS_ERROR_STATUS);
        }
        /**
         * Returns the isWarning field.
         * @return the protocol field value
         */
        public boolean isWarning() {
            return requireBoolean("isWarning");
        }
        /**
         * Returns the request field.
         * @return the protocol field value
         */
        public Audits.AffectedRequest request() {
            return require(REQUEST);
        }
        /**
         * Returns the location field.
         * @return the protocol field value, empty when absent
         */
        public Optional<Audits.SourceCodeLocation> location() {
            return optional(LOCATION);
        }
        /**
         * Returns the initiatorOrigin field.
         * @return the protocol field value, empty when absent
         */
        public Optional<String> initiatorOrigin() {
            return optionalString("initiatorOrigin");
        }
        /**
         * Returns the resourceIPAddressSpace field.
         * @return the protocol field value, empty when absent
         */
        public Optional<Network.IPAddressSpace> resourceIPAddressSpace() {
            return optional(RESOURCE_IPADDRESS_SPACE);
        }
        /**
         * Returns the clientSecurityState field.
         * @return the protocol field value, empty when absent
         */
        public Optional<Network.ClientSecurityState> clientSecurityState() {
            return optional(CLIENT_SECURITY_STATE);
        }
        /**
         * Sets the corsErrorStatus field.
         * @param corsErrorStatus field value
         * @return this model
         */
        public CorsIssueDetails corsErrorStatus(Network.CorsErrorStatus corsErrorStatus) {
            set("corsErrorStatus", corsErrorStatus);
            return this;
        }
        /**
         * Sets the isWarning field.
         * @param isWarning field value
         * @return this model
         */
        public CorsIssueDetails isWarning(boolean isWarning) {
            set("isWarning", isWarning);
            return this;
        }
        /**
         * Sets the request field.
         * @param request field value
         * @return this model
         */
        public CorsIssueDetails request(Audits.AffectedRequest request) {
            set("request", request);
            return this;
        }
        /**
         * Sets the location field.
         * @param location field value
         * @return this model
         */
        public CorsIssueDetails location(Audits.SourceCodeLocation location) {
            set("location", location);
            return this;
        }
        /**
         * Sets the initiatorOrigin field.
         * @param initiatorOrigin field value
         * @return this model
         */
        public CorsIssueDetails initiatorOrigin(String initiatorOrigin) {
            set("initiatorOrigin", initiatorOrigin);
            return this;
        }
        /**
         * Sets the resourceIPAddressSpace field.
         * @param resourceIPAddressSpace field value
         * @return this model
         */
        public CorsIssueDetails resourceIPAddressSpace(Network.IPAddressSpace resourceIPAddressSpace) {
            set("resourceIPAddressSpace", resourceIPAddressSpace);
            return this;
        }
        /**
         * Sets the clientSecurityState field.
         * @param clientSecurityState field value
         * @return this model
         */
        public CorsIssueDetails clientSecurityState(Network.ClientSecurityState clientSecurityState) {
            set("clientSecurityState", clientSecurityState);
            return this;
        }
    }
    /**
     * Wire values for AttributionReportingIssueType.
     */
    public enum AttributionReportingIssueType implements CdpValue<String> {
        PERMISSIONPOLICYDISABLED("PermissionPolicyDisabled"),
        UNTRUSTWORTHYREPORTINGORIGIN("UntrustworthyReportingOrigin"),
        INSECURECONTEXT("InsecureContext"),
        INVALIDHEADER("InvalidHeader"),
        INVALIDREGISTERTRIGGERHEADER("InvalidRegisterTriggerHeader"),
        SOURCEANDTRIGGERHEADERS("SourceAndTriggerHeaders"),
        SOURCEIGNORED("SourceIgnored"),
        TRIGGERIGNORED("TriggerIgnored"),
        OSSOURCEIGNORED("OsSourceIgnored"),
        OSTRIGGERIGNORED("OsTriggerIgnored"),
        INVALIDREGISTEROSSOURCEHEADER("InvalidRegisterOsSourceHeader"),
        INVALIDREGISTEROSTRIGGERHEADER("InvalidRegisterOsTriggerHeader"),
        WEBANDOSHEADERS("WebAndOsHeaders"),
        NOWEBOROSSUPPORT("NoWebOrOsSupport"),
        NAVIGATIONREGISTRATIONWITHOUTTRANSIENTUSERACTIVATION("NavigationRegistrationWithoutTransientUserActivation"),
        INVALIDINFOHEADER("InvalidInfoHeader"),
        NOREGISTERSOURCEHEADER("NoRegisterSourceHeader"),
        NOREGISTERTRIGGERHEADER("NoRegisterTriggerHeader"),
        NOREGISTEROSSOURCEHEADER("NoRegisterOsSourceHeader"),
        NOREGISTEROSTRIGGERHEADER("NoRegisterOsTriggerHeader"),
        NAVIGATIONREGISTRATIONUNIQUESCOPEALREADYSET("NavigationRegistrationUniqueScopeAlreadySet");
        public final String value;
        AttributionReportingIssueType(String value) { this.value = value; }
        @Override public String value() { return value; }
        public static AttributionReportingIssueType of(String value) {
            for (AttributionReportingIssueType constant : values()) {
                if (constant.value.equals(value)) return constant;
            }
            throw new IllegalArgumentException("Unknown AttributionReportingIssueType value: " + value);
        }
    }
    /**
     * Wire values for SharedDictionaryError.
     */
    public enum SharedDictionaryError implements CdpValue<String> {
        USEERRORCROSSORIGINNOCORSREQUEST("UseErrorCrossOriginNoCorsRequest"),
        USEERRORDICTIONARYLOADFAILURE("UseErrorDictionaryLoadFailure"),
        USEERRORMATCHINGDICTIONARYNOTUSED("UseErrorMatchingDictionaryNotUsed"),
        USEERRORUNEXPECTEDCONTENTDICTIONARYHEADER("UseErrorUnexpectedContentDictionaryHeader"),
        WRITEERRORCOSSORIGINNOCORSREQUEST("WriteErrorCossOriginNoCorsRequest"),
        WRITEERRORDISALLOWEDBYSETTINGS("WriteErrorDisallowedBySettings"),
        WRITEERROREXPIREDRESPONSE("WriteErrorExpiredResponse"),
        WRITEERRORFEATUREDISABLED("WriteErrorFeatureDisabled"),
        WRITEERRORINSUFFICIENTRESOURCES("WriteErrorInsufficientResources"),
        WRITEERRORINVALIDMATCHFIELD("WriteErrorInvalidMatchField"),
        WRITEERRORINVALIDSTRUCTUREDHEADER("WriteErrorInvalidStructuredHeader"),
        WRITEERRORINVALIDTTLFIELD("WriteErrorInvalidTTLField"),
        WRITEERRORNAVIGATIONREQUEST("WriteErrorNavigationRequest"),
        WRITEERRORNOMATCHFIELD("WriteErrorNoMatchField"),
        WRITEERRORNONINTEGERTTLFIELD("WriteErrorNonIntegerTTLField"),
        WRITEERRORNONLISTMATCHDESTFIELD("WriteErrorNonListMatchDestField"),
        WRITEERRORNONSECURECONTEXT("WriteErrorNonSecureContext"),
        WRITEERRORNONSTRINGIDFIELD("WriteErrorNonStringIdField"),
        WRITEERRORNONSTRINGINMATCHDESTLIST("WriteErrorNonStringInMatchDestList"),
        WRITEERRORINVALIDMATCHDESTLIST("WriteErrorInvalidMatchDestList"),
        WRITEERRORNONSTRINGMATCHFIELD("WriteErrorNonStringMatchField"),
        WRITEERRORNONTOKENTYPEFIELD("WriteErrorNonTokenTypeField"),
        WRITEERRORREQUESTABORTED("WriteErrorRequestAborted"),
        WRITEERRORSHUTTINGDOWN("WriteErrorShuttingDown"),
        WRITEERRORTOOLONGIDFIELD("WriteErrorTooLongIdField"),
        WRITEERRORUNSUPPORTEDTYPE("WriteErrorUnsupportedType");
        public final String value;
        SharedDictionaryError(String value) { this.value = value; }
        @Override public String value() { return value; }
        public static SharedDictionaryError of(String value) {
            for (SharedDictionaryError constant : values()) {
                if (constant.value.equals(value)) return constant;
            }
            throw new IllegalArgumentException("Unknown SharedDictionaryError value: " + value);
        }
    }
    /**
     * Wire values for SRIMessageSignatureError.
     */
    public enum SRIMessageSignatureError implements CdpValue<String> {
        MISSINGSIGNATUREHEADER("MissingSignatureHeader"),
        MISSINGSIGNATUREINPUTHEADER("MissingSignatureInputHeader"),
        INVALIDSIGNATUREHEADER("InvalidSignatureHeader"),
        INVALIDSIGNATUREINPUTHEADER("InvalidSignatureInputHeader"),
        SIGNATUREHEADERVALUEISNOTBYTESEQUENCE("SignatureHeaderValueIsNotByteSequence"),
        SIGNATUREHEADERVALUEISPARAMETERIZED("SignatureHeaderValueIsParameterized"),
        SIGNATUREHEADERVALUEISINCORRECTLENGTH("SignatureHeaderValueIsIncorrectLength"),
        SIGNATUREINPUTHEADERMISSINGLABEL("SignatureInputHeaderMissingLabel"),
        SIGNATUREINPUTHEADERVALUENOTINNERLIST("SignatureInputHeaderValueNotInnerList"),
        SIGNATUREINPUTHEADERVALUEMISSINGCOMPONENTS("SignatureInputHeaderValueMissingComponents"),
        SIGNATUREINPUTHEADERINVALIDCOMPONENTTYPE("SignatureInputHeaderInvalidComponentType"),
        SIGNATUREINPUTHEADERINVALIDCOMPONENTNAME("SignatureInputHeaderInvalidComponentName"),
        SIGNATUREINPUTHEADERINVALIDHEADERCOMPONENTPARAMETER("SignatureInputHeaderInvalidHeaderComponentParameter"),
        SIGNATUREINPUTHEADERINVALIDDERIVEDCOMPONENTPARAMETER("SignatureInputHeaderInvalidDerivedComponentParameter"),
        SIGNATUREINPUTHEADERKEYIDLENGTH("SignatureInputHeaderKeyIdLength"),
        SIGNATUREINPUTHEADERINVALIDPARAMETER("SignatureInputHeaderInvalidParameter"),
        SIGNATUREINPUTHEADERMISSINGREQUIREDPARAMETERS("SignatureInputHeaderMissingRequiredParameters"),
        VALIDATIONFAILEDSIGNATUREEXPIRED("ValidationFailedSignatureExpired"),
        VALIDATIONFAILEDINVALIDLENGTH("ValidationFailedInvalidLength"),
        VALIDATIONFAILEDSIGNATUREMISMATCH("ValidationFailedSignatureMismatch"),
        VALIDATIONFAILEDINTEGRITYMISMATCH("ValidationFailedIntegrityMismatch"),
        SIGNATUREBASEUNKNOWNDERIVEDCOMPONENT("SignatureBaseUnknownDerivedComponent"),
        SIGNATUREBASEMISSINGHEADER("SignatureBaseMissingHeader"),
        SIGNATUREBASEINVALIDUNENCODEDDIGEST("SignatureBaseInvalidUnencodedDigest"),
        SIGNATUREBASEUNSUPPORTEDCOMPONENT("SignatureBaseUnsupportedComponent");
        public final String value;
        SRIMessageSignatureError(String value) { this.value = value; }
        @Override public String value() { return value; }
        public static SRIMessageSignatureError of(String value) {
            for (SRIMessageSignatureError constant : values()) {
                if (constant.value.equals(value)) return constant;
            }
            throw new IllegalArgumentException("Unknown SRIMessageSignatureError value: " + value);
        }
    }
    /**
     * Wire values for UnencodedDigestError.
     */
    public enum UnencodedDigestError implements CdpValue<String> {
        MALFORMEDDICTIONARY("MalformedDictionary"),
        UNKNOWNALGORITHM("UnknownAlgorithm"),
        INCORRECTDIGESTTYPE("IncorrectDigestType"),
        INCORRECTDIGESTLENGTH("IncorrectDigestLength");
        public final String value;
        UnencodedDigestError(String value) { this.value = value; }
        @Override public String value() { return value; }
        public static UnencodedDigestError of(String value) {
            for (UnencodedDigestError constant : values()) {
                if (constant.value.equals(value)) return constant;
            }
            throw new IllegalArgumentException("Unknown UnencodedDigestError value: " + value);
        }
    }
    /**
     * Wire values for ConnectionAllowlistError.
     */
    public enum ConnectionAllowlistError implements CdpValue<String> {
        INVALIDHEADER("InvalidHeader"),
        MORETHANONELIST("MoreThanOneList"),
        ITEMNOTINNERLIST("ItemNotInnerList"),
        INVALIDALLOWLISTITEMTYPE("InvalidAllowlistItemType"),
        REPORTINGENDPOINTNOTTOKEN("ReportingEndpointNotToken"),
        INVALIDURLPATTERN("InvalidUrlPattern");
        public final String value;
        ConnectionAllowlistError(String value) { this.value = value; }
        @Override public String value() { return value; }
        public static ConnectionAllowlistError of(String value) {
            for (ConnectionAllowlistError constant : values()) {
                if (constant.value.equals(value)) return constant;
            }
            throw new IllegalArgumentException("Unknown ConnectionAllowlistError value: " + value);
        }
    }
    /**
     * Details for issues around &quot;Attribution Reporting API&quot; usage. Explainer: https://github.com/WICG/attribution-reporting-api
     */
    public static final class AttributionReportingIssueDetails extends CdpObject {
        public AttributionReportingIssueDetails() {}
        private AttributionReportingIssueDetails(Map<String, Object> values) { super(values); }
        public static AttributionReportingIssueDetails fromMap(Map<String, Object> values) {
            return new AttributionReportingIssueDetails(values);
        }
        /**
         * Returns the violationType field.
         * @return the protocol field value
         */
        public Audits.AttributionReportingIssueType violationType() {
            return require(VIOLATION_TYPE);
        }
        /**
         * Returns the request field.
         * @return the protocol field value, empty when absent
         */
        public Optional<Audits.AffectedRequest> request() {
            return optional(REQUEST);
        }
        /**
         * Returns the violatingNodeId field.
         * @return the protocol field value, empty when absent
         */
        public Optional<DOM.BackendNodeId> violatingNodeId() {
            return optional(VIOLATING_NODE_ID);
        }
        /**
         * Returns the invalidParameter field.
         * @return the protocol field value, empty when absent
         */
        public Optional<String> invalidParameter() {
            return optionalString("invalidParameter");
        }
        /**
         * Sets the violationType field.
         * @param violationType field value
         * @return this model
         */
        public AttributionReportingIssueDetails violationType(Audits.AttributionReportingIssueType violationType) {
            set("violationType", violationType);
            return this;
        }
        /**
         * Sets the request field.
         * @param request field value
         * @return this model
         */
        public AttributionReportingIssueDetails request(Audits.AffectedRequest request) {
            set("request", request);
            return this;
        }
        /**
         * Sets the violatingNodeId field.
         * @param violatingNodeId field value
         * @return this model
         */
        public AttributionReportingIssueDetails violatingNodeId(DOM.BackendNodeId violatingNodeId) {
            set("violatingNodeId", violatingNodeId);
            return this;
        }
        /**
         * Sets the invalidParameter field.
         * @param invalidParameter field value
         * @return this model
         */
        public AttributionReportingIssueDetails invalidParameter(String invalidParameter) {
            set("invalidParameter", invalidParameter);
            return this;
        }
    }
    /**
     * Details for issues about documents in Quirks Mode or Limited Quirks Mode that affects page layouting.
     */
    public static final class QuirksModeIssueDetails extends CdpObject {
        public QuirksModeIssueDetails() {}
        private QuirksModeIssueDetails(Map<String, Object> values) { super(values); }
        public static QuirksModeIssueDetails fromMap(Map<String, Object> values) {
            return new QuirksModeIssueDetails(values);
        }
        /**
         * If false, it means the document&#x27;s mode is &quot;quirks&quot; instead of &quot;limited-quirks&quot;.
         * @return the protocol field value
         */
        public boolean isLimitedQuirksMode() {
            return requireBoolean("isLimitedQuirksMode");
        }
        /**
         * Returns the documentNodeId field.
         * @return the protocol field value
         */
        public DOM.BackendNodeId documentNodeId() {
            return require(DOCUMENT_NODE_ID);
        }
        /**
         * Returns the url field.
         * @return the protocol field value
         */
        public String url() {
            return requireString("url");
        }
        /**
         * Returns the frameId field.
         * @return the protocol field value
         */
        public Page.FrameId frameId() {
            return require(FRAME_ID);
        }
        /**
         * Returns the loaderId field.
         * @return the protocol field value
         */
        public Network.LoaderId loaderId() {
            return require(LOADER_ID);
        }
        /**
         * If false, it means the document&#x27;s mode is &quot;quirks&quot; instead of &quot;limited-quirks&quot;.
         * @param isLimitedQuirksMode field value
         * @return this model
         */
        public QuirksModeIssueDetails isLimitedQuirksMode(boolean isLimitedQuirksMode) {
            set("isLimitedQuirksMode", isLimitedQuirksMode);
            return this;
        }
        /**
         * Sets the documentNodeId field.
         * @param documentNodeId field value
         * @return this model
         */
        public QuirksModeIssueDetails documentNodeId(DOM.BackendNodeId documentNodeId) {
            set("documentNodeId", documentNodeId);
            return this;
        }
        /**
         * Sets the url field.
         * @param url field value
         * @return this model
         */
        public QuirksModeIssueDetails url(String url) {
            set("url", url);
            return this;
        }
        /**
         * Sets the frameId field.
         * @param frameId field value
         * @return this model
         */
        public QuirksModeIssueDetails frameId(Page.FrameId frameId) {
            set("frameId", frameId);
            return this;
        }
        /**
         * Sets the loaderId field.
         * @param loaderId field value
         * @return this model
         */
        public QuirksModeIssueDetails loaderId(Network.LoaderId loaderId) {
            set("loaderId", loaderId);
            return this;
        }
    }
    /**
     * @deprecated Deprecated by the Chromium DevTools Protocol.
     */
    @Deprecated
    public static final class NavigatorUserAgentIssueDetails extends CdpObject {
        public NavigatorUserAgentIssueDetails() {}
        private NavigatorUserAgentIssueDetails(Map<String, Object> values) { super(values); }
        public static NavigatorUserAgentIssueDetails fromMap(Map<String, Object> values) {
            return new NavigatorUserAgentIssueDetails(values);
        }
        /**
         * Returns the url field.
         * @return the protocol field value
         */
        public String url() {
            return requireString("url");
        }
        /**
         * Returns the location field.
         * @return the protocol field value, empty when absent
         */
        public Optional<Audits.SourceCodeLocation> location() {
            return optional(LOCATION);
        }
        /**
         * Sets the url field.
         * @param url field value
         * @return this model
         */
        public NavigatorUserAgentIssueDetails url(String url) {
            set("url", url);
            return this;
        }
        /**
         * Sets the location field.
         * @param location field value
         * @return this model
         */
        public NavigatorUserAgentIssueDetails location(Audits.SourceCodeLocation location) {
            set("location", location);
            return this;
        }
    }
    /**
     */
    public static final class SharedDictionaryIssueDetails extends CdpObject {
        public SharedDictionaryIssueDetails() {}
        private SharedDictionaryIssueDetails(Map<String, Object> values) { super(values); }
        public static SharedDictionaryIssueDetails fromMap(Map<String, Object> values) {
            return new SharedDictionaryIssueDetails(values);
        }
        /**
         * Returns the sharedDictionaryError field.
         * @return the protocol field value
         */
        public Audits.SharedDictionaryError sharedDictionaryError() {
            return require(SHARED_DICTIONARY_ERROR);
        }
        /**
         * Returns the request field.
         * @return the protocol field value
         */
        public Audits.AffectedRequest request() {
            return require(REQUEST);
        }
        /**
         * Sets the sharedDictionaryError field.
         * @param sharedDictionaryError field value
         * @return this model
         */
        public SharedDictionaryIssueDetails sharedDictionaryError(Audits.SharedDictionaryError sharedDictionaryError) {
            set("sharedDictionaryError", sharedDictionaryError);
            return this;
        }
        /**
         * Sets the request field.
         * @param request field value
         * @return this model
         */
        public SharedDictionaryIssueDetails request(Audits.AffectedRequest request) {
            set("request", request);
            return this;
        }
    }
    /**
     */
    public static final class SRIMessageSignatureIssueDetails extends CdpObject {
        public SRIMessageSignatureIssueDetails() {}
        private SRIMessageSignatureIssueDetails(Map<String, Object> values) { super(values); }
        public static SRIMessageSignatureIssueDetails fromMap(Map<String, Object> values) {
            return new SRIMessageSignatureIssueDetails(values);
        }
        /**
         * Returns the error field.
         * @return the protocol field value
         */
        public Audits.SRIMessageSignatureError error() {
            return require(ERROR);
        }
        /**
         * Returns the signatureBase field.
         * @return the protocol field value
         */
        public String signatureBase() {
            return requireString("signatureBase");
        }
        /**
         * Returns the integrityAssertions field.
         * @return the protocol field value
         */
        public java.util.List<String> integrityAssertions() {
            return require(INTEGRITY_ASSERTIONS);
        }
        /**
         * Returns the request field.
         * @return the protocol field value
         */
        public Audits.AffectedRequest request() {
            return require(REQUEST);
        }
        /**
         * Sets the error field.
         * @param error field value
         * @return this model
         */
        public SRIMessageSignatureIssueDetails error(Audits.SRIMessageSignatureError error) {
            set("error", error);
            return this;
        }
        /**
         * Sets the signatureBase field.
         * @param signatureBase field value
         * @return this model
         */
        public SRIMessageSignatureIssueDetails signatureBase(String signatureBase) {
            set("signatureBase", signatureBase);
            return this;
        }
        /**
         * Sets the integrityAssertions field.
         * @param integrityAssertions field value
         * @return this model
         */
        public SRIMessageSignatureIssueDetails integrityAssertions(java.util.List<String> integrityAssertions) {
            set("integrityAssertions", integrityAssertions);
            return this;
        }
        /**
         * Sets the request field.
         * @param request field value
         * @return this model
         */
        public SRIMessageSignatureIssueDetails request(Audits.AffectedRequest request) {
            set("request", request);
            return this;
        }
    }
    /**
     */
    public static final class UnencodedDigestIssueDetails extends CdpObject {
        public UnencodedDigestIssueDetails() {}
        private UnencodedDigestIssueDetails(Map<String, Object> values) { super(values); }
        public static UnencodedDigestIssueDetails fromMap(Map<String, Object> values) {
            return new UnencodedDigestIssueDetails(values);
        }
        /**
         * Returns the error field.
         * @return the protocol field value
         */
        public Audits.UnencodedDigestError error() {
            return require(ERROR_2);
        }
        /**
         * Returns the request field.
         * @return the protocol field value
         */
        public Audits.AffectedRequest request() {
            return require(REQUEST);
        }
        /**
         * Sets the error field.
         * @param error field value
         * @return this model
         */
        public UnencodedDigestIssueDetails error(Audits.UnencodedDigestError error) {
            set("error", error);
            return this;
        }
        /**
         * Sets the request field.
         * @param request field value
         * @return this model
         */
        public UnencodedDigestIssueDetails request(Audits.AffectedRequest request) {
            set("request", request);
            return this;
        }
    }
    /**
     */
    public static final class ConnectionAllowlistIssueDetails extends CdpObject {
        public ConnectionAllowlistIssueDetails() {}
        private ConnectionAllowlistIssueDetails(Map<String, Object> values) { super(values); }
        public static ConnectionAllowlistIssueDetails fromMap(Map<String, Object> values) {
            return new ConnectionAllowlistIssueDetails(values);
        }
        /**
         * Returns the error field.
         * @return the protocol field value
         */
        public Audits.ConnectionAllowlistError error() {
            return require(ERROR_3);
        }
        /**
         * Returns the request field.
         * @return the protocol field value
         */
        public Audits.AffectedRequest request() {
            return require(REQUEST);
        }
        /**
         * Sets the error field.
         * @param error field value
         * @return this model
         */
        public ConnectionAllowlistIssueDetails error(Audits.ConnectionAllowlistError error) {
            set("error", error);
            return this;
        }
        /**
         * Sets the request field.
         * @param request field value
         * @return this model
         */
        public ConnectionAllowlistIssueDetails request(Audits.AffectedRequest request) {
            set("request", request);
            return this;
        }
    }
    /**
     * Wire values for GenericIssueErrorType.
     */
    public enum GenericIssueErrorType implements CdpValue<String> {
        FORMLABELFORNAMEERROR("FormLabelForNameError"),
        FORMDUPLICATEIDFORINPUTERROR("FormDuplicateIdForInputError"),
        FORMINPUTWITHNOLABELERROR("FormInputWithNoLabelError"),
        FORMAUTOCOMPLETEATTRIBUTEEMPTYERROR("FormAutocompleteAttributeEmptyError"),
        FORMEMPTYIDANDNAMEATTRIBUTESFORINPUTERROR("FormEmptyIdAndNameAttributesForInputError"),
        FORMARIALABELLEDBYTONONEXISTINGIDERROR("FormAriaLabelledByToNonExistingIdError"),
        FORMINPUTASSIGNEDAUTOCOMPLETEVALUETOIDORNAMEATTRIBUTEERROR("FormInputAssignedAutocompleteValueToIdOrNameAttributeError"),
        FORMLABELHASNEITHERFORNORNESTEDINPUTERROR("FormLabelHasNeitherForNorNestedInputError"),
        FORMLABELFORMATCHESNONEXISTINGIDERROR("FormLabelForMatchesNonExistingIdError"),
        FORMINPUTHASWRONGBUTWELLINTENDEDAUTOCOMPLETEVALUEERROR("FormInputHasWrongButWellIntendedAutocompleteValueError"),
        RESPONSEWASBLOCKEDBYORB("ResponseWasBlockedByORB"),
        NAVIGATIONENTRYMARKEDSKIPPABLE("NavigationEntryMarkedSkippable"),
        BACKUINAVIGATIONWOULDSKIPAD("BackUINavigationWouldSkipAd"),
        AUTOFILLANDMANUALTEXTPOLICYCONTROLLEDFEATURESINFO("AutofillAndManualTextPolicyControlledFeaturesInfo"),
        AUTOFILLPOLICYCONTROLLEDFEATUREINFO("AutofillPolicyControlledFeatureInfo"),
        MANUALTEXTPOLICYCONTROLLEDFEATUREINFO("ManualTextPolicyControlledFeatureInfo"),
        FORMMODELCONTEXTPARAMETERMISSINGTITLEANDDESCRIPTION("FormModelContextParameterMissingTitleAndDescription"),
        FORMMODELCONTEXTMISSINGTOOLNAME("FormModelContextMissingToolName"),
        FORMMODELCONTEXTMISSINGTOOLDESCRIPTION("FormModelContextMissingToolDescription"),
        FORMMODELCONTEXTREQUIREDPARAMETERMISSINGNAME("FormModelContextRequiredParameterMissingName"),
        FORMMODELCONTEXTPARAMETERMISSINGNAME("FormModelContextParameterMissingName");
        public final String value;
        GenericIssueErrorType(String value) { this.value = value; }
        @Override public String value() { return value; }
        public static GenericIssueErrorType of(String value) {
            for (GenericIssueErrorType constant : values()) {
                if (constant.value.equals(value)) return constant;
            }
            throw new IllegalArgumentException("Unknown GenericIssueErrorType value: " + value);
        }
    }
    /**
     * Depending on the concrete errorType, different properties are set.
     */
    public static final class GenericIssueDetails extends CdpObject {
        public GenericIssueDetails() {}
        private GenericIssueDetails(Map<String, Object> values) { super(values); }
        public static GenericIssueDetails fromMap(Map<String, Object> values) {
            return new GenericIssueDetails(values);
        }
        /**
         * Issues with the same errorType are aggregated in the frontend.
         * @return the protocol field value
         */
        public Audits.GenericIssueErrorType errorType() {
            return require(ERROR_TYPE);
        }
        /**
         * Returns the frameId field.
         * @return the protocol field value, empty when absent
         */
        public Optional<Page.FrameId> frameId() {
            return optional(FRAME_ID);
        }
        /**
         * Returns the violatingNodeId field.
         * @return the protocol field value, empty when absent
         */
        public Optional<DOM.BackendNodeId> violatingNodeId() {
            return optional(VIOLATING_NODE_ID);
        }
        /**
         * Returns the violatingNodeAttribute field.
         * @return the protocol field value, empty when absent
         */
        public Optional<String> violatingNodeAttribute() {
            return optionalString("violatingNodeAttribute");
        }
        /**
         * Returns the request field.
         * @return the protocol field value, empty when absent
         */
        public Optional<Audits.AffectedRequest> request() {
            return optional(REQUEST);
        }
        /**
         * Issues with the same errorType are aggregated in the frontend.
         * @param errorType field value
         * @return this model
         */
        public GenericIssueDetails errorType(Audits.GenericIssueErrorType errorType) {
            set("errorType", errorType);
            return this;
        }
        /**
         * Sets the frameId field.
         * @param frameId field value
         * @return this model
         */
        public GenericIssueDetails frameId(Page.FrameId frameId) {
            set("frameId", frameId);
            return this;
        }
        /**
         * Sets the violatingNodeId field.
         * @param violatingNodeId field value
         * @return this model
         */
        public GenericIssueDetails violatingNodeId(DOM.BackendNodeId violatingNodeId) {
            set("violatingNodeId", violatingNodeId);
            return this;
        }
        /**
         * Sets the violatingNodeAttribute field.
         * @param violatingNodeAttribute field value
         * @return this model
         */
        public GenericIssueDetails violatingNodeAttribute(String violatingNodeAttribute) {
            set("violatingNodeAttribute", violatingNodeAttribute);
            return this;
        }
        /**
         * Sets the request field.
         * @param request field value
         * @return this model
         */
        public GenericIssueDetails request(Audits.AffectedRequest request) {
            set("request", request);
            return this;
        }
    }
    /**
     * This issue tracks information needed to print a deprecation message. https://source.chromium.org/chromium/chromium/src/+/main:third_party/blink/renderer/core/frame/third_party/blink/renderer/core/frame/deprecation/README.md
     */
    public static final class DeprecationIssueDetails extends CdpObject {
        public DeprecationIssueDetails() {}
        private DeprecationIssueDetails(Map<String, Object> values) { super(values); }
        public static DeprecationIssueDetails fromMap(Map<String, Object> values) {
            return new DeprecationIssueDetails(values);
        }
        /**
         * Returns the affectedFrame field.
         * @return the protocol field value, empty when absent
         */
        public Optional<Audits.AffectedFrame> affectedFrame() {
            return optional(AFFECTED_FRAME);
        }
        /**
         * Returns the sourceCodeLocation field.
         * @return the protocol field value
         */
        public Audits.SourceCodeLocation sourceCodeLocation() {
            return require(SOURCE_CODE_LOCATION);
        }
        /**
         * One of the deprecation names from third_party/blink/renderer/core/frame/deprecation/deprecation.json5
         * @return the protocol field value
         */
        public String type() {
            return requireString("type");
        }
        /**
         * Sets the affectedFrame field.
         * @param affectedFrame field value
         * @return this model
         */
        public DeprecationIssueDetails affectedFrame(Audits.AffectedFrame affectedFrame) {
            set("affectedFrame", affectedFrame);
            return this;
        }
        /**
         * Sets the sourceCodeLocation field.
         * @param sourceCodeLocation field value
         * @return this model
         */
        public DeprecationIssueDetails sourceCodeLocation(Audits.SourceCodeLocation sourceCodeLocation) {
            set("sourceCodeLocation", sourceCodeLocation);
            return this;
        }
        /**
         * One of the deprecation names from third_party/blink/renderer/core/frame/deprecation/deprecation.json5
         * @param type field value
         * @return this model
         */
        public DeprecationIssueDetails type(String type) {
            set("type", type);
            return this;
        }
    }
    /**
     * This issue warns about sites in the redirect chain of a finished navigation that may be flagged as trackers and have their state cleared if they don&#x27;t receive a user interaction. Note that in this context &#x27;site&#x27; means eTLD+1. For example, if the URL {@code https://example.test:80/bounce} was in the redirect chain, the site reported would be {@code example.test}.
     */
    public static final class BounceTrackingIssueDetails extends CdpObject {
        public BounceTrackingIssueDetails() {}
        private BounceTrackingIssueDetails(Map<String, Object> values) { super(values); }
        public static BounceTrackingIssueDetails fromMap(Map<String, Object> values) {
            return new BounceTrackingIssueDetails(values);
        }
        /**
         * Returns the trackingSites field.
         * @return the protocol field value
         */
        public java.util.List<String> trackingSites() {
            return require(TRACKING_SITES);
        }
        /**
         * Sets the trackingSites field.
         * @param trackingSites field value
         * @return this model
         */
        public BounceTrackingIssueDetails trackingSites(java.util.List<String> trackingSites) {
            set("trackingSites", trackingSites);
            return this;
        }
    }
    /**
     * This issue warns about third-party sites that are accessing cookies on the current page, and have been permitted due to having a global metadata grant. Note that in this context &#x27;site&#x27; means eTLD+1. For example, if the URL {@code https://example.test:80/web_page} was accessing cookies, the site reported would be {@code example.test}.
     */
    public static final class CookieDeprecationMetadataIssueDetails extends CdpObject {
        public CookieDeprecationMetadataIssueDetails() {}
        private CookieDeprecationMetadataIssueDetails(Map<String, Object> values) { super(values); }
        public static CookieDeprecationMetadataIssueDetails fromMap(Map<String, Object> values) {
            return new CookieDeprecationMetadataIssueDetails(values);
        }
        /**
         * Returns the allowedSites field.
         * @return the protocol field value
         */
        public java.util.List<String> allowedSites() {
            return require(ALLOWED_SITES);
        }
        /**
         * Returns the optOutPercentage field.
         * @return the protocol field value
         */
        public double optOutPercentage() {
            return requireDouble("optOutPercentage");
        }
        /**
         * Returns the isOptOutTopLevel field.
         * @return the protocol field value
         */
        public boolean isOptOutTopLevel() {
            return requireBoolean("isOptOutTopLevel");
        }
        /**
         * Returns the operation field.
         * @return the protocol field value
         */
        public Audits.CookieOperation operation() {
            return require(OPERATION);
        }
        /**
         * Sets the allowedSites field.
         * @param allowedSites field value
         * @return this model
         */
        public CookieDeprecationMetadataIssueDetails allowedSites(java.util.List<String> allowedSites) {
            set("allowedSites", allowedSites);
            return this;
        }
        /**
         * Sets the optOutPercentage field.
         * @param optOutPercentage field value
         * @return this model
         */
        public CookieDeprecationMetadataIssueDetails optOutPercentage(double optOutPercentage) {
            set("optOutPercentage", optOutPercentage);
            return this;
        }
        /**
         * Sets the isOptOutTopLevel field.
         * @param isOptOutTopLevel field value
         * @return this model
         */
        public CookieDeprecationMetadataIssueDetails isOptOutTopLevel(boolean isOptOutTopLevel) {
            set("isOptOutTopLevel", isOptOutTopLevel);
            return this;
        }
        /**
         * Sets the operation field.
         * @param operation field value
         * @return this model
         */
        public CookieDeprecationMetadataIssueDetails operation(Audits.CookieOperation operation) {
            set("operation", operation);
            return this;
        }
    }
    /**
     * Wire values for ClientHintIssueReason.
     */
    public enum ClientHintIssueReason implements CdpValue<String> {
        METATAGALLOWLISTINVALIDORIGIN("MetaTagAllowListInvalidOrigin"),
        METATAGMODIFIEDHTML("MetaTagModifiedHTML");
        public final String value;
        ClientHintIssueReason(String value) { this.value = value; }
        @Override public String value() { return value; }
        public static ClientHintIssueReason of(String value) {
            for (ClientHintIssueReason constant : values()) {
                if (constant.value.equals(value)) return constant;
            }
            throw new IllegalArgumentException("Unknown ClientHintIssueReason value: " + value);
        }
    }
    /**
     */
    public static final class FederatedAuthRequestIssueDetails extends CdpObject {
        public FederatedAuthRequestIssueDetails() {}
        private FederatedAuthRequestIssueDetails(Map<String, Object> values) { super(values); }
        public static FederatedAuthRequestIssueDetails fromMap(Map<String, Object> values) {
            return new FederatedAuthRequestIssueDetails(values);
        }
        /**
         * Returns the federatedAuthRequestIssueReason field.
         * @return the protocol field value
         */
        public Audits.FederatedAuthRequestIssueReason federatedAuthRequestIssueReason() {
            return require(FEDERATED_AUTH_REQUEST_ISSUE_REASON);
        }
        /**
         * Sets the federatedAuthRequestIssueReason field.
         * @param federatedAuthRequestIssueReason field value
         * @return this model
         */
        public FederatedAuthRequestIssueDetails federatedAuthRequestIssueReason(Audits.FederatedAuthRequestIssueReason federatedAuthRequestIssueReason) {
            set("federatedAuthRequestIssueReason", federatedAuthRequestIssueReason);
            return this;
        }
    }
    /**
     * Represents the failure reason when a federated authentication reason fails. Should be updated alongside RequestIdTokenStatus in third_party/blink/public/mojom/devtools/inspector_issue.mojom to include all cases except for success.
     */
    public enum FederatedAuthRequestIssueReason implements CdpValue<String> {
        SHOULDEMBARGO("ShouldEmbargo"),
        TOOMANYREQUESTS("TooManyRequests"),
        WELLKNOWNHTTPNOTFOUND("WellKnownHttpNotFound"),
        WELLKNOWNNORESPONSE("WellKnownNoResponse"),
        WELLKNOWNINVALIDRESPONSE("WellKnownInvalidResponse"),
        WELLKNOWNLISTEMPTY("WellKnownListEmpty"),
        WELLKNOWNINVALIDCONTENTTYPE("WellKnownInvalidContentType"),
        CONFIGNOTINWELLKNOWN("ConfigNotInWellKnown"),
        WELLKNOWNTOOBIG("WellKnownTooBig"),
        CONFIGHTTPNOTFOUND("ConfigHttpNotFound"),
        CONFIGNORESPONSE("ConfigNoResponse"),
        CONFIGINVALIDRESPONSE("ConfigInvalidResponse"),
        CONFIGINVALIDCONTENTTYPE("ConfigInvalidContentType"),
        IDPNOTPOTENTIALLYTRUSTWORTHY("IdpNotPotentiallyTrustworthy"),
        DISABLEDINSETTINGS("DisabledInSettings"),
        DISABLEDINFLAGS("DisabledInFlags"),
        ERRORFETCHINGSIGNIN("ErrorFetchingSignin"),
        INVALIDSIGNINRESPONSE("InvalidSigninResponse"),
        ACCOUNTSHTTPNOTFOUND("AccountsHttpNotFound"),
        ACCOUNTSNORESPONSE("AccountsNoResponse"),
        ACCOUNTSINVALIDRESPONSE("AccountsInvalidResponse"),
        ACCOUNTSLISTEMPTY("AccountsListEmpty"),
        ACCOUNTSINVALIDCONTENTTYPE("AccountsInvalidContentType"),
        IDTOKENHTTPNOTFOUND("IdTokenHttpNotFound"),
        IDTOKENNORESPONSE("IdTokenNoResponse"),
        IDTOKENINVALIDRESPONSE("IdTokenInvalidResponse"),
        IDTOKENIDPERRORRESPONSE("IdTokenIdpErrorResponse"),
        IDTOKENCROSSSITEIDPERRORRESPONSE("IdTokenCrossSiteIdpErrorResponse"),
        IDTOKENINVALIDREQUEST("IdTokenInvalidRequest"),
        IDTOKENINVALIDCONTENTTYPE("IdTokenInvalidContentType"),
        ERRORIDTOKEN("ErrorIdToken"),
        CANCELED("Canceled"),
        RPPAGENOTVISIBLE("RpPageNotVisible"),
        SILENTMEDIATIONFAILURE("SilentMediationFailure"),
        NOTSIGNEDINWITHIDP("NotSignedInWithIdp"),
        MISSINGTRANSIENTUSERACTIVATION("MissingTransientUserActivation"),
        REPLACEDBYACTIVEMODE("ReplacedByActiveMode"),
        RELYINGPARTYORIGINISOPAQUE("RelyingPartyOriginIsOpaque"),
        TYPENOTMATCHING("TypeNotMatching"),
        UIDISMISSEDNOEMBARGO("UiDismissedNoEmbargo"),
        CORSERROR("CorsError"),
        SUPPRESSEDBYSEGMENTATIONPLATFORM("SuppressedBySegmentationPlatform");
        public final String value;
        FederatedAuthRequestIssueReason(String value) { this.value = value; }
        @Override public String value() { return value; }
        public static FederatedAuthRequestIssueReason of(String value) {
            for (FederatedAuthRequestIssueReason constant : values()) {
                if (constant.value.equals(value)) return constant;
            }
            throw new IllegalArgumentException("Unknown FederatedAuthRequestIssueReason value: " + value);
        }
    }
    /**
     */
    public static final class FederatedAuthUserInfoRequestIssueDetails extends CdpObject {
        public FederatedAuthUserInfoRequestIssueDetails() {}
        private FederatedAuthUserInfoRequestIssueDetails(Map<String, Object> values) { super(values); }
        public static FederatedAuthUserInfoRequestIssueDetails fromMap(Map<String, Object> values) {
            return new FederatedAuthUserInfoRequestIssueDetails(values);
        }
        /**
         * Returns the federatedAuthUserInfoRequestIssueReason field.
         * @return the protocol field value
         */
        public Audits.FederatedAuthUserInfoRequestIssueReason federatedAuthUserInfoRequestIssueReason() {
            return require(FEDERATED_AUTH_USER_INFO_REQUEST_ISSUE_REASON);
        }
        /**
         * Sets the federatedAuthUserInfoRequestIssueReason field.
         * @param federatedAuthUserInfoRequestIssueReason field value
         * @return this model
         */
        public FederatedAuthUserInfoRequestIssueDetails federatedAuthUserInfoRequestIssueReason(Audits.FederatedAuthUserInfoRequestIssueReason federatedAuthUserInfoRequestIssueReason) {
            set("federatedAuthUserInfoRequestIssueReason", federatedAuthUserInfoRequestIssueReason);
            return this;
        }
    }
    /**
     * Represents the failure reason when a getUserInfo() call fails. Should be updated alongside FederatedAuthUserInfoRequestResult in third_party/blink/public/mojom/devtools/inspector_issue.mojom.
     */
    public enum FederatedAuthUserInfoRequestIssueReason implements CdpValue<String> {
        NOTSAMEORIGIN("NotSameOrigin"),
        NOTIFRAME("NotIframe"),
        NOTPOTENTIALLYTRUSTWORTHY("NotPotentiallyTrustworthy"),
        NOAPIPERMISSION("NoApiPermission"),
        NOTSIGNEDINWITHIDP("NotSignedInWithIdp"),
        NOACCOUNTSHARINGPERMISSION("NoAccountSharingPermission"),
        INVALIDCONFIGORWELLKNOWN("InvalidConfigOrWellKnown"),
        INVALIDACCOUNTSRESPONSE("InvalidAccountsResponse"),
        NORETURNINGUSERFROMFETCHEDACCOUNTS("NoReturningUserFromFetchedAccounts");
        public final String value;
        FederatedAuthUserInfoRequestIssueReason(String value) { this.value = value; }
        @Override public String value() { return value; }
        public static FederatedAuthUserInfoRequestIssueReason of(String value) {
            for (FederatedAuthUserInfoRequestIssueReason constant : values()) {
                if (constant.value.equals(value)) return constant;
            }
            throw new IllegalArgumentException("Unknown FederatedAuthUserInfoRequestIssueReason value: " + value);
        }
    }
    /**
     */
    public static final class EmailVerificationRequestIssueDetails extends CdpObject {
        public EmailVerificationRequestIssueDetails() {}
        private EmailVerificationRequestIssueDetails(Map<String, Object> values) { super(values); }
        public static EmailVerificationRequestIssueDetails fromMap(Map<String, Object> values) {
            return new EmailVerificationRequestIssueDetails(values);
        }
        /**
         * Returns the emailVerificationRequestIssueReason field.
         * @return the protocol field value
         */
        public Audits.EmailVerificationRequestIssueReason emailVerificationRequestIssueReason() {
            return require(EMAIL_VERIFICATION_REQUEST_ISSUE_REASON);
        }
        /**
         * Sets the emailVerificationRequestIssueReason field.
         * @param emailVerificationRequestIssueReason field value
         * @return this model
         */
        public EmailVerificationRequestIssueDetails emailVerificationRequestIssueReason(Audits.EmailVerificationRequestIssueReason emailVerificationRequestIssueReason) {
            set("emailVerificationRequestIssueReason", emailVerificationRequestIssueReason);
            return this;
        }
    }
    /**
     * Represents the failure reason when an email verification request fails. Should be updated alongside EmailVerificationRequestResult in third_party/blink/public/mojom/devtools/inspector_issue.mojom.
     */
    public enum EmailVerificationRequestIssueReason implements CdpValue<String> {
        INVALIDEMAIL("InvalidEmail"),
        DNSFETCHFAILED("DnsFetchFailed"),
        DNSINVALIDRECORD("DnsInvalidRecord"),
        WELLKNOWNHTTPNOTFOUND("WellKnownHttpNotFound"),
        WELLKNOWNNORESPONSE("WellKnownNoResponse"),
        WELLKNOWNINVALIDRESPONSE("WellKnownInvalidResponse"),
        WELLKNOWNLISTEMPTY("WellKnownListEmpty"),
        WELLKNOWNINVALIDCONTENTTYPE("WellKnownInvalidContentType"),
        WELLKNOWNMISSINGISSUANCEENDPOINT("WellKnownMissingIssuanceEndpoint"),
        WELLKNOWNISSUANCEENDPOINTCROSSORIGIN("WellKnownIssuanceEndpointCrossOrigin"),
        WELLKNOWNUNSUPPORTEDSIGNINGALGORITHM("WellKnownUnsupportedSigningAlgorithm"),
        TOKENHTTPNOTFOUND("TokenHttpNotFound"),
        TOKENNORESPONSE("TokenNoResponse"),
        TOKENINVALIDRESPONSE("TokenInvalidResponse"),
        TOKENINVALIDCONTENTTYPE("TokenInvalidContentType"),
        TOKENMALFORMEDSDJWT("TokenMalformedSdJwt"),
        TOKENINVALIDSDJWT("TokenInvalidSdJwt"),
        KEYBINDINGSIGNINGFAILED("KeyBindingSigningFailed"),
        RPORIGINISOPAQUE("RpOriginIsOpaque"),
        WELLKNOWNMISSINGACCOUNTSENDPOINT("WellKnownMissingAccountsEndpoint"),
        USERLOGGEDOUT("UserLoggedOut"),
        WELLKNOWNACCOUNTSENDPOINTCROSSORIGIN("WellKnownAccountsEndpointCrossOrigin"),
        ACCOUNTSHTTPNOTFOUND("AccountsHttpNotFound"),
        ACCOUNTSNORESPONSE("AccountsNoResponse"),
        ACCOUNTSINVALIDRESPONSE("AccountsInvalidResponse"),
        ACCOUNTSINVALIDCONTENTTYPE("AccountsInvalidContentType"),
        ACCOUNTSEMPTYLIST("AccountsEmptyList"),
        EMAILVERIFICATIONWELLKNOWNHTTPNOTFOUND("EmailVerificationWellKnownHttpNotFound"),
        EMAILVERIFICATIONWELLKNOWNNORESPONSE("EmailVerificationWellKnownNoResponse"),
        EMAILVERIFICATIONWELLKNOWNINVALIDRESPONSE("EmailVerificationWellKnownInvalidResponse"),
        EMAILVERIFICATIONWELLKNOWNINVALIDCONTENTTYPE("EmailVerificationWellKnownInvalidContentType"),
        JWKSHTTPNOTFOUND("JwksHttpNotFound"),
        JWKSINVALIDRESPONSE("JwksInvalidResponse"),
        TOKENVERIFICATIONSDJWTUNSUPPORTEDHEADERALG("TokenVerificationSdJwtUnsupportedHeaderAlg"),
        TOKENVERIFICATIONSDJWTMISSINGISS("TokenVerificationSdJwtMissingIss"),
        TOKENVERIFICATIONSDJWTMISSINGIAT("TokenVerificationSdJwtMissingIat"),
        TOKENVERIFICATIONSDJWTMISSINGCNF("TokenVerificationSdJwtMissingCnf"),
        TOKENVERIFICATIONSDJWTMISSINGEMAIL("TokenVerificationSdJwtMissingEmail"),
        TOKENVERIFICATIONSDJWTINVALIDISSUEDAT("TokenVerificationSdJwtInvalidIssuedAt"),
        TOKENVERIFICATIONSDJWTINVALIDISSUER("TokenVerificationSdJwtInvalidIssuer"),
        TOKENVERIFICATIONSDJWTJWKSMISSINGKEYS("TokenVerificationSdJwtJwksMissingKeys"),
        TOKENVERIFICATIONSDJWTSIGNATUREFAILED("TokenVerificationSdJwtSignatureFailed"),
        TOKENVERIFICATIONSDJWTINVALIDEMAILVERIFIED("TokenVerificationSdJwtInvalidEmailVerified"),
        TOKENVERIFICATIONSDJWTINVALIDEMAIL("TokenVerificationSdJwtInvalidEmail"),
        TOKENVERIFICATIONSDJWTINVALIDHOLDERKEY("TokenVerificationSdJwtInvalidHolderKey"),
        TOKENVERIFICATIONKBINVALIDTYP("TokenVerificationKbInvalidTyp"),
        TOKENVERIFICATIONKBMISSINGAUD("TokenVerificationKbMissingAud"),
        TOKENVERIFICATIONKBMISSINGNONCE("TokenVerificationKbMissingNonce"),
        TOKENVERIFICATIONKBMISSINGIAT("TokenVerificationKbMissingIat"),
        TOKENVERIFICATIONKBMISSINGSDHASH("TokenVerificationKbMissingSdHash"),
        TOKENVERIFICATIONKBINVALIDISSUEDAT("TokenVerificationKbInvalidIssuedAt"),
        TOKENVERIFICATIONKBINVALIDAUDIENCE("TokenVerificationKbInvalidAudience"),
        TOKENVERIFICATIONKBINVALIDNONCE("TokenVerificationKbInvalidNonce"),
        TOKENVERIFICATIONKBINVALIDSDHASH("TokenVerificationKbInvalidSdHash"),
        TOKENVERIFICATIONKBMISSINGCNF("TokenVerificationKbMissingCnf"),
        TOKENVERIFICATIONKBSIGNATUREFAILED("TokenVerificationKbSignatureFailed");
        public final String value;
        EmailVerificationRequestIssueReason(String value) { this.value = value; }
        @Override public String value() { return value; }
        public static EmailVerificationRequestIssueReason of(String value) {
            for (EmailVerificationRequestIssueReason constant : values()) {
                if (constant.value.equals(value)) return constant;
            }
            throw new IllegalArgumentException("Unknown EmailVerificationRequestIssueReason value: " + value);
        }
    }
    /**
     * This issue tracks client hints related issues. It&#x27;s used to deprecate old features, encourage the use of new ones, and provide general guidance.
     */
    public static final class ClientHintIssueDetails extends CdpObject {
        public ClientHintIssueDetails() {}
        private ClientHintIssueDetails(Map<String, Object> values) { super(values); }
        public static ClientHintIssueDetails fromMap(Map<String, Object> values) {
            return new ClientHintIssueDetails(values);
        }
        /**
         * Returns the sourceCodeLocation field.
         * @return the protocol field value
         */
        public Audits.SourceCodeLocation sourceCodeLocation() {
            return require(SOURCE_CODE_LOCATION);
        }
        /**
         * Returns the clientHintIssueReason field.
         * @return the protocol field value
         */
        public Audits.ClientHintIssueReason clientHintIssueReason() {
            return require(CLIENT_HINT_ISSUE_REASON);
        }
        /**
         * Sets the sourceCodeLocation field.
         * @param sourceCodeLocation field value
         * @return this model
         */
        public ClientHintIssueDetails sourceCodeLocation(Audits.SourceCodeLocation sourceCodeLocation) {
            set("sourceCodeLocation", sourceCodeLocation);
            return this;
        }
        /**
         * Sets the clientHintIssueReason field.
         * @param clientHintIssueReason field value
         * @return this model
         */
        public ClientHintIssueDetails clientHintIssueReason(Audits.ClientHintIssueReason clientHintIssueReason) {
            set("clientHintIssueReason", clientHintIssueReason);
            return this;
        }
    }
    /**
     */
    public static final class FailedRequestInfo extends CdpObject {
        public FailedRequestInfo() {}
        private FailedRequestInfo(Map<String, Object> values) { super(values); }
        public static FailedRequestInfo fromMap(Map<String, Object> values) {
            return new FailedRequestInfo(values);
        }
        /**
         * The URL that failed to load.
         * @return the protocol field value
         */
        public String url() {
            return requireString("url");
        }
        /**
         * The failure message for the failed request.
         * @return the protocol field value
         */
        public String failureMessage() {
            return requireString("failureMessage");
        }
        /**
         * Returns the requestId field.
         * @return the protocol field value, empty when absent
         */
        public Optional<Network.RequestId> requestId() {
            return optional(REQUEST_ID);
        }
        /**
         * The URL that failed to load.
         * @param url field value
         * @return this model
         */
        public FailedRequestInfo url(String url) {
            set("url", url);
            return this;
        }
        /**
         * The failure message for the failed request.
         * @param failureMessage field value
         * @return this model
         */
        public FailedRequestInfo failureMessage(String failureMessage) {
            set("failureMessage", failureMessage);
            return this;
        }
        /**
         * Sets the requestId field.
         * @param requestId field value
         * @return this model
         */
        public FailedRequestInfo requestId(Network.RequestId requestId) {
            set("requestId", requestId);
            return this;
        }
    }
    /**
     * Wire values for PartitioningBlobURLInfo.
     */
    public enum PartitioningBlobURLInfo implements CdpValue<String> {
        BLOCKEDCROSSPARTITIONFETCHING("BlockedCrossPartitionFetching"),
        ENFORCENOOPENERFORNAVIGATION("EnforceNoopenerForNavigation");
        public final String value;
        PartitioningBlobURLInfo(String value) { this.value = value; }
        @Override public String value() { return value; }
        public static PartitioningBlobURLInfo of(String value) {
            for (PartitioningBlobURLInfo constant : values()) {
                if (constant.value.equals(value)) return constant;
            }
            throw new IllegalArgumentException("Unknown PartitioningBlobURLInfo value: " + value);
        }
    }
    /**
     */
    public static final class PartitioningBlobURLIssueDetails extends CdpObject {
        public PartitioningBlobURLIssueDetails() {}
        private PartitioningBlobURLIssueDetails(Map<String, Object> values) { super(values); }
        public static PartitioningBlobURLIssueDetails fromMap(Map<String, Object> values) {
            return new PartitioningBlobURLIssueDetails(values);
        }
        /**
         * The BlobURL that failed to load.
         * @return the protocol field value
         */
        public String url() {
            return requireString("url");
        }
        /**
         * Additional information about the Partitioning Blob URL issue.
         * @return the protocol field value
         */
        public Audits.PartitioningBlobURLInfo partitioningBlobURLInfo() {
            return require(PARTITIONING_BLOB_URLINFO);
        }
        /**
         * The BlobURL that failed to load.
         * @param url field value
         * @return this model
         */
        public PartitioningBlobURLIssueDetails url(String url) {
            set("url", url);
            return this;
        }
        /**
         * Additional information about the Partitioning Blob URL issue.
         * @param partitioningBlobURLInfo field value
         * @return this model
         */
        public PartitioningBlobURLIssueDetails partitioningBlobURLInfo(Audits.PartitioningBlobURLInfo partitioningBlobURLInfo) {
            set("partitioningBlobURLInfo", partitioningBlobURLInfo);
            return this;
        }
    }
    /**
     * Wire values for ElementAccessibilityIssueReason.
     */
    public enum ElementAccessibilityIssueReason implements CdpValue<String> {
        DISALLOWEDSELECTCHILD("DisallowedSelectChild"),
        DISALLOWEDOPTGROUPCHILD("DisallowedOptGroupChild"),
        NONPHRASINGCONTENTOPTIONCHILD("NonPhrasingContentOptionChild"),
        INTERACTIVECONTENTOPTIONCHILD("InteractiveContentOptionChild"),
        INTERACTIVECONTENTLEGENDCHILD("InteractiveContentLegendChild"),
        INTERACTIVECONTENTSUMMARYDESCENDANT("InteractiveContentSummaryDescendant");
        public final String value;
        ElementAccessibilityIssueReason(String value) { this.value = value; }
        @Override public String value() { return value; }
        public static ElementAccessibilityIssueReason of(String value) {
            for (ElementAccessibilityIssueReason constant : values()) {
                if (constant.value.equals(value)) return constant;
            }
            throw new IllegalArgumentException("Unknown ElementAccessibilityIssueReason value: " + value);
        }
    }
    /**
     * This issue warns about errors in the select or summary element content model.
     */
    public static final class ElementAccessibilityIssueDetails extends CdpObject {
        public ElementAccessibilityIssueDetails() {}
        private ElementAccessibilityIssueDetails(Map<String, Object> values) { super(values); }
        public static ElementAccessibilityIssueDetails fromMap(Map<String, Object> values) {
            return new ElementAccessibilityIssueDetails(values);
        }
        /**
         * Returns the nodeId field.
         * @return the protocol field value
         */
        public DOM.BackendNodeId nodeId() {
            return require(NODE_ID);
        }
        /**
         * Returns the elementAccessibilityIssueReason field.
         * @return the protocol field value
         */
        public Audits.ElementAccessibilityIssueReason elementAccessibilityIssueReason() {
            return require(ELEMENT_ACCESSIBILITY_ISSUE_REASON);
        }
        /**
         * Returns the hasDisallowedAttributes field.
         * @return the protocol field value
         */
        public boolean hasDisallowedAttributes() {
            return requireBoolean("hasDisallowedAttributes");
        }
        /**
         * Sets the nodeId field.
         * @param nodeId field value
         * @return this model
         */
        public ElementAccessibilityIssueDetails nodeId(DOM.BackendNodeId nodeId) {
            set("nodeId", nodeId);
            return this;
        }
        /**
         * Sets the elementAccessibilityIssueReason field.
         * @param elementAccessibilityIssueReason field value
         * @return this model
         */
        public ElementAccessibilityIssueDetails elementAccessibilityIssueReason(Audits.ElementAccessibilityIssueReason elementAccessibilityIssueReason) {
            set("elementAccessibilityIssueReason", elementAccessibilityIssueReason);
            return this;
        }
        /**
         * Sets the hasDisallowedAttributes field.
         * @param hasDisallowedAttributes field value
         * @return this model
         */
        public ElementAccessibilityIssueDetails hasDisallowedAttributes(boolean hasDisallowedAttributes) {
            set("hasDisallowedAttributes", hasDisallowedAttributes);
            return this;
        }
    }
    /**
     * Wire values for StyleSheetLoadingIssueReason.
     */
    public enum StyleSheetLoadingIssueReason implements CdpValue<String> {
        LATEIMPORTRULE("LateImportRule"),
        REQUESTFAILED("RequestFailed");
        public final String value;
        StyleSheetLoadingIssueReason(String value) { this.value = value; }
        @Override public String value() { return value; }
        public static StyleSheetLoadingIssueReason of(String value) {
            for (StyleSheetLoadingIssueReason constant : values()) {
                if (constant.value.equals(value)) return constant;
            }
            throw new IllegalArgumentException("Unknown StyleSheetLoadingIssueReason value: " + value);
        }
    }
    /**
     * This issue warns when a referenced stylesheet couldn&#x27;t be loaded.
     */
    public static final class StylesheetLoadingIssueDetails extends CdpObject {
        public StylesheetLoadingIssueDetails() {}
        private StylesheetLoadingIssueDetails(Map<String, Object> values) { super(values); }
        public static StylesheetLoadingIssueDetails fromMap(Map<String, Object> values) {
            return new StylesheetLoadingIssueDetails(values);
        }
        /**
         * Source code position that referenced the failing stylesheet.
         * @return the protocol field value
         */
        public Audits.SourceCodeLocation sourceCodeLocation() {
            return require(SOURCE_CODE_LOCATION);
        }
        /**
         * Reason why the stylesheet couldn&#x27;t be loaded.
         * @return the protocol field value
         */
        public Audits.StyleSheetLoadingIssueReason styleSheetLoadingIssueReason() {
            return require(STYLE_SHEET_LOADING_ISSUE_REASON);
        }
        /**
         * Contains additional info when the failure was due to a request.
         * @return the protocol field value, empty when absent
         */
        public Optional<Audits.FailedRequestInfo> failedRequestInfo() {
            return optional(FAILED_REQUEST_INFO);
        }
        /**
         * Source code position that referenced the failing stylesheet.
         * @param sourceCodeLocation field value
         * @return this model
         */
        public StylesheetLoadingIssueDetails sourceCodeLocation(Audits.SourceCodeLocation sourceCodeLocation) {
            set("sourceCodeLocation", sourceCodeLocation);
            return this;
        }
        /**
         * Reason why the stylesheet couldn&#x27;t be loaded.
         * @param styleSheetLoadingIssueReason field value
         * @return this model
         */
        public StylesheetLoadingIssueDetails styleSheetLoadingIssueReason(Audits.StyleSheetLoadingIssueReason styleSheetLoadingIssueReason) {
            set("styleSheetLoadingIssueReason", styleSheetLoadingIssueReason);
            return this;
        }
        /**
         * Contains additional info when the failure was due to a request.
         * @param failedRequestInfo field value
         * @return this model
         */
        public StylesheetLoadingIssueDetails failedRequestInfo(Audits.FailedRequestInfo failedRequestInfo) {
            set("failedRequestInfo", failedRequestInfo);
            return this;
        }
    }
    /**
     * Wire values for PropertyRuleIssueReason.
     */
    public enum PropertyRuleIssueReason implements CdpValue<String> {
        INVALIDSYNTAX("InvalidSyntax"),
        INVALIDINITIALVALUE("InvalidInitialValue"),
        INVALIDINHERITS("InvalidInherits"),
        INVALIDNAME("InvalidName");
        public final String value;
        PropertyRuleIssueReason(String value) { this.value = value; }
        @Override public String value() { return value; }
        public static PropertyRuleIssueReason of(String value) {
            for (PropertyRuleIssueReason constant : values()) {
                if (constant.value.equals(value)) return constant;
            }
            throw new IllegalArgumentException("Unknown PropertyRuleIssueReason value: " + value);
        }
    }
    /**
     * This issue warns about errors in property rules that lead to property registrations being ignored.
     */
    public static final class PropertyRuleIssueDetails extends CdpObject {
        public PropertyRuleIssueDetails() {}
        private PropertyRuleIssueDetails(Map<String, Object> values) { super(values); }
        public static PropertyRuleIssueDetails fromMap(Map<String, Object> values) {
            return new PropertyRuleIssueDetails(values);
        }
        /**
         * Source code position of the property rule.
         * @return the protocol field value
         */
        public Audits.SourceCodeLocation sourceCodeLocation() {
            return require(SOURCE_CODE_LOCATION);
        }
        /**
         * Reason why the property rule was discarded.
         * @return the protocol field value
         */
        public Audits.PropertyRuleIssueReason propertyRuleIssueReason() {
            return require(PROPERTY_RULE_ISSUE_REASON);
        }
        /**
         * The value of the property rule property that failed to parse
         * @return the protocol field value, empty when absent
         */
        public Optional<String> propertyValue() {
            return optionalString("propertyValue");
        }
        /**
         * Source code position of the property rule.
         * @param sourceCodeLocation field value
         * @return this model
         */
        public PropertyRuleIssueDetails sourceCodeLocation(Audits.SourceCodeLocation sourceCodeLocation) {
            set("sourceCodeLocation", sourceCodeLocation);
            return this;
        }
        /**
         * Reason why the property rule was discarded.
         * @param propertyRuleIssueReason field value
         * @return this model
         */
        public PropertyRuleIssueDetails propertyRuleIssueReason(Audits.PropertyRuleIssueReason propertyRuleIssueReason) {
            set("propertyRuleIssueReason", propertyRuleIssueReason);
            return this;
        }
        /**
         * The value of the property rule property that failed to parse
         * @param propertyValue field value
         * @return this model
         */
        public PropertyRuleIssueDetails propertyValue(String propertyValue) {
            set("propertyValue", propertyValue);
            return this;
        }
    }
    /**
     * Wire values for UserReidentificationIssueType.
     */
    public enum UserReidentificationIssueType implements CdpValue<String> {
        BLOCKEDFRAMENAVIGATION("BlockedFrameNavigation"),
        BLOCKEDSUBRESOURCE("BlockedSubresource"),
        NOISEDCANVASREADBACK("NoisedCanvasReadback");
        public final String value;
        UserReidentificationIssueType(String value) { this.value = value; }
        @Override public String value() { return value; }
        public static UserReidentificationIssueType of(String value) {
            for (UserReidentificationIssueType constant : values()) {
                if (constant.value.equals(value)) return constant;
            }
            throw new IllegalArgumentException("Unknown UserReidentificationIssueType value: " + value);
        }
    }
    /**
     * This issue warns about uses of APIs that may be considered misuse to re-identify users.
     */
    public static final class UserReidentificationIssueDetails extends CdpObject {
        public UserReidentificationIssueDetails() {}
        private UserReidentificationIssueDetails(Map<String, Object> values) { super(values); }
        public static UserReidentificationIssueDetails fromMap(Map<String, Object> values) {
            return new UserReidentificationIssueDetails(values);
        }
        /**
         * Returns the type field.
         * @return the protocol field value
         */
        public Audits.UserReidentificationIssueType type() {
            return require(TYPE_3);
        }
        /**
         * Applies to BlockedFrameNavigation and BlockedSubresource issue types.
         * @return the protocol field value, empty when absent
         */
        public Optional<Audits.AffectedRequest> request() {
            return optional(REQUEST);
        }
        /**
         * Applies to NoisedCanvasReadback issue type.
         * @return the protocol field value, empty when absent
         */
        public Optional<Audits.SourceCodeLocation> sourceCodeLocation() {
            return optional(SOURCE_CODE_LOCATION);
        }
        /**
         * Sets the type field.
         * @param type field value
         * @return this model
         */
        public UserReidentificationIssueDetails type(Audits.UserReidentificationIssueType type) {
            set("type", type);
            return this;
        }
        /**
         * Applies to BlockedFrameNavigation and BlockedSubresource issue types.
         * @param request field value
         * @return this model
         */
        public UserReidentificationIssueDetails request(Audits.AffectedRequest request) {
            set("request", request);
            return this;
        }
        /**
         * Applies to NoisedCanvasReadback issue type.
         * @param sourceCodeLocation field value
         * @return this model
         */
        public UserReidentificationIssueDetails sourceCodeLocation(Audits.SourceCodeLocation sourceCodeLocation) {
            set("sourceCodeLocation", sourceCodeLocation);
            return this;
        }
    }
    /**
     * Wire values for PermissionElementIssueType.
     */
    public enum PermissionElementIssueType implements CdpValue<String> {
        INVALIDTYPE("InvalidType"),
        FENCEDFRAMEDISALLOWED("FencedFrameDisallowed"),
        CSPFRAMEANCESTORSMISSING("CspFrameAncestorsMissing"),
        PERMISSIONSPOLICYBLOCKED("PermissionsPolicyBlocked"),
        PADDINGRIGHTUNSUPPORTED("PaddingRightUnsupported"),
        PADDINGBOTTOMUNSUPPORTED("PaddingBottomUnsupported"),
        INSETBOXSHADOWUNSUPPORTED("InsetBoxShadowUnsupported"),
        REQUESTINPROGRESS("RequestInProgress"),
        UNTRUSTEDEVENT("UntrustedEvent"),
        REGISTRATIONFAILED("RegistrationFailed"),
        TYPENOTSUPPORTED("TypeNotSupported"),
        INVALIDTYPEACTIVATION("InvalidTypeActivation"),
        SECURITYCHECKSFAILED("SecurityChecksFailed"),
        ACTIVATIONDISABLED("ActivationDisabled"),
        GEOLOCATIONDEPRECATED("GeolocationDeprecated"),
        INVALIDDISPLAYSTYLE("InvalidDisplayStyle"),
        NONOPAQUECOLOR("NonOpaqueColor"),
        LOWCONTRAST("LowContrast"),
        FONTSIZETOOSMALL("FontSizeTooSmall"),
        FONTSIZETOOLARGE("FontSizeTooLarge"),
        INVALIDSIZEVALUE("InvalidSizeValue");
        public final String value;
        PermissionElementIssueType(String value) { this.value = value; }
        @Override public String value() { return value; }
        public static PermissionElementIssueType of(String value) {
            for (PermissionElementIssueType constant : values()) {
                if (constant.value.equals(value)) return constant;
            }
            throw new IllegalArgumentException("Unknown PermissionElementIssueType value: " + value);
        }
    }
    /**
     * This issue warns about improper usage of the &lt;permission&gt; element.
     */
    public static final class PermissionElementIssueDetails extends CdpObject {
        public PermissionElementIssueDetails() {}
        private PermissionElementIssueDetails(Map<String, Object> values) { super(values); }
        public static PermissionElementIssueDetails fromMap(Map<String, Object> values) {
            return new PermissionElementIssueDetails(values);
        }
        /**
         * Returns the issueType field.
         * @return the protocol field value
         */
        public Audits.PermissionElementIssueType issueType() {
            return require(ISSUE_TYPE);
        }
        /**
         * The value of the type attribute.
         * @return the protocol field value, empty when absent
         */
        public Optional<String> type() {
            return optionalString("type");
        }
        /**
         * The node ID of the &lt;permission&gt; element.
         * @return the protocol field value, empty when absent
         */
        public Optional<DOM.BackendNodeId> nodeId() {
            return optional(NODE_ID);
        }
        /**
         * True if the issue is a warning, false if it is an error.
         * @return the protocol field value, empty when absent
         */
        public Optional<Boolean> isWarning() {
            return optionalBoolean("isWarning");
        }
        /**
         * Fields for message construction: Used for messages that reference a specific permission name
         * @return the protocol field value, empty when absent
         */
        public Optional<String> permissionName() {
            return optionalString("permissionName");
        }
        /**
         * Used for messages about occlusion
         * @return the protocol field value, empty when absent
         */
        public Optional<String> occluderNodeInfo() {
            return optionalString("occluderNodeInfo");
        }
        /**
         * Used for messages about occluder&#x27;s parent
         * @return the protocol field value, empty when absent
         */
        public Optional<String> occluderParentNodeInfo() {
            return optionalString("occluderParentNodeInfo");
        }
        /**
         * Used for messages about activation disabled reason
         * @return the protocol field value, empty when absent
         */
        public Optional<String> disableReason() {
            return optionalString("disableReason");
        }
        /**
         * Sets the issueType field.
         * @param issueType field value
         * @return this model
         */
        public PermissionElementIssueDetails issueType(Audits.PermissionElementIssueType issueType) {
            set("issueType", issueType);
            return this;
        }
        /**
         * The value of the type attribute.
         * @param type field value
         * @return this model
         */
        public PermissionElementIssueDetails type(String type) {
            set("type", type);
            return this;
        }
        /**
         * The node ID of the &lt;permission&gt; element.
         * @param nodeId field value
         * @return this model
         */
        public PermissionElementIssueDetails nodeId(DOM.BackendNodeId nodeId) {
            set("nodeId", nodeId);
            return this;
        }
        /**
         * True if the issue is a warning, false if it is an error.
         * @param isWarning field value
         * @return this model
         */
        public PermissionElementIssueDetails isWarning(boolean isWarning) {
            set("isWarning", isWarning);
            return this;
        }
        /**
         * Fields for message construction: Used for messages that reference a specific permission name
         * @param permissionName field value
         * @return this model
         */
        public PermissionElementIssueDetails permissionName(String permissionName) {
            set("permissionName", permissionName);
            return this;
        }
        /**
         * Used for messages about occlusion
         * @param occluderNodeInfo field value
         * @return this model
         */
        public PermissionElementIssueDetails occluderNodeInfo(String occluderNodeInfo) {
            set("occluderNodeInfo", occluderNodeInfo);
            return this;
        }
        /**
         * Used for messages about occluder&#x27;s parent
         * @param occluderParentNodeInfo field value
         * @return this model
         */
        public PermissionElementIssueDetails occluderParentNodeInfo(String occluderParentNodeInfo) {
            set("occluderParentNodeInfo", occluderParentNodeInfo);
            return this;
        }
        /**
         * Used for messages about activation disabled reason
         * @param disableReason field value
         * @return this model
         */
        public PermissionElementIssueDetails disableReason(String disableReason) {
            set("disableReason", disableReason);
            return this;
        }
    }
    /**
     * The issue warns about blocked calls to privacy sensitive APIs via the Selective Permissions Intervention.
     */
    public static final class SelectivePermissionsInterventionIssueDetails extends CdpObject {
        public SelectivePermissionsInterventionIssueDetails() {}
        private SelectivePermissionsInterventionIssueDetails(Map<String, Object> values) { super(values); }
        public static SelectivePermissionsInterventionIssueDetails fromMap(Map<String, Object> values) {
            return new SelectivePermissionsInterventionIssueDetails(values);
        }
        /**
         * Which API was intervened on.
         * @return the protocol field value
         */
        public String apiName() {
            return requireString("apiName");
        }
        /**
         * Why the ad script using the API is considered an ad.
         * @return the protocol field value
         */
        public Network.AdAncestry adAncestry() {
            return require(AD_ANCESTRY);
        }
        /**
         * The stack trace at the time of the intervention.
         * @return the protocol field value, empty when absent
         */
        public Optional<Runtime.StackTrace> stackTrace() {
            return optional(STACK_TRACE);
        }
        /**
         * Which API was intervened on.
         * @param apiName field value
         * @return this model
         */
        public SelectivePermissionsInterventionIssueDetails apiName(String apiName) {
            set("apiName", apiName);
            return this;
        }
        /**
         * Why the ad script using the API is considered an ad.
         * @param adAncestry field value
         * @return this model
         */
        public SelectivePermissionsInterventionIssueDetails adAncestry(Network.AdAncestry adAncestry) {
            set("adAncestry", adAncestry);
            return this;
        }
        /**
         * The stack trace at the time of the intervention.
         * @param stackTrace field value
         * @return this model
         */
        public SelectivePermissionsInterventionIssueDetails stackTrace(Runtime.StackTrace stackTrace) {
            set("stackTrace", stackTrace);
            return this;
        }
    }
    /**
     * A unique identifier for the type of issue. Each type may use one of the optional fields in InspectorIssueDetails to convey more specific information about the kind of issue.
     */
    public enum InspectorIssueCode implements CdpValue<String> {
        COOKIEISSUE("CookieIssue"),
        MIXEDCONTENTISSUE("MixedContentIssue"),
        BLOCKEDBYRESPONSEISSUE("BlockedByResponseIssue"),
        HEAVYADISSUE("HeavyAdIssue"),
        CONTENTSECURITYPOLICYISSUE("ContentSecurityPolicyIssue"),
        SHAREDARRAYBUFFERISSUE("SharedArrayBufferIssue"),
        CORSISSUE("CorsIssue"),
        ATTRIBUTIONREPORTINGISSUE("AttributionReportingIssue"),
        QUIRKSMODEISSUE("QuirksModeIssue"),
        PARTITIONINGBLOBURLISSUE("PartitioningBlobURLIssue"),
        NAVIGATORUSERAGENTISSUE("NavigatorUserAgentIssue"),
        GENERICISSUE("GenericIssue"),
        DEPRECATIONISSUE("DeprecationIssue"),
        CLIENTHINTISSUE("ClientHintIssue"),
        FEDERATEDAUTHREQUESTISSUE("FederatedAuthRequestIssue"),
        BOUNCETRACKINGISSUE("BounceTrackingIssue"),
        COOKIEDEPRECATIONMETADATAISSUE("CookieDeprecationMetadataIssue"),
        STYLESHEETLOADINGISSUE("StylesheetLoadingIssue"),
        FEDERATEDAUTHUSERINFOREQUESTISSUE("FederatedAuthUserInfoRequestIssue"),
        PROPERTYRULEISSUE("PropertyRuleIssue"),
        SHAREDDICTIONARYISSUE("SharedDictionaryIssue"),
        ELEMENTACCESSIBILITYISSUE("ElementAccessibilityIssue"),
        SRIMESSAGESIGNATUREISSUE("SRIMessageSignatureIssue"),
        UNENCODEDDIGESTISSUE("UnencodedDigestIssue"),
        CONNECTIONALLOWLISTISSUE("ConnectionAllowlistIssue"),
        USERREIDENTIFICATIONISSUE("UserReidentificationIssue"),
        PERMISSIONELEMENTISSUE("PermissionElementIssue"),
        PERFORMANCEISSUE("PerformanceIssue"),
        SELECTIVEPERMISSIONSINTERVENTIONISSUE("SelectivePermissionsInterventionIssue"),
        EMAILVERIFICATIONREQUESTISSUE("EmailVerificationRequestIssue");
        public final String value;
        InspectorIssueCode(String value) { this.value = value; }
        @Override public String value() { return value; }
        public static InspectorIssueCode of(String value) {
            for (InspectorIssueCode constant : values()) {
                if (constant.value.equals(value)) return constant;
            }
            throw new IllegalArgumentException("Unknown InspectorIssueCode value: " + value);
        }
    }
    /**
     * This struct holds a list of optional fields with additional information specific to the kind of issue. When adding a new issue code, please also add a new optional field to this type.
     */
    public static final class InspectorIssueDetails extends CdpObject {
        public InspectorIssueDetails() {}
        private InspectorIssueDetails(Map<String, Object> values) { super(values); }
        public static InspectorIssueDetails fromMap(Map<String, Object> values) {
            return new InspectorIssueDetails(values);
        }
        /**
         * Returns the cookieIssueDetails field.
         * @return the protocol field value, empty when absent
         */
        public Optional<Audits.CookieIssueDetails> cookieIssueDetails() {
            return optional(COOKIE_ISSUE_DETAILS);
        }
        /**
         * Returns the mixedContentIssueDetails field.
         * @return the protocol field value, empty when absent
         */
        public Optional<Audits.MixedContentIssueDetails> mixedContentIssueDetails() {
            return optional(MIXED_CONTENT_ISSUE_DETAILS);
        }
        /**
         * Returns the blockedByResponseIssueDetails field.
         * @return the protocol field value, empty when absent
         */
        public Optional<Audits.BlockedByResponseIssueDetails> blockedByResponseIssueDetails() {
            return optional(BLOCKED_BY_RESPONSE_ISSUE_DETAILS);
        }
        /**
         * Returns the heavyAdIssueDetails field.
         * @return the protocol field value, empty when absent
         */
        public Optional<Audits.HeavyAdIssueDetails> heavyAdIssueDetails() {
            return optional(HEAVY_AD_ISSUE_DETAILS);
        }
        /**
         * Returns the contentSecurityPolicyIssueDetails field.
         * @return the protocol field value, empty when absent
         */
        public Optional<Audits.ContentSecurityPolicyIssueDetails> contentSecurityPolicyIssueDetails() {
            return optional(CONTENT_SECURITY_POLICY_ISSUE_DETAILS);
        }
        /**
         * Returns the sharedArrayBufferIssueDetails field.
         * @return the protocol field value, empty when absent
         */
        public Optional<Audits.SharedArrayBufferIssueDetails> sharedArrayBufferIssueDetails() {
            return optional(SHARED_ARRAY_BUFFER_ISSUE_DETAILS);
        }
        /**
         * Returns the corsIssueDetails field.
         * @return the protocol field value, empty when absent
         */
        public Optional<Audits.CorsIssueDetails> corsIssueDetails() {
            return optional(CORS_ISSUE_DETAILS);
        }
        /**
         * Returns the attributionReportingIssueDetails field.
         * @return the protocol field value, empty when absent
         */
        public Optional<Audits.AttributionReportingIssueDetails> attributionReportingIssueDetails() {
            return optional(ATTRIBUTION_REPORTING_ISSUE_DETAILS);
        }
        /**
         * Returns the quirksModeIssueDetails field.
         * @return the protocol field value, empty when absent
         */
        public Optional<Audits.QuirksModeIssueDetails> quirksModeIssueDetails() {
            return optional(QUIRKS_MODE_ISSUE_DETAILS);
        }
        /**
         * Returns the partitioningBlobURLIssueDetails field.
         * @return the protocol field value, empty when absent
         */
        public Optional<Audits.PartitioningBlobURLIssueDetails> partitioningBlobURLIssueDetails() {
            return optional(PARTITIONING_BLOB_URLISSUE_DETAILS);
        }
        /**
         * Returns the navigatorUserAgentIssueDetails field.
         * @return the protocol field value, empty when absent
         * @deprecated Deprecated by the Chromium DevTools Protocol.
         */
        @Deprecated
        public Optional<Audits.NavigatorUserAgentIssueDetails> navigatorUserAgentIssueDetails() {
            return optional(NAVIGATOR_USER_AGENT_ISSUE_DETAILS);
        }
        /**
         * Returns the genericIssueDetails field.
         * @return the protocol field value, empty when absent
         */
        public Optional<Audits.GenericIssueDetails> genericIssueDetails() {
            return optional(GENERIC_ISSUE_DETAILS);
        }
        /**
         * Returns the deprecationIssueDetails field.
         * @return the protocol field value, empty when absent
         */
        public Optional<Audits.DeprecationIssueDetails> deprecationIssueDetails() {
            return optional(DEPRECATION_ISSUE_DETAILS);
        }
        /**
         * Returns the clientHintIssueDetails field.
         * @return the protocol field value, empty when absent
         */
        public Optional<Audits.ClientHintIssueDetails> clientHintIssueDetails() {
            return optional(CLIENT_HINT_ISSUE_DETAILS);
        }
        /**
         * Returns the federatedAuthRequestIssueDetails field.
         * @return the protocol field value, empty when absent
         */
        public Optional<Audits.FederatedAuthRequestIssueDetails> federatedAuthRequestIssueDetails() {
            return optional(FEDERATED_AUTH_REQUEST_ISSUE_DETAILS);
        }
        /**
         * Returns the bounceTrackingIssueDetails field.
         * @return the protocol field value, empty when absent
         */
        public Optional<Audits.BounceTrackingIssueDetails> bounceTrackingIssueDetails() {
            return optional(BOUNCE_TRACKING_ISSUE_DETAILS);
        }
        /**
         * Returns the cookieDeprecationMetadataIssueDetails field.
         * @return the protocol field value, empty when absent
         */
        public Optional<Audits.CookieDeprecationMetadataIssueDetails> cookieDeprecationMetadataIssueDetails() {
            return optional(COOKIE_DEPRECATION_METADATA_ISSUE_DETAILS);
        }
        /**
         * Returns the stylesheetLoadingIssueDetails field.
         * @return the protocol field value, empty when absent
         */
        public Optional<Audits.StylesheetLoadingIssueDetails> stylesheetLoadingIssueDetails() {
            return optional(STYLESHEET_LOADING_ISSUE_DETAILS);
        }
        /**
         * Returns the propertyRuleIssueDetails field.
         * @return the protocol field value, empty when absent
         */
        public Optional<Audits.PropertyRuleIssueDetails> propertyRuleIssueDetails() {
            return optional(PROPERTY_RULE_ISSUE_DETAILS);
        }
        /**
         * Returns the federatedAuthUserInfoRequestIssueDetails field.
         * @return the protocol field value, empty when absent
         */
        public Optional<Audits.FederatedAuthUserInfoRequestIssueDetails> federatedAuthUserInfoRequestIssueDetails() {
            return optional(FEDERATED_AUTH_USER_INFO_REQUEST_ISSUE_DETAILS);
        }
        /**
         * Returns the sharedDictionaryIssueDetails field.
         * @return the protocol field value, empty when absent
         */
        public Optional<Audits.SharedDictionaryIssueDetails> sharedDictionaryIssueDetails() {
            return optional(SHARED_DICTIONARY_ISSUE_DETAILS);
        }
        /**
         * Returns the elementAccessibilityIssueDetails field.
         * @return the protocol field value, empty when absent
         */
        public Optional<Audits.ElementAccessibilityIssueDetails> elementAccessibilityIssueDetails() {
            return optional(ELEMENT_ACCESSIBILITY_ISSUE_DETAILS);
        }
        /**
         * Returns the sriMessageSignatureIssueDetails field.
         * @return the protocol field value, empty when absent
         */
        public Optional<Audits.SRIMessageSignatureIssueDetails> sriMessageSignatureIssueDetails() {
            return optional(SRI_MESSAGE_SIGNATURE_ISSUE_DETAILS);
        }
        /**
         * Returns the unencodedDigestIssueDetails field.
         * @return the protocol field value, empty when absent
         */
        public Optional<Audits.UnencodedDigestIssueDetails> unencodedDigestIssueDetails() {
            return optional(UNENCODED_DIGEST_ISSUE_DETAILS);
        }
        /**
         * Returns the connectionAllowlistIssueDetails field.
         * @return the protocol field value, empty when absent
         */
        public Optional<Audits.ConnectionAllowlistIssueDetails> connectionAllowlistIssueDetails() {
            return optional(CONNECTION_ALLOWLIST_ISSUE_DETAILS);
        }
        /**
         * Returns the userReidentificationIssueDetails field.
         * @return the protocol field value, empty when absent
         */
        public Optional<Audits.UserReidentificationIssueDetails> userReidentificationIssueDetails() {
            return optional(USER_REIDENTIFICATION_ISSUE_DETAILS);
        }
        /**
         * Returns the permissionElementIssueDetails field.
         * @return the protocol field value, empty when absent
         */
        public Optional<Audits.PermissionElementIssueDetails> permissionElementIssueDetails() {
            return optional(PERMISSION_ELEMENT_ISSUE_DETAILS);
        }
        /**
         * Returns the performanceIssueDetails field.
         * @return the protocol field value, empty when absent
         */
        public Optional<Audits.PerformanceIssueDetails> performanceIssueDetails() {
            return optional(PERFORMANCE_ISSUE_DETAILS);
        }
        /**
         * Returns the selectivePermissionsInterventionIssueDetails field.
         * @return the protocol field value, empty when absent
         */
        public Optional<Audits.SelectivePermissionsInterventionIssueDetails> selectivePermissionsInterventionIssueDetails() {
            return optional(SELECTIVE_PERMISSIONS_INTERVENTION_ISSUE_DETAILS);
        }
        /**
         * Returns the emailVerificationRequestIssueDetails field.
         * @return the protocol field value, empty when absent
         */
        public Optional<Audits.EmailVerificationRequestIssueDetails> emailVerificationRequestIssueDetails() {
            return optional(EMAIL_VERIFICATION_REQUEST_ISSUE_DETAILS);
        }
        /**
         * Sets the cookieIssueDetails field.
         * @param cookieIssueDetails field value
         * @return this model
         */
        public InspectorIssueDetails cookieIssueDetails(Audits.CookieIssueDetails cookieIssueDetails) {
            set("cookieIssueDetails", cookieIssueDetails);
            return this;
        }
        /**
         * Sets the mixedContentIssueDetails field.
         * @param mixedContentIssueDetails field value
         * @return this model
         */
        public InspectorIssueDetails mixedContentIssueDetails(Audits.MixedContentIssueDetails mixedContentIssueDetails) {
            set("mixedContentIssueDetails", mixedContentIssueDetails);
            return this;
        }
        /**
         * Sets the blockedByResponseIssueDetails field.
         * @param blockedByResponseIssueDetails field value
         * @return this model
         */
        public InspectorIssueDetails blockedByResponseIssueDetails(Audits.BlockedByResponseIssueDetails blockedByResponseIssueDetails) {
            set("blockedByResponseIssueDetails", blockedByResponseIssueDetails);
            return this;
        }
        /**
         * Sets the heavyAdIssueDetails field.
         * @param heavyAdIssueDetails field value
         * @return this model
         */
        public InspectorIssueDetails heavyAdIssueDetails(Audits.HeavyAdIssueDetails heavyAdIssueDetails) {
            set("heavyAdIssueDetails", heavyAdIssueDetails);
            return this;
        }
        /**
         * Sets the contentSecurityPolicyIssueDetails field.
         * @param contentSecurityPolicyIssueDetails field value
         * @return this model
         */
        public InspectorIssueDetails contentSecurityPolicyIssueDetails(Audits.ContentSecurityPolicyIssueDetails contentSecurityPolicyIssueDetails) {
            set("contentSecurityPolicyIssueDetails", contentSecurityPolicyIssueDetails);
            return this;
        }
        /**
         * Sets the sharedArrayBufferIssueDetails field.
         * @param sharedArrayBufferIssueDetails field value
         * @return this model
         */
        public InspectorIssueDetails sharedArrayBufferIssueDetails(Audits.SharedArrayBufferIssueDetails sharedArrayBufferIssueDetails) {
            set("sharedArrayBufferIssueDetails", sharedArrayBufferIssueDetails);
            return this;
        }
        /**
         * Sets the corsIssueDetails field.
         * @param corsIssueDetails field value
         * @return this model
         */
        public InspectorIssueDetails corsIssueDetails(Audits.CorsIssueDetails corsIssueDetails) {
            set("corsIssueDetails", corsIssueDetails);
            return this;
        }
        /**
         * Sets the attributionReportingIssueDetails field.
         * @param attributionReportingIssueDetails field value
         * @return this model
         */
        public InspectorIssueDetails attributionReportingIssueDetails(Audits.AttributionReportingIssueDetails attributionReportingIssueDetails) {
            set("attributionReportingIssueDetails", attributionReportingIssueDetails);
            return this;
        }
        /**
         * Sets the quirksModeIssueDetails field.
         * @param quirksModeIssueDetails field value
         * @return this model
         */
        public InspectorIssueDetails quirksModeIssueDetails(Audits.QuirksModeIssueDetails quirksModeIssueDetails) {
            set("quirksModeIssueDetails", quirksModeIssueDetails);
            return this;
        }
        /**
         * Sets the partitioningBlobURLIssueDetails field.
         * @param partitioningBlobURLIssueDetails field value
         * @return this model
         */
        public InspectorIssueDetails partitioningBlobURLIssueDetails(Audits.PartitioningBlobURLIssueDetails partitioningBlobURLIssueDetails) {
            set("partitioningBlobURLIssueDetails", partitioningBlobURLIssueDetails);
            return this;
        }
        /**
         * Sets the navigatorUserAgentIssueDetails field.
         * @param navigatorUserAgentIssueDetails field value
         * @return this model
         * @deprecated Deprecated by the Chromium DevTools Protocol.
         */
        @Deprecated
        public InspectorIssueDetails navigatorUserAgentIssueDetails(Audits.NavigatorUserAgentIssueDetails navigatorUserAgentIssueDetails) {
            set("navigatorUserAgentIssueDetails", navigatorUserAgentIssueDetails);
            return this;
        }
        /**
         * Sets the genericIssueDetails field.
         * @param genericIssueDetails field value
         * @return this model
         */
        public InspectorIssueDetails genericIssueDetails(Audits.GenericIssueDetails genericIssueDetails) {
            set("genericIssueDetails", genericIssueDetails);
            return this;
        }
        /**
         * Sets the deprecationIssueDetails field.
         * @param deprecationIssueDetails field value
         * @return this model
         */
        public InspectorIssueDetails deprecationIssueDetails(Audits.DeprecationIssueDetails deprecationIssueDetails) {
            set("deprecationIssueDetails", deprecationIssueDetails);
            return this;
        }
        /**
         * Sets the clientHintIssueDetails field.
         * @param clientHintIssueDetails field value
         * @return this model
         */
        public InspectorIssueDetails clientHintIssueDetails(Audits.ClientHintIssueDetails clientHintIssueDetails) {
            set("clientHintIssueDetails", clientHintIssueDetails);
            return this;
        }
        /**
         * Sets the federatedAuthRequestIssueDetails field.
         * @param federatedAuthRequestIssueDetails field value
         * @return this model
         */
        public InspectorIssueDetails federatedAuthRequestIssueDetails(Audits.FederatedAuthRequestIssueDetails federatedAuthRequestIssueDetails) {
            set("federatedAuthRequestIssueDetails", federatedAuthRequestIssueDetails);
            return this;
        }
        /**
         * Sets the bounceTrackingIssueDetails field.
         * @param bounceTrackingIssueDetails field value
         * @return this model
         */
        public InspectorIssueDetails bounceTrackingIssueDetails(Audits.BounceTrackingIssueDetails bounceTrackingIssueDetails) {
            set("bounceTrackingIssueDetails", bounceTrackingIssueDetails);
            return this;
        }
        /**
         * Sets the cookieDeprecationMetadataIssueDetails field.
         * @param cookieDeprecationMetadataIssueDetails field value
         * @return this model
         */
        public InspectorIssueDetails cookieDeprecationMetadataIssueDetails(Audits.CookieDeprecationMetadataIssueDetails cookieDeprecationMetadataIssueDetails) {
            set("cookieDeprecationMetadataIssueDetails", cookieDeprecationMetadataIssueDetails);
            return this;
        }
        /**
         * Sets the stylesheetLoadingIssueDetails field.
         * @param stylesheetLoadingIssueDetails field value
         * @return this model
         */
        public InspectorIssueDetails stylesheetLoadingIssueDetails(Audits.StylesheetLoadingIssueDetails stylesheetLoadingIssueDetails) {
            set("stylesheetLoadingIssueDetails", stylesheetLoadingIssueDetails);
            return this;
        }
        /**
         * Sets the propertyRuleIssueDetails field.
         * @param propertyRuleIssueDetails field value
         * @return this model
         */
        public InspectorIssueDetails propertyRuleIssueDetails(Audits.PropertyRuleIssueDetails propertyRuleIssueDetails) {
            set("propertyRuleIssueDetails", propertyRuleIssueDetails);
            return this;
        }
        /**
         * Sets the federatedAuthUserInfoRequestIssueDetails field.
         * @param federatedAuthUserInfoRequestIssueDetails field value
         * @return this model
         */
        public InspectorIssueDetails federatedAuthUserInfoRequestIssueDetails(Audits.FederatedAuthUserInfoRequestIssueDetails federatedAuthUserInfoRequestIssueDetails) {
            set("federatedAuthUserInfoRequestIssueDetails", federatedAuthUserInfoRequestIssueDetails);
            return this;
        }
        /**
         * Sets the sharedDictionaryIssueDetails field.
         * @param sharedDictionaryIssueDetails field value
         * @return this model
         */
        public InspectorIssueDetails sharedDictionaryIssueDetails(Audits.SharedDictionaryIssueDetails sharedDictionaryIssueDetails) {
            set("sharedDictionaryIssueDetails", sharedDictionaryIssueDetails);
            return this;
        }
        /**
         * Sets the elementAccessibilityIssueDetails field.
         * @param elementAccessibilityIssueDetails field value
         * @return this model
         */
        public InspectorIssueDetails elementAccessibilityIssueDetails(Audits.ElementAccessibilityIssueDetails elementAccessibilityIssueDetails) {
            set("elementAccessibilityIssueDetails", elementAccessibilityIssueDetails);
            return this;
        }
        /**
         * Sets the sriMessageSignatureIssueDetails field.
         * @param sriMessageSignatureIssueDetails field value
         * @return this model
         */
        public InspectorIssueDetails sriMessageSignatureIssueDetails(Audits.SRIMessageSignatureIssueDetails sriMessageSignatureIssueDetails) {
            set("sriMessageSignatureIssueDetails", sriMessageSignatureIssueDetails);
            return this;
        }
        /**
         * Sets the unencodedDigestIssueDetails field.
         * @param unencodedDigestIssueDetails field value
         * @return this model
         */
        public InspectorIssueDetails unencodedDigestIssueDetails(Audits.UnencodedDigestIssueDetails unencodedDigestIssueDetails) {
            set("unencodedDigestIssueDetails", unencodedDigestIssueDetails);
            return this;
        }
        /**
         * Sets the connectionAllowlistIssueDetails field.
         * @param connectionAllowlistIssueDetails field value
         * @return this model
         */
        public InspectorIssueDetails connectionAllowlistIssueDetails(Audits.ConnectionAllowlistIssueDetails connectionAllowlistIssueDetails) {
            set("connectionAllowlistIssueDetails", connectionAllowlistIssueDetails);
            return this;
        }
        /**
         * Sets the userReidentificationIssueDetails field.
         * @param userReidentificationIssueDetails field value
         * @return this model
         */
        public InspectorIssueDetails userReidentificationIssueDetails(Audits.UserReidentificationIssueDetails userReidentificationIssueDetails) {
            set("userReidentificationIssueDetails", userReidentificationIssueDetails);
            return this;
        }
        /**
         * Sets the permissionElementIssueDetails field.
         * @param permissionElementIssueDetails field value
         * @return this model
         */
        public InspectorIssueDetails permissionElementIssueDetails(Audits.PermissionElementIssueDetails permissionElementIssueDetails) {
            set("permissionElementIssueDetails", permissionElementIssueDetails);
            return this;
        }
        /**
         * Sets the performanceIssueDetails field.
         * @param performanceIssueDetails field value
         * @return this model
         */
        public InspectorIssueDetails performanceIssueDetails(Audits.PerformanceIssueDetails performanceIssueDetails) {
            set("performanceIssueDetails", performanceIssueDetails);
            return this;
        }
        /**
         * Sets the selectivePermissionsInterventionIssueDetails field.
         * @param selectivePermissionsInterventionIssueDetails field value
         * @return this model
         */
        public InspectorIssueDetails selectivePermissionsInterventionIssueDetails(Audits.SelectivePermissionsInterventionIssueDetails selectivePermissionsInterventionIssueDetails) {
            set("selectivePermissionsInterventionIssueDetails", selectivePermissionsInterventionIssueDetails);
            return this;
        }
        /**
         * Sets the emailVerificationRequestIssueDetails field.
         * @param emailVerificationRequestIssueDetails field value
         * @return this model
         */
        public InspectorIssueDetails emailVerificationRequestIssueDetails(Audits.EmailVerificationRequestIssueDetails emailVerificationRequestIssueDetails) {
            set("emailVerificationRequestIssueDetails", emailVerificationRequestIssueDetails);
            return this;
        }
    }
    /**
     * A unique id for a DevTools inspector issue. Allows other entities (e.g. exceptions, CDP message, console messages, etc.) to reference an issue.
     */
    public static final class IssueId implements CdpValue<String> {
        public final String value;
        public IssueId(String value) { this.value = java.util.Objects.requireNonNull(value); }
        @Override public String value() { return value; }
        @Override public boolean equals(@org.jspecify.annotations.Nullable Object other) {
            if (this == other) return true;
            if (!(other instanceof IssueId)) return false;
            return value.equals(((IssueId) other).value);
        }
        @Override public int hashCode() { return value.hashCode(); }
        @Override public String toString() { return "IssueId(" + value + ")"; }
    }
    /**
     * An inspector issue reported from the back-end.
     */
    public static final class InspectorIssue extends CdpObject {
        public InspectorIssue() {}
        private InspectorIssue(Map<String, Object> values) { super(values); }
        public static InspectorIssue fromMap(Map<String, Object> values) {
            return new InspectorIssue(values);
        }
        /**
         * Returns the code field.
         * @return the protocol field value
         */
        public Audits.InspectorIssueCode code() {
            return require(CODE);
        }
        /**
         * Returns the details field.
         * @return the protocol field value
         */
        public Audits.InspectorIssueDetails details() {
            return require(DETAILS);
        }
        /**
         * A unique id for this issue. May be omitted if no other entity (e.g. exception, CDP message, etc.) is referencing this issue.
         * @return the protocol field value, empty when absent
         */
        public Optional<Audits.IssueId> issueId() {
            return optional(ISSUE_ID);
        }
        /**
         * Sets the code field.
         * @param code field value
         * @return this model
         */
        public InspectorIssue code(Audits.InspectorIssueCode code) {
            set("code", code);
            return this;
        }
        /**
         * Sets the details field.
         * @param details field value
         * @return this model
         */
        public InspectorIssue details(Audits.InspectorIssueDetails details) {
            set("details", details);
            return this;
        }
        /**
         * A unique id for this issue. May be omitted if no other entity (e.g. exception, CDP message, etc.) is referencing this issue.
         * @param issueId field value
         * @return this model
         */
        public InspectorIssue issueId(Audits.IssueId issueId) {
            set("issueId", issueId);
            return this;
        }
    }
    /**
     * Returns the response body and size if it were re-encoded with the specified settings. Only applies to images.
     */
    public static final class GetEncodedResponseRequest extends CdpObject {
        public GetEncodedResponseRequest() {}
        public static GetEncodedResponseRequest fromMap(Map<String, Object> values) {
            final GetEncodedResponseRequest instance_ = new GetEncodedResponseRequest();
            if (values != null) instance_.values.putAll(values);
            return instance_;
        }
        /**
         * Identifier of the network request to get content for.
         * @return the protocol field value
         */
        public Network.RequestId requestId() {
            return require(REQUEST_ID);
        }
        /**
         * The encoding to use.
         * @return the protocol field value
         */
        public GetEncodedResponseEncodingValues encoding() {
            return require(ENCODING);
        }
        /**
         * The quality of the encoding (0-1). (defaults to 1)
         * @return the protocol field value, empty when absent
         */
        public OptionalDouble quality() {
            return optionalDouble("quality");
        }
        /**
         * Whether to only return the size information (defaults to false).
         * @return the protocol field value, empty when absent
         */
        public Optional<Boolean> sizeOnly() {
            return optionalBoolean("sizeOnly");
        }
        /**
         * Identifier of the network request to get content for.
         * @param requestId field value
         * @return this model
         */
        public GetEncodedResponseRequest requestId(Network.RequestId requestId) {
            set("requestId", requestId);
            return this;
        }
        /**
         * The encoding to use.
         * @param encoding field value
         * @return this model
         */
        public GetEncodedResponseRequest encoding(GetEncodedResponseEncodingValues encoding) {
            set("encoding", encoding);
            return this;
        }
        /**
         * The quality of the encoding (0-1). (defaults to 1)
         * @param quality field value
         * @return this model
         */
        public GetEncodedResponseRequest quality(double quality) {
            set("quality", quality);
            return this;
        }
        /**
         * Whether to only return the size information (defaults to false).
         * @param sizeOnly field value
         * @return this model
         */
        public GetEncodedResponseRequest sizeOnly(boolean sizeOnly) {
            set("sizeOnly", sizeOnly);
            return this;
        }
    }
    /**
     * Returns the response body and size if it were re-encoded with the specified settings. Only applies to images.
     */
    public static final class GetEncodedResponseResult extends CdpObject {
        public GetEncodedResponseResult() {}
        private GetEncodedResponseResult(Map<String, Object> values) { super(values); }
        public static GetEncodedResponseResult fromMap(Map<String, Object> values) {
            return new GetEncodedResponseResult(values);
        }
        /**
         * The encoded body as a base64 string. Omitted if sizeOnly is true. (Encoded as a base64 string when passed over JSON)
         * @return the protocol field value, empty when absent
         */
        public Optional<String> body() {
            return optionalString("body");
        }
        /**
         * Size before re-encoding.
         * @return the protocol field value
         */
        public long originalSize() {
            return requireLong("originalSize");
        }
        /**
         * Size after re-encoding.
         * @return the protocol field value
         */
        public long encodedSize() {
            return requireLong("encodedSize");
        }
        /**
         * The encoded body as a base64 string. Omitted if sizeOnly is true. (Encoded as a base64 string when passed over JSON)
         * @param body field value
         * @return this model
         */
        public GetEncodedResponseResult body(String body) {
            set("body", body);
            return this;
        }
        /**
         * Size before re-encoding.
         * @param originalSize field value
         * @return this model
         */
        public GetEncodedResponseResult originalSize(long originalSize) {
            set("originalSize", originalSize);
            return this;
        }
        /**
         * Size after re-encoding.
         * @param encodedSize field value
         * @return this model
         */
        public GetEncodedResponseResult encodedSize(long encodedSize) {
            set("encodedSize", encodedSize);
            return this;
        }
    }
    /**
     * Payload of the Audits.issueAdded event.
     */
    public static final class IssueAddedEvent extends CdpObject {
        public IssueAddedEvent() {}
        private IssueAddedEvent(Map<String, Object> values) { super(values); }
        public static IssueAddedEvent fromMap(Map<String, Object> values) {
            return new IssueAddedEvent(values);
        }
        /**
         * Returns the issue field.
         * @return the protocol field value
         */
        public Audits.InspectorIssue issue() {
            return require(ISSUE);
        }
        /**
         * Sets the issue field.
         * @param issue field value
         * @return this model
         */
        public IssueAddedEvent issue(Audits.InspectorIssue issue) {
            set("issue", issue);
            return this;
        }
    }
    /**
     * The encoding to use.
     */
    public enum GetEncodedResponseEncodingValues implements CdpValue<String> {
        WEBP("webp"),
        JPEG("jpeg"),
        PNG("png");
        public final String value;
        GetEncodedResponseEncodingValues(String value) { this.value = value; }
        @Override public String value() { return value; }
        public static GetEncodedResponseEncodingValues of(String value) {
            for (GetEncodedResponseEncodingValues constant : values()) {
                if (constant.value.equals(value)) return constant;
            }
            throw new IllegalArgumentException("Unknown GetEncodedResponseEncodingValues value: " + value);
        }
    }
    public static final class Client extends CdpDomainClient {
        public Client(CdpClient client) { super(client); }
        /**
         * Returns the response body and size if it were re-encoded with the specified settings. Only applies to images.
         * @param requestId protocol value
         * @param encoding protocol value
         * @param quality protocol value
         * @param sizeOnly protocol value
         * @return a stage completing with the command result
         */
        public CompletionStage<GetEncodedResponseResult> getEncodedResponse(Network.RequestId requestId, GetEncodedResponseEncodingValues encoding, @Nullable Double quality, @Nullable Boolean sizeOnly) {
            return call("Audits.getEncodedResponse", params("requestId", requestId, "encoding", encoding, "quality", quality, "sizeOnly", sizeOnly), GetEncodedResponseResult::fromMap);
        }
        /**
         * Returns the response body and size if it were re-encoded with the specified settings. Only applies to images.
         * @param requestId protocol value
         * @param encoding protocol value
         * @return a stage completing with the command result
         */
        public CompletionStage<GetEncodedResponseResult> getEncodedResponse(Network.RequestId requestId, GetEncodedResponseEncodingValues encoding) {
            return call("Audits.getEncodedResponse", params("requestId", requestId, "encoding", encoding), GetEncodedResponseResult::fromMap);
        }
        /**
         * Returns the response body and size if it were re-encoded with the specified settings. Only applies to images.
         * @param request request parameters
         * @return a stage completing with the command result
         */
        public CompletionStage<GetEncodedResponseResult> getEncodedResponse(GetEncodedResponseRequest request) {
            return call("Audits.getEncodedResponse", request, GetEncodedResponseResult::fromMap);
        }
        /**
         * Disables issues domain, prevents further issues from being reported to the client.
         * @return a stage completing when the command completes
         */
        public CompletionStage<Void> disable() {
            return call("Audits.disable");
        }
        /**
         * Enables issues domain, sends the issues collected so far to the client by means of the {@code issueAdded} event.
         * @return a stage completing when the command completes
         */
        public CompletionStage<Void> enable() {
            return call("Audits.enable");
        }
        /**
         * Runs the form issues check for the target page. Found issues are reported using Audits.issueAdded event.
         * @return a stage completing with the command result
         */
        public CompletionStage<java.util.List<Audits.GenericIssueDetails>> checkFormsIssues() {
            return call("Audits.checkFormsIssues", FORM_ISSUES::require);
        }
        /**
         * Subscribes to Audits.issueAdded.
         * @param handler event callback
         * @return a removable subscription
         */
        public CdpSubscription onIssueAdded(Consumer<IssueAddedEvent> handler) {
            return on("Audits.issueAdded", IssueAddedEvent::fromMap, handler);
        }
    }
}
