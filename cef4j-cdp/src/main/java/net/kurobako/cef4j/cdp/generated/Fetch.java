// GENERATED - do not edit. Regenerate via: ./mvnw generate-sources -pl cef4j-cdp -Dcef.version=150.0.18+gdb11278+chromium-150.0.7871.213
package net.kurobako.cef4j.cdp.generated;

import java.util.Map;
import java.util.Optional;
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
 * A domain for letting clients substitute browser&#x27;s network layer with client code.
 * @see <a href="https://chromium.googlesource.com/chromium/src/+/refs/tags/150.0.7871.213/third_party/blink/public/devtools_protocol/domains/Fetch.pdl">Pinned protocol source</a>
 */
@Generated("./mvnw generate-sources -pl cef4j-cdp -Dcef.version=150.0.18+gdb11278+chromium-150.0.7871.213")
@SuppressWarnings({"EscapedEntity", "InvalidParam", "JavaLangClash", "MissingSummary", "Unchecked", "UnusedMethod"})
@NullableBoundary("CDP wire objects use null for absent optional fields")
public final class Fetch {
    private Fetch() {}
    private static final CdpField<Fetch.AuthChallenge> AUTH_CHALLENGE = CdpField.of("authChallenge", value -> java.util.Objects.requireNonNull(Fetch.AuthChallenge.fromMap(CdpObject.requireObjectMap(value))));
    private static final CdpField<Fetch.AuthChallengeResponse> AUTH_CHALLENGE_RESPONSE = CdpField.of("authChallengeResponse", value -> java.util.Objects.requireNonNull(Fetch.AuthChallengeResponse.fromMap(CdpObject.requireObjectMap(value))));
    private static final CdpField<Network.ErrorReason> ERROR_REASON = CdpField.of("errorReason", value -> Network.ErrorReason.of((String) value));
    private static final CdpField<Page.FrameId> FRAME_ID = CdpField.of("frameId", value -> new Page.FrameId((String) value));
    private static final CdpField<java.util.List<Fetch.HeaderEntry>> HEADERS = CdpField.of("headers", value -> CdpObject.requireList(value, element0 -> java.util.Objects.requireNonNull(Fetch.HeaderEntry.fromMap(CdpObject.requireObjectMap(element0)))));
    private static final CdpField<Network.RequestId> NETWORK_ID = CdpField.of("networkId", value -> new Network.RequestId((String) value));
    private static final CdpField<java.util.List<Fetch.RequestPattern>> PATTERNS = CdpField.of("patterns", value -> CdpObject.requireList(value, element0 -> java.util.Objects.requireNonNull(Fetch.RequestPattern.fromMap(CdpObject.requireObjectMap(element0)))));
    private static final CdpField<Fetch.RequestId> REDIRECTED_REQUEST_ID = CdpField.of("redirectedRequestId", value -> new Fetch.RequestId((String) value));
    private static final CdpField<Network.Request> REQUEST = CdpField.of("request", value -> java.util.Objects.requireNonNull(Network.Request.fromMap(CdpObject.requireObjectMap(value))));
    private static final CdpField<Fetch.RequestId> REQUEST_ID = CdpField.of("requestId", value -> new Fetch.RequestId((String) value));
    private static final CdpField<Fetch.RequestStage> REQUEST_STAGE = CdpField.of("requestStage", value -> Fetch.RequestStage.of((String) value));
    private static final CdpField<Network.ResourceType> RESOURCE_TYPE = CdpField.of("resourceType", value -> Network.ResourceType.of((String) value));
    private static final CdpField<AuthChallengeResponse.ResponseValues> RESPONSE = CdpField.of("response", value -> AuthChallengeResponse.ResponseValues.of((String) value));
    private static final CdpField<Network.ErrorReason> RESPONSE_ERROR_REASON = CdpField.of("responseErrorReason", value -> Network.ErrorReason.of((String) value));
    private static final CdpField<java.util.List<Fetch.HeaderEntry>> RESPONSE_HEADERS = CdpField.of("responseHeaders", value -> CdpObject.requireList(value, element0 -> java.util.Objects.requireNonNull(Fetch.HeaderEntry.fromMap(CdpObject.requireObjectMap(element0)))));
    private static final CdpField<AuthChallenge.SourceValues> SOURCE = CdpField.of("source", value -> AuthChallenge.SourceValues.of((String) value));
    private static final CdpField<IO.StreamHandle> STREAM = CdpField.of("stream", value -> new IO.StreamHandle((String) value));
    /**
     * Unique request identifier. Note that this does not identify individual HTTP requests that are part of a network request.
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
     * Stages of the request to handle. Request will intercept before the request is sent. Response will intercept after the response is received (but before response body is received).
     */
    public enum RequestStage implements CdpValue<String> {
        REQUEST("Request"),
        RESPONSE("Response");
        public final String value;
        RequestStage(String value) { this.value = value; }
        @Override public String value() { return value; }
        public static RequestStage of(String value) {
            for (RequestStage constant : values()) {
                if (constant.value.equals(value)) return constant;
            }
            throw new IllegalArgumentException("Unknown RequestStage value: " + value);
        }
    }
    /**
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
        public Optional<Fetch.RequestStage> requestStage() {
            return optional(REQUEST_STAGE);
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
         * @param requestStage field value
         * @return this model
         */
        public RequestPattern requestStage(Fetch.RequestStage requestStage) {
            set("requestStage", requestStage);
            return this;
        }
    }
    /**
     * Response HTTP header entry
     */
    public static final class HeaderEntry extends CdpObject {
        public HeaderEntry() {}
        private HeaderEntry(Map<String, Object> values) { super(values); }
        public static HeaderEntry fromMap(Map<String, Object> values) {
            return new HeaderEntry(values);
        }
        /**
         * Returns the name field.
         * @return the protocol field value
         */
        public String name() {
            return requireString("name");
        }
        /**
         * Returns the value field.
         * @return the protocol field value
         */
        public String value() {
            return requireString("value");
        }
        /**
         * Sets the name field.
         * @param name field value
         * @return this model
         */
        public HeaderEntry name(String name) {
            set("name", name);
            return this;
        }
        /**
         * Sets the value field.
         * @param value field value
         * @return this model
         */
        public HeaderEntry value(String value) {
            set("value", value);
            return this;
        }
    }
    /**
     * Authorization challenge for HTTP status code 401 or 407.
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
            return require(RESPONSE);
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
     * Enables issuing of requestPaused events. A request will be paused until client calls one of failRequest, fulfillRequest or continueRequest/continueWithAuth.
     */
    public static final class EnableRequest extends CdpObject {
        public EnableRequest() {}
        public static EnableRequest fromMap(Map<String, Object> values) {
            final EnableRequest instance_ = new EnableRequest();
            if (values != null) instance_.values.putAll(values);
            return instance_;
        }
        /**
         * If specified, only requests matching any of these patterns will produce fetchRequested event and will be paused until clients response. If not set, all requests will be affected.
         * @return the protocol field value, empty when absent
         */
        public Optional<java.util.List<Fetch.RequestPattern>> patterns() {
            return optional(PATTERNS);
        }
        /**
         * If true, authRequired events will be issued and requests will be paused expecting a call to continueWithAuth.
         * @return the protocol field value, empty when absent
         */
        public Optional<Boolean> handleAuthRequests() {
            return optionalBoolean("handleAuthRequests");
        }
        /**
         * If specified, only requests matching any of these patterns will produce fetchRequested event and will be paused until clients response. If not set, all requests will be affected.
         * @param patterns field value
         * @return this model
         */
        public EnableRequest patterns(java.util.List<Fetch.RequestPattern> patterns) {
            set("patterns", patterns);
            return this;
        }
        /**
         * If true, authRequired events will be issued and requests will be paused expecting a call to continueWithAuth.
         * @param handleAuthRequests field value
         * @return this model
         */
        public EnableRequest handleAuthRequests(boolean handleAuthRequests) {
            set("handleAuthRequests", handleAuthRequests);
            return this;
        }
    }
    /**
     * Causes the request to fail with specified reason.
     */
    public static final class FailRequestRequest extends CdpObject {
        public FailRequestRequest() {}
        public static FailRequestRequest fromMap(Map<String, Object> values) {
            final FailRequestRequest instance_ = new FailRequestRequest();
            if (values != null) instance_.values.putAll(values);
            return instance_;
        }
        /**
         * An id the client received in requestPaused event.
         * @return the protocol field value
         */
        public Fetch.RequestId requestId() {
            return require(REQUEST_ID);
        }
        /**
         * Causes the request to fail with the given reason.
         * @return the protocol field value
         */
        public Network.ErrorReason errorReason() {
            return require(ERROR_REASON);
        }
        /**
         * An id the client received in requestPaused event.
         * @param requestId field value
         * @return this model
         */
        public FailRequestRequest requestId(Fetch.RequestId requestId) {
            set("requestId", requestId);
            return this;
        }
        /**
         * Causes the request to fail with the given reason.
         * @param errorReason field value
         * @return this model
         */
        public FailRequestRequest errorReason(Network.ErrorReason errorReason) {
            set("errorReason", errorReason);
            return this;
        }
    }
    /**
     * Provides response to the request.
     */
    public static final class FulfillRequestRequest extends CdpObject {
        public FulfillRequestRequest() {}
        public static FulfillRequestRequest fromMap(Map<String, Object> values) {
            final FulfillRequestRequest instance_ = new FulfillRequestRequest();
            if (values != null) instance_.values.putAll(values);
            return instance_;
        }
        /**
         * An id the client received in requestPaused event.
         * @return the protocol field value
         */
        public Fetch.RequestId requestId() {
            return require(REQUEST_ID);
        }
        /**
         * An HTTP response code.
         * @return the protocol field value
         */
        public long responseCode() {
            return requireLong("responseCode");
        }
        /**
         * Response headers.
         * @return the protocol field value, empty when absent
         */
        public Optional<java.util.List<Fetch.HeaderEntry>> responseHeaders() {
            return optional(RESPONSE_HEADERS);
        }
        /**
         * Alternative way of specifying response headers as a \0-separated series of name: value pairs. Prefer the above method unless you need to represent some non-UTF8 values that can&#x27;t be transmitted over the protocol as text. (Encoded as a base64 string when passed over JSON)
         * @return the protocol field value, empty when absent
         */
        public Optional<String> binaryResponseHeaders() {
            return optionalString("binaryResponseHeaders");
        }
        /**
         * A response body. If absent, original response body will be used if the request is intercepted at the response stage and empty body will be used if the request is intercepted at the request stage. (Encoded as a base64 string when passed over JSON)
         * @return the protocol field value, empty when absent
         */
        public Optional<String> body() {
            return optionalString("body");
        }
        /**
         * A textual representation of responseCode. If absent, a standard phrase matching responseCode is used.
         * @return the protocol field value, empty when absent
         */
        public Optional<String> responsePhrase() {
            return optionalString("responsePhrase");
        }
        /**
         * An id the client received in requestPaused event.
         * @param requestId field value
         * @return this model
         */
        public FulfillRequestRequest requestId(Fetch.RequestId requestId) {
            set("requestId", requestId);
            return this;
        }
        /**
         * An HTTP response code.
         * @param responseCode field value
         * @return this model
         */
        public FulfillRequestRequest responseCode(long responseCode) {
            set("responseCode", responseCode);
            return this;
        }
        /**
         * Response headers.
         * @param responseHeaders field value
         * @return this model
         */
        public FulfillRequestRequest responseHeaders(java.util.List<Fetch.HeaderEntry> responseHeaders) {
            set("responseHeaders", responseHeaders);
            return this;
        }
        /**
         * Alternative way of specifying response headers as a \0-separated series of name: value pairs. Prefer the above method unless you need to represent some non-UTF8 values that can&#x27;t be transmitted over the protocol as text. (Encoded as a base64 string when passed over JSON)
         * @param binaryResponseHeaders field value
         * @return this model
         */
        public FulfillRequestRequest binaryResponseHeaders(String binaryResponseHeaders) {
            set("binaryResponseHeaders", binaryResponseHeaders);
            return this;
        }
        /**
         * A response body. If absent, original response body will be used if the request is intercepted at the response stage and empty body will be used if the request is intercepted at the request stage. (Encoded as a base64 string when passed over JSON)
         * @param body field value
         * @return this model
         */
        public FulfillRequestRequest body(String body) {
            set("body", body);
            return this;
        }
        /**
         * A textual representation of responseCode. If absent, a standard phrase matching responseCode is used.
         * @param responsePhrase field value
         * @return this model
         */
        public FulfillRequestRequest responsePhrase(String responsePhrase) {
            set("responsePhrase", responsePhrase);
            return this;
        }
    }
    /**
     * Continues the request, optionally modifying some of its parameters.
     */
    public static final class ContinueRequestRequest extends CdpObject {
        public ContinueRequestRequest() {}
        public static ContinueRequestRequest fromMap(Map<String, Object> values) {
            final ContinueRequestRequest instance_ = new ContinueRequestRequest();
            if (values != null) instance_.values.putAll(values);
            return instance_;
        }
        /**
         * An id the client received in requestPaused event.
         * @return the protocol field value
         */
        public Fetch.RequestId requestId() {
            return require(REQUEST_ID);
        }
        /**
         * If set, the request url will be modified in a way that&#x27;s not observable by page.
         * @return the protocol field value, empty when absent
         */
        public Optional<String> url() {
            return optionalString("url");
        }
        /**
         * If set, the request method is overridden.
         * @return the protocol field value, empty when absent
         */
        public Optional<String> method() {
            return optionalString("method");
        }
        /**
         * If set, overrides the post data in the request. (Encoded as a base64 string when passed over JSON)
         * @return the protocol field value, empty when absent
         */
        public Optional<String> postData() {
            return optionalString("postData");
        }
        /**
         * If set, overrides the request headers. Note that the overrides do not extend to subsequent redirect hops, if a redirect happens. Another override may be applied to a different request produced by a redirect.
         * @return the protocol field value, empty when absent
         */
        public Optional<java.util.List<Fetch.HeaderEntry>> headers() {
            return optional(HEADERS);
        }
        /**
         * If set, overrides response interception behavior for this request.
         * <p><b>Experimental:</b> this part of CDP may change without notice.
         * @return the protocol field value, empty when absent
         */
        public Optional<Boolean> interceptResponse() {
            return optionalBoolean("interceptResponse");
        }
        /**
         * An id the client received in requestPaused event.
         * @param requestId field value
         * @return this model
         */
        public ContinueRequestRequest requestId(Fetch.RequestId requestId) {
            set("requestId", requestId);
            return this;
        }
        /**
         * If set, the request url will be modified in a way that&#x27;s not observable by page.
         * @param url field value
         * @return this model
         */
        public ContinueRequestRequest url(String url) {
            set("url", url);
            return this;
        }
        /**
         * If set, the request method is overridden.
         * @param method field value
         * @return this model
         */
        public ContinueRequestRequest method(String method) {
            set("method", method);
            return this;
        }
        /**
         * If set, overrides the post data in the request. (Encoded as a base64 string when passed over JSON)
         * @param postData field value
         * @return this model
         */
        public ContinueRequestRequest postData(String postData) {
            set("postData", postData);
            return this;
        }
        /**
         * If set, overrides the request headers. Note that the overrides do not extend to subsequent redirect hops, if a redirect happens. Another override may be applied to a different request produced by a redirect.
         * @param headers field value
         * @return this model
         */
        public ContinueRequestRequest headers(java.util.List<Fetch.HeaderEntry> headers) {
            set("headers", headers);
            return this;
        }
        /**
         * If set, overrides response interception behavior for this request.
         * <p><b>Experimental:</b> this part of CDP may change without notice.
         * @param interceptResponse field value
         * @return this model
         */
        public ContinueRequestRequest interceptResponse(boolean interceptResponse) {
            set("interceptResponse", interceptResponse);
            return this;
        }
    }
    /**
     * Continues a request supplying authChallengeResponse following authRequired event.
     */
    public static final class ContinueWithAuthRequest extends CdpObject {
        public ContinueWithAuthRequest() {}
        public static ContinueWithAuthRequest fromMap(Map<String, Object> values) {
            final ContinueWithAuthRequest instance_ = new ContinueWithAuthRequest();
            if (values != null) instance_.values.putAll(values);
            return instance_;
        }
        /**
         * An id the client received in authRequired event.
         * @return the protocol field value
         */
        public Fetch.RequestId requestId() {
            return require(REQUEST_ID);
        }
        /**
         * Response to with an authChallenge.
         * @return the protocol field value
         */
        public Fetch.AuthChallengeResponse authChallengeResponse() {
            return require(AUTH_CHALLENGE_RESPONSE);
        }
        /**
         * An id the client received in authRequired event.
         * @param requestId field value
         * @return this model
         */
        public ContinueWithAuthRequest requestId(Fetch.RequestId requestId) {
            set("requestId", requestId);
            return this;
        }
        /**
         * Response to with an authChallenge.
         * @param authChallengeResponse field value
         * @return this model
         */
        public ContinueWithAuthRequest authChallengeResponse(Fetch.AuthChallengeResponse authChallengeResponse) {
            set("authChallengeResponse", authChallengeResponse);
            return this;
        }
    }
    /**
     * Continues loading of the paused response, optionally modifying the response headers. If either responseCode or headers are modified, all of them must be present.
     * <p><b>Experimental:</b> this part of CDP may change without notice.
     */
    public static final class ContinueResponseRequest extends CdpObject {
        public ContinueResponseRequest() {}
        public static ContinueResponseRequest fromMap(Map<String, Object> values) {
            final ContinueResponseRequest instance_ = new ContinueResponseRequest();
            if (values != null) instance_.values.putAll(values);
            return instance_;
        }
        /**
         * An id the client received in requestPaused event.
         * @return the protocol field value
         */
        public Fetch.RequestId requestId() {
            return require(REQUEST_ID);
        }
        /**
         * An HTTP response code. If absent, original response code will be used.
         * @return the protocol field value, empty when absent
         */
        public OptionalLong responseCode() {
            return optionalLong("responseCode");
        }
        /**
         * A textual representation of responseCode. If absent, a standard phrase matching responseCode is used.
         * @return the protocol field value, empty when absent
         */
        public Optional<String> responsePhrase() {
            return optionalString("responsePhrase");
        }
        /**
         * Response headers. If absent, original response headers will be used.
         * @return the protocol field value, empty when absent
         */
        public Optional<java.util.List<Fetch.HeaderEntry>> responseHeaders() {
            return optional(RESPONSE_HEADERS);
        }
        /**
         * Alternative way of specifying response headers as a \0-separated series of name: value pairs. Prefer the above method unless you need to represent some non-UTF8 values that can&#x27;t be transmitted over the protocol as text. (Encoded as a base64 string when passed over JSON)
         * @return the protocol field value, empty when absent
         */
        public Optional<String> binaryResponseHeaders() {
            return optionalString("binaryResponseHeaders");
        }
        /**
         * An id the client received in requestPaused event.
         * @param requestId field value
         * @return this model
         */
        public ContinueResponseRequest requestId(Fetch.RequestId requestId) {
            set("requestId", requestId);
            return this;
        }
        /**
         * An HTTP response code. If absent, original response code will be used.
         * @param responseCode field value
         * @return this model
         */
        public ContinueResponseRequest responseCode(long responseCode) {
            set("responseCode", responseCode);
            return this;
        }
        /**
         * A textual representation of responseCode. If absent, a standard phrase matching responseCode is used.
         * @param responsePhrase field value
         * @return this model
         */
        public ContinueResponseRequest responsePhrase(String responsePhrase) {
            set("responsePhrase", responsePhrase);
            return this;
        }
        /**
         * Response headers. If absent, original response headers will be used.
         * @param responseHeaders field value
         * @return this model
         */
        public ContinueResponseRequest responseHeaders(java.util.List<Fetch.HeaderEntry> responseHeaders) {
            set("responseHeaders", responseHeaders);
            return this;
        }
        /**
         * Alternative way of specifying response headers as a \0-separated series of name: value pairs. Prefer the above method unless you need to represent some non-UTF8 values that can&#x27;t be transmitted over the protocol as text. (Encoded as a base64 string when passed over JSON)
         * @param binaryResponseHeaders field value
         * @return this model
         */
        public ContinueResponseRequest binaryResponseHeaders(String binaryResponseHeaders) {
            set("binaryResponseHeaders", binaryResponseHeaders);
            return this;
        }
    }
    /**
     * Causes the body of the response to be received from the server and returned as a single string. May only be issued for a request that is paused in the Response stage and is mutually exclusive with takeResponseBodyForInterceptionAsStream. Calling other methods that affect the request or disabling fetch domain before body is received results in an undefined behavior. Note that the response body is not available for redirects. Requests paused in the _redirect received_ state may be differentiated by {@code responseCode} and presence of {@code location} response header, see comments to {@code requestPaused} for details.
     */
    public static final class GetResponseBodyRequest extends CdpObject {
        public GetResponseBodyRequest() {}
        public static GetResponseBodyRequest fromMap(Map<String, Object> values) {
            final GetResponseBodyRequest instance_ = new GetResponseBodyRequest();
            if (values != null) instance_.values.putAll(values);
            return instance_;
        }
        /**
         * Identifier for the intercepted request to get body for.
         * @return the protocol field value
         */
        public Fetch.RequestId requestId() {
            return require(REQUEST_ID);
        }
        /**
         * Identifier for the intercepted request to get body for.
         * @param requestId field value
         * @return this model
         */
        public GetResponseBodyRequest requestId(Fetch.RequestId requestId) {
            set("requestId", requestId);
            return this;
        }
    }
    /**
     * Returns a handle to the stream representing the response body. The request must be paused in the HeadersReceived stage. Note that after this command the request can&#x27;t be continued as is -- client either needs to cancel it or to provide the response body. The stream only supports sequential read, IO.read will fail if the position is specified. This method is mutually exclusive with getResponseBody. Calling other methods that affect the request or disabling fetch domain before body is received results in an undefined behavior.
     */
    public static final class TakeResponseBodyAsStreamRequest extends CdpObject {
        public TakeResponseBodyAsStreamRequest() {}
        public static TakeResponseBodyAsStreamRequest fromMap(Map<String, Object> values) {
            final TakeResponseBodyAsStreamRequest instance_ = new TakeResponseBodyAsStreamRequest();
            if (values != null) instance_.values.putAll(values);
            return instance_;
        }
        /**
         * Returns the requestId field.
         * @return the protocol field value
         */
        public Fetch.RequestId requestId() {
            return require(REQUEST_ID);
        }
        /**
         * Sets the requestId field.
         * @param requestId field value
         * @return this model
         */
        public TakeResponseBodyAsStreamRequest requestId(Fetch.RequestId requestId) {
            set("requestId", requestId);
            return this;
        }
    }
    /**
     * Causes the body of the response to be received from the server and returned as a single string. May only be issued for a request that is paused in the Response stage and is mutually exclusive with takeResponseBodyForInterceptionAsStream. Calling other methods that affect the request or disabling fetch domain before body is received results in an undefined behavior. Note that the response body is not available for redirects. Requests paused in the _redirect received_ state may be differentiated by {@code responseCode} and presence of {@code location} response header, see comments to {@code requestPaused} for details.
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
     * Issued when the domain is enabled and the request URL matches the specified filter. The request is paused until the client responds with one of continueRequest, failRequest or fulfillRequest. The stage of the request can be determined by presence of responseErrorReason and responseStatusCode -- the request is at the response stage if either of these fields is present and in the request stage otherwise. Redirect responses and subsequent requests are reported similarly to regular responses and requests. Redirect responses may be distinguished by the value of {@code responseStatusCode} (which is one of 301, 302, 303, 307, 308) along with presence of the {@code location} header. Requests resulting from a redirect will have {@code redirectedRequestId} field set.
     */
    public static final class RequestPausedEvent extends CdpObject {
        public RequestPausedEvent() {}
        private RequestPausedEvent(Map<String, Object> values) { super(values); }
        public static RequestPausedEvent fromMap(Map<String, Object> values) {
            return new RequestPausedEvent(values);
        }
        /**
         * Each request the page makes will have a unique id.
         * @return the protocol field value
         */
        public Fetch.RequestId requestId() {
            return require(REQUEST_ID);
        }
        /**
         * The details of the request.
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
         * Response error if intercepted at response stage.
         * @return the protocol field value, empty when absent
         */
        public Optional<Network.ErrorReason> responseErrorReason() {
            return optional(RESPONSE_ERROR_REASON);
        }
        /**
         * Response code if intercepted at response stage.
         * @return the protocol field value, empty when absent
         */
        public OptionalLong responseStatusCode() {
            return optionalLong("responseStatusCode");
        }
        /**
         * Response status text if intercepted at response stage.
         * @return the protocol field value, empty when absent
         */
        public Optional<String> responseStatusText() {
            return optionalString("responseStatusText");
        }
        /**
         * Response headers if intercepted at the response stage.
         * @return the protocol field value, empty when absent
         */
        public Optional<java.util.List<Fetch.HeaderEntry>> responseHeaders() {
            return optional(RESPONSE_HEADERS);
        }
        /**
         * If the intercepted request had a corresponding Network.requestWillBeSent event fired for it, then this networkId will be the same as the requestId present in the requestWillBeSent event.
         * @return the protocol field value, empty when absent
         */
        public Optional<Network.RequestId> networkId() {
            return optional(NETWORK_ID);
        }
        /**
         * If the request is due to a redirect response from the server, the id of the request that has caused the redirect.
         * <p><b>Experimental:</b> this part of CDP may change without notice.
         * @return the protocol field value, empty when absent
         */
        public Optional<Fetch.RequestId> redirectedRequestId() {
            return optional(REDIRECTED_REQUEST_ID);
        }
        /**
         * Each request the page makes will have a unique id.
         * @param requestId field value
         * @return this model
         */
        public RequestPausedEvent requestId(Fetch.RequestId requestId) {
            set("requestId", requestId);
            return this;
        }
        /**
         * The details of the request.
         * @param request field value
         * @return this model
         */
        public RequestPausedEvent request(Network.Request request) {
            set("request", request);
            return this;
        }
        /**
         * The id of the frame that initiated the request.
         * @param frameId field value
         * @return this model
         */
        public RequestPausedEvent frameId(Page.FrameId frameId) {
            set("frameId", frameId);
            return this;
        }
        /**
         * How the requested resource will be used.
         * @param resourceType field value
         * @return this model
         */
        public RequestPausedEvent resourceType(Network.ResourceType resourceType) {
            set("resourceType", resourceType);
            return this;
        }
        /**
         * Response error if intercepted at response stage.
         * @param responseErrorReason field value
         * @return this model
         */
        public RequestPausedEvent responseErrorReason(Network.ErrorReason responseErrorReason) {
            set("responseErrorReason", responseErrorReason);
            return this;
        }
        /**
         * Response code if intercepted at response stage.
         * @param responseStatusCode field value
         * @return this model
         */
        public RequestPausedEvent responseStatusCode(long responseStatusCode) {
            set("responseStatusCode", responseStatusCode);
            return this;
        }
        /**
         * Response status text if intercepted at response stage.
         * @param responseStatusText field value
         * @return this model
         */
        public RequestPausedEvent responseStatusText(String responseStatusText) {
            set("responseStatusText", responseStatusText);
            return this;
        }
        /**
         * Response headers if intercepted at the response stage.
         * @param responseHeaders field value
         * @return this model
         */
        public RequestPausedEvent responseHeaders(java.util.List<Fetch.HeaderEntry> responseHeaders) {
            set("responseHeaders", responseHeaders);
            return this;
        }
        /**
         * If the intercepted request had a corresponding Network.requestWillBeSent event fired for it, then this networkId will be the same as the requestId present in the requestWillBeSent event.
         * @param networkId field value
         * @return this model
         */
        public RequestPausedEvent networkId(Network.RequestId networkId) {
            set("networkId", networkId);
            return this;
        }
        /**
         * If the request is due to a redirect response from the server, the id of the request that has caused the redirect.
         * <p><b>Experimental:</b> this part of CDP may change without notice.
         * @param redirectedRequestId field value
         * @return this model
         */
        public RequestPausedEvent redirectedRequestId(Fetch.RequestId redirectedRequestId) {
            set("redirectedRequestId", redirectedRequestId);
            return this;
        }
    }
    /**
     * Issued when the domain is enabled with handleAuthRequests set to true. The request is paused until client responds with continueWithAuth.
     */
    public static final class AuthRequiredEvent extends CdpObject {
        public AuthRequiredEvent() {}
        private AuthRequiredEvent(Map<String, Object> values) { super(values); }
        public static AuthRequiredEvent fromMap(Map<String, Object> values) {
            return new AuthRequiredEvent(values);
        }
        /**
         * Each request the page makes will have a unique id.
         * @return the protocol field value
         */
        public Fetch.RequestId requestId() {
            return require(REQUEST_ID);
        }
        /**
         * The details of the request.
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
         * Details of the Authorization Challenge encountered. If this is set, client should respond with continueRequest that contains AuthChallengeResponse.
         * @return the protocol field value
         */
        public Fetch.AuthChallenge authChallenge() {
            return require(AUTH_CHALLENGE);
        }
        /**
         * Each request the page makes will have a unique id.
         * @param requestId field value
         * @return this model
         */
        public AuthRequiredEvent requestId(Fetch.RequestId requestId) {
            set("requestId", requestId);
            return this;
        }
        /**
         * The details of the request.
         * @param request field value
         * @return this model
         */
        public AuthRequiredEvent request(Network.Request request) {
            set("request", request);
            return this;
        }
        /**
         * The id of the frame that initiated the request.
         * @param frameId field value
         * @return this model
         */
        public AuthRequiredEvent frameId(Page.FrameId frameId) {
            set("frameId", frameId);
            return this;
        }
        /**
         * How the requested resource will be used.
         * @param resourceType field value
         * @return this model
         */
        public AuthRequiredEvent resourceType(Network.ResourceType resourceType) {
            set("resourceType", resourceType);
            return this;
        }
        /**
         * Details of the Authorization Challenge encountered. If this is set, client should respond with continueRequest that contains AuthChallengeResponse.
         * @param authChallenge field value
         * @return this model
         */
        public AuthRequiredEvent authChallenge(Fetch.AuthChallenge authChallenge) {
            set("authChallenge", authChallenge);
            return this;
        }
    }
    public static final class Client extends CdpDomainClient {
        public Client(CdpClient client) { super(client); }
        /**
         * Disables the fetch domain.
         * @return a stage completing when the command completes
         */
        public CompletionStage<Void> disable() {
            return call("Fetch.disable");
        }
        /**
         * Enables issuing of requestPaused events. A request will be paused until client calls one of failRequest, fulfillRequest or continueRequest/continueWithAuth.
         * @param patterns protocol value
         * @param handleAuthRequests protocol value
         * @return a stage completing when the command completes
         */
        public CompletionStage<Void> enable(java.util.@Nullable List<Fetch.RequestPattern> patterns, @Nullable Boolean handleAuthRequests) {
            return call("Fetch.enable", params("patterns", patterns, "handleAuthRequests", handleAuthRequests));
        }
        /**
         * Enables issuing of requestPaused events. A request will be paused until client calls one of failRequest, fulfillRequest or continueRequest/continueWithAuth.
         * @return a stage completing when the command completes
         */
        public CompletionStage<Void> enable() {
            return call("Fetch.enable");
        }
        /**
         * Enables issuing of requestPaused events. A request will be paused until client calls one of failRequest, fulfillRequest or continueRequest/continueWithAuth.
         * @param request request parameters
         * @return a stage completing when the command completes
         */
        public CompletionStage<Void> enable(EnableRequest request) {
            return call("Fetch.enable", request);
        }
        /**
         * Causes the request to fail with specified reason.
         * @param requestId protocol value
         * @param errorReason protocol value
         * @return a stage completing when the command completes
         */
        public CompletionStage<Void> failRequest(Fetch.RequestId requestId, Network.ErrorReason errorReason) {
            return call("Fetch.failRequest", params("requestId", requestId, "errorReason", errorReason));
        }
        /**
         * Causes the request to fail with specified reason.
         * @param request request parameters
         * @return a stage completing when the command completes
         */
        public CompletionStage<Void> failRequest(FailRequestRequest request) {
            return call("Fetch.failRequest", request);
        }
        /**
         * Provides response to the request.
         * @param requestId protocol value
         * @param responseCode protocol value
         * @param responseHeaders protocol value
         * @param binaryResponseHeaders protocol value
         * @param body protocol value
         * @param responsePhrase protocol value
         * @return a stage completing when the command completes
         */
        public CompletionStage<Void> fulfillRequest(Fetch.RequestId requestId, long responseCode, java.util.@Nullable List<Fetch.HeaderEntry> responseHeaders, @Nullable String binaryResponseHeaders, @Nullable String body, @Nullable String responsePhrase) {
            return call("Fetch.fulfillRequest", params("requestId", requestId, "responseCode", responseCode, "responseHeaders", responseHeaders, "binaryResponseHeaders", binaryResponseHeaders, "body", body, "responsePhrase", responsePhrase));
        }
        /**
         * Provides response to the request.
         * @param requestId protocol value
         * @param responseCode protocol value
         * @return a stage completing when the command completes
         */
        public CompletionStage<Void> fulfillRequest(Fetch.RequestId requestId, long responseCode) {
            return call("Fetch.fulfillRequest", params("requestId", requestId, "responseCode", responseCode));
        }
        /**
         * Provides response to the request.
         * @param request request parameters
         * @return a stage completing when the command completes
         */
        public CompletionStage<Void> fulfillRequest(FulfillRequestRequest request) {
            return call("Fetch.fulfillRequest", request);
        }
        /**
         * Continues the request, optionally modifying some of its parameters.
         * @param requestId protocol value
         * @param url protocol value
         * @param method protocol value
         * @param postData protocol value
         * @param headers protocol value
         * @param interceptResponse protocol value
         * @return a stage completing when the command completes
         */
        public CompletionStage<Void> continueRequest(Fetch.RequestId requestId, @Nullable String url, @Nullable String method, @Nullable String postData, java.util.@Nullable List<Fetch.HeaderEntry> headers, @Nullable Boolean interceptResponse) {
            return call("Fetch.continueRequest", params("requestId", requestId, "url", url, "method", method, "postData", postData, "headers", headers, "interceptResponse", interceptResponse));
        }
        /**
         * Continues the request, optionally modifying some of its parameters.
         * @param requestId protocol value
         * @return a stage completing when the command completes
         */
        public CompletionStage<Void> continueRequest(Fetch.RequestId requestId) {
            return call("Fetch.continueRequest", params("requestId", requestId));
        }
        /**
         * Continues the request, optionally modifying some of its parameters.
         * @param request request parameters
         * @return a stage completing when the command completes
         */
        public CompletionStage<Void> continueRequest(ContinueRequestRequest request) {
            return call("Fetch.continueRequest", request);
        }
        /**
         * Continues a request supplying authChallengeResponse following authRequired event.
         * @param requestId protocol value
         * @param authChallengeResponse protocol value
         * @return a stage completing when the command completes
         */
        public CompletionStage<Void> continueWithAuth(Fetch.RequestId requestId, Fetch.AuthChallengeResponse authChallengeResponse) {
            return call("Fetch.continueWithAuth", params("requestId", requestId, "authChallengeResponse", authChallengeResponse));
        }
        /**
         * Continues a request supplying authChallengeResponse following authRequired event.
         * @param request request parameters
         * @return a stage completing when the command completes
         */
        public CompletionStage<Void> continueWithAuth(ContinueWithAuthRequest request) {
            return call("Fetch.continueWithAuth", request);
        }
        /**
         * Continues loading of the paused response, optionally modifying the response headers. If either responseCode or headers are modified, all of them must be present.
         * <p><b>Experimental:</b> this part of CDP may change without notice.
         * @param requestId protocol value
         * @param responseCode protocol value
         * @param responsePhrase protocol value
         * @param responseHeaders protocol value
         * @param binaryResponseHeaders protocol value
         * @return a stage completing when the command completes
         */
        public CompletionStage<Void> continueResponse(Fetch.RequestId requestId, @Nullable Long responseCode, @Nullable String responsePhrase, java.util.@Nullable List<Fetch.HeaderEntry> responseHeaders, @Nullable String binaryResponseHeaders) {
            return call("Fetch.continueResponse", params("requestId", requestId, "responseCode", responseCode, "responsePhrase", responsePhrase, "responseHeaders", responseHeaders, "binaryResponseHeaders", binaryResponseHeaders));
        }
        /**
         * Continues loading of the paused response, optionally modifying the response headers. If either responseCode or headers are modified, all of them must be present.
         * <p><b>Experimental:</b> this part of CDP may change without notice.
         * @param requestId protocol value
         * @return a stage completing when the command completes
         */
        public CompletionStage<Void> continueResponse(Fetch.RequestId requestId) {
            return call("Fetch.continueResponse", params("requestId", requestId));
        }
        /**
         * Continues loading of the paused response, optionally modifying the response headers. If either responseCode or headers are modified, all of them must be present.
         * <p><b>Experimental:</b> this part of CDP may change without notice.
         * @param request request parameters
         * @return a stage completing when the command completes
         */
        public CompletionStage<Void> continueResponse(ContinueResponseRequest request) {
            return call("Fetch.continueResponse", request);
        }
        /**
         * Causes the body of the response to be received from the server and returned as a single string. May only be issued for a request that is paused in the Response stage and is mutually exclusive with takeResponseBodyForInterceptionAsStream. Calling other methods that affect the request or disabling fetch domain before body is received results in an undefined behavior. Note that the response body is not available for redirects. Requests paused in the _redirect received_ state may be differentiated by {@code responseCode} and presence of {@code location} response header, see comments to {@code requestPaused} for details.
         * @param requestId protocol value
         * @return a stage completing with the command result
         */
        public CompletionStage<GetResponseBodyResult> getResponseBody(Fetch.RequestId requestId) {
            return call("Fetch.getResponseBody", params("requestId", requestId), GetResponseBodyResult::fromMap);
        }
        /**
         * Causes the body of the response to be received from the server and returned as a single string. May only be issued for a request that is paused in the Response stage and is mutually exclusive with takeResponseBodyForInterceptionAsStream. Calling other methods that affect the request or disabling fetch domain before body is received results in an undefined behavior. Note that the response body is not available for redirects. Requests paused in the _redirect received_ state may be differentiated by {@code responseCode} and presence of {@code location} response header, see comments to {@code requestPaused} for details.
         * @param request request parameters
         * @return a stage completing with the command result
         */
        public CompletionStage<GetResponseBodyResult> getResponseBody(GetResponseBodyRequest request) {
            return call("Fetch.getResponseBody", request, GetResponseBodyResult::fromMap);
        }
        /**
         * Returns a handle to the stream representing the response body. The request must be paused in the HeadersReceived stage. Note that after this command the request can&#x27;t be continued as is -- client either needs to cancel it or to provide the response body. The stream only supports sequential read, IO.read will fail if the position is specified. This method is mutually exclusive with getResponseBody. Calling other methods that affect the request or disabling fetch domain before body is received results in an undefined behavior.
         * @param requestId protocol value
         * @return a stage completing with the command result
         */
        public CompletionStage<IO.StreamHandle> takeResponseBodyAsStream(Fetch.RequestId requestId) {
            return call("Fetch.takeResponseBodyAsStream", params("requestId", requestId), STREAM::require);
        }
        /**
         * Returns a handle to the stream representing the response body. The request must be paused in the HeadersReceived stage. Note that after this command the request can&#x27;t be continued as is -- client either needs to cancel it or to provide the response body. The stream only supports sequential read, IO.read will fail if the position is specified. This method is mutually exclusive with getResponseBody. Calling other methods that affect the request or disabling fetch domain before body is received results in an undefined behavior.
         * @param request request parameters
         * @return a stage completing with the command result
         */
        public CompletionStage<IO.StreamHandle> takeResponseBodyAsStream(TakeResponseBodyAsStreamRequest request) {
            return call("Fetch.takeResponseBodyAsStream", request, STREAM::require);
        }
        /**
         * Issued when the domain is enabled and the request URL matches the specified filter. The request is paused until the client responds with one of continueRequest, failRequest or fulfillRequest. The stage of the request can be determined by presence of responseErrorReason and responseStatusCode -- the request is at the response stage if either of these fields is present and in the request stage otherwise. Redirect responses and subsequent requests are reported similarly to regular responses and requests. Redirect responses may be distinguished by the value of {@code responseStatusCode} (which is one of 301, 302, 303, 307, 308) along with presence of the {@code location} header. Requests resulting from a redirect will have {@code redirectedRequestId} field set.
         * @param handler event callback
         * @return a removable subscription
         */
        public CdpSubscription onRequestPaused(Consumer<RequestPausedEvent> handler) {
            return on("Fetch.requestPaused", RequestPausedEvent::fromMap, handler);
        }
        /**
         * Issued when the domain is enabled with handleAuthRequests set to true. The request is paused until client responds with continueWithAuth.
         * @param handler event callback
         * @return a removable subscription
         */
        public CdpSubscription onAuthRequired(Consumer<AuthRequiredEvent> handler) {
            return on("Fetch.authRequired", AuthRequiredEvent::fromMap, handler);
        }
    }
}
