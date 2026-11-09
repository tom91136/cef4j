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
 * Network domain allows tracking network activities of the page. It exposes information about http, file, data and other requests and responses, their headers, bodies, timing, etc.
 * @see <a href="https://chromium.googlesource.com/chromium/src/+/refs/tags/150.0.7871.213/third_party/blink/public/devtools_protocol/domains/Network.pdl">Pinned protocol source</a>
 */
@Generated("./mvnw generate-sources -pl cef4j-cdp -Dcef.version=150.0.18+gdb11278+chromium-150.0.7871.213")
@SuppressWarnings({"EscapedEntity", "InvalidParam", "JavaLangClash", "MissingSummary", "Unchecked", "UnusedMethod"})
@NullableBoundary("CDP wire objects use null for absent optional fields")
public final class Network {
    private Network() {}
    private static final CdpField<Network.ServiceWorkerRouterSource> ACTUAL_SOURCE_TYPE = CdpField.of("actualSourceType", value -> Network.ServiceWorkerRouterSource.of((String) value));
    private static final CdpField<Network.AdAncestry> AD_SCRIPT_ANCESTRY = CdpField.of("adScriptAncestry", value -> java.util.Objects.requireNonNull(Network.AdAncestry.fromMap(CdpObject.requireObjectMap(value))));
    private static final CdpField<java.util.List<String>> ALLOWED_REFRESH_INITIATORS = CdpField.of("allowedRefreshInitiators", value -> CdpObject.requireList(value, element0 -> (String) element0));
    private static final CdpField<Network.AlternateProtocolUsage> ALTERNATE_PROTOCOL_USAGE = CdpField.of("alternateProtocolUsage", value -> Network.AlternateProtocolUsage.of((String) value));
    private static final CdpField<java.util.List<Network.AdScriptIdentifier>> ANCESTRY_CHAIN = CdpField.of("ancestryChain", value -> CdpObject.requireList(value, element0 -> java.util.Objects.requireNonNull(Network.AdScriptIdentifier.fromMap(CdpObject.requireObjectMap(element0)))));
    private static final CdpField<java.util.List<Network.AssociatedCookie>> ASSOCIATED_COOKIES = CdpField.of("associatedCookies", value -> CdpObject.requireList(value, element0 -> java.util.Objects.requireNonNull(Network.AssociatedCookie.fromMap(CdpObject.requireObjectMap(element0)))));
    private static final CdpField<Network.AuthChallenge> AUTH_CHALLENGE = CdpField.of("authChallenge", value -> java.util.Objects.requireNonNull(Network.AuthChallenge.fromMap(CdpObject.requireObjectMap(value))));
    private static final CdpField<Network.AuthChallengeResponse> AUTH_CHALLENGE_RESPONSE = CdpField.of("authChallengeResponse", value -> java.util.Objects.requireNonNull(Network.AuthChallengeResponse.fromMap(CdpObject.requireObjectMap(value))));
    private static final CdpField<java.util.List<Network.BlockedSetCookieWithReason>> BLOCKED_COOKIES = CdpField.of("blockedCookies", value -> CdpObject.requireList(value, element0 -> java.util.Objects.requireNonNull(Network.BlockedSetCookieWithReason.fromMap(CdpObject.requireObjectMap(element0)))));
    private static final CdpField<Network.BlockedReason> BLOCKED_REASON = CdpField.of("blockedReason", value -> Network.BlockedReason.of((String) value));
    private static final CdpField<java.util.List<Network.SetCookieBlockedReason>> BLOCKED_REASONS = CdpField.of("blockedReasons", value -> CdpObject.requireList(value, element0 -> Network.SetCookieBlockedReason.of((String) element0)));
    private static final CdpField<java.util.List<Network.CookieBlockedReason>> BLOCKED_REASONS_2 = CdpField.of("blockedReasons", value -> CdpObject.requireList(value, element0 -> Network.CookieBlockedReason.of((String) element0)));
    private static final CdpField<String> BUFFERED_DATA = CdpField.of("bufferedData", value -> (String) value);
    private static final CdpField<java.util.List<String>> CERTIFICATES = CdpField.of("certificates", value -> CdpObject.requireList(value, element0 -> (String) element0));
    private static final CdpField<Security.CertificateId> CERTIFICATE_ID = CdpField.of("certificateId", value -> new Security.CertificateId(((Number) value).longValue()));
    private static final CdpField<Network.CertificateTransparencyCompliance> CERTIFICATE_TRANSPARENCY_COMPLIANCE = CdpField.of("certificateTransparencyCompliance", value -> Network.CertificateTransparencyCompliance.of((String) value));
    private static final CdpField<Network.ChallengeEventDetails> CHALLENGE_EVENT_DETAILS = CdpField.of("challengeEventDetails", value -> java.util.Objects.requireNonNull(Network.ChallengeEventDetails.fromMap(CdpObject.requireObjectMap(value))));
    private static final CdpField<ChallengeEventDetails.ChallengeResultValues> CHALLENGE_RESULT = CdpField.of("challengeResult", value -> ChallengeEventDetails.ChallengeResultValues.of((String) value));
    private static final CdpField<Network.ClientSecurityState> CLIENT_SECURITY_STATE = CdpField.of("clientSecurityState", value -> java.util.Objects.requireNonNull(Network.ClientSecurityState.fromMap(CdpObject.requireObjectMap(value))));
    private static final CdpField<Network.CrossOriginEmbedderPolicyStatus> COEP = CdpField.of("coep", value -> java.util.Objects.requireNonNull(Network.CrossOriginEmbedderPolicyStatus.fromMap(CdpObject.requireObjectMap(value))));
    private static final CdpField<Network.ConnectionType> CONNECTION_TYPE = CdpField.of("connectionType", value -> Network.ConnectionType.of((String) value));
    private static final CdpField<Network.ConnectTiming> CONNECT_TIMING = CdpField.of("connectTiming", value -> java.util.Objects.requireNonNull(Network.ConnectTiming.fromMap(CdpObject.requireObjectMap(value))));
    private static final CdpField<Network.Cookie> COOKIE = CdpField.of("cookie", value -> java.util.Objects.requireNonNull(Network.Cookie.fromMap(CdpObject.requireObjectMap(value))));
    private static final CdpField<java.util.List<Network.CookieParam>> COOKIES = CdpField.of("cookies", value -> CdpObject.requireList(value, element0 -> java.util.Objects.requireNonNull(Network.CookieParam.fromMap(CdpObject.requireObjectMap(element0)))));
    private static final CdpField<java.util.List<Network.Cookie>> COOKIES_2 = CdpField.of("cookies", value -> CdpObject.requireList(value, element0 -> java.util.Objects.requireNonNull(Network.Cookie.fromMap(CdpObject.requireObjectMap(element0)))));
    private static final CdpField<java.util.List<Network.DeviceBoundSessionCookieCraving>> COOKIE_CRAVINGS = CdpField.of("cookieCravings", value -> CdpObject.requireList(value, element0 -> java.util.Objects.requireNonNull(Network.DeviceBoundSessionCookieCraving.fromMap(CdpObject.requireObjectMap(element0)))));
    private static final CdpField<Network.CookiePartitionKey> COOKIE_PARTITION_KEY = CdpField.of("cookiePartitionKey", value -> java.util.Objects.requireNonNull(Network.CookiePartitionKey.fromMap(CdpObject.requireObjectMap(value))));
    private static final CdpField<Network.CrossOriginOpenerPolicyStatus> COOP = CdpField.of("coop", value -> java.util.Objects.requireNonNull(Network.CrossOriginOpenerPolicyStatus.fromMap(CdpObject.requireObjectMap(value))));
    private static final CdpField<Network.CorsError> CORS_ERROR = CdpField.of("corsError", value -> Network.CorsError.of((String) value));
    private static final CdpField<Network.CorsErrorStatus> CORS_ERROR_STATUS = CdpField.of("corsErrorStatus", value -> java.util.Objects.requireNonNull(Network.CorsErrorStatus.fromMap(CdpObject.requireObjectMap(value))));
    private static final CdpField<Network.CreationEventDetails> CREATION_EVENT_DETAILS = CdpField.of("creationEventDetails", value -> java.util.Objects.requireNonNull(Network.CreationEventDetails.fromMap(CdpObject.requireObjectMap(value))));
    private static final CdpField<java.util.List<Network.ContentSecurityPolicyStatus>> CSP = CdpField.of("csp", value -> CdpObject.requireList(value, element0 -> java.util.Objects.requireNonNull(Network.ContentSecurityPolicyStatus.fromMap(CdpObject.requireObjectMap(element0)))));
    private static final CdpField<Runtime.UniqueDebuggerId> DEBUGGER_ID = CdpField.of("debuggerId", value -> new Runtime.UniqueDebuggerId((String) value));
    private static final CdpField<TerminationEventDetails.DeletionReasonValues> DELETION_REASON = CdpField.of("deletionReason", value -> TerminationEventDetails.DeletionReasonValues.of((String) value));
    private static final CdpField<java.util.List<Network.DeviceBoundSessionWithUsage>> DEVICE_BOUND_SESSION_USAGES = CdpField.of("deviceBoundSessionUsages", value -> CdpObject.requireList(value, element0 -> java.util.Objects.requireNonNull(Network.DeviceBoundSessionWithUsage.fromMap(CdpObject.requireObjectMap(element0)))));
    private static final CdpField<Network.DirectSocketDnsQueryType> DNS_QUERY_TYPE = CdpField.of("dnsQueryType", value -> Network.DirectSocketDnsQueryType.of((String) value));
    private static final CdpField<java.util.List<Network.ContentEncoding>> ENCODINGS = CdpField.of("encodings", value -> CdpObject.requireList(value, element0 -> Network.ContentEncoding.of((String) element0)));
    private static final CdpField<java.util.List<Network.ReportingApiEndpoint>> ENDPOINTS = CdpField.of("endpoints", value -> CdpObject.requireList(value, element0 -> java.util.Objects.requireNonNull(Network.ReportingApiEndpoint.fromMap(CdpObject.requireObjectMap(element0)))));
    private static final CdpField<java.util.List<Network.SignedExchangeError>> ERRORS = CdpField.of("errors", value -> CdpObject.requireList(value, element0 -> java.util.Objects.requireNonNull(Network.SignedExchangeError.fromMap(CdpObject.requireObjectMap(element0)))));
    private static final CdpField<Network.SignedExchangeErrorField> ERROR_FIELD = CdpField.of("errorField", value -> Network.SignedExchangeErrorField.of((String) value));
    private static final CdpField<Network.ErrorReason> ERROR_REASON = CdpField.of("errorReason", value -> Network.ErrorReason.of((String) value));
    private static final CdpField<Network.DeviceBoundSessionEventId> EVENT_ID = CdpField.of("eventId", value -> new Network.DeviceBoundSessionEventId((String) value));
    private static final CdpField<java.util.List<Network.ExemptedSetCookieWithReason>> EXEMPTED_COOKIES = CdpField.of("exemptedCookies", value -> CdpObject.requireList(value, element0 -> java.util.Objects.requireNonNull(Network.ExemptedSetCookieWithReason.fromMap(CdpObject.requireObjectMap(element0)))));
    private static final CdpField<Network.CookieExemptionReason> EXEMPTION_REASON = CdpField.of("exemptionReason", value -> Network.CookieExemptionReason.of((String) value));
    private static final CdpField<Network.TimeSinceEpoch> EXPIRES = CdpField.of("expires", value -> new Network.TimeSinceEpoch(((Number) value).doubleValue()));
    private static final CdpField<Network.TimeSinceEpoch> EXPIRY_DATE = CdpField.of("expiryDate", value -> new Network.TimeSinceEpoch(((Number) value).doubleValue()));
    private static final CdpField<Network.DeviceBoundSessionFailedRequest> FAILED_REQUEST = CdpField.of("failedRequest", value -> java.util.Objects.requireNonNull(Network.DeviceBoundSessionFailedRequest.fromMap(CdpObject.requireObjectMap(value))));
    private static final CdpField<Network.DeviceBoundSessionFetchResult> FETCH_RESULT = CdpField.of("fetchResult", value -> Network.DeviceBoundSessionFetchResult.of((String) value));
    private static final CdpField<Page.FrameId> FRAME_ID = CdpField.of("frameId", value -> new Page.FrameId((String) value));
    private static final CdpField<Network.SignedExchangeHeader> HEADER = CdpField.of("header", value -> java.util.Objects.requireNonNull(Network.SignedExchangeHeader.fromMap(CdpObject.requireObjectMap(value))));
    private static final CdpField<Network.ReportId> ID = CdpField.of("id", value -> new Network.ReportId((String) value));
    private static final CdpField<Network.RequestId> IDENTIFIER = CdpField.of("identifier", value -> new Network.RequestId((String) value));
    private static final CdpField<Network.DeviceBoundSessionInclusionRules> INCLUSION_RULES = CdpField.of("inclusionRules", value -> java.util.Objects.requireNonNull(Network.DeviceBoundSessionInclusionRules.fromMap(CdpObject.requireObjectMap(value))));
    private static final CdpField<Network.SignedExchangeInfo> INFO = CdpField.of("info", value -> java.util.Objects.requireNonNull(Network.SignedExchangeInfo.fromMap(CdpObject.requireObjectMap(value))));
    private static final CdpField<Network.ResourcePriority> INITIAL_PRIORITY = CdpField.of("initialPriority", value -> Network.ResourcePriority.of((String) value));
    private static final CdpField<Network.Initiator> INITIATOR = CdpField.of("initiator", value -> java.util.Objects.requireNonNull(Network.Initiator.fromMap(CdpObject.requireObjectMap(value))));
    private static final CdpField<Network.IPAddressSpace> INITIATOR_IPADDRESS_SPACE = CdpField.of("initiatorIPAddressSpace", value -> Network.IPAddressSpace.of((String) value));
    private static final CdpField<Network.InterceptionId> INTERCEPTION_ID = CdpField.of("interceptionId", value -> new Network.InterceptionId((String) value));
    private static final CdpField<Network.InterceptionStage> INTERCEPTION_STAGE = CdpField.of("interceptionStage", value -> Network.InterceptionStage.of((String) value));
    private static final CdpField<java.util.List<String>> ISSUERS = CdpField.of("issuers", value -> CdpObject.requireList(value, element0 -> (String) element0));
    private static final CdpField<Network.DeviceBoundSessionKey> KEY = CdpField.of("key", value -> java.util.Objects.requireNonNull(Network.DeviceBoundSessionKey.fromMap(CdpObject.requireObjectMap(value))));
    private static final CdpField<Network.LoaderId> LOADER_ID = CdpField.of("loaderId", value -> new Network.LoaderId((String) value));
    private static final CdpField<Network.LocalNetworkAccessRequestPolicy> LOCAL_NETWORK_ACCESS_REQUEST_POLICY = CdpField.of("localNetworkAccessRequestPolicy", value -> Network.LocalNetworkAccessRequestPolicy.of((String) value));
    private static final CdpField<java.util.List<Network.NetworkConditions>> MATCHED_NETWORK_CONDITIONS = CdpField.of("matchedNetworkConditions", value -> CdpObject.requireList(value, element0 -> java.util.Objects.requireNonNull(Network.NetworkConditions.fromMap(CdpObject.requireObjectMap(element0)))));
    private static final CdpField<Network.ServiceWorkerRouterSource> MATCHED_SOURCE_TYPE = CdpField.of("matchedSourceType", value -> Network.ServiceWorkerRouterSource.of((String) value));
    private static final CdpField<Network.DirectUDPMessage> MESSAGE = CdpField.of("message", value -> java.util.Objects.requireNonNull(Network.DirectUDPMessage.fromMap(CdpObject.requireObjectMap(value))));
    private static final CdpField<Security.MixedContentType> MIXED_CONTENT_TYPE = CdpField.of("mixedContentType", value -> Security.MixedContentType.of((String) value));
    private static final CdpField<Network.ResourcePriority> NEW_PRIORITY = CdpField.of("newPriority", value -> Network.ResourcePriority.of((String) value));
    private static final CdpField<Network.DeviceBoundSession> NEW_SESSION = CdpField.of("newSession", value -> java.util.Objects.requireNonNull(Network.DeviceBoundSession.fromMap(CdpObject.requireObjectMap(value))));
    private static final CdpField<Network.TrustTokenOperationType> OPERATION = CdpField.of("operation", value -> Network.TrustTokenOperationType.of((String) value));
    private static final CdpField<Network.LoadNetworkResourceOptions> OPTIONS = CdpField.of("options", value -> java.util.Objects.requireNonNull(Network.LoadNetworkResourceOptions.fromMap(CdpObject.requireObjectMap(value))));
    private static final CdpField<Network.DirectTCPSocketOptions> OPTIONS_2 = CdpField.of("options", value -> java.util.Objects.requireNonNull(Network.DirectTCPSocketOptions.fromMap(CdpObject.requireObjectMap(value))));
    private static final CdpField<Network.DirectUDPSocketOptions> OPTIONS_3 = CdpField.of("options", value -> java.util.Objects.requireNonNull(Network.DirectUDPSocketOptions.fromMap(CdpObject.requireObjectMap(value))));
    private static final CdpField<Network.Response> OUTER_RESPONSE = CdpField.of("outerResponse", value -> java.util.Objects.requireNonNull(Network.Response.fromMap(CdpObject.requireObjectMap(value))));
    private static final CdpField<Network.CookiePartitionKey> PARTITION_KEY = CdpField.of("partitionKey", value -> java.util.Objects.requireNonNull(Network.CookiePartitionKey.fromMap(CdpObject.requireObjectMap(value))));
    private static final CdpField<java.util.List<Network.RequestPattern>> PATTERNS = CdpField.of("patterns", value -> CdpObject.requireList(value, element0 -> java.util.Objects.requireNonNull(Network.RequestPattern.fromMap(CdpObject.requireObjectMap(element0)))));
    private static final CdpField<java.util.List<Network.PostDataEntry>> POST_DATA_ENTRIES = CdpField.of("postDataEntries", value -> CdpObject.requireList(value, element0 -> java.util.Objects.requireNonNull(Network.PostDataEntry.fromMap(CdpObject.requireObjectMap(element0)))));
    private static final CdpField<Network.CookiePriority> PRIORITY = CdpField.of("priority", value -> Network.CookiePriority.of((String) value));
    private static final CdpField<Network.Response> REDIRECT_RESPONSE = CdpField.of("redirectResponse", value -> java.util.Objects.requireNonNull(Network.Response.fromMap(CdpObject.requireObjectMap(value))));
    private static final CdpField<Request.ReferrerPolicyValues> REFERRER_POLICY = CdpField.of("referrerPolicy", value -> Request.ReferrerPolicyValues.of((String) value));
    private static final CdpField<Network.RefreshEventDetails> REFRESH_EVENT_DETAILS = CdpField.of("refreshEventDetails", value -> java.util.Objects.requireNonNull(Network.RefreshEventDetails.fromMap(CdpObject.requireObjectMap(value))));
    private static final CdpField<TrustTokenParams.RefreshPolicyValues> REFRESH_POLICY = CdpField.of("refreshPolicy", value -> TrustTokenParams.RefreshPolicyValues.of((String) value));
    private static final CdpField<RefreshEventDetails.RefreshResultValues> REFRESH_RESULT = CdpField.of("refreshResult", value -> RefreshEventDetails.RefreshResultValues.of((String) value));
    private static final CdpField<Network.RenderBlockingBehavior> RENDER_BLOCKING_BEHAVIOR = CdpField.of("renderBlockingBehavior", value -> Network.RenderBlockingBehavior.of((String) value));
    private static final CdpField<Network.ReportingApiReport> REPORT = CdpField.of("report", value -> java.util.Objects.requireNonNull(Network.ReportingApiReport.fromMap(CdpObject.requireObjectMap(value))));
    private static final CdpField<Network.CrossOriginOpenerPolicyValue> REPORT_ONLY_VALUE = CdpField.of("reportOnlyValue", value -> Network.CrossOriginOpenerPolicyValue.of((String) value));
    private static final CdpField<Network.CrossOriginEmbedderPolicyValue> REPORT_ONLY_VALUE_2 = CdpField.of("reportOnlyValue", value -> Network.CrossOriginEmbedderPolicyValue.of((String) value));
    private static final CdpField<Network.Request> REQUEST = CdpField.of("request", value -> java.util.Objects.requireNonNull(Network.Request.fromMap(CdpObject.requireObjectMap(value))));
    private static final CdpField<Network.WebSocketRequest> REQUEST_2 = CdpField.of("request", value -> java.util.Objects.requireNonNull(Network.WebSocketRequest.fromMap(CdpObject.requireObjectMap(value))));
    private static final CdpField<Network.RequestId> REQUEST_ID = CdpField.of("requestId", value -> new Network.RequestId((String) value));
    private static final CdpField<Network.LoadNetworkResourcePageResult> RESOURCE = CdpField.of("resource", value -> java.util.Objects.requireNonNull(Network.LoadNetworkResourcePageResult.fromMap(CdpObject.requireObjectMap(value))));
    private static final CdpField<Network.IPAddressSpace> RESOURCE_IPADDRESS_SPACE = CdpField.of("resourceIPAddressSpace", value -> Network.IPAddressSpace.of((String) value));
    private static final CdpField<Network.ResourceType> RESOURCE_TYPE = CdpField.of("resourceType", value -> Network.ResourceType.of((String) value));
    private static final CdpField<Network.Response> RESPONSE = CdpField.of("response", value -> java.util.Objects.requireNonNull(Network.Response.fromMap(CdpObject.requireObjectMap(value))));
    private static final CdpField<AuthChallengeResponse.ResponseValues> RESPONSE_2 = CdpField.of("response", value -> AuthChallengeResponse.ResponseValues.of((String) value));
    private static final CdpField<Network.WebSocketFrame> RESPONSE_3 = CdpField.of("response", value -> java.util.Objects.requireNonNull(Network.WebSocketFrame.fromMap(CdpObject.requireObjectMap(value))));
    private static final CdpField<Network.WebSocketResponse> RESPONSE_4 = CdpField.of("response", value -> java.util.Objects.requireNonNull(Network.WebSocketResponse.fromMap(CdpObject.requireObjectMap(value))));
    private static final CdpField<Network.ErrorReason> RESPONSE_ERROR_REASON = CdpField.of("responseErrorReason", value -> Network.ErrorReason.of((String) value));
    private static final CdpField<Network.TimeSinceEpoch> RESPONSE_TIME = CdpField.of("responseTime", value -> new Network.TimeSinceEpoch(((Number) value).doubleValue()));
    private static final CdpField<Boolean> RESULT = CdpField.of("result", value -> (Boolean) value);
    private static final CdpField<java.util.List<Debugger.SearchMatch>> RESULT_2 = CdpField.of("result", value -> CdpObject.requireList(value, element0 -> java.util.Objects.requireNonNull(Debugger.SearchMatch.fromMap(CdpObject.requireObjectMap(element0)))));
    private static final CdpField<java.util.List<String>> RULE_IDS = CdpField.of("ruleIds", value -> CdpObject.requireList(value, element0 -> (String) element0));
    private static final CdpField<DeviceBoundSessionUrlRule.RuleTypeValues> RULE_TYPE = CdpField.of("ruleType", value -> DeviceBoundSessionUrlRule.RuleTypeValues.of((String) value));
    private static final CdpField<Network.CookieSameSite> SAME_SITE = CdpField.of("sameSite", value -> Network.CookieSameSite.of((String) value));
    private static final CdpField<java.util.List<String>> SAN_LIST = CdpField.of("sanList", value -> CdpObject.requireList(value, element0 -> (String) element0));
    private static final CdpField<String> SCHEMEFUL_SITE = CdpField.of("schemefulSite", value -> (String) value);
    private static final CdpField<Runtime.ScriptId> SCRIPT_ID = CdpField.of("scriptId", value -> new Runtime.ScriptId((String) value));
    private static final CdpField<Network.SecurityDetails> SECURITY_DETAILS = CdpField.of("securityDetails", value -> java.util.Objects.requireNonNull(Network.SecurityDetails.fromMap(CdpObject.requireObjectMap(value))));
    private static final CdpField<Security.SecurityState> SECURITY_STATE = CdpField.of("securityState", value -> Security.SecurityState.of((String) value));
    private static final CdpField<Network.ServiceWorkerResponseSource> SERVICE_WORKER_RESPONSE_SOURCE = CdpField.of("serviceWorkerResponseSource", value -> Network.ServiceWorkerResponseSource.of((String) value));
    private static final CdpField<Network.ServiceWorkerRouterInfo> SERVICE_WORKER_ROUTER_INFO = CdpField.of("serviceWorkerRouterInfo", value -> java.util.Objects.requireNonNull(Network.ServiceWorkerRouterInfo.fromMap(CdpObject.requireObjectMap(value))));
    private static final CdpField<java.util.List<Network.DeviceBoundSession>> SESSIONS = CdpField.of("sessions", value -> CdpObject.requireList(value, element0 -> java.util.Objects.requireNonNull(Network.DeviceBoundSession.fromMap(CdpObject.requireObjectMap(element0)))));
    private static final CdpField<Network.DeviceBoundSessionKey> SESSION_KEY = CdpField.of("sessionKey", value -> java.util.Objects.requireNonNull(Network.DeviceBoundSessionKey.fromMap(CdpObject.requireObjectMap(value))));
    private static final CdpField<java.util.List<Network.SignedExchangeSignature>> SIGNATURES = CdpField.of("signatures", value -> CdpObject.requireList(value, element0 -> java.util.Objects.requireNonNull(Network.SignedExchangeSignature.fromMap(CdpObject.requireObjectMap(element0)))));
    private static final CdpField<java.util.List<Network.SignedCertificateTimestamp>> SIGNED_CERTIFICATE_TIMESTAMP_LIST = CdpField.of("signedCertificateTimestampList", value -> CdpObject.requireList(value, element0 -> java.util.Objects.requireNonNull(Network.SignedCertificateTimestamp.fromMap(CdpObject.requireObjectMap(element0)))));
    private static final CdpField<AuthChallenge.SourceValues> SOURCE = CdpField.of("source", value -> AuthChallenge.SourceValues.of((String) value));
    private static final CdpField<Network.ContentSecurityPolicySource> SOURCE_2 = CdpField.of("source", value -> Network.ContentSecurityPolicySource.of((String) value));
    private static final CdpField<Network.CookieSourceScheme> SOURCE_SCHEME = CdpField.of("sourceScheme", value -> Network.CookieSourceScheme.of((String) value));
    private static final CdpField<Runtime.StackTrace> STACK = CdpField.of("stack", value -> java.util.Objects.requireNonNull(Runtime.StackTrace.fromMap(CdpObject.requireObjectMap(value))));
    private static final CdpField<Network.ReportStatus> STATUS = CdpField.of("status", value -> Network.ReportStatus.of((String) value));
    private static final CdpField<TrustTokenOperationDoneEvent.StatusValues> STATUS_2 = CdpField.of("status", value -> TrustTokenOperationDoneEvent.StatusValues.of((String) value));
    private static final CdpField<Network.SecurityIsolationStatus> STATUS_3 = CdpField.of("status", value -> java.util.Objects.requireNonNull(Network.SecurityIsolationStatus.fromMap(CdpObject.requireObjectMap(value))));
    private static final CdpField<IO.StreamHandle> STREAM = CdpField.of("stream", value -> new IO.StreamHandle((String) value));
    private static final CdpField<Boolean> SUCCESS = CdpField.of("success", value -> (Boolean) value);
    private static final CdpField<java.util.List<String>> TABLE_NAMES = CdpField.of("tableNames", value -> CdpObject.requireList(value, element0 -> (String) element0));
    private static final CdpField<Network.TerminationEventDetails> TERMINATION_EVENT_DETAILS = CdpField.of("terminationEventDetails", value -> java.util.Objects.requireNonNull(Network.TerminationEventDetails.fromMap(CdpObject.requireObjectMap(value))));
    private static final CdpField<Network.TimeSinceEpoch> TIMESTAMP = CdpField.of("timestamp", value -> new Network.TimeSinceEpoch(((Number) value).doubleValue()));
    private static final CdpField<Network.MonotonicTime> TIMESTAMP_2 = CdpField.of("timestamp", value -> new Network.MonotonicTime(((Number) value).doubleValue()));
    private static final CdpField<Network.ResourceTiming> TIMING = CdpField.of("timing", value -> java.util.Objects.requireNonNull(Network.ResourceTiming.fromMap(CdpObject.requireObjectMap(value))));
    private static final CdpField<Network.RequestId> TRANSPORT_ID = CdpField.of("transportId", value -> new Network.RequestId((String) value));
    private static final CdpField<Network.TrustTokenParams> TRUST_TOKEN_PARAMS = CdpField.of("trustTokenParams", value -> java.util.Objects.requireNonNull(Network.TrustTokenParams.fromMap(CdpObject.requireObjectMap(value))));
    private static final CdpField<Network.ResourceType> TYPE = CdpField.of("type", value -> Network.ResourceType.of((String) value));
    private static final CdpField<Initiator.TypeValues> TYPE_2 = CdpField.of("type", value -> Initiator.TypeValues.of((String) value));
    private static final CdpField<Network.TrustTokenOperationType> TYPE_3 = CdpField.of("type", value -> Network.TrustTokenOperationType.of((String) value));
    private static final CdpField<java.util.List<String>> URLS = CdpField.of("urls", value -> CdpObject.requireList(value, element0 -> (String) element0));
    private static final CdpField<java.util.List<Network.BlockPattern>> URL_PATTERNS = CdpField.of("urlPatterns", value -> CdpObject.requireList(value, element0 -> java.util.Objects.requireNonNull(Network.BlockPattern.fromMap(CdpObject.requireObjectMap(element0)))));
    private static final CdpField<java.util.List<Network.DeviceBoundSessionUrlRule>> URL_RULES = CdpField.of("urlRules", value -> CdpObject.requireList(value, element0 -> java.util.Objects.requireNonNull(Network.DeviceBoundSessionUrlRule.fromMap(CdpObject.requireObjectMap(element0)))));
    private static final CdpField<DeviceBoundSessionWithUsage.UsageValues> USAGE = CdpField.of("usage", value -> DeviceBoundSessionWithUsage.UsageValues.of((String) value));
    private static final CdpField<Emulation.UserAgentMetadata> USER_AGENT_METADATA = CdpField.of("userAgentMetadata", value -> java.util.Objects.requireNonNull(Emulation.UserAgentMetadata.fromMap(CdpObject.requireObjectMap(value))));
    private static final CdpField<Network.TimeSinceEpoch> VALID_FROM = CdpField.of("validFrom", value -> new Network.TimeSinceEpoch(((Number) value).doubleValue()));
    private static final CdpField<Network.TimeSinceEpoch> VALID_TO = CdpField.of("validTo", value -> new Network.TimeSinceEpoch(((Number) value).doubleValue()));
    private static final CdpField<Network.CrossOriginOpenerPolicyValue> VALUE = CdpField.of("value", value -> Network.CrossOriginOpenerPolicyValue.of((String) value));
    private static final CdpField<Network.CrossOriginEmbedderPolicyValue> VALUE_2 = CdpField.of("value", value -> Network.CrossOriginEmbedderPolicyValue.of((String) value));
    private static final CdpField<Network.TimeSinceEpoch> WALL_TIME = CdpField.of("wallTime", value -> new Network.TimeSinceEpoch(((Number) value).doubleValue()));
    /**
     * Resource type as it was perceived by the rendering engine.
     */
    public enum ResourceType implements CdpValue<String> {
        DOCUMENT("Document"),
        STYLESHEET("Stylesheet"),
        IMAGE("Image"),
        MEDIA("Media"),
        FONT("Font"),
        SCRIPT("Script"),
        TEXTTRACK("TextTrack"),
        XHR("XHR"),
        FETCH("Fetch"),
        PREFETCH("Prefetch"),
        EVENTSOURCE("EventSource"),
        WEBSOCKET("WebSocket"),
        MANIFEST("Manifest"),
        SIGNEDEXCHANGE("SignedExchange"),
        PING("Ping"),
        CSPVIOLATIONREPORT("CSPViolationReport"),
        PREFLIGHT("Preflight"),
        FEDCM("FedCM"),
        OTHER("Other");
        public final String value;
        ResourceType(String value) { this.value = value; }
        @Override public String value() { return value; }
        public static ResourceType of(String value) {
            for (ResourceType constant : values()) {
                if (constant.value.equals(value)) return constant;
            }
            throw new IllegalArgumentException("Unknown ResourceType value: " + value);
        }
    }
    /**
     * Unique loader identifier.
     */
    public static final class LoaderId implements CdpValue<String> {
        public final String value;
        public LoaderId(String value) { this.value = java.util.Objects.requireNonNull(value); }
        @Override public String value() { return value; }
        @Override public boolean equals(@org.jspecify.annotations.Nullable Object other) {
            if (this == other) return true;
            if (!(other instanceof LoaderId)) return false;
            return value.equals(((LoaderId) other).value);
        }
        @Override public int hashCode() { return value.hashCode(); }
        @Override public String toString() { return "LoaderId(" + value + ")"; }
    }
    /**
     * Unique network request identifier. Note that this does not identify individual HTTP requests that are part of a network request.
     */
    public static final class RequestId implements CdpValue<String> {
        public final String value;
        public RequestId(String value) { this.value = java.util.Objects.requireNonNull(value); }
        @Override public String value() { return value; }
        @Override public boolean equals(@org.jspecify.annotations.Nullable Object other) {
            if (this == other) return true;
            if (!(other instanceof RequestId)) return false;
            return value.equals(((RequestId) other).value);
        }
        @Override public int hashCode() { return value.hashCode(); }
        @Override public String toString() { return "RequestId(" + value + ")"; }
    }
    /**
     * Unique intercepted request identifier.
     */
    public static final class InterceptionId implements CdpValue<String> {
        public final String value;
        public InterceptionId(String value) { this.value = java.util.Objects.requireNonNull(value); }
        @Override public String value() { return value; }
        @Override public boolean equals(@org.jspecify.annotations.Nullable Object other) {
            if (this == other) return true;
            if (!(other instanceof InterceptionId)) return false;
            return value.equals(((InterceptionId) other).value);
        }
        @Override public int hashCode() { return value.hashCode(); }
        @Override public String toString() { return "InterceptionId(" + value + ")"; }
    }
    /**
     * Network level fetch failure reason.
     */
    public enum ErrorReason implements CdpValue<String> {
        FAILED("Failed"),
        ABORTED("Aborted"),
        TIMEDOUT("TimedOut"),
        ACCESSDENIED("AccessDenied"),
        CONNECTIONCLOSED("ConnectionClosed"),
        CONNECTIONRESET("ConnectionReset"),
        CONNECTIONREFUSED("ConnectionRefused"),
        CONNECTIONABORTED("ConnectionAborted"),
        CONNECTIONFAILED("ConnectionFailed"),
        NAMENOTRESOLVED("NameNotResolved"),
        INTERNETDISCONNECTED("InternetDisconnected"),
        ADDRESSUNREACHABLE("AddressUnreachable"),
        BLOCKEDBYCLIENT("BlockedByClient"),
        BLOCKEDBYRESPONSE("BlockedByResponse");
        public final String value;
        ErrorReason(String value) { this.value = value; }
        @Override public String value() { return value; }
        public static ErrorReason of(String value) {
            for (ErrorReason constant : values()) {
                if (constant.value.equals(value)) return constant;
            }
            throw new IllegalArgumentException("Unknown ErrorReason value: " + value);
        }
    }
    /**
     * UTC time in seconds, counted from January 1, 1970.
     */
    public static final class TimeSinceEpoch implements CdpValue<Double> {
        public final double value;
        public TimeSinceEpoch(double value) { this.value = value; }
        @Override public Double value() { return value; }
        @Override public boolean equals(@org.jspecify.annotations.Nullable Object other) {
            if (this == other) return true;
            if (!(other instanceof TimeSinceEpoch)) return false;
            return Double.compare(value, ((TimeSinceEpoch) other).value) == 0;
        }
        @Override public int hashCode() { return Double.hashCode(value); }
        @Override public String toString() { return "TimeSinceEpoch(" + value + ")"; }
    }
    /**
     * Monotonically increasing time in seconds since an arbitrary point in the past.
     */
    public static final class MonotonicTime implements CdpValue<Double> {
        public final double value;
        public MonotonicTime(double value) { this.value = value; }
        @Override public Double value() { return value; }
        @Override public boolean equals(@org.jspecify.annotations.Nullable Object other) {
            if (this == other) return true;
            if (!(other instanceof MonotonicTime)) return false;
            return Double.compare(value, ((MonotonicTime) other).value) == 0;
        }
        @Override public int hashCode() { return Double.hashCode(value); }
        @Override public String toString() { return "MonotonicTime(" + value + ")"; }
    }
    /**
     * The underlying connection technology that the browser is supposedly using.
     */
    public enum ConnectionType implements CdpValue<String> {
        NONE("none"),
        CELLULAR2G("cellular2g"),
        CELLULAR3G("cellular3g"),
        CELLULAR4G("cellular4g"),
        BLUETOOTH("bluetooth"),
        ETHERNET("ethernet"),
        WIFI("wifi"),
        WIMAX("wimax"),
        OTHER("other");
        public final String value;
        ConnectionType(String value) { this.value = value; }
        @Override public String value() { return value; }
        public static ConnectionType of(String value) {
            for (ConnectionType constant : values()) {
                if (constant.value.equals(value)) return constant;
            }
            throw new IllegalArgumentException("Unknown ConnectionType value: " + value);
        }
    }
    /**
     * Represents the cookie&#x27;s &#x27;SameSite&#x27; status: https://tools.ietf.org/html/draft-west-first-party-cookies
     */
    public enum CookieSameSite implements CdpValue<String> {
        STRICT("Strict"),
        LAX("Lax"),
        NONE("None");
        public final String value;
        CookieSameSite(String value) { this.value = value; }
        @Override public String value() { return value; }
        public static CookieSameSite of(String value) {
            for (CookieSameSite constant : values()) {
                if (constant.value.equals(value)) return constant;
            }
            throw new IllegalArgumentException("Unknown CookieSameSite value: " + value);
        }
    }
    /**
     * Represents the cookie&#x27;s &#x27;Priority&#x27; status: https://tools.ietf.org/html/draft-west-cookie-priority-00
     * <p><b>Experimental:</b> this part of CDP may change without notice.
     */
    public enum CookiePriority implements CdpValue<String> {
        LOW("Low"),
        MEDIUM("Medium"),
        HIGH("High");
        public final String value;
        CookiePriority(String value) { this.value = value; }
        @Override public String value() { return value; }
        public static CookiePriority of(String value) {
            for (CookiePriority constant : values()) {
                if (constant.value.equals(value)) return constant;
            }
            throw new IllegalArgumentException("Unknown CookiePriority value: " + value);
        }
    }
    /**
     * Represents the source scheme of the origin that originally set the cookie. A value of &quot;Unset&quot; allows protocol clients to emulate legacy cookie scope for the scheme. This is a temporary ability and it will be removed in the future.
     * <p><b>Experimental:</b> this part of CDP may change without notice.
     */
    public enum CookieSourceScheme implements CdpValue<String> {
        UNSET("Unset"),
        NONSECURE("NonSecure"),
        SECURE("Secure");
        public final String value;
        CookieSourceScheme(String value) { this.value = value; }
        @Override public String value() { return value; }
        public static CookieSourceScheme of(String value) {
            for (CookieSourceScheme constant : values()) {
                if (constant.value.equals(value)) return constant;
            }
            throw new IllegalArgumentException("Unknown CookieSourceScheme value: " + value);
        }
    }
    /**
     * Timing information for the request.
     */
    public static final class ResourceTiming extends CdpObject {
        public ResourceTiming() {}
        private ResourceTiming(Map<String, Object> values) { super(values); }
        public static ResourceTiming fromMap(Map<String, Object> values) {
            return new ResourceTiming(values);
        }
        /**
         * Timing&#x27;s requestTime is a baseline in seconds, while the other numbers are ticks in milliseconds relatively to this requestTime.
         * @return the protocol field value
         */
        public double requestTime() {
            return requireDouble("requestTime");
        }
        /**
         * Started resolving proxy.
         * @return the protocol field value
         */
        public double proxyStart() {
            return requireDouble("proxyStart");
        }
        /**
         * Finished resolving proxy.
         * @return the protocol field value
         */
        public double proxyEnd() {
            return requireDouble("proxyEnd");
        }
        /**
         * Started DNS address resolve.
         * @return the protocol field value
         */
        public double dnsStart() {
            return requireDouble("dnsStart");
        }
        /**
         * Finished DNS address resolve.
         * @return the protocol field value
         */
        public double dnsEnd() {
            return requireDouble("dnsEnd");
        }
        /**
         * Started connecting to the remote host.
         * @return the protocol field value
         */
        public double connectStart() {
            return requireDouble("connectStart");
        }
        /**
         * Connected to the remote host.
         * @return the protocol field value
         */
        public double connectEnd() {
            return requireDouble("connectEnd");
        }
        /**
         * Started SSL handshake.
         * @return the protocol field value
         */
        public double sslStart() {
            return requireDouble("sslStart");
        }
        /**
         * Finished SSL handshake.
         * @return the protocol field value
         */
        public double sslEnd() {
            return requireDouble("sslEnd");
        }
        /**
         * Started running ServiceWorker.
         * <p><b>Experimental:</b> this part of CDP may change without notice.
         * @return the protocol field value
         */
        public double workerStart() {
            return requireDouble("workerStart");
        }
        /**
         * Finished Starting ServiceWorker.
         * <p><b>Experimental:</b> this part of CDP may change without notice.
         * @return the protocol field value
         */
        public double workerReady() {
            return requireDouble("workerReady");
        }
        /**
         * Started fetch event.
         * <p><b>Experimental:</b> this part of CDP may change without notice.
         * @return the protocol field value
         */
        public double workerFetchStart() {
            return requireDouble("workerFetchStart");
        }
        /**
         * Settled fetch event respondWith promise.
         * <p><b>Experimental:</b> this part of CDP may change without notice.
         * @return the protocol field value
         */
        public double workerRespondWithSettled() {
            return requireDouble("workerRespondWithSettled");
        }
        /**
         * Started ServiceWorker static routing source evaluation.
         * <p><b>Experimental:</b> this part of CDP may change without notice.
         * @return the protocol field value, empty when absent
         */
        public OptionalDouble workerRouterEvaluationStart() {
            return optionalDouble("workerRouterEvaluationStart");
        }
        /**
         * Started cache lookup when the source was evaluated to {@code cache}.
         * <p><b>Experimental:</b> this part of CDP may change without notice.
         * @return the protocol field value, empty when absent
         */
        public OptionalDouble workerCacheLookupStart() {
            return optionalDouble("workerCacheLookupStart");
        }
        /**
         * Started sending request.
         * @return the protocol field value
         */
        public double sendStart() {
            return requireDouble("sendStart");
        }
        /**
         * Finished sending request.
         * @return the protocol field value
         */
        public double sendEnd() {
            return requireDouble("sendEnd");
        }
        /**
         * Time the server started pushing request.
         * <p><b>Experimental:</b> this part of CDP may change without notice.
         * @return the protocol field value
         */
        public double pushStart() {
            return requireDouble("pushStart");
        }
        /**
         * Time the server finished pushing request.
         * <p><b>Experimental:</b> this part of CDP may change without notice.
         * @return the protocol field value
         */
        public double pushEnd() {
            return requireDouble("pushEnd");
        }
        /**
         * Started receiving response headers.
         * <p><b>Experimental:</b> this part of CDP may change without notice.
         * @return the protocol field value
         */
        public double receiveHeadersStart() {
            return requireDouble("receiveHeadersStart");
        }
        /**
         * Finished receiving response headers.
         * @return the protocol field value
         */
        public double receiveHeadersEnd() {
            return requireDouble("receiveHeadersEnd");
        }
        /**
         * Timing&#x27;s requestTime is a baseline in seconds, while the other numbers are ticks in milliseconds relatively to this requestTime.
         * @param requestTime field value
         * @return this model
         */
        public ResourceTiming requestTime(double requestTime) {
            set("requestTime", requestTime);
            return this;
        }
        /**
         * Started resolving proxy.
         * @param proxyStart field value
         * @return this model
         */
        public ResourceTiming proxyStart(double proxyStart) {
            set("proxyStart", proxyStart);
            return this;
        }
        /**
         * Finished resolving proxy.
         * @param proxyEnd field value
         * @return this model
         */
        public ResourceTiming proxyEnd(double proxyEnd) {
            set("proxyEnd", proxyEnd);
            return this;
        }
        /**
         * Started DNS address resolve.
         * @param dnsStart field value
         * @return this model
         */
        public ResourceTiming dnsStart(double dnsStart) {
            set("dnsStart", dnsStart);
            return this;
        }
        /**
         * Finished DNS address resolve.
         * @param dnsEnd field value
         * @return this model
         */
        public ResourceTiming dnsEnd(double dnsEnd) {
            set("dnsEnd", dnsEnd);
            return this;
        }
        /**
         * Started connecting to the remote host.
         * @param connectStart field value
         * @return this model
         */
        public ResourceTiming connectStart(double connectStart) {
            set("connectStart", connectStart);
            return this;
        }
        /**
         * Connected to the remote host.
         * @param connectEnd field value
         * @return this model
         */
        public ResourceTiming connectEnd(double connectEnd) {
            set("connectEnd", connectEnd);
            return this;
        }
        /**
         * Started SSL handshake.
         * @param sslStart field value
         * @return this model
         */
        public ResourceTiming sslStart(double sslStart) {
            set("sslStart", sslStart);
            return this;
        }
        /**
         * Finished SSL handshake.
         * @param sslEnd field value
         * @return this model
         */
        public ResourceTiming sslEnd(double sslEnd) {
            set("sslEnd", sslEnd);
            return this;
        }
        /**
         * Started running ServiceWorker.
         * <p><b>Experimental:</b> this part of CDP may change without notice.
         * @param workerStart field value
         * @return this model
         */
        public ResourceTiming workerStart(double workerStart) {
            set("workerStart", workerStart);
            return this;
        }
        /**
         * Finished Starting ServiceWorker.
         * <p><b>Experimental:</b> this part of CDP may change without notice.
         * @param workerReady field value
         * @return this model
         */
        public ResourceTiming workerReady(double workerReady) {
            set("workerReady", workerReady);
            return this;
        }
        /**
         * Started fetch event.
         * <p><b>Experimental:</b> this part of CDP may change without notice.
         * @param workerFetchStart field value
         * @return this model
         */
        public ResourceTiming workerFetchStart(double workerFetchStart) {
            set("workerFetchStart", workerFetchStart);
            return this;
        }
        /**
         * Settled fetch event respondWith promise.
         * <p><b>Experimental:</b> this part of CDP may change without notice.
         * @param workerRespondWithSettled field value
         * @return this model
         */
        public ResourceTiming workerRespondWithSettled(double workerRespondWithSettled) {
            set("workerRespondWithSettled", workerRespondWithSettled);
            return this;
        }
        /**
         * Started ServiceWorker static routing source evaluation.
         * <p><b>Experimental:</b> this part of CDP may change without notice.
         * @param workerRouterEvaluationStart field value
         * @return this model
         */
        public ResourceTiming workerRouterEvaluationStart(double workerRouterEvaluationStart) {
            set("workerRouterEvaluationStart", workerRouterEvaluationStart);
            return this;
        }
        /**
         * Started cache lookup when the source was evaluated to {@code cache}.
         * <p><b>Experimental:</b> this part of CDP may change without notice.
         * @param workerCacheLookupStart field value
         * @return this model
         */
        public ResourceTiming workerCacheLookupStart(double workerCacheLookupStart) {
            set("workerCacheLookupStart", workerCacheLookupStart);
            return this;
        }
        /**
         * Started sending request.
         * @param sendStart field value
         * @return this model
         */
        public ResourceTiming sendStart(double sendStart) {
            set("sendStart", sendStart);
            return this;
        }
        /**
         * Finished sending request.
         * @param sendEnd field value
         * @return this model
         */
        public ResourceTiming sendEnd(double sendEnd) {
            set("sendEnd", sendEnd);
            return this;
        }
        /**
         * Time the server started pushing request.
         * <p><b>Experimental:</b> this part of CDP may change without notice.
         * @param pushStart field value
         * @return this model
         */
        public ResourceTiming pushStart(double pushStart) {
            set("pushStart", pushStart);
            return this;
        }
        /**
         * Time the server finished pushing request.
         * <p><b>Experimental:</b> this part of CDP may change without notice.
         * @param pushEnd field value
         * @return this model
         */
        public ResourceTiming pushEnd(double pushEnd) {
            set("pushEnd", pushEnd);
            return this;
        }
        /**
         * Started receiving response headers.
         * <p><b>Experimental:</b> this part of CDP may change without notice.
         * @param receiveHeadersStart field value
         * @return this model
         */
        public ResourceTiming receiveHeadersStart(double receiveHeadersStart) {
            set("receiveHeadersStart", receiveHeadersStart);
            return this;
        }
        /**
         * Finished receiving response headers.
         * @param receiveHeadersEnd field value
         * @return this model
         */
        public ResourceTiming receiveHeadersEnd(double receiveHeadersEnd) {
            set("receiveHeadersEnd", receiveHeadersEnd);
            return this;
        }
    }
    /**
     * Loading priority of a resource request.
     */
    public enum ResourcePriority implements CdpValue<String> {
        VERYLOW("VeryLow"),
        LOW("Low"),
        MEDIUM("Medium"),
        HIGH("High"),
        VERYHIGH("VeryHigh");
        public final String value;
        ResourcePriority(String value) { this.value = value; }
        @Override public String value() { return value; }
        public static ResourcePriority of(String value) {
            for (ResourcePriority constant : values()) {
                if (constant.value.equals(value)) return constant;
            }
            throw new IllegalArgumentException("Unknown ResourcePriority value: " + value);
        }
    }
    /**
     * The render-blocking behavior of a resource request.
     * <p><b>Experimental:</b> this part of CDP may change without notice.
     */
    public enum RenderBlockingBehavior implements CdpValue<String> {
        BLOCKING("Blocking"),
        INBODYPARSERBLOCKING("InBodyParserBlocking"),
        NONBLOCKING("NonBlocking"),
        NONBLOCKINGDYNAMIC("NonBlockingDynamic"),
        POTENTIALLYBLOCKING("PotentiallyBlocking");
        public final String value;
        RenderBlockingBehavior(String value) { this.value = value; }
        @Override public String value() { return value; }
        public static RenderBlockingBehavior of(String value) {
            for (RenderBlockingBehavior constant : values()) {
                if (constant.value.equals(value)) return constant;
            }
            throw new IllegalArgumentException("Unknown RenderBlockingBehavior value: " + value);
        }
    }
    /**
     * Post data entry for HTTP request
     */
    public static final class PostDataEntry extends CdpObject {
        public PostDataEntry() {}
        private PostDataEntry(Map<String, Object> values) { super(values); }
        public static PostDataEntry fromMap(Map<String, Object> values) {
            return new PostDataEntry(values);
        }
        /**
         * Returns the bytes field.
         * @return the protocol field value, empty when absent
         */
        public Optional<String> bytes() {
            return optionalString("bytes");
        }
        /**
         * Sets the bytes field.
         * @param bytes field value
         * @return this model
         */
        public PostDataEntry bytes(String bytes) {
            set("bytes", bytes);
            return this;
        }
    }
    /**
     * HTTP request data.
     */
    public static final class Request extends CdpObject {
        public Request() {}
        private Request(Map<String, Object> values) { super(values); }
        public static Request fromMap(Map<String, Object> values) {
            return new Request(values);
        }
        /**
         * The referrer policy of the request, as defined in https://www.w3.org/TR/referrer-policy/
         */
        public enum ReferrerPolicyValues implements CdpValue<String> {
            UNSAFE_URL("unsafe-url"),
            NO_REFERRER_WHEN_DOWNGRADE("no-referrer-when-downgrade"),
            NO_REFERRER("no-referrer"),
            ORIGIN("origin"),
            ORIGIN_WHEN_CROSS_ORIGIN("origin-when-cross-origin"),
            SAME_ORIGIN("same-origin"),
            STRICT_ORIGIN("strict-origin"),
            STRICT_ORIGIN_WHEN_CROSS_ORIGIN("strict-origin-when-cross-origin");
            public final String value;
            ReferrerPolicyValues(String value) { this.value = value; }
            @Override public String value() { return value; }
            public static ReferrerPolicyValues of(String value) {
                for (ReferrerPolicyValues constant : values()) {
                    if (constant.value.equals(value)) return constant;
                }
                throw new IllegalArgumentException("Unknown ReferrerPolicyValues value: " + value);
            }
        }
        /**
         * Request URL (without fragment).
         * @return the protocol field value
         */
        public String url() {
            return requireString("url");
        }
        /**
         * Fragment of the requested URL starting with hash, if present.
         * @return the protocol field value, empty when absent
         */
        public Optional<String> urlFragment() {
            return optionalString("urlFragment");
        }
        /**
         * HTTP request method.
         * @return the protocol field value
         */
        public String method() {
            return requireString("method");
        }
        /**
         * HTTP request headers.
         * @return the protocol field value
         */
        public java.util.Map<String, Object> headers() {
            return requireObject("headers");
        }
        /**
         * HTTP POST request data. Use postDataEntries instead.
         * @return the protocol field value, empty when absent
         * @deprecated Deprecated by the Chromium DevTools Protocol.
         */
        @Deprecated
        public Optional<String> postData() {
            return optionalString("postData");
        }
        /**
         * True when the request has POST data. Note that postData might still be omitted when this flag is true when the data is too long.
         * @return the protocol field value, empty when absent
         */
        public Optional<Boolean> hasPostData() {
            return optionalBoolean("hasPostData");
        }
        /**
         * Request body elements (post data broken into individual entries).
         * <p><b>Experimental:</b> this part of CDP may change without notice.
         * @return the protocol field value, empty when absent
         */
        public Optional<java.util.List<Network.PostDataEntry>> postDataEntries() {
            return optional(POST_DATA_ENTRIES);
        }
        /**
         * The mixed content type of the request.
         * @return the protocol field value, empty when absent
         */
        public Optional<Security.MixedContentType> mixedContentType() {
            return optional(MIXED_CONTENT_TYPE);
        }
        /**
         * Priority of the resource request at the time request is sent.
         * @return the protocol field value
         */
        public Network.ResourcePriority initialPriority() {
            return require(INITIAL_PRIORITY);
        }
        /**
         * The referrer policy of the request, as defined in https://www.w3.org/TR/referrer-policy/
         * @return the protocol field value
         */
        public Request.ReferrerPolicyValues referrerPolicy() {
            return require(REFERRER_POLICY);
        }
        /**
         * Whether is loaded via link preload.
         * @return the protocol field value, empty when absent
         */
        public Optional<Boolean> isLinkPreload() {
            return optionalBoolean("isLinkPreload");
        }
        /**
         * Set for requests when the TrustToken API is used. Contains the parameters passed by the developer (e.g. via &quot;fetch&quot;) as understood by the backend.
         * <p><b>Experimental:</b> this part of CDP may change without notice.
         * @return the protocol field value, empty when absent
         */
        public Optional<Network.TrustTokenParams> trustTokenParams() {
            return optional(TRUST_TOKEN_PARAMS);
        }
        /**
         * True if this resource request is considered to be the &#x27;same site&#x27; as the request corresponding to the main frame.
         * <p><b>Experimental:</b> this part of CDP may change without notice.
         * @return the protocol field value, empty when absent
         */
        public Optional<Boolean> isSameSite() {
            return optionalBoolean("isSameSite");
        }
        /**
         * True when the resource request is ad-related.
         * <p><b>Experimental:</b> this part of CDP may change without notice.
         * @return the protocol field value, empty when absent
         */
        public Optional<Boolean> isAdRelated() {
            return optionalBoolean("isAdRelated");
        }
        /**
         * Request URL (without fragment).
         * @param url field value
         * @return this model
         */
        public Request url(String url) {
            set("url", url);
            return this;
        }
        /**
         * Fragment of the requested URL starting with hash, if present.
         * @param urlFragment field value
         * @return this model
         */
        public Request urlFragment(String urlFragment) {
            set("urlFragment", urlFragment);
            return this;
        }
        /**
         * HTTP request method.
         * @param method field value
         * @return this model
         */
        public Request method(String method) {
            set("method", method);
            return this;
        }
        /**
         * HTTP request headers.
         * @param headers field value
         * @return this model
         */
        public Request headers(java.util.Map<String, Object> headers) {
            set("headers", headers);
            return this;
        }
        /**
         * HTTP POST request data. Use postDataEntries instead.
         * @param postData field value
         * @return this model
         * @deprecated Deprecated by the Chromium DevTools Protocol.
         */
        @Deprecated
        public Request postData(String postData) {
            set("postData", postData);
            return this;
        }
        /**
         * True when the request has POST data. Note that postData might still be omitted when this flag is true when the data is too long.
         * @param hasPostData field value
         * @return this model
         */
        public Request hasPostData(boolean hasPostData) {
            set("hasPostData", hasPostData);
            return this;
        }
        /**
         * Request body elements (post data broken into individual entries).
         * <p><b>Experimental:</b> this part of CDP may change without notice.
         * @param postDataEntries field value
         * @return this model
         */
        public Request postDataEntries(java.util.List<Network.PostDataEntry> postDataEntries) {
            set("postDataEntries", postDataEntries);
            return this;
        }
        /**
         * The mixed content type of the request.
         * @param mixedContentType field value
         * @return this model
         */
        public Request mixedContentType(Security.MixedContentType mixedContentType) {
            set("mixedContentType", mixedContentType);
            return this;
        }
        /**
         * Priority of the resource request at the time request is sent.
         * @param initialPriority field value
         * @return this model
         */
        public Request initialPriority(Network.ResourcePriority initialPriority) {
            set("initialPriority", initialPriority);
            return this;
        }
        /**
         * The referrer policy of the request, as defined in https://www.w3.org/TR/referrer-policy/
         * @param referrerPolicy field value
         * @return this model
         */
        public Request referrerPolicy(Request.ReferrerPolicyValues referrerPolicy) {
            set("referrerPolicy", referrerPolicy);
            return this;
        }
        /**
         * Whether is loaded via link preload.
         * @param isLinkPreload field value
         * @return this model
         */
        public Request isLinkPreload(boolean isLinkPreload) {
            set("isLinkPreload", isLinkPreload);
            return this;
        }
        /**
         * Set for requests when the TrustToken API is used. Contains the parameters passed by the developer (e.g. via &quot;fetch&quot;) as understood by the backend.
         * <p><b>Experimental:</b> this part of CDP may change without notice.
         * @param trustTokenParams field value
         * @return this model
         */
        public Request trustTokenParams(Network.TrustTokenParams trustTokenParams) {
            set("trustTokenParams", trustTokenParams);
            return this;
        }
        /**
         * True if this resource request is considered to be the &#x27;same site&#x27; as the request corresponding to the main frame.
         * <p><b>Experimental:</b> this part of CDP may change without notice.
         * @param isSameSite field value
         * @return this model
         */
        public Request isSameSite(boolean isSameSite) {
            set("isSameSite", isSameSite);
            return this;
        }
        /**
         * True when the resource request is ad-related.
         * <p><b>Experimental:</b> this part of CDP may change without notice.
         * @param isAdRelated field value
         * @return this model
         */
        public Request isAdRelated(boolean isAdRelated) {
            set("isAdRelated", isAdRelated);
            return this;
        }
    }
    /**
     * Details of a signed certificate timestamp (SCT).
     */
    public static final class SignedCertificateTimestamp extends CdpObject {
        public SignedCertificateTimestamp() {}
        private SignedCertificateTimestamp(Map<String, Object> values) { super(values); }
        public static SignedCertificateTimestamp fromMap(Map<String, Object> values) {
            return new SignedCertificateTimestamp(values);
        }
        /**
         * Validation status.
         * @return the protocol field value
         */
        public String status() {
            return requireString("status");
        }
        /**
         * Origin.
         * @return the protocol field value
         */
        public String origin() {
            return requireString("origin");
        }
        /**
         * Log name / description.
         * @return the protocol field value
         */
        public String logDescription() {
            return requireString("logDescription");
        }
        /**
         * Log ID.
         * @return the protocol field value
         */
        public String logId() {
            return requireString("logId");
        }
        /**
         * Issuance date. Unlike TimeSinceEpoch, this contains the number of milliseconds since January 1, 1970, UTC, not the number of seconds.
         * @return the protocol field value
         */
        public double timestamp() {
            return requireDouble("timestamp");
        }
        /**
         * Hash algorithm.
         * @return the protocol field value
         */
        public String hashAlgorithm() {
            return requireString("hashAlgorithm");
        }
        /**
         * Signature algorithm.
         * @return the protocol field value
         */
        public String signatureAlgorithm() {
            return requireString("signatureAlgorithm");
        }
        /**
         * Signature data.
         * @return the protocol field value
         */
        public String signatureData() {
            return requireString("signatureData");
        }
        /**
         * Validation status.
         * @param status field value
         * @return this model
         */
        public SignedCertificateTimestamp status(String status) {
            set("status", status);
            return this;
        }
        /**
         * Origin.
         * @param origin field value
         * @return this model
         */
        public SignedCertificateTimestamp origin(String origin) {
            set("origin", origin);
            return this;
        }
        /**
         * Log name / description.
         * @param logDescription field value
         * @return this model
         */
        public SignedCertificateTimestamp logDescription(String logDescription) {
            set("logDescription", logDescription);
            return this;
        }
        /**
         * Log ID.
         * @param logId field value
         * @return this model
         */
        public SignedCertificateTimestamp logId(String logId) {
            set("logId", logId);
            return this;
        }
        /**
         * Issuance date. Unlike TimeSinceEpoch, this contains the number of milliseconds since January 1, 1970, UTC, not the number of seconds.
         * @param timestamp field value
         * @return this model
         */
        public SignedCertificateTimestamp timestamp(double timestamp) {
            set("timestamp", timestamp);
            return this;
        }
        /**
         * Hash algorithm.
         * @param hashAlgorithm field value
         * @return this model
         */
        public SignedCertificateTimestamp hashAlgorithm(String hashAlgorithm) {
            set("hashAlgorithm", hashAlgorithm);
            return this;
        }
        /**
         * Signature algorithm.
         * @param signatureAlgorithm field value
         * @return this model
         */
        public SignedCertificateTimestamp signatureAlgorithm(String signatureAlgorithm) {
            set("signatureAlgorithm", signatureAlgorithm);
            return this;
        }
        /**
         * Signature data.
         * @param signatureData field value
         * @return this model
         */
        public SignedCertificateTimestamp signatureData(String signatureData) {
            set("signatureData", signatureData);
            return this;
        }
    }
    /**
     * Security details about a request.
     */
    public static final class SecurityDetails extends CdpObject {
        public SecurityDetails() {}
        private SecurityDetails(Map<String, Object> values) { super(values); }
        public static SecurityDetails fromMap(Map<String, Object> values) {
            return new SecurityDetails(values);
        }
        /**
         * Protocol name (e.g. &quot;TLS 1.2&quot; or &quot;QUIC&quot;).
         * @return the protocol field value
         */
        public String protocol() {
            return requireString("protocol");
        }
        /**
         * Key Exchange used by the connection, or the empty string if not applicable.
         * @return the protocol field value
         */
        public String keyExchange() {
            return requireString("keyExchange");
        }
        /**
         * (EC)DH group used by the connection, if applicable.
         * @return the protocol field value, empty when absent
         */
        public Optional<String> keyExchangeGroup() {
            return optionalString("keyExchangeGroup");
        }
        /**
         * Cipher name.
         * @return the protocol field value
         */
        public String cipher() {
            return requireString("cipher");
        }
        /**
         * TLS MAC. Note that AEAD ciphers do not have separate MACs.
         * @return the protocol field value, empty when absent
         */
        public Optional<String> mac() {
            return optionalString("mac");
        }
        /**
         * Certificate ID value.
         * @return the protocol field value
         */
        public Security.CertificateId certificateId() {
            return require(CERTIFICATE_ID);
        }
        /**
         * Certificate subject name.
         * @return the protocol field value
         */
        public String subjectName() {
            return requireString("subjectName");
        }
        /**
         * Subject Alternative Name (SAN) DNS names and IP addresses.
         * @return the protocol field value
         */
        public java.util.List<String> sanList() {
            return require(SAN_LIST);
        }
        /**
         * Name of the issuing CA.
         * @return the protocol field value
         */
        public String issuer() {
            return requireString("issuer");
        }
        /**
         * Certificate valid from date.
         * @return the protocol field value
         */
        public Network.TimeSinceEpoch validFrom() {
            return require(VALID_FROM);
        }
        /**
         * Certificate valid to (expiration) date
         * @return the protocol field value
         */
        public Network.TimeSinceEpoch validTo() {
            return require(VALID_TO);
        }
        /**
         * List of signed certificate timestamps (SCTs).
         * @return the protocol field value
         */
        public java.util.List<Network.SignedCertificateTimestamp> signedCertificateTimestampList() {
            return require(SIGNED_CERTIFICATE_TIMESTAMP_LIST);
        }
        /**
         * Whether the request complied with Certificate Transparency policy
         * @return the protocol field value
         */
        public Network.CertificateTransparencyCompliance certificateTransparencyCompliance() {
            return require(CERTIFICATE_TRANSPARENCY_COMPLIANCE);
        }
        /**
         * The signature algorithm used by the server in the TLS server signature, represented as a TLS SignatureScheme code point. Omitted if not applicable or not known.
         * @return the protocol field value, empty when absent
         */
        public OptionalLong serverSignatureAlgorithm() {
            return optionalLong("serverSignatureAlgorithm");
        }
        /**
         * Whether the connection used Encrypted ClientHello
         * @return the protocol field value
         */
        public boolean encryptedClientHello() {
            return requireBoolean("encryptedClientHello");
        }
        /**
         * Protocol name (e.g. &quot;TLS 1.2&quot; or &quot;QUIC&quot;).
         * @param protocol field value
         * @return this model
         */
        public SecurityDetails protocol(String protocol) {
            set("protocol", protocol);
            return this;
        }
        /**
         * Key Exchange used by the connection, or the empty string if not applicable.
         * @param keyExchange field value
         * @return this model
         */
        public SecurityDetails keyExchange(String keyExchange) {
            set("keyExchange", keyExchange);
            return this;
        }
        /**
         * (EC)DH group used by the connection, if applicable.
         * @param keyExchangeGroup field value
         * @return this model
         */
        public SecurityDetails keyExchangeGroup(String keyExchangeGroup) {
            set("keyExchangeGroup", keyExchangeGroup);
            return this;
        }
        /**
         * Cipher name.
         * @param cipher field value
         * @return this model
         */
        public SecurityDetails cipher(String cipher) {
            set("cipher", cipher);
            return this;
        }
        /**
         * TLS MAC. Note that AEAD ciphers do not have separate MACs.
         * @param mac field value
         * @return this model
         */
        public SecurityDetails mac(String mac) {
            set("mac", mac);
            return this;
        }
        /**
         * Certificate ID value.
         * @param certificateId field value
         * @return this model
         */
        public SecurityDetails certificateId(Security.CertificateId certificateId) {
            set("certificateId", certificateId);
            return this;
        }
        /**
         * Certificate subject name.
         * @param subjectName field value
         * @return this model
         */
        public SecurityDetails subjectName(String subjectName) {
            set("subjectName", subjectName);
            return this;
        }
        /**
         * Subject Alternative Name (SAN) DNS names and IP addresses.
         * @param sanList field value
         * @return this model
         */
        public SecurityDetails sanList(java.util.List<String> sanList) {
            set("sanList", sanList);
            return this;
        }
        /**
         * Name of the issuing CA.
         * @param issuer field value
         * @return this model
         */
        public SecurityDetails issuer(String issuer) {
            set("issuer", issuer);
            return this;
        }
        /**
         * Certificate valid from date.
         * @param validFrom field value
         * @return this model
         */
        public SecurityDetails validFrom(Network.TimeSinceEpoch validFrom) {
            set("validFrom", validFrom);
            return this;
        }
        /**
         * Certificate valid to (expiration) date
         * @param validTo field value
         * @return this model
         */
        public SecurityDetails validTo(Network.TimeSinceEpoch validTo) {
            set("validTo", validTo);
            return this;
        }
        /**
         * List of signed certificate timestamps (SCTs).
         * @param signedCertificateTimestampList field value
         * @return this model
         */
        public SecurityDetails signedCertificateTimestampList(java.util.List<Network.SignedCertificateTimestamp> signedCertificateTimestampList) {
            set("signedCertificateTimestampList", signedCertificateTimestampList);
            return this;
        }
        /**
         * Whether the request complied with Certificate Transparency policy
         * @param certificateTransparencyCompliance field value
         * @return this model
         */
        public SecurityDetails certificateTransparencyCompliance(Network.CertificateTransparencyCompliance certificateTransparencyCompliance) {
            set("certificateTransparencyCompliance", certificateTransparencyCompliance);
            return this;
        }
        /**
         * The signature algorithm used by the server in the TLS server signature, represented as a TLS SignatureScheme code point. Omitted if not applicable or not known.
         * @param serverSignatureAlgorithm field value
         * @return this model
         */
        public SecurityDetails serverSignatureAlgorithm(long serverSignatureAlgorithm) {
            set("serverSignatureAlgorithm", serverSignatureAlgorithm);
            return this;
        }
        /**
         * Whether the connection used Encrypted ClientHello
         * @param encryptedClientHello field value
         * @return this model
         */
        public SecurityDetails encryptedClientHello(boolean encryptedClientHello) {
            set("encryptedClientHello", encryptedClientHello);
            return this;
        }
    }
    /**
     * Whether the request complied with Certificate Transparency policy.
     */
    public enum CertificateTransparencyCompliance implements CdpValue<String> {
        UNKNOWN("unknown"),
        NOT_COMPLIANT("not-compliant"),
        COMPLIANT("compliant");
        public final String value;
        CertificateTransparencyCompliance(String value) { this.value = value; }
        @Override public String value() { return value; }
        public static CertificateTransparencyCompliance of(String value) {
            for (CertificateTransparencyCompliance constant : values()) {
                if (constant.value.equals(value)) return constant;
            }
            throw new IllegalArgumentException("Unknown CertificateTransparencyCompliance value: " + value);
        }
    }
    /**
     * The reason why request was blocked.
     */
    public enum BlockedReason implements CdpValue<String> {
        OTHER("other"),
        CSP("csp"),
        MIXED_CONTENT("mixed-content"),
        ORIGIN("origin"),
        INSPECTOR("inspector"),
        INTEGRITY("integrity"),
        SUBRESOURCE_FILTER("subresource-filter"),
        CONTENT_TYPE("content-type"),
        COEP_FRAME_RESOURCE_NEEDS_COEP_HEADER("coep-frame-resource-needs-coep-header"),
        COOP_SANDBOXED_IFRAME_CANNOT_NAVIGATE_TO_COOP_PAGE("coop-sandboxed-iframe-cannot-navigate-to-coop-page"),
        CORP_NOT_SAME_ORIGIN("corp-not-same-origin"),
        CORP_NOT_SAME_ORIGIN_AFTER_DEFAULTED_TO_SAME_ORIGIN_BY_COEP("corp-not-same-origin-after-defaulted-to-same-origin-by-coep"),
        CORP_NOT_SAME_ORIGIN_AFTER_DEFAULTED_TO_SAME_ORIGIN_BY_DIP("corp-not-same-origin-after-defaulted-to-same-origin-by-dip"),
        CORP_NOT_SAME_ORIGIN_AFTER_DEFAULTED_TO_SAME_ORIGIN_BY_COEP_AND_DIP("corp-not-same-origin-after-defaulted-to-same-origin-by-coep-and-dip"),
        CORP_NOT_SAME_SITE("corp-not-same-site"),
        SRI_MESSAGE_SIGNATURE_MISMATCH("sri-message-signature-mismatch");
        public final String value;
        BlockedReason(String value) { this.value = value; }
        @Override public String value() { return value; }
        public static BlockedReason of(String value) {
            for (BlockedReason constant : values()) {
                if (constant.value.equals(value)) return constant;
            }
            throw new IllegalArgumentException("Unknown BlockedReason value: " + value);
        }
    }
    /**
     * The reason why request was blocked.
     */
    public enum CorsError implements CdpValue<String> {
        DISALLOWEDBYMODE("DisallowedByMode"),
        INVALIDRESPONSE("InvalidResponse"),
        WILDCARDORIGINNOTALLOWED("WildcardOriginNotAllowed"),
        MISSINGALLOWORIGINHEADER("MissingAllowOriginHeader"),
        MULTIPLEALLOWORIGINVALUES("MultipleAllowOriginValues"),
        INVALIDALLOWORIGINVALUE("InvalidAllowOriginValue"),
        ALLOWORIGINMISMATCH("AllowOriginMismatch"),
        INVALIDALLOWCREDENTIALS("InvalidAllowCredentials"),
        CORSDISABLEDSCHEME("CorsDisabledScheme"),
        PREFLIGHTINVALIDSTATUS("PreflightInvalidStatus"),
        PREFLIGHTDISALLOWEDREDIRECT("PreflightDisallowedRedirect"),
        PREFLIGHTWILDCARDORIGINNOTALLOWED("PreflightWildcardOriginNotAllowed"),
        PREFLIGHTMISSINGALLOWORIGINHEADER("PreflightMissingAllowOriginHeader"),
        PREFLIGHTMULTIPLEALLOWORIGINVALUES("PreflightMultipleAllowOriginValues"),
        PREFLIGHTINVALIDALLOWORIGINVALUE("PreflightInvalidAllowOriginValue"),
        PREFLIGHTALLOWORIGINMISMATCH("PreflightAllowOriginMismatch"),
        PREFLIGHTINVALIDALLOWCREDENTIALS("PreflightInvalidAllowCredentials"),
        PREFLIGHTMISSINGALLOWEXTERNAL("PreflightMissingAllowExternal"),
        PREFLIGHTINVALIDALLOWEXTERNAL("PreflightInvalidAllowExternal"),
        INVALIDALLOWMETHODSPREFLIGHTRESPONSE("InvalidAllowMethodsPreflightResponse"),
        INVALIDALLOWHEADERSPREFLIGHTRESPONSE("InvalidAllowHeadersPreflightResponse"),
        METHODDISALLOWEDBYPREFLIGHTRESPONSE("MethodDisallowedByPreflightResponse"),
        HEADERDISALLOWEDBYPREFLIGHTRESPONSE("HeaderDisallowedByPreflightResponse"),
        REDIRECTCONTAINSCREDENTIALS("RedirectContainsCredentials"),
        INSECURELOCALNETWORK("InsecureLocalNetwork"),
        INVALIDLOCALNETWORKACCESS("InvalidLocalNetworkAccess"),
        NOCORSREDIRECTMODENOTFOLLOW("NoCorsRedirectModeNotFollow"),
        LOCALNETWORKACCESSPERMISSIONDENIED("LocalNetworkAccessPermissionDenied");
        public final String value;
        CorsError(String value) { this.value = value; }
        @Override public String value() { return value; }
        public static CorsError of(String value) {
            for (CorsError constant : values()) {
                if (constant.value.equals(value)) return constant;
            }
            throw new IllegalArgumentException("Unknown CorsError value: " + value);
        }
    }
    /**
     */
    public static final class CorsErrorStatus extends CdpObject {
        public CorsErrorStatus() {}
        private CorsErrorStatus(Map<String, Object> values) { super(values); }
        public static CorsErrorStatus fromMap(Map<String, Object> values) {
            return new CorsErrorStatus(values);
        }
        /**
         * Returns the corsError field.
         * @return the protocol field value
         */
        public Network.CorsError corsError() {
            return require(CORS_ERROR);
        }
        /**
         * Returns the failedParameter field.
         * @return the protocol field value
         */
        public String failedParameter() {
            return requireString("failedParameter");
        }
        /**
         * Sets the corsError field.
         * @param corsError field value
         * @return this model
         */
        public CorsErrorStatus corsError(Network.CorsError corsError) {
            set("corsError", corsError);
            return this;
        }
        /**
         * Sets the failedParameter field.
         * @param failedParameter field value
         * @return this model
         */
        public CorsErrorStatus failedParameter(String failedParameter) {
            set("failedParameter", failedParameter);
            return this;
        }
    }
    /**
     * Source of serviceworker response.
     */
    public enum ServiceWorkerResponseSource implements CdpValue<String> {
        CACHE_STORAGE("cache-storage"),
        HTTP_CACHE("http-cache"),
        FALLBACK_CODE("fallback-code"),
        NETWORK("network");
        public final String value;
        ServiceWorkerResponseSource(String value) { this.value = value; }
        @Override public String value() { return value; }
        public static ServiceWorkerResponseSource of(String value) {
            for (ServiceWorkerResponseSource constant : values()) {
                if (constant.value.equals(value)) return constant;
            }
            throw new IllegalArgumentException("Unknown ServiceWorkerResponseSource value: " + value);
        }
    }
    /**
     * Determines what type of Trust Token operation is executed and depending on the type, some additional parameters. The values are specified in third_party/blink/renderer/core/fetch/trust_token.idl.
     * <p><b>Experimental:</b> this part of CDP may change without notice.
     */
    public static final class TrustTokenParams extends CdpObject {
        public TrustTokenParams() {}
        private TrustTokenParams(Map<String, Object> values) { super(values); }
        public static TrustTokenParams fromMap(Map<String, Object> values) {
            return new TrustTokenParams(values);
        }
        /**
         * Only set for &quot;token-redemption&quot; operation and determine whether to request a fresh SRR or use a still valid cached SRR.
         */
        public enum RefreshPolicyValues implements CdpValue<String> {
            USECACHED("UseCached"),
            REFRESH("Refresh");
            public final String value;
            RefreshPolicyValues(String value) { this.value = value; }
            @Override public String value() { return value; }
            public static RefreshPolicyValues of(String value) {
                for (RefreshPolicyValues constant : values()) {
                    if (constant.value.equals(value)) return constant;
                }
                throw new IllegalArgumentException("Unknown RefreshPolicyValues value: " + value);
            }
        }
        /**
         * Returns the operation field.
         * @return the protocol field value
         */
        public Network.TrustTokenOperationType operation() {
            return require(OPERATION);
        }
        /**
         * Only set for &quot;token-redemption&quot; operation and determine whether to request a fresh SRR or use a still valid cached SRR.
         * @return the protocol field value
         */
        public TrustTokenParams.RefreshPolicyValues refreshPolicy() {
            return require(REFRESH_POLICY);
        }
        /**
         * Origins of issuers from whom to request tokens or redemption records.
         * @return the protocol field value, empty when absent
         */
        public Optional<java.util.List<String>> issuers() {
            return optional(ISSUERS);
        }
        /**
         * Sets the operation field.
         * @param operation field value
         * @return this model
         */
        public TrustTokenParams operation(Network.TrustTokenOperationType operation) {
            set("operation", operation);
            return this;
        }
        /**
         * Only set for &quot;token-redemption&quot; operation and determine whether to request a fresh SRR or use a still valid cached SRR.
         * @param refreshPolicy field value
         * @return this model
         */
        public TrustTokenParams refreshPolicy(TrustTokenParams.RefreshPolicyValues refreshPolicy) {
            set("refreshPolicy", refreshPolicy);
            return this;
        }
        /**
         * Origins of issuers from whom to request tokens or redemption records.
         * @param issuers field value
         * @return this model
         */
        public TrustTokenParams issuers(java.util.List<String> issuers) {
            set("issuers", issuers);
            return this;
        }
    }
    /**
     * Wire values for TrustTokenOperationType.
     * <p><b>Experimental:</b> this part of CDP may change without notice.
     */
    public enum TrustTokenOperationType implements CdpValue<String> {
        ISSUANCE("Issuance"),
        REDEMPTION("Redemption"),
        SIGNING("Signing");
        public final String value;
        TrustTokenOperationType(String value) { this.value = value; }
        @Override public String value() { return value; }
        public static TrustTokenOperationType of(String value) {
            for (TrustTokenOperationType constant : values()) {
                if (constant.value.equals(value)) return constant;
            }
            throw new IllegalArgumentException("Unknown TrustTokenOperationType value: " + value);
        }
    }
    /**
     * The reason why Chrome uses a specific transport protocol for HTTP semantics.
     * <p><b>Experimental:</b> this part of CDP may change without notice.
     */
    public enum AlternateProtocolUsage implements CdpValue<String> {
        ALTERNATIVEJOBWONWITHOUTRACE("alternativeJobWonWithoutRace"),
        ALTERNATIVEJOBWONRACE("alternativeJobWonRace"),
        MAINJOBWONRACE("mainJobWonRace"),
        MAPPINGMISSING("mappingMissing"),
        BROKEN("broken"),
        DNSALPNH3JOBWONWITHOUTRACE("dnsAlpnH3JobWonWithoutRace"),
        DNSALPNH3JOBWONRACE("dnsAlpnH3JobWonRace"),
        UNSPECIFIEDREASON("unspecifiedReason");
        public final String value;
        AlternateProtocolUsage(String value) { this.value = value; }
        @Override public String value() { return value; }
        public static AlternateProtocolUsage of(String value) {
            for (AlternateProtocolUsage constant : values()) {
                if (constant.value.equals(value)) return constant;
            }
            throw new IllegalArgumentException("Unknown AlternateProtocolUsage value: " + value);
        }
    }
    /**
     * Source of service worker router.
     */
    public enum ServiceWorkerRouterSource implements CdpValue<String> {
        NETWORK("network"),
        CACHE("cache"),
        FETCH_EVENT("fetch-event"),
        RACE_NETWORK_AND_FETCH_HANDLER("race-network-and-fetch-handler"),
        RACE_NETWORK_AND_CACHE("race-network-and-cache");
        public final String value;
        ServiceWorkerRouterSource(String value) { this.value = value; }
        @Override public String value() { return value; }
        public static ServiceWorkerRouterSource of(String value) {
            for (ServiceWorkerRouterSource constant : values()) {
                if (constant.value.equals(value)) return constant;
            }
            throw new IllegalArgumentException("Unknown ServiceWorkerRouterSource value: " + value);
        }
    }
    /**
     * <p><b>Experimental:</b> this part of CDP may change without notice.
     */
    public static final class ServiceWorkerRouterInfo extends CdpObject {
        public ServiceWorkerRouterInfo() {}
        private ServiceWorkerRouterInfo(Map<String, Object> values) { super(values); }
        public static ServiceWorkerRouterInfo fromMap(Map<String, Object> values) {
            return new ServiceWorkerRouterInfo(values);
        }
        /**
         * ID of the rule matched. If there is a matched rule, this field will be set, otherwiser no value will be set.
         * @return the protocol field value, empty when absent
         */
        public OptionalLong ruleIdMatched() {
            return optionalLong("ruleIdMatched");
        }
        /**
         * The router source of the matched rule. If there is a matched rule, this field will be set, otherwise no value will be set.
         * @return the protocol field value, empty when absent
         */
        public Optional<Network.ServiceWorkerRouterSource> matchedSourceType() {
            return optional(MATCHED_SOURCE_TYPE);
        }
        /**
         * The actual router source used.
         * @return the protocol field value, empty when absent
         */
        public Optional<Network.ServiceWorkerRouterSource> actualSourceType() {
            return optional(ACTUAL_SOURCE_TYPE);
        }
        /**
         * ID of the rule matched. If there is a matched rule, this field will be set, otherwiser no value will be set.
         * @param ruleIdMatched field value
         * @return this model
         */
        public ServiceWorkerRouterInfo ruleIdMatched(long ruleIdMatched) {
            set("ruleIdMatched", ruleIdMatched);
            return this;
        }
        /**
         * The router source of the matched rule. If there is a matched rule, this field will be set, otherwise no value will be set.
         * @param matchedSourceType field value
         * @return this model
         */
        public ServiceWorkerRouterInfo matchedSourceType(Network.ServiceWorkerRouterSource matchedSourceType) {
            set("matchedSourceType", matchedSourceType);
            return this;
        }
        /**
         * The actual router source used.
         * @param actualSourceType field value
         * @return this model
         */
        public ServiceWorkerRouterInfo actualSourceType(Network.ServiceWorkerRouterSource actualSourceType) {
            set("actualSourceType", actualSourceType);
            return this;
        }
    }
    /**
     * HTTP response data.
     */
    public static final class Response extends CdpObject {
        public Response() {}
        private Response(Map<String, Object> values) { super(values); }
        public static Response fromMap(Map<String, Object> values) {
            return new Response(values);
        }
        /**
         * Response URL. This URL can be different from CachedResource.url in case of redirect.
         * @return the protocol field value
         */
        public String url() {
            return requireString("url");
        }
        /**
         * HTTP response status code.
         * @return the protocol field value
         */
        public long status() {
            return requireLong("status");
        }
        /**
         * HTTP response status text.
         * @return the protocol field value
         */
        public String statusText() {
            return requireString("statusText");
        }
        /**
         * HTTP response headers.
         * @return the protocol field value
         */
        public java.util.Map<String, Object> headers() {
            return requireObject("headers");
        }
        /**
         * HTTP response headers text. This has been replaced by the headers in Network.responseReceivedExtraInfo.
         * @return the protocol field value, empty when absent
         * @deprecated Deprecated by the Chromium DevTools Protocol.
         */
        @Deprecated
        public Optional<String> headersText() {
            return optionalString("headersText");
        }
        /**
         * Resource mimeType as determined by the browser.
         * @return the protocol field value
         */
        public String mimeType() {
            return requireString("mimeType");
        }
        /**
         * Resource charset as determined by the browser (if applicable).
         * @return the protocol field value
         */
        public String charset() {
            return requireString("charset");
        }
        /**
         * Refined HTTP request headers that were actually transmitted over the network.
         * @return the protocol field value, empty when absent
         */
        public Optional<java.util.Map<String, Object>> requestHeaders() {
            return optionalObject("requestHeaders");
        }
        /**
         * HTTP request headers text. This has been replaced by the headers in Network.requestWillBeSentExtraInfo.
         * @return the protocol field value, empty when absent
         * @deprecated Deprecated by the Chromium DevTools Protocol.
         */
        @Deprecated
        public Optional<String> requestHeadersText() {
            return optionalString("requestHeadersText");
        }
        /**
         * Specifies whether physical connection was actually reused for this request.
         * @return the protocol field value
         */
        public boolean connectionReused() {
            return requireBoolean("connectionReused");
        }
        /**
         * Physical connection id that was actually used for this request.
         * @return the protocol field value
         */
        public double connectionId() {
            return requireDouble("connectionId");
        }
        /**
         * Remote IP address.
         * @return the protocol field value, empty when absent
         */
        public Optional<String> remoteIPAddress() {
            return optionalString("remoteIPAddress");
        }
        /**
         * Remote port.
         * @return the protocol field value, empty when absent
         */
        public OptionalLong remotePort() {
            return optionalLong("remotePort");
        }
        /**
         * Specifies that the request was served from the disk cache.
         * @return the protocol field value, empty when absent
         */
        public Optional<Boolean> fromDiskCache() {
            return optionalBoolean("fromDiskCache");
        }
        /**
         * Specifies that the request was served from the ServiceWorker.
         * @return the protocol field value, empty when absent
         */
        public Optional<Boolean> fromServiceWorker() {
            return optionalBoolean("fromServiceWorker");
        }
        /**
         * Specifies that the request was served from the prefetch cache.
         * @return the protocol field value, empty when absent
         */
        public Optional<Boolean> fromPrefetchCache() {
            return optionalBoolean("fromPrefetchCache");
        }
        /**
         * Specifies that the request was served from the prefetch cache.
         * @return the protocol field value, empty when absent
         */
        public Optional<Boolean> fromEarlyHints() {
            return optionalBoolean("fromEarlyHints");
        }
        /**
         * Information about how ServiceWorker Static Router API was used. If this field is set with {@code matchedSourceType} field, a matching rule is found. If this field is set without {@code matchedSource}, no matching rule is found. Otherwise, the API is not used.
         * <p><b>Experimental:</b> this part of CDP may change without notice.
         * @return the protocol field value, empty when absent
         */
        public Optional<Network.ServiceWorkerRouterInfo> serviceWorkerRouterInfo() {
            return optional(SERVICE_WORKER_ROUTER_INFO);
        }
        /**
         * Total number of bytes received for this request so far.
         * @return the protocol field value
         */
        public double encodedDataLength() {
            return requireDouble("encodedDataLength");
        }
        /**
         * Timing information for the given request.
         * @return the protocol field value, empty when absent
         */
        public Optional<Network.ResourceTiming> timing() {
            return optional(TIMING);
        }
        /**
         * Response source of response from ServiceWorker.
         * @return the protocol field value, empty when absent
         */
        public Optional<Network.ServiceWorkerResponseSource> serviceWorkerResponseSource() {
            return optional(SERVICE_WORKER_RESPONSE_SOURCE);
        }
        /**
         * The time at which the returned response was generated.
         * @return the protocol field value, empty when absent
         */
        public Optional<Network.TimeSinceEpoch> responseTime() {
            return optional(RESPONSE_TIME);
        }
        /**
         * Cache Storage Cache Name.
         * @return the protocol field value, empty when absent
         */
        public Optional<String> cacheStorageCacheName() {
            return optionalString("cacheStorageCacheName");
        }
        /**
         * Protocol used to fetch this request.
         * @return the protocol field value, empty when absent
         */
        public Optional<String> protocol() {
            return optionalString("protocol");
        }
        /**
         * The reason why Chrome uses a specific transport protocol for HTTP semantics.
         * <p><b>Experimental:</b> this part of CDP may change without notice.
         * @return the protocol field value, empty when absent
         */
        public Optional<Network.AlternateProtocolUsage> alternateProtocolUsage() {
            return optional(ALTERNATE_PROTOCOL_USAGE);
        }
        /**
         * Security state of the request resource.
         * @return the protocol field value
         */
        public Security.SecurityState securityState() {
            return require(SECURITY_STATE);
        }
        /**
         * Security details for the request.
         * @return the protocol field value, empty when absent
         */
        public Optional<Network.SecurityDetails> securityDetails() {
            return optional(SECURITY_DETAILS);
        }
        /**
         * Response URL. This URL can be different from CachedResource.url in case of redirect.
         * @param url field value
         * @return this model
         */
        public Response url(String url) {
            set("url", url);
            return this;
        }
        /**
         * HTTP response status code.
         * @param status field value
         * @return this model
         */
        public Response status(long status) {
            set("status", status);
            return this;
        }
        /**
         * HTTP response status text.
         * @param statusText field value
         * @return this model
         */
        public Response statusText(String statusText) {
            set("statusText", statusText);
            return this;
        }
        /**
         * HTTP response headers.
         * @param headers field value
         * @return this model
         */
        public Response headers(java.util.Map<String, Object> headers) {
            set("headers", headers);
            return this;
        }
        /**
         * HTTP response headers text. This has been replaced by the headers in Network.responseReceivedExtraInfo.
         * @param headersText field value
         * @return this model
         * @deprecated Deprecated by the Chromium DevTools Protocol.
         */
        @Deprecated
        public Response headersText(String headersText) {
            set("headersText", headersText);
            return this;
        }
        /**
         * Resource mimeType as determined by the browser.
         * @param mimeType field value
         * @return this model
         */
        public Response mimeType(String mimeType) {
            set("mimeType", mimeType);
            return this;
        }
        /**
         * Resource charset as determined by the browser (if applicable).
         * @param charset field value
         * @return this model
         */
        public Response charset(String charset) {
            set("charset", charset);
            return this;
        }
        /**
         * Refined HTTP request headers that were actually transmitted over the network.
         * @param requestHeaders field value
         * @return this model
         */
        public Response requestHeaders(java.util.Map<String, Object> requestHeaders) {
            set("requestHeaders", requestHeaders);
            return this;
        }
        /**
         * HTTP request headers text. This has been replaced by the headers in Network.requestWillBeSentExtraInfo.
         * @param requestHeadersText field value
         * @return this model
         * @deprecated Deprecated by the Chromium DevTools Protocol.
         */
        @Deprecated
        public Response requestHeadersText(String requestHeadersText) {
            set("requestHeadersText", requestHeadersText);
            return this;
        }
        /**
         * Specifies whether physical connection was actually reused for this request.
         * @param connectionReused field value
         * @return this model
         */
        public Response connectionReused(boolean connectionReused) {
            set("connectionReused", connectionReused);
            return this;
        }
        /**
         * Physical connection id that was actually used for this request.
         * @param connectionId field value
         * @return this model
         */
        public Response connectionId(double connectionId) {
            set("connectionId", connectionId);
            return this;
        }
        /**
         * Remote IP address.
         * @param remoteIPAddress field value
         * @return this model
         */
        public Response remoteIPAddress(String remoteIPAddress) {
            set("remoteIPAddress", remoteIPAddress);
            return this;
        }
        /**
         * Remote port.
         * @param remotePort field value
         * @return this model
         */
        public Response remotePort(long remotePort) {
            set("remotePort", remotePort);
            return this;
        }
        /**
         * Specifies that the request was served from the disk cache.
         * @param fromDiskCache field value
         * @return this model
         */
        public Response fromDiskCache(boolean fromDiskCache) {
            set("fromDiskCache", fromDiskCache);
            return this;
        }
        /**
         * Specifies that the request was served from the ServiceWorker.
         * @param fromServiceWorker field value
         * @return this model
         */
        public Response fromServiceWorker(boolean fromServiceWorker) {
            set("fromServiceWorker", fromServiceWorker);
            return this;
        }
        /**
         * Specifies that the request was served from the prefetch cache.
         * @param fromPrefetchCache field value
         * @return this model
         */
        public Response fromPrefetchCache(boolean fromPrefetchCache) {
            set("fromPrefetchCache", fromPrefetchCache);
            return this;
        }
        /**
         * Specifies that the request was served from the prefetch cache.
         * @param fromEarlyHints field value
         * @return this model
         */
        public Response fromEarlyHints(boolean fromEarlyHints) {
            set("fromEarlyHints", fromEarlyHints);
            return this;
        }
        /**
         * Information about how ServiceWorker Static Router API was used. If this field is set with {@code matchedSourceType} field, a matching rule is found. If this field is set without {@code matchedSource}, no matching rule is found. Otherwise, the API is not used.
         * <p><b>Experimental:</b> this part of CDP may change without notice.
         * @param serviceWorkerRouterInfo field value
         * @return this model
         */
        public Response serviceWorkerRouterInfo(Network.ServiceWorkerRouterInfo serviceWorkerRouterInfo) {
            set("serviceWorkerRouterInfo", serviceWorkerRouterInfo);
            return this;
        }
        /**
         * Total number of bytes received for this request so far.
         * @param encodedDataLength field value
         * @return this model
         */
        public Response encodedDataLength(double encodedDataLength) {
            set("encodedDataLength", encodedDataLength);
            return this;
        }
        /**
         * Timing information for the given request.
         * @param timing field value
         * @return this model
         */
        public Response timing(Network.ResourceTiming timing) {
            set("timing", timing);
            return this;
        }
        /**
         * Response source of response from ServiceWorker.
         * @param serviceWorkerResponseSource field value
         * @return this model
         */
        public Response serviceWorkerResponseSource(Network.ServiceWorkerResponseSource serviceWorkerResponseSource) {
            set("serviceWorkerResponseSource", serviceWorkerResponseSource);
            return this;
        }
        /**
         * The time at which the returned response was generated.
         * @param responseTime field value
         * @return this model
         */
        public Response responseTime(Network.TimeSinceEpoch responseTime) {
            set("responseTime", responseTime);
            return this;
        }
        /**
         * Cache Storage Cache Name.
         * @param cacheStorageCacheName field value
         * @return this model
         */
        public Response cacheStorageCacheName(String cacheStorageCacheName) {
            set("cacheStorageCacheName", cacheStorageCacheName);
            return this;
        }
        /**
         * Protocol used to fetch this request.
         * @param protocol field value
         * @return this model
         */
        public Response protocol(String protocol) {
            set("protocol", protocol);
            return this;
        }
        /**
         * The reason why Chrome uses a specific transport protocol for HTTP semantics.
         * <p><b>Experimental:</b> this part of CDP may change without notice.
         * @param alternateProtocolUsage field value
         * @return this model
         */
        public Response alternateProtocolUsage(Network.AlternateProtocolUsage alternateProtocolUsage) {
            set("alternateProtocolUsage", alternateProtocolUsage);
            return this;
        }
        /**
         * Security state of the request resource.
         * @param securityState field value
         * @return this model
         */
        public Response securityState(Security.SecurityState securityState) {
            set("securityState", securityState);
            return this;
        }
        /**
         * Security details for the request.
         * @param securityDetails field value
         * @return this model
         */
        public Response securityDetails(Network.SecurityDetails securityDetails) {
            set("securityDetails", securityDetails);
            return this;
        }
    }
    /**
     * WebSocket request data.
     */
    public static final class WebSocketRequest extends CdpObject {
        public WebSocketRequest() {}
        private WebSocketRequest(Map<String, Object> values) { super(values); }
        public static WebSocketRequest fromMap(Map<String, Object> values) {
            return new WebSocketRequest(values);
        }
        /**
         * HTTP request headers.
         * @return the protocol field value
         */
        public java.util.Map<String, Object> headers() {
            return requireObject("headers");
        }
        /**
         * HTTP request headers.
         * @param headers field value
         * @return this model
         */
        public WebSocketRequest headers(java.util.Map<String, Object> headers) {
            set("headers", headers);
            return this;
        }
    }
    /**
     * WebSocket response data.
     */
    public static final class WebSocketResponse extends CdpObject {
        public WebSocketResponse() {}
        private WebSocketResponse(Map<String, Object> values) { super(values); }
        public static WebSocketResponse fromMap(Map<String, Object> values) {
            return new WebSocketResponse(values);
        }
        /**
         * HTTP response status code.
         * @return the protocol field value
         */
        public long status() {
            return requireLong("status");
        }
        /**
         * HTTP response status text.
         * @return the protocol field value
         */
        public String statusText() {
            return requireString("statusText");
        }
        /**
         * HTTP response headers.
         * @return the protocol field value
         */
        public java.util.Map<String, Object> headers() {
            return requireObject("headers");
        }
        /**
         * HTTP response headers text.
         * @return the protocol field value, empty when absent
         */
        public Optional<String> headersText() {
            return optionalString("headersText");
        }
        /**
         * HTTP request headers.
         * @return the protocol field value, empty when absent
         */
        public Optional<java.util.Map<String, Object>> requestHeaders() {
            return optionalObject("requestHeaders");
        }
        /**
         * HTTP request headers text.
         * @return the protocol field value, empty when absent
         */
        public Optional<String> requestHeadersText() {
            return optionalString("requestHeadersText");
        }
        /**
         * HTTP response status code.
         * @param status field value
         * @return this model
         */
        public WebSocketResponse status(long status) {
            set("status", status);
            return this;
        }
        /**
         * HTTP response status text.
         * @param statusText field value
         * @return this model
         */
        public WebSocketResponse statusText(String statusText) {
            set("statusText", statusText);
            return this;
        }
        /**
         * HTTP response headers.
         * @param headers field value
         * @return this model
         */
        public WebSocketResponse headers(java.util.Map<String, Object> headers) {
            set("headers", headers);
            return this;
        }
        /**
         * HTTP response headers text.
         * @param headersText field value
         * @return this model
         */
        public WebSocketResponse headersText(String headersText) {
            set("headersText", headersText);
            return this;
        }
        /**
         * HTTP request headers.
         * @param requestHeaders field value
         * @return this model
         */
        public WebSocketResponse requestHeaders(java.util.Map<String, Object> requestHeaders) {
            set("requestHeaders", requestHeaders);
            return this;
        }
        /**
         * HTTP request headers text.
         * @param requestHeadersText field value
         * @return this model
         */
        public WebSocketResponse requestHeadersText(String requestHeadersText) {
            set("requestHeadersText", requestHeadersText);
            return this;
        }
    }
    /**
     * WebSocket message data. This represents an entire WebSocket message, not just a fragmented frame as the name suggests.
     */
    public static final class WebSocketFrame extends CdpObject {
        public WebSocketFrame() {}
        private WebSocketFrame(Map<String, Object> values) { super(values); }
        public static WebSocketFrame fromMap(Map<String, Object> values) {
            return new WebSocketFrame(values);
        }
        /**
         * WebSocket message opcode.
         * @return the protocol field value
         */
        public double opcode() {
            return requireDouble("opcode");
        }
        /**
         * WebSocket message mask.
         * @return the protocol field value
         */
        public boolean mask() {
            return requireBoolean("mask");
        }
        /**
         * WebSocket message payload data. If the opcode is 1, this is a text message and payloadData is a UTF-8 string. If the opcode isn&#x27;t 1, then payloadData is a base64 encoded string representing binary data.
         * @return the protocol field value
         */
        public String payloadData() {
            return requireString("payloadData");
        }
        /**
         * WebSocket message opcode.
         * @param opcode field value
         * @return this model
         */
        public WebSocketFrame opcode(double opcode) {
            set("opcode", opcode);
            return this;
        }
        /**
         * WebSocket message mask.
         * @param mask field value
         * @return this model
         */
        public WebSocketFrame mask(boolean mask) {
            set("mask", mask);
            return this;
        }
        /**
         * WebSocket message payload data. If the opcode is 1, this is a text message and payloadData is a UTF-8 string. If the opcode isn&#x27;t 1, then payloadData is a base64 encoded string representing binary data.
         * @param payloadData field value
         * @return this model
         */
        public WebSocketFrame payloadData(String payloadData) {
            set("payloadData", payloadData);
            return this;
        }
    }
    /**
     * Information about the cached resource.
     */
    public static final class CachedResource extends CdpObject {
        public CachedResource() {}
        private CachedResource(Map<String, Object> values) { super(values); }
        public static CachedResource fromMap(Map<String, Object> values) {
            return new CachedResource(values);
        }
        /**
         * Resource URL. This is the url of the original network request.
         * @return the protocol field value
         */
        public String url() {
            return requireString("url");
        }
        /**
         * Type of this resource.
         * @return the protocol field value
         */
        public Network.ResourceType type() {
            return require(TYPE);
        }
        /**
         * Cached response data.
         * @return the protocol field value, empty when absent
         */
        public Optional<Network.Response> response() {
            return optional(RESPONSE);
        }
        /**
         * Cached response body size.
         * @return the protocol field value
         */
        public double bodySize() {
            return requireDouble("bodySize");
        }
        /**
         * Resource URL. This is the url of the original network request.
         * @param url field value
         * @return this model
         */
        public CachedResource url(String url) {
            set("url", url);
            return this;
        }
        /**
         * Type of this resource.
         * @param type field value
         * @return this model
         */
        public CachedResource type(Network.ResourceType type) {
            set("type", type);
            return this;
        }
        /**
         * Cached response data.
         * @param response field value
         * @return this model
         */
        public CachedResource response(Network.Response response) {
            set("response", response);
            return this;
        }
        /**
         * Cached response body size.
         * @param bodySize field value
         * @return this model
         */
        public CachedResource bodySize(double bodySize) {
            set("bodySize", bodySize);
            return this;
        }
    }
    /**
     * Information about the request initiator.
     */
    public static final class Initiator extends CdpObject {
        public Initiator() {}
        private Initiator(Map<String, Object> values) { super(values); }
        public static Initiator fromMap(Map<String, Object> values) {
            return new Initiator(values);
        }
        /**
         * Type of this initiator.
         */
        public enum TypeValues implements CdpValue<String> {
            PARSER("parser"),
            SCRIPT("script"),
            PRELOAD("preload"),
            SIGNEDEXCHANGE("SignedExchange"),
            PREFLIGHT("preflight"),
            FEDCM("FedCM"),
            OTHER("other");
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
         * Type of this initiator.
         * @return the protocol field value
         */
        public Initiator.TypeValues type() {
            return require(TYPE_2);
        }
        /**
         * Initiator JavaScript stack trace, set for Script only. Requires the Debugger domain to be enabled.
         * @return the protocol field value, empty when absent
         */
        public Optional<Runtime.StackTrace> stack() {
            return optional(STACK);
        }
        /**
         * Initiator URL, set for Parser type or for Script type (when script is importing module) or for SignedExchange type.
         * @return the protocol field value, empty when absent
         */
        public Optional<String> url() {
            return optionalString("url");
        }
        /**
         * Initiator line number, set for Parser type or for Script type (when script is importing module) (0-based).
         * @return the protocol field value, empty when absent
         */
        public OptionalDouble lineNumber() {
            return optionalDouble("lineNumber");
        }
        /**
         * Initiator column number, set for Parser type or for Script type (when script is importing module) (0-based).
         * @return the protocol field value, empty when absent
         */
        public OptionalDouble columnNumber() {
            return optionalDouble("columnNumber");
        }
        /**
         * Set if another request triggered this request (e.g. preflight).
         * @return the protocol field value, empty when absent
         */
        public Optional<Network.RequestId> requestId() {
            return optional(REQUEST_ID);
        }
        /**
         * Type of this initiator.
         * @param type field value
         * @return this model
         */
        public Initiator type(Initiator.TypeValues type) {
            set("type", type);
            return this;
        }
        /**
         * Initiator JavaScript stack trace, set for Script only. Requires the Debugger domain to be enabled.
         * @param stack field value
         * @return this model
         */
        public Initiator stack(Runtime.StackTrace stack) {
            set("stack", stack);
            return this;
        }
        /**
         * Initiator URL, set for Parser type or for Script type (when script is importing module) or for SignedExchange type.
         * @param url field value
         * @return this model
         */
        public Initiator url(String url) {
            set("url", url);
            return this;
        }
        /**
         * Initiator line number, set for Parser type or for Script type (when script is importing module) (0-based).
         * @param lineNumber field value
         * @return this model
         */
        public Initiator lineNumber(double lineNumber) {
            set("lineNumber", lineNumber);
            return this;
        }
        /**
         * Initiator column number, set for Parser type or for Script type (when script is importing module) (0-based).
         * @param columnNumber field value
         * @return this model
         */
        public Initiator columnNumber(double columnNumber) {
            set("columnNumber", columnNumber);
            return this;
        }
        /**
         * Set if another request triggered this request (e.g. preflight).
         * @param requestId field value
         * @return this model
         */
        public Initiator requestId(Network.RequestId requestId) {
            set("requestId", requestId);
            return this;
        }
    }
    /**
     * cookiePartitionKey object The representation of the components of the key that are created by the cookiePartitionKey class contained in net/cookies/cookie_partition_key.h.
     * <p><b>Experimental:</b> this part of CDP may change without notice.
     */
    public static final class CookiePartitionKey extends CdpObject {
        public CookiePartitionKey() {}
        private CookiePartitionKey(Map<String, Object> values) { super(values); }
        public static CookiePartitionKey fromMap(Map<String, Object> values) {
            return new CookiePartitionKey(values);
        }
        /**
         * The site of the top-level URL the browser was visiting at the start of the request to the endpoint that set the cookie.
         * @return the protocol field value
         */
        public String topLevelSite() {
            return requireString("topLevelSite");
        }
        /**
         * Indicates if the cookie has any ancestors that are cross-site to the topLevelSite.
         * @return the protocol field value
         */
        public boolean hasCrossSiteAncestor() {
            return requireBoolean("hasCrossSiteAncestor");
        }
        /**
         * The site of the top-level URL the browser was visiting at the start of the request to the endpoint that set the cookie.
         * @param topLevelSite field value
         * @return this model
         */
        public CookiePartitionKey topLevelSite(String topLevelSite) {
            set("topLevelSite", topLevelSite);
            return this;
        }
        /**
         * Indicates if the cookie has any ancestors that are cross-site to the topLevelSite.
         * @param hasCrossSiteAncestor field value
         * @return this model
         */
        public CookiePartitionKey hasCrossSiteAncestor(boolean hasCrossSiteAncestor) {
            set("hasCrossSiteAncestor", hasCrossSiteAncestor);
            return this;
        }
    }
    /**
     * Cookie object
     */
    public static final class Cookie extends CdpObject {
        public Cookie() {}
        private Cookie(Map<String, Object> values) { super(values); }
        public static Cookie fromMap(Map<String, Object> values) {
            return new Cookie(values);
        }
        /**
         * Cookie name.
         * @return the protocol field value
         */
        public String name() {
            return requireString("name");
        }
        /**
         * Cookie value.
         * @return the protocol field value
         */
        public String value() {
            return requireString("value");
        }
        /**
         * Cookie domain.
         * @return the protocol field value
         */
        public String domain() {
            return requireString("domain");
        }
        /**
         * Cookie path.
         * @return the protocol field value
         */
        public String path() {
            return requireString("path");
        }
        /**
         * Cookie expiration date as the number of seconds since the UNIX epoch. The value is set to -1 if the expiry date is not set. The value can be null for values that cannot be represented in JSON (±Inf).
         * @return the protocol field value
         */
        public double expires() {
            return requireDouble("expires");
        }
        /**
         * Cookie size.
         * @return the protocol field value
         */
        public long size() {
            return requireLong("size");
        }
        /**
         * True if cookie is http-only.
         * @return the protocol field value
         */
        public boolean httpOnly() {
            return requireBoolean("httpOnly");
        }
        /**
         * True if cookie is secure.
         * @return the protocol field value
         */
        public boolean secure() {
            return requireBoolean("secure");
        }
        /**
         * True in case of session cookie.
         * @return the protocol field value
         */
        public boolean session() {
            return requireBoolean("session");
        }
        /**
         * Cookie SameSite type.
         * @return the protocol field value, empty when absent
         */
        public Optional<Network.CookieSameSite> sameSite() {
            return optional(SAME_SITE);
        }
        /**
         * Cookie Priority
         * <p><b>Experimental:</b> this part of CDP may change without notice.
         * @return the protocol field value
         */
        public Network.CookiePriority priority() {
            return require(PRIORITY);
        }
        /**
         * Cookie source scheme type.
         * <p><b>Experimental:</b> this part of CDP may change without notice.
         * @return the protocol field value
         */
        public Network.CookieSourceScheme sourceScheme() {
            return require(SOURCE_SCHEME);
        }
        /**
         * Cookie source port. Valid values are {-1, [1, 65535]}, -1 indicates an unspecified port. An unspecified port value allows protocol clients to emulate legacy cookie scope for the port. This is a temporary ability and it will be removed in the future.
         * <p><b>Experimental:</b> this part of CDP may change without notice.
         * @return the protocol field value
         */
        public long sourcePort() {
            return requireLong("sourcePort");
        }
        /**
         * Cookie partition key.
         * <p><b>Experimental:</b> this part of CDP may change without notice.
         * @return the protocol field value, empty when absent
         */
        public Optional<Network.CookiePartitionKey> partitionKey() {
            return optional(PARTITION_KEY);
        }
        /**
         * True if cookie partition key is opaque.
         * <p><b>Experimental:</b> this part of CDP may change without notice.
         * @return the protocol field value, empty when absent
         */
        public Optional<Boolean> partitionKeyOpaque() {
            return optionalBoolean("partitionKeyOpaque");
        }
        /**
         * Cookie name.
         * @param name field value
         * @return this model
         */
        public Cookie name(String name) {
            set("name", name);
            return this;
        }
        /**
         * Cookie value.
         * @param value field value
         * @return this model
         */
        public Cookie value(String value) {
            set("value", value);
            return this;
        }
        /**
         * Cookie domain.
         * @param domain field value
         * @return this model
         */
        public Cookie domain(String domain) {
            set("domain", domain);
            return this;
        }
        /**
         * Cookie path.
         * @param path field value
         * @return this model
         */
        public Cookie path(String path) {
            set("path", path);
            return this;
        }
        /**
         * Cookie expiration date as the number of seconds since the UNIX epoch. The value is set to -1 if the expiry date is not set. The value can be null for values that cannot be represented in JSON (±Inf).
         * @param expires field value
         * @return this model
         */
        public Cookie expires(double expires) {
            set("expires", expires);
            return this;
        }
        /**
         * Cookie size.
         * @param size field value
         * @return this model
         */
        public Cookie size(long size) {
            set("size", size);
            return this;
        }
        /**
         * True if cookie is http-only.
         * @param httpOnly field value
         * @return this model
         */
        public Cookie httpOnly(boolean httpOnly) {
            set("httpOnly", httpOnly);
            return this;
        }
        /**
         * True if cookie is secure.
         * @param secure field value
         * @return this model
         */
        public Cookie secure(boolean secure) {
            set("secure", secure);
            return this;
        }
        /**
         * True in case of session cookie.
         * @param session field value
         * @return this model
         */
        public Cookie session(boolean session) {
            set("session", session);
            return this;
        }
        /**
         * Cookie SameSite type.
         * @param sameSite field value
         * @return this model
         */
        public Cookie sameSite(Network.CookieSameSite sameSite) {
            set("sameSite", sameSite);
            return this;
        }
        /**
         * Cookie Priority
         * <p><b>Experimental:</b> this part of CDP may change without notice.
         * @param priority field value
         * @return this model
         */
        public Cookie priority(Network.CookiePriority priority) {
            set("priority", priority);
            return this;
        }
        /**
         * Cookie source scheme type.
         * <p><b>Experimental:</b> this part of CDP may change without notice.
         * @param sourceScheme field value
         * @return this model
         */
        public Cookie sourceScheme(Network.CookieSourceScheme sourceScheme) {
            set("sourceScheme", sourceScheme);
            return this;
        }
        /**
         * Cookie source port. Valid values are {-1, [1, 65535]}, -1 indicates an unspecified port. An unspecified port value allows protocol clients to emulate legacy cookie scope for the port. This is a temporary ability and it will be removed in the future.
         * <p><b>Experimental:</b> this part of CDP may change without notice.
         * @param sourcePort field value
         * @return this model
         */
        public Cookie sourcePort(long sourcePort) {
            set("sourcePort", sourcePort);
            return this;
        }
        /**
         * Cookie partition key.
         * <p><b>Experimental:</b> this part of CDP may change without notice.
         * @param partitionKey field value
         * @return this model
         */
        public Cookie partitionKey(Network.CookiePartitionKey partitionKey) {
            set("partitionKey", partitionKey);
            return this;
        }
        /**
         * True if cookie partition key is opaque.
         * <p><b>Experimental:</b> this part of CDP may change without notice.
         * @param partitionKeyOpaque field value
         * @return this model
         */
        public Cookie partitionKeyOpaque(boolean partitionKeyOpaque) {
            set("partitionKeyOpaque", partitionKeyOpaque);
            return this;
        }
    }
    /**
     * Types of reasons why a cookie may not be stored from a response.
     * <p><b>Experimental:</b> this part of CDP may change without notice.
     */
    public enum SetCookieBlockedReason implements CdpValue<String> {
        SECUREONLY("SecureOnly"),
        SAMESITESTRICT("SameSiteStrict"),
        SAMESITELAX("SameSiteLax"),
        SAMESITEUNSPECIFIEDTREATEDASLAX("SameSiteUnspecifiedTreatedAsLax"),
        SAMESITENONEINSECURE("SameSiteNoneInsecure"),
        USERPREFERENCES("UserPreferences"),
        THIRDPARTYPHASEOUT("ThirdPartyPhaseout"),
        THIRDPARTYBLOCKEDINFIRSTPARTYSET("ThirdPartyBlockedInFirstPartySet"),
        SYNTAXERROR("SyntaxError"),
        SCHEMENOTSUPPORTED("SchemeNotSupported"),
        OVERWRITESECURE("OverwriteSecure"),
        INVALIDDOMAIN("InvalidDomain"),
        INVALIDPREFIX("InvalidPrefix"),
        UNKNOWNERROR("UnknownError"),
        SCHEMEFULSAMESITESTRICT("SchemefulSameSiteStrict"),
        SCHEMEFULSAMESITELAX("SchemefulSameSiteLax"),
        SCHEMEFULSAMESITEUNSPECIFIEDTREATEDASLAX("SchemefulSameSiteUnspecifiedTreatedAsLax"),
        NAMEVALUEPAIREXCEEDSMAXSIZE("NameValuePairExceedsMaxSize"),
        DISALLOWEDCHARACTER("DisallowedCharacter"),
        NOCOOKIECONTENT("NoCookieContent");
        public final String value;
        SetCookieBlockedReason(String value) { this.value = value; }
        @Override public String value() { return value; }
        public static SetCookieBlockedReason of(String value) {
            for (SetCookieBlockedReason constant : values()) {
                if (constant.value.equals(value)) return constant;
            }
            throw new IllegalArgumentException("Unknown SetCookieBlockedReason value: " + value);
        }
    }
    /**
     * Types of reasons why a cookie may not be sent with a request.
     * <p><b>Experimental:</b> this part of CDP may change without notice.
     */
    public enum CookieBlockedReason implements CdpValue<String> {
        SECUREONLY("SecureOnly"),
        NOTONPATH("NotOnPath"),
        DOMAINMISMATCH("DomainMismatch"),
        SAMESITESTRICT("SameSiteStrict"),
        SAMESITELAX("SameSiteLax"),
        SAMESITEUNSPECIFIEDTREATEDASLAX("SameSiteUnspecifiedTreatedAsLax"),
        SAMESITENONEINSECURE("SameSiteNoneInsecure"),
        USERPREFERENCES("UserPreferences"),
        THIRDPARTYPHASEOUT("ThirdPartyPhaseout"),
        THIRDPARTYBLOCKEDINFIRSTPARTYSET("ThirdPartyBlockedInFirstPartySet"),
        UNKNOWNERROR("UnknownError"),
        SCHEMEFULSAMESITESTRICT("SchemefulSameSiteStrict"),
        SCHEMEFULSAMESITELAX("SchemefulSameSiteLax"),
        SCHEMEFULSAMESITEUNSPECIFIEDTREATEDASLAX("SchemefulSameSiteUnspecifiedTreatedAsLax"),
        NAMEVALUEPAIREXCEEDSMAXSIZE("NameValuePairExceedsMaxSize"),
        PORTMISMATCH("PortMismatch"),
        SCHEMEMISMATCH("SchemeMismatch"),
        ANONYMOUSCONTEXT("AnonymousContext");
        public final String value;
        CookieBlockedReason(String value) { this.value = value; }
        @Override public String value() { return value; }
        public static CookieBlockedReason of(String value) {
            for (CookieBlockedReason constant : values()) {
                if (constant.value.equals(value)) return constant;
            }
            throw new IllegalArgumentException("Unknown CookieBlockedReason value: " + value);
        }
    }
    /**
     * Types of reasons why a cookie should have been blocked by 3PCD but is exempted for the request.
     * <p><b>Experimental:</b> this part of CDP may change without notice.
     */
    public enum CookieExemptionReason implements CdpValue<String> {
        NONE("None"),
        USERSETTING("UserSetting"),
        TPCDMETADATA("TPCDMetadata"),
        TPCDDEPRECATIONTRIAL("TPCDDeprecationTrial"),
        TOPLEVELTPCDDEPRECATIONTRIAL("TopLevelTPCDDeprecationTrial"),
        TPCDHEURISTICS("TPCDHeuristics"),
        ENTERPRISEPOLICY("EnterprisePolicy"),
        STORAGEACCESS("StorageAccess"),
        TOPLEVELSTORAGEACCESS("TopLevelStorageAccess"),
        SCHEME("Scheme"),
        SAMESITENONECOOKIESINSANDBOX("SameSiteNoneCookiesInSandbox");
        public final String value;
        CookieExemptionReason(String value) { this.value = value; }
        @Override public String value() { return value; }
        public static CookieExemptionReason of(String value) {
            for (CookieExemptionReason constant : values()) {
                if (constant.value.equals(value)) return constant;
            }
            throw new IllegalArgumentException("Unknown CookieExemptionReason value: " + value);
        }
    }
    /**
     * A cookie which was not stored from a response with the corresponding reason.
     * <p><b>Experimental:</b> this part of CDP may change without notice.
     */
    public static final class BlockedSetCookieWithReason extends CdpObject {
        public BlockedSetCookieWithReason() {}
        private BlockedSetCookieWithReason(Map<String, Object> values) { super(values); }
        public static BlockedSetCookieWithReason fromMap(Map<String, Object> values) {
            return new BlockedSetCookieWithReason(values);
        }
        /**
         * The reason(s) this cookie was blocked.
         * @return the protocol field value
         */
        public java.util.List<Network.SetCookieBlockedReason> blockedReasons() {
            return require(BLOCKED_REASONS);
        }
        /**
         * The string representing this individual cookie as it would appear in the header. This is not the entire &quot;cookie&quot; or &quot;set-cookie&quot; header which could have multiple cookies.
         * @return the protocol field value
         */
        public String cookieLine() {
            return requireString("cookieLine");
        }
        /**
         * The cookie object which represents the cookie which was not stored. It is optional because sometimes complete cookie information is not available, such as in the case of parsing errors.
         * @return the protocol field value, empty when absent
         */
        public Optional<Network.Cookie> cookie() {
            return optional(COOKIE);
        }
        /**
         * The reason(s) this cookie was blocked.
         * @param blockedReasons field value
         * @return this model
         */
        public BlockedSetCookieWithReason blockedReasons(java.util.List<Network.SetCookieBlockedReason> blockedReasons) {
            set("blockedReasons", blockedReasons);
            return this;
        }
        /**
         * The string representing this individual cookie as it would appear in the header. This is not the entire &quot;cookie&quot; or &quot;set-cookie&quot; header which could have multiple cookies.
         * @param cookieLine field value
         * @return this model
         */
        public BlockedSetCookieWithReason cookieLine(String cookieLine) {
            set("cookieLine", cookieLine);
            return this;
        }
        /**
         * The cookie object which represents the cookie which was not stored. It is optional because sometimes complete cookie information is not available, such as in the case of parsing errors.
         * @param cookie field value
         * @return this model
         */
        public BlockedSetCookieWithReason cookie(Network.Cookie cookie) {
            set("cookie", cookie);
            return this;
        }
    }
    /**
     * A cookie should have been blocked by 3PCD but is exempted and stored from a response with the corresponding reason. A cookie could only have at most one exemption reason.
     * <p><b>Experimental:</b> this part of CDP may change without notice.
     */
    public static final class ExemptedSetCookieWithReason extends CdpObject {
        public ExemptedSetCookieWithReason() {}
        private ExemptedSetCookieWithReason(Map<String, Object> values) { super(values); }
        public static ExemptedSetCookieWithReason fromMap(Map<String, Object> values) {
            return new ExemptedSetCookieWithReason(values);
        }
        /**
         * The reason the cookie was exempted.
         * @return the protocol field value
         */
        public Network.CookieExemptionReason exemptionReason() {
            return require(EXEMPTION_REASON);
        }
        /**
         * The string representing this individual cookie as it would appear in the header.
         * @return the protocol field value
         */
        public String cookieLine() {
            return requireString("cookieLine");
        }
        /**
         * The cookie object representing the cookie.
         * @return the protocol field value
         */
        public Network.Cookie cookie() {
            return require(COOKIE);
        }
        /**
         * The reason the cookie was exempted.
         * @param exemptionReason field value
         * @return this model
         */
        public ExemptedSetCookieWithReason exemptionReason(Network.CookieExemptionReason exemptionReason) {
            set("exemptionReason", exemptionReason);
            return this;
        }
        /**
         * The string representing this individual cookie as it would appear in the header.
         * @param cookieLine field value
         * @return this model
         */
        public ExemptedSetCookieWithReason cookieLine(String cookieLine) {
            set("cookieLine", cookieLine);
            return this;
        }
        /**
         * The cookie object representing the cookie.
         * @param cookie field value
         * @return this model
         */
        public ExemptedSetCookieWithReason cookie(Network.Cookie cookie) {
            set("cookie", cookie);
            return this;
        }
    }
    /**
     * A cookie associated with the request which may or may not be sent with it. Includes the cookies itself and reasons for blocking or exemption.
     * <p><b>Experimental:</b> this part of CDP may change without notice.
     */
    public static final class AssociatedCookie extends CdpObject {
        public AssociatedCookie() {}
        private AssociatedCookie(Map<String, Object> values) { super(values); }
        public static AssociatedCookie fromMap(Map<String, Object> values) {
            return new AssociatedCookie(values);
        }
        /**
         * The cookie object representing the cookie which was not sent.
         * @return the protocol field value
         */
        public Network.Cookie cookie() {
            return require(COOKIE);
        }
        /**
         * The reason(s) the cookie was blocked. If empty means the cookie is included.
         * @return the protocol field value
         */
        public java.util.List<Network.CookieBlockedReason> blockedReasons() {
            return require(BLOCKED_REASONS_2);
        }
        /**
         * The reason the cookie should have been blocked by 3PCD but is exempted. A cookie could only have at most one exemption reason.
         * @return the protocol field value, empty when absent
         */
        public Optional<Network.CookieExemptionReason> exemptionReason() {
            return optional(EXEMPTION_REASON);
        }
        /**
         * The cookie object representing the cookie which was not sent.
         * @param cookie field value
         * @return this model
         */
        public AssociatedCookie cookie(Network.Cookie cookie) {
            set("cookie", cookie);
            return this;
        }
        /**
         * The reason(s) the cookie was blocked. If empty means the cookie is included.
         * @param blockedReasons field value
         * @return this model
         */
        public AssociatedCookie blockedReasons(java.util.List<Network.CookieBlockedReason> blockedReasons) {
            set("blockedReasons", blockedReasons);
            return this;
        }
        /**
         * The reason the cookie should have been blocked by 3PCD but is exempted. A cookie could only have at most one exemption reason.
         * @param exemptionReason field value
         * @return this model
         */
        public AssociatedCookie exemptionReason(Network.CookieExemptionReason exemptionReason) {
            set("exemptionReason", exemptionReason);
            return this;
        }
    }
    /**
     * Cookie parameter object
     */
    public static final class CookieParam extends CdpObject {
        public CookieParam() {}
        private CookieParam(Map<String, Object> values) { super(values); }
        public static CookieParam fromMap(Map<String, Object> values) {
            return new CookieParam(values);
        }
        /**
         * Cookie name.
         * @return the protocol field value
         */
        public String name() {
            return requireString("name");
        }
        /**
         * Cookie value.
         * @return the protocol field value
         */
        public String value() {
            return requireString("value");
        }
        /**
         * The request-URI to associate with the setting of the cookie. This value can affect the default domain, path, source port, and source scheme values of the created cookie.
         * @return the protocol field value, empty when absent
         */
        public Optional<String> url() {
            return optionalString("url");
        }
        /**
         * Cookie domain.
         * @return the protocol field value, empty when absent
         */
        public Optional<String> domain() {
            return optionalString("domain");
        }
        /**
         * Cookie path.
         * @return the protocol field value, empty when absent
         */
        public Optional<String> path() {
            return optionalString("path");
        }
        /**
         * True if cookie is secure.
         * @return the protocol field value, empty when absent
         */
        public Optional<Boolean> secure() {
            return optionalBoolean("secure");
        }
        /**
         * True if cookie is http-only.
         * @return the protocol field value, empty when absent
         */
        public Optional<Boolean> httpOnly() {
            return optionalBoolean("httpOnly");
        }
        /**
         * Cookie SameSite type.
         * @return the protocol field value, empty when absent
         */
        public Optional<Network.CookieSameSite> sameSite() {
            return optional(SAME_SITE);
        }
        /**
         * Cookie expiration date, session cookie if not set
         * @return the protocol field value, empty when absent
         */
        public Optional<Network.TimeSinceEpoch> expires() {
            return optional(EXPIRES);
        }
        /**
         * Cookie Priority.
         * <p><b>Experimental:</b> this part of CDP may change without notice.
         * @return the protocol field value, empty when absent
         */
        public Optional<Network.CookiePriority> priority() {
            return optional(PRIORITY);
        }
        /**
         * Cookie source scheme type.
         * <p><b>Experimental:</b> this part of CDP may change without notice.
         * @return the protocol field value, empty when absent
         */
        public Optional<Network.CookieSourceScheme> sourceScheme() {
            return optional(SOURCE_SCHEME);
        }
        /**
         * Cookie source port. Valid values are {-1, [1, 65535]}, -1 indicates an unspecified port. An unspecified port value allows protocol clients to emulate legacy cookie scope for the port. This is a temporary ability and it will be removed in the future.
         * <p><b>Experimental:</b> this part of CDP may change without notice.
         * @return the protocol field value, empty when absent
         */
        public OptionalLong sourcePort() {
            return optionalLong("sourcePort");
        }
        /**
         * Cookie partition key. If not set, the cookie will be set as not partitioned.
         * <p><b>Experimental:</b> this part of CDP may change without notice.
         * @return the protocol field value, empty when absent
         */
        public Optional<Network.CookiePartitionKey> partitionKey() {
            return optional(PARTITION_KEY);
        }
        /**
         * Cookie name.
         * @param name field value
         * @return this model
         */
        public CookieParam name(String name) {
            set("name", name);
            return this;
        }
        /**
         * Cookie value.
         * @param value field value
         * @return this model
         */
        public CookieParam value(String value) {
            set("value", value);
            return this;
        }
        /**
         * The request-URI to associate with the setting of the cookie. This value can affect the default domain, path, source port, and source scheme values of the created cookie.
         * @param url field value
         * @return this model
         */
        public CookieParam url(String url) {
            set("url", url);
            return this;
        }
        /**
         * Cookie domain.
         * @param domain field value
         * @return this model
         */
        public CookieParam domain(String domain) {
            set("domain", domain);
            return this;
        }
        /**
         * Cookie path.
         * @param path field value
         * @return this model
         */
        public CookieParam path(String path) {
            set("path", path);
            return this;
        }
        /**
         * True if cookie is secure.
         * @param secure field value
         * @return this model
         */
        public CookieParam secure(boolean secure) {
            set("secure", secure);
            return this;
        }
        /**
         * True if cookie is http-only.
         * @param httpOnly field value
         * @return this model
         */
        public CookieParam httpOnly(boolean httpOnly) {
            set("httpOnly", httpOnly);
            return this;
        }
        /**
         * Cookie SameSite type.
         * @param sameSite field value
         * @return this model
         */
        public CookieParam sameSite(Network.CookieSameSite sameSite) {
            set("sameSite", sameSite);
            return this;
        }
        /**
         * Cookie expiration date, session cookie if not set
         * @param expires field value
         * @return this model
         */
        public CookieParam expires(Network.TimeSinceEpoch expires) {
            set("expires", expires);
            return this;
        }
        /**
         * Cookie Priority.
         * <p><b>Experimental:</b> this part of CDP may change without notice.
         * @param priority field value
         * @return this model
         */
        public CookieParam priority(Network.CookiePriority priority) {
            set("priority", priority);
            return this;
        }
        /**
         * Cookie source scheme type.
         * <p><b>Experimental:</b> this part of CDP may change without notice.
         * @param sourceScheme field value
         * @return this model
         */
        public CookieParam sourceScheme(Network.CookieSourceScheme sourceScheme) {
            set("sourceScheme", sourceScheme);
            return this;
        }
        /**
         * Cookie source port. Valid values are {-1, [1, 65535]}, -1 indicates an unspecified port. An unspecified port value allows protocol clients to emulate legacy cookie scope for the port. This is a temporary ability and it will be removed in the future.
         * <p><b>Experimental:</b> this part of CDP may change without notice.
         * @param sourcePort field value
         * @return this model
         */
        public CookieParam sourcePort(long sourcePort) {
            set("sourcePort", sourcePort);
            return this;
        }
        /**
         * Cookie partition key. If not set, the cookie will be set as not partitioned.
         * <p><b>Experimental:</b> this part of CDP may change without notice.
         * @param partitionKey field value
         * @return this model
         */
        public CookieParam partitionKey(Network.CookiePartitionKey partitionKey) {
            set("partitionKey", partitionKey);
            return this;
        }
    }
    /**
     * Authorization challenge for HTTP status code 401 or 407.
     * <p><b>Experimental:</b> this part of CDP may change without notice.
     */
    public static final class AuthChallenge extends CdpObject {
        public AuthChallenge() {}
        private AuthChallenge(Map<String, Object> values) { super(values); }
        public static AuthChallenge fromMap(Map<String, Object> values) {
            return new AuthChallenge(values);
        }
        /**
         * Source of the authentication challenge.
         */
        public enum SourceValues implements CdpValue<String> {
            SERVER("Server"),
            PROXY("Proxy");
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
         * Source of the authentication challenge.
         * @return the protocol field value, empty when absent
         */
        public Optional<AuthChallenge.SourceValues> source() {
            return optional(SOURCE);
        }
        /**
         * Origin of the challenger.
         * @return the protocol field value
         */
        public String origin() {
            return requireString("origin");
        }
        /**
         * The authentication scheme used, such as basic or digest
         * @return the protocol field value
         */
        public String scheme() {
            return requireString("scheme");
        }
        /**
         * The realm of the challenge. May be empty.
         * @return the protocol field value
         */
        public String realm() {
            return requireString("realm");
        }
        /**
         * Source of the authentication challenge.
         * @param source field value
         * @return this model
         */
        public AuthChallenge source(AuthChallenge.SourceValues source) {
            set("source", source);
            return this;
        }
        /**
         * Origin of the challenger.
         * @param origin field value
         * @return this model
         */
        public AuthChallenge origin(String origin) {
            set("origin", origin);
            return this;
        }
        /**
         * The authentication scheme used, such as basic or digest
         * @param scheme field value
         * @return this model
         */
        public AuthChallenge scheme(String scheme) {
            set("scheme", scheme);
            return this;
        }
        /**
         * The realm of the challenge. May be empty.
         * @param realm field value
         * @return this model
         */
        public AuthChallenge realm(String realm) {
            set("realm", realm);
            return this;
        }
    }
    /**
     * Response to an AuthChallenge.
     * <p><b>Experimental:</b> this part of CDP may change without notice.
     */
    public static final class AuthChallengeResponse extends CdpObject {
        public AuthChallengeResponse() {}
        private AuthChallengeResponse(Map<String, Object> values) { super(values); }
        public static AuthChallengeResponse fromMap(Map<String, Object> values) {
            return new AuthChallengeResponse(values);
        }
        /**
         * The decision on what to do in response to the authorization challenge. Default means deferring to the default behavior of the net stack, which will likely either the Cancel authentication or display a popup dialog box.
         */
        public enum ResponseValues implements CdpValue<String> {
            DEFAULT("Default"),
            CANCELAUTH("CancelAuth"),
            PROVIDECREDENTIALS("ProvideCredentials");
            public final String value;
            ResponseValues(String value) { this.value = value; }
            @Override public String value() { return value; }
            public static ResponseValues of(String value) {
                for (ResponseValues constant : values()) {
                    if (constant.value.equals(value)) return constant;
                }
                throw new IllegalArgumentException("Unknown ResponseValues value: " + value);
            }
        }
        /**
         * The decision on what to do in response to the authorization challenge. Default means deferring to the default behavior of the net stack, which will likely either the Cancel authentication or display a popup dialog box.
         * @return the protocol field value
         */
        public AuthChallengeResponse.ResponseValues response() {
            return require(RESPONSE_2);
        }
        /**
         * The username to provide, possibly empty. Should only be set if response is ProvideCredentials.
         * @return the protocol field value, empty when absent
         */
        public Optional<String> username() {
            return optionalString("username");
        }
        /**
         * The password to provide, possibly empty. Should only be set if response is ProvideCredentials.
         * @return the protocol field value, empty when absent
         */
        public Optional<String> password() {
            return optionalString("password");
        }
        /**
         * The decision on what to do in response to the authorization challenge. Default means deferring to the default behavior of the net stack, which will likely either the Cancel authentication or display a popup dialog box.
         * @param response field value
         * @return this model
         */
        public AuthChallengeResponse response(AuthChallengeResponse.ResponseValues response) {
            set("response", response);
            return this;
        }
        /**
         * The username to provide, possibly empty. Should only be set if response is ProvideCredentials.
         * @param username field value
         * @return this model
         */
        public AuthChallengeResponse username(String username) {
            set("username", username);
            return this;
        }
        /**
         * The password to provide, possibly empty. Should only be set if response is ProvideCredentials.
         * @param password field value
         * @return this model
         */
        public AuthChallengeResponse password(String password) {
            set("password", password);
            return this;
        }
    }
    /**
     * Stages of the interception to begin intercepting. Request will intercept before the request is sent. Response will intercept after the response is received.
     * <p><b>Experimental:</b> this part of CDP may change without notice.
     */
    public enum InterceptionStage implements CdpValue<String> {
        REQUEST("Request"),
        HEADERSRECEIVED("HeadersReceived");
        public final String value;
        InterceptionStage(String value) { this.value = value; }
        @Override public String value() { return value; }
        public static InterceptionStage of(String value) {
            for (InterceptionStage constant : values()) {
                if (constant.value.equals(value)) return constant;
            }
            throw new IllegalArgumentException("Unknown InterceptionStage value: " + value);
        }
    }
    /**
     * Request pattern for interception.
     * <p><b>Experimental:</b> this part of CDP may change without notice.
     */
    public static final class RequestPattern extends CdpObject {
        public RequestPattern() {}
        private RequestPattern(Map<String, Object> values) { super(values); }
        public static RequestPattern fromMap(Map<String, Object> values) {
            return new RequestPattern(values);
        }
        /**
         * Wildcards ({@code &#x27;*&#x27;} -&gt; zero or more, {@code &#x27;?&#x27;} -&gt; exactly one) are allowed. Escape character is backslash. Omitting is equivalent to {@code &quot;*&quot;}.
         * @return the protocol field value, empty when absent
         */
        public Optional<String> urlPattern() {
            return optionalString("urlPattern");
        }
        /**
         * If set, only requests for matching resource types will be intercepted.
         * @return the protocol field value, empty when absent
         */
        public Optional<Network.ResourceType> resourceType() {
            return optional(RESOURCE_TYPE);
        }
        /**
         * Stage at which to begin intercepting requests. Default is Request.
         * @return the protocol field value, empty when absent
         */
        public Optional<Network.InterceptionStage> interceptionStage() {
            return optional(INTERCEPTION_STAGE);
        }
        /**
         * Wildcards ({@code &#x27;*&#x27;} -&gt; zero or more, {@code &#x27;?&#x27;} -&gt; exactly one) are allowed. Escape character is backslash. Omitting is equivalent to {@code &quot;*&quot;}.
         * @param urlPattern field value
         * @return this model
         */
        public RequestPattern urlPattern(String urlPattern) {
            set("urlPattern", urlPattern);
            return this;
        }
        /**
         * If set, only requests for matching resource types will be intercepted.
         * @param resourceType field value
         * @return this model
         */
        public RequestPattern resourceType(Network.ResourceType resourceType) {
            set("resourceType", resourceType);
            return this;
        }
        /**
         * Stage at which to begin intercepting requests. Default is Request.
         * @param interceptionStage field value
         * @return this model
         */
        public RequestPattern interceptionStage(Network.InterceptionStage interceptionStage) {
            set("interceptionStage", interceptionStage);
            return this;
        }
    }
    /**
     * Information about a signed exchange signature. https://wicg.github.io/webpackage/draft-yasskin-httpbis-origin-signed-exchanges-impl.html#rfc.section.3.1
     * <p><b>Experimental:</b> this part of CDP may change without notice.
     */
    public static final class SignedExchangeSignature extends CdpObject {
        public SignedExchangeSignature() {}
        private SignedExchangeSignature(Map<String, Object> values) { super(values); }
        public static SignedExchangeSignature fromMap(Map<String, Object> values) {
            return new SignedExchangeSignature(values);
        }
        /**
         * Signed exchange signature label.
         * @return the protocol field value
         */
        public String label() {
            return requireString("label");
        }
        /**
         * The hex string of signed exchange signature.
         * @return the protocol field value
         */
        public String signature() {
            return requireString("signature");
        }
        /**
         * Signed exchange signature integrity.
         * @return the protocol field value
         */
        public String integrity() {
            return requireString("integrity");
        }
        /**
         * Signed exchange signature cert Url.
         * @return the protocol field value, empty when absent
         */
        public Optional<String> certUrl() {
            return optionalString("certUrl");
        }
        /**
         * The hex string of signed exchange signature cert sha256.
         * @return the protocol field value, empty when absent
         */
        public Optional<String> certSha256() {
            return optionalString("certSha256");
        }
        /**
         * Signed exchange signature validity Url.
         * @return the protocol field value
         */
        public String validityUrl() {
            return requireString("validityUrl");
        }
        /**
         * Signed exchange signature date.
         * @return the protocol field value
         */
        public long date() {
            return requireLong("date");
        }
        /**
         * Signed exchange signature expires.
         * @return the protocol field value
         */
        public long expires() {
            return requireLong("expires");
        }
        /**
         * The encoded certificates.
         * @return the protocol field value, empty when absent
         */
        public Optional<java.util.List<String>> certificates() {
            return optional(CERTIFICATES);
        }
        /**
         * Signed exchange signature label.
         * @param label field value
         * @return this model
         */
        public SignedExchangeSignature label(String label) {
            set("label", label);
            return this;
        }
        /**
         * The hex string of signed exchange signature.
         * @param signature field value
         * @return this model
         */
        public SignedExchangeSignature signature(String signature) {
            set("signature", signature);
            return this;
        }
        /**
         * Signed exchange signature integrity.
         * @param integrity field value
         * @return this model
         */
        public SignedExchangeSignature integrity(String integrity) {
            set("integrity", integrity);
            return this;
        }
        /**
         * Signed exchange signature cert Url.
         * @param certUrl field value
         * @return this model
         */
        public SignedExchangeSignature certUrl(String certUrl) {
            set("certUrl", certUrl);
            return this;
        }
        /**
         * The hex string of signed exchange signature cert sha256.
         * @param certSha256 field value
         * @return this model
         */
        public SignedExchangeSignature certSha256(String certSha256) {
            set("certSha256", certSha256);
            return this;
        }
        /**
         * Signed exchange signature validity Url.
         * @param validityUrl field value
         * @return this model
         */
        public SignedExchangeSignature validityUrl(String validityUrl) {
            set("validityUrl", validityUrl);
            return this;
        }
        /**
         * Signed exchange signature date.
         * @param date field value
         * @return this model
         */
        public SignedExchangeSignature date(long date) {
            set("date", date);
            return this;
        }
        /**
         * Signed exchange signature expires.
         * @param expires field value
         * @return this model
         */
        public SignedExchangeSignature expires(long expires) {
            set("expires", expires);
            return this;
        }
        /**
         * The encoded certificates.
         * @param certificates field value
         * @return this model
         */
        public SignedExchangeSignature certificates(java.util.List<String> certificates) {
            set("certificates", certificates);
            return this;
        }
    }
    /**
     * Information about a signed exchange header. https://wicg.github.io/webpackage/draft-yasskin-httpbis-origin-signed-exchanges-impl.html#cbor-representation
     * <p><b>Experimental:</b> this part of CDP may change without notice.
     */
    public static final class SignedExchangeHeader extends CdpObject {
        public SignedExchangeHeader() {}
        private SignedExchangeHeader(Map<String, Object> values) { super(values); }
        public static SignedExchangeHeader fromMap(Map<String, Object> values) {
            return new SignedExchangeHeader(values);
        }
        /**
         * Signed exchange request URL.
         * @return the protocol field value
         */
        public String requestUrl() {
            return requireString("requestUrl");
        }
        /**
         * Signed exchange response code.
         * @return the protocol field value
         */
        public long responseCode() {
            return requireLong("responseCode");
        }
        /**
         * Signed exchange response headers.
         * @return the protocol field value
         */
        public java.util.Map<String, Object> responseHeaders() {
            return requireObject("responseHeaders");
        }
        /**
         * Signed exchange response signature.
         * @return the protocol field value
         */
        public java.util.List<Network.SignedExchangeSignature> signatures() {
            return require(SIGNATURES);
        }
        /**
         * Signed exchange header integrity hash in the form of {@code sha256-&lt;base64-hash-value&gt;}.
         * @return the protocol field value
         */
        public String headerIntegrity() {
            return requireString("headerIntegrity");
        }
        /**
         * Signed exchange request URL.
         * @param requestUrl field value
         * @return this model
         */
        public SignedExchangeHeader requestUrl(String requestUrl) {
            set("requestUrl", requestUrl);
            return this;
        }
        /**
         * Signed exchange response code.
         * @param responseCode field value
         * @return this model
         */
        public SignedExchangeHeader responseCode(long responseCode) {
            set("responseCode", responseCode);
            return this;
        }
        /**
         * Signed exchange response headers.
         * @param responseHeaders field value
         * @return this model
         */
        public SignedExchangeHeader responseHeaders(java.util.Map<String, Object> responseHeaders) {
            set("responseHeaders", responseHeaders);
            return this;
        }
        /**
         * Signed exchange response signature.
         * @param signatures field value
         * @return this model
         */
        public SignedExchangeHeader signatures(java.util.List<Network.SignedExchangeSignature> signatures) {
            set("signatures", signatures);
            return this;
        }
        /**
         * Signed exchange header integrity hash in the form of {@code sha256-&lt;base64-hash-value&gt;}.
         * @param headerIntegrity field value
         * @return this model
         */
        public SignedExchangeHeader headerIntegrity(String headerIntegrity) {
            set("headerIntegrity", headerIntegrity);
            return this;
        }
    }
    /**
     * Field type for a signed exchange related error.
     * <p><b>Experimental:</b> this part of CDP may change without notice.
     */
    public enum SignedExchangeErrorField implements CdpValue<String> {
        SIGNATURESIG("signatureSig"),
        SIGNATUREINTEGRITY("signatureIntegrity"),
        SIGNATURECERTURL("signatureCertUrl"),
        SIGNATURECERTSHA256("signatureCertSha256"),
        SIGNATUREVALIDITYURL("signatureValidityUrl"),
        SIGNATURETIMESTAMPS("signatureTimestamps");
        public final String value;
        SignedExchangeErrorField(String value) { this.value = value; }
        @Override public String value() { return value; }
        public static SignedExchangeErrorField of(String value) {
            for (SignedExchangeErrorField constant : values()) {
                if (constant.value.equals(value)) return constant;
            }
            throw new IllegalArgumentException("Unknown SignedExchangeErrorField value: " + value);
        }
    }
    /**
     * Information about a signed exchange response.
     * <p><b>Experimental:</b> this part of CDP may change without notice.
     */
    public static final class SignedExchangeError extends CdpObject {
        public SignedExchangeError() {}
        private SignedExchangeError(Map<String, Object> values) { super(values); }
        public static SignedExchangeError fromMap(Map<String, Object> values) {
            return new SignedExchangeError(values);
        }
        /**
         * Error message.
         * @return the protocol field value
         */
        public String message() {
            return requireString("message");
        }
        /**
         * The index of the signature which caused the error.
         * @return the protocol field value, empty when absent
         */
        public OptionalLong signatureIndex() {
            return optionalLong("signatureIndex");
        }
        /**
         * The field which caused the error.
         * @return the protocol field value, empty when absent
         */
        public Optional<Network.SignedExchangeErrorField> errorField() {
            return optional(ERROR_FIELD);
        }
        /**
         * Error message.
         * @param message field value
         * @return this model
         */
        public SignedExchangeError message(String message) {
            set("message", message);
            return this;
        }
        /**
         * The index of the signature which caused the error.
         * @param signatureIndex field value
         * @return this model
         */
        public SignedExchangeError signatureIndex(long signatureIndex) {
            set("signatureIndex", signatureIndex);
            return this;
        }
        /**
         * The field which caused the error.
         * @param errorField field value
         * @return this model
         */
        public SignedExchangeError errorField(Network.SignedExchangeErrorField errorField) {
            set("errorField", errorField);
            return this;
        }
    }
    /**
     * Information about a signed exchange response.
     * <p><b>Experimental:</b> this part of CDP may change without notice.
     */
    public static final class SignedExchangeInfo extends CdpObject {
        public SignedExchangeInfo() {}
        private SignedExchangeInfo(Map<String, Object> values) { super(values); }
        public static SignedExchangeInfo fromMap(Map<String, Object> values) {
            return new SignedExchangeInfo(values);
        }
        /**
         * The outer response of signed HTTP exchange which was received from network.
         * @return the protocol field value
         */
        public Network.Response outerResponse() {
            return require(OUTER_RESPONSE);
        }
        /**
         * Whether network response for the signed exchange was accompanied by extra headers.
         * @return the protocol field value
         */
        public boolean hasExtraInfo() {
            return requireBoolean("hasExtraInfo");
        }
        /**
         * Information about the signed exchange header.
         * @return the protocol field value, empty when absent
         */
        public Optional<Network.SignedExchangeHeader> header() {
            return optional(HEADER);
        }
        /**
         * Security details for the signed exchange header.
         * @return the protocol field value, empty when absent
         */
        public Optional<Network.SecurityDetails> securityDetails() {
            return optional(SECURITY_DETAILS);
        }
        /**
         * Errors occurred while handling the signed exchange.
         * @return the protocol field value, empty when absent
         */
        public Optional<java.util.List<Network.SignedExchangeError>> errors() {
            return optional(ERRORS);
        }
        /**
         * The outer response of signed HTTP exchange which was received from network.
         * @param outerResponse field value
         * @return this model
         */
        public SignedExchangeInfo outerResponse(Network.Response outerResponse) {
            set("outerResponse", outerResponse);
            return this;
        }
        /**
         * Whether network response for the signed exchange was accompanied by extra headers.
         * @param hasExtraInfo field value
         * @return this model
         */
        public SignedExchangeInfo hasExtraInfo(boolean hasExtraInfo) {
            set("hasExtraInfo", hasExtraInfo);
            return this;
        }
        /**
         * Information about the signed exchange header.
         * @param header field value
         * @return this model
         */
        public SignedExchangeInfo header(Network.SignedExchangeHeader header) {
            set("header", header);
            return this;
        }
        /**
         * Security details for the signed exchange header.
         * @param securityDetails field value
         * @return this model
         */
        public SignedExchangeInfo securityDetails(Network.SecurityDetails securityDetails) {
            set("securityDetails", securityDetails);
            return this;
        }
        /**
         * Errors occurred while handling the signed exchange.
         * @param errors field value
         * @return this model
         */
        public SignedExchangeInfo errors(java.util.List<Network.SignedExchangeError> errors) {
            set("errors", errors);
            return this;
        }
    }
    /**
     * List of content encodings supported by the backend.
     * <p><b>Experimental:</b> this part of CDP may change without notice.
     */
    public enum ContentEncoding implements CdpValue<String> {
        DEFLATE("deflate"),
        GZIP("gzip"),
        BR("br"),
        ZSTD("zstd");
        public final String value;
        ContentEncoding(String value) { this.value = value; }
        @Override public String value() { return value; }
        public static ContentEncoding of(String value) {
            for (ContentEncoding constant : values()) {
                if (constant.value.equals(value)) return constant;
            }
            throw new IllegalArgumentException("Unknown ContentEncoding value: " + value);
        }
    }
    /**
     * <p><b>Experimental:</b> this part of CDP may change without notice.
     */
    public static final class NetworkConditions extends CdpObject {
        public NetworkConditions() {}
        private NetworkConditions(Map<String, Object> values) { super(values); }
        public static NetworkConditions fromMap(Map<String, Object> values) {
            return new NetworkConditions(values);
        }
        /**
         * Only matching requests will be affected by these conditions. Patterns use the URLPattern constructor string syntax (https://urlpattern.spec.whatwg.org/) and must be absolute. If the pattern is empty, all requests are matched (including p2p connections).
         * @return the protocol field value
         */
        public String urlPattern() {
            return requireString("urlPattern");
        }
        /**
         * Minimum latency from request sent to response headers received (ms).
         * @return the protocol field value
         */
        public double latency() {
            return requireDouble("latency");
        }
        /**
         * Maximal aggregated download throughput (bytes/sec). -1 disables download throttling.
         * @return the protocol field value
         */
        public double downloadThroughput() {
            return requireDouble("downloadThroughput");
        }
        /**
         * Maximal aggregated upload throughput (bytes/sec). -1 disables upload throttling.
         * @return the protocol field value
         */
        public double uploadThroughput() {
            return requireDouble("uploadThroughput");
        }
        /**
         * Connection type if known.
         * @return the protocol field value, empty when absent
         */
        public Optional<Network.ConnectionType> connectionType() {
            return optional(CONNECTION_TYPE);
        }
        /**
         * WebRTC packet loss (percent, 0-100). 0 disables packet loss emulation, 100 drops all the packets.
         * @return the protocol field value, empty when absent
         */
        public OptionalDouble packetLoss() {
            return optionalDouble("packetLoss");
        }
        /**
         * WebRTC packet queue length (packet). 0 removes any queue length limitations.
         * @return the protocol field value, empty when absent
         */
        public OptionalLong packetQueueLength() {
            return optionalLong("packetQueueLength");
        }
        /**
         * WebRTC packetReordering feature.
         * @return the protocol field value, empty when absent
         */
        public Optional<Boolean> packetReordering() {
            return optionalBoolean("packetReordering");
        }
        /**
         * True to emulate internet disconnection.
         * @return the protocol field value, empty when absent
         */
        public Optional<Boolean> offline() {
            return optionalBoolean("offline");
        }
        /**
         * Only matching requests will be affected by these conditions. Patterns use the URLPattern constructor string syntax (https://urlpattern.spec.whatwg.org/) and must be absolute. If the pattern is empty, all requests are matched (including p2p connections).
         * @param urlPattern field value
         * @return this model
         */
        public NetworkConditions urlPattern(String urlPattern) {
            set("urlPattern", urlPattern);
            return this;
        }
        /**
         * Minimum latency from request sent to response headers received (ms).
         * @param latency field value
         * @return this model
         */
        public NetworkConditions latency(double latency) {
            set("latency", latency);
            return this;
        }
        /**
         * Maximal aggregated download throughput (bytes/sec). -1 disables download throttling.
         * @param downloadThroughput field value
         * @return this model
         */
        public NetworkConditions downloadThroughput(double downloadThroughput) {
            set("downloadThroughput", downloadThroughput);
            return this;
        }
        /**
         * Maximal aggregated upload throughput (bytes/sec). -1 disables upload throttling.
         * @param uploadThroughput field value
         * @return this model
         */
        public NetworkConditions uploadThroughput(double uploadThroughput) {
            set("uploadThroughput", uploadThroughput);
            return this;
        }
        /**
         * Connection type if known.
         * @param connectionType field value
         * @return this model
         */
        public NetworkConditions connectionType(Network.ConnectionType connectionType) {
            set("connectionType", connectionType);
            return this;
        }
        /**
         * WebRTC packet loss (percent, 0-100). 0 disables packet loss emulation, 100 drops all the packets.
         * @param packetLoss field value
         * @return this model
         */
        public NetworkConditions packetLoss(double packetLoss) {
            set("packetLoss", packetLoss);
            return this;
        }
        /**
         * WebRTC packet queue length (packet). 0 removes any queue length limitations.
         * @param packetQueueLength field value
         * @return this model
         */
        public NetworkConditions packetQueueLength(long packetQueueLength) {
            set("packetQueueLength", packetQueueLength);
            return this;
        }
        /**
         * WebRTC packetReordering feature.
         * @param packetReordering field value
         * @return this model
         */
        public NetworkConditions packetReordering(boolean packetReordering) {
            set("packetReordering", packetReordering);
            return this;
        }
        /**
         * True to emulate internet disconnection.
         * @param offline field value
         * @return this model
         */
        public NetworkConditions offline(boolean offline) {
            set("offline", offline);
            return this;
        }
    }
    /**
     * <p><b>Experimental:</b> this part of CDP may change without notice.
     */
    public static final class BlockPattern extends CdpObject {
        public BlockPattern() {}
        private BlockPattern(Map<String, Object> values) { super(values); }
        public static BlockPattern fromMap(Map<String, Object> values) {
            return new BlockPattern(values);
        }
        /**
         * URL pattern to match. Patterns use the URLPattern constructor string syntax (https://urlpattern.spec.whatwg.org/) and must be absolute. Example: {@code *://*:*&amp;#47;*.css}.
         * @return the protocol field value
         */
        public String urlPattern() {
            return requireString("urlPattern");
        }
        /**
         * Whether or not to block the pattern. If false, a matching request will not be blocked even if it matches a later {@code BlockPattern}.
         * @return the protocol field value
         */
        public boolean block() {
            return requireBoolean("block");
        }
        /**
         * URL pattern to match. Patterns use the URLPattern constructor string syntax (https://urlpattern.spec.whatwg.org/) and must be absolute. Example: {@code *://*:*&amp;#47;*.css}.
         * @param urlPattern field value
         * @return this model
         */
        public BlockPattern urlPattern(String urlPattern) {
            set("urlPattern", urlPattern);
            return this;
        }
        /**
         * Whether or not to block the pattern. If false, a matching request will not be blocked even if it matches a later {@code BlockPattern}.
         * @param block field value
         * @return this model
         */
        public BlockPattern block(boolean block) {
            set("block", block);
            return this;
        }
    }
    /**
     * Wire values for DirectSocketDnsQueryType.
     * <p><b>Experimental:</b> this part of CDP may change without notice.
     */
    public enum DirectSocketDnsQueryType implements CdpValue<String> {
        IPV4("ipv4"),
        IPV6("ipv6");
        public final String value;
        DirectSocketDnsQueryType(String value) { this.value = value; }
        @Override public String value() { return value; }
        public static DirectSocketDnsQueryType of(String value) {
            for (DirectSocketDnsQueryType constant : values()) {
                if (constant.value.equals(value)) return constant;
            }
            throw new IllegalArgumentException("Unknown DirectSocketDnsQueryType value: " + value);
        }
    }
    /**
     * <p><b>Experimental:</b> this part of CDP may change without notice.
     */
    public static final class DirectTCPSocketOptions extends CdpObject {
        public DirectTCPSocketOptions() {}
        private DirectTCPSocketOptions(Map<String, Object> values) { super(values); }
        public static DirectTCPSocketOptions fromMap(Map<String, Object> values) {
            return new DirectTCPSocketOptions(values);
        }
        /**
         * TCP_NODELAY option
         * @return the protocol field value
         */
        public boolean noDelay() {
            return requireBoolean("noDelay");
        }
        /**
         * Expected to be unsigned integer.
         * @return the protocol field value, empty when absent
         */
        public OptionalDouble keepAliveDelay() {
            return optionalDouble("keepAliveDelay");
        }
        /**
         * Expected to be unsigned integer.
         * @return the protocol field value, empty when absent
         */
        public OptionalDouble sendBufferSize() {
            return optionalDouble("sendBufferSize");
        }
        /**
         * Expected to be unsigned integer.
         * @return the protocol field value, empty when absent
         */
        public OptionalDouble receiveBufferSize() {
            return optionalDouble("receiveBufferSize");
        }
        /**
         * Returns the dnsQueryType field.
         * @return the protocol field value, empty when absent
         */
        public Optional<Network.DirectSocketDnsQueryType> dnsQueryType() {
            return optional(DNS_QUERY_TYPE);
        }
        /**
         * TCP_NODELAY option
         * @param noDelay field value
         * @return this model
         */
        public DirectTCPSocketOptions noDelay(boolean noDelay) {
            set("noDelay", noDelay);
            return this;
        }
        /**
         * Expected to be unsigned integer.
         * @param keepAliveDelay field value
         * @return this model
         */
        public DirectTCPSocketOptions keepAliveDelay(double keepAliveDelay) {
            set("keepAliveDelay", keepAliveDelay);
            return this;
        }
        /**
         * Expected to be unsigned integer.
         * @param sendBufferSize field value
         * @return this model
         */
        public DirectTCPSocketOptions sendBufferSize(double sendBufferSize) {
            set("sendBufferSize", sendBufferSize);
            return this;
        }
        /**
         * Expected to be unsigned integer.
         * @param receiveBufferSize field value
         * @return this model
         */
        public DirectTCPSocketOptions receiveBufferSize(double receiveBufferSize) {
            set("receiveBufferSize", receiveBufferSize);
            return this;
        }
        /**
         * Sets the dnsQueryType field.
         * @param dnsQueryType field value
         * @return this model
         */
        public DirectTCPSocketOptions dnsQueryType(Network.DirectSocketDnsQueryType dnsQueryType) {
            set("dnsQueryType", dnsQueryType);
            return this;
        }
    }
    /**
     * <p><b>Experimental:</b> this part of CDP may change without notice.
     */
    public static final class DirectUDPSocketOptions extends CdpObject {
        public DirectUDPSocketOptions() {}
        private DirectUDPSocketOptions(Map<String, Object> values) { super(values); }
        public static DirectUDPSocketOptions fromMap(Map<String, Object> values) {
            return new DirectUDPSocketOptions(values);
        }
        /**
         * Returns the remoteAddr field.
         * @return the protocol field value, empty when absent
         */
        public Optional<String> remoteAddr() {
            return optionalString("remoteAddr");
        }
        /**
         * Unsigned int 16.
         * @return the protocol field value, empty when absent
         */
        public OptionalLong remotePort() {
            return optionalLong("remotePort");
        }
        /**
         * Returns the localAddr field.
         * @return the protocol field value, empty when absent
         */
        public Optional<String> localAddr() {
            return optionalString("localAddr");
        }
        /**
         * Unsigned int 16.
         * @return the protocol field value, empty when absent
         */
        public OptionalLong localPort() {
            return optionalLong("localPort");
        }
        /**
         * Returns the dnsQueryType field.
         * @return the protocol field value, empty when absent
         */
        public Optional<Network.DirectSocketDnsQueryType> dnsQueryType() {
            return optional(DNS_QUERY_TYPE);
        }
        /**
         * Expected to be unsigned integer.
         * @return the protocol field value, empty when absent
         */
        public OptionalDouble sendBufferSize() {
            return optionalDouble("sendBufferSize");
        }
        /**
         * Expected to be unsigned integer.
         * @return the protocol field value, empty when absent
         */
        public OptionalDouble receiveBufferSize() {
            return optionalDouble("receiveBufferSize");
        }
        /**
         * Returns the multicastLoopback field.
         * @return the protocol field value, empty when absent
         */
        public Optional<Boolean> multicastLoopback() {
            return optionalBoolean("multicastLoopback");
        }
        /**
         * Unsigned int 8.
         * @return the protocol field value, empty when absent
         */
        public OptionalLong multicastTimeToLive() {
            return optionalLong("multicastTimeToLive");
        }
        /**
         * Returns the multicastAllowAddressSharing field.
         * @return the protocol field value, empty when absent
         */
        public Optional<Boolean> multicastAllowAddressSharing() {
            return optionalBoolean("multicastAllowAddressSharing");
        }
        /**
         * Sets the remoteAddr field.
         * @param remoteAddr field value
         * @return this model
         */
        public DirectUDPSocketOptions remoteAddr(String remoteAddr) {
            set("remoteAddr", remoteAddr);
            return this;
        }
        /**
         * Unsigned int 16.
         * @param remotePort field value
         * @return this model
         */
        public DirectUDPSocketOptions remotePort(long remotePort) {
            set("remotePort", remotePort);
            return this;
        }
        /**
         * Sets the localAddr field.
         * @param localAddr field value
         * @return this model
         */
        public DirectUDPSocketOptions localAddr(String localAddr) {
            set("localAddr", localAddr);
            return this;
        }
        /**
         * Unsigned int 16.
         * @param localPort field value
         * @return this model
         */
        public DirectUDPSocketOptions localPort(long localPort) {
            set("localPort", localPort);
            return this;
        }
        /**
         * Sets the dnsQueryType field.
         * @param dnsQueryType field value
         * @return this model
         */
        public DirectUDPSocketOptions dnsQueryType(Network.DirectSocketDnsQueryType dnsQueryType) {
            set("dnsQueryType", dnsQueryType);
            return this;
        }
        /**
         * Expected to be unsigned integer.
         * @param sendBufferSize field value
         * @return this model
         */
        public DirectUDPSocketOptions sendBufferSize(double sendBufferSize) {
            set("sendBufferSize", sendBufferSize);
            return this;
        }
        /**
         * Expected to be unsigned integer.
         * @param receiveBufferSize field value
         * @return this model
         */
        public DirectUDPSocketOptions receiveBufferSize(double receiveBufferSize) {
            set("receiveBufferSize", receiveBufferSize);
            return this;
        }
        /**
         * Sets the multicastLoopback field.
         * @param multicastLoopback field value
         * @return this model
         */
        public DirectUDPSocketOptions multicastLoopback(boolean multicastLoopback) {
            set("multicastLoopback", multicastLoopback);
            return this;
        }
        /**
         * Unsigned int 8.
         * @param multicastTimeToLive field value
         * @return this model
         */
        public DirectUDPSocketOptions multicastTimeToLive(long multicastTimeToLive) {
            set("multicastTimeToLive", multicastTimeToLive);
            return this;
        }
        /**
         * Sets the multicastAllowAddressSharing field.
         * @param multicastAllowAddressSharing field value
         * @return this model
         */
        public DirectUDPSocketOptions multicastAllowAddressSharing(boolean multicastAllowAddressSharing) {
            set("multicastAllowAddressSharing", multicastAllowAddressSharing);
            return this;
        }
    }
    /**
     * <p><b>Experimental:</b> this part of CDP may change without notice.
     */
    public static final class DirectUDPMessage extends CdpObject {
        public DirectUDPMessage() {}
        private DirectUDPMessage(Map<String, Object> values) { super(values); }
        public static DirectUDPMessage fromMap(Map<String, Object> values) {
            return new DirectUDPMessage(values);
        }
        /**
         * Returns the data field.
         * @return the protocol field value
         */
        public String data() {
            return requireString("data");
        }
        /**
         * Null for connected mode.
         * @return the protocol field value, empty when absent
         */
        public Optional<String> remoteAddr() {
            return optionalString("remoteAddr");
        }
        /**
         * Null for connected mode. Expected to be unsigned integer.
         * @return the protocol field value, empty when absent
         */
        public OptionalLong remotePort() {
            return optionalLong("remotePort");
        }
        /**
         * Sets the data field.
         * @param data field value
         * @return this model
         */
        public DirectUDPMessage data(String data) {
            set("data", data);
            return this;
        }
        /**
         * Null for connected mode.
         * @param remoteAddr field value
         * @return this model
         */
        public DirectUDPMessage remoteAddr(String remoteAddr) {
            set("remoteAddr", remoteAddr);
            return this;
        }
        /**
         * Null for connected mode. Expected to be unsigned integer.
         * @param remotePort field value
         * @return this model
         */
        public DirectUDPMessage remotePort(long remotePort) {
            set("remotePort", remotePort);
            return this;
        }
    }
    /**
     * Wire values for LocalNetworkAccessRequestPolicy.
     * <p><b>Experimental:</b> this part of CDP may change without notice.
     */
    public enum LocalNetworkAccessRequestPolicy implements CdpValue<String> {
        ALLOW("Allow"),
        BLOCKFROMINSECURETOMOREPRIVATE("BlockFromInsecureToMorePrivate"),
        WARNFROMINSECURETOMOREPRIVATE("WarnFromInsecureToMorePrivate"),
        PERMISSIONBLOCK("PermissionBlock"),
        PERMISSIONWARN("PermissionWarn");
        public final String value;
        LocalNetworkAccessRequestPolicy(String value) { this.value = value; }
        @Override public String value() { return value; }
        public static LocalNetworkAccessRequestPolicy of(String value) {
            for (LocalNetworkAccessRequestPolicy constant : values()) {
                if (constant.value.equals(value)) return constant;
            }
            throw new IllegalArgumentException("Unknown LocalNetworkAccessRequestPolicy value: " + value);
        }
    }
    /**
     * Wire values for IPAddressSpace.
     * <p><b>Experimental:</b> this part of CDP may change without notice.
     */
    public enum IPAddressSpace implements CdpValue<String> {
        LOOPBACK("Loopback"),
        LOCAL("Local"),
        PUBLIC("Public"),
        UNKNOWN("Unknown");
        public final String value;
        IPAddressSpace(String value) { this.value = value; }
        @Override public String value() { return value; }
        public static IPAddressSpace of(String value) {
            for (IPAddressSpace constant : values()) {
                if (constant.value.equals(value)) return constant;
            }
            throw new IllegalArgumentException("Unknown IPAddressSpace value: " + value);
        }
    }
    /**
     * <p><b>Experimental:</b> this part of CDP may change without notice.
     */
    public static final class ConnectTiming extends CdpObject {
        public ConnectTiming() {}
        private ConnectTiming(Map<String, Object> values) { super(values); }
        public static ConnectTiming fromMap(Map<String, Object> values) {
            return new ConnectTiming(values);
        }
        /**
         * Timing&#x27;s requestTime is a baseline in seconds, while the other numbers are ticks in milliseconds relatively to this requestTime. Matches ResourceTiming&#x27;s requestTime for the same request (but not for redirected requests).
         * @return the protocol field value
         */
        public double requestTime() {
            return requireDouble("requestTime");
        }
        /**
         * Timing&#x27;s requestTime is a baseline in seconds, while the other numbers are ticks in milliseconds relatively to this requestTime. Matches ResourceTiming&#x27;s requestTime for the same request (but not for redirected requests).
         * @param requestTime field value
         * @return this model
         */
        public ConnectTiming requestTime(double requestTime) {
            set("requestTime", requestTime);
            return this;
        }
    }
    /**
     * <p><b>Experimental:</b> this part of CDP may change without notice.
     */
    public static final class ClientSecurityState extends CdpObject {
        public ClientSecurityState() {}
        private ClientSecurityState(Map<String, Object> values) { super(values); }
        public static ClientSecurityState fromMap(Map<String, Object> values) {
            return new ClientSecurityState(values);
        }
        /**
         * Returns the initiatorIsSecureContext field.
         * @return the protocol field value
         */
        public boolean initiatorIsSecureContext() {
            return requireBoolean("initiatorIsSecureContext");
        }
        /**
         * Returns the initiatorIPAddressSpace field.
         * @return the protocol field value
         */
        public Network.IPAddressSpace initiatorIPAddressSpace() {
            return require(INITIATOR_IPADDRESS_SPACE);
        }
        /**
         * Returns the localNetworkAccessRequestPolicy field.
         * @return the protocol field value
         */
        public Network.LocalNetworkAccessRequestPolicy localNetworkAccessRequestPolicy() {
            return require(LOCAL_NETWORK_ACCESS_REQUEST_POLICY);
        }
        /**
         * Sets the initiatorIsSecureContext field.
         * @param initiatorIsSecureContext field value
         * @return this model
         */
        public ClientSecurityState initiatorIsSecureContext(boolean initiatorIsSecureContext) {
            set("initiatorIsSecureContext", initiatorIsSecureContext);
            return this;
        }
        /**
         * Sets the initiatorIPAddressSpace field.
         * @param initiatorIPAddressSpace field value
         * @return this model
         */
        public ClientSecurityState initiatorIPAddressSpace(Network.IPAddressSpace initiatorIPAddressSpace) {
            set("initiatorIPAddressSpace", initiatorIPAddressSpace);
            return this;
        }
        /**
         * Sets the localNetworkAccessRequestPolicy field.
         * @param localNetworkAccessRequestPolicy field value
         * @return this model
         */
        public ClientSecurityState localNetworkAccessRequestPolicy(Network.LocalNetworkAccessRequestPolicy localNetworkAccessRequestPolicy) {
            set("localNetworkAccessRequestPolicy", localNetworkAccessRequestPolicy);
            return this;
        }
    }
    /**
     * Identifies the script on the stack that caused a resource or element to be labeled as an ad. For resources, this indicates the context that triggered the fetch. For elements, this indicates the context that caused the element to be appended to the DOM.
     * <p><b>Experimental:</b> this part of CDP may change without notice.
     */
    public static final class AdScriptIdentifier extends CdpObject {
        public AdScriptIdentifier() {}
        private AdScriptIdentifier(Map<String, Object> values) { super(values); }
        public static AdScriptIdentifier fromMap(Map<String, Object> values) {
            return new AdScriptIdentifier(values);
        }
        /**
         * The script&#x27;s V8 identifier.
         * @return the protocol field value
         */
        public Runtime.ScriptId scriptId() {
            return require(SCRIPT_ID);
        }
        /**
         * V8&#x27;s debugging ID for the v8::Context.
         * @return the protocol field value
         */
        public Runtime.UniqueDebuggerId debuggerId() {
            return require(DEBUGGER_ID);
        }
        /**
         * The script&#x27;s url (or generated name based on id if inline script).
         * @return the protocol field value
         */
        public String name() {
            return requireString("name");
        }
        /**
         * The script&#x27;s V8 identifier.
         * @param scriptId field value
         * @return this model
         */
        public AdScriptIdentifier scriptId(Runtime.ScriptId scriptId) {
            set("scriptId", scriptId);
            return this;
        }
        /**
         * V8&#x27;s debugging ID for the v8::Context.
         * @param debuggerId field value
         * @return this model
         */
        public AdScriptIdentifier debuggerId(Runtime.UniqueDebuggerId debuggerId) {
            set("debuggerId", debuggerId);
            return this;
        }
        /**
         * The script&#x27;s url (or generated name based on id if inline script).
         * @param name field value
         * @return this model
         */
        public AdScriptIdentifier name(String name) {
            set("name", name);
            return this;
        }
    }
    /**
     * Encapsulates the script ancestry and the root script filter list rule that caused the resource or element to be labeled as an ad.
     * <p><b>Experimental:</b> this part of CDP may change without notice.
     */
    public static final class AdAncestry extends CdpObject {
        public AdAncestry() {}
        private AdAncestry(Map<String, Object> values) { super(values); }
        public static AdAncestry fromMap(Map<String, Object> values) {
            return new AdAncestry(values);
        }
        /**
         * A chain of {@code AdScriptIdentifier}s representing the ancestry of an ad script that led to the creation of a resource or element. The chain is ordered from the script itself (lowest level) up to its root ancestor that was flagged by a filter list.
         * @return the protocol field value
         */
        public java.util.List<Network.AdScriptIdentifier> ancestryChain() {
            return require(ANCESTRY_CHAIN);
        }
        /**
         * The filter list rule that caused the root (last) script in {@code ancestryChain} to be tagged as an ad.
         * @return the protocol field value, empty when absent
         */
        public Optional<String> rootScriptFilterlistRule() {
            return optionalString("rootScriptFilterlistRule");
        }
        /**
         * A chain of {@code AdScriptIdentifier}s representing the ancestry of an ad script that led to the creation of a resource or element. The chain is ordered from the script itself (lowest level) up to its root ancestor that was flagged by a filter list.
         * @param ancestryChain field value
         * @return this model
         */
        public AdAncestry ancestryChain(java.util.List<Network.AdScriptIdentifier> ancestryChain) {
            set("ancestryChain", ancestryChain);
            return this;
        }
        /**
         * The filter list rule that caused the root (last) script in {@code ancestryChain} to be tagged as an ad.
         * @param rootScriptFilterlistRule field value
         * @return this model
         */
        public AdAncestry rootScriptFilterlistRule(String rootScriptFilterlistRule) {
            set("rootScriptFilterlistRule", rootScriptFilterlistRule);
            return this;
        }
    }
    /**
     * Represents the provenance of an ad resource or element. Only one of {@code filterlistRule} or {@code adScriptAncestry} can be set. If {@code filterlistRule} is provided, the resource URL directly matches a filter list rule. If {@code adScriptAncestry} is provided, an ad script initiated the resource fetch or appended the element to the DOM. If neither is provided, the entity is known to be an ad, but provenance tracking information is unavailable.
     * <p><b>Experimental:</b> this part of CDP may change without notice.
     */
    public static final class AdProvenance extends CdpObject {
        public AdProvenance() {}
        private AdProvenance(Map<String, Object> values) { super(values); }
        public static AdProvenance fromMap(Map<String, Object> values) {
            return new AdProvenance(values);
        }
        /**
         * The filterlist rule that matched, if any.
         * @return the protocol field value, empty when absent
         */
        public Optional<String> filterlistRule() {
            return optionalString("filterlistRule");
        }
        /**
         * The script ancestry that created the ad, if any.
         * @return the protocol field value, empty when absent
         */
        public Optional<Network.AdAncestry> adScriptAncestry() {
            return optional(AD_SCRIPT_ANCESTRY);
        }
        /**
         * The filterlist rule that matched, if any.
         * @param filterlistRule field value
         * @return this model
         */
        public AdProvenance filterlistRule(String filterlistRule) {
            set("filterlistRule", filterlistRule);
            return this;
        }
        /**
         * The script ancestry that created the ad, if any.
         * @param adScriptAncestry field value
         * @return this model
         */
        public AdProvenance adScriptAncestry(Network.AdAncestry adScriptAncestry) {
            set("adScriptAncestry", adScriptAncestry);
            return this;
        }
    }
    /**
     * Wire values for CrossOriginOpenerPolicyValue.
     * <p><b>Experimental:</b> this part of CDP may change without notice.
     */
    public enum CrossOriginOpenerPolicyValue implements CdpValue<String> {
        SAMEORIGIN("SameOrigin"),
        SAMEORIGINALLOWPOPUPS("SameOriginAllowPopups"),
        RESTRICTPROPERTIES("RestrictProperties"),
        UNSAFENONE("UnsafeNone"),
        SAMEORIGINPLUSCOEP("SameOriginPlusCoep"),
        RESTRICTPROPERTIESPLUSCOEP("RestrictPropertiesPlusCoep"),
        NOOPENERALLOWPOPUPS("NoopenerAllowPopups");
        public final String value;
        CrossOriginOpenerPolicyValue(String value) { this.value = value; }
        @Override public String value() { return value; }
        public static CrossOriginOpenerPolicyValue of(String value) {
            for (CrossOriginOpenerPolicyValue constant : values()) {
                if (constant.value.equals(value)) return constant;
            }
            throw new IllegalArgumentException("Unknown CrossOriginOpenerPolicyValue value: " + value);
        }
    }
    /**
     * <p><b>Experimental:</b> this part of CDP may change without notice.
     */
    public static final class CrossOriginOpenerPolicyStatus extends CdpObject {
        public CrossOriginOpenerPolicyStatus() {}
        private CrossOriginOpenerPolicyStatus(Map<String, Object> values) { super(values); }
        public static CrossOriginOpenerPolicyStatus fromMap(Map<String, Object> values) {
            return new CrossOriginOpenerPolicyStatus(values);
        }
        /**
         * Returns the value field.
         * @return the protocol field value
         */
        public Network.CrossOriginOpenerPolicyValue value() {
            return require(VALUE);
        }
        /**
         * Returns the reportOnlyValue field.
         * @return the protocol field value
         */
        public Network.CrossOriginOpenerPolicyValue reportOnlyValue() {
            return require(REPORT_ONLY_VALUE);
        }
        /**
         * Returns the reportingEndpoint field.
         * @return the protocol field value, empty when absent
         */
        public Optional<String> reportingEndpoint() {
            return optionalString("reportingEndpoint");
        }
        /**
         * Returns the reportOnlyReportingEndpoint field.
         * @return the protocol field value, empty when absent
         */
        public Optional<String> reportOnlyReportingEndpoint() {
            return optionalString("reportOnlyReportingEndpoint");
        }
        /**
         * Sets the value field.
         * @param value field value
         * @return this model
         */
        public CrossOriginOpenerPolicyStatus value(Network.CrossOriginOpenerPolicyValue value) {
            set("value", value);
            return this;
        }
        /**
         * Sets the reportOnlyValue field.
         * @param reportOnlyValue field value
         * @return this model
         */
        public CrossOriginOpenerPolicyStatus reportOnlyValue(Network.CrossOriginOpenerPolicyValue reportOnlyValue) {
            set("reportOnlyValue", reportOnlyValue);
            return this;
        }
        /**
         * Sets the reportingEndpoint field.
         * @param reportingEndpoint field value
         * @return this model
         */
        public CrossOriginOpenerPolicyStatus reportingEndpoint(String reportingEndpoint) {
            set("reportingEndpoint", reportingEndpoint);
            return this;
        }
        /**
         * Sets the reportOnlyReportingEndpoint field.
         * @param reportOnlyReportingEndpoint field value
         * @return this model
         */
        public CrossOriginOpenerPolicyStatus reportOnlyReportingEndpoint(String reportOnlyReportingEndpoint) {
            set("reportOnlyReportingEndpoint", reportOnlyReportingEndpoint);
            return this;
        }
    }
    /**
     * Wire values for CrossOriginEmbedderPolicyValue.
     * <p><b>Experimental:</b> this part of CDP may change without notice.
     */
    public enum CrossOriginEmbedderPolicyValue implements CdpValue<String> {
        NONE("None"),
        CREDENTIALLESS("Credentialless"),
        REQUIRECORP("RequireCorp");
        public final String value;
        CrossOriginEmbedderPolicyValue(String value) { this.value = value; }
        @Override public String value() { return value; }
        public static CrossOriginEmbedderPolicyValue of(String value) {
            for (CrossOriginEmbedderPolicyValue constant : values()) {
                if (constant.value.equals(value)) return constant;
            }
            throw new IllegalArgumentException("Unknown CrossOriginEmbedderPolicyValue value: " + value);
        }
    }
    /**
     * <p><b>Experimental:</b> this part of CDP may change without notice.
     */
    public static final class CrossOriginEmbedderPolicyStatus extends CdpObject {
        public CrossOriginEmbedderPolicyStatus() {}
        private CrossOriginEmbedderPolicyStatus(Map<String, Object> values) { super(values); }
        public static CrossOriginEmbedderPolicyStatus fromMap(Map<String, Object> values) {
            return new CrossOriginEmbedderPolicyStatus(values);
        }
        /**
         * Returns the value field.
         * @return the protocol field value
         */
        public Network.CrossOriginEmbedderPolicyValue value() {
            return require(VALUE_2);
        }
        /**
         * Returns the reportOnlyValue field.
         * @return the protocol field value
         */
        public Network.CrossOriginEmbedderPolicyValue reportOnlyValue() {
            return require(REPORT_ONLY_VALUE_2);
        }
        /**
         * Returns the reportingEndpoint field.
         * @return the protocol field value, empty when absent
         */
        public Optional<String> reportingEndpoint() {
            return optionalString("reportingEndpoint");
        }
        /**
         * Returns the reportOnlyReportingEndpoint field.
         * @return the protocol field value, empty when absent
         */
        public Optional<String> reportOnlyReportingEndpoint() {
            return optionalString("reportOnlyReportingEndpoint");
        }
        /**
         * Sets the value field.
         * @param value field value
         * @return this model
         */
        public CrossOriginEmbedderPolicyStatus value(Network.CrossOriginEmbedderPolicyValue value) {
            set("value", value);
            return this;
        }
        /**
         * Sets the reportOnlyValue field.
         * @param reportOnlyValue field value
         * @return this model
         */
        public CrossOriginEmbedderPolicyStatus reportOnlyValue(Network.CrossOriginEmbedderPolicyValue reportOnlyValue) {
            set("reportOnlyValue", reportOnlyValue);
            return this;
        }
        /**
         * Sets the reportingEndpoint field.
         * @param reportingEndpoint field value
         * @return this model
         */
        public CrossOriginEmbedderPolicyStatus reportingEndpoint(String reportingEndpoint) {
            set("reportingEndpoint", reportingEndpoint);
            return this;
        }
        /**
         * Sets the reportOnlyReportingEndpoint field.
         * @param reportOnlyReportingEndpoint field value
         * @return this model
         */
        public CrossOriginEmbedderPolicyStatus reportOnlyReportingEndpoint(String reportOnlyReportingEndpoint) {
            set("reportOnlyReportingEndpoint", reportOnlyReportingEndpoint);
            return this;
        }
    }
    /**
     * Wire values for ContentSecurityPolicySource.
     * <p><b>Experimental:</b> this part of CDP may change without notice.
     */
    public enum ContentSecurityPolicySource implements CdpValue<String> {
        HTTP("HTTP"),
        META("Meta");
        public final String value;
        ContentSecurityPolicySource(String value) { this.value = value; }
        @Override public String value() { return value; }
        public static ContentSecurityPolicySource of(String value) {
            for (ContentSecurityPolicySource constant : values()) {
                if (constant.value.equals(value)) return constant;
            }
            throw new IllegalArgumentException("Unknown ContentSecurityPolicySource value: " + value);
        }
    }
    /**
     * <p><b>Experimental:</b> this part of CDP may change without notice.
     */
    public static final class ContentSecurityPolicyStatus extends CdpObject {
        public ContentSecurityPolicyStatus() {}
        private ContentSecurityPolicyStatus(Map<String, Object> values) { super(values); }
        public static ContentSecurityPolicyStatus fromMap(Map<String, Object> values) {
            return new ContentSecurityPolicyStatus(values);
        }
        /**
         * Returns the effectiveDirectives field.
         * @return the protocol field value
         */
        public String effectiveDirectives() {
            return requireString("effectiveDirectives");
        }
        /**
         * Returns the isEnforced field.
         * @return the protocol field value
         */
        public boolean isEnforced() {
            return requireBoolean("isEnforced");
        }
        /**
         * Returns the source field.
         * @return the protocol field value
         */
        public Network.ContentSecurityPolicySource source() {
            return require(SOURCE_2);
        }
        /**
         * Sets the effectiveDirectives field.
         * @param effectiveDirectives field value
         * @return this model
         */
        public ContentSecurityPolicyStatus effectiveDirectives(String effectiveDirectives) {
            set("effectiveDirectives", effectiveDirectives);
            return this;
        }
        /**
         * Sets the isEnforced field.
         * @param isEnforced field value
         * @return this model
         */
        public ContentSecurityPolicyStatus isEnforced(boolean isEnforced) {
            set("isEnforced", isEnforced);
            return this;
        }
        /**
         * Sets the source field.
         * @param source field value
         * @return this model
         */
        public ContentSecurityPolicyStatus source(Network.ContentSecurityPolicySource source) {
            set("source", source);
            return this;
        }
    }
    /**
     * <p><b>Experimental:</b> this part of CDP may change without notice.
     */
    public static final class SecurityIsolationStatus extends CdpObject {
        public SecurityIsolationStatus() {}
        private SecurityIsolationStatus(Map<String, Object> values) { super(values); }
        public static SecurityIsolationStatus fromMap(Map<String, Object> values) {
            return new SecurityIsolationStatus(values);
        }
        /**
         * Returns the coop field.
         * @return the protocol field value, empty when absent
         */
        public Optional<Network.CrossOriginOpenerPolicyStatus> coop() {
            return optional(COOP);
        }
        /**
         * Returns the coep field.
         * @return the protocol field value, empty when absent
         */
        public Optional<Network.CrossOriginEmbedderPolicyStatus> coep() {
            return optional(COEP);
        }
        /**
         * Returns the csp field.
         * @return the protocol field value, empty when absent
         */
        public Optional<java.util.List<Network.ContentSecurityPolicyStatus>> csp() {
            return optional(CSP);
        }
        /**
         * Sets the coop field.
         * @param coop field value
         * @return this model
         */
        public SecurityIsolationStatus coop(Network.CrossOriginOpenerPolicyStatus coop) {
            set("coop", coop);
            return this;
        }
        /**
         * Sets the coep field.
         * @param coep field value
         * @return this model
         */
        public SecurityIsolationStatus coep(Network.CrossOriginEmbedderPolicyStatus coep) {
            set("coep", coep);
            return this;
        }
        /**
         * Sets the csp field.
         * @param csp field value
         * @return this model
         */
        public SecurityIsolationStatus csp(java.util.List<Network.ContentSecurityPolicyStatus> csp) {
            set("csp", csp);
            return this;
        }
    }
    /**
     * The status of a Reporting API report.
     * <p><b>Experimental:</b> this part of CDP may change without notice.
     */
    public enum ReportStatus implements CdpValue<String> {
        QUEUED("Queued"),
        PENDING("Pending"),
        MARKEDFORREMOVAL("MarkedForRemoval"),
        SUCCESS("Success");
        public final String value;
        ReportStatus(String value) { this.value = value; }
        @Override public String value() { return value; }
        public static ReportStatus of(String value) {
            for (ReportStatus constant : values()) {
                if (constant.value.equals(value)) return constant;
            }
            throw new IllegalArgumentException("Unknown ReportStatus value: " + value);
        }
    }
    /**
     * Tagged String wire value for ReportId.
     * <p><b>Experimental:</b> this part of CDP may change without notice.
     */
    public static final class ReportId implements CdpValue<String> {
        public final String value;
        public ReportId(String value) { this.value = java.util.Objects.requireNonNull(value); }
        @Override public String value() { return value; }
        @Override public boolean equals(@org.jspecify.annotations.Nullable Object other) {
            if (this == other) return true;
            if (!(other instanceof ReportId)) return false;
            return value.equals(((ReportId) other).value);
        }
        @Override public int hashCode() { return value.hashCode(); }
        @Override public String toString() { return "ReportId(" + value + ")"; }
    }
    /**
     * An object representing a report generated by the Reporting API.
     * <p><b>Experimental:</b> this part of CDP may change without notice.
     */
    public static final class ReportingApiReport extends CdpObject {
        public ReportingApiReport() {}
        private ReportingApiReport(Map<String, Object> values) { super(values); }
        public static ReportingApiReport fromMap(Map<String, Object> values) {
            return new ReportingApiReport(values);
        }
        /**
         * Returns the id field.
         * @return the protocol field value
         */
        public Network.ReportId id() {
            return require(ID);
        }
        /**
         * The URL of the document that triggered the report.
         * @return the protocol field value
         */
        public String initiatorUrl() {
            return requireString("initiatorUrl");
        }
        /**
         * The name of the endpoint group that should be used to deliver the report.
         * @return the protocol field value
         */
        public String destination() {
            return requireString("destination");
        }
        /**
         * The type of the report (specifies the set of data that is contained in the report body).
         * @return the protocol field value
         */
        public String type() {
            return requireString("type");
        }
        /**
         * When the report was generated.
         * @return the protocol field value
         */
        public Network.TimeSinceEpoch timestamp() {
            return require(TIMESTAMP);
        }
        /**
         * How many uploads deep the related request was.
         * @return the protocol field value
         */
        public long depth() {
            return requireLong("depth");
        }
        /**
         * The number of delivery attempts made so far, not including an active attempt.
         * @return the protocol field value
         */
        public long completedAttempts() {
            return requireLong("completedAttempts");
        }
        /**
         * Returns the body field.
         * @return the protocol field value
         */
        public java.util.Map<String, Object> body() {
            return requireObject("body");
        }
        /**
         * Returns the status field.
         * @return the protocol field value
         */
        public Network.ReportStatus status() {
            return require(STATUS);
        }
        /**
         * Sets the id field.
         * @param id field value
         * @return this model
         */
        public ReportingApiReport id(Network.ReportId id) {
            set("id", id);
            return this;
        }
        /**
         * The URL of the document that triggered the report.
         * @param initiatorUrl field value
         * @return this model
         */
        public ReportingApiReport initiatorUrl(String initiatorUrl) {
            set("initiatorUrl", initiatorUrl);
            return this;
        }
        /**
         * The name of the endpoint group that should be used to deliver the report.
         * @param destination field value
         * @return this model
         */
        public ReportingApiReport destination(String destination) {
            set("destination", destination);
            return this;
        }
        /**
         * The type of the report (specifies the set of data that is contained in the report body).
         * @param type field value
         * @return this model
         */
        public ReportingApiReport type(String type) {
            set("type", type);
            return this;
        }
        /**
         * When the report was generated.
         * @param timestamp field value
         * @return this model
         */
        public ReportingApiReport timestamp(Network.TimeSinceEpoch timestamp) {
            set("timestamp", timestamp);
            return this;
        }
        /**
         * How many uploads deep the related request was.
         * @param depth field value
         * @return this model
         */
        public ReportingApiReport depth(long depth) {
            set("depth", depth);
            return this;
        }
        /**
         * The number of delivery attempts made so far, not including an active attempt.
         * @param completedAttempts field value
         * @return this model
         */
        public ReportingApiReport completedAttempts(long completedAttempts) {
            set("completedAttempts", completedAttempts);
            return this;
        }
        /**
         * Sets the body field.
         * @param body field value
         * @return this model
         */
        public ReportingApiReport body(java.util.Map<String, Object> body) {
            set("body", body);
            return this;
        }
        /**
         * Sets the status field.
         * @param status field value
         * @return this model
         */
        public ReportingApiReport status(Network.ReportStatus status) {
            set("status", status);
            return this;
        }
    }
    /**
     * <p><b>Experimental:</b> this part of CDP may change without notice.
     */
    public static final class ReportingApiEndpoint extends CdpObject {
        public ReportingApiEndpoint() {}
        private ReportingApiEndpoint(Map<String, Object> values) { super(values); }
        public static ReportingApiEndpoint fromMap(Map<String, Object> values) {
            return new ReportingApiEndpoint(values);
        }
        /**
         * The URL of the endpoint to which reports may be delivered.
         * @return the protocol field value
         */
        public String url() {
            return requireString("url");
        }
        /**
         * Name of the endpoint group.
         * @return the protocol field value
         */
        public String groupName() {
            return requireString("groupName");
        }
        /**
         * The URL of the endpoint to which reports may be delivered.
         * @param url field value
         * @return this model
         */
        public ReportingApiEndpoint url(String url) {
            set("url", url);
            return this;
        }
        /**
         * Name of the endpoint group.
         * @param groupName field value
         * @return this model
         */
        public ReportingApiEndpoint groupName(String groupName) {
            set("groupName", groupName);
            return this;
        }
    }
    /**
     * Unique identifier for a device bound session.
     * <p><b>Experimental:</b> this part of CDP may change without notice.
     */
    public static final class DeviceBoundSessionKey extends CdpObject {
        public DeviceBoundSessionKey() {}
        private DeviceBoundSessionKey(Map<String, Object> values) { super(values); }
        public static DeviceBoundSessionKey fromMap(Map<String, Object> values) {
            return new DeviceBoundSessionKey(values);
        }
        /**
         * The site the session is set up for.
         * @return the protocol field value
         */
        public String site() {
            return requireString("site");
        }
        /**
         * The id of the session.
         * @return the protocol field value
         */
        public String id() {
            return requireString("id");
        }
        /**
         * The site the session is set up for.
         * @param site field value
         * @return this model
         */
        public DeviceBoundSessionKey site(String site) {
            set("site", site);
            return this;
        }
        /**
         * The id of the session.
         * @param id field value
         * @return this model
         */
        public DeviceBoundSessionKey id(String id) {
            set("id", id);
            return this;
        }
    }
    /**
     * How a device bound session was used during a request.
     * <p><b>Experimental:</b> this part of CDP may change without notice.
     */
    public static final class DeviceBoundSessionWithUsage extends CdpObject {
        public DeviceBoundSessionWithUsage() {}
        private DeviceBoundSessionWithUsage(Map<String, Object> values) { super(values); }
        public static DeviceBoundSessionWithUsage fromMap(Map<String, Object> values) {
            return new DeviceBoundSessionWithUsage(values);
        }
        /**
         * How the session was used (or not used).
         */
        public enum UsageValues implements CdpValue<String> {
            NOTINSCOPE("NotInScope"),
            INSCOPEREFRESHNOTYETNEEDED("InScopeRefreshNotYetNeeded"),
            INSCOPEREFRESHNOTALLOWED("InScopeRefreshNotAllowed"),
            PROACTIVEREFRESHNOTPOSSIBLE("ProactiveRefreshNotPossible"),
            PROACTIVEREFRESHATTEMPTED("ProactiveRefreshAttempted"),
            DEFERRED("Deferred");
            public final String value;
            UsageValues(String value) { this.value = value; }
            @Override public String value() { return value; }
            public static UsageValues of(String value) {
                for (UsageValues constant : values()) {
                    if (constant.value.equals(value)) return constant;
                }
                throw new IllegalArgumentException("Unknown UsageValues value: " + value);
            }
        }
        /**
         * The key for the session.
         * @return the protocol field value
         */
        public Network.DeviceBoundSessionKey sessionKey() {
            return require(SESSION_KEY);
        }
        /**
         * How the session was used (or not used).
         * @return the protocol field value
         */
        public DeviceBoundSessionWithUsage.UsageValues usage() {
            return require(USAGE);
        }
        /**
         * The key for the session.
         * @param sessionKey field value
         * @return this model
         */
        public DeviceBoundSessionWithUsage sessionKey(Network.DeviceBoundSessionKey sessionKey) {
            set("sessionKey", sessionKey);
            return this;
        }
        /**
         * How the session was used (or not used).
         * @param usage field value
         * @return this model
         */
        public DeviceBoundSessionWithUsage usage(DeviceBoundSessionWithUsage.UsageValues usage) {
            set("usage", usage);
            return this;
        }
    }
    /**
     * A device bound session&#x27;s cookie craving.
     * <p><b>Experimental:</b> this part of CDP may change without notice.
     */
    public static final class DeviceBoundSessionCookieCraving extends CdpObject {
        public DeviceBoundSessionCookieCraving() {}
        private DeviceBoundSessionCookieCraving(Map<String, Object> values) { super(values); }
        public static DeviceBoundSessionCookieCraving fromMap(Map<String, Object> values) {
            return new DeviceBoundSessionCookieCraving(values);
        }
        /**
         * The name of the craving.
         * @return the protocol field value
         */
        public String name() {
            return requireString("name");
        }
        /**
         * The domain of the craving.
         * @return the protocol field value
         */
        public String domain() {
            return requireString("domain");
        }
        /**
         * The path of the craving.
         * @return the protocol field value
         */
        public String path() {
            return requireString("path");
        }
        /**
         * The {@code Secure} attribute of the craving attributes.
         * @return the protocol field value
         */
        public boolean secure() {
            return requireBoolean("secure");
        }
        /**
         * The {@code HttpOnly} attribute of the craving attributes.
         * @return the protocol field value
         */
        public boolean httpOnly() {
            return requireBoolean("httpOnly");
        }
        /**
         * The {@code SameSite} attribute of the craving attributes.
         * @return the protocol field value, empty when absent
         */
        public Optional<Network.CookieSameSite> sameSite() {
            return optional(SAME_SITE);
        }
        /**
         * The name of the craving.
         * @param name field value
         * @return this model
         */
        public DeviceBoundSessionCookieCraving name(String name) {
            set("name", name);
            return this;
        }
        /**
         * The domain of the craving.
         * @param domain field value
         * @return this model
         */
        public DeviceBoundSessionCookieCraving domain(String domain) {
            set("domain", domain);
            return this;
        }
        /**
         * The path of the craving.
         * @param path field value
         * @return this model
         */
        public DeviceBoundSessionCookieCraving path(String path) {
            set("path", path);
            return this;
        }
        /**
         * The {@code Secure} attribute of the craving attributes.
         * @param secure field value
         * @return this model
         */
        public DeviceBoundSessionCookieCraving secure(boolean secure) {
            set("secure", secure);
            return this;
        }
        /**
         * The {@code HttpOnly} attribute of the craving attributes.
         * @param httpOnly field value
         * @return this model
         */
        public DeviceBoundSessionCookieCraving httpOnly(boolean httpOnly) {
            set("httpOnly", httpOnly);
            return this;
        }
        /**
         * The {@code SameSite} attribute of the craving attributes.
         * @param sameSite field value
         * @return this model
         */
        public DeviceBoundSessionCookieCraving sameSite(Network.CookieSameSite sameSite) {
            set("sameSite", sameSite);
            return this;
        }
    }
    /**
     * A device bound session&#x27;s inclusion URL rule.
     * <p><b>Experimental:</b> this part of CDP may change without notice.
     */
    public static final class DeviceBoundSessionUrlRule extends CdpObject {
        public DeviceBoundSessionUrlRule() {}
        private DeviceBoundSessionUrlRule(Map<String, Object> values) { super(values); }
        public static DeviceBoundSessionUrlRule fromMap(Map<String, Object> values) {
            return new DeviceBoundSessionUrlRule(values);
        }
        /**
         * See comments on {@code net::device_bound_sessions::SessionInclusionRules::UrlRule::rule_type}.
         */
        public enum RuleTypeValues implements CdpValue<String> {
            EXCLUDE("Exclude"),
            INCLUDE("Include");
            public final String value;
            RuleTypeValues(String value) { this.value = value; }
            @Override public String value() { return value; }
            public static RuleTypeValues of(String value) {
                for (RuleTypeValues constant : values()) {
                    if (constant.value.equals(value)) return constant;
                }
                throw new IllegalArgumentException("Unknown RuleTypeValues value: " + value);
            }
        }
        /**
         * See comments on {@code net::device_bound_sessions::SessionInclusionRules::UrlRule::rule_type}.
         * @return the protocol field value
         */
        public DeviceBoundSessionUrlRule.RuleTypeValues ruleType() {
            return require(RULE_TYPE);
        }
        /**
         * See comments on {@code net::device_bound_sessions::SessionInclusionRules::UrlRule::host_pattern}.
         * @return the protocol field value
         */
        public String hostPattern() {
            return requireString("hostPattern");
        }
        /**
         * See comments on {@code net::device_bound_sessions::SessionInclusionRules::UrlRule::path_prefix}.
         * @return the protocol field value
         */
        public String pathPrefix() {
            return requireString("pathPrefix");
        }
        /**
         * See comments on {@code net::device_bound_sessions::SessionInclusionRules::UrlRule::rule_type}.
         * @param ruleType field value
         * @return this model
         */
        public DeviceBoundSessionUrlRule ruleType(DeviceBoundSessionUrlRule.RuleTypeValues ruleType) {
            set("ruleType", ruleType);
            return this;
        }
        /**
         * See comments on {@code net::device_bound_sessions::SessionInclusionRules::UrlRule::host_pattern}.
         * @param hostPattern field value
         * @return this model
         */
        public DeviceBoundSessionUrlRule hostPattern(String hostPattern) {
            set("hostPattern", hostPattern);
            return this;
        }
        /**
         * See comments on {@code net::device_bound_sessions::SessionInclusionRules::UrlRule::path_prefix}.
         * @param pathPrefix field value
         * @return this model
         */
        public DeviceBoundSessionUrlRule pathPrefix(String pathPrefix) {
            set("pathPrefix", pathPrefix);
            return this;
        }
    }
    /**
     * A device bound session&#x27;s inclusion rules.
     * <p><b>Experimental:</b> this part of CDP may change without notice.
     */
    public static final class DeviceBoundSessionInclusionRules extends CdpObject {
        public DeviceBoundSessionInclusionRules() {}
        private DeviceBoundSessionInclusionRules(Map<String, Object> values) { super(values); }
        public static DeviceBoundSessionInclusionRules fromMap(Map<String, Object> values) {
            return new DeviceBoundSessionInclusionRules(values);
        }
        /**
         * See comments on {@code net::device_bound_sessions::SessionInclusionRules::origin_}.
         * @return the protocol field value
         */
        public String origin() {
            return requireString("origin");
        }
        /**
         * Whether the whole site is included. See comments on {@code net::device_bound_sessions::SessionInclusionRules::include_site_} for more details; this boolean is true if that value is populated.
         * @return the protocol field value
         */
        public boolean includeSite() {
            return requireBoolean("includeSite");
        }
        /**
         * See comments on {@code net::device_bound_sessions::SessionInclusionRules::url_rules_}.
         * @return the protocol field value
         */
        public java.util.List<Network.DeviceBoundSessionUrlRule> urlRules() {
            return require(URL_RULES);
        }
        /**
         * See comments on {@code net::device_bound_sessions::SessionInclusionRules::origin_}.
         * @param origin field value
         * @return this model
         */
        public DeviceBoundSessionInclusionRules origin(String origin) {
            set("origin", origin);
            return this;
        }
        /**
         * Whether the whole site is included. See comments on {@code net::device_bound_sessions::SessionInclusionRules::include_site_} for more details; this boolean is true if that value is populated.
         * @param includeSite field value
         * @return this model
         */
        public DeviceBoundSessionInclusionRules includeSite(boolean includeSite) {
            set("includeSite", includeSite);
            return this;
        }
        /**
         * See comments on {@code net::device_bound_sessions::SessionInclusionRules::url_rules_}.
         * @param urlRules field value
         * @return this model
         */
        public DeviceBoundSessionInclusionRules urlRules(java.util.List<Network.DeviceBoundSessionUrlRule> urlRules) {
            set("urlRules", urlRules);
            return this;
        }
    }
    /**
     * A device bound session.
     * <p><b>Experimental:</b> this part of CDP may change without notice.
     */
    public static final class DeviceBoundSession extends CdpObject {
        public DeviceBoundSession() {}
        private DeviceBoundSession(Map<String, Object> values) { super(values); }
        public static DeviceBoundSession fromMap(Map<String, Object> values) {
            return new DeviceBoundSession(values);
        }
        /**
         * The site and session ID of the session.
         * @return the protocol field value
         */
        public Network.DeviceBoundSessionKey key() {
            return require(KEY);
        }
        /**
         * See comments on {@code net::device_bound_sessions::Session::refresh_url_}.
         * @return the protocol field value
         */
        public String refreshUrl() {
            return requireString("refreshUrl");
        }
        /**
         * See comments on {@code net::device_bound_sessions::Session::inclusion_rules_}.
         * @return the protocol field value
         */
        public Network.DeviceBoundSessionInclusionRules inclusionRules() {
            return require(INCLUSION_RULES);
        }
        /**
         * See comments on {@code net::device_bound_sessions::Session::cookie_cravings_}.
         * @return the protocol field value
         */
        public java.util.List<Network.DeviceBoundSessionCookieCraving> cookieCravings() {
            return require(COOKIE_CRAVINGS);
        }
        /**
         * See comments on {@code net::device_bound_sessions::Session::expiry_date_}.
         * @return the protocol field value
         */
        public Network.TimeSinceEpoch expiryDate() {
            return require(EXPIRY_DATE);
        }
        /**
         * See comments on {@code net::device_bound_sessions::Session::cached_challenge__}.
         * @return the protocol field value, empty when absent
         */
        public Optional<String> cachedChallenge() {
            return optionalString("cachedChallenge");
        }
        /**
         * See comments on {@code net::device_bound_sessions::Session::allowed_refresh_initiators_}.
         * @return the protocol field value
         */
        public java.util.List<String> allowedRefreshInitiators() {
            return require(ALLOWED_REFRESH_INITIATORS);
        }
        /**
         * The site and session ID of the session.
         * @param key field value
         * @return this model
         */
        public DeviceBoundSession key(Network.DeviceBoundSessionKey key) {
            set("key", key);
            return this;
        }
        /**
         * See comments on {@code net::device_bound_sessions::Session::refresh_url_}.
         * @param refreshUrl field value
         * @return this model
         */
        public DeviceBoundSession refreshUrl(String refreshUrl) {
            set("refreshUrl", refreshUrl);
            return this;
        }
        /**
         * See comments on {@code net::device_bound_sessions::Session::inclusion_rules_}.
         * @param inclusionRules field value
         * @return this model
         */
        public DeviceBoundSession inclusionRules(Network.DeviceBoundSessionInclusionRules inclusionRules) {
            set("inclusionRules", inclusionRules);
            return this;
        }
        /**
         * See comments on {@code net::device_bound_sessions::Session::cookie_cravings_}.
         * @param cookieCravings field value
         * @return this model
         */
        public DeviceBoundSession cookieCravings(java.util.List<Network.DeviceBoundSessionCookieCraving> cookieCravings) {
            set("cookieCravings", cookieCravings);
            return this;
        }
        /**
         * See comments on {@code net::device_bound_sessions::Session::expiry_date_}.
         * @param expiryDate field value
         * @return this model
         */
        public DeviceBoundSession expiryDate(Network.TimeSinceEpoch expiryDate) {
            set("expiryDate", expiryDate);
            return this;
        }
        /**
         * See comments on {@code net::device_bound_sessions::Session::cached_challenge__}.
         * @param cachedChallenge field value
         * @return this model
         */
        public DeviceBoundSession cachedChallenge(String cachedChallenge) {
            set("cachedChallenge", cachedChallenge);
            return this;
        }
        /**
         * See comments on {@code net::device_bound_sessions::Session::allowed_refresh_initiators_}.
         * @param allowedRefreshInitiators field value
         * @return this model
         */
        public DeviceBoundSession allowedRefreshInitiators(java.util.List<String> allowedRefreshInitiators) {
            set("allowedRefreshInitiators", allowedRefreshInitiators);
            return this;
        }
    }
    /**
     * A unique identifier for a device bound session event.
     * <p><b>Experimental:</b> this part of CDP may change without notice.
     */
    public static final class DeviceBoundSessionEventId implements CdpValue<String> {
        public final String value;
        public DeviceBoundSessionEventId(String value) { this.value = java.util.Objects.requireNonNull(value); }
        @Override public String value() { return value; }
        @Override public boolean equals(@org.jspecify.annotations.Nullable Object other) {
            if (this == other) return true;
            if (!(other instanceof DeviceBoundSessionEventId)) return false;
            return value.equals(((DeviceBoundSessionEventId) other).value);
        }
        @Override public int hashCode() { return value.hashCode(); }
        @Override public String toString() { return "DeviceBoundSessionEventId(" + value + ")"; }
    }
    /**
     * A fetch result for a device bound session creation or refresh.
     * <p><b>Experimental:</b> this part of CDP may change without notice.
     */
    public enum DeviceBoundSessionFetchResult implements CdpValue<String> {
        SUCCESS("Success"),
        KEYERROR("KeyError"),
        SIGNINGERROR("SigningError"),
        TRANSIENTSIGNINGERROR("TransientSigningError"),
        SERVERREQUESTEDTERMINATION("ServerRequestedTermination"),
        INVALIDSESSIONID("InvalidSessionId"),
        INVALIDCHALLENGE("InvalidChallenge"),
        TOOMANYCHALLENGES("TooManyChallenges"),
        INVALIDFETCHERURL("InvalidFetcherUrl"),
        INVALIDREFRESHURL("InvalidRefreshUrl"),
        TRANSIENTHTTPERROR("TransientHttpError"),
        SCOPEORIGINSAMESITEMISMATCH("ScopeOriginSameSiteMismatch"),
        REFRESHURLSAMESITEMISMATCH("RefreshUrlSameSiteMismatch"),
        MISMATCHEDSESSIONID("MismatchedSessionId"),
        MISSINGSCOPE("MissingScope"),
        NOCREDENTIALS("NoCredentials"),
        SUBDOMAINREGISTRATIONWELLKNOWNUNAVAILABLE("SubdomainRegistrationWellKnownUnavailable"),
        SUBDOMAINREGISTRATIONUNAUTHORIZED("SubdomainRegistrationUnauthorized"),
        SUBDOMAINREGISTRATIONWELLKNOWNMALFORMED("SubdomainRegistrationWellKnownMalformed"),
        SESSIONPROVIDERWELLKNOWNUNAVAILABLE("SessionProviderWellKnownUnavailable"),
        RELYINGPARTYWELLKNOWNUNAVAILABLE("RelyingPartyWellKnownUnavailable"),
        FEDERATEDKEYTHUMBPRINTMISMATCH("FederatedKeyThumbprintMismatch"),
        INVALIDFEDERATEDSESSIONURL("InvalidFederatedSessionUrl"),
        INVALIDFEDERATEDKEY("InvalidFederatedKey"),
        TOOMANYRELYINGORIGINLABELS("TooManyRelyingOriginLabels"),
        BOUNDCOOKIESETFORBIDDEN("BoundCookieSetForbidden"),
        NETERROR("NetError"),
        PROXYERROR("ProxyError"),
        EMPTYSESSIONCONFIG("EmptySessionConfig"),
        INVALIDCREDENTIALSCONFIG("InvalidCredentialsConfig"),
        INVALIDCREDENTIALSTYPE("InvalidCredentialsType"),
        INVALIDCREDENTIALSEMPTYNAME("InvalidCredentialsEmptyName"),
        INVALIDCREDENTIALSCOOKIE("InvalidCredentialsCookie"),
        PERSISTENTHTTPERROR("PersistentHttpError"),
        REGISTRATIONATTEMPTEDCHALLENGE("RegistrationAttemptedChallenge"),
        INVALIDSCOPEORIGIN("InvalidScopeOrigin"),
        SCOPEORIGINCONTAINSPATH("ScopeOriginContainsPath"),
        REFRESHINITIATORNOTSTRING("RefreshInitiatorNotString"),
        REFRESHINITIATORINVALIDHOSTPATTERN("RefreshInitiatorInvalidHostPattern"),
        INVALIDSCOPESPECIFICATION("InvalidScopeSpecification"),
        MISSINGSCOPESPECIFICATIONTYPE("MissingScopeSpecificationType"),
        EMPTYSCOPESPECIFICATIONDOMAIN("EmptyScopeSpecificationDomain"),
        EMPTYSCOPESPECIFICATIONPATH("EmptyScopeSpecificationPath"),
        INVALIDSCOPESPECIFICATIONTYPE("InvalidScopeSpecificationType"),
        INVALIDSCOPEINCLUDESITE("InvalidScopeIncludeSite"),
        MISSINGSCOPEINCLUDESITE("MissingScopeIncludeSite"),
        FEDERATEDNOTAUTHORIZEDBYPROVIDER("FederatedNotAuthorizedByProvider"),
        FEDERATEDNOTAUTHORIZEDBYRELYINGPARTY("FederatedNotAuthorizedByRelyingParty"),
        SESSIONPROVIDERWELLKNOWNMALFORMED("SessionProviderWellKnownMalformed"),
        SESSIONPROVIDERWELLKNOWNHASPROVIDERORIGIN("SessionProviderWellKnownHasProviderOrigin"),
        RELYINGPARTYWELLKNOWNMALFORMED("RelyingPartyWellKnownMalformed"),
        RELYINGPARTYWELLKNOWNHASRELYINGORIGINS("RelyingPartyWellKnownHasRelyingOrigins"),
        INVALIDFEDERATEDSESSIONPROVIDERSESSIONMISSING("InvalidFederatedSessionProviderSessionMissing"),
        INVALIDFEDERATEDSESSIONWRONGPROVIDERORIGIN("InvalidFederatedSessionWrongProviderOrigin"),
        INVALIDCREDENTIALSCOOKIECREATIONTIME("InvalidCredentialsCookieCreationTime"),
        INVALIDCREDENTIALSCOOKIENAME("InvalidCredentialsCookieName"),
        INVALIDCREDENTIALSCOOKIEPARSING("InvalidCredentialsCookieParsing"),
        INVALIDCREDENTIALSCOOKIEUNPERMITTEDATTRIBUTE("InvalidCredentialsCookieUnpermittedAttribute"),
        INVALIDCREDENTIALSCOOKIEINVALIDDOMAIN("InvalidCredentialsCookieInvalidDomain"),
        INVALIDCREDENTIALSCOOKIEPREFIX("InvalidCredentialsCookiePrefix"),
        INVALIDSCOPERULEPATH("InvalidScopeRulePath"),
        INVALIDSCOPERULEHOSTPATTERN("InvalidScopeRuleHostPattern"),
        SCOPERULEORIGINSCOPEDHOSTPATTERNMISMATCH("ScopeRuleOriginScopedHostPatternMismatch"),
        SCOPERULESITESCOPEDHOSTPATTERNMISMATCH("ScopeRuleSiteScopedHostPatternMismatch"),
        SIGNINGQUOTAEXCEEDED("SigningQuotaExceeded"),
        INVALIDCONFIGJSON("InvalidConfigJson"),
        INVALIDFEDERATEDSESSIONPROVIDERFAILEDTORESTOREKEY("InvalidFederatedSessionProviderFailedToRestoreKey"),
        FAILEDTOUNWRAPKEY("FailedToUnwrapKey"),
        SESSIONDELETEDDURINGREFRESH("SessionDeletedDuringRefresh"),
        CROSSORIGINREGISTRATIONSITENOTINCLUDED("CrossOriginRegistrationSiteNotIncluded");
        public final String value;
        DeviceBoundSessionFetchResult(String value) { this.value = value; }
        @Override public String value() { return value; }
        public static DeviceBoundSessionFetchResult of(String value) {
            for (DeviceBoundSessionFetchResult constant : values()) {
                if (constant.value.equals(value)) return constant;
            }
            throw new IllegalArgumentException("Unknown DeviceBoundSessionFetchResult value: " + value);
        }
    }
    /**
     * Details about a failed device bound session network request.
     * <p><b>Experimental:</b> this part of CDP may change without notice.
     */
    public static final class DeviceBoundSessionFailedRequest extends CdpObject {
        public DeviceBoundSessionFailedRequest() {}
        private DeviceBoundSessionFailedRequest(Map<String, Object> values) { super(values); }
        public static DeviceBoundSessionFailedRequest fromMap(Map<String, Object> values) {
            return new DeviceBoundSessionFailedRequest(values);
        }
        /**
         * The failed request URL.
         * @return the protocol field value
         */
        public String requestUrl() {
            return requireString("requestUrl");
        }
        /**
         * The net error of the response if it was not OK.
         * @return the protocol field value, empty when absent
         */
        public Optional<String> netError() {
            return optionalString("netError");
        }
        /**
         * The response code if the net error was OK and the response code was not 200.
         * @return the protocol field value, empty when absent
         */
        public OptionalLong responseError() {
            return optionalLong("responseError");
        }
        /**
         * The body of the response if the net error was OK, the response code was not 200, and the response body was not empty.
         * @return the protocol field value, empty when absent
         */
        public Optional<String> responseErrorBody() {
            return optionalString("responseErrorBody");
        }
        /**
         * The failed request URL.
         * @param requestUrl field value
         * @return this model
         */
        public DeviceBoundSessionFailedRequest requestUrl(String requestUrl) {
            set("requestUrl", requestUrl);
            return this;
        }
        /**
         * The net error of the response if it was not OK.
         * @param netError field value
         * @return this model
         */
        public DeviceBoundSessionFailedRequest netError(String netError) {
            set("netError", netError);
            return this;
        }
        /**
         * The response code if the net error was OK and the response code was not 200.
         * @param responseError field value
         * @return this model
         */
        public DeviceBoundSessionFailedRequest responseError(long responseError) {
            set("responseError", responseError);
            return this;
        }
        /**
         * The body of the response if the net error was OK, the response code was not 200, and the response body was not empty.
         * @param responseErrorBody field value
         * @return this model
         */
        public DeviceBoundSessionFailedRequest responseErrorBody(String responseErrorBody) {
            set("responseErrorBody", responseErrorBody);
            return this;
        }
    }
    /**
     * Session event details specific to creation.
     * <p><b>Experimental:</b> this part of CDP may change without notice.
     */
    public static final class CreationEventDetails extends CdpObject {
        public CreationEventDetails() {}
        private CreationEventDetails(Map<String, Object> values) { super(values); }
        public static CreationEventDetails fromMap(Map<String, Object> values) {
            return new CreationEventDetails(values);
        }
        /**
         * The result of the fetch attempt.
         * @return the protocol field value
         */
        public Network.DeviceBoundSessionFetchResult fetchResult() {
            return require(FETCH_RESULT);
        }
        /**
         * The session if there was a newly created session. This is populated for all successful creation events.
         * @return the protocol field value, empty when absent
         */
        public Optional<Network.DeviceBoundSession> newSession() {
            return optional(NEW_SESSION);
        }
        /**
         * Details about a failed device bound session network request if there was one.
         * @return the protocol field value, empty when absent
         */
        public Optional<Network.DeviceBoundSessionFailedRequest> failedRequest() {
            return optional(FAILED_REQUEST);
        }
        /**
         * The result of the fetch attempt.
         * @param fetchResult field value
         * @return this model
         */
        public CreationEventDetails fetchResult(Network.DeviceBoundSessionFetchResult fetchResult) {
            set("fetchResult", fetchResult);
            return this;
        }
        /**
         * The session if there was a newly created session. This is populated for all successful creation events.
         * @param newSession field value
         * @return this model
         */
        public CreationEventDetails newSession(Network.DeviceBoundSession newSession) {
            set("newSession", newSession);
            return this;
        }
        /**
         * Details about a failed device bound session network request if there was one.
         * @param failedRequest field value
         * @return this model
         */
        public CreationEventDetails failedRequest(Network.DeviceBoundSessionFailedRequest failedRequest) {
            set("failedRequest", failedRequest);
            return this;
        }
    }
    /**
     * Session event details specific to refresh.
     * <p><b>Experimental:</b> this part of CDP may change without notice.
     */
    public static final class RefreshEventDetails extends CdpObject {
        public RefreshEventDetails() {}
        private RefreshEventDetails(Map<String, Object> values) { super(values); }
        public static RefreshEventDetails fromMap(Map<String, Object> values) {
            return new RefreshEventDetails(values);
        }
        /**
         * The result of a refresh.
         */
        public enum RefreshResultValues implements CdpValue<String> {
            REFRESHED("Refreshed"),
            INITIALIZEDSERVICE("InitializedService"),
            UNREACHABLE("Unreachable"),
            SERVERERROR("ServerError"),
            REFRESHQUOTAEXCEEDED("RefreshQuotaExceeded"),
            FATALERROR("FatalError"),
            SIGNINGQUOTAEXCEEDED("SigningQuotaExceeded"),
            REFRESHEDASWAITER("RefreshedAsWaiter"),
            TRANSIENTSIGNINGERROR("TransientSigningError");
            public final String value;
            RefreshResultValues(String value) { this.value = value; }
            @Override public String value() { return value; }
            public static RefreshResultValues of(String value) {
                for (RefreshResultValues constant : values()) {
                    if (constant.value.equals(value)) return constant;
                }
                throw new IllegalArgumentException("Unknown RefreshResultValues value: " + value);
            }
        }
        /**
         * The result of a refresh.
         * @return the protocol field value
         */
        public RefreshEventDetails.RefreshResultValues refreshResult() {
            return require(REFRESH_RESULT);
        }
        /**
         * If there was a fetch attempt, the result of that.
         * @return the protocol field value, empty when absent
         */
        public Optional<Network.DeviceBoundSessionFetchResult> fetchResult() {
            return optional(FETCH_RESULT);
        }
        /**
         * The session display if there was a newly created session. This is populated for any refresh event that modifies the session config.
         * @return the protocol field value, empty when absent
         */
        public Optional<Network.DeviceBoundSession> newSession() {
            return optional(NEW_SESSION);
        }
        /**
         * See comments on {@code net::device_bound_sessions::RefreshEventResult::was_fully_proactive_refresh}.
         * @return the protocol field value
         */
        public boolean wasFullyProactiveRefresh() {
            return requireBoolean("wasFullyProactiveRefresh");
        }
        /**
         * Details about a failed device bound session network request if there was one.
         * @return the protocol field value, empty when absent
         */
        public Optional<Network.DeviceBoundSessionFailedRequest> failedRequest() {
            return optional(FAILED_REQUEST);
        }
        /**
         * The result of a refresh.
         * @param refreshResult field value
         * @return this model
         */
        public RefreshEventDetails refreshResult(RefreshEventDetails.RefreshResultValues refreshResult) {
            set("refreshResult", refreshResult);
            return this;
        }
        /**
         * If there was a fetch attempt, the result of that.
         * @param fetchResult field value
         * @return this model
         */
        public RefreshEventDetails fetchResult(Network.DeviceBoundSessionFetchResult fetchResult) {
            set("fetchResult", fetchResult);
            return this;
        }
        /**
         * The session display if there was a newly created session. This is populated for any refresh event that modifies the session config.
         * @param newSession field value
         * @return this model
         */
        public RefreshEventDetails newSession(Network.DeviceBoundSession newSession) {
            set("newSession", newSession);
            return this;
        }
        /**
         * See comments on {@code net::device_bound_sessions::RefreshEventResult::was_fully_proactive_refresh}.
         * @param wasFullyProactiveRefresh field value
         * @return this model
         */
        public RefreshEventDetails wasFullyProactiveRefresh(boolean wasFullyProactiveRefresh) {
            set("wasFullyProactiveRefresh", wasFullyProactiveRefresh);
            return this;
        }
        /**
         * Details about a failed device bound session network request if there was one.
         * @param failedRequest field value
         * @return this model
         */
        public RefreshEventDetails failedRequest(Network.DeviceBoundSessionFailedRequest failedRequest) {
            set("failedRequest", failedRequest);
            return this;
        }
    }
    /**
     * Session event details specific to termination.
     * <p><b>Experimental:</b> this part of CDP may change without notice.
     */
    public static final class TerminationEventDetails extends CdpObject {
        public TerminationEventDetails() {}
        private TerminationEventDetails(Map<String, Object> values) { super(values); }
        public static TerminationEventDetails fromMap(Map<String, Object> values) {
            return new TerminationEventDetails(values);
        }
        /**
         * The reason for a session being deleted.
         */
        public enum DeletionReasonValues implements CdpValue<String> {
            EXPIRED("Expired"),
            FAILEDTORESTOREKEY("FailedToRestoreKey"),
            FAILEDTOUNWRAPKEY("FailedToUnwrapKey"),
            STORAGEPARTITIONCLEARED("StoragePartitionCleared"),
            CLEARBROWSINGDATA("ClearBrowsingData"),
            SERVERREQUESTED("ServerRequested"),
            INVALIDSESSIONPARAMS("InvalidSessionParams"),
            REFRESHFATALERROR("RefreshFatalError"),
            DEVTOOLS("DevTools");
            public final String value;
            DeletionReasonValues(String value) { this.value = value; }
            @Override public String value() { return value; }
            public static DeletionReasonValues of(String value) {
                for (DeletionReasonValues constant : values()) {
                    if (constant.value.equals(value)) return constant;
                }
                throw new IllegalArgumentException("Unknown DeletionReasonValues value: " + value);
            }
        }
        /**
         * The reason for a session being deleted.
         * @return the protocol field value
         */
        public TerminationEventDetails.DeletionReasonValues deletionReason() {
            return require(DELETION_REASON);
        }
        /**
         * The reason for a session being deleted.
         * @param deletionReason field value
         * @return this model
         */
        public TerminationEventDetails deletionReason(TerminationEventDetails.DeletionReasonValues deletionReason) {
            set("deletionReason", deletionReason);
            return this;
        }
    }
    /**
     * Session event details specific to challenges.
     * <p><b>Experimental:</b> this part of CDP may change without notice.
     */
    public static final class ChallengeEventDetails extends CdpObject {
        public ChallengeEventDetails() {}
        private ChallengeEventDetails(Map<String, Object> values) { super(values); }
        public static ChallengeEventDetails fromMap(Map<String, Object> values) {
            return new ChallengeEventDetails(values);
        }
        /**
         * The result of a challenge.
         */
        public enum ChallengeResultValues implements CdpValue<String> {
            SUCCESS("Success"),
            NOSESSIONID("NoSessionId"),
            NOSESSIONMATCH("NoSessionMatch"),
            CANTSETBOUNDCOOKIE("CantSetBoundCookie");
            public final String value;
            ChallengeResultValues(String value) { this.value = value; }
            @Override public String value() { return value; }
            public static ChallengeResultValues of(String value) {
                for (ChallengeResultValues constant : values()) {
                    if (constant.value.equals(value)) return constant;
                }
                throw new IllegalArgumentException("Unknown ChallengeResultValues value: " + value);
            }
        }
        /**
         * The result of a challenge.
         * @return the protocol field value
         */
        public ChallengeEventDetails.ChallengeResultValues challengeResult() {
            return require(CHALLENGE_RESULT);
        }
        /**
         * The challenge set.
         * @return the protocol field value
         */
        public String challenge() {
            return requireString("challenge");
        }
        /**
         * The result of a challenge.
         * @param challengeResult field value
         * @return this model
         */
        public ChallengeEventDetails challengeResult(ChallengeEventDetails.ChallengeResultValues challengeResult) {
            set("challengeResult", challengeResult);
            return this;
        }
        /**
         * The challenge set.
         * @param challenge field value
         * @return this model
         */
        public ChallengeEventDetails challenge(String challenge) {
            set("challenge", challenge);
            return this;
        }
    }
    /**
     * An object providing the result of a network resource load.
     * <p><b>Experimental:</b> this part of CDP may change without notice.
     */
    public static final class LoadNetworkResourcePageResult extends CdpObject {
        public LoadNetworkResourcePageResult() {}
        private LoadNetworkResourcePageResult(Map<String, Object> values) { super(values); }
        public static LoadNetworkResourcePageResult fromMap(Map<String, Object> values) {
            return new LoadNetworkResourcePageResult(values);
        }
        /**
         * Returns the success field.
         * @return the protocol field value
         */
        public boolean success() {
            return requireBoolean("success");
        }
        /**
         * Optional values used for error reporting.
         * @return the protocol field value, empty when absent
         */
        public OptionalDouble netError() {
            return optionalDouble("netError");
        }
        /**
         * Returns the netErrorName field.
         * @return the protocol field value, empty when absent
         */
        public Optional<String> netErrorName() {
            return optionalString("netErrorName");
        }
        /**
         * Returns the httpStatusCode field.
         * @return the protocol field value, empty when absent
         */
        public OptionalDouble httpStatusCode() {
            return optionalDouble("httpStatusCode");
        }
        /**
         * If successful, one of the following two fields holds the result.
         * @return the protocol field value, empty when absent
         */
        public Optional<IO.StreamHandle> stream() {
            return optional(STREAM);
        }
        /**
         * Response headers.
         * @return the protocol field value, empty when absent
         */
        public Optional<java.util.Map<String, Object>> headers() {
            return optionalObject("headers");
        }
        /**
         * Sets the success field.
         * @param success field value
         * @return this model
         */
        public LoadNetworkResourcePageResult success(boolean success) {
            set("success", success);
            return this;
        }
        /**
         * Optional values used for error reporting.
         * @param netError field value
         * @return this model
         */
        public LoadNetworkResourcePageResult netError(double netError) {
            set("netError", netError);
            return this;
        }
        /**
         * Sets the netErrorName field.
         * @param netErrorName field value
         * @return this model
         */
        public LoadNetworkResourcePageResult netErrorName(String netErrorName) {
            set("netErrorName", netErrorName);
            return this;
        }
        /**
         * Sets the httpStatusCode field.
         * @param httpStatusCode field value
         * @return this model
         */
        public LoadNetworkResourcePageResult httpStatusCode(double httpStatusCode) {
            set("httpStatusCode", httpStatusCode);
            return this;
        }
        /**
         * If successful, one of the following two fields holds the result.
         * @param stream field value
         * @return this model
         */
        public LoadNetworkResourcePageResult stream(IO.StreamHandle stream) {
            set("stream", stream);
            return this;
        }
        /**
         * Response headers.
         * @param headers field value
         * @return this model
         */
        public LoadNetworkResourcePageResult headers(java.util.Map<String, Object> headers) {
            set("headers", headers);
            return this;
        }
    }
    /**
     * An options object that may be extended later to better support CORS, CORB and streaming.
     * <p><b>Experimental:</b> this part of CDP may change without notice.
     */
    public static final class LoadNetworkResourceOptions extends CdpObject {
        public LoadNetworkResourceOptions() {}
        private LoadNetworkResourceOptions(Map<String, Object> values) { super(values); }
        public static LoadNetworkResourceOptions fromMap(Map<String, Object> values) {
            return new LoadNetworkResourceOptions(values);
        }
        /**
         * Returns the disableCache field.
         * @return the protocol field value
         */
        public boolean disableCache() {
            return requireBoolean("disableCache");
        }
        /**
         * Returns the includeCredentials field.
         * @return the protocol field value
         */
        public boolean includeCredentials() {
            return requireBoolean("includeCredentials");
        }
        /**
         * Sets the disableCache field.
         * @param disableCache field value
         * @return this model
         */
        public LoadNetworkResourceOptions disableCache(boolean disableCache) {
            set("disableCache", disableCache);
            return this;
        }
        /**
         * Sets the includeCredentials field.
         * @param includeCredentials field value
         * @return this model
         */
        public LoadNetworkResourceOptions includeCredentials(boolean includeCredentials) {
            set("includeCredentials", includeCredentials);
            return this;
        }
    }
    /**
     * Sets a list of content encodings that will be accepted. Empty list means no encoding is accepted.
     * <p><b>Experimental:</b> this part of CDP may change without notice.
     */
    public static final class SetAcceptedEncodingsRequest extends CdpObject {
        public SetAcceptedEncodingsRequest() {}
        public static SetAcceptedEncodingsRequest fromMap(Map<String, Object> values) {
            final SetAcceptedEncodingsRequest instance_ = new SetAcceptedEncodingsRequest();
            if (values != null) instance_.values.putAll(values);
            return instance_;
        }
        /**
         * List of accepted content encodings.
         * @return the protocol field value
         */
        public java.util.List<Network.ContentEncoding> encodings() {
            return require(ENCODINGS);
        }
        /**
         * List of accepted content encodings.
         * @param encodings field value
         * @return this model
         */
        public SetAcceptedEncodingsRequest encodings(java.util.List<Network.ContentEncoding> encodings) {
            set("encodings", encodings);
            return this;
        }
    }
    /**
     * Response to Network.requestIntercepted which either modifies the request to continue with any modifications, or blocks it, or completes it with the provided response bytes. If a network fetch occurs as a result which encounters a redirect an additional Network.requestIntercepted event will be sent with the same InterceptionId. Deprecated, use Fetch.continueRequest, Fetch.fulfillRequest and Fetch.failRequest instead.
     * <p><b>Experimental:</b> this part of CDP may change without notice.
     * @deprecated Deprecated by the Chromium DevTools Protocol.
     */
    @Deprecated
    public static final class ContinueInterceptedRequestRequest extends CdpObject {
        public ContinueInterceptedRequestRequest() {}
        public static ContinueInterceptedRequestRequest fromMap(Map<String, Object> values) {
            final ContinueInterceptedRequestRequest instance_ = new ContinueInterceptedRequestRequest();
            if (values != null) instance_.values.putAll(values);
            return instance_;
        }
        /**
         * Returns the interceptionId field.
         * @return the protocol field value
         */
        public Network.InterceptionId interceptionId() {
            return require(INTERCEPTION_ID);
        }
        /**
         * If set this causes the request to fail with the given reason. Passing {@code Aborted} for requests marked with {@code isNavigationRequest} also cancels the navigation. Must not be set in response to an authChallenge.
         * @return the protocol field value, empty when absent
         */
        public Optional<Network.ErrorReason> errorReason() {
            return optional(ERROR_REASON);
        }
        /**
         * If set the requests completes using with the provided base64 encoded raw response, including HTTP status line and headers etc... Must not be set in response to an authChallenge. (Encoded as a base64 string when passed over JSON)
         * @return the protocol field value, empty when absent
         */
        public Optional<String> rawResponse() {
            return optionalString("rawResponse");
        }
        /**
         * If set the request url will be modified in a way that&#x27;s not observable by page. Must not be set in response to an authChallenge.
         * @return the protocol field value, empty when absent
         */
        public Optional<String> url() {
            return optionalString("url");
        }
        /**
         * If set this allows the request method to be overridden. Must not be set in response to an authChallenge.
         * @return the protocol field value, empty when absent
         */
        public Optional<String> method() {
            return optionalString("method");
        }
        /**
         * If set this allows postData to be set. Must not be set in response to an authChallenge.
         * @return the protocol field value, empty when absent
         */
        public Optional<String> postData() {
            return optionalString("postData");
        }
        /**
         * If set this allows the request headers to be changed. Must not be set in response to an authChallenge.
         * @return the protocol field value, empty when absent
         */
        public Optional<java.util.Map<String, Object>> headers() {
            return optionalObject("headers");
        }
        /**
         * Response to a requestIntercepted with an authChallenge. Must not be set otherwise.
         * @return the protocol field value, empty when absent
         */
        public Optional<Network.AuthChallengeResponse> authChallengeResponse() {
            return optional(AUTH_CHALLENGE_RESPONSE);
        }
        /**
         * Sets the interceptionId field.
         * @param interceptionId field value
         * @return this model
         */
        public ContinueInterceptedRequestRequest interceptionId(Network.InterceptionId interceptionId) {
            set("interceptionId", interceptionId);
            return this;
        }
        /**
         * If set this causes the request to fail with the given reason. Passing {@code Aborted} for requests marked with {@code isNavigationRequest} also cancels the navigation. Must not be set in response to an authChallenge.
         * @param errorReason field value
         * @return this model
         */
        public ContinueInterceptedRequestRequest errorReason(Network.ErrorReason errorReason) {
            set("errorReason", errorReason);
            return this;
        }
        /**
         * If set the requests completes using with the provided base64 encoded raw response, including HTTP status line and headers etc... Must not be set in response to an authChallenge. (Encoded as a base64 string when passed over JSON)
         * @param rawResponse field value
         * @return this model
         */
        public ContinueInterceptedRequestRequest rawResponse(String rawResponse) {
            set("rawResponse", rawResponse);
            return this;
        }
        /**
         * If set the request url will be modified in a way that&#x27;s not observable by page. Must not be set in response to an authChallenge.
         * @param url field value
         * @return this model
         */
        public ContinueInterceptedRequestRequest url(String url) {
            set("url", url);
            return this;
        }
        /**
         * If set this allows the request method to be overridden. Must not be set in response to an authChallenge.
         * @param method field value
         * @return this model
         */
        public ContinueInterceptedRequestRequest method(String method) {
            set("method", method);
            return this;
        }
        /**
         * If set this allows postData to be set. Must not be set in response to an authChallenge.
         * @param postData field value
         * @return this model
         */
        public ContinueInterceptedRequestRequest postData(String postData) {
            set("postData", postData);
            return this;
        }
        /**
         * If set this allows the request headers to be changed. Must not be set in response to an authChallenge.
         * @param headers field value
         * @return this model
         */
        public ContinueInterceptedRequestRequest headers(java.util.Map<String, Object> headers) {
            set("headers", headers);
            return this;
        }
        /**
         * Response to a requestIntercepted with an authChallenge. Must not be set otherwise.
         * @param authChallengeResponse field value
         * @return this model
         */
        public ContinueInterceptedRequestRequest authChallengeResponse(Network.AuthChallengeResponse authChallengeResponse) {
            set("authChallengeResponse", authChallengeResponse);
            return this;
        }
    }
    /**
     * Deletes browser cookies with matching name and url or domain/path/partitionKey pair.
     */
    public static final class DeleteCookiesRequest extends CdpObject {
        public DeleteCookiesRequest() {}
        public static DeleteCookiesRequest fromMap(Map<String, Object> values) {
            final DeleteCookiesRequest instance_ = new DeleteCookiesRequest();
            if (values != null) instance_.values.putAll(values);
            return instance_;
        }
        /**
         * Name of the cookies to remove.
         * @return the protocol field value
         */
        public String name() {
            return requireString("name");
        }
        /**
         * If specified, deletes all the cookies with the given name where domain and path match provided URL.
         * @return the protocol field value, empty when absent
         */
        public Optional<String> url() {
            return optionalString("url");
        }
        /**
         * If specified, deletes only cookies with the exact domain.
         * @return the protocol field value, empty when absent
         */
        public Optional<String> domain() {
            return optionalString("domain");
        }
        /**
         * If specified, deletes only cookies with the exact path.
         * @return the protocol field value, empty when absent
         */
        public Optional<String> path() {
            return optionalString("path");
        }
        /**
         * If specified, deletes only cookies with the the given name and partitionKey where all partition key attributes match the cookie partition key attribute.
         * <p><b>Experimental:</b> this part of CDP may change without notice.
         * @return the protocol field value, empty when absent
         */
        public Optional<Network.CookiePartitionKey> partitionKey() {
            return optional(PARTITION_KEY);
        }
        /**
         * Name of the cookies to remove.
         * @param name field value
         * @return this model
         */
        public DeleteCookiesRequest name(String name) {
            set("name", name);
            return this;
        }
        /**
         * If specified, deletes all the cookies with the given name where domain and path match provided URL.
         * @param url field value
         * @return this model
         */
        public DeleteCookiesRequest url(String url) {
            set("url", url);
            return this;
        }
        /**
         * If specified, deletes only cookies with the exact domain.
         * @param domain field value
         * @return this model
         */
        public DeleteCookiesRequest domain(String domain) {
            set("domain", domain);
            return this;
        }
        /**
         * If specified, deletes only cookies with the exact path.
         * @param path field value
         * @return this model
         */
        public DeleteCookiesRequest path(String path) {
            set("path", path);
            return this;
        }
        /**
         * If specified, deletes only cookies with the the given name and partitionKey where all partition key attributes match the cookie partition key attribute.
         * <p><b>Experimental:</b> this part of CDP may change without notice.
         * @param partitionKey field value
         * @return this model
         */
        public DeleteCookiesRequest partitionKey(Network.CookiePartitionKey partitionKey) {
            set("partitionKey", partitionKey);
            return this;
        }
    }
    /**
     * Activates emulation of network conditions. This command is deprecated in favor of the emulateNetworkConditionsByRule and overrideNetworkState commands, which can be used together to the same effect.
     * @deprecated Deprecated by the Chromium DevTools Protocol.
     */
    @Deprecated
    public static final class EmulateNetworkConditionsRequest extends CdpObject {
        public EmulateNetworkConditionsRequest() {}
        public static EmulateNetworkConditionsRequest fromMap(Map<String, Object> values) {
            final EmulateNetworkConditionsRequest instance_ = new EmulateNetworkConditionsRequest();
            if (values != null) instance_.values.putAll(values);
            return instance_;
        }
        /**
         * True to emulate internet disconnection.
         * @return the protocol field value
         */
        public boolean offline() {
            return requireBoolean("offline");
        }
        /**
         * Minimum latency from request sent to response headers received (ms).
         * @return the protocol field value
         */
        public double latency() {
            return requireDouble("latency");
        }
        /**
         * Maximal aggregated download throughput (bytes/sec). -1 disables download throttling.
         * @return the protocol field value
         */
        public double downloadThroughput() {
            return requireDouble("downloadThroughput");
        }
        /**
         * Maximal aggregated upload throughput (bytes/sec). -1 disables upload throttling.
         * @return the protocol field value
         */
        public double uploadThroughput() {
            return requireDouble("uploadThroughput");
        }
        /**
         * Connection type if known.
         * @return the protocol field value, empty when absent
         */
        public Optional<Network.ConnectionType> connectionType() {
            return optional(CONNECTION_TYPE);
        }
        /**
         * WebRTC packet loss (percent, 0-100). 0 disables packet loss emulation, 100 drops all the packets.
         * <p><b>Experimental:</b> this part of CDP may change without notice.
         * @return the protocol field value, empty when absent
         */
        public OptionalDouble packetLoss() {
            return optionalDouble("packetLoss");
        }
        /**
         * WebRTC packet queue length (packet). 0 removes any queue length limitations.
         * <p><b>Experimental:</b> this part of CDP may change without notice.
         * @return the protocol field value, empty when absent
         */
        public OptionalLong packetQueueLength() {
            return optionalLong("packetQueueLength");
        }
        /**
         * WebRTC packetReordering feature.
         * <p><b>Experimental:</b> this part of CDP may change without notice.
         * @return the protocol field value, empty when absent
         */
        public Optional<Boolean> packetReordering() {
            return optionalBoolean("packetReordering");
        }
        /**
         * True to emulate internet disconnection.
         * @param offline field value
         * @return this model
         */
        public EmulateNetworkConditionsRequest offline(boolean offline) {
            set("offline", offline);
            return this;
        }
        /**
         * Minimum latency from request sent to response headers received (ms).
         * @param latency field value
         * @return this model
         */
        public EmulateNetworkConditionsRequest latency(double latency) {
            set("latency", latency);
            return this;
        }
        /**
         * Maximal aggregated download throughput (bytes/sec). -1 disables download throttling.
         * @param downloadThroughput field value
         * @return this model
         */
        public EmulateNetworkConditionsRequest downloadThroughput(double downloadThroughput) {
            set("downloadThroughput", downloadThroughput);
            return this;
        }
        /**
         * Maximal aggregated upload throughput (bytes/sec). -1 disables upload throttling.
         * @param uploadThroughput field value
         * @return this model
         */
        public EmulateNetworkConditionsRequest uploadThroughput(double uploadThroughput) {
            set("uploadThroughput", uploadThroughput);
            return this;
        }
        /**
         * Connection type if known.
         * @param connectionType field value
         * @return this model
         */
        public EmulateNetworkConditionsRequest connectionType(Network.ConnectionType connectionType) {
            set("connectionType", connectionType);
            return this;
        }
        /**
         * WebRTC packet loss (percent, 0-100). 0 disables packet loss emulation, 100 drops all the packets.
         * <p><b>Experimental:</b> this part of CDP may change without notice.
         * @param packetLoss field value
         * @return this model
         */
        public EmulateNetworkConditionsRequest packetLoss(double packetLoss) {
            set("packetLoss", packetLoss);
            return this;
        }
        /**
         * WebRTC packet queue length (packet). 0 removes any queue length limitations.
         * <p><b>Experimental:</b> this part of CDP may change without notice.
         * @param packetQueueLength field value
         * @return this model
         */
        public EmulateNetworkConditionsRequest packetQueueLength(long packetQueueLength) {
            set("packetQueueLength", packetQueueLength);
            return this;
        }
        /**
         * WebRTC packetReordering feature.
         * <p><b>Experimental:</b> this part of CDP may change without notice.
         * @param packetReordering field value
         * @return this model
         */
        public EmulateNetworkConditionsRequest packetReordering(boolean packetReordering) {
            set("packetReordering", packetReordering);
            return this;
        }
    }
    /**
     * Activates emulation of network conditions for individual requests using URL match patterns. Unlike the deprecated Network.emulateNetworkConditions this method does not affect {@code navigator} state. Use Network.overrideNetworkState to explicitly modify {@code navigator} behavior.
     * <p><b>Experimental:</b> this part of CDP may change without notice.
     */
    public static final class EmulateNetworkConditionsByRuleRequest extends CdpObject {
        public EmulateNetworkConditionsByRuleRequest() {}
        public static EmulateNetworkConditionsByRuleRequest fromMap(Map<String, Object> values) {
            final EmulateNetworkConditionsByRuleRequest instance_ = new EmulateNetworkConditionsByRuleRequest();
            if (values != null) instance_.values.putAll(values);
            return instance_;
        }
        /**
         * True to emulate internet disconnection. Deprecated, use the offline property in matchedNetworkConditions or emulateOfflineServiceWorker instead.
         * @return the protocol field value, empty when absent
         * @deprecated Deprecated by the Chromium DevTools Protocol.
         */
        @Deprecated
        public Optional<Boolean> offline() {
            return optionalBoolean("offline");
        }
        /**
         * True to emulate offline service worker.
         * @return the protocol field value, empty when absent
         */
        public Optional<Boolean> emulateOfflineServiceWorker() {
            return optionalBoolean("emulateOfflineServiceWorker");
        }
        /**
         * Configure conditions for matching requests. If multiple entries match a request, the first entry wins. Global conditions can be configured by leaving the urlPattern for the conditions empty. These global conditions are also applied for throttling of p2p connections.
         * @return the protocol field value
         */
        public java.util.List<Network.NetworkConditions> matchedNetworkConditions() {
            return require(MATCHED_NETWORK_CONDITIONS);
        }
        /**
         * True to emulate internet disconnection. Deprecated, use the offline property in matchedNetworkConditions or emulateOfflineServiceWorker instead.
         * @param offline field value
         * @return this model
         * @deprecated Deprecated by the Chromium DevTools Protocol.
         */
        @Deprecated
        public EmulateNetworkConditionsByRuleRequest offline(boolean offline) {
            set("offline", offline);
            return this;
        }
        /**
         * True to emulate offline service worker.
         * @param emulateOfflineServiceWorker field value
         * @return this model
         */
        public EmulateNetworkConditionsByRuleRequest emulateOfflineServiceWorker(boolean emulateOfflineServiceWorker) {
            set("emulateOfflineServiceWorker", emulateOfflineServiceWorker);
            return this;
        }
        /**
         * Configure conditions for matching requests. If multiple entries match a request, the first entry wins. Global conditions can be configured by leaving the urlPattern for the conditions empty. These global conditions are also applied for throttling of p2p connections.
         * @param matchedNetworkConditions field value
         * @return this model
         */
        public EmulateNetworkConditionsByRuleRequest matchedNetworkConditions(java.util.List<Network.NetworkConditions> matchedNetworkConditions) {
            set("matchedNetworkConditions", matchedNetworkConditions);
            return this;
        }
    }
    /**
     * Override the state of navigator.onLine and navigator.connection.
     * <p><b>Experimental:</b> this part of CDP may change without notice.
     */
    public static final class OverrideNetworkStateRequest extends CdpObject {
        public OverrideNetworkStateRequest() {}
        public static OverrideNetworkStateRequest fromMap(Map<String, Object> values) {
            final OverrideNetworkStateRequest instance_ = new OverrideNetworkStateRequest();
            if (values != null) instance_.values.putAll(values);
            return instance_;
        }
        /**
         * True to emulate internet disconnection.
         * @return the protocol field value
         */
        public boolean offline() {
            return requireBoolean("offline");
        }
        /**
         * Minimum latency from request sent to response headers received (ms).
         * @return the protocol field value
         */
        public double latency() {
            return requireDouble("latency");
        }
        /**
         * Maximal aggregated download throughput (bytes/sec). -1 disables download throttling.
         * @return the protocol field value
         */
        public double downloadThroughput() {
            return requireDouble("downloadThroughput");
        }
        /**
         * Maximal aggregated upload throughput (bytes/sec). -1 disables upload throttling.
         * @return the protocol field value
         */
        public double uploadThroughput() {
            return requireDouble("uploadThroughput");
        }
        /**
         * Connection type if known.
         * @return the protocol field value, empty when absent
         */
        public Optional<Network.ConnectionType> connectionType() {
            return optional(CONNECTION_TYPE);
        }
        /**
         * True to emulate internet disconnection.
         * @param offline field value
         * @return this model
         */
        public OverrideNetworkStateRequest offline(boolean offline) {
            set("offline", offline);
            return this;
        }
        /**
         * Minimum latency from request sent to response headers received (ms).
         * @param latency field value
         * @return this model
         */
        public OverrideNetworkStateRequest latency(double latency) {
            set("latency", latency);
            return this;
        }
        /**
         * Maximal aggregated download throughput (bytes/sec). -1 disables download throttling.
         * @param downloadThroughput field value
         * @return this model
         */
        public OverrideNetworkStateRequest downloadThroughput(double downloadThroughput) {
            set("downloadThroughput", downloadThroughput);
            return this;
        }
        /**
         * Maximal aggregated upload throughput (bytes/sec). -1 disables upload throttling.
         * @param uploadThroughput field value
         * @return this model
         */
        public OverrideNetworkStateRequest uploadThroughput(double uploadThroughput) {
            set("uploadThroughput", uploadThroughput);
            return this;
        }
        /**
         * Connection type if known.
         * @param connectionType field value
         * @return this model
         */
        public OverrideNetworkStateRequest connectionType(Network.ConnectionType connectionType) {
            set("connectionType", connectionType);
            return this;
        }
    }
    /**
     * Enables network tracking, network events will now be delivered to the client.
     */
    public static final class EnableRequest extends CdpObject {
        public EnableRequest() {}
        public static EnableRequest fromMap(Map<String, Object> values) {
            final EnableRequest instance_ = new EnableRequest();
            if (values != null) instance_.values.putAll(values);
            return instance_;
        }
        /**
         * Buffer size in bytes to use when preserving network payloads (XHRs, etc). This is the maximum number of bytes that will be collected by this DevTools session.
         * <p><b>Experimental:</b> this part of CDP may change without notice.
         * @return the protocol field value, empty when absent
         */
        public OptionalLong maxTotalBufferSize() {
            return optionalLong("maxTotalBufferSize");
        }
        /**
         * Per-resource buffer size in bytes to use when preserving network payloads (XHRs, etc).
         * <p><b>Experimental:</b> this part of CDP may change without notice.
         * @return the protocol field value, empty when absent
         */
        public OptionalLong maxResourceBufferSize() {
            return optionalLong("maxResourceBufferSize");
        }
        /**
         * Longest post body size (in bytes) that would be included in requestWillBeSent notification
         * @return the protocol field value, empty when absent
         */
        public OptionalLong maxPostDataSize() {
            return optionalLong("maxPostDataSize");
        }
        /**
         * Whether DirectSocket chunk send/receive events should be reported.
         * <p><b>Experimental:</b> this part of CDP may change without notice.
         * @return the protocol field value, empty when absent
         */
        public Optional<Boolean> reportDirectSocketTraffic() {
            return optionalBoolean("reportDirectSocketTraffic");
        }
        /**
         * Enable storing response bodies outside of renderer, so that these survive a cross-process navigation. Requires maxTotalBufferSize to be set. Currently defaults to false. This field is being deprecated in favor of the dedicated configureDurableMessages command, due to the possibility of deadlocks when awaiting Network.enable before issuing Runtime.runIfWaitingForDebugger.
         * <p><b>Experimental:</b> this part of CDP may change without notice.
         * @return the protocol field value, empty when absent
         */
        public Optional<Boolean> enableDurableMessages() {
            return optionalBoolean("enableDurableMessages");
        }
        /**
         * Buffer size in bytes to use when preserving network payloads (XHRs, etc). This is the maximum number of bytes that will be collected by this DevTools session.
         * <p><b>Experimental:</b> this part of CDP may change without notice.
         * @param maxTotalBufferSize field value
         * @return this model
         */
        public EnableRequest maxTotalBufferSize(long maxTotalBufferSize) {
            set("maxTotalBufferSize", maxTotalBufferSize);
            return this;
        }
        /**
         * Per-resource buffer size in bytes to use when preserving network payloads (XHRs, etc).
         * <p><b>Experimental:</b> this part of CDP may change without notice.
         * @param maxResourceBufferSize field value
         * @return this model
         */
        public EnableRequest maxResourceBufferSize(long maxResourceBufferSize) {
            set("maxResourceBufferSize", maxResourceBufferSize);
            return this;
        }
        /**
         * Longest post body size (in bytes) that would be included in requestWillBeSent notification
         * @param maxPostDataSize field value
         * @return this model
         */
        public EnableRequest maxPostDataSize(long maxPostDataSize) {
            set("maxPostDataSize", maxPostDataSize);
            return this;
        }
        /**
         * Whether DirectSocket chunk send/receive events should be reported.
         * <p><b>Experimental:</b> this part of CDP may change without notice.
         * @param reportDirectSocketTraffic field value
         * @return this model
         */
        public EnableRequest reportDirectSocketTraffic(boolean reportDirectSocketTraffic) {
            set("reportDirectSocketTraffic", reportDirectSocketTraffic);
            return this;
        }
        /**
         * Enable storing response bodies outside of renderer, so that these survive a cross-process navigation. Requires maxTotalBufferSize to be set. Currently defaults to false. This field is being deprecated in favor of the dedicated configureDurableMessages command, due to the possibility of deadlocks when awaiting Network.enable before issuing Runtime.runIfWaitingForDebugger.
         * <p><b>Experimental:</b> this part of CDP may change without notice.
         * @param enableDurableMessages field value
         * @return this model
         */
        public EnableRequest enableDurableMessages(boolean enableDurableMessages) {
            set("enableDurableMessages", enableDurableMessages);
            return this;
        }
    }
    /**
     * Configures storing response bodies outside of renderer, so that these survive a cross-process navigation. If maxTotalBufferSize is not set, durable messages are disabled.
     * <p><b>Experimental:</b> this part of CDP may change without notice.
     */
    public static final class ConfigureDurableMessagesRequest extends CdpObject {
        public ConfigureDurableMessagesRequest() {}
        public static ConfigureDurableMessagesRequest fromMap(Map<String, Object> values) {
            final ConfigureDurableMessagesRequest instance_ = new ConfigureDurableMessagesRequest();
            if (values != null) instance_.values.putAll(values);
            return instance_;
        }
        /**
         * Buffer size in bytes to use when preserving network payloads (XHRs, etc).
         * @return the protocol field value, empty when absent
         */
        public OptionalLong maxTotalBufferSize() {
            return optionalLong("maxTotalBufferSize");
        }
        /**
         * Per-resource buffer size in bytes to use when preserving network payloads (XHRs, etc).
         * @return the protocol field value, empty when absent
         */
        public OptionalLong maxResourceBufferSize() {
            return optionalLong("maxResourceBufferSize");
        }
        /**
         * Buffer size in bytes to use when preserving network payloads (XHRs, etc).
         * @param maxTotalBufferSize field value
         * @return this model
         */
        public ConfigureDurableMessagesRequest maxTotalBufferSize(long maxTotalBufferSize) {
            set("maxTotalBufferSize", maxTotalBufferSize);
            return this;
        }
        /**
         * Per-resource buffer size in bytes to use when preserving network payloads (XHRs, etc).
         * @param maxResourceBufferSize field value
         * @return this model
         */
        public ConfigureDurableMessagesRequest maxResourceBufferSize(long maxResourceBufferSize) {
            set("maxResourceBufferSize", maxResourceBufferSize);
            return this;
        }
    }
    /**
     * Returns the DER-encoded certificate.
     * <p><b>Experimental:</b> this part of CDP may change without notice.
     */
    public static final class GetCertificateRequest extends CdpObject {
        public GetCertificateRequest() {}
        public static GetCertificateRequest fromMap(Map<String, Object> values) {
            final GetCertificateRequest instance_ = new GetCertificateRequest();
            if (values != null) instance_.values.putAll(values);
            return instance_;
        }
        /**
         * Origin to get certificate for.
         * @return the protocol field value
         */
        public String origin() {
            return requireString("origin");
        }
        /**
         * Origin to get certificate for.
         * @param origin field value
         * @return this model
         */
        public GetCertificateRequest origin(String origin) {
            set("origin", origin);
            return this;
        }
    }
    /**
     * Returns all browser cookies for the current URL. Depending on the backend support, will return detailed cookie information in the {@code cookies} field.
     */
    public static final class GetCookiesRequest extends CdpObject {
        public GetCookiesRequest() {}
        public static GetCookiesRequest fromMap(Map<String, Object> values) {
            final GetCookiesRequest instance_ = new GetCookiesRequest();
            if (values != null) instance_.values.putAll(values);
            return instance_;
        }
        /**
         * The list of URLs for which applicable cookies will be fetched. If not specified, it&#x27;s assumed to be set to the list containing the URLs of the page and all of its subframes.
         * @return the protocol field value, empty when absent
         */
        public Optional<java.util.List<String>> urls() {
            return optional(URLS);
        }
        /**
         * The list of URLs for which applicable cookies will be fetched. If not specified, it&#x27;s assumed to be set to the list containing the URLs of the page and all of its subframes.
         * @param urls field value
         * @return this model
         */
        public GetCookiesRequest urls(java.util.List<String> urls) {
            set("urls", urls);
            return this;
        }
    }
    /**
     * Returns content served for the given request.
     */
    public static final class GetResponseBodyRequest extends CdpObject {
        public GetResponseBodyRequest() {}
        public static GetResponseBodyRequest fromMap(Map<String, Object> values) {
            final GetResponseBodyRequest instance_ = new GetResponseBodyRequest();
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
         * Identifier of the network request to get content for.
         * @param requestId field value
         * @return this model
         */
        public GetResponseBodyRequest requestId(Network.RequestId requestId) {
            set("requestId", requestId);
            return this;
        }
    }
    /**
     * Returns post data sent with the request. Returns an error when no data was sent with the request.
     */
    public static final class GetRequestPostDataRequest extends CdpObject {
        public GetRequestPostDataRequest() {}
        public static GetRequestPostDataRequest fromMap(Map<String, Object> values) {
            final GetRequestPostDataRequest instance_ = new GetRequestPostDataRequest();
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
         * Identifier of the network request to get content for.
         * @param requestId field value
         * @return this model
         */
        public GetRequestPostDataRequest requestId(Network.RequestId requestId) {
            set("requestId", requestId);
            return this;
        }
    }
    /**
     * Returns content served for the given currently intercepted request.
     * <p><b>Experimental:</b> this part of CDP may change without notice.
     */
    public static final class GetResponseBodyForInterceptionRequest extends CdpObject {
        public GetResponseBodyForInterceptionRequest() {}
        public static GetResponseBodyForInterceptionRequest fromMap(Map<String, Object> values) {
            final GetResponseBodyForInterceptionRequest instance_ = new GetResponseBodyForInterceptionRequest();
            if (values != null) instance_.values.putAll(values);
            return instance_;
        }
        /**
         * Identifier for the intercepted request to get body for.
         * @return the protocol field value
         */
        public Network.InterceptionId interceptionId() {
            return require(INTERCEPTION_ID);
        }
        /**
         * Identifier for the intercepted request to get body for.
         * @param interceptionId field value
         * @return this model
         */
        public GetResponseBodyForInterceptionRequest interceptionId(Network.InterceptionId interceptionId) {
            set("interceptionId", interceptionId);
            return this;
        }
    }
    /**
     * Returns a handle to the stream representing the response body. Note that after this command, the intercepted request can&#x27;t be continued as is -- you either need to cancel it or to provide the response body. The stream only supports sequential read, IO.read will fail if the position is specified.
     * <p><b>Experimental:</b> this part of CDP may change without notice.
     */
    public static final class TakeResponseBodyForInterceptionAsStreamRequest extends CdpObject {
        public TakeResponseBodyForInterceptionAsStreamRequest() {}
        public static TakeResponseBodyForInterceptionAsStreamRequest fromMap(Map<String, Object> values) {
            final TakeResponseBodyForInterceptionAsStreamRequest instance_ = new TakeResponseBodyForInterceptionAsStreamRequest();
            if (values != null) instance_.values.putAll(values);
            return instance_;
        }
        /**
         * Returns the interceptionId field.
         * @return the protocol field value
         */
        public Network.InterceptionId interceptionId() {
            return require(INTERCEPTION_ID);
        }
        /**
         * Sets the interceptionId field.
         * @param interceptionId field value
         * @return this model
         */
        public TakeResponseBodyForInterceptionAsStreamRequest interceptionId(Network.InterceptionId interceptionId) {
            set("interceptionId", interceptionId);
            return this;
        }
    }
    /**
     * This method sends a new XMLHttpRequest which is identical to the original one. The following parameters should be identical: method, url, async, request body, extra headers, withCredentials attribute, user, password.
     * <p><b>Experimental:</b> this part of CDP may change without notice.
     */
    public static final class ReplayXHRRequest extends CdpObject {
        public ReplayXHRRequest() {}
        public static ReplayXHRRequest fromMap(Map<String, Object> values) {
            final ReplayXHRRequest instance_ = new ReplayXHRRequest();
            if (values != null) instance_.values.putAll(values);
            return instance_;
        }
        /**
         * Identifier of XHR to replay.
         * @return the protocol field value
         */
        public Network.RequestId requestId() {
            return require(REQUEST_ID);
        }
        /**
         * Identifier of XHR to replay.
         * @param requestId field value
         * @return this model
         */
        public ReplayXHRRequest requestId(Network.RequestId requestId) {
            set("requestId", requestId);
            return this;
        }
    }
    /**
     * Searches for given string in response content.
     * <p><b>Experimental:</b> this part of CDP may change without notice.
     */
    public static final class SearchInResponseBodyRequest extends CdpObject {
        public SearchInResponseBodyRequest() {}
        public static SearchInResponseBodyRequest fromMap(Map<String, Object> values) {
            final SearchInResponseBodyRequest instance_ = new SearchInResponseBodyRequest();
            if (values != null) instance_.values.putAll(values);
            return instance_;
        }
        /**
         * Identifier of the network response to search.
         * @return the protocol field value
         */
        public Network.RequestId requestId() {
            return require(REQUEST_ID);
        }
        /**
         * String to search for.
         * @return the protocol field value
         */
        public String query() {
            return requireString("query");
        }
        /**
         * If true, search is case sensitive.
         * @return the protocol field value, empty when absent
         */
        public Optional<Boolean> caseSensitive() {
            return optionalBoolean("caseSensitive");
        }
        /**
         * If true, treats string parameter as regex.
         * @return the protocol field value, empty when absent
         */
        public Optional<Boolean> isRegex() {
            return optionalBoolean("isRegex");
        }
        /**
         * Identifier of the network response to search.
         * @param requestId field value
         * @return this model
         */
        public SearchInResponseBodyRequest requestId(Network.RequestId requestId) {
            set("requestId", requestId);
            return this;
        }
        /**
         * String to search for.
         * @param query field value
         * @return this model
         */
        public SearchInResponseBodyRequest query(String query) {
            set("query", query);
            return this;
        }
        /**
         * If true, search is case sensitive.
         * @param caseSensitive field value
         * @return this model
         */
        public SearchInResponseBodyRequest caseSensitive(boolean caseSensitive) {
            set("caseSensitive", caseSensitive);
            return this;
        }
        /**
         * If true, treats string parameter as regex.
         * @param isRegex field value
         * @return this model
         */
        public SearchInResponseBodyRequest isRegex(boolean isRegex) {
            set("isRegex", isRegex);
            return this;
        }
    }
    /**
     * Blocks URLs from loading.
     * <p><b>Experimental:</b> this part of CDP may change without notice.
     */
    public static final class SetBlockedURLsRequest extends CdpObject {
        public SetBlockedURLsRequest() {}
        public static SetBlockedURLsRequest fromMap(Map<String, Object> values) {
            final SetBlockedURLsRequest instance_ = new SetBlockedURLsRequest();
            if (values != null) instance_.values.putAll(values);
            return instance_;
        }
        /**
         * Patterns to match in the order in which they are given. These patterns also take precedence over any wildcard patterns defined in {@code urls}.
         * @return the protocol field value, empty when absent
         */
        public Optional<java.util.List<Network.BlockPattern>> urlPatterns() {
            return optional(URL_PATTERNS);
        }
        /**
         * URL patterns to block. Wildcards (&#x27;*&#x27;) are allowed.
         * @return the protocol field value, empty when absent
         * @deprecated Deprecated by the Chromium DevTools Protocol.
         */
        @Deprecated
        public Optional<java.util.List<String>> urls() {
            return optional(URLS);
        }
        /**
         * Patterns to match in the order in which they are given. These patterns also take precedence over any wildcard patterns defined in {@code urls}.
         * @param urlPatterns field value
         * @return this model
         */
        public SetBlockedURLsRequest urlPatterns(java.util.List<Network.BlockPattern> urlPatterns) {
            set("urlPatterns", urlPatterns);
            return this;
        }
        /**
         * URL patterns to block. Wildcards (&#x27;*&#x27;) are allowed.
         * @param urls field value
         * @return this model
         * @deprecated Deprecated by the Chromium DevTools Protocol.
         */
        @Deprecated
        public SetBlockedURLsRequest urls(java.util.List<String> urls) {
            set("urls", urls);
            return this;
        }
    }
    /**
     * Toggles ignoring of service worker for each request.
     */
    public static final class SetBypassServiceWorkerRequest extends CdpObject {
        public SetBypassServiceWorkerRequest() {}
        public static SetBypassServiceWorkerRequest fromMap(Map<String, Object> values) {
            final SetBypassServiceWorkerRequest instance_ = new SetBypassServiceWorkerRequest();
            if (values != null) instance_.values.putAll(values);
            return instance_;
        }
        /**
         * Bypass service worker and load from network.
         * @return the protocol field value
         */
        public boolean bypass() {
            return requireBoolean("bypass");
        }
        /**
         * Bypass service worker and load from network.
         * @param bypass field value
         * @return this model
         */
        public SetBypassServiceWorkerRequest bypass(boolean bypass) {
            set("bypass", bypass);
            return this;
        }
    }
    /**
     * Toggles ignoring cache for each request. If {@code true}, cache will not be used.
     */
    public static final class SetCacheDisabledRequest extends CdpObject {
        public SetCacheDisabledRequest() {}
        public static SetCacheDisabledRequest fromMap(Map<String, Object> values) {
            final SetCacheDisabledRequest instance_ = new SetCacheDisabledRequest();
            if (values != null) instance_.values.putAll(values);
            return instance_;
        }
        /**
         * Cache disabled state.
         * @return the protocol field value
         */
        public boolean cacheDisabled() {
            return requireBoolean("cacheDisabled");
        }
        /**
         * Cache disabled state.
         * @param cacheDisabled field value
         * @return this model
         */
        public SetCacheDisabledRequest cacheDisabled(boolean cacheDisabled) {
            set("cacheDisabled", cacheDisabled);
            return this;
        }
    }
    /**
     * Sets a cookie with the given cookie data; may overwrite equivalent cookies if they exist.
     */
    public static final class SetCookieRequest extends CdpObject {
        public SetCookieRequest() {}
        public static SetCookieRequest fromMap(Map<String, Object> values) {
            final SetCookieRequest instance_ = new SetCookieRequest();
            if (values != null) instance_.values.putAll(values);
            return instance_;
        }
        /**
         * Cookie name.
         * @return the protocol field value
         */
        public String name() {
            return requireString("name");
        }
        /**
         * Cookie value.
         * @return the protocol field value
         */
        public String value() {
            return requireString("value");
        }
        /**
         * The request-URI to associate with the setting of the cookie. This value can affect the default domain, path, source port, and source scheme values of the created cookie.
         * @return the protocol field value, empty when absent
         */
        public Optional<String> url() {
            return optionalString("url");
        }
        /**
         * Cookie domain.
         * @return the protocol field value, empty when absent
         */
        public Optional<String> domain() {
            return optionalString("domain");
        }
        /**
         * Cookie path.
         * @return the protocol field value, empty when absent
         */
        public Optional<String> path() {
            return optionalString("path");
        }
        /**
         * True if cookie is secure.
         * @return the protocol field value, empty when absent
         */
        public Optional<Boolean> secure() {
            return optionalBoolean("secure");
        }
        /**
         * True if cookie is http-only.
         * @return the protocol field value, empty when absent
         */
        public Optional<Boolean> httpOnly() {
            return optionalBoolean("httpOnly");
        }
        /**
         * Cookie SameSite type.
         * @return the protocol field value, empty when absent
         */
        public Optional<Network.CookieSameSite> sameSite() {
            return optional(SAME_SITE);
        }
        /**
         * Cookie expiration date, session cookie if not set
         * @return the protocol field value, empty when absent
         */
        public Optional<Network.TimeSinceEpoch> expires() {
            return optional(EXPIRES);
        }
        /**
         * Cookie Priority type.
         * <p><b>Experimental:</b> this part of CDP may change without notice.
         * @return the protocol field value, empty when absent
         */
        public Optional<Network.CookiePriority> priority() {
            return optional(PRIORITY);
        }
        /**
         * Cookie source scheme type.
         * <p><b>Experimental:</b> this part of CDP may change without notice.
         * @return the protocol field value, empty when absent
         */
        public Optional<Network.CookieSourceScheme> sourceScheme() {
            return optional(SOURCE_SCHEME);
        }
        /**
         * Cookie source port. Valid values are {-1, [1, 65535]}, -1 indicates an unspecified port. An unspecified port value allows protocol clients to emulate legacy cookie scope for the port. This is a temporary ability and it will be removed in the future.
         * <p><b>Experimental:</b> this part of CDP may change without notice.
         * @return the protocol field value, empty when absent
         */
        public OptionalLong sourcePort() {
            return optionalLong("sourcePort");
        }
        /**
         * Cookie partition key. If not set, the cookie will be set as not partitioned.
         * <p><b>Experimental:</b> this part of CDP may change without notice.
         * @return the protocol field value, empty when absent
         */
        public Optional<Network.CookiePartitionKey> partitionKey() {
            return optional(PARTITION_KEY);
        }
        /**
         * Cookie name.
         * @param name field value
         * @return this model
         */
        public SetCookieRequest name(String name) {
            set("name", name);
            return this;
        }
        /**
         * Cookie value.
         * @param value field value
         * @return this model
         */
        public SetCookieRequest value(String value) {
            set("value", value);
            return this;
        }
        /**
         * The request-URI to associate with the setting of the cookie. This value can affect the default domain, path, source port, and source scheme values of the created cookie.
         * @param url field value
         * @return this model
         */
        public SetCookieRequest url(String url) {
            set("url", url);
            return this;
        }
        /**
         * Cookie domain.
         * @param domain field value
         * @return this model
         */
        public SetCookieRequest domain(String domain) {
            set("domain", domain);
            return this;
        }
        /**
         * Cookie path.
         * @param path field value
         * @return this model
         */
        public SetCookieRequest path(String path) {
            set("path", path);
            return this;
        }
        /**
         * True if cookie is secure.
         * @param secure field value
         * @return this model
         */
        public SetCookieRequest secure(boolean secure) {
            set("secure", secure);
            return this;
        }
        /**
         * True if cookie is http-only.
         * @param httpOnly field value
         * @return this model
         */
        public SetCookieRequest httpOnly(boolean httpOnly) {
            set("httpOnly", httpOnly);
            return this;
        }
        /**
         * Cookie SameSite type.
         * @param sameSite field value
         * @return this model
         */
        public SetCookieRequest sameSite(Network.CookieSameSite sameSite) {
            set("sameSite", sameSite);
            return this;
        }
        /**
         * Cookie expiration date, session cookie if not set
         * @param expires field value
         * @return this model
         */
        public SetCookieRequest expires(Network.TimeSinceEpoch expires) {
            set("expires", expires);
            return this;
        }
        /**
         * Cookie Priority type.
         * <p><b>Experimental:</b> this part of CDP may change without notice.
         * @param priority field value
         * @return this model
         */
        public SetCookieRequest priority(Network.CookiePriority priority) {
            set("priority", priority);
            return this;
        }
        /**
         * Cookie source scheme type.
         * <p><b>Experimental:</b> this part of CDP may change without notice.
         * @param sourceScheme field value
         * @return this model
         */
        public SetCookieRequest sourceScheme(Network.CookieSourceScheme sourceScheme) {
            set("sourceScheme", sourceScheme);
            return this;
        }
        /**
         * Cookie source port. Valid values are {-1, [1, 65535]}, -1 indicates an unspecified port. An unspecified port value allows protocol clients to emulate legacy cookie scope for the port. This is a temporary ability and it will be removed in the future.
         * <p><b>Experimental:</b> this part of CDP may change without notice.
         * @param sourcePort field value
         * @return this model
         */
        public SetCookieRequest sourcePort(long sourcePort) {
            set("sourcePort", sourcePort);
            return this;
        }
        /**
         * Cookie partition key. If not set, the cookie will be set as not partitioned.
         * <p><b>Experimental:</b> this part of CDP may change without notice.
         * @param partitionKey field value
         * @return this model
         */
        public SetCookieRequest partitionKey(Network.CookiePartitionKey partitionKey) {
            set("partitionKey", partitionKey);
            return this;
        }
    }
    /**
     * Sets given cookies.
     */
    public static final class SetCookiesRequest extends CdpObject {
        public SetCookiesRequest() {}
        public static SetCookiesRequest fromMap(Map<String, Object> values) {
            final SetCookiesRequest instance_ = new SetCookiesRequest();
            if (values != null) instance_.values.putAll(values);
            return instance_;
        }
        /**
         * Cookies to be set.
         * @return the protocol field value
         */
        public java.util.List<Network.CookieParam> cookies() {
            return require(COOKIES);
        }
        /**
         * Cookies to be set.
         * @param cookies field value
         * @return this model
         */
        public SetCookiesRequest cookies(java.util.List<Network.CookieParam> cookies) {
            set("cookies", cookies);
            return this;
        }
    }
    /**
     * Specifies whether to always send extra HTTP headers with the requests from this page.
     */
    public static final class SetExtraHTTPHeadersRequest extends CdpObject {
        public SetExtraHTTPHeadersRequest() {}
        public static SetExtraHTTPHeadersRequest fromMap(Map<String, Object> values) {
            final SetExtraHTTPHeadersRequest instance_ = new SetExtraHTTPHeadersRequest();
            if (values != null) instance_.values.putAll(values);
            return instance_;
        }
        /**
         * Map with extra HTTP headers.
         * @return the protocol field value
         */
        public java.util.Map<String, Object> headers() {
            return requireObject("headers");
        }
        /**
         * Map with extra HTTP headers.
         * @param headers field value
         * @return this model
         */
        public SetExtraHTTPHeadersRequest headers(java.util.Map<String, Object> headers) {
            set("headers", headers);
            return this;
        }
    }
    /**
     * Specifies whether to attach a page script stack id in requests
     * <p><b>Experimental:</b> this part of CDP may change without notice.
     */
    public static final class SetAttachDebugStackRequest extends CdpObject {
        public SetAttachDebugStackRequest() {}
        public static SetAttachDebugStackRequest fromMap(Map<String, Object> values) {
            final SetAttachDebugStackRequest instance_ = new SetAttachDebugStackRequest();
            if (values != null) instance_.values.putAll(values);
            return instance_;
        }
        /**
         * Whether to attach a page script stack for debugging purpose.
         * @return the protocol field value
         */
        public boolean enabled() {
            return requireBoolean("enabled");
        }
        /**
         * Whether to attach a page script stack for debugging purpose.
         * @param enabled field value
         * @return this model
         */
        public SetAttachDebugStackRequest enabled(boolean enabled) {
            set("enabled", enabled);
            return this;
        }
    }
    /**
     * Sets the requests to intercept that match the provided patterns and optionally resource types. Deprecated, please use Fetch.enable instead.
     * <p><b>Experimental:</b> this part of CDP may change without notice.
     * @deprecated Deprecated by the Chromium DevTools Protocol.
     */
    @Deprecated
    public static final class SetRequestInterceptionRequest extends CdpObject {
        public SetRequestInterceptionRequest() {}
        public static SetRequestInterceptionRequest fromMap(Map<String, Object> values) {
            final SetRequestInterceptionRequest instance_ = new SetRequestInterceptionRequest();
            if (values != null) instance_.values.putAll(values);
            return instance_;
        }
        /**
         * Requests matching any of these patterns will be forwarded and wait for the corresponding continueInterceptedRequest call.
         * @return the protocol field value
         */
        public java.util.List<Network.RequestPattern> patterns() {
            return require(PATTERNS);
        }
        /**
         * Requests matching any of these patterns will be forwarded and wait for the corresponding continueInterceptedRequest call.
         * @param patterns field value
         * @return this model
         */
        public SetRequestInterceptionRequest patterns(java.util.List<Network.RequestPattern> patterns) {
            set("patterns", patterns);
            return this;
        }
    }
    /**
     * Allows overriding user agent with the given string.
     */
    public static final class SetUserAgentOverrideRequest extends CdpObject {
        public SetUserAgentOverrideRequest() {}
        public static SetUserAgentOverrideRequest fromMap(Map<String, Object> values) {
            final SetUserAgentOverrideRequest instance_ = new SetUserAgentOverrideRequest();
            if (values != null) instance_.values.putAll(values);
            return instance_;
        }
        /**
         * User agent to use.
         * @return the protocol field value
         */
        public String userAgent() {
            return requireString("userAgent");
        }
        /**
         * Browser language to emulate.
         * @return the protocol field value, empty when absent
         */
        public Optional<String> acceptLanguage() {
            return optionalString("acceptLanguage");
        }
        /**
         * The platform navigator.platform should return.
         * @return the protocol field value, empty when absent
         */
        public Optional<String> platform() {
            return optionalString("platform");
        }
        /**
         * To be sent in Sec-CH-UA-* headers and returned in navigator.userAgentData
         * <p><b>Experimental:</b> this part of CDP may change without notice.
         * @return the protocol field value, empty when absent
         */
        public Optional<Emulation.UserAgentMetadata> userAgentMetadata() {
            return optional(USER_AGENT_METADATA);
        }
        /**
         * User agent to use.
         * @param userAgent field value
         * @return this model
         */
        public SetUserAgentOverrideRequest userAgent(String userAgent) {
            set("userAgent", userAgent);
            return this;
        }
        /**
         * Browser language to emulate.
         * @param acceptLanguage field value
         * @return this model
         */
        public SetUserAgentOverrideRequest acceptLanguage(String acceptLanguage) {
            set("acceptLanguage", acceptLanguage);
            return this;
        }
        /**
         * The platform navigator.platform should return.
         * @param platform field value
         * @return this model
         */
        public SetUserAgentOverrideRequest platform(String platform) {
            set("platform", platform);
            return this;
        }
        /**
         * To be sent in Sec-CH-UA-* headers and returned in navigator.userAgentData
         * <p><b>Experimental:</b> this part of CDP may change without notice.
         * @param userAgentMetadata field value
         * @return this model
         */
        public SetUserAgentOverrideRequest userAgentMetadata(Emulation.UserAgentMetadata userAgentMetadata) {
            set("userAgentMetadata", userAgentMetadata);
            return this;
        }
    }
    /**
     * Enables streaming of the response for the given requestId. If enabled, the dataReceived event contains the data that was received during streaming.
     * <p><b>Experimental:</b> this part of CDP may change without notice.
     */
    public static final class StreamResourceContentRequest extends CdpObject {
        public StreamResourceContentRequest() {}
        public static StreamResourceContentRequest fromMap(Map<String, Object> values) {
            final StreamResourceContentRequest instance_ = new StreamResourceContentRequest();
            if (values != null) instance_.values.putAll(values);
            return instance_;
        }
        /**
         * Identifier of the request to stream.
         * @return the protocol field value
         */
        public Network.RequestId requestId() {
            return require(REQUEST_ID);
        }
        /**
         * Identifier of the request to stream.
         * @param requestId field value
         * @return this model
         */
        public StreamResourceContentRequest requestId(Network.RequestId requestId) {
            set("requestId", requestId);
            return this;
        }
    }
    /**
     * Returns information about the COEP/COOP isolation status.
     * <p><b>Experimental:</b> this part of CDP may change without notice.
     */
    public static final class GetSecurityIsolationStatusRequest extends CdpObject {
        public GetSecurityIsolationStatusRequest() {}
        public static GetSecurityIsolationStatusRequest fromMap(Map<String, Object> values) {
            final GetSecurityIsolationStatusRequest instance_ = new GetSecurityIsolationStatusRequest();
            if (values != null) instance_.values.putAll(values);
            return instance_;
        }
        /**
         * If no frameId is provided, the status of the target is provided.
         * @return the protocol field value, empty when absent
         */
        public Optional<Page.FrameId> frameId() {
            return optional(FRAME_ID);
        }
        /**
         * If no frameId is provided, the status of the target is provided.
         * @param frameId field value
         * @return this model
         */
        public GetSecurityIsolationStatusRequest frameId(Page.FrameId frameId) {
            set("frameId", frameId);
            return this;
        }
    }
    /**
     * Enables tracking for the Reporting API, events generated by the Reporting API will now be delivered to the client. Enabling triggers &#x27;reportingApiReportAdded&#x27; for all existing reports.
     * <p><b>Experimental:</b> this part of CDP may change without notice.
     */
    public static final class EnableReportingApiRequest extends CdpObject {
        public EnableReportingApiRequest() {}
        public static EnableReportingApiRequest fromMap(Map<String, Object> values) {
            final EnableReportingApiRequest instance_ = new EnableReportingApiRequest();
            if (values != null) instance_.values.putAll(values);
            return instance_;
        }
        /**
         * Whether to enable or disable events for the Reporting API
         * @return the protocol field value
         */
        public boolean enable() {
            return requireBoolean("enable");
        }
        /**
         * Whether to enable or disable events for the Reporting API
         * @param enable field value
         * @return this model
         */
        public EnableReportingApiRequest enable(boolean enable) {
            set("enable", enable);
            return this;
        }
    }
    /**
     * Sets up tracking device bound sessions and fetching of initial set of sessions.
     * <p><b>Experimental:</b> this part of CDP may change without notice.
     */
    public static final class EnableDeviceBoundSessionsRequest extends CdpObject {
        public EnableDeviceBoundSessionsRequest() {}
        public static EnableDeviceBoundSessionsRequest fromMap(Map<String, Object> values) {
            final EnableDeviceBoundSessionsRequest instance_ = new EnableDeviceBoundSessionsRequest();
            if (values != null) instance_.values.putAll(values);
            return instance_;
        }
        /**
         * Whether to enable or disable events.
         * @return the protocol field value
         */
        public boolean enable() {
            return requireBoolean("enable");
        }
        /**
         * Whether to enable or disable events.
         * @param enable field value
         * @return this model
         */
        public EnableDeviceBoundSessionsRequest enable(boolean enable) {
            set("enable", enable);
            return this;
        }
    }
    /**
     * Deletes a device bound session.
     * <p><b>Experimental:</b> this part of CDP may change without notice.
     */
    public static final class DeleteDeviceBoundSessionRequest extends CdpObject {
        public DeleteDeviceBoundSessionRequest() {}
        public static DeleteDeviceBoundSessionRequest fromMap(Map<String, Object> values) {
            final DeleteDeviceBoundSessionRequest instance_ = new DeleteDeviceBoundSessionRequest();
            if (values != null) instance_.values.putAll(values);
            return instance_;
        }
        /**
         * Returns the key field.
         * @return the protocol field value
         */
        public Network.DeviceBoundSessionKey key() {
            return require(KEY);
        }
        /**
         * Sets the key field.
         * @param key field value
         * @return this model
         */
        public DeleteDeviceBoundSessionRequest key(Network.DeviceBoundSessionKey key) {
            set("key", key);
            return this;
        }
    }
    /**
     * Fetches the schemeful site for a specific origin.
     * <p><b>Experimental:</b> this part of CDP may change without notice.
     */
    public static final class FetchSchemefulSiteRequest extends CdpObject {
        public FetchSchemefulSiteRequest() {}
        public static FetchSchemefulSiteRequest fromMap(Map<String, Object> values) {
            final FetchSchemefulSiteRequest instance_ = new FetchSchemefulSiteRequest();
            if (values != null) instance_.values.putAll(values);
            return instance_;
        }
        /**
         * The URL origin.
         * @return the protocol field value
         */
        public String origin() {
            return requireString("origin");
        }
        /**
         * The URL origin.
         * @param origin field value
         * @return this model
         */
        public FetchSchemefulSiteRequest origin(String origin) {
            set("origin", origin);
            return this;
        }
    }
    /**
     * Fetches the resource and returns the content.
     * <p><b>Experimental:</b> this part of CDP may change without notice.
     */
    public static final class LoadNetworkResourceRequest extends CdpObject {
        public LoadNetworkResourceRequest() {}
        public static LoadNetworkResourceRequest fromMap(Map<String, Object> values) {
            final LoadNetworkResourceRequest instance_ = new LoadNetworkResourceRequest();
            if (values != null) instance_.values.putAll(values);
            return instance_;
        }
        /**
         * Frame id to get the resource for. Mandatory for frame targets, and should be omitted for worker targets.
         * @return the protocol field value, empty when absent
         */
        public Optional<Page.FrameId> frameId() {
            return optional(FRAME_ID);
        }
        /**
         * URL of the resource to get content for.
         * @return the protocol field value
         */
        public String url() {
            return requireString("url");
        }
        /**
         * Options for the request.
         * @return the protocol field value
         */
        public Network.LoadNetworkResourceOptions options() {
            return require(OPTIONS);
        }
        /**
         * Frame id to get the resource for. Mandatory for frame targets, and should be omitted for worker targets.
         * @param frameId field value
         * @return this model
         */
        public LoadNetworkResourceRequest frameId(Page.FrameId frameId) {
            set("frameId", frameId);
            return this;
        }
        /**
         * URL of the resource to get content for.
         * @param url field value
         * @return this model
         */
        public LoadNetworkResourceRequest url(String url) {
            set("url", url);
            return this;
        }
        /**
         * Options for the request.
         * @param options field value
         * @return this model
         */
        public LoadNetworkResourceRequest options(Network.LoadNetworkResourceOptions options) {
            set("options", options);
            return this;
        }
    }
    /**
     * Sets Controls for third-party cookie access Page reload is required before the new cookie behavior will be observed
     * <p><b>Experimental:</b> this part of CDP may change without notice.
     */
    public static final class SetCookieControlsRequest extends CdpObject {
        public SetCookieControlsRequest() {}
        public static SetCookieControlsRequest fromMap(Map<String, Object> values) {
            final SetCookieControlsRequest instance_ = new SetCookieControlsRequest();
            if (values != null) instance_.values.putAll(values);
            return instance_;
        }
        /**
         * Whether 3pc restriction is enabled.
         * @return the protocol field value
         */
        public boolean enableThirdPartyCookieRestriction() {
            return requireBoolean("enableThirdPartyCookieRestriction");
        }
        /**
         * Whether 3pc restriction is enabled.
         * @param enableThirdPartyCookieRestriction field value
         * @return this model
         */
        public SetCookieControlsRequest enableThirdPartyCookieRestriction(boolean enableThirdPartyCookieRestriction) {
            set("enableThirdPartyCookieRestriction", enableThirdPartyCookieRestriction);
            return this;
        }
    }
    /**
     * Returns content served for the given request.
     */
    public static final class GetResponseBodyResult extends CdpObject {
        public GetResponseBodyResult() {}
        private GetResponseBodyResult(Map<String, Object> values) { super(values); }
        public static GetResponseBodyResult fromMap(Map<String, Object> values) {
            return new GetResponseBodyResult(values);
        }
        /**
         * Response body.
         * @return the protocol field value
         */
        public String body() {
            return requireString("body");
        }
        /**
         * True, if content was sent as base64.
         * @return the protocol field value
         */
        public boolean base64Encoded() {
            return requireBoolean("base64Encoded");
        }
        /**
         * Response body.
         * @param body field value
         * @return this model
         */
        public GetResponseBodyResult body(String body) {
            set("body", body);
            return this;
        }
        /**
         * True, if content was sent as base64.
         * @param base64Encoded field value
         * @return this model
         */
        public GetResponseBodyResult base64Encoded(boolean base64Encoded) {
            set("base64Encoded", base64Encoded);
            return this;
        }
    }
    /**
     * Returns post data sent with the request. Returns an error when no data was sent with the request.
     */
    public static final class GetRequestPostDataResult extends CdpObject {
        public GetRequestPostDataResult() {}
        private GetRequestPostDataResult(Map<String, Object> values) { super(values); }
        public static GetRequestPostDataResult fromMap(Map<String, Object> values) {
            return new GetRequestPostDataResult(values);
        }
        /**
         * Request body string, omitting files from multipart requests
         * @return the protocol field value
         */
        public String postData() {
            return requireString("postData");
        }
        /**
         * True, if content was sent as base64.
         * @return the protocol field value
         */
        public boolean base64Encoded() {
            return requireBoolean("base64Encoded");
        }
        /**
         * Request body string, omitting files from multipart requests
         * @param postData field value
         * @return this model
         */
        public GetRequestPostDataResult postData(String postData) {
            set("postData", postData);
            return this;
        }
        /**
         * True, if content was sent as base64.
         * @param base64Encoded field value
         * @return this model
         */
        public GetRequestPostDataResult base64Encoded(boolean base64Encoded) {
            set("base64Encoded", base64Encoded);
            return this;
        }
    }
    /**
     * Returns content served for the given currently intercepted request.
     * <p><b>Experimental:</b> this part of CDP may change without notice.
     */
    public static final class GetResponseBodyForInterceptionResult extends CdpObject {
        public GetResponseBodyForInterceptionResult() {}
        private GetResponseBodyForInterceptionResult(Map<String, Object> values) { super(values); }
        public static GetResponseBodyForInterceptionResult fromMap(Map<String, Object> values) {
            return new GetResponseBodyForInterceptionResult(values);
        }
        /**
         * Response body.
         * @return the protocol field value
         */
        public String body() {
            return requireString("body");
        }
        /**
         * True, if content was sent as base64.
         * @return the protocol field value
         */
        public boolean base64Encoded() {
            return requireBoolean("base64Encoded");
        }
        /**
         * Response body.
         * @param body field value
         * @return this model
         */
        public GetResponseBodyForInterceptionResult body(String body) {
            set("body", body);
            return this;
        }
        /**
         * True, if content was sent as base64.
         * @param base64Encoded field value
         * @return this model
         */
        public GetResponseBodyForInterceptionResult base64Encoded(boolean base64Encoded) {
            set("base64Encoded", base64Encoded);
            return this;
        }
    }
    /**
     * Fired when data chunk was received over the network.
     */
    public static final class DataReceivedEvent extends CdpObject {
        public DataReceivedEvent() {}
        private DataReceivedEvent(Map<String, Object> values) { super(values); }
        public static DataReceivedEvent fromMap(Map<String, Object> values) {
            return new DataReceivedEvent(values);
        }
        /**
         * Request identifier.
         * @return the protocol field value
         */
        public Network.RequestId requestId() {
            return require(REQUEST_ID);
        }
        /**
         * Timestamp.
         * @return the protocol field value
         */
        public Network.MonotonicTime timestamp() {
            return require(TIMESTAMP_2);
        }
        /**
         * Data chunk length.
         * @return the protocol field value
         */
        public long dataLength() {
            return requireLong("dataLength");
        }
        /**
         * Actual bytes received (might be less than dataLength for compressed encodings).
         * @return the protocol field value
         */
        public long encodedDataLength() {
            return requireLong("encodedDataLength");
        }
        /**
         * Data that was received. (Encoded as a base64 string when passed over JSON)
         * <p><b>Experimental:</b> this part of CDP may change without notice.
         * @return the protocol field value, empty when absent
         */
        public Optional<String> data() {
            return optionalString("data");
        }
        /**
         * Request identifier.
         * @param requestId field value
         * @return this model
         */
        public DataReceivedEvent requestId(Network.RequestId requestId) {
            set("requestId", requestId);
            return this;
        }
        /**
         * Timestamp.
         * @param timestamp field value
         * @return this model
         */
        public DataReceivedEvent timestamp(Network.MonotonicTime timestamp) {
            set("timestamp", timestamp);
            return this;
        }
        /**
         * Data chunk length.
         * @param dataLength field value
         * @return this model
         */
        public DataReceivedEvent dataLength(long dataLength) {
            set("dataLength", dataLength);
            return this;
        }
        /**
         * Actual bytes received (might be less than dataLength for compressed encodings).
         * @param encodedDataLength field value
         * @return this model
         */
        public DataReceivedEvent encodedDataLength(long encodedDataLength) {
            set("encodedDataLength", encodedDataLength);
            return this;
        }
        /**
         * Data that was received. (Encoded as a base64 string when passed over JSON)
         * <p><b>Experimental:</b> this part of CDP may change without notice.
         * @param data field value
         * @return this model
         */
        public DataReceivedEvent data(String data) {
            set("data", data);
            return this;
        }
    }
    /**
     * Fired when EventSource message is received.
     */
    public static final class EventSourceMessageReceivedEvent extends CdpObject {
        public EventSourceMessageReceivedEvent() {}
        private EventSourceMessageReceivedEvent(Map<String, Object> values) { super(values); }
        public static EventSourceMessageReceivedEvent fromMap(Map<String, Object> values) {
            return new EventSourceMessageReceivedEvent(values);
        }
        /**
         * Request identifier.
         * @return the protocol field value
         */
        public Network.RequestId requestId() {
            return require(REQUEST_ID);
        }
        /**
         * Timestamp.
         * @return the protocol field value
         */
        public Network.MonotonicTime timestamp() {
            return require(TIMESTAMP_2);
        }
        /**
         * Message type.
         * @return the protocol field value
         */
        public String eventName() {
            return requireString("eventName");
        }
        /**
         * Message identifier.
         * @return the protocol field value
         */
        public String eventId() {
            return requireString("eventId");
        }
        /**
         * Message content.
         * @return the protocol field value
         */
        public String data() {
            return requireString("data");
        }
        /**
         * Request identifier.
         * @param requestId field value
         * @return this model
         */
        public EventSourceMessageReceivedEvent requestId(Network.RequestId requestId) {
            set("requestId", requestId);
            return this;
        }
        /**
         * Timestamp.
         * @param timestamp field value
         * @return this model
         */
        public EventSourceMessageReceivedEvent timestamp(Network.MonotonicTime timestamp) {
            set("timestamp", timestamp);
            return this;
        }
        /**
         * Message type.
         * @param eventName field value
         * @return this model
         */
        public EventSourceMessageReceivedEvent eventName(String eventName) {
            set("eventName", eventName);
            return this;
        }
        /**
         * Message identifier.
         * @param eventId field value
         * @return this model
         */
        public EventSourceMessageReceivedEvent eventId(String eventId) {
            set("eventId", eventId);
            return this;
        }
        /**
         * Message content.
         * @param data field value
         * @return this model
         */
        public EventSourceMessageReceivedEvent data(String data) {
            set("data", data);
            return this;
        }
    }
    /**
     * Fired when HTTP request has failed to load.
     */
    public static final class LoadingFailedEvent extends CdpObject {
        public LoadingFailedEvent() {}
        private LoadingFailedEvent(Map<String, Object> values) { super(values); }
        public static LoadingFailedEvent fromMap(Map<String, Object> values) {
            return new LoadingFailedEvent(values);
        }
        /**
         * Request identifier.
         * @return the protocol field value
         */
        public Network.RequestId requestId() {
            return require(REQUEST_ID);
        }
        /**
         * Timestamp.
         * @return the protocol field value
         */
        public Network.MonotonicTime timestamp() {
            return require(TIMESTAMP_2);
        }
        /**
         * Resource type.
         * @return the protocol field value
         */
        public Network.ResourceType type() {
            return require(TYPE);
        }
        /**
         * Error message. List of network errors: https://cs.chromium.org/chromium/src/net/base/net_error_list.h
         * @return the protocol field value
         */
        public String errorText() {
            return requireString("errorText");
        }
        /**
         * True if loading was canceled.
         * @return the protocol field value, empty when absent
         */
        public Optional<Boolean> canceled() {
            return optionalBoolean("canceled");
        }
        /**
         * The reason why loading was blocked, if any.
         * @return the protocol field value, empty when absent
         */
        public Optional<Network.BlockedReason> blockedReason() {
            return optional(BLOCKED_REASON);
        }
        /**
         * The reason why loading was blocked by CORS, if any.
         * @return the protocol field value, empty when absent
         */
        public Optional<Network.CorsErrorStatus> corsErrorStatus() {
            return optional(CORS_ERROR_STATUS);
        }
        /**
         * Request identifier.
         * @param requestId field value
         * @return this model
         */
        public LoadingFailedEvent requestId(Network.RequestId requestId) {
            set("requestId", requestId);
            return this;
        }
        /**
         * Timestamp.
         * @param timestamp field value
         * @return this model
         */
        public LoadingFailedEvent timestamp(Network.MonotonicTime timestamp) {
            set("timestamp", timestamp);
            return this;
        }
        /**
         * Resource type.
         * @param type field value
         * @return this model
         */
        public LoadingFailedEvent type(Network.ResourceType type) {
            set("type", type);
            return this;
        }
        /**
         * Error message. List of network errors: https://cs.chromium.org/chromium/src/net/base/net_error_list.h
         * @param errorText field value
         * @return this model
         */
        public LoadingFailedEvent errorText(String errorText) {
            set("errorText", errorText);
            return this;
        }
        /**
         * True if loading was canceled.
         * @param canceled field value
         * @return this model
         */
        public LoadingFailedEvent canceled(boolean canceled) {
            set("canceled", canceled);
            return this;
        }
        /**
         * The reason why loading was blocked, if any.
         * @param blockedReason field value
         * @return this model
         */
        public LoadingFailedEvent blockedReason(Network.BlockedReason blockedReason) {
            set("blockedReason", blockedReason);
            return this;
        }
        /**
         * The reason why loading was blocked by CORS, if any.
         * @param corsErrorStatus field value
         * @return this model
         */
        public LoadingFailedEvent corsErrorStatus(Network.CorsErrorStatus corsErrorStatus) {
            set("corsErrorStatus", corsErrorStatus);
            return this;
        }
    }
    /**
     * Fired when HTTP request has finished loading.
     */
    public static final class LoadingFinishedEvent extends CdpObject {
        public LoadingFinishedEvent() {}
        private LoadingFinishedEvent(Map<String, Object> values) { super(values); }
        public static LoadingFinishedEvent fromMap(Map<String, Object> values) {
            return new LoadingFinishedEvent(values);
        }
        /**
         * Request identifier.
         * @return the protocol field value
         */
        public Network.RequestId requestId() {
            return require(REQUEST_ID);
        }
        /**
         * Timestamp.
         * @return the protocol field value
         */
        public Network.MonotonicTime timestamp() {
            return require(TIMESTAMP_2);
        }
        /**
         * Total number of bytes received for this request.
         * @return the protocol field value
         */
        public double encodedDataLength() {
            return requireDouble("encodedDataLength");
        }
        /**
         * Request identifier.
         * @param requestId field value
         * @return this model
         */
        public LoadingFinishedEvent requestId(Network.RequestId requestId) {
            set("requestId", requestId);
            return this;
        }
        /**
         * Timestamp.
         * @param timestamp field value
         * @return this model
         */
        public LoadingFinishedEvent timestamp(Network.MonotonicTime timestamp) {
            set("timestamp", timestamp);
            return this;
        }
        /**
         * Total number of bytes received for this request.
         * @param encodedDataLength field value
         * @return this model
         */
        public LoadingFinishedEvent encodedDataLength(double encodedDataLength) {
            set("encodedDataLength", encodedDataLength);
            return this;
        }
    }
    /**
     * Details of an intercepted HTTP request, which must be either allowed, blocked, modified or mocked. Deprecated, use Fetch.requestPaused instead.
     * <p><b>Experimental:</b> this part of CDP may change without notice.
     * @deprecated Deprecated by the Chromium DevTools Protocol.
     */
    @Deprecated
    public static final class RequestInterceptedEvent extends CdpObject {
        public RequestInterceptedEvent() {}
        private RequestInterceptedEvent(Map<String, Object> values) { super(values); }
        public static RequestInterceptedEvent fromMap(Map<String, Object> values) {
            return new RequestInterceptedEvent(values);
        }
        /**
         * Each request the page makes will have a unique id, however if any redirects are encountered while processing that fetch, they will be reported with the same id as the original fetch. Likewise if HTTP authentication is needed then the same fetch id will be used.
         * @return the protocol field value
         */
        public Network.InterceptionId interceptionId() {
            return require(INTERCEPTION_ID);
        }
        /**
         * Returns the request field.
         * @return the protocol field value
         */
        public Network.Request request() {
            return require(REQUEST);
        }
        /**
         * The id of the frame that initiated the request.
         * @return the protocol field value
         */
        public Page.FrameId frameId() {
            return require(FRAME_ID);
        }
        /**
         * How the requested resource will be used.
         * @return the protocol field value
         */
        public Network.ResourceType resourceType() {
            return require(RESOURCE_TYPE);
        }
        /**
         * Whether this is a navigation request, which can abort the navigation completely.
         * @return the protocol field value
         */
        public boolean isNavigationRequest() {
            return requireBoolean("isNavigationRequest");
        }
        /**
         * Set if the request is a navigation that will result in a download. Only present after response is received from the server (i.e. HeadersReceived stage).
         * @return the protocol field value, empty when absent
         */
        public Optional<Boolean> isDownload() {
            return optionalBoolean("isDownload");
        }
        /**
         * Redirect location, only sent if a redirect was intercepted.
         * @return the protocol field value, empty when absent
         */
        public Optional<String> redirectUrl() {
            return optionalString("redirectUrl");
        }
        /**
         * Details of the Authorization Challenge encountered. If this is set then continueInterceptedRequest must contain an authChallengeResponse.
         * @return the protocol field value, empty when absent
         */
        public Optional<Network.AuthChallenge> authChallenge() {
            return optional(AUTH_CHALLENGE);
        }
        /**
         * Response error if intercepted at response stage or if redirect occurred while intercepting request.
         * @return the protocol field value, empty when absent
         */
        public Optional<Network.ErrorReason> responseErrorReason() {
            return optional(RESPONSE_ERROR_REASON);
        }
        /**
         * Response code if intercepted at response stage or if redirect occurred while intercepting request or auth retry occurred.
         * @return the protocol field value, empty when absent
         */
        public OptionalLong responseStatusCode() {
            return optionalLong("responseStatusCode");
        }
        /**
         * Response headers if intercepted at the response stage or if redirect occurred while intercepting request or auth retry occurred.
         * @return the protocol field value, empty when absent
         */
        public Optional<java.util.Map<String, Object>> responseHeaders() {
            return optionalObject("responseHeaders");
        }
        /**
         * If the intercepted request had a corresponding requestWillBeSent event fired for it, then this requestId will be the same as the requestId present in the requestWillBeSent event.
         * @return the protocol field value, empty when absent
         */
        public Optional<Network.RequestId> requestId() {
            return optional(REQUEST_ID);
        }
        /**
         * Each request the page makes will have a unique id, however if any redirects are encountered while processing that fetch, they will be reported with the same id as the original fetch. Likewise if HTTP authentication is needed then the same fetch id will be used.
         * @param interceptionId field value
         * @return this model
         */
        public RequestInterceptedEvent interceptionId(Network.InterceptionId interceptionId) {
            set("interceptionId", interceptionId);
            return this;
        }
        /**
         * Sets the request field.
         * @param request field value
         * @return this model
         */
        public RequestInterceptedEvent request(Network.Request request) {
            set("request", request);
            return this;
        }
        /**
         * The id of the frame that initiated the request.
         * @param frameId field value
         * @return this model
         */
        public RequestInterceptedEvent frameId(Page.FrameId frameId) {
            set("frameId", frameId);
            return this;
        }
        /**
         * How the requested resource will be used.
         * @param resourceType field value
         * @return this model
         */
        public RequestInterceptedEvent resourceType(Network.ResourceType resourceType) {
            set("resourceType", resourceType);
            return this;
        }
        /**
         * Whether this is a navigation request, which can abort the navigation completely.
         * @param isNavigationRequest field value
         * @return this model
         */
        public RequestInterceptedEvent isNavigationRequest(boolean isNavigationRequest) {
            set("isNavigationRequest", isNavigationRequest);
            return this;
        }
        /**
         * Set if the request is a navigation that will result in a download. Only present after response is received from the server (i.e. HeadersReceived stage).
         * @param isDownload field value
         * @return this model
         */
        public RequestInterceptedEvent isDownload(boolean isDownload) {
            set("isDownload", isDownload);
            return this;
        }
        /**
         * Redirect location, only sent if a redirect was intercepted.
         * @param redirectUrl field value
         * @return this model
         */
        public RequestInterceptedEvent redirectUrl(String redirectUrl) {
            set("redirectUrl", redirectUrl);
            return this;
        }
        /**
         * Details of the Authorization Challenge encountered. If this is set then continueInterceptedRequest must contain an authChallengeResponse.
         * @param authChallenge field value
         * @return this model
         */
        public RequestInterceptedEvent authChallenge(Network.AuthChallenge authChallenge) {
            set("authChallenge", authChallenge);
            return this;
        }
        /**
         * Response error if intercepted at response stage or if redirect occurred while intercepting request.
         * @param responseErrorReason field value
         * @return this model
         */
        public RequestInterceptedEvent responseErrorReason(Network.ErrorReason responseErrorReason) {
            set("responseErrorReason", responseErrorReason);
            return this;
        }
        /**
         * Response code if intercepted at response stage or if redirect occurred while intercepting request or auth retry occurred.
         * @param responseStatusCode field value
         * @return this model
         */
        public RequestInterceptedEvent responseStatusCode(long responseStatusCode) {
            set("responseStatusCode", responseStatusCode);
            return this;
        }
        /**
         * Response headers if intercepted at the response stage or if redirect occurred while intercepting request or auth retry occurred.
         * @param responseHeaders field value
         * @return this model
         */
        public RequestInterceptedEvent responseHeaders(java.util.Map<String, Object> responseHeaders) {
            set("responseHeaders", responseHeaders);
            return this;
        }
        /**
         * If the intercepted request had a corresponding requestWillBeSent event fired for it, then this requestId will be the same as the requestId present in the requestWillBeSent event.
         * @param requestId field value
         * @return this model
         */
        public RequestInterceptedEvent requestId(Network.RequestId requestId) {
            set("requestId", requestId);
            return this;
        }
    }
    /**
     * Fired if request ended up loading from cache.
     */
    public static final class RequestServedFromCacheEvent extends CdpObject {
        public RequestServedFromCacheEvent() {}
        private RequestServedFromCacheEvent(Map<String, Object> values) { super(values); }
        public static RequestServedFromCacheEvent fromMap(Map<String, Object> values) {
            return new RequestServedFromCacheEvent(values);
        }
        /**
         * Request identifier.
         * @return the protocol field value
         */
        public Network.RequestId requestId() {
            return require(REQUEST_ID);
        }
        /**
         * Request identifier.
         * @param requestId field value
         * @return this model
         */
        public RequestServedFromCacheEvent requestId(Network.RequestId requestId) {
            set("requestId", requestId);
            return this;
        }
    }
    /**
     * Fired when page is about to send HTTP request.
     */
    public static final class RequestWillBeSentEvent extends CdpObject {
        public RequestWillBeSentEvent() {}
        private RequestWillBeSentEvent(Map<String, Object> values) { super(values); }
        public static RequestWillBeSentEvent fromMap(Map<String, Object> values) {
            return new RequestWillBeSentEvent(values);
        }
        /**
         * Request identifier.
         * @return the protocol field value
         */
        public Network.RequestId requestId() {
            return require(REQUEST_ID);
        }
        /**
         * Loader identifier. Empty string if the request is fetched from worker.
         * @return the protocol field value
         */
        public Network.LoaderId loaderId() {
            return require(LOADER_ID);
        }
        /**
         * URL of the document this request is loaded for.
         * @return the protocol field value
         */
        public String documentURL() {
            return requireString("documentURL");
        }
        /**
         * Request data.
         * @return the protocol field value
         */
        public Network.Request request() {
            return require(REQUEST);
        }
        /**
         * Timestamp.
         * @return the protocol field value
         */
        public Network.MonotonicTime timestamp() {
            return require(TIMESTAMP_2);
        }
        /**
         * Timestamp.
         * @return the protocol field value
         */
        public Network.TimeSinceEpoch wallTime() {
            return require(WALL_TIME);
        }
        /**
         * Request initiator.
         * @return the protocol field value
         */
        public Network.Initiator initiator() {
            return require(INITIATOR);
        }
        /**
         * In the case that redirectResponse is populated, this flag indicates whether requestWillBeSentExtraInfo and responseReceivedExtraInfo events will be or were emitted for the request which was just redirected.
         * <p><b>Experimental:</b> this part of CDP may change without notice.
         * @return the protocol field value
         */
        public boolean redirectHasExtraInfo() {
            return requireBoolean("redirectHasExtraInfo");
        }
        /**
         * Redirect response data.
         * @return the protocol field value, empty when absent
         */
        public Optional<Network.Response> redirectResponse() {
            return optional(REDIRECT_RESPONSE);
        }
        /**
         * Type of this resource.
         * @return the protocol field value, empty when absent
         */
        public Optional<Network.ResourceType> type() {
            return optional(TYPE);
        }
        /**
         * Frame identifier.
         * @return the protocol field value, empty when absent
         */
        public Optional<Page.FrameId> frameId() {
            return optional(FRAME_ID);
        }
        /**
         * Whether the request is initiated by a user gesture. Defaults to false.
         * @return the protocol field value, empty when absent
         */
        public Optional<Boolean> hasUserGesture() {
            return optionalBoolean("hasUserGesture");
        }
        /**
         * The render-blocking behavior of the request.
         * <p><b>Experimental:</b> this part of CDP may change without notice.
         * @return the protocol field value, empty when absent
         */
        public Optional<Network.RenderBlockingBehavior> renderBlockingBehavior() {
            return optional(RENDER_BLOCKING_BEHAVIOR);
        }
        /**
         * Request identifier.
         * @param requestId field value
         * @return this model
         */
        public RequestWillBeSentEvent requestId(Network.RequestId requestId) {
            set("requestId", requestId);
            return this;
        }
        /**
         * Loader identifier. Empty string if the request is fetched from worker.
         * @param loaderId field value
         * @return this model
         */
        public RequestWillBeSentEvent loaderId(Network.LoaderId loaderId) {
            set("loaderId", loaderId);
            return this;
        }
        /**
         * URL of the document this request is loaded for.
         * @param documentURL field value
         * @return this model
         */
        public RequestWillBeSentEvent documentURL(String documentURL) {
            set("documentURL", documentURL);
            return this;
        }
        /**
         * Request data.
         * @param request field value
         * @return this model
         */
        public RequestWillBeSentEvent request(Network.Request request) {
            set("request", request);
            return this;
        }
        /**
         * Timestamp.
         * @param timestamp field value
         * @return this model
         */
        public RequestWillBeSentEvent timestamp(Network.MonotonicTime timestamp) {
            set("timestamp", timestamp);
            return this;
        }
        /**
         * Timestamp.
         * @param wallTime field value
         * @return this model
         */
        public RequestWillBeSentEvent wallTime(Network.TimeSinceEpoch wallTime) {
            set("wallTime", wallTime);
            return this;
        }
        /**
         * Request initiator.
         * @param initiator field value
         * @return this model
         */
        public RequestWillBeSentEvent initiator(Network.Initiator initiator) {
            set("initiator", initiator);
            return this;
        }
        /**
         * In the case that redirectResponse is populated, this flag indicates whether requestWillBeSentExtraInfo and responseReceivedExtraInfo events will be or were emitted for the request which was just redirected.
         * <p><b>Experimental:</b> this part of CDP may change without notice.
         * @param redirectHasExtraInfo field value
         * @return this model
         */
        public RequestWillBeSentEvent redirectHasExtraInfo(boolean redirectHasExtraInfo) {
            set("redirectHasExtraInfo", redirectHasExtraInfo);
            return this;
        }
        /**
         * Redirect response data.
         * @param redirectResponse field value
         * @return this model
         */
        public RequestWillBeSentEvent redirectResponse(Network.Response redirectResponse) {
            set("redirectResponse", redirectResponse);
            return this;
        }
        /**
         * Type of this resource.
         * @param type field value
         * @return this model
         */
        public RequestWillBeSentEvent type(Network.ResourceType type) {
            set("type", type);
            return this;
        }
        /**
         * Frame identifier.
         * @param frameId field value
         * @return this model
         */
        public RequestWillBeSentEvent frameId(Page.FrameId frameId) {
            set("frameId", frameId);
            return this;
        }
        /**
         * Whether the request is initiated by a user gesture. Defaults to false.
         * @param hasUserGesture field value
         * @return this model
         */
        public RequestWillBeSentEvent hasUserGesture(boolean hasUserGesture) {
            set("hasUserGesture", hasUserGesture);
            return this;
        }
        /**
         * The render-blocking behavior of the request.
         * <p><b>Experimental:</b> this part of CDP may change without notice.
         * @param renderBlockingBehavior field value
         * @return this model
         */
        public RequestWillBeSentEvent renderBlockingBehavior(Network.RenderBlockingBehavior renderBlockingBehavior) {
            set("renderBlockingBehavior", renderBlockingBehavior);
            return this;
        }
    }
    /**
     * Fired when resource loading priority is changed
     * <p><b>Experimental:</b> this part of CDP may change without notice.
     */
    public static final class ResourceChangedPriorityEvent extends CdpObject {
        public ResourceChangedPriorityEvent() {}
        private ResourceChangedPriorityEvent(Map<String, Object> values) { super(values); }
        public static ResourceChangedPriorityEvent fromMap(Map<String, Object> values) {
            return new ResourceChangedPriorityEvent(values);
        }
        /**
         * Request identifier.
         * @return the protocol field value
         */
        public Network.RequestId requestId() {
            return require(REQUEST_ID);
        }
        /**
         * New priority
         * @return the protocol field value
         */
        public Network.ResourcePriority newPriority() {
            return require(NEW_PRIORITY);
        }
        /**
         * Timestamp.
         * @return the protocol field value
         */
        public Network.MonotonicTime timestamp() {
            return require(TIMESTAMP_2);
        }
        /**
         * Request identifier.
         * @param requestId field value
         * @return this model
         */
        public ResourceChangedPriorityEvent requestId(Network.RequestId requestId) {
            set("requestId", requestId);
            return this;
        }
        /**
         * New priority
         * @param newPriority field value
         * @return this model
         */
        public ResourceChangedPriorityEvent newPriority(Network.ResourcePriority newPriority) {
            set("newPriority", newPriority);
            return this;
        }
        /**
         * Timestamp.
         * @param timestamp field value
         * @return this model
         */
        public ResourceChangedPriorityEvent timestamp(Network.MonotonicTime timestamp) {
            set("timestamp", timestamp);
            return this;
        }
    }
    /**
     * Fired when a signed exchange was received over the network
     * <p><b>Experimental:</b> this part of CDP may change without notice.
     */
    public static final class SignedExchangeReceivedEvent extends CdpObject {
        public SignedExchangeReceivedEvent() {}
        private SignedExchangeReceivedEvent(Map<String, Object> values) { super(values); }
        public static SignedExchangeReceivedEvent fromMap(Map<String, Object> values) {
            return new SignedExchangeReceivedEvent(values);
        }
        /**
         * Request identifier.
         * @return the protocol field value
         */
        public Network.RequestId requestId() {
            return require(REQUEST_ID);
        }
        /**
         * Information about the signed exchange response.
         * @return the protocol field value
         */
        public Network.SignedExchangeInfo info() {
            return require(INFO);
        }
        /**
         * Request identifier.
         * @param requestId field value
         * @return this model
         */
        public SignedExchangeReceivedEvent requestId(Network.RequestId requestId) {
            set("requestId", requestId);
            return this;
        }
        /**
         * Information about the signed exchange response.
         * @param info field value
         * @return this model
         */
        public SignedExchangeReceivedEvent info(Network.SignedExchangeInfo info) {
            set("info", info);
            return this;
        }
    }
    /**
     * Fired when HTTP response is available.
     */
    public static final class ResponseReceivedEvent extends CdpObject {
        public ResponseReceivedEvent() {}
        private ResponseReceivedEvent(Map<String, Object> values) { super(values); }
        public static ResponseReceivedEvent fromMap(Map<String, Object> values) {
            return new ResponseReceivedEvent(values);
        }
        /**
         * Request identifier.
         * @return the protocol field value
         */
        public Network.RequestId requestId() {
            return require(REQUEST_ID);
        }
        /**
         * Loader identifier. Empty string if the request is fetched from worker.
         * @return the protocol field value
         */
        public Network.LoaderId loaderId() {
            return require(LOADER_ID);
        }
        /**
         * Timestamp.
         * @return the protocol field value
         */
        public Network.MonotonicTime timestamp() {
            return require(TIMESTAMP_2);
        }
        /**
         * Resource type.
         * @return the protocol field value
         */
        public Network.ResourceType type() {
            return require(TYPE);
        }
        /**
         * Response data.
         * @return the protocol field value
         */
        public Network.Response response() {
            return require(RESPONSE);
        }
        /**
         * Indicates whether requestWillBeSentExtraInfo and responseReceivedExtraInfo events will be or were emitted for this request.
         * <p><b>Experimental:</b> this part of CDP may change without notice.
         * @return the protocol field value
         */
        public boolean hasExtraInfo() {
            return requireBoolean("hasExtraInfo");
        }
        /**
         * Frame identifier.
         * @return the protocol field value, empty when absent
         */
        public Optional<Page.FrameId> frameId() {
            return optional(FRAME_ID);
        }
        /**
         * Request identifier.
         * @param requestId field value
         * @return this model
         */
        public ResponseReceivedEvent requestId(Network.RequestId requestId) {
            set("requestId", requestId);
            return this;
        }
        /**
         * Loader identifier. Empty string if the request is fetched from worker.
         * @param loaderId field value
         * @return this model
         */
        public ResponseReceivedEvent loaderId(Network.LoaderId loaderId) {
            set("loaderId", loaderId);
            return this;
        }
        /**
         * Timestamp.
         * @param timestamp field value
         * @return this model
         */
        public ResponseReceivedEvent timestamp(Network.MonotonicTime timestamp) {
            set("timestamp", timestamp);
            return this;
        }
        /**
         * Resource type.
         * @param type field value
         * @return this model
         */
        public ResponseReceivedEvent type(Network.ResourceType type) {
            set("type", type);
            return this;
        }
        /**
         * Response data.
         * @param response field value
         * @return this model
         */
        public ResponseReceivedEvent response(Network.Response response) {
            set("response", response);
            return this;
        }
        /**
         * Indicates whether requestWillBeSentExtraInfo and responseReceivedExtraInfo events will be or were emitted for this request.
         * <p><b>Experimental:</b> this part of CDP may change without notice.
         * @param hasExtraInfo field value
         * @return this model
         */
        public ResponseReceivedEvent hasExtraInfo(boolean hasExtraInfo) {
            set("hasExtraInfo", hasExtraInfo);
            return this;
        }
        /**
         * Frame identifier.
         * @param frameId field value
         * @return this model
         */
        public ResponseReceivedEvent frameId(Page.FrameId frameId) {
            set("frameId", frameId);
            return this;
        }
    }
    /**
     * Fired when WebSocket is closed.
     */
    public static final class WebSocketClosedEvent extends CdpObject {
        public WebSocketClosedEvent() {}
        private WebSocketClosedEvent(Map<String, Object> values) { super(values); }
        public static WebSocketClosedEvent fromMap(Map<String, Object> values) {
            return new WebSocketClosedEvent(values);
        }
        /**
         * Request identifier.
         * @return the protocol field value
         */
        public Network.RequestId requestId() {
            return require(REQUEST_ID);
        }
        /**
         * Timestamp.
         * @return the protocol field value
         */
        public Network.MonotonicTime timestamp() {
            return require(TIMESTAMP_2);
        }
        /**
         * Request identifier.
         * @param requestId field value
         * @return this model
         */
        public WebSocketClosedEvent requestId(Network.RequestId requestId) {
            set("requestId", requestId);
            return this;
        }
        /**
         * Timestamp.
         * @param timestamp field value
         * @return this model
         */
        public WebSocketClosedEvent timestamp(Network.MonotonicTime timestamp) {
            set("timestamp", timestamp);
            return this;
        }
    }
    /**
     * Fired upon WebSocket creation.
     */
    public static final class WebSocketCreatedEvent extends CdpObject {
        public WebSocketCreatedEvent() {}
        private WebSocketCreatedEvent(Map<String, Object> values) { super(values); }
        public static WebSocketCreatedEvent fromMap(Map<String, Object> values) {
            return new WebSocketCreatedEvent(values);
        }
        /**
         * Request identifier.
         * @return the protocol field value
         */
        public Network.RequestId requestId() {
            return require(REQUEST_ID);
        }
        /**
         * WebSocket request URL.
         * @return the protocol field value
         */
        public String url() {
            return requireString("url");
        }
        /**
         * Request initiator.
         * @return the protocol field value, empty when absent
         */
        public Optional<Network.Initiator> initiator() {
            return optional(INITIATOR);
        }
        /**
         * Request identifier.
         * @param requestId field value
         * @return this model
         */
        public WebSocketCreatedEvent requestId(Network.RequestId requestId) {
            set("requestId", requestId);
            return this;
        }
        /**
         * WebSocket request URL.
         * @param url field value
         * @return this model
         */
        public WebSocketCreatedEvent url(String url) {
            set("url", url);
            return this;
        }
        /**
         * Request initiator.
         * @param initiator field value
         * @return this model
         */
        public WebSocketCreatedEvent initiator(Network.Initiator initiator) {
            set("initiator", initiator);
            return this;
        }
    }
    /**
     * Fired when WebSocket message error occurs.
     */
    public static final class WebSocketFrameErrorEvent extends CdpObject {
        public WebSocketFrameErrorEvent() {}
        private WebSocketFrameErrorEvent(Map<String, Object> values) { super(values); }
        public static WebSocketFrameErrorEvent fromMap(Map<String, Object> values) {
            return new WebSocketFrameErrorEvent(values);
        }
        /**
         * Request identifier.
         * @return the protocol field value
         */
        public Network.RequestId requestId() {
            return require(REQUEST_ID);
        }
        /**
         * Timestamp.
         * @return the protocol field value
         */
        public Network.MonotonicTime timestamp() {
            return require(TIMESTAMP_2);
        }
        /**
         * WebSocket error message.
         * @return the protocol field value
         */
        public String errorMessage() {
            return requireString("errorMessage");
        }
        /**
         * Request identifier.
         * @param requestId field value
         * @return this model
         */
        public WebSocketFrameErrorEvent requestId(Network.RequestId requestId) {
            set("requestId", requestId);
            return this;
        }
        /**
         * Timestamp.
         * @param timestamp field value
         * @return this model
         */
        public WebSocketFrameErrorEvent timestamp(Network.MonotonicTime timestamp) {
            set("timestamp", timestamp);
            return this;
        }
        /**
         * WebSocket error message.
         * @param errorMessage field value
         * @return this model
         */
        public WebSocketFrameErrorEvent errorMessage(String errorMessage) {
            set("errorMessage", errorMessage);
            return this;
        }
    }
    /**
     * Fired when WebSocket message is received.
     */
    public static final class WebSocketFrameReceivedEvent extends CdpObject {
        public WebSocketFrameReceivedEvent() {}
        private WebSocketFrameReceivedEvent(Map<String, Object> values) { super(values); }
        public static WebSocketFrameReceivedEvent fromMap(Map<String, Object> values) {
            return new WebSocketFrameReceivedEvent(values);
        }
        /**
         * Request identifier.
         * @return the protocol field value
         */
        public Network.RequestId requestId() {
            return require(REQUEST_ID);
        }
        /**
         * Timestamp.
         * @return the protocol field value
         */
        public Network.MonotonicTime timestamp() {
            return require(TIMESTAMP_2);
        }
        /**
         * WebSocket response data.
         * @return the protocol field value
         */
        public Network.WebSocketFrame response() {
            return require(RESPONSE_3);
        }
        /**
         * Request identifier.
         * @param requestId field value
         * @return this model
         */
        public WebSocketFrameReceivedEvent requestId(Network.RequestId requestId) {
            set("requestId", requestId);
            return this;
        }
        /**
         * Timestamp.
         * @param timestamp field value
         * @return this model
         */
        public WebSocketFrameReceivedEvent timestamp(Network.MonotonicTime timestamp) {
            set("timestamp", timestamp);
            return this;
        }
        /**
         * WebSocket response data.
         * @param response field value
         * @return this model
         */
        public WebSocketFrameReceivedEvent response(Network.WebSocketFrame response) {
            set("response", response);
            return this;
        }
    }
    /**
     * Fired when WebSocket message is sent.
     */
    public static final class WebSocketFrameSentEvent extends CdpObject {
        public WebSocketFrameSentEvent() {}
        private WebSocketFrameSentEvent(Map<String, Object> values) { super(values); }
        public static WebSocketFrameSentEvent fromMap(Map<String, Object> values) {
            return new WebSocketFrameSentEvent(values);
        }
        /**
         * Request identifier.
         * @return the protocol field value
         */
        public Network.RequestId requestId() {
            return require(REQUEST_ID);
        }
        /**
         * Timestamp.
         * @return the protocol field value
         */
        public Network.MonotonicTime timestamp() {
            return require(TIMESTAMP_2);
        }
        /**
         * WebSocket response data.
         * @return the protocol field value
         */
        public Network.WebSocketFrame response() {
            return require(RESPONSE_3);
        }
        /**
         * Request identifier.
         * @param requestId field value
         * @return this model
         */
        public WebSocketFrameSentEvent requestId(Network.RequestId requestId) {
            set("requestId", requestId);
            return this;
        }
        /**
         * Timestamp.
         * @param timestamp field value
         * @return this model
         */
        public WebSocketFrameSentEvent timestamp(Network.MonotonicTime timestamp) {
            set("timestamp", timestamp);
            return this;
        }
        /**
         * WebSocket response data.
         * @param response field value
         * @return this model
         */
        public WebSocketFrameSentEvent response(Network.WebSocketFrame response) {
            set("response", response);
            return this;
        }
    }
    /**
     * Fired when WebSocket handshake response becomes available.
     */
    public static final class WebSocketHandshakeResponseReceivedEvent extends CdpObject {
        public WebSocketHandshakeResponseReceivedEvent() {}
        private WebSocketHandshakeResponseReceivedEvent(Map<String, Object> values) { super(values); }
        public static WebSocketHandshakeResponseReceivedEvent fromMap(Map<String, Object> values) {
            return new WebSocketHandshakeResponseReceivedEvent(values);
        }
        /**
         * Request identifier.
         * @return the protocol field value
         */
        public Network.RequestId requestId() {
            return require(REQUEST_ID);
        }
        /**
         * Timestamp.
         * @return the protocol field value
         */
        public Network.MonotonicTime timestamp() {
            return require(TIMESTAMP_2);
        }
        /**
         * WebSocket response data.
         * @return the protocol field value
         */
        public Network.WebSocketResponse response() {
            return require(RESPONSE_4);
        }
        /**
         * Request identifier.
         * @param requestId field value
         * @return this model
         */
        public WebSocketHandshakeResponseReceivedEvent requestId(Network.RequestId requestId) {
            set("requestId", requestId);
            return this;
        }
        /**
         * Timestamp.
         * @param timestamp field value
         * @return this model
         */
        public WebSocketHandshakeResponseReceivedEvent timestamp(Network.MonotonicTime timestamp) {
            set("timestamp", timestamp);
            return this;
        }
        /**
         * WebSocket response data.
         * @param response field value
         * @return this model
         */
        public WebSocketHandshakeResponseReceivedEvent response(Network.WebSocketResponse response) {
            set("response", response);
            return this;
        }
    }
    /**
     * Fired when WebSocket is about to initiate handshake.
     */
    public static final class WebSocketWillSendHandshakeRequestEvent extends CdpObject {
        public WebSocketWillSendHandshakeRequestEvent() {}
        private WebSocketWillSendHandshakeRequestEvent(Map<String, Object> values) { super(values); }
        public static WebSocketWillSendHandshakeRequestEvent fromMap(Map<String, Object> values) {
            return new WebSocketWillSendHandshakeRequestEvent(values);
        }
        /**
         * Request identifier.
         * @return the protocol field value
         */
        public Network.RequestId requestId() {
            return require(REQUEST_ID);
        }
        /**
         * Timestamp.
         * @return the protocol field value
         */
        public Network.MonotonicTime timestamp() {
            return require(TIMESTAMP_2);
        }
        /**
         * UTC Timestamp.
         * @return the protocol field value
         */
        public Network.TimeSinceEpoch wallTime() {
            return require(WALL_TIME);
        }
        /**
         * WebSocket request data.
         * @return the protocol field value
         */
        public Network.WebSocketRequest request() {
            return require(REQUEST_2);
        }
        /**
         * Request identifier.
         * @param requestId field value
         * @return this model
         */
        public WebSocketWillSendHandshakeRequestEvent requestId(Network.RequestId requestId) {
            set("requestId", requestId);
            return this;
        }
        /**
         * Timestamp.
         * @param timestamp field value
         * @return this model
         */
        public WebSocketWillSendHandshakeRequestEvent timestamp(Network.MonotonicTime timestamp) {
            set("timestamp", timestamp);
            return this;
        }
        /**
         * UTC Timestamp.
         * @param wallTime field value
         * @return this model
         */
        public WebSocketWillSendHandshakeRequestEvent wallTime(Network.TimeSinceEpoch wallTime) {
            set("wallTime", wallTime);
            return this;
        }
        /**
         * WebSocket request data.
         * @param request field value
         * @return this model
         */
        public WebSocketWillSendHandshakeRequestEvent request(Network.WebSocketRequest request) {
            set("request", request);
            return this;
        }
    }
    /**
     * Fired upon WebTransport creation.
     */
    public static final class WebTransportCreatedEvent extends CdpObject {
        public WebTransportCreatedEvent() {}
        private WebTransportCreatedEvent(Map<String, Object> values) { super(values); }
        public static WebTransportCreatedEvent fromMap(Map<String, Object> values) {
            return new WebTransportCreatedEvent(values);
        }
        /**
         * WebTransport identifier.
         * @return the protocol field value
         */
        public Network.RequestId transportId() {
            return require(TRANSPORT_ID);
        }
        /**
         * WebTransport request URL.
         * @return the protocol field value
         */
        public String url() {
            return requireString("url");
        }
        /**
         * Timestamp.
         * @return the protocol field value
         */
        public Network.MonotonicTime timestamp() {
            return require(TIMESTAMP_2);
        }
        /**
         * Request initiator.
         * @return the protocol field value, empty when absent
         */
        public Optional<Network.Initiator> initiator() {
            return optional(INITIATOR);
        }
        /**
         * WebTransport identifier.
         * @param transportId field value
         * @return this model
         */
        public WebTransportCreatedEvent transportId(Network.RequestId transportId) {
            set("transportId", transportId);
            return this;
        }
        /**
         * WebTransport request URL.
         * @param url field value
         * @return this model
         */
        public WebTransportCreatedEvent url(String url) {
            set("url", url);
            return this;
        }
        /**
         * Timestamp.
         * @param timestamp field value
         * @return this model
         */
        public WebTransportCreatedEvent timestamp(Network.MonotonicTime timestamp) {
            set("timestamp", timestamp);
            return this;
        }
        /**
         * Request initiator.
         * @param initiator field value
         * @return this model
         */
        public WebTransportCreatedEvent initiator(Network.Initiator initiator) {
            set("initiator", initiator);
            return this;
        }
    }
    /**
     * Fired when WebTransport handshake is finished.
     */
    public static final class WebTransportConnectionEstablishedEvent extends CdpObject {
        public WebTransportConnectionEstablishedEvent() {}
        private WebTransportConnectionEstablishedEvent(Map<String, Object> values) { super(values); }
        public static WebTransportConnectionEstablishedEvent fromMap(Map<String, Object> values) {
            return new WebTransportConnectionEstablishedEvent(values);
        }
        /**
         * WebTransport identifier.
         * @return the protocol field value
         */
        public Network.RequestId transportId() {
            return require(TRANSPORT_ID);
        }
        /**
         * Timestamp.
         * @return the protocol field value
         */
        public Network.MonotonicTime timestamp() {
            return require(TIMESTAMP_2);
        }
        /**
         * WebTransport identifier.
         * @param transportId field value
         * @return this model
         */
        public WebTransportConnectionEstablishedEvent transportId(Network.RequestId transportId) {
            set("transportId", transportId);
            return this;
        }
        /**
         * Timestamp.
         * @param timestamp field value
         * @return this model
         */
        public WebTransportConnectionEstablishedEvent timestamp(Network.MonotonicTime timestamp) {
            set("timestamp", timestamp);
            return this;
        }
    }
    /**
     * Fired when WebTransport is disposed.
     */
    public static final class WebTransportClosedEvent extends CdpObject {
        public WebTransportClosedEvent() {}
        private WebTransportClosedEvent(Map<String, Object> values) { super(values); }
        public static WebTransportClosedEvent fromMap(Map<String, Object> values) {
            return new WebTransportClosedEvent(values);
        }
        /**
         * WebTransport identifier.
         * @return the protocol field value
         */
        public Network.RequestId transportId() {
            return require(TRANSPORT_ID);
        }
        /**
         * Timestamp.
         * @return the protocol field value
         */
        public Network.MonotonicTime timestamp() {
            return require(TIMESTAMP_2);
        }
        /**
         * WebTransport identifier.
         * @param transportId field value
         * @return this model
         */
        public WebTransportClosedEvent transportId(Network.RequestId transportId) {
            set("transportId", transportId);
            return this;
        }
        /**
         * Timestamp.
         * @param timestamp field value
         * @return this model
         */
        public WebTransportClosedEvent timestamp(Network.MonotonicTime timestamp) {
            set("timestamp", timestamp);
            return this;
        }
    }
    /**
     * Fired upon direct_socket.TCPSocket creation.
     * <p><b>Experimental:</b> this part of CDP may change without notice.
     */
    public static final class DirectTCPSocketCreatedEvent extends CdpObject {
        public DirectTCPSocketCreatedEvent() {}
        private DirectTCPSocketCreatedEvent(Map<String, Object> values) { super(values); }
        public static DirectTCPSocketCreatedEvent fromMap(Map<String, Object> values) {
            return new DirectTCPSocketCreatedEvent(values);
        }
        /**
         * Returns the identifier field.
         * @return the protocol field value
         */
        public Network.RequestId identifier() {
            return require(IDENTIFIER);
        }
        /**
         * Returns the remoteAddr field.
         * @return the protocol field value
         */
        public String remoteAddr() {
            return requireString("remoteAddr");
        }
        /**
         * Unsigned int 16.
         * @return the protocol field value
         */
        public long remotePort() {
            return requireLong("remotePort");
        }
        /**
         * Returns the options field.
         * @return the protocol field value
         */
        public Network.DirectTCPSocketOptions options() {
            return require(OPTIONS_2);
        }
        /**
         * Returns the timestamp field.
         * @return the protocol field value
         */
        public Network.MonotonicTime timestamp() {
            return require(TIMESTAMP_2);
        }
        /**
         * Returns the initiator field.
         * @return the protocol field value, empty when absent
         */
        public Optional<Network.Initiator> initiator() {
            return optional(INITIATOR);
        }
        /**
         * Sets the identifier field.
         * @param identifier field value
         * @return this model
         */
        public DirectTCPSocketCreatedEvent identifier(Network.RequestId identifier) {
            set("identifier", identifier);
            return this;
        }
        /**
         * Sets the remoteAddr field.
         * @param remoteAddr field value
         * @return this model
         */
        public DirectTCPSocketCreatedEvent remoteAddr(String remoteAddr) {
            set("remoteAddr", remoteAddr);
            return this;
        }
        /**
         * Unsigned int 16.
         * @param remotePort field value
         * @return this model
         */
        public DirectTCPSocketCreatedEvent remotePort(long remotePort) {
            set("remotePort", remotePort);
            return this;
        }
        /**
         * Sets the options field.
         * @param options field value
         * @return this model
         */
        public DirectTCPSocketCreatedEvent options(Network.DirectTCPSocketOptions options) {
            set("options", options);
            return this;
        }
        /**
         * Sets the timestamp field.
         * @param timestamp field value
         * @return this model
         */
        public DirectTCPSocketCreatedEvent timestamp(Network.MonotonicTime timestamp) {
            set("timestamp", timestamp);
            return this;
        }
        /**
         * Sets the initiator field.
         * @param initiator field value
         * @return this model
         */
        public DirectTCPSocketCreatedEvent initiator(Network.Initiator initiator) {
            set("initiator", initiator);
            return this;
        }
    }
    /**
     * Fired when direct_socket.TCPSocket connection is opened.
     * <p><b>Experimental:</b> this part of CDP may change without notice.
     */
    public static final class DirectTCPSocketOpenedEvent extends CdpObject {
        public DirectTCPSocketOpenedEvent() {}
        private DirectTCPSocketOpenedEvent(Map<String, Object> values) { super(values); }
        public static DirectTCPSocketOpenedEvent fromMap(Map<String, Object> values) {
            return new DirectTCPSocketOpenedEvent(values);
        }
        /**
         * Returns the identifier field.
         * @return the protocol field value
         */
        public Network.RequestId identifier() {
            return require(IDENTIFIER);
        }
        /**
         * Returns the remoteAddr field.
         * @return the protocol field value
         */
        public String remoteAddr() {
            return requireString("remoteAddr");
        }
        /**
         * Expected to be unsigned integer.
         * @return the protocol field value
         */
        public long remotePort() {
            return requireLong("remotePort");
        }
        /**
         * Returns the timestamp field.
         * @return the protocol field value
         */
        public Network.MonotonicTime timestamp() {
            return require(TIMESTAMP_2);
        }
        /**
         * Returns the localAddr field.
         * @return the protocol field value, empty when absent
         */
        public Optional<String> localAddr() {
            return optionalString("localAddr");
        }
        /**
         * Expected to be unsigned integer.
         * @return the protocol field value, empty when absent
         */
        public OptionalLong localPort() {
            return optionalLong("localPort");
        }
        /**
         * Sets the identifier field.
         * @param identifier field value
         * @return this model
         */
        public DirectTCPSocketOpenedEvent identifier(Network.RequestId identifier) {
            set("identifier", identifier);
            return this;
        }
        /**
         * Sets the remoteAddr field.
         * @param remoteAddr field value
         * @return this model
         */
        public DirectTCPSocketOpenedEvent remoteAddr(String remoteAddr) {
            set("remoteAddr", remoteAddr);
            return this;
        }
        /**
         * Expected to be unsigned integer.
         * @param remotePort field value
         * @return this model
         */
        public DirectTCPSocketOpenedEvent remotePort(long remotePort) {
            set("remotePort", remotePort);
            return this;
        }
        /**
         * Sets the timestamp field.
         * @param timestamp field value
         * @return this model
         */
        public DirectTCPSocketOpenedEvent timestamp(Network.MonotonicTime timestamp) {
            set("timestamp", timestamp);
            return this;
        }
        /**
         * Sets the localAddr field.
         * @param localAddr field value
         * @return this model
         */
        public DirectTCPSocketOpenedEvent localAddr(String localAddr) {
            set("localAddr", localAddr);
            return this;
        }
        /**
         * Expected to be unsigned integer.
         * @param localPort field value
         * @return this model
         */
        public DirectTCPSocketOpenedEvent localPort(long localPort) {
            set("localPort", localPort);
            return this;
        }
    }
    /**
     * Fired when direct_socket.TCPSocket is aborted.
     * <p><b>Experimental:</b> this part of CDP may change without notice.
     */
    public static final class DirectTCPSocketAbortedEvent extends CdpObject {
        public DirectTCPSocketAbortedEvent() {}
        private DirectTCPSocketAbortedEvent(Map<String, Object> values) { super(values); }
        public static DirectTCPSocketAbortedEvent fromMap(Map<String, Object> values) {
            return new DirectTCPSocketAbortedEvent(values);
        }
        /**
         * Returns the identifier field.
         * @return the protocol field value
         */
        public Network.RequestId identifier() {
            return require(IDENTIFIER);
        }
        /**
         * Returns the errorMessage field.
         * @return the protocol field value
         */
        public String errorMessage() {
            return requireString("errorMessage");
        }
        /**
         * Returns the timestamp field.
         * @return the protocol field value
         */
        public Network.MonotonicTime timestamp() {
            return require(TIMESTAMP_2);
        }
        /**
         * Sets the identifier field.
         * @param identifier field value
         * @return this model
         */
        public DirectTCPSocketAbortedEvent identifier(Network.RequestId identifier) {
            set("identifier", identifier);
            return this;
        }
        /**
         * Sets the errorMessage field.
         * @param errorMessage field value
         * @return this model
         */
        public DirectTCPSocketAbortedEvent errorMessage(String errorMessage) {
            set("errorMessage", errorMessage);
            return this;
        }
        /**
         * Sets the timestamp field.
         * @param timestamp field value
         * @return this model
         */
        public DirectTCPSocketAbortedEvent timestamp(Network.MonotonicTime timestamp) {
            set("timestamp", timestamp);
            return this;
        }
    }
    /**
     * Fired when direct_socket.TCPSocket is closed.
     * <p><b>Experimental:</b> this part of CDP may change without notice.
     */
    public static final class DirectTCPSocketClosedEvent extends CdpObject {
        public DirectTCPSocketClosedEvent() {}
        private DirectTCPSocketClosedEvent(Map<String, Object> values) { super(values); }
        public static DirectTCPSocketClosedEvent fromMap(Map<String, Object> values) {
            return new DirectTCPSocketClosedEvent(values);
        }
        /**
         * Returns the identifier field.
         * @return the protocol field value
         */
        public Network.RequestId identifier() {
            return require(IDENTIFIER);
        }
        /**
         * Returns the timestamp field.
         * @return the protocol field value
         */
        public Network.MonotonicTime timestamp() {
            return require(TIMESTAMP_2);
        }
        /**
         * Sets the identifier field.
         * @param identifier field value
         * @return this model
         */
        public DirectTCPSocketClosedEvent identifier(Network.RequestId identifier) {
            set("identifier", identifier);
            return this;
        }
        /**
         * Sets the timestamp field.
         * @param timestamp field value
         * @return this model
         */
        public DirectTCPSocketClosedEvent timestamp(Network.MonotonicTime timestamp) {
            set("timestamp", timestamp);
            return this;
        }
    }
    /**
     * Fired when data is sent to tcp direct socket stream.
     * <p><b>Experimental:</b> this part of CDP may change without notice.
     */
    public static final class DirectTCPSocketChunkSentEvent extends CdpObject {
        public DirectTCPSocketChunkSentEvent() {}
        private DirectTCPSocketChunkSentEvent(Map<String, Object> values) { super(values); }
        public static DirectTCPSocketChunkSentEvent fromMap(Map<String, Object> values) {
            return new DirectTCPSocketChunkSentEvent(values);
        }
        /**
         * Returns the identifier field.
         * @return the protocol field value
         */
        public Network.RequestId identifier() {
            return require(IDENTIFIER);
        }
        /**
         * Returns the data field.
         * @return the protocol field value
         */
        public String data() {
            return requireString("data");
        }
        /**
         * Returns the timestamp field.
         * @return the protocol field value
         */
        public Network.MonotonicTime timestamp() {
            return require(TIMESTAMP_2);
        }
        /**
         * Sets the identifier field.
         * @param identifier field value
         * @return this model
         */
        public DirectTCPSocketChunkSentEvent identifier(Network.RequestId identifier) {
            set("identifier", identifier);
            return this;
        }
        /**
         * Sets the data field.
         * @param data field value
         * @return this model
         */
        public DirectTCPSocketChunkSentEvent data(String data) {
            set("data", data);
            return this;
        }
        /**
         * Sets the timestamp field.
         * @param timestamp field value
         * @return this model
         */
        public DirectTCPSocketChunkSentEvent timestamp(Network.MonotonicTime timestamp) {
            set("timestamp", timestamp);
            return this;
        }
    }
    /**
     * Fired when data is received from tcp direct socket stream.
     * <p><b>Experimental:</b> this part of CDP may change without notice.
     */
    public static final class DirectTCPSocketChunkReceivedEvent extends CdpObject {
        public DirectTCPSocketChunkReceivedEvent() {}
        private DirectTCPSocketChunkReceivedEvent(Map<String, Object> values) { super(values); }
        public static DirectTCPSocketChunkReceivedEvent fromMap(Map<String, Object> values) {
            return new DirectTCPSocketChunkReceivedEvent(values);
        }
        /**
         * Returns the identifier field.
         * @return the protocol field value
         */
        public Network.RequestId identifier() {
            return require(IDENTIFIER);
        }
        /**
         * Returns the data field.
         * @return the protocol field value
         */
        public String data() {
            return requireString("data");
        }
        /**
         * Returns the timestamp field.
         * @return the protocol field value
         */
        public Network.MonotonicTime timestamp() {
            return require(TIMESTAMP_2);
        }
        /**
         * Sets the identifier field.
         * @param identifier field value
         * @return this model
         */
        public DirectTCPSocketChunkReceivedEvent identifier(Network.RequestId identifier) {
            set("identifier", identifier);
            return this;
        }
        /**
         * Sets the data field.
         * @param data field value
         * @return this model
         */
        public DirectTCPSocketChunkReceivedEvent data(String data) {
            set("data", data);
            return this;
        }
        /**
         * Sets the timestamp field.
         * @param timestamp field value
         * @return this model
         */
        public DirectTCPSocketChunkReceivedEvent timestamp(Network.MonotonicTime timestamp) {
            set("timestamp", timestamp);
            return this;
        }
    }
    /**
     * Payload of the Network.directUDPSocketJoinedMulticastGroup event.
     * <p><b>Experimental:</b> this part of CDP may change without notice.
     */
    public static final class DirectUDPSocketJoinedMulticastGroupEvent extends CdpObject {
        public DirectUDPSocketJoinedMulticastGroupEvent() {}
        private DirectUDPSocketJoinedMulticastGroupEvent(Map<String, Object> values) { super(values); }
        public static DirectUDPSocketJoinedMulticastGroupEvent fromMap(Map<String, Object> values) {
            return new DirectUDPSocketJoinedMulticastGroupEvent(values);
        }
        /**
         * Returns the identifier field.
         * @return the protocol field value
         */
        public Network.RequestId identifier() {
            return require(IDENTIFIER);
        }
        /**
         * Returns the IPAddress field.
         * @return the protocol field value
         */
        public String IPAddress() {
            return requireString("IPAddress");
        }
        /**
         * Sets the identifier field.
         * @param identifier field value
         * @return this model
         */
        public DirectUDPSocketJoinedMulticastGroupEvent identifier(Network.RequestId identifier) {
            set("identifier", identifier);
            return this;
        }
        /**
         * Sets the IPAddress field.
         * @param IPAddress field value
         * @return this model
         */
        public DirectUDPSocketJoinedMulticastGroupEvent IPAddress(String IPAddress) {
            set("IPAddress", IPAddress);
            return this;
        }
    }
    /**
     * Payload of the Network.directUDPSocketLeftMulticastGroup event.
     * <p><b>Experimental:</b> this part of CDP may change without notice.
     */
    public static final class DirectUDPSocketLeftMulticastGroupEvent extends CdpObject {
        public DirectUDPSocketLeftMulticastGroupEvent() {}
        private DirectUDPSocketLeftMulticastGroupEvent(Map<String, Object> values) { super(values); }
        public static DirectUDPSocketLeftMulticastGroupEvent fromMap(Map<String, Object> values) {
            return new DirectUDPSocketLeftMulticastGroupEvent(values);
        }
        /**
         * Returns the identifier field.
         * @return the protocol field value
         */
        public Network.RequestId identifier() {
            return require(IDENTIFIER);
        }
        /**
         * Returns the IPAddress field.
         * @return the protocol field value
         */
        public String IPAddress() {
            return requireString("IPAddress");
        }
        /**
         * Sets the identifier field.
         * @param identifier field value
         * @return this model
         */
        public DirectUDPSocketLeftMulticastGroupEvent identifier(Network.RequestId identifier) {
            set("identifier", identifier);
            return this;
        }
        /**
         * Sets the IPAddress field.
         * @param IPAddress field value
         * @return this model
         */
        public DirectUDPSocketLeftMulticastGroupEvent IPAddress(String IPAddress) {
            set("IPAddress", IPAddress);
            return this;
        }
    }
    /**
     * Fired upon direct_socket.UDPSocket creation.
     * <p><b>Experimental:</b> this part of CDP may change without notice.
     */
    public static final class DirectUDPSocketCreatedEvent extends CdpObject {
        public DirectUDPSocketCreatedEvent() {}
        private DirectUDPSocketCreatedEvent(Map<String, Object> values) { super(values); }
        public static DirectUDPSocketCreatedEvent fromMap(Map<String, Object> values) {
            return new DirectUDPSocketCreatedEvent(values);
        }
        /**
         * Returns the identifier field.
         * @return the protocol field value
         */
        public Network.RequestId identifier() {
            return require(IDENTIFIER);
        }
        /**
         * Returns the options field.
         * @return the protocol field value
         */
        public Network.DirectUDPSocketOptions options() {
            return require(OPTIONS_3);
        }
        /**
         * Returns the timestamp field.
         * @return the protocol field value
         */
        public Network.MonotonicTime timestamp() {
            return require(TIMESTAMP_2);
        }
        /**
         * Returns the initiator field.
         * @return the protocol field value, empty when absent
         */
        public Optional<Network.Initiator> initiator() {
            return optional(INITIATOR);
        }
        /**
         * Sets the identifier field.
         * @param identifier field value
         * @return this model
         */
        public DirectUDPSocketCreatedEvent identifier(Network.RequestId identifier) {
            set("identifier", identifier);
            return this;
        }
        /**
         * Sets the options field.
         * @param options field value
         * @return this model
         */
        public DirectUDPSocketCreatedEvent options(Network.DirectUDPSocketOptions options) {
            set("options", options);
            return this;
        }
        /**
         * Sets the timestamp field.
         * @param timestamp field value
         * @return this model
         */
        public DirectUDPSocketCreatedEvent timestamp(Network.MonotonicTime timestamp) {
            set("timestamp", timestamp);
            return this;
        }
        /**
         * Sets the initiator field.
         * @param initiator field value
         * @return this model
         */
        public DirectUDPSocketCreatedEvent initiator(Network.Initiator initiator) {
            set("initiator", initiator);
            return this;
        }
    }
    /**
     * Fired when direct_socket.UDPSocket connection is opened.
     * <p><b>Experimental:</b> this part of CDP may change without notice.
     */
    public static final class DirectUDPSocketOpenedEvent extends CdpObject {
        public DirectUDPSocketOpenedEvent() {}
        private DirectUDPSocketOpenedEvent(Map<String, Object> values) { super(values); }
        public static DirectUDPSocketOpenedEvent fromMap(Map<String, Object> values) {
            return new DirectUDPSocketOpenedEvent(values);
        }
        /**
         * Returns the identifier field.
         * @return the protocol field value
         */
        public Network.RequestId identifier() {
            return require(IDENTIFIER);
        }
        /**
         * Returns the localAddr field.
         * @return the protocol field value
         */
        public String localAddr() {
            return requireString("localAddr");
        }
        /**
         * Expected to be unsigned integer.
         * @return the protocol field value
         */
        public long localPort() {
            return requireLong("localPort");
        }
        /**
         * Returns the timestamp field.
         * @return the protocol field value
         */
        public Network.MonotonicTime timestamp() {
            return require(TIMESTAMP_2);
        }
        /**
         * Returns the remoteAddr field.
         * @return the protocol field value, empty when absent
         */
        public Optional<String> remoteAddr() {
            return optionalString("remoteAddr");
        }
        /**
         * Expected to be unsigned integer.
         * @return the protocol field value, empty when absent
         */
        public OptionalLong remotePort() {
            return optionalLong("remotePort");
        }
        /**
         * Sets the identifier field.
         * @param identifier field value
         * @return this model
         */
        public DirectUDPSocketOpenedEvent identifier(Network.RequestId identifier) {
            set("identifier", identifier);
            return this;
        }
        /**
         * Sets the localAddr field.
         * @param localAddr field value
         * @return this model
         */
        public DirectUDPSocketOpenedEvent localAddr(String localAddr) {
            set("localAddr", localAddr);
            return this;
        }
        /**
         * Expected to be unsigned integer.
         * @param localPort field value
         * @return this model
         */
        public DirectUDPSocketOpenedEvent localPort(long localPort) {
            set("localPort", localPort);
            return this;
        }
        /**
         * Sets the timestamp field.
         * @param timestamp field value
         * @return this model
         */
        public DirectUDPSocketOpenedEvent timestamp(Network.MonotonicTime timestamp) {
            set("timestamp", timestamp);
            return this;
        }
        /**
         * Sets the remoteAddr field.
         * @param remoteAddr field value
         * @return this model
         */
        public DirectUDPSocketOpenedEvent remoteAddr(String remoteAddr) {
            set("remoteAddr", remoteAddr);
            return this;
        }
        /**
         * Expected to be unsigned integer.
         * @param remotePort field value
         * @return this model
         */
        public DirectUDPSocketOpenedEvent remotePort(long remotePort) {
            set("remotePort", remotePort);
            return this;
        }
    }
    /**
     * Fired when direct_socket.UDPSocket is aborted.
     * <p><b>Experimental:</b> this part of CDP may change without notice.
     */
    public static final class DirectUDPSocketAbortedEvent extends CdpObject {
        public DirectUDPSocketAbortedEvent() {}
        private DirectUDPSocketAbortedEvent(Map<String, Object> values) { super(values); }
        public static DirectUDPSocketAbortedEvent fromMap(Map<String, Object> values) {
            return new DirectUDPSocketAbortedEvent(values);
        }
        /**
         * Returns the identifier field.
         * @return the protocol field value
         */
        public Network.RequestId identifier() {
            return require(IDENTIFIER);
        }
        /**
         * Returns the errorMessage field.
         * @return the protocol field value
         */
        public String errorMessage() {
            return requireString("errorMessage");
        }
        /**
         * Returns the timestamp field.
         * @return the protocol field value
         */
        public Network.MonotonicTime timestamp() {
            return require(TIMESTAMP_2);
        }
        /**
         * Sets the identifier field.
         * @param identifier field value
         * @return this model
         */
        public DirectUDPSocketAbortedEvent identifier(Network.RequestId identifier) {
            set("identifier", identifier);
            return this;
        }
        /**
         * Sets the errorMessage field.
         * @param errorMessage field value
         * @return this model
         */
        public DirectUDPSocketAbortedEvent errorMessage(String errorMessage) {
            set("errorMessage", errorMessage);
            return this;
        }
        /**
         * Sets the timestamp field.
         * @param timestamp field value
         * @return this model
         */
        public DirectUDPSocketAbortedEvent timestamp(Network.MonotonicTime timestamp) {
            set("timestamp", timestamp);
            return this;
        }
    }
    /**
     * Fired when direct_socket.UDPSocket is closed.
     * <p><b>Experimental:</b> this part of CDP may change without notice.
     */
    public static final class DirectUDPSocketClosedEvent extends CdpObject {
        public DirectUDPSocketClosedEvent() {}
        private DirectUDPSocketClosedEvent(Map<String, Object> values) { super(values); }
        public static DirectUDPSocketClosedEvent fromMap(Map<String, Object> values) {
            return new DirectUDPSocketClosedEvent(values);
        }
        /**
         * Returns the identifier field.
         * @return the protocol field value
         */
        public Network.RequestId identifier() {
            return require(IDENTIFIER);
        }
        /**
         * Returns the timestamp field.
         * @return the protocol field value
         */
        public Network.MonotonicTime timestamp() {
            return require(TIMESTAMP_2);
        }
        /**
         * Sets the identifier field.
         * @param identifier field value
         * @return this model
         */
        public DirectUDPSocketClosedEvent identifier(Network.RequestId identifier) {
            set("identifier", identifier);
            return this;
        }
        /**
         * Sets the timestamp field.
         * @param timestamp field value
         * @return this model
         */
        public DirectUDPSocketClosedEvent timestamp(Network.MonotonicTime timestamp) {
            set("timestamp", timestamp);
            return this;
        }
    }
    /**
     * Fired when message is sent to udp direct socket stream.
     * <p><b>Experimental:</b> this part of CDP may change without notice.
     */
    public static final class DirectUDPSocketChunkSentEvent extends CdpObject {
        public DirectUDPSocketChunkSentEvent() {}
        private DirectUDPSocketChunkSentEvent(Map<String, Object> values) { super(values); }
        public static DirectUDPSocketChunkSentEvent fromMap(Map<String, Object> values) {
            return new DirectUDPSocketChunkSentEvent(values);
        }
        /**
         * Returns the identifier field.
         * @return the protocol field value
         */
        public Network.RequestId identifier() {
            return require(IDENTIFIER);
        }
        /**
         * Returns the message field.
         * @return the protocol field value
         */
        public Network.DirectUDPMessage message() {
            return require(MESSAGE);
        }
        /**
         * Returns the timestamp field.
         * @return the protocol field value
         */
        public Network.MonotonicTime timestamp() {
            return require(TIMESTAMP_2);
        }
        /**
         * Sets the identifier field.
         * @param identifier field value
         * @return this model
         */
        public DirectUDPSocketChunkSentEvent identifier(Network.RequestId identifier) {
            set("identifier", identifier);
            return this;
        }
        /**
         * Sets the message field.
         * @param message field value
         * @return this model
         */
        public DirectUDPSocketChunkSentEvent message(Network.DirectUDPMessage message) {
            set("message", message);
            return this;
        }
        /**
         * Sets the timestamp field.
         * @param timestamp field value
         * @return this model
         */
        public DirectUDPSocketChunkSentEvent timestamp(Network.MonotonicTime timestamp) {
            set("timestamp", timestamp);
            return this;
        }
    }
    /**
     * Fired when message is received from udp direct socket stream.
     * <p><b>Experimental:</b> this part of CDP may change without notice.
     */
    public static final class DirectUDPSocketChunkReceivedEvent extends CdpObject {
        public DirectUDPSocketChunkReceivedEvent() {}
        private DirectUDPSocketChunkReceivedEvent(Map<String, Object> values) { super(values); }
        public static DirectUDPSocketChunkReceivedEvent fromMap(Map<String, Object> values) {
            return new DirectUDPSocketChunkReceivedEvent(values);
        }
        /**
         * Returns the identifier field.
         * @return the protocol field value
         */
        public Network.RequestId identifier() {
            return require(IDENTIFIER);
        }
        /**
         * Returns the message field.
         * @return the protocol field value
         */
        public Network.DirectUDPMessage message() {
            return require(MESSAGE);
        }
        /**
         * Returns the timestamp field.
         * @return the protocol field value
         */
        public Network.MonotonicTime timestamp() {
            return require(TIMESTAMP_2);
        }
        /**
         * Sets the identifier field.
         * @param identifier field value
         * @return this model
         */
        public DirectUDPSocketChunkReceivedEvent identifier(Network.RequestId identifier) {
            set("identifier", identifier);
            return this;
        }
        /**
         * Sets the message field.
         * @param message field value
         * @return this model
         */
        public DirectUDPSocketChunkReceivedEvent message(Network.DirectUDPMessage message) {
            set("message", message);
            return this;
        }
        /**
         * Sets the timestamp field.
         * @param timestamp field value
         * @return this model
         */
        public DirectUDPSocketChunkReceivedEvent timestamp(Network.MonotonicTime timestamp) {
            set("timestamp", timestamp);
            return this;
        }
    }
    /**
     * Fired when additional information about a requestWillBeSent event is available from the network stack. Not every requestWillBeSent event will have an additional requestWillBeSentExtraInfo fired for it, and there is no guarantee whether requestWillBeSent or requestWillBeSentExtraInfo will be fired first for the same request.
     * <p><b>Experimental:</b> this part of CDP may change without notice.
     */
    public static final class RequestWillBeSentExtraInfoEvent extends CdpObject {
        public RequestWillBeSentExtraInfoEvent() {}
        private RequestWillBeSentExtraInfoEvent(Map<String, Object> values) { super(values); }
        public static RequestWillBeSentExtraInfoEvent fromMap(Map<String, Object> values) {
            return new RequestWillBeSentExtraInfoEvent(values);
        }
        /**
         * Request identifier. Used to match this information to an existing requestWillBeSent event.
         * @return the protocol field value
         */
        public Network.RequestId requestId() {
            return require(REQUEST_ID);
        }
        /**
         * A list of cookies potentially associated to the requested URL. This includes both cookies sent with the request and the ones not sent; the latter are distinguished by having blockedReasons field set.
         * @return the protocol field value
         */
        public java.util.List<Network.AssociatedCookie> associatedCookies() {
            return require(ASSOCIATED_COOKIES);
        }
        /**
         * Raw request headers as they will be sent over the wire.
         * @return the protocol field value
         */
        public java.util.Map<String, Object> headers() {
            return requireObject("headers");
        }
        /**
         * Connection timing information for the request.
         * <p><b>Experimental:</b> this part of CDP may change without notice.
         * @return the protocol field value
         */
        public Network.ConnectTiming connectTiming() {
            return require(CONNECT_TIMING);
        }
        /**
         * How the request site&#x27;s device bound sessions were used during this request.
         * @return the protocol field value, empty when absent
         */
        public Optional<java.util.List<Network.DeviceBoundSessionWithUsage>> deviceBoundSessionUsages() {
            return optional(DEVICE_BOUND_SESSION_USAGES);
        }
        /**
         * The client security state set for the request.
         * @return the protocol field value, empty when absent
         */
        public Optional<Network.ClientSecurityState> clientSecurityState() {
            return optional(CLIENT_SECURITY_STATE);
        }
        /**
         * Whether the site has partitioned cookies stored in a partition different than the current one.
         * @return the protocol field value, empty when absent
         */
        public Optional<Boolean> siteHasCookieInOtherPartition() {
            return optionalBoolean("siteHasCookieInOtherPartition");
        }
        /**
         * The network conditions id if this request was affected by network conditions configured via emulateNetworkConditionsByRule.
         * @return the protocol field value, empty when absent
         */
        public Optional<String> appliedNetworkConditionsId() {
            return optionalString("appliedNetworkConditionsId");
        }
        /**
         * Request identifier. Used to match this information to an existing requestWillBeSent event.
         * @param requestId field value
         * @return this model
         */
        public RequestWillBeSentExtraInfoEvent requestId(Network.RequestId requestId) {
            set("requestId", requestId);
            return this;
        }
        /**
         * A list of cookies potentially associated to the requested URL. This includes both cookies sent with the request and the ones not sent; the latter are distinguished by having blockedReasons field set.
         * @param associatedCookies field value
         * @return this model
         */
        public RequestWillBeSentExtraInfoEvent associatedCookies(java.util.List<Network.AssociatedCookie> associatedCookies) {
            set("associatedCookies", associatedCookies);
            return this;
        }
        /**
         * Raw request headers as they will be sent over the wire.
         * @param headers field value
         * @return this model
         */
        public RequestWillBeSentExtraInfoEvent headers(java.util.Map<String, Object> headers) {
            set("headers", headers);
            return this;
        }
        /**
         * Connection timing information for the request.
         * <p><b>Experimental:</b> this part of CDP may change without notice.
         * @param connectTiming field value
         * @return this model
         */
        public RequestWillBeSentExtraInfoEvent connectTiming(Network.ConnectTiming connectTiming) {
            set("connectTiming", connectTiming);
            return this;
        }
        /**
         * How the request site&#x27;s device bound sessions were used during this request.
         * @param deviceBoundSessionUsages field value
         * @return this model
         */
        public RequestWillBeSentExtraInfoEvent deviceBoundSessionUsages(java.util.List<Network.DeviceBoundSessionWithUsage> deviceBoundSessionUsages) {
            set("deviceBoundSessionUsages", deviceBoundSessionUsages);
            return this;
        }
        /**
         * The client security state set for the request.
         * @param clientSecurityState field value
         * @return this model
         */
        public RequestWillBeSentExtraInfoEvent clientSecurityState(Network.ClientSecurityState clientSecurityState) {
            set("clientSecurityState", clientSecurityState);
            return this;
        }
        /**
         * Whether the site has partitioned cookies stored in a partition different than the current one.
         * @param siteHasCookieInOtherPartition field value
         * @return this model
         */
        public RequestWillBeSentExtraInfoEvent siteHasCookieInOtherPartition(boolean siteHasCookieInOtherPartition) {
            set("siteHasCookieInOtherPartition", siteHasCookieInOtherPartition);
            return this;
        }
        /**
         * The network conditions id if this request was affected by network conditions configured via emulateNetworkConditionsByRule.
         * @param appliedNetworkConditionsId field value
         * @return this model
         */
        public RequestWillBeSentExtraInfoEvent appliedNetworkConditionsId(String appliedNetworkConditionsId) {
            set("appliedNetworkConditionsId", appliedNetworkConditionsId);
            return this;
        }
    }
    /**
     * Fired when additional information about a responseReceived event is available from the network stack. Not every responseReceived event will have an additional responseReceivedExtraInfo for it, and responseReceivedExtraInfo may be fired before or after responseReceived.
     * <p><b>Experimental:</b> this part of CDP may change without notice.
     */
    public static final class ResponseReceivedExtraInfoEvent extends CdpObject {
        public ResponseReceivedExtraInfoEvent() {}
        private ResponseReceivedExtraInfoEvent(Map<String, Object> values) { super(values); }
        public static ResponseReceivedExtraInfoEvent fromMap(Map<String, Object> values) {
            return new ResponseReceivedExtraInfoEvent(values);
        }
        /**
         * Request identifier. Used to match this information to another responseReceived event.
         * @return the protocol field value
         */
        public Network.RequestId requestId() {
            return require(REQUEST_ID);
        }
        /**
         * A list of cookies which were not stored from the response along with the corresponding reasons for blocking. The cookies here may not be valid due to syntax errors, which are represented by the invalid cookie line string instead of a proper cookie.
         * @return the protocol field value
         */
        public java.util.List<Network.BlockedSetCookieWithReason> blockedCookies() {
            return require(BLOCKED_COOKIES);
        }
        /**
         * Raw response headers as they were received over the wire. Duplicate headers in the response are represented as a single key with their values concatentated using {@code \n} as the separator. See also {@code headersText} that contains verbatim text for HTTP/1.*.
         * @return the protocol field value
         */
        public java.util.Map<String, Object> headers() {
            return requireObject("headers");
        }
        /**
         * The IP address space of the resource. The address space can only be determined once the transport established the connection, so we can&#x27;t send it in {@code requestWillBeSentExtraInfo}.
         * @return the protocol field value
         */
        public Network.IPAddressSpace resourceIPAddressSpace() {
            return require(RESOURCE_IPADDRESS_SPACE);
        }
        /**
         * The status code of the response. This is useful in cases the request failed and no responseReceived event is triggered, which is the case for, e.g., CORS errors. This is also the correct status code for cached requests, where the status in responseReceived is a 200 and this will be 304.
         * @return the protocol field value
         */
        public long statusCode() {
            return requireLong("statusCode");
        }
        /**
         * Raw response header text as it was received over the wire. The raw text may not always be available, such as in the case of HTTP/2 or QUIC.
         * @return the protocol field value, empty when absent
         */
        public Optional<String> headersText() {
            return optionalString("headersText");
        }
        /**
         * The cookie partition key that will be used to store partitioned cookies set in this response. Only sent when partitioned cookies are enabled.
         * <p><b>Experimental:</b> this part of CDP may change without notice.
         * @return the protocol field value, empty when absent
         */
        public Optional<Network.CookiePartitionKey> cookiePartitionKey() {
            return optional(COOKIE_PARTITION_KEY);
        }
        /**
         * True if partitioned cookies are enabled, but the partition key is not serializable to string.
         * @return the protocol field value, empty when absent
         */
        public Optional<Boolean> cookiePartitionKeyOpaque() {
            return optionalBoolean("cookiePartitionKeyOpaque");
        }
        /**
         * A list of cookies which should have been blocked by 3PCD but are exempted and stored from the response with the corresponding reason.
         * @return the protocol field value, empty when absent
         */
        public Optional<java.util.List<Network.ExemptedSetCookieWithReason>> exemptedCookies() {
            return optional(EXEMPTED_COOKIES);
        }
        /**
         * Request identifier. Used to match this information to another responseReceived event.
         * @param requestId field value
         * @return this model
         */
        public ResponseReceivedExtraInfoEvent requestId(Network.RequestId requestId) {
            set("requestId", requestId);
            return this;
        }
        /**
         * A list of cookies which were not stored from the response along with the corresponding reasons for blocking. The cookies here may not be valid due to syntax errors, which are represented by the invalid cookie line string instead of a proper cookie.
         * @param blockedCookies field value
         * @return this model
         */
        public ResponseReceivedExtraInfoEvent blockedCookies(java.util.List<Network.BlockedSetCookieWithReason> blockedCookies) {
            set("blockedCookies", blockedCookies);
            return this;
        }
        /**
         * Raw response headers as they were received over the wire. Duplicate headers in the response are represented as a single key with their values concatentated using {@code \n} as the separator. See also {@code headersText} that contains verbatim text for HTTP/1.*.
         * @param headers field value
         * @return this model
         */
        public ResponseReceivedExtraInfoEvent headers(java.util.Map<String, Object> headers) {
            set("headers", headers);
            return this;
        }
        /**
         * The IP address space of the resource. The address space can only be determined once the transport established the connection, so we can&#x27;t send it in {@code requestWillBeSentExtraInfo}.
         * @param resourceIPAddressSpace field value
         * @return this model
         */
        public ResponseReceivedExtraInfoEvent resourceIPAddressSpace(Network.IPAddressSpace resourceIPAddressSpace) {
            set("resourceIPAddressSpace", resourceIPAddressSpace);
            return this;
        }
        /**
         * The status code of the response. This is useful in cases the request failed and no responseReceived event is triggered, which is the case for, e.g., CORS errors. This is also the correct status code for cached requests, where the status in responseReceived is a 200 and this will be 304.
         * @param statusCode field value
         * @return this model
         */
        public ResponseReceivedExtraInfoEvent statusCode(long statusCode) {
            set("statusCode", statusCode);
            return this;
        }
        /**
         * Raw response header text as it was received over the wire. The raw text may not always be available, such as in the case of HTTP/2 or QUIC.
         * @param headersText field value
         * @return this model
         */
        public ResponseReceivedExtraInfoEvent headersText(String headersText) {
            set("headersText", headersText);
            return this;
        }
        /**
         * The cookie partition key that will be used to store partitioned cookies set in this response. Only sent when partitioned cookies are enabled.
         * <p><b>Experimental:</b> this part of CDP may change without notice.
         * @param cookiePartitionKey field value
         * @return this model
         */
        public ResponseReceivedExtraInfoEvent cookiePartitionKey(Network.CookiePartitionKey cookiePartitionKey) {
            set("cookiePartitionKey", cookiePartitionKey);
            return this;
        }
        /**
         * True if partitioned cookies are enabled, but the partition key is not serializable to string.
         * @param cookiePartitionKeyOpaque field value
         * @return this model
         */
        public ResponseReceivedExtraInfoEvent cookiePartitionKeyOpaque(boolean cookiePartitionKeyOpaque) {
            set("cookiePartitionKeyOpaque", cookiePartitionKeyOpaque);
            return this;
        }
        /**
         * A list of cookies which should have been blocked by 3PCD but are exempted and stored from the response with the corresponding reason.
         * @param exemptedCookies field value
         * @return this model
         */
        public ResponseReceivedExtraInfoEvent exemptedCookies(java.util.List<Network.ExemptedSetCookieWithReason> exemptedCookies) {
            set("exemptedCookies", exemptedCookies);
            return this;
        }
    }
    /**
     * Fired when 103 Early Hints headers is received in addition to the common response. Not every responseReceived event will have an responseReceivedEarlyHints fired. Only one responseReceivedEarlyHints may be fired for eached responseReceived event.
     * <p><b>Experimental:</b> this part of CDP may change without notice.
     */
    public static final class ResponseReceivedEarlyHintsEvent extends CdpObject {
        public ResponseReceivedEarlyHintsEvent() {}
        private ResponseReceivedEarlyHintsEvent(Map<String, Object> values) { super(values); }
        public static ResponseReceivedEarlyHintsEvent fromMap(Map<String, Object> values) {
            return new ResponseReceivedEarlyHintsEvent(values);
        }
        /**
         * Request identifier. Used to match this information to another responseReceived event.
         * @return the protocol field value
         */
        public Network.RequestId requestId() {
            return require(REQUEST_ID);
        }
        /**
         * Raw response headers as they were received over the wire. Duplicate headers in the response are represented as a single key with their values concatentated using {@code \n} as the separator. See also {@code headersText} that contains verbatim text for HTTP/1.*.
         * @return the protocol field value
         */
        public java.util.Map<String, Object> headers() {
            return requireObject("headers");
        }
        /**
         * Request identifier. Used to match this information to another responseReceived event.
         * @param requestId field value
         * @return this model
         */
        public ResponseReceivedEarlyHintsEvent requestId(Network.RequestId requestId) {
            set("requestId", requestId);
            return this;
        }
        /**
         * Raw response headers as they were received over the wire. Duplicate headers in the response are represented as a single key with their values concatentated using {@code \n} as the separator. See also {@code headersText} that contains verbatim text for HTTP/1.*.
         * @param headers field value
         * @return this model
         */
        public ResponseReceivedEarlyHintsEvent headers(java.util.Map<String, Object> headers) {
            set("headers", headers);
            return this;
        }
    }
    /**
     * Fired exactly once for each Trust Token operation. Depending on the type of the operation and whether the operation succeeded or failed, the event is fired before the corresponding request was sent or after the response was received.
     * <p><b>Experimental:</b> this part of CDP may change without notice.
     */
    public static final class TrustTokenOperationDoneEvent extends CdpObject {
        public TrustTokenOperationDoneEvent() {}
        private TrustTokenOperationDoneEvent(Map<String, Object> values) { super(values); }
        public static TrustTokenOperationDoneEvent fromMap(Map<String, Object> values) {
            return new TrustTokenOperationDoneEvent(values);
        }
        /**
         * Detailed success or error status of the operation. &#x27;AlreadyExists&#x27; also signifies a successful operation, as the result of the operation already exists und thus, the operation was abort preemptively (e.g. a cache hit).
         */
        public enum StatusValues implements CdpValue<String> {
            OK("Ok"),
            INVALIDARGUMENT("InvalidArgument"),
            MISSINGISSUERKEYS("MissingIssuerKeys"),
            FAILEDPRECONDITION("FailedPrecondition"),
            RESOURCEEXHAUSTED("ResourceExhausted"),
            ALREADYEXISTS("AlreadyExists"),
            RESOURCELIMITED("ResourceLimited"),
            UNAUTHORIZED("Unauthorized"),
            BADRESPONSE("BadResponse"),
            INTERNALERROR("InternalError"),
            UNKNOWNERROR("UnknownError"),
            FULFILLEDLOCALLY("FulfilledLocally"),
            SITEISSUERLIMIT("SiteIssuerLimit");
            public final String value;
            StatusValues(String value) { this.value = value; }
            @Override public String value() { return value; }
            public static StatusValues of(String value) {
                for (StatusValues constant : values()) {
                    if (constant.value.equals(value)) return constant;
                }
                throw new IllegalArgumentException("Unknown StatusValues value: " + value);
            }
        }
        /**
         * Detailed success or error status of the operation. &#x27;AlreadyExists&#x27; also signifies a successful operation, as the result of the operation already exists und thus, the operation was abort preemptively (e.g. a cache hit).
         * @return the protocol field value
         */
        public TrustTokenOperationDoneEvent.StatusValues status() {
            return require(STATUS_2);
        }
        /**
         * Returns the type field.
         * @return the protocol field value
         */
        public Network.TrustTokenOperationType type() {
            return require(TYPE_3);
        }
        /**
         * Returns the requestId field.
         * @return the protocol field value
         */
        public Network.RequestId requestId() {
            return require(REQUEST_ID);
        }
        /**
         * Top level origin. The context in which the operation was attempted.
         * @return the protocol field value, empty when absent
         */
        public Optional<String> topLevelOrigin() {
            return optionalString("topLevelOrigin");
        }
        /**
         * Origin of the issuer in case of a &quot;Issuance&quot; or &quot;Redemption&quot; operation.
         * @return the protocol field value, empty when absent
         */
        public Optional<String> issuerOrigin() {
            return optionalString("issuerOrigin");
        }
        /**
         * The number of obtained Trust Tokens on a successful &quot;Issuance&quot; operation.
         * @return the protocol field value, empty when absent
         */
        public OptionalLong issuedTokenCount() {
            return optionalLong("issuedTokenCount");
        }
        /**
         * Detailed success or error status of the operation. &#x27;AlreadyExists&#x27; also signifies a successful operation, as the result of the operation already exists und thus, the operation was abort preemptively (e.g. a cache hit).
         * @param status field value
         * @return this model
         */
        public TrustTokenOperationDoneEvent status(TrustTokenOperationDoneEvent.StatusValues status) {
            set("status", status);
            return this;
        }
        /**
         * Sets the type field.
         * @param type field value
         * @return this model
         */
        public TrustTokenOperationDoneEvent type(Network.TrustTokenOperationType type) {
            set("type", type);
            return this;
        }
        /**
         * Sets the requestId field.
         * @param requestId field value
         * @return this model
         */
        public TrustTokenOperationDoneEvent requestId(Network.RequestId requestId) {
            set("requestId", requestId);
            return this;
        }
        /**
         * Top level origin. The context in which the operation was attempted.
         * @param topLevelOrigin field value
         * @return this model
         */
        public TrustTokenOperationDoneEvent topLevelOrigin(String topLevelOrigin) {
            set("topLevelOrigin", topLevelOrigin);
            return this;
        }
        /**
         * Origin of the issuer in case of a &quot;Issuance&quot; or &quot;Redemption&quot; operation.
         * @param issuerOrigin field value
         * @return this model
         */
        public TrustTokenOperationDoneEvent issuerOrigin(String issuerOrigin) {
            set("issuerOrigin", issuerOrigin);
            return this;
        }
        /**
         * The number of obtained Trust Tokens on a successful &quot;Issuance&quot; operation.
         * @param issuedTokenCount field value
         * @return this model
         */
        public TrustTokenOperationDoneEvent issuedTokenCount(long issuedTokenCount) {
            set("issuedTokenCount", issuedTokenCount);
            return this;
        }
    }
    /**
     * Fired once security policy has been updated.
     * <p><b>Experimental:</b> this part of CDP may change without notice.
     */
    public static final class PolicyUpdatedEvent extends CdpObject {
        public PolicyUpdatedEvent() {}
        private PolicyUpdatedEvent(Map<String, Object> values) { super(values); }
        public static PolicyUpdatedEvent fromMap(Map<String, Object> values) {
            return new PolicyUpdatedEvent(values);
        }
    }
    /**
     * Is sent whenever a new report is added. And after &#x27;enableReportingApi&#x27; for all existing reports.
     * <p><b>Experimental:</b> this part of CDP may change without notice.
     */
    public static final class ReportingApiReportAddedEvent extends CdpObject {
        public ReportingApiReportAddedEvent() {}
        private ReportingApiReportAddedEvent(Map<String, Object> values) { super(values); }
        public static ReportingApiReportAddedEvent fromMap(Map<String, Object> values) {
            return new ReportingApiReportAddedEvent(values);
        }
        /**
         * Returns the report field.
         * @return the protocol field value
         */
        public Network.ReportingApiReport report() {
            return require(REPORT);
        }
        /**
         * Sets the report field.
         * @param report field value
         * @return this model
         */
        public ReportingApiReportAddedEvent report(Network.ReportingApiReport report) {
            set("report", report);
            return this;
        }
    }
    /**
     * Payload of the Network.reportingApiReportUpdated event.
     * <p><b>Experimental:</b> this part of CDP may change without notice.
     */
    public static final class ReportingApiReportUpdatedEvent extends CdpObject {
        public ReportingApiReportUpdatedEvent() {}
        private ReportingApiReportUpdatedEvent(Map<String, Object> values) { super(values); }
        public static ReportingApiReportUpdatedEvent fromMap(Map<String, Object> values) {
            return new ReportingApiReportUpdatedEvent(values);
        }
        /**
         * Returns the report field.
         * @return the protocol field value
         */
        public Network.ReportingApiReport report() {
            return require(REPORT);
        }
        /**
         * Sets the report field.
         * @param report field value
         * @return this model
         */
        public ReportingApiReportUpdatedEvent report(Network.ReportingApiReport report) {
            set("report", report);
            return this;
        }
    }
    /**
     * Payload of the Network.reportingApiEndpointsChangedForOrigin event.
     * <p><b>Experimental:</b> this part of CDP may change without notice.
     */
    public static final class ReportingApiEndpointsChangedForOriginEvent extends CdpObject {
        public ReportingApiEndpointsChangedForOriginEvent() {}
        private ReportingApiEndpointsChangedForOriginEvent(Map<String, Object> values) { super(values); }
        public static ReportingApiEndpointsChangedForOriginEvent fromMap(Map<String, Object> values) {
            return new ReportingApiEndpointsChangedForOriginEvent(values);
        }
        /**
         * Origin of the document(s) which configured the endpoints.
         * @return the protocol field value
         */
        public String origin() {
            return requireString("origin");
        }
        /**
         * Returns the endpoints field.
         * @return the protocol field value
         */
        public java.util.List<Network.ReportingApiEndpoint> endpoints() {
            return require(ENDPOINTS);
        }
        /**
         * Origin of the document(s) which configured the endpoints.
         * @param origin field value
         * @return this model
         */
        public ReportingApiEndpointsChangedForOriginEvent origin(String origin) {
            set("origin", origin);
            return this;
        }
        /**
         * Sets the endpoints field.
         * @param endpoints field value
         * @return this model
         */
        public ReportingApiEndpointsChangedForOriginEvent endpoints(java.util.List<Network.ReportingApiEndpoint> endpoints) {
            set("endpoints", endpoints);
            return this;
        }
    }
    /**
     * Triggered when the initial set of device bound sessions is added.
     * <p><b>Experimental:</b> this part of CDP may change without notice.
     */
    public static final class DeviceBoundSessionsAddedEvent extends CdpObject {
        public DeviceBoundSessionsAddedEvent() {}
        private DeviceBoundSessionsAddedEvent(Map<String, Object> values) { super(values); }
        public static DeviceBoundSessionsAddedEvent fromMap(Map<String, Object> values) {
            return new DeviceBoundSessionsAddedEvent(values);
        }
        /**
         * The device bound sessions.
         * @return the protocol field value
         */
        public java.util.List<Network.DeviceBoundSession> sessions() {
            return require(SESSIONS);
        }
        /**
         * The device bound sessions.
         * @param sessions field value
         * @return this model
         */
        public DeviceBoundSessionsAddedEvent sessions(java.util.List<Network.DeviceBoundSession> sessions) {
            set("sessions", sessions);
            return this;
        }
    }
    /**
     * Triggered when a device bound session event occurs.
     * <p><b>Experimental:</b> this part of CDP may change without notice.
     */
    public static final class DeviceBoundSessionEventOccurredEvent extends CdpObject {
        public DeviceBoundSessionEventOccurredEvent() {}
        private DeviceBoundSessionEventOccurredEvent(Map<String, Object> values) { super(values); }
        public static DeviceBoundSessionEventOccurredEvent fromMap(Map<String, Object> values) {
            return new DeviceBoundSessionEventOccurredEvent(values);
        }
        /**
         * A unique identifier for this session event.
         * @return the protocol field value
         */
        public Network.DeviceBoundSessionEventId eventId() {
            return require(EVENT_ID);
        }
        /**
         * The site this session event is associated with.
         * @return the protocol field value
         */
        public String site() {
            return requireString("site");
        }
        /**
         * Whether this event was considered successful.
         * @return the protocol field value
         */
        public boolean succeeded() {
            return requireBoolean("succeeded");
        }
        /**
         * The session ID this event is associated with. May not be populated for failed events.
         * @return the protocol field value, empty when absent
         */
        public Optional<String> sessionId() {
            return optionalString("sessionId");
        }
        /**
         * The below are the different session event type details. Exactly one is populated.
         * @return the protocol field value, empty when absent
         */
        public Optional<Network.CreationEventDetails> creationEventDetails() {
            return optional(CREATION_EVENT_DETAILS);
        }
        /**
         * Returns the refreshEventDetails field.
         * @return the protocol field value, empty when absent
         */
        public Optional<Network.RefreshEventDetails> refreshEventDetails() {
            return optional(REFRESH_EVENT_DETAILS);
        }
        /**
         * Returns the terminationEventDetails field.
         * @return the protocol field value, empty when absent
         */
        public Optional<Network.TerminationEventDetails> terminationEventDetails() {
            return optional(TERMINATION_EVENT_DETAILS);
        }
        /**
         * Returns the challengeEventDetails field.
         * @return the protocol field value, empty when absent
         */
        public Optional<Network.ChallengeEventDetails> challengeEventDetails() {
            return optional(CHALLENGE_EVENT_DETAILS);
        }
        /**
         * A unique identifier for this session event.
         * @param eventId field value
         * @return this model
         */
        public DeviceBoundSessionEventOccurredEvent eventId(Network.DeviceBoundSessionEventId eventId) {
            set("eventId", eventId);
            return this;
        }
        /**
         * The site this session event is associated with.
         * @param site field value
         * @return this model
         */
        public DeviceBoundSessionEventOccurredEvent site(String site) {
            set("site", site);
            return this;
        }
        /**
         * Whether this event was considered successful.
         * @param succeeded field value
         * @return this model
         */
        public DeviceBoundSessionEventOccurredEvent succeeded(boolean succeeded) {
            set("succeeded", succeeded);
            return this;
        }
        /**
         * The session ID this event is associated with. May not be populated for failed events.
         * @param sessionId field value
         * @return this model
         */
        public DeviceBoundSessionEventOccurredEvent sessionId(String sessionId) {
            set("sessionId", sessionId);
            return this;
        }
        /**
         * The below are the different session event type details. Exactly one is populated.
         * @param creationEventDetails field value
         * @return this model
         */
        public DeviceBoundSessionEventOccurredEvent creationEventDetails(Network.CreationEventDetails creationEventDetails) {
            set("creationEventDetails", creationEventDetails);
            return this;
        }
        /**
         * Sets the refreshEventDetails field.
         * @param refreshEventDetails field value
         * @return this model
         */
        public DeviceBoundSessionEventOccurredEvent refreshEventDetails(Network.RefreshEventDetails refreshEventDetails) {
            set("refreshEventDetails", refreshEventDetails);
            return this;
        }
        /**
         * Sets the terminationEventDetails field.
         * @param terminationEventDetails field value
         * @return this model
         */
        public DeviceBoundSessionEventOccurredEvent terminationEventDetails(Network.TerminationEventDetails terminationEventDetails) {
            set("terminationEventDetails", terminationEventDetails);
            return this;
        }
        /**
         * Sets the challengeEventDetails field.
         * @param challengeEventDetails field value
         * @return this model
         */
        public DeviceBoundSessionEventOccurredEvent challengeEventDetails(Network.ChallengeEventDetails challengeEventDetails) {
            set("challengeEventDetails", challengeEventDetails);
            return this;
        }
    }
    public static final class Client extends CdpDomainClient {
        public Client(CdpClient client) { super(client); }
        /**
         * Sets a list of content encodings that will be accepted. Empty list means no encoding is accepted.
         * <p><b>Experimental:</b> this part of CDP may change without notice.
         * @param encodings protocol value
         * @return a stage completing when the command completes
         */
        public CompletionStage<Void> setAcceptedEncodings(java.util.List<Network.ContentEncoding> encodings) {
            return call("Network.setAcceptedEncodings", params("encodings", encodings));
        }
        /**
         * Sets a list of content encodings that will be accepted. Empty list means no encoding is accepted.
         * <p><b>Experimental:</b> this part of CDP may change without notice.
         * @param request request parameters
         * @return a stage completing when the command completes
         */
        public CompletionStage<Void> setAcceptedEncodings(SetAcceptedEncodingsRequest request) {
            return call("Network.setAcceptedEncodings", request);
        }
        /**
         * Clears accepted encodings set by setAcceptedEncodings
         * <p><b>Experimental:</b> this part of CDP may change without notice.
         * @return a stage completing when the command completes
         */
        public CompletionStage<Void> clearAcceptedEncodingsOverride() {
            return call("Network.clearAcceptedEncodingsOverride");
        }
        /**
         * Tells whether clearing browser cache is supported.
         * @return a stage completing with the command result
         * @deprecated Deprecated by the Chromium DevTools Protocol.
         */
        @Deprecated
        public CompletionStage<Boolean> canClearBrowserCache() {
            return call("Network.canClearBrowserCache", RESULT::require);
        }
        /**
         * Tells whether clearing browser cookies is supported.
         * @return a stage completing with the command result
         * @deprecated Deprecated by the Chromium DevTools Protocol.
         */
        @Deprecated
        public CompletionStage<Boolean> canClearBrowserCookies() {
            return call("Network.canClearBrowserCookies", RESULT::require);
        }
        /**
         * Tells whether emulation of network conditions is supported.
         * @return a stage completing with the command result
         * @deprecated Deprecated by the Chromium DevTools Protocol.
         */
        @Deprecated
        public CompletionStage<Boolean> canEmulateNetworkConditions() {
            return call("Network.canEmulateNetworkConditions", RESULT::require);
        }
        /**
         * Clears browser cache.
         * @return a stage completing when the command completes
         */
        public CompletionStage<Void> clearBrowserCache() {
            return call("Network.clearBrowserCache");
        }
        /**
         * Clears browser cookies.
         * @return a stage completing when the command completes
         */
        public CompletionStage<Void> clearBrowserCookies() {
            return call("Network.clearBrowserCookies");
        }
        /**
         * Response to Network.requestIntercepted which either modifies the request to continue with any modifications, or blocks it, or completes it with the provided response bytes. If a network fetch occurs as a result which encounters a redirect an additional Network.requestIntercepted event will be sent with the same InterceptionId. Deprecated, use Fetch.continueRequest, Fetch.fulfillRequest and Fetch.failRequest instead.
         * <p><b>Experimental:</b> this part of CDP may change without notice.
         * @param interceptionId protocol value
         * @param errorReason protocol value
         * @param rawResponse protocol value
         * @param url protocol value
         * @param method protocol value
         * @param postData protocol value
         * @param headers protocol value
         * @param authChallengeResponse protocol value
         * @return a stage completing when the command completes
         * @deprecated Deprecated by the Chromium DevTools Protocol.
         */
        @Deprecated
        public CompletionStage<Void> continueInterceptedRequest(Network.InterceptionId interceptionId, Network.@Nullable ErrorReason errorReason, @Nullable String rawResponse, @Nullable String url, @Nullable String method, @Nullable String postData, java.util.@Nullable Map<String, Object> headers, Network.@Nullable AuthChallengeResponse authChallengeResponse) {
            return call("Network.continueInterceptedRequest", params("interceptionId", interceptionId, "errorReason", errorReason, "rawResponse", rawResponse, "url", url, "method", method, "postData", postData, "headers", headers, "authChallengeResponse", authChallengeResponse));
        }
        /**
         * Response to Network.requestIntercepted which either modifies the request to continue with any modifications, or blocks it, or completes it with the provided response bytes. If a network fetch occurs as a result which encounters a redirect an additional Network.requestIntercepted event will be sent with the same InterceptionId. Deprecated, use Fetch.continueRequest, Fetch.fulfillRequest and Fetch.failRequest instead.
         * <p><b>Experimental:</b> this part of CDP may change without notice.
         * @param interceptionId protocol value
         * @return a stage completing when the command completes
         * @deprecated Deprecated by the Chromium DevTools Protocol.
         */
        @Deprecated
        public CompletionStage<Void> continueInterceptedRequest(Network.InterceptionId interceptionId) {
            return call("Network.continueInterceptedRequest", params("interceptionId", interceptionId));
        }
        /**
         * Response to Network.requestIntercepted which either modifies the request to continue with any modifications, or blocks it, or completes it with the provided response bytes. If a network fetch occurs as a result which encounters a redirect an additional Network.requestIntercepted event will be sent with the same InterceptionId. Deprecated, use Fetch.continueRequest, Fetch.fulfillRequest and Fetch.failRequest instead.
         * <p><b>Experimental:</b> this part of CDP may change without notice.
         * @param request request parameters
         * @return a stage completing when the command completes
         * @deprecated Deprecated by the Chromium DevTools Protocol.
         */
        @Deprecated
        public CompletionStage<Void> continueInterceptedRequest(ContinueInterceptedRequestRequest request) {
            return call("Network.continueInterceptedRequest", request);
        }
        /**
         * Deletes browser cookies with matching name and url or domain/path/partitionKey pair.
         * @param name protocol value
         * @param url protocol value
         * @param domain protocol value
         * @param path protocol value
         * @param partitionKey protocol value
         * @return a stage completing when the command completes
         */
        public CompletionStage<Void> deleteCookies(String name, @Nullable String url, @Nullable String domain, @Nullable String path, Network.@Nullable CookiePartitionKey partitionKey) {
            return call("Network.deleteCookies", params("name", name, "url", url, "domain", domain, "path", path, "partitionKey", partitionKey));
        }
        /**
         * Deletes browser cookies with matching name and url or domain/path/partitionKey pair.
         * @param name protocol value
         * @return a stage completing when the command completes
         */
        public CompletionStage<Void> deleteCookies(String name) {
            return call("Network.deleteCookies", params("name", name));
        }
        /**
         * Deletes browser cookies with matching name and url or domain/path/partitionKey pair.
         * @param request request parameters
         * @return a stage completing when the command completes
         */
        public CompletionStage<Void> deleteCookies(DeleteCookiesRequest request) {
            return call("Network.deleteCookies", request);
        }
        /**
         * Disables network tracking, prevents network events from being sent to the client.
         * @return a stage completing when the command completes
         */
        public CompletionStage<Void> disable() {
            return call("Network.disable");
        }
        /**
         * Activates emulation of network conditions. This command is deprecated in favor of the emulateNetworkConditionsByRule and overrideNetworkState commands, which can be used together to the same effect.
         * @param offline protocol value
         * @param latency protocol value
         * @param downloadThroughput protocol value
         * @param uploadThroughput protocol value
         * @param connectionType protocol value
         * @param packetLoss protocol value
         * @param packetQueueLength protocol value
         * @param packetReordering protocol value
         * @return a stage completing when the command completes
         * @deprecated Deprecated by the Chromium DevTools Protocol.
         */
        @Deprecated
        public CompletionStage<Void> emulateNetworkConditions(boolean offline, double latency, double downloadThroughput, double uploadThroughput, Network.@Nullable ConnectionType connectionType, @Nullable Double packetLoss, @Nullable Long packetQueueLength, @Nullable Boolean packetReordering) {
            return call("Network.emulateNetworkConditions", params("offline", offline, "latency", latency, "downloadThroughput", downloadThroughput, "uploadThroughput", uploadThroughput, "connectionType", connectionType, "packetLoss", packetLoss, "packetQueueLength", packetQueueLength, "packetReordering", packetReordering));
        }
        /**
         * Activates emulation of network conditions. This command is deprecated in favor of the emulateNetworkConditionsByRule and overrideNetworkState commands, which can be used together to the same effect.
         * @param offline protocol value
         * @param latency protocol value
         * @param downloadThroughput protocol value
         * @param uploadThroughput protocol value
         * @return a stage completing when the command completes
         * @deprecated Deprecated by the Chromium DevTools Protocol.
         */
        @Deprecated
        public CompletionStage<Void> emulateNetworkConditions(boolean offline, double latency, double downloadThroughput, double uploadThroughput) {
            return call("Network.emulateNetworkConditions", params("offline", offline, "latency", latency, "downloadThroughput", downloadThroughput, "uploadThroughput", uploadThroughput));
        }
        /**
         * Activates emulation of network conditions. This command is deprecated in favor of the emulateNetworkConditionsByRule and overrideNetworkState commands, which can be used together to the same effect.
         * @param request request parameters
         * @return a stage completing when the command completes
         * @deprecated Deprecated by the Chromium DevTools Protocol.
         */
        @Deprecated
        public CompletionStage<Void> emulateNetworkConditions(EmulateNetworkConditionsRequest request) {
            return call("Network.emulateNetworkConditions", request);
        }
        /**
         * Activates emulation of network conditions for individual requests using URL match patterns. Unlike the deprecated Network.emulateNetworkConditions this method does not affect {@code navigator} state. Use Network.overrideNetworkState to explicitly modify {@code navigator} behavior.
         * <p><b>Experimental:</b> this part of CDP may change without notice.
         * @param offline protocol value
         * @param emulateOfflineServiceWorker protocol value
         * @param matchedNetworkConditions protocol value
         * @return a stage completing with the command result
         */
        public CompletionStage<java.util.List<String>> emulateNetworkConditionsByRule(@Nullable Boolean offline, @Nullable Boolean emulateOfflineServiceWorker, java.util.List<Network.NetworkConditions> matchedNetworkConditions) {
            return call("Network.emulateNetworkConditionsByRule", params("offline", offline, "emulateOfflineServiceWorker", emulateOfflineServiceWorker, "matchedNetworkConditions", matchedNetworkConditions), RULE_IDS::require);
        }
        /**
         * Activates emulation of network conditions for individual requests using URL match patterns. Unlike the deprecated Network.emulateNetworkConditions this method does not affect {@code navigator} state. Use Network.overrideNetworkState to explicitly modify {@code navigator} behavior.
         * <p><b>Experimental:</b> this part of CDP may change without notice.
         * @param matchedNetworkConditions protocol value
         * @return a stage completing with the command result
         */
        public CompletionStage<java.util.List<String>> emulateNetworkConditionsByRule(java.util.List<Network.NetworkConditions> matchedNetworkConditions) {
            return call("Network.emulateNetworkConditionsByRule", params("matchedNetworkConditions", matchedNetworkConditions), RULE_IDS::require);
        }
        /**
         * Activates emulation of network conditions for individual requests using URL match patterns. Unlike the deprecated Network.emulateNetworkConditions this method does not affect {@code navigator} state. Use Network.overrideNetworkState to explicitly modify {@code navigator} behavior.
         * <p><b>Experimental:</b> this part of CDP may change without notice.
         * @param request request parameters
         * @return a stage completing with the command result
         */
        public CompletionStage<java.util.List<String>> emulateNetworkConditionsByRule(EmulateNetworkConditionsByRuleRequest request) {
            return call("Network.emulateNetworkConditionsByRule", request, RULE_IDS::require);
        }
        /**
         * Override the state of navigator.onLine and navigator.connection.
         * <p><b>Experimental:</b> this part of CDP may change without notice.
         * @param offline protocol value
         * @param latency protocol value
         * @param downloadThroughput protocol value
         * @param uploadThroughput protocol value
         * @param connectionType protocol value
         * @return a stage completing when the command completes
         */
        public CompletionStage<Void> overrideNetworkState(boolean offline, double latency, double downloadThroughput, double uploadThroughput, Network.ConnectionType connectionType) {
            return call("Network.overrideNetworkState", params("offline", offline, "latency", latency, "downloadThroughput", downloadThroughput, "uploadThroughput", uploadThroughput, "connectionType", connectionType));
        }
        /**
         * Override the state of navigator.onLine and navigator.connection.
         * <p><b>Experimental:</b> this part of CDP may change without notice.
         * @param offline protocol value
         * @param latency protocol value
         * @param downloadThroughput protocol value
         * @param uploadThroughput protocol value
         * @return a stage completing when the command completes
         */
        public CompletionStage<Void> overrideNetworkState(boolean offline, double latency, double downloadThroughput, double uploadThroughput) {
            return call("Network.overrideNetworkState", params("offline", offline, "latency", latency, "downloadThroughput", downloadThroughput, "uploadThroughput", uploadThroughput));
        }
        /**
         * Override the state of navigator.onLine and navigator.connection.
         * <p><b>Experimental:</b> this part of CDP may change without notice.
         * @param request request parameters
         * @return a stage completing when the command completes
         */
        public CompletionStage<Void> overrideNetworkState(OverrideNetworkStateRequest request) {
            return call("Network.overrideNetworkState", request);
        }
        /**
         * Enables network tracking, network events will now be delivered to the client.
         * @param maxTotalBufferSize protocol value
         * @param maxResourceBufferSize protocol value
         * @param maxPostDataSize protocol value
         * @param reportDirectSocketTraffic protocol value
         * @param enableDurableMessages protocol value
         * @return a stage completing when the command completes
         */
        public CompletionStage<Void> enable(@Nullable Long maxTotalBufferSize, @Nullable Long maxResourceBufferSize, @Nullable Long maxPostDataSize, @Nullable Boolean reportDirectSocketTraffic, @Nullable Boolean enableDurableMessages) {
            return call("Network.enable", params("maxTotalBufferSize", maxTotalBufferSize, "maxResourceBufferSize", maxResourceBufferSize, "maxPostDataSize", maxPostDataSize, "reportDirectSocketTraffic", reportDirectSocketTraffic, "enableDurableMessages", enableDurableMessages));
        }
        /**
         * Enables network tracking, network events will now be delivered to the client.
         * @return a stage completing when the command completes
         */
        public CompletionStage<Void> enable() {
            return call("Network.enable");
        }
        /**
         * Enables network tracking, network events will now be delivered to the client.
         * @param request request parameters
         * @return a stage completing when the command completes
         */
        public CompletionStage<Void> enable(EnableRequest request) {
            return call("Network.enable", request);
        }
        /**
         * Configures storing response bodies outside of renderer, so that these survive a cross-process navigation. If maxTotalBufferSize is not set, durable messages are disabled.
         * <p><b>Experimental:</b> this part of CDP may change without notice.
         * @param maxTotalBufferSize protocol value
         * @param maxResourceBufferSize protocol value
         * @return a stage completing when the command completes
         */
        public CompletionStage<Void> configureDurableMessages(@Nullable Long maxTotalBufferSize, @Nullable Long maxResourceBufferSize) {
            return call("Network.configureDurableMessages", params("maxTotalBufferSize", maxTotalBufferSize, "maxResourceBufferSize", maxResourceBufferSize));
        }
        /**
         * Configures storing response bodies outside of renderer, so that these survive a cross-process navigation. If maxTotalBufferSize is not set, durable messages are disabled.
         * <p><b>Experimental:</b> this part of CDP may change without notice.
         * @return a stage completing when the command completes
         */
        public CompletionStage<Void> configureDurableMessages() {
            return call("Network.configureDurableMessages");
        }
        /**
         * Configures storing response bodies outside of renderer, so that these survive a cross-process navigation. If maxTotalBufferSize is not set, durable messages are disabled.
         * <p><b>Experimental:</b> this part of CDP may change without notice.
         * @param request request parameters
         * @return a stage completing when the command completes
         */
        public CompletionStage<Void> configureDurableMessages(ConfigureDurableMessagesRequest request) {
            return call("Network.configureDurableMessages", request);
        }
        /**
         * Returns all browser cookies. Depending on the backend support, will return detailed cookie information in the {@code cookies} field. Deprecated. Use Storage.getCookies instead.
         * @return a stage completing with the command result
         * @deprecated Deprecated by the Chromium DevTools Protocol.
         */
        @Deprecated
        public CompletionStage<java.util.List<Network.Cookie>> getAllCookies() {
            return call("Network.getAllCookies", COOKIES_2::require);
        }
        /**
         * Returns the DER-encoded certificate.
         * <p><b>Experimental:</b> this part of CDP may change without notice.
         * @param origin protocol value
         * @return a stage completing with the command result
         */
        public CompletionStage<java.util.List<String>> getCertificate(String origin) {
            return call("Network.getCertificate", params("origin", origin), TABLE_NAMES::require);
        }
        /**
         * Returns the DER-encoded certificate.
         * <p><b>Experimental:</b> this part of CDP may change without notice.
         * @param request request parameters
         * @return a stage completing with the command result
         */
        public CompletionStage<java.util.List<String>> getCertificate(GetCertificateRequest request) {
            return call("Network.getCertificate", request, TABLE_NAMES::require);
        }
        /**
         * Returns all browser cookies for the current URL. Depending on the backend support, will return detailed cookie information in the {@code cookies} field.
         * @param urls protocol value
         * @return a stage completing with the command result
         */
        public CompletionStage<java.util.List<Network.Cookie>> getCookies(java.util.List<String> urls) {
            return call("Network.getCookies", params("urls", urls), COOKIES_2::require);
        }
        /**
         * Returns all browser cookies for the current URL. Depending on the backend support, will return detailed cookie information in the {@code cookies} field.
         * @return a stage completing with the command result
         */
        public CompletionStage<java.util.List<Network.Cookie>> getCookies() {
            return call("Network.getCookies", COOKIES_2::require);
        }
        /**
         * Returns all browser cookies for the current URL. Depending on the backend support, will return detailed cookie information in the {@code cookies} field.
         * @param request request parameters
         * @return a stage completing with the command result
         */
        public CompletionStage<java.util.List<Network.Cookie>> getCookies(GetCookiesRequest request) {
            return call("Network.getCookies", request, COOKIES_2::require);
        }
        /**
         * Returns content served for the given request.
         * @param requestId protocol value
         * @return a stage completing with the command result
         */
        public CompletionStage<GetResponseBodyResult> getResponseBody(Network.RequestId requestId) {
            return call("Network.getResponseBody", params("requestId", requestId), GetResponseBodyResult::fromMap);
        }
        /**
         * Returns content served for the given request.
         * @param request request parameters
         * @return a stage completing with the command result
         */
        public CompletionStage<GetResponseBodyResult> getResponseBody(GetResponseBodyRequest request) {
            return call("Network.getResponseBody", request, GetResponseBodyResult::fromMap);
        }
        /**
         * Returns post data sent with the request. Returns an error when no data was sent with the request.
         * @param requestId protocol value
         * @return a stage completing with the command result
         */
        public CompletionStage<GetRequestPostDataResult> getRequestPostData(Network.RequestId requestId) {
            return call("Network.getRequestPostData", params("requestId", requestId), GetRequestPostDataResult::fromMap);
        }
        /**
         * Returns post data sent with the request. Returns an error when no data was sent with the request.
         * @param request request parameters
         * @return a stage completing with the command result
         */
        public CompletionStage<GetRequestPostDataResult> getRequestPostData(GetRequestPostDataRequest request) {
            return call("Network.getRequestPostData", request, GetRequestPostDataResult::fromMap);
        }
        /**
         * Returns content served for the given currently intercepted request.
         * <p><b>Experimental:</b> this part of CDP may change without notice.
         * @param interceptionId protocol value
         * @return a stage completing with the command result
         */
        public CompletionStage<GetResponseBodyForInterceptionResult> getResponseBodyForInterception(Network.InterceptionId interceptionId) {
            return call("Network.getResponseBodyForInterception", params("interceptionId", interceptionId), GetResponseBodyForInterceptionResult::fromMap);
        }
        /**
         * Returns content served for the given currently intercepted request.
         * <p><b>Experimental:</b> this part of CDP may change without notice.
         * @param request request parameters
         * @return a stage completing with the command result
         */
        public CompletionStage<GetResponseBodyForInterceptionResult> getResponseBodyForInterception(GetResponseBodyForInterceptionRequest request) {
            return call("Network.getResponseBodyForInterception", request, GetResponseBodyForInterceptionResult::fromMap);
        }
        /**
         * Returns a handle to the stream representing the response body. Note that after this command, the intercepted request can&#x27;t be continued as is -- you either need to cancel it or to provide the response body. The stream only supports sequential read, IO.read will fail if the position is specified.
         * <p><b>Experimental:</b> this part of CDP may change without notice.
         * @param interceptionId protocol value
         * @return a stage completing with the command result
         */
        public CompletionStage<IO.StreamHandle> takeResponseBodyForInterceptionAsStream(Network.InterceptionId interceptionId) {
            return call("Network.takeResponseBodyForInterceptionAsStream", params("interceptionId", interceptionId), STREAM::require);
        }
        /**
         * Returns a handle to the stream representing the response body. Note that after this command, the intercepted request can&#x27;t be continued as is -- you either need to cancel it or to provide the response body. The stream only supports sequential read, IO.read will fail if the position is specified.
         * <p><b>Experimental:</b> this part of CDP may change without notice.
         * @param request request parameters
         * @return a stage completing with the command result
         */
        public CompletionStage<IO.StreamHandle> takeResponseBodyForInterceptionAsStream(TakeResponseBodyForInterceptionAsStreamRequest request) {
            return call("Network.takeResponseBodyForInterceptionAsStream", request, STREAM::require);
        }
        /**
         * This method sends a new XMLHttpRequest which is identical to the original one. The following parameters should be identical: method, url, async, request body, extra headers, withCredentials attribute, user, password.
         * <p><b>Experimental:</b> this part of CDP may change without notice.
         * @param requestId protocol value
         * @return a stage completing when the command completes
         */
        public CompletionStage<Void> replayXHR(Network.RequestId requestId) {
            return call("Network.replayXHR", params("requestId", requestId));
        }
        /**
         * This method sends a new XMLHttpRequest which is identical to the original one. The following parameters should be identical: method, url, async, request body, extra headers, withCredentials attribute, user, password.
         * <p><b>Experimental:</b> this part of CDP may change without notice.
         * @param request request parameters
         * @return a stage completing when the command completes
         */
        public CompletionStage<Void> replayXHR(ReplayXHRRequest request) {
            return call("Network.replayXHR", request);
        }
        /**
         * Searches for given string in response content.
         * <p><b>Experimental:</b> this part of CDP may change without notice.
         * @param requestId protocol value
         * @param query protocol value
         * @param caseSensitive protocol value
         * @param isRegex protocol value
         * @return a stage completing with the command result
         */
        public CompletionStage<java.util.List<Debugger.SearchMatch>> searchInResponseBody(Network.RequestId requestId, String query, @Nullable Boolean caseSensitive, @Nullable Boolean isRegex) {
            return call("Network.searchInResponseBody", params("requestId", requestId, "query", query, "caseSensitive", caseSensitive, "isRegex", isRegex), RESULT_2::require);
        }
        /**
         * Searches for given string in response content.
         * <p><b>Experimental:</b> this part of CDP may change without notice.
         * @param requestId protocol value
         * @param query protocol value
         * @return a stage completing with the command result
         */
        public CompletionStage<java.util.List<Debugger.SearchMatch>> searchInResponseBody(Network.RequestId requestId, String query) {
            return call("Network.searchInResponseBody", params("requestId", requestId, "query", query), RESULT_2::require);
        }
        /**
         * Searches for given string in response content.
         * <p><b>Experimental:</b> this part of CDP may change without notice.
         * @param request request parameters
         * @return a stage completing with the command result
         */
        public CompletionStage<java.util.List<Debugger.SearchMatch>> searchInResponseBody(SearchInResponseBodyRequest request) {
            return call("Network.searchInResponseBody", request, RESULT_2::require);
        }
        /**
         * Blocks URLs from loading.
         * <p><b>Experimental:</b> this part of CDP may change without notice.
         * @param urlPatterns protocol value
         * @param urls protocol value
         * @return a stage completing when the command completes
         */
        public CompletionStage<Void> setBlockedURLs(java.util.@Nullable List<Network.BlockPattern> urlPatterns, java.util.@Nullable List<String> urls) {
            return call("Network.setBlockedURLs", params("urlPatterns", urlPatterns, "urls", urls));
        }
        /**
         * Blocks URLs from loading.
         * <p><b>Experimental:</b> this part of CDP may change without notice.
         * @return a stage completing when the command completes
         */
        public CompletionStage<Void> setBlockedURLs() {
            return call("Network.setBlockedURLs");
        }
        /**
         * Blocks URLs from loading.
         * <p><b>Experimental:</b> this part of CDP may change without notice.
         * @param request request parameters
         * @return a stage completing when the command completes
         */
        public CompletionStage<Void> setBlockedURLs(SetBlockedURLsRequest request) {
            return call("Network.setBlockedURLs", request);
        }
        /**
         * Toggles ignoring of service worker for each request.
         * @param bypass protocol value
         * @return a stage completing when the command completes
         */
        public CompletionStage<Void> setBypassServiceWorker(boolean bypass) {
            return call("Network.setBypassServiceWorker", params("bypass", bypass));
        }
        /**
         * Toggles ignoring of service worker for each request.
         * @param request request parameters
         * @return a stage completing when the command completes
         */
        public CompletionStage<Void> setBypassServiceWorker(SetBypassServiceWorkerRequest request) {
            return call("Network.setBypassServiceWorker", request);
        }
        /**
         * Toggles ignoring cache for each request. If {@code true}, cache will not be used.
         * @param cacheDisabled protocol value
         * @return a stage completing when the command completes
         */
        public CompletionStage<Void> setCacheDisabled(boolean cacheDisabled) {
            return call("Network.setCacheDisabled", params("cacheDisabled", cacheDisabled));
        }
        /**
         * Toggles ignoring cache for each request. If {@code true}, cache will not be used.
         * @param request request parameters
         * @return a stage completing when the command completes
         */
        public CompletionStage<Void> setCacheDisabled(SetCacheDisabledRequest request) {
            return call("Network.setCacheDisabled", request);
        }
        /**
         * Sets a cookie with the given cookie data; may overwrite equivalent cookies if they exist.
         * @param name protocol value
         * @param value protocol value
         * @param url protocol value
         * @param domain protocol value
         * @param path protocol value
         * @param secure protocol value
         * @param httpOnly protocol value
         * @param sameSite protocol value
         * @param expires protocol value
         * @param priority protocol value
         * @param sourceScheme protocol value
         * @param sourcePort protocol value
         * @param partitionKey protocol value
         * @return a stage completing with the command result
         */
        public CompletionStage<Boolean> setCookie(String name, String value, @Nullable String url, @Nullable String domain, @Nullable String path, @Nullable Boolean secure, @Nullable Boolean httpOnly, Network.@Nullable CookieSameSite sameSite, Network.@Nullable TimeSinceEpoch expires, Network.@Nullable CookiePriority priority, Network.@Nullable CookieSourceScheme sourceScheme, @Nullable Long sourcePort, Network.@Nullable CookiePartitionKey partitionKey) {
            return call("Network.setCookie", params("name", name, "value", value, "url", url, "domain", domain, "path", path, "secure", secure, "httpOnly", httpOnly, "sameSite", sameSite, "expires", expires, "priority", priority, "sourceScheme", sourceScheme, "sourcePort", sourcePort, "partitionKey", partitionKey), SUCCESS::require);
        }
        /**
         * Sets a cookie with the given cookie data; may overwrite equivalent cookies if they exist.
         * @param name protocol value
         * @param value protocol value
         * @return a stage completing with the command result
         */
        public CompletionStage<Boolean> setCookie(String name, String value) {
            return call("Network.setCookie", params("name", name, "value", value), SUCCESS::require);
        }
        /**
         * Sets a cookie with the given cookie data; may overwrite equivalent cookies if they exist.
         * @param request request parameters
         * @return a stage completing with the command result
         */
        public CompletionStage<Boolean> setCookie(SetCookieRequest request) {
            return call("Network.setCookie", request, SUCCESS::require);
        }
        /**
         * Sets given cookies.
         * @param cookies protocol value
         * @return a stage completing when the command completes
         */
        public CompletionStage<Void> setCookies(java.util.List<Network.CookieParam> cookies) {
            return call("Network.setCookies", params("cookies", cookies));
        }
        /**
         * Sets given cookies.
         * @param request request parameters
         * @return a stage completing when the command completes
         */
        public CompletionStage<Void> setCookies(SetCookiesRequest request) {
            return call("Network.setCookies", request);
        }
        /**
         * Specifies whether to always send extra HTTP headers with the requests from this page.
         * @param headers protocol value
         * @return a stage completing when the command completes
         */
        public CompletionStage<Void> setExtraHTTPHeaders(java.util.Map<String, Object> headers) {
            return call("Network.setExtraHTTPHeaders", params("headers", headers));
        }
        /**
         * Specifies whether to always send extra HTTP headers with the requests from this page.
         * @param request request parameters
         * @return a stage completing when the command completes
         */
        public CompletionStage<Void> setExtraHTTPHeaders(SetExtraHTTPHeadersRequest request) {
            return call("Network.setExtraHTTPHeaders", request);
        }
        /**
         * Specifies whether to attach a page script stack id in requests
         * <p><b>Experimental:</b> this part of CDP may change without notice.
         * @param enabled protocol value
         * @return a stage completing when the command completes
         */
        public CompletionStage<Void> setAttachDebugStack(boolean enabled) {
            return call("Network.setAttachDebugStack", params("enabled", enabled));
        }
        /**
         * Specifies whether to attach a page script stack id in requests
         * <p><b>Experimental:</b> this part of CDP may change without notice.
         * @param request request parameters
         * @return a stage completing when the command completes
         */
        public CompletionStage<Void> setAttachDebugStack(SetAttachDebugStackRequest request) {
            return call("Network.setAttachDebugStack", request);
        }
        /**
         * Sets the requests to intercept that match the provided patterns and optionally resource types. Deprecated, please use Fetch.enable instead.
         * <p><b>Experimental:</b> this part of CDP may change without notice.
         * @param patterns protocol value
         * @return a stage completing when the command completes
         * @deprecated Deprecated by the Chromium DevTools Protocol.
         */
        @Deprecated
        public CompletionStage<Void> setRequestInterception(java.util.List<Network.RequestPattern> patterns) {
            return call("Network.setRequestInterception", params("patterns", patterns));
        }
        /**
         * Sets the requests to intercept that match the provided patterns and optionally resource types. Deprecated, please use Fetch.enable instead.
         * <p><b>Experimental:</b> this part of CDP may change without notice.
         * @param request request parameters
         * @return a stage completing when the command completes
         * @deprecated Deprecated by the Chromium DevTools Protocol.
         */
        @Deprecated
        public CompletionStage<Void> setRequestInterception(SetRequestInterceptionRequest request) {
            return call("Network.setRequestInterception", request);
        }
        /**
         * Allows overriding user agent with the given string.
         * @param userAgent protocol value
         * @param acceptLanguage protocol value
         * @param platform protocol value
         * @param userAgentMetadata protocol value
         * @return a stage completing when the command completes
         */
        public CompletionStage<Void> setUserAgentOverride(String userAgent, @Nullable String acceptLanguage, @Nullable String platform, Emulation.@Nullable UserAgentMetadata userAgentMetadata) {
            return call("Network.setUserAgentOverride", params("userAgent", userAgent, "acceptLanguage", acceptLanguage, "platform", platform, "userAgentMetadata", userAgentMetadata));
        }
        /**
         * Allows overriding user agent with the given string.
         * @param userAgent protocol value
         * @return a stage completing when the command completes
         */
        public CompletionStage<Void> setUserAgentOverride(String userAgent) {
            return call("Network.setUserAgentOverride", params("userAgent", userAgent));
        }
        /**
         * Allows overriding user agent with the given string.
         * @param request request parameters
         * @return a stage completing when the command completes
         */
        public CompletionStage<Void> setUserAgentOverride(SetUserAgentOverrideRequest request) {
            return call("Network.setUserAgentOverride", request);
        }
        /**
         * Enables streaming of the response for the given requestId. If enabled, the dataReceived event contains the data that was received during streaming.
         * <p><b>Experimental:</b> this part of CDP may change without notice.
         * @param requestId protocol value
         * @return a stage completing with the command result
         */
        public CompletionStage<String> streamResourceContent(Network.RequestId requestId) {
            return call("Network.streamResourceContent", params("requestId", requestId), BUFFERED_DATA::require);
        }
        /**
         * Enables streaming of the response for the given requestId. If enabled, the dataReceived event contains the data that was received during streaming.
         * <p><b>Experimental:</b> this part of CDP may change without notice.
         * @param request request parameters
         * @return a stage completing with the command result
         */
        public CompletionStage<String> streamResourceContent(StreamResourceContentRequest request) {
            return call("Network.streamResourceContent", request, BUFFERED_DATA::require);
        }
        /**
         * Returns information about the COEP/COOP isolation status.
         * <p><b>Experimental:</b> this part of CDP may change without notice.
         * @param frameId protocol value
         * @return a stage completing with the command result
         */
        public CompletionStage<Network.SecurityIsolationStatus> getSecurityIsolationStatus(Page.FrameId frameId) {
            return call("Network.getSecurityIsolationStatus", params("frameId", frameId), STATUS_3::require);
        }
        /**
         * Returns information about the COEP/COOP isolation status.
         * <p><b>Experimental:</b> this part of CDP may change without notice.
         * @return a stage completing with the command result
         */
        public CompletionStage<Network.SecurityIsolationStatus> getSecurityIsolationStatus() {
            return call("Network.getSecurityIsolationStatus", STATUS_3::require);
        }
        /**
         * Returns information about the COEP/COOP isolation status.
         * <p><b>Experimental:</b> this part of CDP may change without notice.
         * @param request request parameters
         * @return a stage completing with the command result
         */
        public CompletionStage<Network.SecurityIsolationStatus> getSecurityIsolationStatus(GetSecurityIsolationStatusRequest request) {
            return call("Network.getSecurityIsolationStatus", request, STATUS_3::require);
        }
        /**
         * Enables tracking for the Reporting API, events generated by the Reporting API will now be delivered to the client. Enabling triggers &#x27;reportingApiReportAdded&#x27; for all existing reports.
         * <p><b>Experimental:</b> this part of CDP may change without notice.
         * @param enable protocol value
         * @return a stage completing when the command completes
         */
        public CompletionStage<Void> enableReportingApi(boolean enable) {
            return call("Network.enableReportingApi", params("enable", enable));
        }
        /**
         * Enables tracking for the Reporting API, events generated by the Reporting API will now be delivered to the client. Enabling triggers &#x27;reportingApiReportAdded&#x27; for all existing reports.
         * <p><b>Experimental:</b> this part of CDP may change without notice.
         * @param request request parameters
         * @return a stage completing when the command completes
         */
        public CompletionStage<Void> enableReportingApi(EnableReportingApiRequest request) {
            return call("Network.enableReportingApi", request);
        }
        /**
         * Sets up tracking device bound sessions and fetching of initial set of sessions.
         * <p><b>Experimental:</b> this part of CDP may change without notice.
         * @param enable protocol value
         * @return a stage completing when the command completes
         */
        public CompletionStage<Void> enableDeviceBoundSessions(boolean enable) {
            return call("Network.enableDeviceBoundSessions", params("enable", enable));
        }
        /**
         * Sets up tracking device bound sessions and fetching of initial set of sessions.
         * <p><b>Experimental:</b> this part of CDP may change without notice.
         * @param request request parameters
         * @return a stage completing when the command completes
         */
        public CompletionStage<Void> enableDeviceBoundSessions(EnableDeviceBoundSessionsRequest request) {
            return call("Network.enableDeviceBoundSessions", request);
        }
        /**
         * Deletes a device bound session.
         * <p><b>Experimental:</b> this part of CDP may change without notice.
         * @param key protocol value
         * @return a stage completing when the command completes
         */
        public CompletionStage<Void> deleteDeviceBoundSession(Network.DeviceBoundSessionKey key) {
            return call("Network.deleteDeviceBoundSession", params("key", key));
        }
        /**
         * Deletes a device bound session.
         * <p><b>Experimental:</b> this part of CDP may change without notice.
         * @param request request parameters
         * @return a stage completing when the command completes
         */
        public CompletionStage<Void> deleteDeviceBoundSession(DeleteDeviceBoundSessionRequest request) {
            return call("Network.deleteDeviceBoundSession", request);
        }
        /**
         * Fetches the schemeful site for a specific origin.
         * <p><b>Experimental:</b> this part of CDP may change without notice.
         * @param origin protocol value
         * @return a stage completing with the command result
         */
        public CompletionStage<String> fetchSchemefulSite(String origin) {
            return call("Network.fetchSchemefulSite", params("origin", origin), SCHEMEFUL_SITE::require);
        }
        /**
         * Fetches the schemeful site for a specific origin.
         * <p><b>Experimental:</b> this part of CDP may change without notice.
         * @param request request parameters
         * @return a stage completing with the command result
         */
        public CompletionStage<String> fetchSchemefulSite(FetchSchemefulSiteRequest request) {
            return call("Network.fetchSchemefulSite", request, SCHEMEFUL_SITE::require);
        }
        /**
         * Fetches the resource and returns the content.
         * <p><b>Experimental:</b> this part of CDP may change without notice.
         * @param frameId protocol value
         * @param url protocol value
         * @param options protocol value
         * @return a stage completing with the command result
         */
        public CompletionStage<Network.LoadNetworkResourcePageResult> loadNetworkResource(Page.FrameId frameId, String url, Network.LoadNetworkResourceOptions options) {
            return call("Network.loadNetworkResource", params("frameId", frameId, "url", url, "options", options), RESOURCE::require);
        }
        /**
         * Fetches the resource and returns the content.
         * <p><b>Experimental:</b> this part of CDP may change without notice.
         * @param url protocol value
         * @param options protocol value
         * @return a stage completing with the command result
         */
        public CompletionStage<Network.LoadNetworkResourcePageResult> loadNetworkResource(String url, Network.LoadNetworkResourceOptions options) {
            return call("Network.loadNetworkResource", params("url", url, "options", options), RESOURCE::require);
        }
        /**
         * Fetches the resource and returns the content.
         * <p><b>Experimental:</b> this part of CDP may change without notice.
         * @param request request parameters
         * @return a stage completing with the command result
         */
        public CompletionStage<Network.LoadNetworkResourcePageResult> loadNetworkResource(LoadNetworkResourceRequest request) {
            return call("Network.loadNetworkResource", request, RESOURCE::require);
        }
        /**
         * Sets Controls for third-party cookie access Page reload is required before the new cookie behavior will be observed
         * <p><b>Experimental:</b> this part of CDP may change without notice.
         * @param enableThirdPartyCookieRestriction protocol value
         * @return a stage completing when the command completes
         */
        public CompletionStage<Void> setCookieControls(boolean enableThirdPartyCookieRestriction) {
            return call("Network.setCookieControls", params("enableThirdPartyCookieRestriction", enableThirdPartyCookieRestriction));
        }
        /**
         * Sets Controls for third-party cookie access Page reload is required before the new cookie behavior will be observed
         * <p><b>Experimental:</b> this part of CDP may change without notice.
         * @param request request parameters
         * @return a stage completing when the command completes
         */
        public CompletionStage<Void> setCookieControls(SetCookieControlsRequest request) {
            return call("Network.setCookieControls", request);
        }
        /**
         * Fired when data chunk was received over the network.
         * @param handler event callback
         * @return a removable subscription
         */
        public CdpSubscription onDataReceived(Consumer<DataReceivedEvent> handler) {
            return on("Network.dataReceived", DataReceivedEvent::fromMap, handler);
        }
        /**
         * Fired when EventSource message is received.
         * @param handler event callback
         * @return a removable subscription
         */
        public CdpSubscription onEventSourceMessageReceived(Consumer<EventSourceMessageReceivedEvent> handler) {
            return on("Network.eventSourceMessageReceived", EventSourceMessageReceivedEvent::fromMap, handler);
        }
        /**
         * Fired when HTTP request has failed to load.
         * @param handler event callback
         * @return a removable subscription
         */
        public CdpSubscription onLoadingFailed(Consumer<LoadingFailedEvent> handler) {
            return on("Network.loadingFailed", LoadingFailedEvent::fromMap, handler);
        }
        /**
         * Fired when HTTP request has finished loading.
         * @param handler event callback
         * @return a removable subscription
         */
        public CdpSubscription onLoadingFinished(Consumer<LoadingFinishedEvent> handler) {
            return on("Network.loadingFinished", LoadingFinishedEvent::fromMap, handler);
        }
        /**
         * Details of an intercepted HTTP request, which must be either allowed, blocked, modified or mocked. Deprecated, use Fetch.requestPaused instead.
         * <p><b>Experimental:</b> this part of CDP may change without notice.
         * @param handler event callback
         * @return a removable subscription
         * @deprecated Deprecated by the Chromium DevTools Protocol.
         */
        @Deprecated
        public CdpSubscription onRequestIntercepted(Consumer<RequestInterceptedEvent> handler) {
            return on("Network.requestIntercepted", RequestInterceptedEvent::fromMap, handler);
        }
        /**
         * Fired if request ended up loading from cache.
         * @param handler event callback
         * @return a removable subscription
         */
        public CdpSubscription onRequestServedFromCache(Consumer<RequestServedFromCacheEvent> handler) {
            return on("Network.requestServedFromCache", RequestServedFromCacheEvent::fromMap, handler);
        }
        /**
         * Fired when page is about to send HTTP request.
         * @param handler event callback
         * @return a removable subscription
         */
        public CdpSubscription onRequestWillBeSent(Consumer<RequestWillBeSentEvent> handler) {
            return on("Network.requestWillBeSent", RequestWillBeSentEvent::fromMap, handler);
        }
        /**
         * Fired when resource loading priority is changed
         * <p><b>Experimental:</b> this part of CDP may change without notice.
         * @param handler event callback
         * @return a removable subscription
         */
        public CdpSubscription onResourceChangedPriority(Consumer<ResourceChangedPriorityEvent> handler) {
            return on("Network.resourceChangedPriority", ResourceChangedPriorityEvent::fromMap, handler);
        }
        /**
         * Fired when a signed exchange was received over the network
         * <p><b>Experimental:</b> this part of CDP may change without notice.
         * @param handler event callback
         * @return a removable subscription
         */
        public CdpSubscription onSignedExchangeReceived(Consumer<SignedExchangeReceivedEvent> handler) {
            return on("Network.signedExchangeReceived", SignedExchangeReceivedEvent::fromMap, handler);
        }
        /**
         * Fired when HTTP response is available.
         * @param handler event callback
         * @return a removable subscription
         */
        public CdpSubscription onResponseReceived(Consumer<ResponseReceivedEvent> handler) {
            return on("Network.responseReceived", ResponseReceivedEvent::fromMap, handler);
        }
        /**
         * Fired when WebSocket is closed.
         * @param handler event callback
         * @return a removable subscription
         */
        public CdpSubscription onWebSocketClosed(Consumer<WebSocketClosedEvent> handler) {
            return on("Network.webSocketClosed", WebSocketClosedEvent::fromMap, handler);
        }
        /**
         * Fired upon WebSocket creation.
         * @param handler event callback
         * @return a removable subscription
         */
        public CdpSubscription onWebSocketCreated(Consumer<WebSocketCreatedEvent> handler) {
            return on("Network.webSocketCreated", WebSocketCreatedEvent::fromMap, handler);
        }
        /**
         * Fired when WebSocket message error occurs.
         * @param handler event callback
         * @return a removable subscription
         */
        public CdpSubscription onWebSocketFrameError(Consumer<WebSocketFrameErrorEvent> handler) {
            return on("Network.webSocketFrameError", WebSocketFrameErrorEvent::fromMap, handler);
        }
        /**
         * Fired when WebSocket message is received.
         * @param handler event callback
         * @return a removable subscription
         */
        public CdpSubscription onWebSocketFrameReceived(Consumer<WebSocketFrameReceivedEvent> handler) {
            return on("Network.webSocketFrameReceived", WebSocketFrameReceivedEvent::fromMap, handler);
        }
        /**
         * Fired when WebSocket message is sent.
         * @param handler event callback
         * @return a removable subscription
         */
        public CdpSubscription onWebSocketFrameSent(Consumer<WebSocketFrameSentEvent> handler) {
            return on("Network.webSocketFrameSent", WebSocketFrameSentEvent::fromMap, handler);
        }
        /**
         * Fired when WebSocket handshake response becomes available.
         * @param handler event callback
         * @return a removable subscription
         */
        public CdpSubscription onWebSocketHandshakeResponseReceived(Consumer<WebSocketHandshakeResponseReceivedEvent> handler) {
            return on("Network.webSocketHandshakeResponseReceived", WebSocketHandshakeResponseReceivedEvent::fromMap, handler);
        }
        /**
         * Fired when WebSocket is about to initiate handshake.
         * @param handler event callback
         * @return a removable subscription
         */
        public CdpSubscription onWebSocketWillSendHandshakeRequest(Consumer<WebSocketWillSendHandshakeRequestEvent> handler) {
            return on("Network.webSocketWillSendHandshakeRequest", WebSocketWillSendHandshakeRequestEvent::fromMap, handler);
        }
        /**
         * Fired upon WebTransport creation.
         * @param handler event callback
         * @return a removable subscription
         */
        public CdpSubscription onWebTransportCreated(Consumer<WebTransportCreatedEvent> handler) {
            return on("Network.webTransportCreated", WebTransportCreatedEvent::fromMap, handler);
        }
        /**
         * Fired when WebTransport handshake is finished.
         * @param handler event callback
         * @return a removable subscription
         */
        public CdpSubscription onWebTransportConnectionEstablished(Consumer<WebTransportConnectionEstablishedEvent> handler) {
            return on("Network.webTransportConnectionEstablished", WebTransportConnectionEstablishedEvent::fromMap, handler);
        }
        /**
         * Fired when WebTransport is disposed.
         * @param handler event callback
         * @return a removable subscription
         */
        public CdpSubscription onWebTransportClosed(Consumer<WebTransportClosedEvent> handler) {
            return on("Network.webTransportClosed", WebTransportClosedEvent::fromMap, handler);
        }
        /**
         * Fired upon direct_socket.TCPSocket creation.
         * <p><b>Experimental:</b> this part of CDP may change without notice.
         * @param handler event callback
         * @return a removable subscription
         */
        public CdpSubscription onDirectTCPSocketCreated(Consumer<DirectTCPSocketCreatedEvent> handler) {
            return on("Network.directTCPSocketCreated", DirectTCPSocketCreatedEvent::fromMap, handler);
        }
        /**
         * Fired when direct_socket.TCPSocket connection is opened.
         * <p><b>Experimental:</b> this part of CDP may change without notice.
         * @param handler event callback
         * @return a removable subscription
         */
        public CdpSubscription onDirectTCPSocketOpened(Consumer<DirectTCPSocketOpenedEvent> handler) {
            return on("Network.directTCPSocketOpened", DirectTCPSocketOpenedEvent::fromMap, handler);
        }
        /**
         * Fired when direct_socket.TCPSocket is aborted.
         * <p><b>Experimental:</b> this part of CDP may change without notice.
         * @param handler event callback
         * @return a removable subscription
         */
        public CdpSubscription onDirectTCPSocketAborted(Consumer<DirectTCPSocketAbortedEvent> handler) {
            return on("Network.directTCPSocketAborted", DirectTCPSocketAbortedEvent::fromMap, handler);
        }
        /**
         * Fired when direct_socket.TCPSocket is closed.
         * <p><b>Experimental:</b> this part of CDP may change without notice.
         * @param handler event callback
         * @return a removable subscription
         */
        public CdpSubscription onDirectTCPSocketClosed(Consumer<DirectTCPSocketClosedEvent> handler) {
            return on("Network.directTCPSocketClosed", DirectTCPSocketClosedEvent::fromMap, handler);
        }
        /**
         * Fired when data is sent to tcp direct socket stream.
         * <p><b>Experimental:</b> this part of CDP may change without notice.
         * @param handler event callback
         * @return a removable subscription
         */
        public CdpSubscription onDirectTCPSocketChunkSent(Consumer<DirectTCPSocketChunkSentEvent> handler) {
            return on("Network.directTCPSocketChunkSent", DirectTCPSocketChunkSentEvent::fromMap, handler);
        }
        /**
         * Fired when data is received from tcp direct socket stream.
         * <p><b>Experimental:</b> this part of CDP may change without notice.
         * @param handler event callback
         * @return a removable subscription
         */
        public CdpSubscription onDirectTCPSocketChunkReceived(Consumer<DirectTCPSocketChunkReceivedEvent> handler) {
            return on("Network.directTCPSocketChunkReceived", DirectTCPSocketChunkReceivedEvent::fromMap, handler);
        }
        /**
         * Subscribes to Network.directUDPSocketJoinedMulticastGroup.
         * <p><b>Experimental:</b> this part of CDP may change without notice.
         * @param handler event callback
         * @return a removable subscription
         */
        public CdpSubscription onDirectUDPSocketJoinedMulticastGroup(Consumer<DirectUDPSocketJoinedMulticastGroupEvent> handler) {
            return on("Network.directUDPSocketJoinedMulticastGroup", DirectUDPSocketJoinedMulticastGroupEvent::fromMap, handler);
        }
        /**
         * Subscribes to Network.directUDPSocketLeftMulticastGroup.
         * <p><b>Experimental:</b> this part of CDP may change without notice.
         * @param handler event callback
         * @return a removable subscription
         */
        public CdpSubscription onDirectUDPSocketLeftMulticastGroup(Consumer<DirectUDPSocketLeftMulticastGroupEvent> handler) {
            return on("Network.directUDPSocketLeftMulticastGroup", DirectUDPSocketLeftMulticastGroupEvent::fromMap, handler);
        }
        /**
         * Fired upon direct_socket.UDPSocket creation.
         * <p><b>Experimental:</b> this part of CDP may change without notice.
         * @param handler event callback
         * @return a removable subscription
         */
        public CdpSubscription onDirectUDPSocketCreated(Consumer<DirectUDPSocketCreatedEvent> handler) {
            return on("Network.directUDPSocketCreated", DirectUDPSocketCreatedEvent::fromMap, handler);
        }
        /**
         * Fired when direct_socket.UDPSocket connection is opened.
         * <p><b>Experimental:</b> this part of CDP may change without notice.
         * @param handler event callback
         * @return a removable subscription
         */
        public CdpSubscription onDirectUDPSocketOpened(Consumer<DirectUDPSocketOpenedEvent> handler) {
            return on("Network.directUDPSocketOpened", DirectUDPSocketOpenedEvent::fromMap, handler);
        }
        /**
         * Fired when direct_socket.UDPSocket is aborted.
         * <p><b>Experimental:</b> this part of CDP may change without notice.
         * @param handler event callback
         * @return a removable subscription
         */
        public CdpSubscription onDirectUDPSocketAborted(Consumer<DirectUDPSocketAbortedEvent> handler) {
            return on("Network.directUDPSocketAborted", DirectUDPSocketAbortedEvent::fromMap, handler);
        }
        /**
         * Fired when direct_socket.UDPSocket is closed.
         * <p><b>Experimental:</b> this part of CDP may change without notice.
         * @param handler event callback
         * @return a removable subscription
         */
        public CdpSubscription onDirectUDPSocketClosed(Consumer<DirectUDPSocketClosedEvent> handler) {
            return on("Network.directUDPSocketClosed", DirectUDPSocketClosedEvent::fromMap, handler);
        }
        /**
         * Fired when message is sent to udp direct socket stream.
         * <p><b>Experimental:</b> this part of CDP may change without notice.
         * @param handler event callback
         * @return a removable subscription
         */
        public CdpSubscription onDirectUDPSocketChunkSent(Consumer<DirectUDPSocketChunkSentEvent> handler) {
            return on("Network.directUDPSocketChunkSent", DirectUDPSocketChunkSentEvent::fromMap, handler);
        }
        /**
         * Fired when message is received from udp direct socket stream.
         * <p><b>Experimental:</b> this part of CDP may change without notice.
         * @param handler event callback
         * @return a removable subscription
         */
        public CdpSubscription onDirectUDPSocketChunkReceived(Consumer<DirectUDPSocketChunkReceivedEvent> handler) {
            return on("Network.directUDPSocketChunkReceived", DirectUDPSocketChunkReceivedEvent::fromMap, handler);
        }
        /**
         * Fired when additional information about a requestWillBeSent event is available from the network stack. Not every requestWillBeSent event will have an additional requestWillBeSentExtraInfo fired for it, and there is no guarantee whether requestWillBeSent or requestWillBeSentExtraInfo will be fired first for the same request.
         * <p><b>Experimental:</b> this part of CDP may change without notice.
         * @param handler event callback
         * @return a removable subscription
         */
        public CdpSubscription onRequestWillBeSentExtraInfo(Consumer<RequestWillBeSentExtraInfoEvent> handler) {
            return on("Network.requestWillBeSentExtraInfo", RequestWillBeSentExtraInfoEvent::fromMap, handler);
        }
        /**
         * Fired when additional information about a responseReceived event is available from the network stack. Not every responseReceived event will have an additional responseReceivedExtraInfo for it, and responseReceivedExtraInfo may be fired before or after responseReceived.
         * <p><b>Experimental:</b> this part of CDP may change without notice.
         * @param handler event callback
         * @return a removable subscription
         */
        public CdpSubscription onResponseReceivedExtraInfo(Consumer<ResponseReceivedExtraInfoEvent> handler) {
            return on("Network.responseReceivedExtraInfo", ResponseReceivedExtraInfoEvent::fromMap, handler);
        }
        /**
         * Fired when 103 Early Hints headers is received in addition to the common response. Not every responseReceived event will have an responseReceivedEarlyHints fired. Only one responseReceivedEarlyHints may be fired for eached responseReceived event.
         * <p><b>Experimental:</b> this part of CDP may change without notice.
         * @param handler event callback
         * @return a removable subscription
         */
        public CdpSubscription onResponseReceivedEarlyHints(Consumer<ResponseReceivedEarlyHintsEvent> handler) {
            return on("Network.responseReceivedEarlyHints", ResponseReceivedEarlyHintsEvent::fromMap, handler);
        }
        /**
         * Fired exactly once for each Trust Token operation. Depending on the type of the operation and whether the operation succeeded or failed, the event is fired before the corresponding request was sent or after the response was received.
         * <p><b>Experimental:</b> this part of CDP may change without notice.
         * @param handler event callback
         * @return a removable subscription
         */
        public CdpSubscription onTrustTokenOperationDone(Consumer<TrustTokenOperationDoneEvent> handler) {
            return on("Network.trustTokenOperationDone", TrustTokenOperationDoneEvent::fromMap, handler);
        }
        /**
         * Fired once security policy has been updated.
         * <p><b>Experimental:</b> this part of CDP may change without notice.
         * @param handler event callback
         * @return a removable subscription
         */
        public CdpSubscription onPolicyUpdated(Consumer<PolicyUpdatedEvent> handler) {
            return on("Network.policyUpdated", PolicyUpdatedEvent::fromMap, handler);
        }
        /**
         * Is sent whenever a new report is added. And after &#x27;enableReportingApi&#x27; for all existing reports.
         * <p><b>Experimental:</b> this part of CDP may change without notice.
         * @param handler event callback
         * @return a removable subscription
         */
        public CdpSubscription onReportingApiReportAdded(Consumer<ReportingApiReportAddedEvent> handler) {
            return on("Network.reportingApiReportAdded", ReportingApiReportAddedEvent::fromMap, handler);
        }
        /**
         * Subscribes to Network.reportingApiReportUpdated.
         * <p><b>Experimental:</b> this part of CDP may change without notice.
         * @param handler event callback
         * @return a removable subscription
         */
        public CdpSubscription onReportingApiReportUpdated(Consumer<ReportingApiReportUpdatedEvent> handler) {
            return on("Network.reportingApiReportUpdated", ReportingApiReportUpdatedEvent::fromMap, handler);
        }
        /**
         * Subscribes to Network.reportingApiEndpointsChangedForOrigin.
         * <p><b>Experimental:</b> this part of CDP may change without notice.
         * @param handler event callback
         * @return a removable subscription
         */
        public CdpSubscription onReportingApiEndpointsChangedForOrigin(Consumer<ReportingApiEndpointsChangedForOriginEvent> handler) {
            return on("Network.reportingApiEndpointsChangedForOrigin", ReportingApiEndpointsChangedForOriginEvent::fromMap, handler);
        }
        /**
         * Triggered when the initial set of device bound sessions is added.
         * <p><b>Experimental:</b> this part of CDP may change without notice.
         * @param handler event callback
         * @return a removable subscription
         */
        public CdpSubscription onDeviceBoundSessionsAdded(Consumer<DeviceBoundSessionsAddedEvent> handler) {
            return on("Network.deviceBoundSessionsAdded", DeviceBoundSessionsAddedEvent::fromMap, handler);
        }
        /**
         * Triggered when a device bound session event occurs.
         * <p><b>Experimental:</b> this part of CDP may change without notice.
         * @param handler event callback
         * @return a removable subscription
         */
        public CdpSubscription onDeviceBoundSessionEventOccurred(Consumer<DeviceBoundSessionEventOccurredEvent> handler) {
            return on("Network.deviceBoundSessionEventOccurred", DeviceBoundSessionEventOccurredEvent::fromMap, handler);
        }
    }
}
