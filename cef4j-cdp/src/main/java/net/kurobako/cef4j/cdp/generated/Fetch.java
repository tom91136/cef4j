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
 * A domain for letting clients substitute browser&#x27;s network layer with client code.
 * @see <a href="https://chromium.googlesource.com/chromium/src/+/refs/tags/150.0.7871.213/third_party/blink/public/devtools_protocol/domains/Fetch.pdl">Pinned protocol source</a>
 */
@Generated("mvn generate-sources -pl cef4j-platform -Dcef.version=150.0.18+gdb11278+chromium-150.0.7871.213")
@SuppressWarnings({"EscapedEntity", "InvalidParam", "JavaLangClash", "MissingSummary", "Unchecked", "UnusedMethod"})
public final class Fetch {
    private Fetch() {}
    /**
     * Unique request identifier. Note that this does not identify individual HTTP requests that are part of a network request.
     */
    public static final class RequestId implements CdpValue<String> {
        public final String value;
        public RequestId(@Nonnull String value) { this.value = java.util.Objects.requireNonNull(value); }
        @Nonnull public String value() { return value; }
        @Override public boolean equals(Object other) {
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
        @Nonnull public String value() { return value; }
        public static RequestStage of(@Nonnull String value) {
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
            return Optional.ofNullable((String) raw("urlPattern"));
        }
        /**
         * If set, only requests for matching resource types will be intercepted.
         * @return the protocol field value, empty when absent
         */
        public Optional<Network.ResourceType> resourceType() {
            return Optional.ofNullable(raw("resourceType") == null ? null : Network.ResourceType.of((String) raw("resourceType")));
        }
        /**
         * Stage at which to begin intercepting requests. Default is Request.
         * @return the protocol field value, empty when absent
         */
        public Optional<Fetch.RequestStage> requestStage() {
            return Optional.ofNullable(raw("requestStage") == null ? null : Fetch.RequestStage.of((String) raw("requestStage")));
        }
        /**
         * Wildcards ({@code &#x27;*&#x27;} -&gt; zero or more, {@code &#x27;?&#x27;} -&gt; exactly one) are allowed. Escape character is backslash. Omitting is equivalent to {@code &quot;*&quot;}.
         * @param urlPattern field value; empty omits the value
         * @return this model
         */
        public RequestPattern urlPattern(Optional<String> urlPattern) {
            set("urlPattern", urlPattern.orElse(null));
            return this;
        }
        /**
         * Wildcards ({@code &#x27;*&#x27;} -&gt; zero or more, {@code &#x27;?&#x27;} -&gt; exactly one) are allowed. Escape character is backslash. Omitting is equivalent to {@code &quot;*&quot;}.
         * @param urlPattern field value; null removes the value
         * @return this model
         */
        public RequestPattern urlPattern(String urlPattern) {
            set("urlPattern", urlPattern);
            return this;
        }
        /**
         * If set, only requests for matching resource types will be intercepted.
         * @param resourceType field value; empty omits the value
         * @return this model
         */
        public RequestPattern resourceType(Optional<Network.ResourceType> resourceType) {
            set("resourceType", resourceType.orElse(null));
            return this;
        }
        /**
         * If set, only requests for matching resource types will be intercepted.
         * @param resourceType field value; null removes the value
         * @return this model
         */
        public RequestPattern resourceType(Network.ResourceType resourceType) {
            set("resourceType", resourceType);
            return this;
        }
        /**
         * Stage at which to begin intercepting requests. Default is Request.
         * @param requestStage field value; empty omits the value
         * @return this model
         */
        public RequestPattern requestStage(Optional<Fetch.RequestStage> requestStage) {
            set("requestStage", requestStage.orElse(null));
            return this;
        }
        /**
         * Stage at which to begin intercepting requests. Default is Request.
         * @param requestStage field value; null removes the value
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
            return (String) require("name");
        }
        /**
         * Returns the value field.
         * @return the protocol field value
         */
        public String value() {
            return (String) require("value");
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
            @Nonnull public String value() { return value; }
            public static SourceValues of(@Nonnull String value) {
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
            return Optional.ofNullable(raw("source") == null ? null : AuthChallenge.SourceValues.of((String) raw("source")));
        }
        /**
         * Origin of the challenger.
         * @return the protocol field value
         */
        public String origin() {
            return (String) require("origin");
        }
        /**
         * The authentication scheme used, such as basic or digest
         * @return the protocol field value
         */
        public String scheme() {
            return (String) require("scheme");
        }
        /**
         * The realm of the challenge. May be empty.
         * @return the protocol field value
         */
        public String realm() {
            return (String) require("realm");
        }
        /**
         * Source of the authentication challenge.
         * @param source field value; empty omits the value
         * @return this model
         */
        public AuthChallenge source(Optional<AuthChallenge.SourceValues> source) {
            set("source", source.orElse(null));
            return this;
        }
        /**
         * Source of the authentication challenge.
         * @param source field value; null removes the value
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
            @Nonnull public String value() { return value; }
            public static ResponseValues of(@Nonnull String value) {
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
            return AuthChallengeResponse.ResponseValues.of((String) require("response"));
        }
        /**
         * The username to provide, possibly empty. Should only be set if response is ProvideCredentials.
         * @return the protocol field value, empty when absent
         */
        public Optional<String> username() {
            return Optional.ofNullable((String) raw("username"));
        }
        /**
         * The password to provide, possibly empty. Should only be set if response is ProvideCredentials.
         * @return the protocol field value, empty when absent
         */
        public Optional<String> password() {
            return Optional.ofNullable((String) raw("password"));
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
         * @param username field value; empty omits the value
         * @return this model
         */
        public AuthChallengeResponse username(Optional<String> username) {
            set("username", username.orElse(null));
            return this;
        }
        /**
         * The username to provide, possibly empty. Should only be set if response is ProvideCredentials.
         * @param username field value; null removes the value
         * @return this model
         */
        public AuthChallengeResponse username(String username) {
            set("username", username);
            return this;
        }
        /**
         * The password to provide, possibly empty. Should only be set if response is ProvideCredentials.
         * @param password field value; empty omits the value
         * @return this model
         */
        public AuthChallengeResponse password(Optional<String> password) {
            set("password", password.orElse(null));
            return this;
        }
        /**
         * The password to provide, possibly empty. Should only be set if response is ProvideCredentials.
         * @param password field value; null removes the value
         * @return this model
         */
        public AuthChallengeResponse password(String password) {
            set("password", password);
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
            return (String) require("body");
        }
        /**
         * True, if content was sent as base64.
         * @return the protocol field value
         */
        public boolean base64Encoded() {
            return (Boolean) require("base64Encoded");
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
            return new Fetch.RequestId((String) require("requestId"));
        }
        /**
         * The details of the request.
         * @return the protocol field value
         */
        public Network.Request request() {
            return java.util.Objects.requireNonNull(Network.Request.fromMap(java.util.Objects.requireNonNull(CdpObject.objectMap(require("request")))));
        }
        /**
         * The id of the frame that initiated the request.
         * @return the protocol field value
         */
        public Page.FrameId frameId() {
            return new Page.FrameId((String) require("frameId"));
        }
        /**
         * How the requested resource will be used.
         * @return the protocol field value
         */
        public Network.ResourceType resourceType() {
            return Network.ResourceType.of((String) require("resourceType"));
        }
        /**
         * Response error if intercepted at response stage.
         * @return the protocol field value, empty when absent
         */
        public Optional<Network.ErrorReason> responseErrorReason() {
            return Optional.ofNullable(raw("responseErrorReason") == null ? null : Network.ErrorReason.of((String) raw("responseErrorReason")));
        }
        /**
         * Response code if intercepted at response stage.
         * @return the protocol field value, empty when absent
         */
        public OptionalLong responseStatusCode() {
            Long value = CdpObject.numberAsLong(raw("responseStatusCode"));
            return value == null ? OptionalLong.empty() : OptionalLong.of(value);
        }
        /**
         * Response status text if intercepted at response stage.
         * @return the protocol field value, empty when absent
         */
        public Optional<String> responseStatusText() {
            return Optional.ofNullable((String) raw("responseStatusText"));
        }
        /**
         * Response headers if intercepted at the response stage.
         * @return the protocol field value, empty when absent
         */
        public Optional<java.util.List<Fetch.HeaderEntry>> responseHeaders() {
            return Optional.ofNullable(list(raw("responseHeaders"), element0 -> java.util.Objects.requireNonNull(Fetch.HeaderEntry.fromMap(java.util.Objects.requireNonNull(CdpObject.objectMap(element0))))));
        }
        /**
         * If the intercepted request had a corresponding Network.requestWillBeSent event fired for it, then this networkId will be the same as the requestId present in the requestWillBeSent event.
         * @return the protocol field value, empty when absent
         */
        public Optional<Network.RequestId> networkId() {
            return Optional.ofNullable(raw("networkId") == null ? null : new Network.RequestId((String) raw("networkId")));
        }
        /**
         * If the request is due to a redirect response from the server, the id of the request that has caused the redirect.
         * <p><b>Experimental:</b> this part of CDP may change without notice.
         * @return the protocol field value, empty when absent
         */
        public Optional<Fetch.RequestId> redirectedRequestId() {
            return Optional.ofNullable(raw("redirectedRequestId") == null ? null : new Fetch.RequestId((String) raw("redirectedRequestId")));
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
         * @param responseErrorReason field value; empty omits the value
         * @return this model
         */
        public RequestPausedEvent responseErrorReason(Optional<Network.ErrorReason> responseErrorReason) {
            set("responseErrorReason", responseErrorReason.orElse(null));
            return this;
        }
        /**
         * Response error if intercepted at response stage.
         * @param responseErrorReason field value; null removes the value
         * @return this model
         */
        public RequestPausedEvent responseErrorReason(Network.ErrorReason responseErrorReason) {
            set("responseErrorReason", responseErrorReason);
            return this;
        }
        /**
         * Response code if intercepted at response stage.
         * @param responseStatusCode field value; empty omits the value
         * @return this model
         */
        public RequestPausedEvent responseStatusCode(OptionalLong responseStatusCode) {
            set("responseStatusCode", responseStatusCode.isPresent() ? responseStatusCode.getAsLong() : null);
            return this;
        }
        /**
         * Response code if intercepted at response stage.
         * @param responseStatusCode field value; null removes the value
         * @return this model
         */
        public RequestPausedEvent responseStatusCode(Long responseStatusCode) {
            set("responseStatusCode", responseStatusCode);
            return this;
        }
        /**
         * Response status text if intercepted at response stage.
         * @param responseStatusText field value; empty omits the value
         * @return this model
         */
        public RequestPausedEvent responseStatusText(Optional<String> responseStatusText) {
            set("responseStatusText", responseStatusText.orElse(null));
            return this;
        }
        /**
         * Response status text if intercepted at response stage.
         * @param responseStatusText field value; null removes the value
         * @return this model
         */
        public RequestPausedEvent responseStatusText(String responseStatusText) {
            set("responseStatusText", responseStatusText);
            return this;
        }
        /**
         * Response headers if intercepted at the response stage.
         * @param responseHeaders field value; empty omits the value
         * @return this model
         */
        public RequestPausedEvent responseHeaders(Optional<java.util.List<Fetch.HeaderEntry>> responseHeaders) {
            set("responseHeaders", responseHeaders.orElse(null));
            return this;
        }
        /**
         * Response headers if intercepted at the response stage.
         * @param responseHeaders field value; null removes the value
         * @return this model
         */
        public RequestPausedEvent responseHeaders(java.util.List<Fetch.HeaderEntry> responseHeaders) {
            set("responseHeaders", responseHeaders);
            return this;
        }
        /**
         * If the intercepted request had a corresponding Network.requestWillBeSent event fired for it, then this networkId will be the same as the requestId present in the requestWillBeSent event.
         * @param networkId field value; empty omits the value
         * @return this model
         */
        public RequestPausedEvent networkId(Optional<Network.RequestId> networkId) {
            set("networkId", networkId.orElse(null));
            return this;
        }
        /**
         * If the intercepted request had a corresponding Network.requestWillBeSent event fired for it, then this networkId will be the same as the requestId present in the requestWillBeSent event.
         * @param networkId field value; null removes the value
         * @return this model
         */
        public RequestPausedEvent networkId(Network.RequestId networkId) {
            set("networkId", networkId);
            return this;
        }
        /**
         * If the request is due to a redirect response from the server, the id of the request that has caused the redirect.
         * <p><b>Experimental:</b> this part of CDP may change without notice.
         * @param redirectedRequestId field value; empty omits the value
         * @return this model
         */
        public RequestPausedEvent redirectedRequestId(Optional<Fetch.RequestId> redirectedRequestId) {
            set("redirectedRequestId", redirectedRequestId.orElse(null));
            return this;
        }
        /**
         * If the request is due to a redirect response from the server, the id of the request that has caused the redirect.
         * <p><b>Experimental:</b> this part of CDP may change without notice.
         * @param redirectedRequestId field value; null removes the value
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
            return new Fetch.RequestId((String) require("requestId"));
        }
        /**
         * The details of the request.
         * @return the protocol field value
         */
        public Network.Request request() {
            return java.util.Objects.requireNonNull(Network.Request.fromMap(java.util.Objects.requireNonNull(CdpObject.objectMap(require("request")))));
        }
        /**
         * The id of the frame that initiated the request.
         * @return the protocol field value
         */
        public Page.FrameId frameId() {
            return new Page.FrameId((String) require("frameId"));
        }
        /**
         * How the requested resource will be used.
         * @return the protocol field value
         */
        public Network.ResourceType resourceType() {
            return Network.ResourceType.of((String) require("resourceType"));
        }
        /**
         * Details of the Authorization Challenge encountered. If this is set, client should respond with continueRequest that contains AuthChallengeResponse.
         * @return the protocol field value
         */
        public Fetch.AuthChallenge authChallenge() {
            return java.util.Objects.requireNonNull(Fetch.AuthChallenge.fromMap(java.util.Objects.requireNonNull(CdpObject.objectMap(require("authChallenge")))));
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
    public static final class Client {
        private final CdpClient client;
        public Client(CdpClient client) { this.client = client; }
        /**
         * Disables the fetch domain.
         * @return a stage completing when the command completes
         */
        public CompletionStage<Void> disable() {
            return client.call("Fetch.disable", null, result_ -> null);
        }
        /**
         * Enables issuing of requestPaused events. A request will be paused until client calls one of failRequest, fulfillRequest or continueRequest/continueWithAuth.
         * @param patterns protocol value
         * @param handleAuthRequests protocol value
         * @return a stage completing when the command completes
         */
        public CompletionStage<Void> enable(Optional<java.util.List<Fetch.RequestPattern>> patterns, Optional<Boolean> handleAuthRequests) {
            Map<String, Object> params = new LinkedHashMap<>();
            patterns.ifPresent(value_ -> params.put("patterns", CdpObject.json(value_)));
            handleAuthRequests.ifPresent(value_ -> params.put("handleAuthRequests", value_));
            return client.call("Fetch.enable", params, result_ -> null);
        }
        /**
         * Enables issuing of requestPaused events. A request will be paused until client calls one of failRequest, fulfillRequest or continueRequest/continueWithAuth.
         * @return a stage completing when the command completes
         */
        public CompletionStage<Void> enable() {
            return enable(Optional.empty(), Optional.empty());
        }
        /**
         * Causes the request to fail with specified reason.
         * @param requestId protocol value
         * @param errorReason protocol value
         * @return a stage completing when the command completes
         */
        public CompletionStage<Void> failRequest(Fetch.RequestId requestId, Network.ErrorReason errorReason) {
            Map<String, Object> params = new LinkedHashMap<>();
            params.put("requestId", CdpObject.json(requestId));
            params.put("errorReason", CdpObject.json(errorReason));
            return client.call("Fetch.failRequest", params, result_ -> null);
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
        public CompletionStage<Void> fulfillRequest(Fetch.RequestId requestId, long responseCode, Optional<java.util.List<Fetch.HeaderEntry>> responseHeaders, Optional<String> binaryResponseHeaders, Optional<String> body, Optional<String> responsePhrase) {
            Map<String, Object> params = new LinkedHashMap<>();
            params.put("requestId", CdpObject.json(requestId));
            params.put("responseCode", CdpObject.json(responseCode));
            responseHeaders.ifPresent(value_ -> params.put("responseHeaders", CdpObject.json(value_)));
            binaryResponseHeaders.ifPresent(value_ -> params.put("binaryResponseHeaders", CdpObject.json(value_)));
            body.ifPresent(value_ -> params.put("body", CdpObject.json(value_)));
            responsePhrase.ifPresent(value_ -> params.put("responsePhrase", CdpObject.json(value_)));
            return client.call("Fetch.fulfillRequest", params, result_ -> null);
        }
        /**
         * Provides response to the request.
         * @param requestId protocol value
         * @param responseCode protocol value
         * @return a stage completing when the command completes
         */
        public CompletionStage<Void> fulfillRequest(Fetch.RequestId requestId, long responseCode) {
            return fulfillRequest(requestId, responseCode, Optional.empty(), Optional.empty(), Optional.empty(), Optional.empty());
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
        public CompletionStage<Void> continueRequest(Fetch.RequestId requestId, Optional<String> url, Optional<String> method, Optional<String> postData, Optional<java.util.List<Fetch.HeaderEntry>> headers, Optional<Boolean> interceptResponse) {
            Map<String, Object> params = new LinkedHashMap<>();
            params.put("requestId", CdpObject.json(requestId));
            url.ifPresent(value_ -> params.put("url", CdpObject.json(value_)));
            method.ifPresent(value_ -> params.put("method", CdpObject.json(value_)));
            postData.ifPresent(value_ -> params.put("postData", CdpObject.json(value_)));
            headers.ifPresent(value_ -> params.put("headers", CdpObject.json(value_)));
            interceptResponse.ifPresent(value_ -> params.put("interceptResponse", value_));
            return client.call("Fetch.continueRequest", params, result_ -> null);
        }
        /**
         * Continues the request, optionally modifying some of its parameters.
         * @param requestId protocol value
         * @return a stage completing when the command completes
         */
        public CompletionStage<Void> continueRequest(Fetch.RequestId requestId) {
            return continueRequest(requestId, Optional.empty(), Optional.empty(), Optional.empty(), Optional.empty(), Optional.empty());
        }
        /**
         * Continues a request supplying authChallengeResponse following authRequired event.
         * @param requestId protocol value
         * @param authChallengeResponse protocol value
         * @return a stage completing when the command completes
         */
        public CompletionStage<Void> continueWithAuth(Fetch.RequestId requestId, Fetch.AuthChallengeResponse authChallengeResponse) {
            Map<String, Object> params = new LinkedHashMap<>();
            params.put("requestId", CdpObject.json(requestId));
            params.put("authChallengeResponse", CdpObject.json(authChallengeResponse));
            return client.call("Fetch.continueWithAuth", params, result_ -> null);
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
        public CompletionStage<Void> continueResponse(Fetch.RequestId requestId, OptionalLong responseCode, Optional<String> responsePhrase, Optional<java.util.List<Fetch.HeaderEntry>> responseHeaders, Optional<String> binaryResponseHeaders) {
            Map<String, Object> params = new LinkedHashMap<>();
            params.put("requestId", CdpObject.json(requestId));
            responseCode.ifPresent(value_ -> params.put("responseCode", value_));
            responsePhrase.ifPresent(value_ -> params.put("responsePhrase", CdpObject.json(value_)));
            responseHeaders.ifPresent(value_ -> params.put("responseHeaders", CdpObject.json(value_)));
            binaryResponseHeaders.ifPresent(value_ -> params.put("binaryResponseHeaders", CdpObject.json(value_)));
            return client.call("Fetch.continueResponse", params, result_ -> null);
        }
        /**
         * Continues loading of the paused response, optionally modifying the response headers. If either responseCode or headers are modified, all of them must be present.
         * <p><b>Experimental:</b> this part of CDP may change without notice.
         * @param requestId protocol value
         * @return a stage completing when the command completes
         */
        public CompletionStage<Void> continueResponse(Fetch.RequestId requestId) {
            return continueResponse(requestId, OptionalLong.empty(), Optional.empty(), Optional.empty(), Optional.empty());
        }
        /**
         * Causes the body of the response to be received from the server and returned as a single string. May only be issued for a request that is paused in the Response stage and is mutually exclusive with takeResponseBodyForInterceptionAsStream. Calling other methods that affect the request or disabling fetch domain before body is received results in an undefined behavior. Note that the response body is not available for redirects. Requests paused in the _redirect received_ state may be differentiated by {@code responseCode} and presence of {@code location} response header, see comments to {@code requestPaused} for details.
         * @param requestId protocol value
         * @return a stage completing with the command result
         */
        public CompletionStage<GetResponseBodyResult> getResponseBody(Fetch.RequestId requestId) {
            Map<String, Object> params = new LinkedHashMap<>();
            params.put("requestId", CdpObject.json(requestId));
            return client.call("Fetch.getResponseBody", params, result_ -> new GetResponseBodyResult(result_));
        }
        /**
         * Returns a handle to the stream representing the response body. The request must be paused in the HeadersReceived stage. Note that after this command the request can&#x27;t be continued as is -- client either needs to cancel it or to provide the response body. The stream only supports sequential read, IO.read will fail if the position is specified. This method is mutually exclusive with getResponseBody. Calling other methods that affect the request or disabling fetch domain before body is received results in an undefined behavior.
         * @param requestId protocol value
         * @return a stage completing with the command result
         */
        public CompletionStage<IO.StreamHandle> takeResponseBodyAsStream(Fetch.RequestId requestId) {
            Map<String, Object> params = new LinkedHashMap<>();
            params.put("requestId", CdpObject.json(requestId));
            return client.call("Fetch.takeResponseBodyAsStream", params, result_ -> new IO.StreamHandle((String) java.util.Objects.requireNonNull(result_.get("stream"))));
        }
        /**
         * Issued when the domain is enabled and the request URL matches the specified filter. The request is paused until the client responds with one of continueRequest, failRequest or fulfillRequest. The stage of the request can be determined by presence of responseErrorReason and responseStatusCode -- the request is at the response stage if either of these fields is present and in the request stage otherwise. Redirect responses and subsequent requests are reported similarly to regular responses and requests. Redirect responses may be distinguished by the value of {@code responseStatusCode} (which is one of 301, 302, 303, 307, 308) along with presence of the {@code location} header. Requests resulting from a redirect will have {@code redirectedRequestId} field set.
         * @param handler event callback
         * @return a removable subscription
         */
        public CdpSubscription onRequestPaused(Consumer<RequestPausedEvent> handler) {
            return client.on("Fetch.requestPaused", RequestPausedEvent::fromMap, handler);
        }
        /**
         * Issued when the domain is enabled with handleAuthRequests set to true. The request is paused until client responds with continueWithAuth.
         * @param handler event callback
         * @return a removable subscription
         */
        public CdpSubscription onAuthRequired(Consumer<AuthRequiredEvent> handler) {
            return client.on("Fetch.authRequired", AuthRequiredEvent::fromMap, handler);
        }
    }
}
