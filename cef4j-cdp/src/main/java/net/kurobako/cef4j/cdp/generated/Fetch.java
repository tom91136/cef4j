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
 * A domain for letting clients substitute browser&#x27;s network layer with client code.
 * @see <a href="https://chromium.googlesource.com/chromium/src/+/refs/tags/146.0.7680.165/third_party/blink/public/devtools_protocol/domains/Fetch.pdl">Pinned protocol source</a>
 */
@SuppressWarnings({"EscapedEntity", "JavaLangClash", "MissingSummary", "UnusedMethod"})
public final class Fetch {
    private Fetch() {}
    @Nullable private static Long numberAsLong(@Nullable Object value) { return value == null ? null : ((Number) value).longValue(); }
    @Nullable private static Double numberAsDouble(@Nullable Object value) { return value == null ? null : ((Number) value).doubleValue(); }
    /**
     * Stages of the request to handle. Request will intercept before the request is sent. Response will intercept after the response is received (but before response body is received).
     */
    public static final class RequestStage {
        private RequestStage() {}
        public static final String REQUEST = "Request";
        public static final String RESPONSE = "Response";
    }
    /**
     */
    public static final class RequestPattern extends CdpObject {
        private RequestPattern(Map<String, Object> values) { super(values); }
        @Nullable public static RequestPattern fromMap(@Nullable Map<String, Object> values) {
            return values == null ? null : new RequestPattern(values);
        }
        public static Builder builder() { return new Builder(); }
        /**
         * Wildcards ({@code &#x27;*&#x27;} -&gt; zero or more, {@code &#x27;?&#x27;} -&gt; exactly one) are allowed. Escape character is backslash. Omitting is equivalent to {@code &quot;*&quot;}.
         * @return the protocol field value
         */
        @Nullable public String urlPattern() {
            return (String) value("urlPattern");
        }
        /**
         * If set, only requests for matching resource types will be intercepted.
         * @return the protocol field value
         */
        @Nullable public String resourceType() {
            return (String) value("resourceType");
        }
        /**
         * Stage at which to begin intercepting requests. Default is Request.
         * @return the protocol field value
         */
        @Nullable public String requestStage() {
            return (String) value("requestStage");
        }
        public static final class Builder {
            private final Map<String, Object> values = new LinkedHashMap<>();
            /**
             * Wildcards ({@code &#x27;*&#x27;} -&gt; zero or more, {@code &#x27;?&#x27;} -&gt; exactly one) are allowed. Escape character is backslash. Omitting is equivalent to {@code &quot;*&quot;}.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder urlPattern(@Nullable String value) {
                if (value == null) values.remove("urlPattern");
                else values.put("urlPattern", jsonValue(value));
                return this;
            }
            /**
             * If set, only requests for matching resource types will be intercepted.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder resourceType(@Nullable String value) {
                if (value == null) values.remove("resourceType");
                else values.put("resourceType", jsonValue(value));
                return this;
            }
            /**
             * Stage at which to begin intercepting requests. Default is Request.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder requestStage(@Nullable String value) {
                if (value == null) values.remove("requestStage");
                else values.put("requestStage", jsonValue(value));
                return this;
            }
            public RequestPattern build() {
                return new RequestPattern(values);
            }
        }
    }
    /**
     * Response HTTP header entry
     */
    public static final class HeaderEntry extends CdpObject {
        private HeaderEntry(Map<String, Object> values) { super(values); }
        @Nullable public static HeaderEntry fromMap(@Nullable Map<String, Object> values) {
            return values == null ? null : new HeaderEntry(values);
        }
        public static Builder builder() { return new Builder(); }
        /**
         * Returns the name field.
         * @return the protocol field value
         */
        @Nullable public String name() {
            return (String) value("name");
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
             * Sets the name field.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder name(@Nullable String value) {
                if (value == null) values.remove("name");
                else values.put("name", jsonValue(value));
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
            public HeaderEntry build() {
                if (!values.containsKey("name")) throw new IllegalStateException("Missing required CDP field: name");
                if (!values.containsKey("value")) throw new IllegalStateException("Missing required CDP field: value");
                return new HeaderEntry(values);
            }
        }
    }
    /**
     * Authorization challenge for HTTP status code 401 or 407.
     */
    public static final class AuthChallenge extends CdpObject {
        private AuthChallenge(Map<String, Object> values) { super(values); }
        @Nullable public static AuthChallenge fromMap(@Nullable Map<String, Object> values) {
            return values == null ? null : new AuthChallenge(values);
        }
        public static Builder builder() { return new Builder(); }
        /**
         * Source of the authentication challenge.
         * @return the protocol field value
         */
        @Nullable public String source() {
            return (String) value("source");
        }
        /**
         * Source of the authentication challenge.
         */
        public static final class SourceValues {
            private SourceValues() {}
            public static final String SERVER = "Server";
            public static final String PROXY = "Proxy";
        }
        /**
         * Origin of the challenger.
         * @return the protocol field value
         */
        @Nullable public String origin() {
            return (String) value("origin");
        }
        /**
         * The authentication scheme used, such as basic or digest
         * @return the protocol field value
         */
        @Nullable public String scheme() {
            return (String) value("scheme");
        }
        /**
         * The realm of the challenge. May be empty.
         * @return the protocol field value
         */
        @Nullable public String realm() {
            return (String) value("realm");
        }
        public static final class Builder {
            private final Map<String, Object> values = new LinkedHashMap<>();
            /**
             * Source of the authentication challenge.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder source(@Nullable String value) {
                if (value == null) values.remove("source");
                else values.put("source", jsonValue(value));
                return this;
            }
            /**
             * Origin of the challenger.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder origin(@Nullable String value) {
                if (value == null) values.remove("origin");
                else values.put("origin", jsonValue(value));
                return this;
            }
            /**
             * The authentication scheme used, such as basic or digest
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder scheme(@Nullable String value) {
                if (value == null) values.remove("scheme");
                else values.put("scheme", jsonValue(value));
                return this;
            }
            /**
             * The realm of the challenge. May be empty.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder realm(@Nullable String value) {
                if (value == null) values.remove("realm");
                else values.put("realm", jsonValue(value));
                return this;
            }
            public AuthChallenge build() {
                if (!values.containsKey("origin")) throw new IllegalStateException("Missing required CDP field: origin");
                if (!values.containsKey("scheme")) throw new IllegalStateException("Missing required CDP field: scheme");
                if (!values.containsKey("realm")) throw new IllegalStateException("Missing required CDP field: realm");
                return new AuthChallenge(values);
            }
        }
    }
    /**
     * Response to an AuthChallenge.
     */
    public static final class AuthChallengeResponse extends CdpObject {
        private AuthChallengeResponse(Map<String, Object> values) { super(values); }
        @Nullable public static AuthChallengeResponse fromMap(@Nullable Map<String, Object> values) {
            return values == null ? null : new AuthChallengeResponse(values);
        }
        public static Builder builder() { return new Builder(); }
        /**
         * The decision on what to do in response to the authorization challenge. Default means deferring to the default behavior of the net stack, which will likely either the Cancel authentication or display a popup dialog box.
         * @return the protocol field value
         */
        @Nullable public String response() {
            return (String) value("response");
        }
        /**
         * The decision on what to do in response to the authorization challenge. Default means deferring to the default behavior of the net stack, which will likely either the Cancel authentication or display a popup dialog box.
         */
        public static final class ResponseValues {
            private ResponseValues() {}
            public static final String DEFAULT = "Default";
            public static final String CANCELAUTH = "CancelAuth";
            public static final String PROVIDECREDENTIALS = "ProvideCredentials";
        }
        /**
         * The username to provide, possibly empty. Should only be set if response is ProvideCredentials.
         * @return the protocol field value
         */
        @Nullable public String username() {
            return (String) value("username");
        }
        /**
         * The password to provide, possibly empty. Should only be set if response is ProvideCredentials.
         * @return the protocol field value
         */
        @Nullable public String password() {
            return (String) value("password");
        }
        public static final class Builder {
            private final Map<String, Object> values = new LinkedHashMap<>();
            /**
             * The decision on what to do in response to the authorization challenge. Default means deferring to the default behavior of the net stack, which will likely either the Cancel authentication or display a popup dialog box.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder response(@Nullable String value) {
                if (value == null) values.remove("response");
                else values.put("response", jsonValue(value));
                return this;
            }
            /**
             * The username to provide, possibly empty. Should only be set if response is ProvideCredentials.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder username(@Nullable String value) {
                if (value == null) values.remove("username");
                else values.put("username", jsonValue(value));
                return this;
            }
            /**
             * The password to provide, possibly empty. Should only be set if response is ProvideCredentials.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder password(@Nullable String value) {
                if (value == null) values.remove("password");
                else values.put("password", jsonValue(value));
                return this;
            }
            public AuthChallengeResponse build() {
                if (!values.containsKey("response")) throw new IllegalStateException("Missing required CDP field: response");
                return new AuthChallengeResponse(values);
            }
        }
    }
    /**
     * Disables the fetch domain.
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
     * Disables the fetch domain.
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
     * Enables issuing of requestPaused events. A request will be paused until client calls one of failRequest, fulfillRequest or continueRequest/continueWithAuth.
     */
    public static final class EnableParams extends CdpObject {
        private EnableParams(Map<String, Object> values) { super(values); }
        @Nullable public static EnableParams fromMap(@Nullable Map<String, Object> values) {
            return values == null ? null : new EnableParams(values);
        }
        public static Builder builder() { return new Builder(); }
        /**
         * If specified, only requests matching any of these patterns will produce fetchRequested event and will be paused until clients response. If not set, all requests will be affected.
         * @return the protocol field value
         */
        @Nullable public java.util.List<Fetch.RequestPattern> patterns() {
            return list(value("patterns"), element0 -> Fetch.RequestPattern.fromMap(objectMap(element0)));
        }
        /**
         * If true, authRequired events will be issued and requests will be paused expecting a call to continueWithAuth.
         * @return the protocol field value
         */
        @Nullable public Boolean handleAuthRequests() {
            return (Boolean) value("handleAuthRequests");
        }
        public static final class Builder {
            private final Map<String, Object> values = new LinkedHashMap<>();
            /**
             * If specified, only requests matching any of these patterns will produce fetchRequested event and will be paused until clients response. If not set, all requests will be affected.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder patterns(@Nullable java.util.List<Fetch.RequestPattern> value) {
                if (value == null) values.remove("patterns");
                else values.put("patterns", jsonValue(value));
                return this;
            }
            /**
             * If true, authRequired events will be issued and requests will be paused expecting a call to continueWithAuth.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder handleAuthRequests(@Nullable Boolean value) {
                if (value == null) values.remove("handleAuthRequests");
                else values.put("handleAuthRequests", jsonValue(value));
                return this;
            }
            public EnableParams build() {
                return new EnableParams(values);
            }
        }
    }
    /**
     * Enables issuing of requestPaused events. A request will be paused until client calls one of failRequest, fulfillRequest or continueRequest/continueWithAuth.
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
     * Causes the request to fail with specified reason.
     */
    public static final class FailRequestParams extends CdpObject {
        private FailRequestParams(Map<String, Object> values) { super(values); }
        @Nullable public static FailRequestParams fromMap(@Nullable Map<String, Object> values) {
            return values == null ? null : new FailRequestParams(values);
        }
        public static Builder builder() { return new Builder(); }
        /**
         * An id the client received in requestPaused event.
         * @return the protocol field value
         */
        @Nullable public String requestId() {
            return (String) value("requestId");
        }
        /**
         * Causes the request to fail with the given reason.
         * @return the protocol field value
         */
        @Nullable public String errorReason() {
            return (String) value("errorReason");
        }
        public static final class Builder {
            private final Map<String, Object> values = new LinkedHashMap<>();
            /**
             * An id the client received in requestPaused event.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder requestId(@Nullable String value) {
                if (value == null) values.remove("requestId");
                else values.put("requestId", jsonValue(value));
                return this;
            }
            /**
             * Causes the request to fail with the given reason.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder errorReason(@Nullable String value) {
                if (value == null) values.remove("errorReason");
                else values.put("errorReason", jsonValue(value));
                return this;
            }
            public FailRequestParams build() {
                if (!values.containsKey("requestId")) throw new IllegalStateException("Missing required CDP field: requestId");
                if (!values.containsKey("errorReason")) throw new IllegalStateException("Missing required CDP field: errorReason");
                return new FailRequestParams(values);
            }
        }
    }
    /**
     * Causes the request to fail with specified reason.
     */
    public static final class FailRequestResult extends CdpObject {
        private FailRequestResult(Map<String, Object> values) { super(values); }
        @Nullable public static FailRequestResult fromMap(@Nullable Map<String, Object> values) {
            return values == null ? null : new FailRequestResult(values);
        }
        public static Builder builder() { return new Builder(); }
        public static final class Builder {
            private final Map<String, Object> values = new LinkedHashMap<>();
            public FailRequestResult build() {
                return new FailRequestResult(values);
            }
        }
    }
    /**
     * Provides response to the request.
     */
    public static final class FulfillRequestParams extends CdpObject {
        private FulfillRequestParams(Map<String, Object> values) { super(values); }
        @Nullable public static FulfillRequestParams fromMap(@Nullable Map<String, Object> values) {
            return values == null ? null : new FulfillRequestParams(values);
        }
        public static Builder builder() { return new Builder(); }
        /**
         * An id the client received in requestPaused event.
         * @return the protocol field value
         */
        @Nullable public String requestId() {
            return (String) value("requestId");
        }
        /**
         * An HTTP response code.
         * @return the protocol field value
         */
        @Nullable public Long responseCode() {
            return numberAsLong(value("responseCode"));
        }
        /**
         * Response headers.
         * @return the protocol field value
         */
        @Nullable public java.util.List<Fetch.HeaderEntry> responseHeaders() {
            return list(value("responseHeaders"), element0 -> Fetch.HeaderEntry.fromMap(objectMap(element0)));
        }
        /**
         * Alternative way of specifying response headers as a \0-separated series of name: value pairs. Prefer the above method unless you need to represent some non-UTF8 values that can&#x27;t be transmitted over the protocol as text. (Encoded as a base64 string when passed over JSON)
         * @return the protocol field value
         */
        @Nullable public String binaryResponseHeaders() {
            return (String) value("binaryResponseHeaders");
        }
        /**
         * A response body. If absent, original response body will be used if the request is intercepted at the response stage and empty body will be used if the request is intercepted at the request stage. (Encoded as a base64 string when passed over JSON)
         * @return the protocol field value
         */
        @Nullable public String body() {
            return (String) value("body");
        }
        /**
         * A textual representation of responseCode. If absent, a standard phrase matching responseCode is used.
         * @return the protocol field value
         */
        @Nullable public String responsePhrase() {
            return (String) value("responsePhrase");
        }
        public static final class Builder {
            private final Map<String, Object> values = new LinkedHashMap<>();
            /**
             * An id the client received in requestPaused event.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder requestId(@Nullable String value) {
                if (value == null) values.remove("requestId");
                else values.put("requestId", jsonValue(value));
                return this;
            }
            /**
             * An HTTP response code.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder responseCode(@Nullable Long value) {
                if (value == null) values.remove("responseCode");
                else values.put("responseCode", jsonValue(value));
                return this;
            }
            /**
             * Response headers.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder responseHeaders(@Nullable java.util.List<Fetch.HeaderEntry> value) {
                if (value == null) values.remove("responseHeaders");
                else values.put("responseHeaders", jsonValue(value));
                return this;
            }
            /**
             * Alternative way of specifying response headers as a \0-separated series of name: value pairs. Prefer the above method unless you need to represent some non-UTF8 values that can&#x27;t be transmitted over the protocol as text. (Encoded as a base64 string when passed over JSON)
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder binaryResponseHeaders(@Nullable String value) {
                if (value == null) values.remove("binaryResponseHeaders");
                else values.put("binaryResponseHeaders", jsonValue(value));
                return this;
            }
            /**
             * A response body. If absent, original response body will be used if the request is intercepted at the response stage and empty body will be used if the request is intercepted at the request stage. (Encoded as a base64 string when passed over JSON)
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder body(@Nullable String value) {
                if (value == null) values.remove("body");
                else values.put("body", jsonValue(value));
                return this;
            }
            /**
             * A textual representation of responseCode. If absent, a standard phrase matching responseCode is used.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder responsePhrase(@Nullable String value) {
                if (value == null) values.remove("responsePhrase");
                else values.put("responsePhrase", jsonValue(value));
                return this;
            }
            public FulfillRequestParams build() {
                if (!values.containsKey("requestId")) throw new IllegalStateException("Missing required CDP field: requestId");
                if (!values.containsKey("responseCode")) throw new IllegalStateException("Missing required CDP field: responseCode");
                return new FulfillRequestParams(values);
            }
        }
    }
    /**
     * Provides response to the request.
     */
    public static final class FulfillRequestResult extends CdpObject {
        private FulfillRequestResult(Map<String, Object> values) { super(values); }
        @Nullable public static FulfillRequestResult fromMap(@Nullable Map<String, Object> values) {
            return values == null ? null : new FulfillRequestResult(values);
        }
        public static Builder builder() { return new Builder(); }
        public static final class Builder {
            private final Map<String, Object> values = new LinkedHashMap<>();
            public FulfillRequestResult build() {
                return new FulfillRequestResult(values);
            }
        }
    }
    /**
     * Continues the request, optionally modifying some of its parameters.
     */
    public static final class ContinueRequestParams extends CdpObject {
        private ContinueRequestParams(Map<String, Object> values) { super(values); }
        @Nullable public static ContinueRequestParams fromMap(@Nullable Map<String, Object> values) {
            return values == null ? null : new ContinueRequestParams(values);
        }
        public static Builder builder() { return new Builder(); }
        /**
         * An id the client received in requestPaused event.
         * @return the protocol field value
         */
        @Nullable public String requestId() {
            return (String) value("requestId");
        }
        /**
         * If set, the request url will be modified in a way that&#x27;s not observable by page.
         * @return the protocol field value
         */
        @Nullable public String url() {
            return (String) value("url");
        }
        /**
         * If set, the request method is overridden.
         * @return the protocol field value
         */
        @Nullable public String method() {
            return (String) value("method");
        }
        /**
         * If set, overrides the post data in the request. (Encoded as a base64 string when passed over JSON)
         * @return the protocol field value
         */
        @Nullable public String postData() {
            return (String) value("postData");
        }
        /**
         * If set, overrides the request headers. Note that the overrides do not extend to subsequent redirect hops, if a redirect happens. Another override may be applied to a different request produced by a redirect.
         * @return the protocol field value
         */
        @Nullable public java.util.List<Fetch.HeaderEntry> headers() {
            return list(value("headers"), element0 -> Fetch.HeaderEntry.fromMap(objectMap(element0)));
        }
        /**
         * If set, overrides response interception behavior for this request.
         * <p><b>Experimental:</b> this part of CDP may change without notice.
         * @return the protocol field value
         */
        @Nullable public Boolean interceptResponse() {
            return (Boolean) value("interceptResponse");
        }
        public static final class Builder {
            private final Map<String, Object> values = new LinkedHashMap<>();
            /**
             * An id the client received in requestPaused event.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder requestId(@Nullable String value) {
                if (value == null) values.remove("requestId");
                else values.put("requestId", jsonValue(value));
                return this;
            }
            /**
             * If set, the request url will be modified in a way that&#x27;s not observable by page.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder url(@Nullable String value) {
                if (value == null) values.remove("url");
                else values.put("url", jsonValue(value));
                return this;
            }
            /**
             * If set, the request method is overridden.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder method(@Nullable String value) {
                if (value == null) values.remove("method");
                else values.put("method", jsonValue(value));
                return this;
            }
            /**
             * If set, overrides the post data in the request. (Encoded as a base64 string when passed over JSON)
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder postData(@Nullable String value) {
                if (value == null) values.remove("postData");
                else values.put("postData", jsonValue(value));
                return this;
            }
            /**
             * If set, overrides the request headers. Note that the overrides do not extend to subsequent redirect hops, if a redirect happens. Another override may be applied to a different request produced by a redirect.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder headers(@Nullable java.util.List<Fetch.HeaderEntry> value) {
                if (value == null) values.remove("headers");
                else values.put("headers", jsonValue(value));
                return this;
            }
            /**
             * If set, overrides response interception behavior for this request.
             * <p><b>Experimental:</b> this part of CDP may change without notice.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder interceptResponse(@Nullable Boolean value) {
                if (value == null) values.remove("interceptResponse");
                else values.put("interceptResponse", jsonValue(value));
                return this;
            }
            public ContinueRequestParams build() {
                if (!values.containsKey("requestId")) throw new IllegalStateException("Missing required CDP field: requestId");
                return new ContinueRequestParams(values);
            }
        }
    }
    /**
     * Continues the request, optionally modifying some of its parameters.
     */
    public static final class ContinueRequestResult extends CdpObject {
        private ContinueRequestResult(Map<String, Object> values) { super(values); }
        @Nullable public static ContinueRequestResult fromMap(@Nullable Map<String, Object> values) {
            return values == null ? null : new ContinueRequestResult(values);
        }
        public static Builder builder() { return new Builder(); }
        public static final class Builder {
            private final Map<String, Object> values = new LinkedHashMap<>();
            public ContinueRequestResult build() {
                return new ContinueRequestResult(values);
            }
        }
    }
    /**
     * Continues a request supplying authChallengeResponse following authRequired event.
     */
    public static final class ContinueWithAuthParams extends CdpObject {
        private ContinueWithAuthParams(Map<String, Object> values) { super(values); }
        @Nullable public static ContinueWithAuthParams fromMap(@Nullable Map<String, Object> values) {
            return values == null ? null : new ContinueWithAuthParams(values);
        }
        public static Builder builder() { return new Builder(); }
        /**
         * An id the client received in authRequired event.
         * @return the protocol field value
         */
        @Nullable public String requestId() {
            return (String) value("requestId");
        }
        /**
         * Response to with an authChallenge.
         * @return the protocol field value
         */
        @Nullable public Fetch.AuthChallengeResponse authChallengeResponse() {
            return Fetch.AuthChallengeResponse.fromMap(objectMap(value("authChallengeResponse")));
        }
        public static final class Builder {
            private final Map<String, Object> values = new LinkedHashMap<>();
            /**
             * An id the client received in authRequired event.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder requestId(@Nullable String value) {
                if (value == null) values.remove("requestId");
                else values.put("requestId", jsonValue(value));
                return this;
            }
            /**
             * Response to with an authChallenge.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder authChallengeResponse(@Nullable Fetch.AuthChallengeResponse value) {
                if (value == null) values.remove("authChallengeResponse");
                else values.put("authChallengeResponse", jsonValue(value));
                return this;
            }
            public ContinueWithAuthParams build() {
                if (!values.containsKey("requestId")) throw new IllegalStateException("Missing required CDP field: requestId");
                if (!values.containsKey("authChallengeResponse")) throw new IllegalStateException("Missing required CDP field: authChallengeResponse");
                return new ContinueWithAuthParams(values);
            }
        }
    }
    /**
     * Continues a request supplying authChallengeResponse following authRequired event.
     */
    public static final class ContinueWithAuthResult extends CdpObject {
        private ContinueWithAuthResult(Map<String, Object> values) { super(values); }
        @Nullable public static ContinueWithAuthResult fromMap(@Nullable Map<String, Object> values) {
            return values == null ? null : new ContinueWithAuthResult(values);
        }
        public static Builder builder() { return new Builder(); }
        public static final class Builder {
            private final Map<String, Object> values = new LinkedHashMap<>();
            public ContinueWithAuthResult build() {
                return new ContinueWithAuthResult(values);
            }
        }
    }
    /**
     * Continues loading of the paused response, optionally modifying the response headers. If either responseCode or headers are modified, all of them must be present.
     * <p><b>Experimental:</b> this part of CDP may change without notice.
     */
    public static final class ContinueResponseParams extends CdpObject {
        private ContinueResponseParams(Map<String, Object> values) { super(values); }
        @Nullable public static ContinueResponseParams fromMap(@Nullable Map<String, Object> values) {
            return values == null ? null : new ContinueResponseParams(values);
        }
        public static Builder builder() { return new Builder(); }
        /**
         * An id the client received in requestPaused event.
         * @return the protocol field value
         */
        @Nullable public String requestId() {
            return (String) value("requestId");
        }
        /**
         * An HTTP response code. If absent, original response code will be used.
         * @return the protocol field value
         */
        @Nullable public Long responseCode() {
            return numberAsLong(value("responseCode"));
        }
        /**
         * A textual representation of responseCode. If absent, a standard phrase matching responseCode is used.
         * @return the protocol field value
         */
        @Nullable public String responsePhrase() {
            return (String) value("responsePhrase");
        }
        /**
         * Response headers. If absent, original response headers will be used.
         * @return the protocol field value
         */
        @Nullable public java.util.List<Fetch.HeaderEntry> responseHeaders() {
            return list(value("responseHeaders"), element0 -> Fetch.HeaderEntry.fromMap(objectMap(element0)));
        }
        /**
         * Alternative way of specifying response headers as a \0-separated series of name: value pairs. Prefer the above method unless you need to represent some non-UTF8 values that can&#x27;t be transmitted over the protocol as text. (Encoded as a base64 string when passed over JSON)
         * @return the protocol field value
         */
        @Nullable public String binaryResponseHeaders() {
            return (String) value("binaryResponseHeaders");
        }
        public static final class Builder {
            private final Map<String, Object> values = new LinkedHashMap<>();
            /**
             * An id the client received in requestPaused event.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder requestId(@Nullable String value) {
                if (value == null) values.remove("requestId");
                else values.put("requestId", jsonValue(value));
                return this;
            }
            /**
             * An HTTP response code. If absent, original response code will be used.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder responseCode(@Nullable Long value) {
                if (value == null) values.remove("responseCode");
                else values.put("responseCode", jsonValue(value));
                return this;
            }
            /**
             * A textual representation of responseCode. If absent, a standard phrase matching responseCode is used.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder responsePhrase(@Nullable String value) {
                if (value == null) values.remove("responsePhrase");
                else values.put("responsePhrase", jsonValue(value));
                return this;
            }
            /**
             * Response headers. If absent, original response headers will be used.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder responseHeaders(@Nullable java.util.List<Fetch.HeaderEntry> value) {
                if (value == null) values.remove("responseHeaders");
                else values.put("responseHeaders", jsonValue(value));
                return this;
            }
            /**
             * Alternative way of specifying response headers as a \0-separated series of name: value pairs. Prefer the above method unless you need to represent some non-UTF8 values that can&#x27;t be transmitted over the protocol as text. (Encoded as a base64 string when passed over JSON)
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder binaryResponseHeaders(@Nullable String value) {
                if (value == null) values.remove("binaryResponseHeaders");
                else values.put("binaryResponseHeaders", jsonValue(value));
                return this;
            }
            public ContinueResponseParams build() {
                if (!values.containsKey("requestId")) throw new IllegalStateException("Missing required CDP field: requestId");
                return new ContinueResponseParams(values);
            }
        }
    }
    /**
     * Continues loading of the paused response, optionally modifying the response headers. If either responseCode or headers are modified, all of them must be present.
     * <p><b>Experimental:</b> this part of CDP may change without notice.
     */
    public static final class ContinueResponseResult extends CdpObject {
        private ContinueResponseResult(Map<String, Object> values) { super(values); }
        @Nullable public static ContinueResponseResult fromMap(@Nullable Map<String, Object> values) {
            return values == null ? null : new ContinueResponseResult(values);
        }
        public static Builder builder() { return new Builder(); }
        public static final class Builder {
            private final Map<String, Object> values = new LinkedHashMap<>();
            public ContinueResponseResult build() {
                return new ContinueResponseResult(values);
            }
        }
    }
    /**
     * Causes the body of the response to be received from the server and returned as a single string. May only be issued for a request that is paused in the Response stage and is mutually exclusive with takeResponseBodyForInterceptionAsStream. Calling other methods that affect the request or disabling fetch domain before body is received results in an undefined behavior. Note that the response body is not available for redirects. Requests paused in the _redirect received_ state may be differentiated by {@code responseCode} and presence of {@code location} response header, see comments to {@code requestPaused} for details.
     */
    public static final class GetResponseBodyParams extends CdpObject {
        private GetResponseBodyParams(Map<String, Object> values) { super(values); }
        @Nullable public static GetResponseBodyParams fromMap(@Nullable Map<String, Object> values) {
            return values == null ? null : new GetResponseBodyParams(values);
        }
        public static Builder builder() { return new Builder(); }
        /**
         * Identifier for the intercepted request to get body for.
         * @return the protocol field value
         */
        @Nullable public String requestId() {
            return (String) value("requestId");
        }
        public static final class Builder {
            private final Map<String, Object> values = new LinkedHashMap<>();
            /**
             * Identifier for the intercepted request to get body for.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder requestId(@Nullable String value) {
                if (value == null) values.remove("requestId");
                else values.put("requestId", jsonValue(value));
                return this;
            }
            public GetResponseBodyParams build() {
                if (!values.containsKey("requestId")) throw new IllegalStateException("Missing required CDP field: requestId");
                return new GetResponseBodyParams(values);
            }
        }
    }
    /**
     * Causes the body of the response to be received from the server and returned as a single string. May only be issued for a request that is paused in the Response stage and is mutually exclusive with takeResponseBodyForInterceptionAsStream. Calling other methods that affect the request or disabling fetch domain before body is received results in an undefined behavior. Note that the response body is not available for redirects. Requests paused in the _redirect received_ state may be differentiated by {@code responseCode} and presence of {@code location} response header, see comments to {@code requestPaused} for details.
     */
    public static final class GetResponseBodyResult extends CdpObject {
        private GetResponseBodyResult(Map<String, Object> values) { super(values); }
        @Nullable public static GetResponseBodyResult fromMap(@Nullable Map<String, Object> values) {
            return values == null ? null : new GetResponseBodyResult(values);
        }
        public static Builder builder() { return new Builder(); }
        /**
         * Response body.
         * @return the protocol field value
         */
        @Nullable public String body() {
            return (String) value("body");
        }
        /**
         * True, if content was sent as base64.
         * @return the protocol field value
         */
        @Nullable public Boolean base64Encoded() {
            return (Boolean) value("base64Encoded");
        }
        public static final class Builder {
            private final Map<String, Object> values = new LinkedHashMap<>();
            /**
             * Response body.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder body(@Nullable String value) {
                if (value == null) values.remove("body");
                else values.put("body", jsonValue(value));
                return this;
            }
            /**
             * True, if content was sent as base64.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder base64Encoded(@Nullable Boolean value) {
                if (value == null) values.remove("base64Encoded");
                else values.put("base64Encoded", jsonValue(value));
                return this;
            }
            public GetResponseBodyResult build() {
                if (!values.containsKey("body")) throw new IllegalStateException("Missing required CDP field: body");
                if (!values.containsKey("base64Encoded")) throw new IllegalStateException("Missing required CDP field: base64Encoded");
                return new GetResponseBodyResult(values);
            }
        }
    }
    /**
     * Returns a handle to the stream representing the response body. The request must be paused in the HeadersReceived stage. Note that after this command the request can&#x27;t be continued as is -- client either needs to cancel it or to provide the response body. The stream only supports sequential read, IO.read will fail if the position is specified. This method is mutually exclusive with getResponseBody. Calling other methods that affect the request or disabling fetch domain before body is received results in an undefined behavior.
     */
    public static final class TakeResponseBodyAsStreamParams extends CdpObject {
        private TakeResponseBodyAsStreamParams(Map<String, Object> values) { super(values); }
        @Nullable public static TakeResponseBodyAsStreamParams fromMap(@Nullable Map<String, Object> values) {
            return values == null ? null : new TakeResponseBodyAsStreamParams(values);
        }
        public static Builder builder() { return new Builder(); }
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
             * Sets the requestId field.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder requestId(@Nullable String value) {
                if (value == null) values.remove("requestId");
                else values.put("requestId", jsonValue(value));
                return this;
            }
            public TakeResponseBodyAsStreamParams build() {
                if (!values.containsKey("requestId")) throw new IllegalStateException("Missing required CDP field: requestId");
                return new TakeResponseBodyAsStreamParams(values);
            }
        }
    }
    /**
     * Returns a handle to the stream representing the response body. The request must be paused in the HeadersReceived stage. Note that after this command the request can&#x27;t be continued as is -- client either needs to cancel it or to provide the response body. The stream only supports sequential read, IO.read will fail if the position is specified. This method is mutually exclusive with getResponseBody. Calling other methods that affect the request or disabling fetch domain before body is received results in an undefined behavior.
     */
    public static final class TakeResponseBodyAsStreamResult extends CdpObject {
        private TakeResponseBodyAsStreamResult(Map<String, Object> values) { super(values); }
        @Nullable public static TakeResponseBodyAsStreamResult fromMap(@Nullable Map<String, Object> values) {
            return values == null ? null : new TakeResponseBodyAsStreamResult(values);
        }
        public static Builder builder() { return new Builder(); }
        /**
         * Returns the stream field.
         * @return the protocol field value
         */
        @Nullable public String stream() {
            return (String) value("stream");
        }
        public static final class Builder {
            private final Map<String, Object> values = new LinkedHashMap<>();
            /**
             * Sets the stream field.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder stream(@Nullable String value) {
                if (value == null) values.remove("stream");
                else values.put("stream", jsonValue(value));
                return this;
            }
            public TakeResponseBodyAsStreamResult build() {
                if (!values.containsKey("stream")) throw new IllegalStateException("Missing required CDP field: stream");
                return new TakeResponseBodyAsStreamResult(values);
            }
        }
    }
    /**
     * Issued when the domain is enabled and the request URL matches the specified filter. The request is paused until the client responds with one of continueRequest, failRequest or fulfillRequest. The stage of the request can be determined by presence of responseErrorReason and responseStatusCode -- the request is at the response stage if either of these fields is present and in the request stage otherwise. Redirect responses and subsequent requests are reported similarly to regular responses and requests. Redirect responses may be distinguished by the value of {@code responseStatusCode} (which is one of 301, 302, 303, 307, 308) along with presence of the {@code location} header. Requests resulting from a redirect will have {@code redirectedRequestId} field set.
     */
    public static final class RequestPausedEvent extends CdpObject {
        private RequestPausedEvent(Map<String, Object> values) { super(values); }
        @Nullable public static RequestPausedEvent fromMap(@Nullable Map<String, Object> values) {
            return values == null ? null : new RequestPausedEvent(values);
        }
        public static Builder builder() { return new Builder(); }
        /**
         * Each request the page makes will have a unique id.
         * @return the protocol field value
         */
        @Nullable public String requestId() {
            return (String) value("requestId");
        }
        /**
         * The details of the request.
         * @return the protocol field value
         */
        @Nullable public Network.Request request() {
            return Network.Request.fromMap(objectMap(value("request")));
        }
        /**
         * The id of the frame that initiated the request.
         * @return the protocol field value
         */
        @Nullable public String frameId() {
            return (String) value("frameId");
        }
        /**
         * How the requested resource will be used.
         * @return the protocol field value
         */
        @Nullable public String resourceType() {
            return (String) value("resourceType");
        }
        /**
         * Response error if intercepted at response stage.
         * @return the protocol field value
         */
        @Nullable public String responseErrorReason() {
            return (String) value("responseErrorReason");
        }
        /**
         * Response code if intercepted at response stage.
         * @return the protocol field value
         */
        @Nullable public Long responseStatusCode() {
            return numberAsLong(value("responseStatusCode"));
        }
        /**
         * Response status text if intercepted at response stage.
         * @return the protocol field value
         */
        @Nullable public String responseStatusText() {
            return (String) value("responseStatusText");
        }
        /**
         * Response headers if intercepted at the response stage.
         * @return the protocol field value
         */
        @Nullable public java.util.List<Fetch.HeaderEntry> responseHeaders() {
            return list(value("responseHeaders"), element0 -> Fetch.HeaderEntry.fromMap(objectMap(element0)));
        }
        /**
         * If the intercepted request had a corresponding Network.requestWillBeSent event fired for it, then this networkId will be the same as the requestId present in the requestWillBeSent event.
         * @return the protocol field value
         */
        @Nullable public String networkId() {
            return (String) value("networkId");
        }
        /**
         * If the request is due to a redirect response from the server, the id of the request that has caused the redirect.
         * <p><b>Experimental:</b> this part of CDP may change without notice.
         * @return the protocol field value
         */
        @Nullable public String redirectedRequestId() {
            return (String) value("redirectedRequestId");
        }
        public static final class Builder {
            private final Map<String, Object> values = new LinkedHashMap<>();
            /**
             * Each request the page makes will have a unique id.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder requestId(@Nullable String value) {
                if (value == null) values.remove("requestId");
                else values.put("requestId", jsonValue(value));
                return this;
            }
            /**
             * The details of the request.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder request(@Nullable Network.Request value) {
                if (value == null) values.remove("request");
                else values.put("request", jsonValue(value));
                return this;
            }
            /**
             * The id of the frame that initiated the request.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder frameId(@Nullable String value) {
                if (value == null) values.remove("frameId");
                else values.put("frameId", jsonValue(value));
                return this;
            }
            /**
             * How the requested resource will be used.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder resourceType(@Nullable String value) {
                if (value == null) values.remove("resourceType");
                else values.put("resourceType", jsonValue(value));
                return this;
            }
            /**
             * Response error if intercepted at response stage.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder responseErrorReason(@Nullable String value) {
                if (value == null) values.remove("responseErrorReason");
                else values.put("responseErrorReason", jsonValue(value));
                return this;
            }
            /**
             * Response code if intercepted at response stage.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder responseStatusCode(@Nullable Long value) {
                if (value == null) values.remove("responseStatusCode");
                else values.put("responseStatusCode", jsonValue(value));
                return this;
            }
            /**
             * Response status text if intercepted at response stage.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder responseStatusText(@Nullable String value) {
                if (value == null) values.remove("responseStatusText");
                else values.put("responseStatusText", jsonValue(value));
                return this;
            }
            /**
             * Response headers if intercepted at the response stage.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder responseHeaders(@Nullable java.util.List<Fetch.HeaderEntry> value) {
                if (value == null) values.remove("responseHeaders");
                else values.put("responseHeaders", jsonValue(value));
                return this;
            }
            /**
             * If the intercepted request had a corresponding Network.requestWillBeSent event fired for it, then this networkId will be the same as the requestId present in the requestWillBeSent event.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder networkId(@Nullable String value) {
                if (value == null) values.remove("networkId");
                else values.put("networkId", jsonValue(value));
                return this;
            }
            /**
             * If the request is due to a redirect response from the server, the id of the request that has caused the redirect.
             * <p><b>Experimental:</b> this part of CDP may change without notice.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder redirectedRequestId(@Nullable String value) {
                if (value == null) values.remove("redirectedRequestId");
                else values.put("redirectedRequestId", jsonValue(value));
                return this;
            }
            public RequestPausedEvent build() {
                if (!values.containsKey("requestId")) throw new IllegalStateException("Missing required CDP field: requestId");
                if (!values.containsKey("request")) throw new IllegalStateException("Missing required CDP field: request");
                if (!values.containsKey("frameId")) throw new IllegalStateException("Missing required CDP field: frameId");
                if (!values.containsKey("resourceType")) throw new IllegalStateException("Missing required CDP field: resourceType");
                return new RequestPausedEvent(values);
            }
        }
    }
    /**
     * Issued when the domain is enabled with handleAuthRequests set to true. The request is paused until client responds with continueWithAuth.
     */
    public static final class AuthRequiredEvent extends CdpObject {
        private AuthRequiredEvent(Map<String, Object> values) { super(values); }
        @Nullable public static AuthRequiredEvent fromMap(@Nullable Map<String, Object> values) {
            return values == null ? null : new AuthRequiredEvent(values);
        }
        public static Builder builder() { return new Builder(); }
        /**
         * Each request the page makes will have a unique id.
         * @return the protocol field value
         */
        @Nullable public String requestId() {
            return (String) value("requestId");
        }
        /**
         * The details of the request.
         * @return the protocol field value
         */
        @Nullable public Network.Request request() {
            return Network.Request.fromMap(objectMap(value("request")));
        }
        /**
         * The id of the frame that initiated the request.
         * @return the protocol field value
         */
        @Nullable public String frameId() {
            return (String) value("frameId");
        }
        /**
         * How the requested resource will be used.
         * @return the protocol field value
         */
        @Nullable public String resourceType() {
            return (String) value("resourceType");
        }
        /**
         * Details of the Authorization Challenge encountered. If this is set, client should respond with continueRequest that contains AuthChallengeResponse.
         * @return the protocol field value
         */
        @Nullable public Fetch.AuthChallenge authChallenge() {
            return Fetch.AuthChallenge.fromMap(objectMap(value("authChallenge")));
        }
        public static final class Builder {
            private final Map<String, Object> values = new LinkedHashMap<>();
            /**
             * Each request the page makes will have a unique id.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder requestId(@Nullable String value) {
                if (value == null) values.remove("requestId");
                else values.put("requestId", jsonValue(value));
                return this;
            }
            /**
             * The details of the request.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder request(@Nullable Network.Request value) {
                if (value == null) values.remove("request");
                else values.put("request", jsonValue(value));
                return this;
            }
            /**
             * The id of the frame that initiated the request.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder frameId(@Nullable String value) {
                if (value == null) values.remove("frameId");
                else values.put("frameId", jsonValue(value));
                return this;
            }
            /**
             * How the requested resource will be used.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder resourceType(@Nullable String value) {
                if (value == null) values.remove("resourceType");
                else values.put("resourceType", jsonValue(value));
                return this;
            }
            /**
             * Details of the Authorization Challenge encountered. If this is set, client should respond with continueRequest that contains AuthChallengeResponse.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder authChallenge(@Nullable Fetch.AuthChallenge value) {
                if (value == null) values.remove("authChallenge");
                else values.put("authChallenge", jsonValue(value));
                return this;
            }
            public AuthRequiredEvent build() {
                if (!values.containsKey("requestId")) throw new IllegalStateException("Missing required CDP field: requestId");
                if (!values.containsKey("request")) throw new IllegalStateException("Missing required CDP field: request");
                if (!values.containsKey("frameId")) throw new IllegalStateException("Missing required CDP field: frameId");
                if (!values.containsKey("resourceType")) throw new IllegalStateException("Missing required CDP field: resourceType");
                if (!values.containsKey("authChallenge")) throw new IllegalStateException("Missing required CDP field: authChallenge");
                return new AuthRequiredEvent(values);
            }
        }
    }
    public static final class Client {
        private final CdpClient client;
        public Client(CdpClient client) { this.client = client; }
        /**
         * Disables the fetch domain.
         * @return a stage completing with the command result
         */
        public CompletionStage<DisableResult> disable() {
            return client.call("Fetch.disable", null, DisableResult::fromMap);
        }
        /**
         * Enables issuing of requestPaused events. A request will be paused until client calls one of failRequest, fulfillRequest or continueRequest/continueWithAuth.
         * @param params command parameters
         * @return a stage completing with the command result
         */
        public CompletionStage<EnableResult> enable(EnableParams params) {
            return client.call("Fetch.enable", params, EnableResult::fromMap);
        }
        /**
         * Causes the request to fail with specified reason.
         * @param params command parameters
         * @return a stage completing with the command result
         */
        public CompletionStage<FailRequestResult> failRequest(FailRequestParams params) {
            return client.call("Fetch.failRequest", params, FailRequestResult::fromMap);
        }
        /**
         * Provides response to the request.
         * @param params command parameters
         * @return a stage completing with the command result
         */
        public CompletionStage<FulfillRequestResult> fulfillRequest(FulfillRequestParams params) {
            return client.call("Fetch.fulfillRequest", params, FulfillRequestResult::fromMap);
        }
        /**
         * Continues the request, optionally modifying some of its parameters.
         * @param params command parameters
         * @return a stage completing with the command result
         */
        public CompletionStage<ContinueRequestResult> continueRequest(ContinueRequestParams params) {
            return client.call("Fetch.continueRequest", params, ContinueRequestResult::fromMap);
        }
        /**
         * Continues a request supplying authChallengeResponse following authRequired event.
         * @param params command parameters
         * @return a stage completing with the command result
         */
        public CompletionStage<ContinueWithAuthResult> continueWithAuth(ContinueWithAuthParams params) {
            return client.call("Fetch.continueWithAuth", params, ContinueWithAuthResult::fromMap);
        }
        /**
         * Continues loading of the paused response, optionally modifying the response headers. If either responseCode or headers are modified, all of them must be present.
         * <p><b>Experimental:</b> this part of CDP may change without notice.
         * @param params command parameters
         * @return a stage completing with the command result
         */
        public CompletionStage<ContinueResponseResult> continueResponse(ContinueResponseParams params) {
            return client.call("Fetch.continueResponse", params, ContinueResponseResult::fromMap);
        }
        /**
         * Causes the body of the response to be received from the server and returned as a single string. May only be issued for a request that is paused in the Response stage and is mutually exclusive with takeResponseBodyForInterceptionAsStream. Calling other methods that affect the request or disabling fetch domain before body is received results in an undefined behavior. Note that the response body is not available for redirects. Requests paused in the _redirect received_ state may be differentiated by {@code responseCode} and presence of {@code location} response header, see comments to {@code requestPaused} for details.
         * @param params command parameters
         * @return a stage completing with the command result
         */
        public CompletionStage<GetResponseBodyResult> getResponseBody(GetResponseBodyParams params) {
            return client.call("Fetch.getResponseBody", params, GetResponseBodyResult::fromMap);
        }
        /**
         * Returns a handle to the stream representing the response body. The request must be paused in the HeadersReceived stage. Note that after this command the request can&#x27;t be continued as is -- client either needs to cancel it or to provide the response body. The stream only supports sequential read, IO.read will fail if the position is specified. This method is mutually exclusive with getResponseBody. Calling other methods that affect the request or disabling fetch domain before body is received results in an undefined behavior.
         * @param params command parameters
         * @return a stage completing with the command result
         */
        public CompletionStage<TakeResponseBodyAsStreamResult> takeResponseBodyAsStream(TakeResponseBodyAsStreamParams params) {
            return client.call("Fetch.takeResponseBodyAsStream", params, TakeResponseBodyAsStreamResult::fromMap);
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
