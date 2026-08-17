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
 * Network domain allows tracking network activities of the page. It exposes information about http, file, data and other requests and responses, their headers, bodies, timing, etc.
 * @see <a href="https://chromium.googlesource.com/chromium/src/+/refs/tags/150.0.7871.213/third_party/blink/public/devtools_protocol/domains/Network.pdl">Pinned protocol source</a>
 */
@Generated("mvn generate-sources -pl cef4j-platform -Dcef.version=150.0.18+gdb11278+chromium-150.0.7871.213")
@SuppressWarnings({"EscapedEntity", "JavaLangClash", "MissingSummary", "UnusedMethod"})
public final class Network {
    private Network() {}
    @Nullable private static Long numberAsLong(@Nullable Object value) { return value == null ? null : ((Number) value).longValue(); }
    @Nullable private static Double numberAsDouble(@Nullable Object value) { return value == null ? null : ((Number) value).doubleValue(); }
    /**
     * Resource type as it was perceived by the rendering engine.
     */
    public static final class ResourceType {
        private ResourceType() {}
        public static final String DOCUMENT = "Document";
        public static final String STYLESHEET = "Stylesheet";
        public static final String IMAGE = "Image";
        public static final String MEDIA = "Media";
        public static final String FONT = "Font";
        public static final String SCRIPT = "Script";
        public static final String TEXTTRACK = "TextTrack";
        public static final String XHR = "XHR";
        public static final String FETCH = "Fetch";
        public static final String PREFETCH = "Prefetch";
        public static final String EVENTSOURCE = "EventSource";
        public static final String WEBSOCKET = "WebSocket";
        public static final String MANIFEST = "Manifest";
        public static final String SIGNEDEXCHANGE = "SignedExchange";
        public static final String PING = "Ping";
        public static final String CSPVIOLATIONREPORT = "CSPViolationReport";
        public static final String PREFLIGHT = "Preflight";
        public static final String FEDCM = "FedCM";
        public static final String OTHER = "Other";
    }
    /**
     * Network level fetch failure reason.
     */
    public static final class ErrorReason {
        private ErrorReason() {}
        public static final String FAILED = "Failed";
        public static final String ABORTED = "Aborted";
        public static final String TIMEDOUT = "TimedOut";
        public static final String ACCESSDENIED = "AccessDenied";
        public static final String CONNECTIONCLOSED = "ConnectionClosed";
        public static final String CONNECTIONRESET = "ConnectionReset";
        public static final String CONNECTIONREFUSED = "ConnectionRefused";
        public static final String CONNECTIONABORTED = "ConnectionAborted";
        public static final String CONNECTIONFAILED = "ConnectionFailed";
        public static final String NAMENOTRESOLVED = "NameNotResolved";
        public static final String INTERNETDISCONNECTED = "InternetDisconnected";
        public static final String ADDRESSUNREACHABLE = "AddressUnreachable";
        public static final String BLOCKEDBYCLIENT = "BlockedByClient";
        public static final String BLOCKEDBYRESPONSE = "BlockedByResponse";
    }
    /**
     * The underlying connection technology that the browser is supposedly using.
     */
    public static final class ConnectionType {
        private ConnectionType() {}
        public static final String NONE = "none";
        public static final String CELLULAR2G = "cellular2g";
        public static final String CELLULAR3G = "cellular3g";
        public static final String CELLULAR4G = "cellular4g";
        public static final String BLUETOOTH = "bluetooth";
        public static final String ETHERNET = "ethernet";
        public static final String WIFI = "wifi";
        public static final String WIMAX = "wimax";
        public static final String OTHER = "other";
    }
    /**
     * Represents the cookie&#x27;s &#x27;SameSite&#x27; status: https://tools.ietf.org/html/draft-west-first-party-cookies
     */
    public static final class CookieSameSite {
        private CookieSameSite() {}
        public static final String STRICT = "Strict";
        public static final String LAX = "Lax";
        public static final String NONE = "None";
    }
    /**
     * Represents the cookie&#x27;s &#x27;Priority&#x27; status: https://tools.ietf.org/html/draft-west-cookie-priority-00
     * <p><b>Experimental:</b> this part of CDP may change without notice.
     */
    public static final class CookiePriority {
        private CookiePriority() {}
        public static final String LOW = "Low";
        public static final String MEDIUM = "Medium";
        public static final String HIGH = "High";
    }
    /**
     * Represents the source scheme of the origin that originally set the cookie. A value of &quot;Unset&quot; allows protocol clients to emulate legacy cookie scope for the scheme. This is a temporary ability and it will be removed in the future.
     * <p><b>Experimental:</b> this part of CDP may change without notice.
     */
    public static final class CookieSourceScheme {
        private CookieSourceScheme() {}
        public static final String UNSET = "Unset";
        public static final String NONSECURE = "NonSecure";
        public static final String SECURE = "Secure";
    }
    /**
     * Timing information for the request.
     */
    public static final class ResourceTiming extends CdpObject {
        private ResourceTiming(Map<String, Object> values) { super(values); }
        @Nullable public static ResourceTiming fromMap(@Nullable Map<String, Object> values) {
            return values == null ? null : new ResourceTiming(values);
        }
        public static Builder builder() { return new Builder(); }
        /**
         * Timing&#x27;s requestTime is a baseline in seconds, while the other numbers are ticks in milliseconds relatively to this requestTime.
         * @return the protocol field value
         */
        @Nullable public Double requestTime() {
            return numberAsDouble(value("requestTime"));
        }
        /**
         * Started resolving proxy.
         * @return the protocol field value
         */
        @Nullable public Double proxyStart() {
            return numberAsDouble(value("proxyStart"));
        }
        /**
         * Finished resolving proxy.
         * @return the protocol field value
         */
        @Nullable public Double proxyEnd() {
            return numberAsDouble(value("proxyEnd"));
        }
        /**
         * Started DNS address resolve.
         * @return the protocol field value
         */
        @Nullable public Double dnsStart() {
            return numberAsDouble(value("dnsStart"));
        }
        /**
         * Finished DNS address resolve.
         * @return the protocol field value
         */
        @Nullable public Double dnsEnd() {
            return numberAsDouble(value("dnsEnd"));
        }
        /**
         * Started connecting to the remote host.
         * @return the protocol field value
         */
        @Nullable public Double connectStart() {
            return numberAsDouble(value("connectStart"));
        }
        /**
         * Connected to the remote host.
         * @return the protocol field value
         */
        @Nullable public Double connectEnd() {
            return numberAsDouble(value("connectEnd"));
        }
        /**
         * Started SSL handshake.
         * @return the protocol field value
         */
        @Nullable public Double sslStart() {
            return numberAsDouble(value("sslStart"));
        }
        /**
         * Finished SSL handshake.
         * @return the protocol field value
         */
        @Nullable public Double sslEnd() {
            return numberAsDouble(value("sslEnd"));
        }
        /**
         * Started running ServiceWorker.
         * <p><b>Experimental:</b> this part of CDP may change without notice.
         * @return the protocol field value
         */
        @Nullable public Double workerStart() {
            return numberAsDouble(value("workerStart"));
        }
        /**
         * Finished Starting ServiceWorker.
         * <p><b>Experimental:</b> this part of CDP may change without notice.
         * @return the protocol field value
         */
        @Nullable public Double workerReady() {
            return numberAsDouble(value("workerReady"));
        }
        /**
         * Started fetch event.
         * <p><b>Experimental:</b> this part of CDP may change without notice.
         * @return the protocol field value
         */
        @Nullable public Double workerFetchStart() {
            return numberAsDouble(value("workerFetchStart"));
        }
        /**
         * Settled fetch event respondWith promise.
         * <p><b>Experimental:</b> this part of CDP may change without notice.
         * @return the protocol field value
         */
        @Nullable public Double workerRespondWithSettled() {
            return numberAsDouble(value("workerRespondWithSettled"));
        }
        /**
         * Started ServiceWorker static routing source evaluation.
         * <p><b>Experimental:</b> this part of CDP may change without notice.
         * @return the protocol field value
         */
        @Nullable public Double workerRouterEvaluationStart() {
            return numberAsDouble(value("workerRouterEvaluationStart"));
        }
        /**
         * Started cache lookup when the source was evaluated to {@code cache}.
         * <p><b>Experimental:</b> this part of CDP may change without notice.
         * @return the protocol field value
         */
        @Nullable public Double workerCacheLookupStart() {
            return numberAsDouble(value("workerCacheLookupStart"));
        }
        /**
         * Started sending request.
         * @return the protocol field value
         */
        @Nullable public Double sendStart() {
            return numberAsDouble(value("sendStart"));
        }
        /**
         * Finished sending request.
         * @return the protocol field value
         */
        @Nullable public Double sendEnd() {
            return numberAsDouble(value("sendEnd"));
        }
        /**
         * Time the server started pushing request.
         * <p><b>Experimental:</b> this part of CDP may change without notice.
         * @return the protocol field value
         */
        @Nullable public Double pushStart() {
            return numberAsDouble(value("pushStart"));
        }
        /**
         * Time the server finished pushing request.
         * <p><b>Experimental:</b> this part of CDP may change without notice.
         * @return the protocol field value
         */
        @Nullable public Double pushEnd() {
            return numberAsDouble(value("pushEnd"));
        }
        /**
         * Started receiving response headers.
         * <p><b>Experimental:</b> this part of CDP may change without notice.
         * @return the protocol field value
         */
        @Nullable public Double receiveHeadersStart() {
            return numberAsDouble(value("receiveHeadersStart"));
        }
        /**
         * Finished receiving response headers.
         * @return the protocol field value
         */
        @Nullable public Double receiveHeadersEnd() {
            return numberAsDouble(value("receiveHeadersEnd"));
        }
        public static final class Builder {
            private final Map<String, Object> values = new LinkedHashMap<>();
            /**
             * Timing&#x27;s requestTime is a baseline in seconds, while the other numbers are ticks in milliseconds relatively to this requestTime.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder requestTime(@Nullable Double value) {
                if (value == null) values.remove("requestTime");
                else values.put("requestTime", jsonValue(value));
                return this;
            }
            /**
             * Started resolving proxy.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder proxyStart(@Nullable Double value) {
                if (value == null) values.remove("proxyStart");
                else values.put("proxyStart", jsonValue(value));
                return this;
            }
            /**
             * Finished resolving proxy.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder proxyEnd(@Nullable Double value) {
                if (value == null) values.remove("proxyEnd");
                else values.put("proxyEnd", jsonValue(value));
                return this;
            }
            /**
             * Started DNS address resolve.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder dnsStart(@Nullable Double value) {
                if (value == null) values.remove("dnsStart");
                else values.put("dnsStart", jsonValue(value));
                return this;
            }
            /**
             * Finished DNS address resolve.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder dnsEnd(@Nullable Double value) {
                if (value == null) values.remove("dnsEnd");
                else values.put("dnsEnd", jsonValue(value));
                return this;
            }
            /**
             * Started connecting to the remote host.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder connectStart(@Nullable Double value) {
                if (value == null) values.remove("connectStart");
                else values.put("connectStart", jsonValue(value));
                return this;
            }
            /**
             * Connected to the remote host.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder connectEnd(@Nullable Double value) {
                if (value == null) values.remove("connectEnd");
                else values.put("connectEnd", jsonValue(value));
                return this;
            }
            /**
             * Started SSL handshake.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder sslStart(@Nullable Double value) {
                if (value == null) values.remove("sslStart");
                else values.put("sslStart", jsonValue(value));
                return this;
            }
            /**
             * Finished SSL handshake.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder sslEnd(@Nullable Double value) {
                if (value == null) values.remove("sslEnd");
                else values.put("sslEnd", jsonValue(value));
                return this;
            }
            /**
             * Started running ServiceWorker.
             * <p><b>Experimental:</b> this part of CDP may change without notice.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder workerStart(@Nullable Double value) {
                if (value == null) values.remove("workerStart");
                else values.put("workerStart", jsonValue(value));
                return this;
            }
            /**
             * Finished Starting ServiceWorker.
             * <p><b>Experimental:</b> this part of CDP may change without notice.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder workerReady(@Nullable Double value) {
                if (value == null) values.remove("workerReady");
                else values.put("workerReady", jsonValue(value));
                return this;
            }
            /**
             * Started fetch event.
             * <p><b>Experimental:</b> this part of CDP may change without notice.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder workerFetchStart(@Nullable Double value) {
                if (value == null) values.remove("workerFetchStart");
                else values.put("workerFetchStart", jsonValue(value));
                return this;
            }
            /**
             * Settled fetch event respondWith promise.
             * <p><b>Experimental:</b> this part of CDP may change without notice.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder workerRespondWithSettled(@Nullable Double value) {
                if (value == null) values.remove("workerRespondWithSettled");
                else values.put("workerRespondWithSettled", jsonValue(value));
                return this;
            }
            /**
             * Started ServiceWorker static routing source evaluation.
             * <p><b>Experimental:</b> this part of CDP may change without notice.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder workerRouterEvaluationStart(@Nullable Double value) {
                if (value == null) values.remove("workerRouterEvaluationStart");
                else values.put("workerRouterEvaluationStart", jsonValue(value));
                return this;
            }
            /**
             * Started cache lookup when the source was evaluated to {@code cache}.
             * <p><b>Experimental:</b> this part of CDP may change without notice.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder workerCacheLookupStart(@Nullable Double value) {
                if (value == null) values.remove("workerCacheLookupStart");
                else values.put("workerCacheLookupStart", jsonValue(value));
                return this;
            }
            /**
             * Started sending request.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder sendStart(@Nullable Double value) {
                if (value == null) values.remove("sendStart");
                else values.put("sendStart", jsonValue(value));
                return this;
            }
            /**
             * Finished sending request.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder sendEnd(@Nullable Double value) {
                if (value == null) values.remove("sendEnd");
                else values.put("sendEnd", jsonValue(value));
                return this;
            }
            /**
             * Time the server started pushing request.
             * <p><b>Experimental:</b> this part of CDP may change without notice.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder pushStart(@Nullable Double value) {
                if (value == null) values.remove("pushStart");
                else values.put("pushStart", jsonValue(value));
                return this;
            }
            /**
             * Time the server finished pushing request.
             * <p><b>Experimental:</b> this part of CDP may change without notice.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder pushEnd(@Nullable Double value) {
                if (value == null) values.remove("pushEnd");
                else values.put("pushEnd", jsonValue(value));
                return this;
            }
            /**
             * Started receiving response headers.
             * <p><b>Experimental:</b> this part of CDP may change without notice.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder receiveHeadersStart(@Nullable Double value) {
                if (value == null) values.remove("receiveHeadersStart");
                else values.put("receiveHeadersStart", jsonValue(value));
                return this;
            }
            /**
             * Finished receiving response headers.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder receiveHeadersEnd(@Nullable Double value) {
                if (value == null) values.remove("receiveHeadersEnd");
                else values.put("receiveHeadersEnd", jsonValue(value));
                return this;
            }
            public ResourceTiming build() {
                if (!values.containsKey("requestTime")) throw new IllegalStateException("Missing required CDP field: requestTime");
                if (!values.containsKey("proxyStart")) throw new IllegalStateException("Missing required CDP field: proxyStart");
                if (!values.containsKey("proxyEnd")) throw new IllegalStateException("Missing required CDP field: proxyEnd");
                if (!values.containsKey("dnsStart")) throw new IllegalStateException("Missing required CDP field: dnsStart");
                if (!values.containsKey("dnsEnd")) throw new IllegalStateException("Missing required CDP field: dnsEnd");
                if (!values.containsKey("connectStart")) throw new IllegalStateException("Missing required CDP field: connectStart");
                if (!values.containsKey("connectEnd")) throw new IllegalStateException("Missing required CDP field: connectEnd");
                if (!values.containsKey("sslStart")) throw new IllegalStateException("Missing required CDP field: sslStart");
                if (!values.containsKey("sslEnd")) throw new IllegalStateException("Missing required CDP field: sslEnd");
                if (!values.containsKey("workerStart")) throw new IllegalStateException("Missing required CDP field: workerStart");
                if (!values.containsKey("workerReady")) throw new IllegalStateException("Missing required CDP field: workerReady");
                if (!values.containsKey("workerFetchStart")) throw new IllegalStateException("Missing required CDP field: workerFetchStart");
                if (!values.containsKey("workerRespondWithSettled")) throw new IllegalStateException("Missing required CDP field: workerRespondWithSettled");
                if (!values.containsKey("sendStart")) throw new IllegalStateException("Missing required CDP field: sendStart");
                if (!values.containsKey("sendEnd")) throw new IllegalStateException("Missing required CDP field: sendEnd");
                if (!values.containsKey("pushStart")) throw new IllegalStateException("Missing required CDP field: pushStart");
                if (!values.containsKey("pushEnd")) throw new IllegalStateException("Missing required CDP field: pushEnd");
                if (!values.containsKey("receiveHeadersStart")) throw new IllegalStateException("Missing required CDP field: receiveHeadersStart");
                if (!values.containsKey("receiveHeadersEnd")) throw new IllegalStateException("Missing required CDP field: receiveHeadersEnd");
                return new ResourceTiming(values);
            }
        }
    }
    /**
     * Loading priority of a resource request.
     */
    public static final class ResourcePriority {
        private ResourcePriority() {}
        public static final String VERYLOW = "VeryLow";
        public static final String LOW = "Low";
        public static final String MEDIUM = "Medium";
        public static final String HIGH = "High";
        public static final String VERYHIGH = "VeryHigh";
    }
    /**
     * The render-blocking behavior of a resource request.
     * <p><b>Experimental:</b> this part of CDP may change without notice.
     */
    public static final class RenderBlockingBehavior {
        private RenderBlockingBehavior() {}
        public static final String BLOCKING = "Blocking";
        public static final String INBODYPARSERBLOCKING = "InBodyParserBlocking";
        public static final String NONBLOCKING = "NonBlocking";
        public static final String NONBLOCKINGDYNAMIC = "NonBlockingDynamic";
        public static final String POTENTIALLYBLOCKING = "PotentiallyBlocking";
    }
    /**
     * Post data entry for HTTP request
     */
    public static final class PostDataEntry extends CdpObject {
        private PostDataEntry(Map<String, Object> values) { super(values); }
        @Nullable public static PostDataEntry fromMap(@Nullable Map<String, Object> values) {
            return values == null ? null : new PostDataEntry(values);
        }
        public static Builder builder() { return new Builder(); }
        /**
         * Returns the bytes field.
         * @return the protocol field value
         */
        @Nullable public String bytes() {
            return (String) value("bytes");
        }
        public static final class Builder {
            private final Map<String, Object> values = new LinkedHashMap<>();
            /**
             * Sets the bytes field.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder bytes(@Nullable String value) {
                if (value == null) values.remove("bytes");
                else values.put("bytes", jsonValue(value));
                return this;
            }
            public PostDataEntry build() {
                return new PostDataEntry(values);
            }
        }
    }
    /**
     * HTTP request data.
     */
    public static final class Request extends CdpObject {
        private Request(Map<String, Object> values) { super(values); }
        @Nullable public static Request fromMap(@Nullable Map<String, Object> values) {
            return values == null ? null : new Request(values);
        }
        public static Builder builder() { return new Builder(); }
        /**
         * Request URL (without fragment).
         * @return the protocol field value
         */
        @Nullable public String url() {
            return (String) value("url");
        }
        /**
         * Fragment of the requested URL starting with hash, if present.
         * @return the protocol field value
         */
        @Nullable public String urlFragment() {
            return (String) value("urlFragment");
        }
        /**
         * HTTP request method.
         * @return the protocol field value
         */
        @Nullable public String method() {
            return (String) value("method");
        }
        /**
         * HTTP request headers.
         * @return the protocol field value
         */
        @Nullable public java.util.Map<String, Object> headers() {
            return objectMap(value("headers"));
        }
        /**
         * HTTP POST request data. Use postDataEntries instead.
         * @return the protocol field value
         * @deprecated Deprecated by the Chromium DevTools Protocol.
         */
        @Deprecated
        @Nullable public String postData() {
            return (String) value("postData");
        }
        /**
         * True when the request has POST data. Note that postData might still be omitted when this flag is true when the data is too long.
         * @return the protocol field value
         */
        @Nullable public Boolean hasPostData() {
            return (Boolean) value("hasPostData");
        }
        /**
         * Request body elements (post data broken into individual entries).
         * <p><b>Experimental:</b> this part of CDP may change without notice.
         * @return the protocol field value
         */
        @Nullable public java.util.List<Network.PostDataEntry> postDataEntries() {
            return list(value("postDataEntries"), element0 -> Network.PostDataEntry.fromMap(objectMap(element0)));
        }
        /**
         * The mixed content type of the request.
         * @return the protocol field value
         */
        @Nullable public String mixedContentType() {
            return (String) value("mixedContentType");
        }
        /**
         * Priority of the resource request at the time request is sent.
         * @return the protocol field value
         */
        @Nullable public String initialPriority() {
            return (String) value("initialPriority");
        }
        /**
         * The referrer policy of the request, as defined in https://www.w3.org/TR/referrer-policy/
         * @return the protocol field value
         */
        @Nullable public String referrerPolicy() {
            return (String) value("referrerPolicy");
        }
        /**
         * The referrer policy of the request, as defined in https://www.w3.org/TR/referrer-policy/
         */
        public static final class ReferrerPolicyValues {
            private ReferrerPolicyValues() {}
            public static final String UNSAFE_URL = "unsafe-url";
            public static final String NO_REFERRER_WHEN_DOWNGRADE = "no-referrer-when-downgrade";
            public static final String NO_REFERRER = "no-referrer";
            public static final String ORIGIN = "origin";
            public static final String ORIGIN_WHEN_CROSS_ORIGIN = "origin-when-cross-origin";
            public static final String SAME_ORIGIN = "same-origin";
            public static final String STRICT_ORIGIN = "strict-origin";
            public static final String STRICT_ORIGIN_WHEN_CROSS_ORIGIN = "strict-origin-when-cross-origin";
        }
        /**
         * Whether is loaded via link preload.
         * @return the protocol field value
         */
        @Nullable public Boolean isLinkPreload() {
            return (Boolean) value("isLinkPreload");
        }
        /**
         * Set for requests when the TrustToken API is used. Contains the parameters passed by the developer (e.g. via &quot;fetch&quot;) as understood by the backend.
         * <p><b>Experimental:</b> this part of CDP may change without notice.
         * @return the protocol field value
         */
        @Nullable public Network.TrustTokenParams trustTokenParams() {
            return Network.TrustTokenParams.fromMap(objectMap(value("trustTokenParams")));
        }
        /**
         * True if this resource request is considered to be the &#x27;same site&#x27; as the request corresponding to the main frame.
         * <p><b>Experimental:</b> this part of CDP may change without notice.
         * @return the protocol field value
         */
        @Nullable public Boolean isSameSite() {
            return (Boolean) value("isSameSite");
        }
        /**
         * True when the resource request is ad-related.
         * <p><b>Experimental:</b> this part of CDP may change without notice.
         * @return the protocol field value
         */
        @Nullable public Boolean isAdRelated() {
            return (Boolean) value("isAdRelated");
        }
        public static final class Builder {
            private final Map<String, Object> values = new LinkedHashMap<>();
            /**
             * Request URL (without fragment).
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder url(@Nullable String value) {
                if (value == null) values.remove("url");
                else values.put("url", jsonValue(value));
                return this;
            }
            /**
             * Fragment of the requested URL starting with hash, if present.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder urlFragment(@Nullable String value) {
                if (value == null) values.remove("urlFragment");
                else values.put("urlFragment", jsonValue(value));
                return this;
            }
            /**
             * HTTP request method.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder method(@Nullable String value) {
                if (value == null) values.remove("method");
                else values.put("method", jsonValue(value));
                return this;
            }
            /**
             * HTTP request headers.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder headers(@Nullable java.util.Map<String, Object> value) {
                if (value == null) values.remove("headers");
                else values.put("headers", jsonValue(value));
                return this;
            }
            /**
             * HTTP POST request data. Use postDataEntries instead.
             * @param value field value; null removes an optional value
             * @return this builder
             * @deprecated Deprecated by the Chromium DevTools Protocol.
             */
            @Deprecated
            public Builder postData(@Nullable String value) {
                if (value == null) values.remove("postData");
                else values.put("postData", jsonValue(value));
                return this;
            }
            /**
             * True when the request has POST data. Note that postData might still be omitted when this flag is true when the data is too long.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder hasPostData(@Nullable Boolean value) {
                if (value == null) values.remove("hasPostData");
                else values.put("hasPostData", jsonValue(value));
                return this;
            }
            /**
             * Request body elements (post data broken into individual entries).
             * <p><b>Experimental:</b> this part of CDP may change without notice.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder postDataEntries(@Nullable java.util.List<Network.PostDataEntry> value) {
                if (value == null) values.remove("postDataEntries");
                else values.put("postDataEntries", jsonValue(value));
                return this;
            }
            /**
             * The mixed content type of the request.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder mixedContentType(@Nullable String value) {
                if (value == null) values.remove("mixedContentType");
                else values.put("mixedContentType", jsonValue(value));
                return this;
            }
            /**
             * Priority of the resource request at the time request is sent.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder initialPriority(@Nullable String value) {
                if (value == null) values.remove("initialPriority");
                else values.put("initialPriority", jsonValue(value));
                return this;
            }
            /**
             * The referrer policy of the request, as defined in https://www.w3.org/TR/referrer-policy/
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder referrerPolicy(@Nullable String value) {
                if (value == null) values.remove("referrerPolicy");
                else values.put("referrerPolicy", jsonValue(value));
                return this;
            }
            /**
             * Whether is loaded via link preload.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder isLinkPreload(@Nullable Boolean value) {
                if (value == null) values.remove("isLinkPreload");
                else values.put("isLinkPreload", jsonValue(value));
                return this;
            }
            /**
             * Set for requests when the TrustToken API is used. Contains the parameters passed by the developer (e.g. via &quot;fetch&quot;) as understood by the backend.
             * <p><b>Experimental:</b> this part of CDP may change without notice.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder trustTokenParams(@Nullable Network.TrustTokenParams value) {
                if (value == null) values.remove("trustTokenParams");
                else values.put("trustTokenParams", jsonValue(value));
                return this;
            }
            /**
             * True if this resource request is considered to be the &#x27;same site&#x27; as the request corresponding to the main frame.
             * <p><b>Experimental:</b> this part of CDP may change without notice.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder isSameSite(@Nullable Boolean value) {
                if (value == null) values.remove("isSameSite");
                else values.put("isSameSite", jsonValue(value));
                return this;
            }
            /**
             * True when the resource request is ad-related.
             * <p><b>Experimental:</b> this part of CDP may change without notice.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder isAdRelated(@Nullable Boolean value) {
                if (value == null) values.remove("isAdRelated");
                else values.put("isAdRelated", jsonValue(value));
                return this;
            }
            public Request build() {
                if (!values.containsKey("url")) throw new IllegalStateException("Missing required CDP field: url");
                if (!values.containsKey("method")) throw new IllegalStateException("Missing required CDP field: method");
                if (!values.containsKey("headers")) throw new IllegalStateException("Missing required CDP field: headers");
                if (!values.containsKey("initialPriority")) throw new IllegalStateException("Missing required CDP field: initialPriority");
                if (!values.containsKey("referrerPolicy")) throw new IllegalStateException("Missing required CDP field: referrerPolicy");
                return new Request(values);
            }
        }
    }
    /**
     * Details of a signed certificate timestamp (SCT).
     */
    public static final class SignedCertificateTimestamp extends CdpObject {
        private SignedCertificateTimestamp(Map<String, Object> values) { super(values); }
        @Nullable public static SignedCertificateTimestamp fromMap(@Nullable Map<String, Object> values) {
            return values == null ? null : new SignedCertificateTimestamp(values);
        }
        public static Builder builder() { return new Builder(); }
        /**
         * Validation status.
         * @return the protocol field value
         */
        @Nullable public String status() {
            return (String) value("status");
        }
        /**
         * Origin.
         * @return the protocol field value
         */
        @Nullable public String origin() {
            return (String) value("origin");
        }
        /**
         * Log name / description.
         * @return the protocol field value
         */
        @Nullable public String logDescription() {
            return (String) value("logDescription");
        }
        /**
         * Log ID.
         * @return the protocol field value
         */
        @Nullable public String logId() {
            return (String) value("logId");
        }
        /**
         * Issuance date. Unlike TimeSinceEpoch, this contains the number of milliseconds since January 1, 1970, UTC, not the number of seconds.
         * @return the protocol field value
         */
        @Nullable public Double timestamp() {
            return numberAsDouble(value("timestamp"));
        }
        /**
         * Hash algorithm.
         * @return the protocol field value
         */
        @Nullable public String hashAlgorithm() {
            return (String) value("hashAlgorithm");
        }
        /**
         * Signature algorithm.
         * @return the protocol field value
         */
        @Nullable public String signatureAlgorithm() {
            return (String) value("signatureAlgorithm");
        }
        /**
         * Signature data.
         * @return the protocol field value
         */
        @Nullable public String signatureData() {
            return (String) value("signatureData");
        }
        public static final class Builder {
            private final Map<String, Object> values = new LinkedHashMap<>();
            /**
             * Validation status.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder status(@Nullable String value) {
                if (value == null) values.remove("status");
                else values.put("status", jsonValue(value));
                return this;
            }
            /**
             * Origin.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder origin(@Nullable String value) {
                if (value == null) values.remove("origin");
                else values.put("origin", jsonValue(value));
                return this;
            }
            /**
             * Log name / description.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder logDescription(@Nullable String value) {
                if (value == null) values.remove("logDescription");
                else values.put("logDescription", jsonValue(value));
                return this;
            }
            /**
             * Log ID.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder logId(@Nullable String value) {
                if (value == null) values.remove("logId");
                else values.put("logId", jsonValue(value));
                return this;
            }
            /**
             * Issuance date. Unlike TimeSinceEpoch, this contains the number of milliseconds since January 1, 1970, UTC, not the number of seconds.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder timestamp(@Nullable Double value) {
                if (value == null) values.remove("timestamp");
                else values.put("timestamp", jsonValue(value));
                return this;
            }
            /**
             * Hash algorithm.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder hashAlgorithm(@Nullable String value) {
                if (value == null) values.remove("hashAlgorithm");
                else values.put("hashAlgorithm", jsonValue(value));
                return this;
            }
            /**
             * Signature algorithm.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder signatureAlgorithm(@Nullable String value) {
                if (value == null) values.remove("signatureAlgorithm");
                else values.put("signatureAlgorithm", jsonValue(value));
                return this;
            }
            /**
             * Signature data.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder signatureData(@Nullable String value) {
                if (value == null) values.remove("signatureData");
                else values.put("signatureData", jsonValue(value));
                return this;
            }
            public SignedCertificateTimestamp build() {
                if (!values.containsKey("status")) throw new IllegalStateException("Missing required CDP field: status");
                if (!values.containsKey("origin")) throw new IllegalStateException("Missing required CDP field: origin");
                if (!values.containsKey("logDescription")) throw new IllegalStateException("Missing required CDP field: logDescription");
                if (!values.containsKey("logId")) throw new IllegalStateException("Missing required CDP field: logId");
                if (!values.containsKey("timestamp")) throw new IllegalStateException("Missing required CDP field: timestamp");
                if (!values.containsKey("hashAlgorithm")) throw new IllegalStateException("Missing required CDP field: hashAlgorithm");
                if (!values.containsKey("signatureAlgorithm")) throw new IllegalStateException("Missing required CDP field: signatureAlgorithm");
                if (!values.containsKey("signatureData")) throw new IllegalStateException("Missing required CDP field: signatureData");
                return new SignedCertificateTimestamp(values);
            }
        }
    }
    /**
     * Security details about a request.
     */
    public static final class SecurityDetails extends CdpObject {
        private SecurityDetails(Map<String, Object> values) { super(values); }
        @Nullable public static SecurityDetails fromMap(@Nullable Map<String, Object> values) {
            return values == null ? null : new SecurityDetails(values);
        }
        public static Builder builder() { return new Builder(); }
        /**
         * Protocol name (e.g. &quot;TLS 1.2&quot; or &quot;QUIC&quot;).
         * @return the protocol field value
         */
        @Nullable public String protocol() {
            return (String) value("protocol");
        }
        /**
         * Key Exchange used by the connection, or the empty string if not applicable.
         * @return the protocol field value
         */
        @Nullable public String keyExchange() {
            return (String) value("keyExchange");
        }
        /**
         * (EC)DH group used by the connection, if applicable.
         * @return the protocol field value
         */
        @Nullable public String keyExchangeGroup() {
            return (String) value("keyExchangeGroup");
        }
        /**
         * Cipher name.
         * @return the protocol field value
         */
        @Nullable public String cipher() {
            return (String) value("cipher");
        }
        /**
         * TLS MAC. Note that AEAD ciphers do not have separate MACs.
         * @return the protocol field value
         */
        @Nullable public String mac() {
            return (String) value("mac");
        }
        /**
         * Certificate ID value.
         * @return the protocol field value
         */
        @Nullable public Long certificateId() {
            return numberAsLong(value("certificateId"));
        }
        /**
         * Certificate subject name.
         * @return the protocol field value
         */
        @Nullable public String subjectName() {
            return (String) value("subjectName");
        }
        /**
         * Subject Alternative Name (SAN) DNS names and IP addresses.
         * @return the protocol field value
         */
        @Nullable public java.util.List<String> sanList() {
            return list(value("sanList"), element0 -> (String) element0);
        }
        /**
         * Name of the issuing CA.
         * @return the protocol field value
         */
        @Nullable public String issuer() {
            return (String) value("issuer");
        }
        /**
         * Certificate valid from date.
         * @return the protocol field value
         */
        @Nullable public Double validFrom() {
            return numberAsDouble(value("validFrom"));
        }
        /**
         * Certificate valid to (expiration) date
         * @return the protocol field value
         */
        @Nullable public Double validTo() {
            return numberAsDouble(value("validTo"));
        }
        /**
         * List of signed certificate timestamps (SCTs).
         * @return the protocol field value
         */
        @Nullable public java.util.List<Network.SignedCertificateTimestamp> signedCertificateTimestampList() {
            return list(value("signedCertificateTimestampList"), element0 -> Network.SignedCertificateTimestamp.fromMap(objectMap(element0)));
        }
        /**
         * Whether the request complied with Certificate Transparency policy
         * @return the protocol field value
         */
        @Nullable public String certificateTransparencyCompliance() {
            return (String) value("certificateTransparencyCompliance");
        }
        /**
         * The signature algorithm used by the server in the TLS server signature, represented as a TLS SignatureScheme code point. Omitted if not applicable or not known.
         * @return the protocol field value
         */
        @Nullable public Long serverSignatureAlgorithm() {
            return numberAsLong(value("serverSignatureAlgorithm"));
        }
        /**
         * Whether the connection used Encrypted ClientHello
         * @return the protocol field value
         */
        @Nullable public Boolean encryptedClientHello() {
            return (Boolean) value("encryptedClientHello");
        }
        public static final class Builder {
            private final Map<String, Object> values = new LinkedHashMap<>();
            /**
             * Protocol name (e.g. &quot;TLS 1.2&quot; or &quot;QUIC&quot;).
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder protocol(@Nullable String value) {
                if (value == null) values.remove("protocol");
                else values.put("protocol", jsonValue(value));
                return this;
            }
            /**
             * Key Exchange used by the connection, or the empty string if not applicable.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder keyExchange(@Nullable String value) {
                if (value == null) values.remove("keyExchange");
                else values.put("keyExchange", jsonValue(value));
                return this;
            }
            /**
             * (EC)DH group used by the connection, if applicable.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder keyExchangeGroup(@Nullable String value) {
                if (value == null) values.remove("keyExchangeGroup");
                else values.put("keyExchangeGroup", jsonValue(value));
                return this;
            }
            /**
             * Cipher name.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder cipher(@Nullable String value) {
                if (value == null) values.remove("cipher");
                else values.put("cipher", jsonValue(value));
                return this;
            }
            /**
             * TLS MAC. Note that AEAD ciphers do not have separate MACs.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder mac(@Nullable String value) {
                if (value == null) values.remove("mac");
                else values.put("mac", jsonValue(value));
                return this;
            }
            /**
             * Certificate ID value.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder certificateId(@Nullable Long value) {
                if (value == null) values.remove("certificateId");
                else values.put("certificateId", jsonValue(value));
                return this;
            }
            /**
             * Certificate subject name.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder subjectName(@Nullable String value) {
                if (value == null) values.remove("subjectName");
                else values.put("subjectName", jsonValue(value));
                return this;
            }
            /**
             * Subject Alternative Name (SAN) DNS names and IP addresses.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder sanList(@Nullable java.util.List<String> value) {
                if (value == null) values.remove("sanList");
                else values.put("sanList", jsonValue(value));
                return this;
            }
            /**
             * Name of the issuing CA.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder issuer(@Nullable String value) {
                if (value == null) values.remove("issuer");
                else values.put("issuer", jsonValue(value));
                return this;
            }
            /**
             * Certificate valid from date.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder validFrom(@Nullable Double value) {
                if (value == null) values.remove("validFrom");
                else values.put("validFrom", jsonValue(value));
                return this;
            }
            /**
             * Certificate valid to (expiration) date
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder validTo(@Nullable Double value) {
                if (value == null) values.remove("validTo");
                else values.put("validTo", jsonValue(value));
                return this;
            }
            /**
             * List of signed certificate timestamps (SCTs).
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder signedCertificateTimestampList(@Nullable java.util.List<Network.SignedCertificateTimestamp> value) {
                if (value == null) values.remove("signedCertificateTimestampList");
                else values.put("signedCertificateTimestampList", jsonValue(value));
                return this;
            }
            /**
             * Whether the request complied with Certificate Transparency policy
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder certificateTransparencyCompliance(@Nullable String value) {
                if (value == null) values.remove("certificateTransparencyCompliance");
                else values.put("certificateTransparencyCompliance", jsonValue(value));
                return this;
            }
            /**
             * The signature algorithm used by the server in the TLS server signature, represented as a TLS SignatureScheme code point. Omitted if not applicable or not known.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder serverSignatureAlgorithm(@Nullable Long value) {
                if (value == null) values.remove("serverSignatureAlgorithm");
                else values.put("serverSignatureAlgorithm", jsonValue(value));
                return this;
            }
            /**
             * Whether the connection used Encrypted ClientHello
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder encryptedClientHello(@Nullable Boolean value) {
                if (value == null) values.remove("encryptedClientHello");
                else values.put("encryptedClientHello", jsonValue(value));
                return this;
            }
            public SecurityDetails build() {
                if (!values.containsKey("protocol")) throw new IllegalStateException("Missing required CDP field: protocol");
                if (!values.containsKey("keyExchange")) throw new IllegalStateException("Missing required CDP field: keyExchange");
                if (!values.containsKey("cipher")) throw new IllegalStateException("Missing required CDP field: cipher");
                if (!values.containsKey("certificateId")) throw new IllegalStateException("Missing required CDP field: certificateId");
                if (!values.containsKey("subjectName")) throw new IllegalStateException("Missing required CDP field: subjectName");
                if (!values.containsKey("sanList")) throw new IllegalStateException("Missing required CDP field: sanList");
                if (!values.containsKey("issuer")) throw new IllegalStateException("Missing required CDP field: issuer");
                if (!values.containsKey("validFrom")) throw new IllegalStateException("Missing required CDP field: validFrom");
                if (!values.containsKey("validTo")) throw new IllegalStateException("Missing required CDP field: validTo");
                if (!values.containsKey("signedCertificateTimestampList")) throw new IllegalStateException("Missing required CDP field: signedCertificateTimestampList");
                if (!values.containsKey("certificateTransparencyCompliance")) throw new IllegalStateException("Missing required CDP field: certificateTransparencyCompliance");
                if (!values.containsKey("encryptedClientHello")) throw new IllegalStateException("Missing required CDP field: encryptedClientHello");
                return new SecurityDetails(values);
            }
        }
    }
    /**
     * Whether the request complied with Certificate Transparency policy.
     */
    public static final class CertificateTransparencyCompliance {
        private CertificateTransparencyCompliance() {}
        public static final String UNKNOWN = "unknown";
        public static final String NOT_COMPLIANT = "not-compliant";
        public static final String COMPLIANT = "compliant";
    }
    /**
     * The reason why request was blocked.
     */
    public static final class BlockedReason {
        private BlockedReason() {}
        public static final String OTHER = "other";
        public static final String CSP = "csp";
        public static final String MIXED_CONTENT = "mixed-content";
        public static final String ORIGIN = "origin";
        public static final String INSPECTOR = "inspector";
        public static final String INTEGRITY = "integrity";
        public static final String SUBRESOURCE_FILTER = "subresource-filter";
        public static final String CONTENT_TYPE = "content-type";
        public static final String COEP_FRAME_RESOURCE_NEEDS_COEP_HEADER = "coep-frame-resource-needs-coep-header";
        public static final String COOP_SANDBOXED_IFRAME_CANNOT_NAVIGATE_TO_COOP_PAGE = "coop-sandboxed-iframe-cannot-navigate-to-coop-page";
        public static final String CORP_NOT_SAME_ORIGIN = "corp-not-same-origin";
        public static final String CORP_NOT_SAME_ORIGIN_AFTER_DEFAULTED_TO_SAME_ORIGIN_BY_COEP = "corp-not-same-origin-after-defaulted-to-same-origin-by-coep";
        public static final String CORP_NOT_SAME_ORIGIN_AFTER_DEFAULTED_TO_SAME_ORIGIN_BY_DIP = "corp-not-same-origin-after-defaulted-to-same-origin-by-dip";
        public static final String CORP_NOT_SAME_ORIGIN_AFTER_DEFAULTED_TO_SAME_ORIGIN_BY_COEP_AND_DIP = "corp-not-same-origin-after-defaulted-to-same-origin-by-coep-and-dip";
        public static final String CORP_NOT_SAME_SITE = "corp-not-same-site";
        public static final String SRI_MESSAGE_SIGNATURE_MISMATCH = "sri-message-signature-mismatch";
    }
    /**
     * The reason why request was blocked.
     */
    public static final class CorsError {
        private CorsError() {}
        public static final String DISALLOWEDBYMODE = "DisallowedByMode";
        public static final String INVALIDRESPONSE = "InvalidResponse";
        public static final String WILDCARDORIGINNOTALLOWED = "WildcardOriginNotAllowed";
        public static final String MISSINGALLOWORIGINHEADER = "MissingAllowOriginHeader";
        public static final String MULTIPLEALLOWORIGINVALUES = "MultipleAllowOriginValues";
        public static final String INVALIDALLOWORIGINVALUE = "InvalidAllowOriginValue";
        public static final String ALLOWORIGINMISMATCH = "AllowOriginMismatch";
        public static final String INVALIDALLOWCREDENTIALS = "InvalidAllowCredentials";
        public static final String CORSDISABLEDSCHEME = "CorsDisabledScheme";
        public static final String PREFLIGHTINVALIDSTATUS = "PreflightInvalidStatus";
        public static final String PREFLIGHTDISALLOWEDREDIRECT = "PreflightDisallowedRedirect";
        public static final String PREFLIGHTWILDCARDORIGINNOTALLOWED = "PreflightWildcardOriginNotAllowed";
        public static final String PREFLIGHTMISSINGALLOWORIGINHEADER = "PreflightMissingAllowOriginHeader";
        public static final String PREFLIGHTMULTIPLEALLOWORIGINVALUES = "PreflightMultipleAllowOriginValues";
        public static final String PREFLIGHTINVALIDALLOWORIGINVALUE = "PreflightInvalidAllowOriginValue";
        public static final String PREFLIGHTALLOWORIGINMISMATCH = "PreflightAllowOriginMismatch";
        public static final String PREFLIGHTINVALIDALLOWCREDENTIALS = "PreflightInvalidAllowCredentials";
        public static final String PREFLIGHTMISSINGALLOWEXTERNAL = "PreflightMissingAllowExternal";
        public static final String PREFLIGHTINVALIDALLOWEXTERNAL = "PreflightInvalidAllowExternal";
        public static final String INVALIDALLOWMETHODSPREFLIGHTRESPONSE = "InvalidAllowMethodsPreflightResponse";
        public static final String INVALIDALLOWHEADERSPREFLIGHTRESPONSE = "InvalidAllowHeadersPreflightResponse";
        public static final String METHODDISALLOWEDBYPREFLIGHTRESPONSE = "MethodDisallowedByPreflightResponse";
        public static final String HEADERDISALLOWEDBYPREFLIGHTRESPONSE = "HeaderDisallowedByPreflightResponse";
        public static final String REDIRECTCONTAINSCREDENTIALS = "RedirectContainsCredentials";
        public static final String INSECURELOCALNETWORK = "InsecureLocalNetwork";
        public static final String INVALIDLOCALNETWORKACCESS = "InvalidLocalNetworkAccess";
        public static final String NOCORSREDIRECTMODENOTFOLLOW = "NoCorsRedirectModeNotFollow";
        public static final String LOCALNETWORKACCESSPERMISSIONDENIED = "LocalNetworkAccessPermissionDenied";
    }
    /**
     */
    public static final class CorsErrorStatus extends CdpObject {
        private CorsErrorStatus(Map<String, Object> values) { super(values); }
        @Nullable public static CorsErrorStatus fromMap(@Nullable Map<String, Object> values) {
            return values == null ? null : new CorsErrorStatus(values);
        }
        public static Builder builder() { return new Builder(); }
        /**
         * Returns the corsError field.
         * @return the protocol field value
         */
        @Nullable public String corsError() {
            return (String) value("corsError");
        }
        /**
         * Returns the failedParameter field.
         * @return the protocol field value
         */
        @Nullable public String failedParameter() {
            return (String) value("failedParameter");
        }
        public static final class Builder {
            private final Map<String, Object> values = new LinkedHashMap<>();
            /**
             * Sets the corsError field.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder corsError(@Nullable String value) {
                if (value == null) values.remove("corsError");
                else values.put("corsError", jsonValue(value));
                return this;
            }
            /**
             * Sets the failedParameter field.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder failedParameter(@Nullable String value) {
                if (value == null) values.remove("failedParameter");
                else values.put("failedParameter", jsonValue(value));
                return this;
            }
            public CorsErrorStatus build() {
                if (!values.containsKey("corsError")) throw new IllegalStateException("Missing required CDP field: corsError");
                if (!values.containsKey("failedParameter")) throw new IllegalStateException("Missing required CDP field: failedParameter");
                return new CorsErrorStatus(values);
            }
        }
    }
    /**
     * Source of serviceworker response.
     */
    public static final class ServiceWorkerResponseSource {
        private ServiceWorkerResponseSource() {}
        public static final String CACHE_STORAGE = "cache-storage";
        public static final String HTTP_CACHE = "http-cache";
        public static final String FALLBACK_CODE = "fallback-code";
        public static final String NETWORK = "network";
    }
    /**
     * Determines what type of Trust Token operation is executed and depending on the type, some additional parameters. The values are specified in third_party/blink/renderer/core/fetch/trust_token.idl.
     * <p><b>Experimental:</b> this part of CDP may change without notice.
     */
    public static final class TrustTokenParams extends CdpObject {
        private TrustTokenParams(Map<String, Object> values) { super(values); }
        @Nullable public static TrustTokenParams fromMap(@Nullable Map<String, Object> values) {
            return values == null ? null : new TrustTokenParams(values);
        }
        public static Builder builder() { return new Builder(); }
        /**
         * Returns the operation field.
         * @return the protocol field value
         */
        @Nullable public String operation() {
            return (String) value("operation");
        }
        /**
         * Only set for &quot;token-redemption&quot; operation and determine whether to request a fresh SRR or use a still valid cached SRR.
         * @return the protocol field value
         */
        @Nullable public String refreshPolicy() {
            return (String) value("refreshPolicy");
        }
        /**
         * Only set for &quot;token-redemption&quot; operation and determine whether to request a fresh SRR or use a still valid cached SRR.
         */
        public static final class RefreshPolicyValues {
            private RefreshPolicyValues() {}
            public static final String USECACHED = "UseCached";
            public static final String REFRESH = "Refresh";
        }
        /**
         * Origins of issuers from whom to request tokens or redemption records.
         * @return the protocol field value
         */
        @Nullable public java.util.List<String> issuers() {
            return list(value("issuers"), element0 -> (String) element0);
        }
        public static final class Builder {
            private final Map<String, Object> values = new LinkedHashMap<>();
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
            /**
             * Only set for &quot;token-redemption&quot; operation and determine whether to request a fresh SRR or use a still valid cached SRR.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder refreshPolicy(@Nullable String value) {
                if (value == null) values.remove("refreshPolicy");
                else values.put("refreshPolicy", jsonValue(value));
                return this;
            }
            /**
             * Origins of issuers from whom to request tokens or redemption records.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder issuers(@Nullable java.util.List<String> value) {
                if (value == null) values.remove("issuers");
                else values.put("issuers", jsonValue(value));
                return this;
            }
            public TrustTokenParams build() {
                if (!values.containsKey("operation")) throw new IllegalStateException("Missing required CDP field: operation");
                if (!values.containsKey("refreshPolicy")) throw new IllegalStateException("Missing required CDP field: refreshPolicy");
                return new TrustTokenParams(values);
            }
        }
    }
    /**
     * Wire values for TrustTokenOperationType.
     * <p><b>Experimental:</b> this part of CDP may change without notice.
     */
    public static final class TrustTokenOperationType {
        private TrustTokenOperationType() {}
        public static final String ISSUANCE = "Issuance";
        public static final String REDEMPTION = "Redemption";
        public static final String SIGNING = "Signing";
    }
    /**
     * The reason why Chrome uses a specific transport protocol for HTTP semantics.
     * <p><b>Experimental:</b> this part of CDP may change without notice.
     */
    public static final class AlternateProtocolUsage {
        private AlternateProtocolUsage() {}
        public static final String ALTERNATIVEJOBWONWITHOUTRACE = "alternativeJobWonWithoutRace";
        public static final String ALTERNATIVEJOBWONRACE = "alternativeJobWonRace";
        public static final String MAINJOBWONRACE = "mainJobWonRace";
        public static final String MAPPINGMISSING = "mappingMissing";
        public static final String BROKEN = "broken";
        public static final String DNSALPNH3JOBWONWITHOUTRACE = "dnsAlpnH3JobWonWithoutRace";
        public static final String DNSALPNH3JOBWONRACE = "dnsAlpnH3JobWonRace";
        public static final String UNSPECIFIEDREASON = "unspecifiedReason";
    }
    /**
     * Source of service worker router.
     */
    public static final class ServiceWorkerRouterSource {
        private ServiceWorkerRouterSource() {}
        public static final String NETWORK = "network";
        public static final String CACHE = "cache";
        public static final String FETCH_EVENT = "fetch-event";
        public static final String RACE_NETWORK_AND_FETCH_HANDLER = "race-network-and-fetch-handler";
        public static final String RACE_NETWORK_AND_CACHE = "race-network-and-cache";
    }
    /**
     * <p><b>Experimental:</b> this part of CDP may change without notice.
     */
    public static final class ServiceWorkerRouterInfo extends CdpObject {
        private ServiceWorkerRouterInfo(Map<String, Object> values) { super(values); }
        @Nullable public static ServiceWorkerRouterInfo fromMap(@Nullable Map<String, Object> values) {
            return values == null ? null : new ServiceWorkerRouterInfo(values);
        }
        public static Builder builder() { return new Builder(); }
        /**
         * ID of the rule matched. If there is a matched rule, this field will be set, otherwiser no value will be set.
         * @return the protocol field value
         */
        @Nullable public Long ruleIdMatched() {
            return numberAsLong(value("ruleIdMatched"));
        }
        /**
         * The router source of the matched rule. If there is a matched rule, this field will be set, otherwise no value will be set.
         * @return the protocol field value
         */
        @Nullable public String matchedSourceType() {
            return (String) value("matchedSourceType");
        }
        /**
         * The actual router source used.
         * @return the protocol field value
         */
        @Nullable public String actualSourceType() {
            return (String) value("actualSourceType");
        }
        public static final class Builder {
            private final Map<String, Object> values = new LinkedHashMap<>();
            /**
             * ID of the rule matched. If there is a matched rule, this field will be set, otherwiser no value will be set.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder ruleIdMatched(@Nullable Long value) {
                if (value == null) values.remove("ruleIdMatched");
                else values.put("ruleIdMatched", jsonValue(value));
                return this;
            }
            /**
             * The router source of the matched rule. If there is a matched rule, this field will be set, otherwise no value will be set.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder matchedSourceType(@Nullable String value) {
                if (value == null) values.remove("matchedSourceType");
                else values.put("matchedSourceType", jsonValue(value));
                return this;
            }
            /**
             * The actual router source used.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder actualSourceType(@Nullable String value) {
                if (value == null) values.remove("actualSourceType");
                else values.put("actualSourceType", jsonValue(value));
                return this;
            }
            public ServiceWorkerRouterInfo build() {
                return new ServiceWorkerRouterInfo(values);
            }
        }
    }
    /**
     * HTTP response data.
     */
    public static final class Response extends CdpObject {
        private Response(Map<String, Object> values) { super(values); }
        @Nullable public static Response fromMap(@Nullable Map<String, Object> values) {
            return values == null ? null : new Response(values);
        }
        public static Builder builder() { return new Builder(); }
        /**
         * Response URL. This URL can be different from CachedResource.url in case of redirect.
         * @return the protocol field value
         */
        @Nullable public String url() {
            return (String) value("url");
        }
        /**
         * HTTP response status code.
         * @return the protocol field value
         */
        @Nullable public Long status() {
            return numberAsLong(value("status"));
        }
        /**
         * HTTP response status text.
         * @return the protocol field value
         */
        @Nullable public String statusText() {
            return (String) value("statusText");
        }
        /**
         * HTTP response headers.
         * @return the protocol field value
         */
        @Nullable public java.util.Map<String, Object> headers() {
            return objectMap(value("headers"));
        }
        /**
         * HTTP response headers text. This has been replaced by the headers in Network.responseReceivedExtraInfo.
         * @return the protocol field value
         * @deprecated Deprecated by the Chromium DevTools Protocol.
         */
        @Deprecated
        @Nullable public String headersText() {
            return (String) value("headersText");
        }
        /**
         * Resource mimeType as determined by the browser.
         * @return the protocol field value
         */
        @Nullable public String mimeType() {
            return (String) value("mimeType");
        }
        /**
         * Resource charset as determined by the browser (if applicable).
         * @return the protocol field value
         */
        @Nullable public String charset() {
            return (String) value("charset");
        }
        /**
         * Refined HTTP request headers that were actually transmitted over the network.
         * @return the protocol field value
         */
        @Nullable public java.util.Map<String, Object> requestHeaders() {
            return objectMap(value("requestHeaders"));
        }
        /**
         * HTTP request headers text. This has been replaced by the headers in Network.requestWillBeSentExtraInfo.
         * @return the protocol field value
         * @deprecated Deprecated by the Chromium DevTools Protocol.
         */
        @Deprecated
        @Nullable public String requestHeadersText() {
            return (String) value("requestHeadersText");
        }
        /**
         * Specifies whether physical connection was actually reused for this request.
         * @return the protocol field value
         */
        @Nullable public Boolean connectionReused() {
            return (Boolean) value("connectionReused");
        }
        /**
         * Physical connection id that was actually used for this request.
         * @return the protocol field value
         */
        @Nullable public Double connectionId() {
            return numberAsDouble(value("connectionId"));
        }
        /**
         * Remote IP address.
         * @return the protocol field value
         */
        @Nullable public String remoteIPAddress() {
            return (String) value("remoteIPAddress");
        }
        /**
         * Remote port.
         * @return the protocol field value
         */
        @Nullable public Long remotePort() {
            return numberAsLong(value("remotePort"));
        }
        /**
         * Specifies that the request was served from the disk cache.
         * @return the protocol field value
         */
        @Nullable public Boolean fromDiskCache() {
            return (Boolean) value("fromDiskCache");
        }
        /**
         * Specifies that the request was served from the ServiceWorker.
         * @return the protocol field value
         */
        @Nullable public Boolean fromServiceWorker() {
            return (Boolean) value("fromServiceWorker");
        }
        /**
         * Specifies that the request was served from the prefetch cache.
         * @return the protocol field value
         */
        @Nullable public Boolean fromPrefetchCache() {
            return (Boolean) value("fromPrefetchCache");
        }
        /**
         * Specifies that the request was served from the prefetch cache.
         * @return the protocol field value
         */
        @Nullable public Boolean fromEarlyHints() {
            return (Boolean) value("fromEarlyHints");
        }
        /**
         * Information about how ServiceWorker Static Router API was used. If this field is set with {@code matchedSourceType} field, a matching rule is found. If this field is set without {@code matchedSource}, no matching rule is found. Otherwise, the API is not used.
         * <p><b>Experimental:</b> this part of CDP may change without notice.
         * @return the protocol field value
         */
        @Nullable public Network.ServiceWorkerRouterInfo serviceWorkerRouterInfo() {
            return Network.ServiceWorkerRouterInfo.fromMap(objectMap(value("serviceWorkerRouterInfo")));
        }
        /**
         * Total number of bytes received for this request so far.
         * @return the protocol field value
         */
        @Nullable public Double encodedDataLength() {
            return numberAsDouble(value("encodedDataLength"));
        }
        /**
         * Timing information for the given request.
         * @return the protocol field value
         */
        @Nullable public Network.ResourceTiming timing() {
            return Network.ResourceTiming.fromMap(objectMap(value("timing")));
        }
        /**
         * Response source of response from ServiceWorker.
         * @return the protocol field value
         */
        @Nullable public String serviceWorkerResponseSource() {
            return (String) value("serviceWorkerResponseSource");
        }
        /**
         * The time at which the returned response was generated.
         * @return the protocol field value
         */
        @Nullable public Double responseTime() {
            return numberAsDouble(value("responseTime"));
        }
        /**
         * Cache Storage Cache Name.
         * @return the protocol field value
         */
        @Nullable public String cacheStorageCacheName() {
            return (String) value("cacheStorageCacheName");
        }
        /**
         * Protocol used to fetch this request.
         * @return the protocol field value
         */
        @Nullable public String protocol() {
            return (String) value("protocol");
        }
        /**
         * The reason why Chrome uses a specific transport protocol for HTTP semantics.
         * <p><b>Experimental:</b> this part of CDP may change without notice.
         * @return the protocol field value
         */
        @Nullable public String alternateProtocolUsage() {
            return (String) value("alternateProtocolUsage");
        }
        /**
         * Security state of the request resource.
         * @return the protocol field value
         */
        @Nullable public String securityState() {
            return (String) value("securityState");
        }
        /**
         * Security details for the request.
         * @return the protocol field value
         */
        @Nullable public Network.SecurityDetails securityDetails() {
            return Network.SecurityDetails.fromMap(objectMap(value("securityDetails")));
        }
        public static final class Builder {
            private final Map<String, Object> values = new LinkedHashMap<>();
            /**
             * Response URL. This URL can be different from CachedResource.url in case of redirect.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder url(@Nullable String value) {
                if (value == null) values.remove("url");
                else values.put("url", jsonValue(value));
                return this;
            }
            /**
             * HTTP response status code.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder status(@Nullable Long value) {
                if (value == null) values.remove("status");
                else values.put("status", jsonValue(value));
                return this;
            }
            /**
             * HTTP response status text.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder statusText(@Nullable String value) {
                if (value == null) values.remove("statusText");
                else values.put("statusText", jsonValue(value));
                return this;
            }
            /**
             * HTTP response headers.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder headers(@Nullable java.util.Map<String, Object> value) {
                if (value == null) values.remove("headers");
                else values.put("headers", jsonValue(value));
                return this;
            }
            /**
             * HTTP response headers text. This has been replaced by the headers in Network.responseReceivedExtraInfo.
             * @param value field value; null removes an optional value
             * @return this builder
             * @deprecated Deprecated by the Chromium DevTools Protocol.
             */
            @Deprecated
            public Builder headersText(@Nullable String value) {
                if (value == null) values.remove("headersText");
                else values.put("headersText", jsonValue(value));
                return this;
            }
            /**
             * Resource mimeType as determined by the browser.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder mimeType(@Nullable String value) {
                if (value == null) values.remove("mimeType");
                else values.put("mimeType", jsonValue(value));
                return this;
            }
            /**
             * Resource charset as determined by the browser (if applicable).
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder charset(@Nullable String value) {
                if (value == null) values.remove("charset");
                else values.put("charset", jsonValue(value));
                return this;
            }
            /**
             * Refined HTTP request headers that were actually transmitted over the network.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder requestHeaders(@Nullable java.util.Map<String, Object> value) {
                if (value == null) values.remove("requestHeaders");
                else values.put("requestHeaders", jsonValue(value));
                return this;
            }
            /**
             * HTTP request headers text. This has been replaced by the headers in Network.requestWillBeSentExtraInfo.
             * @param value field value; null removes an optional value
             * @return this builder
             * @deprecated Deprecated by the Chromium DevTools Protocol.
             */
            @Deprecated
            public Builder requestHeadersText(@Nullable String value) {
                if (value == null) values.remove("requestHeadersText");
                else values.put("requestHeadersText", jsonValue(value));
                return this;
            }
            /**
             * Specifies whether physical connection was actually reused for this request.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder connectionReused(@Nullable Boolean value) {
                if (value == null) values.remove("connectionReused");
                else values.put("connectionReused", jsonValue(value));
                return this;
            }
            /**
             * Physical connection id that was actually used for this request.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder connectionId(@Nullable Double value) {
                if (value == null) values.remove("connectionId");
                else values.put("connectionId", jsonValue(value));
                return this;
            }
            /**
             * Remote IP address.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder remoteIPAddress(@Nullable String value) {
                if (value == null) values.remove("remoteIPAddress");
                else values.put("remoteIPAddress", jsonValue(value));
                return this;
            }
            /**
             * Remote port.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder remotePort(@Nullable Long value) {
                if (value == null) values.remove("remotePort");
                else values.put("remotePort", jsonValue(value));
                return this;
            }
            /**
             * Specifies that the request was served from the disk cache.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder fromDiskCache(@Nullable Boolean value) {
                if (value == null) values.remove("fromDiskCache");
                else values.put("fromDiskCache", jsonValue(value));
                return this;
            }
            /**
             * Specifies that the request was served from the ServiceWorker.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder fromServiceWorker(@Nullable Boolean value) {
                if (value == null) values.remove("fromServiceWorker");
                else values.put("fromServiceWorker", jsonValue(value));
                return this;
            }
            /**
             * Specifies that the request was served from the prefetch cache.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder fromPrefetchCache(@Nullable Boolean value) {
                if (value == null) values.remove("fromPrefetchCache");
                else values.put("fromPrefetchCache", jsonValue(value));
                return this;
            }
            /**
             * Specifies that the request was served from the prefetch cache.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder fromEarlyHints(@Nullable Boolean value) {
                if (value == null) values.remove("fromEarlyHints");
                else values.put("fromEarlyHints", jsonValue(value));
                return this;
            }
            /**
             * Information about how ServiceWorker Static Router API was used. If this field is set with {@code matchedSourceType} field, a matching rule is found. If this field is set without {@code matchedSource}, no matching rule is found. Otherwise, the API is not used.
             * <p><b>Experimental:</b> this part of CDP may change without notice.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder serviceWorkerRouterInfo(@Nullable Network.ServiceWorkerRouterInfo value) {
                if (value == null) values.remove("serviceWorkerRouterInfo");
                else values.put("serviceWorkerRouterInfo", jsonValue(value));
                return this;
            }
            /**
             * Total number of bytes received for this request so far.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder encodedDataLength(@Nullable Double value) {
                if (value == null) values.remove("encodedDataLength");
                else values.put("encodedDataLength", jsonValue(value));
                return this;
            }
            /**
             * Timing information for the given request.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder timing(@Nullable Network.ResourceTiming value) {
                if (value == null) values.remove("timing");
                else values.put("timing", jsonValue(value));
                return this;
            }
            /**
             * Response source of response from ServiceWorker.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder serviceWorkerResponseSource(@Nullable String value) {
                if (value == null) values.remove("serviceWorkerResponseSource");
                else values.put("serviceWorkerResponseSource", jsonValue(value));
                return this;
            }
            /**
             * The time at which the returned response was generated.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder responseTime(@Nullable Double value) {
                if (value == null) values.remove("responseTime");
                else values.put("responseTime", jsonValue(value));
                return this;
            }
            /**
             * Cache Storage Cache Name.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder cacheStorageCacheName(@Nullable String value) {
                if (value == null) values.remove("cacheStorageCacheName");
                else values.put("cacheStorageCacheName", jsonValue(value));
                return this;
            }
            /**
             * Protocol used to fetch this request.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder protocol(@Nullable String value) {
                if (value == null) values.remove("protocol");
                else values.put("protocol", jsonValue(value));
                return this;
            }
            /**
             * The reason why Chrome uses a specific transport protocol for HTTP semantics.
             * <p><b>Experimental:</b> this part of CDP may change without notice.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder alternateProtocolUsage(@Nullable String value) {
                if (value == null) values.remove("alternateProtocolUsage");
                else values.put("alternateProtocolUsage", jsonValue(value));
                return this;
            }
            /**
             * Security state of the request resource.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder securityState(@Nullable String value) {
                if (value == null) values.remove("securityState");
                else values.put("securityState", jsonValue(value));
                return this;
            }
            /**
             * Security details for the request.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder securityDetails(@Nullable Network.SecurityDetails value) {
                if (value == null) values.remove("securityDetails");
                else values.put("securityDetails", jsonValue(value));
                return this;
            }
            public Response build() {
                if (!values.containsKey("url")) throw new IllegalStateException("Missing required CDP field: url");
                if (!values.containsKey("status")) throw new IllegalStateException("Missing required CDP field: status");
                if (!values.containsKey("statusText")) throw new IllegalStateException("Missing required CDP field: statusText");
                if (!values.containsKey("headers")) throw new IllegalStateException("Missing required CDP field: headers");
                if (!values.containsKey("mimeType")) throw new IllegalStateException("Missing required CDP field: mimeType");
                if (!values.containsKey("charset")) throw new IllegalStateException("Missing required CDP field: charset");
                if (!values.containsKey("connectionReused")) throw new IllegalStateException("Missing required CDP field: connectionReused");
                if (!values.containsKey("connectionId")) throw new IllegalStateException("Missing required CDP field: connectionId");
                if (!values.containsKey("encodedDataLength")) throw new IllegalStateException("Missing required CDP field: encodedDataLength");
                if (!values.containsKey("securityState")) throw new IllegalStateException("Missing required CDP field: securityState");
                return new Response(values);
            }
        }
    }
    /**
     * WebSocket request data.
     */
    public static final class WebSocketRequest extends CdpObject {
        private WebSocketRequest(Map<String, Object> values) { super(values); }
        @Nullable public static WebSocketRequest fromMap(@Nullable Map<String, Object> values) {
            return values == null ? null : new WebSocketRequest(values);
        }
        public static Builder builder() { return new Builder(); }
        /**
         * HTTP request headers.
         * @return the protocol field value
         */
        @Nullable public java.util.Map<String, Object> headers() {
            return objectMap(value("headers"));
        }
        public static final class Builder {
            private final Map<String, Object> values = new LinkedHashMap<>();
            /**
             * HTTP request headers.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder headers(@Nullable java.util.Map<String, Object> value) {
                if (value == null) values.remove("headers");
                else values.put("headers", jsonValue(value));
                return this;
            }
            public WebSocketRequest build() {
                if (!values.containsKey("headers")) throw new IllegalStateException("Missing required CDP field: headers");
                return new WebSocketRequest(values);
            }
        }
    }
    /**
     * WebSocket response data.
     */
    public static final class WebSocketResponse extends CdpObject {
        private WebSocketResponse(Map<String, Object> values) { super(values); }
        @Nullable public static WebSocketResponse fromMap(@Nullable Map<String, Object> values) {
            return values == null ? null : new WebSocketResponse(values);
        }
        public static Builder builder() { return new Builder(); }
        /**
         * HTTP response status code.
         * @return the protocol field value
         */
        @Nullable public Long status() {
            return numberAsLong(value("status"));
        }
        /**
         * HTTP response status text.
         * @return the protocol field value
         */
        @Nullable public String statusText() {
            return (String) value("statusText");
        }
        /**
         * HTTP response headers.
         * @return the protocol field value
         */
        @Nullable public java.util.Map<String, Object> headers() {
            return objectMap(value("headers"));
        }
        /**
         * HTTP response headers text.
         * @return the protocol field value
         */
        @Nullable public String headersText() {
            return (String) value("headersText");
        }
        /**
         * HTTP request headers.
         * @return the protocol field value
         */
        @Nullable public java.util.Map<String, Object> requestHeaders() {
            return objectMap(value("requestHeaders"));
        }
        /**
         * HTTP request headers text.
         * @return the protocol field value
         */
        @Nullable public String requestHeadersText() {
            return (String) value("requestHeadersText");
        }
        public static final class Builder {
            private final Map<String, Object> values = new LinkedHashMap<>();
            /**
             * HTTP response status code.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder status(@Nullable Long value) {
                if (value == null) values.remove("status");
                else values.put("status", jsonValue(value));
                return this;
            }
            /**
             * HTTP response status text.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder statusText(@Nullable String value) {
                if (value == null) values.remove("statusText");
                else values.put("statusText", jsonValue(value));
                return this;
            }
            /**
             * HTTP response headers.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder headers(@Nullable java.util.Map<String, Object> value) {
                if (value == null) values.remove("headers");
                else values.put("headers", jsonValue(value));
                return this;
            }
            /**
             * HTTP response headers text.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder headersText(@Nullable String value) {
                if (value == null) values.remove("headersText");
                else values.put("headersText", jsonValue(value));
                return this;
            }
            /**
             * HTTP request headers.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder requestHeaders(@Nullable java.util.Map<String, Object> value) {
                if (value == null) values.remove("requestHeaders");
                else values.put("requestHeaders", jsonValue(value));
                return this;
            }
            /**
             * HTTP request headers text.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder requestHeadersText(@Nullable String value) {
                if (value == null) values.remove("requestHeadersText");
                else values.put("requestHeadersText", jsonValue(value));
                return this;
            }
            public WebSocketResponse build() {
                if (!values.containsKey("status")) throw new IllegalStateException("Missing required CDP field: status");
                if (!values.containsKey("statusText")) throw new IllegalStateException("Missing required CDP field: statusText");
                if (!values.containsKey("headers")) throw new IllegalStateException("Missing required CDP field: headers");
                return new WebSocketResponse(values);
            }
        }
    }
    /**
     * WebSocket message data. This represents an entire WebSocket message, not just a fragmented frame as the name suggests.
     */
    public static final class WebSocketFrame extends CdpObject {
        private WebSocketFrame(Map<String, Object> values) { super(values); }
        @Nullable public static WebSocketFrame fromMap(@Nullable Map<String, Object> values) {
            return values == null ? null : new WebSocketFrame(values);
        }
        public static Builder builder() { return new Builder(); }
        /**
         * WebSocket message opcode.
         * @return the protocol field value
         */
        @Nullable public Double opcode() {
            return numberAsDouble(value("opcode"));
        }
        /**
         * WebSocket message mask.
         * @return the protocol field value
         */
        @Nullable public Boolean mask() {
            return (Boolean) value("mask");
        }
        /**
         * WebSocket message payload data. If the opcode is 1, this is a text message and payloadData is a UTF-8 string. If the opcode isn&#x27;t 1, then payloadData is a base64 encoded string representing binary data.
         * @return the protocol field value
         */
        @Nullable public String payloadData() {
            return (String) value("payloadData");
        }
        public static final class Builder {
            private final Map<String, Object> values = new LinkedHashMap<>();
            /**
             * WebSocket message opcode.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder opcode(@Nullable Double value) {
                if (value == null) values.remove("opcode");
                else values.put("opcode", jsonValue(value));
                return this;
            }
            /**
             * WebSocket message mask.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder mask(@Nullable Boolean value) {
                if (value == null) values.remove("mask");
                else values.put("mask", jsonValue(value));
                return this;
            }
            /**
             * WebSocket message payload data. If the opcode is 1, this is a text message and payloadData is a UTF-8 string. If the opcode isn&#x27;t 1, then payloadData is a base64 encoded string representing binary data.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder payloadData(@Nullable String value) {
                if (value == null) values.remove("payloadData");
                else values.put("payloadData", jsonValue(value));
                return this;
            }
            public WebSocketFrame build() {
                if (!values.containsKey("opcode")) throw new IllegalStateException("Missing required CDP field: opcode");
                if (!values.containsKey("mask")) throw new IllegalStateException("Missing required CDP field: mask");
                if (!values.containsKey("payloadData")) throw new IllegalStateException("Missing required CDP field: payloadData");
                return new WebSocketFrame(values);
            }
        }
    }
    /**
     * Information about the cached resource.
     */
    public static final class CachedResource extends CdpObject {
        private CachedResource(Map<String, Object> values) { super(values); }
        @Nullable public static CachedResource fromMap(@Nullable Map<String, Object> values) {
            return values == null ? null : new CachedResource(values);
        }
        public static Builder builder() { return new Builder(); }
        /**
         * Resource URL. This is the url of the original network request.
         * @return the protocol field value
         */
        @Nullable public String url() {
            return (String) value("url");
        }
        /**
         * Type of this resource.
         * @return the protocol field value
         */
        @Nullable public String type() {
            return (String) value("type");
        }
        /**
         * Cached response data.
         * @return the protocol field value
         */
        @Nullable public Network.Response response() {
            return Network.Response.fromMap(objectMap(value("response")));
        }
        /**
         * Cached response body size.
         * @return the protocol field value
         */
        @Nullable public Double bodySize() {
            return numberAsDouble(value("bodySize"));
        }
        public static final class Builder {
            private final Map<String, Object> values = new LinkedHashMap<>();
            /**
             * Resource URL. This is the url of the original network request.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder url(@Nullable String value) {
                if (value == null) values.remove("url");
                else values.put("url", jsonValue(value));
                return this;
            }
            /**
             * Type of this resource.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder type(@Nullable String value) {
                if (value == null) values.remove("type");
                else values.put("type", jsonValue(value));
                return this;
            }
            /**
             * Cached response data.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder response(@Nullable Network.Response value) {
                if (value == null) values.remove("response");
                else values.put("response", jsonValue(value));
                return this;
            }
            /**
             * Cached response body size.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder bodySize(@Nullable Double value) {
                if (value == null) values.remove("bodySize");
                else values.put("bodySize", jsonValue(value));
                return this;
            }
            public CachedResource build() {
                if (!values.containsKey("url")) throw new IllegalStateException("Missing required CDP field: url");
                if (!values.containsKey("type")) throw new IllegalStateException("Missing required CDP field: type");
                if (!values.containsKey("bodySize")) throw new IllegalStateException("Missing required CDP field: bodySize");
                return new CachedResource(values);
            }
        }
    }
    /**
     * Information about the request initiator.
     */
    public static final class Initiator extends CdpObject {
        private Initiator(Map<String, Object> values) { super(values); }
        @Nullable public static Initiator fromMap(@Nullable Map<String, Object> values) {
            return values == null ? null : new Initiator(values);
        }
        public static Builder builder() { return new Builder(); }
        /**
         * Type of this initiator.
         * @return the protocol field value
         */
        @Nullable public String type() {
            return (String) value("type");
        }
        /**
         * Type of this initiator.
         */
        public static final class TypeValues {
            private TypeValues() {}
            public static final String PARSER = "parser";
            public static final String SCRIPT = "script";
            public static final String PRELOAD = "preload";
            public static final String SIGNEDEXCHANGE = "SignedExchange";
            public static final String PREFLIGHT = "preflight";
            public static final String FEDCM = "FedCM";
            public static final String OTHER = "other";
        }
        /**
         * Initiator JavaScript stack trace, set for Script only. Requires the Debugger domain to be enabled.
         * @return the protocol field value
         */
        @Nullable public Runtime.StackTrace stack() {
            return Runtime.StackTrace.fromMap(objectMap(value("stack")));
        }
        /**
         * Initiator URL, set for Parser type or for Script type (when script is importing module) or for SignedExchange type.
         * @return the protocol field value
         */
        @Nullable public String url() {
            return (String) value("url");
        }
        /**
         * Initiator line number, set for Parser type or for Script type (when script is importing module) (0-based).
         * @return the protocol field value
         */
        @Nullable public Double lineNumber() {
            return numberAsDouble(value("lineNumber"));
        }
        /**
         * Initiator column number, set for Parser type or for Script type (when script is importing module) (0-based).
         * @return the protocol field value
         */
        @Nullable public Double columnNumber() {
            return numberAsDouble(value("columnNumber"));
        }
        /**
         * Set if another request triggered this request (e.g. preflight).
         * @return the protocol field value
         */
        @Nullable public String requestId() {
            return (String) value("requestId");
        }
        public static final class Builder {
            private final Map<String, Object> values = new LinkedHashMap<>();
            /**
             * Type of this initiator.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder type(@Nullable String value) {
                if (value == null) values.remove("type");
                else values.put("type", jsonValue(value));
                return this;
            }
            /**
             * Initiator JavaScript stack trace, set for Script only. Requires the Debugger domain to be enabled.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder stack(@Nullable Runtime.StackTrace value) {
                if (value == null) values.remove("stack");
                else values.put("stack", jsonValue(value));
                return this;
            }
            /**
             * Initiator URL, set for Parser type or for Script type (when script is importing module) or for SignedExchange type.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder url(@Nullable String value) {
                if (value == null) values.remove("url");
                else values.put("url", jsonValue(value));
                return this;
            }
            /**
             * Initiator line number, set for Parser type or for Script type (when script is importing module) (0-based).
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder lineNumber(@Nullable Double value) {
                if (value == null) values.remove("lineNumber");
                else values.put("lineNumber", jsonValue(value));
                return this;
            }
            /**
             * Initiator column number, set for Parser type or for Script type (when script is importing module) (0-based).
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder columnNumber(@Nullable Double value) {
                if (value == null) values.remove("columnNumber");
                else values.put("columnNumber", jsonValue(value));
                return this;
            }
            /**
             * Set if another request triggered this request (e.g. preflight).
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder requestId(@Nullable String value) {
                if (value == null) values.remove("requestId");
                else values.put("requestId", jsonValue(value));
                return this;
            }
            public Initiator build() {
                if (!values.containsKey("type")) throw new IllegalStateException("Missing required CDP field: type");
                return new Initiator(values);
            }
        }
    }
    /**
     * cookiePartitionKey object The representation of the components of the key that are created by the cookiePartitionKey class contained in net/cookies/cookie_partition_key.h.
     * <p><b>Experimental:</b> this part of CDP may change without notice.
     */
    public static final class CookiePartitionKey extends CdpObject {
        private CookiePartitionKey(Map<String, Object> values) { super(values); }
        @Nullable public static CookiePartitionKey fromMap(@Nullable Map<String, Object> values) {
            return values == null ? null : new CookiePartitionKey(values);
        }
        public static Builder builder() { return new Builder(); }
        /**
         * The site of the top-level URL the browser was visiting at the start of the request to the endpoint that set the cookie.
         * @return the protocol field value
         */
        @Nullable public String topLevelSite() {
            return (String) value("topLevelSite");
        }
        /**
         * Indicates if the cookie has any ancestors that are cross-site to the topLevelSite.
         * @return the protocol field value
         */
        @Nullable public Boolean hasCrossSiteAncestor() {
            return (Boolean) value("hasCrossSiteAncestor");
        }
        public static final class Builder {
            private final Map<String, Object> values = new LinkedHashMap<>();
            /**
             * The site of the top-level URL the browser was visiting at the start of the request to the endpoint that set the cookie.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder topLevelSite(@Nullable String value) {
                if (value == null) values.remove("topLevelSite");
                else values.put("topLevelSite", jsonValue(value));
                return this;
            }
            /**
             * Indicates if the cookie has any ancestors that are cross-site to the topLevelSite.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder hasCrossSiteAncestor(@Nullable Boolean value) {
                if (value == null) values.remove("hasCrossSiteAncestor");
                else values.put("hasCrossSiteAncestor", jsonValue(value));
                return this;
            }
            public CookiePartitionKey build() {
                if (!values.containsKey("topLevelSite")) throw new IllegalStateException("Missing required CDP field: topLevelSite");
                if (!values.containsKey("hasCrossSiteAncestor")) throw new IllegalStateException("Missing required CDP field: hasCrossSiteAncestor");
                return new CookiePartitionKey(values);
            }
        }
    }
    /**
     * Cookie object
     */
    public static final class Cookie extends CdpObject {
        private Cookie(Map<String, Object> values) { super(values); }
        @Nullable public static Cookie fromMap(@Nullable Map<String, Object> values) {
            return values == null ? null : new Cookie(values);
        }
        public static Builder builder() { return new Builder(); }
        /**
         * Cookie name.
         * @return the protocol field value
         */
        @Nullable public String name() {
            return (String) value("name");
        }
        /**
         * Cookie value.
         * @return the protocol field value
         */
        @Nullable public String value() {
            return (String) value("value");
        }
        /**
         * Cookie domain.
         * @return the protocol field value
         */
        @Nullable public String domain() {
            return (String) value("domain");
        }
        /**
         * Cookie path.
         * @return the protocol field value
         */
        @Nullable public String path() {
            return (String) value("path");
        }
        /**
         * Cookie expiration date as the number of seconds since the UNIX epoch. The value is set to -1 if the expiry date is not set. The value can be null for values that cannot be represented in JSON (±Inf).
         * @return the protocol field value
         */
        @Nullable public Double expires() {
            return numberAsDouble(value("expires"));
        }
        /**
         * Cookie size.
         * @return the protocol field value
         */
        @Nullable public Long size() {
            return numberAsLong(value("size"));
        }
        /**
         * True if cookie is http-only.
         * @return the protocol field value
         */
        @Nullable public Boolean httpOnly() {
            return (Boolean) value("httpOnly");
        }
        /**
         * True if cookie is secure.
         * @return the protocol field value
         */
        @Nullable public Boolean secure() {
            return (Boolean) value("secure");
        }
        /**
         * True in case of session cookie.
         * @return the protocol field value
         */
        @Nullable public Boolean session() {
            return (Boolean) value("session");
        }
        /**
         * Cookie SameSite type.
         * @return the protocol field value
         */
        @Nullable public String sameSite() {
            return (String) value("sameSite");
        }
        /**
         * Cookie Priority
         * <p><b>Experimental:</b> this part of CDP may change without notice.
         * @return the protocol field value
         */
        @Nullable public String priority() {
            return (String) value("priority");
        }
        /**
         * Cookie source scheme type.
         * <p><b>Experimental:</b> this part of CDP may change without notice.
         * @return the protocol field value
         */
        @Nullable public String sourceScheme() {
            return (String) value("sourceScheme");
        }
        /**
         * Cookie source port. Valid values are {-1, [1, 65535]}, -1 indicates an unspecified port. An unspecified port value allows protocol clients to emulate legacy cookie scope for the port. This is a temporary ability and it will be removed in the future.
         * <p><b>Experimental:</b> this part of CDP may change without notice.
         * @return the protocol field value
         */
        @Nullable public Long sourcePort() {
            return numberAsLong(value("sourcePort"));
        }
        /**
         * Cookie partition key.
         * <p><b>Experimental:</b> this part of CDP may change without notice.
         * @return the protocol field value
         */
        @Nullable public Network.CookiePartitionKey partitionKey() {
            return Network.CookiePartitionKey.fromMap(objectMap(value("partitionKey")));
        }
        /**
         * True if cookie partition key is opaque.
         * <p><b>Experimental:</b> this part of CDP may change without notice.
         * @return the protocol field value
         */
        @Nullable public Boolean partitionKeyOpaque() {
            return (Boolean) value("partitionKeyOpaque");
        }
        public static final class Builder {
            private final Map<String, Object> values = new LinkedHashMap<>();
            /**
             * Cookie name.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder name(@Nullable String value) {
                if (value == null) values.remove("name");
                else values.put("name", jsonValue(value));
                return this;
            }
            /**
             * Cookie value.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder value(@Nullable String value) {
                if (value == null) values.remove("value");
                else values.put("value", jsonValue(value));
                return this;
            }
            /**
             * Cookie domain.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder domain(@Nullable String value) {
                if (value == null) values.remove("domain");
                else values.put("domain", jsonValue(value));
                return this;
            }
            /**
             * Cookie path.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder path(@Nullable String value) {
                if (value == null) values.remove("path");
                else values.put("path", jsonValue(value));
                return this;
            }
            /**
             * Cookie expiration date as the number of seconds since the UNIX epoch. The value is set to -1 if the expiry date is not set. The value can be null for values that cannot be represented in JSON (±Inf).
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder expires(@Nullable Double value) {
                if (value == null) values.remove("expires");
                else values.put("expires", jsonValue(value));
                return this;
            }
            /**
             * Cookie size.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder size(@Nullable Long value) {
                if (value == null) values.remove("size");
                else values.put("size", jsonValue(value));
                return this;
            }
            /**
             * True if cookie is http-only.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder httpOnly(@Nullable Boolean value) {
                if (value == null) values.remove("httpOnly");
                else values.put("httpOnly", jsonValue(value));
                return this;
            }
            /**
             * True if cookie is secure.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder secure(@Nullable Boolean value) {
                if (value == null) values.remove("secure");
                else values.put("secure", jsonValue(value));
                return this;
            }
            /**
             * True in case of session cookie.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder session(@Nullable Boolean value) {
                if (value == null) values.remove("session");
                else values.put("session", jsonValue(value));
                return this;
            }
            /**
             * Cookie SameSite type.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder sameSite(@Nullable String value) {
                if (value == null) values.remove("sameSite");
                else values.put("sameSite", jsonValue(value));
                return this;
            }
            /**
             * Cookie Priority
             * <p><b>Experimental:</b> this part of CDP may change without notice.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder priority(@Nullable String value) {
                if (value == null) values.remove("priority");
                else values.put("priority", jsonValue(value));
                return this;
            }
            /**
             * Cookie source scheme type.
             * <p><b>Experimental:</b> this part of CDP may change without notice.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder sourceScheme(@Nullable String value) {
                if (value == null) values.remove("sourceScheme");
                else values.put("sourceScheme", jsonValue(value));
                return this;
            }
            /**
             * Cookie source port. Valid values are {-1, [1, 65535]}, -1 indicates an unspecified port. An unspecified port value allows protocol clients to emulate legacy cookie scope for the port. This is a temporary ability and it will be removed in the future.
             * <p><b>Experimental:</b> this part of CDP may change without notice.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder sourcePort(@Nullable Long value) {
                if (value == null) values.remove("sourcePort");
                else values.put("sourcePort", jsonValue(value));
                return this;
            }
            /**
             * Cookie partition key.
             * <p><b>Experimental:</b> this part of CDP may change without notice.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder partitionKey(@Nullable Network.CookiePartitionKey value) {
                if (value == null) values.remove("partitionKey");
                else values.put("partitionKey", jsonValue(value));
                return this;
            }
            /**
             * True if cookie partition key is opaque.
             * <p><b>Experimental:</b> this part of CDP may change without notice.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder partitionKeyOpaque(@Nullable Boolean value) {
                if (value == null) values.remove("partitionKeyOpaque");
                else values.put("partitionKeyOpaque", jsonValue(value));
                return this;
            }
            public Cookie build() {
                if (!values.containsKey("name")) throw new IllegalStateException("Missing required CDP field: name");
                if (!values.containsKey("value")) throw new IllegalStateException("Missing required CDP field: value");
                if (!values.containsKey("domain")) throw new IllegalStateException("Missing required CDP field: domain");
                if (!values.containsKey("path")) throw new IllegalStateException("Missing required CDP field: path");
                if (!values.containsKey("expires")) throw new IllegalStateException("Missing required CDP field: expires");
                if (!values.containsKey("size")) throw new IllegalStateException("Missing required CDP field: size");
                if (!values.containsKey("httpOnly")) throw new IllegalStateException("Missing required CDP field: httpOnly");
                if (!values.containsKey("secure")) throw new IllegalStateException("Missing required CDP field: secure");
                if (!values.containsKey("session")) throw new IllegalStateException("Missing required CDP field: session");
                if (!values.containsKey("priority")) throw new IllegalStateException("Missing required CDP field: priority");
                if (!values.containsKey("sourceScheme")) throw new IllegalStateException("Missing required CDP field: sourceScheme");
                if (!values.containsKey("sourcePort")) throw new IllegalStateException("Missing required CDP field: sourcePort");
                return new Cookie(values);
            }
        }
    }
    /**
     * Types of reasons why a cookie may not be stored from a response.
     * <p><b>Experimental:</b> this part of CDP may change without notice.
     */
    public static final class SetCookieBlockedReason {
        private SetCookieBlockedReason() {}
        public static final String SECUREONLY = "SecureOnly";
        public static final String SAMESITESTRICT = "SameSiteStrict";
        public static final String SAMESITELAX = "SameSiteLax";
        public static final String SAMESITEUNSPECIFIEDTREATEDASLAX = "SameSiteUnspecifiedTreatedAsLax";
        public static final String SAMESITENONEINSECURE = "SameSiteNoneInsecure";
        public static final String USERPREFERENCES = "UserPreferences";
        public static final String THIRDPARTYPHASEOUT = "ThirdPartyPhaseout";
        public static final String THIRDPARTYBLOCKEDINFIRSTPARTYSET = "ThirdPartyBlockedInFirstPartySet";
        public static final String SYNTAXERROR = "SyntaxError";
        public static final String SCHEMENOTSUPPORTED = "SchemeNotSupported";
        public static final String OVERWRITESECURE = "OverwriteSecure";
        public static final String INVALIDDOMAIN = "InvalidDomain";
        public static final String INVALIDPREFIX = "InvalidPrefix";
        public static final String UNKNOWNERROR = "UnknownError";
        public static final String SCHEMEFULSAMESITESTRICT = "SchemefulSameSiteStrict";
        public static final String SCHEMEFULSAMESITELAX = "SchemefulSameSiteLax";
        public static final String SCHEMEFULSAMESITEUNSPECIFIEDTREATEDASLAX = "SchemefulSameSiteUnspecifiedTreatedAsLax";
        public static final String NAMEVALUEPAIREXCEEDSMAXSIZE = "NameValuePairExceedsMaxSize";
        public static final String DISALLOWEDCHARACTER = "DisallowedCharacter";
        public static final String NOCOOKIECONTENT = "NoCookieContent";
    }
    /**
     * Types of reasons why a cookie may not be sent with a request.
     * <p><b>Experimental:</b> this part of CDP may change without notice.
     */
    public static final class CookieBlockedReason {
        private CookieBlockedReason() {}
        public static final String SECUREONLY = "SecureOnly";
        public static final String NOTONPATH = "NotOnPath";
        public static final String DOMAINMISMATCH = "DomainMismatch";
        public static final String SAMESITESTRICT = "SameSiteStrict";
        public static final String SAMESITELAX = "SameSiteLax";
        public static final String SAMESITEUNSPECIFIEDTREATEDASLAX = "SameSiteUnspecifiedTreatedAsLax";
        public static final String SAMESITENONEINSECURE = "SameSiteNoneInsecure";
        public static final String USERPREFERENCES = "UserPreferences";
        public static final String THIRDPARTYPHASEOUT = "ThirdPartyPhaseout";
        public static final String THIRDPARTYBLOCKEDINFIRSTPARTYSET = "ThirdPartyBlockedInFirstPartySet";
        public static final String UNKNOWNERROR = "UnknownError";
        public static final String SCHEMEFULSAMESITESTRICT = "SchemefulSameSiteStrict";
        public static final String SCHEMEFULSAMESITELAX = "SchemefulSameSiteLax";
        public static final String SCHEMEFULSAMESITEUNSPECIFIEDTREATEDASLAX = "SchemefulSameSiteUnspecifiedTreatedAsLax";
        public static final String NAMEVALUEPAIREXCEEDSMAXSIZE = "NameValuePairExceedsMaxSize";
        public static final String PORTMISMATCH = "PortMismatch";
        public static final String SCHEMEMISMATCH = "SchemeMismatch";
        public static final String ANONYMOUSCONTEXT = "AnonymousContext";
    }
    /**
     * Types of reasons why a cookie should have been blocked by 3PCD but is exempted for the request.
     * <p><b>Experimental:</b> this part of CDP may change without notice.
     */
    public static final class CookieExemptionReason {
        private CookieExemptionReason() {}
        public static final String NONE = "None";
        public static final String USERSETTING = "UserSetting";
        public static final String TPCDMETADATA = "TPCDMetadata";
        public static final String TPCDDEPRECATIONTRIAL = "TPCDDeprecationTrial";
        public static final String TOPLEVELTPCDDEPRECATIONTRIAL = "TopLevelTPCDDeprecationTrial";
        public static final String TPCDHEURISTICS = "TPCDHeuristics";
        public static final String ENTERPRISEPOLICY = "EnterprisePolicy";
        public static final String STORAGEACCESS = "StorageAccess";
        public static final String TOPLEVELSTORAGEACCESS = "TopLevelStorageAccess";
        public static final String SCHEME = "Scheme";
        public static final String SAMESITENONECOOKIESINSANDBOX = "SameSiteNoneCookiesInSandbox";
    }
    /**
     * A cookie which was not stored from a response with the corresponding reason.
     * <p><b>Experimental:</b> this part of CDP may change without notice.
     */
    public static final class BlockedSetCookieWithReason extends CdpObject {
        private BlockedSetCookieWithReason(Map<String, Object> values) { super(values); }
        @Nullable public static BlockedSetCookieWithReason fromMap(@Nullable Map<String, Object> values) {
            return values == null ? null : new BlockedSetCookieWithReason(values);
        }
        public static Builder builder() { return new Builder(); }
        /**
         * The reason(s) this cookie was blocked.
         * @return the protocol field value
         */
        @Nullable public java.util.List<String> blockedReasons() {
            return list(value("blockedReasons"), element0 -> (String) element0);
        }
        /**
         * The string representing this individual cookie as it would appear in the header. This is not the entire &quot;cookie&quot; or &quot;set-cookie&quot; header which could have multiple cookies.
         * @return the protocol field value
         */
        @Nullable public String cookieLine() {
            return (String) value("cookieLine");
        }
        /**
         * The cookie object which represents the cookie which was not stored. It is optional because sometimes complete cookie information is not available, such as in the case of parsing errors.
         * @return the protocol field value
         */
        @Nullable public Network.Cookie cookie() {
            return Network.Cookie.fromMap(objectMap(value("cookie")));
        }
        public static final class Builder {
            private final Map<String, Object> values = new LinkedHashMap<>();
            /**
             * The reason(s) this cookie was blocked.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder blockedReasons(@Nullable java.util.List<String> value) {
                if (value == null) values.remove("blockedReasons");
                else values.put("blockedReasons", jsonValue(value));
                return this;
            }
            /**
             * The string representing this individual cookie as it would appear in the header. This is not the entire &quot;cookie&quot; or &quot;set-cookie&quot; header which could have multiple cookies.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder cookieLine(@Nullable String value) {
                if (value == null) values.remove("cookieLine");
                else values.put("cookieLine", jsonValue(value));
                return this;
            }
            /**
             * The cookie object which represents the cookie which was not stored. It is optional because sometimes complete cookie information is not available, such as in the case of parsing errors.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder cookie(@Nullable Network.Cookie value) {
                if (value == null) values.remove("cookie");
                else values.put("cookie", jsonValue(value));
                return this;
            }
            public BlockedSetCookieWithReason build() {
                if (!values.containsKey("blockedReasons")) throw new IllegalStateException("Missing required CDP field: blockedReasons");
                if (!values.containsKey("cookieLine")) throw new IllegalStateException("Missing required CDP field: cookieLine");
                return new BlockedSetCookieWithReason(values);
            }
        }
    }
    /**
     * A cookie should have been blocked by 3PCD but is exempted and stored from a response with the corresponding reason. A cookie could only have at most one exemption reason.
     * <p><b>Experimental:</b> this part of CDP may change without notice.
     */
    public static final class ExemptedSetCookieWithReason extends CdpObject {
        private ExemptedSetCookieWithReason(Map<String, Object> values) { super(values); }
        @Nullable public static ExemptedSetCookieWithReason fromMap(@Nullable Map<String, Object> values) {
            return values == null ? null : new ExemptedSetCookieWithReason(values);
        }
        public static Builder builder() { return new Builder(); }
        /**
         * The reason the cookie was exempted.
         * @return the protocol field value
         */
        @Nullable public String exemptionReason() {
            return (String) value("exemptionReason");
        }
        /**
         * The string representing this individual cookie as it would appear in the header.
         * @return the protocol field value
         */
        @Nullable public String cookieLine() {
            return (String) value("cookieLine");
        }
        /**
         * The cookie object representing the cookie.
         * @return the protocol field value
         */
        @Nullable public Network.Cookie cookie() {
            return Network.Cookie.fromMap(objectMap(value("cookie")));
        }
        public static final class Builder {
            private final Map<String, Object> values = new LinkedHashMap<>();
            /**
             * The reason the cookie was exempted.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder exemptionReason(@Nullable String value) {
                if (value == null) values.remove("exemptionReason");
                else values.put("exemptionReason", jsonValue(value));
                return this;
            }
            /**
             * The string representing this individual cookie as it would appear in the header.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder cookieLine(@Nullable String value) {
                if (value == null) values.remove("cookieLine");
                else values.put("cookieLine", jsonValue(value));
                return this;
            }
            /**
             * The cookie object representing the cookie.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder cookie(@Nullable Network.Cookie value) {
                if (value == null) values.remove("cookie");
                else values.put("cookie", jsonValue(value));
                return this;
            }
            public ExemptedSetCookieWithReason build() {
                if (!values.containsKey("exemptionReason")) throw new IllegalStateException("Missing required CDP field: exemptionReason");
                if (!values.containsKey("cookieLine")) throw new IllegalStateException("Missing required CDP field: cookieLine");
                if (!values.containsKey("cookie")) throw new IllegalStateException("Missing required CDP field: cookie");
                return new ExemptedSetCookieWithReason(values);
            }
        }
    }
    /**
     * A cookie associated with the request which may or may not be sent with it. Includes the cookies itself and reasons for blocking or exemption.
     * <p><b>Experimental:</b> this part of CDP may change without notice.
     */
    public static final class AssociatedCookie extends CdpObject {
        private AssociatedCookie(Map<String, Object> values) { super(values); }
        @Nullable public static AssociatedCookie fromMap(@Nullable Map<String, Object> values) {
            return values == null ? null : new AssociatedCookie(values);
        }
        public static Builder builder() { return new Builder(); }
        /**
         * The cookie object representing the cookie which was not sent.
         * @return the protocol field value
         */
        @Nullable public Network.Cookie cookie() {
            return Network.Cookie.fromMap(objectMap(value("cookie")));
        }
        /**
         * The reason(s) the cookie was blocked. If empty means the cookie is included.
         * @return the protocol field value
         */
        @Nullable public java.util.List<String> blockedReasons() {
            return list(value("blockedReasons"), element0 -> (String) element0);
        }
        /**
         * The reason the cookie should have been blocked by 3PCD but is exempted. A cookie could only have at most one exemption reason.
         * @return the protocol field value
         */
        @Nullable public String exemptionReason() {
            return (String) value("exemptionReason");
        }
        public static final class Builder {
            private final Map<String, Object> values = new LinkedHashMap<>();
            /**
             * The cookie object representing the cookie which was not sent.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder cookie(@Nullable Network.Cookie value) {
                if (value == null) values.remove("cookie");
                else values.put("cookie", jsonValue(value));
                return this;
            }
            /**
             * The reason(s) the cookie was blocked. If empty means the cookie is included.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder blockedReasons(@Nullable java.util.List<String> value) {
                if (value == null) values.remove("blockedReasons");
                else values.put("blockedReasons", jsonValue(value));
                return this;
            }
            /**
             * The reason the cookie should have been blocked by 3PCD but is exempted. A cookie could only have at most one exemption reason.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder exemptionReason(@Nullable String value) {
                if (value == null) values.remove("exemptionReason");
                else values.put("exemptionReason", jsonValue(value));
                return this;
            }
            public AssociatedCookie build() {
                if (!values.containsKey("cookie")) throw new IllegalStateException("Missing required CDP field: cookie");
                if (!values.containsKey("blockedReasons")) throw new IllegalStateException("Missing required CDP field: blockedReasons");
                return new AssociatedCookie(values);
            }
        }
    }
    /**
     * Cookie parameter object
     */
    public static final class CookieParam extends CdpObject {
        private CookieParam(Map<String, Object> values) { super(values); }
        @Nullable public static CookieParam fromMap(@Nullable Map<String, Object> values) {
            return values == null ? null : new CookieParam(values);
        }
        public static Builder builder() { return new Builder(); }
        /**
         * Cookie name.
         * @return the protocol field value
         */
        @Nullable public String name() {
            return (String) value("name");
        }
        /**
         * Cookie value.
         * @return the protocol field value
         */
        @Nullable public String value() {
            return (String) value("value");
        }
        /**
         * The request-URI to associate with the setting of the cookie. This value can affect the default domain, path, source port, and source scheme values of the created cookie.
         * @return the protocol field value
         */
        @Nullable public String url() {
            return (String) value("url");
        }
        /**
         * Cookie domain.
         * @return the protocol field value
         */
        @Nullable public String domain() {
            return (String) value("domain");
        }
        /**
         * Cookie path.
         * @return the protocol field value
         */
        @Nullable public String path() {
            return (String) value("path");
        }
        /**
         * True if cookie is secure.
         * @return the protocol field value
         */
        @Nullable public Boolean secure() {
            return (Boolean) value("secure");
        }
        /**
         * True if cookie is http-only.
         * @return the protocol field value
         */
        @Nullable public Boolean httpOnly() {
            return (Boolean) value("httpOnly");
        }
        /**
         * Cookie SameSite type.
         * @return the protocol field value
         */
        @Nullable public String sameSite() {
            return (String) value("sameSite");
        }
        /**
         * Cookie expiration date, session cookie if not set
         * @return the protocol field value
         */
        @Nullable public Double expires() {
            return numberAsDouble(value("expires"));
        }
        /**
         * Cookie Priority.
         * <p><b>Experimental:</b> this part of CDP may change without notice.
         * @return the protocol field value
         */
        @Nullable public String priority() {
            return (String) value("priority");
        }
        /**
         * Cookie source scheme type.
         * <p><b>Experimental:</b> this part of CDP may change without notice.
         * @return the protocol field value
         */
        @Nullable public String sourceScheme() {
            return (String) value("sourceScheme");
        }
        /**
         * Cookie source port. Valid values are {-1, [1, 65535]}, -1 indicates an unspecified port. An unspecified port value allows protocol clients to emulate legacy cookie scope for the port. This is a temporary ability and it will be removed in the future.
         * <p><b>Experimental:</b> this part of CDP may change without notice.
         * @return the protocol field value
         */
        @Nullable public Long sourcePort() {
            return numberAsLong(value("sourcePort"));
        }
        /**
         * Cookie partition key. If not set, the cookie will be set as not partitioned.
         * <p><b>Experimental:</b> this part of CDP may change without notice.
         * @return the protocol field value
         */
        @Nullable public Network.CookiePartitionKey partitionKey() {
            return Network.CookiePartitionKey.fromMap(objectMap(value("partitionKey")));
        }
        public static final class Builder {
            private final Map<String, Object> values = new LinkedHashMap<>();
            /**
             * Cookie name.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder name(@Nullable String value) {
                if (value == null) values.remove("name");
                else values.put("name", jsonValue(value));
                return this;
            }
            /**
             * Cookie value.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder value(@Nullable String value) {
                if (value == null) values.remove("value");
                else values.put("value", jsonValue(value));
                return this;
            }
            /**
             * The request-URI to associate with the setting of the cookie. This value can affect the default domain, path, source port, and source scheme values of the created cookie.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder url(@Nullable String value) {
                if (value == null) values.remove("url");
                else values.put("url", jsonValue(value));
                return this;
            }
            /**
             * Cookie domain.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder domain(@Nullable String value) {
                if (value == null) values.remove("domain");
                else values.put("domain", jsonValue(value));
                return this;
            }
            /**
             * Cookie path.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder path(@Nullable String value) {
                if (value == null) values.remove("path");
                else values.put("path", jsonValue(value));
                return this;
            }
            /**
             * True if cookie is secure.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder secure(@Nullable Boolean value) {
                if (value == null) values.remove("secure");
                else values.put("secure", jsonValue(value));
                return this;
            }
            /**
             * True if cookie is http-only.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder httpOnly(@Nullable Boolean value) {
                if (value == null) values.remove("httpOnly");
                else values.put("httpOnly", jsonValue(value));
                return this;
            }
            /**
             * Cookie SameSite type.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder sameSite(@Nullable String value) {
                if (value == null) values.remove("sameSite");
                else values.put("sameSite", jsonValue(value));
                return this;
            }
            /**
             * Cookie expiration date, session cookie if not set
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder expires(@Nullable Double value) {
                if (value == null) values.remove("expires");
                else values.put("expires", jsonValue(value));
                return this;
            }
            /**
             * Cookie Priority.
             * <p><b>Experimental:</b> this part of CDP may change without notice.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder priority(@Nullable String value) {
                if (value == null) values.remove("priority");
                else values.put("priority", jsonValue(value));
                return this;
            }
            /**
             * Cookie source scheme type.
             * <p><b>Experimental:</b> this part of CDP may change without notice.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder sourceScheme(@Nullable String value) {
                if (value == null) values.remove("sourceScheme");
                else values.put("sourceScheme", jsonValue(value));
                return this;
            }
            /**
             * Cookie source port. Valid values are {-1, [1, 65535]}, -1 indicates an unspecified port. An unspecified port value allows protocol clients to emulate legacy cookie scope for the port. This is a temporary ability and it will be removed in the future.
             * <p><b>Experimental:</b> this part of CDP may change without notice.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder sourcePort(@Nullable Long value) {
                if (value == null) values.remove("sourcePort");
                else values.put("sourcePort", jsonValue(value));
                return this;
            }
            /**
             * Cookie partition key. If not set, the cookie will be set as not partitioned.
             * <p><b>Experimental:</b> this part of CDP may change without notice.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder partitionKey(@Nullable Network.CookiePartitionKey value) {
                if (value == null) values.remove("partitionKey");
                else values.put("partitionKey", jsonValue(value));
                return this;
            }
            public CookieParam build() {
                if (!values.containsKey("name")) throw new IllegalStateException("Missing required CDP field: name");
                if (!values.containsKey("value")) throw new IllegalStateException("Missing required CDP field: value");
                return new CookieParam(values);
            }
        }
    }
    /**
     * Authorization challenge for HTTP status code 401 or 407.
     * <p><b>Experimental:</b> this part of CDP may change without notice.
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
     * <p><b>Experimental:</b> this part of CDP may change without notice.
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
     * Stages of the interception to begin intercepting. Request will intercept before the request is sent. Response will intercept after the response is received.
     * <p><b>Experimental:</b> this part of CDP may change without notice.
     */
    public static final class InterceptionStage {
        private InterceptionStage() {}
        public static final String REQUEST = "Request";
        public static final String HEADERSRECEIVED = "HeadersReceived";
    }
    /**
     * Request pattern for interception.
     * <p><b>Experimental:</b> this part of CDP may change without notice.
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
        @Nullable public String interceptionStage() {
            return (String) value("interceptionStage");
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
            public Builder interceptionStage(@Nullable String value) {
                if (value == null) values.remove("interceptionStage");
                else values.put("interceptionStage", jsonValue(value));
                return this;
            }
            public RequestPattern build() {
                return new RequestPattern(values);
            }
        }
    }
    /**
     * Information about a signed exchange signature. https://wicg.github.io/webpackage/draft-yasskin-httpbis-origin-signed-exchanges-impl.html#rfc.section.3.1
     * <p><b>Experimental:</b> this part of CDP may change without notice.
     */
    public static final class SignedExchangeSignature extends CdpObject {
        private SignedExchangeSignature(Map<String, Object> values) { super(values); }
        @Nullable public static SignedExchangeSignature fromMap(@Nullable Map<String, Object> values) {
            return values == null ? null : new SignedExchangeSignature(values);
        }
        public static Builder builder() { return new Builder(); }
        /**
         * Signed exchange signature label.
         * @return the protocol field value
         */
        @Nullable public String label() {
            return (String) value("label");
        }
        /**
         * The hex string of signed exchange signature.
         * @return the protocol field value
         */
        @Nullable public String signature() {
            return (String) value("signature");
        }
        /**
         * Signed exchange signature integrity.
         * @return the protocol field value
         */
        @Nullable public String integrity() {
            return (String) value("integrity");
        }
        /**
         * Signed exchange signature cert Url.
         * @return the protocol field value
         */
        @Nullable public String certUrl() {
            return (String) value("certUrl");
        }
        /**
         * The hex string of signed exchange signature cert sha256.
         * @return the protocol field value
         */
        @Nullable public String certSha256() {
            return (String) value("certSha256");
        }
        /**
         * Signed exchange signature validity Url.
         * @return the protocol field value
         */
        @Nullable public String validityUrl() {
            return (String) value("validityUrl");
        }
        /**
         * Signed exchange signature date.
         * @return the protocol field value
         */
        @Nullable public Long date() {
            return numberAsLong(value("date"));
        }
        /**
         * Signed exchange signature expires.
         * @return the protocol field value
         */
        @Nullable public Long expires() {
            return numberAsLong(value("expires"));
        }
        /**
         * The encoded certificates.
         * @return the protocol field value
         */
        @Nullable public java.util.List<String> certificates() {
            return list(value("certificates"), element0 -> (String) element0);
        }
        public static final class Builder {
            private final Map<String, Object> values = new LinkedHashMap<>();
            /**
             * Signed exchange signature label.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder label(@Nullable String value) {
                if (value == null) values.remove("label");
                else values.put("label", jsonValue(value));
                return this;
            }
            /**
             * The hex string of signed exchange signature.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder signature(@Nullable String value) {
                if (value == null) values.remove("signature");
                else values.put("signature", jsonValue(value));
                return this;
            }
            /**
             * Signed exchange signature integrity.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder integrity(@Nullable String value) {
                if (value == null) values.remove("integrity");
                else values.put("integrity", jsonValue(value));
                return this;
            }
            /**
             * Signed exchange signature cert Url.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder certUrl(@Nullable String value) {
                if (value == null) values.remove("certUrl");
                else values.put("certUrl", jsonValue(value));
                return this;
            }
            /**
             * The hex string of signed exchange signature cert sha256.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder certSha256(@Nullable String value) {
                if (value == null) values.remove("certSha256");
                else values.put("certSha256", jsonValue(value));
                return this;
            }
            /**
             * Signed exchange signature validity Url.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder validityUrl(@Nullable String value) {
                if (value == null) values.remove("validityUrl");
                else values.put("validityUrl", jsonValue(value));
                return this;
            }
            /**
             * Signed exchange signature date.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder date(@Nullable Long value) {
                if (value == null) values.remove("date");
                else values.put("date", jsonValue(value));
                return this;
            }
            /**
             * Signed exchange signature expires.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder expires(@Nullable Long value) {
                if (value == null) values.remove("expires");
                else values.put("expires", jsonValue(value));
                return this;
            }
            /**
             * The encoded certificates.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder certificates(@Nullable java.util.List<String> value) {
                if (value == null) values.remove("certificates");
                else values.put("certificates", jsonValue(value));
                return this;
            }
            public SignedExchangeSignature build() {
                if (!values.containsKey("label")) throw new IllegalStateException("Missing required CDP field: label");
                if (!values.containsKey("signature")) throw new IllegalStateException("Missing required CDP field: signature");
                if (!values.containsKey("integrity")) throw new IllegalStateException("Missing required CDP field: integrity");
                if (!values.containsKey("validityUrl")) throw new IllegalStateException("Missing required CDP field: validityUrl");
                if (!values.containsKey("date")) throw new IllegalStateException("Missing required CDP field: date");
                if (!values.containsKey("expires")) throw new IllegalStateException("Missing required CDP field: expires");
                return new SignedExchangeSignature(values);
            }
        }
    }
    /**
     * Information about a signed exchange header. https://wicg.github.io/webpackage/draft-yasskin-httpbis-origin-signed-exchanges-impl.html#cbor-representation
     * <p><b>Experimental:</b> this part of CDP may change without notice.
     */
    public static final class SignedExchangeHeader extends CdpObject {
        private SignedExchangeHeader(Map<String, Object> values) { super(values); }
        @Nullable public static SignedExchangeHeader fromMap(@Nullable Map<String, Object> values) {
            return values == null ? null : new SignedExchangeHeader(values);
        }
        public static Builder builder() { return new Builder(); }
        /**
         * Signed exchange request URL.
         * @return the protocol field value
         */
        @Nullable public String requestUrl() {
            return (String) value("requestUrl");
        }
        /**
         * Signed exchange response code.
         * @return the protocol field value
         */
        @Nullable public Long responseCode() {
            return numberAsLong(value("responseCode"));
        }
        /**
         * Signed exchange response headers.
         * @return the protocol field value
         */
        @Nullable public java.util.Map<String, Object> responseHeaders() {
            return objectMap(value("responseHeaders"));
        }
        /**
         * Signed exchange response signature.
         * @return the protocol field value
         */
        @Nullable public java.util.List<Network.SignedExchangeSignature> signatures() {
            return list(value("signatures"), element0 -> Network.SignedExchangeSignature.fromMap(objectMap(element0)));
        }
        /**
         * Signed exchange header integrity hash in the form of {@code sha256-&lt;base64-hash-value&gt;}.
         * @return the protocol field value
         */
        @Nullable public String headerIntegrity() {
            return (String) value("headerIntegrity");
        }
        public static final class Builder {
            private final Map<String, Object> values = new LinkedHashMap<>();
            /**
             * Signed exchange request URL.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder requestUrl(@Nullable String value) {
                if (value == null) values.remove("requestUrl");
                else values.put("requestUrl", jsonValue(value));
                return this;
            }
            /**
             * Signed exchange response code.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder responseCode(@Nullable Long value) {
                if (value == null) values.remove("responseCode");
                else values.put("responseCode", jsonValue(value));
                return this;
            }
            /**
             * Signed exchange response headers.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder responseHeaders(@Nullable java.util.Map<String, Object> value) {
                if (value == null) values.remove("responseHeaders");
                else values.put("responseHeaders", jsonValue(value));
                return this;
            }
            /**
             * Signed exchange response signature.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder signatures(@Nullable java.util.List<Network.SignedExchangeSignature> value) {
                if (value == null) values.remove("signatures");
                else values.put("signatures", jsonValue(value));
                return this;
            }
            /**
             * Signed exchange header integrity hash in the form of {@code sha256-&lt;base64-hash-value&gt;}.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder headerIntegrity(@Nullable String value) {
                if (value == null) values.remove("headerIntegrity");
                else values.put("headerIntegrity", jsonValue(value));
                return this;
            }
            public SignedExchangeHeader build() {
                if (!values.containsKey("requestUrl")) throw new IllegalStateException("Missing required CDP field: requestUrl");
                if (!values.containsKey("responseCode")) throw new IllegalStateException("Missing required CDP field: responseCode");
                if (!values.containsKey("responseHeaders")) throw new IllegalStateException("Missing required CDP field: responseHeaders");
                if (!values.containsKey("signatures")) throw new IllegalStateException("Missing required CDP field: signatures");
                if (!values.containsKey("headerIntegrity")) throw new IllegalStateException("Missing required CDP field: headerIntegrity");
                return new SignedExchangeHeader(values);
            }
        }
    }
    /**
     * Field type for a signed exchange related error.
     * <p><b>Experimental:</b> this part of CDP may change without notice.
     */
    public static final class SignedExchangeErrorField {
        private SignedExchangeErrorField() {}
        public static final String SIGNATURESIG = "signatureSig";
        public static final String SIGNATUREINTEGRITY = "signatureIntegrity";
        public static final String SIGNATURECERTURL = "signatureCertUrl";
        public static final String SIGNATURECERTSHA256 = "signatureCertSha256";
        public static final String SIGNATUREVALIDITYURL = "signatureValidityUrl";
        public static final String SIGNATURETIMESTAMPS = "signatureTimestamps";
    }
    /**
     * Information about a signed exchange response.
     * <p><b>Experimental:</b> this part of CDP may change without notice.
     */
    public static final class SignedExchangeError extends CdpObject {
        private SignedExchangeError(Map<String, Object> values) { super(values); }
        @Nullable public static SignedExchangeError fromMap(@Nullable Map<String, Object> values) {
            return values == null ? null : new SignedExchangeError(values);
        }
        public static Builder builder() { return new Builder(); }
        /**
         * Error message.
         * @return the protocol field value
         */
        @Nullable public String message() {
            return (String) value("message");
        }
        /**
         * The index of the signature which caused the error.
         * @return the protocol field value
         */
        @Nullable public Long signatureIndex() {
            return numberAsLong(value("signatureIndex"));
        }
        /**
         * The field which caused the error.
         * @return the protocol field value
         */
        @Nullable public String errorField() {
            return (String) value("errorField");
        }
        public static final class Builder {
            private final Map<String, Object> values = new LinkedHashMap<>();
            /**
             * Error message.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder message(@Nullable String value) {
                if (value == null) values.remove("message");
                else values.put("message", jsonValue(value));
                return this;
            }
            /**
             * The index of the signature which caused the error.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder signatureIndex(@Nullable Long value) {
                if (value == null) values.remove("signatureIndex");
                else values.put("signatureIndex", jsonValue(value));
                return this;
            }
            /**
             * The field which caused the error.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder errorField(@Nullable String value) {
                if (value == null) values.remove("errorField");
                else values.put("errorField", jsonValue(value));
                return this;
            }
            public SignedExchangeError build() {
                if (!values.containsKey("message")) throw new IllegalStateException("Missing required CDP field: message");
                return new SignedExchangeError(values);
            }
        }
    }
    /**
     * Information about a signed exchange response.
     * <p><b>Experimental:</b> this part of CDP may change without notice.
     */
    public static final class SignedExchangeInfo extends CdpObject {
        private SignedExchangeInfo(Map<String, Object> values) { super(values); }
        @Nullable public static SignedExchangeInfo fromMap(@Nullable Map<String, Object> values) {
            return values == null ? null : new SignedExchangeInfo(values);
        }
        public static Builder builder() { return new Builder(); }
        /**
         * The outer response of signed HTTP exchange which was received from network.
         * @return the protocol field value
         */
        @Nullable public Network.Response outerResponse() {
            return Network.Response.fromMap(objectMap(value("outerResponse")));
        }
        /**
         * Whether network response for the signed exchange was accompanied by extra headers.
         * @return the protocol field value
         */
        @Nullable public Boolean hasExtraInfo() {
            return (Boolean) value("hasExtraInfo");
        }
        /**
         * Information about the signed exchange header.
         * @return the protocol field value
         */
        @Nullable public Network.SignedExchangeHeader header() {
            return Network.SignedExchangeHeader.fromMap(objectMap(value("header")));
        }
        /**
         * Security details for the signed exchange header.
         * @return the protocol field value
         */
        @Nullable public Network.SecurityDetails securityDetails() {
            return Network.SecurityDetails.fromMap(objectMap(value("securityDetails")));
        }
        /**
         * Errors occurred while handling the signed exchange.
         * @return the protocol field value
         */
        @Nullable public java.util.List<Network.SignedExchangeError> errors() {
            return list(value("errors"), element0 -> Network.SignedExchangeError.fromMap(objectMap(element0)));
        }
        public static final class Builder {
            private final Map<String, Object> values = new LinkedHashMap<>();
            /**
             * The outer response of signed HTTP exchange which was received from network.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder outerResponse(@Nullable Network.Response value) {
                if (value == null) values.remove("outerResponse");
                else values.put("outerResponse", jsonValue(value));
                return this;
            }
            /**
             * Whether network response for the signed exchange was accompanied by extra headers.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder hasExtraInfo(@Nullable Boolean value) {
                if (value == null) values.remove("hasExtraInfo");
                else values.put("hasExtraInfo", jsonValue(value));
                return this;
            }
            /**
             * Information about the signed exchange header.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder header(@Nullable Network.SignedExchangeHeader value) {
                if (value == null) values.remove("header");
                else values.put("header", jsonValue(value));
                return this;
            }
            /**
             * Security details for the signed exchange header.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder securityDetails(@Nullable Network.SecurityDetails value) {
                if (value == null) values.remove("securityDetails");
                else values.put("securityDetails", jsonValue(value));
                return this;
            }
            /**
             * Errors occurred while handling the signed exchange.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder errors(@Nullable java.util.List<Network.SignedExchangeError> value) {
                if (value == null) values.remove("errors");
                else values.put("errors", jsonValue(value));
                return this;
            }
            public SignedExchangeInfo build() {
                if (!values.containsKey("outerResponse")) throw new IllegalStateException("Missing required CDP field: outerResponse");
                if (!values.containsKey("hasExtraInfo")) throw new IllegalStateException("Missing required CDP field: hasExtraInfo");
                return new SignedExchangeInfo(values);
            }
        }
    }
    /**
     * List of content encodings supported by the backend.
     * <p><b>Experimental:</b> this part of CDP may change without notice.
     */
    public static final class ContentEncoding {
        private ContentEncoding() {}
        public static final String DEFLATE = "deflate";
        public static final String GZIP = "gzip";
        public static final String BR = "br";
        public static final String ZSTD = "zstd";
    }
    /**
     * <p><b>Experimental:</b> this part of CDP may change without notice.
     */
    public static final class NetworkConditions extends CdpObject {
        private NetworkConditions(Map<String, Object> values) { super(values); }
        @Nullable public static NetworkConditions fromMap(@Nullable Map<String, Object> values) {
            return values == null ? null : new NetworkConditions(values);
        }
        public static Builder builder() { return new Builder(); }
        /**
         * Only matching requests will be affected by these conditions. Patterns use the URLPattern constructor string syntax (https://urlpattern.spec.whatwg.org/) and must be absolute. If the pattern is empty, all requests are matched (including p2p connections).
         * @return the protocol field value
         */
        @Nullable public String urlPattern() {
            return (String) value("urlPattern");
        }
        /**
         * Minimum latency from request sent to response headers received (ms).
         * @return the protocol field value
         */
        @Nullable public Double latency() {
            return numberAsDouble(value("latency"));
        }
        /**
         * Maximal aggregated download throughput (bytes/sec). -1 disables download throttling.
         * @return the protocol field value
         */
        @Nullable public Double downloadThroughput() {
            return numberAsDouble(value("downloadThroughput"));
        }
        /**
         * Maximal aggregated upload throughput (bytes/sec). -1 disables upload throttling.
         * @return the protocol field value
         */
        @Nullable public Double uploadThroughput() {
            return numberAsDouble(value("uploadThroughput"));
        }
        /**
         * Connection type if known.
         * @return the protocol field value
         */
        @Nullable public String connectionType() {
            return (String) value("connectionType");
        }
        /**
         * WebRTC packet loss (percent, 0-100). 0 disables packet loss emulation, 100 drops all the packets.
         * @return the protocol field value
         */
        @Nullable public Double packetLoss() {
            return numberAsDouble(value("packetLoss"));
        }
        /**
         * WebRTC packet queue length (packet). 0 removes any queue length limitations.
         * @return the protocol field value
         */
        @Nullable public Long packetQueueLength() {
            return numberAsLong(value("packetQueueLength"));
        }
        /**
         * WebRTC packetReordering feature.
         * @return the protocol field value
         */
        @Nullable public Boolean packetReordering() {
            return (Boolean) value("packetReordering");
        }
        /**
         * True to emulate internet disconnection.
         * @return the protocol field value
         */
        @Nullable public Boolean offline() {
            return (Boolean) value("offline");
        }
        public static final class Builder {
            private final Map<String, Object> values = new LinkedHashMap<>();
            /**
             * Only matching requests will be affected by these conditions. Patterns use the URLPattern constructor string syntax (https://urlpattern.spec.whatwg.org/) and must be absolute. If the pattern is empty, all requests are matched (including p2p connections).
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder urlPattern(@Nullable String value) {
                if (value == null) values.remove("urlPattern");
                else values.put("urlPattern", jsonValue(value));
                return this;
            }
            /**
             * Minimum latency from request sent to response headers received (ms).
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder latency(@Nullable Double value) {
                if (value == null) values.remove("latency");
                else values.put("latency", jsonValue(value));
                return this;
            }
            /**
             * Maximal aggregated download throughput (bytes/sec). -1 disables download throttling.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder downloadThroughput(@Nullable Double value) {
                if (value == null) values.remove("downloadThroughput");
                else values.put("downloadThroughput", jsonValue(value));
                return this;
            }
            /**
             * Maximal aggregated upload throughput (bytes/sec). -1 disables upload throttling.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder uploadThroughput(@Nullable Double value) {
                if (value == null) values.remove("uploadThroughput");
                else values.put("uploadThroughput", jsonValue(value));
                return this;
            }
            /**
             * Connection type if known.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder connectionType(@Nullable String value) {
                if (value == null) values.remove("connectionType");
                else values.put("connectionType", jsonValue(value));
                return this;
            }
            /**
             * WebRTC packet loss (percent, 0-100). 0 disables packet loss emulation, 100 drops all the packets.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder packetLoss(@Nullable Double value) {
                if (value == null) values.remove("packetLoss");
                else values.put("packetLoss", jsonValue(value));
                return this;
            }
            /**
             * WebRTC packet queue length (packet). 0 removes any queue length limitations.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder packetQueueLength(@Nullable Long value) {
                if (value == null) values.remove("packetQueueLength");
                else values.put("packetQueueLength", jsonValue(value));
                return this;
            }
            /**
             * WebRTC packetReordering feature.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder packetReordering(@Nullable Boolean value) {
                if (value == null) values.remove("packetReordering");
                else values.put("packetReordering", jsonValue(value));
                return this;
            }
            /**
             * True to emulate internet disconnection.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder offline(@Nullable Boolean value) {
                if (value == null) values.remove("offline");
                else values.put("offline", jsonValue(value));
                return this;
            }
            public NetworkConditions build() {
                if (!values.containsKey("urlPattern")) throw new IllegalStateException("Missing required CDP field: urlPattern");
                if (!values.containsKey("latency")) throw new IllegalStateException("Missing required CDP field: latency");
                if (!values.containsKey("downloadThroughput")) throw new IllegalStateException("Missing required CDP field: downloadThroughput");
                if (!values.containsKey("uploadThroughput")) throw new IllegalStateException("Missing required CDP field: uploadThroughput");
                return new NetworkConditions(values);
            }
        }
    }
    /**
     * <p><b>Experimental:</b> this part of CDP may change without notice.
     */
    public static final class BlockPattern extends CdpObject {
        private BlockPattern(Map<String, Object> values) { super(values); }
        @Nullable public static BlockPattern fromMap(@Nullable Map<String, Object> values) {
            return values == null ? null : new BlockPattern(values);
        }
        public static Builder builder() { return new Builder(); }
        /**
         * URL pattern to match. Patterns use the URLPattern constructor string syntax (https://urlpattern.spec.whatwg.org/) and must be absolute. Example: {@code *://*:*&amp;#47;*.css}.
         * @return the protocol field value
         */
        @Nullable public String urlPattern() {
            return (String) value("urlPattern");
        }
        /**
         * Whether or not to block the pattern. If false, a matching request will not be blocked even if it matches a later {@code BlockPattern}.
         * @return the protocol field value
         */
        @Nullable public Boolean block() {
            return (Boolean) value("block");
        }
        public static final class Builder {
            private final Map<String, Object> values = new LinkedHashMap<>();
            /**
             * URL pattern to match. Patterns use the URLPattern constructor string syntax (https://urlpattern.spec.whatwg.org/) and must be absolute. Example: {@code *://*:*&amp;#47;*.css}.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder urlPattern(@Nullable String value) {
                if (value == null) values.remove("urlPattern");
                else values.put("urlPattern", jsonValue(value));
                return this;
            }
            /**
             * Whether or not to block the pattern. If false, a matching request will not be blocked even if it matches a later {@code BlockPattern}.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder block(@Nullable Boolean value) {
                if (value == null) values.remove("block");
                else values.put("block", jsonValue(value));
                return this;
            }
            public BlockPattern build() {
                if (!values.containsKey("urlPattern")) throw new IllegalStateException("Missing required CDP field: urlPattern");
                if (!values.containsKey("block")) throw new IllegalStateException("Missing required CDP field: block");
                return new BlockPattern(values);
            }
        }
    }
    /**
     * Wire values for DirectSocketDnsQueryType.
     * <p><b>Experimental:</b> this part of CDP may change without notice.
     */
    public static final class DirectSocketDnsQueryType {
        private DirectSocketDnsQueryType() {}
        public static final String IPV4 = "ipv4";
        public static final String IPV6 = "ipv6";
    }
    /**
     * <p><b>Experimental:</b> this part of CDP may change without notice.
     */
    public static final class DirectTCPSocketOptions extends CdpObject {
        private DirectTCPSocketOptions(Map<String, Object> values) { super(values); }
        @Nullable public static DirectTCPSocketOptions fromMap(@Nullable Map<String, Object> values) {
            return values == null ? null : new DirectTCPSocketOptions(values);
        }
        public static Builder builder() { return new Builder(); }
        /**
         * TCP_NODELAY option
         * @return the protocol field value
         */
        @Nullable public Boolean noDelay() {
            return (Boolean) value("noDelay");
        }
        /**
         * Expected to be unsigned integer.
         * @return the protocol field value
         */
        @Nullable public Double keepAliveDelay() {
            return numberAsDouble(value("keepAliveDelay"));
        }
        /**
         * Expected to be unsigned integer.
         * @return the protocol field value
         */
        @Nullable public Double sendBufferSize() {
            return numberAsDouble(value("sendBufferSize"));
        }
        /**
         * Expected to be unsigned integer.
         * @return the protocol field value
         */
        @Nullable public Double receiveBufferSize() {
            return numberAsDouble(value("receiveBufferSize"));
        }
        /**
         * Returns the dnsQueryType field.
         * @return the protocol field value
         */
        @Nullable public String dnsQueryType() {
            return (String) value("dnsQueryType");
        }
        public static final class Builder {
            private final Map<String, Object> values = new LinkedHashMap<>();
            /**
             * TCP_NODELAY option
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder noDelay(@Nullable Boolean value) {
                if (value == null) values.remove("noDelay");
                else values.put("noDelay", jsonValue(value));
                return this;
            }
            /**
             * Expected to be unsigned integer.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder keepAliveDelay(@Nullable Double value) {
                if (value == null) values.remove("keepAliveDelay");
                else values.put("keepAliveDelay", jsonValue(value));
                return this;
            }
            /**
             * Expected to be unsigned integer.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder sendBufferSize(@Nullable Double value) {
                if (value == null) values.remove("sendBufferSize");
                else values.put("sendBufferSize", jsonValue(value));
                return this;
            }
            /**
             * Expected to be unsigned integer.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder receiveBufferSize(@Nullable Double value) {
                if (value == null) values.remove("receiveBufferSize");
                else values.put("receiveBufferSize", jsonValue(value));
                return this;
            }
            /**
             * Sets the dnsQueryType field.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder dnsQueryType(@Nullable String value) {
                if (value == null) values.remove("dnsQueryType");
                else values.put("dnsQueryType", jsonValue(value));
                return this;
            }
            public DirectTCPSocketOptions build() {
                if (!values.containsKey("noDelay")) throw new IllegalStateException("Missing required CDP field: noDelay");
                return new DirectTCPSocketOptions(values);
            }
        }
    }
    /**
     * <p><b>Experimental:</b> this part of CDP may change without notice.
     */
    public static final class DirectUDPSocketOptions extends CdpObject {
        private DirectUDPSocketOptions(Map<String, Object> values) { super(values); }
        @Nullable public static DirectUDPSocketOptions fromMap(@Nullable Map<String, Object> values) {
            return values == null ? null : new DirectUDPSocketOptions(values);
        }
        public static Builder builder() { return new Builder(); }
        /**
         * Returns the remoteAddr field.
         * @return the protocol field value
         */
        @Nullable public String remoteAddr() {
            return (String) value("remoteAddr");
        }
        /**
         * Unsigned int 16.
         * @return the protocol field value
         */
        @Nullable public Long remotePort() {
            return numberAsLong(value("remotePort"));
        }
        /**
         * Returns the localAddr field.
         * @return the protocol field value
         */
        @Nullable public String localAddr() {
            return (String) value("localAddr");
        }
        /**
         * Unsigned int 16.
         * @return the protocol field value
         */
        @Nullable public Long localPort() {
            return numberAsLong(value("localPort"));
        }
        /**
         * Returns the dnsQueryType field.
         * @return the protocol field value
         */
        @Nullable public String dnsQueryType() {
            return (String) value("dnsQueryType");
        }
        /**
         * Expected to be unsigned integer.
         * @return the protocol field value
         */
        @Nullable public Double sendBufferSize() {
            return numberAsDouble(value("sendBufferSize"));
        }
        /**
         * Expected to be unsigned integer.
         * @return the protocol field value
         */
        @Nullable public Double receiveBufferSize() {
            return numberAsDouble(value("receiveBufferSize"));
        }
        /**
         * Returns the multicastLoopback field.
         * @return the protocol field value
         */
        @Nullable public Boolean multicastLoopback() {
            return (Boolean) value("multicastLoopback");
        }
        /**
         * Unsigned int 8.
         * @return the protocol field value
         */
        @Nullable public Long multicastTimeToLive() {
            return numberAsLong(value("multicastTimeToLive"));
        }
        /**
         * Returns the multicastAllowAddressSharing field.
         * @return the protocol field value
         */
        @Nullable public Boolean multicastAllowAddressSharing() {
            return (Boolean) value("multicastAllowAddressSharing");
        }
        public static final class Builder {
            private final Map<String, Object> values = new LinkedHashMap<>();
            /**
             * Sets the remoteAddr field.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder remoteAddr(@Nullable String value) {
                if (value == null) values.remove("remoteAddr");
                else values.put("remoteAddr", jsonValue(value));
                return this;
            }
            /**
             * Unsigned int 16.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder remotePort(@Nullable Long value) {
                if (value == null) values.remove("remotePort");
                else values.put("remotePort", jsonValue(value));
                return this;
            }
            /**
             * Sets the localAddr field.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder localAddr(@Nullable String value) {
                if (value == null) values.remove("localAddr");
                else values.put("localAddr", jsonValue(value));
                return this;
            }
            /**
             * Unsigned int 16.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder localPort(@Nullable Long value) {
                if (value == null) values.remove("localPort");
                else values.put("localPort", jsonValue(value));
                return this;
            }
            /**
             * Sets the dnsQueryType field.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder dnsQueryType(@Nullable String value) {
                if (value == null) values.remove("dnsQueryType");
                else values.put("dnsQueryType", jsonValue(value));
                return this;
            }
            /**
             * Expected to be unsigned integer.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder sendBufferSize(@Nullable Double value) {
                if (value == null) values.remove("sendBufferSize");
                else values.put("sendBufferSize", jsonValue(value));
                return this;
            }
            /**
             * Expected to be unsigned integer.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder receiveBufferSize(@Nullable Double value) {
                if (value == null) values.remove("receiveBufferSize");
                else values.put("receiveBufferSize", jsonValue(value));
                return this;
            }
            /**
             * Sets the multicastLoopback field.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder multicastLoopback(@Nullable Boolean value) {
                if (value == null) values.remove("multicastLoopback");
                else values.put("multicastLoopback", jsonValue(value));
                return this;
            }
            /**
             * Unsigned int 8.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder multicastTimeToLive(@Nullable Long value) {
                if (value == null) values.remove("multicastTimeToLive");
                else values.put("multicastTimeToLive", jsonValue(value));
                return this;
            }
            /**
             * Sets the multicastAllowAddressSharing field.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder multicastAllowAddressSharing(@Nullable Boolean value) {
                if (value == null) values.remove("multicastAllowAddressSharing");
                else values.put("multicastAllowAddressSharing", jsonValue(value));
                return this;
            }
            public DirectUDPSocketOptions build() {
                return new DirectUDPSocketOptions(values);
            }
        }
    }
    /**
     * <p><b>Experimental:</b> this part of CDP may change without notice.
     */
    public static final class DirectUDPMessage extends CdpObject {
        private DirectUDPMessage(Map<String, Object> values) { super(values); }
        @Nullable public static DirectUDPMessage fromMap(@Nullable Map<String, Object> values) {
            return values == null ? null : new DirectUDPMessage(values);
        }
        public static Builder builder() { return new Builder(); }
        /**
         * Returns the data field.
         * @return the protocol field value
         */
        @Nullable public String data() {
            return (String) value("data");
        }
        /**
         * Null for connected mode.
         * @return the protocol field value
         */
        @Nullable public String remoteAddr() {
            return (String) value("remoteAddr");
        }
        /**
         * Null for connected mode. Expected to be unsigned integer.
         * @return the protocol field value
         */
        @Nullable public Long remotePort() {
            return numberAsLong(value("remotePort"));
        }
        public static final class Builder {
            private final Map<String, Object> values = new LinkedHashMap<>();
            /**
             * Sets the data field.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder data(@Nullable String value) {
                if (value == null) values.remove("data");
                else values.put("data", jsonValue(value));
                return this;
            }
            /**
             * Null for connected mode.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder remoteAddr(@Nullable String value) {
                if (value == null) values.remove("remoteAddr");
                else values.put("remoteAddr", jsonValue(value));
                return this;
            }
            /**
             * Null for connected mode. Expected to be unsigned integer.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder remotePort(@Nullable Long value) {
                if (value == null) values.remove("remotePort");
                else values.put("remotePort", jsonValue(value));
                return this;
            }
            public DirectUDPMessage build() {
                if (!values.containsKey("data")) throw new IllegalStateException("Missing required CDP field: data");
                return new DirectUDPMessage(values);
            }
        }
    }
    /**
     * Wire values for LocalNetworkAccessRequestPolicy.
     * <p><b>Experimental:</b> this part of CDP may change without notice.
     */
    public static final class LocalNetworkAccessRequestPolicy {
        private LocalNetworkAccessRequestPolicy() {}
        public static final String ALLOW = "Allow";
        public static final String BLOCKFROMINSECURETOMOREPRIVATE = "BlockFromInsecureToMorePrivate";
        public static final String WARNFROMINSECURETOMOREPRIVATE = "WarnFromInsecureToMorePrivate";
        public static final String PERMISSIONBLOCK = "PermissionBlock";
        public static final String PERMISSIONWARN = "PermissionWarn";
    }
    /**
     * Wire values for IPAddressSpace.
     * <p><b>Experimental:</b> this part of CDP may change without notice.
     */
    public static final class IPAddressSpace {
        private IPAddressSpace() {}
        public static final String LOOPBACK = "Loopback";
        public static final String LOCAL = "Local";
        public static final String PUBLIC = "Public";
        public static final String UNKNOWN = "Unknown";
    }
    /**
     * <p><b>Experimental:</b> this part of CDP may change without notice.
     */
    public static final class ConnectTiming extends CdpObject {
        private ConnectTiming(Map<String, Object> values) { super(values); }
        @Nullable public static ConnectTiming fromMap(@Nullable Map<String, Object> values) {
            return values == null ? null : new ConnectTiming(values);
        }
        public static Builder builder() { return new Builder(); }
        /**
         * Timing&#x27;s requestTime is a baseline in seconds, while the other numbers are ticks in milliseconds relatively to this requestTime. Matches ResourceTiming&#x27;s requestTime for the same request (but not for redirected requests).
         * @return the protocol field value
         */
        @Nullable public Double requestTime() {
            return numberAsDouble(value("requestTime"));
        }
        public static final class Builder {
            private final Map<String, Object> values = new LinkedHashMap<>();
            /**
             * Timing&#x27;s requestTime is a baseline in seconds, while the other numbers are ticks in milliseconds relatively to this requestTime. Matches ResourceTiming&#x27;s requestTime for the same request (but not for redirected requests).
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder requestTime(@Nullable Double value) {
                if (value == null) values.remove("requestTime");
                else values.put("requestTime", jsonValue(value));
                return this;
            }
            public ConnectTiming build() {
                if (!values.containsKey("requestTime")) throw new IllegalStateException("Missing required CDP field: requestTime");
                return new ConnectTiming(values);
            }
        }
    }
    /**
     * <p><b>Experimental:</b> this part of CDP may change without notice.
     */
    public static final class ClientSecurityState extends CdpObject {
        private ClientSecurityState(Map<String, Object> values) { super(values); }
        @Nullable public static ClientSecurityState fromMap(@Nullable Map<String, Object> values) {
            return values == null ? null : new ClientSecurityState(values);
        }
        public static Builder builder() { return new Builder(); }
        /**
         * Returns the initiatorIsSecureContext field.
         * @return the protocol field value
         */
        @Nullable public Boolean initiatorIsSecureContext() {
            return (Boolean) value("initiatorIsSecureContext");
        }
        /**
         * Returns the initiatorIPAddressSpace field.
         * @return the protocol field value
         */
        @Nullable public String initiatorIPAddressSpace() {
            return (String) value("initiatorIPAddressSpace");
        }
        /**
         * Returns the localNetworkAccessRequestPolicy field.
         * @return the protocol field value
         */
        @Nullable public String localNetworkAccessRequestPolicy() {
            return (String) value("localNetworkAccessRequestPolicy");
        }
        public static final class Builder {
            private final Map<String, Object> values = new LinkedHashMap<>();
            /**
             * Sets the initiatorIsSecureContext field.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder initiatorIsSecureContext(@Nullable Boolean value) {
                if (value == null) values.remove("initiatorIsSecureContext");
                else values.put("initiatorIsSecureContext", jsonValue(value));
                return this;
            }
            /**
             * Sets the initiatorIPAddressSpace field.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder initiatorIPAddressSpace(@Nullable String value) {
                if (value == null) values.remove("initiatorIPAddressSpace");
                else values.put("initiatorIPAddressSpace", jsonValue(value));
                return this;
            }
            /**
             * Sets the localNetworkAccessRequestPolicy field.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder localNetworkAccessRequestPolicy(@Nullable String value) {
                if (value == null) values.remove("localNetworkAccessRequestPolicy");
                else values.put("localNetworkAccessRequestPolicy", jsonValue(value));
                return this;
            }
            public ClientSecurityState build() {
                if (!values.containsKey("initiatorIsSecureContext")) throw new IllegalStateException("Missing required CDP field: initiatorIsSecureContext");
                if (!values.containsKey("initiatorIPAddressSpace")) throw new IllegalStateException("Missing required CDP field: initiatorIPAddressSpace");
                if (!values.containsKey("localNetworkAccessRequestPolicy")) throw new IllegalStateException("Missing required CDP field: localNetworkAccessRequestPolicy");
                return new ClientSecurityState(values);
            }
        }
    }
    /**
     * Identifies the script on the stack that caused a resource or element to be labeled as an ad. For resources, this indicates the context that triggered the fetch. For elements, this indicates the context that caused the element to be appended to the DOM.
     * <p><b>Experimental:</b> this part of CDP may change without notice.
     */
    public static final class AdScriptIdentifier extends CdpObject {
        private AdScriptIdentifier(Map<String, Object> values) { super(values); }
        @Nullable public static AdScriptIdentifier fromMap(@Nullable Map<String, Object> values) {
            return values == null ? null : new AdScriptIdentifier(values);
        }
        public static Builder builder() { return new Builder(); }
        /**
         * The script&#x27;s V8 identifier.
         * @return the protocol field value
         */
        @Nullable public String scriptId() {
            return (String) value("scriptId");
        }
        /**
         * V8&#x27;s debugging ID for the v8::Context.
         * @return the protocol field value
         */
        @Nullable public String debuggerId() {
            return (String) value("debuggerId");
        }
        /**
         * The script&#x27;s url (or generated name based on id if inline script).
         * @return the protocol field value
         */
        @Nullable public String name() {
            return (String) value("name");
        }
        public static final class Builder {
            private final Map<String, Object> values = new LinkedHashMap<>();
            /**
             * The script&#x27;s V8 identifier.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder scriptId(@Nullable String value) {
                if (value == null) values.remove("scriptId");
                else values.put("scriptId", jsonValue(value));
                return this;
            }
            /**
             * V8&#x27;s debugging ID for the v8::Context.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder debuggerId(@Nullable String value) {
                if (value == null) values.remove("debuggerId");
                else values.put("debuggerId", jsonValue(value));
                return this;
            }
            /**
             * The script&#x27;s url (or generated name based on id if inline script).
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder name(@Nullable String value) {
                if (value == null) values.remove("name");
                else values.put("name", jsonValue(value));
                return this;
            }
            public AdScriptIdentifier build() {
                if (!values.containsKey("scriptId")) throw new IllegalStateException("Missing required CDP field: scriptId");
                if (!values.containsKey("debuggerId")) throw new IllegalStateException("Missing required CDP field: debuggerId");
                if (!values.containsKey("name")) throw new IllegalStateException("Missing required CDP field: name");
                return new AdScriptIdentifier(values);
            }
        }
    }
    /**
     * Encapsulates the script ancestry and the root script filter list rule that caused the resource or element to be labeled as an ad.
     * <p><b>Experimental:</b> this part of CDP may change without notice.
     */
    public static final class AdAncestry extends CdpObject {
        private AdAncestry(Map<String, Object> values) { super(values); }
        @Nullable public static AdAncestry fromMap(@Nullable Map<String, Object> values) {
            return values == null ? null : new AdAncestry(values);
        }
        public static Builder builder() { return new Builder(); }
        /**
         * A chain of {@code AdScriptIdentifier}s representing the ancestry of an ad script that led to the creation of a resource or element. The chain is ordered from the script itself (lowest level) up to its root ancestor that was flagged by a filter list.
         * @return the protocol field value
         */
        @Nullable public java.util.List<Network.AdScriptIdentifier> ancestryChain() {
            return list(value("ancestryChain"), element0 -> Network.AdScriptIdentifier.fromMap(objectMap(element0)));
        }
        /**
         * The filter list rule that caused the root (last) script in {@code ancestryChain} to be tagged as an ad.
         * @return the protocol field value
         */
        @Nullable public String rootScriptFilterlistRule() {
            return (String) value("rootScriptFilterlistRule");
        }
        public static final class Builder {
            private final Map<String, Object> values = new LinkedHashMap<>();
            /**
             * A chain of {@code AdScriptIdentifier}s representing the ancestry of an ad script that led to the creation of a resource or element. The chain is ordered from the script itself (lowest level) up to its root ancestor that was flagged by a filter list.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder ancestryChain(@Nullable java.util.List<Network.AdScriptIdentifier> value) {
                if (value == null) values.remove("ancestryChain");
                else values.put("ancestryChain", jsonValue(value));
                return this;
            }
            /**
             * The filter list rule that caused the root (last) script in {@code ancestryChain} to be tagged as an ad.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder rootScriptFilterlistRule(@Nullable String value) {
                if (value == null) values.remove("rootScriptFilterlistRule");
                else values.put("rootScriptFilterlistRule", jsonValue(value));
                return this;
            }
            public AdAncestry build() {
                if (!values.containsKey("ancestryChain")) throw new IllegalStateException("Missing required CDP field: ancestryChain");
                return new AdAncestry(values);
            }
        }
    }
    /**
     * Represents the provenance of an ad resource or element. Only one of {@code filterlistRule} or {@code adScriptAncestry} can be set. If {@code filterlistRule} is provided, the resource URL directly matches a filter list rule. If {@code adScriptAncestry} is provided, an ad script initiated the resource fetch or appended the element to the DOM. If neither is provided, the entity is known to be an ad, but provenance tracking information is unavailable.
     * <p><b>Experimental:</b> this part of CDP may change without notice.
     */
    public static final class AdProvenance extends CdpObject {
        private AdProvenance(Map<String, Object> values) { super(values); }
        @Nullable public static AdProvenance fromMap(@Nullable Map<String, Object> values) {
            return values == null ? null : new AdProvenance(values);
        }
        public static Builder builder() { return new Builder(); }
        /**
         * The filterlist rule that matched, if any.
         * @return the protocol field value
         */
        @Nullable public String filterlistRule() {
            return (String) value("filterlistRule");
        }
        /**
         * The script ancestry that created the ad, if any.
         * @return the protocol field value
         */
        @Nullable public Network.AdAncestry adScriptAncestry() {
            return Network.AdAncestry.fromMap(objectMap(value("adScriptAncestry")));
        }
        public static final class Builder {
            private final Map<String, Object> values = new LinkedHashMap<>();
            /**
             * The filterlist rule that matched, if any.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder filterlistRule(@Nullable String value) {
                if (value == null) values.remove("filterlistRule");
                else values.put("filterlistRule", jsonValue(value));
                return this;
            }
            /**
             * The script ancestry that created the ad, if any.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder adScriptAncestry(@Nullable Network.AdAncestry value) {
                if (value == null) values.remove("adScriptAncestry");
                else values.put("adScriptAncestry", jsonValue(value));
                return this;
            }
            public AdProvenance build() {
                return new AdProvenance(values);
            }
        }
    }
    /**
     * Wire values for CrossOriginOpenerPolicyValue.
     * <p><b>Experimental:</b> this part of CDP may change without notice.
     */
    public static final class CrossOriginOpenerPolicyValue {
        private CrossOriginOpenerPolicyValue() {}
        public static final String SAMEORIGIN = "SameOrigin";
        public static final String SAMEORIGINALLOWPOPUPS = "SameOriginAllowPopups";
        public static final String RESTRICTPROPERTIES = "RestrictProperties";
        public static final String UNSAFENONE = "UnsafeNone";
        public static final String SAMEORIGINPLUSCOEP = "SameOriginPlusCoep";
        public static final String RESTRICTPROPERTIESPLUSCOEP = "RestrictPropertiesPlusCoep";
        public static final String NOOPENERALLOWPOPUPS = "NoopenerAllowPopups";
    }
    /**
     * <p><b>Experimental:</b> this part of CDP may change without notice.
     */
    public static final class CrossOriginOpenerPolicyStatus extends CdpObject {
        private CrossOriginOpenerPolicyStatus(Map<String, Object> values) { super(values); }
        @Nullable public static CrossOriginOpenerPolicyStatus fromMap(@Nullable Map<String, Object> values) {
            return values == null ? null : new CrossOriginOpenerPolicyStatus(values);
        }
        public static Builder builder() { return new Builder(); }
        /**
         * Returns the value field.
         * @return the protocol field value
         */
        @Nullable public String value() {
            return (String) value("value");
        }
        /**
         * Returns the reportOnlyValue field.
         * @return the protocol field value
         */
        @Nullable public String reportOnlyValue() {
            return (String) value("reportOnlyValue");
        }
        /**
         * Returns the reportingEndpoint field.
         * @return the protocol field value
         */
        @Nullable public String reportingEndpoint() {
            return (String) value("reportingEndpoint");
        }
        /**
         * Returns the reportOnlyReportingEndpoint field.
         * @return the protocol field value
         */
        @Nullable public String reportOnlyReportingEndpoint() {
            return (String) value("reportOnlyReportingEndpoint");
        }
        public static final class Builder {
            private final Map<String, Object> values = new LinkedHashMap<>();
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
            /**
             * Sets the reportOnlyValue field.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder reportOnlyValue(@Nullable String value) {
                if (value == null) values.remove("reportOnlyValue");
                else values.put("reportOnlyValue", jsonValue(value));
                return this;
            }
            /**
             * Sets the reportingEndpoint field.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder reportingEndpoint(@Nullable String value) {
                if (value == null) values.remove("reportingEndpoint");
                else values.put("reportingEndpoint", jsonValue(value));
                return this;
            }
            /**
             * Sets the reportOnlyReportingEndpoint field.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder reportOnlyReportingEndpoint(@Nullable String value) {
                if (value == null) values.remove("reportOnlyReportingEndpoint");
                else values.put("reportOnlyReportingEndpoint", jsonValue(value));
                return this;
            }
            public CrossOriginOpenerPolicyStatus build() {
                if (!values.containsKey("value")) throw new IllegalStateException("Missing required CDP field: value");
                if (!values.containsKey("reportOnlyValue")) throw new IllegalStateException("Missing required CDP field: reportOnlyValue");
                return new CrossOriginOpenerPolicyStatus(values);
            }
        }
    }
    /**
     * Wire values for CrossOriginEmbedderPolicyValue.
     * <p><b>Experimental:</b> this part of CDP may change without notice.
     */
    public static final class CrossOriginEmbedderPolicyValue {
        private CrossOriginEmbedderPolicyValue() {}
        public static final String NONE = "None";
        public static final String CREDENTIALLESS = "Credentialless";
        public static final String REQUIRECORP = "RequireCorp";
    }
    /**
     * <p><b>Experimental:</b> this part of CDP may change without notice.
     */
    public static final class CrossOriginEmbedderPolicyStatus extends CdpObject {
        private CrossOriginEmbedderPolicyStatus(Map<String, Object> values) { super(values); }
        @Nullable public static CrossOriginEmbedderPolicyStatus fromMap(@Nullable Map<String, Object> values) {
            return values == null ? null : new CrossOriginEmbedderPolicyStatus(values);
        }
        public static Builder builder() { return new Builder(); }
        /**
         * Returns the value field.
         * @return the protocol field value
         */
        @Nullable public String value() {
            return (String) value("value");
        }
        /**
         * Returns the reportOnlyValue field.
         * @return the protocol field value
         */
        @Nullable public String reportOnlyValue() {
            return (String) value("reportOnlyValue");
        }
        /**
         * Returns the reportingEndpoint field.
         * @return the protocol field value
         */
        @Nullable public String reportingEndpoint() {
            return (String) value("reportingEndpoint");
        }
        /**
         * Returns the reportOnlyReportingEndpoint field.
         * @return the protocol field value
         */
        @Nullable public String reportOnlyReportingEndpoint() {
            return (String) value("reportOnlyReportingEndpoint");
        }
        public static final class Builder {
            private final Map<String, Object> values = new LinkedHashMap<>();
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
            /**
             * Sets the reportOnlyValue field.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder reportOnlyValue(@Nullable String value) {
                if (value == null) values.remove("reportOnlyValue");
                else values.put("reportOnlyValue", jsonValue(value));
                return this;
            }
            /**
             * Sets the reportingEndpoint field.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder reportingEndpoint(@Nullable String value) {
                if (value == null) values.remove("reportingEndpoint");
                else values.put("reportingEndpoint", jsonValue(value));
                return this;
            }
            /**
             * Sets the reportOnlyReportingEndpoint field.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder reportOnlyReportingEndpoint(@Nullable String value) {
                if (value == null) values.remove("reportOnlyReportingEndpoint");
                else values.put("reportOnlyReportingEndpoint", jsonValue(value));
                return this;
            }
            public CrossOriginEmbedderPolicyStatus build() {
                if (!values.containsKey("value")) throw new IllegalStateException("Missing required CDP field: value");
                if (!values.containsKey("reportOnlyValue")) throw new IllegalStateException("Missing required CDP field: reportOnlyValue");
                return new CrossOriginEmbedderPolicyStatus(values);
            }
        }
    }
    /**
     * Wire values for ContentSecurityPolicySource.
     * <p><b>Experimental:</b> this part of CDP may change without notice.
     */
    public static final class ContentSecurityPolicySource {
        private ContentSecurityPolicySource() {}
        public static final String HTTP = "HTTP";
        public static final String META = "Meta";
    }
    /**
     * <p><b>Experimental:</b> this part of CDP may change without notice.
     */
    public static final class ContentSecurityPolicyStatus extends CdpObject {
        private ContentSecurityPolicyStatus(Map<String, Object> values) { super(values); }
        @Nullable public static ContentSecurityPolicyStatus fromMap(@Nullable Map<String, Object> values) {
            return values == null ? null : new ContentSecurityPolicyStatus(values);
        }
        public static Builder builder() { return new Builder(); }
        /**
         * Returns the effectiveDirectives field.
         * @return the protocol field value
         */
        @Nullable public String effectiveDirectives() {
            return (String) value("effectiveDirectives");
        }
        /**
         * Returns the isEnforced field.
         * @return the protocol field value
         */
        @Nullable public Boolean isEnforced() {
            return (Boolean) value("isEnforced");
        }
        /**
         * Returns the source field.
         * @return the protocol field value
         */
        @Nullable public String source() {
            return (String) value("source");
        }
        public static final class Builder {
            private final Map<String, Object> values = new LinkedHashMap<>();
            /**
             * Sets the effectiveDirectives field.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder effectiveDirectives(@Nullable String value) {
                if (value == null) values.remove("effectiveDirectives");
                else values.put("effectiveDirectives", jsonValue(value));
                return this;
            }
            /**
             * Sets the isEnforced field.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder isEnforced(@Nullable Boolean value) {
                if (value == null) values.remove("isEnforced");
                else values.put("isEnforced", jsonValue(value));
                return this;
            }
            /**
             * Sets the source field.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder source(@Nullable String value) {
                if (value == null) values.remove("source");
                else values.put("source", jsonValue(value));
                return this;
            }
            public ContentSecurityPolicyStatus build() {
                if (!values.containsKey("effectiveDirectives")) throw new IllegalStateException("Missing required CDP field: effectiveDirectives");
                if (!values.containsKey("isEnforced")) throw new IllegalStateException("Missing required CDP field: isEnforced");
                if (!values.containsKey("source")) throw new IllegalStateException("Missing required CDP field: source");
                return new ContentSecurityPolicyStatus(values);
            }
        }
    }
    /**
     * <p><b>Experimental:</b> this part of CDP may change without notice.
     */
    public static final class SecurityIsolationStatus extends CdpObject {
        private SecurityIsolationStatus(Map<String, Object> values) { super(values); }
        @Nullable public static SecurityIsolationStatus fromMap(@Nullable Map<String, Object> values) {
            return values == null ? null : new SecurityIsolationStatus(values);
        }
        public static Builder builder() { return new Builder(); }
        /**
         * Returns the coop field.
         * @return the protocol field value
         */
        @Nullable public Network.CrossOriginOpenerPolicyStatus coop() {
            return Network.CrossOriginOpenerPolicyStatus.fromMap(objectMap(value("coop")));
        }
        /**
         * Returns the coep field.
         * @return the protocol field value
         */
        @Nullable public Network.CrossOriginEmbedderPolicyStatus coep() {
            return Network.CrossOriginEmbedderPolicyStatus.fromMap(objectMap(value("coep")));
        }
        /**
         * Returns the csp field.
         * @return the protocol field value
         */
        @Nullable public java.util.List<Network.ContentSecurityPolicyStatus> csp() {
            return list(value("csp"), element0 -> Network.ContentSecurityPolicyStatus.fromMap(objectMap(element0)));
        }
        public static final class Builder {
            private final Map<String, Object> values = new LinkedHashMap<>();
            /**
             * Sets the coop field.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder coop(@Nullable Network.CrossOriginOpenerPolicyStatus value) {
                if (value == null) values.remove("coop");
                else values.put("coop", jsonValue(value));
                return this;
            }
            /**
             * Sets the coep field.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder coep(@Nullable Network.CrossOriginEmbedderPolicyStatus value) {
                if (value == null) values.remove("coep");
                else values.put("coep", jsonValue(value));
                return this;
            }
            /**
             * Sets the csp field.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder csp(@Nullable java.util.List<Network.ContentSecurityPolicyStatus> value) {
                if (value == null) values.remove("csp");
                else values.put("csp", jsonValue(value));
                return this;
            }
            public SecurityIsolationStatus build() {
                return new SecurityIsolationStatus(values);
            }
        }
    }
    /**
     * The status of a Reporting API report.
     * <p><b>Experimental:</b> this part of CDP may change without notice.
     */
    public static final class ReportStatus {
        private ReportStatus() {}
        public static final String QUEUED = "Queued";
        public static final String PENDING = "Pending";
        public static final String MARKEDFORREMOVAL = "MarkedForRemoval";
        public static final String SUCCESS = "Success";
    }
    /**
     * An object representing a report generated by the Reporting API.
     * <p><b>Experimental:</b> this part of CDP may change without notice.
     */
    public static final class ReportingApiReport extends CdpObject {
        private ReportingApiReport(Map<String, Object> values) { super(values); }
        @Nullable public static ReportingApiReport fromMap(@Nullable Map<String, Object> values) {
            return values == null ? null : new ReportingApiReport(values);
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
         * The URL of the document that triggered the report.
         * @return the protocol field value
         */
        @Nullable public String initiatorUrl() {
            return (String) value("initiatorUrl");
        }
        /**
         * The name of the endpoint group that should be used to deliver the report.
         * @return the protocol field value
         */
        @Nullable public String destination() {
            return (String) value("destination");
        }
        /**
         * The type of the report (specifies the set of data that is contained in the report body).
         * @return the protocol field value
         */
        @Nullable public String type() {
            return (String) value("type");
        }
        /**
         * When the report was generated.
         * @return the protocol field value
         */
        @Nullable public Double timestamp() {
            return numberAsDouble(value("timestamp"));
        }
        /**
         * How many uploads deep the related request was.
         * @return the protocol field value
         */
        @Nullable public Long depth() {
            return numberAsLong(value("depth"));
        }
        /**
         * The number of delivery attempts made so far, not including an active attempt.
         * @return the protocol field value
         */
        @Nullable public Long completedAttempts() {
            return numberAsLong(value("completedAttempts"));
        }
        /**
         * Returns the body field.
         * @return the protocol field value
         */
        @Nullable public java.util.Map<String, Object> body() {
            return objectMap(value("body"));
        }
        /**
         * Returns the status field.
         * @return the protocol field value
         */
        @Nullable public String status() {
            return (String) value("status");
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
             * The URL of the document that triggered the report.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder initiatorUrl(@Nullable String value) {
                if (value == null) values.remove("initiatorUrl");
                else values.put("initiatorUrl", jsonValue(value));
                return this;
            }
            /**
             * The name of the endpoint group that should be used to deliver the report.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder destination(@Nullable String value) {
                if (value == null) values.remove("destination");
                else values.put("destination", jsonValue(value));
                return this;
            }
            /**
             * The type of the report (specifies the set of data that is contained in the report body).
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder type(@Nullable String value) {
                if (value == null) values.remove("type");
                else values.put("type", jsonValue(value));
                return this;
            }
            /**
             * When the report was generated.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder timestamp(@Nullable Double value) {
                if (value == null) values.remove("timestamp");
                else values.put("timestamp", jsonValue(value));
                return this;
            }
            /**
             * How many uploads deep the related request was.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder depth(@Nullable Long value) {
                if (value == null) values.remove("depth");
                else values.put("depth", jsonValue(value));
                return this;
            }
            /**
             * The number of delivery attempts made so far, not including an active attempt.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder completedAttempts(@Nullable Long value) {
                if (value == null) values.remove("completedAttempts");
                else values.put("completedAttempts", jsonValue(value));
                return this;
            }
            /**
             * Sets the body field.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder body(@Nullable java.util.Map<String, Object> value) {
                if (value == null) values.remove("body");
                else values.put("body", jsonValue(value));
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
            public ReportingApiReport build() {
                if (!values.containsKey("id")) throw new IllegalStateException("Missing required CDP field: id");
                if (!values.containsKey("initiatorUrl")) throw new IllegalStateException("Missing required CDP field: initiatorUrl");
                if (!values.containsKey("destination")) throw new IllegalStateException("Missing required CDP field: destination");
                if (!values.containsKey("type")) throw new IllegalStateException("Missing required CDP field: type");
                if (!values.containsKey("timestamp")) throw new IllegalStateException("Missing required CDP field: timestamp");
                if (!values.containsKey("depth")) throw new IllegalStateException("Missing required CDP field: depth");
                if (!values.containsKey("completedAttempts")) throw new IllegalStateException("Missing required CDP field: completedAttempts");
                if (!values.containsKey("body")) throw new IllegalStateException("Missing required CDP field: body");
                if (!values.containsKey("status")) throw new IllegalStateException("Missing required CDP field: status");
                return new ReportingApiReport(values);
            }
        }
    }
    /**
     * <p><b>Experimental:</b> this part of CDP may change without notice.
     */
    public static final class ReportingApiEndpoint extends CdpObject {
        private ReportingApiEndpoint(Map<String, Object> values) { super(values); }
        @Nullable public static ReportingApiEndpoint fromMap(@Nullable Map<String, Object> values) {
            return values == null ? null : new ReportingApiEndpoint(values);
        }
        public static Builder builder() { return new Builder(); }
        /**
         * The URL of the endpoint to which reports may be delivered.
         * @return the protocol field value
         */
        @Nullable public String url() {
            return (String) value("url");
        }
        /**
         * Name of the endpoint group.
         * @return the protocol field value
         */
        @Nullable public String groupName() {
            return (String) value("groupName");
        }
        public static final class Builder {
            private final Map<String, Object> values = new LinkedHashMap<>();
            /**
             * The URL of the endpoint to which reports may be delivered.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder url(@Nullable String value) {
                if (value == null) values.remove("url");
                else values.put("url", jsonValue(value));
                return this;
            }
            /**
             * Name of the endpoint group.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder groupName(@Nullable String value) {
                if (value == null) values.remove("groupName");
                else values.put("groupName", jsonValue(value));
                return this;
            }
            public ReportingApiEndpoint build() {
                if (!values.containsKey("url")) throw new IllegalStateException("Missing required CDP field: url");
                if (!values.containsKey("groupName")) throw new IllegalStateException("Missing required CDP field: groupName");
                return new ReportingApiEndpoint(values);
            }
        }
    }
    /**
     * Unique identifier for a device bound session.
     * <p><b>Experimental:</b> this part of CDP may change without notice.
     */
    public static final class DeviceBoundSessionKey extends CdpObject {
        private DeviceBoundSessionKey(Map<String, Object> values) { super(values); }
        @Nullable public static DeviceBoundSessionKey fromMap(@Nullable Map<String, Object> values) {
            return values == null ? null : new DeviceBoundSessionKey(values);
        }
        public static Builder builder() { return new Builder(); }
        /**
         * The site the session is set up for.
         * @return the protocol field value
         */
        @Nullable public String site() {
            return (String) value("site");
        }
        /**
         * The id of the session.
         * @return the protocol field value
         */
        @Nullable public String id() {
            return (String) value("id");
        }
        public static final class Builder {
            private final Map<String, Object> values = new LinkedHashMap<>();
            /**
             * The site the session is set up for.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder site(@Nullable String value) {
                if (value == null) values.remove("site");
                else values.put("site", jsonValue(value));
                return this;
            }
            /**
             * The id of the session.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder id(@Nullable String value) {
                if (value == null) values.remove("id");
                else values.put("id", jsonValue(value));
                return this;
            }
            public DeviceBoundSessionKey build() {
                if (!values.containsKey("site")) throw new IllegalStateException("Missing required CDP field: site");
                if (!values.containsKey("id")) throw new IllegalStateException("Missing required CDP field: id");
                return new DeviceBoundSessionKey(values);
            }
        }
    }
    /**
     * How a device bound session was used during a request.
     * <p><b>Experimental:</b> this part of CDP may change without notice.
     */
    public static final class DeviceBoundSessionWithUsage extends CdpObject {
        private DeviceBoundSessionWithUsage(Map<String, Object> values) { super(values); }
        @Nullable public static DeviceBoundSessionWithUsage fromMap(@Nullable Map<String, Object> values) {
            return values == null ? null : new DeviceBoundSessionWithUsage(values);
        }
        public static Builder builder() { return new Builder(); }
        /**
         * The key for the session.
         * @return the protocol field value
         */
        @Nullable public Network.DeviceBoundSessionKey sessionKey() {
            return Network.DeviceBoundSessionKey.fromMap(objectMap(value("sessionKey")));
        }
        /**
         * How the session was used (or not used).
         * @return the protocol field value
         */
        @Nullable public String usage() {
            return (String) value("usage");
        }
        /**
         * How the session was used (or not used).
         */
        public static final class UsageValues {
            private UsageValues() {}
            public static final String NOTINSCOPE = "NotInScope";
            public static final String INSCOPEREFRESHNOTYETNEEDED = "InScopeRefreshNotYetNeeded";
            public static final String INSCOPEREFRESHNOTALLOWED = "InScopeRefreshNotAllowed";
            public static final String PROACTIVEREFRESHNOTPOSSIBLE = "ProactiveRefreshNotPossible";
            public static final String PROACTIVEREFRESHATTEMPTED = "ProactiveRefreshAttempted";
            public static final String DEFERRED = "Deferred";
        }
        public static final class Builder {
            private final Map<String, Object> values = new LinkedHashMap<>();
            /**
             * The key for the session.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder sessionKey(@Nullable Network.DeviceBoundSessionKey value) {
                if (value == null) values.remove("sessionKey");
                else values.put("sessionKey", jsonValue(value));
                return this;
            }
            /**
             * How the session was used (or not used).
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder usage(@Nullable String value) {
                if (value == null) values.remove("usage");
                else values.put("usage", jsonValue(value));
                return this;
            }
            public DeviceBoundSessionWithUsage build() {
                if (!values.containsKey("sessionKey")) throw new IllegalStateException("Missing required CDP field: sessionKey");
                if (!values.containsKey("usage")) throw new IllegalStateException("Missing required CDP field: usage");
                return new DeviceBoundSessionWithUsage(values);
            }
        }
    }
    /**
     * A device bound session&#x27;s cookie craving.
     * <p><b>Experimental:</b> this part of CDP may change without notice.
     */
    public static final class DeviceBoundSessionCookieCraving extends CdpObject {
        private DeviceBoundSessionCookieCraving(Map<String, Object> values) { super(values); }
        @Nullable public static DeviceBoundSessionCookieCraving fromMap(@Nullable Map<String, Object> values) {
            return values == null ? null : new DeviceBoundSessionCookieCraving(values);
        }
        public static Builder builder() { return new Builder(); }
        /**
         * The name of the craving.
         * @return the protocol field value
         */
        @Nullable public String name() {
            return (String) value("name");
        }
        /**
         * The domain of the craving.
         * @return the protocol field value
         */
        @Nullable public String domain() {
            return (String) value("domain");
        }
        /**
         * The path of the craving.
         * @return the protocol field value
         */
        @Nullable public String path() {
            return (String) value("path");
        }
        /**
         * The {@code Secure} attribute of the craving attributes.
         * @return the protocol field value
         */
        @Nullable public Boolean secure() {
            return (Boolean) value("secure");
        }
        /**
         * The {@code HttpOnly} attribute of the craving attributes.
         * @return the protocol field value
         */
        @Nullable public Boolean httpOnly() {
            return (Boolean) value("httpOnly");
        }
        /**
         * The {@code SameSite} attribute of the craving attributes.
         * @return the protocol field value
         */
        @Nullable public String sameSite() {
            return (String) value("sameSite");
        }
        public static final class Builder {
            private final Map<String, Object> values = new LinkedHashMap<>();
            /**
             * The name of the craving.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder name(@Nullable String value) {
                if (value == null) values.remove("name");
                else values.put("name", jsonValue(value));
                return this;
            }
            /**
             * The domain of the craving.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder domain(@Nullable String value) {
                if (value == null) values.remove("domain");
                else values.put("domain", jsonValue(value));
                return this;
            }
            /**
             * The path of the craving.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder path(@Nullable String value) {
                if (value == null) values.remove("path");
                else values.put("path", jsonValue(value));
                return this;
            }
            /**
             * The {@code Secure} attribute of the craving attributes.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder secure(@Nullable Boolean value) {
                if (value == null) values.remove("secure");
                else values.put("secure", jsonValue(value));
                return this;
            }
            /**
             * The {@code HttpOnly} attribute of the craving attributes.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder httpOnly(@Nullable Boolean value) {
                if (value == null) values.remove("httpOnly");
                else values.put("httpOnly", jsonValue(value));
                return this;
            }
            /**
             * The {@code SameSite} attribute of the craving attributes.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder sameSite(@Nullable String value) {
                if (value == null) values.remove("sameSite");
                else values.put("sameSite", jsonValue(value));
                return this;
            }
            public DeviceBoundSessionCookieCraving build() {
                if (!values.containsKey("name")) throw new IllegalStateException("Missing required CDP field: name");
                if (!values.containsKey("domain")) throw new IllegalStateException("Missing required CDP field: domain");
                if (!values.containsKey("path")) throw new IllegalStateException("Missing required CDP field: path");
                if (!values.containsKey("secure")) throw new IllegalStateException("Missing required CDP field: secure");
                if (!values.containsKey("httpOnly")) throw new IllegalStateException("Missing required CDP field: httpOnly");
                return new DeviceBoundSessionCookieCraving(values);
            }
        }
    }
    /**
     * A device bound session&#x27;s inclusion URL rule.
     * <p><b>Experimental:</b> this part of CDP may change without notice.
     */
    public static final class DeviceBoundSessionUrlRule extends CdpObject {
        private DeviceBoundSessionUrlRule(Map<String, Object> values) { super(values); }
        @Nullable public static DeviceBoundSessionUrlRule fromMap(@Nullable Map<String, Object> values) {
            return values == null ? null : new DeviceBoundSessionUrlRule(values);
        }
        public static Builder builder() { return new Builder(); }
        /**
         * See comments on {@code net::device_bound_sessions::SessionInclusionRules::UrlRule::rule_type}.
         * @return the protocol field value
         */
        @Nullable public String ruleType() {
            return (String) value("ruleType");
        }
        /**
         * See comments on {@code net::device_bound_sessions::SessionInclusionRules::UrlRule::rule_type}.
         */
        public static final class RuleTypeValues {
            private RuleTypeValues() {}
            public static final String EXCLUDE = "Exclude";
            public static final String INCLUDE = "Include";
        }
        /**
         * See comments on {@code net::device_bound_sessions::SessionInclusionRules::UrlRule::host_pattern}.
         * @return the protocol field value
         */
        @Nullable public String hostPattern() {
            return (String) value("hostPattern");
        }
        /**
         * See comments on {@code net::device_bound_sessions::SessionInclusionRules::UrlRule::path_prefix}.
         * @return the protocol field value
         */
        @Nullable public String pathPrefix() {
            return (String) value("pathPrefix");
        }
        public static final class Builder {
            private final Map<String, Object> values = new LinkedHashMap<>();
            /**
             * See comments on {@code net::device_bound_sessions::SessionInclusionRules::UrlRule::rule_type}.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder ruleType(@Nullable String value) {
                if (value == null) values.remove("ruleType");
                else values.put("ruleType", jsonValue(value));
                return this;
            }
            /**
             * See comments on {@code net::device_bound_sessions::SessionInclusionRules::UrlRule::host_pattern}.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder hostPattern(@Nullable String value) {
                if (value == null) values.remove("hostPattern");
                else values.put("hostPattern", jsonValue(value));
                return this;
            }
            /**
             * See comments on {@code net::device_bound_sessions::SessionInclusionRules::UrlRule::path_prefix}.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder pathPrefix(@Nullable String value) {
                if (value == null) values.remove("pathPrefix");
                else values.put("pathPrefix", jsonValue(value));
                return this;
            }
            public DeviceBoundSessionUrlRule build() {
                if (!values.containsKey("ruleType")) throw new IllegalStateException("Missing required CDP field: ruleType");
                if (!values.containsKey("hostPattern")) throw new IllegalStateException("Missing required CDP field: hostPattern");
                if (!values.containsKey("pathPrefix")) throw new IllegalStateException("Missing required CDP field: pathPrefix");
                return new DeviceBoundSessionUrlRule(values);
            }
        }
    }
    /**
     * A device bound session&#x27;s inclusion rules.
     * <p><b>Experimental:</b> this part of CDP may change without notice.
     */
    public static final class DeviceBoundSessionInclusionRules extends CdpObject {
        private DeviceBoundSessionInclusionRules(Map<String, Object> values) { super(values); }
        @Nullable public static DeviceBoundSessionInclusionRules fromMap(@Nullable Map<String, Object> values) {
            return values == null ? null : new DeviceBoundSessionInclusionRules(values);
        }
        public static Builder builder() { return new Builder(); }
        /**
         * See comments on {@code net::device_bound_sessions::SessionInclusionRules::origin_}.
         * @return the protocol field value
         */
        @Nullable public String origin() {
            return (String) value("origin");
        }
        /**
         * Whether the whole site is included. See comments on {@code net::device_bound_sessions::SessionInclusionRules::include_site_} for more details; this boolean is true if that value is populated.
         * @return the protocol field value
         */
        @Nullable public Boolean includeSite() {
            return (Boolean) value("includeSite");
        }
        /**
         * See comments on {@code net::device_bound_sessions::SessionInclusionRules::url_rules_}.
         * @return the protocol field value
         */
        @Nullable public java.util.List<Network.DeviceBoundSessionUrlRule> urlRules() {
            return list(value("urlRules"), element0 -> Network.DeviceBoundSessionUrlRule.fromMap(objectMap(element0)));
        }
        public static final class Builder {
            private final Map<String, Object> values = new LinkedHashMap<>();
            /**
             * See comments on {@code net::device_bound_sessions::SessionInclusionRules::origin_}.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder origin(@Nullable String value) {
                if (value == null) values.remove("origin");
                else values.put("origin", jsonValue(value));
                return this;
            }
            /**
             * Whether the whole site is included. See comments on {@code net::device_bound_sessions::SessionInclusionRules::include_site_} for more details; this boolean is true if that value is populated.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder includeSite(@Nullable Boolean value) {
                if (value == null) values.remove("includeSite");
                else values.put("includeSite", jsonValue(value));
                return this;
            }
            /**
             * See comments on {@code net::device_bound_sessions::SessionInclusionRules::url_rules_}.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder urlRules(@Nullable java.util.List<Network.DeviceBoundSessionUrlRule> value) {
                if (value == null) values.remove("urlRules");
                else values.put("urlRules", jsonValue(value));
                return this;
            }
            public DeviceBoundSessionInclusionRules build() {
                if (!values.containsKey("origin")) throw new IllegalStateException("Missing required CDP field: origin");
                if (!values.containsKey("includeSite")) throw new IllegalStateException("Missing required CDP field: includeSite");
                if (!values.containsKey("urlRules")) throw new IllegalStateException("Missing required CDP field: urlRules");
                return new DeviceBoundSessionInclusionRules(values);
            }
        }
    }
    /**
     * A device bound session.
     * <p><b>Experimental:</b> this part of CDP may change without notice.
     */
    public static final class DeviceBoundSession extends CdpObject {
        private DeviceBoundSession(Map<String, Object> values) { super(values); }
        @Nullable public static DeviceBoundSession fromMap(@Nullable Map<String, Object> values) {
            return values == null ? null : new DeviceBoundSession(values);
        }
        public static Builder builder() { return new Builder(); }
        /**
         * The site and session ID of the session.
         * @return the protocol field value
         */
        @Nullable public Network.DeviceBoundSessionKey key() {
            return Network.DeviceBoundSessionKey.fromMap(objectMap(value("key")));
        }
        /**
         * See comments on {@code net::device_bound_sessions::Session::refresh_url_}.
         * @return the protocol field value
         */
        @Nullable public String refreshUrl() {
            return (String) value("refreshUrl");
        }
        /**
         * See comments on {@code net::device_bound_sessions::Session::inclusion_rules_}.
         * @return the protocol field value
         */
        @Nullable public Network.DeviceBoundSessionInclusionRules inclusionRules() {
            return Network.DeviceBoundSessionInclusionRules.fromMap(objectMap(value("inclusionRules")));
        }
        /**
         * See comments on {@code net::device_bound_sessions::Session::cookie_cravings_}.
         * @return the protocol field value
         */
        @Nullable public java.util.List<Network.DeviceBoundSessionCookieCraving> cookieCravings() {
            return list(value("cookieCravings"), element0 -> Network.DeviceBoundSessionCookieCraving.fromMap(objectMap(element0)));
        }
        /**
         * See comments on {@code net::device_bound_sessions::Session::expiry_date_}.
         * @return the protocol field value
         */
        @Nullable public Double expiryDate() {
            return numberAsDouble(value("expiryDate"));
        }
        /**
         * See comments on {@code net::device_bound_sessions::Session::cached_challenge__}.
         * @return the protocol field value
         */
        @Nullable public String cachedChallenge() {
            return (String) value("cachedChallenge");
        }
        /**
         * See comments on {@code net::device_bound_sessions::Session::allowed_refresh_initiators_}.
         * @return the protocol field value
         */
        @Nullable public java.util.List<String> allowedRefreshInitiators() {
            return list(value("allowedRefreshInitiators"), element0 -> (String) element0);
        }
        public static final class Builder {
            private final Map<String, Object> values = new LinkedHashMap<>();
            /**
             * The site and session ID of the session.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder key(@Nullable Network.DeviceBoundSessionKey value) {
                if (value == null) values.remove("key");
                else values.put("key", jsonValue(value));
                return this;
            }
            /**
             * See comments on {@code net::device_bound_sessions::Session::refresh_url_}.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder refreshUrl(@Nullable String value) {
                if (value == null) values.remove("refreshUrl");
                else values.put("refreshUrl", jsonValue(value));
                return this;
            }
            /**
             * See comments on {@code net::device_bound_sessions::Session::inclusion_rules_}.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder inclusionRules(@Nullable Network.DeviceBoundSessionInclusionRules value) {
                if (value == null) values.remove("inclusionRules");
                else values.put("inclusionRules", jsonValue(value));
                return this;
            }
            /**
             * See comments on {@code net::device_bound_sessions::Session::cookie_cravings_}.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder cookieCravings(@Nullable java.util.List<Network.DeviceBoundSessionCookieCraving> value) {
                if (value == null) values.remove("cookieCravings");
                else values.put("cookieCravings", jsonValue(value));
                return this;
            }
            /**
             * See comments on {@code net::device_bound_sessions::Session::expiry_date_}.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder expiryDate(@Nullable Double value) {
                if (value == null) values.remove("expiryDate");
                else values.put("expiryDate", jsonValue(value));
                return this;
            }
            /**
             * See comments on {@code net::device_bound_sessions::Session::cached_challenge__}.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder cachedChallenge(@Nullable String value) {
                if (value == null) values.remove("cachedChallenge");
                else values.put("cachedChallenge", jsonValue(value));
                return this;
            }
            /**
             * See comments on {@code net::device_bound_sessions::Session::allowed_refresh_initiators_}.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder allowedRefreshInitiators(@Nullable java.util.List<String> value) {
                if (value == null) values.remove("allowedRefreshInitiators");
                else values.put("allowedRefreshInitiators", jsonValue(value));
                return this;
            }
            public DeviceBoundSession build() {
                if (!values.containsKey("key")) throw new IllegalStateException("Missing required CDP field: key");
                if (!values.containsKey("refreshUrl")) throw new IllegalStateException("Missing required CDP field: refreshUrl");
                if (!values.containsKey("inclusionRules")) throw new IllegalStateException("Missing required CDP field: inclusionRules");
                if (!values.containsKey("cookieCravings")) throw new IllegalStateException("Missing required CDP field: cookieCravings");
                if (!values.containsKey("expiryDate")) throw new IllegalStateException("Missing required CDP field: expiryDate");
                if (!values.containsKey("allowedRefreshInitiators")) throw new IllegalStateException("Missing required CDP field: allowedRefreshInitiators");
                return new DeviceBoundSession(values);
            }
        }
    }
    /**
     * A fetch result for a device bound session creation or refresh.
     * <p><b>Experimental:</b> this part of CDP may change without notice.
     */
    public static final class DeviceBoundSessionFetchResult {
        private DeviceBoundSessionFetchResult() {}
        public static final String SUCCESS = "Success";
        public static final String KEYERROR = "KeyError";
        public static final String SIGNINGERROR = "SigningError";
        public static final String TRANSIENTSIGNINGERROR = "TransientSigningError";
        public static final String SERVERREQUESTEDTERMINATION = "ServerRequestedTermination";
        public static final String INVALIDSESSIONID = "InvalidSessionId";
        public static final String INVALIDCHALLENGE = "InvalidChallenge";
        public static final String TOOMANYCHALLENGES = "TooManyChallenges";
        public static final String INVALIDFETCHERURL = "InvalidFetcherUrl";
        public static final String INVALIDREFRESHURL = "InvalidRefreshUrl";
        public static final String TRANSIENTHTTPERROR = "TransientHttpError";
        public static final String SCOPEORIGINSAMESITEMISMATCH = "ScopeOriginSameSiteMismatch";
        public static final String REFRESHURLSAMESITEMISMATCH = "RefreshUrlSameSiteMismatch";
        public static final String MISMATCHEDSESSIONID = "MismatchedSessionId";
        public static final String MISSINGSCOPE = "MissingScope";
        public static final String NOCREDENTIALS = "NoCredentials";
        public static final String SUBDOMAINREGISTRATIONWELLKNOWNUNAVAILABLE = "SubdomainRegistrationWellKnownUnavailable";
        public static final String SUBDOMAINREGISTRATIONUNAUTHORIZED = "SubdomainRegistrationUnauthorized";
        public static final String SUBDOMAINREGISTRATIONWELLKNOWNMALFORMED = "SubdomainRegistrationWellKnownMalformed";
        public static final String SESSIONPROVIDERWELLKNOWNUNAVAILABLE = "SessionProviderWellKnownUnavailable";
        public static final String RELYINGPARTYWELLKNOWNUNAVAILABLE = "RelyingPartyWellKnownUnavailable";
        public static final String FEDERATEDKEYTHUMBPRINTMISMATCH = "FederatedKeyThumbprintMismatch";
        public static final String INVALIDFEDERATEDSESSIONURL = "InvalidFederatedSessionUrl";
        public static final String INVALIDFEDERATEDKEY = "InvalidFederatedKey";
        public static final String TOOMANYRELYINGORIGINLABELS = "TooManyRelyingOriginLabels";
        public static final String BOUNDCOOKIESETFORBIDDEN = "BoundCookieSetForbidden";
        public static final String NETERROR = "NetError";
        public static final String PROXYERROR = "ProxyError";
        public static final String EMPTYSESSIONCONFIG = "EmptySessionConfig";
        public static final String INVALIDCREDENTIALSCONFIG = "InvalidCredentialsConfig";
        public static final String INVALIDCREDENTIALSTYPE = "InvalidCredentialsType";
        public static final String INVALIDCREDENTIALSEMPTYNAME = "InvalidCredentialsEmptyName";
        public static final String INVALIDCREDENTIALSCOOKIE = "InvalidCredentialsCookie";
        public static final String PERSISTENTHTTPERROR = "PersistentHttpError";
        public static final String REGISTRATIONATTEMPTEDCHALLENGE = "RegistrationAttemptedChallenge";
        public static final String INVALIDSCOPEORIGIN = "InvalidScopeOrigin";
        public static final String SCOPEORIGINCONTAINSPATH = "ScopeOriginContainsPath";
        public static final String REFRESHINITIATORNOTSTRING = "RefreshInitiatorNotString";
        public static final String REFRESHINITIATORINVALIDHOSTPATTERN = "RefreshInitiatorInvalidHostPattern";
        public static final String INVALIDSCOPESPECIFICATION = "InvalidScopeSpecification";
        public static final String MISSINGSCOPESPECIFICATIONTYPE = "MissingScopeSpecificationType";
        public static final String EMPTYSCOPESPECIFICATIONDOMAIN = "EmptyScopeSpecificationDomain";
        public static final String EMPTYSCOPESPECIFICATIONPATH = "EmptyScopeSpecificationPath";
        public static final String INVALIDSCOPESPECIFICATIONTYPE = "InvalidScopeSpecificationType";
        public static final String INVALIDSCOPEINCLUDESITE = "InvalidScopeIncludeSite";
        public static final String MISSINGSCOPEINCLUDESITE = "MissingScopeIncludeSite";
        public static final String FEDERATEDNOTAUTHORIZEDBYPROVIDER = "FederatedNotAuthorizedByProvider";
        public static final String FEDERATEDNOTAUTHORIZEDBYRELYINGPARTY = "FederatedNotAuthorizedByRelyingParty";
        public static final String SESSIONPROVIDERWELLKNOWNMALFORMED = "SessionProviderWellKnownMalformed";
        public static final String SESSIONPROVIDERWELLKNOWNHASPROVIDERORIGIN = "SessionProviderWellKnownHasProviderOrigin";
        public static final String RELYINGPARTYWELLKNOWNMALFORMED = "RelyingPartyWellKnownMalformed";
        public static final String RELYINGPARTYWELLKNOWNHASRELYINGORIGINS = "RelyingPartyWellKnownHasRelyingOrigins";
        public static final String INVALIDFEDERATEDSESSIONPROVIDERSESSIONMISSING = "InvalidFederatedSessionProviderSessionMissing";
        public static final String INVALIDFEDERATEDSESSIONWRONGPROVIDERORIGIN = "InvalidFederatedSessionWrongProviderOrigin";
        public static final String INVALIDCREDENTIALSCOOKIECREATIONTIME = "InvalidCredentialsCookieCreationTime";
        public static final String INVALIDCREDENTIALSCOOKIENAME = "InvalidCredentialsCookieName";
        public static final String INVALIDCREDENTIALSCOOKIEPARSING = "InvalidCredentialsCookieParsing";
        public static final String INVALIDCREDENTIALSCOOKIEUNPERMITTEDATTRIBUTE = "InvalidCredentialsCookieUnpermittedAttribute";
        public static final String INVALIDCREDENTIALSCOOKIEINVALIDDOMAIN = "InvalidCredentialsCookieInvalidDomain";
        public static final String INVALIDCREDENTIALSCOOKIEPREFIX = "InvalidCredentialsCookiePrefix";
        public static final String INVALIDSCOPERULEPATH = "InvalidScopeRulePath";
        public static final String INVALIDSCOPERULEHOSTPATTERN = "InvalidScopeRuleHostPattern";
        public static final String SCOPERULEORIGINSCOPEDHOSTPATTERNMISMATCH = "ScopeRuleOriginScopedHostPatternMismatch";
        public static final String SCOPERULESITESCOPEDHOSTPATTERNMISMATCH = "ScopeRuleSiteScopedHostPatternMismatch";
        public static final String SIGNINGQUOTAEXCEEDED = "SigningQuotaExceeded";
        public static final String INVALIDCONFIGJSON = "InvalidConfigJson";
        public static final String INVALIDFEDERATEDSESSIONPROVIDERFAILEDTORESTOREKEY = "InvalidFederatedSessionProviderFailedToRestoreKey";
        public static final String FAILEDTOUNWRAPKEY = "FailedToUnwrapKey";
        public static final String SESSIONDELETEDDURINGREFRESH = "SessionDeletedDuringRefresh";
        public static final String CROSSORIGINREGISTRATIONSITENOTINCLUDED = "CrossOriginRegistrationSiteNotIncluded";
    }
    /**
     * Details about a failed device bound session network request.
     * <p><b>Experimental:</b> this part of CDP may change without notice.
     */
    public static final class DeviceBoundSessionFailedRequest extends CdpObject {
        private DeviceBoundSessionFailedRequest(Map<String, Object> values) { super(values); }
        @Nullable public static DeviceBoundSessionFailedRequest fromMap(@Nullable Map<String, Object> values) {
            return values == null ? null : new DeviceBoundSessionFailedRequest(values);
        }
        public static Builder builder() { return new Builder(); }
        /**
         * The failed request URL.
         * @return the protocol field value
         */
        @Nullable public String requestUrl() {
            return (String) value("requestUrl");
        }
        /**
         * The net error of the response if it was not OK.
         * @return the protocol field value
         */
        @Nullable public String netError() {
            return (String) value("netError");
        }
        /**
         * The response code if the net error was OK and the response code was not 200.
         * @return the protocol field value
         */
        @Nullable public Long responseError() {
            return numberAsLong(value("responseError"));
        }
        /**
         * The body of the response if the net error was OK, the response code was not 200, and the response body was not empty.
         * @return the protocol field value
         */
        @Nullable public String responseErrorBody() {
            return (String) value("responseErrorBody");
        }
        public static final class Builder {
            private final Map<String, Object> values = new LinkedHashMap<>();
            /**
             * The failed request URL.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder requestUrl(@Nullable String value) {
                if (value == null) values.remove("requestUrl");
                else values.put("requestUrl", jsonValue(value));
                return this;
            }
            /**
             * The net error of the response if it was not OK.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder netError(@Nullable String value) {
                if (value == null) values.remove("netError");
                else values.put("netError", jsonValue(value));
                return this;
            }
            /**
             * The response code if the net error was OK and the response code was not 200.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder responseError(@Nullable Long value) {
                if (value == null) values.remove("responseError");
                else values.put("responseError", jsonValue(value));
                return this;
            }
            /**
             * The body of the response if the net error was OK, the response code was not 200, and the response body was not empty.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder responseErrorBody(@Nullable String value) {
                if (value == null) values.remove("responseErrorBody");
                else values.put("responseErrorBody", jsonValue(value));
                return this;
            }
            public DeviceBoundSessionFailedRequest build() {
                if (!values.containsKey("requestUrl")) throw new IllegalStateException("Missing required CDP field: requestUrl");
                return new DeviceBoundSessionFailedRequest(values);
            }
        }
    }
    /**
     * Session event details specific to creation.
     * <p><b>Experimental:</b> this part of CDP may change without notice.
     */
    public static final class CreationEventDetails extends CdpObject {
        private CreationEventDetails(Map<String, Object> values) { super(values); }
        @Nullable public static CreationEventDetails fromMap(@Nullable Map<String, Object> values) {
            return values == null ? null : new CreationEventDetails(values);
        }
        public static Builder builder() { return new Builder(); }
        /**
         * The result of the fetch attempt.
         * @return the protocol field value
         */
        @Nullable public String fetchResult() {
            return (String) value("fetchResult");
        }
        /**
         * The session if there was a newly created session. This is populated for all successful creation events.
         * @return the protocol field value
         */
        @Nullable public Network.DeviceBoundSession newSession() {
            return Network.DeviceBoundSession.fromMap(objectMap(value("newSession")));
        }
        /**
         * Details about a failed device bound session network request if there was one.
         * @return the protocol field value
         */
        @Nullable public Network.DeviceBoundSessionFailedRequest failedRequest() {
            return Network.DeviceBoundSessionFailedRequest.fromMap(objectMap(value("failedRequest")));
        }
        public static final class Builder {
            private final Map<String, Object> values = new LinkedHashMap<>();
            /**
             * The result of the fetch attempt.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder fetchResult(@Nullable String value) {
                if (value == null) values.remove("fetchResult");
                else values.put("fetchResult", jsonValue(value));
                return this;
            }
            /**
             * The session if there was a newly created session. This is populated for all successful creation events.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder newSession(@Nullable Network.DeviceBoundSession value) {
                if (value == null) values.remove("newSession");
                else values.put("newSession", jsonValue(value));
                return this;
            }
            /**
             * Details about a failed device bound session network request if there was one.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder failedRequest(@Nullable Network.DeviceBoundSessionFailedRequest value) {
                if (value == null) values.remove("failedRequest");
                else values.put("failedRequest", jsonValue(value));
                return this;
            }
            public CreationEventDetails build() {
                if (!values.containsKey("fetchResult")) throw new IllegalStateException("Missing required CDP field: fetchResult");
                return new CreationEventDetails(values);
            }
        }
    }
    /**
     * Session event details specific to refresh.
     * <p><b>Experimental:</b> this part of CDP may change without notice.
     */
    public static final class RefreshEventDetails extends CdpObject {
        private RefreshEventDetails(Map<String, Object> values) { super(values); }
        @Nullable public static RefreshEventDetails fromMap(@Nullable Map<String, Object> values) {
            return values == null ? null : new RefreshEventDetails(values);
        }
        public static Builder builder() { return new Builder(); }
        /**
         * The result of a refresh.
         * @return the protocol field value
         */
        @Nullable public String refreshResult() {
            return (String) value("refreshResult");
        }
        /**
         * The result of a refresh.
         */
        public static final class RefreshResultValues {
            private RefreshResultValues() {}
            public static final String REFRESHED = "Refreshed";
            public static final String INITIALIZEDSERVICE = "InitializedService";
            public static final String UNREACHABLE = "Unreachable";
            public static final String SERVERERROR = "ServerError";
            public static final String REFRESHQUOTAEXCEEDED = "RefreshQuotaExceeded";
            public static final String FATALERROR = "FatalError";
            public static final String SIGNINGQUOTAEXCEEDED = "SigningQuotaExceeded";
            public static final String REFRESHEDASWAITER = "RefreshedAsWaiter";
            public static final String TRANSIENTSIGNINGERROR = "TransientSigningError";
        }
        /**
         * If there was a fetch attempt, the result of that.
         * @return the protocol field value
         */
        @Nullable public String fetchResult() {
            return (String) value("fetchResult");
        }
        /**
         * The session display if there was a newly created session. This is populated for any refresh event that modifies the session config.
         * @return the protocol field value
         */
        @Nullable public Network.DeviceBoundSession newSession() {
            return Network.DeviceBoundSession.fromMap(objectMap(value("newSession")));
        }
        /**
         * See comments on {@code net::device_bound_sessions::RefreshEventResult::was_fully_proactive_refresh}.
         * @return the protocol field value
         */
        @Nullable public Boolean wasFullyProactiveRefresh() {
            return (Boolean) value("wasFullyProactiveRefresh");
        }
        /**
         * Details about a failed device bound session network request if there was one.
         * @return the protocol field value
         */
        @Nullable public Network.DeviceBoundSessionFailedRequest failedRequest() {
            return Network.DeviceBoundSessionFailedRequest.fromMap(objectMap(value("failedRequest")));
        }
        public static final class Builder {
            private final Map<String, Object> values = new LinkedHashMap<>();
            /**
             * The result of a refresh.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder refreshResult(@Nullable String value) {
                if (value == null) values.remove("refreshResult");
                else values.put("refreshResult", jsonValue(value));
                return this;
            }
            /**
             * If there was a fetch attempt, the result of that.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder fetchResult(@Nullable String value) {
                if (value == null) values.remove("fetchResult");
                else values.put("fetchResult", jsonValue(value));
                return this;
            }
            /**
             * The session display if there was a newly created session. This is populated for any refresh event that modifies the session config.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder newSession(@Nullable Network.DeviceBoundSession value) {
                if (value == null) values.remove("newSession");
                else values.put("newSession", jsonValue(value));
                return this;
            }
            /**
             * See comments on {@code net::device_bound_sessions::RefreshEventResult::was_fully_proactive_refresh}.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder wasFullyProactiveRefresh(@Nullable Boolean value) {
                if (value == null) values.remove("wasFullyProactiveRefresh");
                else values.put("wasFullyProactiveRefresh", jsonValue(value));
                return this;
            }
            /**
             * Details about a failed device bound session network request if there was one.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder failedRequest(@Nullable Network.DeviceBoundSessionFailedRequest value) {
                if (value == null) values.remove("failedRequest");
                else values.put("failedRequest", jsonValue(value));
                return this;
            }
            public RefreshEventDetails build() {
                if (!values.containsKey("refreshResult")) throw new IllegalStateException("Missing required CDP field: refreshResult");
                if (!values.containsKey("wasFullyProactiveRefresh")) throw new IllegalStateException("Missing required CDP field: wasFullyProactiveRefresh");
                return new RefreshEventDetails(values);
            }
        }
    }
    /**
     * Session event details specific to termination.
     * <p><b>Experimental:</b> this part of CDP may change without notice.
     */
    public static final class TerminationEventDetails extends CdpObject {
        private TerminationEventDetails(Map<String, Object> values) { super(values); }
        @Nullable public static TerminationEventDetails fromMap(@Nullable Map<String, Object> values) {
            return values == null ? null : new TerminationEventDetails(values);
        }
        public static Builder builder() { return new Builder(); }
        /**
         * The reason for a session being deleted.
         * @return the protocol field value
         */
        @Nullable public String deletionReason() {
            return (String) value("deletionReason");
        }
        /**
         * The reason for a session being deleted.
         */
        public static final class DeletionReasonValues {
            private DeletionReasonValues() {}
            public static final String EXPIRED = "Expired";
            public static final String FAILEDTORESTOREKEY = "FailedToRestoreKey";
            public static final String FAILEDTOUNWRAPKEY = "FailedToUnwrapKey";
            public static final String STORAGEPARTITIONCLEARED = "StoragePartitionCleared";
            public static final String CLEARBROWSINGDATA = "ClearBrowsingData";
            public static final String SERVERREQUESTED = "ServerRequested";
            public static final String INVALIDSESSIONPARAMS = "InvalidSessionParams";
            public static final String REFRESHFATALERROR = "RefreshFatalError";
            public static final String DEVTOOLS = "DevTools";
        }
        public static final class Builder {
            private final Map<String, Object> values = new LinkedHashMap<>();
            /**
             * The reason for a session being deleted.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder deletionReason(@Nullable String value) {
                if (value == null) values.remove("deletionReason");
                else values.put("deletionReason", jsonValue(value));
                return this;
            }
            public TerminationEventDetails build() {
                if (!values.containsKey("deletionReason")) throw new IllegalStateException("Missing required CDP field: deletionReason");
                return new TerminationEventDetails(values);
            }
        }
    }
    /**
     * Session event details specific to challenges.
     * <p><b>Experimental:</b> this part of CDP may change without notice.
     */
    public static final class ChallengeEventDetails extends CdpObject {
        private ChallengeEventDetails(Map<String, Object> values) { super(values); }
        @Nullable public static ChallengeEventDetails fromMap(@Nullable Map<String, Object> values) {
            return values == null ? null : new ChallengeEventDetails(values);
        }
        public static Builder builder() { return new Builder(); }
        /**
         * The result of a challenge.
         * @return the protocol field value
         */
        @Nullable public String challengeResult() {
            return (String) value("challengeResult");
        }
        /**
         * The result of a challenge.
         */
        public static final class ChallengeResultValues {
            private ChallengeResultValues() {}
            public static final String SUCCESS = "Success";
            public static final String NOSESSIONID = "NoSessionId";
            public static final String NOSESSIONMATCH = "NoSessionMatch";
            public static final String CANTSETBOUNDCOOKIE = "CantSetBoundCookie";
        }
        /**
         * The challenge set.
         * @return the protocol field value
         */
        @Nullable public String challenge() {
            return (String) value("challenge");
        }
        public static final class Builder {
            private final Map<String, Object> values = new LinkedHashMap<>();
            /**
             * The result of a challenge.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder challengeResult(@Nullable String value) {
                if (value == null) values.remove("challengeResult");
                else values.put("challengeResult", jsonValue(value));
                return this;
            }
            /**
             * The challenge set.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder challenge(@Nullable String value) {
                if (value == null) values.remove("challenge");
                else values.put("challenge", jsonValue(value));
                return this;
            }
            public ChallengeEventDetails build() {
                if (!values.containsKey("challengeResult")) throw new IllegalStateException("Missing required CDP field: challengeResult");
                if (!values.containsKey("challenge")) throw new IllegalStateException("Missing required CDP field: challenge");
                return new ChallengeEventDetails(values);
            }
        }
    }
    /**
     * An object providing the result of a network resource load.
     * <p><b>Experimental:</b> this part of CDP may change without notice.
     */
    public static final class LoadNetworkResourcePageResult extends CdpObject {
        private LoadNetworkResourcePageResult(Map<String, Object> values) { super(values); }
        @Nullable public static LoadNetworkResourcePageResult fromMap(@Nullable Map<String, Object> values) {
            return values == null ? null : new LoadNetworkResourcePageResult(values);
        }
        public static Builder builder() { return new Builder(); }
        /**
         * Returns the success field.
         * @return the protocol field value
         */
        @Nullable public Boolean success() {
            return (Boolean) value("success");
        }
        /**
         * Optional values used for error reporting.
         * @return the protocol field value
         */
        @Nullable public Double netError() {
            return numberAsDouble(value("netError"));
        }
        /**
         * Returns the netErrorName field.
         * @return the protocol field value
         */
        @Nullable public String netErrorName() {
            return (String) value("netErrorName");
        }
        /**
         * Returns the httpStatusCode field.
         * @return the protocol field value
         */
        @Nullable public Double httpStatusCode() {
            return numberAsDouble(value("httpStatusCode"));
        }
        /**
         * If successful, one of the following two fields holds the result.
         * @return the protocol field value
         */
        @Nullable public String stream() {
            return (String) value("stream");
        }
        /**
         * Response headers.
         * @return the protocol field value
         */
        @Nullable public java.util.Map<String, Object> headers() {
            return objectMap(value("headers"));
        }
        public static final class Builder {
            private final Map<String, Object> values = new LinkedHashMap<>();
            /**
             * Sets the success field.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder success(@Nullable Boolean value) {
                if (value == null) values.remove("success");
                else values.put("success", jsonValue(value));
                return this;
            }
            /**
             * Optional values used for error reporting.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder netError(@Nullable Double value) {
                if (value == null) values.remove("netError");
                else values.put("netError", jsonValue(value));
                return this;
            }
            /**
             * Sets the netErrorName field.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder netErrorName(@Nullable String value) {
                if (value == null) values.remove("netErrorName");
                else values.put("netErrorName", jsonValue(value));
                return this;
            }
            /**
             * Sets the httpStatusCode field.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder httpStatusCode(@Nullable Double value) {
                if (value == null) values.remove("httpStatusCode");
                else values.put("httpStatusCode", jsonValue(value));
                return this;
            }
            /**
             * If successful, one of the following two fields holds the result.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder stream(@Nullable String value) {
                if (value == null) values.remove("stream");
                else values.put("stream", jsonValue(value));
                return this;
            }
            /**
             * Response headers.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder headers(@Nullable java.util.Map<String, Object> value) {
                if (value == null) values.remove("headers");
                else values.put("headers", jsonValue(value));
                return this;
            }
            public LoadNetworkResourcePageResult build() {
                if (!values.containsKey("success")) throw new IllegalStateException("Missing required CDP field: success");
                return new LoadNetworkResourcePageResult(values);
            }
        }
    }
    /**
     * An options object that may be extended later to better support CORS, CORB and streaming.
     * <p><b>Experimental:</b> this part of CDP may change without notice.
     */
    public static final class LoadNetworkResourceOptions extends CdpObject {
        private LoadNetworkResourceOptions(Map<String, Object> values) { super(values); }
        @Nullable public static LoadNetworkResourceOptions fromMap(@Nullable Map<String, Object> values) {
            return values == null ? null : new LoadNetworkResourceOptions(values);
        }
        public static Builder builder() { return new Builder(); }
        /**
         * Returns the disableCache field.
         * @return the protocol field value
         */
        @Nullable public Boolean disableCache() {
            return (Boolean) value("disableCache");
        }
        /**
         * Returns the includeCredentials field.
         * @return the protocol field value
         */
        @Nullable public Boolean includeCredentials() {
            return (Boolean) value("includeCredentials");
        }
        public static final class Builder {
            private final Map<String, Object> values = new LinkedHashMap<>();
            /**
             * Sets the disableCache field.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder disableCache(@Nullable Boolean value) {
                if (value == null) values.remove("disableCache");
                else values.put("disableCache", jsonValue(value));
                return this;
            }
            /**
             * Sets the includeCredentials field.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder includeCredentials(@Nullable Boolean value) {
                if (value == null) values.remove("includeCredentials");
                else values.put("includeCredentials", jsonValue(value));
                return this;
            }
            public LoadNetworkResourceOptions build() {
                if (!values.containsKey("disableCache")) throw new IllegalStateException("Missing required CDP field: disableCache");
                if (!values.containsKey("includeCredentials")) throw new IllegalStateException("Missing required CDP field: includeCredentials");
                return new LoadNetworkResourceOptions(values);
            }
        }
    }
    /**
     * Sets a list of content encodings that will be accepted. Empty list means no encoding is accepted.
     * <p><b>Experimental:</b> this part of CDP may change without notice.
     */
    public static final class SetAcceptedEncodingsParams extends CdpObject {
        private SetAcceptedEncodingsParams(Map<String, Object> values) { super(values); }
        @Nullable public static SetAcceptedEncodingsParams fromMap(@Nullable Map<String, Object> values) {
            return values == null ? null : new SetAcceptedEncodingsParams(values);
        }
        public static Builder builder() { return new Builder(); }
        /**
         * List of accepted content encodings.
         * @return the protocol field value
         */
        @Nullable public java.util.List<String> encodings() {
            return list(value("encodings"), element0 -> (String) element0);
        }
        public static final class Builder {
            private final Map<String, Object> values = new LinkedHashMap<>();
            /**
             * List of accepted content encodings.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder encodings(@Nullable java.util.List<String> value) {
                if (value == null) values.remove("encodings");
                else values.put("encodings", jsonValue(value));
                return this;
            }
            public SetAcceptedEncodingsParams build() {
                if (!values.containsKey("encodings")) throw new IllegalStateException("Missing required CDP field: encodings");
                return new SetAcceptedEncodingsParams(values);
            }
        }
    }
    /**
     * Sets a list of content encodings that will be accepted. Empty list means no encoding is accepted.
     * <p><b>Experimental:</b> this part of CDP may change without notice.
     */
    public static final class SetAcceptedEncodingsResult extends CdpObject {
        private SetAcceptedEncodingsResult(Map<String, Object> values) { super(values); }
        @Nullable public static SetAcceptedEncodingsResult fromMap(@Nullable Map<String, Object> values) {
            return values == null ? null : new SetAcceptedEncodingsResult(values);
        }
        public static Builder builder() { return new Builder(); }
        public static final class Builder {
            private final Map<String, Object> values = new LinkedHashMap<>();
            public SetAcceptedEncodingsResult build() {
                return new SetAcceptedEncodingsResult(values);
            }
        }
    }
    /**
     * Clears accepted encodings set by setAcceptedEncodings
     * <p><b>Experimental:</b> this part of CDP may change without notice.
     */
    public static final class ClearAcceptedEncodingsOverrideParams extends CdpObject {
        private ClearAcceptedEncodingsOverrideParams(Map<String, Object> values) { super(values); }
        @Nullable public static ClearAcceptedEncodingsOverrideParams fromMap(@Nullable Map<String, Object> values) {
            return values == null ? null : new ClearAcceptedEncodingsOverrideParams(values);
        }
        public static Builder builder() { return new Builder(); }
        public static final class Builder {
            private final Map<String, Object> values = new LinkedHashMap<>();
            public ClearAcceptedEncodingsOverrideParams build() {
                return new ClearAcceptedEncodingsOverrideParams(values);
            }
        }
    }
    /**
     * Clears accepted encodings set by setAcceptedEncodings
     * <p><b>Experimental:</b> this part of CDP may change without notice.
     */
    public static final class ClearAcceptedEncodingsOverrideResult extends CdpObject {
        private ClearAcceptedEncodingsOverrideResult(Map<String, Object> values) { super(values); }
        @Nullable public static ClearAcceptedEncodingsOverrideResult fromMap(@Nullable Map<String, Object> values) {
            return values == null ? null : new ClearAcceptedEncodingsOverrideResult(values);
        }
        public static Builder builder() { return new Builder(); }
        public static final class Builder {
            private final Map<String, Object> values = new LinkedHashMap<>();
            public ClearAcceptedEncodingsOverrideResult build() {
                return new ClearAcceptedEncodingsOverrideResult(values);
            }
        }
    }
    /**
     * Tells whether clearing browser cache is supported.
     * @deprecated Deprecated by the Chromium DevTools Protocol.
     */
    @Deprecated
    public static final class CanClearBrowserCacheParams extends CdpObject {
        private CanClearBrowserCacheParams(Map<String, Object> values) { super(values); }
        @Nullable public static CanClearBrowserCacheParams fromMap(@Nullable Map<String, Object> values) {
            return values == null ? null : new CanClearBrowserCacheParams(values);
        }
        public static Builder builder() { return new Builder(); }
        public static final class Builder {
            private final Map<String, Object> values = new LinkedHashMap<>();
            public CanClearBrowserCacheParams build() {
                return new CanClearBrowserCacheParams(values);
            }
        }
    }
    /**
     * Tells whether clearing browser cache is supported.
     * @deprecated Deprecated by the Chromium DevTools Protocol.
     */
    @Deprecated
    public static final class CanClearBrowserCacheResult extends CdpObject {
        private CanClearBrowserCacheResult(Map<String, Object> values) { super(values); }
        @Nullable public static CanClearBrowserCacheResult fromMap(@Nullable Map<String, Object> values) {
            return values == null ? null : new CanClearBrowserCacheResult(values);
        }
        public static Builder builder() { return new Builder(); }
        /**
         * True if browser cache can be cleared.
         * @return the protocol field value
         */
        @Nullable public Boolean result() {
            return (Boolean) value("result");
        }
        public static final class Builder {
            private final Map<String, Object> values = new LinkedHashMap<>();
            /**
             * True if browser cache can be cleared.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder result(@Nullable Boolean value) {
                if (value == null) values.remove("result");
                else values.put("result", jsonValue(value));
                return this;
            }
            public CanClearBrowserCacheResult build() {
                if (!values.containsKey("result")) throw new IllegalStateException("Missing required CDP field: result");
                return new CanClearBrowserCacheResult(values);
            }
        }
    }
    /**
     * Tells whether clearing browser cookies is supported.
     * @deprecated Deprecated by the Chromium DevTools Protocol.
     */
    @Deprecated
    public static final class CanClearBrowserCookiesParams extends CdpObject {
        private CanClearBrowserCookiesParams(Map<String, Object> values) { super(values); }
        @Nullable public static CanClearBrowserCookiesParams fromMap(@Nullable Map<String, Object> values) {
            return values == null ? null : new CanClearBrowserCookiesParams(values);
        }
        public static Builder builder() { return new Builder(); }
        public static final class Builder {
            private final Map<String, Object> values = new LinkedHashMap<>();
            public CanClearBrowserCookiesParams build() {
                return new CanClearBrowserCookiesParams(values);
            }
        }
    }
    /**
     * Tells whether clearing browser cookies is supported.
     * @deprecated Deprecated by the Chromium DevTools Protocol.
     */
    @Deprecated
    public static final class CanClearBrowserCookiesResult extends CdpObject {
        private CanClearBrowserCookiesResult(Map<String, Object> values) { super(values); }
        @Nullable public static CanClearBrowserCookiesResult fromMap(@Nullable Map<String, Object> values) {
            return values == null ? null : new CanClearBrowserCookiesResult(values);
        }
        public static Builder builder() { return new Builder(); }
        /**
         * True if browser cookies can be cleared.
         * @return the protocol field value
         */
        @Nullable public Boolean result() {
            return (Boolean) value("result");
        }
        public static final class Builder {
            private final Map<String, Object> values = new LinkedHashMap<>();
            /**
             * True if browser cookies can be cleared.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder result(@Nullable Boolean value) {
                if (value == null) values.remove("result");
                else values.put("result", jsonValue(value));
                return this;
            }
            public CanClearBrowserCookiesResult build() {
                if (!values.containsKey("result")) throw new IllegalStateException("Missing required CDP field: result");
                return new CanClearBrowserCookiesResult(values);
            }
        }
    }
    /**
     * Tells whether emulation of network conditions is supported.
     * @deprecated Deprecated by the Chromium DevTools Protocol.
     */
    @Deprecated
    public static final class CanEmulateNetworkConditionsParams extends CdpObject {
        private CanEmulateNetworkConditionsParams(Map<String, Object> values) { super(values); }
        @Nullable public static CanEmulateNetworkConditionsParams fromMap(@Nullable Map<String, Object> values) {
            return values == null ? null : new CanEmulateNetworkConditionsParams(values);
        }
        public static Builder builder() { return new Builder(); }
        public static final class Builder {
            private final Map<String, Object> values = new LinkedHashMap<>();
            public CanEmulateNetworkConditionsParams build() {
                return new CanEmulateNetworkConditionsParams(values);
            }
        }
    }
    /**
     * Tells whether emulation of network conditions is supported.
     * @deprecated Deprecated by the Chromium DevTools Protocol.
     */
    @Deprecated
    public static final class CanEmulateNetworkConditionsResult extends CdpObject {
        private CanEmulateNetworkConditionsResult(Map<String, Object> values) { super(values); }
        @Nullable public static CanEmulateNetworkConditionsResult fromMap(@Nullable Map<String, Object> values) {
            return values == null ? null : new CanEmulateNetworkConditionsResult(values);
        }
        public static Builder builder() { return new Builder(); }
        /**
         * True if emulation of network conditions is supported.
         * @return the protocol field value
         */
        @Nullable public Boolean result() {
            return (Boolean) value("result");
        }
        public static final class Builder {
            private final Map<String, Object> values = new LinkedHashMap<>();
            /**
             * True if emulation of network conditions is supported.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder result(@Nullable Boolean value) {
                if (value == null) values.remove("result");
                else values.put("result", jsonValue(value));
                return this;
            }
            public CanEmulateNetworkConditionsResult build() {
                if (!values.containsKey("result")) throw new IllegalStateException("Missing required CDP field: result");
                return new CanEmulateNetworkConditionsResult(values);
            }
        }
    }
    /**
     * Clears browser cache.
     */
    public static final class ClearBrowserCacheParams extends CdpObject {
        private ClearBrowserCacheParams(Map<String, Object> values) { super(values); }
        @Nullable public static ClearBrowserCacheParams fromMap(@Nullable Map<String, Object> values) {
            return values == null ? null : new ClearBrowserCacheParams(values);
        }
        public static Builder builder() { return new Builder(); }
        public static final class Builder {
            private final Map<String, Object> values = new LinkedHashMap<>();
            public ClearBrowserCacheParams build() {
                return new ClearBrowserCacheParams(values);
            }
        }
    }
    /**
     * Clears browser cache.
     */
    public static final class ClearBrowserCacheResult extends CdpObject {
        private ClearBrowserCacheResult(Map<String, Object> values) { super(values); }
        @Nullable public static ClearBrowserCacheResult fromMap(@Nullable Map<String, Object> values) {
            return values == null ? null : new ClearBrowserCacheResult(values);
        }
        public static Builder builder() { return new Builder(); }
        public static final class Builder {
            private final Map<String, Object> values = new LinkedHashMap<>();
            public ClearBrowserCacheResult build() {
                return new ClearBrowserCacheResult(values);
            }
        }
    }
    /**
     * Clears browser cookies.
     */
    public static final class ClearBrowserCookiesParams extends CdpObject {
        private ClearBrowserCookiesParams(Map<String, Object> values) { super(values); }
        @Nullable public static ClearBrowserCookiesParams fromMap(@Nullable Map<String, Object> values) {
            return values == null ? null : new ClearBrowserCookiesParams(values);
        }
        public static Builder builder() { return new Builder(); }
        public static final class Builder {
            private final Map<String, Object> values = new LinkedHashMap<>();
            public ClearBrowserCookiesParams build() {
                return new ClearBrowserCookiesParams(values);
            }
        }
    }
    /**
     * Clears browser cookies.
     */
    public static final class ClearBrowserCookiesResult extends CdpObject {
        private ClearBrowserCookiesResult(Map<String, Object> values) { super(values); }
        @Nullable public static ClearBrowserCookiesResult fromMap(@Nullable Map<String, Object> values) {
            return values == null ? null : new ClearBrowserCookiesResult(values);
        }
        public static Builder builder() { return new Builder(); }
        public static final class Builder {
            private final Map<String, Object> values = new LinkedHashMap<>();
            public ClearBrowserCookiesResult build() {
                return new ClearBrowserCookiesResult(values);
            }
        }
    }
    /**
     * Response to Network.requestIntercepted which either modifies the request to continue with any modifications, or blocks it, or completes it with the provided response bytes. If a network fetch occurs as a result which encounters a redirect an additional Network.requestIntercepted event will be sent with the same InterceptionId. Deprecated, use Fetch.continueRequest, Fetch.fulfillRequest and Fetch.failRequest instead.
     * <p><b>Experimental:</b> this part of CDP may change without notice.
     * @deprecated Deprecated by the Chromium DevTools Protocol.
     */
    @Deprecated
    public static final class ContinueInterceptedRequestParams extends CdpObject {
        private ContinueInterceptedRequestParams(Map<String, Object> values) { super(values); }
        @Nullable public static ContinueInterceptedRequestParams fromMap(@Nullable Map<String, Object> values) {
            return values == null ? null : new ContinueInterceptedRequestParams(values);
        }
        public static Builder builder() { return new Builder(); }
        /**
         * Returns the interceptionId field.
         * @return the protocol field value
         */
        @Nullable public String interceptionId() {
            return (String) value("interceptionId");
        }
        /**
         * If set this causes the request to fail with the given reason. Passing {@code Aborted} for requests marked with {@code isNavigationRequest} also cancels the navigation. Must not be set in response to an authChallenge.
         * @return the protocol field value
         */
        @Nullable public String errorReason() {
            return (String) value("errorReason");
        }
        /**
         * If set the requests completes using with the provided base64 encoded raw response, including HTTP status line and headers etc... Must not be set in response to an authChallenge. (Encoded as a base64 string when passed over JSON)
         * @return the protocol field value
         */
        @Nullable public String rawResponse() {
            return (String) value("rawResponse");
        }
        /**
         * If set the request url will be modified in a way that&#x27;s not observable by page. Must not be set in response to an authChallenge.
         * @return the protocol field value
         */
        @Nullable public String url() {
            return (String) value("url");
        }
        /**
         * If set this allows the request method to be overridden. Must not be set in response to an authChallenge.
         * @return the protocol field value
         */
        @Nullable public String method() {
            return (String) value("method");
        }
        /**
         * If set this allows postData to be set. Must not be set in response to an authChallenge.
         * @return the protocol field value
         */
        @Nullable public String postData() {
            return (String) value("postData");
        }
        /**
         * If set this allows the request headers to be changed. Must not be set in response to an authChallenge.
         * @return the protocol field value
         */
        @Nullable public java.util.Map<String, Object> headers() {
            return objectMap(value("headers"));
        }
        /**
         * Response to a requestIntercepted with an authChallenge. Must not be set otherwise.
         * @return the protocol field value
         */
        @Nullable public Network.AuthChallengeResponse authChallengeResponse() {
            return Network.AuthChallengeResponse.fromMap(objectMap(value("authChallengeResponse")));
        }
        public static final class Builder {
            private final Map<String, Object> values = new LinkedHashMap<>();
            /**
             * Sets the interceptionId field.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder interceptionId(@Nullable String value) {
                if (value == null) values.remove("interceptionId");
                else values.put("interceptionId", jsonValue(value));
                return this;
            }
            /**
             * If set this causes the request to fail with the given reason. Passing {@code Aborted} for requests marked with {@code isNavigationRequest} also cancels the navigation. Must not be set in response to an authChallenge.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder errorReason(@Nullable String value) {
                if (value == null) values.remove("errorReason");
                else values.put("errorReason", jsonValue(value));
                return this;
            }
            /**
             * If set the requests completes using with the provided base64 encoded raw response, including HTTP status line and headers etc... Must not be set in response to an authChallenge. (Encoded as a base64 string when passed over JSON)
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder rawResponse(@Nullable String value) {
                if (value == null) values.remove("rawResponse");
                else values.put("rawResponse", jsonValue(value));
                return this;
            }
            /**
             * If set the request url will be modified in a way that&#x27;s not observable by page. Must not be set in response to an authChallenge.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder url(@Nullable String value) {
                if (value == null) values.remove("url");
                else values.put("url", jsonValue(value));
                return this;
            }
            /**
             * If set this allows the request method to be overridden. Must not be set in response to an authChallenge.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder method(@Nullable String value) {
                if (value == null) values.remove("method");
                else values.put("method", jsonValue(value));
                return this;
            }
            /**
             * If set this allows postData to be set. Must not be set in response to an authChallenge.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder postData(@Nullable String value) {
                if (value == null) values.remove("postData");
                else values.put("postData", jsonValue(value));
                return this;
            }
            /**
             * If set this allows the request headers to be changed. Must not be set in response to an authChallenge.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder headers(@Nullable java.util.Map<String, Object> value) {
                if (value == null) values.remove("headers");
                else values.put("headers", jsonValue(value));
                return this;
            }
            /**
             * Response to a requestIntercepted with an authChallenge. Must not be set otherwise.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder authChallengeResponse(@Nullable Network.AuthChallengeResponse value) {
                if (value == null) values.remove("authChallengeResponse");
                else values.put("authChallengeResponse", jsonValue(value));
                return this;
            }
            public ContinueInterceptedRequestParams build() {
                if (!values.containsKey("interceptionId")) throw new IllegalStateException("Missing required CDP field: interceptionId");
                return new ContinueInterceptedRequestParams(values);
            }
        }
    }
    /**
     * Response to Network.requestIntercepted which either modifies the request to continue with any modifications, or blocks it, or completes it with the provided response bytes. If a network fetch occurs as a result which encounters a redirect an additional Network.requestIntercepted event will be sent with the same InterceptionId. Deprecated, use Fetch.continueRequest, Fetch.fulfillRequest and Fetch.failRequest instead.
     * <p><b>Experimental:</b> this part of CDP may change without notice.
     * @deprecated Deprecated by the Chromium DevTools Protocol.
     */
    @Deprecated
    public static final class ContinueInterceptedRequestResult extends CdpObject {
        private ContinueInterceptedRequestResult(Map<String, Object> values) { super(values); }
        @Nullable public static ContinueInterceptedRequestResult fromMap(@Nullable Map<String, Object> values) {
            return values == null ? null : new ContinueInterceptedRequestResult(values);
        }
        public static Builder builder() { return new Builder(); }
        public static final class Builder {
            private final Map<String, Object> values = new LinkedHashMap<>();
            public ContinueInterceptedRequestResult build() {
                return new ContinueInterceptedRequestResult(values);
            }
        }
    }
    /**
     * Deletes browser cookies with matching name and url or domain/path/partitionKey pair.
     */
    public static final class DeleteCookiesParams extends CdpObject {
        private DeleteCookiesParams(Map<String, Object> values) { super(values); }
        @Nullable public static DeleteCookiesParams fromMap(@Nullable Map<String, Object> values) {
            return values == null ? null : new DeleteCookiesParams(values);
        }
        public static Builder builder() { return new Builder(); }
        /**
         * Name of the cookies to remove.
         * @return the protocol field value
         */
        @Nullable public String name() {
            return (String) value("name");
        }
        /**
         * If specified, deletes all the cookies with the given name where domain and path match provided URL.
         * @return the protocol field value
         */
        @Nullable public String url() {
            return (String) value("url");
        }
        /**
         * If specified, deletes only cookies with the exact domain.
         * @return the protocol field value
         */
        @Nullable public String domain() {
            return (String) value("domain");
        }
        /**
         * If specified, deletes only cookies with the exact path.
         * @return the protocol field value
         */
        @Nullable public String path() {
            return (String) value("path");
        }
        /**
         * If specified, deletes only cookies with the the given name and partitionKey where all partition key attributes match the cookie partition key attribute.
         * <p><b>Experimental:</b> this part of CDP may change without notice.
         * @return the protocol field value
         */
        @Nullable public Network.CookiePartitionKey partitionKey() {
            return Network.CookiePartitionKey.fromMap(objectMap(value("partitionKey")));
        }
        public static final class Builder {
            private final Map<String, Object> values = new LinkedHashMap<>();
            /**
             * Name of the cookies to remove.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder name(@Nullable String value) {
                if (value == null) values.remove("name");
                else values.put("name", jsonValue(value));
                return this;
            }
            /**
             * If specified, deletes all the cookies with the given name where domain and path match provided URL.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder url(@Nullable String value) {
                if (value == null) values.remove("url");
                else values.put("url", jsonValue(value));
                return this;
            }
            /**
             * If specified, deletes only cookies with the exact domain.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder domain(@Nullable String value) {
                if (value == null) values.remove("domain");
                else values.put("domain", jsonValue(value));
                return this;
            }
            /**
             * If specified, deletes only cookies with the exact path.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder path(@Nullable String value) {
                if (value == null) values.remove("path");
                else values.put("path", jsonValue(value));
                return this;
            }
            /**
             * If specified, deletes only cookies with the the given name and partitionKey where all partition key attributes match the cookie partition key attribute.
             * <p><b>Experimental:</b> this part of CDP may change without notice.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder partitionKey(@Nullable Network.CookiePartitionKey value) {
                if (value == null) values.remove("partitionKey");
                else values.put("partitionKey", jsonValue(value));
                return this;
            }
            public DeleteCookiesParams build() {
                if (!values.containsKey("name")) throw new IllegalStateException("Missing required CDP field: name");
                return new DeleteCookiesParams(values);
            }
        }
    }
    /**
     * Deletes browser cookies with matching name and url or domain/path/partitionKey pair.
     */
    public static final class DeleteCookiesResult extends CdpObject {
        private DeleteCookiesResult(Map<String, Object> values) { super(values); }
        @Nullable public static DeleteCookiesResult fromMap(@Nullable Map<String, Object> values) {
            return values == null ? null : new DeleteCookiesResult(values);
        }
        public static Builder builder() { return new Builder(); }
        public static final class Builder {
            private final Map<String, Object> values = new LinkedHashMap<>();
            public DeleteCookiesResult build() {
                return new DeleteCookiesResult(values);
            }
        }
    }
    /**
     * Disables network tracking, prevents network events from being sent to the client.
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
     * Disables network tracking, prevents network events from being sent to the client.
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
     * Activates emulation of network conditions. This command is deprecated in favor of the emulateNetworkConditionsByRule and overrideNetworkState commands, which can be used together to the same effect.
     * @deprecated Deprecated by the Chromium DevTools Protocol.
     */
    @Deprecated
    public static final class EmulateNetworkConditionsParams extends CdpObject {
        private EmulateNetworkConditionsParams(Map<String, Object> values) { super(values); }
        @Nullable public static EmulateNetworkConditionsParams fromMap(@Nullable Map<String, Object> values) {
            return values == null ? null : new EmulateNetworkConditionsParams(values);
        }
        public static Builder builder() { return new Builder(); }
        /**
         * True to emulate internet disconnection.
         * @return the protocol field value
         */
        @Nullable public Boolean offline() {
            return (Boolean) value("offline");
        }
        /**
         * Minimum latency from request sent to response headers received (ms).
         * @return the protocol field value
         */
        @Nullable public Double latency() {
            return numberAsDouble(value("latency"));
        }
        /**
         * Maximal aggregated download throughput (bytes/sec). -1 disables download throttling.
         * @return the protocol field value
         */
        @Nullable public Double downloadThroughput() {
            return numberAsDouble(value("downloadThroughput"));
        }
        /**
         * Maximal aggregated upload throughput (bytes/sec). -1 disables upload throttling.
         * @return the protocol field value
         */
        @Nullable public Double uploadThroughput() {
            return numberAsDouble(value("uploadThroughput"));
        }
        /**
         * Connection type if known.
         * @return the protocol field value
         */
        @Nullable public String connectionType() {
            return (String) value("connectionType");
        }
        /**
         * WebRTC packet loss (percent, 0-100). 0 disables packet loss emulation, 100 drops all the packets.
         * <p><b>Experimental:</b> this part of CDP may change without notice.
         * @return the protocol field value
         */
        @Nullable public Double packetLoss() {
            return numberAsDouble(value("packetLoss"));
        }
        /**
         * WebRTC packet queue length (packet). 0 removes any queue length limitations.
         * <p><b>Experimental:</b> this part of CDP may change without notice.
         * @return the protocol field value
         */
        @Nullable public Long packetQueueLength() {
            return numberAsLong(value("packetQueueLength"));
        }
        /**
         * WebRTC packetReordering feature.
         * <p><b>Experimental:</b> this part of CDP may change without notice.
         * @return the protocol field value
         */
        @Nullable public Boolean packetReordering() {
            return (Boolean) value("packetReordering");
        }
        public static final class Builder {
            private final Map<String, Object> values = new LinkedHashMap<>();
            /**
             * True to emulate internet disconnection.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder offline(@Nullable Boolean value) {
                if (value == null) values.remove("offline");
                else values.put("offline", jsonValue(value));
                return this;
            }
            /**
             * Minimum latency from request sent to response headers received (ms).
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder latency(@Nullable Double value) {
                if (value == null) values.remove("latency");
                else values.put("latency", jsonValue(value));
                return this;
            }
            /**
             * Maximal aggregated download throughput (bytes/sec). -1 disables download throttling.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder downloadThroughput(@Nullable Double value) {
                if (value == null) values.remove("downloadThroughput");
                else values.put("downloadThroughput", jsonValue(value));
                return this;
            }
            /**
             * Maximal aggregated upload throughput (bytes/sec). -1 disables upload throttling.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder uploadThroughput(@Nullable Double value) {
                if (value == null) values.remove("uploadThroughput");
                else values.put("uploadThroughput", jsonValue(value));
                return this;
            }
            /**
             * Connection type if known.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder connectionType(@Nullable String value) {
                if (value == null) values.remove("connectionType");
                else values.put("connectionType", jsonValue(value));
                return this;
            }
            /**
             * WebRTC packet loss (percent, 0-100). 0 disables packet loss emulation, 100 drops all the packets.
             * <p><b>Experimental:</b> this part of CDP may change without notice.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder packetLoss(@Nullable Double value) {
                if (value == null) values.remove("packetLoss");
                else values.put("packetLoss", jsonValue(value));
                return this;
            }
            /**
             * WebRTC packet queue length (packet). 0 removes any queue length limitations.
             * <p><b>Experimental:</b> this part of CDP may change without notice.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder packetQueueLength(@Nullable Long value) {
                if (value == null) values.remove("packetQueueLength");
                else values.put("packetQueueLength", jsonValue(value));
                return this;
            }
            /**
             * WebRTC packetReordering feature.
             * <p><b>Experimental:</b> this part of CDP may change without notice.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder packetReordering(@Nullable Boolean value) {
                if (value == null) values.remove("packetReordering");
                else values.put("packetReordering", jsonValue(value));
                return this;
            }
            public EmulateNetworkConditionsParams build() {
                if (!values.containsKey("offline")) throw new IllegalStateException("Missing required CDP field: offline");
                if (!values.containsKey("latency")) throw new IllegalStateException("Missing required CDP field: latency");
                if (!values.containsKey("downloadThroughput")) throw new IllegalStateException("Missing required CDP field: downloadThroughput");
                if (!values.containsKey("uploadThroughput")) throw new IllegalStateException("Missing required CDP field: uploadThroughput");
                return new EmulateNetworkConditionsParams(values);
            }
        }
    }
    /**
     * Activates emulation of network conditions. This command is deprecated in favor of the emulateNetworkConditionsByRule and overrideNetworkState commands, which can be used together to the same effect.
     * @deprecated Deprecated by the Chromium DevTools Protocol.
     */
    @Deprecated
    public static final class EmulateNetworkConditionsResult extends CdpObject {
        private EmulateNetworkConditionsResult(Map<String, Object> values) { super(values); }
        @Nullable public static EmulateNetworkConditionsResult fromMap(@Nullable Map<String, Object> values) {
            return values == null ? null : new EmulateNetworkConditionsResult(values);
        }
        public static Builder builder() { return new Builder(); }
        public static final class Builder {
            private final Map<String, Object> values = new LinkedHashMap<>();
            public EmulateNetworkConditionsResult build() {
                return new EmulateNetworkConditionsResult(values);
            }
        }
    }
    /**
     * Activates emulation of network conditions for individual requests using URL match patterns. Unlike the deprecated Network.emulateNetworkConditions this method does not affect {@code navigator} state. Use Network.overrideNetworkState to explicitly modify {@code navigator} behavior.
     * <p><b>Experimental:</b> this part of CDP may change without notice.
     */
    public static final class EmulateNetworkConditionsByRuleParams extends CdpObject {
        private EmulateNetworkConditionsByRuleParams(Map<String, Object> values) { super(values); }
        @Nullable public static EmulateNetworkConditionsByRuleParams fromMap(@Nullable Map<String, Object> values) {
            return values == null ? null : new EmulateNetworkConditionsByRuleParams(values);
        }
        public static Builder builder() { return new Builder(); }
        /**
         * True to emulate internet disconnection. Deprecated, use the offline property in matchedNetworkConditions or emulateOfflineServiceWorker instead.
         * @return the protocol field value
         * @deprecated Deprecated by the Chromium DevTools Protocol.
         */
        @Deprecated
        @Nullable public Boolean offline() {
            return (Boolean) value("offline");
        }
        /**
         * True to emulate offline service worker.
         * @return the protocol field value
         */
        @Nullable public Boolean emulateOfflineServiceWorker() {
            return (Boolean) value("emulateOfflineServiceWorker");
        }
        /**
         * Configure conditions for matching requests. If multiple entries match a request, the first entry wins. Global conditions can be configured by leaving the urlPattern for the conditions empty. These global conditions are also applied for throttling of p2p connections.
         * @return the protocol field value
         */
        @Nullable public java.util.List<Network.NetworkConditions> matchedNetworkConditions() {
            return list(value("matchedNetworkConditions"), element0 -> Network.NetworkConditions.fromMap(objectMap(element0)));
        }
        public static final class Builder {
            private final Map<String, Object> values = new LinkedHashMap<>();
            /**
             * True to emulate internet disconnection. Deprecated, use the offline property in matchedNetworkConditions or emulateOfflineServiceWorker instead.
             * @param value field value; null removes an optional value
             * @return this builder
             * @deprecated Deprecated by the Chromium DevTools Protocol.
             */
            @Deprecated
            public Builder offline(@Nullable Boolean value) {
                if (value == null) values.remove("offline");
                else values.put("offline", jsonValue(value));
                return this;
            }
            /**
             * True to emulate offline service worker.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder emulateOfflineServiceWorker(@Nullable Boolean value) {
                if (value == null) values.remove("emulateOfflineServiceWorker");
                else values.put("emulateOfflineServiceWorker", jsonValue(value));
                return this;
            }
            /**
             * Configure conditions for matching requests. If multiple entries match a request, the first entry wins. Global conditions can be configured by leaving the urlPattern for the conditions empty. These global conditions are also applied for throttling of p2p connections.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder matchedNetworkConditions(@Nullable java.util.List<Network.NetworkConditions> value) {
                if (value == null) values.remove("matchedNetworkConditions");
                else values.put("matchedNetworkConditions", jsonValue(value));
                return this;
            }
            public EmulateNetworkConditionsByRuleParams build() {
                if (!values.containsKey("matchedNetworkConditions")) throw new IllegalStateException("Missing required CDP field: matchedNetworkConditions");
                return new EmulateNetworkConditionsByRuleParams(values);
            }
        }
    }
    /**
     * Activates emulation of network conditions for individual requests using URL match patterns. Unlike the deprecated Network.emulateNetworkConditions this method does not affect {@code navigator} state. Use Network.overrideNetworkState to explicitly modify {@code navigator} behavior.
     * <p><b>Experimental:</b> this part of CDP may change without notice.
     */
    public static final class EmulateNetworkConditionsByRuleResult extends CdpObject {
        private EmulateNetworkConditionsByRuleResult(Map<String, Object> values) { super(values); }
        @Nullable public static EmulateNetworkConditionsByRuleResult fromMap(@Nullable Map<String, Object> values) {
            return values == null ? null : new EmulateNetworkConditionsByRuleResult(values);
        }
        public static Builder builder() { return new Builder(); }
        /**
         * An id for each entry in matchedNetworkConditions. The id will be included in the requestWillBeSentExtraInfo for requests affected by a rule.
         * @return the protocol field value
         */
        @Nullable public java.util.List<String> ruleIds() {
            return list(value("ruleIds"), element0 -> (String) element0);
        }
        public static final class Builder {
            private final Map<String, Object> values = new LinkedHashMap<>();
            /**
             * An id for each entry in matchedNetworkConditions. The id will be included in the requestWillBeSentExtraInfo for requests affected by a rule.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder ruleIds(@Nullable java.util.List<String> value) {
                if (value == null) values.remove("ruleIds");
                else values.put("ruleIds", jsonValue(value));
                return this;
            }
            public EmulateNetworkConditionsByRuleResult build() {
                if (!values.containsKey("ruleIds")) throw new IllegalStateException("Missing required CDP field: ruleIds");
                return new EmulateNetworkConditionsByRuleResult(values);
            }
        }
    }
    /**
     * Override the state of navigator.onLine and navigator.connection.
     * <p><b>Experimental:</b> this part of CDP may change without notice.
     */
    public static final class OverrideNetworkStateParams extends CdpObject {
        private OverrideNetworkStateParams(Map<String, Object> values) { super(values); }
        @Nullable public static OverrideNetworkStateParams fromMap(@Nullable Map<String, Object> values) {
            return values == null ? null : new OverrideNetworkStateParams(values);
        }
        public static Builder builder() { return new Builder(); }
        /**
         * True to emulate internet disconnection.
         * @return the protocol field value
         */
        @Nullable public Boolean offline() {
            return (Boolean) value("offline");
        }
        /**
         * Minimum latency from request sent to response headers received (ms).
         * @return the protocol field value
         */
        @Nullable public Double latency() {
            return numberAsDouble(value("latency"));
        }
        /**
         * Maximal aggregated download throughput (bytes/sec). -1 disables download throttling.
         * @return the protocol field value
         */
        @Nullable public Double downloadThroughput() {
            return numberAsDouble(value("downloadThroughput"));
        }
        /**
         * Maximal aggregated upload throughput (bytes/sec). -1 disables upload throttling.
         * @return the protocol field value
         */
        @Nullable public Double uploadThroughput() {
            return numberAsDouble(value("uploadThroughput"));
        }
        /**
         * Connection type if known.
         * @return the protocol field value
         */
        @Nullable public String connectionType() {
            return (String) value("connectionType");
        }
        public static final class Builder {
            private final Map<String, Object> values = new LinkedHashMap<>();
            /**
             * True to emulate internet disconnection.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder offline(@Nullable Boolean value) {
                if (value == null) values.remove("offline");
                else values.put("offline", jsonValue(value));
                return this;
            }
            /**
             * Minimum latency from request sent to response headers received (ms).
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder latency(@Nullable Double value) {
                if (value == null) values.remove("latency");
                else values.put("latency", jsonValue(value));
                return this;
            }
            /**
             * Maximal aggregated download throughput (bytes/sec). -1 disables download throttling.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder downloadThroughput(@Nullable Double value) {
                if (value == null) values.remove("downloadThroughput");
                else values.put("downloadThroughput", jsonValue(value));
                return this;
            }
            /**
             * Maximal aggregated upload throughput (bytes/sec). -1 disables upload throttling.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder uploadThroughput(@Nullable Double value) {
                if (value == null) values.remove("uploadThroughput");
                else values.put("uploadThroughput", jsonValue(value));
                return this;
            }
            /**
             * Connection type if known.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder connectionType(@Nullable String value) {
                if (value == null) values.remove("connectionType");
                else values.put("connectionType", jsonValue(value));
                return this;
            }
            public OverrideNetworkStateParams build() {
                if (!values.containsKey("offline")) throw new IllegalStateException("Missing required CDP field: offline");
                if (!values.containsKey("latency")) throw new IllegalStateException("Missing required CDP field: latency");
                if (!values.containsKey("downloadThroughput")) throw new IllegalStateException("Missing required CDP field: downloadThroughput");
                if (!values.containsKey("uploadThroughput")) throw new IllegalStateException("Missing required CDP field: uploadThroughput");
                return new OverrideNetworkStateParams(values);
            }
        }
    }
    /**
     * Override the state of navigator.onLine and navigator.connection.
     * <p><b>Experimental:</b> this part of CDP may change without notice.
     */
    public static final class OverrideNetworkStateResult extends CdpObject {
        private OverrideNetworkStateResult(Map<String, Object> values) { super(values); }
        @Nullable public static OverrideNetworkStateResult fromMap(@Nullable Map<String, Object> values) {
            return values == null ? null : new OverrideNetworkStateResult(values);
        }
        public static Builder builder() { return new Builder(); }
        public static final class Builder {
            private final Map<String, Object> values = new LinkedHashMap<>();
            public OverrideNetworkStateResult build() {
                return new OverrideNetworkStateResult(values);
            }
        }
    }
    /**
     * Enables network tracking, network events will now be delivered to the client.
     */
    public static final class EnableParams extends CdpObject {
        private EnableParams(Map<String, Object> values) { super(values); }
        @Nullable public static EnableParams fromMap(@Nullable Map<String, Object> values) {
            return values == null ? null : new EnableParams(values);
        }
        public static Builder builder() { return new Builder(); }
        /**
         * Buffer size in bytes to use when preserving network payloads (XHRs, etc). This is the maximum number of bytes that will be collected by this DevTools session.
         * <p><b>Experimental:</b> this part of CDP may change without notice.
         * @return the protocol field value
         */
        @Nullable public Long maxTotalBufferSize() {
            return numberAsLong(value("maxTotalBufferSize"));
        }
        /**
         * Per-resource buffer size in bytes to use when preserving network payloads (XHRs, etc).
         * <p><b>Experimental:</b> this part of CDP may change without notice.
         * @return the protocol field value
         */
        @Nullable public Long maxResourceBufferSize() {
            return numberAsLong(value("maxResourceBufferSize"));
        }
        /**
         * Longest post body size (in bytes) that would be included in requestWillBeSent notification
         * @return the protocol field value
         */
        @Nullable public Long maxPostDataSize() {
            return numberAsLong(value("maxPostDataSize"));
        }
        /**
         * Whether DirectSocket chunk send/receive events should be reported.
         * <p><b>Experimental:</b> this part of CDP may change without notice.
         * @return the protocol field value
         */
        @Nullable public Boolean reportDirectSocketTraffic() {
            return (Boolean) value("reportDirectSocketTraffic");
        }
        /**
         * Enable storing response bodies outside of renderer, so that these survive a cross-process navigation. Requires maxTotalBufferSize to be set. Currently defaults to false. This field is being deprecated in favor of the dedicated configureDurableMessages command, due to the possibility of deadlocks when awaiting Network.enable before issuing Runtime.runIfWaitingForDebugger.
         * <p><b>Experimental:</b> this part of CDP may change without notice.
         * @return the protocol field value
         */
        @Nullable public Boolean enableDurableMessages() {
            return (Boolean) value("enableDurableMessages");
        }
        public static final class Builder {
            private final Map<String, Object> values = new LinkedHashMap<>();
            /**
             * Buffer size in bytes to use when preserving network payloads (XHRs, etc). This is the maximum number of bytes that will be collected by this DevTools session.
             * <p><b>Experimental:</b> this part of CDP may change without notice.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder maxTotalBufferSize(@Nullable Long value) {
                if (value == null) values.remove("maxTotalBufferSize");
                else values.put("maxTotalBufferSize", jsonValue(value));
                return this;
            }
            /**
             * Per-resource buffer size in bytes to use when preserving network payloads (XHRs, etc).
             * <p><b>Experimental:</b> this part of CDP may change without notice.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder maxResourceBufferSize(@Nullable Long value) {
                if (value == null) values.remove("maxResourceBufferSize");
                else values.put("maxResourceBufferSize", jsonValue(value));
                return this;
            }
            /**
             * Longest post body size (in bytes) that would be included in requestWillBeSent notification
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder maxPostDataSize(@Nullable Long value) {
                if (value == null) values.remove("maxPostDataSize");
                else values.put("maxPostDataSize", jsonValue(value));
                return this;
            }
            /**
             * Whether DirectSocket chunk send/receive events should be reported.
             * <p><b>Experimental:</b> this part of CDP may change without notice.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder reportDirectSocketTraffic(@Nullable Boolean value) {
                if (value == null) values.remove("reportDirectSocketTraffic");
                else values.put("reportDirectSocketTraffic", jsonValue(value));
                return this;
            }
            /**
             * Enable storing response bodies outside of renderer, so that these survive a cross-process navigation. Requires maxTotalBufferSize to be set. Currently defaults to false. This field is being deprecated in favor of the dedicated configureDurableMessages command, due to the possibility of deadlocks when awaiting Network.enable before issuing Runtime.runIfWaitingForDebugger.
             * <p><b>Experimental:</b> this part of CDP may change without notice.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder enableDurableMessages(@Nullable Boolean value) {
                if (value == null) values.remove("enableDurableMessages");
                else values.put("enableDurableMessages", jsonValue(value));
                return this;
            }
            public EnableParams build() {
                return new EnableParams(values);
            }
        }
    }
    /**
     * Enables network tracking, network events will now be delivered to the client.
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
     * Configures storing response bodies outside of renderer, so that these survive a cross-process navigation. If maxTotalBufferSize is not set, durable messages are disabled.
     * <p><b>Experimental:</b> this part of CDP may change without notice.
     */
    public static final class ConfigureDurableMessagesParams extends CdpObject {
        private ConfigureDurableMessagesParams(Map<String, Object> values) { super(values); }
        @Nullable public static ConfigureDurableMessagesParams fromMap(@Nullable Map<String, Object> values) {
            return values == null ? null : new ConfigureDurableMessagesParams(values);
        }
        public static Builder builder() { return new Builder(); }
        /**
         * Buffer size in bytes to use when preserving network payloads (XHRs, etc).
         * @return the protocol field value
         */
        @Nullable public Long maxTotalBufferSize() {
            return numberAsLong(value("maxTotalBufferSize"));
        }
        /**
         * Per-resource buffer size in bytes to use when preserving network payloads (XHRs, etc).
         * @return the protocol field value
         */
        @Nullable public Long maxResourceBufferSize() {
            return numberAsLong(value("maxResourceBufferSize"));
        }
        public static final class Builder {
            private final Map<String, Object> values = new LinkedHashMap<>();
            /**
             * Buffer size in bytes to use when preserving network payloads (XHRs, etc).
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder maxTotalBufferSize(@Nullable Long value) {
                if (value == null) values.remove("maxTotalBufferSize");
                else values.put("maxTotalBufferSize", jsonValue(value));
                return this;
            }
            /**
             * Per-resource buffer size in bytes to use when preserving network payloads (XHRs, etc).
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder maxResourceBufferSize(@Nullable Long value) {
                if (value == null) values.remove("maxResourceBufferSize");
                else values.put("maxResourceBufferSize", jsonValue(value));
                return this;
            }
            public ConfigureDurableMessagesParams build() {
                return new ConfigureDurableMessagesParams(values);
            }
        }
    }
    /**
     * Configures storing response bodies outside of renderer, so that these survive a cross-process navigation. If maxTotalBufferSize is not set, durable messages are disabled.
     * <p><b>Experimental:</b> this part of CDP may change without notice.
     */
    public static final class ConfigureDurableMessagesResult extends CdpObject {
        private ConfigureDurableMessagesResult(Map<String, Object> values) { super(values); }
        @Nullable public static ConfigureDurableMessagesResult fromMap(@Nullable Map<String, Object> values) {
            return values == null ? null : new ConfigureDurableMessagesResult(values);
        }
        public static Builder builder() { return new Builder(); }
        public static final class Builder {
            private final Map<String, Object> values = new LinkedHashMap<>();
            public ConfigureDurableMessagesResult build() {
                return new ConfigureDurableMessagesResult(values);
            }
        }
    }
    /**
     * Returns all browser cookies. Depending on the backend support, will return detailed cookie information in the {@code cookies} field. Deprecated. Use Storage.getCookies instead.
     * @deprecated Deprecated by the Chromium DevTools Protocol.
     */
    @Deprecated
    public static final class GetAllCookiesParams extends CdpObject {
        private GetAllCookiesParams(Map<String, Object> values) { super(values); }
        @Nullable public static GetAllCookiesParams fromMap(@Nullable Map<String, Object> values) {
            return values == null ? null : new GetAllCookiesParams(values);
        }
        public static Builder builder() { return new Builder(); }
        public static final class Builder {
            private final Map<String, Object> values = new LinkedHashMap<>();
            public GetAllCookiesParams build() {
                return new GetAllCookiesParams(values);
            }
        }
    }
    /**
     * Returns all browser cookies. Depending on the backend support, will return detailed cookie information in the {@code cookies} field. Deprecated. Use Storage.getCookies instead.
     * @deprecated Deprecated by the Chromium DevTools Protocol.
     */
    @Deprecated
    public static final class GetAllCookiesResult extends CdpObject {
        private GetAllCookiesResult(Map<String, Object> values) { super(values); }
        @Nullable public static GetAllCookiesResult fromMap(@Nullable Map<String, Object> values) {
            return values == null ? null : new GetAllCookiesResult(values);
        }
        public static Builder builder() { return new Builder(); }
        /**
         * Array of cookie objects.
         * @return the protocol field value
         */
        @Nullable public java.util.List<Network.Cookie> cookies() {
            return list(value("cookies"), element0 -> Network.Cookie.fromMap(objectMap(element0)));
        }
        public static final class Builder {
            private final Map<String, Object> values = new LinkedHashMap<>();
            /**
             * Array of cookie objects.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder cookies(@Nullable java.util.List<Network.Cookie> value) {
                if (value == null) values.remove("cookies");
                else values.put("cookies", jsonValue(value));
                return this;
            }
            public GetAllCookiesResult build() {
                if (!values.containsKey("cookies")) throw new IllegalStateException("Missing required CDP field: cookies");
                return new GetAllCookiesResult(values);
            }
        }
    }
    /**
     * Returns the DER-encoded certificate.
     * <p><b>Experimental:</b> this part of CDP may change without notice.
     */
    public static final class GetCertificateParams extends CdpObject {
        private GetCertificateParams(Map<String, Object> values) { super(values); }
        @Nullable public static GetCertificateParams fromMap(@Nullable Map<String, Object> values) {
            return values == null ? null : new GetCertificateParams(values);
        }
        public static Builder builder() { return new Builder(); }
        /**
         * Origin to get certificate for.
         * @return the protocol field value
         */
        @Nullable public String origin() {
            return (String) value("origin");
        }
        public static final class Builder {
            private final Map<String, Object> values = new LinkedHashMap<>();
            /**
             * Origin to get certificate for.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder origin(@Nullable String value) {
                if (value == null) values.remove("origin");
                else values.put("origin", jsonValue(value));
                return this;
            }
            public GetCertificateParams build() {
                if (!values.containsKey("origin")) throw new IllegalStateException("Missing required CDP field: origin");
                return new GetCertificateParams(values);
            }
        }
    }
    /**
     * Returns the DER-encoded certificate.
     * <p><b>Experimental:</b> this part of CDP may change without notice.
     */
    public static final class GetCertificateResult extends CdpObject {
        private GetCertificateResult(Map<String, Object> values) { super(values); }
        @Nullable public static GetCertificateResult fromMap(@Nullable Map<String, Object> values) {
            return values == null ? null : new GetCertificateResult(values);
        }
        public static Builder builder() { return new Builder(); }
        /**
         * Returns the tableNames field.
         * @return the protocol field value
         */
        @Nullable public java.util.List<String> tableNames() {
            return list(value("tableNames"), element0 -> (String) element0);
        }
        public static final class Builder {
            private final Map<String, Object> values = new LinkedHashMap<>();
            /**
             * Sets the tableNames field.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder tableNames(@Nullable java.util.List<String> value) {
                if (value == null) values.remove("tableNames");
                else values.put("tableNames", jsonValue(value));
                return this;
            }
            public GetCertificateResult build() {
                if (!values.containsKey("tableNames")) throw new IllegalStateException("Missing required CDP field: tableNames");
                return new GetCertificateResult(values);
            }
        }
    }
    /**
     * Returns all browser cookies for the current URL. Depending on the backend support, will return detailed cookie information in the {@code cookies} field.
     */
    public static final class GetCookiesParams extends CdpObject {
        private GetCookiesParams(Map<String, Object> values) { super(values); }
        @Nullable public static GetCookiesParams fromMap(@Nullable Map<String, Object> values) {
            return values == null ? null : new GetCookiesParams(values);
        }
        public static Builder builder() { return new Builder(); }
        /**
         * The list of URLs for which applicable cookies will be fetched. If not specified, it&#x27;s assumed to be set to the list containing the URLs of the page and all of its subframes.
         * @return the protocol field value
         */
        @Nullable public java.util.List<String> urls() {
            return list(value("urls"), element0 -> (String) element0);
        }
        public static final class Builder {
            private final Map<String, Object> values = new LinkedHashMap<>();
            /**
             * The list of URLs for which applicable cookies will be fetched. If not specified, it&#x27;s assumed to be set to the list containing the URLs of the page and all of its subframes.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder urls(@Nullable java.util.List<String> value) {
                if (value == null) values.remove("urls");
                else values.put("urls", jsonValue(value));
                return this;
            }
            public GetCookiesParams build() {
                return new GetCookiesParams(values);
            }
        }
    }
    /**
     * Returns all browser cookies for the current URL. Depending on the backend support, will return detailed cookie information in the {@code cookies} field.
     */
    public static final class GetCookiesResult extends CdpObject {
        private GetCookiesResult(Map<String, Object> values) { super(values); }
        @Nullable public static GetCookiesResult fromMap(@Nullable Map<String, Object> values) {
            return values == null ? null : new GetCookiesResult(values);
        }
        public static Builder builder() { return new Builder(); }
        /**
         * Array of cookie objects.
         * @return the protocol field value
         */
        @Nullable public java.util.List<Network.Cookie> cookies() {
            return list(value("cookies"), element0 -> Network.Cookie.fromMap(objectMap(element0)));
        }
        public static final class Builder {
            private final Map<String, Object> values = new LinkedHashMap<>();
            /**
             * Array of cookie objects.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder cookies(@Nullable java.util.List<Network.Cookie> value) {
                if (value == null) values.remove("cookies");
                else values.put("cookies", jsonValue(value));
                return this;
            }
            public GetCookiesResult build() {
                if (!values.containsKey("cookies")) throw new IllegalStateException("Missing required CDP field: cookies");
                return new GetCookiesResult(values);
            }
        }
    }
    /**
     * Returns content served for the given request.
     */
    public static final class GetResponseBodyParams extends CdpObject {
        private GetResponseBodyParams(Map<String, Object> values) { super(values); }
        @Nullable public static GetResponseBodyParams fromMap(@Nullable Map<String, Object> values) {
            return values == null ? null : new GetResponseBodyParams(values);
        }
        public static Builder builder() { return new Builder(); }
        /**
         * Identifier of the network request to get content for.
         * @return the protocol field value
         */
        @Nullable public String requestId() {
            return (String) value("requestId");
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
            public GetResponseBodyParams build() {
                if (!values.containsKey("requestId")) throw new IllegalStateException("Missing required CDP field: requestId");
                return new GetResponseBodyParams(values);
            }
        }
    }
    /**
     * Returns content served for the given request.
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
     * Returns post data sent with the request. Returns an error when no data was sent with the request.
     */
    public static final class GetRequestPostDataParams extends CdpObject {
        private GetRequestPostDataParams(Map<String, Object> values) { super(values); }
        @Nullable public static GetRequestPostDataParams fromMap(@Nullable Map<String, Object> values) {
            return values == null ? null : new GetRequestPostDataParams(values);
        }
        public static Builder builder() { return new Builder(); }
        /**
         * Identifier of the network request to get content for.
         * @return the protocol field value
         */
        @Nullable public String requestId() {
            return (String) value("requestId");
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
            public GetRequestPostDataParams build() {
                if (!values.containsKey("requestId")) throw new IllegalStateException("Missing required CDP field: requestId");
                return new GetRequestPostDataParams(values);
            }
        }
    }
    /**
     * Returns post data sent with the request. Returns an error when no data was sent with the request.
     */
    public static final class GetRequestPostDataResult extends CdpObject {
        private GetRequestPostDataResult(Map<String, Object> values) { super(values); }
        @Nullable public static GetRequestPostDataResult fromMap(@Nullable Map<String, Object> values) {
            return values == null ? null : new GetRequestPostDataResult(values);
        }
        public static Builder builder() { return new Builder(); }
        /**
         * Request body string, omitting files from multipart requests
         * @return the protocol field value
         */
        @Nullable public String postData() {
            return (String) value("postData");
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
             * Request body string, omitting files from multipart requests
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder postData(@Nullable String value) {
                if (value == null) values.remove("postData");
                else values.put("postData", jsonValue(value));
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
            public GetRequestPostDataResult build() {
                if (!values.containsKey("postData")) throw new IllegalStateException("Missing required CDP field: postData");
                if (!values.containsKey("base64Encoded")) throw new IllegalStateException("Missing required CDP field: base64Encoded");
                return new GetRequestPostDataResult(values);
            }
        }
    }
    /**
     * Returns content served for the given currently intercepted request.
     * <p><b>Experimental:</b> this part of CDP may change without notice.
     */
    public static final class GetResponseBodyForInterceptionParams extends CdpObject {
        private GetResponseBodyForInterceptionParams(Map<String, Object> values) { super(values); }
        @Nullable public static GetResponseBodyForInterceptionParams fromMap(@Nullable Map<String, Object> values) {
            return values == null ? null : new GetResponseBodyForInterceptionParams(values);
        }
        public static Builder builder() { return new Builder(); }
        /**
         * Identifier for the intercepted request to get body for.
         * @return the protocol field value
         */
        @Nullable public String interceptionId() {
            return (String) value("interceptionId");
        }
        public static final class Builder {
            private final Map<String, Object> values = new LinkedHashMap<>();
            /**
             * Identifier for the intercepted request to get body for.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder interceptionId(@Nullable String value) {
                if (value == null) values.remove("interceptionId");
                else values.put("interceptionId", jsonValue(value));
                return this;
            }
            public GetResponseBodyForInterceptionParams build() {
                if (!values.containsKey("interceptionId")) throw new IllegalStateException("Missing required CDP field: interceptionId");
                return new GetResponseBodyForInterceptionParams(values);
            }
        }
    }
    /**
     * Returns content served for the given currently intercepted request.
     * <p><b>Experimental:</b> this part of CDP may change without notice.
     */
    public static final class GetResponseBodyForInterceptionResult extends CdpObject {
        private GetResponseBodyForInterceptionResult(Map<String, Object> values) { super(values); }
        @Nullable public static GetResponseBodyForInterceptionResult fromMap(@Nullable Map<String, Object> values) {
            return values == null ? null : new GetResponseBodyForInterceptionResult(values);
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
            public GetResponseBodyForInterceptionResult build() {
                if (!values.containsKey("body")) throw new IllegalStateException("Missing required CDP field: body");
                if (!values.containsKey("base64Encoded")) throw new IllegalStateException("Missing required CDP field: base64Encoded");
                return new GetResponseBodyForInterceptionResult(values);
            }
        }
    }
    /**
     * Returns a handle to the stream representing the response body. Note that after this command, the intercepted request can&#x27;t be continued as is -- you either need to cancel it or to provide the response body. The stream only supports sequential read, IO.read will fail if the position is specified.
     * <p><b>Experimental:</b> this part of CDP may change without notice.
     */
    public static final class TakeResponseBodyForInterceptionAsStreamParams extends CdpObject {
        private TakeResponseBodyForInterceptionAsStreamParams(Map<String, Object> values) { super(values); }
        @Nullable public static TakeResponseBodyForInterceptionAsStreamParams fromMap(@Nullable Map<String, Object> values) {
            return values == null ? null : new TakeResponseBodyForInterceptionAsStreamParams(values);
        }
        public static Builder builder() { return new Builder(); }
        /**
         * Returns the interceptionId field.
         * @return the protocol field value
         */
        @Nullable public String interceptionId() {
            return (String) value("interceptionId");
        }
        public static final class Builder {
            private final Map<String, Object> values = new LinkedHashMap<>();
            /**
             * Sets the interceptionId field.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder interceptionId(@Nullable String value) {
                if (value == null) values.remove("interceptionId");
                else values.put("interceptionId", jsonValue(value));
                return this;
            }
            public TakeResponseBodyForInterceptionAsStreamParams build() {
                if (!values.containsKey("interceptionId")) throw new IllegalStateException("Missing required CDP field: interceptionId");
                return new TakeResponseBodyForInterceptionAsStreamParams(values);
            }
        }
    }
    /**
     * Returns a handle to the stream representing the response body. Note that after this command, the intercepted request can&#x27;t be continued as is -- you either need to cancel it or to provide the response body. The stream only supports sequential read, IO.read will fail if the position is specified.
     * <p><b>Experimental:</b> this part of CDP may change without notice.
     */
    public static final class TakeResponseBodyForInterceptionAsStreamResult extends CdpObject {
        private TakeResponseBodyForInterceptionAsStreamResult(Map<String, Object> values) { super(values); }
        @Nullable public static TakeResponseBodyForInterceptionAsStreamResult fromMap(@Nullable Map<String, Object> values) {
            return values == null ? null : new TakeResponseBodyForInterceptionAsStreamResult(values);
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
            public TakeResponseBodyForInterceptionAsStreamResult build() {
                if (!values.containsKey("stream")) throw new IllegalStateException("Missing required CDP field: stream");
                return new TakeResponseBodyForInterceptionAsStreamResult(values);
            }
        }
    }
    /**
     * This method sends a new XMLHttpRequest which is identical to the original one. The following parameters should be identical: method, url, async, request body, extra headers, withCredentials attribute, user, password.
     * <p><b>Experimental:</b> this part of CDP may change without notice.
     */
    public static final class ReplayXHRParams extends CdpObject {
        private ReplayXHRParams(Map<String, Object> values) { super(values); }
        @Nullable public static ReplayXHRParams fromMap(@Nullable Map<String, Object> values) {
            return values == null ? null : new ReplayXHRParams(values);
        }
        public static Builder builder() { return new Builder(); }
        /**
         * Identifier of XHR to replay.
         * @return the protocol field value
         */
        @Nullable public String requestId() {
            return (String) value("requestId");
        }
        public static final class Builder {
            private final Map<String, Object> values = new LinkedHashMap<>();
            /**
             * Identifier of XHR to replay.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder requestId(@Nullable String value) {
                if (value == null) values.remove("requestId");
                else values.put("requestId", jsonValue(value));
                return this;
            }
            public ReplayXHRParams build() {
                if (!values.containsKey("requestId")) throw new IllegalStateException("Missing required CDP field: requestId");
                return new ReplayXHRParams(values);
            }
        }
    }
    /**
     * This method sends a new XMLHttpRequest which is identical to the original one. The following parameters should be identical: method, url, async, request body, extra headers, withCredentials attribute, user, password.
     * <p><b>Experimental:</b> this part of CDP may change without notice.
     */
    public static final class ReplayXHRResult extends CdpObject {
        private ReplayXHRResult(Map<String, Object> values) { super(values); }
        @Nullable public static ReplayXHRResult fromMap(@Nullable Map<String, Object> values) {
            return values == null ? null : new ReplayXHRResult(values);
        }
        public static Builder builder() { return new Builder(); }
        public static final class Builder {
            private final Map<String, Object> values = new LinkedHashMap<>();
            public ReplayXHRResult build() {
                return new ReplayXHRResult(values);
            }
        }
    }
    /**
     * Searches for given string in response content.
     * <p><b>Experimental:</b> this part of CDP may change without notice.
     */
    public static final class SearchInResponseBodyParams extends CdpObject {
        private SearchInResponseBodyParams(Map<String, Object> values) { super(values); }
        @Nullable public static SearchInResponseBodyParams fromMap(@Nullable Map<String, Object> values) {
            return values == null ? null : new SearchInResponseBodyParams(values);
        }
        public static Builder builder() { return new Builder(); }
        /**
         * Identifier of the network response to search.
         * @return the protocol field value
         */
        @Nullable public String requestId() {
            return (String) value("requestId");
        }
        /**
         * String to search for.
         * @return the protocol field value
         */
        @Nullable public String query() {
            return (String) value("query");
        }
        /**
         * If true, search is case sensitive.
         * @return the protocol field value
         */
        @Nullable public Boolean caseSensitive() {
            return (Boolean) value("caseSensitive");
        }
        /**
         * If true, treats string parameter as regex.
         * @return the protocol field value
         */
        @Nullable public Boolean isRegex() {
            return (Boolean) value("isRegex");
        }
        public static final class Builder {
            private final Map<String, Object> values = new LinkedHashMap<>();
            /**
             * Identifier of the network response to search.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder requestId(@Nullable String value) {
                if (value == null) values.remove("requestId");
                else values.put("requestId", jsonValue(value));
                return this;
            }
            /**
             * String to search for.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder query(@Nullable String value) {
                if (value == null) values.remove("query");
                else values.put("query", jsonValue(value));
                return this;
            }
            /**
             * If true, search is case sensitive.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder caseSensitive(@Nullable Boolean value) {
                if (value == null) values.remove("caseSensitive");
                else values.put("caseSensitive", jsonValue(value));
                return this;
            }
            /**
             * If true, treats string parameter as regex.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder isRegex(@Nullable Boolean value) {
                if (value == null) values.remove("isRegex");
                else values.put("isRegex", jsonValue(value));
                return this;
            }
            public SearchInResponseBodyParams build() {
                if (!values.containsKey("requestId")) throw new IllegalStateException("Missing required CDP field: requestId");
                if (!values.containsKey("query")) throw new IllegalStateException("Missing required CDP field: query");
                return new SearchInResponseBodyParams(values);
            }
        }
    }
    /**
     * Searches for given string in response content.
     * <p><b>Experimental:</b> this part of CDP may change without notice.
     */
    public static final class SearchInResponseBodyResult extends CdpObject {
        private SearchInResponseBodyResult(Map<String, Object> values) { super(values); }
        @Nullable public static SearchInResponseBodyResult fromMap(@Nullable Map<String, Object> values) {
            return values == null ? null : new SearchInResponseBodyResult(values);
        }
        public static Builder builder() { return new Builder(); }
        /**
         * List of search matches.
         * @return the protocol field value
         */
        @Nullable public java.util.List<Debugger.SearchMatch> result() {
            return list(value("result"), element0 -> Debugger.SearchMatch.fromMap(objectMap(element0)));
        }
        public static final class Builder {
            private final Map<String, Object> values = new LinkedHashMap<>();
            /**
             * List of search matches.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder result(@Nullable java.util.List<Debugger.SearchMatch> value) {
                if (value == null) values.remove("result");
                else values.put("result", jsonValue(value));
                return this;
            }
            public SearchInResponseBodyResult build() {
                if (!values.containsKey("result")) throw new IllegalStateException("Missing required CDP field: result");
                return new SearchInResponseBodyResult(values);
            }
        }
    }
    /**
     * Blocks URLs from loading.
     * <p><b>Experimental:</b> this part of CDP may change without notice.
     */
    public static final class SetBlockedURLsParams extends CdpObject {
        private SetBlockedURLsParams(Map<String, Object> values) { super(values); }
        @Nullable public static SetBlockedURLsParams fromMap(@Nullable Map<String, Object> values) {
            return values == null ? null : new SetBlockedURLsParams(values);
        }
        public static Builder builder() { return new Builder(); }
        /**
         * Patterns to match in the order in which they are given. These patterns also take precedence over any wildcard patterns defined in {@code urls}.
         * @return the protocol field value
         */
        @Nullable public java.util.List<Network.BlockPattern> urlPatterns() {
            return list(value("urlPatterns"), element0 -> Network.BlockPattern.fromMap(objectMap(element0)));
        }
        /**
         * URL patterns to block. Wildcards (&#x27;*&#x27;) are allowed.
         * @return the protocol field value
         * @deprecated Deprecated by the Chromium DevTools Protocol.
         */
        @Deprecated
        @Nullable public java.util.List<String> urls() {
            return list(value("urls"), element0 -> (String) element0);
        }
        public static final class Builder {
            private final Map<String, Object> values = new LinkedHashMap<>();
            /**
             * Patterns to match in the order in which they are given. These patterns also take precedence over any wildcard patterns defined in {@code urls}.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder urlPatterns(@Nullable java.util.List<Network.BlockPattern> value) {
                if (value == null) values.remove("urlPatterns");
                else values.put("urlPatterns", jsonValue(value));
                return this;
            }
            /**
             * URL patterns to block. Wildcards (&#x27;*&#x27;) are allowed.
             * @param value field value; null removes an optional value
             * @return this builder
             * @deprecated Deprecated by the Chromium DevTools Protocol.
             */
            @Deprecated
            public Builder urls(@Nullable java.util.List<String> value) {
                if (value == null) values.remove("urls");
                else values.put("urls", jsonValue(value));
                return this;
            }
            public SetBlockedURLsParams build() {
                return new SetBlockedURLsParams(values);
            }
        }
    }
    /**
     * Blocks URLs from loading.
     * <p><b>Experimental:</b> this part of CDP may change without notice.
     */
    public static final class SetBlockedURLsResult extends CdpObject {
        private SetBlockedURLsResult(Map<String, Object> values) { super(values); }
        @Nullable public static SetBlockedURLsResult fromMap(@Nullable Map<String, Object> values) {
            return values == null ? null : new SetBlockedURLsResult(values);
        }
        public static Builder builder() { return new Builder(); }
        public static final class Builder {
            private final Map<String, Object> values = new LinkedHashMap<>();
            public SetBlockedURLsResult build() {
                return new SetBlockedURLsResult(values);
            }
        }
    }
    /**
     * Toggles ignoring of service worker for each request.
     */
    public static final class SetBypassServiceWorkerParams extends CdpObject {
        private SetBypassServiceWorkerParams(Map<String, Object> values) { super(values); }
        @Nullable public static SetBypassServiceWorkerParams fromMap(@Nullable Map<String, Object> values) {
            return values == null ? null : new SetBypassServiceWorkerParams(values);
        }
        public static Builder builder() { return new Builder(); }
        /**
         * Bypass service worker and load from network.
         * @return the protocol field value
         */
        @Nullable public Boolean bypass() {
            return (Boolean) value("bypass");
        }
        public static final class Builder {
            private final Map<String, Object> values = new LinkedHashMap<>();
            /**
             * Bypass service worker and load from network.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder bypass(@Nullable Boolean value) {
                if (value == null) values.remove("bypass");
                else values.put("bypass", jsonValue(value));
                return this;
            }
            public SetBypassServiceWorkerParams build() {
                if (!values.containsKey("bypass")) throw new IllegalStateException("Missing required CDP field: bypass");
                return new SetBypassServiceWorkerParams(values);
            }
        }
    }
    /**
     * Toggles ignoring of service worker for each request.
     */
    public static final class SetBypassServiceWorkerResult extends CdpObject {
        private SetBypassServiceWorkerResult(Map<String, Object> values) { super(values); }
        @Nullable public static SetBypassServiceWorkerResult fromMap(@Nullable Map<String, Object> values) {
            return values == null ? null : new SetBypassServiceWorkerResult(values);
        }
        public static Builder builder() { return new Builder(); }
        public static final class Builder {
            private final Map<String, Object> values = new LinkedHashMap<>();
            public SetBypassServiceWorkerResult build() {
                return new SetBypassServiceWorkerResult(values);
            }
        }
    }
    /**
     * Toggles ignoring cache for each request. If {@code true}, cache will not be used.
     */
    public static final class SetCacheDisabledParams extends CdpObject {
        private SetCacheDisabledParams(Map<String, Object> values) { super(values); }
        @Nullable public static SetCacheDisabledParams fromMap(@Nullable Map<String, Object> values) {
            return values == null ? null : new SetCacheDisabledParams(values);
        }
        public static Builder builder() { return new Builder(); }
        /**
         * Cache disabled state.
         * @return the protocol field value
         */
        @Nullable public Boolean cacheDisabled() {
            return (Boolean) value("cacheDisabled");
        }
        public static final class Builder {
            private final Map<String, Object> values = new LinkedHashMap<>();
            /**
             * Cache disabled state.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder cacheDisabled(@Nullable Boolean value) {
                if (value == null) values.remove("cacheDisabled");
                else values.put("cacheDisabled", jsonValue(value));
                return this;
            }
            public SetCacheDisabledParams build() {
                if (!values.containsKey("cacheDisabled")) throw new IllegalStateException("Missing required CDP field: cacheDisabled");
                return new SetCacheDisabledParams(values);
            }
        }
    }
    /**
     * Toggles ignoring cache for each request. If {@code true}, cache will not be used.
     */
    public static final class SetCacheDisabledResult extends CdpObject {
        private SetCacheDisabledResult(Map<String, Object> values) { super(values); }
        @Nullable public static SetCacheDisabledResult fromMap(@Nullable Map<String, Object> values) {
            return values == null ? null : new SetCacheDisabledResult(values);
        }
        public static Builder builder() { return new Builder(); }
        public static final class Builder {
            private final Map<String, Object> values = new LinkedHashMap<>();
            public SetCacheDisabledResult build() {
                return new SetCacheDisabledResult(values);
            }
        }
    }
    /**
     * Sets a cookie with the given cookie data; may overwrite equivalent cookies if they exist.
     */
    public static final class SetCookieParams extends CdpObject {
        private SetCookieParams(Map<String, Object> values) { super(values); }
        @Nullable public static SetCookieParams fromMap(@Nullable Map<String, Object> values) {
            return values == null ? null : new SetCookieParams(values);
        }
        public static Builder builder() { return new Builder(); }
        /**
         * Cookie name.
         * @return the protocol field value
         */
        @Nullable public String name() {
            return (String) value("name");
        }
        /**
         * Cookie value.
         * @return the protocol field value
         */
        @Nullable public String value() {
            return (String) value("value");
        }
        /**
         * The request-URI to associate with the setting of the cookie. This value can affect the default domain, path, source port, and source scheme values of the created cookie.
         * @return the protocol field value
         */
        @Nullable public String url() {
            return (String) value("url");
        }
        /**
         * Cookie domain.
         * @return the protocol field value
         */
        @Nullable public String domain() {
            return (String) value("domain");
        }
        /**
         * Cookie path.
         * @return the protocol field value
         */
        @Nullable public String path() {
            return (String) value("path");
        }
        /**
         * True if cookie is secure.
         * @return the protocol field value
         */
        @Nullable public Boolean secure() {
            return (Boolean) value("secure");
        }
        /**
         * True if cookie is http-only.
         * @return the protocol field value
         */
        @Nullable public Boolean httpOnly() {
            return (Boolean) value("httpOnly");
        }
        /**
         * Cookie SameSite type.
         * @return the protocol field value
         */
        @Nullable public String sameSite() {
            return (String) value("sameSite");
        }
        /**
         * Cookie expiration date, session cookie if not set
         * @return the protocol field value
         */
        @Nullable public Double expires() {
            return numberAsDouble(value("expires"));
        }
        /**
         * Cookie Priority type.
         * <p><b>Experimental:</b> this part of CDP may change without notice.
         * @return the protocol field value
         */
        @Nullable public String priority() {
            return (String) value("priority");
        }
        /**
         * Cookie source scheme type.
         * <p><b>Experimental:</b> this part of CDP may change without notice.
         * @return the protocol field value
         */
        @Nullable public String sourceScheme() {
            return (String) value("sourceScheme");
        }
        /**
         * Cookie source port. Valid values are {-1, [1, 65535]}, -1 indicates an unspecified port. An unspecified port value allows protocol clients to emulate legacy cookie scope for the port. This is a temporary ability and it will be removed in the future.
         * <p><b>Experimental:</b> this part of CDP may change without notice.
         * @return the protocol field value
         */
        @Nullable public Long sourcePort() {
            return numberAsLong(value("sourcePort"));
        }
        /**
         * Cookie partition key. If not set, the cookie will be set as not partitioned.
         * <p><b>Experimental:</b> this part of CDP may change without notice.
         * @return the protocol field value
         */
        @Nullable public Network.CookiePartitionKey partitionKey() {
            return Network.CookiePartitionKey.fromMap(objectMap(value("partitionKey")));
        }
        public static final class Builder {
            private final Map<String, Object> values = new LinkedHashMap<>();
            /**
             * Cookie name.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder name(@Nullable String value) {
                if (value == null) values.remove("name");
                else values.put("name", jsonValue(value));
                return this;
            }
            /**
             * Cookie value.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder value(@Nullable String value) {
                if (value == null) values.remove("value");
                else values.put("value", jsonValue(value));
                return this;
            }
            /**
             * The request-URI to associate with the setting of the cookie. This value can affect the default domain, path, source port, and source scheme values of the created cookie.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder url(@Nullable String value) {
                if (value == null) values.remove("url");
                else values.put("url", jsonValue(value));
                return this;
            }
            /**
             * Cookie domain.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder domain(@Nullable String value) {
                if (value == null) values.remove("domain");
                else values.put("domain", jsonValue(value));
                return this;
            }
            /**
             * Cookie path.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder path(@Nullable String value) {
                if (value == null) values.remove("path");
                else values.put("path", jsonValue(value));
                return this;
            }
            /**
             * True if cookie is secure.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder secure(@Nullable Boolean value) {
                if (value == null) values.remove("secure");
                else values.put("secure", jsonValue(value));
                return this;
            }
            /**
             * True if cookie is http-only.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder httpOnly(@Nullable Boolean value) {
                if (value == null) values.remove("httpOnly");
                else values.put("httpOnly", jsonValue(value));
                return this;
            }
            /**
             * Cookie SameSite type.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder sameSite(@Nullable String value) {
                if (value == null) values.remove("sameSite");
                else values.put("sameSite", jsonValue(value));
                return this;
            }
            /**
             * Cookie expiration date, session cookie if not set
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder expires(@Nullable Double value) {
                if (value == null) values.remove("expires");
                else values.put("expires", jsonValue(value));
                return this;
            }
            /**
             * Cookie Priority type.
             * <p><b>Experimental:</b> this part of CDP may change without notice.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder priority(@Nullable String value) {
                if (value == null) values.remove("priority");
                else values.put("priority", jsonValue(value));
                return this;
            }
            /**
             * Cookie source scheme type.
             * <p><b>Experimental:</b> this part of CDP may change without notice.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder sourceScheme(@Nullable String value) {
                if (value == null) values.remove("sourceScheme");
                else values.put("sourceScheme", jsonValue(value));
                return this;
            }
            /**
             * Cookie source port. Valid values are {-1, [1, 65535]}, -1 indicates an unspecified port. An unspecified port value allows protocol clients to emulate legacy cookie scope for the port. This is a temporary ability and it will be removed in the future.
             * <p><b>Experimental:</b> this part of CDP may change without notice.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder sourcePort(@Nullable Long value) {
                if (value == null) values.remove("sourcePort");
                else values.put("sourcePort", jsonValue(value));
                return this;
            }
            /**
             * Cookie partition key. If not set, the cookie will be set as not partitioned.
             * <p><b>Experimental:</b> this part of CDP may change without notice.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder partitionKey(@Nullable Network.CookiePartitionKey value) {
                if (value == null) values.remove("partitionKey");
                else values.put("partitionKey", jsonValue(value));
                return this;
            }
            public SetCookieParams build() {
                if (!values.containsKey("name")) throw new IllegalStateException("Missing required CDP field: name");
                if (!values.containsKey("value")) throw new IllegalStateException("Missing required CDP field: value");
                return new SetCookieParams(values);
            }
        }
    }
    /**
     * Sets a cookie with the given cookie data; may overwrite equivalent cookies if they exist.
     */
    public static final class SetCookieResult extends CdpObject {
        private SetCookieResult(Map<String, Object> values) { super(values); }
        @Nullable public static SetCookieResult fromMap(@Nullable Map<String, Object> values) {
            return values == null ? null : new SetCookieResult(values);
        }
        public static Builder builder() { return new Builder(); }
        /**
         * Always set to true. If an error occurs, the response indicates protocol error.
         * @return the protocol field value
         * @deprecated Deprecated by the Chromium DevTools Protocol.
         */
        @Deprecated
        @Nullable public Boolean success() {
            return (Boolean) value("success");
        }
        public static final class Builder {
            private final Map<String, Object> values = new LinkedHashMap<>();
            /**
             * Always set to true. If an error occurs, the response indicates protocol error.
             * @param value field value; null removes an optional value
             * @return this builder
             * @deprecated Deprecated by the Chromium DevTools Protocol.
             */
            @Deprecated
            public Builder success(@Nullable Boolean value) {
                if (value == null) values.remove("success");
                else values.put("success", jsonValue(value));
                return this;
            }
            public SetCookieResult build() {
                if (!values.containsKey("success")) throw new IllegalStateException("Missing required CDP field: success");
                return new SetCookieResult(values);
            }
        }
    }
    /**
     * Sets given cookies.
     */
    public static final class SetCookiesParams extends CdpObject {
        private SetCookiesParams(Map<String, Object> values) { super(values); }
        @Nullable public static SetCookiesParams fromMap(@Nullable Map<String, Object> values) {
            return values == null ? null : new SetCookiesParams(values);
        }
        public static Builder builder() { return new Builder(); }
        /**
         * Cookies to be set.
         * @return the protocol field value
         */
        @Nullable public java.util.List<Network.CookieParam> cookies() {
            return list(value("cookies"), element0 -> Network.CookieParam.fromMap(objectMap(element0)));
        }
        public static final class Builder {
            private final Map<String, Object> values = new LinkedHashMap<>();
            /**
             * Cookies to be set.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder cookies(@Nullable java.util.List<Network.CookieParam> value) {
                if (value == null) values.remove("cookies");
                else values.put("cookies", jsonValue(value));
                return this;
            }
            public SetCookiesParams build() {
                if (!values.containsKey("cookies")) throw new IllegalStateException("Missing required CDP field: cookies");
                return new SetCookiesParams(values);
            }
        }
    }
    /**
     * Sets given cookies.
     */
    public static final class SetCookiesResult extends CdpObject {
        private SetCookiesResult(Map<String, Object> values) { super(values); }
        @Nullable public static SetCookiesResult fromMap(@Nullable Map<String, Object> values) {
            return values == null ? null : new SetCookiesResult(values);
        }
        public static Builder builder() { return new Builder(); }
        public static final class Builder {
            private final Map<String, Object> values = new LinkedHashMap<>();
            public SetCookiesResult build() {
                return new SetCookiesResult(values);
            }
        }
    }
    /**
     * Specifies whether to always send extra HTTP headers with the requests from this page.
     */
    public static final class SetExtraHTTPHeadersParams extends CdpObject {
        private SetExtraHTTPHeadersParams(Map<String, Object> values) { super(values); }
        @Nullable public static SetExtraHTTPHeadersParams fromMap(@Nullable Map<String, Object> values) {
            return values == null ? null : new SetExtraHTTPHeadersParams(values);
        }
        public static Builder builder() { return new Builder(); }
        /**
         * Map with extra HTTP headers.
         * @return the protocol field value
         */
        @Nullable public java.util.Map<String, Object> headers() {
            return objectMap(value("headers"));
        }
        public static final class Builder {
            private final Map<String, Object> values = new LinkedHashMap<>();
            /**
             * Map with extra HTTP headers.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder headers(@Nullable java.util.Map<String, Object> value) {
                if (value == null) values.remove("headers");
                else values.put("headers", jsonValue(value));
                return this;
            }
            public SetExtraHTTPHeadersParams build() {
                if (!values.containsKey("headers")) throw new IllegalStateException("Missing required CDP field: headers");
                return new SetExtraHTTPHeadersParams(values);
            }
        }
    }
    /**
     * Specifies whether to always send extra HTTP headers with the requests from this page.
     */
    public static final class SetExtraHTTPHeadersResult extends CdpObject {
        private SetExtraHTTPHeadersResult(Map<String, Object> values) { super(values); }
        @Nullable public static SetExtraHTTPHeadersResult fromMap(@Nullable Map<String, Object> values) {
            return values == null ? null : new SetExtraHTTPHeadersResult(values);
        }
        public static Builder builder() { return new Builder(); }
        public static final class Builder {
            private final Map<String, Object> values = new LinkedHashMap<>();
            public SetExtraHTTPHeadersResult build() {
                return new SetExtraHTTPHeadersResult(values);
            }
        }
    }
    /**
     * Specifies whether to attach a page script stack id in requests
     * <p><b>Experimental:</b> this part of CDP may change without notice.
     */
    public static final class SetAttachDebugStackParams extends CdpObject {
        private SetAttachDebugStackParams(Map<String, Object> values) { super(values); }
        @Nullable public static SetAttachDebugStackParams fromMap(@Nullable Map<String, Object> values) {
            return values == null ? null : new SetAttachDebugStackParams(values);
        }
        public static Builder builder() { return new Builder(); }
        /**
         * Whether to attach a page script stack for debugging purpose.
         * @return the protocol field value
         */
        @Nullable public Boolean enabled() {
            return (Boolean) value("enabled");
        }
        public static final class Builder {
            private final Map<String, Object> values = new LinkedHashMap<>();
            /**
             * Whether to attach a page script stack for debugging purpose.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder enabled(@Nullable Boolean value) {
                if (value == null) values.remove("enabled");
                else values.put("enabled", jsonValue(value));
                return this;
            }
            public SetAttachDebugStackParams build() {
                if (!values.containsKey("enabled")) throw new IllegalStateException("Missing required CDP field: enabled");
                return new SetAttachDebugStackParams(values);
            }
        }
    }
    /**
     * Specifies whether to attach a page script stack id in requests
     * <p><b>Experimental:</b> this part of CDP may change without notice.
     */
    public static final class SetAttachDebugStackResult extends CdpObject {
        private SetAttachDebugStackResult(Map<String, Object> values) { super(values); }
        @Nullable public static SetAttachDebugStackResult fromMap(@Nullable Map<String, Object> values) {
            return values == null ? null : new SetAttachDebugStackResult(values);
        }
        public static Builder builder() { return new Builder(); }
        public static final class Builder {
            private final Map<String, Object> values = new LinkedHashMap<>();
            public SetAttachDebugStackResult build() {
                return new SetAttachDebugStackResult(values);
            }
        }
    }
    /**
     * Sets the requests to intercept that match the provided patterns and optionally resource types. Deprecated, please use Fetch.enable instead.
     * <p><b>Experimental:</b> this part of CDP may change without notice.
     * @deprecated Deprecated by the Chromium DevTools Protocol.
     */
    @Deprecated
    public static final class SetRequestInterceptionParams extends CdpObject {
        private SetRequestInterceptionParams(Map<String, Object> values) { super(values); }
        @Nullable public static SetRequestInterceptionParams fromMap(@Nullable Map<String, Object> values) {
            return values == null ? null : new SetRequestInterceptionParams(values);
        }
        public static Builder builder() { return new Builder(); }
        /**
         * Requests matching any of these patterns will be forwarded and wait for the corresponding continueInterceptedRequest call.
         * @return the protocol field value
         */
        @Nullable public java.util.List<Network.RequestPattern> patterns() {
            return list(value("patterns"), element0 -> Network.RequestPattern.fromMap(objectMap(element0)));
        }
        public static final class Builder {
            private final Map<String, Object> values = new LinkedHashMap<>();
            /**
             * Requests matching any of these patterns will be forwarded and wait for the corresponding continueInterceptedRequest call.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder patterns(@Nullable java.util.List<Network.RequestPattern> value) {
                if (value == null) values.remove("patterns");
                else values.put("patterns", jsonValue(value));
                return this;
            }
            public SetRequestInterceptionParams build() {
                if (!values.containsKey("patterns")) throw new IllegalStateException("Missing required CDP field: patterns");
                return new SetRequestInterceptionParams(values);
            }
        }
    }
    /**
     * Sets the requests to intercept that match the provided patterns and optionally resource types. Deprecated, please use Fetch.enable instead.
     * <p><b>Experimental:</b> this part of CDP may change without notice.
     * @deprecated Deprecated by the Chromium DevTools Protocol.
     */
    @Deprecated
    public static final class SetRequestInterceptionResult extends CdpObject {
        private SetRequestInterceptionResult(Map<String, Object> values) { super(values); }
        @Nullable public static SetRequestInterceptionResult fromMap(@Nullable Map<String, Object> values) {
            return values == null ? null : new SetRequestInterceptionResult(values);
        }
        public static Builder builder() { return new Builder(); }
        public static final class Builder {
            private final Map<String, Object> values = new LinkedHashMap<>();
            public SetRequestInterceptionResult build() {
                return new SetRequestInterceptionResult(values);
            }
        }
    }
    /**
     * Allows overriding user agent with the given string.
     */
    public static final class SetUserAgentOverrideParams extends CdpObject {
        private SetUserAgentOverrideParams(Map<String, Object> values) { super(values); }
        @Nullable public static SetUserAgentOverrideParams fromMap(@Nullable Map<String, Object> values) {
            return values == null ? null : new SetUserAgentOverrideParams(values);
        }
        public static Builder builder() { return new Builder(); }
        /**
         * User agent to use.
         * @return the protocol field value
         */
        @Nullable public String userAgent() {
            return (String) value("userAgent");
        }
        /**
         * Browser language to emulate.
         * @return the protocol field value
         */
        @Nullable public String acceptLanguage() {
            return (String) value("acceptLanguage");
        }
        /**
         * The platform navigator.platform should return.
         * @return the protocol field value
         */
        @Nullable public String platform() {
            return (String) value("platform");
        }
        /**
         * To be sent in Sec-CH-UA-* headers and returned in navigator.userAgentData
         * <p><b>Experimental:</b> this part of CDP may change without notice.
         * @return the protocol field value
         */
        @Nullable public Emulation.UserAgentMetadata userAgentMetadata() {
            return Emulation.UserAgentMetadata.fromMap(objectMap(value("userAgentMetadata")));
        }
        public static final class Builder {
            private final Map<String, Object> values = new LinkedHashMap<>();
            /**
             * User agent to use.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder userAgent(@Nullable String value) {
                if (value == null) values.remove("userAgent");
                else values.put("userAgent", jsonValue(value));
                return this;
            }
            /**
             * Browser language to emulate.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder acceptLanguage(@Nullable String value) {
                if (value == null) values.remove("acceptLanguage");
                else values.put("acceptLanguage", jsonValue(value));
                return this;
            }
            /**
             * The platform navigator.platform should return.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder platform(@Nullable String value) {
                if (value == null) values.remove("platform");
                else values.put("platform", jsonValue(value));
                return this;
            }
            /**
             * To be sent in Sec-CH-UA-* headers and returned in navigator.userAgentData
             * <p><b>Experimental:</b> this part of CDP may change without notice.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder userAgentMetadata(@Nullable Emulation.UserAgentMetadata value) {
                if (value == null) values.remove("userAgentMetadata");
                else values.put("userAgentMetadata", jsonValue(value));
                return this;
            }
            public SetUserAgentOverrideParams build() {
                if (!values.containsKey("userAgent")) throw new IllegalStateException("Missing required CDP field: userAgent");
                return new SetUserAgentOverrideParams(values);
            }
        }
    }
    /**
     * Allows overriding user agent with the given string.
     */
    public static final class SetUserAgentOverrideResult extends CdpObject {
        private SetUserAgentOverrideResult(Map<String, Object> values) { super(values); }
        @Nullable public static SetUserAgentOverrideResult fromMap(@Nullable Map<String, Object> values) {
            return values == null ? null : new SetUserAgentOverrideResult(values);
        }
        public static Builder builder() { return new Builder(); }
        public static final class Builder {
            private final Map<String, Object> values = new LinkedHashMap<>();
            public SetUserAgentOverrideResult build() {
                return new SetUserAgentOverrideResult(values);
            }
        }
    }
    /**
     * Enables streaming of the response for the given requestId. If enabled, the dataReceived event contains the data that was received during streaming.
     * <p><b>Experimental:</b> this part of CDP may change without notice.
     */
    public static final class StreamResourceContentParams extends CdpObject {
        private StreamResourceContentParams(Map<String, Object> values) { super(values); }
        @Nullable public static StreamResourceContentParams fromMap(@Nullable Map<String, Object> values) {
            return values == null ? null : new StreamResourceContentParams(values);
        }
        public static Builder builder() { return new Builder(); }
        /**
         * Identifier of the request to stream.
         * @return the protocol field value
         */
        @Nullable public String requestId() {
            return (String) value("requestId");
        }
        public static final class Builder {
            private final Map<String, Object> values = new LinkedHashMap<>();
            /**
             * Identifier of the request to stream.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder requestId(@Nullable String value) {
                if (value == null) values.remove("requestId");
                else values.put("requestId", jsonValue(value));
                return this;
            }
            public StreamResourceContentParams build() {
                if (!values.containsKey("requestId")) throw new IllegalStateException("Missing required CDP field: requestId");
                return new StreamResourceContentParams(values);
            }
        }
    }
    /**
     * Enables streaming of the response for the given requestId. If enabled, the dataReceived event contains the data that was received during streaming.
     * <p><b>Experimental:</b> this part of CDP may change without notice.
     */
    public static final class StreamResourceContentResult extends CdpObject {
        private StreamResourceContentResult(Map<String, Object> values) { super(values); }
        @Nullable public static StreamResourceContentResult fromMap(@Nullable Map<String, Object> values) {
            return values == null ? null : new StreamResourceContentResult(values);
        }
        public static Builder builder() { return new Builder(); }
        /**
         * Data that has been buffered until streaming is enabled. (Encoded as a base64 string when passed over JSON)
         * @return the protocol field value
         */
        @Nullable public String bufferedData() {
            return (String) value("bufferedData");
        }
        public static final class Builder {
            private final Map<String, Object> values = new LinkedHashMap<>();
            /**
             * Data that has been buffered until streaming is enabled. (Encoded as a base64 string when passed over JSON)
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder bufferedData(@Nullable String value) {
                if (value == null) values.remove("bufferedData");
                else values.put("bufferedData", jsonValue(value));
                return this;
            }
            public StreamResourceContentResult build() {
                if (!values.containsKey("bufferedData")) throw new IllegalStateException("Missing required CDP field: bufferedData");
                return new StreamResourceContentResult(values);
            }
        }
    }
    /**
     * Returns information about the COEP/COOP isolation status.
     * <p><b>Experimental:</b> this part of CDP may change without notice.
     */
    public static final class GetSecurityIsolationStatusParams extends CdpObject {
        private GetSecurityIsolationStatusParams(Map<String, Object> values) { super(values); }
        @Nullable public static GetSecurityIsolationStatusParams fromMap(@Nullable Map<String, Object> values) {
            return values == null ? null : new GetSecurityIsolationStatusParams(values);
        }
        public static Builder builder() { return new Builder(); }
        /**
         * If no frameId is provided, the status of the target is provided.
         * @return the protocol field value
         */
        @Nullable public String frameId() {
            return (String) value("frameId");
        }
        public static final class Builder {
            private final Map<String, Object> values = new LinkedHashMap<>();
            /**
             * If no frameId is provided, the status of the target is provided.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder frameId(@Nullable String value) {
                if (value == null) values.remove("frameId");
                else values.put("frameId", jsonValue(value));
                return this;
            }
            public GetSecurityIsolationStatusParams build() {
                return new GetSecurityIsolationStatusParams(values);
            }
        }
    }
    /**
     * Returns information about the COEP/COOP isolation status.
     * <p><b>Experimental:</b> this part of CDP may change without notice.
     */
    public static final class GetSecurityIsolationStatusResult extends CdpObject {
        private GetSecurityIsolationStatusResult(Map<String, Object> values) { super(values); }
        @Nullable public static GetSecurityIsolationStatusResult fromMap(@Nullable Map<String, Object> values) {
            return values == null ? null : new GetSecurityIsolationStatusResult(values);
        }
        public static Builder builder() { return new Builder(); }
        /**
         * Returns the status field.
         * @return the protocol field value
         */
        @Nullable public Network.SecurityIsolationStatus status() {
            return Network.SecurityIsolationStatus.fromMap(objectMap(value("status")));
        }
        public static final class Builder {
            private final Map<String, Object> values = new LinkedHashMap<>();
            /**
             * Sets the status field.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder status(@Nullable Network.SecurityIsolationStatus value) {
                if (value == null) values.remove("status");
                else values.put("status", jsonValue(value));
                return this;
            }
            public GetSecurityIsolationStatusResult build() {
                if (!values.containsKey("status")) throw new IllegalStateException("Missing required CDP field: status");
                return new GetSecurityIsolationStatusResult(values);
            }
        }
    }
    /**
     * Enables tracking for the Reporting API, events generated by the Reporting API will now be delivered to the client. Enabling triggers &#x27;reportingApiReportAdded&#x27; for all existing reports.
     * <p><b>Experimental:</b> this part of CDP may change without notice.
     */
    public static final class EnableReportingApiParams extends CdpObject {
        private EnableReportingApiParams(Map<String, Object> values) { super(values); }
        @Nullable public static EnableReportingApiParams fromMap(@Nullable Map<String, Object> values) {
            return values == null ? null : new EnableReportingApiParams(values);
        }
        public static Builder builder() { return new Builder(); }
        /**
         * Whether to enable or disable events for the Reporting API
         * @return the protocol field value
         */
        @Nullable public Boolean enable() {
            return (Boolean) value("enable");
        }
        public static final class Builder {
            private final Map<String, Object> values = new LinkedHashMap<>();
            /**
             * Whether to enable or disable events for the Reporting API
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder enable(@Nullable Boolean value) {
                if (value == null) values.remove("enable");
                else values.put("enable", jsonValue(value));
                return this;
            }
            public EnableReportingApiParams build() {
                if (!values.containsKey("enable")) throw new IllegalStateException("Missing required CDP field: enable");
                return new EnableReportingApiParams(values);
            }
        }
    }
    /**
     * Enables tracking for the Reporting API, events generated by the Reporting API will now be delivered to the client. Enabling triggers &#x27;reportingApiReportAdded&#x27; for all existing reports.
     * <p><b>Experimental:</b> this part of CDP may change without notice.
     */
    public static final class EnableReportingApiResult extends CdpObject {
        private EnableReportingApiResult(Map<String, Object> values) { super(values); }
        @Nullable public static EnableReportingApiResult fromMap(@Nullable Map<String, Object> values) {
            return values == null ? null : new EnableReportingApiResult(values);
        }
        public static Builder builder() { return new Builder(); }
        public static final class Builder {
            private final Map<String, Object> values = new LinkedHashMap<>();
            public EnableReportingApiResult build() {
                return new EnableReportingApiResult(values);
            }
        }
    }
    /**
     * Sets up tracking device bound sessions and fetching of initial set of sessions.
     * <p><b>Experimental:</b> this part of CDP may change without notice.
     */
    public static final class EnableDeviceBoundSessionsParams extends CdpObject {
        private EnableDeviceBoundSessionsParams(Map<String, Object> values) { super(values); }
        @Nullable public static EnableDeviceBoundSessionsParams fromMap(@Nullable Map<String, Object> values) {
            return values == null ? null : new EnableDeviceBoundSessionsParams(values);
        }
        public static Builder builder() { return new Builder(); }
        /**
         * Whether to enable or disable events.
         * @return the protocol field value
         */
        @Nullable public Boolean enable() {
            return (Boolean) value("enable");
        }
        public static final class Builder {
            private final Map<String, Object> values = new LinkedHashMap<>();
            /**
             * Whether to enable or disable events.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder enable(@Nullable Boolean value) {
                if (value == null) values.remove("enable");
                else values.put("enable", jsonValue(value));
                return this;
            }
            public EnableDeviceBoundSessionsParams build() {
                if (!values.containsKey("enable")) throw new IllegalStateException("Missing required CDP field: enable");
                return new EnableDeviceBoundSessionsParams(values);
            }
        }
    }
    /**
     * Sets up tracking device bound sessions and fetching of initial set of sessions.
     * <p><b>Experimental:</b> this part of CDP may change without notice.
     */
    public static final class EnableDeviceBoundSessionsResult extends CdpObject {
        private EnableDeviceBoundSessionsResult(Map<String, Object> values) { super(values); }
        @Nullable public static EnableDeviceBoundSessionsResult fromMap(@Nullable Map<String, Object> values) {
            return values == null ? null : new EnableDeviceBoundSessionsResult(values);
        }
        public static Builder builder() { return new Builder(); }
        public static final class Builder {
            private final Map<String, Object> values = new LinkedHashMap<>();
            public EnableDeviceBoundSessionsResult build() {
                return new EnableDeviceBoundSessionsResult(values);
            }
        }
    }
    /**
     * Deletes a device bound session.
     * <p><b>Experimental:</b> this part of CDP may change without notice.
     */
    public static final class DeleteDeviceBoundSessionParams extends CdpObject {
        private DeleteDeviceBoundSessionParams(Map<String, Object> values) { super(values); }
        @Nullable public static DeleteDeviceBoundSessionParams fromMap(@Nullable Map<String, Object> values) {
            return values == null ? null : new DeleteDeviceBoundSessionParams(values);
        }
        public static Builder builder() { return new Builder(); }
        /**
         * Returns the key field.
         * @return the protocol field value
         */
        @Nullable public Network.DeviceBoundSessionKey key() {
            return Network.DeviceBoundSessionKey.fromMap(objectMap(value("key")));
        }
        public static final class Builder {
            private final Map<String, Object> values = new LinkedHashMap<>();
            /**
             * Sets the key field.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder key(@Nullable Network.DeviceBoundSessionKey value) {
                if (value == null) values.remove("key");
                else values.put("key", jsonValue(value));
                return this;
            }
            public DeleteDeviceBoundSessionParams build() {
                if (!values.containsKey("key")) throw new IllegalStateException("Missing required CDP field: key");
                return new DeleteDeviceBoundSessionParams(values);
            }
        }
    }
    /**
     * Deletes a device bound session.
     * <p><b>Experimental:</b> this part of CDP may change without notice.
     */
    public static final class DeleteDeviceBoundSessionResult extends CdpObject {
        private DeleteDeviceBoundSessionResult(Map<String, Object> values) { super(values); }
        @Nullable public static DeleteDeviceBoundSessionResult fromMap(@Nullable Map<String, Object> values) {
            return values == null ? null : new DeleteDeviceBoundSessionResult(values);
        }
        public static Builder builder() { return new Builder(); }
        public static final class Builder {
            private final Map<String, Object> values = new LinkedHashMap<>();
            public DeleteDeviceBoundSessionResult build() {
                return new DeleteDeviceBoundSessionResult(values);
            }
        }
    }
    /**
     * Fetches the schemeful site for a specific origin.
     * <p><b>Experimental:</b> this part of CDP may change without notice.
     */
    public static final class FetchSchemefulSiteParams extends CdpObject {
        private FetchSchemefulSiteParams(Map<String, Object> values) { super(values); }
        @Nullable public static FetchSchemefulSiteParams fromMap(@Nullable Map<String, Object> values) {
            return values == null ? null : new FetchSchemefulSiteParams(values);
        }
        public static Builder builder() { return new Builder(); }
        /**
         * The URL origin.
         * @return the protocol field value
         */
        @Nullable public String origin() {
            return (String) value("origin");
        }
        public static final class Builder {
            private final Map<String, Object> values = new LinkedHashMap<>();
            /**
             * The URL origin.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder origin(@Nullable String value) {
                if (value == null) values.remove("origin");
                else values.put("origin", jsonValue(value));
                return this;
            }
            public FetchSchemefulSiteParams build() {
                if (!values.containsKey("origin")) throw new IllegalStateException("Missing required CDP field: origin");
                return new FetchSchemefulSiteParams(values);
            }
        }
    }
    /**
     * Fetches the schemeful site for a specific origin.
     * <p><b>Experimental:</b> this part of CDP may change without notice.
     */
    public static final class FetchSchemefulSiteResult extends CdpObject {
        private FetchSchemefulSiteResult(Map<String, Object> values) { super(values); }
        @Nullable public static FetchSchemefulSiteResult fromMap(@Nullable Map<String, Object> values) {
            return values == null ? null : new FetchSchemefulSiteResult(values);
        }
        public static Builder builder() { return new Builder(); }
        /**
         * The corresponding schemeful site.
         * @return the protocol field value
         */
        @Nullable public String schemefulSite() {
            return (String) value("schemefulSite");
        }
        public static final class Builder {
            private final Map<String, Object> values = new LinkedHashMap<>();
            /**
             * The corresponding schemeful site.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder schemefulSite(@Nullable String value) {
                if (value == null) values.remove("schemefulSite");
                else values.put("schemefulSite", jsonValue(value));
                return this;
            }
            public FetchSchemefulSiteResult build() {
                if (!values.containsKey("schemefulSite")) throw new IllegalStateException("Missing required CDP field: schemefulSite");
                return new FetchSchemefulSiteResult(values);
            }
        }
    }
    /**
     * Fetches the resource and returns the content.
     * <p><b>Experimental:</b> this part of CDP may change without notice.
     */
    public static final class LoadNetworkResourceParams extends CdpObject {
        private LoadNetworkResourceParams(Map<String, Object> values) { super(values); }
        @Nullable public static LoadNetworkResourceParams fromMap(@Nullable Map<String, Object> values) {
            return values == null ? null : new LoadNetworkResourceParams(values);
        }
        public static Builder builder() { return new Builder(); }
        /**
         * Frame id to get the resource for. Mandatory for frame targets, and should be omitted for worker targets.
         * @return the protocol field value
         */
        @Nullable public String frameId() {
            return (String) value("frameId");
        }
        /**
         * URL of the resource to get content for.
         * @return the protocol field value
         */
        @Nullable public String url() {
            return (String) value("url");
        }
        /**
         * Options for the request.
         * @return the protocol field value
         */
        @Nullable public Network.LoadNetworkResourceOptions options() {
            return Network.LoadNetworkResourceOptions.fromMap(objectMap(value("options")));
        }
        public static final class Builder {
            private final Map<String, Object> values = new LinkedHashMap<>();
            /**
             * Frame id to get the resource for. Mandatory for frame targets, and should be omitted for worker targets.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder frameId(@Nullable String value) {
                if (value == null) values.remove("frameId");
                else values.put("frameId", jsonValue(value));
                return this;
            }
            /**
             * URL of the resource to get content for.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder url(@Nullable String value) {
                if (value == null) values.remove("url");
                else values.put("url", jsonValue(value));
                return this;
            }
            /**
             * Options for the request.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder options(@Nullable Network.LoadNetworkResourceOptions value) {
                if (value == null) values.remove("options");
                else values.put("options", jsonValue(value));
                return this;
            }
            public LoadNetworkResourceParams build() {
                if (!values.containsKey("url")) throw new IllegalStateException("Missing required CDP field: url");
                if (!values.containsKey("options")) throw new IllegalStateException("Missing required CDP field: options");
                return new LoadNetworkResourceParams(values);
            }
        }
    }
    /**
     * Fetches the resource and returns the content.
     * <p><b>Experimental:</b> this part of CDP may change without notice.
     */
    public static final class LoadNetworkResourceResult extends CdpObject {
        private LoadNetworkResourceResult(Map<String, Object> values) { super(values); }
        @Nullable public static LoadNetworkResourceResult fromMap(@Nullable Map<String, Object> values) {
            return values == null ? null : new LoadNetworkResourceResult(values);
        }
        public static Builder builder() { return new Builder(); }
        /**
         * Returns the resource field.
         * @return the protocol field value
         */
        @Nullable public Network.LoadNetworkResourcePageResult resource() {
            return Network.LoadNetworkResourcePageResult.fromMap(objectMap(value("resource")));
        }
        public static final class Builder {
            private final Map<String, Object> values = new LinkedHashMap<>();
            /**
             * Sets the resource field.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder resource(@Nullable Network.LoadNetworkResourcePageResult value) {
                if (value == null) values.remove("resource");
                else values.put("resource", jsonValue(value));
                return this;
            }
            public LoadNetworkResourceResult build() {
                if (!values.containsKey("resource")) throw new IllegalStateException("Missing required CDP field: resource");
                return new LoadNetworkResourceResult(values);
            }
        }
    }
    /**
     * Sets Controls for third-party cookie access Page reload is required before the new cookie behavior will be observed
     * <p><b>Experimental:</b> this part of CDP may change without notice.
     */
    public static final class SetCookieControlsParams extends CdpObject {
        private SetCookieControlsParams(Map<String, Object> values) { super(values); }
        @Nullable public static SetCookieControlsParams fromMap(@Nullable Map<String, Object> values) {
            return values == null ? null : new SetCookieControlsParams(values);
        }
        public static Builder builder() { return new Builder(); }
        /**
         * Whether 3pc restriction is enabled.
         * @return the protocol field value
         */
        @Nullable public Boolean enableThirdPartyCookieRestriction() {
            return (Boolean) value("enableThirdPartyCookieRestriction");
        }
        public static final class Builder {
            private final Map<String, Object> values = new LinkedHashMap<>();
            /**
             * Whether 3pc restriction is enabled.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder enableThirdPartyCookieRestriction(@Nullable Boolean value) {
                if (value == null) values.remove("enableThirdPartyCookieRestriction");
                else values.put("enableThirdPartyCookieRestriction", jsonValue(value));
                return this;
            }
            public SetCookieControlsParams build() {
                if (!values.containsKey("enableThirdPartyCookieRestriction")) throw new IllegalStateException("Missing required CDP field: enableThirdPartyCookieRestriction");
                return new SetCookieControlsParams(values);
            }
        }
    }
    /**
     * Sets Controls for third-party cookie access Page reload is required before the new cookie behavior will be observed
     * <p><b>Experimental:</b> this part of CDP may change without notice.
     */
    public static final class SetCookieControlsResult extends CdpObject {
        private SetCookieControlsResult(Map<String, Object> values) { super(values); }
        @Nullable public static SetCookieControlsResult fromMap(@Nullable Map<String, Object> values) {
            return values == null ? null : new SetCookieControlsResult(values);
        }
        public static Builder builder() { return new Builder(); }
        public static final class Builder {
            private final Map<String, Object> values = new LinkedHashMap<>();
            public SetCookieControlsResult build() {
                return new SetCookieControlsResult(values);
            }
        }
    }
    /**
     * Fired when data chunk was received over the network.
     */
    public static final class DataReceivedEvent extends CdpObject {
        private DataReceivedEvent(Map<String, Object> values) { super(values); }
        @Nullable public static DataReceivedEvent fromMap(@Nullable Map<String, Object> values) {
            return values == null ? null : new DataReceivedEvent(values);
        }
        public static Builder builder() { return new Builder(); }
        /**
         * Request identifier.
         * @return the protocol field value
         */
        @Nullable public String requestId() {
            return (String) value("requestId");
        }
        /**
         * Timestamp.
         * @return the protocol field value
         */
        @Nullable public Double timestamp() {
            return numberAsDouble(value("timestamp"));
        }
        /**
         * Data chunk length.
         * @return the protocol field value
         */
        @Nullable public Long dataLength() {
            return numberAsLong(value("dataLength"));
        }
        /**
         * Actual bytes received (might be less than dataLength for compressed encodings).
         * @return the protocol field value
         */
        @Nullable public Long encodedDataLength() {
            return numberAsLong(value("encodedDataLength"));
        }
        /**
         * Data that was received. (Encoded as a base64 string when passed over JSON)
         * <p><b>Experimental:</b> this part of CDP may change without notice.
         * @return the protocol field value
         */
        @Nullable public String data() {
            return (String) value("data");
        }
        public static final class Builder {
            private final Map<String, Object> values = new LinkedHashMap<>();
            /**
             * Request identifier.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder requestId(@Nullable String value) {
                if (value == null) values.remove("requestId");
                else values.put("requestId", jsonValue(value));
                return this;
            }
            /**
             * Timestamp.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder timestamp(@Nullable Double value) {
                if (value == null) values.remove("timestamp");
                else values.put("timestamp", jsonValue(value));
                return this;
            }
            /**
             * Data chunk length.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder dataLength(@Nullable Long value) {
                if (value == null) values.remove("dataLength");
                else values.put("dataLength", jsonValue(value));
                return this;
            }
            /**
             * Actual bytes received (might be less than dataLength for compressed encodings).
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder encodedDataLength(@Nullable Long value) {
                if (value == null) values.remove("encodedDataLength");
                else values.put("encodedDataLength", jsonValue(value));
                return this;
            }
            /**
             * Data that was received. (Encoded as a base64 string when passed over JSON)
             * <p><b>Experimental:</b> this part of CDP may change without notice.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder data(@Nullable String value) {
                if (value == null) values.remove("data");
                else values.put("data", jsonValue(value));
                return this;
            }
            public DataReceivedEvent build() {
                if (!values.containsKey("requestId")) throw new IllegalStateException("Missing required CDP field: requestId");
                if (!values.containsKey("timestamp")) throw new IllegalStateException("Missing required CDP field: timestamp");
                if (!values.containsKey("dataLength")) throw new IllegalStateException("Missing required CDP field: dataLength");
                if (!values.containsKey("encodedDataLength")) throw new IllegalStateException("Missing required CDP field: encodedDataLength");
                return new DataReceivedEvent(values);
            }
        }
    }
    /**
     * Fired when EventSource message is received.
     */
    public static final class EventSourceMessageReceivedEvent extends CdpObject {
        private EventSourceMessageReceivedEvent(Map<String, Object> values) { super(values); }
        @Nullable public static EventSourceMessageReceivedEvent fromMap(@Nullable Map<String, Object> values) {
            return values == null ? null : new EventSourceMessageReceivedEvent(values);
        }
        public static Builder builder() { return new Builder(); }
        /**
         * Request identifier.
         * @return the protocol field value
         */
        @Nullable public String requestId() {
            return (String) value("requestId");
        }
        /**
         * Timestamp.
         * @return the protocol field value
         */
        @Nullable public Double timestamp() {
            return numberAsDouble(value("timestamp"));
        }
        /**
         * Message type.
         * @return the protocol field value
         */
        @Nullable public String eventName() {
            return (String) value("eventName");
        }
        /**
         * Message identifier.
         * @return the protocol field value
         */
        @Nullable public String eventId() {
            return (String) value("eventId");
        }
        /**
         * Message content.
         * @return the protocol field value
         */
        @Nullable public String data() {
            return (String) value("data");
        }
        public static final class Builder {
            private final Map<String, Object> values = new LinkedHashMap<>();
            /**
             * Request identifier.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder requestId(@Nullable String value) {
                if (value == null) values.remove("requestId");
                else values.put("requestId", jsonValue(value));
                return this;
            }
            /**
             * Timestamp.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder timestamp(@Nullable Double value) {
                if (value == null) values.remove("timestamp");
                else values.put("timestamp", jsonValue(value));
                return this;
            }
            /**
             * Message type.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder eventName(@Nullable String value) {
                if (value == null) values.remove("eventName");
                else values.put("eventName", jsonValue(value));
                return this;
            }
            /**
             * Message identifier.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder eventId(@Nullable String value) {
                if (value == null) values.remove("eventId");
                else values.put("eventId", jsonValue(value));
                return this;
            }
            /**
             * Message content.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder data(@Nullable String value) {
                if (value == null) values.remove("data");
                else values.put("data", jsonValue(value));
                return this;
            }
            public EventSourceMessageReceivedEvent build() {
                if (!values.containsKey("requestId")) throw new IllegalStateException("Missing required CDP field: requestId");
                if (!values.containsKey("timestamp")) throw new IllegalStateException("Missing required CDP field: timestamp");
                if (!values.containsKey("eventName")) throw new IllegalStateException("Missing required CDP field: eventName");
                if (!values.containsKey("eventId")) throw new IllegalStateException("Missing required CDP field: eventId");
                if (!values.containsKey("data")) throw new IllegalStateException("Missing required CDP field: data");
                return new EventSourceMessageReceivedEvent(values);
            }
        }
    }
    /**
     * Fired when HTTP request has failed to load.
     */
    public static final class LoadingFailedEvent extends CdpObject {
        private LoadingFailedEvent(Map<String, Object> values) { super(values); }
        @Nullable public static LoadingFailedEvent fromMap(@Nullable Map<String, Object> values) {
            return values == null ? null : new LoadingFailedEvent(values);
        }
        public static Builder builder() { return new Builder(); }
        /**
         * Request identifier.
         * @return the protocol field value
         */
        @Nullable public String requestId() {
            return (String) value("requestId");
        }
        /**
         * Timestamp.
         * @return the protocol field value
         */
        @Nullable public Double timestamp() {
            return numberAsDouble(value("timestamp"));
        }
        /**
         * Resource type.
         * @return the protocol field value
         */
        @Nullable public String type() {
            return (String) value("type");
        }
        /**
         * Error message. List of network errors: https://cs.chromium.org/chromium/src/net/base/net_error_list.h
         * @return the protocol field value
         */
        @Nullable public String errorText() {
            return (String) value("errorText");
        }
        /**
         * True if loading was canceled.
         * @return the protocol field value
         */
        @Nullable public Boolean canceled() {
            return (Boolean) value("canceled");
        }
        /**
         * The reason why loading was blocked, if any.
         * @return the protocol field value
         */
        @Nullable public String blockedReason() {
            return (String) value("blockedReason");
        }
        /**
         * The reason why loading was blocked by CORS, if any.
         * @return the protocol field value
         */
        @Nullable public Network.CorsErrorStatus corsErrorStatus() {
            return Network.CorsErrorStatus.fromMap(objectMap(value("corsErrorStatus")));
        }
        public static final class Builder {
            private final Map<String, Object> values = new LinkedHashMap<>();
            /**
             * Request identifier.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder requestId(@Nullable String value) {
                if (value == null) values.remove("requestId");
                else values.put("requestId", jsonValue(value));
                return this;
            }
            /**
             * Timestamp.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder timestamp(@Nullable Double value) {
                if (value == null) values.remove("timestamp");
                else values.put("timestamp", jsonValue(value));
                return this;
            }
            /**
             * Resource type.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder type(@Nullable String value) {
                if (value == null) values.remove("type");
                else values.put("type", jsonValue(value));
                return this;
            }
            /**
             * Error message. List of network errors: https://cs.chromium.org/chromium/src/net/base/net_error_list.h
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder errorText(@Nullable String value) {
                if (value == null) values.remove("errorText");
                else values.put("errorText", jsonValue(value));
                return this;
            }
            /**
             * True if loading was canceled.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder canceled(@Nullable Boolean value) {
                if (value == null) values.remove("canceled");
                else values.put("canceled", jsonValue(value));
                return this;
            }
            /**
             * The reason why loading was blocked, if any.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder blockedReason(@Nullable String value) {
                if (value == null) values.remove("blockedReason");
                else values.put("blockedReason", jsonValue(value));
                return this;
            }
            /**
             * The reason why loading was blocked by CORS, if any.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder corsErrorStatus(@Nullable Network.CorsErrorStatus value) {
                if (value == null) values.remove("corsErrorStatus");
                else values.put("corsErrorStatus", jsonValue(value));
                return this;
            }
            public LoadingFailedEvent build() {
                if (!values.containsKey("requestId")) throw new IllegalStateException("Missing required CDP field: requestId");
                if (!values.containsKey("timestamp")) throw new IllegalStateException("Missing required CDP field: timestamp");
                if (!values.containsKey("type")) throw new IllegalStateException("Missing required CDP field: type");
                if (!values.containsKey("errorText")) throw new IllegalStateException("Missing required CDP field: errorText");
                return new LoadingFailedEvent(values);
            }
        }
    }
    /**
     * Fired when HTTP request has finished loading.
     */
    public static final class LoadingFinishedEvent extends CdpObject {
        private LoadingFinishedEvent(Map<String, Object> values) { super(values); }
        @Nullable public static LoadingFinishedEvent fromMap(@Nullable Map<String, Object> values) {
            return values == null ? null : new LoadingFinishedEvent(values);
        }
        public static Builder builder() { return new Builder(); }
        /**
         * Request identifier.
         * @return the protocol field value
         */
        @Nullable public String requestId() {
            return (String) value("requestId");
        }
        /**
         * Timestamp.
         * @return the protocol field value
         */
        @Nullable public Double timestamp() {
            return numberAsDouble(value("timestamp"));
        }
        /**
         * Total number of bytes received for this request.
         * @return the protocol field value
         */
        @Nullable public Double encodedDataLength() {
            return numberAsDouble(value("encodedDataLength"));
        }
        public static final class Builder {
            private final Map<String, Object> values = new LinkedHashMap<>();
            /**
             * Request identifier.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder requestId(@Nullable String value) {
                if (value == null) values.remove("requestId");
                else values.put("requestId", jsonValue(value));
                return this;
            }
            /**
             * Timestamp.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder timestamp(@Nullable Double value) {
                if (value == null) values.remove("timestamp");
                else values.put("timestamp", jsonValue(value));
                return this;
            }
            /**
             * Total number of bytes received for this request.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder encodedDataLength(@Nullable Double value) {
                if (value == null) values.remove("encodedDataLength");
                else values.put("encodedDataLength", jsonValue(value));
                return this;
            }
            public LoadingFinishedEvent build() {
                if (!values.containsKey("requestId")) throw new IllegalStateException("Missing required CDP field: requestId");
                if (!values.containsKey("timestamp")) throw new IllegalStateException("Missing required CDP field: timestamp");
                if (!values.containsKey("encodedDataLength")) throw new IllegalStateException("Missing required CDP field: encodedDataLength");
                return new LoadingFinishedEvent(values);
            }
        }
    }
    /**
     * Details of an intercepted HTTP request, which must be either allowed, blocked, modified or mocked. Deprecated, use Fetch.requestPaused instead.
     * <p><b>Experimental:</b> this part of CDP may change without notice.
     * @deprecated Deprecated by the Chromium DevTools Protocol.
     */
    @Deprecated
    public static final class RequestInterceptedEvent extends CdpObject {
        private RequestInterceptedEvent(Map<String, Object> values) { super(values); }
        @Nullable public static RequestInterceptedEvent fromMap(@Nullable Map<String, Object> values) {
            return values == null ? null : new RequestInterceptedEvent(values);
        }
        public static Builder builder() { return new Builder(); }
        /**
         * Each request the page makes will have a unique id, however if any redirects are encountered while processing that fetch, they will be reported with the same id as the original fetch. Likewise if HTTP authentication is needed then the same fetch id will be used.
         * @return the protocol field value
         */
        @Nullable public String interceptionId() {
            return (String) value("interceptionId");
        }
        /**
         * Returns the request field.
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
         * Whether this is a navigation request, which can abort the navigation completely.
         * @return the protocol field value
         */
        @Nullable public Boolean isNavigationRequest() {
            return (Boolean) value("isNavigationRequest");
        }
        /**
         * Set if the request is a navigation that will result in a download. Only present after response is received from the server (i.e. HeadersReceived stage).
         * @return the protocol field value
         */
        @Nullable public Boolean isDownload() {
            return (Boolean) value("isDownload");
        }
        /**
         * Redirect location, only sent if a redirect was intercepted.
         * @return the protocol field value
         */
        @Nullable public String redirectUrl() {
            return (String) value("redirectUrl");
        }
        /**
         * Details of the Authorization Challenge encountered. If this is set then continueInterceptedRequest must contain an authChallengeResponse.
         * @return the protocol field value
         */
        @Nullable public Network.AuthChallenge authChallenge() {
            return Network.AuthChallenge.fromMap(objectMap(value("authChallenge")));
        }
        /**
         * Response error if intercepted at response stage or if redirect occurred while intercepting request.
         * @return the protocol field value
         */
        @Nullable public String responseErrorReason() {
            return (String) value("responseErrorReason");
        }
        /**
         * Response code if intercepted at response stage or if redirect occurred while intercepting request or auth retry occurred.
         * @return the protocol field value
         */
        @Nullable public Long responseStatusCode() {
            return numberAsLong(value("responseStatusCode"));
        }
        /**
         * Response headers if intercepted at the response stage or if redirect occurred while intercepting request or auth retry occurred.
         * @return the protocol field value
         */
        @Nullable public java.util.Map<String, Object> responseHeaders() {
            return objectMap(value("responseHeaders"));
        }
        /**
         * If the intercepted request had a corresponding requestWillBeSent event fired for it, then this requestId will be the same as the requestId present in the requestWillBeSent event.
         * @return the protocol field value
         */
        @Nullable public String requestId() {
            return (String) value("requestId");
        }
        public static final class Builder {
            private final Map<String, Object> values = new LinkedHashMap<>();
            /**
             * Each request the page makes will have a unique id, however if any redirects are encountered while processing that fetch, they will be reported with the same id as the original fetch. Likewise if HTTP authentication is needed then the same fetch id will be used.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder interceptionId(@Nullable String value) {
                if (value == null) values.remove("interceptionId");
                else values.put("interceptionId", jsonValue(value));
                return this;
            }
            /**
             * Sets the request field.
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
             * Whether this is a navigation request, which can abort the navigation completely.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder isNavigationRequest(@Nullable Boolean value) {
                if (value == null) values.remove("isNavigationRequest");
                else values.put("isNavigationRequest", jsonValue(value));
                return this;
            }
            /**
             * Set if the request is a navigation that will result in a download. Only present after response is received from the server (i.e. HeadersReceived stage).
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder isDownload(@Nullable Boolean value) {
                if (value == null) values.remove("isDownload");
                else values.put("isDownload", jsonValue(value));
                return this;
            }
            /**
             * Redirect location, only sent if a redirect was intercepted.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder redirectUrl(@Nullable String value) {
                if (value == null) values.remove("redirectUrl");
                else values.put("redirectUrl", jsonValue(value));
                return this;
            }
            /**
             * Details of the Authorization Challenge encountered. If this is set then continueInterceptedRequest must contain an authChallengeResponse.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder authChallenge(@Nullable Network.AuthChallenge value) {
                if (value == null) values.remove("authChallenge");
                else values.put("authChallenge", jsonValue(value));
                return this;
            }
            /**
             * Response error if intercepted at response stage or if redirect occurred while intercepting request.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder responseErrorReason(@Nullable String value) {
                if (value == null) values.remove("responseErrorReason");
                else values.put("responseErrorReason", jsonValue(value));
                return this;
            }
            /**
             * Response code if intercepted at response stage or if redirect occurred while intercepting request or auth retry occurred.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder responseStatusCode(@Nullable Long value) {
                if (value == null) values.remove("responseStatusCode");
                else values.put("responseStatusCode", jsonValue(value));
                return this;
            }
            /**
             * Response headers if intercepted at the response stage or if redirect occurred while intercepting request or auth retry occurred.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder responseHeaders(@Nullable java.util.Map<String, Object> value) {
                if (value == null) values.remove("responseHeaders");
                else values.put("responseHeaders", jsonValue(value));
                return this;
            }
            /**
             * If the intercepted request had a corresponding requestWillBeSent event fired for it, then this requestId will be the same as the requestId present in the requestWillBeSent event.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder requestId(@Nullable String value) {
                if (value == null) values.remove("requestId");
                else values.put("requestId", jsonValue(value));
                return this;
            }
            public RequestInterceptedEvent build() {
                if (!values.containsKey("interceptionId")) throw new IllegalStateException("Missing required CDP field: interceptionId");
                if (!values.containsKey("request")) throw new IllegalStateException("Missing required CDP field: request");
                if (!values.containsKey("frameId")) throw new IllegalStateException("Missing required CDP field: frameId");
                if (!values.containsKey("resourceType")) throw new IllegalStateException("Missing required CDP field: resourceType");
                if (!values.containsKey("isNavigationRequest")) throw new IllegalStateException("Missing required CDP field: isNavigationRequest");
                return new RequestInterceptedEvent(values);
            }
        }
    }
    /**
     * Fired if request ended up loading from cache.
     */
    public static final class RequestServedFromCacheEvent extends CdpObject {
        private RequestServedFromCacheEvent(Map<String, Object> values) { super(values); }
        @Nullable public static RequestServedFromCacheEvent fromMap(@Nullable Map<String, Object> values) {
            return values == null ? null : new RequestServedFromCacheEvent(values);
        }
        public static Builder builder() { return new Builder(); }
        /**
         * Request identifier.
         * @return the protocol field value
         */
        @Nullable public String requestId() {
            return (String) value("requestId");
        }
        public static final class Builder {
            private final Map<String, Object> values = new LinkedHashMap<>();
            /**
             * Request identifier.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder requestId(@Nullable String value) {
                if (value == null) values.remove("requestId");
                else values.put("requestId", jsonValue(value));
                return this;
            }
            public RequestServedFromCacheEvent build() {
                if (!values.containsKey("requestId")) throw new IllegalStateException("Missing required CDP field: requestId");
                return new RequestServedFromCacheEvent(values);
            }
        }
    }
    /**
     * Fired when page is about to send HTTP request.
     */
    public static final class RequestWillBeSentEvent extends CdpObject {
        private RequestWillBeSentEvent(Map<String, Object> values) { super(values); }
        @Nullable public static RequestWillBeSentEvent fromMap(@Nullable Map<String, Object> values) {
            return values == null ? null : new RequestWillBeSentEvent(values);
        }
        public static Builder builder() { return new Builder(); }
        /**
         * Request identifier.
         * @return the protocol field value
         */
        @Nullable public String requestId() {
            return (String) value("requestId");
        }
        /**
         * Loader identifier. Empty string if the request is fetched from worker.
         * @return the protocol field value
         */
        @Nullable public String loaderId() {
            return (String) value("loaderId");
        }
        /**
         * URL of the document this request is loaded for.
         * @return the protocol field value
         */
        @Nullable public String documentURL() {
            return (String) value("documentURL");
        }
        /**
         * Request data.
         * @return the protocol field value
         */
        @Nullable public Network.Request request() {
            return Network.Request.fromMap(objectMap(value("request")));
        }
        /**
         * Timestamp.
         * @return the protocol field value
         */
        @Nullable public Double timestamp() {
            return numberAsDouble(value("timestamp"));
        }
        /**
         * Timestamp.
         * @return the protocol field value
         */
        @Nullable public Double wallTime() {
            return numberAsDouble(value("wallTime"));
        }
        /**
         * Request initiator.
         * @return the protocol field value
         */
        @Nullable public Network.Initiator initiator() {
            return Network.Initiator.fromMap(objectMap(value("initiator")));
        }
        /**
         * In the case that redirectResponse is populated, this flag indicates whether requestWillBeSentExtraInfo and responseReceivedExtraInfo events will be or were emitted for the request which was just redirected.
         * <p><b>Experimental:</b> this part of CDP may change without notice.
         * @return the protocol field value
         */
        @Nullable public Boolean redirectHasExtraInfo() {
            return (Boolean) value("redirectHasExtraInfo");
        }
        /**
         * Redirect response data.
         * @return the protocol field value
         */
        @Nullable public Network.Response redirectResponse() {
            return Network.Response.fromMap(objectMap(value("redirectResponse")));
        }
        /**
         * Type of this resource.
         * @return the protocol field value
         */
        @Nullable public String type() {
            return (String) value("type");
        }
        /**
         * Frame identifier.
         * @return the protocol field value
         */
        @Nullable public String frameId() {
            return (String) value("frameId");
        }
        /**
         * Whether the request is initiated by a user gesture. Defaults to false.
         * @return the protocol field value
         */
        @Nullable public Boolean hasUserGesture() {
            return (Boolean) value("hasUserGesture");
        }
        /**
         * The render-blocking behavior of the request.
         * <p><b>Experimental:</b> this part of CDP may change without notice.
         * @return the protocol field value
         */
        @Nullable public String renderBlockingBehavior() {
            return (String) value("renderBlockingBehavior");
        }
        public static final class Builder {
            private final Map<String, Object> values = new LinkedHashMap<>();
            /**
             * Request identifier.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder requestId(@Nullable String value) {
                if (value == null) values.remove("requestId");
                else values.put("requestId", jsonValue(value));
                return this;
            }
            /**
             * Loader identifier. Empty string if the request is fetched from worker.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder loaderId(@Nullable String value) {
                if (value == null) values.remove("loaderId");
                else values.put("loaderId", jsonValue(value));
                return this;
            }
            /**
             * URL of the document this request is loaded for.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder documentURL(@Nullable String value) {
                if (value == null) values.remove("documentURL");
                else values.put("documentURL", jsonValue(value));
                return this;
            }
            /**
             * Request data.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder request(@Nullable Network.Request value) {
                if (value == null) values.remove("request");
                else values.put("request", jsonValue(value));
                return this;
            }
            /**
             * Timestamp.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder timestamp(@Nullable Double value) {
                if (value == null) values.remove("timestamp");
                else values.put("timestamp", jsonValue(value));
                return this;
            }
            /**
             * Timestamp.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder wallTime(@Nullable Double value) {
                if (value == null) values.remove("wallTime");
                else values.put("wallTime", jsonValue(value));
                return this;
            }
            /**
             * Request initiator.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder initiator(@Nullable Network.Initiator value) {
                if (value == null) values.remove("initiator");
                else values.put("initiator", jsonValue(value));
                return this;
            }
            /**
             * In the case that redirectResponse is populated, this flag indicates whether requestWillBeSentExtraInfo and responseReceivedExtraInfo events will be or were emitted for the request which was just redirected.
             * <p><b>Experimental:</b> this part of CDP may change without notice.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder redirectHasExtraInfo(@Nullable Boolean value) {
                if (value == null) values.remove("redirectHasExtraInfo");
                else values.put("redirectHasExtraInfo", jsonValue(value));
                return this;
            }
            /**
             * Redirect response data.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder redirectResponse(@Nullable Network.Response value) {
                if (value == null) values.remove("redirectResponse");
                else values.put("redirectResponse", jsonValue(value));
                return this;
            }
            /**
             * Type of this resource.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder type(@Nullable String value) {
                if (value == null) values.remove("type");
                else values.put("type", jsonValue(value));
                return this;
            }
            /**
             * Frame identifier.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder frameId(@Nullable String value) {
                if (value == null) values.remove("frameId");
                else values.put("frameId", jsonValue(value));
                return this;
            }
            /**
             * Whether the request is initiated by a user gesture. Defaults to false.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder hasUserGesture(@Nullable Boolean value) {
                if (value == null) values.remove("hasUserGesture");
                else values.put("hasUserGesture", jsonValue(value));
                return this;
            }
            /**
             * The render-blocking behavior of the request.
             * <p><b>Experimental:</b> this part of CDP may change without notice.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder renderBlockingBehavior(@Nullable String value) {
                if (value == null) values.remove("renderBlockingBehavior");
                else values.put("renderBlockingBehavior", jsonValue(value));
                return this;
            }
            public RequestWillBeSentEvent build() {
                if (!values.containsKey("requestId")) throw new IllegalStateException("Missing required CDP field: requestId");
                if (!values.containsKey("loaderId")) throw new IllegalStateException("Missing required CDP field: loaderId");
                if (!values.containsKey("documentURL")) throw new IllegalStateException("Missing required CDP field: documentURL");
                if (!values.containsKey("request")) throw new IllegalStateException("Missing required CDP field: request");
                if (!values.containsKey("timestamp")) throw new IllegalStateException("Missing required CDP field: timestamp");
                if (!values.containsKey("wallTime")) throw new IllegalStateException("Missing required CDP field: wallTime");
                if (!values.containsKey("initiator")) throw new IllegalStateException("Missing required CDP field: initiator");
                if (!values.containsKey("redirectHasExtraInfo")) throw new IllegalStateException("Missing required CDP field: redirectHasExtraInfo");
                return new RequestWillBeSentEvent(values);
            }
        }
    }
    /**
     * Fired when resource loading priority is changed
     * <p><b>Experimental:</b> this part of CDP may change without notice.
     */
    public static final class ResourceChangedPriorityEvent extends CdpObject {
        private ResourceChangedPriorityEvent(Map<String, Object> values) { super(values); }
        @Nullable public static ResourceChangedPriorityEvent fromMap(@Nullable Map<String, Object> values) {
            return values == null ? null : new ResourceChangedPriorityEvent(values);
        }
        public static Builder builder() { return new Builder(); }
        /**
         * Request identifier.
         * @return the protocol field value
         */
        @Nullable public String requestId() {
            return (String) value("requestId");
        }
        /**
         * New priority
         * @return the protocol field value
         */
        @Nullable public String newPriority() {
            return (String) value("newPriority");
        }
        /**
         * Timestamp.
         * @return the protocol field value
         */
        @Nullable public Double timestamp() {
            return numberAsDouble(value("timestamp"));
        }
        public static final class Builder {
            private final Map<String, Object> values = new LinkedHashMap<>();
            /**
             * Request identifier.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder requestId(@Nullable String value) {
                if (value == null) values.remove("requestId");
                else values.put("requestId", jsonValue(value));
                return this;
            }
            /**
             * New priority
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder newPriority(@Nullable String value) {
                if (value == null) values.remove("newPriority");
                else values.put("newPriority", jsonValue(value));
                return this;
            }
            /**
             * Timestamp.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder timestamp(@Nullable Double value) {
                if (value == null) values.remove("timestamp");
                else values.put("timestamp", jsonValue(value));
                return this;
            }
            public ResourceChangedPriorityEvent build() {
                if (!values.containsKey("requestId")) throw new IllegalStateException("Missing required CDP field: requestId");
                if (!values.containsKey("newPriority")) throw new IllegalStateException("Missing required CDP field: newPriority");
                if (!values.containsKey("timestamp")) throw new IllegalStateException("Missing required CDP field: timestamp");
                return new ResourceChangedPriorityEvent(values);
            }
        }
    }
    /**
     * Fired when a signed exchange was received over the network
     * <p><b>Experimental:</b> this part of CDP may change without notice.
     */
    public static final class SignedExchangeReceivedEvent extends CdpObject {
        private SignedExchangeReceivedEvent(Map<String, Object> values) { super(values); }
        @Nullable public static SignedExchangeReceivedEvent fromMap(@Nullable Map<String, Object> values) {
            return values == null ? null : new SignedExchangeReceivedEvent(values);
        }
        public static Builder builder() { return new Builder(); }
        /**
         * Request identifier.
         * @return the protocol field value
         */
        @Nullable public String requestId() {
            return (String) value("requestId");
        }
        /**
         * Information about the signed exchange response.
         * @return the protocol field value
         */
        @Nullable public Network.SignedExchangeInfo info() {
            return Network.SignedExchangeInfo.fromMap(objectMap(value("info")));
        }
        public static final class Builder {
            private final Map<String, Object> values = new LinkedHashMap<>();
            /**
             * Request identifier.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder requestId(@Nullable String value) {
                if (value == null) values.remove("requestId");
                else values.put("requestId", jsonValue(value));
                return this;
            }
            /**
             * Information about the signed exchange response.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder info(@Nullable Network.SignedExchangeInfo value) {
                if (value == null) values.remove("info");
                else values.put("info", jsonValue(value));
                return this;
            }
            public SignedExchangeReceivedEvent build() {
                if (!values.containsKey("requestId")) throw new IllegalStateException("Missing required CDP field: requestId");
                if (!values.containsKey("info")) throw new IllegalStateException("Missing required CDP field: info");
                return new SignedExchangeReceivedEvent(values);
            }
        }
    }
    /**
     * Fired when HTTP response is available.
     */
    public static final class ResponseReceivedEvent extends CdpObject {
        private ResponseReceivedEvent(Map<String, Object> values) { super(values); }
        @Nullable public static ResponseReceivedEvent fromMap(@Nullable Map<String, Object> values) {
            return values == null ? null : new ResponseReceivedEvent(values);
        }
        public static Builder builder() { return new Builder(); }
        /**
         * Request identifier.
         * @return the protocol field value
         */
        @Nullable public String requestId() {
            return (String) value("requestId");
        }
        /**
         * Loader identifier. Empty string if the request is fetched from worker.
         * @return the protocol field value
         */
        @Nullable public String loaderId() {
            return (String) value("loaderId");
        }
        /**
         * Timestamp.
         * @return the protocol field value
         */
        @Nullable public Double timestamp() {
            return numberAsDouble(value("timestamp"));
        }
        /**
         * Resource type.
         * @return the protocol field value
         */
        @Nullable public String type() {
            return (String) value("type");
        }
        /**
         * Response data.
         * @return the protocol field value
         */
        @Nullable public Network.Response response() {
            return Network.Response.fromMap(objectMap(value("response")));
        }
        /**
         * Indicates whether requestWillBeSentExtraInfo and responseReceivedExtraInfo events will be or were emitted for this request.
         * <p><b>Experimental:</b> this part of CDP may change without notice.
         * @return the protocol field value
         */
        @Nullable public Boolean hasExtraInfo() {
            return (Boolean) value("hasExtraInfo");
        }
        /**
         * Frame identifier.
         * @return the protocol field value
         */
        @Nullable public String frameId() {
            return (String) value("frameId");
        }
        public static final class Builder {
            private final Map<String, Object> values = new LinkedHashMap<>();
            /**
             * Request identifier.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder requestId(@Nullable String value) {
                if (value == null) values.remove("requestId");
                else values.put("requestId", jsonValue(value));
                return this;
            }
            /**
             * Loader identifier. Empty string if the request is fetched from worker.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder loaderId(@Nullable String value) {
                if (value == null) values.remove("loaderId");
                else values.put("loaderId", jsonValue(value));
                return this;
            }
            /**
             * Timestamp.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder timestamp(@Nullable Double value) {
                if (value == null) values.remove("timestamp");
                else values.put("timestamp", jsonValue(value));
                return this;
            }
            /**
             * Resource type.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder type(@Nullable String value) {
                if (value == null) values.remove("type");
                else values.put("type", jsonValue(value));
                return this;
            }
            /**
             * Response data.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder response(@Nullable Network.Response value) {
                if (value == null) values.remove("response");
                else values.put("response", jsonValue(value));
                return this;
            }
            /**
             * Indicates whether requestWillBeSentExtraInfo and responseReceivedExtraInfo events will be or were emitted for this request.
             * <p><b>Experimental:</b> this part of CDP may change without notice.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder hasExtraInfo(@Nullable Boolean value) {
                if (value == null) values.remove("hasExtraInfo");
                else values.put("hasExtraInfo", jsonValue(value));
                return this;
            }
            /**
             * Frame identifier.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder frameId(@Nullable String value) {
                if (value == null) values.remove("frameId");
                else values.put("frameId", jsonValue(value));
                return this;
            }
            public ResponseReceivedEvent build() {
                if (!values.containsKey("requestId")) throw new IllegalStateException("Missing required CDP field: requestId");
                if (!values.containsKey("loaderId")) throw new IllegalStateException("Missing required CDP field: loaderId");
                if (!values.containsKey("timestamp")) throw new IllegalStateException("Missing required CDP field: timestamp");
                if (!values.containsKey("type")) throw new IllegalStateException("Missing required CDP field: type");
                if (!values.containsKey("response")) throw new IllegalStateException("Missing required CDP field: response");
                if (!values.containsKey("hasExtraInfo")) throw new IllegalStateException("Missing required CDP field: hasExtraInfo");
                return new ResponseReceivedEvent(values);
            }
        }
    }
    /**
     * Fired when WebSocket is closed.
     */
    public static final class WebSocketClosedEvent extends CdpObject {
        private WebSocketClosedEvent(Map<String, Object> values) { super(values); }
        @Nullable public static WebSocketClosedEvent fromMap(@Nullable Map<String, Object> values) {
            return values == null ? null : new WebSocketClosedEvent(values);
        }
        public static Builder builder() { return new Builder(); }
        /**
         * Request identifier.
         * @return the protocol field value
         */
        @Nullable public String requestId() {
            return (String) value("requestId");
        }
        /**
         * Timestamp.
         * @return the protocol field value
         */
        @Nullable public Double timestamp() {
            return numberAsDouble(value("timestamp"));
        }
        public static final class Builder {
            private final Map<String, Object> values = new LinkedHashMap<>();
            /**
             * Request identifier.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder requestId(@Nullable String value) {
                if (value == null) values.remove("requestId");
                else values.put("requestId", jsonValue(value));
                return this;
            }
            /**
             * Timestamp.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder timestamp(@Nullable Double value) {
                if (value == null) values.remove("timestamp");
                else values.put("timestamp", jsonValue(value));
                return this;
            }
            public WebSocketClosedEvent build() {
                if (!values.containsKey("requestId")) throw new IllegalStateException("Missing required CDP field: requestId");
                if (!values.containsKey("timestamp")) throw new IllegalStateException("Missing required CDP field: timestamp");
                return new WebSocketClosedEvent(values);
            }
        }
    }
    /**
     * Fired upon WebSocket creation.
     */
    public static final class WebSocketCreatedEvent extends CdpObject {
        private WebSocketCreatedEvent(Map<String, Object> values) { super(values); }
        @Nullable public static WebSocketCreatedEvent fromMap(@Nullable Map<String, Object> values) {
            return values == null ? null : new WebSocketCreatedEvent(values);
        }
        public static Builder builder() { return new Builder(); }
        /**
         * Request identifier.
         * @return the protocol field value
         */
        @Nullable public String requestId() {
            return (String) value("requestId");
        }
        /**
         * WebSocket request URL.
         * @return the protocol field value
         */
        @Nullable public String url() {
            return (String) value("url");
        }
        /**
         * Request initiator.
         * @return the protocol field value
         */
        @Nullable public Network.Initiator initiator() {
            return Network.Initiator.fromMap(objectMap(value("initiator")));
        }
        public static final class Builder {
            private final Map<String, Object> values = new LinkedHashMap<>();
            /**
             * Request identifier.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder requestId(@Nullable String value) {
                if (value == null) values.remove("requestId");
                else values.put("requestId", jsonValue(value));
                return this;
            }
            /**
             * WebSocket request URL.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder url(@Nullable String value) {
                if (value == null) values.remove("url");
                else values.put("url", jsonValue(value));
                return this;
            }
            /**
             * Request initiator.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder initiator(@Nullable Network.Initiator value) {
                if (value == null) values.remove("initiator");
                else values.put("initiator", jsonValue(value));
                return this;
            }
            public WebSocketCreatedEvent build() {
                if (!values.containsKey("requestId")) throw new IllegalStateException("Missing required CDP field: requestId");
                if (!values.containsKey("url")) throw new IllegalStateException("Missing required CDP field: url");
                return new WebSocketCreatedEvent(values);
            }
        }
    }
    /**
     * Fired when WebSocket message error occurs.
     */
    public static final class WebSocketFrameErrorEvent extends CdpObject {
        private WebSocketFrameErrorEvent(Map<String, Object> values) { super(values); }
        @Nullable public static WebSocketFrameErrorEvent fromMap(@Nullable Map<String, Object> values) {
            return values == null ? null : new WebSocketFrameErrorEvent(values);
        }
        public static Builder builder() { return new Builder(); }
        /**
         * Request identifier.
         * @return the protocol field value
         */
        @Nullable public String requestId() {
            return (String) value("requestId");
        }
        /**
         * Timestamp.
         * @return the protocol field value
         */
        @Nullable public Double timestamp() {
            return numberAsDouble(value("timestamp"));
        }
        /**
         * WebSocket error message.
         * @return the protocol field value
         */
        @Nullable public String errorMessage() {
            return (String) value("errorMessage");
        }
        public static final class Builder {
            private final Map<String, Object> values = new LinkedHashMap<>();
            /**
             * Request identifier.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder requestId(@Nullable String value) {
                if (value == null) values.remove("requestId");
                else values.put("requestId", jsonValue(value));
                return this;
            }
            /**
             * Timestamp.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder timestamp(@Nullable Double value) {
                if (value == null) values.remove("timestamp");
                else values.put("timestamp", jsonValue(value));
                return this;
            }
            /**
             * WebSocket error message.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder errorMessage(@Nullable String value) {
                if (value == null) values.remove("errorMessage");
                else values.put("errorMessage", jsonValue(value));
                return this;
            }
            public WebSocketFrameErrorEvent build() {
                if (!values.containsKey("requestId")) throw new IllegalStateException("Missing required CDP field: requestId");
                if (!values.containsKey("timestamp")) throw new IllegalStateException("Missing required CDP field: timestamp");
                if (!values.containsKey("errorMessage")) throw new IllegalStateException("Missing required CDP field: errorMessage");
                return new WebSocketFrameErrorEvent(values);
            }
        }
    }
    /**
     * Fired when WebSocket message is received.
     */
    public static final class WebSocketFrameReceivedEvent extends CdpObject {
        private WebSocketFrameReceivedEvent(Map<String, Object> values) { super(values); }
        @Nullable public static WebSocketFrameReceivedEvent fromMap(@Nullable Map<String, Object> values) {
            return values == null ? null : new WebSocketFrameReceivedEvent(values);
        }
        public static Builder builder() { return new Builder(); }
        /**
         * Request identifier.
         * @return the protocol field value
         */
        @Nullable public String requestId() {
            return (String) value("requestId");
        }
        /**
         * Timestamp.
         * @return the protocol field value
         */
        @Nullable public Double timestamp() {
            return numberAsDouble(value("timestamp"));
        }
        /**
         * WebSocket response data.
         * @return the protocol field value
         */
        @Nullable public Network.WebSocketFrame response() {
            return Network.WebSocketFrame.fromMap(objectMap(value("response")));
        }
        public static final class Builder {
            private final Map<String, Object> values = new LinkedHashMap<>();
            /**
             * Request identifier.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder requestId(@Nullable String value) {
                if (value == null) values.remove("requestId");
                else values.put("requestId", jsonValue(value));
                return this;
            }
            /**
             * Timestamp.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder timestamp(@Nullable Double value) {
                if (value == null) values.remove("timestamp");
                else values.put("timestamp", jsonValue(value));
                return this;
            }
            /**
             * WebSocket response data.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder response(@Nullable Network.WebSocketFrame value) {
                if (value == null) values.remove("response");
                else values.put("response", jsonValue(value));
                return this;
            }
            public WebSocketFrameReceivedEvent build() {
                if (!values.containsKey("requestId")) throw new IllegalStateException("Missing required CDP field: requestId");
                if (!values.containsKey("timestamp")) throw new IllegalStateException("Missing required CDP field: timestamp");
                if (!values.containsKey("response")) throw new IllegalStateException("Missing required CDP field: response");
                return new WebSocketFrameReceivedEvent(values);
            }
        }
    }
    /**
     * Fired when WebSocket message is sent.
     */
    public static final class WebSocketFrameSentEvent extends CdpObject {
        private WebSocketFrameSentEvent(Map<String, Object> values) { super(values); }
        @Nullable public static WebSocketFrameSentEvent fromMap(@Nullable Map<String, Object> values) {
            return values == null ? null : new WebSocketFrameSentEvent(values);
        }
        public static Builder builder() { return new Builder(); }
        /**
         * Request identifier.
         * @return the protocol field value
         */
        @Nullable public String requestId() {
            return (String) value("requestId");
        }
        /**
         * Timestamp.
         * @return the protocol field value
         */
        @Nullable public Double timestamp() {
            return numberAsDouble(value("timestamp"));
        }
        /**
         * WebSocket response data.
         * @return the protocol field value
         */
        @Nullable public Network.WebSocketFrame response() {
            return Network.WebSocketFrame.fromMap(objectMap(value("response")));
        }
        public static final class Builder {
            private final Map<String, Object> values = new LinkedHashMap<>();
            /**
             * Request identifier.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder requestId(@Nullable String value) {
                if (value == null) values.remove("requestId");
                else values.put("requestId", jsonValue(value));
                return this;
            }
            /**
             * Timestamp.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder timestamp(@Nullable Double value) {
                if (value == null) values.remove("timestamp");
                else values.put("timestamp", jsonValue(value));
                return this;
            }
            /**
             * WebSocket response data.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder response(@Nullable Network.WebSocketFrame value) {
                if (value == null) values.remove("response");
                else values.put("response", jsonValue(value));
                return this;
            }
            public WebSocketFrameSentEvent build() {
                if (!values.containsKey("requestId")) throw new IllegalStateException("Missing required CDP field: requestId");
                if (!values.containsKey("timestamp")) throw new IllegalStateException("Missing required CDP field: timestamp");
                if (!values.containsKey("response")) throw new IllegalStateException("Missing required CDP field: response");
                return new WebSocketFrameSentEvent(values);
            }
        }
    }
    /**
     * Fired when WebSocket handshake response becomes available.
     */
    public static final class WebSocketHandshakeResponseReceivedEvent extends CdpObject {
        private WebSocketHandshakeResponseReceivedEvent(Map<String, Object> values) { super(values); }
        @Nullable public static WebSocketHandshakeResponseReceivedEvent fromMap(@Nullable Map<String, Object> values) {
            return values == null ? null : new WebSocketHandshakeResponseReceivedEvent(values);
        }
        public static Builder builder() { return new Builder(); }
        /**
         * Request identifier.
         * @return the protocol field value
         */
        @Nullable public String requestId() {
            return (String) value("requestId");
        }
        /**
         * Timestamp.
         * @return the protocol field value
         */
        @Nullable public Double timestamp() {
            return numberAsDouble(value("timestamp"));
        }
        /**
         * WebSocket response data.
         * @return the protocol field value
         */
        @Nullable public Network.WebSocketResponse response() {
            return Network.WebSocketResponse.fromMap(objectMap(value("response")));
        }
        public static final class Builder {
            private final Map<String, Object> values = new LinkedHashMap<>();
            /**
             * Request identifier.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder requestId(@Nullable String value) {
                if (value == null) values.remove("requestId");
                else values.put("requestId", jsonValue(value));
                return this;
            }
            /**
             * Timestamp.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder timestamp(@Nullable Double value) {
                if (value == null) values.remove("timestamp");
                else values.put("timestamp", jsonValue(value));
                return this;
            }
            /**
             * WebSocket response data.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder response(@Nullable Network.WebSocketResponse value) {
                if (value == null) values.remove("response");
                else values.put("response", jsonValue(value));
                return this;
            }
            public WebSocketHandshakeResponseReceivedEvent build() {
                if (!values.containsKey("requestId")) throw new IllegalStateException("Missing required CDP field: requestId");
                if (!values.containsKey("timestamp")) throw new IllegalStateException("Missing required CDP field: timestamp");
                if (!values.containsKey("response")) throw new IllegalStateException("Missing required CDP field: response");
                return new WebSocketHandshakeResponseReceivedEvent(values);
            }
        }
    }
    /**
     * Fired when WebSocket is about to initiate handshake.
     */
    public static final class WebSocketWillSendHandshakeRequestEvent extends CdpObject {
        private WebSocketWillSendHandshakeRequestEvent(Map<String, Object> values) { super(values); }
        @Nullable public static WebSocketWillSendHandshakeRequestEvent fromMap(@Nullable Map<String, Object> values) {
            return values == null ? null : new WebSocketWillSendHandshakeRequestEvent(values);
        }
        public static Builder builder() { return new Builder(); }
        /**
         * Request identifier.
         * @return the protocol field value
         */
        @Nullable public String requestId() {
            return (String) value("requestId");
        }
        /**
         * Timestamp.
         * @return the protocol field value
         */
        @Nullable public Double timestamp() {
            return numberAsDouble(value("timestamp"));
        }
        /**
         * UTC Timestamp.
         * @return the protocol field value
         */
        @Nullable public Double wallTime() {
            return numberAsDouble(value("wallTime"));
        }
        /**
         * WebSocket request data.
         * @return the protocol field value
         */
        @Nullable public Network.WebSocketRequest request() {
            return Network.WebSocketRequest.fromMap(objectMap(value("request")));
        }
        public static final class Builder {
            private final Map<String, Object> values = new LinkedHashMap<>();
            /**
             * Request identifier.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder requestId(@Nullable String value) {
                if (value == null) values.remove("requestId");
                else values.put("requestId", jsonValue(value));
                return this;
            }
            /**
             * Timestamp.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder timestamp(@Nullable Double value) {
                if (value == null) values.remove("timestamp");
                else values.put("timestamp", jsonValue(value));
                return this;
            }
            /**
             * UTC Timestamp.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder wallTime(@Nullable Double value) {
                if (value == null) values.remove("wallTime");
                else values.put("wallTime", jsonValue(value));
                return this;
            }
            /**
             * WebSocket request data.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder request(@Nullable Network.WebSocketRequest value) {
                if (value == null) values.remove("request");
                else values.put("request", jsonValue(value));
                return this;
            }
            public WebSocketWillSendHandshakeRequestEvent build() {
                if (!values.containsKey("requestId")) throw new IllegalStateException("Missing required CDP field: requestId");
                if (!values.containsKey("timestamp")) throw new IllegalStateException("Missing required CDP field: timestamp");
                if (!values.containsKey("wallTime")) throw new IllegalStateException("Missing required CDP field: wallTime");
                if (!values.containsKey("request")) throw new IllegalStateException("Missing required CDP field: request");
                return new WebSocketWillSendHandshakeRequestEvent(values);
            }
        }
    }
    /**
     * Fired upon WebTransport creation.
     */
    public static final class WebTransportCreatedEvent extends CdpObject {
        private WebTransportCreatedEvent(Map<String, Object> values) { super(values); }
        @Nullable public static WebTransportCreatedEvent fromMap(@Nullable Map<String, Object> values) {
            return values == null ? null : new WebTransportCreatedEvent(values);
        }
        public static Builder builder() { return new Builder(); }
        /**
         * WebTransport identifier.
         * @return the protocol field value
         */
        @Nullable public String transportId() {
            return (String) value("transportId");
        }
        /**
         * WebTransport request URL.
         * @return the protocol field value
         */
        @Nullable public String url() {
            return (String) value("url");
        }
        /**
         * Timestamp.
         * @return the protocol field value
         */
        @Nullable public Double timestamp() {
            return numberAsDouble(value("timestamp"));
        }
        /**
         * Request initiator.
         * @return the protocol field value
         */
        @Nullable public Network.Initiator initiator() {
            return Network.Initiator.fromMap(objectMap(value("initiator")));
        }
        public static final class Builder {
            private final Map<String, Object> values = new LinkedHashMap<>();
            /**
             * WebTransport identifier.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder transportId(@Nullable String value) {
                if (value == null) values.remove("transportId");
                else values.put("transportId", jsonValue(value));
                return this;
            }
            /**
             * WebTransport request URL.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder url(@Nullable String value) {
                if (value == null) values.remove("url");
                else values.put("url", jsonValue(value));
                return this;
            }
            /**
             * Timestamp.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder timestamp(@Nullable Double value) {
                if (value == null) values.remove("timestamp");
                else values.put("timestamp", jsonValue(value));
                return this;
            }
            /**
             * Request initiator.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder initiator(@Nullable Network.Initiator value) {
                if (value == null) values.remove("initiator");
                else values.put("initiator", jsonValue(value));
                return this;
            }
            public WebTransportCreatedEvent build() {
                if (!values.containsKey("transportId")) throw new IllegalStateException("Missing required CDP field: transportId");
                if (!values.containsKey("url")) throw new IllegalStateException("Missing required CDP field: url");
                if (!values.containsKey("timestamp")) throw new IllegalStateException("Missing required CDP field: timestamp");
                return new WebTransportCreatedEvent(values);
            }
        }
    }
    /**
     * Fired when WebTransport handshake is finished.
     */
    public static final class WebTransportConnectionEstablishedEvent extends CdpObject {
        private WebTransportConnectionEstablishedEvent(Map<String, Object> values) { super(values); }
        @Nullable public static WebTransportConnectionEstablishedEvent fromMap(@Nullable Map<String, Object> values) {
            return values == null ? null : new WebTransportConnectionEstablishedEvent(values);
        }
        public static Builder builder() { return new Builder(); }
        /**
         * WebTransport identifier.
         * @return the protocol field value
         */
        @Nullable public String transportId() {
            return (String) value("transportId");
        }
        /**
         * Timestamp.
         * @return the protocol field value
         */
        @Nullable public Double timestamp() {
            return numberAsDouble(value("timestamp"));
        }
        public static final class Builder {
            private final Map<String, Object> values = new LinkedHashMap<>();
            /**
             * WebTransport identifier.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder transportId(@Nullable String value) {
                if (value == null) values.remove("transportId");
                else values.put("transportId", jsonValue(value));
                return this;
            }
            /**
             * Timestamp.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder timestamp(@Nullable Double value) {
                if (value == null) values.remove("timestamp");
                else values.put("timestamp", jsonValue(value));
                return this;
            }
            public WebTransportConnectionEstablishedEvent build() {
                if (!values.containsKey("transportId")) throw new IllegalStateException("Missing required CDP field: transportId");
                if (!values.containsKey("timestamp")) throw new IllegalStateException("Missing required CDP field: timestamp");
                return new WebTransportConnectionEstablishedEvent(values);
            }
        }
    }
    /**
     * Fired when WebTransport is disposed.
     */
    public static final class WebTransportClosedEvent extends CdpObject {
        private WebTransportClosedEvent(Map<String, Object> values) { super(values); }
        @Nullable public static WebTransportClosedEvent fromMap(@Nullable Map<String, Object> values) {
            return values == null ? null : new WebTransportClosedEvent(values);
        }
        public static Builder builder() { return new Builder(); }
        /**
         * WebTransport identifier.
         * @return the protocol field value
         */
        @Nullable public String transportId() {
            return (String) value("transportId");
        }
        /**
         * Timestamp.
         * @return the protocol field value
         */
        @Nullable public Double timestamp() {
            return numberAsDouble(value("timestamp"));
        }
        public static final class Builder {
            private final Map<String, Object> values = new LinkedHashMap<>();
            /**
             * WebTransport identifier.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder transportId(@Nullable String value) {
                if (value == null) values.remove("transportId");
                else values.put("transportId", jsonValue(value));
                return this;
            }
            /**
             * Timestamp.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder timestamp(@Nullable Double value) {
                if (value == null) values.remove("timestamp");
                else values.put("timestamp", jsonValue(value));
                return this;
            }
            public WebTransportClosedEvent build() {
                if (!values.containsKey("transportId")) throw new IllegalStateException("Missing required CDP field: transportId");
                if (!values.containsKey("timestamp")) throw new IllegalStateException("Missing required CDP field: timestamp");
                return new WebTransportClosedEvent(values);
            }
        }
    }
    /**
     * Fired upon direct_socket.TCPSocket creation.
     * <p><b>Experimental:</b> this part of CDP may change without notice.
     */
    public static final class DirectTCPSocketCreatedEvent extends CdpObject {
        private DirectTCPSocketCreatedEvent(Map<String, Object> values) { super(values); }
        @Nullable public static DirectTCPSocketCreatedEvent fromMap(@Nullable Map<String, Object> values) {
            return values == null ? null : new DirectTCPSocketCreatedEvent(values);
        }
        public static Builder builder() { return new Builder(); }
        /**
         * Returns the identifier field.
         * @return the protocol field value
         */
        @Nullable public String identifier() {
            return (String) value("identifier");
        }
        /**
         * Returns the remoteAddr field.
         * @return the protocol field value
         */
        @Nullable public String remoteAddr() {
            return (String) value("remoteAddr");
        }
        /**
         * Unsigned int 16.
         * @return the protocol field value
         */
        @Nullable public Long remotePort() {
            return numberAsLong(value("remotePort"));
        }
        /**
         * Returns the options field.
         * @return the protocol field value
         */
        @Nullable public Network.DirectTCPSocketOptions options() {
            return Network.DirectTCPSocketOptions.fromMap(objectMap(value("options")));
        }
        /**
         * Returns the timestamp field.
         * @return the protocol field value
         */
        @Nullable public Double timestamp() {
            return numberAsDouble(value("timestamp"));
        }
        /**
         * Returns the initiator field.
         * @return the protocol field value
         */
        @Nullable public Network.Initiator initiator() {
            return Network.Initiator.fromMap(objectMap(value("initiator")));
        }
        public static final class Builder {
            private final Map<String, Object> values = new LinkedHashMap<>();
            /**
             * Sets the identifier field.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder identifier(@Nullable String value) {
                if (value == null) values.remove("identifier");
                else values.put("identifier", jsonValue(value));
                return this;
            }
            /**
             * Sets the remoteAddr field.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder remoteAddr(@Nullable String value) {
                if (value == null) values.remove("remoteAddr");
                else values.put("remoteAddr", jsonValue(value));
                return this;
            }
            /**
             * Unsigned int 16.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder remotePort(@Nullable Long value) {
                if (value == null) values.remove("remotePort");
                else values.put("remotePort", jsonValue(value));
                return this;
            }
            /**
             * Sets the options field.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder options(@Nullable Network.DirectTCPSocketOptions value) {
                if (value == null) values.remove("options");
                else values.put("options", jsonValue(value));
                return this;
            }
            /**
             * Sets the timestamp field.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder timestamp(@Nullable Double value) {
                if (value == null) values.remove("timestamp");
                else values.put("timestamp", jsonValue(value));
                return this;
            }
            /**
             * Sets the initiator field.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder initiator(@Nullable Network.Initiator value) {
                if (value == null) values.remove("initiator");
                else values.put("initiator", jsonValue(value));
                return this;
            }
            public DirectTCPSocketCreatedEvent build() {
                if (!values.containsKey("identifier")) throw new IllegalStateException("Missing required CDP field: identifier");
                if (!values.containsKey("remoteAddr")) throw new IllegalStateException("Missing required CDP field: remoteAddr");
                if (!values.containsKey("remotePort")) throw new IllegalStateException("Missing required CDP field: remotePort");
                if (!values.containsKey("options")) throw new IllegalStateException("Missing required CDP field: options");
                if (!values.containsKey("timestamp")) throw new IllegalStateException("Missing required CDP field: timestamp");
                return new DirectTCPSocketCreatedEvent(values);
            }
        }
    }
    /**
     * Fired when direct_socket.TCPSocket connection is opened.
     * <p><b>Experimental:</b> this part of CDP may change without notice.
     */
    public static final class DirectTCPSocketOpenedEvent extends CdpObject {
        private DirectTCPSocketOpenedEvent(Map<String, Object> values) { super(values); }
        @Nullable public static DirectTCPSocketOpenedEvent fromMap(@Nullable Map<String, Object> values) {
            return values == null ? null : new DirectTCPSocketOpenedEvent(values);
        }
        public static Builder builder() { return new Builder(); }
        /**
         * Returns the identifier field.
         * @return the protocol field value
         */
        @Nullable public String identifier() {
            return (String) value("identifier");
        }
        /**
         * Returns the remoteAddr field.
         * @return the protocol field value
         */
        @Nullable public String remoteAddr() {
            return (String) value("remoteAddr");
        }
        /**
         * Expected to be unsigned integer.
         * @return the protocol field value
         */
        @Nullable public Long remotePort() {
            return numberAsLong(value("remotePort"));
        }
        /**
         * Returns the timestamp field.
         * @return the protocol field value
         */
        @Nullable public Double timestamp() {
            return numberAsDouble(value("timestamp"));
        }
        /**
         * Returns the localAddr field.
         * @return the protocol field value
         */
        @Nullable public String localAddr() {
            return (String) value("localAddr");
        }
        /**
         * Expected to be unsigned integer.
         * @return the protocol field value
         */
        @Nullable public Long localPort() {
            return numberAsLong(value("localPort"));
        }
        public static final class Builder {
            private final Map<String, Object> values = new LinkedHashMap<>();
            /**
             * Sets the identifier field.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder identifier(@Nullable String value) {
                if (value == null) values.remove("identifier");
                else values.put("identifier", jsonValue(value));
                return this;
            }
            /**
             * Sets the remoteAddr field.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder remoteAddr(@Nullable String value) {
                if (value == null) values.remove("remoteAddr");
                else values.put("remoteAddr", jsonValue(value));
                return this;
            }
            /**
             * Expected to be unsigned integer.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder remotePort(@Nullable Long value) {
                if (value == null) values.remove("remotePort");
                else values.put("remotePort", jsonValue(value));
                return this;
            }
            /**
             * Sets the timestamp field.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder timestamp(@Nullable Double value) {
                if (value == null) values.remove("timestamp");
                else values.put("timestamp", jsonValue(value));
                return this;
            }
            /**
             * Sets the localAddr field.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder localAddr(@Nullable String value) {
                if (value == null) values.remove("localAddr");
                else values.put("localAddr", jsonValue(value));
                return this;
            }
            /**
             * Expected to be unsigned integer.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder localPort(@Nullable Long value) {
                if (value == null) values.remove("localPort");
                else values.put("localPort", jsonValue(value));
                return this;
            }
            public DirectTCPSocketOpenedEvent build() {
                if (!values.containsKey("identifier")) throw new IllegalStateException("Missing required CDP field: identifier");
                if (!values.containsKey("remoteAddr")) throw new IllegalStateException("Missing required CDP field: remoteAddr");
                if (!values.containsKey("remotePort")) throw new IllegalStateException("Missing required CDP field: remotePort");
                if (!values.containsKey("timestamp")) throw new IllegalStateException("Missing required CDP field: timestamp");
                return new DirectTCPSocketOpenedEvent(values);
            }
        }
    }
    /**
     * Fired when direct_socket.TCPSocket is aborted.
     * <p><b>Experimental:</b> this part of CDP may change without notice.
     */
    public static final class DirectTCPSocketAbortedEvent extends CdpObject {
        private DirectTCPSocketAbortedEvent(Map<String, Object> values) { super(values); }
        @Nullable public static DirectTCPSocketAbortedEvent fromMap(@Nullable Map<String, Object> values) {
            return values == null ? null : new DirectTCPSocketAbortedEvent(values);
        }
        public static Builder builder() { return new Builder(); }
        /**
         * Returns the identifier field.
         * @return the protocol field value
         */
        @Nullable public String identifier() {
            return (String) value("identifier");
        }
        /**
         * Returns the errorMessage field.
         * @return the protocol field value
         */
        @Nullable public String errorMessage() {
            return (String) value("errorMessage");
        }
        /**
         * Returns the timestamp field.
         * @return the protocol field value
         */
        @Nullable public Double timestamp() {
            return numberAsDouble(value("timestamp"));
        }
        public static final class Builder {
            private final Map<String, Object> values = new LinkedHashMap<>();
            /**
             * Sets the identifier field.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder identifier(@Nullable String value) {
                if (value == null) values.remove("identifier");
                else values.put("identifier", jsonValue(value));
                return this;
            }
            /**
             * Sets the errorMessage field.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder errorMessage(@Nullable String value) {
                if (value == null) values.remove("errorMessage");
                else values.put("errorMessage", jsonValue(value));
                return this;
            }
            /**
             * Sets the timestamp field.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder timestamp(@Nullable Double value) {
                if (value == null) values.remove("timestamp");
                else values.put("timestamp", jsonValue(value));
                return this;
            }
            public DirectTCPSocketAbortedEvent build() {
                if (!values.containsKey("identifier")) throw new IllegalStateException("Missing required CDP field: identifier");
                if (!values.containsKey("errorMessage")) throw new IllegalStateException("Missing required CDP field: errorMessage");
                if (!values.containsKey("timestamp")) throw new IllegalStateException("Missing required CDP field: timestamp");
                return new DirectTCPSocketAbortedEvent(values);
            }
        }
    }
    /**
     * Fired when direct_socket.TCPSocket is closed.
     * <p><b>Experimental:</b> this part of CDP may change without notice.
     */
    public static final class DirectTCPSocketClosedEvent extends CdpObject {
        private DirectTCPSocketClosedEvent(Map<String, Object> values) { super(values); }
        @Nullable public static DirectTCPSocketClosedEvent fromMap(@Nullable Map<String, Object> values) {
            return values == null ? null : new DirectTCPSocketClosedEvent(values);
        }
        public static Builder builder() { return new Builder(); }
        /**
         * Returns the identifier field.
         * @return the protocol field value
         */
        @Nullable public String identifier() {
            return (String) value("identifier");
        }
        /**
         * Returns the timestamp field.
         * @return the protocol field value
         */
        @Nullable public Double timestamp() {
            return numberAsDouble(value("timestamp"));
        }
        public static final class Builder {
            private final Map<String, Object> values = new LinkedHashMap<>();
            /**
             * Sets the identifier field.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder identifier(@Nullable String value) {
                if (value == null) values.remove("identifier");
                else values.put("identifier", jsonValue(value));
                return this;
            }
            /**
             * Sets the timestamp field.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder timestamp(@Nullable Double value) {
                if (value == null) values.remove("timestamp");
                else values.put("timestamp", jsonValue(value));
                return this;
            }
            public DirectTCPSocketClosedEvent build() {
                if (!values.containsKey("identifier")) throw new IllegalStateException("Missing required CDP field: identifier");
                if (!values.containsKey("timestamp")) throw new IllegalStateException("Missing required CDP field: timestamp");
                return new DirectTCPSocketClosedEvent(values);
            }
        }
    }
    /**
     * Fired when data is sent to tcp direct socket stream.
     * <p><b>Experimental:</b> this part of CDP may change without notice.
     */
    public static final class DirectTCPSocketChunkSentEvent extends CdpObject {
        private DirectTCPSocketChunkSentEvent(Map<String, Object> values) { super(values); }
        @Nullable public static DirectTCPSocketChunkSentEvent fromMap(@Nullable Map<String, Object> values) {
            return values == null ? null : new DirectTCPSocketChunkSentEvent(values);
        }
        public static Builder builder() { return new Builder(); }
        /**
         * Returns the identifier field.
         * @return the protocol field value
         */
        @Nullable public String identifier() {
            return (String) value("identifier");
        }
        /**
         * Returns the data field.
         * @return the protocol field value
         */
        @Nullable public String data() {
            return (String) value("data");
        }
        /**
         * Returns the timestamp field.
         * @return the protocol field value
         */
        @Nullable public Double timestamp() {
            return numberAsDouble(value("timestamp"));
        }
        public static final class Builder {
            private final Map<String, Object> values = new LinkedHashMap<>();
            /**
             * Sets the identifier field.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder identifier(@Nullable String value) {
                if (value == null) values.remove("identifier");
                else values.put("identifier", jsonValue(value));
                return this;
            }
            /**
             * Sets the data field.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder data(@Nullable String value) {
                if (value == null) values.remove("data");
                else values.put("data", jsonValue(value));
                return this;
            }
            /**
             * Sets the timestamp field.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder timestamp(@Nullable Double value) {
                if (value == null) values.remove("timestamp");
                else values.put("timestamp", jsonValue(value));
                return this;
            }
            public DirectTCPSocketChunkSentEvent build() {
                if (!values.containsKey("identifier")) throw new IllegalStateException("Missing required CDP field: identifier");
                if (!values.containsKey("data")) throw new IllegalStateException("Missing required CDP field: data");
                if (!values.containsKey("timestamp")) throw new IllegalStateException("Missing required CDP field: timestamp");
                return new DirectTCPSocketChunkSentEvent(values);
            }
        }
    }
    /**
     * Fired when data is received from tcp direct socket stream.
     * <p><b>Experimental:</b> this part of CDP may change without notice.
     */
    public static final class DirectTCPSocketChunkReceivedEvent extends CdpObject {
        private DirectTCPSocketChunkReceivedEvent(Map<String, Object> values) { super(values); }
        @Nullable public static DirectTCPSocketChunkReceivedEvent fromMap(@Nullable Map<String, Object> values) {
            return values == null ? null : new DirectTCPSocketChunkReceivedEvent(values);
        }
        public static Builder builder() { return new Builder(); }
        /**
         * Returns the identifier field.
         * @return the protocol field value
         */
        @Nullable public String identifier() {
            return (String) value("identifier");
        }
        /**
         * Returns the data field.
         * @return the protocol field value
         */
        @Nullable public String data() {
            return (String) value("data");
        }
        /**
         * Returns the timestamp field.
         * @return the protocol field value
         */
        @Nullable public Double timestamp() {
            return numberAsDouble(value("timestamp"));
        }
        public static final class Builder {
            private final Map<String, Object> values = new LinkedHashMap<>();
            /**
             * Sets the identifier field.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder identifier(@Nullable String value) {
                if (value == null) values.remove("identifier");
                else values.put("identifier", jsonValue(value));
                return this;
            }
            /**
             * Sets the data field.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder data(@Nullable String value) {
                if (value == null) values.remove("data");
                else values.put("data", jsonValue(value));
                return this;
            }
            /**
             * Sets the timestamp field.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder timestamp(@Nullable Double value) {
                if (value == null) values.remove("timestamp");
                else values.put("timestamp", jsonValue(value));
                return this;
            }
            public DirectTCPSocketChunkReceivedEvent build() {
                if (!values.containsKey("identifier")) throw new IllegalStateException("Missing required CDP field: identifier");
                if (!values.containsKey("data")) throw new IllegalStateException("Missing required CDP field: data");
                if (!values.containsKey("timestamp")) throw new IllegalStateException("Missing required CDP field: timestamp");
                return new DirectTCPSocketChunkReceivedEvent(values);
            }
        }
    }
    /**
     * Payload of the Network.directUDPSocketJoinedMulticastGroup event.
     * <p><b>Experimental:</b> this part of CDP may change without notice.
     */
    public static final class DirectUDPSocketJoinedMulticastGroupEvent extends CdpObject {
        private DirectUDPSocketJoinedMulticastGroupEvent(Map<String, Object> values) { super(values); }
        @Nullable public static DirectUDPSocketJoinedMulticastGroupEvent fromMap(@Nullable Map<String, Object> values) {
            return values == null ? null : new DirectUDPSocketJoinedMulticastGroupEvent(values);
        }
        public static Builder builder() { return new Builder(); }
        /**
         * Returns the identifier field.
         * @return the protocol field value
         */
        @Nullable public String identifier() {
            return (String) value("identifier");
        }
        /**
         * Returns the IPAddress field.
         * @return the protocol field value
         */
        @Nullable public String IPAddress() {
            return (String) value("IPAddress");
        }
        public static final class Builder {
            private final Map<String, Object> values = new LinkedHashMap<>();
            /**
             * Sets the identifier field.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder identifier(@Nullable String value) {
                if (value == null) values.remove("identifier");
                else values.put("identifier", jsonValue(value));
                return this;
            }
            /**
             * Sets the IPAddress field.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder IPAddress(@Nullable String value) {
                if (value == null) values.remove("IPAddress");
                else values.put("IPAddress", jsonValue(value));
                return this;
            }
            public DirectUDPSocketJoinedMulticastGroupEvent build() {
                if (!values.containsKey("identifier")) throw new IllegalStateException("Missing required CDP field: identifier");
                if (!values.containsKey("IPAddress")) throw new IllegalStateException("Missing required CDP field: IPAddress");
                return new DirectUDPSocketJoinedMulticastGroupEvent(values);
            }
        }
    }
    /**
     * Payload of the Network.directUDPSocketLeftMulticastGroup event.
     * <p><b>Experimental:</b> this part of CDP may change without notice.
     */
    public static final class DirectUDPSocketLeftMulticastGroupEvent extends CdpObject {
        private DirectUDPSocketLeftMulticastGroupEvent(Map<String, Object> values) { super(values); }
        @Nullable public static DirectUDPSocketLeftMulticastGroupEvent fromMap(@Nullable Map<String, Object> values) {
            return values == null ? null : new DirectUDPSocketLeftMulticastGroupEvent(values);
        }
        public static Builder builder() { return new Builder(); }
        /**
         * Returns the identifier field.
         * @return the protocol field value
         */
        @Nullable public String identifier() {
            return (String) value("identifier");
        }
        /**
         * Returns the IPAddress field.
         * @return the protocol field value
         */
        @Nullable public String IPAddress() {
            return (String) value("IPAddress");
        }
        public static final class Builder {
            private final Map<String, Object> values = new LinkedHashMap<>();
            /**
             * Sets the identifier field.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder identifier(@Nullable String value) {
                if (value == null) values.remove("identifier");
                else values.put("identifier", jsonValue(value));
                return this;
            }
            /**
             * Sets the IPAddress field.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder IPAddress(@Nullable String value) {
                if (value == null) values.remove("IPAddress");
                else values.put("IPAddress", jsonValue(value));
                return this;
            }
            public DirectUDPSocketLeftMulticastGroupEvent build() {
                if (!values.containsKey("identifier")) throw new IllegalStateException("Missing required CDP field: identifier");
                if (!values.containsKey("IPAddress")) throw new IllegalStateException("Missing required CDP field: IPAddress");
                return new DirectUDPSocketLeftMulticastGroupEvent(values);
            }
        }
    }
    /**
     * Fired upon direct_socket.UDPSocket creation.
     * <p><b>Experimental:</b> this part of CDP may change without notice.
     */
    public static final class DirectUDPSocketCreatedEvent extends CdpObject {
        private DirectUDPSocketCreatedEvent(Map<String, Object> values) { super(values); }
        @Nullable public static DirectUDPSocketCreatedEvent fromMap(@Nullable Map<String, Object> values) {
            return values == null ? null : new DirectUDPSocketCreatedEvent(values);
        }
        public static Builder builder() { return new Builder(); }
        /**
         * Returns the identifier field.
         * @return the protocol field value
         */
        @Nullable public String identifier() {
            return (String) value("identifier");
        }
        /**
         * Returns the options field.
         * @return the protocol field value
         */
        @Nullable public Network.DirectUDPSocketOptions options() {
            return Network.DirectUDPSocketOptions.fromMap(objectMap(value("options")));
        }
        /**
         * Returns the timestamp field.
         * @return the protocol field value
         */
        @Nullable public Double timestamp() {
            return numberAsDouble(value("timestamp"));
        }
        /**
         * Returns the initiator field.
         * @return the protocol field value
         */
        @Nullable public Network.Initiator initiator() {
            return Network.Initiator.fromMap(objectMap(value("initiator")));
        }
        public static final class Builder {
            private final Map<String, Object> values = new LinkedHashMap<>();
            /**
             * Sets the identifier field.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder identifier(@Nullable String value) {
                if (value == null) values.remove("identifier");
                else values.put("identifier", jsonValue(value));
                return this;
            }
            /**
             * Sets the options field.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder options(@Nullable Network.DirectUDPSocketOptions value) {
                if (value == null) values.remove("options");
                else values.put("options", jsonValue(value));
                return this;
            }
            /**
             * Sets the timestamp field.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder timestamp(@Nullable Double value) {
                if (value == null) values.remove("timestamp");
                else values.put("timestamp", jsonValue(value));
                return this;
            }
            /**
             * Sets the initiator field.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder initiator(@Nullable Network.Initiator value) {
                if (value == null) values.remove("initiator");
                else values.put("initiator", jsonValue(value));
                return this;
            }
            public DirectUDPSocketCreatedEvent build() {
                if (!values.containsKey("identifier")) throw new IllegalStateException("Missing required CDP field: identifier");
                if (!values.containsKey("options")) throw new IllegalStateException("Missing required CDP field: options");
                if (!values.containsKey("timestamp")) throw new IllegalStateException("Missing required CDP field: timestamp");
                return new DirectUDPSocketCreatedEvent(values);
            }
        }
    }
    /**
     * Fired when direct_socket.UDPSocket connection is opened.
     * <p><b>Experimental:</b> this part of CDP may change without notice.
     */
    public static final class DirectUDPSocketOpenedEvent extends CdpObject {
        private DirectUDPSocketOpenedEvent(Map<String, Object> values) { super(values); }
        @Nullable public static DirectUDPSocketOpenedEvent fromMap(@Nullable Map<String, Object> values) {
            return values == null ? null : new DirectUDPSocketOpenedEvent(values);
        }
        public static Builder builder() { return new Builder(); }
        /**
         * Returns the identifier field.
         * @return the protocol field value
         */
        @Nullable public String identifier() {
            return (String) value("identifier");
        }
        /**
         * Returns the localAddr field.
         * @return the protocol field value
         */
        @Nullable public String localAddr() {
            return (String) value("localAddr");
        }
        /**
         * Expected to be unsigned integer.
         * @return the protocol field value
         */
        @Nullable public Long localPort() {
            return numberAsLong(value("localPort"));
        }
        /**
         * Returns the timestamp field.
         * @return the protocol field value
         */
        @Nullable public Double timestamp() {
            return numberAsDouble(value("timestamp"));
        }
        /**
         * Returns the remoteAddr field.
         * @return the protocol field value
         */
        @Nullable public String remoteAddr() {
            return (String) value("remoteAddr");
        }
        /**
         * Expected to be unsigned integer.
         * @return the protocol field value
         */
        @Nullable public Long remotePort() {
            return numberAsLong(value("remotePort"));
        }
        public static final class Builder {
            private final Map<String, Object> values = new LinkedHashMap<>();
            /**
             * Sets the identifier field.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder identifier(@Nullable String value) {
                if (value == null) values.remove("identifier");
                else values.put("identifier", jsonValue(value));
                return this;
            }
            /**
             * Sets the localAddr field.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder localAddr(@Nullable String value) {
                if (value == null) values.remove("localAddr");
                else values.put("localAddr", jsonValue(value));
                return this;
            }
            /**
             * Expected to be unsigned integer.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder localPort(@Nullable Long value) {
                if (value == null) values.remove("localPort");
                else values.put("localPort", jsonValue(value));
                return this;
            }
            /**
             * Sets the timestamp field.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder timestamp(@Nullable Double value) {
                if (value == null) values.remove("timestamp");
                else values.put("timestamp", jsonValue(value));
                return this;
            }
            /**
             * Sets the remoteAddr field.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder remoteAddr(@Nullable String value) {
                if (value == null) values.remove("remoteAddr");
                else values.put("remoteAddr", jsonValue(value));
                return this;
            }
            /**
             * Expected to be unsigned integer.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder remotePort(@Nullable Long value) {
                if (value == null) values.remove("remotePort");
                else values.put("remotePort", jsonValue(value));
                return this;
            }
            public DirectUDPSocketOpenedEvent build() {
                if (!values.containsKey("identifier")) throw new IllegalStateException("Missing required CDP field: identifier");
                if (!values.containsKey("localAddr")) throw new IllegalStateException("Missing required CDP field: localAddr");
                if (!values.containsKey("localPort")) throw new IllegalStateException("Missing required CDP field: localPort");
                if (!values.containsKey("timestamp")) throw new IllegalStateException("Missing required CDP field: timestamp");
                return new DirectUDPSocketOpenedEvent(values);
            }
        }
    }
    /**
     * Fired when direct_socket.UDPSocket is aborted.
     * <p><b>Experimental:</b> this part of CDP may change without notice.
     */
    public static final class DirectUDPSocketAbortedEvent extends CdpObject {
        private DirectUDPSocketAbortedEvent(Map<String, Object> values) { super(values); }
        @Nullable public static DirectUDPSocketAbortedEvent fromMap(@Nullable Map<String, Object> values) {
            return values == null ? null : new DirectUDPSocketAbortedEvent(values);
        }
        public static Builder builder() { return new Builder(); }
        /**
         * Returns the identifier field.
         * @return the protocol field value
         */
        @Nullable public String identifier() {
            return (String) value("identifier");
        }
        /**
         * Returns the errorMessage field.
         * @return the protocol field value
         */
        @Nullable public String errorMessage() {
            return (String) value("errorMessage");
        }
        /**
         * Returns the timestamp field.
         * @return the protocol field value
         */
        @Nullable public Double timestamp() {
            return numberAsDouble(value("timestamp"));
        }
        public static final class Builder {
            private final Map<String, Object> values = new LinkedHashMap<>();
            /**
             * Sets the identifier field.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder identifier(@Nullable String value) {
                if (value == null) values.remove("identifier");
                else values.put("identifier", jsonValue(value));
                return this;
            }
            /**
             * Sets the errorMessage field.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder errorMessage(@Nullable String value) {
                if (value == null) values.remove("errorMessage");
                else values.put("errorMessage", jsonValue(value));
                return this;
            }
            /**
             * Sets the timestamp field.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder timestamp(@Nullable Double value) {
                if (value == null) values.remove("timestamp");
                else values.put("timestamp", jsonValue(value));
                return this;
            }
            public DirectUDPSocketAbortedEvent build() {
                if (!values.containsKey("identifier")) throw new IllegalStateException("Missing required CDP field: identifier");
                if (!values.containsKey("errorMessage")) throw new IllegalStateException("Missing required CDP field: errorMessage");
                if (!values.containsKey("timestamp")) throw new IllegalStateException("Missing required CDP field: timestamp");
                return new DirectUDPSocketAbortedEvent(values);
            }
        }
    }
    /**
     * Fired when direct_socket.UDPSocket is closed.
     * <p><b>Experimental:</b> this part of CDP may change without notice.
     */
    public static final class DirectUDPSocketClosedEvent extends CdpObject {
        private DirectUDPSocketClosedEvent(Map<String, Object> values) { super(values); }
        @Nullable public static DirectUDPSocketClosedEvent fromMap(@Nullable Map<String, Object> values) {
            return values == null ? null : new DirectUDPSocketClosedEvent(values);
        }
        public static Builder builder() { return new Builder(); }
        /**
         * Returns the identifier field.
         * @return the protocol field value
         */
        @Nullable public String identifier() {
            return (String) value("identifier");
        }
        /**
         * Returns the timestamp field.
         * @return the protocol field value
         */
        @Nullable public Double timestamp() {
            return numberAsDouble(value("timestamp"));
        }
        public static final class Builder {
            private final Map<String, Object> values = new LinkedHashMap<>();
            /**
             * Sets the identifier field.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder identifier(@Nullable String value) {
                if (value == null) values.remove("identifier");
                else values.put("identifier", jsonValue(value));
                return this;
            }
            /**
             * Sets the timestamp field.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder timestamp(@Nullable Double value) {
                if (value == null) values.remove("timestamp");
                else values.put("timestamp", jsonValue(value));
                return this;
            }
            public DirectUDPSocketClosedEvent build() {
                if (!values.containsKey("identifier")) throw new IllegalStateException("Missing required CDP field: identifier");
                if (!values.containsKey("timestamp")) throw new IllegalStateException("Missing required CDP field: timestamp");
                return new DirectUDPSocketClosedEvent(values);
            }
        }
    }
    /**
     * Fired when message is sent to udp direct socket stream.
     * <p><b>Experimental:</b> this part of CDP may change without notice.
     */
    public static final class DirectUDPSocketChunkSentEvent extends CdpObject {
        private DirectUDPSocketChunkSentEvent(Map<String, Object> values) { super(values); }
        @Nullable public static DirectUDPSocketChunkSentEvent fromMap(@Nullable Map<String, Object> values) {
            return values == null ? null : new DirectUDPSocketChunkSentEvent(values);
        }
        public static Builder builder() { return new Builder(); }
        /**
         * Returns the identifier field.
         * @return the protocol field value
         */
        @Nullable public String identifier() {
            return (String) value("identifier");
        }
        /**
         * Returns the message field.
         * @return the protocol field value
         */
        @Nullable public Network.DirectUDPMessage message() {
            return Network.DirectUDPMessage.fromMap(objectMap(value("message")));
        }
        /**
         * Returns the timestamp field.
         * @return the protocol field value
         */
        @Nullable public Double timestamp() {
            return numberAsDouble(value("timestamp"));
        }
        public static final class Builder {
            private final Map<String, Object> values = new LinkedHashMap<>();
            /**
             * Sets the identifier field.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder identifier(@Nullable String value) {
                if (value == null) values.remove("identifier");
                else values.put("identifier", jsonValue(value));
                return this;
            }
            /**
             * Sets the message field.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder message(@Nullable Network.DirectUDPMessage value) {
                if (value == null) values.remove("message");
                else values.put("message", jsonValue(value));
                return this;
            }
            /**
             * Sets the timestamp field.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder timestamp(@Nullable Double value) {
                if (value == null) values.remove("timestamp");
                else values.put("timestamp", jsonValue(value));
                return this;
            }
            public DirectUDPSocketChunkSentEvent build() {
                if (!values.containsKey("identifier")) throw new IllegalStateException("Missing required CDP field: identifier");
                if (!values.containsKey("message")) throw new IllegalStateException("Missing required CDP field: message");
                if (!values.containsKey("timestamp")) throw new IllegalStateException("Missing required CDP field: timestamp");
                return new DirectUDPSocketChunkSentEvent(values);
            }
        }
    }
    /**
     * Fired when message is received from udp direct socket stream.
     * <p><b>Experimental:</b> this part of CDP may change without notice.
     */
    public static final class DirectUDPSocketChunkReceivedEvent extends CdpObject {
        private DirectUDPSocketChunkReceivedEvent(Map<String, Object> values) { super(values); }
        @Nullable public static DirectUDPSocketChunkReceivedEvent fromMap(@Nullable Map<String, Object> values) {
            return values == null ? null : new DirectUDPSocketChunkReceivedEvent(values);
        }
        public static Builder builder() { return new Builder(); }
        /**
         * Returns the identifier field.
         * @return the protocol field value
         */
        @Nullable public String identifier() {
            return (String) value("identifier");
        }
        /**
         * Returns the message field.
         * @return the protocol field value
         */
        @Nullable public Network.DirectUDPMessage message() {
            return Network.DirectUDPMessage.fromMap(objectMap(value("message")));
        }
        /**
         * Returns the timestamp field.
         * @return the protocol field value
         */
        @Nullable public Double timestamp() {
            return numberAsDouble(value("timestamp"));
        }
        public static final class Builder {
            private final Map<String, Object> values = new LinkedHashMap<>();
            /**
             * Sets the identifier field.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder identifier(@Nullable String value) {
                if (value == null) values.remove("identifier");
                else values.put("identifier", jsonValue(value));
                return this;
            }
            /**
             * Sets the message field.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder message(@Nullable Network.DirectUDPMessage value) {
                if (value == null) values.remove("message");
                else values.put("message", jsonValue(value));
                return this;
            }
            /**
             * Sets the timestamp field.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder timestamp(@Nullable Double value) {
                if (value == null) values.remove("timestamp");
                else values.put("timestamp", jsonValue(value));
                return this;
            }
            public DirectUDPSocketChunkReceivedEvent build() {
                if (!values.containsKey("identifier")) throw new IllegalStateException("Missing required CDP field: identifier");
                if (!values.containsKey("message")) throw new IllegalStateException("Missing required CDP field: message");
                if (!values.containsKey("timestamp")) throw new IllegalStateException("Missing required CDP field: timestamp");
                return new DirectUDPSocketChunkReceivedEvent(values);
            }
        }
    }
    /**
     * Fired when additional information about a requestWillBeSent event is available from the network stack. Not every requestWillBeSent event will have an additional requestWillBeSentExtraInfo fired for it, and there is no guarantee whether requestWillBeSent or requestWillBeSentExtraInfo will be fired first for the same request.
     * <p><b>Experimental:</b> this part of CDP may change without notice.
     */
    public static final class RequestWillBeSentExtraInfoEvent extends CdpObject {
        private RequestWillBeSentExtraInfoEvent(Map<String, Object> values) { super(values); }
        @Nullable public static RequestWillBeSentExtraInfoEvent fromMap(@Nullable Map<String, Object> values) {
            return values == null ? null : new RequestWillBeSentExtraInfoEvent(values);
        }
        public static Builder builder() { return new Builder(); }
        /**
         * Request identifier. Used to match this information to an existing requestWillBeSent event.
         * @return the protocol field value
         */
        @Nullable public String requestId() {
            return (String) value("requestId");
        }
        /**
         * A list of cookies potentially associated to the requested URL. This includes both cookies sent with the request and the ones not sent; the latter are distinguished by having blockedReasons field set.
         * @return the protocol field value
         */
        @Nullable public java.util.List<Network.AssociatedCookie> associatedCookies() {
            return list(value("associatedCookies"), element0 -> Network.AssociatedCookie.fromMap(objectMap(element0)));
        }
        /**
         * Raw request headers as they will be sent over the wire.
         * @return the protocol field value
         */
        @Nullable public java.util.Map<String, Object> headers() {
            return objectMap(value("headers"));
        }
        /**
         * Connection timing information for the request.
         * <p><b>Experimental:</b> this part of CDP may change without notice.
         * @return the protocol field value
         */
        @Nullable public Network.ConnectTiming connectTiming() {
            return Network.ConnectTiming.fromMap(objectMap(value("connectTiming")));
        }
        /**
         * How the request site&#x27;s device bound sessions were used during this request.
         * @return the protocol field value
         */
        @Nullable public java.util.List<Network.DeviceBoundSessionWithUsage> deviceBoundSessionUsages() {
            return list(value("deviceBoundSessionUsages"), element0 -> Network.DeviceBoundSessionWithUsage.fromMap(objectMap(element0)));
        }
        /**
         * The client security state set for the request.
         * @return the protocol field value
         */
        @Nullable public Network.ClientSecurityState clientSecurityState() {
            return Network.ClientSecurityState.fromMap(objectMap(value("clientSecurityState")));
        }
        /**
         * Whether the site has partitioned cookies stored in a partition different than the current one.
         * @return the protocol field value
         */
        @Nullable public Boolean siteHasCookieInOtherPartition() {
            return (Boolean) value("siteHasCookieInOtherPartition");
        }
        /**
         * The network conditions id if this request was affected by network conditions configured via emulateNetworkConditionsByRule.
         * @return the protocol field value
         */
        @Nullable public String appliedNetworkConditionsId() {
            return (String) value("appliedNetworkConditionsId");
        }
        public static final class Builder {
            private final Map<String, Object> values = new LinkedHashMap<>();
            /**
             * Request identifier. Used to match this information to an existing requestWillBeSent event.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder requestId(@Nullable String value) {
                if (value == null) values.remove("requestId");
                else values.put("requestId", jsonValue(value));
                return this;
            }
            /**
             * A list of cookies potentially associated to the requested URL. This includes both cookies sent with the request and the ones not sent; the latter are distinguished by having blockedReasons field set.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder associatedCookies(@Nullable java.util.List<Network.AssociatedCookie> value) {
                if (value == null) values.remove("associatedCookies");
                else values.put("associatedCookies", jsonValue(value));
                return this;
            }
            /**
             * Raw request headers as they will be sent over the wire.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder headers(@Nullable java.util.Map<String, Object> value) {
                if (value == null) values.remove("headers");
                else values.put("headers", jsonValue(value));
                return this;
            }
            /**
             * Connection timing information for the request.
             * <p><b>Experimental:</b> this part of CDP may change without notice.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder connectTiming(@Nullable Network.ConnectTiming value) {
                if (value == null) values.remove("connectTiming");
                else values.put("connectTiming", jsonValue(value));
                return this;
            }
            /**
             * How the request site&#x27;s device bound sessions were used during this request.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder deviceBoundSessionUsages(@Nullable java.util.List<Network.DeviceBoundSessionWithUsage> value) {
                if (value == null) values.remove("deviceBoundSessionUsages");
                else values.put("deviceBoundSessionUsages", jsonValue(value));
                return this;
            }
            /**
             * The client security state set for the request.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder clientSecurityState(@Nullable Network.ClientSecurityState value) {
                if (value == null) values.remove("clientSecurityState");
                else values.put("clientSecurityState", jsonValue(value));
                return this;
            }
            /**
             * Whether the site has partitioned cookies stored in a partition different than the current one.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder siteHasCookieInOtherPartition(@Nullable Boolean value) {
                if (value == null) values.remove("siteHasCookieInOtherPartition");
                else values.put("siteHasCookieInOtherPartition", jsonValue(value));
                return this;
            }
            /**
             * The network conditions id if this request was affected by network conditions configured via emulateNetworkConditionsByRule.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder appliedNetworkConditionsId(@Nullable String value) {
                if (value == null) values.remove("appliedNetworkConditionsId");
                else values.put("appliedNetworkConditionsId", jsonValue(value));
                return this;
            }
            public RequestWillBeSentExtraInfoEvent build() {
                if (!values.containsKey("requestId")) throw new IllegalStateException("Missing required CDP field: requestId");
                if (!values.containsKey("associatedCookies")) throw new IllegalStateException("Missing required CDP field: associatedCookies");
                if (!values.containsKey("headers")) throw new IllegalStateException("Missing required CDP field: headers");
                if (!values.containsKey("connectTiming")) throw new IllegalStateException("Missing required CDP field: connectTiming");
                return new RequestWillBeSentExtraInfoEvent(values);
            }
        }
    }
    /**
     * Fired when additional information about a responseReceived event is available from the network stack. Not every responseReceived event will have an additional responseReceivedExtraInfo for it, and responseReceivedExtraInfo may be fired before or after responseReceived.
     * <p><b>Experimental:</b> this part of CDP may change without notice.
     */
    public static final class ResponseReceivedExtraInfoEvent extends CdpObject {
        private ResponseReceivedExtraInfoEvent(Map<String, Object> values) { super(values); }
        @Nullable public static ResponseReceivedExtraInfoEvent fromMap(@Nullable Map<String, Object> values) {
            return values == null ? null : new ResponseReceivedExtraInfoEvent(values);
        }
        public static Builder builder() { return new Builder(); }
        /**
         * Request identifier. Used to match this information to another responseReceived event.
         * @return the protocol field value
         */
        @Nullable public String requestId() {
            return (String) value("requestId");
        }
        /**
         * A list of cookies which were not stored from the response along with the corresponding reasons for blocking. The cookies here may not be valid due to syntax errors, which are represented by the invalid cookie line string instead of a proper cookie.
         * @return the protocol field value
         */
        @Nullable public java.util.List<Network.BlockedSetCookieWithReason> blockedCookies() {
            return list(value("blockedCookies"), element0 -> Network.BlockedSetCookieWithReason.fromMap(objectMap(element0)));
        }
        /**
         * Raw response headers as they were received over the wire. Duplicate headers in the response are represented as a single key with their values concatentated using {@code \n} as the separator. See also {@code headersText} that contains verbatim text for HTTP/1.*.
         * @return the protocol field value
         */
        @Nullable public java.util.Map<String, Object> headers() {
            return objectMap(value("headers"));
        }
        /**
         * The IP address space of the resource. The address space can only be determined once the transport established the connection, so we can&#x27;t send it in {@code requestWillBeSentExtraInfo}.
         * @return the protocol field value
         */
        @Nullable public String resourceIPAddressSpace() {
            return (String) value("resourceIPAddressSpace");
        }
        /**
         * The status code of the response. This is useful in cases the request failed and no responseReceived event is triggered, which is the case for, e.g., CORS errors. This is also the correct status code for cached requests, where the status in responseReceived is a 200 and this will be 304.
         * @return the protocol field value
         */
        @Nullable public Long statusCode() {
            return numberAsLong(value("statusCode"));
        }
        /**
         * Raw response header text as it was received over the wire. The raw text may not always be available, such as in the case of HTTP/2 or QUIC.
         * @return the protocol field value
         */
        @Nullable public String headersText() {
            return (String) value("headersText");
        }
        /**
         * The cookie partition key that will be used to store partitioned cookies set in this response. Only sent when partitioned cookies are enabled.
         * <p><b>Experimental:</b> this part of CDP may change without notice.
         * @return the protocol field value
         */
        @Nullable public Network.CookiePartitionKey cookiePartitionKey() {
            return Network.CookiePartitionKey.fromMap(objectMap(value("cookiePartitionKey")));
        }
        /**
         * True if partitioned cookies are enabled, but the partition key is not serializable to string.
         * @return the protocol field value
         */
        @Nullable public Boolean cookiePartitionKeyOpaque() {
            return (Boolean) value("cookiePartitionKeyOpaque");
        }
        /**
         * A list of cookies which should have been blocked by 3PCD but are exempted and stored from the response with the corresponding reason.
         * @return the protocol field value
         */
        @Nullable public java.util.List<Network.ExemptedSetCookieWithReason> exemptedCookies() {
            return list(value("exemptedCookies"), element0 -> Network.ExemptedSetCookieWithReason.fromMap(objectMap(element0)));
        }
        public static final class Builder {
            private final Map<String, Object> values = new LinkedHashMap<>();
            /**
             * Request identifier. Used to match this information to another responseReceived event.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder requestId(@Nullable String value) {
                if (value == null) values.remove("requestId");
                else values.put("requestId", jsonValue(value));
                return this;
            }
            /**
             * A list of cookies which were not stored from the response along with the corresponding reasons for blocking. The cookies here may not be valid due to syntax errors, which are represented by the invalid cookie line string instead of a proper cookie.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder blockedCookies(@Nullable java.util.List<Network.BlockedSetCookieWithReason> value) {
                if (value == null) values.remove("blockedCookies");
                else values.put("blockedCookies", jsonValue(value));
                return this;
            }
            /**
             * Raw response headers as they were received over the wire. Duplicate headers in the response are represented as a single key with their values concatentated using {@code \n} as the separator. See also {@code headersText} that contains verbatim text for HTTP/1.*.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder headers(@Nullable java.util.Map<String, Object> value) {
                if (value == null) values.remove("headers");
                else values.put("headers", jsonValue(value));
                return this;
            }
            /**
             * The IP address space of the resource. The address space can only be determined once the transport established the connection, so we can&#x27;t send it in {@code requestWillBeSentExtraInfo}.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder resourceIPAddressSpace(@Nullable String value) {
                if (value == null) values.remove("resourceIPAddressSpace");
                else values.put("resourceIPAddressSpace", jsonValue(value));
                return this;
            }
            /**
             * The status code of the response. This is useful in cases the request failed and no responseReceived event is triggered, which is the case for, e.g., CORS errors. This is also the correct status code for cached requests, where the status in responseReceived is a 200 and this will be 304.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder statusCode(@Nullable Long value) {
                if (value == null) values.remove("statusCode");
                else values.put("statusCode", jsonValue(value));
                return this;
            }
            /**
             * Raw response header text as it was received over the wire. The raw text may not always be available, such as in the case of HTTP/2 or QUIC.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder headersText(@Nullable String value) {
                if (value == null) values.remove("headersText");
                else values.put("headersText", jsonValue(value));
                return this;
            }
            /**
             * The cookie partition key that will be used to store partitioned cookies set in this response. Only sent when partitioned cookies are enabled.
             * <p><b>Experimental:</b> this part of CDP may change without notice.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder cookiePartitionKey(@Nullable Network.CookiePartitionKey value) {
                if (value == null) values.remove("cookiePartitionKey");
                else values.put("cookiePartitionKey", jsonValue(value));
                return this;
            }
            /**
             * True if partitioned cookies are enabled, but the partition key is not serializable to string.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder cookiePartitionKeyOpaque(@Nullable Boolean value) {
                if (value == null) values.remove("cookiePartitionKeyOpaque");
                else values.put("cookiePartitionKeyOpaque", jsonValue(value));
                return this;
            }
            /**
             * A list of cookies which should have been blocked by 3PCD but are exempted and stored from the response with the corresponding reason.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder exemptedCookies(@Nullable java.util.List<Network.ExemptedSetCookieWithReason> value) {
                if (value == null) values.remove("exemptedCookies");
                else values.put("exemptedCookies", jsonValue(value));
                return this;
            }
            public ResponseReceivedExtraInfoEvent build() {
                if (!values.containsKey("requestId")) throw new IllegalStateException("Missing required CDP field: requestId");
                if (!values.containsKey("blockedCookies")) throw new IllegalStateException("Missing required CDP field: blockedCookies");
                if (!values.containsKey("headers")) throw new IllegalStateException("Missing required CDP field: headers");
                if (!values.containsKey("resourceIPAddressSpace")) throw new IllegalStateException("Missing required CDP field: resourceIPAddressSpace");
                if (!values.containsKey("statusCode")) throw new IllegalStateException("Missing required CDP field: statusCode");
                return new ResponseReceivedExtraInfoEvent(values);
            }
        }
    }
    /**
     * Fired when 103 Early Hints headers is received in addition to the common response. Not every responseReceived event will have an responseReceivedEarlyHints fired. Only one responseReceivedEarlyHints may be fired for eached responseReceived event.
     * <p><b>Experimental:</b> this part of CDP may change without notice.
     */
    public static final class ResponseReceivedEarlyHintsEvent extends CdpObject {
        private ResponseReceivedEarlyHintsEvent(Map<String, Object> values) { super(values); }
        @Nullable public static ResponseReceivedEarlyHintsEvent fromMap(@Nullable Map<String, Object> values) {
            return values == null ? null : new ResponseReceivedEarlyHintsEvent(values);
        }
        public static Builder builder() { return new Builder(); }
        /**
         * Request identifier. Used to match this information to another responseReceived event.
         * @return the protocol field value
         */
        @Nullable public String requestId() {
            return (String) value("requestId");
        }
        /**
         * Raw response headers as they were received over the wire. Duplicate headers in the response are represented as a single key with their values concatentated using {@code \n} as the separator. See also {@code headersText} that contains verbatim text for HTTP/1.*.
         * @return the protocol field value
         */
        @Nullable public java.util.Map<String, Object> headers() {
            return objectMap(value("headers"));
        }
        public static final class Builder {
            private final Map<String, Object> values = new LinkedHashMap<>();
            /**
             * Request identifier. Used to match this information to another responseReceived event.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder requestId(@Nullable String value) {
                if (value == null) values.remove("requestId");
                else values.put("requestId", jsonValue(value));
                return this;
            }
            /**
             * Raw response headers as they were received over the wire. Duplicate headers in the response are represented as a single key with their values concatentated using {@code \n} as the separator. See also {@code headersText} that contains verbatim text for HTTP/1.*.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder headers(@Nullable java.util.Map<String, Object> value) {
                if (value == null) values.remove("headers");
                else values.put("headers", jsonValue(value));
                return this;
            }
            public ResponseReceivedEarlyHintsEvent build() {
                if (!values.containsKey("requestId")) throw new IllegalStateException("Missing required CDP field: requestId");
                if (!values.containsKey("headers")) throw new IllegalStateException("Missing required CDP field: headers");
                return new ResponseReceivedEarlyHintsEvent(values);
            }
        }
    }
    /**
     * Fired exactly once for each Trust Token operation. Depending on the type of the operation and whether the operation succeeded or failed, the event is fired before the corresponding request was sent or after the response was received.
     * <p><b>Experimental:</b> this part of CDP may change without notice.
     */
    public static final class TrustTokenOperationDoneEvent extends CdpObject {
        private TrustTokenOperationDoneEvent(Map<String, Object> values) { super(values); }
        @Nullable public static TrustTokenOperationDoneEvent fromMap(@Nullable Map<String, Object> values) {
            return values == null ? null : new TrustTokenOperationDoneEvent(values);
        }
        public static Builder builder() { return new Builder(); }
        /**
         * Detailed success or error status of the operation. &#x27;AlreadyExists&#x27; also signifies a successful operation, as the result of the operation already exists und thus, the operation was abort preemptively (e.g. a cache hit).
         * @return the protocol field value
         */
        @Nullable public String status() {
            return (String) value("status");
        }
        /**
         * Detailed success or error status of the operation. &#x27;AlreadyExists&#x27; also signifies a successful operation, as the result of the operation already exists und thus, the operation was abort preemptively (e.g. a cache hit).
         */
        public static final class StatusValues {
            private StatusValues() {}
            public static final String OK = "Ok";
            public static final String INVALIDARGUMENT = "InvalidArgument";
            public static final String MISSINGISSUERKEYS = "MissingIssuerKeys";
            public static final String FAILEDPRECONDITION = "FailedPrecondition";
            public static final String RESOURCEEXHAUSTED = "ResourceExhausted";
            public static final String ALREADYEXISTS = "AlreadyExists";
            public static final String RESOURCELIMITED = "ResourceLimited";
            public static final String UNAUTHORIZED = "Unauthorized";
            public static final String BADRESPONSE = "BadResponse";
            public static final String INTERNALERROR = "InternalError";
            public static final String UNKNOWNERROR = "UnknownError";
            public static final String FULFILLEDLOCALLY = "FulfilledLocally";
            public static final String SITEISSUERLIMIT = "SiteIssuerLimit";
        }
        /**
         * Returns the type field.
         * @return the protocol field value
         */
        @Nullable public String type() {
            return (String) value("type");
        }
        /**
         * Returns the requestId field.
         * @return the protocol field value
         */
        @Nullable public String requestId() {
            return (String) value("requestId");
        }
        /**
         * Top level origin. The context in which the operation was attempted.
         * @return the protocol field value
         */
        @Nullable public String topLevelOrigin() {
            return (String) value("topLevelOrigin");
        }
        /**
         * Origin of the issuer in case of a &quot;Issuance&quot; or &quot;Redemption&quot; operation.
         * @return the protocol field value
         */
        @Nullable public String issuerOrigin() {
            return (String) value("issuerOrigin");
        }
        /**
         * The number of obtained Trust Tokens on a successful &quot;Issuance&quot; operation.
         * @return the protocol field value
         */
        @Nullable public Long issuedTokenCount() {
            return numberAsLong(value("issuedTokenCount"));
        }
        public static final class Builder {
            private final Map<String, Object> values = new LinkedHashMap<>();
            /**
             * Detailed success or error status of the operation. &#x27;AlreadyExists&#x27; also signifies a successful operation, as the result of the operation already exists und thus, the operation was abort preemptively (e.g. a cache hit).
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder status(@Nullable String value) {
                if (value == null) values.remove("status");
                else values.put("status", jsonValue(value));
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
             * Top level origin. The context in which the operation was attempted.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder topLevelOrigin(@Nullable String value) {
                if (value == null) values.remove("topLevelOrigin");
                else values.put("topLevelOrigin", jsonValue(value));
                return this;
            }
            /**
             * Origin of the issuer in case of a &quot;Issuance&quot; or &quot;Redemption&quot; operation.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder issuerOrigin(@Nullable String value) {
                if (value == null) values.remove("issuerOrigin");
                else values.put("issuerOrigin", jsonValue(value));
                return this;
            }
            /**
             * The number of obtained Trust Tokens on a successful &quot;Issuance&quot; operation.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder issuedTokenCount(@Nullable Long value) {
                if (value == null) values.remove("issuedTokenCount");
                else values.put("issuedTokenCount", jsonValue(value));
                return this;
            }
            public TrustTokenOperationDoneEvent build() {
                if (!values.containsKey("status")) throw new IllegalStateException("Missing required CDP field: status");
                if (!values.containsKey("type")) throw new IllegalStateException("Missing required CDP field: type");
                if (!values.containsKey("requestId")) throw new IllegalStateException("Missing required CDP field: requestId");
                return new TrustTokenOperationDoneEvent(values);
            }
        }
    }
    /**
     * Fired once security policy has been updated.
     * <p><b>Experimental:</b> this part of CDP may change without notice.
     */
    public static final class PolicyUpdatedEvent extends CdpObject {
        private PolicyUpdatedEvent(Map<String, Object> values) { super(values); }
        @Nullable public static PolicyUpdatedEvent fromMap(@Nullable Map<String, Object> values) {
            return values == null ? null : new PolicyUpdatedEvent(values);
        }
        public static Builder builder() { return new Builder(); }
        public static final class Builder {
            private final Map<String, Object> values = new LinkedHashMap<>();
            public PolicyUpdatedEvent build() {
                return new PolicyUpdatedEvent(values);
            }
        }
    }
    /**
     * Is sent whenever a new report is added. And after &#x27;enableReportingApi&#x27; for all existing reports.
     * <p><b>Experimental:</b> this part of CDP may change without notice.
     */
    public static final class ReportingApiReportAddedEvent extends CdpObject {
        private ReportingApiReportAddedEvent(Map<String, Object> values) { super(values); }
        @Nullable public static ReportingApiReportAddedEvent fromMap(@Nullable Map<String, Object> values) {
            return values == null ? null : new ReportingApiReportAddedEvent(values);
        }
        public static Builder builder() { return new Builder(); }
        /**
         * Returns the report field.
         * @return the protocol field value
         */
        @Nullable public Network.ReportingApiReport report() {
            return Network.ReportingApiReport.fromMap(objectMap(value("report")));
        }
        public static final class Builder {
            private final Map<String, Object> values = new LinkedHashMap<>();
            /**
             * Sets the report field.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder report(@Nullable Network.ReportingApiReport value) {
                if (value == null) values.remove("report");
                else values.put("report", jsonValue(value));
                return this;
            }
            public ReportingApiReportAddedEvent build() {
                if (!values.containsKey("report")) throw new IllegalStateException("Missing required CDP field: report");
                return new ReportingApiReportAddedEvent(values);
            }
        }
    }
    /**
     * Payload of the Network.reportingApiReportUpdated event.
     * <p><b>Experimental:</b> this part of CDP may change without notice.
     */
    public static final class ReportingApiReportUpdatedEvent extends CdpObject {
        private ReportingApiReportUpdatedEvent(Map<String, Object> values) { super(values); }
        @Nullable public static ReportingApiReportUpdatedEvent fromMap(@Nullable Map<String, Object> values) {
            return values == null ? null : new ReportingApiReportUpdatedEvent(values);
        }
        public static Builder builder() { return new Builder(); }
        /**
         * Returns the report field.
         * @return the protocol field value
         */
        @Nullable public Network.ReportingApiReport report() {
            return Network.ReportingApiReport.fromMap(objectMap(value("report")));
        }
        public static final class Builder {
            private final Map<String, Object> values = new LinkedHashMap<>();
            /**
             * Sets the report field.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder report(@Nullable Network.ReportingApiReport value) {
                if (value == null) values.remove("report");
                else values.put("report", jsonValue(value));
                return this;
            }
            public ReportingApiReportUpdatedEvent build() {
                if (!values.containsKey("report")) throw new IllegalStateException("Missing required CDP field: report");
                return new ReportingApiReportUpdatedEvent(values);
            }
        }
    }
    /**
     * Payload of the Network.reportingApiEndpointsChangedForOrigin event.
     * <p><b>Experimental:</b> this part of CDP may change without notice.
     */
    public static final class ReportingApiEndpointsChangedForOriginEvent extends CdpObject {
        private ReportingApiEndpointsChangedForOriginEvent(Map<String, Object> values) { super(values); }
        @Nullable public static ReportingApiEndpointsChangedForOriginEvent fromMap(@Nullable Map<String, Object> values) {
            return values == null ? null : new ReportingApiEndpointsChangedForOriginEvent(values);
        }
        public static Builder builder() { return new Builder(); }
        /**
         * Origin of the document(s) which configured the endpoints.
         * @return the protocol field value
         */
        @Nullable public String origin() {
            return (String) value("origin");
        }
        /**
         * Returns the endpoints field.
         * @return the protocol field value
         */
        @Nullable public java.util.List<Network.ReportingApiEndpoint> endpoints() {
            return list(value("endpoints"), element0 -> Network.ReportingApiEndpoint.fromMap(objectMap(element0)));
        }
        public static final class Builder {
            private final Map<String, Object> values = new LinkedHashMap<>();
            /**
             * Origin of the document(s) which configured the endpoints.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder origin(@Nullable String value) {
                if (value == null) values.remove("origin");
                else values.put("origin", jsonValue(value));
                return this;
            }
            /**
             * Sets the endpoints field.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder endpoints(@Nullable java.util.List<Network.ReportingApiEndpoint> value) {
                if (value == null) values.remove("endpoints");
                else values.put("endpoints", jsonValue(value));
                return this;
            }
            public ReportingApiEndpointsChangedForOriginEvent build() {
                if (!values.containsKey("origin")) throw new IllegalStateException("Missing required CDP field: origin");
                if (!values.containsKey("endpoints")) throw new IllegalStateException("Missing required CDP field: endpoints");
                return new ReportingApiEndpointsChangedForOriginEvent(values);
            }
        }
    }
    /**
     * Triggered when the initial set of device bound sessions is added.
     * <p><b>Experimental:</b> this part of CDP may change without notice.
     */
    public static final class DeviceBoundSessionsAddedEvent extends CdpObject {
        private DeviceBoundSessionsAddedEvent(Map<String, Object> values) { super(values); }
        @Nullable public static DeviceBoundSessionsAddedEvent fromMap(@Nullable Map<String, Object> values) {
            return values == null ? null : new DeviceBoundSessionsAddedEvent(values);
        }
        public static Builder builder() { return new Builder(); }
        /**
         * The device bound sessions.
         * @return the protocol field value
         */
        @Nullable public java.util.List<Network.DeviceBoundSession> sessions() {
            return list(value("sessions"), element0 -> Network.DeviceBoundSession.fromMap(objectMap(element0)));
        }
        public static final class Builder {
            private final Map<String, Object> values = new LinkedHashMap<>();
            /**
             * The device bound sessions.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder sessions(@Nullable java.util.List<Network.DeviceBoundSession> value) {
                if (value == null) values.remove("sessions");
                else values.put("sessions", jsonValue(value));
                return this;
            }
            public DeviceBoundSessionsAddedEvent build() {
                if (!values.containsKey("sessions")) throw new IllegalStateException("Missing required CDP field: sessions");
                return new DeviceBoundSessionsAddedEvent(values);
            }
        }
    }
    /**
     * Triggered when a device bound session event occurs.
     * <p><b>Experimental:</b> this part of CDP may change without notice.
     */
    public static final class DeviceBoundSessionEventOccurredEvent extends CdpObject {
        private DeviceBoundSessionEventOccurredEvent(Map<String, Object> values) { super(values); }
        @Nullable public static DeviceBoundSessionEventOccurredEvent fromMap(@Nullable Map<String, Object> values) {
            return values == null ? null : new DeviceBoundSessionEventOccurredEvent(values);
        }
        public static Builder builder() { return new Builder(); }
        /**
         * A unique identifier for this session event.
         * @return the protocol field value
         */
        @Nullable public String eventId() {
            return (String) value("eventId");
        }
        /**
         * The site this session event is associated with.
         * @return the protocol field value
         */
        @Nullable public String site() {
            return (String) value("site");
        }
        /**
         * Whether this event was considered successful.
         * @return the protocol field value
         */
        @Nullable public Boolean succeeded() {
            return (Boolean) value("succeeded");
        }
        /**
         * The session ID this event is associated with. May not be populated for failed events.
         * @return the protocol field value
         */
        @Nullable public String sessionId() {
            return (String) value("sessionId");
        }
        /**
         * The below are the different session event type details. Exactly one is populated.
         * @return the protocol field value
         */
        @Nullable public Network.CreationEventDetails creationEventDetails() {
            return Network.CreationEventDetails.fromMap(objectMap(value("creationEventDetails")));
        }
        /**
         * Returns the refreshEventDetails field.
         * @return the protocol field value
         */
        @Nullable public Network.RefreshEventDetails refreshEventDetails() {
            return Network.RefreshEventDetails.fromMap(objectMap(value("refreshEventDetails")));
        }
        /**
         * Returns the terminationEventDetails field.
         * @return the protocol field value
         */
        @Nullable public Network.TerminationEventDetails terminationEventDetails() {
            return Network.TerminationEventDetails.fromMap(objectMap(value("terminationEventDetails")));
        }
        /**
         * Returns the challengeEventDetails field.
         * @return the protocol field value
         */
        @Nullable public Network.ChallengeEventDetails challengeEventDetails() {
            return Network.ChallengeEventDetails.fromMap(objectMap(value("challengeEventDetails")));
        }
        public static final class Builder {
            private final Map<String, Object> values = new LinkedHashMap<>();
            /**
             * A unique identifier for this session event.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder eventId(@Nullable String value) {
                if (value == null) values.remove("eventId");
                else values.put("eventId", jsonValue(value));
                return this;
            }
            /**
             * The site this session event is associated with.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder site(@Nullable String value) {
                if (value == null) values.remove("site");
                else values.put("site", jsonValue(value));
                return this;
            }
            /**
             * Whether this event was considered successful.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder succeeded(@Nullable Boolean value) {
                if (value == null) values.remove("succeeded");
                else values.put("succeeded", jsonValue(value));
                return this;
            }
            /**
             * The session ID this event is associated with. May not be populated for failed events.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder sessionId(@Nullable String value) {
                if (value == null) values.remove("sessionId");
                else values.put("sessionId", jsonValue(value));
                return this;
            }
            /**
             * The below are the different session event type details. Exactly one is populated.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder creationEventDetails(@Nullable Network.CreationEventDetails value) {
                if (value == null) values.remove("creationEventDetails");
                else values.put("creationEventDetails", jsonValue(value));
                return this;
            }
            /**
             * Sets the refreshEventDetails field.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder refreshEventDetails(@Nullable Network.RefreshEventDetails value) {
                if (value == null) values.remove("refreshEventDetails");
                else values.put("refreshEventDetails", jsonValue(value));
                return this;
            }
            /**
             * Sets the terminationEventDetails field.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder terminationEventDetails(@Nullable Network.TerminationEventDetails value) {
                if (value == null) values.remove("terminationEventDetails");
                else values.put("terminationEventDetails", jsonValue(value));
                return this;
            }
            /**
             * Sets the challengeEventDetails field.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder challengeEventDetails(@Nullable Network.ChallengeEventDetails value) {
                if (value == null) values.remove("challengeEventDetails");
                else values.put("challengeEventDetails", jsonValue(value));
                return this;
            }
            public DeviceBoundSessionEventOccurredEvent build() {
                if (!values.containsKey("eventId")) throw new IllegalStateException("Missing required CDP field: eventId");
                if (!values.containsKey("site")) throw new IllegalStateException("Missing required CDP field: site");
                if (!values.containsKey("succeeded")) throw new IllegalStateException("Missing required CDP field: succeeded");
                return new DeviceBoundSessionEventOccurredEvent(values);
            }
        }
    }
    public static final class Client {
        private final CdpClient client;
        public Client(CdpClient client) { this.client = client; }
        /**
         * Sets a list of content encodings that will be accepted. Empty list means no encoding is accepted.
         * <p><b>Experimental:</b> this part of CDP may change without notice.
         * @param params command parameters
         * @return a stage completing with the command result
         */
        public CompletionStage<SetAcceptedEncodingsResult> setAcceptedEncodings(SetAcceptedEncodingsParams params) {
            return client.call("Network.setAcceptedEncodings", params, SetAcceptedEncodingsResult::fromMap);
        }
        /**
         * Clears accepted encodings set by setAcceptedEncodings
         * <p><b>Experimental:</b> this part of CDP may change without notice.
         * @return a stage completing with the command result
         */
        public CompletionStage<ClearAcceptedEncodingsOverrideResult> clearAcceptedEncodingsOverride() {
            return client.call("Network.clearAcceptedEncodingsOverride", null, ClearAcceptedEncodingsOverrideResult::fromMap);
        }
        /**
         * Tells whether clearing browser cache is supported.
         * @return a stage completing with the command result
         * @deprecated Deprecated by the Chromium DevTools Protocol.
         */
        @Deprecated
        public CompletionStage<CanClearBrowserCacheResult> canClearBrowserCache() {
            return client.call("Network.canClearBrowserCache", null, CanClearBrowserCacheResult::fromMap);
        }
        /**
         * Tells whether clearing browser cookies is supported.
         * @return a stage completing with the command result
         * @deprecated Deprecated by the Chromium DevTools Protocol.
         */
        @Deprecated
        public CompletionStage<CanClearBrowserCookiesResult> canClearBrowserCookies() {
            return client.call("Network.canClearBrowserCookies", null, CanClearBrowserCookiesResult::fromMap);
        }
        /**
         * Tells whether emulation of network conditions is supported.
         * @return a stage completing with the command result
         * @deprecated Deprecated by the Chromium DevTools Protocol.
         */
        @Deprecated
        public CompletionStage<CanEmulateNetworkConditionsResult> canEmulateNetworkConditions() {
            return client.call("Network.canEmulateNetworkConditions", null, CanEmulateNetworkConditionsResult::fromMap);
        }
        /**
         * Clears browser cache.
         * @return a stage completing with the command result
         */
        public CompletionStage<ClearBrowserCacheResult> clearBrowserCache() {
            return client.call("Network.clearBrowserCache", null, ClearBrowserCacheResult::fromMap);
        }
        /**
         * Clears browser cookies.
         * @return a stage completing with the command result
         */
        public CompletionStage<ClearBrowserCookiesResult> clearBrowserCookies() {
            return client.call("Network.clearBrowserCookies", null, ClearBrowserCookiesResult::fromMap);
        }
        /**
         * Response to Network.requestIntercepted which either modifies the request to continue with any modifications, or blocks it, or completes it with the provided response bytes. If a network fetch occurs as a result which encounters a redirect an additional Network.requestIntercepted event will be sent with the same InterceptionId. Deprecated, use Fetch.continueRequest, Fetch.fulfillRequest and Fetch.failRequest instead.
         * <p><b>Experimental:</b> this part of CDP may change without notice.
         * @param params command parameters
         * @return a stage completing with the command result
         * @deprecated Deprecated by the Chromium DevTools Protocol.
         */
        @Deprecated
        public CompletionStage<ContinueInterceptedRequestResult> continueInterceptedRequest(ContinueInterceptedRequestParams params) {
            return client.call("Network.continueInterceptedRequest", params, ContinueInterceptedRequestResult::fromMap);
        }
        /**
         * Deletes browser cookies with matching name and url or domain/path/partitionKey pair.
         * @param params command parameters
         * @return a stage completing with the command result
         */
        public CompletionStage<DeleteCookiesResult> deleteCookies(DeleteCookiesParams params) {
            return client.call("Network.deleteCookies", params, DeleteCookiesResult::fromMap);
        }
        /**
         * Disables network tracking, prevents network events from being sent to the client.
         * @return a stage completing with the command result
         */
        public CompletionStage<DisableResult> disable() {
            return client.call("Network.disable", null, DisableResult::fromMap);
        }
        /**
         * Activates emulation of network conditions. This command is deprecated in favor of the emulateNetworkConditionsByRule and overrideNetworkState commands, which can be used together to the same effect.
         * @param params command parameters
         * @return a stage completing with the command result
         * @deprecated Deprecated by the Chromium DevTools Protocol.
         */
        @Deprecated
        public CompletionStage<EmulateNetworkConditionsResult> emulateNetworkConditions(EmulateNetworkConditionsParams params) {
            return client.call("Network.emulateNetworkConditions", params, EmulateNetworkConditionsResult::fromMap);
        }
        /**
         * Activates emulation of network conditions for individual requests using URL match patterns. Unlike the deprecated Network.emulateNetworkConditions this method does not affect {@code navigator} state. Use Network.overrideNetworkState to explicitly modify {@code navigator} behavior.
         * <p><b>Experimental:</b> this part of CDP may change without notice.
         * @param params command parameters
         * @return a stage completing with the command result
         */
        public CompletionStage<EmulateNetworkConditionsByRuleResult> emulateNetworkConditionsByRule(EmulateNetworkConditionsByRuleParams params) {
            return client.call("Network.emulateNetworkConditionsByRule", params, EmulateNetworkConditionsByRuleResult::fromMap);
        }
        /**
         * Override the state of navigator.onLine and navigator.connection.
         * <p><b>Experimental:</b> this part of CDP may change without notice.
         * @param params command parameters
         * @return a stage completing with the command result
         */
        public CompletionStage<OverrideNetworkStateResult> overrideNetworkState(OverrideNetworkStateParams params) {
            return client.call("Network.overrideNetworkState", params, OverrideNetworkStateResult::fromMap);
        }
        /**
         * Enables network tracking, network events will now be delivered to the client.
         * @param params command parameters
         * @return a stage completing with the command result
         */
        public CompletionStage<EnableResult> enable(EnableParams params) {
            return client.call("Network.enable", params, EnableResult::fromMap);
        }
        /**
         * Enables network tracking, network events will now be delivered to the client.
         * @return a stage completing with the command result
         */
        public CompletionStage<EnableResult> enable() {
            return enable(EnableParams.builder().build());
        }
        /**
         * Configures storing response bodies outside of renderer, so that these survive a cross-process navigation. If maxTotalBufferSize is not set, durable messages are disabled.
         * <p><b>Experimental:</b> this part of CDP may change without notice.
         * @param params command parameters
         * @return a stage completing with the command result
         */
        public CompletionStage<ConfigureDurableMessagesResult> configureDurableMessages(ConfigureDurableMessagesParams params) {
            return client.call("Network.configureDurableMessages", params, ConfigureDurableMessagesResult::fromMap);
        }
        /**
         * Configures storing response bodies outside of renderer, so that these survive a cross-process navigation. If maxTotalBufferSize is not set, durable messages are disabled.
         * <p><b>Experimental:</b> this part of CDP may change without notice.
         * @return a stage completing with the command result
         */
        public CompletionStage<ConfigureDurableMessagesResult> configureDurableMessages() {
            return configureDurableMessages(ConfigureDurableMessagesParams.builder().build());
        }
        /**
         * Returns all browser cookies. Depending on the backend support, will return detailed cookie information in the {@code cookies} field. Deprecated. Use Storage.getCookies instead.
         * @return a stage completing with the command result
         * @deprecated Deprecated by the Chromium DevTools Protocol.
         */
        @Deprecated
        public CompletionStage<GetAllCookiesResult> getAllCookies() {
            return client.call("Network.getAllCookies", null, GetAllCookiesResult::fromMap);
        }
        /**
         * Returns the DER-encoded certificate.
         * <p><b>Experimental:</b> this part of CDP may change without notice.
         * @param params command parameters
         * @return a stage completing with the command result
         */
        public CompletionStage<GetCertificateResult> getCertificate(GetCertificateParams params) {
            return client.call("Network.getCertificate", params, GetCertificateResult::fromMap);
        }
        /**
         * Returns all browser cookies for the current URL. Depending on the backend support, will return detailed cookie information in the {@code cookies} field.
         * @param params command parameters
         * @return a stage completing with the command result
         */
        public CompletionStage<GetCookiesResult> getCookies(GetCookiesParams params) {
            return client.call("Network.getCookies", params, GetCookiesResult::fromMap);
        }
        /**
         * Returns all browser cookies for the current URL. Depending on the backend support, will return detailed cookie information in the {@code cookies} field.
         * @return a stage completing with the command result
         */
        public CompletionStage<GetCookiesResult> getCookies() {
            return getCookies(GetCookiesParams.builder().build());
        }
        /**
         * Returns content served for the given request.
         * @param params command parameters
         * @return a stage completing with the command result
         */
        public CompletionStage<GetResponseBodyResult> getResponseBody(GetResponseBodyParams params) {
            return client.call("Network.getResponseBody", params, GetResponseBodyResult::fromMap);
        }
        /**
         * Returns post data sent with the request. Returns an error when no data was sent with the request.
         * @param params command parameters
         * @return a stage completing with the command result
         */
        public CompletionStage<GetRequestPostDataResult> getRequestPostData(GetRequestPostDataParams params) {
            return client.call("Network.getRequestPostData", params, GetRequestPostDataResult::fromMap);
        }
        /**
         * Returns content served for the given currently intercepted request.
         * <p><b>Experimental:</b> this part of CDP may change without notice.
         * @param params command parameters
         * @return a stage completing with the command result
         */
        public CompletionStage<GetResponseBodyForInterceptionResult> getResponseBodyForInterception(GetResponseBodyForInterceptionParams params) {
            return client.call("Network.getResponseBodyForInterception", params, GetResponseBodyForInterceptionResult::fromMap);
        }
        /**
         * Returns a handle to the stream representing the response body. Note that after this command, the intercepted request can&#x27;t be continued as is -- you either need to cancel it or to provide the response body. The stream only supports sequential read, IO.read will fail if the position is specified.
         * <p><b>Experimental:</b> this part of CDP may change without notice.
         * @param params command parameters
         * @return a stage completing with the command result
         */
        public CompletionStage<TakeResponseBodyForInterceptionAsStreamResult> takeResponseBodyForInterceptionAsStream(TakeResponseBodyForInterceptionAsStreamParams params) {
            return client.call("Network.takeResponseBodyForInterceptionAsStream", params, TakeResponseBodyForInterceptionAsStreamResult::fromMap);
        }
        /**
         * This method sends a new XMLHttpRequest which is identical to the original one. The following parameters should be identical: method, url, async, request body, extra headers, withCredentials attribute, user, password.
         * <p><b>Experimental:</b> this part of CDP may change without notice.
         * @param params command parameters
         * @return a stage completing with the command result
         */
        public CompletionStage<ReplayXHRResult> replayXHR(ReplayXHRParams params) {
            return client.call("Network.replayXHR", params, ReplayXHRResult::fromMap);
        }
        /**
         * Searches for given string in response content.
         * <p><b>Experimental:</b> this part of CDP may change without notice.
         * @param params command parameters
         * @return a stage completing with the command result
         */
        public CompletionStage<SearchInResponseBodyResult> searchInResponseBody(SearchInResponseBodyParams params) {
            return client.call("Network.searchInResponseBody", params, SearchInResponseBodyResult::fromMap);
        }
        /**
         * Blocks URLs from loading.
         * <p><b>Experimental:</b> this part of CDP may change without notice.
         * @param params command parameters
         * @return a stage completing with the command result
         */
        public CompletionStage<SetBlockedURLsResult> setBlockedURLs(SetBlockedURLsParams params) {
            return client.call("Network.setBlockedURLs", params, SetBlockedURLsResult::fromMap);
        }
        /**
         * Blocks URLs from loading.
         * <p><b>Experimental:</b> this part of CDP may change without notice.
         * @return a stage completing with the command result
         */
        public CompletionStage<SetBlockedURLsResult> setBlockedURLs() {
            return setBlockedURLs(SetBlockedURLsParams.builder().build());
        }
        /**
         * Toggles ignoring of service worker for each request.
         * @param params command parameters
         * @return a stage completing with the command result
         */
        public CompletionStage<SetBypassServiceWorkerResult> setBypassServiceWorker(SetBypassServiceWorkerParams params) {
            return client.call("Network.setBypassServiceWorker", params, SetBypassServiceWorkerResult::fromMap);
        }
        /**
         * Toggles ignoring cache for each request. If {@code true}, cache will not be used.
         * @param params command parameters
         * @return a stage completing with the command result
         */
        public CompletionStage<SetCacheDisabledResult> setCacheDisabled(SetCacheDisabledParams params) {
            return client.call("Network.setCacheDisabled", params, SetCacheDisabledResult::fromMap);
        }
        /**
         * Sets a cookie with the given cookie data; may overwrite equivalent cookies if they exist.
         * @param params command parameters
         * @return a stage completing with the command result
         */
        public CompletionStage<SetCookieResult> setCookie(SetCookieParams params) {
            return client.call("Network.setCookie", params, SetCookieResult::fromMap);
        }
        /**
         * Sets given cookies.
         * @param params command parameters
         * @return a stage completing with the command result
         */
        public CompletionStage<SetCookiesResult> setCookies(SetCookiesParams params) {
            return client.call("Network.setCookies", params, SetCookiesResult::fromMap);
        }
        /**
         * Specifies whether to always send extra HTTP headers with the requests from this page.
         * @param params command parameters
         * @return a stage completing with the command result
         */
        public CompletionStage<SetExtraHTTPHeadersResult> setExtraHTTPHeaders(SetExtraHTTPHeadersParams params) {
            return client.call("Network.setExtraHTTPHeaders", params, SetExtraHTTPHeadersResult::fromMap);
        }
        /**
         * Specifies whether to attach a page script stack id in requests
         * <p><b>Experimental:</b> this part of CDP may change without notice.
         * @param params command parameters
         * @return a stage completing with the command result
         */
        public CompletionStage<SetAttachDebugStackResult> setAttachDebugStack(SetAttachDebugStackParams params) {
            return client.call("Network.setAttachDebugStack", params, SetAttachDebugStackResult::fromMap);
        }
        /**
         * Sets the requests to intercept that match the provided patterns and optionally resource types. Deprecated, please use Fetch.enable instead.
         * <p><b>Experimental:</b> this part of CDP may change without notice.
         * @param params command parameters
         * @return a stage completing with the command result
         * @deprecated Deprecated by the Chromium DevTools Protocol.
         */
        @Deprecated
        public CompletionStage<SetRequestInterceptionResult> setRequestInterception(SetRequestInterceptionParams params) {
            return client.call("Network.setRequestInterception", params, SetRequestInterceptionResult::fromMap);
        }
        /**
         * Allows overriding user agent with the given string.
         * @param params command parameters
         * @return a stage completing with the command result
         */
        public CompletionStage<SetUserAgentOverrideResult> setUserAgentOverride(SetUserAgentOverrideParams params) {
            return client.call("Network.setUserAgentOverride", params, SetUserAgentOverrideResult::fromMap);
        }
        /**
         * Enables streaming of the response for the given requestId. If enabled, the dataReceived event contains the data that was received during streaming.
         * <p><b>Experimental:</b> this part of CDP may change without notice.
         * @param params command parameters
         * @return a stage completing with the command result
         */
        public CompletionStage<StreamResourceContentResult> streamResourceContent(StreamResourceContentParams params) {
            return client.call("Network.streamResourceContent", params, StreamResourceContentResult::fromMap);
        }
        /**
         * Returns information about the COEP/COOP isolation status.
         * <p><b>Experimental:</b> this part of CDP may change without notice.
         * @param params command parameters
         * @return a stage completing with the command result
         */
        public CompletionStage<GetSecurityIsolationStatusResult> getSecurityIsolationStatus(GetSecurityIsolationStatusParams params) {
            return client.call("Network.getSecurityIsolationStatus", params, GetSecurityIsolationStatusResult::fromMap);
        }
        /**
         * Returns information about the COEP/COOP isolation status.
         * <p><b>Experimental:</b> this part of CDP may change without notice.
         * @return a stage completing with the command result
         */
        public CompletionStage<GetSecurityIsolationStatusResult> getSecurityIsolationStatus() {
            return getSecurityIsolationStatus(GetSecurityIsolationStatusParams.builder().build());
        }
        /**
         * Enables tracking for the Reporting API, events generated by the Reporting API will now be delivered to the client. Enabling triggers &#x27;reportingApiReportAdded&#x27; for all existing reports.
         * <p><b>Experimental:</b> this part of CDP may change without notice.
         * @param params command parameters
         * @return a stage completing with the command result
         */
        public CompletionStage<EnableReportingApiResult> enableReportingApi(EnableReportingApiParams params) {
            return client.call("Network.enableReportingApi", params, EnableReportingApiResult::fromMap);
        }
        /**
         * Sets up tracking device bound sessions and fetching of initial set of sessions.
         * <p><b>Experimental:</b> this part of CDP may change without notice.
         * @param params command parameters
         * @return a stage completing with the command result
         */
        public CompletionStage<EnableDeviceBoundSessionsResult> enableDeviceBoundSessions(EnableDeviceBoundSessionsParams params) {
            return client.call("Network.enableDeviceBoundSessions", params, EnableDeviceBoundSessionsResult::fromMap);
        }
        /**
         * Deletes a device bound session.
         * <p><b>Experimental:</b> this part of CDP may change without notice.
         * @param params command parameters
         * @return a stage completing with the command result
         */
        public CompletionStage<DeleteDeviceBoundSessionResult> deleteDeviceBoundSession(DeleteDeviceBoundSessionParams params) {
            return client.call("Network.deleteDeviceBoundSession", params, DeleteDeviceBoundSessionResult::fromMap);
        }
        /**
         * Fetches the schemeful site for a specific origin.
         * <p><b>Experimental:</b> this part of CDP may change without notice.
         * @param params command parameters
         * @return a stage completing with the command result
         */
        public CompletionStage<FetchSchemefulSiteResult> fetchSchemefulSite(FetchSchemefulSiteParams params) {
            return client.call("Network.fetchSchemefulSite", params, FetchSchemefulSiteResult::fromMap);
        }
        /**
         * Fetches the resource and returns the content.
         * <p><b>Experimental:</b> this part of CDP may change without notice.
         * @param params command parameters
         * @return a stage completing with the command result
         */
        public CompletionStage<LoadNetworkResourceResult> loadNetworkResource(LoadNetworkResourceParams params) {
            return client.call("Network.loadNetworkResource", params, LoadNetworkResourceResult::fromMap);
        }
        /**
         * Sets Controls for third-party cookie access Page reload is required before the new cookie behavior will be observed
         * <p><b>Experimental:</b> this part of CDP may change without notice.
         * @param params command parameters
         * @return a stage completing with the command result
         */
        public CompletionStage<SetCookieControlsResult> setCookieControls(SetCookieControlsParams params) {
            return client.call("Network.setCookieControls", params, SetCookieControlsResult::fromMap);
        }
        /**
         * Fired when data chunk was received over the network.
         * @param handler event callback
         * @return a removable subscription
         */
        public CdpSubscription onDataReceived(Consumer<DataReceivedEvent> handler) {
            return client.on("Network.dataReceived", DataReceivedEvent::fromMap, handler);
        }
        /**
         * Fired when EventSource message is received.
         * @param handler event callback
         * @return a removable subscription
         */
        public CdpSubscription onEventSourceMessageReceived(Consumer<EventSourceMessageReceivedEvent> handler) {
            return client.on("Network.eventSourceMessageReceived", EventSourceMessageReceivedEvent::fromMap, handler);
        }
        /**
         * Fired when HTTP request has failed to load.
         * @param handler event callback
         * @return a removable subscription
         */
        public CdpSubscription onLoadingFailed(Consumer<LoadingFailedEvent> handler) {
            return client.on("Network.loadingFailed", LoadingFailedEvent::fromMap, handler);
        }
        /**
         * Fired when HTTP request has finished loading.
         * @param handler event callback
         * @return a removable subscription
         */
        public CdpSubscription onLoadingFinished(Consumer<LoadingFinishedEvent> handler) {
            return client.on("Network.loadingFinished", LoadingFinishedEvent::fromMap, handler);
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
            return client.on("Network.requestIntercepted", RequestInterceptedEvent::fromMap, handler);
        }
        /**
         * Fired if request ended up loading from cache.
         * @param handler event callback
         * @return a removable subscription
         */
        public CdpSubscription onRequestServedFromCache(Consumer<RequestServedFromCacheEvent> handler) {
            return client.on("Network.requestServedFromCache", RequestServedFromCacheEvent::fromMap, handler);
        }
        /**
         * Fired when page is about to send HTTP request.
         * @param handler event callback
         * @return a removable subscription
         */
        public CdpSubscription onRequestWillBeSent(Consumer<RequestWillBeSentEvent> handler) {
            return client.on("Network.requestWillBeSent", RequestWillBeSentEvent::fromMap, handler);
        }
        /**
         * Fired when resource loading priority is changed
         * <p><b>Experimental:</b> this part of CDP may change without notice.
         * @param handler event callback
         * @return a removable subscription
         */
        public CdpSubscription onResourceChangedPriority(Consumer<ResourceChangedPriorityEvent> handler) {
            return client.on("Network.resourceChangedPriority", ResourceChangedPriorityEvent::fromMap, handler);
        }
        /**
         * Fired when a signed exchange was received over the network
         * <p><b>Experimental:</b> this part of CDP may change without notice.
         * @param handler event callback
         * @return a removable subscription
         */
        public CdpSubscription onSignedExchangeReceived(Consumer<SignedExchangeReceivedEvent> handler) {
            return client.on("Network.signedExchangeReceived", SignedExchangeReceivedEvent::fromMap, handler);
        }
        /**
         * Fired when HTTP response is available.
         * @param handler event callback
         * @return a removable subscription
         */
        public CdpSubscription onResponseReceived(Consumer<ResponseReceivedEvent> handler) {
            return client.on("Network.responseReceived", ResponseReceivedEvent::fromMap, handler);
        }
        /**
         * Fired when WebSocket is closed.
         * @param handler event callback
         * @return a removable subscription
         */
        public CdpSubscription onWebSocketClosed(Consumer<WebSocketClosedEvent> handler) {
            return client.on("Network.webSocketClosed", WebSocketClosedEvent::fromMap, handler);
        }
        /**
         * Fired upon WebSocket creation.
         * @param handler event callback
         * @return a removable subscription
         */
        public CdpSubscription onWebSocketCreated(Consumer<WebSocketCreatedEvent> handler) {
            return client.on("Network.webSocketCreated", WebSocketCreatedEvent::fromMap, handler);
        }
        /**
         * Fired when WebSocket message error occurs.
         * @param handler event callback
         * @return a removable subscription
         */
        public CdpSubscription onWebSocketFrameError(Consumer<WebSocketFrameErrorEvent> handler) {
            return client.on("Network.webSocketFrameError", WebSocketFrameErrorEvent::fromMap, handler);
        }
        /**
         * Fired when WebSocket message is received.
         * @param handler event callback
         * @return a removable subscription
         */
        public CdpSubscription onWebSocketFrameReceived(Consumer<WebSocketFrameReceivedEvent> handler) {
            return client.on("Network.webSocketFrameReceived", WebSocketFrameReceivedEvent::fromMap, handler);
        }
        /**
         * Fired when WebSocket message is sent.
         * @param handler event callback
         * @return a removable subscription
         */
        public CdpSubscription onWebSocketFrameSent(Consumer<WebSocketFrameSentEvent> handler) {
            return client.on("Network.webSocketFrameSent", WebSocketFrameSentEvent::fromMap, handler);
        }
        /**
         * Fired when WebSocket handshake response becomes available.
         * @param handler event callback
         * @return a removable subscription
         */
        public CdpSubscription onWebSocketHandshakeResponseReceived(Consumer<WebSocketHandshakeResponseReceivedEvent> handler) {
            return client.on("Network.webSocketHandshakeResponseReceived", WebSocketHandshakeResponseReceivedEvent::fromMap, handler);
        }
        /**
         * Fired when WebSocket is about to initiate handshake.
         * @param handler event callback
         * @return a removable subscription
         */
        public CdpSubscription onWebSocketWillSendHandshakeRequest(Consumer<WebSocketWillSendHandshakeRequestEvent> handler) {
            return client.on("Network.webSocketWillSendHandshakeRequest", WebSocketWillSendHandshakeRequestEvent::fromMap, handler);
        }
        /**
         * Fired upon WebTransport creation.
         * @param handler event callback
         * @return a removable subscription
         */
        public CdpSubscription onWebTransportCreated(Consumer<WebTransportCreatedEvent> handler) {
            return client.on("Network.webTransportCreated", WebTransportCreatedEvent::fromMap, handler);
        }
        /**
         * Fired when WebTransport handshake is finished.
         * @param handler event callback
         * @return a removable subscription
         */
        public CdpSubscription onWebTransportConnectionEstablished(Consumer<WebTransportConnectionEstablishedEvent> handler) {
            return client.on("Network.webTransportConnectionEstablished", WebTransportConnectionEstablishedEvent::fromMap, handler);
        }
        /**
         * Fired when WebTransport is disposed.
         * @param handler event callback
         * @return a removable subscription
         */
        public CdpSubscription onWebTransportClosed(Consumer<WebTransportClosedEvent> handler) {
            return client.on("Network.webTransportClosed", WebTransportClosedEvent::fromMap, handler);
        }
        /**
         * Fired upon direct_socket.TCPSocket creation.
         * <p><b>Experimental:</b> this part of CDP may change without notice.
         * @param handler event callback
         * @return a removable subscription
         */
        public CdpSubscription onDirectTCPSocketCreated(Consumer<DirectTCPSocketCreatedEvent> handler) {
            return client.on("Network.directTCPSocketCreated", DirectTCPSocketCreatedEvent::fromMap, handler);
        }
        /**
         * Fired when direct_socket.TCPSocket connection is opened.
         * <p><b>Experimental:</b> this part of CDP may change without notice.
         * @param handler event callback
         * @return a removable subscription
         */
        public CdpSubscription onDirectTCPSocketOpened(Consumer<DirectTCPSocketOpenedEvent> handler) {
            return client.on("Network.directTCPSocketOpened", DirectTCPSocketOpenedEvent::fromMap, handler);
        }
        /**
         * Fired when direct_socket.TCPSocket is aborted.
         * <p><b>Experimental:</b> this part of CDP may change without notice.
         * @param handler event callback
         * @return a removable subscription
         */
        public CdpSubscription onDirectTCPSocketAborted(Consumer<DirectTCPSocketAbortedEvent> handler) {
            return client.on("Network.directTCPSocketAborted", DirectTCPSocketAbortedEvent::fromMap, handler);
        }
        /**
         * Fired when direct_socket.TCPSocket is closed.
         * <p><b>Experimental:</b> this part of CDP may change without notice.
         * @param handler event callback
         * @return a removable subscription
         */
        public CdpSubscription onDirectTCPSocketClosed(Consumer<DirectTCPSocketClosedEvent> handler) {
            return client.on("Network.directTCPSocketClosed", DirectTCPSocketClosedEvent::fromMap, handler);
        }
        /**
         * Fired when data is sent to tcp direct socket stream.
         * <p><b>Experimental:</b> this part of CDP may change without notice.
         * @param handler event callback
         * @return a removable subscription
         */
        public CdpSubscription onDirectTCPSocketChunkSent(Consumer<DirectTCPSocketChunkSentEvent> handler) {
            return client.on("Network.directTCPSocketChunkSent", DirectTCPSocketChunkSentEvent::fromMap, handler);
        }
        /**
         * Fired when data is received from tcp direct socket stream.
         * <p><b>Experimental:</b> this part of CDP may change without notice.
         * @param handler event callback
         * @return a removable subscription
         */
        public CdpSubscription onDirectTCPSocketChunkReceived(Consumer<DirectTCPSocketChunkReceivedEvent> handler) {
            return client.on("Network.directTCPSocketChunkReceived", DirectTCPSocketChunkReceivedEvent::fromMap, handler);
        }
        /**
         * Subscribes to Network.directUDPSocketJoinedMulticastGroup.
         * <p><b>Experimental:</b> this part of CDP may change without notice.
         * @param handler event callback
         * @return a removable subscription
         */
        public CdpSubscription onDirectUDPSocketJoinedMulticastGroup(Consumer<DirectUDPSocketJoinedMulticastGroupEvent> handler) {
            return client.on("Network.directUDPSocketJoinedMulticastGroup", DirectUDPSocketJoinedMulticastGroupEvent::fromMap, handler);
        }
        /**
         * Subscribes to Network.directUDPSocketLeftMulticastGroup.
         * <p><b>Experimental:</b> this part of CDP may change without notice.
         * @param handler event callback
         * @return a removable subscription
         */
        public CdpSubscription onDirectUDPSocketLeftMulticastGroup(Consumer<DirectUDPSocketLeftMulticastGroupEvent> handler) {
            return client.on("Network.directUDPSocketLeftMulticastGroup", DirectUDPSocketLeftMulticastGroupEvent::fromMap, handler);
        }
        /**
         * Fired upon direct_socket.UDPSocket creation.
         * <p><b>Experimental:</b> this part of CDP may change without notice.
         * @param handler event callback
         * @return a removable subscription
         */
        public CdpSubscription onDirectUDPSocketCreated(Consumer<DirectUDPSocketCreatedEvent> handler) {
            return client.on("Network.directUDPSocketCreated", DirectUDPSocketCreatedEvent::fromMap, handler);
        }
        /**
         * Fired when direct_socket.UDPSocket connection is opened.
         * <p><b>Experimental:</b> this part of CDP may change without notice.
         * @param handler event callback
         * @return a removable subscription
         */
        public CdpSubscription onDirectUDPSocketOpened(Consumer<DirectUDPSocketOpenedEvent> handler) {
            return client.on("Network.directUDPSocketOpened", DirectUDPSocketOpenedEvent::fromMap, handler);
        }
        /**
         * Fired when direct_socket.UDPSocket is aborted.
         * <p><b>Experimental:</b> this part of CDP may change without notice.
         * @param handler event callback
         * @return a removable subscription
         */
        public CdpSubscription onDirectUDPSocketAborted(Consumer<DirectUDPSocketAbortedEvent> handler) {
            return client.on("Network.directUDPSocketAborted", DirectUDPSocketAbortedEvent::fromMap, handler);
        }
        /**
         * Fired when direct_socket.UDPSocket is closed.
         * <p><b>Experimental:</b> this part of CDP may change without notice.
         * @param handler event callback
         * @return a removable subscription
         */
        public CdpSubscription onDirectUDPSocketClosed(Consumer<DirectUDPSocketClosedEvent> handler) {
            return client.on("Network.directUDPSocketClosed", DirectUDPSocketClosedEvent::fromMap, handler);
        }
        /**
         * Fired when message is sent to udp direct socket stream.
         * <p><b>Experimental:</b> this part of CDP may change without notice.
         * @param handler event callback
         * @return a removable subscription
         */
        public CdpSubscription onDirectUDPSocketChunkSent(Consumer<DirectUDPSocketChunkSentEvent> handler) {
            return client.on("Network.directUDPSocketChunkSent", DirectUDPSocketChunkSentEvent::fromMap, handler);
        }
        /**
         * Fired when message is received from udp direct socket stream.
         * <p><b>Experimental:</b> this part of CDP may change without notice.
         * @param handler event callback
         * @return a removable subscription
         */
        public CdpSubscription onDirectUDPSocketChunkReceived(Consumer<DirectUDPSocketChunkReceivedEvent> handler) {
            return client.on("Network.directUDPSocketChunkReceived", DirectUDPSocketChunkReceivedEvent::fromMap, handler);
        }
        /**
         * Fired when additional information about a requestWillBeSent event is available from the network stack. Not every requestWillBeSent event will have an additional requestWillBeSentExtraInfo fired for it, and there is no guarantee whether requestWillBeSent or requestWillBeSentExtraInfo will be fired first for the same request.
         * <p><b>Experimental:</b> this part of CDP may change without notice.
         * @param handler event callback
         * @return a removable subscription
         */
        public CdpSubscription onRequestWillBeSentExtraInfo(Consumer<RequestWillBeSentExtraInfoEvent> handler) {
            return client.on("Network.requestWillBeSentExtraInfo", RequestWillBeSentExtraInfoEvent::fromMap, handler);
        }
        /**
         * Fired when additional information about a responseReceived event is available from the network stack. Not every responseReceived event will have an additional responseReceivedExtraInfo for it, and responseReceivedExtraInfo may be fired before or after responseReceived.
         * <p><b>Experimental:</b> this part of CDP may change without notice.
         * @param handler event callback
         * @return a removable subscription
         */
        public CdpSubscription onResponseReceivedExtraInfo(Consumer<ResponseReceivedExtraInfoEvent> handler) {
            return client.on("Network.responseReceivedExtraInfo", ResponseReceivedExtraInfoEvent::fromMap, handler);
        }
        /**
         * Fired when 103 Early Hints headers is received in addition to the common response. Not every responseReceived event will have an responseReceivedEarlyHints fired. Only one responseReceivedEarlyHints may be fired for eached responseReceived event.
         * <p><b>Experimental:</b> this part of CDP may change without notice.
         * @param handler event callback
         * @return a removable subscription
         */
        public CdpSubscription onResponseReceivedEarlyHints(Consumer<ResponseReceivedEarlyHintsEvent> handler) {
            return client.on("Network.responseReceivedEarlyHints", ResponseReceivedEarlyHintsEvent::fromMap, handler);
        }
        /**
         * Fired exactly once for each Trust Token operation. Depending on the type of the operation and whether the operation succeeded or failed, the event is fired before the corresponding request was sent or after the response was received.
         * <p><b>Experimental:</b> this part of CDP may change without notice.
         * @param handler event callback
         * @return a removable subscription
         */
        public CdpSubscription onTrustTokenOperationDone(Consumer<TrustTokenOperationDoneEvent> handler) {
            return client.on("Network.trustTokenOperationDone", TrustTokenOperationDoneEvent::fromMap, handler);
        }
        /**
         * Fired once security policy has been updated.
         * <p><b>Experimental:</b> this part of CDP may change without notice.
         * @param handler event callback
         * @return a removable subscription
         */
        public CdpSubscription onPolicyUpdated(Consumer<PolicyUpdatedEvent> handler) {
            return client.on("Network.policyUpdated", PolicyUpdatedEvent::fromMap, handler);
        }
        /**
         * Is sent whenever a new report is added. And after &#x27;enableReportingApi&#x27; for all existing reports.
         * <p><b>Experimental:</b> this part of CDP may change without notice.
         * @param handler event callback
         * @return a removable subscription
         */
        public CdpSubscription onReportingApiReportAdded(Consumer<ReportingApiReportAddedEvent> handler) {
            return client.on("Network.reportingApiReportAdded", ReportingApiReportAddedEvent::fromMap, handler);
        }
        /**
         * Subscribes to Network.reportingApiReportUpdated.
         * <p><b>Experimental:</b> this part of CDP may change without notice.
         * @param handler event callback
         * @return a removable subscription
         */
        public CdpSubscription onReportingApiReportUpdated(Consumer<ReportingApiReportUpdatedEvent> handler) {
            return client.on("Network.reportingApiReportUpdated", ReportingApiReportUpdatedEvent::fromMap, handler);
        }
        /**
         * Subscribes to Network.reportingApiEndpointsChangedForOrigin.
         * <p><b>Experimental:</b> this part of CDP may change without notice.
         * @param handler event callback
         * @return a removable subscription
         */
        public CdpSubscription onReportingApiEndpointsChangedForOrigin(Consumer<ReportingApiEndpointsChangedForOriginEvent> handler) {
            return client.on("Network.reportingApiEndpointsChangedForOrigin", ReportingApiEndpointsChangedForOriginEvent::fromMap, handler);
        }
        /**
         * Triggered when the initial set of device bound sessions is added.
         * <p><b>Experimental:</b> this part of CDP may change without notice.
         * @param handler event callback
         * @return a removable subscription
         */
        public CdpSubscription onDeviceBoundSessionsAdded(Consumer<DeviceBoundSessionsAddedEvent> handler) {
            return client.on("Network.deviceBoundSessionsAdded", DeviceBoundSessionsAddedEvent::fromMap, handler);
        }
        /**
         * Triggered when a device bound session event occurs.
         * <p><b>Experimental:</b> this part of CDP may change without notice.
         * @param handler event callback
         * @return a removable subscription
         */
        public CdpSubscription onDeviceBoundSessionEventOccurred(Consumer<DeviceBoundSessionEventOccurredEvent> handler) {
            return client.on("Network.deviceBoundSessionEventOccurred", DeviceBoundSessionEventOccurredEvent::fromMap, handler);
        }
    }
}
